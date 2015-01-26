/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.habilitaciones.grpSHPS.ABMDocEspSHPS;

import java.util.ArrayList;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 *
 * @author fer
 */
public class DocEspSHPSModel extends ABMModel {

	private ABMDocEspSHPS getBeanDocSHPS() {
		return (ABMDocEspSHPS) getRequestBean("habilitaciones$grpSHPS$ABMDocEspSHPS$ABMDocEspSHPS");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();

		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		UIComponent[] fechas = new UIComponent[2];
		String[] nomFechas = new String[2];

		int pos = 0;
		noVacios[pos] = this.getBeanDocSHPS().getTfNumeroInscripcion();
		nomNoVacios[pos++] = "N\372mero de Inscripci\363n";
		noVacios[pos] = this.getBeanDocSHPS().getTfFechaInicio();
		nomNoVacios[pos++] = "Inicio de Actividades";

		pos = 0;
		fechas[pos] = this.getBeanDocSHPS().getTfFechaInicio();
		nomFechas[pos++] = "Inicio de Actividades";
		fechas[pos] = this.getBeanDocSHPS().getTfFechaCese();
		nomFechas[pos++] = "Cese de Actividades";

		v.noSonVacios(noVacios, nomNoVacios);
		v.formatoFechaValido(fechas, nomFechas);

		Persona persona = this.getBeanDocSHPS().obtenerObjetoDelElementoPila(1, Persona.class);
		if(persona == null || persona.getIdPersona() == -1) {
			String msg = "El campo 'Persona' es requerido.";
			v.getErrores().add(msg);
		}

		Domicilio domicilio = this.getBeanDocSHPS().obtenerObjetoDelElementoPila(3, Domicilio.class);
		if(domicilio.getLocalidad() == null) {
			String msg = "Debe seleccionar un Domicilio Postal.";
			v.getErrores().add(msg);
		}

		DocumentoSHPS documentoSHPS = this.getBeanDocSHPS().obtenerObjetoDelElementoPila(0, DocumentoSHPS.class);

		if(documentoSHPS.getRubroPrincipal() == null) {
			String msg = "Debe seleccionar un Rubro principal";
			v.getErrores().add(msg);
		}

		if(!v.fechaNoMayorAFechaActual(documentoSHPS.getFechaInicioActividad(), "Fecha de Inicio de Actividades")) {
			this.getBeanDocSHPS().getTfFechaInicio().setValid(false);
		}

		ArrayList atributosDinamicos = this.getBeanDocSHPS().obtenerObjetoDelElementoPila(9, ArrayList.class);

		Validador v2 = this.getBeanDocSHPS().getPanelAtributoDinamico().validarCampos(atributosDinamicos);
		if(v2.getErrores().size() > 0) {
			for(Object cadaError : v2.getErrores()) {
				v.getErrores().add(cadaError);
			}
		}
		return v;
	}

	private void deshabilitarOcultarElementos() {
		ABMDocEspSHPS locBean = this.getBeanDocSHPS();
		locBean.getTfNumeroInscripcion().setDisabled(true);
		locBean.getTfFechaInicio().setDisabled(true);
		locBean.getTfNombre().setDisabled(true);
		locBean.getTfContador().setDisabled(true);
		locBean.getTfFechaCese().setDisabled(true);
		locBean.getBtnSeleccionarPersonaFisica().setRendered(false);
		locBean.getBtnSeleccionarPersonaJuridica().setRendered(false);
		locBean.getBtnSeleccionarDomicilioPostal().setRendered(false);
		locBean.getBtnSeleccionarDomicilioSolicitante().setRendered(false);
		locBean.getBtnLimpiarDomicilioPostal().setRendered(false);
		locBean.getBtnLimpiarPersona().setRendered(false);
		locBean.getTfDenominacionEntidad().setRendered(false);
		locBean.getBtnLimpiarContador().setRendered(false);
		locBean.getBtnSeleccionarContador().setRendered(false);
		locBean.getBtnSeleccionarPersonaJuridicaContador().setRendered(false);
		locBean.getBtnSeleccionarDomicilioSolicitanteContador().setRendered(false);
		// Tabla rubros
		locBean.getGroupPanel5().setRendered(false);
		locBean.getRadioButton7().setDisabled(true);
		locBean.getTableColumn22().setRendered(false);
		// Tabla locales comerciales
		locBean.getTableColumn1().setRendered(false);
		locBean.getGroupPanel3().setRendered(false);
		// Transportes vehiculares
		locBean.getTableColumn11().setRendered(false);
		locBean.getGroupPanel1().setRendered(false);
		// Clausuras temporarias
		locBean.getTableColumn9().setRendered(false);
		locBean.getGroupPanel2().setRendered(false);
		// Libretas sanitarias
		locBean.getTableColumn20().setRendered(false);
		locBean.getGroupPanel4().setRendered(false);

		locBean.getPanelAtributoDinamico().deshabilitarCampos();
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DocumentoSHPS locDocumento = (DocumentoSHPS) pObject;
			Persona persona = getBeanDocSHPS().obtenerObjetoDelElementoPila(1, Persona.class);
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().addObligacion(persona, locDocumento.getObligacion());
			return "La Obligaci\363n de SHPS se agreg\363 exitosamente.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			Integer nro = getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().getNroInscripcionDocEspSHPS();
			getBeanDocSHPS().getTfNumeroInscripcion().setText(nro.toString());
		}

