/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.grpBromatologia.ABMTransporteVehicular;

import com.trascender.habilitaciones.recurso.persistent.shps.TransporteVehicular;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.component.UIComponent;

/**
 *
 * @author pedro
 */
public class TransporteVehicularModel extends ABMModel{
    
    private ABMTransporteVehicular getBeanTransporteVehicular(){
        return (ABMTransporteVehicular) getRequestBean("habilitaciones$grpBromatologia$ABMTransporteVehicular$ABMTransporteVehicular");
    }
    private Validador getValidadorAgregarModificar(){
        Validador v = new Validador();
                UIComponent[] noVacios = new UIComponent[4];
                String[] nomNoVacios = new String[4];
                UIComponent[] fechas = new UIComponent[2];
                String[] nomFechas = new String[2];

                int pos = 0;
                noVacios[pos] = this.getBeanTransporteVehicular().getTfNumeroInscripcion();
                nomNoVacios[pos++] = "N\372mero de Inscripci\363n";
                noVacios[pos] = this.getBeanTransporteVehicular().getTfVehiculo();
                nomNoVacios[pos++] = "Veh\355culo";
                noVacios[pos] = this.getBeanTransporteVehicular().getTfFechaAlta();
                nomNoVacios[pos++] = "Fecha Alta";
                noVacios[pos] = this.getBeanTransporteVehicular().getTaDescripcion();
                nomNoVacios[pos++] = "Descripci\363n";
                
                pos = 0;
                fechas[pos] = this.getBeanTransporteVehicular().getTfFechaAlta();
                nomFechas[pos++] = "Fecha Alta";
                fechas[pos] = this.getBeanTransporteVehicular().getTfFechaBaja();
                nomFechas[pos++] = "Fecha Baja";
                
                v.noSonVacios(noVacios, nomNoVacios);
                v.formatoFechaValido(fechas, nomFechas);
                
                return v;
    }
     private void deshabilitarElementosConsultarEliminar(){
		 this.getBeanTransporteVehicular().getTfFechaAlta().setDisabled(true);
		 this.getBeanTransporteVehicular().getTfFechaBaja().setDisabled(true);
		 this.getBeanTransporteVehicular().getTfNumeroInscripcion().setDisabled(true);
		 this.getBeanTransporteVehicular().getTfVehiculo().setDisabled(true);
		 this.getBeanTransporteVehicular().getTaDescripcion().setDisabled(true);
		 this.getBeanTransporteVehicular().getBtnAgregarInspeccion().setRendered(false);
		 this.getBeanTransporteVehicular().getBtnQuitarInspeccion().setRendered(false);
		 this.getBeanTransporteVehicular().getBtnSeleccionarVehiculo().setRendered(false);
		 this.getBeanTransporteVehicular().getBtnQuitarInspeccion().setRendered(false);
		 this.getBeanTransporteVehicular().getStaticText6().setRendered(false);
		 this.getBeanTransporteVehicular().getBtnLimpiarVehiculo().setRendered(false);
		 this.getBeanTransporteVehicular().getTaComentarioLogAuditoria().setRendered(false);
		 this.getBeanTransporteVehicular().getLblComentarioLogAuditoria().setRendered(false);
     }
     public class AgregarTransporteVehicularController extends AgregarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
           TransporteVehicular transporte = (TransporteVehicular) pObject;
           getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().setLlave(getSessionBean1().getLlave());
           getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().addTransporteVehicular(transporte);
           return "El Transporte Vehicular se agrego correctamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }

		@Override
		public ABMModel getModel() {
			return TransporteVehicularModel.this;
		}
         
     }
     public class ModificarTransporteVehicularController extends ModificarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            TransporteVehicular transporte = (TransporteVehicular) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().updateTranporteVehicular(transporte);
            return "El Transporte Vehicular se modifico correctamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }
         
        @Override
		public ABMModel getModel() {
			return TransporteVehicularModel.this;
		}
     }
     public class ConsultarTrasnporteVehicularController extends ConsultarAbstractController{

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
			return TransporteVehicularModel.this;
		}
     }
     public class EliminarTransporteVehicularController extends EliminarAbstractController{

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            TransporteVehicular transporte = (TransporteVehicular) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().deleteTransporteVehicular(transporte);
            return "El Transporte Vehicular se elimino correctamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            deshabilitarElementosConsultarEliminar();
        }
        
        @Override
		public ABMModel getModel() {
			return TransporteVehicularModel.this;
		}
         
     }
	@Override
	public String getReglaNavegacion() {
		return "ABMTransporteVehicular";
	}
	@Override
	public String getNombreEntidad() {
		return "Transporte Vehicular";
	}
    
}
