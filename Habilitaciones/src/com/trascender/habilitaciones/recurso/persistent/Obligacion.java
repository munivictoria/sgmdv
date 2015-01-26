package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;

/**
 * Representa una obligacion por parte del contribuyente con el municipio
 */

@Entity
@Table(name = "OBLIGACION")
public class Obligacion implements Serializable, AuditoriaIndirecta{

	public static final long serialVersionUID = 3159134484007399357L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_obligacion")
	@SequenceGenerator(name = "gen_id_obligacion", sequenceName = "gen_id_obligacion", allocationSize = 1)
	@Column(name = "ID_OBLIGACION")
	private long idObligacion=-1;

	private String nombre;

	private String descripcion;

	@Enumerated(EnumType.STRING)
	private Estado estado=Estado.CREADO;

	@OneToMany(mappedBy = "obligacion")
	private Set<RegistroExencionObligacion> listaRegistrosExencion = new HashSet<RegistroExencionObligacion>();

	//El estado anterior sirve para ver cual era el estado antes de ser marcada como exenta
	@Column(name = "ESTADO_ANTERIOR")
	@Enumerated(EnumType.STRING)
	private Estado estadoAnterior = Estado.CREADO;

	@Column(name = "NUMERO_TRAMITE", nullable = false)
	private Integer numeroTramite=0;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ID_PERSONA")
	private Persona persona;

	/**
	 * Estados: CREADO,PREHABILITADO,HABILITADO,INHABILITADO,ANULADO,TERMINADO,EXENTA, PENDIENTE_DE_ALTA
	 */
	public enum Estado{CREADO,PREHABILITADO,HABILITADO,INHABILITADO,ANULADO,TERMINADO,EXENTA, PENDIENTE_DE_ALTA;
	@Override
	public String toString() {
		return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
	}
	};

	@OneToMany(mappedBy = "obligacion", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DocHabilitante> listaDocumentosHabilitantes = new HashSet<DocHabilitante>();

	@OneToOne(mappedBy = "obligacion", cascade = CascadeType.ALL)
	private DocHabilitanteEspecializado documentoEspecializado;

	public DocHabilitanteEspecializado getDocumentoEspecializado() {
		return documentoEspecializado;
	}
	public void setDocumentoEspecializado(
			DocHabilitanteEspecializado documentoEspecializado) {
		this.documentoEspecializado = documentoEspecializado;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String pDescripcion) {
		descripcion = pDescripcion;
	}

	public long getIdObligacion() {
		return idObligacion;
	}
	public void setIdObligacion(long pIdObligacion) {
		idObligacion = pIdObligacion;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado pEstado) {
		estado = pEstado;
	}

	public Estado getEstadoAnterior() {
		return estadoAnterior;
	}
	public void setEstadoAnterior(Estado pEstadoAnterior) {
		this.estadoAnterior = pEstadoAnterior;
	}

	public Set<RegistroExencionObligacion> getListaRegistrosExencion() {
		return listaRegistrosExencion;
	}
	public void setListaRegistrosExencion(Set<RegistroExencionObligacion> pListaRegistrosExencion) {
		this.listaRegistrosExencion = pListaRegistrosExencion;
	}

	public boolean prehabilitar(){
		if (this.estado.equals(Estado.CREADO)){
			boolean prehabilitar=true;
			for (DocHabilitante locDocHabilitante: this.getListaDocumentosHabilitantes()){
				prehabilitar=prehabilitar&&locDocHabilitante.getEstado().equals(DocHabilitante.Estado.HABILITADO);
			}
			if (prehabilitar)
				this.setEstado(Estado.PREHABILITADO);
			return prehabilitar;
		}
		else{
			return false;
		}
	}

	/**
	 * Habilita una obligación que se encuentra prehabilitada o en el caso que se encuentre inhabilitada
	 * verifica que pueda ser habilitada verificando que sus hijos estón habilitados
	 * @return
	 */
	public boolean habilitar(){
		boolean habilito=false;
		if (this.estado.equals(Estado.PREHABILITADO)){
			habilito=true;
		}
		else if (this.getEstado().equals(Estado.INHABILITADO)){
			habilito=true;
			for (DocHabilitante locDocHabilitante: this.getListaDocumentosHabilitantes()){
				habilito=habilito&&(locDocHabilitante.getEstado().equals(DocHabilitante.Estado.HABILITADO)||(locDocHabilitante.getEstado().equals(DocHabilitante.Estado.TERMINADO)));
			}
		}
		if (habilito) this.setEstado(Estado.HABILITADO);
		return habilito;
	}

	public boolean anular() throws Exception{
		if ((this.estado.equals(Estado.PREHABILITADO))
				||(this.estado.equals(Estado.INHABILITADO))
				||(this.getEstado().equals(Estado.CREADO))){
			this.setEstado(Estado.ANULADO);
			if (this.getDocumentoEspecializado()!=null){
				this.getDocumentoEspecializado().setEstado(DocHabilitanteEspecializado.Estado.INACTIVO);
			}
			return true;
		}
		else if(this.estado.equals(Estado.EXENTA)){
			throw new HabilitacionesException(910);
		}
		return false;
	}

	/**
	 * vuelve a dar de alta una obligacion.
	 * @return true si se activo
	 */
	public boolean reActivar(){
		this.setEstado(Estado.CREADO);
		this.getDocumentoEspecializado().setEstado(DocHabilitanteEspecializado.Estado.ACTIVO);
		return true;
	}

	public boolean ratificar(){
		if(this.estado.equals(Estado.ANULADO)){
			this.setEstado(Estado.CREADO);
			if(this.getDocumentoEspecializado() != null){
				this.getDocumentoEspecializado().setEstado(DocHabilitanteEspecializado.Estado.ACTIVO);
			}
			return true;
		}
		return false;
	}

	public boolean terminar(){
		boolean terminada=false;
		if (this.estado.equals(Estado.HABILITADO)){
			terminada=true;
			for (DocHabilitante locDocHabilitante: this.getListaDocumentosHabilitantes()){
				terminada=terminada&&locDocHabilitante.terminar();
			}

			if (terminada){
				this.setEstado(Estado.TERMINADO);
			}
		}
		return terminada;
	}

	public boolean inhabilitar(){
		if (this.getEstado().equals(Estado.HABILITADO)){
			this.setEstado(Estado.INHABILITADO);
			return true;
		}
		else{
			return false;
		}
	}

	public Integer getNumeroTramite() {
		return numeroTramite;
	}

	public void setNumeroTramite(Integer pNumeroTramite) {
		numeroTramite  = pNumeroTramite;
	}


	public Set<DocHabilitante> getListaDocumentosHabilitantes() {
		return listaDocumentosHabilitantes;
	}
	public void setListaDocumentosHabilitantes(
			Set<DocHabilitante> pListaDocumentosHabilitantes) {
		listaDocumentosHabilitantes = pListaDocumentosHabilitantes;
	}


	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public String getStringPersona() {
		return this.getPersona().toString();
	}
	
	public void setStringPersona(String pString){
	}

	@Override
	public String toString() {	
		return ((this.getPersona()!=null)?(this.getPersona()+" "):"")+((this.getDocumentoEspecializado()!=null)?this.getDocumentoEspecializado().toString():"");
	}

	public String getStringPoseeExenciones(){
		return (this.getListaRegistrosExencion() == null || this.getListaRegistrosExencion().isEmpty())?"No":"Si";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idObligacion ^ (idObligacion >>> 32));
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
		Obligacion other = (Obligacion) obj;
		if (idObligacion != other.idObligacion)
			return false;
		return true;
	}

