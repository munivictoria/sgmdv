/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.grpArrendamiento.ABMDocEspArrendamiento;

import java.util.ArrayList;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.arrendamiento.DocumentoArrendamiento;
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
public class DocEspArrendamientoModel extends ABMModel {

	private ABMDocEspArrendamiento getBeanDocEspArrendamiento(){
		return (ABMDocEspArrendamiento) getRequestBean("habilitaciones$grpArrendamiento$ABMDocEspArrendamiento$ABMDocEspArrendamiento");
	}

	private Validador getValidadorAgregarModificar(){
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		UIComponent[] noFechas = new UIComponent[1];
		String[] nomNoFechas = new String[1];

		int pos = 0;
		noVacios[pos] = this.getBeanDocEspArrendamiento().getTfParcela();
		nomNoVacios[pos++] = "Parcela";
		noVacios[pos] = this.getBeanDocEspArrendamiento().getTfFechaInicio();
		nomNoVacios[pos++] = "Fecha Inicio";

		pos = 0;
		noFechas[pos] = this.getBeanDocEspArrendamiento().getTfFechaInicio();
		nomNoFechas[pos++] = "Fecha Inicio";

		v.noSonVacios(noVacios, nomNoVacios);
		v.formatoFechaValido(noFechas, nomNoVacios);

		Domicilio domicilio = this.getBeanDocEspArrendamiento().obtenerObjetoDelElementoPila(4, Domicilio.class);
		if (domicilio.getLocalidad() == null) {
			String msg = "Debe seleccionar un Domicilio Postal.";
			v.getErrores().add(msg);
		}

		DocumentoArrendamiento documentoArrendamiento = (DocumentoArrendamiento) this.getBeanDocEspArrendamiento().getElementoPila().getObjetos().get(1);

		if (!v.fechaNoMayorAFechaActual(documentoArrendamiento.getFechaInicioActividad(), "Inicio de Actividades")) {
			this.getBeanDocEspArrendamiento().getTfFechaInicio().setValid(false);
		}

		ArrayList atributosDinamicos = this.getBeanDocEspArrendamiento().obtenerObjetoDelElementoPila(5, ArrayList.class);

		Validador v2 = this.getBeanDocEspArrendamiento().getPanelAtributoDinamico().validarCampos(atributosDinamicos);
		if (v2.getErrores().size() > 0) {
			for (Object cadaError : v2.getErrores()) {
				v.getErrores().add(cadaError);
			}
		}
		return v;
	}
	private void deshabilitarElementosConsultarEliminar(){
		this.getBeanDocEspArrendamiento().getTfFechaCese().setDisabled(true);
		this.getBeanDocEspArrendamiento().getTfFechaInicio().setDisabled(true);
		this.getBeanDocEspArrendamiento().getTaNombre().setDisabled(true);
		this.getBeanDocEspArrendamiento().getTfParcela().setDisabled(true);

		this.getBeanDocEspArrendamiento().getBtnLimpiarDomicilioPostal().setRendered(false);

		this.getBeanDocEspArrendamiento().getBtnLimpiarPersona().setRendered(false);
		this.getBeanDocEspArrendamiento().getBtnSeleccionarDomicilioParcela().setRendered(false);
		this.getBeanDocEspArrendamiento().getBtnSeleccionarDomicilioPostal().setRendered(false);
		this.getBeanDocEspArrendamiento().getBtnSeleccionarDomicilioSolicitante().setRendered(false);
		this.getBeanDocEspArrendamiento().getBtnSeleccionarParcela().setRendered(false);
		this.getBeanDocEspArrendamiento().getBtnLimpiarParcela().setRendered(false);
		this.getBeanDocEspArrendamiento().getBtnSeleccionarPersonaFisica().setRendered(true);
		//		        this.getBeanDocEspArrendamiento().getBtnSeleccionarPersonaFisica().setRendered(false);
		this.getBeanDocEspArrendamiento().getBtnSeleccionarPersonaJuridica().setRendered(true);
		this.getBeanDocEspArrendamiento().getTableColumn1().setRendered(false);
		//		this.getBeanDocEspArrendamiento().getTable1().setRendered(false);
		this.getBeanDocEspArrendamiento().getPanelAtributoDinamico().deshabilitarCampos();
		this.getBeanDocEspArrendamiento().getTaComentarioLogAuditoria().setRendered(false);
		this.getBeanDocEspArrendamiento().getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarDocEspArrendamientoController extends AgregarAbstractController{

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion)pObject;
			Persona persona = getBeanDocEspArrendamiento().obtenerObjetoDelElementoPila(2, Persona.class);
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().addObligacion(persona, locObligacion);
			return "El Documento Arrendamiento se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return DocEspArrendamientoModel.this;
		}
	}
	public class ModificarDocEspArrendamientoController extends ModificarAbstractController{

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion) pObject;   
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().updateObligacion(locObligacion);
			return "El Documento Arrendamiento se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			//			getBeanDocEspArrendamiento().getTable1().setRendered(false);

			getBeanDocEspArrendamiento().getBtnLimpiarParcela().setRendered(false);
			getBeanDocEspArrendamiento().getBtnSeleccionarParcela().setRendered(true);
		}

		@Override
		public ABMModel getModel() {
			return DocEspArrendamientoModel.this;
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
			return DocEspArrendamientoModel.this;
		}
	}
	public class EliminarDocEspArrendamientoController extends EliminarAbstractController{

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Obligacion locObligacion = (Obligacion) pObject;
			locObligacion.anular();
			getCommunicationHabilitacionesBean().getRemoteSystemObligacion().updateObligacion(locObligacion);
			return "El Documento Arrendamiento se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return DocEspArrendamientoModel.this;
		}
	}
	public class RectivarDocEspArrendamientoController extends ModificarAbstractController{
		
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
			return "El Documento Arrendamiento se reactiv\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return DocEspArrendamientoModel.this;
		}
	}
	@Override
	public String getReglaNavegacion() {
		return "ABMDocEspArrendamiento";
	}

	@Override
	public String getNombreEntidad() {
		return "Documentos Especiales Arrendamiento";
	}
}