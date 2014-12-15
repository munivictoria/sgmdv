/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trascender.presentacion.abstracts;

import jasper.ConstantesReportes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamicoArchivo;
import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.controller.ABMController;
import com.trascender.presentacion.reportes.ImpresionReporteDinamico;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author Fernando Gareis
 */
public abstract class ABMPageBean extends TrascenderAbstractPageBean {

	private StaticText stTitulo = new StaticText();

	private Button btnGuardar = new Button();
	private Button btnCancelar = new Button();
	private StaticText stSeparador = new StaticText();

	protected Label lblComentarioLogAuditoria = new Label();
	protected TextArea taComentarioLogAuditoria = new TextArea();

	private FiltroLogsAuditoria filtroLogAuditoria = new FiltroLogsAuditoria();
	private TextField tfFiltrarPropiedad = new TextField();
	private TextField tfFiltrarUsuario = new TextField();
	private TextField tfFiltrarFechaDesde = new TextField();
	private TextField tfFiltrarFechaHasta = new TextField();
	private String accion;

	// protected boolean ES_AGREGAR = false;
	// protected boolean ES_MODIFICAR = false;
	// protected boolean ES_ELIMINAR = false;
	// protected boolean ES_CONSULTAR = false;

	public FiltroLogsAuditoria getFiltroLogAuditoria() {
		return filtroLogAuditoria;
	}

	public TextArea getTaComentarioLogAuditoria() {
		return taComentarioLogAuditoria;
	}

	public void setTaComentarioLogAuditoria(TextArea taComentarioLogAuditoria) {
		this.taComentarioLogAuditoria = taComentarioLogAuditoria;
	}

	public Label getLblComentarioLogAuditoria() {
		return lblComentarioLogAuditoria;
	}

	public void setLblComentarioLogAuditoria(Label lblComentarioLogAuditoria) {
		this.lblComentarioLogAuditoria = lblComentarioLogAuditoria;
	}

	public void setFiltroLogAuditoria(FiltroLogsAuditoria filtroLogAuditoria) {
		this.filtroLogAuditoria = filtroLogAuditoria;
	}
	
	public TextField getTfFiltrarPropiedad() {
		return tfFiltrarPropiedad;
	}

	public void setTfFiltrarPropiedad(TextField tfFiltrarPropiedad) {
		this.tfFiltrarPropiedad = tfFiltrarPropiedad;
	}

	public TextField getTfFiltrarUsuario() {
		return tfFiltrarUsuario;
	}

	public void setTfFiltrarUsuario(TextField tfFiltrarUsuario) {
		this.tfFiltrarUsuario = tfFiltrarUsuario;
	}

	public TextField getTfFiltrarFechaDesde() {
		return tfFiltrarFechaDesde;
	}

	public void setTfFiltrarFechaDesde(TextField tfFiltrarFechaDesde) {
		this.tfFiltrarFechaDesde = tfFiltrarFechaDesde;
	}

	public TextField getTfFiltrarFechaHasta() {
		return tfFiltrarFechaHasta;
	}

	public void setTfFiltrarFechaHasta(TextField tfFiltrarFechaHasta) {
		this.tfFiltrarFechaHasta = tfFiltrarFechaHasta;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Button getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(Button b) {
		this.btnGuardar = b;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button b) {
		this.btnCancelar = b;
	}

	public StaticText getStSeparador() {
		return stSeparador;
	}

	public void setStSeparador(StaticText st) {
		this.stSeparador = st;
	}

	public StaticText getStTitulo() {
		return stTitulo;
	}

	public void setStTitulo(StaticText st) {
		this.stTitulo = st;
	}

	@Override
	public Body getBody1() {
		Body body1 = super.getBody1();
		body1.setOnKeyPress("return tabulador(event)");
		return body1;
	}

	@Override
	public Form getForm1() {
		Form form1 = super.getForm1();
		// Si el elemento activo es un TextArea, enter no tabula al siguiente elemento, si no
		// que realiza la accion por defecto de este componente.
		form1.setOnKeyPress("return document.activeElement.type == 'textarea' || event.keyCode!=13");
		return form1;
	}

	protected PanelAtributoDinamico panelAtributoDinamico;

	public PanelAtributoDinamico getPanelAtributoDinamico() {
		return panelAtributoDinamico;
	}

	public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
		this.panelAtributoDinamico = panelAtributoDinamico;
	}

