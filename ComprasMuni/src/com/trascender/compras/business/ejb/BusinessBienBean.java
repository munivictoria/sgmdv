
package com.trascender.compras.business.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.compras.business.interfaces.BusinessBienLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroBien;
import com.trascender.compras.recurso.filtros.FiltroTipoBien;
import com.trascender.compras.recurso.filtros.FiltroUnidad;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.BienProvisto;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.TipoBien;
import com.trascender.compras.recurso.persistent.suministros.Unidad;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.AuxIdEntidad;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;

@Stateless(name = "BusinessBienLocal")
public class BusinessBienBean implements BusinessBienLocal {
	static {
		Grupo grupoRecursos = new Grupo();
		grupoRecursos.setId(BusinessBienBean.serialVersionUID);
		grupoRecursos.setNombre(BusinessBienBean.NOMBRE);

		// Esta pantalla no va mas.
		// Recurso grupoBien = new Recurso();
		// grupoBien.setIdRecurso(GrupoBien.getSerialVersionUID());
		// grupoBien.setNombre("Grupo de Bienes");
		// grupoRecursos.getListaRecursos().add(grupoBien);

		Recurso bien = new Recurso();
		bien.setIdRecurso(Bien.serialVersionUID);
		bien.setNombre("Bienes");
		bien.setAtributosConsultables("Nombre", "nombre", "Descripción", "descripcion", Tipo.TEXTO_LARGO, "Estado", "estado", "Unidad de Medida", "unidad");
		bien.setClase(Bien.class);
		grupoRecursos.getListaRecursos().add(bien);

		Recurso unidad = new Recurso();
		unidad.setIdRecurso(Unidad.serialVersionUID);
		unidad.setNombre("Unidades de Medida");
		unidad.setAtributosConsultables("Descripción", "descripcion");
		unidad.setClase(Unidad.class);
		grupoRecursos.getListaRecursos().add(unidad);

		Recurso tipoBien = new Recurso();
		tipoBien.setIdRecurso(TipoBien.serialVersionUID);
		tipoBien.setNombre("Categoría Bien");
		tipoBien.setAtributosConsultables("Nombre", "nombre", "Descripción", "descripcion", Tipo.TEXTO_LARGO, "Código imputación", "codigoImputacion");
		tipoBien.setClase(TipoBien.class);
		grupoRecursos.getListaRecursos().add(tipoBien);

		SecurityMgr.getInstance().addGrupo(grupoRecursos);
	}

	private static final long serialVersionUID = 6927039842065308919L;
	private static final String NOMBRE = "COM|Adm de Bienes Físicos";

	@PersistenceContext(name = "Vipians")
	private EntityManager entity;

