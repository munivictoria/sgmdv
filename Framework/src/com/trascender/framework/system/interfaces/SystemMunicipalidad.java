/*
 * Generated by XDoclet - Do not edit!
 */
package com.trascender.framework.system.interfaces;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.filtros.FiltroArea;
import com.trascender.framework.recurso.filtros.FiltroCodigoCiiu;
import com.trascender.framework.recurso.filtros.FiltroDiaFeriado;
import com.trascender.framework.recurso.filtros.FiltroDigestoMunicipal;
import com.trascender.framework.recurso.filtros.FiltroLocalidad;
import com.trascender.framework.recurso.filtros.FiltroMunicipalidad;
import com.trascender.framework.recurso.filtros.FiltroPais;
import com.trascender.framework.recurso.filtros.FiltroProvincia;
import com.trascender.framework.recurso.filtros.FiltroSecretaria;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.recurso.persistent.GrupoCiiu;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Municipalidad;
import com.trascender.framework.recurso.persistent.Pais;
import com.trascender.framework.recurso.persistent.Provincia;
import com.trascender.framework.recurso.persistent.SeccionCiiu;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.framework.recurso.transients.AuxIdEntidad;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.recurso.transients.Calendario.EstadoCalendario;

/**
 * Remote interface for SystemMunicipalidad.
 */
@Remote
public interface SystemMunicipalidad {
	
	public static final String JNDI_NAME = "ejb/SystemMunicipalidad/remote";
	
   /**
    * Obtiene los datos de la municipalidad actual Business method
    */
   public FiltroMunicipalidad getMunicipalidad(FiltroMunicipalidad pFiltro)
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /** 
    * Permite definir los datos de la municipalidad, solo se permite una municipalidad. Business method
    */
   public void setMunicipalidad( com.trascender.framework.recurso.persistent.Municipalidad pMunicipalidad )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;
   
   public Municipalidad getMunicipalidad() throws Exception;

