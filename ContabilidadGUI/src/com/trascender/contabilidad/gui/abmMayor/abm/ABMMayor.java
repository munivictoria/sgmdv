package com.trascender.contabilidad.gui.abmMayor.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.trascender.contabilidad.gui.abmCuenta.AdminCuenta;
import com.trascender.contabilidad.gui.abmLineaMayor.LineaMayorTableModel;
import com.trascender.contabilidad.gui.abmMayor.MayorABMModel;
import com.trascender.contabilidad.recurso.persistent.Cuenta;
import com.trascender.contabilidad.recurso.persistent.LineaMayor;
import com.trascender.contabilidad.recurso.persistent.Mayor;
import com.trascender.contabilidad.recurso.persistent.Mayor.Tipo;
import com.trascender.gui.framework.abmStandard.ABMController;
import com.trascender.gui.framework.exception.GuiException;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;
import com.trascender.gui.framework.util.Conversor;
import com.trascender.gui.framework.util.Validador;

public abstract class ABMMayor extends ABMController<Mayor> {

	public abstract ABMMayorView getView();
	public abstract MayorABMModel getAbmModel(); 
	public abstract LineaMayorTableModel getTableModel();
	private Tipo tipoMayor;
	
	@Override
	protected void init() {
		super.init();
		
		this.getView().getTfCuenta().setEditable(false);
		
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		this.getView().setAbmModel(this.getAbmModel());
		this.getView().getPnlTabla().getTblDatos().setModel(this.getTableModel());
		
		this.getView().getCbTipo().setModel(new TDefaultComboBoxModel(Mayor.Tipo.values()));
	}
	
	private void setListeners() {
		this.getView().getPnlBotonesSeleccionCuenta().getBtnSeleccionar().addActionListener(new BtnSeleccionarCuentaListener(this));
		this.getView().getPnlBotonesSeleccionCuenta().getBtnLimpiar().addActionListener(new BtnLimpiarCuentaListener(this));
		
		this.getView().getBtnGenerarMayor().addActionListener(new BtnGenerarMayorListener(this));
		
		this.getView().getCbTipo().addActionListener(new CbTipoDeMayor(this));
	}
	
	@Override
	public void actualizarABMModel() {
		this.getAbmModel().setMes(Conversor.getInteger(this.getView().getTfMes().getText()));
		this.getAbmModel().setAnio(Conversor.getInteger(this.getView().getTfAnio().getText()));
		
		Object locTipo = this.getView().getCbTipo().getSelectedItem();
		if (locTipo != null) this.getAbmModel().setTipo((Mayor.Tipo)locTipo);
		else this.getAbmModel().setTipo(null);
		
		List<LineaMayor> locLista = this.getTableModel().getListaObjetos();
		this.getAbmModel().getListaLineaMayor().clear();
		this.getAbmModel().getListaLineaMayor().addAll(locLista);
		
		this.getAbmModel().fireActualizarDatos();
	}

	@Override
	public void actualizarView() {
		String mes = "";
		if (this.getAbmModel().getMes() != null && Conversor.getInteger(this.getAbmModel().getMes().toString()) <= 9) {
			mes = "0" + Conversor.getString(this.getAbmModel().getMes());
		} else  {
			mes = Conversor.getString(this.getAbmModel().getMes());
		}
		this.getView().getTfMes().setText(mes);
		this.getView().getTfAnio().setText(Conversor.getString(this.getAbmModel().getAnio()));
		this.getView().getTfCuenta().setText(Conversor.getVacioSiNull(this.getAbmModel().getCuenta()));
		this.getView().getCbTipo().setSelectedItem(this.getAbmModel().getObjetoABM().getTipo());
	}
	
	@Override
	public boolean validarDatos() {
		boolean validacionOK = true;
		
		List<Integer> attMin = new ArrayList<Integer>();
		List<JLabel> lblMin = new ArrayList<JLabel>();
		List<Integer> cantMin = new ArrayList<Integer>();

		attMin.add(this.getView().getPnlTabla().getTblDatos().getRowCount());
		lblMin.add(new JLabel("La Lista de Líneas del Mayor"));
		cantMin.add(1);
		
		List<String> listaErrores = new ArrayList<String>();
		try {
			listaErrores.addAll(Validador.validarCantidadMinima(attMin, lblMin, cantMin));
		}
		catch (GuiException ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.getView(), ex.getMessage());
			return false;
		}
		
