package com.trascender.catastro.recurso.metamodel;

import java.util.Date;

import com.trascender.catastro.enums.AfectacionesCatastrales;
import com.trascender.catastro.enums.ClasificacionCatSegunSuUso;
import com.trascender.catastro.recurso.persistent.AsociacionParcela;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.catastro.recurso.persistent.NomenclaturaCatastral;
import com.trascender.catastro.recurso.persistent.Parcela.TipoParcela;
import com.trascender.catastro.recurso.persistent.ParcelaPorCuadra;
import com.trascender.catastro.recurso.persistent.PlanoConstruccion;
import com.trascender.catastro.recurso.persistent.PlanoMensura;
import com.trascender.catastro.recurso.persistent.Planta;
import com.trascender.catastro.recurso.persistent.RegistroMejora;
import com.trascender.catastro.recurso.persistent.SubParcela;
import com.trascender.catastro.recurso.persistent.TituloPropiedadParcelario;
import com.trascender.framework.recurso.metamodel._AtributoDinamico;
import com.trascender.framework.recurso.metamodel._Domicilio;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

/**
 * @author jsantacruz
 */
public class _Parcela extends MetaClase {

	private static final long serialVersionUID = 720875139207963288L;
	
	public static _Parcela i(){
		return new _Parcela();
	}
	
	public _Parcela(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
	}

	public _Parcela(String nombre){
		super(nombre);
		this.init();
	}
	
