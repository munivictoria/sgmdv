package com.trascender.contabilidad.gui.abmAsientoContable.abm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmAsientoContable.AsientoContableABMModel;
import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.abmLineaAsientoContable.LineaAsientoContableTableModel;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.LineaAsientoContable;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public class CargarAsientoContable extends ABMAsientoContable {
	
	private CargarAsientoContableView view;
	private AsientoContableABMModel abmModel;
	private LineaAsientoContableTableModel tableModel = new LineaAsientoContableTableModel();
	LineaAsientoContable lineaAsientoContable;
	
	public CargarAsientoContable(JDialog owner, AsientoContableABMModel abmModel) throws Exception {
		this.view = new CargarAsientoContableView(owner);
		this.abmModel = abmModel;
		this.lineaAsientoContable = new LineaAsientoContable();
		this.init();
	}

	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionCuenta().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaListener(this));
		this.getView().getPnlBotonesSeleccionCuenta().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaListener(this));
		
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAceptarAsientoContableListener(this));
	}
	
	

	@Override
	public LineaAsientoContableTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	public CargarAsientoContableView getView() {
		return this.view;
	}
	
	@Override
	public AsientoContableABMModel getAbmModel() {
		return this.abmModel;
	}


	
	/**
	 * Selecciona una cuenta contable para asientos
	 * @throws Exception
	 */
	void seleccionarCuenta() throws Exception {
		AdminCuenta adminCuenta = new AdminCuenta(this.getView(),true);
		Cuenta locCuenta = adminCuenta.openSelect();
		
		if (locCuenta != null) {
			Cuenta cuenta = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().getCuentaByID(locCuenta.getIdCuenta());
			ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable().validarCuenta(cuenta);
			this.getLineaAsientoContable().setCuenta(cuenta);
			this.getView().getAbmModel().getLineaAsientoContable().add(this.getLineaAsientoContable());
			this.actualizarABMModel();
			this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getLineaAsientoContable().getCuenta()));
