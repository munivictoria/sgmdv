/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import javax.persistence.Transient;

import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.framework.recurso.persistent.Area;

@Entity
@Table(name = "CUENTA")
public class Cuenta implements Serializable, Cloneable{

	/**
	 * 
	 */
	public static final long serialVersionUID = -2241356923595105275L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_cuenta")
	@SequenceGenerator(name = "gen_id_cuenta", sequenceName = "gen_id_cuenta",allocationSize = 1)
	@Column(name="ID_CUENTA")
	private long idCuenta = -1;
	
	@Column(name = "CODIGO_IMPUTACION")
	private String codigoImputacion;
	private String nombre;
	private String abreviatura;
	//Relacion con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA_PADRE")
	private Cuenta cuentaPadre;
	
	@ManyToOne
	@JoinColumn(name = "ID_PLAN_DE_CUENTA")
	private PlanDeCuenta planDeCuenta;
	
	@OneToMany
    @JoinTable(name="RELA_CUENTA_TIPO_CUENTA", joinColumns=@JoinColumn(name="ID_CUENTA", nullable = false),
    								inverseJoinColumns=@JoinColumn(name="ID_TIPO_CUENTA", nullable = false))
	private List<TipoCuenta> listaTipoCuenta = new ArrayList<TipoCuenta>();
	
	@OneToMany(mappedBy = "cuentaPadre", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Cuenta> cuentasHijos = new HashSet<Cuenta>();
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "RELA_CUENTA_TIPO_BALANCE", joinColumns=@JoinColumn(name = "ID_CUENTA"), inverseJoinColumns=@JoinColumn(name = "ID_TIPO_BALANCE"))
	private Set<TipoBalance> listaTiposBalance = new HashSet<TipoBalance>();
	
	@OneToMany(mappedBy = "cuenta")
	private Set<LineaPresupuesto> lineasPresupuesto = new HashSet<LineaPresupuesto>();
	
	@OneToMany(mappedBy = "cuenta")
	private Set<CuentaHistoricaBalance> listaCuentasHistorico = new HashSet<CuentaHistoricaBalance>();
	
//	@OneToMany(mappedBy = "cuenta", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@Transient
	private Set<LineaMayor> listaLineaMayor = new HashSet<LineaMayor>();
	
