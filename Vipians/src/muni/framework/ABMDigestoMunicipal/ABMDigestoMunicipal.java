/*
 * ABMDigestoMunicipal.java
 *
 * Created on 18 de octubre de 2006, 10:30
 * Copyright Trascender SRL
 */

package muni.framework.ABMDigestoMunicipal;

import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMDigestoMunicipal extends ABMPageBean {

	private Script scriptFinalM1 = new Script();

	public Script getScriptFinalM1() {
		return scriptFinalM1;
	}

	public void setScriptFinalM1(Script s) {
		this.scriptFinalM1 = s;
	}

	@Override
	protected void _init() throws Exception {
		Option[] op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(DigestoMunicipal.Tipo.values(), "may");
		ddTipoDigestoDefaultOptions.setOptions(op);

		Option[] opEstado = null;
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(DigestoMunicipal.Estado.values(), "may");
		ddEstadoDigestoDefaultOptions.setOptions(opEstado);

		Option[] opEjeTematico = null;
		opEjeTematico = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(DigestoMunicipal.EjeTematico.values(), "may");
		getDdEjeTematicoDigestoDefaultOptions().setOptions(opEjeTematico);

		Option[] opAmbito = null;
		opAmbito = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(DigestoMunicipal.Ambito.values(), "may");
		getDdAmbitoDigestoDefaultOptions().setOptions(opAmbito);

		if(this.getComunicationBean().getListaDigestoConcordancia() != null) {
			this.ldpConcordanciasDigesto.setList(this.getComunicationBean().getListaDigestoConcordancia());
		}
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		DigestoMunicipal locDigMunicipal = this.obtenerObjetoDelElementoPila(0, DigestoMunicipal.class);
		this.getTablaLogs().getLdpLogs().setList(locDigMunicipal.getListaLogsAuditoria());
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

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private DropDown ddTipoDigesto = new DropDown();

	public DropDown getDdTipoDigesto() {
		return ddTipoDigesto;
	}

	public void setDdTipoDigesto(DropDown dd) {
		this.ddTipoDigesto = dd;
	}

	private Label label3 = new Label(); // Ambito
	private Label label6 = new Label(); // Eje Tematico

	private SingleSelectOptionsList ddTipoDigestoDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddEjeTematicoDigestoDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddAmbitoDigestoDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdTipoDigestoDefaultOptions() {
		return ddTipoDigestoDefaultOptions;
	}

	public void setDdTipoDigestoDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddTipoDigestoDefaultOptions = ssol;
	}

	// </editor-fold>

	private Label lblEstadoDigesto = new Label();

	public Label getLblEstadoDigesto() {
		return lblEstadoDigesto;
	}

	public void setLblEstadoDigesto(Label lblEstadoDigesto) {
		this.lblEstadoDigesto = lblEstadoDigesto;
	}

	private DropDown ddEstadoDigesto = new DropDown();

	public DropDown getDdEstadoDigesto() {
		return ddEstadoDigesto;
	}

	public void setDdEstadoDigesto(DropDown ddEstadoDigesto) {
		this.ddEstadoDigesto = ddEstadoDigesto;
	}

	private SingleSelectOptionsList ddEstadoDigestoDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdEstadoDigestoDefaultOptions() {
		return ddEstadoDigestoDefaultOptions;
	}

	public void setDdEstadoDigestoDefaultOptions(SingleSelectOptionsList ddEstadoDigestoDefaultOptions) {
		this.ddEstadoDigestoDefaultOptions = ddEstadoDigestoDefaultOptions;
	}

	private Label lblMotivoEstado = new Label();

	public Label getLblMotivoEstado() {
		return lblMotivoEstado;
	}

	public void setLblMotivoEstado(Label lblMotivoEstado) {
		this.lblMotivoEstado = lblMotivoEstado;
	}

	private TextArea taMotivoEstado = new TextArea();

	public TextArea getTaMotivoEstado() {
		return taMotivoEstado;
	}

	public void setTaMotivoEstado(TextArea taMotivoEstado) {
		this.taMotivoEstado = taMotivoEstado;
	}

	private Label lblTema = new Label();

	private Label lblFecha = new Label();

	public Label getLblFecha() {
		return lblFecha;
	}

	public void setLblFecha(Label lblFecha) {
		this.lblFecha = lblFecha;
	}

	private TextField tfFecha = new TextField();

	public TextField getTfFecha() {
		return tfFecha;
	}

	public void setTfFecha(TextField tfFecha) {
		this.tfFecha = tfFecha;
	}

	private Label lblNumero = new Label();

	public Label getLblNumero() {
		return lblNumero;
	}

	public void setLblNumero(Label lblNumero) {
		this.lblNumero = lblNumero;
	}

	private TextField tfNumero = new TextField();

	public TextField getTfNumero() {
		return tfNumero;
	}

	public void setTfNumero(TextField tfNumero) {
		this.tfNumero = tfNumero;
	}

	private Label lblDescripcion = new Label();

	public Label getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(Label lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	private TextArea taDescripcion = new TextArea();

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea taDescripcion) {
		this.taDescripcion = taDescripcion;
	}

	private Label lblComposicion = new Label();

	public Label getLblComposicion() {
		return lblComposicion;
	}

	public void setLblComposicion(Label lblComposicion) {
		this.lblComposicion = lblComposicion;
	}

	private PanelGroup gpBotones = new PanelGroup();

	public PanelGroup getGpBotones() {
		return gpBotones;
	}

	public void setGpBotones(PanelGroup gpBotones) {
		this.gpBotones = gpBotones;
	}

	private Button btnAgregarDigesto = new Button();

	public Button getBtnAgregarDigesto() {
		return btnAgregarDigesto;
	}

	public void setBtnAgregarDigesto(Button btnAgregarDigesto) {
		this.btnAgregarDigesto = btnAgregarDigesto;
	}

	private Button btnEliminarDigesto = new Button();

	public Button getBtnEliminarDigesto() {
		return btnEliminarDigesto;
	}

	public void setBtnEliminarDigesto(Button btnEliminarDigesto) {
		this.btnEliminarDigesto = btnEliminarDigesto;
	}

	private Table table1 = new Table();

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	private ObjectListDataProvider ldpConcordanciasDigesto = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpConcordanciasDigesto() {
		return ldpConcordanciasDigesto;
	}

	public void setLdpConcordanciasDigesto(ObjectListDataProvider oldp) {
		this.ldpConcordanciasDigesto = oldp;
	}

	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
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

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
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
		return staticText1;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	private RadioButton radioButton1 = new RadioButton();

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton rb) {
		this.radioButton1 = rb;
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

	private DropDown ddAmbitoDigesto = new DropDown();
	private DropDown ddEjeTematicoDigesto = new DropDown();

	/**
	 * <p>
	 * Construir una instancia de bean de página.
	 * </p>
	 */
	public ABMDigestoMunicipal() {
	}

	/**
	 * <p>
	 * Método de devolución de llamada al que se llama cuando se completa el procesamiento de esta petición, si se llamó al método <code>init()</code> (sin
	 * tener en cuenta si se trata de la página que se ha procesado o no). Puede personalizar este método para liberar los recursos adquiridos en los métodos
	 * <code>init()</code>, <code>preprocess()</code> o <code>prerender()</code> (o durante la ejecución de un controlador de eventos).
	 * </p>
	 */

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new DigestoMunicipal());// DIGESTO
		ep.getObjetos().add(ind++, null); // lista Concordancias
		ep.getObjetos().add(ind++, new Integer(0));

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		try {
			int ind = 0;
			DigestoMunicipal digestoMunicipal = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(ind++, DigestoMunicipal.class);

			digestoMunicipal.setTipo(this.getDDEnumValue(getDdTipoDigesto(), DigestoMunicipal.Tipo.class));
			digestoMunicipal.setEstado(this.getDDEnumValue(getDdEstadoDigesto(), DigestoMunicipal.Estado.class));
			digestoMunicipal.setAmbito(this.getDDEnumValue(getDdAmbitoDigesto(), DigestoMunicipal.Ambito.class));
			digestoMunicipal.setEjeTematico(this.getDDEnumValue(getDdEjeTematicoDigesto(), DigestoMunicipal.EjeTematico.class));
			digestoMunicipal.setFecha(this.getTextFieldValueDate(getTfFecha()));
			digestoMunicipal.setDescripcion(this.getTextAreaValue(getTaDescripcion()));
			digestoMunicipal.setNumero(this.getTextFieldValueInteger(getTfNumero()));

			this.getLdpConcordanciasDigesto().commitChanges();
			List listaConcordancias = this.getLdpConcordanciasDigesto().getList();
			for(Object cadaObject : listaConcordancias) {
				DigestoMunicipal cadaDigesto = (DigestoMunicipal) cadaObject;
				digestoMunicipal.addConcordancia(cadaDigesto);
			}

			this.getComunicationBean().setListaDigestoConcordancia(listaConcordancias);

			this.getElementoPila().getObjetos().set(0, digestoMunicipal);
			this.getElementoPila().getObjetos().set(1, listaConcordancias);

		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		DigestoMunicipal digestoMunicipal = (DigestoMunicipal) this.obtenerObjetoDelElementoPila(0, DigestoMunicipal.class);

		this.getDdTipoDigesto().setSelected(Util.getEnumNameFromString(String.valueOf(digestoMunicipal.getTipo())));
		this.getDdTipoDigestoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(digestoMunicipal.getTipo())));

		this.getDdEstadoDigesto().setSelected(Util.getEnumNameFromString(String.valueOf(digestoMunicipal.getEstado())));
		this.getDdEstadoDigestoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(digestoMunicipal.getEstado())));

		this.getDdAmbitoDigesto().setSelected(Util.getEnumNameFromString(String.valueOf(digestoMunicipal.getAmbito())));
		this.getDdAmbitoDigestoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(digestoMunicipal.getAmbito())));

		this.getDdEjeTematicoDigesto().setSelected(Util.getEnumNameFromString(String.valueOf(digestoMunicipal.getEjeTematico())));
		this.getDdEjeTematicoDigestoDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(digestoMunicipal.getEjeTematico())));

		this.getTfFecha().setText(Conversor.getStringDeFechaCorta(digestoMunicipal.getFecha()));
		this.getTfNumero().setText(digestoMunicipal.getNumero());

		if(digestoMunicipal.getDescripcion() != null)
			this.getTaDescripcion().setText(digestoMunicipal.getDescripcion());
		else
			this.getTaDescripcion().setText(null);

		List listaConcordancias = (List) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
		this.getLdpConcordanciasDigesto().setList(listaConcordancias);
		this.getComunicationBean().setListaDigestoConcordancia(listaConcordancias);
	}

	private String getIdSinPrefijo(String idCompleto, String idComponente) {
		String retorno = null;
		if(idCompleto != null && idCompleto.length() > 0)
			retorno = idCompleto.substring(idCompleto.indexOf(idComponente) + idComponente.length() + 1);
		return retorno;
	}

	public String btnSeleccionarDigesto_action() {
		return navegarParaSeleccionar("AdminDigestoMunicipal");
	}

	public String btnEliminarDigesto_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			// APLICAR LOGICA AQUI...
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getLdpConcordanciasDigesto().getObjects()[index];
					this.getLdpConcordanciasDigesto().getList().remove(index);
					DigestoMunicipal locDigesto = (DigestoMunicipal) obtenerObjetoDelElementoPila(0, DigestoMunicipal.class);
					locDigesto.quitarConcordancia((DigestoMunicipal) obj);
					this.getElementoPila().getObjetos().set(0, locDigesto);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getLdpConcordanciasDigesto().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMDigestoMunicipal";
	}

	/**
	 * @return the label3
	 */
	public Label getLabel3() {
		return label3;
	}

	/**
	 * @param label3
	 *            the label3 to set
	 */
	public void setLabel3(Label label3) {
		this.label3 = label3;
	}

	/**
	 * @return the label6
	 */
	public Label getLabel6() {
		return label6;
	}

	/**
	 * @param label6
	 *            the label6 to set
	 */
	public void setLabel6(Label label6) {
		this.label6 = label6;
	}

	/**
	 * @return the ddEjeTematicoDigestoDefaultOptions
	 */
	public SingleSelectOptionsList getDdEjeTematicoDigestoDefaultOptions() {
		return ddEjeTematicoDigestoDefaultOptions;
	}

	/**
	 * @param ddEjeTematicoDigestoDefaultOptions
	 *            the ddEjeTematicoDigestoDefaultOptions to set
	 */
	public void setDdEjeTematicoDigestoDefaultOptions(SingleSelectOptionsList ddEjeTematicoDigestoDefaultOptions) {
		this.ddEjeTematicoDigestoDefaultOptions = ddEjeTematicoDigestoDefaultOptions;
	}

	/**
	 * @return the ddAmbitoDigestoDefaultOptions
	 */
	public SingleSelectOptionsList getDdAmbitoDigestoDefaultOptions() {
		return ddAmbitoDigestoDefaultOptions;
	}

	/**
	 * @param ddAmbitoDigestoDefaultOptions
	 *            the ddAmbitoDigestoDefaultOptions to set
	 */
	public void setDdAmbitoDigestoDefaultOptions(SingleSelectOptionsList ddAmbitoDigestoDefaultOptions) {
		this.ddAmbitoDigestoDefaultOptions = ddAmbitoDigestoDefaultOptions;
	}

	/**
	 * @return the lblTema
	 */
	public Label getLblTema() {
		return lblTema;
	}

	/**
	 * @param lblTema
	 *            the lblTema to set
	 */
	public void setLblTema(Label lblTema) {
		this.lblTema = lblTema;
	}

	/**
	 * @return the ddAmbitoDigesto
	 */
	public DropDown getDdAmbitoDigesto() {
		return ddAmbitoDigesto;
	}

	/**
	 * @param ddAmbitoDigesto
	 *            the ddAmbitoDigesto to set
	 */
	public void setDdAmbitoDigesto(DropDown ddAmbitoDigesto) {
		this.ddAmbitoDigesto = ddAmbitoDigesto;
	}

	/**
	 * @return the ddEjeTematicoDigesto
	 */
	public DropDown getDdEjeTematicoDigesto() {
		return ddEjeTematicoDigesto;
	}

	/**
	 * @param ddEjeTematicoDigesto
	 *            the ddEjeTematicoDigesto to set
	 */
	public void setDdEjeTematicoDigesto(DropDown ddEjeTematicoDigesto) {
		this.ddEjeTematicoDigesto = ddEjeTematicoDigesto;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof DigestoMunicipal) {
			DigestoMunicipal concordancia = (DigestoMunicipal) pObject;
			DigestoMunicipal digestoMunicipal = null;

			List listaConcordancias = (List) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
			if(!listaConcordancias.contains(digestoMunicipal)) {
				try {
					System.out.println("Agregue: " + concordancia);
					concordancia = this.getComunicationBean().getRemoteSystemMunicipalidad().getDigestoMunicipalPorId(concordancia.getIdDigestoMunicipal());
					listaConcordancias.add(concordancia);
					this.getElementoPila().getObjetos().set(1, listaConcordancias);
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		DigestoMunicipal digestoMunicipal = (DigestoMunicipal) pObject;
		this.getElementoPila().getObjetos().set(0, digestoMunicipal);
		this.getElementoPila().getObjetos().set(1, new ArrayList(digestoMunicipal.getListaConcordancias()));
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMDigestoMunicipal$ABMDigestoMunicipal}";
	}

	@Override
	public long getSerialVersionUID() {
		return DigestoMunicipal.serialVersionUID;
	}
}