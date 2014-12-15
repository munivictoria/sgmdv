package muni.habilitaciones.grpCementerio.ABMTipoSepultura;

import java.util.List;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoSepultura;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
/**
 * 
 * @author nico
 *
 */
public class AdminTipoSepultura extends AdminPageBean {
	
	@Override
	protected void _init() throws Exception {
		this.setListaDelCommunicationAtributosDinamicos(null);
	}

	private Label lbCodigo = new Label();
	private Label lbDescripcion = new Label();
	private TextField tfCodigo = new TextField();
	private TextArea taDescripcion = new TextArea();
	private StaticText staticText1 = new StaticText();
	private PanelAtributoDinamico panelAtributoDinamico = new PanelAtributoDinamico();

    public PanelAtributoDinamico getPanelAtributoDinamico() {
        return panelAtributoDinamico;
    }

    public void setPanelAtributoDinamico(PanelAtributoDinamico panelAtributoDinamico) {
        this.panelAtributoDinamico = panelAtributoDinamico;
    }
	
	public Label getLbCodigo() {
		return lbCodigo;
	}

	public void setLbCodigo(Label lbCodigo) {
		this.lbCodigo = lbCodigo;
	}

	public Label getLbDescripcion() {
		return lbDescripcion;
	}

	public void setLbDescripcion(Label lbDescripcion) {
		this.lbDescripcion = lbDescripcion;
	}

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tfCodigo) {
		this.tfCodigo = tfCodigo;
	}

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea taDescripcion) {
		this.taDescripcion = taDescripcion;
	}
	
	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	private ObjectListDataProvider ldpTipoSepultura = new ObjectListDataProvider();
	
	public ObjectListDataProvider getLdpTipoSepultura() {
		return ldpTipoSepultura;
	}

	public void setLdpTipoSepultura(ObjectListDataProvider ldpTipoSepultura) {
		this.ldpTipoSepultura = ldpTipoSepultura;
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpTipoSepultura();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationHabilitacionesBean().getListaTipoSepultura();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationHabilitacionesBean().setListaTipoSepultura(lista);
	}
	
	private List getListaDelCommunicationAtributosDinamicos() {
        return this.getCommunicationHabilitacionesBean().getListaAtributosDinamicosTipoSepultura();
    }

    private void setListaDelCommunicationAtributosDinamicos(List lista) {
        this.getCommunicationHabilitacionesBean().setListaAtributosDinamicosTipoSepultura(lista);
    }

	@Override
	protected void limpiarObjetosUsados() {
		this.getTfCodigo().setText("");
		this.getTaDescripcion().setText("");
		
		panelAtributoDinamico.limpiarCampos();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		TipoSepultura locTipoSepultura = (TipoSepultura) pObject;
		getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().setLlave(getSessionBean1().getLlave());
        locTipoSepultura = getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().getTipoSepulturaById(locTipoSepultura.getIdTipoAlicuota());
		return locTipoSepultura;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().setLlave(getSessionBean1().getLlave());
		return getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().findListaTipoSepultura((FiltroTipoSepultura) pFiltro);
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
        FiltroTipoSepultura locFiltro = this.getFiltro();
        
        locFiltro.setCodigo(getTextFieldValue(this.getTfCodigo()));
        locFiltro.setDescripcion(getTextAreaValue(this.getTaDescripcion()));
        
        if(locFiltro.getListaAtributosDinamicos() != null){
        	locFiltro.setListaAtributosDinamicos(panelAtributoDinamico.obtenerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos()));
        }
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroTipoSepultura locFiltro = this.getFiltro();
        this.getTfCodigo().setText(locFiltro.getCodigo());
        this.getTaDescripcion().setText(locFiltro.getDescripcion());
        
        panelAtributoDinamico = new PanelAtributoDinamico(locFiltro.getListaAtributosDinamicos(),"#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura}");
        panelAtributoDinamico.establecerListaAtributosDinamicos(locFiltro.getListaAtributosDinamicos());

//        this.setListaDelCommunicationAtributosDinamicos(locFiltro.getListaAtributosDinamicos());
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Tipos de Sepultura";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminTipoSepultura";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		FiltroTipoSepultura locFiltro = getFiltro();
		List atributosDinamicos = null;
		try {
			this.getComunicationBean().getRemoteSystemParametro().setLlave(this.getSessionBean1().getLlave());
			atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(TipoSepultura.serialVersionUID, null, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		locFiltro.setListaAtributosDinamicos(atributosDinamicos);
		
		return ep;
	}
	
	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationHabilitacionesBean().getTablaTipoSepultura();
	}

	public String btnAgregar_action() {
		return toAbm(new TipoSepulturaModel().new AgregarTipoSepulturaController());
	}

	public String btnModificar_action() {
		return toAbm(new TipoSepulturaModel().new ModificarTipoSepulturaController());
	}

	public String btnEliminar_action() {
		return toAbm(new TipoSepulturaModel().new EliminarTipoSepulturaController());
	}

	public String btnConsultar_action() {
		return toAbm(new TipoSepulturaModel().new ConsultarTipoSepulturaController());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		
	}

	@Override
	public long getSerialVersionUID() {
		return TipoSepultura.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpCementerio$ABMTipoSepultura$AdminTipoSepultura}";
	}
}
