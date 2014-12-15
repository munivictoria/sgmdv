package muni.expedientes.ABMEstadoTramite;

import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.expedientes.recurso.persistent.DocumentoCatalogo;
import com.trascender.expedientes.recurso.persistent.EstadoTramite;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMEstadoTramite extends ABMPageBean {

	TextField tfNombre = new TextField();
	Label lblNombre = new Label();
	private Checkbox cbCierreTramite = new Checkbox();
	private Label lblCierreTramite = new Label();
	
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

	public Checkbox getCbCierreTramite() {
		return cbCierreTramite;
	}

	public void setCbCierreTramite(Checkbox cbCierreTramite) {
		this.cbCierreTramite = cbCierreTramite;
	}

	public Label getLblCierreTramite() {
		return lblCierreTramite;
	}

	public void setLblCierreTramite(Label lblCierreTramite) {
		this.lblCierreTramite = lblCierreTramite;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new EstadoTramite());
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		EstadoTramite locEstadosTramite = obtenerObjetoDelElementoPila(ind++, EstadoTramite.class);
		
		locEstadosTramite.setNombre(this.getTextFieldValue(getTfNombre()));
		locEstadosTramite.setCierraTramite(getCbCierreTramite().isChecked());
		
		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, locEstadosTramite);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		EstadoTramite locEstadosTramite = (EstadoTramite) this.obtenerObjetoDelElementoPila(ind++, EstadoTramite.class);
		this.getTfNombre().setText(locEstadosTramite.getNombre());
		
		if (locEstadosTramite.isCierraTramite()){
			this.getCbCierreTramite().setSelected(true);
		}
		
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		EstadoTramite locEstadosTramite = (EstadoTramite) pObject;
		try {
			long idEstadosTramite = locEstadosTramite.getIdEstadoTramite();
			this.getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(this.getSessionBean1().getLlave());
			locEstadosTramite = this.getCommunicationExpedientesBean().getRemoteSystemCatalogos().getEstadosTramitePorId(idEstadosTramite);
		} catch(Exception ex) {
			error("No se pudo recuperar el estado tramite: " + ex.getMessage());
		}
		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, locEstadosTramite);
		
	}

	@Override
	protected String getNombrePagina() {
		return "Estados Trámite";
	}
	
	@Override
	protected String getCasoNavegacion() {
		return "ABMEstadoTramite";
	}

	@Override
	public String getNombreBean() {
		return "#{expedientes$ABMEstadoTramite$ABMEstadoTramite}";
	}

	@Override
	public long getSerialVersionUID() {
		return EstadoTramite.serialVersionUID;
	}

}
