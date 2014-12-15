/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.saic.ImprimirLiquidaciones;

import java.util.ArrayList;
import java.util.List;

import com.trascender.presentacion.abstracts.controller.ABMController;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;
import com.trascender.saic.recurso.persistent.RegistroCancelacionManual;

/**
 *
 * @author Fer Luca
 */
public class RegistroCancelacionManualModel extends ABMModel {

	private ABMRegistroCancelacionManual getBeanRegCancelacion() {
		return (ABMRegistroCancelacionManual) getRequestBean("saic$ImprimirLiquidaciones$ABMRegistroCancelacionManual");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		getBeanRegCancelacion().getTaComentario().setDisabled(true);
	}

	public class AgregarRegCancelacionController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			RegistroCancelacionManual locRegistro = (RegistroCancelacionManual) pObject;
			getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(getSessionBean1().getLlave());

			// en teoria le paso el cometario y la lista de liquidaciones a la logica
			// locRegistro.getComentario();
			List listaLiquidaciones = getBeanRegCancelacion().obtenerObjetoDelElementoPila(1, ArrayList.class);
			getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().addRegistroCancelacionManual(listaLiquidaciones, locRegistro.getComentario());
			
			String comentario = (String) getBeanRegCancelacion().getTaComentario().getText();
			
			return "Liquidacion/es marcada/s paga/s con exito.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanRegCancelacion().getStTitulo().setText("Marcar Liquidación Pagada");

		}

		@Override
		public ABMModel getModel() {
			return RegistroCancelacionManualModel.this;
		}

	}

	/**
	 * Es para eliminar Liquidaciones
	 **/
	public class EliminarRegCancelacionController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			String comentario = (String) getBeanRegCancelacion().getTaComentario().getText();
			getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(getSessionBean1().getLlave());
			List listaLiquidaciones = getBeanRegCancelacion().obtenerObjetoDelElementoPila(1, ArrayList.class);
			getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().eliminarLiquidacionesFisicamente(listaLiquidaciones, comentario);
			
			// se limpia la lista al eliminar porque seguian "seleccionados" de alguna forma y lanzaba error al querer manejar otra liquidacion...
			getCommunicationSAICBean().getSeleccionadosSeleccionMultipleImprimirLiquidaciones().clear();
			
			return "Liquidacion/es eliminada/s con exito.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanRegCancelacion().getStTitulo().setText("Eliminar Liquidación");
		}

		@Override
		public ABMModel getModel() {
			return RegistroCancelacionManualModel.this;
		}
	}

	public class MarcarImpagaController implements ABMController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			String comentario = (String) getBeanRegCancelacion().getTaComentario().getText();
			getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(getSessionBean1().getLlave());
			List listaLiquidaciones = getBeanRegCancelacion().obtenerObjetoDelElementoPila(1, ArrayList.class);
			getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().marcarImpaga(listaLiquidaciones, comentario);
			return "Liquidacion/es marcada/s impaga/s con exito.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanRegCancelacion().getStTitulo().setText("Marcar Liquidación Impaga");
		}

		@Override
		public boolean guardaEstadoObjetosUsados() {
			return false;
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
			return true;
		}

		@Override
		public String getTextoBotonAceptar() {
			return "Aceptar";
		}

		@Override
		public String getTextoBotonCancelar() {
			return "Cancelar";
		}

		@Override
		public String getTituloPagina() {
			// return "Marcar Impaga";
			return "";
		}

		@Override
		public String getCodigoColores() {
			return Constantes.ACCION_MODIFICAR;
		}

		@Override
		public ABMModel getModel() {
			return RegistroCancelacionManualModel.this;
		}

		@Override
		public boolean seleccionarObjeto() {
			return true;
		}
	}

	@Override
	public String getReglaNavegacion() {
		return "ABMRegistroCancelacionManual";
	}

	@Override
	public String getNombreEntidad() {
		return "Registro de Cancelaci\363n Manual";
	}
}
