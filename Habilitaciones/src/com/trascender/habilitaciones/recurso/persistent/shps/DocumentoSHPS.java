package com.trascender.habilitaciones.recurso.persistent.shps;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.Util;
import com.trascender.framework.util.anotations.NoAuditable;
import com.trascender.habilitaciones.recurso.persistent.AsocRegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.DeclaracionJuradaSHPS;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.LineaDeclaracionJuradaSHPS;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.RegistroValuado;

@Entity
@DiscriminatorValue(value = "SHPS")
public class DocumentoSHPS extends DocHabilitanteEspecializado {

	public static final long serialVersionUID = -6355935211164354875L;

	@ManyToMany
	@JoinTable(name = "RELA_LISTA_LOC_COMER", joinColumns = @JoinColumn(name = "ID_DOC_HAB_ESPECIALIZADO"), inverseJoinColumns = @JoinColumn(name = "ID_LOCAL_COMERCIAL"))
	private Set<LocalComercial> listaLocalesComerciales=new HashSet<LocalComercial>();

	@ManyToMany
	@JoinTable(name = "RELA_LISTA_TRANS_VEHIC", joinColumns=@JoinColumn(name = "ID_DOC_HAB_ESPECIALIZADO"), inverseJoinColumns = @JoinColumn(name = "ID_TRANSPORTE_VEHICULAR"))
	private Set<TransporteVehicular> listaTransportesVehiculares=new HashSet<TransporteVehicular>();

	@NoAuditable
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "documentoSHPS")
	private Set<ClausuraSHPS> listaClausuras=new HashSet<ClausuraSHPS>();

	@Transient
	@OneToMany(mappedBy = "documentoSHPS", cascade = CascadeType.ALL)
	private Set<LogModificaciones> listaModificaciones=new HashSet<LogModificaciones>();

	@OneToMany
	@JoinTable(name="RELA_DOC_SHPS_LIB_SAN",joinColumns=@JoinColumn(name="ID_DOC_HAB_ESPECIALIZADO"), inverseJoinColumns=@JoinColumn(name="ID_LIBRETA_SANITARIA") )
	private Set<LibretaSanitaria> listaLibretaSanitarias = new HashSet<LibretaSanitaria>();

	@Column(name = "DENOMINACION_ENTIDAD")
	private String denominacionEntidad;

