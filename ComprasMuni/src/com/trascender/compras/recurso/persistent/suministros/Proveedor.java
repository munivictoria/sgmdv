/**
 * 
 */
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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;

@Entity
@Table(name = "PROVEEDOR")
public class Proveedor implements Serializable, EntidadTrascender {
	public static final long serialVersionUID = 3195013600657283499L;

	public enum Estado{
		ACTIVO,INACTIVO
	}
	public enum Tipo{
		GENERAL, CONSULTOR, CONTRATISTA
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_proveedor")
	@SequenceGenerator(name = "gen_id_proveedor", sequenceName = "gen_id_proveedor",allocationSize = 1)
	@Column(name="ID_PROVEEDOR")
	private long idProveedor=-1;

	@ManyToOne
	@JoinColumn(name = "ID_PERSONA")
	private Persona proveedorLocal;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOMICILIO")
	private Domicilio domicilio;

	private String codigo;

	private String contacto;
	private String telefono;
	private String email;

	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.ACTIVO;

	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	@Transient
	//    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BienProvisto> listaBienProvisto;

	@OneToMany
	@JoinTable(name="RELA_PROVEEDOR_CIIU", joinColumns=@JoinColumn(name="ID_PROVEEDOR"),
	inverseJoinColumns=@JoinColumn(name="ID_CODIGO_CIIU"))
	private List<CodigoCiiu> listaCodigosCiiu = new ArrayList<CodigoCiiu>();

	@OneToMany
	@JoinTable(name="RELA_PROVEEDOR_TIPO_BIEN", joinColumns=@JoinColumn(name="ID_PROVEEDOR", nullable = false), 
	inverseJoinColumns=@JoinColumn(name="ID_TIPO_BIEN", nullable = false))
	private List<TipoBien> listaTipoBien = new ArrayList<TipoBien>();

	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();
	
	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.getIdProveedor());
		this.listaAtributosDinamicos.add(pAtributoDinamico);
	}
	
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}
	
	public void setListaAtributosDinamicos(List<AtributoDinamico<?>> pListaAtributosDinamicos) {
		this.listaAtributosDinamicos.clear();
		for (AtributoDinamico<?> cadaAtributo : pListaAtributosDinamicos){
			if (cadaAtributo.getValor() != null){
				this.addAtributoDinamico(cadaAtributo);
			}
		}
	}
	
	public Proveedor(){
		listaBienProvisto = new ArrayList<BienProvisto>();
	}

	public String getRazonSocial(){
		return this.proveedorLocal != null ? this.getProveedorLocal().getDenominacion() : null;
	}

	public String getCuit(){
		return this.proveedorLocal != null ? this.getProveedorLocal().getCuim() : null;
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(long idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public Domicilio getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Persona getProveedorLocal() {
		return proveedorLocal;
	}
	public void setProveedorLocal(Persona proveedorLocal) {
		this.proveedorLocal = proveedorLocal;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public List<CodigoCiiu> getListaCodigosCiiu() {
		return listaCodigosCiiu;
	}

	public void setListaCodigosCiiu(List<CodigoCiiu> listaCodigosCiiu) {
		this.listaCodigosCiiu = listaCodigosCiiu;
	}

	@Override
	public String toString() {
		if(this.getProveedorLocal() != null){
			return this.getProveedorLocal().getDenominacion();
		}
		return null;
	}

	public List<BienProvisto> getListaBienProvisto() {
		return listaBienProvisto;
	}
	public void setListaBienProvisto(List<BienProvisto> bienProvisto) {
		this.listaBienProvisto = bienProvisto;
	}
	public void addBien(Bien bien, double precio, String descripcion){
		BienProvisto locBienProvisto = new BienProvisto();
		locBienProvisto.setBien(bien);
		locBienProvisto.setProveedor(this);
		locBienProvisto.setPrecio(precio);
		locBienProvisto.setDescripcion(descripcion);
		this.listaBienProvisto.add(locBienProvisto);
	}
	public Bien getBien(int index){
		return (this.listaBienProvisto.get(index)).getBien();
	}
	public void addBienProvisto(BienProvisto pBienProvisto){
		pBienProvisto.setProveedor(this);
		this.listaBienProvisto.add(pBienProvisto);
	}
	public void deleteBienProvisto(BienProvisto pBienProvisto){
		this.listaBienProvisto.remove(pBienProvisto);
	}
	public List<Bien> getListaBienes(){
		List<Bien> listaBienes = new ArrayList<Bien>();
		for (BienProvisto locBienProvisto: this.listaBienProvisto){
			listaBienes.add(locBienProvisto.getBien());
		}
		return listaBienes;
	}
	public BienProvisto getBienProvistoByID(Bien pBien){
		BienProvisto resultBienProvisto=null;
		for (BienProvisto locBienProvisto: this.listaBienProvisto){
			if (locBienProvisto.getBien().getIdBien()==pBien.getIdBien()){
				resultBienProvisto = locBienProvisto;
				break;
			}
		}
		return resultBienProvisto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idProveedor ^ (idProveedor >>> 32));
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
		Proveedor other = (Proveedor) obj;
		if (idProveedor != other.idProveedor)
			return false;
		return true;
	}

	public List<TipoBien> getListaTipoBien() {
		return listaTipoBien;
	}

	public void setListaTipoBien(List<TipoBien> listaTipoBien) {
		this.listaTipoBien = listaTipoBien;
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
			return this.idProveedor;
		}

		public String getNombrePropiedadId() {
			return "idProveedor";
		}

		public boolean isAuditable() {
			return true;
		}
}