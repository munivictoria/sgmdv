/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMRol;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author juanma
 */
public class RolModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMRol";
	}

	@Override
	public String getNombreEntidad() {
		return "Rol";
	}

	private ABMRol getBeanRol() {
		return (ABMRol) getRequestBean("framework$ABMRol$ABMRol");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];
		int pos = 0;
		noVacios[pos] = getBeanRol().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMRol abmRol = getBeanRol();
		abmRol.getTfNombre().setDisabled(true);
		abmRol.getCbAgregar().setDisabled(true);
		abmRol.getCbEditar().setDisabled(true);
		abmRol.getCbEliminar().setDisabled(true);
		abmRol.getCbBuscar().setDisabled(true);
		abmRol.getCbTodos().setDisabled(true);
		abmRol.getCbAuditar().setDisabled(true);
		abmRol.getTaComentarioLogAuditoria().setRendered(false);
		abmRol.getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarRolController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Rol locRol = (Rol) pObject;
			getComunicationBean().getRemoteSystemRol().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemRol().addRol(locRol);
			if (getComunicationBean().getListaPermisos() != null) {
				getComunicationBean().getListaPermisos().clear();
			}

			return "El rol se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return RolModel.this;
		}
	}

	public class ModificarRolController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Rol locRol = (Rol) pObject;
			getComunicationBean().getRemoteSystemRol().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemRol().updateRol(locRol);
			if (getComunicationBean().getListaPermisos() != null) {
				getComunicationBean().getListaPermisos().clear();
			}
			return "El rol se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return RolModel.this;
		}
	}

	public class ConsultarRolController extends ConsultarAbstractController {

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
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return RolModel.this;
		}
	}

	public class EliminarRolController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Rol locRol = (Rol) pObject;
			getComunicationBean().getRemoteSystemRol().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemRol().removeRol(locRol);
			if (getComunicationBean().getListaPermisos() != null) {
				getComunicationBean().getListaPermisos().clear();
			}

			return "El rol se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return RolModel.this;
		}
	}
}
