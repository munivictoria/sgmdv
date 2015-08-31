/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.ABMFaseCatalogo;

import javax.faces.component.UIComponent;

import com.trascender.expedientes.recurso.persistent.FaseCatalogo;
import com.trascender.presentacion.abstracts.controller.ABMController;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

public class FaseCatalogoModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMFaseCatalogo";
	}

	@Override
	public String getNombreEntidad() {
		return "Fase de Catálogo";
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];
		int pos = 0;
		noVacios[pos] = getBeanFaseCatalogo().getTfNombre();
		nomNoVacios[pos] = "Nombre";
		v.noSonVacios(noVacios, nomNoVacios);
		
		return v;
	}

	private ABMFaseCatalogo getBeanFaseCatalogo() {
		return (ABMFaseCatalogo) getRequestBean("expedientes$ABMFaseCatalogo$ABMFaseCatalogo");
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMFaseCatalogo abmFaseCatalogo = getBeanFaseCatalogo();
		abmFaseCatalogo.getTfNombre().setDisabled(true);

		abmFaseCatalogo.getTableTC().getBtnAgregar().setRendered(false);
		abmFaseCatalogo.getTableTC().getBtnQuitar().setRendered(false);
		abmFaseCatalogo.getTableTC().getBtnQuitarTodos().setRendered(false);
		abmFaseCatalogo.getTableTC().getTableColumn1().setRendered(false);
		abmFaseCatalogo.getTableTC().getGroupPanel1().setRendered(false);

		abmFaseCatalogo.getGroupPanel1().setRendered(false);
		abmFaseCatalogo.getTableColumn1().setRendered(false);
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			FaseCatalogo locFaseCatalogo = (FaseCatalogo) pObject;
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().addFaseCatalogo(locFaseCatalogo);
			
			return "La Fase del Catálogo se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return FaseCatalogoModel.this;
		}
		
	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			FaseCatalogo locFaseCatalogo = (FaseCatalogo) pObject;
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().updateFaseCatalogo(locFaseCatalogo);
			
			return "La Fase del Catálogo se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return FaseCatalogoModel.this;
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
			return FaseCatalogoModel.this;
		}

	}

	public class EliminarControler extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			FaseCatalogo locFaseCatalogo = (FaseCatalogo) pObject;
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
			String mensaje = getCommunicationExpedientesBean().getRemoteSystemCatalogos().deleteFaseCatalogo(locFaseCatalogo);
			
			return mensaje;
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return FaseCatalogoModel.this;
		}
		
	}

	public class RecuperarFaseCatalogo implements ABMController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			FaseCatalogo locfase = (FaseCatalogo) pObject;
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().restoreFaseCatalogo(locfase);

			return "La Fase se recuper\363 exitosamente";
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
			return "Recuperar Fase";
		}

		@Override
		public String getTextoBotonCancelar() {
			return "Cancelar";
		}

		@Override
		public String getTituloPagina() {
			return "Recuperar Fase";
		}

		@Override
		public String getCodigoColores() {
			return Constantes.COLORES_AGREGAR;
		}

		@Override
		public ABMModel getModel() {
			return FaseCatalogoModel.this;
		}

		@Override
		public boolean seleccionarObjeto() {
			return true;
		}
		
	}

}