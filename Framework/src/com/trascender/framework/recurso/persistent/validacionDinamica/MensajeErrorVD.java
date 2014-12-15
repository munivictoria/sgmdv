package com.trascender.framework.recurso.persistent.validacionDinamica;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.exception.ValidacionDinamicaException;

/**
 * La idea basicamente de esta clase es que el usuario pueda lanzar un mensaje de <br>
 * error customizado o un mensaje previamente cargado en el sistema. <br><br>
 * <li>Nota: solo debe estar presente 1 valor. o el numero de mensaje. o el mensaje.<br>
 * @author jsantacruz
 */
@Entity
@Table(name="MENSAJE_ERROR_VD")
public class MensajeErrorVD implements Serializable{

	public static final long serialVersionUID = -2846291102553600678L;
	
	@Id
	@SequenceGenerator(allocationSize=1, name="GEN_ID_MSG_ERROR_VD", sequenceName="GEN_ID_MSG_ERROR_VD")
	@GeneratedValue(generator="GEN_ID_MSG_ERROR_VD", strategy=GenerationType.SEQUENCE)
	@Column(name="ID_MENSAJE_ERROR_VD")
	private Long idMensajeErrorVD = -1l;
	
	/**
	 * Si el numero esta presente. busca el mensaje segun el numero de excepcion 
	 * en la clase que se listan todos los mensajes
	 */
	@Column(name="NUMERO_MENSAJE", unique=true)
	private Long numeroMsg;
	
	/**
	 * Si el mensaje se encuentra presente, se lanzara la excepcion con ese mensaje.
	 */
	@Column
	private String mensaje;
	
	public void throwException() throws ValidacionDinamicaException{
		throw new ValidacionDinamicaException(this);
	}
	
	public Long getIdMensajeErrorVD() {
		return idMensajeErrorVD;
	}
	public void setIdMensajeErrorVD(Long pIdMensajeErrorVD) {
		idMensajeErrorVD = pIdMensajeErrorVD;
	}
	public Long getNumeroMsg() {
		return numeroMsg;
	}
	public void setNumeroMsg(Long pNumeroMsg) {
		numeroMsg = pNumeroMsg;
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String pMensaje) {
		mensaje = pMensaje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((numeroMsg == null) ? 0 : numeroMsg.hashCode());
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
		MensajeErrorVD other = (MensajeErrorVD) obj;
		if (numeroMsg == null) {
			if (other.numeroMsg != null)
				return false;
		} else if (!numeroMsg.equals(other.numeroMsg))
			return false;
		return true;
	}
	
	
	

}
