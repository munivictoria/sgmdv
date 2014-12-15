package muni.catastro.ABMParcela;

import com.trascender.catastro.recurso.persistent.FirmantePlanoConstruccion;
import com.trascender.catastro.recurso.persistent.PlanoConstruccion;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class PlanoConstruccionModel extends ABMModel {
	
	@Override
	public String getReglaNavegacion() {
		return "ABMPlanoConstruccion";
	}

	@Override
	public String getNombreEntidad() {
		return "Plano Construcción";
	}

	private ABMPlanoConstruccion getBeanPlanoConstruccion() {
		return (ABMPlanoConstruccion) getRequestBean("catastro$ABMParcela$ABMPlanoConstruccion");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		PlanoConstruccion locPlano = (PlanoConstruccion) getBeanPlanoConstruccion().obtenerObjetoDelElementoPila(0);
		for (FirmantePlanoConstruccion cadaFirmante : locPlano.getListaFirmantePlanoConstruccion()) {
			if (cadaFirmante.getCargo() == null) {
				v.getErrores().add("Debe seleccionar un Cargo para cada Firmante");
				break;
			}
		}
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMPlanoConstruccion abmPlanoConstruccion = getBeanPlanoConstruccion();
		
		abmPlanoConstruccion.getPanelAtributoDinamicoPlanoConstruccion().deshabilitarCampos();
		abmPlanoConstruccion.getTfNroPlantas().setDisabled(true);
		abmPlanoConstruccion.getTfFechaPlanoConstruccion().setDisabled(true);
		abmPlanoConstruccion.getTfNroExpedientePlanoConstruccion().setDisabled(true);
		abmPlanoConstruccion.getRbgEstadoPlanoConstruccion().setDisabled(true);
		abmPlanoConstruccion.getTfSuperficieCubiertaPlantaAlta().setDisabled(true);
		abmPlanoConstruccion.getTfSuperficieCubiertaPlantaBaja().setDisabled(true);
		abmPlanoConstruccion.getTfSuperficieSemiCubiertaPlantaAlta().setDisabled(true);
		abmPlanoConstruccion.getTfSuperficieSemiCubiertaPlantaBaja().setDisabled(true);
		abmPlanoConstruccion.getDdCargo().setDisabled(true);
		abmPlanoConstruccion.getTfFechaFirma().setDisabled(true);
		abmPlanoConstruccion.getTableColumn1().setRendered(false);
		abmPlanoConstruccion.getGroupPanel1().setRendered(false);
		abmPlanoConstruccion.getLblComentario2().setRendered(false);
		abmPlanoConstruccion.getTaComentario2().setRendered(false);
		abmPlanoConstruccion.getBtnSeleccionarEstadisticaIndec().setRendered(false);
		abmPlanoConstruccion.getBtnLimpiarEstadisticaIndec().setRendered(false);
	}

	public class AgregarPlanoConstruccionController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			PlanoConstruccion locPlano = (PlanoConstruccion) pObject;

			getBeanPlanoConstruccion().getRequestBean1().setObjetoSeleccion(locPlano);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return PlanoConstruccionModel.this;
		}
	}

	public class ModificarPlanoConstruccionController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			getBeanPlanoConstruccion().getRequestBean1().setObjetoSeleccion(null);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return PlanoConstruccionModel.this;
		}
	}

	public class ConsultarPlanoConstruccionController extends ConsultarAbstractController {

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
			return PlanoConstruccionModel.this;
		}
	}

	public class EliminarPlanoConstruccionController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			PlanoConstruccion locPlanoConstruccion = (PlanoConstruccion) pObject;

			return "El plano se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return PlanoConstruccionModel.this;
		}
	}

}
