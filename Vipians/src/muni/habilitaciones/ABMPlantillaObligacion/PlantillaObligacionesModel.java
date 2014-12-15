/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.ABMPlantillaObligacion;

import javax.faces.component.UIComponent;

import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 *
 * @author pedro
 */
public class PlantillaObligacionesModel extends ABMModel{
    
    private ABMPlantillaObligacion getABMPlantillaObligacion(){
        return (ABMPlantillaObligacion) getRequestBean("habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion");
    }
    
    private Validador getValidadorAgregarModificar(){
        Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[2];
        String[] nomNoVacios = new String[2];

        int pos = 0;
        noVacios[pos] = this.getABMPlantillaObligacion().getTfNombre();
        nomNoVacios[pos++] = "Nombre";
        noVacios[pos] = this.getABMPlantillaObligacion().getDdTipoObligacion();
        nomNoVacios[pos++] = "Tipo de Obligaci\363n";

        v.noSonVacios(noVacios, nomNoVacios);
        
        return v;
    }
    private void deshabilitarElementosConsultarEliminar(){
        ABMPlantillaObligacion abmPalBMPlantillaObligacion = getABMPlantillaObligacion();
        abmPalBMPlantillaObligacion.getTfNombre().setDisabled(true);
        abmPalBMPlantillaObligacion.getDdTipoObligacion().setDisabled(true);
        abmPalBMPlantillaObligacion.getTaDescripcion().setDisabled(true);
        abmPalBMPlantillaObligacion.getBtnAgregarDocumento().setRendered(false);
        abmPalBMPlantillaObligacion.getBtnAgregarPermiso().setRendered(false);
        abmPalBMPlantillaObligacion.getBtnAgregarSellado().setRendered(false);
        abmPalBMPlantillaObligacion.getBtnQuitarElemento().setRendered(false);
        abmPalBMPlantillaObligacion.getTaComentarioLogAuditoria().setRendered(false);
		abmPalBMPlantillaObligacion.getLblComentarioLogAuditoria().setRendered(false);
    }
    public class AgregarPlantillaObligacionesController extends AgregarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            PlantillaObligacion plantillaObligacion = (PlantillaObligacion) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().setLlave(getSessionBean1().getLlave());
            plantillaObligacion = getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().addPlantillaObligacion(plantillaObligacion);
            
            getApplicationBean1().agregarNuevaPlantilla(plantillaObligacion);
            
            return "La Plantilla de Obligaciones se agreg\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }

		@Override
		public ABMModel getModel() {
			return PlantillaObligacionesModel.this;
		}
        
    }
    public class ModificarPlantillaObligacionController extends ModificarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            PlantillaObligacion plantillaObligacion = (PlantillaObligacion) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().setLlave(getSessionBean1().getLlave());
            plantillaObligacion = getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().updatePlantillaObligacion(plantillaObligacion);
            
            getApplicationBean1().modificarPlantilla(plantillaObligacion);
            
            return "La Plantilla de Obligaciones se modific\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }   
        
        @Override
		public ABMModel getModel() {
			return PlantillaObligacionesModel.this;
		}
    }
    public class ConsultarPlantillaObligacionController extends ConsultarAbstractController{

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
			return PlantillaObligacionesModel.this;
		}
     }
    public class EliminarPlantillaObligacionController extends EliminarAbstractController{

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            PlantillaObligacion plantillaObligacion = (PlantillaObligacion) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemPlantillaObligaciones().deletePlantillaObligacion(plantillaObligacion);
            
            getApplicationBean1().eliminarPlantilla(plantillaObligacion);
            
            return "La Plantilla de Obligaciones se elimin\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            deshabilitarElementosConsultarEliminar();
        }
         
        @Override
		public ABMModel getModel() {
			return PlantillaObligacionesModel.this;
		}
     }
	@Override
	public String getReglaNavegacion() {
		return "ABMPlantillaObligacion";
	}



	@Override
	public String getNombreEntidad() {
		return "Plantilla de Obligación";
	}
    
}
