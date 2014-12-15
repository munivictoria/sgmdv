/*
 * AgregarObra.java
 *
 * Created on 3 de noviembre de 2006, 15:17
 * Copyright Trascender SRL
 */

package muni.habilitaciones.grpPlanFinanciacionObra.ABMObra;

import java.util.ArrayList;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra.TipoObra;
import com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

// comment for ana
/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMObra extends ABMPageBean {

	// CAMBIAR: Constantes que varian segun la pagina.
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Modificar Obra";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "ModificarObra";

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		if(this.getListaDelCommunicationTabla2() != null) {
			this.getObjectListDataProviderTabla2().setList(this.getListaDelCommunicationTabla2());
		}

		Option[] op = null;
		// Tipo de Obra
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(TipoObra.values(), "cap");
		ddTipoObraDefaultOptions.setOptions(op);
	}

	private Label label15 = new Label();

	public Label getLabel15() {
		return label15;
	}

	public void setLabel15(Label l) {
		this.label15 = l;
	}

	private Label label16 = new Label();

	public Label getLabel16() {
		return label16;
	}

	public void setLabel16(Label l) {
		this.label16 = l;
	}

	private TextField tfMetrosTotalAfectados = new TextField();

	public TextField getTfMetrosTotalAfectados() {
		return tfMetrosTotalAfectados;
	}

	public void setTfMetrosTotalAfectados(TextField tf) {
		this.tfMetrosTotalAfectados = tf;
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

	private ObjectListDataProvider ldpCuadra = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpCuadra() {
		return ldpCuadra;
	}

	public void setLdpCuadra(ObjectListDataProvider oldp) {
		this.ldpCuadra = oldp;
	}

	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	private RadioButton radioButton1 = new RadioButton();

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton rb) {
		this.radioButton1 = rb;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private Label label7 = new Label();

	public Label getLabel7() {
		return label7;
	}

	public void setLabel7(Label l) {
		this.label7 = l;
	}

	private ObjectListDataProvider ldpPlanCuentaPorObra = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpPlanCuentaPorObra() {
		return ldpPlanCuentaPorObra;
	}

	public void setLdpPlanCuentaPorObra(ObjectListDataProvider oldp) {
		this.ldpPlanCuentaPorObra = oldp;
	}

	private Label label27 = new Label();

	public Label getLabel27() {
		return label27;
	}

	public void setLabel27(Label l) {
		this.label27 = l;
	}

	private Table table2 = new Table();

	public Table getTable2() {
		return table2;
	}

	public void setTable2(Table t) {
		this.table2 = t;
	}

	private TableRowGroup tableRowGroup2 = new TableRowGroup();

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup trg) {
		this.tableRowGroup2 = trg;
	}

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	private Button btnAgregarPlanCuentaObra = new Button();

	public Button getBtnAgregarPlanCuentaObra() {
		return btnAgregarPlanCuentaObra;
	}

	public void setBtnAgregarPlanCuentaObra(Button b) {
		this.btnAgregarPlanCuentaObra = b;
	}

	protected HtmlAjaxCommandButton btnQuitarPlanCuentaObra = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnQuitarPlanCuentaObra() {
		return btnQuitarPlanCuentaObra;
	}

	public void setBtnQuitarPlanCuentaObra(HtmlAjaxCommandButton btnQuitarPlanCuentaObra) {
		this.btnQuitarPlanCuentaObra = btnQuitarPlanCuentaObra;
	}

	private TableColumn tableColumn4 = new TableColumn();

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tc) {
		this.tableColumn4 = tc;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private TableColumn tableColumn11 = new TableColumn();

	public TableColumn getTableColumn11() {
		return tableColumn11;
	}

	public void setTableColumn11(TableColumn tc) {
		this.tableColumn11 = tc;
	}

	private RadioButton radioButton2 = new RadioButton();

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton rb) {
		this.radioButton2 = rb;
	}

	private PanelGroup groupPanel3 = new PanelGroup();

	public PanelGroup getGroupPanel3() {
		return groupPanel3;
	}

	public void setGroupPanel3(PanelGroup pg) {
		this.groupPanel3 = pg;
	}

	private Button btnAgregarCuadra = new Button();

	public Button getBtnAgregarCuadra() {
		return btnAgregarCuadra;
	}

	public void setBtnAgregarCuadra(Button b) {
		this.btnAgregarCuadra = b;
	}

	protected HtmlAjaxCommandButton btnQuitarCuadra = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnQuitarCuadra() {
		return btnQuitarCuadra;
	}

	public void setBtnQuitarCuadra(HtmlAjaxCommandButton btnQuitarCuadra) {
		this.btnQuitarCuadra = btnQuitarCuadra;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private TextField tfCostoTotalObra = new TextField();

	public TextField getTfCostoTotalObra() {
		return tfCostoTotalObra;
	}

	public void setTfCostoTotalObra(TextField tf) {
		this.tfCostoTotalObra = tf;
	}

	private TextArea taDescripcion = new TextArea();

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea ta) {
		this.taDescripcion = ta;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private StaticText staticText6 = new StaticText();

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText st) {
		this.staticText6 = st;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private TextField tfNumeroObra = new TextField();

	public TextField getTfNumeroObra() {
		return tfNumeroObra;
	}

	public void setTfNumeroObra(TextField tf) {
		this.tfNumeroObra = tf;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private DropDown ddTipoObra = new DropDown();

	public DropDown getDdTipoObra() {
		return ddTipoObra;
	}

	public void setDdTipoObra(DropDown dd) {
		this.ddTipoObra = dd;
	}

	private SingleSelectOptionsList ddTipoObraDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdTipoObraDefaultOptions() {
		return ddTipoObraDefaultOptions;
	}

	public void setDdTipoObraDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddTipoObraDefaultOptions = ssol;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private TextField tfValorPorMetro = new TextField();

	public TextField getTfValorPorMetro() {
		return tfValorPorMetro;
	}

	public void setTfValorPorMetro(TextField tf) {
		this.tfValorPorMetro = tf;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	private TextField tfDigestoMunicipal = new TextField();

	public TextField getTfDigestoMunicipal() {
		return tfDigestoMunicipal;
	}

	public void setTfDigestoMunicipal(TextField tf) {
		this.tfDigestoMunicipal = tf;
	}

	private HtmlAjaxCommandButton btnLimpiarDigesto = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarDigesto() {
		return btnLimpiarDigesto;
	}

	public void setBtnLimpiarDigesto(HtmlAjaxCommandButton btnLimpiarDigesto) {
		this.btnLimpiarDigesto = btnLimpiarDigesto;
	}

	private Button btnSeleccionarDigesto = new Button();

	public Button getBtnSeleccionarDigesto() {
		return btnSeleccionarDigesto;
	}

	public void setBtnSeleccionarDigesto(Button b) {
		this.btnSeleccionarDigesto = b;
	}

	private Label lblObraViaAdministracion = new Label();

	public Label getLblObraViaAdministracion() {
		return lblObraViaAdministracion;
	}

	public void setLblObraViaAdministracion(Label lblObraViaAdministracion) {
		this.lblObraViaAdministracion = lblObraViaAdministracion;
	}

	private Checkbox cbObraViaAdministracion = new Checkbox();

	public Checkbox getCbObraViaAdministracion() {
		return cbObraViaAdministracion;
	}

	public void setCbObraViaAdministracion(Checkbox cbObraViaAdministracion) {
		this.cbObraViaAdministracion = cbObraViaAdministracion;
	}

	// </editor-fold>
	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMObra() {
	}

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new Obra());
		ep.getObjetos().add(ind++, new ArrayList()); // Tabla 1: cuadras afectadas
		ep.getObjetos().add(ind++, new ArrayList()); // Tabla 2: planes de cuenta para Obras
		ep.getObjetos().add(ind++, new DigestoMunicipal());
		ep.getObjetos().add(ind++, new ArrayList()); // cuadras a eliminar
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		Obra obra = (Obra) this.obtenerObjetoDelElementoPila(ind++, Obra.class);
		ArrayList cuadrasTabla = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		ArrayList planesCuentaObra = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		DigestoMunicipal digesto = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(ind++, DigestoMunicipal.class);

		obra.setNumeroObra(getTextFieldValue(this.getTfNumeroObra()));
		obra.setDescripcion(getTextAreaValue(this.getTaDescripcion()));
		obra.setTipoObra(getDDEnumValue(this.getDdTipoObra(), TipoObra.class));
		obra.setMetrosTotalAfectados(getTextFieldValueDouble(this.getTfMetrosTotalAfectados()));
		obra.setValorPorMetro(getTextFieldValueDouble(this.getTfValorPorMetro()));
		obra.setCostoTotalObra(getTextFieldValueDouble(this.getTfCostoTotalObra()));

		if(digesto.getIdDigestoMunicipal() == -1) {
			digesto = null;
		}
		obra.setDigestoMunicipal(digesto);

		obra.setObraViaAdministracion(cbObraViaAdministracion.isChecked());

		this.getObjectListDataProvider().commitChanges();
		cuadrasTabla = (ArrayList) this.getObjectListDataProvider().getList();
		if(cuadrasTabla.size() <= 0) {
			cuadrasTabla = null;
		}
		/*
		 * if (obra.getListaCuadras() != null) { obra.getListaCuadras().clear(); if (cuadrasTabla != null) { obra.getListaCuadras().addAll(cuadrasTabla); } }
		 */
		this.setListaDelCommunication(cuadrasTabla);

		this.getObjectListDataProviderTabla2().commitChanges();
		planesCuentaObra = (ArrayList) this.getObjectListDataProviderTabla2().getList();
		if(planesCuentaObra.size() <= 0) {
			planesCuentaObra = null;
		}
		/*
		 * if (obra.getListaPlanesCuentaPorObra() != null) { obra.getListaPlanesCuentaPorObra().clear(); if (planesCuentaObra != null) {
		 * obra.getListaPlanesCuentaPorObra().addAll(planesCuentaObra); } }
		 */
		this.setListaDelCommunicationTabla2(planesCuentaObra);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, obra);
		this.getElementoPila().getObjetos().set(ind++, cuadrasTabla);
		this.getElementoPila().getObjetos().set(ind++, planesCuentaObra);
		this.getElementoPila().getObjetos().set(ind++, digesto);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		Obra obra = null;
		ArrayList cuadrasTabla = null;
		ArrayList cuadras = null;
		ArrayList planesCuentaObra = null;
		ArrayList planesCuentaObraTabla = null;
		ArrayList listaCuadras = null;
		listaCuadras = new ArrayList();
		DigestoMunicipal digesto = null;

		if(this.getRequestBean1().getObjetosSeleccionMultiple() != null) {

			boolean algunaRepetida = false;
			obra = (Obra) this.getElementoPila().getObjetos().get(0);
			// cuadras = new ArrayList();
			// Marcos: Agrego todas las cuadras de obra al Arreglo cuadras
			// cuadras.addAll(obra.getListaCuadras());
			cuadrasTabla = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
			// cuadrasTabla = (ArrayList) this.getElementoPila().getObjetos().get(1);

			// Logica especial de Cuadras Afectadas
			for(int i = 0; i < this.getRequestBean1().getObjetosSeleccionMultiple().size(); i++) {
				Object seleccionado = this.getRequestBean1().getObjetosSeleccionMultiple().get(i);
				if(seleccionado instanceof Cuadra) {
					Cuadra nuevaCuadra = (Cuadra) seleccionado;

					Cuadra deLaTabla = null;
					boolean esta = false;
					int j = 0;
					if(cuadrasTabla != null) {
						while(j < cuadrasTabla.size() && !esta) {
							deLaTabla = (Cuadra) cuadrasTabla.get(j++);
							esta = (deLaTabla.getIdCuadra() == nuevaCuadra.getIdCuadra());
						}
					}

					if(!esta) {

						try {
							// cuadrasTabla = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
							this.getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(this.getSessionBean1().getLlave());
							Cuadra cuadra = (Cuadra) this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().getCuadraPorId(new Long(nuevaCuadra.getIdCuadra()));
							obra.getListaCuadras().add(cuadra);

							// CuadraModificarObraTabla cuadraModificarObraTabla = new CuadraModificarObraTabla(cuadra, cuadra.getNombre());

							cuadrasTabla.add(cuadra);

						} catch(Exception e) {
							e.printStackTrace();
						}

					} else {
						algunaRepetida = true;
					}
				}
			}
			if(algunaRepetida) {
				warn("Al menos una Cuadra de las que intent\363 agregar ya se encontraba en la lista y no fue agregada.");
			}

			this.getElementoPila().getObjetos().set(0, obra);
			this.getElementoPila().getObjetos().set(1, new ArrayList(obra.getListaCuadras()));

			this.getRequestBean1().setObjetoSeleccion(null);
		}

		// if (this.getRequestBean1().getRespuestaABM() != null) {
		// Object respuesta = this.getRequestBean1().getRespuestaABM();
		// int posicionEP = -1;
		// ArrayList arrayListCorrespondiente = null;
		// if (respuesta instanceof RenovacionLibretaSanitaria) {
		// posicionEP = 2;
		// }
		// if (respuesta instanceof ConstanciaVacunacion) {
		// posicionEP = 3;
		// }
		// if (respuesta instanceof InhabilitacionTemporariaLS) {
		// posicionEP = 4;
		// }
		// if (posicionEP != -1) {
		// arrayListCorrespondiente = (ArrayList) this.obtenerObjetoDelElementoPila(posicionEP, ArrayList.class);
		// arrayListCorrespondiente.add(respuesta);
		// this.getElementoPila().getObjetos().set(posicionEP, arrayListCorrespondiente);
		// }
		// }

		// this.acomodarSeleccionado();

		ind = 0;
		obra = (Obra) this.obtenerObjetoDelElementoPila(ind++, Obra.class);
		cuadrasTabla = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		planesCuentaObraTabla = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		digesto = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(3, DigestoMunicipal.class);

		this.getCbObraViaAdministracion().setValue(new Boolean(obra.isObraViaAdministracion()));
		this.getTfNumeroObra().setText(obra.getNumeroObra());
		this.getTaDescripcion().setText(obra.getDescripcion());

		if(obra.getCostoTotalObra() != null) {
			this.getTfCostoTotalObra().setText(Conversor.getStringDeDouble(obra.getCostoTotalObra()));
		}
		if(obra.getMetrosTotalAfectados() != null) {
			this.getTfMetrosTotalAfectados().setText(Conversor.getStringDeDouble(obra.getMetrosTotalAfectados()));
		}
		if(obra.getValorPorMetro() != null) {
			this.getTfValorPorMetro().setText(obra.getValorPorMetro().toString());
		}
		if(digesto != null && digesto.getIdDigestoMunicipal() != -1) {
			this.getTfDigestoMunicipal().setText(digesto.toString());
		} else if(obra.getDigestoMunicipal() != null)
			this.getTfDigestoMunicipal().setText(obra.getDigestoMunicipal().toString());

		ddTipoObra.setSelected(Util.getEnumNameFromString(String.valueOf(obra.getTipoObra())));
		ddTipoObraDefaultOptions.setSelectedValue(Util.getEnumNameFromString(String.valueOf(obra.getTipoObra())));

		/*
		 * for (Iterator it = cuadrasTabla.iterator(); it.hasNext();) { CuadraModificarObraTabla cuadra = (CuadraModificarObraTabla)it.next();
		 * System.out.println("cuadra lista--" + cuadra.getNombre()); }
		 */

		// System.out.println("tam planes" + planesCuentaObraTabla.size());
		// Tabla 1
		// this.getObjectListDataProvider().setList(cuadrasTabla);
		// this.setListaDelCommunication(cuadrasTabla);

		this.getObjectListDataProvider().setList(cuadrasTabla);
		this.setListaDelCommunication(cuadrasTabla);
		// Tabla 2
		this.getObjectListDataProviderTabla2().setList(planesCuentaObraTabla);
		this.setListaDelCommunicationTabla2(planesCuentaObraTabla);

	}

	private ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpCuadra();
	}

	private ObjectListDataProvider getObjectListDataProviderTabla2() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpPlanCuentaPorObra();
	}

	private ArrayList getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaCuadras();
	}

	private void setListaDelCommunication(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaCuadras(lista);
	}

	// Tabla 2

	private ArrayList getListaDelCommunicationTabla2() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaPlanesCuentaPorObra();
	}

	private void setListaDelCommunicationTabla2(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaPlanesCuentaPorObra(lista);
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Metodos estaticos de la pagina">

	// <editor-fold defaultstate="collapsed" desc="Metodos para seleccionar RaddioButton">
	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	private Object lastSelected = null;

	public Object getRBSelected() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if(selected != null) {
			lastSelected = selected;
		}
	}

	public String getCurrentRow2() {
		return tableRowGroup2.getRowKey().getRowId();
	}

	public void setCurrentRow2(int row) {
	}

	private Object lastSelected2 = null;

	public Object getRBSelected2() {
		String sv = (String) radioButton2.getSelectedValue();
		return sv.equals(lastSelected2) ? sv : null;
	}

	public void setRBSelected2(Object selected) {
		if(selected != null) {
			lastSelected2 = selected;
		}
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="Metodos para seleccionar la fila recientemente seleccionada">
	private RowKey rowKeySeleccionado = null;

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	public void guardarOrdenamiento() {
		this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
	}

	public void setearOrdenamiento() {
		this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
	}

	public Long getPosicionEnTabla(RowKey rk) {
		long inicioPagina = 0;
		long posicionGlobal = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
		long cantRegistros = this.getTableRowGroup1().getRowCount();
		boolean encontrado = false;

		if(rk != null) {
			while(!encontrado && inicioPagina < cantRegistros) {
				this.getTableRowGroup1().setFirst((int) inicioPagina);
				posicionEnPagina = 0;
				while(!encontrado && posicionEnPagina < cantRegistrosPorPagina) {
					encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int) posicionEnPagina].equals(rk);
					if(!encontrado) {
						posicionEnPagina++;
						posicionGlobal++;
					}
				}
				if(!encontrado) {
					inicioPagina += cantRegistrosPorPagina;
				}
			}
		}
		return new Long(posicionGlobal);
	}

	public RowKey getRowKeyAsociado(Long posicionEnTabla) {
		RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];
		return rk;
	}

	public void seleccionarFila(Long posicionGlobal) {
		long cantFilas = this.getTableRowGroup1().getRowCount();

		if(cantFilas > 0) {
			// si hay al menos una fila
			if(posicionGlobal.longValue() >= cantFilas) {
				// si elimine la ultima fila, me posiciono en la anterior
				posicionGlobal = new Long(cantFilas - 1);
			}

			int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
			this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
			lastSelected = new Long(index).toString();
		}
	}

	public Long getInicioPagina(Long posicionGlobal) {
		long inicioPagina = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();

		inicioPagina = (posicionGlobal.longValue() / cantRegistrosPorPagina) * cantRegistrosPorPagina;
		return new Long(inicioPagina);
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	// Tabla 2
	public RowKey getSeleccionado2() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup2");
			rk = this.getObjectListDataProviderTabla2().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	// </editor-fold>

	// </editor-fold>
	public String btnAgregarCuadra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();

			this.getRequestBean1().setTipoSeleccion("MULTIPLE");
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminCuadra";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarCuadra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		// ArrayList listaCuadras = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
		ArrayList listaCuadras = (ArrayList) this.obtenerObjetoDelElementoPila(4, ArrayList.class);

		if(ultimo) {
			RowKey rk = null;

			// APLICAR LOGICA AQUI...
			try {
				rk = this.getSeleccionado();
				if(rk != null) {

					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					Cuadra cuadra = (Cuadra) obj;
					this.getListaDelCommunication().remove(cuadra);

					// CuadraModificarObraTabla cuadraModificarObraTabla = (CuadraModificarObraTabla) obj;

					Obra obra = (Obra) this.getElementoPila().getObjetos().get(0);

					listaCuadras.add(cuadra);

					obra.getListaCuadras().remove(cuadra);

					this.getElementoPila().getObjetos().set(0, obra);
					this.getElementoPila().getObjetos().set(1, new ArrayList(obra.getListaCuadras()));
					this.getElementoPila().getObjetos().set(4, listaCuadras);

				}

			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_QuitarError:", ex);
				error(NOMBRE_PAGINA + " - Quitar: " + ex.getMessage());
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarPlanCuentaObra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.getRequestBean1().setRefrescarTabla(true);

			retorno = "AdminPlanCuentaObra";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarPlanCuentaObra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.getRequestBean1().setRefrescarTabla(true);
			// APLICAR LOGICA AQUI...
			try {
				rk = this.getSeleccionado2();
				if(rk != null) {

					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderTabla2().getObjects()[index];
					this.getListaDelCommunicationTabla2().remove(obj);
					// PlanCuentaObraModificarTabla planCuentaObraModificarTabla = (PlanCuentaObraModificarTabla) obj;

					Obra obra = (Obra) this.getElementoPila().getObjetos().get(0);
					obra.getListaPlanesCuentaPorObra().remove(obj);
					this.getElementoPila().getObjetos().set(0, obra);
					this.getElementoPila().getObjetos().set(2, new ArrayList(obra.getListaPlanesCuentaPorObra()));

				}
			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_QuitarError:", ex);
				error(NOMBRE_PAGINA + " - Quitar: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarDigesto_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(3, tfDigestoMunicipal);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarDigesto_action() {
		return navegarParaSeleccionar("AdminDigestoMunicipal");
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMObra";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		Obra obra = null;
		ArrayList planesCuentaObraTabla = null;

		obra = (Obra) this.getElementoPila().getObjetos().get(0);

		// planesCuentaObra = new ArrayList();
		// Marcos: Agrego todas las cuadras de obra al Arreglo cuadras
		// planesCuentaObra.addAll(obra.getListaPlanesCuentaPorObra());
		// planesCuentaObraTabla = (ArrayList) this.getElementoPila().getObjetos().get(2);
		planesCuentaObraTabla = (ArrayList) this.obtenerObjetoDelElementoPila(2, ArrayList.class);

		// Logica especial de Planes de Cuenta Por Obra
		if(pObject instanceof PlanCuentaObra) {
			PlanCuentaObra nuevoPlanCuentaObra = (PlanCuentaObra) pObject;

			PlanCuentaObra deLaTabla = null;
			boolean esta = false;
			int i = 0;
			if(planesCuentaObraTabla != null) {
				while(i < planesCuentaObraTabla.size() && !esta) {
					deLaTabla = (PlanCuentaObra) planesCuentaObraTabla.get(i++);
					esta = (deLaTabla.getIdPlanCuentaPorObra() == nuevoPlanCuentaObra.getIdPlanCuentaPorObra());
				}
			}

			if(!esta) {

				try {
					// planesCuentaObraTabla = (ArrayList) this.obtenerObjetoDelElementoPila(2, ArrayList.class);
					this.getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra().setLlave(this.getSessionBean1().getLlave());
					PlanCuentaObra planCuentaObra = (PlanCuentaObra) this.getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra()
							.getPlanCuentaObraPorId(nuevoPlanCuentaObra.getIdPlanCuentaPorObra());

					obra.getListaPlanesCuentaPorObra().add(planCuentaObra);

					// PlanCuentaObraModificarTabla cuentaObraModificarTabla = new PlanCuentaObraModificarTabla(planCuentaObra.getNombre(), planCuentaObra);

					planesCuentaObraTabla.add(planCuentaObra);

				} catch(Exception e) {
					log("Error Descripcion_ : ", e);
					e.printStackTrace();
				}

				// planesCuentaObraTabla.add(nuevoPlanCuentaObra);
				// obra.getListaPlanesCuentaPorObra().add(nuevoPlanCuentaObra);

			} else {
				warn("El Plan de Cuenta que intenta agregar ya se encuentra en la lista.");
			}
			this.getElementoPila().getObjetos().set(2, planesCuentaObraTabla);
			this.getRequestBean1().setObjetoSeleccion(null);
		}
		if(pObject instanceof DigestoMunicipal) {
			DigestoMunicipal digestoMunicipal = (DigestoMunicipal) pObject;

			this.getElementoPila().getObjetos().set(3, digestoMunicipal);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		int ind = 0;
		Obra obra = null;
		ArrayList cuadrasTabla = null;
		ArrayList cuadras = null;
		ArrayList planesCuentaObraTabla = null;
		ArrayList listaCuadras = new ArrayList();

		obra = (Obra) pObject;

		Long idObra = new Long(obra.getIdObra());

		try {
			this.getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra().setLlave(this.getSessionBean1().getLlave());
			obra = this.getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra().getObraPorId(idObra.longValue());
			this.getElementoPila().getObjetos().set(3, obra.getDigestoMunicipal());
		} catch(Exception ex) {
			log(CASO_NAVEGACION + "_BuscarObraPorIdError:", ex);
			error(NOMBRE_PAGINA + " - No se pudo recuperar la Obra: " + ex.getMessage());
			ex.printStackTrace();
		}

		cuadrasTabla = new ArrayList(obra.getListaCuadras());
		planesCuentaObraTabla = new ArrayList(obra.getListaPlanesCuentaPorObra());
		/*
		 * cuadrasTabla = new ArrayList();
		 * 
		 * Iterator iterator = obra.getListaCuadras().iterator();
		 * 
		 * //Marcos: Agrago cuadra a cuadra dentro de cuadrasTabla con objetos CuadraModificarObraTabla para mostrar en la tabla while (iterator.hasNext()) {
		 * Cuadra cuadra = (Cuadra) iterator.next();
		 * 
		 * CuadraModificarObraTabla cuadraModificarObraTabla = new CuadraModificarObraTabla(cuadra, cuadra.getNombre());
		 * 
		 * //Marcos: Agrego a la CuadrasTabla la CuadraModificarObraTabla cuadrasTabla.add(cuadraModificarObraTabla);
		 * 
		 * }
		 *//*
			 * planesCuentaObraTabla = new ArrayList();
			 * 
			 * Iterator iteratorPlanCuentaO = obra.getListaPlanesCuentaPorObra().iterator();
			 * 
			 * while(iteratorPlanCuentaO.hasNext()){ PlanCuentaObra planCuentaObra = (PlanCuentaObra) iteratorPlanCuentaO.next(); PlanCuentaObraModificarTabla
			 * planCuentaObraModificarTabla = new PlanCuentaObraModificarTabla(planCuentaObra.getNombre(), planCuentaObra);
			 * 
			 * planesCuentaObraTabla.add(planCuentaObraModificarTabla); }
			 */
		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, obra);
		this.getElementoPila().getObjetos().set(ind++, cuadrasTabla);
		this.getElementoPila().getObjetos().set(ind++, planesCuentaObraTabla);
		this.getElementoPila().getObjetos().set(4, listaCuadras);
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra}";
	}

	@Override
	public long getSerialVersionUID() {
		return Obra.serialVersionUID;
	}
}