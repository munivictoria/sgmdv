/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.saic.ABMReliquidacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.component.UIComponent;

import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.LogLiquidacion;
import com.trascender.saic.recurso.persistent.ParametroReliquidacion;

/**
 *
 * @author Fer Luca
 */
public class ReliquidarVariasModel extends ABMModel{

	public ReliquidarVarias getBeanReliquidarVarias(){
		return (ReliquidarVarias) getRequestBean("saic$ABMReliquidacion$ReliquidarVarias");
	}

	private Validador getValidadorAgregarModificar(){
		Validador v = new Validador();

		UIComponent[] noFechas = new UIComponent[1];
		String[] nomNoFechas = new String[1];
		UIComponent[] flotantes = new UIComponent[2];
		String[] nomFlotantes = new String[2];
		
		List nuevosParametrosAValuar = getCommunicationSAICBean().getListaParametrosReliquidacionVarias();
        
		if (nuevosParametrosAValuar != null) {
		    for(Object cadaParametro : nuevosParametrosAValuar) {
		    	ParametroReliquidacion locParametro = (ParametroReliquidacion) cadaParametro;
		    	if(locParametro.getTipoValor().toString().equals("FIJO") && locParametro.getValor().trim().length() == 0) {
		    			v.getErrores().add("Debe ingresar todos los valores Fijos.");
		    			this.getBeanReliquidarVarias().mostrarEstadoObjetosUsados();
		    	}
			}
		}

		return v;
	}

	private void deshabilitarElementosConsultarEliminar(){

	}

	public class ReliquidarVariasController extends ModificarAbstractController{

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			List<LiquidacionTasa> listaLiquidaciones = getBeanReliquidarVarias().obtenerObjetoDelElementoPila(0, ArrayList.class);
			Date fechaReliquidacion = getBeanReliquidarVarias().obtenerObjetoDelElementoPila(1, Date.class);
			DigestoMunicipal digestoMunicipal = getBeanReliquidarVarias().obtenerObjetoDelElementoPila(2, DigestoMunicipal.class);
			ArrayList nuevosParametrosAValuar = null;
			ArrayList nuevosParametrosAValuarAlicuota = getBeanReliquidarVarias().obtenerObjetoDelElementoPila(4, ArrayList.class);        
			
			nuevosParametrosAValuar = (ArrayList) getCommunicationSAICBean().getListaParametrosReliquidacionVarias();
            if (nuevosParametrosAValuar != null && nuevosParametrosAValuar.size() > 0) {
                getBeanReliquidarVarias().getObjectListDataProvider3().setList(nuevosParametrosAValuar);
            }
			
            List<String> locListaParametrosActuales = new ArrayList<String>();
            HashMap<String, Object> locMapaParametrosFijos = new HashMap<String, Object>();
            if (nuevosParametrosAValuar != null) {
	            for(Object cadaParametro : nuevosParametrosAValuar) {
	            	ParametroReliquidacion locParametro = (ParametroReliquidacion) cadaParametro;
	            	if(locParametro.getTipoValor().toString().equals("ACTUAL")) {
	            		locListaParametrosActuales.add(locParametro.getNombreParametro());
	            	}
	            	else if(locParametro.getTipoValor().toString().equals("FIJO") && locParametro.getValor().startsWith("\"") && locParametro.getValor().endsWith("\"")) {
	            		String locValor = locParametro.getValor().trim();
						locValor = locValor.substring(1, locValor.length() - 1);
						locMapaParametrosFijos.put(locParametro.getNombreParametro(), locValor);
	            		}
	            		else {
	            			Double locValor = new Double(locParametro.getValor());
	            			locMapaParametrosFijos.put(locParametro.getNombreParametro(), locValor);
	            		}
	            }
            }
            
			boolean aplicarInteres = getBeanReliquidarVarias().getCbAplicarInteres().isChecked();
			try {
				getCommunicationSAICBean().getRemoteSystemReliquidacion().setLlave(getSessionBean1().getLlave());
				for (LiquidacionTasa cadaLiquidacion : listaLiquidaciones){
					getCommunicationSAICBean().getRemoteSystemReliquidacion()
					.reliquidarObligacion(cadaLiquidacion, fechaReliquidacion, locListaParametrosActuales, nuevosParametrosAValuarAlicuota,
							locMapaParametrosFijos, digestoMunicipal, aplicarInteres);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			return "Se generar\363n exitosamente las Reliquidaciones de las Tasas.";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public String getTituloPagina() {
			return "";
		}

		@Override
		public String getTextoBotonAceptar(){
			return "Reliquidar";
		}

		@Override
		public ABMModel getModel() {
			return ReliquidarVariasModel.this;
		}
	}

	@Override
	public String getReglaNavegacion() {
		return "ReliquidarVarias";
	}

	@Override
	public String getNombreEntidad() {
		return "Reliquidar Varias";
	}
}
