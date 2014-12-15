
package com.trascender.framework.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.validacionDinamica.ComponenteValidacion;
import com.trascender.framework.recurso.persistent.validacionDinamica.ValidacionDinamica;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;

@Entity
@Table(name = "CONFIGURACION_RECURSO")
public class ConfiguracionRecurso implements Serializable {

	public static final long serialVersionUID = 1796634724001338259L;

	@Id
	@GeneratedValue(generator = "GEN_ID_CONFIG_RECURSO", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, name = "GEN_ID_CONFIG_RECURSO", sequenceName = "GEN_ID_CONFIG_RECURSO")
	@Column(name = "ID_CONFIGURACION_RECURSO")
	private Long idConfiguracionRecurso = -1l;

	@Column(name = "ID_RECURSO", nullable = false, unique = true)
	private Long idRecurso;

	@Column(name = "NOMBRE_ALIAS")
	private String nombreAlias;

	@Column(name = "TO_STRING")
	private String toString;

	private Integer orden;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_VALIDACION_DINAMICA")
	private ValidacionDinamica validacionDinamica;

	@Transient
	private Recurso recurso;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "configuracionRecurso")
	private List<ConjuntoAtributoTabla> listaConjuntoAtributosTabla = new ArrayList<ConjuntoAtributoTabla>();

	@ElementCollection
	@CollectionTable(name = "RELA_LISTA_ATTR_CONF", joinColumns = @JoinColumn(name = "ID_CONFIGURACION_RECURSO"))
	@Column(name = "ATRIBUTO_CONFIGURABLE")
	private Set<String> listaAtributosConfigurables = new HashSet<String>();

	/**
	 * Agrega una validacion al sistema. (el elemento que identifica una validacion por si misma es componenteValidacion). no confundir
	 * 
	 * @param pComponente
	 * @throws TrascenderFrameworkException
	 */
	public void addValidacion(ComponenteValidacion pComponente) throws TrascenderFrameworkException {
		if(this.validacionDinamica == null) {
			this.validacionDinamica = new ValidacionDinamica();
			if(this.idRecurso == null) {
				throw new TrascenderFrameworkException(81);
			}
			this.validacionDinamica.setIdRecurso(this.idRecurso);
		}

		this.validacionDinamica.addComponente(pComponente);
	}

	public String getNombreRecurso() {
		if(idRecurso == null) {
			return "";
		}
		return SecurityMgr.getInstance().getNombreRecurso(idRecurso);
	}

	public Long getIdConfiguracionRecurso() {
		return idConfiguracionRecurso;
	}

	public void setIdConfiguracionRecurso(Long pIdConfiguracionRecurso) {
		idConfiguracionRecurso = pIdConfiguracionRecurso;
	}

	public Long getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(Long pIdRecurso) {
		idRecurso = pIdRecurso;
	}

	public String getNombreAlias() {
		return nombreAlias;
	}

	public void setNombreAlias(String pNombreAlias) {
		nombreAlias = pNombreAlias;
	}

	public String getToString() {
		return toString;
	}

	public void setToString(String pToString) {
		toString = pToString;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer pOrden) {
		orden = pOrden;
	}

	public ValidacionDinamica getValidacionDinamica() {
		return validacionDinamica;
	}

	public void setValidacionDinamica(ValidacionDinamica pValidacionDinamica) {
		validacionDinamica = pValidacionDinamica;
	}

	public Set<String> getListaAtributosConfigurables() {
		return listaAtributosConfigurables;
	}

	public void setListaAtributosConfigurables(Set<String> pListaAtributosConfigurables) {
		listaAtributosConfigurables = pListaAtributosConfigurables;
	}

	public List<ConjuntoAtributoTabla> getListaConjuntoAtributosTabla() {
		return listaConjuntoAtributosTabla;
	}

	public void setListaConjuntoAtributosTabla(List<ConjuntoAtributoTabla> pListaConjuntoAtributosTabla) {
		listaConjuntoAtributosTabla = pListaConjuntoAtributosTabla;
	}

	public Recurso getRecurso() {
		return (Recurso) SecurityMgr.getInstance().getRecursoBySerial(this.idRecurso);
	}

	public void setRecurso(Recurso pRecurso) {
		if(pRecurso != null) {
			this.idRecurso = pRecurso.getIdRecurso();
		}
		recurso = pRecurso;
	}

	@Override
	public String toString() {
		return "Configuración de: " + ((this.nombreAlias != null) ? this.nombreAlias : "Entidad sin Alias asignado.");
	}

	public ConjuntoAtributoTabla getConjuntoConUsuario(Usuario locUsuario) {
		for(ConjuntoAtributoTabla cadaConjunto : this.getListaConjuntoAtributosTabla()) {
			if(cadaConjunto.getListaUsuarios().contains(locUsuario)) {
				return cadaConjunto;
			}
		}
		
		return null;
	}
	
	public ConjuntoAtributoTabla getConjuntoSinUsuario() {
		for(ConjuntoAtributoTabla cadaConjunto : this.getListaConjuntoAtributosTabla()) {
			if(cadaConjunto.getListaUsuarios().isEmpty()) {
				return cadaConjunto;
			}
		}
		
		return null;
	}
	
}