/**
 * AgregarZonificacion.java
 *
 * Created on 24 de octubre de 2006, 15:21
 * Copyright Trascender
 */
package muni.catastro.ABMZonificacion;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ABMZonificacion extends ABMPageBean {

    private TextField tfNombre = new TextField();

    public TextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(TextField tf) {
        this.tfNombre = tf;
    }
    private Label label4 = new Label();

    public Label getLabel4() {
        return label4;
    }

    public void setLabel4(Label l) {
        this.label4 = l;
    }

    private MessageGroup messageGroup1 = new MessageGroup();

    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }

    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
    }
 
    // CAMBIAR: Objetos administrados por la pagina
    //          Generar getters y setters.
    //          En el getter poner:
    //          if (this.objeto == null) this.objeto = new Objeto();
    /*
    private Zonificacion zonificacionABM = null;

    public Zonificacion getZonificacionABM() {
        if (this.zonificacionABM == null) {
            this.zonificacionABM = new Zonificacion();
        }
        return this.zonificacionABM;
    }

    public void setZonificacionABM(Zonificacion pZonificacion) {
        this.zonificacionABM = pZonificacion;
    }
     * */
    
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        ep.getObjetos().add(ind++, null);

        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
        Zonificacion zonificacion = (Zonificacion)this.obtenerObjetoDelElementoPila(0, Zonificacion.class);

        zonificacion.setNombre(this.getTextFieldValue(getTfNombre()));
        
        this.getElementoPila().getObjetos().set(0, zonificacion);
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
        Zonificacion zonificacion = (Zonificacion)this.obtenerObjetoDelElementoPila(0, Zonificacion.class);

        this.getTfNombre().setText(zonificacion.getNombre());
    }
    
    /** 
     * <p>Construir una instancia de bean de p\225gina.</p>
     */
    public ABMZonificacion() {
    }

    @Override
    protected String getNombrePagina() {
        return "Zonificacion";
    }

    @Override
    protected String getCasoNavegacion() {
        return "ABMZonificacion";
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Zonificacion zonificacion = (Zonificacion) pObject;
        //this.setZonificacionABM(zonificacion);
        this.getElementoPila().getObjetos().set(0, zonificacion);
    }
	
	@Override
	public long getSerialVersionUID() {
		return Zonificacion.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{catastro$ABMZonificacion$ABMZonificacion}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Zonificacion locZonificacion = this.obtenerObjetoDelElementoPila(0, Zonificacion.class);
		this.getTablaLogs().getLdpLogs().setList(locZonificacion.getListaLogsAuditoria());
	}
}