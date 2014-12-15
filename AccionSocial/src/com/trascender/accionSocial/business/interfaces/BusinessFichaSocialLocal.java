package com.trascender.accionSocial.business.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.trascender.accionSocial.recurso.filtros.FiltroFichaSocial;
import com.trascender.accionSocial.recurso.filtros.FiltroObraSocial;
import com.trascender.accionSocial.recurso.persistent.Beneficio;
import com.trascender.accionSocial.recurso.persistent.FichaSocial;
import com.trascender.accionSocial.recurso.persistent.ObraSocial;

@Local
public interface BusinessFichaSocialLocal {
	
	public static final String JNDI_NAME="BusinessFichaSocialLocal";
	/*
	 * Obra social
	 */
	public ObraSocial addObraSocial(ObraSocial pObraSocial) throws Exception;
	
	public ObraSocial updateObraSocial(ObraSocial pObraSocial) throws Exception;
	
	public void deleteObraSocial(ObraSocial pObraSocial) throws Exception;
	
	public FiltroObraSocial findListaObraSocial(FiltroObraSocial pFiltro) throws Exception;
	
	public ObraSocial getObraSocialPorId(long pId) throws Exception;
	
	/*
	 * Beneficio
	 */
	
	public Beneficio addbeneficio(Beneficio pBeneficio) throws Exception;
	
	public Beneficio updateBeneficio(Beneficio pBeneficio) throws Exception;
	
	public void deleteBeneficio(Beneficio pBeneficio) throws Exception;
	
	public Beneficio getBeneficioPorId(long pId) throws Exception;
	
	public List findListaBeneficio(String pNombre, Beneficio.TipoBeneficio pTipoBeneficio) throws Exception;
	
	/*
	 * Ficha social
	 */
	
	public FichaSocial addFichaSocial(FichaSocial pFichaSocial) throws Exception;
	
	public FichaSocial updateFichaSocial(FichaSocial pFichaSocial) throws Exception;
	
	public void deleteFichaSocial(FichaSocial pFichaSocial) throws Exception;
	
	public FichaSocial getFichaSocialPorId(long pId) throws Exception;
	
	public FiltroFichaSocial findListaFichaSocial(FiltroFichaSocial pFiltro) throws Exception;

}
