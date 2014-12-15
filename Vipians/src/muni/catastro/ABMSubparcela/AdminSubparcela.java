/*
 * AdminSubparcela.java
 *
 * Created on 30 de octubre de 2006, 12:03
 * Copyright Trascender
 */

package muni.catastro.ABMSubparcela;

import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.catastro.recurso.filtros.FiltroSubParcela;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.SubParcela;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminSubparcela extends AdminPageBean {

	@Override
	protected void _init() throws Exception {

		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}

		this.habilitarBtnExportar();
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private StaticText staticText10 = new StaticText();

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText staticText10) {
		this.staticText10 = staticText10;
	}

	private StaticText staticText6 = new StaticText();

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText st) {
		this.staticText6 = st;
	}

	private StaticText staticText8 = new StaticText();

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText st) {
		this.staticText8 = st;
	}

	private ObjectListDataProvider ldpSubparcelas = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpSubparcelas() {
		return ldpSubparcelas;
	}

	public void setLdpSubparcelas(ObjectListDataProvider ldpSubparcelas) {
		this.ldpSubparcelas = ldpSubparcelas;
	}

	private TableColumn tableColumn3 = new TableColumn();

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tc) {
		this.tableColumn3 = tc;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private TableColumn tableColumn9 = new TableColumn();

	public TableColumn getTableColumn9() {
		return tableColumn9;
	}

	public void setTableColumn9(TableColumn tc) {
		this.tableColumn9 = tc;
	}

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private StaticText staticText5 = new StaticText();

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText st) {
		this.staticText5 = st;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private Button btnImprimirReporte = new Button();

	public Button getBtnImprimirReporte() {
		return btnImprimirReporte;
	}

	public void setBtnImprimirReporte(Button b) {
		this.btnImprimirReporte = b;
	}

	private TableSelectPhaseListener tablePhaseListener;

	@Override
	public TableSelectPhaseListener getTablePhaseListener() {
		return tablePhaseListener;
	}

	public AdminSubparcela() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new String());// 0 tipoSeleccion

		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// Parcela parcela = this.getSessionBean1().getParcelaSeleccionada();
		// Persona propietario = this.getSessionBean1().getPersonaSeleccionada();

		// if (nroParcela != null && nroParcela != "")
		// parcela.setNroParcela(Conversor.getIntegerDeString(nroParcela.toString()));
		// else parcela.setNroParcela(null);

		// aca deberia meter la parcela al filtro
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		/*
		 * if (this.getRequestBean1().getTipoSeleccion() != null) { // en obligOSP, si selecciono Parcela o subparcela String seleccion =
		 * this.getRequestBean1().getTipoSeleccion(); this.getElementoPila().getObjetos().set(0, seleccion); }
		 * 
		 * int ind = 0; Parcela parcela = this.getSessionBean1().getParcelaSeleccionada(); Persona propietario =
		 * this.getSessionBean1().getPersonaSeleccionada();
		 */
		FiltroSubParcela locFiltro = getFiltro();
		if(locFiltro.getParcelaPadre() != null) {
			this.getTfParcelaSeleccionada().setText(locFiltro.getParcelaPadre().toString());
		}
		if(locFiltro.getTitular() != null) {
			this.getTfPersonaSeleccionada().setText(locFiltro.getTitular().toString());
		}
	}

	@Override
	public String btnLimpiarParcela_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.getTfParcelaSeleccionada().setText("");
			FiltroSubParcela locFiltro = getFiltro();
			locFiltro.setParcelaPadre(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	public String btnLimpiarPersona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.getTfPersonaSeleccionada().setText("");
			FiltroSubParcela locFiltro = getFiltro();
			locFiltro.setTitular(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
		return this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().findListaSubParcela((FiltroSubParcela) pFiltro);
	}

	@Override
	protected void refrescarTabla() throws Exception {
		FiltroSubParcela locFiltro = this.getFiltro();
		this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
		locFiltro = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().findListaSubParcela(locFiltro);
		this.setListaDelCommunication(locFiltro.getListaResultados());
		this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		this.getPaginatedTable().setFiltro(locFiltro);
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpSubparcelas();
	}

	@Override
	protected ArrayList getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaSubparcelas();
	}

	private TableSelectPhaseListener getTableSelectPhaseListener() {
		return this.getComunicationCatastroBean().getTablePhaseListenerParcelas();
	}

	// M?todos de la l?gica de navegaci?n
	// Mines agregaste los siguientes metodos para la zonificacion:

	public String btnImprimirReporte_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;

			try {

				rk = this.getSeleccionado();

				if(rk != null) {
					int index = this.getNroFila(rk.toString());

					Object obj = this.getObjectListDataProvider().getObjects()[index];
					this.getSessionBean1().setObjetoImpresion(obj);

					// this.setRowKeySeleccionado(this.getSeleccionado());
				}
			} catch(Exception e) {
				log("AdminSubparcela" + "_ImprimirError: ", e);
				error("Administraci\363n de Subparcelas" + " - Imprimir: " + e.getMessage());
			}
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// this.guardarOrdenamiento();
			// Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			// this.getElementoPila().setPosicionGlobal(pos.longValue());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnConsultar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		String tipoSeleccion = (String) this.obtenerObjetoDelElementoPila(0, String.class);

		if(ultimo) {
			RowKey rk = null;
			try {

				rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					Parcela parcela = (Parcela) obj;
					this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
					parcela = (Parcela) this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(parcela.getIdParcela());

					this.getRequestBean1().setObjetoABM(parcela);
					this.setRowKeySeleccionado(this.getSeleccionado());
				}

			} catch(Exception ex) {
				log("AdminSubparcela" + "_ModificarError:", ex);
				error("Administraci\363n de Subparcelas" + " - Modificar: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			this.guardarOrdenamiento();
			Long pos = this.getPosicionEnTabla(this.getRowKeySeleccionado());
			this.getElementoPila().setPosicionGlobal(pos.longValue());

			if(rk != null) {
				retorno = "ConsultarParcela";
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getComunicationCatastroBean().setListaSubparcelas((ArrayList) lista);
	}

	@Override
	protected void limpiarObjetosUsados() {
		// this.getSessionBean1().setPersonaSeleccionada(null);
		// this.getSessionBean1().setParcelaSeleccionada(null);
		FiltroSubParcela locFiltro = getFiltro();
		locFiltro.setParcelaPadre(null);
		locFiltro.setTitular(null);

		this.getTfParcelaSeleccionada().setText("");
		this.getTfPersonaSeleccionada().setText("");
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		return pObject;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Subparcelas";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminSubparcela";
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getComunicationCatastroBean().getTablaSubparcela();
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		FiltroSubParcela locFiltro = getFiltro();
		if(pObject instanceof Persona) {
			Persona propietario = (Persona) pObject;
			locFiltro.setTitular(propietario);

			// this.getSessionBean1().setPersonaSeleccionada(propietario);
		} else if(pObject instanceof Parcela) {
			Parcela parcela = (Parcela) pObject;
			locFiltro.setParcelaPadre(parcela);

			// this.getSessionBean1().setParcelaSeleccionada(parcela);
		}
	}

	@Override
	public String getNombreBean() {
		return "#{catastro$ABMSubparcela$AdminSubparcela}";
	}

	@Override
	public long getSerialVersionUID() {
		return SubParcela.serialVersionUID;
	}
}