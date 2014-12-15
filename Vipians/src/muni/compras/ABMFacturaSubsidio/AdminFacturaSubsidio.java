/*
 * AdminManzana.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */
package muni.compras.ABMFacturaSubsidio;

import java.util.List;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;

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
import com.trascender.compras.recurso.filtros.FiltroFacturaSubsidio;
import com.trascender.compras.recurso.persistent.suministros.FacturaProveedor;
import com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;


public class AdminFacturaSubsidio extends AdminPageBean {
    
    protected StaticText stFechaEmision = new StaticText();
    protected StaticText stProveedor = new StaticText();
    protected StaticText stEstado = new StaticText();
    protected StaticText stTotal2 = new StaticText();
    protected StaticText stTipoFactura = new StaticText();
    protected StaticText stFechaDesde = new StaticText();
    protected StaticText stFechaHasta = new StaticText();
    protected StaticText stDigestoMunicipal = new StaticText();    
    protected StaticText stTotal = new StaticText();
    protected StaticText stSeparador5 = new StaticText();
    
    protected TableColumn tcFechaEmision = new TableColumn();
    protected TableColumn tcProveedor = new TableColumn();    
    protected TableColumn tcEstado = new TableColumn();
    protected TableColumn tcMonto = new TableColumn();
    protected TableColumn tcTipoFactura = new TableColumn();
    protected TableColumn tcDigestoMunicipal = new TableColumn();
    
    protected Button btnSeleccionarProveedor = new Button();
    protected Button btnLimpiarProveedor = new Button();
    protected Button btnLimpiarDigesto = new Button();
    protected Button btnSeleccionarDigesto = new Button();
    protected Button btnImprimirReporte = new Button();
    
    protected Label lblFechaDesde = new Label();
    protected Label lblEstado = new Label();
    protected Label lblTotal = new Label();
    protected Label lblProveedor = new Label();
    protected Label lblFechaHasta = new Label();
    protected Label lblDigestoMunicipal = new Label();
    
    
    protected TextField tfProveedor = new TextField();
    protected TextField tfFechaDesde = new TextField();
    protected TextField tfDigestoMunicipal = new TextField();
    protected TextField tfFechaHasta = new TextField();
    
    protected DropDown ddEstado = new DropDown();
    protected SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();
    protected NumberConverter numberConverter1 = new NumberConverter();
    protected DateTimeConverter dateTimeConverter1 = new DateTimeConverter();    
    protected ObjectListDataProvider ldpFactura = new ObjectListDataProvider();
    
    //***GETTERS & SETTERS
    
    public Label getLblEstado() {
        return lblEstado;
    }

    public void setLblEstado(Label lblEstado) {
        this.lblEstado = lblEstado;
    }

    public Label getLblFechaDesde() {
        return lblFechaDesde;
    }

    public void setLblFechaDesde(Label lblFechaDesde) {
        this.lblFechaDesde = lblFechaDesde;
    }

    public Label getLblFechaHasta() {
        return lblFechaHasta;
    }

    public void setLblFechaHasta(Label lblFechaHasta) {
        this.lblFechaHasta = lblFechaHasta;
    }

    public Label getLblProveedor() {
        return lblProveedor;
    }

    public void setLblProveedor(Label lblProveedor) {
        this.lblProveedor = lblProveedor;
    }

    public Label getLblTotal() {
        return lblTotal;
    }

    public void setLblTotal(Label lblTotal) {
        this.lblTotal = lblTotal;
    }

    public TableColumn getTcDigestoMunicipal() {
        return tcDigestoMunicipal;
    }

    public void setTcDigestoMunicipal(TableColumn tcDigestoMunicipal) {
        this.tcDigestoMunicipal = tcDigestoMunicipal;
    }

    public TableColumn getTcEstado() {
        return tcEstado;
    }

    public void setTcEstado(TableColumn tcEstado) {
        this.tcEstado = tcEstado;
    }

    public TableColumn getTcFechaEmision() {
        return tcFechaEmision;
    }

