package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LINEA_ASIENTO_REFINANCIACION")
public class LineaAsociacionRefinanciacion implements Serializable {

	public static final long serialVersionUID = 8608191933168368724L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_lin_asiento_refin")
	@SequenceGenerator(name = "gen_id_lin_asiento_refin", sequenceName = "gen_id_lin_asiento_refin",allocationSize = 1)
	@Column(name="ID_LINEA_ASIENTO_REFINANCIACION")
	private long idLlineaAsociacionRefinanciacion = -1;
	
	@ManyToOne
	@JoinColumn(name = "ID_ASIENTO_REFINANCIACION")
	private AsociacionRefinanciacion asociacionRefinanciacion;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA_REFINANCIACION")
	private CuentaRefinanciacion cuentaRefinanciacion;

	public long getIdLlineaAsociacionRefinanciacion() {
		return idLlineaAsociacionRefinanciacion;
	}
	
	public void setIdLlineaAsociacionRefinanciacion(
			long idLlineaAsociacionRefinanciacion) {
		this.idLlineaAsociacionRefinanciacion = idLlineaAsociacionRefinanciacion;
	}
	
	public AsociacionRefinanciacion getAsociacionRefinanciacion() {
		return asociacionRefinanciacion;
	}
	
	public void setAsociacionRefinanciacion(
			AsociacionRefinanciacion asociacionRefinanciacion) {
		this.asociacionRefinanciacion = asociacionRefinanciacion;
	}
	
	public CuentaRefinanciacion getCuentaRefinanciacion() {
		return cuentaRefinanciacion;
	}
	
	public void setCuentaRefinanciacion(CuentaRefinanciacion cuentaRefinanciacion) {
		this.cuentaRefinanciacion = cuentaRefinanciacion;
	}
}