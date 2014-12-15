package muni.habilitaciones.grpAutomotor.ABMValuacionAcara;

import javax.faces.component.UIComponent;

import com.trascender.habilitaciones.recurso.persistent.transito.ValuacionAcara;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class ValuacionAcaraModel extends ABMModel {
	
	 private ABMValuacionAcara getBeanValuacionAcara() {
	        return (ABMValuacionAcara) getRequestBean("habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara");
	    }

	    private Validador getValidadorAgregarModificar() {
	        Validador v = new Validador();
	        UIComponent[] noVacios = new UIComponent[4];
	        String[] nomNoVacios = new String[4];

	        int pos = 0;
	        noVacios[pos] = getBeanValuacionAcara().getTfAnio();
	        nomNoVacios[pos++] = "Anio";
	        noVacios[pos] = getBeanValuacionAcara().getTfValor();
	        nomNoVacios[pos++] = "Valor";
	        noVacios[pos] = getBeanValuacionAcara().getTfModelo();
	        nomNoVacios[pos++] = "Modelo";
	        noVacios[pos] = getBeanValuacionAcara().getDdMoneda();
	        nomNoVacios[pos++] = "Moneda";

	        v.noSonVacios(noVacios, nomNoVacios);
	        return v;
	    }

	    private void deshabilitarElementosConsultarEliminar() {
	        getBeanValuacionAcara().getTfAnio().setDisabled(true);
	        getBeanValuacionAcara().getTfValor().setDisabled(true);
	        getBeanValuacionAcara().getTfModelo().setDisabled(true);
	        getBeanValuacionAcara().getDdMoneda().setDisabled(true);
	        getBeanValuacionAcara().getBtnSeleccionarModelo().setRendered(false);
	        getBeanValuacionAcara().getBtnLimpiarModelo().setRendered(false);
	        getBeanValuacionAcara().getPanelAtributoDinamico().deshabilitarCampos();
	        getBeanValuacionAcara().getTaComentarioLogAuditoria().setRendered(false);
			getBeanValuacionAcara().getLblComentarioLogAuditoria().setRendered(false);
	    }

	    public class AgregarController extends AgregarAbstractController {

	        @Override
	        public Validador getValidador() {
	            return getValidadorAgregarModificar();
	        }

	        @Override
	        public String accionBotonAceptar(Object pObject) throws Exception {
	            ValuacionAcara locValuacionAcara = (ValuacionAcara) pObject;
	            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(getSessionBean1().getLlave());
	            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().addValuacionAcara(locValuacionAcara);
	            return "La Valuación Acara se agreg\363 correctamente";
	        }

	        @Override
	        public void ocultarDeshabilitarEnVista() {
	        	getBeanValuacionAcara().getLblFechaBaja().setRendered(false);
            	getBeanValuacionAcara().getTfFechaBaja().setRendered(false);
	        }

			@Override
			public ABMModel getModel() {
				return ValuacionAcaraModel.this;
			}
	    }

	    public class ModificarController extends ModificarAbstractController {

	        @Override
	        public Validador getValidador() {
	            return getValidadorAgregarModificar();
	        }

	        @Override
	        public String accionBotonAceptar(Object pObject) throws Exception {
	            ValuacionAcara locValuacionAcara = (ValuacionAcara) pObject;
	            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(getSessionBean1().getLlave());
	            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().updateValuacionAcara(locValuacionAcara);
	            return "La Valuación Acara se modific\363 correctamente";
	        }

	        @Override
	        public void ocultarDeshabilitarEnVista() {
	        	getBeanValuacionAcara().getLblFechaBaja().setRendered(false);
            	getBeanValuacionAcara().getTfFechaBaja().setRendered(false);
	        }
	        
	        @Override
			public ABMModel getModel() {
				return ValuacionAcaraModel.this;
			}
	    }

	    public class EliminarController extends EliminarAbstractController {

	        @Override
	        public Validador getValidador() {
	            return null;
	        }

	        @Override
	        public String accionBotonAceptar(Object pObject) throws Exception {
	        	ValuacionAcara locValuacionAcara = (ValuacionAcara) pObject;
	            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().setLlave(getSessionBean1().getLlave());
	            getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().deleteValuacionAcara(locValuacionAcara);
	            return "La Valuación Acara se elimin\363 correctamente";
	        }

	        @Override
	        public void ocultarDeshabilitarEnVista() {
	            deshabilitarElementosConsultarEliminar();
	            getBeanValuacionAcara().getLblFechaBaja().setRendered(false);
            	getBeanValuacionAcara().getTfFechaBaja().setRendered(false);
	        }
	        
	        @Override
			public ABMModel getModel() {
				return ValuacionAcaraModel.this;
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
	            ValuacionAcara locValuacion = (ValuacionAcara) getBeanValuacionAcara().getElementoPila().getObjetos().get(0);
	            if(locValuacion.getFechaBaja() != null){
	            	getBeanValuacionAcara().getLblFechaBaja().setRendered(true);
	            	getBeanValuacionAcara().getTfFechaBaja().setRendered(true);
	            }else{
	            	getBeanValuacionAcara().getLblFechaBaja().setRendered(false);
	            	getBeanValuacionAcara().getTfFechaBaja().setRendered(false);
	            }
	        }
	        
	        @Override
			public ABMModel getModel() {
				return ValuacionAcaraModel.this;
			}
	    }

		@Override
		public String getReglaNavegacion() {
			return "ABMValuacionAcara";
		}

		@Override
		public String getNombreEntidad() {
			return "Valuación ACARA";
		}

}
