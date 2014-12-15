package com.trascender.caja.gui.principal;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.gui.framework.login.Login;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.principal.MainView;
import com.trascender.gui.framework.util.Constantes;

public class ConectarListener implements ActionListener {
	
	private Component mainWindow;
	
	public ConectarListener(Component mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.conectar();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(((MainView)this.mainWindow), ex.getMessage());
		}
	}
	
	/**
	 * Conecta el usuario y actualiza la barra de estado con el nombre del
	 * usuario que ingresó.
	 * @throws Exception
	 */
	private void conectar() throws Exception {
		Login login = new Login((JFrame)this.mainWindow);
		
		long llave = login.open();
		
		
		if (llave != 0) {
			CajaGUI.getInstance().getAdminSystemsCaja().setLlave(llave);
			
			Usuario usuario = AppManager.getInstance().getAdminSystems().getSystemUsuario().findUsuarioPorLlave(llave);
			
			MainView mainView = (MainView)this.mainWindow;
			if (usuario != null) {
				AppManager.getInstance().setUsuario(usuario);
				
				String nombreUsuario = "Usuario: ";
				PersonaFisica personaUsuario = AppManager.getInstance().getAdminSystems().getSystemPersonaFisica().getPersonaFisicaPorId(usuario.getIdPersonaFisica());
				if (personaUsuario != null) {
					 nombreUsuario += personaUsuario.getNombre() + " " + personaUsuario.getApellido() + " ";  
				}
				
				nombreUsuario +=  "[" + usuario.toString() + "]";
				mainView.getLblUsuario().setText(nombreUsuario);
				
				AppManager.getInstance().habilitarConexion(mainView, false);
				CajaGUI.getInstance().habilitarMenuesSegunPermisos();
				
				if (!CajaGUI.getInstance().getUsuario().getUser().equals(Constantes.SUPER_USUARIO) && 
						CajaGUI.getInstance().getCaja() != null) {
					
					CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionPlanillaDiaria().getCajaActual(CajaGUI.getInstance().getCaja().getIpAddress());
					CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos().setSystemPlanillaDiaria(CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionPlanillaDiaria());
				}
			}
			else {
				AppManager.getInstance().habilitarConexion(mainView, true);
				CajaGUI.getInstance().habilitarMenuesSegunPermisos();
			}
		}
	}
}
