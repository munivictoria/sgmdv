/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMOrdenCompra;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.PagoOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.presentacion.abstracts.controller.ABMController;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.FirmarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author ubuntero
 */
public class OrdenCompraModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMOrdenCompra";
	}

	@Override
	public String getNombreEntidad() {
		return "Orden Compra";
	}

	private ABMOrdenCompra getBeanOrdenCompra() {
		return (ABMOrdenCompra) getRequestBean("compras$ABMOrdenCompra$ABMOrdenCompra");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		UIComponent[] fechas = new UIComponent[1];
		String[] nomFechas = new String[1];

		int pos = 0;
		noVacios[pos] = getBeanOrdenCompra().getTfProveedor();
		nomNoVacios[pos++] = "Proveedor";
		noVacios[pos] = getBeanOrdenCompra().getTfFecha();
		nomNoVacios[pos++] = "Fecha";

		pos = 0;
		fechas[pos] = getBeanOrdenCompra().getTfFecha();
		nomFechas[pos++] = "Fecha";

		v.noSonVacios(noVacios, nomNoVacios);
		v.formatoFechaValido(fechas, nomFechas);

		if (getBeanOrdenCompra().getObjectListDataProvider().getList() == null
				|| getBeanOrdenCompra().getObjectListDataProvider().getList().isEmpty()) {

			v.getErrores().add("Debe agregar al menos una Línea a la Orden de Compra");
		}

		boolean hayVacios = false;
		for (Object o : getBeanOrdenCompra().getObjectListDataProvider().getList()) {
			LineaOrdenCompra cadaLinea = (LineaOrdenCompra) o;
			if (cadaLinea.getMontoUnitario() == 0.00) {
				hayVacios = true;
			}
		}

		if (hayVacios) {
			v.getErrores().add("Debe asignar un precio unitario a todas las Líneas");
		}

		return v;
	}

	private void ocultarConsultarFinalizarEnVista() {

		getBeanOrdenCompra().getTfFecha().setDisabled(true);
		getBeanOrdenCompra().getTfProveedor().setDisabled(true);
		getBeanOrdenCompra().getTaDescripcion().setDisabled(true);
		getBeanOrdenCompra().getTfPrecioUnitario().setDisabled(true);
		
		getBeanOrdenCompra().getBtnSeleccionarProveedor().setRendered(false);
		getBeanOrdenCompra().getBtnLimpiarProveedor().setRendered(false);

		getBeanOrdenCompra().getGroupPanel1().setRendered(false);
		getBeanOrdenCompra().getTableColumn2().setRendered(false);
		getBeanOrdenCompra().getPgPagos().setRendered(false);
		getBeanOrdenCompra().getGroupPanelPagos().setRendered(false);
		getBeanOrdenCompra().getTableColumn1Pagos().setRendered(false);
		getBeanOrdenCompra().getTfNombrePago().setDisabled(true);
		getBeanOrdenCompra().getTfNombrePago().setStyleClass("textFieldDisabled");
		getBeanOrdenCompra().getTfMontoPago().setDisabled(true);
		getBeanOrdenCompra().getTfMontoPago().setStyleClass("textFieldDisabled");
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			OrdenCompra locOrdenCompra = (OrdenCompra) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().addOrdenCompra(locOrdenCompra);
			return "La orden de compra se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanOrdenCompra().getTable3().setRendered(false);
			getBeanOrdenCompra().getLblFirmas().setRendered(false);
			getBeanOrdenCompra().getTfNumero().setText("Asignado al guardar");
			getBeanOrdenCompra().getPgFinalizarComo().setRendered(false);
			getBeanOrdenCompra().getPgTransferirOrdenCompra().setRendered(false);
			getBeanOrdenCompra().getLblComentarioTransferencia().setRendered(false);
			getBeanOrdenCompra().getTaComentarioTransferencia().setRendered(false);

			getBeanOrdenCompra().getTableRowGroup4().setRendered(false);
			getBeanOrdenCompra().getLblHistoricidad().setRendered(false);
			getBeanOrdenCompra().getPgFirmas().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return OrdenCompraModel.this;
		}
	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			OrdenCompra locOrdenCompra = (OrdenCompra) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().updateOrdenCompra(locOrdenCompra);
			return "La orden de compra se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanOrdenCompra().getTable3().setRendered(false);
			getBeanOrdenCompra().getLblFirmas().setRendered(false);
			getBeanOrdenCompra().getPgFinalizarComo().setRendered(false);
			getBeanOrdenCompra().getPgTransferirOrdenCompra().setRendered(false);
			getBeanOrdenCompra().getLblComentarioTransferencia().setRendered(false);
			getBeanOrdenCompra().getTaComentarioTransferencia().setRendered(false);

			getBeanOrdenCompra().getTableRowGroup4().setRendered(false);
			getBeanOrdenCompra().getLblHistoricidad().setRendered(false);
			getBeanOrdenCompra().getPgFirmas().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return OrdenCompraModel.this;
		}
	}

	public class TransferirController implements ABMController {

		@Override
		public Validador getValidador() {
			Validador v = new Validador();
			UIComponent[] noVacios = new UIComponent[1];
			String[] nomNoVacios = new String[1];

			int pos = 0;
			noVacios[pos] = getBeanOrdenCompra().getTfNuevoProveedor();
			nomNoVacios[pos++] = "Nuevo Proveedor";

			if (getBeanOrdenCompra().getTfNuevoProveedor().getText() == null || getBeanOrdenCompra().getTfNuevoProveedor().getText().equals("")) {
				v.getErrores().add("Debe seleccionar un nuevo Proveedor para Transferir la Orden de Compra");
			}

			return v;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			OrdenCompra locOrdenCompra = (OrdenCompra) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().transferirOrdenCompra(locOrdenCompra,
					(Proveedor) getBeanOrdenCompra().obtenerObjetoDelElementoPila(6, Proveedor.class),
					(String) getBeanOrdenCompra().getTaComentarioTransferencia().getText());
			return "La Orden de Compra se transfiri\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanOrdenCompra().getPgFinalizarComo().setRendered(false);
			getBeanOrdenCompra().getTableRowGroup4().setRendered(false);
			getBeanOrdenCompra().getLblHistoricidad().setRendered(false);
			getBeanOrdenCompra().getPgFirmas().setRendered(false);
			ocultarConsultarFinalizarEnVista();
		}

		@Override
		public boolean guardaEstadoObjetosUsados() {
			return false;
		}

		@Override
		public boolean mostrarBotonAceptar() {
			return true;
		}

		@Override
		public boolean mostrarBotonCancelar() {
			return true;
		}

		@Override
		public boolean mostrarStSeparador() {
			return true;
		}

		@Override
		public boolean recargarPaginaAdmin() {
			return true;
		}

		@Override
		public String getTextoBotonAceptar() {
			return "Transferir";
		}

		@Override
		public String getTextoBotonCancelar() {
			return "Cancelar";
		}

		@Override
		public String getTituloPagina() {
			return "Transferir";
		}

		@Override
		public String getCodigoColores() {
			return "236, 236, 242";
		}

		@Override
		public ABMModel getModel() {
			return OrdenCompraModel.this;
		}

		@Override
		public boolean seleccionarObjeto() {
			return true;
		}
	}

	public class FinalizarController implements ABMController {

		@Override
		public Validador getValidador() {

			Validador v = new Validador();
			UIComponent[] noVacios = new UIComponent[1];
			String[] nomNoVacios = new String[1];

			int pos = 0;
			noVacios[pos] = getBeanOrdenCompra().getDdFinalizarComo();
			nomNoVacios[pos++] = "Finalizar como";

			v.noSonVacios(noVacios, nomNoVacios);

			return v;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {

			OrdenCompra locOrdenCompra = (OrdenCompra) pObject;
			Object comentario = getBeanOrdenCompra().getTaComentario().getText();
			Object estado = getBeanOrdenCompra().getDdFinalizarComo().getSelected();

			if (comentario != null && comentario != "") {
				locOrdenCompra.setDescripcion(comentario.toString());
			} else {
				locOrdenCompra.setDescripcion(null);
			}

			locOrdenCompra.setEstado(OrdenCompra.Estado.valueOf(estado.toString()));

			getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().finalizarOrdenCompra(locOrdenCompra);
			return "La orden de compra se finaliz\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ocultarConsultarFinalizarEnVista();
			getBeanOrdenCompra().getPgTransferirOrdenCompra().setRendered(false);
			getBeanOrdenCompra().getTaComentarioTransferencia().setRendered(false);
			getBeanOrdenCompra().getPgFirmas().setRendered(false);
		}

		@Override
		public boolean guardaEstadoObjetosUsados() {
			return false;
		}

		@Override
		public boolean mostrarBotonAceptar() {
			return true;
		}

		@Override
		public boolean mostrarBotonCancelar() {
			return true;
		}

		@Override
		public boolean mostrarStSeparador() {
			return true;
		}

		@Override
		public boolean recargarPaginaAdmin() {
			return true;
		}

		@Override
		public String getTextoBotonAceptar() {
			return "Finalizar";
		}

		@Override
		public String getTextoBotonCancelar() {
			return "Cancelar";
		}

		@Override
		public String getTituloPagina() {
			return "Finalizar";
		}

		@Override
		public String getCodigoColores() {
			return Constantes.COLORES_ELIMINAR;
		}

		@Override
		public boolean seleccionarObjeto() {
			return true;
		}

		@Override
		public ABMModel getModel() {
			return OrdenCompraModel.this;
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
			ocultarConsultarFinalizarEnVista();
			getBeanOrdenCompra().getPgFinalizarComo().setRendered(false);
			getBeanOrdenCompra().getPgTransferirOrdenCompra().setRendered(false);
			getBeanOrdenCompra().getPgFirmas().setRendered(true);
		}

		@Override
		public ABMModel getModel() {
			return OrdenCompraModel.this;
		}
	}

	public class GenerarPagosController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			Validador v = new Validador();
			OrdenCompra locOrden = (OrdenCompra) getBeanOrdenCompra().getElementoPila().getObjetos().get(0);
			for (PagoOrdenCompra cadaPago : locOrden.getListaPagosOrdenCompra()) {
				if (cadaPago.getNombre() == null || cadaPago.getNombre().trim().isEmpty() || cadaPago.getMonto() == null || cadaPago.getMonto() <= 0D) {
					v.getErrores().add("Los Pagos no pueden tener nombres o montos vacios o menores a cero.");
					break;
				}
			}
			return v;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			OrdenCompra locOrdenCompra = (OrdenCompra) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().updateOrdenCompra(locOrdenCompra);
			return "Pagos generados exitosamente.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ocultarConsultarFinalizarEnVista();
			getBeanOrdenCompra().getPgFinalizarComo().setRendered(false);
			getBeanOrdenCompra().getPgTransferirOrdenCompra().setRendered(false);
			getBeanOrdenCompra().getPgHistoricidadTransferencias().setRendered(false);
			getBeanOrdenCompra().getTfPrecioUnitario().setDisabled(false);

			getBeanOrdenCompra().getPgPagos().setRendered(true);
			getBeanOrdenCompra().getGroupPanelPagos().setRendered(true);
			getBeanOrdenCompra().getTableColumn1Pagos().setRendered(true);
			getBeanOrdenCompra().getTfNombrePago().setDisabled(false);
			getBeanOrdenCompra().getTfNombrePago().setStyleClass(null);
			getBeanOrdenCompra().getTfMontoPago().setDisabled(false);
			getBeanOrdenCompra().getTfMontoPago().setStyleClass(null);
			getBeanOrdenCompra().getPgFirmas().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return OrdenCompraModel.this;
		}

		@Override
		public boolean seleccionarObjeto() {
			return true;
		}
		
		@Override
		public String getTituloPagina() {
			return "Modificar Pagos";
		}
	}

	public class FirmarController extends FirmarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			OrdenCompra locOrdenCompra = (OrdenCompra) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().firmarOrdenCompra(locOrdenCompra);
			return "La Orden de Compra se firm\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ocultarConsultarFinalizarEnVista();
			getBeanOrdenCompra().getTableRowGroup4().setRendered(false);
			getBeanOrdenCompra().getLblHistoricidad().setRendered(false);
			getBeanOrdenCompra().getPgFinalizarComo().setRendered(false);
			getBeanOrdenCompra().getPgTransferirOrdenCompra().setRendered(false);
			getBeanOrdenCompra().getPgFirmas().setRendered(true);
		}

		@Override
		public boolean guardaEstadoObjetosUsados() {
			return false;
		}

		@Override
		public boolean seleccionarObjeto() {
			return true;
		}

		@Override
		public ABMModel getModel() {
			return OrdenCompraModel.this;
		}
	}
}
