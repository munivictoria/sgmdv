/*
 * AdminMovimientoDeMercaderia.java
 *
 * Created on 18 de octubre de 2006, 8:55
 * Copyright Trascender SRL
 */
package muni.inventario.ABMMovimientoDeMercaderia;

import jasper.ConstantesReportes;

import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.filtros.FiltroMovimientoDeMercaderia;
import com.trascender.compras.recurso.persistent.inventario.Deposito;
import com.trascender.compras.recurso.persistent.inventario.MovimientoDeMercaderia;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;


public class AdminMovimientoDeMercaderia extends AdminPageBean {

    private final String NOMBRE_PAGINA = "Administraci\363n de Movimientos de Mercadería";
    // Motivo del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AdminMovimientoDeMercaderia";
    // Motivo del caso de navegacion para llegar a la pagina de caducidad

    @Override
    protected void _init() throws Exception {
    	Set<String> locListaAreas = this.getCommunicationComprasBean().getMapaAreasDeposito().keySet();
		Option[] opAreas = new Option[locListaAreas.size() + 1];
		int i = 0;
		opAreas[i++] = new Option("", "");
		for (String cadaArea : locListaAreas) {
			opAreas[i++] = new Option(cadaArea, cadaArea);
		}
		ddAreaOptions.setOptions(opAreas);
	    
		Set<String> locListaDepositos = this.getCommunicationComprasBean().getMapaDepositosOrigen().keySet();
		Option[] opDeposito = new Option[locListaDepositos.size() + 1];
		i = 0;
		opDeposito[i++] = new Option("", "");
		for (String cadaDeposito : locListaDepositos) {
			opDeposito[i++] = new Option(cadaDeposito, cadaDeposito);
		}
        ddDepositoOptions.setOptions(opDeposito);
        
        Option[] opTipo = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(MovimientoDeMercaderia.Tipo.values(), "may");
        ddTipoOptions.setOptions(opTipo);
    	
        this.habilitarBtnExportar();
    }
    
    private DropDown ddDeposito = new DropDown();
    private DropDown ddTipo = new DropDown();
    private TextField tfFechaDesde = new TextField();
    private TextField tfFechaHasta = new TextField();
    private SingleSelectOptionsList ddDepositoOptions = new SingleSelectOptionsList();
    private SingleSelectOptionsList ddTipoOptions = new SingleSelectOptionsList();
    private Label lblTipo = new Label();
    private Label lblDeposito = new Label();
    private Label lblFechaDesde = new Label();
    private Label lblFechaHasta = new Label();
    
    public Label getLblTipo() {
		return lblTipo;
	}

	public void setLblTipo(Label lblTipo) {
		this.lblTipo = lblTipo;
	}

	public Label getLblDeposito() {
		return lblDeposito;
	}

