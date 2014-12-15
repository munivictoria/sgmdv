package com.trascender.habilitaciones.util;

import javax.persistence.EntityManager;

import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;

/**
 * 
 * @author JSantaCruz
 * @version 0.9.1
 */

public class Util {
	
	private static Util instance;
	
	private static EntityManager entityManager;
	
	private Util(){
		
	}
	
	public static Util getInstance(EntityManager pEntity) throws Exception{
		if(pEntity != null){
			entityManager = pEntity;
		}else throw new Exception("El gestor de persistencia no puede ser nulo.");
		
		if(instance == null){
			instance = new Util();
		}
		
		return instance;
	}

	/**
	 * Aplicable para Documentos TGI, SHPS, OSP
	 * Parche para solucionar el tema de domicilios que quedan flotando en la base de datos.
	 * 
	 * @param pDocumentoHabEspViejo Documento antes de ser updateado
	 * @param pDocumentoHabEspActualizado Documento despues de ser updateado
	 * @throws Exception
	 */
	public void deleteDomicilioExcluyente(DocHabilitanteEspecializado pDocumentoHabEspViejo, 
										  DocHabilitanteEspecializado pDocumentoHabEspActualizado) 
				throws Exception{
		
		if(pDocumentoHabEspActualizado == null || pDocumentoHabEspViejo == null){
			throw new HabilitacionesException(720);
		}
		
		if(pDocumentoHabEspActualizado.getDomicilio().getIdDomicilio() == pDocumentoHabEspViejo.getDomicilio().getIdDomicilio()){
			return;
		}
		
		Domicilio locDomicilioDocumentoViejo = null;
		Domicilio locDomicilioPostalPersonaViejo = null;
		Domicilio locDomicilioParcelarioViejo = null;
		
		DocHabilitanteEspecializado locDocumento = null;
		
		try{
				locDomicilioDocumentoViejo = pDocumentoHabEspViejo.getDomicilio();
				locDomicilioPostalPersonaViejo = pDocumentoHabEspViejo.getObligacion().getPersona().getDomicilioPostal();
				Boolean esDeSHPS = (pDocumentoHabEspViejo instanceof DocumentoSHPS) && (pDocumentoHabEspActualizado instanceof DocumentoSHPS);
				
				if(esDeSHPS){
					System.out.println("El Documento parece ser de SHPS La variable auxiliar DOMICILIO PARCELARIO va a ser igual al domicilio postal de la persona");
					locDomicilioParcelarioViejo = pDocumentoHabEspViejo.getObligacion().getPersona().getDomicilioPostal();
				
				}else locDomicilioParcelarioViejo = pDocumentoHabEspViejo.getParcela().getDomicilioParcelario();
				locDocumento =  pDocumentoHabEspActualizado;
		
			if ((!esDeSHPS 
					 && (locDomicilioDocumentoViejo.getIdDomicilio() 
						!= locDocumento.getParcela().getDomicilioParcelario().getIdDomicilio() ))
					 &&
					 (locDomicilioDocumentoViejo.getIdDomicilio() 
						!= locDocumento.getObligacion().getPersona().getDomicilioPostal().getIdDomicilio())
					 && locDomicilioDocumentoViejo.getIdDomicilio() 									// agregado ultimo
					 		!= locDocumento.getObligacion().getPersona().getDomicilio().getIdDomicilio() // se verifica el dom tambien xq se puede dar el caso de las personas q se le actualiza el id de domicilios
						){
					System.out.println("El domicilio del documento no es ni el de la parcela ni el del solicitante");
					
					if(locDocumento.getDomicilio().getIdDomicilio() != locDomicilioDocumentoViejo.getIdDomicilio())
					{
					System.out.println("El domicilio no es el mismo que el anterior quiere decir que es uno nuevo," +
							" y como no era ni el dom ni de la persona ni del documento hay q borrar el viejo." );
							
					System.out.println("IDs Domicilios anteriores (deldoc,delaPersona,delaParcela: "
							+locDomicilioDocumentoViejo.getIdDomicilio()+"/"
							+locDomicilioPostalPersonaViejo.getIdDomicilio()+"/"
							+((esDeSHPS)?"SHPS NO tiene dom ":locDocumento.getParcela().getDomicilioParcelario().getIdDomicilio()));
					
					System.out.println("IDs Domicilios nuevos  (deldoc,delaPersona,delaParcela: "
							+locDocumento.getDomicilio().getIdDomicilio()+"/"
							+locDocumento.getObligacion().getPersona().getDomicilio().getIdDomicilio()+"/"
							+ ((esDeSHPS)?"SHPS NO tiene dom ":locDocumento.getParcela().getDomicilioParcelario().getIdDomicilio()) );
				
					
					System.out.println("COMIENZA EL BORRADO DE DOMICILIO EXECEDENTE");
					
					entityManager.remove(this.entityManager.merge(locDomicilioDocumentoViejo));
					
					System.out.println("EL BORRADO DE DOMICILIO EXEDENTE SE AFECTUADO CORRECTAMENTE");
					}else 
						System.out.println("EL ID del domicilio sigue siendo el mismo - por lo tanto no se borra");
				}else 
					System.out.println("Tiene el id de la persona o de la parcela por lo tanto no es necesario borrar dom");
				
			}catch (Exception e) {
					e.printStackTrace();
					System.out.println("No se ha podido eliminar el dom Excedente, No deberia afectar la actualizacion del documento");
			}
		}
	
	/**
	 * Recupera el ultimo numero de tramite + 1
	 * @return Devuelve el numero inmediato superior del ultimo numero de tramite.
	 */
	public synchronized Integer getNumeroTramite() throws Exception{
		Object pObject = new Object();
		synchronized (pObject) {
			Integer locRetorno = 0;
			try{
				locRetorno = (Integer) entityManager.createQuery("SELECT MAX(o.numeroTramite) FROM Obligacion o").getSingleResult();
				
				if(locRetorno == null){
					Long locNum = ((Long)this.entityManager.createQuery("SELECT COUNT(a) FROM Obligacion a").getSingleResult());
					if( locNum != null && locNum == 0 ){
						locRetorno = 0;
					} else throw new HabilitacionesException(7);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return ( (locRetorno >= 0)?(locRetorno+1):null );
		}
	}
}
