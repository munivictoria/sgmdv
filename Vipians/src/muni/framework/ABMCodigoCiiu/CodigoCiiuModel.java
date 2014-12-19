/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMCodigoCiiu;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class CodigoCiiuModel extends ABMModel {
	
	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[4];
		String[] nomNoVacios = new String[4];
		int pos = 0;
		noVacios[pos] = getBeanCodigoCiiu().getTfCodigo();
		nomNoVacios[pos++] = "Código";
		noVacios[pos] = getBeanCodigoCiiu().getTfDescripcion();
		nomNoVacios[pos++] = "Descripcion";
		noVacios[pos] = getBeanCodigoCiiu().getDdSeccion();
		nomNoVacios[pos++] = "Sección";
		noVacios[pos] = getBeanCodigoCiiu().getDdGrupo();
		nomNoVacios[pos++] = "Grupo";

		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

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
		getBeanCodigoCiiu().getDdGrupo().setDisabled(true);
		getBeanCodigoCiiu().getDdSeccion().setDisabled(true);
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
	
	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			CodigoCiiu locCodigo = (CodigoCiiu) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().addCodigoCiiu(locCodigo);
			return "El Código CIIU fue agregado con exito";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return CodigoCiiuModel.this;
		}
		
	}
	
	public class ModificarController extends ModificarAbstractController{

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			CodigoCiiu locCodigo = (CodigoCiiu) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().updateCodigoCiiu(locCodigo);
			return "El Código CIIU fue modificado con exito";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return CodigoCiiuModel.this;
		}
		
	}
	
	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			CodigoCiiu locCodigo = (CodigoCiiu) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().deleteCodigoCiiu(locCodigo);
			return "El Código CIIU fue eliminado con exito";
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