package muni.inventario.ABMArticulo;

import javax.faces.component.UIComponent;

import com.trascender.compras.recurso.persistent.inventario.Articulo;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.ActivarAbstractController;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;


public class ArticuloModel extends ABMModel{

	@Override
	public String getReglaNavegacion() {
		return "ABMArticulo";
	}

	@Override
	public String getNombreEntidad() {
		return "Articulo";
	}

	private ABMArticulo getBeanArticulo() {
		return (ABMArticulo) getRequestBean("inventario$ABMArticulo$ABMArticulo");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
        UIComponent[] noVacios = new UIComponent[5];
        String[] nomNoVacios = new String[5];
        UIComponent[] fechas = new UIComponent[1];
        String[] nomFechas = new String[1];
        UIComponent[] flotantes = new UIComponent[1];
        String[] nomFlotantes = new String[1];
        UIComponent[] esPositivo = new UIComponent[1];
        String[] nomEsPositivo = new String[1];

        ABMArticulo beanArticulo = getBeanArticulo();

        int pos = 0;
        noVacios[pos] = beanArticulo.getTfCodigoArticulo();
        nomNoVacios[pos++] = "C\363digo Art\355culo";
        noVacios[pos] = beanArticulo.getTfFechaEntradaServicio();
        nomNoVacios[pos++] = "Fecha Entrada Servicio";
        noVacios[pos] = beanArticulo.getTfCosto();
        nomNoVacios[pos++] = "Costo";
        noVacios[pos] = beanArticulo.getTfArea();
        nomNoVacios[pos++] = "Area";
        noVacios[pos] = beanArticulo.getTfNombre();
        nomNoVacios[pos++] = "Nombre";
        
        pos = 0;
        fechas[pos] = beanArticulo.getTfFechaEntradaServicio();
        nomFechas[pos++] = "Fecha Entrada Servicio";

        pos = 0;
        flotantes[pos] = beanArticulo.getTfCosto();
        nomFlotantes[pos++] = "Costo";

        pos = 0;
        esPositivo[pos] = beanArticulo.getTfCosto();
        nomEsPositivo[pos++] = "Costo";

        v.noSonVacios(noVacios, nomNoVacios);
        v.formatoFechaValido(fechas, nomFechas);
        v.sonFlotantes(flotantes, nomFlotantes);
        v.sonPositivos(esPositivo, nomEsPositivo);

		return v;
	}

	private void deshabilitarElementosConsultarEliminar() {
		ABMArticulo beanArticulo = getBeanArticulo();
		beanArticulo.getTfNombre().setDisabled(true);
		beanArticulo.getTfCodigoArticulo().setDisabled(true);
		beanArticulo.getTfCosto().setDisabled(true);
		beanArticulo.getTfColor().setDisabled(true);
		beanArticulo.getTfFechaCompra().setDisabled(true);
		beanArticulo.getTfFechaEntradaServicio().setDisabled(true);
		beanArticulo.getTfMarca().setDisabled(true);
		beanArticulo.getTfMaterial().setDisabled(true);
		beanArticulo.getTfModelo().setDisabled(true);
		beanArticulo.getTfNumeroSerie().setDisabled(true);
		beanArticulo.getBtnLimpiarArea().setRendered(false);
		beanArticulo.getBtnSeleccionarArea().setRendered(false);
		beanArticulo.getTaDescripcion().setDisabled(true);
		beanArticulo.getDdEstado().setDisabled(true);
		
	}

	public class AgregarArticuloController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Articulo locArticulo = (Articulo) pObject;
			getCommunicationComprasBean().getRemoteSystemStock().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemStock().addArticulo(locArticulo);
            return "El Art\355culo se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ArticuloModel.this;
		}

	}

	public class ModificarArticuloController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Articulo locArticulo = (Articulo) pObject;
			getCommunicationComprasBean().getRemoteSystemStock().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemStock().updateArticulo(locArticulo);
            return "El Art\355culo se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
		}

		@Override
		public ABMModel getModel() {
			return ArticuloModel.this;
		}
	}

	public class ConsultarArticuloController extends ConsultarAbstractController {

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
			return ArticuloModel.this;
		}

	}

	public class EliminarArticuloController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Articulo locArticulo = (Articulo) pObject;
			getCommunicationComprasBean().getRemoteSystemStock().setLlave(getSessionBean1().getLlave());
            getCommunicationComprasBean().getRemoteSystemStock().deleteArticulo(locArticulo);
            return "El Art\355culo se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
		}

		@Override
		public ABMModel getModel() {
			return ArticuloModel.this;
		}

	}
}
