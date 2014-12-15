/*
 * AgregarMovimientoDeMercaderia.java
 *
 * Created on 12 de junio de 2008, 8:30
 * Copyright Trascender SRL
 */
package muni.inventario.ABMMovimientoDeMercaderia;
  
import java.util.Calendar;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.sun.data.provider.RowKey;
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
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.persistent.inventario.Deposito;
import com.trascender.compras.recurso.persistent.inventario.LineaMovimientoMercaderia;
import com.trascender.compras.recurso.persistent.inventario.MovimientoDeMercaderia;
import com.trascender.compras.recurso.persistent.inventario.MovimientoDeMercaderia.Tipo;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;


public class ABMMovimientoDeMercaderia extends ABMPageBean {
	
    // CAMBIAR: Constantes que varian segun la pagina.
    // NOMBRE a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Agregar Movimiento De Mercadería";
    // NOMBRE del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AgregarMovimientoDeMercaderia";
    // NOMBRE del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // NOMBRE del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;

    @Override
    protected void _init() throws Exception {
        Option[] op = null;
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(MovimientoDeMercaderia.Tipo.values(), "cap");
        ddTipoMovimientoDefaultOptions.setOptions(op);
        
        Option[] opDepositosOrigen = null;
        opDepositosOrigen = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getCommunicationComprasBean().getMapaDepositosOrigen().keySet().toArray(), "");
        ddDepositoOrigenDefaultOptions.setOptions(opDepositosOrigen);
        
