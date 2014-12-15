package muni.habilitaciones.grpCementerio.ABMParcelaCementerio;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.faces.convert.DateTimeConverter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TabSet;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.persistent.RegistroPropietario;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.cementerio.Concesion;
import com.trascender.habilitaciones.recurso.persistent.cementerio.Difunto;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.abstracts.TablaLogs;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

public class ABMParcelaCementerio extends ABMPageBean{
	
	@Override
    protected void _init() throws Exception{
		dateTimeConverter.setPattern("dd/MM/yyyy");
        dateTimeConverter.setTimeZone(TimeZone.getDefault());
        
    	if(this.getListaDelCommunicationDifuntos() != null){
    		this.getObjectListDataProviderDifuntos().setList(this.getListaDelCommunicationDifuntos());
    	}
    	
    	if(this.getListaDelCommunicationRegistroPropietario() != null){
    		this.getObjectListDataProviderRegistroPropietario().setList(this.getListaDelCommunicationRegistroPropietario());
    	}
    	
    	Option[] opTipoConcesion = null;
    	opTipoConcesion = this.getApplicationBean1().getMgrDropDown()
				.armarArrayOptionsList(Concesion.TipoConcesion.values(), "cap");
		ddTipoConcesionDefaultOptions.setOptions(opTipoConcesion);
    }
	
	private Label lblSuperficie = new Label();
	private Label lblDifuntos = new Label();
	private Label lblTipoSepultura = new Label();
	private Label lblPropietarios = new Label();
	private Label lblFechaInscripcion = new Label();
	private Label lblFechaFin = new Label();
	private Label lblTipoConcesion = new Label();
	
	private Label lblComentarioLogAuditoria2 = new Label();
	private TextArea taComentarioLogAuditoria2 = new TextArea();
	private TablaLogs tablaLogs2 = new TablaLogs(this.getNombreBean());
	
	private TextField tfSuperficie = new TextField();
	private TextField tfTipoSepultura = new TextField();
	private TextField tfFechaInscripcion = new TextField();
	private TextField tfFechaFin = new TextField();
	private TextArea taDescripcion = new TextArea();
	private DropDown ddTipoConcesion = new DropDown();
	private SingleSelectOptionsList ddTipoConcesionDefaultOptions = new SingleSelectOptionsList();
	private Table tablaDifuntos = new Table();
	private Table tablaPropietarios = new Table();
	
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private TableRowGroup tableRowGroup2 = new TableRowGroup();
	private ObjectListDataProvider ldpDifuntos = new ObjectListDataProvider();
	private ObjectListDataProvider ldpRegistroPropietario = new ObjectListDataProvider();
	private RadioButton radioButton1 = new RadioButton();
	private TableColumn tableColumn1 = new TableColumn();
	private RadioButton radioButton2 = new RadioButton();
	private TableColumn tableColumn2 = new TableColumn();
	private TableColumn tcPersona = new TableColumn();
	private TableColumn tcFechaDeceso = new TableColumn();
	private TableColumn tcPersonaPropietario = new TableColumn();
	private TableColumn tcDescripcion = new TableColumn();
	private PanelGroup groupPanel1 = new PanelGroup();
	private PanelGroup groupPanel2 = new PanelGroup();
	
	private StaticText stPersona = new StaticText();
	private StaticText stFechaDeceso = new StaticText();
	private StaticText staticText1 = new StaticText();
	private StaticText staticText2 = new StaticText();
	private StaticText stPersonaPropietario = new StaticText();
	private PanelAtributoDinamico panelAtributoDinamico2 = new PanelAtributoDinamico();
	
	private Button btnAgregarDifunto = new Button();
	private Button btnModificarDifunto = new Button();
	private Button btnConsultarDifunto = new Button();
	private Button btnSeleccionarTipoSepultura = new Button();
	private Button btnAgregarPersonaFisica = new Button();
	private Button btnAgregarPersonaJuridica = new Button();
	 protected HtmlAjaxCommandButton btnQuitarPersona = new HtmlAjaxCommandButton();
	 protected HtmlAjaxCommandButton btnQuitarTodosPersona = new HtmlAjaxCommandButton();
	 protected HtmlAjaxCommandButton btnQuitarDifunto = new HtmlAjaxCommandButton();
	 protected HtmlAjaxCommandButton btnQuitarTodosDifunto = new HtmlAjaxCommandButton();
	
