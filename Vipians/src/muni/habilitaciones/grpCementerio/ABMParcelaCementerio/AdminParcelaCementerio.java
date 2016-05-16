package muni.habilitaciones.grpCementerio.ABMParcelaCementerio;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.Concesion;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

public class AdminParcelaCementerio extends AdminPageBean{
	
	@Override
    protected void _init() throws Exception{   	
    	Option[] opTipoConcesion = null;
    	opTipoConcesion = this.getApplicationBean1().getMgrDropDown()
				.armarArrayOptionsList(Concesion.TipoConcesion.values(), "cap");
		ddTipoConcesionDefaultOptions.setOptions(opTipoConcesion);
    }
	
	private Label lblPersona = new Label();
	private Label lblTipoSepultura = new Label();
	private TextField tfPersona = new TextField();
	private TextField tfTipoSepultura = new TextField();
	private ObjectListDataProvider ldpParcelaCementerio = new ObjectListDataProvider();
	private Button btnSeleccionarDifunto = new Button();
	private HtmlAjaxCommandButton btnLimpiarDifunto = new HtmlAjaxCommandButton();
	private Button btnSeleccionarTipoSepultura = new Button();
	private HtmlAjaxCommandButton btnLimpiarTipoSepultura = new HtmlAjaxCommandButton();
	private PanelAtributoDinamico panelAtributoDinamico;
	private PanelAtributoDinamico panelAtributoDinamico2;
	private Label lblFechaInscripcion = new Label();
	private Label lblFechaFinalizacion = new Label();
	private Label lblTipoConcesion = new Label();
	private TextField tfFechaInscripcion = new TextField();
	private TextField tfFechaFinalizacion = new TextField();	
	private DropDown ddTipoConcesion = new DropDown();
	private SingleSelectOptionsList ddTipoConcesionDefaultOptions = new SingleSelectOptionsList();
	private TextField tfNumeroCuenta = new TextField();
	
	public TextField getTfNumeroCuenta() {
		return tfNumeroCuenta;
	}

	public void setTfNumeroCuenta(TextField tfNumeroCuenta) {
		this.tfNumeroCuenta = tfNumeroCuenta;
	}

	public HtmlAjaxCommandButton getBtnLimpiarDifunto() {
		return btnLimpiarDifunto;
	}

	public void setBtnLimpiarDifunto(HtmlAjaxCommandButton btnLimpiarDifunto) {
		this.btnLimpiarDifunto = btnLimpiarDifunto;
	}

	public PanelAtributoDinamico getPanelAtributoDinamico2() {
		return panelAtributoDinamico2;
	}

	public void setPanelAtributoDinamico2(PanelAtributoDinamico panelAtributoDinamico2) {
		this.panelAtributoDinamico2 = panelAtributoDinamico2;
	}

	public Label getLblFechaInscripcion() {
		return lblFechaInscripcion;
	}

	public void setLblFechaInscripcion(Label lblFechaInscripcion) {
		this.lblFechaInscripcion = lblFechaInscripcion;
	}

	public Label getLblFechaFinalizacion() {
		return lblFechaFinalizacion;
	}

	public void setLblFechaFinalizacion(Label lblFechaFinalizacion) {
		this.lblFechaFinalizacion = lblFechaFinalizacion;
	}

	public Label getLblTipoConcesion() {
		return lblTipoConcesion;
	}

	public void setLblTipoConcesion(Label lblTipoConcesion) {
		this.lblTipoConcesion = lblTipoConcesion;
	}

	public TextField getTfFechaInscripcion() {
		return tfFechaInscripcion;
	}

	public void setTfFechaInscripcion(TextField tfFechaInscripcion) {
		this.tfFechaInscripcion = tfFechaInscripcion;
	}

	public TextField getTfFechaFinalizacion() {
		return tfFechaFinalizacion;
	}

	public void setTfFechaFinalizacion(TextField tfFechaFinalizacion) {
		this.tfFechaFinalizacion = tfFechaFinalizacion;
	}

	public DropDown getDdTipoConcesion() {
		return ddTipoConcesion;
	}

	public void setDdTipoConcesion(DropDown ddTipoConcesion) {
		this.ddTipoConcesion = ddTipoConcesion;
	}

	public SingleSelectOptionsList getDdTipoConcesionDefaultOptions() {
		return ddTipoConcesionDefaultOptions;
	}

	public void setDdTipoConcesionDefaultOptions(SingleSelectOptionsList ddTipoConcesionDefaultOptions) {
		this.ddTipoConcesionDefaultOptions = ddTipoConcesionDefaultOptions;
	}

	public HtmlAjaxCommandButton getBtnLimpiarTipoSepultura() {
		return btnLimpiarTipoSepultura;
	}

	public void setBtnLimpiarTipoSepultura(HtmlAjaxCommandButton btnLimpiarTipoSepultura) {
		this.btnLimpiarTipoSepultura = btnLimpiarTipoSepultura;
	}

