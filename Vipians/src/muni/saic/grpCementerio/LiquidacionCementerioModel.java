package muni.saic.grpCementerio;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author nico
 *
 */
public class LiquidacionCementerioModel extends ABMModel{

	private ABMLiquidacionCementerio getBeanLiquidacionCementerio() {
		return (ABMLiquidacionCementerio) getRequestBean("");
	}

	@Override
	public String getReglaNavegacion() {
		return "ABMLiquidacionCementerio";
	}

	@Override
	public String getNombreEntidad() {
		return "Liquidación de Cementerio";
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
			return LiquidacionCementerioModel.this;
		}
	}
}