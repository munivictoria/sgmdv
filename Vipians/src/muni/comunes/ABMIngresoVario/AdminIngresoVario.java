/*
 * AdminIngresoVario.java
 *
 * Created on 28 de mayo de 2007, 11.21
 * Copyright Trascender SRL
 */

package muni.comunes.ABMIngresoVario;

import jasper.ConstantesReportes;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.faces.context.FacesContext;

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
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.contabilidad.recurso.filtros.FiltroIngresoVario;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.saic.recurso.persistent.RegistroDeuda;

public class AdminIngresoVario extends AdminPageBean {

	// CAMBIAR: Constantes que varian segun la pagina.
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Administraci\363n de Ingresos Varios";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "AdminIngresoVario";
	// nombre del caso de navegacion para llegar a la pagina de caducidad

	private DropDown ddConceptoIngresoVario = new DropDown();
	private SingleSelectOptionsList ddConceptoIngresoVarioOptions = new SingleSelectOptionsList();
	private ObjectListDataProvider ldpIngresosVarios = new ObjectListDataProvider();
	private Button btnImprimir = new Button();
	private HtmlAjaxCommandButton btnLimpiarConcepto = new HtmlAjaxCommandButton();
	private TextField tfConcepto = new TextField();
	private Button btnRefinanciar = new Button();
	private TextField tfNumero = new TextField();
	
	private Button btnActualizarDeuda = new Button();

	public Button getBtnActualizarDeuda() {
		return btnActualizarDeuda;
	}

	public void setBtnActualizarDeuda(Button btnActualizarDeuda) {
		this.btnActualizarDeuda = btnActualizarDeuda;
	}
	
	public Button getBtnRefinanciar() {
		return btnRefinanciar;
	}

	public void setBtnRefinanciar(Button btnRefinanciar) {
		this.btnRefinanciar = btnRefinanciar;
	}
	
	public HtmlAjaxCommandButton getBtnLimpiarConcepto() {
		return btnLimpiarConcepto;
	}

	public void setBtnLimpiarConcepto(HtmlAjaxCommandButton btnLimpiarConcepto) {
		this.btnLimpiarConcepto = btnLimpiarConcepto;
	}

	public TextField getTfConcepto() {
		return tfConcepto;
	}

	public void setTfConcepto(TextField tfConcepto) {
		this.tfConcepto = tfConcepto;
	}

	public Button getBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(Button btnImprimir) {
		this.btnImprimir = btnImprimir;
	}

	public DropDown getDdConceptoIngresoVario() {
		return ddConceptoIngresoVario;
	}

	public void setDdConceptoIngresoVario(DropDown ddConceptos) {
		this.ddConceptoIngresoVario = ddConceptos;
	}

	public SingleSelectOptionsList getDdConceptoIngresoVarioOptions() {
		return ddConceptoIngresoVarioOptions;
	}

	public void setDdConceptoIngresoVarioOptions(SingleSelectOptionsList ddConceptosOptions) {
		this.ddConceptoIngresoVarioOptions = ddConceptosOptions;
	}

	public ObjectListDataProvider getLdpIngresosVarios() {
		return ldpIngresosVarios;
	}

	public void setLdpIngresosVarios(ObjectListDataProvider oldp) {
		this.ldpIngresosVarios = oldp;
	}

	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdEstadoDefaultOptions() {
		return ddEstadoDefaultOptions;
	}

	public void setDdEstadoDefaultOptions(SingleSelectOptionsList ddEstadoDefaultOptions) {
		this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
	}

	private DropDown ddEstado = new DropDown();

	public DropDown getDdEstado() {
		return ddEstado;
	}

	public void setDdEstado(DropDown ddEstado) {
		this.ddEstado = ddEstado;
	}

	private Label lblEstado = new Label();

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private HtmlAjaxCommandButton btnLimpiarConceptoIngresoVario = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarConceptoIngresoVario() {
		return btnLimpiarConceptoIngresoVario;
	}

	public void setBtnLimpiarConceptoIngresoVario(HtmlAjaxCommandButton btnLimpiarConceptoIngresoVario) {
		this.btnLimpiarConceptoIngresoVario = btnLimpiarConceptoIngresoVario;
	}

	private Button btnSeleccionarConceptoIngresoVario = new Button();

	public Button getBtnSeleccionarConceptoIngresoVario() {
		return btnSeleccionarConceptoIngresoVario;
	}

	public void setBtnSeleccionarConceptoIngresoVario(Button b) {
		this.btnSeleccionarConceptoIngresoVario = b;
	}
	
	private TextField tfFechaDesde = new TextField(), tfFechaHasta = new TextField();

	public TextField getTfFechaDesde() {
		return tfFechaDesde;
	}

	public void setTfFechaDesde(TextField tfFechaDesde) {
		this.tfFechaDesde = tfFechaDesde;
	}

