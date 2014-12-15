package muni.framework.ABMSecretaria;

import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author nico
 * 
 */
public class ABMSecretaria extends ABMPageBean {

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}
	
	private PanelGroup groupPanel = new PanelGroup();

	public PanelGroup getGroupPanel() {
		return groupPanel;
	}

	public void setGroupPanel(PanelGroup groupPanel) {
		this.groupPanel = groupPanel;
	}

	private Label lbNombre = new Label();
	private Label lbArea = new Label();

	public Label getLbNombre() {
		return lbNombre;
	}

	public void setLbNombre(Label lbNombre) {
		this.lbNombre = lbNombre;
	}

	public Label getLbArea() {
		return lbArea;
	}

	public void setLbArea(Label lbArea) {
		this.lbArea = lbArea;
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}
	
	private Label lbCodigoImputacion = new Label();
	
	public Label getLbCodigoImputacion() {
		return lbCodigoImputacion;
	}

	public void setLbCodigoImputacion(Label lbCodigoImputacion) {
		this.lbCodigoImputacion = lbCodigoImputacion;
	}
	
	private TextField tfCodigoImputacion = new TextField();
	
	public TextField getTfCodigoImputacion() {
		return tfCodigoImputacion;
	}

	public void setTfCodigoImputacion(TextField tfCodigoImputacion) {
		this.tfCodigoImputacion = tfCodigoImputacion;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup messageGroup1) {
		this.messageGroup1 = messageGroup1;
	}

	private Table tablaArea = new Table();

	public Table getTablaArea() {
		return tablaArea;
	}

	public void setTablaArea(Table tablaArea) {
		this.tablaArea = tablaArea;
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	private ObjectListDataProvider ldpAreas = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpAreas() {
		return ldpAreas;
	}

	public void setLdpAreas(ObjectListDataProvider ldpAreas) {
		this.ldpAreas = ldpAreas;
	}

	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpAreas();
	}

	private List getListaDelCommunication() {
		return this.getComunicationBean().getListaAreasSecretaria();
	}

	private void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaAreasSecretaria(lista);
	}

	@Override
	protected void _init() throws Exception {
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		Secretaria secretaria = (Secretaria) this.obtenerObjetoDelElementoPila(0, Secretaria.class);

		secretaria.setNombre(this.getTextFieldValue(getTfNombre()));
		secretaria.setCodigoImputacion(this.getTextFieldValue(getTfCodigoImputacion()));

		this.getElementoPila().getObjetos().set(0, secretaria);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		Secretaria secretaria = (Secretaria) this.obtenerObjetoDelElementoPila(0, Secretaria.class);

		this.getTfNombre().setText(secretaria.getNombre());
		this.getTfCodigoImputacion().setText(secretaria.getCodigoImputacion());

		this.getObjectListDataProvider().setList(secretaria.getListaAreas());
		this.setListaDelCommunication(new ArrayList(secretaria.getListaAreas()));
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMSecretaria";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Secretaria());

		return ep;
	}

	public String btnAgregarArea_action() {
		return navegarParaSeleccionar("AdminArea");
	}

	public String btnQuitarArea_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		return retorno;
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getLdpAreas().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Secretaria secretaria = (Secretaria) pObject;
		List listaArea = new ArrayList();
		try {
			this.setListaDelCommunication(secretaria.getListaAreas());
			this.getLdpAreas().setList(this.getListaDelCommunication());
			listaArea = this.getLdpAreas().getList();
		} catch (Exception ex) {
			error("No se pudieron obtener las Áreas de la Secretaria: " + ex.getMessage());
		}
		this.getElementoPila().getObjetos().set(0, secretaria);
	}
	
	@Override
	public long getSerialVersionUID() {
		return Secretaria.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMSecretaria$ABMSecretaria}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Secretaria locSecretaria = this.obtenerObjetoDelElementoPila(0, Secretaria.class);
		this.getTablaLogs().getLdpLogs().setList(locSecretaria.getListaLogsAuditoria());
	}
}