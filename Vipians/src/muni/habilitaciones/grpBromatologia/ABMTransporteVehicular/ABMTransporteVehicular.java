/*
 * ABMTransporteVehicular.java
 *
 * Created on 24 de octubre de 2006, 10:40
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpBromatologia.ABMTransporteVehicular;

import java.util.ArrayList;

import javax.faces.convert.DateTimeConverter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
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
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.habilitaciones.recurso.persistent.shps.InspeccionVehicular;
import com.trascender.habilitaciones.recurso.persistent.shps.TransporteVehicular;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;


/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ABMTransporteVehicular extends ABMPageBean {
    
    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">    
    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Modificar Transporte Vehicular";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ModificarTransporteVehicular";
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
        
        dateTimeConverter1.setTimeStyle("short");
        
    }
    
    private TextField tfNumeroInscripcion = new TextField();
    
    public TextField getTfNumeroInscripcion() {
        return tfNumeroInscripcion;
    }
    
    public void setTfNumeroInscripcion(TextField tf) {
        this.tfNumeroInscripcion = tf;
    }
    
    private Label label4 = new Label();
    
    public Label getLabel4() {
        return label4;
    }
    
    public void setLabel4(Label l) {
        this.label4 = l;
    }
    
    private Label label5 = new Label();
    
    public Label getLabel5() {
        return label5;
    }
    
    public void setLabel5(Label l) {
        this.label5 = l;
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
    
    private TextField tfVehiculo = new TextField();
    
    public TextField getTfVehiculo() {
        return tfVehiculo;
    }
    
    public void setTfVehiculo(TextField tf) {
        this.tfVehiculo = tf;
    }
    
    private Button btnSeleccionarVehiculo = new Button();
    
    public Button getBtnSeleccionarVehiculo() {
        return btnSeleccionarVehiculo;
    }
    
    public void setBtnSeleccionarVehiculo(Button b) {
        this.btnSeleccionarVehiculo = b;
    }

    private TextArea taDescripcion = new TextArea();

    public TextArea getTaDescripcion() {
        return taDescripcion;
    }

    public void setTaDescripcion(TextArea ta) {
        this.taDescripcion = ta;
    }

    private HtmlAjaxCommandButton btnLimpiarVehiculo = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnLimpiarVehiculo() {
		return btnLimpiarVehiculo;
	}

	public void setBtnLimpiarVehiculo(HtmlAjaxCommandButton btnLimpiarVehiculo) {
		this.btnLimpiarVehiculo = btnLimpiarVehiculo;
	}

	private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }

    private TextField tfFechaBaja = new TextField();

    public TextField getTfFechaBaja() {
        return tfFechaBaja;
    }

    public void setTfFechaBaja(TextField tf) {
        this.tfFechaBaja = tf;
    }

    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label l) {
        this.label3 = l;
    }

    private TextField tfFechaAlta = new TextField();

    public TextField getTfFechaAlta() {
        return tfFechaAlta;
    }

    public void setTfFechaAlta(TextField tf) {
        this.tfFechaAlta = tf;
    }

    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }

    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
    }

    private Label label6 = new Label();

    public Label getLabel6() {
        return label6;
    }

    public void setLabel6(Label l) {
        this.label6 = l;
    }

    private ObjectListDataProvider ldpInspeccionTransporteVehicular = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpInspeccionTransporteVehicular() {
        return ldpInspeccionTransporteVehicular;
    }

    public void setLdpInspeccionTransporteVehicular(ObjectListDataProvider oldp) {
        this.ldpInspeccionTransporteVehicular = oldp;
    }

    private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

    public DateTimeConverter getDateTimeConverter1() {
        return dateTimeConverter1;
    }

    public void setDateTimeConverter1(DateTimeConverter dtc) {
        this.dateTimeConverter1 = dtc;
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

    private PanelGroup groupPanel1 = new PanelGroup();

    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup pg) {
        this.groupPanel1 = pg;
    }

    private Button btnAgregarInspeccion = new Button();

    public Button getBtnAgregarInspeccion() {
        return btnAgregarInspeccion;
    }

    public void setBtnAgregarInspeccion(Button b) {
        this.btnAgregarInspeccion = b;
    }

    protected HtmlAjaxCommandButton btnQuitarInspeccion = new HtmlAjaxCommandButton();

    public HtmlAjaxCommandButton getBtnQuitarInspeccion() {
		return btnQuitarInspeccion;
	}

	public void setBtnQuitarInspeccion(HtmlAjaxCommandButton btnQuitarInspeccion) {
		this.btnQuitarInspeccion = btnQuitarInspeccion;
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

    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }

    private TableColumn tableColumn4 = new TableColumn();

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tc) {
        this.tableColumn4 = tc;
    }

    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }

    private Button btnConsultarInspeccion = new Button();

    public Button getBtnConsultarInspeccion() {
        return btnConsultarInspeccion;
    }

    public void setBtnConsultarInspeccion(Button b) {
        this.btnConsultarInspeccion = b;
    }

    private StaticText staticText6 = new StaticText();

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText st) {
        this.staticText6 = st;
    }
    // </editor-fold>

    /** 
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public ABMTransporteVehicular() {
    }
    
    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        
        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new TransporteVehicular());
        ep.getObjetos().add(ind++, new Vehiculo());
        ep.getObjetos().add(ind++, new ArrayList());        // Tabla 1: Inspecciones Vehiculares

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }
    
    @Override
    protected void guardarEstadoObjetosUsados() {
        int ind = 0;
        // CAMBIAR: Revisar el metodo completo.
        TransporteVehicular transporteVehicular = (TransporteVehicular) this.obtenerObjetoDelElementoPila(ind++, TransporteVehicular.class);
        Vehiculo vehiculo = (Vehiculo) this.obtenerObjetoDelElementoPila(ind++, Vehiculo.class);
        ArrayList inspeccionesVehiculares = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        
        transporteVehicular.setNumeroInscripcion(getTextFieldValue(this.getTfNumeroInscripcion()));
        transporteVehicular.setFechaAlta(Conversor.getFechaCortaDeString(getTextFieldValue(this.getTfFechaAlta())));
        transporteVehicular.setFechaBaja(Conversor.getFechaCortaDeString(getTextFieldValue(this.getTfFechaBaja())));
        transporteVehicular.setDescripcion(getTextAreaValue(this.getTaDescripcion()));
        
        if(vehiculo.getIdVehiculo()==-1) vehiculo = null;
        transporteVehicular.setVehiculo(vehiculo);
        
        this.getObjectListDataProvider().commitChanges();
        inspeccionesVehiculares = (ArrayList) this.getObjectListDataProvider().getList();
        if (inspeccionesVehiculares.isEmpty()) inspeccionesVehiculares = null;
        if (transporteVehicular.getListaInspeccionesVehiculares() != null) {
            transporteVehicular.getListaInspeccionesVehiculares().clear();
            if (inspeccionesVehiculares != null) transporteVehicular.getListaInspeccionesVehiculares().addAll(inspeccionesVehiculares);
        }
        this.setListaDelCommunication(inspeccionesVehiculares);
        
        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, transporteVehicular);
        this.getElementoPila().getObjetos().set(ind++, vehiculo);
        this.getElementoPila().getObjetos().set(ind++, inspeccionesVehiculares);
    }
    
    @Override
    protected void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        TransporteVehicular transporteVehicular = null;
        Vehiculo vehiculo = null;
        ArrayList inspeccionesVehiculares = null;
        
        if (this.getRequestBean1().getRespuestaABM() != null) {
            Object respuesta = this.getRequestBean1().getRespuestaABM();
            int posicionEP = -1;
            ArrayList arrayListCorrespondiente = null;
            if (respuesta instanceof InspeccionVehicular) {
                posicionEP = 2;
            }
            if (posicionEP != -1) {
                arrayListCorrespondiente = (ArrayList) this.obtenerObjetoDelElementoPila(posicionEP, ArrayList.class);
                arrayListCorrespondiente.add(respuesta);
                this.getElementoPila().getObjetos().set(posicionEP, arrayListCorrespondiente);
            }
        }

        this.acomodarSeleccionado();
        
        ind = 0;
        transporteVehicular = (TransporteVehicular) this.obtenerObjetoDelElementoPila(ind++, TransporteVehicular.class);
        vehiculo = (Vehiculo) this.obtenerObjetoDelElementoPila(ind++, Vehiculo.class);
        inspeccionesVehiculares = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        
        this.getTfNumeroInscripcion().setText(transporteVehicular.getNumeroInscripcion());
        if (transporteVehicular.getFechaAlta() != null) this.getTfFechaAlta().setText(Conversor.getStringDeFechaCorta(transporteVehicular.getFechaAlta()));
        if (transporteVehicular.getFechaBaja() != null) this.getTfFechaBaja().setText(Conversor.getStringDeFechaCorta(transporteVehicular.getFechaBaja()));
        this.getTaDescripcion().setText(transporteVehicular.getDescripcion());
        
        this.getTfVehiculo().setText(vehiculo.toString());
        
        this.getObjectListDataProvider().setList(inspeccionesVehiculares);
        this.setListaDelCommunication(inspeccionesVehiculares);
    }

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpInspeccionTransporteVehicular();
    }
    
    private ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaInspeccionesTransporteVehicular();
    }
    
    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaInspeccionesTransporteVehicular(lista);
    }
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
    
    // <editor-fold defaultstate="collapsed" desc="Metodos para seleccionar la fila recientemente seleccionada">
    private RowKey rowKeySeleccionado = null;
    
    public RowKey getRowKeySeleccionado() {
        return rowKeySeleccionado;
    }
    
    public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
        this.rowKeySeleccionado = rowKeySeleccionado;
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
        } catch (Exception ex) {
            
        }
        return rk;
    }
    // </editor-fold>
    
    // </editor-fold>


    public String btnSeleccionarVehiculo_action() {
        return navegarParaSeleccionar("AdminVehiculo", 1);    
    }

    public String btnLimpiarVehiculo_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if(ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(1,this.getTfVehiculo());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }


    public String btnAgregarInspeccion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
            
            // APLICAR LOGICA AQUI...
            
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(0, TransporteVehicular.class));
            
            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AgregarInspeccionVehicular";
        } else {
            retorno = this.prepararCaducidad();
        }
        
        return retorno;
    }

    public String btnQuitarInspeccion_action() {
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

    public String btnConsultarInspeccion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        Object inspeccionSeleccionada = null;
       
        if (ultimo) {
            RowKey rk = null;

            // APLICAR LOGICA AQUI...
            try {
                rk = this.getSeleccionado();
                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    inspeccionSeleccionada = this.getObjectListDataProvider().getObjects()[index];
                }                
            } catch (Exception ex) {}

            this.guardarEstadoObjetosUsados();
            if (inspeccionSeleccionada != null) {
                this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
                this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(0, TransporteVehicular.class));
                this.getRequestBean1().setObjetoABM(inspeccionSeleccionada);
                retorno = "ConsultarInspeccionVehicular";
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    
    @Override
    protected String getCasoNavegacion() {
        return "ABMTransporteVehicular";
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		TransporteVehicular transporteVehicular = null;
        Vehiculo vehiculo = null;
        ArrayList inspeccionesVehiculares = null;
        
		transporteVehicular = (TransporteVehicular) this.getRequestBean1().getObjetoABM();
        //long idTransporteVehicular = transporteVehicular.getIdTransporteVehicular();

//        try {
//            this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().setLlave(this.getSessionBean1().getLlave());
//            transporteVehicular = this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().getTransporteVehicularPorId(idTransporteVehicular);
//        } catch (Exception ex) {
//            log(CASO_NAVEGACION+"_BuscarTransporteVehicularPorIdError:", ex);
//            error(NOMBRE_PAGINA+" - No se pudo recuperar el Transporte Vehicular: " + ex.getMessage());
//        }
        vehiculo = transporteVehicular.getVehiculo();
        inspeccionesVehiculares = new ArrayList(transporteVehicular.getListaInspeccionesVehiculares());
        
        int ind = 0;
        this.getElementoPila().getObjetos().set(ind++, transporteVehicular);
        this.getElementoPila().getObjetos().set(ind++, vehiculo);
        this.getElementoPila().getObjetos().set(ind++, inspeccionesVehiculares);
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		TransporteVehicular locTransporte = this.obtenerObjetoDelElementoPila(0, TransporteVehicular.class);
		this.getTablaLogs().getLdpLogs().setList(locTransporte.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return TransporteVehicular.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular}";
	}
}
