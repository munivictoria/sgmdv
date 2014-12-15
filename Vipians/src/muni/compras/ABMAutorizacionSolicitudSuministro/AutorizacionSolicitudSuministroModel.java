/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMAutorizacionSolicitudSuministro;

import com.trascender.compras.recurso.persistent.suministros.AutorizacionSolicitudSuministro;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author ubuntero
 */
public class AutorizacionSolicitudSuministroModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMAutorizacionSolicitudSuministro";
	}

	@Override
	public String getNombreEntidad() {
		return "Autorizacion de Solicitud de Suministro";
	}

	private ABMAutorizacionSolicitudSuministro getBeanAutorizacionSolicitudSuministro() {
		return (ABMAutorizacionSolicitudSuministro) getRequestBean("compras$ABMAutorizacionSolicitudSuministro$ABMAutorizacionSolicitudSuministro");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();

		AutorizacionSolicitudSuministro locAutorizacionSolicitud = this.getBeanAutorizacionSolicitudSuministro()
				.obtenerObjetoDelElementoPila(0, AutorizacionSolicitudSuministro.class);
		if (locAutorizacionSolicitud.getListaUsuarios().isEmpty()) {
			v.getErrores().add("Debe agregar al menos un Usuario Autorizador.");
		}
		if (locAutorizacionSolicitud.getListaReglasFirma().isEmpty()) {
			v.getErrores().add("Debe agregar al menos una Regla de Firma.");
		}

		return v;
	}

	private void ocultarConsultarEliminar() {

		getBeanAutorizacionSolicitudSuministro().getTfMontoMaximo().setDisabled(true);
		getBeanAutorizacionSolicitudSuministro().getTfMontoMinimo().setDisabled(true);
		getBeanAutorizacionSolicitudSuministro().getTfPeriodoNumero().setDisabled(true);
		getBeanAutorizacionSolicitudSuministro().getBtnAgregar().setRendered(false);
		getBeanAutorizacionSolicitudSuministro().getBtnQuitar().setRendered(false);
		getBeanAutorizacionSolicitudSuministro().getBtnQuitarTodos().setRendered(false);
		getBeanAutorizacionSolicitudSuministro().getBtnAgregarRegla().setRendered(false);
		getBeanAutorizacionSolicitudSuministro().getBtnQuitarRegla().setRendered(false);
		getBeanAutorizacionSolicitudSuministro().getBtnQuitarTodosRegla().setRendered(false);
		getBeanAutorizacionSolicitudSuministro().getBtnAgregarSuplente().setRendered(false);
		getBeanAutorizacionSolicitudSuministro().getBtnModificarSuplente().setRendered(false);
		getBeanAutorizacionSolicitudSuministro().getBtnQuitarSuplente().setRendered(false);
		getBeanAutorizacionSolicitudSuministro().getBtnQuitarTodosSuplente().setRendered(false);
		getBeanAutorizacionSolicitudSuministro().getBtnModificarRegla().setRendered(false);
		getBeanAutorizacionSolicitudSuministro().getBtnModificar().setRendered(false);

		getBeanAutorizacionSolicitudSuministro().getTableColumn1().setRendered(false);
		getBeanAutorizacionSolicitudSuministro().getTableColumn3().setRendered(false);
		getBeanAutorizacionSolicitudSuministro().getStSeparador1().setRendered(false);
		getBeanAutorizacionSolicitudSuministro().getStSeparador2().setRendered(false);
		getBeanAutorizacionSolicitudSuministro().getDdArea().setDisabled(true);
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			AutorizacionSolicitudSuministro locAutorizacionSolSuministro = (AutorizacionSolicitudSuministro) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().addAutorizacionSolicitudSuministro(
					locAutorizacionSolSuministro);
			return "La autorizaci\363n se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {
			return AutorizacionSolicitudSuministroModel.this;
		}

	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			AutorizacionSolicitudSuministro locAutorizacionSolSuministro = (AutorizacionSolicitudSuministro) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().updateAutorizacionSolicitudSuministro(
					locAutorizacionSolSuministro);
			return "La autorizaci\363n se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {
			return AutorizacionSolicitudSuministroModel.this;
		}
	}

	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			AutorizacionSolicitudSuministro locAutorizacionSolSuministro = (AutorizacionSolicitudSuministro) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().deleteAutorizacionSolicitudSuministro(
					locAutorizacionSolSuministro);
			return "La autorizaci\363n se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ocultarConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return AutorizacionSolicitudSuministroModel.this;
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
			getBeanAutorizacionSolicitudSuministro().getLblComentarioLogAuditoria().setRendered(false);
			getBeanAutorizacionSolicitudSuministro().getTaComentarioLogAuditoria().setRendered(false);
			ocultarConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return AutorizacionSolicitudSuministroModel.this;
		}
	}
}
