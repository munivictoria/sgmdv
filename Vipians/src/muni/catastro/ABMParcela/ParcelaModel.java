/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.catastro.ABMParcela;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.ParcelaPorCuadra;
import com.trascender.catastro.recurso.persistent.PlanoConstruccion;
import com.trascender.catastro.recurso.persistent.PlanoMensura;
import com.trascender.catastro.recurso.persistent.RegistroMejora;
import com.trascender.catastro.recurso.persistent.RegistroPropietario;
import com.trascender.catastro.recurso.persistent.TituloPropiedadParcelario;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.controller.ABMModel;
import com.trascender.presentacion.abstracts.controller.AgregarAbstractController;
import com.trascender.presentacion.abstracts.controller.ConsultarAbstractController;
import com.trascender.presentacion.abstracts.controller.EliminarAbstractController;
import com.trascender.presentacion.abstracts.controller.ModificarAbstractController;
import com.trascender.presentacion.validadores.Validador;

/**
 * 
 * @author juanma
 */
public class ParcelaModel extends ABMModel {

	@Override
	public String getReglaNavegacion() {
		return "ABMParcela";
	}

	@Override
	public String getNombreEntidad() {
		return "Parcela";
	}

	private ABMParcela getBeanParcela() {
		return (ABMParcela) getRequestBean("catastro$ABMParcela$ABMParcela");
	}

