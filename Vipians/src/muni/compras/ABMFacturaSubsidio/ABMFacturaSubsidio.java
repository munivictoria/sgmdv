/*
 * ABMFacturaSubsidio.java
 *
 * Created on 11 de febrero de 2009, 08:00
 * Copyright Trascender SRL
 * 
 * Marcos
 */
package muni.compras.ABMFacturaSubsidio;

import java.util.ArrayList;
import java.util.Iterator;

import javax.faces.convert.NumberConverter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.persistent.suministros.BienProvisto;
import com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio;
import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;


public class ABMFacturaSubsidio extends ABMPageBean {
    
    protected Button btnAgregarLinea = new Button();
    protected Button btnQuitar = new Button();
    protected Button btnQuitarTodos = new Button();
    protected Button btnSeleccionarProveedor = new Button();
    protected Button btnSeleccionarDigestoMunicipal = new Button();
    protected HtmlAjaxCommandButton btnLimpiarDigestoMunicipal = new HtmlAjaxCommandButton();
    protected HtmlAjaxCommandButton btnLimpiarProveedor = new HtmlAjaxCommandButton();
    
    protected TextField tfCantidad = new TextField();
    protected TextField tfNumeroFactura = new TextField();
    protected TextField tfProveedor = new TextField();
    protected TextField tfDigestoMunicipal = new TextField();
    
    protected Label label1 = new Label();
    protected Label label4 = new Label();
    protected Label lblTotalF = new Label();
    protected Label lblNumeroFactura = new Label();
    
    protected StaticText stPrecio = new StaticText();
    protected StaticText stSeparador = new StaticText();
    protected StaticText staticText4 = new StaticText();
    protected StaticText staticText6 = new StaticText();
    protected StaticText staticText1 = new StaticText();
    protected StaticText stCuenta = new StaticText();
    
    protected NumberConverter numberConverter1 = new NumberConverter();

    protected PanelGroup groupPanel1 = new PanelGroup();
    

    protected ObjectListDataProvider ldpLineasFactura = new ObjectListDataProvider();
    protected Table table1 = new Table();
    protected SingleSelectOptionsList ddTipoFacturaDefaultOptions = new SingleSelectOptionsList();
    protected TableRowGroup tableRowGroup1 = new TableRowGroup();
    protected TableColumn tableColumn1 = new TableColumn();
    protected TableColumn tableColumn5 = new TableColumn();
    protected TableColumn tableColumn6 = new TableColumn();
    protected RadioButton radioButton1 = new RadioButton();
    protected TableColumn tableColumn2 = new TableColumn();
    
    
    protected TableColumn tableColumn3 = new TableColumn();
    protected StaticText staticText2 = new StaticText();
    protected TableColumn tableColumn4 = new TableColumn();
    protected StaticText stTotal = new StaticText();
    protected DropDown ddTipoFactura = new DropDown();
    protected TextField tfTotal = new TextField();  
    protected TextField tfMontoTotal = new TextField();
    protected TextField tfCodigoProveedor = new TextField();
    protected Label labelValor = new Label();
    protected Label labelConcepto = new Label();
    protected Label label5 = new Label();
    protected TextField tfFecha = new TextField();
    protected StaticText staticText5 = new StaticText();
    protected Object lastSelected = null;
    protected RowKey rowKeySeleccionado = null;
   
    @Override
    protected void _init() throws Exception {
        Option[] op = null;
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(FacturaSubsidio.TipoFactura.values(), "may");
        this.ddTipoFacturaDefaultOptions.setOptions(op);
        if (this.getListaDelCommunication() != null) {
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }
        numberConverter1.setPattern("$ #,##0.00");
        numberConverter1.setMinIntegerDigits(1);
        numberConverter1.setMaxIntegerDigits(40);
        numberConverter1.setMaxFractionDigits(3);

        //Intento limpiar la lista de lineas de factura porsi quedo con algo en el anterior llamado.
        //this.setListaDelCommunication(null);
    }

    //***GETTERS & SETTERS***

    public NumberConverter getNumberConverter1() {
        return numberConverter1;
    }

    public void setNumberConverter1(NumberConverter numberConverter1) {
        this.numberConverter1 = numberConverter1;
    }

    public StaticText getStPrecio() {
        return stPrecio;
    }

    public void setStPrecio(StaticText st) {
        this.stPrecio = st;
    }   

    public TextField getTfProveedor() {
        return tfProveedor;
    }

    public void setTfProveedor(TextField tf) {
        this.tfProveedor = tf;
    }

