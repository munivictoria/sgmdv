package com.trascender.caja.gui.principal;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.trascender.gui.framework.main.AppManager;
import com.trascender.gui.framework.principal.Main;

public class MainCaja extends Main {
	
	private MainCajaView view;
	
	public MainCaja() throws Exception {
		this.view = new MainCajaView();
		this.init();
		this.view.getTimer().start();
	}
	
	@Override
	protected void init() {
		super.init();
		this.setListeners();
	}

	private void setListeners() {
		this.getView().getMenuSistema().getMiConectar().addActionListener(new ConectarListener(this.getView()));
		this.getView().getMenuSistema().getMiDesconectar().addActionListener(new DesconectarListener(this.getView()));
		
		this.getView().addWindowListener(new MainWindowListener(this.view));
		this.getView().getBtnConectar().addActionListener(new ConectarListener(this.view));
		this.getView().getBtnDesconectar().addActionListener(new DesconectarListener(this.view));
		this.getView().setTimer(new Timer(1000, new TimerFechaHoraListener(this.view)));
	}

	@Override
	public MainCajaView getView() {
		return this.view;
	}
	class MainWindowListener extends WindowAdapter {
		private Component mainWindow;
		public MainWindowListener(Component mainWindow) {
			this.mainWindow = mainWindow;
		}
		public void windowClosing(WindowEvent e) {
			AppManager.getInstance().salir((JFrame)this.mainWindow);
		}
		
	}

	class TimerFechaHoraListener implements ActionListener {
		private MainCajaView view;
		public TimerFechaHoraListener(MainCajaView view) {
			this.view = view;
		}
		public void actionPerformed(ActionEvent e) {
			Date now = new Date();
			String fecha = new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy '-' HH:mm:ss 'Hs.'").format(now);
			fecha = fecha.substring(0, 1).toUpperCase() + fecha.substring(1);
			this.view.getLblFechaHora().setText(fecha);
		}
	}
	
}
