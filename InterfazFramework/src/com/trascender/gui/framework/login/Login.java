package com.trascender.gui.framework.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;

public class Login {
	
	private LoginView interfaz;
	private long llave = 0;
	
	public Login(JFrame pPadre) throws Exception {
		this.interfaz = new LoginView(pPadre);
		this.setListeners();
	}
	
	public Login() throws Exception {		
		this.interfaz = new LoginView();
		this.setListeners();
	}

	/**
	 * @return la llave obtenida del login
	 */
	public long open(){
		this.interfaz.setVisible(true);
		return this.llave;
	}
	
	public void close(){
		this.interfaz.dispose();
	}
	
	private void setListeners() {
		this.interfaz.addWindowListener(new LoginWindowListener());
		
		this.interfaz.getTfUsuario().addKeyListener(new TfUsuarioKeyListener());
		this.interfaz.getPfClave().addKeyListener(new TfUsuarioKeyListener());
		
		this.interfaz.getTfUsuario().addFocusListener(new TfGainFocusListener());
		this.interfaz.getPfClave().addFocusListener(new TfGainFocusListener());
		
		this.interfaz.getBtnIngresar().addActionListener(new LoginButtonListener());
		this.interfaz.getBtnCancelar().addActionListener(new LoginButtonListener());
	}
	
	
	public void login() {
		try {
			String nombre = this.interfaz.getTfUsuario().getText();
			String clave  = new String(this.interfaz.getPfClave().getPassword()).trim();
			
			// ariel
//			this.llave = AppManager.getInstance().getAdminSystems().getSystemUsuario().login(nombre, clave);
			this.llave = AppManager.getInstance().getAdminSystems().login(nombre, clave);
			
			
			this.close();
		}
		catch(TrascenderException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.interfaz, ex.getMessage());	
		}
		catch(RemoteException ex) {
			ex.printStackTrace();
			TrascenderException exc = new GuiException(2);
			AppManager.getInstance().showErrorMsg(this.interfaz, exc.getMessage());
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.interfaz, ex.getMessage());
		}
	}

	class LoginWindowListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			Login.this.close();
		}
	}

	class LoginButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand().equals(Login.this.interfaz.getBtnIngresar().getActionCommand())) {
				Login.this.login();
			}
			else if (e.getActionCommand().equals(Login.this.interfaz.getBtnCancelar().getActionCommand())) {
				Login.this.close();
			}
		}
	}

	class TfUsuarioKeyListener extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER){
				Login.this.login();
			}
			else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
				Login.this.close();
			}
		}

	}
	
	class TfGainFocusListener extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			JTextField tf = (JTextField)e.getSource();
			tf.selectAll();
		}
	}
}
