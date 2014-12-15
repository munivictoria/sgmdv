package muni.habilitaciones.grpTipoParametro.ABMPlan;

import javax.faces.component.UIComponent;

import com.trascender.habilitaciones.recurso.persistent.Plan;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class PlanModel extends ABMModel {

	private ABMPlan getBeanPlan() {
		return (ABMPlan) getRequestBean("habilitaciones$grpTipoParametro$ABMPlan$ABMPlan");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];

		int pos = 0;
		noVacios[pos] = getBeanPlan().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanPlan().getDdTipoObligacion();
		nomNoVacios[pos++] = "TipoObligacion";

		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		getBeanPlan().getTfNombre().setDisabled(true);
		getBeanPlan().getDdTipoObligacion().setDisabled(true);
		getBeanPlan().cbPlanPorDefecto.setDisabled(true);
		getBeanPlan().getTaComentarioLogAuditoria().setRendered(false);
		getBeanPlan().getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Plan locPlan = (Plan) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(getSessionBean1().getLlave());
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().addPlan(locPlan);
			return "El Plan se agreg\363 correctamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return PlanModel.this;
		}
	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Plan locPlan = (Plan) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(getSessionBean1().getLlave());
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().updatePlan(locPlan);
			return "El Plan se modific\363 correctamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return PlanModel.this;
		}
	}

	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Plan locPlan = (Plan) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(getSessionBean1().getLlave());
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().deletePlan(locPlan);
			return "El Plan se elimin\363 correctamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return PlanModel.this;
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
			return PlanModel.this;
		}
	}

	@Override
	public String getReglaNavegacion() {
		return "ABMPlan";
	}

	@Override
	public String getNombreEntidad() {
		return "Plan";
	}

}
