package com.trascender.contabilidad.gui.abmConceptoIngresoVario;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Util;

public class AdminConceptoSelladoAdministrativoView extends AdminView {

	private static final long serialVersionUID = -164871982654747873L;
	
	private ConceptoSelladoAdministrativoBusquedaModel busquedaModel;
	private ConceptoSelladoAdministrativoTableModel tableModel;
	
	private final String NOMBRE_RECURSO = "AdminConceptoSelladoAdministrativo";

	private JLabel lblNombre;
	private JTextField tfNombre;
	
	public AdminConceptoSelladoAdministrativoView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public AdminConceptoSelladoAdministrativoView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblNombre"));
		this.lblNombre.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblNombre);
		
		this.tfNombre = new JTextField();
		this.tfNombre.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfNombre);
		
		numFila++;
		this.setPosicionPanelBotonesBusqueda(numFila);
		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());

		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTamanioPanelBusqueda(numFila + 1);
		this.setTamanioPosicionVentana();
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String titulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO + ".titulo");
		this.setTitle(titulo);
		this.getPnlCabecera().getLblTitulo().setText(titulo);
	}

	public ConceptoSelladoAdministrativoBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(
			ConceptoSelladoAdministrativoBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public ConceptoSelladoAdministrativoTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(ConceptoSelladoAdministrativoTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}
	
	
}
