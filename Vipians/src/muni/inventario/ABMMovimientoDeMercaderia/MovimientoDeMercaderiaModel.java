package muni.inventario.ABMMovimientoDeMercaderia;

import javax.faces.component.UIComponent;

import muni.inventario.ABMDeposito.ABMDeposito;

import com.arjuna.ats.internal.arjuna.objectstore.jdbc.postgresql_driver;
import com.trascender.compras.exception.TrascenderInventarioException;
import com.trascender.compras.recurso.persistent.inventario.LineaMovimientoMercaderia;
import com.trascender.compras.recurso.persistent.inventario.MovimientoDeMercaderia;
import com.trascender.compras.recurso.persistent.inventario.MovimientoDeMercaderia.Tipo;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;


public class MovimientoDeMercaderiaModel extends ABMModel{

	@Override
	public String getReglaNavegacion() {
		return "ABMMovimientoDeMercaderia";
	}

	@Override
	public String getNombreEntidad() {
		return "Movimiento De Mercadería";
	}

	private ABMMovimientoDeMercaderia getBeanMovimientoMerc() {
		return (ABMMovimientoDeMercaderia) getRequestBean("inventario$ABMMovimientoDeMercaderia$ABMMovimientoDeMercaderia");
	}
	
	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
        UIComponent[] locNoVacios = new UIComponent[3];
        String[] locNomNoVacios = new String[3];

        ABMMovimientoDeMercaderia locBean = getBeanMovimientoMerc();
        
        Object locTipoMovimiento = locBean.getDdTipoMovimiento().getSelected();
        MovimientoDeMercaderia.Tipo tipoMov = null;
        if(locTipoMovimiento != null && !locTipoMovimiento.toString().isEmpty()){
            tipoMov = MovimientoDeMercaderia.Tipo.valueOf(locTipoMovimiento.toString());
        }
      
        if (locTipoMovimiento != null && !locTipoMovimiento.toString().isEmpty()) {
             if (tipoMov.equals(Tipo.MOVIMIENTO)) {
                locNoVacios =  new UIComponent[5];
                locNomNoVacios = new String[5];
               }else{
            	   locNoVacios =  new UIComponent[4];
                   locNomNoVacios = new String[4];
               }
         }
        int pos = 0;
        locNoVacios[pos] = locBean.getTfFecha();
        locNomNoVacios[pos++] = "Fecha";
        locNoVacios[pos] = locBean.getDdTipoMovimiento();
        locNomNoVacios[pos++] = "Tipo";
        locNoVacios[pos] = locBean.getTfUsuario();
        locNomNoVacios[pos++] = "Usuario";

        if (locTipoMovimiento != null && !locTipoMovimiento.toString().isEmpty()) {
            if (tipoMov.equals(Tipo.INGRESO) || tipoMov.equals(Tipo.EGRESO)) {
                locNoVacios[pos] = locBean.getDdDepositoOrigen();
                locNomNoVacios[pos++] = "Depósito";
            } else if (tipoMov.equals(Tipo.MOVIMIENTO)) {
                locNoVacios[pos] = locBean.getDdDepositoOrigen();
                locNomNoVacios[pos++] = "Depósito";
                locNoVacios[pos] = locBean.getDdDepositoDestino();
                locNomNoVacios[pos++] = "Depósito Destino";
            }
        }

        v.noSonVacios(locNoVacios, locNomNoVacios);

        if(!v.fechaValida(locBean.getTfFecha().getText().toString(), "dd/MM/yyyy")){
            v.getErrores().add("La Fecha es inv\341lida.");
        }

        if(locBean.getDdDepositoDestino() != null){
            if(locBean.getDdDepositoOrigen() == locBean.getDdDepositoDestino()){
                v.getErrores().add("El Depósito no puede ser igual al Depósito Destino.");
            }
        }
        
