
package muni.habilitaciones.grpAutomotor.ABMValuacionAcara;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.ValuacionAcara;
import com.trascender.habilitaciones.recurso.persistent.transito.ValuacionAcara.Moneda;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

public class ABMValuacionAcara extends ABMPageBean {

	protected void _init() throws Exception {
		Option[] opMoneda = null;
		opMoneda = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(ValuacionAcara.Moneda.values(), "may");
		ddMonedaDefaultOptions.setOptions(opMoneda);
	}

	private Label lblAnio = new Label();
	private Label lblValor = new Label();
	private Label lblMoneda = new Label();
	private Label lblModelo = new Label();
	private Label lblFechaBaja = new Label();

	private TextField tfAnio = new TextField();
	private TextField tfValor = new TextField();
	private TextField tfFechaBaja = new TextField();
	private TextField tfModelo = new TextField();

	DropDown ddMoneda = new DropDown();
	private SingleSelectOptionsList ddMonedaDefaultOptions = new SingleSelectOptionsList();

	private HtmlAjaxCommandButton btnLimpiarModelo = new HtmlAjaxCommandButton();
	private Button btnSeleccionarModelo = new Button();
	
	public TextField getTfModelo() {
		return tfModelo;
	}

	public void setTfModelo(TextField tfModelo) {
		this.tfModelo = tfModelo;
	}

	public HtmlAjaxCommandButton getBtnLimpiarModelo() {
		return btnLimpiarModelo;
	}

	public void setBtnLimpiarModelo(HtmlAjaxCommandButton btnLimpiarModelo) {
		this.btnLimpiarModelo = btnLimpiarModelo;
	}

	public Label getLblFechaBaja() {
		return lblFechaBaja;
	}

	public void setLblFechaBaja(Label lblFechaBaja) {
		this.lblFechaBaja = lblFechaBaja;
	}

	public TextField getTfFechaBaja() {
		return tfFechaBaja;
	}

	public void setTfFechaBaja(TextField tfFechaBaja) {
		this.tfFechaBaja = tfFechaBaja;
	}

	public Label getLblMoneda() {
		return lblMoneda;
	}

	public void setLblMoneda(Label lblMoneda) {
		this.lblMoneda = lblMoneda;
	}

	public Label getLblModelo() {
		return lblModelo;
	}

	public void setLblModelo(Label lblModelo) {
		this.lblModelo = lblModelo;
	}

	public SingleSelectOptionsList getDdMonedaDefaultOptions() {
		return ddMonedaDefaultOptions;
	}

	public void setDdMonedaDefaultOptions(SingleSelectOptionsList ddMonedaDefaultOptions) {
		this.ddMonedaDefaultOptions = ddMonedaDefaultOptions;
	}

	public Button getBtnSeleccionarModelo() {
		return btnSeleccionarModelo;
	}

	public void setBtnSeleccionarModelo(Button btnSeleccionarModelo) {
		this.btnSeleccionarModelo = btnSeleccionarModelo;
	}

	public Label getLblAnio() {
		return lblAnio;
	}

	public void setLblAnio(Label lblAnio) {
		this.lblAnio = lblAnio;
	}

	public Label getLblValor() {
		return lblValor;
	}

	public void setLblValor(Label lblValor) {
		this.lblValor = lblValor;
	}

	public TextField getTfAnio() {
		return tfAnio;
	}

	public void setTfAnio(TextField tfAnio) {
		this.tfAnio = tfAnio;
	}

	public TextField getTfValor() {
		return tfValor;
	}

	public void setTfValor(TextField tfValor) {
		this.tfValor = tfValor;
	}

	public DropDown getDdMoneda() {
		return ddMoneda;
	}

