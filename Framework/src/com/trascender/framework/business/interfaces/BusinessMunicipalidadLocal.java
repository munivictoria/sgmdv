package com.trascender.framework.business.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
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

/**
 * Local interface for BusinessMunicipalidad.
 */
@Local
public interface BusinessMunicipalidadLocal {

	public final static String JNDI_NAME = "ejb/BusinessMunicipalidad";

	/**
	 * Business method
	 */
	public void updateMunicipalidad(Municipalidad pMunicipalidad ) throws Exception;

	/**
	 * Business method
	 */
	public FiltroMunicipalidad getMunicipalidad(FiltroMunicipalidad pFiltro) throws Exception;

	/**
	 * Business method
	 */
	public void addArea( Area pArea ) throws Exception;

	/**
	 * Business method
	 */
	public void updateArea( Area pArea ) throws Exception;

	/**
	 * Business method
	 */
	public FiltroArea findArea(FiltroArea pFiltro) throws Exception;

	/**
	 * Business method
	 */
	public Pais addPais( Pais pPais ) throws Exception;

	/**
	 * Business method
	 */
	public void updatePais( Pais pPais ) throws Exception;

	/**
	 * Business method
	 */
	public void removePais( Pais pPais ) throws Exception;


	public Pais getPaisPorId(long pId);

	/**
	 * Business method
	 */
	public Provincia addProvincia( Provincia pProvincia ) throws Exception;

	/**
	 * Business method
	 */
	public void updateProvincia( Provincia pProvincia ) throws Exception;

	/**
	 * Business method
	 */
	public FiltroProvincia findProvincia(FiltroProvincia pFiltro) throws TrascenderFrameworkException;

	public Provincia getProvinciaPorId(long pId);

	/**
	 * Business method
	 */
	public FiltroPais findPais(FiltroPais pFiltro ) throws TrascenderFrameworkException;

	/**
	 * Business method
	 */
	public void addLocalidad( Localidad pLocalidad ) throws Exception;

	/**
	 * Business method
	 */
	public void updateLocalidad( Localidad pLocalidad ) throws Exception;

	/**
	 * Business method
	 */
	public void removeLocalidad( Localidad pLocalidad ) throws Exception;

	/**
	 * Business method
	 */
	public FiltroLocalidad findLocalidad(FiltroLocalidad pFiltro) throws Exception;


	public Localidad getLocalidadPorId(long pId);

	public DiaFeriado getDiaFeriadoPorId(long pId);

	/**
	 * Business method
	 */
	public void removeProvincia( Provincia pProvincia ) throws Exception;

	/**
	 * Agrega un dia feriado al sistema
	 * @param pDiaFeriado
	 * @return 
	 * @throws Exception
	 */
	public DiaFeriado addDiaFeriado( DiaFeriado pDiaFeriado ) throws Exception;

	/**
	 * Actualiza los datos de un d�a feriado
	 * @param pDiaFeriado
	 * @return 
	 * @throws Exception
	 */
	public DiaFeriado updateDiaFeriado( DiaFeriado pDiaFeriado ) throws Exception;

	/**
	 * Elimina un d�a feriado f�sicamente
	 * @throws Exception
	 */
	public void deleteDiaFeriado( DiaFeriado pDiaFeriado ) throws Exception;

	public FiltroDiaFeriado findListadoDiasFeriados(FiltroDiaFeriado pFiltro ) throws Exception;

	public DigestoMunicipal addDigestoMunicipal( DigestoMunicipal pDigestoMunicipal ) throws Exception;

	public DigestoMunicipal updateDigestoMunicipal( DigestoMunicipal pDigestoMunicipal ) throws Exception;

	public void deleteDigestoMuncipal( DigestoMunicipal pDigestoMunicipal ) throws Exception;

	public FiltroDigestoMunicipal findListaDigestosMunicipales(FiltroDigestoMunicipal pFiltro);

	public List<DigestoMunicipal> findListaDigestoMunicipalPorConcordancia(DigestoMunicipal pNodo) throws Exception;

	/**
	 * Recupera el digesto municipal por número de identificación único
	 * @param pIdDigestoMunicipal
	 * @return 
	 */
	public DigestoMunicipal getDigestoMunicipalPorId( long pIdDigestoMunicipal ) ;

	/**
	 * Obtiene la localidad municipal
	 * @return
	 * @throws TrascenderException
	 */
	public Localidad getLocalidadMunicipal() throws TrascenderException;


	public FiltroCodigoCiiu findListaCodigosCiiu(FiltroCodigoCiiu pFiltro);
	
	public List<AuxIdEntidad> findListaAuxIdCodigoCiiu(String cadena) throws Exception;

	public Area getAreaById(Long pId) throws Exception;

	public CodigoCiiu getCodigoCiiuById(Long pId) throws Exception;
	
	public void addCodigoCiiu(CodigoCiiu pCodigo);
	
	public void updateCodigoCiiu(CodigoCiiu pCodigo);
	
	public void deleteCodigoCiiu(CodigoCiiu pCodigo);

	public List <SeccionCiiu> findListaSeccionCiiu(String pCodigo, String pNombre) throws Exception;

	public List <GrupoCiiu> findListaGrupoCiiu(String pCodigo, String pNombre, SeccionCiiu pSeccion) throws Exception;

	public Secretaria addSecretaria(Secretaria pSecretaria);

	public void updateSecretaria(Secretaria pSecretaria);

	public void deleteSecretaria(Secretaria pSecretaria) throws TrascenderFrameworkException;

	public FiltroSecretaria findListaSecretarias(FiltroSecretaria pFiltro);

	public Secretaria getSecretariaPorId(long idSecretaria);

}
