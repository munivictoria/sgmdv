/*
 * AdminDeposito.java
 *
 * Created on 18 de octubre de 2006, 8:55
 * Copyright Trascender SRL
 */

package muni.inventario.ABMArticulo;

import java.util.List;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.filtros.FiltroArticulo;
import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminArticulo extends AdminPageBean {

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		Option[] opEstadoContable = null;
		opEstadoContable = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Articulo.EstadoContable.values(), "may");
		ddEstadoContableDefaultOptions.setOptions(opEstadoContable);

		this.habilitarBtnExportar();
	}

	private Button btnActivar = new Button();

	public Button getBtnActivar() {
		return btnActivar;
	}

	public void setBtnActivar(Button btnActivar) {
		this.btnActivar = btnActivar;
	}

	private ObjectListDataProvider ldpArticulos = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpArticulos() {
		return ldpArticulos;
	}

	public void setLdpArticulos(ObjectListDataProvider ldpArticulos) {
		this.ldpArticulos = ldpArticulos;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label label1) {
		this.label1 = label1;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label label2) {
		this.label2 = label2;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label label3) {
		this.label3 = label3;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label label4) {
		this.label4 = label4;
	}

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label label5) {
		this.label5 = label5;
	}

	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label label6) {
		this.label6 = label6;
	}

	private StaticText stColNombre = new StaticText();

	public StaticText getStColNombre() {
		return stColNombre;
	}

	public void setStColNombre(StaticText stColNombre) {
		this.stColNombre = stColNombre;
	}

	private StaticText stColFechaCompra = new StaticText();

	public StaticText getStColFechaCompra() {
		return stColFechaCompra;
	}

	public void setStColFechaCompra(StaticText stColFechaCompra) {
		this.stColFechaCompra = stColFechaCompra;
	}

	private StaticText stColFechaPuestaServicio = new StaticText();

	public StaticText getStColFechaPuestaServicio() {
		return stColFechaPuestaServicio;
	}

	public void setStColFechaPuestaServicio(StaticText stColFechaPuestaServicio) {
		this.stColFechaPuestaServicio = stColFechaPuestaServicio;
	}

	private StaticText stColPaseArticulo = new StaticText();

	public StaticText getStColPaseArticulo() {
		return stColPaseArticulo;
	}

	public void setStColPaseArticulo(StaticText stColPaseArticulo) {
		this.stColPaseArticulo = stColPaseArticulo;
	}

	private DropDown ddEstadoContable = new DropDown();

	public DropDown getDdEstadoContable() {
		return ddEstadoContable;
	}

	public void setDdEstadoContable(DropDown ddEstadoContable) {
		this.ddEstadoContable = ddEstadoContable;
	}

	private SingleSelectOptionsList ddEstadoContableDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdEstadoContableDefaultOptions() {
		return ddEstadoContableDefaultOptions;
	}

	public void setDdEstadoContableDefaultOptions(SingleSelectOptionsList ddEstadoContableDefaultOptions) {
		this.ddEstadoContableDefaultOptions = ddEstadoContableDefaultOptions;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	private TextField tfCodigo = new TextField();

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tfCodigo) {
		this.tfCodigo = tfCodigo;
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	private TextField tfFechaCompra = new TextField();

	public TextField getTfFechaCompra() {
		return tfFechaCompra;
	}

	public void setTfFechaCompra(TextField tfFechaCompra) {
		this.tfFechaCompra = tfFechaCompra;
	}

	private TextField tfFechaEntradaServicio = new TextField();

	public TextField getTfFechaEntradaServicio() {
		return tfFechaEntradaServicio;
	}

	public void setTfFechaEntradaServicio(TextField tfFechaEntradaServicio) {
		this.tfFechaEntradaServicio = tfFechaEntradaServicio;
	}

	private TextField tfArea = new TextField();

	public TextField getTfArea() {
		return tfArea;
	}

	public void setTfArea(TextField tfArea) {
		this.tfArea = tfArea;
	}

	private Button btnSeleccionarArea = new Button();

	public Button getBtnSeleccionarArea() {
		return btnSeleccionarArea;
	}

	public void setBtnSeleccionarArea(Button btnSeleccionarArea) {
		this.btnSeleccionarArea = btnSeleccionarArea;
	}

	private Button btnLimpiarArea = new Button();

	public Button getBtnLimpiarArea() {
		return btnLimpiarArea;
	}

	public void setBtnLimpiarArea(Button btnLimpiarArea) {
		this.btnLimpiarArea = btnLimpiarArea;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText pStaticText2) {
		this.staticText2 = pStaticText2;
	}

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText staticText5) {
		this.staticText5 = staticText5;
	}

	private StaticText staticText6 = new StaticText();

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText pStaticText6) {
		this.staticText6 = pStaticText6;
	}

	private StaticText staticText8 = new StaticText();

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText pStaticText8) {
		this.staticText8 = pStaticText8;
	}

	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText staticText9) {
		this.staticText9 = staticText9;
	}

	private StaticText staticText10 = new StaticText();

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText staticText10) {
		this.staticText10 = staticText10;
	}

	private StaticText stColCodigo = new StaticText();

	public StaticText getStColCodigo() {
		return stColCodigo;
	}

	public void setStColCodigo(StaticText stColCodigo) {
		this.stColCodigo = stColCodigo;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText pTableColumn3) {
		this.staticText3 = pTableColumn3;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText pStaticText4) {
		this.staticText4 = pStaticText4;
	}

	private Button btnAgregarPaseArticulo = new Button();

	public Button getBtnAgregarPaseArticulo() {
		return btnAgregarPaseArticulo;
	}

	public void setBtnAgregarPaseArticulo(Button btnAgregarPaseArticulo) {
		this.btnAgregarPaseArticulo = btnAgregarPaseArticulo;
	}

	// </editor-fold>
	/**
	 * <p>
	 * Construir una instancia de bean de página.
	 * </p>
	 */
	public AdminArticulo() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroArticulo locFiltro = this.getFiltro();

		locFiltro.setCodigo(getTextFieldValue(this.getTfCodigo()));
		locFiltro.setNombre(getTextFieldValue(this.getTfNombre()));
		locFiltro.setFechaEntradaServicio(getTextFieldValueDate(this.getTfFechaEntradaServicio()));
		locFiltro.setFechaCompra(getTextFieldValueDate(this.getTfFechaCompra()));
		locFiltro.setEstadoContable(getDDEnumValue(this.getDdEstadoContable(), Articulo.EstadoContable.class));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroArticulo locFiltro = this.getFiltro();

		this.getTfCodigo().setText(locFiltro.getCodigo());
		this.getTfNombre().setText(locFiltro.getNombre());
		this.getTfFechaCompra().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaCompra()));
		this.getTfFechaEntradaServicio().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaEntradaServicio()));
		this.getTfArea().setText(locFiltro.getArea());
		this.getDdEstadoContable().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstadoContable() == null ? "" : locFiltro.getEstadoContable())));
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroArticulo locFiltro = this.getFiltro();
		locFiltro.setCodigo("");
		locFiltro.setNombre("");
		locFiltro.setFechaCompra(null);
		locFiltro.setFechaEntradaServicio(null);
		locFiltro.setArea(null);
		// CAMBIAR: Limpiar los textField y dropDown
		this.getTfCodigo().setText("");
		this.getTfNombre().setText("");
		this.getTfFechaCompra().setText("");
		this.getTfFechaEntradaServicio().setText("");
		// this.getTfArea().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpArticulos();
	}

	@Override
	protected List getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationComprasBean().getListaArticulos();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationComprasBean().setListaArticulos(lista);
	}

	public String btnAgregar_action() {
		return toAbm(new ArticuloModel().new AgregarArticuloController());
	}

	public String btnModificar_action() {
		return toAbm(new ArticuloModel().new ModificarArticuloController());
	}

	public String btnEliminar_action() {
		return toAbm(new ArticuloModel().new EliminarArticuloController());
	}

	public String btnConsultar_action() {
		return toAbm(new ArticuloModel().new ConsultarArticuloController());
	}

	public String btnAgregarPaseArticulo_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {

				rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					Articulo articulo = (Articulo) obj;

					this.getCommunicationComprasBean().getRemoteSystemStock().setLlave(this.getSessionBean1().getLlave());
					articulo = this.getCommunicationComprasBean().getRemoteSystemStock().getArticuloPorId(articulo.getIdArticulo());
					getRequestBean1().setObjetoABM(articulo);
					this.setRowKeySeleccionado(this.getSeleccionado());
				}

			} catch(Exception ex) {
				log(getCasoNavegacion() + "_PaseArticuloError:", ex);
				error(getNombrePagina() + " - Pase de Area: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			if(rk != null) {
				retorno = "AgregarPaseArticulo";
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarArea_action() {
		return navegarParaSeleccionar("AdminArea");
	}

	public String btnLimpiarArea_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(getTfArea());
			FiltroArticulo locFiltro = this.getFiltro();
			locFiltro.setArea(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Articulo articulo = (Articulo) pObject;
		this.getCommunicationComprasBean().getRemoteSystemStock().setLlave(this.getSessionBean1().getLlave());
		articulo = this.getCommunicationComprasBean().getRemoteSystemStock().getArticuloPorId(articulo.getIdArticulo());
		return articulo;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationComprasBean().getRemoteSystemStock().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationComprasBean().getRemoteSystemStock().findListaArticulo((FiltroArticulo) pFiltro);
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroArticulo locFiltro = this.getFiltro();

		if(pObject instanceof Area) {
			locFiltro.setArea((Area) pObject);
		}
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationComprasBean().getTablaArticulo();
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Art\355culos";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminArticulo";
	}

	@Override
	public String getNombreBean() {
		return "#{inventario$ABMArticulo$AdminArticulo}";
	}

	@Override
	public long getSerialVersionUID() {
		return Articulo.serialVersionUID;
	}
}