
package com.trascender.saic.business.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.nfunk.jep.JEP;
import org.nfunk.jep.Variable;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.framework.business.interfaces.BusinessMunicipalidadLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.filtros.FiltroDiaFeriado;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.ConceptoPorMora;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Exencion.Estado;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoParametro;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroAlicuota;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroVencimiento;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroVencimiento.TipoAtributoVencimiento;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.habilitaciones.recurso.persistent.TipoVencimiento;
import com.trascender.habilitaciones.recurso.persistent.VariableFormulaCompuesta;
import com.trascender.habilitaciones.recurso.persistent.VariableFormulaSimple;
import com.trascender.habilitaciones.recurso.persistent.enums.TipoParametroInteres;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.habilitaciones.util.MotorFormulas;
import com.trascender.saic.business.interfaces.BusinessLiquidacionTasaLocal;
import com.trascender.saic.business.interfaces.BusinessReLiquidacionLocal;
import com.trascender.saic.exception.SaicException;
import com.trascender.saic.recurso.persistent.AlicuotaLiquidada;
import com.trascender.saic.recurso.persistent.DocGeneradorDeuda;
import com.trascender.saic.recurso.persistent.ExencionRegistroDeuda;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.LogLiquidacion;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacion;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacionFormula;
import com.trascender.saic.recurso.persistent.ParametroValuado;
import com.trascender.saic.recurso.persistent.ParametroValuadoAlicuota;
import com.trascender.saic.recurso.persistent.ParametroValuadoDouble;
import com.trascender.saic.recurso.persistent.ParametroValuadoString;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.TipoDeuda;
import com.trascender.saic.recurso.persistent.RegistroExencionRegistroDeuda;
import com.trascender.saic.recurso.persistent.Reliquidacion;
import com.trascender.saic.recurso.persistent.Tasa;
import com.trascender.saic.recurso.persistent.TasaTGI;
import com.trascender.saic.recurso.persistent.Vencimiento;
import com.trascender.saic.recurso.persistent.refinanciacion.CuotaRefinanciacion;
import com.trascender.saic.util.ValorBasicoTasaFuncion;
import com.trascender.saic.util.ValorModificadorFuncion;

/**
 * @ejb.bean name="BusinessReLiquidacion" display-name="Name for BusinessReLiquidacion" description="Description for BusinessReLiquidacion"
 *           jndi-name="ejb/BusinessReLiquidacion" type="Stateless" view-type="local"
 */
@Stateless(name = "BusinessReLiquidacionLocal")
public class BusinessReLiquidacionBean implements BusinessReLiquidacionLocal {
	@SuppressWarnings("unused")
	private static final Integer CANTIDAD_MILISEGUNDOS_DIARIOS = 86400000;

	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;

	@EJB
	private BusinessLiquidacionTasaLocal businessLiquidacionTasaLocal;
	@EJB
	private BusinessMunicipalidadLocal businessMunicipalidadLocal;

	// private LinkedHashMap<Integer, ParametroValuado> listaParametrosValuados;
	// private LinkedHashMap<Integer, Vencimiento> listaVencimientos;
	// private LinkedHashMap<Integer, ModificadorLiquidacion> listaModificadoresLiquidacion;
	private LinkedHashMap<Integer, AlicuotaLiquidada> listaAlicuotasLiquidadas;

	// Map utilizado para agregar o actualizar Exenciones de Registros de Deuda al finalizar un proceso de liquidación.
	private LinkedHashMap<Integer, ExencionRegistroDeuda> listaExencionesRegistrosDeuda = new LinkedHashMap<Integer, ExencionRegistroDeuda>();

	private List<RegistroDeuda> listaLiquidacionesTasaAnteriores;
	private Date fechaReLiquidacion;
	private Date fechaNuevoVencimiento;
	private Date fechaVencimientoOriginal;

	private boolean guardarReliquidacion = true;
	private boolean isListaParametrosCargada = false;

	@SuppressWarnings("unchecked")
	private List listaDiasFeriados;

	private JEP jep;
	
	private long llave = -1;

	/**
	 * 
	 */
	private static final long serialVersionUID = 590336802115500415L;
	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre("SAI|Adm. Reliquidaciones");

