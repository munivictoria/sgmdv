
package muni;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 
 * @author nico
 *
 */
public class Zip {
	
	private String carpetaTemporal;
	
	public String getCarpetaTemporal() {
		return carpetaTemporal;
	}

	public void setCarpetaTemporal(String carpetaTemporal) {
		this.carpetaTemporal = carpetaTemporal;
	}
	
	/**
	 * El primer File es el Zip, el resto son los archivos a meter dentro del zip.
	 * @param archivos
	 */
	public void comprimir(List<File> pListaArchivos, File pZip) {
		byte[] buffer = new byte[1024];
		
		try {
			FileOutputStream locFOS = new FileOutputStream(pZip.getPath());
			ZipOutputStream locZOS = new ZipOutputStream(locFOS);
			
			for(File cadaArchivo : pListaArchivos) {
				ZipEntry ze = new ZipEntry(cadaArchivo.getName());
				locZOS.putNextEntry(ze);

				FileInputStream in = new FileInputStream(this.carpetaTemporal + File.separator + cadaArchivo.getName());

				int len;
				while((len = in.read(buffer)) > 0) {
					locZOS.write(buffer, 0, len);
				}
				in.close();
			}

			locZOS.closeEntry();
			locZOS.close();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}