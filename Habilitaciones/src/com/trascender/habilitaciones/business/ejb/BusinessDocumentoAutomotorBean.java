
package com.trascender.habilitaciones.business.ejb;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;
import ar.trascender.criterio.enums.Posicion;

import com.trascender.catastro.business.interfaces.BusinessRegistroPropiedadLocal;
import com.trascender.catastro.recurso.persistent.RegistroPropietario;
import com.trascender.catastro.recurso.persistent.TituloPropiedad;
import com.trascender.framework.business.interfaces.BusinessParametroLocal;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.AuxIdEntidad;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoAutomotorLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.exception.TransitoException;
import com.trascender.habilitaciones.recurso.filtros.FiltroMarca;
import com.trascender.habilitaciones.recurso.filtros.FiltroModelo;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionAutomotor;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoVehiculo;
import com.trascender.habilitaciones.recurso.filtros.FiltroTituloPropiedadAutomotor;
import com.trascender.habilitaciones.recurso.filtros.FiltroValuacionAcara;
import com.trascender.habilitaciones.recurso.filtros.FiltroVehiculo;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.shps.TransporteVehicular;
import com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.Marca;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;
import com.trascender.habilitaciones.recurso.persistent.transito.TituloPropiedadAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.ValuacionAcara;
import com.trascender.habilitaciones.recurso.persistent.transito.ValuacionAcara.Moneda;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.habilitaciones.util.TransitoUtils;

@Stateless(name = "ejb/BusinessDocumentoAutomotorLocal")
public class BusinessDocumentoAutomotorBean implements BusinessDocumentoAutomotorLocal {

	public static final long serialVersionUID = -8208778200066275481L;

	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre("HAB|Adm. Obligación Automotor");

		Recurso vehiculo = new Recurso();
		vehiculo.setIdRecurso(Vehiculo.serialVersionUID);
		vehiculo.setNombre("Vehículo");
		vehiculo.setAtributosConsultables("Patente", "patente", "Descripción", "descripcion", Tipo.TEXTO_LARGO);
		vehiculo.setClase(Vehiculo.class);
		grupo.getListaRecursos().add(vehiculo);

		Recurso modelo = new Recurso();
		modelo.setIdRecurso(Modelo.serialVersionUID);
		modelo.setNombre("Modelo");
		modelo.setAtributosConsultables("Nombre", "nombre", "Descripción", "descripcion", Tipo.TEXTO_LARGO, "Mínimo", "minimo", Tipo.MONTO);
		modelo.setClase(Modelo.class);
		grupo.getListaRecursos().add(modelo);

		Recurso marca = new Recurso();
		marca.setIdRecurso(Marca.serialVersionUID);
		marca.setNombre("Marca");
		marca.setAtributosConsultables("Nombre", "nombre", "Código", "codigo");
		marca.setClase(Marca.class);
		grupo.getListaRecursos().add(marca);

		Recurso tipoVehiculo = new Recurso();
		tipoVehiculo.setIdRecurso(TipoVehiculo.serialVersionUID);
		tipoVehiculo.setNombre("Tipo Vehículo");
		tipoVehiculo.setAtributosConsultables("Nombre", "nombre", "Descripción", "descripcion", Tipo.TEXTO_LARGO, "Código", "codigo");
		tipoVehiculo.setClase(TipoVehiculo.class);
		grupo.getListaRecursos().add(tipoVehiculo);

		Recurso documentoAutomotor = new Recurso();
		documentoAutomotor.setNombre("Obligación Automotor");
		documentoAutomotor.setIdRecurso(DocumentoAutomotor.serialVersionUID);
		documentoAutomotor.setAtributosConsultables("Contribuyente", "persona", "Vehículo", "documentoEspecializado");
		documentoAutomotor.setClase(DocumentoAutomotor.class);
		grupo.getListaRecursos().add(documentoAutomotor);

		Recurso tituloPropiedadAutomotor = new Recurso();
		tituloPropiedadAutomotor.setNombre("Titulo Propiedad Automotor");
		tituloPropiedadAutomotor.setIdRecurso(TituloPropiedadAutomotor.serialVersionUID);
		tituloPropiedadAutomotor.setAtributosConsultables("Código", "codigo", "Vehiculo", "vehiculo");
		tituloPropiedadAutomotor.setClase(TituloPropiedadAutomotor.class);
		grupo.getListaRecursos().add(tituloPropiedadAutomotor);

		Recurso valuacionAcara = new Recurso();
		valuacionAcara.setNombre("Valuación Acara");
		valuacionAcara.setIdRecurso(ValuacionAcara.serialVersionUID);
		valuacionAcara.setAtributosConsultables("Modelo", "modelo", "Año", "anio", "Valuación", "valor", "Moneda", "moneda");
		valuacionAcara.setClase(ValuacionAcara.class);
		grupo.getListaRecursos().add(valuacionAcara);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	@EJB
	private BusinessParametroLocal businessParametro;

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private BusinessRegistroPropiedadLocal businessRegistroPropietario;

