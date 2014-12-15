/*
 * MgrPilas.java
 *
 * Created on 12 de octubre de 2006, 07:52
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.trascender.presentacion.navegacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Manager de Elementos Pila.
 * 20/09/2006
 * @author Ariel & Juan Pablo
 */
public class MgrPilas implements Serializable {
    
    private static final long serialVersionUID = 6495359515940884993L;
    private Random r;
    private List listaPilas = new ArrayList();
    private Boolean paginaInicial = true;
    //ArrayList<ElementoPila> listaPilas = new ArrayList<ElementoPila>();
    
    public MgrPilas() {
        r = new Random();
        r.setSeed(new Date().getTime());
    }
    
    public void addElemento(ElementoPila pElemento) {
        Long locIdSubSesion = pElemento.getIdSubSesion();
        int locOrden = 1;
        ElementoPila locElemento = null;
        
        for (int i=0; i<listaPilas.size(); i++) {
            locElemento = (ElementoPila) listaPilas.get(i);
            if (locElemento.getIdSubSesion().equals(locIdSubSesion)) {
                locOrden++;
            }
        }
        pElemento.setOrden(locOrden);
        
        listaPilas.add(pElemento);
    }
    
    public void removeElemento(ElementoPila pElemento) {
        listaPilas.remove(pElemento);
    }
    
    public boolean isLastElementoPila(Long pIdSubSesion, Long pIdPagina) {
        boolean esUltimo = false;
        
        System.out.println("pIdSubSesion " + pIdSubSesion);
        System.out.println("pIdPagina " + pIdPagina);
        if (pIdPagina != null && pIdSubSesion != null) {
            ElementoPila ultimoElemento = this.getLastElementoPila(pIdSubSesion);
            System.out.println("Ultimo elemento: " + ultimoElemento);
            System.out.println("ID PAGINA: " + ultimoElemento.getIdPagina());
            esUltimo = ultimoElemento.getIdPagina().equals(pIdPagina);
        }
        
        return esUltimo;
    }
    
    public ElementoPila getElementoPilaAnterior(ElementoPila pElemento) {
        Long locIdSubSesion = pElemento.getIdSubSesion();
        int locOrden        = pElemento.getOrden();
        boolean encontrado  = false;
        
        //Iterator<ElementoPila> it = listaPilas.iterator();
        Iterator it = listaPilas.iterator();
        ElementoPila ep = null;
        while (it.hasNext() && !encontrado) {
            ep = (ElementoPila) it.next();
            if (ep.getIdSubSesion().equals(locIdSubSesion)) {
                if (ep.getOrden() == (locOrden-1)) {
                    encontrado = true;
                }
            }
        }
        if (!encontrado) ep = null;
        return ep;
    }
    
    public Long generarClaveUnica() {
        Long clave = new Long(this.r.nextLong());
        return clave;
    }
    
    public ElementoPila getLastElementoPila(Long pIdSubSesion) {
        ElementoPila locElemento = null;
        int locOrden = 0;
        
        //Iterator<ElementoPila> it = listaPilas.iterator();
        Iterator it = listaPilas.iterator();
        ElementoPila ep = null;
        while (it.hasNext()) {
            ep = (ElementoPila) it.next();
            if ((ep.getIdSubSesion().equals(pIdSubSesion)) &&
                    (ep.getOrden() > locOrden)) {
                locElemento = ep;
                locOrden++;
            }
        }
        return locElemento;
    }
    
    public String getRutaOperacion(Long pIdSubSesion) {
        String ruta = "";
        int maximoCaracteres = 160;
        String separador = "&nbsp;&raquo;&nbsp;";
        String indicadorContinuacion = "...";
        
        Iterator it = listaPilas.iterator();
        ElementoPila ep = null;
        int cont = 0;
        while(it.hasNext()) {
            ep = (ElementoPila) it.next();
            if (ep.getIdSubSesion().equals(pIdSubSesion)) {
                ruta += ep.getNombrePagina() + separador;
                cont++;
            }
        }
        ruta = ruta.substring(0, ruta.length()-separador.length()) + ".";
        if (ruta.length() > (maximoCaracteres+indicadorContinuacion.length())) {
            int cantABorrar = ruta.length() - maximoCaracteres -1;
            ruta = indicadorContinuacion + ruta.substring(cantABorrar, ruta.length());
        }
        //return "<font size='11px'><b>Ruta de la operaci&oacute;n:&nbsp;&nbsp;</b></font>" + ruta;
        
        this.paginaInicial = (cont == 1) ? true : false;
        
        return "<b>Ruta de la operaci&oacute;n:&nbsp;&nbsp;</b>" + ruta;
    }
    
    public Boolean getPaginaInicial() {
    	return this.paginaInicial;
    }
}
