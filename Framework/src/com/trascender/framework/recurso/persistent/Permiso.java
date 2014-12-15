package com.trascender.framework.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;

/**
 * Representa un recurso permitido de un rol
 * @hibernate.class table = "PERMISO"
 */

@Entity
@Table(name="PERMISO")
public class Permiso implements Serializable, Comparable<Permiso>{

	public static final long serialVersionUID = 3391833851844798515L;
	
	@Id
	@GeneratedValue(generator="gen_id_permiso",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="gen_id_permiso", sequenceName="gen_id_permiso",allocationSize = 1)
	@Column(name= "ID_PERMISO")
	private long idPermiso=-1;
	
	@Column(name="ID_RECURSO", nullable=false)
	private long idRecurso;
	
	@Column(name="INS")
	private boolean insert;
	@Column(name="UPD")
	private boolean update;
	@Column(name="DEL")
	private boolean delete;
	@Column(name="SEL")
	private boolean select;
	@Column(name="AUD")
	private boolean audith;
	
	@ManyToOne
	@JoinColumn(name="ID_ROL", nullable=false)
	private Rol rol;
	
	public Permiso(){
		super();
	}
	
	public Permiso(Recurso pRecurso){
		this.setIdRecurso(pRecurso.getIdRecurso());
		this.setInsert(pRecurso.isInsert());
		this.setUpdate(pRecurso.isUpdate());
		this.setDelete(pRecurso.isDelete());
		this.setSelect(pRecurso.isSelect());
	}

