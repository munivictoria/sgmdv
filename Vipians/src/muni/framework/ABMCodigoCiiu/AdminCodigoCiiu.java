/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.framework.ABMCodigoCiiu;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ValueChangeEvent;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.filtros.FiltroCodigoCiiu;
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.recurso.persistent.GrupoCiiu;
import com.trascender.framework.recurso.persistent.SeccionCiiu;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author ubuntero ubuntero no! Fernando Luca!
 */
public class AdminCodigoCiiu extends AdminPageBean {

	protected ObjectListDataProvider ldpCodigosCiiu = new ObjectListDataProvider();

	protected Label lblCodigo = new Label();
	protected Label lblDescripcion = new Label();

	protected TextField tfCodigo = new TextField();
	protected TextField tfDescripcion = new TextField();

	protected TableColumn tcCodigo = new TableColumn();
	protected TableColumn tcDescripcion = new TableColumn();

	protected StaticText stCodigo = new StaticText();
	protected StaticText stDescripcion = new StaticText();

	protected DropDown ddGrupo = new DropDown();
	protected DropDown ddSeccion = new DropDown();

	protected SingleSelectOptionsList ddGrupoDefaultOptions = new SingleSelectOptionsList();
	protected SingleSelectOptionsList ddSeccionDefaultOptions = new SingleSelectOptionsList();

	@Override
	protected void _init() {
		armarDropDownSeccion();
		armarDropDownGrupo();
	}

	private void armarDropDownSeccion() {
		if(this.getComunicationBean().getMapaSeccionesCiiu() == null || this.getComunicationBean().getMapaSeccionesCiiu().isEmpty()) {
			try {
				this.getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
				List<SeccionCiiu> locListaSecciones = this.getComunicationBean().getRemoteSystemMunicipalidad().findListaSeccionCiiu(null, null);
				Map<String, SeccionCiiu> locMapa = new LinkedHashMap<String, SeccionCiiu>();
				for(SeccionCiiu cadaSeccionCiiu : locListaSecciones) {
					locMapa.put(cadaSeccionCiiu.toString(), cadaSeccionCiiu);
				}
				this.getComunicationBean().setMapaSeccionesCiiu(locMapa);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		Option[] op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getComunicationBean().getMapaSeccionesCiiu().keySet().toArray(), "");
		ddSeccionDefaultOptions.setOptions(op);
	}

	private void armarDropDownGrupo() {
		if(this.getComunicationBean().getMapaGruposCiiu() != null && !this.getComunicationBean().getMapaGruposCiiu().isEmpty()) {
			Option[] op2 = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getComunicationBean().getMapaGruposCiiu().keySet().toArray(), "");
			ddGrupoDefaultOptions.setOptions(op2);
		} else {
			ddGrupoDefaultOptions.setOptions(new Option[] {});
		}
	}

	public DropDown getDdGrupo() {
		return ddGrupo;
	}

	public void setDdGrupo(DropDown ddGrupo) {
		this.ddGrupo = ddGrupo;
	}

	public SingleSelectOptionsList getDdGrupoDefaultOptions() {
		return ddGrupoDefaultOptions;
	}

	public void setDdGrupoDefaultOptions(SingleSelectOptionsList ddGrupoDefaultOptions) {
		this.ddGrupoDefaultOptions = ddGrupoDefaultOptions;
	}

	public DropDown getDdSeccion() {
		return ddSeccion;
	}

	public void setDdSeccion(DropDown ddSeccion) {
		this.ddSeccion = ddSeccion;
	}

	public SingleSelectOptionsList getDdSeccionDefaultOptions() {
		return ddSeccionDefaultOptions;
	}

	public void setDdSeccionDefaultOptions(SingleSelectOptionsList ddSeccionDefaultOptions) {
		this.ddSeccionDefaultOptions = ddSeccionDefaultOptions;
	}

	public Label getLblCodigo() {
		return lblCodigo;
	}

	public void setLblCodigo(Label lblCodigo) {
		this.lblCodigo = lblCodigo;
	}

	public Label getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(Label lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	public StaticText getStCodigo() {
		return stCodigo;
	}

	public void setStCodigo(StaticText stCodigo) {
		this.stCodigo = stCodigo;
	}

	public StaticText getStDescripcion() {
		return stDescripcion;
	}

	public void setStDescripcion(StaticText stDescripcion) {
		this.stDescripcion = stDescripcion;
	}

	public TableColumn getTcCodigo() {
		return tcCodigo;
	}

	public void setTcCodigo(TableColumn tcCodigo) {
		this.tcCodigo = tcCodigo;
	}

	public TableColumn getTcDescripcion() {
		return tcDescripcion;
	}

	public void setTcDescripcion(TableColumn tcDescripcion) {
		this.tcDescripcion = tcDescripcion;
	}

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tfCodigo) {
		this.tfCodigo = tfCodigo;
	}

