package com.trascender.habilitaciones.recurso.persistent.osp;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.trascender.habilitaciones.recurso.persistent.AsocRegAlicuota;

@Entity
@DiscriminatorValue("OSP")
public class AsocServicioOsp extends AsocRegAlicuota{
	private static final long serialVersionUID = -8103820621002966596L;
	
	@Column(name = "CODIGO_MEDIDOR")
	private String codigoMedidor;
	
	private String codigo;
	
	public String getCodigoMedidor() {
		return codigoMedidor;
	}

	public void setCodigoMedidor(String codigoMedidor) {
		this.codigoMedidor = codigoMedidor;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
