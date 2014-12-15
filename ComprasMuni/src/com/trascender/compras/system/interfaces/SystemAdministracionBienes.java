/*
 * Generated by XDoclet - Do not edit!
 */

package com.trascender.compras.system.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.trascender.compras.recurso.filtros.FiltroBien;
import com.trascender.compras.recurso.filtros.FiltroTipoBien;
import com.trascender.compras.recurso.filtros.FiltroUnidad;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.TipoBien;
import com.trascender.compras.recurso.persistent.suministros.Unidad;
import com.trascender.framework.recurso.transients.AuxIdEntidad;

/**
 * Remote interface for SystemAdministracionBienes.
 */
@Remote
public interface SystemAdministracionBienes {

	public static final String JNDI_NAME = "ejb/SystemAdministracionBienes/remote";

	public void setLlave(long pLlave) throws java.rmi.RemoteException;

	// /**
	// * Business method
	// */
	// public com.trascender.compras.recurso.persistent.suministros.GrupoBien addGrupoBienes( com.trascender.compras.recurso.persistent.suministros.GrupoBien
	// pGrupoBien )
	// throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;
	//
	// /**
	// * Business method
	// */
	// public java.util.List findListaGrupoBienesPorNodo( com.trascender.compras.recurso.persistent.suministros.GrupoBien pNodo )
	// throws java.lang.Exception, java.rmi.RemoteException;
	//
	// /**
	// * Business method
	// */
	// public com.trascender.compras.recurso.persistent.suministros.GrupoBien updateGrupoBienes( com.trascender.compras.recurso.persistent.suministros.GrupoBien
	// pNodo )
	// throws java.lang.Exception, java.rmi.RemoteException;
	//
	// /**
	// * Business method
	// */
	// public void deleteGrupoBienes( com.trascender.compras.recurso.persistent.suministros.GrupoBien pNodo )
	// throws java.lang.Exception, java.rmi.RemoteException;
	//
	// /**
	// * Business method
	// */
	// public com.trascender.compras.recurso.persistent.suministros.GrupoBien findGrupoBienesPorNombre( java.lang.String pNombre )
	// throws java.lang.Exception, java.rmi.RemoteException;
	//
	// /**
	// * Business method
	// */
	// public java.util.List findListaGrupoBienes( )
	// throws java.lang.Exception, java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public com.trascender.compras.recurso.persistent.suministros.Bien addBien(com.trascender.compras.recurso.persistent.suministros.Bien pBien) throws java.lang.Exception,
			java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public FiltroBien findListadoBienes(FiltroBien filtro) throws java.lang.Exception, java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public com.trascender.compras.recurso.persistent.suministros.Bien updateBien(com.trascender.compras.recurso.persistent.suministros.Bien pBien) throws java.lang.Exception,
			java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public void deleteBien(com.trascender.compras.recurso.persistent.suministros.Bien pBien) throws java.lang.Exception, java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public com.trascender.compras.recurso.persistent.suministros.Bien restoreBien(com.trascender.compras.recurso.persistent.suministros.Bien pBien) throws java.lang.Exception,
			java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public com.trascender.compras.recurso.persistent.suministros.Bien findBienByID(long pIdBien) throws java.lang.Exception, java.rmi.RemoteException;

	/**
	 * Business method
	 * 
	 * @param pGrupo
	 * @param pBien
	 * @return
	 * @throws Exception
	 * @throws java.rmi.RemoteException
	 */
	public List<Bien> validarNombreBien(Bien pBien) throws Exception, java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public Unidad addUnidad(Unidad pUnidad) throws Exception, java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public Unidad updateUnidad(Unidad pUnidad) throws Exception, java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public void deleteUnidad(Unidad pUnidad) throws Exception, java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public Unidad getUnidadByID(Long pIDUnidad) throws Exception, java.rmi.RemoteException;

	public TipoBien getTipoBienByID(Long pIDTipoBien) throws Exception, java.rmi.RemoteException;

	/**
	 * Business method
	 */
	public FiltroUnidad findListaUnidad(FiltroUnidad filtro) throws Exception, java.rmi.RemoteException;

	public List<Unidad> findListaUnidad() throws Exception;

	public FiltroTipoBien findListaTipoBien(FiltroTipoBien filtro) throws Exception;

	public List<TipoBien> findListaTipoBien() throws Exception;

	public FiltroBien findListaBien(FiltroBien filtro) throws Exception;

	public TipoBien addTipoBien(TipoBien pTipoBien) throws Exception;

	public void updateTipoBien(TipoBien pTipoBien) throws Exception;

	public void deleteTipoBien(TipoBien pTipoBien) throws Exception;

	public List<AuxIdEntidad> findListaAuxIdBien(String cadena) throws Exception;
}