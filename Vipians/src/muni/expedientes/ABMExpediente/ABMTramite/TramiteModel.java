
package muni.expedientes.ABMExpediente.ABMTramite;

import muni.expedientes.NodoExpedienteControler;

import com.trascender.expedientes.recurso.persistent.NodoExpediente;
import com.trascender.expedientes.recurso.persistent.Responsable;
import com.trascender.expedientes.recurso.persistent.Tramite;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

public class TramiteModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMTramite";
	}

	@Override
	public String getNombreEntidad() {
		return "Trámite";
	}

	private Usuario usuario = getSessionBean1().getUsuario();

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
//		UIComponent[] noVacios = new UIComponent[1];
//		String[] nomNoVacios = new String[1];
//		int pos = 0;
//		noVacios[pos] = getBeanTramite().getDdEstado();
//		nomNoVacios[pos] = "Estado";
//
//		v.noSonVacios(noVacios, nomNoVacios);
		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMTramite abmTramite = getBeanTramite();
		abmTramite.getDdEstado().setDisabled(true);
		abmTramite.getTaComentarios().setDisabled(true);
		abmTramite.getTfNombre().setDisabled(true);
		abmTramite.getPgDatosRequeridos().setRendered(false);

		abmTramite.getTableDocumentos().getGroupPanel1().setRendered(false);
		abmTramite.getTableDocumentos().getTableColumn1().setRendered(false);
		abmTramite.getCbPresentado().setDisabled(true);
		abmTramite.getTaObservacion().setDisabled(true);

	}

	private ABMTramite getBeanTramite() {
		return (ABMTramite) getRequestBean("expedientes$ABMExpediente$ABMTramite");
	}

	public class ModificarController extends ModificarAbstractController implements NodoExpedienteControler {

		private String mensaje = "Debe ser un Usuario o pertenecer a un Area responsable de este Tr\341mite para poder modificarlo";

		@Override
		public void getValoresPorDefecto(NodoExpediente pNodoExpediente) {
			// Tramite pTramite = (Tramite) pNodoExpediente;
			// if (pTramite.getFechaInicio() == null)
			// pTramite.setFechaInicio(new Date());
			// pTramite.setEstado(EstadoTramite.ABIERTO);
		}

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Tramite locTramite = (Tramite) pObject;
			return "Tr\341mite " + locTramite.getPlantilla() + " modificado exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ABMTramite abmTramite = getBeanTramite();
			abmTramite.getTableDocumentos().getBtnModificar().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return TramiteModel.this;
		}

		@Override
		public boolean getValidacion(NodoExpediente pNodoExpediente) {
			Tramite pTramite = (Tramite) pNodoExpediente;
			boolean retorno = false;
			Responsable rExp = pTramite.getNodoPadre().getNodoPadre().getNodoProcedimiento().getResponsable();
			Responsable rFase = pTramite.getNodoPadre().getNodoProcedimiento().getResponsable();
			Responsable r = pTramite.getNodoProcedimiento().getResponsable();

			if(rExp != null && rExp.soyResponsable(usuario) != null ? rExp.soyResponsable(usuario) : false) {
				retorno = true;
			} else if(rFase != null && rFase.soyResponsable(usuario) != null ? rFase.soyResponsable(usuario) : false) {
				retorno = true;
			} else if(r != null && r.soyResponsable(usuario) != null ? r.soyResponsable(usuario) : false) {
				retorno = true;
			}

			if(!retorno) {
				mensaje = "Debe ser un Usuario o pertenecer a un Area responsable de este Tr\341mite para poder modificarlo";
			}

			return retorno;
		}

		@Override
		public String getMessage() {
			return mensaje;
		}
	}

	public class ConsultarControler extends ConsultarAbstractController implements NodoExpedienteControler {

		private String mensaje = "Debe ser un Usuario o pertenecer a un Area responsable o supervisora";

		@Override
		public void getValoresPorDefecto(NodoExpediente pNodoExpediente) {

		}

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			return null;
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return TramiteModel.this;
		}

		@Override
		public boolean getValidacion(NodoExpediente pNodoExpediente) {
			Tramite pTramite = (Tramite) pNodoExpediente;
			boolean retorno = false;
			Responsable rExp = pTramite.getNodoPadre().getNodoPadre().getNodoProcedimiento().getResponsable();
			Responsable rFase = pTramite.getNodoPadre().getNodoProcedimiento().getResponsable();
			Responsable r = pTramite.getNodoProcedimiento().getResponsable();

			if(rExp != null && rExp.soyResponsable(usuario) != null) {
				retorno = true;
			} else if(rFase != null && rFase.soyResponsable(usuario) != null) {
				retorno = true;
			} else if(r != null && r.soyResponsable(usuario) != null) {
				retorno = true;
			}

			return retorno;
		}

		@Override
		public String getMessage() {
			return mensaje;
		}
	}
}