/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.framework.recurso.persistent.reporteDinamico.Reporte;

@Entity
@Table(name = "EXP_DOCUMENTOCATALOGO")
public class DocumentoCatalogo implements Serializable {

	public static final long serialVersionUID = -450293616901232370L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_DocumentoCatalogo")
	@SequenceGenerator(name = "gen_id_DocumentoCatalogo", sequenceName = "gen_id_DocumentoCatalogo", allocationSize = 1)
	@Column(name = "ID_DocumentoCatalogo")
	private long idDocumentoCatalogo = -1l;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Enumerated(EnumType.STRING)
	private EstadoPlantilla estado = EstadoPlantilla.ACTIVO;

	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	public enum Tipo {
		ENTRADA, SALIDA
	}

	@ManyToOne
	@JoinColumn(name = "id_reporte")
	private Reporte reporte;

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Reporte getReporte() {
		return reporte;
	}

	public void setReporte(Reporte reporte) {
		this.reporte = reporte;
	}

	public long getIdDocumentoCatalogo() {
		return idDocumentoCatalogo;
	}

	public void setIdDocumentoCatalogo(long idDocumentoCatalogo) {
		this.idDocumentoCatalogo = idDocumentoCatalogo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EstadoPlantilla getEstado() {
		return estado;
	}

	public void setEstado(EstadoPlantilla estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return getNombre();
	}

}