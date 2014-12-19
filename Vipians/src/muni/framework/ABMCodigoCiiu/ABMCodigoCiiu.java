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
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.recurso.persistent.CodigoCiiu;
import com.trascender.framework.recurso.persistent.GrupoCiiu;
import com.trascender.framework.recurso.persistent.SeccionCiiu;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMCodigoCiiu extends ABMPageBean {

	protected Label lblCodigo = new Label();
	protected Label lblDescripcion = new Label();
	protected TextField tfCodigo = new TextField();
	protected TextField tfDescripcion = new TextField();
	protected ObjectListDataProvider ldpCodigosCiiu = new ObjectListDataProvider();
	
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
	
	public DropDown getDdGrupo() {
		return ddGrupo;
	}

	public void setDdGrupo(DropDown ddGrupo) {
		this.ddGrupo = ddGrupo;
	}

	public DropDown getDdSeccion() {
		return ddSeccion;
	}

	public void setDdSeccion(DropDown ddSeccion) {
		this.ddSeccion = ddSeccion;
	}

	public SingleSelectOptionsList getDdGrupoDefaultOptions() {
		return ddGrupoDefaultOptions;
	}

	public void setDdGrupoDefaultOptions(
			SingleSelectOptionsList ddGrupoDefaultOptions) {
		this.ddGrupoDefaultOptions = ddGrupoDefaultOptions;
	}

	public SingleSelectOptionsList getDdSeccionDefaultOptions() {
		return ddSeccionDefaultOptions;
	}

	public void setDdSeccionDefaultOptions(
			SingleSelectOptionsList ddSeccionDefaultOptions) {
		this.ddSeccionDefaultOptions = ddSeccionDefaultOptions;
	}

	public ObjectListDataProvider getLdpCodigosCiiu() {
		return ldpCodigosCiiu;
	}

	public void setLdpCodigosCiiu(ObjectListDataProvider ldpCodigosCiiu) {
		this.ldpCodigosCiiu = ldpCodigosCiiu;
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

	@Override
	protected void guardarEstadoObjetosUsados() {
		CodigoCiiu codigoCiiu = (CodigoCiiu) this.obtenerObjetoDelElementoPila(0, CodigoCiiu.class);

		codigoCiiu.setCodigo(this.getTextFieldValue(getTfCodigo()));
		codigoCiiu.setDescripcion(this.getTextFieldValue(getTfDescripcion()));
		
		codigoCiiu.setGrupoCiiu(this.getDDObjectValue(ddGrupo, getComunicationBean().getMapaGruposCiiu()));

		this.getElementoPila().getObjetos().set(0, codigoCiiu);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		CodigoCiiu codigoCiiu = (CodigoCiiu) this.obtenerObjetoDelElementoPila(ind++, CodigoCiiu.class);

		this.getTfCodigo().setText(codigoCiiu.getCodigo());
		this.getTfDescripcion().setText(codigoCiiu.getDescripcion());
		
		if(codigoCiiu.getGrupoCiiu() != null) {
			this.getDdSeccion().setSelected(codigoCiiu.getGrupoCiiu().getSeccionCiiu().toString());
			this.getDdSeccionDefaultOptions().setSelectedValue(codigoCiiu.getGrupoCiiu().getSeccionCiiu());
			this.armarMapaGrupos(codigoCiiu.getGrupoCiiu().getSeccionCiiu());
			this.armarDropDownGrupo();
			this.getDdGrupo().setSelected(codigoCiiu.getGrupoCiiu().toString());
			this.getDdGrupoDefaultOptions().setSelectedValue(codigoCiiu.getGrupoCiiu());
		}
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMCodigoCiiu";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new CodigoCiiu());

		return ep;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof CodigoCiiu) {
			if(pObject != null) {
				CodigoCiiu codigoCiiu = (CodigoCiiu) pObject;
				this.getElementoPila().getObjetos().set(0, codigoCiiu);
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		CodigoCiiu codigoCiiu = null;
		try {
			codigoCiiu = (CodigoCiiu) pObject;
		} catch(Exception e) {
			log(this.getCasoNavegacion() + "_Guardar Error:", e);
			e.printStackTrace();
		}
		this.getElementoPila().getObjetos().set(0, codigoCiiu);
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMCodigoCiiu$ABMCodigoCiiu}";
	}

	@Override
	public long getSerialVersionUID() {
		return CodigoCiiu.serialVersionUID;
	}
	
	public void valueChangeEvent(ValueChangeEvent event) {
		String toStringSeccion = (String) this.getDdSeccion().getSelected();
		SeccionCiiu seccionCiiu = this.getComunicationBean().getMapaSeccionesCiiu().get(toStringSeccion);
		if(seccionCiiu != null) {
			armarMapaGrupos(seccionCiiu);
		} else {
			this.getComunicationBean().setMapaGruposCiiu(null);
			this.armarDropDownGrupo();
		}
	}
	
	private void armarMapaGrupos(SeccionCiiu seccionCiiu) {
		try {
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
	}
	
	private void armarDropDownGrupo() {
		if(this.getComunicationBean().getMapaGruposCiiu() != null && !this.getComunicationBean().getMapaGruposCiiu().isEmpty()) {
			Option[] op2 = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsObjects(this.getComunicationBean().getMapaGruposCiiu().keySet().toArray(), "");
			ddGrupoDefaultOptions.setOptions(op2);
		} else {
			ddGrupoDefaultOptions.setOptions(new Option[] {});
		}
	}
}