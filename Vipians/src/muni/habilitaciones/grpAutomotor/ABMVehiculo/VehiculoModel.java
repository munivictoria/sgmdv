package muni.habilitaciones.grpAutomotor.ABMVehiculo;

import javax.faces.component.UIComponent;

import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class VehiculoModel extends ABMModel {

    private ABMVehiculo getBeanVehiculo() {
        return (ABMVehiculo) getRequestBean("habilitaciones$grpAutomotor$ABMVehiculo$ABMVehiculo");
    }

    private Validador getValidadorAgregarModificar() {
        com.trascender.presentacion.validadores.Validador v = new com.trascender.presentacion.validadores.Validador();
        UIComponent[] noVacios = new UIComponent[2];
        String[] nomNoVacios = new String[2];

        int pos = 0;
        noVacios[pos] = getBeanVehiculo().getTfPatente();
        nomNoVacios[pos++] = "Patente";

        noVacios[pos] = getBeanVehiculo().getTfModelo();
        nomNoVacios[pos++] = "Modelo";


        v.noSonVacios(noVacios, nomNoVacios);
        return v;
    }

    private void deshabilitarElementosConsultarEliminar() {
        getBeanVehiculo().getTfPatente().setDisabled(true);
        getBeanVehiculo().getTfAnio().setDisabled(true);
        getBeanVehiculo().getTfCapacidad().setDisabled(true);
        getBeanVehiculo().getTfPeso().setDisabled(true);
        getBeanVehiculo().getTfChasis().setDisabled(true);
        getBeanVehiculo().getTfMotor().setDisabled(true);
        getBeanVehiculo().getTfModelo().setDisabled(true);
        getBeanVehiculo().getTaDescripcion().setDisabled(true);
        getBeanVehiculo().getBtnLimpiarModelo().setRendered(false);
        getBeanVehiculo().getBtnSeleccionarModelo().setRendered(false);
        getBeanVehiculo().getPanelAtributoDinamico().deshabilitarCampos();
        getBeanVehiculo().getPanelAtributoDinamico2().deshabilitarCampos();
        getBeanVehiculo().getGroupPanel1().setRendered(false);
        getBeanVehiculo().getTfCodigo().setDisabled(true);
        getBeanVehiculo().getTfFechaInscripcion().setDisabled(true);
        getBeanVehiculo().getTaDescripcionPropietarios().setDisabled(true);
        getBeanVehiculo().getTableColumn1().setRendered(false);
        getBeanVehiculo().getTaComentarioLogAuditoria().setRendered(false);
		getBeanVehiculo().getLblComentarioLogAuditoria().setRendered(false);
		getBeanVehiculo().getLblComentarioLogAuditoria2().setRendered(false);
		getBeanVehiculo().getTaComentarioLogAuditoria2().setRendered(false);
    }

    public class AgregarController extends AgregarAbstractController {

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            Vehiculo locVehiculo = (Vehiculo) pObject;
            
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().addVehiculo(locVehiculo);
            return "El vehiculo se agreg\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        	getBeanVehiculo().getGroupPanelObligaciones().setRendered(false);
        }

		@Override
		public ABMModel getModel() {
			return VehiculoModel.this;
		}
    }

    public class ModificarController extends ModificarAbstractController {

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            Vehiculo locVehiculo = (Vehiculo) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().updateVehiculo(locVehiculo);
            return "El vehiculo se modific\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }
        
        @Override
		public ABMModel getModel() {
			return VehiculoModel.this;
		}
    }

    public class EliminarController extends EliminarAbstractController {

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            Vehiculo locVehiculo = (Vehiculo) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().deleteVehiculo(locVehiculo);
            return "El vehiculo se elimin\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            deshabilitarElementosConsultarEliminar();
        }
        
        @Override
		public ABMModel getModel() {
			return VehiculoModel.this;
		}
    }

    public class ConsultarController extends ConsultarAbstractController {

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
			return VehiculoModel.this;
		}
    }

	@Override
	public String getReglaNavegacion() {
		return "ABMVehiculo";
	}

	@Override
	public String getNombreEntidad() {
		return "Vehículo";
	}
}
