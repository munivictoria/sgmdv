package muni.habilitaciones.grpTipoParametro.ABMTipoParametroGrilla;

import javax.faces.component.UIComponent;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class TipoParametroGrillaFilaModel extends ABMModel{

	@Override
	public String getReglaNavegacion() {
		// TODO Auto-generated method stub
		return "ABMTipoParametroGrillaFila";
	}

	@Override
	public String getNombreEntidad() {
		// TODO Auto-generated method stub
		return "Par\341metro de Grilla Fila";
	}
	
	public Validador getValidadorAgregarModificar(){
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		
		int pos = 0;
		noVacios[pos] = getBeanTipoParametroGrillaFila().getTfNumero();
		nomNoVacios[pos++] = "Numero";
		noVacios[pos] = getBeanTipoParametroGrillaFila().getTaCondicion();
		nomNoVacios[pos++] = "Condicion";

		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}
	
	private ABMTipoParametroGrillaFila getBeanTipoParametroGrillaFila() {
		return (ABMTipoParametroGrillaFila) getRequestBean("habilitaciones$grpTipoParametro$ABMTipoParametroGrilla$ABMTipoParametroGrillaFila");
	}

	public class AgregarController extends AgregarAbstractController{
		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			getBeanTipoParametroGrillaFila().getRequestBean1().setRespuestaABM(pObject);
			return "";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return TipoParametroGrillaFilaModel.this;
		}
	}
	
	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			getBeanTipoParametroGrillaFila().getRequestBean1().setRespuestaABM(pObject);
			return null;
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return TipoParametroGrillaFilaModel.this;
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
		}

		@Override
		public ABMModel getModel() {
			return TipoParametroGrillaFilaModel.this;
		}
	}
	
	private void deshabilitarElementosConsultarEliminar() {
		getBeanTipoParametroGrillaFila().getTfNumero().setDisabled(true);
		getBeanTipoParametroGrillaFila().getTaCondicion().setDisabled(true);
		getBeanTipoParametroGrillaFila().getTableColumn1().setRendered(false);
		getBeanTipoParametroGrillaFila().getTfNumeroValor().setStyleClass("textFieldDisabled");
		getBeanTipoParametroGrillaFila().getTaCondicion().setStyleClass("textFieldDisabled");
		getBeanTipoParametroGrillaFila().getBtnAgregarValor().setRendered(false);
        getBeanTipoParametroGrillaFila().getBtnQuitarValor().setRendered(false);
        getBeanTipoParametroGrillaFila().getTableColumn2().setRendered(false);
        getBeanTipoParametroGrillaFila().getTaCondicion().setDisabled(true);
        getBeanTipoParametroGrillaFila().getTaCondicion().setStyleClass("textFieldDisabled");
        getBeanTipoParametroGrillaFila().getTaValor().setDisabled(true);
        getBeanTipoParametroGrillaFila().getTaValor().setStyleClass("textFieldDisabled");
        getBeanTipoParametroGrillaFila().getTaCondicionValor().setDisabled(true);
        getBeanTipoParametroGrillaFila().getTaCondicionValor().setStyleClass("textFieldDisabled");
        getBeanTipoParametroGrillaFila().getBtnAgregarValor().setRendered(false);
        getBeanTipoParametroGrillaFila().getBtnQuitarValor().setRendered(false);
	}
}
