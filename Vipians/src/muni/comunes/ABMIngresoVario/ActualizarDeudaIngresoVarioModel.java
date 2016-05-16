/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.comunes.ABMIngresoVario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIComponent;

import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class ActualizarDeudaIngresoVarioModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMActualizarDeudaIngresoVario";
	}

	@Override
	public String getNombreEntidad() {
		return "Actualizar Deuda Ingreso Vario";
	}

	private ABMActualizarDeudaIngresoVario getBeanActualizarDeudaIngresoVario() {
		return (ABMActualizarDeudaIngresoVario) getRequestBean("comunes$ABMIngresoVario$ABMActualizarDeudaIngresoVario");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];
		int pos = 0;

		noVacios[pos] = getBeanActualizarDeudaIngresoVario().getTfFechaVencimiento();
		nomNoVacios[pos++] = "Fecha vencimiento";

		v.noSonVacios(noVacios, nomNoVacios);

		if(v.getErrores().size() == 0) {
			String fechaVencimiento = getBeanActualizarDeudaIngresoVario().getTfFechaVencimiento().getText().toString();
			try {
				Date fechaInicio = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVencimiento);
				Date fechaHoy = new Date();

				long diasDeDiferencia = (fechaInicio.getTime() - fechaHoy.getTime()) / (60 * 60 * 1000 * 24);
				if(diasDeDiferencia < 0) {
					v.getErrores().add("La fecha del vencimiento no puede ser menor al día de hoy.");
				} else if(diasDeDiferencia > 364) {
					v.getErrores().add("La fecha del vencimiento no puede superar el año.");
				}
			} catch(ParseException e) {
				e.printStackTrace();
			}
		}

		return v;
	}

	@SuppressWarnings("unused")
	private void deshabilitarElementosConsultarEliminar() {

	}

	public class ActualizarController extends ModificarAbstractController {

		@Override
		public String getTextoBotonAceptar() {
			return "Actualizar";
		}

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			List<IngresoVario> listaIngresosVarios = new ArrayList<IngresoVario>(getBeanActualizarDeudaIngresoVario().getCommunicationSAICBean()
					.getSeleccionadosSeleccionMultipleActualizarDeudaIngresoVario());

			Date fechaActualizacion = getBeanActualizarDeudaIngresoVario().obtenerObjetoDelElementoPila(1, Date.class);
			boolean aplicarIntereses = getBeanActualizarDeudaIngresoVario().obtenerObjetoDelElementoPila(2, Boolean.class);

			getCommunicationContabilidadBean().getRemoteSystemAdministracionIngresos().setLlave(getSessionBean1().getLlave());
			for(IngresoVario cadaIngresoVario : listaIngresosVarios) {
				getCommunicationContabilidadBean().getRemoteSystemAdministracionIngresos().actualizarDeudaIngresoVario(cadaIngresoVario, fechaActualizacion, aplicarIntereses);
			}

			return "Se actualiz\363 exitosamente la deuda de los Ingresos Varios.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanActualizarDeudaIngresoVario().getStTitulo().setText(getNombreEntidad());
			// getBeanActualizarDeudaIngresoVario().getCbAplicarIntereses().setDisabled(!getCommunicationSAICBean().getEsAdministradorReliquidaciones());
		}

		@Override
		public ABMModel getModel() {
			return ActualizarDeudaIngresoVarioModel.this;
		}

	}

}