	private Validador getValidadorAgregarModificar() {
		Validador v = new Validador();
		UIComponent[] noVacios = new UIComponent[3];
		String[] nomNoVacios = new String[3];
		UIComponent[] fechas = new UIComponent[3];
		String[] nomFechas = new String[3];
		UIComponent[] enteros = new UIComponent[12];
		String[] nomEnteros = new String[12];
		UIComponent[] flotantes = new UIComponent[3];
		String[] nomFlotantes = new String[3];

		int pos = 0;
		noVacios[pos] = getBeanParcela().getTfFechaAlta();
		nomNoVacios[pos++] = "Fecha Alta";
		noVacios[pos] = getBeanParcela().getTfSuperficie();
		nomNoVacios[pos++] = "Superficie";
		noVacios[pos] = getBeanParcela().getTfManzanaCuadra();
		nomNoVacios[pos++] = "Manzana";

		pos = 0;
		fechas[pos] = getBeanParcela().getTfFechaAlta();
		nomFechas[pos++] = "Fecha Alta Domicilio";
		fechas[pos] = getBeanParcela().getTfFechaInscripcion();
		nomFechas[pos++] = "Fecha Inscripción Título Propiedad";
		fechas[pos] = getBeanParcela().getTfFechaPlanoMensura();
		nomFechas[pos++] = "Fecha Inscripción Plano Mensura";

		pos = 0;
		enteros[pos] = getBeanParcela().getTfDpto();
		nomEnteros[pos++] = "Departamento";
		enteros[pos] = getBeanParcela().getTfPedania();
		nomEnteros[pos++] = "Pedania";
		enteros[pos] = getBeanParcela().getTfCircunscripcion();
		nomEnteros[pos++] = "Circunscripci\363n";
		enteros[pos] = getBeanParcela().getTfDistrito();
		nomEnteros[pos++] = "Distrito";
		enteros[pos] = getBeanParcela().getTfSubDistrito();
		nomEnteros[pos++] = "Subdistrito";
		enteros[pos] = getBeanParcela().getTfSeccionPoligono();
		nomEnteros[pos++] = "Secci\363n-Pol\355gono";
		enteros[pos] = getBeanParcela().getTfQuinta();
		nomEnteros[pos++] = "Quinta";
		enteros[pos] = getBeanParcela().getTfChacraFraccion();
		nomEnteros[pos++] = "Chacra-Fracci\363n";
		enteros[pos] = getBeanParcela().getTfNroParcela();
		nomEnteros[pos++] = "Nro.Parcela";
		enteros[pos] = getBeanParcela().getTfNroPartida();
		nomEnteros[pos++] = "Nro.Partida";
		enteros[pos] = getBeanParcela().getTfNroRegistro();
		nomEnteros[pos++] = "Nro.Registro";
//		enteros[pos] = getBeanParcela().getTfNroMatricula();
//		nomEnteros[pos++] = "Nro.Matr\355cula";
		enteros[pos] = getBeanParcela().getTfNroCuenta();
		nomEnteros[pos++] = "Nro.Cuenta";

		pos = 0;
		flotantes[pos] = getBeanParcela().getTfSuperficie();
		nomFlotantes[pos++] = "Superficie";
		flotantes[pos] = getBeanParcela().getTfAvaluoTerreno();
		nomFlotantes[pos++] = "Aval\372o del Terreno";
		flotantes[pos] = getBeanParcela().getTfAvaluoMejoras();
		nomFlotantes[pos++] = "Aval\372o de Mejoras";

		v.noSonVacios(noVacios, nomNoVacios);
		v.formatoFechaValido(fechas, nomFechas);
		v.esNumero(enteros, nomEnteros);
		v.sonFlotantes(flotantes, nomFlotantes);

		Parcela locParcela = (Parcela) this.getBeanParcela().getElementoPila().getObjetos().get(0);
		if(locParcela.getDomicilioParcelario() == null) {
			v.getErrores().add("El domicilio parcelario es requerido.");
		}
		
		ArrayList atributosDinamicos = this.getBeanParcela().obtenerObjetoDelElementoPila(14, ArrayList.class);
		Validador v2 = this.getBeanParcela().getPanelAtributoDinamico().validarCampos(atributosDinamicos);
		this.agruparValidadores(v, v2, "(Pestaña \"Más información\")");
		
		atributosDinamicos = this.getBeanParcela().obtenerObjetoDelElementoPila(17, ArrayList.class);
		Validador v3 = this.getBeanParcela().getPanelAtributoDinamicoTituloPropiedad().validarCampos(atributosDinamicos);
		this.agruparValidadores(v, v3, "(Pestaña \"Título de propiedad\")");
		
		List<RegistroPropietario> listaPropietarios = locParcela.getTituloPropiedad().getListaRegistrosPropietarios();
		if(listaPropietarios != null && listaPropietarios.size() > 0) {
			float porcentajeTotal = 0.0f;
			boolean alMenoUno = false;
			for(RegistroPropietario cadaPropietario : listaPropietarios) {
				if (cadaPropietario.getPorcentaje() != null) {
					porcentajeTotal += Util.redondear(cadaPropietario.getPorcentaje(), 2);
					alMenoUno = true;
				}
			}
			porcentajeTotal = Util.redondear(porcentajeTotal, 2);
			if (alMenoUno && porcentajeTotal != 0f && porcentajeTotal != 100f) {
				v.getErrores().add("La suma de los Porcentajes de cada Propietario debe ser 100.");
			}
		}
		
		atributosDinamicos = this.getBeanParcela().obtenerObjetoDelElementoPila(18, ArrayList.class);
		Validador v4 = this.getBeanParcela().getPanelAtributoDinamicoPlanoMensura().validarCampos(atributosDinamicos);
		this.agruparValidadores(v, v4, "(Pestaña \"Plano de Mensura\")");
		
//		atributosDinamicos = this.getBeanParcela().obtenerObjetoDelElementoPila(19, ArrayList.class);
//		Validador v5 = this.getBeanParcela().getPanelAtributoDinamicoPlanoConstruccion().validarCampos(atributosDinamicos);
//		this.agruparValidadores(v, v5, "(Pestaña \"Plano de Construcción\")");

		return v;
	}

	private void agruparValidadores(Validador pValidadoUnico, Validador otroValidador, String mensaje){
		for (Object cadaError : otroValidador.getErrores()) {
			pValidadoUnico.getErrores().add(cadaError);
		}
	}
	
