
package com.trascender.catastro.business.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;
import ar.trascender.criterio.enums.Posicion;

import com.trascender.catastro.business.interfaces.BusinessCodigosCatastralesLocal;
import com.trascender.catastro.exception.CatastroException;
import com.trascender.catastro.recurso.filtros.FiltroCategoria;
import com.trascender.catastro.recurso.filtros.FiltroTipoConstruccion;
import com.trascender.catastro.recurso.filtros.FiltroValorBasicoMejora;
import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion;
import com.trascender.catastro.recurso.persistent.RegistroMejora;
import com.trascender.catastro.recurso.persistent.TipoConstruccion;
import com.trascender.catastro.recurso.persistent.ValorBasicoMejora;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;

/**
 * @ejb.bean name="BusinessCodigosCatastrales" display-name="Name for BusinessCodigosCatastrales" description="Description for BusinessCodigosCatastrales"
 *           jndi-name="ejb/BusinessCodigosCatastrales" type="Stateless" view-type="local"
 */
@Stateless(name = "BusinessCodigosCatastralesLocal")
public class BusinessCodigosCatastralesBean implements BusinessCodigosCatastralesLocal {

	// public static final int VALOR_MAX_TABLA_COEFICIENTE_CATEGORIA = 75;
	public static final int VALOR_MAX_TABLA_COEFICIENTE_CATEGORIA = 120;
	static {
		Grupo grupo = new Grupo();
		grupo.setId(BusinessCodigosCatastralesBean.serialVersionUID);
		grupo.setNombre(BusinessCodigosCatastralesBean.NAME);

		Recurso coeficienteDepreciacion = new Recurso();
		Recurso tipoConstruccion = new Recurso();
		Recurso categoria = new Recurso();
		Recurso valorBasicoMejora = new Recurso();

		coeficienteDepreciacion.setIdRecurso(CoeficienteDepreciacion.serialVersionUID);
		coeficienteDepreciacion.setNombre("Coeficiente de Depreciación");
		coeficienteDepreciacion.setAtributosConsultables("Código", "codigo", "Nombre", "nombre", "Tipo Construcción", "tipoConstruccion");
		coeficienteDepreciacion.setClase(CoeficienteDepreciacion.class);

		tipoConstruccion.setIdRecurso(TipoConstruccion.serialVersionUID);
		tipoConstruccion.setNombre("Tipo de Construcción");
		tipoConstruccion.setAtributosConsultables("Nombre", "nombre", "Descripción", "descripcion", Tipo.TEXTO_LARGO);
		tipoConstruccion.setClase(TipoConstruccion.class);

		categoria.setIdRecurso(Categoria.serialVersionUID);
		categoria.setNombre("Categoría");
		categoria.setAtributosConsultables("Código", "codigo", "Nombre", "nombre", "Tipo Construcción", "tipoConstruccion");
		categoria.setClase(Categoria.class);

		valorBasicoMejora.setIdRecurso(ValorBasicoMejora.serialVersionUID);
		valorBasicoMejora.setNombre("Valor Básico por Mejora");
		valorBasicoMejora.setAtributosConsultables("Año vigencia", "anioVigente", "Categoría", "categoria", "Valor", "valor", Tipo.MONTO);
		valorBasicoMejora.setClase(ValorBasicoMejora.class);

		grupo.getListaRecursos().add(coeficienteDepreciacion);
		grupo.getListaRecursos().add(tipoConstruccion);
		grupo.getListaRecursos().add(categoria);
		grupo.getListaRecursos().add(valorBasicoMejora);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6507271357278229711L;
	public static final String NAME = "CAT|Adm. de Códigos Catastrales";

	public BusinessCodigosCatastralesBean() {
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

	/**
	 * Agrega un tipo de construccion
	 * 
	 * @param pTipoConstruccion
	 *            tipo de construccion a agregar
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.TipoConstruccion addTipoConstruccion(com.trascender.catastro.recurso.persistent.TipoConstruccion pTipoConstruccion)
			throws Exception {

		pTipoConstruccion.setActivo(true);

		this.validarTipoConstruccion(pTipoConstruccion);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pTipoConstruccion);

		entityManager.persist(pTipoConstruccion);

		entityManager.flush();

		return pTipoConstruccion;
	}

	/**
	 * Valida que no aya un Tipo Contruccion con el mismo nombre y que no este asociado a alguna categoria
	 * 
	 * @param pTipoConstruccion
	 */
	private void validarTipoConstruccion(TipoConstruccion pTipoConstruccion) throws Exception {
		if(((Long) Criterio.getInstance(this.entityManager, TipoConstruccion.class).add(Restriccion.LIKE("nombre", pTipoConstruccion.getNombre(), false, Posicion.EXACTA))
				.add(Restriccion.IGUAL("activo", true)).add(Restriccion.NOT(Restriccion.IGUAL("idTipoConstruccion", pTipoConstruccion.getIdTipoConstruccion())))
				.setProyeccion(Proyeccion.COUNT()).uniqueResult()) > 0) {
			throw new CatastroException(50);
		}

		if(!pTipoConstruccion.isActivo()) {
			if((Long) Criterio.getInstance(this.entityManager, Categoria.class).add(Restriccion.IGUAL("tipoConstruccion", pTipoConstruccion)).setProyeccion(Proyeccion.COUNT())
					.uniqueResult() > 0) {
				throw new CatastroException(710);
			}
		}
	}

	/**
	 * Actualiza el tipo de construccion
	 * 
	 * @param pTipoConstruccion
	 *            tipo de construccion a actualizar
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.TipoConstruccion updateTipoConstruccion(com.trascender.catastro.recurso.persistent.TipoConstruccion pTipoConstruccion)
			throws Exception {
		this.validarTipoConstruccion(pTipoConstruccion);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pTipoConstruccion);

		this.entityManager.merge(pTipoConstruccion);

		this.entityManager.flush();

		return pTipoConstruccion;

	}

	/**
	 * Recupera un listado de tipos de cosntrucciones
	 * 
	 * @param pIdTipoConstruccion
	 *            n�mero de identificaci�n del tipo de construcci�n
	 * @param pNombre
	 *            nombre del tipo de construcci�n, solo es verificado si el id es nulo
	 * @param estado
	 *            true=ACTIVO false=ELIMINADO
	 * @return List listado con tipos de construcci�n
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public FiltroTipoConstruccion findTiposConstrucciones(FiltroTipoConstruccion filtro) {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, TipoConstruccion.class).add(Restriccion.LIKE("nombre", filtro.getNombre(), false, Posicion.AL_PRINCIPIO))
				.add(Restriccion.IGUAL("idTipoConstruccion", filtro.getIdTipoConstruccion())).add(Restriccion.IGUAL("activo", new Boolean(true)));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, TipoConstruccion.serialVersionUID, "idTipoConstruccion", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	/**
	 * Valida que no ayan otras categorias con un mismo nombre o codigo para cierto tipo de construccion.
	 * 
	 * @param pCategoria
	 * @throws Exception
	 */
	private void validarCategoria(Categoria pCategoria) throws Exception {
		if(!pCategoria.isActivo()) {
			// Valido si tiene relacion con un ValorBasicoMejora
			if((Long) Criterio.getInstance(this.entityManager, ValorBasicoMejora.class).add(Restriccion.IGUAL("categoria", pCategoria)).setProyeccion(Proyeccion.COUNT()).uniqueResult() > 0) {
				throw new CatastroException(721);
			}

			// Valido si tiene relacion con un registro mejora
			if(((Long) Criterio.getInstance(this.entityManager, RegistroMejora.class).add(Restriccion.IGUAL("categoria", pCategoria)).setProyeccion(Proyeccion.COUNT()).uniqueResult()) > 0) {
				throw new CatastroException(722);
			}

		}

		Criterio locCritrio = Criterio.getInstance(this.entityManager, Categoria.class).add(Restriccion.IGUAL("codigo", pCategoria.getCodigo())).add(Restriccion.IGUAL("activo", true))
				.add(Restriccion.IGUAL("tipoConstruccion", pCategoria.getTipoConstruccion()));

		if(pCategoria.getTipoConstruccion() == null) {
			throw new CatastroException(53);
		}
		if(pCategoria.getCodigo() == null) {
			throw new CatastroException(54);
		}

		if(pCategoria.getIdCategoria() != -1) {
			locCritrio.add(Restriccion.NOT(Restriccion.IGUAL("idCategoria", pCategoria.getIdCategoria())));
		}

		for(Object cadaOb : locCritrio.list()) {
			cadaOb.toString();
			Categoria cadaCategoria = (Categoria) cadaOb;

			if(cadaCategoria.getCodigo().equals(pCategoria.getCodigo())) {
				throw new CatastroException(55);
			}

			if(cadaCategoria.getNombre().equalsIgnoreCase(pCategoria.getNombre())) {
				throw new CatastroException(68);
			}
		}

	}

	/**
	 * Agrega una nueva categoria
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.Categoria addCategoria(com.trascender.catastro.recurso.persistent.Categoria pCategoria) throws Exception {

		this.validarCategoria(pCategoria);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pCategoria);

		this.entityManager.persist(pCategoria);
		this.entityManager.flush();
		this.entityManager.refresh(pCategoria);

		return pCategoria;

	}

	/**
	 * Actualiza los datos de una categor�a
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.Categoria updateCategoria(com.trascender.catastro.recurso.persistent.Categoria pCategoria) throws Exception {
		this.validarCategoria(pCategoria);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pCategoria);

		this.entityManager.merge(pCategoria);

		this.entityManager.flush();

		return pCategoria;
	}

	/**
	 * recupera un listado de categorias
	 * 
	 * @param pCodigoCategoria
	 *            c�digo de la categor�a
	 * @param pEstado
	 *            estado de la categor�a
	 * @param pTipoConstruccion
	 *            tipo de cosntrucci�n de la categor�a
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public FiltroCategoria findListaCategorias(FiltroCategoria filtro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, Categoria.class).add(Restriccion.LIKE("nombre", filtro.getNombre(), false, Posicion.AL_PRINCIPIO))
				.add(Restriccion.IGUAL("activo", true)).add(Restriccion.IGUAL("codigo", filtro.getCodigoCategoria()))
				.add(Restriccion.IGUAL("tipoConstruccion", filtro.getTipoConstruccion()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Categoria.serialVersionUID, "idCategoria", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		return filtro;
	}

	/**
	 * Recupera una categoria por id
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.Categoria getCategoriaPorId(long pIdCategoria) {

		Categoria locCategoria = this.entityManager.find(Categoria.class, pIdCategoria);
		locCategoria.toString();
		locCategoria.getTipoConstruccion().toString();
		for(ValorBasicoMejora cadaValorBascoMejora : locCategoria.getListaValoresBasicosMejora()) {
			cadaValorBascoMejora.toString();
		}

		if(locCategoria != null) {
			locCategoria.getListaLogsAuditoria().size();
		}

		return locCategoria;
	}

	public TipoConstruccion getTipoConstruccionPorId(long pIdTipoConstruccion) {
		TipoConstruccion locTipoConstruccion = this.entityManager.find(TipoConstruccion.class, pIdTipoConstruccion);
		locTipoConstruccion.toString();

		if(locTipoConstruccion != null) {
			locTipoConstruccion.getListaLogsAuditoria().size();
		}

		return locTipoConstruccion;
	}

	public ValorBasicoMejora getValorBasicoMejoraPorId(long pIdValorBasicoMejora) {
		ValorBasicoMejora locValorBasicoMejora = this.entityManager.find(ValorBasicoMejora.class, pIdValorBasicoMejora);
		locValorBasicoMejora.toString();

		if(locValorBasicoMejora != null) {
			locValorBasicoMejora.getListaLogsAuditoria().size();
		}

		return locValorBasicoMejora;
	}

	/**
	 * agrega un coeficiente de depreciacion
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion addCoeficienteDepreciacion(
			com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion pCoeficienteDepreciacion) throws Exception {
		this.validarCoeficienteDepreciacion(pCoeficienteDepreciacion);

		this.entityManager.persist(pCoeficienteDepreciacion);
		this.entityManager.flush();
		this.entityManager.refresh(pCoeficienteDepreciacion);

		return pCoeficienteDepreciacion;
	}

	/**
	 * Valida que no aya un coeficiente depreciacion en la misma categoria
	 * 
	 * @param pCoeficienteDepreciacion
	 */
	private void validarCoeficienteDepreciacion(CoeficienteDepreciacion pCoeficienteDepreciacion) {
		// if (((Long) Criterio
		// .getInstance(this.entityManager, CoeficienteDepreciacion.class)
		// .add(Restriccion.IGUAL("categoria",
		// pCoeficienteDepreciacion.getCategoria()))
		// .setProyeccion(Proyeccion.COUNT()).uniqueResult()) > 0) {
		//
		// }
	}

	/**
	 * Actualiza los datos de un coeficiente de depreciaci�n
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion updateCoeficienteDepreciacion(
			com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion pCoeficienteDepreciacion) throws Exception {
		this.entityManager.merge(pCoeficienteDepreciacion);

		this.entityManager.flush();

		return pCoeficienteDepreciacion;
	}

	/**
	 * Elimina un coeficiente de depreciacion de la tabla
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteCoeficienteDepreciacion(com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion pCoeficienteDepreciacion) throws Exception {

		this.entityManager.getReference(CoeficienteDepreciacion.class, pCoeficienteDepreciacion);
		this.entityManager.remove(pCoeficienteDepreciacion);
	}

	/**
	 * REcupera un coeficiente de depreciacion segun los años de antiguedad y la categoria
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion findCoeficienteDepreciacion(Integer pAniosAntiguedad,
			com.trascender.catastro.recurso.persistent.Categoria pCategoria) throws Exception {
		if((pAniosAntiguedad == null) || (pCategoria == null)) {
			throw new CatastroException(58);
		}

		return (CoeficienteDepreciacion) Criterio.getInstance(this.entityManager, CoeficienteDepreciacion.class).add(Restriccion.IGUAL("aniosExistencia", pAniosAntiguedad))
				.add(Restriccion.IGUAL("categoria", pCategoria)).uniqueResult();

	}

	/**
	 * Recupera un listado de coeficientes de depreciacion de los 75 años
	 * 
	 * @param pCategoria
	 *            categoria a la que pertenecen los coeficientes de depreciacion, no puede ser nula
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findListaCoeficientesDepreciacion(com.trascender.catastro.recurso.persistent.Categoria pCategoria) throws Exception {
		// la categoria no puede ser nula para este m�todo
		if(pCategoria == null) {
			throw new CatastroException(65);
		}

		java.util.List<CoeficienteDepreciacion> listadoRetorno = new ArrayList<CoeficienteDepreciacion>();

		List locListadoCoeficienteDependencia = Criterio.getInstance(this.entityManager, CoeficienteDepreciacion.class).add(Restriccion.IGUAL("categoria", pCategoria))
				.add(Orden.ASC("aniosExistencia")).list();

		Integer acumuladorAnioAnterior = 0;
		// agrega todos los coeficientes de depreciaci�n que se encuentran
		// entre medio,
		// tomando como m�ximo el valor de los a�os de existencia de la
		// categoria con un valor m�s alto en
		// ese atributo
		for(Object o : locListadoCoeficienteDependencia) {
			CoeficienteDepreciacion locCoeficienteDepreciacion = (CoeficienteDepreciacion) o;
			while((locCoeficienteDepreciacion.getAniosExistencia() - acumuladorAnioAnterior) > 0) {
				acumuladorAnioAnterior++;

				if(locCoeficienteDepreciacion.getAniosExistencia().equals(acumuladorAnioAnterior)) {

					listadoRetorno.add(locCoeficienteDepreciacion);
				} else {
					CoeficienteDepreciacion locNuevoCoeficienteDepreciacion = new CoeficienteDepreciacion();
					locNuevoCoeficienteDepreciacion.setAniosExistencia(acumuladorAnioAnterior);
					locNuevoCoeficienteDepreciacion.setCategoria(pCategoria);

					listadoRetorno.add(locNuevoCoeficienteDepreciacion);

				}
			}
		}
		// completo los valores hasta el maximo
		for(; acumuladorAnioAnterior <= BusinessCodigosCatastralesBean.VALOR_MAX_TABLA_COEFICIENTE_CATEGORIA; acumuladorAnioAnterior++) {
			CoeficienteDepreciacion locNuevoCoeficienteDepreciacion = new CoeficienteDepreciacion();
			locNuevoCoeficienteDepreciacion.setAniosExistencia(acumuladorAnioAnterior);
			locNuevoCoeficienteDepreciacion.setCategoria(pCategoria);
			listadoRetorno.add(locNuevoCoeficienteDepreciacion);
		}

		return listadoRetorno;

	}

	/**
	 * Agrega un valor basico por mejora
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.ValorBasicoMejora addValorBasicoMejora(com.trascender.catastro.recurso.persistent.ValorBasicoMejora pValorBasicoMejora)
			throws Exception {
		this.validarValirBasicoPorMejora(pValorBasicoMejora);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pValorBasicoMejora);

		this.entityManager.persist(pValorBasicoMejora);
		this.entityManager.flush();
		this.entityManager.refresh(pValorBasicoMejora);

		return pValorBasicoMejora;
	}

	/**
	 * Valida nullidades y que no aya un Valor basico mejora para una misma categoria en un mismo año...
	 * 
	 * @param pValorBasicoMejora
	 * @throws Exception
	 */
	private void validarValirBasicoPorMejora(ValorBasicoMejora pValorBasicoMejora) throws Exception {

		if(pValorBasicoMejora.getAnioVigente() == null) {
			throw new CatastroException(59);
		}

		if(pValorBasicoMejora.getCategoria() == null) {
			throw new CatastroException(60);
		}

		Criterio locCriterio = Criterio.getInstance(this.entityManager, ValorBasicoMejora.class);

		// Restricciones
		locCriterio.add(Restriccion.IGUAL("anioVigente", pValorBasicoMejora.getAnioVigente())).add(Restriccion.IGUAL("categoria", pValorBasicoMejora.getCategoria()))
				.setProyeccion(Proyeccion.COUNT());

		// Si es un update q no se busque a si mismo
		if(pValorBasicoMejora.getIdValorBasicoMejora() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idValorBasicoMejora", pValorBasicoMejora.getIdValorBasicoMejora())));
		}

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new CatastroException(61);
		}

	}

	/**
	 * Actualiza los datos de una valor basico por mejora
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.ValorBasicoMejora updateValorBasicoMejora(com.trascender.catastro.recurso.persistent.ValorBasicoMejora pValorBasicoMejora)
			throws Exception {
		this.validarValirBasicoPorMejora(pValorBasicoMejora);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pValorBasicoMejora);

		this.entityManager.merge(pValorBasicoMejora);

		this.entityManager.flush();

		return pValorBasicoMejora;
	}

	/**
	 * Elimina un valor basico por mejora
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteValorBasicoMejora(com.trascender.catastro.recurso.persistent.ValorBasicoMejora pValorBasicoMejora) throws Exception {

		ValorBasicoMejora locValorBasicoMejora = this.entityManager.find(ValorBasicoMejora.class, pValorBasicoMejora.getIdValorBasicoMejora());

		this.entityManager.remove(locValorBasicoMejora);

	}

	/**
	 * Recupera un listado de valores basicos por mejora
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public FiltroValorBasicoMejora findListaValoresBasicosMejora(FiltroValorBasicoMejora filtro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, ValorBasicoMejora.class).add(Restriccion.IGUAL("categoria", filtro.getCategoria()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, ValorBasicoMejora.serialVersionUID, "idValorBasicoMejora", filtro.getListaBusquedaPorLogs());

		filtro.procesarYListar(locCriterio);

		return filtro;

	}

	/**
	 * Recupera un valor basico por mejora
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.ValorBasicoMejora findValorBasicoMejora(Integer pAnio, com.trascender.catastro.recurso.persistent.Categoria pCategoria)
			throws Exception {

		return (ValorBasicoMejora) Criterio.getInstance(this.entityManager, ValorBasicoMejora.class).add(Restriccion.IGUAL("anioVigente", pAnio))
				.add(Restriccion.IGUAL("categoria", pCategoria)).uniqueResult();
	}

	/**
	 * Almacena un listado de coeficientes de depreciacion
	 * 
	 * @param listado
	 *            listado de coeficientes de depreciacion a almacenar
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@SuppressWarnings("unchecked")
	public void saveListadoCoeficienteDepreciacion(List listado) throws Exception {
		for(Object o : listado) {
			CoeficienteDepreciacion locC = (CoeficienteDepreciacion) o;
			if((locC.getEstadoBueno() > 0f) || (locC.getEstadoRegular() > 0f) || (locC.getEstadoMalo() > 0f)) {
				this.entityManager.merge(locC);
			} else {
				if(locC.getIdCoeficienteDepreciacion() > -1) {
					this.entityManager.remove(locC);
				}
			}
		}

	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public com.trascender.catastro.recurso.persistent.VolanteCatastral updateVolanteCatastral(com.trascender.catastro.recurso.persistent.VolanteCatastral pVolanteCatastral)
			throws java.lang.Exception {

		this.entityManager.merge(pVolanteCatastral);
		this.entityManager.flush();

		return pVolanteCatastral;

	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteVolanteCatastral(com.trascender.catastro.recurso.persistent.VolanteCatastral pVolanteCatastral) throws java.lang.Exception {

		pVolanteCatastral = this.entityManager.merge(pVolanteCatastral);
		this.entityManager.remove(pVolanteCatastral);

	}
}
