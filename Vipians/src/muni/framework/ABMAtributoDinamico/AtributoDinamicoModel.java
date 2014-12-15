/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMAtributoDinamico;

import java.util.List;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.presentacion.abstracts.controller.ABMController;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.utiles.Constantes;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author juanma
 */
public class AtributoDinamicoModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMAtributoDinamico";
	}

	@Override
	public String getNombreEntidad() {
		return "Atributo Dinamico";
	}

	private ABMAtributoDinamico getBeanAtributoDinamico() {
		return (ABMAtributoDinamico) getRequestBean("framework$ABMAtributoDinamico$ABMAtributoDinamico");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[3];
		String[] nomNoVacios = new String[3];
		int pos = 0;
		noVacios[pos] = getBeanAtributoDinamico().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanAtributoDinamico().getTfRecurso();
		nomNoVacios[pos++] = "Recurso";
		noVacios[pos] = getBeanAtributoDinamico().getDdTipo();
		nomNoVacios[pos++] = "Tipo";
		v.noSonVacios(noVacios, nomNoVacios);

		if (getBeanAtributoDinamico().getDdTipo().getSelected().equals(PlantillaAtributoDinamico.Tipo.LISTADO.toString())) {
			if (this.getComunicationBean().getListaDatosListadoAtributoDinamico().size() <= 1) {
				String msg = "Debe agregar al menos dos opciones al listado.";
				v.getErrores().add(msg);
			}
		}
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMAtributoDinamico abmAtributoDinamico = getBeanAtributoDinamico();
		abmAtributoDinamico.getTfNombre().setDisabled(true);
		abmAtributoDinamico.getTfRecurso().setDisabled(true);
		abmAtributoDinamico.getBtnSeleccionarRecurso().setRendered(false);
		abmAtributoDinamico.getBtnLimpiarRecurso().setRendered(false);
		abmAtributoDinamico.getDdTipo().setDisabled(true);
		abmAtributoDinamico.getCbRequerido().setDisabled(true);
		abmAtributoDinamico.getCbBusqueda().setDisabled(true);

		abmAtributoDinamico.getTfOpcion().setDisabled(true);
		abmAtributoDinamico.getBtnAgregarDato().setRendered(true);
		abmAtributoDinamico.getBtnQuitarDato().setRendered(true);
		abmAtributoDinamico.getTfOpcionTabla().setDisabled(true);
		abmAtributoDinamico.getRadioButton1().setDisabled(true);
		abmAtributoDinamico.getTfOpcion().setVisible(false);
		abmAtributoDinamico.getLabel4().setText("Opciónes Actuales");
		abmAtributoDinamico.getTaComentarioLogAuditoria().setDisabled(true);
	}

	public class AgregarAtributoDinamicoController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			getComunicationBean().getRemoteSystemParametro().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemParametro().addPlantillaAtributoDinamico((PlantillaAtributoDinamico) pObject);

			return "El atributo dinamico se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return AtributoDinamicoModel.this;
		}
	}

	public class ModificarAtributoDinamicoController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			getComunicationBean().getRemoteSystemParametro().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemParametro().updatePlantillaAtributoDinamico((PlantillaAtributoDinamico) pObject);
			return "El atributo dinamico se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ABMAtributoDinamico abmAtributoDinamico = getBeanAtributoDinamico();
			abmAtributoDinamico.getBtnSeleccionarRecurso().setRendered(false);
			abmAtributoDinamico.getBtnLimpiarRecurso().setRendered(false);
			abmAtributoDinamico.getDdTipo().setDisabled(true);
		}

		@Override
		public ABMModel getModel() {
			return AtributoDinamicoModel.this;
		}
	}

	public class ConsultarAtributoDinamicoController extends ConsultarAbstractController {

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
			return AtributoDinamicoModel.this;
		}
	}

	public class EliminarAtributoDinamicoController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			getComunicationBean().getRemoteSystemParametro().setLlave(getSessionBean1().getLlave());
			getComunicationBean().getRemoteSystemParametro().deletePlantillaAtributoDinamico((PlantillaAtributoDinamico) pObject);

			return "El atributo dinamico se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ABMAtributoDinamico abmAtributoDinamico = getBeanAtributoDinamico();
			abmAtributoDinamico.getTaComentarioLogAuditoria().setRendered(false);
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return AtributoDinamicoModel.this;
		}
	}

	public class SeleccionarRecursoAtributoDinamicoController implements ABMController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			getComunicationBean().getRemoteSystemParametro().deletePlantillaAtributoDinamico((PlantillaAtributoDinamico) pObject);
			getComunicationBean().getListaAtributosDinamicos();

			return "El atributo dinamico se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public boolean guardaEstadoObjetosUsados() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean mostrarBotonAceptar() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean mostrarBotonCancelar() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean mostrarStSeparador() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean recargarPaginaAdmin() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public String getTextoBotonAceptar() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public String getTextoBotonCancelar() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public String getTituloPagina() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public String getCodigoColores() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean seleccionarObjeto() {
			return false;
		}

		@Override
		public ABMModel getModel() {
			return AtributoDinamicoModel.this;
		}
	}
	
	public class AgregarAtributoDinamicoObjetoSeleccionController implements ABMController {

		@Override
		public Validador getValidador() {
			Validador v = new Validador();
			UIComponent[] noVacios = new UIComponent[2];
			String[] nomNoVacios = new String[2];
			int pos = 0;
			noVacios[pos] = getBeanAtributoDinamico().getTfNombre();
			nomNoVacios[pos++] = "Nombre";
			noVacios[pos] = getBeanAtributoDinamico().getDdTipo();
			nomNoVacios[pos++] = "Tipo";
			v.noSonVacios(noVacios, nomNoVacios);

			if (getBeanAtributoDinamico().getDdTipo().getSelected().equals(PlantillaAtributoDinamico.Tipo.LISTADO.toString())) {
				List locListaDatos = getBeanAtributoDinamico().obtenerObjetoDelElementoPila(2, List.class);
				if (locListaDatos.size() <= 1) {
					String msg = "Debe agregar al menos dos opciones al listado.";
					v.getErrores().add(msg);
				}
			}
			return v;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			getBeanAtributoDinamico().getRequestBean1().setObjetoSeleccion(pObject);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanAtributoDinamico().getLabel2().setRendered(false);
			getBeanAtributoDinamico().getTfRecurso().setRendered(false);
			getBeanAtributoDinamico().getBtnSeleccionarRecurso().setRendered(false);
			getBeanAtributoDinamico().getBtnLimpiarRecurso().setRendered(false);
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
			return "Guardar";
		}

		@Override
		public String getTextoBotonCancelar() {
			return "Cancelar";
		}

		@Override
		public String getTituloPagina() {
			return "Agregar Atributo Dinámico";
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
			return AtributoDinamicoModel.this;
		}
	}
	
	public class ModificarAtributoDinamicoObjetoSeleccionController implements ABMController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanAtributoDinamico().getBtnSeleccionarRecurso().setRendered(false);
			getBeanAtributoDinamico().getBtnLimpiarRecurso().setRendered(false);
			getBeanAtributoDinamico().getDdTipo().setDisabled(true);
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
			return "Guardar";
		}

		@Override
		public String getTextoBotonCancelar() {
			return "Cancelar";
		}

		@Override
		public String getTituloPagina() {
			return "Modificar Atributo Dinámico";
		}

		@Override
		public String getCodigoColores() {
			return Constantes.COLORES_MODIFICAR;
		}

		@Override
		public boolean seleccionarObjeto() {
			return true;
		}

		@Override
		public ABMModel getModel() {
			return AtributoDinamicoModel.this;
		}
	}
}