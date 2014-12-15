/*
 * AdminManzana.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */
package muni.compras.ABMFacturaServicio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;

import muni.CommunicationMesaEntradaBean;
import muni.compras.ABMFacturaSubsidio.ABMFacturaSubsidio;
import muni.entrada.ABMTramite.AdminTramite;
import muni.entrada.ABMTramite.AgregarTramite;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.filtros.FiltroFacturaServicio;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.FacturaProveedor;
import com.trascender.compras.recurso.persistent.suministros.FacturaServicio;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class AdminFacturaServicio extends AdminPageBean {
    
    private Label lblFechaDesde = new Label();
    private Label lblFechaHasta = new Label();
    private Label lblProveedor = new Label();
    private Label lblEstado = new Label();
    private Label lblTotal = new Label();
    private Label lblBien = new Label();
    
    private TableColumn tcFechaEmision = new TableColumn();
    private TableColumn tcTipoFactura = new TableColumn();
    private TableColumn tcProveedor = new TableColumn();
    private TableColumn tcEstado = new TableColumn();
    private TableColumn tcMonto = new TableColumn();
    
    private StaticText stFechaEmision = new StaticText();
    private StaticText stTipoFactura = new StaticText();
    private StaticText stProveedor = new StaticText();
    private StaticText stEstado = new StaticText();
    private StaticText stTotal = new StaticText();
    private StaticText stTotal2 = new StaticText();
    private StaticText stSeparador5 = new StaticText();
    
    private Button btnLimpiarBien = new Button();
    private Button btnLimpiarProveedor = new Button();
    private Button btnSeleccionarBien = new Button();
    private Button btnSeleccionarProveedor = new Button();
    private Button btnImprimirReporte = new Button();
    
    private TextField tfBien = new TextField();
    private TextField tfFechaDesde = new TextField();
    private TextField tfProveedor = new TextField();
    private TextField tfFechaHasta = new TextField();
    
    private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();
    
    private ObjectListDataProvider ldpFactura = new ObjectListDataProvider();
    
    private DropDown ddEstado = new DropDown();
    
    private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();
    
    private NumberConverter numberConverter1 = new NumberConverter();
    
    @Override
    protected void _init() throws Exception {
        
        Option[] op = null;
        // Tipo Documento
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(FacturaServicio.Estado.values(), "may");
        ddEstadoDefaultOptions.setOptions(op);
        
        numberConverter1.setPattern("$ #,##0.00");
        numberConverter1.setMinIntegerDigits(1);
        numberConverter1.setMaxIntegerDigits(40);
        numberConverter1.setMaxFractionDigits(3);

        dateTimeConverter1.setTimeZone(null);
        dateTimeConverter1.setPattern("dd/MM/yyyy");
        dateTimeConverter1.setTimeStyle("full");

    }

    public TableColumn getTcTipoFactura() {
        return tcTipoFactura;
    }

    public void setTcTipoFactura(TableColumn tcTipoFactura) {
        this.tcTipoFactura = tcTipoFactura;
    }
    
    public TableColumn getTcFechaEmision() {
        return tcFechaEmision;
    }

    public void setTcFechaEmision(TableColumn tcFechaEmision) {
        this.tcFechaEmision = tcFechaEmision;
    }
    
    public Label getLblEstado() {
        return lblEstado;
    }

    public void setLblEstado(Label lblEstado) {
        this.lblEstado = lblEstado;
    }
    
    public Label getLblProveedor() {
        return lblProveedor;
    }

    public void setLblProveedor(Label lblProveedor) {
        this.lblProveedor = lblProveedor;
    }
    
    public Label getLblFechaHasta() {
        return lblFechaHasta;
    }

    public void setLblFechaHasta(Label lblFechaHasta) {
        this.lblFechaHasta = lblFechaHasta;
    }
    
    public Label getLblFechaDesde() {
        return lblFechaDesde;
    }

    public void setLblFechaDesde(Label lblFechaDesde) {
        this.lblFechaDesde = lblFechaDesde;
    }

    public Button getBtnLimpiarBien() {
        return btnLimpiarBien;
    }

    public void setBtnLimpiarBien(Button b) {
        this.btnLimpiarBien = b;
    }

    public Button getBtnLimpiarProveedor() {
        return btnLimpiarProveedor;
    }

    public void setBtnLimpiarProveedor(Button b) {
        this.btnLimpiarProveedor = b;
    }

    public DateTimeConverter getDateTimeConverter1() {
        return dateTimeConverter1;
    }

    public void setDateTimeConverter1(DateTimeConverter dtc) {
        this.dateTimeConverter1 = dtc;
    }

    public ObjectListDataProvider getLdpFactura() {
        return ldpFactura;
    }

    public void setLdpFactura(ObjectListDataProvider oldp) {
        this.ldpFactura = oldp;
    }
    
    public StaticText getStFechaEmision() {
        return stFechaEmision;
    }

    public void setStFechaEmision(StaticText stFechaEmision) {
        this.stFechaEmision = stFechaEmision;
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

    public TableColumn getTcEstado() {
        return tcEstado;
    }

    public void setTcEstado(TableColumn tcEstado) {
        this.tcEstado = tcEstado;
    }

    public StaticText getStEstado() {
        return stEstado;
    }

    public void setStEstado(StaticText stEstado) {
        this.stEstado = stEstado;
    }

    public Label getLblBien() {
        return lblBien;
    }

    public void setLblBien(Label lblCotrato) {
        this.lblBien = lblCotrato;
    }

    public TextField getTfBien() {
        return tfBien;
    }

    public void setTfBien(TextField tfBien) {
        this.tfBien = tfBien;
    }

    public TextField getTfFechaDesde() {
        return tfFechaDesde;
    }

    public void setTfFechaDesde(TextField tf) {
        this.tfFechaDesde = tf;
    }

    public Button getBtnSeleccionarBien() {
        return btnSeleccionarBien;
    }

    public void setBtnSeleccionarBien(Button btnSeleccionarBien) {
        this.btnSeleccionarBien = btnSeleccionarBien;
    }

    public DropDown getDdEstado() {
        return ddEstado;
    }

    public void setDdEstado(DropDown dd) {
        this.ddEstado = dd;
    }

    public SingleSelectOptionsList getDdEstadoDefaultOptions() {
        return ddEstadoDefaultOptions;
    }

    public void setDdEstadoDefaultOptions(SingleSelectOptionsList ssol) {
        this.ddEstadoDefaultOptions = ssol;
    }

    public TextField getTfProveedor() {
        return tfProveedor;
    }

    public void setTfProveedor(TextField tf) {
        this.tfProveedor = tf;
    }

    public Button getBtnSeleccionarProveedor() {
        return btnSeleccionarProveedor;
    }

    public void setBtnSeleccionarProveedor(Button b) {
        this.btnSeleccionarProveedor = b;
    }
    
    public TableColumn getTcMonto() {
        return tcMonto;
    }

    public void setTcMonto(TableColumn tcMonto) {
        this.tcMonto = tcMonto;
    }

    public StaticText getStTotal2() {
        return stTotal2;
    }

    public void setStTotal2(StaticText stTotal2) {
        this.stTotal2 = stTotal2;
    }

    public Label getLblTotal() {
        return lblTotal;
    }

    public void setLblTotal(Label lblTotal) {
        this.lblTotal = lblTotal;
    }
    
    public StaticText getStTotal() {
        return stTotal;
    }

    public void setStTotal(StaticText st) {
        this.stTotal = st;
    }

    public NumberConverter getNumberConverter1() {
        return numberConverter1;
    }

    public void setNumberConverter1(NumberConverter nc) {
        this.numberConverter1 = nc;
    }

    public TextField getTfFechaHasta() {
        return tfFechaHasta;
    }

    public void setTfFechaHasta(TextField tf) {
        this.tfFechaHasta = tf;
    }

    public StaticText getStTipoFactura() {
        return stTipoFactura;
    }

    public void setStTipoFactura(StaticText stTipoFactura) {
        this.stTipoFactura = stTipoFactura;
    }

    public StaticText getStSeparador5() {
        return stSeparador5;
    }

    public void setStSeparador5(StaticText stSeparador5) {
        this.stSeparador5 = stSeparador5;
    }

    public Button getBtnImprimirReporte() {
        return btnImprimirReporte;
    }

    public void setBtnImprimirReporte(Button b) {
        this.btnImprimirReporte = b;
    }

    public AdminFacturaServicio() {
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new FacturaServicio());
        ep.getObjetos().add(ind++, new Proveedor());
        ep.getObjetos().add(ind++, new ArrayList());
        ep.getObjetos().add(ind++, null);
        ep.getObjetos().add(ind++, null);
        ep.getObjetos().add(ind++, null); //Bien
        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
    	
    	FiltroFacturaServicio locFiltro = this.getFiltro();
    	locFiltro.setEstado(this.getDDEnumValue(getDdEstado(), FacturaServicio.Estado.class));
		locFiltro.setFechaDesde(this.getTextFieldValueDate(getTfFechaDesde()));
		locFiltro.setFechaHasta(this.getTextFieldValueDate(getTfFechaHasta()));
    	
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        FacturaServicio facturaServicio = null;
        FacturaServicio auxFacturaServicio = null;

        double monto = 0.0;
        List lista;

        FiltroFacturaServicio locFiltro = this.getFiltro();
        
        locFiltro.setEstado(this.getDDEnumValue(getDdEstado(), FacturaProveedor.Estado.class));
    	this.getTfFechaDesde().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaDesde()));
		this.getTfFechaHasta().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaHasta()));
		this.getTfProveedor().setText(locFiltro.getProveedor());
		this.getTfBien().setText(locFiltro.getBien());

        if (this.getListaDelCommunication() != null && !this.getListaDelCommunication().isEmpty()) {
            lista = this.getListaDelCommunication();
            for (int i = 0; i < lista.size(); i++) {
                auxFacturaServicio = (FacturaServicio) lista.get(i);
                monto += auxFacturaServicio.getTotal().doubleValue();
            }
        }

        Double montoMostrar = new Double(monto);
        this.getStTotal().setText(montoMostrar);

        //Marcos: new
        this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
        this.getDdEstadoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
        
