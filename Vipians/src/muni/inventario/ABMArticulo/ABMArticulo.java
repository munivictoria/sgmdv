/*
 * AgregarArticulo.java
 *
 * Created on 24 de octubre de 2006, 10:40
 * Copyright Trascender SRL
 */

package muni.inventario.ABMArticulo;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.compras.recurso.persistent.inventario.InformacionTecnica;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
//import muni.compras.ABMFacturaProveedor.ConsultarFacturaProveedor;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMArticulo extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		Option[] opEstado = null;
		opEstado = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(InformacionTecnica.Estado.values(), "may");
		ddEstadoDefaultOptions.setOptions(opEstado);
	}

	private TextField tfCosto = new TextField();

	public TextField getTfCosto() {
		return tfCosto;
	}

	public void setTfCosto(TextField tfCosto) {
		this.tfCosto = tfCosto;
	}

	private TextField tfArea = new TextField();

	public TextField getTfArea() {
		return tfArea;
	}

	public void setTfArea(TextField tfArea) {
		this.tfArea = tfArea;
	}

	private TextField tfCodigoArticulo = new TextField();

	public TextField getTfCodigoArticulo() {
		return tfCodigoArticulo;
	}

	public void setTfCodigoArticulo(TextField tfCodigoArticulo) {
		this.tfCodigoArticulo = tfCodigoArticulo;
	}

	private TextField tfMarca = new TextField();

	public TextField getTfMarca() {
		return tfMarca;
	}

	public void setTfMarca(TextField tfMarca) {
		this.tfMarca = tfMarca;
	}

	private TextField tfModelo = new TextField();

	public TextField getTfModelo() {
		return tfModelo;
	}

	public void setTfModelo(TextField tfModelo) {
		this.tfModelo = tfModelo;
	}

	private TextField tfNumeroSerie = new TextField();

	public TextField getTfNumeroSerie() {
		return tfNumeroSerie;
	}

	public void setTfNumeroSerie(TextField tfNumeroSerie) {
		this.tfNumeroSerie = tfNumeroSerie;
	}

	private TextField tfMaterial = new TextField();

	public TextField getTfMaterial() {
		return tfMaterial;
	}

	public void setTfMaterial(TextField tfMaterial) {
		this.tfMaterial = tfMaterial;
	}

	private TextField tfColor = new TextField();

	public TextField getTfColor() {
		return tfColor;
	}

	public void setTfColor(TextField tfColor) {
		this.tfColor = tfColor;
	}

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	private Button btnSeleccionarArea = new Button();

	public Button getBtnSeleccionarArea() {
		return btnSeleccionarArea;
	}

	public void setBtnSeleccionarArea(Button btnSeleccionarArea) {
		this.btnSeleccionarArea = btnSeleccionarArea;
	}

	private Button btnLimpiarArea = new Button();

	public Button getBtnLimpiarArea() {
		return btnLimpiarArea;
	}

	public void setBtnLimpiarArea(Button btnLimpiarArea) {
		this.btnLimpiarArea = btnLimpiarArea;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	private TextArea taDescripcion = new TextArea();

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea ta) {
		this.taDescripcion = ta;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label label6) {
		this.label6 = label6;
	}

	private Label label7 = new Label();

	public Label getLabel7() {
		return label7;
	}

	public void setLabel7(Label label7) {
		this.label7 = label7;
	}

	private Label label8 = new Label();

	public Label getLabel8() {
		return label8;
	}

	public void setLabel8(Label label8) {
		this.label8 = label8;
	}

	private Label label9 = new Label();

	public Label getLabel9() {
		return label9;
	}

	public void setLabel9(Label label9) {
		this.label9 = label9;
	}

	private Label label10 = new Label();

	public Label getLabel10() {
		return label10;
	}

	public void setLabel10(Label label10) {
		this.label10 = label10;
	}

	private Label label11 = new Label();

	public Label getLabel11() {
		return label11;
	}

	public void setLabel11(Label label11) {
		this.label11 = label11;
	}

	private Label label12 = new Label();

	public Label getLabel12() {
		return label12;
	}

	public void setLabel12(Label label12) {
		this.label12 = label12;
	}

	private Label label13 = new Label();

	public Label getLabel13() {
		return label13;
	}

	public void setLabel13(Label label13) {
		this.label13 = label13;
	}

	private Label label14 = new Label();

	public Label getLabel14() {
		return label14;
	}

	public void setLabel14(Label label14) {
		this.label14 = label14;
	}

	private DropDown ddEstado = new DropDown();

	public DropDown getDdEstado() {
		return ddEstado;
	}

	public void setDdEstado(DropDown ddEstado) {
		this.ddEstado = ddEstado;
	}

	public SingleSelectOptionsList getDdEstadoDefaultOptions() {
		return ddEstadoDefaultOptions;
	}

	public void setDdEstadoDefaultOptions(SingleSelectOptionsList ddEstadoDefaultOptions) {
		this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
	}

	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();

	private TextField tfFechaEntradaServicio = new TextField();

	public TextField getTfFechaEntradaServicio() {
		return tfFechaEntradaServicio;
	}

	public void setTfFechaEntradaServicio(TextField tfFechaEntradaServicio) {
		this.tfFechaEntradaServicio = tfFechaEntradaServicio;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label label4) {
		this.label4 = label4;
	}

	private TextField tfFechaCompra = new TextField();

	public TextField getTfFechaCompra() {
		return tfFechaCompra;
	}

	public void setTfFechaCompra(TextField tfFechaCompra) {
		this.tfFechaCompra = tfFechaCompra;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMArticulo() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new Articulo());
		ep.getObjetos().add(ind++, new Area());
		ep.getObjetos().add(ind++, new InformacionTecnica());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	public String btnSeleccionarArea_action() {
		return navegarParaSeleccionar("AdminArea");
	}

	public String btnLimpiarArea_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(1, Area.class, this.getTfArea());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		Articulo articulo = (Articulo) this.obtenerObjetoDelElementoPila(ind++, Articulo.class);
		Area area = (Area) this.obtenerObjetoDelElementoPila(ind++, Area.class);
		InformacionTecnica informacionTecnica = (InformacionTecnica) this.obtenerObjetoDelElementoPila(ind++, InformacionTecnica.class);

		InformacionTecnica.Estado estado = null;

		// Obtener datos de los TextField
		Object codigo = this.getTfCodigoArticulo().getText();
		Object nombre = this.getTfNombre().getText();
		Object descripcion = this.getTaDescripcion().getText();
		Object fechaCompra = this.getTfFechaCompra().getText();
		Object fechaEntradaServicio = this.getTfFechaEntradaServicio().getText();
		Object costo = this.getTfCosto().getText();
		Object marca = this.getTfMarca().getText();
		Object modelo = this.getTfModelo().getText();
		Object numeroSerie = this.getTfNumeroSerie().getText();
		Object material = this.getTfMaterial().getText();
		Object color = this.getTfColor().getText();
		Object estadoSelected = ddEstado.getSelected();

		if(area.getIdArea() == -1) {
			area = null;
		}
		articulo.setArea(area);

		if(codigo != null && codigo != "") {
			articulo.setCodigo(codigo.toString());
		} else {
			articulo.setCodigo(null);
		}

		if(nombre != null && nombre != "") {
			articulo.setNombre(nombre.toString());
		} else {
			articulo.setNombre(null);
		}

		if(descripcion != null && descripcion != "") {
			articulo.setDescripcion(descripcion.toString());
		} else {
			articulo.setDescripcion(null);
		}

		if(fechaCompra != null && fechaCompra != "") {
			articulo.setFechaCompra(Conversor.getFechaCortaDeString(fechaCompra.toString()));
		} else {
			articulo.setFechaCompra(null);
		}

		if(fechaEntradaServicio != null && fechaEntradaServicio != "") {
			articulo.setFechaPuestaServicio(Conversor.getFechaCortaDeString(fechaEntradaServicio.toString()));
		} else {
			articulo.setFechaPuestaServicio(null);
		}

		if(costo != null && costo != "") {
			articulo.setCosto(Conversor.getDoubleDeString(costo.toString()));
		} else {
			articulo.setCosto(null);
		}

		if(marca != null && marca != "") {
			informacionTecnica.setMarca(marca.toString());
		} else {
			informacionTecnica.setMarca(null);
		}

		if(modelo != null && modelo != "") {
			informacionTecnica.setModelo(modelo.toString());
		} else {
			informacionTecnica.setModelo(null);
		}

		if(numeroSerie != null && numeroSerie != "") {
			informacionTecnica.setNumeroSerie(numeroSerie.toString());
		} else {
			informacionTecnica.setNumeroSerie(null);
		}

		if(material != null && material != "") {
			informacionTecnica.setMaterial(material.toString());
		} else {
			informacionTecnica.setMaterial(null);
		}

		if(color != null && color != "") {
			informacionTecnica.setColor(color.toString());
		} else {
			informacionTecnica.setColor(null);
		}

		if((estadoSelected != null) && (estadoSelected.toString().length() > 0)) {
			estado = InformacionTecnica.Estado.valueOf(estadoSelected.toString());
		} else {
			estado = null;
		}
		if(estado != null) {
			informacionTecnica.setEstado(estado);
		} else {
			informacionTecnica.setEstado(null);
		}

		articulo.setInformacionTecnica(informacionTecnica);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, articulo);
		this.getElementoPila().getObjetos().set(ind++, area);
		this.getElementoPila().getObjetos().set(ind++, informacionTecnica);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		Articulo articulo = null;
		Area area = null;
		InformacionTecnica informacionTecnica = null;

		int ind = 0;
		articulo = (Articulo) this.obtenerObjetoDelElementoPila(ind++, Articulo.class);
		area = (Area) this.obtenerObjetoDelElementoPila(ind++, Area.class);
		informacionTecnica = (InformacionTecnica) this.obtenerObjetoDelElementoPila(ind++, InformacionTecnica.class);

		this.getTfCodigoArticulo().setText(articulo.getCodigo());
		this.getTfNombre().setText(articulo.getNombre());
		this.getTaDescripcion().setText(articulo.getDescripcion());
		this.getTfFechaCompra().setText(Conversor.getStringDeFechaCorta(articulo.getFechaCompra()));
		this.getTfFechaEntradaServicio().setText(Conversor.getStringDeFechaCorta(articulo.getFechaPuestaServicio()));
		this.getTfCosto().setText(Conversor.getStringDeDouble(articulo.getCosto()));
		if(area != null && area.getIdArea() != -1) {
			this.getTfArea().setText(area);
		}
		this.getTfMarca().setText(informacionTecnica.getMarca());
		this.getTfModelo().setText(informacionTecnica.getModelo());
		this.getTfNumeroSerie().setText(informacionTecnica.getNumeroSerie());
		this.getTfMaterial().setText(informacionTecnica.getMaterial());
		this.getTfColor().setText(informacionTecnica.getColor());
		this.getDdEstado().setSelected(Util.getEnumNameFromString(String.valueOf(informacionTecnica.getEstado())));
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof Area) {
			Area area = (Area) pObject;
			this.getElementoPila().getObjetos().set(1, area);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Articulo articulo = (Articulo) pObject;
		Area area = (Area) articulo.getArea();
		InformacionTecnica informacionTecnica = (InformacionTecnica) articulo.getInformacionTecnica();

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, articulo);
		this.getElementoPila().getObjetos().set(ind++, area);
		this.getElementoPila().getObjetos().set(ind++, informacionTecnica);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMArticulo";
	}

	@Override
	public String getNombreBean() {
		return "#{inventario$ABMArticulo$ABMArticulo}";
	}

	@Override
	public long getSerialVersionUID() {
		return Articulo.serialVersionUID;
	}
}