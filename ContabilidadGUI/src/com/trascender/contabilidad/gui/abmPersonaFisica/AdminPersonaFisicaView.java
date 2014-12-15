package com.trascender.contabilidad.gui.abmPersonaFisica;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.AdminView;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public class AdminPersonaFisicaView extends AdminView {

	private static final long serialVersionUID = 2984512308146934854L;
	
	private PersonaFisicaBusquedaModel busquedaModel;
	private PersonaFisicaTableModel tableModel;
	
	private JLabel lblCuil;
	private JTextField tfCuil;
	private JLabel lblDocumento;
	private JComboBox cbTipoDocumento;
	private JTextField tfNumeroDocumento;
	private JLabel lblApellido;
	private JTextField tfApellido;
	private JLabel lblNombre;
	private JTextField tfNombre;
	
	private final String NOMBRE_RECURSO = "AdminPersonaFisica";
	
	public AdminPersonaFisicaView(JFrame owner) {
		super(owner);
		this.init();
	}

	public AdminPersonaFisicaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblCuil = new TLabel();
		this.lblCuil.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblCuil"));
		this.lblCuil.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblCuil);
		
		this.tfCuil = new JTextField();
		this.tfCuil.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfCuil);
		
		numFila++;
		this.lblDocumento = new TLabel();
		this.lblDocumento.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblDocumento"));
		this.lblDocumento.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblDocumento);
		
		this.cbTipoDocumento = new JComboBox();
		this.cbTipoDocumento.setBounds(Util.getBoundsColumnaInputComboBox(numFila));
		this.getPnlBusqueda().add(this.cbTipoDocumento);
		
		this.tfNumeroDocumento = new JTextField();
		this.tfNumeroDocumento.setBounds(ConstantesPosicion.COLUMN_INPUT_POS_INI_X + this.cbTipoDocumento.getBounds().width + ConstantesSeparacion.SEPARADOR_HORIZONTAL,
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+numFila*ConstantesSeparacion.INCREMENTO_Y,
				ConstantesTamanio.TF_WIDTH, ConstantesTamanio.TF_HEIGHT);
		this.getPnlBusqueda().add(this.tfNumeroDocumento);

		numFila++;
		this.lblApellido = new TLabel();
		this.lblApellido.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblApellido"));
		this.lblApellido.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlBusqueda().add(this.lblApellido);
		
		this.tfApellido = new JTextField();
		this.tfApellido.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.getPnlBusqueda().add(this.tfApellido);
		
		numFila++;
		this.lblNombre = new TLabel();
		this.lblNombre.setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".lblNombre"));
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
		this.setTamanioPanelBusqueda(numFila+1);
		this.setTamanioPosicionVentana();
	}
	
	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString(this.NOMBRE_RECURSO+".descripcion"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString(this.NOMBRE_RECURSO+".titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	public PersonaFisicaBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public void setBusquedaModel(PersonaFisicaBusquedaModel busquedaModel) {
		this.busquedaModel = busquedaModel;
	}

	public PersonaFisicaTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(PersonaFisicaTableModel tableModel) {
		this.tableModel = tableModel;
		this.getPnlTabla().getTblDatos().setModel(tableModel);
	}

	public JComboBox getCbTipoDocumento() {
		return cbTipoDocumento;
	}

	public JLabel getLblApellido() {
		return lblApellido;
	}

	public JLabel getLblCuil() {
		return lblCuil;
	}

	public JLabel getLblDocumento() {
		return lblDocumento;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JTextField getTfApellido() {
		return tfApellido;
	}

	public JTextField getTfCuil() {
		return tfCuil;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public JTextField getTfNumeroDocumento() {
		return tfNumeroDocumento;
	}
	
	
	
}
