package muni.catastro.ABMParcela;

import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.catastro.recurso.persistent.EstadisticasIndec;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * @author danilo
 *
 */
public class ABMEstadisticasIndec extends ABMPageBean{
	
	private Label lblCantidadViviendas = new Label();
	private Label lblCantidadHabitaciones = new Label();
	private Label lblCantidadBanios = new Label();
	private Label lblCantidadCocinas = new Label();
	private Label lblCantidadGarages = new Label();
	private Label lblLocalesComerciales = new Label();
	private Label lblOtros = new Label();
	
	private TextField tfCantidadViviendas = new TextField();
	private TextField tfCantidadHabitaciones = new TextField();
	private TextField tfCantidadBanios = new TextField();
	private TextField tfCantidadCocinas = new TextField();
	private TextField tfCantidadGarages = new TextField();
	private TextField tfLocalesComerciales = new TextField();
	private TextField tfOtros = new TextField();
	
	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}
	
	public Label getLblCantidadViviendas() {
		return lblCantidadViviendas;
	}

	public void setLblCantidadViviendas(Label lblCantidadViviendas) {
		this.lblCantidadViviendas = lblCantidadViviendas;
	}

	public Label getLblCantidadHabitaciones() {
		return lblCantidadHabitaciones;
	}

	public void setLblCantidadHabitaciones(Label lblCantidadHabitaciones) {
		this.lblCantidadHabitaciones = lblCantidadHabitaciones;
	}

	public Label getLblCantidadBanios() {
		return lblCantidadBanios;
	}

	public void setLblCantidadBanios(Label lblCantidadBanios) {
		this.lblCantidadBanios = lblCantidadBanios;
	}

	public Label getLblCantidadCocinas() {
		return lblCantidadCocinas;
	}

	public void setLblCantidadCocinas(Label lblCantidadCocinas) {
		this.lblCantidadCocinas = lblCantidadCocinas;
	}

	public Label getLblCantidadGarages() {
		return lblCantidadGarages;
	}

	public void setLblCantidadGarages(Label lblCantidadGarages) {
		this.lblCantidadGarages = lblCantidadGarages;
	}

	public Label getLblLocalesComerciales() {
		return lblLocalesComerciales;
	}

	public void setLblLocalesComerciales(Label lblLocalesComerciales) {
		this.lblLocalesComerciales = lblLocalesComerciales;
	}

	public Label getLblOtros() {
		return lblOtros;
	}

	public void setLblOtros(Label lblOtros) {
		this.lblOtros = lblOtros;
	}

	public TextField getTfCantidadViviendas() {
		return tfCantidadViviendas;
	}

	public void setTfCantidadViviendas(TextField tfCantidadViviendas) {
		this.tfCantidadViviendas = tfCantidadViviendas;
	}

	public TextField getTfCantidadHabitaciones() {
		return tfCantidadHabitaciones;
	}

	public void setTfCantidadHabitaciones(TextField tfCantidadHabitaciones) {
		this.tfCantidadHabitaciones = tfCantidadHabitaciones;
	}

	public TextField getTfCantidadBanios() {
		return tfCantidadBanios;
	}

	public void setTfCantidadBanios(TextField tfCantidadBanios) {
		this.tfCantidadBanios = tfCantidadBanios;
	}

	public TextField getTfCantidadCocinas() {
		return tfCantidadCocinas;
	}

	public void setTfCantidadCocinas(TextField tfCantidadCocinas) {
		this.tfCantidadCocinas = tfCantidadCocinas;
	}

	public TextField getTfCantidadGarages() {
		return tfCantidadGarages;
	}

	public void setTfCantidadGarages(TextField tfCantidadGarages) {
		this.tfCantidadGarages = tfCantidadGarages;
	}

	public TextField getTfLocalesComerciales() {
		return tfLocalesComerciales;
	}

	public void setTfLocalesComerciales(TextField tfLocalesComerciales) {
		this.tfLocalesComerciales = tfLocalesComerciales;
	}

	public TextField getTfOtros() {
		return tfOtros;
	}

	public void setTfOtros(TextField tfOtros) {
		this.tfOtros = tfOtros;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new EstadisticasIndec());

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		EstadisticasIndec estadisticasIndec = (EstadisticasIndec) this.obtenerObjetoDelElementoPila(0, EstadisticasIndec.class);

		estadisticasIndec.setCantidadBanios(getTextFieldValueInteger(this.getTfCantidadBanios()));
		estadisticasIndec.setCantidadCocinas(getTextFieldValueInteger(this.getTfCantidadCocinas()));
		estadisticasIndec.setCantidadGarages(getTextFieldValueInteger(this.getTfCantidadGarages()));
		estadisticasIndec.setCantidadHabitaciones(getTextFieldValueInteger(this.getTfCantidadHabitaciones()));
		estadisticasIndec.setCantidadViviendas(getTextFieldValueInteger(this.getTfCantidadViviendas()));
		estadisticasIndec.setLocalesComerciales(getTextFieldValueInteger(this.getTfLocalesComerciales()));
		estadisticasIndec.setOtros(getTextFieldValueInteger(this.getTfOtros()));
		
		this.getElementoPila().getObjetos().set(0, estadisticasIndec);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		EstadisticasIndec estadisticasIndec = (EstadisticasIndec) this.obtenerObjetoDelElementoPila(0, EstadisticasIndec.class);
		
		if(estadisticasIndec != null) {
			if (estadisticasIndec.getCantidadBanios() != null) {
				this.getTfCantidadBanios().setText(estadisticasIndec.getCantidadBanios());
			}
			if(estadisticasIndec.getCantidadCocinas() != null) {
				this.getTfCantidadCocinas().setText(estadisticasIndec.getCantidadCocinas());
			}
			if(estadisticasIndec.getCantidadGarages() != null) {
				this.getTfCantidadGarages().setText(estadisticasIndec.getCantidadGarages());
			}
			if(estadisticasIndec.getCantidadHabitaciones() != null) {
				this.getTfCantidadHabitaciones().setText(estadisticasIndec.getCantidadHabitaciones());
			}
			if(estadisticasIndec.getCantidadViviendas() != null) {
				this.getTfCantidadViviendas().setText(estadisticasIndec.getCantidadViviendas());
			}
			if(estadisticasIndec.getLocalesComerciales() != null) {
				this.getTfLocalesComerciales().setText(estadisticasIndec.getLocalesComerciales());
			}
			if(estadisticasIndec.getOtros() != null) {
				this.getTfOtros().setText(estadisticasIndec.getOtros());
			}
		}
		
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		EstadisticasIndec estadisticaIndec = null;
		if(pObject instanceof EstadisticasIndec) {
			if(pObject != null) {
				estadisticaIndec = (EstadisticasIndec) pObject;
				this.getElementoPila().getObjetos().set(0, estadisticaIndec);
			}
		}
		
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		EstadisticasIndec estadisticasIndec = (EstadisticasIndec) pObject;

		this.getElementoPila().getObjetos().set(0, estadisticasIndec);
		
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMEstadisticasIndec";
	}

	@Override
	public String getNombreBean() {
		return "#{catastro$ABMParcela$ABMEstadisticasIndec}";
	}

	@Override
	public long getSerialVersionUID() {
		return EstadisticasIndec.serialVersionUID;
	}

}
