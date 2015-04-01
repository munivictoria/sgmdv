package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.collections.iterators.ArrayListIterator;

import com.trascender.compras.recurso.persistent.inventario.LineaMovimientoMercaderia;
import com.trascender.framework.recurso.persistent.referencia.CuentaRfr;
import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.anotations.NoAuditable;

@Entity
@Table(name = "LINEA_SOLICITUD_SUMINISTRO")
public class LineaSolicitudSuministro implements Serializable, AuditoriaIndirecta{

	public static final long serialVersionUID = 518768980622813875L;

	public enum Estado{
		PENDIENTE, RECIBIDA
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_linea_sol_sum")
	@SequenceGenerator(name = "gen_id_linea_sol_sum", sequenceName = "gen_id_linea_sol_sum",allocationSize = 1)
	@Column(name="ID_LINEA_SOLICITUD_SUMINISTRO")
	private long idLineaSolicitudSuministro =-1;
	private double cantidad;

	@ManyToOne
	@JoinColumn(name = "ID_BIEN")
	private Bien bien;

	@ManyToOne
	@JoinColumn(name = "ID_SOLICITUD_SUMINISTRO")
	private SolicitudSuministro solicitudSuministro;

	//La cascada es temporal, arreglado por Fernando, no deberia estar aca.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_LINEA_ORDEN_COMPRA")
	private LineaOrdenCompra lineaOrdenCompra;

	@NoAuditable
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "RELA_LINEA_CONTRATACION_LINEA_SOL_SUM", joinColumns = @JoinColumn(name = "ID_LINEA_SOLICITUD_SUMINISTRO"), inverseJoinColumns = @JoinColumn(name = "ID_LINEA_CONTRATACION"))
	private List<LineaContratacion> listaLineaContratacion = new ArrayList<LineaContratacion>();

	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.PENDIENTE;

	@OneToMany(mappedBy = "lineaSolicitudSuministro")
	private List<LineaMovimientoMercaderia> listaLineasMovimientosMercaderia = new ArrayList<LineaMovimientoMercaderia>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CUENTA")
	private CuentaRfr cuenta;

	public CuentaRfr getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaRfr cuenta) {
		this.cuenta = cuenta;
	}

	public List<LineaMovimientoMercaderia> getListaLineasMovimientosMercaderia() {
		return listaLineasMovimientosMercaderia;
	}

	public void setListaLineasMovimientosMercaderia(
			List<LineaMovimientoMercaderia> listaLineasMovimientosMercaderia) {
		this.listaLineasMovimientosMercaderia = listaLineasMovimientosMercaderia;
	}

	public List<LineaPresupuestoSolicitudSuministro> getListaLineasPresupuestoSolicitudSuministro() {
		List<LineaPresupuestoSolicitudSuministro> locListaResultado = new ArrayList<LineaPresupuestoSolicitudSuministro>();
		for (PresupuestoSolicitudSuministro cadaPresupuesto : this.solicitudSuministro.getListaPresupuestos()){
			for (LineaPresupuestoSolicitudSuministro cadaLineaPresupuesto : cadaPresupuesto.getListaLineasPresupuestoSolicitud()){
				if (cadaLineaPresupuesto.getLineaSolicitudSuministro().equals(this)){
					locListaResultado.add(cadaLineaPresupuesto);
				}
			}
		}
		return locListaResultado;
	}
	//
	//	public void setListaLineasPresupuestoSolicitudSuministro(
	//			List<LineaPresupuestoSolicitudSuministro> listaLineasPresupuestoSolicitudSuministro) {
	//		this.listaLineasPresupuestoSolicitudSuministro = listaLineasPresupuestoSolicitudSuministro;
	//	}

	//Metodo para la interfaz
	public String getArea(){
		return this.solicitudSuministro.getArea().getNombre();
	}

	public Integer getNumeroSolicitud(){
		return this.getSolicitudSuministro().getNumero();
	}

	public LineaSolicitudSuministro(){
	}

	public Bien getBien() {
		return bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public long getIdLineaSolicitudSuministro() {
		return idLineaSolicitudSuministro;
	}

	public void setIdLineaSolicitudSuministro(long idLineaSolicitudSuministro) {
		this.idLineaSolicitudSuministro = idLineaSolicitudSuministro;
	}


	public SolicitudSuministro getSolicitudSuministro() {
		return solicitudSuministro;
	}


	public void setSolicitudSuministro(SolicitudSuministro solicitudSuministro) {
		this.solicitudSuministro = solicitudSuministro;
	}


	public LineaOrdenCompra getLineaOrdenCompra() {
		return lineaOrdenCompra;
	}


	public void setLineaOrdenCompra(LineaOrdenCompra lineaOrdenCompra) {
		this.lineaOrdenCompra = lineaOrdenCompra;
	}
	
	public List<LineaContratacion> getListaLineaContratacion() {
		return listaLineaContratacion;
	}

	public void setListaLineaContratacion(
			List<LineaContratacion> listaLineaContratacion) {
		this.listaLineaContratacion = listaLineaContratacion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Double getCantidadEntregasPrevias(){
		Double locCantidad = new Double(0);
		for (LineaMovimientoMercaderia cadaLinea : this.listaLineasMovimientosMercaderia){
			if (cadaLinea.getIdLineaMovimientoMercaderia() == -1) continue;//No sumamos las nuevas, solo las previas.
			locCantidad += cadaLinea.getCantidad();
		}
		return locCantidad;
	}
	
	public Double getCantidadRestante(){
		Double cantidadEnLineasContratacion = 0d;
		for(LineaContratacion cadaLineaContratacion : this.listaLineaContratacion){
			cantidadEnLineasContratacion += cadaLineaContratacion.getCantidad();
		}
		return this.cantidad - cantidadEnLineasContratacion;
	}
	
	public void setCantidadRestante(Double p){
		
	}

	@Override
	public int hashCode() {
		if (this.idLineaSolicitudSuministro == -1){
			return super.hashCode();
		}
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idLineaSolicitudSuministro ^ (idLineaSolicitudSuministro >>> 32));
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
		LineaSolicitudSuministro other = (LineaSolicitudSuministro) obj;
		if (idLineaSolicitudSuministro != other.idLineaSolicitudSuministro)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Línea Solicitud Suministro["+this.bien + " x " + this.cantidad + "]";
	}

	public EntidadTrascender getEntidadTrascender() {
		return this.solicitudSuministro;
	}

	public String getNombrePropiedad() {
		return "Linea[" + this.bien + "]";
	}

	public boolean concatenaNombre() {
		return true;
	}

}
