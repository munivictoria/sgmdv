package com.trascender.compras.recurso.persistent.inventario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import org.hibernate.annotations.Where;

import com.trascender.compras.exception.TrascenderInventarioException;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;

/**
 * @hibernate.class table = "MOVIMIENTO_MERCADERIA"
 * @author ignacio
 *
 */
@Entity
@Table(name = "MOVIMIENTO_MERCADERIA")
public class MovimientoDeMercaderia implements Serializable, EntidadTrascender{

	public static final long serialVersionUID = -1898235179840621994L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_movimiento_mercaderia")
	@SequenceGenerator(name = "gen_id_movimiento_mercaderia", sequenceName = "gen_id_movimiento_mercaderia",allocationSize = 1)
	@Column(name="ID_MOVIMIENTO_MERCADERIA")
	private long idMovimientoDeMercaderia = -1L;
	private String motivo;
	private Calendar fecha = Calendar.getInstance();
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "ID_DEPOSITO")
	private Deposito deposito;
	
	@ManyToOne
	@JoinColumn(name = "ID_DEPOSITO_DESTINO")
	private Deposito depositoDestino;
	
//	@Enumerated(EnumType.STRING)
//	private Estado estado = Estado.PENDIENTE;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_FIRMA_PERMISO_HAB")
	private FirmaPermiso firmaReceptor;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "movimientoDeMercaderia", orphanRemoval = true)
	private List<LineaMovimientoMercaderia> listaLineasMovimientoMercaderia = new ArrayList<LineaMovimientoMercaderia>();
	
	public enum Tipo{INGRESO, EGRESO, MOVIMIENTO;
		@Override
		public String toString(){
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
		
	}
	
//	public enum Estado{PENDIENTE, RECIBIDO, ANULADO;
//		@Override
//		public String toString() {
//			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
//		}
//	}
	
	public void addLineaMovimientoMercaderia(LineaMovimientoMercaderia pLinea){
		pLinea.setMovimientoDeMercaderia(this);
		this.listaLineasMovimientoMercaderia.add(pLinea);
	}
	
	public List<LineaMovimientoMercaderia> getListaLineasMovimientoMercaderia() {
		return listaLineasMovimientoMercaderia;
	}
	public void setListaLineasMovimientoMercaderia(
			List<LineaMovimientoMercaderia> listaLineasMovimientoMercaderia) {
		this.listaLineasMovimientoMercaderia = listaLineasMovimientoMercaderia;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
//	public Estado getEstado() {
//		return estado;
//	}
//	public void setEstado(Estado estado) {
//		this.estado = estado;
//	}
	public FirmaPermiso getFirmaReceptor() {
		return firmaReceptor;
	}
	public void setFirmaReceptor(FirmaPermiso firmaReceptor) {
		this.firmaReceptor = firmaReceptor;
	}
	@Override
	public int hashCode() {
		if (this.idMovimientoDeMercaderia == -1){
			return super.hashCode();
		}
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idMovimientoDeMercaderia ^ (idMovimientoDeMercaderia >>> 32));
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
		MovimientoDeMercaderia other = (MovimientoDeMercaderia) obj;
		if (idMovimientoDeMercaderia != other.idMovimientoDeMercaderia)
			return false;
		return true;
	}
	public long getIdMovimientoDeMercaderia() {
		return idMovimientoDeMercaderia;
	}
	public void setIdMovimientoDeMercaderia(long idMovimientoDeMercaderia) {
		this.idMovimientoDeMercaderia = idMovimientoDeMercaderia;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public Deposito getDeposito() {
		return deposito;
	}
	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
		for (LineaMovimientoMercaderia cadaLinea : this.listaLineasMovimientoMercaderia){
			Stock locStock = this.deposito != null ?  
					this.deposito.getStockPorBien(cadaLinea.getBien()) : 
					null;
			cadaLinea.setStock(locStock);
		}
	}
	public Deposito getDepositoDestino() {
		return depositoDestino;
	}
	public void setDepositoDestino(Deposito depositoDestino) {
		this.depositoDestino = depositoDestino;
		for (LineaMovimientoMercaderia cadaLinea : this.listaLineasMovimientoMercaderia){
			Stock locStock = this.depositoDestino != null ? 
					this.depositoDestino.getStockPorBien(cadaLinea.getBien()) :
					null;
			cadaLinea.setStockDestino(locStock);
		}
	}
	
	public void setOrdenCompra(OrdenCompra pOrdenCompra){
		this.listaLineasMovimientoMercaderia.clear();
		if (this.deposito == null) return;
		for (LineaOrdenCompra cadaLineaOC : pOrdenCompra.getListaLineaOrdenCompra()){
			//Si ya se ingresaron todos los bienes
			if (cadaLineaOC.getLineaMovimientoMercaderia() != null &&
					cadaLineaOC.getLineaMovimientoMercaderia().getCantidad().equals(
							cadaLineaOC.getCantidad())) continue;
			Stock locStock = this.deposito.getStockPorBien(cadaLineaOC.getBien());
			if (locStock == null) continue;
			LineaMovimientoMercaderia locLineaMovimiento = new LineaMovimientoMercaderia();
			locLineaMovimiento.setCantidad(cadaLineaOC.getCantidad());
			locLineaMovimiento.setLineaOrdenCompra(cadaLineaOC);
			locLineaMovimiento.setStock(locStock);
			locLineaMovimiento.setBien(cadaLineaOC.getBien());
			this.addLineaMovimientoMercaderia(locLineaMovimiento);
		}
		this.setTipo(Tipo.INGRESO);
	}
	
	public void setSolicitudSuministro(SolicitudSuministro pSolicitud){
		this.listaLineasMovimientoMercaderia.clear();
		if (this.deposito == null) return;
		for (LineaSolicitudSuministro cadaLineaSS : pSolicitud.getListaLineaSolSuministro()){
			//TODO Validar que haya cantidades pendientes, es decir, cantidades que no esten pedidas en Contrataciones.
//			//Si esta asociada a una Contratacion
//			if (cadaLineaSS.getlistalinea != null) continue;
			//Si ya se entragaron todas las cantidades
			if (cadaLineaSS.getCantidadEntregasPrevias().equals(cadaLineaSS.getCantidad())) continue;
			Stock locStock = this.deposito.getStockPorBien(cadaLineaSS.getBien());
			if (locStock == null) continue;
			LineaMovimientoMercaderia locLineaMovimiento = new LineaMovimientoMercaderia();
			locLineaMovimiento.setCantidad(cadaLineaSS.getCantidad());
			locLineaMovimiento.setLineaSolicitudSuministro(cadaLineaSS);
			locLineaMovimiento.setStock(locStock);
			locLineaMovimiento.setBien(cadaLineaSS.getBien());
			if (depositoDestino != null) {
				locLineaMovimiento.setStockDestino(this.depositoDestino.getStockPorBien(cadaLineaSS.getBien()));
			}
			this.addLineaMovimientoMercaderia(locLineaMovimiento);
		}
	}
	
	public void validarCantidadesPrevias() throws TrascenderInventarioException{
		for (LineaMovimientoMercaderia cadaLinea : this.listaLineasMovimientoMercaderia){
			if (cadaLinea.getLineaSolicitudSuministro() != null && 
					cadaLinea.getCantidad() + cadaLinea.getCantidadEntregasPrevias()
					> cadaLinea.getLineaSolicitudSuministro().getCantidad()) {
				throw new TrascenderInventarioException(147);
			}
		}
	}
	
	public void validarCantidades() throws TrascenderException{
		if (this.tipo == Tipo.INGRESO) return;
		for (LineaMovimientoMercaderia cadaLineaMovimiento : this.getListaLineasMovimientoMercaderia()) {
			if(cadaLineaMovimiento.getStock() == null) return;
			Double locCantidad = cadaLineaMovimiento.getStock().getCantidad();
			if (cadaLineaMovimiento.getCantidad() > locCantidad) {
				throw new TrascenderInventarioException(140);
			}
		}
	}
	
	public void validarTiposStock() throws TrascenderException {
		if (this.tipo != Tipo.MOVIMIENTO) return;
		for (LineaMovimientoMercaderia cadaLineaMovimiento : this.getListaLineasMovimientoMercaderia()){
			if (cadaLineaMovimiento.getStock()!=null && cadaLineaMovimiento.getStockDestino()!=null &&
					!cadaLineaMovimiento.getStock().getBien().equals(cadaLineaMovimiento.getStockDestino().getBien())) {
				throw new TrascenderInventarioException(143);
			}
		}
	}
	
	public Date getFechaDate(){
		return this.getFecha()!=null?this.getFecha().getTime():null;
	}
	
	public void setFechaDate(){}
	
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

		public long getSerialVersionUID() {
			return serialVersionUID;
		}

		public long getIdEntidad() {
			return this.idMovimientoDeMercaderia;
		}

		public String getNombrePropiedadId() {
			return "idMovimientoDeMercaderia";
		}

		public boolean isAuditable() {
			return true;
		}
}
