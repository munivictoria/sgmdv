package com.trascender.gui.framework.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.trascender.gui.framework.exception.GuiException;

public class Configuracion implements Serializable {
	
	private static final long serialVersionUID = -6371637983875370375L;
	private static final String FILE_NAME = "contabilidadGUI.conf";
	
	// ATRIBUTOS A GUARDAR
	private String superUserName = "root";
	private int ventanasAdminAncho = 800;
	private int ventanasAdminAlto  = 600;
	
	public Configuracion() {
		
	}
	
	public static Configuracion loadDefault() {
		return new Configuracion();
	}
	
	/**
	 * Carga el archivo del disco
	 * @throws Exception
	 */
	public static Configuracion load() throws Exception {
		try {
			Configuracion locConfiguracion = null;
			ObjectInput locIn = new ObjectInputStream(new FileInputStream(FILE_NAME));
			locConfiguracion = (Configuracion)locIn.readObject();
			
			return locConfiguracion;
		} 
		catch (FileNotFoundException e) {
			throw new GuiException(0);
		}
		catch (IOException e) {
			throw new GuiException(1);
		}
	}
	
	/**
	 * Almacena el archivo en el disco
	 * @throws Exception
	 */
	public void save() throws Exception {
		try {
			ObjectOutput locOut = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
			locOut.writeObject(this);
			locOut.close();
		}
		catch (FileNotFoundException e) {
			throw new GuiException(0);
		}
		catch (IOException e) {
			throw new GuiException(1);
		}
	}
	
	
	@Override
	public void finalize() throws Exception {
		this.save();
	}

	public String getSuperUserName() {
		return this.superUserName;
	}

	public int getVentanasAdminAlto() {
		return ventanasAdminAlto;
	}

	public int getVentanasAdminAncho() {
		return ventanasAdminAncho;
	}

}
