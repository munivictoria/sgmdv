/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.compras.ABMAutorizacionSolicitudSuministro;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.persistent.suministros.ReglaFirmaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorSolicitudSuministro;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author Fer Luca
 */
public class ABMReglaFirmaSolicitudSuministro extends ABMPageBean {

	private Label lblUsuarios = new Label();
	private Label lblListaUsuarios = new Label();
	private Label lblOrden = new Label();
	private Label lblEstado = new Label();
	private TextField tfOrden = new TextField();
	private Table table1 = new Table();
	private DropDown ddEstado = new DropDown();
	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();
	private DropDown ddUsuarios = new DropDown();
	private SingleSelectOptionsList ddUsuariosDefaultOptions = new SingleSelectOptionsList();
	private Button btnAgregar = new Button();
	protected HtmlAjaxCommandButton btnQuitar = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarTodos = new HtmlAjaxCommandButton();
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private ObjectListDataProvider ldpUsuariosAutorizadores = new ObjectListDataProvider();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tcNombre = new TableColumn();
	private RadioButton radioButton1 = new RadioButton();
	private StaticText stUsuario = new StaticText();
	private PanelGroup groupPanel1 = new PanelGroup();
	private StaticText stSeparador1 = new StaticText();
	private Label lblUrgente = new Label();
	private Checkbox cbUrgente = new Checkbox();

	public Label getLblUrgente() {
		return lblUrgente;
	}

	public void setLblUrgente(Label lblUrgente) {
		this.lblUrgente = lblUrgente;
	}

	public Checkbox getCbUrgente() {
		return cbUrgente;
	}

