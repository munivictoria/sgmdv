/*
 * ABMManzana.java
 *
 * Created on 2 de noviembre de 2006, 13:24
 * Copyright Trascender SRL
 */
package muni.catastro.ABMManzana;

import java.util.ArrayList;
import java.util.List;

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
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaManzana;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.presentacion.abstracts.ABMPageBean;
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
public class ABMManzana extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		if (this.getListaDelCommunication2() != null) {
			this.getObjectListDataProvider2().setList(this.getListaDelCommunication2());
		}
	}

	private ObjectListDataProvider getObjectListDataProvider2() {
		return this.getLdpZonas();
	}

	private List getListaDelCommunication2() {
		return this.getComunicationCatastroBean().getListaZonasManzana();
	}

	private void setListaDelCommunication2(List lista) {
		this.getComunicationCatastroBean().setListaZonasManzana(lista);
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

	private Label label6 = new Label();
	private TableRowGroup tableRowGroup2 = new TableRowGroup();
	private ObjectListDataProvider ldpZonas = new ObjectListDataProvider();
	private TableColumn tableColumn3 = new TableColumn();
	private TableColumn tableColumn4 = new TableColumn();
	private TableColumn tableColumn5 = new TableColumn();
	private RadioButton radioButton2 = new RadioButton();
	private StaticText staticText5 = new StaticText();
	private StaticText staticText6 = new StaticText();
	private PanelGroup groupPanel2 = new PanelGroup();
	private Button btnAgregarZona = new Button();
	 protected HtmlAjaxCommandButton btnQuitarZona = new HtmlAjaxCommandButton();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label label6) {
		this.label6 = label6;
	}

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
		this.tableRowGroup2 = tableRowGroup2;
	}

	public ObjectListDataProvider getLdpZonas() {
		return ldpZonas;
	}

	public void setLdpZonas(ObjectListDataProvider ldpZonas) {
		this.ldpZonas = ldpZonas;
	}

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tableColumn3) {
		this.tableColumn3 = tableColumn3;
	}

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tableColumn4) {
		this.tableColumn4 = tableColumn4;
	}

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tableColumn5) {
		this.tableColumn5 = tableColumn5;
	}

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton radioButton2) {
		this.radioButton2 = radioButton2;
	}

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText staticText5) {
		this.staticText5 = staticText5;
	}

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText staticText6) {
		this.staticText6 = staticText6;
	}

	public PanelGroup getGroupPanel2() {
		return groupPanel2;
	}

	public void setGroupPanel2(PanelGroup groupPanel2) {
		this.groupPanel2 = groupPanel2;
	}

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

	private TextField tfNroManzana = new TextField();

	public TextField getTfNroManzana() {
		return tfNroManzana;
	}

	public void setTfNroManzana(TextField tf) {
		this.tfNroManzana = tf;
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

	private ObjectListDataProvider ldpCuadrasPorManzana = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpCuadrasPorManzana() {
		return ldpCuadrasPorManzana;
	}

	public void setLdpCuadrasPorManzana(ObjectListDataProvider oldp) {
		this.ldpCuadrasPorManzana = oldp;
	}

	private ObjectListDataProvider ldpAtributosManzana = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpAtributosManzana() {
		return ldpAtributosManzana;
	}

	public void setLdpAtributosManzana(ObjectListDataProvider ldpAtributosManzana) {
		this.ldpAtributosManzana = ldpAtributosManzana;
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

	private Button btnAgregar = new Button();

	public Button getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(Button b) {
		this.btnAgregar = b;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	 protected HtmlAjaxCommandButton btnQuitar = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(HtmlAjaxCommandButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	private RadioButton radioButton1 = new RadioButton();

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton rb) {
		this.radioButton1 = rb;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	/*
	 * private PanelAtributoDinamico panelAtributoDinamico = new
	 * PanelAtributoDinamico();
	 * 
	 * public PanelAtributoDinamico getPanelAtributoDinamico() { return
	 * panelAtributoDinamico; }
	 * 
	 * public void setPanelAtributoDinamico(PanelAtributoDinamico
	 * panelAtributoDinamico) { this.panelAtributoDinamico =
	 * panelAtributoDinamico; }
	 */

	 protected HtmlAjaxCommandButton btnQuitarTodos = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnQuitarTodos() {
		return btnQuitarTodos;
	}

	public void setBtnQuitarTodos(HtmlAjaxCommandButton btnQuitarTodos) {
		this.btnQuitarTodos = btnQuitarTodos;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMManzana() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Manzana());
		// ep.getObjetos().add(ind++, new Zona());
		ep.getObjetos().add(ind++, new ArrayList()); // cuadras
		ep.getObjetos().add(ind++, new ArrayList()); // AtributosDinamicos

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		Manzana manzana = (Manzana) this.obtenerObjetoDelElementoPila(ind++, Manzana.class);
		// Zona zona = (Zona) this.obtenerObjetoDelElementoPila(ind++,
		// Zona.class);
		ArrayList cuadras = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		manzana.setNombre(this.getTextFieldValue(getTfNombre()));
		manzana.setNroManzana(this.getTextFieldValueInteger(getTfNroManzana()));

		// if (zona.getIdZona() == -1) zona = null;
		// manzana.setZona(zona);

		this.getObjectListDataProvider().commitChanges();
		cuadras = (ArrayList) this.getObjectListDataProvider().getList();

		// if (cuadras.isEmpty()) {
		// cuadras = null;
		// }

		this.setListaDelCommunication(cuadras);

		// if (this.getObjectListDataProviderAtributosDinamicos() != null) {
		//
		// this.getObjectListDataProviderAtributosDinamicos().commitChanges();
		// //this.getObjectListDataProviderAtributosDinamicos().setObjectType(AtributoDinamico.class);
		// }
		// atributosDinamicos = (ArrayList)
		// this.getObjectListDataProviderAtributosDinamicos().getList();
		// this.setListaDelCommunicationAtributosDinamicos(atributosDinamicos);

		// manzana.getListaCuadrasDelimitantes().clear();
		// if (!cuadras.isEmpty())
		// manzana.getListaCuadrasDelimitantes().addAll(cuadras);

		manzana.setListaCuadrasDelimitantes(cuadras);

		atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		manzana.setListaAtributosDinamicos(atributosDinamicos);

		if (this.getObjectListDataProvider2().getList() != null && this.getObjectListDataProvider2().getList().size() > 0) {
			this.getObjectListDataProvider2().commitChanges();
		}
		List listaZonas = (List) this.getObjectListDataProvider2().getList();
		if (listaZonas.size() <= 0) {
			System.out.println("-------- lista zonas null o vacio");
		}
		manzana.setListaAsociacionParcelaManzana(listaZonas);
		this.setListaDelCommunication2(listaZonas);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, manzana);
		// this.getElementoPila().getObjetos().set(ind++, zona);
		this.getElementoPila().getObjetos().set(ind++, cuadras);
		this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		this.acomodarSeleccionado();

		// if (this.getListaDelCommunicationAtributosDinamicos() == null) {
		// try {
		// System.out.println("---ENTRO EN EL IF---");
		// if
		// (this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(Manzana.serialVersionUID,
		// null) != null) {
		// this.getObjectListDataProviderAtributosDinamicos().setList((ArrayList)
		// this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(Manzana.serialVersionUID,
		// null));
		// atributosDinamicos = (ArrayList)
		// this.getObjectListDataProviderAtributosDinamicos().getList();
		// this.getElementoPila().getObjetos().set(2, atributosDinamicos);
		// }
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// }
		// }

		int ind = 0;
		Manzana manzana = (Manzana) this.obtenerObjetoDelElementoPila(ind++, Manzana.class);
		// zona = (Zona) this.obtenerObjetoDelElementoPila(ind++, Zona.class);
		ArrayList cuadras = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		this.getTfNombre().setText(manzana.getNombre());
		if (manzana.getNroManzana() != null) {
			this.getTfNroManzana().setText(manzana.getNroManzana().toString());
		}
		// this.getTfZona().setText(zona.toString());
		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{catastro$ABMManzana$ABMManzana}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);
		this.getObjectListDataProvider().setList(cuadras);

		// this.getObjectListDataProviderAtributosDinamicos().setList(atributosDinamicos);
		// this.getObjectListDataProviderAtributosDinamicos().setObjectType(AtributoDinamico.class);

		this.getObjectListDataProvider2().setList(manzana.getListaAsociacionParcelaManzana());
		this.setListaDelCommunication2(new ArrayList(manzana.getListaAsociacionParcelaManzana()));

		this.setListaDelCommunication(cuadras);
		this.setListaDelCommunicationAtributosDinamicos(atributosDinamicos);
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpCuadrasPorManzana();
	}

	private ObjectListDataProvider getObjectListDataProviderAtributosDinamicos() {
		this.getLdpAtributosManzana().setObjectType(AtributoDinamico.class);
		return this.getLdpAtributosManzana();
	}

	private ArrayList getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaCuadrasPorManzana();
	}

	private void setListaDelCommunication(ArrayList lista) {
		this.getComunicationCatastroBean().setListaCuadrasPorManzana(lista);
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		this.getComunicationCatastroBean().setListaAtributosDinamicosManzana(lista);
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
		if (selected != null) {
			lastSelected = selected;
		}
	}

	private Object lastSelected2 = null;

	public Object getRBSelected2() {
		String sv = (String) radioButton2.getSelectedValue();
		return sv.equals(lastSelected2) ? sv : null;
	}

	public void setRBSelected2(Object selected) {
		if (selected != null) {
			lastSelected2 = selected;
		}
	}

	public String getCurrentRow2() {
		return tableRowGroup2.getRowKey().getRowId();
	}

	public void setCurrentRow2(int row) {
	}

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

	public String btnAgregar_action() {
		return navegarParaSeleccionar("AdminCuadra");
	}

	public String btnQuitar_action() {
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

	public String btnQuitarTodos_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			try {
				this.getListaDelCommunication().clear();
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnAgregarZona_action() {
		return navegarParaSeleccionar("AdminZona");
	}

	public String btnQuitarZona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		Manzana manzana = (Manzana) this.obtenerObjetoDelElementoPila(0, Manzana.class);

		if (ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado2();
				if (rk != null) {
					int index = getNroFila(rk.toString());

					Object obj = this.getObjectListDataProvider2().getObjects()[index];

					AsociacionParcelaManzana asociacion = (AsociacionParcelaManzana) obj;

					if (this.getObjectListDataProvider2().getList().size() > 0) {
						this.getObjectListDataProvider2().commitChanges();
					}

					manzana.removeZona(asociacion);
					this.setListaDelCommunication2(manzana.getListaAsociacionParcelaManzana());
					this.getObjectListDataProvider2().setList(this.getListaDelCommunication2());
					this.getElementoPila().getObjetos().set(0, manzana);
				}
			} catch (Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
		}
		return retorno;
	}

	public RowKey getSeleccionado2() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getLdpZonas().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}

	@Override
	protected String getNombrePagina() {
		return "Manzana";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMManzana";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof Cuadra) {
			Cuadra nuevaCuadra = (Cuadra) pObject;
			ArrayList cuadras = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);

			Cuadra deLaTabla = null;
			boolean esta = false;
			int i = 0;
			while (i < cuadras.size() && !esta) {
				deLaTabla = (Cuadra) cuadras.get(i++);
				esta = (deLaTabla.getIdCuadra() == nuevaCuadra.getIdCuadra());
			}
			if (!esta) {
				cuadras.add(nuevaCuadra);
			} else {
				warn("La Cuadra que intenta agregar ya se encuentra en la lista.");
			}
			this.getElementoPila().getObjetos().set(1, cuadras);

			this.getRequestBean1().setObjetoSeleccion(null);
		}

		if (pObject instanceof Zona) {
			Manzana manzana = (Manzana) this.obtenerObjetoDelElementoPila(0, Manzana.class);
			Zona nuevaZona = (Zona) pObject;
			AsociacionParcelaManzana asociacionExistente = null;

			boolean esta = false;
			int i = 0;
			while (i < manzana.getListaAsociacionParcelaManzana().size() && !esta) {
				asociacionExistente = manzana.getListaAsociacionParcelaManzana().get(i);
				esta = (asociacionExistente.getZona().equals(nuevaZona));
				i++;
			}
			if (!esta) {
				manzana.addZona(nuevaZona);
			} else {
				warn("La Zona que intenta agregar ya se encuentra en la lista.");
			}
			this.getElementoPila().getObjetos().set(0, manzana);
			this.getRequestBean1().setObjetoSeleccion(null);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Manzana manzana = (Manzana) pObject;
		ArrayList cuadras = null;
		ArrayList atributosDinamicos = null;

		try {
			long idManzana = manzana.getIdManzana();
			this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(this.getSessionBean1().getLlave());
			manzana = this.getComunicationCatastroBean().getRemoteSystemInformacionGeografica().getManzanaPorId(idManzana);
		} catch (Exception ex) {
			error("No se pudo recuperar la Manzana: " + ex.getMessage());
		}

		List listaZona = new ArrayList();

		try {
			this.setListaDelCommunication2(manzana.getListaAsociacionParcelaManzana());
			this.getLdpZonas().setList(this.getListaDelCommunication2());
			listaZona = this.getLdpZonas().getList();
		} catch (Exception ex) {
			error("No se pudieron obtener las Zonas de la Manzana: " + ex.getMessage());
		}

		// zona = manzana.getZona();
		cuadras = new ArrayList(manzana.getListaCuadrasDelimitantes());
		if (manzana.getListaAtributosDinamicos() != null) {
			try {
				atributosDinamicos = (ArrayList) this.getComunicationBean().getRemoteSystemParametro()
						.getAtributosPorRecurso(Manzana.serialVersionUID, manzana.getListaAtributosDinamicos(), null);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

//		for (Object cadaAtributo : atributosDinamicos) {
//			System.out.println("Objeto" + ((AtributoDinamico) cadaAtributo).getNombre());
//		}

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, manzana);
		// this.getElementoPila().getObjetos().set(ind++, zona);
		this.getElementoPila().getObjetos().set(ind++, cuadras);
		this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
	}
	
	@Override
	public long getSerialVersionUID() {
		return Manzana.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMManzana$ABMManzana}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Manzana locManzana = this.obtenerObjetoDelElementoPila(0, Manzana.class);
		this.getTablaLogs().getLdpLogs().setList(locManzana.getListaLogsAuditoria());
	}
}