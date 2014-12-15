
package muni.habilitaciones.grpAutomotor.ABMValuacionAcara;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

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
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroValuacionAcara;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.ValuacionAcara;
import com.trascender.habilitaciones.recurso.persistent.transito.ValuacionAcara.Moneda;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

public class AdminValuacionAcara extends AdminPageBean {

	protected void _init() throws Exception {
		Option[] opMoneda = null;
		opMoneda = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(ValuacionAcara.Moneda.values(), "may");
		ddMonedaDefaultOptions.setOptions(opMoneda);
	}

	private ObjectListDataProvider ldpValuacionAcara = new ObjectListDataProvider();

	private Label lblAnio = new Label();
	private Label lblValor = new Label();
	private Label lblMoneda = new Label();
	private Label lblModelo = new Label();

	private TextField tfAnio = new TextField();
	private TextField tfValor = new TextField();
	private TextField tfModelo = new TextField();
	
	DropDown ddMoneda = new DropDown();
	private SingleSelectOptionsList ddMonedaDefaultOptions = new SingleSelectOptionsList();

	private TableColumn tcModelo = new TableColumn();
	private TableColumn tcValor = new TableColumn();
	private TableColumn tcMoneda = new TableColumn();
	private TableColumn tcAnio = new TableColumn();

	private StaticText stModelo = new StaticText();
	private StaticText stValor = new StaticText();
	private StaticText stMoneda = new StaticText();
	private StaticText stAnio = new StaticText();

	private HtmlAjaxCommandButton btnLimpiarModelo = new HtmlAjaxCommandButton();
	private Button btnSeleccionarModelo = new Button();
	private Button btnProcesarArchivoAcara = new Button();
	
	public TextField getTfModelo() {
		return tfModelo;
	}

	public void setTfModelo(TextField tfModelo) {
		this.tfModelo = tfModelo;
	}

	private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	public HtmlAjaxCommandButton getBtnLimpiarModelo() {
		return btnLimpiarModelo;
	}

	public void setBtnLimpiarModelo(HtmlAjaxCommandButton btnLimpiarModelo) {
		this.btnLimpiarModelo = btnLimpiarModelo;
	}

	public Button getBtnProcesarArchivoAcara() {
		return btnProcesarArchivoAcara;
	}

	public void setBtnProcesarArchivoAcara(Button btnProcesarArchivoAcara) {
		this.btnProcesarArchivoAcara = btnProcesarArchivoAcara;
	}

	public Button getBtnSeleccionarModelo() {
		return btnSeleccionarModelo;
	}

	public void setBtnSeleccionarModelo(Button btnSeleccionarModelo) {
		this.btnSeleccionarModelo = btnSeleccionarModelo;
	}

	public Label getLblMoneda() {
		return lblMoneda;
	}

	public void setLblMoneda(Label lblMoneda) {
		this.lblMoneda = lblMoneda;
	}

	public Label getLblModelo() {
		return lblModelo;
	}

	public void setLblModelo(Label lblModelo) {
		this.lblModelo = lblModelo;
	}

	public SingleSelectOptionsList getDdMonedaDefaultOptions() {
		return ddMonedaDefaultOptions;
	}

	public void setDdMonedaDefaultOptions(SingleSelectOptionsList ddMonedaDefaultOptions) {
		this.ddMonedaDefaultOptions = ddMonedaDefaultOptions;
	}

	public ObjectListDataProvider getLdpValuacionAcara() {
		return ldpValuacionAcara;
	}

	public void setLdpValuacionAcara(ObjectListDataProvider ldpValuacionAcara) {
		this.ldpValuacionAcara = ldpValuacionAcara;
	}

	public Label getLblAnio() {
		return lblAnio;
	}

	public void setLblAnio(Label lblAnio) {
		this.lblAnio = lblAnio;
	}

	public Label getLblValor() {
		return lblValor;
	}

	public void setLblValor(Label lblValor) {
		this.lblValor = lblValor;
	}

	public TextField getTfAnio() {
		return tfAnio;
	}

	public void setTfAnio(TextField tfAnio) {
		this.tfAnio = tfAnio;
	}

	public TextField getTfValor() {
		return tfValor;
	}

	public void setTfValor(TextField tfValor) {
		this.tfValor = tfValor;
	}

	public DropDown getDdMoneda() {
		return ddMoneda;
	}

	public void setDdMoneda(DropDown ddMoneda) {
		this.ddMoneda = ddMoneda;
	}

	public TableColumn getTcModelo() {
		return tcModelo;
	}

	public void setTcModelo(TableColumn tcModelo) {
		this.tcModelo = tcModelo;
	}

	public TableColumn getTcValor() {
		return tcValor;
	}

	public void setTcValor(TableColumn tcValor) {
		this.tcValor = tcValor;
	}

	public TableColumn getTcMoneda() {
		return tcMoneda;
	}

	public void setTcMoneda(TableColumn tcMoneda) {
		this.tcMoneda = tcMoneda;
	}

	public TableColumn getTcAnio() {
		return tcAnio;
	}

	public void setTcAnio(TableColumn tcAnio) {
		this.tcAnio = tcAnio;
	}

	public StaticText getStModelo() {
		return stModelo;
	}

	public void setStModelo(StaticText stModelo) {
		this.stModelo = stModelo;
	}

	public StaticText getStValor() {
		return stValor;
	}

	public void setStValor(StaticText stValor) {
		this.stValor = stValor;
	}

	public StaticText getStMoneda() {
		return stMoneda;
	}

