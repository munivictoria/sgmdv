/*
 * AdminBien.java
 *
 * Created on 21 de noviembre de 2006, 11:19
 * Copyright Trascencer
 */
package muni.compras.ABMBien;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

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
import com.trascender.compras.recurso.filtros.FiltroBien;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.Unidad;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
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
public class AdminBien extends AdminPageBean {

	@Override
	protected void _init() throws Exception {
		Option[] opEstado = null;
		Option[] opTipo = null;
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(Bien.Estado.values(), "may");
		opTipo = this.armarArrayOptionsList(Bien.Tipo.values(), "");
		ddEstadoDefaultOptions.setOptions(opEstado);
		ddTipoDefaultOptions.setOptions(opTipo);

		Set<String> locListaUnidades = getApplicationBean1().getMapaUnidad().keySet();

		Option[] opUnidad = new Option[locListaUnidades.size() + 1];
		int i = 0;
		opUnidad[i++] = new Option("", "");
		for (String cadaUnidad : locListaUnidades) {
			opUnidad[i++] = new Option(cadaUnidad, cadaUnidad);
		}
		this.ddUnidadDefaultOptions.setOptions(opUnidad);

		Set<String> locListaTipoBien = getApplicationBean1().getMapaTipoBien().keySet();

		Option[] opTipoBien = new Option[locListaTipoBien.size() + 1];
		int j = 0;
		opTipoBien[j++] = new Option("", "");
		for (String cadaTipo : locListaTipoBien) {
			opTipoBien[j++] = new Option(cadaTipo, cadaTipo);
		}
		this.ddTipoBienDefaultOptions.setOptions(opTipoBien);
	}

	private Button btnSeleccionarCodigoCiiu = new Button();
	private HtmlAjaxCommandButton btnLimpiarCodigoCiiu = new HtmlAjaxCommandButton();
	private Label lblCodigoCiiu = new Label();
	private TextField tfCodigoCiiu = new TextField();

	private Button btnSeleccionarUnidad = new Button();
	private HtmlAjaxCommandButton btnLimpiarUnidad = new HtmlAjaxCommandButton();
	private Label lblUnidad = new Label();
	private DropDown ddUnidad = new DropDown();
	private SingleSelectOptionsList ddUnidadDefaultOptions = new SingleSelectOptionsList();

	private Label lblTipo = new Label();
	private DropDown ddTipo = new DropDown();
	private SingleSelectOptionsList ddTipoDefaultOptions = new SingleSelectOptionsList();

	private Label lblTipoBien = new Label();
	private DropDown ddTipoBien = new DropDown();
	private SingleSelectOptionsList ddTipoBienDefaultOptions = new SingleSelectOptionsList();

	private ObjectListDataProvider ldpBienes = new ObjectListDataProvider();
	private TableColumn tcNombre = new TableColumn();
	private TableColumn tcUnidadMedida = new TableColumn();
	private TableColumn tcDescripcion = new TableColumn();
	private TableColumn tcEstado = new TableColumn();

	private StaticText stNombre = new StaticText();
	private Label lblNombre = new Label();
	private TextField tfNombre = new TextField();
	private TextField tfDescripcion = new TextField();

	private Label lblEstado = new Label();
	private Label lblGrupoBienes = new Label();
	private Label lblDescripcion = new Label();

	private StaticText stUnidadMedida = new StaticText();
	private StaticText stDescripcion = new StaticText();
	private StaticText stEstado = new StaticText();
	private StaticText stSeparador5 = new StaticText();
	private StaticText stFiltrarPor = new StaticText();

	private Button btnActivar = new Button();
	private Button btnImprimirReporte = new Button();

	private DropDown ddEstado = new DropDown();
	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();
	
	public HtmlAjaxCommandButton getBtnLimpiarCodigoCiiu() {
		return btnLimpiarCodigoCiiu;
	}

	public void setBtnLimpiarCodigoCiiu(HtmlAjaxCommandButton btnLimpiarCodigoCiiu) {
		this.btnLimpiarCodigoCiiu = btnLimpiarCodigoCiiu;
	}

