package muni.saic.grpAutomotor;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author nico
 *
 */
public class LiquidacionAutomotorModel extends ABMModel {

	private ABMLiquidacionAutomotor getBeanLiquidacionAutomotor() {
		return (ABMLiquidacionAutomotor) getRequestBean("saic$grpAutomotor$ABMLiquidacionAutomotor$ABMLiquidacionAutomotor");
	}

	@Override
	public String getReglaNavegacion() {
		return "ABMLiquidacionAutomotor";
	}

	@Override
	public String getNombreEntidad() {
		return "Liquidación de Automotor";
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
			return LiquidacionAutomotorModel.this;
		}
	}	
}