	private void deshabilitarParcela() {
		ABMParcela abmParcela = getBeanParcela();

		abmParcela.getTfRegistroMejora().setDisabled(true);
		abmParcela.getTfFechaAlta().setDisabled(true);
		abmParcela.getTfSuperficie().setDisabled(true);
		abmParcela.getTfManzanaCuadra().setDisabled(true);
		abmParcela.getTfNroPartida().setDisabled(true);
		abmParcela.getTfNroRegistro().setDisabled(true);
		abmParcela.getTfNroMatricula().setDisabled(true);
		abmParcela.getTfNroCuenta().setDisabled(true);
		abmParcela.getTfFechaAlta().setDisabled(true);
		abmParcela.getTfDpto().setDisabled(true);
		abmParcela.getTfPedania().setDisabled(true);
		abmParcela.getTfCircunscripcion().setDisabled(true);
		abmParcela.getTfDistrito().setDisabled(true);
		abmParcela.getTfSubDistrito().setDisabled(true);
		abmParcela.getTfSeccionPoligono().setDisabled(true);
		abmParcela.getTfQuinta().setDisabled(true);
		abmParcela.getTfChacraFraccion().setDisabled(true);
		abmParcela.getTfLote().setDisabled(true);
		abmParcela.getTfNumeroParcela().setDisabled(true);
		abmParcela.getTfNroParcela().setDisabled(true);
		abmParcela.getTfNroPartida().setDisabled(true);
		abmParcela.getTfNroRegistro().setDisabled(true);
		abmParcela.getTfNroMatricula().setDisabled(true);
		abmParcela.getTfNroCuenta().setDisabled(true);
		abmParcela.getTfSuperficie().setDisabled(true);
		abmParcela.getTfAvaluoTerreno().setDisabled(true);
		abmParcela.getTfAvaluoMejoras().setDisabled(true);
		
		abmParcela.getBtnLimpiarDomicilio().setRendered(false);
		abmParcela.getBtnLimpiarManzanaCuadra().setRendered(false);
		abmParcela.getBtnLimpiarZona().setRendered(false);
		abmParcela.getBtnSeleccionarDomicilio().setRendered(false);
		abmParcela.getBtnSeleccionarManzana().setRendered(false);
		abmParcela.getBtnSeleccionarZona().setRendered(false);
		
		abmParcela.getTextField1().setDisabled(true); // Metros de Frente

		abmParcela.getChbClasificacionRegadio().setDisabled(true);
		abmParcela.getChbClasificacionSecano().setDisabled(true);
		abmParcela.getRbgClasificacionSegunPlanta().setDisabled(true);
		
		abmParcela.getDdClasificacionSegunPlantaUrbana().setDisabled(true);

		abmParcela.getGroupPanelVolanteCatastral().setRendered(true);
		abmParcela.getGroupPanelSubdivisionParcela().setRendered(true);
		abmParcela.getPanelAtributoDinamico().deshabilitarCampos();
		
		abmParcela.getTableColumn21().setRendered(false); //Columna Radio Button tabla zonas
		abmParcela.getGroupPanel6().setRendered(false); //Acciones sobre Zonas
		abmParcela.getGroupPanel3().setRendered(false); // Acciones Volante Catastral.
		abmParcela.getTableColumn7().setRendered(false);//Columna Radio Button tabla zonas
//		abmParcela.getGroupPanel5().setRendered(false);
//		abmParcela.getTableColumn18().setRendered(false);

		abmParcela.getTaComentario().setDisabled(true);

		deshabilitarCombos();
	}

	private void deshabilitarRegistroMejora(){
		ABMParcela abmParcela = getBeanParcela();

		abmParcela.getBtnQuitarRegMejora().setRendered(false);
		abmParcela.getBtnModificarRegMejora().setRendered(false);
		abmParcela.getBtnAgregarRegMejora().setRendered(false);
	}

	private void deshabilitarTituloPropiedad() {
		ABMParcela abmParcela = getBeanParcela();

		abmParcela.getBtnAgregarZona().setRendered(false);
		abmParcela.getBtnQuitarZona().setRendered(false);
		abmParcela.getRadioButton6().setDisabled(true);
		abmParcela.getBtnAgregarPersonaFisica().setRendered(false);
		abmParcela.getBtnAgregarPersonaJuridica().setRendered(false);
		abmParcela.getStaticText24().setRendered(false);
		abmParcela.getBtnQuitar().setRendered(false);
		abmParcela.getBtnQuitarTodos().setRendered(false);
		abmParcela.getRadioButton5().setDisabled(true);
		abmParcela.getTextArea1().setDisabled(true);
		abmParcela.getTfTitulo().setDisabled(true);
		abmParcela.getTfFechaInscripcion().setDisabled(true);
		abmParcela.getTfFolio().setDisabled(true);
		abmParcela.getTfAsiento().setDisabled(true);
		abmParcela.getTfArea().setDisabled(true);
		abmParcela.getDdTipoTransaccion().setDisabled(true);
		
		abmParcela.getPanelAtributoDinamicoTituloPropiedad().deshabilitarCampos();
	}

