package muni.habilitaciones.grpTipoParametro.ABMTipoParametroGrupoZona;

import javax.faces.component.UIComponent;

import com.trascender.habilitaciones.recurso.persistent.TipoParametroGrupoZona;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class TipoParametroGrupoZonaModel extends ABMModel{

	@Override
	public String getReglaNavegacion() {
		return "ABMTipoParametroGrupoZona";
	}

	@Override
	public String getNombreEntidad() {
		return "Par\341metro de Grupo de Zonas";
	}

	private ABMTipoParametroGrupoZona getBeanParametroGrpZona() {
		return (ABMTipoParametroGrupoZona) getRequestBean("habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[3];
		String[] nomNoVacios = new String[3];
		
		int pos = 0;
		noVacios[pos] = getBeanParametroGrpZona().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanParametroGrpZona().getTfNombreVariable();
		nomNoVacios[pos++] = "Nombre de Variable";
		noVacios[pos] = getBeanParametroGrpZona().getTfZonificacion();
		nomNoVacios[pos++] = "Zonificacion";

		v.noSonVacios(noVacios, nomNoVacios);

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		getBeanParametroGrpZona().getTaComentarioLogAuditoria().setRendered(false);
		getBeanParametroGrpZona().getLblComentarioLogAuditoria().setRendered(false);
		getBeanParametroGrpZona().getBtnSeleccionarZonificacion().setRendered(false);
	}

	public class AgregarTipoParametroGrupoZonaController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoParametroGrupoZona locParametro = (TipoParametroGrupoZona)pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().addTipoParametroGrupoZona(locParametro);
            return "El Par\341metro de Grupo de Zonas se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return TipoParametroGrupoZonaModel.this;
		}

	}

	public class ModificarTipoParametroGrupoZonaController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoParametroGrupoZona locParametro = (TipoParametroGrupoZona)pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().updateTipoParametroGrupoZona(locParametro);
            return "El Par\341metro de Grupo de Zonas se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return TipoParametroGrupoZonaModel.this;
		}
	}

	public class ConsultarTipoParametroGrupoZonaController extends ConsultarAbstractController {

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
			return TipoParametroGrupoZonaModel.this;
		}

	}

	public class EliminarTipoParametroGrupoZonaController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoParametroGrupoZona locParametro = (TipoParametroGrupoZona)pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().deleteTipoParametroGrupoZona(locParametro);
            return "El Par\341metro de Grupo de Zonas se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return TipoParametroGrupoZonaModel.this;
		}

	}
}
