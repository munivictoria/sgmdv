/*
 * ABMOfertaLicitacion.java
 *
 * Created on 2 de noviembre de 2006, 11:21
 * Copyright Trascender SRL
 */
package muni.compras.ABMOfertaLicitacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import org.jboss.resource.metadata.LicenseMetaData;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
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
import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.compras.recurso.persistent.suministros.LineaOfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.OfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ABMOfertaLicitacion extends ABMPageBean {    
    
    private TextField tfNumero = new TextField();
    private TextField tfPlazoMantenimientoOferta = new TextField();
    private TextField tfExtensionMantenimientoOferta = new TextField();
    private TextField tfLicitacion = new TextField();
    private TextField tfProveedor = new TextField();
    private TextField tfFechaOferta = new TextField();
    private TextField tfImporte = new TextField();
    private TextField tfGarantia = new TextField();
    private TextField tfPlazo = new TextField();
    
    private Label lblFechaOferta = new Label();
    private Label lblImporte = new Label();
    private Label lblProveedor = new Label();
    private Label lblLicitacion = new Label();
    private Label lblGarantia = new Label();
    private Label lblComentarios = new Label();
    private Label lblPlazo = new Label();

    private StaticText stSeparador1 = new StaticText();
    private StaticText stPrecioUnitario = new StaticText();
    private StaticText staticText3 = new StaticText();
    private StaticText stPrecioTotal = new StaticText();
    private StaticText stComentarios = new StaticText();
    private StaticText stRenglonLicitacion = new StaticText();
    
    private TableColumn tcRenglonLicitacion = new TableColumn();
    private TableColumn tcPrecioUnitario = new TableColumn();
    private TableColumn tcPrecioTotal = new TableColumn();
    private TableColumn tcComentarios = new TableColumn();
    private TableColumn tableColumn1 = new TableColumn();
    
    private Button btnAgregar = new Button();
    private Button btnQuitar = new Button();
    private Button btnQuitarTodos = new Button();
    private Button btnSeleccionarLicitacion = new Button();
    private Button btnLimpiarLicitacion = new Button();
    private Button btnSeleccionarProveedor = new Button();
    private Button btnLimpiarProveedor = new Button();
    
    private DropDown ddTipoLicitacion = new DropDown();
    private DropDown ddTipoContratacion = new DropDown();
    private DropDown ddEstadoLicitacion = new DropDown();
    
    private SingleSelectOptionsList ddTipoLicitacionDefaultOptions = new SingleSelectOptionsList();
    private SingleSelectOptionsList ddTipoContratacionDefaultOptions = new SingleSelectOptionsList();
    private SingleSelectOptionsList ddEstadoLicitacionDefaultOptions = new SingleSelectOptionsList();
    
    private RadioButton radioButton1 = new RadioButton();
    private TextArea taComentariosOferta = new TextArea();
    private ObjectListDataProvider ldpOfertaRenglonesLicitacion = new ObjectListDataProvider();
    private Table table1 = new Table();
    private TableRowGroup tableRowGroup1 = new TableRowGroup();
    private PanelGroup groupPanel1 = new PanelGroup();
    private TabSet tabSet1 = new TabSet();
    
    private static long idRenglonOferta = -1;
    
    @Override
    protected void _init() throws Exception {
        Option[] opTipo = null;
        opTipo = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Contratacion.Tipo.values(), "may");
        ddTipoLicitacionDefaultOptions.setOptions(opTipo);
        Option[] opEstado = null;
        opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Contratacion.Estado.values(), "may");
        ddEstadoLicitacionDefaultOptions.setOptions(opEstado);
        if (this.getListaDelCommunication() != null) {
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }
    }
    

    public TextField getTfNumero() {
        return tfNumero;
    }

    public void setTfNumero(TextField tf) {
        this.tfNumero = tf;
    }
    

    public TextField getTfPlazoMantenimientoOferta() {
        return tfPlazoMantenimientoOferta;
    }

    public void setTfPlazoMantenimientoOferta(TextField tfPlazoMantenimientoOferta) {
        this.tfPlazoMantenimientoOferta = tfPlazoMantenimientoOferta;
    }
    

    public TextField getTfExtensionMantenimientoOferta() {
        return tfExtensionMantenimientoOferta;
    }

    public void setTfExtensionMantenimientoOferta(TextField tfExtensionMantenimientoOferta) {
        this.tfExtensionMantenimientoOferta = tfExtensionMantenimientoOferta;
    }
    

    public Label getLblFechaOferta() {
        return lblFechaOferta;
    }

    public void setLblFechaOferta(Label lblFechaOferta) {
        this.lblFechaOferta = lblFechaOferta;
    }
    

    public Label getLblImporte() {
        return lblImporte;
    }

    public void setLblImporte(Label lblImporte) {
        this.lblImporte = lblImporte;
    }
    
    public TextField getTfLicitacion() {
        return tfLicitacion;
    }

    public void setTfLicitacion(TextField tfLicitacion) {
        this.tfLicitacion = tfLicitacion;
    }
    

    public TextField getTfProveedor() {
        return tfProveedor;
    }

    public void setTfProveedor(TextField tfProveedor) {
        this.tfProveedor = tfProveedor;
    }
    

    public TextField getTfFechaOferta() {
        return tfFechaOferta;
    }

    public void setTfFechaOferta(TextField tfFechaOferta) {
        this.tfFechaOferta = tfFechaOferta;
    }
    

    public TextField getTfImporte() {
        return tfImporte;
    }

    public void setTfImporte(TextField tfImporte) {
        this.tfImporte = tfImporte;
    }
    
    public TextField getTfGarantia() {
        return tfGarantia;
    }

    public void setTfGarantia(TextField tfGarantia) {
        this.tfGarantia = tfGarantia;
    }
    

    public TextField getTfPlazo() {
        return tfPlazo;
    }

    public void setTfPlazo(TextField tfPlazo) {
        this.tfPlazo = tfPlazo;
    }
    

    public Label getLblProveedor() {
        return lblProveedor;
    }

    public void setLblProveedor(Label lblProveedor) {
        this.lblProveedor = lblProveedor;
    }
    

    public Label getLblLicitacion() {
        return lblLicitacion;
    }

    public void setLblLicitacion(Label lblLicitacion) {
        this.lblLicitacion = lblLicitacion;
    }
    

    public Label getLblGarantia() {
        return lblGarantia;
    }

    public void setLblGarantia(Label lblGarantia) {
        this.lblGarantia = lblGarantia;
    }
    

    public Label getLblComentarios() {
        return lblComentarios;
    }

    public void setLblComentarios(Label lblComentarios) {
        this.lblComentarios = lblComentarios;
    }
    

    public Label getLblPlazo() {
        return lblPlazo;
    }

    public void setLblPlazo(Label lblPlazo) {
        this.lblPlazo = lblPlazo;
    }

    public TextArea getTaComentariosOferta() {
        return taComentariosOferta;
    }

    public void setTaComentariosOferta(TextArea taComentariosOferta) {
        this.taComentariosOferta = taComentariosOferta;
    }

    public ObjectListDataProvider getLdpOfertaRenglonesLicitacion() {
        return ldpOfertaRenglonesLicitacion;
    }

    public void setLdpOfertaRenglonesLicitacion(ObjectListDataProvider oldp) {
        this.ldpOfertaRenglonesLicitacion = oldp;
    }
    

    public Table getTable1() {
        return table1;
    }

    public void setTable1(Table t) {
        this.table1 = t;
    }
    

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup trg) {
        this.tableRowGroup1 = trg;
    }
    

    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup pg) {
        this.groupPanel1 = pg;
    }
    

    public Button getBtnAgregar() {
        return btnAgregar;
    }

    public void setBtnAgregar(Button b) {
        this.btnAgregar = b;
    }
    

    public StaticText getStSeparador1() {
        return stSeparador1;
    }

    public void setStSeparador1(StaticText stSeparador1) {
        this.stSeparador1 = stSeparador1;
    }
    

    public StaticText getStPrecioUnitario() {
        return stPrecioUnitario;
    }

    public void setStPrecioUnitario(StaticText stPrecioUnitario) {
        this.stPrecioUnitario = stPrecioUnitario;
    }
    

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText staticText3) {
        this.staticText3 = staticText3;
    }
    

    public StaticText getStPrecioTotal() {
        return stPrecioTotal;
    }

    public void setStPrecioTotal(StaticText stPrecioTotal) {
        this.stPrecioTotal = stPrecioTotal;
    }
    

    public StaticText getStComentarios() {
        return stComentarios;
    }

    public void setStComentarios(StaticText stComentarios) {
        this.stComentarios = stComentarios;
    }
    

    public Button getBtnQuitar() {
        return btnQuitar;
    }

    public void setBtnQuitar(Button b) {
        this.btnQuitar = b;
    }
    

    public TableColumn getTableColumn1() {
        return tableColumn1;
    }

    public void setTableColumn1(TableColumn tc) {
        this.tableColumn1 = tc;
    }
    

    public RadioButton getRadioButton1() {
        return radioButton1;
    }

    public void setRadioButton1(RadioButton rb) {
        this.radioButton1 = rb;
    }
    

    public TableColumn getTcRenglonLicitacion() {
        return tcRenglonLicitacion;
    }

    public void setTcRenglonLicitacion(TableColumn tcRenglonLicitacion) {
        this.tcRenglonLicitacion = tcRenglonLicitacion;
    }
    

    public TableColumn getTcPrecioUnitario() {
        return tcPrecioUnitario;
    }

    public void setTcPrecioUnitario(TableColumn tcPrecioUnitario) {
        this.tcPrecioUnitario = tcPrecioUnitario;
    }
    

    public TableColumn getTcPrecioTotal() {
        return tcPrecioTotal;
    }

    public void setTcPrecioTotal(TableColumn tcPrecioTotal) {
        this.tcPrecioTotal = tcPrecioTotal;
    }
    

    public TableColumn getTcComentarios() {
        return tcComentarios;
    }

    public void setTcComentarios(TableColumn tcComentarios) {
        this.tcComentarios = tcComentarios;
    }
    

    public StaticText getStRenglonLicitacion() {
        return stRenglonLicitacion;
    }

    public void setStRenglonLicitacion(StaticText stRenglonLicitacion) {
        this.stRenglonLicitacion = stRenglonLicitacion;
    }
    

    public Button getBtnQuitarTodos() {
        return btnQuitarTodos;
    }

    public void setBtnQuitarTodos(Button b) {
        this.btnQuitarTodos = b;
    }
    

    public Button getBtnSeleccionarLicitacion() {
        return btnSeleccionarLicitacion;
    }

    public void setBtnSeleccionarLicitacion(Button btnSeleccionarLicitacion) {
        this.btnSeleccionarLicitacion = btnSeleccionarLicitacion;
    }
    

    public Button getBtnLimpiarLicitacion() {
        return btnLimpiarLicitacion;
    }

    public void setBtnLimpiarLicitacion(Button btnLimpiarLicitacion) {
        this.btnLimpiarLicitacion = btnLimpiarLicitacion;
    }
    

    public Button getBtnSeleccionarProveedor() {
        return btnSeleccionarProveedor;
    }

    public void setBtnSeleccionarProveedor(Button btnSeleccionarProveedor) {
        this.btnSeleccionarProveedor = btnSeleccionarProveedor;
    }
    

    public Button getBtnLimpiarProveedor() {
        return btnLimpiarProveedor;
    }

    public void setBtnLimpiarProveedor(Button btnLimpiarProveedor) {
        this.btnLimpiarProveedor = btnLimpiarProveedor;
    }
    

    public DropDown getDdTipoLicitacion() {
        return ddTipoLicitacion;
    }

    public void setDdTipoLicitacion(DropDown ddTipoLicitacion) {
        this.ddTipoLicitacion = ddTipoLicitacion;
    }

    public SingleSelectOptionsList getDdTipoLicitacionDefaultOptions() {
        return ddTipoLicitacionDefaultOptions;
    }

    public void setDdTipoLicitacionDefaultOptions(SingleSelectOptionsList ddTipoLicitacionDefaultOptions) {
        this.ddTipoLicitacionDefaultOptions = ddTipoLicitacionDefaultOptions;
    }
    

    public DropDown getDdTipoContratacion() {
        return ddTipoContratacion;
    }

    public void setDdTipoContratacion(DropDown ddTipoContratacion) {
        this.ddTipoContratacion = ddTipoContratacion;
    }

    public SingleSelectOptionsList getDdTipoContratacionDefaultOptions() {
        return ddTipoContratacionDefaultOptions;
    }

    public void setDdTipoContratacionDefaultOptions(SingleSelectOptionsList ddTipoContratacionDefaultOptions) {
        this.ddTipoContratacionDefaultOptions = ddTipoContratacionDefaultOptions;
    }
    

    public DropDown getDdEstadoLicitacion() {
        return ddEstadoLicitacion;
    }

    public void setDdEstadoLicitacion(DropDown ddEstadoLicitacion) {
        this.ddEstadoLicitacion = ddEstadoLicitacion;
    }

    public SingleSelectOptionsList getDdEstadoLicitacionDefaultOptions() {
        return ddEstadoLicitacionDefaultOptions;
    }

    public void setDdEstadoLicitacionDefaultOptions(SingleSelectOptionsList ddEstadoLicitacionDefaultOptions) {
        this.ddEstadoLicitacionDefaultOptions = ddEstadoLicitacionDefaultOptions;
    }
    

    public TabSet getTabSet1() {
        return tabSet1;
    }

    public void setTabSet1(TabSet tabSet1) {
        this.tabSet1 = tabSet1;
    }

    public ABMOfertaLicitacion() {
    }

    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;

        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new OfertaContratacion());
        ep.getObjetos().add(ind++, new Contratacion());
        ep.getObjetos().add(ind++, new Proveedor());
        ep.getObjetos().add(ind++, new ArrayList());// lista de renglones de la oferta de licitacion

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        OfertaContratacion ofertaLicitacion = (OfertaContratacion) this.obtenerObjetoDelElementoPila(ind++, OfertaContratacion.class);
        Contratacion contratacion = (Contratacion) this.obtenerObjetoDelElementoPila(ind++, Contratacion.class);
        Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(ind++, Proveedor.class);
        ArrayList listaOfertaRenglonLicitacion = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

        Object fechaOferta = this.getTfFechaOferta().getText();
        Object importe = this.getTfImporte().getText();
        Object garantia = this.getTfGarantia().getText();
        Object plazo = this.getTfPlazo().getText();
        Object comentario = this.getTaComentariosOferta().getText();

        if (fechaOferta != null && fechaOferta != "") {
//            ofertaLicitacion.setFechaOferta(Conversor.getFechaCortaDeString(fechaOferta.toString()));
        } else {
//            ofertaLicitacion.setFechaOferta(null);
        }
        if (importe != null && importe != "") {
//            ofertaLicitacion.setImporte(Conversor.getDoubleDeString(importe.toString()));
        } else {
//            ofertaLicitacion.setImporte(null);
        }

        if (garantia != null && garantia != "") {
            ofertaLicitacion.setGarantia(garantia.toString());
        } else {
            ofertaLicitacion.setGarantia(null);
        }

        if (plazo != null && plazo != "") {
            ofertaLicitacion.setPlazo(plazo.toString());
        } else {
            ofertaLicitacion.setPlazo(null);
        }

        if (comentario != null && comentario != "") {
            ofertaLicitacion.setComentarios(comentario.toString());
        } else {
            ofertaLicitacion.setComentarios(null);
        }


        if (contratacion.getIdContratacion() == -1) {
            contratacion = null;
        }
        ofertaLicitacion.setContratacion(contratacion);


        if (proveedor.getIdProveedor() == -1) {
            proveedor = null;
        }
        ofertaLicitacion.setProveedor(proveedor);

        if (listaOfertaRenglonLicitacion != null && !listaOfertaRenglonLicitacion.isEmpty()) {
            ofertaLicitacion.setListaLineasOfertasContratacion(listaOfertaRenglonLicitacion);
        }
        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, ofertaLicitacion);
        this.getElementoPila().getObjetos().set(ind++, contratacion);
        this.getElementoPila().getObjetos().set(ind++, proveedor);
        this.getElementoPila().getObjetos().set(ind++, listaOfertaRenglonLicitacion);
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.

        OfertaContratacion ofertaLicitacion = null;
        Contratacion contratacion = null;
        Proveedor proveedor = null;
        ArrayList listaOfertaRenglonLicitacion = null;
        LineaOfertaContratacion ofertaRenglonLicitacion = null;

        if (this.getRequestBean1().getObjetoSeleccion() != null) {
            Object seleccionado = this.getRequestBean1().getObjetoSeleccion();


            if (seleccionado instanceof Contratacion) {
                contratacion = (Contratacion) seleccionado;
                try{
                this.getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().setLlave(getSessionBean1().getLlave());
                contratacion = this.getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().getContratacionPorId(contratacion.getIdContratacion());
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                
                this.getElementoPila().getObjetos().set(1, contratacion);
                this.getRequestBean1().setObjetoSeleccion(null);
            } else if (seleccionado instanceof Proveedor) {
                proveedor = (Proveedor) seleccionado;
                this.getElementoPila().getObjetos().set(2, proveedor);
                this.getRequestBean1().setObjetoSeleccion(null);
            } else if (seleccionado instanceof LineaOfertaContratacion) {
                ofertaLicitacion = (OfertaContratacion) this.obtenerObjetoDelElementoPila(0, OfertaContratacion.class);
                listaOfertaRenglonLicitacion = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
                ofertaRenglonLicitacion = (LineaOfertaContratacion) seleccionado;
                ofertaRenglonLicitacion.setOfertaContratacion(ofertaLicitacion);

                // verifico que el RenglonLicitacion de la OfertaRenglonLicitacion no este en la lista, de encontrarse no lo agrega.-
                boolean renglonAsignado = false;
                for (Iterator it = listaOfertaRenglonLicitacion.iterator(); it.hasNext();) {
                    LineaOfertaContratacion locOfertaRenglonLicitacion = (LineaOfertaContratacion) it.next();
                    if (locOfertaRenglonLicitacion.getLineaContratacion() == ofertaRenglonLicitacion.getLineaContratacion()) {
                        renglonAsignado = true; //en este caso no lo agrego a la lista
                    }
                }
                if (renglonAsignado) {
                    warn("El Rengl\363n de Licitaci\363n asignado al Rengl\363n de la Oferta ya est\341 siendo usado.");
                } else {
                    ofertaRenglonLicitacion.setIdLineaOfertaContratacion(idRenglonOferta--);
                    listaOfertaRenglonLicitacion.add(ofertaRenglonLicitacion);
                }
                //
                this.getElementoPila().getObjetos().set(3, listaOfertaRenglonLicitacion);
                this.getRequestBean1().setObjetoSeleccion(null);
            }

        }

        //  this.acomodarSeleccionado();

//        if (this.getRequestBean1().getObjetoABM() != null) {
//            manzana = (Manzana) this.getRequestBean1().getObjetoABM();
//            //zona = manzana.getZona();
//            cuadras = (ArrayList) manzana.getListaCuadrasDelimitantes();
//            
//            ind = 0;
//            this.getElementoPila().getObjetos().set(ind++, manzana);
//            //this.getElementoPila().getObjetos().set(ind++, zona);
//            this.getElementoPila().getObjetos().set(ind++, cuadras);
//        }

        int ind = 0;
        ofertaLicitacion = (OfertaContratacion) this.obtenerObjetoDelElementoPila(ind++, OfertaContratacion.class);
        contratacion = (Contratacion) this.obtenerObjetoDelElementoPila(ind++, Contratacion.class);
        proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(ind++, Proveedor.class);
        listaOfertaRenglonLicitacion = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

        if (contratacion != null && contratacion.getIdContratacion() != -1) {
            this.getTfLicitacion().setText(contratacion.toString());
        }
        if (proveedor != null && proveedor.getIdProveedor() != -1) {
            this.getTfProveedor().setText(proveedor.toString());
        }
//        this.getTfFechaOferta().setText(Conversor.getStringDeFechaCorta(ofertaLicitacion.getFechaOferta()));
//        this.getTfImporte().setText(Conversor.getStringDeDouble(ofertaLicitacion.getImporte()));
        this.getTfGarantia().setText(ofertaLicitacion.getGarantia());
        this.getTfPlazo().setText(ofertaLicitacion.getPlazo());
        this.getTaComentariosOferta().setText(ofertaLicitacion.getComentarios());

        this.getObjectListDataProvider().setList(listaOfertaRenglonLicitacion);
        this.setListaDelCommunication(listaOfertaRenglonLicitacion);
    }

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpOfertaRenglonesLicitacion();
    }

    private List getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationComprasBean().getListaOfertaRenglonLicitacion();
    }

    private void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationComprasBean().setListaOfertaRenglonLicitacion(lista);
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

        if (rk != null) {
            while (!encontrado && inicioPagina < cantRegistros) {
                this.getTableRowGroup1().setFirst((int) inicioPagina);
                posicionEnPagina = 0;
                while (!encontrado && posicionEnPagina < cantRegistrosPorPagina) {
                    encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int) posicionEnPagina].equals(rk);
                    if (!encontrado) {
                        posicionEnPagina++;
                        posicionGlobal++;
                    }
                }
                if (!encontrado) {
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
        } catch (Exception ex) {
        }
        return rk;
    }

    public String btnAgregar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        this.guardarEstadoObjetosUsados();
        Contratacion contratacion = (Contratacion) this.obtenerObjetoDelElementoPila(1, Contratacion.class);
        ArrayList listaRenglonesLicitacion = new ArrayList();
        if (ultimo) {

            if (contratacion.getIdContratacion() != -1) {

//                listaRenglonesLicitacion.addAll(licitacion.getListaRenglonesLicitacion());
                this.getRequestBean1().setObjetosSeleccionMultiple(listaRenglonesLicitacion);
                this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

                // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
                retorno = "AgregarOfertaRenglonLicitacion";
            } else {
                warn("Seleccione una Licitaci\363n para agregar Renglones de Oferta.");
                return null;
            }
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

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnSeleccionarProveedor_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminProveedor";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnLimpiarProveedor_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            this.limpiarObjeto(2, Proveedor.class, this.getTfProveedor());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnSeleccionarLicitacion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminLicitacion";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnLimpiarLicitacion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            this.limpiarObjeto(1, Contratacion.class, this.getTfLicitacion());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    
    @Override
    protected String getNombrePagina() {
        return "Oferta Licitacion";
    }

    @Override
    protected String getCasoNavegacion() {
        return "ABMOfertaLicitacion";
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getNombreBean() {
		return "#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion}";
	}


	@Override
	public long getSerialVersionUID() {
		return OfertaContratacion.serialVersionUID;
	}
}