
package com.trascender.saic.recurso.persistent;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.TipoModificador;
import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.saic.system.ejb.SystemLiquidacionTasaBean;
import com.trascender.saic.util.VencimientoComparator;

@Entity
@Table(name = "LIQUIDACION_TASA")
@PrimaryKeyJoinColumn(name = "ID_REGISTRO_DEUDA")
@Cacheable
public class LiquidacionTasa extends RegistroDeuda implements Cloneable {

	/**
	 * 
	 */
	public static final long serialVersionUID = -3440227850979583798L;
	public static final long codigoTGI = serialVersionUID;
	public static final long codigoSHPS = serialVersionUID + 1;
	public static final long codigoOSP = serialVersionUID + 2;
	public static final long codigoPFO = serialVersionUID + 3;
	public static final long codigoTasaMenor = serialVersionUID + 4;

	public static final long codigoEstadoCuenta = serialVersionUID + 5;

	public static final long codigoTasasUnificadas = serialVersionUID + 6;

	public static final long codigoCementerio = serialVersionUID + 7;
	public static final long codigoAutomotor = serialVersionUID + 8;
	public static final long codigoArrendamiento = serialVersionUID + 9;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CUOTA_LIQUIDACION", nullable = true)
	private CuotaLiquidacion cuotaLiquidacion;

	@Column(name = "OBSERVACIONES_PIE_PAGINA")
	private String observacionesPiePagina;

