/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMCalendarioMunicipal;

import javax.faces.component.UIComponent;

import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author Fer Luca
 */
public class PeriodoModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMPeriodo";
	}

	@Override
	public String getNombreEntidad() {
		return "Periodo";
	}

	private ABMPeriodo getBeanPeriodo() {
		return (ABMPeriodo) getRequestBean("framework$ABMCalendarioMunicipal$ABMPeriodo");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();

		UIComponent[] noVacios = new UIComponent[3];
		String[] nomNoVacios = new String[3];
		UIComponent[] fechas = new UIComponent[1];
		String[] nomFechas = new String[1];

		int pos = 0;
		noVacios[pos] = getBeanPeriodo().getTfNombrePeriodo();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanPeriodo().getTfNumeroPeriodo();
		nomNoVacios[pos++] = "Número";
		noVacios[pos] = getBeanPeriodo().getTfFechaInicioPeriodo();
		nomNoVacios[pos++] = "Fecha de Inicio";

		pos = 0;
		fechas[pos] = getBeanPeriodo().getTfFechaInicioPeriodo();
		nomFechas[pos++] = "Fecha Inicio";

		v.noSonVacios(noVacios, nomNoVacios);
		v.formatoFechaValido(fechas, nomFechas);

		if (getBeanPeriodo().getObjectListDataProviderCuotas().getList().isEmpty()) {
			v.getErrores().add("Debe agregar Cuotas a la lista");
		} else {
			boolean hayVacios = false;
			for (Object o : getBeanPeriodo().getObjectListDataProviderCuotas().getList()) {
				CuotaLiquidacion cadaCuota = (CuotaLiquidacion) o;
				if (cadaCuota.getNombre().equals("") || cadaCuota.getNumero() == null) {
					hayVacios = true;
				}
			}

			if (hayVacios) {
				v.getErrores().add("Debe completar los datos de todas las Cuotas");
			}
		}

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		getBeanPeriodo().getGroupPanel1().setRendered(false);
		getBeanPeriodo().getTableColumn1().setRendered(false);
		getBeanPeriodo().getTfFechaInicioPeriodo().setDisabled(true);
		getBeanPeriodo().getTfFechaVencimiento().setDisabled(true);
		getBeanPeriodo().getTfNombrePeriodo().setDisabled(true);
		getBeanPeriodo().getTfNumeroPeriodo().setDisabled(true);
	}

	public class AgregarPeriodoController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			PeriodoLiquidacion locPeriodo = (PeriodoLiquidacion) pObject;
			getBeanPeriodo().getRequestBean1().setObjetoSeleccion(locPeriodo);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return PeriodoModel.this;
		}
	}

	public class ModificarPeriodoController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return PeriodoModel.this;
		}
	}

	public class ConsultarPeriodoController extends ConsultarAbstractController {

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
			return PeriodoModel.this;
		}
	}
}