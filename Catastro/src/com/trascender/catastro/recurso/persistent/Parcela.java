package com.trascender.catastro.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostPersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.catastro.enums.AfectacionesCatastrales;
import com.trascender.catastro.enums.ClasificacionCatSegunSuUso;
import com.trascender.catastro.exception.CatastroException;
import com.trascender.catastro.recurso.persistent.TipoPlanta.Tipo;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.Util;
import com.trascender.framework.util.anotations.NoAuditable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISC")
@DiscriminatorValue(value = "P")
@Table(name = "PARCELA")
@Cacheable
public class Parcela implements Serializable, Cloneable, EntidadTrascender {

	public static final long serialVersionUID = -1724036473956407989L;

	/**
	 * Tipos:DOMINAL, POSESORIA, APARENTE, ARRENDAMIENTO;
	 */
	public enum TipoParcela {
		DOMINAL, POSESORIA, APARENTE, ARRENDAMIENTO;

		@Override
		public String toString() {
			return Util.capitalizeEnumName(this.name());
		}
	}
	
	public enum Estado{ACTIVO, ELIMINADO;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	};

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_parcela")
	@SequenceGenerator(name = "gen_id_parcela", sequenceName = "gen_id_parcela", allocationSize = 1)
	@Column(name = "ID_PARCELA")
	private long idParcela = -1;

	@Column(name = "NRO_PARTIDA_PROVINCIAL")
	private String nroPartidaProvincial;

	@Column(name = "NRO_REGISTRO")
	private String nroRegistro;

	@Column(name = "NRO_CUENTA")
	private Integer nroCuenta;

	@Column(name = "NRO_MATRICULA")
	private String nroMatricula;

	private Double superficie;

	@Column(name = "FECHA_ALTA")
	private Date fechaAlta;
	
	@Enumerated(EnumType.STRING)
	private Estado estado=Estado.ACTIVO;

	@OneToMany(mappedBy = "parcela", cascade = {CascadeType.ALL, CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval=true)
	private List<ParcelaPorCuadra> listaParcelasPorCuadra = new ArrayList<ParcelaPorCuadra>();

	@OneToMany(mappedBy = "parcela", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<RegistroMejora> listaRegistrosMejora = new HashSet<RegistroMejora>();

	@Column(name = "METROS_FRENTE")
	private Double metrosFrente;

	@NoAuditable
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "ID_TITULO_PROPIEDAD")
	private TituloPropiedadParcelario tituloPropiedad;

	@NoAuditable
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "ID_PLANO_MENSURA")
	private PlanoMensura planoMensura;

