package com.trascender.framework.recurso.metamodel;

import java.util.Map.Entry;

import org.apache.ws.scout.model.uddi.v2.GetAssertionStatusReport;

import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.AsociacionMetaclase;
import com.trascender.framework.util.Metaclase;

public class _Area extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8340127772270801702L;
	
	public static _Area i(){
		return getInstancia(_Area.class);
	}
	
	private void metodoBoludo(){
		estado().provincia().pais().continente().nombre;
		estado.referencia
		
		_FacturaProveedor.i().listaPagosOrdenCompra().ordenCompra().area().secretaria()
	}

	@Atributo(name = "Id area", visibleEnTabla = true, clase = Long.class)
	public String idArea = this.getNombreCompleto("idArea");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = this.getNombreCompleto("nombre");
	
	@Atributo(name = "Descripcion", visibleEnTabla = true, clase = String.class)
	public String descripcion = getNombreCompleto("descripcion");
	
	// @Atributo(name = "Estado", visibleEnTabla = true, clase = Localidad.class)
	// public _Localidad estado = validarInstanciacion(_Localidad.class, "estado");

	@Atributo(name = "Estado", visibleEnTabla = true, clase = Localidad.class)
	private AsociacionMetaclase<_Area, _Localidad> estado = getAsociacion("estado", _Area.class, _Localidad.class);

	public _Localidad estado() {
		return estado.referencia;
	}

}
