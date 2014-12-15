/*
 * AgregarTipoVencimiento.java
 *
 * Created on 18 de octubre de 2006, 10:30
 * Copyright Trascender SRL
 */

package muni.habilitaciones.ABMTipoVencimiento;

import java.util.ArrayList;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Listbox;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.DefaultOptionsList;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.habilitaciones.recurso.persistent.TipoVencimiento;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMTipoVencimiento extends ABMPageBean {

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */
	@Override
	protected void _init() throws Exception {
		ddOperador1DefaultOptions.setOptions(new com.sun.rave.web.ui.model.Option[] {new com.sun.rave.web.ui.model.Option("", ""),
				new com.sun.rave.web.ui.model.Option(">", "es mayor que"), new com.sun.rave.web.ui.model.Option("<", "es menor que"),
				new com.sun.rave.web.ui.model.Option("==", "es igual que"), new com.sun.rave.web.ui.model.Option(">=", "es mayor o igual que"),
				new com.sun.rave.web.ui.model.Option("<=", "es menor o igual que"), new com.sun.rave.web.ui.model.Option("== 1", "es verdadero"),
				new com.sun.rave.web.ui.model.Option("== 0", "es falso")});
		ddOperador1DefaultOptions.setSelectedValue("");

		ddUnion1DefaultOptions.setOptions(new com.sun.rave.web.ui.model.Option[] {new com.sun.rave.web.ui.model.Option("", ""), new com.sun.rave.web.ui.model.Option("&&", "Y"),
				new com.sun.rave.web.ui.model.Option("||", "O")});
		ddUnion1DefaultOptions.setSelectedValue("");

		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
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

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private TextField tfMeses = new TextField();

	public TextField getTfMeses() {
		return tfMeses;
	}

	public void setTfMeses(TextField tf) {
		this.tfMeses = tf;
	}

	private StaticText stValor1 = new StaticText();

	public StaticText getStValor1() {
		return stValor1;
	}

	public void setStValor1(StaticText st) {
		this.stValor1 = st;
	}

	private TextField tfDias = new TextField();

	public TextField getTfDias() {
		return tfDias;
	}

	public void setTfDias(TextField tf) {
		this.tfDias = tf;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private Label label11 = new Label();

	public Label getLabel11() {
		return label11;
	}

	public void setLabel11(Label l) {
		this.label11 = l;
	}

	private Listbox lbVariables = new Listbox();

	public Listbox getLbVariables() {
		return lbVariables;
	}

	public void setLbVariables(Listbox l) {
		this.lbVariables = l;
	}

	private DefaultOptionsList lbVariablesDefaultOptions = new DefaultOptionsList();

	public DefaultOptionsList getLbVariablesDefaultOptions() {
		return lbVariablesDefaultOptions;
	}

	public void setLbVariablesDefaultOptions(DefaultOptionsList dol) {
		this.lbVariablesDefaultOptions = dol;
	}

	private Label label12 = new Label();

	public Label getLabel12() {
		return label12;
	}

	public void setLabel12(Label l) {
		this.label12 = l;
	}

	private DropDown ddVariable1 = new DropDown();

	public DropDown getDdVariable1() {
		return ddVariable1;
	}

	public void setDdVariable1(DropDown dd) {
		this.ddVariable1 = dd;
	}

	private DropDown ddOperador1 = new DropDown();

	public DropDown getDdOperador1() {
		return ddOperador1;
	}

	public void setDdOperador1(DropDown dd) {
		this.ddOperador1 = dd;
	}

	private TextField tfValor1 = new TextField();

	public TextField getTfValor1() {
		return tfValor1;
	}

	public void setTfValor1(TextField tf) {
		this.tfValor1 = tf;
	}

	private SingleSelectOptionsList ddOperador1DefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdOperador1DefaultOptions() {
		return ddOperador1DefaultOptions;
	}

	public void setDdOperador1DefaultOptions(SingleSelectOptionsList ssol) {
		this.ddOperador1DefaultOptions = ssol;
	}

	private DropDown ddUnion1 = new DropDown();

	public DropDown getDdUnion1() {
		return ddUnion1;
	}

	public void setDdUnion1(DropDown dd) {
		this.ddUnion1 = dd;
	}

	private SingleSelectOptionsList ddUnion1DefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdUnion1DefaultOptions() {
		return ddUnion1DefaultOptions;
	}

	public void setDdUnion1DefaultOptions(SingleSelectOptionsList ssol) {
		this.ddUnion1DefaultOptions = ssol;
	}

	private DropDown ddUnion2 = new DropDown();

	public DropDown getDdUnion2() {
		return ddUnion2;
	}

	public void setDdUnion2(DropDown dd) {
		this.ddUnion2 = dd;
	}

	private DropDown ddOperador2 = new DropDown();

	public DropDown getDdOperador2() {
		return ddOperador2;
	}

	public void setDdOperador2(DropDown dd) {
		this.ddOperador2 = dd;
	}

	private TextField tfValor2 = new TextField();

	public TextField getTfValor2() {
		return tfValor2;
	}

	public void setTfValor2(TextField tf) {
		this.tfValor2 = tf;
	}

	private DropDown ddVariable2 = new DropDown();

	public DropDown getDdVariable2() {
		return ddVariable2;
	}

	public void setDdVariable2(DropDown dd) {
		this.ddVariable2 = dd;
	}

	private DropDown ddOperador3 = new DropDown();

	public DropDown getDdOperador3() {
		return ddOperador3;
	}

	public void setDdOperador3(DropDown dd) {
		this.ddOperador3 = dd;
	}

	private TextField tfValor3 = new TextField();

	public TextField getTfValor3() {
		return tfValor3;
	}

	public void setTfValor3(TextField tf) {
		this.tfValor3 = tf;
	}

	private DropDown ddVariable3 = new DropDown();

	public DropDown getDdVariable3() {
		return ddVariable3;
	}

	public void setDdVariable3(DropDown dd) {
		this.ddVariable3 = dd;
	}

	private TextArea taCondicion = new TextArea();

	public TextArea getTaCondicion() {
		return taCondicion;
	}

	public void setTaCondicion(TextArea ta) {
		this.taCondicion = ta;
	}

	private ObjectListDataProvider ldpTipoParametroTipoVencimiento = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpTipoParametroTipoVencimiento() {
		return ldpTipoParametroTipoVencimiento;
	}

	public void setLdpTipoParametroTipoVencimiento(ObjectListDataProvider oldp) {
		this.ldpTipoParametroTipoVencimiento = oldp;
	}

	private TextArea taFormula = new TextArea();

	public TextArea getTaFormula() {
		return taFormula;
	}

	public void setTaFormula(TextArea ta) {
		this.taFormula = ta;
	}

	private TextField tfValorFinal = new TextField();

	public TextField getTfValorFinal() {
		return tfValorFinal;
	}

	public void setTfValorFinal(TextField tf) {
		this.tfValorFinal = tf;
	}

	private TextField tfValorFinalSino = new TextField();

	public TextField getTfValorFinalSino() {
		return tfValorFinalSino;
	}

	public void setTfValorFinalSino(TextField tf) {
		this.tfValorFinalSino = tf;
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

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	// </editor-fold>

	/**
	 * <p>
	 * Construir una instancia de bean de página.
	 * </p>
	 */
	public ABMTipoVencimiento() {
	}

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new TipoVencimiento());
		ep.getObjetos().add(ind++, new ArrayList()); // lista de parametros

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		int ind = 0;
		TipoVencimiento tipoVencimiento = (TipoVencimiento) this.obtenerObjetoDelElementoPila(ind++, TipoVencimiento.class);
		ArrayList parametros = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		tipoVencimiento.setNombre(getTextFieldValue(this.getTfNombre()));
		tipoVencimiento.setMeses(Conversor.getIntegerDeString(getTextFieldValue(this.getTfMeses())));
		tipoVencimiento.setDias(Conversor.getIntegerDeString(getTextFieldValue(this.getTfDias())));
		tipoVencimiento.setFormulaCalculo(getTextAreaValue(this.getTaFormula()));
		tipoVencimiento.setCondicion(getTextAreaValue(this.getTaCondicion()));

		this.getObjectListDataProvider().commitChanges();
		parametros = (ArrayList) this.getObjectListDataProvider().getList();
		this.setListaDelCommunication(parametros);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, tipoVencimiento);
		this.getElementoPila().getObjetos().set(ind++, parametros);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		int ind = 0;
		TipoVencimiento tipoVencimiento = null;
		ArrayList parametros = null;

		tipoVencimiento = (TipoVencimiento) this.obtenerObjetoDelElementoPila(ind++, TipoVencimiento.class);
		parametros = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		// variables/parametros
		this.getObjectListDataProvider().setList(parametros);
		Option[] op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProvider().getObjects(), "cap");
		lbVariablesDefaultOptions.setOptions(op);

		this.getTfNombre().setText(tipoVencimiento.getNombre());
		if(tipoVencimiento.getMeses() != null)
			this.getTfMeses().setText(tipoVencimiento.getMeses().toString());
		if(tipoVencimiento.getDias() != null)
			this.getTfDias().setText(tipoVencimiento.getDias().toString());
		this.getTaFormula().setText(tipoVencimiento.getFormulaCalculo());
		this.getTaCondicion().setText(tipoVencimiento.getCondicion());

		this.setListaDelCommunication(parametros);
		this.getObjectListDataProvider().setList(parametros);
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpTipoParametroTipoVencimiento();
	}

	private ArrayList getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaTiposParametroTipoVencimiento();
	}

	private void setListaDelCommunication(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaTiposParametroTipoVencimiento(lista);
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Metodos estaticos de la pagina">

	public void limpiarObjeto(int posicion, Class tipoClase, TextField campo) {
		try {
			this.getElementoPila().getObjetos().set(posicion, tipoClase.newInstance());
			if(campo != null)
				campo.setText("");
		} catch(Exception ex) {
		}
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMTipoVencimiento";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		this.getElementoPila().getObjetos().set(1, pObject);
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		TipoVencimiento tipoVencimiento = null;

		tipoVencimiento = (TipoVencimiento) pObject;
		this.getElementoPila().getObjetos().set(0, tipoVencimiento);
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$ABMTipoVencimiento$ABMTipoVencimiento}";
	}

	@Override
	public long getSerialVersionUID() {
		return TipoVencimiento.serialVersionUID;
	}
}