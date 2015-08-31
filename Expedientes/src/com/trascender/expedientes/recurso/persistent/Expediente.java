/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trascender.expedientes.enums.Estado;
import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;

@Entity
@Table(name = "EXP_EXPEDIENTE")
public class Expediente extends NodoExpediente implements Serializable {

	public static final long serialVersionUID = -40903768640019390L;

	@Column(name = "ASUNTO", nullable = false)
	private String asunto;

	@Column(name = "FECHA_REGISTRO")
	private Date fechaRegistro;

	@Column(name = "NROREGISTRO", nullable = false)
	private Long nroRegistro;

	@ManyToOne(cascade = {javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST})
	@JoinColumn(name = "ID_INTERESADO")
	private Persona interesado;

	@Enumerated(EnumType.STRING)
	private Estado estado = Estado.ABIERTO;

	@OneToOne
	@JoinColumn(name = "ID_FASEACTUAL")
	private Fase faseActual;

	@Transient
	private Fase faseActualSegunPermisos;

	public enum AccionFase {
		AVANCE, RETROCESO, CANCELACION, AVANCE_ESPECIAL
	}

	@Transient
	public String getStringInteresado() {
		return this.interesado != null ? this.interesado.getDenominacion() : "";
	}

	@Transient
	public String getStringProcedimiento() {
		return this.nodoProcedimiento != null ? ((Procedimiento) this.nodoProcedimiento).getNombre() : "";
	}

	public List<Documento> listarDocumentacionPresentada() {
		List<Documento> locListaResultado = new ArrayList<Documento>();
		for(NodoExpediente cadaNodoF : listaNodosExpedientes) {
			for(NodoExpediente cadaNodoT : cadaNodoF.getListaNodosExpedientes()) {
				Tramite cadaTramite = (Tramite) cadaNodoT;
				for(Documento cadaDocumento : cadaTramite.getListaDocumentoEntrada()) {
					if(cadaDocumento.isPresentado()) {
						locListaResultado.add(cadaDocumento);
					}
				}
			}
		}

		return locListaResultado;
	}

	public Fase getFaseActualSegunPermisos() {
		return faseActualSegunPermisos != null ? faseActualSegunPermisos : faseActual;
	}

