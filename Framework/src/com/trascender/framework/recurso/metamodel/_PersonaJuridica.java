package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.persistent.PersonaJuridica.OrganismoEmisor;
import com.trascender.framework.recurso.persistent.PersonaJuridica.TipoSocietario;
import com.trascender.framework.recurso.persistent.Socio;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _PersonaJuridica extends _Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1703414039808778969L;
	
	public static _PersonaJuridica i(){
		return new _PersonaJuridica();
	}
	
	public _PersonaJuridica(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _PersonaJuridica(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _PersonaJuridica(){
		this.init();
	}
	
	private void init(){
		this.razonSocial = getNombreCompleto("razonSocial");
	}
	
	@Atributo(name = "Razon social", visibleEnTabla = true, clase = String.class)
	public String razonSocial = getNombreCompleto("razonSocial");
	
	@Atributo(name = "Numero de sociedad", visibleEnTabla = true, clase = String.class)
	public String numeroSociedad = getNombreCompleto("numeroSociedad");
	
	@Atributo(name = "Lista de socios", visibleEnTabla = true, clase = Socio.class)
	public _Socio listaSocios = validarInstanciacion(_Socio.class, "listaSocios");
	
	@Atributo(name = "Tipo de societario", visibleEnTabla = true, clase = TipoSocietario.class)
	public String tipoSocietario = getNombreCompleto("tipoSocietario");
	
	@Atributo(name = "Organismo emisor", visibleEnTabla = true, clase = OrganismoEmisor.class)
	public String organismoEmisor = getNombreCompleto("organismoEmisor");
	
	@Atributo(name = "Nacionalidad", visibleEnTabla = true, clase = String.class)
	public String nacionalidad = getNombreCompleto("nacionalidad");
	
	@Atributo(name = "Nombre fantasia", visibleEnTabla = true, clase = String.class)
	public String nombreFantasia = getNombreCompleto("nombreFantasia");
	
	@Atributo(name = "Numero ingresos brutos", visibleEnTabla = true, clase = String.class)
	public String numeroIngresosBrutos = getNombreCompleto("numeroIngresosBrutos");
	
	@Atributo(name = "Numero de convenio multilateral", visibleEnTabla = true, clase = String.class)
	public String numeroConvenioMultilateral = getNombreCompleto("numeroConvenioMultilateral");

}
