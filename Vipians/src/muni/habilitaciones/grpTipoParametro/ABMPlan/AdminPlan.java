package muni.habilitaciones.grpTipoParametro.ABMPlan;

import java.util.List;
import java.util.Set;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlan;
import com.trascender.habilitaciones.recurso.persistent.Plan;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

public class AdminPlan extends AdminPageBean {
	
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
	
	private Label lblTipoObligacion = new Label();
	private Label lblNombre = new Label();
	private TextField tfNombre = new TextField();
	private DropDown ddTipoObligacion = new DropDown();
	private SingleSelectOptionsList ddTipoObligacionDefaultOptions = new SingleSelectOptionsList();
	
	private ObjectListDataProvider ldpPlan = new ObjectListDataProvider();

	
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
		protected List getListaDelCommunication() {
			 return getCommunicationHabilitacionesBean().getListaPlan();
		}

		@Override
		protected void setListaDelCommunication(List lista) {
			 getCommunicationHabilitacionesBean().setListaPlan(lista);
		}
		
		  @Override
		    protected TableSelectPhaseListener getTablePhaseListener() {
		        return getCommunicationHabilitacionesBean().getTablePhaseListenerPlan();
		    }

		@Override
		protected void limpiarObjetosUsados() {
			FiltroPlan locFiltro = this.getFiltro();
			locFiltro.setNombre(null);
			locFiltro.setTipoObligacion(null);
			
			this.getTfNombre().setText("");
			this.getDdTipoObligacion().setSelected("");
		}

		@Override
		protected Object getObjectPorId(Object pObject) throws Exception {
			Plan locPlan = (Plan) pObject;
			this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().setLlave(this.getSessionBean1().getLlave());
			locPlan = this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().getPlanPorId(locPlan.getIdPlan());
		    return locPlan;
		}

		@Override
		protected void guardarEstadoObjetosUsados() {
		FiltroPlan locFiltroPlan= this.getFiltro();
	    	
	        locFiltroPlan.setNombre(getTextFieldValue(this.getTfNombre()));
	        locFiltroPlan.setTipoObligacion(getDDObjectValue(this.getDdTipoObligacion(), this.getCommunicationHabilitacionesBean().getMapaTipoObligacion()));
		}

		@Override
		protected void mostrarEstadoObjetosUsados() {
			
			FiltroPlan locFiltroPlan= this.getFiltro();
			
			if(locFiltroPlan.getNombre() != null){
			this.getLblNombre().setText(locFiltroPlan.getNombre());
			}
			if(locFiltroPlan.getTipoObligacion() != null){
			this.getDdTipoObligacion().setSelected(locFiltroPlan.getTipoObligacion());
			}
		}

		@Override
		protected String getNombrePagina() {
			return "Plan";
		}

		@Override
		protected String getCasoNavegacion() {
			return "AdminPlan";
		}

		@Override
		protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		        return ep;
		}
		
		public String btnAgregar_action() {
			return toAbm(new PlanModel().new AgregarController());
		}

		public String btnModificar_action() {
			return toAbm(new PlanModel().new ModificarController());
		}

		public String btnEliminar_action() {
			return toAbm(new PlanModel().new EliminarController());
		}

		public String btnConsultar_action() {
			return toAbm(new PlanModel().new ConsultarController());
		}

		@Override
		protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
			FiltroPlan locFiltroPlan= (FiltroPlan) pFiltro;
			return this.getCommunicationHabilitacionesBean().getRemoteSystemTipoTasa().findListaPlan(locFiltroPlan);
		}

		@Override
		public PaginatedTable getPaginatedTable() {
			return this.getCommunicationHabilitacionesBean().getTablaPlan();
		}

		@Override
		public ObjectListDataProvider getObjectListDataProvider() {
			return this.getLdpPlan();
		}

		@Override
		protected void procesarObjetoSeleccion(Object pObject) {
			
		}

		@Override
		public long getSerialVersionUID() {
			return Plan.serialVersionUID;
		}

		@Override
		public String getNombreBean(){
			return "#{habilitaciones$grpTipoParametro$ABMPlan$AdminPlan}";
		}
}
