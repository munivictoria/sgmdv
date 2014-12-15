package com.trascender.gui.framework.main;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.trascender.gui.framework.recursos.RecursosInterface;

public class AdministracionRecursos {
		
	/**
	 * @param pRecurso
	 * @return Icon del Recurso especificado
	 */
	public Icon getIcon(RecursosInterface pRecurso) {
		try{
			String clave = pRecurso.getUriRecurso();
			
			URL locUrlArchivo = this.getClass().getClassLoader().getResource(clave);
			if (locUrlArchivo != null){
				Icon retorno = new ImageIcon(locUrlArchivo);
				return retorno;
			}
			else {
				return null;
			}
		}
		catch(Exception e) {
			return null;
		}     
	}
	
	
	public Image getImage(RecursosInterface pRecurso) {
		try {
			URL locUrlArchivo = this.getClass().getClassLoader().getResource(pRecurso.getUriRecurso());
			if (locUrlArchivo != null){
				Image retorno = Toolkit.getDefaultToolkit().createImage(locUrlArchivo); 
				return retorno;
			}
			else {
				return null;
			}
		}
		catch(Exception e) {
			return null;
		}
	}
	
}
