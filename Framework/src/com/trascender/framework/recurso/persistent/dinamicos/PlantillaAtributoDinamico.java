package com.trascender.framework.recurso.persistent.dinamicos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.SecurityMgr;

@Entity
@Table(name = "PLANTILLA_ATRIBUTO_DINAMICO")
public class PlantillaAtributoDinamico implements Serializable, EntidadTrascender{
	
	public static final long serialVersionUID = 9107761871498560832L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_plan_atri_dinamico")
	@SequenceGenerator(name = "gen_id_plan_atri_dinamico", sequenceName = "gen_id_plan_atri_dinamico",allocationSize = 1)
	@Column(name="ID_PLANTILLA")
	private long idPlantillaAtributoDinamico = -1;
	
	private String nombre;
	
	@Column(name = "ID_RECURSO")
	private Long idRecurso;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@OneToMany(mappedBy = "plantillaAtributoDinamico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<OpcionAtributoDinamicoListado> listaOpciones = new ArrayList<OpcionAtributoDinamicoListado>();
	
	@OrderBy(value="fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();
	
	private boolean requerido = false; 
	
	private boolean busqueda = true;
	
	public enum Tipo{ENTERO, DECIMAL, CADENA, FECHA, BOOLEANO, LISTADO, ARCHIVO}
	
	public boolean isRequerido() {
		return requerido;
	}

	public void setRequerido(boolean pRequerido) {
		requerido = pRequerido;
	}

	public boolean isBusqueda() {
		return busqueda;
	}

	public void setBusqueda(boolean pBusqueda) {
		busqueda = pBusqueda;
	}

	public void addOpcionAtributoDinamico(OpcionAtributoDinamicoListado pOpcion){
		pOpcion.setPlantillaAtributoDinamico(this);
		listaOpciones.add(pOpcion);
	}
	
	public List<OpcionAtributoDinamicoListado> getListaOpciones() {
		return listaOpciones;
	}

	public void setListaOpciones(List<OpcionAtributoDinamicoListado> pListaOpciones) {
		listaOpciones = pListaOpciones;
	}

	public long getIdPlantillaAtributoDinamico() {
		return idPlantillaAtributoDinamico;
	}

	public void setIdPlantillaAtributoDinamico(long pIdPlantillaAtributoDinamico) {
		idPlantillaAtributoDinamico = pIdPlantillaAtributoDinamico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

	public Long getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(Long pIdRecurso) {
		idRecurso = pIdRecurso;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo pTipo) {
		tipo = pTipo;
	}
	
	public String getNombreRecurso(){
		return SecurityMgr.getInstance().getNombreRecurso(idRecurso);
	}
	
	public AtributoDinamico<?> generarAtributoDinamico(){
		AtributoDinamico<?> locAtributo = null;
		switch (this.tipo) {
		case ENTERO:
			locAtributo = new AtributoDinamicoEntero();
			break;
		case DECIMAL:
			locAtributo = new AtributoDinamicoDecimal();
			break;
		case CADENA:
			locAtributo = new AtributoDinamicoCadena();
			break;
		case FECHA:
			locAtributo = new AtributoDinamicoFecha();
			break;
		case BOOLEANO:
			locAtributo = new AtributoDinamicoBooleano();
			break;
		case LISTADO:
			locAtributo = new AtributoDinamicoListado();
			break;
		case ARCHIVO:
			locAtributo = new AtributoDinamicoArchivo();
			break;
		default:
			break;
		}
		locAtributo.setIdRecurso(this.idRecurso);
		locAtributo.setPlantilla(this);
		return locAtributo;
	}
	
	public OpcionAtributoDinamicoListado getOpcionPorValor(String pValor){
		OpcionAtributoDinamicoListado locOpcion = null;
		for (OpcionAtributoDinamicoListado cadaOpcion : listaOpciones){
			if (cadaOpcion.getValor().equals(pValor)){
				locOpcion = cadaOpcion;
				break;
			}
		}
		return locOpcion;
	}
	
	@Override
	public String toString(){
		return nombre + ", " + tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idPlantillaAtributoDinamico ^ (idPlantillaAtributoDinamico >>> 32));
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
		PlantillaAtributoDinamico other = (PlantillaAtributoDinamico) obj;
		//Si los id's son -1, que compare en memoria.
		if (this.idPlantillaAtributoDinamico == -1 && other.idPlantillaAtributoDinamico == -1)
			return this == other;
		if (idPlantillaAtributoDinamico != other.idPlantillaAtributoDinamico)
			return false;
		return true;
	}

	//*********************************************************************************************************************************************************************************/
	// AUDITORIA

	@Transient
	private long llaveUsuarioAuditoria;
	@Transient
	private String comentarioAuditoria;

	public List<LogAuditoria> getListaLogsAuditoria() {
		return listaLogsAuditoria;
	}

	public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
		this.listaLogsAuditoria = pListaLogsAuditoria;
	}

	public long getLlaveUsuarioAuditoria() {
		return llaveUsuarioAuditoria;
	}

	public void setLlaveUsuarioAuditoria(long llaveUsuarioAuditoria) {
		this.llaveUsuarioAuditoria = llaveUsuarioAuditoria;
	}

	public String getComentarioAuditoria() {
		return comentarioAuditoria;
	}

	public void setComentarioAuditoria(String comentarioAuditoria) {
		this.comentarioAuditoria = comentarioAuditoria;
	}

	public long getIdEntidad() {
		return this.idPlantillaAtributoDinamico;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNombrePropiedadId() {
		return "idPlantillaAtributoDinamico";
	}

	public boolean isAuditable() {
		return true;
	}
	
}
