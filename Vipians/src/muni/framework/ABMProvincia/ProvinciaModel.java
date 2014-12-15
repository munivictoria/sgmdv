/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMProvincia;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.Provincia;
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
public class ProvinciaModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMProvincia";
	}

	@Override
	public String getNombreEntidad() {
		return "Provincia";
	}

	private ABMProvincia getBeanProvincia() {
		return (ABMProvincia) getRequestBean("framework$ABMProvincia$ABMProvincia");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		int pos = 0;
		noVacios[pos] = getBeanProvincia().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanProvincia().getDdPais();
		nomNoVacios[pos++] = "Pais";
		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMProvincia abmProvincia = getBeanProvincia();
		abmProvincia.getTfNombre().setDisabled(true);
		abmProvincia.getTfAbreviatura().setDisabled(true);
		abmProvincia.getTfCodigo().setDisabled(true);
		abmProvincia.getDdPais().setDisabled(true);
		abmProvincia.getBtnSeleccionarPais().setRendered(false);
		abmProvincia.getTaComentarioLogAuditoria().setRendered(false);
		abmProvincia.getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarProvinciaController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Provincia locProvincia = (Provincia) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			locProvincia = getComunicationBean().getRemoteSystemMunicipalidad().addProvincia(locProvincia);

			getApplicationBean1().agregarNuevaProvincia(locProvincia);
			return "La provincia se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ProvinciaModel.this;
		}
	}

	public class ModificarProvinciaController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Provincia locProvincia = (Provincia) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemMunicipalidad().updateProvincia(locProvincia);

			getApplicationBean1().modificarProvincia(locProvincia);
			return "La provincia se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ProvinciaModel.this;
		}
	}

	public class ConsultarPronvinciaController extends ConsultarAbstractController {

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
			return ProvinciaModel.this;
		}
	}

	public class EliminarPronvinciaController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Provincia locProvincia = (Provincia) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemMunicipalidad().removeProvincia(locProvincia);

			getApplicationBean1().eliminarProvincia(locProvincia);
			return "La provincia se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return ProvinciaModel.this;
		}
	}
}
