package com.trascender.habilitaciones.recurso.persistent;

import java.text.DateFormat;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;

@Entity
@DiscriminatorValue(value = "VALOR_MEDIDOR")
public class ValorMedidor extends RegistroValuado{

	public static final long serialVersionUID = 6015001204348672561L;
	
	@Column(nullable = false)
	private Double lectura = 0d;
	
	//Pasa a la clase padre, pero dejo los geters y seters, Fernando
//	@ManyToOne
//	@JoinColumn(name = "ID_REG_ALICUOTA", nullable = false)
//	private ServicioOSP servicioOSP = null;
	
	@Column(name = "CODIGO_MEDIDOR")
	private String codigoMedidor;
	
	public String getCodigoMedidor() {
		return codigoMedidor;
	}


	public void setCodigoMedidor(String codigoMedidor) {
		this.codigoMedidor = codigoMedidor;
	}


	public Double getLectura() {
		return lectura;
	}


	public void setLectura(Double lectura) {
		this.lectura = lectura;
	}


	public String getStringPersona() {
		return this.getDocHabilitanteEspecializado().getObligacion().getPersona().toString();
	}
	
	
	public String getStringCodigoMedidor(){
//		return ((DocumentoOSP)this.getDocHabilitanteEspecializado()).getCodigoMedidor();
		return this.codigoMedidor;
	}

	public ServicioOSP getServicioOSP() {
		return (ServicioOSP) getRegAlicuota();
	}
	public void setServicioOSP(ServicioOSP servicioOSP) {
		this.setRegAlicuota(servicioOSP);
	}
	
	
	public String getStringDireccion(){
		return this.getDocHabilitanteEspecializado().getParcela().getDomicilioParcelario().toString();
	}
	
	public String getStringObligacion(){
			return this.getDocHabilitanteEspecializado().getObligacion().toString();
	}
	
	@Override
	public String toString() {
		DateFormat locDateFormat=DateFormat.getDateInstance(DateFormat.SHORT);
		
		return ((this.getServicioOSP()!=null)?this.getServicioOSP().toString():"")
				+" - "+
				((this.getFecha()!=null)?locDateFormat.format(this.getFecha()):"")
				+" - "+
				((this.getMontoImponible()!=null)?this.getMontoImponible():"");
	}
}
