package muni.framework.ABMReporteJasper;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.ReportesJasper;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class ReporteJasperModel extends ABMModel{
	
	@Override
	public String getReglaNavegacion() {
		return "ABMReporteJasper";
	}

	@Override
	public String getNombreEntidad() {
		return "Reporte";
	}

	private ABMReporteJasper getBeanReporteJasper() {
		return (ABMReporteJasper) getRequestBean("framework$ABMReporteJasper$ABMReporteJasper");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		int pos = 0;
		noVacios[pos] = getBeanReporteJasper().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanReporteJasper().getTfNombreArchivo();
		nomNoVacios[pos++] = "Archivo";

		v.noSonVacios(noVacios, nomNoVacios);
		
		ReportesJasper reporteJasper = getBeanReporteJasper().obtenerObjetoDelElementoPila(0, ReportesJasper.class);
	
		if (!reporteJasper.getNombreArchivo().endsWith(".jasper")){
			v.getErrores().add("El archivo debe tener extension .jasper");
		}
		
		return v;
	}
	
	private void deshabilitarElementosConsultarEliminar() {
		getBeanReporteJasper().getTfNombre().setDisabled(true);
		getBeanReporteJasper().getUpload().setDisabled(true);
		getBeanReporteJasper().getLblEliminarArchivo().setRendered(false);
		getBeanReporteJasper().getTfNombreArchivo().setDisabled(true);
		
	}
	
	public class AgregarReporteController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			getComunicationBean().getRemoteSystemParametro().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemParametro().addReporteJasper((ReportesJasper) pObject);

			return "El Reporte se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanReporteJasper().getLblEliminarArchivo().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return ReporteJasperModel.this;
		}
	}
	
	public class ModificarReporteController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			getComunicationBean().getRemoteSystemParametro().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemParametro().updateReporteJasper((ReportesJasper)pObject);
			return "El Reporte se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanReporteJasper().getLblEliminarArchivo().setRendered(false);
			getBeanReporteJasper().getTfNombre().setDisabled(true);
		}

		@Override
		public ABMModel getModel() {
			return ReporteJasperModel.this;
		}
	}
	
	public class ConsultarReporteController extends ConsultarAbstractController {

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
			return ReporteJasperModel.this;
		}
	}
}
