
package muni.expedientes.ABMProcedimiento;

import muni.expedientes.panels.PanelPlazo;
import muni.expedientes.panels.PanelResponsableNodo;

import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.expedientes.recurso.persistent.DocumentoProcedimiento;
import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.expedientes.recurso.persistent.NodoProcedimiento;
import com.trascender.expedientes.recurso.persistent.PlazoProcedimiento;
import com.trascender.expedientes.recurso.persistent.Responsable;
import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;

public class PanelEditNodo {

	private Label lTitulo = new Label();
	private TextField tNombre = new TextField();
	public PanelResponsableNodo panelResponsable = new PanelResponsableNodo();
	private PanelPlazo panelPlazo = new PanelPlazo();
	public PlazoProcedimiento plazo;
	public Responsable responsable;
	private String nombre = "";
	private boolean renderedNodo = false;
	private PanelGroup gpAreas1 = new PanelGroup();
	private PanelGroup gpUsuarios1 = new PanelGroup();
	private PanelGroup gpUsuariosExtensores = new PanelGroup();
	private TableColumn tcAreas1 = new TableColumn();
	private DropDown ddResponsabilidadArea = new DropDown();
	private DropDown ddResponsabilidadUsuario = new DropDown();

	public PanelGroup getGpUsuariosExtensores() {
		return gpUsuariosExtensores;
	}

	public void setGpUsuariosExtensores(PanelGroup gpUsuariosExtensores) {
		this.gpUsuariosExtensores = gpUsuariosExtensores;
	}

	public PanelGroup getGpAreas1() {
		return gpAreas1;
	}

	public void setGpAreas1(PanelGroup gpAreas1) {
		this.gpAreas1 = gpAreas1;
	}

	public PanelGroup getGpUsuarios1() {
		return gpUsuarios1;
	}

	public void setGpUsuarios1(PanelGroup gpUsuarios1) {
		this.gpUsuarios1 = gpUsuarios1;
	}

	public TableColumn getTcAreas1() {
		return tcAreas1;
	}

	public void setTcAreas1(TableColumn tcAreas1) {
		this.tcAreas1 = tcAreas1;
	}

	public DropDown getDdResponsabilidadArea() {
		return ddResponsabilidadArea;
	}

	public void setDdResponsabilidadArea(DropDown ddResponsabilidadArea) {
		this.ddResponsabilidadArea = ddResponsabilidadArea;
	}

	public DropDown getDdResponsabilidadUsuario() {
		return ddResponsabilidadUsuario;
	}

	public void setDdResponsabilidadUsuario(DropDown ddResponsabilidadUsuario) {
		this.ddResponsabilidadUsuario = ddResponsabilidadUsuario;
	}

	public boolean isRenderedNodo() {
		return renderedNodo;
	}

	public void setRenderedNodo(boolean renderedNodo) {
		this.renderedNodo = renderedNodo;
	}

	public PanelResponsableNodo getPanelResponsable() {
		return panelResponsable;
	}

	public Label getlTitulo() {
		return lTitulo;
	}

	public void setlTitulo(Label lTitulo) {
		this.lTitulo = lTitulo;
	}

	public void setPanelResponsable(PanelResponsableNodo panelResponsable) {
		this.panelResponsable = panelResponsable;
	}

	public TextField gettNombre() {
		return tNombre;
	}

	public void settNombre(TextField tNombre) {
		this.tNombre = tNombre;
	}

	public PanelPlazo getPanelPlazo() {
		return panelPlazo;
	}

	public void setPanelPlazo(PanelPlazo panelPlazo) {
		this.panelPlazo = panelPlazo;
	}

	public void _init() {
		panelResponsable._initTables();

	}

	public void guardarEstado(NodoProcedimiento pObject) {
		getValoresInstancia(pObject);
		panelResponsable.guardarDatos(responsable);
		pObject.setPlazo(panelPlazo.guardarDatos(plazo));
	}

	public void mostrarEstado(Object pObject) {
		if(pObject != null) {
			if(!(pObject instanceof DocumentoProcedimiento)) {
				renderedNodo = true;
				getValoresInstancia(pObject);
				panelPlazo.mostrarDatos(plazo);
				panelResponsable.mostrarDatos(responsable);
				tNombre.setText(nombre);
			} else {
				System.out.println("es un documento...");
			}
		} else {
			renderedNodo = false;
		}
	}

	private void getValoresInstancia(Object pObject) {
		// responsable = new Responsable();
		// plazo = new Plazo();
		if(pObject instanceof FaseProcedimiento) {
			lTitulo.setText("Fase");
			FaseProcedimiento locFase = (FaseProcedimiento) pObject;
			responsable = locFase.getResponsable();
			plazo = locFase.getPlazo();
			nombre = locFase.getFaseCatalogo().getNombre();
		}
		if(pObject instanceof TramiteProcedimiento) {
			lTitulo.setText("Tr\341mite");
			TramiteProcedimiento locTramite = (TramiteProcedimiento) pObject;
			responsable = locTramite.getResponsable();
			plazo = locTramite.getPlazo();
			nombre = locTramite.getTramiteCatalogo().getNombre();
		}
	}

	public class NodoPila {
		// public String nombre;
		public NodoProcedimiento object;
		public Responsable responsable = new Responsable();
		public PlazoProcedimiento plazo = new PlazoProcedimiento();

	}

}