/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMPersonaFisica;

import java.util.ArrayList;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ActivarAbstractController;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author juanma
 */
public class PersonaFisicaModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMPersonaFisica";
	}

	@Override
	public String getNombreEntidad() {
		return "Persona Fisica";
	}

	private ABMPersonaFisica getBeanPersonaFisica() {
		return (ABMPersonaFisica) getRequestBean("framework$ABMPersonaFisica$ABMPersonaFisica");
	}

	private Validador getValidadorAgregarModificar() {
		ABMPersonaFisica locBeanPersonaFisica = getBeanPersonaFisica();

		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[5];
		String[] nomNoVacios = new String[5];
		UIComponent[] cuil = new UIComponent[1];
		String[] nomCuil = new String[1];
		UIComponent[] fechas = new UIComponent[1];
		String[] nomFechas = new String[1];
		UIComponent[] enteros = new UIComponent[4];
		String[] nomEnteros = new String[4];

		int pos = 0;
		noVacios[pos] = locBeanPersonaFisica.getTfCuil();
		nomNoVacios[pos++] = "CUIL";
		noVacios[pos] = locBeanPersonaFisica.getDdTipoDocumento();
		nomNoVacios[pos++] = "Documento";
		noVacios[pos] = locBeanPersonaFisica.getTfNumeroDocumento();
		nomNoVacios[pos++] = "Número de Documento";
		noVacios[pos] = locBeanPersonaFisica.getTfApellido();
		nomNoVacios[pos++] = "Apellido";
		noVacios[pos] = locBeanPersonaFisica.getTfNombre();
		nomNoVacios[pos++] = "Nombre";

		pos = 0;
		cuil[pos] = locBeanPersonaFisica.getTfCuil();
		nomCuil[pos++] = "CUIL";

		pos = 0;
		fechas[pos] = locBeanPersonaFisica.getTfNacimiento();
		nomFechas[pos++] = "Fecha de Nacimiento";

		pos = 0;
		enteros[pos] = locBeanPersonaFisica.getTfNumeroDocumento();
		nomEnteros[pos++] = "Numero de Documento";
		enteros[pos] = locBeanPersonaFisica.getTfEdad();
		nomEnteros[pos++] = "Edad";
		enteros[pos] = locBeanPersonaFisica.getTfTelefono();
		nomEnteros[pos++] = "Tel\351fono";
		enteros[pos] = locBeanPersonaFisica.getTfCelular();
		nomEnteros[pos++] = "Celular";

		Domicilio domicilio = locBeanPersonaFisica.obtenerObjetoDelElementoPila(1, Domicilio.class);
		if (domicilio.getLocalidad() == null) {
			String msg = "El Domicilio es requerido.";
			v.getErrores().add(msg);
		}

		Domicilio domicilioPostal = locBeanPersonaFisica.obtenerObjetoDelElementoPila(2, Domicilio.class);
		if (domicilioPostal.getLocalidad() == null) {
			String msg = "El Domicilio Postal es requerido.";
			v.getErrores().add(msg);
		}

		v.noSonVacios(noVacios, nomNoVacios);
		v.formatoCuitValido(cuil, nomCuil);
		v.formatoFechaValido(fechas, nomFechas);
		v.esNumero(enteros, nomEnteros);

		PersonaFisica persona = locBeanPersonaFisica.obtenerObjetoDelElementoPila(0, PersonaFisica.class);

		if (!v.fechaNoMayorAFechaActual(persona.getFechaNacimiento(), "Fecha de Nacimiento")) {
			locBeanPersonaFisica.getTfNacimiento().setValid(false);
		}

		if (persona.getNumeroDocumento() != null && persona.getCuil() != null) {
			if (!persona.getCuil().contains(persona.getNumeroDocumento())) {
				String msg = "N\372mero de Cuil no se corresponde con el N\372mero de Documento.";
				v.getErrores().add(msg);
				locBeanPersonaFisica.getTfCuil().setValid(false);
			}
		}

		ArrayList atributosDinamicos = locBeanPersonaFisica.obtenerObjetoDelElementoPila(3, ArrayList.class);
		Validador v2 = locBeanPersonaFisica.getPanelAtributoDinamico().validarCampos(atributosDinamicos);
		if (v2.getErrores().size() > 0) {
			for (int i = 0; i < v2.getErrores().size(); i++) {
				v.getErrores().add(v2.getErrores().get(i).toString());
			}
		}

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMPersonaFisica abmPersonaF = getBeanPersonaFisica();
		abmPersonaF.getTfCuil().setDisabled(true);
		abmPersonaF.getTfNumeroDocumento().setDisabled(true);
		abmPersonaF.getDdTipoDocumento().setDisabled(true);
		abmPersonaF.getTfApellido().setDisabled(true);
		abmPersonaF.getTfNombre().setDisabled(true);
		abmPersonaF.getDdSexo().setDisabled(true);
		abmPersonaF.getDdEstadoCivil().setDisabled(true);
		abmPersonaF.getTfNacimiento().setDisabled(true);
		abmPersonaF.getCbJubilado().setDisabled(true);
		abmPersonaF.getTfTelefono().setDisabled(true);
		abmPersonaF.getTfCelular().setDisabled(true);
		abmPersonaF.getTfEmail().setDisabled(true);
		abmPersonaF.getBtnSeleccionarDomicilio().setDisabled(false);
		abmPersonaF.getBtnSeleccionarDomicilioPostal().setDisabled(false);
		abmPersonaF.getBtnCopiarDomicilioFiscal().setDisabled(false);

		abmPersonaF.getBtnSeleccionarDomicilio().setVisible(false);
		abmPersonaF.getBtnSeleccionarDomicilioPostal().setVisible(false);
		abmPersonaF.getBtnCopiarDomicilioFiscal().setVisible(false);
		abmPersonaF.getPanelAtributoDinamico().deshabilitarCampos();
		abmPersonaF.getBtnGenerarCuit().setRendered(false);

	}

	public class AgregarPersonaFisicaController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			PersonaFisica locPersonaF = (PersonaFisica) pObject;
			getComunicationBean().getRemoteSystemPersonaFisica().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemPersonaFisica().addPersonaFisica(locPersonaF);

			return "La persona f\355sica se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return PersonaFisicaModel.this;
		}
	}

	public class ModificarPersonaFisicaController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			PersonaFisica locPersonaF = (PersonaFisica) pObject;
			getComunicationBean().getRemoteSystemPersonaFisica().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemPersonaFisica().updatePersonaFisica(locPersonaF);

			return "La persona f\355sica se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return PersonaFisicaModel.this;
		}
	}

	public class ConsultarPersonaFisicaController extends ConsultarAbstractController {

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
			return PersonaFisicaModel.this;
		}
	}

	public class EliminarPersonaFisicaController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			PersonaFisica locPersonaF = (PersonaFisica) pObject;
			getComunicationBean().getRemoteSystemPersonaFisica().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemPersonaFisica().removePersonaFisica(locPersonaF);
			return "La persona f\355sica se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return PersonaFisicaModel.this;
		}
	}

	public class ActivarPersonaFisicaController extends ActivarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			PersonaFisica locPersonaF = (PersonaFisica) pObject;
			getComunicationBean().getRemoteSystemPersonaFisica().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemPersonaFisica().restorePersonaFisica(locPersonaF);

			return "La persona f\355sica se Recupero exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return PersonaFisicaModel.this;
		}
	}
}
