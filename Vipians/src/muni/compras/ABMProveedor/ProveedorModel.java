package muni.compras.ABMProveedor;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ActivarAbstractController;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class ProveedorModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMProveedor";
	}

	@Override
	public String getNombreEntidad() {
		return "Proveedor";
	}

	private ABMProveedor getBeanProveedor() {
		return (ABMProveedor) getRequestBean("compras$ABMProveedor$ABMProveedor");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];

		int pos = 0;
		noVacios[pos] = getBeanProveedor().getTfProveedorLocal();
		nomNoVacios[pos++] = "Razón Social";

		v.noSonVacios(noVacios, nomNoVacios);

		Domicilio domicilio = (Domicilio) getBeanProveedor().obtenerObjetoDelElementoPila(2, Domicilio.class);
		if (domicilio.getLocalidad() == null) {
			String msg = "Debe seleccionar un Domicilio Fiscal";
			v.getErrores().add(msg);
		}

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		getBeanProveedor().getTfContacto().setDisabled(true);
		getBeanProveedor().getTfCodigo().setDisabled(true);
		getBeanProveedor().getTfEmail().setDisabled(true);
		getBeanProveedor().getTfProveedorLocal().setDisabled(true);
		getBeanProveedor().getTfTelefono().setDisabled(true);
		getBeanProveedor().getDdTipo().setDisabled(true);
		getBeanProveedor().getBtnSeleccionarPersonaFisica().setRendered(false);
		getBeanProveedor().getBtnSeleccionarPersonaJuridica().setRendered(false);
		getBeanProveedor().getBtnSeleccionarDomicilioProveedor().setRendered(false);
		getBeanProveedor().getBtnSeleccionarDomicilio().setRendered(false);
		getBeanProveedor().getStSeparador1().setRendered(false);
		getBeanProveedor().getTableColumn2().setRendered(false);
		getBeanProveedor().getGroupPanel1().setRendered(false);
		getBeanProveedor().getGroupPanel2().setRendered(false);
		getBeanProveedor().getTableColumn3().setRendered(false);
		getBeanProveedor().getGroupPanel3().setRendered(false);
		getBeanProveedor().getPanelAtributoDinamico().deshabilitarCampos();
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Proveedor locProveedor = (Proveedor) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().addProveedor(locProveedor);
			return "El Proveedor se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ProveedorModel.this;
		}
	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Proveedor locProveedor = (Proveedor) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().updateProveedor(locProveedor);
			return "El Proveedor se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}
		
		@Override
		public ABMModel getModel() {
			return ProveedorModel.this;
		}
	}

	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Proveedor locProveedor = (Proveedor) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().deleteProveedor(locProveedor);
			return "El Proveedor se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}
		
		@Override
		public ABMModel getModel() {
			return ProveedorModel.this;
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
			getBeanProveedor().getTaComentarioLogAuditoria().setRendered(false);
    		getBeanProveedor().getLblComentarioLogAuditoria().setRendered(false);
		}
		
		@Override
		public ABMModel getModel() {
			return ProveedorModel.this;
		}
	}

	public class ActivarController extends ActivarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Proveedor locProveedor = (Proveedor) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionProveedores().restoreProveedor(locProveedor);
			return "El Proovedor se Recupero exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}
		
		@Override
		public ABMModel getModel() {
			return ProveedorModel.this;
		}

	}
}
