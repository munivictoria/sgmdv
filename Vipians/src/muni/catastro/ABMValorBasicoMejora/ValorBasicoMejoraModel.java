/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.catastro.ABMValorBasicoMejora;

import javax.faces.component.UIComponent;

import com.trascender.catastro.recurso.persistent.ValorBasicoMejora;
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
public class ValorBasicoMejoraModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMValorBasicoMejora";
	}

	@Override
	public String getNombreEntidad() {
		return "Valor Basico de Mejora";
	}

	private ABMValorBasicoMejora getBeanValorBasicoMejora() {
		return (ABMValorBasicoMejora) getRequestBean("catastro$ABMValorBasicoMejora$ABMValorBasicoMejora");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[3];
		String[] nomNoVacios = new String[3];
		int pos = 0;

		noVacios[pos] = getBeanValorBasicoMejora().getTfAnioVigencia();
		nomNoVacios[pos++] = "AnioVigencia";
		noVacios[pos] = getBeanValorBasicoMejora().getTfCategoria();
		nomNoVacios[pos++] = "Categoria";
		noVacios[pos] = getBeanValorBasicoMejora().getTfValor();
		nomNoVacios[pos++] = "Valor";

		v.noSonVacios(noVacios, nomNoVacios);

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMValorBasicoMejora locValorBasicoMejora = getBeanValorBasicoMejora();
		locValorBasicoMejora.getTfAnioVigencia().setDisabled(true);
		locValorBasicoMejora.getTfCategoria().setDisabled(true);
		locValorBasicoMejora.getTfValor().setDisabled(true);
		locValorBasicoMejora.getBtnSeleccionarCategoria().setRendered(false);
	}

	public class AgregarValorBasicoMejoraController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ValorBasicoMejora locValorBasicoMejora = (ValorBasicoMejora) pObject;
			getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().addValorBasicoMejora(locValorBasicoMejora);

			return "El valor basico mejora se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ValorBasicoMejoraModel.this;
		}
	}

	public class ModificarValorBasicoMejoraController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ValorBasicoMejora locValorBasicoMejora = (ValorBasicoMejora) pObject;
			getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().updateValorBasicoMejora(locValorBasicoMejora);

			return "El valor basico mejora se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ValorBasicoMejoraModel.this;
		}
	}

	public class ConsultarValorBasicoMejoraController extends ConsultarAbstractController {

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
			return ValorBasicoMejoraModel.this;
		}
	}

	public class EliminarValorBasicoMejoraController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ValorBasicoMejora locValorBasicoMejora = (ValorBasicoMejora) pObject;
			getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().deleteValorBasicoMejora(locValorBasicoMejora);

			return "El valor basico mejora se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return ValorBasicoMejoraModel.this;
		}
	}
}