	protected DateTimeConverter dateTimeConverter = new DateTimeConverter();
	protected TabSet tabSet1 = new TabSet();
		
	public Label getLblComentarioLogAuditoria2() {
		return lblComentarioLogAuditoria2;
	}

	public void setLblComentarioLogAuditoria2(Label lblComentarioLogAuditoria2) {
		this.lblComentarioLogAuditoria2 = lblComentarioLogAuditoria2;
	}

	public TextArea getTaComentarioLogAuditoria2() {
		return taComentarioLogAuditoria2;
	}

	public void setTaComentarioLogAuditoria2(TextArea taComentarioLogAuditoria2) {
		this.taComentarioLogAuditoria2 = taComentarioLogAuditoria2;
	}

	public TablaLogs getTablaLogs2() {
		return tablaLogs2;
	}

	public void setTablaLogs2(TablaLogs tablaLogs2) {
		this.tablaLogs2 = tablaLogs2;
	}

	public PanelAtributoDinamico getPanelAtributoDinamico2() {
		return panelAtributoDinamico2;
	}

	public void setPanelAtributoDinamico2(PanelAtributoDinamico panelAtributoDinamico2) {
		this.panelAtributoDinamico2 = panelAtributoDinamico2;
	}

	public SingleSelectOptionsList getDdTipoConcesionDefaultOptions() {
		return ddTipoConcesionDefaultOptions;
	}

	public void setDdTipoConcesionDefaultOptions(
			SingleSelectOptionsList ddTipoConcesionDefaultOptions) {
		this.ddTipoConcesionDefaultOptions = ddTipoConcesionDefaultOptions;
	}

	public Label getLblTipoConcesion() {
		return lblTipoConcesion;
	}

	public void setLblTipoConcesion(Label lblTipoConcesion) {
		this.lblTipoConcesion = lblTipoConcesion;
	}

	public DropDown getDdTipoConcesion() {
		return ddTipoConcesion;
	}

	public void setDdTipoConcesion(DropDown ddTipoConcesion) {
		this.ddTipoConcesion = ddTipoConcesion;
	}

	public Button getBtnConsultarDifunto() {
		return btnConsultarDifunto;
	}

	public void setBtnConsultarDifunto(Button btnConsultarDifunto) {
		this.btnConsultarDifunto = btnConsultarDifunto;
	}

	public Label getLblFechaInscripcion() {
		return lblFechaInscripcion;
	}

	public void setLblFechaInscripcion(Label lblFechaInscripcion) {
		this.lblFechaInscripcion = lblFechaInscripcion;
	}

	public Label getLblFechaFin() {
		return lblFechaFin;
	}

	public void setLblFechaFin(Label lblFechaFin) {
		this.lblFechaFin = lblFechaFin;
	}

	public TextField getTfFechaInscripcion() {
		return tfFechaInscripcion;
	}

	public void setTfFechaInscripcion(TextField tfFechaInscripcion) {
		this.tfFechaInscripcion = tfFechaInscripcion;
	}

	public TextField getTfFechaFin() {
		return tfFechaFin;
	}

	public void setTfFechaFin(TextField tfFechaFin) {
		this.tfFechaFin = tfFechaFin;
	}

	public TableColumn getTcPersonaPropietario() {
		return tcPersonaPropietario;
	}

	public void setTcPersonaPropietario(TableColumn tcPersonaPropietario) {
		this.tcPersonaPropietario = tcPersonaPropietario;
	}

	public TableColumn getTcDescripcion() {
		return tcDescripcion;
	}

	public void setTcDescripcion(TableColumn tcDescripcion) {
		this.tcDescripcion = tcDescripcion;
	}

	public Label getLblPropietarios() {
		return lblPropietarios;
	}

