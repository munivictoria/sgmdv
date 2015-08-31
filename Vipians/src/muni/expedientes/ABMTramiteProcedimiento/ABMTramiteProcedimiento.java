/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.ABMTramiteProcedimiento;

import java.util.Set;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;
import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMTramiteProcedimiento extends ABMPageBean {

	private TextField tfNombre = new TextField();
	private Label lblNombre = new Label();

	private Label lblNombreTramiteCatalogo = new Label();
	private DropDown ddTramiteCatalogo = new DropDown();
	private SingleSelectOptionsList ddTramiteCatalogoOptions = new SingleSelectOptionsList();
	private Button btnSeleccionarTramiteCatalogo = new Button();
	private HtmlAjaxCommandButton btnLimpiarTramiteCatalogo = new HtmlAjaxCommandButton();

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

	public Label getLblNombreTramiteCatalogo() {
		return lblNombreTramiteCatalogo;
	}

	public void setLblNombreTramiteCatalogo(Label lblNombreTramiteCatalogo) {
		this.lblNombreTramiteCatalogo = lblNombreTramiteCatalogo;
	}

	public DropDown getDdTramiteCatalogo() {
		return ddTramiteCatalogo;
	}

	public void setDdTramiteCatalogo(DropDown ddTramiteCatalogo) {
		this.ddTramiteCatalogo = ddTramiteCatalogo;
	}

	public SingleSelectOptionsList getDdTramiteCatalogoOptions() {
		return ddTramiteCatalogoOptions;
	}

	public void setDdTramiteCatalogoOptions(SingleSelectOptionsList ddTramiteCatalogoOptions) {
		this.ddTramiteCatalogoOptions = ddTramiteCatalogoOptions;
	}

	public Button getBtnSeleccionarTramiteCatalogo() {
		return btnSeleccionarTramiteCatalogo;
	}

	public void setBtnSeleccionarTramiteCatalogo(Button btnSeleccionarTramiteCatalogo) {
		this.btnSeleccionarTramiteCatalogo = btnSeleccionarTramiteCatalogo;
	}

	public HtmlAjaxCommandButton getBtnLimpiarTramiteCatalogo() {
		return btnLimpiarTramiteCatalogo;
	}

	public void setBtnLimpiarTramiteCatalogo(HtmlAjaxCommandButton btnLimpiarTramiteCatalogo) {
		this.btnLimpiarTramiteCatalogo = btnLimpiarTramiteCatalogo;
	}

	@Override
	protected void _init() throws Exception {
		Set<String> locListaTramiteCatalogo = this.getCommunicationExpedientesBean().getMapaTramiteCatalogo().keySet();
		Option[] opTramiteCatalogo = new Option[locListaTramiteCatalogo.size() + 1];
		int i = 0;
		opTramiteCatalogo[i++] = new Option("", "");
		for(String cadaCalle : locListaTramiteCatalogo) {
			opTramiteCatalogo[i++] = new Option(cadaCalle, cadaCalle);
		}
		this.ddTramiteCatalogoOptions.setOptions(opTramiteCatalogo);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		TramiteProcedimiento locTramiteProcedimiento = obtenerObjetoDelElementoPila(ind++, TramiteProcedimiento.class);
		// TramiteCatalogo locTramiteCatalogo =
		// obtenerObjetoDelElementoPila(ind++,
		// TramiteCatalogo.class);

		locTramiteProcedimiento.setTramiteCatalogo(this.getDDObjectValue(getDdTramiteCatalogo(), getCommunicationExpedientesBean().getMapaTramiteCatalogo()));

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, locTramiteProcedimiento);
		this.getElementoPila().getObjetos().set(ind++, locTramiteProcedimiento.getTramiteCatalogo());
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		TramiteProcedimiento locTramiteProcedimiento = (TramiteProcedimiento) this.obtenerObjetoDelElementoPila(ind++, TramiteProcedimiento.class);
		TramiteCatalogo locTramiteCatalogo = (TramiteCatalogo) this.obtenerObjetoDelElementoPila(ind++, TramiteCatalogo.class);

		this.getTfNombre().setText(locTramiteProcedimiento.getTramiteCatalogo().getNombre());
		if(locTramiteCatalogo != null) {
			this.ddTramiteCatalogo.setSelected(locTramiteCatalogo.getNombre() != null ? locTramiteCatalogo.getNombre() : "");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new TramiteProcedimiento());
		ep.getObjetos().add(ind++, new TramiteCatalogo());
		
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof TramiteCatalogo) {
			if(pObject != null) {
				int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
				this.getElementoPila().getObjetos().set(posicion, pObject);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void procesarObjetoABM(Object pObject) {
		TramiteProcedimiento locTramiteProcedimiento = (TramiteProcedimiento) pObject;
		TramiteCatalogo locTramiteCatalogo = (TramiteCatalogo) locTramiteProcedimiento.getTramiteCatalogo();
		try {
			long idTramiteProcedimiento = locTramiteProcedimiento.getIdNodoProcedimiento();
			this.getCommunicationExpedientesBean().getRemoteSystemProcedimientos().setLlave(this.getSessionBean1().getLlave());
			locTramiteProcedimiento = this.getCommunicationExpedientesBean().getRemoteSystemProcedimientos().getTramiteProcedimientoPorId(idTramiteProcedimiento);
		} catch(Exception ex) {
			error("No se pudo recuperar TramiteProcedimiento: " + ex.getMessage());
		}
		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, locTramiteProcedimiento);
		this.getElementoPila().getObjetos().set(ind++, locTramiteCatalogo);
	}

	@Override
	protected String getNombrePagina() {
		return "TramiteProcedimiento";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMTramiteProcedimiento";
	}

	public String btnSeleccionarTramiteCatalogo_action() {
		return navegarParaSeleccionar("AdminTramiteCatalogo", 1);
	}

	public String btnLimpiarTramiteCatalogo_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.ddTramiteCatalogo.setSelected(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		
		return retorno;
	}

	@Override
	public String getNombreBean() {
		return "#{expedientes$ABMTramiteProcedimiento$ABMTramiteProcedimiento}";
	}

	@Override
	public long getSerialVersionUID() {
		return TramiteProcedimiento.serialVersionUID;
	}
	
}