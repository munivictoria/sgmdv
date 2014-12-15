package com.trascender.contabilidad.gui.BACABMEgresoTesoreria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.ParseException;

import javax.swing.Icon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import com.trascender.contabilidad.gui.recursos.MessagesContabilidad;
import com.trascender.contabilidad.gui.recursos.RecursosContabilidad;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.abmStandard.PnlABMAtributos;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Constantes;
import com.trascender.gui.framework.util.Util;

public class AdminEgresoTesoreriaView extends ABMView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3539574760327138884L;
	
	private JLabel lblFechaDesde;
	private JFormattedTextField tfFechaDesde;
	private JLabel lblFechaHasta;
	private JFormattedTextField tfFechaHasta;

	private MaskFormatter formatter;

	private JPanel pnlAtributos;

	private final String NOMBRE_RECURSO = "EgresoTesoreria";

	public AdminEgresoTesoreriaView() {
		this.setColorFondo();
		this.setTituloVentana();
		this.visibleAll();
		this.setTextoBtnCancelar();
		this.init();
//		this.setImagenCabecera();
	}
	
	private void init() {
		this.getPnlCuerpo().setLayout(new BorderLayout());
		
		this.pnlAtributos = new PnlABMAtributos();
		
		int numFila = -1;
		
		numFila++;
		this.lblFechaDesde = new TLabel();
		this.lblFechaDesde.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblFechaDesde"));
		this.lblFechaDesde.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblFechaDesde);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.tfFechaDesde = new JFormattedTextField(this.formatter);
		this.tfFechaDesde.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		pnlAtributos.add(this.tfFechaDesde);
		
		numFila++;
		this.lblFechaHasta = new TLabel();
		this.lblFechaHasta.setText(MessagesContabilidad.getString(NOMBRE_RECURSO+".lblFechaHasta"));
		this.lblFechaHasta.setBounds(Util.getBoundsColumnaLabel(numFila));
		this.pnlAtributos.add(this.lblFechaHasta);
		
		try {
			this.formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.tfFechaHasta= new JFormattedTextField(this.formatter);
		this.tfFechaHasta.setBounds(Util.getBoundsColumnaInputTextFieldFechaDesde(numFila));
		pnlAtributos.add(this.tfFechaHasta);
				
		numFila++;
		this.getPnlCuerpo().add(pnlAtributos);
		this.setTamanioPosicionVentana(numFila);
	}
	
	private void visibleAll() {
		this.getPnlPie().getBtnImprimir().setVisible(true);
		this.getPnlPie().getBtnAceptar().setVisible(false);
	}

	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_CONSULTAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
		
	}

	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(Util.getTamanioVentanaABM(pCantidadFilasComponentes));
		this.setLocationRelativeTo(null);			
	}

	@Override
	public void setTextoBtnAceptar() {
	}
	
	public void setTextoBtnCancelar() {
		this.getPnlPie().getBtnCancelar().setText("Salir");
	}

	@Override
	public void setTituloVentana() {
		this.getPnlCabecera().getLblTitulo().setText(MessagesContabilidad.getString("EgresoTesoreria.titulo"));
	}
	
	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosContabilidad.IMG_CAB_AGREGAR);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}
	
	public JFormattedTextField getTfFechaDesde() {
		return tfFechaDesde;
	}

	public JFormattedTextField getTfFechaHasta() {
		return tfFechaHasta;
	}

	@Override
	public void setDescripcionVentana() {
		
	}
}


