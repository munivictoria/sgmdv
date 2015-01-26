/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.grpTGI.ABMDocEspTGI;

import java.util.ArrayList;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
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
public class DocEspTGIModel extends ABMModel {

	private ABMDocEspTGI getBeanDocEspTGI(){
		return (ABMDocEspTGI) getRequestBean("habilitaciones$grpTGI$ABMDocEspTGI$ABMDocEspTGI");
	}

	private Validador getValidadorAgregarModificar(){
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		UIComponent[] noFechas = new UIComponent[1];
		String[] nomNoFechas = new String[1];

		int pos = 0;
		noVacios[pos] = this.getBeanDocEspTGI().getTfParcela();
		nomNoVacios[pos++] = "Parcela";
		noVacios[pos] = this.getBeanDocEspTGI().getTfFechaInicio();
		nomNoVacios[pos++] = "Fecha Inicio";

		pos = 0;
		noFechas[pos] = this.getBeanDocEspTGI().getTfFechaInicio();
		nomNoFechas[pos++] = "Fecha Inicio";

		v.noSonVacios(noVacios, nomNoVacios);
		v.formatoFechaValido(noFechas, nomNoVacios);

		Domicilio domicilio = this.getBeanDocEspTGI().obtenerObjetoDelElementoPila(4, Domicilio.class);
		if (domicilio.getLocalidad() == null) {
			String msg = "Debe seleccionar un Domicilio Postal.";
			v.getErrores().add(msg);
		}

		DocumentoTGI documentoTGI = (DocumentoTGI) this.getBeanDocEspTGI().getElementoPila().getObjetos().get(1);

		if (!v.fechaNoMayorAFechaActual(documentoTGI.getFechaInicioActividad(), "Inicio de Actividades")) {
			this.getBeanDocEspTGI().getTfFechaInicio().setValid(false);
		}

		ArrayList atributosDinamicos = this.getBeanDocEspTGI().obtenerObjetoDelElementoPila(5, ArrayList.class);

		Validador v2 = this.getBeanDocEspTGI().getPanelAtributoDinamico().validarCampos(atributosDinamicos);
		if (v2.getErrores().size() > 0) {
			for (Object cadaError : v2.getErrores()) {
				v.getErrores().add(cadaError);
			}
		}
		return v;
	}
	private void deshabilitarElementosConsultarEliminar(){
		this.getBeanDocEspTGI().getTfFechaCese().setDisabled(true);
		this.getBeanDocEspTGI().getTfFechaInicio().setDisabled(true);
		this.getBeanDocEspTGI().getTaNombre().setDisabled(true);
		this.getBeanDocEspTGI().getTfParcela().setDisabled(true);

		this.getBeanDocEspTGI().getBtnLimpiarDomicilioPostal().setRendered(false);

		this.getBeanDocEspTGI().getBtnLimpiarPersona().setRendered(false);
		this.getBeanDocEspTGI().getBtnSeleccionarDomicilioParcela().setRendered(false);
		this.getBeanDocEspTGI().getBtnSeleccionarDomicilioPostal().setRendered(false);
		this.getBeanDocEspTGI().getBtnSeleccionarDomicilioSolicitante().setRendered(false);
		this.getBeanDocEspTGI().getBtnSeleccionarParcela().setRendered(false);
		this.getBeanDocEspTGI().getBtnLimpiarParcela().setRendered(false);
		this.getBeanDocEspTGI().getBtnSeleccionarPersonaFisica().setRendered(true);
		//		        this.getBeanDocEspTGI().getBtnSeleccionarPersonaFisica().setRendered(false);
		this.getBeanDocEspTGI().getBtnSeleccionarPersonaJuridica().setRendered(true);
		this.getBeanDocEspTGI().getTableColumn1().setRendered(false);
		//		this.getBeanDocEspTGI().getTable1().setRendered(false);
		this.getBeanDocEspTGI().getPanelAtributoDinamico().deshabilitarCampos();
		this.getBeanDocEspTGI().getTaComentarioLogAuditoria().setRendered(false);
		this.getBeanDocEspTGI().getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarDocEspTGIController extends AgregarAbstractController{

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion)pObject;
			Persona persona = getBeanDocEspTGI().obtenerObjetoDelElementoPila(2, Persona.class);
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().addObligacion(persona, locObligacion);
			return "El Documento TGI se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return DocEspTGIModel.this;
		}
	}
	public class ModificarDocEspTGIController extends ModificarAbstractController{

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion) pObject;   
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().updateObligacion(locObligacion);
			return "El Documento TGI se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			//			getBeanDocEspTGI().getTable1().setRendered(false);

			getBeanDocEspTGI().getBtnLimpiarParcela().setRendered(false);
			getBeanDocEspTGI().getBtnSeleccionarParcela().setRendered(true);
		}

		@Override
		public ABMModel getModel() {
			return DocEspTGIModel.this;
		}
	}
	public class ConsultarDocEspController extends ConsultarAbstractController{

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
			return DocEspTGIModel.this;
		}
	}
	public class EliminarDocEspTGIController extends EliminarAbstractController{

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion) pObject;
			locObligacion.anular();
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().updateObligacion(locObligacion);
			return "El Documento TGI se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return DocEspTGIModel.this;
		}
	}
	public class RectivarDocEspTGIController extends ModificarAbstractController{
		
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
			locObligacion.reActivar();
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().updateObligacion(locObligacion);
			return "El Documento TGI se reactiv\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return DocEspTGIModel.this;
		}
	}
	@Override
	public String getReglaNavegacion() {
		return "ABMDocEspTGI";
	}

	@Override
	public String getNombreEntidad() {
		return "Documentos Especiales TGI";
	}
}