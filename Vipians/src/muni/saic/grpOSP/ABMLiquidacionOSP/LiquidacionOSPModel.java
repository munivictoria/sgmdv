
package muni.saic.grpOSP.ABMLiquidacionOSP;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author nico
 *
 */
public class LiquidacionOSPModel extends ABMModel {

	private ABMLiquidacionOSP getBeanLiquidacionOSP() {
		return (ABMLiquidacionOSP) getRequestBean("saic$grpOSP$ABMLiquidacionOSP$ABMLiquidacionOSP");
	}

	@Override
	public String getReglaNavegacion() {
		return "ABMLiquidacionOSP";
	}

	@Override
	public String getNombreEntidad() {
		return "Liquidación de Obras y Servicios Públicos";
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
			return LiquidacionOSPModel.this;
		}
	}
}