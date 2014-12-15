/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.catastro.ABMDeclaracionJurada;

import javax.faces.component.UIComponent;

import com.trascender.catastro.recurso.persistent.DeclaracionJurada;
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
public class DeclaracionJuradaModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMDeclaracionJurada";
	}

	@Override
	public String getNombreEntidad() {
		return "Declaracion Jurada";
	}

	private ABMDeclaracionJurada getBeanDecalracionJurada() {
		return (ABMDeclaracionJurada) getRequestBean("catastro$ABMDeclaracionJurada$ABMDeclaracionJurada");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		int pos = 0;
		noVacios[pos] = getBeanDecalracionJurada().getTfCodigo();
		nomNoVacios[pos++] = "Codigo";
		noVacios[pos] = getBeanDecalracionJurada().getTfFechaInscripcion();
		nomNoVacios[pos++] = "FechaInscripcion";

		v.noSonVacios(noVacios, nomNoVacios);

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMDeclaracionJurada abmDJurada = getBeanDecalracionJurada();
		abmDJurada.getTfCodigo().setDisabled(true);
		abmDJurada.getTfFechaInscripcion().setDisabled(true);
	}

	public class AgregarDeclaracionJuradaController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DeclaracionJurada locDJurada = (DeclaracionJurada) pObject;
			/*
			 * getComunicationCatastroBean().getRemoteSystemAdministracionDDJJ().
			 * setLlave(getSessionBean1().getLlave());
			 * getComunicationCatastroBean
			 * ().getRemoteSystemAdministracionDDJJ().
			 * addDeclaracionJurada(locDJurada);
			 */
			// getBeanDecalracionJurada().getRequestBean1().setObjetoABM(locDJurada);
			getBeanDecalracionJurada().getRequestBean1().setObjetoSeleccion(locDJurada);

			return "La declaracion jurada se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return DeclaracionJuradaModel.this;
		}
	}

	public class ModificarDeclaracionJuradaController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DeclaracionJurada locDJurada = (DeclaracionJurada) pObject;
			getComunicationCatastroBean().getRemoteSystemAdministracionDDJJ().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemAdministracionDDJJ().updateDeclaracionJurada(locDJurada);

			// getBeanDecalracionJurada().getRequestBean1().setObjetoABM(locDJurada);

			return "La declaracion jurada se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return DeclaracionJuradaModel.this;
		}
	}

	public class ConsultarDeclaracionJuradaController extends ConsultarAbstractController {

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
			return DeclaracionJuradaModel.this;
		}
	}

	public class EliminarDeclaracionJuradaController extends EliminarAbstractController {

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
			return DeclaracionJuradaModel.this;
		}
	}
}