	public TextField getTfDescripcion() {
		return tfDescripcion;
	}

	public void setTfDescripcion(TextField tfDescripcion) {
		this.tfDescripcion = tfDescripcion;
	}

	public ObjectListDataProvider getLdpCodigosCiiu() {
		return ldpCodigosCiiu;
	}

	public void setLdpCodigosCiiu(ObjectListDataProvider ldpCodigosCiiu) {
		this.ldpCodigosCiiu = ldpCodigosCiiu;
	}

	public String btnConsultar_action() {
		return toAbm(new CodigoCiiuModel().new ConsultarController());
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpCodigosCiiu();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getComunicationBean().getListaCodigosCiiu();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationBean().setListaCodigosCiiu(lista);
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationBean().getRemoteSystemMunicipalidad().findListaCodigosCiiu((FiltroCodigoCiiu) pFiltro);
	}

	@Override
	protected void refrescarTabla() throws Exception {
		// CAMBIAR: Utilizar el EJBClient adecuado.
		FiltroCodigoCiiu locFiltro = this.getFiltro();
		this.getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
		locFiltro = this.getComunicationBean().getRemoteSystemMunicipalidad().findListaCodigosCiiu(locFiltro);
		this.getPaginatedTable().setFiltro(locFiltro);
		this.setListaDelCommunication(locFiltro.getListaResultados());
		this.getObjectListDataProvider().setList(this.getListaDelCommunication());
	}

	@Override
	protected void limpiarObjetosUsados() {
		for(int i = 0; i < this.getElementoPila().getObjetos().size(); i++) {
			this.getElementoPila().getObjetos().set(i, null);
		}
		this.getTfCodigo().setText("");
		this.getTfDescripcion().setText("");
		this.getDdGrupo().setSelected("");
		this.getDdGrupoDefaultOptions().setSelectedValue("");
		this.getDdSeccion().setSelected("");
		this.getDdSeccionDefaultOptions().setSelectedValue("");
		this.getComunicationBean().setMapaGruposCiiu(null);
		armarDropDownGrupo();
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		return pObject;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		FiltroCodigoCiiu locFiltro = this.getFiltro();

		locFiltro.setCodigo(this.getTextFieldValue(getTfCodigo()));
		locFiltro.setInformacion(this.getTextFieldValue(getTfDescripcion()));
		locFiltro.setSeccionCiiu(this.getDDObjectValue(getDdSeccion(), getComunicationBean().getMapaSeccionesCiiu()));
		locFiltro.setGrupoCiiu(this.getDDObjectValue(getDdGrupo(), getComunicationBean().getMapaGruposCiiu()));
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		FiltroCodigoCiiu locFiltro = this.getFiltro();

		this.getTfCodigo().setText(locFiltro.getCodigo());
		this.getTfDescripcion().setText(locFiltro.getInformacion());

		if(locFiltro.getSeccionCiiu() != null) {
			this.getDdSeccion().setSelected(locFiltro.getSeccionCiiu().toString());
			this.getDdSeccionDefaultOptions().setSelectedValue(locFiltro.getSeccionCiiu());
		}

		if(locFiltro.getGrupoCiiu() != null) {
			this.getDdGrupo().setSelected(locFiltro.getGrupoCiiu().toString());
			this.getDdGrupoDefaultOptions().setSelectedValue(locFiltro.getGrupoCiiu());
		}
	}

	public void valueChangeEvent(ValueChangeEvent event) {
		String toStringSeccion = (String) this.getDdSeccion().getSelected();
		SeccionCiiu seccionCiiu = this.getComunicationBean().getMapaSeccionesCiiu().get(toStringSeccion);
		if(seccionCiiu != null) {
			System.out.println("Seccion CIIU: " + seccionCiiu);
			try {
				this.getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
				List<GrupoCiiu> locListaGruposCiiu = this.getComunicationBean().getRemoteSystemMunicipalidad().findListaGrupoCiiu(null, null, seccionCiiu);
				Map<String, GrupoCiiu> locMapa = new LinkedHashMap<String, GrupoCiiu>();
				for(GrupoCiiu cadaGrupo : locListaGruposCiiu) {
					locMapa.put(cadaGrupo.toString(), cadaGrupo);
				}
				this.getComunicationBean().setMapaGruposCiiu(locMapa);
				Option[] op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(locMapa.keySet().toArray(), "");
				ddGrupoDefaultOptions.setOptions(op);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			this.getComunicationBean().setMapaGruposCiiu(null);
			this.armarDropDownGrupo();
		}
	}

	@Override
	protected String getNombrePagina() {
		return "Actividades CIIU";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminCodigoCiiu";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return getComunicationBean().getTablaCodigoCIIU();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMCodigoCiiu$AdminCodigoCiiu}";
	}

	@Override
	public long getSerialVersionUID() {
		return CodigoCiiu.serialVersionUID;
	}
}