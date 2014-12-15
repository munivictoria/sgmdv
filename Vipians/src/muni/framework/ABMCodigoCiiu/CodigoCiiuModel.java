/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMCodigoCiiu;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author ubuntero
 */
public class CodigoCiiuModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMCodigoCiiu";
	}

	@Override
	public String getNombreEntidad() {
		return "C\363digo CIIU";
	}

	private ABMCodigoCiiu getBeanCodigoCiiu() {
		return (ABMCodigoCiiu) getRequestBean("framework$ABMCodigoCiiu$ABMCodigoCiiu");
	}

	private void ocultarConsultarFinalizarEnVista() {

		getBeanCodigoCiiu().getTfCodigo().setDisabled(true);
		getBeanCodigoCiiu().getTfDescripcion().setDisabled(true);
	}

	public class ConsultarController extends ConsultarAbstractController {

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
			ocultarConsultarFinalizarEnVista();
		}

		@Override
		public ABMModel getModel() {
			return CodigoCiiuModel.this;
		}
	}
}