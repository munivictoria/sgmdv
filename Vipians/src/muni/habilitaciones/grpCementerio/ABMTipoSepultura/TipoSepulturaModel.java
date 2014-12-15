package muni.habilitaciones.grpCementerio.ABMTipoSepultura;

import javax.faces.component.UIComponent;

import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
*
* @author nico
*/
public class TipoSepulturaModel extends ABMModel{
	private ABMTipoSepultura getBeanTipoSepultura() {
		return (ABMTipoSepultura) getRequestBean("habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[3];
        String[] nomNoVacios = new String[3];
        int pos = 0;
        noVacios[pos] = getBeanTipoSepultura().getTfCodigo();
        nomNoVacios[pos++] = "Código";
        noVacios[pos] = getBeanTipoSepultura().getTaDescripcion();
        nomNoVacios[pos++] = "Descripción";
        noVacios[pos] = getBeanTipoSepultura().getTfValor();
        nomNoVacios[pos++] = "Valor";
        
        v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMTipoSepultura abmTipoSepultura = getBeanTipoSepultura();
		
		abmTipoSepultura.getTfCodigo().setDisabled(true);
		abmTipoSepultura.getTaDescripcion().setStyleClass("textFieldDisabled");
		abmTipoSepultura.getTaDescripcion().setDisabled(true);
		abmTipoSepultura.getTfMinimo().setDisabled(true);
		abmTipoSepultura.getTfValor().setDisabled(true);
		abmTipoSepultura.getcPorcentual().setDisabled(true);
		abmTipoSepultura.getTaComentarioLogAuditoria().setRendered(false);
		abmTipoSepultura.getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarTipoSepulturaController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoSepultura locTipoSepultura = (TipoSepultura) pObject;
			
			getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(getSessionBean1().getLlave());
			TipoObligacion tipoObligacion = getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().findListaTipoObligacion("CEMENTERIO", null).get(0);
			locTipoSepultura.setTipoObligacion(tipoObligacion);
			
			getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().setLlave(getSessionBean1().getLlave());
			getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().addTipoSepultura(locTipoSepultura);
			
			return "El tipo de sepultura se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return TipoSepulturaModel.this;
		}
	}

	public class ModificarTipoSepulturaController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoSepultura locTipoSepultura = (TipoSepultura) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().setLlave(getSessionBean1().getLlave());
			getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().updateTipoSepultura(locTipoSepultura);

			return "El tipo de sepultura se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}
		
		@Override
		public ABMModel getModel() {
			return TipoSepulturaModel.this;
		}
	}

	public class ConsultarTipoSepulturaController extends ConsultarAbstractController {

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
			return TipoSepulturaModel.this;
		}
	}

	public class EliminarTipoSepulturaController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			TipoSepultura locTipoSepultura = (TipoSepultura) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().setLlave(getSessionBean1().getLlave());
			getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().deleteTipoSepultura(locTipoSepultura);

			return "El tipo de sepultura se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}
		
		@Override
		public ABMModel getModel() {
			return TipoSepulturaModel.this;
		}
	}

	@Override
	public String getReglaNavegacion() {
		return "ABMTipoSepultura";
	}

	@Override
	public String getNombreEntidad() {
		return "Tipo Sepultura";
	}
}
