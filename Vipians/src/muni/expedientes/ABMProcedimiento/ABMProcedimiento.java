/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.ABMProcedimiento;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.event.ActionEvent;

import muni.expedientes.Trees.TreeProcedimiento;
import muni.expedientes.panels.PanelPlazo;
import muni.expedientes.panels.PanelResponsableProcedimiento;
import muni.expedientes.tables.ABM_Table;

import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.TreeNode;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.expedientes.recurso.persistent.DocumentoCatalogo;
import com.trascender.expedientes.recurso.persistent.DocumentoProcedimiento;
import com.trascender.expedientes.recurso.persistent.FaseCatalogo;
import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.expedientes.recurso.persistent.NodoProcedimiento;
import com.trascender.expedientes.recurso.persistent.PlazoProcedimiento;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.expedientes.recurso.persistent.Responsabilidad;
import com.trascender.expedientes.recurso.persistent.Responsable;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;
import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;
import com.trascender.expedientes.recurso.persistent.UsuarioExtensor;
import com.trascender.framework.recurso.persistent.Numerador;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMProcedimiento extends ABM_Table {

	private TextField tfNombre = new TextField();
	private Label lblNombre = new Label();

	private Label lblNumerador = new Label();
	private DropDown ddNumerador = new DropDown();
	private SingleSelectOptionsList ddNumeradorDefaultOptions = new SingleSelectOptionsList();

	private PanelResponsableProcedimiento panelResponsable = new PanelResponsableProcedimiento();
	private PanelPlazo panelPlazo = new PanelPlazo();
	private PanelEditNodo panelEditNodo = new PanelEditNodo();

	private DropDown ddResponsabilidadArea = new DropDown();
	private DropDown ddResponsabilidadUsuario = new DropDown();

	private PanelEditNodo.NodoPila nodo;
	public Procedimiento procedimiento;
	private Responsable responsable;
	private Integer selectedNode;
	private PlazoProcedimiento plazo;
	public String bean;
	public String usuarioSeleccion;

	private boolean renderPanelOrden = true;

	public Label getLblNumerador() {
		return lblNumerador;
	}

	public void setLblNumerador(Label lblNumerador) {
		this.lblNumerador = lblNumerador;
	}

	public DropDown getDdNumerador() {
		return ddNumerador;
	}

	public void setDdNumerador(DropDown ddNumerador) {
		this.ddNumerador = ddNumerador;
	}

	public SingleSelectOptionsList getDdNumeradorDefaultOptions() {
		return ddNumeradorDefaultOptions;
	}

	public void setDdNumeradorDefaultOptions(SingleSelectOptionsList ddNumeradorDefaultOptions) {
		this.ddNumeradorDefaultOptions = ddNumeradorDefaultOptions;
	}

	public DropDown getDdResponsabilidadArea() {
		return ddResponsabilidadArea;
	}

	public void setDdResponsabilidadArea(DropDown ddResponsabilidadArea) {
		this.ddResponsabilidadArea = ddResponsabilidadArea;
	}

	public DropDown getDdResponsabilidadUsuario() {
		return ddResponsabilidadUsuario;
	}

	public void setDdResponsabilidadUsuario(DropDown ddResponsabilidadUsuario) {
		this.ddResponsabilidadUsuario = ddResponsabilidadUsuario;
	}

	public Option[] getOptionsResponsabilidad() {
		return this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjectsList(Responsabilidad.Accion.values(), "cap");
	}

	public boolean isRenderPanelOrden() {
		return renderPanelOrden;
	}

	public void setRenderPanelOrden(boolean renderPanelOrden) {
		this.renderPanelOrden = renderPanelOrden;
	}

	private TreeProcedimiento trProcedimiento = new TreeProcedimiento();

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

	public PanelResponsableProcedimiento getPanelResponsable() {
		return panelResponsable;
	}

	public void setPanelResponsable(PanelResponsableProcedimiento panelResponsable) {
		this.panelResponsable = panelResponsable;
	}

	public PanelPlazo getPanelPlazo() {
		return panelPlazo;
	}

	public void setPanelPlazo(PanelPlazo panelPlazo) {
		this.panelPlazo = panelPlazo;
	}

	public PanelEditNodo getPanelEditNodo() {
		return panelEditNodo;
	}

	public void setPanelEditNodo(PanelEditNodo panelEditNodo) {
		this.panelEditNodo = panelEditNodo;
	}

	public TreeProcedimiento getTrProcedimiento() {
		return trProcedimiento;
	}

	public void setTrProcedimiento(TreeProcedimiento trProcedimiento) {
		this.trProcedimiento = trProcedimiento;
	}

	@Override
	protected void _init() throws Exception {
		panelResponsable._initTables();
		panelEditNodo._init();

		Set<String> locListaNumeradores = this.getComunicationBean().getMapaNumerador().keySet();
		Option[] opNumerador = new Option[locListaNumeradores.size() + 1];
		int i = 0;
		opNumerador[i++] = new Option("", "");
		for(String cadaNumerador : locListaNumeradores) {
			opNumerador[i++] = new Option(cadaNumerador, cadaNumerador);
		}
		ddNumeradorDefaultOptions.setOptions(opNumerador);
	}

	@Override
	public void getElementosPila() {
		int ind = 0;
		procedimiento = this.obtenerObjetoDelElementoPila(ind++, Procedimiento.class);
		responsable = this.obtenerObjetoDelElementoPila(ind++, Responsable.class);
		selectedNode = this.obtenerObjetoDelElementoPila(ind++, Integer.class);
		plazo = this.obtenerObjetoDelElementoPila(ind++, PlazoProcedimiento.class);
		bean = this.obtenerObjetoDelElementoPila(ind++, String.class);
		nodo = this.obtenerObjetoDelElementoPila(ind++, PanelEditNodo.NodoPila.class);
		usuarioSeleccion = this.obtenerObjetoDelElementoPila(10, String.class);
		// atributosDinamicos = this.obtenerObjetoDelElementoPila(ind++,
		// ArrayList.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void setElementosPila() {
		int ind = 0;

		this.getElementoPila().getObjetos().set(ind++, procedimiento);
		this.getElementoPila().getObjetos().set(ind++, responsable);
		this.getElementoPila().getObjetos().set(ind++, selectedNode);
		this.getElementoPila().getObjetos().set(ind++, plazo);
		this.getElementoPila().getObjetos().set(ind++, bean);
		this.getElementoPila().getObjetos().set(ind++, nodo);
		this.getElementoPila().getObjetos().set(10, usuarioSeleccion);
		// this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		this.getElementosPila();

		if(nodo.object != null) {
			panelEditNodo.guardarEstado(nodo.object);
		}

		panelResponsable.guardarDatos(responsable);
		panelPlazo.guardarDatos(plazo);

		// atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		// procedimiento.setListaAtributosDinamicos(atributosDinamicos);

		procedimiento.setResponsable(responsable);
		procedimiento.setPlazo(plazo);
		String textNombre = this.getTextFieldValue(getTfNombre());
		if(textNombre != null) {
			textNombre = capitalizeOnlyFirst(textNombre);
		}
		procedimiento.setNombre(textNombre);
		
		Numerador locNumerador = this.getDDObjectValue(this.getDdNumerador(), this.getComunicationBean().getMapaNumerador());
		if(locNumerador != null) {
			procedimiento.setNumerador(locNumerador);
		}
		
		this.setElementosPila();
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		acomodarSeleccionado();
		this.getElementosPila();

		if(nodo.object != null) {
			panelEditNodo.mostrarEstado(nodo.object);
		} else {
			// panelEditNodo.mostrarEstado(trProcedimiento.getObject(procedimiento));
		}

		panelResponsable.mostrarDatos(responsable);
		panelPlazo.mostrarDatos(plazo);

		this.setTrProcedimiento(armarArbol(procedimiento));
		this.getTfNombre().setText(procedimiento.getNombre());
		
		if(procedimiento.getNumerador() != null) {
			this.getDdNumerador().setSelected(procedimiento.getNumerador().getNombre());
		}
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Procedimiento());
		ep.getObjetos().add(ind++, null);
		ep.getObjetos().add(ind++, new Integer(0));
		ep.getObjetos().add(ind++, null);
		ep.getObjetos().add(ind++, new String());
		ep.getObjetos().add(ind++, panelEditNodo.new NodoPila());
		ep.getObjetos().add(ind++, new ArrayList());
		ep.getObjetos().add(ind++, new Integer(0)); // 7 nro para diferenciar fases de fases especiales
		ep.getObjetos().add(ind++, null); // 8 nro id nodo padre
		ep.getObjetos().add(ind++, null); // 9 tramite
		ep.getObjetos().add(ind++, null); // 10 String para distinguir el tipo de Usuario a seleccionar

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		this.getElementosPila();

		if(bean.equals("EditarNodoBean") && nodo.object != null) {
			if(nodo.responsable == null) {
				nodo.responsable = new Responsable();
			}
			if(usuarioSeleccion.equals("extensor")) {
				UsuarioExtensor locUsuarioExtensor = new UsuarioExtensor();
				locUsuarioExtensor.setUsuario((Usuario) pObject);
				locUsuarioExtensor.setResponsable(nodo.responsable);
				usuarioSeleccion = null;
				this.getElementoPila().getObjetos().set(10, null);
				pObject = locUsuarioExtensor;
			}
			panelEditNodo.panelResponsable.procesarObjetoSeleccion(pObject, nodo.responsable);
			nodo.object.setResponsable(nodo.responsable);
		} else {
			panelResponsable.procesarObjetoSeleccion(pObject, responsable);
		}
		if(pObject instanceof FaseCatalogo) {
			Integer pos = (Integer) this.obtenerObjetoDelElementoPila(7);
			if(pos == 1) {
				FaseCatalogo locFC = (FaseCatalogo) pObject;
				if(locFC.getEstado().equals(EstadoPlantilla.ACTIVO)) {
					try {
						locFC = getCommunicationExpedientesBean().getRemoteSystemCatalogos().getFaseCatalogoPorId(locFC.getIdFaseCatalogo());
						FaseProcedimiento fp = new FaseProcedimiento(locFC, procedimiento);
						fp.setOrden(procedimiento.getListaNodosHijos().size());
						procedimiento.getListaNodosHijos().add(fp);
					} catch(RemoteException e) {
						e.printStackTrace();
					} catch(Exception e) {
						e.printStackTrace();
					}
				} else {
					warn("Solo puede seleccionar Fases activas");
				}

			} else if(pos == 2) {
				FaseProcedimiento locFaseProcedimiento = null;
				if(!procedimiento.getListaNodosHijos().isEmpty()) {
					locFaseProcedimiento = (FaseProcedimiento) procedimiento.getListaNodosHijos().get(selectedNode);
					FaseCatalogo fc = (FaseCatalogo) pObject;

					try {
						fc = getCommunicationExpedientesBean().getRemoteSystemCatalogos().getFaseCatalogoPorId(fc.getIdFaseCatalogo());
						FaseProcedimiento fp = new FaseProcedimiento(fc, locFaseProcedimiento);
						fp.setOrden(locFaseProcedimiento.getListaNodosHijos().size());
						locFaseProcedimiento.getListaNodosHijos().add(fp);
					} catch(RemoteException e) {
						e.printStackTrace();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		if(pObject instanceof TramiteCatalogo) {
			NodoProcedimiento locFaseProcedimiento = null;
			if(!procedimiento.getListaNodosHijos().isEmpty()) {
				locFaseProcedimiento = procedimiento.getListaNodosHijos().get(selectedNode);
				TramiteCatalogo tc = (TramiteCatalogo) pObject;
				if(tc.getEstado().equals(EstadoPlantilla.ACTIVO)) {
					try {
						tc = getCommunicationExpedientesBean().getRemoteSystemCatalogos().getTramiteCatalogoPorId(tc.getIdTramiteCatalogo());
						Integer nodoHijo = (Integer) this.obtenerObjetoDelElementoPila(8);
						if(nodoHijo != null) {
							FaseProcedimiento faseProcedimiento = ((FaseProcedimiento) locFaseProcedimiento).getListaFasesEspeciales().get(nodoHijo);
							TramiteProcedimiento tp = new TramiteProcedimiento(tc, faseProcedimiento);
							tp.setOrden(faseProcedimiento.getListaNodosHijos().size());
							faseProcedimiento.getListaNodosHijos().add(tp);
						} else {
							TramiteProcedimiento tp = new TramiteProcedimiento(tc, locFaseProcedimiento);
							tp.setOrden(locFaseProcedimiento.getListaNodosHijos().size());
							locFaseProcedimiento.getListaNodosHijos().add(tp);
						}
					} catch(RemoteException e) {
						e.printStackTrace();
					} catch(Exception e) {
						e.printStackTrace();
					}
				} else {
					warn("Solo puede seleccionar Trámites activos");
				}
			}
		}
		if(pObject instanceof DocumentoCatalogo) {
			DocumentoCatalogo locDC = (DocumentoCatalogo) pObject;
			TramiteProcedimiento locTramiteProcedimiento = (TramiteProcedimiento) obtenerObjetoDelElementoPila(9);
			try {
				locDC = getCommunicationExpedientesBean().getRemoteSystemCatalogos().getDocumentoCatalogoPorId(locDC.getIdDocumentoCatalogo());
				DocumentoProcedimiento dp = new DocumentoProcedimiento(locDC, locTramiteProcedimiento);
				dp.setOrden(locTramiteProcedimiento.getListaNodosHijos().size());
				locTramiteProcedimiento.getListaNodosHijos().add(dp);
			} catch(RemoteException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		this.getRequestBean1().setObjetoSeleccion(null);
	}

	@SuppressWarnings({})
	@Override
	protected void procesarObjetoABM(Object pObject) {
		// inicializa mapComboBox
		this.getCommunicationExpedientesBean().setMapaProcedimiento(null);
		getElementosPila();
		procedimiento = (Procedimiento) pObject;

		if(procedimiento.getResponsable() != null) {
			responsable = getCommunicationExpedientesBean().getRemoteSystemProcedimientos().getResponsablePorId(procedimiento.getResponsable().getIdResponsable());
		}
		if(procedimiento.getPlazo() != null) {
			plazo = procedimiento.getPlazo();
		}

		setElementosPila();
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMProcedimiento";
	}

	// Arbol de procedimiento

	private TreeProcedimiento armarArbol(Procedimiento pProcedimiento) {
		TreeProcedimiento arbol = new TreeProcedimiento();
		arbol.createTreeProcedimiento(pProcedimiento, 0);

		return arbol;
	}

	public String btnAgregarFase_action() {
		String retorno = trProcedimiento.agregarFase_action();
		if(retorno != null) {
			this.getElementoPila().getObjetos().set(7, 1);
			retorno = navegarParaSeleccionar(retorno);
		}

		return retorno;
	}

	public String btnAgregarFaseEspecial_action() {
		String retorno = trProcedimiento.agregarFaseEspecial_action(this.getElementoPila());
		if(retorno != null) {
			this.getElementoPila().getObjetos().set(7, 2);
			retorno = navegarParaSeleccionar(retorno);
		}

		return retorno;
	}

	public String btnAgregarTramite_action() {
		String retorno = trProcedimiento.agregarTramite_action(this.getElementoPila());
		if(retorno != null) {
			retorno = navegarParaSeleccionar(retorno);
		}

		return retorno;
	}

	public String btnAgregarDocumento_action() {
		String retorno = trProcedimiento.agregarDocumento_action(this.getElementoPila());
		if(retorno != null) {
			retorno = navegarParaSeleccionar(retorno);
		}

		return retorno;
	}

	public void btnQuitarElemento_action() {
		trProcedimiento.quitarElemento_action(this.getElementoPila());
	}

	// tabla areas responsables
	public String btnAgregarArea_action() {
		getElementosPila();
		bean = "AbmProcedimiento";
		setElementosPila();

		return navegarParaSeleccionar("AdminArea");
	}

	public String btnQuitarArea_action() {
		return quitar_action(panelResponsable.getTableAreas());
	}

	public String btnQuitarTodasAreas_action() {
		return quitarTodos_action(panelResponsable.getTableAreas());
	}

	// tabla Usuarios responsables
	public String btnAgregarUsuario_action() {
		getElementosPila();
		bean = "AbmProcedimiento";
		setElementosPila();

		return navegarParaSeleccionar("AdminUsuario");
	}

	public String btnQuitarUsuario_action() {
		return quitar_action(panelResponsable.getTableUsuarios());
	}

	public String btnQuitarTodosUsuarios_action() {
		return quitarTodos_action(panelResponsable.getTableUsuarios());
	}

	@SuppressWarnings("rawtypes")
	public void editarNodo_action(ActionEvent event) {
		List lista = new ArrayList();
		getElementosPila();
		if(nodo.object != null) {
			panelEditNodo.guardarEstado(nodo.object);
		}
		NodoProcedimiento nodeObject = null;
		TreeNode node = (TreeNode) event.getComponent().getParent();
		TreeNode parentNode = (TreeNode) node.getParent();
		String nodoId = node.getId();
		String parentNodeId = parentNode.getId();
		int nodePos = -1;
		int parentPos;

		String[] splitId = nodoId.split("_");
		String[] splitParentId = parentNodeId.split("_");
		if(!nodoId.equals("tnRaiz")) {
			if(splitId[0].equals("F")) {
				lista = procedimiento.getListaNodosHijos();
				nodePos = Integer.parseInt(splitId[1]);
				nodeObject = (NodoProcedimiento) lista.get(nodePos);
			} else if(splitId[0].equals("E")) {
				parentPos = Integer.parseInt(splitParentId[1]);
				nodePos = Integer.parseInt(splitId[1]);
				FaseProcedimiento locFase = procedimiento.getListaFasesProcedimiento().get(parentPos);
				lista = locFase.getListaFasesEspeciales();
				nodeObject = (NodoProcedimiento) lista.get(nodePos);
			} else if(splitId[0].equals("T")) {
				parentPos = Integer.parseInt(splitParentId[1]);
				nodePos = Integer.parseInt(splitId[1]);
				FaseProcedimiento locFase = procedimiento.getListaFasesProcedimiento().get(parentPos);
				lista = locFase.getListaTramitesProcedimientos();
				nodeObject = (NodoProcedimiento) lista.get(nodePos);
			} else if(splitId[0].equals("D")) {
				int posicionFase = Integer.parseInt(parentNode.getParent().getId().split("_")[1]);
				int posicionTramite = Integer.parseInt(splitParentId[1]);
				int posicionDocumento = Integer.parseInt(splitId[1]);
				nodeObject = procedimiento.getListaFasesProcedimiento().get(posicionFase).getListaTramitesProcedimientos().get(posicionTramite).getListaDocumentosProcedimiento()
						.get(posicionDocumento);
			}
		}

		nodo.object = nodeObject;
		if(nodeObject != null) {
			if(!(nodeObject instanceof DocumentoProcedimiento)) {
				nodo.plazo = nodeObject.getPlazo();
				nodo.responsable = nodeObject.getResponsable();
			} else {
				System.out.println("es un documento...");
			}
		}

		panelEditNodo.mostrarEstado(nodeObject);
		setElementosPila();
	}

	public void btnFirstNodo_action() {
		trProcedimiento.setFirstPosition(getElementoPila());
	}

	public void btnNextNodo_action() {
		trProcedimiento.setNextPosition(getElementoPila());
	}

	public void btnPreviousNodo_action() {
		trProcedimiento.setPreviousPosicion(getElementoPila());
	}

	public void btnLastNodo_action() {
		trProcedimiento.setLastPosition(getElementoPila());
	}

	@Override
	public String getNombreBean() {
		return "#{expedientes$ABMProcedimiento$ABMProcedimiento}";
	}

	@Override
	public long getSerialVersionUID() {
		return Procedimiento.serialVersionUID;
	}

}