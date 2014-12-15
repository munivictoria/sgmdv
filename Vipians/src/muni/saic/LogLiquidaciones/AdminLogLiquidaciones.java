
package muni.saic.LogLiquidaciones;

import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.saic.recurso.filtros.FiltroLogLiquidacion;
import com.trascender.saic.recurso.persistent.LogLiquidacion;

public class AdminLogLiquidaciones extends AdminPageBean {

	private StaticText staticText1 = new StaticText();
	private Label lbTipoObligacion = new Label();
	private Label lbEvento = new Label();
	private Label lbUsuario = new Label();
	private Label lbAnio = new Label();
	private Label lbNroPeriodo = new Label();
	private Label lbNroCuota = new Label();
	private Label lbFechaDesde = new Label();
	private Label lbFechaHasta = new Label();
	private DropDown ddTipoObligacion = new DropDown();
	private SingleSelectOptionsList ddTipoObligacionOptions = new SingleSelectOptionsList();
	private DropDown ddEvento = new DropDown();
	private SingleSelectOptionsList ddEventoOptions = new SingleSelectOptionsList();
	private TextField tfUsuario = new TextField();
	private Button btnSeleccionarUsuario = new Button();
	private HtmlAjaxCommandButton btnLimpiarUsuario = new HtmlAjaxCommandButton();
	private TextField tfAnio = new TextField();
	private TextField tfNroPeriodo = new TextField();
	private TextField tfNroCuota = new TextField();
	private TextField tfFechaDesde = new TextField();
	private TextField tfFechaHasta = new TextField();
	private ObjectListDataProvider ldpLogLiquidaciones = new ObjectListDataProvider();

	@Override
	protected void _init() throws Exception {
		Option[] op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().keySet().toArray(), "");
		ddTipoObligacionOptions.setOptions(op);
		
