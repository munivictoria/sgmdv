/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.Trees;

import java.util.ArrayList;
import java.util.List;

import com.trascender.expedientes.recurso.persistent.Expediente;
import com.trascender.expedientes.recurso.persistent.Fase;
import com.trascender.expedientes.recurso.persistent.FaseCatalogo;
import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.expedientes.recurso.persistent.Procedimiento;
import com.trascender.expedientes.recurso.persistent.Tramite;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;
import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;

public class CrearDatosNodos {

	private static final String T = "T_";
	private static final String F = "F_";
	private static final String URL_FASE = "/resources/imagenes/arboles/documento.png";
	private static final String URL_TRAMITE = "/resources/imagenes/arboles/permiso.png";

	@SuppressWarnings("rawtypes")
	public static List<TreeView.Datos> crearNodosDatos(List lista) {
		List<TreeView.Datos> listaRetorno = new ArrayList<TreeView.Datos>();

		for(int i = 0; i < lista.size(); i++) {
			listaRetorno.add(crearDatos(lista.get(i), i));
		}

		return listaRetorno;
	}

	public static TreeView.Datos crearDatos(Object pObject, int posicion) {
		if(pObject instanceof Procedimiento) {
			Procedimiento p = (Procedimiento) pObject;
			return new DatosNodo(p.getNombre(), "tnRaiz", URL_FASE, crearNodosDatos(p.getListaFasesProcedimiento()));
		}

		if(pObject instanceof Expediente) {
			Expediente e = (Expediente) pObject;
			return new DatosNodo(e.getAsunto(), "tnRaiz", URL_FASE, crearNodosDatos(e.getListaNodosExpedientes()));
		}

		if(pObject instanceof Fase) {
			Fase f = (Fase) pObject;
			return new DatosNodo(f.getPlantilla().toString(), F + posicion, URL_FASE, crearNodosDatos(f.getListaNodosExpedientes()));
		}
		if(pObject instanceof FaseProcedimiento) {
			FaseProcedimiento fp = (FaseProcedimiento) pObject;
			return new DatosNodo(fp.getFaseCatalogo().getNombre(), F + posicion, URL_FASE, crearNodosDatos(fp.getListaTramitesProcedimientos()));
		}

		if(pObject instanceof FaseCatalogo) {
			FaseCatalogo fp = (FaseCatalogo) pObject;
			return new DatosNodo(fp.getNombre(), F + posicion, URL_FASE, crearNodosDatos(fp.getListaTramitesCatalogos()));
		}

		if(pObject instanceof Tramite) {
			Tramite t = (Tramite) pObject;
			return new DatosNodo(t.getPlantilla().toString(), T + posicion, URL_TRAMITE, null);
		}
		if(pObject instanceof TramiteProcedimiento) {
			TramiteProcedimiento tp = (TramiteProcedimiento) pObject;
			return new DatosNodo(tp.getTramiteCatalogo().getNombre(), T + posicion, URL_TRAMITE, null);
		}

		if(pObject instanceof TramiteCatalogo) {
			TramiteCatalogo tp = (TramiteCatalogo) pObject;
			return new DatosNodo(tp.getNombre(), T + posicion, URL_TRAMITE, null);
		}

		return null;
	}

	public static int getPosicionElemento(String nodoId) {
		try {
			return Integer.parseInt(nodoId.substring(nodoId.indexOf('_') + 1));
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}