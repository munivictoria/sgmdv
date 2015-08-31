/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.ABMExpediente;

import muni.expedientes.tables.TableDocumentos;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class DocumentoModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMDocumento";
	}

	@Override
	public String getNombreEntidad() {
		return "Documento";
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		// UIComponent[] noVacios = new UIComponent[1];
		// String[] nomNoVacios = new String[1];
		// int pos = 0;
		
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
	}

	// private ABMDocumento getBeanDocumento() {
	// return (ABMDocumento) getRequestBean("expedientes$ABMExpediente$ABMDocumento");
	// }

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TableDocumentos.WDocumento wDocumento = (TableDocumentos.WDocumento) pObject;
			
			return "El Documento " + wDocumento.documento.getPlantilla() + " se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return DocumentoModel.this;
		}

	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TableDocumentos.WDocumento wDocumento = (TableDocumentos.WDocumento) pObject;
			
			return "El Documento " + wDocumento.documento.getPlantilla() + " se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return DocumentoModel.this;
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
			return DocumentoModel.this;
		}

	}

	public class EliminarControler extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TableDocumentos.WDocumento wDocumento = (TableDocumentos.WDocumento) pObject;
			
			return "El Documento " + wDocumento.documento.getPlantilla() + " se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return DocumentoModel.this;
		}

	}

}