		Option[] opTipoEvento = null;
		opTipoEvento = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(LogLiquidacion.Evento.values(), "cap");
		ddEventoOptions.setOptions(opTipoEvento);
	}

	public ObjectListDataProvider getLdpLogLiquidaciones() {
		return ldpLogLiquidaciones;
	}

	public void setLdpLogLiquidaciones(ObjectListDataProvider ldpLogLiquidaciones) {
		this.ldpLogLiquidaciones = ldpLogLiquidaciones;
	}

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	public Label getLbTipoObligacion() {
		return lbTipoObligacion;
	}

	public void setLbTipoObligacion(Label lbTipoObligacion) {
		this.lbTipoObligacion = lbTipoObligacion;
	}

	public Label getLbEvento() {
		return lbEvento;
	}

	public void setLbEvento(Label lbEvento) {
		this.lbEvento = lbEvento;
	}

	public Label getLbUsuario() {
		return lbUsuario;
	}

	public void setLbUsuario(Label lbUsuario) {
		this.lbUsuario = lbUsuario;
	}

	public Label getLbAnio() {
		return lbAnio;
	}

	public void setLbAnio(Label lbAnio) {
		this.lbAnio = lbAnio;
	}

	public Label getLbNroPeriodo() {
		return lbNroPeriodo;
	}

	public void setLbNroPeriodo(Label lbNroPeriodo) {
		this.lbNroPeriodo = lbNroPeriodo;
	}

	public Label getLbNroCuota() {
		return lbNroCuota;
	}

	public void setLbNroCuota(Label lbNroCuota) {
		this.lbNroCuota = lbNroCuota;
	}

	public Label getLbFechaDesde() {
		return lbFechaDesde;
	}

	public void setLbFechaDesde(Label lbFechaDesde) {
		this.lbFechaDesde = lbFechaDesde;
	}

	public Label getLbFechaHasta() {
		return lbFechaHasta;
	}

	public void setLbFechaHasta(Label lbFechaHasta) {
		this.lbFechaHasta = lbFechaHasta;
	}

	public DropDown getDdTipoObligacion() {
		return ddTipoObligacion;
	}

	public void setDdTipoObligacion(DropDown ddTipoObligacion) {
		this.ddTipoObligacion = ddTipoObligacion;
	}

	public SingleSelectOptionsList getDdTipoObligacionOptions() {
		return ddTipoObligacionOptions;
	}

	public void setDdTipoObligacionOptions(SingleSelectOptionsList ddTipoObligacionOptions) {
		this.ddTipoObligacionOptions = ddTipoObligacionOptions;
	}

	public DropDown getDdEvento() {
		return ddEvento;
	}

	public void setDdEvento(DropDown ddEvento) {
		this.ddEvento = ddEvento;
	}

	public SingleSelectOptionsList getDdEventoOptions() {
		return ddEventoOptions;
	}

	public void setDdEventoOptions(SingleSelectOptionsList ddEventoOptions) {
		this.ddEventoOptions = ddEventoOptions;
	}

	public TextField getTfUsuario() {
		return tfUsuario;
	}

	public void setTfUsuario(TextField tfUsuario) {
		this.tfUsuario = tfUsuario;
	}

	public Button getBtnSeleccionarUsuario() {
		return btnSeleccionarUsuario;
	}

	public void setBtnSeleccionarUsuario(Button btnSeleccionarUsuario) {
		this.btnSeleccionarUsuario = btnSeleccionarUsuario;
	}

	public HtmlAjaxCommandButton getBtnLimpiarUsuario() {
		return btnLimpiarUsuario;
	}

	public void setBtnLimpiarUsuario(HtmlAjaxCommandButton btnLimpiarUsuario) {
		this.btnLimpiarUsuario = btnLimpiarUsuario;
	}

	public TextField getTfAnio() {
		return tfAnio;
	}

	public void setTfAnio(TextField tfAnio) {
		this.tfAnio = tfAnio;
	}

	public TextField getTfNroPeriodo() {
		return tfNroPeriodo;
	}

	public void setTfNroPeriodo(TextField tfNroPeriodo) {
		this.tfNroPeriodo = tfNroPeriodo;
	}

	public TextField getTfNroCuota() {
		return tfNroCuota;
	}

	public void setTfNroCuota(TextField tfNroCuota) {
		this.tfNroCuota = tfNroCuota;
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

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpLogLiquidaciones();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationSAICBean().getListaLogLiquidaciones();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationSAICBean().setListaLogLiquidaciones(lista);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroLogLiquidacion locFiltro = this.getFiltro();

		locFiltro.setTipoObligacion(null);
		locFiltro.setEvento(null);
		locFiltro.setUsuario(null);
		locFiltro.setAnio(null);
		locFiltro.setNroPeriodo(null);
		locFiltro.setNroCuota(null);
		locFiltro.setFechaDesde(null);
		locFiltro.setFechaHasta(null);
		
		this.getDdTipoObligacion().setSelected(null);
		this.getDdEvento().setSelected(null);
		this.getTfUsuario().setText("");
		this.getTfAnio().setText("");
		this.getTfNroPeriodo().setText("");
		this.getTfNroCuota().setText("");
		this.getTfFechaDesde().setText("");
		this.getTfFechaHasta().setText("");
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		LogLiquidacion locLogLiquidacion = (LogLiquidacion) pObject;
		getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(getSessionBean1().getLlave());
		locLogLiquidacion = getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().getLogLiquidacionesPorId(locLogLiquidacion.getIdLogLiquidacion());
		return locLogLiquidacion;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(getSessionBean1().getLlave());
		return getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().findListaLogLiquidacion((FiltroLogLiquidacion) pFiltro);
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroLogLiquidacion locFiltro = this.getFiltro();

		locFiltro.setTipoObligacion(getDDObjectValue(this.getDdTipoObligacion(), this.getCommunicationHabilitacionesBean().getMapaTipoObligacion()));
		locFiltro.setEvento(getDDEnumValue(this.getDdEvento(), LogLiquidacion.Evento.class));
		locFiltro.setAnio(getTextFieldValueInteger(this.getTfAnio()));
		locFiltro.setNroPeriodo(getTextFieldValueInteger(this.getTfNroPeriodo()));
		locFiltro.setNroCuota(getTextFieldValueInteger(this.getTfNroCuota()));
		locFiltro.setFechaDesde(getTextFieldValueDate(this.getTfFechaDesde()));
		locFiltro.setFechaHasta(getTextFieldValueDate(this.getTfFechaHasta()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroLogLiquidacion locFiltro = this.getFiltro();

		this.getDdTipoObligacion().setSelected(locFiltro.getTipoObligacion());
		this.getDdEvento().setSelected(locFiltro.getEvento());
		if(locFiltro.getUsuario() != null && locFiltro.getUsuario().length() > 0) {
			this.getTfUsuario().setText(locFiltro.getUsuario());
		}
		if(locFiltro.getAnio() != null) {
			this.getTfAnio().setText(locFiltro.getAnio().toString());
		}
		if(locFiltro.getNroPeriodo() != null) {
			this.getTfNroPeriodo().setText(locFiltro.getNroPeriodo().toString());
		}
		if(locFiltro.getNroCuota() != null) {
			this.getTfNroCuota().setText(locFiltro.getNroCuota().toString());
		}
		if(locFiltro.getFechaDesde() != null) {
			this.getTfFechaDesde().setText(locFiltro.getFechaDesde().toString());
		}
		if(locFiltro.getFechaHasta() != null) {
			this.getTfFechaHasta().setText(locFiltro.getFechaHasta().toString());
		}
	}

	public String btnSeleccionarUsuario_action() {
		return navegarParaSeleccionar("AdminUsuario");
	}

	public String btnLimpiarUsuario_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.limpiarObjeto(getTfUsuario());
			FiltroLogLiquidacion locFiltro = getFiltro();
			locFiltro.setUsuario("");

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		this.getCommunicationSAICBean().getTablaLogLiquidaciones().getTcSeleccion().setRendered(false);
		return this.getCommunicationSAICBean().getTablaLogLiquidaciones();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof Usuario) {
			Usuario locUsuario = (Usuario) pObject;
			FiltroLogLiquidacion locFiltro = this.getFiltro();

			locFiltro.setUsuario(locUsuario.getUser());
		}
	}
	
	@Override
	protected String getNombrePagina() {
		return "Log Liquidaciones";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminLogLiquidaciones";
	}

	@Override
	public String getNombreBean() {
		return "#{saic$LogLiquidaciones$AdminLogLiquidaciones}";
	}

	@Override
	public long getSerialVersionUID() {
		return LogLiquidacion.serialVersionUID;
	}

}