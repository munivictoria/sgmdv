package com.trascender.contabilidad.gui.abmMayor.abm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.trascender.contabilidad.gui.abmLineaMayor.LineaMayorTableModel;
import com.trascender.contabilidad.gui.abmMayor.MayorABMModel;
import com.trascender.contabilidad.recurso.persistent.Mayor;
import com.trascender.gui.framework.main.AppManager;

public class AgregarMayor extends ABMMayor {
	private AgregarMayorView view;
	private MayorABMModel abmModel = new MayorABMModel();
	private LineaMayorTableModel tableModel = new LineaMayorTableModel();
	
	public AgregarMayor(JFrame owner) throws Exception {
		this.view = new AgregarMayorView(owner);
		this.abmModel.setObjetoABM(new Mayor());
		this.init();
	}

	public AgregarMayor(JDialog owner) throws Exception {
		this.view = new AgregarMayorView(owner);
		this.abmModel.setObjetoABM(new Mayor());
		this.init();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}
	
	private void setListeners() {
		this.getView().getPnlPie().getBtnAceptar().addActionListener(new BtnAgregarMayorListener(this));
	}

	@Override
	public MayorABMModel getAbmModel() {
		return this.abmModel;
	}

	@Override
	public LineaMayorTableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	public ABMMayorView getView() {
		return this.view;
	}
	
	void agregarMayor() throws Exception {
		if (this.validarDatos()) {
			this.actualizarABMModel();
			this.getAbmModel().agregar();
			this.setOperacionRealizada(true);
			this.close();
		}
	}
}


class BtnAgregarMayorListener implements ActionListener {
	private AgregarMayor controller;

	public BtnAgregarMayorListener(AgregarMayor controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.controller.agregarMayor();
		} catch (Exception ex) {
			ex.printStackTrace();
			AppManager.getInstance().showErrorMsg(this.controller.getView(),
					ex.getMessage());
		}
	}
}