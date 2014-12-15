
package muni.expedientes.Trees;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.el.MethodExpression;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.servlet.http.Cookie;

import muni.expedientes.utils.CookieUtils;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandLink;

import com.sun.rave.web.ui.component.Tree;
import com.sun.rave.web.ui.component.TreeNode;

public class TreeView extends Tree {

	protected static final String SEPARATOR_IDMODEL = "_";
	protected static final String HIGHTLIGHT_STYLE = "background-color:#CBDCAF; color: #000000; font-weight : bold";
	protected String selectedCookieValue;

	MethodExpression methodExpression = FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
			.createMethodExpression(FacesContext.getCurrentInstance().getELContext(), getElExpression(), null, new Class[] {ActionEvent.class});

	public void warn(String pMensaje) {
		FacesContext.getCurrentInstance().addMessage("warn", new FacesMessage(FacesMessage.SEVERITY_WARN, pMensaje, pMensaje));
	}

	public Object getSessionBean(String pBeanName) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(pBeanName);
	}

	public Object getRequestBean(String pBeanName) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(pBeanName);
	}

	public Object object;

	public interface Datos {
		public String getTexto();

		public String getId();

		public List<Datos> getHijos();

		public String getUrl();
	}

	public void addNodo(Datos pDatos) {
		this.getChildren().add(crearNodo(pDatos));
	}

	public void createTree(Datos pDatos) {
		this.getChildren().clear();
		this.getChildren().add(crearNodo(pDatos));
	}

	public TreeNode crearNodo(Datos pDatos) {
		TreeNode locNodo = new TreeNode();
		locNodo.setUrl("#");
		locNodo.setExpanded(true);
		locNodo.setId(pDatos.getId());
		locNodo.setText(pDatos.getTexto());
		locNodo.setToolTip("");
		locNodo.setImageURL(pDatos.getUrl());
		if(pDatos.getHijos() != null) {
			locNodo.getChildren().addAll(crearNodos(pDatos.getHijos()));
		}
		if(getElExpression() != null) {
			addFacets(locNodo);
		}

		return locNodo;

	}

	public List<TreeNode> crearNodos(List<Datos> pLista) {
		List<TreeNode> locNodos = new ArrayList<TreeNode>();
		for(Datos datos : pLista) {
			locNodos.add(crearNodo(datos));
		}
		return locNodos;
	}

	public TreeNode getNodo(String idCompleto) {
		TreeNode locNode = null;
		if(idCompleto != null) {
			try {
				String idNodo = this.getIdSinPrefijo(idCompleto, this.getId());
				locNode = (TreeNode) this.findComponent(idNodo);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return locNode;
	}

	private String getCookieSelected() throws Exception {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Cookie c = CookieUtils.getCookieValue(context, this.getCookieName());
			return c.getValue().replace(CookieUtils.VALID_CHAR, SEPARATOR_CHAR);
		} catch(Exception e) {
			throw new Exception("seleccionar nodo");
		}
	}

	@Override
	public String getCookieSelectedTreeNode() {
		return super.getCookieSelectedTreeNode();
	}

	public void setCookieSelected(String s) {
		CookieUtils.setCookieValue(getFacesContext(), getCookieName(), s);
	}

	private String getCookieName() {
		return this.getClientId(getFacesContext()) + COOKIE_SUFFIX;
	}

	public TreeNode getSelectedNode() throws Exception {
		this.selectedCookieValue = getCookieSelected();
		return this.getNodo(this.selectedCookieValue);
	}

	// public TreeNode getSelectedNode() {
	// return this.getNodo(MetodosEstaticos.obtenerCookie("selCookie"));
	// }

	private String getIdSinPrefijo(String idCompleto, String idComponente) {
		String retorno = null;
		if(idCompleto != null && idCompleto.length() > 0) {
			try {
				retorno = idCompleto.substring(idCompleto.indexOf(idComponente) + idComponente.length() + 1);
			} catch(RuntimeException ex) {
				ex.printStackTrace();
			}
		}

		return retorno;
	}

	protected void addFacets(TreeNode node) {
		HtmlAjaxCommandLink link = new HtmlAjaxCommandLink();
		link.setValue(node.getText());
		link.setReRender(addReRenders());
		link.addActionListener(new MethodExpressionActionListener(methodExpression));
		node.getFacets().put("content", link);
	}

	// to override
	protected String getElExpression() {
		return null;
	}

	protected String addReRenders() {
		return null;
	}

	// for now these scriptPath references are absolute urls
	protected void renderScriptDependency(FacesContext facesContext, String scriptPath) throws IOException {
		ResponseWriter writer = facesContext.getResponseWriter();
		writer.startElement("script", null);
		writer.writeAttribute("type", "text/javascript", null);
		writer.writeAttribute("src", scriptPath, null);
		writer.endElement("script");
		writer.write("\n");
	}
}