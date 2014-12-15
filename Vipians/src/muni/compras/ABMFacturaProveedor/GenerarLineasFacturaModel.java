/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMFacturaProveedor;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;

import com.sun.data.provider.RowKey;
import com.trascender.compras.recurso.persistent.suministros.FacturaProveedor;
import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.compras.recurso.persistent.suministros.LineaFacturaPagoOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.PagoOrdenCompra;
import com.trascender.presentacion.abstracts.controller.ABMController;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

/**
 *
 * @author Fer Luca
 */
public class GenerarLineasFacturaModel extends ABMModel{
	
	@Override
	public String getReglaNavegacion() {
		return "GenerarLineasFactura";
	}

	@Override
	public String getNombreEntidad() {
		return "Lineas Factura";
	}
    
    private  GenerarLineasFactura getBeanGenerarLineasFactura(){
        return (GenerarLineasFactura) getRequestBean("compras$ABMFacturaProveedor$GenerarLineasFactura");
    }
    
    private Validador getValidadorAgregarModificar(){
         
        Validador v = new Validador();
        
        UIComponent[] noVacios = new UIComponent[1];
        String[] nomNoVacios = new String[1];

        int pos = 0;
        noVacios[pos] = getBeanGenerarLineasFactura().getTfOrdenCompra();
        nomNoVacios[pos++] = "Orden de Compra";

        v.noSonVacios(noVacios, nomNoVacios);
        
        if (getBeanGenerarLineasFactura().getTable2().isRendered() && getBeanGenerarLineasFactura().getTableRowGroup2().getSelectedRowKeys().length == 0){
            v.getErrores().add("Debe seleccionar al menos un Pago");
        }
        
        return v;
     }
     
     private void ocultarConsultarEliminar(){
     }
     
     public class GenerarLineasController implements ABMController {

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
            OrdenCompra locOrdenCompra = (OrdenCompra) getBeanGenerarLineasFactura().obtenerObjetoDelElementoPila(0, OrdenCompra.class);
            //List<PagoOrdenCompra> locListaPagos = (List<PagoOrdenCompra>) getBeanGenerarLineasFactura().obtenerObjetoDelElementoPila(1, ArrayList.class);
            
            if(locOrdenCompra.getListaPagosOrdenCompra().size() <= 1){
                getBeanGenerarLineasFactura().getRequestBean1().setObjetoSeleccion(locOrdenCompra);
            } 
            else {
                List<LineaFactura> locListaLineas = new ArrayList<LineaFactura>();
                FacturaProveedor factura = (FacturaProveedor) getBeanGenerarLineasFactura().getElementoPila().getObjetos().get(4);
                RowKey[] selectedRowKeys = getBeanGenerarLineasFactura().getTableRowGroup2().getSelectedRowKeys();
                
                for (int i = 0; i < selectedRowKeys.length; i++) {
                    String rowId = selectedRowKeys[i].getRowId();
                    RowKey rowKey = getBeanGenerarLineasFactura().getObjectListDataProviderPagos().getRowKey(rowId);
                    Object obj = getBeanGenerarLineasFactura().getObjectListDataProviderPagos().getObjects()[getBeanGenerarLineasFactura().getNroFila(rowKey.toString())];
                    PagoOrdenCompra cadaPago = (PagoOrdenCompra) obj;
                    cadaPago.setFactura(factura);
                    LineaFacturaPagoOrdenCompra nuevaLinea = new LineaFacturaPagoOrdenCompra();
                    nuevaLinea.setPagoOrdenCompra(cadaPago);
                    nuevaLinea.setMontoUnitario(cadaPago.getMonto());
                    nuevaLinea.setCantidad(new Double(1));
                    locListaLineas.add(nuevaLinea);
                }
                getBeanGenerarLineasFactura().getRequestBean1().setObjetoSeleccion(locListaLineas);
                getBeanGenerarLineasFactura().getCommunicationComprasBean().setProveedorSeleccionado(((PagoOrdenCompra)getBeanGenerarLineasFactura().getObjectListDataProviderPagos().getList().get(0)).getOrdenCompra().getProveedor());
            }
            return "";
        }
        
        @Override
        public void ocultarDeshabilitarEnVista() {
            
        }

        @Override
        public boolean guardaEstadoObjetosUsados() {
            return true;
        }

        @Override
        public boolean mostrarBotonAceptar() {
            return true;
        }

        @Override
        public boolean mostrarBotonCancelar() {
            return true;
        }

        @Override
        public boolean mostrarStSeparador() {
            return true;
        }

        @Override
        public boolean recargarPaginaAdmin() {
            return false;
        }

        @Override
        public String getTextoBotonAceptar() {
            return "Generar Líneas";
        }

        @Override
        public String getTextoBotonCancelar() {
            return "Cancelar";
        }

        @Override
        public String getTituloPagina() {
            return "Orden Compra";
        }

        @Override
        public String getCodigoColores() {
            return Constantes.COLORES_AGREGAR;
        }

		@Override
		public boolean seleccionarObjeto() {
			return true;
		}

		@Override
		public ABMModel getModel() {
			return GenerarLineasFacturaModel.this;
		}
         
     }

}
