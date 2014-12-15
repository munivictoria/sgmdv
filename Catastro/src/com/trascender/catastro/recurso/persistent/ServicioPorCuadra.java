package com.trascender.catastro.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.JoinColumnOrFormula;


/**
 * Representa un servicio en una cuadra, se utiliza para almacenar la fecha de alta
	*/

@Entity
@Table(name = "SERVICIO_POR_CUADRA")
public class ServicioPorCuadra implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = 1655719162708797887L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_servicio_por_cuadra")
	@SequenceGenerator(name ="gen_id_servicio_por_cuadra", sequenceName = "gen_id_servicio_por_cuadra", allocationSize = 1)
	@Column(name = "ID_SERVICIO_POR_CUADRA")
	private long idServicioPorCuadra=-1;
	
	@Column(name = "FECHA_ALTA")
	private Date fechaAlta;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUADRA")
	private Cuadra cuadra;
	
	@ManyToOne
	@JoinColumn(name = "ID_SERVICIO")
	private Servicio servicio;
	
	public Cuadra getCuadra() {
		return cuadra;
	}

	public void setCuadra(Cuadra cuadra) {
		this.cuadra = cuadra;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public long getIdServicioPorCuadra() {
		return idServicioPorCuadra;
	}

	public void setIdServicioPorCuadra(long idServicioPorCuadra) {
		this.idServicioPorCuadra = idServicioPorCuadra;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
}