	/**
	 * Agrega un vehículo al sistema
	 * 
	 * @param pVehiculo
	 *            vehiculo que se desea agregar
	 * @return vehiculo agregado (con datos acutizados)
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public Vehiculo addVehiculo(Vehiculo pVehiculo) throws Exception {
		this.validarVehiculo(pVehiculo);
		this.validarTituloPropiedad(pVehiculo.getTituloPropiedad());

		if(pVehiculo.getTituloPropiedad() != null) {
			for(RegistroPropietario cadaRegistro : pVehiculo.getTituloPropiedad().getListaRegistrosPropietarios()) {
				cadaRegistro.setTituloPropiedad(pVehiculo.getTituloPropiedad());
			}
		}

		TrascenderEnverListener.setValoresEnAuditoriaBean(pVehiculo);
		// TrascenderEnverListener.setValoresEnAuditoriaBean(pVehiculo.getTituloPropiedad());

		this.entityManager.persist(pVehiculo);
		pVehiculo.postPersist();
		this.entityManager.flush();

		return pVehiculo;
	}

	/**
	 * Valida que la patente sea valida y que no aya un vehiculo con la misma patente
	 * 
	 * @param pVehiculo
	 * @throws Exception
	 */
	private void validarVehiculo(Vehiculo pVehiculo) throws Exception {

		if(!TransitoUtils.validarPatente(pVehiculo.getPatente())) {
			throw new TransitoException(1);
		}

		if(!TransitoUtils.validarFormatoPatente(pVehiculo.getPatente())) {
			throw new TransitoException(3);
		}

		if(((Long) Criterio.getInstance(this.entityManager, Vehiculo.class).add(Restriccion.IGUAL("patente", pVehiculo.getPatente()))
				.add(Restriccion.NOT(Restriccion.IGUAL("idVehiculo", pVehiculo.getIdVehiculo()))).setProyeccion(Proyeccion.COUNT()).uniqueResult()) > 0) {
			throw new TransitoException(2);

		}
	}

	/**
	 * Actualiza los datos de un vehículo
	 * 
	 * @param pVehiculo
	 *            vehículo que se desea actualizar
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public Vehiculo updateVehiculo(Vehiculo pVehiculo) throws Exception {
		this.validarVehiculo(pVehiculo);
		this.validarTituloPropiedad(pVehiculo.getTituloPropiedad());
		this.validarObligaciones(pVehiculo);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pVehiculo);

		this.entityManager.merge(pVehiculo);
		this.entityManager.flush();
		return pVehiculo;
	}

	/**
	 * Elimina un vehículo que no se encuentre registrado como TransporteVehicular
	 * 
	 * @param pVehiculo
	 *            vehiculo que se desea agregar
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteVehiculo(Vehiculo pVehiculo) throws Exception {
		this.validarAsociacionesVehiculo(pVehiculo);
		pVehiculo = this.entityManager.getReference(Vehiculo.class, pVehiculo.getIdVehiculo());
		this.entityManager.remove(this.entityManager.merge(pVehiculo));
	}

	/**
	 * Valida que un vehiculo no este asociado a ningun TransporteVehicular
	 */
	private void validarAsociacionesVehiculo(Vehiculo pVehiculo) throws Exception {
		if(((Long) Criterio.getInstance(this.entityManager, TransporteVehicular.class).add(Restriccion.IGUAL("vehiculo", pVehiculo)).setProyeccion(Proyeccion.COUNT()).uniqueResult()) > 0) {
			throw new TransitoException(20);
		}

		// Esta validación no es necesaria, si paso la validacion anterior es
		// imposible que esté asociada a una Obligacion.
		// if(((Long)Criterio.getInstance(this.entityManager,
		// DocumentoSHPS.class)
		// .crearAlias("listaTransportesVehiculares", "cadaTransporte")
		// .add(Restriccion.IGUAL("vehiculo", pVehiculo))
		// .setProyeccion(Proyeccion.COUNT())
		// .uniqueResult()) > 0){
		// throw new TransitoException(21);
		// }

	}

