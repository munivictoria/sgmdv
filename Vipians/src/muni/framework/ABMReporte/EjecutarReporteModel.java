/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package muni.framework.ABMReporte;

import jasper.ConstantesReportes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import com.sun.rave.web.ui.model.UploadedFile;
import com.trascender.framework.recurso.persistent.reporteDinamico.ParametroReporte;
import com.trascender.framework.recurso.persistent.reporteDinamico.Reporte;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class EjecutarReporteModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "EjecutarReporte";
	}

	@Override
	public String getNombreEntidad() {
		return "Reporte";
	}

	private EjecutarReporte getBeanReporte() {
		return (EjecutarReporte) getRequestBean("framework$ABMReporte$EjecutarReporte");
	}

	public class EjecutarController extends ModificarAbstractController {

		@Override
		public String getTextoBotonAceptar() {
			return "Ejecutar";
		}

		@Override
		public Validador getValidador() {
			return validarParametrosRequeridos();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Reporte reporte = (Reporte) pObject;

			try {
				Map<String, Object> locMapaParametros = getMapaParametrosLimpio(reporte);

				JasperPrint locJasper = getComunicationBean().getRemoteSystemParametro().getReporte(reporte, locMapaParametros);
				int tipoReporte = reporte.getTipo() == Reporte.Tipo.PDF ? ConstantesReportes.PDF : ConstantesReportes.XLSX;

				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, tipoReporte);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", reporte.getNombreArchivoJasper());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, locJasper);
			} catch(Exception e) {
				getBeanReporte().subirErrorEnReporteASesion();
				e.printStackTrace();

				return null;
			}

			return "El reporte se generó exitosamente";
		}

		@Override
		public String getTituloPagina() {
			return "Ejecutar " + this.getModel().getNombreEntidad();
		}

		@Override
		public void ocultarDeshabilitarEnVista() {

		}

		@Override
		public ABMModel getModel() {
			return EjecutarReporteModel.this;
		}

	}

	public class ProcesarReporteController extends ModificarAbstractController {

		@Override
		public String getTextoBotonAceptar() {
			return "Procesar";
		}

		@Override
		public Validador getValidador() {
			return validarParametrosRequeridos();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Reporte reporte = (Reporte) pObject;

			try {
				getBeanReporte().getRequestBean1().setObjetoSeleccion(getMapaParametrosLimpio(reporte));
			} catch(Exception e) {
				getBeanReporte().subirErrorEnReporteASesion();
				e.printStackTrace();

				return null;
			}

			// return "El reporte se generó exitosamente";
			return null;
		}

		@Override
		public String getTituloPagina() {
			return "Procesar " + this.getModel().getNombreEntidad();
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return EjecutarReporteModel.this;
		}

	}

	private Validador validarParametrosRequeridos() {
		Validador v = new Validador();

		try {
			Reporte locReporte = (Reporte) getBeanReporte().obtenerObjetoDelElementoPila(0);
			Map<String, Object> locMapaParametros = getBeanReporte().getMapaParametros();

			for(ParametroReporte parametro : locReporte.getListaParametroReporte()) {
				if(parametro.isRequerido()) {
					Object valor = locMapaParametros.get(parametro.getNombreAtributo());

					if(valor == null) {
						v.getErrores().add("El parámetro '" + parametro.getNombre() + "' es requerido.");
					} else {
						if(valor instanceof String) {
							String cadena = (String) valor;
							if(cadena == "") {
								v.getErrores().add("El parámetro '" + parametro.getNombre() + "' es requerido.");
							}
						} else if(valor instanceof UploadedFile) {
							UploadedFile archivo = (UploadedFile) valor;
							if(archivo.getOriginalName() == "") {
								v.getErrores().add("El parámetro '" + parametro.getNombre() + "' es requerido.");
							} else if(!archivo.getContentType().startsWith("image/")) {
								v.getErrores().add("El parámetro '" + parametro.getNombre() + "' debe ser de tipo imagen.");
							}
						} else if (valor instanceof Object[]) {
							Object[] arreglo = (Object[]) valor;
							if (arreglo.length <= 0) {
								v.getErrores().add("El parámetro '" + parametro.getNombre() + "' es requerido.");
							}
						}
					}
				}
			}

			if(v.getErrores().size() > 0) {
				getBeanReporte().subirErrorEnReporteASesion();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return v;
	}

	private Map<String, Object> getMapaParametrosLimpio(Reporte reporte) throws Exception {
		// Quitamos los valores nulos del mapa
		for(ParametroReporte cadaParametro : reporte.getListaParametroReporte()) {

			if(getBeanReporte().getMapaParametros().get(cadaParametro.getNombreAtributo()) == null) {
				getBeanReporte().getMapaParametros().remove(cadaParametro.getNombreAtributo());
			} else {
				if(cadaParametro.getTipo() == ParametroReporte.Tipo.LISTADO_SIMPLE 
						&& getBeanReporte().getMapaParametros().get(cadaParametro.getNombreAtributo()).equals("")) {
					getBeanReporte().getMapaParametros().remove(cadaParametro.getNombreAtributo());
				} else if (cadaParametro.getTipo() == ParametroReporte.Tipo.LISTADO_MULTIPLE) {
					//Si es listado multiple, lo paso de arreglo de objetos a lista de String 
					String[] arreglo = (String[]) getBeanReporte().getMapaParametros().get(cadaParametro.getNombreAtributo());
					getBeanReporte().getMapaParametros().remove(cadaParametro.getNombreAtributo());
					if (arreglo.length > 0) {
						List<String> lista = Arrays.asList(arreglo);
						getBeanReporte().getMapaParametros().put(cadaParametro.getNombreAtributo(), lista);
					}
				} else if(cadaParametro.getTipo().equals(ParametroReporte.Tipo.IMAGEN)) {
					UploadedFile locImage = (UploadedFile) getBeanReporte().getMapaParametros().get(cadaParametro.getNombreAtributo());
					if(locImage != null) {
						byte[] imagen = locImage.getBytes();
						getBeanReporte().getMapaParametros().remove(cadaParametro.getNombreAtributo());
						getBeanReporte().getMapaParametros().put(cadaParametro.getNombreAtributo(), imagen);
					}
				}
			}
		}

		return getBeanReporte().getMapaParametros();
	}

}