
package com.trascender.habilitaciones.business.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import ar.trascender.criterio.enums.Posicion;

import com.trascender.catastro.business.interfaces.BusinessRegistroParcelarioLocal;
import com.trascender.catastro.business.interfaces.BusinessRegistroPropiedadLocal;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.ParcelaPorCuadra;
import com.trascender.framework.business.interfaces.BusinessPersonaLocal;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoPlanObraLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessObligacionLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessPlantillaObligacionLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroObra;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.RegistroValuado;
import com.trascender.habilitaciones.recurso.persistent.pfo.CuadraAfectada;
import com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra;

@Stateless(name = "ejb/BusinessDocumentoPlanObraLocal")
public class BusinessDocumentoPlanObraBean implements BusinessDocumentoPlanObraLocal {

	private static final long serialVersionUID = -573189414739916046L;

	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre("HAB|Adm. Obligación PFO");

		Recurso obra = new Recurso();
		obra.setIdRecurso(Obra.serialVersionUID);
		obra.setNombre("Obra");
		obra.setAtributosConsultables("Nº", "numeroObra", "Descripción", "descripcion", Tipo.TEXTO_LARGO, "Metros afectados", "metrosTotalAfectados", "Tipo de Obra", "tipoObra");
		obra.setClase(Obra.class);
		grupo.getListaRecursos().add(obra);

		Recurso planCuentaObra = new Recurso();
		planCuentaObra.setIdRecurso(PlanCuentaObra.serialVersionUID);
		planCuentaObra.setNombre("Plan de Cuenta");
		planCuentaObra.setClase(PlanCuentaObra.class);
		grupo.getListaRecursos().add(planCuentaObra);

		Recurso documentoPlanObra = new Recurso();
		documentoPlanObra.setIdRecurso(DocumentoPlanObra.serialVersionUID);
		documentoPlanObra.setNombre("Obligación PFO");
		documentoPlanObra.setClase(DocumentoPlanObra.class);
		grupo.getListaRecursos().add(documentoPlanObra);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	@EJB
	private BusinessRegistroPropiedadLocal businessRegistroPropiedadLocal;
	@EJB
	private BusinessRegistroParcelarioLocal businessRegistroParcelarioLocal;
	@EJB
	private BusinessPersonaLocal businessPersonaLocal;
	@EJB
	private BusinessObligacionLocal businessObligacionLocal;
	@EJB
	private BusinessPlantillaObligacionLocal businessPlantillaObligacionLocal;

	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;

	public BusinessDocumentoPlanObraBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}

	/**
	 * 
	 * @param pObra
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.habilitaciones.recurso.persistent.pfo.Obra addObra(com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra) throws Exception {

		this.validarObra(pObra);

		this.entityManager.persist(pObra);
		this.entityManager.flush();
		this.entityManager.refresh(pObra);

		return pObra;
	}

	/**
	 * Valida que no haya una obra con el mismo numero
	 * 
	 * @param pObra
	 * @throws Exception
	 */
	private void validarObra(Obra pObra) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, Obra.class).add(Restriccion.LIKE("numeroObra", pObra.getNumeroObra(), true, Posicion.EXACTA))
				.setProyeccion(Proyeccion.COUNT());

		if(pObra.getIdObra() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idObra", pObra.getIdObra())));
		}

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new HabilitacionesException(99);
		}
	}

	/**
	 * @param pObra
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.habilitaciones.recurso.persistent.pfo.Obra updateObra(com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra) throws Exception {
		this.validarObra(pObra);
		pObra = this.entityManager.merge(pObra);

		return pObra;
	}

	/**
	 * Valida si la obra esta asociada a alguna obligacion
	 */

	private void validarAsociacionObra(Obra pObra) throws Exception {

		if((Long) Criterio.getInstance(this.entityManager, DocumentoPlanObra.class).add(Restriccion.IGUAL("obra", pObra)).setProyeccion(Proyeccion.COUNT()).uniqueResult() > 0) {
			throw new HabilitacionesException(97);
		}
	}

	/**
	 * 
	 * @param pObra
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteObra(com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra) throws Exception {
		pObra = this.entityManager.getReference(Obra.class, pObra.getIdObra());
		this.validarAsociacionObra(pObra);
		this.entityManager.remove(pObra);
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public FiltroObra findListaObras(FiltroObra pFiltro) throws Exception {

		Criterio locCriterio = Criterio
				.getInstance(this.entityManager, Obra.class)
				.setDistinct(true)
				.crearAlias("listaCuadras", "cadaCuadra")
				.add(Restriccion.IGUAL("tipoObra", pFiltro.getTipoObra()))
				.add(Restriccion.LIKE("descripcion", pFiltro.getDescripcion(), false, Posicion.CUALQUIERA))
				.add(Restriccion.IGUAL("cadaCuadra", pFiltro.getCuadra()))
				.add(Restriccion.LIKE("numeroObra", ((pFiltro.getNumero() != null && !pFiltro.getNumero().trim().equals("") ? pFiltro.getNumero() : null)), true, Posicion.AL_PRINCIPIO));

		pFiltro.procesarYListar(locCriterio);

		return pFiltro;

	}

	/**
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.habilitaciones.recurso.persistent.pfo.Obra getObraPorId(long pId) throws Exception {
		Obra locObra = (Obra) Criterio.getInstance(this.entityManager, Obra.class).add(Restriccion.IGUAL("idObra", pId)).uniqueResult();

		locObra.toString();
		for(Cuadra cadaCuadra : locObra.getListaCuadras()) {
			cadaCuadra.toString();
		}

		for(PlanCuentaObra cadaPlanCuentaObra : locObra.getListaPlanesCuentaPorObra()) {
			cadaPlanCuentaObra.toString();
		}

		return locObra;
	}

	/**
	 * 
	 * @param pDocumentoPlanObra
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra addDocumentoPlanObra(
			com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra pDocumentoPlanObra) throws Exception {

		this.entityManager.persist(pDocumentoPlanObra);
		this.entityManager.flush();
		this.entityManager.refresh(pDocumentoPlanObra);

		return pDocumentoPlanObra;
	}

	/**
	 * @param pDocumentoPlanObra
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra updateDocumentoPlanObra(
			com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra pDocumentoPlanObra) throws Exception {

		this.entityManager.merge(pDocumentoPlanObra);

		return pDocumentoPlanObra;
	}

	/**
	 * 
	 * @param pDocumentoPlanObra
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 * 
	 */
	public void deleteDocumentoPlanObra(com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra pDocumentoPlanObra) throws Exception {

		this.entityManager.getReference(DocumentoPlanObra.class, pDocumentoPlanObra);
		this.entityManager.remove(pDocumentoPlanObra);
	}

	/**
	 * Recupera la lista de documentos especializados de SHPS de una persona
	 * 
	 * @param pPersona
	 *            pPersona
	 * @return listado de documentos especializados
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findListaDocumentosPlanObra(com.trascender.framework.recurso.persistent.Persona pPersona, Integer pNumeroRegistro,
			com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra) throws Exception {

		List locListaResultado = Criterio.getInstance(this.entityManager, DocumentoPlanObra.class)
				.add(Restriccion.NOT(Restriccion.IGUAL("obligacion.estado", Obligacion.Estado.ANULADO))).add(Restriccion.IGUAL("obra", pObra))
				.add(Restriccion.IGUAL("obligacion.persona", pPersona)).add(Restriccion.IGUAL("parcela.nroRegistro", pNumeroRegistro)).list();

		for(Object cadaObject : locListaResultado) {
			DocumentoPlanObra cadaDocumento = (DocumentoPlanObra) cadaObject;
			cadaDocumento.toString();
			cadaDocumento.getObligacion().toString();
			cadaDocumento.getObligacion().getPersona().toString();

			if(cadaDocumento.getObligacion().getListaRegistrosExencion() != null && !cadaDocumento.getObligacion().getListaRegistrosExencion().isEmpty()) {
				cadaDocumento.getObligacion().getListaRegistrosExencion().size();
			}
		}

		return locListaResultado;
	}

	/**
	 * Recupera el listado de obligaciones del documento plan de obra
	 * 
	 * @param pPersona
	 * @param pNumeroRegistro
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findListaObligacionPlanObra(com.trascender.framework.recurso.persistent.Persona pPersona, Integer pNumeroRegistro,
			com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, Obligacion.class).setModoDebug(true).crearAlias("documentoEspecializado", "locDocumento")
				.add(Restriccion.JPQL("TYPE (locDocumento) = ".concat(DocumentoPlanObra.class.getSimpleName()))).add(Restriccion.IGUAL("persona", pPersona))
				.add(Restriccion.IGUAL("locDocumento.obra", pObra));

		if(pNumeroRegistro != null) {
			locCriterio.add(Restriccion.IGUAL("locDocumento.parcela.nroRegistro", pNumeroRegistro.toString()));
		}

		List locListaRetorno = locCriterio.list();

		for(Object cadaOb : locListaRetorno) {
			Obligacion cadaObligacion = (Obligacion) cadaOb;

			cadaObligacion.getPersona().toString();
			DocumentoPlanObra locDocumento = (DocumentoPlanObra) cadaObligacion.getDocumentoEspecializado();
			locDocumento.toString();
			locDocumento.getParcela().toString();

			if(cadaObligacion.getListaRegistrosExencion() != null && !cadaObligacion.getListaRegistrosExencion().isEmpty()) {
				cadaObligacion.getListaRegistrosExencion().toString();
			}
		}

		return locListaRetorno;
	}

	/**
	 * 
	 * @param pPlanCuentaObra
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra addPlanCuentaObra(com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra pPlanCuentaObra)
			throws Exception {

		this.entityManager.persist(pPlanCuentaObra);
		this.entityManager.flush();
		this.entityManager.refresh(pPlanCuentaObra);

		return pPlanCuentaObra;
	}

	/**
	 * 
	 * @param pPlanCuentaObra
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra updatePlanCuentaObra(com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra pPlanCuentaObra)
			throws Exception {

		pPlanCuentaObra = this.entityManager.merge(pPlanCuentaObra);
		this.entityManager.flush();

		return pPlanCuentaObra;
	}

	/**
	 * 
	 * @param pPlanCuentaObra
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deletePlanCuentaObra(com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra pPlanCuentaObra) throws Exception {
		this.validarAsociacionPlanCuentaObra(pPlanCuentaObra);
		pPlanCuentaObra = this.entityManager.getReference(PlanCuentaObra.class, pPlanCuentaObra.getIdPlanCuentaPorObra());
		this.entityManager.remove(pPlanCuentaObra);
	}

	/**
	 * Valida si un plan cuenta obra esta asociado a una obra
	 * 
	 * @param pPlanCuentaObra
	 * @throws Exception
	 */
	private void validarAsociacionPlanCuentaObra(PlanCuentaObra pPlanCuentaObra) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, Obra.class).crearAlias("listaPlanesCuentaPorObra", "cadaPlan")
				.add(Restriccion.IGUAL("cadaPlan", pPlanCuentaObra)).setProyeccion(Proyeccion.COUNT());

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new HabilitacionesException(103);
		}
	}

	/**
	 * 
	 * @param pNombre
	 *            nombre del plan cuenta obra
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findListaPlanCuentaObra(String pNombre) throws Exception {
		return Criterio.getInstance(this.entityManager, PlanCuentaObra.class).add(Restriccion.LIKE("nombre", pNombre, true, Posicion.AL_PRINCIPIO)).list();
	}

	/**
	 * 
	 * @param pId
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra getPlanCuentaObraPorId(long pId) throws Exception {

		PlanCuentaObra locPlanCuenta = (PlanCuentaObra) Criterio.getInstance(this.entityManager, PlanCuentaObra.class).add(Restriccion.IGUAL("idPlanCuentaPorObra", pId)).uniqueResult();

		locPlanCuenta.toString();

		return locPlanCuenta;
	}

	/**
	 * Genera las obligaciones para todas los propietarios de parcelas que se encuentren dentro de las cuadras que pertenecen a una obra
	 * 
	 * @param pPlantillaObligacion
	 * @param pObra
	 * @param pPlanCuentaDefecto
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public int generarObligacionesFromObra(com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion pPlantillaObligacion,
			com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra, com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra pPlanCuentaDefecto) throws Exception {

		Map<Parcela, Obligacion> listaObligacionesAgregar = new HashMap<Parcela, Obligacion>();
		Map<Parcela, Obligacion> listaObligacionesActualizar = new HashMap<Parcela, Obligacion>();

		pObra = this.getObraPorId(pObra.getIdObra());
		pPlantillaObligacion = this.businessPlantillaObligacionLocal.getPlantillaObligacion(pPlantillaObligacion.getIdPlantillaObligacion());

		for(Cuadra locCuadra : pObra.getListaCuadras()) {
			List<ParcelaPorCuadra> locLista = businessRegistroParcelarioLocal.findListaParcelasPorCuadra(locCuadra.getIdCuadra());
			for(ParcelaPorCuadra locParcelaPorCuadra : locLista) {
				// PRIMERO VERIFICO QUE LA PARCELA TENGA DUEÑO , Y QUE LA PARCELA TENGA METROS DE FRENTE SINO NO SE
				// PUEDE ASIGNAR ESA OBLIGACIÓN
				Parcela locParcela = locParcelaPorCuadra.getParcela();
				Persona locPropietario = this.businessRegistroPropiedadLocal.getPropietario(locParcela);
				System.out.println("propietarioo");
				System.out.println(locPropietario);

				if(locPropietario != null && locParcela.getMetrosFrentePorCuadra(locCuadra) > 0) {
					boolean nuevaObligacion = true;
					Obligacion locObligacion = null;
					// compruebo si la obligacion no esta en la base
					locObligacion = this.getObligacionPorParcelaYObra(locParcela, pObra);

					if(locObligacion == null) {
						// Si la obligación no se encuentra en la base de
						// datos verifico que no esté a punto de agregarla
						locObligacion = listaObligacionesAgregar.get(locParcela);
						if(locObligacion == null) {
							locObligacion = listaObligacionesActualizar.get(locParcela);
						}
					} else {
						// En caso que la obligación se encuentre en la base
						// de datos
						nuevaObligacion = false;
					}

					DocumentoPlanObra locDocumentoPlanObra = null;

					if(locObligacion == null) {
						locObligacion = this.businessPlantillaObligacionLocal.generarArbolObligacion(pPlantillaObligacion);

						// le asigno el numero_tramite a la obligacion....sumandole uno al mayor de la lista (si las obligaciones que se van a generar son mas
						// de una)
						if(listaObligacionesAgregar.size() >= 1) {
							System.out.println("tamanio lista = " + listaObligacionesAgregar.size());
							Integer mayor = Integer.MIN_VALUE;
							Iterator it = listaObligacionesAgregar.entrySet().iterator();
							while(it.hasNext()) {
								Map.Entry e = (Map.Entry) it.next();
								Obligacion cadaObligacion = (Obligacion) e.getValue();
								if(cadaObligacion.getNumeroTramite() > mayor) {
									mayor = cadaObligacion.getNumeroTramite();
								}
							}
							locObligacion.setNumeroTramite(mayor + 1);
						}

						locDocumentoPlanObra = (DocumentoPlanObra) locObligacion.getDocumentoEspecializado();
						// empiezo a cargar los datos del documento
						// especializado
						locDocumentoPlanObra.setDomicilio(locParcela.getDomicilioParcelario());
						locDocumentoPlanObra.setFechaCreacion(Calendar.getInstance().getTime());

						// solo asigno a la persona a quien es atribuida la
						// obligación, si la obligación no existe

						locObligacion.setPersona(locPropietario);

					} else {
						locDocumentoPlanObra = (DocumentoPlanObra) locObligacion.getDocumentoEspecializado();
					}

					// Cargo los datos mínimos del documento de plan de obra
					locDocumentoPlanObra.setParcela(locParcela);
					locDocumentoPlanObra.setObra(pObra);
					locDocumentoPlanObra.setPlanCuentaObra(pPlanCuentaDefecto);

					CuadraAfectada locCuadraAfectada = new CuadraAfectada();
					locCuadraAfectada.setDocumentoPlanObra(locDocumentoPlanObra);
					locCuadraAfectada.setCuadra(locCuadra);
					locCuadraAfectada.setMetros(locParcela.getMetrosFrentePorCuadra(locCuadra));

					if(locCuadraAfectada.getMetros() <= 0) {
						locObligacion.setEstado(Obligacion.Estado.ANULADO);
					}

					locDocumentoPlanObra.getListaCuadrasAfectadas().add(locCuadraAfectada);
					// AGREGO LA OBLIGACIÓNif (sessCatastro!=null)
					// sessCatastro.close();

					if(nuevaObligacion) {
						listaObligacionesAgregar.put(locParcela, locObligacion);
					} else {
						locObligacion.ratificar();
						listaObligacionesActualizar.put(locParcela, locObligacion);
					}
				} else {
					Obligacion locObligacion = this.getObligacionPorParcelaYObra(locParcela, pObra);
					if(locObligacion != null) {

						DocumentoPlanObra locDocumentoPlanObra = (DocumentoPlanObra) locObligacion.getDocumentoEspecializado();
						for(CuadraAfectada cuadraAfectada : locDocumentoPlanObra.getListaCuadrasAfectadas()) {
							cuadraAfectada.setMetros(locParcela.getMetrosFrentePorCuadra(locCuadra));
						}
						locDocumentoPlanObra.setParcela(locParcela);
						locDocumentoPlanObra.setObra(pObra);
						locDocumentoPlanObra.setPlanCuentaObra(pPlanCuentaDefecto);

						locObligacion.setEstado(Obligacion.Estado.ANULADO);
						listaObligacionesActualizar.put(locParcela, locObligacion);
					}
				}
			}
		}

		for(Obligacion locObligacionAgregar : listaObligacionesAgregar.values()) {
			locObligacionAgregar.setIdObligacion(-1);
			this.businessObligacionLocal.addObligacion(locObligacionAgregar);
		}

		for(Obligacion locObligacionActualizar : listaObligacionesActualizar.values()) {
			this.businessObligacionLocal.updateObligacion(locObligacionActualizar);
		}

		return listaObligacionesAgregar.size() + listaObligacionesActualizar.size();
	}

	/**
	 * Recupera una obligación de plan de obra a partir de una parcela y una obra
	 * 
	 * @param pParcela
	 *            parcela a la q1ue pertenece la obligación
	 * @param pObra
	 *            obra a la que pertenece la obligación
	 * @return
	 * @throws Exception
	 * 
	 */

	private Obligacion getObligacionPorParcelaYObra(Parcela pParcela, Obra pObra) throws Exception {

		DocumentoPlanObra locDocumentoPlanObra = (DocumentoPlanObra) Criterio.getInstance(this.entityManager, DocumentoPlanObra.class).add(Restriccion.IGUAL("parcela", pParcela))
				.add(Restriccion.IGUAL("obra", pObra)).uniqueResult();

		if(locDocumentoPlanObra != null) {
			locDocumentoPlanObra.toString();
			locDocumentoPlanObra.getObra().toString();
			locDocumentoPlanObra.getParcela().toString();
			locDocumentoPlanObra.getDomicilio().toString();
			for(CuadraAfectada cadaCuadraAfectada : locDocumentoPlanObra.getListaCuadrasAfectadas()) {
				cadaCuadraAfectada.toString();
			}

			for(RegistroValuado cadaRegistroValuado : locDocumentoPlanObra.getListaRegistrosValuados()) {
				cadaRegistroValuado.toString();
			}

			return locDocumentoPlanObra.getObligacion();
		}

		return null;
	}

	/**
	 * Recupera el documento plan de obra para la obligación especificada
	 * 
	 * @param pObligacion
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra getDocumentoPlanObra(com.trascender.habilitaciones.recurso.persistent.Obligacion pObligacion)
			throws Exception {

		DocumentoPlanObra locDocumentoPlanObra = (DocumentoPlanObra) Criterio.getInstance(this.entityManager, DocumentoPlanObra.class).add(Restriccion.IGUAL("obligacion", pObligacion))
				.uniqueResult();

		if(locDocumentoPlanObra != null) {
			locDocumentoPlanObra.getObligacion().toString();
			locDocumentoPlanObra.toString();
			locDocumentoPlanObra.getObligacion().getPersona().toString();
			locDocumentoPlanObra.getDomicilio().toString();
			locDocumentoPlanObra.getObra().toString();
			locDocumentoPlanObra.getPlanCuentaObra().toString();

			locDocumentoPlanObra.getParcela().toString();
			for(ParcelaPorCuadra cadaParcelaPorCuadra : locDocumentoPlanObra.getParcela().getListaParcelasPorCuadra()) {
				cadaParcelaPorCuadra.toString();
				cadaParcelaPorCuadra.getParcela().toString();
				cadaParcelaPorCuadra.getCuadra().toString();
			}

			for(Cuadra cadaCuadra : locDocumentoPlanObra.getObra().getListaCuadras()) {
				cadaCuadra.toString();
				cadaCuadra.getListaParcelasPorCuadra().toString();
			}
		}

		return locDocumentoPlanObra;
	}

	/**
	 * Recupera el listado de cuadras afectadas de un documentoPFO
	 * 
	 * @param pDocumentoPlanObra
	 * @return
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public java.util.List getListaCuadrasAfectadasFromDocumentoPFO(com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra pDocumentoPlanObra) throws Exception {

		List locListaCuadraAfectadas = Criterio.getInstance(this.entityManager, CuadraAfectada.class).add(Restriccion.IGUAL("documentoPlanObra", pDocumentoPlanObra)).list();

		for(Object cadaOb : locListaCuadraAfectadas) {
			CuadraAfectada cadaCuadra = (CuadraAfectada) cadaOb;
			cadaCuadra.toString();
			cadaCuadra.getCuadra().toString();
		}

		return locListaCuadraAfectadas;
	}

	@SuppressWarnings("unchecked")
	public void anularObligacionesFromObra(ArrayList<Obligacion> pListaObligacionesFromObra, java.util.List pListaCuadra) throws java.lang.Exception {

		List<Obligacion> listaObligaciones = new ArrayList<Obligacion>();
		try {
			for(Object cadaOb : pListaCuadra) {
				Cuadra cadaCuadraParam = (Cuadra) cadaOb;
				for(Obligacion cadaObligacion : pListaObligacionesFromObra) { // por cada obligacion...
					DocumentoPlanObra locDocumentoPlanObra = (DocumentoPlanObra) cadaObligacion.getDocumentoEspecializado();
					List<CuadraAfectada> locListadoCuadras = this.getListaCuadrasAfectadasFromDocumentoPFO(locDocumentoPlanObra);
					for(CuadraAfectada cadaCuadraAfectada : locListadoCuadras) { // ...recorro cada cuadra afectada de dicha obligacion...
						if(cadaCuadraParam.getIdCuadra() == cadaCuadraAfectada.getCuadra().getIdCuadra()) { // ... si esa cuadra de esa obligacion se encuentra
																											// dentro del listado que me pasaron...ANULAAAR!
							listaObligaciones.add(cadaObligacion);
						}
					}
				}
			}

			if(listaObligaciones != null && !listaObligaciones.isEmpty()) {
				for(Obligacion cadaObligacion : listaObligaciones) {
					cadaObligacion.anular();
					this.entityManager.merge(cadaObligacion);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