   /**
    * Permite agregar un area a la base de datos
    * @param pArea �rea que se desea agregar Business method
    */
   public void addArea( com.trascender.framework.recurso.persistent.Area pArea )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Actualiza los datos de un area
    * @param pArea �rea a actualizar Business method
    */
   public void updateArea( com.trascender.framework.recurso.persistent.Area pArea )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Permite eliminar un area
    * @param pArea �rea a eliminar Business method
    */
   public void removeArea( com.trascender.framework.recurso.persistent.Area pArea )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Permite restaurar un area previamente eliminada
    * @param pArea area que se desea restaurar Business method
    */
   public void restoreArea( com.trascender.framework.recurso.persistent.Area pArea )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Realiza b�squedas de area
    * @param pNombre nombre del area (o las primeras letras del mismo
    * @param pEstado estado en que se encuentra el area (ACTIVO, ELIMINADO) Business method
    */
   public FiltroArea findArea(FiltroArea pFiltro )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   public FiltroArea findAreasSinPermisoSelect(FiltroArea filtro) throws com.trascender.framework.exception.TrascenderException;
   
   /**
    * Business method
    */
   public void setLlave( long pLlave )
      throws java.rmi.RemoteException;

   /**
    * Agrega un pais
    * @param pPais pais a agregar Business method
    */
   public Pais addPais( com.trascender.framework.recurso.persistent.Pais pPais )
      throws com.trascender.framework.exception.TrascenderFrameworkException, java.rmi.RemoteException;

   /**
    * Realiza b�squedas de pais por nombre
    * @param pPais nombre del pais a buscar Business method
    */
   public FiltroPais findPais(FiltroPais pFiltro)
      throws com.trascender.framework.exception.TrascenderFrameworkException, java.rmi.RemoteException;

   /**
    * Actualiza los datos de un pais
    * @param pPais pais a actualizar Business method
    */
   public void updatePais( com.trascender.framework.recurso.persistent.Pais pPais )
      throws com.trascender.framework.exception.TrascenderFrameworkException, java.rmi.RemoteException;

   /**
    * Elimina un pais
    * @param pPais pais a eliminar Business method
    */
   public void removePais( com.trascender.framework.recurso.persistent.Pais pPais )
      throws com.trascender.framework.exception.TrascenderFrameworkException, java.rmi.RemoteException;

   /**
    * Permite agregar una localidad
    * @param pLocalidad localidad a agregar Business method
    */
   public void addLocalidad( com.trascender.framework.recurso.persistent.Localidad pLocalidad )
      throws com.trascender.framework.exception.TrascenderFrameworkException, java.rmi.RemoteException;

   /**
    * Actualiza en la base de datos una localidad
    * @param pLocalidad localidad a actualizar Business method
    */
   public void updateLocalidad( com.trascender.framework.recurso.persistent.Localidad pLocalidad )
      throws com.trascender.framework.exception.TrascenderFrameworkException, java.rmi.RemoteException;

   /**
    * Elimina una localidad
    * @param pLocalidad localidad que se desea eliminar Business method
    */
   public void removeLocalidad( com.trascender.framework.recurso.persistent.Localidad pLocalidad )
      throws com.trascender.framework.exception.TrascenderFrameworkException, java.rmi.RemoteException;

   /**
    * Realiza una b�squeda de localidades
    * @param pCodPostal c�digo postal de la localidad que se desa emcpmtrar
    * @param pProvincia provincia a la que pertenecen las localidades
    * @param pPais pa�s al que pertenecen las localidades Business method
    */
   public FiltroLocalidad findLocalidad(FiltroLocalidad pFiltro)
      throws com.trascender.framework.exception.TrascenderFrameworkException, java.rmi.RemoteException;

   /**
    * Permite agregar una nueva provincia
    * @param pProvincia provincia que se desea agregar Business method
    */
   public Provincia addProvincia( com.trascender.framework.recurso.persistent.Provincia pProvincia )
      throws com.trascender.framework.exception.TrascenderFrameworkException, java.rmi.RemoteException;

   /**
    * Permite actualizar una provincia
    * @param pProvincia provincia a actulizar Business method
    */
   public void updateProvincia( com.trascender.framework.recurso.persistent.Provincia pProvincia )
      throws com.trascender.framework.exception.TrascenderFrameworkException, java.rmi.RemoteException;

   /**
    * Permite eliminar una provincia
    * @param pProvincia provincia que se desea eliminar Business method
    */
   public void removeProvincia( com.trascender.framework.recurso.persistent.Provincia pProvincia )
      throws com.trascender.framework.exception.TrascenderFrameworkException, java.rmi.RemoteException;

   /**
    * Permite encontrar una provincia
    * @param pProvincia primeras letras del nombre de la provincia que se desea encontrar
    * @param pPais pais al que pertenece la provincia Business method
    */
   public FiltroProvincia findProvincia(FiltroProvincia pFiltro)
      throws com.trascender.framework.exception.TrascenderFrameworkException, java.rmi.RemoteException;

   public com.trascender.framework.recurso.persistent.Localidad getLocalidadPorId( long pId )
      throws java.lang.Exception, java.rmi.RemoteException;

   public com.trascender.framework.recurso.persistent.Provincia getProvinciaPorId( long pId )
      throws java.lang.Exception, java.rmi.RemoteException;

   public com.trascender.framework.recurso.persistent.Pais getPaisPorId( long pId )
      throws java.lang.Exception, java.rmi.RemoteException;

   public DiaFeriado getDiaFeriadoPorId(long pId) throws Exception;
   
   /**
    * Registra un d�a feriado
    * @param pDiaFeriado
    * @return 
    * @throws Exception
    */
   public com.trascender.framework.recurso.persistent.DiaFeriado addDiaFeriado( com.trascender.framework.recurso.persistent.DiaFeriado pDiaFeriado )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Actualiza los datos de los dias feriados
    * @param pDiaFeriado
    * @return 
    * @throws TrascenderException
    */
   public com.trascender.framework.recurso.persistent.DiaFeriado updateDiaFeriado( com.trascender.framework.recurso.persistent.DiaFeriado pDiaFeriado )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Elimina un d�a feriado
    * @param pDiaFeriado
    * @throws TrascenderException
    */
   public void deleteDiaFeriado( com.trascender.framework.recurso.persistent.DiaFeriado pDiaFeriado )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Recupera el listado de d�as feriados por nombre y a�o
    * @param pNombre
    * @param pAnio
    * @return 
    * @throws TrascenderException
    */
   public FiltroDiaFeriado findListaDiasFeriados(FiltroDiaFeriado pFiltro)
      throws TrascenderException, java.rmi.RemoteException;
   
   public DigestoMunicipal addDigestoMunicipal(DigestoMunicipal pDigestoMunicipal )
      throws TrascenderException, RemoteException;

   public DigestoMunicipal updateDigestoMunicipal(DigestoMunicipal pDigestoMunicipal )
      throws TrascenderException, RemoteException;

   public void deleteDigestoMuncipal(DigestoMunicipal pDigestoMunicipal )
      throws TrascenderException, RemoteException;

   public List<DigestoMunicipal> findListaDigestoMunicipalPorConcordancia(DigestoMunicipal pNodo)
			throws TrascenderException, RemoteException;

   /**
    * 
    * @param pAnioFecha
    * @param pTipo
    * @param pEstado
    * @param pNumero
    * @return
    * @throws com.trascender.framework.exception.TrascenderException
    * @throws java.rmi.RemoteException
    */
	public FiltroDigestoMunicipal findListaDigestosMunicipales(FiltroDigestoMunicipal pFiltro)
      throws TrascenderException, RemoteException;

   public DigestoMunicipal getDigestoMunicipalPorId( long pIdDigestoMunicipal )
      throws TrascenderException, RemoteException;
   
   /**Metodos el modulo Temas
    */
   
   /**
    * Obtiene la localidad del municipio
    * @return
    * @throws Exception
    * @throws RemoteException
    */
   public Localidad getLocalidadMunicipal() throws Exception, RemoteException;
   
   public FiltroCodigoCiiu findListaCodigosCiiu(FiltroCodigoCiiu pFiltro) throws TrascenderException;
   
   public List<AuxIdEntidad> findListaAuxIdCodigoCiiu(String cadena) throws TrascenderException;

   public Area getAreaById(Long pId) throws TrascenderException;
   
   public CodigoCiiu getCodigoCiiuById(Long pId) throws Exception, RemoteException;
//   public void addSeccionCiuu(SeccionCiiu pSeccion) throws Exception, RemoteException;
   
   public List <SeccionCiiu> findListaSeccionCiiu(String pCodigo, String pNombre) throws TrascenderException;
   
   public List <GrupoCiiu> findListaGrupoCiiu(String pCodigo, String pNombre, SeccionCiiu pSeccion) throws TrascenderException;
   
   public Calendario addCalendario(Calendario pCalendario) throws Exception;
	
	public Calendario updateCalendario(Calendario pCalendario) throws Exception;

	public Collection<Calendario> findListaCalendarios(Long pIdTipoObligacion, 
			Integer pAnio,
			EstadoCalendario pEstado) throws Exception;
	
	public Secretaria addSecretaria(Secretaria pSecretaria) throws TrascenderException;
	
	public void updateSecretaria(Secretaria pSecretaria) throws TrascenderException;
	
	public void deleteSecretaria(Secretaria pSecretaria) throws TrascenderException;
	
	public FiltroSecretaria findListaSecretarias(FiltroSecretaria pFiltro) throws TrascenderException;
	
	public Secretaria getSecretariaPorId(long idSecretaria) throws TrascenderException;
	

}
