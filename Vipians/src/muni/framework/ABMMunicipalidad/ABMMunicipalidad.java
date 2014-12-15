/*
 * ABMMunicipalidad.java
 *
 * Created on 25 de octubre de 2006, 14:30
 * Copyright Trascender
 */
package muni.framework.ABMMunicipalidad;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.ImageHyperlink;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.Upload;
import com.sun.rave.web.ui.model.UploadedFile;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Municipalidad;
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
public class ABMMunicipalidad extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
	}

	private StaticText stVariosServicios = new StaticText();
	private StaticText stNumerarSolSum = new StaticText();
	private StaticText stNombreCuentaConcatenado = new StaticText();
	private StaticText stMontoFacturaVariaoc = new StaticText();
	private Checkbox cbVariosServicios = new Checkbox();
	private Checkbox cbNumerarSolSum = new Checkbox();
	private Checkbox cbNombreCuentaConcatenado = new Checkbox();
	private Checkbox cbMontoFacturaVariaoc = new Checkbox();
	private Label lblRutaReporte = new Label();
	private TextField tfRutaReporte = new TextField();
	
	public Label getLblRutaReporte() {
		return lblRutaReporte;
	}

	public void setLblRutaReporte(Label lblRutaReporte) {
		this.lblRutaReporte = lblRutaReporte;
	}

	public TextField getTfRutaReporte() {
		return tfRutaReporte;
	}

	public void setTfRutaReporte(TextField tfRutaReporte) {
		this.tfRutaReporte = tfRutaReporte;
	}

	public StaticText getStNombreCuentaConcatenado() {
		return stNombreCuentaConcatenado;
	}

	public void setStNombreCuentaConcatenado(StaticText stNombreCuentaConcatenado) {
		this.stNombreCuentaConcatenado = stNombreCuentaConcatenado;
	}

	public Checkbox getCbNombreCuentaConcatenado() {
		return cbNombreCuentaConcatenado;
	}

	public void setCbNombreCuentaConcatenado(Checkbox cbNombreCuentaConcatenado) {
		this.cbNombreCuentaConcatenado = cbNombreCuentaConcatenado;
	}

	public StaticText getStVariosServicios() {
		return stVariosServicios;
	}

	public void setStVariosServicios(StaticText stVariosServicios) {
		this.stVariosServicios = stVariosServicios;
	}

	public StaticText getStNumerarSolSum() {
		return stNumerarSolSum;
	}

	public void setStNumerarSolSum(StaticText stNumerarSolSum) {
		this.stNumerarSolSum = stNumerarSolSum;
	}

	public Checkbox getCbVariosServicios() {
		return cbVariosServicios;
	}

	public void setCbVariosServicios(Checkbox cbVariosServicios) {
		this.cbVariosServicios = cbVariosServicios;
	}

	public Checkbox getCbNumerarSolSum() {
		return cbNumerarSolSum;
	}

	public void setCbNumerarSolSum(Checkbox cbNumerarSolSum) {
		this.cbNumerarSolSum = cbNumerarSolSum;
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

	private TextField tfTelefono = new TextField();

	public TextField getTfTelefono() {
		return tfTelefono;
	}

	public void setTfTelefono(TextField tf) {
		this.tfTelefono = tf;
	}

	private Label label13 = new Label();

	public Label getLabel13() {
		return label13;
	}

	public void setLabel13(Label l) {
		this.label13 = l;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private Button btnSeleccionarDomicilio = new Button();

	public Button getBtnSeleccionarDomicilio() {
		return btnSeleccionarDomicilio;
	}

	public void setBtnSeleccionarDomicilio(Button b) {
		this.btnSeleccionarDomicilio = b;
	}

	private StaticText stDomicilio = new StaticText();

	public StaticText getStDomicilio() {
		return stDomicilio;
	}

	public void setStDomicilio(StaticText st) {
		this.stDomicilio = st;
	}

	private Upload upLogo = new Upload();

	public Upload getUpLogo() {
		return upLogo;
	}

	public void setUpLogo(Upload upLogo) {
		this.upLogo = upLogo;
	}

	transient private UploadedFile uploadedFile;

	public UploadedFile getUploadedFile() {
		return this.uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	private ImageHyperlink imagen = new ImageHyperlink();

	public ImageHyperlink getImagen() {
		return imagen;
	}

	public void setImagen(ImageHyperlink imagen) {
		this.imagen = imagen;
	}

	public StaticText nombreDelLogo = new StaticText();

	public StaticText getNombreDelLogo() {
		return nombreDelLogo;
	}

	public void setNombreDelLogo(StaticText nombreDelLogo) {
		this.nombreDelLogo = nombreDelLogo;
	}

	private String strUrlImagen = "";

	public String getStrUrlImagen() {
		return strUrlImagen;
	}

	public void setStrUrlImagen(String strUrlImagen) {
		this.strUrlImagen = strUrlImagen;
	}

	public StaticText getStMontoFacturaVariaoc() {
		return stMontoFacturaVariaoc;
	}

	public void setStMontoFacturaVariaoc(StaticText stMontoFacturaVariaoc) {
		this.stMontoFacturaVariaoc = stMontoFacturaVariaoc;
	}

	public Checkbox getCbMontoFacturaVariaoc() {
		return cbMontoFacturaVariaoc;
	}

	public void setCbMontoFacturaVariaoc(Checkbox cbMontoFacturaVariaoc) {
		this.cbMontoFacturaVariaoc = cbMontoFacturaVariaoc;
	}

	private Label stEncabezado = new Label();
	private Label stSubEncabezado = new Label();

	private TextField tfEncabezado = new TextField();
	private TextField tfSubEncabezado = new TextField();

	public Label getStEncabezado() {
		return stEncabezado;
	}

	public void setStEncabezado(Label stEncabezado) {
		this.stEncabezado = stEncabezado;
	}

	public Label getStSubEncabezado() {
		return stSubEncabezado;
	}

	public void setStSubEncabezado(Label stSubEncabezado) {
		this.stSubEncabezado = stSubEncabezado;
	}

	public TextField getTfEncabezado() {
		return tfEncabezado;
	}

	public void setTfEncabezado(TextField tfEncabezado) {
		this.tfEncabezado = tfEncabezado;
	}

	public TextField getTfSubEncabezado() {
		return tfSubEncabezado;
	}

	public void setTfSubEncabezado(TextField tfSubEncabezado) {
		this.tfSubEncabezado = tfSubEncabezado;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMMunicipalidad() {

	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Municipalidad());
		ep.getObjetos().add(ind++, new Domicilio());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		Municipalidad municipalidad = (Municipalidad) this.obtenerObjetoDelElementoPila(0, Municipalidad.class);
		Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(1, Domicilio.class);

		municipalidad.setNombre(this.getTextFieldValue(getTfNombre()));
		municipalidad.setTelefono(this.getTextFieldValue(getTfTelefono()));

		if (domicilio.getLocalidad() == null) {
			domicilio = null;
		}
		municipalidad.setEncabezadoReportes(this.getTextFieldValue(getTfEncabezado()));
		municipalidad.setSubencabezadoReportes(this.getTextFieldValue(getTfSubEncabezado()));

		municipalidad.setDomicilio(domicilio);
		if (this.getUploadedFile().getOriginalName() != null && !this.getUploadedFile().getOriginalName().isEmpty() && this.getUploadedFile().getBytes() != null) {
			municipalidad.setLogo(this.getUploadedFile().getBytes());
			municipalidad.setNombreLogo(this.getUploadedFile().getOriginalName());
			System.out.println("Nuevos datos agregados: " + this.getUploadedFile().getOriginalName() + "   " + this.getUploadedFile().getBytes());
		}
		System.out.println("NOMBRE ORIGINAL: " + municipalidad.getNombreLogo());

		Object variosServicios = this.getCbVariosServicios().getValue();
		Object numerarSolSum = this.getCbNumerarSolSum().getValue();
		Object nombreCuentaConcatenado = this.getCbNombreCuentaConcatenado().getValue();
		Object montofacturavariaoc = this.getCbMontoFacturaVariaoc().getValue();

		if (variosServicios != null && variosServicios != "") {
			municipalidad.setVariosServiciosOSP(((Boolean) variosServicios).booleanValue());
		} else {
			municipalidad.setVariosServiciosOSP(false);
		}
		if (numerarSolSum != null && numerarSolSum != "") {
			municipalidad.setNumSolSuministroPorArea(((Boolean) numerarSolSum).booleanValue());
		} else {
			municipalidad.setNumSolSuministroPorArea(false);
		}
		if (nombreCuentaConcatenado != null && nombreCuentaConcatenado != "") {
			municipalidad.setNombreCuentaConcatenado(((Boolean) nombreCuentaConcatenado).booleanValue());
		} else {
			municipalidad.setNombreCuentaConcatenado(false);
		}
		if (montofacturavariaoc != null && montofacturavariaoc != ""){
			municipalidad.setMontoFacturaVariaoc(((Boolean) montofacturavariaoc).booleanValue());
		} else {
			municipalidad.setMontoFacturaVariaoc(false);
		}
		municipalidad.setRutaReportes(this.getTextFieldValue(this.getTfRutaReporte()));
		
		this.getElementoPila().getObjetos().set(0, municipalidad);
		this.getElementoPila().getObjetos().set(1, domicilio);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		if (this.getRequestBean1().getRespuestaABM() != null) {
			Object respuesta = this.getRequestBean1().getRespuestaABM();
			int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
			this.getElementoPila().getObjetos().set(posicion, respuesta);
		}

		this.acomodarSeleccionado();

		Municipalidad municipalidad = (Municipalidad) this.obtenerObjetoDelElementoPila(0, Municipalidad.class);
		Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(1, Domicilio.class);

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("arregloBytes", municipalidad.getLogo());
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nombreImagen", municipalidad.getNombreLogo());

		this.getTfNombre().setText(municipalidad.getNombre());
		this.getTfTelefono().setText(municipalidad.getTelefono());
		this.getStDomicilio().setText(domicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));
		this.getTfEncabezado().setText(municipalidad.getEncabezadoReportes());
		this.getTfSubEncabezado().setText(municipalidad.getSubencabezadoReportes());

		if (municipalidad.getNombreLogo() != null && !municipalidad.getNombreLogo().equals("")) {
			this.getNombreDelLogo().setText(municipalidad.getNombreLogo());
		}

		this.getCbVariosServicios().setValue(municipalidad.isVariosServiciosOSP());
		this.getCbNumerarSolSum().setValue(municipalidad.isNumSolSuministroPorArea());
		this.getCbNombreCuentaConcatenado().setValue(municipalidad.isNombreCuentaConcatenado());
		this.getCbMontoFacturaVariaoc().setValue(municipalidad.isMontoFacturaVariaoc());
		this.getTfRutaReporte().setText(municipalidad.getRutaReportes());
	}

	public String btnSeleccionarDomicilio_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 1;

		if (ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(posicionObjetoSeleccionado, Domicilio.class);
			if (domicilio != null) {

				Localidad localidad = domicilio.getLocalidad();

				if (localidad != null) {
					this.getRequestBean1().setObjetoABM(domicilio);
					retorno = "ModificarDomicilio";
				} else {
					retorno = "AgregarDomicilio";
				}
			} else {
				retorno = "AgregarDomicilio";
			}

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public void uploadChangeListener(ValueChangeEvent evento) {
		System.out.println("COMPONENTE " + upLogo);
		System.out.println("VALUE " + upLogo.getValue());
		System.out.println("CLASE DEL VALUE " + upLogo.getValue().getClass().getName());

		Municipalidad municipalidad;
		UploadedFile file = (UploadedFile) upLogo.getValue();

		municipalidad = (Municipalidad) this.obtenerObjetoDelElementoPila(0, Municipalidad.class);
		System.out.println("MUNICIPALIDAD " + municipalidad);

		if (file.getOriginalName() != null && !file.getOriginalName().equals("") && file.getBytes() != null) {

			System.out.println("VALUE FILE " + file.getBytes());
			municipalidad.setLogo(file.getBytes());
			municipalidad.setNombreLogo(file.getOriginalName());
		}

		System.out.println("BYTES DEL ARREGLO DE FOTO: " + file.getBytes());

		this.getElementoPila().getObjetos().set(0, municipalidad);
		this.mostrarEstadoObjetosUsados();
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMMunicipalidad";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Municipalidad municipalidad = (Municipalidad) pObject;
		Domicilio domicilio = municipalidad.getDomicilio();

		this.getElementoPila().getObjetos().set(0, municipalidad);
		this.getElementoPila().getObjetos().set(1, domicilio);
	}
	
	@Override
	public long getSerialVersionUID() {
		return Municipalidad.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMMunicipalidad$ABMMunicipalidad}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Municipalidad locMunicipalidad = this.obtenerObjetoDelElementoPila(0, Municipalidad.class);
		this.getTablaLogs().getLdpLogs().setList(locMunicipalidad.getListaLogsAuditoria());
	}
}