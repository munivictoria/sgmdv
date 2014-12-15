/*
 * ModificarCalle.java
 *
 * Created on 1 de noviembre de 2006, 09:48
 * Copyright Trascender
 */
package muni.habilitaciones.grpAutomotor.ABMMarca;

import java.util.ArrayList;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.habilitaciones.recurso.persistent.transito.Marca;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

public class ABMMarca extends ABMPageBean {
    
    private TextField tfCodigo = new TextField();
    private TextField tfNombre = new TextField();
    
    private Label lblCodigo = new Label();
    private Label lblNombre = new Label();

    public Label getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(Label lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public Label getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(Label lblNombre) {
        this.lblNombre = lblNombre;
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

    public void setTfNombre(TextField tf) {
        this.tfNombre = tf;
    }
    
    public ABMMarca() {
    }

   
    @Override
    protected void guardarEstadoObjetosUsados() {
        // CAMBIAR: Obtener los valores de los campos y
        //          asignarlos a los atributos de los objetos de la pagina
        Marca locMarca = (Marca) this.obtenerObjetoDelElementoPila(0, Marca.class);
        ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
        
        locMarca.setCodigo(getTextFieldValue(this.getTfCodigo()));
        locMarca.setNombre(getTextFieldValue(this.getTfNombre()));

        atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
        locMarca.setListaAtributosDinamicos(atributosDinamicos);
        
        this.getElementoPila().getObjetos().set(0, locMarca);
        this.getElementoPila().getObjetos().set(1, atributosDinamicos);
    }
    
    @Override
    protected void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Crear una instancia por cada objeto manejado en la pagina
        Marca locMarca = (Marca) this.obtenerObjetoDelElementoPila(0, Marca.class);
        ArrayList atributosDinamicos =  (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
//        ArrayList atributosDinamicos = null;
        
        if (locMarca.getListaAtributosDinamicos() != null) {
        	
            locMarca = (Marca) this.obtenerObjetoDelElementoPila(0, Marca.class);
                try {
                    atributosDinamicos = (ArrayList) this.getComunicationBean()
                            .getRemoteSystemParametro()
                            .getAtributosPorRecurso(Marca.serialVersionUID, locMarca.getListaAtributosDinamicos(), null);
                    this.getElementoPila().getObjetos().set(1, atributosDinamicos);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        this.getTfCodigo().setText(locMarca.getCodigo());
        this.getTfNombre().setText(locMarca.getNombre());
        
        atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);

        panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca}");
        panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);
        this.setListaDelCommunicationAtributosDinamicos(atributosDinamicos);
    }
    protected ArrayList getListaDelCommunicationAtributosDinamicos() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getComunicationBean().getListaAtributosDinamicosMarca();
    }

    private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getComunicationBean().setListaAtributosDinamicosMarca(lista);
    }
    
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;
        ep.getObjetos().add(ind++, new Marca());
        ep.getObjetos().add(ind++, new ArrayList()); //AtributosDinamicos
        return ep;
    }

    @Override
    protected String getCasoNavegacion() {
        return "ABMMarca";
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Marca locMarca = (Marca) this.obtenerObjetoDelElementoPila(0, Marca.class);
        ArrayList atributosDinamicos =  null;
        
		locMarca = (Marca) pObject;
        this.getElementoPila().getObjetos().set(0, locMarca);
        this.getElementoPila().getObjetos().set(1, atributosDinamicos);
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Marca locMarca = this.obtenerObjetoDelElementoPila(0, Marca.class);
		this.getTablaLogs().getLdpLogs().setList(locMarca.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return Marca.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpAutomotor$ABMMarca$ABMMarca}";
	}
}
