package muni.framework.ABMSecretaria;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author nico
 */
public class SecretariaModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMSecretaria";
	}

	@Override
	public String getNombreEntidad() {
		return "Secretaria";
	}

	private ABMSecretaria getBeanSecretaria() {
		return (ABMSecretaria) getRequestBean("framework$ABMSecretaria$ABMSecretaria");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];
		int pos = 0;
		noVacios[pos] = getBeanSecretaria().getTfNombre();
		nomNoVacios[pos++] = "Nombre";

		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMSecretaria abmSecretaria = getBeanSecretaria();

		abmSecretaria.getTfNombre().setDisabled(true);
		abmSecretaria.getTfCodigoImputacion().setDisabled(true);
		abmSecretaria.getTaComentarioLogAuditoria().setRendered(false);
		abmSecretaria.getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarSecretariaController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Secretaria locSecretaria = (Secretaria) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			locSecretaria = getComunicationBean().getRemoteSystemMunicipalidad().addSecretaria(locSecretaria);

			getApplicationBean1().agregarNuevaSecretaria(locSecretaria);
			return "La secretaría se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ABMSecretaria abmSecretaria = getBeanSecretaria();
			abmSecretaria.getGroupPanel().setRendered(false);
			/*
			 * abmSecretaria.getLbArea().setRendered(false);
			 * abmSecretaria.getTablaArea().setRendered(false);
			 */
		}

		@Override
		public ABMModel getModel() {
			return SecretariaModel.this;
		}
	}

	public class ModificarSecretariaController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Secretaria locSecretaria = (Secretaria) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemMunicipalidad().updateSecretaria(locSecretaria);

			getApplicationBean1().modificarSecretaria(locSecretaria);
			return "La secretaría se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return SecretariaModel.this;
		}
	}

	public class ConsultarSecretariaController extends ConsultarAbstractController {

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
			return SecretariaModel.this;
		}
	}

	public class EliminarSecretariaController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Secretaria locSecretaria = (Secretaria) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemMunicipalidad().deleteSecretaria(locSecretaria);

			getApplicationBean1().eliminarSecretaria(locSecretaria);
			return "La secretaría se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return SecretariaModel.this;
		}
	}
}
