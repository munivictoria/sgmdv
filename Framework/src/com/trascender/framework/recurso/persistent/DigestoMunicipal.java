package com.trascender.framework.recurso.persistent;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostPersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;


@Entity
@Table(name= "DIGESTO_MUNICIPAL")
public class DigestoMunicipal implements Serializable, EntidadTrascender{
	public static final long serialVersionUID = -694890491145148521L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_digesto_municipal")
	@SequenceGenerator(name="gen_id_digesto_municipal",sequenceName="gen_id_digesto_municipal",allocationSize = 1)
	@Column(name="ID_DIGESTO_MUNICIPAL")
	private long idDigestoMunicipal=-1;

	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Tipo tipo;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Estado estado;

	@Enumerated(EnumType.STRING)
	@Column(name="EJE_TEMATICO", nullable=false)
	private EjeTematico ejeTematico;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Ambito ambito;
	
	private String descripcion;
	
	@Column(nullable=false)
	private Date fecha;
	
	private Integer numero; 
	
	@ManyToMany(cascade= CascadeType.MERGE)
    @JoinTable(name="RELA_DIGESTO_DIGESTO",
         joinColumns={@JoinColumn(name="ID_DIGESTO_MUNICIPAL")},
         inverseJoinColumns={@JoinColumn(name="ID_DIGESTO_MUNICIPAL_NODO")})
	private Set<DigestoMunicipal> listaConcordancias;
	
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();
	
	/**
	 * Tipos posibles {ORDENANZA, DECRETO, RESOLUCION, COMUNICACION, ACUERDO, CONTRATO}
	 * @author jsantacruz
	 */
	public enum Tipo {
		ORDENANZA, DECRETO, RESOLUCION, COMUNICACION, ACUERDO, CONTRATO; //PROYECTO_DE_ORDENANZA, ACTAS_ACUERDO, GENERAL
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}

	/**
	 * Ejes posibles: {ORG_INSTITUCIONAL_Y_ADMINISTRATIVA, URBANISMO, OBRA_PUBLICA, CONTABILIDAD_Y_RECURSOS_FINANCIEROS,
	 * SERVICIOS_MUNICIPALES, PODER_DE_POLICIA}
	 * @author jsantacruz
	 */
	public enum EjeTematico {
		ORG_INSTITUCIONAL_Y_ADMINISTRATIVA, URBANISMO, OBRA_PUBLICA, CONTABILIDAD_Y_RECURSOS_FINANCIEROS,
		SERVICIOS_MUNICIPALES, PODER_DE_POLICIA;
		
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		};
	}
	
	/**
	 * Estados posibles {TRANSITORIA, VIGENTE, CUMPLIDA, DEROGADA}
	 * @author jsantacruz
	 */
	public enum Estado {
		TRANSITORIA, VIGENTE, CUMPLIDA, DEROGADA; //ARCHIVADA, EN_TRAMITE
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	
	/**
	 * Ambitos: {LEGISLACION_PROVINCIAL, LEGISLACION_NACIONAL}
	 * @author jsantacruz
	 */
	public enum Ambito {
		LEGISLACION_MUNICIPAL,
		LEGISLACION_PROVINCIAL,
		LEGISLACION_NACIONAL;
		
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		};
	}
	
	
	public DigestoMunicipal(){
		this.listaConcordancias = new HashSet<DigestoMunicipal>();
	}
	
	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.idDigestoMunicipal);
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

	public long getIdDigestoMunicipal() {
		return idDigestoMunicipal;
	}
	public void setIdDigestoMunicipal(long idDigestoMunicipal) {
		this.idDigestoMunicipal = idDigestoMunicipal;
	}

	public String getDescripcion(){
		return descripcion;
	}	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	} 
	
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public Estado getEstado(){
		return estado;
	}
	
	public void setEstado(Estado estado){
		this.estado = estado;
	}
	
	public Date getFecha(){
		return fecha;
	}
	
	public void setFecha(Date pFecha){
		this.fecha = pFecha;
	}
	
	public void addConcordancia(DigestoMunicipal pDigestoMunicipal){
		if (pDigestoMunicipal == null){
			return;
		}
		if (this.equals(pDigestoMunicipal)){
			return;
		}
		pDigestoMunicipal.getListaConcordancias().add(this);
		this.listaConcordancias.add(pDigestoMunicipal);
	}
	
	public void quitarConcordancia(DigestoMunicipal pDigestoMunicipal){
		if (pDigestoMunicipal == null){
			return;
		}
		if (!this.listaConcordancias.contains(pDigestoMunicipal)){
			return;
		}
		pDigestoMunicipal.getListaConcordancias().remove(this);
		this.listaConcordancias.remove(pDigestoMunicipal);
	}
	
	public Set<DigestoMunicipal> getListaConcordancias(){
		return listaConcordancias;
	}
	
	public void setListaConcordancias(Set<DigestoMunicipal> pLista){
		this.listaConcordancias = pLista;
	}
	
			
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer pNumero) {
		numero = pNumero;
	}
	
	public EjeTematico getEjeTematico() {
		return ejeTematico;
	}

	public void setEjeTematico(EjeTematico pEjeTematico) {
		ejeTematico = pEjeTematico;
	}

	public Ambito getAmbito() {
		return ambito;
	}

	public void setAmbito(Ambito pAmbito) {
		ambito = pAmbito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idDigestoMunicipal ^ (idDigestoMunicipal >>> 32));
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
		DigestoMunicipal other = (DigestoMunicipal) obj;
		if (idDigestoMunicipal != other.idDigestoMunicipal)
			return false;
		return true;
	}

	@Override
	public String toString() {
//		DateFormat locDatef = new dateformatt
		return ("[" + (this.getNumero())+"]" +" "+((this.getFecha()!=null)?this.getFecha().toString():"")+" "+((this.getEstado()!=null)?this.getEstado().toString():""));
	}
