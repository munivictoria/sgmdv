
package muni.saic.ABMReliquidacion;

import jasper.ConstantesReportes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;

import net.sf.jasperreports.engine.JasperPrint;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.event.TableSelectPhaseListener;
import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.validadores.Validador;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;

/**
 * 
 * @author nico
 * 
 */
public class ABMActualizarDeuda extends ABMPageBean {

	@Override
	protected void _init() throws Exception {
		if(this.getListaDelCommunication() != null) {
			this.getLdpLiquidaciones().setList(this.getListaDelCommunication());
		}
		
//		tablePhaseListener = this.getTableSelectPhaseListener();

		this.getDtcFecha().setPattern("dd/MM/yyyy");
		this.getDtcFecha().setTimeZone(TimeZone.getDefault());
		this.getNcMonto().setPattern("$ #,##0.00");
	}

	private Label lbFechaVencimiento = new Label();
	private Label lbAplicarIntereses = new Label();
	private Label lbLiquidaciones = new Label();
	private TextField tfFechaVencimiento = new TextField();
	private Checkbox cbAplicarIntereses = new Checkbox();
	private Table tbLiquidaciones = new Table();
	private TableRowGroup tbrgLiquidaciones = new TableRowGroup();
	private ObjectListDataProvider ldpLiquidaciones = new ObjectListDataProvider();
	private TableColumn tcPeriodo = new TableColumn();
	private TableColumn tcObligacion = new TableColumn();
	private TableColumn tcVencimiento = new TableColumn();
	private TableColumn tcMonto = new TableColumn();
	private TableColumn tcEstado = new TableColumn();
	private StaticText stPeriodo = new StaticText();
	private TextArea taObligacion = new TextArea();
	private StaticText stVencimiento = new StaticText();
	private StaticText stMonto = new StaticText();
	private StaticText stEstado = new StaticText();
	private MessageGroup messageGroup1 = new MessageGroup();
	private Button btnActualizarDeuda = new Button();
	private Button btnActualizarEImprimirDeuda = new Button();
	private DateTimeConverter dtcFecha = new DateTimeConverter();
	private NumberConverter ncMonto = new NumberConverter();
	private Checkbox checkBoxSeleccion = new Checkbox();
	private TableColumn tcCheckBox = new TableColumn();
	private Set listaSeleccionados = new HashSet();
	
	
	public Set getListaSeleccionados() {
		return listaSeleccionados;
	}

	public void setListaSeleccionados(Set listaSeleccionados) {
		this.listaSeleccionados = listaSeleccionados;
	}
	
	public Checkbox getCheckBoxSeleccion() {
		return checkBoxSeleccion;
	}

	public void setCheckBoxSeleccion(Checkbox checkBoxSeleccion) {
		this.checkBoxSeleccion = checkBoxSeleccion;
	}

	public TableColumn getTcCheckBox() {
		return tcCheckBox;
	}

	public void setTcCheckBox(TableColumn tcCheckBox) {
		this.tcCheckBox = tcCheckBox;
	}

	public Button getBtnActualizarEImprimirDeuda() {
		return btnActualizarEImprimirDeuda;
	}

	public void setBtnActualizarEImprimirDeuda(Button btnActualizarEImprimirDeuda) {
		this.btnActualizarEImprimirDeuda = btnActualizarEImprimirDeuda;
	}

	public NumberConverter getNcMonto() {
		return ncMonto;
	}

	public void setNcMonto(NumberConverter ncMonto) {
		this.ncMonto = ncMonto;
	}

	public DateTimeConverter getDtcFecha() {
		return dtcFecha;
	}

	public void setDtcFecha(DateTimeConverter dtcFecha) {
		this.dtcFecha = dtcFecha;
	}

	public Button getBtnActualizarDeuda() {
		return btnActualizarDeuda;
	}

	public void setBtnActualizarDeuda(Button btnActualizarDeuda) {
		this.btnActualizarDeuda = btnActualizarDeuda;
	}

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	public Label getLbAplicarIntereses() {
		return lbAplicarIntereses;
	}

