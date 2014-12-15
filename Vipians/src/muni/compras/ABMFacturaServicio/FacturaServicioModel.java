/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMFacturaServicio;

import com.trascender.compras.recurso.persistent.suministros.FacturaServicio;
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
public class FacturaServicioModel extends ABMModel{
    
    private ABMFacturaServicio getBeanFacturaServicio(){
        return (ABMFacturaServicio) getRequestBean("compras$ABMFacturaServicio$ABMFacturaServicio");
    }
    
    private Validador getValidadorAgregarModificar(){
        
        Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[3];
        String[] nomNoVacios = new String[3];

        int pos = 0;
        noVacios[pos] = getBeanFacturaServicio().getTfProveedor();
        nomNoVacios[pos++] = "Proveedor";
        noVacios[pos] = getBeanFacturaServicio().getTfBien();
        nomNoVacios[pos++] = "Bien";
        noVacios[pos] = getBeanFacturaServicio().getTfFecha();
        nomNoVacios[pos++] = "Fecha de Emisi\363n";

        v.noSonVacios(noVacios, nomNoVacios);

        UIComponent[] fechas = new UIComponent[1];
        String[] nomFechas = new String[1];

        fechas[0] = getBeanFacturaServicio().getTfFecha();
        nomFechas[0] = "Fecha de Emisi\363n";

        v.formatoFechaValido(fechas, nomFechas);

        if (getBeanFacturaServicio().getListaDelCommunication() == null || getBeanFacturaServicio().getListaDelCommunication().isEmpty()) {
            String msg = "Debe agregar al menos una L\355nea Factura.";
            v.getErrores().add(msg);
        }

        FacturaServicio facturaServicio = (FacturaServicio) getBeanFacturaServicio().obtenerObjetoDelElementoPila(0, FacturaServicio.class);

        if (!v.fechaNoMayorAFechaActual(facturaServicio.getFechaEmision(), "Fecha de Emisi\363n")) {
            getBeanFacturaServicio().getTfFecha().setValid(false);
        }
        return v;
    }
    
    private void deshabilitarElementosConsultarEliminar(){
        
        getBeanFacturaServicio().getTfBien().setDisabled(true);
        getBeanFacturaServicio().getTfCantidad().setDisabled(true);
        getBeanFacturaServicio().getTfCodigoProveedor().setDisabled(true);
        getBeanFacturaServicio().getTfFecha().setDisabled(true);
        getBeanFacturaServicio().getTfMontoTotal().setDisabled(true);
        getBeanFacturaServicio().getTfNumeroFactura().setDisabled(true);
        getBeanFacturaServicio().getTfProveedor().setDisabled(true);
        getBeanFacturaServicio().getTfTotal().setDisabled(true);
        getBeanFacturaServicio().getDdTipoFactura().setDisabled(true);
        
        getBeanFacturaServicio().getTableColumn1().setRendered(false);
        getBeanFacturaServicio().getBtnSeleccionarBien().setRendered(false);
        getBeanFacturaServicio().getBtnSeleccionarProveedor().setRendered(false);
        getBeanFacturaServicio().getBtnLimpiarBien().setRendered(false);
        getBeanFacturaServicio().getBtnLimpiarProveedor().setRendered(false);
        getBeanFacturaServicio().getGroupPanel1().setRendered(false); 
    }
    
    public class AgregarController extends AgregarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            FacturaServicio locFacturasServicio = (FacturaServicio) pObject;
            getCommunicationComprasBean().getRemoteSystemAdministracionFacturaServicio().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemAdministracionFacturaServicio().addFacturaServicio(locFacturasServicio);
            return "La factura por servicio se agreg\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }
        
    	@Override
		public ABMModel getModel() {
			return FacturaServicioModel.this;
		}
    }
    
    public class ModificarController extends ModificarAbstractController{

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            FacturaServicio locFacturasServicio = (FacturaServicio) pObject;
            getCommunicationComprasBean().getRemoteSystemAdministracionFacturaServicio().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemAdministracionFacturaServicio().updateFacturaServicio(locFacturasServicio);
            return "La factura por servicio se modific\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }
        
    	@Override
		public ABMModel getModel() {
			return FacturaServicioModel.this;
		}
    }
    
    public class EliminarController extends EliminarAbstractController{

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            FacturaServicio locFacturasServicio = (FacturaServicio) pObject;
            getCommunicationComprasBean().getRemoteSystemAdministracionFacturaServicio().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemAdministracionFacturaServicio().deleteFacturaServicio(locFacturasServicio);
            return "La factura por servicio se elimin\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            deshabilitarElementosConsultarEliminar();
        }
    	
        @Override
		public ABMModel getModel() {
			return FacturaServicioModel.this;
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
            getBeanFacturaServicio().getTaComentarioLogAuditoria().setRendered(false);
			getBeanFacturaServicio().getLblComentarioLogAuditoria().setRendered(false);
        }
        
    	@Override
		public ABMModel getModel() {
			return FacturaServicioModel.this;
		}
    }

	@Override
	public String getReglaNavegacion() {
		return "ABMFacturaServicio";
	}

	@Override
	public String getNombreEntidad() {
		return "Factura Servicio";
	}
}
