package muni.catastro.ABMParcela;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.RadioButtonGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.persistent.EstadisticasIndec;
import com.trascender.catastro.recurso.persistent.FirmantePlanoConstruccion;
import com.trascender.catastro.recurso.persistent.PlanoConstruccion;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

public class ABMPlanoConstruccion extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
	
		Option[] opEstadoPlanoConstruccion = null;
		opEstadoPlanoConstruccion = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(PlanoConstruccion.Estado.values(), "cap");
		rbgEstadoPlanoConstruccionOptions.setOptions(opEstadoPlanoConstruccion);
		
		Option[] op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(FirmantePlanoConstruccion.Cargo.values(), "may");
		ddCargoSocietarioDefaultOptions.setOptions(op);
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		PlanoConstruccion planoConstruccion = this.obtenerObjetoDelElementoPila(0, PlanoConstruccion.class);
		this.getTablaLogs().getLdpLogs().setList(planoConstruccion.getListaLogsAuditoria());
		this.guardarEstadoObjetosUsados();
	}
	
	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpCargos();
	}

	private List getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaCargosPlanoConstruccion();
	}

	private void setListaDelCommunication(List lista) {
		this.getComunicationCatastroBean().setListaCargosPlanoConstruccion(lista);
	}
	
	private Label label69 = new Label();
	private Label label70 = new Label();
	private Label label74 = new Label();
	private Label label75 = new Label();
	private Label label77 = new Label();
	private Label label78 = new Label();
	private Label label79 = new Label();
	private Label lblComentario2 = new Label();
	private Label lblSuperficieCubiertaPlantaBaja = new Label();
	private Label lblSuperficieCubiertaPlantaAlta = new Label();
	private Label lblSuperficieSemiCubiertaPlantaBaja = new Label();
	private Label lblSuperficieSemiCubiertaPlantaAlta = new Label();
	private Label lblNroPlantas = new Label();
	private Label lblSuperficieCubiertaSemicubierta = new Label();
	private Label lblEstadisticasIndec = new Label();

	private TextField tfFechaPlanoConstruccion = new TextField();
	private TextField tfNroExpedientePlanoConstruccion = new TextField();
	private TextField tfSuperficieCubiertaPlantaBaja = new TextField();
	private TextField tfSuperficieCubiertaPlantaAlta = new TextField();
	private TextField tfSuperficieSemiCubiertaPlantaBaja = new TextField();
	private TextField tfSuperficieSemiCubiertaPlantaAlta = new TextField();
	private TextField tfNroPlantas = new TextField();
	private TextField tfFechaFirma = new TextField();
	private TextField tfEstadisticasIndec = new TextField();
	
	private Button btnSeleccionarEstadisticaIndec = new Button();
	private HtmlAjaxCommandButton btnLimpiarEstadisticaIndec = new HtmlAjaxCommandButton();
	
	private TextArea taComentario2 = new TextArea();
	
	private RadioButtonGroup rbgEstadoPlanoConstruccion = new RadioButtonGroup();
	private SingleSelectOptionsList rbgEstadoPlanoConstruccionOptions = new SingleSelectOptionsList();
	
	private PanelAtributoDinamico panelAtributoDinamicoPlanoConstruccion = new PanelAtributoDinamico();
	
	public Label getLabel79() {
		return label79;
	}

	public void setLabel79(Label label79) {
		this.label79 = label79;
	}

	public Label getLblEstadisticasIndec() {
		return lblEstadisticasIndec;
	}

	public void setLblEstadisticasIndec(Label lblEstadisticasIndec) {
		this.lblEstadisticasIndec = lblEstadisticasIndec;
	}

	public TextField getTfEstadisticasIndec() {
		return tfEstadisticasIndec;
	}

	public void setTfEstadisticasIndec(TextField tfEstadisticasIndec) {
		this.tfEstadisticasIndec = tfEstadisticasIndec;
	}

	public Button getBtnSeleccionarEstadisticaIndec() {
		return btnSeleccionarEstadisticaIndec;
	}

	public void setBtnSeleccionarEstadisticaIndec(
			Button btnSeleccionarEstadisticaIndec) {
		this.btnSeleccionarEstadisticaIndec = btnSeleccionarEstadisticaIndec;
	}

	public HtmlAjaxCommandButton getBtnLimpiarEstadisticaIndec() {
		return btnLimpiarEstadisticaIndec;
	}

	public void setBtnLimpiarEstadisticaIndec(
			HtmlAjaxCommandButton btnLimpiarEstadisticaIndec) {
		this.btnLimpiarEstadisticaIndec = btnLimpiarEstadisticaIndec;
	}

	public TextField getTfFechaFirma() {
		return tfFechaFirma;
	}

	public void setTfFechaFirma(TextField tfFechaFirma) {
		this.tfFechaFirma = tfFechaFirma;
	}

	public Label getLblSuperficieCubiertaSemicubierta() {
		return lblSuperficieCubiertaSemicubierta;
	}

	public void setLblSuperficieCubiertaSemicubierta(
			Label lblSuperficieCubiertaSemicubierta) {
		this.lblSuperficieCubiertaSemicubierta = lblSuperficieCubiertaSemicubierta;
	}

	public Label getLblSuperficieCubiertaPlantaBaja() {
		return lblSuperficieCubiertaPlantaBaja;
	}

	public void setLblSuperficieCubiertaPlantaBaja(
			Label lblSuperficieCubiertaPlantaBaja) {
		this.lblSuperficieCubiertaPlantaBaja = lblSuperficieCubiertaPlantaBaja;
	}

	public Label getLblSuperficieCubiertaPlantaAlta() {
		return lblSuperficieCubiertaPlantaAlta;
	}

	public void setLblSuperficieCubiertaPlantaAlta(
			Label lblSuperficieCubiertaPlantaAlta) {
		this.lblSuperficieCubiertaPlantaAlta = lblSuperficieCubiertaPlantaAlta;
	}

	public Label getLblSuperficieSemiCubiertaPlantaBaja() {
		return lblSuperficieSemiCubiertaPlantaBaja;
	}

	public void setLblSuperficieSemiCubiertaPlantaBaja(
			Label lblSuperficieSemiCubiertaPlantaBaja) {
		this.lblSuperficieSemiCubiertaPlantaBaja = lblSuperficieSemiCubiertaPlantaBaja;
	}

	public Label getLblSuperficieSemiCubiertaPlantaAlta() {
		return lblSuperficieSemiCubiertaPlantaAlta;
	}

	public void setLblSuperficieSemiCubiertaPlantaAlta(
			Label lblSuperficieSemiCubiertaPlantaAlta) {
		this.lblSuperficieSemiCubiertaPlantaAlta = lblSuperficieSemiCubiertaPlantaAlta;
	}

	public Label getLblNroPlantas() {
		return lblNroPlantas;
	}

	public void setLblNroPlantas(Label lblNroPlantas) {
		this.lblNroPlantas = lblNroPlantas;
	}

	public TextField getTfSuperficieCubiertaPlantaBaja() {
		return tfSuperficieCubiertaPlantaBaja;
	}

	public void setTfSuperficieCubiertaPlantaBaja(
			TextField tfSuperficieCubiertaPlantaBaja) {
		this.tfSuperficieCubiertaPlantaBaja = tfSuperficieCubiertaPlantaBaja;
	}

	public TextField getTfSuperficieCubiertaPlantaAlta() {
		return tfSuperficieCubiertaPlantaAlta;
	}

	public void setTfSuperficieCubiertaPlantaAlta(
			TextField tfSuperficieCubiertaPlantaAlta) {
		this.tfSuperficieCubiertaPlantaAlta = tfSuperficieCubiertaPlantaAlta;
	}

	public TextField getTfSuperficieSemiCubiertaPlantaBaja() {
		return tfSuperficieSemiCubiertaPlantaBaja;
	}

	public void setTfSuperficieSemiCubiertaPlantaBaja(
			TextField tfSuperficieSemiCubiertaPlantaBaja) {
		this.tfSuperficieSemiCubiertaPlantaBaja = tfSuperficieSemiCubiertaPlantaBaja;
	}

	public TextField getTfSuperficieSemiCubiertaPlantaAlta() {
		return tfSuperficieSemiCubiertaPlantaAlta;
	}

	public void setTfSuperficieSemiCubiertaPlantaAlta(
			TextField tfSuperficieSemiCubiertaPlantaAlta) {
		this.tfSuperficieSemiCubiertaPlantaAlta = tfSuperficieSemiCubiertaPlantaAlta;
	}

	public TextField getTfNroPlantas() {
		return tfNroPlantas;
	}

	public void setTfNroPlantas(TextField tfNroPlantas) {
		this.tfNroPlantas = tfNroPlantas;
	}

	public Label getLabel69() {
		return label69;
	}

	public void setLabel69(Label label69) {
		this.label69 = label69;
	}

	public Label getLabel70() {
		return label70;
	}

	public void setLabel70(Label label70) {
		this.label70 = label70;
	}

	public Label getLabel74() {
		return label74;
	}

	public void setLabel74(Label label74) {
		this.label74 = label74;
	}

	public Label getLabel75() {
		return label75;
	}

	public void setLabel75(Label label75) {
		this.label75 = label75;
	}

	public Label getLabel77() {
		return label77;
	}

	public void setLabel77(Label label77) {
		this.label77 = label77;
	}

	public Label getLabel78() {
		return label78;
	}

	public void setLabel78(Label label78) {
		this.label78 = label78;
	}

	public Label getLblComentario2() {
		return lblComentario2;
	}

	public void setLblComentario2(Label lblComentario2) {
		this.lblComentario2 = lblComentario2;
	}

	public TextField getTfFechaPlanoConstruccion() {
		return tfFechaPlanoConstruccion;
	}

	public void setTfFechaPlanoConstruccion(TextField tfFechaPlanoConstruccion) {
		this.tfFechaPlanoConstruccion = tfFechaPlanoConstruccion;
	}

	public TextField getTfNroExpedientePlanoConstruccion() {
		return tfNroExpedientePlanoConstruccion;
	}

	public void setTfNroExpedientePlanoConstruccion(
			TextField tfNroExpedientePlanoConstruccion) {
		this.tfNroExpedientePlanoConstruccion = tfNroExpedientePlanoConstruccion;
	}

	public TextArea getTaComentario2() {
		return taComentario2;
	}

	public void setTaComentario2(TextArea taComentario2) {
		this.taComentario2 = taComentario2;
	}

	public RadioButtonGroup getRbgEstadoPlanoConstruccion() {
		return rbgEstadoPlanoConstruccion;
	}

	public void setRbgEstadoPlanoConstruccion(
			RadioButtonGroup rbgEstadoPlanoConstruccion) {
		this.rbgEstadoPlanoConstruccion = rbgEstadoPlanoConstruccion;
	}

	public SingleSelectOptionsList getRbgEstadoPlanoConstruccionOptions() {
		return rbgEstadoPlanoConstruccionOptions;
	}

	public void setRbgEstadoPlanoConstruccionOptions(
			SingleSelectOptionsList rbgEstadoPlanoConstruccionOptions) {
		this.rbgEstadoPlanoConstruccionOptions = rbgEstadoPlanoConstruccionOptions;
	}

	public PanelAtributoDinamico getPanelAtributoDinamicoPlanoConstruccion() {
		return panelAtributoDinamicoPlanoConstruccion;
	}

	public void setPanelAtributoDinamicoPlanoConstruccion(
			PanelAtributoDinamico panelAtributoDinamicoPlanoConstruccion) {
		this.panelAtributoDinamicoPlanoConstruccion = panelAtributoDinamicoPlanoConstruccion;
	}
	
	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private ObjectListDataProvider ldpCargos = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpCargos() {
		return ldpCargos;
	}

	public void setLdpCargos(ObjectListDataProvider oldp) {
		this.ldpCargos = oldp;
	}

	private Table table1 = new Table();

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}
	
	private StaticText staticText1 = new StaticText();
	private StaticText staticText4 = new StaticText();
	
	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText staticText4) {
		this.staticText4 = staticText4;
	}

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tableColumn2 = new TableColumn();
	private TableColumn tableColumn4 = new TableColumn();
	private TableColumn tableColumn5 = new TableColumn();
	
	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tableColumn5) {
		this.tableColumn5 = tableColumn5;
	}

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tableColumn4) {
		this.tableColumn4 = tableColumn4;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}
	
	private RadioButton radioButton1 = new RadioButton();
	
	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	private DropDown listaCargos = new DropDown();

	public DropDown getListaCargos() {
		return listaCargos;
	}

	public void setListaCargos(DropDown listaCargos) {
		this.listaCargos = listaCargos;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}
	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}
	
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
		if (selected != null) {
			lastSelected = selected;
		}
	}
	
	private DropDown ddCargo = new DropDown();
	private SingleSelectOptionsList ddCargoSocietarioDefaultOptions = new SingleSelectOptionsList();
	
	public DropDown getDdCargo() {
		return ddCargo;
	}

	public void setDdCargo(DropDown ddCargo) {
		this.ddCargo = ddCargo;
	}

	public SingleSelectOptionsList getDdCargoSocietarioDefaultOptions() {
		return ddCargoSocietarioDefaultOptions;
	}

	public void setDdCargoSocietarioDefaultOptions(
			SingleSelectOptionsList ddCargoSocietarioDefaultOptions) {
		this.ddCargoSocietarioDefaultOptions = ddCargoSocietarioDefaultOptions;
	}

	private Button btnAgregar = new Button();
	private Button btnAgregarPJ = new Button();
	protected HtmlAjaxCommandButton btnQuitarTodos = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitar = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnQuitarTodos() {
		return btnQuitarTodos;
	}

	public void setBtnQuitarTodos(HtmlAjaxCommandButton btnQuitarTodos) {
		this.btnQuitarTodos = btnQuitarTodos;
	}

	public HtmlAjaxCommandButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(HtmlAjaxCommandButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public Button getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(Button b) {
		this.btnAgregar = b;
	}

	public Button getBtnAgregarPJ() {
		return btnAgregarPJ;
	}

	public void setBtnAgregarPJ(Button btnAgregarPJ) {
		this.btnAgregarPJ = btnAgregarPJ;
	}
	
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		
		ep.getObjetos().add(ind++, new PlanoConstruccion()); 
		ep.getObjetos().add(ind++, new ArrayList()); // AtributosDinamicosPlanoConstruccion
		ep.getObjetos().add(ind++, new ArrayList()); // listafirmantesPlanoConstruccion
		ep.getObjetos().add(ind++, new EstadisticasIndec()); // EstadisticasIndec
		
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		PlanoConstruccion planoConstruccion = this.obtenerObjetoDelElementoPila(ind++, PlanoConstruccion.class);
		List atributosDinamicosPlanoConstruccion = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		List listaFirmantesPlanoConstruccion = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		EstadisticasIndec estadisticasIndec = (EstadisticasIndec) this.obtenerObjetoDelElementoPila(ind++, EstadisticasIndec.class);

		planoConstruccion.setFechaInscripcion(this.getTextFieldValueDate(getTfFechaPlanoConstruccion()));
		planoConstruccion.setNroExpediente(getTextFieldValue(getTfNroExpedientePlanoConstruccion()));
		planoConstruccion.setEstado(this.getRBGEnumValue(getRbgEstadoPlanoConstruccion(), PlanoConstruccion.Estado.class));
		planoConstruccion.setComentario(this.getTextAreaValue(getTaComentario2()));
		planoConstruccion.setSuperficieCubiertaPlantaAlta(this.getTextFieldValueDouble(getTfSuperficieCubiertaPlantaAlta()));
		planoConstruccion.setSuperficieSemiCubiertaPlantaAlta(this.getTextFieldValueDouble(getTfSuperficieSemiCubiertaPlantaAlta()));
		planoConstruccion.setSuperficieCubiertaPlantaBaja(this.getTextFieldValueDouble(getTfSuperficieCubiertaPlantaBaja()));
		planoConstruccion.setSuperficieSemiCubiertaPlantaBaja(this.getTextFieldValueDouble(getTfSuperficieSemiCubiertaPlantaBaja()));
		planoConstruccion.setNroPlantas(this.getTextFieldValueInteger(getTfNroPlantas()));
		
		atributosDinamicosPlanoConstruccion = panelAtributoDinamicoPlanoConstruccion.obtenerListaAtributosDinamicos(atributosDinamicosPlanoConstruccion);
		planoConstruccion.setListaAtributosDinamicos(atributosDinamicosPlanoConstruccion);
		
		this.getObjectListDataProvider().commitChanges();
		listaFirmantesPlanoConstruccion = this.getObjectListDataProvider().getList();
		this.setListaDelCommunication(listaFirmantesPlanoConstruccion);
		planoConstruccion.setListaFirmantePlanoConstruccion(listaFirmantesPlanoConstruccion);
		planoConstruccion.setEstadisticasIndec(estadisticasIndec);
		
		this.getElementoPila().getObjetos().set(0, planoConstruccion);
		this.getElementoPila().getObjetos().set(1, atributosDinamicosPlanoConstruccion);
		this.getElementoPila().getObjetos().set(2, listaFirmantesPlanoConstruccion);
		this.getElementoPila().getObjetos().set(3, estadisticasIndec);
		
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		PlanoConstruccion locPlanoConstruccion = this.obtenerObjetoDelElementoPila(ind++, PlanoConstruccion.class);
		
		try {
			if(locPlanoConstruccion != null) {
				this.getElementoPila()
						.getObjetos()
						.set(1,
								this.getComunicationBean().getRemoteSystemParametro()
										.getAtributosPorRecurso(PlanoConstruccion.serialVersionUID, locPlanoConstruccion.getListaAtributosDinamicos(), null));
			} else {
				this.getElementoPila().getObjetos()
						.set(1, this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(PlanoConstruccion.serialVersionUID, null, null));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		ArrayList atributosDinamicosPlanoConstruccion = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		List listaFirmantesPlanoConstruccion = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		EstadisticasIndec estadisticasIndec = (EstadisticasIndec) this.obtenerObjetoDelElementoPila(ind++, EstadisticasIndec.class);
		
		this.getObjectListDataProvider().setList(listaFirmantesPlanoConstruccion);
		this.setListaDelCommunication(listaFirmantesPlanoConstruccion);
		
		if(locPlanoConstruccion != null) {

			if(locPlanoConstruccion.getNroExpediente() != null){
				this.getTfNroExpedientePlanoConstruccion().setText(locPlanoConstruccion.getNroExpediente());
			}
			if(locPlanoConstruccion.getFechaInscripcion() != null) {
				this.getTfFechaPlanoConstruccion().setText(Conversor.getStringDeFechaCorta(locPlanoConstruccion.getFechaInscripcion()));
			}
			if(locPlanoConstruccion.getEstado() != null) {
				this.getRbgEstadoPlanoConstruccion().setSelected(Util.getEnumNameFromString(String.valueOf(locPlanoConstruccion.getEstado())));
			}
			if(locPlanoConstruccion.getComentario() != null) {
				this.getTaComentario2().setText(locPlanoConstruccion.getComentario());
			}
			if(locPlanoConstruccion.getSuperficieCubiertaPlantaAlta() != null){
				this.getTfSuperficieCubiertaPlantaAlta().setText(locPlanoConstruccion.getSuperficieCubiertaPlantaAlta());
			}
			if(locPlanoConstruccion.getSuperficieSemiCubiertaPlantaAlta() != null){
				this.getTfSuperficieSemiCubiertaPlantaAlta().setText(locPlanoConstruccion.getSuperficieSemiCubiertaPlantaAlta());
			}
			if(locPlanoConstruccion.getSuperficieCubiertaPlantaBaja() != null){
				this.getTfSuperficieCubiertaPlantaBaja().setText(locPlanoConstruccion.getSuperficieCubiertaPlantaBaja());
			}
			if(locPlanoConstruccion.getSuperficieSemiCubiertaPlantaBaja() != null){
				this.getTfSuperficieSemiCubiertaPlantaBaja().setText(locPlanoConstruccion.getSuperficieSemiCubiertaPlantaBaja());
			}
			if(locPlanoConstruccion.getNroPlantas() != null){
			this.getTfNroPlantas().setText(locPlanoConstruccion.getNroPlantas());
			}
			if(estadisticasIndec != null) {
				this.getTfEstadisticasIndec().setText(estadisticasIndec.toString());
			}
			
			panelAtributoDinamicoPlanoConstruccion = new PanelAtributoDinamico(atributosDinamicosPlanoConstruccion, "#{catastro$ABMParcela$ABMPlanoConstruccion}");
			panelAtributoDinamicoPlanoConstruccion.establecerListaAtributosDinamicos(atributosDinamicosPlanoConstruccion);
		} 
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		EstadisticasIndec estadisticaIndec = null;
		
		if (pObject instanceof Persona) {
			Persona nuevoFirmante = (Persona) pObject;
			List listaFirmantes = this.obtenerObjetoDelElementoPila(2, ArrayList.class);

			FirmantePlanoConstruccion deLaTabla = null;
			boolean esta = false;
			int i = 0;
			while (i < listaFirmantes.size() && !esta) {
				deLaTabla = (FirmantePlanoConstruccion) listaFirmantes.get(i++);
				esta = deLaTabla.getPersona().equals(nuevoFirmante);
			}
			if (!esta) {
				PlanoConstruccion planoConstruccion = this.obtenerObjetoDelElementoPila(0, PlanoConstruccion.class);
				FirmantePlanoConstruccion locFirmante = new FirmantePlanoConstruccion();
				locFirmante.setPersona(nuevoFirmante);
				locFirmante.setCargo(null);
				locFirmante.setPlanoConstruccion(planoConstruccion);
				listaFirmantes.add(locFirmante);
			} else {
				warn("La Persona que intenta agregar ya se encuentra en la lista.");
			}

			this.getElementoPila().getObjetos().set(2, listaFirmantes);
			this.getRequestBean1().setObjetoSeleccion(null);
		}
		if(pObject instanceof EstadisticasIndec) {
			if(pObject != null) {
				estadisticaIndec = (EstadisticasIndec) pObject;
				this.getElementoPila().getObjetos().set(3, estadisticaIndec);
			}
		}
		
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		PlanoConstruccion planoConstruccion = (PlanoConstruccion) this.getRequestBean1().getObjetoABM();
		List listaFirmantesPlanoConstruccion = planoConstruccion.getListaFirmantePlanoConstruccion();
		EstadisticasIndec estadisticaIndec = planoConstruccion.getEstadisticasIndec();
		
		this.getElementoPila().getObjetos().set(0, planoConstruccion);
		this.getElementoPila().getObjetos().set(2, listaFirmantesPlanoConstruccion);
		this.getElementoPila().getObjetos().set(3, estadisticaIndec);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMPlanoConstruccion";
	}

	@Override
	public String getNombreBean() {
		return "#{catastro$ABMParcela$ABMPlanoConstruccion}";
	}

	@Override
	public long getSerialVersionUID() {
		return PlanoConstruccion.serialVersionUID;
	}
	
	public String btnAgregar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			retorno = "AdminPersonaFisica";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	public String btnAgregarPJ_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			retorno = "AdminPersonaJuridica";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	public String btnQuitar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					this.getListaDelCommunication().remove(obj);
				}
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	public String btnQuitarTodos_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			try {
				this.getListaDelCommunication().clear();
			} catch (Exception ex) {
			}

//			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}
	
	public String btnSeleccionarEstadisticaIndec_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		PlanoConstruccion locPlanoConstruccion = this.obtenerObjetoDelElementoPila(0, PlanoConstruccion.class);
		
		if (locPlanoConstruccion.getEstadisticasIndec().getIdEstadisticasIndec() != -1){
			if(ultimo) {
				this.guardarEstadoObjetosUsados();
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(3, EstadisticasIndec.class));
				this.getRequestBean1().setAbmController(new EstadisticasIndecModel().new ModificarEstadisticasIndecController());
				retorno = "ABMEstadisticasIndec";
			} else {
				retorno = this.prepararCaducidad();
			}
		
		}else{
			if(ultimo) {
				this.guardarEstadoObjetosUsados();
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(3, EstadisticasIndec.class));
				this.getRequestBean1().setAbmController(new EstadisticasIndecModel().new AgregarEstadisticasIndecController());
				retorno = "ABMEstadisticasIndec";
			} else {
				retorno = this.prepararCaducidad();
			}
		}
		return retorno;
	}
	
	public String btnLimpiarEstadisticaIndec_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(3, EstadisticasIndec.class, this.getTfEstadisticasIndec());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

}
