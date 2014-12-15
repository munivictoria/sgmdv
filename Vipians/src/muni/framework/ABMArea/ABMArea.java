/*
 * ABMArea.java
 *
 * Created on 24 de octubre de 2006, 12:53
 * Copyright Trascender SRL
 */
package muni.framework.ABMArea;

import java.util.Set;

import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMArea extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		Set<String> locListaSecretarias = getApplicationBean1().getMapaSecretaria().keySet();

		Option[] opSecretarias = new Option[locListaSecretarias.size() + 1];
		int i = 0;
		opSecretarias[i++] = new Option("", "");
		for (String cadaSecretaria : locListaSecretarias) {
			opSecretarias[i++] = new Option(cadaSecretaria, cadaSecretaria);
		}
		this.ddSecretariaOptions.setOptions(opSecretarias);
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

	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label label6) {
		this.label6 = label6;
	}

	private DropDown ddSecretaria = new DropDown();

	public DropDown getDdSecretaria() {
		return ddSecretaria;
	}

	public void setDdSecretaria(DropDown ddSecretaria) {
		this.ddSecretaria = ddSecretaria;
	}

	private SingleSelectOptionsList ddSecretariaOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdSecretariaOptions() {
		return ddSecretariaOptions;
	}

	public void setDdSecretariaOptions(SingleSelectOptionsList ddSecretariaOptions) {
		this.ddSecretariaOptions = ddSecretariaOptions;
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

	private TextArea taDescripcion = new TextArea();

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea ta) {
		this.taDescripcion = ta;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMArea() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Area());
		ep.getObjetos().add(ind++, new Secretaria());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		Area area = (Area) this.obtenerObjetoDelElementoPila(ind++, Area.class);
		Secretaria secretaria = (Secretaria) this.obtenerObjetoDelElementoPila(ind++, Secretaria.class);

		area.setNombre(this.getTextFieldValue(getTfNombre()));
		area.setDescripcion(this.getTextAreaValue(getTaDescripcion()));
		area.setSecretaria(this.getDDObjectValue(getDdSecretaria(), getApplicationBean1().getMapaSecretaria()));

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, area);
		this.getElementoPila().getObjetos().set(ind++, secretaria);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		this.acomodarSeleccionado();

		int ind = 0;
		Area area = (Area) this.obtenerObjetoDelElementoPila(ind++, Area.class);
		Secretaria secretaria = (Secretaria) this.obtenerObjetoDelElementoPila(ind++, Secretaria.class);

		this.getTfNombre().setText(area.getNombre());
		this.getTaDescripcion().setText(area.getDescripcion());
		if (secretaria != null && secretaria.getIdSecretaria() != -1) {
			this.getDdSecretaria().setSelected(secretaria.toString());
		}
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMArea";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Area area = (Area) pObject;
		Secretaria secretaria = area.getSecretaria();

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, area);
		this.getElementoPila().getObjetos().set(ind++, secretaria);
	}
	
	@Override
	public long getSerialVersionUID() {
		return Area.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMArea$ABMArea}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Area locArea = this.obtenerObjetoDelElementoPila(0, Area.class);
		this.getTablaLogs().getLdpLogs().setList(locArea.getListaLogsAuditoria());
	}
}