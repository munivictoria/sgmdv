/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMMunicipalidad;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.Municipalidad;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author juanma
 */
public class MunicipalidadModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMMunicipalidad";
	}

	@Override
	public String getNombreEntidad() {
		return "Municipalidad";
	}

	private ABMMunicipalidad getBeanMunicipalidad() {
		return (ABMMunicipalidad) getRequestBean("framework$ABMMunicipalidad$ABMMunicipalidad");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		int pos = 0;
		noVacios[pos] = getBeanMunicipalidad().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanMunicipalidad().getTfTelefono();
		nomNoVacios[pos++] = "Telefono";
		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMMunicipalidad abmMunicipalidad = getBeanMunicipalidad();
		abmMunicipalidad.getTfNombre().setDisabled(true);
		abmMunicipalidad.getTfTelefono().setDisabled(true);
		abmMunicipalidad.getBtnSeleccionarDomicilio().setRendered(false);
		abmMunicipalidad.getTfEncabezado().setDisabled(true);
		abmMunicipalidad.getTfSubEncabezado().setDisabled(true);
		abmMunicipalidad.getCbVariosServicios().setDisabled(true);
		abmMunicipalidad.getCbNumerarSolSum().setDisabled(true);
		abmMunicipalidad.getTfRutaReporte().setDisabled(true);
	}

	public class AgregarMunicipalidadController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			return null;
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return MunicipalidadModel.this;
		}
	}

	public class ModificarMunicipalidadController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Municipalidad locMunicipalidad = (Municipalidad) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemMunicipalidad().setMunicipalidad(locMunicipalidad);

			if (locMunicipalidad != null) {
				System.out.println("Municipalidad: " + locMunicipalidad);
				getApplicationBean1().setMunicipalidad(locMunicipalidad);
			}
			return "La municipalidad se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}
		
		@Override
		public ABMModel getModel() {
			return MunicipalidadModel.this;
		}
	}

	public class ConsultarMunicipalidadController extends ConsultarAbstractController {

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
			return MunicipalidadModel.this;
		}
	}
}