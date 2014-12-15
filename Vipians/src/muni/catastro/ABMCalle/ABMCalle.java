/*
 * ABMCalle.java
 *
 * Created on 1 de noviembre de 2006, 09:48
 * Copyright Trascender
 */
package muni.catastro.ABMCalle;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.AsociacionParcelaCalle;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.TipoCalle;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMCalle extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		if (this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpZonas();
	}

	private List getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaZonasCalles();
	}

	private void setListaDelCommunication(List lista) {
		this.getComunicationCatastroBean().setListaZonasCalles(lista);
	}

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText staticText2) {
		this.staticText2 = staticText2;
	}

	private RadioButton radioButton1 = new RadioButton();

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
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

	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	private TableColumn tableColumn3 = new TableColumn();

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tableColumn3) {
		this.tableColumn3 = tableColumn3;
	}

	private ObjectListDataProvider ldpZonas = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpZonas() {
		return ldpZonas;
	}

	public void setLdpZonas(ObjectListDataProvider ldpZonas) {
		this.ldpZonas = ldpZonas;
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

	public void setLabel5(Label label5) {
		this.label5 = label5;
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

	private TextField tfTipoCalle = new TextField();

	public TextField getTfTipoCalle() {
		return tfTipoCalle;
	}

	public void setTfTipoCalle(TextField tfCodigo) {
		this.tfTipoCalle = tfCodigo;
	}

	private Button btnSeleccionarTipoCalle = new Button();

	public Button getBtnSeleccionarTipoCalle() {
		return btnSeleccionarTipoCalle;
	}

	public void setBtnSeleccionarTipoCalle(Button b) {
		this.btnSeleccionarTipoCalle = b;
	}

	private Button btnAgregarZona = new Button();

	public Button getBtnAgregarZona() {
		return btnAgregarZona;
	}

	public void setBtnAgregarZona(Button btnAgregarZona) {
		this.btnAgregarZona = btnAgregarZona;
	}

	 protected HtmlAjaxCommandButton btnQuitarZona = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnQuitarZona() {
		return btnQuitarZona;
	}

	public void setBtnQuitarZona(HtmlAjaxCommandButton btnQuitarZona) {
		this.btnQuitarZona = btnQuitarZona;
	}

	private TextField tfCodigo = new TextField();

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tfCodigo) {
		this.tfCodigo = tfCodigo;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMCalle() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Calle());

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		Calle calle = (Calle) this.obtenerObjetoDelElementoPila(0, Calle.class);

		calle.setCodigo(this.getTextFieldValue(getTfCodigo()));
		calle.setNombre(this.getTextFieldValue(getTfNombre()));

		if (this.getObjectListDataProvider().getList() != null && this.getObjectListDataProvider().getList().size() > 0) {
			this.getObjectListDataProvider().commitChanges();
		}
		List listaZonas = (List) this.getObjectListDataProvider().getList();

		if (listaZonas.size() <= 0) {
			System.out.println("-------- lista zonas null o vacio");
		}
		calle.setListaAsociacionParcelaCalle(listaZonas);
		this.setListaDelCommunication(listaZonas);

		this.getElementoPila().getObjetos().set(0, calle);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		Calle calle = (Calle) this.obtenerObjetoDelElementoPila(0, Calle.class);

		this.getTfCodigo().setText(calle.getCodigo());
		this.getTfNombre().setText(calle.getNombre());
		if (calle.getTipoCalle() != null) {
			this.getTfTipoCalle().setText(calle.getTipoCalle().toString());
		}

		this.getObjectListDataProvider().setList(calle.getListaAsociacionParcelaCalle());
		this.setListaDelCommunication(new ArrayList(calle.getListaAsociacionParcelaCalle()));
	}

	public String btnSeleccionarTipoCalle_action() {
		return navegarParaSeleccionar("AdminTipoCalle");
	}

	public String btnAgregarZona_action() {
		return navegarParaSeleccionar("AdminZona");
	}

	public String btnQuitarZona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		Calle calle = (Calle) this.obtenerObjetoDelElementoPila(0, Calle.class);

		if (ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					int index = getNroFila(rk.toString());

					Object obj = this.getObjectListDataProvider().getObjects()[index];

					AsociacionParcelaCalle asociacion = (AsociacionParcelaCalle) obj;

					if (this.getObjectListDataProvider().getList().size() > 0) {
						this.getObjectListDataProvider().commitChanges();
					}

					calle.removeZona(asociacion);

					this.setListaDelCommunication(calle.getListaAsociacionParcelaCalle());
					this.getObjectListDataProvider().setList(this.getListaDelCommunication());
					this.getElementoPila().getObjetos().set(0, calle);
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
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getLdpZonas().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMCalle";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if (pObject instanceof TipoCalle) {
			TipoCalle tipoCalle = (TipoCalle) pObject;
			Calle calle = (Calle) this.obtenerObjetoDelElementoPila(0, Calle.class);
			calle.setTipoCalle(tipoCalle);

			this.getElementoPila().getObjetos().set(0, calle);
		}

		if (pObject instanceof Zona) {
			Zona nuevaZona = (Zona) pObject;
			Calle calle = (Calle) this.obtenerObjetoDelElementoPila(0, Calle.class);
			AsociacionParcelaCalle asociacionExistente = null;

			boolean esta = false;
			int i = 0;
			while (i < calle.getListaAsociacionParcelaCalle().size() && !esta) {
				asociacionExistente = calle.getListaAsociacionParcelaCalle().get(i);
				esta = (asociacionExistente.getZona().equals(nuevaZona));
				i++;
			}
			if (!esta) {
				calle.addZona(nuevaZona);
			} else {
				warn("La Zona que intenta agregar ya se encuentra en la lista.");
			}
			this.getElementoPila().getObjetos().set(0, calle);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Calle calle = (Calle) pObject;

		List listaZona = new ArrayList();

		try {
			this.setListaDelCommunication(calle.getListaAsociacionParcelaCalle());
			this.getLdpZonas().setList(this.getListaDelCommunication());
			listaZona = this.getLdpZonas().getList();
		} catch (Exception ex) {
			error("No se pudieron obtener las Zonas de la Calle: " + ex.getMessage());
		}

		this.getElementoPila().getObjetos().set(0, calle);
	}
	
	@Override
	public long getSerialVersionUID() {
		return Calle.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMCalle$ABMCalle}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Calle locCalle = this.obtenerObjetoDelElementoPila(0, Calle.class);
		this.getTablaLogs().getLdpLogs().setList(locCalle.getListaLogsAuditoria());
	}
}