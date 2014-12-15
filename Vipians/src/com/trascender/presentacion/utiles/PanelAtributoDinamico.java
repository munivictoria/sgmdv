/*
 * PanelAtributoDinamico.java
 *
 * Created on 16 de Enero de 2012, 08:00
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.trascender.presentacion.utiles;

import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.event.ValueChangeEvent;

import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.ImageHyperlink;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.Upload;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.sun.rave.web.ui.model.UploadedFile;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamicoArchivo;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author Pedro
 */
public class PanelAtributoDinamico extends PanelGroup {

	private List<AtributoDinamico<?>> listaAtributosDinamicos;
	private HtmlPanelGrid panelGrid = new HtmlPanelGrid();
	private String nombreBean;
	private String id = "";
	private String nombreAtributo;

	public HtmlPanelGrid getPanelGrid() {
		return panelGrid;
	}

	public void setPanelGrid(HtmlPanelGrid panelGrid) {
		this.panelGrid = panelGrid;
	}

	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}

	public void setListaAtributosDinamicos(List<AtributoDinamico<?>> listaAtributosDinamicos) {
		this.listaAtributosDinamicos = listaAtributosDinamicos;
	}

	public PanelAtributoDinamico() {
		super();
	}

	public PanelAtributoDinamico(List<AtributoDinamico<?>> listaAtributosDinamicos, String pNombreBean) {
		super();
		setListaAtributosDinamicos(listaAtributosDinamicos);
		this.nombreBean = pNombreBean;
		init();
	}

	public PanelAtributoDinamico(List<AtributoDinamico<?>> listaAtributosDinamicos, String pNombreBean, String pId) {
		super();
		setListaAtributosDinamicos(listaAtributosDinamicos);
		this.nombreBean = pNombreBean;
		id = pId;
		init();
	}

	public List<AtributoDinamico<?>> obtenerListaAtributosDinamicos(List<AtributoDinamico<?>> listaAtributosDinamicos) {
		setListaAtributosDinamicos(null);
		setListaAtributosDinamicos(listaAtributosDinamicos);
		try {
			int indice = 0;
			setPanelGrid((HtmlPanelGrid) this.getChildren().get(0));
			for(Object object : getPanelGrid().getChildren()) {
				if(object instanceof TextArea) {
					TextArea textA = (TextArea) object;
					if(!textA.getText().toString().isEmpty()) {
						getListaAtributosDinamicos().get(indice).setValorString(textA.getText().toString());
						// ((AtributoDinamico)
						// object).setValorString(textA.getText().toString());
					} else {
						getListaAtributosDinamicos().get(indice).setValorString(null);
						// ((AtributoDinamico) object).setValorString(null);
					}
					indice++;
				} else if(object instanceof TextField) {
					TextField text = (TextField) object;
					if(!text.getText().toString().isEmpty()) {
						((AtributoDinamico) getListaAtributosDinamicos().get(indice)).setValorString(text.getText().toString());
						// ((AtributoDinamico)
						// object).setValorString(text.getText().toString());
					} else {
						((AtributoDinamico) getListaAtributosDinamicos().get(indice)).setValorString(null);
						// ((AtributoDinamico) object).setValorString(null);
					}
					indice++;
				} else if(object instanceof DropDown) {
					DropDown drop = (DropDown) object;
					if(!drop.getSelected().toString().isEmpty()) {
						((AtributoDinamico) getListaAtributosDinamicos().get(indice)).setValorString(drop.getSelected().toString());
						// ((AtributoDinamico)
						// object).setValorString(drop.getSelected().toString());
					} else {
						((AtributoDinamico) getListaAtributosDinamicos().get(indice)).setValorString(null);
						// ((AtributoDinamico) object).setValorString(null);
					}
					indice++;
				} else if(object instanceof PanelGroup) {
					PanelGroup auxPanel1 = (PanelGroup) object;
					Upload locUpload = null;
					AtributoDinamicoArchivo locAtributo = null;

					// PanelGroup auxGrid = this.getPrimerAparicionHijo(auxPanel1, PanelGroup.class);
					HtmlPanelGrid auxGrid = this.getPrimerAparicionHijo(auxPanel1, HtmlPanelGrid.class);

					TextField tex = this.getPrimerAparicionHijo(auxGrid, TextField.class);

					if(tex.getToolTip().equals("Nombre Archivo")) {
						if(!tex.getText().toString().equals("")) {
							PanelGroup ob = (PanelGroup) tex.getParent().getParent();

							locUpload = this.getPrimerAparicionHijo(ob, Upload.class);

							locAtributo = (AtributoDinamicoArchivo) getListaAtributosDinamicos().get(indice);
							if(locUpload.getUploadedFile().getBytes() != null && locUpload.getUploadedFile().getBytes().length > 0) {
								locAtributo.setNombreArchivo(locUpload.getUploadedFile().getOriginalName());
								locAtributo.setValor(locUpload.getUploadedFile().getBytes());
							}

						} else {
							locAtributo = (AtributoDinamicoArchivo) getListaAtributosDinamicos().get(indice);
							System.out.println(locAtributo);
							locAtributo.setValor(null);
							locAtributo.setNombreArchivo(null);
						}
					}
					indice++;
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		return getListaAtributosDinamicos();
	}

	private <T> T getPrimerAparicionHijo(UIComponent pComponentePadre, Class<T> pClaseComponenteHijo) {
		UIComponent resultado = null;
		for(UIComponent cadaHijo : pComponentePadre.getChildren()) {
			if(pClaseComponenteHijo.isInstance(cadaHijo)) {
				resultado = cadaHijo;
			}
		}
		return (T) resultado;
	}

	public void establecerListaAtributosDinamicos(List<AtributoDinamico<?>> listaAtributosDinamicos) {
		setPanelGrid((HtmlPanelGrid) this.getChildren().get(0));
		// int indicePanel = 1;
		int indiceLista = 0;

		for(Object object : getPanelGrid().getChildren()) {
			if(object instanceof TextArea) {
				AtributoDinamico atributo = listaAtributosDinamicos.get(indiceLista++);
				if(atributo.getValorString() != null) {
					((TextArea) object).setText(atributo.getValorString());
				} else {
					((TextArea) object).setText("");
				}
			} else if(object instanceof TextField) {
				AtributoDinamico atributo = listaAtributosDinamicos.get(indiceLista++);
				if(atributo.getValorString() != null) {
					((TextField) object).setText(atributo.getValorString());
				} else {
					((TextField) object).setText("");
				}
			} else if(object instanceof DropDown) {
				AtributoDinamico atributo = listaAtributosDinamicos.get(indiceLista++);
				if(atributo.getValorString() != null) {
					((DropDown) object).setSelected(atributo.getValorString());
				} else {
					((DropDown) object).setSelected("");
				}
			} else if(object instanceof PanelGroup) {
				AtributoDinamicoArchivo atributo = (AtributoDinamicoArchivo) listaAtributosDinamicos.get(indiceLista++);
				PanelGroup ob = (PanelGroup) object;

				for(Object aux : ob.getChildren()) {
					if(aux instanceof TextField) {
						TextField texto = (TextField) aux;
						texto.setText(atributo.getNombreArchivo());
					}
				}

			}
		}
		this.getChildren().add(0, getPanelGrid());
	}

	public void deshabilitarCampos() {
		setPanelGrid((HtmlPanelGrid) this.getChildren().get(0));
		for(Object object : getPanelGrid().getChildren()) {
			if(object instanceof TextField) {
				TextField tf = (TextField) object;
				tf.setDisabled(true);
				tf.setStyle("textFieldDisabled");
			} else if(object instanceof TextArea) {
				TextArea tArea = (TextArea) object;
				tArea.setDisabled(true);
				tArea.setStyleClass("textFieldDisabled");
			} else if(object instanceof DropDown) {
				DropDown dd = (DropDown) object;
				dd.setDisabled(true);
				dd.setStyleClass("textFieldDisabled");
			} else if(object instanceof PanelGroup) {
				PanelGroup pg1 = (PanelGroup) object;
				HtmlPanelGrid hpg = (HtmlPanelGrid) pg1.getChildren().get(0);
				PanelGroup pg2 = (PanelGroup) hpg.getChildren().get(1);
				pg2.getChildren().get(0).setRendered(false);
				pg2.getChildren().get(1).setRendered(false);
				if(((TextField) hpg.getChildren().get(0)).getText() == "") {
					pg2.getChildren().get(2).setRendered(false);
				}
			}
		}
		this.getChildren().add(0, getPanelGrid());
	}

	public void limpiarCampos() {
		setPanelGrid((HtmlPanelGrid) this.getChildren().get(0));
		for(Object object : getPanelGrid().getChildren()) {
			if(object instanceof TextField) {
				TextField tf = (TextField) object;
				tf.setText("");
			} else if(object instanceof TextArea) {
				TextArea ta = (TextArea) object;
				ta.setText("");
			} else if(object instanceof DropDown) {
				DropDown dd = (DropDown) object;
				dd.setSelected("");
			} else if(object instanceof DropDown) {
				DropDown dd = (DropDown) object;
				dd.setSelected("");
			}
		}
		this.getChildren().add(0, getPanelGrid());
	}

	public Validador validarCampos(List listaAtributosDinamicos) {
		System.out.println("*******************TAMAÑO LISTA ATRIBUTOS " + listaAtributosDinamicos.size());
		setPanelGrid((HtmlPanelGrid) this.getChildren().get(0));
		Validador v = new Validador();
		ArrayList<Integer> indicesCamposFecha = new ArrayList();

		if(listaAtributosDinamicos != null) {
			int ind = 0;
			for(Object object : listaAtributosDinamicos) {
				AtributoDinamico atributo = (AtributoDinamico) object;
				if(atributo.getPlantilla().getTipo() == PlantillaAtributoDinamico.Tipo.FECHA) {
					indicesCamposFecha.add(ind);
				}
				ind++;
			}
		}
		System.out.println("indice CAMPOS FECHAS " + indicesCamposFecha.size() );
		int tamanio = indicesCamposFecha.size();
		System.out.println("---CANTIDAD DE CAMPOS FECHA: " + tamanio);

		UIComponent[] fechas = new UIComponent[tamanio];
		String[] nomFechas = new String[tamanio];

		if(indicesCamposFecha != null && !indicesCamposFecha.isEmpty()) {
			int pos = 0;
			for(Integer indice : indicesCamposFecha) {
				System.out.println("---INDICE: " + indice);
				fechas[pos] = getPanelGrid().getChildren().get((indice * 2) + 1);
				nomFechas[pos++] = ((AtributoDinamico) listaAtributosDinamicos.get(indice)).getNombre();
			}
		}

		ArrayList<Integer> indicesRequeridos = new ArrayList<Integer>();
		if(listaAtributosDinamicos != null && !listaAtributosDinamicos.isEmpty()) {
			int indice = 0;
			for(Object object : listaAtributosDinamicos) {
				if(((AtributoDinamico) object).getPlantilla().isRequerido()) {
					indicesRequeridos.add(indice);
				}
				indice++;
			}
		}

		UIComponent[] requeridos = new UIComponent[indicesRequeridos.size()];
		String[] nomRequeridos = new String[indicesRequeridos.size()];
		int posi = 0;
		for(Integer ind : indicesRequeridos) {
			requeridos[posi] = getPanelGrid().getChildren().get((ind * 2) + 1);

			System.out.println("este tipo es la cosa: " + requeridos[posi].getClass());
			if(requeridos[posi] instanceof PanelGroup) {
				System.out.println("**** es un panel group ****");
				requeridos[posi] = ((PanelGroup) requeridos[posi]).getChildren().get(0);
				System.out.println("y ahora es de esta clase " + requeridos[posi]);
				requeridos[posi] = ((PanelGroup) requeridos[posi]).getChildren().get(0);
				System.out.println("y ahora es de esta clase " + requeridos[posi]);
			}

			nomRequeridos[posi] = ((AtributoDinamico) listaAtributosDinamicos.get(ind)).getNombre();
			posi = posi + 1;
		}
		v.noSonVacios(requeridos, nomRequeridos);
		v.formatoFechaValido(fechas, nomFechas);

		return v;
	}

	private void init() {
		panelGrid = new HtmlPanelGrid();
		UploadedFile uploadedFile = null;
		getPanelGrid().setColumns(4);
		getPanelGrid().setStyleClass("panelAtributoDinamicoColumnaIzquierda panelAtributoDinamicoColumnaDerecha");
		getPanelGrid().setStyle("overflow: auto; white-space:nowrap;");
		System.out.println("init-------------");
		int ind = 0;

		if(id != "") {
			nombreAtributo = this.id;
		} else {
			nombreAtributo = "";
		}

		// si no hay atributos dinamicos debe mostrar un cartelito
		if(getListaAtributosDinamicos() != null && !getListaAtributosDinamicos().isEmpty()) {

			for(Object object : getListaAtributosDinamicos()) {
				AtributoDinamico atributo = (AtributoDinamico) object;
				Label labelAtributo = new Label();
				labelAtributo.setText(atributo.getNombre());
				labelAtributo.setId("labelAtributo" + nombreAtributo + ind);
				labelAtributo.setStyleClass("label");
				labelAtributo.setStyle("float:left; clear:both;margin-top:4px;width:20%;");

				getPanelGrid().getChildren().add(labelAtributo);

				if(atributo.getPlantilla().getTipo() == PlantillaAtributoDinamico.Tipo.CADENA) {
					TextArea textAreaValor = new TextArea();
					textAreaValor.setStyle("textField");
					textAreaValor.setStyle("float:left;");
					textAreaValor.setId("textAreaValor" + nombreAtributo + ind);
					textAreaValor.setText(atributo.getNombre());
					getPanelGrid().getChildren().add(textAreaValor);
				} else if(atributo.getPlantilla().getTipo() == PlantillaAtributoDinamico.Tipo.ENTERO || atributo.getPlantilla().getTipo() == PlantillaAtributoDinamico.Tipo.DECIMAL
						|| atributo.getPlantilla().getTipo() == PlantillaAtributoDinamico.Tipo.FECHA) {
					TextField textFieldValor = new TextField();
					textFieldValor.setStyleClass("textField");
					textFieldValor.setStyle("float:left;");
					textFieldValor.setId("textFieldValor" + nombreAtributo + ind);
					if(atributo.getPlantilla().getTipo() == PlantillaAtributoDinamico.Tipo.ENTERO) {
						textFieldValor.setOnKeyPress("return ValidarNum(event,this)");
					}
					if(atributo.getPlantilla().getTipo() == PlantillaAtributoDinamico.Tipo.DECIMAL) {
						textFieldValor.setOnKeyPress("return ValidarFloat(event,this)");
					}
					if(atributo.getPlantilla().getTipo() == PlantillaAtributoDinamico.Tipo.FECHA) {
						textFieldValor.setId("textFieldFechaDinamica" + nombreAtributo + ind);
						textFieldValor.setColumns(10);
						// textFieldValor.setOnKeyUp("mascara(this,'/',patronFecha,true)");
						// textFieldValor.setMaxLength(10);
					}
					getPanelGrid().getChildren().add(textFieldValor);
				} else if(atributo.getPlantilla().getTipo() == PlantillaAtributoDinamico.Tipo.BOOLEANO) {

					DropDown dropDownBulean = new DropDown();
					dropDownBulean.setId("dropDownBulean" + nombreAtributo + ind);
					dropDownBulean.setStyle("float:left");
					getPanelGrid().getChildren().add(dropDownBulean);
					Option[] op = null;
					op = armarArrayOptions(new String[] {"Si", "No"});
					SingleSelectOptionsList dropDownList = new SingleSelectOptionsList();
					dropDownList.setOptions(op);
					dropDownBulean.setItems(dropDownList.getOptions());
					getPanelGrid().getChildren().add(dropDownBulean);

				} else if(atributo.getPlantilla().getTipo() == PlantillaAtributoDinamico.Tipo.LISTADO) {
					DropDown dropDownValor = new DropDown();
					dropDownValor.setId("dropDownValor" + nombreAtributo + ind);
					dropDownValor.setStyle("float:left;");

					// lista de opciones
					SingleSelectOptionsList dropDownList = new SingleSelectOptionsList();
					Option[] op = null;
					op = armarArrayOptions(atributo.getPlantilla().getListaOpciones().toArray());
					dropDownList.setOptions(op);
					dropDownValor.setItems(dropDownList.getOptions());
					getPanelGrid().getChildren().add(dropDownValor);

				} else if(atributo.getPlantilla().getTipo() == PlantillaAtributoDinamico.Tipo.ARCHIVO) {
					AtributoDinamicoArchivo atributoArchivo = (AtributoDinamicoArchivo) atributo;
					Upload upLocal = this.getUploadLocal(ind);
					TextField tfNombreArchivo = this.getTfNombreArchivo(ind);
					Label lbImagenAgregar = this.getLbImagenAgregar(ind);
					Label lbImagenLimpiar = this.getLbImagenLimpiar(ind);
					ImageHyperlink hlImagenBajar = this.getHlImagenBajar(ind);
					PanelGroup panelAux = new PanelGroup();
					HtmlPanelGrid panelAux2 = new HtmlPanelGrid();
					PanelGroup panelAux3 = new PanelGroup();

					panelAux.setId("panelAux1-" + nombreAtributo + ind);
					panelAux2.setId("panelAux2-" + nombreAtributo + ind);
					panelAux3.setId("panelAux3-" + nombreAtributo + ind);

					panelAux2.setColumns(2);

					panelAux.setBlock(true);
					// panelAux2.setBlock(true);

					panelAux2.setStyle("padding:0px; float:left; overflow: auto;");// width:120px;");
					panelAux.setStyle("float:left;");

					System.out.println("DATOS DEL ATRIBUTO: " + atributoArchivo);
					if(atributoArchivo.getNombreArchivo() == null || atributoArchivo.getNombreArchivo().isEmpty()) {
						tfNombreArchivo.setText("");
					} else {
						tfNombreArchivo.setText(atributoArchivo.getNombreArchivo());
					}
					tfNombreArchivo.setOnKeyPress("return false;");
					tfNombreArchivo.setStyleClass("textFieldDisabled");
					panelAux2.getChildren().add(tfNombreArchivo);
					panelAux3.getChildren().add(lbImagenAgregar);
					panelAux3.getChildren().add(lbImagenLimpiar);
					hlImagenBajar.getChildren().add(this.getUIParameter(atributo.getNombre(), ind));
					panelAux3.getChildren().add(hlImagenBajar);

					panelAux2.getChildren().add(panelAux3);

					panelAux.getChildren().add(panelAux2);
					upLocal.setUploadedFile(uploadedFile);
					panelAux.getChildren().add(upLocal);
					getPanelGrid().getChildren().add(panelAux);
				}
				ind++;
			}
		} else {
			Label mensaje = new Label(); // muestra msj indicando que no hay
			// atributos dinamicos
			mensaje.setText("No se han encontrado atributos din\341micos.");
			mensaje.setId("mensaje1" + nombreAtributo);
			mensaje.setStyleClass("label");
			getPanelGrid().getChildren().add(mensaje);
		}
		this.getChildren().add(getPanelGrid());
	}

	// metodo modificado obtenido del AplicationBean
	public Option[] armarArrayOptions(Object[] objetos) {
		Option[] o = new Option[objetos.length + 1];
		o[0] = new Option("", "");
		for(int i = 0; i < objetos.length; i++) {
			String display = objetos[i].toString();
			Option opcion = new Option(display);
			o[i + 1] = opcion;
		}
		return o;
	}

	public void fileChangeListener(ValueChangeEvent event) {
		Object value = event.getNewValue();
		if(value != null && value instanceof UploadedFile) {
			UploadedFile locUploadedFile = (UploadedFile) event;
			System.out.println("Se cargo archivo");
			System.out.println(locUploadedFile.getAsString());
		}
	}

	private String armarExpressionEnBean(String propiedad) {
		StringBuilder sb = new StringBuilder(nombreBean);
		sb.insert(nombreBean.length() - 1, "." + propiedad);
		System.out.println("EXPRESSION EN BEAN: " + sb.toString());
		return sb.toString();
	}

	private MethodExpressionActionListener getActionListener(String pValor) {
		Class[] args = new Class[] {javax.faces.event.ActionEvent.class};
		FacesContext context = FacesContext.getCurrentInstance();
		ExpressionFactory elFactory = context.getApplication().getExpressionFactory();
		ELContext elContext = context.getELContext();
		MethodExpression methodExpression = elFactory.createMethodExpression(elContext, pValor, null, args);
		MethodExpressionActionListener listener = new MethodExpressionActionListener(methodExpression);
		return listener;
	}
	
	private MethodExpression getMethodBiding(String pValor) {
		Class[] args = new Class[] {javax.faces.event.ActionEvent.class};
		FacesContext context = FacesContext.getCurrentInstance();
		ExpressionFactory elFactory = context.getApplication().getExpressionFactory();
		ELContext elContext = context.getELContext();
		MethodExpression methodExpression = elFactory.createMethodExpression(elContext, pValor, null, args);
		System.out.println("METHOD EXPRESSION: " + methodExpression);
		return methodExpression;
	}

	private UIParameter getUIParameter(String pPropiedad, int indice) {
		UIParameter uiParameter = new UIParameter();
		uiParameter.setName("atributoDinamicoSeleccionado");
		uiParameter.setValue(pPropiedad);
		uiParameter.setId("uiParameter-" + nombreAtributo + indice);
		return uiParameter;
	}

	private TextField getTfNombreArchivo(int indice) {
		TextField tfNombreArchivo = new TextField();

		tfNombreArchivo.setToolTip("Nombre Archivo");
		tfNombreArchivo.setStyleClass("label subidora");
		tfNombreArchivo.setId("archivo-" + nombreAtributo + indice);
		tfNombreArchivo.setStyle("padding-left:0pt !important;");

		return tfNombreArchivo;
	}

	private Label getLbImagenAgregar(int ind) {
		Label lbImagenAgregar = new Label();

		lbImagenAgregar.setOnClick("procesarDatosLabelUpload(this)");
		lbImagenAgregar.setId("btnAgregarArchivo-" + nombreAtributo + ind);
		lbImagenAgregar.setStyleClass("buttonAgregar");
		lbImagenAgregar.setStyle("height:15px; padding-top: 2px");
		lbImagenAgregar.setText(" ");
		lbImagenAgregar.setToolTip("Agregar Archivo");

		return lbImagenAgregar;
	}

	private Label getLbImagenLimpiar(int ind) {
		Label lbImagenLimpiar = new Label();

		lbImagenLimpiar.setId("btnEliminarArchivo-" + nombreAtributo + ind);
		lbImagenLimpiar.setOnClick("borrarArchivo(this)");
		lbImagenLimpiar
				.setStyle("font-weight: bold; font-size: 10px; font-family: Verdana, Geneva, Arial, Helvetica, sans-serif; background-color: #EEE; background-image: url(/Vipians/resources/imagenes/botones/eraser13.png) ! important; padding: 2px 9px; cursor: pointer; background-repeat: no-repeat;");
		lbImagenLimpiar.setText(" ");
		lbImagenLimpiar.setToolTip("Eliminar Archivo");
		return lbImagenLimpiar;
	}

	private ImageHyperlink getHlImagenBajar(int ind) {
		ImageHyperlink hlImagenBajar = new ImageHyperlink();
		hlImagenBajar.setId("btnDescargarArchivo-" + nombreAtributo + ind);
		hlImagenBajar
				.setStyle("font-weight: bold; font-size: 10px; font-family: Verdana, Geneva, Arial, Helvetica, sans-serif; background-color: #EEE; background-image: url(/Vipians/resources/imagenes/botones/download-icon_small.png) ! important; padding: 2px 8px; cursor: pointer; background-repeat: no-repeat;");
		hlImagenBajar.setText(" ");
		hlImagenBajar.setToolTip("Descargar Archivo");
		hlImagenBajar.setOnClick("descargarArchivoDinamico(null, this)");
		hlImagenBajar.addActionListener(this.getActionListener(this.armarExpressionEnBean("procesarDescargarAtributoDinamico(javax.faces.event.ActionEvent)")));
		return hlImagenBajar;
	}

	private Upload getUploadLocal(int ind) {
		Upload upLocal = new Upload();

		upLocal.setId("upload-" + nombreAtributo + ind);
		upLocal.setStyleClass("accionSubir");
		upLocal.setStyle("display:none;");
		return upLocal;
	}
}