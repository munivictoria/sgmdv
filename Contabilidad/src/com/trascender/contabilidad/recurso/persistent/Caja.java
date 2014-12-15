
package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "CAJA")
public class Caja implements Serializable {
	
	public static final long serialVersionUID = 3337679096804383619L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_caja")
	@SequenceGenerator(name = "gen_id_caja", sequenceName = "gen_id_caja",allocationSize = 1)
	@Column(name="ID_CAJA")
	private long idCaja=-1;
	private String nombre;
	private String numero;
	
	@Column(name = "IP_ADDRESS")
	private String ipAddress;
	
	@Column(name = "PUERTO_IMPRESION")
	private String puertoImpresion;
	public enum Estado{ACTIVO, INACTIVO; 
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	};
	
	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.ACTIVO;
	//Relacion con otros objetos
	
	@OneToMany(mappedBy = "caja", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<TicketCaja> tickets;
	
	public Set<TicketCaja> getTickets() {
		return tickets;
	}

	public void setTickets(Set<TicketCaja> tickets) {
		this.tickets = tickets;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String pNombre){
		this.nombre = pNombre;
	}
	
	public String getNumero(){
		return numero;
	}
	
	public void setNumero(String pNumero){
		this.numero = pNumero;
	}

	public long getIdCaja(){
		return idCaja;
	}
	
	public void setIdCaja(long pIdCaja){
		this.idCaja = pIdCaja;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getPuertoImpresion() {
		return puertoImpresion;
	}

	public void setPuertoImpresion(String puertoImpresion) {
		this.puertoImpresion = puertoImpresion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idCaja == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idCaja ^ (idCaja >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Caja other = (Caja) obj;
		if (idCaja != other.idCaja) {
			return false;
		}
		return true;
	}
	@Override
	public String toString(){
		return (this.getNombre() == null)?"":this.getNombre();
	}
}
