package muni.compras.ABMUsuarioFirmante;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorOrdenCompra;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMUsuarioFirmante extends ABMPageBean{
    
    private Label lblUsuario = new Label();
    private Label lblImprimeOrdenNueva = new Label();
    private TextField tfUsuario = new TextField();
    private Button btnSeleccionarUsuario = new Button();
    private Checkbox cbImprimeOrdenNueva = new Checkbox();
    
    
	public Label getLblImprimeOrdenNueva() {
		return lblImprimeOrdenNueva;
	}

	public void setLblImprimeOrdenNueva(Label lblImprimeOrdenNueva) {
		this.lblImprimeOrdenNueva = lblImprimeOrdenNueva;
	}

	public Checkbox getCbImprimeOrdenNueva() {
		return cbImprimeOrdenNueva;
	}

	public void setCbImprimeOrdenNueva(Checkbox cbImprimeOrdenNueva) {
		this.cbImprimeOrdenNueva = cbImprimeOrdenNueva;
	}

	public Label getLblUsuario() {
		return lblUsuario;
	}

	public void setLblUsuario(Label lblUsuario) {
		this.lblUsuario = lblUsuario;
	}

	public TextField getTfUsuario() {
		return tfUsuario;
	}

	public void setTfUsuario(TextField tfUsuario) {
		this.tfUsuario = tfUsuario;
	}

	public Button getBtnSeleccionarUsuario() {
		return btnSeleccionarUsuario;
	}

	public void setBtnSeleccionarUsuario(Button btnSeleccionarUsuario) {
		this.btnSeleccionarUsuario = btnSeleccionarUsuario;
	}

	@Override
    protected void guardarEstadoObjetosUsados() {
		UsuarioAutorizadorOrdenCompra locUsuarioFirmante = (UsuarioAutorizadorOrdenCompra) obtenerObjetoDelElementoPila(0, UsuarioAutorizadorOrdenCompra.class);
		if (this.getCbImprimeOrdenNueva().isChecked()){
			locUsuarioFirmante.setImprimeOrdenNueva(true);
		}
        this.getElementoPila().getObjetos().set(0, locUsuarioFirmante);
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
    	UsuarioAutorizadorOrdenCompra locUsuarioFirmante = (UsuarioAutorizadorOrdenCompra) obtenerObjetoDelElementoPila(0, UsuarioAutorizadorOrdenCompra.class);
        this.getTfUsuario().setText(locUsuarioFirmante.getUsuario());
        if(locUsuarioFirmante.isImprimeOrdenNueva()){
        	this.getCbImprimeOrdenNueva().setSelected(true);
        }
    }

    @Override
    protected String getCasoNavegacion() {
        return "ABMUsuarioFirmante";
    }

    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        ep.getObjetos().add(ind++, new UsuarioAutorizadorOrdenCompra());
        return ep;
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		UsuarioAutorizadorOrdenCompra locUsuarioFirmante = (UsuarioAutorizadorOrdenCompra) obtenerObjetoDelElementoPila(0, UsuarioAutorizadorOrdenCompra.class);
		
		if(pObject instanceof Usuario){
			locUsuarioFirmante.setUsuario((Usuario)pObject);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		UsuarioAutorizadorOrdenCompra locUsuarioFirmante = (UsuarioAutorizadorOrdenCompra) pObject;
		System.out.println("procesar objeto abm, usuario: " + locUsuarioFirmante.getUsuario());
        this.getElementoPila().getObjetos().set(0, locUsuarioFirmante);
	}
	
	public String btnSeleccionarUsuario_action() {
		return navegarParaSeleccionar("AdminUsuario");
	}
	
	@Override
	public long getSerialVersionUID() {
		return UsuarioAutorizadorOrdenCompra.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMUsuarioFirmante$ABMUsuarioFirmante}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();
		
		UsuarioAutorizadorOrdenCompra locUsuarioAutorizador = this.obtenerObjetoDelElementoPila(0, UsuarioAutorizadorOrdenCompra.class);
		this.getTablaLogs().getLdpLogs().setList(locUsuarioAutorizador.getListaLogsAuditoria());
	}
}