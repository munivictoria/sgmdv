package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;
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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostLoad;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.anotations.NoAuditable;

@Entity
@Table(name = "TIPO_TASA")
public class TipoTasa implements Serializable, Cloneable, EntidadTrascender{

	public static final long serialVersionUID = 1453133996945803333L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_tipo_tasa")
	@SequenceGenerator(name = "gen_id_tipo_tasa", sequenceName = "gen_id_tipo_tasa", allocationSize = 1)
	@Column(name = "ID_TIPO_TASA")
	private long idTipoTasa=-1;

	private String nombre;

	@NoAuditable
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_FORMULA")
	private FormulaTipoTasa formula = new FormulaTipoTasa();

	@NoAuditable
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_FORMULA_REG_ALICUOTA")
	private FormulaTipoTasa formulaRegAlicuota = new FormulaTipoTasa();

	@Column(name = "TIPO_TASA_FIJA")
	private boolean fija;

	@Column(name = "CANTIDAD_CUOTAS")
	private Integer cantidadCuotas;

	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name = "FECHA_BAJA")
	private Date fechaBaja;

	//	@ManyToOne
	//	@JoinColumn(name = "ID_TIPO_OBLIGACION")
	//	private TipoObligacion tipoObligacion;

	private String ordenanza;

	@Column(name = "FECHA_VIGENCIA_DESDE")
	private Date fechaVigenciaDesde;

	@Column(name = "FECHA_VIGENCIA_HASTA")
	private Date fechaVigenciaHasta;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_INTERES")
	private ConceptoPorMora interes;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ID_RECARGO")
	private ConceptoPorMora recargo;

	public enum Estado{
		ACTIVA, INACTIVA, EN_ESPERA;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}

	@Enumerated(EnumType.STRING)
	private Estado estado=Estado.EN_ESPERA;

	@OneToMany(mappedBy = "tipoTasa", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<TipoModificador> listaModificadores=new HashSet<TipoModificador>();

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "RELA_TIPO_PARA_TIPO_TASA", joinColumns = @JoinColumn(name = "ID_TIPO_TASA"), inverseJoinColumns = @JoinColumn(name = "ID_TIPO_PARAMETRO"))
	private Set<TipoParametro> listaParametros = new HashSet<TipoParametro>();

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "RELA_TIPO_TASA_PARAM_ALIC", joinColumns = @JoinColumn(name = "ID_TIPO_TASA"), inverseJoinColumns = @JoinColumn(name = "ID_TIPO_PARAM_ALIC"))
	private Set<TipoParametroAlicuota> listaParamatrosAlicuota = new HashSet<TipoParametroAlicuota>(); 

	@OneToMany(mappedBy = "tipoTasa", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<TipoVencimiento> listaVencimientos=new HashSet<TipoVencimiento>();

	@OrderBy("idVariableFormula")
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinTable(name = "RELA_TIPO_TASA_VARIABLE_FORMULA", joinColumns = @JoinColumn(name = "ID_TIPO_TASA"), inverseJoinColumns = @JoinColumn(name = "ID_VARIABLE_FORMULA"))
	private List<VariableFormula> listaVariables = new ArrayList<VariableFormula>();

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_TASA_ANTERIOR")
	private TipoTasa tipoTasaAnterior;

//	@OneToOne(mappedBy = "tipoTasa", cascade = CascadeType.ALL)
//	private PermisoTipoTasa permisoTipoTasa;

	@Column(name = "ITERA_SOBRE_ALICUOTAS")
	private boolean iteraSobreAlicuotas = true;

	@ManyToOne
	@JoinColumn(name = "ID_PLAN")
	private Plan plan;
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

	@Transient
	private List<VariableFormulaSimple> listaVariablesSimple = new ArrayList<VariableFormulaSimple>();

	@Transient
	private List<VariableFormulaSimple> listaVariablesSimpleAlicuota = new ArrayList<VariableFormulaSimple>();

	@Transient
	private List<VariableFormulaCompuesta> listaVariablesFormulaCompuesta = new ArrayList<VariableFormulaCompuesta>();

	@Column(name = "CONDICION_NO_LIQUIDACION")
	private String condicionNoLiquidacion;
	
	public String getCondicionNoLiquidacion() {
		return condicionNoLiquidacion;
	}

	public void setCondicionNoLiquidacion(String condicionNoLiquidacion) {
		this.condicionNoLiquidacion = condicionNoLiquidacion;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public boolean isIteraSobreAlicuotas() {
		return iteraSobreAlicuotas;
	}

	public void setIteraSobreAlicuotas(boolean iteraSobreAlicuotas) {
		this.iteraSobreAlicuotas = iteraSobreAlicuotas;
	}

	public void addTipoParametroAlicuota(TipoParametroAlicuota pTipoParametroAlicuota){
		this.listaParamatrosAlicuota.add(pTipoParametroAlicuota);
	}

	public Set<TipoParametroAlicuota> getListaParamatrosAlicuota() {
		return listaParamatrosAlicuota;
	}

	public void setListaParamatrosAlicuota(
			Set<TipoParametroAlicuota> listaParamatrosAlicuota) {
		this.listaParamatrosAlicuota = listaParamatrosAlicuota;
	}

	public String getFormulaRegAlicuota() {
		return formulaRegAlicuota.getFormula();
	}

	public void setFormulaRegAlicuota(String formulaRegAlicuota) {
		this.formulaRegAlicuota.setFormula(formulaRegAlicuota);
	}

	public TipoTasa getTipoTasaAnterior() {
		return tipoTasaAnterior;
	}
	public void setTipoTasaAnterior(TipoTasa tipoTasaAnterior) {
		this.tipoTasaAnterior = tipoTasaAnterior;
	}

	public Set<TipoModificador> getListaModificadores() {
		return listaModificadores;
	}
	public void setListaModificadores(Set<TipoModificador> listaModificadores) {
		this.listaModificadores = listaModificadores;
	}

	public Set<TipoParametro> getListaParametros() {
		return listaParametros;
	}
	public void setListaParametros(Set<TipoParametro> listaParametros) {
		this.listaParametros = listaParametros;
	}

	public Integer getCantidadCuotas() {
		return cantidadCuotas;
	}
	public void setCantidadCuotas(Integer cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public boolean isFija() {
		return fija;
	}
	public void setFija(boolean fija) {
		this.fija = fija;
	}

	public String getFormula() {
		return formula.getFormula();
	}
	public void setFormula(String formula) {
		this.formula.setFormula(formula);
	}

	public long getIdTipoTasa() {
		return idTipoTasa;
	}
	public void setIdTipoTasa(long idTipoTasa) {
		this.idTipoTasa = idTipoTasa;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoObligacion getTipoObligacion(){
		if(this.plan == null){
			return null;
		}
		return this.plan.getTipoObligacion();
	}


	@Override
	public TipoTasa clone() throws CloneNotSupportedException {
		TipoTasa locTipoTasa=(TipoTasa)super.clone();
		locTipoTasa.setIdTipoTasa(-1);
		locTipoTasa.setEstado(TipoTasa.Estado.EN_ESPERA);
		if(this.getInteres() != null){
			locTipoTasa.setInteres(this.getInteres().clone());
		}
		if(this.getRecargo() != null){
			locTipoTasa.setRecargo(this.getRecargo().clone());
		}
		Set<TipoModificador> locListadoModificadores=new HashSet<TipoModificador>();
		Set<TipoParametro> locListadoParametros=new HashSet<TipoParametro>();
		Set<TipoParametroAlicuota> locListadoParametrosAlicuota = new HashSet<TipoParametroAlicuota>();
		Set<TipoVencimiento> locListaVencimientos=new HashSet<TipoVencimiento>();
		List<VariableFormula> locListaVariables = new ArrayList<VariableFormula>();

		for (TipoModificador locTipoModificador:this.getListaModificadores()){
			locListadoModificadores.add(locTipoModificador.clone());
		}

		for (TipoParametro locTipoParametro: this.getListaParametros()){
			locListadoParametros.add(locTipoParametro);
		}
		for (TipoParametroAlicuota cadaTipoParametroAlicuota : this.getListaParamatrosAlicuota()){
			locListadoParametrosAlicuota.add(cadaTipoParametroAlicuota);
		}

		for (TipoVencimiento locTipoVencimiento: this.getListaVencimientos()){
			locListaVencimientos.add(locTipoVencimiento.clone());
		}

		this.juntarVariables();
		for (VariableFormula cadaVariable : this.getListaVariables()){
			locListaVariables.add(cadaVariable.clone());
		}

		locTipoTasa.setListaParametros(locListadoParametros);
		locTipoTasa.setListaModificadores(locListadoModificadores);
		locTipoTasa.setListaVencimientos(locListaVencimientos);
		locTipoTasa.setListaParamatrosAlicuota(locListadoParametrosAlicuota);
		locTipoTasa.setListaVariables(locListaVariables);
		locTipoTasa.separarVariables();

		locTipoTasa.formula = new FormulaTipoTasa();
		locTipoTasa.setFormula(this.getFormula());

		locTipoTasa.formulaRegAlicuota = new FormulaTipoTasa();
		locTipoTasa.setFormulaRegAlicuota(this.getFormulaRegAlicuota());
		
		locTipoTasa.setListaLogsAuditoria(new ArrayList<LogAuditoria>());

//		locTipoTasa.permisoTipoTasa = null;

		return locTipoTasa;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

	public Date getFechaVigenciaDesde() {
		return fechaVigenciaDesde;
	}
	public void setFechaVigenciaDesde(Date fechaVigenciaDesde) {
		this.fechaVigenciaDesde = fechaVigenciaDesde;
	}

	public Date getFechaVigenciaHasta() {
		return fechaVigenciaHasta;
	}
	public void setFechaVigenciaHasta(Date fechaVigenciaHasta) {
		this.fechaVigenciaHasta = fechaVigenciaHasta;
	}

	public Set<TipoVencimiento> getListaVencimientos() {
		return listaVencimientos;
	}
	public void setListaVencimientos(Set<TipoVencimiento> listaVencimientos) {
		this.listaVencimientos = listaVencimientos;
	}

	public String getOrdenanza() {
		return ordenanza;
	}
	public void setOrdenanza(String ordenanza) {
		this.ordenanza = ordenanza;
	}

	public Estado getEstado() {
		return estado;
	}

	/**
	 * No usar este método más que para consultas
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	/**
	 * Activa este tipo de tasa y anula el anterior tipo de tasa
	 * @return true cuando pudo ser activada, false en caso contrario
	 */
	public boolean activar(){
		if (this.getEstado().equals(Estado.EN_ESPERA)){
//			if (this.getPermisoTipoTasa()!=null){
//				if (this.getPermisoTipoTasa().isHabilitado()){
					this.setEstado(Estado.ACTIVA);
					if (this.getTipoTasaAnterior()!=null) this.getTipoTasaAnterior().desactivar();
					return true;
//				}
//			}
		}
		return false;
	}

	public boolean desactivar(){
		if (this.getEstado().equals(Estado.ACTIVA)){
			this.setEstado(Estado.INACTIVA);
			return true;
		}
		return false;
	}

	/**
	 * Método utilizado para desactivar una tasa en espera
	 */
	public void desactivarTasaEnEspera(){
		this.setEstado(Estado.INACTIVA);
	}

//	public PermisoTipoTasa getPermisoTipoTasa() {
//		return permisoTipoTasa;
//	}
//	public void setPermisoTipoTasa(PermisoTipoTasa permisoTipoTasa) {
//		this.permisoTipoTasa = permisoTipoTasa;
//	}

	//	public Periodicidad getPeriodicidadCuotas() {
	//		return periodicidadCuotas;
	//	}
	//	public void setPeriodicidadCuotas(Periodicidad periodicidadCuotas) {
	//		this.periodicidadCuotas = periodicidadCuotas;
	//	}

	public String getNombreTipoObligacion(){
		return this.getPlan() != null ? this.getPlan().getTipoObligacion().getNombre() : "";
	}

	public List<VariableFormula> getListaVariables() {
		return listaVariables;
	}

	public void setListaVariables(List<VariableFormula> listaVariables) {
		this.listaVariables = listaVariables;
	}

	public List<VariableFormulaSimple> getListaVariablesSimple() {
		return listaVariablesSimple;
	}

	public void setListaVariablesSimple(
			List<VariableFormulaSimple> listaVariablesSimple) {
		this.listaVariablesSimple = listaVariablesSimple;
	}

	public List<VariableFormulaSimple> getListaVariablesSimpleAlicuota() {
		return listaVariablesSimpleAlicuota;
	}

	public void setListaVariablesSimpleAlicuota(
			List<VariableFormulaSimple> listaVariablesSimpleAlicuota) {
		this.listaVariablesSimpleAlicuota = listaVariablesSimpleAlicuota;
	}

	public List<VariableFormulaCompuesta> getListaVariablesFormulaCompuesta() {
		return listaVariablesFormulaCompuesta;
	}

	public void setListaVariablesFormulaCompuesta(
			List<VariableFormulaCompuesta> listaVariablesFormulaCompuesta) {
		this.listaVariablesFormulaCompuesta = listaVariablesFormulaCompuesta;
	}

	/**
	 * El interes es un valor aplicado sobre la tasa luego del vencimiento
	 */
	public ConceptoPorMora getInteres() {
		return interes;
	}
	public void setInteres(ConceptoPorMora interes) {
		this.interes = interes;
	}

	/**
	 * El recargo es un porcentaje sobre el interes de la tasa aplicado luego del vencimiento 
	 */
	public ConceptoPorMora getRecargo() {
		return recargo;
	}
	public void setRecargo(ConceptoPorMora recargo) {
		this.recargo = recargo;
	}

	@PostLoad
	public void onLoad(){
		if (formulaRegAlicuota == null){
			formulaRegAlicuota = new FormulaTipoTasa();
		}
		this.separarVariables();
	}

	public void juntarVariables(){
		this.listaVariables.clear();
		this.listaVariables.addAll(listaVariablesFormulaCompuesta);
		this.listaVariables.addAll(listaVariablesSimple);
		for (VariableFormulaSimple cadaVariable : this.listaVariablesSimpleAlicuota){
			cadaVariable.setVariableDeAlicuota(true);
		}
		this.listaVariables.addAll(listaVariablesSimpleAlicuota);
	}
	
	public boolean tieneParametrosDeduda() {
		for (TipoParametro cadaParametro : listaParametros) {
			if (cadaParametro instanceof TipoParametroDeuda)
				return true;
		}
		return false;
	}

	private void separarVariables(){
		this.listaVariablesSimple.clear();
		this.listaVariablesSimpleAlicuota.clear();
		this.listaVariablesFormulaCompuesta.clear();
		for (VariableFormula cadaVariableFormula : listaVariables){
			if (cadaVariableFormula instanceof VariableFormulaCompuesta){
				this.listaVariablesFormulaCompuesta.add((VariableFormulaCompuesta)cadaVariableFormula);
			} else {
				VariableFormulaSimple variableFormulaSimple = (VariableFormulaSimple) cadaVariableFormula;
				if (variableFormulaSimple.isVariableDeAlicuota()){
					this.listaVariablesSimpleAlicuota.add(variableFormulaSimple);
				} else {
					this.listaVariablesSimple.add(variableFormulaSimple);
				}
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idTipoTasa ^ (idTipoTasa >>> 32));
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
		TipoTasa other = (TipoTasa) obj;
		if (idTipoTasa != other.idTipoTasa)
			return false;
		return true;
	}

	//*********************************************************************************************************************************************************************************/
	// AUDITORIA

	@Transient
	private long llaveUsuarioAuditoria;
	@Transient
	private String comentarioAuditoria;

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

	public long getIdEntidad() {
		return this.idTipoTasa;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idTipoTasa";
	}

	public boolean isAuditable() {
		return true;
	}
}