	public Parcela getParcela(){
		return this.getDocumentoEspecializado().getParcela();
	}

	public RegAlicuota getRegistroAlicuota() {
		return this.getDocumentoEspecializado().getRegistroAlicuota();
	}
	
	public void setStringListaRegAlicuotas(String pString){
	}
	
	public String getStringListaRegAlicuotas(){
		String stringResultado = new String();
		for (Iterator<RegAlicuota> iterator = 
				this.getDocumentoEspecializado().getListaRegAlicuotas().iterator(); iterator.hasNext();) {
			RegAlicuota cadaLinea = iterator.next();
			stringResultado += cadaLinea.toString();
			if (iterator.hasNext()) stringResultado += ", ";
		}
		return stringResultado;
	}
	
	public String getCalle(){
		return this.documentoEspecializado.getDomicilio().getCalle();
	}

	public String getAltura(){
		return this.documentoEspecializado.getDomicilio().getNumero();
	}

	public String getServicios() {
		String locServicios = new String();
		for (Iterator<AsocRegAlicuota> iterator = this.documentoEspecializado.getListaAsocRegAlicuota().iterator(); iterator.hasNext();) {
			ServicioOSP cadaServicio = (ServicioOSP) iterator.next().getRegistroAlicuota();
			locServicios += cadaServicio.getNombre();
			if (iterator.hasNext()) locServicios += ", ";
		}
		return locServicios;
	}

	public void setServicios(String servicios) {
	}

	/**
	 * Valido solo para los DocumentosSHPS
	 * @return
	 */
	public String getNumeroInscripcion(){
		String numero = null;
		if (this.documentoEspecializado instanceof DocumentoSHPS){
			DocumentoSHPS locDocumento = (DocumentoSHPS) documentoEspecializado;
			numero = locDocumento.getNumeroInscripcion();
		}
		return numero;
	}

	/**
	 * Valido solo para los Documentos OSP
	 * @return
	 */
	public Integer getNumeroCuenta(){
		Integer numero = null;
		if (this.documentoEspecializado instanceof DocumentoOSP){
			DocumentoOSP locDocumento = (DocumentoOSP) documentoEspecializado;
			numero = locDocumento.getNumeroCuenta();
		}
		return numero;
	}

	public void setComentarioAuditoria(String pComentario) {
		this.getDocumentoEspecializado().setComentarioAuditoria(pComentario);
	}

	public void setLlaveUsuarioAuditoria(long pLlave) {
		this.getDocumentoEspecializado().setLlaveUsuarioAuditoria(pLlave);
	}

	public long getLlaveUsuarioAuditoria() {
		return this.getDocumentoEspecializado().getLlaveUsuarioAuditoria();
	}

	public String getComentarioAuditoria() {
		return this.getDocumentoEspecializado().getComentarioAuditoria();
	}

	public EntidadTrascender getEntidadTrascender() {
		return this.documentoEspecializado;
	}
	public String getNombrePropiedad() {
		return "persona"; // Lo unico modificable de la obligacion es la persona, so...yeah
	}
	public boolean concatenaNombre() {
		return false;
	}


}
