package muni.saic.ABMReliquidacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.NumberConverter;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;

public class ABMNotificacion extends ABMPageBean {
	
	private TextField tfFechaNotificacion = new TextField();
	private TextField tfFechaApremio = new TextField();
	private TextArea taComentario = new TextArea();
	private Table tbLiquidaciones = new Table();
	private TableRowGroup tbrgLiquidaciones = new TableRowGroup();
	private ObjectListDataProvider ldpLiquidaciones = new ObjectListDataProvider();
	private TableColumn tcCheckBox = new TableColumn();
	private Checkbox checkBoxSeleccion = new Checkbox();
	private DateTimeConverter dtcFecha = new DateTimeConverter();
	private NumberConverter ncMonto = new NumberConverter();

	public void _init() {
		if(this.getListaDelCommunication() != null) {
			this.getLdpLiquidaciones().setList(this.getListaDelCommunication());
		}
		
		this.getDtcFecha().setPattern("dd/MM/yyyy");
		this.getDtcFecha().setTimeZone(TimeZone.getDefault());
		this.getNcMonto().setPattern("$ #,##0.00");
	}

	public TextArea getTaComentario() {
		return taComentario;
	}



	public void setTaComentario(TextArea taComentario) {
		this.taComentario = taComentario;
	}



	public DateTimeConverter getDtcFecha() {
		return dtcFecha;
	}

	public void setDtcFecha(DateTimeConverter dtcFecha) {
		this.dtcFecha = dtcFecha;
	}

	public NumberConverter getNcMonto() {
		return ncMonto;
	}

	public void setNcMonto(NumberConverter ncMonto) {
		this.ncMonto = ncMonto;
	}

	public TableColumn getTcCheckBox() {
		return tcCheckBox;
	}

	public void setTcCheckBox(TableColumn tcCheckBox) {
		this.tcCheckBox = tcCheckBox;
	}

	public ObjectListDataProvider getLdpLiquidaciones() {
		return ldpLiquidaciones;
	}

	public void setLdpLiquidaciones(ObjectListDataProvider ldpLiquidaciones) {
		this.ldpLiquidaciones = ldpLiquidaciones;
	}

	public TableRowGroup getTbrgLiquidaciones() {
		return tbrgLiquidaciones;
	}

	public void setTbrgLiquidaciones(TableRowGroup tbrgLiquidaciones) {
		this.tbrgLiquidaciones = tbrgLiquidaciones;
	}

	public Table getTbLiquidaciones() {
		return tbLiquidaciones;
	}

	public void setTbLiquidaciones(Table tbLiquidaciones) {
		this.tbLiquidaciones = tbLiquidaciones;
	}

	public TextField getTfFechaNotificacion() {
		return tfFechaNotificacion;
	}

	public void setTfFechaNotificacion(TextField tfFechaNotificacion) {
		this.tfFechaNotificacion = tfFechaNotificacion;
	}

	public TextField getTfFechaApremio() {
		return tfFechaApremio;
	}

	public void setTfFechaApremio(TextField tfFechaApremio) {
		this.tfFechaApremio = tfFechaApremio;
	}
	
	public Checkbox getCheckBoxSeleccion() {
		return checkBoxSeleccion;
	}

	public void setCheckBoxSeleccion(Checkbox checkBoxSeleccion) {
		this.checkBoxSeleccion = checkBoxSeleccion;
	}

	public Object getSelected() {
		String sv = getCheckBoxSeleccion().getSelectedValue().toString();
		Object objectSeleccionado = this.getListaDelCommunication().get(Integer.parseInt(sv));// this.getObjectListDataProvider().getObjects()[Integer.parseInt(sv)];

		if(this.getCommunicationSAICBean().getSeleccionadosSeleccionMultipleNotificacion().contains(objectSeleccionado)) {
			return sv;
		}
		return null;
	}
	
	public String getCurrentRow1() {
		return tbrgLiquidaciones.getRowKey().getRowId();
	}

	public void setCurrentRow1(int row) {
	}
	
