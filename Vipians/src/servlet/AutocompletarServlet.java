
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.transients.AuxIdEntidad;
import com.trascender.framework.util.Util;

/**
 * 
 * @author nico
 * 
 */
public class AutocompletarServlet extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);

		String strValorRequest = request.getParameter("valor");
		String strTipoRequest = request.getParameter("tipo");

		if(!strTipoRequest.equals("seleccion")) {
			List<AuxIdEntidad> locListaValores = null;

			String strValor = URLDecoder.decode(strValorRequest, "UTF-8");

			if(strTipoRequest.equals("persona")) {
				try {
					this.getComunicationBean().getRemoteSystemPersonaFisica().setLlave(this.getSessionBean1().getLlave());
					locListaValores = this.getComunicationBean().getRemoteSystemPersonaFisica().findListaAuxIdPersona(strValor);
					
					this.getSessionBean1().getListaIdPersonas().clear();
					for(AuxIdEntidad cadaAux : locListaValores) {
						this.getSessionBean1().getListaIdPersonas().add(cadaAux.getId());
					}
				} catch(TrascenderException e) {
					e.printStackTrace();
				}
			}
			else if(strTipoRequest.equals("parcela")) {
				try {
					this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
					locListaValores = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().findListaAuxIdParcela(strValor);
					
					this.getSessionBean1().getListaIdParcelas().clear();
					for(AuxIdEntidad cadaAux : locListaValores) {
						this.getSessionBean1().getListaIdParcelas().add(cadaAux.getId());
					}
				} catch(TrascenderException e) {
					e.printStackTrace();
				}
			}
			else if(strTipoRequest.equals("bien")) {
				try {
					this.getCommunicationComprasBean().getRemoteSystemAdministracionBienes().setLlave(this.getSessionBean1().getLlave());
					locListaValores = this.getCommunicationComprasBean().getRemoteSystemAdministracionBienes().findListaAuxIdBien(strValor);
					
					this.getSessionBean1().getListaIdBienes().clear();
					for(AuxIdEntidad cadaAux : locListaValores) {
						this.getSessionBean1().getListaIdBienes().add(cadaAux.getId());
					}
				} catch(TrascenderException e) {
					e.printStackTrace();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			else if(strTipoRequest.equals("modeloVehiculo")) {
				try {
					this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
					locListaValores = this.getCommunicationHabilitacionesBean().getRemoteSystemDocumentoAutomotor().findListaAuxIdModeloVehiculo(strValor);
					
					this.getSessionBean1().getListaIdModelosVehiculo().clear();
					for(AuxIdEntidad cadaAux : locListaValores) {
						this.getSessionBean1().getListaIdModelosVehiculo().add(cadaAux.getId());
					}
				} catch(TrascenderException e) {
					e.printStackTrace();
				}
			}
			else if(strTipoRequest.equals("codigoCiiu")) {
				try {
					this.getComunicationBean().getRemoteSystemMunicipalidad().setLlave(this.getSessionBean1().getLlave());
					locListaValores = this.getComunicationBean().getRemoteSystemMunicipalidad().findListaAuxIdCodigoCiiu(strValor);
					
					this.getSessionBean1().getListaIdCodigoCiiu().clear();
					for(AuxIdEntidad cadaAux : locListaValores) {
						this.getSessionBean1().getListaIdCodigoCiiu().add(cadaAux.getId());
					}
				} catch(TrascenderException e) {
					e.printStackTrace();
				}
			}
			else if(strTipoRequest.equals("conceptoIngresoVario")) {
				try {
					this.getCommunicationContabilidadBean().getRemoteSystemAdministracionIngresos().setLlave(this.getSessionBean1().getLlave());
					try {
						locListaValores = this.getCommunicationContabilidadBean().getRemoteSystemAdministracionIngresos().findListaAuxIdConceptoIngresoVario(strValor);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					this.getSessionBean1().getListaIdsConceptoIngresoVario().clear();
					for(AuxIdEntidad cadaAux : locListaValores) {
						this.getSessionBean1().getListaIdsConceptoIngresoVario().add(cadaAux.getId());
					}
				} catch(TrascenderException e) {
					e.printStackTrace();
				}
			}

			Collections.sort(locListaValores, new Comparator<AuxIdEntidad>() {
				public int compare(AuxIdEntidad o1, AuxIdEntidad o2) {
					String obj1 = Util.reemplazarAcentos(o1.getValor());
					String obj2 = Util.reemplazarAcentos(o2.getValor());

					return obj1.compareToIgnoreCase(obj2);
				}
			});
			
			// Se codifica para mandarlo al jsp
			for(AuxIdEntidad cadaAux : locListaValores) {
				cadaAux.setValor(URLEncoder.encode(cadaAux.getValor(), "UTF-8"));
			}

			Gson nuevo = new Gson();
			String json = nuevo.toJson(locListaValores);
			PrintWriter out = response.getWriter();
			out.println(json);
		} else {
			String strId = request.getParameter("id");
			String strBean = request.getParameter("bean");
			String strMetodo = request.getParameter("metodo");
			String idSubSesion = request.getParameter("idSubSesion");

			Object objBean = this.findBean(strBean);
			this.setearIdAlBean(objBean, strMetodo, strId, idSubSesion);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Servlet de autocompletado de campos.";
	}

	/* get Sessions, Requests y Communication Beans */
	public Object findBean(String beanName) {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
	}

	private muni.SessionBean1 getSessionBean1() {
		return (muni.SessionBean1) findBean("SessionBean1");
	}

	private muni.CommunicationComprasBean getCommunicationComprasBean() {
		return (muni.CommunicationComprasBean) findBean("CommunicationComprasBean");
	}
	
	private muni.CommunicationContabilidadBean getCommunicationContabilidadBean() {
		return (muni.CommunicationContabilidadBean) findBean("CommunicationContabilidadBean");
	}

	private muni.CommunicationHabilitacionesBean getCommunicationHabilitacionesBean() {
		return (muni.CommunicationHabilitacionesBean) findBean("CommunicationHabilitacionesBean");
	}

	private muni.ComunicationCatastroBean getComunicationCatastroBean() {
		return (muni.ComunicationCatastroBean) findBean("ComunicationCatastroBean");
	}

	private muni.ComunicationBean getComunicationBean() {
		return (muni.ComunicationBean) findBean("ComunicationBean");
	}
	/*   						  */

	private void setearIdAlBean(Object bean, String nombreMetodo, String id, String idSubSesion) {
		try {
			Method metodo = bean.getClass().getMethod(nombreMetodo, String.class, String.class);
			metodo.invoke(bean, id, idSubSesion);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}