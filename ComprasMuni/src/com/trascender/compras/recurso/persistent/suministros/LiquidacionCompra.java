package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;

@Entity
@Table(name = "LIQUIDACION_COMPRA")
public class LiquidacionCompra implements Serializable, EntidadTrascender{
	
	public static final long serialVersionUID = -235210748177601029L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_liquidacion_compra")
	@SequenceGenerator(name = "gen_id_liquidacion_compra", sequenceName = "gen_id_liquidacion_compra",allocationSize = 1)
	@Column(name="ID_LIQUIDACION_COMPRA")
	private long idLiquidacionCompra = -1;
	
	@OneToMany(mappedBy = "liquidacionCompra", cascade = CascadeType.ALL)
	private List<Factura> listaFacturas = new ArrayList<Factura>();
	
	@Transient
	private List<LineaLiquidacionCompra> listaLineasLiquidacionCompra = new ArrayList<LineaLiquidacionCompra>();
	
	private Date fecha;
	
	private String numero;

	public long getIdLiquidacionCompra() {
		return idLiquidacionCompra;
	}

	public void setIdLiquidacionCompra(long idLiquidacionCompra) {
		this.idLiquidacionCompra = idLiquidacionCompra;
	}

	public List<Factura> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(List<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<LineaLiquidacionCompra> getListaLineasLiquidacionCompra() {
		return listaLineasLiquidacionCompra;
	}

	public void setListaLineasLiquidacionCompra(
			List<LineaLiquidacionCompra> listaLineasLiquidacionCompra) {
		this.listaLineasLiquidacionCompra = listaLineasLiquidacionCompra;
	}
	
	public List<LineaLiquidacionCompra> recrearListaLineasLiquidacionCompra(){
		List<LineaLiquidacionCompra> listaRetorno = new ArrayList<LineaLiquidacionCompra>();
		this.listaLineasLiquidacionCompra.clear();
		for(Factura cadaFactura : this.listaFacturas){
			for(LineaFactura cadaLineaFactura : cadaFactura.getListaLineaFactura()){
				boolean encontrado = false;
				for(LineaLiquidacionCompra cadaLineaLiquidacionExistente : this.listaLineasLiquidacionCompra){
					if(cadaLineaFactura.getNombre().equals(cadaLineaLiquidacionExistente.getNombre())
							&& cadaLineaFactura.getMontoUnitario().equals(cadaLineaLiquidacionExistente.getMontoUnitario())){
						cadaLineaLiquidacionExistente.setCantidad(cadaLineaLiquidacionExistente.getCantidad() + cadaLineaFactura.getCantidad());
						cadaLineaLiquidacionExistente.setMontoTotal(cadaLineaLiquidacionExistente.getCantidad() * cadaLineaLiquidacionExistente.getMontoUnitario());
						encontrado = true;
					}
				}
				
				if(!encontrado){
					LineaLiquidacionCompra nuevaLinea = new LineaLiquidacionCompra();
					nuevaLinea.setNombre(cadaLineaFactura.getNombre());
					nuevaLinea.setCantidad(cadaLineaFactura.getCantidad());
					nuevaLinea.setMontoTotal(cadaLineaFactura.getTotal());
					nuevaLinea.setMontoUnitario(cadaLineaFactura.getMontoUnitario());
					
					if(cadaLineaFactura instanceof LineaFacturaBien){
						nuevaLinea.setUnidadMedida(((LineaFacturaBien)cadaLineaFactura).getBien().getUnidad());
					} else if(cadaLineaFactura instanceof LineaFacturaLineaOrdenCompra){
						nuevaLinea.setUnidadMedida(((LineaFacturaLineaOrdenCompra)cadaLineaFactura).getLineaOrdenCompra().getBien().getUnidad());
					}
					
					
//					nuevaLinea.setMontoUnitario(montoUnitario)
					this.listaLineasLiquidacionCompra.add(nuevaLinea);
				}
			}
		}
		return this.listaLineasLiquidacionCompra;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idLiquidacionCompra ^ (idLiquidacionCompra >>> 32));
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
		LiquidacionCompra other = (LiquidacionCompra) obj;
		if (idLiquidacionCompra != other.idLiquidacionCompra)
			return false;
		return true;
	}
	
	public String getStringListaFacturas(){
		if(!this.listaFacturas.isEmpty()){
			StringBuffer stringRetorno = new StringBuffer();
			stringRetorno.append("[");
			for(Factura cadaFactura : this.listaFacturas){
				stringRetorno.append(cadaFactura.getNumero() + ", ");
			}
			stringRetorno.delete(stringRetorno.lastIndexOf(", "), stringRetorno.length());
			stringRetorno.append("]");
			return stringRetorno.toString();
		}
		return "";
	}
	
	public void setStringListaFacturas(String pString){
	}
	
	public class LineaLiquidacionCompra implements Serializable{
		private static final long serialVersionUID = 6693747227265238777L;
		
		private Unidad unidadMedida;
		private Double cantidad;
		private Double montoUnitario;
		private Double montoTotal;
		private String nombre;
		
		public LineaLiquidacionCompra() {
		}
		
		public LineaLiquidacionCompra(Unidad unidadMedida,
				Double cantidad, Double montoUnitario, Double montoTotal, String nombre) {
			super();
			this.unidadMedida = unidadMedida;
			this.cantidad = cantidad;
			this.montoUnitario = montoUnitario;
			this.montoTotal = montoTotal;
			this.nombre = nombre;
		}
		
		public Unidad getUnidadMedida() {
			return unidadMedida;
		}
		public void setUnidadMedida(Unidad unidadMedida) {
			this.unidadMedida = unidadMedida;
		}
		public Double getCantidad() {
			return cantidad;
		}
		public void setCantidad(Double cantidad) {
			this.cantidad = cantidad;
		}
		
		public Double getMontoUnitario() {
			return montoUnitario;
		}

		public void setMontoUnitario(Double montoUnitario) {
			this.montoUnitario = montoUnitario;
		}

		public Double getMontoTotal() {
			return montoTotal;
		}

		public void setMontoTotal(Double montoTotal) {
			this.montoTotal = montoTotal;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
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

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public long getIdEntidad() {
		return this.getIdLiquidacionCompra();
	}

	public String getNombrePropiedadId() {
		return "idLiquidacionCompra";
	}

	public boolean isAuditable() {
		return true;
	}
}
