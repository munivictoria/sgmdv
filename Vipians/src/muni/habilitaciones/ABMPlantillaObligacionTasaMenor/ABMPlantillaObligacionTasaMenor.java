/*
 * AgregarPlantillaObligacionTasaMenor.java
 *
 * Created on 24 de octubre de 2006, 10:40
 * Copyright Trascender SRL
 */
package muni.habilitaciones.ABMPlantillaObligacionTasaMenor;

import java.util.ArrayList;
import java.util.List;

import muni.framework.ABMAtributoDinamico.AtributoDinamicoModel;

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
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.DocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMPlantillaObligacionTasaMenor extends ABMPageBean {
    @Override
    protected void _init() throws Exception {

        if (this.getListaDelCommunication() != null) {
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }

        if (this.getListaDelCommunication2() != null) {
            this.getObjectListDataProvider2().setList(this.getListaDelCommunication2());
        }

        Option[] op = null;
        // Tipos datos de la plantilla
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(PlantillaAtributoDinamico.Tipo.values(), "cap");
        ddTipoDatoDefaultOptions.setOptions(op);

        Option[] op2 = null;
        // Tipos datos de los registros valuados
        op2 = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(PlantillaAtributoDinamico.Tipo.values(), "cap");
        ddTipoDatoRegValuadoDefaultOptions.setOptions(op2);
    }
    
    private MessageGroup messageGroup1 = new MessageGroup();

    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }

    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
    }
    
    private PanelGroup groupPanel1 = new PanelGroup();

    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup groupPanel1) {
        this.groupPanel1 = groupPanel1;
    }
    
    private PanelGroup groupPanel2 = new PanelGroup();
    private StaticText stSeparador1 = new StaticText();
    private StaticText stSeparador2 = new StaticText();
    //----------StaticText-----------------
    private StaticText staticText1 = new StaticText();
    private StaticText staticText2 = new StaticText();
    private StaticText staticText6 = new StaticText();
    private StaticText staticText7 = new StaticText();
    private StaticText staticText8 = new StaticText();
    private StaticText staticText9 = new StaticText();
    private StaticText staticText10 = new StaticText();

    public StaticText getStSeparador2() {
		return stSeparador2;
	}

	public void setStSeparador2(StaticText stSeparador2) {
		this.stSeparador2 = stSeparador2;
	}

	public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText staticText10) {
        this.staticText10 = staticText10;
    }

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText staticText9) {
        this.staticText9 = staticText9;
    }

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText staticText8) {
        this.staticText8 = staticText8;
    }

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText staticText7) {
        this.staticText7 = staticText7;
    }

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

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText staticText6) {
        this.staticText6 = staticText6;
    }
    //----------TextField-----------------
    private TextField tfNombre = new TextField();
    private TextField tfNombreDato = new TextField();
    private TextField tfNombreDatoRegValuado = new TextField();

    public TextField getTfNombreDatoRegValuado() {
        return tfNombreDatoRegValuado;
    }

    public void setTfNombreDatoRegValuado(TextField tfNombreDatoRegValuado) {
        this.tfNombreDatoRegValuado = tfNombreDatoRegValuado;
    }

    public TextField getTfNombreDato() {
        return tfNombreDato;
    }

    public void setTfNombreDato(TextField tfNombreDato) {
        this.tfNombreDato = tfNombreDato;
    }

    public TextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(TextField tfNombre) {
        this.tfNombre = tfNombre;
    }
    //----------DropDowns-----------------
    private DropDown ddTipoDato = new DropDown();
    private DropDown ddTipoDatoRegValuado = new DropDown();

    public DropDown getDdTipoDatoRegValuado() {
        return ddTipoDatoRegValuado;
    }

    public void setDdTipoDatoRegValuado(DropDown ddTipoDatoRegValuado) {
        this.ddTipoDatoRegValuado = ddTipoDatoRegValuado;
    }

    public DropDown getDdTipoDato() {
        return ddTipoDato;
    }

    public void setDdTipoDato(DropDown ddTipoDato) {
        this.ddTipoDato = ddTipoDato;
    }
    private SingleSelectOptionsList ddTipoDatoDefaultOptions = new SingleSelectOptionsList();
    private SingleSelectOptionsList ddTipoDatoRegValuadoDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdTipoDatoRegValuadoDefaultOptions() {
        return ddTipoDatoRegValuadoDefaultOptions;
    }

    public void setDdTipoDatoRegValuadoDefaultOptions(SingleSelectOptionsList ddTipoDatoRegValuadoDefaultOptions) {
        this.ddTipoDatoRegValuadoDefaultOptions = ddTipoDatoRegValuadoDefaultOptions;
    }

    public SingleSelectOptionsList getDdTipoDatoDefaultOptions() {
        return ddTipoDatoDefaultOptions;
    }

    public void setDdTipoDatoDefaultOptions(SingleSelectOptionsList ddTipoDatoDefaultOptions) {
        this.ddTipoDatoDefaultOptions = ddTipoDatoDefaultOptions;
    }
    //----------Labels-----------------
    private Label lblDatosPlantilla = new Label();
    private Label lblDatosPlantillaRegValuado = new Label();
    private Label label4 = new Label();
    private Label label5 = new Label();
    private Label label1 = new Label();
    private Label label2 = new Label();
    private Label lblNombreDato = new Label();
    private Label lblNombreDatoRegValuado = new Label();
    private Label lblAsociacionAParcela = new Label();
    private Label lblPersonaPropietaria = new Label();

    public Label getLblAsociacionAParcela() {
		return lblAsociacionAParcela;
	}

	public void setLblAsociacionAParcela(Label lblAsociacionAParcela) {
		this.lblAsociacionAParcela = lblAsociacionAParcela;
	}

	public Label getLblPersonaPropietaria() {
		return lblPersonaPropietaria;
	}

	public void setLblPersonaPropietaria(Label lblPersonaPropietaria) {
		this.lblPersonaPropietaria = lblPersonaPropietaria;
	}

	public Label getLabel5() {
        return label5;
    }

    public void setLabel5(Label label5) {
        this.label5 = label5;
    }

    public Label getLblNombreDatoRegValuado() {
        return lblNombreDatoRegValuado;
    }

    public void setLblNombreDatoRegValuado(Label lblNombreDatoRegValuado) {
        this.lblNombreDatoRegValuado = lblNombreDatoRegValuado;
    }

    public Label getLblDatosPlantillaRegValuado() {
        return lblDatosPlantillaRegValuado;
    }

    public void setLblDatosPlantillaRegValuado(Label lblDatosPlantillaRegValuado) {
        this.lblDatosPlantillaRegValuado = lblDatosPlantillaRegValuado;
    }

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label label1) {
        this.label1 = label1;
    }

    public Label getLblNombreDato() {
        return lblNombreDato;
    }

    public void setLblNombreDato(Label lblNombreDato) {
        this.lblNombreDato = lblNombreDato;
    }

    public Label getLblDatosPlantilla() {
        return lblDatosPlantilla;
    }

    public void setLblDatosPlantilla(Label lblDatosPlantilla) {
        this.lblDatosPlantilla = lblDatosPlantilla;
    }

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }

    public Label getLabel4() {
        return label4;
    }

    public void setLabel4(Label l) {
        this.label4 = l;
    }
    //----------Buttons-----------------
    private Button btnAgregarAtributoDinamico = new Button();
    private Button btnModificarAtributoDinamico = new Button();
    private HtmlAjaxCommandButton btnQuitarAtributoDinamico = new HtmlAjaxCommandButton();
    private Button btnAgregarAtributoDinamicoRegValuado = new Button();
    private HtmlAjaxCommandButton btnQuitarAtributoDinamicoRegValuado = new HtmlAjaxCommandButton();
    private Button btnModificarAtributoDinamicoRegValuado = new Button();
    private Button btnQuitarTodos = new Button();
    
    public Button getBtnModificarAtributoDinamicoRegValuado() {
		return btnModificarAtributoDinamicoRegValuado;
	}

	public void setBtnModificarAtributoDinamicoRegValuado(
			Button btnModificarAtributoDinamicoRegValuado) {
		this.btnModificarAtributoDinamicoRegValuado = btnModificarAtributoDinamicoRegValuado;
	}

	public PanelGroup getGroupPanel2() {
		return groupPanel2;
	}

	public void setGroupPanel2(PanelGroup groupPanel2) {
		this.groupPanel2 = groupPanel2;
	}

	public StaticText getStSeparador1() {
		return stSeparador1;
	}

	public void setStSeparador1(StaticText stSeparador1) {
		this.stSeparador1 = stSeparador1;
	}

	public Button getBtnAgregarAtributoDinamicoRegValuado() {
		return btnAgregarAtributoDinamicoRegValuado;
	}

	public void setBtnAgregarAtributoDinamicoRegValuado(
			Button btnAgregarAtributoDinamicoRegValuado) {
		this.btnAgregarAtributoDinamicoRegValuado = btnAgregarAtributoDinamicoRegValuado;
	}

	public HtmlAjaxCommandButton getBtnQuitarAtributoDinamicoRegValuado() {
		return btnQuitarAtributoDinamicoRegValuado;
	}

	public void setBtnQuitarAtributoDinamicoRegValuado(
			HtmlAjaxCommandButton btnQuitarAtributoDinamicoRegValuado) {
		this.btnQuitarAtributoDinamicoRegValuado = btnQuitarAtributoDinamicoRegValuado;
	}

	public Button getBtnModificarAtributoDinamico() {
		return btnModificarAtributoDinamico;
	}

	public void setBtnModificarAtributoDinamico(Button btnModificarAtributoDinamico) {
		this.btnModificarAtributoDinamico = btnModificarAtributoDinamico;
	}

	public HtmlAjaxCommandButton getBtnQuitarAtributoDinamico() {
		return btnQuitarAtributoDinamico;
	}

	public void setBtnQuitarAtributoDinamico(HtmlAjaxCommandButton btnQuitarAtributoDinamico) {
		this.btnQuitarAtributoDinamico = btnQuitarAtributoDinamico;
	}
    
    public Button getBtnAgregarAtributoDinamico() {
		return btnAgregarAtributoDinamico;
	}

	public void setBtnAgregarAtributoDinamico(Button btnAgregarAtributoDinamico) {
		this.btnAgregarAtributoDinamico = btnAgregarAtributoDinamico;
	}

	public Button getBtnQuitarTodos() {
        return btnQuitarTodos;
    }

    public void setBtnQuitarTodos(Button btnQuitarTodos) {
        this.btnQuitarTodos = btnQuitarTodos;
    }

    //----------TextAreas-----------------
    //----------Table Components-----------------
    private TableRowGroup tableRowGroup1 = new TableRowGroup();
    private TableRowGroup tableRowGroup2 = new TableRowGroup();
    private Table table1 = new Table();
    private Table table2 = new Table();

    public TableRowGroup getTableRowGroup2() {
        return tableRowGroup2;
    }

    public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
        this.tableRowGroup2 = tableRowGroup2;
    }

    public Table getTable2() {
        return table2;
    }

    public void setTable2(Table table2) {
        this.table2 = table2;
    }

    public Table getTable1() {
        return table1;
    }

    public void setTable1(Table table1) {
        this.table1 = table1;
    }

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
        this.tableRowGroup1 = tableRowGroup1;
    }
    //-------Objects Lists------------------------
    private ObjectListDataProvider ldpDatosPlantilla = new ObjectListDataProvider();
    private ObjectListDataProvider ldpDatosPlantillaRegValuado = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpDatosPlantillaRegValuado() {
        return ldpDatosPlantillaRegValuado;
    }

    public void setLdpDatosPlantillaRegValuado(ObjectListDataProvider ldpDatosPlantillaRegValuado) {
        this.ldpDatosPlantillaRegValuado = ldpDatosPlantillaRegValuado;
    }

    public ObjectListDataProvider getLdpDatosPlantilla() {
        return ldpDatosPlantilla;
    }

    public void setLdpDatosPlantilla(ObjectListDataProvider ldpDatosPlantilla) {
        this.ldpDatosPlantilla = ldpDatosPlantilla;
    }
    //-------CheckBoxs-------------------------
    private Checkbox cbAsociacionAParcela = new Checkbox();
    private Checkbox cbPersonaPropietaria = new Checkbox();
    
    public Checkbox getCbAsociacionAParcela() {
		return cbAsociacionAParcela;
	}

	public void setCbAsociacionAParcela(Checkbox cbAsociacionAParcela) {
		this.cbAsociacionAParcela = cbAsociacionAParcela;
	}

	public Checkbox getCbPersonaPropietaria() {
		return cbPersonaPropietaria;
	}

	public void setCbPersonaPropietaria(Checkbox cbPersonaPropietaria) {
		this.cbPersonaPropietaria = cbPersonaPropietaria;
	}

	//--------RadioButtons-----------------------
    private RadioButton radioButton1 = new RadioButton();
    private RadioButton radioButton2 = new RadioButton();

    public RadioButton getRadioButton2() {
        return radioButton2;
    }

    public void setRadioButton2(RadioButton radioButton2) {
        this.radioButton2 = radioButton2;
    }

    public RadioButton getRadioButton1() {
        return radioButton1;
    }

    public void setRadioButton1(RadioButton radioButton1) {
        this.radioButton1 = radioButton1;
    }
    //-------TableColumns------------------------
    private TableColumn tableColumn1 = new TableColumn();
    private TableColumn tableColumn2 = new TableColumn();
    private TableColumn tableColumn5 = new TableColumn();
    private TableColumn tableColumn6 = new TableColumn();
    private TableColumn tableColumn7 = new TableColumn();
    private TableColumn tableColumn8 = new TableColumn();

    public TableColumn getTableColumn8() {
        return tableColumn8;
    }

    public void setTableColumn8(TableColumn tableColumn8) {
        this.tableColumn8 = tableColumn8;
    }

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tableColumn7) {
        this.tableColumn7 = tableColumn7;
    }

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tableColumn6) {
        this.tableColumn6 = tableColumn6;
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

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tableColumn5) {
        this.tableColumn5 = tableColumn5;
    }

    public ABMPlantillaObligacionTasaMenor() {
    }
    
	@Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new PlantillaDocumentoTasaMenor());  //0
        ep.getObjetos().add(ind++, new ArrayList()); //1 lista datos de plantilla
        ep.getObjetos().add(ind++, new PlantillaAtributoDinamico()); //2 para los datos de la plantilla
        ep.getObjetos().add(ind++, new ArrayList()); //3 lista datos registro valuado
        ep.getObjetos().add(ind++, new PlantillaAtributoDinamico()); //4 para los datos de registro valuados
        ep.getObjetos().add(ind++, null); //5 para saber si navego a seleccionar un atr dinamico de la plantilla o del reg valuado

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
        int ind = 0;
        PlantillaDocumentoTasaMenor locPlantillaDocumentoTasaMenor = (PlantillaDocumentoTasaMenor) this.obtenerObjetoDelElementoPila(ind++, PlantillaDocumentoTasaMenor.class);
        List listaDatosPlantilla = (List) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        PlantillaAtributoDinamico locPlantillaAtributoDinamico = (PlantillaAtributoDinamico) this.obtenerObjetoDelElementoPila(ind++, PlantillaAtributoDinamico.class);
        List listaDatosRegValuado = (List) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        PlantillaAtributoDinamico locPlantillaAtributoDinamico2 = (PlantillaAtributoDinamico) this.obtenerObjetoDelElementoPila(ind++, PlantillaAtributoDinamico.class);

        Object nombrePlantilla = this.getTfNombre().getText();
        Object nombreDato = this.getTfNombreDato().getText();
        Object nombreDatoRegValuado = this.getTfNombreDatoRegValuado().getText();

        if (nombrePlantilla != null && nombrePlantilla != "") {
            locPlantillaDocumentoTasaMenor.setNombre(nombrePlantilla.toString());
        } else {
            locPlantillaDocumentoTasaMenor.setNombre(null);
        }
        
        //NUEVO DATO------------------------------------------------------------
        if (nombreDato != null && nombreDato != "") {
            locPlantillaAtributoDinamico.setNombre(nombreDato.toString());
        }
        PlantillaAtributoDinamico.Tipo tipoDato = null;
        Object tipo = ddTipoDato.getSelected();
        if ((tipo != null) && (tipo.toString().length() > 0)) {
            tipoDato = PlantillaAtributoDinamico.Tipo.valueOf(tipo.toString());
            locPlantillaAtributoDinamico.setTipo(tipoDato);
        }
        //----------------------------------------------------------------------

        //NUEVO DATO REG VALUADO------------------------------------------------
        if (nombreDatoRegValuado != null && nombreDatoRegValuado != "") {
            locPlantillaAtributoDinamico2.setNombre(nombreDatoRegValuado.toString());
        }
        PlantillaAtributoDinamico.Tipo tipoDatoRegValuado = null;
        Object tipoRegValuado = ddTipoDatoRegValuado.getSelected();
        if ((tipoRegValuado != null) && (tipoRegValuado.toString().length() > 0)) {
            tipoDatoRegValuado = PlantillaAtributoDinamico.Tipo.valueOf(tipoRegValuado.toString());
            locPlantillaAtributoDinamico2.setTipo(tipoDatoRegValuado);
        }
        //----------------------------------------------------------------------

        locPlantillaDocumentoTasaMenor.setAsociacionAParcela(this.getCbAsociacionAParcela().isChecked());
        locPlantillaDocumentoTasaMenor.setPersonaPropietaria(this.getCbPersonaPropietaria().isChecked());
        
        locPlantillaDocumentoTasaMenor.setListaPlantillasAtributos(listaDatosPlantilla);
        locPlantillaDocumentoTasaMenor.setListaPlantillasRegistroValuado(listaDatosRegValuado);

        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, locPlantillaDocumentoTasaMenor);
        this.getElementoPila().getObjetos().set(ind++, listaDatosPlantilla);
        this.getElementoPila().getObjetos().set(ind++, locPlantillaAtributoDinamico);
        this.getElementoPila().getObjetos().set(ind++, listaDatosRegValuado);
        this.getElementoPila().getObjetos().set(ind++, locPlantillaAtributoDinamico2);
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
        PlantillaDocumentoTasaMenor locPlantillaDocumentoTasaMenor = null;
        List listaDatosPlantilla = null;
        PlantillaAtributoDinamico locPlantillaAtributoDinamico = null;
        List listaDatosRegValuado = null;
        PlantillaAtributoDinamico locPlantillaAtributoDinamico2 = null;

