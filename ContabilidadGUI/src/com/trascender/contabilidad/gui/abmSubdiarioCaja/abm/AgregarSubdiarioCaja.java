package com.trascender.contabilidad.gui.abmSubdiarioCaja.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmLineaSubdiarioCaja.LineaSubdiarioCajaTableModel;
import com.trascender.contabilidad.gui.abmSubdiarioCaja.SubdiarioCajaABMModel;
import com.trascender.contabilidad.recurso.persistent.SubdiarioCaja;
import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.util.Conversor;

public class AgregarSubdiarioCaja extends ABMSubdiarioCaja {
	
	private AgregarSubdiarioCajaView view;
	private SubdiarioCajaABMModel abmModel = new SubdiarioCajaABMModel();
	private LineaSubdiarioCajaTableModel tableModel = new LineaSubdiarioCajaTableModel();
	
	public AgregarSubdiarioCaja(JFrame owner) throws Exception {
		this.view = new AgregarSubdiarioCajaView(owner);
		this.abmModel.setObjetoABM(new SubdiarioCaja());
		this.init();
	}
	
	public AgregarSubdiarioCaja(JDialog owner) throws Exception {
		this.view = new AgregarSubdiarioCajaView(owner);
		this.abmModel.setObjetoABM(new SubdiarioCaja());
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		
		this.enableBotonesTabla(false);
		this.getView().getFtfFechaCreacion().setText(Conversor.getString(Calendar.getInstance().getTime()));
		
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getBtnGenerarSubdiario().addActionListener(new BtnGenerarSubdiarioListener(this));
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarSubdiarioCajaListener(this));
	}
	
	@Override
	public SubdiarioCajaABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public LineaSubdiarioCajaTableModel getTableModel() {
		return this.tableModel;
	}
	
	@Override
	public AgregarSubdiarioCajaView getView() {
		return this.view;
	}
	
	void generarSubdiarioCaja() throws Exception {
		if (this.validarDatosGenerarSubdiario()) {
			this.actualizarABMModel();
			this.getAbmModel().generarSubdiarioCaja();
			this.getTableModel().addRows(this.getAbmModel().getObjetoABM().getLineasSubdiarioCaja());
			this.enableBotonesTabla(true);
			this.actualizarView();
		}
	}
	
	void agregarSubdiarioCaja() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}


class BtnGenerarSubdiarioListener implements ActionListener {
	private AgregarSubdiarioCaja controller;
	public BtnGenerarSubdiarioListener(AgregarSubdiarioCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.generarSubdiarioCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}

class BtnAgregarSubdiarioCajaListener implements ActionListener {
	private AgregarSubdiarioCaja controller;
	public BtnAgregarSubdiarioCajaListener(AgregarSubdiarioCaja controller) {
		this.controller = controller;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarSubdiarioCaja();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(), ex.getMessage());
		}
	}
}
