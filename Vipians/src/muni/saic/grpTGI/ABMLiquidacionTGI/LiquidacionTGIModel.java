
package muni.saic.grpTGI.ABMLiquidacionTGI;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author nico
 * 
 */
public class LiquidacionTGIModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMLiquidacionTGI";
	}

	@Override
	public String getNombreEntidad() {
		return "Liquidación de la Tasa General Inmobiliaria";
	}

	private ABMLiquidacionTGI getBeanLiquidacionTGI() {
		return (ABMLiquidacionTGI) getRequestBean("saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI");
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
			return LiquidacionTGIModel.this;
		}
	}
}
