/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trascender.presentacion.abstracts;

import jasper.ConstantesReportes;

import java.util.Date;
import java.util.Map;

import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.ajax4jsf.ajax.html.HtmlAjaxStatus;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.Head;
import com.sun.rave.web.ui.component.HiddenField;
import com.sun.rave.web.ui.component.Html;
import com.sun.rave.web.ui.component.Link;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.Page;
import com.sun.rave.web.ui.component.RadioButtonGroup;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author Fernando Gareis
 */
public abstract class TrascenderAbstractPageBean extends AbstractPageBean {

	protected final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
	protected final String CASO_NAV_POST_CADUCIDAD = "Main";

	protected Page page1 = new Page();
	protected Html html1 = new Html();
	protected Head head1 = new Head();
	protected Body body1 = new Body();
	protected Form form1 = new Form();
	protected Link link1 = new Link();

	protected MessageGroup messageGroup = new MessageGroup();

	protected HiddenField hidIdPagina = new HiddenField();
	protected HiddenField hidIdSubSesion = new HiddenField();

	protected Long idPagina = null;
	protected Long idSubSesion = null;

	protected Script scriptValidador = new Script();

	private DropDown ddFormatoExportar = new DropDown();

	public DropDown getDdFormatoExportar() {
		return ddFormatoExportar;
	}

	public void setDdFormatoExportar(DropDown ddFormatoExportar) {
		this.ddFormatoExportar = ddFormatoExportar;
	}

	protected SingleSelectOptionsList ddFormatoExportarDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdFormatoExportarDefaultOptions() {
		return ddFormatoExportarDefaultOptions;
	}

	public void setDdFormatoExportarDefaultOptions(SingleSelectOptionsList ddFormatoExportarDefaultOptions) {
		this.ddFormatoExportarDefaultOptions = ddFormatoExportarDefaultOptions;
	}

	public Body getBody1() {
		return body1;
	}

	public void setBody1(Body body1) {
		this.body1 = body1;
	}

	public Form getForm1() {
		form1.getChildren().add(this.getStatusGifLoading());
		Script locScriptAtributoDinamico = getScript("scriptPanelDinamico", "/resources/javascript/PanelAtributoDinamico.js");
		form1.getChildren().add(locScriptAtributoDinamico);
		Script locScriptSeleccionTabla = getScript("scriptSeleccionTabla", "/resources/javascript/seleccionFilasTabla.js");
		form1.getChildren().add(locScriptSeleccionTabla);
		return form1;
	}

	public void setForm1(Form form1) {
		this.form1 = form1;
	}

	public Head getHead1() {
		if(head1.getChildren().size() == 0) { // Solo la primera vez.
			Script scriptjQuery = getScript("jQuery", "/resources/javascript/jQuery/jQuery-2-1-0.js");
			head1.getChildren().add(scriptjQuery);

			Script scriptjQueryUI = getScript("jQueryUI", "/resources/javascript/jQuery/jQueryUI-1-10-4.js");
			head1.getChildren().add(scriptjQueryUI);

			Script scriptMaskedInput = getScript("maskedInput", "/resources/javascript/jQuery/maskedInputPlugin-1-3-1.js");
			head1.getChildren().add(scriptMaskedInput);

			Link linkjQueryStyle = getLink("jQueryUIStyle", "http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css");
			head1.getChildren().add(linkjQueryStyle);
		}
		head1.setTitle(this.getNombrePagina());

		return head1;
	}

	private Link getLink(String id, String url) {
		Link locLink = new Link();
		locLink.setId(id);
		locLink.setUrl(url);
		return locLink;
	}

	private Script getScript(String id, String url) {
		Script locScript = new Script();
		locScript.setUrl(url);
		locScript.setId(id);
		return locScript;
	}

	public void setHead1(Head head1) {
		this.head1 = head1;
	}

	public HiddenField getHidIdPagina() {
		return hidIdPagina;
	}

	public void setHidIdPagina(HiddenField hidIdPagina) {
		this.hidIdPagina = hidIdPagina;
	}

	public HiddenField getHidIdSubSesion() {
		return hidIdSubSesion;
	}

	public void setHidIdSubSesion(HiddenField hidIdSubSesion) {
		this.hidIdSubSesion = hidIdSubSesion;
	}