	/**
	 * 
	 * @param pPatente
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroVehiculo findListaVehiculos(FiltroVehiculo pFiltro) {
		System.out.println(pFiltro.getModelo());
		System.out.println(pFiltro.getPropietario());
		Criterio locCriterio = Criterio.getInstance(this.entityManager, Vehiculo.class).add(Restriccion.ILIKE("patente", pFiltro.getPatente()))
				.add(Restriccion.IGUAL("modelo", pFiltro.getModelo())).add(Restriccion.IGUAL("tituloPropiedad.fechaInscripcion", pFiltro.getFechaInscripcion()))
				.add(Restriccion.ILIKE("tituloPropiedad.codigo", pFiltro.getCodigo()));
		if(pFiltro.getPropietario() != null) {
			locCriterio.crearAlias("tituloPropiedad.listaRegistrosPropietarios.persona", "cadaPropietario").add(Restriccion.IGUAL("cadaPropietario", pFiltro.getPropietario()));
		}

		AtributoDinamico.addRestriccionesCriterio(locCriterio, Vehiculo.serialVersionUID, "idVehiculo", pFiltro.getListaAtributoDinamico());
		AtributoDinamico.addRestriccionesCriterio(locCriterio, TituloPropiedadAutomotor.serialVersionUID, "tituloPropiedad.idTituloPropiedad", pFiltro.getListaAtributoDinamico2());
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Vehiculo.serialVersionUID, "idVehiculo", pFiltro.getListaBusquedaPorLogs());
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, TituloPropiedadAutomotor.serialVersionUID, "tituloPropiedad.idTituloPropiedad", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		for(Vehiculo cadaVehiculo : pFiltro.getListaResultados()) {
			cadaVehiculo.toString();
			for(AtributoDinamico<?> cadaAtributo : cadaVehiculo.getListaAtributosDinamicos()) {
				cadaAtributo.toString();
			}
		}

		return pFiltro;
	}

	/**
	 * Recupera un vehículo según el número de identificación única
	 * 
	 * @param pId
	 *            número de identificación del vehículo
	 * @return vehículo asociado
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public Vehiculo getVehiculoPorId(long pId) throws Exception {
		Vehiculo locVehiculo = (Vehiculo) Criterio.getInstance(this.entityManager, Vehiculo.class).add(Restriccion.IGUAL("idVehiculo", pId)).uniqueResult();

		if(locVehiculo != null) {
			locVehiculo.toString();
			if(locVehiculo.getTituloPropiedad() != null) {
				locVehiculo.getTituloPropiedad().getListaLogsAuditoria().size();
				for(AtributoDinamico<?> cadaAtributoDinamico : locVehiculo.getTituloPropiedad().getListaAtributosDinamicos()) {
					cadaAtributoDinamico.toString();
				}
			}
			locVehiculo.getListaLogsAuditoria().size();
			for(AtributoDinamico<?> cadaAtributoDinamico : locVehiculo.getListaAtributosDinamicos()) {
				cadaAtributoDinamico.toString();
			}
			if(locVehiculo.getTituloPropiedad() != null) {
				for(RegistroPropietario cadaRegProp : locVehiculo.getTituloPropiedad().getListaRegistrosPropietarios()) {
					cadaRegProp.getPersona().getDomicilio().toString();
					cadaRegProp.getPersona().getDomicilioPostal().toString();
				}
			}
		}

		return locVehiculo;
	}

	public Modelo addModelo(Modelo pModelo) throws Exception {
		this.validarModelo(pModelo);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pModelo);
		pModelo = this.entityManager.merge(pModelo);
		pModelo.postPersist();
		this.entityManager.flush();
		return pModelo;
	}

	/**
	 * Valida la consistencia de los datos y si hay algun otro modelo con el mismo nombre.
	 * 
	 * @param pModelo
	 * @throws Exception
	 */
	private void validarModelo(Modelo pModelo) throws Exception {
		if(pModelo == null) {
			throw new TransitoException(5);
		}

		if(pModelo.getMarca() == null) {
			throw new TransitoException(7);
		}

		if(pModelo.getTipoVehiculo() == null) {
			throw new TransitoException(8);
		}

		if(((Long) Criterio.getInstance(this.entityManager, Modelo.class).add(Restriccion.IGUAL("nombre", pModelo.getNombre()))
				.add(Restriccion.DISTINTO("idModelo", pModelo.getIdModelo())).setProyeccion(Proyeccion.COUNT()).uniqueResult()) > 0) {
			throw new TransitoException(6);
		}
	}

