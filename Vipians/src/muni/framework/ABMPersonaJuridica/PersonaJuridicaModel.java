/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMPersonaJuridica;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.recurso.persistent.Socio;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ActivarAbstractController;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author fer
 */
public class PersonaJuridicaModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMPersonaJuridica";
	}

	@Override
	public String getNombreEntidad() {
		return "Persona Juridica";
	}
	
	protected ABMPersonaJuridica getAbmPersonaJuridica() {
		return (ABMPersonaJuridica) getRequestBean("framework$ABMPersonaJuridica$ABMPersonaJuridica");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		UIComponent[] cuil = new UIComponent[1];
		String[] nomCuil = new String[1];
		UIComponent[] esEntero = new UIComponent[2];
		String[] nomEsEntero = new String[2];

		int pos = 0;
		noVacios[pos] = getAbmPersonaJuridica().getTfCuit();
		nomNoVacios[pos++] = "CUIT";
		noVacios[pos] = getAbmPersonaJuridica().getTfRazonSocial();
		nomNoVacios[pos++] = "Raz\363n Social";

		pos = 0;
		cuil[pos] = getAbmPersonaJuridica().getTfCuit();
		nomCuil[pos++] = "CUIT";

		pos = 0;
		esEntero[pos] = getAbmPersonaJuridica().getTfTelefono();
		nomEsEntero[pos++] = "Tel\351fono";
		esEntero[pos] = getAbmPersonaJuridica().getTfCelular();
		nomEsEntero[pos++] = "Celular";

		v.noSonVacios(noVacios, nomNoVacios);
		v.formatoCuitValido(cuil, nomCuil);
		v.esNumero(esEntero, nomEsEntero);

		Domicilio domicilio = (Domicilio) getAbmPersonaJuridica().obtenerObjetoDelElementoPila(1, Domicilio.class);
		if (domicilio.getLocalidad() == null) {
			String msg = "El Domicilio Fiscal es requerido.";
			v.getErrores().add(msg);
		}

		Domicilio domicilioPostal = (Domicilio) getAbmPersonaJuridica().obtenerObjetoDelElementoPila(2, Domicilio.class);
		if (domicilioPostal.getLocalidad() == null) {
			String msg = "El Domicilio Postal es requerido.";
			v.getErrores().add(msg);
		}

		getAbmPersonaJuridica().getLdpSocios().commitChanges();
		List locLista = getAbmPersonaJuridica().getLdpSocios().getList();
		if (locLista == null || locLista.isEmpty()) {
			String msg = "Al menos un socio es requerido";
			v.getErrores().add(msg);
		}

		ArrayList atributosDinamicos = (ArrayList) getAbmPersonaJuridica().obtenerObjetoDelElementoPila(4, ArrayList.class);
		Validador v2 = getAbmPersonaJuridica().getPanelAtributoDinamico().validarCampos(atributosDinamicos);
		if (v2.getErrores().size() > 0) {
			for (int i = 0; i < v2.getErrores().size(); i++) {
				v.getErrores().add(v2.getErrores().toArray()[i].toString());
			}
		}
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		getAbmPersonaJuridica().getTfCuit().setDisabled(true);
		getAbmPersonaJuridica().getTfRazonSocial().setDisabled(true);
		getAbmPersonaJuridica().getTfTelefono().setDisabled(true);
		getAbmPersonaJuridica().getTfCelular().setDisabled(true);
		getAbmPersonaJuridica().getTfEmail().setDisabled(true);
		getAbmPersonaJuridica().getTfNacionalidad().setDisabled(true);
		getAbmPersonaJuridica().getBtnSeleccionarDomicilio().setRendered(false);
		getAbmPersonaJuridica().getBtnSeleccionarDomicilioPostal().setRendered(false);
		getAbmPersonaJuridica().getTableColumn1().setRendered(false);
		getAbmPersonaJuridica().getGroupPanel1().setRendered(false);
		getAbmPersonaJuridica().getBtnCopiarDomicilioFiscal().setRendered(false);
		getAbmPersonaJuridica().getListaCargos().setDisabled(true);
		getAbmPersonaJuridica().getDdCargo().setDisabled(true);
		getAbmPersonaJuridica().getTfNombreFantasia().setDisabled(true);
		getAbmPersonaJuridica().getTfNroSociedad().setDisabled(true);
		getAbmPersonaJuridica().getDdOrganismoEmisor().setDisabled(true);
		getAbmPersonaJuridica().getDdTipoSocietario().setDisabled(true);
		getAbmPersonaJuridica().getTfNroIngresosBrutos().setDisabled(true);
		getAbmPersonaJuridica().getTfNroConvenio().setDisabled(true);
		getAbmPersonaJuridica().getPanelAtributoDinamico().deshabilitarCampos();
		getAbmPersonaJuridica().getBtnCopiarDomicilioPostal().setRendered(false);
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			PersonaJuridica locPersona = (PersonaJuridica) pObject;
			for (Socio cadaSocio : locPersona.getListaSocios()) {
				System.out.println("Cargo: " + cadaSocio.getCargo());
			}
			getComunicationBean().getRemoteSystemPersonaJuridica().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemPersonaJuridica().addPersonaJuridica(locPersona);
			return "Persona Jur\355dica agregada con exito";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {
			return PersonaJuridicaModel.this;
		}
	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			PersonaJuridica locPersona = (PersonaJuridica) pObject;
			getComunicationBean().getRemoteSystemPersonaJuridica().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemPersonaJuridica().updatePersonaJuridica(locPersona);
			return "Persona Jur\355dica modificada con exito";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}
		
		@Override
		public ABMModel getModel() {
			return PersonaJuridicaModel.this;
		}
	}

	public class ConsultarController extends ConsultarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return PersonaJuridicaModel.this;
		}
	}

	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			PersonaJuridica locPersona = (PersonaJuridica) pObject;
			getComunicationBean().getRemoteSystemPersonaJuridica().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemPersonaJuridica().removePersonaJuridica(locPersona);
			return "Persona Jur\355dica eliminada con exito";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}
		
		@Override
		public ABMModel getModel() {
			return PersonaJuridicaModel.this;
		}
	}

	public class ActivarController extends ActivarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			PersonaJuridica locPersonaF = (PersonaJuridica) pObject;
			getComunicationBean().getRemoteSystemPersonaJuridica().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemPersonaJuridica().restorePersonaJuidica(locPersonaF);

			return "La persona Jur\355dica se Recupero exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return PersonaJuridicaModel.this;
		}
	}
}
