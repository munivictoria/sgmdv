/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni;

import com.sun.rave.web.ui.component.TextField;
import com.trascender.presentacion.navegacion.ElementoPila;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tincho
 */
public class ManejoDePila {

    public static ElementoPila getElementoPila(SessionBean1 sessionBean1, Long pIdSubSession) {

        return sessionBean1.getMgrPilas().getLastElementoPila(pIdSubSession);
    }

    public static void crearElementoPila(SessionBean1 sessionBean1, Map pMap) {
            
        ElementoPila ep = new ElementoPila();
        ep.setCasoNavegacion((String) pMap.get("CASO_NAVEGACION"));
        ep.setIdPagina((Long) pMap.get("idPagina"));
        ep.setIdSubSesion((Long) pMap.get("idSubSesion"));
        ep.setNombrePagina((String) pMap.get("NOMBRE_PAGINA"));
        
        System.out.println(ep.getIdPagina() + "..   .. " + ep.getIdSubSesion());        
        sessionBean1.getMgrPilas().addElemento(ep);
    }

    public static boolean ultimoElementoPilaDeSubSesion(SessionBean1 sessionBean1, Long pIdSubSession, Long pIdPagina) {
        return sessionBean1.getMgrPilas().isLastElementoPila(pIdSubSession, pIdPagina);
    }

    public static Object obtenerObjetoDelElementoPila(int posicion, Class tipoClase, ElementoPila elementoPila) {
        Object objeto = null;
        try {
            objeto = elementoPila.getObjetos().get(posicion);
            if (objeto == null) {
                objeto = tipoClase.newInstance();
            }
        } catch (Exception ex) {
        }
        return objeto;
    }

    public static void acomodarSeleccionado(RequestBean1 requestBean1, ElementoPila elementoPila) {
        Object seleccionado = requestBean1.getObjetoSeleccion();
        if (seleccionado != null) {
            int posicion = ((Integer) obtenerObjetoDelElementoPila(getCantidadObjetosUsados(elementoPila) - 1, Integer.class, elementoPila)).intValue();
            elementoPila.getObjetos().set(posicion, seleccionado);
        }
    }

    public static int getCantidadObjetosUsados(ElementoPila elementoPila) {
        return elementoPila.getObjetos().size();
    }

    public static void limpiarObjeto(int posicion, TextField campo, ElementoPila elementoPila) {
        try {
            elementoPila.getObjetos().set(posicion, null);
            if (campo != null) {
                campo.setText(null);
            }
        } catch (Exception ex) {
        }
    }   
}
