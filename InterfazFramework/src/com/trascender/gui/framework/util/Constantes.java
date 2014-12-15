package com.trascender.gui.framework.util;

import java.awt.Color;
import java.awt.Font;

import com.trascender.gui.framework.recursos.Messages;

public class Constantes {
	
	public static final String SUPER_USUARIO = "root";
	
	public static final Color COLOR_ADMIN     = new Color(194,204,231);
	public static final Color COLOR_AGREGAR   = new Color(200,226,200);
	public static final Color COLOR_MODIFICAR = new Color(226,226,200);
	public static final Color COLOR_ELIMINAR  = new Color(226,200,200);
	public static final Color COLOR_CONSULTAR = new Color(226,226,226);
	public static final Color COLOR_ERROR     = new Color(250,160,160);
	
	public static final Color COLOR_REPORTE = new Color(233,233,139);
	
	public static final Font FUENTE_TITULO = new Font(
			Messages.getString("Application.tituloVentanaFontName"),
			Integer.valueOf(Messages.getString("Application.tituloVentanaFontStyle")),
			Integer.valueOf(Messages.getString("Application.tituloVentanaFontSize")));

}
