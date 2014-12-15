/*
 * AdminVehiculo.java
 *
 * Created on 18 de octubre de 2006, 8:55
 * Copyright Trascender SRL
 */

package muni.habilitaciones.grpAutomotor.ABMVehiculo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroVehiculo;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.TituloPropiedadAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

public class AdminVehiculo extends AdminPageBean {

	protected ObjectListDataProvider ldpVehiculo = new ObjectListDataProvider();
	protected Label lblPatente = new Label();
	protected Label lblModelo = new Label();
	protected Label lblMarca = new Label();
	protected Label lblMotor = new Label();
	protected StaticText stPatente = new StaticText();
	protected TextField tfPatente = new TextField();
	protected TextField tfMarca = new TextField();
	protected TextField tfModelo = new TextField();
	protected TextField tfMotor = new TextField();
	protected TextArea textArea1 = new TextArea();
	protected TableColumn tcDescripcion = new TableColumn();
	protected TableColumn tcPatente = new TableColumn();
	protected Button btnSeleccionarModelo = new Button();
	protected HtmlAjaxCommandButton btnLimpiarModelo = new HtmlAjaxCommandButton();
	protected Button btnSeleccionarMarca = new Button();
	protected Button btnLimpiarMarca = new Button();

	private Label lblFechaInscripcion = new Label();
	private TextField tfFechaInscripcion = new TextField();
	private Label lblCodigo = new Label();
	private TextField tfCodigo = new TextField();

	public Label getLblFechaInscripcion() {
		return lblFechaInscripcion;
	}

	public void setLblFechaInscripcion(Label lblFechaInscripcion) {
		this.lblFechaInscripcion = lblFechaInscripcion;
	}

	public TextField getTfFechaInscripcion() {
		return tfFechaInscripcion;
	}