	public void setDdMoneda(DropDown ddMoneda) {
		this.ddMoneda = ddMoneda;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		ValuacionAcara locValuacionAcara = (ValuacionAcara) obtenerObjetoDelElementoPila(0, ValuacionAcara.class);
		Modelo locModelo = (Modelo) obtenerObjetoDelElementoPila(1, Modelo.class);
		ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(2, ArrayList.class);

		locValuacionAcara.setAnio(getTextFieldValueInteger(this.getTfAnio()));
		locValuacionAcara.setValor(getTextFieldValueDouble(this.getTfValor()));
		locValuacionAcara.setModelo(locModelo);
		locValuacionAcara.setMoneda(getDDEnumValue(this.getDdMoneda(), Moneda.class));

		atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		locValuacionAcara.setListaAtributosDinamicos(atributosDinamicos);

		this.getElementoPila().getObjetos().set(0, locValuacionAcara);
		this.getElementoPila().getObjetos().set(1, locModelo);
		this.getElementoPila().getObjetos().set(2, atributosDinamicos);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {

		ValuacionAcara locValuacionAcara = (ValuacionAcara) obtenerObjetoDelElementoPila(0, ValuacionAcara.class);
		Modelo modelo = (Modelo) this.obtenerObjetoDelElementoPila(1, Modelo.class);
		
		if(locValuacionAcara.getListaAtributosDinamicos() != null) {
			locValuacionAcara = (ValuacionAcara) this.obtenerObjetoDelElementoPila(0, ValuacionAcara.class);
			try {
				ArrayList atributosDinamicos = (ArrayList) this.getComunicationBean().getRemoteSystemParametro()
						.getAtributosPorRecurso(ValuacionAcara.serialVersionUID, locValuacionAcara.getListaAtributosDinamicos(), null);
				this.getElementoPila().getObjetos().set(2, atributosDinamicos);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}

		if(locValuacionAcara.getAnio() != null) {
			this.getTfAnio().setValue(locValuacionAcara.getAnio().toString());
		}
		if(locValuacionAcara.getValor() != null) {
			this.getTfValor().setValue(locValuacionAcara.getValor().toString());
		}
		if(modelo != null && modelo.getIdModelo() != -1) {
			this.getTfModelo().setText(modelo.toString());
		}
		if(locValuacionAcara.getMoneda() != null) {
			this.getDdMoneda().setSelected(locValuacionAcara.getMoneda().toString());
		}
		if(locValuacionAcara.getFechaBaja() != null) {
			this.getTfFechaBaja().setText(Conversor.getStringDeFechaCorta(locValuacionAcara.getFechaBaja()));
		}

		ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(2, ArrayList.class);

		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMValuacionAcara";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new ValuacionAcara());
		ep.getObjetos().add(ind++, null);	// modelo
		ep.getObjetos().add(ind++, new ArrayList()); // AtributosDinamicos
		return ep;
	}

	public String btnSeleccionarModelo_action() {
		return navegarParaSeleccionar("AdminModelo");
	}

	public String btnLimpiarModelo_action() {
		String retorno = null;

		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(1, this.getTfModelo());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof Modelo) {
			Modelo locModelo = (Modelo) pObject;
			this.getElementoPila().getObjetos().set(1, locModelo);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		ValuacionAcara locValuacion = (ValuacionAcara) pObject;
		
		this.getElementoPila().getObjetos().set(0, locValuacion);
		this.getElementoPila().getObjetos().set(1, locValuacion.getModelo());
		this.getElementoPila().getObjetos().set(2, locValuacion.getListaAtributosDinamicos());
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		ValuacionAcara locValuacionAcara = this.obtenerObjetoDelElementoPila(0, ValuacionAcara.class);
		this.getTablaLogs().getLdpLogs().setList(locValuacionAcara.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return ValuacionAcara.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpAutomotor$ABMValuacionAcara$ABMValuacionAcara}";
	}
	
	public void setModeloVehiculoAutocompletar(String pId, String pIdSubSesion) throws Exception {
		Long id = Long.parseLong(pId);
		Modelo modelo = null;

		try {
			modelo = (Modelo) this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().getModeloById(id);
		} catch(TrascenderFrameworkException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		}

		this.setIdSubSesion(Long.parseLong(pIdSubSesion));
		this.getElementoPila().getObjetos().set(1, modelo);
	}

	public boolean isHayModelo() {
		Modelo locModelo = (Modelo) this.obtenerObjetoDelElementoPila(1);
		return(locModelo != null && locModelo.getIdModelo() != -1);
	}
}