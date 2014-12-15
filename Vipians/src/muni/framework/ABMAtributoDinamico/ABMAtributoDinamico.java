/*
 * ABMAtributoDinamico.java
 *
 * Created on 31 de agosto de 2006, 11:22
 * Copyright Trascender SRL
 */
package muni.framework.ABMAtributoDinamico;

import java.util.List;

import javax.faces.event.ValueChangeEvent;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.persistent.dinamicos.OpcionAtributoDinamicoListado;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico.Tipo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;
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
public class ABMAtributoDinamico extends ABMPageBean {

	@Override
	protected void _init() throws Exception {

		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		// Tipo
		Option[] opTipo = null;
		opTipo = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(PlantillaAtributoDinamico.Tipo.values(), "may");
		ddTipoDefaultOptions.setOptions(opTipo);
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	// ----------------------------Button---------------------------------------
	private Button btnSeleccionarRecurso = new Button();
	protected HtmlAjaxCommandButton btnAgregarDato = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarDato = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLimpiarRecurso = new HtmlAjaxCommandButton();
	
	public HtmlAjaxCommandButton getBtnLimpiarRecurso() {
		return btnLimpiarRecurso;
	}

	public void setBtnLimpiarRecurso(HtmlAjaxCommandButton btnLimpiarRecurso) {
		this.btnLimpiarRecurso = btnLimpiarRecurso;
	}

	public Button getBtnSeleccionarRecurso() {
		return btnSeleccionarRecurso;
	}

	public void setBtnSeleccionarRecurso(Button btnSeleccionarRecurso) {
		this.btnSeleccionarRecurso = btnSeleccionarRecurso;
	}

	public HtmlAjaxCommandButton getBtnAgregarDato() {
		return btnAgregarDato;
	}

	public void setBtnAgregarDato(HtmlAjaxCommandButton btnAgregarDato) {
		this.btnAgregarDato = btnAgregarDato;
	}

	public HtmlAjaxCommandButton getBtnQuitarDato() {
		return btnQuitarDato;
	}

	public void setBtnQuitarDato(HtmlAjaxCommandButton btnQuitarDato) {
		this.btnQuitarDato = btnQuitarDato;
	}

	// ----------------------------Checkbox-------------------------------------
	private Checkbox cbRequerido = new Checkbox();

	public Checkbox getCbRequerido() {
		return cbRequerido;
	}

	public void setCbRequerido(Checkbox cbRequerido) {
		this.cbRequerido = cbRequerido;
	}

	private Checkbox cbBusqueda = new Checkbox();

	public Checkbox getCbBusqueda() {
		return cbBusqueda;
	}

	public void setCbBusqueda(Checkbox cbBusqueda) {
		this.cbBusqueda = cbBusqueda;
	}

	// ----------------------------Dropdown-------------------------------------
	private DropDown ddTipo = new DropDown();
	private SingleSelectOptionsList ddTipoDefaultOptions = new SingleSelectOptionsList();

	public DropDown getDdTipo() {
		return ddTipo;
	}

	public void setDdTipo(DropDown ddTipo) {
		this.ddTipo = ddTipo;
	}

	public SingleSelectOptionsList getDdTipoDefaultOptions() {
		return ddTipoDefaultOptions;
	}

	public void setDdTipoDefaultOptions(SingleSelectOptionsList ddTipoDefaultOptions) {
		this.ddTipoDefaultOptions = ddTipoDefaultOptions;
	}

	// ----------------------------Label----------------------------------------
	private Label label1 = new Label();
	private Label label2 = new Label();
	private Label label3 = new Label();
	private Label label4 = new Label();
	private Label label5 = new Label();
	private Label label6 = new Label();
	private Label lblOpcionesListado = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label label1) {
		this.label1 = label1;
	}

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label label2) {
		this.label2 = label2;
	}

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label label3) {
		this.label3 = label3;
	}

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label label4) {
		this.label4 = label4;
	}

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label label5) {
		this.label5 = label5;
	}

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label label6) {
		this.label6 = label6;
	}

	public Label getLblOpcionesListado() {
		return lblOpcionesListado;
	}

	public void setLblOpcionesListado(Label lblOpcionesListado) {
		this.lblOpcionesListado = lblOpcionesListado;
	}

	// ----------------------------RadioButton----------------------------------
	private RadioButton radioButton1 = new RadioButton();

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	// ----------------------------StaticText-----------------------------------
	private StaticText staticText1 = new StaticText();
	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText staticText2) {
		this.staticText2 = staticText2;
	}

	// -------------------------------------------------------------------------
	// ----------------------------TextArea-------------------------------------
	// -------------------------------------------------------------------------
	// ----------------------------TextField------------------------------------
	private TextField tfNombre = new TextField();
	private TextField tfRecurso = new TextField();
	private TextField tfOpcion = new TextField();
	private TextField tfOpcionTabla = new TextField();

	public TextField getTfOpcionTabla() {
		return tfOpcionTabla;
	}

	public void setTfOpcionTabla(TextField tfOpcionTabla) {
		this.tfOpcionTabla = tfOpcionTabla;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public TextField getTfRecurso() {
		return tfRecurso;
	}

	public void setTfRecurso(TextField tfRecurso) {
		this.tfRecurso = tfRecurso;
	}

	public TextField getTfOpcion() {
		return tfOpcion;
	}

	public void setTfOpcion(TextField tfOpcion) {
		this.tfOpcion = tfOpcion;
	}

	// -------------------------------------------------------------------------
	// ----------------------------Tabla----------------------------------------
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private Table table1 = new Table();
	private ObjectListDataProvider ldpDatosListado = new ObjectListDataProvider();
	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public ObjectListDataProvider getLdpDatosListado() {
		return ldpDatosListado;
	}

	public void setLdpDatosListado(ObjectListDataProvider ldpDatosListado) {
		this.ldpDatosListado = ldpDatosListado;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table table1) {
		this.table1 = table1;
	}

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	// -------------------------------------------------------------------------

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMAtributoDinamico() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new PlantillaAtributoDinamico());
		ep.getObjetos().add(ind++, new Recurso());
		
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		PlantillaAtributoDinamico locPlantilla = (PlantillaAtributoDinamico) this.obtenerObjetoDelElementoPila(ind++, PlantillaAtributoDinamico.class);
		Recurso locRecurso = (Recurso) this.obtenerObjetoDelElementoPila(ind++, Recurso.class);

		locPlantilla.setNombre(this.getTextFieldValue(getTfNombre()));
		locPlantilla.setTipo(this.getDDEnumValue(getDdTipo(), Tipo.class));

		if (locRecurso.getIdRecurso() != 0) {
			locPlantilla.setIdRecurso(locRecurso.getIdRecurso());
		}

		locPlantilla.setBusqueda(this.cbBusqueda.isChecked());
		locPlantilla.setRequerido(this.cbRequerido.isChecked());
		
		this.getObjectListDataProvider().commitChanges();
		List locLista = this.getObjectListDataProvider().getList();
		this.setListaDelCommunication(locLista);
		
		locPlantilla.setListaOpciones(locLista);
		
		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, locPlantilla);
		this.getElementoPila().getObjetos().set(ind++, locRecurso);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		PlantillaAtributoDinamico locPlantilla = (PlantillaAtributoDinamico) this.obtenerObjetoDelElementoPila(ind++, PlantillaAtributoDinamico.class);
		Recurso locRecurso = (Recurso) this.obtenerObjetoDelElementoPila(ind++, Recurso.class);
		
		this.getTfNombre().setText(locPlantilla.getNombre());
		ddTipo.setSelected(Util.getEnumNameFromString(String.valueOf(locPlantilla.getTipo())));
		cbRequerido.setValue(locPlantilla.isRequerido());
		cbBusqueda.setValue(locPlantilla.isBusqueda());
		ddTipoDefaultOptions.setSelectedValue(Util.getEnumNameFromString(String.valueOf(locPlantilla.getTipo())));
		
		if (locRecurso != null) {
			this.getTfRecurso().setText(locRecurso.getNombre());
		}
		
		habilitarOpcionesListado();
	}

	private void limpiarObjetosUsados() {
		this.getTfOpcion().setText("");
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpDatosListado();
	}

	private List getListaDelCommunication() {
		return this.getComunicationBean().getListaDatosListadoAtributoDinamico();
	}

	private void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaDatosListadoAtributoDinamico(lista);
	}

	private Object lastSelected = null;

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch (Exception ex) {
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
		if (selected != null) {
			lastSelected = selected;
		}
	}

	public String btnSeleccionarRecurso_action() {
		return navegarParaSeleccionar("SeleccionarRecurso");
	}

	public String btnLimpiarRecurso_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			this.limpiarObjeto(1, this.getTfRecurso());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public void btnAgregarDato_action() {
		this.guardarEstadoObjetosUsados();
		Object opcion = tfOpcion.getText();
		System.out.println("tfOpcion: " + tfOpcion.getText());
		PlantillaAtributoDinamico locPlantilla = (PlantillaAtributoDinamico) this.obtenerObjetoDelElementoPila(0, PlantillaAtributoDinamico.class);
		List listaDatosListado = this.getListaDelCommunication();
		if (opcion != null && opcion != "") {
			// StringAux string = new StringAux(opcion.toString());
			OpcionAtributoDinamicoListado opcionAtributoDinamicoListado = new OpcionAtributoDinamicoListado();
			opcionAtributoDinamicoListado.setValor(opcion.toString());
			opcionAtributoDinamicoListado.setPlantillaAtributoDinamico(locPlantilla);
			System.out.println("opcion-----------" + opcion.toString());
			listaDatosListado.add(opcionAtributoDinamicoListado);
			this.setListaDelCommunication(listaDatosListado);
			this.getObjectListDataProvider().setList(listaDatosListado);
			// System.out.println("FIELDKEY: " +
			// this.getObjectListDataProvider().getFieldKey("1"));
						
			this.guardarEstadoObjetosUsados();
			
			this.limpiarObjetosUsados();
		}
	}

	public String btnQuitarDato_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(this.getSeleccionado() != null)
			System.out.println(this.getSeleccionado());
		if(this.getRadioButton1() != null)
			System.out.println(this.getRadioButton1());
		
		if (ultimo) {
			try {
				RowKey rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					OpcionAtributoDinamicoListado locOpcionAtributoDinamicoListado = (OpcionAtributoDinamicoListado) obj;
					this.getListaDelCommunication().remove(locOpcionAtributoDinamicoListado);
					System.out.println("atrib a remover: " + locOpcionAtributoDinamicoListado.getValor());
					this.getLdpDatosListado().setList(this.getListaDelCommunication());
					this.getObjectListDataProvider().setList(this.getListaDelCommunication());
					
					System.out.println("Size del comunication despues de quitar: " + this.getListaDelCommunication().size());

				} else {
					warn("Debe seleccionar el dato que desea eliminar.");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public void valueChangeEvent(ValueChangeEvent event) {
		habilitarOpcionesListado();
		System.out.println("VALOR DEL COMBO: " + this.ddTipo.getValue());
	}

	@Override
	protected String getNombrePagina() {
		return "Atributo Dinámico";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMAtributoDinamico";
	}

	public class StringAux {

		private String string;

		public StringAux() {
		}

		public StringAux(String string) {
			this.string = string;
		}

		public String getString() {
			return string;
		}

		public void setString(String string) {
			this.string = string;
		}
	}

	public void habilitarOpcionesListado() {

//		if (this.ddTipo.getSelected().equals(PlantillaAtributoDinamico.Tipo.LISTADO.toString())) {
//			this.lblOpcionesListado.setVisible(true);
//			this.label4.setVisible(true);
//			this.tfOpcion.setVisible(true);
////			this.btnAgregarDato.setDisabled(true);
////			this.btnQuitarDato.setDisabled(true) ;
//			this.table1.setVisible(true);
//			this.cbBusqueda.setDisabled(false);
//			this.cbBusqueda.setSelected(true);
//		} else if (this.ddTipo.getSelected().equals(PlantillaAtributoDinamico.Tipo.ARCHIVO.toString())) {
//			this.cbBusqueda.setDisabled(true);
//			this.cbBusqueda.setSelected(false);
//			this.lblOpcionesListado.setVisible(false);
//			this.table1.setVisible(false);
//		} else {
//			this.lblOpcionesListado.setVisible(false);
//			this.label4.setVisible(false);
//			this.cbBusqueda.setSelected(true);
//			this.cbBusqueda.setDisabled(false);
//			this.table1.setVisible(false);
//		}
		
		if (this.ddTipo.getSelected().equals(PlantillaAtributoDinamico.Tipo.LISTADO.toString())) {
			this.lblOpcionesListado.setVisible(true);
			this.label4.setRendered(true);
			this.tfOpcion.setRendered(true);
//			this.btnAgregarDato.setDisabled(true);
//			this.btnQuitarDato.setDisabled(true) ;
			this.table1.setRendered(true);
			this.cbBusqueda.setDisabled(false);
			this.cbBusqueda.setSelected(true);
		} else if (this.ddTipo.getSelected().equals(PlantillaAtributoDinamico.Tipo.ARCHIVO.toString())) {
			this.cbBusqueda.setDisabled(true);
			this.cbBusqueda.setSelected(false);
			this.lblOpcionesListado.setRendered(false);
			this.table1.setRendered(false);
		} else {
			this.lblOpcionesListado.setRendered(false);
			this.label4.setRendered(false);
			this.table1.setRendered(false);
		}
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof Recurso) {
			Recurso locRecurso = (Recurso) pObject;
			this.getElementoPila().getObjetos().set(1, locRecurso);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		PlantillaAtributoDinamico locPlantilla = (PlantillaAtributoDinamico) pObject;
		Recurso locRecurso = new Recurso();
		locRecurso.setNombre(SecurityMgr.getInstance().getNombreRecurso(locPlantilla.getIdRecurso()));

		this.setListaDelCommunication(locPlantilla.getListaOpciones());
		this.getObjectListDataProvider().setList(this.getListaDelCommunication());
			
		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, locPlantilla);
		this.getElementoPila().getObjetos().set(ind++, locRecurso);
	}

	// public void habilitarOpcionesListado() {
	// if
	// (this.ddTipo.getSelected().equals(PlantillaAtributoDinamico.Tipo.LISTADO.toString()))
	// {
	// this.lblOpcionesListado.setVisible(true);
	// this.table1.setVisible(true);
	// } else {
	// this.lblOpcionesListado.setVisible(false);
	// this.table1.setVisible(false);
	// }
	// }
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		PlantillaAtributoDinamico locPlantilla = this.obtenerObjetoDelElementoPila(0, PlantillaAtributoDinamico.class);
		this.getTablaLogs().getLdpLogs().setList(locPlantilla.getListaLogsAuditoria());
	}
	
	@Override
	public long getSerialVersionUID() {
		return PlantillaAtributoDinamico.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMAtributoDinamico$ABMAtributoDinamico}";
	}
}