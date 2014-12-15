/*
 * ABMLiquidacionTGI.java
 *
 * Created on 3 de noviembre de 2006, 15:17
 * Copyright Trascender SRL
 */

package muni.saic.grpTGI.ABMLiquidacionTGI;

import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;

/**
 * 
 * @author nico
 * 
 */
public class ABMLiquidacionTGI extends ABMPageBean {

	protected void _init() throws Exception {
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private DropDown ddPeriodicidad = new DropDown();

	public DropDown getDdPeriodicidad() {
		return ddPeriodicidad;
	}

	public void setDdPeriodicidad(DropDown ddPeriodicidad) {
		this.ddPeriodicidad = ddPeriodicidad;
	}

	private SingleSelectOptionsList ddPeriodicidadDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdPeriodicidadDefaultOptions() {
		return ddPeriodicidadDefaultOptions;
	}

	public void setDdPeriodicidadDefaultOptions(SingleSelectOptionsList ddPeriodicidadDefaultOptions) {
		this.ddPeriodicidadDefaultOptions = ddPeriodicidadDefaultOptions;
	}

	// ----------Static Texts---------------------------
	private StaticText stAnio = new StaticText();

	public StaticText getStAnio() {
		return stAnio;
	}

	public void setStAnio(StaticText stAnio) {
		this.stAnio = stAnio;
	}

	private StaticText stTitulo = new StaticText();

	public StaticText getStTitulo() {
		return stTitulo;
	}

	public void setStTitulo(StaticText st) {
		this.stTitulo = st;
	}

	// ----------Labels---------------------------
	private Label lblDatosObligacion = new Label();
	private Label lblFechaObligacion = new Label();
	private Label lblPersona = new Label();
	private Label lblObligacion = new Label();
	private Label lblFormula = new Label();
	private Label lblTituloPeriodo = new Label();
	private Label lblAnio = new Label();
	private Label lblPeriodicidad = new Label();
	private Label lblPeriodo = new Label();
	private Label lblDatosLiquidacion = new Label();
	private Label lblCuotaLiquidada = new Label();
	private Label lblFechaVencimiento = new Label();
	private Label lblTipoDeuda = new Label();
	private Label lblEstado = new Label();
	private Label lblMonto = new Label();
	private Label lblModificadores = new Label();
	private Label lblParametros = new Label();
	private Label lblVencimientos = new Label();
	private Label lblExencion = new Label();
	private Label lblFechaCancelacion = new Label();

	public Label getLblFechaCancelacion() {
		return lblFechaCancelacion;
	}

	public void setLblFechaCancelacion(Label lblFechaCancelacion) {
		this.lblFechaCancelacion = lblFechaCancelacion;
	}

	public Label getLblVencimientos() {
		return lblVencimientos;
	}

	public void setLblVencimientos(Label lblVencimientos) {
		this.lblVencimientos = lblVencimientos;
	}

	public Label getLblExencion() {
		return lblExencion;
	}

	public void setLblExencion(Label lblExencion) {
		this.lblExencion = lblExencion;
	}

	public Label getLblModificadores() {
		return lblModificadores;
	}

	public void setLblModificadores(Label lblModificadores) {
		this.lblModificadores = lblModificadores;
	}

	public Label getLblParametros() {
		return lblParametros;
	}

	public void setLblParametros(Label lblParametros) {
		this.lblParametros = lblParametros;
	}

	public Label getLblCuotaLiquidada() {
		return lblCuotaLiquidada;
	}

	public void setLblCuotaLiquidada(Label lblCuotaLiquidada) {
		this.lblCuotaLiquidada = lblCuotaLiquidada;
	}

	public Label getLblDatosLiquidacion() {
		return lblDatosLiquidacion;
	}

	public void setLblDatosLiquidacion(Label lblDatosLiquidacion) {
		this.lblDatosLiquidacion = lblDatosLiquidacion;
	}

	public Label getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(Label lblEstado) {
		this.lblEstado = lblEstado;
	}

	public Label getLblFechaVencimiento() {
		return lblFechaVencimiento;
	}

	public void setLblFechaVencimiento(Label lblFechaVencimiento) {
		this.lblFechaVencimiento = lblFechaVencimiento;
	}

	public Label getLblMonto() {
		return lblMonto;
	}

	public void setLblMonto(Label lblMonto) {
		this.lblMonto = lblMonto;
	}

	public Label getLblTipoDeuda() {
		return lblTipoDeuda;
	}

	public void setLblTipoDeuda(Label lblTipoDeuda) {
		this.lblTipoDeuda = lblTipoDeuda;
	}

	public Label getLblAnio() {
		return lblAnio;
	}

	public void setLblAnio(Label lblAnio) {
		this.lblAnio = lblAnio;
	}

	public Label getLblDatosObligacion() {
		return lblDatosObligacion;
	}

	public void setLblDatosObligacion(Label lblDatosObligacion) {
		this.lblDatosObligacion = lblDatosObligacion;
	}

	public Label getLblFechaObligacion() {
		return lblFechaObligacion;
	}

	public void setLblFechaObligacion(Label lblFechaObligacion) {
		this.lblFechaObligacion = lblFechaObligacion;
	}

	public Label getLblFormula() {
		return lblFormula;
	}

	public void setLblFormula(Label lblFormula) {
		this.lblFormula = lblFormula;
	}

	public Label getLblObligacion() {
		return lblObligacion;
	}

	public void setLblObligacion(Label lblObligacion) {
		this.lblObligacion = lblObligacion;
	}

	public Label getLblPeriodicidad() {
		return lblPeriodicidad;
	}

	public void setLblPeriodicidad(Label lblPeriodicidad) {
		this.lblPeriodicidad = lblPeriodicidad;
	}

	public Label getLblPeriodo() {
		return lblPeriodo;
	}

	public void setLblPeriodo(Label lblPeriodo) {
		this.lblPeriodo = lblPeriodo;
	}

	public Label getLblPersona() {
		return lblPersona;
	}

	public void setLblPersona(Label lblPersona) {
		this.lblPersona = lblPersona;
	}

	public Label getLblTituloPeriodo() {
		return lblTituloPeriodo;
	}

	public void setLblTituloPeriodo(Label lblTituloPeriodo) {
		this.lblTituloPeriodo = lblTituloPeriodo;
	}

	// ----------Text Fields---------------------------
	private TextField tfPersona = new TextField();
	private TextField tfFechaObligacion = new TextField();
	private TextField tfPeriodo = new TextField();
	private TextField tfCuotaLiquidada = new TextField();
	private TextField tfFechaVencimiento = new TextField();
	private TextField tfTipoDeuda = new TextField();
	private TextField tfEstado = new TextField();
	private TextField tfMonto = new TextField();
	private TextField tfFechaCancelacion = new TextField();

	public TextField getTfFechaCancelacion() {
		return tfFechaCancelacion;
	}

	public void setTfFechaCancelacion(TextField tfFechaCancelacion) {
		this.tfFechaCancelacion = tfFechaCancelacion;
	}

	public TextField getTfCuotaLiquidada() {
		return tfCuotaLiquidada;
	}

	public void setTfCuotaLiquidada(TextField tfCuotaLiquidada) {
		this.tfCuotaLiquidada = tfCuotaLiquidada;
	}

	public TextField getTfEstado() {
		return tfEstado;
	}

	public void setTfEstado(TextField tfEstado) {
		this.tfEstado = tfEstado;
	}

	public TextField getTfFechaVencimiento() {
		return tfFechaVencimiento;
	}

	public void setTfFechaVencimiento(TextField tfFechaVencimiento) {
		this.tfFechaVencimiento = tfFechaVencimiento;
	}

	public TextField getTfMonto() {
		return tfMonto;
	}

	public void setTfMonto(TextField tfMonto) {
		this.tfMonto = tfMonto;
	}

	public TextField getTfTipoDeuda() {
		return tfTipoDeuda;
	}

	public void setTfTipoDeuda(TextField tfTipoDeuda) {
		this.tfTipoDeuda = tfTipoDeuda;
	}

	public TextField getTfFechaObligacion() {
		return tfFechaObligacion;
	}

	public void setTfFechaObligacion(TextField tfFechaObligacion) {
		this.tfFechaObligacion = tfFechaObligacion;
	}

	public TextField getTfPeriodo() {
		return tfPeriodo;
	}

	public void setTfPeriodo(TextField tfPeriodo) {
		this.tfPeriodo = tfPeriodo;
	}

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tfPersona) {
		this.tfPersona = tfPersona;
	}

