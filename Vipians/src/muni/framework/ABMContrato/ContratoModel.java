package muni.framework.ABMContrato;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.Contrato;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class ContratoModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMContrato";
	}

	@Override
	public String getNombreEntidad() {
		return "Contrato";
	}

	private ABMContrato getBeanContrato() {
		return (ABMContrato) getRequestBean("framework$ABMContrato$ABMContrato");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[8];
		String[] nomNoVacios = new String[8];
		UIComponent[] fechas = new UIComponent[2];
		String[] nomFechas = new String[2];
		UIComponent[] esEntero = new UIComponent[2];
		String[] nomEsEntero = new String[2];
		UIComponent[] flotantes = new UIComponent[1];
		String[] nomFlotantes = new String[1];
		UIComponent[] esPositivo = new UIComponent[1];
		String[] nomEsPositivo = new String[1];

		int pos = 0;
		noVacios[pos] = getBeanContrato().getTfCodigoContrato();
		nomNoVacios[pos++] = "C\363digo Contrato";
		noVacios[pos] = getBeanContrato().getTfPersona();
		nomNoVacios[pos++] = "Persona";
		noVacios[pos] = getBeanContrato().getTfFechaInicio();
		nomNoVacios[pos++] = "Fecha de Inicio";
		noVacios[pos] = getBeanContrato().getTfFechaFin();
		nomNoVacios[pos++] = "Fecha de Fin";
		noVacios[pos] = getBeanContrato().getTfCantidadCuotas();
		nomNoVacios[pos++] = "Cantidad de Cuotas";
		noVacios[pos] = getBeanContrato().getTfPrecioCuotas();
		nomNoVacios[pos++] = "Precio por Cuotas";
		noVacios[pos] = getBeanContrato().getTfTotal();
		nomNoVacios[pos++] = "Total";
		noVacios[pos] = getBeanContrato().getTaDescripcion();
		nomNoVacios[pos++] = "Descripci\363n";

		pos = 0;
		fechas[pos] = getBeanContrato().getTfFechaInicio();
		nomFechas[pos++] = "Fecha de Inicio";
		fechas[pos] = getBeanContrato().getTfFechaFin();
		nomFechas[pos++] = "Fecha de Fin";

		pos = 0;
		flotantes[pos] = getBeanContrato().getTfPrecioCuotas();
		nomFlotantes[pos++] = "Precio Por Cuota";

		pos = 0;
		esPositivo[pos] = getBeanContrato().getTfPrecioCuotas();
		nomEsPositivo[pos++] = "Precio Por Cuota";

		pos = 0;
		esEntero[pos] = getBeanContrato().getTfCodigoContrato();
		nomEsEntero[pos++] = "C\363digo Contrato";
		esEntero[pos] = getBeanContrato().getTfCantidadCuotas();
		nomEsEntero[pos++] = "Cantidad Cuotas";

		v.noSonVacios(noVacios, nomNoVacios);
		v.formatoFechaValido(fechas, nomFechas);
		v.sonFlotantes(flotantes, nomFlotantes);
		v.esNumero(esEntero, nomEsEntero);
		v.sonPositivos(esPositivo, nomEsPositivo);

		if (getBeanContrato().getTfFechaInicio().getText() != null && getBeanContrato().getTfFechaFin().getText() != null) {
			v.fechaEsMenorQue(fechas, nomFechas);
		}
		/*
		 * if (!v.fechaNoMayorAFechaActual(Conversor.getFechaCortaDeString(this.
		 * getTfFechaInicio().getText().toString()), "Fecha de Inicio")) {
		 * this.getTfFechaInicio().setValid(false); }
		 */

		if (getBeanContrato().getTfPrecioCuotas().getText().toString().equals("0.0") || getBeanContrato().getTfPrecioCuotas().getText().toString().equals("0")) {
			String msg = "El Precio de la Cuota debe ser mayor a 0.";
			v.getErrores().add(msg);
		}
		return v;

	}

	private void deshabilitarElementosConsultarEliminar() {
		getBeanContrato().getTfCantidadCuotas().setDisabled(true);
		getBeanContrato().getTfCodigoContrato().setDisabled(true);
		getBeanContrato().getTfFechaFin().setDisabled(true);
		getBeanContrato().getTfFechaInicio().setDisabled(true);
		getBeanContrato().getTfPersona().setDisabled(true);
		getBeanContrato().getTfPrecioCuotas().setDisabled(true);
		getBeanContrato().getTfTotal().setDisabled(true);
		getBeanContrato().getTaDescripcion().setDisabled(true);
		getBeanContrato().getBtnSeleccionarPersonaFisica().setVisible(false);
		getBeanContrato().getBtnSeleccionarPersonaJuridica().setVisible(false);
		getBeanContrato().getBtnLimpiarPersona().setRendered(false);

	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Contrato locContrato = (Contrato) pObject;
			getComunicationBean().getRemoteSystemContrato().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemContrato().addContrato(locContrato);
			return "El contrato se agreg\363 exitosamente";

		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ContratoModel.this;
		}
	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Contrato locContrato = (Contrato) pObject;
			getComunicationBean().getRemoteSystemContrato().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemContrato().updateContrato(locContrato);
			return "El contrato se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ContratoModel.this;
		}
	}

	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Contrato locContrato = (Contrato) pObject;
			getComunicationBean().getRemoteSystemContrato().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemContrato().deleteContrato(locContrato);
			return "El contrato se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return ContratoModel.this;
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
			return ContratoModel.this;
		}
	}
}