	private void deshabilitarPlanoMensura() {
		ABMParcela abmParcela = getBeanParcela();

		abmParcela.getTfFechaPlanoMensura().setDisabled(true);
		abmParcela.getTfNroPlanoMensura().setDisabled(true);
		abmParcela.getTfFolioPlanoMensura().setDisabled(true);
		abmParcela.getTfTomoPlanoMensura().setDisabled(true);
		abmParcela.getTfNroExpedientePlanoMensura().setDisabled(true);
		abmParcela.getTfNroRegistroPlanoMensura().setDisabled(true);
		abmParcela.getRbgTipoPlanoMensura().setDisabled(true);
		abmParcela.getRbgEstadoPlanoMensura().setDisabled(true);
		
		abmParcela.getPanelAtributoDinamicoPlanoMensura().deshabilitarCampos();
	}

	private void deshabilitarPlanoConstruccion() {
		ABMParcela abmParcela = getBeanParcela();

		abmParcela.getBtnQuitarPlanoConstruccion().setRendered(false);
		abmParcela.getBtnModificarPlanoConstruccion().setRendered(false);
		abmParcela.getBtnAgregarPlanoConstruccion().setRendered(false);
	}

	private void deshabilitarElementosConsultarEliminar() {
		deshabilitarParcela();
		deshabilitarTituloPropiedad();
		deshabilitarPlanoMensura();
		deshabilitarPlanoConstruccion();
		deshabilitarRegistroMejora();
		
		ABMParcela abmParcela = getBeanParcela();
		
		abmParcela.getTaComentarioLogAuditoria().setRendered(false);
		abmParcela.getLblComentarioLogAuditoria().setRendered(false);
	}

	private void deshabilitarCombos() {
		ABMParcela abmParcela = getBeanParcela();

		abmParcela.getDdAfectacionesExplicitas().setDisabled(true);
		abmParcela.getDdAfectacionesNoExplicitas().setDisabled(true);
		abmParcela.getDdClasificacionSegunPlantaUrbana().setDisabled(true);
		abmParcela.getDdComercial().setDisabled(true);
		abmParcela.getDdEquipamiento().setDisabled(true);
		abmParcela.getDdEquipamiento().setDisabled(true);
		abmParcela.getDdResidencial().setDisabled(true);
		abmParcela.getDdRestriccionesAlDominioExplicitas().setDisabled(true);
		abmParcela.getDdRestriccionesAlDominioNoExplicitas().setDisabled(true);
		abmParcela.getDdRural().setDisabled(true);
		abmParcela.getDdTipoParcela().setDisabled(true);
		abmParcela.getDdVarios().setDisabled(true);
	}

	private void deshabilitarElementosAgregar() {
		ABMParcela abmParcela = getBeanParcela();

		abmParcela.getGroupPanelVolanteCatastral().setRendered(false);
		abmParcela.getGroupPanelSubdivisionParcela().setRendered(false);
	}

	private void deshabilitarElementosModificar() {
		ABMParcela abmParcela = getBeanParcela();

		abmParcela.getGroupPanelVolanteCatastral().setRendered(true);
		abmParcela.getGroupPanelSubdivisionParcela().setRendered(false);
		abmParcela.getTfRegistroMejora().setDisabled(true);
	}

