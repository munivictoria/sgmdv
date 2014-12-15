/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.grpSHPS.ABMLibretaSanitaria;

import com.trascender.habilitaciones.recurso.persistent.shps.LibretaSanitaria;
import com.trascender.presentacion.abstracts.controller.*;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.component.UIComponent;

/**
 *
 * @author pedro
 */
public class LibretaSanitariaModel extends ABMModel{
    private ABMLibretaSanitaria getBeanTransporteVehicular(){
        return (ABMLibretaSanitaria) getRequestBean("habilitaciones$grpSHPS$ABMLibretaSanitaria$ABMLibretaSanitaria");
    }
     private Validador getValidadorAgregarModificar(){
            Validador v = new Validador();
            UIComponent[] noVacios = new UIComponent[2];
            String[] nomNoVacios = new String[2];

            int pos = 0;
            noVacios[pos] = this.getBeanTransporteVehicular().getTfNumeroLibretaSanitaria();
            nomNoVacios[pos++] = "N\372mero";
            noVacios[pos] = this.getBeanTransporteVehicular().getTfPersonaFisica();
            nomNoVacios[pos++] = "Persona";

            v.noSonVacios(noVacios, nomNoVacios);

            return v;
     }
     private void deshabilitarElementosConsultarEliminar(){
         this.getBeanTransporteVehicular().getTfNumeroLibretaSanitaria().setDisabled(true);
         this.getBeanTransporteVehicular().getTfPersonaFisica().setDisabled(true);
         this.getBeanTransporteVehicular().getGroupPanel1().setRendered(false);
         this.getBeanTransporteVehicular().getGroupPanel2().setRendered(false);
         this.getBeanTransporteVehicular().getGroupPanel3().setRendered(false);
         this.getBeanTransporteVehicular().getBtnSeleccionarPersonaFisica().setRendered(false);
         this.getBeanTransporteVehicular().getBtnLimpiarPersonaFisica().setRendered(false);
         this.getBeanTransporteVehicular().getRadioButton1().setDisabled(true);
         this.getBeanTransporteVehicular().getRadioButton2().setDisabled(true);
         this.getBeanTransporteVehicular().getRadioButton3().setDisabled(true);
         this.getBeanTransporteVehicular().getTaComentarioLogAuditoria().setRendered(false);
         this.getBeanTransporteVehicular().getLblComentarioLogAuditoria().setRendered(false);
     }
     public class agregarLibretaSanitariaController extends AgregarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            LibretaSanitaria libreta = (LibretaSanitaria) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().addLibretaSanitaria(libreta);
            return "La Libreta Sanitaria se agreg\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            
        }

		@Override
		public ABMModel getModel() {
			return LibretaSanitariaModel.this;
		}
         
     }
     public class modificarLibretaSanitariaController extends ModificarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
             LibretaSanitaria libreta = (LibretaSanitaria) pObject;
             getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().setLlave(getSessionBean1().getLlave());
             getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().updateLibretaSanitaria(libreta);
            return "La Libreta Sanitaria se modific\363 correctamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            
        }
         
        @Override
		public ABMModel getModel() {
			return LibretaSanitariaModel.this;
		}
     }
      public class consutlarLibretaSanitariaController extends ConsultarAbstractController{

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
			return LibretaSanitariaModel.this;
		}
      }
       public class eliminarLibretaSanitariaController extends EliminarAbstractController{

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
             LibretaSanitaria libreta = (LibretaSanitaria) pObject;
             getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().setLlave(getSessionBean1().getLlave());
             getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().deleteLibretaSanitaria(libreta);
             return "La Libreta Sanitaria se elimin\363 correctamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            deshabilitarElementosConsultarEliminar();
        }
           
        @Override
		public ABMModel getModel() {
			return LibretaSanitariaModel.this;
		}
    }
	@Override
	public String getReglaNavegacion() {
		return "ABMLibretaSanitaria";
	}
	@Override
	public String getNombreEntidad() {
		return "Libreta Sanitaria";
	}
}
