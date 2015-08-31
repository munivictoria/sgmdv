/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.panels;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import muni.CommunicationExpedientesBean;
import muni.SessionBean1;
import muni.expedientes.tables.TableListaTrabajo;
import muni.expedientes.utils.FiltroListaTrabajo;

import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.Listbox;
import com.sun.rave.web.ui.model.MultipleSelectOptionsList;
import com.sun.rave.web.ui.model.Option;
import com.trascender.expedientes.recurso.persistent.Expediente;
import com.trascender.expedientes.recurso.persistent.FaseCatalogo;
import com.trascender.expedientes.recurso.persistent.NodoExpediente;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;

public class PanelListaTrabajo {

	private Label labelFases = new Label();
	private Listbox lbFases = new Listbox();
	// private MultipleSelectOptionsList lbFasesMultipleOptions = new
	// MultipleSelectOptionsList();

	private Label labelTramites = new Label();
	private Listbox lbTramites = new Listbox();
	// private MultipleSelectOptionsList lbTramitesMultipleOptions = new
	// MultipleSelectOptionsList();

	private Label labelProcedimientos = new Label();
	private Listbox lbProcedimientos = new Listbox();
	// private MultipleSelectOptionsList lbProcedimientosMultipleOptions = new
	// MultipleSelectOptionsList();

	private TableListaTrabajo tableExpedientes = new TableListaTrabajo();

	private ConverterProcedimiento converterProcedimiento = new ConverterProcedimiento();
	private ConverterFaseCatalogo converterFaseCatalogo = new ConverterFaseCatalogo();
	private ConverterTramiteCatalogo converterTramiteCatalogo = new ConverterTramiteCatalogo();

	private Checkbox chPlazoVencido = new Checkbox();

	public Label getLabelFases() {
		return labelFases;
	}

	public void setLabelFases(Label labelFases) {
		this.labelFases = labelFases;
	}

	public Listbox getLbFases() {
		return lbFases;
	}

	public void setLbFases(Listbox lbFases) {
		this.lbFases = lbFases;
	}

	public Label getLabelTramites() {
		return labelTramites;
	}

	public void setLabelTramites(Label labelTramites) {
		this.labelTramites = labelTramites;
	}

	public Listbox getLbTramites() {
		return lbTramites;
	}

	public void setLbTramites(Listbox lbTramites) {
		this.lbTramites = lbTramites;
	}

	public Label getLabelProcedimientos() {
		return labelProcedimientos;
	}

	public void setLabelProcedimientos(Label labelProcedimientos) {
		this.labelProcedimientos = labelProcedimientos;
	}

	public Listbox getLbProcedimientos() {
		return lbProcedimientos;
	}

	public void setLbProcedimientos(Listbox lbProcedimientos) {
		this.lbProcedimientos = lbProcedimientos;
	}

	public Checkbox getChPlazoVencido() {
		return chPlazoVencido;
	}

	public void setChPlazoVencido(Checkbox chPlazoVencido) {
		this.chPlazoVencido = chPlazoVencido;
	}

	public TableListaTrabajo getTableExpedientes() {
		return tableExpedientes;
	}

	public void setTableExpedientes(TableListaTrabajo tableExpedientes) {
		this.tableExpedientes = tableExpedientes;
	}

	public MultipleSelectOptionsList getLbFasesMultipleOptions() {
		return getCommunicationExpedienteBean().getLbFasesMultipleOptions();
	}

	public void setLbFasesMultipleOptions(MultipleSelectOptionsList lbFasesMultipleOptions) {
		this.getCommunicationExpedienteBean().setLbFasesMultipleOptions(lbFasesMultipleOptions);
	}

	public MultipleSelectOptionsList getLbTramitesMultipleOptions() {
		return getCommunicationExpedienteBean().getLbTramitesMultipleOptions();
	}

	public void setLbTramitesMultipleOptions(MultipleSelectOptionsList lbTramitesMultipleOptions) {
		this.getCommunicationExpedienteBean().setLbTramitesMultipleOptions(lbTramitesMultipleOptions);
	}

	public MultipleSelectOptionsList getLbProcedimientosMultipleOptions() {
		return getCommunicationExpedienteBean().getLbProcedimientosMultipleOptions();
	}

