package muni.expedientes.ABMFaseProcedimiento;

import javax.faces.component.UIComponent;

import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class FaseProcedimientoModel extends ABMModel {

	
	@Override
	public String getReglaNavegacion() {

		return "ABMFaseProcedimiento";
	}

	@Override
	public String getNombreEntidad() {
		return "FaseProcedimiento";
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];
		int pos = 0;
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMFaseProcedimiento abmFaseProcedimiento = getBeanFaseProcedimiento();

	}

	private ABMFaseProcedimiento getBeanFaseProcedimiento() {
		return (ABMFaseProcedimiento) getRequestBean("expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento");
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {

			FaseProcedimiento locFaseProcedimiento = (FaseProcedimiento) pObject;

			getCommunicationExpedientesBean().getRemoteSystemProcedimientos().setLlave(
					getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemProcedimientos().addFaseProcedimiento(locFaseProcedimiento);

			return "El FaseProcedimiento se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			// TODO Auto-generated method stub

		}

		@Override
		public ABMModel getModel() {

			return FaseProcedimientoModel.this;

		}

	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {

			FaseProcedimiento locFaseProcedimiento = (FaseProcedimiento) pObject;

			getCommunicationExpedientesBean().getRemoteSystemProcedimientos().setLlave(
					getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemProcedimientos().updateFaseProcedimiento(locFaseProcedimiento);

			return "El FaseProcedimiento se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {

			return FaseProcedimientoModel.this;

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

		}

		@Override
		public ABMModel getModel() {
			return FaseProcedimientoModel.this;
		}

	}

	public class EliminarControler extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {

			FaseProcedimiento locFaseProcedimiento = (FaseProcedimiento) pObject;
			getCommunicationExpedientesBean().getRemoteSystemProcedimientos().setLlave(
					getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemProcedimientos().deleteFaseProcedimiento(locFaseProcedimiento);

			return "El FaseProcedimiento se elimin\363 exitosamente";

		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {
			return FaseProcedimientoModel.this;
		}

	}
	
	
	
}
