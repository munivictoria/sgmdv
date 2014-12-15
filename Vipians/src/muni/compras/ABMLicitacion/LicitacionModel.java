/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.compras.ABMLicitacion;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.suministros.Contratacion;
import com.trascender.compras.recurso.persistent.suministros.LineaContratacion;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.RepresentanteActaApertura;
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
public class LicitacionModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMLicitacion";
	}

	@Override
	public String getNombreEntidad() {
		return "Contratación";
	}

	private ABMLicitacion getBeanLicitacion() {
		return (ABMLicitacion) getRequestBean("compras$ABMLicitacion$ABMLicitacion");
	}

	private Validador getValidadorAgregarModificar() {

		ABMLicitacion abmLicitacion = this.getBeanLicitacion();

		Validador v = new Validador();
		UIComponent[] flotantes = new UIComponent[1];
		String[] nomFlotantes = new String[1];
		UIComponent[] fechas = new UIComponent[3];
		String[] nomFechas = new String[3];
		// fechas2 usado para controlar que ciertas fechas no sean mayores que
		// otras
		UIComponent[] fechas2 = new UIComponent[3];
		String[] nomFechas2 = new String[3];

		int pos = 0;
		flotantes[pos] = abmLicitacion.getTfPresupuestoOficial();
		nomFlotantes[pos++] = "Presupuesto Oficial";

		pos = 0;
		fechas[pos] = abmLicitacion.getTfFechaPublicacion();
		nomFechas[pos++] = "Fecha de Publicaci\363n";
		fechas[pos] = abmLicitacion.getTfFechaCierre();
		nomFechas[pos++] = "Fecha de Cierre";
		fechas[pos] = abmLicitacion.getTfFechaAperturaSobres();
		nomFechas[pos++] = "Fecha de Apertura de Sobres";

		pos = 0;
		fechas2[pos] = abmLicitacion.getTfFechaPublicacion();
		nomFechas2[pos++] = "Fecha de Publicaci\363n";
		fechas2[pos] = abmLicitacion.getTfFechaCierre();
		nomFechas2[pos++] = "Fecha de Cierre";
		fechas2[pos] = abmLicitacion.getTfFechaAperturaSobres();
		nomFechas2[pos++] = "Fecha de Apertura de Sobres";

		v.sonFlotantes(flotantes, nomFlotantes);
		v.formatoFechaValido(fechas, nomFechas);

		if (abmLicitacion.getTfFechaPublicacion().getText() != null && abmLicitacion.getTfFechaCierre().getText() != null
				&& abmLicitacion.getTfFechaAperturaSobres().getText() != null) {
			v.fechaEsMenorQue(fechas2, nomFechas2); // si entra al metodo siendo
													// null tira Nullpointer
		}

		if (abmLicitacion.getListaDelCommunication() == null || abmLicitacion.getListaDelCommunication().isEmpty()) {
			String msg = "Debe agregar al menos un renglon a la lista de L\355neas de la Contrataci\363n.";
			v.getErrores().add(msg);
		}

		boolean hayVacios = false;
		for (Object o : abmLicitacion.getObjectListDataProviderRepresentantesActaApertura().getList()) {
			RepresentanteActaApertura cadaRepresentante = (RepresentanteActaApertura) o;
			if (cadaRepresentante.getCargo().equals("")) {
				hayVacios = true;
			}
		}
		
		Contratacion locContratacion = abmLicitacion.obtenerObjetoDelElementoPila(0, Contratacion.class);
		
		for(LineaContratacion cadaLinea : locContratacion.getListaLineasContratacion()){
			if(cadaLinea.getLineaOfertaContratacionAdjudicada() != null && cadaLinea.getLineaOfertaContratacionAdjudicada().getPrecioUnitario() == 0){
				v.getErrores().add("No se puede realizar una adjudicación de una Línea de Oferta cuyo precio unitario es $0.00");
			}
		}
		
		if (hayVacios) {
			v.getErrores().add("Debe asignar un cargo a todos los Representantes del Acta de Apertura");
		}

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMLicitacion abmLicitacion = this.getBeanLicitacion();
		abmLicitacion.getTfDigestoMunicipalLicitacion().setDisabled(true);
		abmLicitacion.getTfExtensionMantenimientoOferta().setDisabled(true);
		abmLicitacion.getTfFechaAperturaSobres().setDisabled(true);
		abmLicitacion.getTfHoraAperturaSobres().setDisabled(true);
		abmLicitacion.getTfMinutoAperturaSobres().setDisabled(true);
		abmLicitacion.getTfFechaCierre().setDisabled(true);
		abmLicitacion.getTfHoraCierre().setDisabled(true);
		abmLicitacion.getTfMinutoCierre().setDisabled(true);
		abmLicitacion.getTfFechaOferta().setDisabled(true);
		abmLicitacion.getTfFechaPublicacion().setDisabled(true);
		abmLicitacion.getTfGarantia().setDisabled(true);
		abmLicitacion.getTfImporte().setDisabled(true);
		abmLicitacion.getTfNumero().setDisabled(true);
		abmLicitacion.getTfObjeto().setDisabled(true);
		abmLicitacion.getTfPlazo().setDisabled(true);
		abmLicitacion.getTfPlazoMantenimientoOferta().setDisabled(true);
		abmLicitacion.getTfPresupuestoOficial().setDisabled(true);
		abmLicitacion.getTfProveedor().setDisabled(true);
		abmLicitacion.getTfTitulo().setDisabled(true);
		abmLicitacion.getTaComentarios().setDisabled(true);
		abmLicitacion.getDdTipoLicitacion().setDisabled(true);

		abmLicitacion.getBtnAgregarLinea().setRendered(false);
		abmLicitacion.getBtnQuitarLinea().setRendered(false);
		abmLicitacion.getBtnQuitarTodosLinea().setRendered(false);
		abmLicitacion.getStSeparador2().setRendered(false);
		abmLicitacion.getTableColumn1().setRendered(false);
		abmLicitacion.getBtnSeleccionarDigestoMunicipalLicitacion().setRendered(false);
		abmLicitacion.getBtnLimpiarDigestoMunicipalLicitacion().setRendered(false);
		// abmLicitacion.getBtnAgregarDesdeSolicitud().setRendered(false);
		// abmLicitacion.getBtnModificarRenglon().setRendered(false);
		abmLicitacion.getGroupPanel1().setRendered(false);

		abmLicitacion.getGroupPanel2().setRendered(false);
		// abmLicitacion.getGroupPanel3().setRendered(false);
		abmLicitacion.getGroupPanel4().setRendered(false);
		abmLicitacion.getBtnAgregarOfertaContratacion().setRendered(false);
		abmLicitacion.getBtnModificarOfertaContratacion().setRendered(false);
		abmLicitacion.getBtnQuitarOfertaContratacion().setRendered(false);
		abmLicitacion.getBtnQuitarTodosOfertaContratacion().setRendered(false);

		abmLicitacion.getStSeparador3().setRendered(false);
		abmLicitacion.getStSeparador4().setRendered(false);
		abmLicitacion.getStSeparador5().setRendered(false);

		abmLicitacion.getTfFechaEntrega().setDisabled(true);
		abmLicitacion.getTfPrecioUnitarioReferencial().setDisabled(true);
		abmLicitacion.getTfLugar().setDisabled(true);
		abmLicitacion.getTfFechaApertura().setDisabled(true);
		abmLicitacion.getTaRegistroEscrito().setDisabled(true);
		abmLicitacion.getTableColumn2().setRendered(false);
		// abmLicitacion.getTableColumn3().setRendered(false);
		abmLicitacion.getTableColumn4().setRendered(false);
		// abmLicitacion.getDdOfertasContratacion().setDisabled(true);
		abmLicitacion.getDdOfertasContratacion().setDisabled(true);
		abmLicitacion.getBtnSeleccionarDigestoMunicipalLicitacion2().setRendered(false);
		abmLicitacion.getBtnLimpiarDigestoMunicipalLicitacion2().setRendered(false);
		abmLicitacion.getDdAdjudicarTodas().setRendered(false);
		abmLicitacion.getLblAdjudicarTodas().setRendered(false);
		
		abmLicitacion.getTfCantidad().setReadOnly(true);
	}

	public class AgregarController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Contratacion locAgregarContratacion = (Contratacion) pObject;
			verificarAdjudicacionSimple(locAgregarContratacion);
			getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().addContratacion(locAgregarContratacion);
			getCommunicationComprasBean().getMapaOpcionesAjudicacionSeleccionada().clear();
			return "La Contrataci\363n se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ABMLicitacion abmLicitacion = getBeanLicitacion();
			abmLicitacion.getTfNumero().setText("Asignado al guardar");
		}

		@Override
		public ABMModel getModel() {
			return LicitacionModel.this;
		}
	}

	// Verifica si el usuario selecciono un proveedor en la ultima pestaña para
	// adjudicar
	private void verificarAdjudicacionSimple(Contratacion pContratacion) {
		Proveedor proveedor = (Proveedor) getBeanLicitacion().obtenerObjetoDelElementoPila(8, Proveedor.class);
		if (proveedor != null && proveedor.getIdProveedor() != -1 && pContratacion.getListaProveedoresAutorizados().isEmpty()
				&& pContratacion.getListaOfertasContratacion().isEmpty()) {
			pContratacion.adjudicar(proveedor);
		}
	}

	public class ModificarController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Contratacion locAgregarContratacion = (Contratacion) pObject;
			verificarAdjudicacionSimple(locAgregarContratacion);
			getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().updateContratacion(locAgregarContratacion);
			getCommunicationComprasBean().getMapaOpcionesAjudicacionSeleccionada().clear();
			return "La Contrataci\363n se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return LicitacionModel.this;
		}
	}

	public class EliminarController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Contratacion locAgregarContratacion = (Contratacion) pObject;
			getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().setLlave(getSessionBean1().getLlave());
			getCommunicationComprasBean().getRemoteSystemAdministracionLicitacion().deleteContratacion(locAgregarContratacion);
			return "La Contrataci\363n se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return LicitacionModel.this;
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
			getBeanLicitacion().getTaComentarioLogAuditoria().setRendered(false);
			getBeanLicitacion().getLblComentarioLogAuditoria().setRendered(false);
		}

		@Override
		public ABMModel getModel() {
			return LicitacionModel.this;
		}
	}
}