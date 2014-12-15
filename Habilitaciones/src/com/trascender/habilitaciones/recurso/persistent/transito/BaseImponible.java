package com.trascender.habilitaciones.recurso.persistent.transito;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
/**
 * @author jsantacruz
 */

@Entity
@Table(name="BASE_IMPONIBLE")
public class BaseImponible implements Serializable{

	public static final long serialVersionUID = -6452931466427942168L;
	
	@Id
	@SequenceGenerator(allocationSize=1, sequenceName="gen_id_base_imp", name="gen_id_base_imp")
	@GeneratedValue(generator="gen_id_base_imp", strategy=GenerationType.SEQUENCE)
	@Column(name="ID_BASE_IMPONIBLE")
	private long idBaseImponible = -1;
	
	private Double valor = 0d;
	
	private boolean exento;
	
	@Column(name="FECHA_BAJA")
	private Date fechaBaja;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_MODELO", nullable=false)
	private Modelo modelo;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_TABLA_BASE_IMPONIBLE", nullable=false)
	private TablaBaseImponible tablaBaseImponible; 
	
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private List<AtributoDinamico<?>> listaAtributosDinamicos = new ArrayList<AtributoDinamico<?>>();
	
	public void addAtributoDinamico(AtributoDinamico<?> pAtributoDinamico){
		pAtributoDinamico.setIdEntidad(this.idBaseImponible);
		this.listaAtributosDinamicos.add(pAtributoDinamico);
	}
	
	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}
	
	public void setListaAtributosDinamicos(List<AtributoDinamico<?>> pListaAtributosDinamicos) {
		this.listaAtributosDinamicos.clear();
		for (AtributoDinamico<?> cadaAtributo : pListaAtributosDinamicos){
			if (cadaAtributo.getValor() != null){
				this.addAtributoDinamico(cadaAtributo);
			}
		}
	}
	
	
	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Long getIdBaseImponible() {
		return idBaseImponible;
	}

	public void setIdBaseImponible(Long idBaseImponible) {
		this.idBaseImponible = idBaseImponible;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public boolean isExento() {
		return exento;
	}

	public void setExento(boolean exento) {
		this.exento = exento;
	}

	public TablaBaseImponible getTablaBaseImponible() {
		return tablaBaseImponible;
	}

	public void setTablaBaseImponible(TablaBaseImponible tablaBaseImponible) {
		this.tablaBaseImponible = tablaBaseImponible;
	}
	
	@PostPersist
	public void postPersist(){
		for (AtributoDinamico<?> cadaAtributo : getListaAtributosDinamicos()){
			cadaAtributo.setIdEntidad(idBaseImponible);
		}
	}

	@Override
	public String toString() {
		return "Base Imponible [" + modelo.getMarca() + ", " + modelo.getNombre() + ", " + modelo.getTipoVehiculo() + "]"
				+"[ $"+ valor + ", Exento: " + ((exento)?"Sí":"No")
				+ "] [Tabla B.I: " + tablaBaseImponible.getNombre() + "]";
	}
	
	
}
