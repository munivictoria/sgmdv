package muni.excepciones.ABMRefinanciacion;

import jasper.ConstantesReportes;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.convert.NumberConverter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.saic.recurso.filtros.FiltroRefinanciacion;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;
import com.trascender.saic.recurso.persistent.refinanciacion.CuotaRefinanciacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion.EstadoRefinanciacion;

public class AdminRefinanciacion extends AdminPageBean {

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		numberConverter1.setPattern("$ #,##0.00");

		this.habilitarBtnExportar();

		Option[] opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(EstadoRefinanciacion.values(), "cap");
		ddEstadoDefaultOptions.setOptions(opEstado);
	}

	private DropDown ddEstado = new DropDown();
	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();

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

	protected PanelGroup pgParametros = new PanelGroup();

	public PanelGroup getPgParametros() {
		return pgParametros;
	}

	public void setPgParametros(PanelGroup pgParametros) {
		this.pgParametros = pgParametros;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private NumberConverter numberConverter1 = new NumberConverter();

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter numberConverter1) {
		this.numberConverter1 = numberConverter1;
	}

	private ObjectListDataProvider ldpRefinanciacion = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpRefinanciacion() {
		return ldpRefinanciacion;
	}

	public void setLdpRefinanciacion(ObjectListDataProvider oldp) {
		this.ldpRefinanciacion = oldp;
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

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label label3) {
		this.label3 = label3;
	}

	private TextArea textArea1 = new TextArea();

	public TextArea getTextArea1() {
		return textArea1;
	}

	public void setTextArea1(TextArea ta) {
		this.textArea1 = ta;
	}

	private PanelGroup gpBotones = new PanelGroup();

	public PanelGroup getGpBotones() {
		return gpBotones;
	}

	public void setGpBotones(PanelGroup pg) {
		this.gpBotones = pg;
	}

	private TableColumn tableColumn5 = new TableColumn();
	private TableColumn tableColumn6 = new TableColumn();

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText staticText8) {
		this.staticText8 = staticText8;
	}

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText staticText9) {
		this.staticText9 = staticText9;
	}

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tableColumn6) {
		this.tableColumn6 = tableColumn6;
	}

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private Button btnSeleccionarPersonaJuridica = new Button();

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button b) {
		this.btnSeleccionarPersonaJuridica = b;
	}

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	private StaticText staticText6 = new StaticText();

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText st) {
		this.staticText6 = st;
	}

	private HtmlAjaxCommandButton btnImprimirReconocimientoDeuda = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnImprimirReconocimientoDeuda() {
		return btnImprimirReconocimientoDeuda;
	}

	public void setBtnImprimirReconocimientoDeuda(HtmlAjaxCommandButton btnImprimirReconocimientoDeuda) {
		this.btnImprimirReconocimientoDeuda = btnImprimirReconocimientoDeuda;
	}

	private TextField tfAlDiaDe = new TextField();
	private TextField tfSolicitante = new TextField();
	private TextField tfMotivo = new TextField();
	private TextField tfObservaciones = new TextField();
	private HtmlAjaxCommandButton btnAceptarDatos = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnCerrarModal = new HtmlAjaxCommandButton();

	public TextField getTfAlDiaDe() {
		return tfAlDiaDe;
	}

	public void setTfAlDiaDe(TextField tfAlDiaDe) {
		this.tfAlDiaDe = tfAlDiaDe;
	}

	public TextField getTfSolicitante() {
		return tfSolicitante;
	}

	public void setTfSolicitante(TextField tfSolicitante) {
		this.tfSolicitante = tfSolicitante;
	}

	public TextField getTfMotivo() {
		return tfMotivo;
	}

	public void setTfMotivo(TextField tfMotivo) {
		this.tfMotivo = tfMotivo;
	}

	public TextField getTfObservaciones() {
		return tfObservaciones;
	}

	public void setTfObservaciones(TextField tfObservaciones) {
		this.tfObservaciones = tfObservaciones;
	}

	public HtmlAjaxCommandButton getBtnAceptarDatos() {
		return btnAceptarDatos;
	}

	public void setBtnAceptarDatos(HtmlAjaxCommandButton btnAceptarDatos) {
		this.btnAceptarDatos = btnAceptarDatos;
	}

	public HtmlAjaxCommandButton getBtnCerrarModal() {
		return btnCerrarModal;
	}

	public void setBtnCerrarModal(HtmlAjaxCommandButton btnCerrarModal) {
		this.btnCerrarModal = btnCerrarModal;
	}

	private Button btnGenerar = new Button();
	private Button btnDarDeBaja = new Button();
	private Button btnDarDeAlta = new Button();
	private Button btnImprimirReporte = new Button();

	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button btnImprimirReporte) {
		this.btnImprimirReporte = btnImprimirReporte;
	}

	public Button getBtnGenerar() {
		return btnGenerar;
	}

	public void setBtnGenerar(Button b) {
		this.btnGenerar = b;
	}

	public Button getBtnDarDeBaja() {
		return btnDarDeBaja;
	}

	public void setBtnDarDeBaja(Button btnDarDeBaja) {
		this.btnDarDeBaja = btnDarDeBaja;
	}

	public Button getBtnDarDeAlta() {
		return btnDarDeAlta;
	}

	public void setBtnDarDeAlta(Button btnDarDeAlta) {
		this.btnDarDeAlta = btnDarDeAlta;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private TextField tfNumeroTramite = new TextField();

	public TextField getTfNumeroTramite() {
		return tfNumeroTramite;
	}

	public void setTfNumeroTramite(TextField tf) {
		this.tfNumeroTramite = tf;
	}

	private StaticText staticText10 = new StaticText();

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText staticText10) {
		this.staticText10 = staticText10;
	}

	private StaticText staticText7 = new StaticText();
	private StaticText staticText8 = new StaticText();
	private StaticText staticText9 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p?gina.
	 * </p>
	 */
	public AdminRefinanciacion() {
	}

	/**
	 * <p>
	 * M?todo de devoluci?n de llamada al que se llama cuando el ?rbol de componentes se ha restaurado, pero antes de que se produzca el procesamiento de
	 * cualquier evento. Este m?todo <strong>s?lo</strong> se llamar? en una petici?n de devoluci?n de env?o que est? procesando el env?o de un formulario.
	 * Puede personalizar este m?todo para asignar recursos necesarios para los controladores de eventos.
	 * </p>
	 */
	public void preprocess() {
	}

	/**
	 * <p>
	 * M?todo de devoluci?n de llamada al que se llama cuando se completa el procesamiento de esta petici?n, si se llam? al m?todo <code>init()</code> (sin
	 * tener en cuenta si se trata de la p?gina que se ha procesado o no). Puede personalizar este m?todo para liberar los recursos adquiridos en los m?todos
	 * <code>init()</code>, <code>preprocess()</code> o <code>prerender()</code> (o durante la ejecuci?n de un controlador de eventos).
	 * </p>
	 */
	public void destroy() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroRefinanciacion locFiltro = this.getFiltro();

		locFiltro.setNroRefinanciacion(this.getTextFieldValueInteger(this.getTfNumeroTramite()));

		Object estadoSelected = this.getDdEstado().getSelected();
		if((estadoSelected != null) && (estadoSelected.toString().length() > 0)) {
			locFiltro.setEstado(EstadoRefinanciacion.valueOf(estadoSelected.toString()));
		} else {
			locFiltro.setEstado(null);
		}
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroRefinanciacion locFiltro = this.getFiltro();

		this.getTfNumeroTramite().setText(locFiltro.getNroRefinanciacion());

		if(locFiltro.getPersona() != null && locFiltro.getPersona().getIdPersona() != -1) {
			this.getTfPersonaSeleccionada().setText(locFiltro.getPersona().toString());
		}

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
		this.getDdEstadoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroRefinanciacion locFiltro = this.getFiltro();
		locFiltro.setPersona(null);
		locFiltro.setNroRefinanciacion(null);
		locFiltro.setEstado(null);

		this.getSessionBean1().setPersonaSeleccionada(null);

		this.getTfNumeroTramite().setText(null);
		this.getTfPersonaSeleccionada().setText(null);
		this.getDdEstado().setSelected(null);
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpRefinanciacion();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationExcepcionesBean().getListaRefinanciaciones();
	}

	protected void setListaDelCommunication(List lista) {
		this.getCommunicationExcepcionesBean().setListaRefinanciaciones(lista);
	}

	public String btnSeleccionarPersonaFisica_action() {
		return navegarParaSeleccionar("AdminPersonaFisica");
	}

	public String btnSeleccionarPersonaJuridica_action() {
		return navegarParaSeleccionar("AdminPersonaJuridica");
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationSAICBean().getTablaRefinanciacion();
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	public String btnGenerar_action() {
		return toAbm(new GenerarRefinanciacionModel().new GenerarRefinanciacionController());
	}

	public String btnModificar_action() {
		return toAbm(new RefinanciacionModel().new ModificarRefinanciacionController());
	}

	public String btnEliminar_action() {
		Object obj = getObjetoSeleccionado();

		if(obj == null) {
			return null;
		}

		try {
			DocumentoRefinanciacion doc = (DocumentoRefinanciacion) this.getObjectPorId(obj);
			if(!doc.getEstadoRefinanciacion().equals(EstadoRefinanciacion.TERMINADA)) {
				for(RegistroDeuda cadaRegistro : doc.getListaRegistrosDeuda()) {
					CuotaRefinanciacion cuota = (CuotaRefinanciacion) cadaRegistro;
					if(cuota.getEstado().equals(EstadoRegistroDeuda.PAGADA)) {
						warn("No se puede eliminar una Refinanciacion que posee cuotas pagadas.");
						return null;
					}
				}
			} else {
				warn("No se puede eliminar una Refinanciacion terminada.");
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return toAbm(new RefinanciacionModel().new EliminarRefinanciacionController());
	}

	public String btnDarDeBaja_action() {
		Object obj = getObjetoSeleccionado();

		if(obj == null) {
			return null;
		}

		try {
			DocumentoRefinanciacion doc = (DocumentoRefinanciacion) this.getObjectPorId(obj);
			if(doc.getEstadoRefinanciacion().equals(EstadoRefinanciacion.CADUCADA)) {
				warn("La Refinanciacion ya se encuentra caducada.");
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return toAbm(new RefinanciacionModel().new DarDeBajaRefinanciacionController());
	}

	public String btnDarDeAlta_action() {
		Object obj = getObjetoSeleccionado();

		if(obj == null) {
			return null;
		}

		try {
			DocumentoRefinanciacion doc = (DocumentoRefinanciacion) this.getObjectPorId(obj);
			if(doc.getEstadoRefinanciacion().equals(EstadoRefinanciacion.PENDIENTE) || doc.getEstadoRefinanciacion().equals(EstadoRefinanciacion.TERMINADA)) {
				warn("La Refinanciacion ya se encuentra activa.");
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return toAbm(new RefinanciacionModel().new DarDeAltaRefinanciacionController());
	}

	public String btnConsultar_action() {
		return toAbm(new RefinanciacionModel().new ConsultarRefinanciacionController());
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		DocumentoRefinanciacion locRefinanciacion = (DocumentoRefinanciacion) pObject;
		getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(getSessionBean1().getLlave());
		locRefinanciacion = getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().getDocumentoRefinanciacion(locRefinanciacion.getIdDocGeneradorDeuda());

		return locRefinanciacion;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(getSessionBean1().getLlave());
		return getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().findListaRefinanciaciones((FiltroRefinanciacion) pFiltro);
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroRefinanciacion locFiltro = this.getFiltro();
		if(pObject instanceof Persona) {
			Persona persona = (Persona) pObject;
			locFiltro.setPersona(persona);

			this.getSessionBean1().setPersonaSeleccionada(persona);
		}
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Refinanciaciones";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminRefinanciacion";
	}

	@Override
	public String getNombreBean() {
		return "#{excepciones$ABMRefinanciacion$AdminRefinanciacion}";
	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoRefinanciacion.serialVersionUID;
	}

	public void setPersonaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroRefinanciacion locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Persona propietario = null;

		try {
			propietario = this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		locFiltro.setPersona(propietario);
		this.getSessionBean1().setPersonaSeleccionada(propietario);
	}

	public String btnReconocimientoDeuda_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;
			rk = this.getSeleccionado();
			if(rk == null) {
				warn("Debe seleccionar una Refinanciacion.");
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public void btnImprimirReconocimientoDeuda_action() {
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			try {
				RowKey rk = null;
				rk = this.getSeleccionado();
				
				int index = this.getNroFila(rk.toString());
				Object obj = this.getObjectListDataProvider().getObjects()[index];
				DocumentoRefinanciacion documentoRefinanciacion = (DocumentoRefinanciacion) obj;

				String strFecha = this.getTextFieldValue(this.getTfAlDiaDe());

				if(strFecha != null) {
					List<RegistroDeuda> listaCuotas = new ArrayList(documentoRefinanciacion.getListaRegistrosDeuda());

					Collections.sort(listaCuotas, new Comparator<RegistroDeuda>() {

						public int compare(RegistroDeuda r1, RegistroDeuda r2) {
							return r1.getFechaVencimiento().compareTo(r2.getFechaVencimiento());
						}
					});

					Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(strFecha);

					boolean error = false;
					for(RegistroDeuda cadaRegistro : listaCuotas) {
						if(cadaRegistro.getFechaVencimiento().before(fecha)) {
							if(!cadaRegistro.getEstado().equals(EstadoRegistroDeuda.PAGADA)) {
								error = true;
								break;
							}
						} else {
							break;
						}
					}

					if(!error) {
//						LibreDeuda locLibreDeuda = new LibreDeuda();
//
//						locLibreDeuda.setSolicitante(this.getTextFieldValue(this.getTfSolicitante()));
//						locLibreDeuda.setMotivo(this.getTextFieldValue(this.getTfMotivo()));
//						locLibreDeuda.setObservaciones(this.getTextFieldValue(this.getTfObservaciones()));
//
//						locLibreDeuda.setUsuario(this.getSessionBean1().getUsuario());
//						locLibreDeuda.setFechaGenerada(new Date());
//						locLibreDeuda.setFechaSolicitada(fecha);
//
//						this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());
//						JasperPrint jp = this.getCommunicationSAICBean().getRemoteSystemImpresion().getReporteReconocimientoDeuda(documentoRefinanciacion, locLibreDeuda);
//
//						this.subirReporteASesion("Reporte_ReconocimientoDeudas", ConstantesReportes.PDF, jp);
					} else {
						warn("No se puede imprimir el reporte porque posee cuotas IMPAGAS antes de la fecha.");
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
					}
				} else {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
				}
			} catch(Exception e) {
				log("AdminRefinanciacion" + "_ReporteDinamicoError: ", e);
				error("Refinanciacion" + " - ReporteDinamico: " + e.getMessage());
			}
		}
	}

	public String btnImprimirReporte_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		boolean errorEnReporte = false;

		if(ultimo) {
			try {
				RowKey rk = null;
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = this.getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					DocumentoRefinanciacion locRefinanciacion = (DocumentoRefinanciacion) obj;

					this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
					locRefinanciacion = this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().getDocumentoRefinanciacion(locRefinanciacion.getIdDocGeneradorDeuda());

					this.getCommunicationSAICBean().getRemoteSystemImpresion().setLlave(this.getSessionBean1().getLlave());
//					JasperPrint jp = this.getCommunicationSAICBean().getRemoteSystemImpresion().getReporteConstanciaRefinanciacion(locRefinanciacion);

//					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
//					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_ConstanciaRefinanciacion");
//					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
				} else {
					errorEnReporte = true;
				}
			} catch(Exception e) {
				log(getCasoNavegacion() + "_ReporteDinamicoError: ", e);
				error(getNombrePagina() + " - ReporteDinamico: " + e.getMessage());
				errorEnReporte = true;
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		if(errorEnReporte) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
		}

		return retorno;
	}

}