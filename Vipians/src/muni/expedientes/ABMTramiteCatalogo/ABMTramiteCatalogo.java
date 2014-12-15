
package muni.expedientes.ABMTramiteCatalogo;

import java.util.ArrayList;

import muni.expedientes.tables.ABM_Table;
import muni.expedientes.tables.TableDocumentoCatalogo;
import muni.expedientes.tables.TableEstadosTramite;

import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.expedientes.recurso.persistent.DocumentoCatalogo;
import com.trascender.expedientes.recurso.persistent.EstadoTramite;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMTramiteCatalogo extends ABM_Table {

	private TextField tfNombre = new TextField();
	private Label lblNombre = new Label();
	private StaticText stEstadosTramite = new StaticText();
	private StaticText stDocumentos = new StaticText();
	private Label lblAvanzarFase = new Label();
	private Label lblReiniciaConFase = new Label();
	private Checkbox cbAvanzarFase = new Checkbox();
	private Checkbox cbReiniciaConFase = new Checkbox();

	public Label getLblAvanzarFase() {
		return lblAvanzarFase;
	}

	public void setLblAvanzarFase(Label lblAvanzarFase) {
		this.lblAvanzarFase = lblAvanzarFase;
	}

	public Label getLblReiniciaConFase() {
		return lblReiniciaConFase;
	}

	public void setLblReiniciaConFase(Label lblReiniciaConFase) {
		this.lblReiniciaConFase = lblReiniciaConFase;
	}

	public Checkbox getCbAvanzarFase() {
		return cbAvanzarFase;
	}

	public void setCbAvanzarFase(Checkbox cbAvanzarFase) {
		this.cbAvanzarFase = cbAvanzarFase;
	}

	public Checkbox getCbReiniciaConFase() {
		return cbReiniciaConFase;
	}

	public void setCbReiniciaConFase(Checkbox cbReiniciaConFase) {
		this.cbReiniciaConFase = cbReiniciaConFase;
	}

	private TableDocumentoCatalogo tableDC = new TableDocumentoCatalogo();
	
	private TableEstadosTramite tableET = new TableEstadosTramite();

	private TramiteCatalogo tramiteCatalogo;

	@SuppressWarnings("rawtypes")
	private ArrayList listaDocumentosCatalogos;
	private ArrayList listaEstadoTramite;

	public StaticText getStDocumentos() {
		return stDocumentos;
	}

	public void setStDocumentos(StaticText stDocumentos) {
		this.stDocumentos = stDocumentos;
	}

	public StaticText getStEstadosTramite() {
		return stEstadosTramite;
	}

	public void setStEstadosTramite(StaticText stEstadosTramite) {
		this.stEstadosTramite = stEstadosTramite;
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
	
	public TableEstadosTramite getTableET() {
		return tableET;
	}

	public void setTableET(TableEstadosTramite tableET) {
		this.tableET = tableET;
	}

	public TableDocumentoCatalogo getTableDC() {
		return tableDC;
	}

	public void setTableDC(TableDocumentoCatalogo tableDC) {
		this.tableDC = tableDC;
	}

	@Override
	protected void _init() throws Exception {
		tableDC._init();
		tableET._init();

	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	protected void guardarEstadoObjetosUsados() {
		getElementosPila();

		String textNombre = this.getTextFieldValue(getTfNombre());
		if(textNombre != null) {
			textNombre = capitalizeOnlyFirst(textNombre);
		}

		tramiteCatalogo.setNombre(textNombre);
		tramiteCatalogo.setRequeridoParaAvanzarDeFase(this.getCbAvanzarFase().isChecked());
		tramiteCatalogo.setReiniciarConFase(this.getCbReiniciaConFase().isChecked());
		listaDocumentosCatalogos = (ArrayList) this.tableDC.getList();
		tramiteCatalogo.setListaDocumentosCatalogos(listaDocumentosCatalogos);
		listaEstadoTramite = (ArrayList) this.tableET.getList();
		tramiteCatalogo.setListaEstadosTramite(listaEstadoTramite);
		
		setElementosPila();
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		acomodarSeleccionado();
		getElementosPila();
		this.getTfNombre().setText(tramiteCatalogo.getNombre());
		this.getCbAvanzarFase().setValue(tramiteCatalogo.isRequeridoParaAvanzarDeFase());
		this.getCbReiniciaConFase().setValue(tramiteCatalogo.isReiniciarConFase());

		this.tableDC.setList(listaDocumentosCatalogos);
		this.tableET.setList(listaEstadoTramite);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new TramiteCatalogo());
		ep.getObjetos().add(ind++, new ArrayList());
		ep.getObjetos().add(ind++, new ArrayList());
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		getElementosPila();
		if(pObject instanceof DocumentoCatalogo) {
			DocumentoCatalogo nuevaDocumentoCatalogo = (DocumentoCatalogo) pObject;
			if (nuevaDocumentoCatalogo.getEstado().equals(EstadoPlantilla.ACTIVO)){
			tableDC.addToList(listaDocumentosCatalogos, nuevaDocumentoCatalogo);
			setElementosPila();
			} else{
				warn("Solo puede seleccionar Documentos activos");
			}
			this.getRequestBean1().setObjetoSeleccion(null);
		}
		if(pObject instanceof EstadoTramite) {
			EstadoTramite nuevaEstadosTramite= (EstadoTramite) pObject;
			if (nuevaEstadosTramite.getEstado().equals(EstadoPlantilla.ACTIVO)){
			tableET.addToList(listaEstadoTramite, nuevaEstadosTramite);
			setElementosPila();
			} else{
				warn("Solo puede seleccionar Estados de Trámites activos");
			}
			this.getRequestBean1().setObjetoSeleccion(null);
		}

	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	protected void procesarObjetoABM(Object pObject) {
		getElementosPila();
		tramiteCatalogo = (TramiteCatalogo) pObject;
		try {
			long idTramiteCatalogo = tramiteCatalogo.getIdTramiteCatalogo();
			this.getCommunicationExpedientesBean().getRemoteSystemCatalogos().setLlave(this.getSessionBean1().getLlave());
			tramiteCatalogo = this.getCommunicationExpedientesBean().getRemoteSystemCatalogos().getTramiteCatalogoPorId(idTramiteCatalogo);
		} catch(Exception ex) {
			error("No se pudo recuperar TramiteCatalogo: " + ex.getMessage());
		}
		listaDocumentosCatalogos = new ArrayList(tramiteCatalogo.getListaDocumentosCatalogos());
		listaEstadoTramite = new ArrayList(tramiteCatalogo.getListaEstadosTramite());

		setElementosPila();

	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMTramiteCatalogo";
	}

	public String btnAgregarDC_action() {
		return navegarParaSeleccionar("AdminDocumentoCatalogo");
	}

	public String btnQuitarDC_action() {
		return quitar_action(tableDC);
	}

	public String btnQuitarTodosDC_action() {
		return quitarTodos_action(tableDC);
	}

	public String btnAgregarET_action() {
		return navegarParaSeleccionar("AdminEstadoTramite");
	}

	public String btnQuitarET_action() {
		return quitar_action(tableET);
	}

	public String btnQuitarTodosET_action() {
		return quitarTodos_action(tableET);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void getElementosPila() {
		int ind = 0;
		tramiteCatalogo = obtenerObjetoDelElementoPila(ind++, TramiteCatalogo.class);
		listaDocumentosCatalogos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		listaEstadoTramite = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);

	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setElementosPila() {
		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, tramiteCatalogo);
		// this.getElementoPila().getObjetos().set(ind++, locAtributosDinamicos);
		this.getElementoPila().getObjetos().set(ind++, listaDocumentosCatalogos);
		this.getElementoPila().getObjetos().set(ind++, listaEstadoTramite);
	}

	@Override
	public String getNombreBean() {
		return "#{expedientes$ABMTramiteCatalogo$ABMTramiteCatalogo}";
	}

	@Override
	public long getSerialVersionUID() {
		return TramiteCatalogo.serialVersionUID;
	}
}