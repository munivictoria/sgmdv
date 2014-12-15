/*
 * AgregarPlantillaObligacion.java
 *
 * Created on 23 de noviembre de 2006, 10:40
 * Copyright Trascender SRL
 */
package muni.habilitaciones.ABMPlantillaObligacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import muni.ManejoDePila;
import muni.ManejoPrepararNavegacion;
import muni.MetodosEstaticos;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.Tree;
import com.sun.rave.web.ui.component.TreeNode;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.habilitaciones.recurso.persistent.PlantillaDocHabCompuesto;
import com.trascender.habilitaciones.recurso.persistent.PlantillaDocHabilitante;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaPermiso;
import com.trascender.habilitaciones.recurso.persistent.PlantillaSellado;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>Page bean that corresponds to a similarly named JSP page. This class
 * contains component definitions (and initialization code) for all components
 * that you have defined on this page, as well as lifecycle methods and event
 * handlers where you may add behavior to respond to incoming events.</p>
 */
public class ABMPlantillaObligacion extends ABMPageBean {

    // <editor-fold defaultstate="collapsed" desc="Atributos de la pagina">
    // Atributos propios de la pagina.
    // CAMBIAR: Ir al dise�o y vincular a los campos ocultos.
	
    public Long getIdPagina() {
        return idPagina;
    }

    public void setIdPagina(Long idPagina) {
        this.idPagina = idPagina;
    }

    public Long getIdSubSesion() {
        return idSubSesion;
    }

    public void setIdSubSesion(Long idSubSesion) {
        this.idSubSesion = idSubSesion;
    }

    public ElementoPila getElementoPila() {

        return this.getSessionBean1().getMgrPilas().getLastElementoPila(this.getIdSubSesion());
    }
    private Map mapObjetosPila = new HashMap();
    // CAMBIAR: Constantes que varian segun la pagina.
    // nombre a mostrar en la ruta de la operacion.
    private final String NOMBRE_PAGINA = "Modificar Plantilla de Obligaci\363n";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "ModificarPlantillaObligacion";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;
    private final String URL_DOC = "/resources/imagenes/arboles/documento.png";
    private final String URL_PER = "/resources/imagenes/arboles/permiso.png";
    private final String URL_SEL = "/resources/imagenes/arboles/sellado.png";
    private Script scriptFinalM1 = new Script();

    public Script getScriptFinalM1() {
        return scriptFinalM1;
    }

    public void setScriptFinalM1(Script s) {
        this.scriptFinalM1 = s;
    }
    private Script scriptValidador = new Script();

    public Script getScriptValidador() {
        return scriptValidador;
    }

    public void setScriptValidador(Script scriptValidador) {
        this.scriptValidador = scriptValidador;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;

    /**
     * <p>Automatically managed component initialization.
     * <strong>WARNING:</strong> This method is automatically generated, so any
     * user-specified code inserted here is subject to being replaced.</p>
     */
    @Override
    protected void _init() throws Exception {

        Option[] op = null;
        // Tipo de Obligacion
        op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().keySet().toArray(), "cap");
        ddTipoObligacionDefaultOptions.setOptions(op);
        
        for(int i = 0; i < op.length; i++) {
        	System.out.println(op[i].getValue());
        }
    }
    
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
    private TextArea taDescripcion = new TextArea();

    public TextArea getTaDescripcion() {
        return taDescripcion;
    }

    public void setTaDescripcion(TextArea ta) {
        this.taDescripcion = ta;
    }
    private DropDown ddTipoObligacion = new DropDown();

    public DropDown getDdTipoObligacion() {
        return ddTipoObligacion;
    }

    public void setDdTipoObligacion(DropDown dd) {
        this.ddTipoObligacion = dd;
    }
    private SingleSelectOptionsList ddTipoObligacionDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdTipoObligacionDefaultOptions() {
        return ddTipoObligacionDefaultOptions;
    }

