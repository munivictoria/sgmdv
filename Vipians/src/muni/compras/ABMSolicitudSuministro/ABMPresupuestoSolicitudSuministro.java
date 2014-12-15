
package muni.compras.ABMSolicitudSuministro;

import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.compras.recurso.persistent.suministros.LineaPresupuestoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.PresupuestoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author nico
 * 
 */
public class ABMPresupuestoSolicitudSuministro extends ABMPageBean {

	private Label lblProveedor = new Label();
	private Label lblPlazo = new Label();

	private TextField tfPrecioUnitario = new TextField();
	private TextField tfProveedor = new TextField();
	private TextField tfPlazo = new TextField();

	private TextArea taComentario = new TextArea();

	private StaticText stLineaPresupuestoSolSum = new StaticText();
	private StaticText stBien = new StaticText();
	private StaticText stCantidad = new StaticText();
	private StaticText stSeparador2 = new StaticText();
	private StaticText stUnidad = new StaticText();
	private StaticText stPrecioTotal = new StaticText();

	private PanelGroup groupPanel1 = new PanelGroup();

	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tcBien = new TableColumn();
	private TableColumn tcCantidad = new TableColumn();
	private TableColumn tcPrecioUnitario = new TableColumn();
	private TableColumn tcComentario = new TableColumn();
	private TableColumn tcPrecioTotal = new TableColumn();
	private TableColumn tcUnidad = new TableColumn();

	private ObjectListDataProvider ldpLineaPresupuestoSolSum = new ObjectListDataProvider();

	private Object lastSelected = null;

	private Button btnSeleccionar = new Button();

	private RadioButton radioButton1 = new RadioButton();

	private Table table1 = new Table();

	// GETTERS & SETTERS

	public Label getLblPlazo() {
		return lblPlazo;
	}

	public Label getLblProveedor() {
		return lblProveedor;
	}

	public void setLblProveedor(Label lblProveedor) {
		this.lblProveedor = lblProveedor;
	}

	public void setLblPlazo(Label lblPlazo) {
		this.lblPlazo = lblPlazo;
	}

	public TextField getTfPrecioUnitario() {
		return tfPrecioUnitario;
	}

	public void setTfPrecioUnitario(TextField tfPrecioUnitario) {
		this.tfPrecioUnitario = tfPrecioUnitario;
	}

	public TextField getTfProveedor() {
		return tfProveedor;
	}

	public void setTfProveedor(TextField tfProveedor) {
		this.tfProveedor = tfProveedor;
	}

	public TextField getTfPlazo() {
		return tfPlazo;
	}

	public void setTfPlazo(TextField tfPlazo) {
		this.tfPlazo = tfPlazo;
	}

	public TextArea getTaComentario() {
		return taComentario;
	}

	public void setTaComentario(TextArea taComentario) {
		this.taComentario = taComentario;
	}

	public StaticText getStLineaPresupuestoSolSum() {
		return stLineaPresupuestoSolSum;
	}

	public void setStLineaPresupuestoSolSum(StaticText stLineaPresupuestoSolSum) {
		this.stLineaPresupuestoSolSum = stLineaPresupuestoSolSum;
	}

	public StaticText getStBien() {
		return stBien;
	}

	public void setStBien(StaticText stBien) {
		this.stBien = stBien;
	}

	public StaticText getStCantidad() {
		return stCantidad;
	}

	public void setStCantidad(StaticText stCantidad) {
		this.stCantidad = stCantidad;
	}

	public StaticText getStSeparador2() {
		return stSeparador2;
	}

	public void setStSeparador2(StaticText stSeparador2) {
		this.stSeparador2 = stSeparador2;
	}

	public StaticText getStUnidad() {
		return stUnidad;
	}

	public void setStUnidad(StaticText stUnidad) {
		this.stUnidad = stUnidad;
	}

	public StaticText getStPrecioTotal() {
		return stPrecioTotal;
	}

