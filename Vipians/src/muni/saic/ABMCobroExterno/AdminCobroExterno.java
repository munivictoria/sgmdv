
package muni.saic.ABMCobroExterno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.List;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;

import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.component.Upload;
import com.sun.rave.web.ui.model.UploadedFile;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.presentacion.abstracts.AdminPageBean;
import com.trascender.presentacion.abstracts.PaginatedTable;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.saic.recurso.filtros.FiltroCobroExterno;
import com.trascender.saic.recurso.persistent.CobroExterno;
import com.trascender.saic.recurso.persistent.CobroExterno.EntidadRecaudadora;

public class AdminCobroExterno extends AdminPageBean {

	private ObjectListDataProvider ldpCobroExterno = new ObjectListDataProvider();
	private Label lblNombre = new Label();
	private TextField tfNombre = new TextField();
	private UploadedFile file = null;
	private Label lblNombreArchivo = new Label();
	private Upload upload = new Upload();
	private Label lblEliminarArchivo = new Label();
	private Label lblAgregarArchivo = new Label();
	private TextField tfNombreArchivo = new TextField();

	private File archivo;

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	public Label getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(TextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Label getLblNombreArchivo() {
		return lblNombreArchivo;
	}

	public void setLblNombreArchivo(Label lblNombreArchivo) {
		this.lblNombreArchivo = lblNombreArchivo;
	}

	public Upload getUpload() {
		return upload;
	}

	public void setUpload(Upload upload) {
		this.upload = upload;
	}

	public Label getLblEliminarArchivo() {
		return lblEliminarArchivo;
	}

	public void setLblEliminarArchivo(Label lblEliminarArchivo) {
		this.lblEliminarArchivo = lblEliminarArchivo;
	}

	public Label getLblAgregarArchivo() {
		return lblAgregarArchivo;
	}

	public void setLblAgregarArchivo(Label lblAgregarArchivo) {
		this.lblAgregarArchivo = lblAgregarArchivo;
	}

	public TextField getTfNombreArchivo() {
		return tfNombreArchivo;
	}

	public void setTfNombreArchivo(TextField tfNombreArchivo) {
		this.tfNombreArchivo = tfNombreArchivo;
	}

	public ObjectListDataProvider getLdpCobroExterno() {
		return ldpCobroExterno;
	}

	public void setLdpCobroExterno(ObjectListDataProvider ldpCobroExterno) {
		this.ldpCobroExterno = ldpCobroExterno;
	}

	@Override
	public ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpCobroExterno();
	}

	@Override
	public PaginatedTable getPaginatedTable() {
		return this.getCommunicationSAICBean().getTablaCobroExterno();
	}

	@Override
	protected List getListaDelCommunication() {
		return this.getCommunicationSAICBean().getListaCobroExterno();
	}

	@Override
	protected void setListaDelCommunication(List lista) {
		this.getCommunicationSAICBean().setListaCobroExterno(lista);
	}

	@Override
	protected void limpiarObjetosUsados() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Object getObjectPorId(Object pObject) throws Exception {
		CobroExterno locCobroExterno = (CobroExterno) pObject;
		this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
		locCobroExterno = this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().getCobroExternoById(locCobroExterno.getIdCobroExterno());
		return locCobroExterno;
	}

	@Override
	protected FiltroAbstracto buscar(FiltroAbstracto pFiltro) throws Exception {
		this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
		return this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().findListaCobroExterno((FiltroCobroExterno) pFiltro);
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {

	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		System.out.println("mostrarEstado");
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {

	}

	public void procesarArchivo(ValueChangeEvent event) throws AbortProcessingException {
		Object value = event.getNewValue();
		if(value != null && value instanceof UploadedFile) {
			file = (UploadedFile) value;
		}
	}

	public void btnProcesarArchivo_action() throws RemoteException {
		System.out.println("mmmmmmmmmmmmmmmmmmm <--------------------------------------");
		this.guardarEstadoObjetosUsados();
		System.out.println(this.file.getOriginalName());
		if(!this.file.getOriginalName().equals("")) {
			System.out.println("ES BUENA <---------------------------------------------------");
			this.getTfNombreArchivo().setText(this.file.getOriginalName());

			try {

				InputStream locInputStream = this.file.getInputStream();

				BufferedReader br = null;

				br = new BufferedReader(new InputStreamReader(locInputStream));

				File locFile = new File("archivo.011");

				FileWriter locWriter = new FileWriter(locFile);

				String newLine = System.getProperty("line.separator");
				String line;
				while((line = br.readLine()) != null) {
					locWriter.write(line + newLine);
				}

				locWriter.close();
				System.out.println(locFile.toString());

				this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(this.getSessionBean1().getLlave());
				this.getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().procesarArchivoCobroExterno(locFile, EntidadRecaudadora.PAGOFACIL); // <--- pa
																																						// probar

				locFile.delete();
				this.getTfNombreArchivo().setText("");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected String getNombrePagina() {
		return "Cobros Externos";
	}

	@Override
	protected String getCasoNavegacion() {
		return "AdminCobroExterno";
	}

	@Override
	public String getNombreBean() {
		return "#{saic$ABMCobroExterno$AdminCobroExterno}";
	}

	@Override
	public long getSerialVersionUID() {
		return CobroExterno.serialVersionUID;
	}
}