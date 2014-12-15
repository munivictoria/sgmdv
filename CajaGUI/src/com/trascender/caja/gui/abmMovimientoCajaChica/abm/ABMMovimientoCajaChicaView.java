package com.trascender.caja.gui.abmMovimientoCajaChica.abm;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.trascender.caja.gui.abmMovimientoCajaChica.MovimientoCajaChicaABMModel;
import com.trascender.caja.gui.recursos.MessagesCaja;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlBotonesSeleccion;
import com.trascender.gui.framework.component.TFormattedTextFieldImporte;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;
import com.trascender.gui.framework.util.Util;

public abstract class ABMMovimientoCajaChicaView extends ABMView {

	private static final long serialVersionUID = -4239528446371284240L;
	
	private MovimientoCajaChicaABMModel abmModel;
	
	private JLabel lblImporte;
	private TFormattedTextFieldImporte tfImporte;
	private JLabel lblConcepto;
	private JTextField tfConcepto;
	private PnlBotonesSeleccion pnlBotonesSeleccionConcepto;
	private JLabel lblCajaChica;
	private JTextField tfCajaChica;
	private PnlBotonesSeleccion pnlBotonesSeleccionCajaChica;
	
	private final String NOMBRE_RECURSO = "ABMMovimientoCajaChica";

	public ABMMovimientoCajaChicaView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public ABMMovimientoCajaChicaView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		int numFila = -1;
		
		numFila++;
		this.lblCajaChica = new TLabel();
		this.lblCajaChica.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblCajaChica"));
		this.lblCajaChica.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblCajaChica);
		
		this.tfCajaChica = new JTextField();
		this.tfCajaChica.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.tfCajaChica.setEditable(false);
		this.getPnlCuerpo().add(this.tfCajaChica);
		
		this.pnlBotonesSeleccionCajaChica = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionCajaChica.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBotonesSeleccionCajaChica);
		
		numFila++;
		this.lblConcepto = new TLabel();
		this.lblConcepto.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblConcepto"));
		this.lblConcepto.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblConcepto);
		
		this.tfConcepto = new JTextField();
		this.tfConcepto.setBounds(Util.getBoundsColumnaInputTextField(numFila));
		this.tfConcepto.setEditable(false);
		this.getPnlCuerpo().add(this.tfConcepto);
		
		this.pnlBotonesSeleccionConcepto = new PnlBotonesSeleccion();
		this.pnlBotonesSeleccionConcepto.setBounds(Util.getBoundsColumnaSeleccion(numFila));
		this.getPnlCuerpo().add(this.pnlBotonesSeleccionConcepto);
		
		numFila++;
		this.lblImporte = new TLabel();
		this.lblImporte.setText(MessagesCaja.getString(this.NOMBRE_RECURSO+".lblImporte"));
		this.lblImporte.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.getPnlCuerpo().add(this.lblImporte);
		
		this.tfImporte = new TFormattedTextFieldImporte();
		this.tfImporte.setBounds(Util.getBoundsColumnaInputTextFieldImporte(numFila));
		this.getPnlCuerpo().add(this.tfImporte);
		
		setTamanioPosicionVentana(numFila + 1);		
	}

	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(
				ConstantesPosicion.COLUMN_LBL_POS_INI_X*2+
				ConstantesPosicion.COLUMN_INPUT_POS_INI_X+
				ConstantesTamanio.TF_WIDTH+
				ConstantesSeparacion.SEPARADOR_HORIZONTAL+
				ConstantesTamanio.PNL_BOTONES_SELECCION_WIDTH,
				
				ConstantesTamanio.PNL_CABECERA_HEIGHT+ConstantesTamanio.PNL_PIE_HEIGHT+pCantidadFilasComponentes*
				ConstantesSeparacion.INCREMENTO_Y+ConstantesPosicion.COLUMN_LBL_POS_INI_Y));
		this.setLocationRelativeTo(null);
	}
	
	public MovimientoCajaChicaABMModel getAbmModel() {
		return abmModel;
	}

	public void setAbmModel(MovimientoCajaChicaABMModel abmModel) {
		this.abmModel = abmModel;
	}

	public JLabel getLblCajaChica() {
		return lblCajaChica;
	}

	public void setLblCajaChica(JLabel lblCajaChica) {
		this.lblCajaChica = lblCajaChica;
	}

	public JLabel getLblConcepto() {
		return lblConcepto;
	}

	public void setLblConcepto(JLabel lblConcepto) {
		this.lblConcepto = lblConcepto;
	}

	public JLabel getLblImporte() {
		return lblImporte;
	}

	public void setLblImporte(JLabel lblImporte) {
		this.lblImporte = lblImporte;
	}

	public JTextField getTfCajaChica() {
		return tfCajaChica;
	}

	public void setTfCajaChica(JTextField tfCajaChica) {
		this.tfCajaChica = tfCajaChica;
	}

	public JTextField getTfConcepto() {
		return tfConcepto;
	}

	public void setTfConcepto(JTextField tfConcepto) {
		this.tfConcepto = tfConcepto;
	}

	public TFormattedTextFieldImporte getTfImporte() {
		return tfImporte;
	}

	public void setTfImporte(TFormattedTextFieldImporte tfImporte) {
		this.tfImporte = tfImporte;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionCajaChica() {
		return pnlBotonesSeleccionCajaChica;
	}

	public void setPnlBotonesSeleccionCajaChica(
			PnlBotonesSeleccion pnlBotonesSeleccionCajaChica) {
		this.pnlBotonesSeleccionCajaChica = pnlBotonesSeleccionCajaChica;
	}

	public PnlBotonesSeleccion getPnlBotonesSeleccionConcepto() {
		return pnlBotonesSeleccionConcepto;
	}

	public void setPnlBotonesSeleccionConcepto(
			PnlBotonesSeleccion pnlBotonesSeleccionConcepto) {
		this.pnlBotonesSeleccionConcepto = pnlBotonesSeleccionConcepto;
	}
	
	
}
