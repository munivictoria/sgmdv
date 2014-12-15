package com.trascender.contabilidad.recurso.persistent;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;

@Entity
@Table(name = "CUENTA_DOC_REFINANCIACION")
@PrimaryKeyJoinColumn(name = "ID_ASOCIACION_CUENTA")
public class CuentaRefinanciacion extends AsociacionCuenta{

	public static final long serialVersionUID = 6683270840501863382L;
	
	@Transient
	private final Long idCuentaRefinanciacion = -1L;
	
	@ManyToOne
	@JoinColumn(name = "ID_ASOCIACION_REFINANCIACION")
	private AsociacionRefinanciacion asociacionRefinanciacion;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOC_GENERADOR_DEUDA")
	private DocumentoRefinanciacion documentoRefinanciacion;
	
	@ManyToOne
	@JoinColumn(name = "ID_PARAMETRO_ASOCIACION")
	private ParametroAsociacion parametroAsociacion;
	private Double importe;
	
	public Long getIdCuentaRefinanciacion() {
		return idCuentaRefinanciacion;
	}

	public DocumentoRefinanciacion getDocumentoRefinanciacion() {
		return documentoRefinanciacion;
	}

	public void setDocumentoRefinanciacion(
			DocumentoRefinanciacion documentoRefinanciacion) {
		this.documentoRefinanciacion = documentoRefinanciacion;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public ParametroAsociacion getParametroAsociacion() {
		return parametroAsociacion;
	}

	public void setParametroAsociacion(ParametroAsociacion parametroAsociacion) {
		this.parametroAsociacion = parametroAsociacion;
	}

	public AsociacionRefinanciacion getAsociacionRefinanciacion() {
		return asociacionRefinanciacion;
	}

	public void setAsociacionRefinanciacion(
			AsociacionRefinanciacion asociacionRefinanciacion) {
		this.asociacionRefinanciacion = asociacionRefinanciacion;
	}
}