
package muni.expedientes.ABMFaseProcedimiento;

import java.util.ArrayList;

import muni.expedientes.tables.TableTramiteProcedimiento;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMFaseProcedimiento extends ABMPageBean {

	TextField tfNombre = new TextField();
	Label lblNombre = new Label();

	private TableTramiteProcedimiento table = new TableTramiteProcedimiento();

	public TableTramiteProcedimiento getTable() {
		return table;
	}

	public void setTable(TableTramiteProcedimiento table) {
		this.table = table;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	@Override
	protected void _init() throws Exception {
		this.table._init();

	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		FaseProcedimiento locFaseProcedimiento = obtenerObjetoDelElementoPila(ind++, FaseProcedimiento.class);
		ArrayList locListaTramitesProcedimientos = (ArrayList) obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		locListaTramitesProcedimientos = (ArrayList) table.getList();
		locFaseProcedimiento.setListaNodosHijos(locListaTramitesProcedimientos);

		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, locFaseProcedimiento);
		this.getElementoPila().getObjetos().set(ind++, locListaTramitesProcedimientos);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void mostrarEstadoObjetosUsados() {
		acomodarSeleccionado();
		int ind = 0;
		FaseProcedimiento locFaseProcedimiento = (FaseProcedimiento) this.obtenerObjetoDelElementoPila(ind++, FaseProcedimiento.class);
		this.getTfNombre().setText(locFaseProcedimiento.getFaseCatalogo().getNombre());

		ArrayList locTramitesProcedimientos = (ArrayList) obtenerObjetoDelElementoPila(ind++, ArrayList.class);

		this.table.setList(locTramitesProcedimientos);

	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {

		int ind = 0;
		ep.getObjetos().add(ind++, new FaseProcedimiento());
		ep.getObjetos().add(ind++, new ArrayList());
		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));

		return ep;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof TramiteProcedimiento) {
			TramiteProcedimiento nuevaTramiteProcedimiento = (TramiteProcedimiento) pObject;
			ArrayList locTramitesProcedimientos = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);

			table.addToList(locTramitesProcedimientos, nuevaTramiteProcedimiento);

			this.getElementoPila().getObjetos().set(1, locTramitesProcedimientos);
			this.getRequestBean1().setObjetoSeleccion(null);
		}

	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	protected void procesarObjetoABM(Object pObject) {
		FaseProcedimiento locFaseProcedimiento = (FaseProcedimiento) pObject;
		ArrayList locListaTramiteProcedimientos = new ArrayList();
		try {
			long idFaseProcedimiento = locFaseProcedimiento.getIdNodoProcedimiento();
			this.getCommunicationExpedientesBean().getRemoteSystemProcedimientos().setLlave(this.getSessionBean1().getLlave());
			locFaseProcedimiento = this.getCommunicationExpedientesBean().getRemoteSystemProcedimientos().getFaseProcedimientoPorId(idFaseProcedimiento);
		} catch(Exception ex) {
			error("No se pudo recuperar FaseProcedimiento: " + ex.getMessage());
		}
		locListaTramiteProcedimientos = new ArrayList(locFaseProcedimiento.getListaTramitesProcedimientos());
		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, locFaseProcedimiento);
		this.getElementoPila().getObjetos().set(ind++, locListaTramiteProcedimientos);

	}

	@Override
	protected String getNombrePagina() {
		return "FaseProcedimiento";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMFaseProcedimiento";
	}

	public String btnAgregarTP_action() {
		return navegarParaSeleccionar("AdminTramiteProcedimiento");
	}

	public String btnQuitarTP_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			table.quitarElemento();
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarTodosTP_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				this.table.quitarTodosLosElementos();
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	@Override
	public String getNombreBean() {
		return "#{expedientes$ABMFaseProcedimiento$ABMFaseProcedimiento}";
	}

	@Override
	public long getSerialVersionUID() {
		return FaseProcedimiento.serialVersionUID;
	}
}