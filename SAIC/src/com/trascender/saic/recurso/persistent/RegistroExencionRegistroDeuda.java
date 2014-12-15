package com.trascender.saic.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "REGISTRO_EXENCION_REG_DEUDA")
public class RegistroExencionRegistroDeuda implements Serializable{


	public static final long serialVersionUID = 7704600893603092704L;
	
	@Id
	@SequenceGenerator(name = "gen_id_reg_exencion_rd", allocationSize = 1, sequenceName = "gen_id_reg_exencion_rd")
	@GeneratedValue(generator = "gen_id_reg_exencion_rd", strategy = GenerationType.SEQUENCE)
	@Column(name="ID_REGISTRO_EXENCION_REG_DEUDA")
	private long idRegistroExencionRegistroDeuda=-1;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_REGISTRO_DEUDA", nullable = true)
	private RegistroDeuda registroDeuda;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_EXENCION", nullable = true )
	private ExencionRegistroDeuda exencionRegistroDeuda;
	
	
	@Column(name="REFERENCIA_NOTA_HCD")
	private String referenciaNotaHCD="";
	
	
	public RegistroDeuda getRegistroDeuda() {
		return registroDeuda;
	}
	public void setRegistroDeuda(RegistroDeuda pRegistroDeuda) {
		this.registroDeuda = pRegistroDeuda;
		if(this.registroDeuda != null && this.registroDeuda.getIdRegistroDeuda() != -1){
			this.registroDeuda.setRegistroExencionRegistroDeuda(this);
		}
	}
	
	public ExencionRegistroDeuda getExencionRegistroDeuda() {
		return exencionRegistroDeuda;
	}
	public void setExencionRegistroDeuda(ExencionRegistroDeuda pExencionRegistroDeuda) {
		this.exencionRegistroDeuda = pExencionRegistroDeuda;
	}
	
	public long getIdRegistroExencionRegistroDeuda() {
		return idRegistroExencionRegistroDeuda;
	}
	public void setIdRegistroExencionRegistroDeuda(long pIdRegistroExencionRegistroDeuda){
		this.idRegistroExencionRegistroDeuda = pIdRegistroExencionRegistroDeuda;
	}
	
	public String getReferenciaNotaHCD() {
		return referenciaNotaHCD;
	}
	public void setReferenciaNotaHCD(String pReferenciaNotaHCD) {
		this.referenciaNotaHCD = pReferenciaNotaHCD;
	}
	
	
	public String getStringRegistroExencionRegDeuda(){
		return this.exencionRegistroDeuda.toString() + " - Referencia Nota HCD ["+this.referenciaNotaHCD+"]";
	}
	
	public String getStringObligacion(){
		return this.getRegistroDeuda().getStringObligacion();
	}
	
	public String getStringPeriodoLiquidado(){
		return this.getRegistroDeuda().getStringPeriodoLiquidado();
	}
	
	public Double getMonto(){
		return this.getRegistroDeuda().getMonto();
	}
	
	public Double getMontoExencion(){
		return this.getRegistroDeuda().getMontoExencion();
	}
	
	public Date getFechaVencimiento(){
		return this.getRegistroDeuda().getFechaVencimiento();
	}
	
	
	@Override
	public String toString(){
		return registroDeuda.toString()+" "+referenciaNotaHCD;
	}
	
	//El hash code lo creo en base al registro de deuda, asi me aseguro q no exista este registro exención mas de una vez
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((registroDeuda == null) ? 0 : registroDeuda.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroExencionRegistroDeuda other = (RegistroExencionRegistroDeuda) obj;
		if (registroDeuda == null) {
			if (other.registroDeuda != null)
				return false;
		} else if (!registroDeuda.equals(other.registroDeuda))
			return false;
		return true;
	}
	
	
}
