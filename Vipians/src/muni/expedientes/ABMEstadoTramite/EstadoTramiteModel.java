package muni.expedientes.ABMEstadoTramite;

import javax.faces.component.UIComponent;

import com.trascender.expedientes.recurso.persistent.EstadoTramite;
import com.trascender.presentacion.abstracts.controller.ABMController;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

public class EstadoTramiteModel extends ABMModel{

	@Override
	public String getReglaNavegacion() {
		return "ABMEstadoTramite";
	}

	@Override
	public String getNombreEntidad() {
		return "Estados Tramite";
	}
	
	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];
		int pos = 0;
		noVacios[pos] = getBeanEstadoTramite().getTfNombre();
		nomNoVacios[pos] = "Nombre";
		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMEstadoTramite abmABMEstadosTramite = getBeanEstadoTramite();
		
		abmABMEstadosTramite.getTfNombre().setDisabled(true);
		abmABMEstadosTramite.getCbCierreTramite().setDisabled(true);
	}

	private ABMEstadoTramite getBeanEstadoTramite() {
		return (ABMEstadoTramite) getRequestBean("expedientes$ABMEstadoTramite$ABMEstadoTramite");
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			EstadoTramite locEstadosTramite = (EstadoTramite) pObject;
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().addEstadosTramite(locEstadosTramite);

			return "El Estado Tramite se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			// TODO Auto-generated method stub
		}

		@Override
		public ABMModel getModel() {
			return EstadoTramiteModel.this;
		}

	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			EstadoTramite locEstadosTramite = (EstadoTramite) pObject;
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().updateEstadosTramite(locEstadosTramite);

			return "El Estado Tramite se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {
			return EstadoTramiteModel.this;
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
			return EstadoTramiteModel.this;
		}

	}

	public class EliminarControler extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			EstadoTramite locEstadosTramite = (EstadoTramite) pObject;
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().deleteEstadosTramite(locEstadosTramite);

			return "El Estado Tramite se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return EstadoTramiteModel.this;
		}

	}
	public class RecuperarEstadoTramiteCatalogo implements ABMController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			EstadoTramite locEstadoTramite = (EstadoTramite) pObject;
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(getSessionBean1().getLlave());
			getCommunicationExpedientesBean().getRemoteSystemCatalogos().restoreEstadoTramiteCatalogo(locEstadoTramite);
			
			return "El Estado Trámite se recuper\363 exitosamente";
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
			return "Recuperar Estado Trámite";
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
			return EstadoTramiteModel.this;
		}

		@Override
		public boolean seleccionarObjeto() {
			return true;
		}
	}
}