	public PanelAtributoDinamico getPanelAtributoDinamico() {
        return panelAtributoDinamico;
    }

    public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
        this.panelAtributoDinamico = panelAtributoDinamico;
    }

	public Button getBtnSeleccionarDifunto() {
		return btnSeleccionarDifunto;
	}

	public void setBtnSeleccionarDifunto(Button btnSeleccionarDifunto) {
		this.btnSeleccionarDifunto = btnSeleccionarDifunto;
	}

	public Button getBtnSeleccionarTipoSepultura() {
		return btnSeleccionarTipoSepultura;
	}

	public void setBtnSeleccionarTipoSepultura(Button btnSeleccionarTipoSepultura) {
		this.btnSeleccionarTipoSepultura = btnSeleccionarTipoSepultura;
	}

	public ObjectListDataProvider getLdpParcelaCementerio() {
		return ldpParcelaCementerio;
	}

	public void setLdpParcelaCementerio(ObjectListDataProvider ldpParcelaCementerio) {
		this.ldpParcelaCementerio = ldpParcelaCementerio;
	}
	
	public Label getLblPersona() {
		return lblPersona;
	}

	public void setLblPersona(Label lblPersona) {
		this.lblPersona = lblPersona;
	}

	public Label getLblTipoSepultura() {
		return lblTipoSepultura;
	}

	public void setLblTipoSepultura(Label lblTipoSepultura) {
		this.lblTipoSepultura = lblTipoSepultura;
	}

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tfPersona) {
		this.tfPersona = tfPersona;
	}

	public TextField getTfTipoSepultura() {
		return tfTipoSepultura;
	}

	public void setTfTipoSepultura(TextField tfTipoSepultura) {
		this.tfTipoSepultura = tfTipoSepultura;
	}

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button btnSeleccionarPersonaFisica) {
		this.btnSeleccionarPersonaFisica = btnSeleccionarPersonaFisica;
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpParcelaCementerio();
	}

	@Override
	protected List<ParcelaCementerio> getListaDelCommunication() {
		return this.getCommunicationHabilitacionesBean().getListaParcelaCementerio();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationHabilitacionesBean().setListaParcelaCementerio(lista);
	}

	@Override
	protected void limpiarObjetosUsados() {
		this.getTfPersona().setText("");
		this.getTfTipoSepultura().setText("");
		this.getTfPersonaSeleccionada().setText("");
		this.getTfFechaInscripcion().setText("");
		this.getTfFechaFinalizacion().setText("");
		this.getDdTipoConcesion().setSelected(null);
		this.getTfNumeroCuenta().setText(null);
		
		FiltroParcelaCementerio locFiltro = (FiltroParcelaCementerio) this.getFiltro();
		locFiltro.setPersonaDifunto(null);
		locFiltro.setTipoSepultura(null);
		locFiltro.setTitular(null);
		locFiltro.setTipoConcesion(null);
		locFiltro.setFechaInscripcion(null);
		locFiltro.setFechaFinalizacion(null);
		locFiltro.setNumeroCuenta(null);
		
		this.panelAtributoDinamico.limpiarCampos();
		
		this.getSessionBean1().getListaIdPersonas().clear();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		ParcelaCementerio locParcelaCementerio = (ParcelaCementerio) pObject;
        this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().setLlave(this.getSessionBean1().getLlave());
        locParcelaCementerio = getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().getParcelaCementerioById(locParcelaCementerio.getIdAsocRegAlicuota());
        return locParcelaCementerio;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().setLlave(this.getSessionBean1().getLlave());
        return this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().findListaParcelaCementerio((FiltroParcelaCementerio)pFiltro);
	}

	@Override
	protected void guardarEstadoObjetosUsados() {		
		FiltroParcelaCementerio locFiltro = this.getFiltro();
		
		borrarListIdAuxPersonas(this.getTfPersonaSeleccionada(), locFiltro.getTitular());
		locFiltro.setListaIdPersonas(this.getSessionBean1().getListaIdPersonas());
		
		locFiltro.setFechaInscripcion(getTextFieldValueDate(this.getTfFechaInscripcion()));
		locFiltro.setFechaFinalizacion(getTextFieldValueDate(this.getTfFechaFinalizacion()));
		locFiltro.setTipoConcesion(getDDEnumValue(this.getDdTipoConcesion(), Concesion.TipoConcesion.class));
		
		if(locFiltro.getListaAtributoDinamico() != null){
	        locFiltro.setListaAtributoDinamico((ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltro.getListaAtributoDinamico()));
		}
//		if(locFiltro.getListaAtributoDinamico2() != null){
//	        locFiltro.setListaAtributoDinamico2((ArrayList) panelAtributoDinamico2.obtenerListaAtributosDinamicos(locFiltro.getListaAtributoDinamico2()));
//		}
		locFiltro.setNumeroCuenta(this.getTextFieldValueInteger(tfNumeroCuenta));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {		
		FiltroParcelaCementerio locFiltro = this.getFiltro();
		
		panelAtributoDinamico = new PanelAtributoDinamico(locFiltro.getListaAtributoDinamico(), "#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio}", "parcelaCementerio");
        panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltro.getListaAtributoDinamico());
        panelAtributoDinamico2 = new PanelAtributoDinamico(locFiltro.getListaAtributoDinamico2(), "#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio}", "Concesion");
        panelAtributoDinamico2.establecerListaAtributosDinamicos(locFiltro.getListaAtributoDinamico2());
        
		this.getTfPersona().setText(locFiltro.getPersonaDifunto());
		this.getTfTipoSepultura().setText(locFiltro.getTipoSepultura());
		this.getTfPersonaSeleccionada().setText(locFiltro.getTitular());
		if(locFiltro.getFechaInscripcion() != null) {
			this.getTfFechaInscripcion().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaInscripcion()));
		}
		if(locFiltro.getFechaFinalizacion() != null) {
			this.getTfFechaFinalizacion().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaFinalizacion()));
		}
		if(locFiltro.getTipoConcesion() != null) {
			this.getDdTipoConcesion().setSelected(locFiltro.getTipoConcesion().toString().toUpperCase());
		}
		this.setTextFieldValueInteger(tfNumeroCuenta, locFiltro.getNumeroCuenta());
	}

	@Override
	protected String getNombrePagina() {
		return "Administración de Parcelas Cementerio";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminParcelaCementerio";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Integer(0));// Nro
		
		FiltroParcelaCementerio locFiltro = getFiltro();
		List atributosDinamicos = null;
		List atributosDinamicos2 = null;
        try {
        	atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(ParcelaCementerio.serialVersionUID, null, true); 
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        try {
        	atributosDinamicos2 = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(Concesion.serialVersionUID, null, true); 
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        locFiltro.setListaAtributoDinamico(atributosDinamicos);
        locFiltro.setListaAtributoDinamico2(atributosDinamicos2);
        
		return ep;
	}

	public String btnSeleccionarPersonaFisica_action() {
		this.getElementoPila().getObjetos().set(0, 2);
		return navegarParaSeleccionar("AdminPersonaFisica");
    }
	
	public String btnSeleccionarDifunto_action() {
		this.getElementoPila().getObjetos().set(0, 1);
		return navegarParaSeleccionar("AdminPersonaFisica");
    }
	
	public String btnLimpiarDifunto_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
        	this.getTfPersona().setText("");
        	((FiltroParcelaCementerio)this.getFiltro()).setPersonaDifunto(null);
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
	
	public String btnLimpiarPersona_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
        	this.getTfPersonaSeleccionada().setText("");
        	((FiltroParcelaCementerio)this.getFiltro()).setTitular(null);
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
	
	public String btnSeleccionarTipoSepultura_action() {
        return navegarParaSeleccionar("AdminTipoSepultura");
    }
	
	public String btnLimpiarTipoSepultura_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();
        
        if (ultimo) {
        	this.getTfTipoSepultura().setText("");
        	((FiltroParcelaCementerio)this.getFiltro()).setTipoSepultura(null);
            this.guardarEstadoObjetosUsados();
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
	
	@Override
    public PaginatedTable getPaginatedTable() {
        return this.getCommunicationHabilitacionesBean().getTablaParcelaCementerio();
    }
	
	public String btnAgregar_action() {
        return toAbm(new ParcelaCementerioModel().new AgregarParcelaCementerioController());
    }
	
	public String btnModificar_action() {
		return toAbm(new ParcelaCementerioModel().new ModificarParcelaCementerioController());
    }
	
	public String btnEliminar_action() {
		return toAbm(new ParcelaCementerioModel().new EliminarParcelaCementerioController());
    }
	
	public String btnConsultar_action() {
		return toAbm(new ParcelaCementerioModel().new ConsultarParcelaCementerioController());
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroParcelaCementerio locFiltro = this.getFiltro();
				
		if (pObject instanceof Persona) {
			Persona locPersona = (Persona) pObject;
			int posicion = this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class).intValue();
			if (posicion == 1) {
				locFiltro.setPersonaDifunto((PersonaFisica) locPersona);
			}
			if (posicion == 2) {
				locFiltro.setTitular(locPersona);
			}
		}
		
		if(pObject instanceof TipoSepultura){
			TipoSepultura locTipoSepultura = (TipoSepultura) pObject;
			locFiltro.setTipoSepultura(locTipoSepultura);
		}
	}
	
	@Override
	public long getSerialVersionUID() {
		return ParcelaCementerio.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpCementerio$ABMParcelaCementerio$AdminParcelaCementerio}";
	}
	
	public void setPersonaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroParcelaCementerio locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Persona propietario = null;
		
		try {
			propietario = (Persona) this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}
		
		locFiltro.setPersonaDifunto((PersonaFisica) propietario);
		this.getSessionBean1().setPersonaSeleccionada(propietario);
	}
}
