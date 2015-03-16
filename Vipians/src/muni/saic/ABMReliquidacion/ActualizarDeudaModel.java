package muni.saic.ABMReliquidacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;

public class ActualizarDeudaModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMActualizarDeuda";
	}

	@Override
	public String getNombreEntidad() {
		return "Actualizar Deuda Liquidación";
	}

	private ABMActualizarDeuda getBeanActualizarDeuda() {
		return (ABMActualizarDeuda) getRequestBean("saic$ABMReliquidacion$ABMActualizarDeuda");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		return v;
	}

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
			List<LiquidacionTasa> listaLiquidaciones = new ArrayList<LiquidacionTasa>(getBeanActualizarDeuda().getCommunicationSAICBean().getSeleccionadosSeleccionMultipleActualizarDeuda());//getBeanActualizarDeuda().obtenerObjetoDelElementoPila(0, ArrayList.class);
			
			Date fechaActualizacion = getBeanActualizarDeuda().obtenerObjetoDelElementoPila(1, Date.class);
			boolean aplicarIntereses = getBeanActualizarDeuda().obtenerObjetoDelElementoPila(2, Boolean.class);
			
			getCommunicationSAICBean().getRemoteSystemReliquidacion().setLlave(getSessionBean1().getLlave());
			for (LiquidacionTasa cadaLiquidacion : listaLiquidaciones) {
				if (cadaLiquidacion instanceof LiquidacionTasaAgrupada) {
					LiquidacionTasaAgrupada agrupada = (LiquidacionTasaAgrupada) cadaLiquidacion;
					for (LiquidacionTasa cadaLiquidacionIndividual : agrupada.getListaLiquidacionesTasa()) {
						getCommunicationSAICBean().getRemoteSystemReliquidacion().calcularIntereses(cadaLiquidacionIndividual, fechaActualizacion, aplicarIntereses, true);
					}
				} else {
					getCommunicationSAICBean().getRemoteSystemReliquidacion().calcularIntereses(cadaLiquidacion, fechaActualizacion, aplicarIntereses, true);
				}
			}
			return "Se actualiz\363 exitosamente la/s liquidacion/es.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanActualizarDeuda().getStTitulo().setText(getNombreEntidad());
			getBeanActualizarDeuda().getCbAplicarIntereses().setDisabled(
					!getCommunicationSAICBean().getEsAdministradorReliquidaciones());
		}

		@Override
		public ABMModel getModel() {
			return ActualizarDeudaModel.this;
		}
	}
}