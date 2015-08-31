/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.utils;

/*
 * of the Common Development and Distribution License
 * (the License).  You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the license at
 * https://woodstock.dev.java.net/public/CDDLv1.0.html.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at https://woodstock.dev.java.net/public/CDDLv1.0.html.
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * you own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * Copyright 2010 Sun Microsystems, Inc. All rights reserved.
 */
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	public static final char VALID_CHAR = '#';

	/**
	 * <p>
	 * Gets the requested cookie name. This method will ensure invalid characters are not used in the name. The cooresponding <code>setCookieValue</code> call
	 * should be used to ensure proper setting / retrieval of your cookie. (NOTE: there is a JS version of these methods as well.)
	 * </p>
	 */
	public static Cookie getCookieValue(FacesContext context, String name) {
		name = CookieUtils.getValidCookieString(name);
		return (Cookie) context.getExternalContext().getRequestCookieMap().get(name);
	}

	/**
	 * <p>
	 * Sets the specified cookie name / value. This method will ensure invalid characters are not used in the name. The cooresponding
	 * <code>getCookieValue</code> call should be used to ensure proper setting / retrieval of your cookie. (NOTE: there is a JS version of these methods as
	 * well.)
	 * </p>
	 */
	public static void setCookieValue(FacesContext context, String name, String value) {
		context.getExternalContext().getRequestCookieMap().get(name);
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		name = getValidCookieString(name);
		Cookie cookie = getCookieValue(context, name);
		value = getValidCookieString(value);
		cookie.setValue(value);
		response.addCookie(cookie);
	}

	/**
	 * <p>
	 * Ensure we use a RFC 2109 compliant cookie name and value.
	 * </p>
	 */
	public static String getValidCookieString(String pString) {
		for(char ch : badCookieChars) {
			pString = pString.replace(ch, VALID_CHAR);
		}
		
		return pString;
	}

	// Characters not allowed to be part of a cookie name (RFC 2109)
	private static final char badCookieChars[] = {'(', ')', '<', '>', '@', ',', ';', ':', '\\', '\'', '/', '[', ']', '?', '=', '{', '}', ' ', '\t'};

}