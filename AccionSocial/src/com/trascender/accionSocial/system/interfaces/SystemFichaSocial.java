package com.trascender.accionSocial.system.interfaces;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Remote;

import com.trascender.accionSocial.recurso.filtros.FiltroFichaSocial;
import com.trascender.accionSocial.recurso.filtros.FiltroObraSocial;
import com.trascender.accionSocial.recurso.persistent.Beneficio;
import com.trascender.accionSocial.recurso.persistent.FichaSocial;
import com.trascender.accionSocial.recurso.persistent.ObraSocial;

@Remote
public interface SystemFichaSocial {
	
	public static final String JNDI_NAME="ejb/SystemFichaSocial/remote";

	public void setLlave(long pLlave) throws RemoteException;
	
	/*
	 * Obra Social
	 */
	
	public ObraSocial addObraSocial(ObraSocial pObraSocial) throws Exception, RemoteException;
	
	public ObraSocial updateObraSocial(ObraSocial pObraSocial) throws Exception, RemoteException;
	
	public void deleteObraSocial(ObraSocial pObraSocial) throws Exception, RemoteException;
	
	public FiltroObraSocial findListaObraSocial(FiltroObraSocial pFiltro) throws Exception, RemoteException;
	
	public ObraSocial getObraSocialPorId(long pId) throws Exception, RemoteException;
	
	/*
	 * Beneficio
	 */
	
	public List findListaBeneficio(String pNombre, Beneficio.TipoBeneficio pTipoBeneficio) throws Exception, RemoteException;
	
	public Beneficio addbeneficio(Beneficio pBeneficio) throws Exception, RemoteException;
	
	public Beneficio updateBeneficio(Beneficio pBeneficio) throws Exception, RemoteException;
	
	public void deleteBeneficio(Beneficio pBeneficio) throws Exception, RemoteException;
	
	public Beneficio getBeneficioPorId(long pId) throws Exception, RemoteException;
	
	/*
	 * Ficha social
	 */
	
	public FichaSocial addFichaSocial(FichaSocial pFichaSocial) throws Exception, RemoteException;

	public FichaSocial updateFichaSocial(FichaSocial pFichaSocial) throws Exception, RemoteException;
	
	public void deleteFichaSocial(FichaSocial pFichaSocial) throws Exception, RemoteException;
	
	public FichaSocial getFichaSocialPorId(long pId) throws Exception, RemoteException;
	
	public FiltroFichaSocial findListaFichaSocial(FiltroFichaSocial pFiltro) throws Exception, RemoteException;

}
