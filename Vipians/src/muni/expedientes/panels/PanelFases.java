/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.panels;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.el.MethodExpression;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import muni.expedientes.tables.TableTramite;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;
import org.ajax4jsf.ajax.html.HtmlAjaxOutputPanel;

import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.expedientes.enums.Estado;
import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.expedientes.recurso.persistent.Expediente;
import com.trascender.expedientes.recurso.persistent.Expediente.AccionFase;
import com.trascender.expedientes.recurso.persistent.Fase;
import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.expedientes.recurso.persistent.NodoExpediente;
import com.trascender.expedientes.recurso.persistent.Responsable;
import com.trascender.expedientes.recurso.persistent.Tramite;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.Usuario;

public class PanelFases {

	private TableTramite tableTramite = new TableTramite();
	private HtmlAjaxCommandButton btnAvanzarFase = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnRetrocederFase = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnCerrarExpediente = new HtmlAjaxCommandButton();
	private HtmlAjaxCommandButton btnCancelarAvanceFase = new HtmlAjaxCommandButton();
	private HtmlAjaxOutputPanel panelGridFase = new HtmlAjaxOutputPanel();

	private Label lbFasesEspeciales = new Label();
	private DropDown ddFasesEspeciales = new DropDown();
	private SingleSelectOptionsList ddFasesEspecialesOptions = new SingleSelectOptionsList();
	private HtmlAjaxCommandButton btnIrAFaseEspecial = new HtmlAjaxCommandButton();
	private StaticText staticText1 = new StaticText();

	private PanelPlazoExpediente panelPlazoExpediente = new PanelPlazoExpediente();
	private PanelResponsableExpediente panelResponsable = new PanelResponsableExpediente();
	private Label lblResponsable = new Label();