	public void setFaseActualSegunPermisos(Fase faseActualSegunPermisos) {
		this.faseActualSegunPermisos = faseActualSegunPermisos;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Long getNroRegistro() {
		return nroRegistro;
	}

	public void setNroRegistro(Long nroRegistro) {
		this.nroRegistro = nroRegistro;
	}

	public Persona getInteresado() {
		return interesado;
	}

	public void setInteresado(Persona interesado) {
		this.interesado = interesado;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Transient
	public void setProcedimiento(Procedimiento procedimiento, Usuario pUsuario) {
		this.listaNodosExpedientes.clear();
		this.getListaHitos().clear();
		for(FaseProcedimiento fp : procedimiento.getListaFasesProcedimiento()) {
			this.listaNodosExpedientes.add(new Fase(fp, this));
		}
		if(this.idNodoExpediente <= 0) {
			String pDescripcion = " Creación";
			addHito(pDescripcion, null, pUsuario);
		}

		this.nodoProcedimiento = procedimiento;
		this.plazo = new Plazo(this.nodoProcedimiento);
		Fase locFase = (Fase) listaNodosExpedientes.get(0);
		this.setFaseActual(locFase);
		locFase.setActiva(true);
		locFase.crearPlazo();
		this.setFaseActivaSegunPermisos(pUsuario);
	}

	public Fase getFaseActual() {
		return faseActual;
	}

	public void setFaseActual(Fase faseActual) {
		this.faseActual = faseActual;
	}

	@Transient
	public Fase avanzarFase(AccionFase pAccion, Usuario pUsuario, String pComentario) throws Exception {
		int index = 0;
		Fase locNuevaActiva = null;
		listaNodosExpedientes = getListaFasesOrdenada();
		index = getIndexActiva();
		if(index < listaNodosExpedientes.size() - 1) {
			Fase locActiva = (Fase) listaNodosExpedientes.get(index);
			locNuevaActiva = (Fase) listaNodosExpedientes.get(++index);
			cambiarFaseActiva(locNuevaActiva, locActiva, pAccion, pUsuario, pComentario);
		} else {
			throw new Exception("No se puede Avanzar Fase");
		}

		return locNuevaActiva;
	}

	@Transient
	public Fase retrocederFase(AccionFase pAccion, Usuario pUsuario, String pComentario) throws Exception {
		int index = 0;
		Fase locNuevaActiva = null;
		listaNodosExpedientes = getListaFasesOrdenada();
		index = getIndexActiva();
		if(index > 0) {
			Fase locActiva = (Fase) listaNodosExpedientes.get(index);
			locNuevaActiva = (Fase) listaNodosExpedientes.get(--index);
			cambiarFaseActiva(locNuevaActiva, locActiva, pAccion, pUsuario, pComentario);
		} else {
			throw new Exception("No se puede Retroceder Fase");
		}

		return locNuevaActiva;
	}

	public int getIndexActiva() {
		int index = 0;
		for(int i = 0; i < listaNodosExpedientes.size(); i++) {
			index = i;
			if(((Fase) listaNodosExpedientes.get(i)).getActiva())
				break;
		}

		return index;
	}

	private void cambiarFaseActiva(Fase locNuevaActiva, Fase locActiva, AccionFase pAccion, Usuario pUsuario, String pComentario) {
		locActiva.setActiva(false);
		locActiva.setEstado(com.trascender.expedientes.recurso.persistent.Fase.Estado.CERRADO);
		if(locNuevaActiva != null) {
			locNuevaActiva.setActiva(true);
			locNuevaActiva.setEstado(com.trascender.expedientes.recurso.persistent.Fase.Estado.ABIERTO);
			this.setFaseActual(locNuevaActiva);
		}

		// Generar el Hito.
		String descripcion;
		switch(pAccion) {
			case AVANCE: {
				descripcion = "Fase actual avanzó a " + locNuevaActiva.getPlantilla().toString();

				// Solo se crea un nuevo plazo si la fase a avanzar no lo tiene.
				if(locNuevaActiva.getPlazo() == null) {
					locNuevaActiva.crearPlazo();
				}
				break;
			}
			case RETROCESO: {
				descripcion = "Fase actual retrocedió a " + locNuevaActiva.getPlantilla().toString();
				break;
			}
			case CANCELACION: {
				descripcion = "Se canceló el avance a " + locActiva.getPlantilla().toString();

				// Se cancela el plazo de la fase actual.
				locActiva.anularPlazo();
				break;
			}
			case AVANCE_ESPECIAL: {
				descripcion = "Fase actual avanzó a " + locNuevaActiva.getPlantilla().toString();
				locNuevaActiva.crearPlazo();
				break;
			}
			default: {
				descripcion = "Fase actual cambió a " + locNuevaActiva.getPlantilla().toString();
				break;
			}
		}
		this.addHito(descripcion, pComentario, pUsuario);
		setFaseActivaSegunPermisos(pUsuario);
	}

	@Transient
	public List<NodoExpediente> getListaFasesOrdenada() {
		Collections.sort(listaNodosExpedientes, comparatorOrden);

		return listaNodosExpedientes;
	}

	public List<NodoExpediente> getListaFasesOrdenadasSegunPermisos() {
		Collections.sort(listaNodosExpedientes, comparatorOrden);
		// Debo quitar de la lista de Fases las fases especiales si no puedo verlas.
		List<NodoExpediente> locListaResultado = new ArrayList<NodoExpediente>();
		for(NodoExpediente cadaNodo : listaNodosExpedientes) {
			Fase cadaFase = (Fase) cadaNodo;
			if(cadaFase.getFaseAVolver() != null && !cadaFase.equals(this.faseActualSegunPermisos)) {
				continue;
			}
			locListaResultado.add(cadaFase);
		}
		if(listaNodosExpedientes.get(0).getNodoPadre().getIdNodoExpediente() == -1){
			for (int i=0; i < locListaResultado.size(); i++) {
				   if(locListaResultado.get(i).getNodoProcedimiento().getEstado().equals(EstadoPlantilla.BAJA)){
					   locListaResultado.remove(locListaResultado.get(i));
				   }
		        }	
		}
		return locListaResultado;
	}

	public boolean isEnPrimerFase() {
		// Si el expediente aun no tiene fases se entiende que es nueva y esta en su primer fase.
		if(this.getListaFasesOrdenada().isEmpty())
			return true;
		Fase primerFase = (Fase) this.getListaFasesOrdenada().get(0);

		return primerFase.equals(this.faseActual);
	}

	private static Comparator<NodoExpediente> comparatorOrden = new Comparator<NodoExpediente>() {

		@Override
		public int compare(NodoExpediente nodo1, NodoExpediente nodo2) {
			return nodo1.getOrden().compareTo(nodo2.getOrden());
		}
	};

	public String getStringFaseActiva() {
		String retorno = "";
		if(faseActual != null) {
			retorno = faseActual.getPlantilla().toString();
		}

		return retorno;
	}

	/**
	 * Setea el atributo faseActualSegunPermisos segun corresponda al usuario ver o no la Fase actual del Expediente.
	 */
	public void setFaseActivaSegunPermisos(Usuario pUsuario) {
		if(faseActual != null) {
			if(faseActual.getFaseAVolver() != null) {
				// Es una Fase Especial, solo ciertas personas pueden ver que está en esta fase.
				if(nodoProcedimiento.getResponsable().esResponsable(pUsuario)
						|| nodoProcedimiento.getResponsable().esSupervisor(pUsuario)
						|| (faseActual.getNodoProcedimiento().getResponsable() != null && (faseActual.getNodoProcedimiento().getResponsable().esResponsable(pUsuario) || faseActual
								.getNodoProcedimiento().getResponsable().esSupervisor(pUsuario)))
						|| (faseActual.getFaseAVolver().getNodoProcedimiento().getResponsable() != null && (faseActual.getFaseAVolver().getNodoProcedimiento().getResponsable()
								.esResponsable(pUsuario) || faseActual.getFaseAVolver().getNodoProcedimiento().getResponsable().esSupervisor(pUsuario)))) {
					faseActualSegunPermisos = faseActual;
				} else {
					// Va a ver como Fase actual la Fase a la que vuelve el expediente.
					faseActualSegunPermisos = faseActual.getFaseAVolver();
				}
			} else {
				faseActualSegunPermisos = faseActual;
			}
		}
	}

	public String getStringFaseActivaSegunPermisos() {
		String retorno = "";

		// Si seteo una Fase actual segun permisos, esta es la que debe ver el Usuario
		if(faseActualSegunPermisos != null) {
			retorno = faseActualSegunPermisos.getPlantilla().toString();
		} else if(faseActual != null) {
			retorno = faseActual.getPlantilla().toString();
		}

		return retorno;
	}

	@Override
	public String toString() {
		String retorno = " Expediente:  " + this.nroRegistro + " : " + ((Procedimiento) this.nodoProcedimiento).getNombre();

		return retorno;
	}

	@Override
	public Object getPlantilla() {
		return getNodoProcedimiento();
	}

	@Override
	public boolean tieneVencimientos(List<DiaFeriado> diasFeriados) {
		if(this.isVencido(diasFeriados)) {
			return true;
		} else if(faseActual != null && faseActual.tieneVencimientos(diasFeriados)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isVencido(List<DiaFeriado> diasFeriados) {
		if(Estado.VENCIDO.equals(estado)) {
			return true;
		}
		if(Estado.CERRADO.equals(estado) || Estado.FINALIZADO.equals(estado)) {
			return false;
		}

		return super.isVencido(diasFeriados);
	}

	public Documento getDocumentoPorNombre(String pNombre) {
		for(NodoExpediente cadaNodoFase : listaNodosExpedientes) {
			for(NodoExpediente cadaNodoTramite : cadaNodoFase.getListaNodosExpedientes()) {
				Tramite cadaTramite = (Tramite) cadaNodoTramite;
				for(Documento cadaDocumento : cadaTramite.getListaDocumentoSalida()) {
					if(cadaDocumento.getNombre().equals(pNombre)) {
						return cadaDocumento;
					}
				}
			}
		}

		return null;
	}

}