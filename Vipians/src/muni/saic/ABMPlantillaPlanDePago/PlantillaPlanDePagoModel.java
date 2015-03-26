/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.saic.ABMPlantillaPlanDePago;

import javax.faces.component.UIComponent;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;
import com.trascender.saic.recurso.persistent.PlantillaPlanDePago;

/**
 * 
 * @author juanma
 */
public class PlantillaPlanDePagoModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMPlantillaPlanDePago";
	}

	@Override
	public String getNombreEntidad() {
		return "PlantillaPlanDePago";
	}

	private ABMPlantillaPlanDePago getBeanPlantillaPlanDePago() {
		return (ABMPlantillaPlanDePago) getRequestBean("saic$ABMPlantillaPlanDePago$ABMPlantillaPlanDePago");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];
		int pos = 0;
		noVacios[pos] = getBeanPlantillaPlanDePago().getTfNombre();
		nomNoVacios[pos++] = "Nombre";

		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMPlantillaPlanDePago abmPlantillaPlanDePago = getBeanPlantillaPlanDePago();
		abmPlantillaPlanDePago.getTfNombre().setDisabled(true);
		abmPlantillaPlanDePago.getTaDescripcion().setDisabled(true);
		abmPlantillaPlanDePago.getTaComentarioLogAuditoria().setRendered(false);
		abmPlantillaPlanDePago.getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			PlantillaPlanDePago locPlantillaPlanDePago = (PlantillaPlanDePago) pObject;
			getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().setLlave(getSessionBean1().getLlave());
			getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().addPlantillaPlanDePago(locPlantillaPlanDePago);

			return "La Plantilla se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return PlantillaPlanDePagoModel.this;
		}
	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			PlantillaPlanDePago locPlantillaPlanDePago = (PlantillaPlanDePago) pObject;
			getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().setLlave(getSessionBean1().getLlave());
			getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().updatePlantillaPlanDePago(locPlantillaPlanDePago);
			return "La Plantilla se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return PlantillaPlanDePagoModel.this;
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
			return PlantillaPlanDePagoModel.this;
		}
	}

	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			PlantillaPlanDePago locPlantillaPlanDePago = (PlantillaPlanDePago) pObject;
			getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().setLlave(getSessionBean1().getLlave());
			getCommunicationSAICBean().getRemoteSystemEstadoCuentaContribuyente().deletePlantillaPlanDePago(locPlantillaPlanDePago);

			return "La Plantilla se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return PlantillaPlanDePagoModel.this;
		}
	}
}