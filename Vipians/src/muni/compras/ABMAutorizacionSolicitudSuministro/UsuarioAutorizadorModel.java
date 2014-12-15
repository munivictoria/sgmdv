/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMAutorizacionSolicitudSuministro;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorSolicitudSuministro;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author Fer Luca
 */
public class UsuarioAutorizadorModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		// TODO Auto-generated method stub
		return "ABMUsuarioAutorizador";
	}

	@Override
	public String getNombreEntidad() {
		// TODO Auto-generated method stub
		return "Usuario Autorizador";
	}

	private ABMUsuarioAutorizador getBeanUsuarioAutorizador() {
		return (ABMUsuarioAutorizador) getRequestBean("compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizador");
	}

	private Validador getValidadorAgregarModificar() {

		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];

		int pos = 0;
		noVacios[pos] = getBeanUsuarioAutorizador().getTfUsuario();
		nomNoVacios[pos++] = "Usuario";

		v.noSonVacios(noVacios, nomNoVacios);

		UsuarioAutorizadorSolicitudSuministro locUsuario = (UsuarioAutorizadorSolicitudSuministro) getBeanUsuarioAutorizador().getElementoPila()
				.getObjetos().get(0);

		return v;
	}

	private void ocultarConsultarEliminar() {
		getBeanUsuarioAutorizador().getBtnAgregar().setRendered(false);
		getBeanUsuarioAutorizador().getBtnQuitar().setRendered(false);
		getBeanUsuarioAutorizador().getBtnQuitarTodos().setRendered(false);
		getBeanUsuarioAutorizador().getTfUsuario().setDisabled(true);
		getBeanUsuarioAutorizador().getBtnSeleccionarUsuario().setRendered(false);
		getBeanUsuarioAutorizador().getBtnLimpiarUsuario().setRendered(false);
		getBeanUsuarioAutorizador().getGroupPanel1().setRendered(false);
		getBeanUsuarioAutorizador().getTableColumn1().setRendered(false);
		getBeanUsuarioAutorizador().getGpEstadoFinalizable().setRendered(false);
		getBeanUsuarioAutorizador().getGpEstadoFinalizacion().setRendered(false);
		getBeanUsuarioAutorizador().getTcRbEstadoFinalizable().setRendered(false);
		getBeanUsuarioAutorizador().getTcRbEstadoFinalizacion().setRendered(false);
		getBeanUsuarioAutorizador().getCbOperaUrgentes().setDisabled(true);
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			getBeanUsuarioAutorizador().getRequestBean1().setObjetoSeleccion(pObject);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {
			return UsuarioAutorizadorModel.this;
		}

	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			// getBeanUsuarioAutorizadorSuplente().getRequestBean1().setObjetoSeleccion(pObject);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {
			return UsuarioAutorizadorModel.this;
		}
	}

	public class ConsultarController extends ConsultarAbstractController {

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
			ocultarConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return UsuarioAutorizadorModel.this;
		}
	}

}
