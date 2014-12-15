/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.framework.ABMConfiguracionRecurso;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.framework.recurso.filtros.FiltroConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author jsantacruz
 */
public class AdminConfiguracionRecurso extends AdminPageBean {

	public AdminConfiguracionRecurso() {
	}

	private TextField tfRecurso = new TextField();
	private TextField tfNombreAlias = new TextField();

	private Button btnBuscarRecurso = new Button();
	private HtmlAjaxCommandButton btnLimpiarRecurso = new HtmlAjaxCommandButton();

	private Button btnAgregarCgfRecurso = new Button();
	private Button btnModificarCgfRecurso = new Button();
	private Button btnConsultarCgfRecurso = new Button();
	private Button btnEliminarCgfRecurso = new Button();

	private ObjectListDataProvider ldpCfgRecurso = new ObjectListDataProvider();

	private Label label1 = new Label();
	private Label label2 = new Label();
	private StaticText staticText1 = new StaticText();
	private StaticText staticText2 = new StaticText();
	private StaticText staticText3 = new StaticText();
	private StaticText staticText4 = new StaticText();
	private StaticText staticText5 = new StaticText();
	private StaticText staticText6 = new StaticText();
	private StaticText staticText7 = new StaticText();
	private StaticText staticText8 = new StaticText();
	private StaticText staticText9 = new StaticText();

	private TableColumn tableColumn2 = new TableColumn();
	private TableColumn tableColumn3 = new TableColumn();

	private Label lblUsuario = new Label();
	private TextField tfUsuario = new TextField();
	private Button btnBuscarUsuario = new Button();
	private HtmlAjaxCommandButton btnLimpiarUsuario = new HtmlAjaxCommandButton();
	
	public Label getLblUsuario() {
		return lblUsuario;
	}

	public void setLblUsuario(Label lblUsuario) {
		this.lblUsuario = lblUsuario;
	}

	public TextField getTfUsuario() {
		return tfUsuario;
	}

	public void setTfUsuario(TextField tfUsuario) {
		this.tfUsuario = tfUsuario;
	}

	public Button getBtnBuscarUsuario() {
		return btnBuscarUsuario;
	}

	public void setBtnBuscarUsuario(Button btnBuscarUsuario) {
		this.btnBuscarUsuario = btnBuscarUsuario;
	}

	public HtmlAjaxCommandButton getBtnLimpiarUsuario() {
		return btnLimpiarUsuario;
	}

