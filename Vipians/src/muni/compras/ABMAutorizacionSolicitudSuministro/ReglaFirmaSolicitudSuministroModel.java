/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMAutorizacionSolicitudSuministro;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.suministros.ReglaFirmaSolicitudSuministro;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author Fer Luca
 */
public class ReglaFirmaSolicitudSuministroModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMReglaFirmaSolicitudSuministro";
	}

	@Override
	public String getNombreEntidad() {
		return "Regla Firma de la Solicitud de Suministro";
	}

	private ABMReglaFirmaSolicitudSuministro getBeanReglaFirmaSolSum() {
		return (ABMReglaFirmaSolicitudSuministro) getRequestBean("compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro");
	}

	private Validador getValidadorAgregarModificar() {

		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];

		int pos = 0;
		noVacios[pos] = getBeanReglaFirmaSolSum().getTfOrden();
		nomNoVacios[pos++] = "Orden";
		noVacios[pos] = getBeanReglaFirmaSolSum().getDdEstado();
		nomNoVacios[pos++] = "Estado";

		v.noSonVacios(noVacios, nomNoVacios);

		ReglaFirmaSolicitudSuministro locRegla = (ReglaFirmaSolicitudSuministro) getBeanReglaFirmaSolSum().getElementoPila().getObjetos().get(0);

		if (locRegla.getListaUsuarios() == null || locRegla.getListaUsuarios().isEmpty()) {
			v.getErrores().add("Debe haber al menos un Usuario en la lista");
		}

		return v;
	}

	private void ocultarConsultarEliminar() {
		getBeanReglaFirmaSolSum().getBtnAgregar().setRendered(false);
		getBeanReglaFirmaSolSum().getBtnQuitar().setRendered(false);
		getBeanReglaFirmaSolSum().getBtnQuitarTodos().setRendered(false);
		getBeanReglaFirmaSolSum().getTfOrden().setDisabled(true);
		getBeanReglaFirmaSolSum().getDdEstado().setDisabled(true);
		getBeanReglaFirmaSolSum().getDdUsuarios().setDisabled(true);
		getBeanReglaFirmaSolSum().getGroupPanel1().setRendered(false);
		getBeanReglaFirmaSolSum().getTableColumn1().setRendered(false);
		getBeanReglaFirmaSolSum().getCbUrgente().setDisabled(true);
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			getBeanReglaFirmaSolSum().getRequestBean1().setObjetoSeleccion(pObject);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {
			return ReglaFirmaSolicitudSuministroModel.this;
		}

	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			// getBeanUsuarioAutorizadorSuplente().getRequestBean1().setObjetoSeleccion(pObject);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}
		
		@Override
		public ABMModel getModel() {
			return ReglaFirmaSolicitudSuministroModel.this;
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
			ocultarConsultarEliminar();
		}
		
		@Override
		public ABMModel getModel() {
			return ReglaFirmaSolicitudSuministroModel.this;
		}
	}
}
