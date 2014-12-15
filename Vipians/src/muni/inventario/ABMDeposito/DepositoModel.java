package muni.inventario.ABMDeposito;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.component.UIComponent;

import muni.inventario.ABMArticulo.ABMArticulo;

import com.sun.data.provider.RowKey;
import com.trascender.compras.recurso.persistent.inventario.Deposito;
import com.trascender.compras.recurso.persistent.inventario.Stock;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroConstante;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;


public class DepositoModel extends ABMModel{

	@Override
	public String getReglaNavegacion() {
		return "ABMDeposito";
	}

	@Override
	public String getNombreEntidad() {
		return "Depósito";
	}
	
	private ABMDeposito getBeanDeposito() {
		return (ABMDeposito) getRequestBean("inventario$ABMDeposito$ABMDeposito");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
        UIComponent[] locNoVacios = new UIComponent[3];
        String[] locNomNoVacios = new String[3];
        UIComponent[] esEntero = new UIComponent[1];
        String[] nomEsEntero = new String[1];

        int pos = 0;
        locNoVacios[pos] = getBeanDeposito().getTfNombre();
        locNomNoVacios[pos++] = "Nombre";
        locNoVacios[pos] = getBeanDeposito().getTfArea();
        locNomNoVacios[pos++] = "\301rea";
        locNoVacios[pos] = getBeanDeposito().getTfTelefono();
        locNomNoVacios[pos++] = "Tel\351fono";

        pos = 0;
        esEntero[pos] = getBeanDeposito().getTfTelefono();
        nomEsEntero[pos++] = "Tel\351fono";

        v.noSonVacios(locNoVacios, locNomNoVacios);
        v.sonEnteros(esEntero, nomEsEntero);

        Domicilio locDomicilio = (Domicilio) getBeanDeposito().obtenerObjetoDelElementoPila(2, Domicilio.class);
        if (locDomicilio.getLocalidad() == null) {
            String msg = "Debe seleccionar un Domicilio.";
            v.getErrores().add(msg);
        }
        
        return v;
	}
	
	private void deshabilitarElementosConsultarEliminar() {
		getBeanDeposito().getTfNombre().setDisabled(true);
		getBeanDeposito().getBtnSeleccionarArea().setRendered(false);
		getBeanDeposito().getBtnLimpiarArea().setRendered(false);
		getBeanDeposito().getBtnSeleccionarDomicilio().setRendered(false);
		getBeanDeposito().getBtnLimpiarDomicilio().setRendered(false);
		getBeanDeposito().getTfTelefono().setDisabled(true);
		getBeanDeposito().getTaDescripcion().setDisabled(true);
		getBeanDeposito().getTfCantidadAComprar().setDisabled(true);
		getBeanDeposito().getTfCantidadLimite().setDisabled(true);
		getBeanDeposito().getBtnQuitar().setRendered(false);
		getBeanDeposito().getBtnQuitarTodos().setRendered(false);
		getBeanDeposito().getBtnSeleccionarBien().setRendered(false);
		getBeanDeposito().getStSeparador3().setRendered(false);
		getBeanDeposito().getTableColumn2().setRendered(false);
		getBeanDeposito().getTableColumn1().setRendered(true);
		getBeanDeposito().getBtnMarcarFaltantes().setRendered(false);		
	}
	
	public class AgregarDepositoController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Deposito locDeposito = (Deposito) pObject;
            getCommunicationComprasBean().getRemoteSystemStock().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemStock().addDeposito(locDeposito);
            return "El Dep\363sito se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanDeposito().getTableStockEnDeposito().setRendered(true);
			getBeanDeposito().getLblStockEnDeposito().setRendered(true);
			getBeanDeposito().getTableColumn2().setRendered(false);
			getBeanDeposito().getTableColumn1().setRendered(true);
			getBeanDeposito().getBtnMarcarFaltantes().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return DepositoModel.this;
		}
		
	}
	
	public class ModificarDepositoController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Deposito locDeposito = (Deposito) pObject;
            getCommunicationComprasBean().getRemoteSystemStock().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemStock().updateDeposito(locDeposito);
            return "El Dep\363sito se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanDeposito().getTableStockEnDeposito().setRendered(true);
			getBeanDeposito().getLblStockEnDeposito().setRendered(true);
			getBeanDeposito().getTableColumn2().setRendered(false);
			getBeanDeposito().getTableColumn1().setRendered(true);
			getBeanDeposito().getBtnMarcarFaltantes().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return DepositoModel.this;
		}
		
	}
	
	public class ConsultarDepositoController extends ConsultarAbstractController {

		@Override
		public Validador getValidador() {
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
			getBeanDeposito().getTableStockEnDeposito().setRendered(true);
			getBeanDeposito().getLblStockEnDeposito().setRendered(true);
		}

		@Override
		public ABMModel getModel() {
			return DepositoModel.this;
		}
		
	}
	
	public class EliminarDepositoController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Deposito locDeposito = (Deposito) pObject;
            getCommunicationComprasBean().getRemoteSystemStock().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemStock().deleteDeposito(locDeposito);
            return "El Dep\363sito se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
			getBeanDeposito().getTableStockEnDeposito().setRendered(true);
			getBeanDeposito().getLblStockEnDeposito().setRendered(true);
			getBeanDeposito().getTableColumn2().setRendered(false);
			getBeanDeposito().getTableColumn1().setRendered(true);
		}

		@Override
		public ABMModel getModel() {
			return DepositoModel.this;
		}
		
	}
	
	public class CheckearStockController extends ConsultarAbstractController {

		
	   @Override
	   public String getTextoBotonCancelar() {
	       return "Cancelar";
	    }
		
		@Override
		public boolean mostrarStSeparador() {
			return true;
		}

		@Override
		public boolean mostrarBotonAceptar() {
			return true;
		}
	
		@Override
		public String getTextoBotonAceptar() {
			return "Generar Solicitud";
		}

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			System.out.println("boton aceptar");
			getCommunicationComprasBean().getRemoteSystemStock().setLlave(getSessionBean1().getLlave());
			
			Set<Stock> listaStocks = getBeanDeposito().getListaSeleccionados();
			System.out.println("listaStocks: " + listaStocks.size());
			
            return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

			getBeanDeposito().getTableStockEnDeposito().setRendered(true);
			getBeanDeposito().getLblStockEnDeposito().setRendered(true);
			getBeanDeposito().getTfNombre().setDisabled(true);
			getBeanDeposito().getBtnSeleccionarArea().setRendered(false);
			getBeanDeposito().getBtnLimpiarArea().setRendered(false);
			getBeanDeposito().getBtnSeleccionarDomicilio().setRendered(false);
			getBeanDeposito().getBtnLimpiarDomicilio().setRendered(false);
			getBeanDeposito().getTfTelefono().setDisabled(true);
			getBeanDeposito().getTaDescripcion().setDisabled(true);
			getBeanDeposito().getTfCantidadAComprar().setDisabled(true);
			getBeanDeposito().getTfCantidadLimite().setDisabled(true);
			getBeanDeposito().getBtnQuitar().setRendered(false);
			getBeanDeposito().getBtnQuitarTodos().setRendered(false);
			getBeanDeposito().getBtnSeleccionarBien().setRendered(false);
			getBeanDeposito().getStSeparador3().setRendered(false);
			getBeanDeposito().getTableColumn2().setRendered(true);
			getBeanDeposito().getTableColumn1().setRendered(false);
			getBeanDeposito().getBtnMarcarFaltantes().setRendered(true);
			
		}
		
		@Override
		public ABMModel getModel() {
			return DepositoModel.this;
		}
		
	}
}
