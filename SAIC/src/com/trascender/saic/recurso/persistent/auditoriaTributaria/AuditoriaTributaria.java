package com.trascender.saic.recurso.persistent.auditoriaTributaria;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.saic.exception.SaicException;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.refinanciacion.CuotaRefinanciacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;
/**
 * @author jsantacruz
 */

@Entity
@Table(name="AUDITORIA_TRIBUTARIA")
public class AuditoriaTributaria implements Serializable{
	
	public static final long serialVersionUID = -9020916327400340807L;

	/**
	 * @author jsantacruz
	 * Estados posibles{NORMAL,INTIMADO,	EN_JUICIO,PROVISORIO,REFINANCIADA_TERMINADA,REFINANCIADA_CADUCADA,REFINANCIADA_PENDIENTE,TERMINADA}
	 */
	public enum EstadoAuditoriaTributaria {
		NORMAL,
		INTIMADO,
		EN_JUICIO,
		PROVISORIO,
		REFINANCIADA_TERMINADA,
		REFINANCIADA_CADUCADA, 
		REFINANCIADA_PENDIENTE,
		TERMINADA;
		
		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}
	
	@Id
	@SequenceGenerator(allocationSize=1, name="gen_id_auditoria_tributaria",sequenceName="gen_id_auditoria_tributaria")
	@GeneratedValue(generator="gen_id_auditoria_tributaria", strategy=GenerationType.SEQUENCE)
	@Column(name="ID_AUDITORIA_TRIBUTARIA")
	private long idAuditoriaTributaria = -1;
	
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;
	
	@ManyToOne(cascade=CascadeType.ALL, optional=true)
	@JoinColumn(name="ID_FIRMA_PERMISO_HAB")
	private FirmaPermiso firma;
	
	@ManyToOne
	@JoinColumn(name="ID_PERSONA")
	private Persona contribuyente;
	
	@Enumerated(EnumType.STRING)
	private EstadoAuditoriaTributaria estado = EstadoAuditoriaTributaria.NORMAL;
	
	@ManyToOne
	@JoinColumn(name="ID_TIPO_OBLIGACION")
	private TipoObligacion tipoObligacion;
	
