/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMBien;

import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ActivarAbstractController;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;
import javax.faces.component.UIComponent;

/**
 * 
 * @author fer
 */
public class BienModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMBien";
	}

	@Override
	public String getNombreEntidad() {
		return "Bien";
	}

	private ABMBien getBeanBien() {
		return (ABMBien) getRequestBean("compras$ABMBien$ABMBien");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[2];
		String[] nomNoVacios = new String[2];
		UIComponent[] flotantes = new UIComponent[1];
		String[] nomFlotantes = new String[1];
		int pos = 0;
		noVacios[pos] = getBeanBien().getTfNombre();
		nomNoVacios[pos++] = "Nombre";
		noVacios[pos] = getBeanBien().getDdUnidad();
		nomNoVacios[pos++] = "Unidad de Medida";

		pos = 0;
		flotantes[pos] = getBeanBien().getTfValorReferencial();
		nomFlotantes[pos++] = "Valor Referencial";

		v.noSonVacios(noVacios, nomNoVacios);
		v.sonFlotantes(flotantes, nomFlotantes);
		v.sonPositivos(flotantes, nomFlotantes);

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMBien abmBien = getBeanBien();
		abmBien.getTfNombre().setDisabled(true);
		abmBien.getTfUnidad().setDisabled(true);
		abmBien.getTaDescripcion().setDisabled(true);
		abmBien.getTfValorReferencial().setDisabled(true);
		abmBien.getBtnLimpiarUnidad().setRendered(false);
		abmBien.getBtnSeleccionarUnidad().setRendered(false);
		getBeanBien().getTableColumn1().setRendered(false);
		getBeanBien().getGroupPanel1().setRendered(false);
		getBeanBien().getGroupPanel1().setRendered(false);
		getBeanBien().getDdUnidad().setDisabled(true);
		getBeanBien().getDdTipo().setDisabled(true);
		getBeanBien().getTableColumnTipoBien().setRendered(false);
		getBeanBien().getGroupPanelTipoBien().setRendered(false);
		getBeanBien().getTaComentarioLogAuditoria().setRendered(false);
		getBeanBien().getLblComentarioLogAuditoria().setRendered(false);
	}

	public class AgregarBienController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Bien locBien = (Bien) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().addBien(locBien);
			return "El Bien se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return BienModel.this;
		}

	}

	public class ModificarBienController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Bien locBien = (Bien) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().updateBien(locBien);
			return "El Bien se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return BienModel.this;
		}
	}

	public class ConsultarBienController extends ConsultarAbstractController {

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
			return BienModel.this;
		}

	}

	public class EliminarBienController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Bien locBien = (Bien) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().deleteBien(locBien);
			return "El Bien se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return BienModel.this;
		}

	}

	public class ActivarBienController extends ActivarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Bien locBien = (Bien) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionBienes().restoreBien(locBien);
			return "El Bien se recuper\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return BienModel.this;
		}

	}
}
