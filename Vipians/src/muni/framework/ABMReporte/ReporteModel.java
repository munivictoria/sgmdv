/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.framework.ABMReporte;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.reporteDinamico.ParametroReporte;
import com.trascender.framework.recurso.persistent.reporteDinamico.Reporte;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class ReporteModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMReporte";
	}

	@Override
	public String getNombreEntidad() {
		return "Reporte";
	}

	private ABMReporte getBeanReporte() {
		return (ABMReporte) getRequestBean("framework$ABMReporte$ABMReporte");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		int pos = 0;
		noVacios[pos] = getBeanReporte().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanReporte().getTfNombreJasper();
		nomNoVacios[pos++] = "Nombre Jasper";

		v.noSonVacios(noVacios, nomNoVacios);

		Reporte locReporte = (Reporte) getBeanReporte().obtenerObjetoDelElementoPila(0);
		long valor = 1;
		for(ParametroReporte cadaParam : locReporte.getListaParametroReporte()) {
			if(cadaParam.getNombre() == null || cadaParam.getNombre().length() == 0) {
				v.getErrores().add("Los parámetros del reporte deben tener un nombre.");
				break;
			}
			if(cadaParam.getNombreAtributo() == null || cadaParam.getNombreAtributo().length() == 0) {
				v.getErrores().add("Los parámetros del reporte deben tener un nombre de atributo.");
				break;
			}
			if(cadaParam.getOrden() == null || cadaParam.getOrden() < 1) {
				v.getErrores().add("Los parámetros del reporte deben tener un orden.");
				break;
			}

			valor *= cadaParam.getOrden();
		}

		if(valor != Util.factorial(locReporte.getListaParametroReporte().size())) {
			v.getErrores().add("Los parámetros del reporte no están correctamente ordenados.");
		}

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMReporte abmReporte = getBeanReporte();
		abmReporte.getTfNombreJasper().setDisabled(true);
		abmReporte.getTfNombre().setDisabled(true);
		abmReporte.getDdTipo().setDisabled(true);
		abmReporte.getPgUsuarios().setRendered(false);
		abmReporte.getTcRbUsuarios().setRendered(false);
		abmReporte.getGroupPanelParametro().setRendered(false);
		abmReporte.getTableColumnRB().setRendered(false);
		abmReporte.getTfNombreParametro().setDisabled(true);
		abmReporte.getDdTipoParametro().setDisabled(true);
		abmReporte.getCbRequerido().setDisabled(true);
		abmReporte.getTaComentarioLogAuditoria().setRendered(false);
		abmReporte.getLblComentarioLogAuditoria().setRendered(false);
		abmReporte.getBtnBuscarRecurso().setRendered(false);
		abmReporte.getBtnLimpiarRecurso().setRendered(false);
		abmReporte.getDdSeleccionaEntidad().setDisabled(true);
		abmReporte.getTfNombreAtributo().setDisabled(true);
		abmReporte.getTfOrden().setDisabled(true);
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Reporte locReporte = (Reporte) pObject;
			getComunicationBean().getRemoteSystemParametro().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemParametro().addReporte(locReporte);

			return "El Reporte se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanReporte().getTablaLogs().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return ReporteModel.this;
		}

	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Reporte locReporte = (Reporte) pObject;
			getComunicationBean().getRemoteSystemParametro().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemParametro().updateReporte(locReporte);

			return "El Reporte se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ReporteModel.this;
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
			return ReporteModel.this;
		}

	}

	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Reporte locReporte = (Reporte) pObject;
			getComunicationBean().getRemoteSystemParametro().deleteReporte(locReporte);

			return "El Reporte se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return ReporteModel.this;
		}

	}

}