/*
 * ABMLocalComercial.java
 *
 * Created on 24 de octubre de 2006, 10:40
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpSHPS.ABMLocalComercial;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.convert.DateTimeConverter;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
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
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.habilitaciones.recurso.persistent.shps.InspeccionComercial;
import com.trascender.habilitaciones.recurso.persistent.shps.LocalComercial;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains
 * component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers
 * where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMLocalComercial extends ABMPageBean {

	// CAMBIAR: Constantes que varian segun la pagina.
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Modificar Local Comercial";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "ModificarLocalComercial";

	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.

	/**
	 * <p>
	 * Automatically managed component initialization. <strong>WARNING:</strong>
	 * This method is automatically generated, so any user-specified code
	 * inserted here is subject to being replaced.
	 * </p>
	 */
	@Override
	protected void _init() throws Exception {

		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		dateTimeConverter1.setTimeStyle("short");

	}

	private TextField tfNumeroInscripcion = new TextField();

	public TextField getTfNumeroInscripcion() {
		return tfNumeroInscripcion;
	}

	public void setTfNumeroInscripcion(TextField tf) {
		this.tfNumeroInscripcion = tf;
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

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private TextField tfParcela = new TextField();

	public TextField getTfParcela() {
		return tfParcela;
	}

	public void setTfParcela(TextField tf) {
		this.tfParcela = tf;
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

	private TextField tfTelefono = new TextField();

	public TextField getTfTelefono() {
		return tfTelefono;
	}

	public void setTfTelefono(TextField tf) {
		this.tfTelefono = tf;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label l) {
		this.label6 = l;
	}

	private TextField tfSuperficieCubierta = new TextField();

	public TextField getTfSuperficieCubierta() {
		return tfSuperficieCubierta;
	}

	public void setTfSuperficieCubierta(TextField tf) {
		this.tfSuperficieCubierta = tf;
	}

	private TextField tfSuperficieSemiCubierta = new TextField();

	public TextField getTfSuperficieSemiCubierta() {
		return tfSuperficieSemiCubierta;
	}

	public void setTfSuperficieSemiCubierta(TextField tf) {
		this.tfSuperficieSemiCubierta = tf;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private Label label7 = new Label();

	public Label getLabel7() {
		return label7;
	}

	public void setLabel7(Label l) {
		this.label7 = l;
	}

	private Table table1 = new Table();

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	private Button btnAgregarInspeccion = new Button();

	public Button getBtnAgregarInspeccion() {
		return btnAgregarInspeccion;
	}

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText st) {
		this.staticText6 = st;
	}

	private Button btnConsultarInspeccion = new Button();

	public Button getBtnConsultarInspeccion() {
		return btnConsultarInspeccion;
	}

	public void setBtnConsultarInspeccion(Button b) {
		this.btnConsultarInspeccion = b;
	}

	private ObjectListDataProvider ldpInspeccionLocalComercial = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpInspeccionLocalComercial() {
		return ldpInspeccionLocalComercial;
	}

	public void setLdpInspeccionLocalComercial(ObjectListDataProvider oldp) {
		this.ldpInspeccionLocalComercial = oldp;
	}

	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setBtnAgregarInspeccion(Button b) {
        this.btnAgregarInspeccion = b;
    }
    protected HtmlAjaxCommandButton btnQuitarInspeccion = new HtmlAjaxCommandButton();

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}
    public HtmlAjaxCommandButton getBtnQuitarInspeccion() {
		return btnQuitarInspeccion;
	}

	private TableColumn tableColumn3 = new TableColumn();

	public void setBtnQuitarInspeccion(HtmlAjaxCommandButton btnQuitarInspeccion) {
		this.btnQuitarInspeccion = btnQuitarInspeccion;
	}
	private StaticText staticText6 = new StaticText();

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tc) {
		this.tableColumn3 = tc;
	}

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	private TableColumn tableColumn4 = new TableColumn();

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tc) {
		this.tableColumn4 = tc;
	}

	private StaticText staticText7 = new StaticText();

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText st) {
		this.staticText7 = st;
	}

	private RadioButton radioButton1 = new RadioButton();

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton rb) {
		this.radioButton1 = rb;
	}

	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dtc) {
		this.dateTimeConverter1 = dtc;
	}

	private Button btnSeleccionarParcela = new Button();

	public Button getBtnSeleccionarParcela() {
		return btnSeleccionarParcela;
	}

	public void setBtnSeleccionarParcela(Button b) {
		this.btnSeleccionarParcela = b;
	}

	private HtmlAjaxCommandButton btnLimpiarParcela = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarParcela() {
		return btnLimpiarParcela;
	}

	public void setBtnLimpiarParcela(HtmlAjaxCommandButton btnLimpiarParcela) {
		this.btnLimpiarParcela = btnLimpiarParcela;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMLocalComercial() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new LocalComercial());
		ep.getObjetos().add(ind++, new Parcela());
		ep.getObjetos().add(ind++, new ArrayList()); // Tabla 1: Inspecciones
														// Comerciales

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		LocalComercial localComercial = (LocalComercial) this.obtenerObjetoDelElementoPila(ind++, LocalComercial.class);
		Parcela parcela = (Parcela) this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
		ArrayList inspeccionesComerciales = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		localComercial.setNumeroInscripcion(getTextFieldValue(this.getTfNumeroInscripcion()));
		localComercial.setSuperficieCubiertaAfectada(getTextFieldValueDouble(this.getTfSuperficieCubierta()));
		localComercial.setSuperficieSemicubiertaAfectada(getTextFieldValueDouble(this.getTfSuperficieSemiCubierta()));
		localComercial.setTelefono(getTextFieldValue(this.getTfTelefono()));
		localComercial.setDescripcion(getTextAreaValue(this.getTaDescripcion()));

		if (parcela.getIdParcela() == -1) {
			parcela = null;
		}
		localComercial.setParcela(parcela);

		this.getObjectListDataProvider().commitChanges();
		inspeccionesComerciales = (ArrayList) this.getObjectListDataProvider().getList();

		if (inspeccionesComerciales.isEmpty()) {
			inspeccionesComerciales = null;
		}
		if (localComercial.getListaInspeccionesComerciales() != null) {
			localComercial.getListaInspeccionesComerciales().clear();
			if (inspeccionesComerciales != null) {
				localComercial.getListaInspeccionesComerciales().addAll(inspeccionesComerciales);
			}
		}
		this.setListaDelCommunication(inspeccionesComerciales);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, localComercial);
		this.getElementoPila().getObjetos().set(ind++, parcela);
		this.getElementoPila().getObjetos().set(ind++, inspeccionesComerciales);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		int ind = 0;
		LocalComercial localComercial = null;
		Parcela parcela = null;
		ArrayList inspeccionesComerciales = null;

		if (this.getRequestBean1().getRespuestaABM() != null) {
			Object respuesta = this.getRequestBean1().getRespuestaABM();
			int posicionEP = -1;
			ArrayList arrayListCorrespondiente = null;
			if (respuesta instanceof InspeccionComercial) {
				posicionEP = 2;
			}
			if (posicionEP != -1) {
				arrayListCorrespondiente = (ArrayList) this.obtenerObjetoDelElementoPila(posicionEP, ArrayList.class);

				InspeccionComercial inspeccionComercialNueva = (InspeccionComercial) respuesta;
				boolean existe = false;
				for (Iterator it = arrayListCorrespondiente.iterator(); it.hasNext();) {
					InspeccionComercial locInspeccionComercial = (InspeccionComercial) it.next();

					if (locInspeccionComercial.getInspector().equals(inspeccionComercialNueva.getInspector())
							&& locInspeccionComercial.getEstado() == (inspeccionComercialNueva.getEstado())
							&& (Conversor.getStringDeFechaCorta(locInspeccionComercial.getFecha())).equals(Conversor.getStringDeFechaCorta(inspeccionComercialNueva.getFecha()))) {
						existe = true;
					}
				}
				if (!existe) {
					arrayListCorrespondiente.add(inspeccionComercialNueva);
					inspeccionComercialNueva.setLocalComercial(localComercial);
					this.getElementoPila().getObjetos().set(posicionEP, arrayListCorrespondiente);
				} else {
					warn("La inspecci\363n comercial ya existe.");
				}
			}
		}

		ind = 0;
		localComercial = (LocalComercial) this.obtenerObjetoDelElementoPila(ind++, LocalComercial.class);
		parcela = (Parcela) this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
		inspeccionesComerciales = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		this.getTfNumeroInscripcion().setText(localComercial.getNumeroInscripcion());
		if (localComercial.getSuperficieCubiertaAfectada() != null) {
			this.getTfSuperficieCubierta().setText(Conversor.getStringDeDouble(localComercial.getSuperficieCubiertaAfectada()));
		}
		if (localComercial.getSuperficieSemicubiertaAfectada() != null) {
			this.getTfSuperficieSemiCubierta().setText(Conversor.getStringDeDouble(localComercial.getSuperficieSemicubiertaAfectada()));
		}
		this.getTfTelefono().setText(localComercial.getTelefono());
		this.getTaDescripcion().setText(localComercial.getDescripcion());

		if (parcela != null && parcela.getIdParcela() != -1) {
			this.getTfParcela().setText(parcela.toString());
		}

		this.getObjectListDataProvider().setList(inspeccionesComerciales);
		this.setListaDelCommunication(inspeccionesComerciales);
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpInspeccionLocalComercial();
	}

	private List getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationHabilitacionesBean().getListaInspeccionesLocalComercial();
	}

	private void setListaDelCommunication(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationHabilitacionesBean().setListaInspeccionesLocalComercial((ArrayList) lista);
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed"
	// desc="Metodos estaticos de la pagina">
	// <editor-fold defaultstate="collapsed"
	// desc="Metodos para seleccionar RaddioButton">
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
		if (selected != null) {
			lastSelected = selected;
		}
	}

	// </editor-fold>
	// <editor-fold defaultstate="collapsed"
	// desc="Metodos para seleccionar la fila recientemente seleccionada">
	private RowKey rowKeySeleccionado = null;

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	public void guardarOrdenamiento() {
		this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
	}

	public void setearOrdenamiento() {
		this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
	}

	public Long getPosicionEnTabla(RowKey rk) {
		long inicioPagina = 0;
		long posicionGlobal = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
		long cantRegistros = this.getTableRowGroup1().getRowCount();
		boolean encontrado = false;

		if (rk != null) {
			while (!encontrado && inicioPagina < cantRegistros) {
				this.getTableRowGroup1().setFirst((int) inicioPagina);
				posicionEnPagina = 0;
				while (!encontrado && posicionEnPagina < cantRegistrosPorPagina) {
					encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int) posicionEnPagina].equals(rk);
					if (!encontrado) {
						posicionEnPagina++;
						posicionGlobal++;
					}
				}
				if (!encontrado) {
					inicioPagina += cantRegistrosPorPagina;
				}
			}
		}
		return new Long(posicionGlobal);
	}

	public RowKey getRowKeyAsociado(Long posicionEnTabla) {
		RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];
		return rk;
	}

	public void seleccionarFila(Long posicionGlobal) {
		long cantFilas = this.getTableRowGroup1().getRowCount();

		if (cantFilas > 0) {
			// si hay al menos una fila
			if (posicionGlobal.longValue() >= cantFilas) {
				// si elimine la ultima fila, me posiciono en la anterior
				posicionGlobal = new Long(cantFilas - 1);
			}
			;

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

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}

	// </editor-fold>

	// </editor-fold>
	public String btnSeleccionarParcela_action() {
		return navegarParaSeleccionar("AdminParcela");
	}

	public String btnLimpiarParcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(1, this.getTfParcela());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarInspeccion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {

			// APLICAR LOGICA AQUI...

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AgregarInspeccionComercial";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarInspeccion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					this.getListaDelCommunication().remove(obj);
				}
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnConsultarInspeccion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		Object inspeccionSeleccionada = null;

		if (ultimo) {
			RowKey rk = null;

			// APLICAR LOGICA AQUI...
			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());
					inspeccionSeleccionada = this.getObjectListDataProvider().getObjects()[index];
				}
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			if (inspeccionSeleccionada != null) {
				this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
				this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(0, LocalComercial.class));
				this.getRequestBean1().setObjetoABM(inspeccionSeleccionada);
				retorno = "ConsultarInspeccionComercial";
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMLocalComercial";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {

		if (pObject instanceof Parcela) {
			Parcela locParcela = (Parcela) pObject;
			this.getElementoPila().getObjetos().set(1, pObject);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		LocalComercial localComercial = null;
		Parcela parcela = null;
		ArrayList inspeccionesComerciales = null;

		localComercial = (LocalComercial) pObject;
		long idLocalComercial = localComercial.getIdLocalComercial();
		Long idParcela = localComercial.getIdParcela();

		try {
			this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().setLlave(this.getSessionBean1().getLlave());
			// inspeccionesComerciales = new ArrayList(
			// this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().findListaInspeccionesComerciales(null,
			// null, localComercial, null) );
			localComercial = this.getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().getLocalComercialPorId(idLocalComercial);
		} catch (Exception ex) {
			log(CASO_NAVEGACION + "_BuscarLocalComercialPorIdError:", ex);
			error(NOMBRE_PAGINA + " - No se pudo recuperar el Local Comercial: " + ex.getMessage());
		}
		if (localComercial.getListaInspeccionesComerciales() != null) {
			inspeccionesComerciales = new ArrayList(localComercial.getListaInspeccionesComerciales());
			this.setListaDelCommunication(inspeccionesComerciales);
			this.getObjectListDataProvider().setList(inspeccionesComerciales);
		}

		try {
			this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
			parcela = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(idParcela.longValue());
		} catch (Exception ex) {
			log(CASO_NAVEGACION + "_BuscarParcelaPorIdError:", ex);
			error(NOMBRE_PAGINA + " - No se pudo recuperar la Parcela vinculada al Local Comercial: " + ex.getMessage());
		}

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, localComercial);
		this.getElementoPila().getObjetos().set(ind++, parcela);
		this.getElementoPila().getObjetos().set(ind++, inspeccionesComerciales);
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		LocalComercial locLocal = this.obtenerObjetoDelElementoPila(0, LocalComercial.class);
		this.getTablaLogs().getLdpLogs().setList(locLocal.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return LocalComercial.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial}";
	}
	
	public void setParcelaAutocompletar(String pId, String pIdSubSesion) throws Exception {
		Long id = Long.parseLong(pId);
		Parcela locParcela = null;

		try {
			locParcela = (Parcela) this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		this.setIdSubSesion(Long.parseLong(pIdSubSesion));
		this.getElementoPila().getObjetos().set(1, locParcela);
	}

	public boolean isHayParcela() {
		Parcela locParcela = (Parcela) this.obtenerObjetoDelElementoPila(1);
		return(locParcela != null && locParcela.getIdParcela() != -1);
	}
}