	// ----------Text Areas---------------------------
	private TextArea taObligacion = new TextArea();
	private TextArea taFormula = new TextArea();
	private TextArea taModificadores = new TextArea();
	private TextArea taParametros = new TextArea();
	private TextArea taVencimientos = new TextArea();
	private TextArea taExencion = new TextArea();

	public TextArea getTaExencion() {
		return taExencion;
	}

	public void setTaExencion(TextArea taExencion) {
		this.taExencion = taExencion;
	}

	public TextArea getTaVencimientos() {
		return taVencimientos;
	}

	public void setTaVencimientos(TextArea taVencimientos) {
		this.taVencimientos = taVencimientos;
	}

	public TextArea getTaModificadores() {
		return taModificadores;
	}

	public void setTaModificadores(TextArea taModificadores) {
		this.taModificadores = taModificadores;
	}

	public TextArea getTaParametros() {
		return taParametros;
	}

	public void setTaParametros(TextArea taParametros) {
		this.taParametros = taParametros;
	}

	public TextArea getTaFormula() {
		return taFormula;
	}

	public void setTaFormula(TextArea taFormula) {
		this.taFormula = taFormula;
	}

	public TextArea getTaObligacion() {
		return taObligacion;
	}

	public void setTaObligacion(TextArea taObligacion) {
		this.taObligacion = taObligacion;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMLiquidacionTGI() {
	}

	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, null); // Liquidacion TGI

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de seleccionado.
		ep.getObjetos().add(ind++, new Integer(0));
		return ep;
	}

	protected void guardarEstadoObjetosUsados() {
	}

	protected void mostrarEstadoObjetosUsados() {
		LiquidacionTasa liquidacionTGI = (LiquidacionTasa) this.obtenerObjetoDelElementoPila(0);

		if(liquidacionTGI != null) {
			this.getTfPeriodo().setText(liquidacionTGI.getPeriodoAnio().toString());
			this.getTaFormula().setText(liquidacionTGI.getStringFormula());
			this.getTaParametros().setText(liquidacionTGI.getStringParametrosValuados());
			this.getTaModificadores().setText(liquidacionTGI.getStringModificadoresLiquidacion());
			this.getTaVencimientos().setText(liquidacionTGI.getStringVencimientos());
			if(liquidacionTGI.getRegistroExencionRegistroDeuda() != null) {
				this.getTaExencion().setText(liquidacionTGI.getRegistroExencionRegistroDeuda().getExencionRegistroDeuda().toString());
			}

			this.getTfFechaObligacion().setText(liquidacionTGI.getFechaEmision());
			this.getTaObligacion().setText(liquidacionTGI.getStringObligacion());
			this.getTfCuotaLiquidada().setText(liquidacionTGI.getCuotaLiquidada());
			this.getTfEstado().setText(liquidacionTGI.getEstado().toString());
			this.getTfMonto().setText(Conversor.getStringDeDouble(liquidacionTGI.getMonto()));
			if(liquidacionTGI.getFechaCancelacion() != null) {
				this.getTfFechaCancelacion().setText(liquidacionTGI.getFechaCancelacion());
			}
		}
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		LiquidacionTasa liquidacionTGI = (LiquidacionTasa) pObject;
		try {
			this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
			liquidacionTGI = (LiquidacionTasa) this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().getRegistroDeudaPorId(new Long(liquidacionTGI.getIdRegistroDeuda()));
		} catch(Exception e) {
			e.printStackTrace();
		}

		this.getElementoPila().getObjetos().set(0, liquidacionTGI);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMLiquidacionTGI";
	}

	@Override
	public String getNombreBean() {
		return "#{saic$grpTGI$ABMLiquidacionTGI$ABMLiquidacionTGI}";
	}

	@Override
	public long getSerialVersionUID() {
		return LiquidacionTasa.codigoTGI;
	}
}