package com.trascender.framework.recurso.persistent;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "REPORTE_JASPER")
public class ReportesJasper implements Serializable{

	public static final long serialVersionUID = 6031578813812094150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_reporte")
	@SequenceGenerator(name = "gen_id_reporte", sequenceName = "gen_id_reporte",allocationSize = 1)
	@Column(name="ID_REPORTE")
	private long idReporte = -1;
	
	@Column(name = "Nombre")
	private String nombre;
	
	@Column(name = "Nombre_Archivo")
	private String nombreArchivo; 
	
	@Type(type="org.hibernate.type.BinaryType")
	@Column(name = "VALOR_ARCHIVO")
	private byte[] valor;

	public void setFile(File pFile) throws Exception{
		int size = (int) pFile.length();
		byte[] locByteBuffer = new byte[size];
		FileInputStream flujoEntrada = new FileInputStream(pFile);
		flujoEntrada.read(locByteBuffer);
		flujoEntrada.close();
		this.valor = locByteBuffer;
	}
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}


	public void setNombreArchivo(String pNombreArchivo) {
		nombreArchivo = pNombreArchivo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String pNombre) {
		nombre = pNombre;
	}


	public byte[] getValor() {
		return valor;
	}

	public void setValor(byte[] pValor) {
		valor = pValor;
	}

	public long getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(long pIdReporte) {
		idReporte = pIdReporte;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idReporte ^ (idReporte >>> 32));
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
		ReportesJasper other = (ReportesJasper) obj;
		if (idReporte != other.idReporte)
			return false;
		return true;
	}
	

	
}
