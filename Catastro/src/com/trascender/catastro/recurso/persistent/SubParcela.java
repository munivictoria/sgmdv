package com.trascender.catastro.recurso.persistent;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.trascender.catastro.exception.CatastroException;
import com.trascender.framework.recurso.persistent.Persona;

//@Entity
//@DiscriminatorValue(value="SP")
public class SubParcela extends Parcela {
	
	public static final long serialVersionUID = -1663543463775124837L;

	/**
	 *Estados: ACTIVO, ELIMINADO; 
	 */
	public enum Estado{
		ACTIVO,
		ELIMINADO;
		
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(this.name());
		}
	}
	
	@ManyToOne(optional=false, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_PARCELA_RELA")
	private Parcela padre;
	
	@ManyToOne(optional=true, fetch = FetchType.LAZY)
	@JoinColumn(name="ID_PERSONA")
	private Persona titular;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.ACTIVO;
	
	@Deprecated
	/**
	 * Usar el constructor sobrecargado.
	 */
	public SubParcela(){
		super();
		this.setNroCuenta(null);
		this.setNroMatricula(null);
		this.setNroPartidaProvincial(null);
		this.setNroRegistro(null);
		this.setTituloPropiedad(null);
	}
	
	/**
	 * Crea una subParcela con los mismos datos que el padre y genera el titulo de propiedad para el nuevo inquilino.
	 * @param pParcela si la parcela es nula se genera con los datos por defecto y deberan setearse a pata los datos.
	 * @param pTitular
	 */
	public SubParcela(Parcela pParcela, Persona pTitular) {
		super();
		try {
		if (pTitular == null){
			throw new CatastroException(88);
		}
		
		if(pParcela != null){
			this.setAfectacionesExplicitas(pParcela.getAfectacionesExplicitas());
			this.setAfectacionesNoExplicitas(pParcela.getAfectacionesNoExplicitas());	
//			this.setAsociacionParcela(pParcela.getAsociacionParcela());
			this.setListaAsociacionParcela(pParcela.getListaAsociacionParcela());
			this.setAvaluoPorMejoras(pParcela.getAvaluoPorMejoras());
			this.setAvaluoTerreno(pParcela.getAvaluoTerreno());
			this.setDomicilioParcelario(pParcela.getDomicilioParcelario().clone());
			this.setFechaAlta(Calendar.getInstance().getTime());
			this.setListaParcelasPorCuadra(pParcela.getListaParcelasPorCuadra());
			this.setListaRegistrosMejora(pParcela.getListaRegistrosMejora());
			this.setManzana(pParcela.getManzana());
			this.setMetrosFrente(pParcela.getMetrosFrente());
			this.setPadre(pParcela);
			this.setPlanta(pParcela.getPlanta());
			this.setRestriccionesDominioExplicitas(pParcela.getRestriccionesDominioExplicitas());
			this.setRestriccionesDominicioNoExplicitas(pParcela.getRestriccionesDominicioNoExplicitas());
			this.setSuperficie(pParcela.getSuperficie());
			this.setTipoComercial(pParcela.getTipoComercial());
			this.setTipoEquipamiento(pParcela.getTipoEquipamiento());
			this.setTipoParcela(pParcela.getTipoParcela());
			this.setTipoResidencial(pParcela.getTipoResidencial());
			this.setTipoRural(pParcela.getTipoRural());
			this.setTipoVarios(pParcela.getTipoVarios());
			this.setTitular(pTitular);
			this.setNroCuenta(null);
			this.setNroMatricula(null);
			this.setNroPartidaProvincial(null);
			this.setNroRegistro(null);
			this.setTituloPropiedad(null);
//			this.setZona(pParcela.getZona());
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Genera una parcela nueva a partir de si misma
	 * @return
	 * @throws Exception
	 */
	public Parcela parseToParcela() throws Exception{
		Parcela locParcela = new Parcela();
		locParcela.setAfectacionesExplicitas(this.getAfectacionesExplicitas());
		locParcela.setAfectacionesNoExplicitas(this.getAfectacionesNoExplicitas());	
//		locParcela.setAsociacionParcela(this.getAsociacionParcela());
		this.setListaAsociacionParcela(locParcela.getListaAsociacionParcela());
		locParcela.setAvaluoPorMejoras(this.getAvaluoPorMejoras());
		locParcela.setAvaluoTerreno(this.getAvaluoTerreno());
		locParcela.setDomicilioParcelario(this.getDomicilioParcelario().clone());
		locParcela.setFechaAlta(this.getFechaAlta());
		locParcela.setListaParcelasPorCuadra(this.getListaParcelasPorCuadra());
		locParcela.setListaRegistrosMejora(this.getListaRegistrosMejora());
		locParcela.setManzana(this.getManzana());
		locParcela.setMetrosFrente(this.getMetrosFrente());
		locParcela.setNroCuenta(this.getNroCuenta());
		locParcela.setNroMatricula(this.getNroMatricula());
		locParcela.setNroPartidaProvincial(this.getNroPartidaProvincial());
		locParcela.setNroRegistro(this.getNroRegistro());
		locParcela.setPlanta(this.getPlanta());
		locParcela.setRestriccionesDominioExplicitas(this.getRestriccionesDominioExplicitas());
		locParcela.setRestriccionesDominicioNoExplicitas(this.getRestriccionesDominicioNoExplicitas());
		locParcela.setSuperficie(this.getSuperficie());
		locParcela.setTipoComercial(this.getTipoComercial());
		locParcela.setTipoEquipamiento(this.getTipoEquipamiento());
		locParcela.setTipoParcela(this.getTipoParcela());
		locParcela.setTipoResidencial(this.getTipoResidencial());
		locParcela.setTipoRural(this.getTipoRural());
		locParcela.setTipoVarios(this.getTipoVarios());
//		locParcela.setZona(this.getZona());
		locParcela.getListaSubParcelas().clear();
		
		return locParcela;
	}
	
	
	public Persona getTitular() {
		return titular;
	}

	public void setTitular(Persona titular) {
		this.titular = titular;
	}

	public Parcela getPadre() {
		return padre;
	}
	public void setPadre(Parcela padre) {
		this.padre = padre;
	}
	
	@Override
	public TituloPropiedadParcelario getTituloPropiedad() {
		return this.padre.getTituloPropiedad();
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		return ("SubParcela de: "+ ((this.getPadre() != null )?this.getPadre().toString() : "Sin Parcela padre. " ) + "Titular: "+ this.getTitular() +" ["+estado+"]" );
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this.getIdParcela() == -1){
			return ((System.identityHashCode(obj) == System.identityHashCode(this)) && super.equals(obj));
		}
		
		return super.equals(obj);
	}
}
