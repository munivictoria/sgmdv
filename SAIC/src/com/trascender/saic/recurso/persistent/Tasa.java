package com.trascender.saic.recurso.persistent;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trascender.framework.util.Periodicidad;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Plan;


@Entity
@Table(name = "TASA")
@PrimaryKeyJoinColumn(name = "ID_DOC_GENERADOR_DEUDA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Tasa extends DocGeneradorDeuda{

	public static final long serialVersionUID = -4997343916679227267L;

	//	@ManyToOne(fetch = FetchType.EAGER)
	//	@JoinColumn(name = "ID_PERIODO", nullable = true)
	@Transient
	private CuotaLiquidacion ultimoPeriodo;

	@Transient
	//	@Enumerated(EnumType.STRING)
	//	@Column(name = "PERIODICIDAD_CONFIGURADA")
	private Periodicidad periodicidadConfigurada;

	//	@ManyToOne(fetch = FetchType.EAGER)
	//	@JoinColumn(name = "ID_TIPO_TASA")
	//	private TipoTasa tipoTasa;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "tasa")
	private Set<ModificadorTasa> listaModificadoresTasa=new HashSet<ModificadorTasa>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PLAN")
	private Plan planElegido;

	private Integer anio;

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public enum Estado {
		ACTIVO, TEMPORALMENTE_INACTIVO, TEMPORALMENTE_ACTIVO, INACTIVO
	};

	//	@Enumerated(EnumType.STRING)
	//	@Column(name = "ESTADO")
	@Transient
	private Estado estado = Estado.TEMPORALMENTE_INACTIVO;

	public Plan getPlanElegido() {
		return planElegido;
	}

	public void setPlanElegido(Plan planElegido) {
		this.planElegido = planElegido;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Tasa(){
		this.setTipoDocGeneradorDeuda(TipoDocGeneradorDeuda.TASA);
	}

	public Periodicidad getPeriodicidadConfigurada() {
		return periodicidadConfigurada;
	}
	public void setPeriodicidadConfigurada(Periodicidad peridicidadConfigurada) {
		this.periodicidadConfigurada = peridicidadConfigurada;
	}

	//	public TipoTasa getTipoTasa() {
	//		return tipoTasa;
	//	}
	//	public void setTipoTasa(TipoTasa tipoTasa) {
	//		this.tipoTasa = tipoTasa;
	//	}

	public CuotaLiquidacion getUltimoPeriodo() {
		return ultimoPeriodo;
	}
	public void setUltimoPeriodo(CuotaLiquidacion ultimoPeriodo) {
		this.ultimoPeriodo = ultimoPeriodo;
	}

	public Set<ModificadorTasa> getListaModificadoresTasa() {
		return listaModificadoresTasa;
	}
	public void setListaModificadoresTasa(
			Set<ModificadorTasa> listaModificadoresTasa) {
		this.listaModificadoresTasa = listaModificadoresTasa;
	}

}