//        if (this.getRequestBean1().getObjetoSeleccion() != null) {
//            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();          
//        }

        int ind = 0;
        locPlantillaDocumentoTasaMenor = (PlantillaDocumentoTasaMenor) this.obtenerObjetoDelElementoPila(ind++, PlantillaDocumentoTasaMenor.class);
        listaDatosPlantilla = (List) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        locPlantillaAtributoDinamico = (PlantillaAtributoDinamico) this.obtenerObjetoDelElementoPila(ind++, PlantillaAtributoDinamico.class);
        listaDatosRegValuado = (List) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        locPlantillaAtributoDinamico2 = (PlantillaAtributoDinamico) this.obtenerObjetoDelElementoPila(ind++, PlantillaAtributoDinamico.class);

        if (locPlantillaDocumentoTasaMenor != null) {
            this.getTfNombre().setText(locPlantillaDocumentoTasaMenor.getNombre());
        }
        if (locPlantillaAtributoDinamico != null) {
            this.getTfNombreDato().setText(locPlantillaAtributoDinamico.getNombre());
            this.getDdTipoDato().setSelected(Util.getEnumNameFromString(String.valueOf(locPlantillaAtributoDinamico.getTipo())));
        }
        if (locPlantillaAtributoDinamico2 != null) {
            this.getTfNombreDatoRegValuado().setText(locPlantillaAtributoDinamico2.getNombre());
            this.getDdTipoDatoRegValuado().setSelected(Util.getEnumNameFromString(String.valueOf(locPlantillaAtributoDinamico2.getTipo())));
        }
        
        this.getCbAsociacionAParcela().setValue(locPlantillaDocumentoTasaMenor.isAsociacionAParcela());
        this.getCbPersonaPropietaria().setValue(locPlantillaDocumentoTasaMenor.isPersonaPropietaria());

        this.getObjectListDataProvider().setList(listaDatosPlantilla);
        this.setListaDelCommunication(listaDatosPlantilla);
        this.getObjectListDataProvider2().setList(listaDatosRegValuado);
        this.setListaDelCommunication2(listaDatosRegValuado);
    }

    private void limpiarObjetosUsados() {
        // CAMBIAR: Crear una instancia por cada objeto manejado en la pagina
        PlantillaAtributoDinamico locPlantillaAtributoDinamico = new PlantillaAtributoDinamico();
        PlantillaAtributoDinamico locPlantillaAtributoDinamico2 = new PlantillaAtributoDinamico();

        this.getElementoPila().getObjetos().set(2, locPlantillaAtributoDinamico);
        this.getElementoPila().getObjetos().set(4, locPlantillaAtributoDinamico2);

        this.getTfNombreDato().setText("");
        this.getTfNombreDatoRegValuado().setText("");
        this.getDdTipoDato().setSelected("");
        this.getDdTipoDatoDefaultOptions().setSelectedValue("");
        this.getDdTipoDatoRegValuado().setSelected("");
        this.getDdTipoDatoRegValuadoDefaultOptions().setSelectedValue("");
    }

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpDatosPlantilla();
    }

    private ObjectListDataProvider getObjectListDataProvider2() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpDatosPlantillaRegValuado();
    }

    private List getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getComunicationBean().getListaDatosPlantillaObligacionTasaMenor();
    }

    private void setListaDelCommunication(List lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getComunicationBean().setListaDatosPlantillaObligacionTasaMenor(lista);
    }

    private List getListaDelCommunication2() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getComunicationBean().getListaDatosRegValuadoPlantillaObligacionTasaMenor();
    }

    private void setListaDelCommunication2(List lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getComunicationBean().setListaDatosRegValuadoPlantillaObligacionTasaMenor(lista);
    }
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
    
    public String getCurrentRow() {
        return tableRowGroup1.getRowKey().getRowId();
    }

    public void setCurrentRow(int row) {
    }

    public String getCurrentRow2() {
        return tableRowGroup2.getRowKey().getRowId();
    }

    public void setCurrentRow2(int row) {
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

    public Object getRBSelected2() {
        String sv = (String) radioButton2.getSelectedValue();
        return sv.equals(lastSelected) ? sv : null;
    }

    public void setRBSelected2(Object selected) {
        if (selected != null) {
            lastSelected = selected;
        }

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

    public RowKey getRegValuadoSeleccionado() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup2");
            rk = this.getObjectListDataProvider2().getRowKey(aRowId);
        } catch (Exception ex) {
        }
        return rk;
    }

    private List crearListaAtributosDinamicos(List list) {
        List locListaAtributosDinamicos = new ArrayList();
        for (Object object : list) {
            PlantillaAtributoDinamico locPlantillaAtributoDinamico = (PlantillaAtributoDinamico) object;
            locListaAtributosDinamicos.add(locPlantillaAtributoDinamico);
        }
        return locListaAtributosDinamicos;
    }

    public String btnAgregarAtributoDinamico_action() {    	
    	String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		this.guardarEstadoObjetosUsados();

		if (ultimo) {
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setAbmController(new AtributoDinamicoModel(). new AgregarAtributoDinamicoObjetoSeleccionController());
			retorno = "ABMAtributoDinamico";
			this.getElementoPila().getObjetos().set(5, "");
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
    }
    
    public String btnModificarAtributoDinamico_action() {
    	String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			RowKey rk = null;

			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el List DataProvider adecuado.
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					
					this.guardarEstadoObjetosUsados();
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoABM(obj);
					this.getRequestBean1().setAbmController(new AtributoDinamicoModel().new ModificarAtributoDinamicoObjetoSeleccionController());
					retorno = "ABMAtributoDinamico";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
    }

    public String btnModificarAtributoDinamicoRegValuado_action() {
    	String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			RowKey rk = null;

			try {
				rk = this.getRegValuadoSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el List DataProvider adecuado.
					Object obj = this.getObjectListDataProvider2().getObjects()[index];

					this.guardarEstadoObjetosUsados();
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoABM(obj);
					this.getRequestBean1().setAbmController(new AtributoDinamicoModel().new ModificarAtributoDinamicoObjetoSeleccionController());
					retorno = "ABMAtributoDinamico";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
    }
    
    public String btnQuitarAtributoDinamico_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;
            // APLICAR LOGICA AQUI...
            try {
                rk = this.getSeleccionado();
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    PlantillaAtributoDinamico locPlantillaAtributoDinamico = (PlantillaAtributoDinamico) obj;
                    this.getListaDelCommunication().remove(locPlantillaAtributoDinamico);
                } else {
                    warn("Debe seleccionar el dato que desea eliminar");
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

    public String btnAgregarAtributoDinamicoRegValuado_action() {  	
  	String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		this.guardarEstadoObjetosUsados();

		if (ultimo) {
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setAbmController(new AtributoDinamicoModel(). new AgregarAtributoDinamicoObjetoSeleccionController());
			this.getElementoPila().getObjetos().set(5, "reg valuado");
			retorno = "ABMAtributoDinamico";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
  }

    public String btnQuitarAtributoDinamicoRegValuado_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;
            // APLICAR LOGICA AQUI...
            try {
                rk = this.getRegValuadoSeleccionado();
                System.out.println("---RK: " + rk);
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProvider2().getObjects()[index];
                    PlantillaAtributoDinamico locPlantillaAtributoDinamico = (PlantillaAtributoDinamico) obj;
                    this.getListaDelCommunication2().remove(locPlantillaAtributoDinamico);
                } else {
                    warn("Debe seleccionar el dato que desea eliminar");
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

    public String btnQuitarTodos_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {

            // APLICAR LOGICA AQUI...
            try {
                this.getListaDelCommunication().clear();
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
//    public String btnModificarLineaSS_action() {
//
//        String retorno = null;
//        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
//        int posicionObjetoSeleccionado = 6;
//        LineaSolicitudSuministro lineaSeleccionada;
//
//        // APLICAR LOGICA AQUI...
//        this.guardarEstadoObjetosUsados();
//        this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
//        this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
//
//        if (ultimo) {
//            RowKey rk = null;
//
//            // APLICAR LOGICA AQUI...
//            try {
//
//                rk = this.getSeleccionado();
//
//                if (rk != null) {
//                    int index = getNroFila(rk.toString());
//                    Object obj = this.getObjectListDataProvider().getObjects()[index];
//                    this.getRequestBean1().setObjetoABM(obj);
//                    this.setRowKeySeleccionado(this.getSeleccionado());
//                    lineaSeleccionada = (LineaSolicitudSuministro) obj;
//                }
//
//            } catch (Exception ex) {
//                log(CASO_NAVEGACION + "_ModificarError:", ex);
//                error(NOMBRE_PAGINA + " - Modificar: " + ex.getMessage());
//                ex.printStackTrace();
//            }
//
//            this.guardarEstadoObjetosUsados();
//
//            this.guardarOrdenamiento();
//            //Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
//            //   this.getElementoPila().setPosicionGlobal(pos.longValue());
//            if (rk != null) {
//                retorno = "ModificarLineaSolicitudSuministro";
//            }
//        } else {
//            retorno = this.prepararCaducidad();
//        }
//        return retorno;
//    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof PlantillaAtributoDinamico){
			PlantillaAtributoDinamico locPlantilla = (PlantillaAtributoDinamico) pObject;
			locPlantilla.setIdRecurso(DocumentoTasaMenor.serialVersionUID);
			String stringAtributoDinamicoSeleccionado = this.obtenerObjetoDelElementoPila(5, String.class);
			
			if(stringAtributoDinamicoSeleccionado.equals("")){
				List listaDatosPlantilla = this.obtenerObjetoDelElementoPila(1, ArrayList.class);
				listaDatosPlantilla.add(locPlantilla);
				this.getElementoPila().getObjetos().set(1, listaDatosPlantilla);
			} else{
				List listaDatosRegValuado = this.obtenerObjetoDelElementoPila(3, ArrayList.class);
				listaDatosRegValuado.add(locPlantilla);
				this.getElementoPila().getObjetos().set(3, listaDatosRegValuado);
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		List listaDatosPlantilla = null;
		List listaDatosRegValuado = null;
		if (pObject instanceof PlantillaDocumentoTasaMenor) {
			PlantillaDocumentoTasaMenor locPlantillaDocumentoTasaMenor = (PlantillaDocumentoTasaMenor) this.getRequestBean1().getObjetoABM();
            if (locPlantillaDocumentoTasaMenor.getListaPlantillasAtributos() != null && !locPlantillaDocumentoTasaMenor.getListaPlantillasAtributos().isEmpty()) {
                listaDatosPlantilla = locPlantillaDocumentoTasaMenor.getListaPlantillasAtributos();
            }

            if (locPlantillaDocumentoTasaMenor.getListaPlantillasRegistroValuado() != null && !locPlantillaDocumentoTasaMenor.getListaPlantillasRegistroValuado().isEmpty()) {
                listaDatosRegValuado = locPlantillaDocumentoTasaMenor.getListaPlantillasRegistroValuado();
            }

            this.getElementoPila().getObjetos().set(0, locPlantillaDocumentoTasaMenor);
            this.getElementoPila().getObjetos().set(1, listaDatosPlantilla);
            this.getElementoPila().getObjetos().set(3, listaDatosRegValuado);
        }
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMPlantillaObligacionTasaMenor";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		PlantillaDocumentoTasaMenor locPlantilla = this.obtenerObjetoDelElementoPila(0, PlantillaDocumentoTasaMenor.class);
		this.getTablaLogs().getLdpLogs().setList(locPlantilla.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return PlantillaDocumentoTasaMenor.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor}";
	}
}
