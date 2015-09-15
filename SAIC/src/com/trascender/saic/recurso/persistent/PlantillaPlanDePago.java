package com.trascender.saic.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;

@Entity
@Table(name = "PLANTILLA_PLAN_DE_PAGO")
public class PlantillaPlanDePago implements EntidadTrascender, Serializable{
	public static final long serialVersionUID = 5511484881779040110L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_plantilla_plan_pago")
	@SequenceGenerator(name = "gen_id_plantilla_plan_pago", sequenceName = "gen_id_plantilla_plan_pago", allocationSize = 1)
	@Column(name = "ID_PLANTILLA_PLAN_DE_PAGO")
	private long idPlantillaPlanDePago = -1;
	
	private String nombre;

	@Column(name = "MONTO_CONDONACION_IMPORTE")
	private Double montoCondonacionImporte;
	@Column(name = "MONTO_CONDONACION_INTERESES")
	private Double montoCondonacionIntereses;
	@Column(name = "CONDONACION_IMPORTE_PORCENTUAL")
	private Boolean condonacionImportePorcentual = true;
	@Column(name = "CONDONACION_INTERES_PORCENTUAL")
	private Boolean condonacionInteresPorcentual = true;

	@Column(name = "CANTIDAD_CUOTAS")
	private Integer cantidadCuotas;
	
	@Column(name = "INTERES_PUNITORIO")
	private Double interesPunitorio;
	@Column(name = "DIA_VENCIMIENTO")
	private Integer diaVencimiento;
	
