package com.trascender.habilitaciones.recurso.persistent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.catastro.exception.CatastroException;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Parcela.TipoParcela;
import com.trascender.catastro.recurso.persistent.Planta;
import com.trascender.catastro.recurso.persistent.TipoPlanta;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;


@Entity
@DiscriminatorValue(value = "PARCELARIO")
public class TipoParametroParcelario extends TipoParametro{
	
	
	public static final long serialVersionUID = 8348960568849000897L;

	public enum AtributoParcela{
		SUPERFICIE_TERRENO,
		AVALUO_TERRENO,
		AVALUO_MEJORAS,
		METROS_FRENTE,
		ES_RADIO_CENTRICO,
		ES_PROPIETARIOS_JUBILADOS,
		ES_BALDIO,
		ES_EXENTO_DE_RECARGO_POR_BALDIO,
		EN_LOTEO,
		EN_POSESION,
		ES_PROPIEDAD_HORIZONTAL,
		ES_ESQUINA,
		CODIGO_SERVICIO_OSM,
		
		//Calculado desde la lógica
		SUPERFICIE_MEJORAS;
		
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "ATRIBUTO")
	private AtributoParcela atributoParcela;
	
	@Transient
	private Map<Long, String> mapaRegAlicuotas;

	public AtributoParcela getAtributoParcela() {
		return atributoParcela;
	}
	public void setAtributoParcela(AtributoParcela atributoParcela) {
		this.atributoParcela = atributoParcela;
	}

	@Override
	public Double getValor(DocHabilitanteEspecializado pDocHabilitanteEspecializado) throws TrascenderException{
		try{
			if (this.getAtributoParcela() != null){
				Parcela locParcela = pDocHabilitanteEspecializado.getParcela();
				switch(this.getAtributoParcela()){
					case SUPERFICIE_TERRENO : return (locParcela.getSuperficie() != null)?locParcela.getSuperficie().doubleValue():0d;
					case AVALUO_MEJORAS: return (locParcela.getAvaluoPorMejoras() != null)?locParcela.getAvaluoPorMejoras().doubleValue():0d;
					case AVALUO_TERRENO: return (locParcela.getAvaluoTerreno() != null)?locParcela.getAvaluoTerreno().doubleValue():0d;
					case ES_BALDIO: return locParcela.getPlanta().getTipoEdificacion().equals(Planta.Edificacion.BALDIO)?1d:0d;
					case ES_PROPIETARIOS_JUBILADOS: return locParcela.isPropietariosJubilados()?1d:0d;
					case METROS_FRENTE: return locParcela.getMetrosFrenteTotal();
					case SUPERFICIE_MEJORAS: return (locParcela.getSuperficieMejoras() != null)?locParcela.getSuperficieMejoras().doubleValue():0d;
					case ES_RADIO_CENTRICO: return ((locParcela.getPlanta().getTipoPlanta().equals(TipoPlanta.Tipo.URBANA)))?1d:0d;
					case EN_LOTEO: return 0d;
					case EN_POSESION: return (locParcela.getTipoParcela().equals(TipoParcela.POSESORIA))?1d:0d;
					case ES_PROPIEDAD_HORIZONTAL: return locParcela.getPlanta().getTipoEdificacion().equals(Planta.Edificacion.EDIFICADO_PH)?1d:0d;
					case CODIGO_SERVICIO_OSM: return getCodigoServicio(locParcela);
					case ES_ESQUINA: return locParcela.getListaParcelasPorCuadra().size() > 1 ? 1d : 0d;
				}
			}
			return 0d;
		}
		catch(CatastroException e){
			return 0d;
		}
	}
	
	private Double getCodigoServicio(Parcela pParcela) {
		if (mapaRegAlicuotas == null){
			this.inicializarMapa();
		}
		Double resultado = 0D;
		String locCodigo = mapaRegAlicuotas.get(pParcela.getIdParcela());
		if (locCodigo != null){
			resultado = Double.valueOf(locCodigo);
		}
		return resultado;
	}
	
	/**
	 * Un mapa papa.. soy el mejor...
	 */
	private void inicializarMapa(){
		try{
			EntityManager em = (EntityManager) new InitialContext().lookup("java:comp/env/Vipians");
			Criterio locCriterio = Criterio.getInstance(em, DocumentoOSP.class)
				.setDistinct(true)
				.add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO))
				.setProyeccion(Proyeccion.PROP("parcela.idParcela","listaAsocRegAlicuota.registroAlicuota.codigo"));
			List<Object[]> locListaResultados = locCriterio.list();
			this.mapaRegAlicuotas = new HashMap<Long, String>();
			for (Object[] cadaResultado : locListaResultados){
				this.mapaRegAlicuotas.put((Long)cadaResultado[0],(String)cadaResultado[1]);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		this.atributoParcela = AtributoParcela.valueOf(pNombreAtributo);
	}
}
