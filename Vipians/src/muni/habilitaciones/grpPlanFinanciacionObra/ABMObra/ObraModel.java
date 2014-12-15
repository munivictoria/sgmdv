/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.grpPlanFinanciacionObra.ABMObra;

import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.presentacion.abstracts.controller.*;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.component.UIComponent;

/**
 *
 * @author pedro
 */
public class ObraModel extends ABMModel{
    private ABMObra getABMObra(){
        return (ABMObra) getRequestBean("habilitaciones$grpPlanFinanciacionObra$ABMObra$ABMObra");
    }
     private Validador getValidadorAgregarModificar(){
         System.out.println("\n\nEntro a validador\n\n");
        Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[6];
        String[] nomNoVacios = new String[6];
        UIComponent[] flotantes = new UIComponent[3];
        String[] nomFlotantes = new String[3];
        UIComponent[] esNumero = new UIComponent[1];
        String[] nomEsNumero = new String[1];

        int pos = 0;
        noVacios[pos] = this.getABMObra().getTfNumeroObra();
        nomNoVacios[pos++] = "N\372mero de Obra";
        noVacios[pos] = this.getABMObra().getTaDescripcion();
        nomNoVacios[pos++] = "Descripci\363n";
        noVacios[pos] = this.getABMObra().getDdTipoObra();
        nomNoVacios[pos++] = "Tipo de Obra";
        noVacios[pos] = this.getABMObra().getTfMetrosTotalAfectados();
        nomNoVacios[pos++] = "Total Metros Afectados";
        noVacios[pos] = this.getABMObra().getTfValorPorMetro();
        nomNoVacios[pos++] = "Valor por Metro";
        noVacios[pos] = this.getABMObra().getTfCostoTotalObra();
        nomNoVacios[pos++] = "Costo Total";

        pos = 0;
        flotantes[pos] = this.getABMObra().getTfMetrosTotalAfectados();
        nomFlotantes[pos++] = "Total Metros Afectados";
        flotantes[pos] = this.getABMObra().getTfValorPorMetro();
        nomFlotantes[pos++] = "Valor por Metro";
        flotantes[pos] = this.getABMObra().getTfCostoTotalObra();
        nomFlotantes[pos++] = "Costo Total";

        pos = 0;
        esNumero[pos] = this.getABMObra().getTfNumeroObra();
        nomEsNumero[pos++] = "N\372mero de Obra";

        v.noSonVacios(noVacios, nomNoVacios);
        v.sonFlotantes(flotantes, nomFlotantes);
        v.esNumero(esNumero, nomEsNumero);
        v.sonPositivos(flotantes, nomFlotantes);                

        return v;
     }
     private void deshabilitarElementosConsultarEliminar(){
         this.getABMObra().getDdTipoObra().setDisabled(true);
         this.getABMObra().getRadioButton1().setDisabled(true);
         this.getABMObra().getRadioButton2().setDisabled(true);
         this.getABMObra().getTfNumeroObra().setDisabled(true);
         this.getABMObra().getTaDescripcion().setDisabled(true);
         this.getABMObra().getTfMetrosTotalAfectados().setDisabled(true);
         this.getABMObra().getTfValorPorMetro().setDisabled(true);
         this.getABMObra().getTfCostoTotalObra().setDisabled(true);
         this.getABMObra().getCbObraViaAdministracion().setDisabled(true);
         this.getABMObra().getBtnSeleccionarDigesto().setRendered(false);
         this.getABMObra().getBtnLimpiarDigesto().setRendered(false);
         this.getABMObra().getBtnAgregarCuadra().setRendered(false);
         this.getABMObra().getBtnQuitarCuadra().setRendered(false);
         this.getABMObra().getBtnAgregarPlanCuentaObra().setRendered(false);
         this.getABMObra().getBtnQuitarPlanCuentaObra().setRendered(false);
     }
     
     public class agregarObraController extends AgregarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            Obra obra = (Obra)pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra().addObra(obra);
            return "La Planificacion de Obra se agreg\363 exitosamente";            
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }

		@Override
		public ABMModel getModel() {
			return ObraModel.this;
		}
         
     }
     public class modificarObraController extends ModificarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            Obra obra = (Obra)pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra().updateObra(obra);
            return "La Planificacion de Obra se modific\363 exitosamente";            
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }
        
        @Override
		public ABMModel getModel() {
			return ObraModel.this;
		}
     }
     
     public class consultarObraController extends ConsultarAbstractController{

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
			return ObraModel.this;
		}
     }
     public class eliminarObraController extends EliminarAbstractController{

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
             Obra obra = (Obra)pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemPlanFinanciacionObra().deleteObra(obra);
            return "La Planificacion de Obra se elimin\363 exitosamente";            
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            deshabilitarElementosConsultarEliminar();
        }
         
        @Override
		public ABMModel getModel() {
			return ObraModel.this;
		}
    }
     
	@Override
	public String getReglaNavegacion() {
		return "ABMObra";
	}
	@Override
	public String getNombreEntidad() {
		return "Obras";
	}
}