	public Option[] getProcedimientosOptions() {
		Option[] ops = getLbProcedimientosMultipleOptions().getOptions();
		Arrays.sort(ops, comparatorOrdenAlfabetico);
		
		return ops;
	}

	public Option[] getFasesOptions() {
		Option[] ops = getLbFasesMultipleOptions().getOptions();
		Arrays.sort(ops, comparatorOrdenAlfabetico);
		
		return ops;
	}

	public Option[] getTramitesOptions() {
		Option[] ops = getLbTramitesMultipleOptions().getOptions();
		Arrays.sort(ops, comparatorOrdenAlfabetico);
		
		return ops;
	}

	public void setLbProcedimientosMultipleOptions(MultipleSelectOptionsList lbProcedimientosMultipleOptions) {
		this.getCommunicationExpedienteBean().setLbProcedimientosMultipleOptions(lbProcedimientosMultipleOptions);
	}

	public ConverterProcedimiento getConverterProcedimiento() {
		return converterProcedimiento;
	}

	public void setConverterProcedimiento(ConverterProcedimiento converterProcedimiento) {
		this.converterProcedimiento = converterProcedimiento;
	}

	public ConverterFaseCatalogo getConverterFaseCatalogo() {
		return converterFaseCatalogo;
	}

	public void setConverterFaseCatalogo(ConverterFaseCatalogo converterFaseCatalogo) {
		this.converterFaseCatalogo = converterFaseCatalogo;
	}

	public ConverterTramiteCatalogo getConverterTramiteCatalogo() {
		return converterTramiteCatalogo;
	}

	public void setConverterTramiteCatalogo(ConverterTramiteCatalogo converterTramiteCatalogo) {
		this.converterTramiteCatalogo = converterTramiteCatalogo;
	}

	public FiltroListaTrabajo getFiltroListaTrabajo() {
		return getCommunicationExpedienteBean().getFiltroListaTrabajo();
	}

	public void setFiltroListaTrabajo(FiltroListaTrabajo filtroListaTrabajo) {
		this.getCommunicationExpedienteBean().setFiltroListaTrabajo(filtroListaTrabajo);
	}

	public void _init() {
		if(getFiltroListaTrabajo().listaExpediente == null || getFiltroListaTrabajo().listaExpediente.isEmpty()) {
			actualizarListaDesdeBD();
			getFiltroListaTrabajo().inicializarMapas();
			resetListBoxProcedimiento();
			resetListBoxFases();
			resetListBoxTramites();
		}
		tableExpedientes.setLastSelected(getFiltroListaTrabajo().lastSelected);
		tableExpedientes._init();
	}

	public void mostrarEstado() {
		actualizarListaDesdeBD();
		FiltroListaTrabajo fLT = getFiltroListaTrabajo();

		fLT.inicializarMapas();
		actualizarListboxPrcedimiento();
		actualizarListboxFases();
		actualizarListboxTramites();

		List<Procedimiento> listaSeleccionProcedimiento = fLT.listaSeleccionProcedimiento;
		List<FaseCatalogo> listaSeleccionFaseCatalogo = fLT.listaSeleccionFaseCatalogo;
		List<TramiteCatalogo> listaSeleccionTramiteCatalogo = fLT.listaSeleccionTramiteCatalogo;
		
		if(!listaSeleccionProcedimiento.isEmpty()) {
			fLT.actualizarMapasSegunSeleccionProcedimiento(listaSeleccionProcedimiento);
			lbProcedimientos.setSelected(listaSeleccionProcedimiento.toArray(new Procedimiento[listaSeleccionProcedimiento.size()]));
		}
		if(!listaSeleccionFaseCatalogo.isEmpty()) {
			fLT.actualizarMapasSegunSeleccionFaseCatalogo(listaSeleccionFaseCatalogo);
			lbFases.setSelected(listaSeleccionFaseCatalogo.toArray(new FaseCatalogo[listaSeleccionFaseCatalogo.size()]));
		}
		if(!listaSeleccionTramiteCatalogo.isEmpty()) {
			lbTramites.setSelected(listaSeleccionTramiteCatalogo.toArray(new TramiteCatalogo[listaSeleccionTramiteCatalogo.size()]));
		}
		
		chPlazoVencido.setValue(fLT.soloVencidos);
		tableExpedientes.setList(fLT.actualizarListaResultado());
	}