//	
	public static DigestoMunicipal copy(DigestoMunicipal pDiegestoMunicipal){
		DigestoMunicipal locDigestoMunicipal = new DigestoMunicipal();
		
		locDigestoMunicipal.setIdDigestoMunicipal(pDiegestoMunicipal.getIdDigestoMunicipal());
		locDigestoMunicipal.setTipo(pDiegestoMunicipal.getTipo());
		locDigestoMunicipal.setEstado(pDiegestoMunicipal.getEstado());
		locDigestoMunicipal.setDescripcion(pDiegestoMunicipal.getDescripcion());
		locDigestoMunicipal.setFecha(pDiegestoMunicipal.getFecha());
		locDigestoMunicipal.setNumero(pDiegestoMunicipal.getNumero());
		
		Set<DigestoMunicipal> locListaConcordancias = new HashSet<DigestoMunicipal>();
		for (DigestoMunicipal cadaDigesto : pDiegestoMunicipal.getListaConcordancias()){
			locListaConcordancias.add(cadaDigesto);
		}
		locDigestoMunicipal.setListaConcordancias(locListaConcordancias);
		return locDigestoMunicipal;
	}
	
	@PostPersist
	public void postPersist(){
		for (AtributoDinamico<?> cadaAtributo : getListaAtributosDinamicos()){
			cadaAtributo.setIdEntidad(idDigestoMunicipal);
		}
	}
	
	//*********************************************************************************************************************************************************************************/
	// AUDITORIA

	@Transient
	private String comentarioAuditoria;
	@Transient
	private long llaveUsuarioAuditoria;
	
	public List<LogAuditoria> getListaLogsAuditoria() {
		return listaLogsAuditoria;
	}

	public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
		this.listaLogsAuditoria = pListaLogsAuditoria;
	}
	
	public long getIdEntidad() {
		return this.idDigestoMunicipal;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return  "idDigestoMunicipal";
	}

	public boolean isAuditable() {
		return true;
	}

	public void setComentarioAuditoria(String pComentario) {
			this.comentarioAuditoria = pComentario;
	}
	
	public void setLlaveUsuarioAuditoria(long pLlave) {
		this.llaveUsuarioAuditoria = pLlave;
	}

	public long getLlaveUsuarioAuditoria() {
		return llaveUsuarioAuditoria;
	}

	public String getComentarioAuditoria() {
		return comentarioAuditoria;
	}
}
