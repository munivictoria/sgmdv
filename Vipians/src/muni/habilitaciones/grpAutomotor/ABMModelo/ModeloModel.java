package muni.habilitaciones.grpAutomotor.ABMModelo;

import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.presentacion.abstracts.controller.*;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.component.UIComponent;

public class ModeloModel extends ABMModel {

    private ABMModelo getBeanModelo() {
        return (ABMModelo) getRequestBean("habilitaciones$grpAutomotor$ABMModelo$ABMModelo");
    }

    private Validador getValidadorAgregarModificar() {
        Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[4];
        String[] nomNoVacios = new String[4];

        int pos = 0;
        noVacios[pos] = getBeanModelo().getTfNombre();
        nomNoVacios[pos++] = "Nombre";
        noVacios[pos] = getBeanModelo().getDdMarca();
        nomNoVacios[pos++] = "Marca";
        noVacios[pos] = getBeanModelo().getTfMinimo();
        nomNoVacios[pos++] = "Minimo";
        noVacios[pos] = getBeanModelo().getDdTipoVehiculo();
        nomNoVacios[pos++] = "Tipo de Vehiculo";

        v.noSonVacios(noVacios, nomNoVacios);

        return v;
    }

    private void deshabilitarElementosConsultarEliminar() {
        getBeanModelo().getDdMarca().setDisabled(true);
        getBeanModelo().getTfMinimo().setDisabled(true);
        getBeanModelo().getTfNombre().setDisabled(true);
        getBeanModelo().getDdTipoVehiculo().setDisabled(true);
        getBeanModelo().getTaDescripcion().setDisabled(true);
        getBeanModelo().getBtnLimpiarMarca().setRendered(false);
        getBeanModelo().getBtnLimpiarTipoVehiculo().setRendered(false);
        getBeanModelo().getBtnSeleccionarMarca().setRendered(false);
        getBeanModelo().getBtnSeleccionarTipoVehiculo().setRendered(false);
        getBeanModelo().getPanelAtributoDinamico().deshabilitarCampos();
        getBeanModelo().getTaComentarioLogAuditoria().setRendered(false);
		getBeanModelo().getLblComentarioLogAuditoria().setRendered(false);
    }

    public class AgregarController extends AgregarAbstractController {

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            Modelo locModelo = (Modelo) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().addModelo(locModelo);
            return "El modelo se agreg\363 exitosamente";

        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }

		@Override
		public ABMModel getModel() {
			return ModeloModel.this;
		}
    }

    public class ModificarController extends ModificarAbstractController {

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            Modelo locModelo = (Modelo) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().updateModelo(locModelo);
            return "El modelo se modific\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }
        
        @Override
		public ABMModel getModel() {
			return ModeloModel.this;
		}
    }

    public class EliminarController extends EliminarAbstractController {

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            Modelo locModelo = (Modelo) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().deleteModelo(locModelo);
            return "El modelo se elimin\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            deshabilitarElementosConsultarEliminar();
        }
        
        @Override
		public ABMModel getModel() {
			return ModeloModel.this;
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
			return ModeloModel.this;
		}
    }

	@Override
	public String getReglaNavegacion() {
		return "ABMModelo";
	}

	@Override
	public String getNombreEntidad() {
		return "Modelo";
	}
}
