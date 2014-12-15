package com.trascender.catastro.recurso.persistent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Representa un coeficiente de depreciaci�n
 * @author Mariano Lusardi
 * @hibernate.class table = "COEFICIENTE_DEPRECIACION" 
 */
@Entity
@Table(name = "COEFICIENTE_DEPRECIACION")
public class CoeficienteDepreciacion implements Serializable{

	/**
	 * 
	 */
	public enum EstadoCoeficiente{
		BUENO,
		REGULAR,
		MALO;

		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}

	}
	public static final long serialVersionUID = -3211159040817183226L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_coeficiente_depreciacion")
	@SequenceGenerator(name = "gen_id_coeficiente_depreciacion", sequenceName = "gen_id_coeficiente_depreciacion",allocationSize = 1)
	@Column(name="ID_COEFICIENTE_DEPRECIACION")
	private long idCoeficienteDepreciacion=-1;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "VALOR_COEF_DEPRECIACION", joinColumns = @JoinColumn(name = "ID_COEFICIENTE_DEPRECIACION"))
	@MapKeyColumn(name = "ESTADO")
	@Column(name = "VALOR")
	@MapKeyEnumerated(EnumType.STRING)
	private Map<EstadoCoeficiente,Double> valor=new HashMap<EstadoCoeficiente,Double>();
	
	@Column(name = "ANIOS_EXISTENCIA")
	private Integer aniosExistencia;
	
	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA")
	private Categoria categoria;
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Integer getAniosExistencia() {
		return aniosExistencia;
	}
	public void setAniosExistencia(Integer aniosExistencia) {
		this.aniosExistencia = aniosExistencia;
	}
	
	public long getIdCoeficienteDepreciacion() {
		return idCoeficienteDepreciacion;
	}
	public void setIdCoeficienteDepreciacion(long idCoeficienteDepreciacion) {
		this.idCoeficienteDepreciacion = idCoeficienteDepreciacion;
	}
	
	public Map<EstadoCoeficiente, Double> getValor() {
		return valor;
	}
	public void setValor(Map<EstadoCoeficiente, Double> valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return ((this.getCategoria()!=null)?this.getCategoria().toString()+" - ":"")+this.getAniosExistencia()+" Años";
	}
	
	public void setEstadoBueno(Double pEstadoBueno){
		this.getValor().put(EstadoCoeficiente.BUENO,pEstadoBueno);
	}
	
	public void setEstadoMalo(Double pEstadoMalo){
		this.getValor().put(EstadoCoeficiente.MALO,pEstadoMalo);
	}
	
	public void setEstadoRegular(Double pEstadoRegular){
		this.getValor().put(EstadoCoeficiente.REGULAR,pEstadoRegular);
	}

	/**
	 * 
	 * @return valor del estado bueno del coeficiente de depreciaci�n, 0 en caso que no est� asignado
	 */
	public Double getEstadoBueno(){
		Double valor=this.getValor().get(EstadoCoeficiente.BUENO);
		if (valor==null){
			valor=0d;
		}
		return valor;
	}

	/**
	 * 
	 * @return valor del estado malo del coeficiente de depreciaci�n, 0 en caso que no est� asignado
	 */
	public Double getEstadoMalo(){
		Double valor=this.getValor().get(EstadoCoeficiente.MALO);
		if (valor==null){
			valor=0d;
		}
		return valor;
	}
	
	/**
	 * 
	 * @return valor del estado regular del coeficiente de depreciaci�n, 0 en caso que no est� asignado
	 */
	public Double getEstadoRegular(){
		Double valor=this.getValor().get(EstadoCoeficiente.REGULAR);
		if (valor==null){
			valor=0d;
		}
		return valor;
	}
}
