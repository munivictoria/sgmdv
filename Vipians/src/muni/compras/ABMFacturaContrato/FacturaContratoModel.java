/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMFacturaContrato;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.suministros.FacturaContrato;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author ubuntero
 */
public class FacturaContratoModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMFacturaContrato";
	}

	@Override
	public String getNombreEntidad() {
		return "Factura Contrato";
	}

	private ABMFacturaContrato getBeanFacturaContrato() {
		return (ABMFacturaContrato) getRequestBean("compras$ABMFacturaContrato$ABMFacturaContrato");
	}

	private Validador getValidadorAgregarModificar() {

		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[3];
		String[] nomNoVacios = new String[3];

		int pos = 0;
		noVacios[pos] = getBeanFacturaContrato().getTfProveedor();
		nomNoVacios[pos++] = "Proveedor";
		noVacios[pos] = getBeanFacturaContrato().getTfContrato();
		nomNoVacios[pos++] = "Contrato";
		noVacios[pos] = getBeanFacturaContrato().getTfFecha();
		nomNoVacios[pos++] = "Fecha de Emisi\363n";

		v.noSonVacios(noVacios, nomNoVacios);

		UIComponent[] fechas = new UIComponent[1];
		String[] nomFechas = new String[1];

		fechas[0] = getBeanFacturaContrato().getTfFecha();
		nomFechas[0] = "Fecha de Emisi\363n";

		v.formatoFechaValido(fechas, nomFechas);

		if (getBeanFacturaContrato().getListaDelCommunication() == null || getBeanFacturaContrato().getListaDelCommunication().isEmpty()) {
			String msg = "Debe agregar al menos una L\355nea Factura .";
			v.getErrores().add(msg);
		}

		FacturaContrato facturaContrato = (FacturaContrato) getBeanFacturaContrato().obtenerObjetoDelElementoPila(0, FacturaContrato.class);

		if (!v.fechaNoMayorAFechaActual(facturaContrato.getFechaEmision(), "Fecha de Emisi\363n")) {
			getBeanFacturaContrato().getTfFecha().setValid(false);
		}
		return v;
	}

	private void ocultarConsultarEliminar() {
		getBeanFacturaContrato().getTfBienProvisto().setDisabled(true);
		getBeanFacturaContrato().getTfCantidad().setDisabled(true);
		getBeanFacturaContrato().getTfCodigoProveedor().setDisabled(true);
		getBeanFacturaContrato().getTfContrato().setDisabled(true);
		getBeanFacturaContrato().getTfCuenta().setDisabled(true);
		getBeanFacturaContrato().getTfFecha().setDisabled(true);
		getBeanFacturaContrato().getTfLineaCantidad().setDisabled(true);
		getBeanFacturaContrato().getTfLineaProveedor().setDisabled(true);
		getBeanFacturaContrato().getTfMonto().setDisabled(true);
		getBeanFacturaContrato().getTfMontoTotal().setDisabled(true);
		getBeanFacturaContrato().getTfNumeroFactura().setDisabled(true);
		getBeanFacturaContrato().getTfProveedor().setDisabled(true);
		getBeanFacturaContrato().getTfTotal().setDisabled(true);
		getBeanFacturaContrato().getDdTipoFactura().setDisabled(true);

		getBeanFacturaContrato().getGroupPanel1().setRendered(false);
		getBeanFacturaContrato().getBtnSeleccionarContrato().setRendered(false);
		getBeanFacturaContrato().getBtnLimpiarContrato().setRendered(false);
		getBeanFacturaContrato().getBtnSeleccionarProveedor().setRendered(false);
		getBeanFacturaContrato().getBtnLimpiarProveedor().setRendered(false);
		getBeanFacturaContrato().getTableColumn1().setRendered(false);
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			FacturaContrato locFacturaContrato = (FacturaContrato) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionFacturaContrato().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionFacturaContrato().addFacturaContrato(locFacturaContrato);
			return "La factura por contrato se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			
		}

		@Override
		public ABMModel getModel() {
			return FacturaContratoModel.this;
		}
	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			FacturaContrato locFacturaContrato = (FacturaContrato) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionFacturaContrato().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionFacturaContrato().updateFacturaContrato(locFacturaContrato);
			return "La factura por contrato se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return FacturaContratoModel.this;
		}
	}

	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			FacturaContrato locFacturaContrato = (FacturaContrato) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionFacturaContrato().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionFacturaContrato().deleteFacturaContrato(locFacturaContrato);
			return "La factura por contrato se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ocultarConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return FacturaContratoModel.this;
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
			ocultarConsultarEliminar();
			getBeanFacturaContrato().getTaComentarioLogAuditoria().setRendered(false);
			getBeanFacturaContrato().getLblComentarioLogAuditoria().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return FacturaContratoModel.this;
		}
	}
}
