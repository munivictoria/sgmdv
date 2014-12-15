
package muni.saic.grpSHPS.ABMLiquidacionSHPS;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author nico
 * 
 */
public class LiquidacionSHPSModel extends ABMModel {

	private ABMLiquidacionSHPS getBeanLiquidacionSHPS() {
		return (ABMLiquidacionSHPS) getRequestBean("saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS");
	}

	@Override
	public String getReglaNavegacion() {
		return "ABMLiquidacionSHPS";
	}

	@Override
	public String getNombreEntidad() {
		return "Liquidación de Salud, Higiene, Profilaxis y Seguridad";
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
			return LiquidacionSHPSModel.this;
		}
	}
}