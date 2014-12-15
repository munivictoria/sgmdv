/*
 * ABMCategoria.java
 *
 * Created on 26 de octubre de 2006, 14:27
 * Copyright Trascender
 */
package muni.catastro.ABMCategoria;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.Categoria;
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
public class ABMCategoria extends ABMPageBean {

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

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private Categoria categoriaABM = null;

	private TipoConstruccion tipoConstruccionSeleccionado = null;

	public Categoria getCategoriaABM() {
		if (this.categoriaABM == null)
			this.categoriaABM = new Categoria();
		return categoriaABM;
	}

	public void setCategoriaABM(Categoria categoriaABM) {
		this.categoriaABM = categoriaABM;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private TextField tfTipoConstruccion = new TextField();

	public TextField getTfTipoConstruccion() {
		return tfTipoConstruccion;
	}

	public void setTfTipoConstruccion(TextField tf) {
		this.tfTipoConstruccion = tf;
	}

	private Button btnSeleccionarTipoConstruccion = new Button();

	public Button getBtnSeleccionarTipoConstruccion() {
		return btnSeleccionarTipoConstruccion;
	}

	public void setBtnSeleccionarTipoConstruccion(Button b) {
		this.btnSeleccionarTipoConstruccion = b;
	}

	private TextField tfCodigo = new TextField();

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tf) {
		this.tfCodigo = tf;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMCategoria() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, this.getCategoriaABM());

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		Categoria categoria = (Categoria) this.obtenerObjetoDelElementoPila(0, Categoria.class);

		categoria.setCodigo(this.getTextFieldValueInteger(getTfCodigo()));
		categoria.setNombre(this.getTextFieldValue(getTfNombre()));

		this.getElementoPila().getObjetos().set(0, categoria);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		Categoria categoria = (Categoria) this.obtenerObjetoDelElementoPila(0, Categoria.class);

		if (categoria.getCodigo() != null) {
			this.getTfCodigo().setText(categoria.getCodigo().toString());
		}
		this.getTfNombre().setText(categoria.getNombre());
		if (categoria.getTipoConstruccion() != null) {
			this.getTfTipoConstruccion().setText(categoria.getTipoConstruccion().toString());
		}
	}

	public String btnSeleccionarTipoConstruccion_action() {
		return navegarParaSeleccionar("AdminTipoConstruccion");
	}

	@Override
	protected String getNombrePagina() {
		return "Categoria";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMCategoria";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof TipoConstruccion) {
			if (pObject != null) {
				TipoConstruccion tipoConstruccion = (TipoConstruccion) pObject;
				Categoria categoria = (Categoria) this.obtenerObjetoDelElementoPila(0, Categoria.class);
				categoria.setTipoConstruccion(tipoConstruccion);

				this.getElementoPila().getObjetos().set(0, categoria);
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Categoria categoria = (Categoria) pObject;

		this.setCategoriaABM(categoria);

		this.getElementoPila().getObjetos().set(0, categoria);
	}
	
	@Override
	public long getSerialVersionUID() {
		return Categoria.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMCategoria$ABMCategoria}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Categoria locCategoria = this.obtenerObjetoDelElementoPila(0, Categoria.class);
		this.getTablaLogs().getLdpLogs().setList(locCategoria.getListaLogsAuditoria());
	}
}