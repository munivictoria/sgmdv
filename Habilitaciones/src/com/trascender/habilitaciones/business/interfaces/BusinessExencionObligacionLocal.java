package com.trascender.habilitaciones.business.interfaces;

import javax.ejb.Local;

import com.trascender.habilitaciones.recurso.filtros.FiltroExencionObligacion;
import com.trascender.habilitaciones.recurso.persistent.ExencionObligacion;

/**
 * 
 * @author Ignacio Sarnaglia, Maximiliano Fontanini
 *
 */
@Local
public interface BusinessExencionObligacionLocal {
	
	public final static String JNDI_NAME = "ejb/BusinessExencionObligacionLocal/local";

	public ExencionObligacion addExencionObligacion (ExencionObligacion pExencionObligacion) throws Exception;
	
	public ExencionObligacion updateExencionObligacion (ExencionObligacion pExencionObligacion) throws Exception;
	
	public ExencionObligacion getExencionObligacionByID(Long pId) throws Exception;
	
	public void deleteExencionObligacion(ExencionObligacion pExencionObligacion) throws Exception;
	
	public FiltroExencionObligacion findListaExencionesObligacion(FiltroExencionObligacion pFiltro) throws Exception;
	
}
