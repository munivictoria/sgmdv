/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.grpOSP.ABMDocEspOSP;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.SubParcela;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author pedro
 */
public class DocEspOSPModel extends ABMModel {

	private ABMDocEspOSP getBeanDocEspOSP() {
		return (ABMDocEspOSP) getRequestBean("habilitaciones$grpOSP$ABMDocEspOSP$ABMDocEspOSP");
	}

	private Validador getValidadorAgregarModificar() {

		List<UIComponent> listaComponentes = new ArrayList<UIComponent>(); // lista
		// de
		// componentes
		// no
		// vacios
		List<String> listaNombres = new ArrayList<String>(); // lista de los
		// nombres de
		// los
		// componentes
		// no vacios

		Validador v = new Validador();

		UIComponent[] fechas = new UIComponent[2];
		String[] nomFechas = new String[2];
		UIComponent[] noEnteros = new UIComponent[1];
		String[] nomNoEnteros = new String[1];
		UIComponent[] flotantes = new UIComponent[1];
		String[] nomFlotantes = new String[1];

		listaComponentes.add(getBeanDocEspOSP().getTfFechaInicio());
		listaNombres.add("Inicio de Actividades");
		listaComponentes.add(getBeanDocEspOSP().getTfParcela());
		listaNombres.add("Parcela");

		if (!SecurityMgr.getInstance().getMunicipalidad().isVariosServiciosOSP()) {
			listaComponentes.add(getBeanDocEspOSP().getTfServicioOSP());
			listaNombres.add("Servicio");
		}

		listaComponentes.add(getBeanDocEspOSP().getTfNumeroCuenta());
		listaNombres.add("N\372mero de Cuenta");

		int pos = 0;
		fechas[pos] = getBeanDocEspOSP().getTfFechaInicio();
		nomFechas[pos++] = "Inicio de Actividades";
		fechas[pos] = getBeanDocEspOSP().getTfFechaCese();
		nomFechas[pos++] = "Cese de Actividades";

		pos = 0;
		noEnteros[pos] = getBeanDocEspOSP().getTfNumeroCuenta();
		nomNoEnteros[pos++] = "N\372mero de Cuenta";

		pos = 0;
		flotantes[pos] = getBeanDocEspOSP().getTfCoeficienteZonal();
		nomFlotantes[pos++] = "Coeficiente Zonal";

		UIComponent[] noVacios = new UIComponent[listaComponentes.size()];
		noVacios = listaComponentes.toArray(noVacios);
		String[] nomNoVacios = new String[listaNombres.size()];
		nomNoVacios = listaNombres.toArray(nomNoVacios);

		v.noSonVacios(noVacios, nomNoVacios);
		v.formatoFechaValido(fechas, nomFechas);
		v.esNumero(noEnteros, nomNoEnteros);
		v.sonFlotantes(flotantes, nomFlotantes);

		DocumentoOSP documentoOSP = getBeanDocEspOSP().obtenerObjetoDelElementoPila(1, DocumentoOSP.class);
		Parcela parcela = getBeanDocEspOSP().obtenerObjetoDelElementoPila(3, Parcela.class);
		Domicilio domicilio = getBeanDocEspOSP().obtenerObjetoDelElementoPila(4, Domicilio.class);
		ServicioOSP servicio = getBeanDocEspOSP().obtenerObjetoDelElementoPila(5, ServicioOSP.class);
		SubParcela subparcela = getBeanDocEspOSP().obtenerObjetoDelElementoPila(8, SubParcela.class);

		if (domicilio.getLocalidad() == null) {
			String msg = "Debe seleccionar un Domicilio Postal.";
			v.getErrores().add(msg);
		}

		if (!v.fechaNoMayorAFechaActual(documentoOSP.getFechaInicioActividad(), "Fecha de Inicio de Actividad")) {
			getBeanDocEspOSP().getTfFechaInicio().setValid(false);
		}
		Localidad localidadDefecto = null;
		try {
			getBeanDocEspOSP().getComunicationBean().getRemoteSystemMunicipalidad().setLlave(getBeanDocEspOSP().getSessionBean1().getLlave());
			localidadDefecto = getBeanDocEspOSP().getComunicationBean().getRemoteSystemMunicipalidad().getLocalidadMunicipal();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (localidadDefecto == null) {
			String msg = "Debe seleccionar una Localidad para la Municipalidad.";
			v.getErrores().add(msg);
		} else if (parcela != null && parcela.getIdParcela() != -1) {
			if (parcela.getDomicilioParcelario().getLocalidad().getIdLocalidad() != localidadDefecto.getIdLocalidad()) {
				String msg = "La localidad de la parcela debe ser igual al de la Municipalidad.";
				v.getErrores().add(msg);
			}
		} else if (subparcela != null && subparcela.getIdParcela() != -1) {
			if (subparcela.getDomicilioParcelario().getLocalidad().getIdLocalidad() != localidadDefecto.getIdLocalidad()) {
				String msg = "La localidad de la subparcela debe ser igual al de la Municipalidad.";
				v.getErrores().add(msg);
			}
		}

		if ((servicio.isMedido()) && (getBeanDocEspOSP().getTfCodigoMedidor().getText() == null)) {
			String msg = "Debe ingresar un c\363digo de medidor si el servicio es medido.";
			v.getErrores().add(msg);
		}

		if (documentoOSP.getListaRegAlicuotas().isEmpty()) {
			String msg = "Debe seleccionar al menos un Servicio.";
			v.getErrores().add(msg);
		}

		ArrayList atributosDinamicos = this.getBeanDocEspOSP().obtenerObjetoDelElementoPila(7, ArrayList.class);

		Validador v2 = this.getBeanDocEspOSP().getPanelAtributoDinamico().validarCampos(atributosDinamicos);
		if (v2.getErrores().size() > 0) {
			for (Object cadaError : v2.getErrores()) {
				v.getErrores().add(cadaError);
			}
		}
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMDocEspOSP abmDocEspOSP = this.getBeanDocEspOSP();

		abmDocEspOSP.getTfBaseConsumo().setDisabled(true);
		abmDocEspOSP.getTfCodigoMedidor().setDisabled(true);
		abmDocEspOSP.getTfCoeficienteZonal().setDisabled(true);
		abmDocEspOSP.getTfFechaCese().setDisabled(true);
		abmDocEspOSP.getTfFechaInicio().setDisabled(true);
		abmDocEspOSP.getTaNombre().setDisabled(true);
		abmDocEspOSP.getTfNumeroCuenta().setDisabled(true);
		abmDocEspOSP.getTfNumeroSubCuenta().setDisabled(true);
		abmDocEspOSP.getTfParcela().setDisabled(true);
		abmDocEspOSP.getTfPersona().setDisabled(true);
		abmDocEspOSP.getTfServicioOSP().setDisabled(true);

		abmDocEspOSP.getBtnSeleccionarSubparcela().setRendered(false);
		abmDocEspOSP.getBtnLimpiarParcela().setRendered(false);
		abmDocEspOSP.getBtnSeleccionarParcela().setRendered(false);
		abmDocEspOSP.getBtnLimpiarSubParcela().setRendered(false);
		abmDocEspOSP.getBtnLimpiarPersona().setRendered(false);
		abmDocEspOSP.getBtnLimpiarServicio().setRendered(false);
		abmDocEspOSP.getBtnLimpiarDomicilioPostal().setRendered(false);
		abmDocEspOSP.getBtnSeleccionarDomicilioParcela().setRendered(false);
		abmDocEspOSP.getBtnSeleccionarDomicilioPostal().setRendered(false);
		abmDocEspOSP.getBtnSeleccionarDomicilioSolicitante().setRendered(false);
		abmDocEspOSP.getBtnSeleccionarPersonaFisica().setRendered(false);
		abmDocEspOSP.getBtnSeleccionarPersonaJuridica().setRendered(false);
		abmDocEspOSP.getBtnSeleccionarServicio().setRendered(false);
		abmDocEspOSP.getPanelAtributoDinamico().deshabilitarCampos();
		abmDocEspOSP.getGroupPanel3().setRendered(false);
		abmDocEspOSP.getTfCodigoMedidorTabla().setDisabled(true);
		abmDocEspOSP.getTaComentarioLogAuditoria().setRendered(false);
		abmDocEspOSP.getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarDocEspOSPController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion) pObject;
			Persona locPersona = locObligacion.getPersona();
			getBeanDocEspOSP().getElementoPila().getObjetos().set(2, locPersona);
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().addObligacion(locPersona, locObligacion);
			return "El Documento Especial OSP se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ABMDocEspOSP abmDocEspOSP = getBeanDocEspOSP();
			abmDocEspOSP.getTfPersona().setDisabled(true);
			abmDocEspOSP.getTfPersona().setVisible(false);
			abmDocEspOSP.getTfPersona().setRendered(false);

			abmDocEspOSP.getLabel16().setVisible(false);
			abmDocEspOSP.getLabel16().setRendered(false);
			abmDocEspOSP.getTablaLogs().setRendered(false);

			alternarServiciosOSP();
			deshabilitarPorObligacionTGIEncontrada();
		}

