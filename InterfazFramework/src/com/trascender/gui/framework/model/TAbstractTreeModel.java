package com.trascender.gui.framework.model;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public abstract class TAbstractTreeModel<T> extends DefaultTreeModel {
	
	private static final long serialVersionUID = 8709224927935174762L;
	
	private DefaultMutableTreeNode ultimoNodoModificado;
	private Set<T> listaObjetos;
	
	public TAbstractTreeModel(DefaultMutableTreeNode root) {
		super(root);
		this.setRoot(root);
	}
	
	public void clearTree() {
		this.getRoot().removeAllChildren();
		this.reload();
	}
	
	public DefaultMutableTreeNode getNodo(TreePath pTreePath) {
		DefaultMutableTreeNode nodo = null;
		if (pTreePath != null) {
			nodo = (DefaultMutableTreeNode)pTreePath.getLastPathComponent();
		}
		return nodo;
	}
	
	@SuppressWarnings("unchecked")
	public T getObjeto(TreePath pTreePath) {
		T objeto = null;
		if (pTreePath != null && !this.getTreePath(this.getRoot()).equals(pTreePath)) {
			DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode)pTreePath.getLastPathComponent();
			objeto = (T)nodoSeleccionado.getUserObject();
		}
		return objeto;
	}
	
	@SuppressWarnings("unchecked")
	public Set<T> getObjetos() {
		this.listaObjetos = null;
		if (this.getRoot().getChildCount() > 0) {
			this.listaObjetos = new HashSet<T>();
			Enumeration<DefaultMutableTreeNode> enAmplitud = this.getRoot().breadthFirstEnumeration();
			DefaultMutableTreeNode nodo = null;
			enAmplitud.nextElement(); // para evitar agregar la raiz a la lista.
			while (enAmplitud.hasMoreElements()) {
				nodo = (DefaultMutableTreeNode) enAmplitud.nextElement();
				this.listaObjetos.add((T)nodo.getUserObject());
			}
		}
		return this.listaObjetos;
	}
	
	public void setObjetos(Set<T> listaObjetos) {
		this.listaObjetos = listaObjetos;
	}
	
	public void agregarNodo(TreePath pTreePathParent, T pObjeto) {
		if (pObjeto != null) {
			DefaultMutableTreeNode parentNode = null;
			if (pTreePathParent == null) {
				parentNode = this.getRoot();
			} else {
				parentNode = (DefaultMutableTreeNode)pTreePathParent.getLastPathComponent();
			}
			DefaultMutableTreeNode nuevoNodo = this.getNewNodo(pObjeto);
			this.insertNodeInto(nuevoNodo, parentNode, parentNode.getChildCount());
			
			this.setUltimoNodoModificado(nuevoNodo);
			
			
		}
	}
	
	public abstract DefaultMutableTreeNode getNewNodo(T pObjeto);
	
	
	public DefaultMutableTreeNode getRoot() {
		return (DefaultMutableTreeNode)super.getRoot();
	}
	
	
	public void modificarNodo(TreePath pTreePath, T pObjeto) {
		if (pTreePath != null && pObjeto != null) {
			DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)(pTreePath.getLastPathComponent());
			nodo.setUserObject(pObjeto);
			this.setUltimoNodoModificado(nodo);
			this.nodeChanged(nodo);
		}
	}
	
	public void eliminarNodo(TreePath pTreePath) {
		if (pTreePath != null) {
			DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)(pTreePath.getLastPathComponent());
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode)(nodo.getParent());
			if ((parent != null)) {
				this.removeNodeFromParent(nodo);
			}
		}
	}
	
	
	public DefaultMutableTreeNode getUltimoNodoModificado() {
		return ultimoNodoModificado;
	}
	
	public void setUltimoNodoModificado(DefaultMutableTreeNode ultimoNodoModificado) {
		this.ultimoNodoModificado = ultimoNodoModificado;
	}
	
	public TreePath getUltimoNodoModificadoTreePath() {
		return this.getTreePath(this.getUltimoNodoModificado());
	}
	
	public TreePath getRootTreePath() {
		return this.getTreePath(this.getRoot());
	}
	
	
	@SuppressWarnings("unchecked")
	public TreePath getObjetoTreePath(T pObjeto) {
		TreePath locTreePath = null;
		if (this.getRoot().getChildCount() > 0) {
			boolean found = false;
			T locObjetoEncontrado = null;
			DefaultMutableTreeNode nodoEncontrado = null;
			
			Enumeration<DefaultMutableTreeNode> enAmplitud = this.getRoot().breadthFirstEnumeration();
			enAmplitud.nextElement(); // para evitar agregar la raiz a la lista.
			
			while (enAmplitud.hasMoreElements() && !found) {
				nodoEncontrado = (DefaultMutableTreeNode) enAmplitud.nextElement();
				locObjetoEncontrado = ((T)nodoEncontrado.getUserObject());
				if ((locObjetoEncontrado!=null)&&(locObjetoEncontrado.equals(pObjeto))) {
					found = true;
					locTreePath = this.getTreePath(nodoEncontrado);
				}
			}
		}
		return locTreePath;
	}
	
	private TreePath getTreePath(DefaultMutableTreeNode nodo) {
		TreePath locTreePath = null;
		if (nodo != null) {
			TreeNode[] nodosHijos = this.getPathToRoot(nodo);
			locTreePath = new TreePath(nodosHijos);
		}
		return locTreePath; 
	}
	
	public void actualizarRaiz(){
		this.reload();
	}

}
