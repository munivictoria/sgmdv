/**
 * 
 */
package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.LazyInitializationException;
import org.hibernate.annotations.Where;

import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.persistent.inventario.LineaMovimientoMercaderia;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.Util;


@Entity
@Table(name = "ORDEN_COMPRA")
public class OrdenCompra implements Serializable, EntidadTrascender {

	public enum Estado{
		NUEVA, APROBADA, RESCINDIDA, ANULADA, CUMPLIDA;
		
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	/**
	 * 
	 */
	public static final long serialVersionUID = 862175077197408247L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_orden_compra")
	@SequenceGenerator(name = "gen_id_orden_compra", sequenceName = "gen_id_orden_compra",allocationSize = 1)
	@Column(name="ID_ORDEN_COMPRA")
	private long idOrdenCompra=-1;
	
	private String descripcion;
	
	@Column(name = "FECHA_EMISION")
	private Date fechaEmision;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	private Integer numero;
	
	@ManyToOne
	@JoinColumn(name = "ID_CONDICION_COMPRA")
	private CondicionCompra condicionCompra;
	
	@OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AutorizacionOrdenCompra> listaFirmaPermisos;
	
	@ManyToOne
	@JoinColumn(name = "ID_PROVEEDOR")
	private Proveedor proveedor;
	
	@OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LineaOrdenCompra> listaLineaOrdenCompra;
	
	private Double total;

	@Column(name = "COMENTARIO_FINALIZACION")
	private String comentarioFinalizacion;
	
