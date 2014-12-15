package com.trascender.gui.framework.abmStandard;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.trascender.gui.framework.component.TFormattedTextFieldDate;
import com.trascender.gui.framework.component.TLabel;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;
import com.trascender.gui.framework.model.TAbstractTableModel;
import com.trascender.gui.framework.recursos.Messages;


public abstract class AdminController<T extends Serializable> {
	
	protected abstract AdminView getView();
	protected abstract TAbstractTableModel<T> getTableModel();
	protected abstract TAbstractBusquedaModel<T> getBusquedaModel();
	
	private T objetoSeleccionado = null;
	private List<T> objetosSeleccionados = null;
	
	private ABMErrores abmErrores;
	public ABMErrores getAbmErrores() {
		return this.abmErrores;
	}
	
	protected void init() {
		this.abmErrores = new ABMErrores(this.getView()); 
		
		this.setModels();
		this.setListeners();
	}
	
	private void setModels() {
		
	}
	
	private void setListeners() {
		this.setBtnCerrarListener(this.getView());
		this.setComponentesBusquedaKeyListener(this.getView());
		this.setTblBusquedaKeyListener(this.getView());
		this.setBtnReiniciarListener();
	}
	
	protected abstract void actualizarBusquedaModel();
	
	protected abstract void actualizarBusquedaView();
	
	public void open() {
		this.getView().getPnlPie().getBtnSeleccionar().setEnabled(false);
		this.getView().setVisible(true);
	}
	
	public T openSelect() {
		this.getView().getPnlTabla().getTblDatos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.getView().getPnlPie().getBtnSeleccionar().addActionListener(new BtnSeleccionarSimpleListener(this));
		this.getView().setVisible(true);
		//return this.getSelectedRow();
		return this.getObjetoSeleccionado();
	}
	
	public List<T> openSelectMultiple() {
		this.getView().getPnlTabla().getTblDatos().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.getView().getPnlPie().getBtnSeleccionar().addActionListener(new BtnSeleccionarMultipleListener(this));
		this.getView().setVisible(true);
		//return this.getSelectedRows();
		return this.getObjetosSeleccionados();
	}

	public void close() {
		this.getView().dispose();
	}
	
	public void mostrarErroresValidacion(List<String> listaErrores) {
		if (Messages.getBoolean("Application.mostrarPantallaErroresAdmin")) {
			this.getAbmErrores().getView().setListaErrores(listaErrores);
			this.getAbmErrores().open();
		}
	}
	
	public T getSelectedRow() {
		T objetoSeleccionado = null;
		if (this.getView() != null && this.getView().getPnlTabla().getTblDatos() != null) {
			int selectedRow = this.getView().getPnlTabla().getTblDatos().getSelectedRow();
			if (selectedRow > -1) {
				objetoSeleccionado = this.getTableModel().getRow(selectedRow);
			}
		}
		return objetoSeleccionado; 
	}
	
	public List<T> getSelectedRows() {
		List<T> objetosSeleccionados = null;
		if (this.getView() != null && this.getView().getPnlTabla().getTblDatos() != null) {
			int[] selectedRows = this.getView().getPnlTabla().getTblDatos().getSelectedRows();
			if (selectedRows != null && selectedRows.length > 0) {
				objetosSeleccionados = new ArrayList<T>();
				for (int i = 0; i < selectedRows.length; i++) {
					objetosSeleccionados.add(this.getTableModel().getRow(selectedRows[i]));
				}
			}
		}
		return objetosSeleccionados;
	}

	public int getSelectedRowIndex() {
		int selectedRowIndex = -1;
		if (this.getView() != null && this.getView().getPnlTabla().getTblDatos() != null) {
			selectedRowIndex = this.getView().getPnlTabla().getTblDatos().getSelectedRow();
		}
		return selectedRowIndex;
	}

	protected final void setObjetoSeleccionado(T objetoSeleccionado) {
		this.objetoSeleccionado = objetoSeleccionado;
	}
	protected final T getObjetoSeleccionado() {
		return this.objetoSeleccionado;
	}
	
	protected final List<T> getObjetosSeleccionados() {
		return this.objetosSeleccionados;
	}
	protected final void setObjetosSeleccionados(List<T> objetosSeleccionados) {
		this.objetosSeleccionados = objetosSeleccionados;
	}
	
	/**
	 * Set del listener del botón Cerrar.
	 * Sobrescribir si se quiere hacer algo diferente de un <code>dispose</code>.
	 */
	protected void setBtnCerrarListener(AdminView view) {
		view.getPnlPie().getBtnCerrar().addActionListener(new BtnCerrarListener(view));
	}
	
