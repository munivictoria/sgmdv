/*
g * AdminMunicipalidad.java
 *
 * Created on 20 de octubre de 2006, 07:32
 * Copyright Trascender
 */
package muni.framework.ABMMunicipalidad;

import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.framework.recurso.filtros.FiltroMunicipalidad;
import com.trascender.framework.recurso.persistent.Municipalidad;
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
public class AdminMunicipalidad extends AdminPageBean {
	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong>
	 * This method is automatically generated, so any user-specified code
	 * inserted here is subject to being replaced.
	 * </p>
	 */
	@Override
	protected void _init() throws Exception {
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
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

	private ObjectListDataProvider ldpMunicipalidad = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpMunicipalidad() {
		return ldpMunicipalidad;
	}

	public void setLdpMunicipalidad(ObjectListDataProvider oldp) {
		this.ldpMunicipalidad = oldp;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private TableColumn tableColumn3 = new TableColumn();

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tc) {
		this.tableColumn3 = tc;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public AdminMunicipalidad() {
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroMunicipalidad locFiltro = getFiltro();

		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroMunicipalidad locFiltro = getFiltro();

		this.getTfNombre().setText(locFiltro.getNombre());

		// if (this.getLdpMunicipalidad().getList() != null){
		// Long filaSeleccionada = new
		// Long(this.getElementoPila().getPosicionGlobal());
		// this.seleccionarFila(filaSeleccionada);
		// }
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroMunicipalidad locFiltro = getFiltro();

		locFiltro.setNombre(null);

		this.getTfNombre().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpMunicipalidad();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getComunicationBean().getListaMunicipalidades();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaMunicipalidades((ArrayList) lista);
	}

	public String btnAgregar_action() {
		return null;

		// String retorno = null;
		// boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		//
		// if (ultimo) {
		//
		// try {
		// RowKey rk = this.getSeleccionado();
		// if (rk != null) {
		// this.setRowKeySeleccionado(this.getSeleccionado());
		// }
		// } catch (Exception ex) {
		// log(CASO_NAVEGACION+"_AgregarError:", ex);
		// error(NOMBRE_PAGINA+" - Agregar: " + ex.getMessage());
		// }
		//
		// this.guardarEstadoObjetosUsados();
		// this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
		//
		// this.guardarOrdenamiento();
		// Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
		//
		// this.getElementoPila().setPosicionGlobal(pos.longValue());
		//
		// retorno = lnkAgregar;
		// } else {
		// retorno = this.prepararCaducidad();
		// }
		//
		// return retorno;
	}

	public String btnModificar_action() {
		return toAbm(new MunicipalidadModel().new ModificarMunicipalidadController());
	}

	public String btnEliminar_action() {
		return null;
		// String retorno = null;
		// boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		//
		// if (ultimo) {
		// RowKey rk = null;
		//
		// try {
		//
		// rk = this.getSeleccionado();
		//
		// if (rk != null) {
		// int index = getNroFila(rk.toString());
		// // CAMBIAR: Utilizar el ListDataProvider adecuado.
		// Object obj = this.getObjectListDataProvider().getObjects()[index];
		// getRequestBean1().setObjetoABM(obj);
		//
		// this.setRowKeySeleccionado(this.getSeleccionado());
		// }
		//
		// } catch (Exception ex) {
		// log(CASO_NAVEGACION+"_EliminarError:", ex);
		// error(NOMBRE_PAGINA+" - Eliminar: " + ex.getMessage());
		// }
		//
		// this.guardarEstadoObjetosUsados();
		// this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
		//
		// this.guardarOrdenamiento();
		// Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
		// this.getElementoPila().setPosicionGlobal(pos.longValue());
		//
		// if (rk != null) retorno = lnkEliminar;
		// } else {
		// retorno = this.prepararCaducidad();
		// }
		//
		// return retorno;
	}

	Municipalidad municipalidadABuscar;

	public Municipalidad getMunicipalidadABuscar() {
		if (this.municipalidadABuscar == null)
			this.municipalidadABuscar = new Municipalidad();
		return municipalidadABuscar;
	}

	public void setMunicipalidadABuscar(Municipalidad municipalidadABuscar) {
		this.municipalidadABuscar = municipalidadABuscar;
	}

	public String btnConsultar_action() {
		return toAbm(new MunicipalidadModel().new ConsultarMunicipalidadController());
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		return pObject;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Municipalidades";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminMunicipalidad";
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemMunicipalidad().getMunicipalidad((FiltroMunicipalidad) pFiltro);
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationBean().getTablaMunicipalidad();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}
	
	@Override
	public long getSerialVersionUID() {
		return Municipalidad.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMMunicipalidad$AdminMunicipalidad}";
	}
}