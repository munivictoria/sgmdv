package muni.framework.ABMProcesoDB;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.ProcesoDB;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class ProcesoDBModel extends ABMModel{

	@Override
	public String getReglaNavegacion() {
		return "ABMProcesoDB";
	}

	@Override
	public String getNombreEntidad() {
		return "Proceso DB";
	}
	
	private ABMProcesoDB getBean() {
		return (ABMProcesoDB) getRequestBean("framework$ABMProcesoDB$ABMProcesoDB");
	}
	
	private void ocultarDeshabilitar() {
		getBean().getTfNombre().setDisabled(true);
		getBean().getTfNombreProceso().setDisabled(true);
		getBean().getTaDescripcion().setDisabled(true);
	}
	
	public class EjecutarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ProcesoDB locProceso = (ProcesoDB) pObject;
			String parametros = (String) getBean().obtenerObjetoDelElementoPila(1);
			String resultado = getComunicationBean().getRemoteSystemParametro().ejecutarProcesoDB(locProceso.getIdProcesoDB(), parametros);
			return resultado;
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ocultarDeshabilitar();
			getBean().getTablaLogs().setRendered(false);
			getBean().getTaComentarioLogAuditoria().setRendered(false);
			getBean().getLblComentarioLogAuditoria().setRendered(false);
		}
		
		@Override
		public String getTextoBotonAceptar() {
			return "Ejecutar";
		}

		@Override
		public ABMModel getModel() {
			return ProcesoDBModel.this;
		}
		
	}

}
