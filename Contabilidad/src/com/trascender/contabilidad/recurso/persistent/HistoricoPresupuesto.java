package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Usuario;

@Entity
@Table(name = "HISTORICO_PRESUPUESTO")
public class HistoricoPresupuesto implements Serializable{

	private static final long serialVersionUID = 2336620406682583328L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_historico_presupuesto")
	@SequenceGenerator(name = "gen_id_historico_presupuesto", sequenceName = "gen_id_historico_presupuesto",allocationSize = 1)
	@Column(name="ID_HIST_PRES")
	private long idHistoricoPresupuesto = -1;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "ID_PRESUPUESTO")
	private Presupuesto presupuesto;
	
	@OneToMany(mappedBy = "historicoPresupuesto", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LineaHistoricoPresupuesto> lineasHistoricoPresupuesto = new HashSet<LineaHistoricoPresupuesto>();
	
	public long getIdHistoricoPresupuesto() {
		return idHistoricoPresupuesto;
	}
	public void setIdHistoricoPresupuesto(long idHistoricoPresupuesto) {
		this.idHistoricoPresupuesto = idHistoricoPresupuesto;
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

	public Presupuesto getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}
	
	public Set<LineaHistoricoPresupuesto> getLineasHistoricoPresupuesto() {
		return lineasHistoricoPresupuesto;
	}
	
	public void setLineasHistoricoPresupuesto(
			Set<LineaHistoricoPresupuesto> lineasHistoricoPresupuesto) {
		this.lineasHistoricoPresupuesto = lineasHistoricoPresupuesto;
	}

}