	public BusinessBienBean() {
		super();

	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {

	}

	public void ejbRemove() throws EJBException, RemoteException {

	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {

	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {

	}

	// /**
	// * Agrega un grupo de bienes (productos)
	// * Business method
	// * Metodo no cambiado
	// * @ejb.interface-method view-type = "local"
	// */
	// public com.trascender.compras.recurso.persistent.suministros.GrupoBien
	// addGrupoBien(
	// com.trascender.compras.recurso.persistent.suministros.GrupoBien
	// pGrupoBien)
	// throws Exception {
	// if (pGrupoBien.getIdGrupoBien() > 0){
	// this.updateGrupoBien(pGrupoBien);
	// }else{
	// entity.persist(pGrupoBien);
	// for(GrupoBien locGrupoBien: pGrupoBien.getListaSubGrupoBienes()){
	// this.addGrupoBien(locGrupoBien);
	// }
	// }
	// return pGrupoBien;
	// }

	// /**
	// * Busca un grupo de bien por nombre (usados para controles de unicidad)
	// * Business method
	// * metodo no cambiado
	// * @ejb.interface-method view-type = "local"
	// */
	// public GrupoBien findGrupoBienesPorNombre(String pNombre)
	// throws Exception {
	// GrupoBien locGrupoBien = (GrupoBien) Criterio.getInstance(entity,
	// GrupoBien.class)
	// .add(Restriccion.LIKE("nombre", pNombre,false,Posicion.EXACTA))
	// .uniqueResult();
	//
	// //FIXME (Tener en cuenta.)si el metodo se usa para controles de unicidad
	// deberia hacerce uno especifico separado
	// //(Preferentemente privado) ya que esto limita a que las busquedas por
	// nombres tengan que ser de manera exacta
	// //y no por ej si quisiese buscar algun buen que contenga un pedazo o un
	// conjunto de caracteres
	// return locGrupoBien;
	// }

	// /**
	// * Busca una lista de grupos de bien por Nodo
	// * Business method
	// * Metodo no cambiado
	// * @ejb.interface-method view-type = "local"
	// */
	// public java.util.List findListaGrupoBienesPorNodo(
	// com.trascender.compras.recurso.persistent.suministros.GrupoBien pNodo)
	// throws Exception {
	// // aseguramiento de limpieza de basura
	// if (pNodo != null) {
	// pNodo.getListaBienes().clear();
	// pNodo.getListaSubGrupoBienes().clear();
	// }
	// List listaGrupoBien;
	// Criterio locCriterio = Criterio.getInstance(entity, GrupoBien.class);
	// if (pNodo != null){
	// locCriterio.add(Restriccion.IGUAL("padre.idGrupoBien",
	// pNodo.getIdGrupoBien()));
	// } else {
	// locCriterio.add(Restriccion.NULO("padre"));
	// }
	//
	// listaGrupoBien = locCriterio.list();
	//
	// // Agrego los Bienes del grupo
	// for (Object forObject:listaGrupoBien){
	// GrupoBien locForGrupoBien = (GrupoBien) forObject;
	// FiltroBien filtro = new FiltroBien();
	// filtro.setNodo(locForGrupoBien);
	// for (Object forBien:this.getListadoBienes(filtro).getListaResultados())
	// locForGrupoBien.addBien((Bien) forBien);
	// }
	// return listaGrupoBien;
	// }

	/**
	 * Business method Modifico un grupo de bien (grupo de productos) Método no modificado
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	// public GrupoBien
	// updateGrupoBien(com.trascender.compras.recurso.persistent.suministros.GrupoBien
	// pNodo)
	// throws java.lang.Exception {
	// if (pNodo.getIdGrupoBien() > 0){
	// entity.merge(pNodo);
	// for(GrupoBien locGrupoBien: pNodo.getListaSubGrupoBienes()){
	// this.updateGrupoBien(locGrupoBien);
	// }
	// }else{
	// this.addGrupoBien(pNodo);
	// }
	// return pNodo;
	// }

	/**
	 * Borra un grupo de bien. Método no modificado Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	// public void deleteGrupoBien(GrupoBien pNodo) throws Exception {
	//
	// //validar si no tiene grupos hijos asociados
	// Long cantidad = Criterio.getInstance(entity, GrupoBien.class)
	// .add(Restriccion.IGUAL("padre", pNodo))
	// .setProyeccion(Proyeccion.COUNT())
	// .uniqueResult();
	//
	// if (cantidad > 0){
	// throw new TrascenderComprasException(304);
	// }
	// this.validarDependenciaBienes(pNodo);
	// pNodo = entity.find(GrupoBien.class, pNodo.getIdGrupoBien());
	// entity.remove(pNodo);
	// }

	// // valida que no haya bienes asociados para el grupo que se intenta
	// eliminar
	// private void validarDependenciaBienes(GrupoBien pNodo) throws
	// TrascenderComprasException {
	// long locCantidadProveedores= (Long)Criterio.getInstance(this.entity,
	// Bien.class)
	// .add(Restriccion.IGUAL("grupo", pNodo))
	// .setProyeccion(Proyeccion.COUNT())
	// .uniqueResult();
	//
	// if(locCantidadProveedores > 0){
	// throw new TrascenderComprasException(305);
	// }
	//
	// }

	// /**
	// * Obtiene todos los grupos de bienes, los ordena ascendentemente por
	// nombre.
	// * Método no modificado
	// * Business method
	// * @ejb.interface-method view-type = "local"
	// */
	// public List getListadoGrupoBienes() throws Exception {
	// return Criterio.getInstance(entity,
	// GrupoBien.class).add(Orden.ASC("nombre")).list();
	// }
	//
	private void validarBien(Bien pBien) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, Bien.class)
		// .add(Restriccion.IGUAL("grupo", pBien.getGrupo()))
				.add(Restriccion.IGUAL("nombre", pBien.getNombre())).add(Restriccion.DISTINTO("idBien", pBien.getIdBien())).setProyeccion(Proyeccion.COUNT());

		Long cantidad = (Long) locCriterio.uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderComprasException(140);
		}
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public Bien addBien(Bien pBien) throws Exception {
		validarBien(pBien);
		pBien.setEstado(Bien.Estado.ACTIVO);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pBien);

		entity.persist(pBien);
		entity.flush();
		return pBien;
	}

