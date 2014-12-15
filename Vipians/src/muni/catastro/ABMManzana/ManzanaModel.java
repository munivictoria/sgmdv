/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.catastro.ABMManzana;

import javax.faces.component.UIComponent;

import com.trascender.catastro.recurso.persistent.Manzana;
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
public class ManzanaModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMManzana";
	}

	@Override
	public String getNombreEntidad() {
		return "Manzana";
	}

	private ABMManzana getBeanManzana() {
		return (ABMManzana) getRequestBean("catastro$ABMManzana$ABMManzana");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];
		int pos = 0;
		noVacios[pos] = getBeanManzana().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
//		noVacios[pos] = getBeanManzana().getTfNroManzana();
//		nomNoVacios[pos++] = "Número de Manzana";
		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMManzana abmManzana = getBeanManzana();
		abmManzana.getTfNombre().setDisabled(true);
		abmManzana.getTfNroManzana().setDisabled(true);
		abmManzana.getBtnAgregar().setRendered(false);
		abmManzana.getBtnQuitar().setRendered(false);
		abmManzana.getBtnQuitarTodos().setRendered(false);
		abmManzana.getStaticText4().setRendered(false);
		abmManzana.getGroupPanel1().setRendered(false);
		abmManzana.getGroupPanel2().setRendered(false);
		abmManzana.getTableColumn1().setRendered(false);
		abmManzana.getTableColumn3().setRendered(false);
	}

	public class AgregarManzanaController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Manzana locManzana = (Manzana) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().addManzana(locManzana);

			return "La manzana se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ManzanaModel.this;
		}
	}

	public class ModificarManzanaController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Manzana locManzana = (Manzana) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().updateManzana(locManzana);

			return "La manzana se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ManzanaModel.this;
		}
	}

	public class ConsultarManzanaController extends ConsultarAbstractController {

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
			return ManzanaModel.this;
		}
	}

	public class EliminarManzanaController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Manzana locManzana = (Manzana) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemInformacionGeografica().deleteManzana(locManzana);

			return "La manzana se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return ManzanaModel.this;
		}
	}
}