/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMOfertaLicitacion;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.suministros.OfertaContratacion;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author ubuntero
 */
public class OfertaLicitacionModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMOfertaLicitacion";
	}

	@Override
	public String getNombreEntidad() {
		return "Oferta Licitación";
	}

	private ABMOfertaLicitacion getBeanOfertaLicitacion() {
		return (ABMOfertaLicitacion) getRequestBean("compras$ABMOfertaLicitacion$ABMOfertaLicitacion");
	}

	private Validador getValidadorAgregarModificar() {

		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[4];
		String[] nomNoVacios = new String[4];
		UIComponent[] flotantes = new UIComponent[1];
		String[] nomFlotantes = new String[1];
		UIComponent[] fecha = new UIComponent[1];
		String[] nomFecha = new String[1];

		int pos = 0;
		noVacios[pos] = getBeanOfertaLicitacion().getTfLicitacion();
		nomNoVacios[pos++] = "Licitaci\363n";
		noVacios[pos] = getBeanOfertaLicitacion().getTfProveedor();
		nomNoVacios[pos++] = "Proveedor ";
		noVacios[pos] = getBeanOfertaLicitacion().getTfFechaOferta();
		nomNoVacios[pos++] = "Fecha Oferta";
		noVacios[pos] = getBeanOfertaLicitacion().getTfImporte();
		nomNoVacios[pos++] = "Importe";

		pos = 0;
		fecha[pos] = getBeanOfertaLicitacion().getTfFechaOferta();
		nomFecha[pos++] = "Fecha Oferta";

		pos = 0;
		flotantes[pos] = getBeanOfertaLicitacion().getTfImporte();
		nomFlotantes[pos++] = "Importe";

		v.noSonVacios(noVacios, nomNoVacios);
		v.formatoFechaValido(fecha, nomFecha);
		v.sonFlotantes(flotantes, nomFlotantes);
		v.sonPositivos(flotantes, nomFlotantes);

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {

		getBeanOfertaLicitacion().getTfExtensionMantenimientoOferta().setDisabled(true);
		getBeanOfertaLicitacion().getTfFechaOferta().setDisabled(true);
		getBeanOfertaLicitacion().getTfGarantia().setDisabled(true);
		getBeanOfertaLicitacion().getTfImporte().setDisabled(true);
		getBeanOfertaLicitacion().getTfLicitacion().setDisabled(true);
		getBeanOfertaLicitacion().getTfNumero().setDisabled(true);
		getBeanOfertaLicitacion().getTfPlazo().setDisabled(true);
		getBeanOfertaLicitacion().getTfPlazoMantenimientoOferta().setDisabled(true);
		getBeanOfertaLicitacion().getTfProveedor().setDisabled(true);
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			OfertaContratacion locOfertaLicitacion = (OfertaContratacion) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().addOfertaLicitacion(locOfertaLicitacion);
			return "La oferta de licitaci\363n se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return OfertaLicitacionModel.this;
		}
	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			OfertaContratacion locOfertaLicitacion = (OfertaContratacion) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().updateOfertaLicitacion(locOfertaLicitacion);
			return "La oferta de licitaci\363n se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return OfertaLicitacionModel.this;
		}
	}

	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			OfertaContratacion locOfertaLicitacion = (OfertaContratacion) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().deleteOfertaLicitacion(locOfertaLicitacion);
			return "La oferta de licitaci\363n se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return OfertaLicitacionModel.this;
		}
	}

	public class ConsultarController extends ConsultarAbstractController {

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
			return OfertaLicitacionModel.this;
		}
	}
}