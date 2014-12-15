package muni.compras.ABMLiquidacionCompra;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.suministros.LiquidacionCompra;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class LiquidacionCompraModel extends ABMModel{

	private ABMLiquidacionCompra getBeanLiquidacionCompra() {
        return (ABMLiquidacionCompra) getRequestBean("compras$ABMLiquidacionCompra$ABMLiquidacionCompra");
    }

    private Validador getValidadorAgregarModificar() {
    	Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		
		int pos = 0;
		noVacios[pos] = getBeanLiquidacionCompra().getTfNumero();
		nomNoVacios[pos++] = "Número";
		noVacios[pos] = getBeanLiquidacionCompra().getTfFecha();
		nomNoVacios[pos++] = "Fecha";

		v.noSonVacios(noVacios, nomNoVacios);
        return v;
    }

    private void deshabilitarElementosConsultarEliminar() {
    	this.getBeanLiquidacionCompra().getTfNumero().setDisabled(true);
    	this.getBeanLiquidacionCompra().getTfFecha().setDisabled(true);
    	this.getBeanLiquidacionCompra().getGroupPanel1().setRendered(false);
    	this.getBeanLiquidacionCompra().getTableColumn1().setRendered(false);
    	this.getBeanLiquidacionCompra().getTaComentarioLogAuditoria().setRendered(false);
    	this.getBeanLiquidacionCompra().getLblComentarioLogAuditoria().setRendered(false);
    }

    public class AgregarLiquidacionCompraController extends AgregarAbstractController {

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
        	LiquidacionCompra locLiquidacionCompra = (LiquidacionCompra) pObject;
            getCommunicationComprasBean().getRemoteSystemAdministracionFactura().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemAdministracionFactura().addLiquidacionCompra(locLiquidacionCompra);
            return "La Liquidaci\363n de Compra se agreg\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        	getBeanLiquidacionCompra().getTfNumero().setText("generar automáticamente");
        }

		@Override
		public ABMModel getModel() {
			return LiquidacionCompraModel.this;
		}
    }

    public class ModificarLiquidacionCompraController extends ModificarAbstractController {

        @Override
        public Validador getValidador() {
            return getValidadorAgregarModificar();
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
        	LiquidacionCompra locLiquidacionCompra = (LiquidacionCompra) pObject;
        	getCommunicationComprasBean().getRemoteSystemAdministracionFactura().setLlave(getSessionBean1().getLlave());
        	getCommunicationComprasBean().getRemoteSystemAdministracionFactura().updateLiquidacionCompra(locLiquidacionCompra);
            return "La Liquidaci\363n de Compra se modific\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }
        
        @Override
		public ABMModel getModel() {
			return LiquidacionCompraModel.this;
		}
    }

    public class EliminarLiquidacionCompraController extends EliminarAbstractController {

        @Override
        public Validador getValidador() {
            return null;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
        	LiquidacionCompra locLiquidacionCompra = (LiquidacionCompra) pObject;
        	getCommunicationComprasBean().getRemoteSystemAdministracionFactura().setLlave(getSessionBean1().getLlave());
        	getCommunicationComprasBean().getRemoteSystemAdministracionFactura().deleteLiquidacionCompra(locLiquidacionCompra);
            return "La Liquidaci\363n de Compra se elimin\363 exitosamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
            deshabilitarElementosConsultarEliminar();
        }
        
        @Override
		public ABMModel getModel() {
			return LiquidacionCompraModel.this;
		}
    }

    public class ConsultarLiquidacionCompraController extends ConsultarAbstractController {

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
			return LiquidacionCompraModel.this;
		}
    }

	@Override
	public String getReglaNavegacion() {
		return "ABMLiquidacionCompra";
	}

	@Override
	public String getNombreEntidad() {
		return "Liquidación de Compra";
	}
}
