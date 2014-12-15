package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;

@Entity
@Table(name = "PRESUPUESTO_SOLICITUD_SUMINISTRO")
public class PresupuestoSolicitudSuministro implements Serializable, AuditoriaIndirecta{
	public static final long serialVersionUID = 2002196886363213122L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_presupuesto_solicitud")
	@SequenceGenerator(name = "gen_id_presupuesto_solicitud", sequenceName = "gen_id_presupuesto_solicitud",allocationSize = 1)
	@Column(name="ID_PRESUPUESTO_SOLICITUD")
	private long idPresupuestoSolicitudSuministro = -1;

	@ManyToOne
	@JoinColumn(name = "ID_PROVEEDOR")
	private Proveedor proveedor;

	@OneToMany(mappedBy = "presupuestoSolicitud", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LineaPresupuestoSolicitudSuministro> listaLineasPresupuestoSolicitud = new ArrayList<LineaPresupuestoSolicitudSuministro>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SOLICITUD_SUMINISTRO")
	private SolicitudSuministro solicitudSuministro;

	@Column(name="PLAZO_MANTENIMIENTO")
	private String plazoMantenimiento;

	public List<LineaPresupuestoSolicitudSuministro> getListaLineasPresupuestoSolicitud() {
		return listaLineasPresupuestoSolicitud;
	}

	public void setListaLineasPresupuestoSolicitud(
			List<LineaPresupuestoSolicitudSuministro> listaLineasPresupuestoSolicitud) {
		this.listaLineasPresupuestoSolicitud = listaLineasPresupuestoSolicitud;
	}

	public void addLineaPresupuestoSolicitud(LineaPresupuestoSolicitudSuministro pLineaPresupuesto){
		pLineaPresupuesto.setPresupuestoSolicitud(this);
		this.listaLineasPresupuestoSolicitud.add(pLineaPresupuesto);
	}

	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public SolicitudSuministro getSolicitudSuministro() {
		return solicitudSuministro;
	}

	public void setSolicitudSuministro(SolicitudSuministro solicitudSuministro) {
		this.solicitudSuministro = solicitudSuministro;
	}

	public Double getTotal(){
		Double locTotal = new Double(0);
		for (LineaPresupuestoSolicitudSuministro cadaLinea : this.getListaLineasPresupuestoSolicitud()){
			locTotal += cadaLinea.getPrecioTotal();
		}
		return locTotal;
	}	

	public long getIdPresupuestoSolicitudSuministro() {
		return idPresupuestoSolicitudSuministro;
	}

	public void setIdPresupuestoSolicitudSuministro(
			long idPresupuestoSolicitudSuministro) {
		this.idPresupuestoSolicitudSuministro = idPresupuestoSolicitudSuministro;
	}

	public String getPlazoMantenimiento() {
		return plazoMantenimiento;
	}

	public void setPlazoMantenimiento(String plazoMantenimiento) {
		this.plazoMantenimiento = plazoMantenimiento;
	}

	@Override
	public String toString(){
		return "Presupuesto de: "+proveedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idPresupuestoSolicitudSuministro ^ (idPresupuestoSolicitudSuministro >>> 32));
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
		PresupuestoSolicitudSuministro other = (PresupuestoSolicitudSuministro) obj;
		if (this.idPresupuestoSolicitudSuministro == -1 && other.idPresupuestoSolicitudSuministro == -1){
			return this == other;
		}
		if (idPresupuestoSolicitudSuministro != other.idPresupuestoSolicitudSuministro)
			return false;
		return true;
	}

	public EntidadTrascender getEntidadTrascender() {
		return this.solicitudSuministro;
	}

	public String getNombrePropiedad() {
		return "Presupuesto["+ this.proveedor +"]";
	}

	public boolean concatenaNombre() {
		return true;
	}
}
