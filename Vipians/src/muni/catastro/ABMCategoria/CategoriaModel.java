/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.catastro.ABMCategoria;

import javax.faces.component.UIComponent;

import com.trascender.catastro.recurso.persistent.Categoria;
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
public class CategoriaModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMCategoria";
	}

	@Override
	public String getNombreEntidad() {
		return "Categoria";
	}

	private ABMCategoria getBeanCategoria() {
		return (ABMCategoria) getRequestBean("catastro$ABMCategoria$ABMCategoria");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[3];
		String[] nomNoVacios = new String[3];
		int pos = 0;
		noVacios[pos] = getBeanCategoria().getTfCodigo();
		nomNoVacios[pos++] = "Código";
		noVacios[pos] = getBeanCategoria().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanCategoria().getTfTipoConstruccion();
		nomNoVacios[pos++] = "Tipo de Construcción";

		UIComponent[] enteros = new UIComponent[1];
		String[] nomEnteros = new String[1];
		pos = 0;
		enteros[pos] = getBeanCategoria().getTfCodigo();
		nomEnteros[pos++] = "Código";

		v.noSonVacios(noVacios, nomNoVacios);
		v.esNumero(enteros, nomEnteros);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMCategoria abmCategoria = getBeanCategoria();
		abmCategoria.getTfCodigo().setDisabled(true);
		abmCategoria.getTfNombre().setDisabled(true);
		abmCategoria.getTfTipoConstruccion().setDisabled(true);
		abmCategoria.getBtnSeleccionarTipoConstruccion().setRendered(false);
		abmCategoria.getTaComentarioLogAuditoria().setRendered(false);
		abmCategoria.getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarCategoriaController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Categoria locCategoria = (Categoria) pObject;
			getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().addCategoria(locCategoria);

			return "La categoria se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return CategoriaModel.this;
		}
	}

	public class ModificarCategoriaController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Categoria locCategoria = (Categoria) pObject;
			getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().updateCategoria(locCategoria);

			return "La categoria se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return CategoriaModel.this;
		}
	}

	public class ConsultarCategoriaController extends ConsultarAbstractController {

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
			return CategoriaModel.this;
		}
	}

	public class EliminarCategoriaController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Categoria locCategoria = (Categoria) pObject;
			getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemCodigosCatastrales().deleteCategoria(locCategoria);

			return "La categoria se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return CategoriaModel.this;
		}
	}
}