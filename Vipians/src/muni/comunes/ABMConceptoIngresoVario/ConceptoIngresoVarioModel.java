package muni.comunes.ABMConceptoIngresoVario;

import javax.faces.component.UIComponent;

import com.trascender.contabilidad.recurso.persistent.ConceptoIngresoVario;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class ConceptoIngresoVarioModel extends ABMModel{

	@Override
	public String getReglaNavegacion() {
		return "ABMConceptoIngresoVario";
	}

	@Override
	public String getNombreEntidad() {
		return "Concepto de Ingreso Vario";
	}

	private ABMConceptoIngresoVario getBeanConcepto() {
		return (ABMConceptoIngresoVario) getRequestBean("comunes$ABMConceptoIngresoVario$ABMConceptoIngresoVario");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[1];
        String[] nomNoVacios = new String[1];
//        UIComponent[] flotantes = new UIComponent[1];
//        String[] nomFlotantes = new String[1];
        
        int pos = 0;
        noVacios[pos] = getBeanConcepto().getTfNombre();
        nomNoVacios[pos++] = "Nombre";
//        noVacios[pos] = getBeanConcepto().getTfValorPorDefecto();
//        nomNoVacios[pos++] = "Valor por Defecto";
        
//        pos = 0;
//        flotantes[pos] = getBeanConcepto().getTfValorPorDefecto();
//        nomFlotantes[pos++] = "Valor por Defecto";
        
        v.noSonVacios(noVacios, nomNoVacios);
//        v.sonFlotantes(flotantes, nomFlotantes);
//        v.sonPositivos(flotantes, nomFlotantes);
        
        ConceptoIngresoVario locConcepto = getBeanConcepto().obtenerObjetoDelElementoPila(0, ConceptoIngresoVario.class);
        
        if(locConcepto.getListaRelaConceptoIngresoVarioCuenta().isEmpty()){
        	v.getErrores().add("Debe haber al menos una Cuenta en la lista");
        }

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		getBeanConcepto().getTfNombre().setDisabled(true);
//		getBeanConcepto().getTfValorPorDefecto().setDisabled(true);
		getBeanConcepto().getTaDescripcion().setDisabled(true);
		getBeanConcepto().getPgCuentas().setRendered(false);
		getBeanConcepto().getPgRoles().setRendered(false);
		getBeanConcepto().getPgUsuarios().setRendered(false);
		getBeanConcepto().getTcRbCuentas().setRendered(false);
		getBeanConcepto().getTcRbRoles().setRendered(false);
		getBeanConcepto().getTcRbUsuarios().setRendered(false);
		getBeanConcepto().getCkbObligatoria().setDisabled(true);
	}

	public class AgregarConceptoIngresoVarioController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ConceptoIngresoVario locConcepto = (ConceptoIngresoVario) pObject;
			getBeanConcepto().getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().setLlave(getBeanConcepto().getSessionBean1().getLlave());
			getBeanConcepto().getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().addConceptoIngresoVario(locConcepto);
            return "El Concepto de Ingreso Vario se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ConceptoIngresoVarioModel.this;
		}

	}

	public class ModificarConceptoIngresoVarioController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ConceptoIngresoVario locConcepto = (ConceptoIngresoVario) pObject;
			getBeanConcepto().getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().setLlave(getBeanConcepto().getSessionBean1().getLlave());
			getBeanConcepto().getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().updateConceptoIngresoVario(locConcepto);
            return "El Concepto de Ingreso Vario se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ConceptoIngresoVarioModel.this;
		}
	}

	public class ConsultarConceptoIngresoVarioController extends ConsultarAbstractController {

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
			return ConceptoIngresoVarioModel.this;
		}

	}

	public class EliminarConceptoIngresoVarioController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ConceptoIngresoVario locConcepto = (ConceptoIngresoVario) pObject;
			getBeanConcepto().getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().setLlave(getBeanConcepto().getSessionBean1().getLlave());
			getBeanConcepto().getCommunicationCajaBean().getRemoteSystemAdministracionIngresos().deleteConceptoIngresoVario(locConcepto);
            return "El Concepto de Ingreso Vario se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return ConceptoIngresoVarioModel.this;
		}

	}
}
