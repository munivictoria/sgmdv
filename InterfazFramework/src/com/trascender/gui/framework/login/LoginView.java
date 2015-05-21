package com.trascender.gui.framework.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.recursos.Recursos;


public class LoginView extends JDialog {

	private static final long serialVersionUID = -9036878266769259160L;
	
	private JLabel lblClave;
	private JLabel lblUsuario;
	private JLabel lblImagen;
	private JTextField tfUsuario;
	private JPasswordField pfClave;
	private JButton btnIngresar;
	private JButton btnCancelar;
	
	
	public LoginView(){
		super();
		this.initialize();
	}
	
	
	public LoginView(JFrame pFrame) {		
		super(pFrame);
		//getContentPane().setFocusCycleRoot(true);
		this.initialize();
	}
	
	private void initialize() {
		setSize(new Dimension(480, 200));
		this.setResizable(false);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setAlwaysOnTop(true);
		setTitle("Vipians : Ingreso");
		getContentPane().setLayout(null);
		
		this.tfUsuario = new JTextField();
		tfUsuario.setColumns(12);
		tfUsuario.setBounds(300, 42, 128, 23);
		getContentPane().add(tfUsuario);
		
		pfClave = new JPasswordField();
		pfClave.setColumns(12);
		pfClave.setBounds(300, 85, 128, 23);
		getContentPane().add(pfClave);
		
		this.btnIngresar = new JButton();
		btnIngresar.setText("Ingresar");
		btnIngresar.setBounds(180, 134, 100, 23);
		getContentPane().add(btnIngresar);

		lblImagen = new JLabel();
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblImagen.setBorder(new LineBorder(Color.black, 1, false));
		lblImagen.setIcon(AppManager.getInstance().getAdminRecursos().getIcon(Recursos.IMG_LOGIN));
		lblImagen.setBounds(10, 10, 144, 150);
		getContentPane().add(lblImagen);
		
		btnCancelar = new JButton();
		btnCancelar.setText("Cancelar");
		btnCancelar.setBounds(290, 134, 100, 23);
		getContentPane().add(btnCancelar);
		
		lblUsuario = new JLabel();
		lblUsuario.setLabelFor(tfUsuario);
//		lblUsuario.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblUsuario.setText("Usuario:");
		lblUsuario.setBounds(188, 46, 100, 15);
		getContentPane().add(lblUsuario);
		
		lblClave = new JLabel();
		lblClave.setLabelFor(pfClave);
//		lblClave.setFont(new Font("Verdana", Font.PLAIN, 10));
		lblClave.setText("Contraseña:");
		lblClave.setBounds(168, 89, 100, 15);
		getContentPane().add(lblClave);
		
		//getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] {tfUsuario, pfClave, btnIngresar, btnCancelar}));

	}


	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JButton getBtnIngresar() {
		return btnIngresar;
	}

	public void setBtnIngresar(JButton btnIngresar) {
		this.btnIngresar = btnIngresar;
	}

	public JLabel getLblClave() {
		return lblClave;
	}

	public void setLblClave(JLabel lblClave) {
		this.lblClave = lblClave;
	}

	public JLabel getLblImagen() {
		return lblImagen;
	}

	public void setLblImagen(JLabel lblImagen) {
		this.lblImagen = lblImagen;
	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public void setLblUsuario(JLabel lblUsuario) {
		this.lblUsuario = lblUsuario;
	}

	public JPasswordField getPfClave() {
		return pfClave;
	}

	public void setPfClave(JPasswordField pfClave) {
		this.pfClave = pfClave;
	}

	public JTextField getTfUsuario() {
		return tfUsuario;
	}

	public void setTfUsuario(JTextField tfUsuario) {
		this.tfUsuario = tfUsuario;
	}
	
}