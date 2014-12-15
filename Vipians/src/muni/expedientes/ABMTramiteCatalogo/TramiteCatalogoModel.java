
package muni.expedientes.ABMTramiteCatalogo;

import javax.faces.component.UIComponent;

import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;
import com.trascender.presentacion.abstracts.controller.ABMController;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

public class TramiteCatalogoModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMTramiteCatalogo";
	}

	@Override
	public String getNombreEntidad() {
		return "Trámite de Catálogo";
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];
		int pos = 0;
		noVacios[pos] = getBeanTramiteCatalogo().getTfNombre();
		nomNoVacios[pos] = "Nombre";
		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMTramiteCatalogo abmTramiteCatalogo = getBeanTramiteCatalogo();
		abmTramiteCatalogo.getTfNombre().setDisabled(true);
		abmTramiteCatalogo.getCbAvanzarFase().setDisabled(true);
		abmTramiteCatalogo.getCbReiniciaConFase().setDisabled(true);
		
		abmTramiteCatalogo.getTableDC().getBtnAgregar().setVisible(false);
		abmTramiteCatalogo.getTableDC().getBtnQuitar().setRendered(false);
		abmTramiteCatalogo.getTableDC().getBtnQuitarTodos().setRendered(false);
		
		abmTramiteCatalogo.getTableET().getBtnAgregar().setVisible(false);
		abmTramiteCatalogo.getTableET().getBtnQuitar().setRendered(false);
		abmTramiteCatalogo.getTableET().getBtnQuitarTodos().setRendered(false);
		
		abmTramiteCatalogo.getTableDC().getTableColumn1().setRendered(false);
		abmTramiteCatalogo.getTableDC().getGroupPanel1().setRendered(false);
		abmTramiteCatalogo.getTableET().getTableColumn1().setRendered(false);
		abmTramiteCatalogo.getTableET().getGroupPanel1().setRendered(false);
		
	}

	private ABMTramiteCatalogo getBeanTramiteCatalogo() {
		return (ABMTramiteCatalogo) getRequestBean("expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo");
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TramiteCatalogo locTramiteCatalogo = (TramiteCatalogo) pObject;
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().addTramiteCatalogo(locTramiteCatalogo);
			return "El Trámite del Cátalogo se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			// TODO Auto-generated method stub
		}

		@Override
		public ABMModel getModel() {
			return TramiteCatalogoModel.this;
		}
	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TramiteCatalogo locTramiteCatalogo = (TramiteCatalogo) pObject;
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().updateTramiteCatalogo(locTramiteCatalogo);
			return "El Trámite del Cátalogo se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {
			return TramiteCatalogoModel.this;
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
			return TramiteCatalogoModel.this;
		}
	}

	public class EliminarControler extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TramiteCatalogo locTramiteCatalogo = (TramiteCatalogo) pObject;
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().deleteTramiteCatalogo(locTramiteCatalogo);
			return "El Trámite del Cátalogo se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return TramiteCatalogoModel.this;
		}
	}
	
	public class RecuperarTramiteCatalogo implements ABMController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TramiteCatalogo locTramite = (TramiteCatalogo) pObject;
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().restoreTramiteCatalogo(locTramite);
			
			return "El Trámite se recuper\363 exitosamente";
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
			return "Recuperar Trámite";
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
			return TramiteCatalogoModel.this;
		}

		@Override
		public boolean seleccionarObjeto() {
			return true;
		}
	}
}