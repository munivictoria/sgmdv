/**
 * 
 * © Copyright 2015, CoDeSoftTodos los derechos reservados.
 * 
 */

package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DescargarArchivoServlet extends HttpServlet {

	private static final long serialVersionUID = -171817994046635785L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		byte[] bytes = null;

		while(bytes == null) {
			bytes = (byte[]) session.getAttribute("bytesDocumentoDescargable");
			try {
				Thread.sleep(1000);
			} catch(InterruptedException ex) {
				Logger.getLogger(ImageServlet.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		session.removeAttribute("bytesDocumentoDescargable");

		String nombreArchivo = null;

		while(nombreArchivo == null) {
			nombreArchivo = (String) session.getAttribute("nombreArchivoOriginal");
			try {
				Thread.sleep(1000);
			} catch(InterruptedException ex) {
				Logger.getLogger(ImageServlet.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		session.removeAttribute("nombreArchivoOriginal");
		
		String formatoArchivo = (String) session.getAttribute("formatoDeArchivo");
		if (formatoArchivo == null || formatoArchivo.equals("application/force-download")) {
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename=" + nombreArchivo);
		} else {
			response.setContentType(formatoArchivo);
			session.removeAttribute("formatoDeArchivo");
			response.setHeader("Content-Disposition", "inline; filename=" + nombreArchivo);
		}
		
		response.setHeader("Content-Length", String.valueOf(bytes.length));

		OutputStream output = response.getOutputStream();
		output.write(bytes);
		output.flush();
		output.close();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}
	
}