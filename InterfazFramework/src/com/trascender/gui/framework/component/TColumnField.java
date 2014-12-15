package com.trascender.gui.framework.component;

import java.lang.reflect.Field;

public class TColumnField extends TColumn{
	
	private static final long serialVersionUID = 7193401223775469499L;
	
	private Field atributo;
	
	public TColumnField(String titulo, Field atributo, Class<?> claseColumna){
		super(titulo, claseColumna);
		this.atributo = atributo;
	}
	
	public TColumnField(String titulo, Field atributo) {
		this(titulo,atributo,String.class);
	}
	
	public Field getAtributo() {
		return atributo;
	}

	@Override
	public Object getValue(Object objeto) throws Exception{
		this.atributo.setAccessible(true);
		return atributo.get(objeto);
	}

//	@Override
//	public void setCellEditor(TableCellEditor cellEditor) {
//		
//		System.out.println("setCellEditor");
//		
//		super.setCellEditor(cellEditor);
//		if (this.claseColumna.equals(Boolean.class)) {
//			
//			System.out.println("pongo el cellEditor de CheckBox");
//			
//			super.setCellEditor(new CheckBoxCellEditor());
//		}
//	}
//	
//	@Override
//	public TableCellEditor getCellEditor() {
//		
//		System.out.println("getCellEditor");
//		
//		if (this.getClaseColumna().equals(Boolean.class)) {
//			
//			System.out.println("obtengo el cellEditor de CheckBox");
//			
//			return new CheckBoxCellEditor();
//		}
//		else {
//			return super.getCellEditor();
//		}
//	}
	
}

//class CheckBoxCellEditor extends AbstractCellEditor implements TableCellEditor {
//
//	private static final long serialVersionUID = -7119022740140319694L;
//	
//	protected JCheckBox checkBox;
//
//	public CheckBoxCellEditor() {
//		checkBox = new JCheckBox();
//		checkBox.setHorizontalAlignment(SwingConstants.CENTER);
//		checkBox.setBackground( Color.white);
//	}
//
//	public Component getTableCellEditorComponent(
//			JTable table,
//			Object value,
//			boolean isSelected,
//			int row,
//			int column) {
//
//		checkBox.setSelected(((Boolean) value).booleanValue());
//
//		Component c = table.getDefaultRenderer(String.class).getTableCellRendererComponent(table, value, isSelected, false, row, column);
//		if (c != null) {
//			checkBox.setBackground(c.getBackground());
//		}
//		return checkBox;
//	}
//	
//	public Object getCellEditorValue() {
//		return Boolean.valueOf(checkBox.isSelected());
//	}
//}
