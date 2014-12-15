/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.compras.ABMActaApertura;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
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
import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.compras.recurso.persistent.suministros.LineaOfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.OfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author ubuntero
 */
public class ABMOfertaLicitacion extends ABMPageBean {

	private Label lblProveedor = new Label();
	private Label lblPlazo = new Label();

	private TextField tfPrecioUnitario = new TextField();
	private TextArea taComentario = new TextArea();
	private TextField tfProveedor = new TextField();
	private TextField tfPlazo = new TextField();

	private StaticText stRenglonesLicitacion = new StaticText();
	private StaticText stBien = new StaticText();
	private StaticText stCantidad = new StaticText();
	private StaticText stSeparador2 = new StaticText();
	private StaticText stUnidad = new StaticText();
	private StaticText stPrecioTotal = new StaticText();

	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private PanelGroup groupPanel1 = new PanelGroup();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tcBien = new TableColumn();
	private TableColumn tcCantidad = new TableColumn();
	private TableColumn tcPrecioUnitario = new TableColumn();
	private TableColumn tcComentario = new TableColumn();
	private TableColumn tcPrecioTotal = new TableColumn();
	private TableColumn tcUnidad = new TableColumn();

	private ObjectListDataProvider ldpOfertaRenglonesLicitacion = new ObjectListDataProvider();
	private ObjectListDataProvider ldpLicitaciones = new ObjectListDataProvider();

	protected SingleSelectOptionsList ddProveedorDefaultOptions = new SingleSelectOptionsList();

	private Object lastSelected = null;

	private Button btnSeleccionar = new Button();
	private Button btnAgregar = new Button();
	private Button btnQuitar = new Button();
	private Button btnQuitarTodos = new Button();
	private RadioButton radioButton1 = new RadioButton();

	private Table table1 = new Table();

	private DropDown ddProveedor = new DropDown();

	public Label getLblPlazo() {
		return lblPlazo;
	}

	public void setLblPlazo(Label lblPlazo) {
		this.lblPlazo = lblPlazo;
	}

	public TextField getTfPlazo() {
		return tfPlazo;
	}

	public void setTfPlazo(TextField tfPlazo) {
		this.tfPlazo = tfPlazo;
	}

	public TextField getTfProveedor() {
		return tfProveedor;
	}

	public void setTfProveedor(TextField tfProveedor) {
		this.tfProveedor = tfProveedor;
	}

	public ObjectListDataProvider getLdpLicitaciones() {
		return ldpLicitaciones;
	}

	public void setLdpLicitaciones(ObjectListDataProvider ldpLicitaciones) {
		this.ldpLicitaciones = ldpLicitaciones;
	}

	public SingleSelectOptionsList getDdProveedorDefaultOptions() {
		return ddProveedorDefaultOptions;
	}

	public void setDdProveedorDefaultOptions(SingleSelectOptionsList ddProveedorDefaultOptions) {
		this.ddProveedorDefaultOptions = ddProveedorDefaultOptions;
	}

	public DropDown getDdProveedor() {
		return ddProveedor;
	}

	public void setDdProveedor(DropDown ddProveedor) {
		this.ddProveedor = ddProveedor;
	}

	public StaticText getStPrecioTotal() {
		return stPrecioTotal;
	}

	public void setStPrecioTotal(StaticText stPrecioTotal) {
		this.stPrecioTotal = stPrecioTotal;
	}

	public StaticText getStUnidad() {
		return stUnidad;
	}

	public void setStUnidad(StaticText stUnidad) {
		this.stUnidad = stUnidad;
	}

	public TableColumn getTcUnidad() {
		return tcUnidad;
	}

	public void setTcUnidad(TableColumn tcUnidad) {
		this.tcUnidad = tcUnidad;
	}

