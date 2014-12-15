package com.trascender.framework.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.PostPersist;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.engine.CollectionEntry;
import org.hibernate.envers.event.AuditEventListener;
import org.hibernate.event.AbstractCollectionEvent;
import org.hibernate.event.PostCollectionRecreateEvent;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PreCollectionRemoveEvent;
import org.hibernate.event.PreCollectionUpdateEvent;

import ar.trascender.util.ReflectionUtils;

import com.trascender.framework.business.ejb.BusinessAuxiliarAuditoriaBean;
import com.trascender.framework.util.anotations.NoAuditable;

public class TrascenderEnverListener extends AuditEventListener {
	private static final long serialVersionUID = -1762484473332795261L;

	@Override
	public void onPostInsert(PostInsertEvent event) {
		Object o = event.getEntity();

		EntidadTrascender locEntidad = null;
		if(o instanceof EntidadTrascender)locEntidad = (EntidadTrascender) o;
		else return;

		// Sesion nueva para que no entre en un bucle infinito
		Session locSession = event.getSession().getSessionFactory()
				.openSession();

		try {
			LogAuditoria locLogAuditoria = this.generarLogAuditoria(locEntidad, null, null, null, LogAuditoria.Tipo.CREO);
			locSession.merge(locLogAuditoria);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		// Sin flush no salva las auditorías a la base.
		locSession.flush();
	}

	private void llamarPostPersist(Object pObject){
		Method[] arregloMotodos = pObject.getClass().getDeclaredMethods();

		for(Method cadaMetodo : arregloMotodos){
			if(cadaMetodo.getAnnotation(PostPersist.class) != null){
				try {
					cadaMetodo.invoke(pObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void onPostDelete(PostDeleteEvent event) {
		super.onPostDelete(event);
	}

	@Override
	public void onPostRecreateCollection(PostCollectionRecreateEvent event) {
	}

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		Object o = event.getEntity();

		EntidadTrascender locEntidad = null;
		if(o instanceof EntidadTrascender)locEntidad = (EntidadTrascender) o;
		else if(o instanceof AuditoriaIndirecta)locEntidad = ((AuditoriaIndirecta)o).getEntidadTrascender();
		else return;

		//Para los atributos dinamicos, vamos a tomar como nombre de propiedad el nombre del atributo dinamico.
		String nombrePropiedad = null;
		// Sesion nueva para que no entre en un bucle infinito
		Session locSession = event.getSession().getSessionFactory()
				.openSession();
		for (int i : event.getDirtyProperties()) {

			nombrePropiedad = event.getPersister().getPropertyNames()[i];
			if(this.esNoAuditable(nombrePropiedad, o)){
				continue;
			}

			Object valorAnterior = event.getOldState()[i];
			Object nuevoValor = event.getState()[i];

			if(sonFechasIguales(nuevoValor, valorAnterior)){
				continue;
			}

			if (procesarEmbebidos(nombrePropiedad, locEntidad, nuevoValor, valorAnterior, locSession)){
				continue;
			}

			if(valorAnterior instanceof Date){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				valorAnterior = sdf.format(valorAnterior);
			}

			if (nuevoValor instanceof Date) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				nuevoValor = sdf.format(nuevoValor);
			}

			if(o instanceof AuditoriaIndirecta){
				AuditoriaIndirecta locAuditoria = (AuditoriaIndirecta) o;
				if(locAuditoria.concatenaNombre()){
					nombrePropiedad = locAuditoria.getNombrePropiedad() + " --> " + nombrePropiedad;
				} else{
					nombrePropiedad = locAuditoria.getNombrePropiedad();
				}
			}

			try {
				LogAuditoria locLogAuditoria = this.generarLogAuditoria(locEntidad, nombrePropiedad, valorAnterior, nuevoValor, LogAuditoria.Tipo.MODIFICO);
				locSession.merge(locLogAuditoria);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		// Sin flush no salva las auditorías a la base.
		locSession.flush();
	}

	@Override
	public void onPreRemoveCollection(PreCollectionRemoveEvent event) {
	}

	@Override
	public void onPreUpdateCollection(PreCollectionUpdateEvent event) {
		Object o = event.getAffectedOwnerOrNull(); 

		EntidadTrascender locAuditable = null;
		if(o instanceof EntidadTrascender)locAuditable = (EntidadTrascender) o;
		else if(o instanceof AuditoriaIndirecta)locAuditable = ((AuditoriaIndirecta)o).getEntidadTrascender();
		else return;

		String locNombrePropiedad = event.getCollection().getRole();
		locNombrePropiedad = locNombrePropiedad.substring(locNombrePropiedad.lastIndexOf(".") + 1);
		if(this.esNoAuditable(locNombrePropiedad, o)){
			return;
		}

		Session locSession = event.getSession();

		CollectionEntry collectionEntry = getCollectionEntry(event);

		List<Object> objetosAAgregar = new ArrayList<Object>();
		List<Object> objetosAQuitar = new ArrayList<Object>();

		//Si tenemos Snapshot, dejamos que Hibernate haga el trabajo
		if (collectionEntry.getSnapshot() != null){
			Iterator itDelete = event.getCollection().getDeletes(collectionEntry.getLoadedPersister(), false);
			while (itDelete.hasNext()){
				objetosAQuitar.add(itDelete.next());
			}
			//Elementos que se agregaron a la lista
			Iterator otroAdd = event.getCollection().entries(collectionEntry.getLoadedPersister());
			while (otroAdd.hasNext()){
				Object cadaObjeto = otroAdd.next();
				if (event.getCollection().needsInserting(cadaObjeto, 0, collectionEntry.getLoadedPersister().getElementType())){
					objetosAAgregar.add(cadaObjeto);
				}
			}

		}else {
			//No hay Snapshot, tenemos que hacerlo a pata.
			Serializable id = event.getAffectedOwnerIdOrNull();
			String locConsulta = "SELECT lista FROM "+o.getClass().getName()+" e JOIN e."+locNombrePropiedad+" lista WHERE e."+locAuditable.getNombrePropiedadId()+" = :id";
			PersistentCollection ps = event.getCollection();
			// si el ps esta vacio no obtenemos el iterator

			List<Object> locListaActual = new ArrayList<Object>();
			if(!ps.empty()){
				Iterator itEntries = event.getCollection().entries(collectionEntry.getLoadedPersister());
				while (itEntries.hasNext()){
					locListaActual.add(itEntries.next());
				}
			}
			Query query = locSession.createQuery(locConsulta);
			query.setParameter("id", id);
			List<Object> locListaAnterior = query.list();
			//			locSession.clear();
			for (Object cadaObjetoNuevo : locListaActual){
				if (!locListaAnterior.contains(cadaObjetoNuevo)){
					objetosAAgregar.add(cadaObjetoNuevo);
				}
			}
			for (Object cadaObjetoAnterior : locListaAnterior){
				if (!locListaActual.contains(cadaObjetoAnterior)){
					objetosAQuitar.add(cadaObjetoAnterior);
				}
			}
		}
		try{
			for (Object cadaObjetoAQuitar : objetosAQuitar){
				LogAuditoria locLog = this.generarLogAuditoria(locAuditable, locNombrePropiedad, cadaObjetoAQuitar, null, LogAuditoria.Tipo.QUITO);
				locSession.merge(locLog);
			}
			for (Object cadaObjetoAAgregar : objetosAAgregar){
				LogAuditoria locLog = this.generarLogAuditoria(locAuditable, locNombrePropiedad, null, cadaObjetoAAgregar, LogAuditoria.Tipo.AGREGO);
				locSession.merge(locLog);
			}
		} catch (Exception e){
			//Para que no nos interrumpa lo que viene haciendo en la base.
			e.printStackTrace();
		}
	}

	//
	private boolean sonFechasIguales(Object fecha1, Object fecha2){
		if(fecha1 != null && fecha2 != null){
			if(fecha1 instanceof Date){
				Date fechaAnterior = ((Date)fecha2);
				Date fechaNueva = (Date)fecha1;
				if(fechaAnterior.compareTo(fechaNueva) == 0){
					return false;
				}
			}
		}
		return false;
	}

	private boolean esNoAuditable(String nombrePropiedad, Object objeto){
		try{
			Class<?> clase = objeto.getClass();
			Field field = ReflectionUtils.getField(clase, nombrePropiedad); //getDeclaredField(nombrePropiedad);
			if (field != null) {
				NoAuditable noAuditable = field.getAnnotation(NoAuditable.class);
				if (noAuditable != null){
					return true;
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	private boolean procesarEmbebidos(String propiedad, EntidadTrascender entidad, Object embebidoActual, Object embebidoViejo, Session locSession){
		try{
			if (esEmbebida(propiedad, entidad)){
				for (Field cadaField : embebidoActual.getClass().getDeclaredFields()){
					cadaField.setAccessible(true);
					Object valorActual = cadaField.get(embebidoActual);
					Object valorAnterior = cadaField.get(embebidoViejo);
					if (!valorActual.equals(valorAnterior)){
						LogAuditoria locLogAuditoria = this.generarLogAuditoria(entidad, cadaField.getName(), valorAnterior, valorActual, LogAuditoria.Tipo.MODIFICO);
						locSession.merge(locLogAuditoria);
					}
				}
				return true;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	private boolean esEmbebida(String nombrePropiedad, Object objeto){
		try{
			Class<?> clase = objeto.getClass();
			Field field = ReflectionUtils.getField(clase, nombrePropiedad);
			if (field != null) {
				Embedded embebida = field.getAnnotation(Embedded.class);
				if (embebida != null){
					return true;
				}
			}
		} catch (Exception e){
		}
		return false;
	}

	private LogAuditoria generarLogAuditoria(EntidadTrascender pEntidadTrascender,
			String pPropiead, Object pValorAnterior, Object pNuevoValor, LogAuditoria.Tipo pTipo) throws Exception{

		BusinessAuxiliarAuditoriaBean businessAuditoria = null;
		try{
			businessAuditoria = getBusinessAuxiliarAuditoria();
		} catch(Exception e){
			e.printStackTrace();
		}

		LogAuditoria locLogAuditoria = new LogAuditoria();
		locLogAuditoria.setIdEntidad(pEntidadTrascender.getIdEntidad());
		locLogAuditoria.setIdRecurso(pEntidadTrascender.getSerialVersionUID());
		locLogAuditoria.setPropiedad(pPropiead);

		if(pNuevoValor != null){
			locLogAuditoria.setValorNuevo(pNuevoValor.toString());
			if(pNuevoValor instanceof EntidadTrascender){
				locLogAuditoria.setIdEntidadNueva(((EntidadTrascender) pNuevoValor).getIdEntidad());
			} else if(pNuevoValor.getClass().getName().toLowerCase().contains("trascender")){
				System.out.println("-------------------------->>>>>>>>>>>>>>>>" + pNuevoValor.getClass().getName().toLowerCase());
				locLogAuditoria.setIdEntidadNueva(this.getIdEntidad(pNuevoValor));
			}
		}
		if(pValorAnterior != null) {
			locLogAuditoria.setValorAnterior(pValorAnterior.toString());
			if(pValorAnterior instanceof EntidadTrascender){
				locLogAuditoria.setIdEntidadAnterior(((EntidadTrascender)pValorAnterior).getIdEntidad());
			} else if(pValorAnterior.getClass().getName().toLowerCase().contains("trascender")){
				System.out.println("-------------------------->>>>>>>>>>>>>>>>" + pValorAnterior.getClass().getName().toLowerCase());
				locLogAuditoria.setIdEntidadAnterior(getIdEntidad(pValorAnterior));
			}
		}		

		locLogAuditoria.setTipo(pTipo);

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date date = new Date(); 
		String dateFormateado = df.format(date);

		Date dateConHoras = new Date();
		try {
			dateConHoras = df.parse(dateFormateado);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		locLogAuditoria.setFecha(dateConHoras);
		locLogAuditoria.setComentario(businessAuditoria.getComentario());
		try{
			locLogAuditoria.setIdUsuario(SecurityMgr.getInstance().getUsuario(businessAuditoria.getLlave()).getIdUsuario());
		} catch(Exception e){
			e.printStackTrace();
		}
		return locLogAuditoria;
	}

	private CollectionEntry getCollectionEntry(AbstractCollectionEvent event) {
		return event.getSession().getPersistenceContext()
				.getCollectionEntry(event.getCollection());
	}

	public static BusinessAuxiliarAuditoriaBean getBusinessAuxiliarAuditoria() throws Exception {
		InitialContext initialContext = new InitialContext();
		BeanManager bm = (BeanManager) initialContext.lookup("java:comp/BeanManager");

		Bean bean = bm.getBeans(BusinessAuxiliarAuditoriaBean.class.getSimpleName()).iterator().next();
		CreationalContext ctx = bm.createCreationalContext(bean);
		BusinessAuxiliarAuditoriaBean businessAuditoria = (BusinessAuxiliarAuditoriaBean) bm.getReference(bean, bean.getClass(), ctx);
		return businessAuditoria;
	}

	public static void setValoresEnAuditoriaBean(EntidadTrascender pEntidad){
		BusinessAuxiliarAuditoriaBean locBusiness = null;
		try {
			locBusiness = getBusinessAuxiliarAuditoria();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(locBusiness == null)return;

		locBusiness.setComentario(pEntidad.getComentarioAuditoria());
		locBusiness.setLlave(pEntidad.getLlaveUsuarioAuditoria());
	}

	private Long getIdEntidad(Object pObj){
		Field locFieldId = ReflectionUtils.getFieldAnotado(pObj.getClass(), Id.class);
		if(locFieldId == null){
			return null;
		}
		locFieldId.setAccessible(true);
		Long locLong = null;
		try {
			locLong = locFieldId.getLong(pObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locLong;
	}
}
