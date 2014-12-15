package com.trascender.catastro.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.catastro.business.interfaces.BusinessCodigosCatastralesLocal;
import com.trascender.catastro.exception.CatastroException;
import com.trascender.catastro.recurso.filtros.FiltroCategoria;
import com.trascender.catastro.recurso.filtros.FiltroTipoConstruccion;
import com.trascender.catastro.recurso.filtros.FiltroValorBasicoMejora;
import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion;
import com.trascender.catastro.recurso.persistent.TipoConstruccion;
import com.trascender.catastro.recurso.persistent.ValorBasicoMejora;
import com.trascender.catastro.system.interfaces.SystemCodigosCatastrales;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;

/**
 * @ejb.bean name="SystemCodigosCatastrales"
 *           display-name="Name for SystemCodigosCatastrales"
 *           description="Description for SystemCodigosCatastrales"
 *           jndi-name="ejb/SystemCodigosCatastrales"
 *           type="Stateless"
 *           view-type="remote"
 */
@Stateful(name="ejb/SystemCodigosCatastrales")
public class SystemCodigosCatastralesBean implements SystemCodigosCatastrales {

	private long llave=0;
	
	@EJB
	private BusinessCodigosCatastralesLocal localBusinessCodigosCatastrales;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5229787542557555710L;

	public SystemCodigosCatastralesBean() {
		super();
	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}

	public void setSessionContext(SessionContext ctx)
		throws EJBException,
		RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {}

	public void ejbActivate() throws EJBException, RemoteException {}

	public void ejbPassivate() throws EJBException, RemoteException {}

