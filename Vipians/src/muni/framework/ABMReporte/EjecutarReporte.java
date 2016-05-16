/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.framework.ABMReporte;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;

import muni.Nav1;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.faces.application.ConfigNavigationCase;
import com.sun.rave.web.ui.component.CheckboxGroup;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.Upload;
import com.sun.rave.web.ui.model.MultipleSelectOptionsList;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.persistent.reporteDinamico.OpcionParametroReporte;
import com.trascender.framework.recurso.persistent.reporteDinamico.ParametroReporte;
import com.trascender.framework.recurso.persistent.reporteDinamico.ParametroReporte.Tipo;
import com.trascender.framework.recurso.persistent.reporteDinamico.Reporte;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.navegacion.Enlace;

public class EjecutarReporte extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public EjecutarReporte() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Reporte());
		ep.getObjetos().add(ind++, new String());// Será el Nombre del Atributo del Parametro o del Reporte que navega a un Admin para seleccionar.

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));

		// Reseteamos el mapa de parametros
		this.getComunicationBean().getMapaParametrosReporte().clear();

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		Reporte reporte = (Reporte) this.obtenerObjetoDelElementoPila(ind++, Reporte.class);
		reporte.setNombre(this.getTextFieldValue(getTfNombre()));

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, reporte);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		Reporte reporte = (Reporte) this.obtenerObjetoDelElementoPila(ind++, Reporte.class);
		this.armarPanelParametros(reporte);

		this.getTfNombre().setText(reporte.getNombre());
	}

	@Override
	protected String getCasoNavegacion() {
		return "EjecutarReporte";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		String nombreParametro = (String) obtenerObjetoDelElementoPila(1);
		this.getMapaParametros().put(nombreParametro, pObject);
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Reporte reporte = (Reporte) pObject;

		if(this.getRequestBean1().getObjetosSeleccionMultiple() != null
				&& this.getRequestBean1().getObjetosSeleccionMultiple().size() > 0) {
			this.getMapaParametros().put("registrosSeleccionados", this.getRequestBean1().getObjetosSeleccionMultiple());
			this.getRequestBean1().setObjetosSeleccionMultiple(null);
		}
		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, reporte);
	}

	@Override
	public long getSerialVersionUID() {
		return Reporte.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMReporte$EjecutarReporte}";
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();
	}

	private PanelGroup panelParametros = new PanelGroup();

	private void armarPanelParametros(Reporte reporte) {
		HtmlPanelGrid panelGrid = new HtmlPanelGrid();
		panelGrid.setColumns(3);

		for(ParametroReporte cadaParametro : reporte.getListaParametroReporte()) {
			// Label del parametro
			Label label = new Label();
			label.setText(cadaParametro.getNombre());
			label.setStyleClass("label");
			panelGrid.getChildren().add(label);

			// Reemplazamos los espacios por guiones bajo porque no se pueden usar espacios como ID html
			String idComponenteValor = "Parametro" + "-" + cadaParametro.getTipo().toString().replace(" ", "_") + "-" + cadaParametro.getNombreAtributo();

			if(cadaParametro.getTipo() == Tipo.LISTADO_SIMPLE) {
				DropDown dropDownValor = new DropDown();
				dropDownValor.setId("dd" + idComponenteValor);
				label.setFor("dd" + idComponenteValor);

				// lista de opciones
				SingleSelectOptionsList dropDownList = new SingleSelectOptionsList();
				Option[] op = null;
				op = armarArrayOptions(cadaParametro.getListaOpciones());
				dropDownList.setOptions(op);
				dropDownValor.setItems(dropDownList.getOptions());
				dropDownValor.setValueBinding("value", this._getValueBinding(this.armarExpressionEnBean("mapaParametros['" + cadaParametro.getNombreAtributo() + "']")));

				panelGrid.getChildren().add(dropDownValor);
				panelGrid.getChildren().add(new StaticText());
			} else if(cadaParametro.getTipo() == Tipo.LISTADO_MULTIPLE) {
				CheckboxGroup cbgOpciones = new CheckboxGroup();
				cbgOpciones.setId("lb" + idComponenteValor);
				label.setFor("lb" + idComponenteValor);
				cbgOpciones.setColumns(4);

				// lista de opciones multiple
				MultipleSelectOptionsList opcionesOptions = new MultipleSelectOptionsList();
				Option[] op = new Option[cadaParametro.getListaOpciones().size()];
				for(int i = 0; i < cadaParametro.getListaOpciones().size(); i++) {
					String display = cadaParametro.getListaOpciones().get(i).getNombre();
					Option opcion = new Option(display);
					op[i] = opcion;
				}
				opcionesOptions.setOptions(op);

				cbgOpciones.setItems(opcionesOptions.getOptions());
				cbgOpciones.setValueBinding("selected", this._getValueBinding(this.armarExpressionEnBean("mapaParametros['" + cadaParametro.getNombreAtributo() + "']")));
				this.getMapaParametros().put(cadaParametro.getNombreAtributo(), new String[] {});

				panelGrid.getChildren().add(cbgOpciones);
				panelGrid.getChildren().add(new StaticText());
			} else if(cadaParametro.getTipo() == Tipo.BOOLEANO) {
				DropDown dropDownValor = new DropDown();
				dropDownValor.setId("dd" + idComponenteValor);
				label.setFor("dd" + idComponenteValor);

				// lista de opciones
				SingleSelectOptionsList dropDownList = new SingleSelectOptionsList();
				Option[] op = new Option[3];
				op[0] = new Option("", "");
				op[1] = new Option(Boolean.TRUE, "Si");
				op[2] = new Option(Boolean.FALSE, "No");

				dropDownList.setOptions(op);
				dropDownValor.setItems(dropDownList.getOptions());
				dropDownValor.setValueBinding("value", this._getValueBinding(this.armarExpressionEnBean("mapaParametros['" + cadaParametro.getNombreAtributo() + "']")));
				dropDownValor.setValueBinding("converter", this._getValueBinding(this.armarExpressionEnBean("converterBooleano")));

				panelGrid.getChildren().add(dropDownValor);
				panelGrid.getChildren().add(new StaticText());
			} else if(cadaParametro.getTipo() == Tipo.IMAGEN) {
				Upload upload = new Upload();
				upload.setId("ul" + idComponenteValor);
				upload.setToolTip("Click para buscar o Arrastra una imagen.");
				upload.setStyle("border-style: dotted; padding: 10px 100px; background-color: #FFF");
				upload.setValueBinding("uploadedFile", this._getValueBinding(this.armarExpressionEnBean("mapaParametros['" + cadaParametro.getNombreAtributo() + "']")));
				label.setFor("ul" + idComponenteValor);

				panelGrid.getChildren().add(upload);
				panelGrid.getChildren().add(new StaticText());
			} else if(cadaParametro.getTipo() == Tipo.FIRMA_ELECTRONICA) {
				HtmlPanelGroup pgCanvas = new HtmlPanelGroup();
				pgCanvas.setId("divCanvas" + idComponenteValor);
				pgCanvas.setLayout("block");

				panelGrid.getChildren().add(pgCanvas);
				panelGrid.getChildren().add(new StaticText());
			} else if(cadaParametro.getTipo() == Tipo.CADENA_LARGA) {
				TextArea ta = new TextArea();
				ta.setId("ta" + idComponenteValor);
				ta.setColumns(40);
				ta.setRows(4);
				ta.setStyleClass("textField");
				panelGrid.getChildren().add(ta);

				ta.setValueBinding("text", this._getValueBinding(this.armarExpressionEnBean("mapaParametros['" + cadaParametro.getNombreAtributo() + "']")));

				panelGrid.getChildren().add(ta);
				panelGrid.getChildren().add(new StaticText());
			} else {
				// Textfield para el valor del parametro
				TextField tf = new TextField();

				// Chequemaos si hay un valor
				Object valor = getMapaParametros().get(cadaParametro.getNombreAtributo());
				if(valor != null) {
					tf.setText(valor);
				} else {
					tf.setText(null);
				}
				tf.setColumns(40);
				tf.setId("tf" + idComponenteValor);
				label.setFor("tf" + idComponenteValor);
				tf.setStyleClass("textField");
				panelGrid.getChildren().add(tf);

				// Si es Recurso, agregamos la lupa y el boton borrar juntos, si no, solo el borrar.
				if(cadaParametro.getTipo() == Tipo.RECURSO) {
					tf.setDisabled(true);
					tf.setStyleClass("textFieldDisabled");

					HtmlPanelGrid panelRecurso = new HtmlPanelGrid();
					panelRecurso.setColumns(2);

					HtmlAjaxCommandButton boton = new HtmlAjaxCommandButton();
					boton.setStyleClass("buttonSeleccionarAjax");
					boton.addActionListener(this.getActionListener(this.armarExpressionEnBean("botonSeleccionRecursoListener(javax.faces.event.ActionEvent)")));
					boton.getChildren().add(getUIParameter(cadaParametro.getNombreAtributo()));
					panelRecurso.getChildren().add(boton);

					panelRecurso.getChildren().add(getBtnLimpiarCampo(cadaParametro, "tf" + idComponenteValor));

					panelGrid.getChildren().add(panelRecurso);
				} else {
					// panelGrid.getChildren().add(getBtnLimpiarCampo(cadaParametro, idComponenteValor));
					panelGrid.getChildren().add(new StaticText());

					if(cadaParametro.getTipo() != Tipo.CADENA) {
						tf.setColumns(10);
					}
					tf.setValueBinding("text", this._getValueBinding(this.armarExpressionEnBean("mapaParametros['" + cadaParametro.getNombreAtributo() + "']")));
				}

				if(cadaParametro.getTipo() == Tipo.FECHA) {
					tf.setValueBinding("converter", this._getValueBinding(this.armarExpressionEnBean("converterFecha")));
				} else if(cadaParametro.getTipo() == Tipo.NUMÉRICO) {
					tf.setValueBinding("converter", this._getValueBinding(this.armarExpressionEnBean("converterNumerico")));
				} else if(cadaParametro.getTipo() == Tipo.DECIMAL) {
					tf.setValueBinding("converter", this._getValueBinding(this.armarExpressionEnBean("converterDecimal")));
				}
			}
		}
		panelParametros.getChildren().add(panelGrid);
	}

	// metodo modificado obtenido del AplicationBean
	public Option[] armarArrayOptions(List<OpcionParametroReporte> lista) {
		Option[] o = new Option[lista.size() + 1];
		o[0] = new Option("", "");
		for(int i = 0; i < lista.size(); i++) {
			String display = lista.get(i).getNombre();
			Option opcion = new Option(display);
			o[i + 1] = opcion;
		}

		return o;
	}

	public Converter getConverterDecimal() {
		return new Converter() {

			@Override
			public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
				return arg2.toString();
			}

			@Override
			public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
				try {
					return Double.valueOf(arg2);
				} catch(NumberFormatException e) {
					return null;
				}
			}
		};
	}

	public Converter getConverterNumerico() {
		return new Converter() {

			@Override
			public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
				return arg2.toString();
			}

			@Override
			public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
				try {
					return Long.valueOf(arg2);
				} catch(NumberFormatException e) {
					return null;
				}
			}
		};
	}

	public Converter getConverterFecha() {
		return new Converter() {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			@Override
			public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
				return sdf.format(arg2);
			}

			@Override
			public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
				try {
					return sdf.parse(arg2);
				} catch(ParseException e) {
					return null;
				}
			}
		};
	}

	public Converter getConverterBooleano() {
		return new Converter() {

			@Override
			public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
				return arg2.toString();
			}

			@Override
			public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
				if(arg2 != null && arg2 == "") {
					return null;
				}

				return Boolean.valueOf(arg2);
			}
		};
	}

	private ValueBinding _getValueBinding(String pPropiedad) {
		return FacesContext.getCurrentInstance().getApplication().createValueBinding(pPropiedad);
	}

	private HtmlAjaxCommandButton getBtnLimpiarCampo(ParametroReporte parametro, String idTf) {
		HtmlAjaxCommandButton boton = new HtmlAjaxCommandButton();
		boton.setStyleClass("buttonLimpiarAjax");
		boton.addActionListener(this.getActionListener(this.armarExpressionEnBean("botonQuitarRecursoListener(javax.faces.event.ActionEvent)")));
		boton.getChildren().add(getUIParameter(parametro.getNombre()));
		// Borramos a mano porque el reRender no me da bola
		boton.setOncomplete("$('#form1\\\\:" + idTf + "').val('')");

		return boton;
	}

	private UIParameter getUIParameter(String pPropiedad) {
		UIParameter uiParameter = new UIParameter();
		uiParameter.setName("navegarAdminRecurso");
		uiParameter.setValue(pPropiedad);

		return uiParameter;
	}

	public Map<String, Object> getMapaParametros() {
		return this.getComunicationBean().getMapaParametrosReporte();
	}

	public void botonSeleccionRecursoListener(ActionEvent evento) {
		// Recuperamos el idParametro que nos llega como String, enviado por el UIParameter asociado al boton.
		String nombreAtributoParametro = getExternalContext().getRequestParameterMap().get("navegarAdminRecurso");
		Reporte reporte = (Reporte) obtenerObjetoDelElementoPila(0);
		ParametroReporte parametro = reporte.getParametroPorNombreAtributo(nombreAtributoParametro);

		// Obtenemos el JSP correspondiente desde Nav1
		String ruta = getLinkRecurso(parametro.getIdRecurso());
		ruta = ruta.replace("/faces", "");
		this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		// Recorremos las reglas de navegacion levantadas por JSF para recuperar la regla de navegacion asociada a nuestro jsp.
		String reglaNavegacion = null;
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
		try {
			Field field = navigationHandler.getClass().getDeclaredField("caseListMap");
			field.setAccessible(true);
			Map mapaNavegacion = (Map) field.get(navigationHandler);
			general: for(Object cadaRegla : mapaNavegacion.values()) {
				List<ConfigNavigationCase> cadaListaRegla = (List) cadaRegla;
				for(ConfigNavigationCase cadaConfig : cadaListaRegla) {
					if(cadaConfig.getToViewId().equals(ruta)) {
						reglaNavegacion = cadaConfig.getFromOutcome();
						break general;
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		// Guardamos el ID del parametro para recuperarlo a la vuelta del admin
		this.getElementoPila().getObjetos().set(1, nombreAtributoParametro);
		// Navegamos usando la regla de navegacion recuperada.
		navigationHandler.handleNavigation(context, null, reglaNavegacion);
	}

	public void botonQuitarRecursoListener(ActionEvent evento) {
		String nombreParametro = getExternalContext().getRequestParameterMap().get("navegarAdminRecurso");
		// Removemos el valor en el Mapa de parametros y reconstruimos
		this.getMapaParametros().remove(nombreParametro);
		Reporte reporte = (Reporte) obtenerObjetoDelElementoPila(0);
		armarPanelParametros(reporte);
	}

	private String getLinkRecurso(Long id) {
		for(Enlace cadaEnlace : getNav1().getLinksRecursos()) {
			if(cadaEnlace.getIdRecurso() == id) {
				return cadaEnlace.getLink();
			}
		}
		return null;
	}

	private Nav1 getNav1() {
		return (Nav1) getBean("Nav1");
	}

	private MethodExpressionActionListener getActionListener(String pValor) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELContext elContext = context.getELContext();
		ExpressionFactory elFactory = context.getApplication().getExpressionFactory();
		Class[] args = new Class[] {javax.faces.event.ActionEvent.class};
		MethodExpression methodExpression = elFactory.createMethodExpression(elContext, pValor, null, args);
		MethodExpressionActionListener listener = new MethodExpressionActionListener(methodExpression);

		return listener;
	}

	private String armarExpressionEnBean(String propiedad) {
		StringBuilder sb = new StringBuilder(getNombreBean());
		sb.insert(getNombreBean().length() - 1, "." + propiedad);

		return sb.toString();
	}

	public PanelGroup getPanelParametros() {
		return panelParametros;
	}

	public void setPanelParametros(PanelGroup panelParametros) {
	}

}