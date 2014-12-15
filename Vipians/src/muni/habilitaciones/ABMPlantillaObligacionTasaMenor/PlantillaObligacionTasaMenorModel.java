package muni.habilitaciones.ABMPlantillaObligacionTasaMenor;

import javax.faces.component.UIComponent;

import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class PlantillaObligacionTasaMenorModel extends ABMModel{

	private ABMPlantillaObligacionTasaMenor getBeanPlantilla(){
        return (ABMPlantillaObligacionTasaMenor) getRequestBean("habilitaciones$ABMPlantillaObligacionTasaMenor$ABMPlantillaObligacionTasaMenor");
    }
    
    private Validador getValidadorAgregarModificar() {

        Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[1];
        String[] nomNoVacios = new String[1];

        int pos = 0;
        noVacios[pos] = getBeanPlantilla().getTfNombre();
        nomNoVacios[pos++] = "Nombre de la Plantilla";
        
        v.noSonVacios(noVacios, nomNoVacios);
        
        return v;
    }
    
    private void deshabilitarElementosConsultarEliminar(){
    	getBeanPlantilla().getTfNombre().setDisabled(true);
    	getBeanPlantilla().getTfNombreDato().setRendered(false);
    	getBeanPlantilla().getTfNombreDatoRegValuado().setRendered(false);
    	getBeanPlantilla().getDdTipoDato().setRendered(false);
    	getBeanPlantilla().getDdTipoDatoRegValuado().setRendered(false);
    	getBeanPlantilla().getLblNombreDato().setRendered(false);
    	getBeanPlantilla().getLblNombreDatoRegValuado().setRendered(false);
    	getBeanPlantilla().getLabel1().setRendered(false);
    	getBeanPlantilla().getLabel5().setRendered(false);
    	getBeanPlantilla().getBtnAgregarAtributoDinamico().setRendered(false);
    	getBeanPlantilla().getBtnAgregarAtributoDinamicoRegValuado().setRendered(false);
    	getBeanPlantilla().getBtnQuitarAtributoDinamico().setRendered(false);
    	getBeanPlantilla().getBtnQuitarAtributoDinamicoRegValuado().setRendered(false);
//    	getBeanPlantilla().getLblDatosPlantilla().setRendered(false);
//    	getBeanPlantilla().getLblDatosPlantillaRegValuado().setRendered(false);
    	getBeanPlantilla().getTaComentarioLogAuditoria().setRendered(false);
    	getBeanPlantilla().getLblComentarioLogAuditoria().setRendered(false);
    	getBeanPlantilla().getCbAsociacionAParcela().setDisabled(true);
    	getBeanPlantilla().getCbPersonaPropietaria().setDisabled(true);
    }
    
    
    public class AgregarPlantillaObligacionTasaMenorController extends AgregarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
        	PlantillaDocumentoTasaMenor locPlantilla = (PlantillaDocumentoTasaMenor) pObject;
        	getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTasaMenor().setLlave(getSessionBean1().getLlave());
        	locPlantilla = getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTasaMenor().addPlantillaDocumentoTasaMenor(locPlantilla);
            
            getApplicationBean1().agregarNuevoTipoObligacionTasaMenor(locPlantilla.getTipoObligacion());
            
            return "La Plantilla se agreg\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            
        }

		@Override
		public ABMModel getModel() {
			return PlantillaObligacionTasaMenorModel.this;
		}
    }
    
    public class ModificarPlantillaObligacionTasaMenorController extends ModificarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
        	PlantillaDocumentoTasaMenor locPlantilla = (PlantillaDocumentoTasaMenor) pObject;
        	getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTasaMenor().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTasaMenor().updatePlantillaDocumentoTasaMenor(locPlantilla);
            
            getApplicationBean1().modificarTipoObligacionTasaMenor(locPlantilla.getTipoObligacion());
            
            return "La Plantilla se modific\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }
        
        @Override
		public ABMModel getModel() {
			return PlantillaObligacionTasaMenorModel.this;
		}
    }
    
    public class ConsultarPlantillaObligacionTasaMenorController extends ConsultarAbstractController{

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
			return PlantillaObligacionTasaMenorModel.this;
		}
    }
    
    public class EliminarPlantillaObligacionTasaMenorController extends EliminarAbstractController{

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
        	PlantillaDocumentoTasaMenor locPlantilla = (PlantillaDocumentoTasaMenor) pObject;
        	getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTasaMenor().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoTasaMenor().removePlantillaDocumentoTasaMenor(locPlantilla);
            
            getApplicationBean1().eliminarTipoObligacionTasaMenor(locPlantilla.getTipoObligacion());
            
            return "La Plantilla se elimin\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            deshabilitarElementosConsultarEliminar();
        }
        
        @Override
		public ABMModel getModel() {
			return PlantillaObligacionTasaMenorModel.this;
		}
    }

	@Override
	public String getReglaNavegacion() {
		return "ABMPlantillaObligacionTasaMenor";
	}

	@Override
	public String getNombreEntidad() {
		return "Plantilla de Obligaci\363n Menor";
	}
}
