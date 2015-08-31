/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.trascender.expedientes.exception.ExpedientesExceptions;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.Usuario;

@Entity
@Table(name = "EXP_PLAZO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO")
@DiscriminatorValue("COMUN")
public class Plazo implements Serializable {

	private static final long serialVersionUID = -7423716402016073225L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_exp_plazo")
	@SequenceGenerator(name = "gen_id_exp_plazo", sequenceName = "gen_id_exp_plazo", allocationSize = 1)
	@Column(name = "ID_PLAZO")
	private long idPlazo = -1l;

	@OneToOne
	@JoinColumn(name = "ID_PLAZOPROCEDIMIENTO")
	private PlazoProcedimiento plazoProcedimiento;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_FIN")
	private Date fechaFin;

	public Plazo(NodoProcedimiento pNodoProcedimiento) {
		PlazoProcedimiento pp = pNodoProcedimiento.getPlazo();
		while(pp == null) {
			pNodoProcedimiento = pNodoProcedimiento.getNodoPadre();
			pp = pNodoProcedimiento.getPlazo();
		}
		this.plazoProcedimiento = pp;
		this.fechaInicio = new Date();
	}

	public Plazo() {
	}

	public long getIdPlazo() {
		return idPlazo;
	}

	public void setIdPlazo(long idPlazo) {
		this.idPlazo = idPlazo;
	}

	public PlazoProcedimiento getPlazoProcedimiento() {
		return plazoProcedimiento;
	}

	public void setPlazoProcedimiento(PlazoProcedimiento plazoProcedimiento) {
		this.plazoProcedimiento = plazoProcedimiento;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public PlazoDatosCalculados getDatosCalculados(List<DiaFeriado> diasFeriados) {
		return new PlazoDatosCalculados(this.fechaInicio, this.plazoProcedimiento.getDias(), this.plazoProcedimiento.isDiasCorridos(), diasFeriados);
	}

	public PlazoExtendido getExtension(Usuario pUsuario, Integer cantidadDias, List<DiaFeriado> pLista) throws ExpedientesExceptions {
		PlazoExtendido locPlazo = new PlazoExtendido();
		locPlazo.setPlazoAnterior(this);
		locPlazo.setCantidadDias(cantidadDias);
		locPlazo.setUsuario(pUsuario);
		locPlazo.setPlazoProcedimiento(plazoProcedimiento);
		locPlazo.setFechaInicio(new Date());
		PlazoDatosCalculados locPDC = locPlazo.getDatosCalculados(pLista);
		locPlazo.setFechaFin(locPDC.getFechaFinal());
		
		return locPlazo;
	}

	public void validarNuevaExtension(Integer dias, Usuario pUsuario, NodoProcedimiento pNodo) throws ExpedientesExceptions {
		UsuarioExtensor locUsuarioExtensor = null;
		if(pNodo.getResponsable() != null) {
			for(UsuarioExtensor cadaUsuarioExtensor : pNodo.getResponsable().getListaUsuariosExtensores()) {
				if(cadaUsuarioExtensor.getUsuario().equals(pUsuario)) {
					locUsuarioExtensor = cadaUsuarioExtensor;
					break;
				}
			}
		}
		if(locUsuarioExtensor == null) {
			// Usuario no puede hacer extensiones.
			throw new ExpedientesExceptions(103);
		}
		if(dias > locUsuarioExtensor.getCantidadDias()) {
			// Demasiados días de extension.
			throw new ExpedientesExceptions(105);
		}
	}

}