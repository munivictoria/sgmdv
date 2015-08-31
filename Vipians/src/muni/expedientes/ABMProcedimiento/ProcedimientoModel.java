/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.ABMProcedimiento;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;

import com.trascender.expedientes.recurso.filtro.FiltroExpediente;
import com.trascender.expedientes.recurso.persistent.Expediente;
import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class ProcedimientoModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMProcedimiento";
	}

	@Override
	public String getNombreEntidad() {
		return "Procedimiento";
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();

		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		UIComponent[] esNUmero = new UIComponent[1];
		String[] nomEsNumero = new String[1];
		int pos = 0;
		int pos2 = 0;
		noVacios[pos] = getBeanProcedimiento().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanProcedimiento().getPanelPlazo().getTfCantidadDias();
		nomNoVacios[pos] = "Cantidad de D\355as";
		esNUmero[pos2] = getBeanProcedimiento().getPanelPlazo().getTfCantidadDias();
		nomEsNumero[pos2] = "Cantidad de D\355as";

		if(getBeanProcedimiento().getPanelResponsable().emptyLists()) {
			// FacesContext context = FacesContext.getCurrentInstance();
			// String mensaje = " Debe asignar responsables al Procedimiento";
			// context.addMessage(getBeanProcedimiento().getPanelResponsable().gethResponsable().getClientId(context), new
			// FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje));
			v.getErrores().add("Debe asignar responsables al Procedimiento.");
		}

		v.noSonVacios(noVacios, nomNoVacios);
		v.esNumero(esNUmero, nomEsNumero);

		Procedimiento locProcedimiento = (Procedimiento) getBeanProcedimiento().obtenerObjetoDelElementoPila(0);

		String mensaje = validarDiasFasePorTramite(locProcedimiento);
		if(mensaje != null) {
			v.getErrores().add(mensaje);
		}

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMProcedimiento abmProcedimiento = getBeanProcedimiento();

		abmProcedimiento.getTfNombre().setDisabled(true);
		abmProcedimiento.getDdNumerador().setDisabled(true);

		// RESPONSABLES
		abmProcedimiento.getPanelResponsable().getTableAreas().getBtnAgregar().setRendered(false);
		abmProcedimiento.getPanelResponsable().getTableAreas().getBtnQuitar().setRendered(false);
		abmProcedimiento.getPanelResponsable().getTableAreas().getBtnQuitarTodos().setRendered(false);

		abmProcedimiento.getPanelResponsable().getTableUsuarios().getBtnAgregar().setRendered(false);
		abmProcedimiento.getPanelResponsable().getTableUsuarios().getBtnQuitar().setRendered(false);
		abmProcedimiento.getPanelResponsable().getTableUsuarios().getBtnQuitarTodos().setRendered(false);

		abmProcedimiento.getPanelResponsable().getTableUsuarios().getTableColumn1().setRendered(false);
		abmProcedimiento.getPanelResponsable().getTableUsuarios().getGroupPanel1().setRendered(false);
		abmProcedimiento.getPanelResponsable().getTableAreas().getTableColumn1().setRendered(false);
		abmProcedimiento.getPanelResponsable().getTableAreas().getGroupPanel1().setRendered(false);

		// PLAZO
		abmProcedimiento.getPanelPlazo().getTfCantidadDias().setDisabled(true);
		abmProcedimiento.getPanelPlazo().getChDiasCorridos().setDisabled(true);
		abmProcedimiento.getPanelPlazo().getTfCantidadExtensiones().setDisabled(true);

		// ESTRUCTURA PROCEDIMIENTO
		abmProcedimiento.getTrProcedimiento().getBtnAgregarFase().setRendered(false);
		abmProcedimiento.getTrProcedimiento().getBtnAgregarTramite().setRendered(false);
		abmProcedimiento.getTrProcedimiento().getBtnAgregarDocumento().setRendered(false);
		abmProcedimiento.getTrProcedimiento().getBtnQuitarElemento().setRendered(false);
		abmProcedimiento.getTrProcedimiento().getStaticText1().setRendered(false);
		abmProcedimiento.getTrProcedimiento().getBtnAgregarFaseEspecial().setRendered(false);

		// EDIT NODO
		abmProcedimiento.getPanelEditNodo().getPanelPlazo().getTfCantidadDias().setDisabled(true);
		abmProcedimiento.getPanelEditNodo().getPanelPlazo().getChDiasCorridos().setDisabled(true);

		abmProcedimiento.setRenderPanelOrden(false);
		abmProcedimiento.getPanelEditNodo().getGpAreas1().setRendered(false);
		abmProcedimiento.getPanelEditNodo().getGpUsuarios1().setRendered(false);
		abmProcedimiento.getPanelEditNodo().getTcAreas1().setRendered(false);
		abmProcedimiento.getPanelEditNodo().getDdResponsabilidadArea().setDisabled(true);
		abmProcedimiento.getPanelEditNodo().getPanelResponsable().getTableUsuarios().getTableColumn1().setRendered(false);
		abmProcedimiento.getPanelEditNodo().getDdResponsabilidadUsuario().setDisabled(true);
		abmProcedimiento.getDdResponsabilidadArea().setDisabled(true);
		abmProcedimiento.getDdResponsabilidadUsuario().setDisabled(true);

		abmProcedimiento.getPanelEditNodo().getPanelResponsable().getTableUsuariosExtensores().getTableColumn1().setRendered(false);
		abmProcedimiento.getPanelEditNodo().getPanelResponsable().getTableUsuariosExtensores().getTfCantidadDiasMaximo().setDisabled(true);
		abmProcedimiento.getPanelEditNodo().getPanelResponsable().getTableUsuariosExtensores().getGroupPanel1().setRendered(false);
	}

	private ABMProcedimiento getBeanProcedimiento() {
		return (ABMProcedimiento) getRequestBean("expedientes$ABMProcedimiento$ABMProcedimiento");
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Procedimiento locProcedimiento = (Procedimiento) pObject;
			getCommunicationExpedientesBean().getRemoteSystemProcedimientos().setLlave(getSessionBean1().getLlave());
			locProcedimiento = getCommunicationExpedientesBean().getRemoteSystemProcedimientos().addProcedimiento(locProcedimiento);

			getCommunicationExpedientesBean().getMapaProcedimientoExpediente().put(locProcedimiento.getNombre(), locProcedimiento);

			return "El Procedimiento se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ProcedimientoModel.this;
		}

	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Procedimiento locProcedimiento = (Procedimiento) pObject;
			String nombreProcedimiento = locProcedimiento.getNombre();
			
			getCommunicationExpedientesBean().getRemoteSystemProcedimientos().setLlave(getSessionBean1().getLlave());
			locProcedimiento = getCommunicationExpedientesBean().getRemoteSystemProcedimientos().updateProcedimiento(locProcedimiento);

			getCommunicationExpedientesBean().getMapaProcedimientoExpediente().remove(nombreProcedimiento);
			getCommunicationExpedientesBean().getMapaProcedimientoExpediente().put(locProcedimiento.getNombre(), locProcedimiento);
			
			return "El Procedimiento se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			FiltroExpediente locFiltro = new FiltroExpediente();
			locFiltro.setProcedimiento(getBeanProcedimiento().procedimiento);
			List<Expediente> listaE = new ArrayList<Expediente>();
			try {
				listaE = getCommunicationExpedientesBean().getRemoteSystemExpedientes().findListaExpediente(locFiltro).getListaResultados();
			} catch(RemoteException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
			if(!listaE.isEmpty()) {
				getBeanProcedimiento().getTrProcedimiento().getBtnQuitarElemento().setRendered(true);
				getBeanProcedimiento().setRenderPanelOrden(true);
			}
		}

		@Override
		public ABMModel getModel() {
			return ProcedimientoModel.this;
		}

	}

	public class ConsultarControler extends ConsultarAbstractController {

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
			return ProcedimientoModel.this;
		}

	}

	public class EliminarControler extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Procedimiento locProcedimiento = (Procedimiento) pObject;
			getCommunicationExpedientesBean().getRemoteSystemProcedimientos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemProcedimientos().deleteProcedimiento(locProcedimiento);

			getCommunicationExpedientesBean().getMapaProcedimientoExpediente().remove(locProcedimiento.getNombre());
			
			return "El Procedimiento se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return ProcedimientoModel.this;
		}

	}

	public String validarDiasFasePorTramite(Procedimiento pProcedimiento) {
		int cantidadDiasFase = 0;
		String mensaje = null;

		for(FaseProcedimiento cadaFase : pProcedimiento.getListaFasesProcedimiento()) {
			if(cadaFase.getPlazo() != null) {
				cantidadDiasFase = cantidadDiasFase + cadaFase.getPlazo().getDias();
				mensaje = validarDiasTramitesPorFase(cadaFase);
				if(mensaje != null) {
					return mensaje;
				}
			}
		}
		if(pProcedimiento.getPlazo().getDias() != null && cantidadDiasFase > pProcedimiento.getPlazo().getDias()) {
			mensaje = "La Suma de los dias de las Fases es mayor a el Plazo del Procedimiento.";
		}

		return mensaje;
	}

	public String validarDiasTramitesPorFase(FaseProcedimiento pFaseProcedimiento) {
		int cantidadDiasTramite = 0;

		for(TramiteProcedimiento cadaTramite : pFaseProcedimiento.getListaTramitesProcedimientos()) {
			if(cadaTramite.getPlazo() != null) {
				cantidadDiasTramite = cantidadDiasTramite + cadaTramite.getPlazo().getDias();
			}
		}
		if(cantidadDiasTramite > pFaseProcedimiento.getPlazo().getDias()) {
			return "La Suma de los dias de los Tramites es mayor a el Plazo de la Fase " + pFaseProcedimiento.getFaseCatalogo().getNombre();
		}

		return null;
	}

}