		@Override
		public ABMModel getModel() {
			return DocEspOSPModel.this;
		}

	}

	public class ModificarDocEspOSPController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().updateObligacion(locObligacion);

			return "El Documento Especial OSP se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			// getBeanDocEspOSP().getTable1().setRendered(false);
			getBeanDocEspOSP().getBtnSeleccionarSubparcela().setRendered(true);
			getBeanDocEspOSP().getBtnLimpiarParcela().setRendered(false);
			getBeanDocEspOSP().getBtnSeleccionarParcela().setRendered(true);
			getBeanDocEspOSP().getBtnLimpiarSubParcela().setRendered(false);

			alternarServiciosOSP();
			deshabilitarPorObligacionTGIEncontrada();
		}

		@Override
		public ABMModel getModel() {
			return DocEspOSPModel.this;
		}
	}

	public class ConsultarDocEspOSPController extends ConsultarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			return null;
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanDocEspOSP().getTable1().setVisible(false);
			getBeanDocEspOSP().getTable1().setRendered(false);
			deshabilitarElementosConsultarEliminar();

			alternarServiciosOSP();
		}

		@Override
		public ABMModel getModel() {
			return DocEspOSPModel.this;
		}
	}

	public class EliminarDocEspOSPController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion) pObject;
			DocumentoOSP locDocumento = getBeanDocEspOSP().obtenerObjetoDelElementoPila(1, DocumentoOSP.class);
			locDocumento.setComentarioAuditoria(locObligacion.getComentarioAuditoria());
			locDocumento.setLlaveUsuarioAuditoria(locObligacion.getLlaveUsuarioAuditoria());
			locObligacion.setDocumentoEspecializado(locDocumento);
			locObligacion.anular();
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().updateObligacion(locObligacion);

			return "El documento OSP se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanDocEspOSP().getTable1().setVisible(false);
			getBeanDocEspOSP().getTable1().setRendered(false);
			deshabilitarElementosConsultarEliminar();

			alternarServiciosOSP();
		}

		@Override
		public ABMModel getModel() {
			return DocEspOSPModel.this;
		}
	}
	
	public class ReactivarDocEspOSPController extends ModificarAbstractController {
		
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
			Obligacion locObligacion = (Obligacion) pObject;
			DocumentoOSP locDocumento = getBeanDocEspOSP().obtenerObjetoDelElementoPila(1, DocumentoOSP.class);
			locDocumento.setComentarioAuditoria(locObligacion.getComentarioAuditoria());
			locDocumento.setLlaveUsuarioAuditoria(locObligacion.getLlaveUsuarioAuditoria());
			locObligacion.setDocumentoEspecializado(locDocumento);
			locObligacion.reActivar();
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().updateObligacion(locObligacion);
			return "El documento OSP se reactiv\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanDocEspOSP().getTable1().setVisible(false);
			getBeanDocEspOSP().getTable1().setRendered(false);
			deshabilitarElementosConsultarEliminar();

			alternarServiciosOSP();
		}

		@Override
		public ABMModel getModel() {
			return DocEspOSPModel.this;
		}
	}

	public void alternarServiciosOSP() {
		ABMDocEspOSP abmDocEspOSP = getBeanDocEspOSP();
		if (SecurityMgr.getInstance().getMunicipalidad().isVariosServiciosOSP()) {
			abmDocEspOSP.getGroupPanel1().setRendered(false);
			abmDocEspOSP.getGroupPanel2().setRendered(true);
		} else {
			abmDocEspOSP.getGroupPanel2().setRendered(false);
			abmDocEspOSP.getGroupPanel1().setRendered(true);
		}
	}
	
	private void deshabilitarPorObligacionTGIEncontrada() {
		ABMDocEspOSP bean = getBeanDocEspOSP();
		Obligacion obligacionTGI = (Obligacion) bean.getElementoPila().getObjetos().get(10);
		if (obligacionTGI != null) {
			bean.getBtnLimpiarDomicilioPostal().setRendered(false);
			bean.getBtnSeleccionarDomicilioParcela().setRendered(false);
			bean.getBtnSeleccionarDomicilioPostal().setRendered(false);
			bean.getBtnSeleccionarDomicilioSolicitante().setRendered(false);
			bean.getCheckbox1().setDisabled(true);
		}
	}

	@Override
	public String getReglaNavegacion() {
		return "ABMDocEspOSP";
	}

	@Override
	public String getNombreEntidad() {
		return "Obligaci\363n: OSP";
	}
}