	@Column(name = "CANTIDAD_DIAS_CESE")
	private Integer cantidadDiasCese;
	@Column(name = "CANTIDAD_CUOTAS_CESE")
	private Integer cantidadCuotasCese;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "plantilla", orphanRemoval = true)
	private List<ParametroAsociacion> listaParametrosAsociacion = new ArrayList<ParametroAsociacion>();
	
	@OrderBy("cuotasHasta")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "plantilla", orphanRemoval = true)
	private List<TasaNominalAnual> listaTasaNominalAnual = new ArrayList<TasaNominalAnual>();

	@Column(name = "CANTIDAD_PROPIEDADES_MAXIMA")
	private Integer cantidadPropiedadesMaxima;
	
	public enum TipoCalculoInteres {
		FRANCÉS, ALEMÁN, DIRECTO;
		
		@Override
		public String toString() {
			return super.toString();
		}
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_CALCULO_INTERES")
	private TipoCalculoInteres tipoCalculoInteres = TipoCalculoInteres.DIRECTO;
	
	@Column(name = "CONDONA_DEUDA_ANTIGUA")
	private Boolean condonaDeudaAntigua = Boolean.FALSE;
	
	@Column(name = "ANIOS_APLICACION")
	private String aniosAplicacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_VENCIMIENTO_PRIMER_CUOTA")
	private Date fechaVencimientoPrimerCuota;

	@OrderBy("anio")
	@OneToMany(mappedBy = "plantilla", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CondicionCondonacionPeriodo> listaCondicionCondonacionPeriodo = new ArrayList<CondicionCondonacionPeriodo>();
	
	public List<CondicionCondonacionPeriodo> getListaCondicionCondonacionPeriodo() {
		return listaCondicionCondonacionPeriodo;
	}

	public void setListaCondicionCondonacionPeriodo(
			List<CondicionCondonacionPeriodo> listaCondicionCondonacionPeriodo) {
		this.listaCondicionCondonacionPeriodo = listaCondicionCondonacionPeriodo;
	}

	public Date getFechaVencimientoPrimerCuota() {
		return fechaVencimientoPrimerCuota;
	}

	public void setFechaVencimientoPrimerCuota(Date fechaVencimientoPrimerCuota) {
		this.fechaVencimientoPrimerCuota = fechaVencimientoPrimerCuota;
	}

	public String getAniosAplicacion() {
		return aniosAplicacion;
	}

	public void setAniosAplicacion(String aniosAplicacion) {
		this.aniosAplicacion = aniosAplicacion;
	}

	public Boolean getCondonaDeudaAntigua() {
		return condonaDeudaAntigua;
	}

	public void setCondonaDeudaAntigua(Boolean condonaDeudaAntigua) {
		this.condonaDeudaAntigua = condonaDeudaAntigua;
	}

	public Integer getCantidadPropiedadesMaxima() {
		return cantidadPropiedadesMaxima;
	}

	public void setCantidadPropiedadesMaxima(Integer cantidadPropiedadesMaxima) {
		this.cantidadPropiedadesMaxima = cantidadPropiedadesMaxima;
	}

	public TipoCalculoInteres getTipoCalculoInteres() {
		return tipoCalculoInteres;
	}

	public void setTipoCalculoInteres(TipoCalculoInteres tipoCalculoInteres) {
		this.tipoCalculoInteres = tipoCalculoInteres;
	}
	
	public List<ParametroAsociacion> getListaParametrosAsociacion() {
		return listaParametrosAsociacion;
	}

	public void setListaParametrosAsociacion(
			List<ParametroAsociacion> listaParametrosAsociacion) {
		this.listaParametrosAsociacion = listaParametrosAsociacion;
	}

	public long getIdPlantillaPlanDePago() {
		return idPlantillaPlanDePago;
	}

	public void setIdPlantillaPlanDePago(long idPlantillaPlanDePago) {
		this.idPlantillaPlanDePago = idPlantillaPlanDePago;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getMontoCondonacionImporte() {
		return montoCondonacionImporte;
	}

	public void setMontoCondonacionImporte(Double montoCondonacionImporte) {
		this.montoCondonacionImporte = montoCondonacionImporte;
	}

	public Double getMontoCondonacionIntereses() {
		return montoCondonacionIntereses;
	}

	public void setMontoCondonacionIntereses(Double montoCondonacionIntereses) {
		this.montoCondonacionIntereses = montoCondonacionIntereses;
	}

	public Boolean getCondonacionImportePorcentual() {
		return condonacionImportePorcentual;
	}

	public void setCondonacionImportePorcentual(Boolean condonacionImportePorcentual) {
		this.condonacionImportePorcentual = condonacionImportePorcentual;
	}

	public Boolean getCondonacionInteresPorcentual() {
		return condonacionInteresPorcentual;
	}

	public void setCondonacionInteresPorcentual(Boolean condonacionInteresPorcentual) {
		this.condonacionInteresPorcentual = condonacionInteresPorcentual;
	}

	public Integer getCantidadCuotas() {
		return cantidadCuotas;
	}

	public void setCantidadCuotas(Integer cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}

	public List<TasaNominalAnual> getListaTasaNominalAnual() {
		return listaTasaNominalAnual;
	}

	public void setListaTasaNominalAnual(
			List<TasaNominalAnual> listaTasaNominalAnual) {
		this.listaTasaNominalAnual = listaTasaNominalAnual;
	}

	public Double getInteresPunitorio() {
		return interesPunitorio;
	}

	public void setInteresPunitorio(Double interesPunitorio) {
		this.interesPunitorio = interesPunitorio;
	}

	public Integer getDiaVencimiento() {
		return diaVencimiento;
	}

	public void setDiaVencimiento(Integer diaVencimiento) {
		this.diaVencimiento = diaVencimiento;
	}

	public Integer getCantidadDiasCese() {
		return cantidadDiasCese;
	}

	public void setCantidadDiasCese(Integer cantidadDiasCese) {
		this.cantidadDiasCese = cantidadDiasCese;
	}

	public Integer getCantidadCuotasCese() {
		return cantidadCuotasCese;
	}

	public void setCantidadCuotasCese(Integer cantidadCuotasCese) {
		this.cantidadCuotasCese = cantidadCuotasCese;
	}
	
	public Double getCondinacionInteresSegunCantidadCuota(Integer cantidadCuotas) {
		if (cantidadCuotas == null) return null;
		for (TasaNominalAnual cadaTNA : listaTasaNominalAnual) {
			if (cadaTNA.getCuotasHasta() >= cantidadCuotas)
				return cadaTNA.getInteresCondonado();
		}
		return null;
	}
	
	public Double getInteresTNASegunCantidadCuota(Integer cantidadCuotas) {
		if (cantidadCuotas == null) return null;
		for (TasaNominalAnual cadaTNA : listaTasaNominalAnual) {
			if (cadaTNA.getCuotasHasta() >= cantidadCuotas)
				return cadaTNA.getInteres();
		}
		return null;
	}
	
	@Override
	public long getIdEntidad() {
		return this.idPlantillaPlanDePago;
	}

	@Override
	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public String getNombrePropiedadId() {
		return "idPlantillaPlanDePago";
	}

	@Override
	public boolean isAuditable() {
		return true;
	}

	@Transient
	private String comentarioAuditoria;
	@Transient
	private long llaveUsuarioAuditoria;
	@Override
	public void setComentarioAuditoria(String pComentario) {
		this.comentarioAuditoria = pComentario;
	}

	@Override
	public void setLlaveUsuarioAuditoria(long pLlave) {
		this.llaveUsuarioAuditoria = pLlave;
	}

	@Override
	public long getLlaveUsuarioAuditoria() {
		return llaveUsuarioAuditoria;
	}

	@Override
	public String getComentarioAuditoria() {
		return comentarioAuditoria;
	}
	
	@OrderBy(value = "fecha")
	@Where(clause = "id_recurso = " + serialVersionUID)
	@OneToMany(mappedBy = "idEntidad", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<LogAuditoria> listaLogsAuditoria = new ArrayList<LogAuditoria>();

	public List<LogAuditoria> getListaLogsAuditoria() {
		return listaLogsAuditoria;
	}

	public void setListaLogsAuditoria(List<LogAuditoria> pListaLogsAuditoria) {
		this.listaLogsAuditoria = pListaLogsAuditoria;
	}
	
	public boolean esCuotaLiquidacionExcluida(CuotaLiquidacion cuota) {
		if (getAniosAplicacion() == null)
			return false;
		String[] anios = getAniosAplicacion().split(",");
		boolean esta = false;
		for (String cadaAnio : anios) {
			if (Integer.valueOf(cadaAnio).equals(cuota.getAnio())) {
				esta = true;
				break;
			}
		}
		return !esta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idPlantillaPlanDePago ^ (idPlantillaPlanDePago >>> 32));
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
		PlantillaPlanDePago other = (PlantillaPlanDePago) obj;
		if (idPlantillaPlanDePago != other.idPlantillaPlanDePago)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getNombre();
	}
	
	

}
