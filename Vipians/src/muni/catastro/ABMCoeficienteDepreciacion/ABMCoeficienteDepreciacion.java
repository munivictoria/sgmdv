/*
 * ABMCoeficienteDepreciacion.java
 *
 * Created on 27 de octubre de 2006, 10:43
 * Copyright Trascender SRL
 */
package muni.catastro.ABMCoeficienteDepreciacion;

import java.util.ArrayList;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMCoeficienteDepreciacion extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private Table table1 = new Table();

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private TextField tfCategoria = new TextField();

	public TextField getTfCategoria() {
		return tfCategoria;
	}

	public void setTfCategoria(TextField tf) {
		this.tfCategoria = tf;
	}

	private Button btnSeleccionarCategoria = new Button();

	public Button getBtnSeleccionarCategoria() {
		return btnSeleccionarCategoria;
	}

	public void setBtnSeleccionarCategoria(Button b) {
		this.btnSeleccionarCategoria = b;
	}

	private HtmlAjaxCommandButton btnLimpiarCategoria = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarCategoria() {
		return btnLimpiarCategoria;
	}

	public void setBtnLimpiarCategoria(HtmlAjaxCommandButton btnLimpiarCategoria) {
		this.btnLimpiarCategoria = btnLimpiarCategoria;
	}

	private ObjectListDataProvider ldpCoeficientesDepreciacionPorCategoria = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpCoeficientesDepreciacionPorCategoria() {
		return ldpCoeficientesDepreciacionPorCategoria;
	}

	public void setLdpCoeficientesDepreciacionPorCategoria(ObjectListDataProvider oldp) {
		this.ldpCoeficientesDepreciacionPorCategoria = oldp;
	}

	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	private TextField textField1 = new TextField();

	public TextField getTextField1() {
		return textField1;
	}

	public void setTextField1(TextField tf) {
		this.textField1 = tf;
	}

	private TableColumn tableColumn3 = new TableColumn();

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tc) {
		this.tableColumn3 = tc;
	}

	private TextField textField2 = new TextField();

	public TextField getTextField2() {
		return textField2;
	}

	public void setTextField2(TextField tf) {
		this.textField2 = tf;
	}

	private TableColumn tableColumn4 = new TableColumn();

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tc) {
		this.tableColumn4 = tc;
	}

	private TextField textField3 = new TextField();

	public TextField getTextField3() {
		return textField3;
	}

	public void setTextField3(TextField tf) {
		this.textField3 = tf;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMCoeficienteDepreciacion() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Categoria());
		ep.getObjetos().add(ind++, new ArrayList());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		Categoria categoria = (Categoria) this.obtenerObjetoDelElementoPila(0, Categoria.class);
		ArrayList coeficientes = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);

		this.getObjectListDataProvider().commitChanges();
		coeficientes = (ArrayList) this.getObjectListDataProvider().getList();
		this.setListaDelCommunication(coeficientes);

		this.getElementoPila().getObjetos().set(0, categoria);
		this.getElementoPila().getObjetos().set(1, coeficientes);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		Categoria categoria = (Categoria) this.obtenerObjetoDelElementoPila(0, Categoria.class);
		ArrayList coeficientes = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);

		this.getTfCategoria().setText(categoria.toString());

		this.getObjectListDataProvider().setList(coeficientes);
		this.setListaDelCommunication(coeficientes);
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpCoeficientesDepreciacionPorCategoria();
	}

	private ArrayList getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaCoeficientesDepreciacionPorCategoria();
	}

	private void setListaDelCommunication(ArrayList lista) {
		this.getComunicationCatastroBean().setListaCoeficientesDepreciacionPorCategoria(lista);
	}

	public String btnSeleccionarCategoria_action() {
		return navegarParaSeleccionar("AdminCategoria", 1);
	}

	public String btnLimpiarCategoria_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(0, this.getTfCategoria());
			getElementoPila().getObjetos().set(1, null);
			this.getObjectListDataProvider().getList().clear();
			this.getListaDelCommunication().clear();
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMCoeficienteDepreciacion";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof Categoria) {
			ArrayList coeficientes = null;
			Categoria categoria = (Categoria) pObject;

			try {
				this.getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(this.getSessionBean1().getLlave());
				coeficientes = (ArrayList) this.getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().findListaCoeficientesDepreciacion(categoria);
			} catch (Exception ex) {
				error("No se pudieron obtener los Coeficientes de Depreciaci\363n de la Categor\355a: " + ex.getMessage());
			}

			this.getElementoPila().getObjetos().set(0, categoria);
			this.getElementoPila().getObjetos().set(1, coeficientes);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Categoria categoria = (Categoria) pObject;
		ArrayList coeficientes = null;

		try {
			this.getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(this.getSessionBean1().getLlave());
			coeficientes = (ArrayList) this.getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().findListaCoeficientesDepreciacion(categoria);
		} catch (Exception ex) {
			error("No se pudieron obtener los Coeficientes de Depreciaci\363n de la Categor\355a: " + ex.getMessage());
		}

		this.getElementoPila().getObjetos().set(0, categoria);
		this.getElementoPila().getObjetos().set(1, coeficientes);
	}
	
	@Override
	public long getSerialVersionUID() {
		return CoeficienteDepreciacion.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Categoria locCategoria = this.obtenerObjetoDelElementoPila(0, Categoria.class);
		this.getTablaLogs().getLdpLogs().setList(locCategoria.getListaLogsAuditoria());
	}
}