	public muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) getSessionBean("SessionBean1");
	}

	public Object getSessionBean(String pBeanName) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(pBeanName);
	}

	public muni.CommunicationExpedientesBean getCommunicationExpedientesBean() {
		return (muni.CommunicationExpedientesBean) getSessionBean("CommunicationExpedientesBean");
	}

	public void _init() {
		tableTramite._init();

		if(this.getCommunicationExpedientesBean().getMapaFasesEspeciales() != null) {
			Set<String> lista = this.getCommunicationExpedientesBean().getMapaFasesEspeciales().keySet();
			Option[] opFasesEspeciales = new Option[lista.size()];
			int i = 0;
			for(String cadaValor : lista) {
				opFasesEspeciales[i++] = new Option(cadaValor, cadaValor);
			}
			this.getDdFasesEspecialesOptions().setOptions(opFasesEspeciales);
		}
	}

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText staticText1) {
		this.staticText1 = staticText1;
	}

	public Label getLbFasesEspeciales() {
		return lbFasesEspeciales;
	}

	public void setLbFasesEspeciales(Label lbFasesEspeciales) {
		this.lbFasesEspeciales = lbFasesEspeciales;
	}

	public HtmlAjaxCommandButton getBtnCancelarAvanceFase() {
		return btnCancelarAvanceFase;
	}

	public void setBtnCancelarAvanceFase(HtmlAjaxCommandButton btnCancelarAvanceFase) {
		this.btnCancelarAvanceFase = btnCancelarAvanceFase;
	}

	public DropDown getDdFasesEspeciales() {
		return ddFasesEspeciales;
	}

	public void setDdFasesEspeciales(DropDown ddFasesEspeciales) {
		this.ddFasesEspeciales = ddFasesEspeciales;
	}

	public SingleSelectOptionsList getDdFasesEspecialesOptions() {
		return ddFasesEspecialesOptions;
	}

	public void setDdFasesEspecialesOptions(SingleSelectOptionsList ddFasesEspecialesOptions) {
		this.ddFasesEspecialesOptions = ddFasesEspecialesOptions;
	}

	public HtmlAjaxCommandButton getBtnIrAFaseEspecial() {
		return btnIrAFaseEspecial;
	}

	public void setBtnIrAFaseEspecial(HtmlAjaxCommandButton btnIrAFaseEspecial) {
		this.btnIrAFaseEspecial = btnIrAFaseEspecial;
	}

	public HtmlAjaxCommandButton getBtnAvanzarFase() {
		return btnAvanzarFase;
	}

	public void setBtnAvanzarFase(HtmlAjaxCommandButton btnAvanzarFase) {
		this.btnAvanzarFase = btnAvanzarFase;
	}

	public HtmlAjaxCommandButton getBtnRetrocederFase() {
		return btnRetrocederFase;
	}

	public void setBtnRetrocederFase(HtmlAjaxCommandButton btnRetrocederFase) {
		this.btnRetrocederFase = btnRetrocederFase;
	}

	public HtmlAjaxCommandButton getBtnCerrarExpediente() {
		return btnCerrarExpediente;
	}

	public void setBtnCerrarExpediente(HtmlAjaxCommandButton btnCerrarExpediente) {
		this.btnCerrarExpediente = btnCerrarExpediente;
	}

	public TableTramite getTableTramite() {
		return tableTramite;
	}

	public void setTableTramite(TableTramite tableTramite) {
		this.tableTramite = tableTramite;
	}

	public HtmlAjaxOutputPanel getPanelGridFase() {
		return panelGridFase;
	}

	public void setPanelGridFase(HtmlAjaxOutputPanel panelGridFase) {
		this.panelGridFase = panelGridFase;
	}

	public PanelPlazoExpediente getPanelPlazoExpediente() {
		return panelPlazoExpediente;
	}

	public void setPanelPlazoExpediente(PanelPlazoExpediente panelPlazoExpediente) {
		this.panelPlazoExpediente = panelPlazoExpediente;
	}

	public PanelResponsableExpediente getPanelResponsable() {
		return panelResponsable;
	}

	public void setPanelResponsable(PanelResponsableExpediente panelResponsable) {
		this.panelResponsable = panelResponsable;
	}

	public Label getLblResponsable() {
		return lblResponsable;
	}

	public void setLblResponsable(Label lblResponsable) {
		this.lblResponsable = lblResponsable;
	}

	public PanelFases(Expediente pExpediente) {
		getFaseActiva(pExpediente);
		armarPanel(pExpediente);
	}

	public PanelFases() {
	}

	public void mostrarDatos(Expediente pExpediente) {
		if(!pExpediente.getListaNodosExpedientes().isEmpty()) {
			lblResponsable.setVisible(false);
			getFaseActiva(pExpediente);
			armarPanel(pExpediente);
			Fase locFaseActual = pExpediente.getFaseActual();
			Responsable locResponsable = locFaseActual.getNodoProcedimiento().getResponsable();
			if(locFaseActual != null) {
				panelPlazoExpediente.mostrarDatos(locFaseActual.getPlazo());

				try {
					FaseProcedimiento faseProcedimiento = (FaseProcedimiento) locFaseActual.getNodoProcedimiento();
					if(faseProcedimiento.getListaFasesEspeciales().size() > 0) {
						this.getCommunicationExpedientesBean().armarMapaFasesEspeciales(faseProcedimiento);
						Option[] opFasesEspeciales = new Option[faseProcedimiento.getListaFasesEspeciales().size() + 1];
						int i = 0;
						opFasesEspeciales[i++] = new Option(null, "");
						for(FaseProcedimiento cadaFase : faseProcedimiento.getListaFasesEspeciales()) {
							opFasesEspeciales[i++] = new Option(cadaFase.getFaseCatalogo(), cadaFase.getFaseCatalogo().getNombre());
						}
						this.getDdFasesEspecialesOptions().setOptions(opFasesEspeciales);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(locFaseActual != null && locResponsable != null) {
				panelResponsable = new PanelResponsableExpediente(locResponsable);
				lblResponsable.setVisible(true);
			}
		} else {
			tableTramite.setList(new ArrayList<Tramite>());
			deshablitarBotones();
		}
	}

	private void getFaseActiva(Expediente pE) {
		if(!pE.getListaNodosExpedientes().isEmpty()) {
			Fase activa = (Fase) pE.getListaNodosExpedientes().get(0);
			for(NodoExpediente fase : pE.getListaNodosExpedientes()) {
				Fase f = (Fase) fase;
				if(f.getActiva()) {
					activa = f;
					break;
				}
			}
			pE.setFaseActual(activa);
			activa.setActiva(true);
		}
	}

	private void armarPanel(Expediente pE) {
		this.panelGridFase.getChildren().clear();
		// getFaseActiva(pE);
		int cont = 0;
		// int pos = 0;
		List<NodoExpediente> listaTramites = new ArrayList<NodoExpediente>();
		boolean cerrado = false;
		for(NodoExpediente f : pE.getListaFasesOrdenadasSegunPermisos()) {
				Fase fase = (Fase) f;
				HtmlOutputLabel ol = new HtmlOutputLabel();
				HtmlOutputLabel ol2 = new HtmlOutputLabel();
				ol.setId(getIdLink(fase));
				ol.setStyle("color: rgb(15, 86, 238)");
				ol.setValue(fase.getPlantilla());
				ol2.setValue(" >> ");
				if(pE.getEstado().equals(Estado.CERRADO) && cerrado) {
					ol.setStyle("text-decoration:line-through;");
				}
				if(fase.equals(pE.getFaseActualSegunPermisos())) {
					// pos = cont;
					if(fase.getFaseAVolver() != null) {
						ol.setStyle("font-weight:bold; color:green");
					} else {
						ol.setStyle("font-weight:bold;");
					}
					cerrado = true;
			
					listaTramites = fase.getListaNodosExpedientes();
				}
				this.panelGridFase.getChildren().add(ol);
				if(cont < pE.getListaFasesOrdenada().size() - 1) {
					this.panelGridFase.getChildren().add(ol2);
				}
				cont++;
		}

		if(pE.getIdNodoExpediente() == -1){
			for (int i=0; i < listaTramites.size(); i++) {
				   if(listaTramites.get(i).getNodoProcedimiento().getEstado().equals(EstadoPlantilla.BAJA)){
					   listaTramites.remove(listaTramites.get(i));
				   }
		        }	
		}
		
		this.tableTramite.setList(listaTramites);

		int size = pE.getListaNodosExpedientes().size();
		if(size <= 0 || pE.getEstado().equals(Estado.CERRADO))
			deshablitarBotones();
	}

	private void deshablitarBotones() {
		lbFasesEspeciales.setRendered(false);
		ddFasesEspeciales.setRendered(false);
		btnIrAFaseEspecial.setRendered(false);
		staticText1.setRendered(false);
		btnAvanzarFase.setRendered(false);
		btnCerrarExpediente.setRendered(false);
		btnRetrocederFase.setRendered(false);
	}

	@SuppressWarnings("unused")
	private Fase faseEspecialAFase(Fase pFaseActivar, Fase FaseEspecialDesactivar) {
		FaseEspecialDesactivar.setActiva(false);
		FaseEspecialDesactivar.setEstado(com.trascender.expedientes.recurso.persistent.Fase.Estado.CERRADO);
		if(pFaseActivar != null) {
			pFaseActivar.setActiva(true);
			pFaseActivar.setEstado(com.trascender.expedientes.recurso.persistent.Fase.Estado.ABIERTO);
		}

		return pFaseActivar;
	}

	public void avanzarFase(Expediente pE, AccionFase pAccion, Usuario pUsuario, String pComentario) throws Exception {
		Fase activaActual = (Fase) pE.getListaFasesOrdenada().get(pE.getIndexActiva());
		Fase activa = null;
		if(activaActual.getFaseAVolver() != null) {
			int index = pE.getIndexActiva();
			// Fase pFaseActiva = (Fase) pE.getListaNodosExpedientes().get(index);

			// activa = faseEspecialAFase(pFaseActiva, activaActual);
			activa = pE.avanzarFase(pAccion, pUsuario, pComentario);
			pE.getListaNodosExpedientes().remove(index);
			for(int i = index; i < pE.getListaFasesOrdenada().size(); i++) {
				pE.getListaNodosExpedientes().get(i).setOrden(i);
			}
			this.armarPanel(pE);
		} else {
			activa = pE.avanzarFase(pAccion, pUsuario, pComentario);
			HtmlOutputLabel ol1 = (HtmlOutputLabel) this.panelGridFase.findComponent(getIdLink(activaActual));
			ol1.setStyle("font-weight:normal; color: rgb(15, 86, 238)");
		}

		HtmlOutputLabel ol2 = (HtmlOutputLabel) this.panelGridFase.findComponent(getIdLink(activa));
		ol2.setStyle("font-weight:bold");

		// int indexOf = 0;
		for(int i = 0; i < pE.getListaNodosExpedientes().size(); i++) {
			if(pE.getListaNodosExpedientes().get(i).getPlantilla().equals(activa.getPlantilla())) {
				// indexOf = i;
				break;
			}
		}
		// hablitarBotones(indexOf, pE.getListaNodosExpedientes().size());
		getTableTramite().setList(activa.getListaNodosExpedientes());
	}

	public void cerrarExpediente(Expediente pE) throws Exception {
		getFaseActiva(pE);
		this.armarPanel(pE);
	}

	public void retrocederFase(Expediente pE, AccionFase pAccion, Usuario pUsuario, String pComentario) throws Exception {
		Fase activaActual = (Fase) pE.getListaFasesOrdenada().get(pE.getIndexActiva());
		Fase activa = pE.retrocederFase(pAccion, pUsuario, pComentario);
		HtmlOutputLabel ol1 = (HtmlOutputLabel) this.panelGridFase.findComponent(getIdLink(activaActual));
		HtmlOutputLabel ol2 = (HtmlOutputLabel) this.panelGridFase.findComponent(getIdLink(activa));
		ol2.setStyle("font-weight:bold");
		ol1.setStyle("font-weight:normal; color: rgb(15, 86, 238)");
		// int indexOf = 0;
		for(int i = 0; i < pE.getListaNodosExpedientes().size(); i++) {
			if(pE.getListaNodosExpedientes().get(i).getPlantilla().equals(activa.getPlantilla())) {
				// indexOf = i;
				break;
			}
		}
		// hablitarBotones(indexOf, pE.getListaNodosExpedientes().size());
		getTableTramite().setList(activa.getListaNodosExpedientes());
	}

	public void irAFaseEspecial(Expediente pE, Usuario pUsuario, String pComentario) throws Exception {
		String stringFaseEspecial = this.getDdFasesEspeciales().getSelected().toString();
		FaseProcedimiento faseEspecial = this.getCommunicationExpedientesBean().getMapaFasesEspeciales().get(stringFaseEspecial);
		faseEspecial = getCommunicationExpedientesBean().getRemoteSystemProcedimientos().getFaseProcedimientoPorId(faseEspecial.getIdNodoProcedimiento());

		if(faseEspecial != null) {
			Fase activaActual = (Fase) pE.getListaFasesOrdenada().get(pE.getIndexActiva());
			Fase locFase = new Fase(faseEspecial, pE);
			locFase.setFaseAVolver(activaActual);

			for(int i = pE.getIndexActiva(); i < pE.getListaFasesOrdenada().size(); i++) {
				pE.getListaFasesOrdenada().get(i).setOrden(i + 1);
			}
			locFase.setOrden(pE.getIndexActiva());
			pE.getListaNodosExpedientes().add(locFase);
			getFaseActiva(pE);
			Fase activa = pE.retrocederFase(AccionFase.AVANCE_ESPECIAL, pUsuario, pComentario);
			pE.setFaseActivaSegunPermisos(pUsuario);
			this.armarPanel(pE);
			HtmlOutputLabel ol1 = (HtmlOutputLabel) this.panelGridFase.findComponent(getIdLink(activaActual));
			HtmlOutputLabel ol2 = (HtmlOutputLabel) this.panelGridFase.findComponent(getIdLink(activa));
			ol2.setStyle("font-weight:bold; color:green");
			ol1.setStyle("font-weight:normal; color: rgb(15, 86, 238)");
			int indexOf = 0;
			for(int i = 0; i < pE.getListaNodosExpedientes().size(); i++) {
				if(pE.getListaNodosExpedientes().get(i).getPlantilla().equals(activa.getPlantilla())) {
					indexOf = i;
					break;
				}
			}
			// hablitarBotones(indexOf, pE.getListaNodosExpedientes().size());
			getTableTramite().setList(activa.getListaNodosExpedientes());
		}
	}

	private String getIdLink(Fase pfase) {
		return "id_" + pfase.getNodoProcedimiento().getIdNodoProcedimiento();
	}

	public void deshabilitarAccionesFases(Expediente pExpediente) {
		renderizar(pExpediente);
		deshabilitar(pExpediente);
	}

	private void deshabilitar(Expediente pExpediente) {
		boolean bandera = isFaseActualNoVencida(pExpediente);
		btnIrAFaseEspecial.setDisabled(bandera);
		btnCancelarAvanceFase.setDisabled(bandera);
		btnRetrocederFase.setDisabled(bandera);
		btnAvanzarFase.setDisabled(bandera);
		btnCerrarExpediente.setDisabled(bandera);
		ddFasesEspeciales.setDisabled(bandera);
	}

	private void renderizar(Expediente pExpediente) {
		boolean bandera = isPuedeAvanzarFaseEspecial(pExpediente);
		lbFasesEspeciales.setRendered(bandera);
		btnIrAFaseEspecial.setRendered(bandera);
		staticText1.setRendered(bandera);
		ddFasesEspeciales.setRendered(bandera);

		bandera = isPuedeCancelarAvance(pExpediente);
		btnCancelarAvanceFase.setRendered(bandera);

		bandera = isPuedeRetrocederFase(pExpediente);
		btnRetrocederFase.setRendered(bandera);

		bandera = isPuedeAvanzarFase(pExpediente);
		btnAvanzarFase.setRendered(bandera);

		bandera = isPuedeCerrarExpediente(pExpediente);
		btnCerrarExpediente.setRendered(bandera);
	}

	private boolean isPuedeRetrocederFase(Expediente pExpediente) {
		if(pExpediente.getEstado() == Estado.CERRADO)
			return false;
		if(pExpediente.getIndexActiva() <= 0)
			return false;
		Fase faseActual = pExpediente.getFaseActual();
		if(faseActual.getFaseAVolver() != null)
			return false;

		return faseActual.esResponsableDirectoOPadre(this.getSessionBean1().getUsuario());
	}

	private boolean isPuedeAvanzarFase(Expediente pExpediente) {
		if(pExpediente.getEstado() == Estado.CERRADO)
			return false;
		if(pExpediente.getIndexActiva() >= pExpediente.getListaNodosExpedientes().size() - 1)
			return false;
		NodoExpediente nodoFaseActiva = pExpediente.getListaFasesOrdenada().get(pExpediente.getIndexActiva());
		boolean retorno = nodoFaseActiva.esResponsableDirectoOPadre(this.getSessionBean1().getUsuario());

		return retorno;
	}

	/**
	 * 
	 * @param pExpediente
	 * @return false si no hay fases especiales a las cuales avanzar, o si no tenemos responsabilidad sobre la fase actual.
	 */
	private boolean isPuedeAvanzarFaseEspecial(Expediente pExpediente) {
		if(pExpediente.getEstado() == Estado.CERRADO)
			return false;
		// if(pExpediente.getIndexActiva() <= 0)
		// return false;
		Fase faseActual = pExpediente.getFaseActual();
		if(faseActual == null)
			return false;
		FaseProcedimiento faseProActual = (FaseProcedimiento) faseActual.getNodoProcedimiento();
		if(faseProActual.getListaFasesEspeciales().isEmpty())
			return false;

		return faseActual.esResponsableDirectoOPadre(this.getSessionBean1().getUsuario());
	}

	private boolean isFaseActualNoVencida(Expediente pExpediente) {
		if(pExpediente.getEstado() == Estado.CERRADO)
			return false;
		// Si es un expediente que se esta creando.
		if(pExpediente.getNodoProcedimiento() == null)
			return false;
		NodoExpediente nodoFaseActiva = pExpediente.getListaFasesOrdenada().get(pExpediente.getIndexActiva());
		// TODO Ver dias vencidos.

		return nodoFaseActiva.isVencido(new ArrayList<DiaFeriado>());
	}

	/**
	 * Si la fase actual aun no tiene Tramites trabajados, se puede cancelar el avance de la fase.
	 * 
	 * @return
	 */
	private boolean isPuedeCancelarAvance(Expediente pExpediente) {
		if(pExpediente.getEstado() == Estado.CERRADO)
			return false;
		// Si es la primera fase, no se puede.
		if(pExpediente.isEnPrimerFase()) {
			return false;
		}
		for(NodoExpediente cadaNodoTramite : pExpediente.getFaseActualSegunPermisos().getListaNodosExpedientes()) {
			Tramite cadaTramite = (Tramite) cadaNodoTramite;
			if(cadaTramite.getEstadoTramite() != null) {
				return false;
			}
		}
		Fase faseQueEnvio = (Fase) pExpediente.getListaFasesOrdenada().get(pExpediente.getIndexActiva() - 1);

		return faseQueEnvio.esResponsableDirectoOPadre(this.getSessionBean1().getUsuario());
	}

	private boolean isPuedeCerrarExpediente(Expediente pExpediente) {
		if(pExpediente.getEstado() == Estado.CERRADO)
			return false;
//		if(pExpediente.getIndexActiva() <= 0)
//			return false;
		Fase faseActual = pExpediente.getFaseActualSegunPermisos();
		if(faseActual == null)
			return false;
		if(faseActual.getFaseAVolver() != null)
			return false;

		return faseActual.esResponsableDirectoOPadre(this.getSessionBean1().getUsuario());
	}

	public void actualizarTramite(Expediente pE, TableTramite.WTramite wt) {
		// Fase activa = pE.getFaseActiva();
		Fase activa = pE.getFaseActual();
		activa.getListaNodosExpedientes().set(wt.index, wt.tramite);
	}

	FacesContext context = FacesContext.getCurrentInstance();
	MethodExpression methodExpression = context.getApplication().getExpressionFactory()
			.createMethodExpression(context.getELContext(), "#{expedientes$ABMExpediente$ABMExpediente.getFase}", null, new Class[] {ActionEvent.class});

	public class Listener implements ActionListener {

		@Override
		public void processAction(ActionEvent e) throws AbortProcessingException {
			System.out.println("Hello!!!");
		}

	}

}