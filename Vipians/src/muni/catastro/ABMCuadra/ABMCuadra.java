/*
 * ABMCuadra.java
 *
 * Created on 31 de octubre de 2006, 08:26
 * Copyright Trascender
 */
package muni.catastro.ABMCuadra;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaCuadra;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMCuadra extends ABMPageBean {

	@Override
	protected void _init() throws Exception {

		Set<String> locListaCalles = this.getComunicationCatastroBean().getMapaCalles().keySet();
		Option[] opCalles = new Option[locListaCalles.size() + 1];
		int i = 0;
		opCalles[i++] = new Option("", "");
		for (String cadaCalle : locListaCalles) {
			opCalles[i++] = new Option(cadaCalle, cadaCalle);
		}
		this.ddCalleOptions.setOptions(opCalles);
		this.ddCalleComienzaOptions.setOptions(opCalles);
		this.ddCalleFinalizaOptions.setOptions(opCalles);

		ddTipoNumeracionDefaultOptions.setOptions(new com.sun.rave.web.ui.model.Option[] { new com.sun.rave.web.ui.model.Option("P", "Par"),
				new com.sun.rave.web.ui.model.Option("I", "Impar") });

		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private ObjectListDataProvider ldpZonas = new ObjectListDataProvider();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public ObjectListDataProvider getLdpZonas() {
		return ldpZonas;
	}

	public void setLdpZonas(ObjectListDataProvider ldpZonas) {
		this.ldpZonas = ldpZonas;
	}

	private Object lastSelected1 = null;

	public Object getRBSelected1() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected1) ? sv : null;
	}

	public void setRBSelected1(Object selected) {
		if (selected != null) {
			lastSelected1 = selected;
		}
	}

	public String getCurrentRow1() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow1(int row) {
	}

	private Button btnAgregarZona = new Button();
	 protected HtmlAjaxCommandButton btnQuitarZona = new HtmlAjaxCommandButton();

	public Button getBtnAgregarZona() {
		return btnAgregarZona;
	}

	public void setBtnAgregarZona(Button btnAgregarZona) {
		this.btnAgregarZona = btnAgregarZona;
	}

	public HtmlAjaxCommandButton getBtnQuitarZona() {
		return btnQuitarZona;
	}

	public void setBtnQuitarZona(HtmlAjaxCommandButton btnQuitarZona) {
		this.btnQuitarZona = btnQuitarZona;
	}

	private PanelGroup groupPanel1 = new PanelGroup();
	private StaticText staticText1 = new StaticText();
	private StaticText staticText2 = new StaticText();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tableColumn2 = new TableColumn();
	private TableColumn tableColumn3 = new TableColumn();
	private RadioButton radioButton1 = new RadioButton();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText staticText2) {
		this.staticText2 = staticText2;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tableColumn3) {
		this.tableColumn3 = tableColumn3;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	private Label label15 = new Label();
	private Label label13 = new Label();

	public Label getLabel13() {
		return label13;
	}

	public void setLabel13(Label label13) {
		this.label13 = label13;
	}

	private Label label19 = new Label();

	public Label getLabel19() {
		return label19;
	}

	public void setLabel19(Label label19) {
		this.label19 = label19;
	}

	public Label getLabel15() {
		return label15;
	}

	public void setLabel15(Label l) {
		this.label15 = l;
	}

	private Label label17 = new Label();

	public Label getLabel17() {
		return label17;
	}

	public void setLabel17(Label l) {
		this.label17 = l;
	}

	private Label label18 = new Label();

	public Label getLabel18() {
		return label18;
	}

	public void setLabel18(Label l) {
		this.label18 = l;
	}

	private TextField tfNumeracionDesde = new TextField();

	public TextField getTfNumeracionDesde() {
		return tfNumeracionDesde;
	}

	public void setTfNumeracionDesde(TextField tf) {
		this.tfNumeracionDesde = tf;
	}

	private TextField tfCodigoPostalPar = new TextField();

	public TextField getTfCodigoPostalPar() {
		return tfCodigoPostalPar;
	}

	public void setTfCodigoPostalPar(TextField tf) {
		this.tfCodigoPostalPar = tf;
	}

	private Label label20 = new Label();

	public Label getLabel20() {
		return label20;
	}

	public void setLabel20(Label l) {
		this.label20 = l;
	}

	private Label label22 = new Label();

	public Label getLabel22() {
		return label22;
	}

	public void setLabel22(Label l) {
		this.label22 = l;
	}

	private TextField tfMetrosLineales = new TextField();

	public TextField getTfMetrosLineales() {
		return tfMetrosLineales;
	}

	public void setTfMetrosLineales(TextField tf) {
		this.tfMetrosLineales = tf;
	}

	private SingleSelectOptionsList ddCalleFinalizaOptions = new SingleSelectOptionsList();
	private DropDown ddCalleFinaliza = new DropDown();

	public DropDown getDdCalleFinaliza() {
		return ddCalleFinaliza;
	}

	public void setDdCalleFinaliza(DropDown ddCalleFinaliza) {
		this.ddCalleFinaliza = ddCalleFinaliza;
	}

	private Label label24 = new Label();

	public Label getLabel24() {
		return label24;
	}

	public void setLabel24(Label l) {
		this.label24 = l;
	}

	private SingleSelectOptionsList ddCalleComienzaOptions = new SingleSelectOptionsList();
	private DropDown ddCalleComienza = new DropDown();

	public DropDown getDdCalleComienza() {
		return ddCalleComienza;
	}

	public void setDdCalleComienza(DropDown ddCalleComienza) {
		this.ddCalleComienza = ddCalleComienza;
	}

	public SingleSelectOptionsList getDdCalleComienzaOptions() {
		return ddCalleComienzaOptions;
	}

	public void setDdCalleComienzaOptions(SingleSelectOptionsList ddCalleComienzaOptions) {
		this.ddCalleComienzaOptions = ddCalleComienzaOptions;
	}

	public SingleSelectOptionsList getDdCalleFinalizaOptions() {
		return ddCalleFinalizaOptions;
	}

	public void setDdCalleFinalizaOptions(SingleSelectOptionsList ddCalleFinalizaOptions) {
		this.ddCalleFinalizaOptions = ddCalleFinalizaOptions;
	}

	public SingleSelectOptionsList getDdCalleOptions() {
		return ddCalleOptions;
	}

	public void setDdCalleOptions(SingleSelectOptionsList ddCalleOptions) {
		this.ddCalleOptions = ddCalleOptions;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private SingleSelectOptionsList ddCalleOptions = new SingleSelectOptionsList();
	private DropDown ddCalle = new DropDown();

	public DropDown getDdCalle() {
		return ddCalle;
	}

	public void setDdCalle(DropDown ddCalle) {
		this.ddCalle = ddCalle;
	}

	private Button btnSeleccionarCalle = new Button();

	public Button getBtnSeleccionarCalle() {
		return btnSeleccionarCalle;
	}

	public void setBtnSeleccionarCalle(Button b) {
		this.btnSeleccionarCalle = b;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private TextField tfNumeracionHasta = new TextField();

	public TextField getTfNumeracionHasta() {
		return tfNumeracionHasta;
	}

	public void setTfNumeracionHasta(TextField tf) {
		this.tfNumeracionHasta = tf;
	}

	private DropDown ddTipoNumeracion = new DropDown();

	public DropDown getDdTipoNumeracion() {
		return ddTipoNumeracion;
	}

	public void setDdTipoNumeracion(DropDown dd) {
		this.ddTipoNumeracion = dd;
	}

	private SingleSelectOptionsList ddTipoNumeracionDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdTipoNumeracionDefaultOptions() {
		return ddTipoNumeracionDefaultOptions;
	}

	public void setDdTipoNumeracionDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddTipoNumeracionDefaultOptions = ssol;
	}

	private Button btnSeleccionarCalleComienza = new Button();

	public Button getBtnSeleccionarCalleComienza() {
		return btnSeleccionarCalleComienza;
	}

	public void setBtnSeleccionarCalleComienza(Button b) {
		this.btnSeleccionarCalleComienza = b;
	}

	private Button btnSeleccionarCalleFinaliza = new Button();

	public Button getBtnSeleccionarCalleFinaliza() {
		return btnSeleccionarCalleFinaliza;
	}

	public void setBtnSeleccionarCalleFinaliza(Button b) {
		this.btnSeleccionarCalleFinaliza = b;
	}

	private TextField tfCodigoPostalImpar = new TextField();

	public TextField getTfCodigoPostalImpar() {
		return tfCodigoPostalImpar;
	}

	public void setTfCodigoPostalImpar(TextField tf) {
		this.tfCodigoPostalImpar = tf;
	}

	private StaticText stSeparador1 = new StaticText();

	public StaticText getStSeparador1() {
		return stSeparador1;
	}

	public void setStSeparador1(StaticText st) {
		this.stSeparador1 = st;
	}

	private Label label2 = new Label();
	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label label4) {
		this.label4 = label4;
	}

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

	private HtmlAjaxCommandButton btnLimpiarCalle = new HtmlAjaxCommandButton();

	private HtmlAjaxCommandButton btnLimpiarCalleComienza = new HtmlAjaxCommandButton();

	private HtmlAjaxCommandButton btnLimpiarCalleFinaliza = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarCalle() {
		return btnLimpiarCalle;
	}

	public void setBtnLimpiarCalle(HtmlAjaxCommandButton btnLimpiarCalle) {
		this.btnLimpiarCalle = btnLimpiarCalle;
	}

	public HtmlAjaxCommandButton getBtnLimpiarCalleComienza() {
		return btnLimpiarCalleComienza;
	}

	public void setBtnLimpiarCalleComienza(HtmlAjaxCommandButton btnLimpiarCalleComienza) {
		this.btnLimpiarCalleComienza = btnLimpiarCalleComienza;
	}

	public HtmlAjaxCommandButton getBtnLimpiarCalleFinaliza() {
		return btnLimpiarCalleFinaliza;
	}

	public void setBtnLimpiarCalleFinaliza(HtmlAjaxCommandButton btnLimpiarCalleFinaliza) {
		this.btnLimpiarCalleFinaliza = btnLimpiarCalleFinaliza;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMCuadra() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Cuadra());
		ep.getObjetos().add(ind++, new Calle());// Calle
		ep.getObjetos().add(ind++, new Calle());// Calle Comienza
		ep.getObjetos().add(ind++, new Calle());// Calle Finaliza
		ep.getObjetos().add(ind++, new ArrayList()); // AtributosDinamicos

		// CAMBIAR: guardar aca la posicion en el ElementoPila del objeto que se
		// est� seleccionando
		// cuando se puede seleccionar mas de un objeto de la misma clase en la
		// misma pagina
		ep.getObjetos().add(ind++, new Integer(0));

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		Cuadra cuadra = (Cuadra) this.obtenerObjetoDelElementoPila(ind++, Cuadra.class);
		Calle calle = (Calle) this.obtenerObjetoDelElementoPila(ind++, Calle.class);
		Calle calleComienza = (Calle) this.obtenerObjetoDelElementoPila(ind++, Calle.class);
		Calle calleFinaliza = (Calle) this.obtenerObjetoDelElementoPila(ind++, Calle.class);
		ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		// Object codigoPostalPar = this.getTfCodigoPostalPar().getText();
		// Object codigoPostalImpar = this.getTfCodigoPostalImpar().getText();

		Object tipoNumeracion = this.getDdTipoNumeracion().getSelected();
		if ((tipoNumeracion != null) && (tipoNumeracion.toString().length() > 0)) {
			cuadra.setTipoNumeracion(new Character(tipoNumeracion.toString().charAt(0)));
		} else {
			cuadra.setTipoNumeracion(null);
		}

		cuadra.setNumeracionDesde(this.getTextFieldValueInteger(getTfNumeracionDesde()));
		cuadra.setNumeracionHasta(this.getTextFieldValueInteger(getTfNumeracionHasta()));
		cuadra.setMetrosLineales(this.getTextFieldValueDouble(getTfMetrosLineales()));
		cuadra.setCalle(this.getDDObjectValue(getDdCalle(), getComunicationCatastroBean().getMapaCalles()));
		cuadra.setCalleComienza(this.getDDObjectValue(getDdCalleComienza(), getComunicationCatastroBean().getMapaCalles()));
		cuadra.setCalleFinaliza(this.getDDObjectValue(getDdCalleFinaliza(), getComunicationCatastroBean().getMapaCalles()));
		

		if (this.getObjectListDataProvider().getList() != null && this.getObjectListDataProvider().getList().size() > 0) {
			this.getObjectListDataProvider().commitChanges();
		}
		List listaZonas = (List) this.getObjectListDataProvider().getList();

		if (listaZonas.size() <= 0) {
			System.out.println("-------- lista zonas null o vacio");
		}
		cuadra.setListaAsociacionParcelaCuadra(listaZonas);
		this.setListaDelCommunication(listaZonas);

		atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		cuadra.setListaAtributosDinamicos(atributosDinamicos);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, cuadra);
		this.getElementoPila().getObjetos().set(ind++, cuadra.getCalle());
		this.getElementoPila().getObjetos().set(ind++, cuadra.getCalleComienza());
		this.getElementoPila().getObjetos().set(ind++, cuadra.getCalleFinaliza());
		this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		Cuadra cuadra = (Cuadra) this.obtenerObjetoDelElementoPila(ind++, Cuadra.class);
		Calle calle = (Calle) this.obtenerObjetoDelElementoPila(ind++, Calle.class);
		Calle calleComienza = (Calle) this.obtenerObjetoDelElementoPila(ind++, Calle.class);
		Calle calleFinaliza = (Calle) this.obtenerObjetoDelElementoPila(ind++, Calle.class);
		ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{catastro$ABMCuadra$ABMCuadra}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);

		if (calle != null && calle.getIdCalle() != -1) {
			this.getDdCalle().setSelected(calle.toString());
		}

		if (calleComienza != null && calleComienza.getIdCalle() != -1) {
			this.getDdCalleComienza().setSelected(calleComienza.toString());
		}

		if (calleFinaliza != null && calleFinaliza.getIdCalle() != -1) {
			this.getDdCalleFinaliza().setSelected(calleFinaliza.toString());
		}

		this.getDdTipoNumeracion().setSelected(String.valueOf(cuadra.getTipoNumeracion()));
		this.getDdTipoNumeracionDefaultOptions().setSelectedValue(String.valueOf(cuadra.getTipoNumeracion()));
		if (cuadra.getNumeracionDesde() != null) {
			this.getTfNumeracionDesde().setText(cuadra.getNumeracionDesde().toString());
		}
		if (cuadra.getNumeracionHasta() != null) {
			this.getTfNumeracionHasta().setText(cuadra.getNumeracionHasta().toString());
		}
		if (cuadra.getMetrosLineales() != null) {
			this.getTfMetrosLineales().setText(Conversor.getStringDeDouble(cuadra.getMetrosLineales()));
		}

		// if (cuadra.getCodPostal() != null) {
		// if (String.valueOf(cuadra.getTipoNumeracion()).equals("I")) {
		// this.getTfCodigoPostalImpar().setText(cuadra.getCodPostal());
		// } else {
		// this.getTfCodigoPostalPar().setText(cuadra.getCodPostal());
		// }
		// }

		this.getObjectListDataProvider().setList(cuadra.getListaAsociacionParcelaCuadra());
		this.setListaDelCommunication(new ArrayList(cuadra.getListaAsociacionParcelaCuadra()));

		this.setListaDelCommunicationAtributosDinamicos(atributosDinamicos);
	}

	private Calle getCallePorNombre(String pCalle) {
		return this.getComunicationCatastroBean().getMapaCalles().get(pCalle);
	}

	private ArrayList getListaDelCommunicationAtributosDinamicos() {
		return this.getComunicationCatastroBean().getListaAtributosDinamicosCuadra();
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		this.getComunicationCatastroBean().setListaAtributosDinamicosCuadra(lista);
	}

	public String btnSeleccionarCalle_action() {
		return navegarParaSeleccionar("AdminCalle", 1);
	}

	public String btnLimpiarCalle_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.ddCalle.setSelected(null);

			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarCalleComienza_action() {
		return navegarParaSeleccionar("AdminCalle", 2);
	}

	public String btnLimpiarCalleComienza_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.ddCalleComienza.setSelected(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarCalleFinaliza_action() {
		return navegarParaSeleccionar("AdminCalle", 3);
	}

	public String btnLimpiarCalleFinaliza_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.ddCalleFinaliza.setSelected(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarZona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			retorno = "AdminZona";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarZona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		Cuadra cuadra = (Cuadra) this.obtenerObjetoDelElementoPila(0, Cuadra.class);

		if (ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());

					Object obj = this.getObjectListDataProvider().getObjects()[index];

					AsociacionParcelaCuadra asociacion = (AsociacionParcelaCuadra) obj;

					if (this.getObjectListDataProvider().getList().size() > 0) {
						this.getObjectListDataProvider().commitChanges();
					}

					cuadra.removeZona(asociacion);
					System.out.println("lista dsp de eliminar = " + cuadra.getListaAsociacionParcelaCuadra() + ", con tamaño = " + cuadra.getListaAsociacionParcelaCuadra().size());

					this.setListaDelCommunication(cuadra.getListaAsociacionParcelaCuadra());
					this.getObjectListDataProvider().setList(this.getListaDelCommunication());
					this.getElementoPila().getObjetos().set(0, cuadra);
				}
			} catch (Exception ex) {
				System.out.println("btnQuitar() entra al catch...");
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
		}
		return retorno;
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getLdpZonas().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpZonas();
	}

	private List getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaZonasCuadras();
	}

	private void setListaDelCommunication(List lista) {
		this.getComunicationCatastroBean().setListaZonasCuadras(lista);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMCuadra";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof Calle) {
			if (pObject != null) {
				int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
				this.getElementoPila().getObjetos().set(posicion, pObject);
			}
		}

		if (pObject instanceof Zona) {
			Cuadra cuadra = (Cuadra) this.obtenerObjetoDelElementoPila(0, Cuadra.class);
			Zona nuevaZona = (Zona) pObject;
			AsociacionParcelaCuadra asociacionExistente = null;

			boolean esta = false;
			int i = 0;
			while (i < cuadra.getListaAsociacionParcelaCuadra().size() && !esta) {
				asociacionExistente = cuadra.getListaAsociacionParcelaCuadra().get(i);
				esta = (asociacionExistente.getZona().equals(nuevaZona));
				i++;
			}
			if (!esta) {
				cuadra.addZona(nuevaZona);
			} else {
				warn("La Zona que intenta agregar ya se encuentra en la lista.");
			}
			this.getElementoPila().getObjetos().set(0, cuadra);
			this.getRequestBean1().setObjetoSeleccion(null);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Cuadra cuadra = (Cuadra) pObject;
		Calle calle = cuadra.getCalle();
		Calle calleComienza = cuadra.getCalleComienza();
		Calle calleFinaliza = cuadra.getCalleFinaliza();

		List listaZona = new ArrayList();
		ArrayList atributosDinamicos = null;

		try {
			this.setListaDelCommunication(cuadra.getListaAsociacionParcelaCuadra());
			this.getLdpZonas().setList(this.getListaDelCommunication());
			listaZona = this.getLdpZonas().getList();
		} catch (Exception ex) {
			error("No se pudieron obtener las Zonas de la Cuadra: " + ex.getMessage());
		}

		if (cuadra.getListaAtributosDinamicos() != null) {
			try {
				atributosDinamicos = (ArrayList) this.getComunicationBean().getRemoteSystemParametro()
						.getAtributosPorRecurso(Cuadra.serialVersionUID, cuadra.getListaAtributosDinamicos(), null);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, cuadra);
		this.getElementoPila().getObjetos().set(ind++, calle);
		this.getElementoPila().getObjetos().set(ind++, calleComienza);
		this.getElementoPila().getObjetos().set(ind++, calleFinaliza);
		this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
	}
	
	@Override
	public long getSerialVersionUID() {
		return Cuadra.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMCuadra$ABMCuadra}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Cuadra locCuadra = this.obtenerObjetoDelElementoPila(0, Cuadra.class);
		this.getTablaLogs().getLdpLogs().setList(locCuadra.getListaLogsAuditoria());
	}
}