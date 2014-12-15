package muni.saic.ABMExencionObligacion;

import java.util.ArrayList;

import javax.faces.component.UIComponent;

import com.trascender.habilitaciones.recurso.persistent.CondicionAplicacionExencion;
import com.trascender.habilitaciones.recurso.persistent.CondicionAplicacionExencionNumeroCuota;
import com.trascender.habilitaciones.recurso.persistent.ExencionObligacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.presentacion.abstracts.controller.ABMController;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

public class ExencionObligacionModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMExencionObligacion";
	}

	@Override
	public String getNombreEntidad() {
		return "Exención de Obligación";
	}

	private ABMExencionObligacion getBeanExencion() {
		return (ABMExencionObligacion) getRequestBean("saic$ABMExencionObligacion$ABMExencionObligacion");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];


		int pos = 0;
		noVacios[pos] = getBeanExencion().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanExencion().getTfValor();
		nomNoVacios[pos++] = "Valor";

		v.noSonVacios(noVacios, nomNoVacios);

		ExencionObligacion locExencionObligacion = (ExencionObligacion) getBeanExencion().getElementoPila().getObjetos().get(0);
		
		if (locExencionObligacion.getValor() != null) {
			if (new Double(getBeanExencion().getTfValor().getText().toString())
					.compareTo(new Double(0)) <= 0)
				v.getErrores().add("El valor debe ser mayor a 0");
		}
		
		if(locExencionObligacion.getListaObligacion().isEmpty()){
			v.getErrores().add("Debe haber al menos una Obligación en la lista");
		}
		
		Object siempre = getBeanExencion().getDdSeAplica().getSelected();
		if (!new Boolean(siempre.toString())){
			if(locExencionObligacion.getListaCondicionAplicacion().isEmpty() && locExencionObligacion.getListaCondicionAplicacionNumeroCuota().isEmpty()){
				v.getErrores().add("Debe haber al menos una Condición de aplicación");
			} else if(!locExencionObligacion.getListaCondicionAplicacionNumeroCuota().isEmpty()){
				for(CondicionAplicacionExencionNumeroCuota cadaCondicion : locExencionObligacion.getListaCondicionAplicacionNumeroCuota()){
					if((cadaCondicion.getNumeroCuota() == null || cadaCondicion.getNumeroPeriodo() == null)
							|| (cadaCondicion.getNumeroCuota().intValue() == 0 || cadaCondicion.getNumeroPeriodo().intValue() == 0)){
						v.getErrores().add("Las Condiciónes de aplicación por período y cuota deben tener valores mayores a cero");
						break;
					}
				}
			}
		}

		return v;
	}
	
	private void mostrarOcultarTabla(){
		Object seleccionado = getBeanExencion().getDdSeAplica().getSelected();
		if(seleccionado != null){
			if(seleccionado.equals("true")){
				getBeanExencion().getTablaCondicionAplicacionExencion().setRendered(false);
				getBeanExencion().getLblCondicionAplicacionExencion().setRendered(false);
				getBeanExencion().getCommunicationSAICBean().setListaCondicionAplicacionExencion(new ArrayList());
			} else{
				getBeanExencion().getTablaCondicionAplicacionExencion().setRendered(true);
				getBeanExencion().getLblCondicionAplicacionExencion().setRendered(true);
			}
		}
	}
	
	private void mostrarOcultarBotones(ExencionObligacion pExencion){
		
		if(!pExencion.getListaObligacion().isEmpty()){
			try {
				String nombreTipoObligacion = null;
				if(!pExencion.getListaObligacion().isEmpty()){
					Obligacion locObligacion = pExencion.getListaObligacion().get(0);
					TipoObligacion locTipoObligacion = this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().getTipoObligacionFromObligacion(locObligacion);
					nombreTipoObligacion = locTipoObligacion.getNombre();
				}
				getBeanExencion().getBtnSeleccionarObligacionOSP().setRendered(nombreTipoObligacion == null || nombreTipoObligacion.equals("OYSP")? true:false);
				getBeanExencion().getBtnSeleccionarObligacionTGI().setRendered(nombreTipoObligacion == null || nombreTipoObligacion.equals("TGI")? true:false);
				getBeanExencion().getBtnSeleccionarObligacionPFO().setRendered(nombreTipoObligacion == null || nombreTipoObligacion.equals("PFO")? true:false);
				getBeanExencion().getBtnSeleccionarObligacionSHPS().setRendered(nombreTipoObligacion == null || nombreTipoObligacion.equals("SHPS")? true:false);
				getBeanExencion().getBtnSeleccionarObligacionAutomotor().setRendered(nombreTipoObligacion == null || nombreTipoObligacion.equals("AUTOMOTOR")? true:false);
				getBeanExencion().getBtnSeleccionarObligacionCementerio().setRendered(nombreTipoObligacion == null || nombreTipoObligacion.equals("CEMENTERIO")? true:false);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else{
			getBeanExencion().getBtnSeleccionarObligacionOSP().setRendered(true);
			getBeanExencion().getBtnSeleccionarObligacionTGI().setRendered(true);
			getBeanExencion().getBtnSeleccionarObligacionPFO().setRendered(true);
			getBeanExencion().getBtnSeleccionarObligacionSHPS().setRendered(true);
			getBeanExencion().getBtnSeleccionarObligacionAutomotor().setRendered(true);
			getBeanExencion().getBtnSeleccionarObligacionCementerio().setRendered(true);
			
			pExencion.setListaCondicionAplicacion(new ArrayList<CondicionAplicacionExencion>());
			getBeanExencion().getElementoPila().getObjetos().set(0, pExencion);
			getBeanExencion().getLdpCondicionAplicacionExencion().getList().clear();
			getBeanExencion().getCommunicationSAICBean().setListaCondicionAplicacionExencion(new ArrayList());
		}
	}

	private void deshabilitarElementosConsultarEliminar() {
		getBeanExencion().getTfNombre().setDisabled(true);
		getBeanExencion().getTfValor().setDisabled(true);
		getBeanExencion().getRbFijo().setDisabled(true);
		getBeanExencion().getRbPorcentual().setDisabled(true);
		getBeanExencion().getTaMotivo().setDisabled(true);
		getBeanExencion().getBtnSeleccionarDigesto().setRendered(false);
		getBeanExencion().getBtnLimpiarDigesto().setRendered(false);
		getBeanExencion().getDdSeAplica().setDisabled(true);
		getBeanExencion().getBtnSeleccionarObligacionAutomotor().setRendered(false);
		getBeanExencion().getBtnSeleccionarObligacionCementerio().setRendered(false);
		getBeanExencion().getBtnSeleccionarObligacionOSP().setRendered(false);
		getBeanExencion().getBtnSeleccionarObligacionPFO().setRendered(false);
		getBeanExencion().getBtnSeleccionarObligacionSHPS().setRendered(false);		
		getBeanExencion().getBtnSeleccionarObligacionTGI().setRendered(false);
		getBeanExencion().getPgCondicionAplicacionExencion().setRendered(false);
		getBeanExencion().getPgObligacion().setRendered(false);
		getBeanExencion().getTcRbObligacion().setRendered(false);
		getBeanExencion().getTcRbCondicionAplicacionExencion().setRendered(false);
		getBeanExencion().getTcRbCondicionAplicacionNumeroCuota().setRendered(false);
		getBeanExencion().getPgCondicionAplicacionNumeroCuota().setRendered(false);
		getBeanExencion().getTfNumeroCuota().setReadOnly(true);
		getBeanExencion().getTfNumeroPeriodo().setReadOnly(true);
		
		mostrarOcultarTabla();
	}

	public class AgregarExencionObligacionController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ExencionObligacion locExencion = (ExencionObligacion) pObject;
			getBeanExencion().getCommunicationHabilitacionesBean().getRemoteSystemExencion().addExencionObligacion(locExencion);
			return "La Exenci\363n se agreg\363 exitosamente.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ExencionObligacion locExencion = (ExencionObligacion) getBeanExencion().obtenerObjetoDelElementoPila(0);
			getBeanExencion().getPgCondicionAplicacionExencion().setRendered(!locExencion.getListaObligacion().isEmpty()? true: false);
			
			mostrarOcultarBotones(locExencion);
			mostrarOcultarTabla();
		}

		@Override
		public ABMModel getModel() {
			return ExencionObligacionModel.this;
		}

	}

	public class ModificarExencionObligacionController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ExencionObligacion locExencion = (ExencionObligacion) pObject;
			getBeanExencion().getCommunicationHabilitacionesBean().getRemoteSystemExencion().updateExencionObligacion(locExencion);
			return "La Exenci\363n se modific\363 exitosamente.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ExencionObligacion locExencion = (ExencionObligacion) getBeanExencion().obtenerObjetoDelElementoPila(0);
			getBeanExencion().getPgCondicionAplicacionExencion().setRendered(!locExencion.getListaObligacion().isEmpty()? true: false);
			
			mostrarOcultarBotones(locExencion);
			mostrarOcultarTabla();
		}

		@Override
		public ABMModel getModel() {
			return ExencionObligacionModel.this;
		}
	}

	public class ConsultarExencionObligacionController extends ConsultarAbstractController {

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
			return ExencionObligacionModel.this;
		}

	}

	public class TerminarExencionObligacionController implements ABMController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ExencionObligacion locExencion = (ExencionObligacion) pObject;
			getBeanExencion().getCommunicationHabilitacionesBean().getRemoteSystemExencion().deleteExencionObligacion(locExencion);
			return "La Exenci\363n se elimin\363 exitosamente.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return ExencionObligacionModel.this;
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
			return "Terminar";
		}

		@Override
		public String getTextoBotonCancelar() {
			return "Cancelar";
		}

		@Override
		public String getTituloPagina() {
			return "Terminar";
		}

		@Override
		public String getCodigoColores() {
			return Constantes.COLORES_ELIMINAR;
		}

		@Override
		public boolean seleccionarObjeto() {
			return true;
		}
	}
}