//			this.getView().getTfImporteHaber().setValue(0.00);
//			this.getView().getTfImporteDebe().setValue(0.00);
		}
	}
	
	void limpiarCuenta() throws Exception {
		this.getLineaAsientoContable().setCuenta(null);
		this.actualizarABMModel();
		this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getLineaAsientoContable().getCuenta()));
	}
	
	void agregarAsientoContable() throws Exception {
		if (validarDatosCargarAsientoContable()) {
			if (this.validarImportes()) {
				if (!this.getView().getTfImporteDebe().getValue().equals("") || !this.getView().getTfImporteDebe().getValue().equals(" ")) {
					this.getLineaAsientoContable().setImporteDebe(Conversor.getDouble(this.getView().getTfImporteDebe().getValue().toString()));
				}
				if (!this.getView().getTfImporteHaber().getValue().equals("") || !this.getView().getTfImporteHaber().getValue().equals(" ")) {
					this.getLineaAsientoContable().setImporteHaber(Conversor.getDouble(this.getView().getTfImporteHaber().getValue().toString()));
				}
				this.getLineaAsientoContable().setAsientoContable(this.getView().getAbmModel().getObjetoABM());
				
				this.getView().getAbmModel().getObjetoABM().getLineasAsientoContable().add(this.getLineaAsientoContable());
				this.setOperacionRealizada(true);
				this.close();
			}
		}
	}
	
	public boolean validarImportes() {
		boolean validacionOK = true;
		
		List<Object> attDecimal = new ArrayList<Object>();
		List<JLabel> lblDecimal = new ArrayList<JLabel>();
		
		List<String> attPositivo = new ArrayList<String>();
		List<JLabel> lblPositivo = new ArrayList<JLabel>();
		
//		List<Object> attDebe = new ArrayList<Object>();
//		List<JLabel> lblDebe = new ArrayList<JLabel>();
//
//		List<Object> attHaber = new ArrayList<Object>();
//		List<JLabel> lblHaber = new ArrayList<JLabel>();
		
		this.getView().getLblImporteDebe().setForeground(Color.BLACK);
		this.getView().getLblImporteHaber().setForeground(Color.BLACK);
		try {
			//Si alguno de los dos campos esta vacio, tira un error
			if (this.getView().getTfImporteDebe().getValue().toString().trim().equals("") || 
					this.getView().getTfImporteHaber().getValue().toString().trim().equals("")) {
				List<String> locListaErrores = new ArrayList<String>();
				validacionOK = false;
				locListaErrores.add("Los campos 'Importe Debe' e 'Importe Haber' no pueden quedar vacíos");
				this.mostrarErroresValidacion(locListaErrores);
			} else{
				//Si los dos campos estan en cero, tira un error
				if ((Conversor.getFloat(this.getView().getTfImporteDebe().getValue().toString())==0) && 
						(Conversor.getFloat(this.getView().getTfImporteHaber().getValue().toString())==0)) {
					List<String> locListaErrores = new ArrayList<String>();
					validacionOK = false;
					locListaErrores.add("Los campos 'Importe Debe' e 'Importe Haber' no pueden quedar ambos en cero");
					this.mostrarErroresValidacion(locListaErrores);
				}
				
				//Si los dos campos tienen valores mayores que cero, tira error
				if((Conversor.getFloat(this.getView().getTfImporteDebe().getValue().toString())>0) && 
						(Conversor.getFloat(this.getView().getTfImporteHaber().getValue().toString())>0)){
					List<String> locListaErrores = new ArrayList<String>();
					validacionOK = false;
					locListaErrores.add("Los campos 'Importe Debe' e 'Importe Haber' no pueden ser ambos mayores a cero");
					this.mostrarErroresValidacion(locListaErrores);
				}
			}
		} catch (Exception e) {
			List<String> locListaErrores = new ArrayList<String>();
			validacionOK = false;
			locListaErrores.add("Los campos 'Importe Debe' e 'Importe Haber' deben ser numéricos");
			this.mostrarErroresValidacion(locListaErrores);
		}
		
		
		attDecimal.add(this.getView().getTfImporteDebe().getValue());
		lblDecimal.add(this.getView().getLblImporteDebe());
		attPositivo.add(this.getView().getTfImporteDebe().getValue().toString());
		lblPositivo.add(this.getView().getLblImporteDebe());

		attDecimal.add(this.getView().getTfImporteHaber().getValue());
		lblDecimal.add(this.getView().getLblImporteHaber());
		attPositivo.add(this.getView().getTfImporteHaber().getValue().toString());
		lblPositivo.add(this.getView().getLblImporteHaber());
		
		
		List<String> listaErrores = new ArrayList<String>();

		try {
//			listaErrores.addAll(Validador.validarImportesDebeHaber(attDebe, lblDebe, attHaber, lblHaber));
			listaErrores.addAll(Validador.validarDecimales(attDecimal, lblDecimal));
			listaErrores.addAll(Validador.validarPositivos(attPositivo, lblPositivo));
		} catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(),ex.getMessage());
		}

		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.mostrarErroresValidacion(listaErrores);
		}

		return validacionOK;
	}
	
	public boolean validarDatosCargarAsientoContable() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		attNulos.add(this.getView().getTfCuenta().getText());
		lblNulos.add(this.getView().getLblCuenta());
		
		List<String> listaErrores = new ArrayList<String>();
		
		try {
			listaErrores.addAll(Validador.validarNulos(attNulos, lblNulos));
		} catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.mostrarErroresValidacion(listaErrores);
		}
		
		return validacionOK;
	}

	public LineaAsientoContable getLineaAsientoContable() {
		return lineaAsientoContable;
	}
	
}


/**
 * Este listener es utilizado cuando se selecciona el botón de seleccionar cuenta 
 * @author Marina
 *
 */
class BtnSeleccionarCuentaListener implements ActionListener {
	private CargarAsientoContable controller;
	
	public BtnSeleccionarCuentaListener(CargarAsientoContable controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			this.controller.seleccionarCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnLimpiarCuentaListener implements ActionListener {
	private CargarAsientoContable controller;
	
	public BtnLimpiarCuentaListener(CargarAsientoContable controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			this.controller.limpiarCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
	
}

class BtnAceptarAsientoContableListener implements ActionListener {
	private CargarAsientoContable controller;
	
	public BtnAceptarAsientoContableListener(CargarAsientoContable controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarAsientoContable();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

