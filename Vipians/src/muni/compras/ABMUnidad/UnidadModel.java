package muni.compras.ABMUnidad;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.suministros.Unidad;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class UnidadModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMUnidad";
	}

	@Override
	public String getNombreEntidad() {
		return "Unidad de Medida";
	}

	private ABMUnidad getBeanUnidad() {
		return (ABMUnidad) getRequestBean("compras$ABMUnidad$ABMUnidad");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];

		int pos = 0;
		noVacios[pos] = getBeanUnidad().getTfDescripcion();
		nomNoVacios[pos++] = "Descripción";

		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		getBeanUnidad().getTfDescripcion().setDisabled(true);
		getBeanUnidad().getTaComentarioLogAuditoria().setRendered(false);
		getBeanUnidad().getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Unidad locUnidad = (Unidad) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(getSessionBean1().getLlave());
			locUnidad = getCommunicationComprasBean().getRemoteSystemAdministracionBienes().addUnidad(locUnidad);

			getApplicationBean1().agregarNuevaUnidad(locUnidad);
			return "La Unidad de Medida se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return UnidadModel.this;
		}

	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Unidad locUnidad = (Unidad) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().updateUnidad(locUnidad);

			getApplicationBean1().modificarUnidad(locUnidad);
			return "La Unidad de Medida se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return UnidadModel.this;
		}

	}

	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Unidad locUnidad = (Unidad) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().deleteUnidad(locUnidad);

			getApplicationBean1().eliminarUnidad(locUnidad);
			return "La Unidad de Medida se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return UnidadModel.this;
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
			return UnidadModel.this;
		}

	}

}