	public void setStPrecioTotal(StaticText stPrecioTotal) {
		this.stPrecioTotal = stPrecioTotal;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	public TableColumn getTcBien() {
		return tcBien;
	}

	public void setTcBien(TableColumn tcBien) {
		this.tcBien = tcBien;
	}

	public TableColumn getTcCantidad() {
		return tcCantidad;
	}

	public void setTcCantidad(TableColumn tcCantidad) {
		this.tcCantidad = tcCantidad;
	}

	public TableColumn getTcPrecioUnitario() {
		return tcPrecioUnitario;
	}

	public void setTcPrecioUnitario(TableColumn tcPrecioUnitario) {
		this.tcPrecioUnitario = tcPrecioUnitario;
	}

	public TableColumn getTcComentario() {
		return tcComentario;
	}

	public void setTcComentario(TableColumn tcComentario) {
		this.tcComentario = tcComentario;
	}

	public TableColumn getTcPrecioTotal() {
		return tcPrecioTotal;
	}

	public void setTcPrecioTotal(TableColumn tcPrecioTotal) {
		this.tcPrecioTotal = tcPrecioTotal;
	}

	public TableColumn getTcUnidad() {
		return tcUnidad;
	}

	public void setTcUnidad(TableColumn tcUnidad) {
		this.tcUnidad = tcUnidad;
	}

	public ObjectListDataProvider getLdpLineaPresupuestoSolSum() {
		return ldpLineaPresupuestoSolSum;
	}

	public void setLdpLineaPresupuestoSolSum(ObjectListDataProvider ldpLineaPresupuestoSolSum) {
		this.ldpLineaPresupuestoSolSum = ldpLineaPresupuestoSolSum;
	}

	public Object getLastSelected() {
		return lastSelected;
	}

	public void setLastSelected(Object lastSelected) {
		this.lastSelected = lastSelected;
	}

	public Button getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public void setBtnSeleccionar(Button btnSeleccionar) {
		this.btnSeleccionar = btnSeleccionar;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table table1) {
		this.table1 = table1;
	}

	//

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		PresupuestoSolicitudSuministro presupuestoSolSum = (PresupuestoSolicitudSuministro) this.obtenerObjetoDelElementoPila(ind++, PresupuestoSolicitudSuministro.class);
		Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(ind++, Proveedor.class);
		List listaLineasPresupuesto = (List) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		presupuestoSolSum.setPlazoMantenimiento(this.getTextFieldValue(getTfPlazo()));

		// if (proveedor.getIdProveedor() == -1) {
		// proveedor = null;
		// }
		// if (!this.getTfProveedor().isRendered()) {
		// String toStringSeccion = (String)
		// this.getDdProveedor().getSelected();
		// if (toStringSeccion != null) {
		// proveedor =
		// this.getCommunicationComprasBean().getMapaProveedores().get(toStringSeccion);
		// }
		// }
		// if (proveedor != null) {
		// try {
		// ofertaContratacion.setProveedor(proveedor);
		// } catch (Exception ex) {
		// log(getCasoNavegacion() + "_GuardarError:", ex);
		// error(getNombrePagina() + " - Guardar: " + ex.getMessage());
		// }
		// }

		this.getObjectListDataProvider().commitChanges();
		listaLineasPresupuesto = this.getObjectListDataProvider().getList();
		this.setListaDelCommunication(listaLineasPresupuesto);

		presupuestoSolSum.setListaLineasPresupuestoSolicitud(listaLineasPresupuesto);

		for(Object o : listaLineasPresupuesto) {
			LineaPresupuestoSolicitudSuministro linea = (LineaPresupuestoSolicitudSuministro) o;

			linea.setPresupuestoSolicitud(presupuestoSolSum);
		}

		this.getElementoPila().getObjetos().set(0, presupuestoSolSum);
		this.getElementoPila().getObjetos().set(1, proveedor);
		this.getElementoPila().getObjetos().set(2, listaLineasPresupuesto);
	}