	public HtmlAjaxCommandButton getBtnLimpiarUnidad() {
		return btnLimpiarUnidad;
	}

	public void setBtnLimpiarUnidad(HtmlAjaxCommandButton btnLimpiarUnidad) {
		this.btnLimpiarUnidad = btnLimpiarUnidad;
	}

	public StaticText getStFiltrarPor() {
		return stFiltrarPor;
	}

	public void setStFiltrarPor(StaticText stFiltrarPor) {
		this.stFiltrarPor = stFiltrarPor;
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

	public SingleSelectOptionsList getDdUnidadDefaultOptions() {
		return ddUnidadDefaultOptions;
	}

	public void setDdUnidadDefaultOptions(SingleSelectOptionsList ddUnidadDefaultOptions) {
		this.ddUnidadDefaultOptions = ddUnidadDefaultOptions;
	}

	public Button getBtnSeleccionarUnidad() {
		return btnSeleccionarUnidad;
	}

	public void setBtnSeleccionarUnidad(Button btnSeleccionarUnidad) {
		this.btnSeleccionarUnidad = btnSeleccionarUnidad;
	}

	public Label getLblUnidad() {
		return lblUnidad;
	}

	public void setLblUnidad(Label lblUnidad) {
		this.lblUnidad = lblUnidad;
	}

	public DropDown getDdUnidad() {
		return ddUnidad;
	}

	public void setDdUnidad(DropDown ddUnidad) {
		this.ddUnidad = ddUnidad;
	}

	public Button getBtnSeleccionarCodigoCiiu() {
		return btnSeleccionarCodigoCiiu;
	}

	public void setBtnSeleccionarCodigoCiiu(Button btnSeleccionarCodigoCiiu) {
		this.btnSeleccionarCodigoCiiu = btnSeleccionarCodigoCiiu;
	}

	public Label getLblCodigoCiiu() {
		return lblCodigoCiiu;
	}

	public void setLblCodigoCiiu(Label lblCodigoCiiu) {
		this.lblCodigoCiiu = lblCodigoCiiu;
	}

	public TextField getTfCodigoCiiu() {
		return tfCodigoCiiu;
	}

	public void setTfCodigoCiiu(TextField tfCodigoCiiu) {
		this.tfCodigoCiiu = tfCodigoCiiu;
	}

	public Label getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(Label lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	public TextField getTfDescripcion() {
		return tfDescripcion;
	}

	public void setTfDescripcion(TextField tfDescripcion) {
		this.tfDescripcion = tfDescripcion;
	}

	public StaticText getStDescripcion() {
		return stDescripcion;
	}

	public void setStDescripcion(StaticText stDescripcion) {
		this.stDescripcion = stDescripcion;
	}

	public StaticText getStEstado() {
		return stEstado;
	}

	public void setStEstado(StaticText stEstado) {
		this.stEstado = stEstado;
	}

	public StaticText getStUnidadMedida() {
		return stUnidadMedida;
	}

	public void setStUnidadMedida(StaticText stUnidadMedida) {
		this.stUnidadMedida = stUnidadMedida;
	}

	public TableColumn getTcDescripcion() {
		return tcDescripcion;
	}

	public void setTcDescripcion(TableColumn tcDescripcion) {
		this.tcDescripcion = tcDescripcion;
	}

	public TableColumn getTcEstado() {
		return tcEstado;
	}

	public void setTcEstado(TableColumn tcEstado) {
		this.tcEstado = tcEstado;
	}

	public TableColumn getTcUnidadMedida() {
		return tcUnidadMedida;
	}

	public void setTcUnidadMedida(TableColumn tcUnidadMedida) {
		this.tcUnidadMedida = tcUnidadMedida;
	}

	public Label getLblGrupoBienes() {
		return lblGrupoBienes;
	}

	public void setLblGrupoBienes(Label lblGrupoBienes) {
		this.lblGrupoBienes = lblGrupoBienes;
	}

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	public DropDown getDdEstado() {
		return ddEstado;
	}

	public void setDdEstado(DropDown ddEstado) {
		this.ddEstado = ddEstado;
	}

	public SingleSelectOptionsList getDdEstadoDefaultOptions() {
		return ddEstadoDefaultOptions;
	}

	public void setDdEstadoDefaultOptions(SingleSelectOptionsList ddEstadoDefaultOptions) {
		this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
	}

	public StaticText getStSeparador5() {
		return stSeparador5;
	}

	public void setStSeparador5(StaticText stSeparador5) {
		this.stSeparador5 = stSeparador5;
	}

	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button b) {
		this.btnImprimirReporte = b;
	}

	public Button getBtnActivar() {
		return btnActivar;
	}

	public void setBtnActivar(Button btnActivar) {
		this.btnActivar = btnActivar;
	}

	// </editor-fold>
	/**
	 * <p>
	 * Construir una instancia de bean de página.
	 * </p>
	 */
	public AdminBien() {
	}

	public ObjectListDataProvider getLdpBienes() {
		return ldpBienes;
	}

	public void setLdpBienes(ObjectListDataProvider oldp) {
		this.ldpBienes = oldp;
	}

	public StaticText getStNombre() {
		return stNombre;
	}

	public void setStNombre(StaticText stNombre) {
		this.stNombre = stNombre;
	}

	public TableColumn getTcNombre() {
		return tcNombre;
	}

	public void setTcNombre(TableColumn tcNombre) {
		this.tcNombre = tcNombre;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationComprasBean().getTablaBien();
	}

	public String btnSeleccionarUnidad_action() {
		return navegarParaSeleccionar("AdminUnidad");
	}

	public String btnLimpiarUnidad_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.limpiarObjeto(getDdUnidad());
			FiltroBien locFiltro = this.getFiltro();
			locFiltro.setUnidad(null);
			
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarCodigoCiiu_action() {
		return navegarParaSeleccionar("AdminCodigoCiiu");
	}

	public String btnLimpiarCodigoCiiu_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.limpiarObjeto(getTfCodigoCiiu());
			FiltroBien locFiltro = this.getFiltro();
			locFiltro.setCodigoCiiu(null);
			
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroBien locFiltro = this.getFiltro();

		borrarListIdAuxCodigosCiiu(this.getTfCodigoCiiu(), locFiltro.getCodigoCiiu());
    	locFiltro.setListaIdCodigosCiiu(this.getSessionBean1().getListaIdCodigoCiiu());
		
		locFiltro.setNombre(getTextFieldValue(this.getTfNombre()));
		locFiltro.setDescripcion(getTextFieldValue(this.getTfDescripcion()));
		locFiltro.setEstado(getDDEnumValue(this.getDdEstado(), Bien.Estado.class));
		locFiltro.setTipo(getDDEnumValue(this.getDdTipo(), Bien.Tipo.class));
		locFiltro.setTipoBien(getDDObjectValue(getDdTipoBien(), getApplicationBean1().getMapaTipoBien()));
		locFiltro.setUnidad(getDDObjectValue(getDdUnidad(), getApplicationBean1().getMapaUnidad()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroBien locFiltro = this.getFiltro();

		this.getTfNombre().setText(locFiltro.getNombre());
		this.getTfDescripcion().setText(locFiltro.getDescripcion());
		this.getDdEstado().setSelected(
				Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado() == null ? Bien.Estado.ACTIVO : locFiltro.getEstado())));
		this.getDdTipo().setSelected(locFiltro.getTipo() != null ? Util.getEnumNameFromString(String.valueOf(locFiltro.getTipo())) : "");

		if (locFiltro.getCodigoCiiu() != null) {
			this.getTfCodigoCiiu().setText(locFiltro.getCodigoCiiu().toString());
		}

		if (locFiltro.getUnidad() != null) {
			this.getDdUnidad().setSelected(locFiltro.getUnidad().getDescripcion());
		}

		if (locFiltro.getTipoBien() != null) {
			this.getDdTipoBien().setSelected(locFiltro.getTipoBien().getNombre());
		}
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationComprasBean().getRemoteSystemAdministracionBienes().findListadoBienes((FiltroBien) pFiltro);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroBien locFiltro = getFiltro();

		locFiltro.setNombre(null);
		locFiltro.setDescripcion(null);
		locFiltro.setEstado(null);
		locFiltro.setTipo(null);
		locFiltro.setTipoBien(null);
		locFiltro.setUnidad(null);
		locFiltro.setCodigoCiiu(null);
		locFiltro.setUnidad(null);
		
		this.getTfNombre().setText("");
		this.getTfDescripcion().setText("");
		this.getTfCodigoCiiu().setText("");
		this.getDdUnidad().setSelected("");
		this.getDdEstado().setSelected(Bien.Estado.ACTIVO.toString());
		this.getDdTipo().setSelected("");
		this.getDdTipoBien().setSelected("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpBienes();
	}

	public String btnConsultar_action() {
		return toAbm(new BienModel().new ConsultarBienController());
	}

	public String btnAgregar_action() {
		return toAbm(new BienModel().new AgregarBienController());
	}

	public String btnModificar_action() {
		Bien bien = (Bien) getObjetoSeleccionado();
		if (bien != null && bien.getEstado().equals(Bien.Estado.INACTIVO)) {
			warn("No se puede modificar un bien que no est\341 en estado ACTIVO.");
			return null;
		}
		return toAbm(new BienModel().new ModificarBienController());
	}

	public String btnEliminar_action() {
		Bien locBien = (Bien) getObjetoSeleccionado();
		if (locBien == null) {
			return null;
		}
		if (locBien.getEstado().equals(Bien.Estado.INACTIVO)) {
			warn("No se puede eliminar un bien que no est\341 en estado ACTIVO.");
			return null;
		}
		return toAbm(new BienModel().new EliminarBienController());
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
				log(getCasoNavegacion() + "_ImprimirError: ", e);
				error(getNombrePagina() + " - Imprimir: " + e.getMessage());
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

	public String btnActivar_action() {
		Bien bien = (Bien) getObjetoSeleccionado();
		if (bien != null && bien.getEstado().equals(Bien.Estado.ACTIVO)) {
			warn("El bien seleccionado ya se encuentra activo.");
			return null;
		} else {
			return toAbm(new BienModel().new ActivarBienController());
		}
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaBienes();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationComprasBean().setListaBienes(lista);
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		// Si existe un metodo "getEntidaedPorID" se lo llama, si no se retorna
		// el mismo objeto del paramatro.
		Bien locBien = (Bien) pObject;
		this.getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(getSessionBean1().getLlave());
		locBien = this.getCommunicationComprasBean().getRemoteSystemAdministracionBienes().findBienByID(locBien.getIdBien());
		return locBien;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Bienes";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminBien";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	public Option[] armarArrayOptionsList(Object[] objetos, String capitalizacion) {
		Option[] o = new Option[objetos.length + 1];
		o[0] = new Option("", "");

		for (int i = 0; i < objetos.length; i++) {
			String display = objetos[i].toString();

			display = display.replaceAll("_", " ");
			Option opcion = new Option(objetos[i].toString(), display);
			o[i + 1] = opcion;
		}
		return o;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroBien locFiltro = this.getFiltro();
		if (pObject instanceof CodigoCiiu) {
			locFiltro.setCodigoCiiu((CodigoCiiu) pObject);
		}
		if (pObject instanceof Unidad) {
			locFiltro.setUnidad((Unidad) pObject);
		}
	}
	
	@Override
	public long getSerialVersionUID() {
		return Bien.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMBien$AdminBien}";
	}
	
	public void setCodigoCiiuAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroBien locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		CodigoCiiu codigoCiiu = null;

		try {
			codigoCiiu = (CodigoCiiu) this.getComunicationBean().getRemoteSystemMunicipalidad().getCodigoCiiuById(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

		locFiltro.setCodigoCiiu(codigoCiiu);
	}
}