//	@OneToOne(mappedBy = "documentoEspecializado", cascade = CascadeType.ALL)
	@Transient
	private TipoActividad tipoActividad;

	@Column(name = "NUMERO_INSCRIPCION", nullable = false)
	private String numeroInscripcion;

	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private final List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CONTADOR")
	private Persona contador;

	public Persona getContador() {
		return contador;
	}

	public void setContador(Persona contador) {
		this.contador = contador;
	}

	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

	@Override
	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.getIdDocHabilitanteEspecializado());
		this.listaAtributosDinamicos.add(pAtributoDinamico);
	}

	@Override
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}
	
	public LineaDeclaracionJuradaSHPS getLineaDDJJSHPS(CuotaLiquidacion pCuota, RegAlicuota pRegAlicuota){
		for (RegistroValuado cadaRegistroValuado : this.getListaRegistrosValuados()){
			if (!cadaRegistroValuado.getCuotaLiquidacion().equals(pCuota)) continue;
			DeclaracionJuradaSHPS cadaDeclaracion = (DeclaracionJuradaSHPS) cadaRegistroValuado;
			for (LineaDeclaracionJuradaSHPS cadaLinea : cadaDeclaracion.getListaLineasDDJJSHPS()) {
				if (cadaLinea.getRubro().equals(pRegAlicuota)){
					return cadaLinea;
				}
			}
		}
		return null;
	}

	@Override
	public void setListaAtributosDinamicos(List<AtributoDinamico<?>> pListaAtributosDinamicos) {
		this.listaAtributosDinamicos.clear();
		for (AtributoDinamico<?> cadaAtributo : pListaAtributosDinamicos){
			if (cadaAtributo.getValor() != null){
				this.addAtributoDinamico(cadaAtributo);
			}
		}
	}

	public void addLibretaSanitaria(LibretaSanitaria pLibretaSanitaria){
		if(!this.listaLibretaSanitarias.contains(pLibretaSanitaria)){
			this.listaLibretaSanitarias.add(pLibretaSanitaria);
		}
	}

	public void setRubroPrincipal(AsocRubro pAsocRubro){

		for(AsocRubro cadaAsocRubro : this.getListaRubros()){
			if(cadaAsocRubro.getRegistroAlicuota().getIdTipoAlicuota() == pAsocRubro.getRegistroAlicuota().getIdTipoAlicuota()){
				cadaAsocRubro.setPrincipal(true);
			} else if(cadaAsocRubro.isPrincipal()){
				cadaAsocRubro.setPrincipal(false);
			}
		}
	}

	public AsocRubro getRubroPrincipal(){

		for (AsocRubro cadaAsoc : this.getListaRubros()) {
			if (cadaAsoc.isPrincipal()) {
				return cadaAsoc;
			}
		}

		return null;
	}

	public Set<LibretaSanitaria> getListaLibretaSanitarias() {
		return listaLibretaSanitarias;
	}

	public void setListaLibretaSanitarias(
			Set<LibretaSanitaria> listaLibretaSanitarias) {
		this.listaLibretaSanitarias = listaLibretaSanitarias;
	}

	public TipoActividad getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(TipoActividad tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public String getDenominacionEntidad() {
		return denominacionEntidad;
	}

	public void setDenominacionEntidad(String denominacionEntidad) {
		this.denominacionEntidad = denominacionEntidad;
	}

	public Set<ClausuraSHPS> getListaClausuras() {
		return listaClausuras;
	}
	public void setListaClausuras(Set<ClausuraSHPS> listaClausuras) {
		this.listaClausuras = listaClausuras;
	}

	public Set<LocalComercial> getListaLocalesComerciales() {
		return listaLocalesComerciales;
	}
	public void setListaLocalesComerciales(Set<LocalComercial> listaLocalesComerciales) {
		this.listaLocalesComerciales = listaLocalesComerciales;
	}

	public Set<TransporteVehicular> getListaTransportesVehiculares() {
		return listaTransportesVehiculares;
	}
	public void setListaTransportesVehiculares(
			Set<TransporteVehicular> listaTransportesVehiculares) {
		this.listaTransportesVehiculares = listaTransportesVehiculares;
	}


	public long getIdDocumentoSHPS() {
		return super.getIdDocHabilitanteEspecializado();
	}
	public void setIdDocumentoSHPS(long idDocumentoSHPS) {
		super.setIdDocHabilitanteEspecializado(idDocumentoSHPS);
	}

	public Set<LogModificaciones> getListaModificaciones() {
		return listaModificaciones;
	}
	public void setListaModificaciones(Set<LogModificaciones> listaModificaciones) {
		this.listaModificaciones = listaModificaciones;
	}

	public String getNumeroInscripcion() {
		return numeroInscripcion;
	}
	public void setNumeroInscripcion(String numeroInscripcion) {
		this.numeroInscripcion = numeroInscripcion;
	}

	private List<AsocRubro> getListaRubros(){
		List<AsocRubro> locListaAsocRubro = new ArrayList<AsocRubro>();
		for(AsocRegAlicuota cadaAsoc : this.getListaAsocRegAlicuota()){
			locListaAsocRubro.add((AsocRubro) cadaAsoc);
		}
		return locListaAsocRubro;
	}

	@Override
	public List<LogAuditoria> getListaLogsAuditoria() {
		return listaLogsAuditoria;
	}

	@Override
	public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
		this.listaLogsAuditoria = pListaLogsAuditoria;
	}

	@Override
	public String toString() {
		String retorno = Util.returnToString(this, serialVersionUID);
		if(retorno != null) {
			return retorno;
		}
		
		StringBuilder locStringBuilder=new StringBuilder();

		locStringBuilder.append("[SHPS]");

		if (this.getRegistroAlicuota()!=null){
			locStringBuilder.append(" Rubro: " + this.getRegistroAlicuota().toString());
		}
		if (this.getDenominacionEntidad()!=null){
			locStringBuilder.append(" | Denominación: "+this.getDenominacionEntidad());
		}
		return locStringBuilder.toString();
	}

	@Override
	@Transient
	public RegAlicuota getRegistroAlicuota() {
		return (!this.getListaRegAlicuotas().isEmpty())?this.getListaRegAlicuotas().iterator().next():null;
	}

	public String getComentario() {
		return null;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}
}
