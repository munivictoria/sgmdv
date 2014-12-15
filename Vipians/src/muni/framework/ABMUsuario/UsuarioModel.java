/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMUsuario;

import java.util.ArrayList;

import javax.faces.component.UIComponent;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.presentacion.abstracts.controller.ABMController;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author juanma
 */
public class UsuarioModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMUsuario";
	}

	@Override
	public String getNombreEntidad() {
		return "Usuario";
	}

	private ABMUsuario getBeanUsuario() {
		return (ABMUsuario) getRequestBean("framework$ABMUsuario$ABMUsuario");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[4];
		String[] nomNoVacios = new String[4];
		int pos = 0;
		noVacios[pos] = getBeanUsuario().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanUsuario().getPfPassword1();
		nomNoVacios[pos++] = "Password";
		noVacios[pos] = getBeanUsuario().getPfConfirmPassword1();
		nomNoVacios[pos++] = "ConfirmarPassword";
		noVacios[pos] = getBeanUsuario().getTfPersona();
		nomNoVacios[pos++] = "Persona";

		ArrayList areas = (ArrayList) this.getBeanUsuario().getElementoPila().getObjetos().get(4);
		if(areas == null) {
			v.getErrores().add("Debe asignar al menos un Area");
		}
		
		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMUsuario abmUsuario = getBeanUsuario();
		abmUsuario.getTfNombre().setDisabled(true);
		abmUsuario.getPfPassword1().setDisabled(true);
		abmUsuario.getPfConfirmPassword1().setDisabled(true);
		abmUsuario.getTfPersona().setDisabled(true);
		abmUsuario.getBtnSeleccionarPersona().setRendered(false);
		abmUsuario.getGroupPanel1().setRendered(false);
		abmUsuario.getTableColumn1().setRendered(false);
		abmUsuario.getTableColumnArea1().setRendered(false);
		abmUsuario.getGroupPanel2().setRendered(false);
	}

	public class AgregarUsuarioController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Usuario locUsuario = (Usuario) pObject;
			getComunicationBean().getRemoteSystemUsuario().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemUsuario().addUsuario(locUsuario);

			return "El usuario se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return UsuarioModel.this;
		}
	}

	public class ModificarUsuarioController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Usuario locUsuario = (Usuario) pObject;
			getComunicationBean().getRemoteSystemUsuario().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemUsuario().updateUsuario(locUsuario);

			return "El usuario se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return UsuarioModel.this;
		}
	}

	public class ConsultarUsuarioController extends ConsultarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			return null;
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ABMUsuario abmUsuario = getBeanUsuario();
			abmUsuario.getTaComentarioLogAuditoria().setRendered(false);
			abmUsuario.getLblComentarioLogAuditoria().setRendered(false);
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return UsuarioModel.this;
		}
	}

	public class EliminarUsuarioController extends EliminarAbstractController {
		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Usuario locUsuario = (Usuario) pObject;
			getComunicationBean().getRemoteSystemUsuario().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemUsuario().removeUsuario(locUsuario);

			return "El usuario se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return UsuarioModel.this;
		}
	}

	public class RecuperarUsuario implements ABMController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Usuario locUsuario = (Usuario) pObject;
			getComunicationBean().getRemoteSystemUsuario().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemUsuario().restoreUsuario(locUsuario);

			return "El Usuario se recuper\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public boolean guardaEstadoObjetosUsados() {
			return false;
		}

		@Override
		public boolean mostrarBotonAceptar() {
			return true;
		}

		@Override
		public boolean mostrarBotonCancelar() {
			return true;
		}

		@Override
		public boolean mostrarStSeparador() {
			return true;
		}

		@Override
		public boolean recargarPaginaAdmin() {
			return true;
		}

		@Override
		public String getTextoBotonAceptar() {
			return "Recuperar Usuario";
		}

		@Override
		public String getTextoBotonCancelar() {
			return "Cancelar";
		}

		@Override
		public String getTituloPagina() {
			return "Recuperar";
		}

		@Override
		public String getCodigoColores() {
			return Constantes.COLORES_AGREGAR;
		}

		@Override
		public ABMModel getModel() {
			return UsuarioModel.this;
		}

		@Override
		public boolean seleccionarObjeto() {
			return true;
		}
	}
}
