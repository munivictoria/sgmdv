/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMLocalidad;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.Localidad;
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
public class LocalidadModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMLocalidad";
	}

	@Override
	public String getNombreEntidad() {
		return "Localidad";
	}

	private ABMLocalidad getBeanLocalidad() {
		return (ABMLocalidad) getRequestBean("framework$ABMLocalidad$ABMLocalidad");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[3];
		String[] nomNoVacios = new String[3];
		int pos = 0;
		noVacios[pos] = getBeanLocalidad().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanLocalidad().getTfCodigoPostal();
		nomNoVacios[pos++] = "CodigoPostal";
		noVacios[pos] = getBeanLocalidad().getDdProvincia();
		nomNoVacios[pos++] = "Provincia";
		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMLocalidad abmLocalidad = this.getBeanLocalidad();
		abmLocalidad.getTfNombre().setDisabled(true);
		abmLocalidad.getTfCodigoPostal().setDisabled(true);
		abmLocalidad.getDdProvincia().setDisabled(true);
		abmLocalidad.getBtnSeleccionarProvincia().setRendered(false);
	}

	public class AgregarLocalidadController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Localidad locLocalidad = (Localidad) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemMunicipalidad().addLocalidad(locLocalidad);

			return "La loclidad se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return LocalidadModel.this;
		}
	}

	public class ModificarLocalidadController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Localidad locLocalidad = (Localidad) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemMunicipalidad().updateLocalidad(locLocalidad);

			return "La localidad se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return LocalidadModel.this;
		}
	}

	public class ConsultarLocalidadController extends ConsultarAbstractController {

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
			return LocalidadModel.this;
		}
	}

	public class EliminarLocalidadController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Localidad locLocalidad = (Localidad) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemMunicipalidad().removeLocalidad(locLocalidad);

			return "La localidad se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return LocalidadModel.this;
		}
	}
}