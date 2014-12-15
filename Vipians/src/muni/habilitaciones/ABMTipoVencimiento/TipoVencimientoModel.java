/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.ABMTipoVencimiento;

import com.trascender.habilitaciones.recurso.persistent.TipoVencimiento;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.component.UIComponent;

/**
 *
 * @author Fer Luca
 */
public class TipoVencimientoModel extends ABMModel{
    
    private ABMTipoVencimiento getBeanTipoVencimiento(){
        return (ABMTipoVencimiento) getRequestBean("habilitaciones$ABMTipoVencimiento$ABMTipoVencimiento");
    }
    
    private Validador getValidadorAgregarModificar() {
        
        Validador v = new Validador();
                UIComponent[] noVacios = null;
                String[] nomNoVacios = null;
                UIComponent[] enteros = null;
                String[] nomEnteros = null;
                              
                noVacios = new UIComponent[3];
                nomNoVacios = new String[3];
                enteros = new UIComponent[2];
                nomEnteros = new String[2];
                             
                int pos = 0;
                noVacios[pos] = getBeanTipoVencimiento().getTfNombre();
                nomNoVacios[pos++] = "Nombre";
                noVacios[pos] = getBeanTipoVencimiento().getTfMeses();
                nomNoVacios[pos++] = "A partir de __ meses";
                noVacios[pos] = getBeanTipoVencimiento().getTfDias();
                nomNoVacios[pos++] = "A partir de __ d\355as";

                pos = 0;
                enteros[pos] = getBeanTipoVencimiento().getTfMeses();
                nomEnteros[pos++] = "A partir de __ meses";
                enteros[pos] = getBeanTipoVencimiento().getTfDias();
                nomEnteros[pos++] = "A partir de __ d\355as";
                
                v.noSonVacios(noVacios, nomNoVacios);
                v.esNumero(enteros, nomEnteros);
                
                getBeanTipoVencimiento().mostrarEstadoObjetosUsados();
                
                return v;
    }
    
    private void deshabilitarElementosConsultarEliminar(){
        
    }
    
    public class AgregarTipoVencimientoController extends AgregarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            TipoVencimiento tipoVencimiento = (TipoVencimiento) getBeanTipoVencimiento().obtenerObjetoDelElementoPila(0, TipoVencimiento.class);
            getBeanTipoVencimiento().getRequestBean1().setRespuestaABM(tipoVencimiento);
            //info("El Vencimiento se agreg\363 exitosamente.");
            return "";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            
        }

		@Override
		public ABMModel getModel() {
			return TipoVencimientoModel.this;
		}
        
    }
    
    public class ModificarTipoVencimientoController extends ModificarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            /*TipoVencimiento tipoVencimiento = (TipoVencimiento) getBeanTipoVencimiento().obtenerObjetoDelElementoPila(0, TipoVencimiento.class);
            getBeanTipoVencimiento().getRequestBean1().setRespuestaABM(tipoVencimiento);*/
            return "";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            
        }
        
        @Override
		public ABMModel getModel() {
			return TipoVencimientoModel.this;
		}
    }

	@Override
	public String getReglaNavegacion() {
		return "ABMTipoVencimiento";
	}

	@Override
	public String getNombreEntidad() {
		return "Vencimiento";
	}
    
    
}
