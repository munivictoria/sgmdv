/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMPais;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.Pais;
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
public class PaisModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMPais";
	}

	@Override
	public String getNombreEntidad() {
		return "Pais";
	}

	private ABMPais getBeanPais() {
		return (ABMPais) getRequestBean("framework$ABMPais$ABMPais");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];
		int pos = 0;
		noVacios[pos] = getBeanPais().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMPais abmPais = getBeanPais();
		abmPais.getTfNombre().setDisabled(true);
		abmPais.getTfAbreviatura().setDisabled(true);
		abmPais.getTaComentarioLogAuditoria().setRendered(false);
		abmPais.getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarPaisController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Pais locPais = (Pais) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			locPais = getComunicationBean().getRemoteSystemMunicipalidad().addPais(locPais);

			getApplicationBean1().agregarNuevoPais(locPais);
			return "El pais se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return PaisModel.this;
		}
	}

	public class ModificarPaisController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Pais locPais = (Pais) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemMunicipalidad().updatePais(locPais);

			getApplicationBean1().modificarPais(locPais);
			return "El pais se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return PaisModel.this;
		}
	}

	public class ConsultarPaisController extends ConsultarAbstractController {

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
			return PaisModel.this;
		}
	}

	public class EliminarPaisController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Pais locPais = (Pais) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemMunicipalidad().removePais(locPais);

			getApplicationBean1().eliminarPais(locPais);
			return "El pais se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return PaisModel.this;
		}
	}
}
