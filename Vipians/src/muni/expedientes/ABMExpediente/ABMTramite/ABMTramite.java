
package muni.expedientes.ABMExpediente.ABMTramite;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import muni.expedientes.AbmNodoExpediente;
import muni.expedientes.NodoExpedienteControler;
import muni.expedientes.ABMExpediente.DocumentoModel;
import muni.expedientes.panels.PanelPlazoExpediente;
import muni.expedientes.panels.PanelResponsableExpediente;
import muni.expedientes.tables.TableDocumentos;
import muni.expedientes.tables.TableTramite;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.expedientes.recurso.persistent.Documento;
import com.trascender.expedientes.recurso.persistent.EstadoTramite;
import com.trascender.expedientes.recurso.persistent.Tramite;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;
import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;
import com.trascender.presentacion.abstracts.controller.ABMController;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;

public class ABMTramite extends AbmNodoExpediente {

	private Label lblResponsable = new Label();
	private PanelResponsableExpediente panelResponsable = new PanelResponsableExpediente();

	private TextField tfNombre = new TextField();
	private Label lblNombre = new Label();
	private TextField tfFechaInicio = new TextField();
	private Label lblFechaInicio = new Label();
	private TextField tfFechaFin = new TextField();
	private Label lblFechaFin = new Label();

	private PanelGroup pgDatosRequeridos = new PanelGroup();
	private Label lbUsuario = new Label();

	private PanelPlazoExpediente panelPlazoExpediente = new PanelPlazoExpediente();

	private Label lblComentarios = new Label();
	private TextArea taComentarios = new TextArea();

	private Label lblEstado = new Label();
	private DropDown ddEstado = new DropDown();
	private SingleSelectOptionsList ddEstadoDefaultOptions = new SingleSelectOptionsList();

	private TableDocumentos tableDocumentos = new TableDocumentos();
	
	private Checkbox cbPresentado = new Checkbox();
	private TextArea taObservacion = new TextArea();
	
	private HtmlAjaxCommandButton btnSeleccionarTodosDocumentosPresentados = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnSeleccionarTodosDocumentosPresentados() {
		return btnSeleccionarTodosDocumentosPresentados;
	}

	public void setBtnSeleccionarTodosDocumentosPresentados(HtmlAjaxCommandButton btnSeleccionarTodosDocumentosPresentados) {
		this.btnSeleccionarTodosDocumentosPresentados = btnSeleccionarTodosDocumentosPresentados;
	}

	private List<Boolean> listaPresentados = new ArrayList<Boolean>();

	public List<Boolean> getListaPresentados() {
		return listaPresentados;
	}

	public void setListaPresentados(List<Boolean> listaPresentados) {
		this.listaPresentados = listaPresentados;
	}

	@SuppressWarnings("rawtypes")
	private ArrayList listaDocumentos;
	private TableTramite.WTramite wTramite;
	private Tramite tramite = new Tramite();
	
	public PanelGroup getPgDatosRequeridos() {
		return pgDatosRequeridos;
	}

	public void setPgDatosRequeridos(PanelGroup pgDatosRequeridos) {
		this.pgDatosRequeridos = pgDatosRequeridos;
	}

	public Checkbox getCbPresentado() {
		return cbPresentado;
	}

	public void setCbPresentado(Checkbox cbPresentado) {
		this.cbPresentado = cbPresentado;
	}

	public TextArea getTaObservacion() {
		return taObservacion;
	}

	public void setTaObservacion(TextArea taObservacion) {
		this.taObservacion = taObservacion;
	}

	public Label getLbUsuario() {
		return lbUsuario;
	}

	public void setLbUsuario(Label lbUsuario) {
		this.lbUsuario = lbUsuario;
	}

	public Label getLblResponsable() {
		return lblResponsable;
	}

	public void setLblResponsable(Label lblResponsable) {
		this.lblResponsable = lblResponsable;
	}

	public PanelResponsableExpediente getPanelResponsable() {
		return panelResponsable;
	}

	public void setPanelResponsable(PanelResponsableExpediente panelResponsable) {
		this.panelResponsable = panelResponsable;
	}

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

	public TextField getTfFechaInicio() {
		return tfFechaInicio;
	}

	public void setTfFechaInicio(TextField tfFechaInicio) {
		this.tfFechaInicio = tfFechaInicio;
	}

	public Label getLblFechaInicio() {
		return lblFechaInicio;
	}

	public void setLblFechaInicio(Label lblFechaInicio) {
		this.lblFechaInicio = lblFechaInicio;
	}

	public TextField getTfFechaFin() {
		return tfFechaFin;
	}

	public void setTfFechaFin(TextField tfFechaFin) {
		this.tfFechaFin = tfFechaFin;
	}

	public Label getLblFechaFin() {
		return lblFechaFin;
	}

	public void setLblFechaFin(Label lblFechaFin) {
		this.lblFechaFin = lblFechaFin;
	}

