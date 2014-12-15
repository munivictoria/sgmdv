package muni.saic.grpTasaMenor.ABMLiquidacionTasaMenor;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author nico
 *
 */
public class LiquidacionTasaMenorModel extends ABMModel {

	private ABMLiquidacionTasaMenor getBeanLiquidacionTasaMenor() {
		return (ABMLiquidacionTasaMenor) getRequestBean("");
	}

	@Override
	public String getReglaNavegacion() {
		return "ABMLiquidacionTasaMenor";
	}

	@Override
	public String getNombreEntidad() {
		return "Liquidación de la Tasa Menor";
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {

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
			return LiquidacionTasaMenorModel.this;
		}
	}
}