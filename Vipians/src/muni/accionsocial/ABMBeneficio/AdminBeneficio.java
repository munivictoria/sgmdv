/*
 * AdminBeneficio.java
 *
 * Created on 1 de noviembre 
 * Copyright Trascender
 */

package muni.accionsocial.ABMBeneficio;

import java.util.ArrayList;
import java.util.List;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.accionSocial.recurso.persistent.Beneficio;
import com.trascender.accionSocial.recurso.persistent.Beneficio.TipoBeneficio;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.Constantes;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class AdminBeneficio extends AdminPageBean {
	
	protected final String lnkAgregar = "AgregarBeneficio";
	protected final String lnkModificar = "ModificarBeneficio";
	protected final String lnkEliminar = "EliminarBeneficio";

	protected StaticText stNombre = new StaticText();
	protected StaticText stTipoBeneficio = new StaticText();

	protected ObjectListDataProvider ldpBeneficio = new ObjectListDataProvider();

	protected TableColumn tcNombre = new TableColumn();
	protected TableColumn tcTipoBeneficio = new TableColumn();

	protected TextField tfNombre = new TextField();

	protected Label lblTipoBeneficio = new Label();
	protected Label lblNombre = new Label();

	protected DropDown ddTipoBeneficio = new DropDown();

	protected SingleSelectOptionsList ddTipoBeneficioDefaultOptions = new SingleSelectOptionsList();

	// ***GETTERS & SETTERS***

	public TableColumn getTcTipoBeneficio() {
		return tcTipoBeneficio;
	}

	public void setTcTipoBeneficio(TableColumn tcTipoBeneficio) {
		this.tcTipoBeneficio = tcTipoBeneficio;
	}

	public StaticText getStTipoBeneficio() {
		return stTipoBeneficio;
	}

	public void setStTipoBeneficio(StaticText stTipoBeneficio) {
		this.stTipoBeneficio = stTipoBeneficio;
	}

	public StaticText getStNombre() {
		return stNombre;
	}

	public void setStNombre(StaticText stNombre) {
		this.stNombre = stNombre;
	}

	public ObjectListDataProvider getLdpBeneficio() {
		return ldpBeneficio;
	}

	public void setLdpBeneficio(ObjectListDataProvider oldp) {
		this.ldpBeneficio = oldp;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	public DropDown getDdTipoBeneficio() {
		return ddTipoBeneficio;
	}

	public void setDdTipoBeneficio(DropDown dd) {
		this.ddTipoBeneficio = dd;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	public SingleSelectOptionsList getDdTipoBeneficioDefaultOptions() {
		return ddTipoBeneficioDefaultOptions;
	}

	public void setDdTipoBeneficioDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddTipoBeneficioDefaultOptions = ssol;
	}

	public Label getLblTipoBeneficio() {
		return lblTipoBeneficio;
	}

	public void setLblTipoBeneficio(Label lblTipoBeneficio) {
		this.lblTipoBeneficio = lblTipoBeneficio;
	}

	public TableColumn getTcNombre() {
		return tcNombre;
	}

	public void setTcNombre(TableColumn tcNombre) {
		this.tcNombre = tcNombre;
	}

	// ***METHODS***

	public AdminBeneficio() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new Beneficio());

		return ep;
	}

	@Override
	protected void _init() throws Exception {

		Option[] op = null;
		// Tipo de Beneficio
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(TipoBeneficio.values(), "cap");
		ddTipoBeneficioDefaultOptions.setOptions(op);

	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo.
		Beneficio beneficio = (Beneficio) this.obtenerObjetoDelElementoPila(0, Beneficio.class);

		TipoBeneficio tipoBeneficio = null;

		Object nombre = this.getTfNombre().getText();
		Object tipo = ddTipoBeneficio.getSelected();
		if((tipo != null) && (tipo.toString().length() > 0)) {
			tipoBeneficio = TipoBeneficio.valueOf(tipo.toString());
		}

		if(nombre != null && nombre != "") {
			beneficio.setNombre(nombre.toString());
		} else {
			beneficio.setNombre(null);
		}
		if(tipoBeneficio != null) {
			beneficio.setTipoBeneficio(tipoBeneficio);
		} else {
			beneficio.setTipoBeneficio(null);
		}

		this.getElementoPila().getObjetos().set(0, beneficio);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Revisar el metodo completo
		Beneficio beneficio = null;

		if(this.getRequestBean1().getObjetoSeleccion() != null) {
			Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
			if(seleccionado instanceof TipoBeneficio) {
				beneficio = (Beneficio) this.obtenerObjetoDelElementoPila(0, Beneficio.class);
				beneficio.setTipoBeneficio((TipoBeneficio) seleccionado);
				this.getElementoPila().getObjetos().set(0, beneficio);
				this.getRequestBean1().setObjetoSeleccion(null);
				try {
					this.refrescarTabla();
				} catch(Exception ex) {
				}
			}
		}

		this.acomodarSeleccionado();

		// CAMBIAR:
		beneficio = (Beneficio) this.obtenerObjetoDelElementoPila(0, Beneficio.class);

		this.getTfNombre().setText(beneficio.getNombre());
		this.getDdTipoBeneficio().setSelected(Util.getEnumNameFromString(String.valueOf(beneficio.getTipoBeneficio())));
		this.getDdTipoBeneficioDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(beneficio.getTipoBeneficio())));

		// if (this.getLdpBeneficio().getList() != null){
		// Long filaSeleccionada = new Long(this.getElementoPila().getPosicionGlobal());
		// System.out.println("filaSeleccionada :" +filaSeleccionada);
		// this.seleccionarFila(filaSeleccionada);
		// }
	}

	@Override
	protected void refrescarTabla() throws Exception {
		// CAMBIAR: Segun objeto de busqueda.
		Beneficio beneficio = (Beneficio) this.obtenerObjetoDelElementoPila(0, Beneficio.class);

		// CAMBIAR: Utilizar el EJBClient adecuado.
		this.getCommunicationAccionSocialBean().getRemoteSystemFichaSocial().setLlave(this.getSessionBean1().getLlave());
		this.setListaDelCommunication((ArrayList) this.getCommunicationAccionSocialBean().getRemoteSystemFichaSocial()
				.findListaBeneficio(beneficio.getNombre(), beneficio.getTipoBeneficio()));
		this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		this.setRBSelected((new Long(0)).toString());
	}

	@Override
	protected void limpiarObjetosUsados() {
		for(int i = 0; i < this.getElementoPila().getObjetos().size(); i++) {
			this.getElementoPila().getObjetos().set(i, null);
		}

		// CAMBIAR: Limpiar los textField y los dropDown
		this.getTfNombre().setText("");
		this.getDdTipoBeneficio().setSelected("");
		this.getDdTipoBeneficioDefaultOptions().setSelectedValue("");
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		// CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
		return this.getLdpBeneficio();
	}

	@Override
	protected List getListaDelCommunication() {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		return this.getCommunicationAccionSocialBean().getListaBeneficios();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		// CAMBIAR: Utilizar la Lista del Comunication que corresponda
		this.getCommunicationAccionSocialBean().setListaBeneficios(lista);
	}

	protected void acomodarSeleccionado() {
		Object seleccionado = this.getRequestBean1().getObjetoSeleccion();
		if(seleccionado != null) {
			int posicion = ((Integer) this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class)).intValue();
			this.getElementoPila().getObjetos().set(posicion, seleccionado);
		}
	}

	protected int getCantidadObjetosUsados() {
		return this.getElementoPila().getObjetos().size();
	}

	@Override
	public String btnSeleccionar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		Beneficio beneficio = new Beneficio();

		if(ultimo) {
			RowKey rk = null;

			try {
				rk = this.getSeleccionado();

				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider().getObjects()[index];
					beneficio = (Beneficio) obj;
					// this.getCommunicationAccionSocialBean().getRemoteSystemFichaSocial().setLlave(this.getSessionBean1().getLlave());
					beneficio = this.getCommunicationAccionSocialBean().getRemoteSystemFichaSocial().getBeneficioPorId(beneficio.getIdBeneficio());
					getRequestBean1().setObjetoSeleccion(beneficio);

					this.setRowKeySeleccionado(this.getSeleccionado());
				}

			} catch(Exception ex) {
				log(getCasoNavegacion() + "_SeleccionarError:", ex);
				error(getNombrePagina() + " - Seleccionar: " + ex.getMessage());
				ex.printStackTrace();
			}

			if(rk != null) {
				ElementoPila locElementoAnterior = this.getSessionBean1().getMgrPilas().getElementoPilaAnterior(this.getElementoPila());
				if(locElementoAnterior == null) {
					return null;
				}
				retorno = this.prepararParaVolver(Constantes.ACCION_SELECCIONAR);
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected TableSelectPhaseListener getTablePhaseListener() {
		return null;
	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		Beneficio locBeneficio = (Beneficio) pObject;
		getCommunicationAccionSocialBean().getRemoteSystemFichaSocial().setLlave(this.getSessionBean1().getLlave());
		locBeneficio = getCommunicationAccionSocialBean().getRemoteSystemFichaSocial().getBeneficioPorId(locBeneficio.getIdBeneficio());
		return locBeneficio;
	}

	@Override
	protected String getNombrePagina() {
		return "Administraci\363n de Beneficios";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminBeneficio";
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNombreBean() {
		return "#{accionsocial$ABMBeneficio$AdminBeneficio}";
	}

	@Override
	public long getSerialVersionUID() {
		return Beneficio.serialVersionUID;
	}
}