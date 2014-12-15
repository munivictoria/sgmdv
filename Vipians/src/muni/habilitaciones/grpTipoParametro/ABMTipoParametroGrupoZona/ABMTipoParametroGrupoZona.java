/*
 * AgregarTipoParametroGrupoZona.java
 *
 * Created on 18 de octubre de 2006, 10:30
 * Copyright Trascender SRL
 */
package muni.habilitaciones.grpTipoParametro.ABMTipoParametroGrupoZona;

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.rave.web.ui.component.Body;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Form;
import com.sun.rave.web.ui.component.Head;
import com.sun.rave.web.ui.component.HiddenField;
import com.sun.rave.web.ui.component.Html;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Link;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.Page;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.validadores.Validador;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import com.sun.rave.web.ui.model.Option;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP.UnidadMedida;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Script;
import com.trascender.catastro.recurso.filtros.FiltroZona;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.habilitaciones.recurso.persistent.TipoParametroGrupoZona;
import com.trascender.presentacion.utiles.Constantes;

public class ABMTipoParametroGrupoZona extends ABMPageBean {
    
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Agregar Par\341metro de Grupo de Zonas";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AgregarTipoParametroGrupoZona";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;    // </editor-fold>
    
    @Override
    protected void _init() throws Exception {

        if (this.getListaDelCommunication() != null) {
            this.getObjectListDataProvider().setList(this.getListaDelCommunication());
        }

        Option[] op = null;
        // Unidades de Medida
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(UnidadMedida.values(), "cap");
    }
    
    private TextField tfZonificacion = new TextField();

    public TextField getTfZonificacion() {
        return tfZonificacion;
    }

    public void setTfZonificacion(TextField tf) {
        this.tfZonificacion = tf;
    }
    private TextField tfNombre = new TextField();

    public TextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(TextField tf) {
        this.tfNombre = tf;
    }
    private Label lblZonificacion = new Label();

    public Label getLblZonificacion() {
        return lblZonificacion;
    }

    public void setLblZonificacion(Label l) {
        this.lblZonificacion = l;
    }
    private Label label4 = new Label();

    public Label getLabel4() {
        return label4;
    }

    public void setLabel4(Label l) {
        this.label4 = l;
    }
    private Button btnSeleccionarZonificacion = new Button();

    public Button getBtnSeleccionarZonificacion() {
        return btnSeleccionarZonificacion;
    }

    public void setBtnSeleccionarZonificacion(Button b) {
        this.btnSeleccionarZonificacion = b;
    }
    
    private MessageGroup messageGroup1 = new MessageGroup();

    public MessageGroup getMessageGroup1() {
        return messageGroup1;
    }

