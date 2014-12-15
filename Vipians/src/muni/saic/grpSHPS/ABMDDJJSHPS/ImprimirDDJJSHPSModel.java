package muni.saic.grpSHPS.ABMDDJJSHPS;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

public class ImprimirDDJJSHPSModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ImprimirDDJJSHPS";
	}

	@Override
	public String getNombreEntidad() {
		return "Imprimir DDJJSHPS";
	}
	
	public ImprimirDDJJSHPS getBeanImprimirDDJJSHPS(){
		return (ImprimirDDJJSHPS) getRequestBean("saic$grpSHPS$ABMDDJJSHPS$ImprimirDDJJSHPS");
	}
	
	private Validador getValidadorAgregarModificar(){
		return null;
	}

	private void deshabilitarElementosConsultarEliminar(){
		
	}

	public class ImprimirDDJJSHPSController extends AgregarAbstractController {

	    @Override
	    public String getCodigoColores(){
	        return Constantes.COLORES_CONSULTAR ;
	    }
		
		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanImprimirDDJJSHPS().getStTitulo().setText("Imprimir DDJJ SHPS");
		}

		@Override
		public ABMModel getModel() {
			return ImprimirDDJJSHPSModel.this;
		}

	

	}


}
