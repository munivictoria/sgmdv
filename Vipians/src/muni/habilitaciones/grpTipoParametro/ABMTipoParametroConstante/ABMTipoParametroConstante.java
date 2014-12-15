/*
 * AgregarTipoParametroConstante.java
 *
 * Created on 18 de octubre de 2006, 10:30
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpTipoParametro.ABMTipoParametroConstante;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.trascender.habilitaciones.recurso.persistent.LineaTipoParametroConstante;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP.UnidadMedida;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;


public class ABMTipoParametroConstante extends ABMPageBean {
    
    
    private final String NOMBRE_PAGINA = "Agregar Par\341metro Constante";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AgregarTipoParametroConstante";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;
    
    @Override
    protected void _init() throws Exception {
        Option[] op = null;
        // Unidades de Medida
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(UnidadMedida.values(),"cap");
        
		if (this.getListaDelCommunicationLineas() != null) {
			this.getLdpLineas().setList(getListaDelCommunicationLineas());
		} 
    }
    
    private TextField tfNombre = new TextField();
    
    public TextField getTfNombre() {
        return tfNombre;
    }
    
    public void setTfNombre(TextField tf) {
        this.tfNombre = tf;
    }
    
    private Label label4 = new Label();
    
    public Label getLabel4() {
        return label4;
    }
    
    public void setLabel4(Label l) {
        this.label4 = l;
    }
    
    private MessageGroup messageGroup1 = new MessageGroup();
    
    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }
    
    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
    }

    private Label label2 = new Label();
    
    public Label getLabel2() {
        return label2;
    }
    
    public void setLabel2(Label l) {
        this.label2 = l;
    }
    
    private Label label3 = new Label();
    
    public Label getLabel3() {
        return label3;
    }
    
    public void setLabel3(Label l) {
        this.label3 = l;
    }
    
    private TextField tfValor = new TextField();
    
    public TextField getTfValor() {
        return tfValor;
    }
    
    public void setTfValor(TextField tf) {
        this.tfValor = tf;
    }

    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }

    private TextField tfNombreVariable = new TextField();

    public TextField getTfNombreVariable() {
        return tfNombreVariable;
    }

    public void setTfNombreVariable(TextField tf) {
        this.tfNombreVariable = tf;
    }

    private RadioButton rbFijo = new RadioButton();

    public RadioButton getRbFijo() {
        return rbFijo;
    }

    public void setRbFijo(RadioButton rb) {
        this.rbFijo = rb;
    }

    private Label label5 = new Label();

    public Label getLabel5() {
        return label5;
    }

    public void setLabel5(Label l) {
        this.label5 = l;
    }

    private RadioButton rbPorcentual = new RadioButton();

    public RadioButton getRbPorcentual() {
        return rbPorcentual;
    }

    public void setRbPorcentual(RadioButton rb) {
        this.rbPorcentual = rb;
    }

    private Label label6 = new Label();

    public Label getLabel6() {
        return label6;
    }

    public void setLabel6(Label l) {
        this.label6 = l;
    }

    public ABMTipoParametroConstante() {
    }
    
    private Label lblListaLineas = new Label();
    private HtmlAjaxCommandButton btnAgregarVariable = new HtmlAjaxCommandButton();
    private HtmlAjaxCommandButton btnQuitarVariable = new HtmlAjaxCommandButton();
    private Table tableListaLineas = new Table();
    private TableRowGroup tableRowGroupListaLineas = new TableRowGroup();
    private ObjectListDataProvider ldpLineas = new ObjectListDataProvider();
    private TableColumn tableColumn1 = new TableColumn();
    private TableColumn tableColumn2 = new TableColumn();
    private TableColumn tableColumn3 = new TableColumn();
    private RadioButton radioButton1 = new RadioButton();
    private Object seleccion = null;
    private TextField tfFechaBaja = new TextField();
    private TextField tfValorLinea = new TextField();
    
	public Label getLblListaLineas() {
		return lblListaLineas;
	}

	public void setLblListaLineas(Label lblListaLineas) {
		this.lblListaLineas = lblListaLineas;
	}

	public HtmlAjaxCommandButton getBtnAgregarVariable() {
		return btnAgregarVariable;
	}

	public void setBtnAgregarVariable(HtmlAjaxCommandButton btnAgregarVariable) {
		this.btnAgregarVariable = btnAgregarVariable;
	}

	public HtmlAjaxCommandButton getBtnQuitarVariable() {
		return btnQuitarVariable;
	}

	public void setBtnQuitarVariable(HtmlAjaxCommandButton btnQuitarVariable) {
		this.btnQuitarVariable = btnQuitarVariable;
	}

	public Table getTableListaLineas() {
		return tableListaLineas;
	}

	public void setTableListaLineas(Table tableListaLineas) {
		this.tableListaLineas = tableListaLineas;
	}

	public TableRowGroup getTableRowGroupListaLineas() {
		return tableRowGroupListaLineas;
	}

	public void setTableRowGroupListaLineas(TableRowGroup tableRowGroupListaLineas) {
		this.tableRowGroupListaLineas = tableRowGroupListaLineas;
	}

	public ObjectListDataProvider getLdpLineas() {
		return ldpLineas;
	}

	public void setLdpLineas(ObjectListDataProvider ldpLineas) {
		this.ldpLineas = ldpLineas;
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

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tableColumn3) {
		this.tableColumn3 = tableColumn3;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public TextField getTfFechaBaja() {
		return tfFechaBaja;
	}

	public void setTfFechaBaja(TextField tfFechaBaja) {
		this.tfFechaBaja = tfFechaBaja;
	}

	public TextField getTfValorLinea() {
		return tfValorLinea;
	}

	public void setTfValorLinea(TextField tfValorLinea) {
		this.tfValorLinea = tfValorLinea;
	}

	public String getVar(){
		return tableRowGroupListaLineas.getRowKey().getRowId();
	}
    
	public Object getSeleccion() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(seleccion) ? sv : null;
	}

	public void setSeleccion(Object selec) {
		if(selec != null) {
			seleccion = selec;
		}
	}
    
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        
        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new TipoParametroConstante());
        
        this.setListaDelCommunicationLineas(new ArrayList());
		this.getLdpLineas().setList(getListaDelCommunicationLineas());
        
        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }
    
    @Override
    protected void guardarEstadoObjetosUsados() {
        // CAMBIAR: Verificar el metodo completo.
        TipoParametroConstante tipoParametroConstante = this.obtenerObjetoDelElementoPila(0, TipoParametroConstante.class);
        
        Object fijo = this.getRbFijo().getValue();

        tipoParametroConstante.setNombre(getTextFieldValue(this.getTfNombre()));
        tipoParametroConstante.setNombreVariable(getTextFieldValue(this.getTfNombreVariable()));
        tipoParametroConstante.setValor(getTextFieldValueDouble(this.getTfValor()));
        if (fijo != null && fijo != "") tipoParametroConstante.setFijo(((Boolean)fijo).booleanValue());
        
        this.getLdpLineas().commitChanges();
        this.setListaDelCommunicationLineas(this.getLdpLineas().getList());
        tipoParametroConstante.setListaLineas(this.getListaDelCommunicationLineas());
        
        this.getElementoPila().getObjetos().set(0, tipoParametroConstante);
    }
    
    @Override
    protected void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Verificar el metodo completo.
        TipoParametroConstante tipoParametroConstante = null;
        
        tipoParametroConstante = this.obtenerObjetoDelElementoPila(0, TipoParametroConstante.class);
        
        this.getTfNombre().setText(tipoParametroConstante.getNombre());
        this.getTfNombreVariable().setText(tipoParametroConstante.getNombreVariable());
        if (tipoParametroConstante.getValor() != null) this.getTfValor().setText(tipoParametroConstante.getValor().toString());
        this.getRbFijo().setValue(new Boolean(tipoParametroConstante.isFijo()));
        this.getRbPorcentual().setValue(new Boolean(!tipoParametroConstante.isFijo()));
        
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		TipoParametroConstante locTipoParametroConstante = (TipoParametroConstante) pObject;
		
		this.setListaDelCommunicationLineas(locTipoParametroConstante.getListaLineas());
		this.getLdpLineas().setList(getListaDelCommunicationLineas());
		
		this.getElementoPila().getObjetos().set(0, pObject);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMTipoParametroConstante";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		TipoParametroConstante locParametro = this.obtenerObjetoDelElementoPila(0, TipoParametroConstante.class);
		this.getTablaLogs().getLdpLogs().setList(locParametro.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return TipoParametroConstante.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante}";
	}
	
	public void btnAgregarLinea_action(){
		this.getLdpLineas().commitChanges();
		LineaTipoParametroConstante locLineaTipoParametroConstante = new LineaTipoParametroConstante();
		
		this.getListaDelCommunicationLineas().add(0, locLineaTipoParametroConstante);
		this.getLdpLineas().setList(this.getListaDelCommunicationLineas());
		this.guardarEstadoObjetosUsados();
	}
	
	public String btnQuitarLinea_action(){
		String retorno = null;
		this.getLdpLineas().commitChanges();
		Object objeto = getObjetoSeleccionadoDeTabla("buttonGroup1", this.getLdpLineas());
		if (objeto != null) {
			this.getListaDelCommunicationLineas().remove(objeto);
			this.getLdpLineas().getList().remove(objeto);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}
	
	public List getListaDelCommunicationLineas(){
		return this.getCommunicationHabilitacionesBean().getListaLineaTipoParametroConstante();
	}
	
	public void setListaDelCommunicationLineas(List pLista){
		this.getCommunicationHabilitacionesBean().setListaLineaTipoParametroConstante(pLista);
	}
}