    public void setTcFechaEmision(TableColumn tcFechaEmision) {
        this.tcFechaEmision = tcFechaEmision;
    }

    public TableColumn getTcMonto() {
        return tcMonto;
    }

    public void setTcMonto(TableColumn tcMonto) {
        this.tcMonto = tcMonto;
    }

    public TableColumn getTcProveedor() {
        return tcProveedor;
    }

    public void setTcProveedor(TableColumn tcProveedor) {
        this.tcProveedor = tcProveedor;
    }

    public TableColumn getTcTipoFactura() {
        return tcTipoFactura;
    }

    public void setTcTipoFactura(TableColumn tcTipoFactura) {
        this.tcTipoFactura = tcTipoFactura;
    }
    
    public StaticText getStDigestoMunicipal() {
        return stDigestoMunicipal;
    }

    public void setStDigestoMunicipal(StaticText stDigestoMunicipal) {
        this.stDigestoMunicipal = stDigestoMunicipal;
    }

    public StaticText getStEstado() {
        return stEstado;
    }

    public void setStEstado(StaticText stEstado) {
        this.stEstado = stEstado;
    }

    public StaticText getStFechaDesde() {
        return stFechaDesde;
    }

    public void setStFechaDesde(StaticText stFechaDesde) {
        this.stFechaDesde = stFechaDesde;
    }

    public StaticText getStFechaEmision() {
        return stFechaEmision;
    }

    public void setStFechaEmision(StaticText stFechaEmision) {
        this.stFechaEmision = stFechaEmision;
    }

    public StaticText getStFechaHasta() {
        return stFechaHasta;
    }

    public void setStFechaHasta(StaticText stFechaHasta) {
        this.stFechaHasta = stFechaHasta;
    }

    public StaticText getStProveedor() {
        return stProveedor;
    }

    public void setStProveedor(StaticText stProveedor) {
        this.stProveedor = stProveedor;
    }

    public StaticText getStSeparador5() {
        return stSeparador5;
    }

    public void setStSeparador5(StaticText stSeparador5) {
        this.stSeparador5 = stSeparador5;
    }

    public StaticText getStTipoFactura() {
        return stTipoFactura;
    }

    public void setStTipoFactura(StaticText stTipoFactura) {
        this.stTipoFactura = stTipoFactura;
    }

    public StaticText getStTotal2() {
        return stTotal2;
    }

