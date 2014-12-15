/*
 * ABMLibretaSanitaria.java
 *
 * Created on 3 de noviembre de 2006, 15:17
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpSHPS.ABMLibretaSanitaria;
 
import java.util.ArrayList;

import javax.faces.convert.DateTimeConverter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.habilitaciones.recurso.persistent.shps.ConstanciaVacunacion;
import com.trascender.habilitaciones.recurso.persistent.shps.InhabilitacionTemporariaLS;
import com.trascender.habilitaciones.recurso.persistent.shps.LibretaSanitaria;
import com.trascender.habilitaciones.recurso.persistent.shps.RenovacionLibretaSanitaria;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;


/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ABMLibretaSanitaria extends ABMPageBean {

    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Modificar Libreta Sanitaria";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ModificarLibretaSanitaria";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;

    // </editor-fold>    
    
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;
    
    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    @Override
    protected void _init() throws Exception {
        if (this.getListaDelCommunication()!=null) {
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }
        if (this.getListaDelCommunicationTabla2()!=null) {
            this.getObjectListDataProviderTabla2().setList(this.getListaDelCommunicationTabla2());
        }
        if (this.getListaDelCommunicationTabla3()!=null) {
            this.getObjectListDataProviderTabla3().setList(this.getListaDelCommunicationTabla3());
        }
        dateTimeConverter1.setTimeZone(null);
        dateTimeConverter1.setTimeStyle("short");
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
    
    private TextField tfPersonaFisica = new TextField();
    
    public TextField getTfPersonaFisica() {
        return tfPersonaFisica;
    }
    
    public void setTfPersonaFisica(TextField tf) {
        this.tfPersonaFisica = tf;
    }
    
    private MessageGroup messageGroup1 = new MessageGroup();
    
    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }
    
    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
    }
    
    private TextField tfNumeroLibretaSanitaria = new TextField();

    public TextField getTfNumeroLibretaSanitaria() {
        return tfNumeroLibretaSanitaria;
    }

    public void setTfNumeroLibretaSanitaria(TextField tf) {
        this.tfNumeroLibretaSanitaria = tf;
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

    private ObjectListDataProvider ldpRenovacionLibretaSanitaria = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpRenovacionLibretaSanitaria() {
        return ldpRenovacionLibretaSanitaria;
    }

    public void setLdpRenovacionLibretaSanitaria(ObjectListDataProvider oldp) {
        this.ldpRenovacionLibretaSanitaria = oldp;
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

    private TableColumn tableColumn3 = new TableColumn();

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tc) {
        this.tableColumn3 = tc;
    }

    private Label label7 = new Label();

    public Label getLabel7() {
        return label7;
    }

    public void setLabel7(Label l) {
        this.label7 = l;
    }

    private ObjectListDataProvider ldpConstanciaVacunacionLibretaSanitaria = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpConstanciaVacunacionLibretaSanitaria() {
        return ldpConstanciaVacunacionLibretaSanitaria;
    }

    public void setLdpConstanciaVacunacionLibretaSanitaria(ObjectListDataProvider oldp) {
        this.ldpConstanciaVacunacionLibretaSanitaria = oldp;
    }

    private ObjectListDataProvider ldpInhabilitacionTemporariaLibretaSanitaria = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpInhabilitacionTemporariaLibretaSanitaria() {
        return ldpInhabilitacionTemporariaLibretaSanitaria;
    }

    public void setLdpInhabilitacionTemporariaLibretaSanitaria(ObjectListDataProvider oldp) {
        this.ldpInhabilitacionTemporariaLibretaSanitaria = oldp;
    }

    private Label label27 = new Label();

    public Label getLabel27() {
        return label27;
    }

    public void setLabel27(Label l) {
        this.label27 = l;
    }

    private Label label28 = new Label();

    public Label getLabel28() {
        return label28;
    }

    public void setLabel28(Label l) {
        this.label28 = l;
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

    private Button btnAgregarConstanciaVacunacion = new Button();

    public Button getBtnAgregarConstanciaVacunacion() {
        return btnAgregarConstanciaVacunacion;
    }

    public void setBtnAgregarConstanciaVacunacion(Button b) {
        this.btnAgregarConstanciaVacunacion = b;
    }

    protected HtmlAjaxCommandButton btnQuitarConstanciaVacunacion = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnQuitarConstanciaVacunacion() {
		return btnQuitarConstanciaVacunacion;
	}

	public void setBtnQuitarConstanciaVacunacion(
			HtmlAjaxCommandButton btnQuitarConstanciaVacunacion) {
		this.btnQuitarConstanciaVacunacion = btnQuitarConstanciaVacunacion;
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

    private TableColumn tableColumn5 = new TableColumn();

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tc) {
        this.tableColumn5 = tc;
    }

    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }

    private TableColumn tableColumn6 = new TableColumn();

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tc) {
        this.tableColumn6 = tc;
    }

    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }

    private Table table3 = new Table();

    public Table getTable3() {
        return table3;
    }

    public void setTable3(Table t) {
        this.table3 = t;
    }

    private TableRowGroup tableRowGroup3 = new TableRowGroup();

    public TableRowGroup getTableRowGroup3() {
        return tableRowGroup3;
    }

    public void setTableRowGroup3(TableRowGroup trg) {
        this.tableRowGroup3 = trg;
    }

    private PanelGroup groupPanel2 = new PanelGroup();

    public PanelGroup getGroupPanel2() {
        return groupPanel2;
    }

    public void setGroupPanel2(PanelGroup pg) {
        this.groupPanel2 = pg;
    }

    private Button btnAgregarInhabilitacion = new Button();

    public Button getBtnAgregarInhabilitacion() {
        return btnAgregarInhabilitacion;
    }

    public void setBtnAgregarInhabilitacion(Button b) {
        this.btnAgregarInhabilitacion = b;
    }

    protected HtmlAjaxCommandButton btnQuitarInhabilitacion = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnQuitarInhabilitacion() {
		return btnQuitarInhabilitacion;
	}

	public void setBtnQuitarInhabilitacion(
			HtmlAjaxCommandButton btnQuitarInhabilitacion) {
		this.btnQuitarInhabilitacion = btnQuitarInhabilitacion;
	}
	private StaticText staticText10 = new StaticText();

    public StaticText getStaticText10() {
        return staticText10;
    }

    public void setStaticText10(StaticText st) {
        this.staticText10 = st;
    }

    private Button btnConsultarInhabilitacion = new Button();

    public Button getBtnConsultarInhabilitacion() {
        return btnConsultarInhabilitacion;
    }

    public void setBtnConsultarInhabilitacion(Button b) {
        this.btnConsultarInhabilitacion = b;
    }

    private TableColumn tableColumn7 = new TableColumn();

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tc) {
        this.tableColumn7 = tc;
    }

    private TableColumn tableColumn8 = new TableColumn();

    public TableColumn getTableColumn8() {
        return tableColumn8;
    }

    public void setTableColumn8(TableColumn tc) {
        this.tableColumn8 = tc;
    }

    private StaticText staticText8 = new StaticText();

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText st) {
        this.staticText8 = st;
    }

    private TableColumn tableColumn9 = new TableColumn();

    public TableColumn getTableColumn9() {
        return tableColumn9;
    }

    public void setTableColumn9(TableColumn tc) {
        this.tableColumn9 = tc;
    }

    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText st) {
        this.staticText9 = st;
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

    private RadioButton radioButton3 = new RadioButton();

    public RadioButton getRadioButton3() {
        return radioButton3;
    }

    public void setRadioButton3(RadioButton rb) {
        this.radioButton3 = rb;
    }

    private PanelGroup groupPanel3 = new PanelGroup();

    public PanelGroup getGroupPanel3() {
        return groupPanel3;
    }

    public void setGroupPanel3(PanelGroup pg) {
        this.groupPanel3 = pg;
    }

    private Button btnSeleccionarPersonaFisica = new Button();

    public Button getBtnSeleccionarPersonaFisica() {
        return btnSeleccionarPersonaFisica;
    }

    public void setBtnSeleccionarPersonaFisica(Button b) {
        this.btnSeleccionarPersonaFisica = b;
    }

    private HtmlAjaxCommandButton btnLimpiarPersonaFisica = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnLimpiarPersonaFisica() {
		return btnLimpiarPersonaFisica;
	}

	public void setBtnLimpiarPersonaFisica(HtmlAjaxCommandButton btnLimpiarPersonaFisica) {
		this.btnLimpiarPersonaFisica = btnLimpiarPersonaFisica;
	}
	private Button btnAgregarRenovacion = new Button();

    public Button getBtnAgregarRenovacion() {
        return btnAgregarRenovacion;
    }

    public void setBtnAgregarRenovacion(Button b) {
        this.btnAgregarRenovacion = b;
    }

    protected HtmlAjaxCommandButton btnQuitarRenovacion = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnQuitarRenovacion() {
		return btnQuitarRenovacion;
	}

	public void setBtnQuitarRenovacion(HtmlAjaxCommandButton btnQuitarRenovacion) {
		this.btnQuitarRenovacion = btnQuitarRenovacion;
	}
	private TableColumn tableColumn10 = new TableColumn();

    public TableColumn getTableColumn10() {
        return tableColumn10;
    }

    public void setTableColumn10(TableColumn tc) {
        this.tableColumn10 = tc;
    }

    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }

    private TableColumn tableColumn12 = new TableColumn();

    public TableColumn getTableColumn12() {
        return tableColumn12;
    }

    public void setTableColumn12(TableColumn tc) {
        this.tableColumn12 = tc;
    }

    private StaticText staticText6 = new StaticText();

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText st) {
        this.staticText6 = st;
    }

    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText st) {
        this.staticText7 = st;
    }

    private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

    public DateTimeConverter getDateTimeConverter1() {
        return dateTimeConverter1;
    }

    public void setDateTimeConverter1(DateTimeConverter dtc) {
        this.dateTimeConverter1 = dtc;
    }
    
    // </editor-fold>

    /** 
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public ABMLibretaSanitaria() {
    }
    
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        
        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new LibretaSanitaria());
        ep.getObjetos().add(ind++, new PersonaFisica());
        ep.getObjetos().add(ind++, new ArrayList());    // Tabla 1: renovaciones
        ep.getObjetos().add(ind++, new ArrayList());    // Tabla 2: constancias vacunacion
        ep.getObjetos().add(ind++, new ArrayList());    // Tabla 3: inhabilitaciones 

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }
    
    @Override
    protected void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        LibretaSanitaria libretaSanitaria = (LibretaSanitaria) this.obtenerObjetoDelElementoPila(ind++, LibretaSanitaria.class);
        PersonaFisica personaFisica = (PersonaFisica) this.obtenerObjetoDelElementoPila(ind++, PersonaFisica.class);
        ArrayList renovaciones = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        ArrayList constancias = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        ArrayList inhabilitaciones = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        
        Object numeroLibretaSanitaria = this.getTfNumeroLibretaSanitaria().getText();
        
        libretaSanitaria.setNumeroLibretaSanitaria(getTextFieldValueInteger(this.getTfNumeroLibretaSanitaria()));
        
        if (personaFisica.getIdPersonaFisica() == -1) personaFisica = null;
        libretaSanitaria.setPersonaFisica(personaFisica);

        this.getObjectListDataProvider().commitChanges();
        renovaciones = (ArrayList) this.getObjectListDataProvider().getList();
        if (renovaciones.size()<=0) renovaciones = null;
        if (libretaSanitaria.getListaRenovaciones() != null) {
            libretaSanitaria.getListaRenovaciones().clear();
            if (renovaciones != null) {libretaSanitaria.getListaRenovaciones().addAll(renovaciones);}
        }
        this.setListaDelCommunication(renovaciones);
        
        this.getObjectListDataProviderTabla2().commitChanges();
        constancias = (ArrayList) this.getObjectListDataProviderTabla2().getList();
        if (constancias.size()<=0) constancias = null;
        if (libretaSanitaria.getListaConstanciasVacunaciones() != null) {
            libretaSanitaria.getListaConstanciasVacunaciones().clear();
            if (constancias != null) {libretaSanitaria.getListaConstanciasVacunaciones().addAll(constancias);}
        }
        this.setListaDelCommunicationTabla2(constancias);
        
        this.getObjectListDataProviderTabla3().commitChanges();
        inhabilitaciones = (ArrayList) this.getObjectListDataProviderTabla3().getList();
        if (inhabilitaciones.size()<=0) inhabilitaciones = null;
        if (libretaSanitaria.getListaInhabilitacionesTemporarias() != null) {
            libretaSanitaria.getListaInhabilitacionesTemporarias().clear();
            if (inhabilitaciones != null) {libretaSanitaria.getListaInhabilitacionesTemporarias().addAll(inhabilitaciones);}
        }        
        this.setListaDelCommunicationTabla3(inhabilitaciones);
        
        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, libretaSanitaria);
        this.getElementoPila().getObjetos().set(ind++, personaFisica);
        this.getElementoPila().getObjetos().set(ind++, renovaciones);
        this.getElementoPila().getObjetos().set(ind++, constancias);
        this.getElementoPila().getObjetos().set(ind++, inhabilitaciones);
    }
    
    @Override
    protected void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        LibretaSanitaria libretaSanitaria = null;
        PersonaFisica personaFisica = null;
        ArrayList renovaciones = null;
        ArrayList constancias = null;
        ArrayList inhabilitaciones = null;
       
        if (this.getRequestBean1().getRespuestaABM() != null) {
            Object respuesta = this.getRequestBean1().getRespuestaABM();
            int posicionEP = -1;
            ArrayList arrayListCorrespondiente = null;
            if (respuesta instanceof RenovacionLibretaSanitaria) {
                posicionEP = 2;
            }
            if (respuesta instanceof ConstanciaVacunacion) {
                posicionEP = 3;
            }
            if (respuesta instanceof InhabilitacionTemporariaLS) {
                posicionEP = 4;
            }
            if (posicionEP != -1) {
                arrayListCorrespondiente = (ArrayList) this.obtenerObjetoDelElementoPila(posicionEP, ArrayList.class);
                arrayListCorrespondiente.add(respuesta);
                this.getElementoPila().getObjetos().set(posicionEP, arrayListCorrespondiente);
            }
        }
        
        ind = 0;
        libretaSanitaria = (LibretaSanitaria) this.obtenerObjetoDelElementoPila(ind++, LibretaSanitaria.class);
        personaFisica = (PersonaFisica) this.obtenerObjetoDelElementoPila(ind++, PersonaFisica.class);
        renovaciones = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        constancias = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        inhabilitaciones = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);        

        if (libretaSanitaria.getNumeroLibretaSanitaria() != null) this.getTfNumeroLibretaSanitaria().setText(libretaSanitaria.getNumeroLibretaSanitaria().toString());
        this.getTfPersonaFisica().setText(personaFisica.toString());
        
        this.getObjectListDataProvider().setList(renovaciones);
        this.setListaDelCommunication(renovaciones);
        // Tabla 2
        this.getObjectListDataProviderTabla2().setList(constancias);
        this.setListaDelCommunicationTabla2(constancias);
        // Tabla 3
        this.getObjectListDataProviderTabla3().setList(inhabilitaciones);
        this.setListaDelCommunicationTabla3(inhabilitaciones);
    }
    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpRenovacionLibretaSanitaria();
    }
    
    private ObjectListDataProvider getObjectListDataProviderTabla2() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpConstanciaVacunacionLibretaSanitaria();
    }
    
    private ObjectListDataProvider getObjectListDataProviderTabla3() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpInhabilitacionTemporariaLibretaSanitaria();
    }    
    
    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaRenovacionesLibretaSanitaria();
    }
    
    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaRenovacionesLibretaSanitaria(lista);
    }
    // Tabla 2
    private ArrayList getListaDelCommunicationTabla2() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaConstanciasVacunacionesLibretaSanitaria();
    }
    
    private void setListaDelCommunicationTabla2(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaConstanciasVacunacionesLibretaSanitaria(lista);
    }
    // Tabla 3
    private ArrayList getListaDelCommunicationTabla3() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaInhabilitacionesTemporariasLibretaSanitaria();
    }
    
    private void setListaDelCommunicationTabla3(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaInhabilitacionesTemporariasLibretaSanitaria(lista);
    }    
    // </editor-fold>
    private void limpiarTabla() {
        this.getObjectListDataProvider().getList().clear();
    }
    // Tabla 2
    private void limpiarTabla2() {
        this.getObjectListDataProviderTabla2().getList().clear();
    }
    // Tabla 3
    private void limpiarTabla3() {
        this.getObjectListDataProviderTabla3().getList().clear();
    }    
    
    public void limpiarObjeto(int posicion, TextField campo) {
        try {
            this.getElementoPila().getObjetos().set(posicion, null);
            if (campo!=null) campo.setText(null);
        } catch (Exception ex) { }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Metodos para seleccionar RaddioButton">
    public String getCurrentRow() {
        return tableRowGroup1.getRowKey().getRowId();
    }
    public void setCurrentRow(int row) {
    }
    private Object lastSelected = null;
    public Object getRBSelected() {
        String sv = (String)radioButton1.getSelectedValue();
        return sv.equals(lastSelected) ? sv : null;
    }
    public void setRBSelected(Object selected){
        if (selected != null) {
            lastSelected = selected;
        }
    }
    // Tabla 2
    public String getCurrentRow2() {
        return tableRowGroup2.getRowKey().getRowId();
    }
    public void setCurrentRow2(int row) {
    }
    private Object lastSelected2 = null;
    public Object getRBSelected2() {
        String sv = (String)radioButton2.getSelectedValue();
        return sv.equals(lastSelected2) ? sv : null;
    }
    public void setRBSelected2(Object selected){
        if (selected != null) {
            lastSelected2 = selected;
        }
    }
    // Tabla 3
    public String getCurrentRow3() {
        return tableRowGroup3.getRowKey().getRowId();
    }
    public void setCurrentRow3(int row) {
    }
    private Object lastSelected3 = null;
    public Object getRBSelected3() {
        String sv = (String)radioButton3.getSelectedValue();
        return sv.equals(lastSelected3) ? sv : null;
    }
    public void setRBSelected3(Object selected){
        if (selected != null) {
            lastSelected3 = selected;
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
        
        if (rk!=null) {
            while (!encontrado && inicioPagina < cantRegistros) {
                this.getTableRowGroup1().setFirst((int)inicioPagina);
                posicionEnPagina = 0;
                while (!encontrado && posicionEnPagina < cantRegistrosPorPagina){
                    encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int)posicionEnPagina].equals(rk);
                    if (!encontrado) {
                        posicionEnPagina++;
                        posicionGlobal++;
                    }
                }
                if (!encontrado) inicioPagina += cantRegistrosPorPagina;
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

        if (cantFilas > 0) {
            // si hay al menos una fila
            if (posicionGlobal.longValue() >= cantFilas) {
                // si elimine la ultima fila, me posiciono en la anterior
                posicionGlobal = new Long(cantFilas - 1);
            };
            
            int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
            this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
            lastSelected = new Long(index).toString();
        }
    }
    
    public Long getInicioPagina(Long posicionGlobal) {
        long inicioPagina = 0 ;
        long posicionEnPagina = 0;
        long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
        
        inicioPagina = ( posicionGlobal.longValue() / cantRegistrosPorPagina ) * cantRegistrosPorPagina ;
        return new Long(inicioPagina);
    }
    
    public RowKey getSeleccionado() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup");
            rk = this.getObjectListDataProvider().getRowKey(aRowId);
        } catch (Exception ex) {}
        return rk;
    }

    // Tabla 2
    public RowKey getSeleccionado2() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup2");
            rk = this.getObjectListDataProviderTabla2().getRowKey(aRowId);
        } catch (Exception ex) {}
        return rk;
    }
    // Tabla 3
    public RowKey getSeleccionado3() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup3");
            rk = this.getObjectListDataProviderTabla3().getRowKey(aRowId);
        } catch (Exception ex) {}
        return rk;
    }
    // </editor-fold>
    
    // </editor-fold>
    
    public String btnSeleccionarPersonaFisica_action() {
    	return navegarParaSeleccionar("AdminPersonaFisica");
    }
    
    public String btnLimpiarPersonaFisica_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if(ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(1, this.getTfPersonaFisica());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }    
    
    
    public String btnAgregarRenovacion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
            
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(0, LibretaSanitaria.class));
            
            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AgregarRenovacionLibretaSanitaria";
        } else {
            retorno = this.prepararCaducidad();
        }
        
        return retorno;
    }

    public String btnQuitarRenovacion_action() {
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
            } catch (Exception ex) {}
            
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    
    public String btnAgregarConstanciaVacunacion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
            
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(0, LibretaSanitaria.class));

            retorno = "AgregarConstanciaVacunacion";
        } else {
            retorno = this.prepararCaducidad();
        }
        
        return retorno;
    }
    
    public String btnQuitarConstanciaVacunacion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;

            try {
                rk = this.getSeleccionado2();
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProviderTabla2().getObjects()[index];
                    this.getListaDelCommunicationTabla2().remove(obj);
                }                
            } catch (Exception ex) {}
            
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnAgregarInhabilitacion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
                        
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(0, LibretaSanitaria.class));
            
            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AgregarInhabilitacionTemporariaLS";
        } else {
            retorno = this.prepararCaducidad();
        }
        
        return retorno;
    }
    
    public String btnQuitarInhabilitacion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;

            try {
                rk = this.getSeleccionado3();
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProviderTabla3().getObjects()[index];
                    this.getListaDelCommunicationTabla3().remove(obj);
                }                
            } catch (Exception ex) {}
            
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnConsultarInhabilitacion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        Object inhabilitacionSeleccionada = null;
        
        if (ultimo) {
            RowKey rk = null;

            try {
                rk = this.getSeleccionado3();
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    inhabilitacionSeleccionada = this.getObjectListDataProviderTabla3().getObjects()[index];
                }                
            } catch (Exception ex) {}

            this.guardarEstadoObjetosUsados();
            if (inhabilitacionSeleccionada != null) {
                this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
                this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(0, LibretaSanitaria.class));
                this.getRequestBean1().setObjetoABM(inhabilitacionSeleccionada);
                retorno = "ConsultarInhabilitacionTemporariaLS";
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    @Override
    protected String getCasoNavegacion() {
        return "ABMLibretaSanitaria";
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
        PersonaFisica personaFisica = null;
        
		if(pObject instanceof PersonaFisica){
			personaFisica = (PersonaFisica) pObject;
			this.getElementoPila().getObjetos().set(1, personaFisica);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		LibretaSanitaria libretaSanitaria = null;
        PersonaFisica personaFisica = null;
        ArrayList renovaciones = null;
        ArrayList constancias = null;
        ArrayList inhabilitaciones = null;
        
		libretaSanitaria = (LibretaSanitaria) pObject;

        Long idPersonaFisica = new Long(libretaSanitaria.getPersonaFisica().getIdPersonaFisica());
        try {
            this.getComunicationBean().getRemoteSystemPersonaFisica().setLlave(this.getSessionBean1().getLlave());
            personaFisica = this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaFisicaPorId(idPersonaFisica.longValue());
        } catch (Exception ex) {
            log(CASO_NAVEGACION+"_BuscarPersonaPorIdError:", ex);
            error(NOMBRE_PAGINA+" - No se pudo recuperar la Persona F\355sica vinculada a la Libreta Sanitaria: " + ex.getMessage());
        }
        
        // NOTA: ejecuto el m�todo que busca la Libreta por ID para traer las listas vinculadas que est�n Lazy
        Long idLibretaSanitaria = new Long(libretaSanitaria.getIdLibretaSanitaria());
        try {
            this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().setLlave(this.getSessionBean1().getLlave());
            libretaSanitaria = this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().getLibretaSanitariaPorId(idLibretaSanitaria.longValue());
        } catch (Exception ex) {
            log(CASO_NAVEGACION+"_BuscarLibretaSanitariaPorIdError:", ex);
            error(NOMBRE_PAGINA+" - No se pudo recuperar la Libreta Sanitaria: " + ex.getMessage());
        }
        
        renovaciones = new ArrayList(libretaSanitaria.getListaRenovaciones());
        constancias = new ArrayList(libretaSanitaria.getListaConstanciasVacunaciones());
        inhabilitaciones = new ArrayList(libretaSanitaria.getListaInhabilitacionesTemporarias());
        
        int ind = 0;
        this.getElementoPila().getObjetos().set(ind++, libretaSanitaria);
        this.getElementoPila().getObjetos().set(ind++, personaFisica);
        this.getElementoPila().getObjetos().set(ind++, renovaciones);
        this.getElementoPila().getObjetos().set(ind++, constancias);
        this.getElementoPila().getObjetos().set(ind++, inhabilitaciones);
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		LibretaSanitaria locLibreta = this.obtenerObjetoDelElementoPila(0, LibretaSanitaria.class);
		this.getTablaLogs().getLdpLogs().setList(locLibreta.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return LibretaSanitaria.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria}";
	}
}
