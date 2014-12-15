package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;

@Entity
@Table(name = "LINEA_PRESUPUESTO_SOLICITUD_SUMINISTRO")
public class LineaPresupuestoSolicitudSuministro implements Serializable, AuditoriaIndirecta{
	public static final long serialVersionUID = -2993609512319961307L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_linea_presupuesto_solicitud")
	@SequenceGenerator(name = "gen_id_linea_presupuesto_solicitud", sequenceName = "gen_id_linea_presupuesto_solicitud",allocationSize = 1)
	@Column(name="ID_LINEA_PRESUPUESTO_SOLICITUD")
	private long idLineaPresupuestoSolicitud = -1;

	@Column(name = "PRECIO_UNITARIO")
	private Double precioUnitario;

	@Column(name = "PRECIO_TOTAL")
	private Double precioTotal;

	private String comentarios;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PRESUPUESTO_SOLICITUD")
	private PresupuestoSolicitudSuministro presupuestoSolicitud;

	@ManyToOne
	@JoinColumn(name = "ID_LINEA_SOLICITUD_SUMINISTRO")
	private LineaSolicitudSuministro lineaSolicitudSuministro;

	public Double getPrecioTotal() {
		if (precioUnitario != null && lineaSolicitudSuministro != null){
			return precioUnitario * lineaSolicitudSuministro.getCantidad();
		}
		else return 0d;
	}
	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public long getIdLineaPresupuestoSolicitud() {
		return idLineaPresupuestoSolicitud;
	}
	public void setIdLineaPresupuestoSolicitud(long idLineaPresupuestoSolicitud) {
		this.idLineaPresupuestoSolicitud = idLineaPresupuestoSolicitud;
	}

	public PresupuestoSolicitudSuministro getPresupuestoSolicitud() {
		return presupuestoSolicitud;
	}
	public void setPresupuestoSolicitud(PresupuestoSolicitudSuministro presupuestoSolicitud) {
		this.presupuestoSolicitud = presupuestoSolicitud;
	}
	public LineaSolicitudSuministro getLineaSolicitudSuministro() {
		return lineaSolicitudSuministro;
	}
	public void setLineaSolicitudSuministro(
			LineaSolicitudSuministro lineaSolicitudSuministro) {
		this.lineaSolicitudSuministro = lineaSolicitudSuministro;
	}
	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	//Para la interfaz
	public Bien getBien(){
		return this.lineaSolicitudSuministro.getBien();
	}

	public Unidad getUnidad(){
		return this.lineaSolicitudSuministro.getBien().getUnidad();
	}

	public Double getCantidadRenglon(){
		return lineaSolicitudSuministro.getCantidad();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idLineaPresupuestoSolicitud ^ (idLineaPresupuestoSolicitud >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineaPresupuestoSolicitudSuministro other = (LineaPresupuestoSolicitudSuministro) obj;
		if (this.idLineaPresupuestoSolicitud == -1 && other.idLineaPresupuestoSolicitud == -1)
			return this == other;
		if (idLineaPresupuestoSolicitud != other.idLineaPresupuestoSolicitud)
			return false;
		return true;
	}

	@Override
	public String toString(){
		return this.getPrecioUnitario()+" ["+this.getPresupuestoSolicitud().getProveedor()+"]";
	}

	public EntidadTrascender getEntidadTrascender() {
		return this.presupuestoSolicitud.getSolicitudSuministro();
	}

	public String getNombrePropiedad() {
		return "LineaPresupuesto[" + this.presupuestoSolicitud.getProveedor() + " - " + this.lineaSolicitudSuministro.getBien() + "]";
	}

	public boolean concatenaNombre() {
		return true;
	}
}
