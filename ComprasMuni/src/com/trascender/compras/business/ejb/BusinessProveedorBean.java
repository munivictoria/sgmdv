
package com.trascender.compras.business.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.compras.business.interfaces.BusinessBienLocal;
import com.trascender.compras.business.interfaces.BusinessProveedorLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroProveedores;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.BienProvisto;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;

@Stateless(name = "BusinessProveedorLocal")
public class BusinessProveedorBean implements BusinessProveedorLocal {
	static {
		Grupo grupo = new Grupo();
		grupo.setId(BusinessProveedorBean.serialVersionUID);
		grupo.setNombre(BusinessProveedorBean.NOMBRE);

		// Recurso grupoProveedor = new Recurso();
		// grupoProveedor.setIdRecurso(GrupoProveedor.serialVersionUID);
		// grupoProveedor.setNombre("Grupo de Proveedores");
		// grupoProveedor.setAtributosConsultables("Nombre", "nombre");
		// grupo.getListaRecursos().add(grupoProveedor);

		Recurso proveedor = new Recurso();
		proveedor.setIdRecurso(Proveedor.serialVersionUID);
		proveedor.setNombre("Proveedores");
		proveedor.setAtributosConsultables("Razón Social", "razonSocial", "Teléfono", "telefono", "E-mail", "email", "Estado", "estado");
		proveedor.setClase(Proveedor.class);
		grupo.getListaRecursos().add(proveedor);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	/**
	 * 
	 */
	public static final long serialVersionUID = 8771239851837003136L;
	public static final String NOMBRE = "COM|Adm de Proveedores";

	@PersistenceContext
	private EntityManager entity;

	@EJB
	private BusinessBienLocal businessBien;

	public BusinessProveedorBean() {
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
	// * Agrega un grupo de proveedores.
	// * Business method
	// * @ejb.interface-method view-type = "local"
	// */
	// public com.trascender.compras.recurso.persistent.suministros.GrupoProveedor addGrupoProveedor(
	// com.trascender.compras.recurso.persistent.suministros.GrupoProveedor pGrupoProveedor)
	// throws java.lang.Exception {
	// this.validarGrupoProveedor(pGrupoProveedor);
	// entity.persist(pGrupoProveedor);
	// return pGrupoProveedor;
	// }
	//
	// private void validarGrupoProveedor(GrupoProveedor pGrupoProveedor) throws Exception{
	// Long cantidad = Criterio.getInstance(entity, GrupoProveedor.class)
	// .add(Restriccion.LIKE("nombre", pGrupoProveedor.getNombre(), false))
	// .add(Restriccion.DISTINTO("idGrupoProveedor", pGrupoProveedor.getIdGrupoProveedor()))
	// .setProyeccion(Proyeccion.COUNT())
	// .uniqueResult();
	// if (cantidad > 0){
	// throw new TrascenderComprasException(344);
	// }
	// }
	//
	//
	// /**
	// * Busca todos los grupos de proveedores y los devuelve ordenados ascendentemente por nombre
	// * Business method
	// * @ejb.interface-method view-type = "local"
	// */
	// public java.util.List findListadoGrupoProveedor()
	// throws java.lang.Exception {
	// return Criterio.getInstance(entity, GrupoProveedor.class)
	// .add(Orden.ASC("nombre")).list();
	// }
	//
	//
	// /**
	// * Modifica un grupo de proveedores
	// * Business method
	// * @ejb.interface-method view-type = "local"
	// */
	// public com.trascender.compras.recurso.persistent.suministros.GrupoProveedor updateGrupoProveedor(
	// com.trascender.compras.recurso.persistent.suministros.GrupoProveedor pGrupoProveedor)
	// throws java.lang.Exception {
	// this.validarGrupoProveedor(pGrupoProveedor);
	// entity.merge(pGrupoProveedor);
	// return pGrupoProveedor;
	// }
	//
	//
	// /**
	// * Borra un grupo de proveedores
	// * Business method
	// * @ejb.interface-method view-type = "local"
	// */
	// public void deleteGrupoProveedor(
	// com.trascender.compras.recurso.persistent.suministros.GrupoProveedor pGrupoProveedor)
	// throws java.lang.Exception {
	//
	// //Valida que no posea grupos asociados.
	// Long cantidad = Criterio.getInstance(entity, GrupoProveedor.class)
	// .add(Restriccion.IGUAL("grupo", pGrupoProveedor))
	// .setProyeccion(Proyeccion.COUNT())
	// .uniqueResult();
	//
	// if (cantidad > 0){
	// throw new TrascenderComprasException(345);
	// }
	// //validar que no tenga proveedores asociados
	// this.validarDependenciaProveedor(pGrupoProveedor);
	//
	// entity.remove(entity.merge(pGrupoProveedor));
	// }

	// private void validarDependenciaProveedor(GrupoProveedor pGrupoProveedor) throws TrascenderComprasException {
	// long locCantidadProveedores= (Long)Criterio.getInstance(this.entity, Proveedor.class)
	// .add(Restriccion.IGUAL("grupoProveedor", pGrupoProveedor))
	// .setProyeccion(Proyeccion.COUNT())
	// .uniqueResult();
	//
	// if(locCantidadProveedores > 0){
	// throw new TrascenderComprasException(346);
	// }
	//
	// }

	// /**
	// * Busca un grupo de proveedores por nodo (padre)
	// * Business method
	// * @ejb.interface-method view-type = "local"
	// */
	// public java.util.List findListaGrupoProveedoresPorNodo(
	// GrupoProveedor pGrupoProveedor) throws Exception {
	// // aseguramiento de limpieza de basura
	// if (pGrupoProveedor != null) {
	// pGrupoProveedor.getListaProveedores().clear();
	// pGrupoProveedor.getListaSubGrupoProveedores().clear();
	// }
	// //trae la lista de subgrupos para el grupo pGrupoProveedor (grupos hijos)
	// Criterio locCriterio = Criterio.getInstance(entity,
	// GrupoProveedor.class);
	// if (pGrupoProveedor != null) {
	// locCriterio.add(Restriccion.IGUAL("grupo.idGrupoProveedor",
	// pGrupoProveedor.getIdGrupoProveedor()));
	// } else {
	// locCriterio.add(Restriccion.NULO("grupo"));
	// }
	// List listaGrupoProveedor = locCriterio.list();
	// System.out.println("hijos encontrados en business"+ listaGrupoProveedor.size());
	// // Agrego los proveedores del grupo para cada grupo de proveedor
	// // for (Object cadaGrupoProveedor : listaGrupoProveedor) {
	// // GrupoProveedor locGrupoProveedor = (GrupoProveedor)cadaGrupoProveedor;
	// // for (Proveedor cadaProveedor : this
	// // .getListadoProveedoresPorGrupo(locGrupoProveedor)) {
	// // locGrupoProveedor.getListaProveedores().add(cadaProveedor);
	// //
	// // }
	// //
	// // }
	// return listaGrupoProveedor;
	// }
	//
	//
	// private List<Proveedor> getListadoProveedoresPorGrupo(GrupoProveedor pGrupo) {
	// List<Proveedor> locListaProveedores = Criterio.getInstance(this.entity, Proveedor.class)
	// .add(Restriccion.IGUAL("grupoProveedor", pGrupo))
	// .add(Restriccion.IGUAL("estado", Proveedor.Estado.ACTIVO))
	// .list();
	//
	// for(Proveedor cadaProveedor : locListaProveedores){
	// cadaProveedor.toString();
	// cadaProveedor.getDomicilio().toString();
	// cadaProveedor.getEstado().toString();
	// cadaProveedor.getGrupoProveedor().toString();
	// cadaProveedor.getProveedorLocal().toString();
	// }
	// return locListaProveedores;
	// }

	/**
	 * Agrega un proveedor Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.compras.recurso.persistent.suministros.Proveedor addProveedor(com.trascender.compras.recurso.persistent.suministros.Proveedor pProveedor)
			throws java.lang.Exception {
		validarProveedor(pProveedor);
		pProveedor.setEstado(Proveedor.Estado.ACTIVO);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pProveedor);

		this.entity.merge(pProveedor);

		this.entity.flush();

		return pProveedor;
	}

	/**
	 * @param pProveedor
	 * @throws TrascenderComprasException
	 */
	private void validarProveedor(Proveedor pProveedor) throws TrascenderComprasException {
		if(pProveedor == null) {
			throw new TrascenderComprasException(358);
		}

		if(pProveedor.getEmail() != null) {
			Criterio locCriterio = Criterio.getInstance(entity, Proveedor.class).add(Restriccion.IGUAL("email", pProveedor.getEmail()))
					.add(Restriccion.DISTINTO("idProveedor", pProveedor.getIdProveedor()));
			locCriterio.setModoDebug(true);
			Proveedor proveedor = locCriterio.uniqueResult();
			if(proveedor != null) {
				throw new TrascenderComprasException(360);
			}
		}

		if(pProveedor.getProveedorLocal() == null) {
			throw new TrascenderComprasException(359);
		}
		for(BienProvisto cadaBienProvisto : pProveedor.getListaBienProvisto()) {
			cadaBienProvisto.setProveedor(pProveedor);
		}
	}

	/**
	 * Busca un listado de proveedores por GrupoProveedor, Nombre, Estado y Bien Business method
	 * 
	 * @throws Exception
	 */
	public FiltroProveedores findListadoProveedores(FiltroProveedores filtro) throws Exception {
		Criterio locCriterio = 	Criterio.getInstance(entity,Proveedor.class)
			
			.add(Restriccion.IGUAL("estado", filtro.getEstado()))
			.add(Restriccion.LIKE("codigo", filtro.getCodigo(), false))//No ponemos ILIKE porque trae otros resultados
			.add(Restriccion.IGUAL("tipo", filtro.getTipo()))
			.add(Restriccion.MIEMBRO_DE("listaCodigosCiiu", filtro.getCodigoCiiu()))
			.add(Restriccion.MIEMBRO_DE("listaTipoBien", filtro.getTipoBien()));
		
		AtributoDinamico.addRestriccionesCriterio(locCriterio, Proveedor.serialVersionUID, "idProveedor", filtro.getListaAtributoDinamico());
		
		if(filtro.getPersona() !=null){
			locCriterio.add(Restriccion.IGUAL("proveedorLocal", filtro.getPersona()));
		} else {
			locCriterio.add(Restriccion.EN("proveedorLocal.idPersona", filtro.getListaIdPersonas()));
		}

		if(filtro.getRazonSocial() != null) {
			locCriterio.crearAlias("proveedorLocal", "locPersona").add(
					Restriccion.OR(Restriccion.ILIKE("locPersona.razonSocial", filtro.getRazonSocial()), Restriccion.ILIKE("locPersona.apellido", filtro.getRazonSocial()),
							Restriccion.ILIKE("locPersona.nombre", filtro.getRazonSocial())));
		}

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Proveedor.serialVersionUID, "idProveedor", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);
		return filtro;
	}

	/**
	 * Modifica un proveedor Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 * @param pProveedor
	 * @return
	 * @throws java.lang.Exception
	 */
	public com.trascender.compras.recurso.persistent.suministros.Proveedor updateProveedor(com.trascender.compras.recurso.persistent.suministros.Proveedor pProveedor)
			throws java.lang.Exception {
		validarProveedor(pProveedor);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pProveedor);

		entity.merge(pProveedor);

		this.entity.flush();

		return pProveedor;
	}

