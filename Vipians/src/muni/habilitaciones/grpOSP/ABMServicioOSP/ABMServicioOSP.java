/*
 * ModificarServicioOSP.java
 *
 * Created on 18 de octubre de 2006, 10:30
 * Copyright Trascender SRL
 */

package muni.habilitaciones.grpOSP.ABMServicioOSP;

import java.util.ArrayList;

import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.util.Periodicidad;
import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP.UnidadMedida;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

public class ABMServicioOSP extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		Option[] op = null;
		// Unidades de Medida
		op = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(UnidadMedida.values(), "cap");
		ddUnidadMedidaDefaultOptions.setOptions(op);

		Option[] opPeriodicidad = null;

		opPeriodicidad = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(Periodicidad.values(), "cap");
		ddPeriodicidadDefaultOptions.setOptions(opPeriodicidad);
	}

	private TextField tfCodigo = new TextField();

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tf) {
		this.tfCodigo = tf;
	}

	private Label label4 = new Label();
	private Label lblCoeficienteValorTerreno = new Label();
	private Label lblCoeficienteValorEdificado = new Label();
	private Label label8 = new Label();
	private Label lblPeriodicidad = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	private TextArea taNombre = new TextArea();

	public TextArea getTaNombre() {
		return taNombre;
	}

	public void setTaNombre(TextArea ta) {
		this.taNombre = ta;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private TextField tfValor = new TextField();
	private TextField tfCoeficienteCodigo = new TextField();
	private TextField tfCoeficienteValorTerreno = new TextField();
	private TextField tfCoeficienteValorEdificado = new TextField();

	public Label getLblCoeficienteValorEdificado() {
		return lblCoeficienteValorEdificado;
	}

	public void setLblCoeficienteValorEdificado(Label lblCoeficienteValorEdificado) {
		this.lblCoeficienteValorEdificado = lblCoeficienteValorEdificado;
	}

	public Label getLblCoeficienteValorTerreno() {
		return lblCoeficienteValorTerreno;
	}

	public void setLblCoeficienteValorTerreno(Label lblCoeficienteValorTerreno) {
		this.lblCoeficienteValorTerreno = lblCoeficienteValorTerreno;
	}

	public TextField getTfCoeficienteValorEdificado() {
		return tfCoeficienteValorEdificado;
	}

	public void setTfCoeficienteValorEdificado(TextField tfCoeficienteValorEdificado) {
		this.tfCoeficienteValorEdificado = tfCoeficienteValorEdificado;
	}

	public TextField getTfCoeficienteValorTerreno() {
		return tfCoeficienteValorTerreno;
	}

	public void setTfCoeficienteValorTerreno(TextField tfCoeficienteValorTerreno) {
		this.tfCoeficienteValorTerreno = tfCoeficienteValorTerreno;
	}

	public Label getLabel8() {
		return label8;
	}

	public void setLabel8(Label label8) {
		this.label8 = label8;
	}

	public Label getLblPeriodicidad() {
		return lblPeriodicidad;
	}

	public void setLblPeriodicidad(Label lblPeriodicidad) {
		this.lblPeriodicidad = lblPeriodicidad;
	}

	public TextField getTfCoeficienteCodigo() {
		return tfCoeficienteCodigo;
	}

	public void setTfCoeficienteCodigo(TextField tfCoeficienteCodigo) {
		this.tfCoeficienteCodigo = tfCoeficienteCodigo;
	}

	public TextField getTfValor() {
		return tfValor;
	}

	public void setTfValor(TextField tf) {
		this.tfValor = tf;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private Checkbox cbMedido = new Checkbox();

	public Checkbox getCbMedido() {
		return cbMedido;
	}

	public void setCbMedido(Checkbox c) {
		this.cbMedido = c;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private Label label6 = new Label();

	public Label getLabel6() {
		return label6;
	}

	public void setLabel6(Label l) {
		this.label6 = l;
	}

	private Label label7 = new Label();

	public Label getLabel7() {
		return label7;
	}

	public void setLabel7(Label l) {
		this.label7 = l;
	}

	private DropDown ddUnidadMedida = new DropDown();

	public DropDown getDdUnidadMedida() {
		return ddUnidadMedida;
	}

	public void setDdUnidadMedida(DropDown dd) {
		this.ddUnidadMedida = dd;
	}

	private SingleSelectOptionsList ddUnidadMedidaDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdUnidadMedidaDefaultOptions() {
		return ddUnidadMedidaDefaultOptions;
	}

	public void setDdUnidadMedidaDefaultOptions(SingleSelectOptionsList ssol) {
		this.ddUnidadMedidaDefaultOptions = ssol;
	}

	private TextField tfBaseConsumo = new TextField();

	public TextField getTfBaseConsumo() {
		return tfBaseConsumo;
	}

	public void setTfBaseConsumo(TextField tf) {
		this.tfBaseConsumo = tf;
	}

	private TextField tfValorPorExcedente = new TextField();

	public TextField getTfValorPorExcedente() {
		return tfValorPorExcedente;
	}

	public void setTfValorPorExcedente(TextField tf) {
		this.tfValorPorExcedente = tf;
	}

	private StaticText staticText2 = new StaticText();

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private SingleSelectOptionsList ddPeriodicidadDefaultOptions = new SingleSelectOptionsList();

	private DropDown ddPeriodicidad = new DropDown();

	public DropDown getDdPeriodicidad() {
		return ddPeriodicidad;
	}

	public void setDdPeriodicidad(DropDown ddPeriodicidad) {
		this.ddPeriodicidad = ddPeriodicidad;
	}

	public SingleSelectOptionsList getDdPeriodicidadDefaultOptions() {
		return ddPeriodicidadDefaultOptions;
	}

	public void setDdPeriodicidadDefaultOptions(SingleSelectOptionsList ddPeriodicidadDefaultOptions) {
		this.ddPeriodicidadDefaultOptions = ddPeriodicidadDefaultOptions;
	}

	private Label lblVolcadoEfluentesIndustriales = new Label();

	public Label getLblVolcadoEfluentesIndustriales() {
		return lblVolcadoEfluentesIndustriales;
	}

	public void setLblVolcadoEfluentesIndustriales(Label pLblVolcadoEfluentesIndustriales) {
		this.lblVolcadoEfluentesIndustriales = pLblVolcadoEfluentesIndustriales;
	}

	private Checkbox cbVolcadoEfluentesIndustriales = new Checkbox();

	public Checkbox getCbVolcadoEfluentesIndustriales() {
		return cbVolcadoEfluentesIndustriales;
	}

	public void setCbVolcadoEfluentesIndustriales(Checkbox pCbVolcadoEfluentesIndustriales) {
		this.cbVolcadoEfluentesIndustriales = pCbVolcadoEfluentesIndustriales;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		int ind = 0;
		ServicioOSP servicioOSP = (ServicioOSP) this.obtenerObjetoDelElementoPila(ind++, ServicioOSP.class);
		ArrayList atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		Periodicidad periodicidad = null;

		servicioOSP.setCodigo(this.getTextFieldValue(this.getTfCodigo()));
		servicioOSP.setNombre(this.getTextAreaValue(this.getTaNombre()));
		servicioOSP.setValor(Conversor.getDoubleDeString(this.getTextFieldValue(this.getTfValor())));
		servicioOSP.setCoeficienteCodigoServicio(Conversor.getDoubleDeString(this.getTextFieldValue(this.getTfCoeficienteCodigo())));
		servicioOSP.setCoeficienteValorTerreno(Conversor.getDoubleDeString(this.getTextFieldValue(this.getTfCoeficienteValorTerreno())));
		servicioOSP.setCoeficienteValorEdificado(Conversor.getDoubleDeString(this.getTextFieldValue(this.getTfCoeficienteValorEdificado())));

		atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		servicioOSP.setListaAtributosDinamicos(atributosDinamicos);

		Object medido = this.getCbMedido().getValue();
		Object volcadoEfluentesIndustriales = this.getCbVolcadoEfluentesIndustriales().getValue();

		Object periodicidadSelected = this.getDdPeriodicidad().getSelected();
		if((periodicidadSelected != null) && (periodicidadSelected.toString().length() > 0)) {
			periodicidad = Periodicidad.valueOf(periodicidadSelected.toString());
		}
		servicioOSP.setPeriodicidad(periodicidad);

		Object unidad = this.getDdUnidadMedida().getSelected();
		UnidadMedida unidadMedida = null;
		if((unidad != null) && (unidad.toString().length() > 0))
			unidadMedida = UnidadMedida.valueOf(unidad.toString());

		Object baseConsumo = this.getTfBaseConsumo().getText();
		Object valorPorExcedente = this.getTfValorPorExcedente().getText();

		if(medido != null && medido != "")
			servicioOSP.setMedido(((Boolean) medido).booleanValue());
		else
			servicioOSP.setMedido(false);
		if(servicioOSP.isMedido()) {
			if(unidadMedida != null)
				servicioOSP.setUnidadMedida(unidadMedida);
			else
				servicioOSP.setUnidadMedida(null);
			if(baseConsumo != null && baseConsumo != "")
				servicioOSP.setBaseConsumo(Conversor.getDoubleDeString(baseConsumo.toString()));
			else
				servicioOSP.setBaseConsumo(null);
			if(valorPorExcedente != null && valorPorExcedente != "")
				servicioOSP.setValorPorExcedente(Conversor.getDoubleDeString(valorPorExcedente.toString()));
			else
				servicioOSP.setValorPorExcedente(null);
		} else {
			servicioOSP.setUnidadMedida(null);
			servicioOSP.setBaseConsumo(null);
			servicioOSP.setValorPorExcedente(null);
		}

		if(volcadoEfluentesIndustriales != null && volcadoEfluentesIndustriales != "") {
			servicioOSP.setVolcadoEfluentesIndustriales(((Boolean) volcadoEfluentesIndustriales).booleanValue());
		} else {
			servicioOSP.setVolcadoEfluentesIndustriales(false);
		}

		// NOTA: seteo el tipo de obligacion como OSP
		servicioOSP.setTipoObligacion(this.getCommunicationHabilitacionesBean().getMapaTipoObligacion().get("OYSP"));
		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, servicioOSP);
		this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Verificar el metodo completo.
		ServicioOSP servicioOSP = null;
		ArrayList atributosDinamicos = null;

		this.acomodarSeleccionado();
		int ind = 0;
		servicioOSP = (ServicioOSP) this.obtenerObjetoDelElementoPila(ind++, ServicioOSP.class);
		try {
			atributosDinamicos = (ArrayList) this.getComunicationBean().getRemoteSystemParametro()
					.getAtributosPorRecurso(ServicioOSP.serialVersionUID, servicioOSP.getListaAtributosDinamicos(), null);
			this.getElementoPila().getObjetos().set(ind++, atributosDinamicos);
		} catch(TrascenderException ex) {
			ex.printStackTrace();
		}
		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);

		this.getTfCodigo().setText(servicioOSP.getCodigo());
		this.getTaNombre().setText(servicioOSP.getNombre());

		if(servicioOSP.getCoeficienteCodigoServicio() != null) {
			this.getTfCoeficienteCodigo().setText(servicioOSP.getCoeficienteCodigoServicio().toString());
		}

		if(servicioOSP.getCoeficienteValorTerreno() != null) {
			this.getTfCoeficienteValorTerreno().setText(servicioOSP.getCoeficienteValorTerreno().toString());
		}
		if(servicioOSP.getCoeficienteValorEdificado() != null) {
			this.getTfCoeficienteValorEdificado().setText(servicioOSP.getCoeficienteValorEdificado().toString());
		}
		if(servicioOSP.getValor() != null)
			this.getTfValor().setText(servicioOSP.getValor().toString());
		this.getDdPeriodicidad().setSelected(Util.getEnumNameFromString(String.valueOf(servicioOSP.getPeriodicidad())));
		this.getDdPeriodicidadDefaultOptions().setSelectedValue(Util.getEnumNameFromString(String.valueOf(servicioOSP.getPeriodicidad())));
		this.getCbVolcadoEfluentesIndustriales().setValue(new Boolean(servicioOSP.isVolcadoEfluentesIndustriales()));
		this.getCbMedido().setValue(new Boolean(servicioOSP.isMedido()));

		if(servicioOSP.isMedido()) {
			if(servicioOSP.getBaseConsumo() != null)
				this.getTfBaseConsumo().setText(servicioOSP.getBaseConsumo().toString());
			if(servicioOSP.getValorPorExcedente() != null)
				this.getTfValorPorExcedente().setText(servicioOSP.getValorPorExcedente().toString());
			this.getDdUnidadMedida().setSelected(Util.getEnumNameFromString(servicioOSP.getUnidadMedida().toString()));
			this.getDdUnidadMedidaDefaultOptions().setSelectedValue(Util.getEnumNameFromString(servicioOSP.getUnidadMedida().toString()));
		} else {
			this.getTfBaseConsumo().setText(null);
			this.getTfValorPorExcedente().setText(null);
			this.getDdUnidadMedida().setSelected(null);
			this.getDdUnidadMedidaDefaultOptions().setSelectedValue(null);
		}
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// TODO Auto-generated method stub
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMServicioOSP";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new ServicioOSP());
		ep.getObjetos().add(ind++, new ArrayList());// AtributosDinamicos

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		if(pObject instanceof ServicioOSP) {
			ServicioOSP ServicioOSP = (ServicioOSP) pObject;
			this.getElementoPila().getObjetos().set(0, ServicioOSP);
		}
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		ServicioOSP locServicioOSP = this.obtenerObjetoDelElementoPila(0, ServicioOSP.class);
		this.getTablaLogs().getLdpLogs().setList(locServicioOSP.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return ServicioOSP.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpOSP$ABMServicioOSP$ABMServicioOSP}";
	}
}