package muni.excepciones.ABMRefinanciacion;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;
import com.trascender.saic.recurso.persistent.refinanciacion.DocumentoRefinanciacion;


public class RefinanciacionModel extends ABMModel{

	private ABMRefinanciacion getBeanRefinanciacion() {
		return (ABMRefinanciacion) getRequestBean("excepciones$ABMRefinanciacion$ABMRefinanciacion");
	}

	@Override
	public String getReglaNavegacion() {
		return "ABMRefinanciacion";
	}

	@Override
	public String getNombreEntidad() {
		return "Refinanciación";
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		getBeanRefinanciacion().getBtnActualizarDeuda().setRendered(false);
		getBeanRefinanciacion().getStSeparador11().setRendered(false);
	}

	public class ModificarRefinanciacionController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			return "La Refinanciaci\363n se modific\363 exitosamente.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanRefinanciacion().getBtnImprimirReconocimientoDeuda().setRendered(false);
			getBeanRefinanciacion().getStSeparador12().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return RefinanciacionModel.this;
		}
		
	}
	
	public class ConsultarRefinanciacionController extends ConsultarAbstractController {

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
			getBeanRefinanciacion().getStSeparador12().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return RefinanciacionModel.this;
		}
		
	}

	public class EliminarRefinanciacionController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DocumentoRefinanciacion locRefinanciacion = (DocumentoRefinanciacion) pObject;
			getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(getSessionBean1().getLlave());
			//TODO Traer de la otra version
//			getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().deleteRefinanciacion(locRefinanciacion);
			
			return "La Refinanciaci\363n se elimin\363 exitosamente.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
			getBeanRefinanciacion().getBtnImprimirCuotasGeneradas().setRendered(false);
			getBeanRefinanciacion().getTableColumn1().setRendered(false);
			getBeanRefinanciacion().getBtnImprimirReconocimientoDeuda().setRendered(false);
			getBeanRefinanciacion().getStSeparador12().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return RefinanciacionModel.this;
		}
		
	}
	
	public class DarDeBajaRefinanciacionController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DocumentoRefinanciacion locRefinanciacion = (DocumentoRefinanciacion) pObject;
			getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(getSessionBean1().getLlave());
			//TODO Traer de la otra version
//			getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().darDeBajaRefinanciacion(locRefinanciacion);
			
			return "La Refinanciaci\363n se di\363 de baja exitosamente.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
			getBeanRefinanciacion().getBtnImprimirCuotasGeneradas().setRendered(false);
			getBeanRefinanciacion().getTableColumn1().setRendered(false);
			getBeanRefinanciacion().getBtnImprimirReconocimientoDeuda().setRendered(false);
			getBeanRefinanciacion().getStSeparador12().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return RefinanciacionModel.this;
		}
		
		@Override
		public String getTituloPagina() {
			return "Dar de Baja " + this.getModel().getNombreEntidad();
		}
		
		@Override
		public String getTextoBotonAceptar() {
			return "Dar de Baja";
		}
		
	}
	
	public class DarDeAltaRefinanciacionController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			DocumentoRefinanciacion locRefinanciacion = (DocumentoRefinanciacion) pObject;
			getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(getSessionBean1().getLlave());
			//TODO Traer de la otra version
//			getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().darDeAltaRefinanciacion(locRefinanciacion);
			
			return "La Refinanciaci\363n se recuper\363 exitosamente.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
			getBeanRefinanciacion().getBtnImprimirCuotasGeneradas().setRendered(false);
			getBeanRefinanciacion().getTableColumn1().setRendered(false);
			getBeanRefinanciacion().getBtnImprimirReconocimientoDeuda().setRendered(false);
			getBeanRefinanciacion().getStSeparador12().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return RefinanciacionModel.this;
		}
		
		@Override
		public String getTituloPagina() {
			return "Dar de Alta " + this.getModel().getNombreEntidad();
		}
		
		@Override
		public String getTextoBotonAceptar() {
			return "Recuperar";
		}
		
	}
	
}