	/**
	 * Set de un keyListener para la tabla.
	 * Hace que al presionar TAB gane el foco el botón Seleccionar.
	 */
	protected void setTblBusquedaKeyListener(AdminView view) {
		view.getPnlTabla().getTblDatos().addKeyListener(new TblBusquedaKeyListener(view));
	}
	
	/**
	 * Set de los keyListener de los componentes de búsqueda.
	 * Al presionar ESC se cierra la ventana.
	 */
	protected void setComponentesBusquedaKeyListener(AdminView view) {
		for (Component c : this.getView().getPnlBusqueda().getComponents()) {
			if (c.getClass().getName().equals(JTextField.class.getName()) || 
					c.getClass().getName().equals(JComboBox.class.getName()) ||
					c.getClass().getName().equals(TFormattedTextFieldDate.class.getName())) {
				c.addKeyListener(new ComponentesBusquedaKeyListener(view));
			}
		}
	}
	
	/**
	 * Set del Listener del Boton Reiniciar para borrar los campos y limpiar la tabla.
	 */
	protected void setBtnReiniciarListener() {
		this.getView().getPnlBotonesBusqueda().getBtnReiniciar().addActionListener(new BtnReiniciarListener(this));
	}
	
	public boolean validarDatos() {
		return true;
	}
	
	public void reiniciarEstadosComponentesBusqueda() {
		for (Component c : this.getView().getPnlBusqueda().getComponents()) {
			if (c instanceof TLabel) {
				((TLabel)c).setForeground(Color.BLACK);
			} 
		}
	}
}

/**
 * Listener para salir de la tabla con TAB. 
 */
class TblBusquedaKeyListener extends KeyAdapter {
	private AdminView view;
	public TblBusquedaKeyListener(AdminView view) {
		this.view = view;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_TAB) {
			//Component nextFocusable = this.view.getFocusTraversalPolicy().getComponentAfter(this.view.getPnlPie(), this.view.getSpTabla().getTblBusqueda());
			//nextFocusable.requestFocus();
			
			if (this.view.getPnlPie().getBtnSeleccionar().isEnabled()) {
				this.view.getPnlPie().getBtnSeleccionar().requestFocus();
			} 
			else if (this.view.getPnlPie().getBtnAgregar().isEnabled()) {
				this.view.getPnlPie().getBtnAgregar().requestFocus();
			}
			else if (this.view.getPnlPie().getBtnModificar().isEnabled()) {
				this.view.getPnlPie().getBtnModificar().requestFocus();
			}
			else if (this.view.getPnlPie().getBtnEliminar().isEnabled()){
				this.view.getPnlPie().getBtnEliminar().requestFocus();
			}
			else {
				this.view.getPnlPie().getBtnCerrar().requestFocus();
			}
				
		}
	}
}

/**
 * Listener por defecto de los botones Cerrar de los Admin que solo hace el
 * <code>dispose</code> de la ventana. 
 */
class BtnCerrarListener implements ActionListener {
	private AdminView view;
	public BtnCerrarListener(AdminView view) {
		this.view = view;
	}
	public void actionPerformed(ActionEvent e) {
		this.view.dispose();
	}
}

/**
 * Listener por defecto para que al presionar ESC en algún componente de la
 * búsqueda se cierre la ventana. 
 */
class ComponentesBusquedaKeyListener extends KeyAdapter {
	private AdminView view;
	public ComponentesBusquedaKeyListener(AdminView view) {
		this.view = view;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.view.dispose();
		}
	}
}

class BtnSeleccionarSimpleListener implements ActionListener {
	private AdminController controller;
	
	public BtnSeleccionarSimpleListener(AdminController controller) {
		this.controller = controller;
	}

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		this.controller.setObjetoSeleccionado(this.controller.getSelectedRow());
		this.controller.close();
	}
}

class BtnSeleccionarMultipleListener implements ActionListener {
	private AdminController controller;
	
	public BtnSeleccionarMultipleListener(AdminController controller) {
		this.controller = controller;
	}

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		this.controller.setObjetosSeleccionados(this.controller.getSelectedRows());
		this.controller.close();
	}
}

class BtnReiniciarListener implements ActionListener {
	private AdminController controller;
	
	public BtnReiniciarListener(AdminController controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		this.controller.actualizarBusquedaModel();
		this.controller.getBusquedaModel().reiniciar();
		this.controller.getTableModel().clearTable();
		this.controller.actualizarBusquedaView();
	}
}