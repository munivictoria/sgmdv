/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.framework.ABMConfiguracionRecurso;
import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;


/**
 * 
 * @author jsantacruz
 */
public class ConfiguracionRecursoModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMConfiguracionRecurso";
	}

	@Override
	public String getNombreEntidad() {
		return "Configuracion de Recurso";
	}

	private ABMConfiguracionRecurso getBeanConfiguracionRecurso() {
		return (ABMConfiguracionRecurso) getRequestBean("framework$ABMConfiguracionRecurso$ABMConfiguracionRecurso");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];
		int pos = 0;
		noVacios[pos] = getBeanConfiguracionRecurso().getTfRecurso();
		nomNoVacios[pos++] = "Recurso";

		v.noSonVacios(noVacios, nomNoVacios);
		
		ConfiguracionRecurso confRec = (ConfiguracionRecurso) this.getBeanConfiguracionRecurso().getElementoPila().getObjetos().get(0);
		if(confRec.getToString() != null && confRec.getToString().length() > 0) {
			String texto = confRec.getToString();
			int cantSignoPesoFormula = texto.length() - texto.replace("$", "").length();
			int cantSignoPeso = (texto.length() - texto.replace("'$'", "").length()) / 3;
			
			if((cantSignoPesoFormula - cantSignoPeso) % 2 != 0) {
				v.getErrores().add("Debe de haber una cantidad par de signos $.");
			}
		}
		
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMConfiguracionRecurso abmConfiguracionRecurso = getBeanConfiguracionRecurso();
		abmConfiguracionRecurso.getBtnSeleccionarRecurso().setRendered(false);
		abmConfiguracionRecurso.getBtnLimpiarRecurso().setRendered(false);
		abmConfiguracionRecurso.getTfNombreAlias().setDisabled(true);
		abmConfiguracionRecurso.getTfOrden().setDisabled(true);
		abmConfiguracionRecurso.getGroupPanel1().setRendered(false);
		abmConfiguracionRecurso.getTableColumn1().setRendered(false);
	}

	public class AgregarConfiguracionRecursoController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ConfiguracionRecurso locConfiguracionRecurso = (ConfiguracionRecurso) pObject;
			getComunicationBean().getRemoteSystemParametro().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemParametro().addConfiguracionRecurso(locConfiguracionRecurso);

			return "La Configuración se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ConfiguracionRecursoModel.this;
		}
	}

	public class ModificarConfiguracionRecursoController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ConfiguracionRecurso locConfiguracionRecurso = (ConfiguracionRecurso) pObject;
			getComunicationBean().getRemoteSystemParametro().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemParametro().updateConfiguracionRecurso(locConfiguracionRecurso);
			return "La Configuraci\363n del recurso se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ABMConfiguracionRecurso abmConfiguracionRecurso = getBeanConfiguracionRecurso();
			abmConfiguracionRecurso.getBtnSeleccionarRecurso().setRendered(false);
			abmConfiguracionRecurso.getBtnLimpiarRecurso().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return ConfiguracionRecursoModel.this;
		}
	}

	public class ConsultarConfiguracionRecursoController extends ConsultarAbstractController {

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
			return ConfiguracionRecursoModel.this;
		}
	}

	public class EliminarConfiguracionRecursoController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ConfiguracionRecurso locConfiguracionRecurso = (ConfiguracionRecurso) pObject;
			getComunicationBean().getRemoteSystemParametro().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemParametro().deleteConfiguracionRecurso(locConfiguracionRecurso);

			return "La Configuraci\363n del recurso se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return ConfiguracionRecursoModel.this;
		}
	}
}