	public TextField getTfFechaHasta() {
		return tfFechaHasta;
	}

	public void setTfFechaHasta(TextField tfFechaHasta) {
		this.tfFechaHasta = tfFechaHasta;
	}
	
	public TextField getTfNumero() {
		return tfNumero;
	}

	public void setTfNumero(TextField tfNumero) {
		this.tfNumero = tfNumero;
	}

	@Override
	protected void _init() throws Exception {
		Option[] op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(RegistroDeuda.EstadoRegistroDeuda.values(), "may");
		ddEstadoDefaultOptions.setOptions(op);

//		Set<String> locListaConceptos = this.getCommunicationCajaBean().getMapaConceptosIngresosVarios().keySet();
//		Option[] opConceptos = new Option[locListaConceptos.size() + 1];
//		int i = 0;
//		opConceptos[i++] = new Option("", "");
//		for(String cadaConcepto : locListaConceptos) {
//			opConceptos[i++] = new Option(cadaConcepto, cadaConcepto);
//		}
//		ddConceptoIngresoVarioOptions.setOptions(opConceptos);
	}

	public AdminIngresoVario() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		this.setTextFieldValueDate(this.tfFechaDesde, new Date());
		this.setTextFieldValueDate(this.tfFechaHasta, new Date());
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroIngresoVario locFiltro = this.getFiltro();

		borrarListIdAuxPersonas(this.tfPersonaSeleccionada, locFiltro.getPersona());
		locFiltro.setListaIdPersonas(this.getSessionBean1().getListaIdPersonas());

		locFiltro.setEstado(getDDEnumValue(this.getDdEstado(), RegistroDeuda.EstadoRegistroDeuda.class));
		
		locFiltro.setFechaDesde(this.getTextFieldValueDate(this.tfFechaDesde));
		locFiltro.setFechaHasta(this.getTextFieldValueDate(this.tfFechaHasta));
		
		locFiltro.setNumero(getTextFieldValueInteger(tfNumero));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroIngresoVario locFiltro = this.getFiltro();

		if(locFiltro.getPersona() != null) {
			this.getTfPersonaSeleccionada().setText(locFiltro.getPersona().toString());
		}

