/*
 * AgregarIngresoVario.java
 *
 * Created on 18 de octubre de 2006, 10:30
 * Copyright Trascender SRL
 */

package muni.comunes.ABMIngresoVario;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.event.ValueChangeEvent;

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
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.ImputacionIngresoVario;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.contabilidad.recurso.persistent.RelaConceptoIngresoVarioCuenta;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMIngresoVario extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunicationImputaciones() != null) {
			this.getObjectListDataProviderImputaciones().setList(this.getListaDelCommunicationImputaciones());
		}
		Set<String> locListaRelas = this.getCommunicationCajaBean().getMapaRelaConceptoIngresoVarioCuenta().keySet();
		Option[] opRelas = new Option[locListaRelas.size() + 1];
		int i = 0;
		opRelas[i++] = new Option("", "");
		for(String cadaRela : locListaRelas) {
			opRelas[i++] = new Option(cadaRela, cadaRela);
		}
		ddTodasRelasDelConceptoSeleccionadoOptions.setOptions(opRelas);
	}

	private TextArea taObservaciones = new TextArea();
	private Label lblNumero = new Label();
	private TextField tfNumero = new TextField();
	private Label lblImputaciones = new Label();
	private Table tablaImputaciones = new Table();
	private TableRowGroup trgImputaciones = new TableRowGroup();
	private TableColumn tcCuenta = new TableColumn();
	private TableColumn tcMonto = new TableColumn();
	private ObjectListDataProvider ldpImputaciones = new ObjectListDataProvider();
	private TextField tfMonto = new TextField();
	private StaticText stCuenta = new StaticText();
	private RadioButton rbImputaciones = new RadioButton();
	private TableColumn tcRbImputaciones = new TableColumn();
	private PanelGroup pgImputaciones = new PanelGroup();
	private Button btnAgregarCuenta = new Button();
	private HtmlAjaxCommandButton btnQuitarCuenta = new HtmlAjaxCommandButton();
	private DropDown ddTodasRelasDelConceptoSeleccionado = new DropDown();
	private SingleSelectOptionsList ddTodasRelasDelConceptoSeleccionadoOptions = new SingleSelectOptionsList();
	private HtmlAjaxCommandButton btnAgregarRelaSeleccionada = new HtmlAjaxCommandButton();
	private Label lblTotal = new Label();
	private StaticText stTotal = new StaticText();
	private TextField tfConcepto = new TextField();
	private HtmlAjaxCommandButton btnLimpiarConcepto = new HtmlAjaxCommandButton();
	
	public HtmlAjaxCommandButton getBtnLimpiarConcepto() {
		return btnLimpiarConcepto;
	}

	public void setBtnLimpiarConcepto(HtmlAjaxCommandButton btnLimpiarConcepto) {
		this.btnLimpiarConcepto = btnLimpiarConcepto;
	}

	public TextField getTfConcepto() {
		return tfConcepto;
	}

	public void setTfConcepto(TextField tfConcepto) {
		this.tfConcepto = tfConcepto;
	}

	public HtmlAjaxCommandButton getBtnQuitarCuenta() {
		return btnQuitarCuenta;
	}

	public void setBtnQuitarCuenta(HtmlAjaxCommandButton btnQuitarCuenta) {
		this.btnQuitarCuenta = btnQuitarCuenta;
	}

	public Label getLblTotal() {
		return lblTotal;
	}

	public void setLblTotal(Label lblTotal) {
		this.lblTotal = lblTotal;
	}

	public StaticText getStTotal() {
		return stTotal;
	}

	public void setStTotal(StaticText stTotal) {
		this.stTotal = stTotal;
	}

	public HtmlAjaxCommandButton getBtnAgregarRelaSeleccionada() {
		return btnAgregarRelaSeleccionada;
	}

	public void setBtnAgregarRelaSeleccionada(HtmlAjaxCommandButton btnAgregarRelaSeleccionada) {
		this.btnAgregarRelaSeleccionada = btnAgregarRelaSeleccionada;
	}

	public DropDown getDdTodasRelasDelConceptoSeleccionado() {
		return ddTodasRelasDelConceptoSeleccionado;
	}

	public void setDdTodasRelasDelConceptoSeleccionado(DropDown ddTodasRelasDelConceptoSeleccionado) {
		this.ddTodasRelasDelConceptoSeleccionado = ddTodasRelasDelConceptoSeleccionado;
	}

	public SingleSelectOptionsList getDdTodasRelasDelConceptoSeleccionadoOptions() {
		return ddTodasRelasDelConceptoSeleccionadoOptions;
	}

	public void setDdTodasRelasDelConceptoSeleccionadoOptions(SingleSelectOptionsList ddTodasRelasDelConceptoSeleccionadoOptions) {
		this.ddTodasRelasDelConceptoSeleccionadoOptions = ddTodasRelasDelConceptoSeleccionadoOptions;
	}

	public RadioButton getRbImputaciones() {
		return rbImputaciones;
	}

	public void setRbImputaciones(RadioButton rbImputaciones) {
		this.rbImputaciones = rbImputaciones;
	}

	public TableColumn getTcRbImputaciones() {
		return tcRbImputaciones;
	}

	public void setTcRbImputaciones(TableColumn tcRbImputaciones) {
		this.tcRbImputaciones = tcRbImputaciones;
	}

	public PanelGroup getPgImputaciones() {
		return pgImputaciones;
	}

	public void setPgImputaciones(PanelGroup pgImputaciones) {
		this.pgImputaciones = pgImputaciones;
	}

	public Button getBtnAgregarCuenta() {
		return btnAgregarCuenta;
	}

	public void setBtnAgregarCuenta(Button btnAgregarCuenta) {
		this.btnAgregarCuenta = btnAgregarCuenta;
	}

	public Label getLblImputaciones() {
		return lblImputaciones;
	}

	public void setLblImputaciones(Label lblImputaciones) {
		this.lblImputaciones = lblImputaciones;
	}

	public Table getTablaImputaciones() {
		return tablaImputaciones;
	}

	public void setTablaImputaciones(Table tablaImputaciones) {
		this.tablaImputaciones = tablaImputaciones;
	}

	public TableRowGroup getTrgImputaciones() {
		return trgImputaciones;
	}

	public void setTrgImputaciones(TableRowGroup trgImputaciones) {
		this.trgImputaciones = trgImputaciones;
	}

	public TableColumn getTcCuenta() {
		return tcCuenta;
	}

	public void setTcCuenta(TableColumn tcCuenta) {
		this.tcCuenta = tcCuenta;
	}

	public TableColumn getTcMonto() {
		return tcMonto;
	}

	public void setTcMonto(TableColumn tcMonto) {
		this.tcMonto = tcMonto;
	}

	public ObjectListDataProvider getLdpImputaciones() {
		return ldpImputaciones;
	}

	public void setLdpImputaciones(ObjectListDataProvider ldpImputaciones) {
		this.ldpImputaciones = ldpImputaciones;
	}

	public TextField getTfMonto() {
		return tfMonto;
	}

	public void setTfMonto(TextField tfMonto) {
		this.tfMonto = tfMonto;
	}

	public StaticText getStCuenta() {
		return stCuenta;
	}

	public void setStCuenta(StaticText stCuenta) {
		this.stCuenta = stCuenta;
	}

	public Label getLblNumero() {
		return lblNumero;
	}

	public void setLblNumero(Label lblNumero) {
		this.lblNumero = lblNumero;
	}

	public TextField getTfNumero() {
		return tfNumero;
	}

	public void setTfNumero(TextField tfNumero) {
		this.tfNumero = tfNumero;
	}

	public TextArea getTaObservaciones() {
		return taObservaciones;
	}

	public void setTaObservaciones(TextArea taObservaciones) {
		this.taObservaciones = taObservaciones;
	}

	private Label lblObservaciones = new Label();

	public Label getLblObservaciones() {
		return lblObservaciones;
	}

	public void setLblObservaciones(Label lblObservaciones) {
		this.lblObservaciones = lblObservaciones;
	}

	private TextField tfFechaEmision = new TextField();

	public TextField getTfFechaEmision() {
		return tfFechaEmision;
	}

	public void setTfFechaEmision(TextField tf) {
		this.tfFechaEmision = tf;
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

	private Label lblEstado = new Label();

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	private TextField tfEstado = new TextField();

	public TextField getTfEstado() {
		return tfEstado;
	}

	public void setTfEstado(TextField tfEstado) {
		this.tfEstado = tfEstado;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private TextField tfPersona = new TextField();

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tf) {
		this.tfPersona = tf;
	}

	private Button btnSeleccionarPersonaFisica = new Button();

	public Button getBtnSeleccionarPersonaFisica() {
		return btnSeleccionarPersonaFisica;
	}

	public void setBtnSeleccionarPersonaFisica(Button b) {
		this.btnSeleccionarPersonaFisica = b;
	}

	private Button btnSeleccionarPersonaJuridica = new Button();

	public Button getBtnSeleccionarPersonaJuridica() {
		return btnSeleccionarPersonaJuridica;
	}

	public void setBtnSeleccionarPersonaJuridica(Button b) {
		this.btnSeleccionarPersonaJuridica = b;
	}

	private HtmlAjaxCommandButton btnLimpiarPersona = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarPersona() {
		return btnLimpiarPersona;
	}

	public void setBtnLimpiarPersona(HtmlAjaxCommandButton btnLimpiarPersona) {
		this.btnLimpiarPersona = btnLimpiarPersona;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private Button btnSeleccionarConceptoIngresoVario = new Button();

	public Button getBtnSeleccionarConceptoIngresoVario() {
		return btnSeleccionarConceptoIngresoVario;
	}

	public void setBtnSeleccionarConceptoIngresoVario(Button b) {
		this.btnSeleccionarConceptoIngresoVario = b;
	}

	private HtmlAjaxCommandButton btnLimpiarConceptoIngresoVario = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarConceptoIngresoVario() {
		return btnLimpiarConceptoIngresoVario;
	}

	public void setBtnLimpiarConceptoIngresoVario(HtmlAjaxCommandButton btnLimpiarConceptoIngresoVario) {
		this.btnLimpiarConceptoIngresoVario = btnLimpiarConceptoIngresoVario;
	}

	private List<ImputacionIngresoVario> getListaDelCommunicationImputaciones() {
		return this.getCommunicationCajaBean().getListaImputacionesIngresoVario();
	}

	private void setListaDelCommunicationImputaciones(List<ImputacionIngresoVario> lista) {
		this.getCommunicationCajaBean().setListaImputacionesIngresoVario(lista);
	}

	private ObjectListDataProvider getObjectListDataProviderImputaciones() {
		return this.getLdpImputaciones();
	}

	public String getCurrentRowImputaciones() {
		return trgImputaciones.getRowKey().getRowId();
	}

	public void setCurrentRowImputaciones(int row) {
	}

	public RowKey getSeleccionadoImputaciones() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroupImputaciones");
			rk = this.getLdpImputaciones().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	private Object lastSelectedImputaciones = null;

	public Object getRBSelectedImputaciones() {
		String sv = (String) rbImputaciones.getSelectedValue();
		return sv.equals(lastSelectedImputaciones) ? sv : null;
	}

	public void setRBSelectedImputaciones(Object selected) {
		if(selected != null) {
			lastSelectedImputaciones = selected;
		}
	}

	public void eventoSeleccionConcepto(ValueChangeEvent event) {

	}

	public ABMIngresoVario() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new IngresoVario());
		ep.getObjetos().add(ind++, null); // personaFisica o personaJuridica
		ep.getObjetos().add(ind++, new ConceptoIngresoVario());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		System.out.println("4");
		// CAMBIAR: Verificar el metodo completo.
		int ind = 0;
		IngresoVario ingresoVario = (IngresoVario) this.obtenerObjetoDelElementoPila(ind++, IngresoVario.class);
		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		ConceptoIngresoVario conceptoIngresoVario = (ConceptoIngresoVario) obtenerObjetoDelElementoPila(ind++, ConceptoIngresoVario.class);

		ingresoVario.setFechaEmision(getTextFieldValueDate(this.getTfFechaEmision()));
		ingresoVario.setObservaciones(getTextAreaValue(this.getTaObservaciones()));

		if(persona != null && persona.getIdPersona() == -1)
			persona = null;
		ingresoVario.setPersona(persona);

		ingresoVario.setConceptoIngresoVario(conceptoIngresoVario);

		this.getObjectListDataProviderImputaciones().commitChanges();
		ingresoVario.setListaImputacionIngresos(this.getObjectListDataProviderImputaciones().getList());
		this.setListaDelCommunicationImputaciones(ingresoVario.getListaImputacionIngresos());

		double totalImputaciones = 0;
		for(ImputacionIngresoVario cadaImputacion : ingresoVario.getListaImputacionIngresos()) {
			totalImputaciones += cadaImputacion.getMonto();
		}
		ingresoVario.setValor(totalImputaciones);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, ingresoVario);
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, ingresoVario.getConceptoIngresoVario());
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		System.out.println("5");
		// CAMBIAR: Verificar el metodo completo.
		int ind = 0;
		IngresoVario ingresoVario = (IngresoVario) this.obtenerObjetoDelElementoPila(ind++, IngresoVario.class);
		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		ConceptoIngresoVario conceptoIngresoVario = (ConceptoIngresoVario) obtenerObjetoDelElementoPila(ind++, ConceptoIngresoVario.class);

		if(ingresoVario.getFechaEmision() == null) {
			ingresoVario.setFechaEmision(new Date());
		}
		this.getTfFechaEmision().setText(Conversor.getStringDeFechaCorta(ingresoVario.getFechaEmision()));
		if(persona != null)
			this.getTfPersona().setText(persona.toString());

		if (conceptoIngresoVario != null) {
			this.getTfConcepto().setText(conceptoIngresoVario.getNombre());
		}

		this.getTfEstado().setText(ingresoVario.getEstado().toString());

		if(ingresoVario.getObservaciones() != null)
			this.getTaObservaciones().setText(ingresoVario.getObservaciones().toString());
		// this.getTaObservaciones().setText(ingresoVario.getObservaciones().toString());
		if(ingresoVario.getNumero() != null)
			this.getTfNumero().setText(ingresoVario.getNumero().toString());

		this.getObjectListDataProviderImputaciones().setList(ingresoVario.getListaImputacionIngresos());
		this.setListaDelCommunicationImputaciones(this.getObjectListDataProviderImputaciones().getList());
	}

	public String btnSeleccionarPersonaFisica_action() {
		return navegarParaSeleccionar("AdminPersonaFisica");
	}

	public String btnSeleccionarPersonaJuridica_action() {
		return navegarParaSeleccionar("AdminPersonaJuridica");
	}

	public String btnLimpiarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(1, this.getTfPersona());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarConceptoIngresoVario_action() {
		return navegarParaSeleccionar("AdminConceptoIngresoVario");
	}

	public String btnLimpiarConceptoIngresoVario_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(2, this.getTfConcepto());
			this.setListaDelCommunicationImputaciones(null);
			this.getLdpImputaciones().getList().clear();
			this.getDdTodasRelasDelConceptoSeleccionadoOptions().setOptions(null);
			this.getCommunicationCajaBean().setConceptoSeleccionado(null);
			this.guardarEstadoObjetosUsados();
			this.getCommunicationCajaBean().getMapaRelaConceptoIngresoVarioCuenta().clear();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarCuenta_action() {
		return navegarParaSeleccionar("AdminCuenta");
	}

	public void btnAgregarRelaSeleccionada_action() {
		this.guardarEstadoObjetosUsados();

		RelaConceptoIngresoVarioCuenta locRelaSeleccionada = this.getDDObjectValue(getDdTodasRelasDelConceptoSeleccionado(), this.getCommunicationCajaBean()
				.getMapaRelaConceptoIngresoVarioCuenta());
		List<ImputacionIngresoVario> locListaActual = this.getLdpImputaciones().getList();

		if(locRelaSeleccionada != null) {
			boolean encontrado = false;
			for(ImputacionIngresoVario cadaImputacion : locListaActual) {
				if(cadaImputacion.getCuenta().equals(locRelaSeleccionada.getCuenta())) {
					encontrado = true;
				}
			}

			if(!encontrado) {
				IngresoVario locIngresoVario = this.obtenerObjetoDelElementoPila(0, IngresoVario.class);
				ImputacionIngresoVario nuevaImputacion = new ImputacionIngresoVario();

				nuevaImputacion.setCuenta(locRelaSeleccionada.getCuenta());
				nuevaImputacion.setIngresoVario(locIngresoVario);
				nuevaImputacion.setMonto(locRelaSeleccionada.getMontoPorDefecto());

				List lista = this.getLdpImputaciones().getList();
				lista.add(nuevaImputacion);
				this.getLdpImputaciones().setList(lista);
				// this.getListaDelCommunicationImputaciones().add(nuevaImputacion);
			}
		}

	}

	public String btnQuitarCuenta_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		this.guardarEstadoObjetosUsados();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionadoImputaciones();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProviderImputaciones().getObjects()[index];
					ImputacionIngresoVario locImputacionSeleccionada = (ImputacionIngresoVario) obj;
					IngresoVario locIngreso = (IngresoVario) this.obtenerObjetoDelElementoPila(0, IngresoVario.class);

					boolean obligatoria = false;
					for(RelaConceptoIngresoVarioCuenta cadaRela : locIngreso.getConceptoIngresoVario().getListaRelaConceptoIngresoVarioCuenta()) {
						if(cadaRela.isObligatoria() && cadaRela.getCuenta().equals(locImputacionSeleccionada.getCuenta())) {
							warn("La Imputacion que intenta quitar es obligatoria");
							obligatoria = true;
							break;
						}
					}

					if(!obligatoria) {
						locIngreso.getListaImputacionIngresos().remove(obj);
					}
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String seleccionarConcepto_action() {

		ConceptoIngresoVario locConcepto = (ConceptoIngresoVario) obtenerObjetoDelElementoPila(2);
		// List<ImputacionIngresoVario> locListaImputaciones = this.getObjectListDataProviderImputaciones().getList();
		List<ImputacionIngresoVario> locLista = new ArrayList();// this.getLdpImputaciones().getList();
		Map<String, RelaConceptoIngresoVarioCuenta> locMapaRelas = new HashMap<String, RelaConceptoIngresoVarioCuenta>();
		if(locConcepto != null) {
			for(RelaConceptoIngresoVarioCuenta cadaRelacion : locConcepto.getListaRelaConceptoIngresoVarioCuenta()) {
				boolean yaEsta = false;
				// for(ImputacionIngresoVario cadaImputacion : locLista){
				// if(cadaImputacion.getCuenta().equals(cadaCuenta)){
				// yaEsta = true;
				// }
				// }
				if(cadaRelacion.isObligatoria()) { // if(!yaEsta){
					ImputacionIngresoVario nuevaImputacion = new ImputacionIngresoVario();
					nuevaImputacion.setCuenta(cadaRelacion.getCuenta());
					nuevaImputacion.setMonto(cadaRelacion.getMontoPorDefecto());
					locLista.add(nuevaImputacion);
				}
			}
			this.getObjectListDataProviderImputaciones().setList(new ArrayList(locLista));
			this.setListaDelCommunicationImputaciones(new ArrayList<ImputacionIngresoVario>(locLista));

			for(RelaConceptoIngresoVarioCuenta cadaRela : locConcepto.getListaRelaConceptoIngresoVarioCuenta()) {
				locMapaRelas.put(cadaRela.getCuenta().getNombre(), cadaRela);
			}

		} else {
			this.getObjectListDataProviderImputaciones().setList(new ArrayList());
			this.setListaDelCommunicationImputaciones(new ArrayList<ImputacionIngresoVario>());
		}

		Set<String> locListaRelas = locMapaRelas.keySet();
		Option[] opRelas = new Option[locListaRelas.size() + 1];
		int i = 0;
		opRelas[i++] = new Option("", "");
		for(String cadaRela : locListaRelas) {
			opRelas[i++] = new Option(cadaRela, cadaRela);
		}
		ddTodasRelasDelConceptoSeleccionadoOptions.setOptions(opRelas);

		this.getCommunicationCajaBean().setMapaRelaConceptoIngresoVarioCuenta(locMapaRelas);

		this.getCommunicationCajaBean().setConceptoSeleccionado(locConcepto);
		
		return null;
	}

	public void setSeleccionarConcepto() {

	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		int ind = 0;
		IngresoVario ingresoVario = (IngresoVario) this.obtenerObjetoDelElementoPila(ind++, IngresoVario.class);
		Persona persona = (Persona) this.obtenerObjetoDelElementoPila(ind++, Persona.class);
		ConceptoIngresoVario conceptoIngresoVario = (ConceptoIngresoVario) obtenerObjetoDelElementoPila(ind++, ConceptoIngresoVario.class);

		if(pObject instanceof ConceptoIngresoVario) {
			conceptoIngresoVario = (ConceptoIngresoVario) pObject;

			// pongo conceptoIngresoVario.valorPorDefecto en ingresoVario.valor
			ingresoVario.setValor(conceptoIngresoVario.getValorPorDefecto());

			List<ImputacionIngresoVario> locListaNuevasImputaciones = new ArrayList<ImputacionIngresoVario>();
			for(RelaConceptoIngresoVarioCuenta cadaRelacion : conceptoIngresoVario.getListaRelaConceptoIngresoVarioCuenta()) {
				ImputacionIngresoVario nuevaImputacion = new ImputacionIngresoVario();
				nuevaImputacion.setCuenta(cadaRelacion.getCuenta());
				nuevaImputacion.setIngresoVario(ingresoVario);
				locListaNuevasImputaciones.add(nuevaImputacion);
			}
			ingresoVario.setListaImputacionIngresos(locListaNuevasImputaciones);
			this.getLdpImputaciones().setList(locListaNuevasImputaciones);
			this.setListaDelCommunicationImputaciones(locListaNuevasImputaciones);

			this.getCommunicationCajaBean().setConceptoSeleccionado(conceptoIngresoVario);

			this.getElementoPila().getObjetos().set(0, ingresoVario);
			this.getElementoPila().getObjetos().set(2, conceptoIngresoVario);
		}

		if(pObject instanceof Cuenta) {
			Cuenta locCuenta = (Cuenta) pObject;

			ImputacionIngresoVario locNuevaImputacion = new ImputacionIngresoVario();
			locNuevaImputacion.setCuenta(locCuenta);
			locNuevaImputacion.setIngresoVario(ingresoVario);
			// ingresoVario.getListaImputacionIngresos().add(locNuevaImputacion);

			for(RelaConceptoIngresoVarioCuenta cadaRela : conceptoIngresoVario.getListaRelaConceptoIngresoVarioCuenta()) {
				if(locCuenta.equals(cadaRela.getCuenta())) {
					locNuevaImputacion.setMonto(cadaRela.getMontoPorDefecto());
					break;
				}
			}

			this.getObjectListDataProviderImputaciones().getList().add(locNuevaImputacion);
			// this.getListaDelCommunicationImputaciones().add(locNuevaImputacion);

			this.getElementoPila().getObjetos().set(0, ingresoVario);
		}

		if(pObject instanceof Persona) {
			persona = (Persona) pObject;
			ingresoVario.setPersona(persona);

			this.getElementoPila().getObjetos().set(0, ingresoVario);
			this.getElementoPila().getObjetos().set(1, persona);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		IngresoVario ingresoVario = (IngresoVario) pObject;
		Persona persona = ingresoVario.getPersona();
		ConceptoIngresoVario conceptoIngresoVario = ingresoVario.getConceptoIngresoVario();

		this.getObjectListDataProviderImputaciones().setList(ingresoVario.getListaImputacionIngresos());
		this.setListaDelCommunicationImputaciones(ingresoVario.getListaImputacionIngresos());

		this.getCommunicationCajaBean().setConceptoSeleccionado(conceptoIngresoVario);

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, ingresoVario);
		this.getElementoPila().getObjetos().set(ind++, persona);
		this.getElementoPila().getObjetos().set(ind++, conceptoIngresoVario);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMIngresoVario";
	}

	public void setPersonaAutocompletar(String pId, String pIdSubSesion) throws Exception {
		Long id = Long.parseLong(pId);
		Persona locPersona = null;

		try {
			locPersona = (Persona) this.getComunicationBean().getRemoteSystemPersonaFisica().getPersonaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		this.setIdSubSesion(Long.parseLong(pIdSubSesion));
		this.getElementoPila().getObjetos().set(1, locPersona);
	}
	
	public void setConceptoAutocompletar(String pId, String pIdSubSesion) throws Exception {
		Long id = Long.parseLong(pId);
		ConceptoIngresoVario locConcepto = null;

		try {
			locConcepto = (ConceptoIngresoVario) this.getCommunicationContabilidadBean().getRemoteSystemAdministracionIngresos().getConceptoIngresoVarioByID(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		this.setIdSubSesion(Long.parseLong(pIdSubSesion));
		this.getElementoPila().getObjetos().set(2, locConcepto);
	}

	public boolean isHayPersona() {
		Persona locPersona = (Persona) this.obtenerObjetoDelElementoPila(1);
		return(locPersona != null && locPersona.getIdPersona() != -1);
	}
	
	public boolean isHayConcepto() {
		ConceptoIngresoVario locConcepto = (ConceptoIngresoVario) this.obtenerObjetoDelElementoPila(2);
		return(locConcepto != null && locConcepto.getIdConceptoIngresoVario() != -1);
	}

	@Override
	public String getNombreBean() {
		return "#{comunes$ABMIngresoVario$ABMIngresoVario}";
	}

	@Override
	public long getSerialVersionUID() {
		return IngresoVario.serialVersionUID;
	}
}