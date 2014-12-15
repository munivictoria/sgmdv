package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Usuario;
@Entity
@Table(name = "PLANILLA_DIARIA_CAJA")
public class PlanillaDiariaCaja implements Serializable{

	public static final long serialVersionUID = 3672953704921768045L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_planilla_diaria_caja")
	@SequenceGenerator(name = "gen_id_planilla_diaria_caja", sequenceName = "gen_id_planilla_diaria_caja",allocationSize = 1)
	@Column(name="ID_PLANILLA_DIARIA_CAJA")
	private long idPlanillaDiariaCaja=-1;
	private Date fecha;
	//Relacion con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "planilla", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MovimientoCajaEgreso> movimientosCajaEgreso;
	
	@OneToOne(mappedBy = "planillaDiariaCaja", cascade = CascadeType.ALL)
	private ArqueoCaja arqueoCaja;
	
	@OneToMany(mappedBy = "planillaDiariaCaja", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<TicketCaja> tickets;
	
	public ArqueoCaja getArqueoCaja() {
		return arqueoCaja;
	}

	public void setArqueoCaja(ArqueoCaja arqueoCaja) {
		this.arqueoCaja = arqueoCaja;
	}

	public Set<MovimientoCajaEgreso> getMovimientosCajaEgreso() {
		return movimientosCajaEgreso;
	}

	public void setMovimientosCajaEgreso(
			Set<MovimientoCajaEgreso> movimientosCajaEgreso) {
		this.movimientosCajaEgreso = movimientosCajaEgreso;
	}
	
	public Set<TicketCaja> getTickets() {
		return tickets;
	}

	public void setTickets(Set<TicketCaja> tickets) {
		this.tickets = tickets;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public long getIdPlanillaDiariaCaja() {
		return idPlanillaDiariaCaja;
	}

	public void setIdPlanillaDiariaCaja(long idPlanillaDiariaCaja) {
		this.idPlanillaDiariaCaja = idPlanillaDiariaCaja;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if(this.idPlanillaDiariaCaja ==-1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idPlanillaDiariaCaja ^ (idPlanillaDiariaCaja >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final PlanillaDiariaCaja other = (PlanillaDiariaCaja) obj;
		if (idPlanillaDiariaCaja != other.idPlanillaDiariaCaja) {
			return false;
		}
		return true;
	}
	
}
