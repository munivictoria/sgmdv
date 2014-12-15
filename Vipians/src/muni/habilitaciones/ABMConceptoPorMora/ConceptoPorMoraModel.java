/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.ABMConceptoPorMora;

import com.trascender.habilitaciones.recurso.persistent.ConceptoPorMora;
import com.trascender.habilitaciones.recurso.persistent.enums.TipoParametroInteres;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;
import java.util.ArrayList;
import javax.faces.component.UIComponent;

/**
 *
 * @author Fer Luca
 */
public class ConceptoPorMoraModel extends ABMModel{
    
    private ABMConceptoPorMora getBeanConceptoPorMora(){
        return (ABMConceptoPorMora) getRequestBean("habilitaciones$ABMConceptoPorMora$ABMConceptoPorMora");
    }
    
    private Validador getValidadorAgregarModificar() {
        
        Validador v = new Validador();
                UIComponent[] noVacios = null;
                String[] nomNoVacios = null;

                noVacios = new UIComponent[1];
                nomNoVacios = new String[1];

                int pos = 0;
                noVacios[pos] = getBeanConceptoPorMora().getTfNombre();
                nomNoVacios[pos++] = "Nombre";

                v.noSonVacios(noVacios, nomNoVacios);
                
                getBeanConceptoPorMora().mostrarEstadoObjetosUsados();
                
                return v;               
                
    }
    
    private void deshabilitarElementosConsultarEliminar(){
        
    }
    
    public class AgregarConceptoController extends AgregarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            
            ArrayList tiposParametro = (ArrayList) getBeanConceptoPorMora().obtenerObjetoDelElementoPila(1, ArrayList.class);
                
            tiposParametro.remove(TipoParametroInteres.IMPORTE_INTERES);
            
            ConceptoPorMora conceptoPorMora = (ConceptoPorMora) getBeanConceptoPorMora().obtenerObjetoDelElementoPila(0, ConceptoPorMora.class);
            
            //TipoModificador tipoModificador = (TipoModificador) this.obtenerObjetoDelElementoPila(0, TipoModificador.class);
            getBeanConceptoPorMora().getRequestBean1().setRespuestaABM(conceptoPorMora);

            return "";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            
        }

		@Override
		public ABMModel getModel() {
			return ConceptoPorMoraModel.this;
		}
        
    }
    
    public class ModificarConceptoController extends ModificarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            
            ArrayList tiposParametro = (ArrayList) getBeanConceptoPorMora().obtenerObjetoDelElementoPila(1, ArrayList.class);
                
            tiposParametro.remove(TipoParametroInteres.IMPORTE_INTERES);
            
            /*ConceptoPorMora conceptoPorMora = (ConceptoPorMora) getBeanConceptoPorMora().obtenerObjetoDelElementoPila(0, ConceptoPorMora.class);
            
            //TipoModificador tipoModificador = (TipoModificador) this.obtenerObjetoDelElementoPila(0, TipoModificador.class);
            getBeanConceptoPorMora().getRequestBean1().setRespuestaABM(conceptoPorMora);*/

            return "";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            
        }
        
        @Override
		public ABMModel getModel() {
			return ConceptoPorMoraModel.this;
		}
    }

	@Override
	public String getReglaNavegacion() {
		return "ABMConceptoPorMora";
	}

	@Override
	public String getNombreEntidad() {
		return "Concepto por Mora";
	}
    
}