	public void validarQuitadoBienProvisto(BienProvisto pBienProvisto) throws TrascenderComprasException {
		Long cantidad = Criterio
				.getInstance(entity, LineaOrdenCompra.class)
				.add(Restriccion.IGUAL("bienProvisto", pBienProvisto))
				.add(Restriccion.NOT(Restriccion.OR(Restriccion.IGUAL("ordenCompra.estado", OrdenCompra.Estado.ANULADA),
						Restriccion.IGUAL("ordenCompra.estado", OrdenCompra.Estado.RESCINDIDA)))).setProyeccion(Proyeccion.COUNT()).uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderComprasException(356);
		}
	}

	public void deleteProveedor(Proveedor pProveedor) throws java.lang.Exception {
		TrascenderEnverListener.setValoresEnAuditoriaBean(pProveedor);
		pProveedor.setEstado(Proveedor.Estado.INACTIVO);
		pProveedor = entity.merge(pProveedor);
		entity.flush();
	}

	/**
	 * Restaura un proveedor INACTIVO a ACTIVO. Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.compras.recurso.persistent.suministros.Proveedor restoreProveedor(com.trascender.compras.recurso.persistent.suministros.Proveedor pProveedor)
			throws java.lang.Exception {
		TrascenderEnverListener.setValoresEnAuditoriaBean(pProveedor);
		pProveedor.setEstado(Proveedor.Estado.ACTIVO);
		entity.merge(pProveedor);
		entity.flush();
		return pProveedor;
	}

	/**
	 * Busca un proveedor por ID Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.compras.recurso.persistent.suministros.Proveedor findProveedorByID(long pIdProveedor) throws java.lang.Exception {
			Proveedor locProveedor = entity.find(Proveedor.class, pIdProveedor);
			
			locProveedor.getListaCodigosCiiu().size();
			locProveedor.getListaAtributosDinamicos().size();
						
			if(locProveedor != null){
				locProveedor.toString();
				locProveedor.getListaLogsAuditoria().size();
			}
			
			if(locProveedor.getDomicilio() != null){
				locProveedor.getDomicilio().toString();
			}
			
			if(locProveedor.getProveedorLocal()!= null){
				locProveedor.getProveedorLocal().toString();
			}
			
			locProveedor.getListaTipoBien().size();
			
			
			if(!locProveedor.getListaBienes().isEmpty()){
				locProveedor.getListaBienes().toString();
				for(Bien cadaBien: locProveedor.getListaBienes()){
					cadaBien.toString();
//					cadaBien.getGrupo().toString();
				}
			}

		if(!locProveedor.getListaBienProvisto().isEmpty()) {
			locProveedor.getListaBienProvisto().toString();
			for(BienProvisto locBienProvisto : locProveedor.getListaBienProvisto()) {
				locBienProvisto.getBien().getNombre();
				locBienProvisto.toString();
				locBienProvisto.getBien().toString();
				// locBienProvisto.getBien().getGrupo().toString();
				locBienProvisto.getDescripcion();
			}
		}
		return locProveedor;
	}

	// /**
	// * Busca un grupo de proveedores por nombre
	// * Business method
	// * @ejb.interface-method view-type = "local"
	// */
	// public com.trascender.compras.recurso.persistent.suministros.GrupoProveedor findGrupoProveedorPorNombre(
	// String pNombre) throws java.lang.Exception {
	// GrupoProveedor locGrupoProveedor = Criterio.getInstance(entity, GrupoProveedor.class)
	// .add(Restriccion.ILIKE("nombre", pNombre)).uniqueResult();
	// locGrupoProveedor.toString();
	// if (locGrupoProveedor.getGrupo() != null) {
	// locGrupoProveedor.getGrupo().toString();
	// }
	// return locGrupoProveedor;
	// }

	public List<Bien> findListaBienesProvistos(Proveedor pProveedor) {
		Criterio locCriterio = Criterio.getInstance(entity, Bien.class).add(Restriccion.IGUAL("estado", Bien.Estado.ACTIVO)).crearAlias("listaCodigosCiiu", "cadaCiiu")
				.add(Restriccion.EN("cadaCiiu", pProveedor.getListaCodigosCiiu()));
		return locCriterio.list();
	}

}