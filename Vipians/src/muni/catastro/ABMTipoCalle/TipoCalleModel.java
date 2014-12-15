/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.catastro.ABMTipoCalle;

import javax.faces.component.UIComponent;

import com.trascender.catastro.recurso.persistent.TipoCalle;
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
public class TipoCalleModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMTipoCalle";
	}

	@Override
	public String getNombreEntidad() {
		return "Tipo Calle";
	}

	private ABMTipoCalle getBeanTipoCalle() {
		return (ABMTipoCalle) getRequestBean("catastro$ABMTipoCalle$ABMTipoCalle");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		int pos = 0;
		noVacios[pos] = getBeanTipoCalle().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanTipoCalle().getTaDescripcion();
		nomNoVacios[pos++] = "Descripcion";
		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMTipoCalle abmTipoCalle = getBeanTipoCalle();
		abmTipoCalle.getTfNombre().setDisabled(true);
		abmTipoCalle.getTaDescripcion().setDisabled(true);
	}

	public class AgregarTipoCalleController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoCalle locTipoCalle = (TipoCalle) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().addTipoCalle(locTipoCalle);

			return "El Tipo Calle se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return TipoCalleModel.this;
		}
	}

	public class ModificarTipoCalleController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoCalle locTipoCalle = (TipoCalle) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().updateTipoCalle(locTipoCalle);

			return "El Tipo Calle se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return TipoCalleModel.this;
		}
	}

	public class ConsultarTipoCalleController extends ConsultarAbstractController {

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
			return TipoCalleModel.this;
		}
	}

	public class EliminarTipoCalleController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoCalle locTipoCalle = (TipoCalle) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().deleteTipoCalle(locTipoCalle);

			return "El Tipo Calle se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return TipoCalleModel.this;
		}
	}
}