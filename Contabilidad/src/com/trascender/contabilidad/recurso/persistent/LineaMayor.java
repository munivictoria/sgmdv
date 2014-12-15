/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LINEA_MAYOR")
public class LineaMayor implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = 4841865763163874262L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_linea_mayor")
	@SequenceGenerator(name = "gen_id_linea_mayor", sequenceName = "gen_id_linea_mayor",allocationSize = 1)
	@Column(name="ID_LINEA_MAYOR")
	private long idLineaMayor = -1;
	
	@Column(name = "IMPORTE_DEBE")
	private Double importeDebe;
	
	@Column(name = "IMPORTE_HABER")
	private Double importeHaber;
	
	private Double saldo;
	
	@ManyToOne
	@JoinColumn(name = "ID_MAYOR")
	private Mayor mayor;
	
	@Column(name = "FECHA")
	private Date fechaGeneracion;
	
	@ManyToOne
	@JoinColumn(name = "ID_FOLIO_LIBRO_DIARIO")
	private FolioLibroDiario folioLibroDiario;
	
	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}
	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}
	public long getIdLineaMayor() {
		return idLineaMayor;
	}
	public void setIdLineaMayor(long idLineaMayor) {
		this.idLineaMayor = idLineaMayor;
	}
	public Mayor getMayor() {
		return mayor;
	}
	public void setMayor(Mayor mayor) {
		this.mayor = mayor;
	}
	public Double getImporteDebe() {
		return importeDebe;
	}
	public void setImporteDebe(Double importeDebe) {
		this.importeDebe = importeDebe;
	}
	public Double getImporteHaber() {
		return importeHaber;
	}
	public void setImporteHaber(Double importeHaber) {
		this.importeHaber = importeHaber;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idLineaMayor == -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idLineaMayor ^ (idLineaMayor >>> 32));
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
		final LineaMayor other = (LineaMayor) obj;
		if (idLineaMayor != other.idLineaMayor) {
			return false;
		}
		return true;
	}
	public FolioLibroDiario getFolioLibroDiario() {
		return folioLibroDiario;
	}
	public void setFolioLibroDiario(FolioLibroDiario folioLibroDiario) {
		this.folioLibroDiario = folioLibroDiario;
	}
	@Override
	public String toString(){
		DateFormat locDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
		return locDateFormat.format(this.fechaGeneracion)+" - "+this.folioLibroDiario+" "+this.saldo;
	}
}