	public void setLblDeposito(Label lblDeposito) {
		this.lblDeposito = lblDeposito;
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

	public SingleSelectOptionsList getDdDepositoOptions() {
		return ddDepositoOptions;
	}

	public void setDdDepositoOptions(SingleSelectOptionsList ddDepositoOptions) {
		this.ddDepositoOptions = ddDepositoOptions;
	}

	public SingleSelectOptionsList getDdTipoOptions() {
		return ddTipoOptions;
	}

	public void setDdTipoOptions(SingleSelectOptionsList ddTipoOptions) {
		this.ddTipoOptions = ddTipoOptions;
	}

	public DropDown getDdDeposito() {
		return ddDeposito;
	}

	public void setDdDeposito(DropDown ddDeposito) {
		this.ddDeposito = ddDeposito;
	}

	public DropDown getDdTipo() {
		return ddTipo;
	}

	public void setDdTipo(DropDown ddTipo) {
		this.ddTipo = ddTipo;
	}

	public TextField getTfFechaDesde() {
		return tfFechaDesde;
	}

	public void setTfFechaDesde(TextField tfFechaDesde) {
		this.tfFechaDesde = tfFechaDesde;
	}

	public TextField getTfFechaHasta() {
		return tfFechaHasta;
	}

	public void setTfFechaHasta(TextField tfFechaHasta) {
		this.tfFechaHasta = tfFechaHasta;
	}

	private ObjectListDataProvider ldpMovimientoDeMercaderia = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpMovimientoDeMercaderia() {
        return ldpMovimientoDeMercaderia;
    }

    public void setLdpMovimientoDeMercaderia(ObjectListDataProvider pOldpMovimientoDeMercaderia) {
        this.ldpMovimientoDeMercaderia = pOldpMovimientoDeMercaderia;
    }

    private Label lblMotivo = new Label();

    public Label getLblMotivo() {
        return lblMotivo;
    }

    public void setLblMotivo(Label pLblMotivo) {
        this.lblMotivo = pLblMotivo;
    }
    private Label lblStockOrigen = new Label();

    public Label getLblStockOrigen() {
        return lblStockOrigen;
    }

    public void setLblStockOrigen(Label pLblStockOrigen) {
        this.lblStockOrigen = pLblStockOrigen;
    }
    private Label lblStockDestino = new Label();

    public Label getLblStockDestino() {
        return lblStockDestino;
    }

    public void setLblStockDestino(Label pLblStockDestino) {
        this.lblStockDestino = pLblStockDestino;
    }
    private Label lblArea = new Label();

    public Label getLblArea() {
        return lblArea;
    }

    public void setLblArea(Label pLblArea) {
        this.lblArea = pLblArea;
    }
    private Label lblCantidad = new Label();

    public Label getLblCantidad() {
        return lblCantidad;
    }

    public void setLblCantidad(Label pLblCantidad) {
        this.lblCantidad = pLblCantidad;
    }
    private Label lblUsuario = new Label();

    public Label getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(Label pLblUsuario) {
        this.lblUsuario = pLblUsuario;
    }
    
    private StaticText stColStock = new StaticText();

    public StaticText getStColStock() {
        return stColStock;
    }

    public void setStColStock(StaticText pStColStock) {
        this.stColStock = pStColStock;
    }
    private StaticText stColCantidad = new StaticText();

    public StaticText getStColCantidad() {
        return stColCantidad;
    }

    public void setStColCantidad(StaticText pStColCantidad) {
        this.stColCantidad = pStColCantidad;
    }
    private StaticText stColEstado = new StaticText();

    public StaticText getStColEstado() {
        return stColEstado;
    }

    public void setStColEstado(StaticText pStColEstado) {
        this.stColEstado = pStColEstado;
    }
    
    private Button btnSeleccionarDepositoOrigen = new Button();
    private Button btnLimpiarDepositoOrigen = new Button();
    private Button btnSeleccionarDepositoDestino = new Button();
    private Button btnLimpiarDepositoDestino = new Button();
    
    public Button getBtnSeleccionarDepositoOrigen() {
		return btnSeleccionarDepositoOrigen;
	}

	public void setBtnSeleccionarDepositoOrigen(Button btnSeleccionarDepositoOrigen) {
		this.btnSeleccionarDepositoOrigen = btnSeleccionarDepositoOrigen;
	}

	public Button getBtnLimpiarDepositoOrigen() {
		return btnLimpiarDepositoOrigen;
	}

	public void setBtnLimpiarDepositoOrigen(Button btnLimpiarDepositoOrigen) {
		this.btnLimpiarDepositoOrigen = btnLimpiarDepositoOrigen;
	}

	public Button getBtnSeleccionarDepositoDestino() {
		return btnSeleccionarDepositoDestino;
	}

	public void setBtnSeleccionarDepositoDestino(
			Button btnSeleccionarDepositoDestino) {
		this.btnSeleccionarDepositoDestino = btnSeleccionarDepositoDestino;
	}

	public Button getBtnLimpiarDepositoDestino() {
		return btnLimpiarDepositoDestino;
	}

	public void setBtnLimpiarDepositoDestino(Button btnLimpiarDepositoDestino) {
		this.btnLimpiarDepositoDestino = btnLimpiarDepositoDestino;
	}

	private Button btnSeleccionarUsuario = new Button();

    public Button getBtnSeleccionarUsuario() {
        return btnSeleccionarUsuario;
    }

    public void setBtnSeleccionarUsuario(Button pBtnSeleccionarUsuario) {
        this.btnSeleccionarUsuario = pBtnSeleccionarUsuario;
    }
    private Button btnLimpiarUsuario = new Button();

    public Button getBtnLimpiarUsuario() {
        return btnLimpiarUsuario;
    }

    public void setBtnLimpiarUsuario(Button pBtnLimpiarUsuario) {
        this.btnLimpiarUsuario = pBtnLimpiarUsuario;
    }
    
	private DropDown ddArea = new DropDown();
	private SingleSelectOptionsList ddAreaOptions = new SingleSelectOptionsList();
    private TextField tfUsuario = new TextField();

    public DropDown getDdArea() {
		return ddArea;
	}

	public void setDdArea(DropDown ddArea) {
		this.ddArea = ddArea;
	}

	public SingleSelectOptionsList getDdAreaOptions() {
		return ddAreaOptions;
	}

	public void setDdAreaOptions(SingleSelectOptionsList ddAreaOptions) {
		this.ddAreaOptions = ddAreaOptions;
	}

	public TextField getTfUsuario() {
        return tfUsuario;
    }

    public void setTfUsuario(TextField pTfUsuario) {
        this.tfUsuario = pTfUsuario;
    }
    
    private Button btnActualizar = new Button();

    public Button getBtnActualizar() {
        return btnActualizar;
    }

    public void setBtnActualizar(Button pBtnActualizar) {
        this.btnActualizar = pBtnActualizar;
    }
    
    private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText pStaticText2) {
        this.staticText2 = pStaticText2;
    }
    private StaticText staticText6 = new StaticText();

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText pStaticText6) {
        this.staticText6 = pStaticText6;
    }
    private StaticText staticText8 = new StaticText();

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText pStaticText8) {
        this.staticText8 = pStaticText8;
    }

    private TableColumn tableColumn1 = new TableColumn();

    public TableColumn getTableColumn1() {
        return tableColumn1;
    }

    public void setTableColumn1(TableColumn pTableColumn1) {
        this.tableColumn1 = pTableColumn1;
    }
    private TableColumn tableColumn5 = new TableColumn();

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn pTableColumn5) {
        this.tableColumn5 = pTableColumn5;
    }
    private TableColumn tableColumn6 = new TableColumn();

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn pTableColumn6) {
        this.tableColumn6 = pTableColumn6;
    }
    
    private TableColumn tableColumn2 = new TableColumn();

    public TableColumn getTableColumn2() {
        return tableColumn2;
    }

    public void setTableColumn2(TableColumn pTableColumn2) {
        this.tableColumn2 = pTableColumn2;
    }
    private StaticText stColMotivo = new StaticText();

    public StaticText getStColMotivo() {
        return stColMotivo;
    }

    public void setStColMotivo(StaticText pStColMotivo) {
        this.stColMotivo = pStColMotivo;
    }
    private StaticText stColTipo = new StaticText();

    public StaticText getStColTipo() {
        return stColTipo;
    }

    public void setStColTipo(StaticText pStColTipo) {
        this.stColTipo = pStColTipo;
    }
    private TableColumn tableColumn3 = new TableColumn();

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn pTableColumn3) {
        this.tableColumn3 = pTableColumn3;
    }
    private TableColumn tableColumn4 = new TableColumn();

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn pTableColumn4) {
        this.tableColumn4 = pTableColumn4;
    }
    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText pTableColumn3) {
        this.staticText3 = pTableColumn3;
    }
    
    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText pStaticText4) {
        this.staticText4 = pStaticText4;
    }
    private StaticText staticText9 = new StaticText();

    public StaticText getStaticText9() {
        return staticText9;
    }

    public void setStaticText9(StaticText staticText9) {
        this.staticText9 = staticText9;
    }
    private StaticText stSeparador10 = new StaticText();
	
	public StaticText getStSeparador10() {
		return stSeparador10;
	}

	public void setStSeparador10(StaticText stSeparador10) {
		this.stSeparador10 = stSeparador10;
	}

	private Button btnImprimirReporte = new Button();
	 
	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button btnImprimirReporte) {
		this.btnImprimirReporte = btnImprimirReporte;
	}

	/**
     * <p>Construir una instancia de bean de página.</p>
     */
    public AdminMovimientoDeMercaderia() {
    }

    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
    	int ind = 0;
    	ep.getObjetos().add(ind++, null); //String para saber si es stockOrigen o stockDestino
        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
    	FiltroMovimientoDeMercaderia locFiltro = this.getFiltro();
    	locFiltro.setArea(this.getDDObjectValue(getDdArea(), getCommunicationComprasBean().getMapaAreasDeposito()));
    	locFiltro.setDepositoOrigen(this.getDDObjectValue(getDdDeposito(), getCommunicationComprasBean().getMapaDepositosOrigen()));
    	locFiltro.setTipo(this.getDDEnumValue(getDdTipo(), MovimientoDeMercaderia.Tipo.class));
    	locFiltro.setFechaDesde(this.getTextFieldValueDate(getTfFechaDesde()));
		locFiltro.setFechaHasta(this.getTextFieldValueDate(getTfFechaHasta()));
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
    	FiltroMovimientoDeMercaderia locFiltro = this.getFiltro();

    	if (locFiltro.getArea() != null) {
			this.getDdArea().setSelected(locFiltro.getArea().toString());
		}
    	if (locFiltro.getDepositoOrigen() != null) {
			this.getDdDeposito().setSelected(locFiltro.getDepositoOrigen().toString());
		}
    	if (locFiltro.getTipo() != null) {
			this.getDdTipo().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipo() != null?locFiltro.getTipo().toString() : "")));
		}
    	this.getTfFechaDesde().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaDesde()));
		this.getTfFechaHasta().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaHasta()));
        this.getTfUsuario().setText(locFiltro.getUsuario());
    }

    @Override
    protected void limpiarObjetosUsados() {
    	FiltroMovimientoDeMercaderia locFiltro = this.getFiltro();
    	locFiltro.setArea(null);
    	locFiltro.setDepositoOrigen(null);
//    	locFiltro.setTipo(MovimientoDeMercaderia.Tipo.MOVIMIENTO);
    	locFiltro.setUsuario(null);
    	locFiltro.setFechaDesde(null);
    	locFiltro.setFechaHasta(null);

        // CAMBIAR: Limpiar los textField y dropDown
        this.getDdArea().setSelected(null);
        this.getDdDeposito().setSelected(null);
        this.getDdTipo().setSelected(null);
        this.getTfUsuario().setText("");
        this.getTfFechaDesde().setText("");
        this.getTfFechaHasta().setText("");
        //this.getDdEstadoDefaultOptions().setSelectedValue(null);
    }

    @Override
    public ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpMovimientoDeMercaderia();
    }

    @Override
    protected List getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationComprasBean().getListaMovimientosDeMercaderia();
    }

    @Override
    protected void setListaDelCommunication(List pListaMovimientosDeMercaderia) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationComprasBean().setListaMovimientosDeMercaderia(pListaMovimientosDeMercaderia);
    }
    
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
//            this.getElementoPila().getObjetos().set(0, "StockOrigen");
//
//            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
//            retorno = "AdminStock";
//        } else {
//            retorno = this.prepararCaducidad();
//        }
//
//        return retorno;
//    }
//
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
//            this.getElementoPila().getObjetos().set(0, "StockDestino");
//
//            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
//            retorno = "AdminStock";
//        } else {
//            retorno = this.prepararCaducidad();
//        }
//        return retorno;
//    }

    public String btnSeleccionarUsuario_action() {
        return navegarParaSeleccionar("AdminUsuario");
    }
    
    public String btnAgregar_action() {
        return toAbm(new MovimientoDeMercaderiaModel().new AgregarMovimientoController());
    }

    public String btnModificar_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            RowKey rk = null;
            try {

                rk = this.getSeleccionado();

                if (rk != null) {
                    int index = getNroFila(rk.toString());
                    Object obj = this.getObjectListDataProvider().getObjects()[index];
                    MovimientoDeMercaderia locMovimientoDeMercaderia = (MovimientoDeMercaderia) obj;
                    this.getCommunicationComprasBean().getRemoteSystemStock().setLlave(this.getSessionBean1().getLlave());
                    locMovimientoDeMercaderia = this.getCommunicationComprasBean().getRemoteSystemStock().getMovimientoDeMercaderiaByID(locMovimientoDeMercaderia.getIdMovimientoDeMercaderia());
                    return toAbm(new MovimientoDeMercaderiaModel(). new ModificarMovimientoController());
                }

            } catch (Exception ex) {
                log(CASO_NAVEGACION + "_ModificarError:", ex);
                error(NOMBRE_PAGINA + " - Modificar: " + ex.getMessage());
            }

            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            this.guardarOrdenamiento();
            Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
            this.getElementoPila().setPosicionGlobal(pos.longValue());

            if (rk != null) {
            	return toAbm(new MovimientoDeMercaderiaModel(). new ModificarMovimientoController());
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnConsultar_action() {
    	return toAbm(new MovimientoDeMercaderiaModel().new ConsultarMovimientoController());
    }

//    public String btnLimpiarStockOrigen_action() {
//        String retorno = null;
//        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
//        if (ultimo) {
//        	FiltroMovimientoDeMercaderia locFiltro = this.getFiltro();
//        	locFiltro.setDepositoOrigen(null);
//            this.getTfDepositoOrigen().setText(null);
//            this.guardarEstadoObjetosUsados();
//        } else {
//            retorno = this.prepararCaducidad();
//        }
//        return retorno;
//    }

//    public String btnLimpiarStockDestino_action() {
//        String retorno = null;
//        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
//        if (ultimo) {
//        	FiltroMovimientoDeMercaderia locFiltro = this.getFiltro();
//        	locFiltro.setDepositoDestino(null);
//            this.getTfDepositoDestino().setText(null);
//            this.guardarEstadoObjetosUsados();
//        } else {
//            retorno = this.prepararCaducidad();
//        }
//        return retorno;
//    }

    public String btnLimpiarUsuario_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        if (ultimo) {
        	FiltroMovimientoDeMercaderia locFiltro = this.getFiltro();
        	locFiltro.setUsuario(null);
            this.getTfUsuario().setText(null);
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		MovimientoDeMercaderia movimientoMerc = (MovimientoDeMercaderia) pObject;
		this.getCommunicationComprasBean().getRemoteSystemStock().setLlave(this.getSessionBean1().getLlave());
		movimientoMerc = this.getCommunicationComprasBean().getRemoteSystemStock().getMovimientoDeMercaderiaByID(movimientoMerc.getIdMovimientoDeMercaderia());
		return movimientoMerc;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationComprasBean().getRemoteSystemStock().setLlave(this.getSessionBean1().getLlave());
        return this.getCommunicationComprasBean().getRemoteSystemStock().findListaMovimientoDeMercaderia((FiltroMovimientoDeMercaderia)pFiltro);
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroMovimientoDeMercaderia locFiltro = this.getFiltro();
		if (pObject instanceof Deposito) {
                if (this.obtenerObjetoDelElementoPila(0, String.class).equals("DepositoOrigen")) {
                	Deposito locDepositoOrigen = (Deposito) pObject;
                    locFiltro.setDepositoOrigen(locDepositoOrigen);
                } else {
                	Deposito locDepositoDestino = (Deposito) pObject;
                    locFiltro.setDepositoDestino(locDepositoDestino);
                }
        }

        if (pObject instanceof Area) {
            Area locArea = (Area) pObject;
            locFiltro.setArea(locArea);
        }

        if (pObject instanceof Usuario) {
            Usuario locUsuario = (Usuario) pObject;
            locFiltro.setUsuario(locUsuario);
        }
	}
	
	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationComprasBean().getTablaMovimientoDeMercaderia();
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Movimientos de Mercadería";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminMovimientoDeMercaderia";
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
					MovimientoDeMercaderia locMovimientoMercaderia = (MovimientoDeMercaderia) obj;
					// this.getSessionBean1().setObjetoImpresion(obj);
					this.getCommunicationComprasBean().getRemoteSystemReportesCompras().setLlave(this.getSessionBean1().getLlave());
					JasperPrint jp = this.getCommunicationComprasBean().getRemoteSystemReportesCompras().getReporteMovimientoMercaderia(locMovimientoMercaderia.getIdMovimientoDeMercaderia());

					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_Movimiento_Mercaderia");
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
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
	
	@Override
	public long getSerialVersionUID() {
		return MovimientoDeMercaderia.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{inventario$ABMMovimientoDeMercaderia$AdminMovimientoDeMercaderia}";
	}
}