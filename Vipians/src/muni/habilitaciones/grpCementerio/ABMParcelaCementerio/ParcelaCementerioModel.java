package muni.habilitaciones.grpCementerio.ABMParcelaCementerio;

import javax.faces.component.UIComponent;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;

public class ParcelaCementerioModel extends ABMModel{

	private ABMParcelaCementerio getBeanParcelaCementerio(){
		return (ABMParcelaCementerio) getRequestBean("habilitaciones$grpCementerio$ABMParcelaCementerio$ABMParcelaCementerio");
	}
	
	private Validador getValidadorAgregarModificar() {
        Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[1];
        String[] nomNoVacios = new String[1];

        int pos = 0;
        noVacios[pos] = getBeanParcelaCementerio().getTfTipoSepultura();
        nomNoVacios[pos++] = "Tipo Sepultura";
        
        v.noSonVacios(noVacios, nomNoVacios);
        
        return v;
	}
	
	private void deshabilitarElementosConsultarEliminar() {
		getBeanParcelaCementerio().getTfSuperficie().setDisabled(true);
		getBeanParcelaCementerio().getBtnSeleccionarTipoSepultura().setRendered(false);
		getBeanParcelaCementerio().getBtnAgregarDifunto().setRendered(false);
		getBeanParcelaCementerio().getBtnQuitarDifunto().setRendered(false);
		getBeanParcelaCementerio().getBtnQuitarTodosDifunto().setRendered(false);
		getBeanParcelaCementerio().getBtnModificarDifunto().setRendered(false);
		getBeanParcelaCementerio().getStaticText1().setRendered(false);
		getBeanParcelaCementerio().getGroupPanel2().setRendered(false);
		getBeanParcelaCementerio().getTableColumn2().setRendered(false);
		getBeanParcelaCementerio().getTaDescripcion().setDisabled(true);
		getBeanParcelaCementerio().getTfFechaInscripcion().setDisabled(true);
		getBeanParcelaCementerio().getTfFechaFin().setDisabled(true);
		getBeanParcelaCementerio().getDdTipoConcesion().setDisabled(true);
		getBeanParcelaCementerio().getPanelAtributoDinamico().deshabilitarCampos();
		getBeanParcelaCementerio().getTaComentarioLogAuditoria().setRendered(false);
		getBeanParcelaCementerio().getLblComentarioLogAuditoria().setRendered(false);
	}
	
	public class AgregarParcelaCementerioController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ParcelaCementerio locParcelaCementerio = (ParcelaCementerio) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().setLlave(getSessionBean1().getLlave());
			getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().addParcelaCementerio(locParcelaCementerio);
			return "La Parcela Cementerio se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			
		}

		@Override
		public ABMModel getModel() {
			return ParcelaCementerioModel.this;
		}
	}
	
	public class ModificarParcelaCementerioController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ParcelaCementerio locParcelaCementerio = (ParcelaCementerio) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().setLlave(getSessionBean1().getLlave());
			getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().updateParcelaCementerio(locParcelaCementerio);
			return "La Parcela Cementerio se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			
		}
		
		@Override
		public ABMModel getModel() {
			return ParcelaCementerioModel.this;
		}
	}
	
	public class ConsultarParcelaCementerioController extends ConsultarAbstractController {

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
			return ParcelaCementerioModel.this;
		}
	}
	
	public class EliminarParcelaCementerioController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			ParcelaCementerio locParcelaCementerio = (ParcelaCementerio) pObject;
			getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().setLlave(getSessionBean1().getLlave());
			getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().deleteParcelaCementerio(locParcelaCementerio);
			return "La Parcela Cementerio se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}
		
		@Override
		public ABMModel getModel() {
			return ParcelaCementerioModel.this;
		}
	}

	@Override
	public String getReglaNavegacion() {
		return "ABMParcelaCementerio";
	}

	@Override
	public String getNombreEntidad() {
		return "Parcela Cementerio";
	}
}
