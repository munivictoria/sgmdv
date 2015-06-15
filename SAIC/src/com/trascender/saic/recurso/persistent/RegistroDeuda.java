
package com.trascender.saic.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Exencion.Estado;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.saic.recurso.interfaces.Pagable;

/**
 * @hibernate.class table = "REGISTRO_DEUDA"
 */

@Entity
@Access(AccessType.FIELD)
@Table(name = "REGISTRO_DEUDA")
@Inheritance(strategy = InheritanceType.JOINED)
public class RegistroDeuda implements Serializable, Pagable {

	public static final long serialVersionUID = 7923125393087476641L;

	/**
	 * Tipos de deuda{ LIQUIDACION, RELIQUIDACION, REFINANCIACION }
	 */
	public enum TipoDeuda {
		LIQUIDACION, RELIQUIDACION, REFINANCIACION;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}

	/**
	 * Estados Registro de Deudas {CANCELADA, VIGENTE, VENCIDA, ANULADA, PAGADA, RELIQUIDADA, REFINANCIADA, PAGADA_BIMESTRAL, PAGADA_EN_TERCIOS, PAGADA_ANUAL,
	 * REFINANCIADA_BIMESTRAL, EXENTO }
	 */
	public enum EstadoRegistroDeuda {
		CANCELADA, VIGENTE, VENCIDA, ANULADA, PAGADA, RELIQUIDADA, REFINANCIADA, NO_OPTADA, PRESCRIPTA,
		// Exento de un porcentaje
		EXENTO;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}

	@Id
	@SequenceGenerator(name = "gen_id_registro_deuda", sequenceName = "gen_id_registro_deuda", allocationSize = 1)
	@GeneratedValue(generator = "gen_id_registro_deuda", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_REGISTRO_DEUDA")
	private long idRegistroDeuda = -1;

	@Column(name = "FECHA_EMISION")
	private Date fechaEmision;

	@Column(name = "NRO_REG_DEUDA")
	private Integer numeroRegistroDeuda;

	@ManyToOne
	@JoinColumn(name = "ID_REGISTRO_CANCELACION", nullable = true)
	private RegistroCancelacion registroCancelacion;

	// Lo dejo en eager porque no puedo cobrar las deudas, problema con javassist, ver si no revienta. Fernando.
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
	@JoinColumn(name = "ID_DOC_GENERADOR_DEUDA", nullable = false)
	private DocGeneradorDeuda docGeneradorDeuda;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO")
	protected EstadoRegistroDeuda estado = EstadoRegistroDeuda.VIGENTE;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO_ANTERIOR", nullable = false)
	private EstadoRegistroDeuda estadoAnterior = EstadoRegistroDeuda.VIGENTE;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO")
	private TipoDeuda tipoDeuda = TipoDeuda.LIQUIDACION;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "ID_REGISTRO_EXENCION_REG_DEUDA", nullable = true, unique = true)
	private RegistroExencionRegistroDeuda registroExencionRegistroDeuda;

	@Override
	public RegistroCancelacion getRegistroCancelacion() {
		return registroCancelacion;
	}

	@Override
	public void setRegistroCancelacion(RegistroCancelacion pRegistroCancelacion) {
		registroCancelacion = pRegistroCancelacion;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date pFechaEmision) {
		fechaEmision = pFechaEmision;
	}

	public long getIdRegistroDeuda() {
		return idRegistroDeuda;
	}

	public void setIdRegistroDeuda(long pIdRegistroDeuda) {
		idRegistroDeuda = pIdRegistroDeuda;
	}

	public DocGeneradorDeuda getDocGeneradorDeuda() {
		return docGeneradorDeuda;
	}

	public void setDocGeneradorDeuda(DocGeneradorDeuda pDocGeneradorDeuda) {
		docGeneradorDeuda = pDocGeneradorDeuda;
	}

	public Integer getNumeroRegistroDeuda() {
		return numeroRegistroDeuda;
	}

	public void setNumeroRegistroDeuda(Integer numeroRegistroDeuda) {
		this.numeroRegistroDeuda = numeroRegistroDeuda;
	}

	public EstadoRegistroDeuda getEstadoAnterior() {
		return estadoAnterior;
	}

	public void setEstadoAnterior(EstadoRegistroDeuda pEstadoRegistroDeuda) {
		this.estadoAnterior = pEstadoRegistroDeuda;
	}