    public void setMessageGroup1(MessageGroup mg) {
        this.messageGroup1 = mg;
    }
    
    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }
    private TextField tfNombreVariable = new TextField();

    public TextField getTfNombreVariable() {
        return tfNombreVariable;
    }

    public void setTfNombreVariable(TextField tf) {
        this.tfNombreVariable = tf;
    }
    private Label label5 = new Label();

    public Label getLabel5() {
        return label5;
    }

    public void setLabel5(Label l) {
        this.label5 = l;
    }
    private Table table1 = new Table();

    public Table getTable1() {
        return table1;
    }

    public void setTable1(Table t) {
        this.table1 = t;
    }
    private TableRowGroup tableRowGroup1 = new TableRowGroup();

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup trg) {
        this.tableRowGroup1 = trg;
    }
    private ObjectListDataProvider ldpZonasTipoParamGrupoZona = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpZonasTipoParamGrupoZona() {
        return ldpZonasTipoParamGrupoZona;
    }

    public void setLdpZonasTipoParamGrupoZona(ObjectListDataProvider oldp) {
        this.ldpZonasTipoParamGrupoZona = oldp;
    }
    private TableColumn tableColumn1 = new TableColumn();

    public TableColumn getTableColumn1() {
        return tableColumn1;
    }

    public void setTableColumn1(TableColumn tc) {
        this.tableColumn1 = tc;
    }
    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    private TableColumn tableColumn2 = new TableColumn();

    public TableColumn getTableColumn2() {
        return tableColumn2;
    }

    public void setTableColumn2(TableColumn tc) {
        this.tableColumn2 = tc;
    }
    private TextField tfValor = new TextField();

    public TextField getTfValor() {
        return tfValor;
    }

    public void setTfValor(TextField tf) {
        this.tfValor = tf;
    }
    
    public ABMTipoParametroGrupoZona() {
    }

    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;

        //CAMBIAR: settear los objetos administrados por la pagina
        try {
            ep.getObjetos().add(ind++, new TipoParametroGrupoZona());
        } catch (Exception e) {
            log(CASO_NAVEGACION + "_AgregarObjetosEPError:", e);
            error(NOMBRE_PAGINA + " - AgregarObjetosEP: " + e.getMessage());
        }
        ep.getObjetos().add(ind++, new ArrayList()); // zonas

        ep.getObjetos().add(ind++, new ArrayList()); // lista de objetos GrupoZona (innerClass)

        ep.getObjetos().add(ind++, null); //zonificacion

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
        // CAMBIAR: Verificar el metodo completo.
        int ind = 0;
        TipoParametroGrupoZona tipoParametroGrupoZona = (TipoParametroGrupoZona) this.obtenerObjetoDelElementoPila(ind++, TipoParametroGrupoZona.class);
        List zonas = (List) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        List gruposZona = (List) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        Zonificacion zonificacion = (Zonificacion) this.obtenerObjetoDelElementoPila(ind++, Zonificacion.class);
        Map mapaZonas = null;

        Object nombre = this.getTfNombre().getText();
        Object nombreVariable = this.getTfNombreVariable().getText();

        if (nombre != null && nombre != "") {
            tipoParametroGrupoZona.setNombreGrupoZona(nombre.toString());
        } else {
            tipoParametroGrupoZona.setNombreGrupoZona(null);
        }
        if (nombreVariable != null && nombreVariable != "") {
            tipoParametroGrupoZona.setNombreVariable(nombreVariable.toString());
        } else {
            tipoParametroGrupoZona.setNombreVariable(null);
        }
        this.getObjectListDataProvider().commitChanges();
        if (this.getObjectListDataProvider().getObjects().length > 0) {
            mapaZonas = new HashMap();
            GrupoZona gz = null;
            for (int i = 0; i < this.getObjectListDataProvider().getObjects().length; i++) {
                gz = (GrupoZona) this.getObjectListDataProvider().getObjects()[i];
                mapaZonas.put(gz.getZona(), gz.getValor());
            }
        }

        String locZonificacion = this.tfZonificacion.toString();
        if (locZonificacion == null || locZonificacion.equals("")) {
            zonificacion = null;
        } else {
            tipoParametroGrupoZona.setZonificacion(zonificacion);
        }

        tipoParametroGrupoZona.setListaZonas(mapaZonas);
        this.setListaDelCommunication(gruposZona);

        ind = 0;
        this.getElementoPila().getObjetos().set(ind++, tipoParametroGrupoZona);
        this.getElementoPila().getObjetos().set(ind++, zonas);
        this.getElementoPila().getObjetos().set(ind++, gruposZona);
        this.getElementoPila().getObjetos().set(ind++, zonificacion);
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Verificar el metodo completo.
        int ind = 0;
        TipoParametroGrupoZona tipoParametroGrupoZona = null;
        List zonas = null;
        List gruposZona = null;
        Map mapaZonas = null;
        Zonificacion zonificacion = null;
        
