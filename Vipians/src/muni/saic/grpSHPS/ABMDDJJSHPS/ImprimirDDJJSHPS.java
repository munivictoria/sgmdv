/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.saic.grpSHPS.ABMDDJJSHPS;

import jasper.ConstantesReportes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.imageio.IIOException;

import muni.Zip;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.RadioButtonGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionSHPS;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.DeclaracionJuradaSHPS;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

/**
 * 
 * @author danilo
 */
public class ImprimirDDJJSHPS extends ABMPageBean {

	private final String NOMBRE_PAGINA = "Imprimir DDJJSHPS";
	private final String CASO_NAVEGACION = "ImprimirDDJJSHPS";

	@Override
	public ElementoPila getElementoPila() {
		return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
	}

	@Override
	protected void _init() throws Exception {

		getApplicationBean1().aplicarPropiedadesTablaAdmin(getTable1(), getTableRowGroup1());

		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalSHPS().keySet());
		llenarDD(ddCalendariosOptions, this.getCommunicationSAICBean().getMapaCalendariosSHPS(null).keySet());
		llenarDD(ddPeriodosOptions, this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalSHPS(null).keySet());
		llenarDD(ddCuotasOptions, this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalSHPS(null).keySet());

		Option[] opTipoBusqueda = new Option[2];
		Option opcion = new Option("Un PDF");
		opTipoBusqueda[0] = opcion;
		// Option opcion2 = new Option("Un PDF por Obligación");
		// opTipoBusqueda[1] = opcion2;
		Option opcion2 = new Option("Un PDF por Contador");
		opTipoBusqueda[1] = opcion2;
		rbgTipoImpresionOptions.setOptions(opTipoBusqueda);
	}

	public void eventoSeleccionAnio(ValueChangeEvent event) {
		String opcion = this.getDdAnios().getSelected().toString();
		seleccionarAnio(opcion, true);
	}

	public void eventoSeleccionCalendario(ValueChangeEvent event) {
		String opcion = this.getDdCalendarios().getSelected().toString();
		seleccionarCalendario(opcion, true);
	}

	public void eventoSeleccionPeriodo(ValueChangeEvent event) {
		String opcion = this.getDdPeriodos().getSelected().toString();
		seleccionarPeriodo(opcion, true);
	}

	private void seleccionarAnio(String pAnio, boolean actualizar) {
		this.ddAnios.setSelected(pAnio);
		if(actualizar) {
			actualizarOpcionesDDCalendario(pAnio);
		}
	}

	private void seleccionarCalendario(String pCalendario, boolean actualizar) {
		this.ddCalendarios.setSelected(pCalendario);
		if(actualizar) {
			this.actualizarOpcionesDDPeriodo(pCalendario);
		}
	}

	private void seleccionarPeriodo(String pPeriodo, boolean actualizar) {
		this.ddPeriodos.setSelected(pPeriodo);
		if(actualizar) {
			this.actualizarOpcionesDDCuotas(pPeriodo);
		}
	}

	private void seleccionarCuota(String pCuota) {
		this.ddCuotas.setSelected(pCuota);
	}

	private String llenarDD(SingleSelectOptionsList ddOpciones, Set<String> llaves) {
		Option[] opciones;
		String opcion = "";
		if(llaves == null || llaves.isEmpty()) {
			opciones = new Option[0];
		} else if(llaves.size() > 1) {
			opciones = new Option[llaves.size() + 1];
			opciones[0] = new Option("");
			int i = 1;
			for(String cadaLlave : llaves) {
				opciones[i++] = new Option(cadaLlave);
			}
		} else {
			opciones = new Option[llaves.size()];
			opcion = llaves.iterator().next();
			opciones[0] = new Option(opcion);
		}
		ddOpciones.setOptions(opciones);
		return opcion;
	}

	private void actualizarOpcionesDDCalendario(String pAnio) {
		String opcion = llenarDD(ddCalendariosOptions, pAnio.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCalendariosSHPS(pAnio).keySet());
		seleccionarCalendario(opcion, true);
	}

	private void actualizarOpcionesDDPeriodo(String pCalendario) {
		String opcion = llenarDD(ddPeriodosOptions, pCalendario.isEmpty() ? null : this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalSHPS(pCalendario).keySet());
		seleccionarPeriodo(opcion, true);
	}

	private void actualizarOpcionesDDCuotas(String pPeriodo) {
		String opcion = llenarDD(ddCuotasOptions, pPeriodo.isEmpty() ? null : this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalSHPS(pPeriodo).keySet());
		seleccionarCuota(opcion);
	}

	private String getAnioCorriente() {
		String anioActual = new Integer(Calendar.getInstance().get(Calendar.YEAR)).toString();
		Integer locAnioActualMapa = this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalSHPS().get(anioActual);
		if(locAnioActualMapa != null) {
			return anioActual;
		} else {
			return this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalSHPS().keySet().iterator().next();
		}
	}

	private Table table1 = new Table();
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private RadioButton radioButton1 = new RadioButton();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tableColumn7 = new TableColumn();
	private TableColumn tableColumn8 = new TableColumn();
	private TableColumn tableColumn4 = new TableColumn();
	private TableColumn tableColumn5 = new TableColumn();
	private TableColumn tableColumn6 = new TableColumn();

	private StaticText textField1 = new StaticText();
	private StaticText textField4 = new StaticText();
	private StaticText textField5 = new StaticText();
	private StaticText staticText2 = new StaticText();

	private RadioButtonGroup rbgTipoImpresion = new RadioButtonGroup();
	private SingleSelectOptionsList rbgTipoImpresionOptions = new SingleSelectOptionsList();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText staticText2) {
		this.staticText2 = staticText2;
	}

	public StaticText getTextField1() {
		return textField1;
	}

	public void setTextField1(StaticText textField1) {
		this.textField1 = textField1;
	}

	public StaticText getTextField4() {
		return textField4;
	}

	public void setTextField4(StaticText textField4) {
		this.textField4 = textField4;
	}

	public StaticText getTextField5() {
		return textField5;
	}

	public void setTextField5(StaticText textField5) {
		this.textField5 = textField5;
	}

	public RadioButtonGroup getRbgTipoImpresion() {
		return rbgTipoImpresion;
	}

	public void setRbgTipoImpresion(RadioButtonGroup rbgTipoImpresion) {
		this.rbgTipoImpresion = rbgTipoImpresion;
	}

	public SingleSelectOptionsList getRbgTipoImpresionOptions() {
		return rbgTipoImpresionOptions;
	}

	public void setRbgTipoImpresionOptions(SingleSelectOptionsList rbgTipoImpresionOptions) {
		this.rbgTipoImpresionOptions = rbgTipoImpresionOptions;
	}

	private Button btnImprimir = new Button();

	public Button getBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(Button btnImprimir) {
		this.btnImprimir = btnImprimir;
	}

	public TableColumn getTableColumn7() {
		return tableColumn7;
	}

	public void setTableColumn7(TableColumn tableColumn7) {
		this.tableColumn7 = tableColumn7;
	}

	public TableColumn getTableColumn8() {
		return tableColumn8;
	}

	public void setTableColumn8(TableColumn tableColumn8) {
		this.tableColumn8 = tableColumn8;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	private Object lastSelected = null;

	public Object getRBSelected() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if(selected != null) {
			lastSelected = selected;
		}
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tc) {
		this.tableColumn4 = tc;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tc) {
		this.tableColumn6 = tc;
	}

	private StaticText textField2 = new StaticText();

	public StaticText getTextField2() {
		return textField2;
	}

	public void setTextField2(StaticText textField2) {
		this.textField2 = textField2;
	}

	private Label lblAnio = new Label();
	private DropDown ddAnios = new DropDown();
	private SingleSelectOptionsList ddAniosOptions = new SingleSelectOptionsList();
	private Label lblCalendarios = new Label();
	private DropDown ddCalendarios = new DropDown();
	private SingleSelectOptionsList ddCalendariosOptions = new SingleSelectOptionsList();
	private Label lblPeriodos = new Label();
	private DropDown ddPeriodos = new DropDown();
	private SingleSelectOptionsList ddPeriodosOptions = new SingleSelectOptionsList();
	private Label lblCuotas = new Label();
	private DropDown ddCuotas = new DropDown();
	private SingleSelectOptionsList ddCuotasOptions = new SingleSelectOptionsList();

	private Button btnSeleccionarPersonaFisica = new Button();
	private Button btnSeleccionarPersonaJuridica = new Button();
	private Button btnLimpiarPersona = new Button();
	private TextField tfPersona = new TextField();
	private Label lblPersona = new Label();
	private Label lblNroInscripcion = new Label();
	private TextField tfNroInscripcion = new TextField();

	protected HtmlAjaxCommandButton btnBuscar = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnReiniciar = new HtmlAjaxCommandButton();
	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label label6) {
		this.label6 = label6;
	}

	public HtmlAjaxCommandButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(HtmlAjaxCommandButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public HtmlAjaxCommandButton getBtnReiniciar() {
		return btnReiniciar;
	}

	public void setBtnReiniciar(HtmlAjaxCommandButton btnReiniciar) {
		this.btnReiniciar = btnReiniciar;
	}

	public Label getLblPersona() {
		return lblPersona;
	}

	public void setLblPersona(Label lblPersona) {
		this.lblPersona = lblPersona;
	}

	public Label getLblNroInscripcion() {
		return lblNroInscripcion;
	}

	public void setLblNroInscripcion(Label lblNroInscripcion) {
		this.lblNroInscripcion = lblNroInscripcion;
	}

	public TextField getTfNroInscripcion() {
		return tfNroInscripcion;
	}

	public void setTfNroInscripcion(TextField tfNroInscripcion) {
		this.tfNroInscripcion = tfNroInscripcion;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public Label getLblAnio() {
		return lblAnio;
	}

	public void setLblAnio(Label lblAnio) {
		this.lblAnio = lblAnio;
	}

	public DropDown getDdAnios() {
		return ddAnios;
	}

	public void setDdAnios(DropDown ddAnios) {
		this.ddAnios = ddAnios;
	}

	public SingleSelectOptionsList getDdAniosOptions() {
		return ddAniosOptions;
	}

	public void setDdAniosOptions(SingleSelectOptionsList ddAniosOptions) {
		this.ddAniosOptions = ddAniosOptions;
	}

	public Label getLblCalendarios() {
		return lblCalendarios;
	}

	public void setLblCalendarios(Label lblCalendarios) {
		this.lblCalendarios = lblCalendarios;
	}

	public DropDown getDdCalendarios() {
		return ddCalendarios;
	}

	public void setDdCalendarios(DropDown ddCalendarios) {
		this.ddCalendarios = ddCalendarios;
	}

	public SingleSelectOptionsList getDdCalendariosOptions() {
		return ddCalendariosOptions;
	}

	public void setDdCalendariosOptions(SingleSelectOptionsList ddCalendariosOptions) {
		this.ddCalendariosOptions = ddCalendariosOptions;
	}

	public Label getLblPeriodos() {
		return lblPeriodos;
	}

	public void setLblPeriodos(Label lblPeriodos) {
		this.lblPeriodos = lblPeriodos;
	}

	public DropDown getDdPeriodos() {
		return ddPeriodos;
	}

	public void setDdPeriodos(DropDown ddPeriodos) {
		this.ddPeriodos = ddPeriodos;
	}

	public SingleSelectOptionsList getDdPeriodosOptions() {
		return ddPeriodosOptions;
	}

	public void setDdPeriodosOptions(SingleSelectOptionsList ddPeriodosOptions) {
		this.ddPeriodosOptions = ddPeriodosOptions;
	}

	public Label getLblCuotas() {
		return lblCuotas;
	}

	public void setLblCuotas(Label lblCuotas) {
		this.lblCuotas = lblCuotas;
	}

	public DropDown getDdCuotas() {
		return ddCuotas;
	}

	public void setDdCuotas(DropDown ddCuotas) {
		this.ddCuotas = ddCuotas;
	}

	public SingleSelectOptionsList getDdCuotasOptions() {
		return ddCuotasOptions;
	}

	public void setDdCuotasOptions(SingleSelectOptionsList ddCuotasOptions) {
		this.ddCuotasOptions = ddCuotasOptions;
	}

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button btnSeleccionarPersonaFisica) {
		this.btnSeleccionarPersonaFisica = btnSeleccionarPersonaFisica;
	}

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button btnSeleccionarPersonaJuridica) {
		this.btnSeleccionarPersonaJuridica = btnSeleccionarPersonaJuridica;
	}

	public Button getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(Button btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
	}

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tfPersona) {
		this.tfPersona = tfPersona;
	}

	private List getListaDelCommunication() {
		return this.getCommunicationHabilitacionesBean().getListaObligacionesSHPSImprimirDDJJ();
	}

	private void setListaDelCommunication(List lista) {
		this.getCommunicationHabilitacionesBean().setListaObligacionesSHPSImprimirDDJJ(lista);
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpDocEspSHPS();
	}

	private ObjectListDataProvider ldpDocEspSHPS = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpDocEspSHPS() {
		return ldpDocEspSHPS;
	}

	public void setLdpDocEspSHPS(ObjectListDataProvider oldp) {
		this.ldpDocEspSHPS = oldp;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private ArrayList getListaDelCommunicationAtributosDinamicos() {
		return this.getCommunicationHabilitacionesBean().getListaAtributosDinamicosSHPS();
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		this.getCommunicationHabilitacionesBean().setListaAtributosDinamicosSHPS(lista);
	}

	@Override
	protected void guardarEstadoObjetosUsados() {

		String tipoImpresion = this.getRbgTipoImpresion().getSelected().toString();

		Persona persona = this.obtenerObjetoDelElementoPila(0, Persona.class);
		String nroInscripcion;
		List listaAtributosDinamicos = this.obtenerObjetoDelElementoPila(5, ArrayList.class);
		listaAtributosDinamicos = panelAtributoDinamico.obtenerListaAtributosDinamicos(listaAtributosDinamicos);

		CalendarioMunicipal calendario = null;
		PeriodoLiquidacion periodo = null;
		CuotaLiquidacion cuota = null;

		String anio = this.getDDObjectValue(getDdAnios(), this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalSHPS()).toString();
		if(anio != null) {
			calendario = this.getDDObjectValue(getDdCalendarios(), this.getCommunicationSAICBean().getMapaCalendariosSHPS(anio));
		}
		if(calendario != null) {
			periodo = this.getDDObjectValue(getDdPeriodos(), this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalSHPS(calendario.getNombre()));
		}
		if(periodo != null) {
			cuota = this.getDDObjectValue(getDdCuotas(), this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalSHPS(periodo.toString()));
		}

		if(persona != null && persona.getIdPersona() == -1) {
			persona = null;
		}

		nroInscripcion = this.getTextFieldValue(this.getTfNroInscripcion());

		this.getElementoPila().getObjetos().set(0, persona);
		this.getElementoPila().getObjetos().set(1, calendario);
		this.getElementoPila().getObjetos().set(2, periodo);
		this.getElementoPila().getObjetos().set(3, cuota);
		this.getElementoPila().getObjetos().set(4, anio);
		this.getElementoPila().getObjetos().set(5, listaAtributosDinamicos);
		this.getElementoPila().getObjetos().set(6, nroInscripcion);
		this.getElementoPila().getObjetos().set(7, tipoImpresion);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {

		int ind = 0;
		Persona persona = this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		CalendarioMunicipal calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++);
		PeriodoLiquidacion periodo = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++);
		CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++);
		String anio = this.obtenerObjetoDelElementoPila(ind++, String.class);
		ArrayList listaAtributosDinamicos = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		String nroInscripcion = (String) this.obtenerObjetoDelElementoPila(ind++);
		String tipoImpresion = this.obtenerObjetoDelElementoPila(ind++, String.class);

		panelAtributoDinamico = new PanelAtributoDinamico(listaAtributosDinamicos, "#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(listaAtributosDinamicos);

		this.rbgTipoImpresion.setSelected(tipoImpresion.toString());

		if(nroInscripcion != null) {
			this.getTfNroInscripcion().setText(nroInscripcion);
		}

		if(persona != null) {
			this.getTfPersona().setText(persona.toString());
		}

		if(anio != null) {
			seleccionarAnio(anio, true);
		}
		if(calendario != null && !calendario.getNombre().isEmpty()) {
			seleccionarCalendario(calendario.getNombre(), true);
		}
		if(periodo != null && !periodo.toString().isEmpty()) {
			seleccionarPeriodo(periodo.toString(), true);
		}
		if(cuota != null && !cuota.toString().isEmpty()) {
			seleccionarCuota(cuota.toString());
		}

		this.setListaDelCommunicationAtributosDinamicos(listaAtributosDinamicos);
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof Persona) {
			Persona persona = (Persona) pObject;
			this.getElementoPila().getObjetos().set(0, persona);
			this.getRequestBean1().setObjetoSeleccion(null);

			try {
				if(persona instanceof PersonaJuridica) {
					persona = this.getComunicationBean().getRemoteSystemPersonaJuridica().getPersonaJuridicaPorId(persona.getIdPersona());
					this.getElementoPila().getObjetos().set(0, persona);
				} else {
					persona = this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaFisicaPorId(persona.getIdPersona());
					this.getElementoPila().getObjetos().set(0, persona);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
	}

	@Override
	protected String getCasoNavegacion() {
		return "ImprimirDDJJSHPS";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		this.getObjectListDataProvider().setList(null);
		ep.getObjetos().add(ind++, null); // 0 Persona (F�sica o Jur�dica)
		ep.getObjetos().add(ind++, null); // 1 Calendario
		ep.getObjetos().add(ind++, null); // 2 Periodo
		ep.getObjetos().add(ind++, null); // 3 Cuota
		ep.getObjetos().add(ind++, getAnioCorriente().toString()); // 4 anio
		try {
			ep.getObjetos().add(ind++, this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(DocumentoSHPS.serialVersionUID, null, true));
		} catch(TrascenderException e) {
			e.printStackTrace();
		} // lista atributos dinamicos
		ep.getObjetos().add(ind++, null); // 6 nroInscripcion
		ep.getObjetos().add(ind++, "Un PDF");// 7 Tipo Impresion

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));

		return ep;
	}

	public String btnSeleccionarPersonaFisica_action() {
		return navegarParaSeleccionar("AdminPersonaFisica");
	}

	public String btnSeleccionarPersonaJuridica_action() {
		return navegarParaSeleccionar("AdminPersonaJuridica");
	}

	public String btnLimpiarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.getTfPersona().setText("");
			this.getElementoPila().getObjetos().set(0, null);
			this.getSessionBean1().setPersonaSeleccionada(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnImprimir_action() throws Exception {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			String rbSeleccionado = null;
			guardarEstadoObjetosUsados();
			if(this.rbgTipoImpresion.getSelected() != null) {
				rbSeleccionado = this.rbgTipoImpresion.getSelected().toString();
			}

			Persona persona = this.obtenerObjetoDelElementoPila(0, Persona.class);
			String nroInscripcion = (String) this.obtenerObjetoDelElementoPila(6);
			ArrayList listaAtributosDinamicos = this.obtenerObjetoDelElementoPila(5, ArrayList.class);

			FiltroObligacionSHPS locFiltro = new FiltroObligacionSHPS();
			locFiltro.setPersona(persona);
			locFiltro.setNumeroInscripcion(nroInscripcion);
			locFiltro.setEstado(Obligacion.Estado.CREADO);
			locFiltro.setListaAtributosDinamicos(listaAtributosDinamicos);

			String opcion = rbSeleccionado.equals("Un PDF por Contador") ? "UnPDFPorContador" : "UnPDF";
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(this.getSessionBean1().getLlave());
			CuotaLiquidacion pCuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(3);
			Map<String, JasperPrint> mapaJasperPrint = this.getCommunicationSAICBean().getRemoteSystemImpresion().getListaObligacionSHPSPorContador(locFiltro, pCuota, opcion);

			if(opcion == "UnPDFPorContador") {
				armarZipPDF(mapaJasperPrint);
			} else if(opcion == "UnPDF") {
				Date fecha = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH.mm.ss");
				subirReporteASesion("DDJJ_" + sdf.format(fecha), ConstantesReportes.PDF, mapaJasperPrint.values().iterator().next());
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public void armarZipPDF(Map<String, JasperPrint> mapaJasperPrint) {
		try {
			File carpeta = new File(System.getProperty("java.io.tmpdir", null), getSessionBean1().getUsuario().getUser());
			if(!carpeta.exists() && !carpeta.mkdir()) {
				throw new IIOException("Error al crear el directorio temporal." + carpeta);
			}
			for(File archivo : carpeta.listFiles()) {
				archivo.delete();
			}

			List<File> listaArchivos = new ArrayList<File>();
			for(String cadaLlave : mapaJasperPrint.keySet()) {
				JasperPrint cadaReporte = mapaJasperPrint.get(cadaLlave);

				File file = Util.crearArchivo(carpeta.getPath(), cadaLlave, "pdf");
				FileOutputStream fos = new FileOutputStream(file);

				JRPdfExporter exporter = new JRPdfExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, cadaReporte);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
				exporter.exportReport();

				listaArchivos.add(file);
			}

			Date fecha = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH.mm.ss");
			File archivoZip = new File(carpeta.getPath() + File.separator + "DDJJ_" + sdf.format(fecha) + ".zip");

			Zip locZip = new Zip();
			locZip.setCarpetaTemporal(carpeta.getPath());
			locZip.comprimir(listaArchivos, archivoZip);

			FileInputStream fis = new FileInputStream(archivoZip);
			byte[] bFile = new byte[(int) archivoZip.length()];
			fis.read(bFile);

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("bytesDocumentoDescargable", bFile);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nombreArchivoOriginal", archivoZip.getName());
			fis.close();
		} catch(Exception e) {
			log(getCasoNavegacion() + "_ReporteDinamicoError: ", e);
			error(getNombrePagina() + " - ReporteDinamico: " + e.getMessage());
		}

	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public String btnBuscar_action() throws RemoteException, TrascenderException {

		guardarEstadoObjetosUsados();

		Persona persona = this.obtenerObjetoDelElementoPila(0, Persona.class);
		String nroInscripcion = (String) this.obtenerObjetoDelElementoPila(6);
		ArrayList listaAtributosDinamicos = this.obtenerObjetoDelElementoPila(5, ArrayList.class);

		FiltroObligacionSHPS locFiltro = new FiltroObligacionSHPS();
		locFiltro.setPersona(persona);
		locFiltro.setNumeroInscripcion(nroInscripcion);
		locFiltro.setListaAtributosDinamicos(listaAtributosDinamicos);
		locFiltro.setEstado(Obligacion.Estado.CREADO);
		CuotaLiquidacion pCuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(3);

		if(locFiltro.getPersona() != null || locFiltro.getNumeroInscripcion() != null || pCuota != null) {
			this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
			locFiltro.setListaResultados(this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligacionesSHPS(locFiltro).getListaResultados());
			List<Obligacion> listaObligaciones = new ArrayList<Obligacion>(locFiltro.getListaResultados());
			this.getObjectListDataProvider().setList(listaObligaciones);
			this.setListaDelCommunication(listaObligaciones);

		} else {
			warn("Debe ingresar un Número de Inscripcion, seleccionar una Persona o una cuota.");
		}
		return "";
	}

	public String btnReiniciar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				this.limpiarObjetosUsados();
				this.limpiarTabla();
			} catch(Exception ex) {
				this.limpiarTabla();
				log(CASO_NAVEGACION + "_ReiniciarError:", ex);
				error(NOMBRE_PAGINA + " - Reiniciar: " + ex.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	public String btnCancelar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.setListaDelCommunication(null);
			retorno = this.prepararParaVolver();

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	private String prepararParaVolver() {
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

	private void limpiarObjetosUsados() {

		this.getElementoPila().getObjetos().set(0, null);
		this.getElementoPila().getObjetos().set(1, null);
		this.getElementoPila().getObjetos().set(2, null);
		this.getElementoPila().getObjetos().set(3, null);
		this.getElementoPila().getObjetos().set(4, null);
		this.getElementoPila().getObjetos().set(5, null);
		this.getElementoPila().getObjetos().set(7, "Un PDF");

		this.getTfPersona().setText(null);
		this.seleccionarAnio(this.getAnioCorriente().toString(), true);
		this.getDdPeriodos().setSelected(null);
		this.getDdCuotas().setSelected(null);
		this.getTfNroInscripcion().setText(null);
		this.getPanelAtributoDinamico().limpiarCampos();
		this.getRbgTipoImpresion().setSelected("Un PDF");

	}

	private void limpiarTabla() {
		this.getObjectListDataProvider().getList().clear();
	}

	@Override
	public String getNombreBean() {
		return "#{saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS}";
	}

	@Override
	public long getSerialVersionUID() {
		return DeclaracionJuradaSHPS.serialVersionUID;
	}
}