    public void setDdTipoObligacionDefaultOptions(SingleSelectOptionsList ssol) {
        this.ddTipoObligacionDefaultOptions = ssol;
    }
    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }
    private Tree trAgregarPlantillaObligacion = new Tree();

    public Tree getTrAgregarPlantillaObligacion() {
        return trAgregarPlantillaObligacion;
    }

    public void setTrAgregarPlantillaObligacion(Tree t) {
        this.trAgregarPlantillaObligacion = t;
    }
    private TreeNode tnRaiz = new TreeNode();

    public TreeNode getTnRaiz() {
        return tnRaiz;
    }

    public void setTnRaiz(TreeNode tn) {
        this.tnRaiz = tn;
    }
    private PanelGroup gpBotones = new PanelGroup();

    public PanelGroup getGpBotones() {
        return gpBotones;
    }

    public void setGpBotones(PanelGroup pg) {
        this.gpBotones = pg;
    }
    private Button btnAgregarDocumento = new Button();

    public Button getBtnAgregarDocumento() {
        return btnAgregarDocumento;
    }

    public void setBtnAgregarDocumento(Button b) {
        this.btnAgregarDocumento = b;
    }
    private Button btnAgregarPermiso = new Button();

    public Button getBtnAgregarPermiso() {
        return btnAgregarPermiso;
    }

    public void setBtnAgregarPermiso(Button b) {
        this.btnAgregarPermiso = b;
    }
    private Button btnAgregarSellado = new Button();

    public Button getBtnAgregarSellado() {
        return btnAgregarSellado;
    }

    public void setBtnAgregarSellado(Button b) {
        this.btnAgregarSellado = b;
    }
    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    private Button btnQuitarElemento = new Button();

    public Button getBtnQuitarElemento() {
        return btnQuitarElemento;
    }

    public void setBtnQuitarElemento(Button b) {
        this.btnQuitarElemento = b;
    }
    // </editor-fold>

    /**
     * <p>Construir una instancia de bean de p�gina.</p>
     */
    public ABMPlantillaObligacion() {
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;

        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new PlantillaObligacion());
        ep.getObjetos().add(ind++, new HashMap());  // documentos hab de la plantilla
        ep.getObjetos().add(ind++, new Tree());     // arbol
        ep.getObjetos().add(ind++, new TreeNode()); // 3 nodo seleccionado
        ep.getObjetos().add(ind++, new Integer(1)); // 4 ultimo id insertado

        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));

        return ep;
    }

    @Override
    protected void guardarEstadoObjetosUsados() {
        // CAMBIAR: Verificar el metodo completo.
        int ind = 0;
        PlantillaObligacion plantillaObligacion = (PlantillaObligacion) ManejoDePila.obtenerObjetoDelElementoPila(ind++, PlantillaObligacion.class, getElementoPila());
        HashMap documentos = (HashMap) ManejoDePila.obtenerObjetoDelElementoPila(ind++, HashMap.class, getElementoPila());
        Tree arbol = (Tree) ManejoDePila.obtenerObjetoDelElementoPila(ind++, Tree.class, this.getElementoPila());
        TreeNode nodo = (TreeNode) ManejoDePila.obtenerObjetoDelElementoPila(ind++, TreeNode.class, this.getElementoPila());
        Integer ultimoId = (Integer) ManejoDePila.obtenerObjetoDelElementoPila(ind++, Integer.class, this.getElementoPila());

        //arbol = this.armarArbol(plantillaObligacion.getListaDocumentosHabilitantes(), plantillaObligacion);
        // this.setTrAgregarPlantillaObligacion(arbol);
        TreeNode nodoSeleccionado = nodo;
        if (nodoSeleccionado != null) {
            //selecciona al padre
            this.getRequestBean1().setObjetoSeleccion(nodoSeleccionado.getText());
        }
        
        plantillaObligacion.setNombre(getTextFieldValue(this.getTfNombre()));
        plantillaObligacion.setDescripcion(getTextAreaValue(this.getTaDescripcion()));
        plantillaObligacion.setTipoObligacion(getDDObjectValue(this.getDdTipoObligacion(), this.getCommunicationHabilitacionesBean().getMapaTipoObligacion()));

        ind = 0;

        this.getElementoPila().getObjetos().set(ind++, plantillaObligacion);
        this.getElementoPila().getObjetos().set(ind++, documentos);
        this.getElementoPila().getObjetos().set(ind++, this.getTrAgregarPlantillaObligacion());
        this.getElementoPila().getObjetos().set(ind++, nodoSeleccionado);
        this.getElementoPila().getObjetos().set(ind++, ultimoId);
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Verificar el metodo completo.
        int ind = 0;
        PlantillaObligacion plantillaObligacion = null;
        HashMap documentos = null;
        Tree arbol = null;
        TreeNode nodo = null;
        Integer ultimoId = null;
        ManejoDePila.acomodarSeleccionado(this.getRequestBean1(), this.getElementoPila());

        if (this.getRequestBean1().getRespuestaABM() != null) {

            PlantillaDocHabilitante respuesta = (PlantillaDocHabilitante) this.getRequestBean1().getRespuestaABM();

            plantillaObligacion = (PlantillaObligacion) ManejoDePila.obtenerObjetoDelElementoPila(0, PlantillaObligacion.class, this.getElementoPila());
            arbol = (Tree) ManejoDePila.obtenerObjetoDelElementoPila(2, Tree.class, this.getElementoPila());
            nodo = (TreeNode) ManejoDePila.obtenerObjetoDelElementoPila(3, TreeNode.class, this.getElementoPila());
            ultimoId = (Integer) ManejoDePila.obtenerObjetoDelElementoPila(4, Integer.class, this.getElementoPila());

            TreeNode treeNodeHijo = new TreeNode();

            treeNodeHijo.setUrl("#");
            treeNodeHijo.setExpanded(true);

            if (respuesta instanceof PlantillaDocHabCompuesto) {

                PlantillaDocHabCompuesto nuevoDocHabComp = (PlantillaDocHabCompuesto) respuesta;

                treeNodeHijo.setId("dNew" + ultimoId);
                treeNodeHijo.setText(nuevoDocHabComp.getNombre());
                treeNodeHijo.setToolTip(nuevoDocHabComp.getDescripcion());
                treeNodeHijo.setImageURL(URL_DOC);
            }

            if (respuesta instanceof PlantillaPermiso) {

                PlantillaPermiso nuevoPermiso = (PlantillaPermiso) respuesta;

                String texto = nuevoPermiso.getNombre();
                texto += ((nuevoPermiso.getPosibleAutorizador() != null) ? (" (" + nuevoPermiso.getPosibleAutorizador() + ")") : "");

                treeNodeHijo.setId("pNew" + ultimoId);
                treeNodeHijo.setText(texto);
                treeNodeHijo.setImageURL(URL_PER);
            }

            if (respuesta instanceof PlantillaSellado) {

                PlantillaSellado nuevoSellado = (PlantillaSellado) respuesta;

                String texto = nuevoSellado.getNombre();
                texto += (nuevoSellado.getValor() != null) ? (" ($ " + nuevoSellado.getValor() + ")") : "";

                treeNodeHijo.setId("sNew" + ultimoId);
                treeNodeHijo.setText(texto);
                treeNodeHijo.setToolTip(nuevoSellado.getDescripcion());
                treeNodeHijo.setImageURL(URL_SEL);
            }

            if (nodo != null) {

                nodo.getChildren().add(treeNodeHijo);
                documentos = (HashMap) ManejoDePila.obtenerObjetoDelElementoPila(1, HashMap.class, this.getElementoPila());
                documentos.put(treeNodeHijo.getId(), respuesta);
                this.getElementoPila().getObjetos().set(1, documentos);

                int ord = ultimoId.intValue() + 1;
                ultimoId = Integer.valueOf(ord);
                this.getElementoPila().getObjetos().set(4, ultimoId);
            }

        }

        ind = 0;
        plantillaObligacion = (PlantillaObligacion) ManejoDePila.obtenerObjetoDelElementoPila(ind++, PlantillaObligacion.class, this.getElementoPila());
        documentos = (HashMap) ManejoDePila.obtenerObjetoDelElementoPila(ind++, HashMap.class, this.getElementoPila());
        arbol = (Tree) ManejoDePila.obtenerObjetoDelElementoPila(ind++, Tree.class, this.getElementoPila());
        nodo = (TreeNode) ManejoDePila.obtenerObjetoDelElementoPila(ind++, TreeNode.class, this.getElementoPila());
        ultimoId = (Integer) ManejoDePila.obtenerObjetoDelElementoPila(ind++, Integer.class, this.getElementoPila());

        this.setTrAgregarPlantillaObligacion(arbol);
        this.getTfNombre().setText(plantillaObligacion.getNombre());
        if(plantillaObligacion.getTipoObligacion() != null){
        this.getDdTipoObligacion().setSelected(plantillaObligacion.getTipoObligacion().getNombre());
        this.getDdTipoObligacionDefaultOptions().setSelectedValue(plantillaObligacion.getTipoObligacion().getNombre());
        }
        this.getTaDescripcion().setText(plantillaObligacion.getDescripcion());
    }

    private String prepararParaAgregar(String casoNavegacion) {
        // CAMBIAR: arbol usado y prefijos aceptados para agregar.
        String ret = null;
        TreeNode nodo = this.getNodo(this.getTrAgregarPlantillaObligacion(), MetodosEstaticos.obtenerCookie("selCookie"));
        System.out.println("obtener cookie---" + MetodosEstaticos.obtenerCookie("selCookie"));
        if (nodo != null) {
            String prefijo = String.valueOf(nodo.getId().charAt(0));
            if (prefijo.equalsIgnoreCase("d") || prefijo.equalsIgnoreCase("t")) {
                this.getElementoPila().getObjetos().set(3, nodo);
                this.guardarEstadoObjetosUsados();
                this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
                ret = casoNavegacion;
            }
        }
        return ret;

    }
    public TreeNode getNodo(Tree tr, String idCompleto) {

        TreeNode selectedNode = null;
        System.out.println(" getNodo, idcompelto----" + idCompleto);
        System.out.println("tr.getid--------" + tr.getId());
        if (idCompleto != null) {
            try {
                String idNodo = this.getIdSinPrefijo(idCompleto, tr.getId());
                System.out.println("idNodo------" + idNodo);

                selectedNode = (TreeNode) tr.findComponent(idNodo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return selectedNode;
    }

    private String getIdSinPrefijo(String idCompleto, String idComponente) {
        String retorno = null;
        System.out.println("idcompleto----------" + idCompleto);
        System.out.println("idComponente------------" + idComponente);
        System.out.println("idCOmpletoIndexOF--" + idCompleto.indexOf(idComponente));
        System.out.println("idCOmponente.lenght+1----" + idComponente.length());
        if (idCompleto != null && idCompleto.length() > 0) {
            try {
                retorno = idCompleto.substring(idCompleto.indexOf(idComponente) + idComponente.length() + 1);
            } catch (RuntimeException ex) {
                ex.printStackTrace();
            }
        }
        return retorno;
    }
    // </editor-fold>

    public String btnAgregarDocumento_action() {
        String retorno = null;
        boolean ultimo = ManejoDePila.ultimoElementoPilaDeSubSesion(this.getSessionBean1(), idSubSesion, idPagina);

        if (ultimo) {

            retorno = this.prepararParaAgregar("AgregarPlantillaDocHabCompuesto");
            if (retorno == null) {
                warn("Por favor, seleccione un Documento Compuesto o la Ra\355z de la Plantilla.");
            }

        } else {
            retorno = ManejoPrepararNavegacion.prepararCaducidad(this.getRequestBean1(), "CASO_NAV_POST_CADUCIDAD", "CASO_NAV_CADUCIDAD");
        }
        return retorno;
    }

    public String btnAgregarPermiso_action() {
        String retorno = null;
        boolean ultimo = ManejoDePila.ultimoElementoPilaDeSubSesion(this.getSessionBean1(), idSubSesion, idPagina);

        if (ultimo) {

            retorno = this.prepararParaAgregar("AgregarPlantillaPermiso");
            if (retorno == null) {
                warn("Por favor, seleccione un Documento Compuesto o la Ra\355z de la Plantilla.");
            }
        } else {
            retorno = ManejoPrepararNavegacion.prepararCaducidad(this.getRequestBean1(), "CASO_NAV_POST_CADUCIDAD", "CASO_NAV_CADUCIDAD");
        }
        return retorno;

    }

    public String btnAgregarSellado_action() {
        String retorno = null;
        boolean ultimo = ManejoDePila.ultimoElementoPilaDeSubSesion(this.getSessionBean1(), idSubSesion, idPagina);

        if (ultimo) {

            retorno = this.prepararParaAgregar("AgregarPlantillaSellado");
            if (retorno == null) {
                warn("Por favor, seleccione un Documento Compuesto o la Ra\355z de la Plantilla.");
            }

        } else {
            retorno = ManejoPrepararNavegacion.prepararCaducidad(this.getRequestBean1(), "CASO_NAV_POST_CADUCIDAD", "CASO_NAV_CADUCIDAD");
        }
        return retorno;
    }

    public String btnQuitarElemento_action() {
        String retorno = "";
        boolean ultimo = ManejoDePila.ultimoElementoPilaDeSubSesion(this.getSessionBean1(), idSubSesion, idPagina);
        if (ultimo) {

            Tree arbol = trAgregarPlantillaObligacion;
            TreeNode nodo = getNodo(arbol, MetodosEstaticos.obtenerCookie("selCookie"));

            if (nodo != null) {
//                TreeNode nodoPadre = nodo.getParent();
//                nodoPadre.getChildren().remove(nodo);
                getElementoPila().getObjetos().set(2, arbol);
                MetodosEstaticos.eliminarCookie("selCookie");
                this.setTrAgregarPlantillaObligacion(arbol);
            }
        } else {
            retorno = ManejoPrepararNavegacion.prepararCaducidad(this.getRequestBean1(), "CASO_NAV_POST_CADUCIDAD", "CASO_NAV_CADUCIDAD");
        }
        return retorno;
    }

    // Armado del Arbol
    private Tree armarArbol(Set documentos, PlantillaObligacion pNodoPrincipal) {


        Tree arbol = new Tree();

        TreeNode raiz = new TreeNode();
        raiz.setId("tnRaiz");
        raiz.setText(pNodoPrincipal.toString());
        raiz.setUrl("#");
        raiz.setExpanded(true);
        arbol.getChildren().clear();
        arbol.getChildren().add(raiz);

        for (int i = 0; i < documentos.size(); i++) {
            PlantillaDocHabilitante plantillaDocHab = (PlantillaDocHabilitante) documentos.toArray()[i];

            TreeNode nuevoNodo = new TreeNode();
            nuevoNodo.setUrl("#");
            nuevoNodo.setExpanded(true);

            String prefijo = "";

            if (plantillaDocHab instanceof PlantillaDocHabCompuesto) {
                PlantillaDocHabCompuesto nuevoDocHabComp = (PlantillaDocHabCompuesto) plantillaDocHab;

                prefijo = "d";
                nuevoNodo.setText(nuevoDocHabComp.getNombre());
                nuevoNodo.setToolTip(nuevoDocHabComp.getDescripcion());
                nuevoNodo.setImageURL(URL_DOC);

                nuevoNodo.getChildren().addAll(this.armarArbolNodos(nuevoDocHabComp.getHijos()));
                //nuevoNodo.getChildren().add( this.armarArbolNodos( nuevoDocHabComp.getHijos() ));
            }

            if (plantillaDocHab instanceof PlantillaPermiso) {

                PlantillaPermiso nuevoPermiso = (PlantillaPermiso) plantillaDocHab;

                String texto = nuevoPermiso.getNombre();
                texto += ((nuevoPermiso.getPosibleAutorizador() != null) ? (" (" + nuevoPermiso.getPosibleAutorizador() + ")") : "");

                prefijo = "p";
                nuevoNodo.setText(texto);
                nuevoNodo.setImageURL(URL_PER);
            }

            if (plantillaDocHab instanceof PlantillaSellado) {

                PlantillaSellado nuevoSellado = (PlantillaSellado) plantillaDocHab;

                String texto = nuevoSellado.getNombre();
                texto += (nuevoSellado.getValor() != null) ? (" ($ " + nuevoSellado.getValor() + ")") : "";

                prefijo = "s";
                nuevoNodo.setText(texto);
                nuevoNodo.setToolTip(nuevoSellado.getDescripcion());
                nuevoNodo.setImageURL(URL_SEL);
            }

            HashMap documentosEP = (HashMap) ManejoDePila.obtenerObjetoDelElementoPila(1, HashMap.class, this.getElementoPila());
            Integer ultimoId = (Integer) ManejoDePila.obtenerObjetoDelElementoPila(4, Integer.class, this.getElementoPila());

            nuevoNodo.setId(prefijo + ultimoId);
            raiz.getChildren().add(nuevoNodo);
            documentosEP.put(nuevoNodo.getId(), plantillaDocHab);

            int ord = ultimoId.intValue() + 1;
            ultimoId = Integer.valueOf(ord);

            this.getElementoPila().getObjetos().set(1, documentosEP);
            this.getElementoPila().getObjetos().set(4, ultimoId);
        }

        return arbol;
    }

    private ArrayList armarArbolNodos(Set hijos) {
        //TreeNode arbol = new TreeNode();
        ArrayList subNodos = new ArrayList();

        for (int i = 0; i < hijos.size(); i++) {

            PlantillaDocHabilitante plantillaDocHab = (PlantillaDocHabilitante) hijos.toArray()[i];

            TreeNode nuevoNodo = new TreeNode();
            nuevoNodo.setUrl("#");
            nuevoNodo.setExpanded(true);


            String prefijo = "";

            if (plantillaDocHab instanceof PlantillaDocHabCompuesto) {
                PlantillaDocHabCompuesto nuevoDocHabComp = (PlantillaDocHabCompuesto) plantillaDocHab;

                prefijo = "d";
                nuevoNodo.setText(nuevoDocHabComp.getNombre());
                nuevoNodo.setToolTip(nuevoDocHabComp.getDescripcion());
                nuevoNodo.setImageURL(URL_DOC);

                nuevoNodo.getChildren().addAll(this.armarArbolNodos(nuevoDocHabComp.getHijos()));
                //nuevoNodo.getChildren().add( this.armarArbolNodos( nuevoDocHabComp.getHijos() ));
            }

            if (plantillaDocHab instanceof PlantillaPermiso) {

                PlantillaPermiso nuevoPermiso = (PlantillaPermiso) plantillaDocHab;

                String texto = nuevoPermiso.getNombre();
                texto += ((nuevoPermiso.getPosibleAutorizador() != null) ? (" (" + nuevoPermiso.getPosibleAutorizador() + ")") : "");

                prefijo = "p";
                nuevoNodo.setText(texto);
                nuevoNodo.setImageURL(URL_PER);
            }

            if (plantillaDocHab instanceof PlantillaSellado) {

                PlantillaSellado nuevoSellado = (PlantillaSellado) plantillaDocHab;

                String texto = nuevoSellado.getNombre();
                texto += (nuevoSellado.getValor() != null) ? (" ($ " + nuevoSellado.getValor() + ")") : "";

                prefijo = "s";
                nuevoNodo.setText(texto);
                nuevoNodo.setToolTip(nuevoSellado.getDescripcion());
                nuevoNodo.setImageURL(URL_SEL);
            }

            HashMap documentosEP = (HashMap) ManejoDePila.obtenerObjetoDelElementoPila(1, HashMap.class, this.getElementoPila());
            Integer ultimoId = (Integer) ManejoDePila.obtenerObjetoDelElementoPila(4, Integer.class, this.getElementoPila());

            nuevoNodo.setId(prefijo + ultimoId);
            //arbol.getChildren().add(nuevoNodo);
            subNodos.add(nuevoNodo);
            documentosEP.put(nuevoNodo.getId(), plantillaDocHab);

            int ord = ultimoId.intValue() + 1;
            ultimoId = Integer.valueOf(ord);

            this.getElementoPila().getObjetos().set(1, documentosEP);
            this.getElementoPila().getObjetos().set(4, ultimoId);
        }

        return subNodos;
    }

    // Armado del HashSet de documentos
    private HashSet armarHashSetDocumentos(Tree arbol, HashMap auxiliarDocumentos) {
        HashSet documentos = new HashSet();
        TreeNode raiz = (TreeNode) arbol.getChildren().get(0);

        for (int i = 0; i < raiz.getChildCount(); i++) {
            documentos.addAll(this.armarHashSetDocumentosNodos((TreeNode) raiz.getChildren().get(i), auxiliarDocumentos));
        }

        return documentos;
    }

    private HashSet armarHashSetDocumentosNodos(TreeNode nodo, HashMap auxiliarDocumentos) {
        HashSet documentos = new HashSet();
        HashSet documentosAuxiliar = new HashSet();

        String prefijo = String.valueOf(nodo.getId().charAt(0));
        documentos.add(auxiliarDocumentos.get(nodo.getId()));//agregue esta linea
        if (prefijo.equalsIgnoreCase("d")) {
            for (int i = 0; i < nodo.getChildCount(); i++) {
                documentosAuxiliar.addAll(this.armarHashSetDocumentosNodos((TreeNode) nodo.getChildren().get(i), auxiliarDocumentos));
            }
            if (!documentosAuxiliar.isEmpty()) {
                PlantillaDocHabCompuesto nuevaPlantillaDocHabCompuesto = (PlantillaDocHabCompuesto) auxiliarDocumentos.get(nodo.getId());
                nuevaPlantillaDocHabCompuesto.getHijos().clear();
                nuevaPlantillaDocHabCompuesto.getHijos().addAll(documentosAuxiliar);
                documentos.add(nuevaPlantillaDocHabCompuesto);
            } else {
                PlantillaDocHabCompuesto nuevaPlantillaDocHabCompuesto = (PlantillaDocHabCompuesto) auxiliarDocumentos.get(nodo.getId());
                documentos.remove(nuevaPlantillaDocHabCompuesto);
            }
        } else {
            // if (prefijo.equalsIgnoreCase("s") || prefijo.equalsIgnoreCase("p")) {
//                documentos.add(auxiliarDocumentos.get(nodo.getId()));
            //}
        }
        return documentos;
    }
    
    @Override
    protected String getCasoNavegacion() {
        return "ABMPlantillaObligacion";
    }

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		PlantillaObligacion plantillaObligacion = null;
		Tree arbol = null;
		
		System.out.println("  objetoSeleccion  ");
        plantillaObligacion = (PlantillaObligacion) ManejoDePila.obtenerObjetoDelElementoPila(0, PlantillaObligacion.class, this.getElementoPila());
        arbol = this.armarArbol(plantillaObligacion.getListaDocumentosHabilitantes(), plantillaObligacion);
        this.getElementoPila().getObjetos().set(0, plantillaObligacion);
        this.getElementoPila().getObjetos().set(2, arbol);
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		PlantillaObligacion plantillaObligacion = null;
		Tree arbol = null;
		
		plantillaObligacion = (PlantillaObligacion) pObject;
        arbol = this.armarArbol(plantillaObligacion.getListaDocumentosHabilitantes(), plantillaObligacion);

        this.getElementoPila().getObjetos().set(0, plantillaObligacion);
        this.getElementoPila().getObjetos().set(2, arbol);
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		PlantillaObligacion locPlantilla = this.obtenerObjetoDelElementoPila(0, PlantillaObligacion.class);
		this.getTablaLogs().getLdpLogs().setList(locPlantilla.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return PlantillaObligacion.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$ABMPlantillaObligacion$ABMPlantillaObligacion}";
	}
}