	public Html getHtml1() {
		return html1;
	}

	public void setHtml1(Html html1) {
		this.html1 = html1;
	}

	public Long getIdPagina() {
		return idPagina;
	}

	public void setIdPagina(Long idPagina) {
		this.idPagina = idPagina;
	}

	public Long getIdSubSesion() {
		return idSubSesion;
	}

	public void setIdSubSesion(Long idSubSesion) {
		this.idSubSesion = idSubSesion;
	}

	public Link getLink1() {
		return link1;
	}

	public void setLink1(Link link1) {
		this.link1 = link1;
	}

	public MessageGroup getMessageGroup() {
		messageGroup.setStyleClass("grupoMsgAdmin");
		return messageGroup;
	}

	public void setMessageGroup(MessageGroup messageGroup) {
		this.messageGroup = messageGroup;
	}

	public Page getPage1() {
		return page1;
	}

	public void setPage1(Page page1) {
		this.page1 = page1;
	}

	public Script getScriptValidador() {
		return scriptValidador;
	}

	public void setScriptValidador(Script scriptValidador) {
		this.scriptValidador = scriptValidador;
	}

	/* Metodos abstractos */
	/*******************************************/

	/* Para cada pagina final */

	/**
	 * Agrega a la pila pasada por parametro los elementos que se usaran en esta pantalla.
	 * 
	 * @param ep
	 * @return
	 */
	protected abstract ElementoPila agregarObjetosAElementoPila(ElementoPila ep);

	/**
	 * Guarda informacion ingresada en pantalla por el usuario para usarse en los metodos correspondientes
	 */
	protected abstract void guardarEstadoObjetosUsados();

	/**
	 * Muestra en pantalla la informacion correspondiente.
	 */
	protected abstract void mostrarEstadoObjetosUsados();

	protected abstract void procesarObjetoSeleccion(Object pObject);

	protected abstract void procesarObjetoABM(Object pObject);

	protected abstract String getNombrePagina();

	/**
	 * El String necesario para que JSF navegue hasta esta pagina.
	 * 
	 * @return
	 */
	protected abstract String getCasoNavegacion();

	/* Solo para cada tipo de Bean (Admin - ABM) */
	protected abstract void haciaAtrasTransaccion();

	//
	protected abstract boolean puedeSerPaginaInicial();

	/*******************************************/

	@Override
	public void init() {
		super.init();

		Option[] opFormatoExportar = new Option[2];
		opFormatoExportar[0] = new Option("Excel");
		opFormatoExportar[1] = new Option("PDF");

		this.ddFormatoExportarDefaultOptions.setOptions(opFormatoExportar);
	}

	protected void _init() throws Exception {

	}

	protected void _prerender() throws Exception {

	}

