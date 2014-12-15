package muni.habilitaciones.grpTipoParametro.ABMTipoParametroConstante;

import javax.faces.component.UIComponent;

import com.trascender.habilitaciones.recurso.filtros.FiltroTipoParametroConstante;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class TipoParametroConstanteModel  extends ABMModel{

	@Override
	public String getReglaNavegacion() {
		return "ABMTipoParametroConstante";
	}

	@Override
	public String getNombreEntidad() {
		return "Par\341metro Constante";
	}

	private ABMTipoParametroConstante getBeanTipoParametro() {
		return (ABMTipoParametroConstante) getRequestBean("habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		getBeanTipoParametro().getTaComentarioLogAuditoria().setRendered(false);
		getBeanTipoParametro().getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarTipoParametroConstanteController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoParametroConstante locTipoParametroConst = (TipoParametroConstante) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(getSessionBean1().getLlave());
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().addTipoParametroConstante(locTipoParametroConst);
			return "El Par\341metro Constante se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return TipoParametroConstanteModel.this;
		}

	}

	public class ModificarTipoParametroConstanteController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoParametroConstante locTipoParametroConst = (TipoParametroConstante) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(getSessionBean1().getLlave());
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().updateTipoParametroConstante(locTipoParametroConst);
			return "El Par\341metro Constante se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return TipoParametroConstanteModel.this;
		}
	}

	public class ConsultarTipoParametroConstanteController extends ConsultarAbstractController {

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
			return TipoParametroConstanteModel.this;
		}

	}

	public class EliminarTipoParametroConstanteController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoParametroConstante locTipoParametroConst = (TipoParametroConstante) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(getSessionBean1().getLlave());
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().deleteTipoParametroConstante(locTipoParametroConst);
			return "El Par\341metro Constante se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return TipoParametroConstanteModel.this;
		}
	}
}
