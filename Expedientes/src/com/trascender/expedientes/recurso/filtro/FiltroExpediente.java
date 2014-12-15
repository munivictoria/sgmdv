package com.trascender.expedientes.recurso.filtro;

import java.util.Date;
import java.util.List;

import com.trascender.expedientes.recurso.persistent.Expediente;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroExpediente extends FiltroAbstracto<Expediente> {

	private static final long serialVersionUID = -7260321319127341202L;

	private String asunto;
	private Procedimiento procedimiento;
	private Persona interesado;
	private Date fechaRegistroDesde;
	private Date fechaRegistroHasta;
	private Long nroRegistro;
	private List<Long> listaIdInteresados;
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public Procedimiento getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(Procedimiento procedimiento) {
		this.procedimiento = procedimiento;
	}

	public Persona getInteresado() {
		return interesado;
	}

	public void setInteresado(Persona interesado) {
		this.interesado = interesado;
	}

	public Date getFechaRegistroDesde() {
		return fechaRegistroDesde;
	}

	public void setFechaRegistroDesde(Date fechaRegistroDesde) {
		this.fechaRegistroDesde = fechaRegistroDesde;
	}

	public Date getFechaRegistroHasta() {
		return fechaRegistroHasta;
	}

	public void setFechaRegistroHasta(Date fechaRegistroHasta) {
		this.fechaRegistroHasta = fechaRegistroHasta;
	}

	public Long getNroRegistro() {
		return nroRegistro;
	}

	public void setNroRegistro(Long nroRegistro) {
		this.nroRegistro = nroRegistro;
	}

	public List<Long> getListaIdInteresados() {
		return listaIdInteresados;
	}

	public void setListaIdInteresados(List<Long> listaIdInteresados) {
		this.listaIdInteresados = listaIdInteresados;
	}
}