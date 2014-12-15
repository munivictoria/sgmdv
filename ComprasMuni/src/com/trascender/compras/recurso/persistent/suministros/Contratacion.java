package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.AssociationOverride;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;

@Entity
@Table(name = "CONTRATACION")
public class Contratacion implements Serializable, EntidadTrascender {
	public static final long serialVersionUID = -3845653790452089864L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_contratacion")
	@SequenceGenerator(name = "gen_id_contratacion", sequenceName = "gen_id_contratacion",allocationSize = 1)
	@Column(name="ID_CONTRATACION")
	private long idContratacion = -1;

	@Column(name = "FECHA_ENTREGA")
	private Date fechaEntrega;

	@Column(name = "FECHA_PUBLICACION")
	private Date fechaPublicacion;

	@Column(name = "FECHA_CIERRE")
	private Date fechaCierre;

	@Column(name = "FECHA_APERTURA_SOBRES")
	private Date fechaAperturaSobres;

	private Integer numero;

	private String objeto;

	@Column(name = "NUMERO_EXPEDIENTE")
	private String numeroExpediente;

	@Column(name = "PRESUPUESTO_OFICIAL")
	private Double presupuestoOficial;

	private String comentarios;

	@Column(name = "PLAZO_OFERTA")
	private String plazoOferta;

	@Column(name = "EXTENSION_OFERTA")
	private String extensionOferta;

	@Enumerated(EnumType.STRING)
	private Estado estado;

	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ID_ACTA_APERTURA")
	private ActaApertura actaApertura;

	@Embedded
	@AttributeOverride(name="fecha", column=@Column(name = "fecha_adjudicacion"))
	@AssociationOverride(name = "digestoMunicipal", joinColumns = @JoinColumn(name = "ID_DIGESTO_ADJUDICACION"))
	private Adjudicacion adjudicacion = new Adjudicacion();

	@ManyToOne
	@JoinColumn(name = "ID_DIGESTO_MUNICIPAL")
	private DigestoMunicipal digestoMunicipal;

