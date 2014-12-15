package muni.habilitaciones.grpSHPS.ABMInspector;

import javax.faces.component.UIComponent;

import com.trascender.habilitaciones.recurso.filtros.FiltroInspector;
import com.trascender.habilitaciones.recurso.persistent.shps.Inspector;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ActivarAbstractController;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class InspectorModel extends ABMModel{
	
	@Override
	public String getReglaNavegacion() {
		return "ABMInspector";
	}

	@Override
	public String getNombreEntidad() {
		return "Inspector";
	}

	private ABMInspector getBeanInspector() {
		return (ABMInspector) getRequestBean("habilitaciones$grpSHPS$ABMInspector$ABMInspector");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		int pos = 0;
		noVacios[pos] = getBeanInspector().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanInspector().getTfPersona();
		nomNoVacios[pos++] = "Persona";

		v.noSonVacios(noVacios, nomNoVacios);

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		getBeanInspector().getTfNombre().setDisabled(true);
		getBeanInspector().getBtnSeleccionarPersona().setRendered(false);
		getBeanInspector().getBtnLimpiarPersona().setRendered(false);
		getBeanInspector().getTaComentarioLogAuditoria().setRendered(false);
		getBeanInspector().getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarInspectorController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Inspector locInspector = (Inspector) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemInspectores().setLlave(getSessionBean1().getLlave());
	        getCommunicationHabilitacionesBean().getRemoteSystemInspectores().addInspector(locInspector);
			return "El Inspector se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return InspectorModel.this;
		}

	}

	public class ModificarInspectorController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Inspector locInspector = (Inspector) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemInspectores().setLlave(getSessionBean1().getLlave());
	        getCommunicationHabilitacionesBean().getRemoteSystemInspectores().updateInspector(locInspector);
			return "El Inspector se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return InspectorModel.this;
		}
	}

	public class ConsultarInspectorController extends ConsultarAbstractController {

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
			return InspectorModel.this;
		}

	}

	public class EliminarInspectorController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Inspector locInspector = (Inspector) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemInspectores().setLlave(getSessionBean1().getLlave());
	        getCommunicationHabilitacionesBean().getRemoteSystemInspectores().deleteInspector(locInspector);
			return "El Inspector se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return InspectorModel.this;
		}

	}
}