	@ManyToOne
	@JoinColumn(name="ID_DOC_GENERADOR_DEUDA")
	private DocumentoRefinanciacion documentoRefinanciacion;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="auditoriaTributaria",fetch=FetchType.LAZY)
	private Set<LogCambiosAuditoriaTributaria> listaLogCambios = new HashSet<LogCambiosAuditoriaTributaria>();
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name="RELA_AUDIT_TRIB_REG_DEUDA", joinColumns={@JoinColumn(name="ID_AUDITORIA_TRIBUTARIA")}, inverseJoinColumns=@JoinColumn(name="ID_REGISTRO_DEUDA"))
	private Set<RegistroDeuda> listaRegistroDeuda = new HashSet<RegistroDeuda>();
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="RELA_AUDIT_TRIB_INTIMACION", joinColumns={@JoinColumn(name="ID_AUDITORIA_TRIBUTARIA")}, inverseJoinColumns=@JoinColumn(name="ID_INTIMACION"))
	private Set<Intimacion> listaIntimaciones = new HashSet<Intimacion>();
	
	/**
	 * Agrega un registro de deuda. haciendo las validaciones pertinentes
	 * @param pRegistroDeuda
	 */
	public void addRegistroDeuda(RegistroDeuda pRegistroDeuda){
		if(pRegistroDeuda != null && !this.listaRegistroDeuda.contains(pRegistroDeuda)){
			if(pRegistroDeuda.getDocGeneradorDeuda()
								.getObligacion()
								.getDocumentoEspecializado()
								.getTipoTasa()
								.getTipoObligacion().equals(this.tipoObligacion) 
					&& (pRegistroDeuda.getDocGeneradorDeuda().getObligacion().getPersona().getIdPersona() 
							== this.getContribuyente().getIdPersona()) ){
				this.listaRegistroDeuda.add(pRegistroDeuda);
			}
		}
	}
	
	public void addIntimacion(Intimacion pIntimacion){
		if(pIntimacion != null && this.isIntimacionAgregable()){
			this.listaIntimaciones.add(pIntimacion);
		}
	}
	
	/**
	 * Valida que solo se puedan agregar intimaciones cuando todas las anteriores esten prescriptas.
	 * @return
	 */
	private boolean isIntimacionAgregable(){
		for(Intimacion cadaIntimacion : this.getListaIntimaciones()){
			if(!cadaIntimacion.getEstado().equals(Intimacion.EstadoIntimacion.PRESCRIPTA)){
				return false;
			}
		}
		return true;
	}
	
	public DocumentoRefinanciacion getDocumentoRefinanciacion() {
		return documentoRefinanciacion;
	}

	public void setDocumentoRefinanciacion(DocumentoRefinanciacion documentoRefinanciacion) {
		try {
//			this.validarDocumentoRefinanciacion(documentoRefinanciacion);
			this.documentoRefinanciacion = documentoRefinanciacion;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		try{
//		if(this.getEstado().equals(EstadoAuditoriaTributaria.PROVISORIO)){
//			this.validarDocumentoRefinanciacion(documentoRefinanciacion);
//			this.documentoRefinanciacion = documentoRefinanciacion;
//		}else {
//			throw new SaicException(408);
//		}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * 
	 * @param documentoRefinanciacion2
	 */
	private void validarDocumentoRefinanciacion(DocumentoRefinanciacion pDocumentoRefinanciacion) throws Exception {
		int locContadorCoincidencias = 0;
		if(pDocumentoRefinanciacion.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda().size() == this.getListaRegistroDeuda().size()){
			for(RegistroDeuda cadaRegistroDeudaDocRef : pDocumentoRefinanciacion.getRegCancelacionPorRefinanciacion().getListaRegistrosDeuda()){
				for(RegistroDeuda cadaRegistroDeudaAudit : this.getListaRegistroDeuda()){
					if(cadaRegistroDeudaAudit.hashCode() == cadaRegistroDeudaDocRef.hashCode()){
						locContadorCoincidencias++;
						break;
					}
				}
			}
		}else {
			throw new SaicException(409);
		}
		
		if(!(locContadorCoincidencias == this.getListaRegistroDeuda().size())){
			throw new SaicException(410);
		}
		
	}

	public Set<Intimacion> getListaIntimaciones() {
		return listaIntimaciones;
	}

	public void setListaIntimaciones(Set<Intimacion> listaIntimaciones) {
		this.listaIntimaciones = listaIntimaciones;
	}

	public long getIdAuditoriaTributaria() {
		return idAuditoriaTributaria;
	}

	public void setIdAuditoriaTributaria(long idAuditoriaTributaria) {
		this.idAuditoriaTributaria = idAuditoriaTributaria;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public FirmaPermiso getFirma() {
		return firma;
	}

	public void setFirma(FirmaPermiso firma) {
		this.firma = firma;
	}

	public Set<LogCambiosAuditoriaTributaria> getListaLogCambios() {
		return listaLogCambios;
	}

	public void setListaLogCambios(Set<LogCambiosAuditoriaTributaria> listaLogCambios) {
		this.listaLogCambios = listaLogCambios;
	}

	public Set<RegistroDeuda> getListaRegistroDeuda() {
		return listaRegistroDeuda;
	}

	public void setListaRegistroDeuda(Set<RegistroDeuda> listaRegistroDeuda) {
		this.listaRegistroDeuda = listaRegistroDeuda;
	}

	public Persona getContribuyente() {
		return contribuyente;
	}

	public void setContribuyente(Persona contribuyente) {
		this.contribuyente = contribuyente;
	}

	public EstadoAuditoriaTributaria getEstado() {
		if(this.getDocumentoRefinanciacion() != null){
			try{
				switch(this.getDocumentoRefinanciacion().getEstadoRefinanciacion()){
					case CADUCADA: return EstadoAuditoriaTributaria.REFINANCIADA_CADUCADA;
					case TERMINADA: return EstadoAuditoriaTributaria.REFINANCIADA_TERMINADA;
					case PENDIENTE: return EstadoAuditoriaTributaria.REFINANCIADA_PENDIENTE;
					default: return null;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return estado;
	}

	public TipoObligacion getTipoObligacion() {
		return tipoObligacion;
	}

	/**
	 * Si el tipo obligacion que se setea es distinto del que ya tiene
	 * vacía la listaRegistroDeudas
	 * @param tipoObligacion
	 */
	public void setTipoObligacion(TipoObligacion tipoObligacion) {
		if(this.tipoObligacion != null && !this.tipoObligacion.equals(tipoObligacion)){
			this.listaRegistroDeuda.clear();
		}
		this.tipoObligacion = tipoObligacion;
	}

	public void setEstado(EstadoAuditoriaTributaria estado) {
		this.estado = estado;
	}
	
	public Boolean isFirmado(){
		return (this.getFirma() != null?true:false);
	}
	
	public Double getMonto(){
		if(this.getDocumentoRefinanciacion() != null){
			return this.getDocumentoRefinanciacion().getTotalAPagar();
		}
		
		Double locMonto = 0D;
		for(RegistroDeuda cadaRegistro : this.getListaRegistroDeuda()){
			if(cadaRegistro instanceof LiquidacionTasa){
				locMonto += ((LiquidacionTasa)cadaRegistro).getValor();
			}else if (cadaRegistro instanceof CuotaRefinanciacion){
				locMonto += ((CuotaRefinanciacion)cadaRegistro).getValor();
			}
		}
		
		return locMonto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idAuditoriaTributaria ^ (idAuditoriaTributaria >>> 32));
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
		AuditoriaTributaria other = (AuditoriaTributaria) obj;
		if (idAuditoriaTributaria != other.idAuditoriaTributaria)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		
		toString.append("Auditoria Tributaria ["+this.getTipoObligacion() +"] - ");
		toString.append( (this.getContribuyente() instanceof PersonaFisica)?((PersonaFisica) this.getContribuyente()).toString():((PersonaJuridica)this.getContribuyente()).toString());
		toString.append(" "+ Util.getString(this.getFechaCreacion())+ " - " + this.getEstado());
		
		return toString.toString();
	}
}
