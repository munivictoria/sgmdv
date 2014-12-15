/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni;

import java.util.Enumeration;

import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author tincho
 */
public class MetodosEstaticos {

	public static String obtenerCookie(String nombreCookie) {
		String retorno = null;
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		request.getAuthType();
		Cookie[] cookies = request.getCookies();

		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals(nombreCookie) && !cookie.getValue().equals("null")) {
				retorno = cookie.getValue();
				retorno = retorno.replace("/", ":");
				break;
			}
		}
		return retorno;
	}

	public static void eliminarCookie(String nombreCookie) {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals(nombreCookie)) {
				cookie.setMaxAge(-1);
				cookie.setValue("null");
				response.addCookie(cookie);
				break;
			}
		}
	}

	public static void setCookie(String nombreCookie, String value) {
//		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
//				.getExternalContext().getResponse();
//		response.setHeader(name, value);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals(nombreCookie)) {
				cookie.setValue(value);
				request.getCookies()[i] = cookie;
				break;
			}
		}
	}
	
//	http://localhost:8080/Vipians/theme/com/sun/rave/web/ui/mytheme/javascript/tree.js
public static void getHeaderCookie(){
	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
			.getExternalContext().getRequest();
	        Enumeration<String> enu =  request.getHeaders("cookie");
	        while (enu.hasMoreElements()) {
				String str = enu.nextElement();
			    String[] cookies = str.split(";");
				for (int i = 0; i < cookies.length; i++) {
					String browserCookie = "form1:incTreeProcedimiento:trProcedimiento-hi";
					 String cookie = cookies[i];
					String headerCookie = cookie.substring(0, browserCookie.length()-1);
					if (headerCookie.equals(browserCookie)) {
						String cookieValue= cookie.replace(headerCookie+"=","");
						 System.out.println(headerCookie +" ¿? "+ cookieValue );
						break;
					} 
				}  	
			}
	        
}
	
	

	public static MethodExpression createMethodExpression(String valueExpression,
			Class<?> expectedReturnType, Class<?>[] expectedParamTypes) {
		MethodExpression methodExpression = null;
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			ExpressionFactory factory = fc.getApplication().getExpressionFactory();
			methodExpression = factory.createMethodExpression(fc.getELContext(), valueExpression,
					expectedReturnType, expectedParamTypes);
		} catch (Exception e) {
			throw new FacesException("Method expression '" + valueExpression
					+ "' could not be created.");
		}

		return methodExpression;
	}

}
