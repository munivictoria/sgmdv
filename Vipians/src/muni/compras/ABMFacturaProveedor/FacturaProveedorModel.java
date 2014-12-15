/*

 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMFacturaProveedor;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.suministros.FacturaProveedor;
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
public class FacturaProveedorModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMFacturaProveedor";
	}

	@Override
	public String getNombreEntidad() {
		return "Factura Proveedor";
	}

	private ABMFacturaProveedor getBeanFacturaProveedor() {
		return (ABMFacturaProveedor) getRequestBean("compras$ABMFacturaProveedor$ABMFacturaProveedor");
	}

	private Validador getValidadorAgregarModificar() {

		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[4];
		String[] nomNoVacios = new String[4];

		int pos = 0;
		noVacios[pos] = getBeanFacturaProveedor().getTfFechaEmision();
		nomNoVacios[pos++] = "Fecha";
		noVacios[pos] = getBeanFacturaProveedor().getTfProveedor();
		nomNoVacios[pos++] = "Proveedor";
		noVacios[pos] = getBeanFacturaProveedor().getDdTipoFactura();
		nomNoVacios[pos++] = "Tipo de Factura";
		noVacios[pos] = getBeanFacturaProveedor().getTfNumeroFactura();
		nomNoVacios[pos++] = "Nº de Factura";

		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {

		getBeanFacturaProveedor().getTfCantidad().setDisabled(true);
		getBeanFacturaProveedor().getTfCuentaRfr().setDisabled(true);
		getBeanFacturaProveedor().getTfNumeroFactura().setDisabled(true);
		getBeanFacturaProveedor().getTfFechaEmision().setDisabled(true);
		getBeanFacturaProveedor().getTfNumeroFactura().setDisabled(true);
		getBeanFacturaProveedor().getTfProveedor().setDisabled(true);
		getBeanFacturaProveedor().getTfTotal().setDisabled(true);
		getBeanFacturaProveedor().getTfPrecio().setDisabled(true);
		getBeanFacturaProveedor().getTfCantidad().setDisabled(true);
		getBeanFacturaProveedor().getDdTipoFactura().setDisabled(true);
		getBeanFacturaProveedor().getGroupPanel1().setRendered(false);
		getBeanFacturaProveedor().getBtnSeleccionarProveedor().setRendered(false);
		getBeanFacturaProveedor().getTableColumn1().setRendered(false);

		getBeanFacturaProveedor().getDdTipoFactura().setDisabled(true);

		getBeanFacturaProveedor().getBtnAgregarLinea().setRendered(false);
		getBeanFacturaProveedor().getBtnAgregarOrden().setRendered(false);
		getBeanFacturaProveedor().getBtnQuitar().setRendered(false);
		getBeanFacturaProveedor().getBtnQuitarTodos().setRendered(false);
		getBeanFacturaProveedor().getStSeparador2().setRendered(false);
		getBeanFacturaProveedor().getStSeparador3().setRendered(false);
		getBeanFacturaProveedor().getTableColumn1().setRendered(false);
		getBeanFacturaProveedor().getTaComentarioLogAuditoria().setDisabled(true);
		getBeanFacturaProveedor().getPanelAtributoDinamico().deshabilitarCampos();
		
		getBeanFacturaProveedor().getBtnSeleccionarProveedor().setRendered(false);
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			FacturaProveedor locFacturaProveedor = (FacturaProveedor) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionFacturaProveedor().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionFacturaProveedor().addFacturaProveedor(locFacturaProveedor);
			return "La factura proveedor se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return FacturaProveedorModel.this;
		}
	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			FacturaProveedor locFacturaProveedor = (FacturaProveedor) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionFacturaProveedor().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionFacturaProveedor().updateFacturaProveedor(locFacturaProveedor);
			return "La factura proveedor se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return FacturaProveedorModel.this;
		}
	}

	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			FacturaProveedor locFacturaProveedor = (FacturaProveedor) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionFacturaProveedor().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionFacturaProveedor().deleteFacturaProveedor(locFacturaProveedor);
			return "La factura proveedor se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return FacturaProveedorModel.this;
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
			getBeanFacturaProveedor().getTaComentarioLogAuditoria().setRendered(false);
			getBeanFacturaProveedor().getLblComentarioLogAuditoria().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return FacturaProveedorModel.this;
		}
	}
}