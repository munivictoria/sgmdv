
package muni.expedientes.ABMExpediente;

import java.util.Map;

import javax.faces.component.UIComponent;

import muni.expedientes.NodoExpedienteControler;

import com.trascender.expedientes.recurso.persistent.Expediente;
import com.trascender.expedientes.recurso.persistent.NodoExpediente;
import com.trascender.expedientes.recurso.persistent.Responsable;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class ExpedienteModel extends ABMModel {

	private Usuario usuario = getSessionBean1().getUsuario();

	@Override
	public String getReglaNavegacion() {
		return "ABMExpediente";
	}

	@Override
	public String getNombreEntidad() {
		return "Expediente";
	}

	private boolean permisoParaModificar(NodoExpediente pNodoExpediente) {
		boolean retorno = false;

		Map<NodoExpediente, Boolean[]> mapPermisos = pNodoExpediente.getMapPermisos(usuario);
		for(NodoExpediente key : mapPermisos.keySet()) {
			Boolean[] booleans = mapPermisos.get(key);
			for(int i = 0; i < booleans.length; i++) {
				if(booleans[i] != null && booleans[i]) {
					retorno = true;
					break;
				}
			}
		}
		return retorno;
	}

	private boolean permisoConsultar(NodoExpediente pNodoExpediente) {
		boolean retorno = false;
		Map<NodoExpediente, Boolean[]> mapPermisos = pNodoExpediente.getMapPermisos(usuario);
		for(NodoExpediente key : mapPermisos.keySet()) {
			Boolean[] booleans = mapPermisos.get(key);
			for(int i = 0; i < booleans.length; i++) {
				if(booleans[i] != null) {
					retorno = true;
					break;
				}
			}
		}
		return retorno;
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[4];
		String[] nomNoVacios = new String[4];
		int pos = 0;
		noVacios[pos] = getBeanExpediente().getTfPersona();
		nomNoVacios[pos++] = "Interesado";
		noVacios[pos] = getBeanExpediente().getTaAsunto();
		nomNoVacios[pos++] = "Asunto";
		noVacios[pos] = getBeanExpediente().getTfNroRegistro();
		nomNoVacios[pos++] = "Nro Registro";
		noVacios[pos] = getBeanExpediente().getPfContraseniaExpediente();
		nomNoVacios[pos++] = "Contraseña";
		v.noSonVacios(noVacios, nomNoVacios);

		Expediente locExpediente = (Expediente) this.getBeanExpediente().obtenerObjetoDelElementoPila(0, Expediente.class);
		if(locExpediente.getInteresado().getTelefono() == null && locExpediente.getInteresado().getCelular() == null) {
			v.getErrores().add("El interesado no tiene teléfono ni celular de contacto.");
		}
		
		String locPass = this.getBeanExpediente().obtenerObjetoDelElementoPila(4, String.class);
		if(locPass == null || !locPass.equals(this.getSessionBean1().getUsuario().getPassword())) {
			v.getErrores().add("Contraseña de usuario no válida.");
		}
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMExpediente abmExpediente = getBeanExpediente();

		abmExpediente.getDdProcedimiento().setRendered(false);
		abmExpediente.getBtnSeleccionarProcedimiento().setRendered(false);
		abmExpediente.getBtnSeleccionarPersonaFisica().setRendered(false);
		abmExpediente.getBtnSeleccionarPersonaJuridica().setRendered(false);
		abmExpediente.getBtnLimpiarPersona().setRendered(false);
		abmExpediente.getTfNroTelefono().setDisabled(true);
		abmExpediente.getTfNroCelular().setDisabled(true);
		abmExpediente.getTfEmail().setDisabled(true);

		abmExpediente.getPanelFases().getLbFasesEspeciales().setRendered(false);
		abmExpediente.getPanelFases().getDdFasesEspeciales().setRendered(false);
		abmExpediente.getPanelFases().getBtnIrAFaseEspecial().setRendered(false);
		abmExpediente.getPanelFases().getStaticText1().setRendered(false);
		abmExpediente.getPanelFases().getBtnAvanzarFase().setRendered(false);
		abmExpediente.getPanelFases().getBtnRetrocederFase().setRendered(false);
		abmExpediente.getPanelFases().getBtnCerrarExpediente().setRendered(false);
		abmExpediente.getPanelFases().getBtnCancelarAvanceFase().setRendered(false);
		abmExpediente.getBtnAgregarExtensionFase().setRendered(false);
		abmExpediente.getBtnAgregarExtensionTramite().setRendered(false);
		abmExpediente.getPfContraseniaExpediente().setRendered(false);
		abmExpediente.getLbContraseniaExpediente().setRendered(false);

		abmExpediente.getPgBotonesFases().setRendered(false);
		
		abmExpediente.getPanelFases().getTableTramite().getBtnModificarTramite().setRendered(false);
		abmExpediente.getTaAsunto().setDisabled(true);
	}

	private void deshabilitarElementosModificar() {
		ABMExpediente abmExpediente = getBeanExpediente();
		abmExpediente.getElementosPila();
		Responsable r = abmExpediente.expediente.getNodoProcedimiento().getResponsable();
		abmExpediente.getBtnSeleccionarProcedimiento().setRendered(false);
		abmExpediente.getDdProcedimiento().setRendered(false);

		if(r != null && r.soyResponsable(usuario) != null && !r.soyResponsable(usuario)) {
			abmExpediente.getBtnSeleccionarPersonaFisica().setRendered(false);
			abmExpediente.getBtnSeleccionarPersonaJuridica().setRendered(false);
			abmExpediente.getBtnLimpiarPersona().setRendered(false);
			abmExpediente.getTfPersona().setDisabled(true);
			abmExpediente.getTaAsunto().setDisabled(true);
			abmExpediente.getTfFechaRegistro().setDisabled(true);
			abmExpediente.getTfNroRegistro().setDisabled(true);
			abmExpediente.getPanelFases().getBtnAvanzarFase().setDisabled(true);
			abmExpediente.getPanelFases().getBtnCerrarExpediente().setDisabled(true);
		}
		Expediente locExpediente = (Expediente) this.getBeanExpediente().obtenerObjetoDelElementoPila(0, Expediente.class);
		if(!locExpediente.isEnPrimerFase()){
			abmExpediente.getTfNroTelefono().setDisabled(true);
			abmExpediente.getTfNroCelular().setDisabled(true);
			abmExpediente.getTfEmail().setDisabled(true);
			abmExpediente.getBtnSeleccionarPersonaFisica().setRendered(false);
			abmExpediente.getBtnSeleccionarPersonaJuridica().setRendered(false);
			abmExpediente.getBtnLimpiarPersona().setRendered(false);
		}
	}

	private ABMExpediente getBeanExpediente() {
		return (ABMExpediente) getRequestBean("expedientes$ABMExpediente$ABMExpediente");
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Expediente locExpediente = (Expediente) pObject;
			String locComentario = (String) getBeanExpediente().getElementoPila().getObjetos().get(3);
			getCommunicationExpedientesBean().getRemoteSystemExpedientes().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemExpedientes().addExpediente(locExpediente, locComentario, usuario);
			return "El Expediente se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ABMExpediente abmExpediente = getBeanExpediente();
			abmExpediente.getTfNroRegistro().setText("Asignado al guardar");
		}

		@Override
		public ABMModel getModel() {
			return ExpedienteModel.this;
		}
	}

	public class ModificarController extends ModificarAbstractController implements NodoExpedienteControler {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Expediente locExpediente = (Expediente) pObject;
			String locComentario = (String) getBeanExpediente().getElementoPila().getObjetos().get(3);
			getCommunicationExpedientesBean().getRemoteSystemExpedientes().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemExpedientes().updateExpediente(locExpediente, locComentario, usuario);
			return "El Expediente se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosModificar();
		}

		@Override
		public ABMModel getModel() {
			return ExpedienteModel.this;
		}

		@Override
		public void getValoresPorDefecto(NodoExpediente pNodoExpediente) {
		}

		@Override
		public boolean getValidacion(NodoExpediente pNodoExpediente) {
			return permisoParaModificar(pNodoExpediente);
		}

		@Override
		public String getMessage() {
			return "Debe ser un Usuario o pertenecer a un Area responsable de este Expediente para poder modificarlo";
		}
	}

	public class ConsultarControler extends ConsultarAbstractController implements NodoExpedienteControler {

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
			return ExpedienteModel.this;
		}

		@Override
		public void getValoresPorDefecto(NodoExpediente pNodoExpediente) {
		}

		@Override
		public boolean getValidacion(NodoExpediente pNodoExpediente) {
			return permisoConsultar(pNodoExpediente);
		}

		@Override
		public String getMessage() {
			return "Debe ser un Usuario o pertenecer a un Area responsable o supervisora";
		}
	}

	public class EliminarControler extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Expediente locExpediente = (Expediente) pObject;
			getCommunicationExpedientesBean().getRemoteSystemExpedientes().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemExpedientes().deleteExpediente(locExpediente, usuario);
			return "El Expediente se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return ExpedienteModel.this;
		}
	}
}