	/**
	 * Agrega un tipo de construcci�n.
	 * @param pTipoConstruccion tipo de construcci�n a agregar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.TipoConstruccion addTipoConstruccion(
		com.trascender.catastro.recurso.persistent.TipoConstruccion pTipoConstruccion) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,TipoConstruccion.serialVersionUID,Permiso.Accion.INSERT)){
					return this.localBusinessCodigosCatastrales.addTipoConstruccion(pTipoConstruccion);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(380);
			}
	}
	
	/**
	 * Actualiza los datos de un tipo de construcci�n
	 * @param pTipoConstruccion tipo de construcci�n a actualizar
	 * @throws TrascenderException 
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.TipoConstruccion updateTipoConstruccion(
		com.trascender.catastro.recurso.persistent.TipoConstruccion pTipoConstruccion) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,TipoConstruccion.serialVersionUID,Permiso.Accion.UPDATE)){
					return this.localBusinessCodigosCatastrales.updateTipoConstruccion(pTipoConstruccion);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(381);
			}
	}
	
	/**
	 * Recupera un listado de tipos de construcci�n
	 * @param pIdTipoConstruccion id del tipo de cosntrucci�n
	 * @param pNombre primeras letras del nombre del tipo de construcci�n
	 * @ejb.interface-method  view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public FiltroTipoConstruccion findTiposConstruccion(FiltroTipoConstruccion filtro ) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,TipoConstruccion.serialVersionUID,Permiso.Accion.SELECT)){
					return this.localBusinessCodigosCatastrales.findTiposConstrucciones(filtro);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(382);
			}
	}
	
	/**
	 * Elimina el tipo de construcci�n
	 * @param pTipoConstruccion tipo de construccion que se desea eliminar
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void deleteTipoConstruccion(
		com.trascender.catastro.recurso.persistent.TipoConstruccion pTipoConstruccion) 
		throws TrascenderException{
			try{
				
				if (SecurityMgr.getInstance().getPermiso(this.llave,TipoConstruccion.serialVersionUID,Permiso.Accion.DELETE)){
					pTipoConstruccion.setActivo(false);
					this.localBusinessCodigosCatastrales.updateTipoConstruccion(pTipoConstruccion);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(383);
			}
	}
	
	/**
	 * 
	 * Recupera el estado anterior de un tipo de construcci�n eliminado
	 * @param pTipoConstruccion tipo de construcci�n que se desea recuperar
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.TipoConstruccion restoreTipoConstruccion(
		com.trascender.catastro.recurso.persistent.TipoConstruccion pTipoConstruccion) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,TipoConstruccion.serialVersionUID,Permiso.Accion.UPDATE)){
					pTipoConstruccion.setActivo(true);
					return this.localBusinessCodigosCatastrales.updateTipoConstruccion(pTipoConstruccion);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(384);
			}
	}
	
	/**
	 * Permite agregar una categor�a
	 * @param pCategoria categor�a que se desea agregar
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Categoria addCategoria(
		com.trascender.catastro.recurso.persistent.Categoria pCategoria) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Categoria.serialVersionUID,Permiso.Accion.INSERT)){
					pCategoria.setActivo(true);
					return this.localBusinessCodigosCatastrales.addCategoria(pCategoria);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(385);
			}
	}
	
	/**
	 * Permite actualizar los datos de una categoria
	 * @param pCategoria categoria que se desea actualizar
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Categoria updateCategoria(
		com.trascender.catastro.recurso.persistent.Categoria pCategoria) 
		throws TrascenderException {
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Categoria.serialVersionUID,Permiso.Accion.UPDATE)){
					pCategoria.setActivo(true);
					return this.localBusinessCodigosCatastrales.updateCategoria(pCategoria);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(386);
			}
	}
	
	/**
	 * Recupera una lista de categor�as
	 * @param pNombre nombre de la categor�a
	 * @param pCodigoCategoria c�digo de la categor�a
	 * @param pEstado estado en que se encuentra la categoria
	 * @param pTipoConstruccion tipo de construccion de la categoria
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public FiltroCategoria findListaCategorias(FiltroCategoria filtro) 
		throws TrascenderException {
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Categoria.serialVersionUID,Permiso.Accion.SELECT)){
					return this.localBusinessCodigosCatastrales.findListaCategorias(filtro);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(387);
			}
	}
	
	/**
	 * Elimina una categor�a
	 * @param pCategoria Categor�a que se desea eliminar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void deleteCategoria(
		com.trascender.catastro.recurso.persistent.Categoria pCategoria)
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Categoria.serialVersionUID,Permiso.Accion.DELETE)){
					pCategoria.setActivo(false);
					this.localBusinessCodigosCatastrales.updateCategoria(pCategoria);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(388);
			}
	}
	
	/**
	 * Restaura una categoria a un estado previo el ser eliminada
	 * @param pCategoria Categoria que se desea restaurar
	 * @throws Exception
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void restoreCategoria(
		com.trascender.catastro.recurso.persistent.Categoria pCategoria) 
		throws TrascenderException {
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Categoria.serialVersionUID,Permiso.Accion.UPDATE)){
					pCategoria.setActivo(true);
					this.localBusinessCodigosCatastrales.updateCategoria(pCategoria);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(389);
			}
	}
	
	/**
	 * Agrega un coeficiente de depreciaci�n
	 * @param pCoeficienteDepreciacion coeficiente de depreciaci�n a agregar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion addCoeficienteDepreciacion(
		com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion pCoeficienteDepreciacion) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,CoeficienteDepreciacion.serialVersionUID,Permiso.Accion.INSERT)){
					return this.localBusinessCodigosCatastrales.addCoeficienteDepreciacion(pCoeficienteDepreciacion);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(390);
			}
	}
	
	/**
	 * Actualiza los datos de un coeficiente de depreciaci�n
	 * @param pCoeficienteDepreciacion coeficiente de depreciaci�n a actualizar
	 * @throws TrascenderException 
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion updateCoeficienteDepreciacion(
		com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion pCoeficienteDepreciacion) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,CoeficienteDepreciacion.serialVersionUID,Permiso.Accion.UPDATE)){
					return this.localBusinessCodigosCatastrales.updateCoeficienteDepreciacion(pCoeficienteDepreciacion);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(391);
			}
	}
	
	/**
	 * Recupera un coeficiente de depreciaci�n seg�n el a�o y la categor�a
	 * @param pAniosAntiguedad A�os de antig�edad del coeficiente de depreciaci�n
	 * @param pCategoria categor�a del coeficiente de depreciaci�n 
	 * @throws TrascenderException 
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion findCoeficienteDepreciacion(
		Integer pAniosAntiguedad,
		com.trascender.catastro.recurso.persistent.Categoria pCategoria) 
		throws TrascenderException {
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,CoeficienteDepreciacion.serialVersionUID,Permiso.Accion.SELECT)){
					return this.localBusinessCodigosCatastrales.findCoeficienteDepreciacion(pAniosAntiguedad,pCategoria);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(392);
			}
	}
	
	public Categoria getCategoriaPorId(long pIdCategoria) throws CatastroException {
		try {
			return this.localBusinessCodigosCatastrales.getCategoriaPorId(pIdCategoria);
		} catch (Exception locE) {
			locE.printStackTrace();
			throw new CatastroException(791);
		}
	}
	
	public TipoConstruccion getTipoConstruccionPorId(long pIdTipoConstruccion) throws CatastroException {
		try {
			return this.localBusinessCodigosCatastrales.getTipoConstruccionPorId(pIdTipoConstruccion);
		} catch (Exception locE) {
			locE.printStackTrace();
			throw new CatastroException(791);
		}
	}
	
	public ValorBasicoMejora getValorBasicoMejoraPorId(long pIdValorBasicoMejora) throws CatastroException {
		try {
			return this.localBusinessCodigosCatastrales.getValorBasicoMejoraPorId(pIdValorBasicoMejora);
		} catch (Exception locE) {
			locE.printStackTrace();
			throw new CatastroException(791);
		}
	}
	
	/**
	 * Recupera un listado de los coeficientes de depreciacion por categoria
	 * @param pCategoria categoria de los coeficientes de depreciaci�n
	 * @throws TrascenderException 
	 * @ejb.interface-method  view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findListaCoeficientesDepreciacion(
		com.trascender.catastro.recurso.persistent.Categoria pCategoria) 
		throws TrascenderException {
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave, CoeficienteDepreciacion.serialVersionUID,Permiso.Accion.SELECT)){
					return this.localBusinessCodigosCatastrales.findListaCoeficientesDepreciacion(pCategoria);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(393);
			}
	}
	
	/**
	 * Agrega un valor b�sico por mejora
	 * @param pValorBasicoMejora valor b�sico por mejora
	 * @throws TrascenderException 
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.ValorBasicoMejora addValorBasicoMejora(
		com.trascender.catastro.recurso.persistent.ValorBasicoMejora pValorBasicoMejora) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,ValorBasicoMejora.serialVersionUID,Permiso.Accion.INSERT)){
					return this.localBusinessCodigosCatastrales.addValorBasicoMejora(pValorBasicoMejora);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(394);
			}
	}
	
	/**
	 * Actualiza los datos de un valor b�sico por mejora
	 * @param pValorBasicoMejora valor b�sico por mejora a actualizar
	 * @throws TrascenderException 
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.ValorBasicoMejora updateValorBasicoMejora(
		com.trascender.catastro.recurso.persistent.ValorBasicoMejora pValorBasicoMejora) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,ValorBasicoMejora.serialVersionUID,Permiso.Accion.UPDATE)){
					return this.localBusinessCodigosCatastrales.updateValorBasicoMejora(pValorBasicoMejora);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(395);	
			}
	}
	
	/**
	 * Elimina de la tabla un valor b�sico por mejora
	 * @param pValorBasicoMejora Valor b�sico por mejora que se desea eliminar
	 * @throws TrascenderException 
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void deleteValorBasicoMejora(
		com.trascender.catastro.recurso.persistent.ValorBasicoMejora pValorBasicoMejora) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,ValorBasicoMejora.serialVersionUID,Permiso.Accion.DELETE)){
					this.localBusinessCodigosCatastrales.deleteValorBasicoMejora(pValorBasicoMejora);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(396);
			}
	}
	
	/**
	 * Recupera un valor b�sico por mejora seg�n el a�o y la categor�a
	 * @param pAnio a�o del valor b�sico por mejora
	 * @param pCategoria categor�a del valor b�sico por mejora
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.ValorBasicoMejora findValorBasicoMejora(
		Integer pAnio,
		com.trascender.catastro.recurso.persistent.Categoria pCategoria) 
		throws TrascenderException{
			try{
				if(SecurityMgr.getInstance().getPermiso(this.llave,ValorBasicoMejora.serialVersionUID,Permiso.Accion.SELECT)){
					return this.localBusinessCodigosCatastrales.findValorBasicoMejora(pAnio,pCategoria);
				}
				else{
					throw new CatastroException(791);
				}
				
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(397);
			}
	}
	
	/**
	 * Recupera un listado de valores b�sicos por mejoras seg�n la categor�a
	 * @param pCategoria categor�a del valor básico por mejora
	 * @throws TrascenderException 
	 * @ejb.interface-method  view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public FiltroValorBasicoMejora findListaValoresBasicosMejora(FiltroValorBasicoMejora filtro) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,ValorBasicoMejora.serialVersionUID,Permiso.Accion.SELECT)){
					return this.localBusinessCodigosCatastrales.findListaValoresBasicosMejora(filtro);			
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(398);
			}
	}
	
	/**
	 * Elimina un coeficiente de depreciaci�n 
	 * @param pCoeficienteDepreciacion coeficiente de depreciaci�n a eliminar
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteCoeficienteDepreciacion(com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion pCoeficienteDepreciacion) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,CoeficienteDepreciacion.serialVersionUID,Permiso.Accion.DELETE)){
					this.localBusinessCodigosCatastrales.deleteCoeficienteDepreciacion(pCoeficienteDepreciacion);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(399);
			}
	}
	
	/**
	 * 
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave){
		this.llave=pLlave;
	}
	
	/**
	 * Almacena un listado de coeficientes de depreciaci�n
	 * @param pListadoCoeficientesDepreciacion listado de coeficientes de depreciaci�n a agregar
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote" 
	 */
	@SuppressWarnings("unchecked")
	public void saveListadoCoeficientesDepreciacion(java.util.List pListadoCoeficientesDepreciacion) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,CoeficienteDepreciacion.serialVersionUID,Permiso.Accion.INSERT))  {
					if (SecurityMgr.getInstance().getPermiso(this.llave,CoeficienteDepreciacion.serialVersionUID,Permiso.Accion.UPDATE)){
						 this.localBusinessCodigosCatastrales.saveListadoCoeficienteDepreciacion(pListadoCoeficientesDepreciacion);
						 return;
					}
				}
				throw new CatastroException(791);
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(400);
			}
	}
}
