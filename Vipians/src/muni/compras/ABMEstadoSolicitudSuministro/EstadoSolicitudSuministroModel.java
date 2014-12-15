/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMEstadoSolicitudSuministro;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.suministros.EstadoSolicitudSuministro;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author danilo
 */
public class EstadoSolicitudSuministroModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMEstadoSolicitudSuministro";
	}

	@Override
	public String getNombreEntidad() {
		return "Estado Solicitud de Suministro";
	}

	private ABMEstadoSolicitudSuministro getBeanEstadoSolicitudSuministro() {
		return (ABMEstadoSolicitudSuministro) getRequestBean("compras$ABMEstadoSolicitudSuministro$ABMEstadoSolicitudSuministro");
	}

	private Validador getValidadorAgregarModificar() {

		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];
		int pos = 0;
		noVacios[pos] = getBeanEstadoSolicitudSuministro().getTfNombre();
		nomNoVacios[pos++] = "Nombre";

		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			EstadoSolicitudSuministro locEstadoSolicitudSuministro = (EstadoSolicitudSuministro) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(getSessionBean1().getLlave());
			locEstadoSolicitudSuministro = getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().addEstadoSolicitudSuministro(
					locEstadoSolicitudSuministro);

			getApplicationBean1().agregarNuevoEstadoSolicitudSuministro(locEstadoSolicitudSuministro);
			return "El Estado de Solicitud de Suministro se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return EstadoSolicitudSuministroModel.this;
		}

	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			EstadoSolicitudSuministro locEstadoSolicitudSuministro = (EstadoSolicitudSuministro) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().updateEstadoSolicitudSuministro(
					locEstadoSolicitudSuministro);

			getApplicationBean1().modificarEstadoSolicitudSuministro(locEstadoSolicitudSuministro);
			return "El Estado de Solicitud de Suministro se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {
			return EstadoSolicitudSuministroModel.this;
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
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return EstadoSolicitudSuministroModel.this;
		}
	}

	private void deshabilitarElementosConsultarEliminar() {

		getBeanEstadoSolicitudSuministro().getTfNombre().setDisabled(true);
		getBeanEstadoSolicitudSuministro().getTaDescripcion().setDisabled(true);
		getBeanEstadoSolicitudSuministro().getCbEsModificable().setDisabled(true);
		getBeanEstadoSolicitudSuministro().getCbEstadoFinal().setDisabled(true);
		getBeanEstadoSolicitudSuministro().getCbEstadoInicial().setDisabled(true);
		getBeanEstadoSolicitudSuministro().getCbUsadoEnContratacion().setDisabled(true);
		getBeanEstadoSolicitudSuministro().getCbUsable().setDisabled(true);
	}

	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			EstadoSolicitudSuministro locEstadoSolicitud = (EstadoSolicitudSuministro) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().deleteEstadoSolicitudSuministro(locEstadoSolicitud);

			getApplicationBean1().eliminarEstadoSolicitudSuministro(locEstadoSolicitud);
			return "El Estado se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return EstadoSolicitudSuministroModel.this;
		}
	}
}
