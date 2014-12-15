package com.trascender.framework.recurso.persistent.dinamicos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.Util;
import com.trascender.framework.util.anotations.NoAuditable;

@Entity
@Table(name = "ATRIBUTO_DINAMICO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO")
public abstract class AtributoDinamico<T> implements Serializable, Comparable<AtributoDinamico<?>>, AuditoriaIndirecta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_id_atributo_dinamico")
	@SequenceGenerator(name = "gen_id_atributo_dinamico", sequenceName = "gen_id_atributo_dinamico", allocationSize = 1)
	@Column(name = "ID_ATRIBUTO_DINAMICO")
	protected long idAtributoDinamico = -1;

	@Column(name = "ID_RECURSO")
	protected long idRecurso;

	@NoAuditable
	@Column(name = "ID_ENTIDAD")
	protected long idEntidad;

	@ManyToOne
	@JoinColumn(name = "ID_PLANTILLA")
	protected PlantillaAtributoDinamico plantilla;

	public long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(long pIdEntidad) {
		idEntidad = pIdEntidad;
	}

	public long getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(long pIdRecurso) {
		idRecurso = pIdRecurso;
	}

	public long getIdAtributoDinamico() {
		return idAtributoDinamico;
	}

	public void setIdAtributoDinamico(long pIdAtributoDinamico) {
		idAtributoDinamico = pIdAtributoDinamico;
	}

	public PlantillaAtributoDinamico getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(PlantillaAtributoDinamico pPlantilla) {
		plantilla = pPlantilla;
	}

	/**
	 * El nombre dado por la plantilla
	 */
	public String getNombre() {
		return plantilla.getNombre();
	}

	public abstract T getValor();

	public abstract void setValor(T valor);

	public abstract void setValorString(String valor) throws Exception;

	public abstract String getValorString();

	public abstract String getNombreAtributoValor();

	public int compareTo(AtributoDinamico<?> pO) {
		String nombre1 = Util.reemplazarAcentos(this.getPlantilla().getNombre());
		String nombre2 = Util.reemplazarAcentos(pO.getPlantilla().getNombre());
		return nombre1.compareToIgnoreCase(nombre2);
	}

	public String getComentario() {
		return null;
	}

	public long getSerialVersionUID() {
		return this.getIdRecurso();
	}

	@Override
	public String toString() {
		return getNombre() + ": " + getValor();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idAtributoDinamico ^ (idAtributoDinamico >>> 32));
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
		AtributoDinamico other = (AtributoDinamico) obj;
		// if (this.idAtributoDinamico == -1 || other.idAtributoDinamico == -1){
		// return this == other;
		// }
		if (this.getIdAtributoDinamico() == -1) {
			return (super.equals(obj) && (System.identityHashCode(this) == System.identityHashCode(obj)));
		}
		if (idAtributoDinamico != other.idAtributoDinamico)
			return false;
		return true;
	}

	public static void addRestriccionesCriterio(Criterio pCriterio, long pSerialVersion, String pNombreAtributoId, List<AtributoDinamico<?>> pListaAtributos) {
		addRestriccionesCriterio(pCriterio, pSerialVersion, pNombreAtributoId, "e", pListaAtributos);
	}

	public static void addRestriccionesCriterio(Criterio pCriterio, long pSerialVersion, String pNombreAtributoId, String aliasEntidad, List<AtributoDinamico<?>> pLista) {
		List<AtributoDinamico<?>> locListaValida = esListaValida(pLista);
		if (locListaValida != null && !locListaValida.isEmpty()) {
			for (int i = 0; i < locListaValida.size(); i++) {
				AtributoDinamico<?> cadaAtributo = locListaValida.get(i);
				String locAlias = "cadaAtrib" + i;
				pCriterio.addEntidadCartesiana("AtributoDinamico", locAlias);
				pCriterio.add(Restriccion.JPQL(locAlias + ".idEntidad = " + aliasEntidad + "." + pNombreAtributoId)).add(Restriccion.IGUAL(locAlias + ".idRecurso", pSerialVersion))
				.add(getRestriccionDeAtributo(cadaAtributo, locAlias)).add(Restriccion.IGUAL(locAlias + ".plantilla", cadaAtributo.getPlantilla()));
			}
		}
	}

	private static Restriccion getRestriccionDeAtributo(AtributoDinamico<?> pAtributo, String pAlias) {
		String locAlias = pAlias + "." + pAtributo.getNombreAtributoValor();
		if (pAtributo instanceof AtributoDinamicoCadena) {
			return Restriccion.ILIKE(locAlias, pAtributo.getValorString());
		}
		return Restriccion.IGUAL(locAlias, pAtributo.getValor());
	}

	private static List<AtributoDinamico<?>> esListaValida(List<AtributoDinamico<?>> pListaAtributoDinamicos) {
		List<AtributoDinamico<?>> locListaAtributos = null;
		if (pListaAtributoDinamicos != null) {
			locListaAtributos = new ArrayList<AtributoDinamico<?>>();
			for (Iterator<AtributoDinamico<?>> cadaIterator = pListaAtributoDinamicos.iterator(); cadaIterator.hasNext();) {
				AtributoDinamico<?> cadaAtributo = cadaIterator.next();
				if (cadaAtributo.getValor() != null && !cadaAtributo.getValorString().trim().isEmpty()) {
					locListaAtributos.add(cadaAtributo);
				}
			}
		}
		return locListaAtributos;
	}

	public EntidadTrascender getEntidadTrascender() {
		return new EntidadTrascender() {

			public void setLlaveUsuarioAuditoria(long pLlave) {
			}

			public void setComentarioAuditoria(String pComentario) {
			}

			public boolean isAuditable() {
				return false;
			}

			public long getSerialVersionUID() {
				return idRecurso;
			}

			public String getNombrePropiedadId() {
				return null;
			}

			public long getLlaveUsuarioAuditoria() {
				return 0;
			}

			public long getIdEntidad() {
				return idEntidad;
			}

			public String getComentarioAuditoria() {
				return null;
			}
		};
	}

	public String getNombrePropiedad() {
		return this.getNombre();
	}

	public boolean concatenaNombre() {
		return false;
	}
}
