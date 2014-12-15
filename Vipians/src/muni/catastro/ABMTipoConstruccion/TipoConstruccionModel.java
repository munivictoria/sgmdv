/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.catastro.ABMTipoConstruccion;

import javax.faces.component.UIComponent;

import com.trascender.catastro.recurso.persistent.TipoConstruccion;
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
public class TipoConstruccionModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMTipoConstruccion";
	}

	@Override
	public String getNombreEntidad() {
		return "Tipo Construccion";
	}

	private ABMTipoConstruccion getBeanTipoConstruccion() {
		return (ABMTipoConstruccion) getRequestBean("catastro$ABMTipoConstruccion$ABMTipoConstruccion");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		int pos = 0;
		noVacios[pos] = getBeanTipoConstruccion().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanTipoConstruccion().getTxDescripcion();
		nomNoVacios[pos++] = "Descripcion";

		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMTipoConstruccion abmTipoConstruccion = getBeanTipoConstruccion();

		abmTipoConstruccion.getTfNombre().setDisabled(true);
		abmTipoConstruccion.getTxDescripcion().setDisabled(true);
	}

	public class AgregarTipoConstruccionController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoConstruccion locTipoConstruccion = (TipoConstruccion) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().addTipoConstruccion(locTipoConstruccion);

			return "El Tipo Construcci\363n se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return TipoConstruccionModel.this;
		}
	}

	public class ModificarTipoConstruccionController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoConstruccion locTipoConstruccion = (TipoConstruccion) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().updateTipoConstruccion(locTipoConstruccion);

			return "El Tipo Construcci\363n  se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return TipoConstruccionModel.this;
		}
	}

	public class ConsultarTipoConstruccionController extends ConsultarAbstractController {

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
			return TipoConstruccionModel.this;
		}
	}

	public class EliminarTipoConstruccionController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoConstruccion locTipoConstruccion = (TipoConstruccion) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().deleteTipoConstruccion(locTipoConstruccion);

			return "El Tipo Construcci\363n  se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return TipoConstruccionModel.this;
		}
	}
}