	@OneToMany(mappedBy = "contratacion", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LineaContratacion> listaLineasContratacion = new ArrayList<LineaContratacion>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contratacion", orphanRemoval = true)
	private List<OfertaContratacion> listaOfertasContratacion = new ArrayList<OfertaContratacion>();

	@OneToMany
	@JoinTable(name = "RELA_LICITACION_PROVEEDOR", joinColumns=@JoinColumn(name = "ID_LICITACION"), inverseJoinColumns = @JoinColumn(name = "ID_PROVEEDOR"))
	private List<Proveedor> listaProveedoresAutorizados = new ArrayList<Proveedor>();
	
	@Transient
	private String bienes; 

	public enum Estado{
		NUEVA, EN_PREPARACION, PROGRAMADA, PUBLICADA, CANCELADA, CERRADA, ADJUDICADA, DESIERTA;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}

	public enum Tipo{
		LICITACIÓN_PUBLICA, LICITACIÓN_PRIVADA, CONCURSO_PÚBLICO, CONCURSO_PRIVADO, CONTRATACIÓN_DIRECTA, SUBASTA;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}

	public void addLineaContratacion(LineaContratacion pLineaContratacion){
		pLineaContratacion.setContratacion(this);
		this.listaLineasContratacion.add(pLineaContratacion);
	}

	public void addOfertaContratacion(OfertaContratacion pOferta){
		pOferta.setContratacion(this);
		this.listaOfertasContratacion.add(pOferta);
	}

	public List<LineaContratacion> getListaLineasContratacion() {
		return listaLineasContratacion;
	}

	public void setListaLineasContratacion(
			List<LineaContratacion> listaLineasContratacion) {
		this.listaLineasContratacion = listaLineasContratacion;
	}

	public long getIdContratacion() {
		return idContratacion;
	}

	public void setIdContratacion(long idContratacion) {
		this.idContratacion = idContratacion;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Date getFechaAperturaSobres() {
		return fechaAperturaSobres;
	}

	public void setFechaAperturaSobres(Date fechaAperturaSobres) {
		this.fechaAperturaSobres = fechaAperturaSobres;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public String getNumeroExpediente() {
		return numeroExpediente;
	}

	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	public Double getPresupuestoOficial() {
		return presupuestoOficial;
	}

	public void setPresupuestoOficial(Double presupuestoOficial) {
		this.presupuestoOficial = presupuestoOficial;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getPlazoOferta() {
		return plazoOferta;
	}

	public void setPlazoOferta(String plazoOferta) {
		this.plazoOferta = plazoOferta;
	}

	public String getExtensionOferta() {
		return extensionOferta;
	}

	public void setExtensionOferta(String extensionOferta) {
		this.extensionOferta = extensionOferta;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public ActaApertura getActaApertura() {
		return actaApertura;
	}

	public void setActaApertura(ActaApertura actaApertura) {
		this.actaApertura = actaApertura;
	}

	public DigestoMunicipal getDigestoMunicipal() {
		return digestoMunicipal;
	}

	public void setDigestoMunicipal(DigestoMunicipal digestoMunicipal) {
		this.digestoMunicipal = digestoMunicipal;
	}

	public Adjudicacion getAdjudicacion() {
		return adjudicacion;
	}

	public void setAdjudicacion(Adjudicacion adjudicacion) {
		this.adjudicacion = adjudicacion;
	}

	public List<OfertaContratacion> getListaOfertasContratacion() {
		return listaOfertasContratacion;
	}

	public void setListaOfertasContratacion(
			List<OfertaContratacion> listaOfertasContratacion) {
		this.listaOfertasContratacion = listaOfertasContratacion;
	}

	public List<Proveedor> getListaProveedoresAutorizados() {
		return listaProveedoresAutorizados;
	}

	public void setListaProveedoresAutorizados(
			List<Proveedor> listaProveedoresAutorizados) {
		this.listaProveedoresAutorizados = listaProveedoresAutorizados;
	}

	/**
	 * Genera una nueva Linea de Contratacion en esta Contratacion a partir
	 * de la Linea de Solicitud, si existiera una linea para el mismo Bien,
	 * le suma la cantidad.
	 */
	public void addLineaSolicitudSuministro(LineaSolicitudSuministro pLineaSolicitud){
		boolean bandera = false;
		for (Iterator<LineaContratacion> iterator = listaLineasContratacion.iterator(); iterator.hasNext() && !bandera;) {
			LineaContratacion cadaLineaContratacion = iterator.next();
			if (cadaLineaContratacion.getBien().equals(pLineaSolicitud.getBien())){
				bandera = true;
				cadaLineaContratacion.setCantidad(cadaLineaContratacion.getCantidad() + pLineaSolicitud.getCantidad());
				cadaLineaContratacion.addLineaSolicitudSuministro(pLineaSolicitud);
			}
		}
		if (!bandera){
			LineaContratacion locLinea = this.generarLineaContratacion(pLineaSolicitud);
			this.addLineaContratacion(locLinea);
		}
	}

	/**
	 * Genera un OfertaContratacion para el Proveedor recibido por parametro, con los
	 * valores referenciales, y lo adjudica.
	 * @param pProveedor
	 */
	public void adjudicar(Proveedor pProveedor){
		OfertaContratacion locOferta = new OfertaContratacion();
		locOferta.setProveedor(pProveedor);
		for (LineaContratacion cadaLineaContratacion : this.listaLineasContratacion){
			LineaOfertaContratacion cadaLineaOfertaContratacion = new LineaOfertaContratacion();
			cadaLineaOfertaContratacion.setPrecioUnitario(cadaLineaContratacion.getValorReferencial());
			cadaLineaOfertaContratacion.setPrecioTotal(cadaLineaContratacion.getMontoTotalReferencial());
			cadaLineaOfertaContratacion.setLineaContratacion(cadaLineaContratacion);
			cadaLineaContratacion.setLineaOfertaContratacionAdjudicada(cadaLineaOfertaContratacion);
			locOferta.addLineaOfertaContratacion(cadaLineaOfertaContratacion);
		}
		this.addOfertaContratacion(locOferta);
	}

	private LineaContratacion generarLineaContratacion(LineaSolicitudSuministro pLineaSolicitud){
		LineaContratacion locLineaContratacion = new LineaContratacion();
		locLineaContratacion.setBien(pLineaSolicitud.getBien());
		locLineaContratacion.setCantidad(pLineaSolicitud.getCantidad());
		locLineaContratacion.addLineaSolicitudSuministro(pLineaSolicitud);
		return locLineaContratacion;
	}

	public boolean isCompletamenteAdjudicada(){
		for (LineaContratacion cadaLinea : this.listaLineasContratacion){
			if (cadaLinea.getLineaOfertaContratacionAdjudicada() == null){
				return false;
			}
		}
		return true;
	}

	public OfertaContratacion getOfertaPorProveedor(Proveedor pProveedor){
		for(OfertaContratacion cadaOferta : this.listaOfertasContratacion){
			if(cadaOferta.getProveedor().equals(pProveedor)){
				return cadaOferta;
			}
		}
		return null;
	}

	@Override
	public String toString(){
		return "Numero " + this.getNumero();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idContratacion ^ (idContratacion >>> 32));
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
		Contratacion other = (Contratacion) obj;
		if (idContratacion != other.idContratacion)
			return false;
		return true;
	}
	
	public String getBienes() {
		String locBienes = new String();
		for (Iterator<LineaContratacion> iterator = 
				this.getListaLineasContratacion().iterator(); iterator.hasNext();) {
			LineaContratacion cadaLinea = iterator.next();
			locBienes += cadaLinea.getBien().getNombre();
			if (iterator.hasNext()) locBienes += ", ";
		}
		this.bienes = locBienes;
		return bienes;
	}

	public void setBienes(String bienes) {
		this.bienes = bienes;
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
			return this.idContratacion;
		}

		public String getNombrePropiedadId() {
			return "idContratacion";
		}

		public boolean isAuditable() {
			return true;
		}
}
