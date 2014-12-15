package com.trascender.framework.recurso.persistent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Parameter;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import sun.awt.image.ByteArrayImageSource;
import sun.awt.image.ToolkitImage;

@Entity
@DiscriminatorValue(value = "IMAGEN")
public class ParametroSistemaImagen extends ParametroSistema {


	/**
	 * Representa un parametro de typo imagen
	 */
	private static final long serialVersionUID = -7292809864956363103L;

//	@Lob
//	@Basic(fetch = FetchType.LAZY)
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] imagen;
	
	@Column(name = "EXTENSION")
	private String extension;
	
	@Transient
	private File archivo;

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File pArchivo) {
		archivo = pArchivo;
	}

	/**
	 * Sube el archivo al atributo buffer, el cual sera persistido.
	 * 
	 * @param deleteSource
	 *            true: borra el archivo fuente. false = null
	 * @throws LogicaException
	 */
	public void loadFile(Boolean deleteSource, Integer maxSize) {

		if (archivo != null) {

			if (!archivo.exists() || !archivo.isFile()) {
				System.out.println("el archivo es incorrecto");
			}
			Integer tamañoMaximo = maxSize;
			if (archivo.length() > tamañoMaximo) {
				System.out.println("tamaño archivo incorrecto");
			}

			int size = (int) archivo.length();

			byte[] locByteBuffer = new byte[size];

			try {
				FileInputStream flujoEntrada = new FileInputStream(archivo);
				flujoEntrada.read(locByteBuffer);
				flujoEntrada.close();
				this.imagen = locByteBuffer;
				int punto = archivo.getAbsolutePath().lastIndexOf('.');
				if (punto != -1) {
					this.extension = archivo.getAbsolutePath().substring(punto);
				}
				else{ System.out.println("Archivo sin extension");}
				

			} catch (IOException e) {
				e.printStackTrace();
			}

			if (deleteSource != null && deleteSource) {
				try {
					archivo.delete();
				} catch (SecurityException e) {
					System.out
							.println("Warning Error al intentar borrar el archivo "
									+ archivo.getAbsolutePath()
									+ ".\n Razon: "
									+ e.getMessage());
				}
			}
		}
	}

	/**
	 * Baja el archivo del buffer al disco, en la ruta especificada.
	 * 
	 * @param absoluteDirOut
	 * @throws LogicaException
	 */
	public void rebuildFile(String absoluteDirOut) {
		if (this.imagen != null) {

			genFile(absoluteDirOut);

			try {

				FileOutputStream flujoSalida = new FileOutputStream(archivo);
				flujoSalida.write(imagen);
				flujoSalida.close();

			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}

	/**
	 * Crea un archivo en disco, En la ruta especificadad, a partir del
	 * nombreArchivoOriginal
	 * 
	 * @param dir
	 * @throws LogicaException
	 */
	private void genFile(String dir) {

		int i = 0;


	/*	int punto = nombreImagen.lastIndexOf('.');

		String nomAux = nombreImagen;

		if (punto != -1) {
			extencion = nombreImagen.substring(punto);
			nomAux = nombreImagen.substring(0, punto);
		}

		archivo = new File(dir + "/" + nombreImagen);
		
		

		while (archivo.exists()) {
			archivo = new File(dir + "/" + nomAux + "(" + (i++) + ")"
					+ extencion);
		}
		*/

		archivo = new File(dir + "/" + super.getCodigo()+this.extension);
		
		

		while (archivo.exists()) {
			archivo = new File(dir + "/" + super.getCodigo() + "(" + (i++) + ")"
					+ extension);
		}


		try {
			archivo.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
/**
 * Retorna parametro como Image
 * @return Image
 */
	 public Object getValorImage() {
	 if(this.imagen!= null){
	 return new ToolkitImage(new ByteArrayImageSource(this.imagen));
	 }
	 return null;
	 }
	@Override
	/**
	 * Retorna parametro como array de bytes
	 * @return imagen como Byte[] 
	 */
	public Object getValor() {
		return this.imagen;
	}
	
	
}
