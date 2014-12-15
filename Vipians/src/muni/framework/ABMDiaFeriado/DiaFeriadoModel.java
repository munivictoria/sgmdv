/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMDiaFeriado;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author juanma
 */
public class DiaFeriadoModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMDiaFeriado";
	}

	@Override
	public String getNombreEntidad() {
		return "Dia Feriado";
	}

	private ABMDiaFeriado getBeanDiaFeriado() {
		return (ABMDiaFeriado) getRequestBean("framework$ABMDiaFeriado$ABMDiaFeriado");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		UIComponent[] fechas = new UIComponent[1];
		String[] nomFechas = new String[1];

		int pos = 0;
		noVacios[pos] = getBeanDiaFeriado().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanDiaFeriado().getTfFecha();
		nomNoVacios[pos++] = "Fecha";

		pos = 0;
		fechas[pos] = getBeanDiaFeriado().getTfFecha();
		nomFechas[pos++] = "Fecha";

		v.noSonVacios(noVacios, nomNoVacios);
		v.formatoFechaValido(fechas, nomFechas);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMDiaFeriado abmDiaFeriado = getBeanDiaFeriado();
		abmDiaFeriado.getTfNombre().setDisabled(true);
		abmDiaFeriado.getTfFecha().setDisabled(true);
	}

	public class AgregarDiaFeriadoController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DiaFeriado locDiaFeriado = (DiaFeriado) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().addDiaFeriado(locDiaFeriado);

			return "El dia feriado se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return DiaFeriadoModel.this;
		}
	}

	public class ModificarDiaFeriadoController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DiaFeriado locDiaFeriado = (DiaFeriado) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().updateDiaFeriado(locDiaFeriado);
			return "El dia feriado se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return DiaFeriadoModel.this;
		}
	}

	public class ConsultarDiaFeriadoController extends ConsultarAbstractController {

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
			return DiaFeriadoModel.this;
		}
	}

	public class EliminarDiaFeriadoController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DiaFeriado locDiaFeriado = (DiaFeriado) pObject;
			getComunicationBean().getRemoteSystemMunicipalidad().deleteDiaFeriado(locDiaFeriado);

			return "El dia feriado se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return DiaFeriadoModel.this;
		}
	}
}