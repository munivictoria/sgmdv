/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.ABMTipoTasa;

import com.trascender.habilitaciones.recurso.persistent.TipoTasa;
import com.trascender.presentacion.abstracts.controller.*;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.component.UIComponent;

/**
 *
 * @author Fernando Luca
 */
public class TipoTasaModel extends ABMModel{
    
    private ABMTipoTasa getBeanTipoTasa(){
        return (ABMTipoTasa) getRequestBean("habilitaciones$ABMTipoTasa$ABMTipoTasa");
    }
    
    private Validador getValidadorAgregarModificar() {
        
        getBeanTipoTasa().guardarEstadoObjetosUsados();
        
        TipoTasa tipoTasa = (TipoTasa) this.getBeanTipoTasa().obtenerObjetoDelElementoPila(0, TipoTasa.class);

        // CAMBIAR: Validar los campos necesarios.
        Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[3];
        String[] nomNoVacios = new String[3];

        int pos = 0;
        noVacios[pos] = this.getBeanTipoTasa().getTfTipoObligacion();
        nomNoVacios[pos++] = "F\363rmula de C\341lculo para (Tipo de Obligaci\363n)";
        noVacios[pos] = this.getBeanTipoTasa().getTfNombre();
        nomNoVacios[pos++] = "Nombre";
        noVacios[pos] = this.getBeanTipoTasa().getTaFormula();
        nomNoVacios[pos++] = "F\363rmula";
        
        v.noSonVacios(noVacios, nomNoVacios);
        
        getBeanTipoTasa().mostrarEstadoObjetosUsados();
        
        return v;
    }
    
    private void deshabilitarElementosConsultarEliminar(){
        getBeanTipoTasa().getTfTipoObligacion().setDisabled(true);
        getBeanTipoTasa().getTfNombre().setDisabled(true);
        getBeanTipoTasa().getTaFormula().setDisabled(true);
        getBeanTipoTasa().getTextArea3().setDisabled(true);
        getBeanTipoTasa().getTextArea4().setDisabled(true);
        getBeanTipoTasa().getTaFormulaAlicuota().setDisabled(true);
        getBeanTipoTasa().getTextArea1().setDisabled(true);
        getBeanTipoTasa().getTextArea2().setDisabled(true);
        getBeanTipoTasa().getTable3().setRendered(false);
        getBeanTipoTasa().getLabel15().setRendered(false);
        getBeanTipoTasa().getTableColumn4().setRendered(false);
        getBeanTipoTasa().getGroupPanel1().setRendered(false);
        getBeanTipoTasa().getGroupPanel2().setRendered(false);
        getBeanTipoTasa().getGroupPanel0().setRendered(false);
        getBeanTipoTasa().getBtnAgregarFormulaAlicuota().setRendered(false);
        getBeanTipoTasa().getBtnQuitarFormulaAlicuota().setRendered(false);
        getBeanTipoTasa().getGroupPanel4().setRendered(false);
        getBeanTipoTasa().getGroupPanel5().setRendered(false);
        getBeanTipoTasa().getCbFija().setDisabled(true);
        getBeanTipoTasa().getGroupPanel6().setRendered(false);
        getBeanTipoTasa().getBtnNuevoParametro().setRendered(false);
        getBeanTipoTasa().getBtnQuitarParametro().setRendered(false);
        getBeanTipoTasa().getGroupPanel7().setRendered(false);
        getBeanTipoTasa().getTableColumn6().setRendered(false);
        getBeanTipoTasa().getTfNombreVariable().setDisabled(true);
        getBeanTipoTasa().getTaExpresion().setDisabled(true);
        getBeanTipoTasa().getBtnAgregarVariable().setRendered(false);
        getBeanTipoTasa().getBtnQuitarVariable().setRendered(false);
        getBeanTipoTasa().getTaComentarioLogAuditoria().setRendered(false);
        getBeanTipoTasa().getLblComentarioLogAuditoria().setRendered(false);
        getBeanTipoTasa().getDdPlanes().setDisabled(true);
    }
    
    
    public class AgregarTipoTasaController extends AgregarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            
            TipoTasa locTipoTasa = (TipoTasa) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().addTipoTasa(locTipoTasa);            
            
            if (getBeanTipoTasa().getListaDelCommunication() != null) {
                getBeanTipoTasa().getListaDelCommunication().clear();
            }
            if (getBeanTipoTasa().getListaDelCommunication2() != null) {
                getBeanTipoTasa().getListaDelCommunication2().clear();
            }
            if (getBeanTipoTasa().getListaDelCommunication3() != null) {
                getBeanTipoTasa().getListaDelCommunication3().clear();
            }

            return "La F\363rmula de C\341lculo se agreg\363 exitosamente.";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            
        }

		@Override
		public ABMModel getModel() {
			return TipoTasaModel.this;
		}
    }
    
    public class ModificarTipoTasaController extends ModificarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            TipoTasa locTipoTasa = (TipoTasa) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().updateTipoTasa(locTipoTasa);            
            
            if (getBeanTipoTasa().getListaDelCommunication() != null) {
                getBeanTipoTasa().getListaDelCommunication().clear();
            }
            if (getBeanTipoTasa().getListaDelCommunication2() != null) {
                getBeanTipoTasa().getListaDelCommunication2().clear();
            }
            if (getBeanTipoTasa().getListaDelCommunication3() != null) {
                getBeanTipoTasa().getListaDelCommunication3().clear();
            }

            return "La F\363rmula de C\341lculo se modific\363 exitosamente.";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            
        }
        
        @Override
		public ABMModel getModel() {
			return TipoTasaModel.this;
		}
    }
    
    public class ConsultarTipoTasaController extends ConsultarAbstractController{

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
			return TipoTasaModel.this;
		}
    }
    
    public class EliminarTipoTasaController extends EliminarAbstractController{

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            TipoTasa locTipoTasa = (TipoTasa) pObject;
            getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(getSessionBean1().getLlave());
            getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().deleteTipoTasa(locTipoTasa);
            return "La F\363rmula de C\341lculo se elimin\363 exitosamente.";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            deshabilitarElementosConsultarEliminar();
        }
        
        @Override
		public ABMModel getModel() {
			return TipoTasaModel.this;
		}
    }

	@Override
	public String getReglaNavegacion() {
		return "ABMTipoTasa";
	}

	@Override
	public String getNombreEntidad() {
		return "Fórmulas de Cálculo";
	}
}
