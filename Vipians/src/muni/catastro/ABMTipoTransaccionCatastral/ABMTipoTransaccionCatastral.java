/*
 * ModificarTipoTransaccionCatastral.java
 *
 * Created on 19 de octubre de 2006, 15:37
 * Copyright mariano
 */

package muni.catastro.ABMTipoTransaccionCatastral;

import com.sun.rave.web.ui.component.HiddenField;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming events.
 * </p>
 */
public class ABMTipoTransaccionCatastral extends ABMPageBean {

	private TextField tfNombre = new TextField();

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tf) {
		this.tfNombre = tf;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	// CAMBIAR: Constantes que varian segun la pagina.
	// cantidad de objetos administrados por la pagina
	// private final int CANTIDAD_OBJETOS = 1;
	// nombre a mostrar en la ruta de la operacion.
	private final String NOMBRE_PAGINA = "Modificar Tipo de Transacci\363n Catastral";
	// nombre del caso de navegacion para llegar a esta pagina.
	private final String CASO_NAVEGACION = "ModificarTipoTransaccionCatastral";
	// nombre del caso de navegacion para llegar a la pagina de caducidad
	private final String CASO_NAV_CADUCIDAD = "PaginaCaducidad";
	// nombre del caso de navegacion para llegar a la pagina que se debe
	// mostrar al salir de la pagina de caducidad
	private final String CASO_NAV_POST_CADUCIDAD = "Main";
	// es una pagina que no necesita idSubSesion
	// Inicia una sub sesion.
	private final boolean PUEDE_SER_PAGINA_INICIAL = false;

	// </editor-fold>donde e

	// <editor-fold defaultstate="collapsed" desc="Metodos para CAMBIAR">
	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		// ep.getObjetos().add(ind++, this.getTipoTransaccionCatastralABM());

		return ep;
	}

	private void setObjetosEnPagina() {
		int ind = 0;

		// CAMBIAR: settear en la pagina (haciendo cast) los objetos administrados por ella
		// this.setTipoTransaccionCatastralABM((TipoTransaccionCatastral) this.obtenerObjetoDelElementoPila(ind++, TipoTransaccionCatastral.class));
	}

	@Override
	protected void guardarEstadoObjetosUsados() {

		// CAMBIAR: Obtener los valores de los campos y
		// asignarlos a los atributos de los objetos de la pagina
		// TipoTransaccionCatastral tipoTransaccionCatastral = new TipoTransaccionCatastral();

		Object id = this.getHidIdObjeto().getText();
		Object nombre = this.getTfNombre().getText();

		// if (id != null && id != "") tipoTransaccionCatastral.setIdTipoTransaccionCatastral( new Long(id.toString()).longValue() );
		// if (nombre != null && nombre != "") tipoTransaccionCatastral.setNombre(nombre.toString());

		// REVEER... El estado debe ser igual al anterior, aunque para modificarlo
		// debe estar activo.

		// this.getElementoPila().getObjetos().set(0, tipoTransaccionCatastral);

	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		// CAMBIAR: Crear una instancia por cada objeto manejado en la pagina
		// TipoTransaccionCatastral tipoTransaccionCatastral = new TipoTransaccionCatastral();

		// CAMBIAR: Obtener datos del Request y asignarlos a los textField
		if(this.getRequestBean1().getObjetoABM() != null) {
			// tipoTransaccionCatastral = (TipoTransaccionCatastral) this.getRequestBean1().getObjetoABM();
			// this.setTipoTransaccionCatastralABM(tipoTransaccionCatastral);
			// this.getElementoPila().getObjetos().set(0, tipoTransaccionCatastral);
		}

		if(this.getElementoPila().getObjetos() != null && !this.getElementoPila().getObjetos().isEmpty()) {
			// CAMBIAR:
			// tipoTransaccionCatastral = (TipoTransaccionCatastral) this.obtenerObjetoDelElementoPila(0, TipoTransaccionCatastral.class);
		}

		// this.getHidIdObjeto().setText(new Long(tipoTransaccionCatastral.getIdTipoTransaccionCatastral()));
		// this.getTfNombre().setText(tipoTransaccionCatastral.getNombre());
	}

	// </editor-fold>

	private HiddenField hidIdObjeto = new HiddenField();

	public HiddenField getHidIdObjeto() {
		return hidIdObjeto;
	}

	public void setHidIdObjeto(HiddenField hf) {
		this.hidIdObjeto = hf;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	// </editor-fold>

	/**
	 * <p>
	 * Construir una instancia de bean de página.
	 * </p>
	 */
	public ABMTipoTransaccionCatastral() {
	}

	@Override
	protected String getNombrePagina() {
		return "Tipo Transaccion Catastral";
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMTipoTransaccionCatastral";
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getNombreBean() {
		return null;
	}

	@Override
	public long getSerialVersionUID() {
		return 0;
	}
}