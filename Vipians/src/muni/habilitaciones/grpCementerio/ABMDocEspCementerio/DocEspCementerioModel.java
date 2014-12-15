package muni.habilitaciones.grpCementerio.ABMDocEspCementerio;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class DocEspCementerioModel extends ABMModel{

	private ABMDocEspCementerio getBeanDocEspCementerio(){
		return (ABMDocEspCementerio) getRequestBean("habilitaciones$grpCementerio$ABMDocEspCementerio$ABMDocEspCementerio");
	}

	private Validador getValidadorAgregarModificar(){
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];

		int pos = 0;
		noVacios[pos] = getBeanDocEspCementerio().getTfPersona();
		nomNoVacios[pos++] = "Persona";

		Domicilio domicilio = getBeanDocEspCementerio().obtenerObjetoDelElementoPila(5, Domicilio.class);

		if (domicilio.getLocalidad() == null) {
			String msg = "Debe seleccionar un Domicilio Postal.";
			v.getErrores().add(msg);
		}

		v.noSonVacios(noVacios, nomNoVacios);

		return v;
	}
	private void deshabilitarElementosConsultarEliminar(){
		getBeanDocEspCementerio().getBtnLimpiarPersona().setRendered(false);
		getBeanDocEspCementerio().getBtnSeleccionarPersonaFisica().setRendered(false);
		getBeanDocEspCementerio().getBtnSeleccionarPersonaJuridica().setRendered(false);
		getBeanDocEspCementerio().getPanelAtributoDinamico().deshabilitarCampos();
		getBeanDocEspCementerio().getTfPersona().setDisabled(true);
		getBeanDocEspCementerio().getGroupPanel1().setRendered(false);
		getBeanDocEspCementerio().getTableColumn1().setRendered(false);
		getBeanDocEspCementerio().getBtnSeleccionarDomicilioPostal().setRendered(false);
		getBeanDocEspCementerio().getBtnSeleccionarDomicilioSolicitante().setRendered(false);
		getBeanDocEspCementerio().getBtnLimpiarDomicilioPostal().setRendered(false);
		getBeanDocEspCementerio().getTaComentarioLogAuditoria().setRendered(false);
		getBeanDocEspCementerio().getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarDocEspCementerioController extends AgregarAbstractController{

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion)pObject;
			Persona persona = getBeanDocEspCementerio().obtenerObjetoDelElementoPila(2, Persona.class);
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().addObligacion(persona, locObligacion);
			return "El Documento Cementerio se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return DocEspCementerioModel.this;
		}

	}
	public class ModificarDocEspCementerioController extends ModificarAbstractController{

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().updateObligacion(locObligacion);
			return "El Documento Cementerio se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {
			return DocEspCementerioModel.this;
		}
	}
	public class ConsultarDocEspCementerioController extends ConsultarAbstractController{

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
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
			return DocEspCementerioModel.this;
		}
	}
	public class EliminarDocEspCementerioController extends EliminarAbstractController{

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion) pObject;
			locObligacion.anular();
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().updateObligacion(locObligacion);
			return "El Documento Cementerio se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		} 

		@Override
		public ABMModel getModel() {
			return DocEspCementerioModel.this;
		}
	}
	@Override
	public String getReglaNavegacion() {
		return "ABMDocEspCementerio";
	}

	@Override
	public String getNombreEntidad() {
		return "Documento Especial Cementerio";
	}
}