	public _Parcela(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id parcela", visibleEnTabla = true, clase = long.class)
	public String idParcela = this.getNombreCompleto("idParcela");
	
	@Atributo(name = "Numero de parcela provincial", visibleEnTabla = true, clase = String.class)
	public String nroPartidaProvincial = this.getNombreCompleto("nroPartidaProvincial");
	
	@Atributo(name = "Numero de registro", visibleEnTabla = true, clase = String.class)
	public String nroRegistro = this.getNombreCompleto("nroRegistro");
	
	@Atributo(name = "Numero de cuenta", visibleEnTabla = true, clase = Integer.class)
	public String nroCuenta = this.getNombreCompleto("nroCuenta");
	
	@Atributo(name = "Numero de matricula", visibleEnTabla = true, clase = String.class)
	public String nroMatricula = this.getNombreCompleto("nroMatricula");
	
	@Atributo(name = "Supeficie", visibleEnTabla = true, clase = Double.class)
	public String superficie = this.getNombreCompleto("superficie");
	
	@Atributo(name = "Fecha alta", visibleEnTabla = true, clase = Date.class)
	public String fechaAlta = this.getNombreCompleto("fechaAlta");
	
	@Atributo(name = "Lista parcelas por cuadra", visibleEnTabla = true, clase = ParcelaPorCuadra.class)
	public _ParcelaPorCuadra listaParcelasPorCuadra = validarInstanciacion(_ParcelaPorCuadra.class, "listaParcelasPorCuadra");
//	public _ParcelaPorCuadra listaParcelasPorCuadra = new _ParcelaPorCuadra(getNombreCompleto("listaParcelasPorCuadra"));
	
	@Atributo(name = "Lista registros mejora", visibleEnTabla = true, clase = RegistroMejora.class)
	public _RegistroMejora listaRegistrosMejora = validarInstanciacion(_RegistroMejora.class, "listaRegistrosMejora");
//	public _RegistroMejora listaRegistrosMejora = new _RegistroMejora(getNombreCompleto("listaRegistrosMejora"));
	
	@Atributo(name = "Metros de frente", visibleEnTabla = true, clase = Double.class)
	public String metrosFrente = this.getNombreCompleto("metrosFrente");
	
	@Atributo(name = "Titulo propiedad", visibleEnTabla = true, clase = TituloPropiedadParcelario.class)
	public _TituloPropiedadParcelario tituloPropiedad = validarInstanciacion(_TituloPropiedadParcelario.class, "tituloPropiedad");
//	public _TituloPropiedadParcelario tituloPropiedad = new _TituloPropiedadParcelario(getNombreCompleto("tituloPropiedad"));
	
	@Atributo(name = "Plano mesura", visibleEnTabla = true, clase = PlanoMensura.class)
	public _PlanoMensura planoMensura = validarInstanciacion(_PlanoMensura.class, "planoMensura");
//	public _PlanoMensura planoMensura = new _PlanoMensura(getNombreCompleto("planoMensura"));
	
	@Atributo(name = "Plano construccion", visibleEnTabla = true, clase = PlanoConstruccion.class)
	public _PlanoConstruccion planoConstruccion = validarInstanciacion(_PlanoConstruccion.class, "planoConstruccion");
	
	@Atributo(name = "Domicilio parcelario", visibleEnTabla = true, clase = Domicilio.class)
	public _Domicilio domicilioParcelario = validarInstanciacion(_Domicilio.class, "domicilioParcelario");
	
	@Atributo(name = "Manzana", visibleEnTabla = true, clase = Manzana.class)
	public _Manzana manzana = validarInstanciacion(_Manzana.class, "manzana");
	
	@Atributo(name = "Avaluo por mejoras", visibleEnTabla = true, clase = Double.class)
	public String avaluoPorMejoras = this.getNombreCompleto("avaluoPorMejoras");
	
	@Atributo(name = "Avaluo terreno", visibleEnTabla = true, clase = Double.class)
	public String avaluoTerreno = this.getNombreCompleto("avaluoTerreno");
	
	@Atributo(name = "Lista asocioacion parcela", visibleEnTabla = true, clase = AsociacionParcela.class)
	public _AsociacionParcela listaAsociacionParcela = validarInstanciacion(_AsociacionParcela.class, "listaAsociacionParcela");
//	public _AsociacionParcela listaAsociacionParcela = new _AsociacionParcela(getNombreCompleto("listaAsociacionParcela"));
	
	@Atributo(name = "Lista subparcelas", visibleEnTabla = true, clase = SubParcela.class)
	public _SubParcela listaSubParcelas = validarInstanciacion(_SubParcela.class, "listaSubParcelas");
//	public _SubParcela listaSubParcelas = new _SubParcela(getNombreCompleto("listaSubParcelas"));
	
	@Atributo(name = "Planta", visibleEnTabla = true, clase = Planta.class)
	public _Planta planta = validarInstanciacion(_Planta.class, "planta");
	
	@Atributo(name = "Nomenclatura catastral", visibleEnTabla = true, clase = NomenclaturaCatastral.class)
	public _NomenclaturaCatastral nomenclaturaCatastral = validarInstanciacion(_NomenclaturaCatastral.class, "nomenclaturaCatastral");
	
	@Atributo(name = "Tipo residencial", visibleEnTabla = true, clase = ClasificacionCatSegunSuUso.Residencial.class)
	public String tipoResidencial = this.getNombreCompleto("tipoResidencial");
	
	@Atributo(name = "Tipo comercial", visibleEnTabla = true, clase = ClasificacionCatSegunSuUso.Comercial.class)
	public String tipoComercial = this.getNombreCompleto("tipoComercial");
	
	@Atributo(name = "Tipo de equipamiento", visibleEnTabla = true, clase = ClasificacionCatSegunSuUso.Equipamiento.class)
	public String tipoEquipamiento = this.getNombreCompleto("tipoEquipamiento");
	
	@Atributo(name = "Tipo varios", visibleEnTabla = true, clase = ClasificacionCatSegunSuUso.Varios.class)
	public String tipoVarios = this.getNombreCompleto("tipoVarios");
	
	@Atributo(name = "Tipo rural", visibleEnTabla = true, clase = ClasificacionCatSegunSuUso.Rural.class)
	public String tipoRural = this.getNombreCompleto("tipoRural");
	
	@Atributo(name = "Afectaciones explicitas", visibleEnTabla = true, clase = AfectacionesCatastrales.Explicitas.class)
	public String afectacionesExplicitas = this.getNombreCompleto("afectacionesExplicitas");
	
	@Atributo(name = "Afectaciones no explicitas", visibleEnTabla = true, clase = AfectacionesCatastrales.NoExplicitas.class)
	public String afectacionesNoExplicitas = this.getNombreCompleto("afectacionesNoExplicitas");
	
	@Atributo(name = "Restricciones dominio explicitas", visibleEnTabla = true, clase = AfectacionesCatastrales.RestriccionesDominioExplicitas.class)
	public String restriccionesDominioExplicitas = this.getNombreCompleto("restriccionesDominioExplicitas");
	
	@Atributo(name = "Restricciones dominio no explicitas", visibleEnTabla = true, clase = AfectacionesCatastrales.RestriccionesDominioNoExplicitas.class)
	public String restriccionesDominicioNoExplicitas = this.getNombreCompleto("restriccionesDominicioNoExplicitas");
	
	@Atributo(name = "Tipo parcela", visibleEnTabla = true, clase = TipoParcela.class)
	public String tipoParcela = this.getNombreCompleto("tipoParcela");
	
	@Atributo(name = "Lista de atributos dinamicos", visibleEnTabla = true, clase = AtributoDinamico.class)
	public _AtributoDinamico listaAtributosDinamicos = validarInstanciacion(_AtributoDinamico.class, "listaAtributosDinamicos");
	
	@Atributo(name = "Periodicidad Auxiliar", visibleEnTabla = true, clase = String.class)
	public String periodicidadAuxiliar = this.getNombreCompleto("periodicidadAuxiliar");

}