        MovimientoDeMercaderia locMovimiento = (MovimientoDeMercaderia)getBeanMovimientoMerc().getElementoPila().getObjetos().get(0);
        for(LineaMovimientoMercaderia cadaLinea : locMovimiento.getListaLineasMovimientoMercaderia()){
        	System.out.println("cadaLinea en el validador: " + cadaLinea);
        	if(cadaLinea.getStock() == null){ 
        		try {
					throw new TrascenderInventarioException(145);
				} catch (TrascenderInventarioException e) {
					v.getErrores().add(e.getMessage());
					break;
				}
        	}
        	
        	try {
				locMovimiento.validarCantidades();
				locMovimiento.validarTiposStock();
			} catch (TrascenderException e) {
				v.getErrores().add(e.getMessage());
				break;
			}
        	
        	if(locMovimiento.getTipo() != null && locMovimiento.getTipo().equals(MovimientoDeMercaderia.Tipo.MOVIMIENTO) && cadaLinea.getStockDestino() == null){
        		try {
					throw new TrascenderInventarioException(146);
				} catch (TrascenderInventarioException e) {
					v.getErrores().add(e.getMessage());
					break;
				}
        	}
        	
        	if(cadaLinea.getLineaOrdenCompra() != null && (locMovimiento.getTipo().equals(MovimientoDeMercaderia.Tipo.MOVIMIENTO) || locMovimiento.getTipo().equals(MovimientoDeMercaderia.Tipo.EGRESO))){
        		v.getErrores().add("El Tipo debe ser MOVIMIENTO o ENGRESO, o genere las Líneas de Movimiento a partir de una Solicitud de Suministro");
        		break;
        	}else if(cadaLinea.getLineaSolicitudSuministro() != null && locMovimiento.getTipo().equals(MovimientoDeMercaderia.Tipo.INGRESO)){
        		v.getErrores().add("El Tipo debe ser INGRESO, o genere las Líneas de Movimiento a partir de una Orden de Compra");
        		break;
        	}
        }
        
        try {
			locMovimiento.validarCantidadesPrevias();
		} catch (TrascenderInventarioException e) {
			v.getErrores().add(e.getMessage());
		}
        