	public void setBtnLimpiarUsuario(HtmlAjaxCommandButton btnLimpiarUsuario) {
		this.btnLimpiarUsuario = btnLimpiarUsuario;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroConfiguracionRecurso locFiltro = this.getFiltro();

		locFiltro.setNombreAlias(this.getTextFieldValue(getTfNombreAlias()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroConfiguracionRecurso locFiltro = this.getFiltro();

		if(locFiltro.getUsuario() != null) {
			this.getTfUsuario().setText(locFiltro.getUsuario().getUser());
		}
		if(locFiltro.getRecurso() != null) {
			this.getTfRecurso().setText(locFiltro.getRecurso().getNombre());
		}
		this.getTfNombreAlias().setText(locFiltro.getNombreAlias());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemParametro().findListaConfiguracionRecurso((FiltroConfiguracionRecurso) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroConfiguracionRecurso locFiltro = this.getFiltro();
		locFiltro.setNombreAlias(null);
		locFiltro.setRecurso(null);
		locFiltro.setUsuario(null);

		this.getTfUsuario().setText("");
		this.getTfRecurso().setText("");
		this.getTfNombreAlias().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpCfgRecurso();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getComunicationBean().getListaConfiguracionRecursos();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaConfiguracionRecursos((ArrayList) lista);
	}

	public String btnAgregar_action() {
		return toAbm(new ConfiguracionRecursoModel().new AgregarConfiguracionRecursoController());
	}

	public String btnModificar_action() {
		return toAbm(new ConfiguracionRecursoModel().new ModificarConfiguracionRecursoController());
	}

	public String btnEliminar_action() {
		return toAbm(new ConfiguracionRecursoModel().new EliminarConfiguracionRecursoController());
	}

	public String btnConsultar_action() {
		return toAbm(new ConfiguracionRecursoModel().new ConsultarConfiguracionRecursoController());
	}
	
	public String btnBuscarUsuario_action() {
		return navegarParaSeleccionar("AdminUsuario");
	}
	
	public String btnLimpiarUsuario_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.getTfUsuario().setText(null);
			FiltroConfiguracionRecurso locFiltro = this.getFiltro();
			locFiltro.setUsuario(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnBuscarRecurso_action() {
		return navegarParaSeleccionar("SeleccionarRecurso");
	}

	public String btnLimpiarRecurso_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.getTfRecurso().setText(null);
			FiltroConfiguracionRecurso locFiltro = this.getFiltro();
			locFiltro.setRecurso(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public TextField getTfRecurso() {
		return tfRecurso;
	}

	public void setTfRecurso(TextField tfRecurso) {
		this.tfRecurso = tfRecurso;
	}

	public HtmlAjaxCommandButton getBtnLimpiarRecurso() {
		return btnLimpiarRecurso;
	}

	public void setBtnLimpiarRecurso(HtmlAjaxCommandButton btnLimpiarRecurso) {
		this.btnLimpiarRecurso = btnLimpiarRecurso;
	}

	/**
	 * @return the tfNombreAlias
	 */
	public TextField getTfNombreAlias() {
		return tfNombreAlias;
	}

	/**
	 * @param tfNombreAlias
	 *            the tfNombreAlias to set
	 */
	public void setTfNombreAlias(TextField tfNombreAlias) {
		this.tfNombreAlias = tfNombreAlias;
	}

	/**
	 * @return the btnBuscarRecurso
	 */
	public Button getBtnBuscarRecurso() {
		return btnBuscarRecurso;
	}

	/**
	 * @param btnBuscarRecurso
	 *            the btnBuscarRecurso to set
	 */
	public void setBtnBuscarRecurso(Button btnBuscarRecurso) {
		this.btnBuscarRecurso = btnBuscarRecurso;
	}

	/**
	 * @return the btnAgregarCgfRecurso
	 */
	public Button getBtnAgregarCgfRecurso() {
		return btnAgregarCgfRecurso;
	}

	/**
	 * @param btnAgregarCgfRecurso
	 *            the btnAgregarCgfRecurso to set
	 */
	public void setBtnAgregarCgfRecurso(Button btnAgregarCgfRecurso) {
		this.btnAgregarCgfRecurso = btnAgregarCgfRecurso;
	}

	/**
	 * @return the btnModificarCgfRecurso
	 */
	public Button getBtnModificarCgfRecurso() {
		return btnModificarCgfRecurso;
	}

	/**
	 * @param btnModificarCgfRecurso
	 *            the btnModificarCgfRecurso to set
	 */
	public void setBtnModificarCgfRecurso(Button btnModificarCgfRecurso) {
		this.btnModificarCgfRecurso = btnModificarCgfRecurso;
	}

	/**
	 * @return the btnConsultarCgfRecurso
	 */
	public Button getBtnConsultarCgfRecurso() {
		return btnConsultarCgfRecurso;
	}

	/**
	 * @param btnConsultarCgfRecurso
	 *            the btnConsultarCgfRecurso to set
	 */
	public void setBtnConsultarCgfRecurso(Button btnConsultarCgfRecurso) {
		this.btnConsultarCgfRecurso = btnConsultarCgfRecurso;
	}

	/**
	 * @return the btnEliminarCgfRecurso
	 */
	public Button getBtnEliminarCgfRecurso() {
		return btnEliminarCgfRecurso;
	}

	/**
	 * @param btnEliminarCgfRecurso
	 *            the btnEliminarCgfRecurso to set
	 */
	public void setBtnEliminarCgfRecurso(Button btnEliminarCgfRecurso) {
		this.btnEliminarCgfRecurso = btnEliminarCgfRecurso;
	}

	public ObjectListDataProvider getLdpCfgRecurso() {
		return this.ldpCfgRecurso;
	}

	/**
	 * @param ldpCfgRecurso
	 *            the ldpCfgRecurso to set
	 */
	public void setLdpCfgRecurso(ObjectListDataProvider ldpCfgRecurso) {
		this.ldpCfgRecurso = ldpCfgRecurso;
	}

	@Override
	protected String getNombrePagina() {
		return "Configuracion de Recurso";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminConfiguracionRecurso";
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		ConfiguracionRecurso locConfiguracionRecurso = (ConfiguracionRecurso) pObject;
		getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
		return getComunicationBean().getRemoteSystemParametro().getConfiguracionRecursoPorId(locConfiguracionRecurso.getIdConfiguracionRecurso());
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationBean().getTablaConfiguracionRecurso();
	}

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label label1) {
		this.label1 = label1;
	}

	/**
	 * @return the label2
	 */
	public Label getLabel2() {
		return label2;
	}

	/**
	 * @param label2
	 *            the label2 to set
	 */
	public void setLabel2(Label label2) {
		this.label2 = label2;
	}

	/**
	 * @return the staticText2
	 */
	public StaticText getStaticText2() {
		return staticText2;
	}

	/**
	 * @param staticText2
	 *            the staticText2 to set
	 */
	public void setStaticText2(StaticText staticText2) {
		this.staticText2 = staticText2;
	}

	/**
	 * @return the staticText3
	 */
	public StaticText getStaticText3() {
		return staticText3;
	}

	/**
	 * @param staticText3
	 *            the staticText3 to set
	 */
	public void setStaticText3(StaticText staticText3) {
		this.staticText3 = staticText3;
	}

	/**
	 * @return the staticText4
	 */
	public StaticText getStaticText4() {
		return staticText4;
	}

	/**
	 * @param staticText4
	 *            the staticText4 to set
	 */
	public void setStaticText4(StaticText staticText4) {
		this.staticText4 = staticText4;
	}

	/**
	 * @return the staticText5
	 */
	public StaticText getStaticText5() {
		return staticText5;
	}

	/**
	 * @param staticText5
	 *            the staticText5 to set
	 */
	public void setStaticText5(StaticText staticText5) {
		this.staticText5 = staticText5;
	}

	/**
	 * @return the staticText6
	 */
	public StaticText getStaticText6() {
		return staticText6;
	}

	/**
	 * @param staticText6
	 *            the staticText6 to set
	 */
	public void setStaticText6(StaticText staticText6) {
		this.staticText6 = staticText6;
	}

	/**
	 * @return the staticText7
	 */
	public StaticText getStaticText7() {
		return staticText7;
	}

	/**
	 * @param staticText7
	 *            the staticText7 to set
	 */
	public void setStaticText7(StaticText staticText7) {
		this.staticText7 = staticText7;
	}

	/**
	 * @return the staticText8
	 */
	public StaticText getStaticText8() {
		return staticText8;
	}

	/**
	 * @param staticText8
	 *            the staticText8 to set
	 */
	public void setStaticText8(StaticText staticText8) {
		this.staticText8 = staticText8;
	}

	/**
	 * @return the staticText9
	 */
	public StaticText getStaticText9() {
		return staticText9;
	}

	/**
	 * @param staticText9
	 *            the staticText9 to set
	 */
	public void setStaticText9(StaticText staticText9) {
		this.staticText9 = staticText9;
	}

	/**
	 * @return the tableColumn2
	 */
	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	/**
	 * @param tableColumn2
	 *            the tableColumn2 to set
	 */
	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	/**
	 * @return the tableColumn3
	 */
	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	/**
	 * @param tableColumn3
	 *            the tableColumn3 to set
	 */
	public void setTableColumn3(TableColumn tableColumn3) {
		this.tableColumn3 = tableColumn3;
	}

	/**
	 * @return the staticText1
	 */
	public StaticText getStaticText1() {
		return staticText1;
	}

	/**
	 * @param staticText1
	 *            the staticText1 to set
	 */
	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroConfiguracionRecurso locFiltro = getFiltro();
		if(pObject instanceof Usuario) {
			Usuario locUsuario = (Usuario) pObject;

			locFiltro.setUsuario(locUsuario);
		}
		if(pObject instanceof Recurso) {
			Recurso locRecurso = (Recurso) pObject;

			locFiltro.setRecurso(locRecurso);
		}
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso}";
	}

	@Override
	public long getSerialVersionUID() {
		return ConfiguracionRecurso.serialVersionUID;
	}
}