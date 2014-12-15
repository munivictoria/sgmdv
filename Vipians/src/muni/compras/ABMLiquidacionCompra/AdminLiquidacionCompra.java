package muni.compras.ABMLiquidacionCompra;

import jasper.ConstantesReportes;

import java.util.List;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.compras.recurso.filtros.FiltroLiquidacionCompra;
import com.trascender.compras.recurso.persistent.suministros.LiquidacionCompra;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminLiquidacionCompra extends AdminPageBean{

	protected ObjectListDataProvider ldpLiquidacionCompra = new ObjectListDataProvider();
    private Label lblNumero = new Label();
    private Label lblFecha = new Label(); 
    private TextField tfNumero = new TextField();
    protected Button btnImprimirReporte = new Button();
    private TextField tfFecha = new TextField();
    
    public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button btnImprimirReporte) {
		this.btnImprimirReporte = btnImprimirReporte;
	}

	public Label getLblFecha() {
		return lblFecha;
	}

	public void setLblFecha(Label lblFecha) {
		this.lblFecha = lblFecha;
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

	public TextField getTfNumero() {
		return tfNumero;
	}

	public void setTfNumero(TextField tfNumero) {
		this.tfNumero = tfNumero;
	}

	public ObjectListDataProvider getLdpLiquidacionCompra() {
        return ldpLiquidacionCompra;
    }

    public void setLdpLiquidacionCompra(ObjectListDataProvider oldp) {
        this.ldpLiquidacionCompra = oldp;
    }
    
    //***Methods***
    public AdminLiquidacionCompra() {
    }
    
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
        FiltroLiquidacionCompra locFiltroLiquidacionCompra = this.getFiltro();
        
        locFiltroLiquidacionCompra.setNumero(getTextFieldValue(this.getTfNumero()));
        locFiltroLiquidacionCompra.setFecha(getTextFieldValueDate(this.getTfFecha()));
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
        FiltroLiquidacionCompra locFiltroLiquidacionCompra = this.getFiltro();
        
        this.getTfNumero().setText(locFiltroLiquidacionCompra.getNumero());
        this.getTfFecha().setText(Conversor.getStringDeFechaCorta(locFiltroLiquidacionCompra.getFecha())); 
    }
    @Override
    protected void limpiarObjetosUsados() {
    	FiltroLiquidacionCompra locFiltro = this.getFiltro();
    	locFiltro.setNumero(null);
    	locFiltro.setFecha(null);
    	
    	this.getTfFecha().setText("");
    	this.getTfNumero().setText("");
    }
    
    @Override
    public ObjectListDataProvider getObjectListDataProvider() {
        return this.getLdpLiquidacionCompra();
    }

    @Override
    protected List getListaDelCommunication() {
        return this.getCommunicationComprasBean().getListaLiquidacionCompra();
    }

    @Override
    protected void setListaDelCommunication(List lista) {
    	this.getCommunicationComprasBean().setListaLiquidacionCompra(lista);
    }
    
    @Override
    protected Object getObjectPorId(Object pObject) throws Exception {
        LiquidacionCompra locLiquidacionCompra = (LiquidacionCompra) pObject;
        this.getCommunicationComprasBean().getRemoteSystemAdministracionFactura().setLlave(this.getSessionBean1().getLlave());
        locLiquidacionCompra = getCommunicationComprasBean().getRemoteSystemAdministracionFactura().getLiquidacionCompraPorId(locLiquidacionCompra.getIdLiquidacionCompra());
        return locLiquidacionCompra;
    }
    
    @Override
    protected String getNombrePagina() {
        return "Administraci\363n de Liquidaciones de Factura";
    }

    @Override
    protected String getCasoNavegacion() {
        return "AdminLiquidacionCompra";
    }
    
    
    public String btnAgregar_action(){
        return toAbm(new LiquidacionCompraModel().new AgregarLiquidacionCompraController());
    }
    
    public String btnModificar_action(){
        return toAbm(new LiquidacionCompraModel().new ModificarLiquidacionCompraController());
    }
    
    public String btnEliminar_action(){
        return toAbm(new LiquidacionCompraModel().new EliminarLiquidacionCompraController());
    }
    
   public String btnConsultar_action(){
        return toAbm(new LiquidacionCompraModel().new ConsultarLiquidacionCompraController());
    }

    @Override
    protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
        FiltroLiquidacionCompra locFiltro = (FiltroLiquidacionCompra) pFiltro;
        this.getCommunicationComprasBean().getRemoteSystemAdministracionFactura().setLlave(this.getSessionBean1().getLlave());
        return this.getCommunicationComprasBean().getRemoteSystemAdministracionFactura().findListaLiquidacionCompra(locFiltro);
    }
    
    @Override
    public PaginatedTable getPaginatedTable(){
        return this.getCommunicationComprasBean().getTablaLiquidacionCompra();
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	} 
	
	@Override
	public long getSerialVersionUID() {
		return LiquidacionCompra.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMLiquidacionCompra$AdminLiquidacionCompra}";
	}
	
	public String btnImprimirReporte_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		boolean errorEnReporte = false;

		if (ultimo) {
			try {
				RowKey rk = null;
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = this.getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					LiquidacionCompra locLiquidacionCompra = (LiquidacionCompra) obj;
//					if (locLiquidacionCompra != null) {
//						warn("La Orden de Compra debe estar APROBADA para imprimirse");
//						errorEnReporte = true;
//					} else {
						this.getCommunicationComprasBean().getRemoteSystemReportesCompras().setLlave(this.getSessionBean1().getLlave());
						JasperPrint jp = this.getCommunicationComprasBean().getRemoteSystemReportesCompras().getReporteLiquidacionCompra(locLiquidacionCompra.getIdLiquidacionCompra());
	
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_Liquidacion_Compra");
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
//					}
				} else {
					errorEnReporte = true;
				}
			} catch (Exception e) {
				log(getCasoNavegacion() + "_ReporteDinamicoError: ", e);
				error(getNombrePagina() + " - ReporteDinamico: " + e.getMessage());
				errorEnReporte = true;
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		if (errorEnReporte) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
		}
		return retorno;
	}
}