	@OneToMany(mappedBy = "cuenta", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<LineaAsientoContable> lineasAsientoContable = new HashSet<LineaAsientoContable>();
	
	@OneToMany(mappedBy = "cuenta", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<LineaSubdiarioCaja> lineasSubdiarioCaja = new HashSet<LineaSubdiarioCaja>();
	
	@OneToMany(mappedBy = "cuenta", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<MovimientoCaja> listaMovimientoCaja = new HashSet<MovimientoCaja>();
	
	@OneToMany(mappedBy = "cuenta", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<LineaFactura> lineasFactura = new HashSet<LineaFactura>();
	
	@Transient
	private Set<LineaOrdenCompra> listaLineasOrdenCompra = new HashSet<LineaOrdenCompra>();
	
	@OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AsociacionCuenta> listaAsociacionCuenta = new HashSet<AsociacionCuenta>();

	@ManyToOne
	@JoinColumn(name = "ID_AREA")
	private Area area;
	
	/**
	 * Es el codigo generado a partir de concatenar el codigoImputacion con los codigos
	 * de las cuentas padres. Solamente se arma si esta asi especificado en Municipalidad.
	 */
	@Column(name = "CODIGO_IMPUTACION_COMPLETO")
	private String codigoImputacionCompleto;
	
	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getCodigoImputacion() {
		return codigoImputacion;
	}

	public void setCodigoImputacion(String codigoImputacion) {
		this.codigoImputacion = codigoImputacion;
	}

	public long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Set<LineaPresupuesto> getLineasPresupuesto() {
		return lineasPresupuesto;
	}

	public void setLineasPresupuesto(Set<LineaPresupuesto> lineasPresupuesto) {
		this.lineasPresupuesto = lineasPresupuesto;
	}

	public Cuenta getCuentaPadre() {
		return cuentaPadre;
	}

	public void setCuentaPadre(Cuenta cuentaPadre) {
		this.cuentaPadre = cuentaPadre;
	}

	public Set<CuentaHistoricaBalance> getListaCuentasHistorico() {
		return listaCuentasHistorico;
	}

	public void setListaCuentasHistorico(
			Set<CuentaHistoricaBalance> listaCuentasHistorico) {
		this.listaCuentasHistorico = listaCuentasHistorico;
	}

	public PlanDeCuenta getPlanDeCuenta() {
		return planDeCuenta;
	}

	public void setPlanDeCuenta(PlanDeCuenta planDeCuenta) {
		this.planDeCuenta = planDeCuenta;
	}

	public Set<LineaAsientoContable> getLineasAsientoContable() {
		return lineasAsientoContable;
	}

	public void setLineasAsientoContable(
			Set<LineaAsientoContable> lineasAsientoContable) {
		this.lineasAsientoContable = lineasAsientoContable;
	}
	
	public Set<LineaSubdiarioCaja> getLineasSubdiarioCaja() {
		return lineasSubdiarioCaja;
	}

	public void setLineasSubdiarioCaja(Set<LineaSubdiarioCaja> lineasSubdiarioCaja) {
		this.lineasSubdiarioCaja = lineasSubdiarioCaja;
	}
	
	public List<TipoCuenta> getListaTipoCuenta() {
		return listaTipoCuenta;
	}

	public void setListaTipoCuenta(List<TipoCuenta> listaTipoCuenta) {
		this.listaTipoCuenta = listaTipoCuenta;
	}

	public Set<TipoBalance> getListaTiposBalance() {
		return listaTiposBalance;
	}

	public void setListaTiposBalance(Set<TipoBalance> listaTiposBalance) {
		this.listaTiposBalance = listaTiposBalance;
	}
	
	public Set<Cuenta> getCuentasHijos() {
		return cuentasHijos;
	}

	public void setCuentasHijos(Set<Cuenta> cuentasHijos) {
		this.cuentasHijos = cuentasHijos;
	}
	
	public Set<LineaMayor> getListaLineaMayor() {
		return listaLineaMayor;
	}

	public void setListaLineaMayor(Set<LineaMayor> listaLineaMayor) {
		this.listaLineaMayor = listaLineaMayor;
	}

	public String getCodigoImputacionCompleto() {
		return this.codigoImputacionCompleto;
	}
	
	public void setCodigoImputacionCompleto(String codigoImputacionCompleto) {
		this.codigoImputacionCompleto = codigoImputacionCompleto;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * Método toString, devuelve el código de imputación, el nombre y, entre paréntesis, la abreviatura.
	 */
	@Override
	public String toString(){
		String locAbreviatura = "";
		if (this.abreviatura != null){
			locAbreviatura = " ("+this.abreviatura+")";
		} 
		String locCodigoImputacion = this.getCodigoImputacionCompleto()!=null?this.getCodigoImputacionCompleto():"";
		String cadena = locCodigoImputacion+" "+this.nombre+locAbreviatura;
		return cadena; 
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idCuenta == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idCuenta ^ (idCuenta >>> 32));
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
		final Cuenta other = (Cuenta) obj;
		if (idCuenta != other.idCuenta) {
			return false;
		}
		return true;
	}
	
	public Set<MovimientoCaja> getListaMovimientoCaja() {
		return listaMovimientoCaja;
	}
	public void setListaMovimientoCaja(Set<MovimientoCaja> listaMovimientoCaja) {
		this.listaMovimientoCaja = listaMovimientoCaja;
	}
	public Set<AsociacionCuenta> getListaAsociacionCuenta() {
		return listaAsociacionCuenta;
	}
	public void setListaAsociacionCuenta(Set<AsociacionCuenta> listaAsociacionCuenta) {
		this.listaAsociacionCuenta = listaAsociacionCuenta;
	}
	
	
	/**
	 * Obtiene recursivamente el código de imputación
	 * @return
	 */
	public String getStringCodigoImputacion(){
		if (this.getCuentaPadre() == null){
			return this.getCodigoImputacion();
		}
		else{
			return this.getCuentaPadre().getCodigoImputacionCompleto()+"."+this.codigoImputacion;
		}
	}
	
	public void armarCodigoImputacionCompleto(boolean concatenando){
		this.setCodigoImputacionCompleto(concatenando ? this.getStringCodigoImputacion() : this.getCodigoImputacion());
		for (Cuenta cadaCuentaHija : this.cuentasHijos) {
			cadaCuentaHija.armarCodigoImputacionCompleto(concatenando);
		}
	}
	
	public StringBuilder getStringTiposCuenta(){
		StringBuilder locCadenaRetorno = new StringBuilder();
		
		for (Iterator<TipoCuenta> iterator = 
				this.getListaTipoCuenta().iterator(); iterator.hasNext();) {
			TipoCuenta cadaCuenta = iterator.next();
			locCadenaRetorno.append(cadaCuenta.getNombre());
			if (iterator.hasNext()) {
				locCadenaRetorno.append(". ");
			}
		}
		return locCadenaRetorno;
	}
	
	public void setStringTiposCuenta(StringBuilder pSb){}
	
	@Override
	public Cuenta clone() throws CloneNotSupportedException{
		return (Cuenta) super.clone();
	}
	public Set<LineaFactura> getLineasFactura() {
		return lineasFactura;
	}
	public void setLineasFactura(Set<LineaFactura> lineasFactura) {
		this.lineasFactura = lineasFactura;
	}
	public Set<LineaOrdenCompra> getListaLineasOrdenCompra() {
		return listaLineasOrdenCompra;
	}
	public void setListaLineasOrdenCompra(
			Set<LineaOrdenCompra> listaLineasOrdenCompra) {
		this.listaLineasOrdenCompra = listaLineasOrdenCompra;
	}
}