	@NoAuditable
	@Transient
//	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
//	@JoinColumn(name = "ID_PLANO_CONSTRUCCION")
	private PlanoConstruccion planoConstruccion;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name = "RELA_PARCELA_PLANO_CONSTRUCCION", joinColumns = @JoinColumn(name = "ID_PARCELA"), inverseJoinColumns = @JoinColumn(name = "ID_PLANO_CONSTRUCCION"))
	private List<PlanoConstruccion> listaPlanosConstruccion = new ArrayList<PlanoConstruccion>();

	@ManyToOne(cascade = { CascadeType.ALL, CascadeType.MERGE,	CascadeType.REMOVE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOMICILIO")
	private Domicilio domicilioParcelario;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ID_MANZANA")
	private Manzana manzana;

	@Column(name = "AVALUO_MEJORAS")
	private Double avaluoPorMejoras = 0d;

	@Column(name = "AVALUO_TERRENO")
	private Double avaluoTerreno = 0d;

	// Agregado para la parcela pueda asociarse a una zona
	//	@Transient
	//	private AsociacionParcela asociacionParcela;

	@OneToMany(mappedBy = "parcela", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<AsociacionParcela> listaAsociacionParcela = new ArrayList<AsociacionParcela>();

	//@OneToMany(mappedBy = "padre", cascade = {CascadeType.ALL, CascadeType.REMOVE}, orphanRemoval=true)
	@Transient
	private Set<SubParcela> listaSubParcelas = new HashSet<SubParcela>();

	@Embedded
	private Planta planta = new Planta(Tipo.URBANA);

	@Embedded
	private NomenclaturaCatastral nomenclaturaCatastral = new NomenclaturaCatastral();

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_RESIDENCIAL")
	private ClasificacionCatSegunSuUso.Residencial tipoResidencial;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_COMERCIAL")
	private ClasificacionCatSegunSuUso.Comercial tipoComercial;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_EQUIPAMIENTO")
	private ClasificacionCatSegunSuUso.Equipamiento tipoEquipamiento;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_VARIOS")
	private ClasificacionCatSegunSuUso.Varios tipoVarios;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_RURAL")
	private ClasificacionCatSegunSuUso.Rural tipoRural;

	@Enumerated(EnumType.STRING)
	@Column(name = "AFECTACIONES_EXPLICITAS")
	private AfectacionesCatastrales.Explicitas afectacionesExplicitas;

	@Enumerated(EnumType.STRING)
	@Column(name = "AFECTACIONES_NO_EXPLICITAS")
	private AfectacionesCatastrales.NoExplicitas afectacionesNoExplicitas;

	@Enumerated(EnumType.STRING)
	@Column(name = "RESTRICCIONES_DOM_EXP")
	private AfectacionesCatastrales.RestriccionesDominioExplicitas restriccionesDominioExplicitas;

	@Enumerated(EnumType.STRING)
	@Column(name = "RESTRICCIONES_DOM_NO_EXP")
	private AfectacionesCatastrales.RestriccionesDominioNoExplicitas restriccionesDominicioNoExplicitas;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_PARCELA")
	private TipoParcela tipoParcela = TipoParcela.DOMINAL;

	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private final List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();

	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	//Temporal para Victoria

	@Column(name = "periodicidad_auxiliar")
	private String periodicidadAuxiliar;


	public Parcela() {
		if (this.nomenclaturaCatastral == null){
			this.nomenclaturaCatastral = new NomenclaturaCatastral();
		}
	}
	
	public List<PlanoConstruccion> getListaPlanosConstruccion() {
		return listaPlanosConstruccion;
	}

	public void setListaPlanosConstruccion(
			List<PlanoConstruccion> listaPlanosConstruccion) {
		this.listaPlanosConstruccion = listaPlanosConstruccion;
	}

	public String getPeriodicidadAuxiliar() {
		return periodicidadAuxiliar;
	}

	public void setPeriodicidadAuxiliar(String periodicidadAuxiliar) {
		this.periodicidadAuxiliar = periodicidadAuxiliar;
	}

	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.getIdParcela());
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

	/**
	 * Al recuperar la nomenclatura de la base puede venir NULL 
	 * si dicha parcela tuviera todos los datos de la nomenclatura vacios. 
	 * @return
	 */

	public NomenclaturaCatastral getNomenclaturaCatastral() {
		return nomenclaturaCatastral;
	}

	public void setNomenclaturaCatastral(NomenclaturaCatastral nomenclaturaCatastral) {
		this.nomenclaturaCatastral = nomenclaturaCatastral;
	}

	/**
	 * @return el aval�o por mejoras de esta parcela, para actualizar este valor
	 *         debe recurrirse al m�todo calcularAvaluo
	 */
	public Double getAvaluoPorMejoras() {
		return avaluoPorMejoras;
	}

	public void setAvaluoPorMejoras(Double avaluoPorMejoras) {
		this.avaluoPorMejoras = avaluoPorMejoras;
	}

	public Integer getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(Integer nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public Set<SubParcela> getListaSubParcelas() {
		return listaSubParcelas;
	}

	public void setListaSubParcelas(Set<SubParcela> listaSubParcelas) {
		this.listaSubParcelas = listaSubParcelas;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public ClasificacionCatSegunSuUso.Residencial getTipoResidencial() {
		return tipoResidencial;
	}

	public void setTipoResidencial(
			ClasificacionCatSegunSuUso.Residencial tipoResidencial) {
		this.tipoResidencial = tipoResidencial;
	}

	public ClasificacionCatSegunSuUso.Comercial getTipoComercial() {
		return tipoComercial;
	}

	public void setTipoComercial(
			ClasificacionCatSegunSuUso.Comercial tipoComercial) {
		this.tipoComercial = tipoComercial;
	}

	public ClasificacionCatSegunSuUso.Equipamiento getTipoEquipamiento() {
		return tipoEquipamiento;
	}

	public void setTipoEquipamiento(
			ClasificacionCatSegunSuUso.Equipamiento tipoEquipamiento) {
		this.tipoEquipamiento = tipoEquipamiento;
	}

	public ClasificacionCatSegunSuUso.Varios getTipoVarios() {
		return tipoVarios;
	}

	public void setTipoVarios(ClasificacionCatSegunSuUso.Varios tipoVarios) {
		this.tipoVarios = tipoVarios;
	}

	public ClasificacionCatSegunSuUso.Rural getTipoRural() {
		return tipoRural;
	}

	public void setTipoRural(ClasificacionCatSegunSuUso.Rural tipoRural) {
		this.tipoRural = tipoRural;
	}

	public AfectacionesCatastrales.Explicitas getAfectacionesExplicitas() {
		return afectacionesExplicitas;
	}

	public void setAfectacionesExplicitas(
			AfectacionesCatastrales.Explicitas afectacionesExplicitas) {
		this.afectacionesExplicitas = afectacionesExplicitas;
	}

	public AfectacionesCatastrales.NoExplicitas getAfectacionesNoExplicitas() {
		return afectacionesNoExplicitas;
	}

	public void setAfectacionesNoExplicitas(
			AfectacionesCatastrales.NoExplicitas afectacionesNoExplicitas) {
		this.afectacionesNoExplicitas = afectacionesNoExplicitas;
	}

	public AfectacionesCatastrales.RestriccionesDominioExplicitas getRestriccionesDominioExplicitas() {
		return restriccionesDominioExplicitas;
	}

	public void setRestriccionesDominioExplicitas(
			AfectacionesCatastrales.RestriccionesDominioExplicitas restriccionesDominioExplicitas) {
		this.restriccionesDominioExplicitas = restriccionesDominioExplicitas;
	}

	public AfectacionesCatastrales.RestriccionesDominioNoExplicitas getRestriccionesDominicioNoExplicitas() {
		return restriccionesDominicioNoExplicitas;
	}

	public void setRestriccionesDominicioNoExplicitas(
			AfectacionesCatastrales.RestriccionesDominioNoExplicitas restriccionesDominicioNoExplicitas) {
		this.restriccionesDominicioNoExplicitas = restriccionesDominicioNoExplicitas;
	}

	public TipoParcela getTipoParcela() {
		return tipoParcela;
	}

	public void setTipoParcela(TipoParcela tipoParcela) {
		this.tipoParcela = tipoParcela;
	}

	public Manzana getManzana() {
		return manzana;
	}

	public void setManzana(Manzana manzana) {
		if ((this.manzana != null) && (!this.manzana.equals(manzana))) {
			this.getListaParcelasPorCuadra().clear();
		}
		this.manzana = manzana;
	}

	public List<ParcelaPorCuadra> getListaParcelasPorCuadra() {
		return listaParcelasPorCuadra;
	}

	public void setListaParcelasPorCuadra(
			List<ParcelaPorCuadra> listaParcelasPorCuadra) {
		this.listaParcelasPorCuadra = listaParcelasPorCuadra;
	}

	public String getCodigoPostal() {
		return this.getDomicilioParcelario().getCodigoPostal();
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public long getIdParcela() {
		return idParcela;
	}

	public void setIdParcela(long idParcela) {
		this.idParcela = idParcela;
	}

	public String getNroMatricula() {
		return nroMatricula;
	}

	public void setNroMatricula(String nroMatricula) {
		this.nroMatricula = nroMatricula;
	}

	public String getNroPartidaProvincial() {
		return nroPartidaProvincial;
	}

	public void setNroPartidaProvincial(String nroPartidaProvincial) {
		this.nroPartidaProvincial = nroPartidaProvincial;
	}

	public String getNroRegistro() {
		return nroRegistro;
	}

	public void setNroRegistro(String nroRegistro) {
		this.nroRegistro = nroRegistro;
	}

	public Double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(Double superficie) {
		this.superficie = superficie;
	}

	public Domicilio getDomicilioParcelario() {
		return domicilioParcelario;
	}

	public void setDomicilioParcelario(Domicilio domicilioParcelario) {
		this.domicilioParcelario = domicilioParcelario;
	}

	@Override
	public String toString() {
		String retorno = Util.returnToString(this, serialVersionUID);
		if(retorno != null) {
			return retorno;
		}
		
		String locRetorno = "Parcela ";

		if(this.getNomenclaturaCatastral() != null && this.getNomenclaturaCatastral().getNroParcela() != null) {
			locRetorno += "nro " + this.getNomenclaturaCatastral().getNroParcela();
		} else { 
			locRetorno += "S/N";
		}

		if (tituloPropiedad != null){
			if(tituloPropiedad.getListaRegistrosPropietarios() != null && tituloPropiedad.getListaRegistrosPropietarios().iterator().hasNext()){
				locRetorno += " ["+tituloPropiedad.getListaRegistrosPropietarios().iterator().next().getPersona().getDenominacion()+"]";
			}
		}
		return locRetorno;
	}

	public String getStringTitular(){
		return tituloPropiedad != null && tituloPropiedad.getListaRegistrosPropietarios() != null && tituloPropiedad.getListaRegistrosPropietarios().iterator().hasNext() ? 
				tituloPropiedad.getListaRegistrosPropietarios().iterator().next().getPersona().getDenominacion() : "";
	}
	
	public String getStringListaPropietarios(){
		return this.tituloPropiedad != null ? this.tituloPropiedad.getStringListaPropietarios() : null;
	}
	
	public void setStringListaPropietarios(String pSb){};

	public TituloPropiedadParcelario getTituloPropiedad() {
		return tituloPropiedad;
	}

	public void setTituloPropiedad(TituloPropiedadParcelario tituloPropiedad) {
		if(tituloPropiedad != null) {
			tituloPropiedad.setParcela(this);
		}
		this.tituloPropiedad = tituloPropiedad;
	}

	public Double getAvaluoTerreno() {
		return avaluoTerreno;
	}

	public void setAvaluoTerreno(Double avaluoTerreno) {
		this.avaluoTerreno = avaluoTerreno;
	}

	public Double getMetrosFrente() {
		return metrosFrente;
	}

	public void setMetrosFrente(Double metrosFrente) {
		this.metrosFrente = metrosFrente;
	}

	public Double getMetrosFrenteTotal(){
		Double metrosTotales = 0D;
		for (ParcelaPorCuadra cadaParcelaPorCuadra : this.listaParcelasPorCuadra){
			metrosTotales += cadaParcelaPorCuadra.getMetrosPorCuadra();
		}
		return metrosTotales;
	}

	public Double getMetrosFrentePorCuadra(Cuadra pCuadra) {
		Double cantidadTotal = 0d;
		for (ParcelaPorCuadra cadaParcelaPorCuadra : this
				.getListaParcelasPorCuadra()) {
			if (cadaParcelaPorCuadra.getCuadra().equals(pCuadra)) {
				cantidadTotal += cadaParcelaPorCuadra.getMetrosPorCuadra();
			}
		}
		return cantidadTotal;
	}

	public Set<RegistroMejora> getListaRegistrosMejora() {
		return listaRegistrosMejora;
	}

	public void setListaRegistrosMejora(Set<RegistroMejora> listaRegistrosMejora) {
		this.listaRegistrosMejora = listaRegistrosMejora;
	}

	@Override
	public int hashCode() {
		if (this.getIdParcela() == -1) {
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idParcela ^ (idParcela >>> 32));
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
		final Parcela other = (Parcela) obj;
		if (idParcela != other.idParcela)
			return false;
		return true;
	}

	public boolean isPropietariosJubilados() throws CatastroException {
		boolean locJubilado = true;
		if (this.getTituloPropiedad() == null) {
			throw new CatastroException(4);
		}
		for (RegistroPropietario locRegistroPropietario : this
				.getTituloPropiedad().getListaRegistrosPropietarios()) {
			Persona locPersona = locRegistroPropietario.getPersona();
			if (locPersona instanceof PersonaFisica) {
				locJubilado = locJubilado
						&& ((PersonaFisica) locPersona).isJubilado();
			}
		}
		return locJubilado;
	}

	/**
	 * Recupera los registros de mejora que se encuentran activos
	 * 
	 * @return listado de RegistroMejora
	 */
	public List<RegistroMejora> getListaRegistrosMejoraActivos() {
		List<RegistroMejora> locListaRegistrosMejora = new ArrayList<RegistroMejora>();
		for (RegistroMejora locRegistroMejora : this.listaRegistrosMejora) {
			if (locRegistroMejora.isActivo()) {
				locListaRegistrosMejora.add(locRegistroMejora);
			}
		}
		return locListaRegistrosMejora;
	}

	/**
	 * recupera la superficie total de mejoras
	 * 
	 * @return
	 */
	public Double getSuperficieMejoras() {
		Double superficie = 0d;
		for (RegistroMejora cadaRegistroMejora : this.getListaRegistrosMejoraActivos()) {
			superficie += cadaRegistroMejora.getSuperficie();
		}
		return superficie;
	}

	//	public AsociacionParcela getAsociacionParcela() {
	//		return asociacionParcela;
	//	}
	//
	//	public void setAsociacionParcela(AsociacionParcela pAsociacionParcela) {
	//		this.asociacionParcela = pAsociacionParcela;
	//	}
	//
	//	public void setZona(Zona pZona) throws Exception {
	//		try {
	//			if(pZona != null){
	//			if (asociacionParcela == null) {
	//				asociacionParcela = new AsociacionParcela();
	//			}
	//			asociacionParcela.setParcela(this);
	//			asociacionParcela.setZona(pZona);
	//			pZona.add(asociacionParcela);
	//			}
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			throw e;
	//		}
	//	}
	//
	//	public Zona getZona() {
	//		return (this.asociacionParcela != null)?this.asociacionParcela.getZona():null;
	//	}

	public void addZona(Zona pZona){
		AsociacionParcela locAsociacion = new AsociacionParcela();
		locAsociacion.setZona(pZona);
		locAsociacion.setParcela(this);
		this.listaAsociacionParcela.add(locAsociacion);
	}

	public void removeZona(AsociacionParcela pAsociacion){
		this.listaAsociacionParcela.remove(pAsociacion);
	}

	public List<AsociacionParcela> getListaAsociacionParcela() {
		return listaAsociacionParcela;
	}

	public void setListaAsociacionParcela(
			List<AsociacionParcela> listaAsociacionParcela) {
		this.listaAsociacionParcela = listaAsociacionParcela;
	}

	/**
	 * Agrega Una Subparcela a la parcela
	 * 
	 * @param pParcela
	 * @throws Exception
	 */
	public void addSubParcela(SubParcela pParcela) throws Exception {
		try {
			if ((this.getSupercieSubParcelas() + pParcela.getSuperficie()) > this.getSuperficie()) {
				throw new CatastroException(87);
			}

			this.getListaSubParcelas().add(pParcela);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Quita Una SubParcela de la parcela
	 * 
	 * @param pParcela
	 * @throws Exception
	 */
	public void removeSubParcela(SubParcela pParcela) throws Exception {
		try {
			if(this.getListaSubParcelas().size() == 2){
				this.getListaSubParcelas().clear();
			}else if (this.getListaSubParcelas().contains(pParcela)) {
				this.getListaSubParcelas().remove(pParcela);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Boolean isSubParcelada(){
		if(this.getListaSubParcelas().isEmpty()){
			return false;
		}else{
			return true;
		}
	}

	public String getNroParcela(){
		if(this.nomenclaturaCatastral == null){
			return null;
		}
		return this.nomenclaturaCatastral.getNroParcela();
	}

	public String getCalle(){
		return this.domicilioParcelario.getCalle();
	}

	public String getAltura(){
		return this.domicilioParcelario.getAltura();
	}

	/**
	 * Calcula la sumatoria de la supercifie de todas las subParcelas
	 * @return
	 */
	public Double getSupercieSubParcelas() {
		Double locRetorno = 0D;
		for (SubParcela cadaSubParcela : this.getListaSubParcelas()) {
			locRetorno += cadaSubParcela.getSuperficie();
		}
		return locRetorno;
	}

	/**
	 * Usar preferentemente para el subparcelamiento devuelve una parcela con los datos necesarios
	 */
	@Override
	public Parcela clone() throws CloneNotSupportedException {
		Parcela locParcela = (Parcela) super.clone();
		locParcela.setIdParcela(-1);
		locParcela.setNroCuenta(null);
		locParcela.setNroMatricula(null);
		locParcela.setNroPartidaProvincial(null);
		locParcela.setNroRegistro(null);
		locParcela.setTituloPropiedad(null);
		return locParcela;
	}

	@PostPersist
	public void postPersist(){
		for (AtributoDinamico<?> cadaAtributo : getListaAtributosDinamicos()){
			cadaAtributo.setIdEntidad(getIdParcela());
		}
	}

	public PlanoMensura getPlanoMensura() {
		return planoMensura;
	}

	public void setPlanoMensura(PlanoMensura planoMensura) throws Exception {
		if (planoMensura != null) {
			planoMensura.setParcela(this);
		}
		this.planoMensura = planoMensura;
	}

	public PlanoConstruccion getPlanoConstruccion() {
		return planoConstruccion;
	}

	public void setPlanoConstruccion(PlanoConstruccion planoConstruccion) throws Exception{
		if (planoConstruccion != null) {
			planoConstruccion.setParcela(this);
		}
		this.planoConstruccion = planoConstruccion;
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
		this.tituloPropiedad.setLlaveUsuarioAuditoria(llaveUsuarioAuditoria);
		for(PlanoConstruccion cadaPlanoConstruccion : this.listaPlanosConstruccion) {
			cadaPlanoConstruccion.setLlaveUsuarioAuditoria(llaveUsuarioAuditoria);
		}
		this.planoMensura.setLlaveUsuarioAuditoria(llaveUsuarioAuditoria);
	}

	public String getComentarioAuditoria() {
		return comentarioAuditoria;
	}

	public void setComentarioAuditoria(String comentarioAuditoria) {
		this.comentarioAuditoria = comentarioAuditoria;
		this.tituloPropiedad.setComentarioAuditoria(comentarioAuditoria);
//		this.planoConstruccion.setComentarioAuditoria(comentarioAuditoria);
		this.planoMensura.setComentarioAuditoria(comentarioAuditoria);
	}

	public long getIdEntidad() {
		return this.idParcela;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idParcela";
	}

	public boolean isAuditable() {
		return true;
	}

	@Transient
	private Map<String, Object> mapaAtributosDinamicos;

	public Map<String, Object> getMapaNombresValoresAtributosDinamicos(){
		if (this.mapaAtributosDinamicos == null) {
			mapaAtributosDinamicos = new HashMap<String, Object>();
			for (AtributoDinamico<?> cadaAtributo : this.listaAtributosDinamicos) {
				mapaAtributosDinamicos.put(cadaAtributo.getNombre(), cadaAtributo.getValor());
			}
		}
		return mapaAtributosDinamicos;
	}
}
