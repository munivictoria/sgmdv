/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.grpOSP.ABMConsumoBasico;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.habilitaciones.recurso.persistent.osp.ConsumoBasico;
import com.trascender.presentacion.abstracts.controller.*;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.component.UIComponent;

/**
 *
 * @author pedro
 */
public class ConsumoBasicoModel  extends ABMModel {
 
    private ABMConsumoBasico getABMConsumoBasico(){
        return (ABMConsumoBasico) getRequestBean("habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico");
    }
     private Validador getValidadorAgregarModificar(){
         Validador v = new Validador();
                UIComponent[] noVacios = new UIComponent[4];
                String[] nomNoVacios = new String[4];
                
                int pos = 0;
                noVacios[pos] = this.getABMConsumoBasico().getTfSuperficieMejorasMinimo();
                nomNoVacios[pos++] = "M\355nimo de Superficie de Mejoras";
                noVacios[pos] = this.getABMConsumoBasico().getTfSuperficieMejorasMaximo();
                nomNoVacios[pos++] = "M\341ximo de Superficie de Mejoras";
                noVacios[pos] = this.getABMConsumoBasico().getTfConsumoInicial();
                nomNoVacios[pos++] = "Consumo Inicial";
                noVacios[pos] = this.getABMConsumoBasico().getTfConsumoPorExcedente();
                nomNoVacios[pos++] = "Consumo por Excedente";
                
                v.noSonVacios(noVacios, nomNoVacios);
                v.sonFlotantes(noVacios, nomNoVacios);
                v.sonPositivos(noVacios, nomNoVacios);
                
                Double minimo = Conversor.getDoubleDeString(this.getABMConsumoBasico().getTfSuperficieMejorasMinimo().getText().toString());
                Double maximo = Conversor.getDoubleDeString(this.getABMConsumoBasico().getTfSuperficieMejorasMaximo().getText().toString());
                
                if (minimo != null && maximo != null && minimo >= maximo){
                    String msg = "'M\355nimo de Superficie de Mejoras' no puede ser mayor que 'M\341ximo de Superficie de Mejoras'.";
                    v.getErrores().add(msg);
                    return null;
                }
                
                return v;
     }
     
     private void deshabilitarElementosConsultarEliminar(){
        ABMConsumoBasico abmConsumoBasico = getABMConsumoBasico();
        abmConsumoBasico.getTfConsumoInicial().setDisabled(true);
        abmConsumoBasico.getTfConsumoPorExcedente().setDisabled(true);
        abmConsumoBasico.getTfSuperficieMejorasMaximo().setDisabled(true);
        abmConsumoBasico.getTfSuperficieMejorasMinimo().setDisabled(true);
    }
     
     public class AgregarConsumoBasicoController extends AgregarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            ConsumoBasico locConsumoBasico = (ConsumoBasico) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().setLlave(getSessionBean1().getLlave());                
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().addConsumoBasico(locConsumoBasico);
            
            return "El consumo b\341sico se agreg\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }

		@Override
		public ABMModel getModel() {
			return ConsumoBasicoModel.this;
		}
         
     }
     
     public class ModificarConsumoBasicoController extends ModificarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            ConsumoBasico locConsumoBasico = (ConsumoBasico) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().setLlave(getSessionBean1().getLlave());                
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().updateConsumoBasico(locConsumoBasico);
            
            return "El Consumo B\341sico se modific\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }   
        
        @Override
		public ABMModel getModel() {
			return ConsumoBasicoModel.this;
		}
    }
     
     public class ConsultarConsumoBasicoController extends ConsultarAbstractController{

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
            deshabilitarElementosConsultarEliminar();
        }
         
        @Override
		public ABMModel getModel() {
			return ConsumoBasicoModel.this;
		}
     }
     
     public class EliminarConsumoBasicoController extends EliminarAbstractController{

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            ConsumoBasico locConsumoBasico = (ConsumoBasico) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().setLlave(getSessionBean1().getLlave());                
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoOSP().deleteConsumoBasico(locConsumoBasico);
            
            return "El Consumo B\341sico se elimin\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            deshabilitarElementosConsultarEliminar();
        }
         
        @Override
		public ABMModel getModel() {
			return ConsumoBasicoModel.this;
		}
     }

	@Override
	public String getReglaNavegacion() {
		return "ABMConsumoBasico";
	}
	@Override
	public String getNombreEntidad() {
		return "Consumo B\341sico";
	}
}