	public void setLblPropietarios(Label lblPropietarios) {
		this.lblPropietarios = lblPropietarios;
	}

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea taDescripcion) {
		this.taDescripcion = taDescripcion;
	}

	public Table getTablaPropietarios() {
		return tablaPropietarios;
	}

	public void setTablaPropietarios(Table tablaPropietarios) {
		this.tablaPropietarios = tablaPropietarios;
	}

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
		this.tableRowGroup2 = tableRowGroup2;
	}

	public ObjectListDataProvider getLdpRegistroPropietario() {
		return ldpRegistroPropietario;
	}

	public void setLdpRegistroPropietario(
			ObjectListDataProvider ldpRegistroPropietario) {
		this.ldpRegistroPropietario = ldpRegistroPropietario;
	}

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton radioButton2) {
		this.radioButton2 = radioButton2;
	}

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	public PanelGroup getGroupPanel2() {
		return groupPanel2;
	}

	public void setGroupPanel2(PanelGroup groupPanel2) {
		this.groupPanel2 = groupPanel2;
	}

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText staticText2) {
		this.staticText2 = staticText2;
	}

	public StaticText getStPersonaPropietario() {
		return stPersonaPropietario;
	}

	public void setStPersonaPropietario(StaticText stPersonaPropietario) {
		this.stPersonaPropietario = stPersonaPropietario;
	}

	public Button getBtnAgregarPersonaFisica() {
		return btnAgregarPersonaFisica;
	}

	public void setBtnAgregarPersonaFisica(Button btnAgregarPersonaFisica) {
		this.btnAgregarPersonaFisica = btnAgregarPersonaFisica;
	}

	public Button getBtnAgregarPersonaJuridica() {
		return btnAgregarPersonaJuridica;
	}

	public void setBtnAgregarPersonaJuridica(Button btnAgregarPersonaJuridica) {
		this.btnAgregarPersonaJuridica = btnAgregarPersonaJuridica;
	}

	public HtmlAjaxCommandButton getBtnQuitarPersona() {
		return btnQuitarPersona;
	}

	public void setBtnQuitarPersona(HtmlAjaxCommandButton btnQuitarPersona) {
		this.btnQuitarPersona = btnQuitarPersona;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodosPersona() {
		return btnQuitarTodosPersona;
	}

	public void setBtnQuitarTodosPersona(HtmlAjaxCommandButton btnQuitarTodosPersona) {
		this.btnQuitarTodosPersona = btnQuitarTodosPersona;
	}

	public TabSet getTabSet1() {
		return tabSet1;
	}

	public void setTabSet1(TabSet tabSet1) {
		this.tabSet1 = tabSet1;
	}

	public DateTimeConverter getDateTimeConverter() {
		return dateTimeConverter;
	}

	public void setDateTimeConverter(DateTimeConverter dateTimeConverter) {
		this.dateTimeConverter = dateTimeConverter;
	}

	public Button getBtnSeleccionarTipoSepultura() {
		return btnSeleccionarTipoSepultura;
	}

	public void setBtnSeleccionarTipoSepultura(Button btnSeleccionarTipoSepultura) {
		this.btnSeleccionarTipoSepultura = btnSeleccionarTipoSepultura;
	}

	public Label getLblTipoSepultura() {
		return lblTipoSepultura;
	}

	public void setLblTipoSepultura(Label lblTipoSepultura) {
		this.lblTipoSepultura = lblTipoSepultura;
	}

	public TextField getTfTipoSepultura() {
		return tfTipoSepultura;
	}

	public void setTfTipoSepultura(TextField tfTipoSepultura) {
		this.tfTipoSepultura = tfTipoSepultura;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	public Label getLblSuperficie() {
		return lblSuperficie;
	}

	public void setLblSuperficie(Label lblSuperficie) {
		this.lblSuperficie = lblSuperficie;
	}

	public Label getLblDifuntos() {
		return lblDifuntos;
	}

	public void setLblDifuntos(Label lblDifuntos) {
		this.lblDifuntos = lblDifuntos;
	}

	public TextField getTfSuperficie() {
		return tfSuperficie;
	}

	public void setTfSuperficie(TextField tfSuperficie) {
		this.tfSuperficie = tfSuperficie;
	}

	public Table getTablaDifuntos() {
		return tablaDifuntos;
	}

	public void setTablaDifuntos(Table tablaDifuntos) {
		this.tablaDifuntos = tablaDifuntos;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public ObjectListDataProvider getLdpDifuntos() {
		return ldpDifuntos;
	}

	public void setLdpDifuntos(ObjectListDataProvider ldpDifuntos) {
		this.ldpDifuntos = ldpDifuntos;
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

	public TableColumn getTcPersona() {
		return tcPersona;
	}

	public void setTcPersona(TableColumn tcPersona) {
		this.tcPersona = tcPersona;
	}

	public TableColumn getTcFechaDeceso() {
		return tcFechaDeceso;
	}

	public void setTcFechaDeceso(TableColumn tcFechaDeceso) {
		this.tcFechaDeceso = tcFechaDeceso;
	}

	public StaticText getStPersona() {
		return stPersona;
	}

	public void setStPersona(StaticText stPersona) {
		this.stPersona = stPersona;
	}

	public StaticText getStFechaDeceso() {
		return stFechaDeceso;
	}

	public void setStFechaDeceso(StaticText stFechaDeceso) {
		this.stFechaDeceso = stFechaDeceso;
	}

	public Button getBtnAgregarDifunto() {
		return btnAgregarDifunto;
	}

	public void setBtnAgregarDifunto(Button btnAgregarDifunto) {
		this.btnAgregarDifunto = btnAgregarDifunto;
	}

	public Button getBtnModificarDifunto() {
		return btnModificarDifunto;
	}

	public void setBtnModificarDifunto(Button btnModificarDifunto) {
		this.btnModificarDifunto = btnModificarDifunto;
	}

	public HtmlAjaxCommandButton getBtnQuitarDifunto() {
		return btnQuitarDifunto;
	}

	public void setBtnQuitarDifunto(HtmlAjaxCommandButton btnQuitarDifunto) {
		this.btnQuitarDifunto = btnQuitarDifunto;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodosDifunto() {
		return btnQuitarTodosDifunto;
	}

	public void setBtnQuitarTodosDifunto(HtmlAjaxCommandButton btnQuitarTodosDifunto) {
		this.btnQuitarTodosDifunto = btnQuitarTodosDifunto;
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
	
	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}
	
	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getLdpDifuntos().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}
	
	private Object lastSelected2 = null;

	public Object getRBSelected2() {
		String sv = (String) radioButton2.getSelectedValue();
		return sv.equals(lastSelected2) ? sv : null;
	}

	public void setRBSelected2(Object selected) {
		if (selected != null) {
			lastSelected2 = selected;
		}
	}
	
	public String getCurrentRow2() {
		return tableRowGroup2.getRowKey().getRowId();
	}

	public void setCurrentRow2(int row) {
	}
	
	public RowKey getSeleccionado2() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup2");
			rk = this.getLdpRegistroPropietario().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}

	private List<Difunto> getListaDelCommunicationDifuntos() {
		return this.getCommunicationHabilitacionesBean().getListaDifuntos();
	}

	private void setListaDelCommunicationDifuntos(List<Difunto> lista) {
		this.getCommunicationHabilitacionesBean().setListaDifuntos(lista);
	}
	
	private ObjectListDataProvider getObjectListDataProviderDifuntos() {
            return this.getLdpDifuntos();
    }
	
	private List<RegistroPropietario> getListaDelCommunicationRegistroPropietario() {
		return this.getCommunicationHabilitacionesBean().getListaRegistroPropietarioParcelaCementerio();
	}

	private void setListaDelCommunicationRegistroPropietario(List<RegistroPropietario> lista) {
		this.getCommunicationHabilitacionesBean().setListaRegistroPropietarioParcelaCementerio(lista);
	}
	
	private ObjectListDataProvider getObjectListDataProviderRegistroPropietario() {
            return this.getLdpRegistroPropietario();
    }
	
	protected List getListaDelCommunicationAtributosDinamicos() {
        return this.getCommunicationHabilitacionesBean().getListaAtributosDinamicosParcelaCementerio();
    }

    private void setListaDelCommunicationAtributosDinamicos(List lista) {
        this.getCommunicationHabilitacionesBean().setListaAtributosDinamicosParcelaCementerio(lista);
    }
	
	@Override
	protected void guardarEstadoObjetosUsados() {
		ParcelaCementerio parcelaCementerio = this.obtenerObjetoDelElementoPila(0, ParcelaCementerio.class);
		TipoSepultura tipoSepultura = this.obtenerObjetoDelElementoPila(1, TipoSepultura.class);
		List<Difunto> listaDifuntos = (List<Difunto>)this.obtenerObjetoDelElementoPila(2, ArrayList.class);
		ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(4, ArrayList.class);
		List atributosDinamicos2 = (List) this.obtenerObjetoDelElementoPila(5, ArrayList.class);
		
		parcelaCementerio.setSuperficie(getTextFieldValue(this.getTfSuperficie()) != null? Double.parseDouble(getTextFieldValue(this.getTfSuperficie())) : null);
		
		if(tipoSepultura.getIdTipoAlicuota() == -1){
			tipoSepultura = null;
		}
		parcelaCementerio.setRegistroAlicuota(tipoSepultura);
		parcelaCementerio.getConcesion().setFechaInscripcion(getTextFieldValueDate(this.getTfFechaInscripcion()));
		parcelaCementerio.getConcesion().setFechaFin(getTextFieldValueDate(this.getTfFechaFin()));
		parcelaCementerio.getConcesion().setTipoConcesion(getDDEnumValue(this.getDdTipoConcesion(), Concesion.TipoConcesion.class));
		
		String idTabActual = this.getTabSet1().getSelected();
		
		listaDifuntos = this.getObjectListDataProviderDifuntos().getList();
		parcelaCementerio.setListaDifuntos(listaDifuntos);
		this.setListaDelCommunicationDifuntos(listaDifuntos);
		
		this.getObjectListDataProviderRegistroPropietario().commitChanges();
        this.setListaDelCommunicationRegistroPropietario(this.getObjectListDataProviderRegistroPropietario().getList());
        parcelaCementerio.getConcesion().setListaRegistrosPropietarios(this.getObjectListDataProviderRegistroPropietario().getList());
		
        atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
        parcelaCementerio.setListaAtributosDinamicos(atributosDinamicos);
        atributosDinamicos2 = (List) panelAtributoDinamico2.obtenerListaAtributosDinamicos(atributosDinamicos2);
        parcelaCementerio.getConcesion().setListaAtributosDinamicos(atributosDinamicos2);
        
		this.getElementoPila().getObjetos().set(0, parcelaCementerio);
		this.getElementoPila().getObjetos().set(1, tipoSepultura);
		this.getElementoPila().getObjetos().set(2, listaDifuntos);
		this.getElementoPila().getObjetos().set(3, idTabActual);
		this.getElementoPila().getObjetos().set(4, atributosDinamicos);
		this.getElementoPila().getObjetos().set(5, atributosDinamicos2);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		ParcelaCementerio parcelaCementerio = this.obtenerObjetoDelElementoPila(0, ParcelaCementerio.class);
		TipoSepultura tipoSepultura = this.obtenerObjetoDelElementoPila(1, TipoSepultura.class);
		List<Difunto> listaDifuntos = (List<Difunto>)this.obtenerObjetoDelElementoPila(2, ArrayList.class);
		ArrayList atributosDinamicos = null;
		List atributosDinamicos2 = null;
		
		Concesion concesion = new Concesion();
		List<RegistroPropietario> registrosProp = new ArrayList<RegistroPropietario>();
		
		if (parcelaCementerio.getListaAtributosDinamicos() != null) {
			parcelaCementerio = (ParcelaCementerio) this.obtenerObjetoDelElementoPila(0, ParcelaCementerio.class);
                try {
                    atributosDinamicos = (ArrayList) this.getComunicationBean()
                            .getRemoteSystemParametro()
                            .getAtributosPorRecurso(ParcelaCementerio.serialVersionUID, parcelaCementerio.getListaAtributosDinamicos(), null);
                    this.getElementoPila().getObjetos().set(4, atributosDinamicos);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
		
		parcelaCementerio = this.obtenerObjetoDelElementoPila(0, ParcelaCementerio.class);
		tipoSepultura = this.obtenerObjetoDelElementoPila(1, TipoSepultura.class);
		listaDifuntos = (List<Difunto>)this.obtenerObjetoDelElementoPila(2, ArrayList.class);
		atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(4, ArrayList.class);

        panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{habilitaciones$ABMParcelaCementerio$ABMParcelaCementerio}", "parcelaCementerio");
        panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);
		
		if(tipoSepultura != null && tipoSepultura.getIdTipoAlicuota() != -1){
			this.getTfTipoSepultura().setText(tipoSepultura.toString());
		}
		
		if(parcelaCementerio.getConcesion() == null){
			parcelaCementerio.setConcesion(concesion);
//			concesion.set
		}
		
		if (parcelaCementerio.getConcesion().getListaAtributosDinamicos() != null) {
            try {
                atributosDinamicos2 = (List) this.getComunicationBean()
                        .getRemoteSystemParametro()
                        .getAtributosPorRecurso(Concesion.serialVersionUID, parcelaCementerio.getConcesion().getListaAtributosDinamicos(), null);
                this.getElementoPila().getObjetos().set(5, atributosDinamicos2);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
		
		atributosDinamicos2 = (List) this.obtenerObjetoDelElementoPila(5, ArrayList.class);
        
        panelAtributoDinamico2 = new PanelAtributoDinamico(atributosDinamicos2, "#{habilitaciones$ABMParcelaCementerio$ABMParcelaCementerio}", "concesion");
        panelAtributoDinamico2.establecerListaAtributosDinamicos(atributosDinamicos2);
		
		this.getTfSuperficie().setText(parcelaCementerio.getSuperficie());
		this.getTfFechaInscripcion().setText(Conversor.getStringDeFechaCorta(parcelaCementerio.getConcesion().getFechaInscripcion()));
		this.getTfFechaFin().setText(Conversor.getStringDeFechaCorta(parcelaCementerio.getConcesion().getFechaFin()));
		
		this.getDdTipoConcesion().setSelected(Util.getEnumNameFromString(String.valueOf(concesion.getTipoConcesion())));
		
		String idTab = (String) this.obtenerObjetoDelElementoPila(3);
        if (idTab != null){
            this.getTabSet1().setSelected(idTab);
        }
		
		this.getObjectListDataProviderDifuntos().setList(listaDifuntos);
		this.setListaDelCommunicationDifuntos(listaDifuntos);
		
		registrosProp = parcelaCementerio.getConcesion().getListaRegistrosPropietarios();
		this.getObjectListDataProviderRegistroPropietario().setList(registrosProp);
		this.setListaDelCommunicationRegistroPropietario(registrosProp);
		
		this.setListaDelCommunicationAtributosDinamicos(atributosDinamicos);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMParcelaCementerio";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		
		ep.getObjetos().add(ind++, new ParcelaCementerio());
		ep.getObjetos().add(ind++, new TipoSepultura());
		ep.getObjetos().add(ind++, new ArrayList<Difunto>());
		ep.getObjetos().add(ind++, null);// id de la pestaña seleccionada
		ep.getObjetos().add(ind++, new ArrayList()); //AtributosDinamicos Parcela Cementerio
		ep.getObjetos().add(ind++, new ArrayList()); //AtributosDinamicos Concesion
		 
		return ep;
	}
	
	public String btnAgregarDifunto_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			
			this.getRequestBean1().setAbmController(new DifuntoModel().new AgregarDifuntoController());
			
			retorno = "ABMDifunto";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	public String btnModificarDifunto_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		RowKey rk = null;
		
		if (ultimo) {

			rk = this.getSeleccionado();
			if (rk != null) {
				int index = getNroFila(rk.toString());
				
				Object obj = this.getLdpDifuntos().getObjects()[index];
				Difunto difunto = (Difunto) obj;
			
				this.guardarEstadoObjetosUsados();
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				
				this.getRequestBean1().setObjetoABM(difunto);
				this.getRequestBean1().setAbmController(new DifuntoModel().new ModificarDifuntoController());
				
				retorno = "ABMDifunto";
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	public String btnConsultarDifunto_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		RowKey rk = null;
		
		if (ultimo) {

			rk = this.getSeleccionado();
			if (rk != null) {
				int index = getNroFila(rk.toString());
				
				Object obj = this.getLdpDifuntos().getObjects()[index];
				Difunto difunto = (Difunto) obj;
			
				this.guardarEstadoObjetosUsados();
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				
				this.getRequestBean1().setObjetoABM(difunto);
				this.getRequestBean1().setAbmController(new DifuntoModel().new ConsultarDifuntoController());
				
				retorno = "ABMDifunto";
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	public String btnQuitarDifunto_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		List<Difunto> listaDifuntos = this.getListaDelCommunicationDifuntos();
		if (ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					
					Object obj = this.getLdpDifuntos().getObjects()[index];
					Difunto difunto = (Difunto) obj;

					difunto.setParcelaCementerio(null);
					this.getListaDelCommunicationDifuntos().remove(difunto);
					this.getObjectListDataProviderDifuntos().setList(
							this.getListaDelCommunicationDifuntos());

					this.getElementoPila().getObjetos()
							.set(2, listaDifuntos);
					ParcelaCementerio parcelaCementerio = (ParcelaCementerio) this
							.obtenerObjetoDelElementoPila(0, ParcelaCementerio.class);
					parcelaCementerio.setListaDifuntos(this.getListaDelCommunicationDifuntos());
				}
			} catch (Exception ex) {
				log(this.getCasoNavegacion() + "_EliminarDifuntoError:", ex);
				error(this.getNombrePagina() + " - Eliminar Difunto: "
						+ ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}
	
	public String btnQuitarTodosDifunto_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			try {
				this.getListaDelCommunicationDifuntos().clear();
				this.getObjectListDataProviderDifuntos().setList(
						this.getListaDelCommunicationDifuntos());
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	public String btnSeleccionarTipoSepultura_action() {
		return navegarParaSeleccionar("AdminTipoSepultura");
	}
	
	public String btnAgregarPersonaFisica_action() {
		return navegarParaSeleccionar("AdminPersonaFisica");
	}

	public String btnAgregarPersonaJuridica_action() {
		return navegarParaSeleccionar("AdminPersonaJuridica");
	}

	public String btnQuitarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		
		ParcelaCementerio parcelaCementerio = (ParcelaCementerio) this.obtenerObjetoDelElementoPila(0, ParcelaCementerio.class);
		
		Concesion concesion = parcelaCementerio.getConcesion();
		List<RegistroPropietario> registros = (List<RegistroPropietario>) concesion.getListaRegistrosPropietarios();

		if (ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado2();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderRegistroPropietario().getObjects()[index];
					RegistroPropietario registroPropietario = (RegistroPropietario) obj;
					
					if (this.getObjectListDataProviderRegistroPropietario().getList().size() > 0) {
						this.getObjectListDataProviderRegistroPropietario().commitChanges();
					}
					
					this.getListaDelCommunicationRegistroPropietario().remove(registroPropietario);
					this.getObjectListDataProviderRegistroPropietario().setList(this.getListaDelCommunicationRegistroPropietario());
//					this.setListaDelCommunicationRegistroPropietario((ArrayList) registros);
//					this.getObjectListDataProviderRegistroPropietario().setList(this.getListaDelCommunicationRegistroPropietario());
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

	public String btnQuitarTodosPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			try {
				this.getListaDelCommunicationRegistroPropietario().clear();
				this.getObjectListDataProviderRegistroPropietario().setList(this.getListaDelCommunicationRegistroPropietario());
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	@Override
	public void destroy() {
		try {
			this.guardarEstadoObjetosUsados();
		} catch (Exception e) {
		}
		super.destroy();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		ParcelaCementerio parcelaCementerio = this.obtenerObjetoDelElementoPila(0, ParcelaCementerio.class);
		TipoSepultura tipoSepultura = this.obtenerObjetoDelElementoPila(1, TipoSepultura.class);
		List<Difunto> listaDifuntos = (List<Difunto>)this.obtenerObjetoDelElementoPila(2, ArrayList.class);
		Concesion concesion = new Concesion();
		
		Object seleccionado = pObject;
		
		if(seleccionado instanceof Difunto){
			Difunto difunto = (Difunto) seleccionado;
			
			boolean yaEstaEnLista = false;
			for(Difunto cadaDifunto : parcelaCementerio.getListaDifuntos()){
				if(cadaDifunto.getPersona().equals(difunto.getPersona())){
					yaEstaEnLista = true;
					break;
				}
			}
			
			if(!yaEstaEnLista){
				difunto.setParcelaCementerio(parcelaCementerio);
				listaDifuntos.add(difunto);
				parcelaCementerio.setListaDifuntos(listaDifuntos);
				
				this.getElementoPila().getObjetos().set(0, parcelaCementerio);
				this.getElementoPila().getObjetos().set(2, listaDifuntos);
			} else{
				warn("La Persona seleccionada ya se encuentra en la lista");
			}
		}
		
		if (seleccionado instanceof Persona) {
			Persona persona = (Persona) seleccionado;
			parcelaCementerio = (ParcelaCementerio) this.obtenerObjetoDelElementoPila(0, ParcelaCementerio.class);
			concesion = parcelaCementerio.getConcesion();

			List<RegistroPropietario> registrosGuardados = (List<RegistroPropietario>) this
					.getListaDelCommunicationRegistroPropietario();
			
			if(registrosGuardados == null){
				registrosGuardados = new ArrayList<RegistroPropietario>();
				this.setListaDelCommunicationRegistroPropietario(registrosGuardados);
			}
			
			RegistroPropietario deLaTabla = null;
			boolean esta = false;
			int i = 0;
			while (i < registrosGuardados.size() && !esta) {
				deLaTabla = (RegistroPropietario) registrosGuardados.get(i);
				esta = (deLaTabla.getPersona().getIdPersona() == persona
						.getIdPersona());
				i++;
			}
			if (!esta) {
				RegistroPropietario nuevoReg = new RegistroPropietario();
				nuevoReg.setPersona(persona);
				nuevoReg.setTituloPropiedad(concesion);
				registrosGuardados.add(nuevoReg);
			} else {
				warn("La Persona que intenta agregar ya se encuentra en la lista.");
			}
			concesion.setListaRegistrosPropietarios(registrosGuardados);
			this.getRequestBean1().setObjetoSeleccion(null);
			
			this.getObjectListDataProviderRegistroPropietario().setList(registrosGuardados);
			this.setListaDelCommunicationRegistroPropietario(registrosGuardados);
			parcelaCementerio.getConcesion().setListaRegistrosPropietarios(registrosGuardados);
			
			this.getElementoPila().getObjetos().set(0, parcelaCementerio);
		}
		
		if(seleccionado instanceof TipoSepultura){
			tipoSepultura = (TipoSepultura) seleccionado;
			parcelaCementerio.setRegistroAlicuota(tipoSepultura);
			
			this.getElementoPila().getObjetos().set(0, parcelaCementerio);
			this.getElementoPila().getObjetos().set(1, tipoSepultura);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		ParcelaCementerio parcelaCementerio = this.obtenerObjetoDelElementoPila(0, ParcelaCementerio.class);
		TipoSepultura tipoSepultura = this.obtenerObjetoDelElementoPila(1, TipoSepultura.class);
		List<Difunto> listaDifuntos = (List<Difunto>)this.obtenerObjetoDelElementoPila(2, ArrayList.class);
		Concesion concesion = new Concesion();
		List<RegistroPropietario> registrosProp = new ArrayList<RegistroPropietario>();
		
		parcelaCementerio = (ParcelaCementerio) pObject;
		tipoSepultura = (TipoSepultura)parcelaCementerio.getRegistroAlicuota();
		listaDifuntos = parcelaCementerio.getListaDifuntos();
		concesion = parcelaCementerio.getConcesion();
		
		for (RegistroPropietario locRegistroPropietario : concesion.getListaRegistrosPropietarios()) {
			if (locRegistroPropietario.getPersona().getEstado()
					.equals(Persona.Estado.ACTIVO)) {
				registrosProp.add(locRegistroPropietario);
			}
		}
		this.setListaDelCommunicationRegistroPropietario(registrosProp);
		this.getLdpRegistroPropietario().setList(registrosProp);
		
		this.getElementoPila().getObjetos().set(0, parcelaCementerio);
		this.getElementoPila().getObjetos().set(1, tipoSepultura);
		this.getElementoPila().getObjetos().set(2, listaDifuntos);
		this.getElementoPila().getObjetos().set(4, parcelaCementerio.getListaAtributosDinamicos());
		this.getElementoPila().getObjetos().set(5, concesion.getListaAtributosDinamicos());
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		try {
			String idTab = this.getTabSet1().getSelected();
			ParcelaCementerio locParcelaCementario = this.obtenerObjetoDelElementoPila(0, ParcelaCementerio.class);

			if(idTab != null){
				if(idTab.equals("one")) {
					this.getTablaLogs().getLdpLogs().setList(locParcelaCementario.getListaLogsAuditoria());
				} else if(idTab.equals("two") && locParcelaCementario.getConcesion() != null) {
					this.getTablaLogs().getLdpLogs().setList(locParcelaCementario.getConcesion().getListaLogsAuditoria());
				}
			}
			else {
				this.getTablaLogs().getLdpLogs().setList(locParcelaCementario.getListaLogsAuditoria());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public long getSerialVersionUID() {
		return ParcelaCementerio.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio}";
	}
}
