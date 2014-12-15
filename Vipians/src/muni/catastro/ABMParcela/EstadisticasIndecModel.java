package muni.catastro.ABMParcela;

import com.trascender.catastro.recurso.persistent.EstadisticasIndec;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * @author danilo
 *
 */
public class EstadisticasIndecModel extends ABMModel{

	@Override
	public String getReglaNavegacion() {
		return "ABMEstadisticasIndec";
	}

	@Override
	public String getNombreEntidad() {
		return "Estadisticas Indec";
	}
	
	private ABMEstadisticasIndec getBeanEstadisticasIndec() {
		return (ABMEstadisticasIndec) getRequestBean("catastro$ABMParcela$ABMEstadisticasIndec");
	}
	
	public class AgregarEstadisticasIndecController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			EstadisticasIndec locEstadisticaIndec = (EstadisticasIndec) pObject;
			getBeanEstadisticasIndec().getRequestBean1().setObjetoSeleccion(locEstadisticaIndec);

			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return EstadisticasIndecModel.this;
		}
	}
	
	public class ModificarEstadisticasIndecController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			EstadisticasIndec locEstadisticaIndec = (EstadisticasIndec) pObject;
			getBeanEstadisticasIndec().getRequestBean1().setObjetoSeleccion(locEstadisticaIndec);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return EstadisticasIndecModel.this;
		}
	}

}
