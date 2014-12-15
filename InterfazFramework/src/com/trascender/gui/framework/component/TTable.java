package com.trascender.gui.framework.component;

import java.awt.Color;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.trascender.gui.framework.model.TBooleanRenderer;
import com.trascender.gui.framework.model.TDateRenderer;
import com.trascender.gui.framework.model.TDoubleRenderer;

public class TTable extends JTable {

	private static final long serialVersionUID = 1293106167734038270L;
	
	private DefaultTableCellRenderer rendererPar; 
	private DefaultTableCellRenderer rendererImpar; 
	private TDateRenderer dateRendererPar;
	private TDateRenderer dateRendererImpar;
	private TBooleanRenderer booleanRendererPar;
	private TBooleanRenderer booleanRendererImpar;
	private TDoubleRenderer doubleRendererPar;
	private TDoubleRenderer doubleRendererImpar;
	
	private Color colorPar   = new Color(255,255,255);
	private Color colorImpar = new Color(230,230,230); 
	
	public TTable() { 
		super(); 
	} 
	
	
	public TTable(TableModel tm) { 
		super(tm); 
	}
	
	public TTable(Object[][] data, Object[] columns) { 
		super (data, columns); 
	}
	
	public TTable(int rows, int columns) { 
		super (rows, columns); 
	}
	
	
	public TBooleanRenderer getBooleanRendererPar() {
		if (this.booleanRendererPar == null) {
			this.booleanRendererPar = new TBooleanRenderer();
			this.booleanRendererPar.setBackground(this.colorPar);
			this.booleanRendererPar.getCheckBox().setBackground(this.colorPar);
		}
		return booleanRendererPar;
	}
	
	public TBooleanRenderer getBooleanRendererImpar() {
		if (this.booleanRendererImpar == null) {
			this.booleanRendererImpar = new TBooleanRenderer();
			this.booleanRendererImpar.setBackground(this.colorImpar);
			this.booleanRendererPar.getCheckBox().setBackground(this.colorImpar);
		}
		return booleanRendererImpar;
	}
	
	public DefaultTableCellRenderer getRendererImpar() {
		if (rendererImpar == null) { 
			rendererImpar = new DefaultTableCellRenderer();
			rendererImpar.setBackground(this.colorImpar); 
		}
		return rendererImpar;
	}

	public DefaultTableCellRenderer getRendererPar() {
		if (rendererPar == null) { 
			rendererPar = new DefaultTableCellRenderer();
			rendererPar.setBackground(this.colorPar);
		}
		return rendererPar;
	}

	public TDateRenderer getDateRendererImpar() {
		if (dateRendererImpar == null) { 
			dateRendererImpar = new TDateRenderer();
			dateRendererImpar.setBackground(this.colorImpar); 
		}
		return dateRendererImpar;
	}

	public TDateRenderer getDateRendererPar() {
		if (this.dateRendererPar == null){
			this.dateRendererPar = new TDateRenderer();
			this.dateRendererPar.setBackground(this.colorPar);
		}
		return dateRendererPar;
	}
	
	public TDoubleRenderer getDoubleRendererPar() {
		if (this.doubleRendererPar == null){
			this.doubleRendererPar = new TDoubleRenderer();
			this.doubleRendererPar.setBackground(this.colorPar);
		}
		return doubleRendererPar;
	}
	
	public TDoubleRenderer getDoubleRendererImpar() {
		if (this.doubleRendererImpar == null){
			this.doubleRendererImpar = new TDoubleRenderer();
			this.doubleRendererImpar.setBackground(this.colorImpar);
		}
		return doubleRendererImpar;
	}

	/** 
	 * Si una fila es par, getCellRenderer devuelve un DefaultTableCellRenderer
	 * con el color colorPar. Si la fila es impar, devuelve uno con el color
	 * colorImpar.
	 */ 
	public TableCellRenderer getCellRenderer(int row, int column) {
		if (this.getModel().getColumnClass(column).equals(Date.class)){
			return ((row % 2) == 0) ? this.getDateRendererPar() : this.getDateRendererImpar();
		}
		if (this.getModel().getColumnClass(column).equals(Boolean.class) || this.getModel().getColumnClass(column).equals(boolean.class)) {
			return ((row % 2) == 0) ? this.getBooleanRendererPar() : this.getBooleanRendererImpar();
		}
//		if (this.getModel().getColumnClass(column).equals(Double.class) || this.getModel().getColumnClass(column).equals(Integer.class)){
			if (this.getModel().getColumnClass(column).equals(Double.class)){
			return ((row % 2) == 0) ? this.getDoubleRendererPar() : this.getDoubleRendererImpar();
		}
		
		return ((row % 2) == 0) ? this.getRendererPar(): this.getRendererImpar();
	}


}
