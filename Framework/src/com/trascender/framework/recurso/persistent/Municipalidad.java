package com.trascender.framework.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import com.trascender.framework.util.EntidadTrascender;
import com.trascender.framework.util.LogAuditoria;
import com.trascender.framework.util.Util;

@Entity
@Table(name="MUNICIPALIDAD")
public class Municipalidad implements Serializable, EntidadTrascender{
	
	public static final long ID_MUNICIPALIDAD_CRESPO=1l;
	static public final long serialVersionUID = -5309742858603992512L;
	
	@Id
	@GeneratedValue(generator="gen_id_municipalidad",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="gen_id_municipalidad", sequenceName="gen_id_municipalidad",allocationSize = 1)
	@Column(name="ID_MUNICIPALIDAD")
	private long idMunicipalidad=-1;
	
	@Column(nullable=false)
	private String nombre;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_DOMICILIO", nullable=false)
	private Domicilio domicilio;
	
	private String telefono;
	
	@Column(name = "NRO_CLIENTE_PAGO_FACIL")
	private Integer numeroClientePagoFacil;
	
	@Column(name = "ENCABEZADO_REPORTES")
	private String encabezadoReportes;
	
	@Column(name = "SUBENCABEZADO_REPORTES")
	private String subencabezadoReportes;
	
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] logo;
	
	@Column(name = "NOMBRE_LOGO")
	private String nombreLogo;
	
	@Column(name = "VARIOS_SERVICIOS_OSP")
	private boolean variosServiciosOSP = false;
	
	@Column(name = "NUM_SOL_SUMINISTRO_POR_AREA")
	private boolean numSolSuministroPorArea = false;
	
	@Column(name = "NOMBRE_CUENTA_CONCATENADO")
	private boolean nombreCuentaConcatenado = true;
	
	@Column(name="MONTO_FACTURA_VARIA_OC")
	private boolean montoFacturaVariaoc = false;
	
	@Column(name ="RUTA_REPORTES")
	private String rutaReportes;
	
	public boolean isNombreCuentaConcatenado() {
		return nombreCuentaConcatenado;
	}
	public void setNombreCuentaConcatenado(boolean pNombreCuentaConcatenado) {
		nombreCuentaConcatenado = pNombreCuentaConcatenado;
	}
	public boolean isNumSolSuministroPorArea() {
		return numSolSuministroPorArea;
	}
	public void setNumSolSuministroPorArea(boolean pNumSolSuministroPorArea) {
		numSolSuministroPorArea = pNumSolSuministroPorArea;
	}
	public boolean isVariosServiciosOSP() {
		return variosServiciosOSP;
	}
	public void setVariosServiciosOSP(boolean pVariosServiciosOSP) {
		variosServiciosOSP = pVariosServiciosOSP;
	}
	public String getNombreLogo() {
		return nombreLogo;
	}
	
	public boolean isMontoFacturaVariaoc() {
		return montoFacturaVariaoc;
	}
	public void setMontoFacturaVariaoc(boolean pMontoFacturaVariaoc) {
		montoFacturaVariaoc = pMontoFacturaVariaoc;
	}
	public void setNombreLogo(String pNombreLogo) {
		nombreLogo = pNombreLogo;
	}
	/**
	 * @return domicilio de la municipalidad
	 */
	public Domicilio getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Domicilio pDomicilio) {
		domicilio = pDomicilio;
	}
	
	public Integer getNumeroCortoClientePagoFacil() throws Exception{
		String locParteDecimal = Util.getParteDecimalAsString(numeroClientePagoFacil.doubleValue()/10000);
		Integer locResulado = Integer.valueOf(locParteDecimal);
		if(this.numeroClientePagoFacil.toString().endsWith(locResulado.toString())){
			return locResulado;
		}else if(this.numeroClientePagoFacil.toString().endsWith((++locResulado).toString()) ){
			return locResulado;
		}else{
			throw new Exception("No se ha podido recuperar el numero de cliente.");
		}
	}
	
	public Integer getNumeroClientePagoFacil() {
		return numeroClientePagoFacil;
	}
	public void setNumeroClientePagoFacil(Integer pNumeroClientePagoFacil) {
		numeroClientePagoFacil = pNumeroClientePagoFacil;
	}
	
	public long getIdMunicipalidad() {
		return idMunicipalidad;
	}
	public void setIdMunicipalidad(long pIdMunicipalidad) {
		idMunicipalidad = pIdMunicipalidad;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String pTelefono) {
		telefono = pTelefono;
	}
	
	public String toString() {	
		return (this.nombre==null)?"":this.nombre;
	}
	public String getEncabezadoReportes() {
		return encabezadoReportes;
	}
	public void setEncabezadoReportes(String pEncabezadoReportes) {
		encabezadoReportes = pEncabezadoReportes;
	}
	public String getSubencabezadoReportes() {
		return subencabezadoReportes;
	}
	public void setSubencabezadoReportes(String pSubencabezadoReportes) {
		subencabezadoReportes = pSubencabezadoReportes;
	}
	public byte[] getLogo() {
		return logo;
	}
	public void setLogo(byte[] pLogo) {
		logo = pLogo;
	}
	
	// ********************************************************************************************************************************/
		// AUDITORIA

		@Transient
		private long llaveUsuarioAuditoria;
		@Transient
		private String comentarioAuditoria;

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

		public long getSerialVersionUID() {
			return serialVersionUID;
		}

		public long getIdEntidad() {
			return this.idMunicipalidad;
		}

		public String getNombrePropiedadId() {
			return "idMunicipalidad";
		}

		public boolean isAuditable() {
			return true;
		}
		public String getRutaReportes() {
			return rutaReportes;
		}
		public void setRutaReportes(String pRutaReportes) {
			rutaReportes = pRutaReportes;
		}
}
