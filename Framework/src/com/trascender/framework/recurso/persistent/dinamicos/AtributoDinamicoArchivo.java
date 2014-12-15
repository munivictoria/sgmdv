package com.trascender.framework.recurso.persistent.dinamicos;

import java.io.File;
import java.io.FileInputStream;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;

@Entity
@DiscriminatorValue(value = "ARCHIVO")
public class AtributoDinamicoArchivo extends AtributoDinamico<byte[]>{
	
	private static final long serialVersionUID = 1749971000461135750L;

	@Type(type="org.hibernate.type.BinaryType")
	@Column(name = "VALOR_ARCHIVO")
	private byte[] valor;
	
	@Column(name = "NOMBRE_ARCHIVO")
	private String nombreArchivo;
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String pNombreArchivo) {
		nombreArchivo = pNombreArchivo;
	}
	
	public AtributoDinamicoArchivo(){}
	
	public void setFile(File pFile) throws Exception{
		int size = (int) pFile.length();
		byte[] locByteBuffer = new byte[size];
		FileInputStream flujoEntrada = new FileInputStream(pFile);
		flujoEntrada.read(locByteBuffer);
		flujoEntrada.close();
		this.valor = locByteBuffer;
		this.nombreArchivo = pFile.getName();
	}
	
	@Override
	public void setValorString(String pValor) throws Exception {
	}

	@Override
	public String getValorString() {
		return null;
	}

	@Override
	public byte[] getValor() {
		return this.valor;
	}

	@Override
	public void setValor(byte[] pValor) {
		this.valor = pValor;
	}
	
	public String toString(){
		return this.nombreArchivo;
	}

	@Override
	public String getNombreAtributoValor() {
		return "valor";
	}

}
