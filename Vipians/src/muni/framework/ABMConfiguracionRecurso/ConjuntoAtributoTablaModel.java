package muni.framework.ABMConfiguracionRecurso;

import com.trascender.framework.recurso.persistent.ConjuntoAtributoTabla;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class ConjuntoAtributoTablaModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMConjuntoAtributoTabla";
	}

	@Override
	public String getNombreEntidad() {
		return "Conjunto de Atributos Tabla";
	}

	private ABMConjuntoAtributoTabla getBeanConjuntoAtributoTabla() {
		return (ABMConjuntoAtributoTabla) getRequestBean("framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		
		ConjuntoAtributoTabla conjuntoAtributoTabla = (ConjuntoAtributoTabla) getBeanConjuntoAtributoTabla().getElementoPila().getObjetos().get(0);

		if(conjuntoAtributoTabla.getListaAtributosTabla().size() == 0) {
			v.getErrores().add("Debe agregar al menos una Opción en la tabla.");
		}
		
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMConjuntoAtributoTabla abmConfiguracionRecurso = getBeanConjuntoAtributoTabla();
		abmConfiguracionRecurso.getGroupPanel1().setRendered(false);
		abmConfiguracionRecurso.getTableColumn1().setRendered(false);
		abmConfiguracionRecurso.getTextField2().setDisabled(true);
		abmConfiguracionRecurso.getTextField3().setDisabled(true);
		abmConfiguracionRecurso.getDdTipoDato().setDisabled(true);
		abmConfiguracionRecurso.getTableColumn6().setRendered(false);
		abmConfiguracionRecurso.getGroupPanel2().setRendered(false);
		abmConfiguracionRecurso.getTextField4().setDisabled(true);
	}

	public class AgregarConjuntoAtributoTablaController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			getBeanConjuntoAtributoTabla().getRequestBean1().setObjetoSeleccion(pObject);
			return null;
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ConjuntoAtributoTablaModel.this;
		}
	}

	public class ModificarConjuntoAtributoTablaController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			getBeanConjuntoAtributoTabla().getRequestBean1().setObjetoSeleccion(pObject);
			return null;
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ConjuntoAtributoTablaModel.this;
		}
	}

	public class EliminarConjuntoAtributoTablaController extends EliminarAbstractController {

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
			return ConjuntoAtributoTablaModel.this;
		}
	}
	
}
