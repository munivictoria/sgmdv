package muni.habilitaciones.grpTipoParametro.ABMPlan;

import java.util.Set;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.habilitaciones.recurso.persistent.Plan;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMPlan extends ABMPageBean {

	@Override
	protected void _init () throws Exception{
		Set<String> locListaTipoObligacion = this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().keySet();
		Option[] opTipo = new Option[locListaTipoObligacion.size() + 1];

		int i=0;
		opTipo[i++] = new Option("" , "");
		for (String cadaTipo : locListaTipoObligacion){
			opTipo[i++] = new Option(cadaTipo, cadaTipo);
		}
		this.ddTipoObligacionDefaultOptions.setOptions(opTipo);
	}

	Label lblTipoObligacion = new Label();
	Label lblNombre = new Label();
	Label lblPlanPorDefecto = new Label();
	Checkbox cbPlanPorDefecto = new Checkbox();
	TextField tfNombre = new TextField();
	DropDown ddTipoObligacion = new DropDown();
	private SingleSelectOptionsList ddTipoObligacionDefaultOptions = new SingleSelectOptionsList();

	private ObjectListDataProvider ldpPlan = new ObjectListDataProvider();


	public Label getLblPlanPorDefecto() {
		return lblPlanPorDefecto;
	}

	public void setLblPlanPorDefecto(Label lblPlanPorDefecto) {
		this.lblPlanPorDefecto = lblPlanPorDefecto;
	}

	public Checkbox getCbPlanPorDefecto() {
		return cbPlanPorDefecto;
	}

	public void setCbPlanPorDefecto(Checkbox cbPlanPorDefecto) {
		this.cbPlanPorDefecto = cbPlanPorDefecto;
	}

	public SingleSelectOptionsList getDdTipoObligacionDefaultOptions() {
		return ddTipoObligacionDefaultOptions;
	}

	public void setDdTipoObligacionDefaultOptions(
			SingleSelectOptionsList ddTipoObligacionDefaultOptions) {
		this.ddTipoObligacionDefaultOptions = ddTipoObligacionDefaultOptions;
	}

	public Label getLblTipoObligacion() {
		return lblTipoObligacion;
	}

	public void setLblTipoObligacion(Label lblTipoObligacion) {
		this.lblTipoObligacion = lblTipoObligacion;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public DropDown getDdTipoObligacion() {
		return ddTipoObligacion;
	}

	public void setDdTipoObligacion(DropDown ddTipoObligacion) {
		this.ddTipoObligacion = ddTipoObligacion;
	}

	public ObjectListDataProvider getLdpPlan() {
		return ldpPlan;
	}

	public void setLdpPlan(ObjectListDataProvider ldpPlan) {
		this.ldpPlan = ldpPlan;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {

		Plan locPlan = obtenerObjetoDelElementoPila(0, Plan.class);
		locPlan.setNombre(getTextFieldValue(this.getTfNombre()));
		locPlan.setTipoObligacion(getDDObjectValue(this.getDdTipoObligacion(), this.getCommunicationHabilitacionesBean().getMapaTipoObligacion()));
		locPlan.setPorDefecto(this.getCbPlanPorDefecto().isChecked());

		this.getElementoPila().getObjetos().set(0, locPlan);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {

		Plan locPlan = obtenerObjetoDelElementoPila(0, Plan.class);
		if(locPlan.getNombre() != null ){
			this.getTfNombre().setValue(locPlan.getNombre().toString());
		}
		if(locPlan.getTipoObligacion() != null){
			this.getDdTipoObligacion().setSelected(locPlan.getTipoObligacion().toString());
		}
		this.getCbPlanPorDefecto().setSelected(locPlan.isPorDefecto());
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMPlan";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Plan());
		return ep;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		this.getElementoPila().getObjetos().set(0, pObject);
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		Plan locPlan = this.obtenerObjetoDelElementoPila(0, Plan.class);
		this.getTablaLogs().getLdpLogs().setList(locPlan.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return Plan.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpTipoParametro$ABMPlan$ABMPlan}";
	}
}
