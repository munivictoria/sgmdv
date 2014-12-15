/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jasper;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

/**
 *
 * @author mariano
 */
public class ImpresionServlet extends HttpServlet {

    public ImpresionServlet() {
        super();
    }

    /*
     * protected void processRequest(HttpServletRequest request,
     * HttpServletResponse response) throws ServletException, IOException {
     * response.setContentType("text/html;charset=UTF-8"); PrintWriter out =
     * response.getWriter(); try {
     *
     * } finally { out.close(); } }
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {       
        ThreadImpresion locImpresion = new ThreadImpresion(request, response);
        locImpresion.run();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    class ThreadImpresion {
        
        private HttpServletRequest request;
        private HttpServletResponse response;
        
        public ThreadImpresion(HttpServletRequest request, HttpServletResponse response){
            this.request = request;
            this.response = response;
        }

        public void run(){
            OutputStream outputStream = null;
            try{
                HttpSession session = request.getSession();
                JasperPrint jasperPrint = null;
                boolean errorEnReporte = false;
                String reportName;
                JRExporter exporter;
                do{
                    // se traen los objetos print y repotName de sessioon para generar
                    // el reporte
                    jasperPrint = (JasperPrint) session.getAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE);
                    errorEnReporte = session.getAttribute("ErrorEnReporte") != null;
                    if (jasperPrint == null){
                        Thread.sleep(1500);
                    }
                } while (jasperPrint == null && !errorEnReporte);

                //Si fue cargado en la sesion un objeto con la llave "ErrorEnReporte" hay que cerrar la ventana
                if (errorEnReporte) {
                    session.removeAttribute("ErrorEnReporte");
                    response.getWriter().write("<script language='javascript'>window.setFocus();</script>");
                    response.getWriter().write("<script language='javascript'>window.close();</script>");
                } else { //sino crea el Reporte
                    
                    reportName = (String) session.getAttribute("reportName");
                    Integer formatoReporte = (Integer) session.getAttribute(ConstantesReportes.FORMATO_REPORTE);

                    // se indica que el contenido de response,y se le asigna un
                    // nombre

                    response.setContentType(this.getContentType(formatoReporte));
                    response.setHeader("Content-Disposition", "inline; filename=" + reportName + this.getExtension(formatoReporte));

                    exporter = this.getExporter(formatoReporte);
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    outputStream = response.getOutputStream();
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
                    
                    exporter.exportReport();
                    session.removeAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE);
                    session.removeAttribute("reportName");
                    session.removeAttribute("formatoReporte");
                    session.removeAttribute(ConstantesReportes.FORMATO_REPORTE);
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        
         private String getContentType(int formatoReporte){
            if (formatoReporte == ConstantesReportes.XLSX){
                return "application/x-excel";
            } else if (formatoReporte == ConstantesReportes.PDF){
                return "application/pdf";
            } else {
                return "";
            }
        }
        
        private String getExtension(int formatoReporte){
            if (formatoReporte == ConstantesReportes.XLSX){
                return ".xlsx";
            } else if (formatoReporte == ConstantesReportes.PDF){
                return ".pdf";
            } else {
                return "";
            }
        }
        
        private JRExporter getExporter(int formatoReporte){
             if (formatoReporte == ConstantesReportes.XLSX){
                JRXlsxExporter exporter =  new JRXlsxExporter();
                return exporter;
            } else if (formatoReporte == ConstantesReportes.PDF){
                return new JRPdfExporter();
            } else {
                return null;
            }
        }
    }
    
    
}
