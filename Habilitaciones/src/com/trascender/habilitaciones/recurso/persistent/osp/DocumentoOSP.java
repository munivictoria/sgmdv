package com.trascender.habilitaciones.recurso.persistent.osp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostPersist;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;


@Entity
@DiscriminatorValue(value = "OSP")
@Cacheable
public class DocumentoOSP extends DocHabilitanteEspecializado{

	public static final String DISCRIMINATOR_VALUE="OSP";

	public static final long serialVersionUID = -6124768499839881843L;

	//	@OneToOne(mappedBy = "documentoEspecializado")
	//	private TipoActividad tipoActividad;

	@Column(name = "NUMERO_CUENTA")
	private Integer numeroCuenta;

	@Column(name = "NUMERO_SUB_CUENTA")
	private Integer numeroSubCuenta;

	@Column(name = "COEFICIENTE_ZONAL")
	private Double coeficienteZonal;

	@Transient
	private ConsumoBasico consumoBasico;

	@Transient
	private Double baseConsumo;

	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private final List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();

	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

	@Override
	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(getIdDocHabilitanteEspecializado());
		this.listaAtributosDinamicos.add(pAtributoDinamico);
	}

	@Override
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
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

	/**
	 * 
	 * @hibernate.property column = "COEFICIENTE_ZONAL"
	 */
	public Double getCoeficienteZonal() {
		return coeficienteZonal;
	}
	public void setCoeficienteZonal(Double coeficienteZonal) {
		this.coeficienteZonal = coeficienteZonal;
	}

	/**
	 * 
	 * @hibernate.property not-null = "true" column = "NUMERO_CUENTA"
	 */
	public Integer getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(Integer numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * 
	 * @hibernate.property not-null = "true" column = "NUMERO_SUB_CUENTA"
	 */
	public Integer getNumeroSubCuenta() {
		return numeroSubCuenta;
	}
	public void setNumeroSubCuenta(Integer numeroSubCuenta) {
		this.numeroSubCuenta = numeroSubCuenta;
	}

	@Override
	public String toString() {
		String retorno = Util.returnToString(this, serialVersionUID);
		if(retorno != null) {
			return retorno;
		}
		
		StringBuilder locToString=new StringBuilder();

		locToString.append("[OSP]");

		if (this.getNumeroCuenta()!=null){
			locToString.append(" Cuenta/Subcuenta: " +this.getNumeroCuenta());
			if (this.getNumeroSubCuenta()!=null){
				locToString.append("/"+this.getNumeroSubCuenta());
			}
		}
		if (this.getParcela()!=null){
			locToString.append(" | Parcela: "+this.getParcela().toString());
		}
		//		if (this.getRegistroAlicuota()!=null){
		//			locToString.append(" | Servicio: "+this.getRegistroAlicuota().toString());
		//		}
		return locToString.toString();
	}

	public ConsumoBasico getConsumoBasico(){
		return consumoBasico;
	}
	public void setConsumoBasico(ConsumoBasico consumoBasico) {
		this.consumoBasico = consumoBasico;
	}

	/**
	 * 
	 * @throws TrascenderException
	 */
	public void calcularBaseConsumo() throws TrascenderException{
		if (this.consumoBasico == null){
			throw new HabilitacionesException(150);
		}
		Double resultado = this.consumoBasico.getConsumoInicial()+ ( (this.getParcela().getSuperficieMejoras().doubleValue()-this.consumoBasico.getSuperficieMejorasMinimo()) * this.consumoBasico.getConsumoPorExcedente());
		this.setBaseConsumo(resultado);
	}

	public Double getBaseConsumo() throws TrascenderException{
		if (this.baseConsumo==null){
			this.calcularBaseConsumo();
		}
		return baseConsumo;
	}
	public void setBaseConsumo(Double resultado) {
		this.baseConsumo = resultado;
	}

	@Override
	public List<LogAuditoria> getListaLogsAuditoria() {
		return listaLogsAuditoria;
	}

	@Override
	public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
		this.listaLogsAuditoria = pListaLogsAuditoria;
	}

	public String getCodigoMedidor() {
		if (this.getListaAsocRegAlicuota().isEmpty()) return null;
		AsocServicioOsp locAsocOSP = (AsocServicioOsp) getListaAsocRegAlicuota().get(0);
		return locAsocOSP.getCodigoMedidor();
	}

	@Override
	public boolean equals(Object obj) {

		if(this.getIdDocHabilitanteEspecializado() == -1){
			return ((System.identityHashCode(obj) == System.identityHashCode(this)) && super.equals(obj));
		}
		return super.equals(obj);
	}

	@Override
	@PostPersist
	public void postPersist(){
		for (AtributoDinamico<?> cadaAtributo : getListaAtributosDinamicos()){
			cadaAtributo.setIdEntidad(getIdDocHabilitanteEspecializado());
		}
	}

	//*********************************************************************************************************************************************************************************/
	// AUDITORIA

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

}
