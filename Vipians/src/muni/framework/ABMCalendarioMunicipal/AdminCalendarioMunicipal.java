/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.framework.ABMCalendarioMunicipal;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ValueChangeEvent;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlan;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.Plan;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.transients.FiltroCalendarioMunicipal;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author Fer Luca
 */
public class AdminCalendarioMunicipal extends AdminPageBean {


	@Override
	protected void _init() throws Exception {
		armarDropDownTipoObligacion();
		armarDropDownPlan();
	}
	private void armarDropDownTipoObligacion() {
		Option[] op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().keySet().toArray(), "");
		ddTipoObligacionDefaultOptions.setOptions(op);
	}

	private ObjectListDataProvider ldpCalendarioMunicipal = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpCalendarioMunicipal() {
		return ldpCalendarioMunicipal;
	}

	public void setLdpCalendarioMunicipal(ObjectListDataProvider ldpCalendarioMunicipal) {
		this.ldpCalendarioMunicipal = ldpCalendarioMunicipal;
	}

	private  Label lblAnio = new Label();
	private  Label lblTipoObligacion = new Label();
	private  Label lblPlan = new Label();
	private TextField tfAnio= new TextField();
	private  DropDown ddTipoObligacion = new DropDown();
	private  DropDown ddPlan = new DropDown();
	private  SingleSelectOptionsList ddTipoObligacionDefaultOptions = new SingleSelectOptionsList();
	private  SingleSelectOptionsList ddPlanDefaultOptions = new SingleSelectOptionsList();
	private Button btnClonar = new Button();

	public Button getBtnClonar() {
		return btnClonar;
	}
	public void setBtnClonar(Button btnClonar) {
		this.btnClonar = btnClonar;
	}
	public DropDown getDdTipoObligacion() {
		return ddTipoObligacion;
	}

	public void setDdTipoObligacion(DropDown ddTipoObligacion) {
		this.ddTipoObligacion = ddTipoObligacion;
	}

	public DropDown getDdPlan() {
		return ddPlan;
	}

	public void setDdPlan(DropDown ddPlan) {
		this.ddPlan = ddPlan;
	}

	public SingleSelectOptionsList getDdTipoObligacionDefaultOptions() {
		return ddTipoObligacionDefaultOptions;
	}

	public void setDdTipoObligacionDefaultOptions(
			SingleSelectOptionsList ddTipoObligacionDefaultOptions) {
		this.ddTipoObligacionDefaultOptions = ddTipoObligacionDefaultOptions;
	}

	public SingleSelectOptionsList getDdPlanDefaultOptions() {
		return ddPlanDefaultOptions;
	}

	public void setDdPlanDefaultOptions(SingleSelectOptionsList ddPlanDefaultOptions) {
		this.ddPlanDefaultOptions = ddPlanDefaultOptions;
	}

	public Label getLblAnio() {
		return lblAnio;
	}

	public void setLblAnio(Label lblAnio) {
		this.lblAnio = lblAnio;
	}

	public Label getLblTipoObligacion() {
		return lblTipoObligacion;
	}

	public void setLblTipoObligacion(Label lblTipoObligacion) {
		this.lblTipoObligacion = lblTipoObligacion;
	}

	public Label getLblPlan() {
		return lblPlan;
	}

	public void setLblPlan(Label lblPlan) {
		this.lblPlan = lblPlan;
	}

	public TextField getTfAnio() {
		return tfAnio;
	}

	public void setTfAnio(TextField tfAnio) {
		this.tfAnio = tfAnio;
	}

	private Label lblNombre = new Label();

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label l) {
		this.lblNombre = l;
	}

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	public AdminCalendarioMunicipal() {
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationBean().getTablaCalendarioMunicipal();
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroCalendarioMunicipal locFiltro = getFiltro();

		locFiltro.setNombre(this.getTextFieldValue(getTfNombre()));
		locFiltro.setAnio(this.getTextFieldValueInteger(getTfAnio()));
		locFiltro.setTipoObligacion(this.getDDObjectValue(getDdTipoObligacion(), getCommunicationHabilitacionesBean().getMapaTipoObligacion()));
		locFiltro.setPlan(this.getDDObjectValue(this.getDdPlan(), getCommunicationHabilitacionesBean().getMapaPlan()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroCalendarioMunicipal locFiltro = getFiltro();

		this.getTfNombre().setText(locFiltro.getNombre());
		this.getTfAnio().setText(locFiltro.getAnio());

		if (locFiltro.getTipoObligacion() != null ){
			this.getDdTipoObligacion().setSelected(locFiltro.getTipoObligacion().getNombre());
		}
		if (locFiltro.getPlan() != null){
			this.getDdPlan().setSelected(locFiltro.getPlan().getNombre());
		}
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemPeriodo().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemPeriodo().findListaCalendarios((FiltroCalendarioMunicipal) pFiltro);
	}

	@Override
	protected void refrescarTabla() throws Exception {
		FiltroCalendarioMunicipal locFiltro = this.getFiltro();
		this.getComunicationBean().getRemoteSystemPeriodo().setLlave(this.getSessionBean1().getLlave());
		locFiltro = this.getComunicationBean().getRemoteSystemPeriodo().findListaCalendarios(locFiltro);
		this.getPaginatedTable().setFiltro(locFiltro);
		this.setListaDelCommunication(locFiltro.getListaResultados());
		this.getObjectListDataProvider().setList(this.getListaDelCommunication());
	}

	@Override
	protected void limpiarObjetosUsados() {
		FiltroCalendarioMunicipal locFiltro = getFiltro();

		locFiltro.setNombre(null);
		this.getTfNombre().setText("");
		this.getTfAnio().setText("");
		this.getDdTipoObligacion().setSelected("");
		Option[] op2 = new Option[0];
		ddPlanDefaultOptions.setOptions(op2);
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpCalendarioMunicipal();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getComunicationBean().getListaCalendariosMunicipales();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaCalendariosMunicipales(lista);
	}

	public String btnAgregar_action() {
		return toAbm(new CalendarioMunicipalModel().new AgregarCalendarioController());
	}

	public String btnModificar_action() {
		return toAbm(new CalendarioMunicipalModel().new ModificarCalendarioController());
	}

	public String btnEliminar_action() {
		return toAbm(new CalendarioMunicipalModel().new EliminarCalendarioController());
	}

	public String btnConsultar_action() {
		return toAbm(new CalendarioMunicipalModel().new ConsultarCalendarioController());
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}


	public String btnClonar_action() {

		Calendario calendario = (Calendario) this.getObjetoSeleccionado();
		if(calendario == null){
			return null;
		}

		try {
			getComunicationBean().getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
			calendario = getComunicationBean().getRemoteSystemPeriodo().getCalendarioById(calendario.getIdCalendario());
		}catch(Exception e){
			e.printStackTrace();
		}

		try{
			CalendarioMunicipal nuevoCalendario =  (CalendarioMunicipal) calendario.clone();
			nuevoCalendario.setListaLogsAuditoria(null);
			this.getRequestBean1().setObjetoABM(nuevoCalendario);
			return toAbm(new CalendarioMunicipalModel().new ClonarCalendarioController());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;  
	}


	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		CalendarioMunicipal locCalendario = (CalendarioMunicipal) pObject;
		getComunicationBean().getRemoteSystemPeriodo().setLlave(getSessionBean1().getLlave());
		locCalendario = getComunicationBean().getRemoteSystemPeriodo().getCalendarioById(locCalendario.getIdCalendario());
		return locCalendario;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Calendarios Municipales";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminCalendarioMunicipal";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	public void valueChangeEvent(ValueChangeEvent event) {
		String toStringTipoObligacion = (String) this.getDdTipoObligacion().getSelected();
		TipoObligacion tipoObligacion = this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get(toStringTipoObligacion);
		if (tipoObligacion != null) {
			armarMapaPlan(tipoObligacion);
		} else {
			this.getCommunicationHabilitacionesBean().setMapaPlan(null);
			this.armarDropDownPlan();
		}
	}
	public void armarMapaPlan(TipoObligacion tipoObligacion) {
		FiltroPlan locFiltro = new FiltroPlan();
		locFiltro.setTipoObligacion(tipoObligacion);

		try {
			this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());
			List<Plan> locListaPlan = this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().findListaPlan(locFiltro).getListaResultados();
			Map<String, Plan> locMapa = new LinkedHashMap<String, Plan>();
			for (Plan cadaPlan : locListaPlan) {
				locMapa.put(cadaPlan.getNombre().toString(), cadaPlan);
			}
			this.getCommunicationHabilitacionesBean().setMapaPlan(locMapa);
			Option[] op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(locMapa.keySet().toArray(), "");
			ddPlanDefaultOptions.setOptions(op);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void armarDropDownPlan() {
		if (this.getCommunicationHabilitacionesBean().getMapaPlan() != null && !this.getCommunicationHabilitacionesBean().getMapaPlan().isEmpty()) {
			Option[] op2 = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getCommunicationHabilitacionesBean().getMapaPlan().keySet().toArray(), "");
			ddPlanDefaultOptions.setOptions(op2);
		} else {
			ddPlanDefaultOptions.setOptions(new Option[] {});
		}
	}

	@Override
	public long getSerialVersionUID() {
		return Calendario.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{framework$ABMCalendarioMunicipal$AdminCalendarioMunicipal}";
	}
}