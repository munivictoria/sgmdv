/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.*;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fer
 */
public class ImageServlet extends HttpServlet {

    public static final String  ARREGLO_BYTES = "arregloBytes";
    public static final String NOMBRE_IMAGEN = "nombreImagen";
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            /* En caso de pasar una variable  al servelet como parametro url
             * Decodifica una variable string y la pasa a arreglo de bytes 
             * 
             * byte[] logo = StringToBytes(request.getParameter("arregloLogo"));
            String nombreLogo = request.getParameter("nombreLogo");
            */
            HttpSession session = request.getSession();
            byte[] imagenBytes;
            String nombreLogo;
            int contador = 0;
            do{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                imagenBytes = (byte[]) session.getAttribute(ARREGLO_BYTES);         
                nombreLogo = (String)session.getAttribute(NOMBRE_IMAGEN);
                if (++contador >  5){break;}
            } while(imagenBytes==null && (nombreLogo == null || !nombreLogo.equals("")));
            
            BufferedInputStream input = null;
            BufferedOutputStream output = null;
             try {
                response.setContentType(getServletContext().getMimeType(nombreLogo));
                response.setContentLength(imagenBytes.length);
                input = new BufferedInputStream(new ByteArrayInputStream(imagenBytes));
            }catch(Exception ex ){
                System.out.println("Problema al mostrar la imagen:");
                ex.printStackTrace();
                //Se le pide al servlet que devuelva una imagen en blanco
                ServletContext contexto = getServletContext();
                
                InputStream isAux = contexto.getResourceAsStream("/resources/imagenes/fondo_blanco.jpeg");
                input = new BufferedInputStream(isAux);
                response.setContentType(getServletContext().getMimeType("fondo_blanco.jpeg"));
            } finally {
                response.setHeader("Content-Disposition", "inline;filename=\"" + "logo" + "\"");
                output = new BufferedOutputStream(response.getOutputStream());
                byte[] buffer = new byte[1024];

                for (int length; (length = input.read(buffer)) > -1;) {
                    System.out.println("FOR?");
                    output.write(buffer, 0, length);
                }
                 
                session.removeAttribute(ARREGLO_BYTES);
                session.removeAttribute(NOMBRE_IMAGEN);
                if (output != null) try { output.close(); } catch (IOException logOrIgnore) {}
                if (input != null) try { input.close(); } catch (IOException logOrIgnore) {}
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
     protected byte[] StringToBytes(String cadena)
    {
        byte[] codificador = cadena.getBytes();
        return codificador;
    }
}
