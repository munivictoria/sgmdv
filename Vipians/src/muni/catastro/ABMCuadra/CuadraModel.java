/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.catastro.ABMCuadra;

import javax.faces.component.UIComponent;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author juanma
 */
public class CuadraModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMCuadra";
	}

	@Override
	public String getNombreEntidad() {
		return "Cuadra";
	}

	private ABMCuadra getBeanCuadra() {
		return (ABMCuadra) getRequestBean("catastro$ABMCuadra$ABMCuadra");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[4];
		String[] nomNoVacios = new String[4];
		int pos = 0;
		noVacios[pos] = getBeanCuadra().getDdCalle();
		nomNoVacios[pos++] = "Calle";
		noVacios[pos] = getBeanCuadra().getDdCalleComienza();
		nomNoVacios[pos++] = "Calle Comienza";
		noVacios[pos] = getBeanCuadra().getDdCalleFinaliza();
		nomNoVacios[pos++] = "Calle Finaliza";
		noVacios[pos] = getBeanCuadra().getDdTipoNumeracion();
		nomNoVacios[pos++] = "Tipo de Numeración";
//		noVacios[pos] = getBeanCuadra().getTfNumeracionDesde();
//		nomNoVacios[pos++] = "Numeración desde";
//		noVacios[pos] = getBeanCuadra().getTfNumeracionHasta();
//		nomNoVacios[pos++] = "Numeración hasta";
//		noVacios[pos] = getBeanCuadra().getTfMetrosLineales();
//		nomNoVacios[pos++] = "Metros Lineales";

		v.noSonVacios(noVacios, nomNoVacios);

		Calle calle = (Calle) getBeanCuadra().getElementoPila().getObjetos().get(1);
		Calle calleComienza = (Calle) getBeanCuadra().getElementoPila().getObjetos().get(2);
		Calle calleFinaliza = (Calle) getBeanCuadra().getElementoPila().getObjetos().get(3);

		if (calle != null && calleComienza != null && calleFinaliza != null) {
			if (calle.equals(calleComienza)) {
				v.getErrores().add("Las calles no pueden estar repetidas.");
			} else if (calle.equals(calleFinaliza)) {
				v.getErrores().add("Las calles no pueden estar repetidas.");
			} else if (calleComienza.equals(calleFinaliza)) {
				v.getErrores().add("Las calles no pueden estar repetidas.");
			}
		}

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMCuadra abmCuadra = getBeanCuadra();
		abmCuadra.getDdCalle().setDisabled(true);
		abmCuadra.getDdTipoNumeracion().setDisabled(true);
		abmCuadra.getTfNumeracionDesde().setDisabled(true);
		abmCuadra.getTfNumeracionHasta().setDisabled(true);
		abmCuadra.getTfCodigoPostalPar().setDisabled(true);
		abmCuadra.getTfCodigoPostalImpar().setDisabled(true);
		abmCuadra.getTfMetrosLineales().setDisabled(true);
		abmCuadra.getDdCalleComienza().setDisabled(true);
		abmCuadra.getDdCalleFinaliza().setDisabled(true);
		abmCuadra.getBtnLimpiarCalle().setRendered(false);
		abmCuadra.getBtnLimpiarCalleComienza().setRendered(false);
		abmCuadra.getBtnLimpiarCalleFinaliza().setRendered(false);
		abmCuadra.getBtnSeleccionarCalle().setRendered(false);
		abmCuadra.getBtnSeleccionarCalle().setRendered(false);
		abmCuadra.getBtnSeleccionarCalleComienza().setRendered(false);
		abmCuadra.getBtnSeleccionarCalleFinaliza().setRendered(false);
		abmCuadra.getGroupPanel1().setRendered(false);
		abmCuadra.getTableColumn1().setRendered(false);
	}

	public class AgregarCuadraController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Cuadra locCuadra = (Cuadra) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().addCuadra(locCuadra);

			return "La cuadra se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return CuadraModel.this;
		}
	}

	public class ModificarCuadraController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Cuadra locCuadra = (Cuadra) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().updateCuadra(locCuadra);

			return "La cuadra se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return CuadraModel.this;
		}
	}

	public class ConsultarCuadraController extends ConsultarAbstractController {

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
			return CuadraModel.this;
		}
	}

	public class EliminarCuadraController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Cuadra locCuadra = (Cuadra) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().deleteCuadra(locCuadra);

			return "La cuadra se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return CuadraModel.this;
		}
	}
}