	public FiltroBien getListadoBienes(FiltroBien filtro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, Bien.class).add(Restriccion.IGUAL("estado", filtro.getEstado()))
				.add(Restriccion.ILIKE("descripcion", filtro.getDescripcion())).add(Restriccion.ILIKE("nombre", filtro.getNombre())).add(Restriccion.IGUAL("tipo", filtro.getTipo()))
				.add(Restriccion.MIEMBRO_DE("listaTipoBien", filtro.getTipoBien())).add(Restriccion.IGUAL("unidad", filtro.getUnidad()))
				.add(Restriccion.MIEMBRO_DE("listaCodigosCiiu", filtro.getCodigoCiiu()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Bien.serialVersionUID, "idBien", filtro.getListaBusquedaPorLogs());
		filtro.procesarYListar(locCriterio);
		return filtro;
	}

	/**
	 * Business method Modifica un bien. Antes se hace comprobaciones para ver si no existe.
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public Bien updateBien(Bien pBien) throws Exception {
		validarBien(pBien);
		pBien.setEstado(Bien.Estado.ACTIVO);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pBien);

		pBien = entity.merge(pBien);
		entity.flush();
		return pBien;
	}

	/**
	 * Business method Modifica un bien provisto (bien asociado a un proveedor)
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pBienProvisto
	 * @return
	 * @throws Exception
	 */
	public BienProvisto updateBienProvisto(BienProvisto pBienProvisto) throws Exception {
		entity.merge(pBienProvisto);
		return pBienProvisto;
	}