	public void guardarEstado() {
		getFiltroListaTrabajo().lastSelected = tableExpedientes.getLastSelected();
	}

	public String Modificar() {
		return null;
	}

	private void actualizarListaDesdeBD() {
		try {
			getFiltroListaTrabajo().listaExpediente.clear();
			getFiltroListaTrabajo().getListaFeriados(null);
			
			List<Expediente> locLista = new ArrayList<Expediente>();
			locLista = getCommunicationExpedienteBean().getRemoteSystemExpedientes().getListaExpedienteSoyResponsable(getSessionBean1().getLlave());
			for(NodoExpediente e : locLista) {
				e = getCommunicationExpedienteBean().getRemoteSystemExpedientes().getExpedientePorId(e.getIdNodoExpediente());
				getFiltroListaTrabajo().listaExpediente.add(e);
			}
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void actualizarListboxPrcedimiento() {
		Set<Option> setOptionProcedimientos = new HashSet<Option>();
		for(Object o : getFiltroListaTrabajo().mapProcedimiento.keySet()) {
			Procedimiento p = (Procedimiento) o;
			Option option = new Option(p, p.getNombre(), String.valueOf(getFiltroListaTrabajo().mapProcedimiento.get(p).size()));
			setOptionProcedimientos.add(option);
		}
		getLbProcedimientosMultipleOptions().setOptions(setOptionProcedimientos.toArray(new Option[setOptionProcedimientos.size()]));
		limpiarSeleccionProcedimiento();
	}

	private void limpiarSeleccionProcedimiento() {
		getFiltroListaTrabajo().listaSeleccionProcedimiento.clear();
		lbProcedimientos.setSelected(null);
	}

	public void actualizarListboxFases() {
		Set<Option> setOptionFaseCatalogo = new HashSet<Option>();
		for(Object o : getFiltroListaTrabajo().mapFaseCatalogo.keySet()) {
			FaseCatalogo fc = (FaseCatalogo) o;
			setOptionFaseCatalogo.add(new Option(fc, fc.getNombre(), String.valueOf(getFiltroListaTrabajo().mapFaseCatalogo.get(fc).size())));
		}
		getLbFasesMultipleOptions().setOptions(setOptionFaseCatalogo.toArray(new Option[setOptionFaseCatalogo.size()]));
		limpiarSeleccionFase();
	}

	private void limpiarSeleccionFase() {
		getFiltroListaTrabajo().listaSeleccionFaseCatalogo.clear();
		lbFases.setSelected(null);
	}

	public void actualizarListboxTramites() {
		Set<Option> setOptionTramiteCatalogo = new HashSet<Option>();
		for(Object o : getFiltroListaTrabajo().mapTramiteCatalogo.keySet()) {
			TramiteCatalogo tc = (TramiteCatalogo) o;
			setOptionTramiteCatalogo.add(new Option(tc, tc.getNombre(), String.valueOf(getFiltroListaTrabajo().mapTramiteCatalogo.get(tc).size())));
		}
		getLbTramitesMultipleOptions().setOptions(setOptionTramiteCatalogo.toArray(new Option[setOptionTramiteCatalogo.size()]));
		limpiarSeleccionTramite();
	}

	private void limpiarSeleccionTramite() {
		lbTramites.setSelected(null);
		getFiltroListaTrabajo().listaSeleccionTramiteCatalogo.clear();
	}

	public void actualizarSegunProcedimientosSeleccionados() {
		Object[] selected = lbProcedimientos.getSelected() != null ? (Object[]) lbProcedimientos.getSelected() : new Object[0];
		List<Procedimiento> list = new ArrayList<Procedimiento>();
		for(Option op : getLbProcedimientosMultipleOptions().getOptions()) {
			for(Object object : selected) {
				if(op.getLabel().equals(object.toString())) {
					list.add((Procedimiento) op.getValue());
				}
			}
		}
		getFiltroListaTrabajo().listaSeleccionProcedimiento = list;
		if(!list.isEmpty()) {
			getFiltroListaTrabajo().actualizarMapasSegunSeleccionProcedimiento(list);
		} else {
			this.getFiltroListaTrabajo().inicializarMapas();
			resetListBoxProcedimiento();
		}
		resetListBoxFases();
		resetListBoxTramites();
	}

	public void actualizarSegunFasesSeleccionadas() {
		Object[] selected = lbFases.getSelected() != null ? (Object[]) lbFases.getSelected() : new Object[0];
		List<FaseCatalogo> list = new ArrayList<FaseCatalogo>();
		for(Option op : getLbFasesMultipleOptions().getOptions()) {
			for(Object object : selected) {
				if(op.getLabel().equals(object.toString())) {
					list.add((FaseCatalogo) op.getValue());
				}
			}
		}
		getFiltroListaTrabajo().listaSeleccionFaseCatalogo = list;
		getFiltroListaTrabajo().actualizarMapasSegunSeleccionFaseCatalogo(list);
		resetListBoxTramites();
	}

	public void actualizarSegunTramitesSeleccionados() {
		Object[] selected = lbTramites.getSelected() != null ? (Object[]) lbTramites.getSelected() : new Object[0];
		List<TramiteCatalogo> list = new ArrayList<TramiteCatalogo>();
		for(Option op : getLbTramitesMultipleOptions().getOptions()) {
			for(Object object : selected) {
				if(op.getLabel().equals(object.toString())) {
					list.add((TramiteCatalogo) op.getValue());
				}
			}
		}
		getFiltroListaTrabajo().listaSeleccionTramiteCatalogo = list;
		tableExpedientes.setList(getFiltroListaTrabajo().actualizarListaResultado());
	}

	public void actualizarSegunVencidos() {
		getFiltroListaTrabajo().soloVencidos = chPlazoVencido.getValue() != null ? (Boolean) chPlazoVencido.getValue() : false;
		getFiltroListaTrabajo().inicializarMapas();
		resetListBoxProcedimiento();
		resetListBoxFases();
		resetListBoxTramites();
	}

	private void resetListBoxProcedimiento() {
		actualizarListboxPrcedimiento();
		limpiarSeleccionProcedimiento();
	}

	private void resetListBoxFases() {
		actualizarListboxFases();
		limpiarSeleccionFase();
	}

	private void resetListBoxTramites() {
		actualizarListboxTramites();
		limpiarSeleccionTramite();
		tableExpedientes.setList(getFiltroListaTrabajo().actualizarListaResultado());
	}

	public class ConverterProcedimiento implements Converter {

		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			Procedimiento p = null;
			for(Option option : getLbProcedimientosMultipleOptions().getOptions()) {
				if(option.getLabel().equals(value)) {
					p = (Procedimiento) option.getValue();
					break;
				}
			}
			
			return p;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			return ((Procedimiento) value).getNombre();
		}

	}

	public class ConverterFaseCatalogo implements Converter {

		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			FaseCatalogo p = null;
			for(Option option : getLbFasesMultipleOptions().getOptions()) {
				if(option.getLabel().equals(value)) {
					p = (FaseCatalogo) option.getValue();
					break;
				}
			}
			
			return p;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			return ((FaseCatalogo) value).getNombre();
		}

	}

	public class ConverterTramiteCatalogo implements Converter {

		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			TramiteCatalogo p = null;
			for(Option option : getLbTramitesMultipleOptions().getOptions()) {
				if(option.getLabel().equals(value)) {
					p = (TramiteCatalogo) option.getValue();
					break;
				}
			}
			
			return p;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			return ((TramiteCatalogo) value).getNombre();
		}

	}

	private CommunicationExpedientesBean getCommunicationExpedienteBean() {
		return (CommunicationExpedientesBean) this.getSessionBean("CommunicationExpedientesBean");
	}

	private SessionBean1 getSessionBean1() {
		return (SessionBean1) getSessionBean("SessionBean1");
	}

	public Object getSessionBean(String pBeanName) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(pBeanName);
	}

	private static Comparator<Option> comparatorOrdenAlfabetico = new Comparator<Option>() {

		@Override
		public int compare(Option o1, Option o2) {
			return o1.getLabel().compareTo(o2.getLabel());
		}
		
	};

}