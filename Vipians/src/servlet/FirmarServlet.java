/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package servlet;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

public class FirmarServlet extends HttpServlet {

	private static final long serialVersionUID = -4420116754195371724L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);

		String strFirmaRequest = request.getParameter("firma");
		String strIdComponente = request.getParameter("id");

		int coma = strFirmaRequest.lastIndexOf(",") + 1;
		BASE64Decoder decode = new BASE64Decoder();
		byte[] firma = decode.decodeBuffer(strFirmaRequest.substring(coma, strFirmaRequest.length()));
		
		int guion = strIdComponente.lastIndexOf("-") + 1;
		String llave = strIdComponente.substring(guion, strIdComponente.length());
		
		getComunicationBean().getMapaParametrosReporte().put(llave, firma);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Servlet de firma.";
	}

	/* get Sessions, Requests y Communication Beans */
	public Object findBean(String beanName) {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
	}

	private muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) findBean("SessionBean1");
	}

	private muni.ComunicationBean getComunicationBean() {
		return (muni.ComunicationBean) findBean("ComunicationBean");
	}

}