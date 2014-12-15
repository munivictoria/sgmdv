package com.trascender.contabilidad.gui.principal;

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

public class MainContabilidad extends Main {

	private MainContabilidadView view;
	
	public MainContabilidad() throws Exception {
		this.view = new MainContabilidadView();
		this.init();
	}

	@Override
	protected void init() {
		super.init();
		this.setListeners();
		this.view.getTimer().start();
	}
	
	public void setListeners() {
		this.getView().getMenuSistema().getMiConectar().addActionListener(new ConectarListener(this.getView()));
		this.getView().getMenuSistema().getMiDesconectar().addActionListener(new DesconectarListener(this.getView()));
		
		this.getView().addWindowListener(new MainWindowListener(this.view));
		this.getView().getBtnConectar().addActionListener(new ConectarListener(this.view));
		this.getView().getBtnDesconectar().addActionListener(new DesconectarListener(this.view));
		this.getView().setTimer(new Timer(1000, new TimerFechaHoraListener(this.view)));
	}
	
	public MainContabilidadView getView() {
		return this.view;
	}
	
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
	private MainContabilidadView view;
	public TimerFechaHoraListener(MainContabilidadView view) {
		this.view = view;
	}
	public void actionPerformed(ActionEvent e) {
		Date now = new Date();
		String fecha = new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy '-' HH:mm:ss 'Hs.'").format(now);
		fecha = fecha.substring(0, 1).toUpperCase() + fecha.substring(1);
		this.view.getLblFechaHora().setText(fecha);
	}
}
