package com.trascender.gui.framework.principal;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.recursos.Recursos;

public class MainView extends JFrame {
	
	private static final long serialVersionUID = -2609205899173811696L;
	
	private JPanel pnlNorth;
	private JToolBar tbMain;
	private JButton btnConectar;
	private JButton btnDesconectar;
	private JLabel lblMensajeImportante;
	private JPanel pnlCenter;
	private JPanel pnlSouth;
	private JLabel lblUsuario;
	private JLabel lblSistema;
	private JLabel lblFechaHora;
//	private JMenuItem miConectar;
//	private JMenuItem miDesconectar;
//	private JMenuItem miSalir;
	
//	private JMenuItem miContenido;
//	private JMenuItem miAcercaDe;

	private JMenuBar mbMain;
	private MenuSistema menuSistema;
//	private JMenu menuAyuda;
//	private MenuSistema menuAdminPersona;
	
	private Timer timer;
	
	public MainView() {
		super();
		this.init();
	}
	
	private void init() {
		this.setIconImage(AppManager.getInstance().getAdminRecursos().getImage(Recursos.ICO_APLICACION));
		this.setSize(new Dimension(1024, 768));
		this.setMinimumSize(new Dimension(800, 600));
		this.setName("frmMain");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		pnlNorth = new JPanel();
		pnlNorth.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlNorth.setLayout(flowLayout);
		this.getContentPane().add(pnlNorth, BorderLayout.NORTH);

		tbMain = new JToolBar();
		tbMain.setFloatable(false);
		pnlNorth.add(tbMain);

		this.btnConectar = new JButton();
		this.btnConectar.setIcon(AppManager.getInstance().getAdminRecursos().getIcon(Recursos.IMG_CONECTAR));
		this.btnConectar.setEnabled(true);
		tbMain.add(btnConectar);

		this.btnDesconectar = new JButton();
		this.btnDesconectar.setIcon(AppManager.getInstance().getAdminRecursos().getIcon(Recursos.IMG_DESCONECTAR));
		this.btnDesconectar.setEnabled(false);
		tbMain.add(btnDesconectar);

		// Marina
		this.lblMensajeImportante = new JLabel();
		this.lblMensajeImportante.setFont(new Font("Verdana", Font.BOLD, 10));
		this.lblMensajeImportante.setText(" ");
		pnlNorth.add(this.lblMensajeImportante);
		
		pnlCenter = new JPanel();
		this.getContentPane().add(pnlCenter, BorderLayout.CENTER);
		
		pnlSouth = new JPanel();
		pnlSouth.setLayout(new BorderLayout());
		pnlSouth.setBorder(new EtchedBorder(BevelBorder.LOWERED));
		this.getContentPane().add(pnlSouth, BorderLayout.SOUTH);
		
		Font fuentePnlBottom = new Font("Verdana", Font.PLAIN, 10);
		
		lblFechaHora = new JLabel();
		lblFechaHora.setFont(fuentePnlBottom);
		lblFechaHora.setText("-");
		pnlSouth.add(lblFechaHora, BorderLayout.EAST);

		lblUsuario = new JLabel();
		lblUsuario.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblUsuario.setFont(fuentePnlBottom);
		lblUsuario.setText("Usuario:");
		pnlSouth.add(lblUsuario, BorderLayout.WEST);

		lblSistema = new JLabel();
		lblSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistema.setFont(fuentePnlBottom);
		lblSistema.setText("-");
		pnlSouth.add(lblSistema, BorderLayout.CENTER);

		mbMain = new JMenuBar();
		this.setJMenuBar(mbMain);

		this.menuSistema = new MenuSistema(this);
		this.menuSistema.getMiDesconectar().setEnabled(false);
		this.mbMain.add(this.menuSistema.getMenu());
	}

	public JButton getBtnConectar() {
		return btnConectar;
	}

	public void setBtnConectar(JButton btnConectar) {
		this.btnConectar = btnConectar;
	}

	public JButton getBtnDesconectar() {
		return btnDesconectar;
	}

	public void setBtnDesconectar(JButton btnDesconectar) {
		this.btnDesconectar = btnDesconectar;
	}

	public JLabel getLblFechaHora() {
		return lblFechaHora;
	}

	public void setLblFechaHora(JLabel lblFechaHora) {
		this.lblFechaHora = lblFechaHora;
	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public void setLblUsuario(JLabel lblUsuario) {
		this.lblUsuario = lblUsuario;
	}

	public JMenuBar getMbMain() {
		return mbMain;
	}

	public void setMbMain(JMenuBar mbMain) {
		this.mbMain = mbMain;
	}

	public JPanel getPnlCenter() {
		return pnlCenter;
	}

	public void setPnlCenter(JPanel pnlCenter) {
		this.pnlCenter = pnlCenter;
	}

	public JPanel getPnlNorth() {
		return pnlNorth;
	}

	public void setPnlNorth(JPanel pnlNorth) {
		this.pnlNorth = pnlNorth;
	}

	public JPanel getPnlSouth() {
		return pnlSouth;
	}

	public void setPnlSouth(JPanel pnlSouth) {
		this.pnlSouth = pnlSouth;
	}

	public JToolBar getTbMain() {
		return tbMain;
	}

	public void setTbMain(JToolBar tbMain) {
		this.tbMain = tbMain;
	}

	public Timer getTimer() {
		return timer;
	}
	
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public JLabel getLblSistema() {
		return lblSistema;
	}

	public void setLblSistema(JLabel lblSistema) {
		this.lblSistema = lblSistema;
	}

	public MenuSistema getMenuSistema() {
		return menuSistema;
	}

	public void setMenuSistema(MenuSistema menuSistema) {
		this.menuSistema = menuSistema;
	}

	public JLabel getLblMensajeImportante() {
		return lblMensajeImportante;
	}

}