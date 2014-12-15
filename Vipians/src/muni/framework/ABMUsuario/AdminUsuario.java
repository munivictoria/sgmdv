/*
 * AdminUsuario.java
 *
 * Created on 27 de septiembre de 2006, 08:39
 * Copyright Trascender
 */

package muni.framework.ABMUsuario;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

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
import com.trascender.framework.recurso.filtros.FiltroUsuario;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminUsuario extends AdminPageBean {

	@Override
	protected void _init() throws Exception {
		Option[] opEstado = null;

		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Usuario.Estado.values(), "may");
		ddEstadoDefaultOptions.setOptions(opEstado);

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(Usuario.Estado.ACTIVO)));
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(HtmlAjaxCommandButton btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private StaticText staticText11 = new StaticText();

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText st) {
		this.staticText11 = st;
	}

	private StaticText staticText12 = new StaticText();

	public StaticText getStaticText12() {
		return staticText12;
	}

	public void setStaticText12(StaticText staticText12) {
		this.staticText12 = staticText12;
	}

	private Button btnActivar = new Button();

	public Button getBtnActivar() {
		return btnActivar;
	}

	public void setBtnActivar(Button b) {
		this.btnActivar = b;
	}

	private TextField tfPersona = new TextField();

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tf) {
		this.tfPersona = tf;
	}

	private StaticText staticText6 = new StaticText();

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText st) {
		this.staticText6 = st;
	}

	private StaticText staticText8 = new StaticText();

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText st) {
		this.staticText8 = st;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private Label lblEstado = new Label();

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label l) {
		this.lblEstado = l;
	}

	public DropDown ddEstado = new DropDown();

	public DropDown getDdEstado() {
		return ddEstado;
	}

	public void setDdEstado(DropDown ddEstado) {
		this.ddEstado = ddEstado;
	}

	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdEstadoDefaultOptions() {
		return ddEstadoDefaultOptions;
	}

	public void setDdEstadoDefaultOptions(SingleSelectOptionsList ddEstadoDefaultOptions) {
		this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
	}

	private TextField tfUsuario = new TextField();

	public TextField getTfUsuario() {
		return tfUsuario;
	}

	public void setTfUsuario(TextField tf) {
		this.tfUsuario = tf;
	}

	private Button btnSeleccionarPersona = new Button();

	public Button getBtnSeleccionarPersona() {
		return btnSeleccionarPersona;
	}

	public void setBtnSeleccionarPersona(Button b) {
		this.btnSeleccionarPersona = b;
	}

	private ObjectListDataProvider ldpUsuarios = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpUsuarios() {
		return ldpUsuarios;
	}

	public void setLdpUsuarios(ObjectListDataProvider oldp) {
		this.ldpUsuarios = oldp;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	private TableColumn tableColumn3 = new TableColumn();

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tc) {
		this.tableColumn3 = tc;
	}

	private TableColumn tableColumn4 = new TableColumn();

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tableColumn4) {
		this.tableColumn4 = tableColumn4;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText staticText9) {
		this.staticText9 = staticText9;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public AdminUsuario() {
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroUsuario locFiltro = this.getFiltro();

		locFiltro.setUser(this.getTextFieldValue(getTfUsuario()));
		locFiltro.setEstado(this.getDDEnumValue(getDdEstado(), Usuario.Estado.class));
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroUsuario locFiltro = this.getFiltro();

		if(locFiltro.getEstado() != null) {
			this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
		}
		this.getTfUsuario().setText(locFiltro.getUser());
		if(locFiltro.getPersonaFisica() != null) {
			this.getTfPersona().setText(locFiltro.getPersonaFisica().toString());
		}
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemUsuario().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemUsuario().findUsuario((FiltroUsuario) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroUsuario locFiltro = getFiltro();
		locFiltro.setUser(null);
		locFiltro.setEstado(null);
		locFiltro.setPersonaFisica(null);

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(Usuario.Estado.ACTIVO)));
		this.getTfUsuario().setText("");
		this.getTfPersona().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpUsuarios();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getComunicationBean().getListaUsuarios();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getComunicationBean().setListaUsuarios((ArrayList) lista);
	}

	public String btnSeleccionarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			try {
				RowKey rk = this.getSeleccionado();
				if(rk != null) {
					this.setRowKeySeleccionado(this.getSeleccionado());
				}
			} catch(Exception ex) {
				log("AdminUsuario" + "_SeleccionarPersonaError:", ex);
				error("Administraci\363n de Usuarios" + " - Seleccionar Persona: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminPersonaFisica";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnModificar_action() {
		Usuario usuario = (Usuario) getObjetoSeleccionado();
		if(usuario == null) {
			return null;
		}
		if(usuario.getEstado().equals(Usuario.Estado.ELIMINADO)) {
			warn("No se puede modificar un Usuario en estado ELIMINADO.");
			return null;
		}

		return toAbm(new UsuarioModel().new ModificarUsuarioController());
	}

	public String btnAgregar_action() {
		return toAbm(new UsuarioModel().new AgregarUsuarioController());
	}

	public String btnEliminar_action() {
		Usuario usuario = (Usuario) getObjetoSeleccionado();
		if(usuario == null) {
			return null;
		}
		if(usuario.getEstado().equals(Usuario.Estado.ELIMINADO)) {
			warn("El Usuario ya se encuentra eliminado.");
			return null;
		}
		
		return toAbm(new UsuarioModel().new EliminarUsuarioController());
	}

	public String btnConsultar_action() {
		return toAbm(new UsuarioModel().new ConsultarUsuarioController());
	}

	public String btnLimpiarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.limpiarObjeto(getTfPersona());
			FiltroUsuario locFiltro = getFiltro();
			locFiltro.setPersonaFisica(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnActivar_action() {
		Usuario usuario = (Usuario) getObjetoSeleccionado();
		if(usuario != null && usuario.getEstado().equals(Usuario.Estado.ACTIVO)) {
			warn("El usuario seleccionado ya se encuentra activo.");
			return null;
		}

		return toAbm(new UsuarioModel().new RecuperarUsuario());
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Usuarios";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminUsuario";
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Usuario locUsuario = (Usuario) pObject;
		getComunicationBean().getRemoteSystemUsuario().setLlave(getSessionBean1().getLlave());
		locUsuario = getComunicationBean().getRemoteSystemUsuario().getUsuarioPorId(locUsuario.getIdUsuario());
		return locUsuario;
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationBean().getTablaUsuario();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof PersonaFisica) {
			if(pObject != null) {
				PersonaFisica persona = (PersonaFisica) pObject;
				FiltroUsuario locFiltro = getFiltro();
				locFiltro.setPersonaFisica(persona);
			}
		}
	}

	@Override
	public long getSerialVersionUID() {
		return Usuario.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMUsuario$AdminUsuario}";
	}
}