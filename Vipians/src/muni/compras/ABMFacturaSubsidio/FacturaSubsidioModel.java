/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMFacturaSubsidio;

import com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio;
import com.trascender.presentacion.abstracts.controller.*;
import com.trascender.presentacion.validadores.Validador;
import java.util.ArrayList;
import javax.faces.component.UIComponent;
import muni.compras.ABMFacturaProveedor.ABMFacturaProveedor;
import muni.compras.ABMFacturaProveedor.FacturaProveedorModel;

/**
 *
 * @author ubuntero
 */
public class FacturaSubsidioModel extends ABMModel{
	
	@Override
	public String getReglaNavegacion() {
		return "ABMFacturaSubsidio";
	}

	@Override
	public String getNombreEntidad() {
		return "Factura Subsidio";
	}
    
    private ABMFacturaSubsidio getBeanFacturaSubsidio(){
        return (ABMFacturaSubsidio) getRequestBean("compras$ABMFacturaSubsidio$ABMFacturaSubsidio");
    }
    
    private Validador getValidadorAgregarModificar(){
        
        Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[3];
        String[] nomNoVacios = new String[3];

        int pos = 0;
        noVacios[pos] = getBeanFacturaSubsidio().getTfProveedor();
        nomNoVacios[pos++] = "Proveedor";
        noVacios[pos] = getBeanFacturaSubsidio().getTfDigestoMunicipal();
        nomNoVacios[pos++] = "Digesto Municipal";
        noVacios[pos] = getBeanFacturaSubsidio().getTfFecha();
        nomNoVacios[pos++] = "Fecha de Emisi\363n";

        v.noSonVacios(noVacios, nomNoVacios);

        UIComponent[] fechas = new UIComponent[1];
        String[] nomFechas = new String[1];

        fechas[0] = getBeanFacturaSubsidio().getTfFecha();
        nomFechas[0] = "Fecha de Emisi\363n";

        v.formatoFechaValido(fechas, nomFechas);

        if (getBeanFacturaSubsidio().getListaDelCommunication() == null || getBeanFacturaSubsidio().getListaDelCommunication().isEmpty()) {
            String msg = "Debe agregar al menos una L\355nea Factura .";
            v.getErrores().add(msg);
        }

        FacturaSubsidio facturaSubsidio = (FacturaSubsidio) getBeanFacturaSubsidio().obtenerObjetoDelElementoPila(0, FacturaSubsidio.class);

        if (!v.fechaNoMayorAFechaActual(facturaSubsidio.getFechaEmision(), "Fecha de Emisi\363n")) {
            getBeanFacturaSubsidio().getTfFecha().setValid(false);
        };

        return v;
    }
    
    private void deshabilitarElementosConsultarEliminar(){
        
        getBeanFacturaSubsidio().getTfCantidad().setDisabled(true);
        getBeanFacturaSubsidio().getTfCodigoProveedor().setDisabled(true);
        getBeanFacturaSubsidio().getTfDigestoMunicipal().setDisabled(true);
        getBeanFacturaSubsidio().getTfFecha().setDisabled(true);
        getBeanFacturaSubsidio().getTfMontoTotal().setDisabled(true);
        getBeanFacturaSubsidio().getTfNumeroFactura().setDisabled(true);
        getBeanFacturaSubsidio().getTfProveedor().setDisabled(true);
        getBeanFacturaSubsidio().getTfTotal().setDisabled(true);
        getBeanFacturaSubsidio().getDdTipoFactura().setDisabled(true);
        
        getBeanFacturaSubsidio().getGroupPanel1().setRendered(false);        
        getBeanFacturaSubsidio().getBtnSeleccionarProveedor().setRendered(false);
        getBeanFacturaSubsidio().getBtnLimpiarProveedor().setRendered(false);
        getBeanFacturaSubsidio().getBtnSeleccionarDigestoMunicipal().setRendered(false);
        getBeanFacturaSubsidio().getBtnLimpiarDigestoMunicipal().setRendered(false);        
        getBeanFacturaSubsidio().getTableColumn5().setRendered(false);
    }
    
    public class AgregarController extends AgregarAbstractController{
    	
        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            FacturaSubsidio locFacturaSubsidio = (FacturaSubsidio) pObject;
            getCommunicationComprasBean().getRemoteSystemAdministracionFacturaSubsidio().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemAdministracionFacturaSubsidio().addFacturaSubsidio(locFacturaSubsidio);
            return "El subsidio se agreg\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }

		@Override
		public ABMModel getModel() {
			return FacturaSubsidioModel.this;
		}
    }
    
    public class ModificarController extends ModificarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            FacturaSubsidio locFacturaSubsidio = (FacturaSubsidio) pObject;
            getCommunicationComprasBean().getRemoteSystemAdministracionFacturaSubsidio().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemAdministracionFacturaSubsidio().updateFacturaSubsidio(locFacturaSubsidio);
            return "El subsidio se modific\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }

		@Override
		public ABMModel getModel() {
			return FacturaSubsidioModel.this;
		}
    }
    
    public class EliminarController extends EliminarAbstractController{

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            FacturaSubsidio locFacturaSubsidio = (FacturaSubsidio) pObject;
            getCommunicationComprasBean().getRemoteSystemAdministracionFacturaSubsidio().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemAdministracionFacturaSubsidio().deleteFacturaSubsidio(locFacturaSubsidio);
            return "El subsidio se elimin\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            deshabilitarElementosConsultarEliminar();
        }

		@Override
		public ABMModel getModel() {
			return FacturaSubsidioModel.this;
		}
    }
    
    public class ConsultarController extends ConsultarAbstractController{

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
            getBeanFacturaSubsidio().getTaComentarioLogAuditoria().setRendered(false);
			getBeanFacturaSubsidio().getLblComentarioLogAuditoria().setRendered(false);
        }

		@Override
		public ABMModel getModel() {
			return FacturaSubsidioModel.this;
		}        
    }
}
