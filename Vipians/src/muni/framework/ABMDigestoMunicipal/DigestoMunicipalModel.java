/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMDigestoMunicipal;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author juanma
 */
public class DigestoMunicipalModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMDigestoMunicipal";
	}

	@Override
	public String getNombreEntidad() {
		return "Digesto Municipal";
	}

	private ABMDigestoMunicipal getBeanDigestoMunicipal() {
		return (ABMDigestoMunicipal) getRequestBean("framework$ABMDigestoMunicipal$ABMDigestoMunicipal");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[5];
		String[] nomNoVacios = new String[5];
		int pos = 0;
		noVacios[pos] = getBeanDigestoMunicipal().getTfNumero();
		nomNoVacios[pos++] = "Numero";
		noVacios[pos] = getBeanDigestoMunicipal().getTfFecha();
		nomNoVacios[pos++] = "Fecha";
		noVacios[pos] = getBeanDigestoMunicipal().getDdTipoDigesto();
		nomNoVacios[pos++] = "Tipo";
		noVacios[pos] = getBeanDigestoMunicipal().getDdEstadoDigesto();
		nomNoVacios[pos++] = "Estado";
		noVacios[pos] = getBeanDigestoMunicipal().getTaDescripcion();
		nomNoVacios[pos++] = "Descripción";

		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMDigestoMunicipal abmDigestoMunicpal = getBeanDigestoMunicipal();
		abmDigestoMunicpal.getTfNumero().setDisabled(true);
		abmDigestoMunicpal.getTfFecha().setDisabled(true);
		abmDigestoMunicpal.getTfNombre().setDisabled(true);
		abmDigestoMunicpal.getDdTipoDigesto().setDisabled(true);
		abmDigestoMunicpal.getDdEstadoDigesto().setDisabled(true);
		abmDigestoMunicpal.getBtnAgregarDigesto().setDisabled(true);
		abmDigestoMunicpal.getBtnAgregarDigesto().setVisible(false);
		abmDigestoMunicpal.getBtnEliminarDigesto().setDisabled(true);
		abmDigestoMunicpal.getBtnEliminarDigesto().setVisible(false);
		abmDigestoMunicpal.getDdAmbitoDigesto().setDisabled(true);
		abmDigestoMunicpal.getDdEjeTematicoDigesto().setDisabled(true);
		abmDigestoMunicpal.getTaDescripcion().setDisabled(true);
		abmDigestoMunicpal.getTableColumn1().setRendered(false);
		abmDigestoMunicpal.getTaComentarioLogAuditoria().setDisabled(true);
	}

	public class AgregarDigestoMunicpalController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DigestoMunicipal locDigestoMunicipal = (DigestoMunicipal) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemMunicipalidad().addDigestoMunicipal(locDigestoMunicipal);

			return "El digesto municipal se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return DigestoMunicipalModel.this;
		}
	}

	public class ModificarDigestoMunicipalController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DigestoMunicipal locDigestoMunicipal = (DigestoMunicipal) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemMunicipalidad().updateDigestoMunicipal(locDigestoMunicipal);
			return "El digesto municipal se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return DigestoMunicipalModel.this;
		}
	}

	public class ConsultarDigestoMunicipalController extends ConsultarAbstractController {

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
			return DigestoMunicipalModel.this;
		}
	}

	public class EliminarDigestoMunicipalController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DigestoMunicipal locDigestoMunicipal = (DigestoMunicipal) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemMunicipalidad().deleteDigestoMuncipal(locDigestoMunicipal);

			return "El digesto municipal se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return DigestoMunicipalModel.this;
		}
	}
}