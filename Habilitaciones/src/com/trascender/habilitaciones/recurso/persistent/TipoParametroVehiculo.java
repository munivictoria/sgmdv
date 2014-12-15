package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.Marca;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.TipoVehiculo;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;

@Entity
@DiscriminatorValue(value = "VEHICULO")
public class TipoParametroVehiculo extends TipoParametro{

	public static final long serialVersionUID = 2263325795444077325L;
	
	public enum AtributoVehiculo{
		NOMBRE_MODELO, MINIMO_MODELO, NOMBRE_MARCA, CODIGO_MARCA, NOMBRE_TIPO_VEHICULO, CODIGO_TIPO_VEHICULO,
		ANIO, PESO, CAPACIDAD;
		
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ATRIBUTO")
	private AtributoVehiculo atributoVehiculo;

	public AtributoVehiculo getAtributoVehiculo() {
		return atributoVehiculo;
	}
	public void setAtributoVehiculo(AtributoVehiculo atributoVehiculo) {
		this.atributoVehiculo = atributoVehiculo;
	}
	
	@Override
	public Object getValor(DocHabilitanteEspecializado pDocumentoHabilitanteEspecializado) throws TrascenderException {
		if (pDocumentoHabilitanteEspecializado instanceof DocumentoAutomotor){
			DocumentoAutomotor locDocAutomotor = (DocumentoAutomotor)pDocumentoHabilitanteEspecializado;
			Vehiculo locVehiculo = locDocAutomotor.getVehiculo();
			Modelo locModelo = locDocAutomotor.getVehiculo().getModelo();
			Marca locMarca = locDocAutomotor.getVehiculo().getModelo().getMarca();
			TipoVehiculo locTipoVehiculo = locDocAutomotor.getVehiculo().getModelo().getTipoVehiculo();
			
			switch (this.atributoVehiculo){
				case NOMBRE_MODELO: return locModelo.getNombre()!=null?locModelo.getNombre():"";
				case MINIMO_MODELO: return locModelo.getMinimo()!=null?locModelo.getMinimo():"";
				case NOMBRE_MARCA: return locMarca.getNombre()!=null?locMarca.getNombre():"";
				case CODIGO_MARCA: return locMarca.getCodigo()!=null?locMarca.getCodigo():"";
				case NOMBRE_TIPO_VEHICULO: return locTipoVehiculo.getNombre()!=null?locTipoVehiculo.getNombre():"";
				case CODIGO_TIPO_VEHICULO: return locTipoVehiculo.getCodigo()!=null?locTipoVehiculo.getCodigo():"";
				case ANIO: return locVehiculo.getAnio()!=null?locVehiculo.getAnio().doubleValue():0;
				case PESO: return locVehiculo.getPeso()!=null?locVehiculo.getPeso():0d;
				case CAPACIDAD: return locVehiculo.getCapacidad()!=null?locVehiculo.getCapacidad():0d;
			}
			return 0d;
		}
		else{
			throw new IllegalArgumentException("El documento habilitante especializado debe ser del tipo DocumentoAutomotor");
			
		}
	}
	
	@Override
	public void setNombreAtributo(String pNombreAtributo) {
		this.atributoVehiculo = AtributoVehiculo.valueOf(pNombreAtributo);
	}
}
