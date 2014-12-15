/*
 * AdminLicitacion.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */
package muni.compras.ABMLicitacion;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import javax.faces.convert.DateTimeConverter;
import javax.faces.event.ActionEvent;

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
import com.trascender.compras.recurso.filtros.FiltroContratacion;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminLicitacion extends AdminPageBean {

	private StaticText stEstado = new StaticText();
	private StaticText stNumero = new StaticText();
	private StaticText stObjeto = new StaticText();
	private StaticText stFechaPublicacion = new StaticText();
	private StaticText stFechaCierre = new StaticText();
	private TableColumn tcNumero = new TableColumn();
	private TableColumn tcObjeto = new TableColumn();
	private TableColumn tcFechaPublicacion = new TableColumn();
	private TableColumn tcFechaCierre = new TableColumn();
	private TableColumn tcEstado = new TableColumn();
	private Label lblNroLicitacion = new Label();
	private Label lblObjeto = new Label();
	private Label lblTipoLicitacion = new Label();
	private Label lblEstadoLicitacion = new Label();
	private Label lblFechaPublicacion = new Label();
	private Label lblFechaAdjudicacion = new Label();
	private Label lblFechaOferta = new Label();
	private Label lblFormatoFechaPublicacion = new Label();
	private Label lblFormatoFechaAdjudicacion = new Label();
	private Label lblFormatoFechaOferta = new Label();
	private Label lblSecretaria = new Label();
	private Label lblArea = new Label();
	private TextField tfNroLicitacion = new TextField();
	private TextField tfFechaPublicacion = new TextField();
	private TextField tfFechaAdjudicacion = new TextField();
	private TextField tfFechaOferta = new TextField();
	private TextField tfObjeto = new TextField();
	private DropDown ddTipoLicitacion = new DropDown();
	private DropDown ddEstadoLicitacion = new DropDown();
	private DropDown ddSecretaria = new DropDown();
	private DropDown ddArea = new DropDown();
	private SingleSelectOptionsList ddEstadoLicitacionDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddTipoLicitacionDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddSecretariaOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddAreaOptions = new SingleSelectOptionsList();
	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();
	private ObjectListDataProvider ldpLicitaciones = new ObjectListDataProvider();

	private Label lblBien = new Label();
	private TextField tfBien = new TextField();
	private Button btnSeleccionarBien = new Button();
	private HtmlAjaxCommandButton btnLimpiarBien = new HtmlAjaxCommandButton();
	
	public HtmlAjaxCommandButton getBtnLimpiarBien() {
		return btnLimpiarBien;
	}

	public void setBtnLimpiarBien(HtmlAjaxCommandButton btnLimpiarBien) {
		this.btnLimpiarBien = btnLimpiarBien;
	}

	public Label getLblBien() {
		return lblBien;
	}

	public void setLblBien(Label lblBien) {
		this.lblBien = lblBien;
	}

	public TextField getTfBien() {
		return tfBien;
	}

	public void setTfBien(TextField tfBien) {
		this.tfBien = tfBien;
	}

	public Button getBtnSeleccionarBien() {
		return btnSeleccionarBien;
	}

	public void setBtnSeleccionarBien(Button btnSeleccionarBien) {
		this.btnSeleccionarBien = btnSeleccionarBien;
	}

	@Override
	protected void _init() throws Exception {

		Option[] opTipo = null;
		opTipo = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Contratacion.Tipo.values(), "may");
		ddTipoLicitacionDefaultOptions.setOptions(opTipo);

		// Option[] opTipoContratacion = null;
		// opTipoContratacion =
		// this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Licitacion.TipoContratacion.values(),
		// "may");
		// ddTipoContratacionDefaultOptions.setOptions(opTipoContratacion);
		Option[] opEstado = null;
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Contratacion.Estado.values(), "may");
		ddEstadoLicitacionDefaultOptions.setOptions(opEstado);

		dateTimeConverter1.setTimeZone(null);
		dateTimeConverter1.setPattern("dd/MM/yy");
		dateTimeConverter1.setTimeStyle("full");

		Set<String> locListaSecretarias = this.getCommunicationComprasBean().getMapaSecretariasArea().keySet();
		Option[] opSecretarias = new Option[locListaSecretarias.size() + 1];
		int i = 0;
		opSecretarias[i++] = new Option("", "");
		for (String cadaSecretaria : locListaSecretarias) {
			opSecretarias[i++] = new Option(cadaSecretaria, cadaSecretaria);
		}
		this.ddSecretariaOptions.setOptions(opSecretarias);

		Set<String> locListaAreas = this.getCommunicationComprasBean().getMapaAreasSolSum().keySet();
		Option[] opAreas = new Option[locListaAreas.size() + 1];
		i = 0;
		opAreas[i++] = new Option("", "");
		for (String cadaArea : locListaAreas) {
			opAreas[i++] = new Option(cadaArea, cadaArea);
		}
		ddAreaOptions.setOptions(opAreas);
	}

	public Label getLblSecretaria() {
		return lblSecretaria;
	}

	public void setLblSecretaria(Label lblSecretaria) {
		this.lblSecretaria = lblSecretaria;
	}

	public Label getLblArea() {
		return lblArea;
	}

	public void setLblArea(Label lblArea) {
		this.lblArea = lblArea;
	}

	public DropDown getDdSecretaria() {
		return ddSecretaria;
	}

	public void setDdSecretaria(DropDown ddSecretaria) {
		this.ddSecretaria = ddSecretaria;
	}

	public DropDown getDdArea() {
		return ddArea;
	}

	public void setDdArea(DropDown ddArea) {
		this.ddArea = ddArea;
	}

	public SingleSelectOptionsList getDdSecretariaOptions() {
		return ddSecretariaOptions;
	}

	public void setDdSecretariaOptions(SingleSelectOptionsList ddSecretariaOptions) {
		this.ddSecretariaOptions = ddSecretariaOptions;
	}

	public SingleSelectOptionsList getDdAreaOptions() {
		return ddAreaOptions;
	}

	public void setDdAreaOptions(SingleSelectOptionsList ddAreaOptions) {
		this.ddAreaOptions = ddAreaOptions;
	}

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
		this.dateTimeConverter1 = dateTimeConverter1;
	}

	public StaticText getStNumero() {
		return stNumero;
	}

	public void setStNumero(StaticText stNumero) {
		this.stNumero = stNumero;
	}

	public ObjectListDataProvider getLdpLicitaciones() {
		return ldpLicitaciones;
	}

	public void setLdpLicitaciones(ObjectListDataProvider ldpLicitaciones) {
		this.ldpLicitaciones = ldpLicitaciones;
	}

	public TableColumn getTcNumero() {
		return tcNumero;
	}

	public void setTcNumero(TableColumn tcNumero) {
		this.tcNumero = tcNumero;
	}

	public TableColumn getTcObjeto() {
		return tcObjeto;
	}

	public void setTcObjeto(TableColumn tcObjeto) {
		this.tcObjeto = tcObjeto;
	}

	public TableColumn getTcFechaPublicacion() {
		return tcFechaPublicacion;
	}

	public void setTcFechaPublicacion(TableColumn tcFechaPublicacion) {
		this.tcFechaPublicacion = tcFechaPublicacion;
	}

	public TableColumn getTcFechaCierre() {
		return tcFechaCierre;
	}

	public void setTcFechaCierre(TableColumn tcFechaCierre) {
		this.tcFechaCierre = tcFechaCierre;
	}

	public StaticText getStEstado() {
		return stEstado;
	}

	public void setStEstado(StaticText stEstado) {
		this.stEstado = stEstado;
	}

	public TableColumn getTcEstado() {
		return tcEstado;
	}

	public void setTcEstado(TableColumn tcEstado) {
		this.tcEstado = tcEstado;
	}

	public StaticText getStObjeto() {
		return stObjeto;
	}

	public void setStObjeto(StaticText stObjeto) {
		this.stObjeto = stObjeto;
	}

	public StaticText getStFechaPublicacion() {
		return stFechaPublicacion;
	}

	public void setStFechaPublicacion(StaticText stFechaPublicacion) {
		this.stFechaPublicacion = stFechaPublicacion;
	}

	public Label getLblNroLicitacion() {
		return lblNroLicitacion;
	}

	public void setLblNroLicitacion(Label lblNroLicitacion) {
		this.lblNroLicitacion = lblNroLicitacion;
	}

	public TextField getTfNroLicitacion() {
		return tfNroLicitacion;
	}

	public void setTfNroLicitacion(TextField tfNroLicitacion) {
		this.tfNroLicitacion = tfNroLicitacion;
	}

	public StaticText getStFechaCierre() {
		return stFechaCierre;
	}

	public void setStFechaCierre(StaticText stFechaCierre) {
		this.stFechaCierre = stFechaCierre;
	}

	public Label getLblObjeto() {
		return lblObjeto;
	}

	public void setLblObjeto(Label lblObjeto) {
		this.lblObjeto = lblObjeto;
	}

	public Label getLblTipoLicitacion() {
		return lblTipoLicitacion;
	}

	public void setLblTipoLicitacion(Label lblTipoLicitacion) {
		this.lblTipoLicitacion = lblTipoLicitacion;
	}

	public Label getLblEstadoLicitacion() {
		return lblEstadoLicitacion;
	}

	public void setLblEstadoLicitacion(Label lblEstadoLicitacion) {
		this.lblEstadoLicitacion = lblEstadoLicitacion;
	}

	public Label getLblFechaPublicacion() {
		return lblFechaPublicacion;
	}

	public void setLblFechaPublicacion(Label lblFechaPublicacion) {
		this.lblFechaPublicacion = lblFechaPublicacion;
	}

	public Label getLblFechaAdjudicacion() {
		return lblFechaAdjudicacion;
	}

	public void setLblFechaAdjudicacion(Label lblFechaAdjudicacion) {
		this.lblFechaAdjudicacion = lblFechaAdjudicacion;
	}

	public Label getLblFechaOferta() {
		return lblFechaOferta;
	}

	public void setLblFechaOferta(Label lblFechaOferta) {
		this.lblFechaOferta = lblFechaOferta;
	}

	public TextField getTfFechaPublicacion() {
		return tfFechaPublicacion;
	}

	public void setTfFechaPublicacion(TextField tfFechaPublicacion) {
		this.tfFechaPublicacion = tfFechaPublicacion;
	}

	public TextField getTfFechaAdjudicacion() {
		return tfFechaAdjudicacion;
	}

	public void setTfFechaAdjudicacion(TextField tfFechaAdjudicacion) {
		this.tfFechaAdjudicacion = tfFechaAdjudicacion;
	}

	public TextField getTfFechaOferta() {
		return tfFechaOferta;
	}

	public void setTfFechaOferta(TextField tfFechaOferta) {
		this.tfFechaOferta = tfFechaOferta;
	}

	public Label getLblFormatoFechaPublicacion() {
		return lblFormatoFechaPublicacion;
	}

	public void setLblFormatoFechaPublicacion(Label lblFormatoFechaPublicacion) {
		this.lblFormatoFechaPublicacion = lblFormatoFechaPublicacion;
	}

	public Label getLblFormatoFechaAdjudicacion() {
		return lblFormatoFechaAdjudicacion;
	}

	public void setLblFormatoFechaAdjudicacion(Label lblFormatoFechaAdjudicacion) {
		this.lblFormatoFechaAdjudicacion = lblFormatoFechaAdjudicacion;
	}

	public Label getLblFormatoFechaOferta() {
		return lblFormatoFechaOferta;
	}

	public void setLblFormatoFechaOferta(Label lblFormatoFechaOferta) {
		this.lblFormatoFechaOferta = lblFormatoFechaOferta;
	}

	public TextField getTfObjeto() {
		return tfObjeto;
	}

	public void setTfObjeto(TextField tfObjeto) {
		this.tfObjeto = tfObjeto;
	}

	public DropDown getDdEstadoLicitacion() {
		return ddEstadoLicitacion;
	}

	public void setDdEstadoLicitacion(DropDown ddEstadoLicitacion) {
		this.ddEstadoLicitacion = ddEstadoLicitacion;
	}

	public SingleSelectOptionsList getDdEstadoLicitacionDefaultOptions() {
		return ddEstadoLicitacionDefaultOptions;
	}

	public void setDdEstadoLicitacionDefaultOptions(SingleSelectOptionsList ddEstadoLicitacionDefaultOptions) {
		this.ddEstadoLicitacionDefaultOptions = ddEstadoLicitacionDefaultOptions;
	}

	public DropDown getDdTipoLicitacion() {
		return ddTipoLicitacion;
	}

	public void setDdTipoLicitacion(DropDown ddTipoLicitacion) {
		this.ddTipoLicitacion = ddTipoLicitacion;
	}

	public SingleSelectOptionsList getDdTipoLicitacionDefaultOptions() {
		return ddTipoLicitacionDefaultOptions;
	}

	public void setDdTipoLicitacionDefaultOptions(SingleSelectOptionsList ddTipoLicitacionDefaultOptions) {
		this.ddTipoLicitacionDefaultOptions = ddTipoLicitacionDefaultOptions;
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationComprasBean().getTablaContratacion();
	}

	public AdminLicitacion() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroContratacion locFiltro = getFiltro();
		
		borrarListIdAuxBienes(this.getTfBien(), locFiltro.getBien());
		locFiltro.setListaIdBienes(this.getSessionBean1().getListaIdBienes());

		locFiltro.setNumero(this.getTextFieldValueInteger(getTfNroLicitacion()));
		locFiltro.setObjeto(this.getTextFieldValue(getTfObjeto()));
		locFiltro.setFechaPublicacion(this.getTextFieldValueDate(getTfFechaPublicacion()));
		locFiltro.setTipo(this.getDDEnumValue(getDdTipoLicitacion(), Contratacion.Tipo.class));
		locFiltro.setEstado(this.getDDEnumValue(getDdEstadoLicitacion(), Contratacion.Estado.class));
		locFiltro.setSecretaria(this.getDDObjectValue(getDdSecretaria(), getCommunicationComprasBean().getMapaSecretariaSolSum()));
		locFiltro.setArea(this.getDDObjectValue(getDdArea(), getCommunicationComprasBean().getMapaAreasSolSum()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroContratacion locFiltro = getFiltro();

		this.getTfNroLicitacion().setText(locFiltro.getNumero());
		this.getTfObjeto().setText(locFiltro.getObjeto());
		this.getTfFechaPublicacion().setText(locFiltro.getFechaPublicacion());
		if (locFiltro.getTipo() != null) {
			this.getDdTipoLicitacion().setSelected(locFiltro.getTipo().toString());
		}
		if (locFiltro.getEstado() != null) {
			this.getDdEstadoLicitacion().setSelected(locFiltro.getEstado().toString());
		}
		if (locFiltro.getArea() != null) {
			this.getDdArea().setSelected(locFiltro.getArea().toString());
		}
		if (locFiltro.getSecretaria() != null) {
			this.getDdSecretaria().setSelected(locFiltro.getSecretaria().toString());
		}
		if (locFiltro.getBien() != null) {
			this.getTfBien().setText(locFiltro.getBien().toString());
		} else {
			this.getTfBien().setText(null);
		}
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().findListaContratacion((FiltroContratacion) pFiltro);
	}

	public void actionListenerDropSecretaria(ActionEvent event) {
		Set<String> locListaAreas = this.getCommunicationComprasBean()
				.getMapaAreasSolSum((Secretaria) this.getDDObjectValue(getDdSecretaria(), getCommunicationComprasBean().getMapaSecretariaSolSum())).keySet();
		Option[] opAreas = new Option[locListaAreas.size() + 1];
		int i = 0;
		opAreas[i++] = new Option("", "");
		for (String cadaArea : locListaAreas) {
			opAreas[i++] = new Option(cadaArea, cadaArea);
		}
		ddAreaOptions.setOptions(opAreas);
	}
	
	@Override
	protected void limpiarObjetosUsados() {
		FiltroContratacion locFiltro = getFiltro();
		locFiltro.setNumero(null);
		locFiltro.setObjeto(null);
		locFiltro.setFechaPublicacion(null);
		locFiltro.setTipo(null);
		locFiltro.setEstado(null);
		locFiltro.setArea(null);
		locFiltro.setSecretaria(null);
		locFiltro.setBien(null);

		this.getTfNroLicitacion().setText(null);
		this.getTfObjeto().setText(null);
		this.getTfFechaPublicacion().setText(null);
		this.getDdEstadoLicitacion().setSelected("");
		this.getDdTipoLicitacion().setSelected("");
		this.getDdArea().setSelected("");
		this.getDdSecretaria().setSelected("");
		this.getTfBien().setText(null);
		
		this.getSessionBean1().getListaIdBienes().clear();
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpLicitaciones();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaLicitaciones();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationComprasBean().setListaLicitaciones(lista);
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Contratacion locContratacion = (Contratacion) pObject;
		this.getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().setLlave(this.getSessionBean1().getLlave());
		locContratacion = this.getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().getContratacionPorId(locContratacion.getIdContratacion());
		return locContratacion;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Contrataciones";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminLicitacion";
	}

	public String btnAgregar_action() {
		return toAbm(new LicitacionModel().new AgregarController());
	}

	public String btnModificar_action() {

		Contratacion contratacion = (Contratacion) this.getObjetoSeleccionado();
		if (contratacion != null && !contratacion.getEstado().equals(Contratacion.Estado.NUEVA)) {
			warn("S\363lo se pueden Modificar las Contrataci\363nes en estado Vigente.");
			return null;
		}
		return toAbm(new LicitacionModel().new ModificarController());
	}

	public String btnEliminar_action() {

		Contratacion contratacion = (Contratacion) this.getObjetoSeleccionado();
		if (contratacion != null && !contratacion.getEstado().equals(Contratacion.Estado.NUEVA)) {
			warn("S\363lo se pueden Eliminar las Contrataci\363nes en estado Vigente.");
			return null;
		}

		return toAbm(new LicitacionModel().new EliminarController());
	}

	public String btnConsultar_action() {
		return toAbm(new LicitacionModel().new ConsultarController());
	}
	
	public String btnSeleccionarBien_action() {
		return navegarParaSeleccionar("AdminBien");
	}
	
	public String btnLimpiarBien_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.limpiarObjeto(this.getTfBien());
			FiltroContratacion locFiltro = getFiltro();
			locFiltro.setBien(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		
		return retorno;
	}

	public String btnAgregarDesdeSolicitud_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.getRequestBean1().setTipoSeleccion("MULTIPLE");
		this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		retorno = "AdminSolicitudSuministro";

		return retorno;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof Bien) {
			FiltroContratacion locFiltro = getFiltro();
			Bien bien = (Bien) pObject;

			locFiltro.setBien(bien);
		}
	}
	
	@Override
	public long getSerialVersionUID() {
		return Contratacion.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMLicitacion$AdminLicitacion}";
	}
	
	public void setPersonaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroContratacion locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Bien locBien = null;
		
		try {
			locBien = (Bien) this.getCommunicationComprasBean().getRemoteSystemAdministracionBienes().findBienByID(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		locFiltro.setBien(locBien);
	}
}