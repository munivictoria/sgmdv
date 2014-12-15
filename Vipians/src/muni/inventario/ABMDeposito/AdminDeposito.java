/*
 * AdminDeposito.java
 *
 * Created on 18 de octubre de 2006, 8:55
 * Copyright Trascender SRL
 */
package muni.inventario.ABMDeposito;

import java.util.List;
import java.util.Set;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.filtros.FiltroDeposito;
import com.trascender.compras.recurso.persistent.inventario.Deposito;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class AdminDeposito extends AdminPageBean {

    @Override
    protected void _init() throws Exception {
        if (this.getListaDelCommunication() != null) {
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }

    	Set<String> locListaAreas = this.getCommunicationComprasBean().getMapaAreasDeposito().keySet();
		Option[] opAreas = new Option[locListaAreas.size() + 1];
		int i = 0;
		opAreas[i++] = new Option("", "");
		for (String cadaArea : locListaAreas) {
			opAreas[i++] = new Option(cadaArea, cadaArea);
		}
		ddAreaOptions.setOptions(opAreas);
        
        this.habilitarBtnExportar();
    }
    
	protected SingleSelectOptionsList ddAreaOptions = new SingleSelectOptionsList();
	protected DropDown ddArea = new DropDown();
	
    public SingleSelectOptionsList getDdAreaOptions() {
		return ddAreaOptions;
	}

	public void setDdAreaOptions(SingleSelectOptionsList ddAreaOptions) {
		this.ddAreaOptions = ddAreaOptions;
	}

	public DropDown getDdArea() {
		return ddArea;
	}

	public void setDdArea(DropDown ddArea) {
		this.ddArea = ddArea;
	}

	private ObjectListDataProvider ldpDeposito = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpDeposito() {
        return ldpDeposito;
    }

    public void setLdpDeposito(ObjectListDataProvider pOldpDepositos) {
        this.ldpDeposito = pOldpDepositos;
    }
    
    private Label lblNombre = new Label();

    public Label getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(Label pLblNombre) {
        this.lblNombre = pLblNombre;
    }
    private Label lblArea = new Label();

    public Label getLblArea() {
        return lblArea;
    }

    public void setLblArea(Label pLblArea) {
        this.lblArea = pLblArea;
    }
 
    private StaticText stColArea = new StaticText();

    public StaticText getStColArea() {
        return stColArea;
    }

    public void setStColArea(StaticText pStColArea) {
        this.stColArea = pStColArea;
    }
    
    private TextField tfNombre = new TextField();

    public TextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(TextField pTfNombre) {
        this.tfNombre = pTfNombre;
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
    private StaticText staticText10 = new StaticText();
    
    public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText staticText10) {
		this.staticText10 = staticText10;
	}

	private StaticText staticText8 = new StaticText();

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText pStaticText8) {
        this.staticText8 = pStaticText8;
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
    
    /**
     * <p>Construir una instancia de bean de página.</p>
     */
    public AdminDeposito() {
    }
    
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
    	FiltroDeposito locFiltro = this.getFiltro();
    	locFiltro.setNombre(getTextFieldValue(this.getTfNombre()));
    	locFiltro.setArea(this.getDDObjectValue(getDdArea(), getCommunicationComprasBean().getMapaAreasDeposito()));
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
    	FiltroDeposito locFiltro = this.getFiltro();
    	
        this.getTfNombre().setText(locFiltro.getNombre());
    	if (locFiltro.getArea() != null) {
			this.getDdArea().setSelected(locFiltro.getArea().toString());
		}
    }

    @Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception { 
    	this.getCommunicationComprasBean().getRemoteSystemStock().setLlave(this.getSessionBean1().getLlave());
        return this.getCommunicationComprasBean().getRemoteSystemStock().findListaDeposito((FiltroDeposito)pFiltro);
    }
    
    @Override
    protected void limpiarObjetosUsados() {
        FiltroDeposito locFiltro = this.getFiltro();
        locFiltro.setArea(null);
        locFiltro.setNombre("");
        // CAMBIAR: Limpiar los textField y dropDown
        this.getTfNombre().setText("");
        this.getDdArea().setSelected("");
    }

    @Override
    public ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpDeposito();
    }

    @Override
    protected List getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationComprasBean().getListaDepositos();
    }

    @Override
    protected void setListaDelCommunication(List lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationComprasBean().setListaDepositos(lista);
    }

    public String btnSeleccionarArea_action() {
    	return navegarParaSeleccionar("AdminArea");
    }
    
    public String btnAgregar_action() {
    	return toAbm(new DepositoModel().new AgregarDepositoController());
    }

    public String btnModificar_action() {
    	return toAbm(new DepositoModel().new ModificarDepositoController());
    }

    public String btnEliminar_action() {
    	return toAbm(new DepositoModel().new EliminarDepositoController());
    }

    public String btnConsultar_action() {
    	return toAbm(new DepositoModel().new ConsultarDepositoController());
    }
    
    public String btnCheckearStock_action() {
    	return toAbm(new DepositoModel().new CheckearStockController());
    }

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Deposito deposito = (Deposito) pObject;
		this.getCommunicationComprasBean().getRemoteSystemStock().setLlave(this.getSessionBean1().getLlave());
		deposito = this.getCommunicationComprasBean().getRemoteSystemStock().getDepositoByID(deposito.getIdDeposito());
		return deposito;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroDeposito locFiltro = this.getFiltro();

		if (pObject instanceof Area) {
			Area locArea = (Area) pObject;
			locFiltro.setArea(locArea);
		}
	}
	
	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationComprasBean().getTablaDeposito();
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Depositos";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminDeposito";
	}
	
	@Override
	public long getSerialVersionUID() {
		return Deposito.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{inventario$ABMDeposito$AdminDeposito}";
	}
}