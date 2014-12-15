/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.catastro.ABMCalle;

import javax.faces.component.UIComponent;

import com.trascender.catastro.recurso.persistent.Calle;
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
public class CalleModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMCalle";
	}

	@Override
	public String getNombreEntidad() {
		return "Calle";
	}

	private ABMCalle getBeanCalle() {
		return (ABMCalle) getRequestBean("catastro$ABMCalle$ABMCalle");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		int pos = 0;
		noVacios[pos] = getBeanCalle().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanCalle().getTfTipoCalle();
		nomNoVacios[pos++] = "Tipo de Calle";

		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMCalle abmCalle = getBeanCalle();
		abmCalle.getTfCodigo().setDisabled(true);
		abmCalle.getTfNombre().setDisabled(true);
		abmCalle.getTfTipoCalle().setDisabled(true);
		abmCalle.getBtnSeleccionarTipoCalle().setRendered(false);

		abmCalle.getBtnAgregarZona().setRendered(false);
		abmCalle.getBtnQuitarZona().setRendered(false);
		abmCalle.getGroupPanel1().setRendered(false);
		abmCalle.getTableColumn1().setRendered(false);
	}

	public class AgregarCalleController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Calle locCalle = (Calle) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().addCalle(locCalle);

			return "La calle se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return CalleModel.this;
		}
	}

	public class ModificarCalleController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Calle locCalle = (Calle) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().updateCalle(locCalle);

			return "La calle se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}
		
		@Override
		public ABMModel getModel() {
			return CalleModel.this;
		}
	}

	public class ConsultarCalleController extends ConsultarAbstractController {

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
			return CalleModel.this;
		}
	}

	public class EliminarCalleController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Calle locCalle = (Calle) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().deleteCalle(locCalle);

			return "La calle se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}
		
		@Override
		public ABMModel getModel() {
			return CalleModel.this;
		}
	}
}