	@Override
	public int hashCode() {
		if(this.idRegistroDeuda == -1)
			return super.hashCode();
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idRegistroDeuda ^ (idRegistroDeuda >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		final RegistroDeuda other = (RegistroDeuda) obj;
		if(idRegistroDeuda != other.idRegistroDeuda)
			return false;
		return true;
	}

	// fechaVencimiento, periodo, importe, intereses, recargo, multas
	/**
	 * Por ahora no hace nada
	 * 
	 * @return null
	 */
	@Access(AccessType.FIELD)
	@Column(name = "FECHA_VENCIMIENTO")
	public Date getFechaVencimiento() {
		return null;
	}

	/**
	 * Por ahora no hace nada
	 * 
	 * @return null
	 */
	@Access(AccessType.FIELD)
	@Column(name = "ID_CUOTA_LIQUIDACION")
	// @ManyToOne()
	public CuotaLiquidacion getCuotaLiquidacion() {
		return null;
	}

	/**
	 * Por ahora no hace nada
	 * 
	 * @return null
	 */
	@Access(AccessType.FIELD)
	@Column(name = "INTERES")
	public Double getInteres() {
		return null;
	}

	/**
	 * Por ahora no hace nada
	 * 
	 * @return null
	 */
	@Access(AccessType.FIELD)
	@Column(name = "RECARGO")
	public Double getRecargo() {
		return null;
	}

	/**
	 * Por ahora no hace nada
	 * 
	 * @return null
	 */
	@Access(AccessType.FIELD)
	@Column(name = "MULTAS")
	public Double getMultas() {
		return null;
	}

	/**
	 * Por ahora no hace nada
	 * 
	 * @return null
	 */
	@Override
	@Access(AccessType.FIELD)
	@Column(name = "MONTO")
	public Double getMonto() {
		return null;
	}

	@Override
	public String getNombre() {
		return this.getStringPeriodoLiquidado();
	}

	@Override
	public Persona getPersona() {
		return this.docGeneradorDeuda.getObligacion().getPersona();
	}

	@Override
	public Long getId() {
		return this.idRegistroDeuda;
	}

	public EstadoRegistroDeuda getEstado() {
		try {
			if(this.estado.equals(EstadoRegistroDeuda.ANULADA) || this.estado.equals(EstadoRegistroDeuda.CANCELADA) || this.estado.equals(EstadoRegistroDeuda.VENCIDA)
					|| this.estado.equals(EstadoRegistroDeuda.VIGENTE)) {
				if(this.getRegistroCancelacion() == null) {// si no tiene reg de cancelacion, es vigente
					estado = EstadoRegistroDeuda.VIGENTE;
				}

				if(estado.equals(EstadoRegistroDeuda.VIGENTE)) {
					Date locFechaActual = Util.getFechaActualFormatoSimple();

					if(this.getFechaVencimiento() != null) {// si es vigente y tiene una fecha de vencimiento, comprueba si no esta venciada
						Date locFechaVencimiento = this.getFechaVencimiento();
						estado = ((locFechaVencimiento.after(locFechaActual) || locFechaActual.getTime() == locFechaVencimiento.getTime()) ? EstadoRegistroDeuda.VIGENTE
								: EstadoRegistroDeuda.VENCIDA);
					} else {// si no tiene una fecha de vencimiento, el estado es vencido
						estado = EstadoRegistroDeuda.VENCIDA;
					}
				}
				// si el estado de la obligacion asociada es anulada, el registro es anulado.
				if(this.getDocGeneradorDeuda() != null 
						&& this.getDocGeneradorDeuda().getObligacion().getEstado().equals(Obligacion.Estado.ANULADO)
						&& this.estado != EstadoRegistroDeuda.PAGADA) {
					estado = EstadoRegistroDeuda.ANULADA;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	public void setEstado(EstadoRegistroDeuda pEstado) {
		this.estado = pEstado;
	}

	public TipoDeuda getTipoDeuda() {
		return tipoDeuda;
	}

	public void setTipoDeuda(TipoDeuda pTipoDeuda) {
		this.tipoDeuda = pTipoDeuda;
	}

	public RegistroExencionRegistroDeuda getRegistroExencionRegistroDeuda() {
		return registroExencionRegistroDeuda;
	}

	public void setRegistroExencionRegistroDeuda(RegistroExencionRegistroDeuda pRegistroExencionRegistroDeuda) {
		this.registroExencionRegistroDeuda = pRegistroExencionRegistroDeuda;
	}

	public Double getMontoExencion() {
		Double locMontoExencion = 0D;
		if(this.getRegistroExencionRegistroDeuda() != null && this.getRegistroExencionRegistroDeuda().getExencionRegistroDeuda().getEstado().equals(Estado.VIGENTE)) {
			if(this instanceof LiquidacionTasa) {
				LiquidacionTasa locLiquidacionTasa = (LiquidacionTasa) this;
				locMontoExencion = (locLiquidacionTasa.getValorTotal() + locLiquidacionTasa.getInteres() + locLiquidacionTasa.getRecargo() + locLiquidacionTasa.getMontoMultas())
						* (this.getRegistroExencionRegistroDeuda().getExencionRegistroDeuda().getValor() / 100D);
			}
		}
		return locMontoExencion;
	}

	public String getFechaCancelacion() {
		if(this.getEstado().equals(EstadoRegistroDeuda.PAGADA) || this.getEstado().equals(EstadoRegistroDeuda.REFINANCIADA) || this.getEstado().equals(EstadoRegistroDeuda.RELIQUIDADA)) {
			return com.trascender.framework.util.Util.getString(this.getRegistroCancelacion().getFechaCancelacion());
		}
		return "";
	}

	/**
	 * Retorna un String con el siguiente formato Nombre Tasa - Año - Nro de cuota
	 * 
	 * @return
	 */
	public String getStringPeriodoLiquidado() {
		try {
			return getNombre();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void setStringPeriodoLiquidado(String pString) {
	}

	public String getStringObligacion() {
		return this.getDocGeneradorDeuda().getObligacion().toString();
	}

	public void setStringObligacion(String pString) {
	}
	
	public String getCodigoBarrasEstandar() {
		String idCadena = String.valueOf(this.idRegistroDeuda);
		String retorno = "";
		int locTamanio=idCadena.length();
		int agregar = 18 - locTamanio;
		if (agregar>0){
			for (int i=0;i<agregar;i++){
				retorno += "0";
			}
		}
		return "1"+retorno+idCadena;
	}

}
