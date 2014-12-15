/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMAutorizacionSolicitudSuministro;

import javax.faces.component.UIComponent;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author Fer Luca
 */
public class UsuarioAutorizadorSuplenteModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMUsuarioAutorizadorSuplente";
	}

	@Override
	public String getNombreEntidad() {
		return "Usuario Autorizador Suplente";
	}

	private ABMUsuarioAutorizadorSuplente getBeanUsuarioAutorizadorSuplente() {
		return (ABMUsuarioAutorizadorSuplente) getRequestBean("compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente");
	}

	private Validador getValidadorAgregarModificar() {

		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[3];
		String[] nomNoVacios = new String[3];

		int pos = 0;
		noVacios[pos] = getBeanUsuarioAutorizadorSuplente().getTfUsuarioSuplente();
		nomNoVacios[pos++] = "Usuario Suplente";
		noVacios[pos] = getBeanUsuarioAutorizadorSuplente().getTfFechaDesde();
		nomNoVacios[pos++] = "Fecha Suplencia Desde";
		noVacios[pos] = getBeanUsuarioAutorizadorSuplente().getTfFechaHasta();
		nomNoVacios[pos++] = "Fecha Suplencia Hasta";

		v.noSonVacios(noVacios, nomNoVacios);

		return v;
	}

	private void ocultarConsultarEliminar() {
		getBeanUsuarioAutorizadorSuplente().getBtnLimpiarUsuario().setRendered(false);
		getBeanUsuarioAutorizadorSuplente().getBtnSeleccionarUsuario().setRendered(false);
		getBeanUsuarioAutorizadorSuplente().getDdUsuarios().setDisabled(true);
		getBeanUsuarioAutorizadorSuplente().getTfFechaDesde().setDisabled(true);
		getBeanUsuarioAutorizadorSuplente().getTfFechaHasta().setDisabled(true);
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			getBeanUsuarioAutorizadorSuplente().getRequestBean1().setObjetoSeleccion(pObject);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {
			return UsuarioAutorizadorSuplenteModel.this;
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
			return UsuarioAutorizadorSuplenteModel.this;
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
			return UsuarioAutorizadorSuplenteModel.this;
		}
	}

}
