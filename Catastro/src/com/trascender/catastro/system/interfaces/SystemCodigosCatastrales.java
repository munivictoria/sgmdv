package com.trascender.catastro.system.interfaces;

import javax.ejb.Remote;

import com.trascender.catastro.exception.CatastroException;
import com.trascender.catastro.recurso.filtros.FiltroCategoria;
import com.trascender.catastro.recurso.filtros.FiltroTipoConstruccion;
import com.trascender.catastro.recurso.filtros.FiltroValorBasicoMejora;
import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.catastro.recurso.persistent.TipoConstruccion;
import com.trascender.catastro.recurso.persistent.ValorBasicoMejora;
import com.trascender.framework.exception.TrascenderException;

@Remote
public interface SystemCodigosCatastrales
{
	public static final String JNDI_NAME="ejb/SystemCodigosCatastrales/remote";
   /**
    * Agrega un tipo de construcci�n.
    * @param pTipoConstruccion tipo de construcci�n a agregar
    * @throws TrascenderException
    */
   public com.trascender.catastro.recurso.persistent.TipoConstruccion addTipoConstruccion( com.trascender.catastro.recurso.persistent.TipoConstruccion pTipoConstruccion )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Actualiza los datos de un tipo de construcci�n
    * @param pTipoConstruccion tipo de construcci�n a actualizar
    * @throws TrascenderException
    */
   public com.trascender.catastro.recurso.persistent.TipoConstruccion updateTipoConstruccion( com.trascender.catastro.recurso.persistent.TipoConstruccion pTipoConstruccion )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Recupera un listado de tipos de construcci�n
    * @param pIdTipoConstruccion id del tipo de cosntrucci�n
    * @param pNombre primeras letras del nombre del tipo de construcci�n
    */
   @SuppressWarnings("unchecked")
   public FiltroTipoConstruccion findTiposConstruccion(FiltroTipoConstruccion pFiltro )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Elimina el tipo de construcci�n
    * @param pTipoConstruccion tipo de construccion que se desea eliminar
    */
   public void deleteTipoConstruccion( com.trascender.catastro.recurso.persistent.TipoConstruccion pTipoConstruccion )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Recupera el estado anterior de un tipo de construcci�n eliminado
    * @param pTipoConstruccion tipo de construcci�n que se desea recuperar
    */
   public com.trascender.catastro.recurso.persistent.TipoConstruccion restoreTipoConstruccion( com.trascender.catastro.recurso.persistent.TipoConstruccion pTipoConstruccion )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Permite agregar una categor�a
    * @param pCategoria categor�a que se desea agregar
    */
   public com.trascender.catastro.recurso.persistent.Categoria addCategoria( com.trascender.catastro.recurso.persistent.Categoria pCategoria )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Permite actualizar los datos de una categoria
    * @param pCategoria categoria que se desea actualizar
    */
   public com.trascender.catastro.recurso.persistent.Categoria updateCategoria( com.trascender.catastro.recurso.persistent.Categoria pCategoria )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Recupera una lista de categor�as
    * @param pNombre nombre de la categor�a
    * @param pCodigoCategoria c�digo de la categor�a
    * @param pEstado estado en que se encuentra la categoria
    * @param pTipoConstruccion tipo de construccion de la categoria
    * @throws TrascenderException
    */
   @SuppressWarnings("unchecked")
   public FiltroCategoria findListaCategorias(FiltroCategoria filtro )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Elimina una categor�a
    * @param pCategoria Categor�a que se desea eliminar
    * @throws TrascenderException
    */
   public void deleteCategoria( com.trascender.catastro.recurso.persistent.Categoria pCategoria )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Restaura una categoria a un estado previo el ser eliminada
    * @param pCategoria Categoria que se desea restaurar
    * @throws Exception
    */
   public void restoreCategoria( com.trascender.catastro.recurso.persistent.Categoria pCategoria )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Agrega un coeficiente de depreciaci�n
    * @param pCoeficienteDepreciacion coeficiente de depreciaci�n a agregar
    * @throws TrascenderException
    */
   public com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion addCoeficienteDepreciacion( com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion pCoeficienteDepreciacion )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Actualiza los datos de un coeficiente de depreciaci�n
    * @param pCoeficienteDepreciacion coeficiente de depreciaci�n a actualizar
    * @throws TrascenderException
    */
   public com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion updateCoeficienteDepreciacion( com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion pCoeficienteDepreciacion )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   public Categoria getCategoriaPorId(long pIdCategoria) throws CatastroException;
   
