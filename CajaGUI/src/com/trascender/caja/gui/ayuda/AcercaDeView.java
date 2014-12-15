package com.trascender.caja.gui.ayuda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.trascender.caja.gui.recursos.RecursosCaja;
import com.trascender.gui.framework.abmStandard.ABMView;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Constantes;
import com.trascender.gui.framework.util.ConstantesPosicion;
import com.trascender.gui.framework.util.ConstantesSeparacion;
import com.trascender.gui.framework.util.ConstantesTamanio;

public class AcercaDeView extends ABMView {

	private static final long serialVersionUID = 5513255615940373091L;

	private JLabel lblImagen;
	private JLabel lblNombre;
	private JLabel lblVersion;
	private JLabel lblCopyRight;
	private JTextArea taProgamadores;
	private JScrollPane spProgramadores;
	
	public AcercaDeView(JDialog owner) {
		super(owner);
		this.init();
	}
	
	public AcercaDeView(JFrame owner) {
		super(owner);
		this.init();
	}
	
	private void init() {
		
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosCaja.IMG_ACERCADE_VIPIANS);
		this.lblImagen = new JLabel();
		this.lblImagen.setIcon(i);
		this.lblImagen.setBounds(0,0,i.getIconWidth(),i.getIconHeight());
		this.getPnlCuerpo().add(this.lblImagen);
		
		this.lblNombre = new JLabel();
		this.lblNombre.setHorizontalTextPosition(SwingConstants.LEFT);
		this.lblNombre.setText("Vipians®");
		this.lblNombre.setBounds(this.lblImagen.getWidth() + ConstantesSeparacion.SEPARADOR_HORIZONTAL,
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y,
				ConstantesTamanio.LBL_WIDTH, ConstantesTamanio.LBL_HEIGHT);
		this.lblNombre.setFont(new Font(	"Verdana",1,18));
		this.getPnlCuerpo().add(this.lblNombre);
		
		this.lblVersion = new JLabel();
		this.lblVersion.setHorizontalTextPosition(SwingConstants.LEFT);
		this.lblVersion.setText("Versión 1.0");
		this.lblVersion.setBounds(this.lblImagen.getWidth() + ConstantesSeparacion.SEPARADOR_HORIZONTAL,
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y + ConstantesTamanio.LBL_HEIGHT + ConstantesSeparacion.SEPARADOR_VERTICAL,
				ConstantesTamanio.LBL_WIDTH, ConstantesTamanio.LBL_HEIGHT);
		this.lblVersion.setFont(new Font("Verdana",0,12));
		this.getPnlCuerpo().add(this.lblVersion);
		
		this.lblCopyRight = new JLabel();
		this.lblCopyRight.setHorizontalTextPosition(SwingConstants.LEFT);
		this.lblCopyRight.setText("2006-2007 Todos los Derechos Reservados.");
		this.lblCopyRight.setBounds(this.lblImagen.getWidth() + ConstantesSeparacion.SEPARADOR_HORIZONTAL,
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y + ConstantesTamanio.LBL_HEIGHT*2 + ConstantesSeparacion.SEPARADOR_VERTICAL*2,
				ConstantesTamanio.LBL_WIDTH+48, ConstantesTamanio.LBL_HEIGHT);
		this.lblCopyRight.setFont(new Font("Verdana",0,12));
		this.getPnlCuerpo().add(this.lblCopyRight);
		
		this.taProgamadores = new JTextArea();
		this.taProgamadores.setText("Desarrolladores:\n\n" +
				"Adrián E. Degenhardt\n" +
				"Adrián F. Godoy\n" + 
				"Andrés A. Trevisan\n" +
				"Ernesto A. Zapata Icart\n" +
				"Ignacio Sarnaglia\n" +
				"Juan P. Burioni\n" +
				"Mariano A. Carpio\n" +
				"Mariano A. Lusardi\n" +
				"Marina S. Sors\n" +
				"Mónica S. Erbes" );
		this.taProgamadores.setCaretPosition(0);
		this.spProgramadores = new JScrollPane(this.taProgamadores);
		this.spProgramadores.setBorder(null);
		this.spProgramadores.setBounds(this.lblImagen.getWidth() + ConstantesSeparacion.SEPARADOR_HORIZONTAL,
				ConstantesPosicion.COLUMN_INPUT_POS_INI_Y+85+ ConstantesSeparacion.SEPARADOR_VERTICAL,
				this.lblCopyRight.getWidth(), 
				ConstantesTamanio.LBL_HEIGHT * 4);
		this.getPnlCuerpo().add (this.spProgramadores);
		
		this.setImagenCabecera();
		this.setColorFondo();
		this.setTamanioPosicionVentana(0);
		this.setTextoBtnAceptar();
		this.setTituloVentana();
	}
	
	public void setImagenCabecera() {
		Icon i = AppManager.getInstance().getAdminRecursos().getIcon(RecursosCaja.IMG_ACERCADE);
		this.getPnlCabecera().getLblImagen().setIcon(i);
	}
	
	@Override
	public void setColorFondo() {
		Color color = Constantes.COLOR_CONSULTAR;
		this.getPnlCabecera().setBackground(color);
		this.getPnlPie().setBackground(color);
		this.getPnlCuerpo().setBackground(Color.WHITE);
	}

	@Override
	public void setDescripcionVentana() {
	}

	@Override
	public void setTamanioPosicionVentana(int pCantidadFilasComponentes) {
		this.setSize(new Dimension(
				this.lblImagen.getWidth() + ConstantesSeparacion.SEPARADOR_HORIZONTAL * 3 + this.lblCopyRight.getWidth(),
				ConstantesTamanio.PNL_CABECERA_HEIGHT + ConstantesTamanio.PNL_PIE_HEIGHT + this.lblImagen.getHeight() - 10));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	@Override
	public void setTextoBtnAceptar() {
		this.getPnlPie().getBtnCancelar().setText("Cerrar");
	}

	@Override
	public void setTituloVentana() {
		String locTitulo = "Acerca de Vipians";
		this.getPnlCabecera().getLblTitulo().setText(locTitulo);
		this.setTitle(locTitulo);
	}

	public JLabel getLblCopyRight() {
		return lblCopyRight;
	}

	public JLabel getLblImagen() {
		return lblImagen;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JLabel getLblVersion() {
		return lblVersion;
	}

	public JTextArea getTaProgamadores() {
		return taProgamadores;
	}

	public JScrollPane getSpProgramadores() {
		return spProgramadores;
	}

}
