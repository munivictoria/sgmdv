/*
 * AgregarDeposito.java
 *
 * Created on 12 de junio de 2008, 8:30
 * Copyright Trascender SRL
 */
package muni.inventario.ABMDeposito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.compras.recurso.persistent.inventario.Deposito;
import com.trascender.compras.recurso.persistent.inventario.Stock;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class ABMDeposito extends ABMPageBean {
	
    private final String NOMBRE_PAGINA = "Agregar Deposito";
    // nombre del caso de navegacion para llegar a esta pagina.
    private final String CASO_NAVEGACION = "AgregarDeposito";
    // nombre del caso de navegacion para llegar a la pagina de caducidad
    private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
    // nombre del caso de navegacion para llegar a la pagina que se debe
    // mostrar al salir de la pagina de caducidad
    private final String CASO_NAV_POST_CADUCIDAD = "Main";
    // es una pagina que no necesita idSubSesion
    // Inicia una sub sesion.
    private final boolean PUEDE_SER_PAGINA_INICIAL = false;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Creator-managed Component Definition">
    private int __placeholder;

    @Override
    protected void _init() throws Exception {
    	if (this.getListaDelCommunication() != null && !this.getListaDelCommunication().isEmpty()) {// aca cambiaste de array a Hashset
            List locList = new ArrayList();
            locList.addAll(this.getListaDelCommunication());
            this.getObjectListDataProvider().setList(locList);
        }
    	getTableStockEnDeposito().setClearSortButton(true);
    }
    
    private Label lblBien = new Label();
    private TextField tfBien = new TextField();
    private Button btnSeleccionarBien = new Button();
    private HtmlAjaxCommandButton btnMarcarFaltantes = new HtmlAjaxCommandButton();
    private Button btnLimpiarBien = new Button();
    private PanelGroup groupPanel1 = new PanelGroup();
    private HtmlAjaxCommandButton btnQuitarTodos = new HtmlAjaxCommandButton();
    private HtmlAjaxCommandButton btnQuitar = new HtmlAjaxCommandButton();
    private StaticText stSeparador3 = new StaticText();
    private TextField tfCantidad = new TextField();
    private TextField tfCantidadLimite = new TextField();
    private TextField tfCantidadAComprar = new TextField();
    private TextArea taDescripcion = new TextArea();
    private TableColumn tableColumn1 = new TableColumn();
    private TableColumn tableColumn2 = new TableColumn();
    private RadioButton radioButton1 = new RadioButton();
    private Object lastSelected = null;
    private Checkbox checkbox1 = new Checkbox();

	public HtmlAjaxCommandButton getBtnMarcarFaltantes() {
		return btnMarcarFaltantes;
	}

	public void setBtnMarcarFaltantes(HtmlAjaxCommandButton btnMarcarFaltantes) {
		this.btnMarcarFaltantes = btnMarcarFaltantes;
	}

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	public Checkbox getCheckbox1() {
		return checkbox1;
	}

	public void setCheckbox1(Checkbox checkbox1) {
		this.checkbox1 = checkbox1;
	}

	public TextField getTfCantidad() {
		return tfCantidad;
	}

	public void setTfCantidad(TextField tfCantidad) {
		this.tfCantidad = tfCantidad;
	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public String getCurrentRow() {
		return tableRGStockEnDeposito.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}
    
	public Object getRBSelected() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if (selected != null) {
			lastSelected = selected;
		}
	}
    
	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

    
    public TextField getTfCantidadLimite() {
		return tfCantidadLimite;
	}

	public void setTfCantidadLimite(TextField tfCantidadLimite) {
		this.tfCantidadLimite = tfCantidadLimite;
	}

	public TextField getTfCantidadAComprar() {
		return tfCantidadAComprar;
	}

	public void setTfCantidadAComprar(TextField tfCantidadAComprar) {
		this.tfCantidadAComprar = tfCantidadAComprar;
	}

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea taDescripcion) {
		this.taDescripcion = taDescripcion;
	}



	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodos() {
		return btnQuitarTodos;
	}

	public void setBtnQuitarTodos(HtmlAjaxCommandButton btnQuitarTodos) {
		this.btnQuitarTodos = btnQuitarTodos;
	}

	public HtmlAjaxCommandButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(HtmlAjaxCommandButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public StaticText getStSeparador3() {
		return stSeparador3;
	}

	public void setStSeparador3(StaticText stSeparador3) {
		this.stSeparador3 = stSeparador3;
	}

	public Label getLblBien() {
		return lblBien;
	}

	public void setLblBien(Label lblBien) {
		this.lblBien = lblBien;
	}

	public TextField getTfBien() {
		return tfBien;
	}

	public void setTfBien(TextField tfBien) {
		this.tfBien = tfBien;
	}

	public Button getBtnSeleccionarBien() {
		return btnSeleccionarBien;
	}

	public void setBtnSeleccionarBien(Button btnSeleccionarBien) {
		this.btnSeleccionarBien = btnSeleccionarBien;
	}

	public Button getBtnLimpiarBien() {
		return btnLimpiarBien;
	}

	public void setBtnLimpiarBien(Button btnLimpiarBien) {
		this.btnLimpiarBien = btnLimpiarBien;
	}

	//Componentes
    private StaticText stBien = new StaticText();

    public StaticText getStBien() {
        return stBien;
    }

    public void setStBien(StaticText pStBien) {
        this.stBien = pStBien;
    }
    
    private TextField tfNombre = new TextField();

    public TextField getTfNombre() {
        return tfNombre;
    }

    public void setTfNombre(TextField pTfNombre) {
        this.tfNombre = pTfNombre;
    }
    private TextField tfArea = new TextField();

    public TextField getTfArea() {
        return tfArea;
    }

    public void setTfArea(TextField pTfArea) {
        this.tfArea = pTfArea;
    }
    private StaticText stDomicilio = new StaticText();

    public StaticText getStDomicilio() {
        return stDomicilio;
    }

    public void setStDomicilio(StaticText pStDomicilio) {
        this.stDomicilio = pStDomicilio;
    }
    private TextField tfTelefono = new TextField();

    public TextField getTfTelefono() {
        return tfTelefono;
    }

    public void setTfTelefono(TextField pTfTelefono) {
        this.tfTelefono = pTfTelefono;
    }
    //Labels
    private Label lblNombre = new Label();

    public Label getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(Label pLblNombre) {
        this.lblNombre = pLblNombre;
    }
    private Label lblArea = new Label();

    public Label getLblArea() {
        return lblArea;
    }

    public void setLblArea(Label pLblArea) {
        this.lblArea = pLblArea;
    }
    private Label lblDomicilio = new Label();

    public Label getLblDomicilio() {
        return lblDomicilio;
    }

    public void setLblDomicilio(Label pLblDomicilio) {
        this.lblDomicilio = pLblDomicilio;
    }
    private Label lblTelefono = new Label();

    public Label getLblTelefono() {
        return lblTelefono;
    }

    public void setLblTelefono(Label pLblTelefono) {
        this.lblTelefono = pLblTelefono;
    }
    
    private Button btnSeleccionarArea = new Button();

    public Button getBtnSeleccionarArea() {
        return btnSeleccionarArea;
    }

    public void setBtnSeleccionarArea(Button pBtnSeleccionarArea) {
        this.btnSeleccionarArea = pBtnSeleccionarArea;
    }
    private Button btnLimpiarArea = new Button();

    public Button getBtnLimpiarArea() {
        return btnLimpiarArea;
    }

    public void setBtnLimpiarArea(Button pBtnLimpiarArea) {
        this.btnLimpiarArea = pBtnLimpiarArea;
    }
    private Button btnSeleccionarDomicilio = new Button();

    public Button getBtnSeleccionarDomicilio() {
        return btnSeleccionarDomicilio;
    }

    public void setBtnSeleccionarDomicilio(Button pBtnSeleccionarDomicilio) {
        this.btnSeleccionarDomicilio = pBtnSeleccionarDomicilio;
    }
    public Button btnLimpiarDomicilio = new Button();

    public Button getBtnLimpiarDomicilio() {
        return btnLimpiarDomicilio;
    }

    public void setBtnLimpiarDomicilio(Button pBtnLimpiarDomicilio) {
        this.btnLimpiarDomicilio = pBtnLimpiarDomicilio;
    }
    
    private ObjectListDataProvider ldpListaStockEnDeposito = new ObjectListDataProvider();

    public ObjectListDataProvider getLdpListaStockEnDeposito() {
        return ldpListaStockEnDeposito;
    }

    public void setLdpListaStockEnDeposito(ObjectListDataProvider pLdpListaStockEnDeposito) {
        this.ldpListaStockEnDeposito = pLdpListaStockEnDeposito;
    }
    
    private List getListaDelCommunication() {
        return this.getCommunicationComprasBean().getListaStockEnDeposito();
    }

    private void setListaDelCommunication(List pListaStockEnDeposito) {
    	this.getCommunicationComprasBean().setListaStockEnDeposito(pListaStockEnDeposito);
	}
    
    private ObjectListDataProvider getObjectListDataProvider() {
        return this.getLdpListaStockEnDeposito();
    }
    
    private Table tableStockEnDeposito = new Table();

    public Table getTableStockEnDeposito(){
        return tableStockEnDeposito;
    }

    public void setTableStockEnDeposito(Table pTableStockEnDeposito){
        this.tableStockEnDeposito = pTableStockEnDeposito;
    }

    private TableRowGroup tableRGStockEnDeposito = new TableRowGroup();

    public TableRowGroup getTableRGStockEnDeposito(){
        return this.tableRGStockEnDeposito;
    }

    public void setTableRGStockEnDeposito(TableRowGroup pTableRGStockEnDeposito){
        this.tableRGStockEnDeposito = pTableRGStockEnDeposito;
    }

    private TableColumn tableColBien = new TableColumn();

    public TableColumn getTableColBien(){
        return this.tableColBien;
    }

    public void setTableColBien(TableColumn pTableColBien){
        this.tableColBien = pTableColBien;
    }

    private TableColumn tableColCantidad = new TableColumn();
    private TableColumn tableColCantidadLimite = new TableColumn();
    private TableColumn tableColCantidadAComprar = new TableColumn();
    private TableColumn tableDescripcion = new TableColumn();

    
    public TableColumn getTableColCantidadLimite() {
		return tableColCantidadLimite;
	}

	public void setTableColCantidadLimite(TableColumn tableColCantidadLimite) {
		this.tableColCantidadLimite = tableColCantidadLimite;
	}

	public TableColumn getTableColCantidadAComprar() {
		return tableColCantidadAComprar;
	}

	public void setTableColCantidadAComprar(TableColumn tableColCantidadAComprar) {
		this.tableColCantidadAComprar = tableColCantidadAComprar;
	}

	public TableColumn getTableDescripcion() {
		return tableDescripcion;
	}

	public void setTableDescripcion(TableColumn tableDescripcion) {
		this.tableDescripcion = tableDescripcion;
	}

	public TableColumn getTableColCantidad(){
        return this.tableColCantidad;
    }

    public void setTableColCantidad(TableColumn pTableColCantidad){
        this.tableColCantidad = pTableColCantidad;
    }
    
    private Label lblStockEnDeposito = new Label();

    public Label getLblStockEnDeposito() {
        return lblStockEnDeposito;
    }

    public void setLblStockEnDeposito(Label pLblStockEnDeposito) {
        this.lblStockEnDeposito = pLblStockEnDeposito;
    }
    
    /** 
     * <p>Construir una instancia de bean de página.</p>
     */
    public ABMDeposito() {
    }
    
    @Override
    protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
        int ind = 0;

        //CAMBIAR: settear los objetos administrados por la pagina
        ep.getObjetos().add(ind++, new Deposito());
        ep.getObjetos().add(ind++, new Area());
        ep.getObjetos().add(ind++, new Domicilio());
        ep.getObjetos().add(ind++, new ArrayList());

//        // Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
        ep.getObjetos().add(ind++, new Integer(0));
        return ep;
    }
    
    @Override
    protected void guardarEstadoObjetosUsados() {
        // CAMBIAR: Verificar el metodo completo.
    	
        Deposito locDeposito = (Deposito) this.obtenerObjetoDelElementoPila(0, Deposito.class);
        Area locArea = (Area) this.obtenerObjetoDelElementoPila(1, Area.class);
        Domicilio locDomicilio = (Domicilio) this.obtenerObjetoDelElementoPila(2, Domicilio.class);
    
        List locListaStock = (List) this.obtenerObjetoDelElementoPila(3, ArrayList.class);
        Set<Stock> locListaStockEnDeposito = new  HashSet<Stock>();
        locListaStockEnDeposito = new HashSet<Stock> (locListaStock);
      
      	locDeposito.setListaStock(locListaStockEnDeposito);
     	this.getObjectListDataProvider().commitChanges();
    	this.setListaDelCommunication(locListaStock);

        Object locNombre = this.getTfNombre().getText();
        Object locTelefono = this.getTfTelefono().getText();

        if (locNombre != null && locNombre != "") {
            locDeposito.setNombre(locNombre.toString());
        } else {
            locDeposito.setNombre(null);
        }

        if (locTelefono != null && locTelefono != "") {
            locDeposito.setTelefono(locTelefono.toString());
        } else {
            locDeposito.setTelefono(null);
        }

        if (locArea != null && locArea.getIdArea() != -1) {
            locDeposito.setArea(locArea);
        }else{
             locDeposito.setArea(null);
        }

        //Me parece que el domicilio tiene id -1 a esta altura porque el deposito se encarga de persistirlo
        if (locDomicilio.getLocalidad() != null) {
            locDeposito.setDomicilio(locDomicilio);
        }

        this.getElementoPila().getObjetos().set(0, locDeposito);
        this.getElementoPila().getObjetos().set(1, locArea);
        this.getElementoPila().getObjetos().set(2, locDomicilio);
    }

    @Override
    protected void mostrarEstadoObjetosUsados() {
        // CAMBIAR: Verificar el metodo completo.
        Deposito locDeposito = null;
        Area locArea = null;
        Domicilio locDomicilio = null;
        List locListaStockEnDeposito = locListaStockEnDeposito = (List) this.obtenerObjetoDelElementoPila(3, ArrayList.class);

        if (this.getRequestBean1().getRespuestaABM() != null) {
            Object seleccionado = this.getRequestBean1().getRespuestaABM();
            if (seleccionado instanceof Domicilio) {
                if (seleccionado != null) {
                    locDomicilio = (Domicilio) seleccionado;
                    this.getElementoPila().getObjetos().set(2, locDomicilio);
                }
            }
        }
        
        if (this.getRequestBean1().getObjetosSeleccionMultiple() != null ){
    		List listaSeleccionados = this.getRequestBean1().getObjetosSeleccionMultiple();

				for (Object cadaObject : listaSeleccionados) {
					Bien cadaBien = (Bien) cadaObject;
					boolean esta = false;
					for (Object cadaObject2 : locListaStockEnDeposito){
						Stock cadaStock = (Stock) cadaObject2;
						if (cadaStock.getBien().equals(cadaBien)) {
							esta = true;
							break;
						}
					}if (!esta){
					Stock nuevoStock = new Stock();
					nuevoStock.setBien(cadaBien);
					locListaStockEnDeposito.add(nuevoStock);
					}
				}
				this.getElementoPila().getObjetos().set(3, locListaStockEnDeposito);
        	}
        
        
        locDeposito = (Deposito) this.obtenerObjetoDelElementoPila(0, Deposito.class);
        locArea = (Area) this.obtenerObjetoDelElementoPila(1, Area.class);
        locDomicilio = (Domicilio) this.obtenerObjetoDelElementoPila(2, Domicilio.class);
        List listaStock = this.obtenerObjetoDelElementoPila(3, ArrayList.class);

        if (locDeposito.getNombre() != null) {
            this.getTfNombre().setText(locDeposito.getNombre());
        }
        if (locArea != null && locArea.getIdArea() != -1) {
            this.getTfArea().setText(locArea.toString());
        }else{
            this.getTfArea().setText(null);
        }
        if (locDomicilio.getLocalidad() != null) {
            this.getStDomicilio().setText(locDomicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));
        }
        if (locDeposito.getTelefono() != null) {
            this.getTfTelefono().setText(locDeposito.getTelefono());
        }

        ArrayList locListaStock = new ArrayList();
        
        locListaStock = new ArrayList(locDeposito.getListaStock());
        this.setListaDelCommunication(locListaStock);
        this.getObjectListDataProvider().setList(locListaStock);
        
        this.setListaDelCommunication(listaStock);
        this.getObjectListDataProvider().setList(listaStock);
    }
    
    public String btnSeleccionarArea_action() {
    	return navegarParaSeleccionar("AdminArea");
    }

    public String btnSeleccionarDomicilio_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        // CAMBIAR: guardo la posicion donde debo guardar el objeto que seleccione
        int posicionObjetoSeleccionado = 2;

        if (ultimo) {

            this.guardarEstadoObjetosUsados();
            this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

            Domicilio domicilio = (Domicilio) this.obtenerObjetoDelElementoPila(2, Domicilio.class);
            Localidad localidad = domicilio.getLocalidad();

            if (localidad != null) {
                this.getRequestBean1().setObjetoABM(domicilio);

                retorno = "ModificarDomicilio";
            } else {
                retorno = "AgregarDomicilio";
            }
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }

    public String btnLimpiarDomicilio_action() {
    	String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.getStDomicilio().setText("");
			this.getElementoPila().getObjetos().set(2, null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
    }

    public String btnLimpiarArea_action() {
    	String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if (ultimo) {
			this.limpiarObjeto(this.getTfArea());
			this.getElementoPila().getObjetos().set(1, null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
    }
    
    @Override
	protected void procesarObjetoSeleccion(Object pObject) {
        if (pObject instanceof Area) {
            Area locArea = (Area) pObject;
            this.getElementoPila().getObjetos().set(1, locArea);
        }
		if (pObject instanceof Domicilio) {
			if (pObject != null) {
				Domicilio locDomicilio = (Domicilio) pObject;
				this.getElementoPila().getObjetos().set(2, locDomicilio);
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		Deposito locDeposito = (Deposito) this.getRequestBean1().getObjetoABM();
        Area locArea = locDeposito.getArea();
        Domicilio locDomicilio = locDeposito.getDomicilio();
        ArrayList locListaStockEnDeposito = new ArrayList();
        
      locListaStockEnDeposito = new ArrayList(locDeposito.getListaStock());
      this.setListaDelCommunication(locListaStockEnDeposito);
      this.getObjectListDataProvider().setList(locListaStockEnDeposito);

        this.getElementoPila().getObjetos().set(0, locDeposito);
        this.getElementoPila().getObjetos().set(1, locArea);
        this.getElementoPila().getObjetos().set(2, locDomicilio);
        this.getElementoPila().getObjetos().set(3, locListaStockEnDeposito);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMDeposito";
	}
	
	 public String btnMarcarFaltantes_action() {
  
        return null;
    }
	
    public String btnSeleccionarBien_action() {
        String retorno = null;
        boolean ultimo = this.ultimoElementoPilaDeSubSesion();

        if (ultimo) {
            // APLICAR LOGICA AQUI...
            this.guardarEstadoObjetosUsados();
            this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
            this.getRequestBean1().setTipoSeleccion("MULTIPLE");
            this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(1, Bien.class));
            // CAMBIAR: Caso de navegacion de la pagina de administracion correspondiente.
            retorno = "AdminBien";
        } else {
            retorno = this.prepararCaducidad();
        }
        return retorno;
    }
    
	public String btnQuitar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			RowKey rk = null;
			// APLICAR LOGICA AQUI...
			try {
				rk = this.getSeleccionado();
				if (rk != null) {
					this.guardarEstadoObjetosUsados();
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					this.getListaDelCommunication().remove(index);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	public String btnQuitarTodos_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if (ultimo) {
			// APLICAR LOGICA AQUI...
			try {
				this.getListaDelCommunication().clear();
				this.getObjectListDataProvider().clearObjectList();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getObjectListDataProvider().getRowKey(aRowId);
		} catch (Exception ex) {
		}
		return rk;
	}
	//----------------------------------------------------------------------------------------------
	
	private Set<Stock> listaSeleccionados = new HashSet();
	
	public Set<Stock> getListaSeleccionados() {
		return listaSeleccionados;
	}

	public void setListaSeleccionados(Set<Stock> listaSeleccionados) {
		this.listaSeleccionados = listaSeleccionados;
	}

	public void setSelected(Object object) {
        Object objectSeleccionado = null;

        if (object == null) {
            objectSeleccionado = this.getObjectListDataProvider().getObjects()[Integer.parseInt(this.getTableRGStockEnDeposito().getRowKey().getRowId())];
            if (objectSeleccionado != null && this.getListaSeleccionados().contains(objectSeleccionado)) {
                this.getListaSeleccionados().remove(objectSeleccionado);
            }
        } else if (this.getObjectListDataProvider() != null && this.getObjectListDataProvider().getObjects().length > 0) {
            objectSeleccionado = this.getObjectListDataProvider().getList().get(Integer.parseInt(object.toString()));
            if (objectSeleccionado != null && !this.getListaSeleccionados().contains(objectSeleccionado)) {
                this.getListaSeleccionados().add((Stock)objectSeleccionado);
            }
        }
    }
	
    public Object getSelected() {
        String sv = getCheckbox1().getSelectedValue().toString();
        Object objectSeleccionado = this.getObjectListDataProvider().getObjects()[Integer.parseInt(sv)];
        if(this.getListaSeleccionados().contains(objectSeleccionado)){
            return sv;
        }
        return null;
    }

    public String getSelectedValue() {
        RowKey rowKey = this.getTableRGStockEnDeposito().getRowKey();
        return (rowKey != null) ? rowKey.getRowId() : null;
    }
    
    @Override
	public long getSerialVersionUID() {
		return Deposito.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{inventario$ABMDeposito$ABMDeposito}";
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Deposito locDeposito = this.obtenerObjetoDelElementoPila(0, Deposito.class);
		this.getTablaLogs().getLdpLogs().setList(locDeposito.getListaLogsAuditoria());
	}
}