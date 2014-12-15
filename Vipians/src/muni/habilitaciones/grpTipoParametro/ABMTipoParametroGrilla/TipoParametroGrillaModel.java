package muni.habilitaciones.grpTipoParametro.ABMTipoParametroGrilla;

import javax.faces.component.UIComponent;

import com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla.TipoParametroGrilla;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class TipoParametroGrillaModel extends ABMModel{

	@Override
	public String getReglaNavegacion() {
		// TODO Auto-generated method stub
		return "ABMTipoParametroGrilla";
	}

	@Override
	public String getNombreEntidad() {
		// TODO Auto-generated method stub
		return "Par\341metro de Grilla";
	}
	
	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];
		
		int pos = 0;
		noVacios[pos] = getBeanTipoParametroGrilla().getTfNombre();
		nomNoVacios[pos++] = "Nombre";

		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}
	
	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoParametroGrilla locTipoParametroGrilla = (TipoParametroGrilla) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(getSessionBean1().getLlave());
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().addTipoParametroGrilla(locTipoParametroGrilla);
			return "El Tipo de parametro se agreg\363 correctamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return TipoParametroGrillaModel.this;
		}
	}
	
	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoParametroGrilla locTipoParametroGrilla = (TipoParametroGrilla) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(getSessionBean1().getLlave());
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().updateTipoParametroGrilla(locTipoParametroGrilla);
			return "El Tipo de parametro se modific\363 correctamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return TipoParametroGrillaModel.this;
		}
	}
	
	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoParametroGrilla locTipoParametroGrilla = (TipoParametroGrilla) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(getSessionBean1().getLlave());
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().deleteTipoParametroGrilla(locTipoParametroGrilla);
			return "El Tipo de parametro se elimin\363 correctamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return TipoParametroGrillaModel.this;
		}
	}

	public class ConsultarController extends ConsultarAbstractController {

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
			return TipoParametroGrillaModel.this;
		}
	}
	
	private ABMTipoParametroGrilla getBeanTipoParametroGrilla() {
		return (ABMTipoParametroGrilla) getRequestBean("habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrilla");
	}
	
	private void deshabilitarElementosConsultarEliminar() {
		getBeanTipoParametroGrilla().getTfNombre().setDisabled(true);
		getBeanTipoParametroGrilla().getTfNombreVariable().setDisabled(true);
		getBeanTipoParametroGrilla().getTaDescripcion().setDisabled(true);
		getBeanTipoParametroGrilla().getTableColumn1().setRendered(false);
		getBeanTipoParametroGrilla().getTfNombreVariable().setStyleClass("textFieldDisabled");
		getBeanTipoParametroGrilla().getTaDescripcion().setStyleClass("textFieldDisabled");
		getBeanTipoParametroGrilla().getBtnAgregarVariable().setRendered(false);
        getBeanTipoParametroGrilla().getBtnQuitarVariable().setRendered(false);
        getBeanTipoParametroGrilla().getTaCondicion().setDisabled(true);
        getBeanTipoParametroGrilla().getTaCondicion().setStyleClass("textFieldDisabled");
        getBeanTipoParametroGrilla().getTaValor().setDisabled(true);
        getBeanTipoParametroGrilla().getTaValor().setStyleClass("textFieldDisabled");
        getBeanTipoParametroGrilla().getBtnAgregarFila().setRendered(false);
        getBeanTipoParametroGrilla().getBtnModificarFila().setRendered(false);
        getBeanTipoParametroGrilla().getBtnQuitarFila().setRendered(false);
        getBeanTipoParametroGrilla().getStSeparadorLocal().setRendered(false);
	}
}
