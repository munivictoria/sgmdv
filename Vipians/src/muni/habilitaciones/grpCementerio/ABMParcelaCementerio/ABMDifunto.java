
package muni.habilitaciones.grpCementerio.ABMParcelaCementerio;

import java.util.List;

import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.habilitaciones.recurso.persistent.cementerio.Difunto;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMDifunto extends ABMPageBean {

	private TextField tfPersona = new TextField();
	private TextField tfFechaDeceso = new TextField();
	private TextArea taCausaDeceso = new TextArea();

	private Label lblPersona = new Label();
	private Label lblFechaDeceso = new Label();
	private Label lblCausaDeceso = new Label();

	private Checkbox ckbInmunoinfecciosa = new Checkbox();
	private Checkbox ckbCremado = new Checkbox();
	private Checkbox ckbReducido = new Checkbox();

	private Button btnSeleccionarPersona = new Button();

	private MessageGroup messageGroup1 = new MessageGroup();

	public TextArea getTaCausaDeceso() {
		return taCausaDeceso;
	}

	public void setTaCausaDeceso(TextArea taCausaDeceso) {
		this.taCausaDeceso = taCausaDeceso;
	}

	public TextField getTfPersona() {
		return tfPersona;
	}

	public void setTfPersona(TextField tfPersona) {
		this.tfPersona = tfPersona;
	}

	public TextField getTfFechaDeceso() {
		return tfFechaDeceso;
	}

	public void setTfFechaDeceso(TextField tfFechaDeceso) {
		this.tfFechaDeceso = tfFechaDeceso;
	}

	public Label getLblPersona() {
		return lblPersona;
	}

	public void setLblPersona(Label lblPersona) {
		this.lblPersona = lblPersona;
	}

	public Label getLblFechaDeceso() {
		return lblFechaDeceso;
	}

	public void setLblFechaDeceso(Label lblFechaDeceso) {
		this.lblFechaDeceso = lblFechaDeceso;
	}

	public Label getLblCausaDeceso() {
		return lblCausaDeceso;
	}

	public void setLblCausaDeceso(Label lblCausaDeceso) {
		this.lblCausaDeceso = lblCausaDeceso;
	}

	public Checkbox getCkbInmunoinfecciosa() {
		return ckbInmunoinfecciosa;
	}

	public void setCkbInmunoinfecciosa(Checkbox ckbInmunoinfecciosa) {
		this.ckbInmunoinfecciosa = ckbInmunoinfecciosa;
	}

	public Checkbox getCkbCremado() {
		return ckbCremado;
	}

	public void setCkbCremado(Checkbox ckbCremado) {
		this.ckbCremado = ckbCremado;
	}

	public Checkbox getCkbReducido() {
		return ckbReducido;
	}

	public void setCkbReducido(Checkbox ckbReducido) {
		this.ckbReducido = ckbReducido;
	}

	public Button getBtnSeleccionarPersona() {
		return btnSeleccionarPersona;
	}

	public void setBtnSeleccionarPersona(Button btnSeleccionarPersona) {
		this.btnSeleccionarPersona = btnSeleccionarPersona;
	}

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	public ABMDifunto() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;
		ep.getObjetos().add(ind++, new Difunto());
		ep.getObjetos().add(ind++, new PersonaFisica());
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		Difunto difunto = this.obtenerObjetoDelElementoPila(0, Difunto.class);
		PersonaFisica persona = this.obtenerObjetoDelElementoPila(1, PersonaFisica.class);

		difunto.setCausa(getTextAreaValue(this.getTaCausaDeceso()));
		difunto.setFechaDeceso(getTextFieldValueDate(this.getTfFechaDeceso()));

		if(persona.getIdPersona() == -1)
			persona = null;
		difunto.setPersona(persona);

		difunto.setInmunoinfecciosa(this.getCkbInmunoinfecciosa().isChecked());
		difunto.setCremado(this.getCkbCremado().isChecked());
		difunto.setReducido(this.getCkbReducido().isChecked());

		this.getElementoPila().getObjetos().set(0, difunto);
		this.getElementoPila().getObjetos().set(1, persona);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		Difunto difunto = this.obtenerObjetoDelElementoPila(0, Difunto.class);
		PersonaFisica persona = this.obtenerObjetoDelElementoPila(1, PersonaFisica.class);

		if(this.getRequestBean1().getObjetoABM() != null) {
			difunto = (Difunto) this.getRequestBean1().getObjetoABM();
			persona = difunto.getPersona();

			this.getElementoPila().getObjetos().set(0, difunto);
			this.getElementoPila().getObjetos().set(1, persona);
		}

		difunto = this.obtenerObjetoDelElementoPila(0, Difunto.class);
		persona = this.obtenerObjetoDelElementoPila(1, PersonaFisica.class);

		this.getTaCausaDeceso().setText(difunto.getCausa());
		this.getTfFechaDeceso().setText(Conversor.getStringDeFechaCorta(difunto.getFechaDeceso()));

		if(persona != null && persona.getIdPersona() != -1) {
			this.getTfPersona().setText(persona);
		}

		this.getCkbInmunoinfecciosa().setSelected(difunto.isInmunoinfecciosa());
		this.getCkbCremado().setSelected(difunto.isCremado());
		this.getCkbReducido().setSelected(difunto.isReducido());
	}

	public String btnSeleccionarPersona_action() {
		return navegarParaSeleccionar("AdminPersonaFisica");
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMDifunto";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		Difunto difunto = this.obtenerObjetoDelElementoPila(0, Difunto.class);
		PersonaFisica persona = this.obtenerObjetoDelElementoPila(1, PersonaFisica.class);

		if(pObject instanceof PersonaFisica) {
			PersonaFisica personaSeleccionada = (PersonaFisica) pObject;

			List<Difunto> listaDifuntosABMParcelaCementerio = this.getCommunicationHabilitacionesBean().getListaDifuntos();

			boolean yaEstaEnListaDifuntosABMParcelaCementerio = false;
			for(Difunto cadaDifunto : listaDifuntosABMParcelaCementerio) {
				if(cadaDifunto.getPersona().equals(personaSeleccionada)) {
					yaEstaEnListaDifuntosABMParcelaCementerio = true;
					break;
				}
			}

			if(!yaEstaEnListaDifuntosABMParcelaCementerio) {
				persona = personaSeleccionada;
				difunto.setPersona(persona);
				this.getElementoPila().getObjetos().set(0, difunto);
				this.getElementoPila().getObjetos().set(1, persona);
			} else {
				warn("La Persona seleccionada ya se encuentra como Difunto en la lista de Difuntos de la Parcela");
			}
		}
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
	}

	@Override
	public String getNombreBean() {
		return "#{habilitaciones$grpCementerio$ABMParcelaCementerio$ABMDifunto}";
	}

	@Override
	public long getSerialVersionUID() {
		return Difunto.serialVersionUID;
	}
}