	@OneToMany(mappedBy = "ordenCompra", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<TransferenciaOrdenCompra> listaTransferencias;
	
	@OrderBy("idPagoOrdenCompra")
	@OneToMany(mappedBy = "ordenCompra", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<PagoOrdenCompra> listaPagosOrdenCompra;
	
	public List<PagoOrdenCompra> getListaPagosOrdenCompra() {
		return listaPagosOrdenCompra;
	}

	public void setListaPagosOrdenCompra(List<PagoOrdenCompra> listaPagosOrdenCompra) {
		this.listaPagosOrdenCompra = listaPagosOrdenCompra;
	}

	public String getComentarioFinalizacion() {
		return comentarioFinalizacion;
	}

	public void setComentarioFinalizacion(String comentarioFinalizacion) {
		this.comentarioFinalizacion = comentarioFinalizacion;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public OrdenCompra(){
		this.total=new Double(0);
		this.estado=OrdenCompra.Estado.APROBADA;
		this.listaFirmaPermisos = new ArrayList<AutorizacionOrdenCompra>();
		this.listaLineaOrdenCompra = new ArrayList<LineaOrdenCompra>();
		this.listaTransferencias = new ArrayList<TransferenciaOrdenCompra>();
		this.listaPagosOrdenCompra = new ArrayList<PagoOrdenCompra>();
	}
	
	public List<TransferenciaOrdenCompra> getListaTransferencias() {
		return listaTransferencias;
	}

	public void setListaTransferencias(
			List<TransferenciaOrdenCompra> listaTransferencias) {
		this.listaTransferencias = listaTransferencias;
	}

	public long getIdOrdenCompra() {
		return idOrdenCompra;
	}

	public void setIdOrdenCompra(long idOrdenCompra) {
		this.idOrdenCompra = idOrdenCompra;
	}

	public CondicionCompra getCondicionCompra() {
		return condicionCompra;
	}

	public void setCondicionCompra(CondicionCompra condicionCompra) {
		this.condicionCompra = condicionCompra;
	}
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public List<AutorizacionOrdenCompra> getListaFirmaPermisos() {
		return listaFirmaPermisos;
	}

	public void setListaFirmaPermisos(List<AutorizacionOrdenCompra> listaFirmaPermisos) {
		this.listaFirmaPermisos = listaFirmaPermisos;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor pProveedor) {
		this.proveedor = pProveedor;
	}
	
	public void addFirmaPermiso(FirmaPermiso pFirmaPermiso){
		AutorizacionOrdenCompra locAutorizacionOrdenCompra = new AutorizacionOrdenCompra();
		locAutorizacionOrdenCompra.setFirmaPermiso(pFirmaPermiso);
		locAutorizacionOrdenCompra.setOrdenCompra(this);
		this.listaFirmaPermisos.add(locAutorizacionOrdenCompra);
	}

	public List<LineaOrdenCompra> getListaLineaOrdenCompra() {
		return listaLineaOrdenCompra;
	}

	public void setListaLineaOrdenCompra(
			List<LineaOrdenCompra> listaLineaOrdenCompra) {
		this.listaLineaOrdenCompra = listaLineaOrdenCompra;
	}
	
	
	public void addLineaOrdenCompra(LineaOrdenCompra pLineaOrdenCompra){
		pLineaOrdenCompra.setOrdenCompra(this);
		this.listaLineaOrdenCompra.add(pLineaOrdenCompra);
	}
	
	public void addFirma(FirmaPermiso pFirmaPermiso) throws TrascenderException {
		AutorizacionOrdenCompra locAutorizacionOrdenCompra = new AutorizacionOrdenCompra();
		locAutorizacionOrdenCompra.setFirmaPermiso(pFirmaPermiso);
		locAutorizacionOrdenCompra.setOrdenCompra(this);
		this.listaFirmaPermisos.add(locAutorizacionOrdenCompra);
	}
	
	public boolean yaFirmo(Usuario pUsuario){
		boolean encontrado = false;
		for (Iterator<AutorizacionOrdenCompra> iterator = this.getListaFirmaPermisos().iterator(); iterator.hasNext() && !encontrado;) {
			AutorizacionOrdenCompra cadaAutorizacion = iterator.next();
			encontrado = cadaAutorizacion.getFirmaPermiso().getUsuario().equals(pUsuario); 
		}
		return encontrado;
	}
	
	private void validarArmadoPagos() throws TrascenderException{
		for (PagoOrdenCompra cadaPago : this.listaPagosOrdenCompra){
			if (cadaPago.getFactura() != null){
				throw new TrascenderComprasException(22);
			}
		}
		this.listaPagosOrdenCompra.clear();
	}
	
	@Override
	public String toString() {
		DateFormat formato=DateFormat.getDateInstance(DateFormat.SHORT);
		if (this.getProveedor() != null)
			return "Orden Compra ["+this.numero+"] - "+formato.format(this.fechaEmision)+" - "+this.getProveedor().toString();
		else
			return "";
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public void setTotal(){
		Double sum = 0d;
		try{
			for (LineaOrdenCompra linea: this.getListaLineaOrdenCompra()){
				sum +=linea.getMontoTotal();
			}
		} catch (LazyInitializationException e){
		} catch (Exception e){
		}
		this.total = sum;
	}
	
	public void generarPago(String pNombre, Double pMonto){
		PagoOrdenCompra locPago = new PagoOrdenCompra();
		locPago.setNombre(pNombre);
		locPago.setMonto(pMonto);
		this.addPagoOrdenCompra(locPago);
	}
	
	/**
	 * Si el pValorPagoInicial no es nulo y no es 0, arma un pago
	 * inicial y devuelve el monto total menos el pago inicial.
	 * @param pValorPagoInicial
	 * @return
	 */
	private Double armarPagoInicial(Double pValorPagoInicial) throws TrascenderException{
		Double locMonto = new Double(this.total);
		if (pValorPagoInicial != null && pValorPagoInicial != 0D){
			if (pValorPagoInicial > this.total){
				throw new TrascenderComprasException(442);
			}
			this.generarPago("Pago inicial", pValorPagoInicial);
			locMonto -= pValorPagoInicial;
		}
		return locMonto;
	}
	
	private void generarPagos(Double pMonto, Double montoCadaCuota){
		Double locMonto = new Double(pMonto);
		int i = 1;
		while (locMonto >= montoCadaCuota){
			this.generarPago("Pago " + i++, montoCadaCuota);
			locMonto -= montoCadaCuota;
		}
		//Si quedo un restito.
		if (!locMonto.equals(0D)){
			this.generarPago("Pago " + i++, Util.redondear(locMonto, 2));
		}
	}
	
	public void generarPagosPorCantidadCuotas(Double pagoInicial, int cantidadCuotas) throws TrascenderException{
		this.validarArmadoPagos();
		Double locMonto = this.armarPagoInicial(pagoInicial);
		if (locMonto <= 0D) return;
		Double locValorCuota = locMonto / cantidadCuotas;
		locValorCuota = this.redondearHaciaArriba(locValorCuota);
		this.generarPagos(locMonto, locValorCuota);
	}
	
	public void generarPagosPorPorcentajeCuota(Double pagoInicial, Double porcentajeCuotas) throws TrascenderException{
		this.validarArmadoPagos();
		Double locMonto = this.armarPagoInicial(pagoInicial);
		if (locMonto <= 0D) return;
		Double locMontoCuota = porcentajeCuotas * locMonto / 100;
		locMontoCuota = this.redondearHaciaArriba(locMontoCuota);
		this.generarPagos(locMonto, locMontoCuota);
	}
	
	public void generarPagosPorValorCuota(Double pPagoInicial, Double pMontoCuotas) throws TrascenderException{
		this.validarArmadoPagos();
		if (pMontoCuotas.equals(0D)){
			throw new TrascenderComprasException(441);
		}
		Double locMonto = this.armarPagoInicial(pPagoInicial);
		if (locMonto <= 0D) return;
		this.generarPagos(locMonto, pMontoCuotas);
	}
	
	private Double redondearHaciaArriba(Double pValor){
		BigDecimal bd = new BigDecimal(pValor);
		bd = bd.setScale(2, RoundingMode.UP);
		return bd.doubleValue();
	}
	
	public void addPagoOrdenCompra(PagoOrdenCompra pPago){
		pPago.setOrdenCompra(this);
		this.listaPagosOrdenCompra.add(pPago);
	}
	
	public List<LineaMovimientoMercaderia> getListaLineasMovimientoMercaderia(){
		List<LineaMovimientoMercaderia> locListaLineasMovimiento = new ArrayList<LineaMovimientoMercaderia>();
		for (LineaOrdenCompra cadaLinea : this.listaLineaOrdenCompra){
			if (cadaLinea.getLineaMovimientoMercaderia() != null) {
				locListaLineasMovimiento.add(cadaLinea.getLineaMovimientoMercaderia());
			}
		}
		return locListaLineasMovimiento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idOrdenCompra ^ (idOrdenCompra >>> 32));
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
		OrdenCompra other = (OrdenCompra) obj;
		if (idOrdenCompra != other.idOrdenCompra)
			return false;
		return true;
	}
	
	public void validarCumplimiento(){
		Double montoPagado = 0D;
		for(PagoOrdenCompra cadaPago : listaPagosOrdenCompra){
			if(cadaPago.getFactura() != null){
				montoPagado += cadaPago.getMonto();
			}
		}
		if(montoPagado >= this.getTotal()){
			this.estado = OrdenCompra.Estado.CUMPLIDA;
		}
	}

	public long getIdEntidad() {
		return idOrdenCompra;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idOrdenCompra";
	}

	public boolean isAuditable() {
		return true;
	}


	// ********************************************************************************************************************************/
	// AUDITORIA

	@Transient
	private long llaveUsuarioAuditoria;
	@Transient
	private String comentarioAuditoria;

	@OrderBy(value = "fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

	public List<LogAuditoria> getListaLogsAuditoria() {
		return listaLogsAuditoria;
	}

	public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
		this.listaLogsAuditoria = pListaLogsAuditoria;
	}

	public long getLlaveUsuarioAuditoria() {
		return llaveUsuarioAuditoria;
	}

	public void setLlaveUsuarioAuditoria(long llaveUsuarioAuditoria) {
		this.llaveUsuarioAuditoria = llaveUsuarioAuditoria;
	}

	public String getComentarioAuditoria() {
		return comentarioAuditoria;
	}

	public void setComentarioAuditoria(String comentarioAuditoria) {
		this.comentarioAuditoria = comentarioAuditoria;
	}
}
