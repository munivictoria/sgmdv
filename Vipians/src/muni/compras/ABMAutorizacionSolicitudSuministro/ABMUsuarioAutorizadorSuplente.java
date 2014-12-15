/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muni.compras.ABMAutorizacionSolicitudSuministro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorSolSumSuplente;
import com.trascender.compras.recurso.persistent.suministros.UsuarioAutorizadorSolicitudSuministro;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * 
 * @author Fer Luca
 */
public class ABMUsuarioAutorizadorSuplente extends ABMPageBean {

	private Label lblUsuarioSuplente = new Label();
	private Label lblUsuarios = new Label();
	private Label lblFechaDesde = new Label();
	private Label lblFechaHasta = new Label();
	private TextField tfUsuarioSuplente = new TextField();
	private TextField tfFechaDesde = new TextField();
	private TextField tfFechaHasta = new TextField();
	private DropDown ddUsuarios = new DropDown();
	private SingleSelectOptionsList ddUsuariosDefaultOptions = new SingleSelectOptionsList();
	private Button btnSeleccionarUsuario = new Button();
	private HtmlAjaxCommandButton btnLimpiarUsuario = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarUsuario() {
		return btnLimpiarUsuario;
	}

	public void setBtnLimpiarUsuario(HtmlAjaxCommandButton btnLimpiarUsuario) {
		this.btnLimpiarUsuario = btnLimpiarUsuario;
	}

	public Button getBtnSeleccionarUsuario() {
		return btnSeleccionarUsuario;
	}

	public void setBtnSeleccionarUsuario(Button btnSeleccionarUsuario) {
		this.btnSeleccionarUsuario = btnSeleccionarUsuario;
	}

	public DropDown getDdUsuarios() {
		return ddUsuarios;
	}

	public void setDdUsuarios(DropDown ddUsuarios) {
		this.ddUsuarios = ddUsuarios;
	}

	public SingleSelectOptionsList getDdUsuariosDefaultOptions() {
		return ddUsuariosDefaultOptions;
	}

	public void setDdUsuariosDefaultOptions(SingleSelectOptionsList ddUsuariosDefaultOptions) {
		this.ddUsuariosDefaultOptions = ddUsuariosDefaultOptions;
	}

	public Label getLblFechaDesde() {
		return lblFechaDesde;
	}

	public void setLblFechaDesde(Label lblFechaDesde) {
		this.lblFechaDesde = lblFechaDesde;
	}

	public Label getLblFechaHasta() {
		return lblFechaHasta;
	}

	public void setLblFechaHasta(Label lblFechaHasta) {
		this.lblFechaHasta = lblFechaHasta;
	}

	public Label getLblUsuarioSuplente() {
		return lblUsuarioSuplente;
	}

	public void setLblUsuarioSuplente(Label lblUsuarioSuplente) {
		this.lblUsuarioSuplente = lblUsuarioSuplente;
	}

	public Label getLblUsuarios() {
		return lblUsuarios;
	}

	public void setLblUsuarios(Label lblUsuarios) {
		this.lblUsuarios = lblUsuarios;
	}

	public TextField getTfFechaDesde() {
		return tfFechaDesde;
	}

	public void setTfFechaDesde(TextField tfFechaDesde) {
		this.tfFechaDesde = tfFechaDesde;
	}

	public TextField getTfFechaHasta() {
		return tfFechaHasta;
	}

	public void setTfFechaHasta(TextField tfFechaHasta) {
		this.tfFechaHasta = tfFechaHasta;
	}

	public TextField getTfUsuarioSuplente() {
		return tfUsuarioSuplente;
	}

	public void setTfUsuarioSuplente(TextField tfUsuarioSuplente) {
		this.tfUsuarioSuplente = tfUsuarioSuplente;
	}

