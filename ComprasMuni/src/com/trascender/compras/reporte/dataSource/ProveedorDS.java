package com.trascender.compras.reporte.dataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.trascender.compras.recurso.persistent.suministros.BienProvisto;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaFisica;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.util.TrascenderDataSource;
import com.trascender.framework.util.Util;

public class ProveedorDS extends TrascenderDataSource{
	
	private int lineaActual = -1;
	private Proveedor proveedor;
	private List<BienProvisto> listaBienesProvistos;
	private Map<String, Object> mapaParametros;
	
	public ProveedorDS(Proveedor pProveedor){
		this.proveedor = pProveedor;
		this.listaBienesProvistos = pProveedor.getListaBienProvisto();
		this.armarParmatros();
	}
	
	private void armarParmatros(){
		mapaParametros = new HashMap<String, Object>();
		mapaParametros.put("PAR_IMAGEN", this.getLogoMunicipalidad());
		mapaParametros.put("PAR_TITULO", this.getTituloReporte());
		mapaParametros.put("PAR_SUBTITULO", this.getSubtituloReporte());
		Persona locProveedorLocal = proveedor.getProveedorLocal();
		if (locProveedorLocal instanceof PersonaFisica){
			PersonaFisica locPersonaFisica = (PersonaFisica) locProveedorLocal;
			mapaParametros.put("PAR_PROVEEDOR_RESPONSABLE", Util.getFormatIfNull(locPersonaFisica.getApellido() + ", " + locPersonaFisica.getNombre()));
		} else {
			PersonaJuridica locPersonaJuridica = (PersonaJuridica) locProveedorLocal;
			mapaParametros.put("PAR_PROVEEDOR_RESPONSABLE", Util.getFormatIfNull(locPersonaJuridica.getRazonSocial()));
		}
		mapaParametros.put("PAR_RAZON_SOCIAL", Util.getFormatIfNull(proveedor.toString()));
		mapaParametros.put("PAR_CUIT", Util.getFormatIfNull(proveedor.getProveedorLocal().getCuim()));
		mapaParametros.put("PAR_ESTADO", Util.getFormatIfNull(proveedor.getEstado().toString()));
		mapaParametros.put("PAR_TELEFONO", Util.getFormatIfNull(proveedor.getTelefono()));
		mapaParametros.put("PAR_CALLE_NUMERO", Util.getFormatIfNull(locProveedorLocal.getDomicilioPostal().getCalle()) + " " + Util.getFormatIfNull(locProveedorLocal.getDomicilioPostal().getNumero()));
		
	}

	public Object getFieldValue(JRField field) throws JRException 
	{
		Object valor = null;
		if (field.getName().equals("F_PRODUCTO_NOMBRE")){
			valor = Util.getFormatIfNull(getBienActual().getNombre());
		} else if (field.getName().equals("F_PRODUCTO_DESCRIPCION")){
			valor = Util.getFormatIfNull(getBienActual().getBien().getDescripcion());
		} else if (field.getName().equals("F_PRODUCTO_ESTADO")){
			valor = Util.getFormatIfNull(getBienActual().getBien().getEstado().toString());
		} else if (field.getName().equals("F_PRODUCTO_PRECIO")){
			valor = Util.getFormatIfNull(getBienActual().getPrecio());
		}
		return valor;
	}
	
	private BienProvisto getBienActual(){
		return listaBienesProvistos.get(lineaActual);
	}

	public boolean next() throws JRException {
		return ++lineaActual < listaBienesProvistos.size();
	}

	public Map<String, Object> getMapaParametros() {
		return mapaParametros;
	}

	public String getNombreReporte() {
		return "Reporte_Proveedor.jasper";
	}

}