	@Override
	public void prerender() {
		boolean existeIdSubSesionReq = false;
		boolean existeIdPaginaReq = false;
		boolean existeIdSubSesionPag = false;
		boolean existeIdPaginaPag = false;
		boolean recarga = false;

		if(this.getRequestBean1() != null) {
			existeIdSubSesionReq = (this.getRequestBean1().getIdSubSesion() != null);
			existeIdPaginaReq = (this.getRequestBean1().getIdPagina() != null);
		}

		this.setIdSubSesion((Long) this.getHidIdSubSesion().getText());
		this.setIdPagina((Long) this.getHidIdPagina().getText());

		existeIdSubSesionPag = this.getIdSubSesion() != null;
		existeIdPaginaPag = this.getIdPagina() != null;

		// Pagina nueva - Inicio de transaccion
		if(!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			if(this.puedeSerPaginaInicial()) {
				Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
				this.setIdPagina(key);
				this.setIdSubSesion(key);
				this.crearElementoPila();
			}
		}

		// Recarga de la misma pagina por validacion
		if(!existeIdSubSesionReq && !existeIdPaginaReq && existeIdSubSesionPag && existeIdPaginaPag) {
			// no se hace nada por ahora
			recarga = true;
			// APLICAR LOGICA AQUI.. ver si es as� realmente..
		}

		// Pagina nueva - hacia adelante en la transaccion
		// Para el caso de las paginas de inicio de transaccion nunca entra
		if(existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			Long key = this.getSessionBean1().getMgrPilas().generarClaveUnica();
			this.setIdPagina(key);
			this.setIdSubSesion(this.getRequestBean1().getIdSubSesion());
			this.crearElementoPila();
		}

		// Pagina nueva - hacia atras en la transaccion
		if(existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {
			ElementoPila ep = this.getRequestBean1().getElementoPilaPaginaAnt();
			this.setIdPagina(ep.getIdPagina());
			this.setIdSubSesion(ep.getIdSubSesion());

			// El comportamiento es distinto segun bean de Admin o ABM
			this.haciaAtrasTransaccion();
		}

		if(!recarga) {
			if(this.getRequestBean1().getObjetoSeleccion() != null) {
				try {
					this.procesarObjetoSeleccion(this.getRequestBean1().getObjetoSeleccion());
				} catch (Exception e) {
					log("Error", e);
				}
			}
			if(this.getRequestBean1().getObjetoABM() != null) {
				try {
					this.procesarObjetoABM(this.getRequestBean1().getObjetoABM());
				} catch (Exception e) {
					log("Error", e);
				}
			}
			try {
				this.mostrarEstadoObjetosUsados();
			} catch (Exception e) {
				log("Error", e);
			}
		}
		this.getSessionBean1().setRutaOperacion(this.getSessionBean1().getMgrPilas().getRutaOperacion(this.getIdSubSesion()));
		
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se completa el procesamiento de esta petici�n, si se llam� al m�todo <code>init()</code> (sin
	 * tener en cuenta si se trata de la p�gina que se ha procesado o no). Puede personalizar este m�todo para liberar los recursos adquiridos en los m�todos
	 * <code>init()</code>, <code>preprocess()</code> o <code>prerender()</code> (o durante la ejecuci�n de un controlador de eventos).
	 * </p>
	 */
	@Override
	public void destroy() {
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando el �rbol de componentes se ha restaurado, pero antes de que se produzca el procesamiento de
	 * cualquier evento. Este m�todo <strong>s�lo</strong> se llamar� en una petici�n de devoluci�n de env�o que est� procesando el env�o de un formulario.
	 * Puede personalizar este m�todo para asignar recursos necesarios para los controladores de eventos.
	 * </p>
	 */
	@Override
	public void preprocess() {
	}

	public ElementoPila getElementoPila() {
		return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
	}

	protected void crearElementoPila() {
		ElementoPila ep = new ElementoPila();
		ep.setCasoNavegacion(getCasoNavegacion());
		ep.setIdPagina(this.getIdPagina());
		ep.setIdSubSesion(this.getIdSubSesion());
		ep.setNombrePagina(getNombrePagina());

		if(this.getRequestBean1().getTipoSeleccion() != null) {
			if(this.getRequestBean1().getTipoSeleccion().equals("MULTIPLE")) {
				ep.setSeleccionMultiple(true);
			}
		}
		if(this.getRequestBean1().getAbmController() != null) {
			ep.setAbmController(this.getRequestBean1().getAbmController());
			ep.setTablaLogs(new TablaLogs(this.getNombreBean()));
		}

		ep = this.agregarObjetosAElementoPila(ep);
		this.getSessionBean1().getMgrPilas().addElemento(ep);
	}

	protected String prepararParaVolver(String pAccionRetorno) {
		this.getRequestBean1().setAccion(pAccionRetorno);
		String retorno = null;
		ElementoPila locElementoAnterior = this.getSessionBean1().getMgrPilas().getElementoPilaAnterior(this.getElementoPila());

		if(locElementoAnterior != null) {
			this.getSessionBean1().getMgrPilas().removeElemento(this.getElementoPila());
			this.getRequestBean1().setIdSubSesion(locElementoAnterior.getIdSubSesion());
			this.getRequestBean1().setIdPagina(locElementoAnterior.getIdPagina());
			this.getRequestBean1().setElementoPilaPaginaAnt(locElementoAnterior);
			retorno = locElementoAnterior.getCasoNavegacion();
		} else {
			retorno = CASO_NAV_POST_CADUCIDAD;
		}

		return retorno;
	}

	protected int getCantidadObjetosUsados() {
		return this.getElementoPila().getObjetos().size();
	}

	protected String prepararCaducidad() {
		// redireccionar a pagina con mensaje de caducidad
		this.getRequestBean1().setCasoNavegacionPostCaducidad(CASO_NAV_POST_CADUCIDAD);
		return CASO_NAV_CADUCIDAD;
	}

	protected boolean ultimoElementoPilaDeSubSesion() {
		return this.getSessionBean1().getMgrPilas().isLastElementoPila(this.getIdSubSesion(), this.getIdPagina());
	}

	protected int getNroFila(String pCadena) {
		// Toma la Cadena con el formato 'RowKey[i]' y devuelve el entero i
		String lCadenaAuxiliar = pCadena.substring(7, pCadena.length() - 1);
		return new Integer(lCadenaAuxiliar).intValue();
	}

	/**
	 * Retorna el elemento ubicado en la posicion recibida por parametro. Si no hay nada en esa posicion, retorna una nueva instancia de la clase recibida.
	 * 
	 * @param posicion
	 * @param tipoClase
	 * @return
	 */
	public <T> T obtenerObjetoDelElementoPila(int posicion, Class<T> pClase) {
		Object objeto = null;
		try {
			objeto = this.getElementoPila().getObjetos().get(posicion);
			if(objeto == null) {
				objeto = pClase.newInstance();
			}
		} catch(Exception ex) {
		}

		return (T) objeto;
	}

	protected void acomodarSeleccionado() {
		Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
		if(seleccionado != null) {
			int posicion = this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class).intValue();
			this.getElementoPila().getObjetos().set(posicion, seleccionado);
		}
	}

	/**
	 * Retorna el elemento ubicado en la posicion recibida por parametro. Si no hay nada en esa posicion, retorna null.
	 * 
	 * @param posicion
	 * @param tipoClase
	 * @return
	 */
	public Object obtenerObjetoDelElementoPila(int posicion) {
		Object objeto = null;
		try {
			objeto = this.getElementoPila().getObjetos().get(posicion);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return objeto;
	}

	public muni.CommunicationExcepcionesBean getCommunicationExcepcionesBean() {
		return (muni.CommunicationExcepcionesBean) getBean("CommunicationExcepcionesBean");
	}

	public muni.CommunicationCajaBean getCommunicationCajaBean() {
		return (muni.CommunicationCajaBean) getBean("CommunicationCajaBean");
	}

	public muni.CommunicationComprasBean getCommunicationComprasBean() {
		return (muni.CommunicationComprasBean) getBean("CommunicationComprasBean");
	}

	public muni.CommunicationSAICBean getCommunicationSAICBean() {
		return (muni.CommunicationSAICBean) getBean("CommunicationSAICBean");
	}

	public muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
		return (muni.CommunicationHabilitacionesBean) getBean("CommunicationHabilitacionesBean");
	}

	public muni.ComunicationCatastroBean getComunicationCatastroBean() {
		return (muni.ComunicationCatastroBean) getBean("ComunicationCatastroBean");
	}

	public muni.ApplicationBean1 getApplicationBean1() {
		return (muni.ApplicationBean1) getBean("ApplicationBean1");
	}

	public muni.ComunicationBean getComunicationBean() {
		return (muni.ComunicationBean) getBean("ComunicationBean");
	}

	public muni.CommunicationContabilidadBean getCommunicationContabilidadBean() {
		return (muni.CommunicationContabilidadBean) getBean("CommunicationContabilidadBean");
	}

	public muni.CommunicationAccionSocialBean getCommunicationAccionSocialBean() {
		return (muni.CommunicationAccionSocialBean) getBean("CommunicationAccionSocialBean");
	}

	public muni.CommunicationExpedientesBean getCommunicationExpedientesBean() {
		return (muni.CommunicationExpedientesBean) getBean("CommunicationExpedientesBean");
	}

	public muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) getBean("SessionBean1");
	}