	private void validarPermisos() {
		try {
			ABMParcela abmParcela = getBeanParcela();
			//Parcela
			if ((!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), Parcela.serialVersionUID, Permiso.Accion.INSERT))
					|| (!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), Parcela.serialVersionUID, Permiso.Accion.UPDATE))) {
				deshabilitarParcela();
			} 
			
			// Titulo Propiedad
			if ((!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), TituloPropiedadParcelario.serialVersionUID, Permiso.Accion.SELECT))
					&& (!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), TituloPropiedadParcelario.serialVersionUID, Permiso.Accion.INSERT))
					&& (!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), TituloPropiedadParcelario.serialVersionUID, Permiso.Accion.UPDATE))) {
				abmParcela.getTabThree().setRendered(false);
			}
			if ((!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), TituloPropiedadParcelario.serialVersionUID, Permiso.Accion.INSERT))
					|| (!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), TituloPropiedadParcelario.serialVersionUID, Permiso.Accion.UPDATE))) {
				deshabilitarTituloPropiedad();
			} 

			// Plano Mensura
			if ((!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), PlanoMensura.serialVersionUID, Permiso.Accion.SELECT))
					&& (!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), PlanoMensura.serialVersionUID, Permiso.Accion.INSERT))
					&& (!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), PlanoMensura.serialVersionUID, Permiso.Accion.UPDATE))) {
				abmParcela.getTabFour().setRendered(false);
			}
			if ((!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), PlanoMensura.serialVersionUID, Permiso.Accion.INSERT))
					|| (!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), PlanoMensura.serialVersionUID, Permiso.Accion.UPDATE))) {
				deshabilitarPlanoMensura();
			}

			// Plano Construccion
			if ((!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), PlanoConstruccion.serialVersionUID, Permiso.Accion.SELECT)
					&& (!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), PlanoConstruccion.serialVersionUID, Permiso.Accion.INSERT)) 
					&& (!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), PlanoConstruccion.serialVersionUID, Permiso.Accion.UPDATE)))) {
				abmParcela.getTabFive().setRendered(false);
			}
			if ((!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), PlanoConstruccion.serialVersionUID, Permiso.Accion.INSERT))
					|| (!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), PlanoConstruccion.serialVersionUID, Permiso.Accion.UPDATE))) {
				deshabilitarPlanoConstruccion();
			}

			// Registro Mejora
			if ((!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), RegistroMejora.serialVersionUID, Permiso.Accion.SELECT)
					&& (!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), RegistroMejora.serialVersionUID, Permiso.Accion.INSERT)) 
					&& (!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), RegistroMejora.serialVersionUID, Permiso.Accion.UPDATE)))) {
				abmParcela.getTabSix().setRendered(false);
			}
			if ((!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), RegistroMejora.serialVersionUID, Permiso.Accion.INSERT))
					|| (!SecurityMgr.getInstance().getPermiso(getSessionBean1().getLlave(), RegistroMejora.serialVersionUID, Permiso.Accion.UPDATE))) {
				deshabilitarRegistroMejora();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class AgregarParcelaController extends AgregarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Parcela locParcela = (Parcela) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().addParcela(locParcela);

			return "La parcela se agreg\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			ABMParcela abmParcela = getBeanParcela();
			abmParcela.getGroupPanelPropietarios().setRendered(false);
			abmParcela.getGroupPanelZonas().setRendered(false);
			abmParcela.getTfRegistroMejora().setRendered(false);
			abmParcela.getLblRegistroMejora().setRendered(false);
			deshabilitarElementosAgregar();
			abmParcela.getGroupPanelObligaciones().setRendered(false);
			abmParcela.getTablaLogs().setRendered(false);
			
			try {
				getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(getSessionBean1().getLlave());
				abmParcela.getTfNroRegistro().setText(getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getSugerenciaNumeroRegistro());
			} catch(RemoteException e) {
				e.printStackTrace();
			}
			
			validarPermisos();
		}

		@Override
		public ABMModel getModel() {
			return ParcelaModel.this;
		}
	}

	public class ModificarParcelaController extends ModificarAbstractController {

		@Override
		public Validador getValidador() {
			return getValidadorAgregarModificar();
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Parcela locParcela = (Parcela) pObject;		

			getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().updateParcela(locParcela);

			return "La parcela se modific\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosModificar();


			validarPermisos();
		}

		@Override
		public ABMModel getModel() {
			return ParcelaModel.this;
		}
	}

	public class ConsultarParcelaController extends ConsultarAbstractController {

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
			ABMParcela abmParcela = getBeanParcela();
			List<ParcelaPorCuadra> locCuadra = abmParcela.obtenerObjetoDelElementoPila(2, List.class);

			for (Iterator iterator = locCuadra.iterator(); iterator.hasNext();) {
				ParcelaPorCuadra parcelaPorCuadra = (ParcelaPorCuadra) iterator	.next();

				if (parcelaPorCuadra.getMetrosPorCuadra().equals(0.0)){
					iterator.remove();
				}
			}
			deshabilitarElementosConsultarEliminar();
			validarPermisos();
		}

		@Override
		public ABMModel getModel() {
			return ParcelaModel.this;
		}
	}

	public class EliminarParcelaController extends EliminarAbstractController {

		@Override
		public Validador getValidador() {
			return null;
		}

		@Override
		public String accionBotonAceptar(Object pObject) throws Exception {
			Parcela locParcela = (Parcela) pObject;
			getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(getSessionBean1().getLlave());
			getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().deleteParcela(locParcela);

			return "La parcela se elimin\363 exitosamente";
		}

		@Override
		public void ocultarDeshabilitarEnVista() {
			deshabilitarElementosConsultarEliminar();
			getBeanParcela().getLblComentarioLogAuditoria().setRendered(true);
			getBeanParcela().getTaComentarioLogAuditoria().setRendered(true);
			validarPermisos();
		}

		@Override
		public ABMModel getModel() {
			return ParcelaModel.this;
		}
	}
}