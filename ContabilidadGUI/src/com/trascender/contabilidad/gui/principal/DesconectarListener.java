package com.trascender.contabilidad.gui.principal;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.principal.MainView;


public class DesconectarListener implements ActionListener {
	
	private Component mainWindow;
	
	public DesconectarListener(Component mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.desconectar();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.mainWindow, ex.getMessage());
		}
	}
	
	private void desconectar() throws Exception {
		AppManager.getInstance().setUsuario(null);
		AppManager.getInstance().setLlaveUsuarioConectado(0);
		
		MainView mainView = (MainView)this.mainWindow;
		AppManager.getInstance().habilitarConexion(mainView, true);
		ContabilidadGUI.getInstance().habilitarMenuesSegunPermisos();
		mainView.getLblUsuario().setText("Usuario:");
		AppManager.getInstance().getAdminSystems().getSystemUsuario().logout();
	}	
}
