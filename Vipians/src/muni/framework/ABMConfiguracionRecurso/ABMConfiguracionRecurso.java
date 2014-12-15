/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.framework.ABMConfiguracionRecurso;

import java.util.List;

import javax.faces.convert.IntegerConverter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.AddRemove;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Listbox;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.filtros.FiltroPlantillaAtributosDinamicos;
import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.ConjuntoAtributoTabla;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author jsantacruz
 */
public class ABMConfiguracionRecurso extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunication() != null) {
			this.getLdpConjuntoAtributosTabla().setList(this.getListaDelCommunication());
		}

		this.armarOpcionesLb();
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private ObjectListDataProvider ldpConjuntoAtributosTabla = new ObjectListDataProvider();
	private RadioButton radioButton1 = new RadioButton();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tableColumn2 = new TableColumn();
	private TableColumn tableColumn3 = new TableColumn();
	private TextArea textArea1 = new TextArea();
	private TextArea textArea2 = new TextArea();
	private PanelGroup groupPanel1 = new PanelGroup();
	private Button btnAgregarConjuntoAtributosTabla = new Button();
	private Button btnModificarConjuntoAtributosTabla = new Button();
	private HtmlAjaxCommandButton btnQuitarConjuntoAtributosTabla = new HtmlAjaxCommandButton();

	private Listbox lbPropiedades = new Listbox();
	private SingleSelectOptionsList lbPropiedadesOptions = new SingleSelectOptionsList();

	// Global

	public Listbox getLbPropiedades() {
		return lbPropiedades;
	}

	public void setLbPropiedades(Listbox lbPropiedades) {
		this.lbPropiedades = lbPropiedades;
	}

	public SingleSelectOptionsList getLbPropiedadesOptions() {
		return lbPropiedadesOptions;
	}

	public void setLbPropiedadesOptions(SingleSelectOptionsList lbPropiedadesOptions) {
		this.lbPropiedadesOptions = lbPropiedadesOptions;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public ObjectListDataProvider getLdpConjuntoAtributosTabla() {
		return ldpConjuntoAtributosTabla;
	}

	public void setLdpConjuntoAtributosTabla(ObjectListDataProvider ldpConjuntoAtributosTabla) {
		this.ldpConjuntoAtributosTabla = ldpConjuntoAtributosTabla;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
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

	public TextArea getTextArea1() {
		return textArea1;
	}

	public void setTextArea1(TextArea textArea1) {
		this.textArea1 = textArea1;
	}

	public TextArea getTextArea2() {
		return textArea2;
	}

	public void setTextArea2(TextArea textArea2) {
		this.textArea2 = textArea2;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	private Object lastSelected = null;

	public Object getRBSelected1() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected1(Object selected) {
		if(selected != null) {
			lastSelected = selected;
		}
	}

	public String getCurrentRow1() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow1(int row) {
	}

	public Button getBtnAgregarConjuntoAtributosTabla() {
		return btnAgregarConjuntoAtributosTabla;
	}

	public void setBtnAgregarConjuntoAtributosTabla(Button btnAgregarConjuntoAtributosTabla) {
		this.btnAgregarConjuntoAtributosTabla = btnAgregarConjuntoAtributosTabla;
	}

	public Button getBtnModificarConjuntoAtributosTabla() {
		return btnModificarConjuntoAtributosTabla;
	}

	public void setBtnModificarConjuntoAtributosTabla(Button btnModificarConjuntoAtributosTabla) {
		this.btnModificarConjuntoAtributosTabla = btnModificarConjuntoAtributosTabla;
	}

	public HtmlAjaxCommandButton getBtnQuitarConjuntoAtributosTabla() {
		return btnQuitarConjuntoAtributosTabla;
	}

	public void setBtnQuitarConjuntoAtributosTabla(HtmlAjaxCommandButton btnQuitarConjuntoAtributosTabla) {
		this.btnQuitarConjuntoAtributosTabla = btnQuitarConjuntoAtributosTabla;
	}

	private Label lblToString = new Label();
	private TextArea taToString = new TextArea();
	private Label lblAyudaToString = new Label();
	
	public Label getLblToString() {
		return lblToString;
	}

	public void setLblToString(Label lblToString) {
		this.lblToString = lblToString;
	}

	public TextArea getTaToString() {
		return taToString;
	}

	public void setTaToString(TextArea taToString) {
		this.taToString = taToString;
	}

	public Label getLblAyudaToString() {
		return lblAyudaToString;
	}

	public void setLblAyudaToString(Label lblAyudaToString) {
		this.lblAyudaToString = lblAyudaToString;
	}

	private Label lblRecurso = new Label();
	private TextField tfRecurso = new TextField();

	private Label label1 = new Label();
	private Label label2 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label label1) {
		this.label1 = label1;
	}

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label label2) {
		this.label2 = label2;
	}

	private StaticText textoSeparador = new StaticText();

	public String getTextoSeparador() {
		return "  |  ";
	}

	public void setTextoSeparador(StaticText textoSeparador) {
		this.textoSeparador = textoSeparador;
	}

	// Pestania 1
	private Label lblNombreAlias = new Label();
	private Label lblOrden = new Label();

	private TextField tfNombreAlias = new TextField();
	private TextField tfOrden = new TextField();

	private Listbox taAtributosTabla = new Listbox();
	private Listbox taAtributosTablaSeleccionado = new Listbox();

	private Button btnAgregarASeleccionado = new Button();
	private Button btnQuitarDeSeleccionado = new Button();
	private Button btnOrdenHaciaArriba = new Button();
	private Button btnOrdenHaciaAbajo = new Button();

	private AddRemove listaLinkeada = new AddRemove();
	private Option[] availableOptions;
	private Option[] selectedOptions;

	public Option[] getAvailableOptions() {
		return availableOptions;
	}

	public void setAvailableOptions(final Option[] availableOptions) {
		this.availableOptions = availableOptions;
	}

	public Option[] getSelectedOptions() {
		return selectedOptions;
	}

	public void setSelectedOptions(final Option[] selectedOptions) {
		this.selectedOptions = selectedOptions;
	}

	// Pestania 2

	// Pestania 3

	@Override
	protected void guardarEstadoObjetosUsados() {
		ConfiguracionRecurso configuracionRecurso = this.obtenerObjetoDelElementoPila(0, ConfiguracionRecurso.class);

		configuracionRecurso.setToString(this.getTextAreaValue(getTaToString()));
		configuracionRecurso.setNombreAlias(this.getTextFieldValue(getTfNombreAlias()));
		configuracionRecurso.setOrden(this.getTextFieldValueInteger(getTfOrden()));

		this.getElementoPila().getObjetos().set(0, configuracionRecurso);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		ConfiguracionRecurso configuracionRecurso = this.obtenerObjetoDelElementoPila(0, ConfiguracionRecurso.class);

		if(configuracionRecurso.getNombreRecurso() != null && configuracionRecurso.getNombreRecurso().length() > 0) {
			this.getTfRecurso().setText(configuracionRecurso.getNombreRecurso());
		}
		this.getTaToString().setText(configuracionRecurso.getToString());
		this.getTfNombreAlias().setText(configuracionRecurso.getNombreAlias());
		this.getTfOrden().setText(configuracionRecurso.getOrden());
	}

	@Override
	protected String getNombrePagina() {
		return "Configuración de Recursos";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMConfiguracionRecurso";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new ConfiguracionRecurso());

		if(this.getListaDelCommunication() != null) {
			this.getListaDelCommunication().clear();
		}

		// Para limpiar propiedades que puedan haber quedado de pantallas anteriores.
		this.getComunicationBean().setListaPropiedadesConfiguracionRecurso(null);
		this.lbPropiedadesOptions.setOptions(null);
		// this.armarOpcionesLb();

		return ep;
	}

	/**
	 * @return the lblRecurso
	 */
	public Label getLblRecurso() {
		return lblRecurso;
	}

	/**
	 * @param lblRecurso
	 *            the lblRecurso to set
	 */
	public void setLblRecurso(Label lblRecurso) {
		this.lblRecurso = lblRecurso;
	}

	/**
	 * @return the tfRecurso
	 */
	public TextField getTfRecurso() {
		return tfRecurso;
	}

	/**
	 * @param tfRecurso
	 *            the tfRecurso to set
	 */
	public void setTfRecurso(TextField tfRecurso) {
		this.tfRecurso = tfRecurso;
	}

	/**
	 * @return the lblNombreAlias
	 */
	public Label getLblNombreAlias() {
		return lblNombreAlias;
	}

	/**
	 * @param lblNombreAlias
	 *            the lblNombreAlias to set
	 */
	public void setLblNombreAlias(Label lblNombreAlias) {
		this.lblNombreAlias = lblNombreAlias;
	}

	/**
	 * @return the lblOrden
	 */
	public Label getLblOrden() {
		return lblOrden;
	}

	/**
	 * @param lblOrden
	 *            the lblOrden to set
	 */
	public void setLblOrden(Label lblOrden) {
		this.lblOrden = lblOrden;
	}

	/**
	 * @return the tfNombreAlias
	 */
	public TextField getTfNombreAlias() {
		return tfNombreAlias;
	}

	/**
	 * @param tfNombreAlias
	 *            the tfNombreAlias to set
	 */
	public void setTfNombreAlias(TextField tfNombreAlias) {
		this.tfNombreAlias = tfNombreAlias;
	}

	/**
	 * @return the tfOrden
	 */
	public TextField getTfOrden() {
		return tfOrden;
	}

	/**
	 * @param tfOrden
	 *            the tfOrden to set
	 */
	public void setTfOrden(TextField tfOrden) {
		this.tfOrden = tfOrden;
	}

	/**
	 * @return the taAtributosTabla
	 */
	public Listbox getTaAtributosTabla() {
		return taAtributosTabla;
	}

	/**
	 * @param taAtributosTabla
	 *            the taAtributosTabla to set
	 */
	public void setTaAtributosTabla(Listbox taAtributosTabla) {
		this.taAtributosTabla = taAtributosTabla;
	}

	/**
	 * @return the taAtributosTablaSeleccionado
	 */
	public Listbox getTaAtributosTablaSeleccionado() {
		return taAtributosTablaSeleccionado;
	}

	/**
	 * @param taAtributosTablaSeleccionado
	 *            the taAtributosTablaSeleccionado to set
	 */
	public void setTaAtributosTablaSeleccionado(Listbox taAtributosTablaSeleccionado) {
		this.taAtributosTablaSeleccionado = taAtributosTablaSeleccionado;
	}

	/**
	 * @return the btnAgregarASeleccionado
	 */
	public Button getBtnAgregarASeleccionado() {
		return btnAgregarASeleccionado;
	}

	/**
	 * @param btnAgregarASeleccionado
	 *            the btnAgregarASeleccionado to set
	 */
	public void setBtnAgregarASeleccionado(Button btnAgregarASeleccionado) {
		this.btnAgregarASeleccionado = btnAgregarASeleccionado;
	}

	/**
	 * @return the btnQuitarDeSeleccionado
	 */
	public Button getBtnQuitarDeSeleccionado() {
		return btnQuitarDeSeleccionado;
	}

	/**
	 * @param btnQuitarDeSeleccionado
	 *            the btnQuitarDeSeleccionado to set
	 */
	public void setBtnQuitarDeSeleccionado(Button btnQuitarDeSeleccionado) {
		this.btnQuitarDeSeleccionado = btnQuitarDeSeleccionado;
	}

	/**
	 * @return the btnOrdenHaciaArriba
	 */
	public Button getBtnOrdenHaciaArriba() {
		return btnOrdenHaciaArriba;
	}

	/**
	 * @param btnOrdenHaciaArriba
	 *            the btnOrdenHaciaArriba to set
	 */
	public void setBtnOrdenHaciaArriba(Button btnOrdenHaciaArriba) {
		this.btnOrdenHaciaArriba = btnOrdenHaciaArriba;
	}

	/**
	 * @return the btnOrdenHaciaAbajo
	 */
	public Button getBtnOrdenHaciaAbajo() {
		return btnOrdenHaciaAbajo;
	}

	private Button btnSeleccionarRecurso = new Button();
	private HtmlAjaxCommandButton btnLimpiarRecurso = new HtmlAjaxCommandButton();

	public Button getBtnSeleccionarRecurso() {
		return btnSeleccionarRecurso;
	}

	public void setBtnSeleccionarRecurso(Button btnSeleccionarRecurso) {
		this.btnSeleccionarRecurso = btnSeleccionarRecurso;
	}

	public HtmlAjaxCommandButton getBtnLimpiarRecurso() {
		return btnLimpiarRecurso;
	}

	public void setBtnLimpiarRecurso(HtmlAjaxCommandButton btnLimpiarRecurso) {
		this.btnLimpiarRecurso = btnLimpiarRecurso;
	}

	/**
	 * @param btnOrdenHaciaAbajo
	 *            the btnOrdenHaciaAbajo to set
	 */
	public void setBtnOrdenHaciaAbajo(Button btnOrdenHaciaAbajo) {
		this.btnOrdenHaciaAbajo = btnOrdenHaciaAbajo;
	}

	// public List<String> getListaAtributos() throws InstantiationException,
	// IllegalAccessException {
	// ConfiguracionRecurso configuracionRecurso = (ConfiguracionRecurso) this
	// .obtenerObjetoDelElementoPila(0, ConfiguracionRecurso.class);
	// Recurso recurso = null;
	//
	// try {
	// // recurso = getComunicationBean().getRemoteSystemUsuario()
	// // .getRecursoPorId(configuracionRecurso.getIdRecurso());
	// recurso = configuracionRecurso.getRecurso();
	// } catch (Exception e) {
	// System.out.println("Salto exception...");
	// }
	//
	// System.out.println("RECURSO ENCONTRADO: " + recurso.getNombre());
	// List<String> retorno = new ArrayList<String>();
	//
	// if (recurso != null) {
	// for (String cadaAtributo : recurso.getMetaClase().newInstance()
	// .getPropiedadAsString(null, true, null)) {
	// retorno.add(cadaAtributo);
	// System.out.println("Agrego al retorno: " + cadaAtributo);
	// }
	// }
	// return retorno;
	// }

	public void setListaAtributos(Button btnOrdenHaciaAbajo) {
		this.btnOrdenHaciaAbajo = btnOrdenHaciaAbajo;
	}

	/**
	 * @return the listaLinkeada
	 */
	public AddRemove getListaLinkeada() {
		return listaLinkeada;
	}

	/**
	 * @param listaLinkeada
	 *            the listaLinkeada to set
	 */
	public void setListaLinkeada(AddRemove listaLinkeada) {
		this.listaLinkeada = listaLinkeada;
	}

	private IntegerConverter addRemoveList1Converter = new IntegerConverter();

	public IntegerConverter getAddRemoveList1Converter() {
		return addRemoveList1Converter;
	}

	public void setAddRemoveList1Converter(final IntegerConverter ic) {
		this.addRemoveList1Converter = ic;
	}

	private Integer[] elegido;

	public Integer[] getSelected() {
		return getElegido();
	}

	public void setSelected(Integer[] sel) {
		setElegido(sel);
	}

	/**
	 * @return the elegido
	 */
	public Integer[] getElegido() {
		return elegido;
	}

	/**
	 * @param elegido
	 *            the elegido to set
	 */
	public void setElegido(Integer[] elegido) {
		this.elegido = elegido;
	}

	public String btnSeleccionarRecurso_action() {
		return navegarParaSeleccionar("SeleccionarRecurso");
	}

	public String btnLimpiarRecurso_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.getElementoPila().getObjetos().set(0, null);
			this.limpiarObjetos();
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public void limpiarObjetos() {
		this.getTfRecurso().setText(null);
		this.getTfNombreAlias().setText(null);
		this.getTfOrden().setText(null);
	}

	public String btnAgregarConjuntoAtributosTabla_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			ConfiguracionRecurso configuracion = (ConfiguracionRecurso) this.obtenerObjetoDelElementoPila(0, ConfiguracionRecurso.class);

			if(configuracion != null && configuracion.getIdRecurso() != null) {
				this.getRequestBean1().setObjetoABM(configuracion);
				retorno = toAbm(new ConjuntoAtributoTablaModel().new AgregarConjuntoAtributoTablaController(), "ABMConjuntoAtributoTabla");
			} else {
				warn("Debe seleccionar un Recurso para agregar un Conjunto de Atributos Tabla.");
				retorno = null;
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnModificarConjuntoAtributosTabla_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());

					Object obj = this.getLdpConjuntoAtributosTabla().getObjects()[index];

					ConjuntoAtributoTabla conjuntoAtrTab = (ConjuntoAtributoTabla) obj;

					if(conjuntoAtrTab != null) {
						this.getRequestBean1().setObjetoABM(conjuntoAtrTab);
						retorno = toAbm(new ConjuntoAtributoTablaModel().new ModificarConjuntoAtributoTablaController(), "ABMConjuntoAtributoTabla");
					} else {
						retorno = null;
					}
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
		}
		return retorno;
	}

	public String btnQuitarConjuntoAtributosTabla_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		ConfiguracionRecurso configuracion = this.obtenerObjetoDelElementoPila(0, ConfiguracionRecurso.class);

		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());

					Object obj = this.getLdpConjuntoAtributosTabla().getObjects()[index];

					ConjuntoAtributoTabla conjuntoAtrTab = (ConjuntoAtributoTabla) obj;

					if(this.getLdpConjuntoAtributosTabla().getList().size() > 0) {
						this.getLdpConjuntoAtributosTabla().commitChanges();
					}

					configuracion.getListaConjuntoAtributosTabla().remove(conjuntoAtrTab);

					this.setListaDelCommunication(configuracion.getListaConjuntoAtributosTabla());
					this.getLdpConjuntoAtributosTabla().setList(this.getListaDelCommunication());
					this.getElementoPila().getObjetos().set(0, configuracion);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
		}
		return retorno;
	}

	private List getListaDelCommunication() {
		return this.getComunicationBean().getListaConjAtrTabConfRec();
	}

	private void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaConjAtrTabConfRec(lista);
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getLdpConjuntoAtributosTabla().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	private void agregarAtributosDinamicosComoPropiedades(Long pSerialVersion) {
		try {
			this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
			FiltroPlantillaAtributosDinamicos locFiltro = new FiltroPlantillaAtributosDinamicos();
			locFiltro.setIdRecurso(pSerialVersion);
			List<PlantillaAtributoDinamico> locListaPlantillas = this.getComunicationBean().getRemoteSystemParametro().findListaPlantillaAtritbutosDinamicos(locFiltro)
					.getListaResultados();
			for(PlantillaAtributoDinamico cadaPlantilla : locListaPlantillas) {
				this.getComunicationBean().getListaPropiedadesConfiguracionRecurso().add("mapaNombresValoresAtributosDinamicos['" + cadaPlantilla.getNombre() + "']");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void armarOpcionesLb() {
		List<String> locListaPropiedades = this.getComunicationBean().getListaPropiedadesConfiguracionRecurso();
		Option[] opOpciones;
		if(locListaPropiedades != null) {
			int i = 0;
			opOpciones = new Option[locListaPropiedades.size()];
			for(String cadaOpcion : locListaPropiedades) {
				opOpciones[i++] = new Option(cadaOpcion, cadaOpcion);
			}
		} else {
			opOpciones = new Option[] {new Option("", "")};
		}
		this.lbPropiedadesOptions.setOptions(opOpciones);
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof Recurso) {
			Recurso recurso = (Recurso) pObject;
			ConfiguracionRecurso configuracionRecurso = this.obtenerObjetoDelElementoPila(0, ConfiguracionRecurso.class);

			if(recurso.getListaPropiedadesClase() != null) {
				this.getComunicationBean().setListaPropiedadesConfiguracionRecurso(recurso.getListaPropiedadesClase());
				this.agregarAtributosDinamicosComoPropiedades(recurso.getIdRecurso());

				this.armarOpcionesLb();
			}

			configuracionRecurso.setRecurso(recurso);
			this.getElementoPila().getObjetos().set(0, configuracionRecurso);
		}
		if(pObject instanceof ConjuntoAtributoTabla) {
			ConfiguracionRecurso configuracionRecurso = this.obtenerObjetoDelElementoPila(0, ConfiguracionRecurso.class);

			ConjuntoAtributoTabla nuevoConjunto = (ConjuntoAtributoTabla) pObject;
			ConjuntoAtributoTabla conjuntoExistente = null;
			boolean esta = false;
			int i = 0;
			while(i < configuracionRecurso.getListaConjuntoAtributosTabla().size() && !esta) {
				conjuntoExistente = configuracionRecurso.getListaConjuntoAtributosTabla().get(i);

				if(nuevoConjunto.getListaUsuarios().isEmpty() && conjuntoExistente.getListaUsuarios().isEmpty()) {
					esta = true;
					break;
				}

				for(Usuario cadaUsuario : conjuntoExistente.getListaUsuarios()) {
					if(nuevoConjunto.getListaUsuarios().contains(cadaUsuario)) {
						esta = true;
						break;
					}
				}

				i++;
			}
			if(!esta) {
				configuracionRecurso.getListaConjuntoAtributosTabla().add(nuevoConjunto);
			} else {
				if(nuevoConjunto.getIdConjuntoAtributoTabla() != conjuntoExistente.getIdConjuntoAtributoTabla()) {
					warn("El Conjunto de Atributos que intenta agregar posee usuarios que ya se encuentran ingresados.");
				}
			}

			this.setListaDelCommunication(configuracionRecurso.getListaConjuntoAtributosTabla());
			this.getLdpConjuntoAtributosTabla().setList(this.getListaDelCommunication());

			this.getElementoPila().getObjetos().set(0, configuracionRecurso);
			this.getRequestBean1().setObjetoSeleccion(null);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		ConfiguracionRecurso configuracionRecurso = (ConfiguracionRecurso) pObject;

		if(configuracionRecurso.getListaConjuntoAtributosTabla() != null && configuracionRecurso.getListaConjuntoAtributosTabla().size() > 0) {
			this.setListaDelCommunication(configuracionRecurso.getListaConjuntoAtributosTabla());
			this.getLdpConjuntoAtributosTabla().setList(this.getListaDelCommunication());
		}

		if(configuracionRecurso.getRecurso() != null && configuracionRecurso.getRecurso().getListaPropiedadesClase() != null) {
			this.getComunicationBean().setListaPropiedadesConfiguracionRecurso(configuracionRecurso.getRecurso().getListaPropiedadesClase());
			this.agregarAtributosDinamicosComoPropiedades(configuracionRecurso.getRecurso().getIdRecurso());

			this.armarOpcionesLb();
		}

		this.getElementoPila().getObjetos().set(0, configuracionRecurso);
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso}";
	}

	@Override
	public long getSerialVersionUID() {
		return ConfiguracionRecurso.serialVersionUID;
	}
}