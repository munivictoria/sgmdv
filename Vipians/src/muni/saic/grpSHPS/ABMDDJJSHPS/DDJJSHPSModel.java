package muni.saic.grpSHPS.ABMDDJJSHPS;

import javax.faces.component.UIComponent;

import com.trascender.habilitaciones.recurso.persistent.DeclaracionJuradaSHPS;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class DDJJSHPSModel extends ABMModel {
	private ABMDDJJSHPS getBeanDeclaracionJuradaSHPS() {
		return (ABMDDJJSHPS) getRequestBean("saic$grpSHPS$ABMDDJJSHPS$ABMDDJJSHPS");
	}

	@Override
	public String getReglaNavegacion() {
		return "ABMDDJJSHPS";
	}

	@Override
	public String getNombreEntidad() {
		return "Declaraciones Juradas de SHPS";
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		
		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];
		int pos = 0;
		noVacios[pos] = getBeanDeclaracionJuradaSHPS().getTfFecha();
		nomNoVacios[pos++] = "Fecha";
		v.noSonVacios(noVacios, nomNoVacios);

		if(getCommunicationSAICBean().getListaMontosImponiblesDeclarados() == null || getCommunicationSAICBean().getListaMontosImponiblesDeclarados().size() == 0) {
			v.getErrores().add("Deben existir Montos Imponibles Declarados.");
		}
		
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMDDJJSHPS bean = getBeanDeclaracionJuradaSHPS();
		bean.getBtnSeleccionarPersonaFisica().setRendered(false);
		bean.getBtnSeleccionarPersonaJuridica().setRendered(false);
		bean.getBtnLimpiarPersona().setRendered(false);
		bean.getBtnCargarRubros().setRendered(false);
		bean.getDdAnios().setDisabled(true);
		bean.getDdCalendarios().setDisabled(true);
		bean.getDdPeriodos().setDisabled(true);
		bean.getDdCuotas().setDisabled(true);
		bean.getTextField1().setDisabled(true);
		bean.getTextField2().setDisabled(true);
		bean.getTextField2().setStyleClass("textFieldDisabled");
		bean.getTfRetenciones().setDisabled(true);
		bean.getTfFecha().setDisabled(true);
		bean.getTfNroInscripcion().setDisabled(true);
		bean.getTfNroInscripcion().setStyle("textFieldDisabled");
		bean.getBtnSeleccionarPersonaFisica().setRendered(false);
		bean.getBtnSeleccionarPersonaJuridica().setRendered(false);
		bean.getBtnLimpiarPersona().setRendered(false);
		bean.getBtnGuardarYLiquidar().setRendered(false);
	}
	
	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DeclaracionJuradaSHPS locDDJJSHPS = (DeclaracionJuradaSHPS) pObject;
			getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(getSessionBean1().getLlave());
			getCommunicationSAICBean().getRemoteSystemRegistroValuado().addDDJJSHPS(locDDJJSHPS);
			
			return "La Declaraci\363n Jurada SHPS se agreg\363 exitosamente.";
		}
		
		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return DDJJSHPSModel.this;
		}

	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DeclaracionJuradaSHPS locDeclaracion = (DeclaracionJuradaSHPS) pObject;
			getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(getSessionBean1().getLlave());
			getCommunicationSAICBean().getRemoteSystemRegistroValuado().updateDDJJSHPS(locDeclaracion);
			
			return "La Declaraci\363n Jurada SHPS se modific\363 exitosamente.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ABMDDJJSHPS bean = getBeanDeclaracionJuradaSHPS();
			bean.getBtnSeleccionarPersonaFisica().setRendered(false);
			bean.getBtnSeleccionarPersonaJuridica().setRendered(false);
			bean.getBtnLimpiarPersona().setRendered(false);
			bean.getBtnCargarRubros().setRendered(false);
			bean.getDdAnios().setDisabled(true);
			bean.getDdCalendarios().setDisabled(true);
			bean.getDdPeriodos().setDisabled(true);
			bean.getDdCuotas().setDisabled(true);
			bean.getTfNroInscripcion().setDisabled(true);
			bean.getBtnSeleccionarPersonaFisica().setRendered(false);
			bean.getBtnSeleccionarPersonaJuridica().setRendered(false);
			bean.getBtnLimpiarPersona().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return DDJJSHPSModel.this;
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
			return DDJJSHPSModel.this;
		}

	}

	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DeclaracionJuradaSHPS locDeclaracion = (DeclaracionJuradaSHPS) pObject;
			getCommunicationSAICBean().getRemoteSystemRegistroValuado().setLlave(getSessionBean1().getLlave());
			getCommunicationSAICBean().getRemoteSystemRegistroValuado().deleteDDJJSHPS(locDeclaracion);
			
			return "La Declaraci\363n Jurada SHPS se elimin\363 exitosamente.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return DDJJSHPSModel.this;
		}

	}
}
