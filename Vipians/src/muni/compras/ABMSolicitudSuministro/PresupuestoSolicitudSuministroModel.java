package muni.compras.ABMSolicitudSuministro;

import java.util.List;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.suministros.LineaPresupuestoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.PresupuestoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author nico
 *
 */
public class PresupuestoSolicitudSuministroModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMPresupuestoSolicitudSuministro";
	}

	@Override
	public String getNombreEntidad() {
		return "Presupuesto de Solicitud de Suministro";
	}

	private ABMPresupuestoSolicitudSuministro getBeanPresupuestoSolSum() {
		return (ABMPresupuestoSolicitudSuministro) getRequestBean("compras$ABMSolicitudSuministro$ABMPresupuestoSolicitudSuministro");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];

		int pos = 0;
		noVacios[pos] = getBeanPresupuestoSolSum().getTfProveedor();
		nomNoVacios[pos++] = "Proveedor";

		v.noSonVacios(noVacios, nomNoVacios);

		boolean hayVacios = false;
		for (Object o : getBeanPresupuestoSolSum().getObjectListDataProvider().getList()) {
			LineaPresupuestoSolicitudSuministro cadaLinea = (LineaPresupuestoSolicitudSuministro) o;
			if (cadaLinea.getPrecioUnitario() == 0.00) {
				hayVacios = true;
			}
		}

		if (hayVacios) {
			v.getErrores().add("Debe asignar un precio unitario a todos los renglones");
		}

		if (getBeanPresupuestoSolSum().getObjectListDataProvider().getList().size() <= 0) {
			v.getErrores().add("El Presupuesto de Solicitud debe tener al menos un Renglón");
		}

		PresupuestoSolicitudSuministro locPresupuesto = (PresupuestoSolicitudSuministro) getBeanPresupuestoSolSum()
				.obtenerObjetoDelElementoPila(0, PresupuestoSolicitudSuministro.class);

		if (getBeanPresupuestoSolSum().getTfProveedor().getValue() != null && !getBeanPresupuestoSolSum().getTfProveedor().getValue().equals("")) {
			List listaPresupuestos = locPresupuesto.getSolicitudSuministro().getListaPresupuestos();
			Proveedor locProveedor = locPresupuesto.getProveedor();
			boolean yaExiste = false;
			for (Object o : listaPresupuestos) {
				PresupuestoSolicitudSuministro cadaPresupuesto = (PresupuestoSolicitudSuministro) o;

				if (!cadaPresupuesto.equals(locPresupuesto) && cadaPresupuesto.getProveedor().equals(locProveedor)) {
					yaExiste = true;
				}
			}
			if (yaExiste) {
				v.getErrores().add("Ya existe un Presupuesto de Solicitud de ese Proveedor");
			}
		}

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		getBeanPresupuestoSolSum().getTaComentario().setDisabled(true);
		getBeanPresupuestoSolSum().getTfPrecioUnitario().setDisabled(true);
		getBeanPresupuestoSolSum().getBtnSeleccionar().setRendered(false);
		getBeanPresupuestoSolSum().getTfPlazo().setDisabled(true);
	}

	public class AgregarPresupuestoSolicitudSuministroController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			PresupuestoSolicitudSuministro locPresupuesto = (PresupuestoSolicitudSuministro) pObject;
			getBeanPresupuestoSolSum().getRequestBean1().setRespuestaABM(locPresupuesto);
			
			return null;
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return PresupuestoSolicitudSuministroModel.this;
		}

	}

	public class ModificarPresupuestoSolicitudSuministroController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			PresupuestoSolicitudSuministro locPresupuesto = (PresupuestoSolicitudSuministro) pObject;
			getBeanPresupuestoSolSum().getRequestBean1().setRespuestaABM(locPresupuesto);
			return null;
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return PresupuestoSolicitudSuministroModel.this;
		}
	}

	public class ConsultarPresupuestoSolicitudSuministroController extends ConsultarAbstractController {

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
			return PresupuestoSolicitudSuministroModel.this;
		}

	}

	/*public class EliminarPresupuestoSolicitudSuministroController extends EliminarAbstractController {
		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {

			return "El Presupuesto se elimin\363 exitosamente.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return PresupuestoSolicitudSuministroModel.this;
		}
	}*/
}