		if (!listaErrores.isEmpty()) {
			validacionOK = false;
			this.mostrarErroresValidacion(listaErrores);
		}
		
		return validacionOK;
	}
	
	public boolean validarDatosGenerarMayor() {
		boolean validacionOK = true;
		
		List<Object> attNulos = new ArrayList<Object>();
		List<JLabel> lblNulos = new ArrayList<JLabel>();
		
		List<Object> attAnio = new ArrayList<Object>();
		List<JLabel> lblAnio = new ArrayList<JLabel>();
		List<Integer> longAnio = new ArrayList<Integer>();
				
		List<Object> attMes = new ArrayList<Object>();
		List<JLabel> lblMes = new ArrayList<JLabel>();
		List<Integer> longMes = new ArrayList<Integer>();

		
		attNulos.add(this.getView().getTfMes().getText());
		lblNulos.add(this.getView().getLblMes());
		attNulos.add(this.getView().getTfAnio().getText());
		lblNulos.add(this.getView().getLblAnio());
		
		attNulos.add(this.getView().getTfCuenta().getText());
		lblNulos.add(this.getView().getLblCuenta());
		
		attNulos.add(this.getView().getCbTipo().getSelectedItem());
		lblNulos.add(this.getView().getLblTipo());
		
		attAnio.add(this.getView().getTfAnio().getText());
		lblAnio.add(this.getView().getLblAnio());
		longAnio.add(4);
		
		attMes.add(this.getView().getTfMes().getText());
		lblMes.add(this.getView().getLblMes());
		longMes.add(2);
		
		List<String> listaErrores = new ArrayList<String>();
		
		try {
			listaErrores.addAll(Validador.validarEnteros(attAnio, lblAnio));
			listaErrores.addAll(Validador.validarEnteros(attMes, lblMes));
			listaErrores.addAll(Validador.validarLongitudExacta(attAnio, lblAnio, longAnio));
			listaErrores.addAll(Validador.validarLongitudExacta(attMes, lblMes, longMes));
			listaErrores.addAll(Validador.validarEnteros(attMes, lblMes));
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
	
	void seleccionarCuenta() throws Exception {
		AdminCuenta  adminCuenta = new AdminCuenta(this.getView());
		Cuenta locCuenta = adminCuenta.openSelect();
		
		if (locCuenta != null) {
			this.getAbmModel().setCuenta(locCuenta);
			this.actualizarABMModel();
			this.actualizarView();
		}
	}
	
	void limpiarCuenta() throws Exception {
		this.getAbmModel().setCuenta(null);
		this.actualizarABMModel();
		this.actualizarView();
	}
	
	void generarMayor() throws Exception {
		if (this.validarDatosGenerarMayor()) {
			this.getTableModel().clearTable();
			this.actualizarABMModel();
			this.getAbmModel().generarLineaMayor();
			this.getTableModel().addRows(this.getAbmModel().getObjetoABM().getListaLineaMayor());
			if (!this.getTipoMayor().equals("") || this.getTipoMayor() != null) {
				this.getAbmModel().getObjetoABM().setTipo(this.getTipoMayor());
			}
			this.actualizarView();
		}
	}
	
	public Tipo getTipoMayor() {
		return tipoMayor;
	}
	public void setTipoMayor(Tipo tipoMayor) {
		this.tipoMayor = tipoMayor;
	}
	void guardarTipoMayor() throws Exception {
		if (this.getView().getCbTipo().getSelectedIndex() != -1) {
			this.actualizarABMModel();
			this.setTipoMayor(this.getAbmModel().getObjetoABM().getTipo());
		}
	}
	
}

class BtnSeleccionarCuentaListener implements ActionListener {
	private ABMMayor controller;
	public BtnSeleccionarCuentaListener(ABMMayor controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.seleccionarCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnLimpiarCuentaListener implements ActionListener {
	private ABMMayor controller;
	public BtnLimpiarCuentaListener(ABMMayor controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.limpiarCuenta();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnGenerarMayorListener implements ActionListener {
	private ABMMayor controller;

	public BtnGenerarMayorListener(ABMMayor controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.generarMayor();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),
					ex.getMessage());
		}
	}
}

class CbTipoDeMayor implements ActionListener {
	private ABMMayor controller;

	public CbTipoDeMayor(ABMMayor controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			this.controller.guardarTipoMayor();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),
					ex.getMessage());
		}
	}
	
}

