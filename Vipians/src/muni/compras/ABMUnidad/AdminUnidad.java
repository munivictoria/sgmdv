/*
 * AdminUnidad.java
 *
 * 
 * Copyright Trascencer
 */
package muni.compras.ABMUnidad;

import java.util.List;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.compras.recurso.filtros.FiltroUnidad;
import com.trascender.compras.recurso.persistent.suministros.Unidad;
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
public class AdminUnidad extends AdminPageBean {

	protected Label lblDescripcion = new Label();

	protected Button btnImprimirReporte = new Button();

	protected TextField tfDescripcion = new TextField();

	protected StaticText stSeparador5 = new StaticText();
	protected StaticText stDescripcion = new StaticText();

	protected TableColumn tcDescripcion = new TableColumn();
	protected ObjectListDataProvider ldpUnidades = new ObjectListDataProvider();

	public Label getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(Label lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button b) {
		this.btnImprimirReporte = b;
	}

	public StaticText getStSeparador5() {
		return stSeparador5;
	}

	public void setStSeparador5(StaticText stSeparador5) {
		this.stSeparador5 = stSeparador5;
	}

	public AdminUnidad() {
	}

	public ObjectListDataProvider getLdpUnidades() {
		return ldpUnidades;
	}

	public void setLdpUnidades(ObjectListDataProvider oldp) {
		this.ldpUnidades = oldp;
	}

	public TableColumn getTcDescripcion() {
		return tcDescripcion;
	}

	public void setTcDescripcion(TableColumn tcDescripcion) {
		this.tcDescripcion = tcDescripcion;
	}

	public StaticText getStDescripcion() {
		return stDescripcion;
	}

	public void setStDescripcion(StaticText stDescripcion) {
		this.stDescripcion = stDescripcion;
	}

	public TextField getTfDescripcion() {
		return tfDescripcion;
	}

	public void setTfDescripcion(TextField tf) {
		this.tfDescripcion = tf;
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationComprasBean().getTablaUnidad();
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroUnidad locFiltro = this.getFiltro();
		
		locFiltro.setDescripcion(this.getTextFieldValue(getTfDescripcion()));
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroUnidad locFiltro = this.getFiltro();
		
		this.getTfDescripcion().setText(locFiltro.getDescripcion());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationComprasBean().getRemoteSystemAdministracionBienes().findListaUnidad((FiltroUnidad) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroUnidad locFiltro = this.getFiltro();
		locFiltro.setDescripcion(null);
		
		this.getTfDescripcion().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpUnidades();
	}

	public String btnImprimirReporte_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();

				if (rk != null) {
					int index = this.getNroFila(rk.toString());

					Object obj = this.getObjectListDataProvider().getObjects()[index];
					this.getSessionBean1().setObjetoImpresion(obj);

					this.setRowKeySeleccionado(this.getSeleccionado());
				}
			} catch (Exception e) {
				log(this.getCasoNavegacion() + "_ImprimirError: ", e);
				error(this.getNombrePagina() + " - Imprimir: " + e.getMessage());
			}
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaUnidades();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationComprasBean().setListaUnidades(lista);
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Unidad locUnidad = (Unidad) pObject;
		this.getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(this.getSessionBean1().getLlave());
		locUnidad = this.getCommunicationComprasBean().getRemoteSystemAdministracionBienes().getUnidadByID(locUnidad.getIdUnidad());
		return locUnidad;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Unidades de Medida";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminUnidad";
	}

	public String btnAgregar_action() {
		return toAbm(new UnidadModel().new AgregarController());
	}

	public String btnModificar_action() {
		return toAbm(new UnidadModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return toAbm(new UnidadModel().new EliminarController());
	}

	public String btnConsultar_action() {
		return toAbm(new UnidadModel().new ConsultarController());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}
	
	@Override
	public long getSerialVersionUID() {
		return Unidad.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMUnidad$AdminUnidad}";
	}
}