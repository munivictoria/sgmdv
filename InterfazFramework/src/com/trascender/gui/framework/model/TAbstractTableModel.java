package com.trascender.gui.framework.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.gui.framework.component.TColumn;
import com.trascender.gui.framework.component.TColumnField;
import com.trascender.gui.framework.main.AppManager;

public abstract class TAbstractTableModel<T extends Serializable> extends AbstractTableModel {
	
	private static final long serialVersionUID = 2561373222924987866L;
	
	private Permiso permiso;
	
	private List<T> listaObjetos = new ArrayList<T>();
	private List<? extends TColumn> listaColumnas = new ArrayList<TColumn>();
	
	private T selectedRow = null;
	
	public TAbstractTableModel(Class<T> clase) throws Exception {
		try{
			Field locField = clase.getField("serialVersionUID");
			this.permiso = AppManager.getInstance().getPermiso(locField.getLong(clase));
			
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 
	 * @return Lista de columnas de la tabla.
	 */
	public List<TColumn> getListaColumnas() {
		return (List<TColumn>) listaColumnas;
	}
	
	/**
	 * 
	 * @param listaColumnas
	 */
	public void setListaColumnas(List<? extends TColumn> listaColumnas) {
		this.listaColumnas = listaColumnas;
		this.fireTableStructureChanged();
	}
	
	/**
	 * 
	 * @return Lista de objetos que contiene la tabla.
	 */
	public List<T> getListaObjetos() {
		return listaObjetos;
	}
	
	/**
	 * 
	 * @param listaObjetos
	 */
	public void setListaObjetos(List<T> listaObjetos) {
		this.listaObjetos = listaObjetos;
		this.fireTableDataChanged();
	}
	
	/**
	 * 
	 * @param listaObjetos
	 */
	public void addRows(Collection<T> rows) {
		int locUltimaFila = this.listaObjetos.size()-1;
		int locNuevaFila = this.listaObjetos.size();
		this.listaObjetos.addAll(rows);
		this.fireTableRowsInserted(locUltimaFila, locNuevaFila);
	}
	
	/**
	 * 
	 * @param listaObjetos
	 */
	public void deleteRows(Collection<T> rows) {
		this.listaObjetos.removeAll(listaObjetos);
		this.fireTableDataChanged();
	}
	
	/**
	 * 
	 * @return Si el usuario tiene permiso para Modificar el recurso.
	 */
	public boolean isUpdate() {
		return permiso.isUpdate();
	}
	
	/**
	 * 
	 * @return Si el usuario tiene permiso para Eliminar el recurso.
	 */
	public boolean isDelete() {
		return permiso.isDelete();
	}
	
	/**
	 * 
	 * @return Si el usuario tiene permiso para Agregar el recurso.
	 */
	public boolean isInsert() {
		return permiso.isInsert();
	}
	
	/**
	 * 
	 * @return Si el usuario tiene permiso para Buscar el recurso.
	 */
	public boolean isSelect() {
		return this.permiso.isSelect();
	}
	
	/**
	 * 
	 */
	public int getRowCount() {
		int locCant = 0;
		if (this.listaObjetos != null) {
			locCant = this.listaObjetos.size();
		}
		return locCant;
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public T getValueAt(int rowIndex, int columnIndex) {
		Object locObjeto = this.getRow(rowIndex);
		TColumn locColumna  = this.listaColumnas.get(columnIndex);
		try{
			return (T) locColumna.getValue(locObjeto);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param rowIndex
	 * @return El objeto contenido en la tabla en la fila especificada.
	 */
	public T getRow(int rowIndex) {
		return this.listaObjetos.get(rowIndex);
	}
	
	/**
	 * 
	 */
	public String getColumnName(int column) {
		return this.listaColumnas.get(column).getTitulo();
	}
	
	/**
	 * 
	 */
	public int getColumnCount() {
		return this.listaColumnas.size();
	}
	
	
	
	/**
	 * Agrega el objeto especificado a la lista de objetos.
	 * @param pRow
	 */
	public void addRow(T pRow){
		int locUltimaFila = this.listaObjetos.size()-1;
		int locNuevaFila = this.listaObjetos.size();
		this.listaObjetos.add(pRow);
		this.fireTableRowsInserted(locUltimaFila, locNuevaFila);
	}
	
	/**
	 * Elimina el objeto especificado de la lista de objetos.
	 * @param pRow
	 */
	public void deleteRow(T pRow){
		int index = this.listaObjetos.indexOf(pRow);
		this.listaObjetos.remove(pRow);
		this.fireTableRowsDeleted(index, index);
	}
	
	/**
	 * Elimina el objeto de la lista de objetos dado un índice.
	 * @param pRowIndex
	 */
	public void deleteRow(int pRowIndex) {
		if (pRowIndex > -1) {
			this.listaObjetos.remove(pRowIndex);
			this.fireTableRowsDeleted(pRowIndex, pRowIndex);
		} 
	}
	
	/**
	 * Actualiza el objeto especificado en la lista de objetos.
	 * @param pRow
	 */
	public void updateRow(T pRow){
		int index = this.listaObjetos.indexOf(pRow);
		this.listaObjetos.set(index, pRow);
		this.fireTableRowsUpdated(index, index);
	}
	
	/**
	 * Limpia la tabla.
	 *
	 */
	public void clearTable() {
		if (this.getListaObjetos() != null) {
			this.getListaObjetos().clear();
			this.fireTableDataChanged();
		}
	}

	/**
	 * 
	 * @return El objeto seleccionado en la tabla.
	 */
	public T getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(T selectedRow) {
		this.selectedRow = selectedRow;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return this.getListaColumnas().get(columnIndex).getClaseColumna();
	}
	
	
	
}
