/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.catastro.ABMTipoTransaccionCatastral;

import com.trascender.catastro.recurso.persistent.TituloPropiedadParcelario.TipoTransaccionCatastral;
import com.trascender.presentacion.abstracts.controller.*;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.component.UIComponent;

/**
 *
 * @author juanma
 */
public class TipoTransaccionCatastralModel extends ABMModel{
    
    private ABMTipoTransaccionCatastral getBeanTipoTransaccionCatastral(){
        return (ABMTipoTransaccionCatastral) getRequestBean("catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral");
    }
    
    private Validador getValidadorAgregarModificar(){
        Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[1];
        String[] nomNoVacios = new String[1];
        int pos = 0;
        noVacios[pos] = getBeanTipoTransaccionCatastral().getTfNombre();
        nomNoVacios[pos++] = "Nombre";
        
        v.noSonVacios(noVacios, nomNoVacios);
        return v;
    }
    
    private void deshabilitarElementosConsultarEliminar(){
        ABMTipoTransaccionCatastral abmTransaccionCatastral = getBeanTipoTransaccionCatastral();
        abmTransaccionCatastral.getTfNombre().setDisabled(true);
    }
    
     public class AgregarTipoTransaccionCatastralController extends AgregarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            return null;
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }

		@Override
		public ABMModel getModel() {
			// TODO Auto-generated method stub
			return null;
		}
    }
    
    public class ModificarTipoTransaccionCatastralController extends ModificarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            return null;
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }

		@Override
		public ABMModel getModel() {
			// TODO Auto-generated method stub
			return null;
		}   
    }
    
    public class ConsultarTipoTransaccionCatastralController extends ConsultarAbstractController{

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
			// TODO Auto-generated method stub
			return null;
		}
        
    }
    
    public class EliminarTipoTransaccionCatastralController extends EliminarAbstractController{

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
			// TODO Auto-generated method stub
			return null;
		}
    }

	@Override
	public String getReglaNavegacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNombreEntidad() {
		// TODO Auto-generated method stub
		return null;
	}
}
