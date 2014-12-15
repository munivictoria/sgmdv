package muni.habilitaciones.grpAutomotor.ABMTipoVehiculo;

import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;
import com.trascender.presentacion.abstracts.controller.*;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.component.UIComponent;

public class TipoVehiculoModel extends ABMModel {

    private ABMTipoVehiculo getBeanTipoVehiculo() {
        return (ABMTipoVehiculo) getRequestBean("habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo");
    }

    private Validador getValidadorAgregarModificar() {
        Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[2];
        String[] nomNoVacios = new String[2];

        int pos = 0;
        noVacios[pos] = getBeanTipoVehiculo().getTfCodigo();
        nomNoVacios[pos++] = "C\363digo";
        noVacios[pos] = getBeanTipoVehiculo().getTfNombre();
        nomNoVacios[pos++] = "Nombre";

        v.noSonVacios(noVacios, nomNoVacios);
        return v;
    }

    private void deshabilitarElementosConsultarEliminar() {
        getBeanTipoVehiculo().getTfNombre().setDisabled(true);
        getBeanTipoVehiculo().getTfCodigo().setDisabled(true);
        getBeanTipoVehiculo().getTaDescripcion().setDisabled(true);
        getBeanTipoVehiculo().getPanelAtributoDinamico().deshabilitarCampos();
        getBeanTipoVehiculo().getTaComentarioLogAuditoria().setRendered(false);
		getBeanTipoVehiculo().getLblComentarioLogAuditoria().setRendered(false);
    }

    public class AgregarController extends AgregarAbstractController {

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            TipoVehiculo locTipoVehiculo = (TipoVehiculo) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().addTipoVehiculo(locTipoVehiculo);
            return "El Tipo de Vehiculo se agreg\363 correctamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }

		@Override
		public ABMModel getModel() {
			return TipoVehiculoModel.this;
		}
    }

    public class ModificarController extends ModificarAbstractController {

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            TipoVehiculo locTipoVehiculo = (TipoVehiculo) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().updateTipoVehiculo(locTipoVehiculo);
            return "El Tipo de Vehiculo se modific\363 correctamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }
        
        @Override
		public ABMModel getModel() {
			return TipoVehiculoModel.this;
		}
    }

    public class EliminarController extends EliminarAbstractController {

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            TipoVehiculo locTipoVehiculo = (TipoVehiculo) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().deleteTipoVehiculo(locTipoVehiculo);
            return "El Tipo de Vehiculo se elimin\363 correctamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            deshabilitarElementosConsultarEliminar();
        }
        
        @Override
		public ABMModel getModel() {
			return TipoVehiculoModel.this;
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
			return TipoVehiculoModel.this;
		}
    }

	@Override
	public String getReglaNavegacion() {
		return "ABMTipoVehiculo";
	}

	@Override
	public String getNombreEntidad() {
		return "Tipo de Vehículo";
	}
}
