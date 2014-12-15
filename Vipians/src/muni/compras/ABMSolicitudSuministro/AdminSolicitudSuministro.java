/*
 * AdminSolicitudSuministro.java
 *
 * Created on 28 de noviembre de 2006, 14:59
 * Copyright Trascender SRL
 */

package muni.compras.ABMSolicitudSuministro;

import jasper.ConstantesReportes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.IntegerConverter;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

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
import com.trascender.compras.recurso.filtros.FiltroSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.EstadoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminSolicitudSuministro extends AdminPageBean {

	protected StaticText stArea = new StaticText();
	protected StaticText stDescripcion = new StaticText();
	protected StaticText stFechaEmision = new StaticText();
	protected StaticText stNumero = new StaticText();
	protected StaticText stEstado = new StaticText();
	protected StaticText stSeparador5 = new StaticText();
	protected StaticText stSeparador6 = new StaticText();
	protected StaticText stSeparador7 = new StaticText();
	protected StaticText stFechaDesde = new StaticText();
	protected StaticText stFechaHasta = new StaticText();
	protected StaticText stFiltrarPor = new StaticText();

	protected Button btnImprimirReporte = new Button();
	protected HtmlAjaxCommandButton btnLimpiarProducto = new HtmlAjaxCommandButton();
	protected Button btnSeleccionarBien = new Button();

	protected Label lblNro = new Label();
	protected Label lblEstado = new Label();
	protected Label lblProducto = new Label();
	protected Label lblArea = new Label();
	protected Label lblFechaDesde = new Label();
	protected Label lblFechaHasta = new Label();
	protected Label lblSecretaria = new Label();
	protected Label lblAccion = new Label();
	protected Label lblUrgente = new Label();

	protected TextField tfBien = new TextField();
	protected TextField tfFechaDesde = new TextField();
	protected TextField tfFechaHasta = new TextField();
	protected TextField tfNumero = new TextField();

	protected TableColumn tcArea = new TableColumn();
	protected TableColumn tcDescripcion = new TableColumn();
	protected TableColumn tcFechaEmision = new TableColumn();
	protected TableColumn tcNumero = new TableColumn();
	protected TableColumn tcEstado = new TableColumn();

	protected DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

	protected ObjectListDataProvider ldpSolicitudSuministro = new ObjectListDataProvider();

	protected DropDown ddEstado = new DropDown();
	protected SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();

	protected SingleSelectOptionsList ddAreaOptions = new SingleSelectOptionsList();
	protected DropDown ddArea = new DropDown();

	protected SingleSelectOptionsList ddSecretariaOptions = new SingleSelectOptionsList();
	protected DropDown ddSecretaria = new DropDown();
	
	protected DropDown ddUrgente = new DropDown();
	protected SingleSelectOptionsList ddUrgenteDefaultOptions = new SingleSelectOptionsList();

	protected IntegerConverter integerConverter1 = new IntegerConverter();

	// ***GETTERS & SETTERS
	
	public DropDown getDdArea() {
		return ddArea;
	}

	public Label getLblUrgente() {
		return lblUrgente;
	}

	public void setLblUrgente(Label lblUrgente) {
		this.lblUrgente = lblUrgente;
	}

	public DropDown getDdUrgente() {
		return ddUrgente;
	}

	public void setDdUrgente(DropDown ddUrgente) {
		this.ddUrgente = ddUrgente;
	}

	public SingleSelectOptionsList getDdUrgenteDefaultOptions() {
		return ddUrgenteDefaultOptions;
	}

	public void setDdUrgenteDefaultOptions(
			SingleSelectOptionsList ddUrgenteDefaultOptions) {
		this.ddUrgenteDefaultOptions = ddUrgenteDefaultOptions;
	}

	public Label getLblAccion() {
		return lblAccion;
	}

	public void setLblAccion(Label lblAccion) {
		this.lblAccion = lblAccion;
	}

	public HtmlAjaxCommandButton getBtnLimpiarProducto() {
		return btnLimpiarProducto;
	}

	public void setBtnLimpiarProducto(HtmlAjaxCommandButton btnLimpiarProducto) {
		this.btnLimpiarProducto = btnLimpiarProducto;
	}

	public SingleSelectOptionsList getDdSecretariaOptions() {
		return ddSecretariaOptions;
	}

	public void setDdSecretariaOptions(SingleSelectOptionsList ddSecretariaOptions) {
		this.ddSecretariaOptions = ddSecretariaOptions;
	}

	public DropDown getDdSecretaria() {
		return ddSecretaria;
	}

	public void setDdSecretaria(DropDown ddSecretaria) {
		this.ddSecretaria = ddSecretaria;
	}

	public void setDdArea(DropDown ddArea) {
		this.ddArea = ddArea;
	}

	public SingleSelectOptionsList getDdAreaOptions() {
		return ddAreaOptions;
	}

	public void setDdAreaOptions(SingleSelectOptionsList ddAreaOptions) {
		this.ddAreaOptions = ddAreaOptions;
	}

	public StaticText getStFiltrarPor() {
		return stFiltrarPor;
	}

	public void setStFiltrarPor(StaticText stFiltrarPor) {
		this.stFiltrarPor = stFiltrarPor;
	}

	public TableColumn getTcArea() {
		return tcArea;
	}

	public void setTcArea(TableColumn tcArea) {
		this.tcArea = tcArea;
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

	public TableColumn getTcFechaEmision() {
		return tcFechaEmision;
	}

	public void setTcFechaEmision(TableColumn tcFechaEmision) {
		this.tcFechaEmision = tcFechaEmision;
	}

	public TableColumn getTcNumero() {
		return tcNumero;
	}

	public void setTcNumero(TableColumn tcNumero) {
		this.tcNumero = tcNumero;
	}

	public Label getLblArea() {
		return lblArea;
	}

	public void setLblArea(Label lblArea) {
		this.lblArea = lblArea;
	}

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	public Label getLblFechaDesde() {
		return lblFechaDesde;
	}

	public void setLblFechaDesde(Label lblFechaDesde) {
		this.lblFechaDesde = lblFechaDesde;
	}

	public Label getLblProducto() {
		return lblProducto;
	}

	public void setLblProducto(Label lblProducto) {
		this.lblProducto = lblProducto;
	}

	public Label getLblSecretaria() {
		return lblSecretaria;
	}

	public void setLblSecretaria(Label lblSecretaria) {
		this.lblSecretaria = lblSecretaria;
	}

	public Label getLblFechaHasta() {
		return lblFechaHasta;
	}

	public void setLblFechaHasta(Label lblFechaHasta) {
		this.lblFechaHasta = lblFechaHasta;
	}

	public StaticText getStArea() {
		return stArea;
	}

	public void setStArea(StaticText stArea) {
		this.stArea = stArea;
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

	public StaticText getStFechaDesde() {
		return stFechaDesde;
	}

	public void setStFechaDesde(StaticText stFechaDesde) {
		this.stFechaDesde = stFechaDesde;
	}

	public StaticText getStFechaEmision() {
		return stFechaEmision;
	}

	public void setStFechaEmision(StaticText stFechaEmision) {
		this.stFechaEmision = stFechaEmision;
	}

	public StaticText getStFechaHasta() {
		return stFechaHasta;
	}

	public void setStFechaHasta(StaticText stFechaHasta) {
		this.stFechaHasta = stFechaHasta;
	}

	public StaticText getStNumero() {
		return stNumero;
	}

	public void setStNumero(StaticText stNumero) {
		this.stNumero = stNumero;
	}

	public StaticText getStSeparador5() {
		return stSeparador5;
	}

	public void setStSeparador5(StaticText stSeparador5) {
		this.stSeparador5 = stSeparador5;
	}

	public StaticText getStSeparador6() {
		return stSeparador6;
	}

	public void setStSeparador6(StaticText stSeparador6) {
		this.stSeparador6 = stSeparador6;
	}

	public StaticText getStSeparador7() {
		return stSeparador7;
	}

	public void setStSeparador7(StaticText stSeparador7) {
		this.stSeparador7 = stSeparador7;
	}

	public Label getLblNro() {
		return lblNro;
	}

	public void setLblNro(Label lblNro) {
		this.lblNro = lblNro;
	}

	public TextField getTfNumero() {
		return tfNumero;
	}

	public void setTfNumero(TextField tfNumero) {
		this.tfNumero = tfNumero;
	}

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dateTimeConverter1) {
		this.dateTimeConverter1 = dateTimeConverter1;
	}

	public ObjectListDataProvider getLdpSolicitudSuministro() {
		return ldpSolicitudSuministro;
	}

	public void setLdpSolicitudSuministro(ObjectListDataProvider oldp) {
		this.ldpSolicitudSuministro = oldp;
	}

	public TextField getTfBien() {
		return tfBien;
	}

	public void setTfBien(TextField tf) {
		this.tfBien = tf;
	}

	public TextField getTfFechaDesde() {
		return tfFechaDesde;
	}

	public void setTfFechaDesde(TextField tf) {
		this.tfFechaDesde = tf;
	}

	public TextField getTfFechaHasta() {
		return tfFechaHasta;
	}

	public void setTfFechaHasta(TextField tf) {
		this.tfFechaHasta = tf;
	}

	public Button getBtnSeleccionarBien() {
		return btnSeleccionarBien;
	}

	public void setBtnSeleccionarBien(Button b) {
		this.btnSeleccionarBien = b;
	}

	public DropDown getDdEstado() {
		return ddEstado;
	}

	public void setDdEstado(DropDown dd) {
		this.ddEstado = dd;
	}

	public SingleSelectOptionsList getDdEstadoDefaultOptions() {
		return ddEstadoDefaultOptions;
	}

	public void setDdEstadoDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddEstadoDefaultOptions = ssol;
	}

	public IntegerConverter getIntegerConverter1() {
		return integerConverter1;
	}

	public void setIntegerConverter1(IntegerConverter ic) {
		this.integerConverter1 = ic;
	}

	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button b) {
		this.btnImprimirReporte = b;
	}

	private DropDown ddOpcionesExtras = new DropDown();
	private SingleSelectOptionsList ddOpcionesExtrasOptions = new SingleSelectOptionsList();

	public DropDown getDdOpcionesExtras() {
		return ddOpcionesExtras;
	}

	public void setDdOpcionesExtras(DropDown ddOpcionesExtras) {
		this.ddOpcionesExtras = ddOpcionesExtras;
	}

	public SingleSelectOptionsList getDdOpcionesExtrasOptions() {
		return ddOpcionesExtrasOptions;
	}

	public void setDdOpcionesExtrasOptions(SingleSelectOptionsList ddOpcionesExtrasOptions) {
		this.ddOpcionesExtrasOptions = ddOpcionesExtrasOptions;
	}

	private Button btnIr = new Button();

	public Button getBtnIr() {
		return btnIr;
	}

	public void setBtnIr(Button btnIr) {
		this.btnIr = btnIr;
	}

	// ***METHODS***
	public AdminSolicitudSuministro() {
	}

	@Override
	protected void _init() throws Exception {

		// Option[] op = null;
		// // Tipo Documento
		// op =
		// this.getApplicationBean1().getMgrDropDown().armarArrayOptions(SolicitudSuministro.Estado.values(),
		// "may");
		// ddEstadoDefaultOptions.setOptions(op);

		dateTimeConverter1.setTimeZone(null);
		dateTimeConverter1.setPattern("dd/MM/yy");
		dateTimeConverter1.setTimeStyle("full");

		Set<String> locListaSecretarias = this.getCommunicationComprasBean().getMapaSecretariasArea().keySet();
		Option[] opSecretarias = new Option[locListaSecretarias.size() + 1];
		int i = 0;
		opSecretarias[i++] = new Option("", "");
		for(String cadaSecretaria : locListaSecretarias) {
			opSecretarias[i++] = new Option(cadaSecretaria, cadaSecretaria);
		}
		this.ddSecretariaOptions.setOptions(opSecretarias);

		Set<String> locListaAreas = this.getCommunicationComprasBean().getMapaAreasSolSum().keySet();
		Option[] opAreas = new Option[locListaAreas.size() + 1];
		i = 0;
		opAreas[i++] = new Option("", "");
		for(String cadaArea : locListaAreas) {
			opAreas[i++] = new Option(cadaArea, cadaArea);
		}
		ddAreaOptions.setOptions(opAreas);

		Set<String> locListaNombresEstados = getApplicationBean1().getMapaEstadosSolicitudSuministro().keySet();
		Option[] opEstados = new Option[locListaNombresEstados.size() + 1];
		i = 0;
		opEstados[i++] = new Option("", "");
		for(String cadaEstado : locListaNombresEstados) {
			opEstados[i++] = new Option(cadaEstado, cadaEstado);
		}
		ddEstadoDefaultOptions.setOptions(opEstados);

		Option[] opOpcionesExtras = new Option[5];
		i = 0;
		opOpcionesExtras[i++] = new Option("", "");
		opOpcionesExtras[i++] = new Option("Modificar Cuentas", "Modificar Cuentas");
		opOpcionesExtras[i++] = new Option("Presupuestar", "Presupuestar");
		opOpcionesExtras[i++] = new Option("Firmar Solicitud", "Firmar Solicitud");
		opOpcionesExtras[i++] = new Option("Finalizar Solicitud", "Finalizar Solicitud");
		ddOpcionesExtrasOptions.setOptions(opOpcionesExtras);
		
		Option[] opUrgente = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(new Object[]{"Si", "No"}, "");
		ddUrgenteDefaultOptions.setOptions(opUrgente);
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		FiltroSolicitudSuministro locFiltro = getFiltro();
		locFiltro.setFechaDesde(locFiltro.getPrimerDiaAnioActual());
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroSolicitudSuministro locFiltro = getFiltro();

		locFiltro.setFechaDesde(this.getTextFieldValueDate(getTfFechaDesde()));
		locFiltro.setFechaHasta(this.getTextFieldValueDate(getTfFechaHasta()));
		locFiltro.setNumero(this.getTextFieldValueInteger(getTfNumero()));
		locFiltro.setArea(this.getDDObjectValue(getDdArea(), getCommunicationComprasBean().getMapaAreasSolSum()));
		locFiltro.setSecretaria(this.getDDObjectValue(getDdSecretaria(), getCommunicationComprasBean().getMapaSecretariaSolSum()));
		locFiltro.setEstado(this.getDDObjectValue(getDdEstado(), getApplicationBean1().getMapaEstadosSolicitudSuministro()));
		
		if(!this.getDdUrgente().getSelected().equals("")){
			locFiltro.setUrgente(this.getDdUrgente().getSelected().toString().equalsIgnoreCase("si")?true:false);
		} else{
			locFiltro.setUrgente(null);
		}
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroSolicitudSuministro locFiltro = getFiltro();

		if(locFiltro.getBien() != null) {
			this.getTfBien().setText(locFiltro.getBien());
		} else {
			this.getTfBien().setText(null);
		}
		if(locFiltro.getArea() != null) {
			this.getDdArea().setSelected(locFiltro.getArea().toString());
		}
		if(locFiltro.getSecretaria() != null) {
			this.getDdSecretaria().setSelected(locFiltro.getSecretaria().toString());
		}
		this.getTfFechaDesde().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaDesde()));
		this.getTfFechaHasta().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaHasta()));
		this.getTfNumero().setText(locFiltro.getNumero());

		if(locFiltro.getEstado() != null) {
			this.getDdEstado().setSelected(locFiltro.getEstado().getNombre());
		}
		
		this.getDdUrgente().setSelected(locFiltro.getUrgente());
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().findListadoSolicitudSuministro((FiltroSolicitudSuministro) pFiltro);
	}

	public void actionListenerDropSecretaria(ActionEvent event) {
		Set<String> locListaAreas = this.getCommunicationComprasBean()
				.getMapaAreasSolSum(this.getDDObjectValue(getDdSecretaria(), getCommunicationComprasBean().getMapaSecretariaSolSum())).keySet();
		Option[] opAreas = new Option[locListaAreas.size() + 1];
		int i = 0;
		opAreas[i++] = new Option("", "");
		for(String cadaArea : locListaAreas) {
			opAreas[i++] = new Option(cadaArea, cadaArea);
		}
		ddAreaOptions.setOptions(opAreas);
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroSolicitudSuministro locFiltro = getFiltro();
		locFiltro.setFechaHasta(null);
		locFiltro.setNumero(null);
		locFiltro.setArea(null);
		locFiltro.setSecretaria(null);
		locFiltro.setEstado(null);
		locFiltro.setBien(null);
		locFiltro.setUrgente(null);
		locFiltro.setFechaDesde(locFiltro.getPrimerDiaAnioActual());

		this.getDdEstado().setSelected("");
		this.getTfBien().setText("");
		this.getDdArea().setSelected("");
		this.getDdSecretaria().setSelected("");
		this.getTfFechaDesde().setText("");
		this.getTfFechaHasta().setText("");
		this.getTfNumero().setText("");
		this.getDdOpcionesExtras().setSelected(null);
		this.getDdUrgente().setSelected(null);
		this.getTfFechaDesde().setText(Conversor.getStringDeFechaCorta(locFiltro.getFechaDesde()));
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpSolicitudSuministro();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaSolicitudesSuministro();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationComprasBean().setListaSolicitudesSuministro(lista);
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationComprasBean().getTablaSolicitudSuministro();
	}

	public String btnSeleccionarBien_action() {
		return navegarParaSeleccionar("AdminBien");
	}

	public String btnImprimirReporte_action() throws Exception {
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
					SolicitudSuministro locSolicitudSuministro = (SolicitudSuministro) obj;
					// this.getSessionBean1().setObjetoImpresion(obj);
					this.getCommunicationComprasBean().getRemoteSystemReportesCompras().setLlave(this.getSessionBean1().getLlave());
					JasperPrint jp = this.getCommunicationComprasBean().getRemoteSystemReportesCompras()
							.getReporteSolicitudSuministro(locSolicitudSuministro.getIdSolicitudSuministro());

					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_SolicitudSuministro");
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
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.ERRROR_EN_REPORTE, true);
		}
		return retorno;
	}

	public String btnLimpiarProducto_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(this.getTfBien());
			FiltroSolicitudSuministro locFiltro = getFiltro();
			locFiltro.setBien(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public void valueChangeEvent(ValueChangeEvent event) {
		this.btnImprimirReporte.setDisabled(false);
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		SolicitudSuministro locSolicitudSuministro = (SolicitudSuministro) pObject;
		getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(this.getSessionBean1().getLlave());
		return getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().findSolicitudSuministroByID(locSolicitudSuministro.getIdSolicitudSuministro());
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Solicitudes de Suministro";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminSolicitudSuministro";
	}

	public String btnIr_action() {
		Object valor = this.getDdOpcionesExtras().getSelected();
		String cadena = (String) valor;
		String retorno = toAbm(new SolicitudSuministroModel().new ModificarCuentaController());
		
		if(cadena.equals("")) {
			warn("Debe seleccionar una opción de la lista.");
			retorno = "";
		}
		if(cadena.equals("Modificar Cuentas")) {
			SolicitudSuministro locSolicitudSuministro = (SolicitudSuministro) getObjetoSeleccionado();
			
			if(locSolicitudSuministro == null) {
				retorno = null;
			}

			if(locSolicitudSuministro.getEstado().isEstadoFinal()) {
				warn("La Solicitud no puede modificarse en el estado actual.");
				retorno = null;
			}
			try {
				if(!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), SolicitudSuministro.serialVersionUID, Permiso.Accion.AUDITH)) {
					warn("No tiene permisos para modificar la Solicitud.");
					retorno = null;
				}
			} catch(RemoteException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}

			Object objetoSeleccionado = this.getObjetoSeleccionado();
			if (objetoSeleccionado != null) {
				this.getPaginatedTable().setObjetoSeleccionado(objetoSeleccionado);
			}
			
			return retorno;
		}
		if(cadena.equals("Presupuestar")) {
			SolicitudSuministro locSolicitudSuministro = (SolicitudSuministro) getObjetoSeleccionado();

			if(locSolicitudSuministro == null) {
				return null;
			}

//			if(locSolicitudSuministro.getEstado().isEstadoFinal()) {
//				warn("La Solicitud no puede modificarse en el estado actual.");
//				return null;
//			}
			try {
				if(!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), SolicitudSuministro.serialVersionUID, Permiso.Accion.AUDITH)) {
					warn("No tiene permisos para modificar la Solicitud.");
					return null;
				}
			} catch(RemoteException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}

			return toAbm(new SolicitudSuministroModel().new PresupuestarController());
		}
		if(cadena.equals("Firmar Solicitud")) {
			SolicitudSuministro locSolicitudSuministro = (SolicitudSuministro) getObjetoSeleccionado();
			Usuario locUsuario = getSessionBean1().getUsuario();
			if(locSolicitudSuministro == null) {
				return null;
			}
			try {
				 this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().validarFirma(locSolicitudSuministro, locUsuario);
				 return toAbm(new SolicitudSuministroModel().new FirmarController());
			} catch (Exception e) {
				error(e.getMessage());
			}
			
		}
		if(cadena.equals("Finalizar Solicitud")) {
			SolicitudSuministro locSolicitudSuministro = (SolicitudSuministro) getObjetoSeleccionado();
			
			if(locSolicitudSuministro == null) {
				return null;
			}

			if(locSolicitudSuministro.getEstado().isEstadoFinal()) {
				warn("La Solicitud ya se encuentra Finalizada.");
				return null;
			}
			
			List<EstadoSolicitudSuministro> locListaEstadosFinalizacion = null;
			try {
				locListaEstadosFinalizacion = this.getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().getListaEstadosFinalizacion(this.getSessionBean1().getLlave(), locSolicitudSuministro);
			} catch (TrascenderException e) {
				e.printStackTrace();
			}
			if(locListaEstadosFinalizacion == null || locListaEstadosFinalizacion.isEmpty()){
				warn("No posee autorización para finalizar la Solicitud");
				return null;
			}
			
			this.getCommunicationComprasBean().setListaEstadosFinalizacion(locListaEstadosFinalizacion);
			return toAbm(new SolicitudSuministroModel().new FinalizarController());
		}
		return "";
	}

	public String btnAgregar_action() {
		return toAbm(new SolicitudSuministroModel().new AgregarController());
	}

	public String btnModificar_action() {

		SolicitudSuministro locSolicitudSuministro = (SolicitudSuministro) getObjetoSeleccionado();

		if(locSolicitudSuministro == null) {
			return null;
		}

		if(!locSolicitudSuministro.getEstado().isModificable()) {
			warn("La Solicitud no puede modificarse en el estado actual.");
			Object objetoSeleccionado = this.getObjetoSeleccionado();
			if (objetoSeleccionado != null) {
				this.getPaginatedTable().setObjetoSeleccionado(objetoSeleccionado);
			}
			return null;
		}
		return toAbm(new SolicitudSuministroModel().new ModificarController());
	}

	public String btnConsultar_action() {
		return toAbm(new SolicitudSuministroModel().new ConsultarController());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof Bien) {
			FiltroSolicitudSuministro locFiltro = getFiltro();
			Bien bien = (Bien) pObject;

			locFiltro.setBien(bien);
		}
	}

	@Override
	public long getSerialVersionUID() {
		return SolicitudSuministro.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{compras$ABMSolicitudSuministro$AdminSolicitudSuministro}";
	}
	
	public void setBienAutocompletar(String pId, String pIdSubSesion) throws Exception { // aunque no se usa el ID de subsession
		FiltroSolicitudSuministro locFiltro = this.getFiltro();
		Long id = Long.parseLong(pId);
		Bien bien = null;
		
		try {
			bien = (Bien) this.getCommunicationComprasBean().getRemoteSystemAdministracionBienes().findBienByID(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}
		
		locFiltro.setBien(bien);
	}
}