	/**
	 * Acciones posibles {INSERT,UPDATE,DELETE,SELECT,AUDITH,LOGIN,LOGOUT,SIGN}
	 * @author jsantacruz
	 */
	public enum Accion{INSERT,UPDATE,DELETE,SELECT,AUDITH,LOGIN,LOGOUT,SIGN;
		@Override
		public String toString() {
			return com.trascender.framework.util.Util.capitalizeEnumName(super.toString());
		}
	};
	
	
	/**
	 * @return devuelve el rol que tiene este permiso 
	 */
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol pRol) {
		rol = pRol;
	}
	/**
	 * @return si corresponde o no ser auditado
	 * @hibernate.property column="AUD"
	 */
	public boolean isAudith() {
		return audith;
	}
	public void setAudith(boolean pAudith) {
		audith = pAudith;
	}
	
	/**
	 * 
	 * @return si puede o no borrar
	 * @hibernate.property column = "DEL"
	 */
	public boolean isDelete() {
		return delete;
	}
	public void setDelete(boolean pDelete) {
		delete = pDelete;
	}
	
	/**
	 * 
	 * @return número de identificaci�n �nico del recurso al que hace referenca este recurso permitido
	 * @hibernate.property column = "ID_RECURSO" not-null = "true"
	 * 
	 */
	public long getIdRecurso() {
		return idRecurso;
	}
	public void setIdRecurso(long pIdRecurso) {
		idRecurso = pIdRecurso;
	}
	
	/**
	 * @return n�mero de identificaci�n �nico del permiso
	 * @hibernate.id column = "ID_PERMISO" generator-class = "increment"
	 * unsaved-value = "-1"
	 */
	public long getIdPermiso() {
		return idPermiso;
	}
	public void setIdPermiso(long pIdPermiso) {
		idPermiso = pIdPermiso;
	}
	
	/**
	 * 
	 * @return si puede o no insertar
	 * @hibernate.property column = "INS"
	 */
	public boolean isInsert() {
		return insert;
	}
	public void setInsert(boolean pInsert) {
		insert = pInsert;
	}
	
	
	/**
	 * 
	 * @return si puede o no ejecutar consultas
	 * @hibernate.property column = "SEL"
	 */
	public boolean isSelect() {
		return select;
	}
	public void setSelect(boolean pSelect) {
		select = pSelect;
	}
	
	
	/**
	 * 
	 * @return si puede o no hacer actualizaciones sobre el recurso 
	 * @hibernate.property column = "UPD"
	 */
	public boolean isUpdate() {
		return update;
	}
	public void setUpdate(boolean pUpdate) {
		update = pUpdate;
	}

	
	/**
	 * Valida si una acci�n está permitida o no
	 * @param pAccion acci�n que se desea validar
	 * @return verdadero si tiene permiso, falso en caso contrario
	 */
	public boolean validar(Accion pAccion,Usuario pUsuario){
		boolean retorno=false;
		System.out.println("El Usuario: "+pUsuario.getUser()+" Quiere: "+pAccion.toString() +" [" + this.getNombreRecurso()+"]");
		switch(pAccion){
			case INSERT: retorno=this.isInsert(); break;
			case DELETE: retorno=this.isDelete(); break;
			case SELECT: retorno=this.isSelect(); break;
			case UPDATE: retorno=this.isUpdate(); break;
			case AUDITH: retorno=this.isAudith(); break;
		}
		return retorno;
	}
	
	/**
	 * Permite mezclar los permisos de dos recursos permitidos
	 * @param pRecursoPermitido recurso con el cual se desea mezclar 
	 * @return RecursoPermitido, con los permisos iguales a la suma l�gica de los
	 * ambos permisos
	 * @throws TrascenderFrameworkException 850 cuando hacen referencia a recursos distintos
	 */
	public Permiso merge(Permiso pRecursoPermitido) throws TrascenderFrameworkException{
		Permiso nuevo=new Permiso();
		if (this.getIdRecurso()!=pRecursoPermitido.getIdRecurso()){
			throw new TrascenderFrameworkException(840);
		}
		nuevo.setIdRecurso(this.idRecurso);
		nuevo.setAudith(this.isAudith()||pRecursoPermitido.isAudith());
		nuevo.setDelete(this.isDelete()||pRecursoPermitido.isDelete());
		nuevo.setInsert(this.isInsert()||pRecursoPermitido.isInsert());
		nuevo.setSelect(this.isSelect()||pRecursoPermitido.isSelect());
		nuevo.setUpdate(this.isUpdate()||pRecursoPermitido.isUpdate());
		//Acá se perdía el rol
		nuevo.setRol(pRecursoPermitido.getRol());
		return nuevo;
	}
	
	
	@Override
	public boolean equals(Object pObj) {
		if (this == pObj) return true;
		if (!(pObj instanceof Permiso)) return false;
		Permiso per=(Permiso)pObj;
		if (this.getIdRecurso()==per.getIdRecurso())
			return true;
		else 
			return false;
	}
	
	public int hashCode() {
		return Long.valueOf(this.getIdRecurso()).intValue()+Long.valueOf(Permiso.serialVersionUID).intValue();
	}
	
	
	public String toString() {	
		return SecurityMgr.getInstance().getNombreRecurso(this.idRecurso);
	}
	
	public String getNombreRecurso(){
		return this.toString();
	}
	
	public Boolean getInsert(){
		return this.insert;
	}
	
	public Boolean getSelect(){
		return this.select;
	}
	
	public Boolean getAudith(){
		return this.audith;
	}
	
	public Boolean getDelete(){
		return this.delete;
	}
	
	public Boolean getUpdate(){
		return this.update;
	}
	
	
	public void setInsert(Boolean pInsert){
		if (pInsert!=null)
			this.insert=pInsert;
	}
	
	public void setSelect(Boolean pSelect){
		if (pSelect!=null)
			this.select=pSelect;
	}
	
	public void setAudith(Boolean pAudith){
		if (pAudith!=null){
			this.audith=pAudith;
		}
	}
	
	public void setDelete(Boolean pDelete){
		if (pDelete!=null){	
			this.delete=pDelete;
		}
	}
	
	public void setUpdate(Boolean pUpdate){
		if (pUpdate!=null){
			this.update=pUpdate;		
		}
	}

	public int compareTo(Permiso pO) {
		String nombreRecurso1 = Util.reemplazarAcentos(this.getNombreRecurso());
		String nombreRecurso2 = Util.reemplazarAcentos(pO.getNombreRecurso());
		return nombreRecurso1.compareToIgnoreCase(nombreRecurso2); 
	}

	
	
}
