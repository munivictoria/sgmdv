/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.ABMDocumentoCatalogo;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.expedientes.recurso.persistent.DocumentoCatalogo;
import com.trascender.framework.recurso.persistent.reporteDinamico.Reporte;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMDocumentoCatalogo extends ABMPageBean {

	TextField tfNombre = new TextField();
	Label lblNombre = new Label();

	private Label lblTipo = new Label();
	private TextField tfTipo = new TextField();
	private Label lblReporte = new Label();
	private TextField tfReporte = new TextField();
	private Button btnBuscarReporte = new Button();
	private HtmlAjaxCommandButton btnLimpiarReporte = new HtmlAjaxCommandButton();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	public Label getLblTipo() {
		return lblTipo;
	}

	public void setLblTipo(Label lblTipo) {
		this.lblTipo = lblTipo;
	}

	public TextField getTfTipo() {
		return tfTipo;
	}

	public void setTfTipo(TextField tfTipo) {
		this.tfTipo = tfTipo;
	}

	public Label getLblReporte() {
		return lblReporte;
	}

	public void setLblReporte(Label lblReporte) {
		this.lblReporte = lblReporte;
	}

	public TextField getTfReporte() {
		return tfReporte;
	}

	public void setTfReporte(TextField tfReporte) {
		this.tfReporte = tfReporte;
	}

	public Button getBtnBuscarReporte() {
		return btnBuscarReporte;
	}

	public void setBtnBuscarReporte(Button btnBuscarReporte) {
		this.btnBuscarReporte = btnBuscarReporte;
	}

	public HtmlAjaxCommandButton getBtnLimpiarReporte() {
		return btnLimpiarReporte;
	}

	public void setBtnLimpiarReporte(HtmlAjaxCommandButton btnLimpiarReporte) {
		this.btnLimpiarReporte = btnLimpiarReporte;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		DocumentoCatalogo locDocumentoCatalogo = obtenerObjetoDelElementoPila(ind++, DocumentoCatalogo.class);
		String textNombre = this.getTextFieldValue(getTfNombre());

		if(textNombre != null) {
			textNombre = capitalizeOnlyFirst(textNombre);
		}
		locDocumentoCatalogo.setNombre(textNombre);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, locDocumentoCatalogo);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		DocumentoCatalogo locDocumentoCatalogo = (DocumentoCatalogo) this.obtenerObjetoDelElementoPila(ind++, DocumentoCatalogo.class);

		if(this.getRequestBean1().getObjeto() != null) {
			locDocumentoCatalogo.setTipo((DocumentoCatalogo.Tipo) this.getRequestBean1().getObjeto());

			this.getElementoPila().getObjetos().set(0, locDocumentoCatalogo);
			this.getRequestBean1().setObjeto(null);
		}

		ind = 0;
		locDocumentoCatalogo = (DocumentoCatalogo) this.obtenerObjetoDelElementoPila(ind++, DocumentoCatalogo.class);

		if(locDocumentoCatalogo.getTipo() != null) {
			this.getTfTipo().setText(Util.getEnumNameFromString(String.valueOf(locDocumentoCatalogo.getTipo())));
		}

		this.getTfNombre().setText(locDocumentoCatalogo.getNombre());

		if(locDocumentoCatalogo.getReporte() != null && locDocumentoCatalogo.getReporte().getIdReporte() != -1) {
			this.getTfReporte().setText(locDocumentoCatalogo.getReporte().getNombre());
		}
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new DocumentoCatalogo());

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		DocumentoCatalogo locDocumentoCatalogo = (DocumentoCatalogo) this.obtenerObjetoDelElementoPila(0, DocumentoCatalogo.class);

		if(pObject instanceof Reporte) {
			if(pObject != null) {
				Reporte reporte = (Reporte) pObject;

				if(reporte.getTipo().equals(Reporte.Tipo.PDF)) {
					locDocumentoCatalogo.setReporte(reporte);
				} else {
					warn("El reporte debe ser de tipo PDF.");
				}
			}
		}

		this.getElementoPila().getObjetos().set(0, locDocumentoCatalogo);
	}

	public String btnBuscarReporte_action() {
		return navegarParaSeleccionar("AdminReporte");
	}

	public String btnLimpiarReporte_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.limpiarObjeto(getTfReporte());
			DocumentoCatalogo locDocumentoCatalogo = (DocumentoCatalogo) this.obtenerObjetoDelElementoPila(0, DocumentoCatalogo.class);
			locDocumentoCatalogo.setReporte(null);

			this.getElementoPila().getObjetos().set(0, locDocumentoCatalogo);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void procesarObjetoABM(Object pObject) {
		DocumentoCatalogo locDocumentoCatalogo = (DocumentoCatalogo) pObject;
		try {
			long idDocumentoCatalogo = locDocumentoCatalogo.getIdDocumentoCatalogo();
			this.getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(this.getSessionBean1().getLlave());
			locDocumentoCatalogo = this.getCommunicationExpedientesBean().getRemoteSystemCatalogos().getDocumentoCatalogoPorId(idDocumentoCatalogo);
		} catch(Exception ex) {
			error("No se pudo recuperar DocumentoCatalogo: " + ex.getMessage());
		}
		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, locDocumentoCatalogo);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMDocumentoCatalogo";
	}

	private String capitalizeOnlyFirst(String pString) {
		char[] charArray = pString.toCharArray();
		charArray[0] = Character.toUpperCase(charArray[0]);

		return new String(charArray);
	}

	@Override
	public String getNombreBean() {
		return "#{expedientes$ABMDocumentoCatalogo$ABMDocumentoCatalogo}";
	}

	@Override
	public long getSerialVersionUID() {
		return DocumentoCatalogo.serialVersionUID;
	}

}