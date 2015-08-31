/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.tables;

import java.util.List;

import com.sun.rave.web.ui.component.StaticText;
import com.trascender.expedientes.recurso.persistent.AreaResponsable;
import com.trascender.framework.recurso.persistent.Area;

public abstract class TableAreas extends TableBean {

	StaticText staticTextNombre = new StaticText();

	public StaticText getStaticTextNombre() {
		return staticTextNombre;
	}

	public void setStaticTextNombre(StaticText staticTextNombre) {
		this.staticTextNombre = staticTextNombre;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addToList(List pList, Object pObject) {
		Area nuevaArea = (Area) pObject;
		List locAreas = pList;

		AreaResponsable deLaTabla = null;
		boolean esta = false;
		int i = 0;
		while(i < locAreas.size() && !esta) {
			deLaTabla = (AreaResponsable) locAreas.get(i++);
			esta = (deLaTabla.getArea().getIdArea() == nuevaArea.getIdArea());
		}
		if(!esta) {
			AreaResponsable nuevaAreaResponsable = new AreaResponsable();
			nuevaAreaResponsable.setArea(nuevaArea);
			locAreas.add(nuevaAreaResponsable);
		} else {
			warn("El Area que intenta agregar ya se encuentra en la lista.");
		}
	}

}