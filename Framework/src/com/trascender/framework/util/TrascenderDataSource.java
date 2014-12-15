package com.trascender.framework.util;
import java.awt.Image;
import java.util.Map;

import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JRDataSource;


public abstract class TrascenderDataSource implements JRDataSource{

	public abstract Map<String, Object> getMapaParametros();

	public abstract String getNombreReporte();
	
	protected String getTituloReporte() {
		return SecurityMgr.getInstance().getMunicipalidad().getEncabezadoReportes(); 
	}
	
	protected String getSubtituloReporte() {
		return SecurityMgr.getInstance().getMunicipalidad().getSubencabezadoReportes();
	}

	
	protected Image getLogoMunicipalidad() {
		return new ImageIcon(SecurityMgr.getInstance().getMunicipalidad().getLogo()).getImage();
	}
}
