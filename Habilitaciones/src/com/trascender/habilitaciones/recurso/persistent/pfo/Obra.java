package com.trascender.habilitaciones.recurso.persistent.pfo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.framework.recurso.persistent.DigestoMunicipal;

@Entity
@Table(name = "OBRA")
public class Obra implements Serializable {

	public enum TipoObra{GENERICA, REPAVIMENTACION, CORDON_CUNETA, CLOACAS, PAVIMENTO, AHORRO_PREVIO;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	};
	/**
	 * 
	 */
	public static final long serialVersionUID = 3822776169137693751L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_obra")
	@SequenceGenerator(name = "gen_id_obra", sequenceName = "gen_id_obra", allocationSize = 1)
	@Column(name = "ID_OBRA")
	private long idObra=-1;
	
	@Column(name = "TOTAL_METROS_AFECTADOS", nullable = false)
	private Double metrosTotalAfectados=0d;
	
	@Column(name = "VALOR_METRO", nullable = false)
	private Double valorPorMetro=0d;
	
	@Column(name = "COSTO_TOTAL_OBRA", nullable = false)
	private Double costoTotalObra=0d;
	private String descripcion;
	
	@Column(name = "NUMERO_OBRA", nullable = false)
	private String numeroObra;
	
	@ManyToMany
	@JoinTable(name = "RELA_CUADRAS_AFECTADAS_OBRA", joinColumns=@JoinColumn(name = "ID_OBRA"), inverseJoinColumns=@JoinColumn(name = "ID_CUADRA"))
	private Set<Cuadra> listaCuadras=new HashSet<Cuadra>();
	
	@ManyToMany
	@JoinTable(name = "RELA_PLAN_CTA_OBRA_POR_OBRA", joinColumns=@JoinColumn(name = "ID_OBRA"), inverseJoinColumns = @JoinColumn(name = "ID_PLAN_CUENTA_OBRA"))
	private Set<PlanCuentaObra> listaPlanesCuentaPorObra=new HashSet<PlanCuentaObra>();
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_OBRA", nullable = false)
	private TipoObra tipoObra;
	
	@ManyToOne()
	@JoinColumn(name = "ID_DIGESTO_MUNICIPAL")
	private DigestoMunicipal digestoMunicipal;
	
	@Column(name = "OBRA_VIA_ADMINISTRACION", nullable = false)
	private boolean obraViaAdministracion=false;

	public TipoObra getTipoObra() {
		return tipoObra;
	}
	public void setTipoObra(TipoObra tipoObra) {
		this.tipoObra = tipoObra;
	}
	public Double getCostoTotalObra() {
		return costoTotalObra;
	}
	public void setCostoTotalObra(Double costoTotalObra) {
		this.costoTotalObra = costoTotalObra;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public long getIdObra() {
		return idObra;
	}
	public void setIdObra(long idObra) {
		this.idObra = idObra;
	}
	
	public Double getMetrosTotalAfectados() {
		return metrosTotalAfectados;
	}
	public void setMetrosTotalAfectados(Double metrosTotalAfectados) {
		this.metrosTotalAfectados = metrosTotalAfectados;
	}
	
	public Double getValorPorMetro() {
		return valorPorMetro;
	}
	public void setValorPorMetro(Double valorPorMetro) {
		this.valorPorMetro = valorPorMetro;
	}
	
	public Set<Cuadra> getListaCuadras() {
		return listaCuadras;
	}
	public void setListaCuadras(Set<Cuadra> listaCuadras) {
		this.listaCuadras = listaCuadras;
	}
	
	public Set<PlanCuentaObra> getListaPlanesCuentaPorObra() {
		return listaPlanesCuentaPorObra;
	}
	public void setListaPlanesCuentaPorObra(
			Set<PlanCuentaObra> listaPlanesCuentaPorObra) {
		this.listaPlanesCuentaPorObra = listaPlanesCuentaPorObra;
	}
	
	@Override
	public String toString() {
		if (this==null) return "";
		return (this.getDescripcion()!=null)?this.getDescripcion():"";
	}
	@Override
	public int hashCode() {
		if (this.getIdObra()==-1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idObra ^ (idObra >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Obra other = (Obra) obj;
		if (idObra != other.idObra)
			return false;
		return true;
	}
	
	public String getNumeroObra() {
		return numeroObra;
	}
	public void setNumeroObra(String numeroObra) {
		this.numeroObra = numeroObra;
	}
	
	//TODO: en el Tags a agregar de DigestoMunicipal dice que hay que agregar en las relaciones many-to-one "not-found="ignore""
	public DigestoMunicipal getDigestoMunicipal() {
		return digestoMunicipal;
	}
	public void setDigestoMunicipal(DigestoMunicipal digestoMunicipal) {
		this.digestoMunicipal = digestoMunicipal;
	}
	
	public boolean isObraViaAdministracion() {
		return obraViaAdministracion;
	}
	public void setObraViaAdministracion(boolean pObraViaAdministracion) {
		this.obraViaAdministracion = pObraViaAdministracion;
	}
	
	
}
