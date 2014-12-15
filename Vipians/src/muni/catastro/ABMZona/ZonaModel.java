/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.catastro.ABMZona;

import javax.faces.component.UIComponent;

import com.trascender.catastro.recurso.persistent.Zona;
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
public class ZonaModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMZona";
	}

	@Override
	public String getNombreEntidad() {
		return "Zona";
	}

	private ABMZona getBeanZona() {
		return (ABMZona) getRequestBean("catastro$ABMZona$ABMZona");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[3];
		String[] nomNoVacios = new String[3];
		int pos = 0;

		noVacios[pos] = getBeanZona().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanZona().getTfPrioridad();
		nomNoVacios[pos++] = "Prioridad";
		noVacios[pos] = getBeanZona().getTfZonificacion();
		nomNoVacios[pos++] = "Zonificacion";
		// noVacios[pos] = getBeanZona().getTxDescripcion();
		// nomNoVacios[pos++] = "Descripcion";

		v.noSonVacios(noVacios, nomNoVacios);

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMZona abmZona = getBeanZona();
		abmZona.getTfNombre().setDisabled(true);
		abmZona.getTfPrioridad().setDisabled(true);
		abmZona.getTfZonificacion().setDisabled(true);
		abmZona.getTxDescripcion().setDisabled(true);
		abmZona.getBtnSeleccionarZonificacion().setRendered(false);
		abmZona.getBtnLimpiarZonificacion().setRendered(false);
		abmZona.getTableColumn1().setRendered(false);
		abmZona.getGroupPanel3().setRendered(false);
		abmZona.getTaComentarioLogAuditoria().setRendered(false);
		abmZona.getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarZonaController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Zona locZona = (Zona) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().addZona(locZona);
			return "La Zona se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ZonaModel.this;
		}
	}

	public class ModificarZonaController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Zona locZona = (Zona) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().updateZona(locZona);
			return "La zona se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ZonaModel.this;
		}
	}

	public class ConsultarZonaController extends ConsultarAbstractController {

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
			return ZonaModel.this;
		}
	}

	public class EliminarZonaController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Zona locZona = (Zona) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().deleteZona(locZona);
			return "La zona se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return ZonaModel.this;
		}
	}
}