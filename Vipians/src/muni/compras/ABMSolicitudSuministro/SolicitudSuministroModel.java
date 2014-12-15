package muni.compras.ABMSolicitudSuministro;

import java.rmi.RemoteException;
import java.util.List;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.suministros.EstadoSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.compras.recurso.persistent.suministros.SolicitudSuministro;
import com.trascender.framework.recurso.persistent.Permiso.Accion;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.presentacion.abstracts.controller.ABMController;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.FirmarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

public class SolicitudSuministroModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMSolicitudSuministro";
	}

	@Override
	public String getNombreEntidad() {
		return "Solicitud de Suministro";
	}

	private ABMSolicitudSuministro getBeanSolicitudSuministro() {
		return (ABMSolicitudSuministro) getRequestBean("compras$ABMSolicitudSuministro$ABMSolicitudSuministro");
	}

	private void validarMostrarBotonConsultarCuenta() {
		try {
			if(!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), SolicitudSuministro.serialVersionUID, Accion.AUDITH)) {
				getBeanSolicitudSuministro().getStSeparador1().setRendered(false);
				getBeanSolicitudSuministro().getBtnCambiarCuenta().setRendered(false);
			}
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];

		int pos = 0;
		noVacios[pos] = getBeanSolicitudSuministro().getDdArea();
		nomNoVacios[pos++] = "Área";

		v.noSonVacios(noVacios, nomNoVacios);

		List listaLineasSolicitud = getBeanSolicitudSuministro().getListaDelCommunication();

		if (listaLineasSolicitud == null || listaLineasSolicitud.isEmpty()) {
			String msg = "Debe agregar al menos una L\355nea de Solicitud de Suministro.";
			v.getErrores().add(msg);
		} else {
			for (Object cadaObject : listaLineasSolicitud) {
				LineaSolicitudSuministro cadaLinea = (LineaSolicitudSuministro) cadaObject;
				if (cadaLinea.getCantidad() == 0) {
					v.getErrores().add("La cantidad solicitada debe ser mayor a 0.");
					break;
				}
			}
		}

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		getBeanSolicitudSuministro().getTfFechaEmision().setDisabled(true);
		getBeanSolicitudSuministro().getTfUsuario().setDisabled(true);
		getBeanSolicitudSuministro().getLblLineaSolSuministro().setRendered(false);
		getBeanSolicitudSuministro().getDdArea().setDisabled(true);
		getBeanSolicitudSuministro().getTfCantidad().setDisabled(true);
		getBeanSolicitudSuministro().getTfEstado().setDisabled(true);
		getBeanSolicitudSuministro().getBtnSeleccionarBien().setRendered(false);
		getBeanSolicitudSuministro().getTableColumn1().setRendered(false);
		getBeanSolicitudSuministro().getGroupPanel1().setRendered(false);
		getBeanSolicitudSuministro().getGpLineas().setRendered(false);
		getBeanSolicitudSuministro().getTaDescripcion().setDisabled(true);
		getBeanSolicitudSuministro().getDdFinalizarComo().setDisabled(true);
		getBeanSolicitudSuministro().getTaComentario().setDisabled(true);
		getBeanSolicitudSuministro().getTfNumero().setDisabled(true);
		getBeanSolicitudSuministro().getBtnAgregarPresupuestoSolSum().setRendered(false);
		getBeanSolicitudSuministro().getBtnModificarPresupuestoSolSum().setRendered(false);
		getBeanSolicitudSuministro().getBtnQuitarPresupuestoSolSum().setRendered(false);
		getBeanSolicitudSuministro().getBtnQuitarTodosPresupuestoSolSum().setRendered(false);
		getBeanSolicitudSuministro().getStSeparador4().setRendered(false);
		getBeanSolicitudSuministro().getTaComentarioLogAuditoria().setRendered(false);
		getBeanSolicitudSuministro().getLblComentarioLogAuditoria().setRendered(false);
		getBeanSolicitudSuministro().getCbUrgente().setDisabled(true);
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			SolicitudSuministro locSolicitudSuministro = (SolicitudSuministro) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().addSolicitudSuministro(locSolicitudSuministro);
			return "La Solicitud de Suministro se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanSolicitudSuministro().getGpFinalizarComo().setRendered(false);
			getBeanSolicitudSuministro().getTfNumero().setText("Asignado al guardar");
			getBeanSolicitudSuministro().getPgFirmasSolicitud().setRendered(false);
			getBeanSolicitudSuministro().getPgMovimientoMercaderia().setRendered(false);

			validarMostrarBotonConsultarCuenta();
			getBeanSolicitudSuministro().isMostrarCbUrgente();
			getBeanSolicitudSuministro().getCbUrgente().setRendered(false);
			getBeanSolicitudSuministro().getLblUrgente().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return SolicitudSuministroModel.this;
		}
	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			SolicitudSuministro locSolicitudSuministro = (SolicitudSuministro) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().updateSolicitudSuministro(locSolicitudSuministro);
			return "La Solicitud de Suministro se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanSolicitudSuministro().getGpFinalizarComo().setRendered(false);
			getBeanSolicitudSuministro().getPgFirmasSolicitud().setRendered(false);
			getBeanSolicitudSuministro().getPgMovimientoMercaderia().setRendered(false);

			validarMostrarBotonConsultarCuenta();
			if(!getBeanSolicitudSuministro().isMostrarCbUrgente()){
				getBeanSolicitudSuministro().getCbUrgente().setRendered(false);
				getBeanSolicitudSuministro().getLblUrgente().setRendered(false);
			}
				
		}

		@Override
		public ABMModel getModel() {
			return SolicitudSuministroModel.this;
		}
	}

	public class FinalizarController implements ABMController {

		@Override
		public Validador getValidador() {
			Validador v = new Validador();
			UIComponent[] noVacios = new UIComponent[1];
			String[] nomNoVacios = new String[1];

			int pos = 0;
			noVacios[pos] = getBeanSolicitudSuministro().getDdFinalizarComo();
			nomNoVacios[pos++] = "Finalizar como";

			v.noSonVacios(noVacios, nomNoVacios);

			return v;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {

			SolicitudSuministro locSolicitudSuministro = (SolicitudSuministro) pObject;
			Object comentario = getBeanSolicitudSuministro().getTaComentario().getText();
			String stringEstado = getBeanSolicitudSuministro().getDdFinalizarComo().getSelected().toString();

			if (comentario != null && comentario != "") {
				locSolicitudSuministro.setComentarioFinalizacion(comentario.toString());
			} else {
				locSolicitudSuministro.setDescripcion(null);
			}

			EstadoSolicitudSuministro locEstado = getApplicationBean1().getMapaEstadosSolicitudSuministro().get(stringEstado);
			locSolicitudSuministro.setEstado(locEstado);

			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().finalizarSolicitud(locSolicitudSuministro);

			return "La solicitud ha sido finalizada con \351xito";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
			// En el metodo anterior se pusieron disabled, ahora se habilitan
			getBeanSolicitudSuministro().getDdFinalizarComo().setDisabled(false);
			getBeanSolicitudSuministro().getTaComentario().setDisabled(false);
			getBeanSolicitudSuministro().getPgMovimientoMercaderia().setRendered(false);

			validarMostrarBotonConsultarCuenta();
			getBeanSolicitudSuministro().isMostrarCbUrgente();
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
		public ABMModel getModel() {
			return SolicitudSuministroModel.this;
		}

		@Override
		public boolean seleccionarObjeto() {
			return true;
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
			SolicitudSuministro locSolicitud = (SolicitudSuministro) getBeanSolicitudSuministro().getElementoPila().getObjetos().get(0);
			if (!locSolicitud.getEstado().isEstadoFinal()) {
				getBeanSolicitudSuministro().getGpFinalizarComo().setRendered(false);
			} else {
				getBeanSolicitudSuministro().getGpFinalizarComo().setRendered(true);
			}

			validarMostrarBotonConsultarCuenta();
			getBeanSolicitudSuministro().isMostrarCbUrgente();
			
			List<LineaOrdenCompra> locListaLineasOrdenCompra = getBeanSolicitudSuministro().getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().getListaLineaOrdenCompraPorSolicitud(locSolicitud);
			getBeanSolicitudSuministro().setListaDelCommunicationLineaOrdenCompra(locListaLineasOrdenCompra);
			getBeanSolicitudSuministro().getElementoPila().getObjetos().set(6, locListaLineasOrdenCompra);
			getBeanSolicitudSuministro().getObjectListDataProviderLineaOrdenCompra().setList(locListaLineasOrdenCompra);
		}

		@Override
		public ABMModel getModel() {
			return SolicitudSuministroModel.this;
		}
	}

	public class FirmarController extends FirmarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			SolicitudSuministro locSolicitudSuministro = (SolicitudSuministro) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().firmarSolicitudSuminstro(locSolicitudSuministro);
			return "La Solicitud de Suministro se Firm\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
			getBeanSolicitudSuministro().getGpFinalizarComo().setRendered(false);
			getBeanSolicitudSuministro().getPgMovimientoMercaderia().setRendered(false);

			validarMostrarBotonConsultarCuenta();
			getBeanSolicitudSuministro().isMostrarCbUrgente();
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
			return SolicitudSuministroModel.this;
		}
	}

	public class ModificarCuentaController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			SolicitudSuministro locSolicitudSuministro = (SolicitudSuministro) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().updateCuentaRfr(locSolicitudSuministro);
			return "La cuenta de la Solicitud de Suministro se Modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanSolicitudSuministro().getGpFinalizarComo().setRendered(false);
			getBeanSolicitudSuministro().getPgFirmasSolicitud().setRendered(false);
			getBeanSolicitudSuministro().getPgMovimientoMercaderia().setRendered(false);
			getBeanSolicitudSuministro().getDdArea().setDisabled(true);
			getBeanSolicitudSuministro().getTfUsuario().setDisabled(true);
			getBeanSolicitudSuministro().getTfFechaEmision().setDisabled(true);
			getBeanSolicitudSuministro().getTfNumero().setDisabled(true);
			getBeanSolicitudSuministro().getTaDescripcion().setDisabled(true);
			getBeanSolicitudSuministro().getBtnSeleccionarBien().setRendered(false);
			getBeanSolicitudSuministro().getStSeparador1().setRendered(false);
			getBeanSolicitudSuministro().getStSeparador3().setRendered(false);
			getBeanSolicitudSuministro().getBtnQuitar().setRendered(false);
			getBeanSolicitudSuministro().getBtnQuitarTodos().setRendered(false);
			getBeanSolicitudSuministro().getTfCantidad().setDisabled(true);
			getBeanSolicitudSuministro().getTfEstado().setDisabled(true);
			getBeanSolicitudSuministro().getTableColumn3().setRendered(false);
			getBeanSolicitudSuministro().getGroupPanel2().setRendered(false);
			getBeanSolicitudSuministro().getCbUrgente().setDisabled(true);
			getBeanSolicitudSuministro().getTfBien().setRendered(false);
			getBeanSolicitudSuministro().getBtnAgregarBien().setRendered(false);
			getBeanSolicitudSuministro().getBtnLimpiarBien().setRendered(false);
			getBeanSolicitudSuministro().getStSeparador2().setRendered(false);
			
			validarMostrarBotonConsultarCuenta();
			getBeanSolicitudSuministro().isMostrarCbUrgente();
		}

		@Override
		public ABMModel getModel() {
			return SolicitudSuministroModel.this;
		}

	}
	
	public class PresupuestarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			SolicitudSuministro locSolicitudSuministro = (SolicitudSuministro) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionSolicitudSuministro().updateSolicitudSuministro(locSolicitudSuministro);
			return "La cuenta de la Solicitud de Suministro se Modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanSolicitudSuministro().getGroupPanel1().setRendered(false);
			getBeanSolicitudSuministro().getTableColumn1().setRendered(false);
			getBeanSolicitudSuministro().getGpFinalizarComo().setRendered(false);
			getBeanSolicitudSuministro().getPgFirmasSolicitud().setRendered(false);
			getBeanSolicitudSuministro().getPgMovimientoMercaderia().setRendered(false);
			getBeanSolicitudSuministro().getDdArea().setDisabled(true);
			getBeanSolicitudSuministro().getTfUsuario().setDisabled(true);
			getBeanSolicitudSuministro().getTfFechaEmision().setDisabled(true);
			getBeanSolicitudSuministro().getTfNumero().setDisabled(true);
			getBeanSolicitudSuministro().getTaDescripcion().setDisabled(true);
			getBeanSolicitudSuministro().getBtnSeleccionarBien().setRendered(false);
			getBeanSolicitudSuministro().getStSeparador1().setRendered(false);
			getBeanSolicitudSuministro().getStSeparador3().setRendered(false);
			getBeanSolicitudSuministro().getBtnQuitar().setRendered(false);
			getBeanSolicitudSuministro().getBtnQuitarTodos().setRendered(false);
			getBeanSolicitudSuministro().getTfCantidad().setDisabled(true);
			getBeanSolicitudSuministro().getTfEstado().setDisabled(true);
			getBeanSolicitudSuministro().getCbUrgente().setDisabled(true);
			
			validarMostrarBotonConsultarCuenta();
			getBeanSolicitudSuministro().isMostrarCbUrgente();
		}

		@Override
		public ABMModel getModel() {
			return SolicitudSuministroModel.this;
		}
	}
}
