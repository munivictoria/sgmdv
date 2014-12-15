package muni.habilitaciones.grpAutomotor.ABMMarca;

import com.sun.tools.xjc.reader.ModelChecker;
import com.trascender.habilitaciones.recurso.persistent.transito.Marca;
import com.trascender.presentacion.abstracts.controller.*;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.component.UIComponent;

public class MarcaModel extends ABMModel {

    private ABMMarca getBeanMarca() {
        return (ABMMarca) getRequestBean("habilitaciones$grpAutomotor$ABMMarca$ABMMarca");
    }

    private Validador getValidadorAgregarModificar() {
        Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[2];
        String[] nomNoVacios = new String[2];

        int pos = 0;
        noVacios[pos] = getBeanMarca().getTfCodigo();
        nomNoVacios[pos++] = "C\363digo";
        noVacios[pos] = getBeanMarca().getTfNombre();
        nomNoVacios[pos++] = "Nombre";

        v.noSonVacios(noVacios, nomNoVacios);
        return v;
    }

    private void deshabilitarElementosConsultarEliminar() {
        getBeanMarca().getTfCodigo().setDisabled(true);
        getBeanMarca().getTfNombre().setDisabled(true);
        getBeanMarca().getPanelAtributoDinamico().deshabilitarCampos();
        getBeanMarca().getTaComentarioLogAuditoria().setRendered(false);
		getBeanMarca().getLblComentarioLogAuditoria().setRendered(false);
    }

    public class AgregarController extends AgregarAbstractController {

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            Marca locMarca = (Marca) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().addMarca(locMarca);
            return "La Marca se agreg\363 correctamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }

		@Override
		public ABMModel getModel() {
			return MarcaModel.this;
		}
    }

    public class ModificarController extends ModificarAbstractController {

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            Marca locMarca = (Marca) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().updateMarca(locMarca);
            return "La Marca se modific\363 correctamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }
        
        @Override
		public ABMModel getModel() {
			return MarcaModel.this;
		}
        
    }

    public class EliminarController extends EliminarAbstractController {

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            Marca locMarca = (Marca) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().deleteMarca(locMarca);
            return "La Marca se elimin\363 correctamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            deshabilitarElementosConsultarEliminar();
        }
        
        @Override
		public ABMModel getModel() {
			return MarcaModel.this;
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
			return MarcaModel.this;
		}
        
    }

	@Override
	public String getReglaNavegacion() {
		return "ABMMarca";
	}

	@Override
	public String getNombreEntidad() {
		return "Marca";
	}
}
