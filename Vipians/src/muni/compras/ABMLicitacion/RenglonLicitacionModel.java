package muni.compras.ABMLicitacion;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.suministros.LineaContratacion;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;


public class RenglonLicitacionModel extends ABMModel{
    
	@Override
	public String getReglaNavegacion() {
		return "ABMRenglonLicitacion";
	}

	@Override
	public String getNombreEntidad() {
		return "Renglon Licitacion";
	}
	
    private ABMRenglonLicitacion getBeanRenglonLicitacion(){
        return (ABMRenglonLicitacion) getRequestBean("compras$ABMLicitacion$ABMRenglonLicitacion");
    }
    
    private Validador getValidadorAgregarModificar(){
        
        com.trascender.presentacion.validadores.Validador v = new com.trascender.presentacion.validadores.Validador();
                UIComponent[] noVacios = new UIComponent[3];
                String[] nomNoVacios = new String[3];
                UIComponent[] flotantes = new UIComponent[1];
                String[] nomFlotantes = new String[1];

                int pos = 0;
                noVacios[pos] = getBeanRenglonLicitacion().getTfNombre();
                nomNoVacios[pos++] = "Nombre";
                noVacios[pos] = getBeanRenglonLicitacion().getTfUnidadMedida();
                nomNoVacios[pos++] = "Unidad de Medida";
                noVacios[pos] = getBeanRenglonLicitacion().getTfCantidad();
                nomNoVacios[pos++] = "Cantidad";

                pos = 0;
                flotantes[pos] = getBeanRenglonLicitacion().getTfCantidad();
                nomFlotantes[pos++] = "Cantidad";

                v.noSonVacios(noVacios, nomNoVacios);
                v.sonFlotantes(flotantes, nomFlotantes);
                v.sonPositivos(flotantes, nomFlotantes);
                
                return v;
                
    }
    
    private void deshabilitarElementosConsultarEliminar(){
        getBeanRenglonLicitacion().getTfNombre().setDisabled(true);
        getBeanRenglonLicitacion().getTfCantidad().setDisabled(true);
        getBeanRenglonLicitacion().getTfUnidadMedida().setDisabled(true);
        getBeanRenglonLicitacion().getTaDescripcion().setDisabled(true);
        
        getBeanRenglonLicitacion().getBtnCancelar().setRendered(false);
        getBeanRenglonLicitacion().getBtnGuardar().setRendered(false);
        getBeanRenglonLicitacion().getBtnLimpiarUnidadMedida().setRendered(false);
        getBeanRenglonLicitacion().getBtnSeleccionarUnidadMedida().setRendered(false);
    
    }
    
    public class AgregarController extends AgregarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            System.out.println(pObject);
            System.out.println("Request bean: " + getRequestBean1());
            getBeanRenglonLicitacion().getRequestBean1().setObjetoSeleccion((LineaContratacion) pObject);
            return "";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {            
        }

		@Override
		public ABMModel getModel() {
			return RenglonLicitacionModel.this;
		}   
    }
    
    public class ModificarController extends ModificarAbstractController{
        
        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            getRequestBean1().setObjetoSeleccion ((LineaContratacion) pObject);
            return "";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        } 
        
        @Override
		public ABMModel getModel() {
			return RenglonLicitacionModel.this;
		}   
    }    
}