   public TipoConstruccion getTipoConstruccionPorId(long pIdTipoConstruccion) throws CatastroException;
   
   public ValorBasicoMejora getValorBasicoMejoraPorId(long pIdValorBasicoMejora) throws CatastroException;
   
   /**
    * Recupera un coeficiente de depreciaci�n seg�n el a�o y la categor�a
    * @param pAniosAntiguedad A�os de antig�edad del coeficiente de depreciaci�n
    * @param pCategoria categor�a del coeficiente de depreciaci�n
    * @throws TrascenderException
    */
   public com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion findCoeficienteDepreciacion( java.lang.Integer pAniosAntiguedad,com.trascender.catastro.recurso.persistent.Categoria pCategoria )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Recupera un listado de los coeficientes de depreciacion por categoria
    * @param pCategoria categoria de los coeficientes de depreciaci�n
    * @throws TrascenderException
    */
   @SuppressWarnings("unchecked")
   public java.util.List findListaCoeficientesDepreciacion( com.trascender.catastro.recurso.persistent.Categoria pCategoria )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Agrega un valor b�sico por mejora
    * @param pValorBasicoMejora valor b�sico por mejora
    * @throws TrascenderException
    */
   public com.trascender.catastro.recurso.persistent.ValorBasicoMejora addValorBasicoMejora( com.trascender.catastro.recurso.persistent.ValorBasicoMejora pValorBasicoMejora )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Actualiza los datos de un valor b�sico por mejora
    * @param pValorBasicoMejora valor b�sico por mejora a actualizar
    * @throws TrascenderException
    */
   public com.trascender.catastro.recurso.persistent.ValorBasicoMejora updateValorBasicoMejora( com.trascender.catastro.recurso.persistent.ValorBasicoMejora pValorBasicoMejora )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Elimina de la tabla un valor b�sico por mejora
    * @param pValorBasicoMejora Valor b�sico por mejora que se desea eliminar
    * @throws TrascenderException
    */
   public void deleteValorBasicoMejora( com.trascender.catastro.recurso.persistent.ValorBasicoMejora pValorBasicoMejora )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Recupera un valor b�sico por mejora seg�n el a�o y la categor�a
    * @param pAnio a�o del valor b�sico por mejora
    * @param pCategoria categor�a del valor b�sico por mejora
    * @throws TrascenderException
    */
   public com.trascender.catastro.recurso.persistent.ValorBasicoMejora findValorBasicoMejora( java.lang.Integer pAnio,com.trascender.catastro.recurso.persistent.Categoria pCategoria )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Recupera un listado de valores b�sicos por mejoras seg�n la categor�a
    * @param pCategoria categor�a del valor básico por mejora
    * @throws TrascenderException
    */
   @SuppressWarnings("unchecked")
   public FiltroValorBasicoMejora findListaValoresBasicosMejora( FiltroValorBasicoMejora pFiltro)
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   /**
    * Elimina un coeficiente de depreciaci�n
    * @param pCoeficienteDepreciacion coeficiente de depreciaci�n a eliminar
    * @throws TrascenderException
    */
   public void deleteCoeficienteDepreciacion( com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion pCoeficienteDepreciacion )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

   public void setLlave( long pLlave )
      throws java.rmi.RemoteException;

   /**
    * Almacena un listado de coeficientes de depreciaci�n
    * @param pListadoCoeficientesDepreciacion listado de coeficientes de depreciaci�n a agregar
    * @throws TrascenderException
    */
   @SuppressWarnings("unchecked")
   public void saveListadoCoeficientesDepreciacion( java.util.List pListadoCoeficientesDepreciacion )
      throws com.trascender.framework.exception.TrascenderException, java.rmi.RemoteException;

}