	@Override
	protected void _init() throws Exception {

		if(this.getCommunicationComprasBean().getListaUsuariosAutorizadoresDesdeABM() != null && this.getCommunicationComprasBean().getListaUsuariosAutorizadoresDesdeABM().size() > 0) {
			Option[] op = null;
			op = this.armarArrayOptionsList(this.getCommunicationComprasBean().getListaUsuariosAutorizadoresDesdeABM().toArray(), "");
			ddUsuariosDefaultOptions.setOptions(op);
		}
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		UsuarioAutorizadorSolSumSuplente usuarioSuplente = (UsuarioAutorizadorSolSumSuplente) this.obtenerObjetoDelElementoPila(ind++);
		UsuarioAutorizadorSolicitudSuministro usuarioReemplaza = null;

		usuarioSuplente.setFechaDesde(getTextFieldValueDate(getTfFechaDesde()));
		usuarioSuplente.setFechaHasta(getTextFieldValueDate(getTfFechaHasta()));

		for(UsuarioAutorizadorSolicitudSuministro cadaUsuario : this.getCommunicationComprasBean().getListaUsuariosAutorizadoresDesdeABM()) {
			System.out.println("this.getDdUsuarios().getSelected().toString(): " + this.getDdUsuarios().getSelected().toString());
			System.out.println("cadaUsuario.getUsuario().toString(): " + cadaUsuario.getUsuario().toString());
			if(this.getDdUsuarios().getSelected().toString().equals(cadaUsuario.getUsuario().toString())) {
				System.out.println("ENTROALIFDEUSRIGUALSELECTED");
				usuarioReemplaza = cadaUsuario;
				break;
			}
		}

		for(UsuarioAutorizadorSolicitudSuministro cadaUsuario : this.getCommunicationComprasBean().getListaUsuariosAutorizadoresDesdeABM()) {
			System.out.println("cadaUsuario.getIdUsuarioAutorizador(): " + cadaUsuario.getIdUsuarioAutorizador());
			System.out.println("cadaUsuario.getUsuario().getIdUsuario(): " + cadaUsuario.getUsuario().getIdUsuario());
		}

		usuarioSuplente.setUsuarioSuplido(usuarioReemplaza);

		this.getElementoPila().getObjetos().set(0, usuarioSuplente);
		this.getElementoPila().getObjetos().set(1, usuarioReemplaza);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		UsuarioAutorizadorSolSumSuplente usuarioSuplente = (UsuarioAutorizadorSolSumSuplente) this.obtenerObjetoDelElementoPila(ind++);
		UsuarioAutorizadorSolicitudSuministro usuarioReemplaza = (UsuarioAutorizadorSolicitudSuministro) this.obtenerObjetoDelElementoPila(ind++);
		Date fechaDesde = usuarioSuplente.getFechaDesde();
		Date fechaHasta = usuarioSuplente.getFechaHasta();

		if(usuarioSuplente.getUsuario() != null) {
			this.getTfUsuarioSuplente().setText(usuarioSuplente);
		}
		if(fechaDesde != null) {
			this.getTfFechaDesde().setText(Conversor.getStringDeFechaCorta(fechaDesde));
		}
		if(fechaHasta != null) {
			this.getTfFechaHasta().setText(Conversor.getStringDeFechaCorta(fechaHasta));
		}

		if(usuarioReemplaza.getUsuario() != null) {
			System.out.println("MOSTRARESTADO SETEANDO EL SELECTED DEL DD: " + usuarioReemplaza.getUsuario().toString());
			this.getDdUsuarios().setSelected(usuarioReemplaza.getUsuario().toString());
			this.getDdUsuariosDefaultOptions().setSelectedValue(usuarioReemplaza.getUsuario().toString());
		}

		this.getElementoPila().getObjetos().set(0, usuarioSuplente);
		this.getElementoPila().getObjetos().set(1, usuarioReemplaza);
	}

	public String btnSeleccionarUsuario_action() {
		return navegarParaSeleccionar("AdminUsuario");
	}

	public String btnLimpiarUsuario_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			this.limpiarObjeto(getTfUsuarioSuplente());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMUsuarioAutorizadorSuplente";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new UsuarioAutorizadorSolSumSuplente());
		ep.getObjetos().add(ind++, new UsuarioAutorizadorSolicitudSuministro());

		return ep;
	}

	public Option[] armarArrayOptionsList(Object[] objetos, String capitalizacion) {
		Option[] o = new Option[objetos.length];
		// o[0] = new Option("", "");

		for(int i = 0; i < objetos.length; i++) {
			String display = objetos[i].toString();

			display = display.replaceAll("_", " ");
			// objetos[i].toString().toUpperCase().replaceAll(" ", "_");
			// reemplazado por getEnumNameFromString
			Option opcion = new Option(objetos[i].toString(), display);
			o[i] = opcion;
		}
		return o;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		if(pObject instanceof ArrayList) {
			this.getCommunicationComprasBean().setListaUsuariosAutorizadoresDesdeABM((List<UsuarioAutorizadorSolicitudSuministro>) pObject);

			Option[] op = null;
			op = this.armarArrayOptionsList(this.getCommunicationComprasBean().getListaUsuariosAutorizadoresDesdeABM().toArray(), "");
			ddUsuariosDefaultOptions.setOptions(op);// armar DD aca
			for(int i = 0; i < op.length; i++) {
				System.out.println("recorriendo for del mostrar(label): " + op[i].getLabel());
				System.out.println("recorriendo for del mostrar(descripcion): " + op[i].getDescription());
			}
		}

		if(pObject instanceof Usuario) {
			Usuario usuarioSeleccionado = (Usuario) pObject;

			UsuarioAutorizadorSolSumSuplente usuarioSuplente = (UsuarioAutorizadorSolSumSuplente) this.obtenerObjetoDelElementoPila(0, UsuarioAutorizadorSolSumSuplente.class);

			usuarioSuplente.setUsuario(usuarioSeleccionado);

			this.getElementoPila().getObjetos().set(0, usuarioSuplente);
			this.getRequestBean1().setObjetoSeleccion(null);
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		UsuarioAutorizadorSolSumSuplente usuarioSuplente = (UsuarioAutorizadorSolSumSuplente) pObject;
		UsuarioAutorizadorSolicitudSuministro usuarioReemplaza = usuarioSuplente.getUsuarioSuplido();

		this.getElementoPila().getObjetos().set(0, usuarioSuplente);
		this.getElementoPila().getObjetos().set(1, usuarioReemplaza);
	}

	@Override
	public String getNombreBean() {
		return "#{compras$ABMAutorizacionSolicitudSuministro$ABMUsuarioAutorizadorSuplente}";
	}

	@Override
	public long getSerialVersionUID() {
		return UsuarioAutorizadorSolSumSuplente.getSerialversionuid();
	}
}