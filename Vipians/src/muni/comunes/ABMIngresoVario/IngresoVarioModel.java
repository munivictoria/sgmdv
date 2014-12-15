package muni.comunes.ABMIngresoVario;

import javax.faces.component.UIComponent;

import muni.comunes.ABMIngresoVario.ABMIngresoVario;
import muni.comunes.ABMIngresoVario.IngresoVarioModel;

import com.trascender.contabilidad.recurso.persistent.ImputacionIngresoVario;
import com.trascender.contabilidad.recurso.persistent.IngresoVario;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class IngresoVarioModel extends ABMModel{
	
	@Override
	public String getReglaNavegacion() {
		return "ABMIngresoVario";
	}

	@Override
	public String getNombreEntidad() {
		return "Ingreso Vario";
	}

	private ABMIngresoVario getBeanConcepto() {
		return (ABMIngresoVario) getRequestBean("comunes$ABMIngresoVario$ABMIngresoVario");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[3];
        String[] nomNoVacios = new String[3];

        UIComponent[] fechas = new UIComponent[1];
        String[] nomFechas = new String[1];
        
        int pos = 0;
        noVacios[pos] = getBeanConcepto().getTfFechaEmision();
        nomNoVacios[pos++] = "Fecha de Emisi\363n";
        noVacios[pos] = getBeanConcepto().getTfPersona();
        nomNoVacios[pos++] = "Persona";
        noVacios[pos] = getBeanConcepto().getDdConceptoIngresoVario();
        nomNoVacios[pos++] = "Concepto";
                   
        pos = 0;
        fechas[pos] = getBeanConcepto().getTfFechaEmision();
        nomFechas[pos++] = "Fecha de Emisi\363n";
        
        IngresoVario locIngreso = getBeanConcepto().obtenerObjetoDelElementoPila(0, IngresoVario.class);
        
        if(locIngreso.getListaImputacionIngresos().isEmpty()){
        	v.getErrores().add("Debe haber al menos una Imputación en la lista");
        }
        
        for(ImputacionIngresoVario cadaImputacion : locIngreso.getListaImputacionIngresos()){
        	if(cadaImputacion.getMonto().equals(0.00d)){
        		v.getErrores().add("Debe ingresar un monto mayor a cero para cada Imputación");
        		break;
        	}
        }
        
        v.noSonVacios(noVacios, nomNoVacios);
        v.formatoFechaValido(fechas, nomFechas);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		getBeanConcepto().getTfNumero().setDisabled(true);
		getBeanConcepto().getTaObservaciones().setDisabled(true);
		getBeanConcepto().getBtnLimpiarConceptoIngresoVario().setRendered(false);
		getBeanConcepto().getBtnLimpiarPersona().setRendered(false);
		getBeanConcepto().getBtnSeleccionarConceptoIngresoVario().setRendered(false);
		getBeanConcepto().getBtnSeleccionarPersonaFisica().setRendered(false);
		getBeanConcepto().getBtnSeleccionarPersonaJuridica().setRendered(false);
		getBeanConcepto().getTfMonto().setDisabled(true);
		getBeanConcepto().getPgImputaciones().setRendered(false);
		getBeanConcepto().getDdConceptoIngresoVario().setDisabled(true);
		getBeanConcepto().getTcRbImputaciones().setRendered(false);
		getBeanConcepto().getTfMonto().setStyleClass("textFieldDisabled");
	}

	public class AgregarIngresoVarioController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			IngresoVario locIngresoVario = (IngresoVario) pObject;
			getBeanConcepto().getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().setLlave(getBeanConcepto().getSessionBean1().getLlave());
            getBeanConcepto().getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().addIngresoVario(locIngresoVario);            
            return "El Ingreso Vario se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanConcepto().getTfNumero().setRendered(false);
			getBeanConcepto().getLblNumero().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return IngresoVarioModel.this;
		}

	}

	public class ModificarIngresoVarioController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			IngresoVario locIngresoVario = (IngresoVario) pObject;
			getBeanConcepto().getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().setLlave(getBeanConcepto().getSessionBean1().getLlave());
            getBeanConcepto().getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().updateIngresoVario(locIngresoVario);            
            return "El Ingreso Vario se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return IngresoVarioModel.this;
		}
	}

	public class ConsultarIngresoVarioController extends ConsultarAbstractController {

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
			return IngresoVarioModel.this;
		}

	}

	public class EliminarIngresoVarioController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			IngresoVario locIngresoVario = (IngresoVario) pObject;
			getBeanConcepto().getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().setLlave(getBeanConcepto().getSessionBean1().getLlave());
            getBeanConcepto().getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().deleteIngresoVario(locIngresoVario);            
            return "El Ingreso Vario se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return IngresoVarioModel.this;
		}

	}
}
