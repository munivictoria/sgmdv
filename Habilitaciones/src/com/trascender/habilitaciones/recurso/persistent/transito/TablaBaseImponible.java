package com.trascender.habilitaciones.recurso.persistent.transito;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jboss.beans.metadata.api.annotations.Inject;

import com.trascender.framework.util.Util;
import com.trascender.habilitaciones.business.interfaces.BusinessTransitoLocal;
/**
 * @author jsantacruz
 */

@Entity
@Table(name="TABLA_BASE_IMPONIBLE")
public class TablaBaseImponible implements Serializable{

	public static final long serialVersionUID = -1724116517438828258L;

	@Transient
	@Inject(bean=BusinessTransitoLocal.JNDI_NAME)
	private BusinessTransitoLocal businessTransito;
	
	@Id
	@SequenceGenerator(allocationSize=1, name="gen_id_tab_base_imp", sequenceName="gen_id_tab_base_imp")
	@GeneratedValue(generator="gen_id_tab_base_imp", strategy=GenerationType.SEQUENCE)
	@Column(name="ID_TABLA_BASE_IMPONIBLE")
	private long idTablaBaseImponible = -1;
	
	@Column(nullable=false)
	private String nombre;
	
	private boolean activo;
	
	/**
	 * Devuelve la Base imponible segun el vehiculo
	 * @param pVehiculo
	 * @return
	 * @throws Exception 
	 */
	public Double getValor(Vehiculo pVehiculo) throws Exception{
//		try{
//			businessTransito = (BusinessTransitoLocal) new InitialContext().lookup(BusinessTransitoLocal.JNDI_NAME);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
		
		if(businessTransito == null){
			throw new Exception("No se pudo inicializar el EJB.");
		}
		return businessTransito.getValorBaseImponible(this, pVehiculo);
	}
	
	public long getIdTablaBaseImponible() {
		return idTablaBaseImponible;
	}

	public void setIdTablaBaseImponible(long idTablaBaseImponible) {
		this.idTablaBaseImponible = idTablaBaseImponible;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	@Transient
//	public Set<BaseImponible> getListaBaseImponible() throws Exception {
//		return this.getListaBaseImponible(null, null, null);
//	}
//	
//	@Transient
//	public Set<BaseImponible> getListaBaseImponible(Marca pMarca, Modelo pModelo, TipoVehiculo pTipoVehiculo) throws Exception{
//		BusinessTransitoLocal businessTransito = null;
//		try{
//			businessTransito = (BusinessTransitoLocal) new InitialContext().lookup("/ejb/BusinessTransitoLocal/local");
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		if(businessTransito == null){
//			throw new Exception("No se pudo inicializar el EJB.");
//		}
//		
//		return businessTransito.findListaBaseImponible(pMarca, pModelo, pTipoVehiculo, this);
//	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		TablaBaseImponible other = (TablaBaseImponible) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Util.capitalize(this.nombre);
	}
}