		Recurso reliquidacion = new Recurso();
		reliquidacion.setIdRecurso(Reliquidacion.serialVersionUID);
		reliquidacion.setNombre("Reliquidaciones");
		reliquidacion.setClase(Reliquidacion.class);
		grupo.getListaRecursos().add(reliquidacion);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	public BusinessReLiquidacionBean() {

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
	 * Forza el recupero de los datos para una loquidacion
	 * 
	 * @param pLiquidacionTasa
	 * @throws Exception
	 */
	
	private void refrescarLiquidacion(LiquidacionTasa pLiquidacionTasa) throws Exception {
		pLiquidacionTasa.toString();
		pLiquidacionTasa.getListaModificadoresLiquidacion().toString();
		pLiquidacionTasa.getListaParametrosValuados().toString();
		pLiquidacionTasa.getListaVencimientos().toString();
		pLiquidacionTasa.getTipoTasa().toString();
		pLiquidacionTasa.getTipoTasa().getListaVencimientos().toString();
		pLiquidacionTasa.getDocGeneradorDeuda().toString();
		pLiquidacionTasa.getDocGeneradorDeuda().getObligacion().toString();

		if(pLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getListaRegistrosExencion() != null) {
			pLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getListaRegistrosExencion().size();
		}
	}

	/**
	 * Reliquida una obligacion sin fechas de vencimiento
	 * 
	 * @param pLiquidacionTasa
	 * @param pFechaReLiquidacion
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Reliquidacion> reliquidarObligacion(LiquidacionTasa pLiquidacionTasa, java.util.Date pFechaReLiquidacion, List<String> pListaNombresNuevosParametrosValuados,
			List<ParametroValuadoAlicuota> pListaNuevosParametrosAlicuotas, Map<String, Object> pMapaValoresFijos,
			com.trascender.framework.recurso.persistent.DigestoMunicipal pDigestoMunicipal, boolean pAplicarIntereses, boolean pGuardarReliquidacion) throws Exception {
		System.out.println("Fecha de vencimiento nueva");
		System.out.println(pFechaReLiquidacion);
		if(pLiquidacionTasa.getEstado().equals(EstadoRegistroDeuda.RELIQUIDADA)) {
			throw new SaicException(72);
		}

		if(pLiquidacionTasa.getEstado().toString().contains("Pagada")) {
			throw new SaicException(73);
		}

		List<Reliquidacion> locListaReliquidaciones = new ArrayList<Reliquidacion>();

		this.isListaParametrosCargada = false;

		// this.listaLiquidacionesTasaAnteriores = this.getListaRegistrosDeudaAsociados(pLiquidacionTasa, null);
		listaLiquidacionesTasaAnteriores = new ArrayList<RegistroDeuda>();
		listaLiquidacionesTasaAnteriores.add(this.levantarDeuda(pLiquidacionTasa));

		for(Object cadaObject : this.listaLiquidacionesTasaAnteriores) {
			System.out.println("Entro al for de la listaliq");
			pLiquidacionTasa = (LiquidacionTasa) cadaObject;
			pLiquidacionTasa.toString();
			pLiquidacionTasa = this.entityManager.merge(pLiquidacionTasa);

			pLiquidacionTasa.getListaModificadoresLiquidacion().toString();
			for(ModificadorLiquidacion cadaModificador : pLiquidacionTasa.getListaModificadoresLiquidacion()) {
				cadaModificador.toString();
			}

			pLiquidacionTasa.getListaAlicuotasLiquidadas().size();
			pLiquidacionTasa.getListaParametrosValuados().toString();
			pLiquidacionTasa.getListaVencimientos().toString();
			pLiquidacionTasa.getTipoTasa().toString();
			pLiquidacionTasa.getTipoTasa().getListaVencimientos().toString();
			pLiquidacionTasa.getTipoTasa().getListaParametros().size();
			pLiquidacionTasa.getTipoTasa().getListaParamatrosAlicuota().size();
			pLiquidacionTasa.getDocGeneradorDeuda().toString();
			pLiquidacionTasa.getDocGeneradorDeuda().getObligacion().toString();
			if(pLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getListaRegistrosExencion() != null) {
				pLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getListaRegistrosExencion().toString();
			}
			this.entityManager.detach(ParametroValuado.class);
			for(ParametroValuado cadaParametro : pLiquidacionTasa.getListaParametrosValuados()) {
				this.entityManager.detach(cadaParametro);
			}
			
			this.entityManager.detach(pLiquidacionTasa);

			System.out.println("Preparando lista de Exenciones Vigentes...");
			this.prepararListaExencionesRegistrosDeuda();

			System.out.println("Fuerzo el recupero de datos");
			this.guardarReliquidacion = pGuardarReliquidacion;

			if(pFechaReLiquidacion != null) {
				this.fechaReLiquidacion = pFechaReLiquidacion;
				// Vence el mismo dia que se reliquida
				this.fechaNuevoVencimiento = pFechaReLiquidacion;
				// this.validarPreCondicionesReliquidacion(pLiquidacionTasa, pFechaReLiquidacion);
			}
			this.fechaVencimientoOriginal = (Date) this.entityManager.createNativeQuery("SELECT fecha FROM p_fecha_vencimiento_original(:id_deuda)")
					.setParameter("id_deuda", pLiquidacionTasa.getIdRegistroDeuda()).getSingleResult();

			// Inicializo listas a utilizar
			// this.listaParametrosValuados = new LinkedHashMap<Integer, ParametroValuado>();
			// this.listaVencimientos = new LinkedHashMap<Integer, Vencimiento>();
			// this.listaModificadoresLiquidacion = new LinkedHashMap<Integer, ModificadorLiquidacion>();
			this.listaAlicuotasLiquidadas = new LinkedHashMap<Integer, AlicuotaLiquidada>();

			List<ParametroValuado> locListadoParametrosValuados = this.getListaParametrosValuados(pLiquidacionTasa, pListaNombresNuevosParametrosValuados, pMapaValoresFijos);
			List<AlicuotaLiquidada> locListaAlicuotasLiquidadas = this.getListaAlicuotasLiquidadas(pLiquidacionTasa, pListaNuevosParametrosAlicuotas);

			pLiquidacionTasa = entityManager.merge(pLiquidacionTasa);
			Reliquidacion locReliquidacion = this.reliquidarObligacion(pLiquidacionTasa, locListadoParametrosValuados, locListaAlicuotasLiquidadas, pAplicarIntereses);

			if(pDigestoMunicipal != null && pDigestoMunicipal.getIdDigestoMunicipal() != -1) {
				locReliquidacion.setDigestoMunicipal(pDigestoMunicipal);
			} else {
				locReliquidacion.setDigestoMunicipal(null);
			}
			LiquidacionTasa locLiquidacionTasa = locReliquidacion.getLiquidacionTasa();
			
			pLiquidacionTasa = entityManager.merge(pLiquidacionTasa);
			// pLiquidacionTasa
			// Se mueve la creacion de los nuevos vencimientos al metodo newLiquidacionTasa()
			// for(Vencimiento locNuevoVencimiento : this.crearNuevosVencimientos(pLiquidacionTasa,this.fechaNuevoVencimiento)){
			// locNuevoVencimiento.setValor(locLiquidacionTasa.getMonto()); //Setearle los montos correspondientes dentro del metodo, para recrear los
			// originales.
			//
			// // if(!this.listaVencimientos.isEmpty() && this.listaVencimientos.containsKey(locNuevoVencimiento.hashCode())){
			// // locNuevoVencimiento = this.listaVencimientos.get(locNuevoVencimiento.hashCode());
			// // }
			//
			// locLiquidacionTasa.getListaVencimientos().add(locNuevoVencimiento);
			// this.entityManager.merge(locNuevoVencimiento);
			// }
			System.out.println("Salio del for de crearVencimientos");
			for(ParametroValuado cadaParametroValuado : locLiquidacionTasa.getListaParametrosValuados()) {

				// if(!this.listaParametrosValuados.isEmpty() && this.listaParametrosValuados.containsKey(cadaParametroValuado.hashCode())){
				// cadaParametroValuado = this.listaParametrosValuados.get(cadaParametroValuado.hashCode());
				// }
				if(!locLiquidacionTasa.getListaParametrosValuados().contains(cadaParametroValuado)) {
					locLiquidacionTasa.getListaParametrosValuados().add(cadaParametroValuado);
				}
				cadaParametroValuado.setLiquidacionTasa(pLiquidacionTasa);
				this.entityManager.merge(cadaParametroValuado);
			}

			for(AlicuotaLiquidada cadaAlicuotaLiquidada : locLiquidacionTasa.getListaAlicuotasLiquidadas()) {
				if(cadaAlicuotaLiquidada.getIdAlicuotaLiquidada() == -1) {
					this.entityManager.persist(cadaAlicuotaLiquidada);
				}
			}

			Iterator<ModificadorLiquidacion> locListModificadores = locLiquidacionTasa.getListaModificadoresLiquidacion().iterator();
			System.out.println("Cantidad de Modificadores: " + locLiquidacionTasa.getListaModificadoresLiquidacion().size());
			int locIndex = 1;
			while(locListModificadores.hasNext()) {
				ModificadorLiquidacion cadaModificadorLiquidacion = locListModificadores.next();
				// if(!this.listaModificadoresLiquidacion.isEmpty() && this.listaModificadoresLiquidacion.containsKey(cadaModificadorLiquidacion.hashCode())){
				// cadaModificadorLiquidacion = this.listaModificadoresLiquidacion.get(cadaModificadorLiquidacion.hashCode());
				// }
				System.out.println("Mod " + (locIndex) + " [cadaModificadorLiquidacion]" + "*******************************************");
				// if(! (pLiquidacionTasa.isVencida()
				// && cadaModificadorLiquidacion.getNombre().trim().equalsIgnoreCase("Descuento Buen Contribuyente"))){
				// if(!locLiquidacionTasa.getListaModificadoresLiquidacion().contains(cadaModificadorLiquidacion)){
				// locLiquidacionTasa.getListaModificadoresLiquidacion().add(cadaModificadorLiquidacion);
				// cadaModificadorLiquidacion.setLiquidacionTasa(pLiquidacionTasa);
				// System.out.println("MODIFICADOR AGREGADO: "+cadaModificadorLiquidacion.getNombre() +
				// " Valor: "+cadaModificadorLiquidacion.getValorModificador());
				// }else{
				// if(cadaModificadorLiquidacion.getNombre().trim().equalsIgnoreCase("Descuento Buen Contribuyente")){
				// if(pLiquidacionTasa.getEstado().equals(EstadoRegistroDeuda.VENCIDA)){
				// locListModificadores.remove();
				// System.out.println("Se saco el modificador: "+ cadaModificadorLiquidacion.getNombre() + " por que la liq estaba vencida.");
				// }
				// }
				// }
				//
				// }else{
				// System.out.println("NOT (liq isVencida? "+pLiquidacionTasa.isVencida(Calendar.getInstance().getTime())+") "
				// +"es buen contribuyente? "+ cadaModificadorLiquidacion.getNombre().trim().equalsIgnoreCase("Descuento Buen Contribuyente")+")" );
				// if(cadaModificadorLiquidacion.getNombre().trim().equalsIgnoreCase("Descuento Buen Contribuyente")){
				// if(pLiquidacionTasa.getEstado().equals(EstadoRegistroDeuda.VENCIDA)){
				// locListModificadores.remove();
				// System.out.println("Se saco el modificador: "+ cadaModificadorLiquidacion.getNombre() + " por que la liq estaba vencida.");
				// }
				// }
				// }
				this.entityManager.merge(cadaModificadorLiquidacion);
				System.out.println("Fin mod " + (locIndex++) + "*******************************");
			}

			System.out.println("Salio del for de modificadores liq - Cantidad de modificadores despues de la liquidacion: "
					+ locLiquidacionTasa.getListaModificadoresLiquidacion().size() + locLiquidacionTasa.getListaModificadoresLiquidacion().toString());

			// una vez que reliquido cancelo la liquidación tasa
			// this.entityManager.refresh(locReliquidacion);
			// acaa
			pLiquidacionTasa.setRegistroCancelacion(locReliquidacion);
			System.out.println("id registro cancelacion = " + locReliquidacion.getIdRegistroCancelacion());

			/*
			 * Al Final de la reliquidacion se busca si la obligacion posee alguna exencion para el periodo a liquidar de ser asi se liquida solo el porcentaje
			 * especificado si dicho porcentaje es 100 el monto de la liquidacion es 0
			 */
			// if(pLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getListaRegistrosExencion() != null &&
			// !pLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getListaRegistrosExencion().isEmpty()){
			// /*
			// * Al Final de la liquidacion se busca si la obligacion posee alguna exencion para el periodo a liquidar
			// * de ser asi se liquida solo el porcentaje especificado si dicho porcentaje es 100 el monto de la liquidacion es 0
			// */
			// //if(pObligacion.getListaRegistrosExencion() != null && !pObligacion.getListaRegistrosExencion().isEmpty()){
			// boolean locIsExentoPeriodo = false;
			// RegistroExencionObligacion locRegistroExencion = null;
			// Iterator<RegistroExencionObligacion> locIterador =
			// pLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getListaRegistrosExencion().iterator();
			// while(locIterador.hasNext() && !locIsExentoPeriodo){
			// locRegistroExencion = locIterador.next();
			// if(locRegistroExencion.getExencionObligacion().getEstado().equals(Estado.VIGENTE)){
			// //Se controla si el periodo es igual o si se trata de una exencion anual y el año del periodo es igual al del parametro
			// // if(locRegistroExencion.getExencionObligacion().getCuotaLiquidacion().equals(pLiquidacionTasa.getCuotaLiquidacion())
			// // || (locRegistroExencion.getExencionObligacion().getTipoExencion().equals(Tipo.ANIO)
			// // && locRegistroExencion.getExencionObligacion().getCuotaLiquidacion().getPeriodo().getFechaInicio().get(Calendar.YEAR)
			// // == pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio().get(Calendar.YEAR)) ){
			// // locIsExentoPeriodo = true;
			// // }
			// }
			// }
			// System.out.println("Es periodo exento " + locIsExentoPeriodo);
			//
			// if(locIsExentoPeriodo && locRegistroExencion != null){
			// ExencionRegistroDeuda locExencionRegistroDeuda = new ExencionRegistroDeuda();
			// if(locRegistroExencion.getExencionObligacion().getDigestoMunicipal() != null){
			// locExencionRegistroDeuda.setDigestoMunicipal(locRegistroExencion.getExencionObligacion().getDigestoMunicipal());
			// }
			// locExencionRegistroDeuda.setNombre("Exención creada Automáticamente en Fecha ["+Util.getStringFechaYHora(Calendar.getInstance().getTime())
			// +"] para la Tasa ["+locLiquidacionTasa.getTipoTasa().getNombre()+"]");
			// locExencionRegistroDeuda.setMotivo("Exención de Registros de Deuda creada Automáticamente duránte un proceso de Reliquidación.");
			// locExencionRegistroDeuda.setPorcentaje(locRegistroExencion.getExencionObligacion().getPorcentaje());
			// locExencionRegistroDeuda.setTipoExencion(locRegistroExencion.getExencionObligacion().getTipoExencion());
			// locExencionRegistroDeuda.setCuotaLiquidacion(locLiquidacionTasa.getCuotaLiquidacion());
			// // locExencionRegistroDeuda.setPeriodicidadCuotas(locLiquidacionTasa.getTipoTasa().getPeriodicidadCuotas());
			//
			// try{
			// if(locExencionRegistroDeuda != null && this.listaExencionesRegistrosDeuda.containsKey(locExencionRegistroDeuda.hashCode())){
			// locExencionRegistroDeuda = this.listaExencionesRegistrosDeuda.get(locExencionRegistroDeuda.hashCode());
			// }
			// }
			// catch (Exception e) {
			// }
			//
			// RegistroExencionRegistroDeuda locRegistroExencionRegistroDeuda = new RegistroExencionRegistroDeuda();
			// locRegistroExencionRegistroDeuda.setExencionRegistroDeuda(locExencionRegistroDeuda);
			// locRegistroExencionRegistroDeuda.setRegistroDeuda(locLiquidacionTasa);
			// //Coloco la misma nota de referencia que tenía la obligación
			// locRegistroExencionRegistroDeuda.setReferenciaNotaHCD(locRegistroExencion.getReferenciaNotaHCD());
			//
			// locExencionRegistroDeuda.addRegistroDeudaExento(locRegistroExencionRegistroDeuda);
			//
			// this.listaExencionesRegistrosDeuda.put(locExencionRegistroDeuda.hashCode(), locExencionRegistroDeuda);
			// }
			// }
			System.out.println("Paso lo de las exenciones");

			// estado que toma la liquidación tasa reliquidada
			pLiquidacionTasa.setEstado(EstadoRegistroDeuda.RELIQUIDADA);
			locLiquidacionTasa.recalcularMonto();
			// Actualizo la lista de exenciones de registros de deuda solo si no son reliquidaciones temporales
			locListaReliquidaciones.add(locReliquidacion);
			if (pGuardarReliquidacion) {
				this.businessLiquidacionTasaLocal.generarLogLiquidacion(locLiquidacionTasa, SecurityMgr.getInstance().getUsuario(llave), LogLiquidacion.Evento.RELIQUIDO, null);
			}
		}