		if(locFiltro.getConceptoIngresoVario() != null) {
			this.getTfConcepto().setText(locFiltro.getConceptoIngresoVario().getNombre());
		}

		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado() == null ? RegistroDeuda.EstadoRegistroDeuda.VIGENTE : locFiltro.getEstado())));
		this.getDdEstadoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(locFiltro.getEstado())));
		
		setTextFieldValueDate(this.tfFechaDesde, locFiltro.getFechaDesde());
		setTextFieldValueDate(this.tfFechaHasta, locFiltro.getFechaHasta());
		
		setTextFieldValueInteger(this.tfNumero, locFiltro.getNumero());
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroIngresoVario locFiltro = this.getFiltro();
		locFiltro.setConceptoIngresoVario(null);
		locFiltro.setEstado(RegistroDeuda.EstadoRegistroDeuda.VIGENTE);
		locFiltro.setPersona(null);
		locFiltro.setNumero(null);

		this.getSessionBean1().setPersonaSeleccionada(null);
		this.getSessionBean1().getListaIdPersonas().clear();
		// CAMBIAR: Limpiar los textField y dropDown
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(RegistroDeuda.EstadoRegistroDeuda.VIGENTE)));
		this.getDdEstadoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(RegistroDeuda.EstadoRegistroDeuda.VIGENTE)));
		this.getTfPersonaSeleccionada().setText(null);
		this.getTfConcepto().setText(null);
		
		this.setTextFieldValueDate(this.tfFechaDesde, new Date());
		this.setTextFieldValueDate(this.tfFechaHasta, new Date());
		this.limpiarObjeto(this.tfNumero);
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpIngresosVarios();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationCajaBean().getListaIngresosVarios();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationCajaBean().setListaIngresosVarios(lista);
	}

	public String btnSeleccionarConceptoIngresoVario_action() {
		return navegarParaSeleccionar("AdminConceptoIngresoVario");
	}

	public String btnLimpiarConceptoIngresoVario_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objet
			FiltroIngresoVario locFiltro = this.getFiltro();
			locFiltro.setConceptoIngresoVario(null);
			this.getTfConcepto().setText("");
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregar_action() {
		return toAbm(new IngresoVarioModel().new AgregarIngresoVarioController());
	}

	public String btnModificar_action() {
		return toAbm(new IngresoVarioModel().new ModificarIngresoVarioController());
	}

	public String btnEliminar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {

				rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					IngresoVario locIngresoVario = (IngresoVario) obj;
					if(locIngresoVario.getEstado() != null && !locIngresoVario.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.VIGENTE)) {
						warn("Solo se pueden eliminar los Ingresos Varios que esten en estado CREADO.");

						return null;
					}

					this.setRowKeySeleccionado(this.getSeleccionado());
				}

			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_EliminarError:", ex);
				error(NOMBRE_PAGINA + " - Eliminar: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			if(rk != null) {
				retorno = toAbm(new IngresoVarioModel().new EliminarIngresoVarioController());
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnConsultar_action() {
		return toAbm(new IngresoVarioModel().new ConsultarIngresoVarioController());
	}

	public String btnImprimir_action() {
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
					IngresoVario locIngresoVario = (IngresoVario) obj;
					this.getCommunicationContabilidadBean().getRemoteSystemReportesContabilidad().setLlave(this.getSessionBean1().getLlave());
					JasperPrint jp = this.getCommunicationContabilidadBean().getRemoteSystemReportesContabilidad().getReporteIngresoVario(locIngresoVario.getIdRegistroDeuda());

					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_Ingreso_Vario");
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
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

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		IngresoVario locIngreso = (IngresoVario) pObject;
		this.getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().getIngresoVarioByID(locIngreso.getIdRegistroDeuda());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().findListaIngresoVario((FiltroIngresoVario) pFiltro);
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationCajaBean().getTablaIngresoVario();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroIngresoVario locFiltro = this.getFiltro();

		if(pObject instanceof Persona) {
			Persona persona = (Persona) pObject;
			this.getSessionBean1().setPersonaSeleccionada(persona);
			locFiltro.setPersona(persona);
		}

		if(pObject instanceof ConceptoIngresoVario) {
			ConceptoIngresoVario conceptoIngresoVario = (ConceptoIngresoVario) pObject;
			locFiltro.setConceptoIngresoVario(conceptoIngresoVario);
		}
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Ingresos Varios";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminIngresoVario";
	}
	
	public String btnActualizarDeuda_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			IngresoVario ingresoVario = (IngresoVario) getObjetoSeleccionado();
			if(ingresoVario == null) {
				return null;
			}
			if(ingresoVario.getConceptoIngresoVario().getTipoTasa() == null) {
				warn("Solo se pueden actualizar los Ingresos Varios que tengan una Fórmula de Cáculo.");

				return null;
			}

			// Se hizo una lista por las dudas si el dia de mañana quieren seleccionar varios Ingresos Varios para actualizarlos U_U
			List<IngresoVario> listaIngresosVarios = new ArrayList<IngresoVario>();
			listaIngresosVarios.add(ingresoVario);

			this.getCommunicationSAICBean().setSeleccionadosSeleccionMultipleActualizarDeudaIngresoVario(new HashSet());
			
			this.getRequestBean1().setObjetoABM(listaIngresosVarios);
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setAbmController(new ActualizarDeudaIngresoVarioModel().new ActualizarController());

			retorno = "ABMActualizarDeudaIngresoVario";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	public String btnRefinanciar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					IngresoVario locIngresoVario = (IngresoVario) obj;
					if(locIngresoVario.getEstado() != null 
							&& !locIngresoVario.getEstado().equals(RegistroDeuda.EstadoRegistroDeuda.VIGENTE)) {
						warn("Solo se pueden refinanciar los Ingresos Varios que esten en estado VIGENTE.");
						return null;
					}

					this.setRowKeySeleccionado(this.getSeleccionado());
					List<RegistroDeuda> listaRegistros = new ArrayList<RegistroDeuda>();
					listaRegistros.add(locIngresoVario);
					this.getRequestBean1().setObjetoABM(listaRegistros);
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

					retorno = "AgregarPlanPagoRefinanciacion";
				}
			} catch(Exception ex) {
				log(CASO_NAVEGACION + "_EliminarError:", ex);
				error(NOMBRE_PAGINA + " - Eliminar: " + ex.getMessage());
			}
			
		} else {
			warn("Debe seleccionar una liquidación");
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public void setPersonaAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroIngresoVario locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Persona locPersona = null;

		try {
			locPersona = (Persona) this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		locFiltro.setPersona(locPersona);
		this.getSessionBean1().setPersonaSeleccionada(locPersona);
	}
	
	public void setConceptoAutocompletar(String pId, String pIdSubSession) { // aunque no se usa el ID de subsession
		FiltroIngresoVario locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		ConceptoIngresoVario locConcepto = null;

		try {
			locConcepto = (ConceptoIngresoVario) getCommunicationContabilidadBean().getRemoteSystemAdministracionIngresos().getConceptoIngresoVarioByID(id);
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch (TrascenderException e) {
			e.printStackTrace();
		}

		locFiltro.setConceptoIngresoVario(locConcepto);
	}

	@Override
	public String getNombreBean() {
		return "#{comunes$ABMIngresoVario$AdminIngresoVario}";
	}

	@Override
	public long getSerialVersionUID() {
		return IngresoVario.serialVersionUID;
	}
}