	// corresponde a la fórmula que se uso para liquidar la tasa
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "ID_TIPO_TASA", nullable = false)
	private TipoTasa tipoTasa;

	@Column(nullable = false)
	private Double valor = 0D;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
	@JoinTable(name = "RELA_PAR_VAL_LIQ_T", joinColumns = @JoinColumn(name = "ID_REGISTRO_DEUDA"), inverseJoinColumns = @JoinColumn(name = "ID_PARAMETRO_VALUADO"))
	private Set<ParametroValuado> listaParametrosValuados = new HashSet<ParametroValuado>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
	@JoinTable(name = "RELA_LIQ_T_ALIC_LIQ", joinColumns = @JoinColumn(name = "ID_REGISTRO_DEUDA"), inverseJoinColumns = @JoinColumn(name = "ID_ALICUOTA_LIQUIDADA"))
	private Set<AlicuotaLiquidada> listaAlicuotasLiquidadas = new HashSet<AlicuotaLiquidada>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
	@JoinTable(name = "RELA_MODIF_LIQ_LIQ_T", joinColumns = @JoinColumn(name = "ID_REGISTRO_DEUDA"), inverseJoinColumns = @JoinColumn(name = "ID_MODIFICADOR_LIQUIDACION"))
	private Set<ModificadorLiquidacion> listaModificadoresLiquidacion = new HashSet<ModificadorLiquidacion>();

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
	@JoinTable(name = "RELA_VENC_LIQ_T", joinColumns = @JoinColumn(name = "ID_REGISTRO_DEUDA"), inverseJoinColumns = @JoinColumn(name = "ID_VENCIMIENTO"))
	@Sort(type = SortType.COMPARATOR, comparator = VencimientoComparator.class)
	private SortedSet<Vencimiento> listaVencimientos = new TreeSet<Vencimiento>(new VencimientoComparator());

	@Column(name = "NUMERO_CUOTA")
	private Integer numeroCuota = 1;

	private Double interes;

	private Double recargo;
	
	@Column(name = "MONTO_CALCULADO")
	private Double montoCalculado;
	
	@Column(name = "FECHA_NOTIFICACION")
	private Date fechaNotificacion;
	
	@Column(name = "FECHA_APREMIO")
	private Date fechaApremio;
	
	public boolean tieneNotificacion() {
		return fechaNotificacion != null;
	}
	
	public boolean tieneApremio() {
		return fechaApremio != null;
	}
	
	public Date getFechaNotificacion() {
		return fechaNotificacion;
	}

	public void setFechaNotificacion(Date fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}

	public Date getFechaApremio() {
		return fechaApremio;
	}

	public void setFechaApremio(Date fechaApremio) {
		this.fechaApremio = fechaApremio;
	}

	/**
	 * Verifica si la liquidación está vencida para la fecha de la emisión
	 * 
	 * @return
	 */
	public boolean isVencida() {
		return this.isVencida(this.getFechaEmision());
	}

	/**
	 * Verifica si la liquidacion está vencida a la fecha recibida.
	 * 
	 * @param pFecha
	 * @return
	 */
	public boolean isVencida(Date pFecha) {
		Vencimiento locVencimiento = this.getListaVencimientos().last();
		return Util.isFechaAfterNoTima(pFecha, locVencimiento.getFecha());
	}

	/**
	 * 
	 * @return la fecha del primer vencimiento
	 */
	public Date getFechaPrimerVencimiento() {
		return this.getListaVencimientos().first().getFecha();
	}

	/**
	 * @hibernate.property column = "RECARGO"
	 */
	@Override
	public Double getRecargo() {
		if(this.recargo == null || this.recargo.isNaN()) {
			this.recargo = 0d;
		}
		return recargo;
	}

	public void setRecargo(Double recargo) {
		this.recargo = recargo;
	}

	@Override
	public Double getInteres() {
		if(this.interes == null || this.interes.isNaN()) {
			this.interes = 0d;
		}
		return interes;
	}

	public void addAlicuotaLiquidada(AlicuotaLiquidada pAlicuotaLiquidada) {
		this.listaAlicuotasLiquidadas.add(pAlicuotaLiquidada);
	}

	public Set<AlicuotaLiquidada> getListaAlicuotasLiquidadas() {
		return listaAlicuotasLiquidadas;
	}

	public void setListaAlicuotasLiquidadas(Set<AlicuotaLiquidada> listaAlicuotasLiquidadas) {
		this.listaAlicuotasLiquidadas = listaAlicuotasLiquidadas;
	}

	public void setInteres(Double interes) {
		this.interes = interes;
	}

	public SortedSet<Vencimiento> getListaVencimientos() {
		return listaVencimientos;
	}

	public void setListaVencimientos(SortedSet<Vencimiento> listaVencimientos) {
		this.listaVencimientos = listaVencimientos;
	}

	public Set<ModificadorLiquidacion> getListaModificadoresLiquidacion() {
		return listaModificadoresLiquidacion;
	}

	public void setListaModificadoresLiquidacion(Set<ModificadorLiquidacion> listaModificadoresLiquidacion) {
		this.listaModificadoresLiquidacion = listaModificadoresLiquidacion;
	}

	public Set<ModificadorLiquidacionFormula> getListaModificadoresLiquidacionFormula() {
		Set<ModificadorLiquidacionFormula> locLista = new HashSet<ModificadorLiquidacionFormula>();
		for(ModificadorLiquidacion cadaModificador : this.listaModificadoresLiquidacion) {
			if(cadaModificador instanceof ModificadorLiquidacionFormula) {
				locLista.add((ModificadorLiquidacionFormula) cadaModificador);
			}
		}
		return locLista;
	}

	public Set<ModificadorLiquidacionManual> getListaModificadoresLiquidacionManual() {
		Set<ModificadorLiquidacionManual> locLista = new HashSet<ModificadorLiquidacionManual>();
		for(ModificadorLiquidacion cadaModificador : this.listaModificadoresLiquidacion) {
			if(cadaModificador instanceof ModificadorLiquidacionManual) {
				locLista.add((ModificadorLiquidacionManual) cadaModificador);
			}
		}
		return locLista;
	}

	public Set<ParametroValuado> getListaParametrosValuados() {
		return listaParametrosValuados;
	}

	public void setListaParametrosValuados(Set<ParametroValuado> listaParametrosValuados) {
		this.listaParametrosValuados = listaParametrosValuados;
	}

	public TipoTasa getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(TipoTasa pTipoTasa) {
		this.tipoTasa = pTipoTasa;
	}

	public String getObservacionesPiePagina() {
		return observacionesPiePagina;
	}

	public void setObservacionesPiePagina(String observacionesPiePagina) {
		this.observacionesPiePagina = observacionesPiePagina;
	}

	@Override
	public CuotaLiquidacion getCuotaLiquidacion() {
		return cuotaLiquidacion;
	}

	public void setCuotaLiquidacion(CuotaLiquidacion cuotaLiquidacion) {
		this.cuotaLiquidacion = cuotaLiquidacion;
	}

	/**
	 * Es el valor de la liquidación sin los modificadores
	 * 
	 */
	public Double getValor() {
		Double locValor = 0D;
		for(AlicuotaLiquidada cadaAlicuotaLiquidada : this.listaAlicuotasLiquidadas) {
			locValor += cadaAlicuotaLiquidada.getValor();
		}
		return this.valor + locValor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	/**
	 * Devuelve el valor puro de la tasa menos los modificadores de descuento.
	 * 
	 * @return
	 */
	public Double getValorTasaConDescuentos() {
		Double locValor = this.getValor();
		for(ModificadorLiquidacion locModificador : this.listaModificadoresLiquidacion) {
			if(locModificador.getValorModificador() < 0) {
				locValor += locModificador.getValorModificador();
			}
		}
		return locValor;
	}

	public Double getSumaRecargosManuales() {
		Double locValor = 0D;
		for(ModificadorLiquidacion cadaModificador : this.listaModificadoresLiquidacion) {
			if(cadaModificador instanceof ModificadorLiquidacionManual && cadaModificador.getValorModificador() > 0) {
				locValor = locValor + cadaModificador.getValorModificador();
			}
		}
		return locValor;
	}

	/**
	 * 
	 * @return valor de la suma de todos los modificadores sobre la tasa
	 */
	public Double getValorModificadoresSobreSaldoNeto() {
		Double valor = 0d;
		for(ModificadorLiquidacion locModificadorLiquidacion : this.getListaModificadoresLiquidacion()) {
			if(locModificadorLiquidacion.isSobreSaldoNeto()) {
				valor += locModificadorLiquidacion.getValorModificador();
			}
		}
		return valor;

	}

	/**
	 * 
	 * @return retorna el valor de la suma de los modificadores sobre el sub_total
	 */
	public Double getValorModificadoresSobreSubTotal() {
		Double valor = 0d;
		for(ModificadorLiquidacion locModificadorLiquidacion : this.getListaModificadoresLiquidacion()) {
			if(!locModificadorLiquidacion.isSobreSaldoNeto()) {
				valor += locModificadorLiquidacion.getValorModificador();
			}
		}
		return valor;
	}

	/**
	 * 
	 * @return obtiene el valor total de la liquidación, mas el monto de las alicuotas liquidadas, más los modificadores del tipo deuda o los de liquidacion
	 *         (sin tipo),
	 */
	public Double getValorTotal() {
		Double valor = 0D;
		valor += this.getValor();
		for(ModificadorLiquidacion locModificadorLiquidacion : this.getListaModificadoresLiquidacion()) {
			if(locModificadorLiquidacion.getEnumTipoModificador() != null) {
				if(locModificadorLiquidacion.getEnumTipoModificador().equals(TipoModificador.EnumTipoModificador.DEUDA)) {
					valor += locModificadorLiquidacion.getValorModificador();
				}
			}
			// significa que es un modificador de liquidación, por eso no tiene
			// tipo
			else {
				valor += locModificadorLiquidacion.getValorModificador();
			}
		}
		return valor;
	}

	@Override
	public String toString() {
		// this.getDocGeneradorDeuda().toString();
		this.cuotaLiquidacion.toString();
		return (this.getNombre() != null) ? this.getNombre() : "";
	}

	@Override
	public LiquidacionTasa clone() throws CloneNotSupportedException {
		if(!this.getClass().equals(LiquidacionTasa.class)) {
			throw new CloneNotSupportedException("Solo es soportada la clonación de clases recuperadas en su totalidad");
		}
		LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) super.clone();
		locLiquidacionTasa.setIdRegistroDeuda(-1);
		locLiquidacionTasa.setListaParametrosValuados(this.listaParametrosValuados);
		locLiquidacionTasa.setListaModificadoresLiquidacion(this.listaModificadoresLiquidacion);
		locLiquidacionTasa.setListaVencimientos(this.listaVencimientos);
		return locLiquidacionTasa;
	}

	public Integer getNumeroCuota() {
		return numeroCuota;
	}

	public void setNumeroCuota(Integer numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	/**
	 * @return obtiene el valor total + intereses + recargos + multas aplicadas (tiene que pagar todo)
	 */
	@Override
	public Double getMonto() {
		Double locMontoExencion = this.getMontoExencion();
		Double locMonto = this.getValorTotal() + this.getInteres() + this.getRecargo() + this.getMontoMultas() + this.getMontoVencimientoActual();
		return ((locMonto != null && locMonto > 0D) ? locMonto : 0D) - ((locMontoExencion != null && locMontoExencion > 0D) ? locMontoExencion : 0D);
	}

	public void recalcularMonto() {
		montoCalculado = this.getMonto() - this.getMontoVencimientoActual();
	}

	public Double getMontoCalculado() {
		return montoCalculado + this.getMontoVencimientoActual();
	}

	public void setMontoCalculado(Double montoCalculado) {
		this.montoCalculado = montoCalculado;
	}

	@Override
	public String getNombre() {
		// return this.getDocGeneradorDeuda().getNombre();
		return this.getCuotaLiquidacion().getPeriodo().getCalendario().getNombre() + ", " + this.getCuotaLiquidacion().getPeriodo().getNumero() + " de "
				+ this.getCuotaLiquidacion().getPeriodo().getCalendario().getListaPeriodos().size();
	}

	@Override
	public Persona getPersona() {
		return this.getDocGeneradorDeuda().getObligacion().getPersona();
	}

	/**
	 * crea una liquidacion a partir de otra liquidación, copia todos los atributos pero no copia las colecciones ni relaciones con otras clases
	 * 
	 * @param pLiquidacionTasa
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public static LiquidacionTasa getInstance(LiquidacionTasa pLiquidacionTasa) throws CloneNotSupportedException {
		LiquidacionTasa locLiquidacionTasa = new LiquidacionTasa();

		locLiquidacionTasa.setNumeroRegistroDeuda(pLiquidacionTasa.getNumeroRegistroDeuda());
		locLiquidacionTasa.setNumeroCuota(pLiquidacionTasa.getNumeroCuota());

		locLiquidacionTasa.setFechaEmision(pLiquidacionTasa.getFechaEmision());
		locLiquidacionTasa.setCuotaLiquidacion(pLiquidacionTasa.getCuotaLiquidacion());
		locLiquidacionTasa.setTipoTasa(pLiquidacionTasa.getTipoTasa());
		locLiquidacionTasa.setValor(pLiquidacionTasa.getValor());
		locLiquidacionTasa.setInteres(pLiquidacionTasa.getInteres());
		locLiquidacionTasa.setRecargo(pLiquidacionTasa.getRecargo());
		locLiquidacionTasa.setDocGeneradorDeuda(pLiquidacionTasa.getDocGeneradorDeuda());
		locLiquidacionTasa.getDocGeneradorDeuda().setObligacion(pLiquidacionTasa.getDocGeneradorDeuda().getObligacion());
		
		//Fechas de Notificacion y Apremio
		locLiquidacionTasa.setFechaNotificacion(pLiquidacionTasa.getFechaNotificacion());
		locLiquidacionTasa.setFechaApremio(pLiquidacionTasa.getFechaApremio());

		return locLiquidacionTasa;

	}

	public static LiquidacionTasa copy(LiquidacionTasa pLiquidacionTasa) throws CloneNotSupportedException {
		LiquidacionTasa locLiquidacionTasa = new LiquidacionTasa();

		// Interesa clonar todo excepto lo siguiente
		locLiquidacionTasa.setNumeroRegistroDeuda(pLiquidacionTasa.getNumeroRegistroDeuda());
		locLiquidacionTasa.setNumeroCuota(pLiquidacionTasa.getNumeroCuota());

		locLiquidacionTasa.setFechaEmision(pLiquidacionTasa.getFechaEmision());
		locLiquidacionTasa.setCuotaLiquidacion(pLiquidacionTasa.getCuotaLiquidacion());
		locLiquidacionTasa.setTipoTasa(pLiquidacionTasa.getTipoTasa());
		locLiquidacionTasa.setValor(pLiquidacionTasa.getValor());
		locLiquidacionTasa.setInteres(pLiquidacionTasa.getInteres());
		locLiquidacionTasa.setRecargo(pLiquidacionTasa.getRecargo());
		locLiquidacionTasa.setDocGeneradorDeuda(pLiquidacionTasa.getDocGeneradorDeuda());
		locLiquidacionTasa.getDocGeneradorDeuda().setObligacion(pLiquidacionTasa.getDocGeneradorDeuda().getObligacion());
		locLiquidacionTasa.setEstado(pLiquidacionTasa.getEstado());
		locLiquidacionTasa.setEstadoAnterior(pLiquidacionTasa.getEstadoAnterior());

		locLiquidacionTasa.setListaModificadoresLiquidacion(new HashSet<ModificadorLiquidacion>());
		locLiquidacionTasa.getListaModificadoresLiquidacion().addAll(pLiquidacionTasa.getListaModificadoresLiquidacion());

		locLiquidacionTasa.setListaParametrosValuados(new HashSet<ParametroValuado>());
		locLiquidacionTasa.getListaParametrosValuados().addAll(pLiquidacionTasa.getListaParametrosValuados());

		locLiquidacionTasa.setListaVencimientos(new TreeSet<Vencimiento>(new VencimientoComparator()));
		locLiquidacionTasa.getListaVencimientos().addAll(pLiquidacionTasa.getListaVencimientos());

		return locLiquidacionTasa;

	}

	/**
	 * 
	 * @return retorna el valor de las multas aplicadas a la liquidación
	 */
	public Double getMontoMultas() {
		Double valor = 0d;
		for(ModificadorLiquidacion locMoficadorLiquidacion : this.getListaModificadoresLiquidacion()) {
			if(locMoficadorLiquidacion.getEnumTipoModificador() != null && locMoficadorLiquidacion.equals(TipoModificador.EnumTipoModificador.MULTA_AUTOMATICA)) {
				// los valores de los modificadores se basan en el valor
				// obtenido de la fórmula, así que son todos en pesos
				valor += locMoficadorLiquidacion.getValorModificador();
			}
		}
		return valor;
	}

	/**
	 * 
	 * @return La fecha del vencimiento que corresponda a la fecha actual o la mayor si estuviera vencida.
	 * 
	 */
	@Override
	public Date getFechaVencimiento() {
		Vencimiento locVencimientoActual = this.getVencimientoActual();
		return locVencimientoActual != null ? locVencimientoActual.getFecha() : listaVencimientos.last().getFecha();
	}
	
	public Date getFechaVencimientoMayor(){
		return listaVencimientos.last().getFecha();
	}

	/**
	 * @return Si la deuda esta vencida, null <br/>
	 *         Si la deuda esta vigente, el vencimiento correspondiente.<br/>
	 *         Si la deuda esta saldada, el vencimiento correspondiente a la fecha de cancelacion.
	 */
	public Vencimiento getVencimientoActual() {
		Date locFechaActual = this.getRegistroCancelacion() != null ? this.getRegistroCancelacion().getFechaCancelacion() : Calendar.getInstance().getTime();
		Iterator<Vencimiento> locCadaVencimiento = this.getListaVencimientos().iterator();
		while(locCadaVencimiento.hasNext()) {
			Vencimiento locVencimiento = locCadaVencimiento.next();
			if(Util.isFechaAfterNoTima(locVencimiento.getFecha(), locFechaActual) || Util.isFechaEqualsNoTima(locVencimiento.getFecha(), locFechaActual)) {
				return locVencimiento;
			}
		}
		return null;
	}

	/**
	 * @return El monto del vencimiento actual o 0 (cero) si esta vencida.
	 */
	public Double getMontoVencimientoActual() {
		Vencimiento locVencimientoActual = this.getVencimientoActual();
		return locVencimientoActual != null ? locVencimientoActual.getValor() : 0D;
	}

	/**
	 * @return El vencimiento segun el numero de orden en la lista.
	 */
	public Vencimiento getVencimientoPorNumero(Integer numero) {
		Integer index = 0;
		for(Iterator<Vencimiento> iterator = listaVencimientos.iterator(); iterator.hasNext();) {
			Vencimiento locVencimiento = iterator.next();
			if(index.equals(numero)) {
				return locVencimiento;
			}
			index++;
		}
		return null;
	}

	/**
	 * Genera un nuevo vencimiento con la fecha recibida. Se entiende que es un vencimiento que actualiza la boleta por lo que no tiene monto. Se eliminan todos
	 * los vencimientos posteriores a la fecha recibida.
	 * 
	 * @param fechaNuevoVencimento
	 */
	public void addNuevoVencimientoPorActualizacion(Date fechaNuevoVencimento) {
		for(Iterator<Vencimiento> iterator = listaVencimientos.iterator(); iterator.hasNext();) {
			Vencimiento cadaVencimiento = iterator.next();
			// Si la fecha de cada vencimiento es mayor o igual a la nueva fecha, la borramos.
			if(!Util.isFechaAfterNoTima(fechaNuevoVencimento, cadaVencimiento.getFecha())) {
				iterator.remove();
			}
		}
		Vencimiento locVencimiento = new Vencimiento();
		locVencimiento.setValor(0d);
		locVencimiento.setFecha(fechaNuevoVencimento);
		locVencimiento.setNombre("Vencimiento por actualización");
		this.listaVencimientos.add(locVencimiento);
	}

	@Override
	public Double getMultas() {
		return this.getMontoMultas();
	}

	public void add(ParametroValuado locParametroValuado) {
		if(locParametroValuado.getLiquidacionTasa() != null) {
			locParametroValuado.getLiquidacionTasa().getListaParametrosValuados().remove(locParametroValuado);
		}
		this.getListaParametrosValuados().add(locParametroValuado);
		locParametroValuado.setLiquidacionTasa(this);
	}

	public String getCuotaLiquidada() {
		return this.getCuotaLiquidacion().getNumero() + "/" + this.getCuotaLiquidacion().getPeriodo().getListaCuotas().size();
	}

	public String getStringFormula() {
		return this.getTipoTasa().getNombre() + "[Vigente Desde] " + Util.getString(this.getTipoTasa().getFechaVigenciaDesde()) + " - [Vigente Hasta] "
				+ Util.getString(this.getTipoTasa().getFechaVigenciaHasta());
	}

	public String getStringParametrosValuados() {
		String locStringParametrosValuados = "";
		for(ParametroValuado cadaParametroValuado : this.getListaParametrosValuados()) {
			locStringParametrosValuados += "[Nombre] " + cadaParametroValuado.getNombreParametro() + " - [Valor] " + cadaParametroValuado.getValorParametro() + "\n";
		}
		return locStringParametrosValuados;
	}

	public String getStringParametrosValuadosAlicuota() {
		String locStringResultado = "";
		List<ParametroValuadoAlicuota> locLista = new ArrayList<ParametroValuadoAlicuota>();
		for(AlicuotaLiquidada cadaAlicuotaLiquidada : this.listaAlicuotasLiquidadas) {
			locLista.addAll(cadaAlicuotaLiquidada.getListaParametrosValuados());
		}

		Collections.sort(locLista, new Comparator<ParametroValuadoAlicuota>() {
			@Override
			public int compare(ParametroValuadoAlicuota o1, ParametroValuadoAlicuota o2) {
				return Util.reemplazarAcentos(o1.getNombreCompleto()).compareToIgnoreCase(Util.reemplazarAcentos(o2.getNombreCompleto()));
			}
		});
		for(ParametroValuadoAlicuota cadaParamaetro : locLista) {
			locStringResultado += "[Nombre]" + cadaParamaetro.getNombreCompleto() + "[Valor]" + cadaParamaetro.getValorParametro() + "\n";
		}
		return locStringResultado;
	}

	public String getStringAlicuotasLiquidadas() {
		List<AlicuotaLiquidada> locLista = new ArrayList<AlicuotaLiquidada>(this.listaAlicuotasLiquidadas);
		Collections.sort(locLista, new Comparator<AlicuotaLiquidada>() {
			@Override
			public int compare(AlicuotaLiquidada o1, AlicuotaLiquidada o2) {
				return Util.reemplazarAcentos(o1.getRegAlicuota().getNombre()).compareToIgnoreCase(Util.reemplazarAcentos(o2.getRegAlicuota().getNombre()));
			}
		});
		String resultado = "";
		for(AlicuotaLiquidada cadaAlicuotaLiquidada : locLista) {
			resultado += "[Nombre]" + cadaAlicuotaLiquidada.getRegAlicuota() + "[Valor]" + cadaAlicuotaLiquidada.getValor() + "\n";
		}
		return resultado;
	}

	public String getStringModificadoresLiquidacion() {
		String locStringModificadoresLiquidacion = "";
		for(ModificadorLiquidacion cadaModificadorLiquidacion : this.getListaModificadoresLiquidacion()) {
			locStringModificadoresLiquidacion += "[Nombre] " + cadaModificadorLiquidacion.getNombre() + " - [Valor] " + cadaModificadorLiquidacion.getValorModificador() + "\n";
		}
		return locStringModificadoresLiquidacion;
	}

	public String getStringVencimientos() {
		String locStringVencimientosLiquidacion = "";
		for(Vencimiento cadaVencimiento : this.getListaVencimientos()) {
			locStringVencimientosLiquidacion += "[Nombre] " + cadaVencimiento.getNombre() + " - [Fecha] " + Util.getString(cadaVencimiento.getFecha()) + " - [Valor] "
					+ cadaVencimiento.getValor() + "\n";
		}
		return locStringVencimientosLiquidacion;
	}

	public String getCodigoBarrasPagoFacil() {
		// Comienza con el codigo de la empresa, seria la municipalidad, 4 digitos.
		String locResultado = "0123";
		// Siguientes 8 digitos, el importe a cobrar.
		locResultado += getMontoPagoFacil(this.getValorTotal());
		// Siguiente 5 digitos, la fecha de vencimiento
		locResultado += getFechaPagoFacil(this.getListaVencimientos().iterator().next().getFecha());
		// Siguientes 14 digitos, el numero de la liquidacion, el id propio de vipians para la liquidacion.
		locResultado += getIdPagoFacil(this.getIdRegistroDeuda()); // <-------------- esto cambia
		// Siguiente digito, el tipo de monenda, 0 para pesos
		locResultado += "0";
		// Siguientes 6 digitos, el recargo en pesos del segundo vencimiento.
		locResultado += "000000"; // <-------------- EL MONTO DEL 2DO VENCIMIENTO (SI HAY 2DO VENCIMIENTO)
		// Siguientes 2 digitos, los dias hasta el segundo vencimineto
		locResultado += "00"; // <-------------- LA DIFERENCIA EN DIAS ENTRE EL 2DO Y EL 1ER VENCIMIENTO
		// Ultimos dos digitos, digitos verificadores.
		locResultado += getPrimerDigitoVerificadorPF(locResultado);
		locResultado += getSegundoDigitoVerificadorPF(locResultado);

		return locResultado;
	}

	private String getMontoPagoFacil(Double pValor) {
		DecimalFormat decimalFormat = new DecimalFormat("000000.00");
		return decimalFormat.format(pValor.doubleValue()).replaceAll(",", "");
	}

	private String getFechaPagoFacil(Date pFecha) {
		SimpleDateFormat format = new SimpleDateFormat("yyDDD");
		return format.format(pFecha);
	}

	private String getIdPagoFacil(long pIdLiquidacion) {
		return this.getSystemLiquidacionTasaBean().getCodigoClientePagoFacil(pIdLiquidacion);
	}

	private int getPrimerDigitoVerificadorPF(String pCadena) {
		int[] arregloVerificador = new int[] {1, 3, 5, 7, 9, 3, 5, 7, 9, 3, 5, 7, 9, 3, 5, 7, 9, 3, 5, 7, 9, 3, 5, 7, 9, 3, 5, 7, 9, 3, 5, 7, 9, 3, 5, 7, 9, 3, 5, 7};
		int suma = 0;
		char[] charArray = pCadena.toCharArray();
		for(int i = 0; i < charArray.length; i++) {
			suma += Character.digit(charArray[i], 10) * arregloVerificador[i];
		}
		int digito = (suma / 2) % 10;
		return digito;
	}

	private int getSegundoDigitoVerificadorPF(String pCadena) {
		int[] arregloVerificador = new int[] {1, 3, 5, 7, 9, 3, 5, 7, 9, 3, 5, 7, 9, 3, 5, 7, 9, 3, 5, 7, 9, 3, 5, 7, 9, 3, 5, 7, 9, 3, 5, 7, 9, 3, 5, 7, 9, 3, 5, 7, 9};
		int suma = 0;
		char[] charArray = pCadena.toCharArray();
		for(int i = 0; i < charArray.length; i++) {
			suma += Character.digit(charArray[i], 10) * arregloVerificador[i];
		}
		int digito = (suma / 2) % 10;
		return digito;
	}

	public String getPeriodoAnio() {
		return this.getCuotaLiquidacion().getPeriodo().getNumero() + "/" + this.getCuotaLiquidacion().getPeriodo().getCalendario().getAnio();
	}

	public ParametroValuado getParametroValuadoPorNombre(String pNombre) {
		for(ParametroValuado cadaParametro : this.getListaParametrosValuados()) {
			if(cadaParametro.getNombreParametro().equalsIgnoreCase(pNombre)) {
				return cadaParametro;
			}
		}
		return null;
	}

	private SystemLiquidacionTasaBean getSystemLiquidacionTasaBean() {
		SystemLiquidacionTasaBean locSystem = null;
		try {
			locSystem = (SystemLiquidacionTasaBean) new InitialContext().lookup(SystemLiquidacionTasaBean.JNDI_NAME);
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return locSystem;
	}

}