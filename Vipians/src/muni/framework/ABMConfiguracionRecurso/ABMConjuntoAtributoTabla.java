
package muni.framework.ABMConfiguracionRecurso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import muni.framework.ABMUsuario.AdminUsuario;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.filtros.FiltroPlantillaAtributosDinamicos;
import com.trascender.framework.recurso.persistent.ConfiguracionAtributoTabla;
import com.trascender.framework.recurso.persistent.ConfiguracionRecurso;
import com.trascender.framework.recurso.persistent.ConjuntoAtributoTabla;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.recurso.transients.AtributoConsultable;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMConjuntoAtributoTabla extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunication2() != null) {
			this.getObjectListDataProvider2().setList(this.getListaDelCommunication2());
		}
		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		Option[] opTipoDato = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjectsList(AtributoConsultable.Tipo.values(), "may");
		ddTipoDatoOptions.setOptions(opTipoDato);

		armarDDOpciones();
	}

	private void armarDDOpciones() {
		List<String> locListaPropiedades = this.getComunicationBean().getListaPropiedadesConfiguracionRecurso();
		Option[] opOpciones;
		if(locListaPropiedades != null) {
			int i = 0;
			opOpciones = new Option[locListaPropiedades.size() + 1];
			opOpciones[i++] = new Option("", "");
			for(String cadaOpcion : locListaPropiedades) {
				opOpciones[i++] = new Option(cadaOpcion, cadaOpcion);
			}
		} else {
			opOpciones = new Option[] {new Option("", "")};
		}
		this.ddOpcionesOptions.setOptions(opOpciones);
	}

	private DropDown ddOpciones = new DropDown();
	private SingleSelectOptionsList ddOpcionesOptions = new SingleSelectOptionsList();
	private DropDown ddTipoDato = new DropDown();
	private SingleSelectOptionsList ddTipoDatoOptions = new SingleSelectOptionsList();

	public DropDown getDdTipoDato() {
		return ddTipoDato;
	}

	public void setDdTipoDato(DropDown ddTipoDato) {
		this.ddTipoDato = ddTipoDato;
	}

	public SingleSelectOptionsList getDdTipoDatoOptions() {
		return ddTipoDatoOptions;
	}

	public void setDdTipoDatoOptions(SingleSelectOptionsList ddTipoDatoOptions) {
		this.ddTipoDatoOptions = ddTipoDatoOptions;
	}

	public DropDown getDdOpciones() {
		return ddOpciones;
	}

	public void setDdOpciones(DropDown ddOpciones) {
		this.ddOpciones = ddOpciones;
	}

	public SingleSelectOptionsList getDdOpcionesOptions() {
		return ddOpcionesOptions;
	}

	public void setDdOpcionesOptions(SingleSelectOptionsList ddOpcionesOptions) {
		this.ddOpcionesOptions = ddOpcionesOptions;
	}

	private TableRowGroup tableRowGroup2 = new TableRowGroup();
	private ObjectListDataProvider ldpUsuarios = new ObjectListDataProvider();
	private RadioButton radioButton2 = new RadioButton();
	private TableColumn tableColumn6 = new TableColumn();
	private TableColumn tableColumn7 = new TableColumn();
	private StaticText staticText1 = new StaticText();
	private PanelGroup groupPanel2 = new PanelGroup();
	private Button btnAgregarUsuario = new Button();
	private HtmlAjaxCommandButton btnQuitarUsuario = new HtmlAjaxCommandButton();

	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tableColumn2 = new TableColumn();
	private TableColumn tableColumn3 = new TableColumn();
	private TableColumn tableColumn4 = new TableColumn();
	private TableColumn tableColumn5 = new TableColumn();
	private TableColumn tableColumn8 = new TableColumn();

	private TextField textField2 = new TextField();
	private TextField textField3 = new TextField();
	private TextField textField4 = new TextField();

	private Label label1 = new Label();
	private Label label2 = new Label();
	
	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label label1) {
		this.label1 = label1;
	}

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label label2) {
		this.label2 = label2;
	}

	public TextField getTextField2() {
		return textField2;
	}

	public void setTextField2(TextField textField2) {
		this.textField2 = textField2;
	}

	public TextField getTextField3() {
		return textField3;
	}

	public void setTextField3(TextField textField3) {
		this.textField3 = textField3;
	}

	public TextField getTextField4() {
		return textField4;
	}

	public void setTextField4(TextField textField4) {
		this.textField4 = textField4;
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public Button getBtnAgregarUsuario() {
		return btnAgregarUsuario;
	}

	public void setBtnAgregarUsuario(Button btnAgregarUsuario) {
		this.btnAgregarUsuario = btnAgregarUsuario;
	}

	public HtmlAjaxCommandButton getBtnQuitarUsuario() {
		return btnQuitarUsuario;
	}

	public void setBtnQuitarUsuario(HtmlAjaxCommandButton btnQuitarUsuario) {
		this.btnQuitarUsuario = btnQuitarUsuario;
	}

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	public PanelGroup getGroupPanel2() {
		return groupPanel2;
	}

	public void setGroupPanel2(PanelGroup groupPanel2) {
		this.groupPanel2 = groupPanel2;
	}

	private Object lastSelected2 = null;

	public Object getRBSelected2() {
		String sv = (String) radioButton2.getSelectedValue();
		return sv.equals(lastSelected2) ? sv : null;
	}

	public void setRBSelected2(Object selected) {
		if(selected != null) {
			lastSelected2 = selected;
		}
	}

	public String getCurrentRow2() {
		return tableRowGroup2.getRowKey().getRowId();
	}

	public void setCurrentRow2(int row) {
	}

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup tableRowGroup2) {
		this.tableRowGroup2 = tableRowGroup2;
	}

	public ObjectListDataProvider getLdpUsuarios() {
		return ldpUsuarios;
	}

	public void setLdpUsuarios(ObjectListDataProvider ldpUsuarios) {
		this.ldpUsuarios = ldpUsuarios;
	}

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton radioButton2) {
		this.radioButton2 = radioButton2;
	}

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tableColumn6) {
		this.tableColumn6 = tableColumn6;
	}

	public TableColumn getTableColumn7() {
		return tableColumn7;
	}

	public void setTableColumn7(TableColumn tableColumn7) {
		this.tableColumn7 = tableColumn7;
	}

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tableColumn1) {
		this.tableColumn1 = tableColumn1;
	}

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tableColumn3) {
		this.tableColumn3 = tableColumn3;
	}

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tableColumn4) {
		this.tableColumn4 = tableColumn4;
	}

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tableColumn5) {
		this.tableColumn5 = tableColumn5;
	}

	public TableColumn getTableColumn8() {
		return tableColumn8;
	}

	public void setTableColumn8(TableColumn tableColumn8) {
		this.tableColumn8 = tableColumn8;
	}

	private RadioButton radioButton1 = new RadioButton();

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	private ObjectListDataProvider ldpOpciones = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpOpciones() {
		return ldpOpciones;
	}

	public void setLdpOpciones(ObjectListDataProvider ldpOpciones) {
		this.ldpOpciones = ldpOpciones;
	}

	private Object lastSelected1 = null;

	public Object getRBSelected1() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected1) ? sv : null;
	}

	public void setRBSelected1(Object selected) {
		if(selected != null) {
			lastSelected1 = selected;
		}
	}

	public String getCurrentRow1() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow1(int row) {
	}

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	protected HtmlAjaxCommandButton btnAgregarOpcion = new HtmlAjaxCommandButton();
	protected HtmlAjaxCommandButton btnQuitarOpcion = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnAgregarOpcion() {
		return btnAgregarOpcion;
	}

	public void setBtnAgregarOpcion(HtmlAjaxCommandButton btnAgregarOpcion) {
		this.btnAgregarOpcion = btnAgregarOpcion;
	}

	public HtmlAjaxCommandButton getBtnQuitarOpcion() {
		return btnQuitarOpcion;
	}

	public void setBtnQuitarOpcion(HtmlAjaxCommandButton btnQuitarOpcion) {
		this.btnQuitarOpcion = btnQuitarOpcion;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new ConjuntoAtributoTabla());

		// Para limpiar propiedades que puedan haber quedado de pantallas anteriores.
		this.getComunicationBean().setListaPropiedadesConfiguracionRecurso(null);

		this.armarDDOpciones();

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		ConjuntoAtributoTabla conjuntoAtributoTabla = this.obtenerObjetoDelElementoPila(0, ConjuntoAtributoTabla.class);

		List listaUsuarios = this.getObjectListDataProvider2().getList();
		conjuntoAtributoTabla.setListaUsuarios(listaUsuarios);
		this.setListaDelCommunication2(listaUsuarios);

		if(this.getObjectListDataProvider().getList() != null && this.getObjectListDataProvider().getList().size() > 0) {
			this.getObjectListDataProvider().commitChanges();
		}
		List listaOpciones = this.getObjectListDataProvider().getList();
		if(listaOpciones.size() <= 0) {
			System.out.println("-------- lista null o vacio");
		}
		conjuntoAtributoTabla.setListaAtributosTabla(listaOpciones);
		this.setListaDelCommunication(listaOpciones);

		this.getElementoPila().getObjetos().set(0, conjuntoAtributoTabla);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		ConjuntoAtributoTabla conjuntoAtributoTabla = this.obtenerObjetoDelElementoPila(0, ConjuntoAtributoTabla.class);

		this.getObjectListDataProvider2().setList(conjuntoAtributoTabla.getListaUsuarios());
		this.setListaDelCommunication2(new ArrayList(conjuntoAtributoTabla.getListaUsuarios()));

		this.getObjectListDataProvider().setList(conjuntoAtributoTabla.getListaAtributosTabla());
		this.setListaDelCommunication(new ArrayList(conjuntoAtributoTabla.getListaAtributosTabla()));

		if(this.getRequestBean1().getObjetosSeleccionMultiple() != null) {
			ArrayList seleccion = this.getRequestBean1().getObjetosSeleccionMultiple();

			try {
				List<Usuario> locListUsuarios = conjuntoAtributoTabla.getListaUsuarios();

				if(locListUsuarios == null) {
					locListUsuarios = new ArrayList<Usuario>();
				}

				AdminPageBean adminUsuario = new AdminUsuario();
				adminUsuario.getBtnModificar().setRendered(true);
				adminUsuario.getBtnEliminar().setRendered(true);

				String mensaje = "";
				List listaNuevos = new ArrayList();
				for(int i = 0; i < seleccion.size(); i++) {
					Usuario locUsuario = (Usuario) seleccion.get(i);

					boolean valor = false;

					for(Usuario cadaUsuario : locListUsuarios) {
						if(cadaUsuario.getIdUsuario() == locUsuario.getIdUsuario()) {
							mensaje += cadaUsuario.toString() + ", ";
							valor = true;
							break;
						}
					}
					if(!valor) {
						listaNuevos.add(locUsuario);

						if(conjuntoAtributoTabla.getListaUsuarios() == null) {
							conjuntoAtributoTabla.setListaUsuarios(new ArrayList<Usuario>());
						}

						conjuntoAtributoTabla.getListaUsuarios().add(locUsuario);
					}

					this.setListaDelCommunication2(conjuntoAtributoTabla.getListaUsuarios());
					this.getObjectListDataProvider2().setList(conjuntoAtributoTabla.getListaUsuarios());
				}

				if(!mensaje.equals("")) {
					warn("Los siguientes Usuarios ya estaban incluidas en la lista: " + mensaje.substring(0, mensaje.length() - 2) + ".");
				}

				this.getElementoPila().getObjetos().set(0, conjuntoAtributoTabla);
			} catch(Exception e) {
				log("ModificarConfiguracionRecurso_MostrarError:", e);
				error("Modificar Configuracion Recurso - Mostrar: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public String btnAgregarUsuario_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setTipoSeleccion("MULTIPLE");

			retorno = "AdminUsuario";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarUsuario_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		ConjuntoAtributoTabla conjAtrTa = this.obtenerObjetoDelElementoPila(0, ConjuntoAtributoTabla.class);

		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado2();
				if(rk != null) {
					int index = getNroFila(rk.toString());

					Object obj = this.getObjectListDataProvider2().getObjects()[index];

					Usuario usuario = (Usuario) obj;

					if(this.getObjectListDataProvider2().getList().size() > 0) {
						this.getObjectListDataProvider2().commitChanges();
					}

					conjAtrTa.getListaUsuarios().remove(usuario);

					this.setListaDelCommunication2(conjAtrTa.getListaUsuarios());
					this.getObjectListDataProvider2().setList(this.getListaDelCommunication2());
					this.getElementoPila().getObjetos().set(0, conjAtrTa);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
		}
		return retorno;
	}

	public String btnAgregarOpcion_action() {
		if(this.ddOpciones.getSelected() != null && this.ddOpciones.getSelected().toString().length() > 0) {
			this.guardarEstadoObjetosUsados();
			ConjuntoAtributoTabla conjuntoAtributoTabla = this.obtenerObjetoDelElementoPila(0, ConjuntoAtributoTabla.class);

			boolean esta = false;
			for(ConfiguracionAtributoTabla cadaAtributo : conjuntoAtributoTabla.getListaAtributosTabla()) {
				if(cadaAtributo.getNombreAtributo().equals(this.ddOpciones.getSelected().toString())) {
					esta = true;
					break;
				}
			}
			if(!esta) {
				ConfiguracionAtributoTabla atributoTabla = new ConfiguracionAtributoTabla();
				atributoTabla.setNombreAtributo(this.ddOpciones.getSelected().toString());
				atributoTabla.setNombreAtributoTabla(Util.capitalizeFirstAndSeparateWords(atributoTabla.getNombreAtributo()));

				if(conjuntoAtributoTabla.getListaAtributosTabla().size() > 0) {
					List<ConfiguracionAtributoTabla> locLista = conjuntoAtributoTabla.getListaAtributosTabla();
					Collections.sort(locLista, new Comparator<ConfiguracionAtributoTabla>() {
						@Override
						public int compare(ConfiguracionAtributoTabla o1, ConfiguracionAtributoTabla o2) {
							return o1.getOrden().compareTo(o2.getOrden());
						}
					});
					Integer pOrden = locLista.get(locLista.size() - 1).getOrden();
					atributoTabla.setOrden(++pOrden);
				} else {
					atributoTabla.setOrden(1);
				}

				conjuntoAtributoTabla.getListaAtributosTabla().add(atributoTabla);
				this.getDdOpciones().setSelected("");
			} else {
				warn("El tipo de opción seleccionada ya se encuentra en la lista.");
			}
			this.getObjectListDataProvider().setList(conjuntoAtributoTabla.getListaAtributosTabla());
		}

		return "";
	}

	public String btnQuitarOpcion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		ConjuntoAtributoTabla conjuntoAtributoTabla = this.obtenerObjetoDelElementoPila(0, ConjuntoAtributoTabla.class);

		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());

					Object obj = this.getObjectListDataProvider().getObjects()[index];

					ConfiguracionAtributoTabla atributoTabla = (ConfiguracionAtributoTabla) obj;

					if(this.getObjectListDataProvider().getList().size() > 0) {
						this.getObjectListDataProvider().commitChanges();
					}

					conjuntoAtributoTabla.getListaAtributosTabla().remove(atributoTabla);
					System.out.println("lista dsp de eliminar = " + conjuntoAtributoTabla.getListaAtributosTabla() + ", con tamaño = "
							+ conjuntoAtributoTabla.getListaAtributosTabla().size());

					this.setListaDelCommunication(conjuntoAtributoTabla.getListaAtributosTabla());
					this.getObjectListDataProvider().setList(this.getListaDelCommunication());
					this.getElementoPila().getObjetos().set(0, conjuntoAtributoTabla);
				}
			} catch(Exception ex) {
				System.out.println("btnQuitar() entra al catch...");
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
		}

		return retorno;
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getLdpOpciones().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public RowKey getSeleccionado2() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroupUser");
			rk = this.getLdpUsuarios().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpOpciones();
	}

	private ObjectListDataProvider getObjectListDataProvider2() {
		return this.getLdpUsuarios();
	}

	private List getListaDelCommunication2() {
		return this.getComunicationBean().getListaUsuariosConfiguracionRecurso();
	}

	private void setListaDelCommunication2(List lista) {
		this.getComunicationBean().setListaUsuariosConfiguracionRecurso(lista);
	}

	private List getListaDelCommunication() {
		return this.getComunicationBean().getListaAtrTabConfiguracionRecurso();
	}

	private void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaAtrTabConfiguracionRecurso(lista);
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof Usuario) {
			Usuario usuario = (Usuario) pObject;
			ConjuntoAtributoTabla conjuntoAtributoTabla = this.obtenerObjetoDelElementoPila(0, ConjuntoAtributoTabla.class);

			boolean esta = false;
			for(Usuario cadaUsuario : conjuntoAtributoTabla.getListaUsuarios()) {
				if(cadaUsuario.equals(usuario)) {
					esta = true;
					break;
				}
			}
			if(!esta) {
				conjuntoAtributoTabla.getListaUsuarios().add(usuario);
			} else {
				warn("El usuario seleccionado ya se encuentra en la lista.");
			}
			this.getObjectListDataProvider2().setList(conjuntoAtributoTabla.getListaUsuarios());
			this.getElementoPila().getObjetos().set(0, conjuntoAtributoTabla);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		if(pObject instanceof ConfiguracionRecurso) {
			ConfiguracionRecurso configuracion = (ConfiguracionRecurso) pObject;
			Recurso recurso = (Recurso) SecurityMgr.getInstance().getRecursoBySerial(configuracion.getIdRecurso());
			
			ConjuntoAtributoTabla conjuntoAtributoTabla = this.obtenerObjetoDelElementoPila(0, ConjuntoAtributoTabla.class);

			if(recurso.getListaPropiedadesClase() != null) {
				this.getComunicationBean().setListaPropiedadesConfiguracionRecurso(recurso.getListaPropiedadesClase());
				this.agregarAtributosDinamicosComoPropiedades(recurso.getIdRecurso());
				
				this.armarDDOpciones();
			}

			conjuntoAtributoTabla.setConfiguracionRecurso(configuracion);
			this.getElementoPila().getObjetos().set(0, conjuntoAtributoTabla);
		}
		if(pObject instanceof ConjuntoAtributoTabla) {
			ConjuntoAtributoTabla conjuntoAtributoTabla = (ConjuntoAtributoTabla) pObject;

			try {
				this.setListaDelCommunication(conjuntoAtributoTabla.getListaAtributosTabla());
				this.getLdpOpciones().setList(this.getListaDelCommunication());

				this.setListaDelCommunication2(conjuntoAtributoTabla.getListaUsuarios());
				this.getLdpUsuarios().setList(this.getListaDelCommunication2());

				if(conjuntoAtributoTabla.getConfiguracionRecurso().getRecurso().getListaPropiedadesClase() != null) {
					this.getComunicationBean().setListaPropiedadesConfiguracionRecurso(conjuntoAtributoTabla.getConfiguracionRecurso().getRecurso().getListaPropiedadesClase());
					this.agregarAtributosDinamicosComoPropiedades(conjuntoAtributoTabla.getConfiguracionRecurso().getRecurso().getIdRecurso());
				} else {
					this.getComunicationBean().setListaPropiedadesConfiguracionRecurso(null);
				}
				
				armarDDOpciones();
			} catch(Exception ex) {
				error("No se pudieron obtener las Opciones del Recurso: " + ex.getMessage());
				ex.printStackTrace();
			}
			this.getElementoPila().getObjetos().set(0, conjuntoAtributoTabla);
		}
	}

	private void agregarAtributosDinamicosComoPropiedades(Long pSerialVersion) {
		try {
			this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
			FiltroPlantillaAtributosDinamicos locFiltro = new FiltroPlantillaAtributosDinamicos();
			locFiltro.setIdRecurso(pSerialVersion);
			List<PlantillaAtributoDinamico> locListaPlantillas = this.getComunicationBean().getRemoteSystemParametro().findListaPlantillaAtritbutosDinamicos(locFiltro)
					.getListaResultados();
			for(PlantillaAtributoDinamico cadaPlantilla : locListaPlantillas) {
				this.getComunicationBean().getListaPropiedadesConfiguracionRecurso().add("mapaNombresValoresAtributosDinamicos['" + cadaPlantilla.getNombre() + "']");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMConjuntoAtributoTabla";
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMConfiguracionRecurso$ABMConjuntoAtributoTabla}";
	}
	
	@Override
	protected String getNombrePagina() {
		return "Conjunto de Atributos Tabla";
	}

	@Override
	public long getSerialVersionUID() {
		return ConjuntoAtributoTabla.serialVersionUID;
	}

}
