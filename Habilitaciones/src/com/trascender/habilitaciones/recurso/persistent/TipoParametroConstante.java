package com.trascender.habilitaciones.recurso.persistent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;

@Entity
@DiscriminatorValue(value = "CONSTANTE")
public class TipoParametroConstante extends TipoParametro implements EntidadTrascender{
	/**
	 * 
	 */
	public static final long serialVersionUID = 4580233888285444438L;
	private String nombre;
	private Double valor;
	private boolean fijo;
	
	@OneToMany(mappedBy = "tipoParametroConstante", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LineaTipoParametroConstante> listaLineas = new ArrayList<LineaTipoParametroConstante>();
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();
	
	public boolean isFijo() {
		return fijo;
	}
	public void setFijo(boolean fijo) {
		this.fijo = fijo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<LineaTipoParametroConstante> getListaLineas() {
		return listaLineas;
	}
	public void setListaLineas(List<LineaTipoParametroConstante> listaLineas) {
		this.listaLineas = listaLineas;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getSimbolo(){
		return (this.isFijo())?"":"%";
	}
	
	public String getTipoValor(){
		return (this.isFijo())?"Fijo":"Porcentual";
	}
	public Double getValor() {
		return this.valor;
	}
	
	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		
	}
	
	@Override
	public Double getValor(DocHabilitanteEspecializado docHabilitanteEspecializado) throws TrascenderException {
//		Date locFechaVencimientoCuota = this.getCuotaLiquidacion().getListaVencimientos().get(0).getTime();
//		
//		LineaTipoParametroConstante lineaFinal = null;
//		for (int i = listaLineas.size() ; i >= 0 ; i--) {
//			LineaTipoParametroConstante cadaLinea = listaLineas.get(i);
//			if (cadaLinea.getFechaBaja() == null 
//					|| !Util.isFechaAfterNoTima(locFechaVencimientoCuota, cadaLinea.getFechaBaja())) {
//				lineaFinal = cadaLinea;
//			} else {
//				break;
//			}
//			
//		}
//		
//		return lineaFinal.getValor();
		
		return this.getValor();
	}

	//*********************************************************************************************************************************************************************************/
	// AUDITORIA

	@Transient
	private long llaveUsuarioAuditoria;
	@Transient
	private String comentarioAuditoria;

	public List<LogAuditoria> getListaLogsAuditoria() {
		return listaLogsAuditoria;
	}

	public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
		this.listaLogsAuditoria = pListaLogsAuditoria;
	}

	public long getLlaveUsuarioAuditoria() {
		return llaveUsuarioAuditoria;
	}

	public void setLlaveUsuarioAuditoria(long llaveUsuarioAuditoria) {
		this.llaveUsuarioAuditoria = llaveUsuarioAuditoria;
	}

	public String getComentarioAuditoria() {
		return comentarioAuditoria;
	}

	public void setComentarioAuditoria(String comentarioAuditoria) {
		this.comentarioAuditoria = comentarioAuditoria;
	}

	public long getIdEntidad() {
		return this.getIdTipoParametro();
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idTipoParametro";
	}

	public boolean isAuditable() {
		return true;
	}
}
