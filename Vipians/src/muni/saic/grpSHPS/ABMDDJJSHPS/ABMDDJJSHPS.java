/*
 * AgregarDDJJSHPS.java
 *
 * Created on 1 de noviembre de 2006, 14:27
 * Copyright Trascender SRL
 */

package muni.saic.grpSHPS.ABMDDJJSHPS;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;
import javax.faces.event.ValueChangeEvent;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Message;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.DeclaracionJuradaSHPS;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.validadores.Validador;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMDDJJSHPS extends ABMPageBean {

	public ElementoPila getElementoPila() {
		return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
	}

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */
	protected void _init() throws Exception {
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		llenarDD(ddAniosOptions, this.getCommunicationSAICBean().getMapaAniosCalendarioMunicipalSHPS().keySet());
		llenarDD(ddCalendariosOptions, this.getCommunicationSAICBean().getMapaCalendariosSHPS(null).keySet());
		llenarDD(ddPeriodosOptions, this.getCommunicationSAICBean().getMapaPeriodosCalendarioMunicipalSHPS(null).keySet());
		llenarDD(ddCuotasOptions, this.getCommunicationSAICBean().getMapaCuotasCalendarioMunicipalSHPS(null).keySet());

		numberConverter1.setPattern("$ #,##0.00");
		dateTimeConverter1.setPattern("dd/MM/yyyy");
		dateTimeConverter1.setTimeZone(java.util.TimeZone.getTimeZone("America/Buenos_Aires"));
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

	private Message message1 = new Message();

	public Message getMessage1() {
		return message1;
	}

	public void setMessage1(Message message1) {
		this.message1 = message1;
	}

	private Message message2 = new Message();

	public Message getMessage2() {
		return message2;
	}

	public void setMessage2(Message message2) {
		this.message2 = message2;
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

	private Label lblNroInscripcion = new Label();
	private TextField tfNroInscripcion = new TextField();

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

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	private Button btnSeleccionarPersonaFisica = new Button();
	private Button btnSeleccionarPersonaJuridica = new Button();
	private HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnCargarRubros = new HtmlAjaxCommandButton();
	private Button btnGuardarYLiquidar = new Button();

	public Button getBtnGuardarYLiquidar() {
		return btnGuardarYLiquidar;
	}

	public void setBtnGuardarYLiquidar(Button btnGuardarYLiquidar) {
		this.btnGuardarYLiquidar = btnGuardarYLiquidar;
	}

	public HtmlAjaxCommandButton getBtnCargarRubros() {
		return btnCargarRubros;
	}

	public void setBtnCargarRubros(HtmlAjaxCommandButton btnCargarRubros) {
		this.btnCargarRubros = btnCargarRubros;
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

	public HtmlAjaxCommandButton getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(HtmlAjaxCommandButton btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
	}

	private StaticText stSeparador = new StaticText();

	public StaticText getStSeparador() {
		return stSeparador;
	}

	public void setStSeparador(StaticText st) {
		this.stSeparador = st;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private TextField tfPersona = new TextField();

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tf) {
		this.tfPersona = tf;
	}

	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label l) {
		this.label6 = l;
	}

	private Label lblRetenciones = new Label();
	private TextField tfRetenciones = new TextField();

	public Label getLblRetenciones() {
		return lblRetenciones;
	}

	public void setLblRetenciones(Label lblRetenciones) {
		this.lblRetenciones = lblRetenciones;
	}

	public TextField getTfRetenciones() {
		return tfRetenciones;
	}

	public void setTfRetenciones(TextField tfRetenciones) {
		this.tfRetenciones = tfRetenciones;
	}

	private Table table1 = new Table();

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	private ObjectListDataProvider ldpMontoImponibleDeclarado = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpMontoImponibleDeclarado() {
		return ldpMontoImponibleDeclarado;
	}

	public void setLdpMontoImponibleDeclarado(ObjectListDataProvider oldp) {
		this.ldpMontoImponibleDeclarado = oldp;
	}

	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	private RadioButton radioButton1 = new RadioButton();

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton rb) {
		this.radioButton1 = rb;
	}

	private TableColumn tableColumn4 = new TableColumn();

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

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private TextField textField1 = new TextField();

	public TextField getTextField1() {
		return textField1;
	}

	public void setTextField1(TextField tf) {
		this.textField1 = tf;
	}

	private TableColumn tableColumn6 = new TableColumn();

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tc) {
		this.tableColumn6 = tc;
	}

	private TableColumn tableColumn7 = new TableColumn();
	private TableColumn tableColumn8 = new TableColumn();

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

	private TextField textField2 = new TextField();

	public TextField getTextField2() {
		return textField2;
	}

	public void setTextField2(TextField tf) {
		this.textField2 = tf;
	}

	private NumberConverter numberConverter1 = new NumberConverter();

	public NumberConverter getNumberConverter1() {
		return numberConverter1;
	}

	public void setNumberConverter1(NumberConverter numberConverter1) {
		this.numberConverter1 = numberConverter1;
	}

	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dtc) {
		this.dateTimeConverter1 = dtc;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private StaticText staticText4 = new StaticText();
	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText staticText4) {
		this.staticText4 = staticText4;
	}

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText staticText5) {
		this.staticText5 = staticText5;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, null); // 0 Declaracion Jurada shps
		ep.getObjetos().add(ind++, null); // 1 Persona (F�sica o Jur�dica)
		ep.getObjetos().add(ind++, null); // 2 Calendario
		ep.getObjetos().add(ind++, null); // 3 Periodo
		ep.getObjetos().add(ind++, null); // 4 Cuota
		ep.getObjetos().add(ind++, getAnioCorriente().toString()); // 5 anio
		ep.getObjetos().add(ind++, null); // 6 nroInscripcion

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));

		// Se limpia la lista de rubros porque siempre queda cargada cuando haces un Add...
		if(this.getListaDelCommunication() != null) {
			this.getListaDelCommunication().clear();
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		return ep;
	}

	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		DeclaracionJuradaSHPS declaracionJuradaSHPS = (DeclaracionJuradaSHPS) this.obtenerObjetoDelElementoPila(ind++, DeclaracionJuradaSHPS.class);
		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		CalendarioMunicipal calendario = null;
		PeriodoLiquidacion periodo = null;
		CuotaLiquidacion cuota = null;
		String nroInscripcion = null;

		nroInscripcion = this.getTextFieldValue(this.getTfNroInscripcion());

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

		this.getObjectListDataProvider().commitChanges();

		List montosImponiblesDeclarados = this.getObjectListDataProvider().getList();
		if(montosImponiblesDeclarados.size() <= 0)
			montosImponiblesDeclarados = null;
		this.setListaDelCommunication(montosImponiblesDeclarados);

		if(this.getListaDelCommunication() != null) {
			declaracionJuradaSHPS.setDescuentoPorRetenciones(this.getTextFieldValueDouble(this.getTfRetenciones()));
		}

		// if(this.getListaDelCommunication() != null) {
		// for(Iterator iterator = this.getListaDelCommunication().iterator(); iterator.hasNext();) {
		// LineaDeclaracionJuradaSHPS cadaLineaDeclaracionJurada = (LineaDeclaracionJuradaSHPS) iterator.next();
		// declaracionJuradaSHPS.getListaLineasDDJJSHPS().add(cadaLineaDeclaracionJurada);
		// }
		// declaracionJuradaSHPS.setDescuentoPorRetenciones(this.getTextFieldValueDouble(this.getTfRetenciones()));
		// }

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, declaracionJuradaSHPS);
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, calendario);
		this.getElementoPila().getObjetos().set(ind++, periodo);
		this.getElementoPila().getObjetos().set(ind++, cuota);
		this.getElementoPila().getObjetos().set(ind++, anio);
		this.getElementoPila().getObjetos().set(ind++, nroInscripcion);
	}

	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		DeclaracionJuradaSHPS declaracionJuradaSHPS = (DeclaracionJuradaSHPS) this.obtenerObjetoDelElementoPila(ind++, DeclaracionJuradaSHPS.class);
		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		CalendarioMunicipal calendario = (CalendarioMunicipal) this.obtenerObjetoDelElementoPila(ind++);
		PeriodoLiquidacion periodo = (PeriodoLiquidacion) this.obtenerObjetoDelElementoPila(ind++);
		CuotaLiquidacion cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(ind++);
		String anio = (String) this.obtenerObjetoDelElementoPila(ind++, String.class);
		String nroInscripcion = (String) this.obtenerObjetoDelElementoPila(ind++);

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

		if(this.getListaDelCommunication() != null) {
			this.getTfRetenciones().setText(declaracionJuradaSHPS.getDescuentoPorRetenciones());
		}
	}

	private void refrescarTabla() throws Exception {
		// CAMBIAR: Segun objeto de busqueda.
		int ind = 0;
		DeclaracionJuradaSHPS declaracionJuradaSHPS = (DeclaracionJuradaSHPS) this.obtenerObjetoDelElementoPila(ind++, DeclaracionJuradaSHPS.class);
		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);

		if(persona != null && ((Persona) persona).getIdPersona() == -1)
			persona = null;

		// CAMBIAR: Utilizar el EJBClient adecuado.
		this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(this.getSessionBean1().getLlave());
		// this.setListaDelCommunication((ArrayList) this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().getListaNuevasDDJJSHPS((Persona) persona,
		// periodo));
		this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		this.setRBSelected((new Long(0)).toString());
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpMontoImponibleDeclarado();
	}

	private List getListaDelCommunication() {
		return this.getCommunicationSAICBean().getListaMontosImponiblesDeclarados();
	}

	private void setListaDelCommunication(List lista) {
		this.getCommunicationSAICBean().setListaMontosImponiblesDeclarados(lista);
	}

	private void limpiarTabla() {
		this.getObjectListDataProvider().getList().clear();
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
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

	private RowKey rowKeySeleccionado = null;

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	public void guardarOrdenamiento() {
		this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
	}

	public void setearOrdenamiento() {
		this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
	}

	public Long getPosicionEnTabla(RowKey rk) {
		long inicioPagina = 0;
		long posicionGlobal = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
		long cantRegistros = this.getTableRowGroup1().getRowCount();
		boolean encontrado = false;

		if(rk != null) {
			while(!encontrado && inicioPagina < cantRegistros) {
				this.getTableRowGroup1().setFirst((int) inicioPagina);
				posicionEnPagina = 0;
				while(!encontrado && posicionEnPagina < cantRegistrosPorPagina) {
					encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int) posicionEnPagina].equals(rk);
					if(!encontrado) {
						posicionEnPagina++;
						posicionGlobal++;
					}
				}
				if(!encontrado)
					inicioPagina += cantRegistrosPorPagina;
			}
		}
		return new Long(posicionGlobal);
	}

	public RowKey getRowKeyAsociado(Long posicionEnTabla) {
		RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];
		return rk;
	}

	public void seleccionarFila(Long posicionGlobal) {
		long cantFilas = this.getTableRowGroup1().getRowCount();

		if(cantFilas > 0) {
			// si hay al menos una fila
			if(posicionGlobal.longValue() >= cantFilas) {
				// si elimine la ultima fila, me posiciono en la anterior
				posicionGlobal = new Long(cantFilas - 1);
			}
			;

			int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
			this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
			lastSelected = new Long(index).toString();
		}
	}

	public Long getInicioPagina(Long posicionGlobal) {
		long inicioPagina = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();

		inicioPagina = (posicionGlobal.longValue() / cantRegistrosPorPagina) * cantRegistrosPorPagina;
		return new Long(inicioPagina);
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch(Exception ex) {

		}
		return rk;
	}

	public String btnSeleccionarPersonaFisica_action() {
		return navegarParaSeleccionar("AdminPersonaFisica");
	}

	public String btnSeleccionarPersonaJuridica_action() {
		return navegarParaSeleccionar("AdminPersonaJuridica");
	}

	public String btnGuardarYLiquidar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
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

				if(locObject instanceof EntidadTrascender) {
					if(this.getTaComentarioLogAuditoria().getText() != null)
						((EntidadTrascender) locObject).setComentarioAuditoria(this.getTaComentarioLogAuditoria().getText().toString());
					((EntidadTrascender) locObject).setLlaveUsuarioAuditoria(this.getSessionBean1().getLlave());
				} else if(locObject instanceof AuditoriaIndirecta) {
					AuditoriaIndirecta locAuditoriaInd = (AuditoriaIndirecta) locObject;
					if(this.getTaComentarioLogAuditoria().getText() != null) {
						locAuditoriaInd.getEntidadTrascender().setComentarioAuditoria(this.getTaComentarioLogAuditoria().getText().toString());
					}
					locAuditoriaInd.getEntidadTrascender().setLlaveUsuarioAuditoria(this.getSessionBean1().getLlave());
				}

				DeclaracionJuradaSHPS locDeclaracion = (DeclaracionJuradaSHPS) this.obtenerObjetoDelElementoPila(0, DeclaracionJuradaSHPS.class);

				getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(getSessionBean1().getLlave());
				locDeclaracion = getCommunicationSAICBean().getRemoteSystemRegistroValuado().addDDJJSHPSParaLiquidar(locDeclaracion);

				this.getRequestBean1().setObjetoABM(locDeclaracion);
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				retorno = "GenerarLiquidacionSHPS";
			} catch(Exception ex) {
				log(getCasoNavegacion() + "_GuardarError:", ex);
				error(ex.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnLimpiarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(1, this.getTfPersona());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnCargarRubros_action() throws RemoteException, TrascenderException {
		guardarEstadoObjetosUsados();
		Persona persona = null;
		CuotaLiquidacion cuota = null;
		String nroInscripcion = null;

		persona = (Persona) this.obtenerObjetoDelElementoPila(1);
		cuota = (CuotaLiquidacion) this.obtenerObjetoDelElementoPila(4);
		nroInscripcion = (String) this.obtenerObjetoDelElementoPila(6);

		if(persona != null || nroInscripcion != null) {
			try {
				if(cuota != null) {
					DeclaracionJuradaSHPS locDDJJSHPS = null;
					this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(this.getSessionBean1().getLlave());
					locDDJJSHPS = this.getCommunicationSAICBean().getRemoteSystemRegistroValuado().getNuevaDDJJSHPS(persona, cuota, nroInscripcion, null);

					if(locDDJJSHPS != null) {
						this.getObjectListDataProvider().setList(locDDJJSHPS.getListaLineasDDJJSHPS());
						this.setListaDelCommunication(locDDJJSHPS.getListaLineasDDJJSHPS());
					}
					this.getElementoPila().getObjetos().set(0, locDDJJSHPS);
				} else {
					warn("Debe seleccionar una cuota.");
				}
			} catch(Exception e) {
				e.printStackTrace();
				warn("Ya existe una Declaración Jurada para ese período.");
			}
		} else {
			warn("Debe seleccionar una persona o número de inscripción.");
		}
		return "";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof Persona) {
			Persona persona = (Persona) pObject;
			this.getElementoPila().getObjetos().set(1, persona);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		DeclaracionJuradaSHPS locDeclaracion = (DeclaracionJuradaSHPS) pObject;
		List montosImponiblesDeclarados = locDeclaracion.getListaLineasDDJJSHPS();

		this.setListaDelCommunication(montosImponiblesDeclarados);
		this.getObjectListDataProvider().setList(montosImponiblesDeclarados);

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, locDeclaracion);
		this.getElementoPila().getObjetos().set(ind++, locDeclaracion.getDocHabilitanteEspecializado().getObligacion().getPersona());
		this.getElementoPila().getObjetos().set(ind++, locDeclaracion.getCuotaLiquidacion().getPeriodo().getCalendario());
		this.getElementoPila().getObjetos().set(ind++, locDeclaracion.getCuotaLiquidacion().getPeriodo());
		this.getElementoPila().getObjetos().set(ind++, locDeclaracion.getCuotaLiquidacion());
		this.getElementoPila().getObjetos().set(ind++, locDeclaracion.getCuotaLiquidacion().getPeriodo().getCalendario().getAnio().toString());

		if(locDeclaracion.getDocHabilitanteEspecializado() instanceof DocumentoSHPS) {
			DocumentoSHPS locDocumento = (DocumentoSHPS) locDeclaracion.getDocHabilitanteEspecializado();
			this.getElementoPila().getObjetos().set(ind++, locDocumento.getNumeroInscripcion());
		}
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMDDJJSHPS";
	}

	public void setPersonaAutocompletar(String pId, String pIdSubSesion) throws Exception {
		Long id = Long.parseLong(pId);
		Persona persona = null;

		try {
			persona = (Persona) this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		this.setIdSubSesion(Long.parseLong(pIdSubSesion));
		this.getElementoPila().getObjetos().set(1, persona);
	}

	public boolean isHayPersona() {
		Persona locPersona = (Persona) this.obtenerObjetoDelElementoPila(1);
		return(locPersona != null && locPersona.getIdPersona() != -1);
	}

	@Override
	public String getNombreBean() {
		return "#{saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS}";
	}

	@Override
	public long getSerialVersionUID() {
		return DeclaracionJuradaSHPS.serialVersionUID;
	}
}