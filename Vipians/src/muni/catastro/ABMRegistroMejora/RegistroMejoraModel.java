/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.catastro.ABMRegistroMejora;

import javax.faces.component.UIComponent;

import com.trascender.catastro.recurso.persistent.RegistroMejora;
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
public class RegistroMejoraModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMRegistroMejora";
	}

	@Override
	public String getNombreEntidad() {
		return "Registro Mejora";
	}

	private ABMRegistroMejora getBeanRegistroMejora() {
		return (ABMRegistroMejora) getRequestBean("catastro$ABMRegistroMejora$ABMRegistroMejora");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[3];
		String[] nomNoVacios = new String[3];
		int pos = 0;
		noVacios[pos] = getBeanRegistroMejora().getTfAnioConstruccion();
		nomNoVacios[pos++] = "Año Construccion";
		noVacios[pos] = getBeanRegistroMejora().getTfCategoria();
		nomNoVacios[pos++] = "Categoria";
		noVacios[pos] = getBeanRegistroMejora().getTfSuperficie();
		nomNoVacios[pos++] = "Superficie";

		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMRegistroMejora abmRegistroMejora = getBeanRegistroMejora();

		abmRegistroMejora.getTfAnioConstruccion().setDisabled(true);
		abmRegistroMejora.getTfCategoria().setDisabled(true);
		abmRegistroMejora.getTfDeclaracionJurada().setDisabled(true);
		abmRegistroMejora.getTfSuperficie().setDisabled(true);
		abmRegistroMejora.getBtnLimpiarCategoria().setRendered(false);
		abmRegistroMejora.getBtnLimpiarDDJJ().setRendered(false);
		abmRegistroMejora.getBtnSeleccionarCategoria().setRendered(false);
		abmRegistroMejora.getBtnSeleccionarDeclaracionJurada().setRendered(false);
		abmRegistroMejora.getDdEstadoMejora().setDisabled(true);
		abmRegistroMejora.getTfNumeroDJ().setDisabled(true);
		abmRegistroMejora.getTfFechaInscripcionDJ().setDisabled(true);
		abmRegistroMejora.getPanelAtributoDinamico().deshabilitarCampos();
		
	}

	public class AgregarRegistroMejoraController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			RegistroMejora locRegistro = (RegistroMejora) pObject;

			getBeanRegistroMejora().getRequestBean1().setObjetoSeleccion(locRegistro);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return RegistroMejoraModel.this;
		}
	}

	public class ModificarRegistroMejoraController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			// RegistroMejora locRegistro = (RegistroMejora) pObject;
			// getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(getSessionBean1().getLlave());
			// getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().up
			// ();
			getBeanRegistroMejora().getRequestBean1().setObjetoSeleccion(null);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return RegistroMejoraModel.this;
		}
	}

	public class ConsultarRegistroMejoraController extends ConsultarAbstractController {

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
			return RegistroMejoraModel.this;
		}
	}

	public class EliminarRegistroMejoraController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			RegistroMejora locRegistro = (RegistroMejora) pObject;

			return "La manzana se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return RegistroMejoraModel.this;
		}
	}
}