	protected ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpLineaPresupuestoSolSum();
	}

	private List getListaDelCommunication() {
		return this.getCommunicationComprasBean().getListaLineasPresupuestosSolSum();
	}

	private void setListaDelCommunication(List lista) {
		this.getCommunicationComprasBean().setListaLineasPresupuestosSolSum(lista);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		PresupuestoSolicitudSuministro presupuestoSolSum = (PresupuestoSolicitudSuministro) this.obtenerObjetoDelElementoPila(0, PresupuestoSolicitudSuministro.class);
		Proveedor proveedor = (Proveedor) this.obtenerObjetoDelElementoPila(1, Proveedor.class);
		List<LineaPresupuestoSolicitudSuministro> listaLineasPresupuesto = (List<LineaPresupuestoSolicitudSuministro>) this.obtenerObjetoDelElementoPila(2, ArrayList.class);

		if(proveedor != null) {
			this.getTfProveedor().setText(proveedor.getRazonSocial());
		}

		this.getTfPlazo().setText(presupuestoSolSum.getPlazoMantenimiento());
		this.getObjectListDataProvider().setList(listaLineasPresupuesto);
		this.setListaDelCommunication((List) listaLineasPresupuesto);
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof Proveedor) {
			try {
				Proveedor proveedor = (Proveedor) pObject;
				proveedor = this.getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().findProveedorByID(proveedor.getIdProveedor());

				boolean yaExiste = false;

				PresupuestoSolicitudSuministro presupuestoSolSum = (PresupuestoSolicitudSuministro) this.obtenerObjetoDelElementoPila(0, PresupuestoSolicitudSuministro.class);
				SolicitudSuministro solicitudSuministro = presupuestoSolSum.getSolicitudSuministro();

				List<PresupuestoSolicitudSuministro> listaPresupuestosExistentes = null;
				if(solicitudSuministro != null) {
					listaPresupuestosExistentes = solicitudSuministro.getListaPresupuestos();

					if(listaPresupuestosExistentes != null) {
						for(PresupuestoSolicitudSuministro presupuesto : listaPresupuestosExistentes) {
							if(presupuesto.getProveedor().getIdProveedor() == proveedor.getIdProveedor()) {
								yaExiste = true;
								warn("Ya existe un Presupuesto para ese Proveedor");
								proveedor = null;
								break;
							}
						}
					}
				}
				if(!yaExiste) {
					presupuestoSolSum.setProveedor(proveedor);
					this.getElementoPila().getObjetos().set(1, proveedor);
				}
			} catch(Exception ex) {
				log(" Error Description", ex);
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		PresupuestoSolicitudSuministro presupuestoSolSum = (PresupuestoSolicitudSuministro) pObject;

		List listaLineasPresupuesto = presupuestoSolSum.getListaLineasPresupuestoSolicitud();
		Proveedor proveedor = presupuestoSolSum.getProveedor();

		this.getElementoPila().getObjetos().set(0, presupuestoSolSum);
		this.getElementoPila().getObjetos().set(1, proveedor);
		this.getElementoPila().getObjetos().set(2, listaLineasPresupuesto);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMPresupuestoSolicitudSuministro";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new PresupuestoSolicitudSuministro()); // 0
		ep.getObjetos().add(ind++, new Proveedor()); // 1
		ep.getObjetos().add(ind++, new ArrayList<LineaPresupuestoSolicitudSuministro>()); // 2

		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
	}

	public String btnSeleccionar_action() {
		return navegarParaSeleccionar("AdminProveedor");
	}

	@Override
	public String getNombreBean() {
		return "#{compras$ABMSolicitudSuministro$ABMPresupuestoSolicitudSuministro}";
	}

	@Override
	public long getSerialVersionUID() {
		return PresupuestoSolicitudSuministro.serialVersionUID;
	}
}