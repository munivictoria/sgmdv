/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.catastro.ABMCoeficienteDepreciacion;

import java.util.ArrayList;
import java.util.List;

import com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author Fer
 */
public class CoeficienteDepreciacionModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMCoeficienteDepreciacion";
	}

	@Override
	public String getNombreEntidad() {
		return "Coeficiente de Depreciacion";
	}

	private ABMCoeficienteDepreciacion getBeanCoeficiente() {
		return (ABMCoeficienteDepreciacion) getRequestBean("catastro$ABMCoeficienteDepreciacion$ABMCoeficienteDepreciacion");
	}

	private Validador getValidadorAgregarModificar() {

		Validador v = new Validador();
		// UIComponent[] noVacios = new UIComponent[0];
		// String[] nomNoVacios = new String[0];
		// int pos = 0;

		// v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		
	}

	public class ModificarCoeficienteDepreciacionController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			List listaCoeficiente = getBeanCoeficiente().obtenerObjetoDelElementoPila(1, ArrayList.class);
			getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(getSessionBean1().getLlave());
			for (Object obj : listaCoeficiente) {
				CoeficienteDepreciacion cadaCoeficiente = (CoeficienteDepreciacion) obj;
				getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().updateCoeficienteDepreciacion(cadaCoeficiente);
			}
			return "El Coeficiente se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {
			return CoeficienteDepreciacionModel.this;
		}
	}

	public class ConsultarCoeficienteDepreciacionController extends ConsultarAbstractController {

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
			
			ABMCoeficienteDepreciacion abm = getBeanCoeficiente();
			abm.getBtnLimpiarCategoria().setRendered(false);
			abm.getTextField1().setDisabled(true);
			abm.getTextField2().setDisabled(true);
			abm.getTextField3().setDisabled(true);
		}

		@Override
		public ABMModel getModel() {
			return CoeficienteDepreciacionModel.this;
		}
	}
}