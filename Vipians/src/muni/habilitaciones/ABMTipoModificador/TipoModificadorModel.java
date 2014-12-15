/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.ABMTipoModificador;

import java.util.ArrayList;
import java.util.List;

import com.trascender.habilitaciones.recurso.persistent.TipoModificador;
import com.trascender.habilitaciones.recurso.persistent.VariableFormula;
import com.trascender.habilitaciones.util.MotorFormulas;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.component.UIComponent;

import org.nfunk.jep.JEP;

/**
 *
 * @author Fer Luca
 */
public class TipoModificadorModel extends ABMModel{
    
    private ABMTipoModificador getBeanTipoModificador(){
        return (ABMTipoModificador) getRequestBean("habilitaciones$ABMTipoModificador$ABMTipoModificador");
    }
    
    private Validador getValidadorAgregarModificar() {
        
        Validador v = new Validador();
        UIComponent[] noVacios = null;
        String[] nomNoVacios = null;
        UIComponent[] enteros = null;
        String[] nomEnteros = null;
        
        noVacios = new UIComponent[2];
        nomNoVacios = new String[2];
        enteros = new UIComponent[4];
        nomEnteros = new String[4];
        
        int pos = 0;
        noVacios[pos] = getBeanTipoModificador().getTfNombre();
        nomNoVacios[pos++] = "Nombre";
        noVacios[pos] = getBeanTipoModificador().getTfDiasDesde();
        nomNoVacios[pos++] = "A partir de __ d\355as";

        pos = 0;
        enteros[pos] = getBeanTipoModificador().getTfMesesDesde();
        nomEnteros[pos++] = "A partir de __ meses";
        enteros[pos] = getBeanTipoModificador().getTfDiasDesde();
        nomEnteros[pos++] = "A partir de __ d\355as";
        enteros[pos] = getBeanTipoModificador().getTfMesesHasta();
        nomEnteros[pos++] = "Hasta los __ meses";
        enteros[pos] = getBeanTipoModificador().getTfDiasHasta();
        nomEnteros[pos++] = "Hasta los __ d\355as";
        
        v.noSonVacios(noVacios, nomNoVacios);
        v.esNumero(enteros, nomEnteros);
        
        List<VariableFormula> locListaVariables = getBeanTipoModificador().obtenerObjetoDelElementoPila(2, ArrayList.class);
        
        boolean hayRepetidos = false;
		boolean hayVacios = false;

		for(VariableFormula indice : locListaVariables) {
			int i = 0;
	
			for(VariableFormula cadaVar : locListaVariables) {
				if(indice.getNombre().equals(cadaVar.getNombre())) {
					i++;
				}
			}
	
			if(i > 1 && !hayRepetidos) {
				v.getErrores().add("Existen variables repetidas");
				hayRepetidos = true;
			}
			if((indice.getNombre().equals("") || indice.getExpresion().equals("")) && !hayVacios) {
				v.getErrores().add("Existen variables con campos vacíos");
				hayVacios = true;
			}
	
			if(hayVacios && hayRepetidos) {
				break;
			}
		}		
		
		getBeanTipoModificador().mostrarEstadoObjetosUsados();
        
        return v;
    }
    
    private void deshabilitarElementosConsultarEliminar(){
        
    }
    
    public class AgregarTipoModificadorController extends AgregarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            
            TipoModificador tipoModificador = (TipoModificador) getBeanTipoModificador().obtenerObjetoDelElementoPila(0, TipoModificador.class);
            getBeanTipoModificador().getRequestBean1().setRespuestaABM(tipoModificador);
            
            //info("El Modificador se agreg\363 exitosamente.");
            return "";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            
        }

		@Override
		public ABMModel getModel() {
			return TipoModificadorModel.this;
		}
    }
    
    public class ModificarTipoModificadorController extends ModificarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            /*
            TipoModificador tipoModificador = (TipoModificador) getBeanTipoModificador().obtenerObjetoDelElementoPila(0, TipoModificador.class);
            getBeanTipoModificador().getRequestBean1().setRespuestaABM(tipoModificador);*/
            
            return "";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            
        }
        
        @Override
		public ABMModel getModel() {
			return TipoModificadorModel.this;
		}
    }

	@Override
	public String getReglaNavegacion() {
		return "ABMTipoModificador";
	}

	@Override
	public String getNombreEntidad() {
		return "Modificador";
	}
}
