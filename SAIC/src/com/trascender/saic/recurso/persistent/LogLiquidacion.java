
package com.trascender.saic.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;

@Entity
@Table(name = "LOG_LIQUIDACION")
public class LogLiquidacion implements Serializable {

	public static final long serialVersionUID = -4580651091700304763L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_log_liquidacion")
	@SequenceGenerator(name = "gen_id_log_liquidacion", sequenceName = "gen_id_log_liquidacion", allocationSize = 1)
	@Column(name = "ID_LOG_LIQUIDACION")
	private long idLogLiquidacion = -1;

	@ManyToOne
	@JoinColumn(name = "ID_OBLIGACION")
	private Obligacion obligacion;

	@ManyToOne
	@JoinColumn(name = "ID_CUOTA_LIQUIDACION")
	private CuotaLiquidacion cuota;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	private Date fecha;

	@Column(name = "MONTO_TOTAL")
	private Double montoTotalLiquidacion;
	
	private Double intereses;
	
	@Column(name = "TIPO_DEUDA")
	@Enumerated(EnumType.STRING)
	private RegistroDeuda.TipoDeuda tipoDeuda;

	@Enumerated(EnumType.STRING)
	private Evento evento;

	private String comentario;
	
	public enum Evento {
		LIQUIDO, RELIQUIDO, MODIFICO, ELIMINO, MARCO_PAGA, MARCO_IMPAGA, PAGO_CAJA, ANULO_PAGO_CAJA, ACTUALIZO,
		NOTIFICO, APREMIO
	}
		
	public long getIdLogLiquidacion() {
		return idLogLiquidacion;
	}

	public void setIdLogLiquidacion(long idLogLiquidacion) {
		this.idLogLiquidacion = idLogLiquidacion;
	}

	public Obligacion getObligacion() {
		return obligacion;
	}

	public void setObligacion(Obligacion obligacion) {
		this.obligacion = obligacion;
	}

	public CuotaLiquidacion getCuota() {
		return cuota;
	}

	public void setCuota(CuotaLiquidacion cuota) {
		this.cuota = cuota;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getMontoTotalLiquidacion() {
		return montoTotalLiquidacion;
	}

	public void setMontoTotalLiquidacion(Double montoTotalLiquidacion) {
		this.montoTotalLiquidacion = montoTotalLiquidacion;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Double getIntereses() {
		return intereses;
	}

	public void setIntereses(Double intereses) {
		this.intereses = intereses;
	}

	public RegistroDeuda.TipoDeuda getTipoDeuda() {
		return tipoDeuda;
	}

	public void setTipoDeuda(RegistroDeuda.TipoDeuda tipoDeuda) {
		this.tipoDeuda = tipoDeuda;
	}

}