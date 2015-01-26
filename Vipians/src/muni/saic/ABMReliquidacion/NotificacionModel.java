package muni.saic.ABMReliquidacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;

public class NotificacionModel extends ABMModel{

	@Override
	public String getReglaNavegacion() {
		return "ABMNotificacion";
	}

	@Override
	public String getNombreEntidad() {
		return "Notificar";
	}
	
	private ABMNotificacion getBeanNotificacion() {
		return (ABMNotificacion) getRequestBean("saic$ABMReliquidacion$ABMNotificacion");
	}
	
	public Validador getValidadorAgregarModificar() {
		return null;
	}
	
	public class NotificarController extends ModificarAbstractController {
		
		@Override
	    public String getTextoBotonAceptar() {
	        return "Notificar";
	    }
		
		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}
		

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			List<LiquidacionTasa> listaLiquidaciones = 
					new ArrayList<LiquidacionTasa>(getCommunicationSAICBean().
							getSeleccionadosSeleccionMultipleNotificacion());
			
			Date fechaNotificacion = (Date) getBeanNotificacion().obtenerObjetoDelElementoPila(1);
			Date fechaApremio = (Date) getBeanNotificacion().obtenerObjetoDelElementoPila(2);
			String comentario = (String) getBeanNotificacion().obtenerObjetoDelElementoPila(3);
			
			getCommunicationSAICBean().getRemoteSystemReliquidacion().setLlave(getSessionBean1().getLlave());
			
			for (LiquidacionTasa cadaLiquidacion : listaLiquidaciones) {
				if (cadaLiquidacion instanceof LiquidacionTasaAgrupada) {
					LiquidacionTasaAgrupada agrupada = (LiquidacionTasaAgrupada) cadaLiquidacion;
					for (LiquidacionTasa cadaLiquidacionIndividual : agrupada.getListaLiquidacionesTasa()) {
						getCommunicationSAICBean().getRemoteSystemReliquidacion().notificar(cadaLiquidacionIndividual, fechaNotificacion, fechaApremio, comentario);
					}
				} else {
					getCommunicationSAICBean().getRemoteSystemReliquidacion().notificar(cadaLiquidacion, fechaNotificacion, fechaApremio, comentario);
				}
			}
			return "Notificación realizada con exito";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return NotificacionModel.this;
		}
	}

}