	/**
	 * Borra un bien. El borrado es lógico. Se le cambia el estado del bien a INACTIVO Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteBien(Bien pBien) throws Exception {
		pBien = entity.merge(pBien);
		pBien.setEstado(Bien.Estado.INACTIVO);
	}

	/**
	 * Se restaura un bien INACTIVO a ACTIVO. Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.compras.recurso.persistent.suministros.Bien restoreBien(com.trascender.compras.recurso.persistent.suministros.Bien pBien) throws java.lang.Exception {
		pBien.setEstado(Bien.Estado.ACTIVO);
		entity.merge(pBien);
		return pBien;
	}

	/**
	 * Busca un bien por id Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.compras.recurso.persistent.suministros.Bien findBienByID(long pIdBien) throws java.lang.Exception {
		Bien locBien = entity.find(Bien.class, pIdBien);
		if(locBien != null) {
			// locBien.getGrupo().toString();
			locBien.getListaCodigosCiiu().toString();
			locBien.toString();
			locBien.getListaTipoBien().size();
			locBien.getListaLogsAuditoria().size();
		}
		return locBien;
	}

	/**
	 * Valida si existe un bien con ese nombre para ese grupo de bienes.
	 * 
	 * @param pGrupo
	 * @param pBien
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Bien> validarNombreBien(Bien pBien) throws Exception {
		return Criterio.getInstance(entity, Bien.class)
		// .add(Restriccion.IGUAL("grupo", pGrupo))
				.add(Restriccion.IGUAL("nombre", pBien.getNombre())).list();
	}

	public Unidad addUnidad(Unidad pUnidad) throws Exception {
		this.validarUnidad(pUnidad);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pUnidad);

		entity.persist(pUnidad);
		entity.flush();

		return pUnidad;
	}

	public Unidad updateUnidad(Unidad pUnidad) throws Exception {
		this.validarUnidad(pUnidad);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pUnidad);

		entity.merge(pUnidad);

		entity.flush();

		return pUnidad;
	}

	private void validarUnidad(Unidad pUnidad) throws TrascenderComprasException {
		Long cantidad = (Long) Criterio.getInstance(entity, Unidad.class).add(Restriccion.DISTINTO("idUnidad", pUnidad.getIdUnidad()))
				.add(Restriccion.LIKE("descripcion", pUnidad.getDescripcion(), false)).setProyeccion(Proyeccion.COUNT()).uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderComprasException(206);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteUnidad(Unidad pUnidad) throws Exception {
		Long cantidad = (Long) Criterio.getInstance(entity, Bien.class).add(Restriccion.IGUAL("unidad", pUnidad)).setProyeccion(Proyeccion.COUNT()).uniqueResult();

		if(cantidad > 0) {
			throw new TrascenderComprasException(205);
		}
		pUnidad = entity.find(Unidad.class, pUnidad.getIdUnidad());
		entity.remove(pUnidad);
	}

	public Unidad getUnidadByID(Long pIDUnidad) throws Exception {
		Unidad locUnidad = entity.find(Unidad.class, pIDUnidad);

		if(locUnidad != null) {
			locUnidad.getListaLogsAuditoria().size();
		}

		return locUnidad;
	}

	public TipoBien getTipoBienByID(Long pIDTipoBien) throws Exception {
		TipoBien locTipoBien = entity.find(TipoBien.class, pIDTipoBien);

		if(locTipoBien != null) {
			locTipoBien.getListaLogsAuditoria().size();
		}

		return locTipoBien;
	}

	public FiltroUnidad findListaUnidad(FiltroUnidad filtro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, Unidad.class).add(Restriccion.ILIKE("descripcion", filtro.getDescripcion()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Unidad.serialVersionUID, "idUnidad", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	public TipoBien addTipoBien(TipoBien pTipoBien) throws Exception {
		this.validarTipoBien(pTipoBien);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pTipoBien);

		entity.persist(pTipoBien);
		entity.flush();

		return pTipoBien;
	}

	public TipoBien updateTipoBien(TipoBien pTipoBien) throws Exception {
		this.validarTipoBien(pTipoBien);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pTipoBien);

		entity.merge(pTipoBien);

		entity.flush();

		return pTipoBien;
	}

	public void deleteTipoBien(TipoBien pTipoBien) throws Exception {
		this.validarBorradoTipoBien(pTipoBien);
		pTipoBien = entity.merge(pTipoBien);

		entity.remove(pTipoBien);
	}

	private void validarBorradoTipoBien(TipoBien pTipoBien) throws TrascenderComprasException {
		Criterio locCriterio = Criterio.getInstance(this.entity, Bien.class).add(Restriccion.MIEMBRO_DE("listaTipoBien", pTipoBien)).setProyeccion(Proyeccion.COUNT());
		Long cantidad = locCriterio.uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderComprasException(141);
		}
		locCriterio = Criterio.getInstance(this.entity, Proveedor.class).add(Restriccion.MIEMBRO_DE("listaTipoBien", pTipoBien)).setProyeccion(Proyeccion.COUNT());
		cantidad = locCriterio.uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderComprasException(142);
		}
	}

	public FiltroTipoBien findListaTipoBien(FiltroTipoBien filtro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, TipoBien.class).add(Restriccion.ILIKE("nombre", filtro.getNombre()))
				.add(Restriccion.ILIKE("descripcion", filtro.getDescripcion()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, TipoBien.serialVersionUID, "idTipoBien", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);
		return filtro;
	}

	private void validarTipoBien(TipoBien pTipoBien) throws TrascenderComprasException {
		Long cantidad = Criterio.getInstance(entity, TipoBien.class).add(Restriccion.DISTINTO("idTipoBien", pTipoBien.getIdTipoBien()))
				.add(Restriccion.LIKE("nombre", pTipoBien.getNombre(), false)).setProyeccion(Proyeccion.COUNT()).uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderComprasException(140);
		}
	}

	public FiltroBien findListaBien(FiltroBien filtro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, Bien.class)
				.add(Restriccion.ILIKE("nombre", filtro.getNombre()))
				.add(Restriccion.ILIKE("descripcion", filtro.getDescripcion()))
				.add(Restriccion.IGUAL("estado", filtro.getEstado()))
				.add(Restriccion.IGUAL("tipo", filtro.getTipo()));;
		if (filtro.getTipoBien() != null) {
			locCriterio.crearAlias("listaTipoBien", "cadaTipoBien")
				.add(Restriccion.IGUAL("cadaTipoBien", filtro.getTipoBien()));
		}
		filtro.procesarYListar(locCriterio);
		return filtro;
	}

	public List<AuxIdEntidad> findListaAuxIdBien(String cadena) throws Exception {
		Criterio locCriterio = Criterio.getInstance(entity, Bien.class);

		locCriterio.add(Restriccion.ILIKE("nombre||' '||unidad.descripcion", cadena).SIN_PROCESAR_ENTIDADES()).add(Restriccion.IGUAL("estado", Bien.Estado.ACTIVO));

		locCriterio.setProyeccion(Proyeccion.NEW(AuxIdEntidad.class, "idBien", "nombre||' ['||unidad.descripcion||']'").SIN_PROCESAR_ENTIDADES());

		List<AuxIdEntidad> locListaBienes = locCriterio.list();

		return locListaBienes;
	}
}