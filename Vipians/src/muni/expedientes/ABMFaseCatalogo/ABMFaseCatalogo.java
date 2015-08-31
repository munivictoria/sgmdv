/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.ABMFaseCatalogo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import muni.expedientes.tables.ABM_Table;
import muni.expedientes.tables.TableTramiteCatalogo;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.expedientes.recurso.persistent.FaseCatalogo;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMFaseCatalogo extends ABM_Table {

	private TextField tfNombre = new TextField();
	private Label lblNombre = new Label();

	private Table tablaFaseEspecial = new Table();
	private TableRowGroup tableRowGroup1 = new TableRowGroup();
	private ObjectListDataProvider ldpFasesEspeciales = new ObjectListDataProvider();
	private TableColumn tableColumn1 = new TableColumn();
	private TableColumn tableColumn2 = new TableColumn();
	private RadioButton radioButton1 = new RadioButton();
	private StaticText staticText1 = new StaticText();
	private PanelGroup groupPanel1 = new PanelGroup();
	private Button btnAgregarFaseEspecial = new Button();
	private HtmlAjaxCommandButton btnQuitarFaseEspecial = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnQuitarTodas = new HtmlAjaxCommandButton();
	private StaticText staticText2 = new StaticText();
	private Label lbFasesEspeciales = new Label();

	public Label getLbFasesEspeciales() {
		return lbFasesEspeciales;
	}

	public void setLbFasesEspeciales(Label lbFasesEspeciales) {
		this.lbFasesEspeciales = lbFasesEspeciales;
	}

	public Table getTablaFaseEspecial() {
		return tablaFaseEspecial;
	}

	public void setTablaFaseEspecial(Table tablaFaseEspecial) {
		this.tablaFaseEspecial = tablaFaseEspecial;
	}

	private Object lastSelected = null;

	public Object getRBSelected() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if(selected != null) {
			lastSelected = selected;
		}
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			rk = this.getLdpFasesEspeciales().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		
		return rk;
	}

	public String getCurrentRowFaseEspecial() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRowFaseEspecial(int row) {
	}

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup tableRowGroup1) {
		this.tableRowGroup1 = tableRowGroup1;
	}

	public ObjectListDataProvider getLdpFasesEspeciales() {
		return ldpFasesEspeciales;
	}

	public void setLdpFasesEspeciales(ObjectListDataProvider ldpFasesEspeciales) {
		this.ldpFasesEspeciales = ldpFasesEspeciales;
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

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup groupPanel1) {
		this.groupPanel1 = groupPanel1;
	}

	public Button getBtnAgregarFaseEspecial() {
		return btnAgregarFaseEspecial;
	}

	public void setBtnAgregarFaseEspecial(Button btnAgregarFaseEspecial) {
		this.btnAgregarFaseEspecial = btnAgregarFaseEspecial;
	}

	public HtmlAjaxCommandButton getBtnQuitarFaseEspecial() {
		return btnQuitarFaseEspecial;
	}

	public void setBtnQuitarFaseEspecial(HtmlAjaxCommandButton btnQuitarFaseEspecial) {
		this.btnQuitarFaseEspecial = btnQuitarFaseEspecial;
	}

	public HtmlAjaxCommandButton getBtnQuitarTodas() {
		return btnQuitarTodas;
	}

	public void setBtnQuitarTodas(HtmlAjaxCommandButton btnQuitarTodas) {
		this.btnQuitarTodas = btnQuitarTodas;
	}

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText staticText2) {
		this.staticText2 = staticText2;
	}

	// TramiteCatalogo
	TableTramiteCatalogo tableTC = new TableTramiteCatalogo();

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

	public TableTramiteCatalogo getTableTC() {
		return tableTC;
	}

	public void setTableTC(TableTramiteCatalogo tableTC) {
		this.tableTC = tableTC;
	}

	@Override
	protected void _init() throws Exception {
		tableTC._init();
		if(this.getListaDelCommunication() != null) {
			this.getLdpFasesEspeciales().setList(this.getListaDelCommunication());
		}
	}
	
	@Override
	protected void _prerender() throws Exception {
		super._prerender();
		
		this.getApplicationBean1().aplicarPropiedadesTablaAdmin(this.getTableTC().getTable(), this.getTableTC().getTableRowGroup1());
		this.getApplicationBean1().aplicarPropiedadesTablaAdmin(this.getTablaFaseEspecial(), this.getTableRowGroup1());
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		FaseCatalogo locFaseCatalogo = obtenerObjetoDelElementoPila(ind++, FaseCatalogo.class);
		ArrayList locListaTramitesCatalogos = (ArrayList) obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		// ArrayList locAtributosDinamicos = (ArrayList)
		// this.obtenerObjetoDelElementoPila(ind++,
		// ArrayList.class);
		String textNombre = this.getTextFieldValue(getTfNombre());
		if(textNombre != null) {
			textNombre = capitalizeOnlyFirst(textNombre);
		}
		locFaseCatalogo.setNombre(textNombre);

		locListaTramitesCatalogos = (ArrayList) this.tableTC.getList();
		locFaseCatalogo.setListaTramitesCatalogos(locListaTramitesCatalogos);

		if(this.getLdpFasesEspeciales().getList() != null && this.getLdpFasesEspeciales().getList().size() > 0) {
			this.getLdpFasesEspeciales().commitChanges();
		}
		List fasesEspeciales = (List) this.getLdpFasesEspeciales().getList();
		if(fasesEspeciales.size() <= 0) {
			System.out.println("-------- registros null o vacio");
		}
		locFaseCatalogo.setListaFasesEspeciales(fasesEspeciales);
		this.setListaDelCommunication(fasesEspeciales);

		// locAtributosDinamicos = (ArrayList) panelAtributoDinamico
		// .obtenerListaAtributosDinamicos(locAtributosDinamicos);
		// locFaseCatalogo.setListaAtributosDinamicos(locAtributosDinamicos);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, locFaseCatalogo);
		this.getElementoPila().getObjetos().set(ind++, locListaTramitesCatalogos);
		// this.getElementoPila().getObjetos().set(ind++,
		// locAtributosDinamicos);
	}

	@SuppressWarnings({"rawtypes"})
	@Override
	protected void mostrarEstadoObjetosUsados() {
		acomodarSeleccionado();
		int ind = 0;
		FaseCatalogo locFaseCatalogo = (FaseCatalogo) this.obtenerObjetoDelElementoPila(ind++, FaseCatalogo.class);
		ArrayList locTramitesCatalogos = (ArrayList) obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		this.getTfNombre().setText(locFaseCatalogo.getNombre());

		this.tableTC.setList(locTramitesCatalogos);

		this.getLdpFasesEspeciales().setList(locFaseCatalogo.getListaFasesEspeciales());
		this.setListaDelCommunication(new ArrayList(locFaseCatalogo.getListaFasesEspeciales()));
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new FaseCatalogo());
		ep.getObjetos().add(ind++, new ArrayList());
		ep.getObjetos().add(ind++, new ArrayList()); // AtributosDinamicos
		
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof TramiteCatalogo) {
			TramiteCatalogo nuevaTramiteCatalogo = (TramiteCatalogo) pObject;
			if(nuevaTramiteCatalogo.getEstado().equals(EstadoPlantilla.ACTIVO)) {
				ArrayList locTramitesCatalogos = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
				tableTC.addToList(locTramitesCatalogos, nuevaTramiteCatalogo);
				this.getElementoPila().getObjetos().set(1, locTramitesCatalogos);
			} else {
				warn("Solo puede seleccionar Trámites Activos");
			}

			this.getRequestBean1().setObjetoSeleccion(null);
		}
		if(pObject instanceof FaseCatalogo) {
			FaseCatalogo nuevaFaseEspecial = (FaseCatalogo) pObject;
			try {
				nuevaFaseEspecial = this.getCommunicationExpedientesBean().getRemoteSystemCatalogos().getFaseCatalogoPorId(nuevaFaseEspecial.getIdFaseCatalogo());
			} catch(RemoteException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
			FaseCatalogo locFaseCatalogo = (FaseCatalogo) this.obtenerObjetoDelElementoPila(0, FaseCatalogo.class);
			// ArrayList locListaFasesEspeciales = (ArrayList) this.obtenerObjetoDelElementoPila(2, ArrayList.class);
			if(!locFaseCatalogo.equals(nuevaFaseEspecial)) {
				if(nuevaFaseEspecial.getListaFasesEspeciales().size() == 0) {
					// if(!validarFasesNoReciprocas(locFaseCatalogo, nuevaFaseEspecial)) {
					FaseCatalogo faseExistente = null;
					boolean esta = false;
					int i = 0;
					while(i < locFaseCatalogo.getListaFasesEspeciales().size() && !esta) {
						faseExistente = locFaseCatalogo.getListaFasesEspeciales().get(i);
						esta = (faseExistente.equals(nuevaFaseEspecial));
						i++;
					}
					if(!esta) {
						locFaseCatalogo.getListaFasesEspeciales().add(nuevaFaseEspecial);
					} else {
						warn("La Fase Especial de Cátalogo que intenta agregar ya se encuentra en la lista.");
					}
					this.getElementoPila().getObjetos().set(0, locFaseCatalogo);
				} else {
					warn("No puede agregar una Fase Especial que ya contiene Fases Especiales.");
					// warn("La Fase Especial que intenta agregar ya contiene a esta Fase como Especial.");
				}
			} else {
				warn("No puede agregar como Fase Especial a la misma Fase.");
			}
			this.getRequestBean1().setObjetoSeleccion(null);
		}
	}

	// private boolean validarFasesNoReciprocas(FaseCatalogo pFaseCatalogo, FaseCatalogo pNuevaFaseCatalogo) {
	// for(FaseCatalogo fc : pNuevaFaseCatalogo.getListaFasesEspeciales()) {
	// if(pFaseCatalogo.equals(fc)) {
	// return true;
	// }
	// if(fc.getListaFasesEspeciales().size() > 0) {
	// validarFasesNoReciprocas(pFaseCatalogo, fc);
	// }
	// }
	// return false;
	// }

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	protected void procesarObjetoABM(Object pObject) {
		FaseCatalogo locFaseCatalogo = (FaseCatalogo) pObject;
		ArrayList locListaTramiteCatalogos = null;
		ArrayList locAtributosDinamicos = null;
		try {
			long idFaseCatalogo = locFaseCatalogo.getIdFaseCatalogo();
			this.getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(this.getSessionBean1().getLlave());
			locFaseCatalogo = this.getCommunicationExpedientesBean().getRemoteSystemCatalogos().getFaseCatalogoPorId(idFaseCatalogo);
		} catch(Exception ex) {
			error("No se pudo recuperar FaseCatalogo: " + ex.getMessage());
		}

		locListaTramiteCatalogos = new ArrayList(locFaseCatalogo.getListaTramitesCatalogos());

		if(locFaseCatalogo.getListaAtributosDinamicos() != null) {
			try {
				locAtributosDinamicos = (ArrayList) this.getComunicationBean().getRemoteSystemParametro()
						.getAtributosPorRecurso(FaseCatalogo.serialVersionUID, locFaseCatalogo.getListaAtributosDinamicos(), null);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, locFaseCatalogo);
		this.getElementoPila().getObjetos().set(ind++, locListaTramiteCatalogos);
		this.getElementoPila().getObjetos().set(ind++, locAtributosDinamicos);
	}

	private List getListaDelCommunication() {
		return this.getCommunicationExpedientesBean().getListaFasesEspecialesCatalogo();
	}

	private void setListaDelCommunication(List lista) {
		this.getCommunicationExpedientesBean().setListaFasesEspecialesCatalogo(lista);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMFaseCatalogo";
	}

	public String btnAgregarTC_action() {
		return navegarParaSeleccionar("AdminTramiteCatalogo");
	}

	public String btnQuitarTC_action() {
		return quitar_action(tableTC);
	}

	public String btnQuitarTodosTC_action() {
		return quitarTodos_action(tableTC);
	}

	public String btnAgregarFaseEspecial_action() {
		return navegarParaSeleccionar("AdminFaseCatalogo");
	}

	public String btnQuitarFaseEspecial_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		FaseCatalogo locFaseCatalogo = this.obtenerObjetoDelElementoPila(0, FaseCatalogo.class);

		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());

					Object obj = this.getLdpFasesEspeciales().getObjects()[index];

					FaseCatalogo faseCatalogo = (FaseCatalogo) obj;

					if(this.getLdpFasesEspeciales().getList().size() > 0) {
						this.getLdpFasesEspeciales().commitChanges();
					}

					locFaseCatalogo.getListaFasesEspeciales().remove(faseCatalogo);

					this.setListaDelCommunication(locFaseCatalogo.getListaFasesEspeciales());
					this.getLdpFasesEspeciales().setList(this.getListaDelCommunication());
					this.getElementoPila().getObjetos().set(0, locFaseCatalogo);
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
		}
		
		return retorno;
	}

	public String btnQuitarTodas_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				this.getListaDelCommunication().clear();
				this.getLdpFasesEspeciales().setList(this.getListaDelCommunication());
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	@Override
	public void getElementosPila() {
	}

	@Override
	protected void setElementosPila() {
	}

	@Override
	public String getNombreBean() {
		return "#{expedientes$ABMFaseCatalogo$ABMFaseCatalogo}";
	}

	@Override
	public long getSerialVersionUID() {
		return FaseCatalogo.serialVersionUID;
	}
	
}