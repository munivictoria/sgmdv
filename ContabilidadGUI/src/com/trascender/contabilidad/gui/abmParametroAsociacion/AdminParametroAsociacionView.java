package com.trascender.contabilidad.gui.abmParametroAsociacion;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;

public class AdminParametroAsociacionView extends AdminView {

	private static final long serialVersionUID = -520225704374575756L;
	private ParametroAsociacionBusquedaModel busquedaModel;
	private ParametroAsociacionTableModel tableModel;
	
	private JLabel lblLeyenda;
	
	private final String NOMBRE_RECURSO = "AdminParametroAsociacion";
	
	
	public AdminParametroAsociacionView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	public AdminParametroAsociacionView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public void init(){
		int numFila = -1;
		
		numFila++;
		this.lblLeyenda = new JLabel("Seleccione el Parámetro de Asociación deseado");
		this.lblLeyenda.setBounds(new Rectangle(ConstantesPosicion.COLUMN_LBL_POS_INI_X,
				ConstantesPosicion.COLUMN_LBL_POS_INI_Y+numFila*ConstantesSeparacion.INCREMENTO_Y,
				ConstantesTamanio.LBL_WIDTH * 2, 
				ConstantesTamanio.LBL_HEIGHT));
		this.lblLeyenda.setFont(new Font("Verdana",0,12));
		this.getPnlBusqueda().add(this.lblLeyenda);
		
		//numFila++;
//		this.setPosicionPanelBotonesBusqueda(numFila);
//		this.getPnlBusqueda().add(this.getPnlBotonesBusqueda());
		
		this.getPnlPie().getBtnConsultar().setVisible(false);
		
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

	public ParametroAsociacionBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(ParametroAsociacionBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public ParametroAsociacionTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(ParametroAsociacionTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

}