	public void setSelected(Object object) {
		Object objectSeleccionado = null;

		if(object == null) {
			objectSeleccionado = this.getLdpLiquidaciones().getObjects()[Integer.parseInt(this.getTbrgLiquidaciones().getRowKey().getRowId())];
			if(objectSeleccionado != null && this.getListaSeleccionados().contains(objectSeleccionado)) {// &&
				this.getListaSeleccionados().remove(objectSeleccionado);
				this.getCommunicationSAICBean().getSeleccionadosSeleccionMultipleNotificacion().remove(objectSeleccionado);
			}
		} else if(this.getLdpLiquidaciones() != null && this.getLdpLiquidaciones().getObjects().length > 0) {
			objectSeleccionado = this.getListaDelCommunication().get(Integer.parseInt(object.toString()));
			if(objectSeleccionado != null && !this.getListaSeleccionados().contains(objectSeleccionado)) { // &&
				this.getListaSeleccionados().add(objectSeleccionado);
				this.getCommunicationSAICBean().getSeleccionadosSeleccionMultipleNotificacion().add(objectSeleccionado);
			}
		}
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new ArrayList()); // 0 lista liquidaciones seleccionadas
		ep.getObjetos().add(ind++, null); // 1 Fecha Notificacion
		ep.getObjetos().add(ind++, null); // 2 Fecha apremio
		ep.getObjetos().add(ind++, new String());//3 Comentario
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		Date fechaNotificacion = Conversor.getFechaCortaDeString(this.getTextFieldValue(this.tfFechaNotificacion));
		Date fechaApremio = Conversor.getFechaCortaDeString(this.getTextFieldValue(this.tfFechaApremio));
		String comentario = this.getTextAreaValue(this.taComentario);
		this.getElementoPila().getObjetos().set(1, fechaNotificacion);
		this.getElementoPila().getObjetos().set(2, fechaApremio);
		this.getElementoPila().getObjetos().set(3, comentario);
		
		this.getCommunicationSAICBean().setSeleccionadosSeleccionMultipleNotificacion(listaSeleccionados);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		List<LiquidacionTasa> listaLiquidaciones = this.obtenerObjetoDelElementoPila(ind++, ArrayList.class);
		Date fechaVencimiento = (Date) this.obtenerObjetoDelElementoPila(ind++);
		Date fechaApremio = (Date) this.obtenerObjetoDelElementoPila(ind++);
		String comentario = (String) this.obtenerObjetoDelElementoPila(ind++);
		
		this.getTfFechaNotificacion().setText(Conversor.getStringDeFechaCorta(fechaVencimiento));
		this.getTfFechaApremio().setText(Conversor.getStringDeFechaCorta(fechaApremio));
		this.getTaComentarioLogAuditoria().setText(comentario);
		this.getLdpLiquidaciones().setList(listaLiquidaciones);
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		List<LiquidacionTasa> listaLiquidaciones = (List) pObject;
		this.setListaDelCommunication(listaLiquidaciones);
		this.getCommunicationSAICBean().setSeleccionadosSeleccionMultipleNotificacion(new HashSet(listaLiquidaciones));
		this.listaSeleccionados.addAll(listaLiquidaciones);
		this.getLdpLiquidaciones().setList(listaLiquidaciones);
		this.getElementoPila().getObjetos().set(0, listaLiquidaciones);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMNotificacion";
	}

	@Override
	public String getNombreBean() {
		return "#{saic$ABMReliquidacion$ABMNotificacion}";
	}

	@Override
	public long getSerialVersionUID() {
		return 0;
	}
	
	protected List getListaDelCommunication() {
		return this.getCommunicationSAICBean().getListaLiquidacionesNotificar();
	}

	protected void setListaDelCommunication(List lista) {
		this.getCommunicationSAICBean().setListaLiquidacionesNotificar(lista);
	}
	
	private Set listaSeleccionados = new HashSet();
	
	
	public Set getListaSeleccionados() {
		return listaSeleccionados;
	}

	public void setListaSeleccionados(Set listaSeleccionados) {
		this.listaSeleccionados = listaSeleccionados;
	}

}
