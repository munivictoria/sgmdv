
package muni.expedientes.ABMResponsable;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.expedientes.recurso.persistent.Responsable;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMResponsable extends ABMPageBean {

	TextField tfNombre = new TextField();
	Label lblNombre = new Label();

	// Areas
	private Label tituloArea = new Label();
	private Table tableArea = new Table();
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private RadioButton radioButton1 = new RadioButton();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tableColumn2 = new TableColumn();
	private StaticText staticText1 = new StaticText();
	private StaticText staticText4 = new StaticText();
	private StaticText staticText5 = new StaticText();
	private StaticText staticText6 = new StaticText();
	private PanelGroup groupPanel1 = new PanelGroup();
	private PanelGroup groupPanel2 = new PanelGroup();
	private Object lastSelected = null;
	private Button btnAgregarArea = new Button();
	private HtmlAjaxCommandButton btnQuitarArea = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnQuitarTodosArea = new HtmlAjaxCommandButton();
	private ObjectListDataProvider objectListDataProviderArea = new ObjectListDataProvider();

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunicationArea() != null) {
			this.getObjectListDataProviderArea().setList(this.getListaDelCommunicationArea());
		}

	}

	public Label getTituloArea() {
		return tituloArea;
	}

	public void setTituloArea(Label tituloArea) {
		this.tituloArea = tituloArea;
	}

	public Table getTableArea() {
		return tableArea;
	}

	public void setTableArea(Table tableArea) {
		this.tableArea = tableArea;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText staticText4) {
		this.staticText4 = staticText4;
	}

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText staticText5) {
		this.staticText5 = staticText5;
	}

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText staticText6) {
		this.staticText6 = staticText6;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public PanelGroup getGroupPanel2() {
		return groupPanel2;
	}

	public void setGroupPanel2(PanelGroup groupPanel2) {
		this.groupPanel2 = groupPanel2;
	}

	public Object getLastSelected() {
		return lastSelected;
	}

	public void setLastSelected(Object lastSelected) {
		this.lastSelected = lastSelected;
	}

	public Button getBtnAgregarArea() {
		return btnAgregarArea;
	}

	public void setBtnAgregarArea(Button btnAgregarArea) {
		this.btnAgregarArea = btnAgregarArea;
	}

	public HtmlAjaxCommandButton getBtnQuitarArea() {
		return btnQuitarArea;
	}

	public void setBtnQuitarArea(HtmlAjaxCommandButton btnQuitarArea) {
		this.btnQuitarArea = btnQuitarArea;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodosArea() {
		return btnQuitarTodosArea;
	}

	public void setBtnQuitarTodosArea(HtmlAjaxCommandButton btnQuitarTodosArea) {
		this.btnQuitarTodosArea = btnQuitarTodosArea;
	}

	public ObjectListDataProvider getObjectListDataProviderArea() {
		return objectListDataProviderArea;
	}

	public void setObjectListDataProviderArea(ObjectListDataProvider objectListDataProviderArea) {
		this.objectListDataProviderArea = objectListDataProviderArea;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		Responsable locResponsable = obtenerObjetoDelElementoPila(ind++, Responsable.class);
		ArrayList locListaAreas = (ArrayList) obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		this.getObjectListDataProviderArea().commitChanges();
		locListaAreas = (ArrayList) this.getObjectListDataProviderArea().getList();
		this.setListaDelCommunicationArea(locListaAreas);
		locResponsable.setAreas(locListaAreas);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, locResponsable);
		this.getElementoPila().getObjetos().set(ind++, locListaAreas);
	}

	@SuppressWarnings({"rawtypes"})
	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		@SuppressWarnings("unused")
		Responsable locResponsable = (Responsable) this.obtenerObjetoDelElementoPila(ind++, Responsable.class);
		ArrayList locListaAreas = (ArrayList) obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		this.getObjectListDataProviderArea().setList(locListaAreas);
		this.setListaDelCommunicationArea(locListaAreas);

	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Responsable());
		ep.getObjetos().add(ind++, new ArrayList());
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof Area) {
			Area nuevaArea = (Area) pObject;
			ArrayList locListaAreas = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);

			Area deLaTabla = null;
			boolean esta = false;
			int i = 0;
			while(i < locListaAreas.size() && !esta) {
				deLaTabla = (Area) locListaAreas.get(i++);
				esta = (deLaTabla.getIdArea() == nuevaArea.getIdArea());
			}
			if(!esta) {
				locListaAreas.add(nuevaArea);
			} else {
				warn("La Area que intenta agregar ya se encuentra en la lista.");
			}
			this.getElementoPila().getObjetos().set(1, locListaAreas);

			this.getRequestBean1().setObjetoSeleccion(null);
		}

	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	protected void procesarObjetoABM(Object pObject) {
		Responsable locResponsable = (Responsable) pObject;
		ArrayList locListaAreas = null;
		try {
			long idResponsable = locResponsable.getIdResponsable();
			this.getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(this.getSessionBean1().getLlave());
			locResponsable = this.getCommunicationExpedientesBean().getRemoteSystemProcedimientos().getResponsablePorId(idResponsable);
		} catch(Exception ex) {
			error("No se pudo recuperar Responsable: " + ex.getMessage());
		}

		locListaAreas = new ArrayList(locResponsable.getAreas());

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, locResponsable);
		this.getElementoPila().getObjetos().set(ind++, locListaAreas);
	}

	@Override
	protected String getNombrePagina() {
		return "Responsable";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMResponsable";
	}

	@SuppressWarnings("rawtypes")
	private List getListaDelCommunicationArea() {
		return this.getCommunicationExpedientesBean().getListaAreasResponsables();
	}

	@SuppressWarnings("rawtypes")
	private void setListaDelCommunicationArea(ArrayList lista) {
		this.getCommunicationExpedientesBean().setListaAreasResponsables(lista);
	}

	public String btnAgregarArea_action() {
		return navegarParaSeleccionar("AdminArea");
	}

	public String btnQuitarArea_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderArea().getObjects()[index];
					this.getListaDelCommunicationArea().remove(obj);
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarTodosArea_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				this.getListaDelCommunicationArea().clear();
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	private RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProviderArea().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	public Object getRBSelected() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if(selected != null) {
			lastSelected = selected;
		}
	}

	@Override
	public String getNombreBean() {
		return "#{expedientes$ABMResponsable$ABMResponsable}";
	}

	@Override
	public long getSerialVersionUID() {
		return Responsable.serialVersionUID;
	}
}