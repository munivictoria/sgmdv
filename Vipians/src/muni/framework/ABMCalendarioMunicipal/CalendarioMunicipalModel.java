/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMCalendarioMunicipal;

import javax.faces.component.UIComponent;

import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 *
 * @author Fer Luca
 */

public class CalendarioMunicipalModel extends ABMModel{

	@Override
	public String getReglaNavegacion() {
		return "ABMCalendarioMunicipal";
	}

	@Override
	public String getNombreEntidad() {
		return "Calendario Municipal";
	}

	private ABMCalendarioMunicipal getBeanCalendarioMunicipal(){
		return (ABMCalendarioMunicipal) getRequestBean("framework$ABMCalendarioMunicipal$ABMCalendarioMunicipal");
	}

	private Validador getValidadorAgregarModificar(){
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[4];
		String[] nomNoVacios = new String[4];
		int pos = 0;
		noVacios[pos] = getBeanCalendarioMunicipal().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanCalendarioMunicipal().getTfAnio();
		nomNoVacios[pos++] = "Año";
		noVacios[pos] = getBeanCalendarioMunicipal().getDdTipoObligacion();
		nomNoVacios[pos++] = "Tipo de Obligación";
		noVacios[pos] = getBeanCalendarioMunicipal().getDdPlanes();
		nomNoVacios[pos++] = "Plan";

		v.noSonVacios(noVacios, nomNoVacios);

		if(getBeanCalendarioMunicipal().getObjectListDataProviderPeriodos().getList().isEmpty()){
			v.getErrores().add("Debe agregar al menos un Período a la lista");
		}

		return v;
	}

	private void deshabilitarElementosConsultarEliminar(){
		getBeanCalendarioMunicipal().getBtnAgregarPeriodo().setRendered(false);
		getBeanCalendarioMunicipal().getBtnModificarPeriodo().setRendered(false);
		getBeanCalendarioMunicipal().getBtnQuitarPeriodo().setRendered(false);
		getBeanCalendarioMunicipal().getBtnQuitarTodosPeriodo().setRendered(false);
		getBeanCalendarioMunicipal().getDdTipoObligacion().setDisabled(true);
		getBeanCalendarioMunicipal().getDdPlanes().setDisabled(true);
		getBeanCalendarioMunicipal().getTfAnio().setDisabled(true);
		getBeanCalendarioMunicipal().getTfNombre().setDisabled(true);
	}

	public class AgregarCalendarioController extends AgregarAbstractController{

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			CalendarioMunicipal locCalendario = (CalendarioMunicipal) pObject;
			getComunicationBean().getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemPeriodo().addCalendarioMunicipal(locCalendario);
			return "El Calendario Municipal se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return CalendarioMunicipalModel.this;
		}
	}

	public class ModificarCalendarioController extends ModificarAbstractController{

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			CalendarioMunicipal locCalendario = (CalendarioMunicipal) pObject;
			getComunicationBean().getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemPeriodo().updateCalendarioMunicipal(locCalendario);
			return "El Calendario Municipal se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return CalendarioMunicipalModel.this;
		}
	}

	public class ConsultarCalendarioController extends ConsultarAbstractController{

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return CalendarioMunicipalModel.this;
		}
	}

	public class ClonarCalendarioController extends AgregarAbstractController{

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			CalendarioMunicipal locCalendario = (CalendarioMunicipal) pObject;
			getComunicationBean().getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemPeriodo().addCalendarioMunicipal(locCalendario);
			return "El Calendario Municipal se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return CalendarioMunicipalModel.this;
		}
	}

	public class EliminarCalendarioController extends EliminarAbstractController{

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			CalendarioMunicipal locCalendario = (CalendarioMunicipal) pObject;
			getComunicationBean().getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemPeriodo().deleteCalendarioMunicipal(locCalendario);
			return "El Calendario Municipal se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
			getBeanCalendarioMunicipal().getTableColumn1().setRendered(false);
			getBeanCalendarioMunicipal().getBtnConsultarPeriodo().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return CalendarioMunicipalModel.this;
		}
	}
}
