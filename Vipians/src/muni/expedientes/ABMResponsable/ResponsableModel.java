package muni.expedientes.ABMResponsable;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class ResponsableModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {

		return "ABMResponsable";
	}

	@Override
	public String getNombreEntidad() {
		return "Responsable";

	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
//		UIComponent[] noVacios = new UIComponent[1];
//		String[] nomNoVacios = new String[1];
//		int pos = 0;
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
//		ABMResponsable abmResponsable = getBeanResponsable();

	}

//	private ABMResponsable getBeanResponsable() {
//		return (ABMResponsable) getRequestBean("expedientes$ABMResponsable$ABMResponsable");
//	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
//
//			Responsable locResponsable = (Responsable) pObject;

			getCommunicationExpedientesBean().getRemoteSystemProcedimientos().setLlave(
					getSessionBean1().getLlave());
			

			return "El Responsable se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			// TODO Auto-generated method stub

		}

		@Override
		public ABMModel getModel() {

			return ResponsableModel.this;

		}

	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {

//			Responsable locResponsable = (Responsable) pObject;

			

			return "El Responsable se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {

			return ResponsableModel.this;

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
			return ResponsableModel.this;
		}

	}

	public class EliminarControler extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {

//			Responsable locResponsable = (Responsable) pObject;
		

			return "El Responsable se elimin\363 exitosamente";

		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return ResponsableModel.this;
		}

	}
	
	
}
