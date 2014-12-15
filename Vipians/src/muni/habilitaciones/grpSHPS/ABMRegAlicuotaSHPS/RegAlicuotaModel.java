/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.grpSHPS.ABMRegAlicuotaSHPS;

import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.presentacion.abstracts.controller.*;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.component.UIComponent;

/**
 *
 * @author fer
 */
public class RegAlicuotaModel extends ABMModel{
    
    private ABMRegAlicuotaSHPS getRegAlicuotaBean(){
        return (ABMRegAlicuotaSHPS) getRequestBean("habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$ABMRegAlicuotaSHPS");
    }
    
    private void ocultarDeshabilitar(){
        getRegAlicuotaBean().getTfCodigoCiiu().setDisabled(true);
        getRegAlicuotaBean().getTfMinimo().setDisabled(true);
        getRegAlicuotaBean().getTfValor().setDisabled(true);
        getRegAlicuotaBean().getDdTipo().setDisabled(true);
        getRegAlicuotaBean().getBtnSeleccionarCodigoCiiu().setRendered(false);
        getRegAlicuotaBean().getTaNombre().setDisabled(true);
        
        getRegAlicuotaBean().getPanelAtributoDinamico().deshabilitarCampos();
        getRegAlicuotaBean().getTaComentarioLogAuditoria().setRendered(false);
		getRegAlicuotaBean().getLblComentarioLogAuditoria().setRendered(false);
    }
    
    public Validador getValidadorAgregarModificar(){
        Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[3];
        String[] nomNoVacios = new String[3];
        UIComponent[] flotantes = new UIComponent[2];
        String[] nomFlotantes = new String[2];

        int pos = 0;
        noVacios[pos] = getRegAlicuotaBean().getTfCodigoCiiu();
        nomNoVacios[pos++] = "C\363digo Ciiu";
        noVacios[pos] = getRegAlicuotaBean().getDdTipo();
        nomNoVacios[pos++] = "Tipo Al\355cuota";
        noVacios[pos] = getRegAlicuotaBean().getTfValor();
        nomNoVacios[pos++] = "Al\355cuota";

        pos = 0;
        flotantes[pos] = getRegAlicuotaBean().getTfValor();
        nomFlotantes[pos++] = "Al\355cuota";
        flotantes[pos] = getRegAlicuotaBean().getTfMinimo();
        nomFlotantes[pos++] = "M\355nimo";

        v.noSonVacios(noVacios, nomNoVacios);
        v.sonFlotantes(flotantes, nomFlotantes);
        
//        ArrayList atributosDinamicos = (ArrayList) getRegAlicuotaBean().obtenerObjetoDelElementoPila(1, ArrayList.class);
//
//                Validador v2 = panelAtributoDinamico.validarCampos(atributosDinamicos);
//                if (v2.getErrores().size() > 0) {
//                    
//                    for (int i = 0; i < v2.getErrores().size(); i++) {
//                        v.getErrores().add(v2.getErrores().get(i));
//                    }
        
        return v;
    }
    
    public class AgregarController extends AgregarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            RegAlicuota locRegAlicuota = (RegAlicuota) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemAlicuota().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemAlicuota().addRegistroAlicuota(locRegAlicuota);
            return "El Registro de Alicuota se agreg\363 exitosamente.";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }

		@Override
		public ABMModel getModel() {
			return RegAlicuotaModel.this;
		}
    }
    
    public class ModificarController extends ModificarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            RegAlicuota locRegAlicuota = (RegAlicuota) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemAlicuota().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemAlicuota().updateRegistroAlicuota(locRegAlicuota);
            return "El Registro de Alicuota se modific\363 exitosamente.";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }
        
        @Override
		public ABMModel getModel() {
			return RegAlicuotaModel.this;
		}
    }
    
    public class EliminarController extends EliminarAbstractController{

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            RegAlicuota locRegAlicuota = (RegAlicuota) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemAlicuota().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemAlicuota().deleteRegistroAlicuota(locRegAlicuota);
            return "El Registro de Alicuota se elimin\363 exitosamente.";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            ocultarDeshabilitar();
        }
        
        @Override
		public ABMModel getModel() {
			return RegAlicuotaModel.this;
		}
    }
    
    public class ConsultarController extends ConsultarAbstractController{

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            return "";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {            
            ocultarDeshabilitar();         
            
        }
        
        @Override
		public ABMModel getModel() {
			return RegAlicuotaModel.this;
		}
    }

	@Override
	public String getReglaNavegacion() {
		return "ABMRegAlicuotaSHPS";
	}

	@Override
	public String getNombreEntidad() {
		return "Registro de Alicuota";
	}
    
}