	public muni.RequestBean1 getRequestBean1() {
		return (muni.RequestBean1) getBean("RequestBean1");
	}

	// ////////// Funciones Extra

	protected String getTextFieldValue(TextField pTextField) {
		String retorno = null;
		if(pTextField.getText() != null && !pTextField.getText().toString().trim().isEmpty()) {
			retorno = pTextField.getText().toString().trim();
		}
		return retorno;
	}

	protected Integer getTextFieldValueInteger(TextField pTextField) {
		Integer retorno = null;
		if(pTextField.getText() != null && !pTextField.getText().toString().trim().isEmpty()) {
			retorno = Conversor.getIntegerDeString(pTextField.getText().toString().trim());
		}
		return retorno;
	}
	
	protected void setTextFieldValueInteger(TextField tf, Integer value) {
		if (value != null) {
			tf.setText(value);
		}
	}

	protected Double getTextFieldValueDouble(TextField pTextField) {
		Double retorno = null;
		if(pTextField.getText() != null && !pTextField.getText().toString().trim().isEmpty()) {
			retorno = Conversor.getDoubleDeString(pTextField.getText().toString().trim());
		}
		return retorno;
	}
	
	protected void setTextFieldValueDouble(TextField tf, Double value) {
		if (value != null) {
			tf.setText(value);
		}
	}