		@Override
		public ABMModel getModel() {
			return DocEspSHPSModel.this;
		}

	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DocumentoSHPS locDocumento = (DocumentoSHPS) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().updateDocumentoSHPS(locDocumento);
			return "La Obligaci\363n de SHPS se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanDocSHPS().getBtnSeleccionarPersonaFisica().setRendered(true);
			getBeanDocSHPS().getBtnSeleccionarPersonaJuridica().setRendered(true);
			getBeanDocSHPS().getBtnLimpiarPersona().setRendered(true);
			getBeanDocSHPS().getBtnLimpiarContador().setRendered(true);
			getBeanDocSHPS().getBtnSeleccionarContador().setRendered(true);
			getBeanDocSHPS().getBtnSeleccionarPersonaJuridicaContador().setRendered(true);
		}

		@Override
		public ABMModel getModel() {
			return DocEspSHPSModel.this;
		}
	}

	public class ConsultarController extends ConsultarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarOcultarElementos();
		}

		@Override
		public ABMModel getModel() {
			return DocEspSHPSModel.this;
		}
	}

	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DocumentoSHPS documentoSHPS = (DocumentoSHPS) pObject;
			Obligacion obligacion = documentoSHPS.getObligacion();
			// Guardo los valores de auditoria que tenia el objeto actual
			String comentario = documentoSHPS.getComentarioAuditoria();
			long llave = documentoSHPS.getLlaveUsuarioAuditoria();
			obligacion = getCommunicationHabilitacionesBean().getRemoteSystemObligacion().getObligacionPorId(obligacion.getIdObligacion());
			documentoSHPS = getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().getDocumentoHabilitanteSHPS(obligacion);
			obligacion.setDocumentoEspecializado(documentoSHPS);
			obligacion.anular();
			obligacion.getDocumentoEspecializado().setComentarioAuditoria(comentario);
			obligacion.getDocumentoEspecializado().setLlaveUsuarioAuditoria(llave);
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().updateObligacion(obligacion);
			return "La Obligaci\363n de SHPS se elimin\363 exitosamente.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarOcultarElementos();
		}

		@Override
		public ABMModel getModel() {
			return DocEspSHPSModel.this;
		}
	}
	
	public class ReactivarController extends ModificarAbstractController {
		
		@Override
		public String getTituloPagina() {
			return "Reactivar " + this.getModel().getNombreEntidad();
		}
		
		@Override
		public String getTextoBotonAceptar() {
			return "Reactivar";
		}

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DocumentoSHPS documentoSHPS = (DocumentoSHPS) pObject;
			Obligacion obligacion = documentoSHPS.getObligacion();
			// Guardo los valores de auditoria que tenia el objeto actual
			String comentario = documentoSHPS.getComentarioAuditoria();
			long llave = documentoSHPS.getLlaveUsuarioAuditoria();
			obligacion = getCommunicationHabilitacionesBean().getRemoteSystemObligacion().getObligacionPorId(obligacion.getIdObligacion());
			documentoSHPS = getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().getDocumentoHabilitanteSHPS(obligacion);
			obligacion.setDocumentoEspecializado(documentoSHPS);
			obligacion.reActivar();
			obligacion.getDocumentoEspecializado().setComentarioAuditoria(comentario);
			obligacion.getDocumentoEspecializado().setLlaveUsuarioAuditoria(llave);
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().updateObligacion(obligacion);
			return "La Obligaci\363n de SHPS se reactiv\363 exitosamente.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarOcultarElementos();
		}

		@Override
		public ABMModel getModel() {
			return DocEspSHPSModel.this;
		}
	}

	@Override
	public String getReglaNavegacion() {
		return "ABMDocEspSHPS";
	}

	@Override
	public String getNombreEntidad() {
		return "Obligaci\363n: SHPS";
	}

}
