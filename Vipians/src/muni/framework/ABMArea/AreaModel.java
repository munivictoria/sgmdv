/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMArea;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.Area;
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
public class AreaModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMArea";
	}

	@Override
	public String getNombreEntidad() {
		return "Area";
	}

	private ABMArea getBeanArea() {
		return (ABMArea) getRequestBean("framework$ABMArea$ABMArea");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[3];
		String[] nomNoVacios = new String[3];
		int pos = 0;
		noVacios[pos] = getBeanArea().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanArea().getTaDescripcion();
		nomNoVacios[pos++] = "Descripcion";
		noVacios[pos] = getBeanArea().getDdSecretaria();
		nomNoVacios[pos++] = "Secretaria";

		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMArea abmArea = getBeanArea();
		abmArea.getTfNombre().setDisabled(true);
		abmArea.getTaDescripcion().setDisabled(true);
		abmArea.getDdSecretaria().setDisabled(true);
		abmArea.getTaComentarioLogAuditoria().setRendered(false);
		abmArea.getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarAreaController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Area locArea = (Area) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemMunicipalidad().addArea(locArea);

			return "El área se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return AreaModel.this;
		}
	}

	public class ModificarBienController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Area locArea = (Area) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemMunicipalidad().updateArea(locArea);
			return "El área se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return AreaModel.this;
		}
	}

	public class ConsultarRolController extends ConsultarAbstractController {

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
			return AreaModel.this;
		}
	}

	public class EliminarRolController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Area locArea = (Area) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemMunicipalidad().removeArea(locArea);

			return "El área se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return AreaModel.this;
		}
	}
}