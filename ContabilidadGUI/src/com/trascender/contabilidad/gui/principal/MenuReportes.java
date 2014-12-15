package com.trascender.contabilidad.gui.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.trascender.contabilidad.gui.abmReporteContable.AdminReporteContable;
import com.trascender.contabilidad.gui.abmReporteContable.parametrosReporteContable.ParametrosReporteContable;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.ReporteContable;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.gui.framework.main.AppManager;

public class MenuReportes extends JMenu {

	private static final long serialVersionUID = -520897848534965617L;
	
	private JFrame owner;
	
	private JMenuItem miReporteContable;
	private List<JMenuItem> listaItemsMenu;
	
	public MenuReportes(JFrame owner) {
		super();
		this.owner = owner;
		this.init();
		this.setListeners();
	}
	
	private void init() {
		this.setText("Reportes");
		this.setMnemonic(KeyEvent.VK_T);
		
		this.miReporteContable = new JMenuItem();
		this.miReporteContable.setText("Reportes Contables");
		this.miReporteContable.setMnemonic(KeyEvent.VK_E);
		this.add(this.miReporteContable);
	}
	
	private void setListeners() {
		this.miReporteContable.addActionListener(new ReportesContablesListener(this.owner));
	}
	
	public void armarItemsMenuReportes(List<ReporteContable> pListaReportes){
		while(this.getPopupMenu().getComponentCount() > 1){
			this.getPopupMenu().remove(1);
		}
		
		if(!pListaReportes.isEmpty()){
			this.addSeparator();
			this.listaItemsMenu = new ArrayList<JMenuItem>();
			for(ReporteContable cadaReporte : pListaReportes){
				JMenuItem nuevoItemMenu = new JMenuItem();
				nuevoItemMenu.setText(cadaReporte.getNombre());
				nuevoItemMenu.setMnemonic(KeyEvent.VK_E);
				this.add(nuevoItemMenu);
				this.listaItemsMenu.add(nuevoItemMenu);
				nuevoItemMenu.addActionListener(new ReportesContablesMenuListener(this.owner, cadaReporte));
			}
		}
	}
	
	public List<JMenuItem> getListaItemsMenu() {
		return listaItemsMenu;
	}

	class ReportesContablesListener implements ActionListener {
		private JFrame owner;
		
		public ReportesContablesListener(JFrame owner) {
			this.owner = owner;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				AdminReporteContable adminReporteContable = new AdminReporteContable(this.owner);
				adminReporteContable.getView().setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
			}
		}
		
	}
	
	class ReportesContablesMenuListener implements ActionListener {
		private JFrame owner;
		private ReporteContable reporte;
		
		public ReportesContablesMenuListener(JFrame owner, ReporteContable pReporte) {
			this.owner = owner;
			this.reporte = pReporte;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				ParametrosReporteContable ventanaReportesContables = new ParametrosReporteContable(this.owner, reporte);
				
				if(!reporte.getListaParametroReporte().isEmpty()){
					ventanaReportesContables.setVisible(true);
				} else{
					ventanaReportesContables.abrirVisualzarReporte();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				AppManager.getInstance().showErrorMsg(this.owner, ex.getMessage());
			}
		}
		
	}

	public JMenuItem getMiReporteContable() {
		return miReporteContable;
	}
	
}