        Option[] opDepositosDestino = null;
        opDepositosDestino = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getCommunicationComprasBean().getMapaDepositoDestino().keySet().toArray(), "");
        ddDepositoDestinoDefaultOptions.setOptions(opDepositosDestino);
        
        if(this.getListaDelCommunicationLineasMovMerc() != null){
        	this.getLdpLineasMovMerc().setList(this.getListaDelCommunicationLineasMovMerc());
        }
    }

    private Label lblLineasMovMerc = new Label();
    private Table table1 = new Table();
    private TableRowGroup tableRowGroup1 = new TableRowGroup();
    private ObjectListDataProvider ldpLineasMovMerc = new ObjectListDataProvider();
    private TableColumn tableColumn1 = new TableColumn();
    private TableColumn tcBien = new TableColumn();
    private TableColumn tcStock = new TableColumn();
    private TableColumn tcStockDestino = new TableColumn();
    private TableColumn tcCantidad = new TableColumn();
    private TableColumn tcCantidadEntregasPrev = new TableColumn();
    private TableColumn tcOrdenOSolicitud = new TableColumn();
    private RadioButton radioButton1 = new RadioButton();
    private TextArea taBien = new TextArea();
    private PanelGroup groupPanel1 = new PanelGroup();
    private Button btnSeleccionarOrdenCompra = new Button();
    private Button btnSeleccionarSolSum = new Button();
    private Button btnQuitarLinea = new Button();
    private Button btnQuitarTodosLinea = new Button();
    private TextField tfStock = new TextField();
    private TextField tfStockDestino = new TextField();
    private StaticText stStock = new StaticText();
    private StaticText stStockDestino = new StaticText();
    private StaticText stOrdenOSolicitud = new StaticText();
    private StaticText stCantidadEntregasPrev = new StaticText();
    
	public StaticText getStCantidadEntregasPrev() {
		return stCantidadEntregasPrev;
	}
	public void setStCantidadEntregasPrev(StaticText stCantidadEntregasPrev) {
		this.stCantidadEntregasPrev = stCantidadEntregasPrev;
	}
	public TableColumn getTcCantidadEntregasPrev() {
		return tcCantidadEntregasPrev;
	}
	public void setTcCantidadEntregasPrev(TableColumn tcCantidadEntregasPrev) {
		this.tcCantidadEntregasPrev = tcCantidadEntregasPrev;
	}
	public TableColumn getTcOrdenOSolicitud() {
		return tcOrdenOSolicitud;
	}
	public void setTcOrdenOSolicitud(TableColumn tcOrdenOSolicitud) {
		this.tcOrdenOSolicitud = tcOrdenOSolicitud;
	}
	public StaticText getStOrdenOSolicitud() {
		return stOrdenOSolicitud;
	}
	public void setStOrdenOSolicitud(StaticText stOrdenOSolicitud) {
		this.stOrdenOSolicitud = stOrdenOSolicitud;
	}
	public StaticText getStStock() {
		return stStock;
	}
	public void setStStock(StaticText stStock) {
		this.stStock = stStock;
	}
	public StaticText getStStockDestino() {
		return stStockDestino;
	}
	public void setStStockDestino(StaticText stStockDestino) {
		this.stStockDestino = stStockDestino;
	}
	public TextField getTfStock() {
		return tfStock;
	}
	public void setTfStock(TextField tfStock) {
		this.tfStock = tfStock;
	}
	public TextField getTfStockDestino() {
		return tfStockDestino;
	}
	public void setTfStockDestino(TextField tfStockDestino) {
		this.tfStockDestino = tfStockDestino;
	}
	public Button getBtnSeleccionarOrdenCompra() {
		return btnSeleccionarOrdenCompra;
	}
	public void setBtnSeleccionarOrdenCompra(Button btnSeleccionarOrdenCompra) {
		this.btnSeleccionarOrdenCompra = btnSeleccionarOrdenCompra;
	}
	public Button getBtnSeleccionarSolSum() {
		return btnSeleccionarSolSum;
	}
	public void setBtnSeleccionarSolSum(Button btnSeleccionarSolSum) {
		this.btnSeleccionarSolSum = btnSeleccionarSolSum;
	}
	public Label getLblLineasMovMerc() {
		return lblLineasMovMerc;
	}
	public void setLblLineasMovMerc(Label lblLineasMovMerc) {
		this.lblLineasMovMerc = lblLineasMovMerc;
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
	public ObjectListDataProvider getLdpLineasMovMerc() {
		return ldpLineasMovMerc;
	}
	public void setLdpLineasMovMerc(ObjectListDataProvider ldpLineasMovMerc) {
		this.ldpLineasMovMerc = ldpLineasMovMerc;
	}
	public TableColumn getTableColumn1() {
		return tableColumn1;
	}
	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}
	public TableColumn getTcBien() {
		return tcBien;
	}
	public void setTcBien(TableColumn tcBien) {
		this.tcBien = tcBien;
	}
	public TableColumn getTcStock() {
		return tcStock;
	}
	public void setTcStock(TableColumn tcStock) {
		this.tcStock = tcStock;
	}
	public TableColumn getTcStockDestino() {
		return tcStockDestino;
	}
	public void setTcStockDestino(TableColumn tcStockDestino) {
		this.tcStockDestino = tcStockDestino;
	}
	public TableColumn getTcCantidad() {
		return tcCantidad;
	}
	public void setTcCantidad(TableColumn tcCantidad) {
		this.tcCantidad = tcCantidad;
	}
	public RadioButton getRadioButton1() {
		return radioButton1;
	}
	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}
	
	public TextArea getTaBien() {
		return taBien;
	}
	public void setTaBien(TextArea taBien) {
		this.taBien = taBien;
	}
	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}
	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}
	public Button getBtnQuitarLinea() {
		return btnQuitarLinea;
	}
	public void setBtnQuitarLinea(Button btnQuitarLinea) {
		this.btnQuitarLinea = btnQuitarLinea;
	}
	public Button getBtnQuitarTodosLinea() {
		return btnQuitarTodosLinea;
	}
	public void setBtnQuitarTodosLinea(Button btnQuitarTodosLinea) {
		this.btnQuitarTodosLinea = btnQuitarTodosLinea;
	}

	private TextField tfFecha = new TextField();

    public TextField getTfFecha() {
        return tfFecha;
    }
    public void setTfFecha(TextField pTfFecha) {
        this.tfFecha = pTfFecha;
    }
    
    private TextField tfCantidad = new TextField();

    public TextField getTfCantidad() {
        return tfCantidad;
    }
    public void setTfCantidad(TextField pTfCantidad) {
        this.tfCantidad = pTfCantidad;
    }
    
    private TextArea taMotivo = new TextArea();
    
    public TextArea getTaMotivo() {
        return taMotivo;
    }
    public void setTaMotivo(TextArea pTaMotivo) {
        this.taMotivo = pTaMotivo;
    }

    private DropDown ddDepositoOrigen = new DropDown();
    private DropDown ddDepositoDestino = new DropDown();
    private SingleSelectOptionsList ddDepositoOrigenDefaultOptions = new SingleSelectOptionsList();
    private SingleSelectOptionsList ddDepositoDestinoDefaultOptions = new SingleSelectOptionsList();
    
    public SingleSelectOptionsList getDdDepositoOrigenDefaultOptions() {
		return ddDepositoOrigenDefaultOptions;
	}
	public void setDdDepositoOrigenDefaultOptions(
			SingleSelectOptionsList ddDepositoOrigenDefaultOptions) {
		this.ddDepositoOrigenDefaultOptions = ddDepositoOrigenDefaultOptions;
	}
	public SingleSelectOptionsList getDdDepositoDestinoDefaultOptions() {
		return ddDepositoDestinoDefaultOptions;
	}
	public void setDdDepositoDestinoDefaultOptions(
			SingleSelectOptionsList ddDepositoDestinoDefaultOptions) {
		this.ddDepositoDestinoDefaultOptions = ddDepositoDestinoDefaultOptions;
	}
	public DropDown getDdDepositoOrigen() {
		return ddDepositoOrigen;
	}
	public void setDdDepositoOrigen(DropDown ddDepositoOrigen) {
		this.ddDepositoOrigen = ddDepositoOrigen;
	}
	public DropDown getDdDepositoDestino() {
		return ddDepositoDestino;
	}
	public void setDdDepositoDestino(DropDown ddDepositoDestino) {
		this.ddDepositoDestino = ddDepositoDestino;
	}

	private TextField tfUsuario = new TextField();

    public TextField getTfUsuario() {
        return tfUsuario;
    }
    public void setTfUsuario(TextField pTfUsuario) {
        this.tfUsuario = pTfUsuario;
    }

    //Labels
    private Label lblFecha = new Label();

    public Label getLblFecha() {
        return lblFecha;
    }
    public void setLblFecha(Label pLblFecha) {
        this.lblFecha = pLblFecha;
    }

    private Label lblTipoMovimiento = new Label();

    public Label getLblTipoMovimiento() {
        return lblTipoMovimiento;
    }
    public void setLblTipoMovimiento(Label pLblTipoMovimiento) {
        this.lblTipoMovimiento = pLblTipoMovimiento;
    }
    
    private Label lblCantidad = new Label();

    public Label getLblCantidad() {
        return lblCantidad;
    }
    public void setLblCantidad(Label pLblCantidad) {
        this.lblCantidad = pLblCantidad;
    }

    private Label lblMotivo = new Label();
    
    public Label getLblMotivo() {
        return lblMotivo;
    }
    public void setLblMotivo(Label pLblMotivo) {
        this.lblMotivo = pLblMotivo;
    }

    private Label lblDepositoOrigen = new Label();
    
    private Label lblDepositoDestino = new Label();
    
    public Label getLblDepositoOrigen() {
		return lblDepositoOrigen;
	}
	public void setLblDepositoOrigen(Label lblDepositoOrigen) {
		this.lblDepositoOrigen = lblDepositoOrigen;
	}
	public Label getLblDepositoDestino() {
		return lblDepositoDestino;
	}
	public void setLblDepositoDestino(Label lblDepositoDestino) {
		this.lblDepositoDestino = lblDepositoDestino;
	}

	private Label lblUsuario = new Label();

    public Label getLblUsuario() {
        return lblUsuario;
    }
    public void setLblUsuario(Label pLblUsuario) {
        this.lblUsuario = pLblUsuario;
    }

    DropDown ddTipoMovimiento = new DropDown();

    public DropDown getDdTipoMovimiento() {
        return ddTipoMovimiento;
    }

    public void setDdTipoMovimiento(DropDown pDdTipoMovimiento) {
        this.ddTipoMovimiento = pDdTipoMovimiento;
    }

    private SingleSelectOptionsList ddTipoMovimientoDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdTipoMovimientoDefaultOptions() {
        return ddTipoMovimientoDefaultOptions;
    }

    public void setDdTipoMovimientoDefaultOptions(SingleSelectOptionsList pDdTipoMovimientoDefaultOptions) {
        this.ddTipoMovimientoDefaultOptions = pDdTipoMovimientoDefaultOptions;
    }
    
    private Button btnSeleccionarStockOrigen = new Button();

    public Button getBtnSeleccionarStockOrigen() {
        return btnSeleccionarStockOrigen;
    }
    public void setBtnSeleccionarStockOrigen(Button pBtnSeleccionarStockOrigen) {
        this.btnSeleccionarStockOrigen = pBtnSeleccionarStockOrigen;
    }

    private Button btnSeleccionarStockDestino = new Button();

    public Button getBtnSeleccionarStockDestino() {
        return btnSeleccionarStockDestino;
    }
    public void setBtnSeleccionarStockDestino(Button pBtnSeleccionarStockDestino) {
        this.btnSeleccionarStockDestino = pBtnSeleccionarStockDestino;
    }

    private Button btnSeleccionarArea = new Button();

    public Button getBtnSeleccionarArea() {
        return btnSeleccionarArea;
    }
    public void setBtnSeleccionarArea(Button pBtnSeleccionarArea) {
        this.btnSeleccionarArea = pBtnSeleccionarArea;
    }
    
    private Object lastSelected = null;
    
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
	
	protected List<LineaMovimientoMercaderia> getListaDelCommunicationLineasMovMerc(){
		return this.getCommunicationComprasBean().getListaLineasMovMerc();
	}
	
	protected void setListaDelCommunicationLineasMovMerc(List<LineaMovimientoMercaderia> listaLineasMovMerc){
		this.getCommunicationComprasBean().setListaLineasMovMerc(listaLineasMovMerc);
	}
    
    /** 
     * <p>Construir una instancia de bean de página.</p>
     */
    public ABMMovimientoDeMercaderia() {
    }
    
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        
        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new MovimientoDeMercaderia());
        ep.getObjetos().add(ind++, new Deposito());
        ep.getObjetos().add(ind++, new Usuario());
        ep.getObjetos().add(ind++, new Area());
        ep.getObjetos().add(ind++, new Deposito());
        
        //Utilizado para saber el tipo de movimiento
        ep.getObjetos().add(ind++, null);
        
        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }
    
    @Override
    protected void guardarEstadoObjetosUsados() {
        // CAMBIAR: Verificar el metodo completo.
        MovimientoDeMercaderia locMovimientoDeMercaderia = (MovimientoDeMercaderia) this.obtenerObjetoDelElementoPila(0, MovimientoDeMercaderia.class);
        Deposito locDepositoOrigen = (Deposito) this.obtenerObjetoDelElementoPila(1, Deposito.class);
        Usuario locUsuario = this.getSessionBean1().getUsuario();
        Area locArea = (Area) this.obtenerObjetoDelElementoPila(3, Area.class);
        Deposito locDepositoDestino = (Deposito)this.obtenerObjetoDelElementoPila(4, Deposito.class);

        Object locFecha = this.getTfFecha().getText();
        Object locMotivo = this.getTaMotivo().getText();

        locMovimientoDeMercaderia.setTipo(getDDEnumValue(this.getDdTipoMovimiento(), MovimientoDeMercaderia.Tipo.class));

        if (locFecha != null && locFecha != "" ){
            Calendar locCalendar = Calendar.getInstance();
            locCalendar.getInstance().setTime(Conversor.getFechaFormatoDeString(locFecha.toString(),"dd/MM/yyyy"));
            locMovimientoDeMercaderia.setFecha(locCalendar);
        }
        else{
             locMovimientoDeMercaderia.setFecha(null);
        }

        if (locMotivo != null && locMotivo != "") {
            locMovimientoDeMercaderia.setMotivo(locMotivo.toString());
        }
        else {
            locMovimientoDeMercaderia.setMotivo(null);
        }
        
        locDepositoOrigen = getDDObjectValue(this.getDdDepositoOrigen(), this.getCommunicationComprasBean().getMapaDepositosOrigen());
        locMovimientoDeMercaderia.setDeposito(locDepositoOrigen);
        
        locDepositoDestino = getDDObjectValue(this.getDdDepositoDestino(), this.getCommunicationComprasBean().getMapaDepositoDestino());
        locMovimientoDeMercaderia.setDepositoDestino(locDepositoDestino);
        
        if(locUsuario != null && locUsuario.getIdUsuario() != -1){
            locMovimientoDeMercaderia.setUsuario(locUsuario);
        }
        else{
            locMovimientoDeMercaderia.setUsuario(null);
        }

//        if(locArea != null && locArea.getIdArea() != -1){
//            locMovimientoDeMercaderia.setArea(locArea);
//        }
//        else{
//            locMovimientoDeMercaderia.setArea(null);
//        }
		
        this.getLdpLineasMovMerc().commitChanges();
        locMovimientoDeMercaderia.setListaLineasMovimientoMercaderia(this.getLdpLineasMovMerc().getList());
        this.setListaDelCommunicationLineasMovMerc(locMovimientoDeMercaderia.getListaLineasMovimientoMercaderia());
        
        this.getElementoPila().getObjetos().set(0, locMovimientoDeMercaderia);
        this.getElementoPila().getObjetos().set(1, locDepositoOrigen);
        this.getElementoPila().getObjetos().set(2, locUsuario);
//        this.getElementoPila().getObjetos().set(3, locArea);
        this.getElementoPila().getObjetos().set(4, locDepositoDestino);
        this.getElementoPila().getObjetos().set(5, locMovimientoDeMercaderia.getTipo());
    }
    
    @Override
    protected void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Verificar el metodo completo.
        MovimientoDeMercaderia locMovimientoDeMercaderia = null;
        Deposito locDepositoOrigen = null;
        Usuario locUsuario = this.getSessionBean1().getUsuario();
        Area locArea = null;
        Deposito locDepositoDestino = null;
        Tipo locTipoMovimiento = null;

        locMovimientoDeMercaderia = (MovimientoDeMercaderia) this.obtenerObjetoDelElementoPila(0, MovimientoDeMercaderia.class);
        locDepositoOrigen = (Deposito) this.obtenerObjetoDelElementoPila(1, Deposito.class);
        //locUsuario = (Usuario) this.obtenerObjetoDelElementoPila(2, Usuario.class);
        locArea = (Area) this.obtenerObjetoDelElementoPila(3, Area.class);
        locDepositoDestino = (Deposito) this.obtenerObjetoDelElementoPila(4, Deposito.class);
        locTipoMovimiento = (Tipo) this.obtenerObjetoDelElementoPila(5, Tipo.class);
        
        if(this.getDdTipoMovimiento().getSelected() != null && this.getDdTipoMovimiento().getSelected().toString().equals("MOVIMIENTO")){
        	this.getDdDepositoDestino().setDisabled(false);
        }
        
        if(locMovimientoDeMercaderia.getFecha() != null)   this.getTfFecha().setText(Conversor.getStringDeFechaCorta(locMovimientoDeMercaderia.getFecha().getTime()));
        if(locMovimientoDeMercaderia.getMotivo() != null) this.getTaMotivo().setText(locMovimientoDeMercaderia.getMotivo());
        if(locMovimientoDeMercaderia.getTipo() != null){
            this.getDdTipoMovimiento().setSelected(Util.getEnumNameFromString(locMovimientoDeMercaderia.getTipo() != null? locMovimientoDeMercaderia.getTipo().toString() : ""));
//            this.getDdTipoMovimientoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(locTipoMovimiento.toString()));
        }

        if(locMovimientoDeMercaderia.getDeposito() != null){
        	this.getDdDepositoOrigen().setSelected(locMovimientoDeMercaderia.getDeposito().toString());
        }
        
        if(locMovimientoDeMercaderia.getDepositoDestino() != null){
        	this.getDdDepositoDestino().setSelected(locMovimientoDeMercaderia.getDepositoDestino().toString());
        }

        if(locUsuario != null && locUsuario.getIdUsuario() != -1){
            this.getTfUsuario().setText(locUsuario.toString());
        }
        else{
            this.getTfUsuario().setText(null);
        }
        System.out.println("lista: " + locMovimientoDeMercaderia.getListaLineasMovimientoMercaderia().size());
        this.getLdpLineasMovMerc().setList(locMovimientoDeMercaderia.getListaLineasMovimientoMercaderia());
        this.setListaDelCommunicationLineasMovMerc(locMovimientoDeMercaderia.getListaLineasMovimientoMercaderia());
    }
    
    public String btnSeleccionarOrdenCompra_action(){
    	return navegarParaSeleccionar("AdminOrdenCompra");
    }
    
    public String btnSeleccionarSolSum_action(){
    	return navegarParaSeleccionar("AdminSolicitudSuministro");
    }
    
    public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getLdpLineasMovMerc().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}
    
    public String btnQuitarLinea_action(){
    	String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if (ultimo) {
			RowKey rk;

			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getLdpLineasMovMerc().getObjects()[index];
					this.getListaDelCommunicationLineasMovMerc().remove(obj);
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
    
    public String btnQuitarTodosLinea_action(){
    	String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			// APLICAR LOGICA AQUI...
			try {
				this.getListaDelCommunicationLineasMovMerc().clear();
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
    
//    public String btnGuardar_action() {
//        String retorno = null;
//        boolean ultimo = this.getSessionBean1().getMgrPilas().isLastElementoPila(this.getIdSubSesion(), this.getIdPagina());
//        
//        if (ultimo) {
//            
//            try {
//            	
//                MovimientoDeMercaderia locMovimientoDeMercaderia  = (MovimientoDeMercaderia) this.obtenerObjetoDelElementoPila(0, MovimientoDeMercaderia.class);
//
//                if (locMovimientoDeMercaderia.getCantidad() != null) {
//                        Double locCantidad = new Double(this.getTfCantidad().getText().toString());
//                        if (locCantidad.compareTo(new Double(0)) <= 0){
//                            warn("La Cantidad debe ser mayor a 0.");
//                            return null;
//                        }
//                        if (!locMovimientoDeMercaderia.getTipo().equals(Tipo.INGRESO) && locMovimientoDeMercaderia.getStock() != null && locMovimientoDeMercaderia.getStock().getIdStock() != -1)
//                            if (locCantidad.compareTo(locMovimientoDeMercaderia.getStock().getCantidad()) > 0){
//                               warn("La Cantidad a extraer no puede ser mayor a la del Stock Origen.");
//                               return null;
//                            }
//                }
//                
//                this.getCommunicationComprasBean().getRemoteSystemStock().setLlave(this.getSessionBean1().getLlave());
//                this.getRequestBean1().setRespuestaABM(this.getCommunicationComprasBean().getRemoteSystemStock().addMovimientoDeMercaderia(locMovimientoDeMercaderia));
//                
//                //this.getRequestBean1().setRespuestaABM(locMovimientoDeMercaderia);
//                
//                info("El Movimiento de Mercader\355a se agreg\363 exitosamente.");
//
//                retorno = this.prepararParaVolver(Constantes.ACCION_AGREGAR);
//            } catch (Exception ex) {
////                if (ex instanceof TrascenderException) {
////                    int codigoError = ((TrascenderException)ex).getCodeTrascenderException();
////                    if (this.getApplicationBean1().esErrorDeLogica(this.getExternalContext().getRequestPathInfo(), codigoError)) retorno = null;
////                    else retorno = this.prepararParaVolver(Constantes.ACCION_RSLT_ERROR);
////                }
//                log(CASO_NAVEGACION+"_GuardarError:", ex);
//                error(NOMBRE_PAGINA+" - Guardar: " + ex.getMessage());
//            }
//            
//        } else {
//            retorno = this.prepararCaducidad();
//        }
//        return retorno;
//    }
    
//    public String btnSeleccionarStockOrigen_action() throws Exception {
//        String retorno = null;
//        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
//
//        if (ultimo) {
//            // APLICAR LOGICA AQUI...
//
//            this.guardarEstadoObjetosUsados();
//            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
//            
//            this.getElementoPila().getObjetos().set(5, "StockOrigen");
//            this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(2, Stock.class));
//            
//            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
//            retorno = "AdminStock";
//        } else {
//            retorno = this.prepararCaducidad();
//        }
//
//        return retorno;
//    }

//    public String btnSeleccionarStockDestino_action() throws Exception {
//        String retorno = null;
//        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
//
//        if (ultimo) {
//            // APLICAR LOGICA AQUI...
//
//            this.guardarEstadoObjetosUsados();
//            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
//
//            this.getElementoPila().getObjetos().set(5, "StockDestino");
//            this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(4, Stock.class));
//            
//            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
//            retorno = "AdminStock";
//        } else {
//            retorno = this.prepararCaducidad();
//        }
//        return retorno;
//    }

//    public String btnSeleccionarArea_action() throws Exception {
//        String retorno = null;
//        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
//
//        if (ultimo) {
//            // APLICAR LOGICA AQUI...
//
//            this.guardarEstadoObjetosUsados();
//            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
//
//            this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(3, Area.class));
//            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
//            retorno = "AdminArea";
//        } else {
//            retorno = this.prepararCaducidad();
//        }
//        return retorno;
//    }
    
    @Override
	protected void procesarObjetoSeleccion(Object pObject) {
    	MovimientoDeMercaderia locMovimientoDeMercaderia = (MovimientoDeMercaderia) this.obtenerObjetoDelElementoPila(0, MovimientoDeMercaderia.class);
        if(pObject instanceof OrdenCompra){
        	OrdenCompra locOrdenCompra = (OrdenCompra) pObject;
        	try {
				this.getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().setLlave(this.getSessionBean1().getLlave());
				locOrdenCompra = this.getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().findOrdenCompraByID(locOrdenCompra.getIdOrdenCompra());
				if (locOrdenCompra.getEstado() == OrdenCompra.Estado.CUMPLIDA) {
		            locMovimientoDeMercaderia.setOrdenCompra(locOrdenCompra);
		            this.getLdpLineasMovMerc().setList(locMovimientoDeMercaderia.getListaLineasMovimientoMercaderia());
		            this.setListaDelCommunicationLineasMovMerc(this.getLdpLineasMovMerc().getList());
				} else {
					warn("Solo pueden usarse Ordenes de Compras CUMPLIDAS");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        if(pObject instanceof SolicitudSuministro){
        	SolicitudSuministro locSolSum = (SolicitudSuministro) pObject;
        	try {
				this.getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().setLlave(this.getSessionBean1().getLlave());
				locSolSum = this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().findSolicitudSuministroByID(locSolSum.getIdSolicitudSuministro());
				if(locSolSum.getEstado().isUsadoEnMovimientos()){
					locMovimientoDeMercaderia.setSolicitudSuministro(locSolSum);
		            locMovimientoDeMercaderia.setSolicitudSuministro(locSolSum);
		            this.getLdpLineasMovMerc().setList(locMovimientoDeMercaderia.getListaLineasMovimientoMercaderia());
		            this.setListaDelCommunicationLineasMovMerc(this.getLdpLineasMovMerc().getList());
				} else{
					warn("El Estado de la Solicitud de Suministro debe permitir que sea usada en movimientos.");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		MovimientoDeMercaderia locMovimientoDeMercaderia = (MovimientoDeMercaderia) pObject;
        MovimientoDeMercaderia.Tipo locTipoMovimiento = locMovimientoDeMercaderia.getTipo();
        Deposito locDepositoOrigen = locMovimientoDeMercaderia.getDeposito();
        Usuario locUsuario  = this.getSessionBean1().getUsuario();
        Deposito locDepositoDestino = locMovimientoDeMercaderia.getDepositoDestino();
        
        this.getLdpLineasMovMerc().setList(locMovimientoDeMercaderia.getListaLineasMovimientoMercaderia());
        this.setListaDelCommunicationLineasMovMerc(locMovimientoDeMercaderia.getListaLineasMovimientoMercaderia());
        
        this.getElementoPila().getObjetos().set(0, locMovimientoDeMercaderia);
        this.getElementoPila().getObjetos().set(1, locDepositoOrigen);
        this.getElementoPila().getObjetos().set(2, locUsuario);
//        this.getElementoPila().getObjetos().set(3, locArea);
        this.getElementoPila().getObjetos().set(4, locDepositoDestino);
        this.getElementoPila().getObjetos().set(6, locTipoMovimiento);
	}
	
	public void valueChangeEventTipo(ActionEvent event) {
		MovimientoDeMercaderia locMovimientoDeMercaderia = (MovimientoDeMercaderia) this.obtenerObjetoDelElementoPila(0, MovimientoDeMercaderia.class);
		String tipoSelected = this.getDdTipoMovimiento().getSelected().toString();
		if(tipoSelected != null && tipoSelected.equals("MOVIMIENTO")){
			this.getDdDepositoDestino().setDisabled(false);
		} else{
			this.getDdDepositoDestino().setSelected("");
			this.getDdDepositoDestino().setDisabled(true);
			locMovimientoDeMercaderia.setDepositoDestino(null);
		}
	}
	
	public void valueChangeEventDepDestino(ActionEvent event) {
		String destinoSelectedString = this.getDdDepositoDestino().getSelected().toString();
		Deposito destino = this.getCommunicationComprasBean().getMapaDepositoDestino().get(destinoSelectedString);
		MovimientoDeMercaderia locMovimiento = (MovimientoDeMercaderia) this.obtenerObjetoDelElementoPila(0, MovimientoDeMercaderia.class);
		if(locMovimiento != null){
			locMovimiento.setDepositoDestino(destino);
			this.getLdpLineasMovMerc().setList(locMovimiento.getListaLineasMovimientoMercaderia());
			this.setListaDelCommunicationLineasMovMerc(locMovimiento.getListaLineasMovimientoMercaderia());
		}
	}
	
	public void valueChangeEventDepOrigen(ActionEvent event) {
		String origenSelectedString = this.getDdDepositoOrigen().getSelected().toString();
		Deposito origen = this.getCommunicationComprasBean().getMapaDepositosOrigen().get(origenSelectedString);
		MovimientoDeMercaderia locMovimiento = (MovimientoDeMercaderia) this.obtenerObjetoDelElementoPila(0, MovimientoDeMercaderia.class);
		if(locMovimiento != null){
			locMovimiento.setDeposito(origen);
			this.getLdpLineasMovMerc().setList(locMovimiento.getListaLineasMovimientoMercaderia());
			this.setListaDelCommunicationLineasMovMerc(locMovimiento.getListaLineasMovimientoMercaderia());
		}
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMMovimientoDeMercaderia";
	}
	
	@Override
	public long getSerialVersionUID() {
		return MovimientoDeMercaderia.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		MovimientoDeMercaderia locMovimientoDeMercaderia = this.obtenerObjetoDelElementoPila(0, MovimientoDeMercaderia.class);
		this.getTablaLogs().getLdpLogs().setList(locMovimientoDeMercaderia.getListaLogsAuditoria());
	}
}