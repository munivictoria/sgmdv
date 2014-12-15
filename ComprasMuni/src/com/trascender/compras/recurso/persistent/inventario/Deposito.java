package com.trascender.compras.recurso.persistent.inventario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;

@Entity
@Table(name = "DEPOSITO")
public class Deposito implements Serializable, EntidadTrascender{

	public static final long serialVersionUID = -3492758683374759584L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_deposito")
	@SequenceGenerator(name = "gen_id_deposito", sequenceName = "gen_id_deposito",allocationSize = 1)
	@Column(name="ID_DEPOSITO")
	private long idDeposito = -1L;
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "ID_AREA")
	private Area area;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_DOMICILIO")
	private Domicilio domicilio;
	private String telefono;
	
	@OneToMany(mappedBy = "deposito", orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<Stock> listaStock = new HashSet<Stock>();
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public Domicilio getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Set<Stock> getListaStock() {
		return listaStock;
	}
	public void setListaStock(Set<Stock> listaDetallesStock) {
		this.listaStock = listaDetallesStock;
	}
	
	
	@Override
	public String toString() {
		return nombre + ",[Area:" + area + "]";
	}
	
	
	public long getIdDeposito() {
		return idDeposito;
	}
	public void setIdDeposito(long idDeposito) {
		this.idDeposito = idDeposito;
	}
	@Override
	public int hashCode() {
		if (this.idDeposito == -1){
			return super.hashCode();
		}
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idDeposito ^ (idDeposito >>> 32));
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
		Deposito other = (Deposito) obj;
		if (idDeposito != other.idDeposito)
			return false;
		return true;
	}
	
	public Stock getStockPorBien(Bien pBien){
		for (Stock cadaStock : this.listaStock){
			if (cadaStock.getBien().equals(pBien)){
				return cadaStock;
			}
		}
		return null;
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
			return this.idDeposito;
		}

		public String getNombrePropiedadId() {
			return "idDeposito";
		}

		public boolean isAuditable() {
			return true;
		}
}
