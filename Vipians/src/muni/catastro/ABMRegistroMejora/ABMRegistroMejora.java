/*
 * AgregarRegistroMejora.java
 *
 * Created on 2 de noviembre de 2006, 17:14
 * Copyright Trascender
 */

package muni.catastro.ABMRegistroMejora;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion;
import com.trascender.catastro.recurso.persistent.DeclaracionJurada;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.RegistroMejora;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMRegistroMejora extends ABMPageBean {

	@Override
	protected void _init() throws Exception {

		Option[] op = null;
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(CoeficienteDepreciacion.EstadoCoeficiente.values(), "cap");
		this.getDdEstadoMejoraDefaultOptions().setOptions(op);

	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		RegistroMejora registroMejora = this.obtenerObjetoDelElementoPila(0, RegistroMejora.class);
		this.getTablaLogs().getLdpLogs().setList(registroMejora.getListaLogsAuditoria());
		this.guardarEstadoObjetosUsados();
	}
	
	private Label lblFechaInscripcionDJ = new Label();
	private Label lblNumeroDJ = new Label();
	private TextField tfFechaInscripcionDJ = new TextField();
	private TextField tfNumeroDJ = new TextField();
	
	public Label getLblFechaInscripcionDJ() {
		return lblFechaInscripcionDJ;
	}

	public void setLblFechaInscripcionDJ(Label lblFechaInscripcionDJ) {
		this.lblFechaInscripcionDJ = lblFechaInscripcionDJ;
	}

	public Label getLblNumeroDJ() {
		return lblNumeroDJ;
	}

	public void setLblNumeroDJ(Label lblNumeroDJ) {
		this.lblNumeroDJ = lblNumeroDJ;
	}

	public TextField getTfFechaInscripcionDJ() {
		return tfFechaInscripcionDJ;
	}

	public void setTfFechaInscripcionDJ(TextField tfFechaInscripcionDJ) {
		this.tfFechaInscripcionDJ = tfFechaInscripcionDJ;
	}

	public TextField getTfNumeroDJ() {
		return tfNumeroDJ;
	}

	public void setTfNumeroDJ(TextField tfNumeroDJ) {
		this.tfNumeroDJ = tfNumeroDJ;
	}

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	private TextField tfSuperficie = new TextField();

	public TextField getTfSuperficie() {
		return tfSuperficie;
	}

	public void setTfSuperficie(TextField tfParcela) {
		this.tfSuperficie = tfParcela;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private TextField tfDeclaracionJurada = new TextField();

	public TextField getTfDeclaracionJurada() {
		return tfDeclaracionJurada;
	}

	public void setTfDeclaracionJurada(TextField tfParcela) {
		this.tfDeclaracionJurada = tfParcela;
	}

	private Button btnSeleccionarDeclaracionJurada = new Button();

	public Button getBtnSeleccionarDeclaracionJurada() {
		return btnSeleccionarDeclaracionJurada;
	}

	public void setBtnSeleccionarDeclaracionJurada(Button b) {
		this.btnSeleccionarDeclaracionJurada = b;
	}

	private Button btnSeleccionarParcela = new Button();

	public Button getBtnSeleccionarParcela() {
		return btnSeleccionarParcela;
	}

	public void setBtnSeleccionarParcela(Button b) {
		this.btnSeleccionarParcela = b;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private TextField tfAnioConstruccion = new TextField();

	public TextField getTfAnioConstruccion() {
		return tfAnioConstruccion;
	}

	public void setTfAnioConstruccion(TextField tf) {
		this.tfAnioConstruccion = tf;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private DropDown ddEstadoMejora = new DropDown();

	public DropDown getDdEstadoMejora() {
		return ddEstadoMejora;
	}

	public void setDdEstadoMejora(DropDown dd) {
		this.ddEstadoMejora = dd;
	}

	private SingleSelectOptionsList ddEstadoMejoraDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdEstadoMejoraDefaultOptions() {
		return ddEstadoMejoraDefaultOptions;
	}

	public void setDdEstadoMejoraDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddEstadoMejoraDefaultOptions = ssol;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label l) {
		this.label6 = l;
	}

	private TextField tfCategoria = new TextField();

	public TextField getTfCategoria() {
		return tfCategoria;
	}

	public void setTfCategoria(TextField tf) {
		this.tfCategoria = tf;
	}

	private Button btnSeleccionarCategoria = new Button();

	public Button getBtnSeleccionarCategoria() {
		return btnSeleccionarCategoria;
	}

	public void setBtnSeleccionarCategoria(Button b) {
		this.btnSeleccionarCategoria = b;
	}

	private HtmlAjaxCommandButton btnLimpiarDDJJ = new HtmlAjaxCommandButton();

	private HtmlAjaxCommandButton btnLimpiarCategoria = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarDDJJ() {
		return btnLimpiarDDJJ;
	}

	public void setBtnLimpiarDDJJ(HtmlAjaxCommandButton btnLimpiarDDJJ) {
		this.btnLimpiarDDJJ = btnLimpiarDDJJ;
	}

	public HtmlAjaxCommandButton getBtnLimpiarCategoria() {
		return btnLimpiarCategoria;
	}

	public void setBtnLimpiarCategoria(HtmlAjaxCommandButton btnLimpiarCategoria) {
		this.btnLimpiarCategoria = btnLimpiarCategoria;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMRegistroMejora() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new RegistroMejora());
		ep.getObjetos().add(ind++, new Parcela());
		ep.getObjetos().add(ind++, new DeclaracionJurada());
		ep.getObjetos().add(ind++, new Categoria());
		ep.getObjetos().add(ind++, new ArrayList()); // 4 AtributosDinamicos

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Obtener los valores de los campos y
		// asignarlos a los atributos de los objetos de la pagina
		RegistroMejora registro = this.obtenerObjetoDelElementoPila(0, RegistroMejora.class);
		Parcela parcela = this.obtenerObjetoDelElementoPila(1, Parcela.class);
		DeclaracionJurada declaracion = this.obtenerObjetoDelElementoPila(2, DeclaracionJurada.class);
		Categoria categoria = this.obtenerObjetoDelElementoPila(3, Categoria.class);
		ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(4, ArrayList.class);

		registro.setSuperficie(this.getTextFieldValueDouble(getTfSuperficie()));
		registro.setAnioConstruccion(this.getTextFieldValueInteger(getTfAnioConstruccion()));
		registro.setEstadoMejora(this.getDDEnumValue(getDdEstadoMejora(), CoeficienteDepreciacion.EstadoCoeficiente.class));

		registro.setParcela(parcela);

		declaracion.setFechaInscripcion(getTextFieldValueDate(this.getTfFechaInscripcionDJ()));
		declaracion.setNumero(getTextFieldValue(this.getTfNumeroDJ()));
		
		if (declaracion.getNumero() == null && declaracion.getFechaInscripcion() == null){
			declaracion = null;
		}
		
		registro.setDeclaracionJurada(declaracion);
		
		if(categoria.getIdCategoria() == -1) {
			categoria = null;
		}
		registro.setCategoria(categoria);
		
		atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		registro.setListaAtributosDinamicos(atributosDinamicos);

		this.getElementoPila().getObjetos().set(0, registro);
		this.getElementoPila().getObjetos().set(1, parcela);
		this.getElementoPila().getObjetos().set(2, declaracion);
		this.getElementoPila().getObjetos().set(3, categoria);
		this.getElementoPila().getObjetos().set(4, atributosDinamicos);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		RegistroMejora registro = this.obtenerObjetoDelElementoPila(0, RegistroMejora.class);
		Parcela parcela = this.obtenerObjetoDelElementoPila(1, Parcela.class);
		DeclaracionJurada declaracion = this.obtenerObjetoDelElementoPila(2, DeclaracionJurada.class);
		Categoria categoria = this.obtenerObjetoDelElementoPila(3, Categoria.class);
		ArrayList atributosDinamicos = this.obtenerObjetoDelElementoPila(4, ArrayList.class);
		
		
		if (registro.getListaAtributosDinamicos() != null) {
			try {
				 atributosDinamicos = (ArrayList) this.getComunicationBean().getRemoteSystemParametro()
						.getAtributosPorRecurso(RegistroMejora.serialVersionUID, registro.getListaAtributosDinamicos(), null);
				this.getElementoPila().getObjetos().set(4, atributosDinamicos);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		
		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, getNombreBean());
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);

		if(registro.getAnioConstruccion() != null) {
			this.getTfAnioConstruccion().setText(registro.getAnioConstruccion().toString());
		}

		if(declaracion.getNumero() != null) {
			this.getTfNumeroDJ().setText(declaracion.getNumero());
		}
		if(declaracion.getFechaInscripcion() != null){
			this.getTfFechaInscripcionDJ().setText(Conversor.getStringDeFechaCorta(declaracion.getFechaInscripcion()));
		}

		this.getTfCategoria().setText(categoria.toString().trim());
		if(registro.getSuperficie() != null) {
			this.getTfSuperficie().setText(Conversor.getStringDeDouble(registro.getSuperficie()));
		}
		this.getDdEstadoMejora().setSelected(Util.getEnumNameFromString(String.valueOf(registro.getEstadoMejora())));
		this.getDdEstadoMejoraDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(registro.getEstadoMejora())));
		this.setListaDelCommunicationAtributosDinamicos(atributosDinamicos);
	}

	protected List getListaDelCommunicationAtributosDinamicos() {
		return getComunicationCatastroBean().getListaAtributosDinamicosRegistroMejora();
	}

	private void setListaDelCommunicationAtributosDinamicos(ArrayList lista) {
		this.getComunicationCatastroBean().setListaAtributosDinamicosRegistroMejora(lista);
	}
	
	public String btnSeleccionarParcela_action() {
		return navegarParaSeleccionar("AdminParcela");
	}

	public String btnSeleccionarCategoria_action() {
		return navegarParaSeleccionar("AdminCategoria");
	}

	public String btnLimpiarCategoria_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(3, Categoria.class, this.getTfCategoria());
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMRegistroMejora";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		Parcela parcela = this.obtenerObjetoDelElementoPila(1, Parcela.class);
		RegistroMejora registro = null;
		Categoria categoria = null;
		if(pObject instanceof Parcela) {
			if(pObject != null) {
				parcela = (Parcela) pObject;
				try {
					// this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
					// parcela =
					// this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getParcelaPorId(parcela.getIdParcela());
					this.getElementoPila().getObjetos().set(1, parcela);
					if(registro == null) {
						registro = new RegistroMejora();
					}
					registro.setParcela(parcela);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		if(pObject instanceof Categoria) {
			if(pObject != null) {
				categoria = (Categoria) pObject;
				this.getElementoPila().getObjetos().set(3, categoria);
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		RegistroMejora registro = (RegistroMejora) this.getRequestBean1().getObjetoABM();
		Parcela parcela = registro.getParcela();
		DeclaracionJurada declaracion = registro.getDeclaracionJurada();
		Categoria categoria = registro.getCategoria();
		ArrayList atributosDinamicos = null;

		this.getElementoPila().getObjetos().set(0, registro);
		this.getElementoPila().getObjetos().set(1, parcela);
		this.getElementoPila().getObjetos().set(2, declaracion);
		this.getElementoPila().getObjetos().set(3, categoria);
		this.getElementoPila().getObjetos().set(4, atributosDinamicos);
	}

	@Override
	public String getNombreBean() {
		return "#{catastro$ABMRegistroMejora$ABMRegistroMejora}";
	}

	@Override
	public long getSerialVersionUID() {
		return RegistroMejora.serialVersionUID;
	}
}