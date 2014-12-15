package com.trascender.gui.framework.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.EventListenerList;


public abstract class TAbstractBusquedaModel<T> { 

	private EventListenerList listaEventos = new EventListenerList(); 
	private ThreadGroup listaThreadEventos;
	
	public abstract List<T> buscar() throws Exception;
	
	public abstract void reiniciar();
	
	public void addActionListener(ActionListener pListener){
		this.listaEventos.add(ActionListener.class, pListener);
	}
	
	public void removeActionListener(ActionListener pListener){
		this.listaEventos.remove(ActionListener.class, pListener);
	}
	
	public void cancelarAcciones(){
		if (this.listaThreadEventos!=null){
			this.listaThreadEventos.interrupt();
		}
	}
	
	protected void fireAction(final ActionEvent pEvent){
		listaThreadEventos = new ThreadGroup("listaEventos");
		ActionListener[] locListaObjetos=  listaEventos.getListeners(ActionListener.class);
		for (final ActionListener locActionListener: locListaObjetos){
			Thread locThread = new Thread(
					listaThreadEventos,
					new Runnable(){
						public void run() {
							locActionListener.actionPerformed(pEvent);
						}
					}
			);
			locThread.run();
		}
	}

	public void fireActualizarDatos() {
		this.fireAction(new ActionEvent(this, 0, "update"));
	}

}
