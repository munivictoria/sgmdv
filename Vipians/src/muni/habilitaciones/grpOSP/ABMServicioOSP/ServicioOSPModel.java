package muni.habilitaciones.grpOSP.ABMServicioOSP;

import javax.faces.component.UIComponent;

import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class ServicioOSPModel extends ABMModel {
	@Override
	public String getReglaNavegacion() {
		return "ABMServicioOSP";
	}

	@Override
	public String getNombreEntidad() {
		return "Servicios OSP";
	}
	
	private ABMServicioOSP getBeanAbmServicioOSP () {
		return (ABMServicioOSP) getRequestBean("habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP");
	}

	private Validador getValidadorAgregarModificar() {

		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[3];
		String[] nomNoVacios = new String[3];
		int pos = 0;
		noVacios[pos] = getBeanAbmServicioOSP().getTfCodigo();
		nomNoVacios[pos++] = "Código";
		noVacios[pos] = getBeanAbmServicioOSP().getTaNombre();
		nomNoVacios[pos++] = "Descripción";
		noVacios[pos] = getBeanAbmServicioOSP().getTfValor();
		nomNoVacios[pos++] = "Valor Mínimo";

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
			ServicioOSP locServicioOSP = (ServicioOSP) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().setLlave(getSessionBean1().getLlave());
			locServicioOSP = getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().addServicioOSP(locServicioOSP);

			return "El Estado de Solicitud de Suministro se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ServicioOSPModel.this;
		}
	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ServicioOSP locServicioOSP = (ServicioOSP) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().setLlave(getSessionBean1().getLlave());
			locServicioOSP = getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().updateServicioOSP(locServicioOSP);

			return "El Estado de Solicitud de Suministro se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {
			return ServicioOSPModel.this;
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
			return ServicioOSPModel.this;
		}
	}

	private void deshabilitarElementosConsultarEliminar() {

		getBeanAbmServicioOSP().getTaNombre().setDisabled(true);
		getBeanAbmServicioOSP().getTfBaseConsumo().setDisabled(true);
		getBeanAbmServicioOSP().getTfCodigo().setDisabled(true);
		getBeanAbmServicioOSP().getTfCoeficienteCodigo().setDisabled(true);
		getBeanAbmServicioOSP().getTfCoeficienteValorEdificado().setDisabled(true);
		getBeanAbmServicioOSP().getTfCoeficienteValorTerreno().setDisabled(true);
		getBeanAbmServicioOSP().getTfValor().setDisabled(true);
		getBeanAbmServicioOSP().getTfValorPorExcedente().setDisabled(true);
		getBeanAbmServicioOSP().getDdPeriodicidad().setDisabled(true);
		getBeanAbmServicioOSP().getDdUnidadMedida().setDisabled(true);
		getBeanAbmServicioOSP().getCbMedido().setDisabled(true);
		getBeanAbmServicioOSP().getCbVolcadoEfluentesIndustriales().setDisabled(true);
		getBeanAbmServicioOSP().getTaComentarioLogAuditoria().setRendered(false);
		getBeanAbmServicioOSP().getLblComentarioLogAuditoria().setRendered(false);
	}

	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ServicioOSP locServicioOSP = (ServicioOSP) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().setLlave(getSessionBean1().getLlave());
			getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().deleteServicioOSP(locServicioOSP);

			return "El Servicio se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return ServicioOSPModel.this;
		}
	}
}
