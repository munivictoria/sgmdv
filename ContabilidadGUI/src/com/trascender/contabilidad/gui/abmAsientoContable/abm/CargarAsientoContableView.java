package com.trascender.contabilidad.gui.abmAsientoContable.abm;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TFormattedTextFieldImporte;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.Constantes;
import com.trascender.gui.framework.util.Util;

public class CargarAsientoContableView extends ABMAsientoContableView {

	private static final long serialVersionUID = 5196281792287682986L;
	
	private final String NOMBRE_RECURSO = "CargarAsientoContable";
	
	private JLabel lblCuenta;
	private JTextField tfCuenta;
	private PnlBotonesSeleccion pnlBotonesSeleccionCuenta;
	private JLabel lblImporteDebe;
	private TFormattedTextFieldImporte tfImporteDebe;
	private JLabel lblImporteHaber;
	private TFormattedTextFieldImporte tfImporteHaber;


	public CargarAsientoContableView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	private void init(){
		this.getPnlAtributos().setVisible(false);
		this.getPnlTabla().setVisible(false);
		this.getPnlCuerpo().setLayout(null);
		
		int numFila = -1;
		
		numFila++;
		this.lblCuenta = new TLabel();
		this.lblCuenta.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblCuenta"));
		this.lblCuenta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblCuenta);
		
		this.tfCuenta = new JTextField();
		this.tfCuenta.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.tfCuenta.setEditable(false);
		this.getPnlCuerpo().add(this.tfCuenta);
		
		this.pnlBotonesSeleccionCuenta = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionCuenta.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBotonesSeleccionCuenta);
		
		numFila++;
		this.lblImporteDebe = new TLabel();
		this.lblImporteDebe.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblImporteDebe"));
		this.lblImporteDebe.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblImporteDebe);
		
		this.tfImporteDebe = new TFormattedTextFieldImporte();
		this.tfImporteDebe.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfImporteDebe);
		
		numFila++;
		this.lblImporteHaber = new TLabel();
		this.lblImporteHaber.setText(MessagesContabilidad.getString(NOMBRE_RECURSO + ".lblImporteHaber"));
		this.lblImporteHaber.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblImporteHaber);
		
		this.tfImporteHaber = new TFormattedTextFieldImporte();
		this.tfImporteHaber.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfImporteHaber);
		
		this.setColorFondo();
		this.setTituloVentana();
		this.setDescripcionVentana();
		this.setTextoBtnAceptar();
		this.setTamanioPosicionVentana(numFila + 1);
	}
	
	@Override
	public void setTamanioPosicionVentana(int cantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABMSeleccion(cantidadFilasComponentes));
		this.setLocationRelativeTo(null);
	}
	
	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_AGREGAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
	}

	@Override
	public void setDescripcionVentana() {
		this.getPnlCabecera().getLblDescripcion().setText(MessagesContabilidad.getString("CargarAsientoContable.descripcion"));
	}

	@Override
	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnAceptar().setText(MessagesContabilidad.getString("Application.btnAgregarTexto"));
		this.getPnlPie().getBtnAceptar().setMnemonic(MessagesContabilidad.getChar("Application.btnAgregarTextoMnemonic"));
		this.getPnlPie().getBtnAceptar().setToolTipText(MessagesContabilidad.getString("Application.btnAgregarTextoToolTip"));
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = MessagesContabilidad.getString("CargarAsientoContable.titulo");
		this.setTitle(locTitulo);
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
	}

	

	public void setPnlBotonesSeleccionCuenta(
			PnlBotonesSeleccion pnlBotonesSeleccionCuenta) {
		this.pnlBotonesSeleccionCuenta = pnlBotonesSeleccionCuenta;
	}

	public JLabel getLblCuenta() {
		return lblCuenta;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public JLabel getLblImporteDebe() {
		return lblImporteDebe;
	}

	public TFormattedTextFieldImporte getTfImporteDebe() {
		return tfImporteDebe;
	}

	public JLabel getLblImporteHaber() {
		return lblImporteHaber;
	}

	public TFormattedTextFieldImporte getTfImporteHaber() {
		return tfImporteHaber;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCuenta() {
		return pnlBotonesSeleccionCuenta;
	}

}
