/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.habilitaciones.grpAutomotor.ABMTipoVehiculo;

import java.util.ArrayList;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

/**
 *
 * @author Fernando Gareis
 */
public class ABMTipoVehiculo extends ABMPageBean{
    
    private Label lblCodigo = new Label();
    private Label lblNombre = new Label();
    private Label lblDescripcion = new Label();
    
    private TextField tfCodigo = new TextField();
    private TextField tfNombre = new TextField();
    private TextArea taDescripcion = new TextArea();

    public Label getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(Label lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public Label getLblDescripcion() {
        return lblDescripcion;
    }

    public void setLblDescripcion(Label lblDescripcion) {
        this.lblDescripcion = lblDescripcion;
    }

    public Label getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(Label lblNombre) {
        this.lblNombre = lblNombre;
    }

    public TextArea getTaDescripcion() {
        return taDescripcion;
    }

    public void setTaDescripcion(TextArea taDescripcion) {
        this.taDescripcion = taDescripcion;
    }

    public TextField getTfCodigo() {
        return tfCodigo;
    }

    public void setTfCodigo(TextField tfCodigo) {
        this.tfCodigo = tfCodigo;
    }

    public TextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(TextField tfNombre) {
        this.tfNombre = tfNombre;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
        TipoVehiculo locTipoVehiculo = (TipoVehiculo) obtenerObjetoDelElementoPila(0, TipoVehiculo.class);
        ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
        
        locTipoVehiculo.setNombre(getTextFieldValue(this.getTfNombre()));
        locTipoVehiculo.setCodigo(getTextFieldValue(this.getTfCodigo()));
        locTipoVehiculo.setDescripcion(getTextAreaValue(this.getTaDescripcion()));
       
        atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
        locTipoVehiculo.setListaAtributosDinamicos(atributosDinamicos);
        
        this.getElementoPila().getObjetos().set(0, locTipoVehiculo);
        this.getElementoPila().getObjetos().set(1, atributosDinamicos);
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
        TipoVehiculo locTipoVehiculo = (TipoVehiculo) obtenerObjetoDelElementoPila(0, TipoVehiculo.class);
        ArrayList atributosDinamicos =  (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
        
        if (locTipoVehiculo.getListaAtributosDinamicos() != null) {
            try {
                atributosDinamicos = (ArrayList) this.getComunicationBean()
                        .getRemoteSystemParametro()
                        .getAtributosPorRecurso(TipoVehiculo.serialVersionUID, locTipoVehiculo.getListaAtributosDinamicos(), null);
                this.getElementoPila().getObjetos().set(1, atributosDinamicos);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        this.getTfNombre().setText(locTipoVehiculo.getNombre());
        this.getTfCodigo().setText(locTipoVehiculo.getCodigo());
        this.getTaDescripcion().setText(locTipoVehiculo.getDescripcion());
        
        atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
        panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo}");
        panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);
    }

    @Override
    protected String getCasoNavegacion() {
        return "ABMTipoVehiculo";
    }

    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        ep.getObjetos().add(ind++, new TipoVehiculo());
        ep.getObjetos().add(ind++, new ArrayList()); //AtributosDinamicos
        return ep;
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		TipoVehiculo locTipoVehiculo = (TipoVehiculo) pObject;
        this.getElementoPila().getObjetos().set(0, locTipoVehiculo);
	}
    
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		TipoVehiculo locTipoVehiculo = this.obtenerObjetoDelElementoPila(0, TipoVehiculo.class);
		this.getTablaLogs().getLdpLogs().setList(locTipoVehiculo.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return TipoVehiculo.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpAutomotor$ABMTipoVehiculo$ABMTipoVehiculo}";
	}
}