//        if (this.getLdpFactura().getList() != null){
//             Long filaSeleccionada = new Long(this.getElementoPila().getPosicionGlobal());
//             System.out.println("filaSeleccionada :" +filaSeleccionada);
//             this.seleccionarFila(filaSeleccionada);
//        }
    }

    @Override
    protected void limpiarObjetosUsados() {
    	FiltroFacturaServicio locFiltro = this.getFiltro();
		locFiltro.setEstado(null);
		locFiltro.setFechaDesde(null);
		locFiltro.setFechaHasta(null);
		locFiltro.setProveedor(null);
		locFiltro.setBien(null);
    	
    	this.getDdEstado().setSelected(null);
        this.getTfFechaDesde().setText("");
        this.getTfFechaHasta().setText("");
        this.getTfProveedor().setText("");
    }

    @Override
    public ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpFactura();
    }

    @Override
    protected List getListaDelCommunication() {
        return this.getCommunicationComprasBean().getListaFacturaServicios();
    }

    @Override
    protected void setListaDelCommunication(List lista) {
        this.getCommunicationComprasBean().setListaFacturaServicios(lista);
    }

    public String btnAceptarOrdenCompra_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;

            try {

                rk = this.getSeleccionado();

                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    getRequestBean1().setObjetoABM(obj);

                    this.setRowKeySeleccionado(this.getSeleccionado());
                }

            } catch (Exception ex) {
                log(this.getCasoNavegacion() + "_AceptarError:", ex);
                error(this.getNombrePagina() + " - Aceptar: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());

            if (rk != null) {
                retorno = "AceptarOrdenCompra";
            }
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnSeleccionarProveedor_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 1;

        if (ultimo) {

            try {
                RowKey rk = this.getSeleccionado();
                if (rk != null) {
                    this.setRowKeySeleccionado(this.getSeleccionado());
                }
            } catch (Exception ex) {
                // CAMBIAR:
                log(this.getCasoNavegacion() + "_SeleccionarProveedorError:", ex);
                error(this.getNombrePagina() + " - Seleccionar Proveedor: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminProveedor";
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;

    }

    public String btnImprimirReporte_action() {
        // TODO: Process the button click action. Return value is a navigation
        // case name where null will return to the same page.
        return null;
    }

    protected CommunicationMesaEntradaBean getCommunicationMesaEntradaBean() {
        return (CommunicationMesaEntradaBean) getBean("CommunicationMesaEntradaBean");
    }

    protected ABMFacturaSubsidio getcompras$ABMFacturaSubsidio$AgregarFacturaSubsidio() {
        return (ABMFacturaSubsidio) getBean("compras$ABMFacturaSubsidio$AgregarFacturaSubsidio");
    }

    protected AdminTramite getentrada$ABMTramite$AdminTramite() {
        return (AdminTramite) getBean("entrada$ABMTramite$AdminTramite");
    }

    protected AgregarTramite getentrada$ABMTramite$AgregarTramite() {
        return (AgregarTramite) getBean("entrada$ABMTramite$AgregarTramite");
    }

    public String btnSeleccionarBien_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 5;

        if (ultimo) {

            // APLICAR LOGICA AQUI...

            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminBien";
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnLimpiarBien_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
//            this.limpiarObjeto(5, this.getTfBien());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnLimpiarProveedor_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
//            this.limpiarObjeto(1, this.getTfProveedor());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    @Override
    protected TableSelectPhaseListener getTablePhaseListener() {
        return null;
    }

    @Override
    protected Object getObjectPorId(Object pObject) throws Exception {
        FacturaServicio locFacturaServicio = (FacturaServicio) pObject;
        this.getCommunicationComprasBean().getRemoteSystemAdministracionFacturaServicio().setLlave(this.getSessionBean1().getLlave());
        locFacturaServicio = this.getCommunicationComprasBean().getRemoteSystemAdministracionFacturaServicio().getFacturaServicioPorId(locFacturaServicio.getIdFactura());
        return locFacturaServicio;
    }
    
    public String btnAgregar_action(){
        return toAbm(new FacturaServicioModel().new AgregarController());
    }
    
    public String btnModificar_action(){        
        return toAbm(new FacturaServicioModel().new ModificarController());
    }
    
    public String btnEliminar_action(){
        return toAbm(new FacturaServicioModel().new EliminarController());
    }
    
    public String btnConsultar_action(){
        return toAbm(new FacturaServicioModel().new ConsultarController());
    }

    @Override
    protected String getNombrePagina() {
        return "Administraci\363n de Facturas de Servicio";
    }

    @Override
    protected String getCasoNavegacion() {
        return "AdminFacturaServicio";
    }

    @Override
    protected void _prerender() throws Exception {
        
    }

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationComprasBean().getTablaFacturaServicio();
	}
    
    @Override
    protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationComprasBean().getRemoteSystemAdministracionFacturaServicio().setLlave(this.getSessionBean1().getLlave());
		FiltroFacturaServicio locFiltro = getCommunicationComprasBean().getRemoteSystemAdministracionFacturaServicio().findListaFacturasServicio((FiltroFacturaServicio)pFiltro);

		double monto = 0.0;
		if (!locFiltro.getListaResultados().isEmpty()) {
			for (FacturaServicio cadaFactura : locFiltro.getListaResultados()) {
				monto += cadaFactura.getTotal().doubleValue();
			}
		}
		Double montoMostrar = new Double(monto);
		this.getStTotal().setText(montoMostrar);
		return locFiltro;
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroFacturaServicio locFiltro = this.getFiltro();
		if (pObject instanceof Proveedor) {
			locFiltro.setProveedor((Proveedor) pObject);
		}
		if (pObject instanceof Bien) {
			Bien bien = (Bien) pObject;

			locFiltro.setBien(bien);
		}
	}
	
	@Override
	public long getSerialVersionUID() {
		return FacturaServicio.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMFacturaServicio$AdminFacturaServicio}";
	}
}