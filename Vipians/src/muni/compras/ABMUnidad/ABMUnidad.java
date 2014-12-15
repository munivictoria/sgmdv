/*
 * ABMUnidad.java
 *
 * Created on 22 de noviembre de 2006, 09:53
 * Copyright Trascender
 */
package muni.compras.ABMUnidad;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.compras.recurso.persistent.suministros.Unidad;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ABMUnidad extends ABMPageBean {

    private Label lblDescripcion = new Label();

    public Label getLblDescripcion() {
        return lblDescripcion;
    }

    public void setLblDescripcion(Label lblDescripcion) {
        this.lblDescripcion = lblDescripcion;
    }
    
    private Label label5 = new Label();

    public Label getLabel5() {
        return label5;
    }

    public void setLabel5(Label l) {
        this.label5 = l;
    }
 
    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }

    private TextField tfDescripcion = new TextField();

    public TextField getTfDescripcion() {
        return tfDescripcion;
    }

    public void setTfDescripcion(TextField tfDescripcion) {
        this.tfDescripcion = tfDescripcion;
    }

    public ABMUnidad() {
    }

    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        ep.getObjetos().add(ind++, new Unidad());

        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
        Unidad unidad = (Unidad) this.obtenerObjetoDelElementoPila(0, Unidad.class);

        unidad.setDescripcion(getTextFieldValue(this.getTfDescripcion()));

        this.getElementoPila().getObjetos().set(0, unidad);
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {       
        int ind = 0;
        Unidad unidad = (Unidad) this.obtenerObjetoDelElementoPila(ind++, Unidad.class);
        
        this.getTfDescripcion().setText(unidad.getDescripcion());
    }
    
    @Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		this.getElementoPila().getObjetos().set(0, pObject);
	}

    @Override
    protected String getCasoNavegacion() {
        return "ABMUnidad";
    }

    @Override
	public long getSerialVersionUID() {
		return Unidad.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{compras$ABMUnidad$ABMUnidad}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Unidad locUnidad = this.obtenerObjetoDelElementoPila(0, Unidad.class);
		this.getTablaLogs().getLdpLogs().setList(locUnidad.getListaLogsAuditoria());
	}
}