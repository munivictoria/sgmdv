/*
 * AdminProveedor.java
 *
 * Created on 23 de noviembre de 2006, 10:18
 * Copyright Trascender
 */
package muni.compras.ABMProveedor;

import jasper.ConstantesReportes;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.filtros.FiltroProveedores;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.TipoBien;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminProveedor extends AdminPageBean {

	private Button btnImprimirReporte = new Button();
	// private Button btnSeleccionarGrupoProveedor = new Button();
	private Button btnActivar = new Button();
	// private Button btnLimpiarGrupoProveedores = new Button();
	private Button btnSeleccionarCodigoCiiu = new Button();
	private HtmlAjaxCommandButton btnLimpiarCodigoCiiu = new HtmlAjaxCommandButton();

	private StaticText stSeparador5 = new StaticText();
	private StaticText stSeparador6 = new StaticText();

	private TextField tfCodigo = new TextField();
	private TextField tfRazonSocial = new TextField();
	// private TextField tfGrupoProveedor = new TextField();
	private TextField tfCodigoCiiu = new TextField();
	// private TextField tfPersona = new TextField();

	// private Label lblGrupoProveedor = new Label();
	private Label lblRazonSocial = new Label();
	private Label lblEstado = new Label();
	private Label lblCodigo = new Label();
	private Label lblTipo = new Label();
	private Label lblCodigoCiiu = new Label();
	private Label lblPersona = new Label();

	private DropDown ddEstadoProveedor = new DropDown();
	private DropDown ddTipo = new DropDown();
	private SingleSelectOptionsList ddEstadoProveedorDefaultOptions = new SingleSelectOptionsList();
	private ObjectListDataProvider ldpProveedor = new ObjectListDataProvider();
	private SingleSelectOptionsList ddTipoDefaultOptions = new SingleSelectOptionsList();

	private Label lblTipoBien = new Label();
	private DropDown ddTipoBien = new DropDown();
	private SingleSelectOptionsList ddTipoBienDefaultOptions = new SingleSelectOptionsList();
	
	private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();

	@Override
	protected void _init() throws Exception {
		
		this.setListaDelCommunicationAtributosDinamicos(null);

		btnImprimirReporte.setDisabled(true);
		// ddEstadoProveedorDefaultOptions.setOptions(new
		// com.sun.rave.web.ui.model.Option[] {new
		// com.sun.rave.web.ui.model.Option("ACTIVO", "ACTIVO"), new
		// com.sun.rave.web.ui.model.Option("INACTIVO", "INACTIVO")});
		// ddEstadoProveedorDefaultOptions.setSelectedValue("ACTIVO");
		Option[] op = null;
		// Tipo Documento
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Proveedor.Estado.values(), "may");
		ddEstadoProveedorDefaultOptions.setOptions(op);

		Option[] opTipo;
		opTipo = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Proveedor.Tipo.values(), "may");
		ddTipoDefaultOptions.setOptions(opTipo);

		Set<String> locListaTipoBien = getApplicationBean1().getMapaTipoBien().keySet();

		Option[] opTipoBien = new Option[locListaTipoBien.size() + 1];
		int j = 0;
		opTipoBien[j++] = new Option("", "");
		for (String cadaTipo : locListaTipoBien) {
			opTipoBien[j++] = new Option(cadaTipo, cadaTipo);
		}
		this.ddTipoBienDefaultOptions.setOptions(opTipoBien);
	}

	// public TextField getTfPersona() {
	// return tfPersona;
	// }
	//
	// public void setTfPersona(TextField tfPersona) {
	// this.tfPersona = tfPersona;
	// }

	private List getListaDelCommunicationAtributosDinamicos() {
		return this.getComunicationBean().getListaAtributosDinamicosPersonasFisicas();
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		this.getComunicationBean().setListaAtributosDinamicosPersonasFisicas(lista);
	}
	
	public Label getLblPersona() {
		return lblPersona;
	}

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	public void setLblPersona(Label lblPersona) {
		this.lblPersona = lblPersona;
	}

	public HtmlAjaxCommandButton getBtnLimpiarCodigoCiiu() {
		return btnLimpiarCodigoCiiu;
	}

	public void setBtnLimpiarCodigoCiiu(HtmlAjaxCommandButton btnLimpiarCodigoCiiu) {
		this.btnLimpiarCodigoCiiu = btnLimpiarCodigoCiiu;
	}

	public Button getBtnSeleccionarCodigoCiiu() {
		return btnSeleccionarCodigoCiiu;
	}

	public void setBtnSeleccionarCodigoCiiu(Button btnSeleccionarCodigoCiiu) {
		this.btnSeleccionarCodigoCiiu = btnSeleccionarCodigoCiiu;
	}

	public TextField getTfCodigoCiiu() {
		return tfCodigoCiiu;
	}

	public void setTfCodigoCiiu(TextField tfCodigoCiiu) {
		this.tfCodigoCiiu = tfCodigoCiiu;
	}

	public Label getLblCodigoCiiu() {
		return lblCodigoCiiu;
	}

	public void setLblCodigoCiiu(Label lblCodigoCiiu) {
		this.lblCodigoCiiu = lblCodigoCiiu;
	}

	public DropDown getDdTipoBien() {
		return ddTipoBien;
	}

	public void setDdTipoBien(DropDown ddTipoBien) {
		this.ddTipoBien = ddTipoBien;
	}

	public SingleSelectOptionsList getDdTipoBienDefaultOptions() {
		return ddTipoBienDefaultOptions;
	}

	public void setDdTipoBienDefaultOptions(SingleSelectOptionsList ddTipoBienDefaultOptions) {
		this.ddTipoBienDefaultOptions = ddTipoBienDefaultOptions;
	}

	public Label getLblTipoBien() {
		return lblTipoBien;
	}

	public void setLblTipoBien(Label lblTipoBien) {
		this.lblTipoBien = lblTipoBien;
	}

	public Label getLblRazonSocial() {
		return lblRazonSocial;
	}

	public void setLblRazonSocial(Label lblRazonSocial) {
		this.lblRazonSocial = lblRazonSocial;
	}

	public TextField getTfRazonSocial() {
		return tfRazonSocial;
	}

	public void setTfRazonSocial(TextField tfRazonSocial) {
		this.tfRazonSocial = tfRazonSocial;
	}

	public DropDown getDdTipo() {
		return ddTipo;
	}

	public void setDdTipo(DropDown ddTipo) {
		this.ddTipo = ddTipo;
	}

	public SingleSelectOptionsList getDdTipoDefaultOptions() {
		return ddTipoDefaultOptions;
	}

	public void setDdTipoDefaultOptions(SingleSelectOptionsList ddTipoDefaultOptions) {
		this.ddTipoDefaultOptions = ddTipoDefaultOptions;
	}

	public Label getLblTipo() {
		return lblTipo;
	}

	public void setLblTipo(Label lblTipo) {
		this.lblTipo = lblTipo;
	}

	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button btnImprimirReporte) {
		this.btnImprimirReporte = btnImprimirReporte;
	}

	public ObjectListDataProvider getLdpProveedor() {
		return ldpProveedor;
	}

	public void setLdpProveedor(ObjectListDataProvider oldp) {
		this.ldpProveedor = oldp;
	}

	public Label getLblCodigo() {
		return lblCodigo;
	}

	public void setLblCodigo(Label lblCodigo) {
		this.lblCodigo = lblCodigo;
	}

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tfCodigo) {
		this.tfCodigo = tfCodigo;
	}

	// public Label getLblGrupoProveedor() {
	// return lblGrupoProveedor;
	// }
	//
	// public void setLblGrupoProveedor(Label lblGrupoProveedor) {
	// this.lblGrupoProveedor = lblGrupoProveedor;
	// }
	//
	// public TextField getTfGrupoProveedor() {
	// return tfGrupoProveedor;
	// }
	//
	// public void setTfGrupoProveedor(TextField tf) {
	// this.tfGrupoProveedor = tf;
	// }
	//
	// public Button getBtnSeleccionarGrupoProveedor() {
	// return btnSeleccionarGrupoProveedor;
	// }
	//
	// public void setBtnSeleccionarGrupoProveedor(Button b) {
	// this.btnSeleccionarGrupoProveedor = b;
	// }

	public StaticText getStSeparador5() {
		return stSeparador5;
	}

	public void setStSeparador5(StaticText stSeparador5) {
		this.stSeparador5 = stSeparador5;
	}

	public StaticText getStSeparador6() {
		return stSeparador6;
	}

	public void setStSeparador6(StaticText stSeparador6) {
		this.stSeparador6 = stSeparador6;
	}

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	public DropDown getDdEstadoProveedor() {
		return ddEstadoProveedor;
	}

	public void setDdEstadoProveedor(DropDown dd) {
		this.ddEstadoProveedor = dd;
	}

	public SingleSelectOptionsList getDdEstadoProveedorDefaultOptions() {
		return ddEstadoProveedorDefaultOptions;
	}

	public void setDdEstadoProveedorDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddEstadoProveedorDefaultOptions = ssol;
	}

	public Button getBtnActivar() {
		return btnActivar;
	}

	public void setBtnActivar(Button btnActivar) {
		this.btnActivar = btnActivar;
	}

	// public Button getBtnLimpiarGrupoProveedores() {
	// return btnLimpiarGrupoProveedores;
	// }
	//
	// public void setBtnLimpiarGrupoProveedores(Button b) {
	// this.btnLimpiarGrupoProveedores = b;
	// }

	public AdminProveedor() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		FiltroProveedores locFiltro = getFiltro();
		List atributosDinamicos = null;
		try {
			this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(Proveedor.serialVersionUID, null, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		locFiltro.setListaAtributoDinamico(atributosDinamicos);
		
		int ind = 0;
		ep.getObjetos().add(ind++, new Proveedor());
		ep.getObjetos().add(ind++, new TipoBien());
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroProveedores locFiltro = getFiltro();

		borrarListIdAuxPersonas(this.tfPersonaSeleccionada, locFiltro.getPersona());
		locFiltro.setListaIdPersonas(this.getSessionBean1().getListaIdPersonas());
		
		locFiltro.setRazonSocial(this.getTextFieldValue(getTfRazonSocial()));
		locFiltro.setCodigo(this.getTextFieldValue(getTfCodigo()));
		locFiltro.setEstado(this.getDDEnumValue(getDdEstadoProveedor(), Proveedor.Estado.class));
		locFiltro.setTipo(this.getDDEnumValue(getDdTipo(), Proveedor.Tipo.class));
		locFiltro.setTipoBien(this.getDDObjectValue(getDdTipoBien(), getApplicationBean1().getMapaTipoBien()));
		
		if (locFiltro.getListaAtributoDinamico() != null) {
			locFiltro.setListaAtributoDinamico((ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltro.getListaAtributoDinamico()));
		}
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroProveedores locFiltro = getFiltro();
		
		panelAtributoDinamico = new PanelAtributoDinamico(locFiltro.getListaAtributoDinamico(), "#{compras$ABMProveedor$ABMProveedor}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltro.getListaAtributoDinamico());

		this.getTfCodigo().setText(locFiltro.getCodigo());
		this.getTfRazonSocial().setText(locFiltro.getRazonSocial());

		this.getDdEstadoProveedor().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado() == null ? Proveedor.Estado.ACTIVO : locFiltro.getEstado())));

		this.getDdTipo().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipo())));
		this.getDdTipoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locFiltro.getTipo())));

		if (locFiltro.getCodigoCiiu() != null) {
			this.getTfCodigoCiiu().setText(locFiltro.getCodigoCiiu());
		}

		if (locFiltro.getTipoBien() != null) {
			this.getDdTipoBien().setSelected(locFiltro.getTipoBien().getNombre());
		}

		if (locFiltro.getPersona() != null) {
			this.getTfPersonaSeleccionada().setText(locFiltro.getPersona().getToStringCompleto());
		}
		 
		this.setListaDelCommunicationAtributosDinamicos((ArrayList)locFiltro.getListaAtributoDinamico());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().findListadoProveedores((FiltroProveedores) pFiltro);
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpProveedor();
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroProveedores locFiltro = getFiltro();

		locFiltro.setRazonSocial(null);
		locFiltro.setCodigo(null);
		locFiltro.setEstado(null);
		locFiltro.setTipo(null);
		locFiltro.setTipoBien(null);
		locFiltro.setCodigoCiiu(null);
		locFiltro.setPersona(null);

		this.getSessionBean1().setPersonaSeleccionada(null);
		this.getSessionBean1().getListaIdPersonas().clear();
		
		this.getTfPersonaSeleccionada().setText("");
		this.getTfCodigo().setText("");
		this.getTfRazonSocial().setText("");
		this.getDdEstadoProveedor().setSelected(Util.getEnumNameFromString(String.valueOf(Proveedor.Estado.ACTIVO)));
		this.getDdTipo().setSelected("");
		this.getDdTipoBien().setSelected("");
		this.getTfCodigoCiiu().setText("");
		
		panelAtributoDinamico.limpiarCampos();
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationComprasBean().getTablaProveedor();
	}

	@Override
	protected List getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationComprasBean().getListaProveedores();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationComprasBean().setListaProveedores(lista);
	}

	@Override
	protected boolean validarSeleccionar(Object pObject) {
		Proveedor proveedor = (Proveedor) pObject;
		if (proveedor.getEstado().equals(Proveedor.Estado.INACTIVO)) {
			warn("No se puede seleccionar un proveedor que no est\341 en estado ACTIVO.");
			return false;
		}
		return true;
	}

	// public String btnLimpiarGrupoProveedores_action() {
	// String retorno = null;
	// boolean ultimo = this.ultimoElementoPilaDeSubSesion();
	// if (ultimo) {
	// this.limpiarObjeto(1, this.getTfGrupoProveedor());
	// this.guardarEstadoObjetosUsados();
	// } else {
	// retorno = this.prepararCaducidad();
	// }
	// return retorno;
	// }

	public String btnImprimirReporte_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			try {
				RowKey rk = null;
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = this.getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					Proveedor locProveedor = (Proveedor) obj;
					// this.getSessionBean1().setObjetoImpresion(obj);
					this.getCommunicationComprasBean().getRemoteSystemReportesCompras().setLlave(this.getSessionBean1().getLlave());
					JasperPrint jp = this.getCommunicationComprasBean().getRemoteSystemReportesCompras().getReporteProveedor(locProveedor.getIdProveedor());

					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_InformacionProveedor");
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
				}
			} catch (Exception e) {
				log(this.getCasoNavegacion() + "_ReporteDinamicoError: ", e);
				error(this.getNombrePagina() + " - ReporteDinamico: " + e.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnActivar_action() {
		Proveedor proveedor = (Proveedor) getObjetoSeleccionado();
		if (proveedor != null && proveedor.getEstado().equals(Proveedor.Estado.ACTIVO)) {
			warn("El Proveedor seleccionado ya se encuentra activo.");
			return null;
		} else {
			return toAbm(new ProveedorModel().new ActivarController());
		}
	}

	public void valueChangeEvent(ValueChangeEvent event) {
		this.btnImprimirReporte.setDisabled(false);
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Proveedor locProveedor = (Proveedor) pObject;
		this.getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().setLlave(this.getSessionBean1().getLlave());
		locProveedor = getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().findProveedorByID(locProveedor.getIdProveedor());
		return locProveedor;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Proveedores";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminProveedor";
	}

	public String btnAgregar_action() {

		return toAbm(new ProveedorModel().new AgregarController());
	}

	public String btnModificar_action() {

		Proveedor proveedor = (Proveedor) getObjetoSeleccionado();

		if (proveedor == null) {
			return null;
		}

		if (proveedor.getEstado().equals(Proveedor.Estado.INACTIVO)) {
			warn("No se puede modificar un proveedor que no est\341 en estado ACTIVO.");
			return null;
		}

		return toAbm(new ProveedorModel().new ModificarController());
	}

	public String btnEliminar_action() {

		Proveedor proveedor = (Proveedor) getObjetoSeleccionado();

		if (proveedor == null) {
			return null;
		}

		if (proveedor.getEstado().equals(Proveedor.Estado.INACTIVO)) {
			warn("No se puede eliminar un proveedor que no est\341 en estado ACTIVO.");
			return null;
		}

		return toAbm(new ProveedorModel().new EliminarController());
	}

	public String btnConsultar_action() {
		return toAbm(new ProveedorModel().new ConsultarController());
	}

	public String btnSeleccionarCodigoCiiu_action() {
		return navegarParaSeleccionar("AdminCodigoCiiu");
	}

	public String btnLimpiarCodigoCiiu_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.getTfCodigoCiiu().setText("");
			FiltroProveedores locFiltro = getFiltro();
			locFiltro.setCodigoCiiu(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroProveedores locFiltro = getFiltro();
		if (pObject instanceof CodigoCiiu) {
			CodigoCiiu codigoCiiu = (CodigoCiiu) pObject;
			locFiltro.setCodigoCiiu(codigoCiiu);
		}
		if (pObject instanceof Persona) {
			Persona persona = (Persona) pObject;
			locFiltro.setPersona(persona);
			this.getSessionBean1().setPersonaSeleccionada(persona);
		}

		this.getRequestBean1().setObjetoSeleccion(null);
	}
	
	@Override
	public long getSerialVersionUID() {
		return Proveedor.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMProveedor$AdminProveedor}";
	}
	
	public void setPersonaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroProveedores locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Persona propietario = null;
		
		try {
			propietario = (Persona) this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}
		
		locFiltro.setPersona(propietario);
		this.getSessionBean1().setPersonaSeleccionada(propietario);
	}
}