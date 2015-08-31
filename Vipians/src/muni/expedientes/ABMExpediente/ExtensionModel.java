/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.ABMExpediente;

import java.util.ArrayList;

import com.trascender.expedientes.recurso.persistent.NodoExpediente;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class ExtensionModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMExtension";
	}

	@Override
	public String getNombreEntidad() {
		return "Extension";
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		// UIComponent[] noVacios = new UIComponent[1];
		// String[] diasNoVacios = new String[1];
		// int pos = 0;
		
		return v;
	}

	private ABMExtension getBeanExtension() {
		return (ABMExtension) getRequestBean("expedientes$ABMExpediente$ABMExtension");
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			NodoExpediente locNodoExpediente = (NodoExpediente) getBeanExtension().obtenerObjetoDelElementoPila(1);
			Integer locDias = (Integer) getBeanExtension().obtenerObjetoDelElementoPila(2);
			String locComentario = (String) getBeanExtension().obtenerObjetoDelElementoPila(3);
			Usuario locUsuario = getBeanExtension().getSessionBean1().getUsuario();

			// TODO Recuperar la lista de feriados.
			locNodoExpediente.agregarExtension(locDias, locUsuario, locComentario, new ArrayList<DiaFeriado>());
			
			return "La Extensión se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ExtensionModel.this;
		}
		
	}

}