//        if (this.getRequestBean1().getObjetoABM() != null) {
//            tipoParametroGrupoZona = (TipoParametroGrupoZona) this.getRequestBean1().getObjetoABM();
//
//            long idTipoParametroGrupoZona = tipoParametroGrupoZona.getIdTipoParametro();
//            try {
//                this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());
//                tipoParametroGrupoZona = this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().getTipoParametroGrupoZonaPorId(idTipoParametroGrupoZona);
//            } catch (Exception ex) {
//                log(CASO_NAVEGACION + "_getTipoParametroGrupoZonaPorId:", ex);
//                error(NOMBRE_PAGINA + " - No se pudo recuperar el Par\341metro de Grupo de Zonas: " + ex.getMessage());
//            }
//
//            mapaZonas = tipoParametroGrupoZona.getListaZonas();
//
//            GrupoZona gz = null;
//            for (int i = 0; i < mapaZonas.size(); i++) {
//                gz = new GrupoZona((Zona) mapaZonas.keySet().toArray()[i], (Double) mapaZonas.get(mapaZonas.keySet().toArray()[i]));
//                gruposZona.add(gz);
//            }
//
//            zonificacion = tipoParametroGrupoZona.getZonificacion();
//
//            ind = 0;
//            this.getElementoPila().getObjetos().set(ind++, tipoParametroGrupoZona);
//            this.getElementoPila().getObjetos().set(ind++, zonas);
//            this.getElementoPila().getObjetos().set(ind++, gruposZona);
//            this.getElementoPila().getObjetos().set(ind++, zonificacion);
//        }

        //zonificacion = (Zonificacion) this.obtenerObjetoDelElementoPila(3, Zonificacion.class);


        ind = 0;
        tipoParametroGrupoZona = (TipoParametroGrupoZona) this.obtenerObjetoDelElementoPila(ind++, TipoParametroGrupoZona.class);
        zonas = (List) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        gruposZona = (List) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
        zonificacion = (Zonificacion) this.obtenerObjetoDelElementoPila(ind++, Zonificacion.class);

        mapaZonas = tipoParametroGrupoZona.getListaZonas();
        if (mapaZonas != null) {
            if (gruposZona.size() > 0 && mapaZonas.size() > 0) {
                gruposZona.clear();
            }
        }
        GrupoZona gz = null;
        if (mapaZonas != null) {
            for (int i = 0; i < mapaZonas.size(); i++) {
                gz = new GrupoZona((Zona) mapaZonas.keySet().toArray()[i], (Double) mapaZonas.get(mapaZonas.keySet().toArray()[i]));
                gruposZona.add(gz);
            }
        }

        this.getTfNombre().setText(tipoParametroGrupoZona.getNombreGrupoZona());
        this.getTfNombreVariable().setText(tipoParametroGrupoZona.getNombreVariable());
        if (zonificacion != null) {
            this.getTfZonificacion().setText(zonificacion.getNombre());
        } else {
            this.getTfZonificacion().setText("");
        }

        this.getObjectListDataProvider().setList(gruposZona);
        this.setListaDelCommunication(gruposZona);
    }

    private ObjectListDataProvider getObjectListDataProvider() {
        // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente        
        return this.getLdpZonasTipoParamGrupoZona();
    }

    private List getListaDelCommunication() {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        return this.getCommunicationHabilitacionesBean().getListaZonasTipoParamGrupoZona();
    }

    private void setListaDelCommunication(List lista) {
        // CAMBIAR: Utilizar la Lista del Comunication que corresponda
        this.getCommunicationHabilitacionesBean().setListaZonasTipoParamGrupoZona(lista);
    }
    
    private void cargarValoresPorDefecto() {
        // CAMBIAR: Obtener los valores por defecto.
        List zonas = (List) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
        Zonificacion zonificacion = (Zonificacion) this.obtenerObjetoDelElementoPila(3, Zonificacion.class);
        List gruposZona = new ArrayList();
        try {
            this.getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().setLlave(this.getSessionBean1().getLlave());
            if (zonificacion != null) {
                if (zonificacion.getNombre() == null) {
                    zonificacion = null;
                } else if (zonificacion.getNombre().equals("")) {
                    zonificacion = null;
                }
                if (zonificacion != null) {
                    FiltroZona locFiltro = new FiltroZona();
                    locFiltro.setZonificacion(zonificacion);
                    locFiltro = this.getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().findListaZonas(locFiltro);
                    zonas = (List) locFiltro.getListaResultados();
                    for (int i = 0; i < zonas.size(); i++) {
                        GrupoZona gz = new GrupoZona((Zona) zonas.get(i));
                        gruposZona.add(gz);
                    }
                }
            }
            this.getElementoPila().getObjetos().set(1, zonas);
            this.getElementoPila().getObjetos().set(2, gruposZona);
        } catch (Exception ex) {
            error("No se pudo recuperar la lista de Zonas.");
        }
        return;
    }

    /**
     * Inner Class para listar en la tabla.
     */
    public class GrupoZona {

        private Zona zona;
        private Double valor;

        /**
         * Constructor por defecto.
         */
        public GrupoZona() {
        }

        /**
         * Constructor con valor inicializado en 0.0F.
         */
        public GrupoZona(Zona zona) {
            this.zona = zona;
            this.valor = new Double(0.0F);
        }

        /**
         * Constructor con 2 parametros
         */
        public GrupoZona(Zona zona, Double valor) {
            this.zona = zona;
            this.valor = valor;
        }

        public Zona getZona() {
            return zona;
        }

        public void setZona(Zona zona) {
            this.zona = zona;
        }

        public Double getValor() {
            return valor;
        }

        public void setValor(Double valor) {
            this.valor = valor;
        }

        public String toString() {
            return zona.toString() + " : " + valor;
        }
    }

    public String btnSeleccionarZonificacion_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 3;

        if (ultimo) {

            // APLICAR LOGICA AQUI...

            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminZonificacion";
        } else {
            retorno = this.prepararCaducidad();
        }

        return retorno;
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		TipoParametroGrupoZona tipoParametroGrupoZona = null;
        List zonas = null;
        List gruposZona = new ArrayList();
        Map mapaZonas = null;
        Zonificacion zonificacion = null;
        
        if (pObject instanceof Zonificacion) {
            zonificacion = (Zonificacion) pObject;
            this.getRequestBean1().setObjetoSeleccion(null);

            if (zonificacion != null) {
                try {
                    if (zonificacion.getNombre() == null) {
                        zonificacion = null;
                    } else if (zonificacion.getNombre().equals("")) {
                        zonificacion = null;
                    }
                    if (zonificacion != null) {
                        this.getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().setLlave(this.getSessionBean1().getLlave());
                        FiltroZona locFiltro = new FiltroZona();
                        locFiltro.setZonificacion(zonificacion);
                        locFiltro = this.getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().findListaZonas(locFiltro);
                        zonas = (List) locFiltro.getListaResultados(); 
                        System.out.println("zonas cant : " + zonas.size());
                    }
                } catch (Exception ex) {
                    error("No se pudo recuperar la lista de Zonas.");
                }
            } else {
                zonas = null;
            }
            this.getElementoPila().getObjetos().set(3, zonificacion);

            if (zonas != null && zonas.size() != 0) {
                for (int i = 0; i < zonas.size(); i++) {
                    Zona z = (Zona) zonas.get(i);
                    boolean esta = false;
                    int j = 0;
                    while (j < gruposZona.size() && !esta) {
                        esta = (z.getIdZona() == ((GrupoZona) gruposZona.get(j)).getZona().getIdZona());
                        j++;
                    }
                    if (!esta) {
                        gruposZona.add(new GrupoZona(z));
                    }
                }
                this.getElementoPila().getObjetos().set(1, zonas);
                this.getElementoPila().getObjetos().set(2, gruposZona);
            } else {
                tipoParametroGrupoZona = (TipoParametroGrupoZona) this.obtenerObjetoDelElementoPila(0, TipoParametroGrupoZona.class);
                warn("Seleccione una zonificaci\363n que tenga zonas asociadas.");
                if (tipoParametroGrupoZona.getListaZonas() != null) {
                    tipoParametroGrupoZona.getListaZonas().clear();
                }
                this.getElementoPila().getObjetos().set(0, tipoParametroGrupoZona);
                this.getElementoPila().getObjetos().set(1, null);
                this.getElementoPila().getObjetos().set(2, null);
                this.getElementoPila().getObjetos().set(3, null);
                //limpio tabla
            }
        }
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		TipoParametroGrupoZona tipoParametroGrupoZona = (TipoParametroGrupoZona) pObject;

        long idTipoParametroGrupoZona = tipoParametroGrupoZona.getIdTipoParametro();
        try {
            this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());
            tipoParametroGrupoZona = this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().getTipoParametroGrupoZonaPorId(idTipoParametroGrupoZona);
        } catch (Exception ex) {
            log(CASO_NAVEGACION + "_getTipoParametroGrupoZonaPorId:", ex);
            error(NOMBRE_PAGINA + " - No se pudo recuperar el Par\341metro de Grupo de Zonas: " + ex.getMessage());
        }

        Zonificacion zonificacion = tipoParametroGrupoZona.getZonificacion();
        int ind = 0;
        this.getElementoPila().getObjetos().set(ind++, tipoParametroGrupoZona);
        this.getElementoPila().getObjetos().set(3, zonificacion);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMTipoParametroGrupoZona";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		TipoParametroGrupoZona locTipoParametroGrupoZona = this.obtenerObjetoDelElementoPila(0, TipoParametroGrupoZona.class);
		this.getTablaLogs().getLdpLogs().setList(locTipoParametroGrupoZona.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return TipoParametroGrupoZona.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona}";
	}
}