		if(pGuardarReliquidacion) {
			this.entityManager.merge(pLiquidacionTasa);
			System.out.println("Reliquidaciones guardadas...");
			this.entityManager.flush();
			this.guardarOActualizarExenciones();
		} else {
			// this.entityManager.clear();
			System.out.println("Reliquidaciones temporales eliminadas...");
		}
		// System.out.println(locListaReliquidaciones.size() +
		// " devolucion de deudas reliquidadas ///**"+ locListaReliquidaciones.get(0).toString());
		entityManager.clear();
		return locListaReliquidaciones;
	}

	private List<AlicuotaLiquidada> getListaAlicuotasLiquidadas(LiquidacionTasa pLiquidacionTasa, List<ParametroValuadoAlicuota> pListaNuevosParametrosAlicuotas) {

		this.prepararListaAlicuotasLiquidadas();

		List<AlicuotaLiquidada> locListaResultado = new ArrayList<AlicuotaLiquidada>();

		// Se recorren todas las alicuotas liquidadas, para ver si sus parametros cambian o no de valor.
		for(AlicuotaLiquidada cadaAlicuotaLiquidada : pLiquidacionTasa.getListaAlicuotasLiquidadas()) {
			AlicuotaLiquidada nuevaAlicuotaLiquidada = new AlicuotaLiquidada();
			nuevaAlicuotaLiquidada.setRegAlicuota(cadaAlicuotaLiquidada.getRegAlicuota());
			for(ParametroValuadoAlicuota cadaParametroActual : cadaAlicuotaLiquidada.getListaParametrosValuados()) {
				// El parametro siempre es nuevo, luego se decide si el valor cambia o se mantiene.
				Object locValor = null;
				for(ParametroValuadoAlicuota cadaParametroNuevo : pListaNuevosParametrosAlicuotas) {
					if(cadaParametroActual.getNombre().equals(cadaParametroNuevo.getNombre())
							&& cadaParametroActual.getAlicuotaLiquidada().getRegAlicuota().equals(cadaParametroNuevo.getAlicuotaLiquidada().getRegAlicuota())) {
						// El valor debe cambiar.
						// Se recupera el TipoParametroAlicuota
						TipoParametroAlicuota locTipoParametro = null;
						Iterator<TipoParametroAlicuota> itTipoParametro = pLiquidacionTasa.getTipoTasa().getListaParamatrosAlicuota().iterator();
						while(itTipoParametro.hasNext() && locTipoParametro == null) {
							TipoParametroAlicuota cadaTipoParametroAlicuota = itTipoParametro.next();
							if(cadaParametroNuevo.getNombre().equals(cadaTipoParametroAlicuota.getNombreVariable())) {
								locTipoParametro = cadaTipoParametroAlicuota;
							}
						}
						locTipoParametro.setCuotaLiquidacion(pLiquidacionTasa.getCuotaLiquidacion());
						locValor = locTipoParametro.getValor(pLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado()
								.getAsocRegAlicuotaPorAlicuota(cadaAlicuotaLiquidada.getRegAlicuota()));
						break;
					}
				}
				// Si no cambio el valor es porque no se recalculo y sigue con el valor actual.
				if(locValor == null) {
					locValor = cadaParametroActual.getValorParametro();
				}
				ParametroValuadoAlicuota locNuevoParametroValuadoAlicuota = ParametroValuadoAlicuota.getInstance(locValor);
				locNuevoParametroValuadoAlicuota.setNombre(cadaParametroActual.getNombre());
				nuevaAlicuotaLiquidada.addParametroValuado(locNuevoParametroValuadoAlicuota);
			}
			locListaResultado.add(nuevaAlicuotaLiquidada);
		}
		return locListaResultado;
	}

	private void guardarOActualizarExenciones() {
		System.out.println("Guardando o Actualizando Exenciones...");
		int locContadorExenciones = 0;

		for(ExencionRegistroDeuda cadaExencionRegistroDeuda : this.listaExencionesRegistrosDeuda.values()) {

			if(locContadorExenciones == 10000) {
				this.entityManager.flush();
				System.out.println(locContadorExenciones + " exenciones guardadas o actualizadas...");
				locContadorExenciones = 0;
			}

			if(cadaExencionRegistroDeuda.getListaRegistrosExencion() != null && !cadaExencionRegistroDeuda.getListaRegistrosExencion().isEmpty()) {
				for(RegistroExencionRegistroDeuda cadaRegistroExencionRegistroDeuda : cadaExencionRegistroDeuda.getListaRegistrosExencion()) {
					cadaRegistroExencionRegistroDeuda.getRegistroDeuda().setRegistroExencionRegistroDeuda(cadaRegistroExencionRegistroDeuda);
					if(cadaRegistroExencionRegistroDeuda.getIdRegistroExencionRegistroDeuda() != -1) {
						this.entityManager.merge(cadaRegistroExencionRegistroDeuda.getRegistroDeuda());
					} else {
						this.entityManager.persist(cadaRegistroExencionRegistroDeuda);
					}
					// this.sess.replicate(cadaRegistroExencionRegistroDeuda.getRegistroDeuda(), ReplicationMode.LATEST_VERSION);
				}
			}

			this.entityManager.merge(cadaExencionRegistroDeuda);
			// this.sess.replicate(cadaExencionRegistroDeuda, ReplicationMode.LATEST_VERSION);
			locContadorExenciones++;
		}

		if(locContadorExenciones > 0) {
			System.out.println(locContadorExenciones + " exenciones guardadas o actualizadas...");
		}
	}

	/**
	 * 
	 * @param pRegistroDeuda
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<RegistroDeuda> getListaRegistrosDeudaAsociados(RegistroDeuda pRegistroDeuda, TipoDeuda pTipoDeuda) throws Exception {
		Criterio locCriterio = null;
		entityManager.detach(pRegistroDeuda);

		if(pRegistroDeuda instanceof LiquidacionTasa) {
			locCriterio = Criterio.getInstance(this.entityManager, LiquidacionTasa.class);
			// .crearAlias("listaAlicuotasLiquidadas", "cadaAlicuota")
			// .crearAlias("listaModificadoresLiquidacion", "cadaModificador")
			// .crearAlias("listaParametrosValuados", "cadaParametro");

		} else if(pRegistroDeuda instanceof CuotaRefinanciacion) {
			locCriterio = Criterio.getInstance(this.entityManager, CuotaRefinanciacion.class);
		}

		if(pRegistroDeuda != null) {
			locCriterio.add(Restriccion.IGUAL("cuotaLiquidacion", pRegistroDeuda.getCuotaLiquidacion()));
		}
		locCriterio.add(Restriccion.IGUAL("docGeneradorDeuda.obligacion", pRegistroDeuda.getDocGeneradorDeuda().getObligacion())).add(Restriccion.IGUAL("tipoDeuda", pTipoDeuda))
				.add(Restriccion.NULO("registroCancelacion"));
		// .setModoDebug(true);

		List<RegistroDeuda> locListaRetornoDeudas = locCriterio.list();
		System.out.println(locListaRetornoDeudas.size());

		for(Object cadaObject : locListaRetornoDeudas) {
			RegistroDeuda locRegistroDeuda = (RegistroDeuda) cadaObject;
			if(locRegistroDeuda instanceof LiquidacionTasa) {
				LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) locRegistroDeuda;
				locLiquidacionTasa.toString();
				locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getPersona().toString();
				locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().toString();

				if(locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getListaRegistrosExencion() != null) {
					locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getListaRegistrosExencion().toString();
				}

				locLiquidacionTasa.getDocGeneradorDeuda().toString();
				locLiquidacionTasa.getTipoTasa().toString();
				locLiquidacionTasa.getTipoTasa().getListaModificadores().toString();
				locLiquidacionTasa.getTipoTasa().getListaParametros().toString();
				locLiquidacionTasa.getTipoTasa().getListaVencimientos().toString();
			} else {
				locRegistroDeuda.toString();
				locRegistroDeuda.getDocGeneradorDeuda().getObligacion().getPersona().toString();
				locRegistroDeuda.getDocGeneradorDeuda().getObligacion().toString();
				locRegistroDeuda.getDocGeneradorDeuda().toString();
			}
			this.entityManager.detach(cadaObject);
		}
		return locListaRetornoDeudas;
	}

	public LiquidacionTasa levantarDeuda(LiquidacionTasa pLiquidacionTasa) {
		LiquidacionTasa locLiquidacionTasa = entityManager.merge(pLiquidacionTasa);
		locLiquidacionTasa.toString();
		locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getPersona().toString();
		locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().toString();

		if(locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getListaRegistrosExencion() != null) {
			locLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getListaRegistrosExencion().toString();
		}

		locLiquidacionTasa.getDocGeneradorDeuda().toString();
		locLiquidacionTasa.getTipoTasa().toString();
		locLiquidacionTasa.getTipoTasa().getListaModificadores().toString();
		locLiquidacionTasa.getTipoTasa().getListaParametros().toString();
		locLiquidacionTasa.getTipoTasa().getListaVencimientos().toString();
		return locLiquidacionTasa;
	}

	/**
	 * 
	 * @param pLiquidacionTasa
	 * @param pFechaReLiquidacion
	 * @param pFechaNuevoVencimiento
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	public List<Reliquidacion> reliquidarObligacion(LiquidacionTasa pLiquidacionTasa, java.util.Date pFechaReLiquidacion, java.util.Date pFechaNuevoVencimiento,
			boolean pAplicarIntereses) throws Exception {
		List<Reliquidacion> locListaReliquidaciones = new ArrayList<Reliquidacion>();

		// this.isListaParametrosCargada = false;
		// Si es TGI Recupero las liquidaciones Anual, Bimestral y las 3 de Tercios.
		this.listaLiquidacionesTasaAnteriores = this.getListaRegistrosDeudaAsociados(pLiquidacionTasa, null);

		for(Object cadaObject : this.listaLiquidacionesTasaAnteriores) {
			pLiquidacionTasa = (LiquidacionTasa) cadaObject;
			pLiquidacionTasa.toString();
			this.validarPreCondicionesReliquidacion(pLiquidacionTasa, pFechaNuevoVencimiento);
			if(pFechaNuevoVencimiento != null) {
				this.fechaNuevoVencimiento = pFechaNuevoVencimiento;
			}

			else {
				this.fechaNuevoVencimiento = pLiquidacionTasa.getFechaVencimiento();
			}
			// this.fechaReLiquidacion = pLiquidacionTasa.getPeriodo().getFechaInicio().getTime();
			// if(pFechaReLiquidacion != null){
			this.fechaReLiquidacion = pFechaReLiquidacion;
			// }

			List<ParametroValuado> locListadoParametrosValuados = this.getListaParametrosValuados(pLiquidacionTasa, null, null);
			// TODO Aqui se toman las alicuotas liquidadas directamente, pero deberia darse al usuario la opcion de que tomen nuevos valores.
			List<AlicuotaLiquidada> locListaAlicuotasLiquidadas = new ArrayList<AlicuotaLiquidada>(pLiquidacionTasa.getListaAlicuotasLiquidadas());
			Reliquidacion locReliquidacion = this.reliquidarObligacion(pLiquidacionTasa, locListadoParametrosValuados, locListaAlicuotasLiquidadas, pAplicarIntereses);// ,
																																										// pDigestoMunicipal);

			LiquidacionTasa locLiquidacionTasa = locReliquidacion.getLiquidacionTasa();
			locLiquidacionTasa.setTipoTasa(locReliquidacion.getLiquidacionTasa().getTipoTasa());

			for(Vencimiento locNuevoVencimiento : this.crearNuevosVencimientos(pLiquidacionTasa, this.fechaNuevoVencimiento)) {
				locNuevoVencimiento.setValor(0D);
				locLiquidacionTasa.getListaVencimientos().add(locNuevoVencimiento);

				if(locNuevoVencimiento.getIdVencimiento() != -1) {
					this.entityManager.merge(locNuevoVencimiento);
				}
				this.entityManager.merge(locNuevoVencimiento);
			}

			for(ParametroValuado cadaParametroValuado : locLiquidacionTasa.getListaParametrosValuados()) {
				if(cadaParametroValuado.getIdParametroValuado() != -1) {
					this.entityManager.merge(cadaParametroValuado);
				} else
					this.entityManager.merge(cadaParametroValuado);
			}

			for(ModificadorLiquidacion cadaModificadorLiquidacion : locLiquidacionTasa.getListaModificadoresLiquidacion()) {
				if(cadaModificadorLiquidacion.getIdModificadorLiquidacion() != -1) {
					this.entityManager.merge(cadaModificadorLiquidacion);
				} else
					this.entityManager.merge(cadaModificadorLiquidacion);
			}

			// una vez que reliquido cancelo la liquidación tasa
			this.entityManager.refresh(locReliquidacion);
			pLiquidacionTasa.setRegistroCancelacion(locReliquidacion);
			this.entityManager.merge(pLiquidacionTasa);

			locListaReliquidaciones.add(locReliquidacion);
		}

		return locListaReliquidaciones;

	}

	@SuppressWarnings("unchecked")
	private void prepararListaParametrosValuados() {
		// List<ParametroValuado> locListaParametrosValuados = Criterio.getInstance(this.entityManager, ParametroValuado.class)
		// .list();
		//
		// this.listaParametrosValuados = new LinkedHashMap<Integer, ParametroValuado>();
		// for(ParametroValuado cadaParametroValuado : locListaParametrosValuados){
		// if(!this.listaParametrosValuados.containsKey(cadaParametroValuado.hashCode())){
		// this.listaParametrosValuados.put(cadaParametroValuado.hashCode(), cadaParametroValuado);
		// }
		// }
		// List<ParametroValuado> locListaParametrosValuados = this.sess.createCriteria(ParametroValuado.class)
		// .list();
		//
		// this.listaParametrosValuados = new LinkedHashMap<Integer, ParametroValuado>();
		// for(ParametroValuado cadaParametroValuado : locListaParametrosValuados){
		// if(!this.listaParametrosValuados.containsKey(cadaParametroValuado.hashCode())){
		// this.listaParametrosValuados.put(cadaParametroValuado.hashCode(), cadaParametroValuado);
		// }
		// }
	}

	@SuppressWarnings("unchecked")
	private void prepararListaModificadoresLiquidacion() {
		// List<ModificadorLiquidacion> locListaModificadoresLiquidacion = Criterio.getInstance(this.entityManager, ModificadorLiquidacionFormula.class)
		// .list();
		//
		// this.listaModificadoresLiquidacion = new LinkedHashMap<Integer, ModificadorLiquidacion>();
		// for(ModificadorLiquidacion cadaModificadorLiquidacion : locListaModificadoresLiquidacion){
		// if(!this.listaModificadoresLiquidacion.containsKey(cadaModificadorLiquidacion.hashCode())){
		// this.listaModificadoresLiquidacion.put(cadaModificadorLiquidacion.hashCode(), cadaModificadorLiquidacion);
		// }
		// }
	}

	@SuppressWarnings("unchecked")
	private void prepararListaVencimientos() {
		// List<Vencimiento> locListaVencimientos = Criterio.getInstance(this.entityManager, Vencimiento.class)
		// .list();
		//
		// this.listaVencimientos = new LinkedHashMap<Integer, Vencimiento>();
		// for(Vencimiento cadaVencimiento : locListaVencimientos){
		// if(!this.listaVencimientos.containsKey(cadaVencimiento.hashCode())){
		// this.listaVencimientos.put(cadaVencimiento.hashCode(), cadaVencimiento);
		// }
		// }
	}

	private void prepararListaAlicuotasLiquidadas() {
		List<AlicuotaLiquidada> locListaAlicuotas = Criterio.getInstance(entityManager, AlicuotaLiquidada.class).list();
		this.listaAlicuotasLiquidadas = new LinkedHashMap<Integer, AlicuotaLiquidada>();
		for(AlicuotaLiquidada cadaAlicuotaLiquidada : locListaAlicuotas) {
			listaAlicuotasLiquidadas.put(cadaAlicuotaLiquidada.hashCode(), cadaAlicuotaLiquidada);
		}
	}

	private void prepararListaExencionesRegistrosDeuda() {
		// Mientras la exencion no esté terminada me sirve para agregarle nuevos registros de deuda exentos
		List<ExencionRegistroDeuda> locListaExencionesRegistrosDeuda = Criterio.getInstance(this.entityManager, ExencionRegistroDeuda.class)
				.add(Restriccion.DISTINTO("estado", Estado.TERMINADA)).list();

		this.listaExencionesRegistrosDeuda = new LinkedHashMap<Integer, ExencionRegistroDeuda>();
		for(ExencionRegistroDeuda cadaExencionRegistroDeuda : locListaExencionesRegistrosDeuda) {

			if(cadaExencionRegistroDeuda.getListaRegistrosExencion() != null && !cadaExencionRegistroDeuda.getListaRegistrosExencion().isEmpty()) {
				cadaExencionRegistroDeuda.getListaRegistrosExencion().toString();
			}

			if(!this.listaExencionesRegistrosDeuda.containsKey(cadaExencionRegistroDeuda.hashCode())) {
				this.listaExencionesRegistrosDeuda.put(cadaExencionRegistroDeuda.hashCode(), cadaExencionRegistroDeuda);
			}
		}

	}

	/**
	 * Recupera listado de Registros de Deuda del contribuyente
	 * 
	 * @param pPersona
	 * @param pTipoDeuda
	 * @throws Exception
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List findListaRegistrosDeudaContribuyente(Persona pPersona, TipoDeuda pTipoDeuda, Periodo pPeriodo, EstadoRegistroDeuda pEstadoRegistroDeuda, TipoObligacion pTipoObligacion,
	// java.lang.Integer pNroRegistroParcela
			com.trascender.catastro.recurso.persistent.Parcela pParcela) throws Exception {
		List<RegistroDeuda> locListaRetorno = new ArrayList<RegistroDeuda>();
		System.out.println(pPersona);
		if(pPersona == null && pPeriodo == null && pParcela == null) {
			throw new SaicException(71);
		}

		Criterio locCriterio = Criterio.getInstance(this.entityManager, LiquidacionTasa.class).crearAlias("docGeneradorDeuda.obligacion", "locObligacion")
				.crearAlias("locObligacion.documentoEspecializado", "locDocEsp");

		if(pTipoDeuda != null) {
			locCriterio.add(Restriccion.IGUAL("tipoDeuda", pTipoDeuda));
		} else {
			locCriterio.add(Restriccion.DISTINTO("tipoDeuda", TipoDeuda.REFINANCIACION));
		}
		if(pPersona != null) {
			locCriterio.add(Restriccion.IGUAL("locObligacion.persona", pPersona));
		}
		if(pParcela != null) {
			locCriterio.add(Restriccion.IGUAL("locDocEsp.parcela", pParcela));
		}
		if(pTipoObligacion != null) {
			locCriterio.add(Restriccion.IGUAL("tipoTasa.tipoObligacion", pTipoObligacion));
		}
		locCriterio.add(Orden.ASC("idRegistroDeuda"));

		List<LiquidacionTasa> locListaRegistrosDeuda = locCriterio.list();
		System.out.println("Lista Registros Deuda encontrados" + locListaRegistrosDeuda.size());
		Iterator<LiquidacionTasa> locIterator = locListaRegistrosDeuda.iterator();
		while(locIterator.hasNext()) {
			boolean locAgregar = true;
			LiquidacionTasa locRegistroDeuda = locIterator.next();
			if(locRegistroDeuda.getNombre() != null) {
				locRegistroDeuda.getNombre().toString();
			}
			locRegistroDeuda.getPersona().toString();
			locRegistroDeuda.getCuotaLiquidacion().toString();
			locRegistroDeuda.getDocGeneradorDeuda().getObligacion().toString();
			locRegistroDeuda.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().toString();
			locRegistroDeuda.getMonto();
			// Cuando se trata de un doc habilitante como shps la obligacion no se genera para la parcela sino para el contribuyente
			if(locRegistroDeuda.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getParcela() != null) {
				locRegistroDeuda.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado().getParcela().toString();
			}

			if(locRegistroDeuda.getDocGeneradorDeuda() instanceof TasaTGI && pTipoObligacion != null && pTipoObligacion.getNombre().equals("TGI")) {
				TasaTGI locTasaTGI = (TasaTGI) locRegistroDeuda.getDocGeneradorDeuda();
				if(locTasaTGI.getEstado().equals(TasaTGI.Estado.ACTIVO)) {
					locAgregar = true;
				}
			}

			if(pEstadoRegistroDeuda != null) {
				if(!locRegistroDeuda.getEstado().equals(pEstadoRegistroDeuda)) {
					locAgregar = false;
				}
			}

			if(locAgregar) {
				locListaRetorno.add(locRegistroDeuda);
			}
			// cargo los modificadores y vencimientos

			for(ModificadorLiquidacion cadaModificador : locRegistroDeuda.getListaModificadoresLiquidacion()) {
				cadaModificador.toString();
			}
		}

		return locListaRetorno;
	}

	/**
	 * Recupera una Reliquidacion por Id_NUEVA_LIQUIDACION
	 * 
	 * @param pId_Deuda
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 **/

	@Override
	public Reliquidacion getReliquidacionPorIdNuevaDeuda(long pIdDeuda) throws Exception {
		Reliquidacion locReliquidacion = (Reliquidacion) Criterio.getInstance(this.entityManager, Reliquidacion.class)
				.add(Restriccion.IGUAL("liquidacionTasa.idRegistroDeuda", pIdDeuda)).uniqueResult();

		if(locReliquidacion != null) {
			locReliquidacion.toString();
			if(locReliquidacion.getDigestoMunicipal() != null) {
				locReliquidacion.getDigestoMunicipal().toString();
			}
			locReliquidacion.getLiquidacionTasa().toString();

			for(Vencimiento locVencimiento : locReliquidacion.getLiquidacionTasa().getListaVencimientos()) {
				locVencimiento.toString();
			}

			for(ModificadorLiquidacion locModificadorLiquidacion : locReliquidacion.getLiquidacionTasa().getListaModificadoresLiquidacion()) {
				locModificadorLiquidacion.toString();
			}

			for(ParametroValuado locParametroValuado : locReliquidacion.getLiquidacionTasa().getListaParametrosValuados()) {
				locParametroValuado.toString();
			}

			locReliquidacion.getLiquidacionTasa().toString();
			locReliquidacion.getLiquidacionTasa().getDocGeneradorDeuda().toString();
			locReliquidacion.getLiquidacionTasa().getDocGeneradorDeuda().getObligacion().toString();
			locReliquidacion.getLiquidacionTasa().getStringObligacion();
			locReliquidacion.getLiquidacionTasa().getEstado().toString();
			locReliquidacion.getLiquidacionTasa().getMonto();

			if(locReliquidacion.getLiquidacionTasa().getRegistroCancelacion() != null) {
				locReliquidacion.getLiquidacionTasa().getRegistroCancelacion().toString();
				locReliquidacion.getLiquidacionTasa().getFechaCancelacion().toString();
			}
		}
		return locReliquidacion;
	}

	/**
	 * Recupera una Reliquidacion por Id@param pId
	 * 
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 * */

	@Override
	public Reliquidacion getReliquidacionPorId(long pId) throws Exception {
		Reliquidacion locReliquidacion = new Reliquidacion();
		locReliquidacion = (Reliquidacion) Criterio.getInstance(this.entityManager, Reliquidacion.class).add(Restriccion.IGUAL("idRegistroCancelacion", pId))
		// .setFetchJoin("liquidacionTasa", "digestoMunicipal")
				.uniqueResult();

		if(locReliquidacion != null) {
			locReliquidacion.toString();
			if(locReliquidacion.getDigestoMunicipal() != null) {
				locReliquidacion.getDigestoMunicipal().toString();
			}
			locReliquidacion.getLiquidacionTasa().toString();

			for(Vencimiento locVencimiento : locReliquidacion.getLiquidacionTasa().getListaVencimientos()) {
				locVencimiento.toString();
			}

			for(ModificadorLiquidacion locModificadorLiquidacion : locReliquidacion.getLiquidacionTasa().getListaModificadoresLiquidacion()) {
				locModificadorLiquidacion.toString();
			}

			for(ParametroValuado locParametroValuado : locReliquidacion.getLiquidacionTasa().getListaParametrosValuados()) {
				locParametroValuado.toString();
			}

			locReliquidacion.getLiquidacionTasa().toString();
			locReliquidacion.getLiquidacionTasa().getDocGeneradorDeuda().toString();
			locReliquidacion.getLiquidacionTasa().getDocGeneradorDeuda().getObligacion().toString();
			locReliquidacion.getLiquidacionTasa().getStringObligacion();
			locReliquidacion.getLiquidacionTasa().getEstado().toString();
			locReliquidacion.getLiquidacionTasa().getMonto();

			if(locReliquidacion.getLiquidacionTasa().getRegistroCancelacion() != null) {
				locReliquidacion.getLiquidacionTasa().getRegistroCancelacion().toString();
				locReliquidacion.getLiquidacionTasa().getFechaCancelacion().toString();
			}
		}
		return locReliquidacion;
	}

	/**
	 * Crea una lista de parámetros valuados a partir de los ya existentes
	 * 
	 * @param pLiquidacionTasa
	 * @return
	 * @throws Exception
	 */
	// TODO Auxiliar
	private List<ParametroValuado> getListaParametrosValuados(LiquidacionTasa pLiquidacionTasa, List<String> pListaParametrosValuadosNuevos, Map<String, Object> pMapaValoresFijos)
			throws Exception {
		this.jep = MotorFormulas.initializeJEP();

		// Parámetros que se modifican
		List<ParametroValuado> locListaParametrosActualizados = new ArrayList<ParametroValuado>();

		if(!pListaParametrosValuadosNuevos.isEmpty()) {

			prepararParametros(pLiquidacionTasa);

			for(TipoParametro cadaTipoParametro : pLiquidacionTasa.getTipoTasa().getListaParametros()) {
				// Calculo los valores para los parametros que el usuario quiere.
				if(pListaParametrosValuadosNuevos.contains(cadaTipoParametro.getNombreVariable())) {
					cadaTipoParametro = this.entityManager.find(TipoParametro.class, cadaTipoParametro.getIdTipoParametro());

					ParametroValuado locParametroValuado = this.businessLiquidacionTasaLocal.getParametroValuadoFromTipoParametro(pLiquidacionTasa.getDocGeneradorDeuda()
							.getObligacion().getDocumentoEspecializado(), cadaTipoParametro, pLiquidacionTasa.getCuotaLiquidacion(), pLiquidacionTasa.getNumeroCuota(),
							pLiquidacionTasa.getIdRegistroDeuda());
					locParametroValuado.setLiquidacionTasa(pLiquidacionTasa);

					Variable locVariable = jep.getVar(locParametroValuado.getNombreParametro());
					if(locVariable == null) {
						jep.addVariable(locParametroValuado.getNombreParametro(), locParametroValuado.getValorParametro());
					} else {
						jep.setVarValue(locParametroValuado.getNombreParametro(), locParametroValuado.getValorParametro());
					}
					locListaParametrosActualizados.add(locParametroValuado);
				}
			}
		}

		// Seteo los valores fijos que quiere el usuario
		if(!pMapaValoresFijos.isEmpty()) {
			for(TipoParametro cadaTipoParametro : pLiquidacionTasa.getTipoTasa().getListaParametros()) {
				Object locValor = pMapaValoresFijos.get(cadaTipoParametro.getNombreVariable());
				if(locValor != null) {
					ParametroValuado locParametro = this.getParametroValuado(cadaTipoParametro.getNombreVariable(), locValor);
					locParametro.setLiquidacionTasa(pLiquidacionTasa);
					Variable locVariable = jep.getVar(locParametro.getNombreParametro());
					if(locVariable == null) {
						jep.addVariable(locParametro.getNombreParametro(), locParametro.getValorParametro());
					} else {
						jep.setVarValue(locParametro.getNombreParametro(), locParametro.getValorParametro());
					}
					locListaParametrosActualizados.add(locParametro);
				}
			}
		}

		List<ParametroValuado> locListaParametrosValuadosRetorno = new ArrayList<ParametroValuado>();
		/*
		 * Se chequean por parametros nuevos en la formula de calculo, ya que se puede haber modificado la formula activa y hay que calcular estos parametros.
		 * De haberlos, se añaden a la lista de retorno, pues es obvio que no existian en la liquidacion anterior
		 */
		for(TipoParametro cadaTipoParametro : pLiquidacionTasa.getTipoTasa().getListaParametros()) {
			boolean esta = false;
			for(ParametroValuado cadaParametroValuado : pLiquidacionTasa.getListaParametrosValuados()) {
				if(cadaParametroValuado.getNombreParametro().equals(cadaTipoParametro.getNombreVariable())) {
					esta = true;
					break;
				}
			}
			if(!esta) {
				this.prepararParametros(pLiquidacionTasa);
				ParametroValuado locParametroValuado = this.businessLiquidacionTasaLocal.getParametroValuadoFromTipoParametro(pLiquidacionTasa.getDocGeneradorDeuda().getObligacion()
						.getDocumentoEspecializado(), cadaTipoParametro, pLiquidacionTasa.getCuotaLiquidacion(), pLiquidacionTasa.getNumeroCuota(),
						pLiquidacionTasa.getIdRegistroDeuda());
				locParametroValuado.setLiquidacionTasa(pLiquidacionTasa);
				Variable locVariable = jep.getVar(locParametroValuado.getNombreParametro());
				if(locVariable == null) {
					jep.addVariable(locParametroValuado.getNombreParametro(), locParametroValuado.getValorParametro());
				} else {
					jep.setVarValue(locParametroValuado.getNombreParametro(), locParametroValuado.getValorParametro());
				}
				// locListaParametrosActualizados.add(locParametroValuado);
				locListaParametrosValuadosRetorno.add(locParametroValuado);

			}
		}

		/*
		 * Armo una lista con los parametros que se van a usar, esta lista contiene los parámetros que van a cambiar de valor y los que no, para eso ya armé
		 * arriba la lista con los parametros que cambian. Ahora recorro la lista de todos los parametros de la liquidacion, buscando cuales son los que
		 * cambian, si lo encuentro lo agrego a la lista de retorno en el mismo momento y sino lo cambio al salir del while
		 */
		for(ParametroValuado cadaParametroValuadoActual : pLiquidacionTasa.getListaParametrosValuados()) {
			boolean locReemplazado = false;
			Iterator<ParametroValuado> locIterador = locListaParametrosActualizados.iterator();
			while(locIterador.hasNext() && locReemplazado == false) {
				ParametroValuado cadaParametroValuadoActualizado = locIterador.next();
				if(cadaParametroValuadoActualizado.getNombreParametro().equals(cadaParametroValuadoActual.getNombreParametro())) {
					locListaParametrosValuadosRetorno.add(cadaParametroValuadoActualizado);
					locReemplazado = true;
				}
			}

			// No estaba en la lista de actualizados
			if(!locReemplazado) {
				Object valor = cadaParametroValuadoActual.getValorParametro();
				ParametroValuado locParametro = this.getParametroValuado(cadaParametroValuadoActual.getNombreParametro(), valor);
				locListaParametrosValuadosRetorno.add(locParametro);
			}
		}
		return locListaParametrosValuadosRetorno;
	}

	private void prepararParametros(LiquidacionTasa pLiquidacionTasa) throws Exception {
		if(!this.isListaParametrosCargada) {
			this.prepararListaParametrosValuados();

			this.prepararListaVencimientos();

			this.prepararListaModificadoresLiquidacion();

			this.prepararListaAlicuotasLiquidadas();

			System.out.println("entra a llamar al procedimiento actualizacion");
			if(pLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoTGI) {
				this.businessLiquidacionTasaLocal.ejecutarProcedimientoActualizacionDeuda(pLiquidacionTasa.getPersona(), pLiquidacionTasa.getDocGeneradorDeuda().getObligacion()
						.getDocumentoEspecializado().getParcela());
			} else if(pLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoOSP) {
				System.out.println("entra a llamar al procedimiento actualizacion OSP");
				this.businessLiquidacionTasaLocal.ejecutarProcedimientoActualizacionDeuda(pLiquidacionTasa.getPersona(), pLiquidacionTasa.getDocGeneradorDeuda().getObligacion()
						.getDocumentoEspecializado().getParcela());
			} else if(pLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoSHPS) {
				this.businessLiquidacionTasaLocal.ejecutarProcedimientoActualizacionDeuda(pLiquidacionTasa.getPersona(), null);
			} else if(pLiquidacionTasa.getDocGeneradorDeuda().getObligacion().getDocumentoEspecializado() instanceof DocumentoPlanObra) {
				this.businessLiquidacionTasaLocal.ejecutarProcedimientoActualizacionDeuda(pLiquidacionTasa.getPersona(), pLiquidacionTasa.getDocGeneradorDeuda().getObligacion()
						.getDocumentoEspecializado().getParcela());
			}

			System.out.println("termina procedimiento actualizacion");
			this.isListaParametrosCargada = true;
		}
	}

	private ParametroValuado getParametroValuado(String pNombreParametro, Object pValorParametro) {
		if(pValorParametro instanceof Double) {
			ParametroValuadoDouble locParametroValuado = new ParametroValuadoDouble();
			locParametroValuado.setNombreParametro(pNombreParametro);
			locParametroValuado.setValorParametro((Double) pValorParametro);
			return locParametroValuado;
		} else {
			ParametroValuadoString locParametroValuado = new ParametroValuadoString();
			locParametroValuado.setNombreParametro(pNombreParametro);
			locParametroValuado.setValorParametro(pValorParametro.toString());
			return locParametroValuado;
		}
	}

	/**
	 * Crea una nueva reLiquidacion
	 * 
	 * @return
	 * @throws Exception
	 */
	private Reliquidacion reliquidarObligacion(LiquidacionTasa pLiquidacionTasa, List<ParametroValuado> pListadoParametrosValuados, List<AlicuotaLiquidada> pListaALicuotasLiquidadas,
			boolean pAlicarIntereses
	// com.trascender.framework.recurso.persistent.DigestoMunicipal pDigestoMunicipal
	) throws Exception {
		Reliquidacion locReliquidacion = Reliquidacion.getInstance(pLiquidacionTasa);
		// Se cancela el día de la fecha
		locReliquidacion.setFechaCancelacion(Calendar.getInstance().getTime());
		LiquidacionTasa locLiquidacionTasa = this.newLiquidacionTasa(pLiquidacionTasa, pListadoParametrosValuados, pListaALicuotasLiquidadas, pAlicarIntereses);
		this.entityManager.detach(pLiquidacionTasa);
		locLiquidacionTasa = this.entityManager.merge(locLiquidacionTasa);
		locReliquidacion.setLiquidacionTasa(locLiquidacionTasa);
		locReliquidacion = this.entityManager.merge(locReliquidacion);
		return locReliquidacion;
	}

	/**
	 * Liquida una obligacion. Un asco sos, un asco.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private LiquidacionTasa newLiquidacionTasa(LiquidacionTasa pLiquidacionTasa, List<ParametroValuado> pListaParametrosValuados, List<AlicuotaLiquidada> pListaAlicuotasLiquidadas,
			boolean pAplicarInteres) throws Exception {
		List<String> listaVariablesVencimiento = new ArrayList<String>(TipoParametroVencimiento.TipoAtributoVencimiento.values().length);

		for(TipoAtributoVencimiento cadaAtributoVencimiento : TipoParametroVencimiento.TipoAtributoVencimiento.values()) {
			listaVariablesVencimiento.add(Util.getEnumNameFromString(cadaAtributoVencimiento.toString()));
		}

		for(ParametroValuado cadaParametroValuado : pListaParametrosValuados) {
			// significa que no pertenece a los parámetros de los vencimientos
			if(!listaVariablesVencimiento.contains(cadaParametroValuado.getNombreParametro())) {
				this.jep.addVariable(cadaParametroValuado.getNombreParametro(), cadaParametroValuado.getValorParametro());
			}
		}

		// Variables simples
		for(VariableFormulaSimple cadaVariableSimple : pLiquidacionTasa.getTipoTasa().getListaVariablesSimple()) {
			this.jep.parseExpression(cadaVariableSimple.getExpresion());
			cadaVariableSimple.setValor(jep.getValue());
			this.jep.addVariable(cadaVariableSimple.getNombre(), cadaVariableSimple.getValor());
		}

		String locFormula = pLiquidacionTasa.getTipoTasa().getFormula();

		jep.parseExpression(locFormula);
		Double locValor = Double.valueOf(jep.getValue()).doubleValue();
		locValor = Util.redondear(locValor, 2);

		String locErrorInfo = jep.getErrorInfo();

		if((locErrorInfo != null) && (!locErrorInfo.equals(""))) {
			System.out.println(locErrorInfo);
			throw new SaicException(70);
		}

		if(locValor.isNaN()) {
			locValor = 0d;
		}

		LiquidacionTasa locLiquidacionTasa = LiquidacionTasa.getInstance(pLiquidacionTasa);

		// locLiquidacionTasa.setTipoDeuda(pLiquidacionTasa.getTipoDeuda());
		locLiquidacionTasa.setTipoDeuda(TipoDeuda.RELIQUIDACION);

		locLiquidacionTasa.setValor(locValor);
		locLiquidacionTasa.setFechaEmision(Calendar.getInstance().getTime());

		// locLiquidacionTasa.setEstadoAnterior(pLiquidacionTasa.getEstado());

		locLiquidacionTasa.getListaParametrosValuados().addAll(pListaParametrosValuados);

		for(ParametroValuado locParametroValuado : pListaParametrosValuados) {
			locParametroValuado.setLiquidacionTasa(locLiquidacionTasa);
		}

		if(pListaAlicuotasLiquidadas != null && !pListaAlicuotasLiquidadas.isEmpty()) {

			List<AlicuotaLiquidada> locListaAlicuotasLiquidadas = new ArrayList<AlicuotaLiquidada>();
			// Iteracion inicial para calcular las variables
			for(AlicuotaLiquidada cadaAlicuotaLiquidada : pListaAlicuotasLiquidadas) {
				AlicuotaLiquidada locAlicuotaLiquidadaNueva = new AlicuotaLiquidada();
				locAlicuotaLiquidadaNueva.setRegAlicuota(cadaAlicuotaLiquidada.getRegAlicuota());
				for(ParametroValuadoAlicuota cadaParametro : cadaAlicuotaLiquidada.getListaParametrosValuados()) {
					jep.addVariable(cadaParametro.getNombre(), cadaParametro.getValorParametro());
					locAlicuotaLiquidadaNueva.addParametroValuado(cadaParametro);
				}
				locListaAlicuotasLiquidadas.add(locAlicuotaLiquidadaNueva);

				for(VariableFormulaSimple cadaVariableSimple : pLiquidacionTasa.getTipoTasa().getListaVariablesSimpleAlicuota()) {
					jep.parseExpression(cadaVariableSimple.getExpresion());
					jep.addVariable(cadaVariableSimple.getNombre(), jep.getValue());
				}
				for(VariableFormulaCompuesta cadaVariableCompuesta : pLiquidacionTasa.getTipoTasa().getListaVariablesFormulaCompuesta()) {
					jep.parseExpression(cadaVariableCompuesta.getExpresion());
					cadaVariableCompuesta.getListaEvaluandos().add(jep.getValue());
				}
			}

			for(VariableFormulaCompuesta cadaVariableCompuesta : pLiquidacionTasa.getTipoTasa().getListaVariablesFormulaCompuesta()) {
				jep.addVariable(cadaVariableCompuesta.getNombre(), cadaVariableCompuesta.getValor());
			}

			if(pLiquidacionTasa.getTipoTasa().isIteraSobreAlicuotas()) {
				for(AlicuotaLiquidada cadaAlicuotaLiquidada : locListaAlicuotasLiquidadas) {
					jep.parseExpression(pLiquidacionTasa.getTipoTasa().getFormulaRegAlicuota());
					System.out.println(jep.getErrorInfo());
					double valor = jep.getValue();
					cadaAlicuotaLiquidada.setValor(Util.redondear(valor, 2));
				}
			} else {
				jep.parseExpression(pLiquidacionTasa.getTipoTasa().getFormulaRegAlicuota());
				double valor = locLiquidacionTasa.getValor() + jep.getValue();
				locLiquidacionTasa.setValor(Util.redondear(valor, 2));
			}
			
			//Teniendo el valor de la Tasa, creo la funcion que puede ser usada por los modificadores.
			ValorBasicoTasaFuncion funcionValorTasa = new ValorBasicoTasaFuncion(locLiquidacionTasa);
			jep.addFunction(ValorBasicoTasaFuncion.VALOR_BASICO_TASA, funcionValorTasa);

			for(AlicuotaLiquidada cadaAlicuotaLiquidada : locListaAlicuotasLiquidadas) {
				if(cadaAlicuotaLiquidada.getValor() == null) {
					cadaAlicuotaLiquidada.setValor(0D);
				}
				AlicuotaLiquidada locAlicuotaLiquidadaNueva = cadaAlicuotaLiquidada;
				if(listaAlicuotasLiquidadas.containsKey(cadaAlicuotaLiquidada.hashCode())) {
					locAlicuotaLiquidadaNueva = listaAlicuotasLiquidadas.get(locAlicuotaLiquidadaNueva.hashCode());
				} else {
					listaAlicuotasLiquidadas.put(locAlicuotaLiquidadaNueva.hashCode(), locAlicuotaLiquidadaNueva);
				}
				locLiquidacionTasa.addAlicuotaLiquidada(locAlicuotaLiquidadaNueva);
			}
		}

		Calendar locCalendarFechaLiquidacion = Calendar.getInstance();
		locCalendarFechaLiquidacion.setTime(pLiquidacionTasa.getFechaEmision());

		this.initicializarTasa(locLiquidacionTasa);

		locLiquidacionTasa.getListaModificadoresLiquidacion().clear();
		locLiquidacionTasa.getListaModificadoresLiquidacion().addAll(
				this.businessLiquidacionTasaLocal.calcularValoresModificadoresLiquidacion(locLiquidacionTasa, locCalendarFechaLiquidacion, this.jep));
		

		//Ya teniendo los modificadores, creo la funcion para poder llamar a sus valores
		ValorModificadorFuncion locFuncion = new ValorModificadorFuncion(locLiquidacionTasa);
		jep.addFunction(ValorModificadorFuncion.VALOR_MODIFICADOR, locFuncion);

		for(Vencimiento locNuevoVencimiento : this.crearNuevosVencimientos(pLiquidacionTasa, this.fechaNuevoVencimiento)) {
			locLiquidacionTasa.getListaVencimientos().add(locNuevoVencimiento);
		}

		this.calcularIntereses(locLiquidacionTasa, this.fechaNuevoVencimiento, pAplicarInteres, false, true);

		// //Para guardar si la Liquidacion esta vencida con respecto a la fecha de vencimiento original.
		// boolean estaVencida = this.fechaNuevoVencimiento.after(fechaVencimientoOriginal);
		//
		// for (Iterator<ModificadorLiquidacion> iterator = locLiquidacionTasa.getListaModificadoresLiquidacion()
		// .iterator(); iterator.hasNext();) {
		// ModificadorLiquidacion cadaModificador = iterator.next();
		// if (cadaModificador instanceof ModificadorLiquidacionFormula) {
		// ModificadorLiquidacionFormula cadaModificadorFormula = (ModificadorLiquidacionFormula) cadaModificador;
		// if(cadaModificadorFormula.getTipoModificador().getQuitarReliquidarVencida()
		// && estaVencida
		// && pAplicarInteres){
		// iterator.remove();
		// }
		// }
		// }
		//
		// locLiquidacionTasa.setInteres(0D);
		// locLiquidacionTasa.setRecargo(0D);
		//
		// List<ParametroValuado> listaParametrosVencimientosActualizados =
		// this.newListaParametrosVencimientosActualizados(locLiquidacionTasa);
		// for (ParametroValuado cadaParametroValuado : listaParametrosVencimientosActualizados){
		// this.jep.addVariable(cadaParametroValuado.getNombreParametro(),cadaParametroValuado.getValorParametro());
		// }
		//
		//
		// // //Calculo interés y recargo solo cuando la tasa esta vencida y el parametro asi lo indique
		// if (estaVencida && pAplicarInteres){
		// this.setInteresYRecargo(locLiquidacionTasa);
		// // }else if (pAplicarInteres && new Date().after(fechaVencimientoOriginal)){
		// // //Aplica los intereses que inicialmente no fueron aplicados.
		// // this.setInteresYRecargo(locLiquidacionTasa);
		// } else if (pAplicarInteres) {
		// //No traemos los intereses anteriores si el parametro indica que no se aplican intereses,
		// //para arreglar una reliquidacion que recibio intereses, por ejemplo.
		// locLiquidacionTasa.setInteres(locValorInteresAnterior);
		// locLiquidacionTasa.setRecargo(locValorRecargoAnterior);
		// System.out.println("No es tasa vencida no se calcula intereses");
		// System.out.println("Se setea el valor anterior de interes y recargo(int: "+ locValorInteresAnterior+", Rec: "+locValorRecargoAnterior+")");
		// }
		return locLiquidacionTasa;
	}

	/**
	 * Calcula el interés y el recargo por mora, necesita que estén seteados los valores del jep
	 * 
	 * @param locLiquidacionTasa
	 */
	public void setInteresYRecargo(LiquidacionTasa pLiquidacionTasa) {
		TipoTasa locFormula = pLiquidacionTasa.getTipoTasa();
		ConceptoPorMora locInteres = locFormula.getInteres();
		ConceptoPorMora locRecargo = locFormula.getRecargo();

		Double locValorInteres = this.calcularConceptoPorMora(locInteres);
		locValorInteres = Util.redondear(locValorInteres.doubleValue(), 2);
		// //Al valor interes le sumo el interes que la tasa ya poseia. En caso de ser una reliquidacion a reliquidar sumo los intereses viejos mas los nuevos
		// //Se pide desde crespo q los intereses se calculen desde la fecha de vencimiento de la liquidacion original, por esto a medida q se va reliquidando
		// //se van sumando los intereses anteriores mas el actual.
		// System.out.println("Interés Actual: "+pLiquidacionTasa.getInteres());
		// locValorInteres+=pLiquidacionTasa.getInteres();

		jep.addVariable(TipoParametroInteres.IMPORTE_INTERES.getNombreVariable(), locValorInteres);

		Double locValorRecargo = this.calcularConceptoPorMora(locRecargo);
		locValorRecargo = Util.redondear(locValorRecargo, 2);
		// System.out.println("Recargo Actual: "+pLiquidacionTasa.getRecargo());
		// locValorRecargo+=pLiquidacionTasa.getRecargo();

		// Double locValorInteresAnterior = pLiquidacionTasa.getInteres();
		// Double locValorRecargoAnterior = pLiquidacionTasa.getRecargo();
		//
		// pLiquidacionTasa.setInteres(locValorInteresAnterior+locValorInteres);
		// pLiquidacionTasa.setRecargo(locValorRecargoAnterior+locValorRecargo);
		// System.out.println("Nuevo Interés: "
		// +locValorInteres.toString()+" "+locValorInteresAnterior.toString()+" = "+pLiquidacionTasa.getInteres().toString());
		// System.out.println("Nuevo Recargo: "
		// +locValorRecargo.toString()+" "+locValorRecargoAnterior.toString()+" = "+pLiquidacionTasa.getRecargo().toString());
		//
		pLiquidacionTasa.setInteres(locValorInteres);
		pLiquidacionTasa.setRecargo(locValorRecargo);

	}

	private Double calcularConceptoPorMora(ConceptoPorMora pConceptoPorMora) {
		Double valorRetorno = 0d;
		if(pConceptoPorMora != null) {
			jep.parseExpression(pConceptoPorMora.getFormula());
			if(jep.getErrorInfo() != null) {
				System.err.println(jep.getErrorInfo());
				valorRetorno = 0d;
			} else {
				Double valor = jep.getValue();
				valorRetorno = Util.redondear(valor.doubleValue(), 2);
			}
		}
		return valorRetorno;
	}

	private void initicializarTasa(LiquidacionTasa pLiquidacionTasa) {
		DocGeneradorDeuda locDocGeneradorDeuda = pLiquidacionTasa.getDocGeneradorDeuda();
		Tasa locTasa = this.entityManager.find(Tasa.class, locDocGeneradorDeuda.getIdDocGeneradorDeuda());
		int i = 0;
		for(RegistroDeuda cadaRegistroDeuda : this.listaLiquidacionesTasaAnteriores) {
			LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) cadaRegistroDeuda;
			if(locLiquidacionTasa == pLiquidacionTasa) {
				this.listaLiquidacionesTasaAnteriores.get(i).setDocGeneradorDeuda(locTasa);
				break;
			}
			i++;
		}
		pLiquidacionTasa.setDocGeneradorDeuda(locTasa);
	}

	private List<Vencimiento> crearNuevosVencimientos(LiquidacionTasa pLiquidacionTasa, Date pFechaNuevoVencimiento) throws Exception {
		System.out.println("Inicio CREAR NUEVOS VENCIMIENTOS");
		List<Vencimiento> locListaNuevosVencimientos = new ArrayList<Vencimiento>();

		CuotaLiquidacion locCuota = pLiquidacionTasa.getCuotaLiquidacion();

		int index = 0;
		for(TipoVencimiento cadaTipoVencimiento : pLiquidacionTasa.getTipoTasa().getListaVencimientos()) {
			Vencimiento locVencimientoNuevo = new Vencimiento();

			if(index == 0) { // Es el primer vencimiento, no lleva monto.
				locVencimientoNuevo.setValor(0D);
			} else { // Hay que reevaluar al monto.
				if(cadaTipoVencimiento.getFormulaCalculo() != null) {
					jep.parseExpression(cadaTipoVencimiento.getFormulaCalculo());
					Double valor = jep.getValue();
					locVencimientoNuevo.setValor(valor);
				} else {
					locVencimientoNuevo.setValor(0D);
				}
			}

			locVencimientoNuevo.setNumero(index + 1);
			locVencimientoNuevo.setNombre("Vencimiento " + (index + 1));
			locVencimientoNuevo.setFecha(locCuota.getListaVencimientos().get(index).getTime());// La fecha original del vencimiento.

			index++;
			locListaNuevosVencimientos.add(locVencimientoNuevo);
			// //--------------------------------
			// System.out.println("Estado de la liquidacion");
			// System.out.println(pLiquidacionTasa.getEstado());
			// if(pLiquidacionTasa.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.VENCIDA)
			// || pLiquidacionTasa.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.NO_OPTADA)){
			// System.out.println("liquidacion vencida: "+ pLiquidacionTasa + " fechaNuevoVenc seteada: "+pFechaNuevoVencimiento);
			// locVencimiento.setFecha(pFechaNuevoVencimiento);
			// } else {
			// System.out.println("Liquidacionj no vencida: "+pLiquidacionTasa +" Cantidad de vencimientos: " + pLiquidacionTasa.getListaVencimientos().size());
			//
			// Iterator<Vencimiento> cadaVenc = pLiquidacionTasa.getListaVencimientos().iterator();
			// int subIndex=0;
			// while (cadaVenc.hasNext()){
			// Vencimiento locCadaVencimiento = cadaVenc.next();
			// // System.out.println("fecha Cada Vencimiento: ("+subIndex+") "+ locCadaVencimiento.getFecha());
			// if(index == subIndex){
			// if(pFechaNuevoVencimiento.after(locCadaVencimiento.getFecha())){
			// locVencimiento.setFecha(pFechaNuevoVencimiento);
			// System.out.println("Se seteo la fecha: "+ pFechaNuevoVencimiento);
			// }else{
			// locVencimiento.setFecha(locCadaVencimiento.getFecha());
			// System.out.println("Se seteo la fecha: "+ locCadaVencimiento.getFecha());
			// }
			// }
			// subIndex++;
			// }
			// }
			// index++;
			//
			// locVencimiento.setNumero(locListaNuevosVencimientos.size()+1);
			// DateFormat locDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
			// locVencimiento.setNombre("Vencimiento de Reliquidacion al "+locDateFormat.format(locVencimiento.getFecha()));
			// locListaNuevosVencimientos.add(locVencimiento);
		}
		System.out.println("FIN CREAR NUEVOS VENCIMIENTOS");
		return locListaNuevosVencimientos;

	}

	@SuppressWarnings({"unused", "unchecked"})
	private boolean isFeriado(Calendar pFechaVencimiento) throws Exception {
		boolean esFeriado = false;

		if(this.listaDiasFeriados == null) {
			this.listaDiasFeriados = this.businessMunicipalidadLocal.findListadoDiasFeriados(new FiltroDiaFeriado(null, pFechaVencimiento.get(Calendar.YEAR))).getListaResultados();
		}

		if(pFechaVencimiento.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			esFeriado = true;
		} else if(pFechaVencimiento.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			esFeriado = true;
		}
		if(!esFeriado) {
			// La lista de días feriados viene ordenada de manera ascendente por fecha
			Iterator iterador = this.listaDiasFeriados.iterator();
			while((iterador.hasNext()) && (!esFeriado)) {
				DiaFeriado locDiaFeriado = (DiaFeriado) iterador.next();
				if(pFechaVencimiento.getTime().equals(locDiaFeriado.getFecha())) {
					esFeriado = true;
				}
			}
		}
		return esFeriado;
	}

	/**
	 * 
	 * @param pLiquidacionTasa
	 * @return la lista actualizada de parámetros valuados
	 */
	private List<ParametroValuado> newListaParametrosVencimientosActualizados(LiquidacionTasa pLiquidacionTasa) throws Exception {
		List<ParametroValuado> locListaNuevosParametrosVencimiento = new ArrayList<ParametroValuado>();

		locListaNuevosParametrosVencimiento.add(this.getParametroImportePrimerVencimiento(pLiquidacionTasa));
		locListaNuevosParametrosVencimiento.add(this.getParametroImporteBasicoPrimerVencimiento(pLiquidacionTasa));
		locListaNuevosParametrosVencimiento.add(this.getParametroDiasDesdePrimerVencimiento(pLiquidacionTasa));
		locListaNuevosParametrosVencimiento.add(this.getParametroMesesDesdePrimerVencimiento(pLiquidacionTasa));
		locListaNuevosParametrosVencimiento.add(this.getParametroDiasDesdeInicioPeriodo(pLiquidacionTasa));
		locListaNuevosParametrosVencimiento.add(this.getParametroMesesDesdeInicioPeriodo(pLiquidacionTasa));

		return locListaNuevosParametrosVencimiento;
	}

	private ParametroValuado getParametroMesesDesdeInicioPeriodo(LiquidacionTasa pLiquidacionTasa) throws Exception {
		ParametroValuadoDouble locParametroValuado = new ParametroValuadoDouble();
		locParametroValuado.setNombreParametro(Util.getEnumNameFromString(TipoParametroVencimiento.TipoAtributoVencimiento.DIAS_DESDE_INICIO_PERIODO.toString()));
		// Puede ser que no hayan cargado los inicios de los periodos, se pone 0 como valor
		if(pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio() == null) {
			locParametroValuado.setValorParametro(0D);
		} else if(this.fechaReLiquidacion.before(pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio().getTime())) {
			locParametroValuado.setValorParametro(Integer.valueOf(
					Util.getMesesDiferencia(this.fechaReLiquidacion, pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio().getTime())).doubleValue());
		} else {
			locParametroValuado.setValorParametro(Integer.valueOf(
					Util.getMesesDiferencia(pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio().getTime(), this.fechaReLiquidacion)).doubleValue());
		}
		return locParametroValuado;
	}

	private ParametroValuado getParametroDiasDesdeInicioPeriodo(LiquidacionTasa pLiquidacionTasa) throws Exception {
		ParametroValuadoDouble locParametroValuado = new ParametroValuadoDouble();
		locParametroValuado.setNombreParametro(Util.getEnumNameFromString(TipoParametroVencimiento.TipoAtributoVencimiento.DIAS_DESDE_INICIO_PERIODO.toString()));
		// Puede ser que no hayan cargado los inicios de los periodos, se pone 0 como valor
		if(pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio() == null) {
			locParametroValuado.setValorParametro(0D);
		} else if(this.fechaReLiquidacion.before(pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio().getTime())) {
			locParametroValuado.setValorParametro(Integer.valueOf(
					Util.getDiasDiferencia(this.fechaReLiquidacion, pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio().getTime())).doubleValue());
		} else {
			locParametroValuado.setValorParametro(Integer.valueOf(
					Util.getDiasDiferencia(pLiquidacionTasa.getCuotaLiquidacion().getPeriodo().getFechaInicio().getTime(), this.fechaReLiquidacion)).doubleValue());
		}
		return locParametroValuado;
	}

	private ParametroValuado getParametroMesesDesdePrimerVencimiento(LiquidacionTasa pLiquidacionTasa) throws Exception {
		ParametroValuadoDouble locParametroValuado = new ParametroValuadoDouble();
		locParametroValuado.setNombreParametro(Util.getEnumNameFromString(TipoParametroVencimiento.TipoAtributoVencimiento.MESES_DESDE_PRIMER_VENCIMIENTO.toString()));
		if(this.fechaReLiquidacion.after(this.fechaVencimientoOriginal)) {
			locParametroValuado.setValorParametro(Integer.valueOf(Util.getMesesDiferencia(this.fechaVencimientoOriginal, this.fechaReLiquidacion)).doubleValue());
		} else {
			locParametroValuado.setValorParametro(0D);
		}
		return locParametroValuado;
	}

	private ParametroValuado getParametroDiasDesdePrimerVencimiento(LiquidacionTasa pLiquidacionTasa) throws Exception {
		ParametroValuadoDouble locParametroValuado = new ParametroValuadoDouble();
		locParametroValuado.setNombreParametro(Util.getEnumNameFromString(TipoParametroVencimiento.TipoAtributoVencimiento.DIAS_DESDE_PRIMER_VENCIMIENTO.toString()));
		if(this.fechaReLiquidacion.after(this.fechaVencimientoOriginal)) {
			locParametroValuado.setValorParametro(Integer.valueOf(Util.getDiasDiferencia(this.fechaVencimientoOriginal, this.fechaReLiquidacion)).doubleValue());
		} else {
			locParametroValuado.setValorParametro(0D);
		}
		// if(this.fechaReLiquidacion.before(pLiquidacionTasa.getListaVencimientos().first().getFecha())){
		// locParametroValuado.setValorParametro(Integer.valueOf(Util.getDiasDiferencia(this.fechaReLiquidacion,this.fechaVencimientoOriginal)).doubleValue());
		// System.out.println("BEFORE");
		// }
		// else{
		// locParametroValuado.setValorParametro(Integer.valueOf(Util.getDiasDiferencia(this.fechaVencimientoOriginal,this.fechaReLiquidacion)).doubleValue());
		// System.out.println("ELSE");
		// }
		// System.out.println("dias diferencia: "+locParametroValuado.getValorParametro());
		// if(this.fechaNuevoVencimiento.before(this.fechaVencimientoOriginal)){
		// locParametroValuado.setValorParametro(Integer.valueOf(Util.getDiasDiferencia(this.fechaReLiquidacion,pLiquidacionTasa.getListaVencimientos().first().getFecha())).doubleValue());
		// }
		// else{
		// locParametroValuado.setValorParametro(Integer.valueOf(Util.getDiasDiferencia(this.fechaVencimientoOriginal,this.fechaReLiquidacion)).doubleValue());
		// }
		// System.out.println("dias diferencia 2: "+locParametroValuado.getValorParametro());
		return locParametroValuado;
	}

	/**
	 * @return el valor del parámetro del primer vecimiento
	 */
	private ParametroValuado getParametroImportePrimerVencimiento(LiquidacionTasa pLiquidacionTasa) {
		ParametroValuadoDouble locParametroValuado = new ParametroValuadoDouble();
		// El valor de los vencimientos es 0, se debe tomar de las liquidaciones
		// locParametroValuado.setValorParametro(pLiquidacionTasa.getListaVencimientos().first().getValor().doubleValue());
		locParametroValuado.setValorParametro(pLiquidacionTasa.getMonto());
		locParametroValuado.setNombreParametro(Util.getEnumNameFromString(TipoParametroVencimiento.TipoAtributoVencimiento.IMPORTE_PRIMER_VENCIMIENTO.toString()));
		return locParametroValuado;
	}
	
	/**
	 * @return el valor básico de la tasa.
	 */
	private ParametroValuado getParametroImporteBasicoPrimerVencimiento(LiquidacionTasa pLiquidacionTasa) {
		ParametroValuadoDouble locParametroValuado = new ParametroValuadoDouble();
		// El valor de los vencimientos es 0, se debe tomar de las liquidaciones
		// locParametroValuado.setValorParametro(pLiquidacionTasa.getListaVencimientos().first().getValor().doubleValue());
		locParametroValuado.setValorParametro(pLiquidacionTasa.getValor());
		locParametroValuado.setNombreParametro(Util.getEnumNameFromString(TipoParametroVencimiento.TipoAtributoVencimiento.IMPORTE_BASICO_PRIMER_VENCIMIENTO.toString()));
		return locParametroValuado;
	}

	/**
	 * Valida las pre condiciones del método reliquidar
	 * 
	 * @param pLiquidacionTasa
	 * @param pFechaReLiquidacion
	 * @throws TrascenderException
	 */
	private void validarPreCondicionesReliquidacion(LiquidacionTasa pLiquidacionTasa, Date pFechaReLiquidacion) throws TrascenderException {
		if(pFechaReLiquidacion.before(pLiquidacionTasa.getFechaVencimiento())) {
			throw new SaicException(29);
		}
	}

	@Override
	public LiquidacionTasa calcularIntereses(LiquidacionTasa pLiquidacionTasa, Date pFecha, boolean pAplicarInteres, boolean guardarCambios, boolean duranteReliquidacion)
			throws Exception {

		// Si es durante una reliquidacion, estos datos ya estan levantados.
		if(!duranteReliquidacion) {
			pLiquidacionTasa = entityManager.merge(pLiquidacionTasa);
			// Se usa "fechaReLiquidacion" como fecha para el calculo del interes.
			this.fechaReLiquidacion = pFecha;
			this.fechaNuevoVencimiento = pFecha;
			// Buscar fecha de vencimiento original.
			// this.fechaVencimientoOriginal = (Date)this.entityManager.createNativeQuery("SELECT fecha FROM p_fecha_vencimiento_original(:id_deuda)")
			// .setParameter("id_deuda", pLiquidacionTasa.getIdRegistroDeuda())
			// .getSingleResult();
			this.fechaVencimientoOriginal = pLiquidacionTasa.getCuotaLiquidacion().getListaVencimientos().get(0).getTime();
			for(Iterator<Vencimiento> iterator = pLiquidacionTasa.getListaVencimientos().iterator(); iterator.hasNext();) {
				iterator.next();
				iterator.remove();
			}
			for(Vencimiento cadaVencimiento : this.crearNuevosVencimientos(pLiquidacionTasa, pFecha)) {
				pLiquidacionTasa.getListaVencimientos().add(cadaVencimiento);
			}
		}
		boolean estaVencida = pLiquidacionTasa.isVencida(fechaNuevoVencimiento);
		// Primero quitar los modificadores que se pierden por vencida
		for(Iterator<ModificadorLiquidacion> iterator = pLiquidacionTasa.getListaModificadoresLiquidacion().iterator(); iterator.hasNext();) {
			ModificadorLiquidacion cadaModificador = iterator.next();
			if(cadaModificador instanceof ModificadorLiquidacionFormula) {
				ModificadorLiquidacionFormula cadaModificadorFormula = (ModificadorLiquidacionFormula) cadaModificador;
				if(cadaModificadorFormula.getTipoModificador().getQuitarReliquidarVencida() && estaVencida && pAplicarInteres) {
					iterator.remove();
				}
			}
		}
		// El motor JEP ya fue inicializado por el proceso de la reliquidacion
		if(!duranteReliquidacion) {
			this.jep = MotorFormulas.initializeJEP();
			//Ya teniendo los modificadores, creo la funcion para poder llamar a sus valores
			ValorModificadorFuncion locFuncion = new ValorModificadorFuncion(pLiquidacionTasa);
			jep.addFunction(ValorModificadorFuncion.VALOR_MODIFICADOR, locFuncion);
		}
		// Agregamos a JEP los parametros valuados de la liquidacion.
		for(ParametroValuado cadaParametroValuado : pLiquidacionTasa.getListaParametrosValuados()) {
			jep.addVariable(cadaParametroValuado.getNombreParametro(), cadaParametroValuado.getValorParametro());
		}

		Double locValorInteresAnterior = pLiquidacionTasa.getInteres();
		Double locValorRecargoAnterior = pLiquidacionTasa.getRecargo();

		pLiquidacionTasa.setInteres(0D);
		pLiquidacionTasa.setRecargo(0D);

		// Calculamos y agregamos los parametros de vencimiento.
		List<ParametroValuado> listaParametrosVencimientosActualizados = this.newListaParametrosVencimientosActualizados(pLiquidacionTasa);
		for(ParametroValuado cadaParametroValuado : listaParametrosVencimientosActualizados) {
			this.jep.addVariable(cadaParametroValuado.getNombreParametro(), cadaParametroValuado.getValorParametro());
		}

		// //Calculo interés y recargo solo cuando la tasa esta vencida y el parametro asi lo indique
		if(estaVencida && pAplicarInteres) {
			this.setInteresYRecargo(pLiquidacionTasa);
		} else if(pAplicarInteres) {
			// No traemos los intereses anteriores si el parametro indica que no se aplican intereses,
			// para arreglar una reliquidacion que recibio intereses, por ejemplo.
			pLiquidacionTasa.setInteres(locValorInteresAnterior);
			pLiquidacionTasa.setRecargo(locValorRecargoAnterior);
		}

		pLiquidacionTasa.addNuevoVencimientoPorActualizacion(fechaNuevoVencimiento);
		pLiquidacionTasa.setEstado(EstadoRegistroDeuda.VIGENTE);
		pLiquidacionTasa.recalcularMonto();
		// if (!guardarCambios && !duranteReliquidacion) {
		// entityManager.clear();
		// }
		
		//Si es durante la reliqudacion, no generamos el Log, pues lo hara el proceso de la reliquidacion.
		if (!duranteReliquidacion) {
			this.businessLiquidacionTasaLocal.generarLogLiquidacion(pLiquidacionTasa, SecurityMgr.getInstance().getUsuario(llave), LogLiquidacion.Evento.ACTUALIZO, null);
			//Hacemos toString para quitar un par de Lazys, solo si no es durante la reliquidacion.
			pLiquidacionTasa.getPersona().toString();
		}

		return pLiquidacionTasa;
	}

	public void setLlave(long llave) {
		this.llave = llave;
	}

	@Override
	public LiquidacionTasa notificar(LiquidacionTasa pLiquidacionTasa,
			Date fechaNotificacion, Date fechaApremio, Usuario pUsuario, String comentario) {
		
		if (fechaNotificacion != null) {
			pLiquidacionTasa.setFechaNotificacion(fechaNotificacion);
			businessLiquidacionTasaLocal.generarLogLiquidacion(pLiquidacionTasa, pUsuario, 
					LogLiquidacion.Evento.NOTIFICO, comentario);
		}
		if (fechaApremio != null) {
			pLiquidacionTasa.setFechaApremio(fechaApremio);
			businessLiquidacionTasaLocal.generarLogLiquidacion(pLiquidacionTasa, pUsuario, 
					LogLiquidacion.Evento.APREMIO, comentario);
		}
		//Porque a veces lo pasa a VENCIDA y no da.
		if (pLiquidacionTasa.getEstado().equals(EstadoRegistroDeuda.VENCIDA)) {
			pLiquidacionTasa.setEstado(EstadoRegistroDeuda.VIGENTE);
		}
		return this.entityManager.merge(pLiquidacionTasa);
	}
	
	@Override
	public void notificar(List<LiquidacionTasa> listaLiquidaciones,
			Date fechaNotificacion, Date fechaApremio, Usuario pUsuario, String comentario) {
		if (fechaNotificacion != null) {
			Query locQuery = entityManager
					.createQuery("UPDATE LiquidacionTasa liq SET liq.fechaNotificacion = :fecha " +
							"WHERE liq.idRegistroDeuda IN (:lista)");
			locQuery.setParameter("fecha", fechaNotificacion);
			List<Long> listaIds = Util.getListaPropiedad(listaLiquidaciones, "idRegistroDeuda");
			locQuery.setParameter("lista", listaIds);
			locQuery.executeUpdate();
			for (LiquidacionTasa cadaLiquidacion : listaLiquidaciones) {
				businessLiquidacionTasaLocal.generarLogLiquidacion(cadaLiquidacion, pUsuario, 
						LogLiquidacion.Evento.NOTIFICO, comentario);
			}
		}
		if (fechaApremio != null) {
			Query locQuery = entityManager
					.createQuery("UPDATE LiquidacionTasa liq SET fechaApremio = :fecha " +
							"WHERE liq IN (:lista)");
			locQuery.setParameter("fecha", fechaNotificacion);
			locQuery.setParameter("lista", listaLiquidaciones);
			locQuery.executeUpdate();
			for (LiquidacionTasa cadaLiquidacion : listaLiquidaciones) {
				businessLiquidacionTasaLocal.generarLogLiquidacion(cadaLiquidacion, pUsuario, 
						LogLiquidacion.Evento.APREMIO, comentario);
			}
		}
	}

}
