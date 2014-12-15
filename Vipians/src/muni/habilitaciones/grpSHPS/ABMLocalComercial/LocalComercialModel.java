/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.grpSHPS.ABMLocalComercial;

import com.trascender.habilitaciones.recurso.persistent.shps.LocalComercial;
import com.trascender.presentacion.abstracts.controller.*;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.component.UIComponent;

/**
 *
 * @author pedro
 */
public class LocalComercialModel  extends ABMModel{
    
    private ABMLocalComercial getBeanLocalComercial(){
        return (ABMLocalComercial) getRequestBean("habilitaciones$grpSHPS$ABMLocalComercial$ABMLocalComercial");
    }
    
    private Validador getValidadorAgregarModificar(){
         Validador v = new Validador();
                UIComponent[] noVacios = new UIComponent[4];
                String[] nomNoVacios = new String[4];
                UIComponent[] flotantes = new UIComponent[2];
                String[] nomFlotantes = new String[2];

                int pos = 0;
                noVacios[pos] = this.getBeanLocalComercial().getTfNumeroInscripcion();
                nomNoVacios[pos++] = "N\372mero de Inscripci\363n";
                noVacios[pos] = this.getBeanLocalComercial().getTfParcela();
                nomNoVacios[pos++] = "Parcela";
                noVacios[pos] = this.getBeanLocalComercial().getTfSuperficieCubierta();
                nomNoVacios[pos++] = "Superficie Cubierta Afectada";
                noVacios[pos] = this.getBeanLocalComercial().getTfSuperficieSemiCubierta();
                nomNoVacios[pos++] = "Superficie Semicubierta Afectada";

                pos = 0;
                flotantes[pos] = this.getBeanLocalComercial().getTfSuperficieCubierta();
                nomFlotantes[pos++] = "Superficie Cubierta Afectada";
                flotantes[pos] = this.getBeanLocalComercial().getTfSuperficieSemiCubierta();
                nomFlotantes[pos++] = "Superficie Semicubierta Afectada";

                v.noSonVacios(noVacios, nomNoVacios);
                v.sonFlotantes(flotantes, nomFlotantes);
                return v;
    }
    private void deshabilitarElementosConsultarEliminar(){
        getBeanLocalComercial().getTfNumeroInscripcion().setDisabled(true);
        getBeanLocalComercial().getTfParcela().setDisabled(true);
        getBeanLocalComercial().getTfSuperficieCubierta().setDisabled(true);
        getBeanLocalComercial().getTfSuperficieSemiCubierta().setDisabled(true);
        getBeanLocalComercial().getTfTelefono().setDisabled(true);
        getBeanLocalComercial().getTaDescripcion().setDisabled(true);
        getBeanLocalComercial().getBtnSeleccionarParcela().setRendered(false);
        getBeanLocalComercial().getBtnLimpiarParcela().setRendered(false);
        getBeanLocalComercial().getTableColumn1().setRendered(false);
        getBeanLocalComercial().getGroupPanel1().setRendered(false);
        getBeanLocalComercial().getTaComentarioLogAuditoria().setRendered(false);
		getBeanLocalComercial().getLblComentarioLogAuditoria().setRendered(false);
    }
    
    public class agregarLocalComercialController extends AgregarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            LocalComercial local = (LocalComercial) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().addLocalComercial(local);
            return "El local comercial se agreg\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }

		@Override
		public ABMModel getModel() {
			return LocalComercialModel.this;
		}
    }
    public class modificarLocalComercialController extends ModificarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            LocalComercial local = (LocalComercial) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().updateLocalComercial(local);
            return "El local comercial se modifico exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }
        
        @Override
		public ABMModel getModel() {
			return LocalComercialModel.this;
		}
    }
    public class consultarLocalComercialController extends ConsultarAbstractController{

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
			return LocalComercialModel.this;
		}
    }
    public class eliminarLocalComercialController extends EliminarAbstractController{

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            LocalComercial local = (LocalComercial) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemBromatologia().deleteLocalComercial(local);
            return "El local comercial se elimino exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            deshabilitarElementosConsultarEliminar();
        }
        
        @Override
		public ABMModel getModel() {
			return LocalComercialModel.this;
		}
    }
	@Override
	public String getReglaNavegacion() {
		return "ABMLocalComercial";
	}

	@Override
	public String getNombreEntidad() {
		return "Local Comercial";
	}
}
