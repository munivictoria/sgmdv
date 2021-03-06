
package muni.saic.grpArrendamiento.ABMLiquidacionArrendamiento;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author nico
 * 
 */
public class LiquidacionArrandamientoModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMLiquidacionArrendamiento";
	}

	@Override
	public String getNombreEntidad() {
		return "Liquidación de la Tasa Arrendamiento";
	}

	private ABMLiquidacionArrendamiento getBeanLiquidacionArrendamiento() {
		return (ABMLiquidacionArrendamiento) getRequestBean("saic$grpArrendamiento$ABMLiquidacionArrendamiento$ABMLiquidacionArrendamiento");
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
			return LiquidacionArrandamientoModel.this;
		}
	}
}
