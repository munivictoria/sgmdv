package muni.compras.ABMUsuarioFirmante;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorOrdenCompra;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class UsuarioFirmanteModel extends ABMModel {

    private ABMUsuarioFirmante getBeanUsuarioFirmante() {
        return (ABMUsuarioFirmante) getRequestBean("compras$ABMUsuarioFirmante$ABMUsuarioFirmante");
    }

    private Validador getValidadorAgregarModificar() {
        com.trascender.presentacion.validadores.Validador v = new com.trascender.presentacion.validadores.Validador();
        UIComponent[] noVacios = new UIComponent[1];
        String[] nomNoVacios = new String[1];

        int pos = 0;
        noVacios[pos] = getBeanUsuarioFirmante().getTfUsuario();
        nomNoVacios[pos++] = "Usuario";
        
        v.noSonVacios(noVacios, nomNoVacios);
        return v;
    }

    private void deshabilitarElementosConsultarEliminar() {
    	this.getBeanUsuarioFirmante().getBtnSeleccionarUsuario().setRendered(false);
    	this.getBeanUsuarioFirmante().getCbImprimeOrdenNueva().setDisabled(true);
    }

    public class AgregarUsuarioFirmanteController extends AgregarAbstractController {

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            UsuarioAutorizadorOrdenCompra locUsuario = (UsuarioAutorizadorOrdenCompra) pObject;
            getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().addUsuarioAutorizadorOrdenCompra(locUsuario);
            return "El Usuario Firmante se agreg\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }

		@Override
		public ABMModel getModel() {
			return UsuarioFirmanteModel.this;
		}
    }

    public class ModificarUsuarioFirmanteController extends ModificarAbstractController {

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
        	UsuarioAutorizadorOrdenCompra locUsuario = (UsuarioAutorizadorOrdenCompra) pObject;
            getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().updateUsuarioAutorizadorOrdenCompra(locUsuario);
            return "El Usuario Firmante se modific\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }
        
        @Override
		public ABMModel getModel() {
			return UsuarioFirmanteModel.this;
		}
    }

    public class EliminarUsuarioFirmanteController extends EliminarAbstractController {

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
        	UsuarioAutorizadorOrdenCompra locUsuario = (UsuarioAutorizadorOrdenCompra) pObject;
            getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemAdministracionOrdenCompra().deleteUsuarioAutorizadorOrdenCompra(locUsuario);
            return "El Usuario Firmante se elimin\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            deshabilitarElementosConsultarEliminar();
        }
        
        @Override
		public ABMModel getModel() {
			return UsuarioFirmanteModel.this;
		}
    }

    public class ConsultarUsuarioFirmanteController extends ConsultarAbstractController {

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
            getBeanUsuarioFirmante().getTaComentarioLogAuditoria().setRendered(false);
    		getBeanUsuarioFirmante().getLblComentarioLogAuditoria().setRendered(false);
        }
        
        @Override
		public ABMModel getModel() {
			return UsuarioFirmanteModel.this;
		}
    }

	@Override
	public String getReglaNavegacion() {
		return "ABMUsuarioFirmante";
	}

	@Override
	public String getNombreEntidad() {
		return "Usuario Firmante";
	}
}
