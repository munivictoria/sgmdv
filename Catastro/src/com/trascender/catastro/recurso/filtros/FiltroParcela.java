package com.trascender.catastro.recurso.filtros;

import java.util.Date;
import java.util.List;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.catastro.recurso.persistent.NomenclaturaCatastral;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Plano;
import com.trascender.catastro.recurso.persistent.TituloPropiedadParcelario;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroParcela extends FiltroAbstracto<Parcela>{

	private static final long serialVersionUID = 76411028183443427L;

	//Parcela
	private Cuadra cuadra;
	private Manzana manzana;
	private String nroPartidaProvincial;
	private String nroRegistro;
	private String nroMatricula;
	private Integer nroCuenta;
	private Persona persona;
	private NomenclaturaCatastral nomenClaturaCatastral;
	private List<AtributoDinamico<?>> listaAtributosDinamicos;
	private Parcela.Estado estado;
	private List<Long> listaIdPersonas;

	private Calle calleComienza;
	private Calle calleFinaliza;
	private Calle calle;
	private String numeroDomicilio;
	
	private Zona zona;

	//Plano Construccion
	private String planoConstruccion;
	private String tomoConstruccion;
	private String folioConstruccion;
	private Parcela parcelaConstruccion;
	private Date fechaInscripcionConstruccion;
	private Plano.Estado estadoConstruccion;
	private String numeroConstruccion;
	private List<AtributoDinamico<?>> listaAtributosDinamicosPlanoConstruccion;

	//Plano Mensura
	private String planoMensura;
	private String tomoMensura;
	private String folioMensura;
	private Parcela parcelaMensura;
	private Date fechaInscripcionMensura;
	private Plano.Estado estadoMensura;
	private List<AtributoDinamico<?>> listaAtributosDinamicosPlanoMensura;

	//Titulo Propiedad
	private Date fechaDesdeTituloPropiedad;
	private Date fechaHastaTituloPropiedad;
	private Persona personaTituloPropiedad;
	private Parcela parcelaTituloPropiedad;
	private TituloPropiedadParcelario.TipoTransaccionCatastral tipoTransaccionCatastralTituloPropiedad;
	private Integer nrotomoTituloPropiedad;
	private Integer nroFolioTituloPropiedad; 
	private Integer nroAsientoTituloPropiedad;
	private List<AtributoDinamico<?>> listaAtributosDinamicosTituloPropiedad;

	public Calle getCalleComienza() {
		return calleComienza;
	}
	public void setCalleComienza(Calle calleComienza) {
		this.calleComienza = calleComienza;
	}
	public Calle getCalleFinaliza() {
		return calleFinaliza;
	}
	public void setCalleFinaliza(Calle calleFinaliza) {
		this.calleFinaliza = calleFinaliza;
	}
	public Cuadra getCuadra() {
		return cuadra;
	}
	public void setCuadra(Cuadra cuadra) {
		this.cuadra = cuadra;
	}
	public Manzana getManzana() {
		return manzana;
	}
	public void setManzana(Manzana manzana) {
		this.manzana = manzana;
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
	public String getNroMatricula() {
		return nroMatricula;
	}
	public void setNroMatricula(String nroMatricula) {
		this.nroMatricula = nroMatricula;
	}
	public Integer getNroCuenta() {
		return nroCuenta;
	}
	public void setNroCuenta(Integer nroCuenta) {
		this.nroCuenta = nroCuenta;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public NomenclaturaCatastral getNomenClaturaCatastral() {
		return nomenClaturaCatastral;
	}
	public void setNomenClaturaCatastral(NomenclaturaCatastral nomenClaturaCatastral) {
		this.nomenClaturaCatastral = nomenClaturaCatastral;
	}
	public String getNumeroDomicilio() {
		return numeroDomicilio;
	}
	public void setNumeroDomicilio(String numeroDomicilio) {
		this.numeroDomicilio = numeroDomicilio;
	}
	public Calle getCalle() {
		return calle;
	}
	public void setCalle(Calle calle) {
		this.calle = calle;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}
	public void setListaAtributosDinamicos(
			List<AtributoDinamico<?>> listaAtributosDinamicos) {
		this.listaAtributosDinamicos = listaAtributosDinamicos;
	}

	public Parcela.Estado getEstado() {
		return estado;
	}
	public void setEstado(Parcela.Estado estado) {
		this.estado = estado;
	}
	//contruccion
	public String getPlanoConstruccion() {
		return planoConstruccion;
	}
	public void setPlanoConstruccion(String planoConstruccion) {
		this.planoConstruccion = planoConstruccion;
	}
	public String getTomoConstruccion() {
		return tomoConstruccion;
	}
	public void setTomoConstruccion(String tomoConstruccion) {
		this.tomoConstruccion = tomoConstruccion;
	}
	public String getFolioConstruccion() {
		return folioConstruccion;
	}
	public void setFolioConstruccion(String folioConstruccion) {
		this.folioConstruccion = folioConstruccion;
	}
	public Parcela getParcelaConstruccion() {
		return parcelaConstruccion;
	}
	public void setParcelaConstruccion(Parcela parcelaConstruccion) {
		this.parcelaConstruccion = parcelaConstruccion;
	}
	public Date getFechaInscripcionConstruccion() {
		return fechaInscripcionConstruccion;
	}
	public void setFechaInscripcionConstruccion(Date fechaInscripcionConstruccion) {
		this.fechaInscripcionConstruccion = fechaInscripcionConstruccion;
	}
	public Plano.Estado getEstadoConstruccion() {
		return estadoConstruccion;
	}
	public void setEstadoConstruccion(Plano.Estado estadoConstruccion) {
		this.estadoConstruccion = estadoConstruccion;
	}
	public String getNumeroConstruccion() {
		return numeroConstruccion;
	}
	public void setNumeroConstruccion(String numeroConstruccion) {
		this.numeroConstruccion = numeroConstruccion;
	}
	public List<AtributoDinamico<?>> getListaAtributosDinamicosPlanoConstruccion() {
		return listaAtributosDinamicosPlanoConstruccion;
	}
	public void setListaAtributosDinamicosPlanoConstruccion(
			List<AtributoDinamico<?>> listaAtributosDinamicosPlanoConstruccion) {
		this.listaAtributosDinamicosPlanoConstruccion = listaAtributosDinamicosPlanoConstruccion;
	}
	//mesura
	public String getPlanoMensura() {
		return planoMensura;
	}
	public void setPlanoMensura(String planoMensura) {
		this.planoMensura = planoMensura;
	}
	public String getTomoMensura() {
		return tomoMensura;
	}
	public void setTomoMensura(String tomoMensura) {
		this.tomoMensura = tomoMensura;
	}
	public String getFolioMensura() {
		return folioMensura;
	}
	public void setFolioMensura(String folioMensura) {
		this.folioMensura = folioMensura;
	}
	public Parcela getParcelaMensura() {
		return parcelaMensura;
	}
	public void setParcelaMensura(Parcela parcelaMensura) {
		this.parcelaMensura = parcelaMensura;
	}
	public Date getFechaInscripcionMensura() {
		return fechaInscripcionMensura;
	}
	public void setFechaInscripcionMensura(Date fechaInscripcionMensura) {
		this.fechaInscripcionMensura = fechaInscripcionMensura;
	}
	public Plano.Estado getEstadoMensura() {
		return estadoMensura;
	}
	public void setEstadoMensura(Plano.Estado estadoMensura) {
		this.estadoMensura = estadoMensura;
	}

	public List<AtributoDinamico<?>> getListaAtributosDinamicosPlanoMensura() {
		return listaAtributosDinamicosPlanoMensura;
	}
	public void setListaAtributosDinamicosPlanoMensura(
			List<AtributoDinamico<?>> listaAtributosDinamicosPlanoMensura) {
		this.listaAtributosDinamicosPlanoMensura = listaAtributosDinamicosPlanoMensura;
	}
	//tituloPropiedad
	public Date getFechaDesdeTituloPropiedad() {
		return fechaDesdeTituloPropiedad;
	}
	public void setFechaDesdeTituloPropiedad(Date fechaDesdeTituloPropiedad) {
		this.fechaDesdeTituloPropiedad = fechaDesdeTituloPropiedad;
	}
	public Date getFechaHastaTituloPropiedad() {
		return fechaHastaTituloPropiedad;
	}
	public void setFechaHastaTituloPropiedad(Date fechaHastaTituloPropiedad) {
		this.fechaHastaTituloPropiedad = fechaHastaTituloPropiedad;
	}
	public Persona getPersonaTituloPropiedad() {
		return personaTituloPropiedad;
	}
	public void setPersonaTituloPropiedad(Persona personaTituloPropiedad) {
		this.personaTituloPropiedad = personaTituloPropiedad;
	}
	public Parcela getParcelaTituloPropiedad() {
		return parcelaTituloPropiedad;
	}
	public void setParcelaTituloPropiedad(Parcela parcelaTituloPropiedad) {
		this.parcelaTituloPropiedad = parcelaTituloPropiedad;
	}
	public TituloPropiedadParcelario.TipoTransaccionCatastral getTipoTransaccionCatastralTituloPropiedad() {
		return tipoTransaccionCatastralTituloPropiedad;
	}
	public void setTipoTransaccionCatastralTituloPropiedad(
			TituloPropiedadParcelario.TipoTransaccionCatastral tipoTransaccionCatastralTituloPropiedad) {
		this.tipoTransaccionCatastralTituloPropiedad = tipoTransaccionCatastralTituloPropiedad;
	}
	public Integer getNrotomoTituloPropiedad() {
		return nrotomoTituloPropiedad;
	}
	public void setNrotomoTituloPropiedad(Integer nrotomoTituloPropiedad) {
		this.nrotomoTituloPropiedad = nrotomoTituloPropiedad;
	}
	public Integer getNroFolioTituloPropiedad() {
		return nroFolioTituloPropiedad;
	}
	public void setNroFolioTituloPropiedad(Integer nroFolioTituloPropiedad) {
		this.nroFolioTituloPropiedad = nroFolioTituloPropiedad;
	}
	public Integer getNroAsientoTituloPropiedad() {
		return nroAsientoTituloPropiedad;
	}
	public void setNroAsientoTituloPropiedad(Integer nroAsientoTituloPropiedad) {
		this.nroAsientoTituloPropiedad = nroAsientoTituloPropiedad;
	}
	public List<AtributoDinamico<?>> getListaAtributosDinamicosTituloPropiedad() {
		return listaAtributosDinamicosTituloPropiedad;
	}
	public void setListaAtributosDinamicosTituloPropiedad(
			List<AtributoDinamico<?>> listaAtributosDinamicosTituloPropiedad) {
		this.listaAtributosDinamicosTituloPropiedad = listaAtributosDinamicosTituloPropiedad;
	}
	public List<Long> getListaIdPersonas() {
		return listaIdPersonas;
	}
	public void setListaIdPersonas(List<Long> listaIdPersonas) {
		this.listaIdPersonas = listaIdPersonas;
	}
}