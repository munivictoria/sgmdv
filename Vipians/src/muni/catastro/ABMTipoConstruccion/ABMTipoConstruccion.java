/*
 * ModificarTipoConstruccion.java
 *
 * Created on 24 de octubre de 2006, 11:40
 * Copyright Trascender
 */
package muni.catastro.ABMTipoConstruccion;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.TipoConstruccion;
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
public class ABMTipoConstruccion extends ABMPageBean {

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

	private TipoConstruccion tipoConstruccionABM = null;

	public TipoConstruccion getTipoConstruccionABM() {
		if (this.tipoConstruccionABM == null)
			this.tipoConstruccionABM = new TipoConstruccion();
		return this.tipoConstruccionABM;
	}

	public void setTipoConstruccionABM(TipoConstruccion pTipoConstruccion) {
		this.tipoConstruccionABM = pTipoConstruccion;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, this.getTipoConstruccionABM());

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		TipoConstruccion tipoConstruccion = (TipoConstruccion) this.obtenerObjetoDelElementoPila(0, TipoConstruccion.class);

		tipoConstruccion.setNombre(this.getTextFieldValue(getTfNombre()));
		tipoConstruccion.setDescripcion(this.getTextAreaValue(getTxDescripcion()));

		this.getElementoPila().getObjetos().set(0, tipoConstruccion);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		TipoConstruccion tipoConstruccion = (TipoConstruccion) this.obtenerObjetoDelElementoPila(0, TipoConstruccion.class);

		this.getTfNombre().setText(tipoConstruccion.getNombre());
		this.getTxDescripcion().setText(tipoConstruccion.getDescripcion());
	}

	private TextArea txDescripcion = new TextArea();

	public TextArea getTxDescripcion() {
		return txDescripcion;
	}

	public void setTxDescripcion(TextArea ta) {
		this.txDescripcion = ta;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p\225gina.
	 * </p>
	 */
	public ABMTipoConstruccion() {
	}

	@Override
	protected String getNombrePagina() {
		return "Tipo Construccion";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMTipoConstruccion";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		TipoConstruccion tipoConstruccion = (TipoConstruccion) pObject;
		this.setTipoConstruccionABM(tipoConstruccion);

		this.getElementoPila().getObjetos().set(0, tipoConstruccion);
	}
	
	@Override
	public long getSerialVersionUID() {
		return TipoConstruccion.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMTipoConstruccion$ABMTipoConstruccion}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		TipoConstruccion locTipoConstruccion = this.obtenerObjetoDelElementoPila(0, TipoConstruccion.class);
		this.getTablaLogs().getLdpLogs().setList(locTipoConstruccion.getListaLogsAuditoria());
	}
}