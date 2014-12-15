package com.trascender.framework.recurso.persistent.validacionDinamica;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="VALIDACION_DINAMICA")
public class ValidacionDinamica implements Serializable{

	public static final long serialVersionUID = -1779645144014759825L;
	
	@Id
	@GeneratedValue(generator="GEN_ID_VAL_DINAMICA", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize=1,name="GEN_ID_VAL_DINAMICA", sequenceName="GEN_ID_VAL_DINAMICA")
	@Column(name="ID_VALIDACION_DINAMICA")
	private Long idValidacionDinamica = -1l;
	
	@Column(name="ID_RECURSO", nullable=false)
	private Long idRecurso;
	
	
	@Transient
	private Class<?> clase;
	
	@OneToMany(orphanRemoval=true, cascade=CascadeType.ALL)
	@JoinTable(name="RELA_VAL_DIN_COMP_VAL", joinColumns=@JoinColumn(name="ID_VALIDACION_DINAMICA"), 
					inverseJoinColumns=@JoinColumn(name="ID_COMPONENTE_VALIDACION"))
	private Set<ComponenteValidacion> listaComponentes = new HashSet<ComponenteValidacion>();

	public void addComponente(ComponenteValidacion pComponente){
		pComponente.setValidacionDinamica(this);
		this.listaComponentes.add(pComponente);
	}
	
	public Long getIdValidacionDinamica() {
		return idValidacionDinamica;
	}

	public void setIdValidacionDinamica(Long pIdValidacionDinamica) {
		idValidacionDinamica = pIdValidacionDinamica;
	}

	public Class<?> getClase() {
		return clase;
	}

	public void setClase(Class<?> pClase) {
		clase = pClase;
	}

	public Set<ComponenteValidacion> getListaComponentes() {
		return listaComponentes;
	}

	public void setListaComponentes(Set<ComponenteValidacion> pListaComponentes) {
		listaComponentes = pListaComponentes;
	}

	public Long getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(Long pIdRecurso) {
		idRecurso = pIdRecurso;
	}
	
}
