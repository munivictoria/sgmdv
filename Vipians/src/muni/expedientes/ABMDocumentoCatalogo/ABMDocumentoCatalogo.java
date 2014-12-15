
package muni.expedientes.ABMDocumentoCatalogo;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.expedientes.recurso.persistent.DocumentoCatalogo;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMDocumentoCatalogo extends ABMPageBean {

	TextField tfNombre = new TextField();
	Label lblNombre = new Label();

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
		this.getTfNombre().setText(locDocumentoCatalogo.getNombre());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new DocumentoCatalogo());
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
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