	public void setLbAplicarIntereses(Label lbAplicarIntereses) {
		this.lbAplicarIntereses = lbAplicarIntereses;
	}

	public Label getLbLiquidaciones() {
		return lbLiquidaciones;
	}

	public void setLbLiquidaciones(Label lbLiquidaciones) {
		this.lbLiquidaciones = lbLiquidaciones;
	}

	public Label getLbFechaVencimiento() {
		return lbFechaVencimiento;
	}

	public void setLbFechaVencimiento(Label lbFechaVencimiento) {
		this.lbFechaVencimiento = lbFechaVencimiento;
	}

	public TextField getTfFechaVencimiento() {
		return tfFechaVencimiento;
	}

	public void setTfFechaVencimiento(TextField tfFechaVencimiento) {
		this.tfFechaVencimiento = tfFechaVencimiento;
	}

	public Checkbox getCbAplicarIntereses() {
		return cbAplicarIntereses;
	}

	public void setCbAplicarIntereses(Checkbox cbAplicarIntereses) {
		this.cbAplicarIntereses = cbAplicarIntereses;
	}

	public Table getTbLiquidaciones() {
		return tbLiquidaciones;
	}

	public void setTbLiquidaciones(Table tbLiquidaciones) {
		this.tbLiquidaciones = tbLiquidaciones;
	}

	public TableRowGroup getTbrgLiquidaciones() {
		return tbrgLiquidaciones;
	}

	public void setTbrgLiquidaciones(TableRowGroup tbrgLiquidaciones) {
		this.tbrgLiquidaciones = tbrgLiquidaciones;
	}

	public ObjectListDataProvider getLdpLiquidaciones() {
		return ldpLiquidaciones;
	}

	public void setLdpLiquidaciones(ObjectListDataProvider ldpLiquidaciones) {
		this.ldpLiquidaciones = ldpLiquidaciones;
	}

	protected List getListaDelCommunication() {
		return this.getCommunicationSAICBean().getListaLiquidacionesActualizarDeuda();
	}

	protected void setListaDelCommunication(List lista) {
		this.getCommunicationSAICBean().setListaLiquidacionesActualizarDeuda(lista);
	}

	public TableColumn getTcPeriodo() {
		return tcPeriodo;
	}

	public void setTcPeriodo(TableColumn tcPeriodo) {
		this.tcPeriodo = tcPeriodo;
	}

	public TableColumn getTcObligacion() {
		return tcObligacion;
	}

	public void setTcObligacion(TableColumn tcObligacion) {
		this.tcObligacion = tcObligacion;
	}

	public TableColumn getTcVencimiento() {
		return tcVencimiento;
	}

	public void setTcVencimiento(TableColumn tcVencimiento) {
		this.tcVencimiento = tcVencimiento;
	}

	public TableColumn getTcMonto() {
		return tcMonto;
	}

	public void setTcMonto(TableColumn tcMonto) {
		this.tcMonto = tcMonto;
	}

	public TableColumn getTcEstado() {
		return tcEstado;
	}

	public void setTcEstado(TableColumn tcEstado) {
		this.tcEstado = tcEstado;
	}

	public StaticText getStPeriodo() {
		return stPeriodo;
	}

	public void setStPeriodo(StaticText stPeriodo) {
		this.stPeriodo = stPeriodo;
	}

	public TextArea getTaObligacion() {
		return taObligacion;
	}

	public void setTaObligacion(TextArea taObligacion) {
		this.taObligacion = taObligacion;
	}

	public StaticText getStVencimiento() {
		return stVencimiento;
	}

	public void setStVencimiento(StaticText stVencimiento) {
		this.stVencimiento = stVencimiento;
	}

	public StaticText getStMonto() {
		return stMonto;
	}

	public void setStMonto(StaticText stMonto) {
		this.stMonto = stMonto;
	}

