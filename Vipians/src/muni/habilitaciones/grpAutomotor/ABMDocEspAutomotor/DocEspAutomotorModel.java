/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.grpAutomotor.ABMDocEspAutomotor;

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

/**
 *
 * @author Fer Luca
 */
public class DocEspAutomotorModel extends ABMModel {

	private ABMDocEspAutomotor getBeanDocEspAutomotor(){
		return (ABMDocEspAutomotor) getRequestBean("habilitaciones$grpAutomotor$ABMDocEspAutomotor$ABMDocEspAutomotor");
	}

	private Validador getValidadorAgregarModificar(){
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];

		int pos = 0;
		noVacios[pos] = getBeanDocEspAutomotor().getTfVehiculo();
		nomNoVacios[pos++] = "Vehículo";

		Domicilio domicilio = getBeanDocEspAutomotor().obtenerObjetoDelElementoPila(5, Domicilio.class);

		if (domicilio.getLocalidad() == null) {
			String msg = "Debe seleccionar un Domicilio Postal.";
			v.getErrores().add(msg);
		}
		
		Obligacion locObligacion = (Obligacion) getBeanDocEspAutomotor().obtenerObjetoDelElementoPila(0);
		if (locObligacion != null && locObligacion.getPersona() == null) {
			v.getErrores().add("Debe seleccionar un Propietario para asociar a la Obligación");
		}

		v.noSonVacios(noVacios, nomNoVacios);

		return v;
	}
	private void deshabilitarElementosConsultarEliminar(){
		getBeanDocEspAutomotor().getBtnLimpiarPersona().setRendered(false);
		getBeanDocEspAutomotor().getBtnLimpiarVehiculo().setRendered(false);
		getBeanDocEspAutomotor().getBtnSeleccionarPersonaFisica().setRendered(false);
		getBeanDocEspAutomotor().getBtnSeleccionarPersonaJuridica().setRendered(false);
		getBeanDocEspAutomotor().getBtnSeleccionarVehiculo().setRendered(false);
		getBeanDocEspAutomotor().getPanelAtributoDinamico().deshabilitarCampos();
		getBeanDocEspAutomotor().getTfVehiculo().setDisabled(true);
		getBeanDocEspAutomotor().getBtnSeleccionarDomicilioPostal().setRendered(false);
		getBeanDocEspAutomotor().getBtnLimpiarDomicilioPostal().setRendered(false);
		getBeanDocEspAutomotor().getBtnSeleccionarDomicilioSolicitante().setRendered(false);
		getBeanDocEspAutomotor().getTaComentarioLogAuditoria().setRendered(false);
		getBeanDocEspAutomotor().getLblComentarioLogAuditoria().setRendered(false);
		getBeanDocEspAutomotor().getTableColumn1().setRendered(false);
		getBeanDocEspAutomotor().getTfNumeroCuenta().setDisabled(true);
	}

	public class AgregarDocEspAutomotorController extends AgregarAbstractController{

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion)pObject;
			Persona persona = getBeanDocEspAutomotor().obtenerObjetoDelElementoPila(2, Persona.class);
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().addObligacion(persona, locObligacion);
			return "El Documento Automotor se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return DocEspAutomotorModel.this;
		}

	}
	public class ModificarDocEspAutomotorController extends ModificarAbstractController{

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion) pObject;   
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().updateObligacion(locObligacion);
			return "El Documento Automotor se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {
			return DocEspAutomotorModel.this;
		}

	}
	public class ConsultarDocEspAutomotorController extends ConsultarAbstractController{

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
			return DocEspAutomotorModel.this;
		}
	}
	public class EliminarDocEspAutomotorController extends EliminarAbstractController{

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion) pObject;
			locObligacion.anular();
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().updateObligacion(locObligacion);
			return "El Documento Automotor se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		} 

		@Override
		public ABMModel getModel() {
			return DocEspAutomotorModel.this;
		}
	}
	@Override
	public String getReglaNavegacion() {
		return "ABMDocEspAutomotor";
	}

	@Override
	public String getNombreEntidad() {
		return "Obligación Automotor";
	}
}