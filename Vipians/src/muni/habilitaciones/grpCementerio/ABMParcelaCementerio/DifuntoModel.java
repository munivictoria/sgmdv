package muni.habilitaciones.grpCementerio.ABMParcelaCementerio;

import javax.faces.component.UIComponent;

import com.trascender.habilitaciones.recurso.persistent.cementerio.Difunto;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class DifuntoModel extends ABMModel{

	private ABMDifunto getBeanDifunto(){
		return (ABMDifunto) getRequestBean("habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto");
	}

	private Validador getValidadorAgregarModificar() {
		return null;
	}

	private void deshabilitarElementosConsultarEliminar() {
		getBeanDifunto().getTaCausaDeceso().setDisabled(true);
		getBeanDifunto().getTfFechaDeceso().setDisabled(true);
		getBeanDifunto().getBtnSeleccionarPersona().setRendered(false);
		getBeanDifunto().getCkbInmunoinfecciosa().setDisabled(true);
		getBeanDifunto().getCkbReducido().setDisabled(true);
		getBeanDifunto().getCkbCremado().setDisabled(true);
	}

	public class AgregarDifuntoController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Difunto locDifunto = (Difunto) pObject;
			getBeanDifunto().getRequestBean1().setObjetoSeleccion(locDifunto);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			// TODO Auto-generated method stub

		}

		@Override
		public ABMModel getModel() {
			return DifuntoModel.this;
		}

	}

	public class ModificarDifuntoController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			//			Difunto locDifunto = (Difunto) pObject;
			//            getBeanDifunto().getRequestBean1().setObjetoSeleccion(locDifunto);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			// TODO Auto-generated method stub

		}

		@Override
		public ABMModel getModel() {
			return DifuntoModel.this;
		}
	}

	public class ConsultarDifuntoController extends ConsultarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			//			Difunto locDifunto = (Difunto) pObject;
			//            getBeanDifunto().getRequestBean1().setObjetoSeleccion(locDifunto);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return DifuntoModel.this;
		}
	}

	@Override
	public String getReglaNavegacion() {
		return "ABMDifunto";
	}

	@Override
	public String getNombreEntidad() {
		return "Difunto";
	}
}