	public PanelPlazoExpediente getPanelPlazoExpediente() {
		return panelPlazoExpediente;
	}

	public void setPanelPlazoExpediente(PanelPlazoExpediente panelPlazoExpediente) {
		this.panelPlazoExpediente = panelPlazoExpediente;
	}

	public TableDocumentos getTableDocumentos() {
		return tableDocumentos;
	}

	public void setTableDocumentos(TableDocumentos tableDocumentos) {
		this.tableDocumentos = tableDocumentos;
	}

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	public DropDown getDdEstado() {
		return ddEstado;
	}

	public void setDdEstado(DropDown ddEstado) {
		this.ddEstado = ddEstado;
	}

	public SingleSelectOptionsList getDdEstadoDefaultOptions() {
		return ddEstadoDefaultOptions;
	}

	public void setDdEstadoDefaultOptions(SingleSelectOptionsList ddEstadoDefaultOptions) {
		this.ddEstadoDefaultOptions = ddEstadoDefaultOptions;
	}

	public Label getLblComentarios() {
		return lblComentarios;
	}

	public void setLblComentarios(Label lblComentarios) {
		this.lblComentarios = lblComentarios;
	}

	public TextArea getTaComentarios() {
		return taComentarios;
	}

	public void setTaComentarios(TextArea taComentarios) {
		this.taComentarios = taComentarios;
	}

	@Override
	public void prerender() {
		super.prerender();

	}

	@Override
	protected void _init() throws Exception {
		tableDocumentos._init();
		super._init();
		armarOpcionesMapaEstados();
	}

