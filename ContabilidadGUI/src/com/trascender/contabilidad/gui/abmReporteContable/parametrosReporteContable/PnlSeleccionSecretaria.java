package com.trascender.contabilidad.gui.abmReporteContable.parametrosReporteContable;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.framework.recurso.filtros.FiltroSecretaria;
import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.gui.framework.model.TDefaultComboBoxModel;

public class PnlSeleccionSecretaria extends JPanel{
	
	private static final long serialVersionUID = 4145893773964566269L;
	private JComboBox comboBox = new JComboBox();
	private ParametrosReporteContable ventana;
	
	public PnlSeleccionSecretaria(ParametrosReporteContable pVentana) {
		this.ventana = pVentana;
		List<Secretaria> locListaSec;
		try {
			locListaSec = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemMunicipalidad().findListaSecretarias(new FiltroSecretaria()).getListaResultados();
			this.comboBox.setModel(new TDefaultComboBoxModel(locListaSec.toArray()));
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
	
	public Long getIdSecretariaSeleccionada(){
		return ((Secretaria)this.getComboBox().getSelectedItem()).getIdSecretaria();
	}
}
