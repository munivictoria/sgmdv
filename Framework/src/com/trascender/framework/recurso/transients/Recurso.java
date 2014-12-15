
package com.trascender.framework.recurso.transients;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import ar.trascender.util.ReflectionUtils;

import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Permiso.Accion;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.util.Metaclase;

/**
 * Representa un recurso del sistema
 * 
 * @author Mariano Lusardi, Ariel Trevisan
 *
 */
public class Recurso implements Serializable {

	public static final long serialVersionUID = -8952879447868357224L;

	private long idRecurso;
	private String nombre;
	private Class clase;
	private Class<? extends Metaclase> metaClase;
	private List<String> listaPropiedadesClase;

	private boolean insert = false;
	private boolean update = false;
	private boolean delete = false;
	private boolean select = false;

	private Set<AtributoConsultable> atributosConsultables = new LinkedHashSet<AtributoConsultable>();

	public Recurso() {
		super();
	}

	/**
	 * Crea un recurso a partir de un permiso
	 * 
	 * @param pPermiso
	 *            permiso que se le aplica al recurso
	 */
	public Recurso(Permiso pPermiso) {
		this();
		this.delete = pPermiso.isDelete();
		this.update = pPermiso.isUpdate();
		this.insert = pPermiso.isInsert();
		this.select = pPermiso.isSelect();
		this.setIdRecurso(pPermiso.getIdRecurso());

	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean pDelete) {
		delete = pDelete;
	}

	public boolean isInsert() {
		return insert;
	}

	public void setInsert(boolean pInsert) {
		insert = pInsert;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean pSelect) {
		select = pSelect;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean pUpdate) {
		update = pUpdate;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Class getClase() {
		return clase;
	}

	public void setClase(Class pClase) {
		clase = pClase;
		this.armarPropiedadesClase();
	}

	private void armarPropiedadesClase() {
		this.listaPropiedadesClase = new ArrayList<String>();
		List<String> listaOpcionesParaOmitir = Arrays.asList("SerialVersionUID", "Auditable", "ListaLogsAuditoria", "LlaveUsuarioAuditoria", "ComentarioAuditoria", "IdEntidad",
				"NombrePropiedadId");

		for(Method cadaMethod : ReflectionUtils.getPropiedades(clase)) {
			String nombre = cadaMethod.getName();

			// Quita el principio de la cadena según sea "is" o "get".
			nombre = nombre.substring(nombre.startsWith("is") ? 2 : 3);
			if(!listaOpcionesParaOmitir.contains(nombre)) {
				listaPropiedadesClase.add(nombre);
			}
		}
	}

	public List<String> getListaPropiedadesClase() {
		return listaPropiedadesClase;
	}

	public Class<? extends Metaclase> getMetaClase() {
		return metaClase;
	}

	public void setMetaClase(Class<? extends Metaclase> pMetaClase) {
		metaClase = pMetaClase;
	}

	/**
	 * Valida si una acci�n est� permitida o no
	 * 
	 * @param pAccion
	 *            acci�n que se desea validar
	 * @return verdadero si tiene permiso, falso en caso contrario
	 */
	public boolean validar(Accion pAccion) {
		boolean retorno = false;
		System.out.println("Quiere: " + pAccion.toString());
		switch(pAccion) {
			case INSERT:
				retorno = this.isInsert();
				break;
			case DELETE:
				retorno = this.isDelete();
				break;
			case SELECT:
				retorno = this.isSelect();
				break;
			case UPDATE:
				retorno = this.isUpdate();
				break;
		}
		return retorno;
	}

	/**
	 * 
	 * @return id del recurso al que hace referencia
	 */
	public long getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(long pIdRecurso) {
		idRecurso = pIdRecurso;
	}

	public void addAtributoConsultable(AtributoConsultable pAtributo) {
		this.atributosConsultables.add(pAtributo);
	}

	public void setAtributosConsultables(Object... pAtributosConsultables) {
		this.atributosConsultables.clear();

		int i = 0;
		while(i < pAtributosConsultables.length) {
			String nombreExterno = (String) pAtributosConsultables[i];
			String nombreInterno = (String) pAtributosConsultables[++i];

			AtributoConsultable locAtributoConsultable;
			locAtributoConsultable = new AtributoConsultable(nombreInterno, nombreExterno, Tipo.TEXTO);

			if((i + 1) >= pAtributosConsultables.length) {
				this.atributosConsultables.add(locAtributoConsultable);
				break;
			}

			if(!(pAtributosConsultables[i + 1] instanceof String)) {
				Tipo tipo = (Tipo) pAtributosConsultables[++i];
				locAtributoConsultable = new AtributoConsultable(nombreInterno, nombreExterno, tipo);
			}

			this.atributosConsultables.add(locAtributoConsultable);
			++i;
		}
	}

	public Set<AtributoConsultable> getAtributosConsultables() {
		return atributosConsultables;
	}

	public void setAtributosConsultables(Set<AtributoConsultable> pAtributosConsultables) {
		atributosConsultables = pAtributosConsultables;
	}

}