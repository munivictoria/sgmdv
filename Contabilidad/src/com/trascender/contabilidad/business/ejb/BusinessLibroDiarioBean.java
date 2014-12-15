
package com.trascender.contabilidad.business.ejb;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.contabilidad.business.interfaces.BusinessLibroDiarioLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.persistent.AsientoContable;
import com.trascender.contabilidad.recurso.persistent.FolioLibroDiario;
import com.trascender.contabilidad.recurso.persistent.LibroDiario;
import com.trascender.contabilidad.recurso.persistent.LineaAsientoContable;
import com.trascender.contabilidad.recurso.persistent.LineaMayor;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuesto;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuestoGastos;
import com.trascender.contabilidad.recurso.persistent.LineaPresupuestoRecursos;
import com.trascender.contabilidad.recurso.persistent.LineaSubdiarioCaja;
import com.trascender.contabilidad.recurso.persistent.Mayor;
import com.trascender.contabilidad.recurso.persistent.Presupuesto;
import com.trascender.contabilidad.recurso.persistent.SubdiarioCaja;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.saic.business.interfaces.BusinessRegistroValuadoLocal;

@Stateless(name = "ejb/BusinessLibroDiarioLocal")
public class BusinessLibroDiarioBean implements BusinessLibroDiarioLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3218492451898076848L;
	private static final String NOMBRE = "CUE|Adm Libro Diario";
	@EJB
	private BusinessRegistroValuadoLocal businessRegistroValuadoLocal;

	@PersistenceContext
	private EntityManager entity;

	static {
		Grupo grupoRecursos = new Grupo();
		grupoRecursos.setId(BusinessLibroDiarioBean.serialVersionUID);
		grupoRecursos.setNombre(BusinessLibroDiarioBean.NOMBRE);

		Recurso libroDiario = new Recurso();
		libroDiario.setNombre("Libro Diario");
		libroDiario.setIdRecurso(LibroDiario.serialVersionUID);
		libroDiario.setClase(LibroDiario.class);
		grupoRecursos.getListaRecursos().add(libroDiario);

		Recurso asientoContable = new Recurso();
		asientoContable.setIdRecurso(AsientoContable.serialVersionUID);
		asientoContable.setNombre("Asiento Contable");
		asientoContable.setClase(AsientoContable.class);
		grupoRecursos.getListaRecursos().add(asientoContable);

		Recurso mayor = new Recurso();
		mayor.setIdRecurso(Mayor.serialVersionUID);
		mayor.setNombre("Mayor");
		mayor.setClase(Mayor.class);
		grupoRecursos.getListaRecursos().add(mayor);

		Recurso folioLibroDiario = new Recurso();
		folioLibroDiario.setIdRecurso(FolioLibroDiario.serialVersionUID);
		folioLibroDiario.setNombre("Folio Libro Diario");
		folioLibroDiario.setClase(FolioLibroDiario.class);
		grupoRecursos.getListaRecursos().add(folioLibroDiario);

		SecurityMgr.getInstance().addGrupo(grupoRecursos);
	}

	public BusinessLibroDiarioBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {

	}

	public void ejbRemove() throws EJBException, RemoteException {

	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {

	}

	public void ejbCreate() throws CreateException {
	}

	public com.trascender.contabilidad.recurso.persistent.LibroDiario addLibroDiario(com.trascender.contabilidad.recurso.persistent.LibroDiario pLibroDiario) throws java.lang.Exception {
		entity.persist(pLibroDiario);
		return pLibroDiario;
	}

	public com.trascender.contabilidad.recurso.persistent.LibroDiario updateLibroDiario(com.trascender.contabilidad.recurso.persistent.LibroDiario pLibroDiario)
			throws java.lang.Exception {
		entity.merge(pLibroDiario);
		return pLibroDiario;
	}

	public com.trascender.contabilidad.recurso.persistent.LibroDiario getLibroDiarioByID(Long pId) throws java.lang.Exception {
		LibroDiario locLibroDiario = entity.find(LibroDiario.class, pId);
		for(FolioLibroDiario folio : locLibroDiario.getFoliosLibroDiario()) {
			folio.toString();
			folio.getAsientosContables().toString();
			if(folio.getLineaMayor() != null) {
				folio.getLineaMayor().toString();
				for(LineaMayor cadaLineaMayor : folio.getLineaMayor()) {
					cadaLineaMayor.toString();
					cadaLineaMayor.getFechaGeneracion().toString();
					cadaLineaMayor.getMayor().toString();
				}
			}
			if(folio.getAsientosContables() != null) {
				for(AsientoContable cadaAsiento : folio.getAsientosContables()) {
					cadaAsiento.getFecha().toString();
					cadaAsiento.getTipo().toString();

				}
			}

		}
		return locLibroDiario;
	}

	public void deleteLibroDiario(com.trascender.contabilidad.recurso.persistent.LibroDiario pLibroDiario) throws java.lang.Exception {
		Integer cantidad = Criterio.getInstance(entity, AsientoContable.class).add(Restriccion.IGUAL("folioLibroDiario.libroDiario", pLibroDiario)).setProyeccion(Proyeccion.COUNT())
				.uniqueResult();
		if(cantidad > 0) {
			throw new TrascenderContabilidadException(220);
		}
		pLibroDiario = entity.merge(pLibroDiario);
		entity.remove(pLibroDiario);
	}

	public List findListaLibroDiario(String pCodigoAutorizacion, String pNumero) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, LibroDiario.class).add(Restriccion.ILIKE("codigoAutorizacion", pCodigoAutorizacion))
				.add(Restriccion.ILIKE("numero", pNumero));
		List<LibroDiario> listaLibroDiario = locCriterio.list();

		for(LibroDiario cadaLibroDiario : listaLibroDiario) {
			cadaLibroDiario.toString();
		}
		return listaLibroDiario;
	}

	public com.trascender.contabilidad.recurso.persistent.FolioLibroDiario getFolioLibroDiarioByID(Long pId) throws java.lang.Exception {
		FolioLibroDiario locFolioLibroDiario = entity.find(FolioLibroDiario.class, pId);
		locFolioLibroDiario.toString();
		return locFolioLibroDiario;
	}

	public List findListaFolioLibroDiario(String pNumero, LibroDiario pLibroDiario) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, FolioLibroDiario.class).add(Restriccion.ILIKE("numero", pNumero)).add(Restriccion.IGUAL("libroDiario", pLibroDiario));
		List listaFolioLibroDiario = locCriterio.list();
		return listaFolioLibroDiario;
	}

	/**
	 * Este metodo trae el subdiario del tipo y la fecha especificada
	 */
	@SuppressWarnings("unchecked")
	public com.trascender.contabilidad.recurso.persistent.AsientoContable traerSubdiarioCaja(Date pFecha, com.trascender.contabilidad.recurso.persistent.SubdiarioCaja.Tipo pTipo)
			throws java.lang.Exception {
		AsientoContable locAsientoContable = new AsientoContable();
		Calendar locCalendar = Calendar.getInstance();
		locCalendar.setTime(pFecha);
		locCalendar.set(Calendar.HOUR_OF_DAY, 0);
		Calendar locCalendarUltimo = Calendar.getInstance();
		locCalendarUltimo.setTime(locCalendar.getTime());
		locCalendarUltimo.set(Calendar.HOUR_OF_DAY, 23);

		List<SubdiarioCaja> locListaSubdiario = Criterio.getInstance(entity, SubdiarioCaja.class).add(Restriccion.MAYOR("fechaCreacion", locCalendar.getTime()))
				.add(Restriccion.MENOR("fechaCreacion", locCalendarUltimo.getTime())).add(Restriccion.IGUAL("tipo", pTipo)).list();
		if(locListaSubdiario.isEmpty()) {
			throw new TrascenderContabilidadException(61);
		}

		for(SubdiarioCaja locSubdiario : locListaSubdiario) {
			if(pTipo == SubdiarioCaja.Tipo.INGRESO) {
				for(LineaSubdiarioCaja locLineaSubdiarioCaja : locSubdiario.getLineasSubdiarioCaja()) {
					LineaAsientoContable locLineaAsientoContable = new LineaAsientoContable();
					locLineaSubdiarioCaja.getCuenta().toString();
					locLineaSubdiarioCaja.getCuenta().getCodigoImputacionCompleto();
					locLineaAsientoContable.setCuenta(locLineaSubdiarioCaja.getCuenta());
					locLineaAsientoContable.getCuenta().getCodigoImputacionCompleto();
					locLineaAsientoContable.setImporteHaber(locLineaSubdiarioCaja.getImporte());
					locLineaAsientoContable.setAsientoContable(locAsientoContable);
					locAsientoContable.getLineasAsientoContable().add(locLineaAsientoContable);
				}
			} else {
				for(LineaSubdiarioCaja locLineaSubdiarioCaja : locSubdiario.getLineasSubdiarioCaja()) {
					LineaAsientoContable locLineaAsientoContable = new LineaAsientoContable();
					locLineaSubdiarioCaja.getCuenta().toString();
					locLineaSubdiarioCaja.getCuenta().getCodigoImputacionCompleto();
					locLineaAsientoContable.setCuenta(locLineaSubdiarioCaja.getCuenta());
					locLineaAsientoContable.getCuenta().getCodigoImputacionCompleto();
					locLineaAsientoContable.setImporteDebe(locLineaSubdiarioCaja.getImporte());
					locLineaAsientoContable.setAsientoContable(locAsientoContable);
					locAsientoContable.getLineasAsientoContable().add(locLineaAsientoContable);
				}
			}
		}
		locAsientoContable.setFecha(new Date());

		return locAsientoContable;
	}

	public com.trascender.contabilidad.recurso.persistent.AsientoContable addAsientoContable(com.trascender.contabilidad.recurso.persistent.AsientoContable pAsientoContable)
			throws java.lang.Exception {
		AsientoContable locAsientoContable = null;

		if(pAsientoContable.getTipo() == null) {
			throw new TrascenderContabilidadException(144);
		}

		try {
			locAsientoContable = Criterio.getInstance(entity, AsientoContable.class).add(Restriccion.IGUAL("numeroAsiento", pAsientoContable.getNumeroAsiento())).detachedUniqueResult();
		} catch(Exception e) {

		}
		if(locAsientoContable != null) {
			throw new TrascenderContabilidadException(142);
		}

		Double importeDebe = 0d;
		Double importeHaber = 0d;
		for(LineaAsientoContable locLinea : pAsientoContable.getLineasAsientoContable()) {
			if(locLinea.getImporteDebe() == null) {
				locLinea.setImporteDebe(0d);
			}
			if(locLinea.getImporteHaber() == null) {
				locLinea.setImporteHaber(0d);
			}
			importeDebe += locLinea.getImporteDebe();
			importeHaber += locLinea.getImporteHaber();
		}
		Long importeDebeLong = Math.round(importeDebe);
		Long importeHaberLong = Math.round(importeHaber);

		if(!importeDebeLong.equals(importeHaberLong)) {
			throw new TrascenderContabilidadException(140);
		}

		this.entity.clear();

		for(LineaAsientoContable locLineaAsientoContable : pAsientoContable.getLineasAsientoContable()) {
			this.modificarPresupuesto(locLineaAsientoContable, pAsientoContable.getTipo());
		}
		entity.persist(pAsientoContable);
		return pAsientoContable;
	}

	public com.trascender.contabilidad.recurso.persistent.AsientoContable updateAsientoContable(com.trascender.contabilidad.recurso.persistent.AsientoContable pAsientoContable)
			throws java.lang.Exception {
		if(pAsientoContable.getTipo() != AsientoContable.Tipo.MANUAL) {
			throw new TrascenderContabilidadException(143);
		}

		Double importeDebe = 0d;
		Double importeHaber = 0d;
		for(LineaAsientoContable locLinea : pAsientoContable.getLineasAsientoContable()) {
			if(locLinea.getImporteDebe() == null) {
				locLinea.setImporteDebe(0d);
			}
			if(locLinea.getImporteHaber() == null) {
				locLinea.setImporteHaber(0d);
			}
			importeDebe += locLinea.getImporteDebe();
			importeHaber += locLinea.getImporteHaber();
		}
		Long importeDebeLong = Math.round(importeDebe);
		Long importeHaberLong = Math.round(importeHaber);

		if(!importeDebeLong.equals(importeHaberLong)) {
			throw new TrascenderContabilidadException(140);
		}

		for(LineaAsientoContable locLineaAsientoContable : pAsientoContable.getLineasAsientoContable()) {
			if(locLineaAsientoContable.getIdLineaAsientoContable() == -1) {
				this.modificarPresupuesto(locLineaAsientoContable, pAsientoContable.getTipo());
			}
		}
		entity.merge(pAsientoContable);
		return pAsientoContable;
	}

	public com.trascender.contabilidad.recurso.persistent.AsientoContable getAsientoContableByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		AsientoContable locAsientoContable = entity.find(AsientoContable.class, pId);

		for(LineaAsientoContable locLinea : locAsientoContable.getLineasAsientoContable()) {
			locLinea.getCuenta().toString();
			locLinea.toString();
		}

		locAsientoContable.getFolioLibroDiario().toString();
		locAsientoContable.getFolioLibroDiario().getLibroDiario().toString();
		locAsientoContable.toString();
		return locAsientoContable;
	}

	public void deleteAsientoContable(com.trascender.contabilidad.recurso.persistent.AsientoContable pAsientoContable) throws java.lang.Exception {
		Integer ultimoNumero = Criterio.getInstance(entity, AsientoContable.class).add(Orden.DESC("numeroAsiento")).setProyeccion(Proyeccion.PROP("numeroAsiento")).setMaxResults(1)
				.uniqueResult();

		if(!pAsientoContable.getNumeroAsiento().equals(ultimoNumero)) {
			throw new TrascenderContabilidadException(141);
		}
		this.entity.clear();
		for(LineaAsientoContable locLineaAsientoContable : pAsientoContable.getLineasAsientoContable()) {
			this.modificarPresupuestoTrasEliminar(locLineaAsientoContable, pAsientoContable.getTipo());
		}
		entity.remove(entity.find(AsientoContable.class, pAsientoContable.getIdAsientoContable()));
	}

	public List findListaAsientoContable(Integer pNumeroAsiento, Date pFechaDesde, Date pFechaHasta, FolioLibroDiario pFolioLibroDiario) throws java.lang.Exception {

		Criterio locCriterio = Criterio.getInstance(entity, AsientoContable.class).add(Restriccion.IGUAL("numeroAsiento", pNumeroAsiento)).add(Restriccion.MAYOR("fecha", pFechaDesde))
				.add(Restriccion.MENOR("fecha", pFechaHasta)).add(Restriccion.IGUAL("folioLibroDiario", pFolioLibroDiario));

		List listaAsientosContables = locCriterio.list();
		return listaAsientosContables;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pMayor
	 * @return
	 * @throws java.lang.Exception
	 */
	public com.trascender.contabilidad.recurso.persistent.Mayor addMayor(com.trascender.contabilidad.recurso.persistent.Mayor pMayor) throws java.lang.Exception {
		entity.persist(pMayor);
		return pMayor;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pMayor
	 * @return
	 * @throws java.lang.Exception
	 */
	public com.trascender.contabilidad.recurso.persistent.Mayor updateMayor(com.trascender.contabilidad.recurso.persistent.Mayor pMayor) throws java.lang.Exception {
		entity.merge(pMayor);
		return pMayor;
	}

	/**
	 * Business method
	 * 
	 * @ejb.interface-method view-type="local"
	 * @param pId
	 * @return
	 * @throws java.lang.Exception
	 */
	public com.trascender.contabilidad.recurso.persistent.Mayor getMayorByID(Long pId) throws java.lang.Exception {
		if(pId == null) {
			throw new TrascenderContabilidadException(298);
		}
		if(pId == -1) {
			throw new TrascenderContabilidadException(299);
		}
		Mayor locMayor = entity.find(Mayor.class, pId);

		for(LineaMayor locLineaMayor : locMayor.getListaLineaMayor()) {
			locLineaMayor.getFechaGeneracion();
			locLineaMayor.getFechaGeneracion().toString();
			locLineaMayor.getFolioLibroDiario();
			locLineaMayor.getSaldo();
			locLineaMayor.getImporteDebe();
			locLineaMayor.getImporteHaber();
			locLineaMayor.toString();

		}

		locMayor.getCuenta().toString();
		locMayor.toString();

		return locMayor;
	}

	public void deleteMayor(com.trascender.contabilidad.recurso.persistent.Mayor pMayor) throws java.lang.Exception {
		entity.merge(pMayor);
		entity.remove(pMayor);
	}

	public List findListaMayor(com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta) throws java.lang.Exception {
		Criterio locCriterio = Criterio.getInstance(entity, Mayor.class).add(Restriccion.IGUAL("cuenta", pCuenta));
		List listaMayor = locCriterio.list();
		for(Object obj : listaMayor) {
			Mayor locMayor = (Mayor) obj;
			locMayor.getCuenta().toString();
			locMayor.toString();
		}
		return listaMayor;
	}

	@SuppressWarnings("unchecked")
	public com.trascender.contabilidad.recurso.persistent.Mayor generarMayor(Calendar pCalendar, com.trascender.contabilidad.recurso.persistent.Cuenta pCuenta)
			throws java.lang.Exception {
		List listaAsientoContable = null;
		Calendar locFechaHasta = Calendar.getInstance();
		locFechaHasta.setTime(pCalendar.getTime());
		locFechaHasta.set(Calendar.DAY_OF_MONTH, pCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Calendar locFechaLineaMayor = Calendar.getInstance();
		// Periodo locPeriodoTransient = SecurityMgr.getInstance().getPeriodoAnual(pCalendar);

		Integer locMes = pCalendar.get(Calendar.MONTH);
		Integer locAnio = pCalendar.get(Calendar.YEAR);

		Double importeDebe = new Double(0);
		Double importeHaber = new Double(0);
		Double importeSaldo = new Double(0);
		Double importeSaldoAnterior = new Double(0);
		FolioLibroDiario locFolioLibroDiario = null;

		// Corrobora si hay un mayor generado para el periodo indicado
		// Periodo locPeriodo = this.businessRegistroValuadoLocal.getPeriodo(locPeriodoTransient.getPeriodicidad(), locPeriodoTransient.getNumeroPeriodo(),
		// locPeriodoTransient.getFechaFin().get(Calendar.YEAR));

		Mayor locMayor = Criterio.getInstance(entity, Mayor.class).add(Restriccion.IGUAL("cuenta", pCuenta)).add(Restriccion.IGUAL("anio", locAnio))
				.add(Restriccion.IGUAL("mes", locMes)).add(Orden.DESC("listaLineaMayor.fechaGeneracion")).uniqueResult();

		pCuenta = entity.merge(pCuenta);
		pCuenta.toString();
		pCuenta.getCodigoImputacionCompleto();

		// Si no hay uno lo crea
		if(locMayor == null) {
			locMayor = new Mayor();
			locMayor.setCuenta(pCuenta);
			locMayor.setAnio(locAnio);
			locMayor.setMes(locMes);
			pCuenta.toString();
		}
		locMayor.toString();
		locMayor.getCuenta().toString();

		// Corrobora si los datos del periodo indicado ya están generados
		for(LineaMayor locLineaMayor : locMayor.getListaLineaMayor()) {
			locLineaMayor.getFolioLibroDiario();
			locLineaMayor.toString();
			locFechaLineaMayor.setTime(locLineaMayor.getFechaGeneracion());
			if(locFechaLineaMayor.get(Calendar.DAY_OF_MONTH) == locFechaHasta.get(Calendar.DAY_OF_MONTH) && locFechaLineaMayor.get(Calendar.MONTH) == locFechaHasta.get(Calendar.MONTH)
					&& locFechaLineaMayor.get(Calendar.YEAR) == locFechaHasta.get(Calendar.YEAR)) {
				throw new TrascenderContabilidadException(170);
			}
		}

		listaAsientoContable = Criterio.getInstance(entity, AsientoContable.class).add(Restriccion.MAYOR("fecha", pCalendar.getTime()))
				.add(Restriccion.MENOR("fecha", locFechaHasta.getTime())).add(Orden.DESC("fecha")).add(Restriccion.IGUAL("lineasAsientoContable.cuenta", pCuenta)).setDistinct(true)
				.list();
		if(listaAsientoContable.isEmpty()) {
			throw new TrascenderContabilidadException(171);
		}

		// Acá se genera el mayor
		for(Object obj : listaAsientoContable) {
			AsientoContable locAsientoContable = (AsientoContable) obj;
			for(LineaAsientoContable locLineaAsientoContable : locAsientoContable.getLineasAsientoContable()) {
				if(locLineaAsientoContable.getCuenta() == locMayor.getCuenta()) {

					if(locLineaAsientoContable.getImporteDebe() != null) {
						importeDebe += locLineaAsientoContable.getImporteDebe();
					}
					if(locLineaAsientoContable.getImporteHaber() != null) {
						importeHaber += locLineaAsientoContable.getImporteHaber();
					}

					List<LineaPresupuesto> locListaLinea;
					try {
						locListaLinea = Criterio.getInstance(entity, LineaPresupuesto.class).add(Restriccion.IGUAL("cuenta", locLineaAsientoContable.getCuenta()))
								.add(Restriccion.IGUAL("presupuesto.anio", locAnio)).add(Restriccion.IGUAL("presupuesto.estado", Presupuesto.Estado.ACTIVO)).setDistinct(true).list();
					} catch(NullPointerException e) {
						throw new TrascenderContabilidadException(147, locLineaAsientoContable.getCuenta(), null);
					}

					LineaPresupuesto locLinea = locListaLinea.get(0);

					if(locLinea instanceof LineaPresupuestoRecursos) {

						if(locLineaAsientoContable.getImporteDebe() > 0) {
							importeDebe += locLineaAsientoContable.getImporteDebe();
						}
						if(locLineaAsientoContable.getImporteHaber() > 0) {
							importeHaber += locLineaAsientoContable.getImporteHaber();
						}
						importeSaldo = importeHaber - importeDebe;

					}
					if(locLinea instanceof LineaPresupuestoGastos) {
						if(locLineaAsientoContable.getImporteDebe() > 0) {
							importeDebe += locLineaAsientoContable.getImporteDebe();
						}
						if(locLineaAsientoContable.getImporteHaber() > 0) {
							importeHaber += locLineaAsientoContable.getImporteHaber();
						}
						importeSaldo = importeDebe - importeHaber;
					}
				}

			}
			locAsientoContable.getFolioLibroDiario().toString();
			locFolioLibroDiario = locAsientoContable.getFolioLibroDiario();

			LineaMayor locLineaMayor = new LineaMayor();

			locLineaMayor.setImporteDebe(importeDebe);
			locLineaMayor.setImporteHaber(importeHaber);
			locLineaMayor.setMayor(locMayor);
			locLineaMayor.setFolioLibroDiario(locFolioLibroDiario);
			locLineaMayor.setFechaGeneracion(locFechaHasta.getTime());
			locLineaMayor.getFechaGeneracion().toString();
			if(!locMayor.getListaLineaMayor().isEmpty()) {
				importeSaldoAnterior = locMayor.getListaLineaMayor().iterator().next().getSaldo().doubleValue();
				importeSaldo += importeSaldoAnterior;
			}
			locLineaMayor.setSaldo(importeSaldo);
			locMayor.getListaLineaMayor().add(locLineaMayor);

			locAsientoContable.getFolioLibroDiario().toString();
			locFolioLibroDiario = locAsientoContable.getFolioLibroDiario();
		}

		return locMayor;
	}

	@SuppressWarnings("unchecked")
	private void modificarPresupuesto(LineaAsientoContable pLineaAsientoContable, AsientoContable.Tipo pTipo) throws Exception {
		// Periodo locPeriodoTransient = SecurityMgr.getInstance().getPeriodo(SecurityMgr.getInstance().getFechaActual(), Periodicidad.ANUAL);
		// Periodo locPeriodo = this.businessRegistroValuadoLocal.getPeriodo(locPeriodoTransient.getPeriodicidad(), locPeriodoTransient.getNumeroPeriodo(),
		// locPeriodoTransient.getFechaFin().get(Calendar.YEAR));

		if(pTipo == AsientoContable.Tipo.COBRO) {
			List<LineaPresupuestoRecursos> locListaLinea = Criterio.getInstance(entity, LineaPresupuestoRecursos.class)
					.add(Restriccion.IGUAL("cuenta", pLineaAsientoContable.getCuenta()))
					// .add(Restriccion.IGUAL("presupuesto.periodo", locPeriodo))
					.add(Restriccion.IGUAL("presupuesto.estado", Presupuesto.Estado.ACTIVO)).setDistinct(true).list();

			if(locListaLinea.size() > 1) {
				throw new TrascenderContabilidadException(145, pLineaAsientoContable.getCuenta(), null);
			}
			if(locListaLinea.size() == 0) {
				throw new TrascenderContabilidadException(146, pLineaAsientoContable.getCuenta(), null);
			}
			LineaPresupuestoRecursos locLineaPresupuestoRecursos = locListaLinea.get(0);
			Double valor = new Double(0);

			if(locLineaPresupuestoRecursos.getMontoRecaudado() != null) {
				valor = locLineaPresupuestoRecursos.getMontoRecaudado();
			}

			if(pLineaAsientoContable.getImporteDebe() > 0) {
				valor -= pLineaAsientoContable.getImporteDebe();
			}
			if(pLineaAsientoContable.getImporteHaber() > 0) {
				valor += pLineaAsientoContable.getImporteHaber();
			}

			locLineaPresupuestoRecursos.setMontoRecaudado(valor);

			this.entity.merge(locLineaPresupuestoRecursos);
		}

		if(pTipo == AsientoContable.Tipo.PAGO) {
			List<LineaPresupuestoGastos> locListaLinea = Criterio.getInstance(entity, LineaPresupuestoGastos.class).add(Restriccion.IGUAL("cuenta", pLineaAsientoContable.getCuenta()))
			// .add(Restriccion.IGUAL("presupuesto.periodo", locPeriodo))
					.add(Restriccion.IGUAL("presupuesto.estado", Presupuesto.Estado.ACTIVO)).setDistinct(true).list();

			if(locListaLinea.size() > 1) {
				throw new TrascenderContabilidadException(145, pLineaAsientoContable.getCuenta(), null);
			}
			if(locListaLinea.size() == 0) {
				throw new TrascenderContabilidadException(146, pLineaAsientoContable.getCuenta(), null);
			}
			LineaPresupuestoGastos locLineaPresupuestoGastos = locListaLinea.get(0);
			Double valor = new Double(0);
			if(locLineaPresupuestoGastos.getMontoPagado() != null) {
				valor = locLineaPresupuestoGastos.getMontoPagado();
			}
			if(pLineaAsientoContable.getImporteDebe() > 0) {
				valor += pLineaAsientoContable.getImporteDebe();
			}
			if(pLineaAsientoContable.getImporteHaber() > 0) {
				valor -= pLineaAsientoContable.getImporteHaber();
			}
			locLineaPresupuestoGastos.setMontoPagado(valor);
			this.entity.merge(locLineaPresupuestoGastos);
		}

		if(pTipo == AsientoContable.Tipo.DEVENGAMIENTO) {
			List<LineaPresupuestoGastos> locListaLinea = Criterio.getInstance(entity, LineaPresupuestoGastos.class).add(Restriccion.IGUAL("cuenta", pLineaAsientoContable.getCuenta()))
			// .add(Restriccion.IGUAL("presupuesto.periodo", locPeriodo))
					.add(Restriccion.IGUAL("presupuesto.estado", Presupuesto.Estado.ACTIVO)).setDistinct(true).list();

			if(locListaLinea.size() > 1) {
				throw new TrascenderContabilidadException(145, pLineaAsientoContable.getCuenta(), null);
			}
			if(locListaLinea.size() == 0) {
				throw new TrascenderContabilidadException(146, pLineaAsientoContable.getCuenta(), null);
			}

			LineaPresupuestoGastos locLineaPresupuestoGastos = locListaLinea.get(0);
			Double valor = new Double(0);

			/*
			 * TODO: FER! esto tenes que terminar para cuando agarres el bicho. las facturas van en el devengado, no en el comprometido. en el comprometido van
			 * las ordenes de compras. tenes que ver que siempre el comprometido termine igual que el devengado
			 * 
			 * if (locLineaPresupuestoGastos.getMontoDevengado() != null){ valor = locLineaPresupuestoGastos.getMontoDevengado(); } if
			 * (pLineaAsientoContable.getImporteDebe() > 0){ valor += pLineaAsientoContable.getImporteDebe(); } if (pLineaAsientoContable.getImporteHaber() >
			 * 0){ valor -= pLineaAsientoContable.getImporteHaber(); }
			 */

			if(locLineaPresupuestoGastos.getMontoComprometido() != null) {
				valor = locLineaPresupuestoGastos.getMontoComprometido();
			}

			if(pLineaAsientoContable.getImporteDebe() > 0) {
				valor += pLineaAsientoContable.getImporteDebe();
			}
			if(pLineaAsientoContable.getImporteHaber() > 0) {
				valor -= pLineaAsientoContable.getImporteHaber();
			}
			locLineaPresupuestoGastos.setMontoComprometido(valor);
			this.entity.merge(locLineaPresupuestoGastos);
		}

		if(pTipo == AsientoContable.Tipo.MANUAL) {
			List<LineaPresupuesto> locListaLinea;
			try {
				locListaLinea = Criterio.getInstance(entity, LineaPresupuesto.class).add(Restriccion.IGUAL("cuenta", pLineaAsientoContable.getCuenta()))
				// .add(Restriccion.IGUAL("presupuesto.periodo", locPeriodo))
						.add(Restriccion.IGUAL("presupuesto.estado", Presupuesto.Estado.ACTIVO)).setDistinct(true).list();
			} catch(NullPointerException e) {
				throw new TrascenderContabilidadException(147, pLineaAsientoContable.getCuenta(), null);
			}

			if(locListaLinea.size() > 1) {
				throw new TrascenderContabilidadException(145, pLineaAsientoContable.getCuenta(), null);
			}
			if(locListaLinea.size() == 0) {
				throw new TrascenderContabilidadException(146, pLineaAsientoContable.getCuenta(), null);
			}
			LineaPresupuesto locLinea = locListaLinea.get(0);

			if(locLinea instanceof LineaPresupuestoRecursos) {
				LineaPresupuestoRecursos locLineaPresupuestoRecursos = (LineaPresupuestoRecursos) locLinea;
				Double valor = new Double(0);
				if(locLineaPresupuestoRecursos.getMontoRecaudado() != null) {
					valor = locLineaPresupuestoRecursos.getMontoRecaudado();
				}

				if(pLineaAsientoContable.getImporteDebe() > 0) {
					valor -= pLineaAsientoContable.getImporteDebe();
				}
				if(pLineaAsientoContable.getImporteHaber() > 0) {
					valor += pLineaAsientoContable.getImporteHaber();
				}
				locLineaPresupuestoRecursos.setMontoRecaudado(valor);
				this.entity.merge(locLineaPresupuestoRecursos);

			}
			if(locLinea instanceof LineaPresupuestoGastos) {
				LineaPresupuestoGastos locLineaPresupuestoGastos = (LineaPresupuestoGastos) locLinea;
				Double valor = new Double(0);
				if(locLineaPresupuestoGastos.getMontoPagado() != null) {
					valor = locLineaPresupuestoGastos.getMontoPagado();
				}
				if(pLineaAsientoContable.getImporteDebe() > 0) {
					valor += pLineaAsientoContable.getImporteDebe();
				}
				if(pLineaAsientoContable.getImporteHaber() > 0) {
					valor -= pLineaAsientoContable.getImporteHaber();
				}
				locLineaPresupuestoGastos.setMontoPagado(valor);
				this.entity.merge(locLineaPresupuestoGastos);
			}
		}

		if(pTipo == AsientoContable.Tipo.PRESUPUESTARIO) {
			List<LineaPresupuestoRecursos> locListaLinea = Criterio.getInstance(entity, LineaPresupuesto.class).add(Restriccion.IGUAL("cuenta", pLineaAsientoContable.getCuenta()))
			// .add(Restriccion.IGUAL("presupuesto.periodo", locPeriodo))
					.add(Restriccion.IGUAL("presupuesto.estado", Presupuesto.Estado.ACTIVO)).setDistinct(true).list();

			if(locListaLinea.size() > 1) {
				throw new TrascenderContabilidadException(145, pLineaAsientoContable.getCuenta(), null);
			}
			if(locListaLinea.size() == 0) {
				throw new TrascenderContabilidadException(146, pLineaAsientoContable.getCuenta(), null);
			}

			LineaPresupuesto locLineaPresupuesto = locListaLinea.get(0);
			Double valor = new Double(0);

			if(locLineaPresupuesto instanceof LineaPresupuestoRecursos) {
				LineaPresupuestoRecursos locLineaPresRecursos = (LineaPresupuestoRecursos) locLineaPresupuesto;
				if(locLineaPresRecursos.getMontoEstimado() != null) {
					valor = locLineaPresRecursos.getMontoEstimado();
				}

				if(pLineaAsientoContable.getImporteDebe() > 0) {
					valor -= pLineaAsientoContable.getImporteDebe();
				}
				if(pLineaAsientoContable.getImporteHaber() > 0) {
					valor += pLineaAsientoContable.getImporteHaber();
				}

				locLineaPresRecursos.setMontoEstimado(valor);

				this.entity.merge(locLineaPresRecursos);
			} else if(locLineaPresupuesto instanceof LineaPresupuestoGastos) {
				LineaPresupuestoGastos locLineaPresGastos = (LineaPresupuestoGastos) locLineaPresupuesto;
				if(locLineaPresGastos.getMontoPresupuestado() != null) {
					valor = locLineaPresGastos.getMontoPresupuestado();
				}

				if(pLineaAsientoContable.getImporteDebe() > 0) {
					valor -= pLineaAsientoContable.getImporteDebe();
				}
				if(pLineaAsientoContable.getImporteHaber() > 0) {
					valor += pLineaAsientoContable.getImporteHaber();
				}

				locLineaPresGastos.setMontoPresupuestado(valor);

				this.entity.merge(locLineaPresGastos);
			}
		}
	}

	private void modificarPresupuestoTrasEliminar(LineaAsientoContable pLineaAsientoContable, AsientoContable.Tipo pTipo) throws Exception {

		// Periodo locPeriodoTransient = SecurityMgr.getInstance().getPeriodo(SecurityMgr.getInstance().getFechaActual(), Periodicidad.ANUAL);
		// Periodo locPeriodo = this.businessRegistroValuadoLocal.getPeriodo(locPeriodoTransient.getPeriodicidad(), locPeriodoTransient.getNumeroPeriodo(),
		// locPeriodoTransient.getFechaFin().get(Calendar.YEAR));

		if(pTipo == AsientoContable.Tipo.COBRO) {
			LineaPresupuestoRecursos locLineaPresupuestoRecursos = Criterio.getInstance(entity, LineaPresupuestoRecursos.class)
					// .add(Restriccion.IGUAL("presupuesto.periodo", locPeriodo))
					.add(Restriccion.IGUAL("cuenta", pLineaAsientoContable.getCuenta())).add(Restriccion.IGUAL("presupuesto.estado", Presupuesto.Estado.ACTIVO)).setDistinct(true)
					.uniqueResult();
			Double valor = locLineaPresupuestoRecursos.getMontoRecaudado();
			if(pLineaAsientoContable.getImporteDebe() > 0) {
				valor += pLineaAsientoContable.getImporteDebe();
			}
			if(pLineaAsientoContable.getImporteHaber() > 0) {
				valor -= pLineaAsientoContable.getImporteHaber();
			}
			locLineaPresupuestoRecursos.setMontoRecaudado(valor);
			this.entity.merge(locLineaPresupuestoRecursos);
		}

		if(pTipo == AsientoContable.Tipo.PAGO) {
			LineaPresupuestoGastos locLineaPresupuestoGastos = Criterio.getInstance(entity, LineaPresupuestoGastos.class)
					.add(Restriccion.IGUAL("cuenta", pLineaAsientoContable.getCuenta()))
					// .add(Restriccion.IGUAL("presupuesto.periodo", locPeriodo))
					.add(Restriccion.IGUAL("presupuesto.estado", Presupuesto.Estado.ACTIVO)).setDistinct(true).uniqueResult();
			Double valor = locLineaPresupuestoGastos.getMontoPagado();
			if(pLineaAsientoContable.getImporteDebe() > 0) {
				valor -= pLineaAsientoContable.getImporteDebe();
			}
			if(pLineaAsientoContable.getImporteHaber() > 0) {
				valor += pLineaAsientoContable.getImporteHaber();
			}
			locLineaPresupuestoGastos.setMontoPagado(valor);
			this.entity.merge(locLineaPresupuestoGastos);
		}

		if(pTipo == AsientoContable.Tipo.DEVENGAMIENTO) {
			LineaPresupuestoGastos locLineaPresupuestoGastos = Criterio.getInstance(entity, LineaPresupuestoGastos.class)
					.add(Restriccion.IGUAL("cuenta", pLineaAsientoContable.getCuenta()))
					// .add(Restriccion.IGUAL("presupuesto.periodo", locPeriodo))
					.add(Restriccion.IGUAL("presupuesto.estado", Presupuesto.Estado.ACTIVO)).setDistinct(true).uniqueResult();
			Double valor = locLineaPresupuestoGastos.getMontoComprometido();
			if(pLineaAsientoContable.getImporteDebe() > 0) {
				valor -= pLineaAsientoContable.getImporteDebe();
			}
			if(pLineaAsientoContable.getImporteHaber() > 0) {
				valor += pLineaAsientoContable.getImporteHaber();
			}
			locLineaPresupuestoGastos.setMontoComprometido(valor);
			this.entity.merge(locLineaPresupuestoGastos);
		}

		if(pTipo == AsientoContable.Tipo.MANUAL) {
			LineaPresupuesto locLinea = Criterio.getInstance(entity, LineaPresupuesto.class).add(Restriccion.IGUAL("cuenta", pLineaAsientoContable.getCuenta()))
			// .add(Restriccion.IGUAL("presupuesto.periodo", locPeriodo))
					.add(Restriccion.IGUAL("presupuesto.estado", Presupuesto.Estado.ACTIVO)).setDistinct(true).uniqueResult();

			if(locLinea instanceof LineaPresupuestoRecursos) {
				LineaPresupuestoRecursos locLineaPresupuestoRecursos = (LineaPresupuestoRecursos) locLinea;
				Double valor = locLineaPresupuestoRecursos.getMontoRecaudado();
				if(pLineaAsientoContable.getImporteDebe() > 0) {
					valor += pLineaAsientoContable.getImporteDebe();
				}
				if(pLineaAsientoContable.getImporteHaber() > 0) {
					valor -= pLineaAsientoContable.getImporteHaber();
				}
				locLineaPresupuestoRecursos.setMontoRecaudado(valor);
				this.entity.merge(locLineaPresupuestoRecursos);
			}
			if(locLinea instanceof LineaPresupuestoGastos) {
				LineaPresupuestoGastos locLineaPresupuestoGastos = (LineaPresupuestoGastos) locLinea;
				Double valor = locLineaPresupuestoGastos.getMontoPagado();
				if(pLineaAsientoContable.getImporteDebe() > 0) {
					valor -= pLineaAsientoContable.getImporteDebe();
				}
				if(pLineaAsientoContable.getImporteHaber() > 0) {
					valor += pLineaAsientoContable.getImporteHaber();
				}
				locLineaPresupuestoGastos.setMontoPagado(valor);
				this.entity.merge(locLineaPresupuestoGastos);
			}
		}
	}

}
