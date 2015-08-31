/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.panels;

import java.util.List;

import javax.faces.component.html.HtmlPanelGrid;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.trascender.expedientes.recurso.persistent.Responsable;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Usuario;

public class PanelResponsableExpediente extends PanelGroup {

	private HtmlPanelGrid panelAreas;
	private HtmlPanelGrid panelUsuarios;
	private HtmlPanelGrid panel;
	private Responsable responsable;
	private boolean reRender = false;

	public boolean isReRender() {
		return reRender;
	}

	public void setReRender(boolean reRender) {
		this.reRender = reRender;
	}

	public PanelResponsableExpediente() {

	}

	public PanelResponsableExpediente(Responsable responsable) {
		if(responsable != null) {
			this.responsable = responsable;
			armarPanel();
		}
	}

	private HtmlPanelGrid inicializaPanelGrid() {
		HtmlPanelGrid pg = new HtmlPanelGrid();
		pg.setColumns(1);
		pg.setCellpadding("0");
		pg.setCellspacing("0");

		return pg;
	}

	private Label inicializaLabel(String pText) {
		Label l = new Label();
		l.setText(pText);
		l.setStyleClass("label");
		l.setStyle("font-weight:bold");
		
		return l;
	}

	private void armarPanel() {
		armarPanelAreas(responsable.getAreas());
		armarPanelUsuarios(responsable.getUsuarios());
		panel = new HtmlPanelGrid();
		panel.setColumns(2);
		panel.getChildren().add(panelAreas);
		panel.getChildren().add(panelUsuarios);
		getChildren().add(panel);
	}

	private void armarPanelUsuarios(List<Usuario> list) {
		this.panelUsuarios = inicializaPanelGrid();
		for(Usuario u : list) {
			this.panelUsuarios.getChildren().add(inicializaLabel(u.getUser()));
		}

	}

	private void armarPanelAreas(List<Area> list) {
		this.panelAreas = inicializaPanelGrid();
		for(Area a : list) {
			this.panelAreas.getChildren().add(inicializaLabel(a.getNombre()));
		}
	}
	
}