/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.recurso.persistent.Usuario;

@Entity
@Table(name = "EXP_TRAMITE")
public class Tramite extends NodoExpediente implements Serializable, Cloneable {

	public static final long serialVersionUID = -8010304888770896737L;

	@Column(name = "COMENTARIOS")
	private String comentarios;

	@ManyToOne
	@JoinColumn(name = "ID_ESTADOTRAMITE")
	private EstadoTramite estadoTramite;

	@OneToMany(mappedBy = "tramite", cascade = CascadeType.ALL)
	private List<Documento> listaDocumentos = new ArrayList<Documento>();

	public Tramite() {
	}

	public Tramite(TramiteProcedimiento pTProcedimiento, Fase pFase) {
		this.setNodoProcedimiento(pTProcedimiento);
		this.setNodoPadre(pFase);
		this.setOrden(pTProcedimiento.getOrden());

		for(DocumentoProcedimiento dp : pTProcedimiento.getListaDocumentosProcedimiento()) {
			if(!dp.getEstado().equals(EstadoPlantilla.BAJA)){
				this.listaDocumentos.add(new Documento(dp, this));
			}
		}
	}

	public EstadoTramite getEstadoTramite() {
		return estadoTramite;
	}

	public void setEstadoTramite(EstadoTramite estadoTramite) {
		this.estadoTramite = estadoTramite;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public List<Documento> getListaDocumentos() {
		return listaDocumentos;
	}

	public void setListaDocumentos(List<Documento> listaDocumentos) {
		this.listaDocumentos = listaDocumentos;
	}

	public List<Documento> getListaDocumentoEntrada() {
		List<Documento> lista = new ArrayList<Documento>();
		for(Documento doc : listaDocumentos) {
			DocumentoCatalogo docCatalogo = doc.getDocumentoProcedimiento().getDocumentoCatalogo();
			if(docCatalogo.getTipo().equals(DocumentoCatalogo.Tipo.ENTRADA)) {
				lista.add(doc);
			}
		}

		return lista;
	}

	public void setListaDocumentoEntrada(List<Documento> listaDocumentosEntrada) {
		for(Documento doc : this.listaDocumentos) {
			if(!listaDocumentosEntrada.contains(doc)) {
				listaDocumentosEntrada.add(doc);
			}
		}

		this.listaDocumentos = listaDocumentosEntrada;
	}

	public List<Documento> getListaDocumentoSalida() {
		List<Documento> lista = new ArrayList<Documento>();
		for(Documento doc : listaDocumentos) {
			DocumentoCatalogo docCatalogo = doc.getDocumentoProcedimiento().getDocumentoCatalogo();
			if(docCatalogo.getTipo().equals(DocumentoCatalogo.Tipo.SALIDA)) {
				lista.add(doc);
			}
		}

		return lista;
	}

	public void cambioEstado(Usuario pUsuario, EstadoTramite pEstado) {
		boolean generarHito = false;
		if((this.estadoTramite == null && pEstado != null) || (this.estadoTramite != null && (pEstado == null || !this.estadoTramite.equals(pEstado)))) {
			generarHito = true;
		}
		this.setEstadoTramite(pEstado);

		if(generarHito) {
			if(this.estadoTramite != null && !this.estadoTramite.isCierraTramite()) {
				setFechaFin(null);
			} else {
				setFechaFin(new Date());
			}

			String pDescripcion = getTramiteCatalogo().getNombre();
			if(this.getEstadoTramite() == null) {
				pDescripcion = pDescripcion + " volvió al Estado vacio.";
			} else {
				pDescripcion = pDescripcion + " cambió a estado " + this.estadoTramite.getNombre();
			}
			addHito(pDescripcion, null, pUsuario);
		}
	}

	public void documentacionPresentada(Usuario pUsuario, List<Boolean> pListaActual, List<Documento> pListaModificada) {
		boolean generarHito1 = false;
		boolean generarHito2 = false;
		String documentos1 = "";
		String documentos2 = "";
		for(int i = 0; i < pListaActual.size(); i++) {
			Documento doc = pListaModificada.get(i);
			if(!pListaActual.get(i) && doc.isPresentado()) {
				generarHito1 = true;
				documentos1 = documentos1 + doc.getNombre() + ", ";
			}
			if(pListaActual.get(i) && !doc.isPresentado()) {
				generarHito2 = true;
				documentos2 = documentos2 + doc.getNombre() + ", ";
			}
		}
		this.setListaDocumentoEntrada(pListaModificada);

		if(generarHito1) {
			if(documentos1.length() > 0) {
				int ind = documentos1.lastIndexOf(",");
				documentos1 = documentos1.substring(0, ind);
			}

			String pDescripcion = "Marcó presentado: " + documentos1;
			addHito(pDescripcion, null, pUsuario);
		}
		if(generarHito2) {
			if(documentos2.length() > 0) {
				int ind = documentos2.lastIndexOf(",");
				documentos2 = documentos2.substring(0, ind);
			}

			String pDescripcion = "Desmarcó presentado: " + documentos2;
			addHito(pDescripcion, null, pUsuario);
		}
	}

	@Override
	public String toString() {
		String retorno = " Tramite: " + getPlantilla();

		return retorno;
	}

	@Override
	public Object getPlantilla() {
		return ((TramiteProcedimiento) getNodoProcedimiento()).getTramiteCatalogo();
	}

	private TramiteCatalogo getTramiteCatalogo() {
		return ((TramiteProcedimiento) getNodoProcedimiento()).getTramiteCatalogo();
	}

	@Override
	public boolean tieneVencimientos(List<DiaFeriado> diasFeriados) {
		try {
			// expediente vencido
			if(this.nodoPadre.nodoPadre.isVencido(diasFeriados)) {
				return true;
			}

			// fase vencida
			if(this.nodoPadre.isVencido(diasFeriados)) {
				return true;
			}

			// este tramite vencido
			return isVencido(diasFeriados);
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean isVencido(List<DiaFeriado> diasFeriados) {
		if(estadoTramite != null && estadoTramite.isCierraTramite()) {
			return false;
		}

		return super.isVencido(diasFeriados);
	}

	public boolean isCerrado() {
		return this.getEstadoTramite() != null && this.getEstadoTramite().isCierraTramite();
	}

	@Override
	public Tramite clone() {
		try {
			return (Tramite) super.clone();
		} catch(CloneNotSupportedException e) {
			return null;
		}
	}

}