	public void procesarDescargarAtributoDinamico(ActionEvent evento) {
		try {
			String nombre = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("atributoDinamicoSeleccionado");
			this.guardarEstadoObjetosUsados();
			for(Object cadaObject : this.getPanelAtributoDinamico().getListaAtributosDinamicos()) {
				AtributoDinamico cadaAtributo = (AtributoDinamico) cadaObject;
				if(cadaAtributo.getNombre().equals(nombre)) {
					AtributoDinamicoArchivo archivoDinamico = (AtributoDinamicoArchivo) cadaAtributo;
					// guardo en memoria los datos para el servlet
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("bytesDocumentoDescargable", archivoDinamico.getValor());
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nombreArchivoOriginal", archivoDinamico.getNombreArchivo());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*********************************/

	public String btnGuardar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			try {

				// No validar si se esta consultando o eliminando.
				if(getController().guardaEstadoObjetosUsados()) {
					this.guardarEstadoObjetosUsados();
				}
				Validador v = this.getController().getValidador();
				if(v != null && v.getErrores().size() > 0) {
					error("Existen Errores:");
					for(int i = 0; i < v.getErrores().size(); i++) {
						warn(v.getErrores().toArray()[i].toString());
					}
					return null;
				}

				Object locObject = this.getElementoPila().getObjetos().get(0);

				EntidadTrascender locEntidad = null;

				if(locObject instanceof EntidadTrascender) {
					locEntidad = (EntidadTrascender) locObject;
				} else if(locObject instanceof AuditoriaIndirecta) {
					locEntidad = ((AuditoriaIndirecta) locObject).getEntidadTrascender();
				}

				if(locEntidad != null) {
					locEntidad.setLlaveUsuarioAuditoria(this.getSessionBean1().getLlave());
					if(this.getTaComentarioLogAuditoria().getText() != null) {
						locEntidad.setComentarioAuditoria(this.getTaComentarioLogAuditoria().getText().toString());
					}
				}

				String mensaje = getController().accionBotonAceptar(locObject);
				info(mensaje);
				this.getRequestBean1().setRefrescarTabla(this.getController().recargarPaginaAdmin());
				retorno = this.prepararParaVolver(this.getAccion());
			} catch(Exception ex) {
				log(getCasoNavegacion() + "_GuardarError:", ex);
				error(ex.getMessage());
			}

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnCancelar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			// CAMBIAR: comentar esta linea y cambiar el retorno
			this.getComunicationBean().setListaLogs(null);
			retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	/**
	 * <p>
	 * M�todo de devoluci�n de llamada al que se llama cuando se navega hasta esta p�gina, ya sea directamente mediante un URL o de manera indirecta a trav�s de
	 * la navegaci�n de p�ginas. Puede personalizar este m�todo para adquirir recursos que se necesitar�n para los controladores de eventos y m�todos del
	 * proceso, sin tener en cuenta si esta p�gina realiza procesamiento de devoluci�n de env�os.
	 * </p>
	 * 
	 * <p>
	 * Tenga en cuenta que si la petici�n actual es una devoluci�n de env�o, los valores de propiedad de los componentes <strong>no</strong> representan ning�n
	 * valor enviado con esta petici�n. En su lugar, representan los valores de propiedades que se guardaron para esta vista cuando se proces�.
	 * </p>
	 */
	@Override
	public void init() {
		// Realizar iniciaciones heredadas de la superclase
		super.init();
		this.setAccion(this.getSessionBean1().getAccion());
		if(this.getTablaLogs() != null && this.getTablaLogs().getTableRowGroup() != null) {
			this.getApplicationBean1().aplicarPropiedadesTablaAdmin(this.getTablaLogs(), this.getTablaLogs().getTableRowGroup());
		}
		try {
			_init();
		} catch(Exception e) {
			log(getCasoNavegacion() + " Initialization Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
	}

	private void setAtributosComentarioAuditoria() {
		this.getLblComentarioLogAuditoria().setStyleClass("label");
		this.getLblComentarioLogAuditoria().setText("Comentario de modificación");

		this.getTaComentarioLogAuditoria().setColumns(40);
		this.getTaComentarioLogAuditoria().setStyleClass("textField");
		this.getTaComentarioLogAuditoria().setRows(5);
	}

	@Override
	public void prerender() {
		super.prerender();
		this.setearTituloPagina();
		this.setearTextoBotones();
		this.setearEstiloBody();
		this.getController().ocultarDeshabilitarEnVista();
		this.getBtnGuardar().setRendered(this.getController().mostrarBotonAceptar());
		this.getBtnCancelar().setRendered(this.getController().mostrarBotonCancelar());
		this.getStSeparador().setRendered(this.getController().mostrarStSeparador());

		try {
			this._prerender();

			this.setAtributosComentarioAuditoria();

			Object locEntidad = this.obtenerObjetoDelElementoPila(0);
			if(locEntidad instanceof EntidadTrascender && ((EntidadTrascender) locEntidad).getComentarioAuditoria() != null) {
				this.getTaComentarioLogAuditoria().setText(((EntidadTrascender) locEntidad).getComentarioAuditoria());
			}

			if(this.getComunicationBean().getListaLogs() == null) {
				this.getComunicationBean().setListaLogs(this.getTablaLogs().getLdpLogs().getList());
			}
			
			// Seteamos a la tabla de logs las propiedades de las tablas paginadas de los admins...
			this.getApplicationBean1().aplicarPropiedadesTablaAdmin(this.getTablaLogs(), this.getTablaLogs().getTableRowGroup());
			
			this.getTablaLogs().setListaLogsFiltrada(this.getTablaLogs().getLdpLogs().getList());

			List<LogAuditoria> listaLogs = new ArrayList<LogAuditoria>();
			if(this.getTablaLogs().getListaLogsFiltrada() != null && this.getTablaLogs().getListaLogsFiltrada().size() > 0) {
				listaLogs.addAll(this.getTablaLogs().getListaLogsFiltrada());
			}

			// -- Se setea nuevamente el filtro porque se vuelve a filtrar y se perdia el valor el filtro,
			// entonces te listaba todos los resultados de nuevo...
			this.setFiltroLogAuditoria(new FiltroLogsAuditoria());
			setearComponentesFiltroTablaLogs_action();
			// --
			filtrarLogs(listaLogs, this.getFiltroLogAuditoria());

			this.getTablaLogs().setListaLogsFiltrada(listaLogs);
			this.getTablaLogs().getLdpLogs().setList(listaLogs);
		} catch(Exception e) {
			log(this.getCasoNavegacion() + " prerender Failure", e);
			throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
		}
	}

	@Override
	protected void haciaAtrasTransaccion() {
		// Nada por ahora
	}

	@Override
	protected boolean puedeSerPaginaInicial() {
		return false;
	}

	@Override
	protected String getNombrePagina() {
		try {
			return this.getController().getTituloPagina();
		} catch(NullPointerException e) {
			// Es porque no existe el ElementoPila (la primera vez que entra a
			// la pagina).
			// Recuperamos el controller directamente y devolvemos el nombre
			return this.getRequestBean1().getAbmController().getTituloPagina();
		}
	}

	protected ABMController getController() {
		return this.getElementoPila().getAbmController();
	}

	private void setearEstiloBody() {
		String estilo = "background-color: rgb(" + this.getController().getCodigoColores() + "); -rave-layout: grid";
		this.getBody1().setStyle(estilo);
	}

	private void setearTextoBotones() {
		this.getBtnGuardar().setText(this.getController().getTextoBotonAceptar());
		this.getBtnCancelar().setText(this.getController().getTextoBotonCancelar());
	}

	private void setearTituloPagina() {
		this.getHead1().setTitle(this.getController().getTituloPagina());
	}

	protected void limpiarObjeto(int posicion, Class tipoClase, TextField campo) {
		try {
			this.getElementoPila().getObjetos().set(posicion, tipoClase.newInstance());
			campo.setText("");
		} catch(Exception ex) {
		}
	}

	protected String navegarParaSeleccionar(String pCasoNavegacion, int pPosicionEnPila) {
		String retorno;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(pPosicionEnPila));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			retorno = pCasoNavegacion;
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	protected String navegarParaSeleccionar(String pCasoNavegacion) {
		String retorno;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			retorno = pCasoNavegacion;
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public void limpiarObjeto(int posicion, TextField campo) {
		this.getElementoPila().getObjetos().set(posicion, null);
		this.limpiarObjeto(campo);
	}

	public void limpiarObjeto(int posicion, DropDown campo) {
		this.getElementoPila().getObjetos().set(posicion, null);
		this.limpiarObjeto(campo);
	}

	public void limpiarObjeto(int posicion, StaticText campo) {
		try {
			this.getElementoPila().getObjetos().set(posicion, null);
			if(campo != null) {
				campo.setText(null);

			}
		} catch(Exception ex) {
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		if(this.getTaComentarioLogAuditoria().getText() != null && !this.getElementoPila().getObjetos().isEmpty()) {
			Object locEntidad = this.obtenerObjetoDelElementoPila(0);
			if(locEntidad instanceof EntidadTrascender) {
				((EntidadTrascender) locEntidad).setComentarioAuditoria(this.getTaComentarioLogAuditoria().getText().toString());
			}
		}
	}

	public TablaLogs getTablaLogs() {
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			return this.getElementoPila().getTablaLogs();
		} else {
			return new TablaLogs();
		}
	}

	public void setTablaLogs(TablaLogs tablaLogs) {
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// this.getElementoPila().setTablaLogs(tablaLogs);
		}
	}

	protected String toAbmConSeleccion(ABMController pController, String pCasoNavegacion, String radioButton, ObjectListDataProvider ldp) {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			Object objetoAbm = this.getObjetoSeleccionadoDeTabla(radioButton, ldp);
			if(objetoAbm != null) {
				this.getRequestBean1().setObjetoABM(objetoAbm);
				retorno = this.toAbm(pController, pCasoNavegacion);
			} else {
				retorno = null;
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	protected String toAbm(ABMController pController, String pCasoNavegacion) {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			getRequestBean1().setAbmController(pController);
			retorno = pCasoNavegacion;
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	protected <T> T getObjetoSeleccionadoDeTabla(String radioButton, ObjectListDataProvider ldp) {
		Object locRetorno = null;
		String aRowId = (String) RadioButton.getSelected(radioButton);
		RowKey rk = ldp.getRowKey(aRowId);
		if(rk != null) {
			int index = getNroFila(rk.toString());
			locRetorno = ldp.getObjects()[index];
		}
		return (T) locRetorno;
	}

	private void filtrarLogs(List<LogAuditoria> pListaLogs, FiltroLogsAuditoria pFiltro) {
		List<LogAuditoria> listaFiltrada = new ArrayList<LogAuditoria>();

		if(pFiltro != null) {
			listaFiltrada = pListaLogs;
			for(Iterator<LogAuditoria> iterator = listaFiltrada.iterator(); iterator.hasNext();) {
				LogAuditoria cadaLog = iterator.next();
				
				SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaDesde = null;
				Date fechaHasta = null;
				try {
					if(pFiltro.fechaDesde != null) {
						fechaDesde = formatoFecha.parse(pFiltro.fechaDesde);
					}
					if(pFiltro.fechaHasta != null) {
						fechaHasta = formatoFecha.parse(pFiltro.fechaHasta);
					}
				} catch(ParseException e) {
					e.printStackTrace();
				}

				if(pFiltro.propiedad != null && (cadaLog.getPropiedad() == null || !cadaLog.getPropiedad().toLowerCase().contains(pFiltro.propiedad.toLowerCase()))) {
					iterator.remove();
				} 
				else 
					if(pFiltro.nombreUsuario != null && !cadaLog.getUsuario().getUser().toLowerCase().contains(pFiltro.nombreUsuario.toLowerCase())) {
						iterator.remove();
				} 
				else 
					if(fechaDesde != null && (!Util.isFechaEqualsNoTima(cadaLog.getFecha(), fechaDesde) && !Util.isFechaAfterNoTima(cadaLog.getFecha(), fechaDesde))) {
						iterator.remove();
				} 
				else 
					if(fechaHasta != null && (Util.isFechaAfterNoTima(cadaLog.getFecha(),fechaHasta))) {
						iterator.remove();
				}
			}
		} else {
			listaFiltrada.addAll(this.getComunicationBean().getListaLogs());
		}
	}

	public void setearComponentesFiltroTablaLogs_action() {
		this.getFiltroLogAuditoria().propiedad = getTextFieldValue(this.getTfFiltrarPropiedad());
		this.getFiltroLogAuditoria().nombreUsuario = getTextFieldValue(this.getTfFiltrarUsuario());
		this.getFiltroLogAuditoria().fechaDesde = getTextFieldValue(this.getTfFiltrarFechaDesde());
		this.getFiltroLogAuditoria().fechaHasta = getTextFieldValue(this.getTfFiltrarFechaHasta());
	}

	public void limpiarFiltradoTablaLogs_action() {
		this.getTfFiltrarPropiedad().setText("");
		this.getTfFiltrarUsuario().setText("");
		this.getTfFiltrarFechaDesde().setText("");
		this.getTfFiltrarFechaHasta().setText("");
		this.getTablaLogs().getListaLogsFiltrada().clear();
		this.setFiltroLogAuditoria(new FiltroLogsAuditoria());

		if(this.getComunicationBean().getListaLogs() != null) {
			this.getTablaLogs().getLdpLogs().setList(this.getComunicationBean().getListaLogs());
		}
	}

	public String btnExportar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			try {
				if(this.getTablaLogs() != null) {
					TableRowGroup trg = this.getTablaLogs().getTableRowGroup();
					List locListaResultados = this.getTablaLogs().getLdpLogs().getList();
					if(locListaResultados.size() > 0) {
						String formatoExportar = this.getDdFormatoExportar().getSelected() != null ? this.getDdFormatoExportar().getSelected().toString() : "";
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
								.put(ConstantesReportes.FORMATO_REPORTE, formatoExportar.equals("PDF") ? ConstantesReportes.PDF : ConstantesReportes.XLSX);
						JasperPrint jp = ImpresionReporteDinamico.imprimirLista(locListaResultados, trg, "Reporte Din\341mico");
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte");
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
					} else {
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
					}
				} else {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
				}
			} catch(Exception e) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ErrorEnReporte", true);
				log(getCasoNavegacion() + "_ReporteDinamicoError: ", e);
				error(getNombrePagina() + " - ReporteDinamico: " + e.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	class FiltroLogsAuditoria {
		public String propiedad;
		public String nombreUsuario;
		public String fechaDesde;
		public String fechaHasta;
	}
}