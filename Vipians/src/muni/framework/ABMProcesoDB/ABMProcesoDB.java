package muni.framework.ABMProcesoDB;

import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.trascender.framework.recurso.persistent.ProcesoDB;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.navegacion.ElementoPila;

public class ABMProcesoDB extends ABMPageBean{
	
	private TextField tfNombre = new TextField();
	private TextField tfNombreProceso = new TextField();
	private TextArea taDescripcion = new TextArea();
	private TextArea taParametros = new TextArea();
	
	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public TextField getTfNombreProceso() {
		return tfNombreProceso;
	}

	public void setTfNombreProceso(TextField tfNombreProceso) {
		this.tfNombreProceso = tfNombreProceso;
	}

	public TextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(TextArea taDescripcion) {
		this.taDescripcion = taDescripcion;
	}

	public TextArea getTaParametros() {
		return taParametros;
	}

	public void setTaParametros(TextArea taParametros) {
		this.taParametros = taParametros;
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int i = 0;
		ep.getObjetos().add(i++, new ProcesoDB());//El Objetoset
		ep.getObjetos().add(i++, new String());//Parametros para la ejecucion.
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
		String parametros = getTextAreaValue(taParametros);
		getElementoPila().getObjetos().set(1, parametros);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		ProcesoDB locProceso = (ProcesoDB) obtenerObjetoDelElementoPila(0);
		taDescripcion.setText(locProceso.getDescripcion());
		tfNombre.setText(locProceso.getNombre());
		tfNombreProceso.setText(locProceso.getNombreProceso());
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		ProcesoDB locProceso = (ProcesoDB) pObject;
		this.getElementoPila().getObjetos().set(0, locProceso);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMProcesoDB";
	}

	@Override
	public String getNombreBean() {
		return "#{framework$ABMProcesoDB$ABMProcesoDB}";
	}

	@Override
	public long getSerialVersionUID() {
		return ProcesoDB.serialVersionUID;
	}
}
