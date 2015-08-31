/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.Trees;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import muni.ManejoDePila;
import muni.expedientes.ABMProcedimiento.PanelEditNodo;
import muni.expedientes.ABMProcedimiento.PanelEditNodo.NodoPila;
import muni.expedientes.utils.CookieUtils;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.Script;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TreeNode;
import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.expedientes.recurso.persistent.DocumentoProcedimiento;
import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.expedientes.recurso.persistent.NodoProcedimiento;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;
import com.trascender.presentacion.navegacion.ElementoPila;

public class TreeProcedimiento extends TreeView {
	
	public muni.CommunicationExpedientesBean getCommunicationExpedientesBean() {
		return (muni.CommunicationExpedientesBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("CommunicationExpedientesBean");
	}	

	// private static final String SEL_COOKIE = "selCookie";
	private static final String F = "F" + SEPARATOR_IDMODEL;
	private static final String FE = "E" + SEPARATOR_IDMODEL;
	private static final String T = "T" + SEPARATOR_IDMODEL;
	private static final String D = "D" + SEPARATOR_IDMODEL;
	private static final String URL_FASE = "/resources/imagenes/arboles/grupo.png";
	private static final String URL_FASE_ESPECIAL = "/resources/imagenes/arboles/fase_especial.png";
	private static final String URL_TRAMITE = "/resources/imagenes/arboles/tramite.png";
	private static final String URL_DOCUMENTO = "/resources/imagenes/arboles/documento.png";

	private Label labelFases = new Label();
	private PanelGroup gpBotones = new PanelGroup();
	private Button btnAgregarFase = new Button();
	private Button btnAgregarFaseEspecial = new Button();
	private Button btnAgregarTramite = new Button();
	private Button btnAgregarDocumento = new Button();
	private HtmlAjaxCommandButton btnQuitarElemento = new HtmlAjaxCommandButton();
	private TreeNode tnRaiz = new TreeNode();
	private StaticText staticText1 = new StaticText();
	private Script scriptFinalM1 = new Script();

	private HtmlAjaxCommandButton btnFirst = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnPrevious = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnNext = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnLast = new HtmlAjaxCommandButton();

	public TreeProcedimiento() {
		// btnFirst.setRendered(false);
		// btnPrevious.setRendered(false);
		// btnNext.setRendered(false);
		// btnLast.setRendered(false);

		btnFirst.setStyleClass("buttonPosicionAjax buttonFirst");
		btnPrevious.setStyleClass("buttonPosicionAjax buttonPrevious");
		btnNext.setStyleClass("buttonPosicionAjax buttonNext");
		btnLast.setStyleClass("buttonPosicionAjax buttonLast");
	}

	public Button getBtnAgregarDocumento() {
		return btnAgregarDocumento;
	}

	public void setBtnAgregarDocumento(Button btnAgregarDocumento) {
		this.btnAgregarDocumento = btnAgregarDocumento;
	}

	public Label getLabelFases() {
		return labelFases;
	}

	public void setLabelFases(Label labelFases) {
		this.labelFases = labelFases;
	}

	public PanelGroup getGpBotones() {
		return gpBotones;
	}

	public void setGpBotones(PanelGroup gpBotones) {
		this.gpBotones = gpBotones;
	}

	public Button getBtnAgregarFase() {
		return btnAgregarFase;
	}

	public void setBtnAgregarFase(Button btnAgregarFase) {
		this.btnAgregarFase = btnAgregarFase;
	}

	public Button getBtnAgregarFaseEspecial() {
		return btnAgregarFaseEspecial;
	}

	public void setBtnAgregarFaseEspecial(Button btnAgregarFaseEspecial) {
		this.btnAgregarFaseEspecial = btnAgregarFaseEspecial;
	}

	public Button getBtnAgregarTramite() {
		return btnAgregarTramite;
	}

	public void setBtnAgregarTramite(Button btnAgregarTramite) {
		this.btnAgregarTramite = btnAgregarTramite;
	}

	public HtmlAjaxCommandButton getBtnQuitarElemento() {
		return btnQuitarElemento;
	}

	public void setBtnQuitarElemento(HtmlAjaxCommandButton btnQuitarElemento) {
		this.btnQuitarElemento = btnQuitarElemento;
	}

	public TreeNode getTnRaiz() {
		return tnRaiz;
	}

	public void setTnRaiz(TreeNode tnRaiz) {
		this.tnRaiz = tnRaiz;
	}

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	public Script getScriptFinalM1() {
		return scriptFinalM1;
	}

	public void setScriptFinalM1(Script scriptFinalM1) {
		this.scriptFinalM1 = scriptFinalM1;
	}

	public HtmlAjaxCommandButton getBtnFirst() {
		return btnFirst;
	}

	public void setBtnFirst(HtmlAjaxCommandButton btnFirst) {
		this.btnFirst = btnFirst;
	}

	public HtmlAjaxCommandButton getBtnPrevious() {
		return btnPrevious;
	}

	public void setBtnPrevious(HtmlAjaxCommandButton btnPrevious) {
		this.btnPrevious = btnPrevious;
	}

	public HtmlAjaxCommandButton getBtnNext() {
		return btnNext;
	}

	public void setBtnNext(HtmlAjaxCommandButton btnNext) {
		this.btnNext = btnNext;
	}

	public HtmlAjaxCommandButton getBtnLast() {
		return btnLast;
	}

	public void setBtnLast(HtmlAjaxCommandButton btnLast) {
		this.btnLast = btnLast;
	}

	public class DatosNodo implements TreeView.Datos {

		private String texto;
		private String id;
		private String url;
		@SuppressWarnings("rawtypes")
		private List hijos;

		@SuppressWarnings("rawtypes")
		public DatosNodo(String texto, String id, String url, List hijos) {
			this.texto = texto;
			this.id = id;
			this.url = url;
			this.hijos = hijos;
		}

		@Override
		public String getTexto() {
			return texto;
		}

		@Override
		public String getId() {
			return id;
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Datos> getHijos() {
			return hijos;
		}

		@Override
		public String getUrl() {
			return url;
		}
		
	}

	@SuppressWarnings("rawtypes")
	public List<TreeView.Datos> crearNodosDatos(List lista) {
		List<TreeView.Datos> listaRetorno = new ArrayList<TreeView.Datos>();

		for(int i = 0; i < lista.size(); i++) {
			listaRetorno.add(crearDatos(lista.get(i), i));
		}

		return listaRetorno;
	}

	public String agregarFase_action() {
		String retorno = null;
		try {
			TreeNode nodo = this.getSelectedNode();
			if(nodo != null && "tnRaiz".equalsIgnoreCase(nodo.getId())) {
				retorno = "AdminFaseCatalogo";
			} else {
				warn("Por favor, seleccione la Raiz.");
			}
		} catch(Exception e) {
			warn("Por favor, seleccione la Raiz.");
		}

		return retorno;
	}

	public String agregarFaseEspecial_action(ElementoPila ep) {
		String retorno = null;
		Integer selected = null;
		try {
			TreeNode nodo = this.getSelectedNode();
			selected = Integer.parseInt(nodo.getId().substring(nodo.getId().indexOf(SEPARATOR_IDMODEL) + 1));

			if(nodo != null && nodo.getId().startsWith("F_")) {
				retorno = "AdminFaseCatalogo";
			} else {
				warn("Por favor, seleccione una Fase.");
			}
		} catch(Exception e) {
			selected = null;
			warn("Por favor, seleccione una Fase.");
		}
		ep.getObjetos().set(2, selected);

		return retorno;
	}

	public Object getObject(Procedimiento pProcedimiento) {
		try {
			TreeNode nodo = this.getSelectedNode();
			TreeNode parentNodo = (TreeNode) nodo.getParent();
			int index = -1;
			Object retorno = null;
			if(nodo != null) {
				String nodoId = nodo.getId();
				String prefijo = String.valueOf(nodoId.charAt(0));
				if(prefijo.equalsIgnoreCase("F") || prefijo.equalsIgnoreCase("E")) {
					index = Integer.parseInt(nodoId.substring(nodoId.indexOf(SEPARATOR_IDMODEL) + 1));
					retorno = pProcedimiento.getListaFasesProcedimiento().get(index);
				}
				if(prefijo.equalsIgnoreCase("T")) {
					String parentNodoId = parentNodo.getId();
					int parentIndex = Integer.parseInt(parentNodoId.substring(parentNodoId.indexOf(SEPARATOR_IDMODEL) + 1));
					FaseProcedimiento locFase = pProcedimiento.getListaFasesProcedimiento().get(parentIndex);
					index = Integer.parseInt(nodoId.substring(nodoId.indexOf(SEPARATOR_IDMODEL) + 1));
					retorno = locFase.getListaTramitesProcedimientos().get(index);
				}
			}
			
			return retorno;
		} catch(Exception e) {
			e.printStackTrace();
			warn(e.getMessage());
			
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public String agregarTramite_action(ElementoPila ep) {
		String retorno = null;
		Integer parentSelected = null;
		Integer selected = null;
		try {
			TreeNode nodo = this.getSelectedNode();

			if(nodo != null) {
				String nodoId = nodo.getId();
				String nodoPadreId = nodo.getParent().getId();
				String prefijo = String.valueOf(nodoId.charAt(0));
				String prefijoPadre = String.valueOf(nodoPadreId.charAt(0));

				if(prefijo.equalsIgnoreCase("F") || prefijo.equalsIgnoreCase("E")) {
					try {
						parentSelected = Integer.parseInt(nodoId.substring(nodoId.indexOf(SEPARATOR_IDMODEL) + 1));
					} catch(Exception e) {
						e.printStackTrace();
					}
					retorno = "AdminTramiteCatalogo";
				} else {
					warn("Por favor, seleccione una Fase.");
				}
				if(prefijoPadre.equalsIgnoreCase("F")) {
					try {
						selected = Integer.parseInt(nodoPadreId.substring(nodoPadreId.indexOf(SEPARATOR_IDMODEL) + 1));
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				warn("Por favor, seleccione una Fase.");
			}
		} catch(Exception e) {
			retorno = null;
			selected = null;
			parentSelected = null;
			warn("Por favor, seleccione una Fase.");
		}

		ep.getObjetos().set(2, parentSelected);
		ep.getObjetos().set(8, selected);
		
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public String agregarDocumento_action(ElementoPila ep) {
		String retorno = null;
		Integer parentSelected = null;
		TramiteProcedimiento tramiteProcedimiento = null;
		try {
			TreeNode nodo = this.getSelectedNode();
			if(nodo != null) {
				String nodoId = nodo.getId();
				String prefijo = String.valueOf(nodoId.charAt(0));
				PanelEditNodo.NodoPila nodoPila = (NodoPila) ep.getObjetos().get(5);
				tramiteProcedimiento = (TramiteProcedimiento) nodoPila.object;

				if(prefijo.equalsIgnoreCase("T")) {
					try {
						parentSelected = Integer.parseInt(nodoId.substring(nodoId.indexOf(SEPARATOR_IDMODEL) + 1));
					} catch(Exception e) {
						e.printStackTrace();
					}
					retorno = "AdminDocumentoCatalogo";
				} else {
					warn("Por favor, seleccione un Tramite.");
				}
			}
		} catch(Exception e) {
			retorno = null;
			parentSelected = null;
			warn("Por favor, seleccione un Tramite.");
		}
		ep.getObjetos().set(2, parentSelected);
		ep.getObjetos().set(9, tramiteProcedimiento);
		
		return retorno;
	}

	int posicion;

	List<NodoProcedimiento> lista;
	private Procedimiento procedimiento;

	@SuppressWarnings("unchecked")
	public void quitarElemento_action(ElementoPila ep) {
		try {
			ubicarPosicionNodoSeleccionado(ep);
		} catch(Exception e) {
			e.printStackTrace();
			warn(e.getMessage());
		}
		if(lista != null && lista.size() > 0) {
			if(isEliminar){
				lista.remove(posicion);
				warn("El Elemneto seleccionado se ha Eliminado Correctamente");
			}else{
				warn("El Elemneto seleccionado se ha dado de Baja Correctamente");
			}
			reordenar(lista);
			createTreeProcedimiento(procedimiento, 0);
			ep.getObjetos().set(0, procedimiento);
		}
	}

	@SuppressWarnings("unchecked")
	private void rearmarArbol(ElementoPila ep, NodoProcedimiento nodo) {
		reordenar(lista);
		createTreeProcedimiento(procedimiento, 0);
		actualizarSeleccion(lista.indexOf(nodo));
		ep.getObjetos().set(0, procedimiento);
	}

	public void setFirstPosition(ElementoPila ep) {
		try {
			ubicarPosicionNodoSeleccionado(ep);

			if(posicion > 0) {
				NodoProcedimiento n = lista.get(posicion);
				lista.add(0, n);
				lista.remove(posicion + 1);
				rearmarArbol(ep, n);
			}
		} catch(Exception e) {
			warn(e.getMessage());
		}
	}

	public void setPreviousPosicion(ElementoPila ep) {
		try {
			ubicarPosicionNodoSeleccionado(ep);

			if(posicion > 0) {
				NodoProcedimiento n = lista.get(posicion);
				lista.add(posicion - 1, n);
				lista.remove(posicion + 1);
				rearmarArbol(ep, n);
			}
		} catch(Exception e) {
			warn(e.getMessage());
		}
	}

	public void setNextPosition(ElementoPila ep) {
		try {
			ubicarPosicionNodoSeleccionado(ep);
			if(posicion < lista.size() - 1) {
				NodoProcedimiento n = lista.get(posicion);
				try {
					lista.add(posicion + 2, n);
				} catch(IndexOutOfBoundsException ioobe) {
					lista.add(n);
				}
				lista.remove(posicion);
				rearmarArbol(ep, n);
			}
		} catch(Exception e) {
			warn(e.getMessage());
		}
	}

	public void setLastPosition(ElementoPila ep) {
		try {
			ubicarPosicionNodoSeleccionado(ep);
			if(posicion < lista.size() - 1) {
				NodoProcedimiento n = lista.get(posicion);
				lista.add(n);
				lista.remove(posicion);
				rearmarArbol(ep, n);
			}
		} catch(Exception e) {
			warn(e.getMessage());
		}
	}

	private void actualizarSeleccion(int pPosicion) {
		// substring es la posicion del nodo obtenida desde el clientId
		String substring = selectedCookieValue.substring(selectedCookieValue.lastIndexOf(SEPARATOR_IDMODEL) + 1);
		selectedCookieValue = selectedCookieValue.substring(0, selectedCookieValue.lastIndexOf(substring));
		selectedCookieValue = selectedCookieValue + Integer.valueOf(pPosicion);
		setCookieSelected(selectedCookieValue);
		String clientID = selectedCookieValue.replace(CookieUtils.VALID_CHAR, SEPARATOR_CHAR);
		TreeNode node = getNodo(clientID);
		node.setStyle(HIGHTLIGHT_STYLE);
	}

	Boolean isEliminar = false;
	public void ubicarPosicionNodoSeleccionado(ElementoPila ep) throws Exception {
		procedimiento = (Procedimiento) ManejoDePila.obtenerObjetoDelElementoPila(0, Procedimiento.class, ep);

		TreeNode nodo = this.getSelectedNode();
		if(nodo == null) {
			throw new Exception("Seleccionar Nodo");
		}

		if(nodo.getId().equals("tnRaiz")) {
			throw new Exception("Ha seleccionado el Nodo Principal");
		}
		
		Long cant = null;
		PanelEditNodo.NodoPila nodoPila = (NodoPila) ep.getObjetos().get(5);
		if(nodoPila.object instanceof FaseProcedimiento) {
			FaseProcedimiento locFase = (FaseProcedimiento) nodoPila.object;
			cant = getCommunicationExpedientesBean().getRemoteSystemExpedientes().getExpedientePorNodoProcedimiento(locFase.getNodoPadre().getIdNodoProcedimiento());
			if(cant <= 0){
				isEliminar = true;
			}else{
				locFase.setEstado(EstadoPlantilla.BAJA);
			}
			lista = locFase.getNodoPadre().getListaNodosHijos();
			posicion = locFase.getNodoPadre().getListaNodosHijos().indexOf(nodoPila.object);
		
		} else if(nodoPila.object instanceof TramiteProcedimiento) {
			TramiteProcedimiento locTramite = (TramiteProcedimiento) nodoPila.object;
			cant = getCommunicationExpedientesBean().getRemoteSystemExpedientes().getExpedientePorNodoProcedimiento(locTramite.getNodoPadre().getNodoPadre().getIdNodoProcedimiento());
			if(cant <= 0){
				isEliminar = true;
			}else{
				locTramite.setEstado(EstadoPlantilla.BAJA);
			}
			lista = locTramite.getNodoPadre().getListaNodosHijos();
			posicion = locTramite.getNodoPadre().getListaNodosHijos().indexOf(nodoPila.object);
	
		} else if(nodoPila.object instanceof DocumentoProcedimiento) {
			DocumentoProcedimiento locDocumento = (DocumentoProcedimiento) nodoPila.object;
			cant = getCommunicationExpedientesBean().getRemoteSystemExpedientes().getExpedientePorNodoProcedimiento(locDocumento.getNodoPadre().getNodoPadre().getNodoPadre().getIdNodoProcedimiento());
			if(cant <= 0){
				isEliminar = true;
			}else{
				locDocumento.setEstado(EstadoPlantilla.BAJA);
			}
			lista = locDocumento.getNodoPadre().getListaNodosHijos();
			posicion = locDocumento.getNodoPadre().getListaNodosHijos().indexOf(nodoPila.object);
		}
	}

	@SuppressWarnings("rawtypes")
	private void reordenar(List lista) {
		for(int i = 0; i < lista.size(); i++) {
			NodoProcedimiento n = (NodoProcedimiento) lista.get(i);
			n.setOrden(i);
		}
	}

	public TreeView.Datos crearDatos(Object pObject, int posicion) {
		if(pObject instanceof Procedimiento) {
			Procedimiento p = (Procedimiento) pObject;
			return new DatosNodo(p.getNombre(), "tnRaiz", URL_FASE, crearNodosDatos(p.getListaFasesProcedimiento()));
		}
		if(pObject instanceof FaseProcedimiento) {
			FaseProcedimiento fp = (FaseProcedimiento) pObject;
			DatosNodo locFase = new DatosNodo(fp.getFaseCatalogo().getNombre(), F + posicion, URL_FASE, crearNodosDatos(fp.getListaTramitesProcedimientos()));

			int posicionAux = 0;
			for(FaseProcedimiento cadaFase : fp.getListaFasesEspeciales()) {
				locFase.hijos.add(new DatosNodo(cadaFase.getFaseCatalogo().getNombre(), FE + posicionAux++, URL_FASE_ESPECIAL, crearNodosDatos(cadaFase
						.getListaTramitesYFasesProcedimiento())));
			}

			return locFase;
		}
		if(pObject instanceof TramiteProcedimiento) {
			TramiteProcedimiento tp = (TramiteProcedimiento) pObject;
			return new DatosNodo(tp.getTramiteCatalogo().getNombre(), T + posicion, URL_TRAMITE, crearNodosDatos(tp.getListaDocumentosProcedimiento()));
		}
		if(pObject instanceof DocumentoProcedimiento) {
			DocumentoProcedimiento dp = (DocumentoProcedimiento) pObject;
			return new DatosNodo(dp.getDocumentoCatalogo().getNombre(), D + posicion, URL_DOCUMENTO, null);
		}

		return null;
	}

	public void createTreeProcedimiento(Object pObject, int posicion) {
		createTree(crearDatos(pObject, posicion));
	}

	@Override
	protected String getElExpression() {
		return "#{expedientes$ABMProcedimiento$ABMProcedimiento.editarNodo_action}";
	}

	@Override
	protected String addReRenders() {
		return "incTFNodo, " + btnFirst.getClientId(getFacesContext()) + ", " + btnPrevious.getClientId(getFacesContext()) + ", " + btnNext.getClientId(getFacesContext()) + ", "
				+ btnLast.getClientId(getFacesContext());
	}

	private void desabilitarBtn(HtmlAjaxCommandButton btn, boolean desabilitar) {
		String disabled = "disabled";
		if(desabilitar) {
			btn.setStyleClass(btn.getStyleClass() + disabled);
			// btn.getAttributes().put(disabled, disabled);
			btn.setDisabled(true);
		} else {
			// btn.getAttributes().remove(disabled);
			btn.setDisabled(false);
		}
	}

	public void habilitarBtns(int i) {
		switch(i) {
			case 0:
				desabilitarBtn(btnFirst, true);
				desabilitarBtn(btnPrevious, true);
				desabilitarBtn(btnNext, false);
				desabilitarBtn(btnLast, false);
				break;
			case 1:
				desabilitarBtn(btnFirst, false);
				desabilitarBtn(btnPrevious, false);
				desabilitarBtn(btnNext, true);
				desabilitarBtn(btnLast, true);
				// btnFirst.setDisabled(false);
				// btnPrevious.setDisabled(false);
				// btnNext.setDisabled(true);
				// btnLast.setDisabled(true);
				break;
			default:
				desabilitarBtn(btnFirst, false);
				desabilitarBtn(btnPrevious, false);
				desabilitarBtn(btnNext, false);
				desabilitarBtn(btnLast, false);
				// btnFirst.setDisabled(false);
				// btnPrevious.setDisabled(false);
				// btnNext.setDisabled(false);
				// btnLast.setDisabled(false);
				break;
		}
	}
	
}