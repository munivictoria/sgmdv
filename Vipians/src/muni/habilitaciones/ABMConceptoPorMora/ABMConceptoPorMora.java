/*
 * AgregarConceptoPorMora.java
 *
 * Created on 18 de octubre de 2006, 10:30
 * Copyright Trascender SRL
 */

package muni.habilitaciones.ABMConceptoPorMora;

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
import com.trascender.habilitaciones.recurso.persistent.ConceptoPorMora;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMConceptoPorMora extends ABMPageBean {

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

		/*
		 * Option[] op = null; op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(TipoModificador.EnumTipoModificador.values(),"cap");
		 */
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

	private Label lblEntonces = new Label();

	public Label getLblEntonces() {
		return lblEntonces;
	}

	public void setLblEntonces(Label l) {
		this.lblEntonces = l;
	}

	private Label lblSino = new Label();

	public Label getLblSino() {
		return lblSino;
	}

	public void setLblSino(Label l) {
		this.lblSino = l;
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

	private TextField tfValorFinalSino = new TextField();

	public TextField getTfValorFinalSino() {
		return tfValorFinalSino;
	}

	public void setTfValorFinalSino(TextField tf) {
		this.tfValorFinalSino = tf;
	}

	private TextField tfValorFinal = new TextField();

	public TextField getTfValorFinal() {
		return tfValorFinal;
	}

	public void setTfValorFinal(TextField tf) {
		this.tfValorFinal = tf;
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

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private ObjectListDataProvider ldpConceptoPorMora = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpConceptoPorMora() {
		return ldpConceptoPorMora;
	}

	public void setLdpConceptoPorMora(ObjectListDataProvider oldp) {
		this.ldpConceptoPorMora = oldp;
	}

	private Label label13 = new Label();

	public Label getLabel13() {
		return label13;
	}

	public void setLabel13(Label l) {
		this.label13 = l;
	}

	// </editor-fold>
	/**
	 * <p>
	 * Construir una instancia de bean de página.
	 * </p>
	 */
	public ABMConceptoPorMora() {
	}

	/**
	 * <p>
	 * Método de devolución de llamada al que se llama cuando se navega hasta esta página, ya sea directamente mediante un URL o de manera indirecta a través de
	 * la navegación de páginas. Puede personalizar este método para adquirir recursos que se necesitarán para los controladores de eventos y métodos del
	 * proceso, sin tener en cuenta si esta página realiza procesamiento de devolución de envíos.
	 * </p>
	 * 
	 * <p>
	 * Tenga en cuenta que si la petición actual es una devolución de envío, los valores de propiedad de los componentes <strong>no</strong> representan ningún
	 * valor enviado con esta petición. En su lugar, representan los valores de propiedades que se guardaron para esta vista cuando se procesó.
	 * </p>
	 */

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new ConceptoPorMora());
		ep.getObjetos().add(ind++, new ArrayList()); // lista de parametros

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		int ind = 0;
		ConceptoPorMora conceptoPorMora = (ConceptoPorMora) this.obtenerObjetoDelElementoPila(ind++, ConceptoPorMora.class);
		ArrayList parametros = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		conceptoPorMora.setNombre(this.getTextFieldValue(this.getTfNombre()));
		conceptoPorMora.setFormula(this.getTextAreaValue(this.getTaCondicion()));

		this.getObjectListDataProvider().commitChanges();
		parametros = (ArrayList) this.getObjectListDataProvider().getList();
		this.setListaDelCommunication(parametros);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, conceptoPorMora);
		this.getElementoPila().getObjetos().set(ind++, parametros);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		int ind = 0;

		ConceptoPorMora conceptoPorMora = null;

		// TipoModificador tipoModificador = null;
		ArrayList parametros = null;

		conceptoPorMora = (ConceptoPorMora) this.obtenerObjetoDelElementoPila(ind++, ConceptoPorMora.class);
		parametros = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		// variables/parametros
		this.getObjectListDataProvider().setList(parametros);
		Option[] op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(this.getObjectListDataProvider().getObjects(), "cap");
		lbVariablesDefaultOptions.setOptions(op);

		this.getTfNombre().setText(conceptoPorMora.getNombre());
		this.getTaCondicion().setText(conceptoPorMora.getFormula());

		this.setListaDelCommunication(parametros);
		this.getObjectListDataProvider().setList(parametros);
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpConceptoPorMora();
	}

	private ArrayList getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaConceptoPorMora();
	}

	private void setListaDelCommunication(ArrayList lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaConceptoPorMora(lista);
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Metodos estaticos de la pagina">

	@Override
	public void limpiarObjeto(int posicion, Class tipoClase, TextField campo) {
		try {
			this.getElementoPila().getObjetos().set(posicion, tipoClase.newInstance());
			if(campo != null) {
				campo.setText("");
			}
		} catch(Exception ex) {
		}
	}

	// </editor-fold>

	/*
	 * public String btnCancelar_action() { String retorno = null; boolean ultimo = this.ultimoElementoPilaDeSubSesion();
	 * 
	 * if (ultimo) {
	 * 
	 * ArrayList tiposParametro = (ArrayList)this.obtenerObjetoDelElementoPila(1, ArrayList.class); tiposParametro.remove(TipoParametroInteres.IMPORTE_INTERES);
	 * 
	 * retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR); } else { retorno = this.prepararCaducidad(); } return retorno; }
	 */

	private void cargarValoresPorDefecto() {
		// CAMBIAR: Obtener los valores por defecto.
		/*
		 * TipoModificador tipoModificador = (TipoModificador) this.obtenerObjetoDelElementoPila(0, TipoModificador.class); Integer def = new Integer(0);
		 * tipoModificador.setDesdeDias(def); tipoModificador.setDesdeMeses(def); tipoModificador.setHastaDias(def); tipoModificador.setHastaMeses(def);
		 * this.getElementoPila().getObjetos().set(0, tipoModificador);
		 */
		return;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMConceptoPorMora";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		this.getElementoPila().getObjetos().set(1, pObject);
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		ConceptoPorMora conceptoPorMora = (ConceptoPorMora) pObject;
		this.getElementoPila().getObjetos().set(0, conceptoPorMora);
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora}";
	}

	@Override
	public long getSerialVersionUID() {
		return ConceptoPorMora.serialVersionUID;
	}
}