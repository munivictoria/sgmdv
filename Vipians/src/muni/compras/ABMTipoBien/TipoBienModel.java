/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMTipoBien;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.suministros.TipoBien;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author danilo
 */
public class TipoBienModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMTipoBien";
	}

	@Override
	public String getNombreEntidad() {
		return "Categoría Bien";
	}

	private ABMTipoBien getBeanTipoBien() {
		return (ABMTipoBien) getRequestBean("compras$ABMTipoBien$ABMTipoBien");
	}

	private Validador getValidadorAgregarModificar() {

		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];
		int pos = 0;
		noVacios[pos] = getBeanTipoBien().getTfNombre();
		nomNoVacios[pos++] = "Nombre";

		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoBien locTipoBien = (TipoBien) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(getSessionBean1().getLlave());
			locTipoBien = getCommunicationComprasBean().getRemoteSystemAdministracionBienes().addTipoBien(locTipoBien);
			
			getApplicationBean1().agregarNuevoTipoBien(locTipoBien);
			return "El Tipo Bien se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return TipoBienModel.this;
		}

	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoBien locTipoBien = (TipoBien) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().updateTipoBien(locTipoBien);

			getApplicationBean1().modificarTipoBien(locTipoBien);
			return "El Tipo Bien se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {
			return TipoBienModel.this;
		}
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
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return TipoBienModel.this;
		}
	}

	private void deshabilitarElementosConsultarEliminar() {

		getBeanTipoBien().getTfNombre().setDisabled(true);
		getBeanTipoBien().getTaDescripcion().setDisabled(true);
		getBeanTipoBien().getTfCodigoImputacion().setDisabled(true);
		getBeanTipoBien().getTaComentarioLogAuditoria().setRendered(false);
		getBeanTipoBien().getLblComentarioLogAuditoria().setRendered(false);
	}

	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoBien locTipoBien = (TipoBien) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().deleteTipoBien(locTipoBien);

			getApplicationBean1().eliminarTipoBien(locTipoBien);
			return "El Tipo Bien se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return TipoBienModel.this;
		}
	}
}
