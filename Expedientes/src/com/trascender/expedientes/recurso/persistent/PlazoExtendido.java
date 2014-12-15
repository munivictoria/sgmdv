package com.trascender.expedientes.recurso.persistent;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.trascender.expedientes.exception.ExpedientesExceptions;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.Usuario;

@Entity
@DiscriminatorValue("EXTENDIDO")
public class PlazoExtendido extends Plazo{
	private static final long serialVersionUID = -8400197480298395094L;

	@ManyToOne
	@JoinColumn(name = "ID_PLAZO_ANTERIOR")
	private Plazo plazoAnterior;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@Column(name = "CANTIDAD_DIAS")
	private Integer cantidadDias;

	public Plazo getPlazoAnterior() {
		return plazoAnterior;
	}

	public void setPlazoAnterior(Plazo plazoAnterior) {
		this.plazoAnterior = plazoAnterior;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getCantidadDias() {
		return cantidadDias;
	}

	public void setCantidadDias(Integer cantidadDias) {
		this.cantidadDias = cantidadDias;
	}
	
	@Override
	public PlazoDatosCalculados getDatosCalculados(List<DiaFeriado> diasFeriados) {
		return new PlazoDatosCalculados(this.getFechaInicio(), cantidadDias, this.getPlazoProcedimiento().isDiasCorridos(),diasFeriados);
	}
	
	@Override
	public void validarNuevaExtension(Integer dias, Usuario pUsuario, NodoProcedimiento pNodo) 
			throws ExpedientesExceptions{
		if (this.usuario.equals(pUsuario)) {
			//Usuario ya hizo extension
			throw new ExpedientesExceptions(104);
		}
		Plazo locPlazoAnterior = this.plazoAnterior;
		while (locPlazoAnterior != null) {
			if (locPlazoAnterior instanceof PlazoExtendido) {
				PlazoExtendido locPlazoExtendido = (PlazoExtendido) locPlazoAnterior;
				if (locPlazoExtendido.getUsuario().equals(pUsuario)) {
					//Usuario ya hizo extension
					throw new ExpedientesExceptions(104);
				}
				locPlazoAnterior = locPlazoExtendido.getPlazoAnterior();
			} else {
				locPlazoAnterior = null;//Para romper el while.
			}
		}
		super.validarNuevaExtension(dias, pUsuario, pNodo);
	}

}
