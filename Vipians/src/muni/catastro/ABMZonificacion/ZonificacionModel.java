/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.catastro.ABMZonificacion;

import javax.faces.component.UIComponent;

import com.trascender.catastro.recurso.persistent.Zonificacion;
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
public class ZonificacionModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMZonificacion";
	}

	@Override
	public String getNombreEntidad() {
		return "Zonificacion";
	}

	private ABMZonificacion getBeanZonificacion() {
		return (ABMZonificacion) getRequestBean("catastro$ABMZonificacion$ABMZonificacion");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];
		int pos = 0;

		noVacios[pos] = getBeanZonificacion().getTfNombre();
		nomNoVacios[pos++] = "Nombre";

		v.noSonVacios(noVacios, nomNoVacios);

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMZonificacion abmZonificacion = getBeanZonificacion();
		abmZonificacion.getTfNombre().setDisabled(true);
	}

	public class AgregarZonificacionController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Zonificacion locZonificacion = (Zonificacion) pObject;
			System.out.println("+++++ ZONIFICACION " + locZonificacion.getNombre());
			getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().addZonificacion(locZonificacion);

			return "La zonificacion se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ZonificacionModel.this;
		}
	}

	public class ModificarZonificacionController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Zonificacion locZonificacion = (Zonificacion) pObject;
			System.out.println("+++++ ZONIFICACION " + locZonificacion.getNombre());
			getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().updateZonificacion(locZonificacion);

			return "La zonificacion se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ZonificacionModel.this;
		}
	}

	public class ConsultarZonificacionController extends ConsultarAbstractController {

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
			return ZonificacionModel.this;
		}
	}

	public class EliminarZonificacionController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Zonificacion locZonificacion = (Zonificacion) pObject;
			getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().removeZonificacion(locZonificacion);

			return "La zonificacion se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return ZonificacionModel.this;
		}
	}
}