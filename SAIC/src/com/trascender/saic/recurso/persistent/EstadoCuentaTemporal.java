package com.trascender.saic.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;


@SuppressWarnings("serial")
//@Entity
//@Table(name = "ESTADO_CUENTA_TEMPORAL")
//@Inheritance(strategy = InheritanceType.JOINED)
@MappedSuperclass
public class EstadoCuentaTemporal implements Serializable{

	public enum Estado{MOROSO, NO_MOROSO};

	//private static final long serialVersionUID = -2165141051294307288L;

	@Id
	@SequenceGenerator(name = "gen_id_estado_cuenta_temporal", allocationSize = 1, sequenceName = "gen_id_estado_cuenta_temporal")
	@GeneratedValue(generator = "gen_id_estado_cuenta_temporal", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_ESTADO_CUENTA_TEMPORAL")
	private long idEstadoCuentaTemporal=-1;

	@Column(name ="TIPO_OBLIGACION")
	private String tipoObligacion;

	//	@ManyToOne(fetch = FetchType.EAGER )
	//	@JoinColumn(name = "ID_PARCELA", nullable = false)
	//	private Parcela parcela;

	@Column(name = "ID_PARCELA")
	private Long idParcela;

	//	@ManyToOne(fetch = FetchType.EAGER)
	//	@JoinColumn(name = "ID_PERSONA")
	//	private Persona persona;

	@Column(name = "ID_PERSONA")
	private Long idPersona;

	//uso el ide xq quiero q sea un objeto liviano
	@Column(name = "ID_REGISTRO_DEUDA")
	//	@Transient //Porque no me interesa levantarlos ahora.
	private long idRegistroDeuda=-1; 


	public long getIdEstadoCuentaTemporal() {
		return idEstadoCuentaTemporal;
	}
	public void setIdEstadoCuentaTemporal(long idEstadoCuentaTemporal) {
		this.idEstadoCuentaTemporal = idEstadoCuentaTemporal;
	}

	public String getTipoObligacion() {
		return tipoObligacion;
	}
	public void setTipoObligacion(String tipoObligacion) {
		this.tipoObligacion = tipoObligacion;
	}
	public long getIdRegistroDeuda() {
		return idRegistroDeuda;
	}
	public void setIdRegistroDeuda(long pIdRegistroDeuda) {
		this.idRegistroDeuda = pIdRegistroDeuda;
	}


	public Long getIdParcela() {
		return idParcela;
	}
	public void setIdParcela(Long idParcela) {
		this.idParcela = idParcela;
	}
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if(idRegistroDeuda != -1){
			result = prime * result + (int) (idRegistroDeuda ^ (idRegistroDeuda >>> 32));
		}
		result = prime * result + ((idParcela == null) ? 0 : idParcela.hashCode());
		result = prime * result + ((idPersona == null) ? 0 : idPersona.hashCode());
		result = prime * result
				+ ((tipoObligacion == null) ? 0 : tipoObligacion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		EstadoCuentaTemporal other = (EstadoCuentaTemporal) obj;
		boolean resultado = true;
		if (this.idPersona != null && other.idPersona != null)
			resultado = resultado && other.idPersona.equals(this.idPersona);
		if (this.tipoObligacion != null && other.tipoObligacion != null)
			resultado = resultado && other.tipoObligacion.equals(this.tipoObligacion);
		if (this.idParcela != null && other.idParcela != null)
			resultado = resultado && other.idParcela.equals(this.idParcela);
		if (this.idRegistroDeuda != -1 && other.idRegistroDeuda != -1)
			resultado = resultado && other.idRegistroDeuda != this.idRegistroDeuda;
		return resultado;
	}

}
