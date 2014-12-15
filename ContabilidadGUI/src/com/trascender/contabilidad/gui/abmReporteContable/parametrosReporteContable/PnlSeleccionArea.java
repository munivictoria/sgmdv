package com.trascender.contabilidad.gui.abmReporteContable.parametrosReporteContable;

import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.filtros.FiltroArea;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;

public class PnlSeleccionArea extends JPanel{

	private static final long serialVersionUID = -8402394206005159795L;

	private JComboBox comboBox = new JComboBox();
	private ParametrosReporteContable ventana;
	
	public PnlSeleccionArea(ParametrosReporteContable pVentana) {
		this.ventana = pVentana;
		List<Area> locListaAreas;
		try {
			locListaAreas = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemMunicipalidad().findArea(new FiltroArea()).getListaResultados();
			this.comboBox.setModel(new TDefaultComboBoxModel(locListaAreas.toArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}
	
	public ParametrosReporteContable getVentana() {
		return ventana;
	}

	public void setVentana(ParametrosReporteContable ventana) {
		this.ventana = ventana;
	}
	
	public Long getIdAreaSeleccionada(){
		return ((Area)this.getComboBox().getSelectedItem()).getIdArea();
	}
}
