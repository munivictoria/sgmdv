package muni.habilitaciones.grpAutomotor.ABMValuacionAcara;


import java.io.File;

import javax.faces.component.UIComponent;

import com.sun.rave.web.ui.model.UploadedFile;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class ProcesarArchivoAcaraModel extends ABMModel {

	  private ABMProcesarArchivoAcara getBeanProcesarArchivoAcara() {
	        return (ABMProcesarArchivoAcara) getRequestBean("habilitaciones$grpAutomotor$ABMValuacionAcara$ABMProcesarArchivoAcara");
	    }
	
    public class AgregarController extends AgregarAbstractController {

        @Override
        public Validador getValidador() {
            Validador v = new Validador();
            UIComponent[] noVacios = new UIComponent[1];
            String[] nomNoVacios = new String[1];
            
            int pos = 0;
            noVacios[pos] = getBeanProcesarArchivoAcara().getUpload();
            nomNoVacios[pos++] = "Archivo";

            v.noSonVacios(noVacios, nomNoVacios);
            return v;
        }

        @Override
        public String accionBotonAceptar(Object pObject) throws Exception {
        	
        	UploadedFile uploadedFile = (UploadedFile) pObject;
        	File file = null;
        	
        	 String name =  uploadedFile.getOriginalName();
             if(name == null || name.length() == 0) {
                 name = "tmp.tmp";
             }
             String suffix = name.substring(name.indexOf("."));
             if(suffix.length() == 0) {
                 suffix = ".tmp";
             }
             String prefix = name.substring(0, name.indexOf("."));
             try {
                  file = File.createTempFile(prefix, suffix);
                 uploadedFile.write(file);
                 getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().procesarArchivoValuacionAcara(file);
                 
             } catch(Exception ex) {
                 ex.printStackTrace();
             }         
        	
            return "El Archivo se procesó correctamente";
        }

        @Override
        public void ocultarDeshabilitarEnVista() {
        }
 
        @Override
        public String getTextoBotonAceptar() {
            return "Procesar";
        }

		@Override
		public ABMModel getModel() {
			return ProcesarArchivoAcaraModel.this;
		}

		@Override
		public String getTituloPagina() {
			return "Procesar archivo Valuación Acara";
		}
        
    }

	@Override
	public String getReglaNavegacion() {
		return "ABMProcesarArchivoValuacionAcara";
	}

	@Override
	public String getNombreEntidad() {
		return "Procesar Archivo Valuación Acara";
	}
    
}

