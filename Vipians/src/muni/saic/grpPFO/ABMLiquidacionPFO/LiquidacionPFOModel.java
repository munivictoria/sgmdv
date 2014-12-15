package muni.saic.grpPFO.ABMLiquidacionPFO;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author nico
 *
 */
public class LiquidacionPFOModel extends ABMModel{

	private ABMLiquidacionPFO getBeanLiquidacionPFO() {
		return (ABMLiquidacionPFO) getRequestBean("saic$grpPFO$ABMLiquidacionPFO$ABMLiquidacionPFO");
	}

	@Override
	public String getReglaNavegacion() {
		return "ABMLiquidacionPFO";
	}

	@Override
	public String getNombreEntidad() {
		return "Liquidación de Plan de Financiación de Obras";
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
			return LiquidacionPFOModel.this;
		}
	}
}