	public void setTfFechaInscripcion(TextField tfFechaInscripcion) {
		this.tfFechaInscripcion = tfFechaInscripcion;
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

	private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();
	private PanelAtributoDinamico panelAtributoDinamico2 = new PanelAtributoDinamico();

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	public PanelAtributoDinamico getPanelAtributoDinamico2() {
		return panelAtributoDinamico2;
	}

	public void setPanelAtributoDinamico2(PanelAtributoDinamico panelAtributoDinamico2) {
		this.panelAtributoDinamico2 = panelAtributoDinamico2;
	}

	public HtmlAjaxCommandButton getBtnLimpiarModelo() {
		return btnLimpiarModelo;
	}

	public void setBtnLimpiarModelo(HtmlAjaxCommandButton btnLimpiarModelo) {
		this.btnLimpiarModelo = btnLimpiarModelo;
	}

	public Button getBtnSeleccionarMarca() {
		return btnSeleccionarMarca;
	}

	public void setBtnSeleccionarMarca(Button btnSeleccionarMarca) {
		this.btnSeleccionarMarca = btnSeleccionarMarca;
	}

	public Button getBtnLimpiarMarca() {
		return btnLimpiarMarca;
	}

	public void setBtnLimpiarMarca(Button btnLimpiarMarca) {
		this.btnLimpiarMarca = btnLimpiarMarca;
	}

	public Label getLblMarca() {
		return lblMarca;
	}

	public void setLblMarca(Label lblMarca) {
		this.lblMarca = lblMarca;
	}

	public TextField getTfMarca() {
		return tfMarca;
	}

	public void setTfMarca(TextField tfMarca) {
		this.tfMarca = tfMarca;
	}

	public Button getBtnSeleccionarModelo() {
		return btnSeleccionarModelo;
	}

	public void setBtnSeleccionarModelo(Button btnSeleccionarModelo) {
		this.btnSeleccionarModelo = btnSeleccionarModelo;
	}

	public Label getLblModelo() {
		return lblModelo;
	}

	public void setLblModelo(Label lblModelo) {
		this.lblModelo = lblModelo;
	}

	public TextField getTfModelo() {
		return tfModelo;
	}

	public void setTfModelo(TextField tfModelo) {
		this.tfModelo = tfModelo;
	}

	public TableColumn getTcDescripcion() {
		return tcDescripcion;
	}

	public void setTcDescripcion(TableColumn tcDescripcion) {
		this.tcDescripcion = tcDescripcion;
	}

	public TableColumn getTcPatente() {
		return tcPatente;
	}

	public void setTcPatente(TableColumn tcPatente) {
		this.tcPatente = tcPatente;
	}

	public Label getLblPatente() {
		return lblPatente;
	}

	public void setLblPatente(Label lblPatente) {
		this.lblPatente = lblPatente;
	}

	public StaticText getStPatente() {
		return stPatente;
	}

	public void setStPatente(StaticText stPatente) {
		this.stPatente = stPatente;
	}

	public ObjectListDataProvider getLdpVehiculo() {
		return ldpVehiculo;
	}

	public void setLdpVehiculo(ObjectListDataProvider oldp) {
		this.ldpVehiculo = oldp;
	}

	public TextField getTfPatente() {
		return tfPatente;
	}

	public void setTfPatente(TextField tf) {
		this.tfPatente = tf;
	}

	public TextArea getTextArea1() {
		return textArea1;
	}

	public void setTextArea1(TextArea ta) {
		this.textArea1 = ta;
	}

	public Label getLblMotor() {
		return lblMotor;
	}

	public void setLblMotor(Label lblMotor) {
		this.lblMotor = lblMotor;
	}

	public TextField getTfMotor() {
		return tfMotor;
	}

	public void setTfMotor(TextField tfMotor) {
		this.tfMotor = tfMotor;
	}

	// ***Methods***
	public AdminVehiculo() {
	}

	@Override
	protected void _init() throws Exception {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		FiltroVehiculo locFiltro = getFiltro();
		List atributosDinamicos = null;
		List atributosDinamicos2 = null;

		try {
			this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(Vehiculo.serialVersionUID, null, true);
			atributosDinamicos2 = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(TituloPropiedadAutomotor.serialVersionUID, null, true);
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		locFiltro.setListaAtributoDinamico(atributosDinamicos);
		locFiltro.setListaAtributoDinamico2(atributosDinamicos2);

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroVehiculo locFiltroVehiculo = this.getFiltro();

		locFiltroVehiculo.setPatente(getTextFieldValue(this.getTfPatente()));
		locFiltroVehiculo.setMotor(getTextFieldValue(this.getTfMotor()));
		locFiltroVehiculo.setFechaInscripcion(getTextFieldValueDate(this.getTfFechaInscripcion()));
		locFiltroVehiculo.setCodigo(getTextFieldValue(this.getTfCodigo()));

		if(locFiltroVehiculo.getListaAtributoDinamico() != null) {
			locFiltroVehiculo.setListaAtributoDinamico((ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltroVehiculo.getListaAtributoDinamico()));
		}
		if(locFiltroVehiculo.getListaAtributoDinamico2() != null) {
			locFiltroVehiculo.setListaAtributoDinamico2((ArrayList) panelAtributoDinamico2.obtenerListaAtributosDinamicos(locFiltroVehiculo.getListaAtributoDinamico2()));
		}
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		FiltroVehiculo locFiltroVehiculo = this.getFiltro();

		if(locFiltroVehiculo.getPropietario() != null) {
			this.getTfPersonaSeleccionada().setText(locFiltroVehiculo.getPropietario().getToStringCompleto());
		}

		this.getTfPatente().setText(locFiltroVehiculo.getPatente());
		this.getTfMotor().setText(locFiltroVehiculo.getMotor());

		if(locFiltroVehiculo.getFechaInscripcion() != null) {
			this.getTfFechaInscripcion().setText(Conversor.getStringDeFechaCorta(locFiltroVehiculo.getFechaInscripcion()));
		}
		this.getTfCodigo().setText(locFiltroVehiculo.getCodigo());

		if(locFiltroVehiculo.getModelo() != null) {
			this.getTfModelo().setText(locFiltroVehiculo.getModelo());
		}

		panelAtributoDinamico = new PanelAtributoDinamico(locFiltroVehiculo.getListaAtributoDinamico(), "#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo}", "vehiculo");
		panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltroVehiculo.getListaAtributoDinamico());
		panelAtributoDinamico2 = new PanelAtributoDinamico(locFiltroVehiculo.getListaAtributoDinamico2(), "#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo}", "tituloPropiedad");
		panelAtributoDinamico2.establecerListaAtributosDinamicos(locFiltroVehiculo.getListaAtributoDinamico2());
	}

	@Override
	protected void limpiarObjetosUsados() {
		// CAMBIAR: Limpiar los textField y dropDown
		FiltroVehiculo locFiltro = this.getFiltro();
		locFiltro.setModelo(null);
		locFiltro.setPropietario(null);
		locFiltro.setPatente(null);
		locFiltro.setFechaInscripcion(null);
		locFiltro.setCodigo(null);
		locFiltro.setMotor(null);

		this.getTfPatente().setText("");
		this.getTfModelo().setText("");
		this.getTfPersonaSeleccionada().setText("");
		this.getTfFechaInscripcion().setText("");
		this.getTfCodigo().setText("");
		this.getTfMotor().setText("");
		panelAtributoDinamico.limpiarCampos();
		panelAtributoDinamico2.limpiarCampos();
	}

	public String btnSeleccionarModelo_action() {
		return navegarParaSeleccionar("AdminModelo");
	}

	@Override
	public String btnLimpiarPersona_action() {
		String retorno = null;

		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(this.getTfPersonaSeleccionada());
			FiltroVehiculo locFiltro = this.getFiltro();
			locFiltro.setPropietario(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}
	
	public String btnLimpiarModelo_action() {
		String retorno = null;

		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(this.getTfModelo());
			FiltroVehiculo locFiltro = this.getFiltro();
			locFiltro.setModelo(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpVehiculo();
	}

	@Override
	protected List getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaVehiculos();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaVehiculos(lista);
	}

	/*
	 * public void setPropiedadesTabla() { String width = null; int cantidadFilas = this.getApplicationBean1().getCantidadFilasTablasAdmin().intValue(); boolean
	 * paginar = false;
	 * 
	 * this.getTableRowGroup1().setRows(cantidadFilas); this.getTable1().setWidth(width); this.getTable1().setPaginateButton(paginar); }
	 */

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Vehiculo locVehiculo = (Vehiculo) pObject;
		this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(this.getSessionBean1().getLlave());
		locVehiculo = getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().getVehiculoPorId(locVehiculo.getIdVehiculo());
		return locVehiculo;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Veh\355culos";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminVehiculo";
	}

	public String btnAgregar_action() {
		return toAbm(new VehiculoModel().new AgregarController());
	}

	public String btnModificar_action() {
		return toAbm(new VehiculoModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return toAbm(new VehiculoModel().new EliminarController());
	}

	public String btnConsultar_action() {
		return toAbm(new VehiculoModel().new ConsultarController());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		FiltroVehiculo vehiculo = (FiltroVehiculo) pFiltro;
		return this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().findListaVehiculo(vehiculo);
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationHabilitacionesBean().getTablaVehiculo();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroVehiculo locFiltro = this.getFiltro();

		if(pObject instanceof Modelo) {
			Modelo locModelo = (Modelo) pObject;
			locFiltro.setModelo(locModelo);
		}
		if(pObject instanceof Persona) {
			Persona locPersona = (Persona) pObject;
			locFiltro.setPropietario(locPersona);
		}
	}

	// private ArrayList getListaDelCommunicationAtributosDinamicos() {
	// // CAMBIAR: Utilizar la Lista del Comunication que corresponda
	// return this.getComunicationBean().getListaAtributosDinamicosVehiculo();
	// }
	//
	// private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
	// // CAMBIAR: Utilizar la Lista del Comunication que corresponda
	// this.getComunicationBean().setListaAtributosDinamicosVehiculo(lista);
	// }

	@Override
	public long getSerialVersionUID() {
		return Vehiculo.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo}";
	}

	public void setModeloVehiculoAutocompletar(String pId, String pIdSubSesion) throws Exception { // aunque no se usa el ID de subsession
		FiltroVehiculo locFiltro = this.getFiltro();
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
	
	public void setPersonaAutocompletar(String pId, String pIdSubSesion) { // aunque no se usa el ID de subsession
		FiltroVehiculo locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Persona propietario = null;
		
		try {
			propietario = (Persona) this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}
		
		locFiltro.setPropietario(propietario);
		this.getSessionBean1().setPersonaSeleccionada(propietario);
	}
}