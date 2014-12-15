/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.saic.ImprimirLiquidaciones;

import java.util.List;
import java.util.Set;

import javax.faces.component.UIComponent;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.LogLiquidacion;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacion;
import com.trascender.saic.recurso.persistent.Vencimiento;

/**
 *
 * @author Fer Luca
 */
public class LiquidacionModel extends ABMModel{

	public ABMLiquidacion getLiquidacionBean(){
		return (ABMLiquidacion) getRequestBean("saic$ImprimirLiquidaciones$ABMLiquidacion");
	}

	private Validador getValidadorAgregarModificar(){
		Validador v = new Validador();

		UIComponent[] noFechas = new UIComponent[1];
		String[] nomNoFechas = new String[1];
		UIComponent[] flotantes = new UIComponent[2];
		String[] nomFlotantes = new String[2];

		int pos = 0;
		noFechas[pos] = this.getLiquidacionBean().getTfFechaVencimiento();
		nomNoFechas[pos++] = "Fecha Vencimiento";

		v.formatoFechaValido(noFechas, nomNoFechas);

		boolean hayModVacio = false;
		for(Set<ModificadorLiquidacion> cadaSet : this.getCommunicationSAICBean().getMapaModificadores().values()){
			for(ModificadorLiquidacion cadaModificador : cadaSet){
				if(cadaModificador.getNombre() == null || cadaModificador.getNombre().equals("")
						|| cadaModificador.getValorModificador() == null || cadaModificador.getValorModificador().toString().equals("")){
					hayModVacio = true;
					break;
				}
			}
		}

		boolean hayBasicoNegativo = false;
		boolean hayInteresNegativo = false;
		List<LiquidacionTasa> locListaLiquidaciones = getCommunicationSAICBean().getListaLiquidaciones();
		for(LiquidacionTasa cadaLiquidacion : locListaLiquidaciones){
			if(cadaLiquidacion.getValor() < 0){
				hayBasicoNegativo = true;
			}
			if(cadaLiquidacion.getInteres() < 0){
				hayInteresNegativo = true;
			}
		}

		if(hayBasicoNegativo){
			v.getErrores().add("Todos los valores de Básico deben ser mayor o igual a cero");
		}
		if(hayInteresNegativo){
			v.getErrores().add("Todos los valores de Interés deben ser mayor o igual a cero");
		}

		boolean hayVencimientoVacio = false;
		boolean noHayVencimientos = false;

		for(Set<Vencimiento> cadaSet : this.getCommunicationSAICBean().getMapaVencimientos().values()){

			if(cadaSet.isEmpty()){
				noHayVencimientos = true;
			}
			for(Vencimiento cadaVencimiento : cadaSet){
				//                Comentado
				//                if(cadaVencimiento.getNombre() == null || cadaVencimiento.getNombre().equals("") || cadaVencimiento.getFecha() == null){
				//                    hayVencimientoVacio = true;
				//                }
			}
		}

		if(hayModVacio){
			v.getErrores().add("Debe completar los campos de todos los Modificadores");
		}

		if(hayVencimientoVacio){
			v.getErrores().add("Debe completar los campos de todos los Vencimientos");
		}

		if(noHayVencimientos){
			v.getErrores().add("Las Liquidaciones deben tener al menos un Vencimiento");
		}

		return v;
	}

	private void deshabilitarElementosConsultarEliminar(){

	}

	public class ModificarLiquidacionController extends ModificarAbstractController{

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			String comentario = (String) getLiquidacionBean().getTaComentario().getText();
			List<LiquidacionTasa> locListaLiquidaciones = getCommunicationSAICBean().getListaLiquidaciones();
			getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(getSessionBean1().getLlave());
			getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().updateLiquidacionTasa(locListaLiquidaciones, comentario);
			
			return "La Liquidación fue modificada con exito";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getLiquidacionBean().getStTitulo().setText("Modificar Liquidación");
		}

		@Override
		public ABMModel getModel() {
			return LiquidacionModel.this;
		}

	}

	@Override
	public String getReglaNavegacion() {
		return "ABMLiquidacion";
	}

	@Override
	public String getNombreEntidad() {
		return "Liquidación";
	}
}
