/*
 * AdminOfertaLicitacion.java
 *
 * Created on 25 de octubre de 2006, 13:16
 * Copyright Trascender SRL
 */

package muni.compras.ABMOfertaLicitacion;

import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.convert.DateTimeConverter;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.compras.recurso.persistent.suministros.OfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminOfertaLicitacion extends AdminPageBean {

	private Button btnSeleccionarProveedor = new Button();
	private Button btnLimpiarProveedor = new Button();
	private Button btnSeleccionarLicitacion = new Button();
	private Button btnLimpiarLicitacion = new Button();

	private StaticText stEstado = new StaticText();
	private StaticText stPlazo = new StaticText();
	private StaticText stProveedor = new StaticText();
	private StaticText stFechaOferta = new StaticText();
	private StaticText stImporte = new StaticText();

	private TableColumn tcProveedor = new TableColumn();
	private TableColumn tcFechaOferta = new TableColumn();
	private TableColumn tcPlazo = new TableColumn();
	private TableColumn tcEstado = new TableColumn();
	private TableColumn tcImporte = new TableColumn();

	private Label lblProveedor = new Label();
	private Label lblLicitacion = new Label();
	private Label lblObjeto = new Label();
	private Label lblTipoLicitacion = new Label();
	private Label lblEstadoLicitacion = new Label();
	private Label lblFechaPublicacion = new Label();
	private Label lblFechaAdjudicacion = new Label();
	private Label lblFechaOferta = new Label();
	private Label lblFormatoFechaOferta = new Label();

	private TextField tfLicitacion = new TextField();
	private TextField tfProveedor = new TextField();
	private TextField tfFechaOferta = new TextField();

	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();
	private ObjectListDataProvider ldpOfertasLicitaciones = new ObjectListDataProvider();

	@Override
	protected void _init() throws Exception {

		dateTimeConverter1.setTimeZone(null);
		dateTimeConverter1.setPattern("dd/MM/yy");
		dateTimeConverter1.setTimeStyle("full");
	}

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
		this.dateTimeConverter1 = dateTimeConverter1;
	}

	public Button getBtnSeleccionarProveedor() {
		return btnSeleccionarProveedor;
	}

	public void setBtnSeleccionarProveedor(Button btnSeleccionarProveedor) {
		this.btnSeleccionarProveedor = btnSeleccionarProveedor;
	}

	public Button getBtnLimpiarProveedor() {
		return btnLimpiarProveedor;
	}

	public void setBtnLimpiarProveedor(Button btnLimpiarProveedor) {
		this.btnLimpiarProveedor = btnLimpiarProveedor;
	}

	public Button getBtnSeleccionarLicitacion() {
		return btnSeleccionarLicitacion;
	}

	public void setBtnSeleccionarLicitacion(Button btnSeleccionarLicitacion) {
		this.btnSeleccionarLicitacion = btnSeleccionarLicitacion;
	}

	public Button getBtnLimpiarLicitacion() {
		return btnLimpiarLicitacion;
	}

	public void setBtnLimpiarLicitacion(Button btnLimpiarLicitacion) {
		this.btnLimpiarLicitacion = btnLimpiarLicitacion;
	}

	public StaticText getStEstado() {
		return stEstado;
	}

	public void setStEstado(StaticText stEstado) {
		this.stEstado = stEstado;
	}

	public StaticText getStPlazo() {
		return stPlazo;
	}

	public void setStPlazo(StaticText stPlazo) {
		this.stPlazo = stPlazo;
	}

	public ObjectListDataProvider getLdpOfertasLicitaciones() {
		return ldpOfertasLicitaciones;
	}

	public void setLdpOfertasLicitaciones(ObjectListDataProvider ldpOfertasLicitaciones) {
		this.ldpOfertasLicitaciones = ldpOfertasLicitaciones;
	}

	public TableColumn getTcProveedor() {
		return tcProveedor;
	}

	public void setTcProveedor(TableColumn tcProveedor) {
		this.tcProveedor = tcProveedor;
	}

	public TableColumn getTcFechaOferta() {
		return tcFechaOferta;
	}

	public void setTcFechaOferta(TableColumn tcFechaOferta) {
		this.tcFechaOferta = tcFechaOferta;
	}

	public TableColumn getTcPlazo() {
		return tcPlazo;
	}

	public void setTcPlazo(TableColumn tcPlazo) {
		this.tcPlazo = tcPlazo;
	}

	public TableColumn getTcEstado() {
		return tcEstado;
	}

	public void setTcEstado(TableColumn tcEstado) {
		this.tcEstado = tcEstado;
	}

	public TableColumn getTcImporte() {
		return tcImporte;
	}

	public void setTcImporte(TableColumn tcImporte) {
		this.tcImporte = tcImporte;
	}

	public StaticText getStProveedor() {
		return stProveedor;
	}

	public void setStProveedor(StaticText stProveedor) {
		this.stProveedor = stProveedor;
	}

	public StaticText getStFechaOferta() {
		return stFechaOferta;
	}

	public void setStFechaOferta(StaticText stFechaOferta) {
		this.stFechaOferta = stFechaOferta;
	}

	public StaticText getStImporte() {
		return stImporte;
	}

	public void setStImporte(StaticText stImporte) {
		this.stImporte = stImporte;
	}

	public Label getLblProveedor() {
		return lblProveedor;
	}

	public void setLblProveedor(Label lblProveedor) {
		this.lblProveedor = lblProveedor;
	}

	public Label getLblLicitacion() {
		return lblLicitacion;
	}

	public void setLblLicitacion(Label lblLicitacion) {
		this.lblLicitacion = lblLicitacion;
	}

	public TextField getTfLicitacion() {
		return tfLicitacion;
	}

	public void setTfLicitacion(TextField tfLicitacion) {
		this.tfLicitacion = tfLicitacion;
	}

	public TextField getTfProveedor() {
		return tfProveedor;
	}

	public void setTfProveedor(TextField tfProveedor) {
		this.tfProveedor = tfProveedor;
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

	public TextField getTfFechaOferta() {
		return tfFechaOferta;
	}

	public void setTfFechaOferta(TextField tfFechaOferta) {
		this.tfFechaOferta = tfFechaOferta;
	}

	public Label getLblFormatoFechaOferta() {
		return lblFormatoFechaOferta;
	}

	public void setLblFormatoFechaOferta(Label lblFormatoFechaOferta) {
		this.lblFormatoFechaOferta = lblFormatoFechaOferta;
	}

	public AdminOfertaLicitacion() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new OfertaContratacion());// 0
		ep.getObjetos().add(ind++, new Contratacion());// 1
		ep.getObjetos().add(ind++, new Proveedor());// 2

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		OfertaContratacion ofertaLicitacion = (OfertaContratacion) this.obtenerObjetoDelElementoPila(ind++, OfertaContratacion.class);
		Contratacion contratacion = (Contratacion) this.obtenerObjetoDelElementoPila(ind++, Contratacion.class);
		Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(ind++, Proveedor.class);

		Object fechaOferta = this.getTfFechaOferta().getText();

		if(fechaOferta != null && fechaOferta != "") {
			// ofertaLicitacion.setFechaOferta(Conversor.getFechaCortaDeString(ofertaLicitacion.toString()));
		} else {
			// ofertaLicitacion.setFechaOferta(null);
		}

		if(contratacion.getIdContratacion() == -1) {
			contratacion = null;
		}
		// ofertaLicitacion.setLicitacion(licitacion);

		if(proveedor.getIdProveedor() == -1) {
			proveedor = null;
		}
		ofertaLicitacion.setProveedor(proveedor);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, ofertaLicitacion);
		this.getElementoPila().getObjetos().set(ind++, contratacion);
		this.getElementoPila().getObjetos().set(ind++, proveedor);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		OfertaContratacion ofertaLicitacion = null;
		Contratacion contratacion = null;
		Proveedor proveedor = null;

		if(this.getRequestBean1().getObjetoSeleccion() != null) {
			Object seleccionado = this.getRequestBean1().getObjetoSeleccion();

			if(seleccionado instanceof Contratacion) {
				contratacion = (Contratacion) seleccionado;
				this.getElementoPila().getObjetos().set(1, contratacion);
				this.getRequestBean1().setObjetoSeleccion(null);
			} else if(seleccionado instanceof Proveedor) {
				proveedor = (Proveedor) seleccionado;
				this.getElementoPila().getObjetos().set(2, proveedor);
				this.getRequestBean1().setObjetoSeleccion(null);
			}
		}

		int ind = 0;
		ofertaLicitacion = (OfertaContratacion) this.obtenerObjetoDelElementoPila(ind++, OfertaContratacion.class);
		contratacion = (Contratacion) this.obtenerObjetoDelElementoPila(ind++, Contratacion.class);
		proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(ind++, Proveedor.class);

		if(contratacion != null && contratacion.getIdContratacion() != -1) {
			this.getTfLicitacion().setText(contratacion.toString());
		}
		if(proveedor != null && proveedor.getIdProveedor() != -1) {
			this.getTfProveedor().setText(proveedor.toString());
		}
		// this.getTfFechaOferta().setText(ofertaLicitacion.getActaApertura().getFechaApertura());

		// if (this.getLdpOfertasLicitaciones().getList() != null){
		// Long filaSeleccionada = new Long(this.getElementoPila().getPosicionGlobal());
		// System.out.println("filaSeleccionada :" +filaSeleccionada);
		// this.seleccionarFila(filaSeleccionada);
		// }
	}

	@Override
	protected void refrescarTabla() throws Exception {
		// CAMBIAR: Segun objeto de busqueda.
		OfertaContratacion ofertaLicitacion = (OfertaContratacion) this.obtenerObjetoDelElementoPila(0, OfertaContratacion.class);
		// ofertaLicitacion.get
		/*
		 * Contratacion contratacion = (Contratacion) ofertaLicitacion.getActaApertura().getLicitacion(); if (ofertaLicitacion.getActaApertura().getLicitacion()
		 * != null && ofertaLicitacion.getActaApertura().getLicitacion().getIdContratacion() == -1) { contratacion = null; }
		 */
		try {
			this.getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().setLlave(this.getSessionBean1().getLlave());
			// this.setListaDelCommunication(this.getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().findListaOfertaLicitacion(contratacion,
			// ofertaLicitacion.getActaApertura().getFechaApertura(), ofertaLicitacion.getProveedor()));
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.getObjectListDataProvider().setList(this.getListaDelCommunication());

		OfertaContratacion prueba = null;
		for(Iterator it = this.getListaDelCommunication().iterator(); it.hasNext();) {
			Object object = it.next();
			prueba = (OfertaContratacion) object;
		}
		this.setRBSelected((new Long(0)).toString());
	}

	@Override
	protected void limpiarObjetosUsados() {
		for(int i = 0; i < this.getElementoPila().getObjetos().size(); i++) {
			this.getElementoPila().getObjetos().set(i, null);
		}

		// CAMBIAR: Limpiar los textField y los dropDown
		this.getTfLicitacion().setText(null);
		this.getTfProveedor().setText(null);
		this.getTfFechaOferta().setText(null);

	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpOfertasLicitaciones();
	}

	@Override
	protected List getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationComprasBean().getListaOfertasLicitaciones();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationComprasBean().setListaOfertasLicitaciones(lista);
	}

	@Override
	public String btnBuscar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			try {

				Validador v = new Validador();

				UIComponent[] fecha = new UIComponent[1];
				String[] nomFecha = new String[1];

				int pos1 = 0;
				fecha[pos1] = this.getTfFechaOferta();
				nomFecha[pos1++] = "Fecha Oferta";

				v.formatoFechaValido(fecha, nomFecha);

				if(v.getErrores().size() > 0) {
					error("Existen Errores:");
					for(int i = 0; i < v.getErrores().size(); i++) {
						warn(v.getErrores().toArray()[i].toString());
					}
					return null;
				}

			} catch(Exception ex) {
				this.limpiarTabla();
				log(this.getCasoNavegacion() + "_BuscarError:", ex);
				error(this.getNombrePagina() + " - Buscar: " + ex.getMessage());
				this.getRequestBean1().setAccion(Constantes.ACCION_RSLT_ERROR);
			}

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarLicitacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminLicitacion";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarLicitacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.limpiarObjeto(this.getTfLicitacion());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarProveedor_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			// CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
			retorno = "AdminProveedor";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarProveedor_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.limpiarObjeto(this.getTfProveedor());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		OfertaContratacion locOfertaLicitacion = (OfertaContratacion) pObject;
		this.getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().setLlave(this.getSessionBean1().getLlave());
		locOfertaLicitacion = this.getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().getOfertaLicitacionPorId(locOfertaLicitacion.getIdOfertaContratacion());
		return locOfertaLicitacion;
	}

	public String btnAgregar_action() {
		return toAbm(new OfertaLicitacionModel().new AgregarController());
	}

	public String btnModificar_action() {
		return toAbm(new OfertaLicitacionModel().new ModificarController());
	}

	public String btnEliminar_action() {
		return toAbm(new OfertaLicitacionModel().new EliminarController());
	}

	public String btnConsultar_action() {
		return toAbm(new OfertaLicitacionModel().new ConsultarController());
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Ofertas de Licitacion";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminOfertaLicitacion";
	}

	@Override
	protected void _prerender() throws Exception {

	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getNombreBean() {
		return "#{compras$ABMOfertaLicitacion$AdminOfertaLicitacion}";
	}

	@Override
	public long getSerialVersionUID() {
		return OfertaContratacion.serialVersionUID;
	}
}