	public Modelo updateModelo(Modelo pModelo) throws Exception {
		this.validarModelo(pModelo);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pModelo);
		pModelo = this.entityManager.merge(pModelo);
		this.entityManager.flush();
		return pModelo;
	}

	public boolean deleteModelo(Modelo pModelo) throws Exception {
		this.validarAsociacionesModelo(pModelo);

		try {
			pModelo = this.entityManager.getReference(Modelo.class, pModelo.getIdModelo());
			this.entityManager.remove(pModelo);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Valida las distintas asociaciones que puede tener un modelo antes de un delete. <br>
	 * 
	 * @param pModelo
	 * @throws Exception
	 */
	private void validarAsociacionesModelo(Modelo pModelo) throws Exception {
		if(((Long) Criterio.getInstance(this.entityManager, Vehiculo.class).add(Restriccion.IGUAL("modelo", pModelo)).setProyeccion(Proyeccion.COUNT()).uniqueResult()) > 0) {
			throw new TransitoException(9);
		}
	}

	public Modelo getModeloById(Long pId) throws Exception {
		Modelo locModelo = Criterio.getInstance(this.entityManager, Modelo.class).add(Restriccion.IGUAL("idModelo", pId)).uniqueResult();

		if(locModelo != null) {
			locModelo.toString();
			locModelo.getMarca().toString();
			locModelo.getTipoVehiculo().toString();
			locModelo.getListaLogsAuditoria().size();
			for(AtributoDinamico<?> cadaAtributoDinamico : locModelo.getListaAtributosDinamicos()) {
				cadaAtributoDinamico.toString();
			}
		}

		return locModelo;
	}

	public FiltroModelo findListaModelo(FiltroModelo pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, Modelo.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
				.add(Restriccion.IGUAL("marca", pFiltro.getMarca())).add(Restriccion.IGUAL("tipoVehiculo", pFiltro.getTipoVehiculo()));

		AtributoDinamico.addRestriccionesCriterio(locCriterio, Modelo.serialVersionUID, "idModelo", pFiltro.getListaAtributoDinamico());
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Modelo.serialVersionUID, "idModelo", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		return pFiltro;
	}

	public List<AuxIdEntidad> findListaAuxIdModeloVehiculo(String cadena) {
		Criterio locCriterio = Criterio.getInstance(entityManager, Modelo.class);

		locCriterio.add(Restriccion.ILIKE("nombre||' '||marca.nombre||' '||tipoVehiculo.nombre", cadena).SIN_PROCESAR_ENTIDADES());

		locCriterio.setProyeccion(Proyeccion.NEW(AuxIdEntidad.class, "idModelo", "nombre||' ['||marca.nombre||', '||tipoVehiculo.nombre||']'").SIN_PROCESAR_ENTIDADES());

		List<AuxIdEntidad> locListaModelosVehiculos = locCriterio.list();

		return locListaModelosVehiculos;
	}

	public Marca addMarca(Marca pMarca) throws Exception {
		this.validarMarca(pMarca);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pMarca);
		pMarca = entityManager.merge(pMarca);
		pMarca.postPersist();
		entityManager.flush();
		return pMarca;
	}

	/**
	 * Valida consistencia de los datos <br>
	 * y si hay entidades con el mismo nombre o codigo.
	 * 
	 * @param pMarca
	 * @throws Exception
	 */
	private void validarMarca(Marca pMarca) throws Exception {
		if(pMarca == null) {
			throw new TransitoException(7);
		}

		if(pMarca.getNombre() == null || pMarca.getCodigo() == null) {
			throw new TransitoException(10);
		}

		if(((Long) Criterio.getInstance(this.entityManager, Marca.class).add(Restriccion.IGUAL("nombre", pMarca.getNombre())).add(Restriccion.DISTINTO("idMarca", pMarca.getIdMarca()))
				.setProyeccion(Proyeccion.COUNT()).uniqueResult()) > 0) {
			throw new TransitoException(12);
		}

		if(((Long) Criterio.getInstance(this.entityManager, Marca.class).add(Restriccion.IGUAL("codigo", pMarca.getCodigo())).add(Restriccion.DISTINTO("idMarca", pMarca.getIdMarca()))
				.setProyeccion(Proyeccion.COUNT()).uniqueResult()) > 0) {
			throw new TransitoException(11);
		}

	}

	public Marca updateMarca(Marca pMarca) throws Exception {
		this.validarMarca(pMarca);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pMarca);
		pMarca = this.entityManager.merge(pMarca);
		this.entityManager.flush();
		return pMarca;
	}

	public boolean deleteMarca(Marca pMarca) throws Exception {
		this.validarAsociacionesMarca(pMarca);

		try {
			pMarca = this.entityManager.getReference(Marca.class, pMarca.getIdMarca());
			this.entityManager.remove(pMarca);
			this.entityManager.flush();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Valida asociaciones para un delete <br>
	 * Valida para: Modelos, ...
	 * 
	 * @param pMarca
	 * @throws Exception
	 */
	private void validarAsociacionesMarca(Marca pMarca) throws Exception {
		if(pMarca == null) {
			throw new TransitoException(7);
		}

		if(((Long) Criterio.getInstance(this.entityManager, Modelo.class).add(Restriccion.IGUAL("marca", pMarca)).setProyeccion(Proyeccion.COUNT()).uniqueResult()) > 0) {
			throw new TransitoException(13);
		}

	}

	public Marca getMarcaById(Long pId) throws Exception {
		Marca locMarca = Criterio.getInstance(this.entityManager, Marca.class).add(Restriccion.IGUAL("idMarca", pId)).uniqueResult();

		if(locMarca != null) {
			locMarca.toString();
			locMarca.getListaLogsAuditoria().size();
			for(AtributoDinamico<?> cadaAtributoDinamico : locMarca.getListaAtributosDinamicos()) {
				cadaAtributoDinamico.toString();
			}
		}

		return locMarca;
	}

	public FiltroMarca findListaMarca(FiltroMarca pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, Marca.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
				.add(Restriccion.IGUAL("codigo", pFiltro.getCodigo()));
		AtributoDinamico.addRestriccionesCriterio(locCriterio, Marca.serialVersionUID, "idMarca", pFiltro.getListaAtributoDinamico());
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Marca.serialVersionUID, "idMarca", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		return pFiltro;
	}

	public TipoVehiculo addTipoVehiculo(TipoVehiculo pTipoVehiculo) throws Exception {
		this.validarTipoVehiculo(pTipoVehiculo);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pTipoVehiculo);

		this.entityManager.persist(pTipoVehiculo);
		pTipoVehiculo.postPersist();
		this.entityManager.flush();

		return pTipoVehiculo;
	}

	/**
	 * Valida integridad de los datos <br>
	 * y que no aya un tipo vehiculo con el mismo codigo o con el mismo nombre.
	 * 
	 * @param pTipoVehiculo
	 * @throws Exception
	 */
	private void validarTipoVehiculo(TipoVehiculo pTipoVehiculo) throws Exception {
		if(pTipoVehiculo == null) {
			throw new TransitoException(8);
		}

		if(pTipoVehiculo.getCodigo() == null || pTipoVehiculo.getNombre() == null) {
			throw new TransitoException(10);
		}

		if(((Long) Criterio.getInstance(this.entityManager, TipoVehiculo.class).add(Restriccion.LIKE("nombre", pTipoVehiculo.getNombre(), false, Posicion.EXACTA))
				.add(Restriccion.DISTINTO("idTipoVehiculo", pTipoVehiculo.getIdTipoVehiculo())).setProyeccion(Proyeccion.COUNT()).uniqueResult()) > 0) {
			throw new TransitoException(14);
		}

		if(((Long) Criterio.getInstance(this.entityManager, TipoVehiculo.class).add(Restriccion.IGUAL("codigo", pTipoVehiculo.getCodigo()))
				.add(Restriccion.DISTINTO("idTipoVehiculo", pTipoVehiculo.getIdTipoVehiculo())).setProyeccion(Proyeccion.COUNT()).uniqueResult()) > 0) {
			throw new TransitoException(15);
		}

	}

	public TipoVehiculo updateTipoVehiculo(TipoVehiculo pTipoVehiculo) throws Exception {
		this.validarTipoVehiculo(pTipoVehiculo);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pTipoVehiculo);
		pTipoVehiculo = this.entityManager.merge(pTipoVehiculo);
		this.entityManager.flush();

		return pTipoVehiculo;
	}

	public boolean deleteTipoVehiculo(TipoVehiculo pTipoVehiculo) throws Exception {
		this.validarAsociacionesTiposVehiculos(pTipoVehiculo);

		try {
			pTipoVehiculo = this.entityManager.getReference(TipoVehiculo.class, pTipoVehiculo.getIdTipoVehiculo());
			this.entityManager.remove(pTipoVehiculo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Valida la asociaciones para un delete <br>
	 * valida asociaciones para: Modelo, ...
	 * 
	 * @param pTipoVehiculo
	 * @throws Exception
	 */
	private void validarAsociacionesTiposVehiculos(TipoVehiculo pTipoVehiculo) throws Exception {
		if(pTipoVehiculo == null) {
			throw new TransitoException(8);
		}

		if(((Long) Criterio.getInstance(this.entityManager, Modelo.class).add(Restriccion.IGUAL("tipoVehiculo", pTipoVehiculo)).setProyeccion(Proyeccion.COUNT()).uniqueResult()) > 0) {
			throw new TransitoException(16);
		}

	}

	public TipoVehiculo getTipoVehiculoById(Long pId) throws Exception {
		TipoVehiculo locTipoVehiculo = Criterio.getInstance(this.entityManager, TipoVehiculo.class).add(Restriccion.IGUAL("idTipoVehiculo", pId)).uniqueResult();

		if(locTipoVehiculo != null) {
			locTipoVehiculo.toString();
			locTipoVehiculo.getListaLogsAuditoria().size();
			for(AtributoDinamico<?> cadaAtributoDinamico : locTipoVehiculo.getListaAtributosDinamicos()) {
				cadaAtributoDinamico.toString();
			}
		}

		return locTipoVehiculo;
	}

	public FiltroTipoVehiculo findListaTipoVehiculo(FiltroTipoVehiculo pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, TipoVehiculo.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
				.add(Restriccion.IGUAL("codigo", pFiltro.getCodigo()));
		AtributoDinamico.addRestriccionesCriterio(locCriterio, TipoVehiculo.serialVersionUID, "idTipoVehiculo", pFiltro.getListaAtributoDinamico());
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, TipoVehiculo.serialVersionUID, "idTipoVehiculo", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		return pFiltro;
	}

	/**
	 * Agrega un documento de Automotor
	 * 
	 * @param pDocumentoAutomotor
	 *            Documento a agregar
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public DocumentoAutomotor addDocumentoAutomotor(DocumentoAutomotor pDocumentoAutomotor) throws Exception {

		TrascenderEnverListener.setValoresEnAuditoriaBean(pDocumentoAutomotor);
		this.entityManager.persist(pDocumentoAutomotor);
		pDocumentoAutomotor.postPersist();
		this.entityManager.flush();

		this.actualizarCantidadVehiculos(pDocumentoAutomotor);
		return pDocumentoAutomotor;
	}

	/**
	 * Actualiza los datos de un documento automotor
	 * 
	 * @param pDocumentoAutomotor
	 *            documento a actualizar
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public DocumentoAutomotor updateDocumentoAutomotor(DocumentoAutomotor pDocumentoAutomotor) throws Exception {

		DocumentoAutomotor locDocumentoViejo = (DocumentoAutomotor) Criterio.getInstance(this.entityManager, DocumentoAutomotor.class)
				.add(Restriccion.IGUAL("idDocHabilitanteEspecializado", pDocumentoAutomotor.getIdDocHabilitanteEspecializado())).uniqueResult();

		try {
			com.trascender.habilitaciones.util.Util.getInstance(this.entityManager).deleteDomicilioExcluyente(locDocumentoViejo, pDocumentoAutomotor);
		} catch(Exception e) {
		}

		if(pDocumentoAutomotor.getDomicilio().getIdDomicilio() == -1) {
			this.entityManager.persist(pDocumentoAutomotor.getDomicilio());
			this.entityManager.merge(pDocumentoAutomotor.getDomicilio());
		}

		this.entityManager.detach(locDocumentoViejo);
		this.entityManager.merge(pDocumentoAutomotor);
		this.entityManager.flush();

		this.actualizarCantidadVehiculos(pDocumentoAutomotor);
		return pDocumentoAutomotor;
	}

	/**
	 * Elimina un documento Automotor logicamente
	 * 
	 * @param pDocumentoAutomotor
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteDocumentoAutomotor(DocumentoAutomotor pDocumentoAutomotor) throws Exception {
		pDocumentoAutomotor.getObligacion().terminar();
		this.entityManager.merge(pDocumentoAutomotor.getObligacion());
	}

	/**
	 * 
	 * @param pObligacion
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public DocumentoAutomotor getDocumentoAutomotor(Obligacion pObligacion) throws Exception {
		DocumentoAutomotor locDocumentoAutomotor = (DocumentoAutomotor) Criterio.getInstance(this.entityManager, DocumentoAutomotor.class)
				.add(Restriccion.IGUAL("obligacion", pObligacion)).uniqueResult();

		locDocumentoAutomotor.toString();
		locDocumentoAutomotor.getObligacion().toString();
		locDocumentoAutomotor.getListaAtributosDinamicos().size();
		locDocumentoAutomotor.getDomicilio().toString();
		locDocumentoAutomotor.getListaLogsAuditoria().size();
		if(locDocumentoAutomotor.getObligacion().getPersona() != null) {
			locDocumentoAutomotor.getObligacion().getPersona().toString();
		}
		return locDocumentoAutomotor;
	}

	/**
	 * @param pTituloPropiedadAutomotor
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void addTituloPropiedadAutomotor(TituloPropiedadAutomotor pTituloPropiedadAutomotor) throws Exception {
		this.entityManager.persist(pTituloPropiedadAutomotor);
	}

	/**
	 * Actualiza los datos del titulo propiedad automotor
	 * 
	 * @param pTituloPropiedadAutomotor
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void updateTituloPropiedadAutomotor(TituloPropiedadAutomotor pTituloPropiedadAutomotor) throws Exception {
		entityManager.merge(pTituloPropiedadAutomotor);
	}

	public FiltroTituloPropiedadAutomotor findListaTitulosPropiedadAutomotor(FiltroTituloPropiedadAutomotor pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, TituloPropiedadAutomotor.class).add(Restriccion.IGUAL("codigo", pFiltro.getCodigo()))
				.add(Restriccion.IGUAL("vehiculo", pFiltro.getVehiculo()));

		pFiltro.procesarYListar(locCriterio);
		return pFiltro;
	}

	public TituloPropiedadAutomotor getTituloPropiedadAutomotorById(Long pId) throws Exception {
		TituloPropiedadAutomotor locTitulo = Criterio.getInstance(this.entityManager, TituloPropiedadAutomotor.class).add(Restriccion.IGUAL("idTituloPropiedad", pId)).uniqueResult();

		if(locTitulo != null) {
			locTitulo.toString();
		}

		return locTitulo;
	}

	/**
	 * Agrega una valuacion Acara al sistema
	 * 
	 * @param pValuacionAcara
	 *            que se desea agregar
	 * @return valuacion Acara agregada (con datos acutizados)
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public ValuacionAcara addValuacionAcara(ValuacionAcara pValuacionAcara) throws Exception {
		TrascenderEnverListener.setValoresEnAuditoriaBean(pValuacionAcara);
		pValuacionAcara = entityManager.merge(pValuacionAcara);
		pValuacionAcara.postPersist();
		return pValuacionAcara;
	}

	/**
	 * Actualiza los datos de una Valuacion Acara
	 * 
	 * @param pValuacionAcara
	 *            valuacion Acara que se desea actualizar
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public ValuacionAcara updateValuacionAcara(ValuacionAcara pValuacionAcara) throws Exception {
		TrascenderEnverListener.setValoresEnAuditoriaBean(pValuacionAcara);
		this.entityManager.merge(pValuacionAcara);
		this.entityManager.flush();
		return pValuacionAcara;
	}

	/**
	 * Elimina una ValuacionAcara
	 * 
	 * @param pValuacionAcara
	 *            Valuacion Acara que se desea eliminar
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteValuacionAcara(ValuacionAcara pValuacionAcara) throws Exception {
		long locLlave = pValuacionAcara.getLlaveUsuarioAuditoria();
		String locComentario = pValuacionAcara.getComentarioAuditoria();
		pValuacionAcara = this.entityManager.getReference(ValuacionAcara.class, pValuacionAcara.getIdValuacionAcara());
		pValuacionAcara.setFechaBaja(new Date());
		pValuacionAcara.setLlaveUsuarioAuditoria(locLlave);
		pValuacionAcara.setComentarioAuditoria(locComentario);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pValuacionAcara);
		this.entityManager.merge(pValuacionAcara);
		this.entityManager.flush();
	}

	/**
	 * 
	 * @param pFiltro
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroValuacionAcara findListaValuacionesAcara(FiltroValuacionAcara pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, ValuacionAcara.class).add(Restriccion.IGUAL("anio", pFiltro.getAnio()))
				.add(Restriccion.IGUAL("valor", pFiltro.getValor())).add(Restriccion.IGUAL("moneda", pFiltro.getMoneda())).add(Restriccion.IGUAL("modelo", pFiltro.getModelo()))
				.setModoDebug(true);

		if(pFiltro.getActiva() != null) {
			if(pFiltro.getActiva().equals(true)) {
				locCriterio.add(Restriccion.NULO("fechaBaja"));
			} else {
				locCriterio.add(Restriccion.NOT(Restriccion.NULO("fechaBaja")));
			}
		}

		AtributoDinamico.addRestriccionesCriterio(locCriterio, ValuacionAcara.serialVersionUID, "idValuacionAcara", pFiltro.getListaAtributoDinamico());
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, ValuacionAcara.serialVersionUID, "idValuacionAcara", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		for(ValuacionAcara cadaValuacionAcara : pFiltro.getListaResultados()) {
			cadaValuacionAcara.toString();
		}

		return pFiltro;
	}

	/**
	 * Recupera una ValuacionAcara según el número de identificación única
	 * 
	 * @param pId
	 *            número de identificación de la ValuacionAcara
	 * @return ValuacionAcara asociada
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public ValuacionAcara getValuacionAcaraById(Long pId) throws Exception {
		ValuacionAcara locValuacionAcara = (ValuacionAcara) Criterio.getInstance(this.entityManager, ValuacionAcara.class).add(Restriccion.IGUAL("idValuacionAcara", pId))
				.uniqueResult();

		if(locValuacionAcara != null) {
			locValuacionAcara.toString();
			locValuacionAcara.getListaAtributosDinamicos().size();
			locValuacionAcara.getListaLogsAuditoria().size();
		}

		return locValuacionAcara;
	}

	public void procesarArchivoValuacionAcara(File pFile) throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(pFile);
		validarDocAcara(doc);
		procesarDocumentoAcara(doc);
	}

	private void procesarDocumentoAcara(Document doc) throws Exception {
		Map<String, Marca> locMapaMarcas = getMapaMarcas();
		Map<String, Modelo> locMapaModelos = getMapaModelos();
		List<ValuacionAcara> locListaValuacionesAcara = getListaValuacionesAcara();
		TipoVehiculo locTipoVehiculoAcara = this.getTipoVehiculoById(0l);

		doc.getDocumentElement().normalize();

		// Marcas
		NodeList listaMarcas = doc.getElementsByTagName("marca");
		for(int indiceMarca = 0; indiceMarca < listaMarcas.getLength(); indiceMarca++) {
			Node nodoMarca = listaMarcas.item(indiceMarca);
			String nombreMarca = nodoMarca.getAttributes().getNamedItem("nombre").getNodeValue();
			Marca locMarca = locMapaMarcas.get(nombreMarca);
			if(locMarca == null) {
				locMarca = new Marca();
				locMarca.setNombre(nombreMarca);
				locMarca.setCodigo(nombreMarca);
				locMarca = this.addMarca(locMarca);
				locMapaMarcas.put(nombreMarca, locMarca);
			}

			// Modelos
			NodeList listaModelos = nodoMarca.getChildNodes();
			for(int indiceModelo = 0; indiceModelo < listaModelos.getLength(); indiceModelo++) {
				Node nodoModelo = listaModelos.item(indiceModelo);
				if(!nodoModelo.getNodeName().equals("modelo"))
					continue;
				String nombreModelo = nodoModelo.getAttributes().getNamedItem("nombre").getNodeValue();
				NodeList listaVersiones = nodoModelo.getChildNodes();
				for(int indiceVersion = 0; indiceVersion < listaVersiones.getLength(); indiceVersion++) {
					Node nodoVersion = listaVersiones.item(indiceVersion);
					if(!nodoVersion.getNodeName().equals("version"))
						continue;
					String nombreVersion = nombreModelo + " - " + nodoVersion.getAttributes().getNamedItem("nombre").getNodeValue();

					Modelo locModelo = locMapaModelos.get(nombreVersion);
					if(locModelo == null) {
						locModelo = new Modelo();
						locModelo.setNombre(nombreVersion);
						locModelo.setMarca(locMarca);
						locModelo.setTipoVehiculo(locTipoVehiculoAcara);
						locModelo = this.addModelo(locModelo);
						locMapaModelos.put(locModelo.getNombre(), locModelo);
					}

					NodeList listaValuacion = nodoVersion.getChildNodes();
					for(int indiceValuacion = 0; indiceValuacion < listaValuacion.getLength(); indiceValuacion++) {
						Node nodoValuacion = listaValuacion.item(indiceValuacion);
						if(!nodoValuacion.getNodeName().equals("valuacion"))
							continue;
						Integer año = Integer.valueOf(nodoValuacion.getAttributes().getNamedItem("año").getNodeValue());
						Moneda moneda = nodoValuacion.getAttributes().getNamedItem("moneda").getNodeValue().equals("pesos") ? Moneda.PESO : Moneda.DOLAR;
						Double valor = Double.valueOf(nodoValuacion.getAttributes().getNamedItem("valor").getNodeValue().replace(",", "."));
						ValuacionAcara locValuacionAcara = new ValuacionAcara();
						locValuacionAcara.setAnio(año);
						locValuacionAcara.setModelo(locModelo);
						locValuacionAcara.setMoneda(moneda);
						locValuacionAcara.setValor(valor);
						this.procesarValuacionAcara(locValuacionAcara, locListaValuacionesAcara);
					}
				}
			}
		}
	}

	private void procesarValuacionAcara(ValuacionAcara pValuacion, List<ValuacionAcara> pLista) throws Exception {
		ValuacionAcara locValuacionExistente = null;
		for(ValuacionAcara cadaValuacion : pLista) {
			if(cadaValuacion.getAnio().equals(pValuacion.getAnio()) && cadaValuacion.getModelo().equals(pValuacion.getModelo())) {
				locValuacionExistente = cadaValuacion;
				break;
			}
		}
		// Si ya existia una anterior para el año y modelo, se da de baja, pues tenemos una mas actualizada.
		if(locValuacionExistente != null) {
			locValuacionExistente.setFechaBaja(new Date());
		}

		this.addValuacionAcara(pValuacion);
	}

	private List<ValuacionAcara> getListaValuacionesAcara() {
		return Criterio.getInstance(entityManager, ValuacionAcara.class).add(Restriccion.NULO("fechaBaja")).list();
	}

	private Map<String, Marca> getMapaMarcas() {
		List<Marca> locListaMarcas = Criterio.getInstance(entityManager, Marca.class).list();
		Map<String, Marca> locMapaMarcas = new HashMap<String, Marca>();
		for(Marca cadaMarca : locListaMarcas) {
			locMapaMarcas.put(cadaMarca.getNombre(), cadaMarca);
		}
		return locMapaMarcas;
	}

	private Map<String, Modelo> getMapaModelos() {
		List<Modelo> locListaModelos = Criterio.getInstance(entityManager, Modelo.class).list();
		Map<String, Modelo> locMapaModelos = new HashMap<String, Modelo>();
		for(Modelo cadaModelo : locListaModelos) {
			locMapaModelos.put(cadaModelo.getNombre(), cadaModelo);
		}
		return locMapaModelos;
	}

	private void validarDocAcara(Document doc) throws Exception {
		if(!doc.getDocumentElement().getNodeName().equals("valuacion_acara")) {
			throw new HabilitacionesException(439);
		}
	}

	public FiltroObligacionAutomotor findListaObligacionesAutomotor(FiltroObligacionAutomotor pFiltro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, Obligacion.class).setModoDebug(true).crearAlias("documentoEspecializado", "locDocEsp")
				.add(Restriccion.JPQL("TYPE (locDocEsp) = ".concat(DocumentoAutomotor.class.getSimpleName())));

		AtributoDinamico.addRestriccionesCriterio(locCriterio, DocumentoAutomotor.serialVersionUID, "idDocHabilitanteEspecializado", "locDocEsp", pFiltro.getListaAtributosDinamicos());

		locCriterio.add(Restriccion.ILIKE("locDocEsp.vehiculo.patente", pFiltro.getPatente())).add(Restriccion.IGUAL("estado", pFiltro.getEstado()))
				.add(Restriccion.IGUAL("locDocEsp.vehiculo", pFiltro.getVehiculo())).add(Restriccion.IGUAL("locDocEsp.numero", pFiltro.getNumeroCuenta()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, DocumentoAutomotor.serialVersionUID, "idDocHabilitanteEspecializado", pFiltro.getListaBusquedaPorLogs(), "locDocEsp");

		if(pFiltro.getPersona() != null) {
			locCriterio.add(Restriccion.IGUAL("persona", pFiltro.getPersona()));
		} else {
			locCriterio.add(Restriccion.EN("persona.id", pFiltro.getListaIdPersonas()));
		}

		pFiltro.procesarYListar(locCriterio);

		for(Obligacion cadaObligacion : pFiltro.getListaResultados()) {
			cadaObligacion.toString();
			cadaObligacion.getPersona().toString();
			cadaObligacion.getDocumentoEspecializado().toString();

		}
		return pFiltro;
	}

	// Actualiza las obligaciones si se cambia el titular del vehiculo
	private void validarObligaciones(Vehiculo pVehiculo) throws HabilitacionesException, Exception {
		// List<DocumentoAutomotor> locListaDocumentos = Criterio.getInstance(this.entityManager, DocumentoAutomotor.class)
		// .add(Restriccion.IGUAL("vehiculo", pVehiculo)).list();
		//
		// if(!locListaDocumentos.isEmpty() && pVehiculo.getTituloPropiedad().getListaRegistrosPropietarios().isEmpty()){
		// throw new HabilitacionesException(389);
		// }
		//
		// for(DocumentoAutomotor cadaDocumento : locListaDocumentos){
		//
		// boolean encontrado = false;
		// for(RegistroPropietario cadaReg : pVehiculo.getTituloPropiedad().getListaRegistrosPropietarios()){
		// if(cadaReg.getPersona().equals(cadaDocumento.getObligacion().getPersona())){
		// encontrado = true;
		// }
		// }
		//
		// if(!encontrado){
		// cadaDocumento.getObligacion().setPersona(pVehiculo.getTituloPropiedad().getListaRegistrosPropietarios().get(0).getPersona());
		// }
		// }
	}

	private void validarTituloPropiedad(TituloPropiedad pTituloPropiedad) throws Exception {
		TituloPropiedad locTituloPropiedad = this.businessRegistroPropietario.getTituloPropiedadPorId(pTituloPropiedad.getIdTituloPropiedad());
		this.entityManager.detach(locTituloPropiedad);
		if(locTituloPropiedad != null) {
			for(RegistroPropietario cadaRegistroPropietario : pTituloPropiedad.getListaRegistrosPropietarios()) {
				cadaRegistroPropietario.setTituloPropiedad(pTituloPropiedad);
			}
		}
	}

	/**
	 * Actualiza la cantidad de vehiculos que una persona posee
	 * 
	 * @param pPersona
	 * @param pPropiedad
	 *            cantidad de vehiculos que agrega
	 * @throws Exception
	 */
	private void actualizarCantidadVehiculos(DocumentoAutomotor pDocumento) {

		Long cantidad = Criterio.getInstance(this.entityManager, Obligacion.class).add(Restriccion.IGUAL("persona", pDocumento.getObligacion().getPersona()))
				.add(ar.trascender.criterio.clases.Grupo.POR("documentoEspecializado.vehiculo.idVehiculo")).setProyeccion(Proyeccion.COUNT()).uniqueResult();

		pDocumento.getObligacion().getPersona().setCantidadVehiculos(cantidad.intValue());

		entityManager.merge(pDocumento.getObligacion().getPersona());
	}
}