	protected Date getTextFieldValueDate(TextField pTextField) {
		Date retorno = null;
		if(pTextField.getText() != null && !pTextField.getText().toString().trim().isEmpty()) {
			retorno = Conversor.getFechaCortaDeString(pTextField.getText().toString().trim());
		}
		return retorno;
	}
	
	protected void setTextFieldValueDate(TextField tf, Date date) {
		if (date != null && tf != null) {
			String valor = Conversor.getStringDeFechaCorta(date);
			tf.setText(valor);
		}
	}

	protected String getTextAreaValue(TextArea pTextArea) {
		String retorno = null;
		if(pTextArea.getText() != null && !pTextArea.getText().toString().trim().isEmpty()) {
			retorno = pTextArea.getText().toString().trim();
		}
		return retorno;
	}

	protected <T> T getDDObjectValue(DropDown pDropDown, Map<String, T> pMapa) {
		if(pDropDown.getSelected() != null && !pDropDown.getSelected().toString().isEmpty()) {
			return pMapa.get(pDropDown.getSelected().toString());
		} else
			return null;
	}

	protected <T extends Enum<T>> T getDDEnumValue(DropDown pDropDown, Class<T> pClase) {
		Object valor = pDropDown.getSelected();
		if(valor == null || valor.toString().trim().isEmpty()) {
			return null;
		} else {
			return Enum.valueOf(pClase, valor.toString());
		}
	}

	protected <T extends Enum<T>> T getRBGEnumValue(RadioButtonGroup pRadioButtonGroup, Class<T> pClase) {
		Object valor = pRadioButtonGroup.getSelected();
		if(valor == null || valor.toString().trim().isEmpty() || valor.equals("NULL")) {
			return null;
		} else {
			return Enum.valueOf(pClase, valor.toString());
		}
	}
	
	protected void setCheckBoxValue(Checkbox cb, Boolean value) {
		if (value != null) {
			cb.setSelected(value);
		}
	}

	public void limpiarObjeto(TextField campo) {
		if(campo != null) {
			campo.setText(null);
		}
	}

	public void limpiarObjeto(DropDown campo) {
		if(campo != null) {
			campo.setSelected("");
		}
	}
	
	protected <T extends Enum<T>> void setDDEnumValue(DropDown dd, T valor) {
		if (dd != null && valor != null) {
			dd.setSelected(valor.toString());
		}
	}
	
	public void subirReporteASesion(String nombreReporte, int formatoReporte, JasperPrint reporte) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, formatoReporte);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", nombreReporte);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, reporte);
	}
	
	public void subirErrorEnReporteASesion(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
	}

	public abstract String getNombreBean();

	public abstract long getSerialVersionUID();

	private HtmlAjaxStatus getStatusGifLoading() {
		HtmlAjaxStatus status = new HtmlAjaxStatus();
		status.setId("ajCargando");
		status.setStartStyle("z-index: 5; position: fixed; top: 50%; left: 50%; margin-top: -50px; margin-left: -100px;");
		status.getFacets().put("start", this.getGiGifLoading());
		return status;
	}

	private HtmlGraphicImage getGiGifLoading() {
		HtmlGraphicImage giLoading = new HtmlGraphicImage();
		giLoading.setId("giLoading");
		giLoading.setValue("/resources/gifs/cargando.gif");
		return giLoading;
	}
}