package muni.excepciones.ABMRefinanciacion;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;
import javax.faces.event.ValueChangeEvent;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TabSet;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.auditoriaTributaria.AuditoriaTributaria;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;

public class GenerarRefinanciacion extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		if(this.getListaDelCommunicationTabla2() != null) {
			this.getObjectListDataProviderTabla2().setList(this.getListaDelCommunicationTabla2());
		}

		if(this.getListaDelCommunicationTabla3() != null) {
			this.getObjectListDataProviderTabla3().setList(this.getListaDelCommunicationTabla3());
		}

		Option[] op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().keySet().toArray(), "cap");
		ddTipoObligacionDefaultOptions.setOptions(op);

		numberConverter1.setPattern("$ #,##0.00");
		dateTimeConverter.setTimeZone(null);
		dateTimeConverter.setPattern("dd/MM/yyyy");
		dateTimeConverter.setTimeStyle("full");
	}

	private TextField tfContribuyente = new TextField();

	public TextField getTfContribuyente() {
		return tfContribuyente;
	}

	public void setTfContribuyente(TextField tf) {
		this.tfContribuyente = tf;
	}

	private TextField tfAuditoriaTributaria = new TextField();

	public TextField getTfAuditoriaTributaria() {
		return tfAuditoriaTributaria;
	}

	public void setTfAuditoriaTributaria(TextField tfAuditoriaTributaria) {
		this.tfAuditoriaTributaria = tfAuditoriaTributaria;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private Button btnSeleccionarAuditoriaTributaria = new Button();

	public Button getBtnSeleccionarAuditoriaTributaria() {
		return btnSeleccionarAuditoriaTributaria;
	}

	public void setBtnSeleccionarAuditoriaTributaria(Button btnSeleccionarAuditoriaTributaria) {
		this.btnSeleccionarAuditoriaTributaria = btnSeleccionarAuditoriaTributaria;
	}

	private Button btnLimpiarAuditoriaTributaria = new Button();

	public Button getBtnLimpiarAuditoriaTributaria() {
		return btnLimpiarAuditoriaTributaria;
	}

	public void setBtnLimpiarAuditoriaTributaria(Button btnLimpiarAuditoriaTributaria) {
		this.btnLimpiarAuditoriaTributaria = btnLimpiarAuditoriaTributaria;
	}

	private TabSet tabSet1 = new TabSet();

	public TabSet getTabSet1() {
		return tabSet1;
	}

	public void setTabSet1(TabSet tabSet1) {
		this.tabSet1 = tabSet1;
	}

	private NumberConverter numberConverter1 = new NumberConverter();

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter numberConverter1) {
		this.numberConverter1 = numberConverter1;
	}

	private DateTimeConverter dateTimeConverter = new DateTimeConverter();

	public DateTimeConverter getDateTimeConverter() {
		return dateTimeConverter;
	}

	public void setDateTimeConverter(DateTimeConverter dateTimeConverter) {
		this.dateTimeConverter = dateTimeConverter;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private ObjectListDataProvider ldpObligaciones = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpObligaciones() {
		return ldpObligaciones;
	}

	public void setLdpObligaciones(ObjectListDataProvider oldp) {
		this.ldpObligaciones = oldp;
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

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private TableColumn tableColumn7 = new TableColumn();

	public TableColumn getTableColumn7() {
		return tableColumn7;
	}

	public void setTableColumn7(TableColumn tc) {
		this.tableColumn7 = tc;
	}

	private Checkbox checkbox4 = new Checkbox();

	public Checkbox getCheckbox4() {
		return checkbox4;
	}

	public void setCheckbox4(Checkbox c) {
		this.checkbox4 = c;
	}

	private Button btnSeleccionarPersonaFisica = new Button();

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button b) {
		this.btnSeleccionarPersonaFisica = b;
	}

	private Button btnSeleccionarPersonaJuridica = new Button();

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button b) {
		this.btnSeleccionarPersonaJuridica = b;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private DropDown ddTipoObligacion = new DropDown();

	public DropDown getDdTipoObligacion() {
		return ddTipoObligacion;
	}

	public void setDdTipoObligacion(DropDown dd) {
		this.ddTipoObligacion = dd;
	}

	private SingleSelectOptionsList ddTipoObligacionDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdTipoObligacionDefaultOptions() {
		return ddTipoObligacionDefaultOptions;
	}

	public void setDdTipoObligacionDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddTipoObligacionDefaultOptions = ssol;
	}

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	private Button btnArmarPlanDePago = new Button();

	public Button getBtnArmarPlanDePago() {
		return btnArmarPlanDePago;
	}

	public void setBtnArmarPlanDePago(Button b) {
		this.btnArmarPlanDePago = b;
	}

	private Button btnArmarPlanDePagoAuditoria = new Button();

	public Button getBtnArmarPlanDePagoAuditoria() {
		return btnArmarPlanDePagoAuditoria;
	}

	public void setBtnArmarPlanDePagoAuditoria(Button btnArmarPlanDePagoAuditoria) {
		this.btnArmarPlanDePagoAuditoria = btnArmarPlanDePagoAuditoria;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label label5) {
		this.label5 = label5;
	}

	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label label6) {
		this.label6 = label6;
	}

	private Table table2 = new Table();

	public Table getTable2() {
		return table2;
	}

	public void setTable2(Table t) {
		this.table2 = t;
	}

	private Table table3 = new Table();

	public Table getTable3() {
		return table3;
	}

	public void setTable3(Table table3) {
		this.table3 = table3;
	}

	private TableRowGroup tableRowGroup2 = new TableRowGroup();

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup trg) {
		this.tableRowGroup2 = trg;
	}

	private TableRowGroup tableRowGroup3 = new TableRowGroup();

	public TableRowGroup getTableRowGroup3() {
		return tableRowGroup3;
	}

	public void setTableRowGroup3(TableRowGroup tableRowGroup3) {
		this.tableRowGroup3 = tableRowGroup3;
	}

	private PanelGroup groupPanel2 = new PanelGroup();

	public PanelGroup getGroupPanel2() {
		return groupPanel2;
	}

	public void setGroupPanel2(PanelGroup pg) {
		this.groupPanel2 = pg;
	}

	private PanelGroup groupPanel3 = new PanelGroup();

	public PanelGroup getGroupPanel3() {
		return groupPanel3;
	}

	public void setGroupPanel3(PanelGroup groupPanel3) {
		this.groupPanel3 = groupPanel3;
	}

	private Button btnVerPeriodos = new Button();

	public Button getBtnVerPeriodos() {
		return btnVerPeriodos;
	}

	public void setBtnVerPeriodos(Button b) {
		this.btnVerPeriodos = b;
	}

	private ObjectListDataProvider ldpPeriodosAdeudados = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpPeriodosAdeudados() {
		return ldpPeriodosAdeudados;
	}

	public void setLdpPeriodosAdeudados(ObjectListDataProvider oldp) {
		this.ldpPeriodosAdeudados = oldp;
	}

	private ObjectListDataProvider ldpPeriodosAdeudadosAuditoria = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpPeriodosAdeudadosAuditoria() {
		return ldpPeriodosAdeudadosAuditoria;
	}

	public void setLdpPeriodosAdeudadosAuditoria(ObjectListDataProvider ldpPeriodosAdeudadosAuditoria) {
		this.ldpPeriodosAdeudadosAuditoria = ldpPeriodosAdeudadosAuditoria;
	}

	private TableColumn tableColumn9 = new TableColumn();

	public TableColumn getTableColumn9() {
		return tableColumn9;
	}

	public void setTableColumn9(TableColumn tc) {
		this.tableColumn9 = tc;
	}

	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	private TableColumn tableColumn10 = new TableColumn();
	private TableColumn tableColumn17 = new TableColumn();

	public TableColumn getTableColumn17() {
		return tableColumn17;
	}

	public void setTableColumn17(TableColumn tableColumn17) {
		this.tableColumn17 = tableColumn17;
	}

	private TableColumn tableColumn18 = new TableColumn();

	public TableColumn getTableColumn18() {
		return tableColumn18;
	}

	public void setTableColumn18(TableColumn tableColumn18) {
		this.tableColumn18 = tableColumn18;
	}

	private TableColumn tableColumn19 = new TableColumn();

	public TableColumn getTableColumn19() {
		return tableColumn19;
	}

	public void setTableColumn19(TableColumn tableColumn19) {
		this.tableColumn19 = tableColumn19;
	}

	private TableColumn tableColumn20 = new TableColumn();

	public TableColumn getTableColumn20() {
		return tableColumn20;
	}

	public void setTableColumn20(TableColumn tableColumn20) {
		this.tableColumn20 = tableColumn20;
	}

	private TableColumn tableColumn21 = new TableColumn();

	public TableColumn getTableColumn21() {
		return tableColumn21;
	}

	public void setTableColumn21(TableColumn tableColumn21) {
		this.tableColumn21 = tableColumn21;
	}

	private TableColumn tableColumn22 = new TableColumn();

	public TableColumn getTableColumn22() {
		return tableColumn22;
	}

	public void setTableColumn22(TableColumn tableColumn22) {
		this.tableColumn22 = tableColumn22;
	}

	private TableColumn tableColumn16 = new TableColumn();

	public TableColumn getTableColumn10() {
		return tableColumn10;
	}

	public void setTableColumn10(TableColumn tc) {
		this.tableColumn10 = tc;
	}

	private StaticText staticText8 = new StaticText();

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText st) {
		this.staticText8 = st;
	}

	private TableColumn tableColumn11 = new TableColumn();

	public TableColumn getTableColumn11() {
		return tableColumn11;
	}

	public void setTableColumn11(TableColumn tc) {
		this.tableColumn11 = tc;
	}

	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText st) {
		this.staticText9 = st;
	}

	private TableColumn tableColumn12 = new TableColumn();

	public TableColumn getTableColumn12() {
		return tableColumn12;
	}

	public void setTableColumn12(TableColumn tc) {
		this.tableColumn12 = tc;
	}

	private StaticText staticText10 = new StaticText();
	private StaticText staticText11 = new StaticText();

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText staticText11) {
		this.staticText11 = staticText11;
	}

	private StaticText staticText12 = new StaticText();

	public StaticText getStaticText12() {
		return staticText12;
	}

	public void setStaticText12(StaticText staticText12) {
		this.staticText12 = staticText12;
	}

	private StaticText staticText13 = new StaticText();

	public StaticText getStaticText13() {
		return staticText13;
	}

	public void setStaticText13(StaticText staticText13) {
		this.staticText13 = staticText13;
	}

	private StaticText staticText14 = new StaticText();

	public StaticText getStaticText14() {
		return staticText14;
	}

	public void setStaticText14(StaticText staticText14) {
		this.staticText14 = staticText14;
	}

	private StaticText staticText15 = new StaticText();

	public StaticText getStaticText15() {
		return staticText15;
	}

	public void setStaticText15(StaticText staticText15) {
		this.staticText15 = staticText15;
	}

	private StaticText staticText16 = new StaticText();

	public StaticText getStaticText16() {
		return staticText16;
	}

	public void setStaticText16(StaticText staticText16) {
		this.staticText16 = staticText16;
	}

	private StaticText staticText17 = new StaticText();

	public StaticText getStaticText17() {
		return staticText17;
	}

	public void setStaticText17(StaticText staticText17) {
		this.staticText17 = staticText17;
	}

	public TableColumn getTableColumn16() {
		return tableColumn16;
	}

	public void setTableColumn16(TableColumn tableColumn16) {
		this.tableColumn16 = tableColumn16;
	}

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText st) {
		this.staticText10 = st;
	}

	private TableColumn tableColumn15 = new TableColumn();

	public TableColumn getTableColumn15() {
		return tableColumn15;
	}

	public void setTableColumn15(TableColumn tc) {
		this.tableColumn15 = tc;
	}

	private Checkbox checkbox1 = new Checkbox();

	public Checkbox getCheckbox1() {
		return checkbox1;
	}

	public void setCheckbox1(Checkbox c) {
		this.checkbox1 = c;
	}

	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private TableColumn tableColumn3 = new TableColumn();

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tc) {
		this.tableColumn3 = tc;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private TableColumn tableColumn4 = new TableColumn();

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tc) {
		this.tableColumn4 = tc;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public GenerarRefinanciacion() {
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando el �rbol de componentes se ha restaurado, pero antes de que se produzca el procesamiento de
	 * cualquier evento. Este m�todo <strong>s�lo</strong> se llamar� en una petici�n de devoluci�n de env�o que est� procesando el env�o de un formulario.
	 * Puede personalizar este m�todo para asignar recursos necesarios para los controladores de eventos.
	 * </p>
	 */
	public void preprocess() {
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se completa el procesamiento de esta petici�n, si se llam� al m�todo <code>init()</code> (sin
	 * tener en cuenta si se trata de la p�gina que se ha procesado o no). Puede personalizar este m�todo para liberar los recursos adquiridos en los m�todos
	 * <code>init()</code>, <code>preprocess()</code> o <code>prerender()</code> (o durante la ejecuci�n de un controlador de eventos).
	 * </p>
	 */
	public void destroy() {
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		this.getApplicationBean1().aplicarPropiedadesTablaAdmin(this.getTable1(), this.getTableRowGroup1());
		this.getApplicationBean1().aplicarPropiedadesTablaAdmin(this.getTable2(), this.getTableRowGroup2());
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new DocumentoRefinanciacion()); // refinanciacion
		ep.getObjetos().add(ind++, null); // persona
		ep.getObjetos().add(ind++, null); // PlantillaObligacion.TipoObligacion
		ep.getObjetos().add(ind++, new ArrayList()); // lista de obligaciones (Obligacion)
		ep.getObjetos().add(ind++, new ArrayList()); // lista de periodos adeudados (......)
		ep.getObjetos().add(ind++, new AuditoriaTributaria()); // auditoria tributaria seleccionada
		ep.getObjetos().add(ind++, new ArrayList()); // periodosAdeudadosReliquidados

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));

		this.setListaDelCommunication(new ArrayList());
		this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		this.setListaDelCommunicationTabla2(new ArrayList());
		this.getObjectListDataProviderTabla2().setList(this.getListaDelCommunicationTabla2());

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		DocumentoRefinanciacion refinanciacion = (DocumentoRefinanciacion) this.obtenerObjetoDelElementoPila(ind++, DocumentoRefinanciacion.class);
		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		// ASI ESTABA CUANDO ERA ENUM
		// PlantillaObligacion.TipoObligacion tipoObligacion = (PlantillaObligacion.TipoObligacion) this.obtenerObjetoDelElementoPila(ind++,
		// PlantillaObligacion.TipoObligacion.class);
		TipoObligacion tipoObligacion = (TipoObligacion) this.obtenerObjetoDelElementoPila(ind++, TipoObligacion.class);
		ArrayList obligaciones = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		ArrayList periodosAdeudados = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		AuditoriaTributaria auditoriaTributaria = (AuditoriaTributaria) this.obtenerObjetoDelElementoPila(ind++, AuditoriaTributaria.class);
		ArrayList listaPeriodosAdeudadosReliquidados = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		if(persona == null) {
			obligaciones.clear();
			this.setListaDelCommunication(obligaciones);
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());

			periodosAdeudados.clear();
			this.setListaDelCommunicationTabla2(periodosAdeudados);
			this.getObjectListDataProviderTabla2().setList(this.getListaDelCommunicationTabla2());
		}

		tipoObligacion = this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get(ddTipoObligacion.getSelected());

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, refinanciacion);
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, tipoObligacion);
		this.getElementoPila().getObjetos().set(ind++, obligaciones);
		this.getElementoPila().getObjetos().set(ind++, periodosAdeudados);
		this.getElementoPila().getObjetos().set(ind++, auditoriaTributaria);
		this.getElementoPila().getObjetos().set(ind++, listaPeriodosAdeudadosReliquidados);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		ind++; // DocumentoRefinanciacion refinanciacion = (DocumentoRefinanciacion) this.obtenerObjetoDelElementoPila(ind++, DocumentoRefinanciacion.class);
		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		TipoObligacion tipoObligacion = (TipoObligacion) this.obtenerObjetoDelElementoPila(ind++, TipoObligacion.class);
		ind++; // List obligaciones = (List) this.obtenerObjetoDelElementoPila(ind++, List.class);
		ind++; // ArrayList periodosAdeudados = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		AuditoriaTributaria auditoriaTributaria = (AuditoriaTributaria) this.obtenerObjetoDelElementoPila(ind++, AuditoriaTributaria.class);
		List listaPeriodosAdeudadosReliquidados = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		if(persona != null) {
			this.getTfContribuyente().setText(persona.toString());
			cargarObligaciones();
		}

		if(auditoriaTributaria != null && auditoriaTributaria.getIdAuditoriaTributaria() != -1) {
			this.getTfAuditoriaTributaria().setText(auditoriaTributaria.toString());
		}

		if(tipoObligacion != null && tipoObligacion.getIdTipoObligacion() != -1) {
			this.getDdTipoObligacion().setSelected(Util.getEnumNameFromString(String.valueOf(tipoObligacion)));
			this.getDdTipoObligacionDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(tipoObligacion)));
		}

		this.setListaDelCommunicationTabla3(new ArrayList(listaPeriodosAdeudadosReliquidados));
		this.getObjectListDataProviderTabla3().setList(new ArrayList(listaPeriodosAdeudadosReliquidados));

		if(this.getRequestBean1().getObjetoSeleccion() != null && this.getRequestBean1().getObjetoSeleccion() instanceof Persona) {
			this.limpiarTabla2();
		}
	}

	public String cargarObligaciones() {
		String retorno = "";
		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(1, Persona.class);
		TipoObligacion tipoObligacion = (TipoObligacion) this.obtenerObjetoDelElementoPila(2, TipoObligacion.class);

		try {
			this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
			List obligaciones = new ArrayList(this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligaciones(persona, tipoObligacion, null, null, null));

			tablePhaseListener.clear();
			tablePhaseListenerTabla2.clear();

			this.setListaDelCommunication(obligaciones);
			this.getObjectListDataProvider().setList(obligaciones);

			if(obligaciones.size() == 1) {
				List obligacionesSeleccionadas = new ArrayList();
				Obligacion o = (Obligacion) obligaciones.get(0);
				obligacionesSeleccionadas.add(o);

				this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().setLlave(this.getSessionBean1().getLlave());
				List periodosAdeudados = this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().getListaDeudasContribuyente(obligacionesSeleccionadas);

				ordenarPeriodosAdeudados(periodosAdeudados);

				this.getElementoPila().getObjetos().set(4, periodosAdeudados);
				this.getObjectListDataProviderTabla2().setList(periodosAdeudados);
				this.setListaDelCommunicationTabla2(new ArrayList(periodosAdeudados));

				if(periodosAdeudados.size() == 1) {
					this.guardarEstadoObjetosUsados();

					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoABM(periodosAdeudados);

					retorno = "AgregarPlanPagoRefinanciacion";
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			error("Generar Refinanciaci\363n" + ": No se pudo recuperar la lista de Obligaciones.");
		}

		// ArrayList periodosAdeudados = (ArrayList) this.obtenerObjetoDelElementoPila(4, ArrayList.class);
		// this.setListaDelCommunicationTabla2(periodosAdeudados);
		// this.getObjectListDataProviderTabla2().setList(periodosAdeudados);

		return retorno;
	}

	@SuppressWarnings("unchecked")
	private void ordenarPeriodosAdeudados(List pListadoPeriodos) {
		Collections.sort(pListadoPeriodos, new Comparator<LiquidacionTasa>() {

			public int compare(LiquidacionTasa l1, LiquidacionTasa l2) {
				return l1.getNombre().compareTo(l2.getNombre());
			}
		});
	}

	// PARA TABLA 1
	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpObligaciones();
	}

	private List getListaDelCommunication() {
		return this.getCommunicationExcepcionesBean().getListaObligacionesRefinanciacion();
	}

	private void setListaDelCommunication(List lista) {
		this.getCommunicationExcepcionesBean().setListaObligacionesRefinanciacion(lista);
	}

	private TableSelectPhaseListener getTableSelectPhaseListener() {
		return this.getCommunicationExcepcionesBean().getTablePhaseListenerObligacionesRefinanciacion();
	}

	// PARA TABLA 2
	private ObjectListDataProvider getObjectListDataProviderTabla2() {
		return this.getLdpPeriodosAdeudados();
	}

	private ArrayList getListaDelCommunicationTabla2() {
		return this.getCommunicationExcepcionesBean().getListaPeriodosAdeudadosRefinanciacion();
	}

	private void setListaDelCommunicationTabla2(ArrayList lista) {
		this.getCommunicationExcepcionesBean().setListaPeriodosAdeudadosRefinanciacion(lista);
	}

	private TableSelectPhaseListener getTableSelectPhaseListenerTabla2() {
		return this.getCommunicationExcepcionesBean().getTablePhaseListenerPeriodosAdeudadosRefinanciacion();
	}

	// PARA TABLA 3
	private ObjectListDataProvider getObjectListDataProviderTabla3() {
		return this.getLdpPeriodosAdeudadosAuditoria();
	}

	private ArrayList getListaDelCommunicationTabla3() {
		return this.getCommunicationExcepcionesBean().getListaPeriodosAdeudadosAuditoria();
	}

	private void setListaDelCommunicationTabla3(ArrayList lista) {
		this.getCommunicationExcepcionesBean().setListaPeriodosAdeudadosAuditoria(lista);
	}

	// private void actualizarTablaObligaciones() throws Exception {
	// // CAMBIAR:
	// ind = 0;
	// persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
	// tipoObligacion = (PlantillaObligacion.TipoObligacion) this.obtenerObjetoDelElementoPila(ind++, PlantillaObligacion.TipoObligacion.class);
	// obligaciones = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
	//
	// try {
	// this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
	// obligaciones = new ArrayList(this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligaciones(persona, tipoObligacion, null));
	// }
	// catch (Exception ex) {
	// ex.printStackTrace();
	// error(NOMBRE_PAGINA + ": No se pudo recuperar la lista de Obligaciones.");
	// }
	// }
	// private void refrescarTabla() throws Exception {
	//
	// Rol rol = (Rol) this.obtenerObjetoDelElementoPila(0, Rol.class);
	// if (rol != null && rol.getIdRol() == -1) rol = null;
	//
	// this.getComunicationBean().getRemoteSystemRol().setLlave(this.getSessionBean1().getLlave());
	// this.setListaDelCommunication((ArrayList) this.getComunicationBean().getRemoteSystemRol().getListaPermisosPorRol(rol));
	// this.getObjectListDataProvider().setList(this.getListaDelCommunication());
	// }

	@SuppressWarnings("unused")
	private void limpiarTabla() {
		this.getObjectListDataProvider().getList().clear();
	}

	private void limpiarTabla2() {
		this.getObjectListDataProviderTabla2().getList().clear();
	}

	private RowKey rowKeySeleccionado = null;

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	public RowKey getRowKeyAsociado(Long posicionEnTabla) {
		RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];

		return rk;
	}

	/*
	 * public void seleccionarFila(Long posicionGlobal) { long cantFilas = this.getTableRowGroup1().getRowCount();
	 * 
	 * if (cantFilas > 0) { // si hay al menos una fila if (posicionGlobal.longValue() >= cantFilas) { // si elimine la ultima fila, me posiciono en la anterior
	 * posicionGlobal = new Long(cantFilas - 1); };
	 * 
	 * int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
	 * this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue()); lastSelected = new Long(index).toString(); } }
	 */

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch(Exception ex) {
		}

		return rk;
	}

	// TABLA 1
	private TableSelectPhaseListener tablePhaseListener = new TableSelectPhaseListener();

	public void setSelected(Object object) {
		RowKey rowKey = tableRowGroup1.getRowKey();
		if(rowKey != null) {
			tablePhaseListener.setSelected(rowKey, object);
		}
	}

	public Object getSelected() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		return tablePhaseListener.getSelected(rowKey);
	}

	public Object getSelectedValue() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		return (rowKey != null) ? rowKey.getRowId() : null;
	}

	public boolean isCurrentRowSelected() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		return tablePhaseListener.isSelected(rowKey);
	}

	// TABLA 2
	private TableSelectPhaseListener tablePhaseListenerTabla2 = new TableSelectPhaseListener();

	public void setSelectedTabla2(Object object) {
		RowKey rowKey = tableRowGroup2.getRowKey();
		if(rowKey != null) {
			tablePhaseListenerTabla2.setSelected(rowKey, object);
		}
	}

	public Object getSelectedTabla2() {
		RowKey rowKey = tableRowGroup2.getRowKey();
		return tablePhaseListenerTabla2.getSelected(rowKey);
	}

	public Object getSelectedValueTabla2() {
		RowKey rowKey = tableRowGroup2.getRowKey();
		return (rowKey != null) ? rowKey.getRowId() : null;
	}

	public boolean isCurrentRowSelectedTabla2() {
		RowKey rowKey = tableRowGroup2.getRowKey();
		return tablePhaseListenerTabla2.isSelected(rowKey);
	}

	// TABLA 3
	private TableSelectPhaseListener tablePhaseListenerTabla3 = new TableSelectPhaseListener();

	public void setSelectedTabla3(Object object) {
		RowKey rowKey = tableRowGroup3.getRowKey();
		if(rowKey != null) {
			tablePhaseListenerTabla3.setSelected(rowKey, object);
		}
	}

	public Object getSelectedTabla3() {
		RowKey rowKey = tableRowGroup3.getRowKey();
		return tablePhaseListenerTabla3.getSelected(rowKey);
	}

	public Object getSelectedValueTabla3() {
		RowKey rowKey = tableRowGroup3.getRowKey();
		return (rowKey != null) ? rowKey.getRowId() : null;
	}

	public boolean isCurrentRowSelectedTabla3() {
		RowKey rowKey = tableRowGroup3.getRowKey();
		return tablePhaseListenerTabla3.isSelected(rowKey);
	}

	public String btnSeleccionarPersonaFisica_action() {
		return navegarParaSeleccionar("AdminPersonaFisica", 1);
	}

	public String btnSeleccionarPersonaJuridica_action() {
		return navegarParaSeleccionar("AdminPersonaJuridica", 1);
	}

	public void valueChangeEventDDTipoObligacion(ValueChangeEvent vce) {
		this.getElementoPila().getObjetos().set(2, vce.getNewValue());

		return;
	}

	public String btnSeleccionarAuditoriaTributaria_action() {
		return navegarParaSeleccionar("AdminAuditoriaTributaria", 5);
	}

	public String btnLimpiarAuditoriaTributaria_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(5, AuditoriaTributaria.class, this.getTfAuditoriaTributaria());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnVerPeriodos_action() {
		// TODO: Buscar las deudas de las obligaciones seleccionadas.
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				this.guardarEstadoObjetosUsados();

				// ArrayList obligaciones = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
				List obligacionesSeleccionadas = new ArrayList();
				RowKey[] selectedRowKeys = getTableRowGroup1().getSelectedRowKeys();

				if(selectedRowKeys.length > 0) {
					for(int i = 0; i < selectedRowKeys.length; i++) {
						String rowId = selectedRowKeys[i].getRowId();
						RowKey rowKey = this.getObjectListDataProvider().getRowKey(rowId);
						// Object obj = this.getObjectListDataProvider().getObjects()[getNroFila(rowKey.toString())];
						Object obj = this.getObjectListDataProvider().getObjects()[Integer.valueOf(rowKey.getRowId()).intValue()];

						Obligacion o = (Obligacion) obj;
						obligacionesSeleccionadas.add(o);
					}

					this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().setLlave(this.getSessionBean1().getLlave());
					List periodosAdeudados = this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().getListaDeudasContribuyente(obligacionesSeleccionadas);

					ordenarPeriodosAdeudados(periodosAdeudados);

					this.getElementoPila().getObjetos().set(4, periodosAdeudados);
					this.getObjectListDataProviderTabla2().setList(periodosAdeudados);
					this.setListaDelCommunicationTabla2(new ArrayList(periodosAdeudados));

					if(periodosAdeudados.size() == 1) {
						this.guardarEstadoObjetosUsados();

						this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
						this.getRequestBean1().setObjetoABM(periodosAdeudados);

						retorno = "AgregarPlanPagoRefinanciacion";
					}
				} else {
					this.getObjectListDataProviderTabla2().setList(new ArrayList());
					this.setListaDelCommunicationTabla2(new ArrayList());
					retorno = null;
					warn("Seleccione al menos una Obligaci\363n.");
				}
			} catch(Exception ex) {
				log("GenerarRefinanciacion", ex);
				error("Generar Refinanciaci\363n" + " - " + this.btnVerPeriodos.getText() + ":" + ex.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnArmarPlanDePago_action() {
		// TODO: ir a ArmarPlanPagoRefinanciacion con la lista de deudas seleccionadas.
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			try {
				this.guardarEstadoObjetosUsados();

				retorno = "AgregarPlanPagoRefinanciacion";

				// ArrayList periodosAdeudados = (ArrayList) this.obtenerObjetoDelElementoPila(4, ArrayList.class);
				List periodosSeleccionados = new ArrayList();
				// ArrayList obligaciones = (ArrayList) this.obtenerObjetoDelElementoPila(2, ArrayList.class);
				// List obligacionesSeleccionadas = new ArrayList();
				RowKey[] selectedRowKeys = getTableRowGroup2().getSelectedRowKeys();

				if(selectedRowKeys.length > 0) {
					for(int i = 0; i < selectedRowKeys.length; i++) {
						String rowId = selectedRowKeys[i].getRowId();
						RowKey rowKey = this.getObjectListDataProviderTabla2().getRowKey(rowId);
						// Object obj = this.getObjectListDataProvider().getObjects()[getNroFila(rowKey.toString())];
						Object obj = this.getObjectListDataProviderTabla2().getObjects()[Integer.valueOf(rowKey.getRowId()).intValue()];

						LiquidacionTasa o = (LiquidacionTasa) obj;
						periodosSeleccionados.add(o);
					}

					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoABM(periodosSeleccionados);

					// this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().setLlave(this.getSessionBean1().getLlave());
					// List periodosAdeudados =
					// this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().getListaDeudasContribuyente(obligacionesSeleccionadas);
					// this.getElementoPila().getObjetos().set(3, periodosAdeudados);
				} else {
					retorno = null;
					warn("Seleccione al menos un Período Adeudado.");
				}
			} catch(Exception ex) {
				log("GenerarRefinanciacion", ex);
				error("Generar Refinanciaci\363n" + " - " + this.btnVerPeriodos.getText() + ":" + ex.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnArmarPlanDePagoAuditoria_action() {
		// TODO: ir a ArmarPlanPagoRefinanciacion con la lista de deudas seleccionadas.
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			try {
				this.guardarEstadoObjetosUsados();

				retorno = "AgregarPlanPagoRefinanciacion";

				AuditoriaTributaria at = (AuditoriaTributaria) this.obtenerObjetoDelElementoPila(5, AuditoriaTributaria.class);
				ArrayList listaPeriodosAdeudadosReliquidados = (ArrayList) this.obtenerObjetoDelElementoPila(6, ArrayList.class);
				if(at == null || at.getIdAuditoriaTributaria() == -1) {
					warn("Seleccione una Auditoria para Refinanciar.");
					return null;
				}

				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				this.getRequestBean1().setObjetoABM(listaPeriodosAdeudadosReliquidados);
				this.getRequestBean1().setRespuestaABM(at);
			} catch(Exception ex) {
				log("GenerarRefinanciacion", ex);
				error("Generar Refinanciaci\363n" + " - " + this.btnVerPeriodos.getText() + ":" + ex.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	@SuppressWarnings("unused")
	private void actualizarTablaPeriodosAdeudados() throws Exception {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;
			this.guardarEstadoObjetosUsados();

			ArrayList obligaciones = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
			ArrayList periodosAdeudados = (ArrayList) this.obtenerObjetoDelElementoPila(4, ArrayList.class);

			try {
				// Inicializo el Array de objetos seleccionados
				if(this.getRequestBean1().getObjetosSeleccionMultiple() != null) {
					this.getRequestBean1().getObjetosSeleccionMultiple().clear();
				} else {
					this.getRequestBean1().setObjetosSeleccionMultiple(new ArrayList());
				}

				RowKey[] selectedRowKeys = getTableRowGroup1().getSelectedRowKeys();

				for(int i = 0; i < selectedRowKeys.length; i++) {
					String rowId = selectedRowKeys[i].getRowId();
					// RowKey rowKey = this.getObjectListDataProvider().getRowKey(rowId);
					Object obj = this.getObjectListDataProvider().getObjects()[Integer.parseInt(rowId)];
					this.getRequestBean1().getObjetosSeleccionMultiple().add(obj);
				}

				// descomentar cuando este listo
				// periodosAdeudados = new ArrayList(
				// this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findPeriodosAdeudados(this.getRequestBean1().getObjetosSeleccionMultiple())
				// );
			} catch(Exception ex) {
				log("GenerarRefinanciacion" + "_SeleccionarMultiplesObjetosError:", ex);
				error("Generar Refinanciaci\363n" + " - Seleccionar M\372ltiples Objetos: " + ex.getMessage());
			}
			// if (!this.getRequestBean1().getObjetosSeleccionMultiple().isEmpty()) retorno = this.prepararParaVolver(Constantes.ACCION_SELECCIONAR);
		} else {
			retorno = this.prepararCaducidad();
		}

		return;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// obtengo la auditoria solo para reliquidar sus reg deuda y guardarlos en una lista q mostrare en la tabla
		if(pObject instanceof Persona) {
			Persona persona = (Persona) pObject;
			// List listaObligaciones = null;
			// try {
			// this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().setLlave(this.getSessionBean1().getLlave());
			// listaObligaciones = this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().getListaObligacionesContribuyente(persona, null);
			//
			// this.setListaDelCommunication(listaObligaciones);
			// this.getObjectListDataProvider().setList(listaObligaciones);
			// } catch(RemoteException e) {
			// e.printStackTrace();
			// } catch(TrascenderException e) {
			// e.printStackTrace();
			// }

			this.getElementoPila().getObjetos().set(1, persona);
			// this.getElementoPila().getObjetos().set(3, listaObligaciones);
		}
		if(pObject instanceof AuditoriaTributaria) {
			List periodosAdeudadosAuditoria = new ArrayList();
			AuditoriaTributaria auditoriaTributaria = null;
			List listaPeriodosAdeudadosReliquidados = new ArrayList();

			auditoriaTributaria = (AuditoriaTributaria) pObject;
			if(auditoriaTributaria.getEstado().equals(AuditoriaTributaria.EstadoAuditoriaTributaria.PROVISORIO)) {

				// //////////////////////reliquido reg deuda////////////////////
				// List periodosAdeudadosAuditoria = new ArrayList();
				List listaObligaciones = new ArrayList();

				for(Iterator it = auditoriaTributaria.getListaRegistroDeuda().iterator(); it.hasNext();) {
					RegistroDeuda registroDeuda = (RegistroDeuda) it.next();
					if(!listaObligaciones.contains(registroDeuda.getDocGeneradorDeuda().getObligacion())) {
						listaObligaciones.add(registroDeuda.getDocGeneradorDeuda().getObligacion());
					}
				}
				try {
					this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().setLlave(this.getSessionBean1().getLlave());
					periodosAdeudadosAuditoria = this.getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().getListaDeudasContribuyente(listaObligaciones);
				} catch(Exception ex) {
					ex.printStackTrace();
					error("Generar Refinanciaci\363n" + ": No se pudo recuperar la lista de Obligaciones.");
				}

				for(Iterator it = periodosAdeudadosAuditoria.iterator(); it.hasNext();) {
					RegistroDeuda registroDeudaR = (RegistroDeuda) it.next();
					for(Iterator it1 = auditoriaTributaria.getListaRegistroDeuda().iterator(); it1.hasNext();) {
						RegistroDeuda registroDeudaA = (RegistroDeuda) it1.next();
						if(registroDeudaA.getIdRegistroDeuda() == registroDeudaR.getIdRegistroDeuda()) {
							listaPeriodosAdeudadosReliquidados.add(registroDeudaR);
						}
					}
				}

				this.getElementoPila().getObjetos().set(5, auditoriaTributaria);
				this.getElementoPila().getObjetos().set(6, listaPeriodosAdeudadosReliquidados);
				this.getRequestBean1().setObjetoSeleccion(null);
			} else {
				warn("La Auditoria Tributaria debe estar en Estado 'Provisorio'.");
				this.getRequestBean1().setObjetoSeleccion(null);
			}
		}
	}

	public String btnLimpiarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(1, this.getTfContribuyente());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		DocumentoRefinanciacion refinanciacion = (DocumentoRefinanciacion) pObject;

		this.getElementoPila().getObjetos().set(0, refinanciacion);
	}

	@Override
	protected String getCasoNavegacion() {
		return "GenerarRefinanciacion";
	}

	@Override
	public String getNombreBean() {
		return "#{excepciones$ABMRefinanciacion$GenerarRefinanciacion}";
	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoRefinanciacion.serialVersionUID;
	}

	public void setPersonaAutocompletar(String pId, String pIdSubSesion) throws Exception {
		Long id = Long.parseLong(pId);
		Persona contribuyente = null;

		try {
			contribuyente = this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		this.setIdSubSesion(Long.parseLong(pIdSubSesion));
		this.getElementoPila().getObjetos().set(1, contribuyente);
	}

	public boolean isHayPersona() {
		Persona locContribuyente = (Persona) this.obtenerObjetoDelElementoPila(1);

		return(locContribuyente != null && locContribuyente.getIdPersona() != -1);
	}

}