	public Button getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(Button btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public Button getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(Button btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public Button getBtnQuitarTodos() {
		return btnQuitarTodos;
	}

	public void setBtnQuitarTodos(Button btnQuitarTodos) {
		this.btnQuitarTodos = btnQuitarTodos;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public StaticText getStSeparador2() {
		return stSeparador2;
	}

	public void setStSeparador2(StaticText stSeparador2) {
		this.stSeparador2 = stSeparador2;
	}

	public Object getLastSelected() {
		return lastSelected;
	}

	public void setLastSelected(Object lastSelected) {
		this.lastSelected = lastSelected;
	}

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public ObjectListDataProvider getLdpOfertaRenglonesLicitacion() {
		return ldpOfertaRenglonesLicitacion;
	}

	public void setLdpOfertaRenglonesLicitacion(ObjectListDataProvider ldpOfertaRenglonesLicitacion) {
		this.ldpOfertaRenglonesLicitacion = ldpOfertaRenglonesLicitacion;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table table1) {
		this.table1 = table1;
	}

	public Button getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public void setBtnSeleccionar(Button btnSeleccionar) {
		this.btnSeleccionar = btnSeleccionar;
	}

	public Label getLblProveedor() {
		return lblProveedor;
	}

	public void setLblProveedor(Label lblProveedor) {
		this.lblProveedor = lblProveedor;
	}

	public StaticText getStCantidad() {
		return stCantidad;
	}

	public void setStCantidad(StaticText stCantidad) {
		this.stCantidad = stCantidad;
	}

	public StaticText getStBien() {
		return stBien;
	}

	public void setStBien(StaticText stBien) {
		this.stBien = stBien;
	}

	public StaticText getStRenglonesLicitacion() {
		return stRenglonesLicitacion;
	}

	public void setStRenglonesLicitacion(StaticText stRenglonesLicitacion) {
		this.stRenglonesLicitacion = stRenglonesLicitacion;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	public TableColumn getTcCantidad() {
		return tcCantidad;
	}

	public void setTcCantidad(TableColumn tcCantidad) {
		this.tcCantidad = tcCantidad;
	}

	public TableColumn getTcComentario() {
		return tcComentario;
	}

	public void setTcComentario(TableColumn tcComentario) {
		this.tcComentario = tcComentario;
	}

	public TableColumn getTcBien() {
		return tcBien;
	}

	public void setTcBien(TableColumn tcBien) {
		this.tcBien = tcBien;
	}

	public TableColumn getTcPrecioTotal() {
		return tcPrecioTotal;
	}

	public void setTcPrecioTotal(TableColumn tcPrecioTotal) {
		this.tcPrecioTotal = tcPrecioTotal;
	}

	public TableColumn getTcPrecioUnitario() {
		return tcPrecioUnitario;
	}

	public void setTcPrecioUnitario(TableColumn tcPrecioUnitario) {
		this.tcPrecioUnitario = tcPrecioUnitario;
	}

	public TextArea getTaComentario() {
		return taComentario;
	}

	public void setTaComentario(TextArea taComentario) {
		this.taComentario = taComentario;
	}

	public TextField getTfPrecioUnitario() {
		return tfPrecioUnitario;
	}

	public void setTfPrecioUnitario(TextField tfPrecioUnitario) {
		this.tfPrecioUnitario = tfPrecioUnitario;
	}

	public Object getRBSelected() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if(selected != null) {
			lastSelected = selected;
		}
	}

	public String btnSeleccionar_action() {
		return navegarParaSeleccionar("AdminProveedor");
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		OfertaContratacion ofertaContratacion = (OfertaContratacion) this.obtenerObjetoDelElementoPila(ind++, OfertaContratacion.class);
		Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(ind++, Proveedor.class);
		List listaRenglonesLicitacion = (List) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		ofertaContratacion.setPlazo(this.getTextFieldValue(getTfPlazo()));

		if(proveedor.getIdProveedor() == -1) {
			proveedor = null;
		}
		if(!this.getTfProveedor().isRendered()) {
			String toStringSeccion = (String) this.getDdProveedor().getSelected();
			if(toStringSeccion != null) {
				proveedor = this.getCommunicationComprasBean().getMapaProveedores().get(toStringSeccion);
			}
		}

		if(proveedor != null) {
			try {
				ofertaContratacion.setProveedor(proveedor);
			} catch(Exception ex) {
				log(getCasoNavegacion() + "_GuardarError:", ex);
				error(getNombrePagina() + " - Guardar: " + ex.getMessage());
			}
		}

		this.getObjectListDataProvider().commitChanges();
		listaRenglonesLicitacion = this.getObjectListDataProvider().getList();
		this.setListaDelCommunication(listaRenglonesLicitacion);

		ofertaContratacion.setListaLineasOfertasContratacion(listaRenglonesLicitacion);

		for(Object o : listaRenglonesLicitacion) {
			LineaOfertaContratacion linea = (LineaOfertaContratacion) o;

			linea.setOfertaContratacion(ofertaContratacion);
		}

		this.getElementoPila().getObjetos().set(0, ofertaContratacion);
		this.getElementoPila().getObjetos().set(1, proveedor);
		this.getElementoPila().getObjetos().set(2, listaRenglonesLicitacion);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		OfertaContratacion ofertaLicitacion = (OfertaContratacion) this.obtenerObjetoDelElementoPila(0, OfertaContratacion.class);
		Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(1, Proveedor.class);
		List<LineaOfertaContratacion> listaLineasContratacion = (List<LineaOfertaContratacion>) this.obtenerObjetoDelElementoPila(2, ArrayList.class);

		if(proveedor != null) {
			this.getTfProveedor().setText(proveedor.getRazonSocial());
			if(this.getCommunicationComprasBean().getMapaProveedores() != null) {
				this.getDdProveedor().setSelected(proveedor.getRazonSocial());
			}
		}

		this.getTfPlazo().setText(ofertaLicitacion.getPlazo());
		this.getObjectListDataProvider().setList(listaLineasContratacion);
		this.setListaDelCommunication((List) listaLineasContratacion);
	}

	protected ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpOfertaRenglonesLicitacion();
	}

	private List getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaOfertaRenglonLicitacion();
	}

	private void setListaDelCommunication(List lista) {
		this.getCommunicationComprasBean().setListaOfertaRenglonLicitacion(lista);
	}

	protected ObjectListDataProvider getObjectListDataProviderLicitaciones() {
		return this.getLdpLicitaciones();
	}

	private List getListaDelCommunicationContratacion() {
		return this.getCommunicationComprasBean().getListaLicitaciones();
	}

	private void setListaDelCommunicationProveedores(List lista) {
		this.getCommunicationComprasBean().setListaLicitaciones(lista);
	}

	@Override
	protected String getNombrePagina() {
		return "Ofertas de Contrataci\363n";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMOfertaLicitacion";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new OfertaContratacion()); // 0
		ep.getObjetos().add(ind++, new Proveedor()); // 1
		ep.getObjetos().add(ind++, new ArrayList<LineaOfertaContratacion>()); // 2

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void _init() throws Exception {

		if(this.getListaDelCommunicationContratacion() != null) {
			this.getObjectListDataProviderLicitaciones().setList(this.getListaDelCommunicationContratacion());
		}

		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

	}

	private void armarDropDownProveedores(Contratacion contratacion) {
		if(this.getCommunicationComprasBean().getMapaProveedores() == null || this.getCommunicationComprasBean().getMapaProveedores().isEmpty()) {
			try {
				List locListaProveedores = contratacion.getListaProveedoresAutorizados();
				Map<String, Proveedor> locMapa = new LinkedHashMap<String, Proveedor>();
				for(Object cadaObject : locListaProveedores) {
					Proveedor p = (Proveedor) cadaObject;
					locMapa.put(p.getRazonSocial(), p);
				}
				this.getCommunicationComprasBean().setMapaProveedores(locMapa);

			} catch(Exception e) {

				e.printStackTrace();
			}
		}
		Option[] op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getCommunicationComprasBean().getMapaProveedores().keySet().toArray(), "");
		ddProveedorDefaultOptions.setOptions(op);
	}

	@Override
	protected void _prerender() {

		OfertaContratacion oferta;
		if(this.getRequestBean1().getObjetoABM() != null) {
			oferta = (OfertaContratacion) this.getRequestBean1().getObjetoABM();
		} else {
			oferta = (OfertaContratacion) this.obtenerObjetoDelElementoPila(0);
		}

		if(/*
			 * !this.getObjectListDataProviderLicitaciones().getList().isEmpty() &&
			 */!oferta.getContratacion().getListaProveedoresAutorizados().isEmpty()) {
			this.getTfProveedor().setRendered(false);
			this.getBtnSeleccionar().setRendered(false);

			this.armarDropDownProveedores(oferta.getContratacion());

		} else {
			this.getDdProveedor().setRendered(false);
			this.getTfProveedor().setRendered(true);
			this.getDdProveedorDefaultOptions().setOptions(null);
			if(!this.getTfPlazo().isDisabled()) {
				this.getBtnSeleccionar().setRendered(true);
			}

		}
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof Proveedor) {
			try {
				Proveedor proveedor = (Proveedor) pObject;
				proveedor = this.getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().findProveedorByID(proveedor.getIdProveedor());

				boolean yaExiste = false;

				OfertaContratacion ofertaLicitacion = (OfertaContratacion) this.obtenerObjetoDelElementoPila(0, OfertaContratacion.class);
				Contratacion contratacion = ofertaLicitacion.getContratacion();

				List<OfertaContratacion> listaOfertasExistentes = null;
				if(contratacion != null) {
					listaOfertasExistentes = contratacion.getListaOfertasContratacion();

					if(listaOfertasExistentes != null) {
						for(OfertaContratacion oferta : listaOfertasExistentes) {
							if(oferta.getProveedor().getIdProveedor() == proveedor.getIdProveedor()) {
								yaExiste = true;
								warn("Ya existe una Oferta Contrataci\363n para ese Proveedor");
								proveedor = null;
								break;
							}
						}
					}
				}
				if(!yaExiste) {
					ofertaLicitacion.setProveedor(proveedor);
					this.getElementoPila().getObjetos().set(1, proveedor);
				}
			} catch(Exception ex) {
				log(" Error Description", ex);
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		OfertaContratacion ofertaLicitacion = (OfertaContratacion) pObject;

		List listaLineasContratacion = ofertaLicitacion.getListaLineasOfertasContratacion();
		Proveedor proveedor = ofertaLicitacion.getProveedor();

		this.getElementoPila().getObjetos().set(0, ofertaLicitacion);
		this.getElementoPila().getObjetos().set(1, proveedor);
		this.getElementoPila().getObjetos().set(2, listaLineasContratacion);
	}

	@Override
	public String getNombreBean() {
		return "#{compras$ABMActaApertura$ABMOfertaLicitacion}";
	}

	@Override
	public long getSerialVersionUID() {
		return OfertaContratacion.serialVersionUID;
	}
}