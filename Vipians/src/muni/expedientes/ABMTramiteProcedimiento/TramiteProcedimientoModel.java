/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.ABMTramiteProcedimiento;

import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class TramiteProcedimientoModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMTramiteProcedimiento";
	}

	@Override
	public String getNombreEntidad() {
		return "TramiteProcedimiento";
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		// UIComponent[] noVacios = new UIComponent[1];
		// String[] nomNoVacios = new String[1];
		// int pos = 0;

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		// ABMTramiteProcedimiento abmTramiteProcedimiento = getBeanTramiteProcedimiento();
	}

	@SuppressWarnings("unused")
	private ABMTramiteProcedimiento getBeanTramiteProcedimiento() {
		return (ABMTramiteProcedimiento) getRequestBean("expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento");
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TramiteProcedimiento locTramiteProcedimiento = (TramiteProcedimiento) pObject;

			getCommunicationExpedientesBean().getRemoteSystemProcedimientos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemProcedimientos().addTramiteProcedimiento(locTramiteProcedimiento);

			return "El TramiteProcedimiento se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return TramiteProcedimientoModel.this;
		}

	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TramiteProcedimiento locTramiteProcedimiento = (TramiteProcedimiento) pObject;

			getCommunicationExpedientesBean().getRemoteSystemProcedimientos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemProcedimientos().updateTramiteProcedimiento(locTramiteProcedimiento);

			return "El TramiteProcedimiento se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return TramiteProcedimientoModel.this;
		}

	}

	public class ConsultarControler extends ConsultarAbstractController {

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
			return TramiteProcedimientoModel.this;
		}

	}

	public class EliminarControler extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TramiteProcedimiento locTramiteProcedimiento = (TramiteProcedimiento) pObject;
			getCommunicationExpedientesBean().getRemoteSystemProcedimientos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemProcedimientos().deleteTramiteProcedimiento(locTramiteProcedimiento);

			return "El TramiteProcedimiento se elimin\363 exitosamente";

		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return TramiteProcedimientoModel.this;
		}

	}

}