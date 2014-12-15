/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMActaApertura;

import java.util.List;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.suministros.LineaOfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.OfertaContratacion;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
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
		return (ABMOfertaLicitacion) getRequestBean("compras$ABMActaApertura$ABMOfertaLicitacion");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();

		UIComponent[] noVacios = new UIComponent[1];
		String[] nomNoVacios = new String[1];

		int pos = 0;
		if (getBeanOfertaLicitacion().getTfProveedor().isRendered()) {

			noVacios[pos] = getBeanOfertaLicitacion().getTfProveedor();
		} else {
			noVacios[pos] = getBeanOfertaLicitacion().getDdProveedor();
		}
		nomNoVacios[pos++] = "Proveedor";
		v.noSonVacios(noVacios, nomNoVacios);

		boolean hayVacios = false;
		for (Object o : getBeanOfertaLicitacion().getObjectListDataProvider().getList()) {
			LineaOfertaContratacion cadaLinea = (LineaOfertaContratacion) o;
			if (cadaLinea.getPrecioUnitario() == 0.00) {
				hayVacios = true;
			}
		}

		if (hayVacios) {
			v.getErrores().add("Debe asignar un precio unitario a todos los renglones");
		}

		if (getBeanOfertaLicitacion().getObjectListDataProvider().getList().size() <= 0) {
			v.getErrores().add("La Oferta de Contratación debe tener al menos un Renglón");
		}

		OfertaContratacion locOferta = (OfertaContratacion) getBeanOfertaLicitacion().obtenerObjetoDelElementoPila(0, OfertaContratacion.class);

		if (getBeanOfertaLicitacion().getTfProveedor().getValue() != null
				&& !getBeanOfertaLicitacion().getTfProveedor().getValue().equals("")
				|| (getBeanOfertaLicitacion().getDdProveedor().getSelected() != null && !getBeanOfertaLicitacion().getDdProveedor().getSelected()
						.equals(""))) {

			List listaOfertas = locOferta.getContratacion().getListaOfertasContratacion();
			Proveedor locProveedor = locOferta.getProveedor();
			boolean yaExiste = false;
			for (Object o : listaOfertas) {
				OfertaContratacion cadaOferta = (OfertaContratacion) o;

				// Si las ofertas son distintas pero los proveedores son
				// iguales.
				if (!cadaOferta.equals(locOferta) && cadaOferta.getProveedor().equals(locProveedor)) {
					yaExiste = true;
				}
			}
			if (yaExiste) {
				v.getErrores().add("Ya existe una Oferta de Contratación de ese Proveedor");
			}
		}

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {

		// getBeanOfertaLicitacion().getTfProveedor().setDisabled(true);
		getBeanOfertaLicitacion().getTaComentario().setDisabled(true);
		getBeanOfertaLicitacion().getTfPrecioUnitario().setDisabled(true);
		getBeanOfertaLicitacion().getBtnSeleccionar().setRendered(false);
		getBeanOfertaLicitacion().getTfPlazo().setDisabled(true);
		getBeanOfertaLicitacion().getDdProveedor().setDisabled(true);
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			OfertaContratacion locOfertaLicitacion = (OfertaContratacion) pObject;
			getBeanOfertaLicitacion().getRequestBean1().setRespuestaABM(locOfertaLicitacion);
			return null;
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
			OfertaContratacion locOfertaContratacion = (OfertaContratacion) pObject;
			getBeanOfertaLicitacion().getRequestBean1().setRespuestaABM(locOfertaContratacion);
			return null;
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return OfertaLicitacionModel.this;
		}
	}

	public class ConsultarController extends ConsultarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
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
