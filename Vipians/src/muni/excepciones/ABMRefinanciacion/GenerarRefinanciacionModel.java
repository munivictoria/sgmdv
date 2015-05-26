package muni.excepciones.ABMRefinanciacion;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.validadores.Validador;


public class GenerarRefinanciacionModel extends ABMModel{

	private GenerarRefinanciacion getBeanGenerarRefinanciacion() {
		return (GenerarRefinanciacion) getRequestBean("excepciones$ABMRefinanciacion$GenerarRefinanciacion");
	}

	@Override
	public String getReglaNavegacion() {
		return "GenerarRefinanciacion";
	}

	@Override
	public String getNombreEntidad() {
		return "Refinanciación";
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {

	}

	public class GenerarRefinanciacionController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			return "La Refinanciaci\363n se gener\363 exitosamente.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}
		
		@Override
		public ABMModel getModel() {
			return GenerarRefinanciacionModel.this;
		}
		
		@Override
		public String getTituloPagina() {
			return "Generar " + this.getModel().getNombreEntidad();
		}
		
		@Override
		public String getTextoBotonCancelar() {
			return "Volver";
		}
		
	}
	
}