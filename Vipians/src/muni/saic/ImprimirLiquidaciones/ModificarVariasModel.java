/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.saic.ImprimirLiquidaciones;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import javax.faces.component.UIComponent;

import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.LogLiquidacion;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacion;
import com.trascender.saic.recurso.persistent.ModificadorLiquidacionManual;
import com.trascender.saic.recurso.persistent.Vencimiento;

/**
 *
 * @author Fer Luca
 */
public class ModificarVariasModel extends ABMModel{

	public ModificarVarias getBeanModificarVarias(){
		return (ModificarVarias) getRequestBean("saic$ImprimirLiquidaciones$ModificarVarias");
	}

	private Validador getValidadorAgregarModificar(){
		Validador v = new Validador();

		UIComponent[] noFechas = new UIComponent[1];
		String[] nomNoFechas = new String[1];
		UIComponent[] flotantes = new UIComponent[2];
		String[] nomFlotantes = new String[2];

		int pos = 0;

		for(String cadaString : this.getCommunicationSAICBean().getMapaBasicosLiq().values()){
			if(!cadaString.equals("diferentes valores")){
				try{
					Double.parseDouble(cadaString);
				} catch(Exception e){
					e.printStackTrace();
					v.getErrores().add("Básico debe contener solo números");
					break;
				}
			}
		}

		for(String cadaString : this.getCommunicationSAICBean().getMapaInteresesLiq().values()){
			if(!cadaString.equals("diferentes valores")){
				try{
					Double.parseDouble(cadaString);
				} catch(Exception e){
					e.printStackTrace();
					v.getErrores().add("Interés debe contener sólo números");
					break;
				}
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("dd/MM/yyyy");
		boolean errorFechaVacia = false;
		boolean errorFechaNoValida = false;
		boolean errorNombreVacio = false;
		boolean errorNombreRepetido = false;
		for(SortedSet<ModificarVarias.VencimientoEnTabla> cadaListaVenc : this.getCommunicationSAICBean().getMapaVencimientosLiq().values()){

			for(ModificarVarias.VencimientoEnTabla cadaVencimiento : cadaListaVenc){
				int cont = 0;
				if(!errorFechaVacia && (cadaVencimiento.getFecha() == null || cadaVencimiento.getFecha().equals(""))){
					v.getErrores().add("El campo Fecha de los Vencimientos es requerido");
				}
				if(!errorFechaNoValida && !cadaVencimiento.getFecha().equals("") && !cadaVencimiento.getFecha().equals("diferentes valores")){
					try{
						sdf.parse(cadaVencimiento.getFecha());
					}catch(Exception e){
						e.printStackTrace();
						v.getErrores().add("El formato de las fechas debe ser dd/mm/aaaa");
						errorFechaNoValida = true;
					}
				}
				if(!errorNombreVacio && cadaVencimiento.getNombre().equals("")){
					errorNombreVacio = true;
					v.getErrores().add("El campo Nombre de los Vencimientos es requerido");
				}
				if(!errorNombreRepetido){
					for(ModificarVarias.VencimientoEnTabla cadaVencimiento2 : cadaListaVenc){
						if(cadaVencimiento.getNombre().equals(cadaVencimiento2.getNombre())){
							cont++;
						}
					}
					if(cont>1){
						errorNombreRepetido = true;
						v.getErrores().add("Hay Vencimientos con nombre repetido");
					}
				}
			}

		}

		errorNombreVacio = false;
		errorNombreRepetido = false;
		boolean errorValorNoValido = false;
		for(Set<ModificarVarias.ModificadorEnTabla> cadaListaMod : this.getCommunicationSAICBean().getMapaModificadoresLiq().values()){

			for(ModificarVarias.ModificadorEnTabla cadaMod : cadaListaMod){
				int cont = 0;

				if (cadaMod.getValorModificador().equals("")) {
					cadaMod.setValorModificador("0.0");
				}

				if(!errorValorNoValido && !cadaMod.getValorModificador().equals("diferentes valores")){
					try{
						Double.parseDouble(cadaMod.getValorModificador());

					}catch(NumberFormatException ex){
						System.out.println("numberFormatEx");
						v.getErrores().add("El Valor de los modificadores debe ser numérico");
						errorValorNoValido = true;
					}
				}

				if(!errorNombreVacio && cadaMod.getNombre().equals("")){
					errorNombreVacio = true;
					v.getErrores().add("El campo Nombre de los Modificadores es requerido");
				}
				if(!errorNombreRepetido){
					for(ModificarVarias.ModificadorEnTabla cadaMod2 : cadaListaMod){
						if(cadaMod.getNombre().equals(cadaMod2.getNombre())){
							cont++;
						}
					}
					if(cont>1){
						errorNombreRepetido = true;
						v.getErrores().add("Hay Modificadores con nombre repetido");
					}
				}
			}

		}

		return v;
	}

	private void deshabilitarElementosConsultarEliminar(){

	}

	public class ModificarVariasController extends ModificarAbstractController{

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().setLlave(getSessionBean1().getLlave());

			List<LiquidacionTasa> locListaLiqAUpdatear = new ArrayList<LiquidacionTasa>();

			Map<String,Set<ModificarVarias.ModificadorEnTabla>> mapaModificadoresLiqAgrupadas = getCommunicationSAICBean().getMapaModificadoresLiq();
			Map<String,String> mapaBasicosLiqAgrupadas = getCommunicationSAICBean().getMapaBasicosLiq();
			Map<String,String> mapaInteresesLiqAgrupadas = getCommunicationSAICBean().getMapaInteresesLiq();
			Map<String,SortedSet<ModificarVarias.VencimientoEnTabla>> mapaVencimientosLiqAgrupadas = getCommunicationSAICBean().getMapaVencimientosLiq();

			for(LiquidacionTasa liq : getCommunicationSAICBean().getListaLiquidaciones()){
				boolean seModifico = false;

				if (!mapaBasicosLiqAgrupadas.get(liq.getTipoTasa().getTipoObligacion().getNombre()).equals("diferentes valores")) {
					Double basico = Double.parseDouble(mapaBasicosLiqAgrupadas.get(liq.getTipoTasa().getTipoObligacion().getNombre()));
					if (liq.getValor().compareTo(basico) != 0) {
						//                            System.out.println("Aca setea el basico: " + basico.toString());
						liq.setValor(basico);
						seModifico = true;
					}
				}

				if (!mapaInteresesLiqAgrupadas.get(liq.getTipoTasa().getTipoObligacion().getNombre()).equals("diferentes valores")) {
					Double interes = Double.parseDouble(mapaInteresesLiqAgrupadas.get(liq.getTipoTasa().getTipoObligacion().getNombre()));
					if (liq.getInteres().compareTo(interes) != 0) {
						//                            System.out.println("interes de la liq: " + liq.getInteres());
						//                            System.out.println("interes nuevo: " + interes);
						//                            System.out.println("Aca setea el interes: " + interes.toString());
						liq.setInteres(interes);
						seModifico = true;
					}
				}

				List<ModificadorLiquidacion> listaModsRemover = new ArrayList<ModificadorLiquidacion>();
				List<ModificadorLiquidacionManual> listaModsAgregar = new ArrayList<ModificadorLiquidacionManual>();
				for(ModificadorLiquidacion cadaMod : liq.getListaModificadoresLiquidacion()){
					boolean seEncontro = false;
					for(ModificarVarias.ModificadorEnTabla cadaModEnTabla : mapaModificadoresLiqAgrupadas.get(liq.getTipoTasa().getTipoObligacion().getNombre())){

						if(cadaModEnTabla.getListaIds().contains(cadaMod.getIdModificadorLiquidacion())){
							seEncontro = true;
							if((!cadaModEnTabla.getValorModificador().equals("diferentes valores") && Double.parseDouble(cadaModEnTabla.getValorModificador()) 
									!= cadaMod.getValorModificador())){
								cadaMod.setValorModificador(Double.parseDouble(cadaModEnTabla.getValorModificador()));
								seModifico = true;
							}
							if(!cadaModEnTabla.getNombre().equals(cadaMod.getNombre())){
								cadaMod.setNombre(cadaModEnTabla.getNombre());
								seModifico = true;
							}
						} else if(cadaModEnTabla.isNuevo()){
							//                                System.out.println("ENTRO ELSE IF --> INSTANCIANDO NUEVO MODIFICADOR MANUAL");
							ModificadorLiquidacionManual nuevoMod = new ModificadorLiquidacionManual();
							nuevoMod.setNombre(cadaModEnTabla.getNombre());
							nuevoMod.setValorModificador(Double.parseDouble(cadaModEnTabla.getValorModificador()));
							nuevoMod.setLiquidacionTasa(liq);
							listaModsAgregar.add(nuevoMod);
						}
					}
					if(!seEncontro){
						listaModsRemover.add(cadaMod);
					}
				}

				if(liq.getListaModificadoresLiquidacion().isEmpty() && !mapaModificadoresLiqAgrupadas.get(liq.getTipoTasa().getTipoObligacion().getNombre()).isEmpty()){
					for(ModificarVarias.ModificadorEnTabla cadaModEnTabla : mapaModificadoresLiqAgrupadas.get(liq.getTipoTasa().getTipoObligacion().getNombre())) {
						ModificadorLiquidacionManual nuevoMod = new ModificadorLiquidacionManual();
						nuevoMod.setNombre(cadaModEnTabla.getNombre());
						nuevoMod.setValorModificador(Double.parseDouble(cadaModEnTabla.getValorModificador()));
						nuevoMod.setLiquidacionTasa(liq);
						listaModsAgregar.add(nuevoMod);
					}
				}

				if(listaModsAgregar.size() > 0){
					//                        System.out.println("LISTA DE MODS A AGREGAR ES > A 0");
					liq.getListaModificadoresLiquidacion().addAll(listaModsAgregar);
					seModifico = true;
				}

				if(listaModsRemover.size() > 0){
					liq.getListaModificadoresLiquidacion().removeAll(listaModsRemover);
					seModifico = true;
				}

				List<Vencimiento> listaVencRemover = new ArrayList<Vencimiento>();
				List<Vencimiento> listaVencAgregar = new ArrayList<Vencimiento>();
				SimpleDateFormat sdf = new SimpleDateFormat();
				sdf.applyPattern("dd/MM/yyyy");
				for (Vencimiento cadaVenc : liq.getListaVencimientos()) {
					boolean seEncontro = false;
					for (ModificarVarias.VencimientoEnTabla cadaVencEnTabla : mapaVencimientosLiqAgrupadas.get(liq.getTipoTasa().getTipoObligacion().getNombre())) {

						if (cadaVencEnTabla.getListaIds().contains(cadaVenc.getIdVencimiento())) {
							seEncontro = true;

							if(!cadaVencEnTabla.getFecha().equals("diferentes valores")){
								Date dateVencTabla = sdf.parse(cadaVencEnTabla.getFecha());
								//                                    System.out.println("dateVencTabla: " + sdf.format(dateVencTabla));
								//                                    System.out.println("cadaVenc.getFecha()" + sdf.format(cadaVenc.getFecha()));

								//                                    Comentado
								//                                    if (dateVencTabla != null && !sdf.format(dateVencTabla).equals(sdf.format(cadaVenc.getFecha()))) {
								////                                        System.out.println("SON DATES DIFERENTES");
								//                                        cadaVenc.setFecha(dateVencTabla);
								//                                        seModifico = true;
								//                                    }
							}
							if (!cadaVencEnTabla.getNombre().equals(cadaVenc.getNombre())) {
								cadaVenc.setNombre(cadaVencEnTabla.getNombre());
								seModifico = true;
							}
						}else if(cadaVencEnTabla.isNuevo()){
							//                                System.out.println("ENTRO ELSE IF --> INSTANCIANDO NUEVO Vencimiento ");
							Vencimiento nuevoVenc = new Vencimiento();
							nuevoVenc.setNombre(cadaVencEnTabla.getNombre());
							//                                Comentado
							//                                nuevoVenc.setFecha(sdf.parse(cadaVencEnTabla.getFecha()));
							nuevoVenc.setValor(liq.getValor());
							nuevoVenc.setNumero(1);
							listaVencAgregar.add(nuevoVenc);
						}
					}
					if (!seEncontro) {
						listaVencRemover.add(cadaVenc);
					}
				}

				if(liq.getListaVencimientos().isEmpty() && !mapaVencimientosLiqAgrupadas.get(liq.getTipoTasa().getTipoObligacion().getNombre()).isEmpty()){
					for(ModificarVarias.VencimientoEnTabla cadaVencEnTabla : mapaVencimientosLiqAgrupadas.get(liq.getTipoTasa().getTipoObligacion().getNombre())) {
						Vencimiento nuevoVenc = new Vencimiento();
						nuevoVenc.setNombre(cadaVencEnTabla.getNombre());
						//                                Comentado
						//                                nuevoVenc.setFecha(sdf.parse(cadaVencEnTabla.getFecha()));
						nuevoVenc.setValor(liq.getValor());
						nuevoVenc.setNumero(1);

						listaVencAgregar.add(nuevoVenc);
					}
				}

				if(listaVencAgregar.size() > 0){
					//                        System.out.println("LISTA DE VENC A AGREGAR ES > A 0");
					//                        Comentado
					//                        liq.getListaVencimientos().addAll(listaVencAgregar);
					for(Vencimiento v : listaVencAgregar){
						//                            System.out.println("v.getNombre() " + v.getNombre());
						//                            System.out.println("v.getFecha().toString() " + v.getFecha().toString());

					}
					seModifico = true;
				}

				if (listaVencRemover.size() > 0) {
					liq.getListaVencimientos().removeAll(listaVencRemover);
					seModifico = true;
				}

				if(seModifico){
					locListaLiqAUpdatear.add(liq);
					liq.getListaVencimientos().size();
					//                        System.out.println("Y aca lo mete a la lista para persistir");
				}
			}

			//            System.out.println("tamaño final de la lista de liquidaciones a updatear: " + locListaLiqAUpdatear.size());
			//            System.out.println("LLama a la logica");
			String comentario = (String) getBeanModificarVarias().getTaComentario().getText();
			getCommunicationSAICBean().getRemoteSystemLiquidacionTasa().updateLiquidacionTasa(locListaLiqAUpdatear, comentario);
			
			return "Las Liquidaciones fueron modificadas con éxito";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			getBeanModificarVarias().getStTitulo().setText("Modificar Liquidaciones");
		}

		@Override
		public ABMModel getModel() {
			return ModificarVariasModel.this;
		}

	}

	@Override
	public String getReglaNavegacion() {
		return "ModificarVarias";
	}

	@Override
	public String getNombreEntidad() {
		return "Liquidaciones";
	}
}