    public void setStTotal2(StaticText stTotal2) {
        this.stTotal2 = stTotal2;
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

    public TextField getTfFechaDesde() {
        return tfFechaDesde;
    }

    public void setTfFechaDesde(TextField tf) {
        this.tfFechaDesde = tf;
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

    public Button getBtnLimpiarDigesto() {
        return btnLimpiarDigesto;
    }

    public void setBtnLimpiarDigesto(Button btnLimpiarDigesto) {
        this.btnLimpiarDigesto = btnLimpiarDigesto;
    }

    public Button getBtnLimpiarProveedor() {
        return btnLimpiarProveedor;
    }

    public void setBtnLimpiarProveedor(Button btnLimpiarProveedor) {
        this.btnLimpiarProveedor = btnLimpiarProveedor;
    }

    public Button getBtnSeleccionarDigesto() {
        return btnSeleccionarDigesto;
    }

    public void setBtnSeleccionarDigesto(Button btn) {
        this.btnSeleccionarDigesto = btn;
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

    public Label getLblDigestoMunicipal() {
        return lblDigestoMunicipal;
    }

    public void setLblDigestoMunicipal(Label lblDigestoMunicipal) {
        this.lblDigestoMunicipal = lblDigestoMunicipal;
    }

    public TextField getTfDigestoMunicipal() {
        return tfDigestoMunicipal;
    }

    public void setTfDigestoMunicipal(TextField tfDigestoMunicipal) {
        this.tfDigestoMunicipal = tfDigestoMunicipal;
    }

    public TextField getTfFechaHasta() {
        return tfFechaHasta;
    }

    public void setTfFechaHasta(TextField tf) {
        this.tfFechaHasta = tf;
    }
    
    public Button getBtnImprimirReporte() {
        return btnImprimirReporte;
    }

    public void setBtnImprimirReporte(Button b) {
        this.btnImprimirReporte = b;
    }

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationComprasBean().getTablaFacturaSubsidio();
	}
    
    //***METHODS***
    
    public AdminFacturaSubsidio() {
    }
    
    @Override
    protected void _init() throws Exception {

//        ddEstadoDefaultOptions.setOptions(new com.sun.rave.web.ui.model.Option[] {new com.sun.rave.web.ui.model.Option("CREADA", "CREADA"), new com.sun.rave.web.ui.model.Option("PAGADA", "PAGADA")});
//        ddEstadoDefaultOptions.setSelectedValue("CREADA");
        Option[] op = null;
        // Tipo Documento
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(FacturaSubsidio.Estado.values(), "may");
        ddEstadoDefaultOptions.setOptions(op);
        
        numberConverter1.setPattern("$ #,##0.00");
        numberConverter1.setMinIntegerDigits(1);
        numberConverter1.setMaxIntegerDigits(40);
        numberConverter1.setMaxFractionDigits(3);

        dateTimeConverter1.setTimeZone(null);
        dateTimeConverter1.setPattern("dd/MM/yyyy");
        dateTimeConverter1.setTimeStyle("full");
        
    }  
    
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
    	FiltroFacturaSubsidio filtroFacturaSubsidio = this.getFiltro();

        filtroFacturaSubsidio.setEstado(this.getDDEnumValue(this.getDdEstado(), FacturaProveedor.Estado.class));
        filtroFacturaSubsidio.setFechaDesde(this.getTextFieldValueDate(this.getTfFechaDesde()));
        filtroFacturaSubsidio.setFechaHasta(this.getTextFieldValueDate(this.getTfFechaHasta()));
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Revisar el metodo completo.
        FacturaSubsidio auxFacturaSubsidio = null;
        double monto = 0.0;
        List lista;
        
        FiltroFacturaSubsidio filtroFacturaSubsidio = this.getFiltro();
        
        this.getTfFechaDesde().setText(Conversor.getStringDeFechaCorta(filtroFacturaSubsidio.getFechaDesde()));
        this.getTfFechaHasta().setText(Conversor.getStringDeFechaCorta(filtroFacturaSubsidio.getFechaHasta()));
        this.getTfProveedor().setText(filtroFacturaSubsidio.getProveedor());
        
    	if (filtroFacturaSubsidio.getProveedor() != null) {
			this.getTfProveedor().setText(filtroFacturaSubsidio.getProveedor().getRazonSocial());
		}
    	if (filtroFacturaSubsidio.getDigestoMuncipal() != null) {
			this.getTfDigestoMunicipal().setText(filtroFacturaSubsidio.getDigestoMuncipal().toString());
		}
        if (this.getListaDelCommunication() != null && !this.getListaDelCommunication().isEmpty()) {
            lista = this.getListaDelCommunication();
            for (int i = 0; i < lista.size(); i++) {
                auxFacturaSubsidio = (FacturaSubsidio) lista.get(i);
                monto += auxFacturaSubsidio.getTotal().doubleValue();
            }
        }
        Double montoMostrar = new Double(monto);
        this.getStTotal().setText(montoMostrar);
        if (filtroFacturaSubsidio.getEstado() != null) {
			this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(filtroFacturaSubsidio.getEstado())));
		}
    }

    @Override
    protected void limpiarObjetosUsados() {
    	FiltroFacturaSubsidio locFiltro = this.getFiltro();
		locFiltro.setEstado(null);
		locFiltro.setFechaDesde(null);
		locFiltro.setFechaHasta(null);
		locFiltro.setProveedor(null);
		locFiltro.setDigestoMuncipal(null);
    	
        this.getDdEstado().setSelected(null);
        this.getTfFechaDesde().setText("");
        this.getTfFechaHasta().setText("");
        this.getTfProveedor().setText("");
        this.getTfDigestoMunicipal().setText("");
    }

    @Override
    public ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
        return this.getLdpFactura();
    }

    @Override
    protected List getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationComprasBean().getListaFacturaSubsidio();
    }

    @Override
    protected void setListaDelCommunication(List lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationComprasBean().setListaFacturaSubsidios(lista);
    }

    public String btnSeleccionarProveedor_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 1;

        if (ultimo) {

            // APLICAR LOGICA AQUI...

            try {
                RowKey rk = this.getSeleccionado();
                if (rk != null) {
                    this.setRowKeySeleccionado(this.getSeleccionado());
                }
            } catch (Exception ex) {
                // CAMBIAR:
                log(getCasoNavegacion() + "_SeleccionarProveedorError:", ex);
                error(getNombrePagina() + " - Seleccionar Proveedor: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
//            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
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

    public String btnSeleccionarDigesto_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 2;

        if (ultimo) {

            // APLICAR LOGICA AQUI...

            this.guardarEstadoObjetosUsados();
//            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminDigestoMunicipal";
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

    public String btnLimpiarProveedor_action() {
        // TODO: Replace with your code
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(this.getTfProveedor());
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnLimpiarDigesto_action() {
        // TODO: Replace with your code
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
            // CAMBIAR: Especificar objeto
            this.limpiarObjeto(this.getTfDigestoMunicipal());
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
        FacturaSubsidio locFacturaSubsidio = (FacturaSubsidio)pObject;
        getCommunicationComprasBean().getRemoteSystemAdministracionFacturaSubsidio().setLlave(this.getSessionBean1().getLlave());
        locFacturaSubsidio = this.getCommunicationComprasBean().getRemoteSystemAdministracionFacturaSubsidio().getFacturaSubsidioPorId(locFacturaSubsidio.getIdFactura());
        return locFacturaSubsidio;
    }
    
    public String btnAgregar_action(){
        return toAbm(new FacturaSubsidioModel().new AgregarController());
    }
    
    public String btnModificar_action(){
        return toAbm(new FacturaSubsidioModel().new ModificarController());
    }
    
    public String btnEliminar_action(){
        return toAbm(new FacturaSubsidioModel().new EliminarController());
    }
    
    public String btnConsultar_action(){
        return toAbm(new FacturaSubsidioModel().new ConsultarController());
    }

    @Override
    protected String getNombrePagina() {
        return "Administraci\363n de Facturas de Subsidio";
    }

    @Override
    protected String getCasoNavegacion() {
        return "AdminFacturaSubsidio";
    }

    @Override
    protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
	
    	this.getCommunicationComprasBean().getRemoteSystemAdministracionFacturaSubsidio().setLlave(this.getSessionBean1().getLlave());
		FiltroFacturaSubsidio locFiltro = getCommunicationComprasBean().getRemoteSystemAdministracionFacturaSubsidio().findListaFacturasSubsidios((FiltroFacturaSubsidio) pFiltro);

		double monto = 0.0;
		if (!locFiltro.getListaResultados().isEmpty()) {
			for (FacturaSubsidio cadaFactura : locFiltro.getListaResultados()) {
				monto += cadaFactura.getTotal().doubleValue();
			}
		}
		Double montoMostrar = new Double(monto);
		this.getStTotal().setText(montoMostrar);
		return locFiltro;
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroFacturaSubsidio locFiltro = this.getFiltro();
		if (pObject instanceof Proveedor) {
			locFiltro.setProveedor((Proveedor) pObject);
		}
		if (pObject instanceof DigestoMunicipal) {
			DigestoMunicipal digesto = (DigestoMunicipal) pObject;

			locFiltro.setDigestoMuncipal(digesto);
		}
		
	}
	
	@Override
	public long getSerialVersionUID() {
		return FacturaSubsidio.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMFacturaSubsidio$AdminFacturaSubsidio}";
	}
}