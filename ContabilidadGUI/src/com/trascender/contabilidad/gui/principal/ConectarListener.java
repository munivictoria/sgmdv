package com.trascender.contabilidad.gui.principal;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.gui.framework.login.Login;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.principal.MainView;

public class ConectarListener implements ActionListener {

	private final Component mainWindow;

	public ConectarListener(Component mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.conectar();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg((this.mainWindow), ex.getMessage());
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
			ContabilidadGUI.getInstance().getAdminSystemsContabilidad().setLlave(llave);

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
				ContabilidadGUI.getInstance().habilitarMenuesSegunPermisos();
				ContabilidadGUI.getInstance().setMunicipalidad(ContabilidadGUI.getInstance().getAdminSystems().getSystemMunicipalidad().getMunicipalidad());
			}
			else {
				AppManager.getInstance().habilitarConexion(mainView, true);
				ContabilidadGUI.getInstance().habilitarMenuesSegunPermisos();
			}
		}
		else {
			// TODO: Cuando la llave == 0 ...
		}
	}
}