	public StaticText getStEstado() {
		return stEstado;
	}

	public void setStEstado(StaticText stEstado) {
		this.stEstado = stEstado;
	}
	
	public String getCurrentRow1() {
		return tbrgLiquidaciones.getRowKey().getRowId();
	}

	public void setCurrentRow1(int row) {
	}

    private RowKey rowKeySeleccionado = null;

    public RowKey getRowKeySeleccionado() {
        return rowKeySeleccionado;
    }

    public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
        this.rowKeySeleccionado = rowKeySeleccionado;
    }
	
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new ArrayList()); // 0 lista liquidaciones seleccionadas
		ep.getObjetos().add(ind++, null); // 1 fecha nuevo vencimiento
		ep.getObjetos().add(ind++, null); // 2 aplicar intereses
		ep.getObjetos().add(ind++, new ArrayList()); // 3 seleccionadas
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		int ind = 0;
		List<LiquidacionTasa> listaLiquidaciones = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		Date fechaVencimiento = this.obtenerObjetoDelElementoPila(ind++, Date.class);
		Boolean aplicarIntereses = this.obtenerObjetoDelElementoPila(ind++, Boolean.class);

		fechaVencimiento = Conversor.getFechaCortaDeString(this.getTextFieldValue(this.tfFechaVencimiento));
		aplicarIntereses = this.getCbAplicarIntereses().isChecked();
		
		this.getCommunicationSAICBean().setSeleccionadosSeleccionMultipleActualizarDeuda(listaSeleccionados);
		
		ind = 0;
		this.getElementoPila().getObjetos().set(ind++, listaLiquidaciones);
		this.getElementoPila().getObjetos().set(ind++, fechaVencimiento);
		this.getElementoPila().getObjetos().set(ind++, aplicarIntereses);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		List<LiquidacionTasa> listaLiquidaciones = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		Date fechaVencimiento = this.obtenerObjetoDelElementoPila(ind++, Date.class);
		Boolean aplicarIntereses = this.obtenerObjetoDelElementoPila(ind++, Boolean.class);

		this.getTfFechaVencimiento().setText(Conversor.getStringDeFechaCorta(fechaVencimiento));
		this.getCbAplicarIntereses().setSelected(aplicarIntereses);
		this.getLdpLiquidaciones().setList(listaLiquidaciones);
	}

	public void setSelected(Object object) {
		Object objectSeleccionado = null;

		if(object == null) {
			objectSeleccionado = this.getLdpLiquidaciones().getObjects()[Integer.parseInt(this.getTbrgLiquidaciones().getRowKey().getRowId())];
			if(objectSeleccionado != null && this.getListaSeleccionados().contains(objectSeleccionado)) {// &&
				this.getListaSeleccionados().remove(objectSeleccionado);
				this.getCommunicationSAICBean().getSeleccionadosSeleccionMultipleActualizarDeuda().remove(objectSeleccionado);
			}
		} else if(this.getLdpLiquidaciones() != null && this.getLdpLiquidaciones().getObjects().length > 0) {
			objectSeleccionado = this.getListaDelCommunication().get(Integer.parseInt(object.toString()));
			if(objectSeleccionado != null && !this.getListaSeleccionados().contains(objectSeleccionado)) { // &&
				this.getListaSeleccionados().add(objectSeleccionado);
				this.getCommunicationSAICBean().getSeleccionadosSeleccionMultipleActualizarDeuda().add(objectSeleccionado);
			}
		}
	}

	public Object getSelected() {
		String sv = getCheckBoxSeleccion().getSelectedValue().toString();
		Object objectSeleccionado = this.getListaDelCommunication().get(Integer.parseInt(sv));// this.getObjectListDataProvider().getObjects()[Integer.parseInt(sv)];

		if(this.getCommunicationSAICBean().getSeleccionadosSeleccionMultipleActualizarDeuda().contains(objectSeleccionado)) {
			return sv;
		}
		return null;
	}

	public String getSelectedValue() {
		RowKey rowKey = tbrgLiquidaciones.getRowKey();
		if(rowKey != null) {
		}
		return (rowKey != null) ? rowKey.getRowId() : null;
	}
	
	private TableSelectPhaseListener getTableSelectPhaseListener() {
		return this.getCommunicationSAICBean().getTablePhaseListenerActualizarDeuda();
	}
	
	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		List<LiquidacionTasa> listaLiquidaciones = (List) pObject;
		this.setListaDelCommunication(listaLiquidaciones);
		this.getCommunicationSAICBean().setSeleccionadosSeleccionMultipleActualizarDeuda(new HashSet(listaLiquidaciones));
		this.listaSeleccionados.addAll(listaLiquidaciones);
		this.getLdpLiquidaciones().setList(listaLiquidaciones);
		this.getElementoPila().getObjetos().set(0, listaLiquidaciones);
	}
	
	public String btnActualizarEImprimirDeuda_action(){
		
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			try {

				// No validar si se esta consultando o eliminando.
				if(getController().guardaEstadoObjetosUsados()) {
					this.guardarEstadoObjetosUsados();
				}
				
				List<LiquidacionTasa> listaLiquidaciones = new ArrayList<LiquidacionTasa>(this.getCommunicationSAICBean().getSeleccionadosSeleccionMultipleActualizarDeuda());
				
				Date fechaActualizacion = this.obtenerObjetoDelElementoPila(1, Date.class);
				boolean aplicarIntereses = this.obtenerObjetoDelElementoPila(2, Boolean.class);
				
				List<LiquidacionTasa> locListaRetorno = new ArrayList<LiquidacionTasa>();
				getCommunicationSAICBean().getRemoteSystemReliquidacion().setLlave(getSessionBean1().getLlave());
				for (LiquidacionTasa cadaLiquidacion : listaLiquidaciones) {
					if (cadaLiquidacion instanceof LiquidacionTasaAgrupada) {
						LiquidacionTasaAgrupada agrupada = (LiquidacionTasaAgrupada) cadaLiquidacion;
						for (int i = 0 ; i < agrupada.getListaLiquidacionesTasa().size() ; i++){
							LiquidacionTasa cadaLiquidacionIndividual = agrupada.getListaLiquidacionesTasa().get(i);
							agrupada.getListaLiquidacionesTasa().set(i, getCommunicationSAICBean().getRemoteSystemReliquidacion().calcularIntereses(cadaLiquidacionIndividual, fechaActualizacion, aplicarIntereses, true));
						}
						locListaRetorno.add(agrupada);
					} else {
						locListaRetorno.add(getCommunicationSAICBean().getRemoteSystemReliquidacion().calcularIntereses(cadaLiquidacion, fechaActualizacion, aplicarIntereses, true));
					}
				}
				
				getCommunicationContabilidadBean().getRemoteSystemReportesContabilidad().setLlave(this.getSessionBean1().getLlave());
				List<LiquidacionTasaAgrupada> locListaAgrupada = Util.castearLista(locListaRetorno);
				JasperPrint jp = getCommunicationContabilidadBean().getRemoteSystemReportesContabilidad().getReporteLiquidacionDeuda(locListaAgrupada);
				subirReporteASesion("Estado de Deuda", ConstantesReportes.PDF, jp);
				this.getRequestBean1().setRefrescarTabla(this.getController().recargarPaginaAdmin());
				retorno = this.prepararParaVolver(this.getAccion());
			} catch(Exception ex) {
				log(getCasoNavegacion() + "_GuardarError:", ex);
				error(ex.getMessage());
				subirErrorEnReporteASesion();
			}

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}
	
	@Override
	protected String getCasoNavegacion() {
		return "ABMActualizarDeuda";
	}

	@Override
	public String getNombreBean() {
		return "#{saic$ABMReliquidacion$ABMActualizarDeuda}";
	}

	@Override
	public long getSerialVersionUID() {
		return 0;
	}
}