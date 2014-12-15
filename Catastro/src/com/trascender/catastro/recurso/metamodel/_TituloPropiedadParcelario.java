package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.TituloPropiedadParcelario.TipoTransaccionCatastral;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _TituloPropiedadParcelario extends _TituloPropiedad {

	/**
	 * 
	 */
	private static final long serialVersionUID = -422391500389679760L;
	
	public static _TituloPropiedadParcelario i(){
		return new _TituloPropiedadParcelario();
	}
	
	public _TituloPropiedadParcelario(String nombre,
			Class<? extends MetaClase> clase) {
		super(nombre, clase);
	}

	public _TituloPropiedadParcelario(String nombre){
		super(nombre);
		this.init();
	}
	
	public _TituloPropiedadParcelario(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Numero de tomo", visibleEnTabla = true, clase = Integer.class)
	public String nroTomo = this.getNombreCompleto("nroTomo");
	
	@Atributo(name = "Numero de folio", visibleEnTabla = true, clase = Integer.class)
	public String nroFolio = this.getNombreCompleto("nroFolio");
	
	@Atributo(name = "Asiento", visibleEnTabla = true, clase = Integer.class)
	public String asiento = this.getNombreCompleto("asiento");
	
	@Atributo(name = "Area de registro", visibleEnTabla = true, clase = String.class)
	public String areaRegistro = this.getNombreCompleto("areaRegistro");
	
	@Atributo(name = "Tipo de transaccion catastral", visibleEnTabla = true, clase = TipoTransaccionCatastral.class)
	public String tipoTransaccionCatastral = this.getNombreCompleto("tipoTransaccionCatastral");
	
	@Atributo(name = "Parcela", visibleEnTabla = true, clase = Parcela.class)
	public _Parcela parcela = validarInstanciacion(_Parcela.class, "parcela");
//	public _Parcela parcela = new _Parcela(getNombreCompleto("parcela"));

}
