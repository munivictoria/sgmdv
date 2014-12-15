
package muni.expedientes.ABMDocumentoCatalogo;

import javax.faces.component.UIComponent;

import com.trascender.expedientes.recurso.persistent.DocumentoCatalogo;
import com.trascender.presentacion.abstracts.controller.ABMController;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

public class DocumentoCatalogoModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMDocumentoCatalogo";
	}

	@Override
	public String getNombreEntidad() {
		return "Documento de Catálogo";

	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];
		int pos = 0;
		noVacios[pos] = getBeanDocumentoCatalogo().getTfNombre();
		nomNoVacios[pos] = "Nombre";
		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMDocumentoCatalogo abmDocumentoCatalogo = getBeanDocumentoCatalogo();
		abmDocumentoCatalogo.getTfNombre().setDisabled(true);

	}

	private ABMDocumentoCatalogo getBeanDocumentoCatalogo() {
		return (ABMDocumentoCatalogo) getRequestBean("expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo");
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DocumentoCatalogo locDocumentoCatalogo = (DocumentoCatalogo) pObject;
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().addDocumentoCatalogo(locDocumentoCatalogo);
			return "El Documento de Catálogo se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			// TODO Auto-generated method stub
		}

		@Override
		public ABMModel getModel() {
			return DocumentoCatalogoModel.this;
		}
	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DocumentoCatalogo locDocumentoCatalogo = (DocumentoCatalogo) pObject;
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().updateDocumentoCatalogo(locDocumentoCatalogo);
			return "El Documento de Catálogo se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return DocumentoCatalogoModel.this;
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
			return DocumentoCatalogoModel.this;
		}

	}

	public class EliminarControler extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DocumentoCatalogo locDocumentoCatalogo = (DocumentoCatalogo) pObject;
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().deleteDocumentoCatalogo(locDocumentoCatalogo);
			return "El Documento de Catálogo se elimin\363 exitosamente";

		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return DocumentoCatalogoModel.this;
		}
	}
	
	public class RecuperarDocumentoCatalogo implements ABMController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DocumentoCatalogo locDocumentoCatalogo = (DocumentoCatalogo) pObject;
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().restoreDocumentoCatalogo(locDocumentoCatalogo);
			
			return "El Documento se recuper\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public boolean guardaEstadoObjetosUsados() {
			return false;
		}

		@Override
		public boolean mostrarBotonAceptar() {
			return true;
		}

		@Override
		public boolean mostrarBotonCancelar() {
			return true;
		}

		@Override
		public boolean mostrarStSeparador() {
			return true;
		}

		@Override
		public boolean recargarPaginaAdmin() {
			return true;
		}

		@Override
		public String getTextoBotonAceptar() {
			return "Recuperar Documento";
		}

		@Override
		public String getTextoBotonCancelar() {
			return "Cancelar";
		}

		@Override
		public String getTituloPagina() {
			return "Recuperar";
		}

		@Override
		public String getCodigoColores() {
			return Constantes.COLORES_AGREGAR;
		}

		@Override
		public ABMModel getModel() {
			return DocumentoCatalogoModel.this;
		}

		@Override
		public boolean seleccionarObjeto() {
			return true;
		}
	}
}