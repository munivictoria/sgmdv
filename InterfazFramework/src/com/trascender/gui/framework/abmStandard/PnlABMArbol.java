package com.trascender.gui.framework.abmStandard;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.TreeModel;

import com.trascender.gui.framework.component.TTreeCuentas;

public class PnlABMArbol extends JScrollPane {
	
	private static final long serialVersionUID = 5513255615940373091L;
	
	private TTreeCuentas treeCuentas;
	
	
	public PnlABMArbol() {
		this.init();
	}
	
	private void init() {
		this.setSize(new Dimension(600, 200));
		this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
		this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.treeCuentas = new TTreeCuentas();
		this.treeCuentas.setShowsRootHandles(true);
		//this.treeCuentas.setRootVisible(false);
		
		this.setViewportView(this.treeCuentas);
	}
	
	public TTreeCuentas getTreeCuentas() {
		return treeCuentas;
	}
	
	
	public void setTreeModel(TreeModel pModel){
		this.treeCuentas.setModel(pModel);
	}

	public TreeModel getTreeModel() {
		return this.treeCuentas.getModel();
	}
}
