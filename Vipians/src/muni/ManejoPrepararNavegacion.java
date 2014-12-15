/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni;

import com.trascender.presentacion.navegacion.ElementoPila;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tincho
 */
public class ManejoPrepararNavegacion {

    public static String prepararParaVolver(SessionBean1 sessionBean1, RequestBean1 requestBean1, ElementoPila elementoPila, String pAccionRetorno) {
        requestBean1.setAccion(pAccionRetorno);
        String retorno = null;
        String CASO_NAV_POST_CADUCIDAD = "Main";
        ElementoPila locElementoAnterior = sessionBean1.getMgrPilas().getElementoPilaAnterior(elementoPila);

        if (locElementoAnterior != null) {
            sessionBean1.getMgrPilas().removeElemento(elementoPila);
            requestBean1.setIdSubSesion(locElementoAnterior.getIdSubSesion());
            requestBean1.setIdPagina(locElementoAnterior.getIdPagina());
            requestBean1.setElementoPilaPaginaAnt(locElementoAnterior);
            retorno = locElementoAnterior.getCasoNavegacion();
        }else{
            return CASO_NAV_POST_CADUCIDAD;
        }
        return retorno;
    }

    public static String prepararCaducidad(RequestBean1 requestBean1, String pCaso_Nav_Post_Caducidad, String pCaso_Nav_Caducidad) {
        // redireccionar a pagina con mensaje de caducidad
        requestBean1.setCasoNavegacionPostCaducidad(pCaso_Nav_Post_Caducidad);
        return pCaso_Nav_Caducidad;
    }

    public static Map prerender(RequestBean1 requestBean1, SessionBean1 sessionBean1, Long pIdSubSesion, Long pIdPagina, boolean puede_ser_pag_inicial) {


        boolean existeIdSubSesionReq = false;
        boolean existeIdPaginaReq = false;
        boolean existeIdSubSesionPag = false;
        boolean existeIdPaginaPag = false;
        Map locMapRet = new HashMap();
        Boolean pagina_nueva = Boolean.FALSE;
        Boolean recarga = Boolean.FALSE;

        if (requestBean1 != null) {

            existeIdSubSesionReq = (requestBean1.getIdSubSesion() != null);
            existeIdPaginaReq = (requestBean1.getIdPagina() != null);

            System.out.println(pIdPagina + "   *1*  " + pIdSubSesion);

        }


        existeIdSubSesionPag = pIdSubSesion != null;
        existeIdPaginaPag = pIdPagina != null;

        // Pagina nueva - Inicio de transaccion
        if (!existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {

            if (puede_ser_pag_inicial) {

                Long key = sessionBean1.getMgrPilas().generarClaveUnica();
                pIdPagina = key;
                pIdSubSesion = key;
                pagina_nueva = Boolean.TRUE;
                //this.crearElementoPila();
                System.out.println(pIdPagina + "   *2*  " + pIdSubSesion);
            }
        }

        // Recarga de la misma pagina por validacion
        if (!existeIdSubSesionReq && !existeIdPaginaReq && existeIdSubSesionPag && existeIdPaginaPag) {
            recarga = Boolean.FALSE;
            // no se hace nada por ahora
            //recarga = true;
            // APLICAR LOGICA AQUI.. ver si es as� realmente..
            System.out.println(pIdPagina + "   *3*  " + pIdSubSesion);
        }

        // Pagina nueva - hacia adelante en la transaccion
        // Para el caso de las paginas de inicio de transaccion nunca entra
        if (existeIdSubSesionReq && !existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {

            Long key = sessionBean1.getMgrPilas().generarClaveUnica();
            pIdPagina = key;
            pIdSubSesion = requestBean1.getIdSubSesion();
            pagina_nueva = Boolean.TRUE;
            System.out.println(pIdPagina + "   *4*  " + pIdSubSesion);

        }

        // Pagina nueva - hacia atras en la transaccion
        if (existeIdSubSesionReq && existeIdPaginaReq && !existeIdSubSesionPag && !existeIdPaginaPag) {

            ElementoPila ep = requestBean1.getElementoPilaPaginaAnt();
            pIdPagina = ep.getIdPagina();
            pIdSubSesion = ep.getIdSubSesion();
            
            System.out.println(pIdPagina + "   * 5 *   " + pIdSubSesion);
        }

        locMapRet.put("idPagina", pIdPagina);
        locMapRet.put("idSubSesion", pIdSubSesion);
        locMapRet.put("pagina_nueva", pagina_nueva);
        locMapRet.put("recarga", recarga);

        
        return locMapRet;

    }
}