	public void setStMoneda(StaticText stMoneda) {
		this.stMoneda = stMoneda;
	}

	public StaticText getStAnio() {
		return stAnio;
	}

	public void setStAnio(StaticText stAnio) {
		this.stAnio = stAnio;
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpValuacionAcara();
	}

	@Override
	protected List getListaDelCommunication() {
		return getCommunicationHabilitacionesBean().getListaValuacionAcara();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		getCommunicationHabilitacionesBean().setListaValuacionAcara(lista);
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return getCommunicationHabilitacionesBean().getTablePhaseListenerValuacionAcara();
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroValuacionAcara locFiltro = this.getFiltro();
		locFiltro.setAnio(null);
		locFiltro.setModelo(null);
		locFiltro.setMoneda(null);
		locFiltro.setValor(null);

		this.getTfAnio().setText("");
		this.getTfValor().setText("");
		this.getTfModelo().setText("");
		this.getDdMoneda().setSelected("");
		panelAtributoDinamico.limpiarCampos();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		ValuacionAcara locValuacionAcara = (ValuacionAcara) pObject;
		this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(this.getSessionBean1().getLlave());
		locValuacionAcara = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().getValuacionAcaraById(locValuacionAcara.getIdValuacionAcara());
		return locValuacionAcara;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {

		FiltroValuacionAcara locFiltroValuacionAcara = this.getFiltro();

		locFiltroValuacionAcara.setAnio(getTextFieldValueInteger(this.getTfAnio()));
		locFiltroValuacionAcara.setValor(getTextFieldValueDouble(this.getTfValor()));
		locFiltroValuacionAcara.setMoneda(getDDEnumValue(this.getDdMoneda(), Moneda.class));

		if(locFiltroValuacionAcara.getListaAtributoDinamico() != null) {
			locFiltroValuacionAcara.setListaAtributoDinamico((ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltroValuacionAcara.getListaAtributoDinamico()));
		}
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {

		FiltroValuacionAcara locFiltroValuacionAcara = this.getFiltro();

		if(locFiltroValuacionAcara.getAnio() != null) {
			this.getTfAnio().setText(locFiltroValuacionAcara.getAnio());
		}
		if(locFiltroValuacionAcara.getValor() != null) {
			this.getTfValor().setText(locFiltroValuacionAcara.getValor());
		}
		if(locFiltroValuacionAcara.getMoneda() != null) {
			this.getDdMoneda().setSelected(locFiltroValuacionAcara.getMoneda().toString());
		}
		if(locFiltroValuacionAcara.getModelo() != null) {
			this.getTfModelo().setText(locFiltroValuacionAcara.getModelo());
		}
		panelAtributoDinamico = new PanelAtributoDinamico(locFiltroValuacionAcara.getListaAtributoDinamico(), "#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltroValuacionAcara.getListaAtributoDinamico());
	}

	@Override
	protected String getNombrePagina() {
		return "Administración Valuación Acara";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminValuacionAcara";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		FiltroValuacionAcara locFiltro = getFiltro();
		List atributosDinamicos = null;
		try {
			this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(ValuacionAcara.serialVersionUID, null, true);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		locFiltro.setListaAtributoDinamico(atributosDinamicos);
		return ep;
	}

	public String btnAgregar_action() {
		return toAbm(new ValuacionAcaraModel().new AgregarController());
	}

	public String btnModificar_action() {
		ValuacionAcara locValuacion = (ValuacionAcara) this.getObjetoSeleccionado();
		if(locValuacion != null && locValuacion.getFechaBaja() != null) {
			warn("No se puede modificar una Valuaci\363n dada de baja");
			return null;
		}
		return toAbm(new ValuacionAcaraModel().new ModificarController());
	}

	public String btnEliminar_action() {
		ValuacionAcara locValuacion = (ValuacionAcara) this.getObjetoSeleccionado();
		if(locValuacion != null && locValuacion.getFechaBaja() != null) {
			warn("La Valuaci\363n seleccionada ya se encuentra dada de baja");
			return null;
		}
		return toAbm(new ValuacionAcaraModel().new EliminarController());
	}

	public String btnConsultar_action() {
		return toAbm(new ValuacionAcaraModel().new ConsultarController());
	}

	public String btnProcesarArchivoAcara_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setAbmController(new ProcesarArchivoAcaraModel().new AgregarController());

			retorno = "ABMProcesarArchivoAcara";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		FiltroValuacionAcara locFiltroValuacionAcara = (FiltroValuacionAcara) pFiltro;
		return this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().findListaValuacionesAcara(locFiltroValuacionAcara);
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationHabilitacionesBean().getTablaValuacionAcara();
	}

	public String btnSeleccionarModelo_action() {
		return navegarParaSeleccionar("AdminModelo");
	}

	public String btnLimpiarModelo_action() {
		String retorno = null;

		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(this.getTfModelo());
			FiltroValuacionAcara locFiltro = this.getFiltro();
			locFiltro.setModelo(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroValuacionAcara locFiltro = this.getFiltro();
		if(pObject instanceof Modelo) {
			Modelo locModelo = (Modelo) pObject;
			locFiltro.setModelo(locModelo);
		}
	}

	@Override
	public long getSerialVersionUID() {
		return ValuacionAcara.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara}";
	}
	
	public void setModeloVehiculoAutocompletar(String pId, String pIdSubSesion) throws Exception { // aunque no se usa el ID de subsession
		FiltroValuacionAcara locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Modelo modelo = null;

		try {
			modelo = (Modelo) this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().getModeloById(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		locFiltro.setModelo(modelo);
	}
}