	public void setCbUrgente(Checkbox cbUrgente) {
		this.cbUrgente = cbUrgente;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public StaticText getStSeparador1() {
		return stSeparador1;
	}

	public void setStSeparador1(StaticText stSeparador1) {
		this.stSeparador1 = stSeparador1;
	}

	public ObjectListDataProvider getLdpUsuariosAutorizadores() {
		return ldpUsuariosAutorizadores;
	}

	public void setLdpUsuariosAutorizadores(ObjectListDataProvider ldpUsuariosAutorizadores) {
		this.ldpUsuariosAutorizadores = ldpUsuariosAutorizadores;
	}

	public Button getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(Button btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public HtmlAjaxCommandButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(HtmlAjaxCommandButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodos() {
		return btnQuitarTodos;
	}

	public void setBtnQuitarTodos(HtmlAjaxCommandButton btnQuitarTodos) {
		this.btnQuitarTodos = btnQuitarTodos;
	}

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

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	public Label getLblListaUsuarios() {
		return lblListaUsuarios;
	}

	public void setLblListaUsuarios(Label lblListaUsuarios) {
		this.lblListaUsuarios = lblListaUsuarios;
	}

	public Label getLblOrden() {
		return lblOrden;
	}

	public void setLblOrden(Label lblOrden) {
		this.lblOrden = lblOrden;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public StaticText getStUsuario() {
		return stUsuario;
	}

	public void setStUsuario(StaticText stUsuario) {
		this.stUsuario = stUsuario;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table table1) {
		this.table1 = table1;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public TableColumn getTcNombre() {
		return tcNombre;
	}

	public void setTcNombre(TableColumn tcNombre) {
		this.tcNombre = tcNombre;
	}

	public TextField getTfOrden() {
		return tfOrden;
	}

	public void setTfOrden(TextField tfOrden) {
		this.tfOrden = tfOrden;
	}

	public DropDown getDdUsuarios() {
		return ddUsuarios;
	}

	public void setDdUsuarios(DropDown ddUsuarios) {
		this.ddUsuarios = ddUsuarios;
	}

	public SingleSelectOptionsList getDdUsuariosDefaultOptions() {
		return ddUsuariosDefaultOptions;
	}

	public void setDdUsuariosDefaultOptions(SingleSelectOptionsList ddUsuariosDefaultOptions) {
		this.ddUsuariosDefaultOptions = ddUsuariosDefaultOptions;
	}

	public Label getLblUsuarios() {
		return lblUsuarios;
	}

	public void setLblUsuarios(Label lblUsuarios) {
		this.lblUsuarios = lblUsuarios;
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

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch(Exception ex) {

		}
		return rk;
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpUsuariosAutorizadores();
	}

	private List getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaUsuariosAutorizadosABMReglaFirma();
	}

	private void setListaDelCommunication(List lista) {
		this.getCommunicationComprasBean().setListaUsuariosAutorizadosABMReglaFirma(lista);
	}

	@Override
	protected void _init() throws Exception {

		if(this.getCommunicationComprasBean().getListaUsuariosAutorizadoresDesdeABM() != null && this.getCommunicationComprasBean().getListaUsuariosAutorizadoresDesdeABM().size() > 0) {
			Option[] op = null;
			op = this.armarArrayOptionsList(this.getCommunicationComprasBean().getListaUsuariosAutorizadoresDesdeABM().toArray(), "");
			ddUsuariosDefaultOptions.setOptions(op);
		}

		Option[] opEstado = null;
		opEstado = this.armarArrayOptionsList(getApplicationBean1().getMapaEstadosSolicitudSuministro().keySet().toArray(), "");
		ddEstadoDefaultOptions.setOptions(opEstado);

		this.getObjectListDataProvider().setList(this.getListaDelCommunication());
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		ReglaFirmaSolicitudSuministro reglaFirma = (ReglaFirmaSolicitudSuministro) this.obtenerObjetoDelElementoPila(ind++);
		List<UsuarioAutorizadorSolicitudSuministro> listaUsuarios; // = (List<UsuarioAutorizadorSolicitudSuministro>)this.obtenerObjetoDelElementoPila(ind++);

		reglaFirma.setOrden(getTextFieldValueInteger(getTfOrden()));

		reglaFirma.setEstado(getDDObjectValue(getDdEstado(), getApplicationBean1().getMapaEstadosSolicitudSuministro()));
		System.out.println(reglaFirma.getEstado().getIdEstadoSolicitudSuministro());

		reglaFirma.setUrgente(this.getCbUrgente().isChecked());

		this.getObjectListDataProvider().commitChanges();
		listaUsuarios = this.getObjectListDataProvider().getList();
		this.setListaDelCommunication(listaUsuarios);
		reglaFirma.setListaUsuarios(listaUsuarios);

		this.getElementoPila().getObjetos().set(0, reglaFirma);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		ReglaFirmaSolicitudSuministro reglaFirma = (ReglaFirmaSolicitudSuministro) this.obtenerObjetoDelElementoPila(ind++);
		List<UsuarioAutorizadorSolicitudSuministro> listaUsuarios = reglaFirma.getListaUsuarios();

		if(reglaFirma.getOrden() != null) {
			this.getTfOrden().setText(reglaFirma.getOrden());
		}

		if(reglaFirma.getEstado() != null) {
			this.getDdEstado().setSelected(reglaFirma.getEstado().getNombre());
			this.getDdUsuariosDefaultOptions().setSelectedValue(reglaFirma.getEstado().getNombre());
		}

		this.getCbUrgente().setValue(reglaFirma.isUrgente());

		this.getObjectListDataProvider().setList(listaUsuarios);
		this.setListaDelCommunication(listaUsuarios);

		this.getElementoPila().getObjetos().set(0, reglaFirma);
	}

	public String btnAgregar_action() {
		this.guardarEstadoObjetosUsados();
		ReglaFirmaSolicitudSuministro locRegla = (ReglaFirmaSolicitudSuministro) this.obtenerObjetoDelElementoPila(0);
		for(UsuarioAutorizadorSolicitudSuministro cadaUsuario : this.getCommunicationComprasBean().getListaUsuariosAutorizadoresDesdeABM()) {
			System.out.println("this.getDdUsuarios().getSelected().toString(): " + this.getDdUsuarios().getSelected().toString());
			System.out.println("cadaUsuario.getUsuario().toString(): " + cadaUsuario.getUsuario().toString());
			if(this.getDdUsuarios().getSelected().toString().equals(cadaUsuario.getUsuario().toString())) {
				System.out.println("ENTROALIFDEUSRIGUALSELECTED");
				if(locRegla.getListaUsuarios() == null) {
					locRegla.setListaUsuarios(new ArrayList<UsuarioAutorizadorSolicitudSuministro>());
				}

				if(!locRegla.getListaUsuarios().contains(cadaUsuario)) {
					locRegla.getListaUsuarios().add(cadaUsuario);
					this.getObjectListDataProvider().setList(locRegla.getListaUsuarios());
				} else {
					warn("El Usuario seleccionado ya se encuentra en la lista");
				}

				break;
			}
		}

		return "";
	}

	public String btnQuitar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			// APLICAR LOGICA AQUI...
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					this.getListaDelCommunication().remove(obj);
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

	public String btnQuitarTodos_action() {
		this.guardarEstadoObjetosUsados();
		ReglaFirmaSolicitudSuministro locRegla = (ReglaFirmaSolicitudSuministro) this.obtenerObjetoDelElementoPila(0);
		locRegla.setListaUsuarios(new ArrayList<UsuarioAutorizadorSolicitudSuministro>());
		this.getObjectListDataProvider().getList().clear();

		return "";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMReglaFirmaSolicitudSuministro";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new ReglaFirmaSolicitudSuministro());

		return ep;
	}

	public Option[] armarArrayOptionsList(Object[] objetos, String capitalizacion) {
		Option[] o = new Option[objetos.length];

		for(int i = 0; i < objetos.length; i++) {
			String display = objetos[i].toString();

			display = display.replaceAll("_", " ");
			Option opcion = new Option(objetos[i].toString(), display);
			o[i] = opcion;
		}
		return o;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		this.getCommunicationComprasBean().setListaUsuariosAutorizadoresDesdeABM((List<UsuarioAutorizadorSolicitudSuministro>) pObject);

		Option[] op = null;
		op = this.armarArrayOptionsList(this.getCommunicationComprasBean().getListaUsuariosAutorizadoresDesdeABM().toArray(), "");
		ddUsuariosDefaultOptions.setOptions(op);
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		ReglaFirmaSolicitudSuministro reglaFirma = (ReglaFirmaSolicitudSuministro) pObject;
		List<UsuarioAutorizadorSolicitudSuministro> listaUsuarios = reglaFirma.getListaUsuarios();

		this.getElementoPila().getObjetos().set(0, reglaFirma);
	}

	@Override
	public String getNombreBean() {
		return "#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro}";
	}

	@Override
	public long getSerialVersionUID() {
		return ReglaFirmaSolicitudSuministro.serialVersionUID;
	}
}