/*
 * AdminCoeficienteDepreciacion.java
 *
 * Created on 27 de octubre de 2006, 10:16
 * Copyright Trascender SRL
 */
package muni.catastro.ABMCoeficienteDepreciacion;

import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.catastro.recurso.filtros.FiltroCategoria;
import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion;
import com.trascender.catastro.recurso.persistent.TipoConstruccion;
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
public class AdminCoeficienteDepreciacion extends AdminPageBean {

	@Override
	protected void _init() throws Exception {
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		this.habilitarBtnExportar();
	}

	private HtmlAjaxCommandButton btnLimpiarTipoConstruccion = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarTipoConstruccion() {
		return btnLimpiarTipoConstruccion;
	}

	public void setBtnLimpiarTipoConstruccion(HtmlAjaxCommandButton btnLimpiarTipoConstruccion) {
		this.btnLimpiarTipoConstruccion = btnLimpiarTipoConstruccion;
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

	private ObjectListDataProvider ldpCategoriaCoeficiente = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpCategoriaCoeficiente() {
		return ldpCategoriaCoeficiente;
	}

	public void setLdpCategoriaCoeficiente(ObjectListDataProvider oldp) {
		this.ldpCategoriaCoeficiente = oldp;
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

	private TableColumn tableColumn4 = new TableColumn();

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tc) {
		this.tableColumn4 = tc;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private TextField tfCodigo = new TextField();

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tf) {
		this.tfCodigo = tf;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private TextField tfTipoConstruccion = new TextField();

	public TextField getTfTipoConstruccion() {
		return tfTipoConstruccion;
	}

	public void setTfTipoConstruccion(TextField tf) {
		this.tfTipoConstruccion = tf;
	}

	private Button btnSeleccionarTipoConstruccion1 = new Button();

	public Button getBtnSeleccionarTipoConstruccion1() {
		return btnSeleccionarTipoConstruccion1;
	}

	public void setBtnSeleccionarTipoConstruccion1(Button b) {
		this.btnSeleccionarTipoConstruccion1 = b;
	}

	private TableColumn tableColumn6 = new TableColumn();

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tc) {
		this.tableColumn6 = tc;
	}

	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText staticText9) {
		this.staticText9 = staticText9;
	}

	public AdminCoeficienteDepreciacion() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroCategoria locFiltro = this.getFiltro();

		locFiltro.setCodigoCategoria(this.getTextFieldValueInteger(getTfCodigo()));
		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroCategoria locFiltro = this.getFiltro();

		if (locFiltro.getCodigoCategoria() != null) {
			this.getTfCodigo().setText(locFiltro.getCodigoCategoria().toString());
		}
		this.getTfNombre().setText(locFiltro.getNombre());
		if (locFiltro.getTipoConstruccion() != null) {
			this.getTfTipoConstruccion().setText(locFiltro.getTipoConstruccion().toString());
		}
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(this.getSessionBean1().getLlave());
		FiltroCategoria locFiltro = this.getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().findListaCategorias((FiltroCategoria) pFiltro);
		return locFiltro;
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroCategoria locFiltro = this.getFiltro();
		locFiltro.setNombre(null);
		locFiltro.setCodigoCategoria(null);
		locFiltro.setTipoConstruccion(null);

		this.getTfNombre().setText("");
		this.getTfCodigo().setText("");
		this.getTfTipoConstruccion().setText("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpCategoriaCoeficiente();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaCategoriaCoeficientesDepreciacion();
	}

	// </editor-fold>
	public String btnSeleccionarTipoConstruccion_action() {
		return navegarParaSeleccionar("AdminTipoConstruccion");
	}

	public String btnAgregar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			try {
				RowKey rk = this.getSeleccionado();
				if (rk != null) {
					this.setRowKeySeleccionado(this.getSeleccionado());
				}
			} catch (Exception ex) {
				log("AdminCoeficienteDepreciacion" + "_AgregarError:", ex);
				error("Administraci\363n de Coeficientes de Depreciaci\363n" + " - Agregar: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			retorno = "AgregarCoeficienteDepreciacion";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnModificar_action() {
		return toAbm(new CoeficienteDepreciacionModel().new ModificarCoeficienteDepreciacionController());
	}

	public String btnEliminar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			RowKey rk = null;
			try {

				rk = this.getSeleccionado();

				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					getRequestBean1().setObjetoABM(obj);

					this.setRowKeySeleccionado(this.getSeleccionado());
				}

			} catch (Exception ex) {
				log("AdminCoeficienteDepreciacion" + "_EliminarError:", ex);
				error("Administraci\363n de Coeficientes de Depreciaci\363n" + " - Eliminar: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			if (rk != null) {
				retorno = "EliminarCoeficienteDepreciacion";
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	// **Mines: boton Consultar...*/
	public String btnConsultar_action() {
		return toAbm(new CoeficienteDepreciacionModel().new ConsultarCoeficienteDepreciacionController());
	}

	public String btnLimpiarTipoConstruccion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.limpiarObjeto(getTfTipoConstruccion());
			FiltroCategoria locFiltro = this.getFiltro();
			locFiltro.setTipoConstruccion(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationCatastroBean().setListaCategoriaCoeficientesDepreciacion(lista);
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Categoria locCategoria = (Categoria) pObject;
		getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(getSessionBean1().getLlave());
		locCategoria = getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().getCategoriaPorId(locCategoria.getIdCategoria());
		return locCategoria;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Coeficientes de Depreciaci\363n";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminCoeficienteDepreciacion";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationCatastroBean().getTablaCoeficienteDepreciacion();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroCategoria locFiltro = this.getFiltro();
		if (pObject instanceof TipoConstruccion) {
			TipoConstruccion tipo = (TipoConstruccion) pObject;
			locFiltro.setTipoConstruccion(tipo);
		}
	}
	
	@Override
	public long getSerialVersionUID() {
		return CoeficienteDepreciacion.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion}";
	}
}