        return v;
	}
	
	private void deshabilitarElementosConsultarEliminar() {
		getBeanMovimientoMerc().getDdTipoMovimiento().setDisabled(true);
		getBeanMovimientoMerc().getDdDepositoOrigen().setDisabled(true);
		getBeanMovimientoMerc().getDdDepositoDestino().setDisabled(true);
		getBeanMovimientoMerc().getTaMotivo().setDisabled(true);
		getBeanMovimientoMerc().getGroupPanel1().setRendered(false);
		getBeanMovimientoMerc().getTfCantidad().setDisabled(true);
	}
	
	public class AgregarMovimientoController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			MovimientoDeMercaderia locMovimiento = (MovimientoDeMercaderia)pObject;
			getCommunicationComprasBean().getRemoteSystemStock().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemStock().addMovimientoDeMercaderia(locMovimiento);
            return "El Movimiento de Mercader\355a se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			if(getBeanMovimientoMerc().getDdTipoMovimiento().getSelected() != null){
				if(!getBeanMovimientoMerc().getDdTipoMovimiento().getSelected().toString().equals("MOVIMIENTO")){
					getBeanMovimientoMerc().getDdDepositoDestino().setDisabled(true);
				}else{
					getBeanMovimientoMerc().getDdDepositoDestino().setDisabled(false);
				}
			}else{
				getBeanMovimientoMerc().getDdDepositoDestino().setDisabled(true);
			}
			
			if(getBeanMovimientoMerc().getDdDepositoOrigen().getSelected() != null && !getBeanMovimientoMerc().getDdDepositoOrigen().getSelected().toString().equals("")){
				getBeanMovimientoMerc().getBtnSeleccionarSolSum().setDisabled(false);
				getBeanMovimientoMerc().getBtnSeleccionarOrdenCompra().setDisabled(false);
			}else{
				getBeanMovimientoMerc().getBtnSeleccionarSolSum().setDisabled(true);
				getBeanMovimientoMerc().getBtnSeleccionarOrdenCompra().setDisabled(true);
			}
			
			MovimientoDeMercaderia locMovimiento = getBeanMovimientoMerc().obtenerObjetoDelElementoPila(0, MovimientoDeMercaderia.class);
			boolean seleccionoOrdenOSolicitud = false;
			for(LineaMovimientoMercaderia cadaLinea : locMovimiento.getListaLineasMovimientoMercaderia()){
				if(cadaLinea.getLineaOrdenCompra() != null || cadaLinea.getLineaSolicitudSuministro() != null){
					seleccionoOrdenOSolicitud = true;
					getBeanMovimientoMerc().getBtnSeleccionarSolSum().setDisabled(true);
					getBeanMovimientoMerc().getBtnSeleccionarOrdenCompra().setDisabled(true);
					break;
				}
			}
			if(getBeanMovimientoMerc().getDdDepositoOrigen().getSelected() != null &&
					!getBeanMovimientoMerc().getDdDepositoOrigen().getSelected().toString().equals("") && !seleccionoOrdenOSolicitud){
				getBeanMovimientoMerc().getBtnSeleccionarSolSum().setDisabled(false);
				getBeanMovimientoMerc().getBtnSeleccionarOrdenCompra().setDisabled(false);
			}
		}

		@Override
		public ABMModel getModel() {
			return MovimientoDeMercaderiaModel.this;
		}
		
	}
	
	public class ModificarMovimientoController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			MovimientoDeMercaderia locMovimiento = (MovimientoDeMercaderia) pObject;
			getCommunicationComprasBean().getRemoteSystemStock().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemStock().updateMovimientoDeMercaderia(locMovimiento);
            return "El Movimiento de Mercader\355a se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			
			if(getBeanMovimientoMerc().getDdTipoMovimiento().getSelected()!= null && !getBeanMovimientoMerc().getDdTipoMovimiento().getSelected().toString().equals("MOVIMIENTO")){
				getBeanMovimientoMerc().getDdDepositoDestino().setDisabled(true);
			}else{
				getBeanMovimientoMerc().getDdDepositoDestino().setDisabled(false);
			}
			
			if(getBeanMovimientoMerc().getDdDepositoOrigen().getSelected() != null && !getBeanMovimientoMerc().getDdDepositoOrigen().getSelected().toString().equals("")){
				getBeanMovimientoMerc().getBtnSeleccionarSolSum().setDisabled(false);
				getBeanMovimientoMerc().getBtnSeleccionarOrdenCompra().setDisabled(false);
			}else{
				getBeanMovimientoMerc().getBtnSeleccionarSolSum().setDisabled(true);
				getBeanMovimientoMerc().getBtnSeleccionarOrdenCompra().setDisabled(true);
			}
			
			MovimientoDeMercaderia locMovimiento = getBeanMovimientoMerc().obtenerObjetoDelElementoPila(0, MovimientoDeMercaderia.class);
			boolean seleccionoOrdenOSolicitud = false;
			for(LineaMovimientoMercaderia cadaLinea : locMovimiento.getListaLineasMovimientoMercaderia()){
				if(cadaLinea.getLineaOrdenCompra() != null || cadaLinea.getLineaSolicitudSuministro() != null){
					seleccionoOrdenOSolicitud = true;
					getBeanMovimientoMerc().getBtnSeleccionarSolSum().setDisabled(true);
					getBeanMovimientoMerc().getBtnSeleccionarOrdenCompra().setDisabled(true);
					break;
				}
			}
			if(getBeanMovimientoMerc().getDdDepositoOrigen().getSelected() != null &&
					!getBeanMovimientoMerc().getDdDepositoOrigen().getSelected().toString().equals("") && !seleccionoOrdenOSolicitud){
				getBeanMovimientoMerc().getBtnSeleccionarSolSum().setDisabled(false);
				getBeanMovimientoMerc().getBtnSeleccionarOrdenCompra().setDisabled(false);
			}
		}

		@Override
		public ABMModel getModel() {
			return MovimientoDeMercaderiaModel.this;
		}
		
	}
	
	public class ConsultarMovimientoController extends ConsultarAbstractController {

		@Override
		public Validador getValidador() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();			
		}

		@Override
		public ABMModel getModel() {
			return MovimientoDeMercaderiaModel.this;
		}
		
	}
	
	public class EliminarMovimientoController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			MovimientoDeMercaderia locMovimiento = (MovimientoDeMercaderia) pObject;
			getCommunicationComprasBean().getRemoteSystemStock().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemStock().deleteMovimientoDeMercaderia(locMovimiento);
            return "El Movimiento de Mercader\355a se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return MovimientoDeMercaderiaModel.this;
		}
		
	}
}
