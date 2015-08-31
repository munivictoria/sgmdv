/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.expedientes.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.trascender.expedientes.recurso.persistent.FaseCatalogo;
import com.trascender.expedientes.recurso.persistent.NodoExpediente;
import com.trascender.expedientes.recurso.persistent.NodoProcedimiento;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;

public class MapUtils {

	public static boolean contiene(Map<Object, List<NodoExpediente>> map, Object o) {
		for(Object key : map.keySet()) {
			if(key instanceof NodoProcedimiento) {
				NodoProcedimiento np = (NodoProcedimiento) key;
				NodoProcedimiento other = (NodoProcedimiento) o;
				if(np.equals(other))
					return true;

			}
			if(key instanceof FaseCatalogo) {
				FaseCatalogo fc = (FaseCatalogo) key;
				FaseCatalogo other = (FaseCatalogo) o;
				if(fc.equals(other))
					return true;
			}
			if(key instanceof TramiteCatalogo) {
				TramiteCatalogo tc = (TramiteCatalogo) key;
				TramiteCatalogo other = (TramiteCatalogo) o;
				if(tc.equals(other))
					return true;
			}
		}
		
		return false;
	}

	public static Object getKey(Map<Object, List<NodoExpediente>> map, Object o) {
		for(Object key : map.keySet()) {
			if(key instanceof NodoProcedimiento) {
				NodoProcedimiento np = (NodoProcedimiento) key;
				NodoProcedimiento other = (NodoProcedimiento) o;
				if(np.equals(other))
					return np;

			}
			if(key instanceof FaseCatalogo) {
				FaseCatalogo fc = (FaseCatalogo) key;
				FaseCatalogo other = (FaseCatalogo) o;
				if(fc.equals(other))
					return fc;
			}
			if(key instanceof TramiteCatalogo) {
				TramiteCatalogo tc = (TramiteCatalogo) key;
				TramiteCatalogo other = (TramiteCatalogo) o;
				if(tc.equals(other))
					return tc;
			}
		}
		
		return null;
	}

	public static void put(Map<Object, List<NodoExpediente>> map, NodoExpediente ne, Object key) {
		if(!contiene(map, key)) {
			List<NodoExpediente> list = new ArrayList<NodoExpediente>();
			list.add(ne);
			map.put(key, list);
		} else {
			map.get(getKey(map, key)).add(ne);
		}
	}

}