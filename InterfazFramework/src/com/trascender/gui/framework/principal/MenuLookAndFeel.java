package com.trascender.gui.framework.principal;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.trascender.gui.framework.main.AppManager;

public class MenuLookAndFeel extends JMenu {
	
	private static final long serialVersionUID = -7853916193138399032L;
	
	Map<String,String> listaLooks;
	
	public MenuLookAndFeel() {
		listaLooks = new HashMap<String,String>();
		
		ButtonGroup locGrupoBotones = new ButtonGroup();
		for (LookAndFeelInfo cadaLook : UIManager.getInstalledLookAndFeels()) {
			listaLooks.put(cadaLook.getName(), cadaLook.getClassName());
			
			JRadioButtonMenuItem locMenuItem = new JRadioButtonMenuItem();
			locMenuItem.setName(cadaLook.getName());
			locMenuItem.setText(cadaLook.getName());
			
			locMenuItem.addChangeListener(new CambioMenuListener());
			
			locGrupoBotones.add(locMenuItem);
			this.add(locMenuItem);
			
//			if (cadaLook.getName().equals(Messages.getString("Application.defaultLookAndFeel"))) {
//				locMenuItem.setSelected(true);
//			}
			
		}
	}

	class CambioMenuListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			try {
				JMenuItem locMenuItem = (JMenuItem)e.getSource();
				if (locMenuItem.isSelected()) {
					UIManager.setLookAndFeel(listaLooks.get(locMenuItem.getName()));
					SwingUtilities.updateComponentTreeUI(AppManager.getInstance().getMainController().getView());
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}