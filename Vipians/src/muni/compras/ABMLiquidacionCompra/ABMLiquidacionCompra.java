package muni.compras.ABMLiquidacionCompra;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.compras.recurso.persistent.suministros.LiquidacionCompra;
import com.trascender.compras.recurso.persistent.suministros.LiquidacionCompra.LineaLiquidacionCompra;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMLiquidacionCompra extends ABMPageBean {

	protected TextField tfNumero = new TextField();
    protected TextField tfFecha = new TextField();
    protected Label lblNumero = new Label();
    protected Label lblFecha = new Label();
    protected Label lblFacturas = new Label();
    protected Label lblLineasLiquidacionCompra = new Label();
    protected Button btnSeleccionarFacturaProveedor = new Button();
    protected Button btnSeleccionarFacturaServicio = new Button();
    protected Button btnSeleccionarFacturaSubsidio = new Button();
    protected Button btnSeleccionarFacturaContrato = new Button();
    protected Button btnQuitarFactura = new Button();
    protected Table tablaFacturas = new Table();
    protected Table tablaLineasLiquidacionCompra = new Table();
    protected TableRowGroup tableRowGroup1 = new TableRowGroup();
    protected TableRowGroup tableRowGroup2 = new TableRowGroup();
    protected ObjectListDataProvider ldpFacturas = new ObjectListDataProvider();
    protected ObjectListDataProvider ldpLineasLiquidacionCompra = new ObjectListDataProvider();
    protected TableColumn tableColumn1 = new TableColumn();
    protected TableColumn tcFechaEmision = new TableColumn();
    protected TableColumn tcTipoFactura = new TableColumn();
    protected TableColumn tcProveedor = new TableColumn();
    protected TableColumn tcMonto = new TableColumn();
    protected TableColumn tcUnidadMedida = new TableColumn();
    protected TableColumn tcCantidad = new TableColumn();
    protected TableColumn tcBien = new TableColumn();
    protected TableColumn tcPrecioUnitario = new TableColumn();
    protected TableColumn tcPrecioTotal = new TableColumn();
    protected StaticText stFechaEmision = new StaticText();
    protected StaticText stTipoFactura = new StaticText();
    protected StaticText stProveedor = new StaticText();
    protected StaticText stMonto = new StaticText();
    protected StaticText stUnidadMedida = new StaticText();
    protected StaticText stCantidad = new StaticText();
    protected StaticText stBien = new StaticText();
    protected StaticText stPrecioUnitario = new StaticText();
    protected StaticText stPrecioTotal = new StaticText();
    protected RadioButton radioButton1 = new RadioButton();
    protected PanelGroup groupPanel1 = new PanelGroup();
    
    //***Getters & Setters***
    
	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public Button getBtnQuitarFactura() {
		return btnQuitarFactura;
	}

	public void setBtnQuitarFactura(Button btnQuitarFactura) {
		this.btnQuitarFactura = btnQuitarFactura;
	}

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
		this.tableRowGroup2 = tableRowGroup2;
	}

	public TableColumn getTcUnidadMedida() {
		return tcUnidadMedida;
	}

	public void setTcUnidadMedida(TableColumn tcUnidadMedida) {
		this.tcUnidadMedida = tcUnidadMedida;
	}

	public TableColumn getTcCantidad() {
		return tcCantidad;
	}

	public void setTcCantidad(TableColumn tcCantidad) {
		this.tcCantidad = tcCantidad;
	}

	public TableColumn getTcBien() {
		return tcBien;
	}

	public void setTcBien(TableColumn tcBien) {
		this.tcBien = tcBien;
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

	public StaticText getStUnidadMedida() {
		return stUnidadMedida;
	}

	public void setStUnidadMedida(StaticText stUnidadMedida) {
		this.stUnidadMedida = stUnidadMedida;
	}

	public StaticText getStCantidad() {
		return stCantidad;
	}

	public void setStCantidad(StaticText stCantidad) {
		this.stCantidad = stCantidad;
	}

	public StaticText getStBien() {
		return stBien;
	}

	public void setStBien(StaticText stBien) {
		this.stBien = stBien;
	}

	public StaticText getStPrecioUnitario() {
		return stPrecioUnitario;
	}

	public void setStPrecioUnitario(StaticText stPrecioUnitario) {
		this.stPrecioUnitario = stPrecioUnitario;
	}

	public StaticText getStPrecioTotal() {
		return stPrecioTotal;
	}

	public void setStPrecioTotal(StaticText stPrecioTotal) {
		this.stPrecioTotal = stPrecioTotal;
	}

	public Label getLblLineasLiquidacionCompra() {
		return lblLineasLiquidacionCompra;
	}

	public void setLblLineasLiquidacionCompra(Label lblLineasLiquidacionCompra) {
		this.lblLineasLiquidacionCompra = lblLineasLiquidacionCompra;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	public Label getLblFacturas() {
		return lblFacturas;
	}

	public void setLblFacturas(Label lblFacturas) {
		this.lblFacturas = lblFacturas;
	}

	public TableColumn getTcFechaEmision() {
		return tcFechaEmision;
	}

	public void setTcFechaEmision(TableColumn tcFechaEmision) {
		this.tcFechaEmision = tcFechaEmision;
	}

	public StaticText getStFechaEmision() {
		return stFechaEmision;
	}

	public void setStFechaEmision(StaticText stFechaEmision) {
		this.stFechaEmision = stFechaEmision;
	}

	public TableColumn getTcTipoFactura() {
		return tcTipoFactura;
	}

	public void setTcTipoFactura(TableColumn tcTipoFactura) {
		this.tcTipoFactura = tcTipoFactura;
	}

	public StaticText getStTipoFactura() {
		return stTipoFactura;
	}

	public void setStTipoFactura(StaticText stTipoFactura) {
		this.stTipoFactura = stTipoFactura;
	}

	public TableColumn getTcProveedor() {
		return tcProveedor;
	}

	public void setTcProveedor(TableColumn tcProveedor) {
		this.tcProveedor = tcProveedor;
	}

	public StaticText getStProveedor() {
		return stProveedor;
	}

	public void setStProveedor(StaticText stProveedor) {
		this.stProveedor = stProveedor;
	}

	public TableColumn getTcMonto() {
		return tcMonto;
	}

	public void setTcMonto(TableColumn tcMonto) {
		this.tcMonto = tcMonto;
	}

	public StaticText getStMonto() {
		return stMonto;
	}

	public void setStMonto(StaticText stMonto) {
		this.stMonto = stMonto;
	}

	public TextField getTfNumero() {
		return tfNumero;
	}

	public void setTfNumero(TextField tfNumero) {
		this.tfNumero = tfNumero;
	}

	public TextField getTfFecha() {
		return tfFecha;
	}

	public void setTfFecha(TextField tfFecha) {
		this.tfFecha = tfFecha;
	}

	public Label getLblNumero() {
		return lblNumero;
	}

	public void setLblNumero(Label lblNumero) {
		this.lblNumero = lblNumero;
	}

	public Label getLblFecha() {
		return lblFecha;
	}

	public void setLblFecha(Label lblFecha) {
		this.lblFecha = lblFecha;
	}

	public Button getBtnSeleccionarFacturaProveedor() {
		return btnSeleccionarFacturaProveedor;
	}

	public void setBtnSeleccionarFacturaProveedor(
			Button btnSeleccionarFacturaProveedor) {
		this.btnSeleccionarFacturaProveedor = btnSeleccionarFacturaProveedor;
	}

	public Button getBtnSeleccionarFacturaServicio() {
		return btnSeleccionarFacturaServicio;
	}

	public void setBtnSeleccionarFacturaServicio(
			Button btnSeleccionarFacturaServicio) {
		this.btnSeleccionarFacturaServicio = btnSeleccionarFacturaServicio;
	}

	public Button getBtnSeleccionarFacturaSubsidio() {
		return btnSeleccionarFacturaSubsidio;
	}

	public void setBtnSeleccionarFacturaSubsidio(
			Button btnSeleccionarFacturaSubsidio) {
		this.btnSeleccionarFacturaSubsidio = btnSeleccionarFacturaSubsidio;
	}

	public Button getBtnSeleccionarFacturaContrato() {
		return btnSeleccionarFacturaContrato;
	}

	public void setBtnSeleccionarFacturaContrato(
			Button btnSeleccionarFacturaContrato) {
		this.btnSeleccionarFacturaContrato = btnSeleccionarFacturaContrato;
	}

	public Table getTablaFacturas() {
		return tablaFacturas;
	}

	public void setTablaFacturas(Table tablaFacturas) {
		this.tablaFacturas = tablaFacturas;
	}

	public Table getTablaLineasLiquidacionCompra() {
		return tablaLineasLiquidacionCompra;
	}

	public void setTablaLineasLiquidacionCompra(Table tablaLineasLiquidacionCompra) {
		this.tablaLineasLiquidacionCompra = tablaLineasLiquidacionCompra;
	}

	public ObjectListDataProvider getLdpFacturas() {
		return ldpFacturas;
	}

	public void setLdpFacturas(ObjectListDataProvider ldpFacturas) {
		this.ldpFacturas = ldpFacturas;
	}

	public ObjectListDataProvider getLdpLineasLiquidacionCompra() {
		return ldpLineasLiquidacionCompra;
	}

	public void setLdpLineasLiquidacionCompra(
			ObjectListDataProvider ldpLineasLiquidacionCompra) {
		this.ldpLineasLiquidacionCompra = ldpLineasLiquidacionCompra;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}
	
	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}
	
	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
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
			rk = this.getLdpFacturas().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}

	private List<Factura> getListaDelCommunicationFacturas() {
		return this.getCommunicationComprasBean().getListaFacturasLiquidacionCompra();
	}

	private void setListaDelCommunicationFacturas(List<Factura> lista) {
		this.getCommunicationComprasBean().setListaFacturasLiquidacionCompra(lista);
	}
	
	private ObjectListDataProvider getObjectListDataProviderFacturas() {
            return this.getLdpFacturas();
    }
	
	private List<LineaLiquidacionCompra> getListaDelCommunicationLineaLiquidacionCompra() {
		return this.getCommunicationComprasBean().getListaLineasLiquidacionCompra();
	}

	private void setListaDelCommunicationLineaLiquidacionCompra(List<LineaLiquidacionCompra> lista) {
		this.getCommunicationComprasBean().setListaLineasLiquidacionCompra(lista);
	}
	
	private ObjectListDataProvider getObjectListDataProviderLineasLiquidacionCompra() {
            return this.getLdpLineasLiquidacionCompra();
    }
	
    //***Methods***
    public ABMLiquidacionCompra() {
	}

    @Override
    protected void _init() throws Exception{
		if(this.getListaDelCommunicationFacturas() != null){
			this.getObjectListDataProviderFacturas().setList(this.getListaDelCommunicationFacturas());
		}
		if(this.getListaDelCommunicationLineaLiquidacionCompra() != null){
			this.getObjectListDataProviderLineasLiquidacionCompra().setList(this.getListaDelCommunicationLineaLiquidacionCompra());
		}
		getTablaFacturas().setClearSortButton(true);
		getTablaLineasLiquidacionCompra().setClearSortButton(true);
    }
    
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;

        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new LiquidacionCompra());
        
        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        
        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
        LiquidacionCompra locLiquidacionCompra = (LiquidacionCompra) this.obtenerObjetoDelElementoPila(0, LiquidacionCompra.class);
        
        locLiquidacionCompra.setNumero(getTextFieldValue(this.getTfNumero()));
        locLiquidacionCompra.setFecha(getTextFieldValueDate(this.getTfFecha()));
        
        this.getElementoPila().getObjetos().set(0, locLiquidacionCompra);
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
    	LiquidacionCompra locLiqudiacionCompra = (LiquidacionCompra) this.obtenerObjetoDelElementoPila(0, LiquidacionCompra.class);

        this.getTfNumero().setText(locLiqudiacionCompra.getNumero());
        
        if(locLiqudiacionCompra.getFecha() == null){
        	locLiqudiacionCompra.setFecha(new Date());
        }        
        this.getTfFecha().setText(Conversor.getStringDeFechaCorta(locLiqudiacionCompra.getFecha()));
        
        this.getObjectListDataProviderFacturas().setList(locLiqudiacionCompra.getListaFacturas());
        this.setListaDelCommunicationFacturas(this.getObjectListDataProviderFacturas().getList());
        
        this.getObjectListDataProviderLineasLiquidacionCompra().setList(locLiqudiacionCompra.getListaLineasLiquidacionCompra());
        this.setListaDelCommunicationLineaLiquidacionCompra(this.getObjectListDataProviderLineasLiquidacionCompra().getList());
    }

    public String btnSeleccionarFacturaProveedor_action() {
       return navegarParaSeleccionar("AdminFacturaProveedor");
    }
    
    public String btnSeleccionarFacturaServicio_action() {
        return navegarParaSeleccionar("AdminFacturaServicio");
    }
    
    public String btnSeleccionarFacturaContrato_action() {
        return navegarParaSeleccionar("AdminFacturaContrato");
    }
     
	public String btnSeleccionarFacturaSubsidio_action() {
	    return navegarParaSeleccionar("AdminFacturaSubsidio");
	}
	
	public String btnQuitarFactura_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if (ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderFacturas().getObjects()[index];
					LiquidacionCompra locLiquidacionCompra = (LiquidacionCompra) this.obtenerObjetoDelElementoPila(0, LiquidacionCompra.class);
					locLiquidacionCompra.getListaFacturas().remove(obj);
					locLiquidacionCompra.recrearListaLineasLiquidacionCompra();
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
	
	@Override
	protected String getCasoNavegacion() {
	    return "ABMLiquidacionCompra";
	}
	
	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof Factura){
			Factura locFactura = (Factura) pObject;
			
			if(locFactura.getLiquidacionCompra() == null){
			
				try{
					this.getCommunicationComprasBean().getRemoteSystemAdministracionFactura().setLlave(this.getSessionBean1().getLlave());
					locFactura = this.getCommunicationComprasBean().getRemoteSystemAdministracionFactura().getFacturaPorId(locFactura.getIdFactura());
				} catch(Exception e){
					e.printStackTrace();
				}
				
				LiquidacionCompra locLiquidacionCompra = (LiquidacionCompra) this.obtenerObjetoDelElementoPila(0, LiquidacionCompra.class);
				locLiquidacionCompra.getListaFacturas().add(locFactura);
				
				this.getObjectListDataProviderLineasLiquidacionCompra().setList(locLiquidacionCompra.recrearListaLineasLiquidacionCompra());
				this.setListaDelCommunicationLineaLiquidacionCompra(this.getObjectListDataProviderLineasLiquidacionCompra().getList());
				
				this.getElementoPila().getObjetos().set(0, locLiquidacionCompra);
			}
			else{
				error("La Factura seleccionada ya posee una Liquidación de Compra asociada");
			}
		}
	}
	
	@Override
	protected void procesarObjetoABM(Object pObject) {
		LiquidacionCompra locLiquidacionCompra = (LiquidacionCompra) pObject;
		
		try{
			this.getCommunicationComprasBean().getRemoteSystemAdministracionFactura().setLlave(this.getSessionBean1().getLlave());
			locLiquidacionCompra = this.getCommunicationComprasBean().getRemoteSystemAdministracionFactura().getLiquidacionCompraPorId(locLiquidacionCompra.getIdLiquidacionCompra());
		} catch(Exception e){
			e.printStackTrace();
		}
		locLiquidacionCompra.recrearListaLineasLiquidacionCompra();
		this.getElementoPila().getObjetos().set(0, locLiquidacionCompra);
	}
	
	@Override
	public long getSerialVersionUID() {
		return LiquidacionCompra.serialVersionUID;
	}
	
	@Override
	public String getNombreBean(){
		return "#{compras$ABMLiquidacionCompra$ABMLiquidacionCompra}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		LiquidacionCompra locLiquidacion = this.obtenerObjetoDelElementoPila(0, LiquidacionCompra.class);
		this.getTablaLogs().getLdpLogs().setList(locLiquidacion.getListaLogsAuditoria());
	}
}