    public TextField getTfNumeroFactura() {
        return tfNumeroFactura;
    }

    public void setTfNumeroFactura(TextField tfNumeroFactura) {
        this.tfNumeroFactura = tfNumeroFactura;
    }

    public TextField getTfCantidad() {
        return tfCantidad;
    }

    public void setTfCantidad(TextField tf) {
        this.tfCantidad = tf;
    }
    
    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup groupPanel1) {
        this.groupPanel1 = groupPanel1;
    }
    
    public TextField getTfDigestoMunicipal() {
        return tfDigestoMunicipal;
    }

    public void setTfDigestoMunicipal(TextField tf) {
        this.tfDigestoMunicipal = tf;
    }
    
    public Label getLblTotalF() {
        return lblTotalF;
    }

    public void setLblTotalF(Label lblTotalF) {
        this.lblTotalF = lblTotalF;
    }
    
    public Label getLblNumeroFactura() {
        return lblNumeroFactura;
    }

    public void setLblNumeroFactura(Label lblNumeroFactura) {
        this.lblNumeroFactura = lblNumeroFactura;
    }

    public Label getLabel4() {
        return label4;
    }

    public void setLabel4(Label l) {
        this.label4 = l;
    }
    
    public Button getBtnAgregarLinea() {
        return btnAgregarLinea;
    }

    public void setBtnAgregarLinea(Button btnAgregarLinea) {
        this.btnAgregarLinea = btnAgregarLinea;
    }

    public Button getBtnQuitar() {
        return btnQuitar;
    }

    public void setBtnQuitar(Button btnQuitar) {
        this.btnQuitar = btnQuitar;
    }

    public Button getBtnQuitarTodos() {
        return btnQuitarTodos;
    }

    public void setBtnQuitarTodos(Button btnQuitarTodos) {
        this.btnQuitarTodos = btnQuitarTodos;
    }    
        
    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText staticText4) {
        this.staticText4 = staticText4;
    }

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText staticText6) {
        this.staticText6 = staticText6;
    }    

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }    

    public ObjectListDataProvider getLdpLineasFactura() {
        return ldpLineasFactura;
    }

    public void setLdpLineasFactura(ObjectListDataProvider oldp) {
        this.ldpLineasFactura = oldp;
    }    

    public Table getTable1() {
        return table1;
    }

    public void setTable1(Table t) {
        this.table1 = t;
    }
    
    public SingleSelectOptionsList getDdTipoFacturaDefaultOptions() {
        return ddTipoFacturaDefaultOptions;
    }

    public void setDdTipoFacturaDefaultOptions(SingleSelectOptionsList ssol) {
        this.ddTipoFacturaDefaultOptions = ssol;
    }    

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup trg) {
        this.tableRowGroup1 = trg;
    }
    
    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tableColumn6) {
        this.tableColumn6 = tableColumn6;
    }
    
    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tableColumn5) {
        this.tableColumn5 = tableColumn5;
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

    public TableColumn getTableColumn2() {
        return tableColumn2;
    }

    public void setTableColumn2(TableColumn tc) {
        this.tableColumn2 = tc;
    }
    
    public StaticText getStCuenta() {
        return stCuenta;
    }

    public void setStCuenta(StaticText stCuenta) {
        this.stCuenta = stCuenta;
    }

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    
    public Button getBtnSeleccionarProveedor() {
        return btnSeleccionarProveedor;
    }

    public void setBtnSeleccionarProveedor(Button b) {
        this.btnSeleccionarProveedor = b;
    }
    
    public Button getBtnSeleccionarDigestoMunicipal() {
        return btnSeleccionarDigestoMunicipal;
    }

    public void setBtnSeleccionarDigestoMunicipal(Button b) {
        this.btnSeleccionarDigestoMunicipal = b;
    }  

    public HtmlAjaxCommandButton getBtnLimpiarDigestoMunicipal() {
		return btnLimpiarDigestoMunicipal;
	}

	public void setBtnLimpiarDigestoMunicipal(
			HtmlAjaxCommandButton btnLimpiarDigestoMunicipal) {
		this.btnLimpiarDigestoMunicipal = btnLimpiarDigestoMunicipal;
	}

	public HtmlAjaxCommandButton getBtnLimpiarProveedor() {
		return btnLimpiarProveedor;
	}

	public void setBtnLimpiarProveedor(HtmlAjaxCommandButton btnLimpiarProveedor) {
		this.btnLimpiarProveedor = btnLimpiarProveedor;
	}

	public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tc) {
        this.tableColumn3 = tc;
    }    

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }
    
    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tc) {
        this.tableColumn4 = tc;
    }    

    public StaticText getStTotal() {
        return stTotal;
    }

    public void setStTotal(StaticText st) {
        this.stTotal = st;
    }
    
    public DropDown getDdTipoFactura() {
        return ddTipoFactura;
    }

    public void setDdTipoFactura(DropDown dd) {
        this.ddTipoFactura = dd;
    }
    
    public TextField getTfTotal() {
        return tfTotal;
    }

    public void setTfTotal(TextField tf) {
        this.tfTotal = tf;
    }    

    public TextField getTfMontoTotal() {
        return tfMontoTotal;
    }

    public void setTfMontoTotal(TextField tf) {
        this.tfMontoTotal = tf;
    }
    
    public TextField getTfCodigoProveedor() {
        return tfCodigoProveedor;
    }

    public void setTfCodigoProveedor(TextField tf) {
        this.tfCodigoProveedor = tf;
    }
    
    public Label getLabelValor() {
        return labelValor;
    }

    public void setLabelValor(Label lb) {
        this.labelValor = lb;
    }
    
    public Label getLabelConcepto() {
        return labelConcepto;
    }

    public void setLabelConcepto(Label lb) {
        this.labelConcepto = lb;
    }    

    public Label getLabel5() {
        return label5;
    }

    public void setLabel5(Label l) {
        this.label5 = l;
    }    

    public TextField getTfFecha() {
        return tfFecha;
    }

    public void setTfFecha(TextField tf) {
        this.tfFecha = tf;
    }
    
    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }
    
   //***METHODS***
    public ABMFacturaSubsidio() {
    }

   
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        ep.getObjetos().add(ind++, new FacturaSubsidio()); //0
        ep.getObjetos().add(ind++, new Proveedor()); // 1
        ep.getObjetos().add(ind++, new DigestoMunicipal()); // 2
        ep.getObjetos().add(ind++, new ArrayList()); // 3 ListaLineaFactura

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        FacturaSubsidio facturaSubsidio = (FacturaSubsidio) this.obtenerObjetoDelElementoPila(ind++, FacturaSubsidio.class);
        Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(ind++, Proveedor.class);
        DigestoMunicipal digestoMunicipal = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(ind++, DigestoMunicipal.class);

        FacturaSubsidio.TipoFactura tipoFactura = null;

        Object tipoFacturaSelected = this.ddTipoFactura.getSelected();
        Object codigoProveedor = this.tfCodigoProveedor.getText();
        Object fecha = tfFecha.getText();
        Object numero = this.getTfNumeroFactura().getText();

        if (codigoProveedor != null && !codigoProveedor.toString().trim().equals("")) {
            facturaSubsidio.setCodigoProveedor(codigoProveedor.toString());
        } else {
            facturaSubsidio.setCodigoProveedor(null);
        }
        if (proveedor.getIdProveedor() == -1) {
            proveedor = null;
        }
        facturaSubsidio.setProveedor(proveedor);

        if(numero != null && numero != ""){
           facturaSubsidio.setNumero(numero.toString());
        } else {
            facturaSubsidio.setNumero(null);
        }

        if ((tipoFacturaSelected != null) && tipoFacturaSelected.toString().length() > 0) {
            tipoFactura = FacturaSubsidio.TipoFactura.valueOf(tipoFacturaSelected.toString());
        } else {
            tipoFactura = null;
        }

        if (fecha != null && fecha != "") {
            facturaSubsidio.setFechaEmision(Conversor.getFechaCortaDeString(fecha.toString()));
        } else {
            facturaSubsidio.setFechaEmision(null);
        }

        if (digestoMunicipal.getIdDigestoMunicipal() == -1) {
            digestoMunicipal = null;
        }
        facturaSubsidio.setDigestoMunicipal(digestoMunicipal);
        if (tipoFactura != null) {
            facturaSubsidio.setTipoFactura(tipoFactura);
        }
        //TODO
        //this.getObjectListDataProvider().commitChanges();
        // remitos = (ArrayList) this.getObjectListDataProvider().getList();
        this.getObjectListDataProvider().commitChanges();
        ArrayList listaLineaFactura = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);

        if(listaLineaFactura.isEmpty()) {
            listaLineaFactura = null;
        }

        facturaSubsidio.setListaLineaFactura(listaLineaFactura);
        //facturaContrato.setListaOrdenesDeCompra(listaOrdenesCompra);
         try{
            facturaSubsidio.setTotal(null); // se calcula desde la logica
        } catch (Exception e) {
        }

        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, facturaSubsidio);
        this.getElementoPila().getObjetos().set(ind++, proveedor);
        this.getElementoPila().getObjetos().set(ind++, digestoMunicipal);
        this.getElementoPila().getObjetos().set(ind++, listaLineaFactura);
    }

    protected void agregarBienesProvistosALista() {
        Proveedor proveedor = (Proveedor) this.getElementoPila().getObjetos().get(1);
        ArrayList listaLineaFactura = new ArrayList();
        ArrayList locListaBienes = new ArrayList();
        locListaBienes.addAll(proveedor.getListaBienProvisto());

        Iterator iterator = locListaBienes.iterator();
        while (iterator.hasNext()) {

            BienProvisto locBienProvisto = (BienProvisto) iterator.next();
//            LineaFactura locLineaFactura = new LineaFactura();

//            locLineaFactura.setBienProvisto(locBienProvisto);
//            listaLineaFactura.add(locLineaFactura);
        }
        this.getElementoPila().getObjetos().set(3, listaLineaFactura);
    }

    protected void agregarBienesProvistosALista(ArrayList list) {

        Proveedor proveedor = (Proveedor) this.getElementoPila().getObjetos().get(1);
        ArrayList locListaBienesProveedor = (ArrayList) proveedor.getListaBienProvisto();

        ArrayList listaLineaFactura = new ArrayList();
        boolean bandera = false;

        //variables del while
        LineaFactura locLineaFactura;
        BienProvisto locBienProvistoProveedor;
        BienProvisto auxBienProvisto;

        Iterator iteratorListaBienesProveedor = locListaBienesProveedor.iterator();

        while (iteratorListaBienesProveedor.hasNext()) {
            locLineaFactura = null;
            auxBienProvisto = null;
            locBienProvistoProveedor = (BienProvisto) iteratorListaBienesProveedor.next();
            Iterator locIterator = list.iterator();

            while (locIterator.hasNext() && !bandera) {
                locLineaFactura = (LineaFactura) locIterator.next();
//                auxBienProvisto = locLineaFactura.getBienProvisto();

                if (auxBienProvisto.getIdBienProvisto() == locBienProvistoProveedor.getIdBienProvisto()) {
                    bandera = true;
                }
            }

            if (!bandera) {
//                locLineaFactura = new LineaFactura();
//                locLineaFactura.setBienProvisto(locBienProvistoProveedor);
            }

            listaLineaFactura.add(locLineaFactura);
            bandera = false;
        }

        this.getElementoPila().getObjetos().set(3, listaLineaFactura);
    }
    
    @Override
    protected void mostrarEstadoObjetosUsados() {
        System.out.println("LLAMANDO AL MOSTRAR ESTADO");
        // CAMBIAR: Revisar el metodo completo.
        int ind = 0;
        FacturaSubsidio facturaSubsidio = null;
        Proveedor proveedor = null;
        DigestoMunicipal digestoMunicipal = null;
        FacturaSubsidio.TipoFactura tipoFactura = null;
        ArrayList listaLineaFactura = null;

        try{
            facturaSubsidio = (FacturaSubsidio) this.obtenerObjetoDelElementoPila(0, FacturaSubsidio.class);
            proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(1, Proveedor.class);
            digestoMunicipal = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(2, DigestoMunicipal.class);
            listaLineaFactura = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
        }catch(Exception e){
            log("Error Description", e);
             e.printStackTrace();
        }

        if(facturaSubsidio.getFechaEmision() != null)  this.getTfFecha().setText(Conversor.getStringDeFechaCorta(facturaSubsidio.getFechaEmision()));
        if(proveedor != null)     this.getTfProveedor().setText(proveedor.getRazonSocial());
        if(facturaSubsidio.getNumero() != null)        this.getTfNumeroFactura().setText(facturaSubsidio.getNumero());
        if(facturaSubsidio.getCodigoProveedor() != null) this.getTfCodigoProveedor().setText(facturaSubsidio.getCodigoProveedor());
        if (digestoMunicipal != null && digestoMunicipal.getIdDigestoMunicipal() != -1) {
            this.getTfDigestoMunicipal().setText(digestoMunicipal.toString());
        }
        System.out.println("SUMANDO");
        double total = 0.0;
        if ( listaLineaFactura != null && !listaLineaFactura.isEmpty()) {

                for (int i = 0; i < listaLineaFactura.size(); i++) {
                    System.out.println("INDICE" + i);
                    LineaFactura cadaLineaFactura = (LineaFactura) listaLineaFactura.get(i);
                    total += cadaLineaFactura.getTotal().doubleValue();
                    try {
                        cadaLineaFactura.setTotal(null);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                this.getStTotal().setText(total);
        }

        this.getDdTipoFactura().setSelected(Util.getEnumNameFromString(String.valueOf(facturaSubsidio.getTipoFactura())));
        this.getDdTipoFacturaDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(facturaSubsidio.getTipoFactura())));

        this.getObjectListDataProvider().setList(listaLineaFactura);
        this.setListaDelCommunication(listaLineaFactura);
    
    }

    protected ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpLineasFactura();
    }

    public ArrayList getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationComprasBean().getListaLineasFacturaSubsidio();
    }

    protected void setListaDelCommunication(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationComprasBean().setListaLineasFacturaSubsidio(lista);
    }

    protected void acomodarSeleccionado() {
        Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
        if (seleccionado != null) {
            int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
            this.getElementoPila().getObjetos().set(posicion, seleccionado);
        }
    } 

    protected void limpiarTabla() {
        this.getObjectListDataProvider().getList().clear();
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

    protected int getNroFila(String pCadena) {
        // Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
        String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
        return new Integer(lCadenaAuxiliar).intValue();
    }

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

    public Long getPosicionEnTabla(
            RowKey rk) {
        long inicioPagina = 0;
        long posicionGlobal = 0;
        long posicionEnPagina = 0;
        long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
        long cantRegistros = this.getTableRowGroup1().getRowCount();
        boolean encontrado = false;

        if (rk != null) {
            while (!encontrado && inicioPagina < cantRegistros) {
                this.getTableRowGroup1().setFirst((int) inicioPagina);
                posicionEnPagina =
                        0;
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

    public RowKey getRowKeyAsociado(
            Long posicionEnTabla) {
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
            }
            ;

            int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
            this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
            lastSelected =
                    new Long(index).toString();
        }

    }

    public Long getInicioPagina(
            Long posicionGlobal) {
        long inicioPagina = 0;
        long posicionEnPagina = 0;
        long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();

        inicioPagina =
                (posicionGlobal.longValue() / cantRegistrosPorPagina) * cantRegistrosPorPagina;
        return new Long(inicioPagina);
    }

    public RowKey getSeleccionado() {
        RowKey rk = null;
        try {
            String aRowId = (String) RadioButton.getSelected("buttonGroup");
            rk =
                    this.getObjectListDataProvider().getRowKey(aRowId);
        } catch (Exception ex) {
        }
        return rk;
    }

    protected ArrayList crearListaLineaFactura(ArrayList list, FacturaSubsidio facturaSubsidio) {
        Iterator iterator = list.iterator();
        LineaFactura locLineaFactura;
        ArrayList locListaLineaFactura = new ArrayList();
        while (iterator.hasNext()) {
            locLineaFactura = (LineaFactura) iterator.next();
            if (locLineaFactura.getCantidad() != null && locLineaFactura.getCantidad().doubleValue() != 0) {
                locLineaFactura.setFactura(facturaSubsidio);
                locListaLineaFactura.add(locLineaFactura);
            }
        }
        return locListaLineaFactura;
    }

    public String btnSeleccionarProveedor_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 1;

        if (ultimo) {

            this.guardarEstadoObjetosUsados();
            //this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(1, Proveedor.class));
            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno =
                    "AdminProveedor";
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnLimpiarProveedor_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(1, this.getTfProveedor());
            this.getTfMontoTotal().setText("0.0");
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnSeleccionarDigestoMunicipal_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 2;

        if (ultimo) {

            this.guardarEstadoObjetosUsados();
            //this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(2, DigestoMunicipal.class));
            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminDigestoMunicipal";
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnLimpiarDigestoMunicipal_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            this.limpiarObjeto(2, this.getTfDigestoMunicipal());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnAgregarLinea_action(){
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(1, Proveedor.class);
        if(proveedor.getIdProveedor() == -1){
            warn("Seleccione un Proveedor para listar sus Bienes.");
            this.getTfProveedor().setValid(false);
            retorno = null;
        } else {
            if (ultimo) {
                this.guardarEstadoObjetosUsados();

                this.getRequestBean1().setObjetoABM(proveedor);
               // this.getRequestBean1().setTipoSeleccion("MULTIPLE");
                this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

                retorno = "AgregarLineaFacturaProveedor";
            } else {
                retorno = this.prepararCaducidad();
            }
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
    
    @Override
    protected String getCasoNavegacion() {
        return "ABMFacturaSubsidio"; 
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	       int ind = 0;
	        FacturaSubsidio facturaSubsidio = null;
	        Proveedor proveedor = null;
	        DigestoMunicipal digestoMunicipal = null;
	        FacturaSubsidio.TipoFactura tipoFactura = null;
	        ArrayList listaLineaFactura = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
	        
        Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
        if (seleccionado instanceof Proveedor) {
            try {
                proveedor = (Proveedor) seleccionado;
                this.getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().setLlave(this.getSessionBean1().getLlave());
                proveedor = this.getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().findProveedorByID(proveedor.getIdProveedor());
                this.getElementoPila().getObjetos().set(1, proveedor);
            } catch (Exception ex) {
                log("Error Description", ex);
            }
        }
        else if (seleccionado instanceof DigestoMunicipal) {
            try {
                //FacturaSubsidio locFacturaSubsidio = (FacturaSubsidio) this.obtenerObjetoDelElementoPila(0, FacturaSubsidio.class);
                digestoMunicipal = (DigestoMunicipal) seleccionado;
                digestoMunicipal = this.getComunicationBean().getRemoteSystemMunicipalidad().getDigestoMunicipalPorId(digestoMunicipal.getIdDigestoMunicipal());
                this.getElementoPila().getObjetos().set(2, digestoMunicipal);

            } catch (Exception ex) {
                log("Error Description", ex);
            }
        }

        if(seleccionado instanceof ArrayList) {
            try{
                ArrayList locListado = (ArrayList) seleccionado;
                for(int i = 0; i<locListado.size(); i++){
                    listaLineaFactura.add(locListado.get(i));
                }
                this.getElementoPila().getObjetos().set(3, listaLineaFactura);
                // o.O .... O.o
            } catch (Exception ex) {
                log("Error Description", ex);
            }
        }
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
	       int ind = 0;
	        FacturaSubsidio facturaSubsidio = null;
	        Proveedor proveedor = null;
	        DigestoMunicipal digestoMunicipal = null;
	        ArrayList listaLineaFactura = (ArrayList) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
	        
        facturaSubsidio = (FacturaSubsidio) this.getRequestBean1().getObjetoABM();
//        try {
//            this.getCommunicationComprasBean().getRemoteSystemAdministracionFacturaSubsidio().setLlave(this.getSessionBean1().getLlave());
//            try {
//                facturaSubsidio = this.getCommunicationComprasBean().getRemoteSystemAdministracionFacturaSubsidio().getFacturaSubsidioPorId(facturaSubsidio.getIdFactura());
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//
//        } catch (RemoteException ex) {
//            ex.printStackTrace();
//        }
        proveedor = null;
        listaLineaFactura = null;
        if (facturaSubsidio.getIdFactura() != -1) {
            try {
                proveedor = facturaSubsidio.getProveedor();
                digestoMunicipal = facturaSubsidio.getDigestoMunicipal();
                listaLineaFactura = new ArrayList(facturaSubsidio.getListaLineaFactura());
            } catch (Exception ex) {
                log(getCasoNavegacion() + "_GuardarError:", ex);
            }
        }

        if (facturaSubsidio.getListaLineaFactura() != null && !facturaSubsidio.getListaLineaFactura().isEmpty() && facturaSubsidio.getListaLineaFactura().size() > 0) {
            for (int i = 0; i < facturaSubsidio.getListaLineaFactura().size(); i++) {
                LineaFactura cadaLineaFactura = (LineaFactura) facturaSubsidio.getListaLineaFactura().get(i);
                try {
                    cadaLineaFactura.setTotal(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            facturaSubsidio.setTotal(null);
        }

        this.getElementoPila().getObjetos().set(0, facturaSubsidio);
        this.getElementoPila().getObjetos().set(1, proveedor);
        this.getElementoPila().getObjetos().set(2, digestoMunicipal);
        this.getElementoPila().getObjetos().set(3, listaLineaFactura);
	}
	
	@Override
	public long getSerialVersionUID() {
		return FacturaSubsidio.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMFacturaSubsidio$ABMFacturaSubsidio}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		FacturaSubsidio locFacturaSubsidio = this.obtenerObjetoDelElementoPila(0, FacturaSubsidio.class);
		this.getTablaLogs().getLdpLogs().setList(locFacturaSubsidio.getListaLogsAuditoria());
	}
}