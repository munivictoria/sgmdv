/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMCalendarioMunicipal;

import java.util.Calendar;

import javax.faces.component.UIComponent;

import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author Fer Luca
 */
public class CuotaModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMCuota";
	}

	@Override
	public String getNombreEntidad() {
		return "Cuota";
	}

	private ABMCuota getBeanCuota() {
		return (ABMCuota) getRequestBean("framework$ABMCalendarioMunicipal$ABMCuota");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();

		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[4];
		// UIComponent[] fechas = new UIComponent[2];
		// String[] nomFechas = new String[2];

		int pos = 0;
		noVacios[pos] = getBeanCuota().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanCuota().getTfNumero();
		nomNoVacios[pos++] = "Número";

		v.noSonVacios(noVacios, nomNoVacios);

		if (getBeanCuota().getObjectListDataProviderVencimientos().getList().isEmpty()) {
			v.getErrores().add("Debe agregar Vencimientos a la lista");
		} else {
			boolean hayVacios = false;
			for (Object o : getBeanCuota().getObjectListDataProviderVencimientos().getList()) {
				Calendar cadaVencimiento = (Calendar) o;
				if (cadaVencimiento == null) {
					hayVacios = true;
				}
			}

			if (hayVacios) {
				v.getErrores().add("Debe completar los datos de los Vencimientos");
			}
		}

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		getBeanCuota().getGroupPanel1().setRendered(false);
		getBeanCuota().getTableColumn1().setRendered(false);
		getBeanCuota().getTfNombre().setDisabled(true);
		getBeanCuota().getTfNumero().setDisabled(true);
	}

	public class AgregarCuotaController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			CuotaLiquidacion locCuota = (CuotaLiquidacion) pObject;
			getBeanCuota().getRequestBean1().setObjetoSeleccion(locCuota);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return CuotaModel.this;
		}
	}

	public class ModificarCuotaController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			CuotaLiquidacion locCuota = (CuotaLiquidacion) pObject;
			getBeanCuota().getRequestBean1().setObjetoSeleccion(locCuota);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}
		
		@Override
		public ABMModel getModel() {
			return CuotaModel.this;
		}
	}

	public class ConsultarCuotaController extends ConsultarAbstractController {

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
			return CuotaModel.this;
		}
	}
}