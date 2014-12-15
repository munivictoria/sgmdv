package muni.expedientes.ABMProcedimiento;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class MyCookieHandler extends CookieHandler {
	private final Map<String, List<String>> cookies = new HashMap<String, List<String>>();

	// form1:incTreeProcedimiento:trProcedimiento-hi=form1:incTreeProcedimiento:trProcedimiento:tnRaiz:F_1:T_0;
	// path=/Vipians/faces/expedientes/ABMProcedimiento/;
	// domain=localhost;

	@Override
	public Map<String, List<String>> get(URI uri, Map<String, List<String>> requestHeaders)
			throws IOException {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();

		Enumeration<String> enu = request.getHeaderNames();
		while (enu.hasMoreElements()) {
         String str = enu.nextElement();
			System.out.println(str+" ** "+ request.getHeader(str));
		}

		Map<String, List<String>> ret = new HashMap<String, List<String>>();
		synchronized (cookies) {
			List<String> store = cookies.get(uri);
			if (store != null) {
				store = Collections.unmodifiableList(store);
				ret.put("Cookie", store);
			}
		}
		return Collections.unmodifiableMap(ret);
	}

	@Override
	public void put(URI uri, Map<String, List<String>> responseHeaders) throws IOException {
		List<String> newCookies = responseHeaders.get("Set-Cookie");
		if (newCookies != null) {
			synchronized (cookies) {
				List<String> store = cookies.get(uri.getHost());
				if (store == null) {
					store = new ArrayList<String>();
					cookies.put(uri.getHost(), store);
				}
				store.addAll(newCookies);
			}
		}
	}

}
