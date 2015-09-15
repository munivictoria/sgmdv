package com.trascender.contabilidad.gui.abmIngresoTesoreria;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.contabilidad.reporte.interfaz.InterfazModuloContable;
import com.trascender.gui.framework.abmStandard.AdminController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.principal.ConexionReportes;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public class AdminMovimientoCajaIngreso extends AdminController<MovimientoCajaIngreso>{
	
	public AdminMovimientoCajaIngresoView view;
	public MovimientoCajaIngresoBusquedaModel busquedaModel = new MovimientoCajaIngresoBusquedaModel();
	public MovimientoCajaIngresoTableModel tableModel = new MovimientoCajaIngresoTableModel();
	
	public AdminMovimientoCajaIngreso(JFrame owner) throws Exception {
		this.view = new AdminMovimientoCajaIngresoView(owner);
		this.init();
	}
	
	public AdminMovimientoCajaIngreso(JDialog owner) throws Exception {
		this.view = new AdminMovimientoCajaIngresoView(owner);
		this.init();
	}

	protected void init() {
		super.init();
		this.setModels();
		this.setListeners();
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
		this.getView().getPnlPie().getBtnAgregar().setEnabled(false);
	}
	
	private void setModels(){
		this.getView().setBusquedaModel(this.getBusquedaModel());
		this.getView().setTableModel(this.getTableModel());
		
		this.getBusquedaModel().reiniciar();
		this.actualizarBusquedaView();
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesBusqueda().getBtnBuscar().addActionListener(new BtnBuscarListener(this));
		this.getView().getPnlBotonesBusqueda().getBtnReiniciar().addActionListener(new BtnReiniciarListener(this));
		this.getView().getPnlPie().getBtnEliminar().addActionListener(new BtnImprimirMovimientoCajaIngresoListener(this));
		this.getView().getPnlPie().getBtnAgregar().addActionListener(new BtnGenerarArchivoInterfaz(this));
	}
	
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<String> attFecha = new ArrayList<String>();
		List<JLabel> lblFecha = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfFechaDesde().getText());
		lblNulos.add(this.getView().getLblFechaDesde());
		
		attFecha.add(this.getView().getTfFechaDesde().getText());
		lblFecha.add(this.getView().getLblFechaDesde());
		
		attNulos.add(this.getView().getTfFechaHasta().getText());
		lblNulos.add(this.getView().getLblFechaHasta());
		
		attFecha.add(this.getView().getTfFechaHasta().getText());
		lblFecha.add(this.getView().getLblFechaHasta());
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
			listaErrores.addAll(Validador.validarFechas(attFecha, lblFecha));
			listaErrores.addAll(Validador.validarFechaNoMayorALaActual(this.getView().getTfFechaDesde().getText(), this.getView().getLblFechaDesde()));
			listaErrores.addAll(Validador.validarFechaNoMayorALaActual(this.getView().getTfFechaHasta().getText(), this.getView().getLblFechaHasta()));
		}
		catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			return false;
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.getAbmErrores().getView().setListaErrores(listaErrores);
			this.getAbmErrores().open();
		}
		
		return validacionOK;
	}
		
	@Override
	protected void actualizarBusquedaView() {
		this.getView().getTfFechaDesde().setValue(Conversor.getString(this.getBusquedaModel().getFechaEmisionDesde()));
		this.getView().getTfFechaHasta().setValue(Conversor.getString(this.getBusquedaModel().getFechaEmisionHasta()));
	}
	
	public void actualizarBusquedaModel() {
		MovimientoCajaIngresoBusquedaModel locModel = this.getView().getBusquedaModel();
		
		locModel.setFechaEmisionDesde(Conversor.getDate(this.getView().getTfFechaDesde().getText()));
		locModel.setFechaEmisionHasta(Conversor.getDate(this.getView().getTfFechaHasta().getText()));
			
		locModel.fireActualizarDatos();
	}
		
	public AdminMovimientoCajaIngresoView getView() {
		return view;
	}
	
	void generarArchivoInterfaz() throws Exception{
		final AdminMovimientoCajaIngreso controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					//TODO Aqui abrir dialogo de busqueda de archivo.
					JFileChooser chooser = new JFileChooser();
//				    FileNameExtensionFilter filter = new FileNameExtensionFilter(
//				        "JPG & GIF Images", "jpg", "gif");
//				    chooser.setFileFilter(filter);
				    int returnVal = chooser.showDialog(controller.getView(), "Guardar");
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				    	List<MovimientoCajaIngreso> locLista = controller.getTableModel().getListaObjetos();
					    InterfazModuloContable locInterfaz = new InterfazModuloContable(locLista);
					    locInterfaz.imprimirAArchivo(chooser.getSelectedFile());
				    }
				}
				catch (Exception ex) {
					ex.printStackTrace();
					AppManager.getInstance().showErrorMsg(controller.getView(), ex.getMessage());
				}
			}
		});
		locThread.start();
	}

	void buscar() throws Exception {
		final AdminMovimientoCajaIngreso controller = this;
		Thread locThread = new Thread(new Runnable(){
			public void run() {
				try {
					controller.actualizarBusquedaModel();
					controller.getView().iniBusqueda();
					List<MovimientoCajaIngreso> locLista = controller.getBusquedaModel().buscar();
					controller.getTableModel().setListaObjetos(locLista);
				}
				catch (Exception ex) {
					ex.printStackTrace();
					AppManager.getInstance().showErrorMsg(controller.getView(), ex.getMessage());
				}
				finally {
					controller.getView().finBusqueda();
				}
			}
		});
		locThread.start();
	}
	
	void imprimir() throws Exception {
		JasperReport masterReport = null;
		try {

			URL urlMaestro = this.getClass().getResource("/reports/Reporte_Ingreso_Tesoreria.jasper");
			
			masterReport = (JasperReport) JRLoader.loadObject(urlMaestro);
		} catch (JRException e) {
			System.out.println("Error cargando el reporte maestro: " + e.getMessage());
		}

		JasperReport subReport = null;
		try {

			URL urlSubReport = this.getClass().getResource("/reports/Reporte_Ingreso_Tesoreria_Subreporte.jasper");
			
			subReport = (JasperReport) JRLoader.loadObject(urlSubReport);
		} catch (JRException e) {
			System.out.println("Error cargando el subreporte: " + e.getMessage());
		}
		
		ConexionReportes.getInstance().conectar();
		
		URL urlImagen = this.getClass().getResource("/reports/logo.png");
		String locLogo = Conversor.getString(urlImagen);
		
		DateFormat df = DateFormat.getDateInstance();

		Map masterParams = new HashMap();
		masterParams.put("PAR_IMAGEN", locLogo);
		masterParams.put("PAR_FECHA_DESDE", df.parse(this.view.getTfFechaDesde().getText()));
		masterParams.put("PAR_FECHA_HASTA", df.parse(this.view.getTfFechaHasta().getText()));
		masterParams.put("PAR_CROSSTAB", subReport);
		
		JasperPrint masterPrint = null;
		
		try {
			masterPrint = JasperFillManager.fillReport(masterReport, masterParams, ConexionReportes.getInstance().getConnection());
			
		} catch (JRException e) {
			System.out.println("Error llenando el reporte maestro: " + e.getMessage());
			try {
				ConexionReportes.getInstance().getConnection().close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}	
		
		// Obtener la resolucion de pantalla del usuario
	    Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Dimension dimension = toolkit.getScreenSize();

		JDialog locDialog = new JDialog(this.view);
		JRViewer locViewer = new JRViewer(masterPrint);
		locDialog.add(locViewer);
		locDialog.pack();
		locDialog.setModal(true);
		locDialog.setSize(dimension.width, dimension.height);
		locDialog.setTitle("Vista previa de Ingreso de Tesorería");
		locDialog.setVisible(true);

		ConexionReportes.getInstance().desconectar();			
	}

	public MovimientoCajaIngresoBusquedaModel getBusquedaModel() {
		return busquedaModel;
	}

	public MovimientoCajaIngresoTableModel getTableModel() {
		return tableModel;
	}
	@Override
	protected void setBtnReiniciarListener() {
		super.setBtnReiniciarListener();
		this.getView().getPnlPie().getBtnEliminar().setEnabled(false);
		this.getView().getPnlPie().getBtnAgregar().setEnabled(false);
	}
}

class BtnBuscarListener implements ActionListener {
	private AdminMovimientoCajaIngreso controller;

	public BtnBuscarListener(AdminMovimientoCajaIngreso controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (this.controller.validarDatos()) {
				this.controller.buscar();
				this.controller.getView().getPnlPie().getBtnEliminar().setEnabled(true);
				this.controller.getView().getPnlPie().getBtnAgregar().setEnabled(true);
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}


class BtnReiniciarListener implements ActionListener {
	private AdminMovimientoCajaIngreso controller;

	public BtnReiniciarListener(AdminMovimientoCajaIngreso controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.setBtnReiniciarListener();
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnImprimirMovimientoCajaIngresoListener implements ActionListener {
	private AdminMovimientoCajaIngreso controller;

	public BtnImprimirMovimientoCajaIngresoListener(
			AdminMovimientoCajaIngreso controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.imprimir();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnGenerarArchivoInterfaz implements ActionListener {
	private AdminMovimientoCajaIngreso controller;
	public BtnGenerarArchivoInterfaz(
			AdminMovimientoCajaIngreso controller){
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			this.controller.generarArchivoInterfaz();
		} catch (Exception ex){
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}


