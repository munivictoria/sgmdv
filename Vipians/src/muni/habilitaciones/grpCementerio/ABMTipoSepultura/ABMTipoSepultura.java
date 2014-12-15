package muni.habilitaciones.grpCementerio.ABMTipoSepultura;

import java.util.ArrayList;
import java.util.List;

import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;
/**
 * 
 * @author nico
 *
 */
public class ABMTipoSepultura extends ABMPageBean{
	
	private Label lbCodigo = new Label();
	private Label lbDescripcion = new Label();
	private Label lbMinimo = new Label();
	private Label lbValor = new Label();
	private Label lbPorcentual = new Label();
	
	public Label getLbCodigo() {
		return lbCodigo;
	}

	public void setLbCodigo(Label lbCodigo) {
		this.lbCodigo = lbCodigo;
	}

	public Label getLbDescripcion() {
		return lbDescripcion;
	}

	public void setLbDescripcion(Label lbDescripcion) {
		this.lbDescripcion = lbDescripcion;
	}

	public Label getLbMinimo() {
		return lbMinimo;
	}

	public void setLbMinimo(Label lbMinimo) {
		this.lbMinimo = lbMinimo;
	}

	public Label getLbValor() {
		return lbValor;
	}

	public void setLbValor(Label lbValor) {
		this.lbValor = lbValor;
	}

	public Label getLbPorcentual() {
		return lbPorcentual;
	}

	public void setLbPorcentual(Label lbPorcentual) {
		this.lbPorcentual = lbPorcentual;
	}
	
	private TextField tfCodigo = new TextField();
	private TextArea taDescripcion = new TextArea();
	private TextField tfMinimo = new TextField();
	private TextField tfValor = new TextField();
	private Checkbox cPorcentual = new Checkbox();
	
	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tfCodigo) {
		this.tfCodigo = tfCodigo;
	}

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea taDescripcion) {
		this.taDescripcion = taDescripcion;
	}

	public TextField getTfMinimo() {
		return tfMinimo;
	}

	public void setTfMinimo(TextField tfMinimo) {
		this.tfMinimo = tfMinimo;
	}

	public TextField getTfValor() {
		return tfValor;
	}

	public void setTfValor(TextField tfValor) {
		this.tfValor = tfValor;
	}

	public Checkbox getcPorcentual() {
		return cPorcentual;
	}

	public void setcPorcentual(Checkbox cPorcentual) {
		this.cPorcentual = cPorcentual;
	}
	
	private MessageGroup messageGroup1 = new MessageGroup();
	
	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup messageGroup1) {
		this.messageGroup1 = messageGroup1;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		TipoSepultura tipoSepultura = (TipoSepultura) this.obtenerObjetoDelElementoPila(0, TipoSepultura.class);
		List atributosDinamicos = (ArrayList) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
		
		Boolean porcentual = this.getcPorcentual().isChecked();
		tipoSepultura.setCodigo(getTextFieldValue(this.getTfCodigo()));
		tipoSepultura.setNombre(getTextAreaValue(this.getTaDescripcion()));
		tipoSepultura.setMinimo(getTextFieldValueDouble(this.getTfMinimo()));
		tipoSepultura.setValor(getTextFieldValueDouble(this.getTfValor()));
		tipoSepultura.setPorcentual(new Boolean(porcentual));
		
		atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		tipoSepultura.setListaAtributosDinamicos(atributosDinamicos);

		this.getElementoPila().getObjetos().set(0, tipoSepultura);
		this.getElementoPila().getObjetos().set(1, atributosDinamicos);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		TipoSepultura tipoSepultura = (TipoSepultura) this.obtenerObjetoDelElementoPila(0, TipoSepultura.class);
		List atributosDinamicos = null;

		if (tipoSepultura.getListaAtributosDinamicos() != null) {
			try {
				atributosDinamicos = (ArrayList) this.getComunicationBean().getRemoteSystemParametro()
						.getAtributosPorRecurso(TipoSepultura.serialVersionUID, tipoSepultura.getListaAtributosDinamicos(), null);
				this.getElementoPila().getObjetos().set(1, atributosDinamicos);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		this.getTfCodigo().setText(tipoSepultura.getCodigo());
		this.getTaDescripcion().setText(tipoSepultura.getNombre());
		this.getTfMinimo().setText(tipoSepultura.getMinimo());
		this.getTfValor().setText(tipoSepultura.getValor());
		this.getcPorcentual().setValue(new Boolean(tipoSepultura.isPorcentual()));
		

		atributosDinamicos = (List) this.obtenerObjetoDelElementoPila(1, ArrayList.class);
        panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos,"#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura}");
        panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);
        
        this.setListaDelCommunicationAtributosDinamicos(atributosDinamicos);
	}
	
	protected List getListaDelCommunicationAtributosDinamicos() {
		return this.getCommunicationHabilitacionesBean().getListaAtributosDinamicosTipoSepultura();
	}

	private void setListaDelCommunicationAtributosDinamicos(List lista) {
		this.getCommunicationHabilitacionesBean().setListaAtributosDinamicosTipoSepultura(lista);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMTipoSepultura";
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new TipoSepultura());
		ep.getObjetos().add(ind++, new ArrayList());// AtributosDinamicos
		
		return ep;
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		TipoSepultura tipoSepultura = (TipoSepultura) pObject;
		
		try{
			this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().setLlave(this.getSessionBean1().getLlave());
			tipoSepultura = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoCementerio().getTipoSepulturaById(tipoSepultura.getIdTipoAlicuota());
		} catch(Exception e){
			e.printStackTrace();
		}
		
		this.getElementoPila().getObjetos().set(0, tipoSepultura);
		this.getElementoPila().getObjetos().set(1, null);
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		TipoSepultura locTipoSepultura = this.obtenerObjetoDelElementoPila(0, TipoSepultura.class);
		this.getTablaLogs().getLdpLogs().setList(locTipoSepultura.getListaLogsAuditoria());
	}

	@Override
	public long getSerialVersionUID() {
		return TipoSepultura.serialVersionUID;
	}

	@Override
	public String getNombreBean(){
		return "#{habilitaciones$grpCementerio$ABMTipoSepultura$ABMTipoSepultura}";
	}
}
