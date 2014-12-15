package muni.habilitaciones.grpTasaMenor.ABMDocEspTasaMenor;

import java.util.List;

import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.DocumentoTasaMenor;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class DocEspTasaMenorModel extends ABMModel{

	@Override
	public String getReglaNavegacion() {
		return "ABMDocEspTasaMenor";
	}

	@Override
	public String getNombreEntidad() {
		return "Documentos Especiales Tasa Menor";
	}

	private ABMDocEspTasaMenor getBeanTasaMenor() {
		return (ABMDocEspTasaMenor) getRequestBean("habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$ABMDocEspTasaMenor");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();

        Domicilio domicilio = (Domicilio) getBeanTasaMenor().obtenerObjetoDelElementoPila(4, Domicilio.class);

        if (domicilio.getLocalidad() == null) {
            String msg = "Debe seleccionar un Domicilio Postal.";
            v.getErrores().add(msg);
        }

		return v;
	}
	
	private void deshabilitarPanelGroups() {
		DocumentoTasaMenor locDocumento = (DocumentoTasaMenor) getBeanTasaMenor().obtenerObjetoDelElementoPila(1, DocumentoTasaMenor.class);
		if(!locDocumento.getPlantillaDocumentoTasaMenor().isAsociacionAParcela()) {
			getBeanTasaMenor().getPgParcela().setRendered(false);
			getBeanTasaMenor().getBtnSeleccionarDomicilioParcela().setRendered(false);
        }
        if(!locDocumento.getPlantillaDocumentoTasaMenor().isPersonaPropietaria()) {
        	getBeanTasaMenor().getPgPersonaSolicitante().setRendered(false);
        }
        else {
        	getBeanTasaMenor().getPgPersona().setRendered(false);
        }
	}

	private void deshabilitarElementosConsultarEliminar() {
		deshabilitarPanelGroups();
		
		getBeanTasaMenor().getBtnLimpiarPersona().setRendered(false);
		getBeanTasaMenor().getBtnLimpiarParcela().setRendered(false);
		getBeanTasaMenor().getBtnLimpiarDomicilioPostal().setRendered(false);
		getBeanTasaMenor().getTableColumn1().setRendered(false);
		getBeanTasaMenor().getTfFechaInicio().setDisabled(true);
		getBeanTasaMenor().getTfFechaCese().setDisabled(true);
		getBeanTasaMenor().getBtnSeleccionarDomicilioParcela().setRendered(false);
		getBeanTasaMenor().getBtnSeleccionarDomicilioPostal().setRendered(false);
		getBeanTasaMenor().getBtnSeleccionarDomicilioSolicitante().setRendered(false);
		getBeanTasaMenor().getBtnSeleccionarParcela().setRendered(false);
		getBeanTasaMenor().getBtnSeleccionarPersona().setRendered(false);
		getBeanTasaMenor().getBtnSeleccionarPersonaJuridica().setRendered(false);
		getBeanTasaMenor().getTaComentarioLogAuditoria().setRendered(false);
		getBeanTasaMenor().getLblComentarioLogAuditoria().setRendered(false);
		getBeanTasaMenor().getPanelAtributoDinamico().deshabilitarCampos();
	}
	
	private void setearAtributoDinamico(Obligacion pObligacion) {
		List atributosDinamicos = (List) this.getBeanTasaMenor().obtenerObjetoDelElementoPila(5, List.class);
		pObligacion.getDocumentoEspecializado().setListaAtributosDinamicos(atributosDinamicos);
	}

	public class AgregarDocEspTasaMenorController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion)pObject;
			Persona persona = getBeanTasaMenor().obtenerObjetoDelElementoPila(2, Persona.class);
			setearAtributoDinamico(locObligacion);
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().addObligacion(persona, locObligacion);
			
			return "La Obligaci\363n de Tasa Menor se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarPanelGroups();
		}

		@Override
		public ABMModel getModel() {
			return DocEspTasaMenorModel.this;
		}
	}

	public class ModificarDocEspTasaMenorController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion) pObject;   
			setearAtributoDinamico(locObligacion);
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().updateObligacion(locObligacion);
			
			return "La Obligaci\363n de Tasa Menor se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarPanelGroups();
		}

		@Override
		public ABMModel getModel() {
			return DocEspTasaMenorModel.this;
		}
	}

	public class ConsultarDocEspTasaMenorController extends ConsultarAbstractController {

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
			return DocEspTasaMenorModel.this;
		}

	}

	public class EliminarDocEspTasaMenorController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion) pObject;
			locObligacion.anular();
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().updateObligacion(locObligacion);
			return "La Obligaci\363n de Tasa Menor se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return DocEspTasaMenorModel.this;
		}
	}
}