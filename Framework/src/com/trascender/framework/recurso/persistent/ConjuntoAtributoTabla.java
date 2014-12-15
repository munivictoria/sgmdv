
package com.trascender.framework.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.exception.TrascenderFrameworkException;

@Entity
@Table(name = "CONJUNTO_ATTR_TABLA")
public class ConjuntoAtributoTabla implements Serializable {

	public static final long serialVersionUID = 3466123567869264433L;

	@Id
	@GeneratedValue(generator = "GEN_ID_CONJ_AT_TAB", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, name = "GEN_ID_CONJ_AT_TAB", sequenceName = "GEN_ID_CONJ_AT_TAB")
	@Column(name = "ID_CONJ_ATTR_TABLA")
	private Long idConjuntoAtributoTabla = -1l;
	
	@ManyToOne
	@JoinColumn(name = "ID_CONFIGURACION_RECURSO")
	private ConfiguracionRecurso configuracionRecurso;

	@ManyToMany
	@JoinTable(name = "RELA_CONJ_ATTR_TABLA_USUARIO", joinColumns = @JoinColumn(name = "ID_CONJ_ATTR_TABLA"), inverseJoinColumns = @JoinColumn(name = "ID_USUARIO"))
	private List<Usuario> listaUsuarios = new ArrayList<Usuario>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "conjuntoAtributoTabla")
	private List<ConfiguracionAtributoTabla> listaAtributosTabla = new ArrayList<ConfiguracionAtributoTabla>();

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> pListaUsuarios) {
		listaUsuarios = pListaUsuarios;
	}

	public List<ConfiguracionAtributoTabla> getListaAtributosTabla() {
		return listaAtributosTabla;
	}

	public void setListaAtributosTabla(List<ConfiguracionAtributoTabla> pListaAtributosTabla) {
		listaAtributosTabla = pListaAtributosTabla;
	}
	
	public Long getIdConjuntoAtributoTabla() {
		return idConjuntoAtributoTabla;
	}

	public void setIdConjuntoAtributoTabla(Long pIdConjuntoAtributoTabla) {
		idConjuntoAtributoTabla = pIdConjuntoAtributoTabla;
	}

	public ConfiguracionRecurso getConfiguracionRecurso() {
		return configuracionRecurso;
	}

	public void setConfiguracionRecurso(ConfiguracionRecurso pConfiguracionRecurso) {
		configuracionRecurso = pConfiguracionRecurso;
	}

	/**
	 * Agrega una configuracion de un atributo para la tabla
	 * 
	 * @param pConfigAttr
	 * @return
	 * @throws Exception
	 */
	public boolean addConfiguracionAtributo(ConfiguracionAtributoTabla pConfigAttr) throws Exception {
		if(pConfigAttr != null) {
			for(ConfiguracionAtributoTabla cadaAtributoConfigurado : this.listaAtributosTabla) {
				if(pConfigAttr.getOrden() == null || cadaAtributoConfigurado.getOrden().equals(pConfigAttr.getOrden())) {
					throw new TrascenderFrameworkException(87);
				}
			}
		}

		return false;
	}

	public String getStringUsuarios() {
		String salida = "";
		for(Usuario cadaUsuario : this.listaUsuarios) {
			salida = salida + cadaUsuario.getUser() + ", ";
		}
		if(salida.length() > 0) {
			int ind = salida.lastIndexOf(",");
			salida = salida.substring(0, ind);
		}

		return salida;
	}

	public void setStringUsuarios(String usuarios) {
	}
	
	public String getStringColumnas() {
		String salida = "";
		for(ConfiguracionAtributoTabla cadaColumna : this.listaAtributosTabla) {
			salida = salida + cadaColumna.getNombreAtributo() + ", ";
		}
		if(salida.length() > 0) {
			int ind = salida.lastIndexOf(",");
			salida = salida.substring(0, ind);
		}

		return salida;
	}

	public void setStringColumnas(String usuarios) {
	}

}