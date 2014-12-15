/*
 * ModificarZona.java
 *
 * Created on 17 de octubre de 2006, 15:49
 * Copyright Trascender
 */

package muni.catastro.ABMZona;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;

import muni.catastro.ABMParcela.AdminParcela;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.HiddenField;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.catastro.recurso.persistent.AsociacionParcela;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaBridge;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaCalle;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaCuadra;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaManzana;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.validadores.Validador;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMZona extends ABMPageBean {

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong> This method is automatically generated, so any user-specified code inserted
	 * here is subject to being replaced.
	 * </p>
	 */
	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunicationFiltrada() != null) {
			this.getLdpAsociacion().setList(this.getListaDelCommunicationFiltrada());
			this.getLdpAsociacion().setObjectType(AsociacionParcelaBridge.class);
		} else if(this.getListaDelCommunication() != null) {
			this.getLdpAsociacion().setList(this.getListaDelCommunication());
			this.getLdpAsociacion().setObjectType(AsociacionParcelaBridge.class);
		}
	}
	
	@Override
	public Body getBody1() {
		Body body1 = super.getBody1();
		body1.setOnKeyPress("");
		return body1;
	}

	// Sobreescritos para que funcione el javascript en la tabla de agregar parcelas...
	@Override
	public Form getForm1() {
		Form form1 = super.getForm1();
		form1.setOnKeyPress("");
		return form1;
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private Table table1 = new Table();

	public Table getTable1() {
		return this.table1;
	}

	public void setTable1(Table table) {
		this.table1 = table;
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	private ObjectListDataProvider ldpAsociacion = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpAsociacion() {
		return ldpAsociacion;
	}

	public void setLdpAsociacion(ObjectListDataProvider ldpAsociacion) {
		this.ldpAsociacion = ldpAsociacion;
	}

	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	private Script scriptFinal1 = new Script();

	public Script getScriptFinal1() {
		return scriptFinal1;
	}

	public void setScriptFinal1(Script scriptFinal1) {
		this.scriptFinal1 = scriptFinal1;
	}

	private RadioButton radioButton1 = new RadioButton();

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton rb) {
		this.radioButton1 = rb;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	private TableColumn tableColumn3 = new TableColumn();

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tc) {
		this.tableColumn3 = tc;
	}

	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	private Object lastSelected = null;

	public Object getRBSelected() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if(selected != null) {
			lastSelected = selected;
		}
	}

	private PanelGroup groupPanel3 = new PanelGroup();
	private PanelGroup groupPanel4 = new PanelGroup();

	public PanelGroup getGroupPanel3() {
		return groupPanel3;
	}

	public void setGroupPanel3(PanelGroup pg) {
		this.groupPanel3 = pg;
	}

	public PanelGroup getGroupPanel4() {
		return groupPanel4;
	}

	public void setGroupPanel4(PanelGroup groupPanel4) {
		this.groupPanel4 = groupPanel4;
	}

	private Button btnSeleccionarParcela = new Button();

	public HtmlAjaxCommandButton getBtnQuitarAsociacion() {
		return btnQuitarAsociacion;
	}

	public void setBtnQuitarAsociacion(HtmlAjaxCommandButton btnQuitarAsociacion) {
		this.btnQuitarAsociacion = btnQuitarAsociacion;
	}

	public Button getBtnSeleccionarCalle() {
		return btnSeleccionarCalle;
	}

	public void setBtnSeleccionarCalle(Button btnSeleccionarCalle) {
		this.btnSeleccionarCalle = btnSeleccionarCalle;
	}

	public Button getBtnSeleccionarCuadra() {
		return btnSeleccionarCuadra;
	}

	public void setBtnSeleccionarCuadra(Button btnSeleccionarCuadra) {
		this.btnSeleccionarCuadra = btnSeleccionarCuadra;
	}

	public Button getBtnSeleccionarManzana() {
		return btnSeleccionarManzana;
	}

	public void setBtnSeleccionarManzana(Button btnSeleccionarManzana) {
		this.btnSeleccionarManzana = btnSeleccionarManzana;
	}

	public Button getBtnSeleccionarParcela() {
		return btnSeleccionarParcela;
	}

	public void setBtnSeleccionarParcela(Button btnSeleccionarParcela) {
		this.btnSeleccionarParcela = btnSeleccionarParcela;
	}

	public StaticText getStaticText14() {
		return staticText14;
	}

	public void setStaticText14(StaticText staticText14) {
		this.staticText14 = staticText14;
	}

	private Label lbParcela = new Label();
	private TextField tfFiltrar = new TextField();
	private HtmlAjaxCommandButton btnFiltrar = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLimpiarFiltrado = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarFiltrado() {
		return btnLimpiarFiltrado;
	}

	public void setBtnLimpiarFiltrado(HtmlAjaxCommandButton btnLimpiarFiltrado) {
		this.btnLimpiarFiltrado = btnLimpiarFiltrado;
	}

	public HtmlAjaxCommandButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public void setBtnFiltrar(HtmlAjaxCommandButton btnFiltrar) {
		this.btnFiltrar = btnFiltrar;
	}

	public Label getLbParcela() {
		return lbParcela;
	}

	public void setLbParcela(Label lbParcela) {
		this.lbParcela = lbParcela;
	}

	public TextField getTfFiltrar() {
		return tfFiltrar;
	}

	public void setTfFiltrar(TextField tfFiltrar) {
		this.tfFiltrar = tfFiltrar;
	}

	private Button btnSeleccionarCuadra = new Button();
	private Button btnSeleccionarCalle = new Button();
	private Button btnSeleccionarManzana = new Button();
	protected HtmlAjaxCommandButton btnQuitarAsociacion = new HtmlAjaxCommandButton();
	private StaticText staticText14 = new StaticText();
	private TextField tfPrioridad = new TextField();

	public TextField getTfPrioridad() {
		return tfPrioridad;
	}

	public void setTfPrioridad(TextField tfPrioridad) {
		this.tfPrioridad = tfPrioridad;
	}

	private Label lblZonificacion = new Label();

	public Button getBtnSeleccionarZonificacion() {
		return btnSeleccionarZonificacion;
	}

	public void setBtnSeleccionarZonificacion(Button btnSeleccionarZonificacion) {
		this.btnSeleccionarZonificacion = btnSeleccionarZonificacion;
	}

	public Label getLblZonificacion() {
		return lblZonificacion;
	}

	public void setLblZonificacion(Label lblZonificacion) {
		this.lblZonificacion = lblZonificacion;
	}

	public TextField getTfZonificacion() {
		return tfZonificacion;
	}

	public void setTfZonificacion(TextField tfZonificacion) {
		this.tfZonificacion = tfZonificacion;
	}

	private TextField tfZonificacion = new TextField();
	private Button btnSeleccionarZonificacion = new Button();
	private HtmlAjaxCommandButton btnLimpiarZonificacion = new HtmlAjaxCommandButton();
	private Label lblPrioridad = new Label();

	public HtmlAjaxCommandButton getBtnLimpiarZonificacion() {
		return btnLimpiarZonificacion;
	}

	public void setBtnLimpiarZonificacion(HtmlAjaxCommandButton btnLimpiarZonificacion) {
		this.btnLimpiarZonificacion = btnLimpiarZonificacion;
	}

	public Label getLblPrioridad() {
		return lblPrioridad;
	}

	public void setLblPrioridad(Label l) {
		this.lblPrioridad = l;
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

	private StaticText stSeparador1 = new StaticText();
	private TextField tfParcela = new TextField();
	private HtmlAjaxCommandButton btnAgregarParcela = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLimpiarParcela = new HtmlAjaxCommandButton();

	// CAMBIAR: Objetos administrados por la pagina
	// Generar getters y setters.
	// En el getter poner:
	// if (this.objeto == null) this.objeto = new Objeto()

	public StaticText getStSeparador1() {
		return stSeparador1;
	}

	public void setStSeparador1(StaticText stSeparador1) {
		this.stSeparador1 = stSeparador1;
	}

	public TextField getTfParcela() {
		return tfParcela;
	}

	public void setTfParcela(TextField tfParcela) {
		this.tfParcela = tfParcela;
	}

	public HtmlAjaxCommandButton getBtnAgregarParcela() {
		return btnAgregarParcela;
	}

	public void setBtnAgregarParcela(HtmlAjaxCommandButton btnAgregarParcela) {
		this.btnAgregarParcela = btnAgregarParcela;
	}

	public HtmlAjaxCommandButton getBtnLimpiarParcela() {
		return btnLimpiarParcela;
	}

	public void setBtnLimpiarParcela(HtmlAjaxCommandButton btnLimpiarParcela) {
		this.btnLimpiarParcela = btnLimpiarParcela;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		if(this.getListaDelCommunicationFiltrada() != null) {
			this.getListaDelCommunicationFiltrada().clear();
		}
		if(this.getListaDelCommunication() != null) {
			this.getLdpAsociacion().setList(this.getListaDelCommunication());
			this.getLdpAsociacion().setObjectType(AsociacionParcelaBridge.class);
		}

		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		// ep.getObjetos().add(ind++, this.administracionZona); //coneccion al
		// stateful
		ep.getObjetos().add(ind++, new Zona());
		ep.getObjetos().add(ind++, new Zonificacion()); // Zonificacion
		ep.getObjetos().add(ind++, null); // parcela para agregar
		ep.getObjetos().add(ind++, null); // texto de filtrado

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	// Tabla 1: Asociaciones

	private List getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaAsociacionParcela();
	}

	private void setListaDelCommunication(List lista) {
		this.getComunicationCatastroBean().setListaAsociacionParcela(lista);
	}

	private List getListaDelCommunicationFiltrada() {
		return this.getComunicationCatastroBean().getListaFiltradaAsocParcela();
	}

	private void setListaDelCommunicationFiltrada(List lista) {
		this.getComunicationCatastroBean().setListaFiltradaAsocParcela(lista);
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		Zona zona = (Zona) this.obtenerObjetoDelElementoPila(0, Zona.class);
		Zonificacion zonificacion = (Zonificacion) this.obtenerObjetoDelElementoPila(1);

		try {
			zona.setNombre(this.getTextFieldValue(getTfNombre()));
			zona.setDescripcion(this.getTextAreaValue(getTxDescripcion()));
			zona.setPrioridad(this.getTextFieldValueInteger(getTfPrioridad()));

			if(zonificacion != null) {
				zona.setZonificacion(zonificacion);
			} else {
				zona.setZonificacion(null);
			}

		} catch(Exception e) {
			log("ModificarZona_MostrarError:", e);
			error("Modificar Zona - Mostrar: " + e.getMessage());
		}

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, zona);
		this.getElementoPila().getObjetos().set(ind++, zonificacion);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		Zona zona = (Zona) this.obtenerObjetoDelElementoPila(0, Zona.class);
		List asociaciones = null;
		List asociacionesActivas = new ArrayList();
		Zonificacion zonificacion = null;
		Integer prioridad;
		String filtrado;

		// if (administracionZona == null) {
		// //administracionZona = (SystemAdministracionZona)
		// this.getComunicationCatastroBean().getRemoteSystemAdministracionZona();
		// }

		if(this.getRequestBean1().getObjetosSeleccionMultiple() != null) {

			ArrayList seleccion = this.getRequestBean1().getObjetosSeleccionMultiple();
			Object obj = seleccion.get(0);

			try {
				/** lista para comparar id con los objetos seleccion */
				List locAsociacionesList = zona.getListaAsociacionParcela();

				if(locAsociacionesList == null) {
					locAsociacionesList = new ArrayList<AsociacionParcelaBridge>();
				}
				if(obj instanceof Parcela) {

					String mensaje = "";

					List listaParcelas = new ArrayList();
					for(int i = 0; i < seleccion.size(); i++) {
						Parcela locParcela = (Parcela) seleccion.get(i);

						// Volver a habilitar los botones...
						AdminPageBean adminParcela = new AdminParcela();
						adminParcela.getBtnModificar().setRendered(true);
						adminParcela.getBtnEliminar().setRendered(true);

						boolean valor = false;

						for(int j = 0; j < locAsociacionesList.size(); j++) {
							if(locAsociacionesList.get(j) instanceof AsociacionParcela) {
								AsociacionParcela locAsociacion = (AsociacionParcela) locAsociacionesList.get(j);
								if(locAsociacion.getParcela().getIdParcela() == locParcela.getIdParcela()) {
									mensaje += locParcela.toString() + ", ";
									valor = true;
									break;
								}
							}
						}
						if(!valor) {
							listaParcelas.add(locParcela);
							AsociacionParcela locAsoc = new AsociacionParcela();
							locAsoc.setParcela(locParcela);
							locAsoc.setZona(zona);

							if(zona.getListaAsociacionParcela() == null) {
								zona.setListaAsociacionParcela(new ArrayList<AsociacionParcelaBridge>());
							}

							zona.getListaAsociacionParcela().add(locAsoc);
						}
					}
					if(!mensaje.equals("")) {
						warn("Las siguientes Parcelas ya estaban incluidas en la lista: " + mensaje.substring(0, mensaje.length() - 2) + ".");
					}

					this.getComunicationCatastroBean().setListaAsociacionParcela(zona.getListaAsociacionParcela());

				} else if(obj instanceof Cuadra) {

					String mensaje = "";

					List listaCuadras = new ArrayList();
					for(int i = 0; i < seleccion.size(); i++) {
						Cuadra locCuadra = (Cuadra) seleccion.get(i);
						boolean valor = false;

						for(int j = 0; j < locAsociacionesList.size(); j++) {
							if(locAsociacionesList.get(j) instanceof AsociacionParcelaCuadra) {
								AsociacionParcelaCuadra locAsociacion = (AsociacionParcelaCuadra) locAsociacionesList.get(j);
								if(locAsociacion.getCuadra().getIdCuadra() == locCuadra.getIdCuadra()) {

									mensaje += locCuadra.getNombre() + ", ";
									valor = true;
									break;
								}
							}
						}
						if(!valor) {
							listaCuadras.add(locCuadra);

							AsociacionParcelaCuadra locAsoc = new AsociacionParcelaCuadra();
							locAsoc.setCuadra(locCuadra);
							locAsoc.setZona(zona);

							if(zona.getListaAsociacionParcela() == null) {
								zona.setListaAsociacionParcela(new ArrayList<AsociacionParcelaBridge>());
							}

							zona.getListaAsociacionParcela().add(locAsoc);
						}
					}
					if(!mensaje.equals("")) {
						warn("Las siguientes Cuadras ya estaban incluidas en la lista: " + mensaje.substring(0, mensaje.length() - 2) + ".");
					}

					this.getComunicationCatastroBean().setListaAsociacionParcela(zona.getListaAsociacionParcela());
				} else if(obj instanceof Calle) {

					String mensaje = "";

					List listaCalles = new ArrayList();
					for(int i = 0; i < seleccion.size(); i++) {
						Calle locCalle = (Calle) seleccion.get(i);
						boolean valor = false;

						for(int j = 0; j < locAsociacionesList.size(); j++) {
							if(locAsociacionesList.get(j) instanceof AsociacionParcelaCalle) {
								AsociacionParcelaCalle locAsociacion = (AsociacionParcelaCalle) locAsociacionesList.get(j);
								if(locAsociacion.getCalle().getIdCalle() == locCalle.getIdCalle()) {

									mensaje += locCalle.getNombre() + ", ";
									valor = true;

									break;
								}
							}
						}
						if(!valor) {
							listaCalles.add(locCalle);

							AsociacionParcelaCalle locAsoc = new AsociacionParcelaCalle();
							locAsoc.setCalle(locCalle);
							locAsoc.setZona(zona);

							if(zona.getListaAsociacionParcela() == null) {
								zona.setListaAsociacionParcela(new ArrayList<AsociacionParcelaBridge>());
							}

							zona.getListaAsociacionParcela().add(locAsoc);
						}
					}
					if(!mensaje.equals("")) {
						warn("Las siguientes Calles ya estaban incluidas en la lista: " + mensaje.substring(0, mensaje.length() - 2) + ".");
					}
					this.getComunicationCatastroBean().setListaAsociacionParcela(zona.getListaAsociacionParcela());

				} else if(obj instanceof Manzana) {

					String mensaje = "";

					List listaManzanas = new ArrayList();
					for(int i = 0; i < seleccion.size(); i++) {
						Manzana locManzana = (Manzana) seleccion.get(i);
						boolean valor = false;

						for(int j = 0; j < locAsociacionesList.size(); j++) {
							if(locAsociacionesList.get(j) instanceof AsociacionParcelaManzana) {
								AsociacionParcelaManzana locAsociacion = (AsociacionParcelaManzana) locAsociacionesList.get(j);
								if(locAsociacion.getManzana().getIdManzana() == locManzana.getIdManzana()) {

									mensaje += locManzana.getNombre() + ", ";
									valor = true;

									break;
								}
							}
						}
						if(!valor) {
							listaManzanas.add(locManzana);

							AsociacionParcelaManzana locAsoc = new AsociacionParcelaManzana();
							locAsoc.setManzana(locManzana);
							locAsoc.setZona(zona);

							if(zona.getListaAsociacionParcela() == null) {
								zona.setListaAsociacionParcela(new ArrayList<AsociacionParcelaBridge>());
							}

							zona.getListaAsociacionParcela().add(locAsoc);
						}
					}
					if(!mensaje.equals("")) {
						warn("Las siguientes manzanas ya estaban incluidas en la lista: " + mensaje.substring(0, mensaje.length() - 2) + ".");
					}
					this.getComunicationCatastroBean().setListaAsociacionParcela(zona.getListaAsociacionParcela());
				}
				this.getElementoPila().getObjetos().set(0, zona);

			} catch(Exception e) {
				log("ModificarZona_MostrarError:", e);
				error("Modificar Zona - Mostrar: " + e.getMessage());
				e.printStackTrace();
			}
		}

		// if (this.getElementoPila() != null &&
		// this.getElementoPila().getObjetos() != null &&
		// !this.getElementoPila().getObjetos().isEmpty()) {
		// administracionZona = (SystemAdministracionZona)
		// this.getComunicationCatastroBean().getRemoteSystemAdministracionZona();
		// }
		zona = (Zona) this.obtenerObjetoDelElementoPila(0, Zona.class);
		zonificacion = (Zonificacion) this.obtenerObjetoDelElementoPila(1, Zonificacion.class);
		try {

			// Zona zona = new Zona();
			// administracionZona.setZona(zona);

			this.getTfNombre().setText(zona.getNombre());
			this.getTfPrioridad().setText(zona.getPrioridad());
			this.getTxDescripcion().setText(zona.getDescripcion());
			asociaciones = zona.getListaAsociacionParcela();

			// Muestro solo las Asociaciones Activas:
			if(asociaciones != null) {
				for(Object obj : asociaciones) {
					if(obj instanceof AsociacionParcela) {
						asociacionesActivas.add(obj);
					}
					if(obj instanceof AsociacionParcelaCalle) {
						AsociacionParcelaCalle a = (AsociacionParcelaCalle) obj;
						if(a.getCalle().isActivo()) {
							asociacionesActivas.add(a);
						}
					}
					if(obj instanceof AsociacionParcelaCuadra) {
						AsociacionParcelaCuadra a = (AsociacionParcelaCuadra) obj;
						if(a.getCuadra().isActivo()) {
							asociacionesActivas.add(a);
						}
					}
					if(obj instanceof AsociacionParcelaManzana) {
						AsociacionParcelaManzana a = (AsociacionParcelaManzana) obj;
						if(a.getManzana().isActivo()) {
							asociacionesActivas.add(a);
						}
					}
				}
			}
		} catch(Exception e) {
			log("ModificarZona_MostrarError:", e);
			error("Modificar Zona - Mostrar: " + e.getMessage());
			e.printStackTrace();
		}

		if(zonificacion != null) {
			this.getTfZonificacion().setText(zonificacion.getNombre());
		}

		filtrado = (String) this.obtenerObjetoDelElementoPila(3);
		if(filtrado != null) {
			this.getTfFiltrar().setText(filtrado);
			this.getLdpAsociacion().setList(this.getListaDelCommunicationFiltrada());
		} else {
			this.getLdpAsociacion().setList(asociacionesActivas);
		}

		// if (asociaciones != null) {
		// for (Iterator it = asociaciones.iterator(); it.hasNext();) {
		// Object object = it.next();
		// if (object instanceof Parcela) {
		// Parcela parcela = (Parcela) object;
		// System.out.println("parcela---" + parcela.toString());
		// }
		// }
		// }
		// Tabla 1this.getLdpAsociacion().setList(asociaciones);

		this.getLdpAsociacion().setObjectType(AsociacionParcelaBridge.class);
		this.setListaDelCommunication(asociacionesActivas);
	}

	private TextArea txDescripcion = new TextArea();

	public TextArea getTxDescripcion() {
		return txDescripcion;
	}

	public void setTxDescripcion(TextArea ta) {
		this.txDescripcion = ta;
	}

	private HiddenField hidIdObjeto = new HiddenField();

	public HiddenField getHidIdObjeto() {
		return hidIdObjeto;
	}

	public void setHidIdObjeto(HiddenField hf) {
		this.hidIdObjeto = hf;
	}

	// desc="Metodos para trabajar con checkboxes de seleccion">
	private TableSelectPhaseListener tablePhaseListener;

	/**
	 * Setter for selected
	 * 
	 * @param object
	 *            Value to set the property to Bind this to the checkbox's selected property If the object's value matches selectedValue then the checkbox is
	 *            considered to be selected.
	 */
	public void setSelected(Object object) {
		RowKey rowKey = tableRowGroup1.getRowKey();
		if(rowKey != null) {
			tablePhaseListener.setSelected(rowKey, object);
		}
	}

	/**
	 * Getter for selected.
	 * 
	 * @return Object value for the current row's checkbox
	 */
	public Object getSelected() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		return tablePhaseListener.getSelected(rowKey);
	}

	/**
	 * Getter for selectedValue
	 * 
	 * @return Object value of the component when it is selected Bind this property to the checkbox's selectedValue property If the object's value matches
	 *         selectedValue then the checkbox is considered to be selected. In this case, when the checkbox's selected property returns its RowKey value, then
	 *         it is considered to be selected.
	 */
	public Object getSelectedValue() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		return (rowKey != null) ? rowKey.getRowId() : null;
	}

	/**
	 * Getter for currentRowSelected
	 * 
	 * @return Boolean true if the checkbox in the current row is selected Bind this property to the row's selected property
	 */
	public boolean isCurrentRowSelected() {
		RowKey rowKey = tableRowGroup1.getRowKey();
		// // ariel
		// if (rowKey != null) {
		return tablePhaseListener.isSelected(rowKey);
		// }
		// else return false;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de página.
	 * </p>
	 */
	public ABMZona() {
	}

	public void btnAgregarParcela_action() {
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			Parcela parcela = (Parcela) this.obtenerObjetoDelElementoPila(2);

			if(parcela != null) {
				Zona zona = (Zona) this.obtenerObjetoDelElementoPila(0, Zona.class);
				List locListaAsocParcelas = zona.getListaAsociacionParcela();
				if(locListaAsocParcelas == null) {
					locListaAsocParcelas = new ArrayList();
				}

				boolean esta = false;
				for(Object cadaObject : locListaAsocParcelas) {
					AsociacionParcela cadaAsocParcela = (AsociacionParcela) cadaObject;
					if(cadaAsocParcela.getParcela().equals(parcela)) {
						esta = true;
						break;
					}
				}
				if(!esta) {
					AsociacionParcela locAsoc = new AsociacionParcela();
					locAsoc.setParcela(parcela);
					locAsoc.setZona(zona);

					if(zona.getListaAsociacionParcela() == null) {
						zona.setListaAsociacionParcela(new ArrayList<AsociacionParcelaBridge>());
					}

					zona.getListaAsociacionParcela().add(locAsoc);

					this.getComunicationCatastroBean().setListaAsociacionParcela(zona.getListaAsociacionParcela());

					String filtrado = (String) this.obtenerObjetoDelElementoPila(3);
					if(filtrado != null) {
						filtrarAsocParcela(this.getComunicationCatastroBean().getListaAsociacionParcela(), filtrado);
					} else {
						this.getLdpAsociacion().setList(zona.getListaAsociacionParcela());
					}

					this.getElementoPila().getObjetos().set(0, zona);
					this.limpiarObjeto(2, this.getTfParcela());
				}
			}
		}
	}

	public String btnLimpiarParcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(2, this.getTfParcela());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarParcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			this.guardarEstadoObjetosUsados();

			this.getRequestBean1().setTipoSeleccion("MULTIPLE");
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			retorno = "AdminParcela";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarCuadra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setTipoSeleccion("MULTIPLE");
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			retorno = "AdminCuadra";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarCalle_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setTipoSeleccion("MULTIPLE");
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			retorno = "AdminCalle";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarManzana_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setTipoSeleccion("MULTIPLE");
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			retorno = "AdminManzana";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	private RowKey rowKeySeleccionado = null;

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getLdpAsociacion().getRowKey(aRowId);

		} catch(Exception ex) {
		}
		return rk;
	}

	public void guardarOrdenamiento() {
		this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
	}

	public void setearOrdenamiento() {
		this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
	}

	public RowKey getRowKeyAsociado(Long posicionEnTabla) {
		RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];
		return rk;
	}

	public Long getPosicionEnTabla(RowKey rk) {
		long inicioPagina = 0;
		long posicionGlobal = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
		long cantRegistros = this.getTableRowGroup1().getRowCount();
		boolean encontrado = false;

		if(rk != null) {
			while(!encontrado && inicioPagina < cantRegistros) {
				this.getTableRowGroup1().setFirst((int) inicioPagina);
				posicionEnPagina = 0;
				while(!encontrado && posicionEnPagina < cantRegistrosPorPagina) {
					encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int) posicionEnPagina].equals(rk);
					if(!encontrado) {
						posicionEnPagina++;
						posicionGlobal++;
					}
				}
				if(!encontrado) {
					inicioPagina += cantRegistrosPorPagina;
				}
			}
		}
		return new Long(posicionGlobal);
	}

	public void seleccionarFila(Long posicionGlobal) {
		long cantFilas = this.getTableRowGroup1().getRowCount();

		if(cantFilas > 0) {
			// si hay al menos una fila
			if(posicionGlobal.longValue() >= cantFilas) {
				// si elimine la ultima fila, me posiciono en la anterior
				posicionGlobal = new Long(cantFilas - 1);
			}
			int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
			this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
			lastSelected = new Long(index).toString();
		}
	}

	public Long getInicioPagina(Long posicionGlobal) {
		long inicioPagina = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();

		inicioPagina = (posicionGlobal.longValue() / cantRegistrosPorPagina) * cantRegistrosPorPagina;
		return new Long(inicioPagina);
	}

	public String btnQuitarAsociacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		Zona zona = (Zona) this.obtenerObjetoDelElementoPila(0, Zona.class);

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.ldpAsociacion.getObject(rk);
					AsociacionParcelaBridge asociacion = (AsociacionParcelaBridge) this.ldpAsociacion.getObjects()[index];
					zona.getListaAsociacionParcela().remove(asociacion);
					this.getComunicationCatastroBean().setListaAsociacionParcela(zona.getListaAsociacionParcela());

					String filtrado = (String) this.obtenerObjetoDelElementoPila(3);
					if(filtrado != null) {
						filtrarAsocParcela(this.getComunicationCatastroBean().getListaAsociacionParcela(), filtrado);
					} else {
						this.getLdpAsociacion().setList(zona.getListaAsociacionParcela());
					}

					this.getElementoPila().getObjetos().set(0, zona);
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public void filtrarAsocParcela(List<AsociacionParcela> listaAsocParcela, String filtrado) {
		List<AsociacionParcela> listaFiltrada = new ArrayList<AsociacionParcela>();

		if(filtrado != null && filtrado != "") {
			for(AsociacionParcela cadaAsocParcela : listaAsocParcela) {
				if(cadaAsocParcela.getNombre().contains(filtrado)) {
					listaFiltrada.add(cadaAsocParcela);
				}
			}
		} else {
			listaFiltrada.addAll(this.getComunicationCatastroBean().getListaAsociacionParcela());
		}
		this.setListaDelCommunicationFiltrada(listaFiltrada);
		this.getLdpAsociacion().setList(listaFiltrada);
	}

	public void btnFiltrar_action() {
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			List<AsociacionParcela> listaAsoc = this.getComunicationCatastroBean().getListaAsociacionParcela();
			if(listaAsoc != null && listaAsoc.size() > 0) {
				String filtrado = this.getTextFieldValue(getTfFiltrar());
				filtrarAsocParcela(listaAsoc, filtrado);

				this.getElementoPila().getObjetos().set(3, filtrado);
			}
		}
	}

	public String btnLimpiarFiltrado_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(3, this.getTfFiltrar());
			if(this.getListaDelCommunication() != null) {
				this.getLdpAsociacion().setList(this.getListaDelCommunication());
				this.getLdpAsociacion().setObjectType(AsociacionParcelaBridge.class);
			}
			this.setListaDelCommunicationFiltrada(null);
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarZonificacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		// CAMBIAR: guardo la posicion donde debo guardar el objeto que
		// seleccione
		int posicionObjetoSeleccionado = 2;

		if(ultimo) {

			Validador v = new Validador();
			UIComponent[] noEnteros = new UIComponent[1];
			String[] nomNoEnteros = new String[1];

			int pos = 0;
			noEnteros[pos] = this.getTfPrioridad();
			nomNoEnteros[pos++] = "Prioridad";

			v.esNumero(noEnteros, nomNoEnteros);

			if(v.getErrores().size() > 0) {
				error("Existen Errores:");
				for(int i = 0; i < v.getErrores().size(); i++) {
					warn(v.getErrores().toArray()[i].toString());
				}
				return null;
			}

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminZonificacion";
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnLimpiarZonificacion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(1, this.getTfZonificacion());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMZona";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof Zonificacion) {
			Zona zona = (Zona) this.obtenerObjetoDelElementoPila(0, Zona.class);
			Zonificacion zonificacion = (Zonificacion) pObject;

			this.getElementoPila().getObjetos().set(1, zonificacion);
			try {
				zona.setZonificacion(zonificacion);
			} catch(Exception e) {
				log("ModificarZona_MostrarError:", e);
				error("Modificar Zona - Mostrar: " + e.getMessage());
			}
			this.getElementoPila().getObjetos().set(0, zona);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Zona zona = null;
		Zonificacion zonificacion = null;
		if(pObject instanceof Zona) {
			zona = (Zona) pObject;
			try {
				// administracionZona.setZona(zona);
			} catch(Exception ex) {
				log("ModificarZona_MostrarError:", ex);
				error("Modificar Zona - Mostrar: " + ex.getMessage());
			}

			try {
				zonificacion = zona.getZonificacion();
			} catch(Exception e) {
				log("ModificarZona_MostrarError:", e);
				error("Modificar Zona - Mostrar: " + e.getMessage());
			}
		}
		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, zona);
		this.getElementoPila().getObjetos().set(ind++, zonificacion);
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Zona locZona = this.obtenerObjetoDelElementoPila(0, Zona.class);
		this.getTablaLogs().getLdpLogs().setList(locZona.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return Zona.serialVersionUID;
	}
	
	@Override
	public String getNombreBean() {
		return "#{catastro$ABMZona$ABMZona}";
	}

	public void setParcelaAutocompletar(String pId, String pIdSubSesion) throws Exception {
		Long id = Long.parseLong(pId);
		Parcela parcela = null;

		try {
			parcela = (Parcela) this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		this.setIdSubSesion(Long.parseLong(pIdSubSesion));
		this.getElementoPila().getObjetos().set(2, parcela);
	}

	public boolean isHayParcela() {
		Parcela locParcela = (Parcela) this.obtenerObjetoDelElementoPila(2);
		return(locParcela != null && locParcela.getIdParcela() != -1);
	}
}