	@Override
	public void getElementosPila() {
		int ind = 0;
		tramite = obtenerObjetoDelElementoPila(ind++, Tramite.class);
		wTramite = obtenerObjetoDelElementoPila(ind++, TableTramite.WTramite.class);
		listaDocumentos = obtenerObjetoDelElementoPila(ind++, ArrayList.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void setElementosPila() {
		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, tramite);
		this.getElementoPila().getObjetos().set(ind++, wTramite);
		this.getElementoPila().getObjetos().set(ind++, listaDocumentos);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void guardarEstadoObjetosUsados() {
		getElementosPila();

		tramite = wTramite.tramite;
		tramite.setComentarios(this.getTextAreaValue(getTaComentarios()));

		EstadoTramite estadoTramite = this.getDDObjectValue(this.getDdEstado(), getCommunicationExpedientesBean().getMapaEstadoTramite());
		tramite.cambioEstado(getSessionBean1().getUsuario(), estadoTramite);
				
		this.setListaPresentados(new ArrayList<Boolean>());
		for(Documento doc : tramite.getListaDocumentos()) {
			System.out.println(doc.isPresentado());
			this.getListaPresentados().add(doc.isPresentado());
		}
		tableDocumentos.getObjectListDataProvider().commitChanges();
		List<Documento> listaModificada = tableDocumentos.getObjectListDataProvider().getList();
		tramite.documentacionPresentada(getSessionBean1().getUsuario(), this.getListaPresentados(), listaModificada);
	
		for (Documento cadaDocumento : tramite.getListaDocumentos()) {
			cadaDocumento.setTramite(tramite);
		}
		setElementosPila();
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		getElementosPila();
		
		this.getTfNombre().setText(wTramite.tramite.getPlantilla().toString());
		this.getTaComentarios().setText(wTramite.tramite.getComentarios());
		
		if(wTramite.tramite.getEstadoTramite() != null){
		this.getDdEstado().setSelected(wTramite.tramite.getEstadoTramite().getNombre().toString());
		}
		this.mostrartHitos(wTramite.tramite);
		
		panelPlazoExpediente.mostrarDatos(wTramite.tramite.getPlazo());

		TramiteProcedimiento locP;
		try {
			locP = getCommunicationExpedientesBean().getRemoteSystemProcedimientos().getTramiteProcedimientoPorId(tramite.getNodoProcedimiento().getIdNodoProcedimiento());
			this.panelResponsable = new PanelResponsableExpediente(locP.getResponsable());
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

		tableDocumentos.setList(tramite.getListaDocumentos());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, tramite);
		ep.getObjetos().add(ind++, listaDocumentos);
		ep.getObjetos().add(ind++, wTramite);
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}
	
//	public String btnSeleccionarTodosDocumentosPresentados_action() {
//		String retorno = null;
//		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
//		
////		this.guardarEstadoObjetosUsados();
//		if(ultimo) {
//			if(tableDocumentos.getObjectListDataProvider().getList() != null) {
//				List<Documento> listaDocumentos = new ArrayList<Documento>();
//				for(int i = 0; i < tableDocumentos.getObjectListDataProvider().getList().size(); i++) {
//					Documento documento = (Documento) tableDocumentos.getObjectListDataProvider().getList().get(i);
//					documento.setPresentado(true);
//					listaDocumentos.add(documento);
//				}
//				
////				tableDocumentos.setList(listaDocumentos);
//			}
//		} else {
//			retorno = this.prepararCaducidad();
//		}
//		return retorno;
//	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		getElementosPila();
		if(pObject instanceof TableDocumentos.WDocumento) {
			TableDocumentos.WDocumento wDocumento = (TableDocumentos.WDocumento) pObject;
			tableDocumentos.actualizarDocumento(wDocumento);
			setElementosPila();
			this.getRequestBean1().setObjetoSeleccion(null);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		getElementosPila();
		wTramite = (TableTramite.WTramite) pObject;
		clonarTramite(wTramite.tramite, tramite);

		TramiteProcedimiento tramiteProcedimiento = (TramiteProcedimiento) wTramite.tramite.getNodoProcedimiento();
//		this.getPgDatosRequeridos().setRendered(tramiteProcedimiento.getTramiteCatalogo().isDatosRequeridos());

		NodoExpedienteControler controller = (NodoExpedienteControler) getController();
		// setea valores de fecha y estado
		controller.getValoresPorDefecto(tramite);
		setElementosPila();
			
		try {
			TramiteCatalogo locTramiteCatalago = getCommunicationExpedientesBean().getRemoteSystemCatalogos().getTramiteCatalogoPorId(tramiteProcedimiento.getTramiteCatalogo().getIdTramiteCatalogo());
			List<EstadoTramite> listaEstadoTramites = locTramiteCatalago.getListaEstadosTramite();
			getCommunicationExpedientesBean().setOpcionesMapaEstadoTramite(listaEstadoTramites);
			
			armarOpcionesMapaEstados();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private void armarOpcionesMapaEstados() {
		Set<String> locListaNombresEstados = getCommunicationExpedientesBean().getMapaEstadoTramite().keySet();
		
		Option[] opEstados = new Option[locListaNombresEstados.size() + 1];
		int i = 0;
		opEstados[i++] = new Option("", "");
		for(String cadaEstado : locListaNombresEstados) {
			opEstados[i++] = new Option(cadaEstado, cadaEstado);
		}
		ddEstadoDefaultOptions.setOptions(opEstados);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMTramite";
	}

	@Override
	public String btnGuardar_action() {
		getElementosPila();
		clonarTramite(tramite, wTramite.tramite);
		getRequestBean1().setObjetoSeleccion(wTramite);
		return super.btnGuardar_action();
	}

	@Override
	public String btnCancelar_action() {
		getElementosPila();
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.getRequestBean1().setObjetoSeleccion(wTramite);
			// CAMBIAR: comentar esta linea y cambiar el retorno
			retorno = this.prepararParaVolver(Constantes.ACCION_CANCELAR);
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	private void clonarTramite(Tramite t1, Tramite t2) {
		t2.setIdNodoExpediente(t1.getIdNodoExpediente());
		t2.setComentarios(t1.getComentarios());
//		t2.setEstado(t1.getEstado());
		t2.setNodoPadre(t1.getNodoPadre());
		t2.setFechaFin(t1.getFechaFin());
		t2.setFechaInicio(t1.getFechaInicio());
		t2.setListaDocumentos(t1.getListaDocumentos());
		t2.setListaHitos(t1.getListaHitos());
		t2.setNodoProcedimiento(t1.getNodoProcedimiento());

	}

	public String btnAgregarDocumento_action() {
		return toAbm(new DocumentoModel().new AgregarController());
	}

	public String btnModificarDocumento_action() {
		return toAbm(new DocumentoModel().new ModificarController());
	}

	public String btnQuitarDocumentos_action() {
		return quitar_action(tableDocumentos);
	}

	public String btnQuitarTodosDocumentos_action() {
		return quitarTodos_action(tableDocumentos);
	}

	protected String toAbm(ABMController pController) {
		getElementosPila();
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			RowKey rk = null;
			if(pController.seleccionarObjeto()) {
				try {
					rk = this.tableDocumentos.getSeleccionado();

					if(rk != null) {
						int index = getNroFila(rk.toString());

						Documento locDocumento = (Documento) this.tableDocumentos.getObjectListDataProvider().getObjects()[index];

						this.getRequestBean1().setObjetoABM(this.tableDocumentos.new WDocumento(locDocumento, index));
					}

				} catch(Exception ex) {
					log(getCasoNavegacion() + "toAbmException:", ex);
					error(getNombrePagina() + " - toAbm: " + ex.getMessage());
				}
			} else {
				// this.getRequestBean1().setObjetoABM(
				// this.tableDocumentos.new WDocumento(new Documento(), null));
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			if(!pController.seleccionarObjeto() || (pController.seleccionarObjeto() && rk != null)) {
				this.getRequestBean1().setAbmController(pController);
				retorno = pController.getModel().getReglaNavegacion();
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		setElementosPila();
		return retorno;
	}

	@Override
	public String getNombreBean() {
		return "#{expedientes$ABMExpediente$ABMTramite}";
	}

	@Override
	public long getSerialVersionUID() {
		return Tramite.serialVersionUID;
	}
}