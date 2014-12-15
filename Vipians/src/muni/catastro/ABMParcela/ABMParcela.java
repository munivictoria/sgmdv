/*
 * ModificarParcela.java
 *
 * Created on 3 de noviembre de 2006, 15:17
 * Copyright Trascender
 */

package muni.catastro.ABMParcela;

import jasper.ConstantesReportes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.event.ValueChangeEvent;

import muni.catastro.ABMRegistroMejora.RegistroMejoraModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import com.sun.data.provider.RowKey;
import com.sun.data.provider.SortCriteria;
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.component.Button;
import com.sun.rave.web.ui.component.Checkbox;
import com.sun.rave.web.ui.component.DropDown;
import com.sun.rave.web.ui.component.Label;
import com.sun.rave.web.ui.component.MessageGroup;
import com.sun.rave.web.ui.component.PageSeparator;
import com.sun.rave.web.ui.component.PanelGroup;
import com.sun.rave.web.ui.component.PanelLayout;
import com.sun.rave.web.ui.component.RadioButton;
import com.sun.rave.web.ui.component.RadioButtonGroup;
import com.sun.rave.web.ui.component.StaticText;
import com.sun.rave.web.ui.component.Tab;
import com.sun.rave.web.ui.component.TabSet;
import com.sun.rave.web.ui.component.Table;
import com.sun.rave.web.ui.component.TableColumn;
import com.sun.rave.web.ui.component.TableRowGroup;
import com.sun.rave.web.ui.component.TextArea;
import com.sun.rave.web.ui.component.TextField;
import com.sun.rave.web.ui.model.Option;
import com.sun.rave.web.ui.model.SingleSelectOptionsList;
import com.trascender.catastro.enums.AfectacionesCatastrales;
import com.trascender.catastro.enums.ClasificacionCatSegunSuUso;
import com.trascender.catastro.enums.ClasificacionCatSegunSuUso.Residencial;
import com.trascender.catastro.recurso.persistent.AsociacionParcela;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.catastro.recurso.persistent.NomenclaturaCatastral;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Parcela.TipoParcela;
import com.trascender.catastro.recurso.persistent.PlanoConstruccion;
import com.trascender.catastro.recurso.persistent.PlanoMensura;
import com.trascender.catastro.recurso.persistent.Planta;
import com.trascender.catastro.recurso.persistent.RegistroMejora;
import com.trascender.catastro.recurso.persistent.RegistroPropietario;
import com.trascender.catastro.recurso.persistent.TipoPlanta;
import com.trascender.catastro.recurso.persistent.TituloPropiedadParcelario;
import com.trascender.catastro.recurso.persistent.VolanteCatastral;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.Util;
import com.trascender.presentacion.abstracts.ABMPageBean;
import com.trascender.presentacion.conversores.Conversor;
import com.trascender.presentacion.navegacion.ElementoPila;
import com.trascender.presentacion.utiles.PanelAtributoDinamico;

/**
 * <p>
 * Page bean that corresponds to a similarly named JSP page. This class contains component definitions (and initialization code) for all components that you
 * have defined on this page, as well as lifecycle methods and event handlers where you may add behavior to respond to incoming evenbts.btn
 * </p>
 */
public class ABMParcela extends ABMPageBean {
	@Override
	protected void _init() throws Exception {
		Option[] opTipoPlanta = null;
		opTipoPlanta = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(Planta.Tipo.values(), "cap");
		rbgClasificacionSegunPlantaDefaultOptions.setOptions(opTipoPlanta);

		Option[] opPlantaUrbana = null;
		opPlantaUrbana = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(Planta.Edificacion.values(), "cap");
		ddClasificacionSegunPlantaUrbanaDefaultOptions.setOptions(opPlantaUrbana);

		Option[] opTipoParcela = null;
		opTipoParcela = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(TipoParcela.values(), "cap");
		ddTipoParcelaDefaultOptions.setOptions(opTipoParcela);

		Option[] opResidencial = null;
		opResidencial = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(ClasificacionCatSegunSuUso.Residencial.values(), "cap");
		ddResidencialDefaultOptions.setOptions(opResidencial);

		Option[] opComercial = null;
		opComercial = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(ClasificacionCatSegunSuUso.Comercial.values(), "cap");
		ddComercialDefaultOptions.setOptions(opComercial);

		Option[] opVarios = null;
		opVarios = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(ClasificacionCatSegunSuUso.Varios.values(), "cap");
		ddVariosDefaultOptions.setOptions(opVarios);

		Option[] opEquipamiento = null;
		opEquipamiento = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(ClasificacionCatSegunSuUso.Equipamiento.values(), "cap");
		ddEquipamientoDefaultOptions.setOptions(opEquipamiento);

		Option[] opRural = null;
		opRural = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(ClasificacionCatSegunSuUso.Rural.values(), "cap");
		ddRuralDefaultOptions.setOptions(opRural);

		Option[] opAfectacionesExplicitas = null;
		opAfectacionesExplicitas = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(AfectacionesCatastrales.Explicitas.values(), "cap");
		ddAfectacionesExplicitasDefaultOptions.setOptions(opAfectacionesExplicitas);

		Option[] opAfectacionesNoExplicitas = null;
		opAfectacionesNoExplicitas = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(AfectacionesCatastrales.NoExplicitas.values(), "cap");
		ddAfectacionesNoExplicitasDefaultOptions.setOptions(opAfectacionesNoExplicitas);

		Option[] opRestriccionesAlDominioExplicitas = null;
		opRestriccionesAlDominioExplicitas = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(AfectacionesCatastrales.RestriccionesDominioExplicitas.values(), "cap");
		ddRestriccionesAlDominioExplicitasDefaultOptions.setOptions(opRestriccionesAlDominioExplicitas);

		Option[] opRestriccionesAlDominioNoExplicitas = null;
		opRestriccionesAlDominioNoExplicitas = this.getApplicationBean1().getMgrDropDown().armarArrayOptions(AfectacionesCatastrales.RestriccionesDominioNoExplicitas.values(), "cap");
		ddRestriccionesAlDominioNoExplicitasDefaultOptions.setOptions(opRestriccionesAlDominioNoExplicitas);

		if(this.getListaDelCommunication() != null) {
			this.getObjectListDataProvider().setList(this.getListaDelCommunication());
		}
		if(this.getListaDelCommunication2() != null) {
			this.getObjectListDataProvider2().setList(this.getListaDelCommunication2());
		}
		if(this.getListaDelCommunication3() != null) {
			this.getObjectListDataProvider3().setList(this.getListaDelCommunication3());
		}
		if(this.getListaDelCommunication4() != null) {
			this.getObjectListDataProvider4().setList(this.getListaDelCommunication4());
		}
		if(this.getListaDelCommunication5() != null) {
			this.getObjectListDataProvider5().setList(this.getListaDelCommunication5());
		}
		if(this.getListaDelCommunicationObligaciones() != null) {
			this.getObjectListDataProviderObligaciones().setList(this.getListaDelCommunicationObligaciones());
		}
		if(this.getListaDelCommunication6() != null) {
			this.getLdpPlanoConstruccion().setList(this.getListaDelCommunication6());
		}

		Option[] opTipoPlanoMensura = null;
		opTipoPlanoMensura = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(PlanoMensura.Tipo.values(), "cap");
		rbgTipoPlanoMensuraOptions.setOptions(opTipoPlanoMensura);

		Option[] opEstadoPlanoMensura = null;
		opEstadoPlanoMensura = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(PlanoMensura.Estado.values(), "cap");
		rbgEstadoPlanoMensuraOptions.setOptions(opEstadoPlanoMensura);

		Option[] opTipoTransaccion = null;
		opTipoTransaccion = this.getApplicationBean1().getMgrDropDown().armarArrayOptionsList(TituloPropiedadParcelario.TipoTransaccionCatastral.values(), "cap");
		ddTipoTransaccionOptions.setOptions(opTipoTransaccion);

		dateTimeConverter1.setTimeZone(java.util.TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
		dateTimeConverter1.setPattern("EEEE, dd 'de' MMMM 'de' yyyy");
		dateTimeConverter1.setTimeStyle("full");

		this.btnImprimirVolante.setDisabled(true);
	}

	private PanelAtributoDinamico panelAtributoDinamicoTituloPropiedad = new PanelAtributoDinamico();
	private PanelAtributoDinamico panelAtributoDinamicoPlanoMensura = new PanelAtributoDinamico();

	private Label lblComentario = new Label();
	private TextArea taComentario = new TextArea();
	private Label lblRegistroMejora = new Label();
	private TextField tfRegistroMejora = new TextField();
	private TextField tfPorcentaje = new TextField();

	public TextField getTfPorcentaje() {
		return tfPorcentaje;
	}

	public void setTfPorcentaje(TextField tfPorcentaje) {
		this.tfPorcentaje = tfPorcentaje;
	}

	public PanelAtributoDinamico getPanelAtributoDinamicoTituloPropiedad() {
		return panelAtributoDinamicoTituloPropiedad;
	}

	public void setPanelAtributoDinamicoTituloPropiedad(PanelAtributoDinamico panelAtributoDinamicoTituloPropiedad) {
		this.panelAtributoDinamicoTituloPropiedad = panelAtributoDinamicoTituloPropiedad;
	}

	public PanelAtributoDinamico getPanelAtributoDinamicoPlanoMensura() {
		return panelAtributoDinamicoPlanoMensura;
	}

	public void setPanelAtributoDinamicoPlanoMensura(PanelAtributoDinamico panelAtributoDinamicoPlanoMensura) {
		this.panelAtributoDinamicoPlanoMensura = panelAtributoDinamicoPlanoMensura;
	}

	public Label getLblRegistroMejora() {
		return lblRegistroMejora;
	}

	public void setLblRegistroMejora(Label lblRegistroMejora) {
		this.lblRegistroMejora = lblRegistroMejora;
	}

	public TextField getTfRegistroMejora() {
		return tfRegistroMejora;
	}

	public void setTfRegistroMejora(TextField tfRegistroMejora) {
		this.tfRegistroMejora = tfRegistroMejora;
	}

	public Label getLblComentario() {
		return lblComentario;
	}

	public void setLblComentario(Label lblComentario) {
		this.lblComentario = lblComentario;
	}

	public TextArea getTaComentario() {
		return taComentario;
	}

	public void setTaComentario(TextArea taComentario) {
		this.taComentario = taComentario;
	}

	private Tab tabOne = new Tab();
	private Tab tabTwo = new Tab();
	private Tab tabThree = new Tab();
	private Tab tabFour = new Tab();
	private Tab tabFive = new Tab();
	private Tab tabSix = new Tab();

	public Tab getTabSix() {
		return tabSix;
	}

	public void setTabSix(Tab tabSix) {
		this.tabSix = tabSix;
	}

	public Tab getTabOne() {
		return tabOne;
	}

	public void setTabOne(Tab tabOne) {
		this.tabOne = tabOne;
	}

	public Tab getTabTwo() {
		return tabTwo;
	}

	public void setTabTwo(Tab tabTwo) {
		this.tabTwo = tabTwo;
	}

	public Tab getTabThree() {
		return tabThree;
	}

	public void setTabThree(Tab tabThree) {
		this.tabThree = tabThree;
	}

	public Tab getTabFour() {
		return tabFour;
	}

	public void setTabFour(Tab tabFour) {
		this.tabFour = tabFour;
	}

	public Tab getTabFive() {
		return tabFive;
	}

	public void setTabFive(Tab tabFive) {
		this.tabFive = tabFive;
	}

	private ObjectListDataProvider ldpRegistroPropietario = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpRegistroPropietario() {
		return ldpRegistroPropietario;
	}

	public void setLdpRegistroPropietario(ObjectListDataProvider oldp) {
		this.ldpRegistroPropietario = oldp;
	}

	private TabSet tabSet1 = new TabSet();
	private Label label20bis = new Label();
	private Label lblEdificacion = new Label();

	public Label getLblEdificacion() {
		return lblEdificacion;
	}

	public void setLblEdificacion(Label lblEdificacion) {
		this.lblEdificacion = lblEdificacion;
	}

	public Label getLabel20bis() {
		return label20bis;
	}

	public void setLabel20bis(Label label20bis) {
		this.label20bis = label20bis;
	}

	public TabSet getTabSet1() {
		return tabSet1;
	}

	public void setTabSet1(TabSet tabSet1) {
		this.tabSet1 = tabSet1;
	}

	private Label label3 = new Label();

	public Label getLabel3() {
		return label3;
	}

	public void setLabel3(Label l) {
		this.label3 = l;
	}

	private TextArea textArea1 = new TextArea();

	public TextArea getTextArea1() {
		return textArea1;
	}

	public void setTextArea1(TextArea ta) {
		this.textArea1 = ta;
	}

	private TextField tfNroParcela = new TextField();
	private TextField tfSuperficieDividida = new TextField();

	public TextField getTfSuperficieDividida() {
		return tfSuperficieDividida;
	}

	public void setTfSuperficieDividida(TextField tfSuperficieDividida) {
		this.tfSuperficieDividida = tfSuperficieDividida;
	}

	public TextField getTfNroParcela() {
		return tfNroParcela;
	}

	public void setTfNroParcela(TextField tf) {
		this.tfNroParcela = tf;
	}

	private Label label1 = new Label();

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label l) {
		this.label1 = l;
	}

	private Label label2 = new Label();

	public Label getLabel2() {
		return label2;
	}

	public void setLabel2(Label l) {
		this.label2 = l;
	}

	private Label label4 = new Label();

	public Label getLabel4() {
		return label4;
	}

	public void setLabel4(Label l) {
		this.label4 = l;
	}

	private TextField tfFechaAlta = new TextField();
	private TextField tfNroPartida = new TextField();

	public TextField getTfNroPartida() {
		return tfNroPartida;
	}

	public void setTfNroPartida(TextField tfNroPartida) {
		this.tfNroPartida = tfNroPartida;
	}

	public TextField getTfFechaAlta() {
		return tfFechaAlta;
	}

	public void setTfFechaAlta(TextField tf) {
		this.tfFechaAlta = tf;
	}

	private TextField tfSuperficie = new TextField();

	public TextField getTfSuperficie() {
		return tfSuperficie;
	}

	public void setTfSuperficie(TextField tf) {
		this.tfSuperficie = tf;
	}

	private Label label5 = new Label();

	public Label getLabel5() {
		return label5;
	}

	public void setLabel5(Label l) {
		this.label5 = l;
	}

	private Label label8 = new Label();

	public Label getLabel8() {
		return label8;
	}

	public void setLabel8(Label l) {
		this.label8 = l;
	}

	private Label label12 = new Label();

	public Label getLabel12() {
		return label12;
	}

	public void setLabel12(Label l) {
		this.label12 = l;
	}

	private Label label13 = new Label();

	public Label getLabel13() {
		return label13;
	}

	public void setLabel13(Label l) {
		this.label13 = l;
	}

	private Label label20 = new Label();

	public Label getLabel20() {
		return label20;
	}

	public void setLabel20(Label label20) {
		this.label20 = label20;
	}

	private Label label21 = new Label();

	public Label getLabel21() {
		return label21;
	}

	public void setLabel21(Label label21) {
		this.label21 = label21;
	}

	private Label label22 = new Label();

	public Label getLabel22() {
		return label22;
	}

	public void setLabel22(Label label22) {
		this.label22 = label22;
	}

	private Label label23 = new Label();

	public Label getLabel23() {
		return label23;
	}

	public void setLabel23(Label label23) {
		this.label23 = label23;
	}

	private Label label24 = new Label();

	public Label getLabel24() {
		return label24;
	}

	public void setLabel24(Label label24) {
		this.label24 = label24;
	}

	private Label label27 = new Label();

	public Label getLabel27() {
		return label27;
	}

	public void setLabel27(Label label27) {
		this.label27 = label27;
	}

	private Label label28 = new Label();

	public Label getLabel28() {
		return label28;
	}

	public void setLabel28(Label label28) {
		this.label28 = label28;
	}

	private Label label29 = new Label();

	public Label getLabel29() {
		return label29;
	}

	public void setLabel29(Label label29) {
		this.label29 = label29;
	}

	private Label label30 = new Label();

	public Label getLabel30() {
		return label30;
	}

	public void setLabel30(Label label30) {
		this.label30 = label30;
	}

	private Label label31 = new Label();

	public Label getLabel31() {
		return label31;
	}

	public void setLabel31(Label label31) {
		this.label31 = label31;
	}

	private Label label32 = new Label();

	public Label getLabel32() {
		return label32;
	}

	public void setLabel32(Label label32) {
		this.label32 = label32;
	}

	private Label label33 = new Label();

	public Label getLabel33() {
		return label33;
	}

	public void setLabel33(Label label33) {
		this.label33 = label33;
	}

	private Label label34 = new Label();

	public Label getLabel34() {
		return label34;
	}

	public void setLabel34(Label label34) {
		this.label34 = label34;
	}

	private Label label35 = new Label();

	public Label getLabel35() {
		return label35;
	}

	public void setLabel35(Label label35) {
		this.label35 = label35;
	}

	private Label label36 = new Label();

	public Label getLabel36() {
		return label36;
	}

	public void setLabel36(Label label36) {
		this.label36 = label36;
	}

	private Label label37 = new Label();

	public Label getLabel37() {
		return label37;
	}

	public void setLabel37(Label label37) {
		this.label37 = label37;
	}

	private Label label38 = new Label();

	public Label getLabel38() {
		return label38;
	}

	public void setLabel38(Label label38) {
		this.label38 = label38;
	}

	private Label label39 = new Label();

	public Label getLabel39() {
		return label39;
	}

	public void setLabel39(Label label39) {
		this.label39 = label39;
	}

	private Label label40 = new Label();

	public Label getLabel40() {
		return label40;
	}

	public void setLabel40(Label label40) {
		this.label40 = label40;
	}

	private Label label41 = new Label();
	private Label label42 = new Label();
	private Label label43 = new Label();
	private Label label44 = new Label();
	private Label label45 = new Label();
	private Label label46 = new Label();
	private Label label47 = new Label();
	private Label label48 = new Label();
	private Label label49 = new Label();
	private Label label50 = new Label();
	private Label label51 = new Label();
	private Label label52 = new Label();
	private Label label53 = new Label();
	private Label label54 = new Label();
	private Label label56 = new Label();
	private Label label57 = new Label();
	private Label label58 = new Label();
	private Label label59 = new Label();
	private Label label60 = new Label();
	private Label label61 = new Label();
	private Label label62 = new Label();
	private Label label63 = new Label();
	private Label label64 = new Label();
	private Label label66 = new Label();
	private Label label67 = new Label();
	private Label label68 = new Label();
	private Label label79 = new Label();
	private Label label80 = new Label();

	public Label getLabel80() {
		return label80;
	}

	public void setLabel80(Label label80) {
		this.label80 = label80;
	}

	public Label getLabel79() {
		return label79;
	}

	public void setLabel79(Label label79) {
		this.label79 = label79;
	}

	public Label getLabel49() {
		return label49;
	}

	public void setLabel49(Label label49) {
		this.label49 = label49;
	}

	public Label getLabel50() {
		return label50;
	}

	public void setLabel50(Label label50) {
		this.label50 = label50;
	}

	public Label getLabel51() {
		return label51;
	}

	public void setLabel51(Label label51) {
		this.label51 = label51;
	}

	public Label getLabel52() {
		return label52;
	}

	public void setLabel52(Label label52) {
		this.label52 = label52;
	}

	public Label getLabel53() {
		return label53;
	}

	public void setLabel53(Label label53) {
		this.label53 = label53;
	}

	public Label getLabel54() {
		return label54;
	}

	public void setLabel54(Label label54) {
		this.label54 = label54;
	}

	public Label getLabel56() {
		return label56;
	}

	public void setLabel56(Label label56) {
		this.label56 = label56;
	}

	public Label getLabel57() {
		return label57;
	}

	public void setLabel57(Label label57) {
		this.label57 = label57;
	}

	public Label getLabel58() {
		return label58;
	}

	public void setLabel58(Label label58) {
		this.label58 = label58;
	}

	public Label getLabel59() {
		return label59;
	}

	public void setLabel59(Label label59) {
		this.label59 = label59;
	}

	public Label getLabel60() {
		return label60;
	}

	public void setLabel60(Label label60) {
		this.label60 = label60;
	}

	public Label getLabel61() {
		return label61;
	}

	public void setLabel61(Label label61) {
		this.label61 = label61;
	}

	public Label getLabel62() {
		return label62;
	}

	public void setLabel62(Label label62) {
		this.label62 = label62;
	}

	public Label getLabel63() {
		return label63;
	}

	public void setLabel63(Label label63) {
		this.label63 = label63;
	}

	public Label getLabel64() {
		return label64;
	}

	public void setLabel64(Label label64) {
		this.label64 = label64;
	}

	public Label getLabel66() {
		return label66;
	}

	public void setLabel66(Label label66) {
		this.label66 = label66;
	}

	public Label getLabel67() {
		return label67;
	}

	public void setLabel67(Label label67) {
		this.label67 = label67;
	}

	public Label getLabel68() {
		return label68;
	}

	public void setLabel68(Label label68) {
		this.label68 = label68;
	}

	public Label getLabel48() {
		return label48;
	}

	public void setLabel48(Label label48) {
		this.label48 = label48;
	}

	public Label getLabel46() {
		return label46;
	}

	public void setLabel46(Label label46) {
		this.label46 = label46;
	}

	public Label getLabel47() {
		return label47;
	}

	public void setLabel47(Label label47) {
		this.label47 = label47;
	}

	public Label getLabel43() {
		return label43;
	}

	public void setLabel43(Label label43) {
		this.label43 = label43;
	}

	public Label getLabel44() {
		return label44;
	}

	public void setLabel44(Label label44) {
		this.label44 = label44;
	}

	public Label getLabel45() {
		return label45;
	}

	public void setLabel45(Label label45) {
		this.label45 = label45;
	}

	public Label getLabel42() {
		return label42;
	}

	public void setLabel42(Label label42) {
		this.label42 = label42;
	}

	public Label getLabel41() {
		return label41;
	}

	public void setLabel41(Label label41) {
		this.label41 = label41;
	}

	private TextField tfNroCuenta = new TextField();

	public TextField getTfNroCuenta() {
		return tfNroCuenta;
	}

	public void setTfNroCuenta(TextField tfNroCuenta) {
		this.tfNroCuenta = tfNroCuenta;
	}

	private TextField tfDpto = new TextField();

	public TextField getTfDpto() {
		return tfDpto;
	}

	public void setTfDpto(TextField tfDpto) {
		this.tfDpto = tfDpto;
	}

	private TextField tfPedania = new TextField();

	public TextField getTfPedania() {
		return tfPedania;
	}

	public void setTfPedania(TextField tfPedania) {
		this.tfPedania = tfPedania;
	}

	private TextField tfCircunscripcion = new TextField();

	public TextField getTfCircunscripcion() {
		return tfCircunscripcion;
	}

	public void setTfCircunscripcion(TextField tfCircunscripcion) {
		this.tfCircunscripcion = tfCircunscripcion;
	}

	private TextField tfDistrito = new TextField();

	public TextField getTfDistrito() {
		return tfDistrito;
	}

	public void setTfDistrito(TextField tfDistrito) {
		this.tfDistrito = tfDistrito;
	}

	private TextField tfSubDistrito = new TextField();

	public TextField getTfSubDistrito() {
		return tfSubDistrito;
	}

	public void setTfSubDistrito(TextField tfSubDistrito) {
		this.tfSubDistrito = tfSubDistrito;
	}

	private TextField tfSeccionPoligono = new TextField();

	public TextField getTfSeccionPoligono() {
		return tfSeccionPoligono;
	}

	public void setTfSeccionPoligono(TextField tfSeccionPoligono) {
		this.tfSeccionPoligono = tfSeccionPoligono;
	}

	private TextField tfManzana = new TextField();

	public TextField getTfManzana() {
		return tfManzana;
	}

	public void setTfManzana(TextField tfManzana) {
		this.tfManzana = tfManzana;
	}

	private TextField tfQuinta = new TextField();

	public TextField getTfQuinta() {
		return tfQuinta;
	}

	public void setTfQuinta(TextField tfQuinta) {
		this.tfQuinta = tfQuinta;
	}

	private TextField tfChacraFraccion = new TextField();

	public TextField getTfChacraFraccion() {
		return tfChacraFraccion;
	}

	public void setTfChacraFraccion(TextField tfChacraFraccion) {
		this.tfChacraFraccion = tfChacraFraccion;
	}

	private TextField tfLote = new TextField();

	public TextField getTfLote() {
		return tfLote;
	}

	public void setTfLote(TextField tfLote) {
		this.tfLote = tfLote;
	}

	private TextField tfNroRegistro = new TextField();

	public TextField getTfNroRegistro() {
		return tfNroRegistro;
	}

	public void setTfNroRegistro(TextField tf) {
		this.tfNroRegistro = tf;
	}

	private TextField tfNroMatricula = new TextField();

	public TextField getTfNroMatricula() {
		return tfNroMatricula;
	}

	public void setTfNroMatricula(TextField tf) {
		this.tfNroMatricula = tf;
	}

	private TextField tfNroPlanoMensura = new TextField();

	public TextField getTfNroPlanoMensura() {
		return tfNroPlanoMensura;
	}

	public void setTfNroPlanoMensura(TextField tfNroPlanoMensura) {
		this.tfNroPlanoMensura = tfNroPlanoMensura;
	}

	private TextField tfFolioPlanoMensura = new TextField();

	public TextField getTfFolioPlanoMensura() {
		return tfFolioPlanoMensura;
	}

	public void setTfFolioPlanoMensura(TextField tfFolioPlanoMensura) {
		this.tfFolioPlanoMensura = tfFolioPlanoMensura;
	}

	public TextField tfTomoPlanoMensura = new TextField();

	public TextField getTfTomoPlanoMensura() {
		return tfTomoPlanoMensura;
	}

	public void setTfTomoPlanoMensura(TextField tfTomoPlanoMensura) {
		this.tfTomoPlanoMensura = tfTomoPlanoMensura;
	}

	private TextField tfNroExpedientePlanoMensura = new TextField();

	public TextField getTfNroExpedientePlanoMensura() {
		return tfNroExpedientePlanoMensura;
	}

	public void setTfNroExpedientePlanoMensura(TextField tfNroExpedientePlanoMensura) {
		this.tfNroExpedientePlanoMensura = tfNroExpedientePlanoMensura;
	}

	private TextField tfNroRegistroPlanoMensura = new TextField();

	public TextField getTfNroRegistroPlanoMensura() {
		return tfNroRegistroPlanoMensura;
	}

	public void setTfNroRegistroPlanoMensura(TextField tfNroRegistroPlanoMensura) {
		this.tfNroRegistroPlanoMensura = tfNroRegistroPlanoMensura;
	}

	private TextField tfFechaPlanoMensura = new TextField();

	public TextField getTfFechaPlanoMensura() {
		return tfFechaPlanoMensura;
	}

	public void setTfFechaPlanoMensura(TextField tfFechaPlanoMensura) {
		this.tfFechaPlanoMensura = tfFechaPlanoMensura;
	}

	private MessageGroup messageGroup1 = new MessageGroup();

	public MessageGroup getMessageGroup1() {
		return messageGroup1;
	}

	public void setMessageGroup1(MessageGroup mg) {
		this.messageGroup1 = mg;
	}

	private MessageGroup messageGroup2 = new MessageGroup();

	public MessageGroup getMessageGroup2() {
		return messageGroup2;
	}

	public void setMessageGroup2(MessageGroup mg) {
		this.messageGroup2 = mg;
	}

	private RadioButtonGroup rbgTipoPlanoMensura = new RadioButtonGroup();

	public RadioButtonGroup getRbgTipoPlanoMensura() {
		return rbgTipoPlanoMensura;
	}

	public void setRbgTipoPlanoMensura(RadioButtonGroup rbgTipoPlanoMensura) {
		this.rbgTipoPlanoMensura = rbgTipoPlanoMensura;
	}

	private SingleSelectOptionsList rbgTipoPlanoMensuraOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getRbgTipoPlanoMensuraOptions() {
		return rbgTipoPlanoMensuraOptions;
	}

	public void setRbgTipoPlanoMensuraOptions(SingleSelectOptionsList rbgTipoPlanoMensuraOptions) {
		this.rbgTipoPlanoMensuraOptions = rbgTipoPlanoMensuraOptions;
	}

	private RadioButtonGroup rbgEstadoPlanoMensura = new RadioButtonGroup();

	public RadioButtonGroup getRbgEstadoPlanoMensura() {
		return rbgEstadoPlanoMensura;
	}

	public void setRbgEstadoPlanoMensura(RadioButtonGroup rbgEstadoPlanoMensura) {
		this.rbgEstadoPlanoMensura = rbgEstadoPlanoMensura;
	}

	private SingleSelectOptionsList rbgEstadoPlanoMensuraOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getRbgEstadoPlanoMensuraOptions() {
		return rbgEstadoPlanoMensuraOptions;
	}

	public void setRbgEstadoPlanoMensuraOptions(SingleSelectOptionsList rbgEstadoPlanoMensuraOptions) {
		this.rbgEstadoPlanoMensuraOptions = rbgEstadoPlanoMensuraOptions;
	}

	private RadioButtonGroup rbgClasificacionSegunPlanta = new RadioButtonGroup();

	public RadioButtonGroup getRbgClasificacionSegunPlanta() {
		return rbgClasificacionSegunPlanta;
	}

	public void setRbgClasificacionSegunPlanta(RadioButtonGroup rbgClasificacionSegunPlanta) {
		this.rbgClasificacionSegunPlanta = rbgClasificacionSegunPlanta;
	}

	private SingleSelectOptionsList rbgClasificacionSegunPlantaDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddAfectacionesExplicitasDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddAfectacionesNoExplicitasDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddRestriccionesAlDominioExplicitasDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddRestriccionesAlDominioNoExplicitasDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddRuralDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddComercialDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddEquipamientoDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddResidencialDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddVariosDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddTipoParcelaDefaultOptions = new SingleSelectOptionsList();
	private SingleSelectOptionsList ddClasificacionSegunPlantaUrbanaDefaultOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdClasificacionSegunPlantaUrbanaDefaultOptions() {
		return ddClasificacionSegunPlantaUrbanaDefaultOptions;
	}

	public void setDdClasificacionSegunPlantaUrbanaDefaultOptions(SingleSelectOptionsList ddClasificacionSegunPlantaUrbanaDefaultOptions) {
		this.ddClasificacionSegunPlantaUrbanaDefaultOptions = ddClasificacionSegunPlantaUrbanaDefaultOptions;
	}

	public SingleSelectOptionsList getDdTipoParcelaDefaultOptions() {
		return ddTipoParcelaDefaultOptions;
	}

	public void setDdTipoParcelaDefaultOptions(SingleSelectOptionsList ddTipoParcelaDefaultOptions) {
		this.ddTipoParcelaDefaultOptions = ddTipoParcelaDefaultOptions;
	}

	public SingleSelectOptionsList getDdResidencialDefaultOptions() {
		return ddResidencialDefaultOptions;
	}

	public void setDdResidencialDefaultOptions(SingleSelectOptionsList ddResidencialDefaultOptions) {
		this.ddResidencialDefaultOptions = ddResidencialDefaultOptions;
	}

	public SingleSelectOptionsList getDdVariosDefaultOptions() {
		return ddVariosDefaultOptions;
	}

	public void setDdVariosDefaultOptions(SingleSelectOptionsList ddVariosDefaultOptions) {
		this.ddVariosDefaultOptions = ddVariosDefaultOptions;
	}

	public SingleSelectOptionsList getDdComercialDefaultOptions() {
		return ddComercialDefaultOptions;
	}

	public void setDdComercialDefaultOptions(SingleSelectOptionsList ddComercialDefaultOptions) {
		this.ddComercialDefaultOptions = ddComercialDefaultOptions;
	}

	public SingleSelectOptionsList getDdEquipamientoDefaultOptions() {
		return ddEquipamientoDefaultOptions;
	}

	public void setDdEquipamientoDefaultOptions(SingleSelectOptionsList ddEquipamientoDefaultOptions) {
		this.ddEquipamientoDefaultOptions = ddEquipamientoDefaultOptions;
	}

	public SingleSelectOptionsList getDdRuralDefaultOptions() {
		return ddRuralDefaultOptions;
	}

	public void setDdRuralDefaultOptions(SingleSelectOptionsList ddRuralDefaultOptions) {
		this.ddRuralDefaultOptions = ddRuralDefaultOptions;
	}

	public SingleSelectOptionsList getDdAfectacionesNoExplicitasDefaultOptions() {
		return ddAfectacionesNoExplicitasDefaultOptions;
	}

	public void setDdAfectacionesNoExplicitasDefaultOptions(SingleSelectOptionsList ddAfectacionesNoExplicitasDefaultOptions) {
		this.ddAfectacionesNoExplicitasDefaultOptions = ddAfectacionesNoExplicitasDefaultOptions;
	}

	public SingleSelectOptionsList getDdAfectacionesExplicitasDefaultOptions() {
		return ddAfectacionesExplicitasDefaultOptions;
	}

	public void setDdAfectacionesExplicitasDefaultOptions(SingleSelectOptionsList ddAfectacionesExplicitasDefaultOptions) {
		this.ddAfectacionesExplicitasDefaultOptions = ddAfectacionesExplicitasDefaultOptions;
	}

	public SingleSelectOptionsList getRbgClasificacionSegunPlantaDefaultOptions() {
		return rbgClasificacionSegunPlantaDefaultOptions;
	}

	public void setRbgClasificacionSegunPlantaDefaultOptions(SingleSelectOptionsList rbgClasificacionSegunPlantaDefaultOptions) {
		this.rbgClasificacionSegunPlantaDefaultOptions = rbgClasificacionSegunPlantaDefaultOptions;
	}

	private PageSeparator psLineaHorizontal = new PageSeparator();

	public PageSeparator getPsLineaHorizontal() {
		return psLineaHorizontal;
	}

	public void setPsLineaHorizontal(PageSeparator psLineaHorizontal) {
		this.psLineaHorizontal = psLineaHorizontal;
	}

	private DropDown ddAfectacionesExplicitas = new DropDown();
	private DropDown ddAfectacionesNoExplicitas = new DropDown();
	private DropDown ddRestriccionesAlDominioExplicitas = new DropDown();
	private DropDown ddTipoParcela = new DropDown();
	private DropDown ddClasificacionSegunPlantaUrbana = new DropDown();
	private DropDown ddTipoTransaccion = new DropDown();

	public DropDown getDdTipoTransaccion() {
		return ddTipoTransaccion;
	}

	public void setDdTipoTransaccion(DropDown ddTipoTransaccion) {
		this.ddTipoTransaccion = ddTipoTransaccion;
	}

	private SingleSelectOptionsList ddTipoTransaccionOptions = new SingleSelectOptionsList();

	public SingleSelectOptionsList getDdTipoTransaccionOptions() {
		return ddTipoTransaccionOptions;
	}

	public void setDdTipoTransaccionOptions(SingleSelectOptionsList ddTipoTransaccionOptions) {
		this.ddTipoTransaccionOptions = ddTipoTransaccionOptions;
	}

	public DropDown getDdClasificacionSegunPlantaUrbana() {
		return ddClasificacionSegunPlantaUrbana;
	}

	public void setDdClasificacionSegunPlantaUrbana(DropDown ddClasificacionSegunPlantaUrbana) {
		this.ddClasificacionSegunPlantaUrbana = ddClasificacionSegunPlantaUrbana;
	}

	public DropDown getDdTipoParcela() {
		return ddTipoParcela;
	}

	public void setDdTipoParcela(DropDown ddTipoParcela) {
		this.ddTipoParcela = ddTipoParcela;
	}

	public DropDown getDdRestriccionesAlDominioExplicitas() {
		return ddRestriccionesAlDominioExplicitas;
	}

	public void setDdRestriccionesAlDominioExplicitas(DropDown ddRestriccionesAlDominioExplicitas) {
		this.ddRestriccionesAlDominioExplicitas = ddRestriccionesAlDominioExplicitas;
	}

	public SingleSelectOptionsList getDdRestriccionesAlDominioExplicitasDefaultOptions() {
		return ddRestriccionesAlDominioExplicitasDefaultOptions;
	}

	public void setDdRestriccionesAlDominioExplicitasDefaultOptions(SingleSelectOptionsList ddRestriccionesAlDominioExplicitasDefaultOptions) {
		this.ddRestriccionesAlDominioExplicitasDefaultOptions = ddRestriccionesAlDominioExplicitasDefaultOptions;
	}

	public DropDown getDdRestriccionesAlDominioNoExplicitas() {
		return ddRestriccionesAlDominioNoExplicitas;
	}

	public void setDdRestriccionesAlDominioNoExplicitas(DropDown ddRestriccionesAlDominioNoExplicitas) {
		this.ddRestriccionesAlDominioNoExplicitas = ddRestriccionesAlDominioNoExplicitas;
	}

	public SingleSelectOptionsList getDdRestriccionesAlDominioNoExplicitasDefaultOptions() {
		return ddRestriccionesAlDominioNoExplicitasDefaultOptions;
	}

	public void setDdRestriccionesAlDominioNoExplicitasDefaultOptions(SingleSelectOptionsList ddRestriccionesAlDominioNoExplicitasDefaultOptions) {
		this.ddRestriccionesAlDominioNoExplicitasDefaultOptions = ddRestriccionesAlDominioNoExplicitasDefaultOptions;
	}

	private DropDown ddRestriccionesAlDominioNoExplicitas = new DropDown();
	private DropDown ddRural = new DropDown();
	private DropDown ddComercial = new DropDown();
	private DropDown ddEquipamiento = new DropDown();
	private DropDown ddResidencial = new DropDown();
	private DropDown ddVarios = new DropDown();

	public DropDown getDdResidencial() {
		return ddResidencial;
	}

	public void setDdResidencial(DropDown ddResidencial) {
		this.ddResidencial = ddResidencial;
	}

	public DropDown getDdVarios() {
		return ddVarios;
	}

	public void setDdVarios(DropDown ddVarios) {
		this.ddVarios = ddVarios;
	}

	public DropDown getDdComercial() {
		return ddComercial;
	}

	public void setDdComercial(DropDown ddComercial) {
		this.ddComercial = ddComercial;
	}

	public DropDown getDdEquipamiento() {
		return ddEquipamiento;
	}

	public void setDdEquipamiento(DropDown ddEquipamiento) {
		this.ddEquipamiento = ddEquipamiento;
	}

	public DropDown getDdRural() {
		return ddRural;
	}

	public void setDdRural(DropDown ddRural) {
		this.ddRural = ddRural;
	}

	public DropDown getDdAfectacionesNoExplicitas() {
		return ddAfectacionesNoExplicitas;
	}

	public void setDdAfectacionesNoExplicitas(DropDown ddAfectacionesNoExplicitas) {
		this.ddAfectacionesNoExplicitas = ddAfectacionesNoExplicitas;
	}

	public DropDown getDdAfectacionesExplicitas() {
		return ddAfectacionesExplicitas;
	}

	public void setDdAfectacionesExplicitas(DropDown ddAfectacionesExplicitas) {
		this.ddAfectacionesExplicitas = ddAfectacionesExplicitas;
	}

	private Checkbox chbClasificacionRegadio = new Checkbox();
	private Checkbox chbClasificacionSecano = new Checkbox();

	public Checkbox getChbClasificacionRegadio() {
		return chbClasificacionRegadio;
	}

	public void setChbClasificacionRegadio(Checkbox chbClasificacionRegadio) {
		this.chbClasificacionRegadio = chbClasificacionRegadio;
	}

	public Checkbox getChbClasificacionSecano() {
		return chbClasificacionSecano;
	}

	public void setChbClasificacionSecano(Checkbox chbClasificacionSecano) {
		this.chbClasificacionSecano = chbClasificacionSecano;
	}

	private StaticText staticText1 = new StaticText();

	public StaticText getStaticText1() {
		return staticText1;
	}

	public void setStaticText1(StaticText st) {
		this.staticText1 = st;
	}

	private final StaticText staticText25 = new StaticText();

	public StaticText getStaticText25() {
		return staticText1;
	}

	public void setStaticText25(StaticText st) {
		this.staticText1 = st;
	}

	private StaticText staticText26 = new StaticText();
	private StaticText staticText27 = new StaticText();

	public StaticText getStaticText26() {
		return staticText26;
	}

	public void setStaticText26(StaticText staticText26) {
		this.staticText26 = staticText26;
	}

	public StaticText getStaticText27() {
		return staticText27;
	}

	public void setStaticText27(StaticText staticText27) {
		this.staticText27 = staticText27;
	}

	private Table table1 = new Table();

	public Table getTable1() {
		return table1;
	}

	public void setTable1(Table t) {
		this.table1 = t;
	}

	private Table table2 = new Table();

	public Table getTable2() {
		return table2;
	}

	public void setTable2(Table t) {
		this.table2 = t;
	}

	private Table tableObligaciones = new Table();

	public Table getTableObligaciones() {
		return tableObligaciones;
	}

	public void setTableObligaciones(Table tableObligaciones) {
		this.tableObligaciones = tableObligaciones;
	}

	private Table table3 = new Table();

	public Table getTable3() {
		return table3;
	}

	public void setTable3(Table t) {
		this.table3 = t;
	}

	private Table table4 = new Table();

	public Table getTable4() {
		return table4;
	}

	public void setTable4(Table t) {
		this.table4 = t;
	}

	private Table table5 = new Table();

	public Table getTable5() {
		return table5;
	}

	public void setTable5(Table table5) {
		this.table5 = table5;
	}

	private TableRowGroup tableRowGroup1 = new TableRowGroup();

	public TableRowGroup getTableRowGroup1() {
		return tableRowGroup1;
	}

	public void setTableRowGroup1(TableRowGroup trg) {
		this.tableRowGroup1 = trg;
	}

	private TableRowGroup tableRowGroup2 = new TableRowGroup();

	public TableRowGroup getTableRowGroup2() {
		return tableRowGroup2;
	}

	public void setTableRowGroup2(TableRowGroup trg) {
		this.tableRowGroup2 = trg;
	}

	private TableRowGroup tableRowObligacion = new TableRowGroup();

	public TableRowGroup getTableRowObligacion() {
		return tableRowObligacion;
	}

	public void setTableRowObligacion(TableRowGroup tableRowObligacion) {
		this.tableRowObligacion = tableRowObligacion;
	}

	private TableRowGroup tableRowGroup3 = new TableRowGroup();

	public TableRowGroup getTableRowGroup3() {
		return tableRowGroup3;
	}

	public void setTableRowGroup3(TableRowGroup trg) {
		this.tableRowGroup3 = trg;
	}

	private TableRowGroup tableRowGroup4 = new TableRowGroup();

	public TableRowGroup getTableRowGroup4() {
		return tableRowGroup4;
	}

	public void setTableRowGroup4(TableRowGroup trg) {
		this.tableRowGroup4 = trg;
	}

	private TableRowGroup tableRowGroup5 = new TableRowGroup();

	public TableRowGroup getTableRowGroup5() {
		return tableRowGroup5;
	}

	public void setTableRowGroup5(TableRowGroup tableRowGroup5) {
		this.tableRowGroup5 = tableRowGroup5;
	}

	private TableRowGroup tableRowGroup6 = new TableRowGroup();

	public TableRowGroup getTableRowGroup6() {
		return tableRowGroup6;
	}

	public void setTableRowGroup6(TableRowGroup tableRowGroup6) {
		this.tableRowGroup6 = tableRowGroup6;
	}

	private ObjectListDataProvider ldpZonas = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpZonas() {
		return ldpZonas;
	}

	public void setLdpZonas(ObjectListDataProvider ldpZonas) {
		this.ldpZonas = ldpZonas;
	}

	private ObjectListDataProvider ldpSubdivisiones = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpSubdivisiones() {
		return ldpSubdivisiones;
	}

	public void setLdpSubdivisiones(ObjectListDataProvider ldpSubdivisiones) {
		this.ldpSubdivisiones = ldpSubdivisiones;
	}

	private ObjectListDataProvider ldpObligaciones = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpObligaciones() {
		return ldpObligaciones;
	}

	public void setLdpObligaciones(ObjectListDataProvider ldpObligaciones) {
		this.ldpObligaciones = ldpObligaciones;
	}

	private ObjectListDataProvider ldpVolanteCatastralParcela = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpVolanteCatastralParcela() {
		return ldpVolanteCatastralParcela;
	}

	public void setLdpVolanteCatastralParcela(ObjectListDataProvider ldpVolanteCatastralParcela) {
		this.ldpVolanteCatastralParcela = ldpVolanteCatastralParcela;
	}

	private ObjectListDataProvider ldpRegistroMejoraParcela = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpRegistroMejoraParcela() {
		return ldpRegistroMejoraParcela;
	}

	public void setLdpRegistroMejoraParcela(ObjectListDataProvider ldpRegistroMejoraParcela) {
		this.ldpRegistroMejoraParcela = ldpRegistroMejoraParcela;
	}

	private ObjectListDataProvider ldpParcelaCuadras = new ObjectListDataProvider();

	public ObjectListDataProvider getLdpParcelaCuadras() {
		return ldpParcelaCuadras;
	}

	public void setLdpParcelaCuadras(ObjectListDataProvider oldp) {
		this.ldpParcelaCuadras = oldp;
	}

	private TableColumn tableColumnObligacion1 = new TableColumn();
	private TableColumn tableColumnObligacion2 = new TableColumn();
	private TableColumn tableColumnObligacion3 = new TableColumn();
	private StaticText staticTextObligacion1 = new StaticText();
	private StaticText staticTextObligacion2 = new StaticText();
	private StaticText staticTextObligacion3 = new StaticText();

	public TableColumn getTableColumnObligacion3() {
		return tableColumnObligacion3;
	}

	public void setTableColumnObligacion3(TableColumn tableColumnObligacion3) {
		this.tableColumnObligacion3 = tableColumnObligacion3;
	}

	public StaticText getStaticTextObligacion3() {
		return staticTextObligacion3;
	}

	public void setStaticTextObligacion3(StaticText staticTextObligacion3) {
		this.staticTextObligacion3 = staticTextObligacion3;
	}

	public TableColumn getTableColumnObligacion1() {
		return tableColumnObligacion1;
	}

	public void setTableColumnObligacion1(TableColumn tableColumnObligacion1) {
		this.tableColumnObligacion1 = tableColumnObligacion1;
	}

	public TableColumn getTableColumnObligacion2() {
		return tableColumnObligacion2;
	}

	public void setTableColumnObligacion2(TableColumn tableColumnObligacion2) {
		this.tableColumnObligacion2 = tableColumnObligacion2;
	}

	public StaticText getStaticTextObligacion1() {
		return staticTextObligacion1;
	}

	public void setStaticTextObligacion1(StaticText staticTextObligacion1) {
		this.staticTextObligacion1 = staticTextObligacion1;
	}

	public StaticText getStaticTextObligacion2() {
		return staticTextObligacion2;
	}

	public void setStaticTextObligacion2(StaticText staticTextObligacion2) {
		this.staticTextObligacion2 = staticTextObligacion2;
	}

	private TableColumn tableColumn1 = new TableColumn();

	public TableColumn getTableColumn1() {
		return tableColumn1;
	}

	public void setTableColumn1(TableColumn tc) {
		this.tableColumn1 = tc;
	}

	private RadioButton radioButton1 = new RadioButton();

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(RadioButton rb) {
		this.radioButton1 = rb;
	}

	private RadioButton radioButton2 = new RadioButton();

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButton2(RadioButton rb) {
		this.radioButton2 = rb;
	}

	private RadioButton radioButton3 = new RadioButton();

	public RadioButton getRadioButton3() {
		return radioButton3;
	}

	public void setRadioButton3(RadioButton rb) {
		this.radioButton3 = rb;
	}

	private RadioButton radioButton4 = new RadioButton();

	public RadioButton getRadioButton4() {
		return radioButton4;
	}

	public void setRadioButton4(RadioButton rb) {
		this.radioButton4 = rb;
	}

	private RadioButton radioButton5 = new RadioButton();

	public RadioButton getRadioButton5() {
		return radioButton5;
	}

	public void setRadioButton5(RadioButton radioButton5) {
		this.radioButton5 = radioButton5;
	}

	private RadioButton radioButton6 = new RadioButton();

	public RadioButton getRadioButton6() {
		return radioButton6;
	}

	public void setRadioButton6(RadioButton radioButton6) {
		this.radioButton6 = radioButton6;
	}

	private RadioButton radioButtonEncargado = new RadioButton();

	public RadioButton getRadioButtonEncargado() {
		return radioButtonEncargado;
	}

	public void setRadioButtonEncargado(RadioButton radioButtonEncargado) {
		this.radioButtonEncargado = radioButtonEncargado;
	}

	private TableColumn tableColumn2 = new TableColumn();

	public TableColumn getTableColumn2() {
		return tableColumn2;
	}

	public void setTableColumn2(TableColumn tc) {
		this.tableColumn2 = tc;
	}

	private StaticText staticText2 = new StaticText();
	private StaticText staticText18 = new StaticText();
	private StaticText staticText19 = new StaticText();
	private StaticText staticText20 = new StaticText();

	public StaticText getStaticText20() {
		return staticText20;
	}

	public void setStaticText20(StaticText staticText20) {
		this.staticText20 = staticText20;
	}

	public StaticText getStaticText19() {
		return staticText19;
	}

	public void setStaticText19(StaticText staticText19) {
		this.staticText19 = staticText19;
	}

	public StaticText getStaticText18() {
		return staticText18;
	}

	public void setStaticText18(StaticText staticText18) {
		this.staticText18 = staticText18;
	}

	public StaticText getStaticText2() {
		return staticText2;
	}

	public void setStaticText2(StaticText st) {
		this.staticText2 = st;
	}

	private TableColumn tableColumn3 = new TableColumn();

	public TableColumn getTableColumn3() {
		return tableColumn3;
	}

	public void setTableColumn3(TableColumn tc) {
		this.tableColumn3 = tc;
	}

	private TableColumn tableColumn4 = new TableColumn();

	public TableColumn getTableColumn4() {
		return tableColumn4;
	}

	public void setTableColumn4(TableColumn tc) {
		this.tableColumn4 = tc;
	}

	private TableColumn tableColumn5 = new TableColumn();

	public TableColumn getTableColumn5() {
		return tableColumn5;
	}

	public void setTableColumn5(TableColumn tc) {
		this.tableColumn5 = tc;
	}

	private TableColumn tableColumn6 = new TableColumn();

	public TableColumn getTableColumn6() {
		return tableColumn6;
	}

	public void setTableColumn6(TableColumn tc) {
		this.tableColumn6 = tc;
	}

	private TableColumn tableColumn7 = new TableColumn();

	public TableColumn getTableColumn7() {
		return tableColumn7;
	}

	public void setTableColumn7(TableColumn tc) {
		this.tableColumn7 = tc;
	}

	private TableColumn tableColumn8 = new TableColumn();

	public TableColumn getTableColumn8() {
		return tableColumn8;
	}

	public void setTableColumn8(TableColumn tc) {
		this.tableColumn8 = tc;
	}

	private TableColumn tableColumn9 = new TableColumn();

	public TableColumn getTableColumn9() {
		return tableColumn9;
	}

	public void setTableColumn9(TableColumn tc) {
		this.tableColumn9 = tc;
	}

	private TableColumn tableColumn10 = new TableColumn();

	public TableColumn getTableColumn10() {
		return tableColumn10;
	}

	public void setTableColumn10(TableColumn tc) {
		this.tableColumn10 = tc;
	}

	private TableColumn tableColumn11 = new TableColumn();

	public TableColumn getTableColumn11() {
		return tableColumn11;
	}

	public void setTableColumn11(TableColumn tc) {
		this.tableColumn11 = tc;
	}

	private TableColumn tableColumn12 = new TableColumn();
	private TableColumn tableColumn13 = new TableColumn();
	private TableColumn tableColumn14 = new TableColumn();
	private TableColumn tableColumn15 = new TableColumn();
	private TableColumn tableColumn16 = new TableColumn();
	private TableColumn tableColumn17 = new TableColumn();
	private TableColumn tableColumn18 = new TableColumn();
	private TableColumn tableColumn19 = new TableColumn();
	private TableColumn tableColumn20 = new TableColumn();
	private TableColumn tableColumn21 = new TableColumn();
	private TableColumn tableColumn22 = new TableColumn();
	private TableColumn tableColumn23 = new TableColumn();
	private TableColumn tableColumn24 = new TableColumn();
	private TableColumn tableColumn25 = new TableColumn();

	public TableColumn getTableColumn24() {
		return tableColumn24;
	}

	public void setTableColumn24(TableColumn tableColumn24) {
		this.tableColumn24 = tableColumn24;
	}

	public TableColumn getTableColumn25() {
		return tableColumn25;
	}

	public void setTableColumn25(TableColumn tableColumn25) {
		this.tableColumn25 = tableColumn25;
	}

	public TableColumn getTableColumn21() {
		return tableColumn21;
	}

	public void setTableColumn21(TableColumn tableColumn21) {
		this.tableColumn21 = tableColumn21;
	}

	public TableColumn getTableColumn22() {
		return tableColumn22;
	}

	public void setTableColumn22(TableColumn tableColumn22) {
		this.tableColumn22 = tableColumn22;
	}

	public TableColumn getTableColumn23() {
		return tableColumn23;
	}

	public void setTableColumn23(TableColumn tableColumn23) {
		this.tableColumn23 = tableColumn23;
	}

	public TableColumn getTableColumn18() {
		return tableColumn18;
	}

	public void setTableColumn18(TableColumn tableColumn18) {
		this.tableColumn18 = tableColumn18;
	}

	public TableColumn getTableColumn19() {
		return tableColumn19;
	}

	public void setTableColumn19(TableColumn tableColumn19) {
		this.tableColumn19 = tableColumn19;
	}

	public TableColumn getTableColumn20() {
		return tableColumn20;
	}

	public void setTableColumn20(TableColumn tableColumn20) {
		this.tableColumn20 = tableColumn20;
	}

	public TableColumn getTableColumn17() {
		return tableColumn17;
	}

	public void setTableColumn17(TableColumn tableColumn17) {
		this.tableColumn17 = tableColumn17;
	}

	public TableColumn getTableColumn13() {
		return tableColumn13;
	}

	public TableColumn getTableColumn16() {
		return tableColumn16;
	}

	public void setTableColumn16(TableColumn tableColumn16) {
		this.tableColumn16 = tableColumn16;
	}

	public void setTableColumn13(TableColumn tableColumn13) {
		this.tableColumn13 = tableColumn13;
	}

	public TableColumn getTableColumn14() {
		return tableColumn14;
	}

	public void setTableColumn14(TableColumn tableColumn14) {
		this.tableColumn14 = tableColumn14;
	}

	public TableColumn getTableColumn15() {
		return tableColumn15;
	}

	public void setTableColumn15(TableColumn tableColumn15) {
		this.tableColumn15 = tableColumn15;
	}

	public TableColumn getTableColumn12() {
		return tableColumn12;
	}

	public void setTableColumn12(TableColumn tc) {
		this.tableColumn12 = tc;
	}

	private TextField textField1 = new TextField();

	public TextField getTextField1() {
		return textField1;
	}

	public void setTextField1(TextField tf) {
		this.textField1 = tf;
	}

	private TextField tfTitulo = new TextField();

	public TextField getTfTitulo() {
		return tfTitulo;
	}

	public void setTfTitulo(TextField tfTitulo) {
		this.tfTitulo = tfTitulo;
	}

	private TextField tfFechaInscripcion = new TextField();

	public TextField getTfFechaInscripcion() {
		return tfFechaInscripcion;
	}

	public void setTfFechaInscripcion(TextField tfFechaInscripcion) {
		this.tfFechaInscripcion = tfFechaInscripcion;
	}

	private TextField tfTomo = new TextField();

	public TextField getTfTomo() {
		return tfTomo;
	}

	public void setTfTomo(TextField tfTomo) {
		this.tfTomo = tfTomo;
	}

	private TextField tfFolio = new TextField();
	private TextField tfAsiento = new TextField();
	private TextField tfArea = new TextField();

	public TextField getTfArea() {
		return tfArea;
	}

	public void setTfArea(TextField tfArea) {
		this.tfArea = tfArea;
	}

	public TextField getTfAsiento() {
		return tfAsiento;
	}

	public void setTfAsiento(TextField tfAsiento) {
		this.tfAsiento = tfAsiento;
	}

	public TextField getTfFolio() {
		return tfFolio;
	}

	public void setTfFolio(TextField tfFolio) {
		this.tfFolio = tfFolio;
	}

	private Label label7 = new Label();

	public Label getLabel7() {
		return label7;
	}

	public void setLabel7(Label l) {
		this.label7 = l;
	}

	private Label label14 = new Label();

	public Label getLabel14() {
		return label14;
	}

	public void setLabel14(Label l) {
		this.label14 = l;
	}

	private TextField tfManzanaCuadra = new TextField();

	public TextField getTfManzanaCuadra() {
		return tfManzanaCuadra;
	}

	public void setTfManzanaCuadra(TextField tf) {
		this.tfManzanaCuadra = tf;
	}

	private Button btnAgregarPersonaFisica = new Button();

	public Button getBtnAgregarPersonaFisica() {
		return btnAgregarPersonaFisica;
	}

	public void setBtnAgregarPersonaFisica(Button b) {
		this.btnAgregarPersonaFisica = b;
	}

	private Button btnAgregarPersonaJuridica = new Button();

	public Button getBtnAgregarPersonaJuridica() {
		return btnAgregarPersonaJuridica;
	}

	public void setBtnAgregarPersonaJuridica(Button b) {
		this.btnAgregarPersonaJuridica = b;
	}

	private Button btnAgregarZona = new Button();
	protected HtmlAjaxCommandButton btnQuitarZona = new HtmlAjaxCommandButton();

	public Button getBtnAgregarZona() {
		return btnAgregarZona;
	}

	public void setBtnAgregarZona(Button btnAgregarZona) {
		this.btnAgregarZona = btnAgregarZona;
	}

	public HtmlAjaxCommandButton getBtnQuitarZona() {
		return btnQuitarZona;
	}

	public void setBtnQuitarZona(HtmlAjaxCommandButton btnQuitarZona) {
		this.btnQuitarZona = btnQuitarZona;
	}

	protected HtmlAjaxCommandButton btnQuitar = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(HtmlAjaxCommandButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	protected HtmlAjaxCommandButton btnQuitarTodos = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnQuitarTodos() {
		return btnQuitarTodos;
	}

	public void setBtnQuitarTodos(HtmlAjaxCommandButton btnQuitarTodos) {
		this.btnQuitarTodos = btnQuitarTodos;
	}

	private Button btnSeleccionarManzana = new Button();

	public Button getBtnSeleccionarManzana() {
		return btnSeleccionarManzana;
	}

	public void setBtnSeleccionarManzana(Button b) {
		this.btnSeleccionarManzana = b;
	}

	private Label label25 = new Label();

	public Label getLabel25() {
		return label25;
	}

	public void setLabel25(Label l) {
		this.label25 = l;
	}

	private TextField tfAvaluoTerreno = new TextField();

	public TextField getTfAvaluoTerreno() {
		return tfAvaluoTerreno;
	}

	public void setTfAvaluoTerreno(TextField tf) {
		this.tfAvaluoTerreno = tf;
	}

	private Label label26 = new Label();

	public Label getLabel26() {
		return label26;
	}

	public void setLabel26(Label l) {
		this.label26 = l;
	}

	private TextField tfAvaluoMejoras = new TextField();

	public TextField getTfAvaluoMejoras() {
		return tfAvaluoMejoras;
	}

	public void setTfAvaluoMejoras(TextField tf) {
		this.tfAvaluoMejoras = tf;
	}

	private PanelGroup groupPanel1 = new PanelGroup();

	public PanelGroup getGroupPanel1() {
		return groupPanel1;
	}

	public void setGroupPanel1(PanelGroup pg) {
		this.groupPanel1 = pg;
	}

	private PanelGroup groupPanel2 = new PanelGroup();

	public PanelGroup getGroupPanel2() {
		return groupPanel2;
	}

	public void setGroupPanel2(PanelGroup pg) {
		this.groupPanel2 = pg;
	}

	private PanelGroup groupPanel3 = new PanelGroup();

	public PanelGroup getGroupPanel3() {
		return groupPanel3;
	}

	public void setGroupPanel3(PanelGroup pg) {
		this.groupPanel3 = pg;
	}

	private PanelGroup groupPanelObligaciones = new PanelGroup();

	public PanelGroup getGroupPanelObligaciones() {
		return groupPanelObligaciones;
	}

	public void setGroupPanelObligaciones(PanelGroup groupPanelObligaciones) {
		this.groupPanelObligaciones = groupPanelObligaciones;
	}

	private PanelGroup groupPanelVolanteCatastral = new PanelGroup();

	public PanelGroup getGroupPanelVolanteCatastral() {
		return groupPanelVolanteCatastral;
	}

	public void setGroupPanelVolanteCatastral(PanelGroup pg) {
		this.groupPanelVolanteCatastral = pg;
	}

	private PanelGroup groupPanelSubdivisionParcela = new PanelGroup();

	public PanelGroup getGroupPanelSubdivisionParcela() {
		return groupPanelSubdivisionParcela;
	}

	public void setGroupPanelSubdivisionParcela(PanelGroup pg) {
		this.groupPanelSubdivisionParcela = pg;
	}

	private PanelGroup groupPanel4 = new PanelGroup();

	public PanelGroup getGroupPanel4() {
		return groupPanel4;
	}

	public void setGroupPanel4(PanelGroup pg) {
		this.groupPanel4 = pg;
	}

	private PanelGroup groupPanel5 = new PanelGroup();

	public PanelGroup getGroupPanel5() {
		return groupPanel5;
	}

	public void setGroupPanel5(PanelGroup pg) {
		this.groupPanel5 = pg;
	}

	private PanelGroup groupPanel6 = new PanelGroup();

	public PanelGroup getGroupPanel6() {
		return groupPanel6;
	}

	public void setGroupPanel6(PanelGroup pg) {
		this.groupPanel6 = pg;
	}

	private PanelGroup groupPanelPropietarios = new PanelGroup();
	private PanelGroup groupPanelZonas = new PanelGroup();

	public PanelGroup getGroupPanelPropietarios() {
		return groupPanelPropietarios;
	}

	public void setGroupPanelPropietarios(PanelGroup groupPanelPropietarios) {
		this.groupPanelPropietarios = groupPanelPropietarios;
	}

	public PanelGroup getGroupPanelZonas() {
		return groupPanelZonas;
	}

	public void setGroupPanelZonas(PanelGroup groupPanelZonas) {
		this.groupPanelZonas = groupPanelZonas;
	}

	private StaticText staticText5 = new StaticText();
	private StaticText staticText6 = new StaticText();
	private StaticText staticText7 = new StaticText();
	private StaticText staticText8 = new StaticText();
	private StaticText staticText9 = new StaticText();
	private StaticText staticText10 = new StaticText();
	private StaticText staticText11 = new StaticText();
	private StaticText staticText12 = new StaticText();
	private StaticText staticText13 = new StaticText();
	private StaticText staticText14 = new StaticText();
	private StaticText staticText15 = new StaticText();
	private StaticText staticText16 = new StaticText();
	private StaticText staticText17 = new StaticText();
	private StaticText staticText21 = new StaticText();
	private StaticText staticText22 = new StaticText();
	private StaticText staticText23 = new StaticText();
	private StaticText staticText24 = new StaticText();

	public StaticText getStaticText24() {
		return staticText24;
	}

	public void setStaticText24(StaticText staticText24) {
		this.staticText24 = staticText24;
	}

	public StaticText getStaticText23() {
		return staticText23;
	}

	public void setStaticText23(StaticText staticText23) {
		this.staticText23 = staticText23;
	}

	public StaticText getStaticText22() {
		return staticText22;
	}

	public void setStaticText22(StaticText staticText22) {
		this.staticText22 = staticText22;
	}

	public StaticText getStaticText21() {
		return staticText21;
	}

	public void setStaticText21(StaticText staticText21) {
		this.staticText21 = staticText21;
	}

	public StaticText getStaticText17() {
		return staticText17;
	}

	public void setStaticText17(StaticText staticText17) {
		this.staticText17 = staticText17;
	}

	public StaticText getStaticText15() {
		return staticText15;
	}

	public void setStaticText15(StaticText staticText15) {
		this.staticText15 = staticText15;
	}

	public StaticText getStaticText16() {
		return staticText16;
	}

	public void setStaticText16(StaticText staticText16) {
		this.staticText16 = staticText16;
	}

	public StaticText getStaticText14() {
		return staticText14;
	}

	public void setStaticText14(StaticText staticText14) {
		this.staticText14 = staticText14;
	}

	public StaticText getStaticText12() {
		return staticText12;
	}

	public void setStaticText12(StaticText staticText12) {
		this.staticText12 = staticText12;
	}

	public StaticText getStaticText13() {
		return staticText13;
	}

	public void setStaticText13(StaticText staticText13) {
		this.staticText13 = staticText13;
	}

	public StaticText getStaticText10() {
		return staticText10;
	}

	public void setStaticText10(StaticText staticText10) {
		this.staticText10 = staticText10;
	}

	public StaticText getStaticText11() {
		return staticText11;
	}

	public void setStaticText11(StaticText staticText11) {
		this.staticText11 = staticText11;
	}

	public StaticText getStaticText9() {
		return staticText9;
	}

	public void setStaticText9(StaticText staticText9) {
		this.staticText9 = staticText9;
	}

	public StaticText getStaticText5() {
		return staticText5;
	}

	public void setStaticText5(StaticText staticText5) {
		this.staticText5 = staticText5;
	}

	public StaticText getStaticText6() {
		return staticText6;
	}

	public void setStaticText6(StaticText staticText6) {
		this.staticText6 = staticText6;
	}

	public StaticText getStaticText7() {
		return staticText7;
	}

	public void setStaticText7(StaticText staticText7) {
		this.staticText7 = staticText7;
	}

	public StaticText getStaticText8() {
		return staticText8;
	}

	public void setStaticText8(StaticText staticText8) {
		this.staticText8 = staticText8;
	}

	private StaticText staticText3 = new StaticText();

	public StaticText getStaticText3() {
		return staticText3;
	}

	public void setStaticText3(StaticText st) {
		this.staticText3 = st;
	}

	public Button getBtnAgregarRegMejora() {
		return btnAgregarRegMejora;
	}

	public void setBtnAgregarRegMejora(Button btnAgregarRegMejora) {
		this.btnAgregarRegMejora = btnAgregarRegMejora;
	}

	public HtmlAjaxCommandButton getBtnQuitarRegMejora() {
		return btnQuitarRegMejora;
	}

	public void setBtnQuitarRegMejora(HtmlAjaxCommandButton btnQuitarRegMejora) {
		this.btnQuitarRegMejora = btnQuitarRegMejora;
	}

	private Button btnAgregarRegMejora = new Button();
	private Button btnModificarRegMejora = new Button();
	protected HtmlAjaxCommandButton btnQuitarRegMejora = new HtmlAjaxCommandButton();
	private Button btnConsultarRegMejora = new Button();
	private Button btnAgregarVolante = new Button();
	private Button btnQuitarVolante = new Button();
	private Button btnImprimirVolante = new Button();

	public Button getBtnConsultarRegMejora() {
		return btnConsultarRegMejora;
	}

	public void setBtnConsultarRegMejora(Button btnConsultarRegMejora) {
		this.btnConsultarRegMejora = btnConsultarRegMejora;
	}

	public Button getBtnModificarRegMejora() {
		return btnModificarRegMejora;
	}

	public void setBtnModificarRegMejora(Button btnModificarRegMejora) {
		this.btnModificarRegMejora = btnModificarRegMejora;
	}

	public Button getBtnImprimirVolante() {
		return btnImprimirVolante;
	}

	public void setBtnImprimirVolante(Button btnImprimirVolante) {
		this.btnImprimirVolante = btnImprimirVolante;
	}

	public Button getBtnAgregarVolante() {
		return btnAgregarVolante;
	}

	public void setBtnAgregarVolante(Button btnAgregarVolante) {
		this.btnAgregarVolante = btnAgregarVolante;
	}

	public Button getBtnQuitarVolante() {
		return btnQuitarVolante;
	}

	public void setBtnQuitarVolante(Button btnQuitarVolante) {
		this.btnQuitarVolante = btnQuitarVolante;
	}

	private HtmlAjaxCommandButton btnLimpiarManzanaCuadra = new HtmlAjaxCommandButton();

	public HtmlAjaxCommandButton getBtnLimpiarManzanaCuadra() {
		return btnLimpiarManzanaCuadra;
	}

	public void setBtnLimpiarManzanaCuadra(HtmlAjaxCommandButton btnLimpiarManzanaCuadra) {
		this.btnLimpiarManzanaCuadra = btnLimpiarManzanaCuadra;
	}

	private StaticText staticText4 = new StaticText();

	public StaticText getStaticText4() {
		return staticText4;
	}

	public void setStaticText4(StaticText st) {
		this.staticText4 = st;
	}

	private Button btnLimpiarDomicilio = new Button();

	public Button getBtnLimpiarDomicilio() {
		return btnLimpiarDomicilio;
	}

	public void setBtnLimpiarDomicilio(Button b) {
		this.btnLimpiarDomicilio = b;
	}

	private PageSeparator pgSeparator = new PageSeparator();

	public PageSeparator getPgSeparator() {
		return pgSeparator;
	}

	public void setPgSeparator(PageSeparator pgSeparator) {
		this.pgSeparator = pgSeparator;
	}

	private PanelLayout pLayout = new PanelLayout();

	public PanelLayout getpLayout() {
		return pLayout;
	}

	public void setpLayout(PanelLayout pLayout) {
		this.pLayout = pLayout;
	}

	private Button btnLimpiarZona = new Button();

	public Button getBtnLimpiarZona() {
		return btnLimpiarZona;
	}

	public void setBtnLimpiarZona(Button b) {
		this.btnLimpiarZona = b;
	}

	private Button btnSeleccionarDomicilio = new Button();

	public Button getBtnSeleccionarDomicilio() {
		return btnSeleccionarDomicilio;
	}

	public void setBtnSeleccionarDomicilio(Button b) {
		this.btnSeleccionarDomicilio = b;
	}

	private Button btnSeleccionarZona = new Button();

	public Button getBtnSeleccionarZona() {
		return btnSeleccionarZona;
	}

	public void setBtnSeleccionarZona(Button b) {
		this.btnSeleccionarZona = b;
	}

	private StaticText stDomicilio = new StaticText();

	public StaticText getStDomicilio() {
		return stDomicilio;
	}

	public void setStDomicilio(StaticText st) {
		this.stDomicilio = st;
	}

	private TextField tfNumeroParcela = new TextField();

	public TextField getTfNumeroParcela() {
		return tfNumeroParcela;
	}

	public void setTfNumeroParcela(TextField tfNumeroParcela) {
		this.tfNumeroParcela = tfNumeroParcela;
	}

	private TextField tfZona = new TextField();
	private TextField tfNumeroCuenta = new TextField();
	private TextField tfNumeroSubCuenta = new TextField();

	public TextField getTfNumeroSubCuenta() {
		return tfNumeroSubCuenta;
	}

	public void setTfNumeroSubCuenta(TextField tfNumeroSubCuenta) {
		this.tfNumeroSubCuenta = tfNumeroSubCuenta;
	}

	public TextField getTfNumeroCuenta() {
		return tfNumeroCuenta;
	}

	public void setTfNumeroCuenta(TextField tfNumeroCuenta) {
		this.tfNumeroCuenta = tfNumeroCuenta;
	}

	public TextField getTfZona() {
		return tfZona;
	}

	public void setTfZona(TextField tf) {
		this.tfZona = tf;
	}

	private Label label19 = new Label();

	public Label getLabel19() {
		return label19;
	}

	public void setLabel19(Label l) {
		this.label19 = l;
	}

	private StaticText stPropietariosTemp = new StaticText();

	public StaticText getStPropietariosTemp() {
		return stPropietariosTemp;
	}

	public void setStPropietariosTemp(StaticText st) {
		this.stPropietariosTemp = st;
	}

	private StaticText stZonas = new StaticText();

	public StaticText getStZonas() {
		return stZonas;
	}

	public void setStZonas(StaticText stZonas) {
		this.stZonas = stZonas;
	}

	private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

	public DateTimeConverter getDateTimeConverter1() {
		return dateTimeConverter1;
	}

	public void setDateTimeConverter1(DateTimeConverter dtc) {
		this.dateTimeConverter1 = dtc;
	}
	
	/* TABLA PLANO CONSTRUCCION */
	
	private ObjectListDataProvider ldpPlanoConstruccion = new ObjectListDataProvider();
	
	Table tablePlanoConstruccion = new Table();
	TableRowGroup tableRowGroupPlanoConstrucion = new TableRowGroup();
	
	RadioButton radioButtonTablaPlano1 = new RadioButton();
	
	TableColumn tableColumnPlano1 = new TableColumn();
	TableColumn tableColumnPlano2 = new TableColumn();
	TableColumn tableColumnPlano3 = new TableColumn();
	TableColumn tableColumnPlano4 = new TableColumn();
	TableColumn tableColumnPlano5 = new TableColumn();
	TableColumn tableColumnPlano6 = new TableColumn();
	
	StaticText staticTextTablaPlanoConstruccion1 = new StaticText();
	StaticText staticTextTablaPlanoConstruccion2 = new StaticText();
	StaticText staticTextTablaPlanoConstruccion3 = new StaticText();
	StaticText staticTextTablaPlanoConstruccion4 = new StaticText();
	StaticText staticTextTablaPlanoConstruccion5 = new StaticText();
	
	PanelGroup groupPanelPlanoConstruccion = new PanelGroup();
	
	Button btnAgregarPlanoConstruccion = new Button();
	Button btnModificarPlanoConstruccion = new Button();
	Button btnConsultarPlanoConstruccion = new Button();
	HtmlAjaxCommandButton btnQuitarPlanoConstruccion = new HtmlAjaxCommandButton();
	
	private Object lastSelectedPlanoConstruccion = null;

	public Object getRBSelected7() {
		String sv = (String) radioButtonTablaPlano1.getSelectedValue();
		return sv.equals(lastSelectedPlanoConstruccion) ? sv : null;
	}

	public void setRBSelected7(Object selected) {
		if(selected != null) {
			lastSelectedPlanoConstruccion = selected;
		}
	}

	private List getListaDelCommunication6() {
		return this.getComunicationCatastroBean().getListaPlanoConstrucciones();
	}

	private void setListaDelCommunication6(List lista) {
		this.getComunicationCatastroBean().setListaPlanoConstrucciones(lista);
	}
	
	public String getCurrentRowPlanoConstruccion() {
		return tableRowGroupPlanoConstrucion.getRowKey().getRowId();
	}

	public void setCurrentRowPlanoConstruccion(int row) {
	}

	public ObjectListDataProvider getLdpPlanoConstruccion() {
		return ldpPlanoConstruccion;
	}

	public void setLdpPlanoConstruccion(ObjectListDataProvider ldpPlanoConstruccion) {
		this.ldpPlanoConstruccion = ldpPlanoConstruccion;
	}

	public Table getTablePlanoConstruccion() {
		return tablePlanoConstruccion;
	}

	public void setTablePlanoConstruccion(Table tablePlanoConstruccion) {
		this.tablePlanoConstruccion = tablePlanoConstruccion;
	}

	public TableRowGroup getTableRowGroupPlanoConstrucion() {
		return tableRowGroupPlanoConstrucion;
	}

	public void setTableRowGroupPlanoConstrucion(
			TableRowGroup tableRowGroupPlanoConstrucion) {
		this.tableRowGroupPlanoConstrucion = tableRowGroupPlanoConstrucion;
	}

	public RadioButton getRadioButtonTablaPlano1() {
		return radioButtonTablaPlano1;
	}

	public void setRadioButtonTablaPlano1(RadioButton radioButtonTablaPlano1) {
		this.radioButtonTablaPlano1 = radioButtonTablaPlano1;
	}

	public TableColumn getTableColumnPlano1() {
		return tableColumnPlano1;
	}

	public void setTableColumnPlano1(TableColumn tableColumnPlano1) {
		this.tableColumnPlano1 = tableColumnPlano1;
	}

	public TableColumn getTableColumnPlano2() {
		return tableColumnPlano2;
	}

	public void setTableColumnPlano2(TableColumn tableColumnPlano2) {
		this.tableColumnPlano2 = tableColumnPlano2;
	}

	public TableColumn getTableColumnPlano3() {
		return tableColumnPlano3;
	}

	public void setTableColumnPlano3(TableColumn tableColumnPlano3) {
		this.tableColumnPlano3 = tableColumnPlano3;
	}

	public TableColumn getTableColumnPlano4() {
		return tableColumnPlano4;
	}

	public void setTableColumnPlano4(TableColumn tableColumnPlano4) {
		this.tableColumnPlano4 = tableColumnPlano4;
	}

	public TableColumn getTableColumnPlano5() {
		return tableColumnPlano5;
	}

	public void setTableColumnPlano5(TableColumn tableColumnPlano5) {
		this.tableColumnPlano5 = tableColumnPlano5;
	}

	public StaticText getStaticTextTablaPlanoConstruccion1() {
		return staticTextTablaPlanoConstruccion1;
	}

	public void setStaticTextTablaPlanoConstruccion1(
			StaticText staticTextTablaPlanoConstruccion1) {
		this.staticTextTablaPlanoConstruccion1 = staticTextTablaPlanoConstruccion1;
	}

	public StaticText getStaticTextTablaPlanoConstruccion2() {
		return staticTextTablaPlanoConstruccion2;
	}

	public void setStaticTextTablaPlanoConstruccion2(
			StaticText staticTextTablaPlanoConstruccion2) {
		this.staticTextTablaPlanoConstruccion2 = staticTextTablaPlanoConstruccion2;
	}

	public StaticText getStaticTextTablaPlanoConstruccion3() {
		return staticTextTablaPlanoConstruccion3;
	}

	public void setStaticTextTablaPlanoConstruccion3(
			StaticText staticTextTablaPlanoConstruccion3) {
		this.staticTextTablaPlanoConstruccion3 = staticTextTablaPlanoConstruccion3;
	}

	public StaticText getStaticTextTablaPlanoConstruccion4() {
		return staticTextTablaPlanoConstruccion4;
	}

	public void setStaticTextTablaPlanoConstruccion4(
			StaticText staticTextTablaPlanoConstruccion4) {
		this.staticTextTablaPlanoConstruccion4 = staticTextTablaPlanoConstruccion4;
	}
	
	public TableColumn getTableColumnPlano6() {
		return tableColumnPlano6;
	}

	public void setTableColumnPlano6(TableColumn tableColumnPlano6) {
		this.tableColumnPlano6 = tableColumnPlano6;
	}

	public StaticText getStaticTextTablaPlanoConstruccion5() {
		return staticTextTablaPlanoConstruccion5;
	}

	public void setStaticTextTablaPlanoConstruccion5(
			StaticText staticTextTablaPlanoConstruccion5) {
		this.staticTextTablaPlanoConstruccion5 = staticTextTablaPlanoConstruccion5;
	}

	public PanelGroup getGroupPanelPlanoConstruccion() {
		return groupPanelPlanoConstruccion;
	}

	public void setGroupPanelPlanoConstruccion(
			PanelGroup groupPanelPlanoConstruccion) {
		this.groupPanelPlanoConstruccion = groupPanelPlanoConstruccion;
	}

	public Button getBtnAgregarPlanoConstruccion() {
		return btnAgregarPlanoConstruccion;
	}

	public void setBtnAgregarPlanoConstruccion(Button btnAgregarPlanoConstruccion) {
		this.btnAgregarPlanoConstruccion = btnAgregarPlanoConstruccion;
	}

	public Button getBtnModificarPlanoConstruccion() {
		return btnModificarPlanoConstruccion;
	}

	public void setBtnModificarPlanoConstruccion(
			Button btnModificarPlanoConstruccion) {
		this.btnModificarPlanoConstruccion = btnModificarPlanoConstruccion;
	}

	public Button getBtnConsultarPlanoConstruccion() {
		return btnConsultarPlanoConstruccion;
	}

	public void setBtnConsultarPlanoConstruccion(
			Button btnConsultarPlanoConstruccion) {
		this.btnConsultarPlanoConstruccion = btnConsultarPlanoConstruccion;
	}
	public HtmlAjaxCommandButton getBtnQuitarPlanoConstruccion() {
		return btnQuitarPlanoConstruccion;
	}

	public void setBtnQuitarPlanoConstruccion(
			HtmlAjaxCommandButton btnQuitarPlanoConstruccion) {
		this.btnQuitarPlanoConstruccion = btnQuitarPlanoConstruccion;
	}

	/**
	 * <p>
	 * Construir una instancia de bean de p�gina.
	 * </p>
	 */
	public ABMParcela() {
	}

	@Override
	protected ElementoPila agregarObjetosAElementoPila(ElementoPila ep) {
		int ind = 0;

		// CAMBIAR: settear los objetos administrados por la pagina
		ep.getObjetos().add(ind++, new Parcela());
		ep.getObjetos().add(ind++, new Domicilio());
		ep.getObjetos().add(ind++, null); // cuadras
		ep.getObjetos().add(ind++, new Manzana());
		ep.getObjetos().add(ind++, new NomenclaturaCatastral());
		ep.getObjetos().add(ind++, null);// 5 lista de volante catastral NUEVAS
		ep.getObjetos().add(ind++, null);// 6 lista registro mejora NUEVAS
		ep.getObjetos().add(ind++, null);// 7 lista de subparcelas NUEVAS
		ep.getObjetos().add(ind++, null);// 8 lista de subparcelas A ELIMINAR
		ep.getObjetos().add(ind++, null);// 9 lista de volantes A ELIMINAR
		ep.getObjetos().add(ind++, null);// 10 lista de registro mejora A ELIMINAR
		ep.getObjetos().add(ind++, null);// 11 lista de Obligaciones OSP
		ep.getObjetos().add(ind++, new ArrayList());// 12 lista de zonas
		ep.getObjetos().add(ind++, null);// 13 id tab actual
		ep.getObjetos().add(ind++, new ArrayList()); // 14 AtributosDinamicos
		ep.getObjetos().add(ind++, new String()); // 15 Asociaciones de Parcela
		ep.getObjetos().add(ind++, new String()); // 16 Nro Parcela
		// Agregado
		// ep.getObjetos().add(ind++, new TituloPropiedadParcelario()); // 15
		// Titulo
		// ep.getObjetos().add(ind++, new Zona()); // 16 Zona

		// Dejar siempre en la ultimo posicion del arreglo. Manejo de
		// seleccionado.

		ep.getObjetos().add(ind++, new ArrayList()); // 17 AtributosDinamicosTituloPropiedad
		ep.getObjetos().add(ind++, new ArrayList()); // 18 AtributosDinamicosPlanoMensura
		ep.getObjetos().add(ind++, null);// 19 lista plano construccion

		ep.getObjetos().add(ind++, new Integer(0));

		return ep;
	}

	@Override
	protected void guardarEstadoObjetosUsados() {
//		int ind = 0;
		Parcela parcela = this.obtenerObjetoDelElementoPila(0, Parcela.class);
		Domicilio domicilio = this.obtenerObjetoDelElementoPila(1, Domicilio.class);
		List cuadras = this.obtenerObjetoDelElementoPila(2, List.class);
		Manzana manzana = this.obtenerObjetoDelElementoPila(3, Manzana.class);
		NomenclaturaCatastral nomenclaturaCatastral = this.obtenerObjetoDelElementoPila(4, NomenclaturaCatastral.class);
		List listaVolanteCatastral = this.obtenerObjetoDelElementoPila(5, List.class);
		List listaRegistroMejora = this.obtenerObjetoDelElementoPila(6, List.class);
		// List listaObligacionesOSP = (List)
		// this.obtenerObjetoDelElementoPila(ind++, List.class);
		ArrayList atributosDinamicos = this.obtenerObjetoDelElementoPila(14, ArrayList.class);
		List atributosDinamicosTituloPropiedad = this.obtenerObjetoDelElementoPila(17, ArrayList.class);
		List atributosDinamicosPlanoMensura = this.obtenerObjetoDelElementoPila(18, ArrayList.class);
		List listaPlanoConstruccion = this.obtenerObjetoDelElementoPila(19, List.class);
		
		// Titulo de propiedad
		TituloPropiedadParcelario tituloPropiedad = parcela.getTituloPropiedad() != null ? parcela.getTituloPropiedad() : new TituloPropiedadParcelario();

		tituloPropiedad.setTitulo(this.getTextFieldValue(getTfTitulo()));
		tituloPropiedad.setFechaInscripcion(this.getTextFieldValueDate(getTfFechaInscripcion()));
		tituloPropiedad.setNroTomo(this.getTextFieldValueInteger(getTfTomo()));
		tituloPropiedad.setNroFolio(this.getTextFieldValueInteger(getTfFolio()));
		tituloPropiedad.setAsiento(this.getTextFieldValueInteger(getTfAsiento()));
		tituloPropiedad.setAreaRegistro(this.getTextFieldValue(getTfArea()));
		tituloPropiedad.setTipoTransaccionCatastral(this.getDDEnumValue(getDdTipoTransaccion(), TituloPropiedadParcelario.TipoTransaccionCatastral.class));

		RegistroPropietario propietarioEncargado = (RegistroPropietario) this.obtenerEncargadoSeleccionado();
		if(propietarioEncargado != null) {
			tituloPropiedad.setEncargadosListaPropietarios(propietarioEncargado);
		}
		if(this.getObjectListDataProvider4().getList() != null && this.getObjectListDataProvider4().getList().size() > 0) {
			this.getObjectListDataProvider4().commitChanges();
		}
		List registros = this.getObjectListDataProvider4().getList();
		if(registros.size() <= 0) {
			System.out.println("-------- registros null o vacio");
		}
		tituloPropiedad.setListaRegistrosPropietarios(registros);
		this.setListaDelCommunication4(registros);

		parcela.setTituloPropiedad(tituloPropiedad);
		//
		// Plano Mensura
		PlanoMensura planoMensura = parcela.getPlanoMensura() != null ? parcela.getPlanoMensura() : new PlanoMensura();

		planoMensura.setFechaInscripcion(this.getTextFieldValueDate(getTfFechaPlanoMensura()));
		planoMensura.setPlano(this.getTextFieldValue(getTfNroPlanoMensura()));
		planoMensura.setFolio(this.getTextFieldValue(getTfFolioPlanoMensura()));
		planoMensura.setTomo(this.getTextFieldValue(getTfTomoPlanoMensura()));
		planoMensura.setNroExpediente(getTextFieldValue(getTfNroExpedientePlanoMensura()));
		planoMensura.setNroRegistro(getTextFieldValue(getTfNroRegistroPlanoMensura()));
		planoMensura.setTipo(this.getRBGEnumValue(getRbgTipoPlanoMensura(), PlanoMensura.Tipo.class));
		planoMensura.setEstado(this.getRBGEnumValue(getRbgEstadoPlanoMensura(), PlanoMensura.Estado.class));
		planoMensura.setComentario(this.getTextAreaValue(getTaComentario()));

		try {
			parcela.setPlanoMensura(planoMensura);
		} catch(Exception e) {
			error(e.getMessage());
		}

		if(this.getObjectListDataProvider5().getList() != null && this.getObjectListDataProvider5().getList().size() > 0) {
			this.getObjectListDataProvider5().commitChanges();
		}
		List listaZonas = this.getObjectListDataProvider5().getList();
		if(listaZonas.size() <= 0) {
			System.out.println("-------- lista zonas null o vacio");
		}
		parcela.setListaAsociacionParcela(listaZonas);
		this.setListaDelCommunication5(listaZonas);

		ClasificacionCatSegunSuUso.Residencial clasificacionResidencial;
		ClasificacionCatSegunSuUso.Comercial clasificacionComercial;
		ClasificacionCatSegunSuUso.Varios clasificacionVarios;
		ClasificacionCatSegunSuUso.Equipamiento clasificacionEquipamiento;
		ClasificacionCatSegunSuUso.Rural clasificacionRural;

		AfectacionesCatastrales.Explicitas afectacionesExplicitas;
		AfectacionesCatastrales.NoExplicitas afectacionesNoExplicitas;
		AfectacionesCatastrales.RestriccionesDominioExplicitas restriccionesExplicitas;
		AfectacionesCatastrales.RestriccionesDominioNoExplicitas restriccionesNoExplicitas;

		// Nomenclatura Catastral
		nomenclaturaCatastral.setDepartamento(this.getTextFieldValue(getTfDpto()));
		nomenclaturaCatastral.setPedania(this.getTextFieldValue(getTfPedania()));
		nomenclaturaCatastral.setCircunscripcion(this.getTextFieldValue(getTfCircunscripcion()));
		nomenclaturaCatastral.setDistrito(this.getTextFieldValue(getTfDistrito()));
		nomenclaturaCatastral.setSubDistrito(this.getTextFieldValue(getTfSubDistrito()));
		nomenclaturaCatastral.setSeccion(this.getTextFieldValue(getTfSeccionPoligono()));
		nomenclaturaCatastral.setQuinta(this.getTextFieldValue(getTfQuinta()));
		nomenclaturaCatastral.setChacra(this.getTextFieldValue(getTfChacraFraccion()));
		nomenclaturaCatastral.setLote(this.getTextFieldValue(getTfLote()));
		nomenclaturaCatastral.setNroParcela(this.getTextFieldValue(getTfNumeroParcela()));
		parcela.setNomenclaturaCatastral(nomenclaturaCatastral);
		//

		// Compatibilidad Numerica
		parcela.setNroPartidaProvincial(this.getTextFieldValue(getTfNroPartida()));
		parcela.setNroRegistro(this.getTextFieldValue(getTfNroRegistro()));
		parcela.setNroMatricula(this.getTextFieldValue(getTfNroMatricula()));
		parcela.setNroCuenta(this.getTextFieldValueInteger(getTfNroCuenta()));
		//

		// Clasificacion Catastral segun su planta
		Planta locPlanta = new Planta(this.getRBGEnumValue(getRbgClasificacionSegunPlanta(), TipoPlanta.Tipo.class));
		parcela.setPlanta(locPlanta);
		parcela.getPlanta().setTipoPlanta(this.getRBGEnumValue(getRbgClasificacionSegunPlanta(), TipoPlanta.Tipo.class));
		parcela.getPlanta().setTipoEdificacion(this.getDDEnumValue(getDdClasificacionSegunPlantaUrbana(), Planta.Edificacion.class));

		if(this.getChbClasificacionRegadio().getValue() != null && this.getChbClasificacionRegadio().getValue() != "") {
			parcela.getPlanta().setDeRegadio(Boolean.parseBoolean(getChbClasificacionRegadio().getValue().toString()));
		} else {
			parcela.getPlanta().setDeRegadio((Boolean.parseBoolean("false")));
		}
		if(getChbClasificacionRegadio().getValue() != null && getChbClasificacionRegadio().getValue() != "") {
			parcela.getPlanta().setDeSecano(Boolean.parseBoolean(getChbClasificacionRegadio().getValue().toString()));
		} else {
			parcela.getPlanta().setDeSecano((Boolean.parseBoolean("false")));
		}
		//

		// Tipo de Parcela
		parcela.setTipoParcela(this.getDDEnumValue(getDdTipoParcela(), TipoParcela.class));
		//

		// Clasifiacion Catastral
		if((getDdResidencial().getSelected() != null) && (getDdResidencial().getSelected().toString().length() > 0)) {
			if(getDdResidencial().getSelected().toString().equals("DENSIDAD_MEDIA_(ARQUITECTURA_ESPECIAL)")) {
				clasificacionResidencial = Residencial.DENSIDAD_MEDIA;
			} else if(getDdResidencial().getSelected().toString().equals("DENSIDAD_BAJA_(BARRIO_JARDIN)")) {
				clasificacionResidencial = Residencial.DENSIDAD_BAJA_BARRIO_JARDIN;
			} else if(getDdResidencial().getSelected().toString().equals("DENSIDAD_BAJA_(ZONA_COSTA)")) {
				clasificacionResidencial = Residencial.DENSIDAD_BAJA_ZONA_COSTA;
			} else if(getDdResidencial().getSelected().toString().equals("DENSIDAD_BAJA_(ZONA_INUNDABLE)")) {
				clasificacionResidencial = Residencial.DENSIDAD_BAJA_ZONA_INUNDABLE;
			} else if(getDdResidencial().getSelected().toString().equals("ESPECIAL_(PLANES_VIVIENDAS)")) {
				clasificacionResidencial = Residencial.ESPECIAL_PLANES_VIVIENDAS;
			} else {
				clasificacionResidencial = ClasificacionCatSegunSuUso.Residencial.valueOf(getDdResidencial().getSelected().toString());
			}
		} else {
			clasificacionResidencial = null;
		}
		parcela.setTipoResidencial(clasificacionResidencial);

		parcela.setTipoComercial(this.getDDEnumValue(getDdComercial(), ClasificacionCatSegunSuUso.Comercial.class));
		parcela.setTipoVarios(this.getDDEnumValue(getDdVarios(), ClasificacionCatSegunSuUso.Varios.class));
		parcela.setTipoEquipamiento(this.getDDEnumValue(getDdEquipamiento(), ClasificacionCatSegunSuUso.Equipamiento.class));
		parcela.setTipoRural(this.getDDEnumValue(getDdRural(), ClasificacionCatSegunSuUso.Rural.class));
		//

		// Domicilio - superficie - fecha alta
		if(domicilio.getLocalidad() == null) {
			domicilio = null;
		}
		parcela.setDomicilioParcelario(domicilio);

		parcela.setSuperficie(this.getTextFieldValueDouble(getTfSuperficie()));
		parcela.setFechaAlta(this.getTextFieldValueDate(getTfFechaAlta()));
		//

		// Avaluos
		parcela.setAvaluoTerreno(this.getTextFieldValueDouble(getTfAvaluoTerreno()));
		parcela.setAvaluoPorMejoras(this.getTextFieldValueDouble(getTfAvaluoMejoras()));
		//

		// Afectaciones y Restricciones
		parcela.setAfectacionesExplicitas(this.getDDEnumValue(getDdAfectacionesExplicitas(), AfectacionesCatastrales.Explicitas.class));
		parcela.setAfectacionesNoExplicitas(this.getDDEnumValue(getDdAfectacionesNoExplicitas(), AfectacionesCatastrales.NoExplicitas.class));
		parcela.setRestriccionesDominioExplicitas(this.getDDEnumValue(getDdRestriccionesAlDominioExplicitas(), AfectacionesCatastrales.RestriccionesDominioExplicitas.class));
		parcela.setRestriccionesDominicioNoExplicitas(this.getDDEnumValue(getDdRestriccionesAlDominioNoExplicitas(), AfectacionesCatastrales.RestriccionesDominioNoExplicitas.class));
		//

		// Metros de Frente por Cuadra
		// Object metrosFrente = this.getTfMetrosFrente().getText();
		//

		// Metros de frente
		// if (metrosFrente != null && metrosFrente != "") {
		// parcela.setMetrosFrente(Conversor.getDoubleDeString(metrosFrente.toString()));
		// } else {
		// parcela.setMetrosFrente(null);
		// }

		if(manzana.getIdManzana() == -1) {
			manzana = null;
		}
		parcela.setManzana(manzana);

		// si hubo cambios en los metros de frente
		this.getLdpParcelaCuadras().commitChanges();

		cuadras = this.getLdpParcelaCuadras().getList();
		parcela.setListaParcelasPorCuadra(cuadras);
		this.getComunicationCatastroBean().setListaParcelaPorCuadras(cuadras);

		listaRegistroMejora = this.getLdpRegistroMejoraParcela().getList();
		if(listaRegistroMejora != null){
		parcela.setListaRegistrosMejora(new HashSet<RegistroMejora>(listaRegistroMejora));
		}
		
		listaPlanoConstruccion = this.getLdpPlanoConstruccion().getList();
		if(listaPlanoConstruccion != null){
		parcela.setListaPlanosConstruccion(new ArrayList<PlanoConstruccion>(listaPlanoConstruccion));
		}

		String idTabActual = this.getTabSet1().getSelected();

		atributosDinamicos = (ArrayList) panelAtributoDinamico.obtenerListaAtributosDinamicos(atributosDinamicos);
		parcela.setListaAtributosDinamicos(atributosDinamicos);

		TituloPropiedadParcelario locTituloPropiedad = parcela.getTituloPropiedad();
		PlanoMensura locPlanoMensura = parcela.getPlanoMensura();
		PlanoConstruccion locPlanoConstruccion = parcela.getPlanoConstruccion();

		atributosDinamicosTituloPropiedad = panelAtributoDinamicoTituloPropiedad.obtenerListaAtributosDinamicos(atributosDinamicosTituloPropiedad);
		locTituloPropiedad.setListaAtributosDinamicos(atributosDinamicosTituloPropiedad);

		atributosDinamicosPlanoMensura = panelAtributoDinamicoPlanoMensura.obtenerListaAtributosDinamicos(atributosDinamicosPlanoMensura);
		locPlanoMensura.setListaAtributosDinamicos(atributosDinamicosPlanoMensura);

		this.getElementoPila().getObjetos().set(0, parcela);
		this.getElementoPila().getObjetos().set(1, domicilio);
		this.getElementoPila().getObjetos().set(2, cuadras);
		this.getElementoPila().getObjetos().set(3, manzana);
		this.getElementoPila().getObjetos().set(4, nomenclaturaCatastral);
		this.getElementoPila().getObjetos().set(5, listaVolanteCatastral);
		this.getElementoPila().getObjetos().set(6, listaRegistroMejora);
		this.getElementoPila().getObjetos().set(13, idTabActual);
		this.getElementoPila().getObjetos().set(14, atributosDinamicos);
		this.getElementoPila().getObjetos().set(17, atributosDinamicosTituloPropiedad);
		this.getElementoPila().getObjetos().set(18, atributosDinamicosPlanoMensura);
		this.getElementoPila().getObjetos().set(19, listaPlanoConstruccion);
	}

	@Override
	protected void mostrarEstadoObjetosUsados() {
		int ind = 0;
		Parcela parcela = this.obtenerObjetoDelElementoPila(ind++, Parcela.class);
		TituloPropiedadParcelario locTituloPropiedad = parcela.getTituloPropiedad();
		PlanoMensura locPlanoMensura = parcela.getPlanoMensura();

		try {
			Parcela locParcela = (Parcela) this.obtenerObjetoDelElementoPila(0);
			this.getElementoPila().getObjetos()
					.set(14, this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(Parcela.serialVersionUID, locParcela.getListaAtributosDinamicos(), null));
			if(locTituloPropiedad != null) {
				this.getElementoPila()
						.getObjetos()
						.set(17,
								this.getComunicationBean().getRemoteSystemParametro()
										.getAtributosPorRecurso(TituloPropiedadParcelario.serialVersionUID, locTituloPropiedad.getListaAtributosDinamicos(), null));
			} else {
				this.getElementoPila().getObjetos()
						.set(17, this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(TituloPropiedadParcelario.serialVersionUID, null, null));
			}
			if(locPlanoMensura != null) {
				this.getElementoPila()
						.getObjetos()
						.set(18,
								this.getComunicationBean().getRemoteSystemParametro()
										.getAtributosPorRecurso(PlanoMensura.serialVersionUID, locPlanoMensura.getListaAtributosDinamicos(), null));
			} else {
				this.getElementoPila().getObjetos().set(18, this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(PlanoMensura.serialVersionUID, null, null));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		this.acomodarSeleccionado();

		if(this.getRequestBean1().getRespuestaABM() != null) {
			Object respuesta = this.getRequestBean1().getRespuestaABM();
			int posicion = this.obtenerObjetoDelElementoPila(this.getCantidadObjetosUsados() - 1, Integer.class).intValue();
			this.getElementoPila().getObjetos().set(posicion, respuesta);
		}

		Domicilio domicilio = this.obtenerObjetoDelElementoPila(ind++, Domicilio.class);
		List cuadras = this.obtenerObjetoDelElementoPila(ind++, List.class);
		Manzana manzana = this.obtenerObjetoDelElementoPila(ind++, Manzana.class);
		NomenclaturaCatastral nomenclaturaCatastral = this.obtenerObjetoDelElementoPila(ind++, NomenclaturaCatastral.class);
		List listaVolanteCatastral = this.obtenerObjetoDelElementoPila(ind++, List.class);
		List listaRegistroMejora = this.obtenerObjetoDelElementoPila(ind++, List.class);
		List atributosDinamicos = this.obtenerObjetoDelElementoPila(14, ArrayList.class);
		List atributosDinamicosTituloPropiedad = this.obtenerObjetoDelElementoPila(17, ArrayList.class);
		List atributosDinamicosPlanoMensura = this.obtenerObjetoDelElementoPila(18, ArrayList.class);
		List listaPlanoConstruccion = this.obtenerObjetoDelElementoPila(19, List.class);
		
		Double aux = 0.0;
		for(RegistroMejora cadaRegistro : parcela.getListaRegistrosMejora()) {
			aux = aux + cadaRegistro.getSuperficie();
		}
		this.getTfRegistroMejora().setText(aux.toString());

		TituloPropiedadParcelario tituloPropiedad = parcela.getTituloPropiedad();

		if(tituloPropiedad != null) {
			if(tituloPropiedad.getTitulo() != null) {
				this.getTfTitulo().setText(tituloPropiedad.getTitulo());
			}
			if(tituloPropiedad.getFechaInscripcion() != null) {
				this.getTfFechaInscripcion().setText(Conversor.getStringDeFechaCorta(tituloPropiedad.getFechaInscripcion()));
			}
			if(tituloPropiedad.getNroTomo() != null) {
				this.getTfTomo().setText(tituloPropiedad.getNroTomo());
			}
			if(tituloPropiedad.getNroFolio() != null) {
				this.getTfFolio().setText(tituloPropiedad.getNroFolio());
			}
			if(tituloPropiedad.getAsiento() != null) {
				this.getTfAsiento().setText(tituloPropiedad.getAsiento());
			}
			if(tituloPropiedad.getAreaRegistro() != null) {
				this.getTfArea().setText(tituloPropiedad.getAreaRegistro());
			}
			if(tituloPropiedad.getTipoTransaccionCatastral() != null) {
				this.getDdTipoTransaccion().setSelected(Util.getEnumNameFromString(String.valueOf(tituloPropiedad.getTipoTransaccionCatastral())));
			}

			this.getObjectListDataProvider4().setList(tituloPropiedad.getListaRegistrosPropietarios());
			this.setListaDelCommunication4(new ArrayList(tituloPropiedad.getListaRegistrosPropietarios()));

			for (RegistroPropietario cadaRegistro : tituloPropiedad.getListaRegistrosPropietarios()){
				if (cadaRegistro.getEncargadoDeObligaciones() != false){
					this.seleccionarFilaEncargado(cadaRegistro);
				}
			} 
			
		} else {
			this.setListaDelCommunication4(new ArrayList());
			this.getObjectListDataProvider4().setList(new ArrayList());
		}

		this.getObjectListDataProvider5().setList(parcela.getListaAsociacionParcela());
		this.setListaDelCommunication5(new ArrayList(parcela.getListaAsociacionParcela()));

		PlanoMensura planoMensura = parcela.getPlanoMensura();
		if(planoMensura != null) {
			if(planoMensura.getFechaInscripcion() != null) {
				this.getTfFechaPlanoMensura().setText(Conversor.getStringDeFechaCorta(planoMensura.getFechaInscripcion()));
			}
			if(planoMensura.getPlano() != null) {
				this.getTfNroPlanoMensura().setText(planoMensura.getPlano());
			}
			if(planoMensura.getFolio() != null) {
				this.getTfFolioPlanoMensura().setText(planoMensura.getFolio());
			}
			if(planoMensura.getTomo() != null) {
				this.getTfTomoPlanoMensura().setText(planoMensura.getTomo());
			}
			if(planoMensura.getNroExpediente() != null) {
				this.getTfNroExpedientePlanoMensura().setText(planoMensura.getNroExpediente());
			}
			if(planoMensura.getNroRegistro() != null) {
				this.getTfNroRegistroPlanoMensura().setText(planoMensura.getNroRegistro());
			}
			if(planoMensura.getPlano() != null) {
				this.getRbgTipoPlanoMensura().setSelected(Util.getEnumNameFromString(String.valueOf(planoMensura.getTipo())));
			}
			if(planoMensura.getEstado() != null) {
				this.getRbgEstadoPlanoMensura().setSelected(Util.getEnumNameFromString(String.valueOf(planoMensura.getEstado())));
			}
			if(planoMensura.getComentario() != null) {
				this.getTaComentario().setText(planoMensura.getComentario());
			}
		}

		panelAtributoDinamico = new PanelAtributoDinamico(atributosDinamicos, "#{catastro$ABMParcela$ABMParcela}");
		panelAtributoDinamico.establecerListaAtributosDinamicos(atributosDinamicos);

		panelAtributoDinamicoTituloPropiedad = new PanelAtributoDinamico(atributosDinamicosTituloPropiedad, "#{catastro$ABMParcela$ABMParcela}");
		panelAtributoDinamicoTituloPropiedad.establecerListaAtributosDinamicos(atributosDinamicosTituloPropiedad);

		panelAtributoDinamicoPlanoMensura = new PanelAtributoDinamico(atributosDinamicosPlanoMensura, "#{catastro$ABMParcela$ABMParcela}");
		panelAtributoDinamicoPlanoMensura.establecerListaAtributosDinamicos(atributosDinamicosPlanoMensura);

		// Nomenclatura Catastral:
		if(nomenclaturaCatastral.getDepartamento() != null) {
			this.getTfDpto().setText(nomenclaturaCatastral.getDepartamento().toString());
		}
		if(nomenclaturaCatastral.getPedania() != null) {
			this.getTfPedania().setText(nomenclaturaCatastral.getPedania().toString());
		}
		if(nomenclaturaCatastral.getCircunscripcion() != null) {
			this.getTfCircunscripcion().setText(nomenclaturaCatastral.getCircunscripcion().toString());
		}
		if(nomenclaturaCatastral.getDistrito() != null) {
			this.getTfDistrito().setText(nomenclaturaCatastral.getDistrito().toString());
		}
		if(nomenclaturaCatastral.getSubDistrito() != null) {
			this.getTfSubDistrito().setText(nomenclaturaCatastral.getSubDistrito().toString());
		}
		if(nomenclaturaCatastral.getSeccion() != null) {
			this.getTfSeccionPoligono().setText(nomenclaturaCatastral.getSeccion().toString());
		}
		if(nomenclaturaCatastral.getQuinta() != null) {
			this.getTfQuinta().setText(nomenclaturaCatastral.getQuinta().toString());
		}
		if(nomenclaturaCatastral.getChacra() != null) {
			this.getTfChacraFraccion().setText(nomenclaturaCatastral.getChacra().toString());
		}
		if(nomenclaturaCatastral.getLote() != null) {
			this.getTfLote().setText(nomenclaturaCatastral.getLote().toString());
		}
		if(nomenclaturaCatastral.getNroParcela() != null) {
			this.getTfNumeroParcela().setText(nomenclaturaCatastral.getNroParcela().toString());
		}

		// Compatibilidad Numerica:
		if(parcela.getNroPartidaProvincial() != null) {
			this.getTfNroPartida().setText(parcela.getNroPartidaProvincial().toString());
		}
		if(parcela.getNroRegistro() != null) {
			this.getTfNroRegistro().setText(parcela.getNroRegistro().toString());
		}
		if(parcela.getNroMatricula() != null) {
			this.getTfNroMatricula().setText(parcela.getNroMatricula().toString());
		}
		if(parcela.getNroCuenta() != null) {
			this.getTfNroCuenta().setText(parcela.getNroCuenta().toString());
		}

		// Clasificacion Catastral segun su planta:
		if(parcela.getPlanta() != null) {
			if(parcela.getPlanta().getTipoPlanta() != null) {
				this.getRbgClasificacionSegunPlanta().setSelected(Util.getEnumNameFromString(String.valueOf(parcela.getPlanta().getTipoPlanta())));
			}
			if(parcela.getPlanta().getTipoEdificacion() != null) {
				this.getDdClasificacionSegunPlantaUrbana().setSelected(Util.getEnumNameFromString(String.valueOf(parcela.getPlanta().getTipoEdificacion())));
			}
			this.getChbClasificacionRegadio().setValue(parcela.getPlanta().getDeRegadio());
			this.getChbClasificacionSecano().setValue(parcela.getPlanta().getDeSecano());
		}
		// Tipo de Parcela
		this.getDdTipoParcela().setSelected(Util.getEnumNameFromString(String.valueOf(parcela.getTipoParcela())));

		// Clasifiacion Catastral segun su uso

		if(parcela.getTipoResidencial() != null) {
			this.getDdResidencial().setSelected(Util.getEnumNameFromString(String.valueOf(parcela.getTipoResidencial())));
		}
		if(parcela.getTipoComercial() != null) {
			this.getDdComercial().setSelected(Util.getEnumNameFromString(String.valueOf(parcela.getTipoComercial())));

		}
		if(parcela.getTipoVarios() != null) {
			this.getDdVarios().setSelected(Util.getEnumNameFromString(String.valueOf(parcela.getTipoVarios())));
		}
		if(parcela.getTipoEquipamiento() != null) {
			this.getDdEquipamiento().setSelected(Util.getEnumNameFromString(String.valueOf(parcela.getTipoEquipamiento())));
		}
		if(parcela.getTipoRural() != null) {
			this.getDdRural().setSelected(Util.getEnumNameFromString(String.valueOf(parcela.getTipoRural())));
		}

		// Domiclio - sup- fechaAlta
		this.getStDomicilio().setText(domicilio.getDomicilioCompleto().replaceAll("\n", "<br/>"));

		if(parcela.getFechaAlta() != null) {
			this.getTfFechaAlta().setText(Conversor.getStringDeFechaCorta(parcela.getFechaAlta()));
		}
		if(parcela.getSuperficie() != null) {
			this.getTfSuperficie().setText(Conversor.getStringDeDouble(parcela.getSuperficie()));
		}

		// Avaluo
		if(parcela.getAvaluoTerreno() != null) {
			this.getTfAvaluoTerreno().setText(Conversor.getStringDeDouble(parcela.getAvaluoTerreno()));
		}
		if(parcela.getAvaluoPorMejoras() != null) {
			this.getTfAvaluoMejoras().setText(Conversor.getStringDeDouble(parcela.getAvaluoPorMejoras()));
		}
		//

		// Afectaciones y Restricciones
		if(parcela.getAfectacionesExplicitas() != null) {
			this.getDdAfectacionesExplicitas().setSelected(Util.getEnumNameFromString(String.valueOf(parcela.getAfectacionesExplicitas())));
		}
		if(parcela.getAfectacionesNoExplicitas() != null) {
			this.getDdAfectacionesNoExplicitas().setSelected(Util.getEnumNameFromString(String.valueOf(parcela.getAfectacionesNoExplicitas())));
		}
		if(parcela.getRestriccionesDominioExplicitas() != null) {
			this.getDdRestriccionesAlDominioExplicitas().setSelected(Util.getEnumNameFromString(String.valueOf(parcela.getRestriccionesDominioExplicitas())));
		}
		if(parcela.getRestriccionesDominicioNoExplicitas() != null) {
			this.getDdRestriccionesAlDominioNoExplicitas().setSelected(Util.getEnumNameFromString(String.valueOf(parcela.getRestriccionesDominicioNoExplicitas())));
		}

		this.getTfManzanaCuadra().setText(manzana.toString());

		this.getLdpParcelaCuadras().setList(cuadras);
		this.setListaDelCommunication(cuadras);

		this.getLdpRegistroMejoraParcela().setList(listaRegistroMejora);
		this.setListaDelCommunication2((ArrayList) listaRegistroMejora);

		this.getLdpPlanoConstruccion().setList(listaPlanoConstruccion);
		this.setListaDelCommunication6(listaPlanoConstruccion);
		
		// this.getLdpVolanteCatastralParcela().setList(listaVolanteCatastral);
		// this.setListaDelCommunication3(new ArrayList(listaVolanteCatastral));

		// double superficieDividida = 0;
		// System.out.println("lista subparcelas---- " +
		// parcela.getListaSubParcelas().size());

		// for (Iterator it = parcela.getListaSubParcelas().iterator();
		// it.hasNext();) {
		// SubParcela subParcela = (SubParcela) it.next();
		// System.out.println("subparcelaaaaa titular---" +
		// subParcela.getTitular());
		// System.out.println("subparcelaaaaa superficie---" +
		// subParcela.getSuperficie());
		// superficieDividida += subParcela.getSuperficie().doubleValue();
		// }

		// for (Iterator it = this.getListaDelCommunication4().iterator();
		// it.hasNext();) {
		// SubParcela subParcela = (SubParcela) it.next();
		// System.out.println("subparcelaaaaa titular---" +
		// subParcela.getTitular());
		// System.out.println("subparcelaaaaa superficie---" +
		// subParcela.getSuperficie());
		// superficieDividida += subParcela.getSuperficie().doubleValue();
		// }
		// this.getTfSuperficieDividida().setText(new
		// Double(superficieDividida));

		this.getLdpVolanteCatastralParcela().setList(this.getListaDelCommunication3());
		this.getLdpObligaciones().setList(this.getListaDelCommunicationObligaciones());

		// this.getLdpSubdivisiones().setList(this.getListaDelCommunication4());
		String idTab = (String) this.obtenerObjetoDelElementoPila(13);
		if(idTab != null) {
			this.getTabSet1().setSelected(idTab);
		}

		// Propietarios:
		String propietariosTemp = "<b>Propietarios:</b><br/>";
		try {
			TituloPropiedadParcelario titulo = parcela.getTituloPropiedad();

			if(titulo != null) {

				List registrosPropietariosTemp = null;

				if(titulo != null) {
					registrosPropietariosTemp = titulo.getListaRegistrosPropietarios();
				}

				if(registrosPropietariosTemp != null && registrosPropietariosTemp.size() > 0) {
					propietariosTemp += "<ol>";
					for(int i = 0; i < registrosPropietariosTemp.size(); i++) {
						RegistroPropietario registroPropietarioTemp = (RegistroPropietario) registrosPropietariosTemp.get(i);
						if(registroPropietarioTemp.getPersona().getEstado().equals(Persona.Estado.ACTIVO)) {
							propietariosTemp += "<li>" + registroPropietarioTemp.getPersona().toString() + "</li>";
						}
					}
					propietariosTemp += "</ol>";
				} else {
					propietariosTemp += "Ning\372n Propietario Registrado.";
				}
			} else {
				propietariosTemp += "Ning\372n T\355tulo de Propiedad Registrado.";
			}

			this.getStPropietariosTemp().setText(propietariosTemp);
		} catch(Exception ex) {
			this.getStPropietariosTemp().setText(propietariosTemp + "Ha ocurrido un error intentando recuperar los propietarios." + "<br/><br/>" + ex.getMessage());
		}
		this.getStZonas().setText(this.obtenerObjetoDelElementoPila(15, String.class));
	}

	/*
	 * parcela cuadras
	 */
	private ObjectListDataProvider getObjectListDataProvider() {
		return this.getLdpParcelaCuadras();
	}

	private List getListaDelCommunication() {
		return this.getComunicationCatastroBean().getListaParcelaPorCuadras();
	}

	private void setListaDelCommunication(List lista) {
		this.getComunicationCatastroBean().setListaParcelaPorCuadras(lista);
	}

	/*
	 * registro mejoras
	 */
	private ObjectListDataProvider getObjectListDataProvider2() {
		return this.getLdpRegistroMejoraParcela();
	}

	private ArrayList getListaDelCommunication2() {
		return this.getComunicationCatastroBean().getListaRegistrosMejoraParcela();
	}

	private void setListaDelCommunication2(ArrayList lista) {
		this.getComunicationCatastroBean().setListaRegistrosMejoraParcela(lista);
	}

	/*
	 * volante catastral
	 */
	private ObjectListDataProvider getObjectListDataProvider3() {
		return this.getLdpVolanteCatastralParcela();
	}

	private ArrayList getListaDelCommunication3() {
		return this.getComunicationCatastroBean().getListaVolatesCatastralesParcela();
	}

	private void setListaDelCommunication3(ArrayList lista) {
		this.getComunicationCatastroBean().setListaVolatesCatastralesParcela(lista);
	}

	/*
	 * Obligaciones
	 */
	private ObjectListDataProvider getObjectListDataProviderObligaciones() {
		return this.getLdpObligaciones();
	}

	private List getListaDelCommunicationObligaciones() {
		return this.getComunicationCatastroBean().getListaObligacionesParcela();
	}

	private void setListaDelCommunicationObligaciones(List lista) {
		this.getComunicationCatastroBean().setListaObligacionesParcela(lista);
	}

	private ObjectListDataProvider getObjectListDataProvider4() {
		return this.getLdpRegistroPropietario();
	}

	private List getListaDelCommunication4() {
		return this.getComunicationCatastroBean().getListaRegistrosPropietarios();
	}

	private void setListaDelCommunication4(List lista) {
		this.getComunicationCatastroBean().setListaRegistrosPropietarios(lista);
	}

	private ObjectListDataProvider getObjectListDataProvider5() {
		return this.getLdpZonas();
	}

	private List getListaDelCommunication5() {
		return this.getComunicationCatastroBean().getListaZonasParcelas();
	}

	private void setListaDelCommunication5(List lista) {
		this.getComunicationCatastroBean().setListaZonasParcelas(lista);
	}

	/*
	 * subdivisiones
	 */
	// private ObjectListDataProvider getObjectListDataProvider4() {
	// // CAMBIAR: Utilizar el ObjectListDataProvider correspondiente
	// return this.getLdpSubdivisiones();
	// }
	//
	// private ArrayList getListaDelCommunication4() {
	// // CAMBIAR: Utilizar la Lista del Comunication que corresponda
	// return this.getComunicationCatastroBean().getListaSubdivisiones();
	// }
	//
	// private void setListaDelCommunication4(ArrayList lista) {
	// // CAMBIAR: Utilizar la Lista del Comunication que corresponda
	// this.getComunicationCatastroBean().setListaSubdivisiones(lista);
	// }
	// /*
	// * obligaciones OSP
	// */
	// private ArrayList getListaDelCommunication5() {
	// // CAMBIAR: Utilizar la Lista del Comunication que corresponda
	// return this.getComunicationCatastroBean().getListaServicios();
	// }
	//
	// private void setListaDelCommunication5(ArrayList lista) {
	// // CAMBIAR: Utilizar la Lista del Comunication que corresponda
	// this.getComunicationCatastroBean().setListaServicios(lista);
	// }

	public String getCurrentRow() {
		return tableRowGroup1.getRowKey().getRowId();
	}

	public void setCurrentRow(int row) {
	}

	public String getCurrentRow2() {
		return tableRowGroup2.getRowKey().getRowId();
	}

	public void setCurrentRow2(int row) {
	}

	public String getCurrentRow3() {
		return tableRowGroup3.getRowKey().getRowId();
	}

	public void setCurrentRow3(int row) {
	}

	public String getCurrentRowObligacion() {
		return tableRowObligacion.getRowKey().getRowId();
	}

	public void setCurrentRowObligacion(int row) {
	}

	public String getCurrentRow4() {
		return tableRowGroup4.getRowKey().getRowId();
	}

	public void setCurrentRow4(int row) {
	}

	public String getCurrentRow5() {
		return tableRowGroup5.getRowKey().getRowId();
	}

	public void setCurrentRow5(int row) {
	}

	public String getCurrentRow6() {
		return tableRowGroup6.getRowKey().getRowId();
	}

	public void setCurrentRow6(int row) {
	}

	public String getCurrentRowEncargado() {
		return tableRowGroup5.getRowKey().getRowId();
	}

	public void setCurrentRowEncargado(int row) {
	}

	private Object lastSelected = null;

	public Object getRBSelected() {
		String sv = (String) radioButton1.getSelectedValue();
		return sv.equals(lastSelected) ? sv : null;
	}

	public void setRBSelected(Object selected) {
		if(selected != null) {
			lastSelected = selected;
		}
	}

	private Object lastSelected2 = null;

	public Object getRBSelected2() {
		String sv = (String) radioButton2.getSelectedValue();
		return sv.equals(lastSelected2) ? sv : null;
	}

	public void setRBSelected2(Object selected) {
		if(selected != null) {
			lastSelected2 = selected;
		}
	}

	private Object lastSelected3 = null;

	public Object getRBSelected3() {
		String sv = (String) radioButton3.getSelectedValue();
		return sv.equals(lastSelected3) ? sv : null;
	}

	public void setRBSelected3(Object selected) {
		if(selected != null) {
			lastSelected3 = selected;
		}
	}

	private Object lastSelected4 = null;

	public Object getRBSelected4() {
		String sv = (String) radioButton4.getSelectedValue();
		return sv.equals(lastSelected4) ? sv : null;
	}

	public void setRBSelected4(Object selected) {
		if(selected != null) {
			lastSelected4 = selected;
		}
	}

	private Object lastSelected5 = null;

	public Object getRBSelected5() {
		String sv = (String) radioButton5.getSelectedValue();
		return sv.equals(lastSelected5) ? sv : null;
	}

	public void setRBSelected5(Object selected) {
		if(selected != null) {
			lastSelected5 = selected;
		}
	}

	private Object lastSelected6 = null;

	public Object getRBSelected6() {
		String sv = (String) radioButton6.getSelectedValue();
		return sv.equals(lastSelected6) ? sv : null;
	}

	public void setRBSelected6(Object selected) {
		if(selected != null) {
			lastSelected6 = selected;
		}
	}

	private Object lastSelectedEncargado = null;

	public Object getRBSelectedEncargado() {
		String sv = (String) radioButtonEncargado.getSelectedValue();
		return sv.equals(lastSelectedEncargado) ? sv : null;
	}

	public void setRBSelectedEncargado(Object selected) {
		if(selected != null) {
			lastSelectedEncargado = selected;
		}
	}

	private RowKey rowKeySeleccionado = null;

	public RowKey getRowKeySeleccionado() {
		return rowKeySeleccionado;
	}

	public void setRowKeySeleccionado(RowKey rowKeySeleccionado) {
		this.rowKeySeleccionado = rowKeySeleccionado;
	}

	public RowKey getEncargadoSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroupEncargado");
			rk = this.getLdpRegistroPropietario().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public void seleccionarFilaEncargado(Object encargado) {
		int cantFilas = this.getObjectListDataProvider4().getList().size();
		RegistroPropietario enTabla;
		for(int i = 0; i < cantFilas; i++) {
			enTabla = (RegistroPropietario) this.getObjectListDataProvider4().getObjects()[i];
			if(enTabla.getIdRegistroPropietario() == ((RegistroPropietario) encargado).getIdRegistroPropietario()) {
				lastSelectedEncargado = new Long(i).toString();
			}
		}
	}

	private Object obtenerEncargadoSeleccionado() {
		RowKey rk = null;
		Object enc = null;
		try {
			rk = this.getEncargadoSeleccionado();
			if(rk != null) {
				int index = getNroFila(rk.toString());
				enc = this.getLdpRegistroPropietario().getObjects()[index];
			}
		} catch(Exception ex) {
		}
		return enc;
	}

	public void guardarOrdenamiento() {
		this.getElementoPila().setOrdenamiento(this.getTableRowGroup1().getTableDataSorter().getSortCriteria());
	}

	public void setearOrdenamiento() {
		this.getTableRowGroup1().setSortCriteria((SortCriteria[]) this.getElementoPila().getOrdenamiento());
	}

	public Long getPosicionEnTabla(RowKey rk) {
		long inicioPagina = 0;
		long posicionGlobal = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();
		long cantRegistros = this.getTableRowGroup1().getRowCount();
		boolean encontrado = false;

		if(rk != null) {
			while(!encontrado && inicioPagina < cantRegistros) {
				this.getTableRowGroup1().setFirst((int) inicioPagina);
				posicionEnPagina = 0;
				while(!encontrado && posicionEnPagina < cantRegistrosPorPagina) {
					encontrado = this.getTableRowGroup1().getRenderedRowKeys()[(int) posicionEnPagina].equals(rk);
					if(!encontrado) {
						posicionEnPagina++;
						posicionGlobal++;
					}
				}
				if(!encontrado) {
					inicioPagina += cantRegistrosPorPagina;
				}
			}
		}
		return new Long(posicionGlobal);
	}

	public RowKey getRowKeyAsociado(Long posicionEnTabla) {
		RowKey rk = this.getTableRowGroup1().getSortedRowKeys()[posicionEnTabla.intValue()];
		return rk;
	}

	public void seleccionarFila(Long posicionGlobal) {
		long cantFilas = this.getTableRowGroup1().getRowCount();
		if(cantFilas > 0) {
			// si hay al menos una fila
			if(posicionGlobal.longValue() > cantFilas) {
				// si elimine la ultima fila, me posiciono en la anterior
				posicionGlobal = new Long(cantFilas);
			}

			int index = this.getNroFila(this.getRowKeyAsociado(posicionGlobal).toString());
			this.getTableRowGroup1().setFirst(this.getInicioPagina(posicionGlobal).intValue());
			lastSelected = new Long(index).toString();
		}
	}

	public Long getInicioPagina(Long posicionGlobal) {
		long inicioPagina = 0;
		long posicionEnPagina = 0;
		long cantRegistrosPorPagina = this.getTableRowGroup1().getRows();

		inicioPagina = (posicionGlobal.longValue() / cantRegistrosPorPagina) * cantRegistrosPorPagina;
		return new Long(inicioPagina);
	}

	public RowKey getSeleccionado() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup");
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getLdpParcelaCuadras().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public RowKey getSeleccionado2() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup2");
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getLdpRegistroMejoraParcela().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	public RowKey getSeleccionado3() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup3");
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getLdpVolanteCatastralParcela().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}
	
	public RowKey getSeleccionadoPlanoConstruccion() {
		RowKey rk = null;
		try {
			String aRowId = (String) RadioButton.getSelected("buttonGroup6");
			// CAMBIAR: Utilizar el ListDataProvider correspondiente
			rk = this.getLdpPlanoConstruccion().getRowKey(aRowId);
		} catch(Exception ex) {
		}
		return rk;
	}

	// public RowKey getSeleccionado4() {
	// RowKey rk = null;
	// try {
	// String aRowId = (String) RadioButton.getSelected("buttonGroup4");
	// // CAMBIAR: Utilizar el ListDataProvider correspondiente
	// rk = this.getLdpSubdivisiones().getRowKey(aRowId);
	// } catch (Exception ex) {
	// }
	// return rk;
	// }

	public String btnSeleccionarDomicilio_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		int posicionObjetoSeleccionado = 1;

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getElementoPila().getObjetos().set(this.getCantidadObjetosUsados() - 1, new Integer(posicionObjetoSeleccionado));
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			Domicilio domicilio = this.obtenerObjetoDelElementoPila(posicionObjetoSeleccionado, Domicilio.class);
			Localidad localidad = domicilio.getLocalidad();

			if(localidad != null) {
				this.getRequestBean1().setObjetoABM(domicilio);
				retorno = "ModificarDomicilio";
			} else {
				retorno = "AgregarDomicilio";
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnLimpiarDomicilio_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(1, this.getStDomicilio());
			this.getStDomicilio().setText(null);
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnSeleccionarManzana_action() {
		return navegarParaSeleccionar("AdminManzana");
	}

	public String btnLimpiarManzanaCuadra_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		if(ultimo) {
			// CAMBIAR: Especificar objeto
			this.limpiarObjeto(2, this.getTfManzanaCuadra());
			this.limpiarObjeto(3, this.getTfManzanaCuadra());
			this.getLdpParcelaCuadras().getList().clear();
			this.getComunicationCatastroBean().getListaParcelaPorCuadras().clear();
			this.guardarEstadoObjetosUsados();
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarRegMejora_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(0, Parcela.class));
			this.getRequestBean1().setAbmController(new RegistroMejoraModel().new AgregarRegistroMejoraController());
			retorno = "ABMRegistroMejora";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnAgregarPlanoConstrucion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
			this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(0, Parcela.class));
			this.getRequestBean1().setAbmController(new PlanoConstruccionModel().new AgregarPlanoConstruccionController());
			retorno = "ABMPlanoConstruccion";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	public String btnModificarRegMejora_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado2();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el ListDataProvider adecuado.
					Object obj = this.getLdpRegistroMejoraParcela().getObjects()[index];
					RegistroMejora registroMejora = (RegistroMejora) obj;

					this.guardarEstadoObjetosUsados();
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(0, Parcela.class));
					this.getRequestBean1().setObjetoABM(registroMejora);
					this.getRequestBean1().setAbmController(new RegistroMejoraModel().new ModificarRegistroMejoraController());
					retorno = "ABMRegistroMejora";
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	public String btnModificarPlanoConstruccion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionadoPlanoConstruccion();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el ListDataProvider adecuado.
					Object obj = this.getLdpPlanoConstruccion().getObjects()[index];
					PlanoConstruccion planoConstruccion = (PlanoConstruccion) obj;

					this.guardarEstadoObjetosUsados();
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(0, Parcela.class));
					this.getRequestBean1().setObjetoABM(planoConstruccion);
					this.getRequestBean1().setAbmController(new PlanoConstruccionModel().new ModificarPlanoConstruccionController());
					retorno = "ABMPlanoConstruccion";
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnConsultarRegMejora_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado2();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el ListDataProvider adecuado.
					Object obj = this.getLdpRegistroMejoraParcela().getObjects()[index];
					RegistroMejora registroMejora = (RegistroMejora) obj;

					this.guardarEstadoObjetosUsados();
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(0, Parcela.class));
					this.getRequestBean1().setObjetoABM(registroMejora);
					this.getRequestBean1().setAbmController(new RegistroMejoraModel().new ConsultarRegistroMejoraController());
					retorno = "ABMRegistroMejora";
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}
	
	public String btnConsultarPlanoConstruccion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionadoPlanoConstruccion();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el ListDataProvider adecuado.
					Object obj = this.getLdpPlanoConstruccion().getObjects()[index];
					PlanoConstruccion planoConstruccion = (PlanoConstruccion) obj;

					this.guardarEstadoObjetosUsados();
					this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
					this.getRequestBean1().setObjetoSeleccion(this.obtenerObjetoDelElementoPila(0, Parcela.class));
					this.getRequestBean1().setObjetoABM(planoConstruccion);
					this.getRequestBean1().setAbmController(new PlanoConstruccionModel().new ConsultarPlanoConstruccionController());
					retorno = "ABMPlanoConstruccion";
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarRegMejora_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		// List listaRegistroMejoraNuevos = (List)
		// this.obtenerObjetoDelElementoPila(6, ArrayList.class);
		// List listaRegistroMejoraAEliminar = (List)
		// this.obtenerObjetoDelElementoPila(10, ArrayList.class);
		List listaRegistrosMejora = this.getListaDelCommunication2();
		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado2();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el ListDataProvider adecuado.
					Object obj = this.getLdpRegistroMejoraParcela().getObjects()[index];
					RegistroMejora registroMejora = (RegistroMejora) obj;

					/*
					 * if (registroMejora.getIdRegistroMejora() != -1) { listaRegistroMejoraAEliminar.add(registroMejora); } else {
					 * listaRegistroMejoraNuevos.remove(registroMejora); }
					 */

					registroMejora.setParcela(null);
					this.getListaDelCommunication2().remove(registroMejora);
					this.getObjectListDataProvider2().setList(this.getListaDelCommunication2());
					listaRegistrosMejora.remove(this.getListaDelCommunication2());

					// this.getElementoPila().getObjetos().set(6,
					// listaRegistroMejoraNuevos);
					// this.getElementoPila().getObjetos().set(10,
					// listaRegistroMejoraAEliminar);

					this.getElementoPila().getObjetos().set(6, listaRegistrosMejora);
					Parcela parcela = this.obtenerObjetoDelElementoPila(0, Parcela.class);
					parcela.setListaRegistrosMejora(new HashSet<RegistroMejora>(this.getListaDelCommunication2()));

					info("El Registro de Mejora se elimin\363 exitosamente.");
				}
			} catch(Exception ex) {
				log("ModificarParcela" + "_EliminarRegistroMejoraError:", ex);
				error("Modificar Parcela" + " - Eliminar Registro de Mejora: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}
	
	public String btnQuitarPlanoConstruccion_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		List listaPlanoConstruccion = this.getListaDelCommunication6();
		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionadoPlanoConstruccion();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					// CAMBIAR: Utilizar el ListDataProvider adecuado.
					Object obj = this.getLdpPlanoConstruccion().getObjects()[index];
					PlanoConstruccion planoConstruccion = (PlanoConstruccion) obj;

					this.getListaDelCommunication6().remove(planoConstruccion);
					this.getLdpPlanoConstruccion().setList(this.getListaDelCommunication6());
					listaPlanoConstruccion.remove(this.getListaDelCommunication6());

					this.getElementoPila().getObjetos().set(19, listaPlanoConstruccion);
					Parcela parcela = this.obtenerObjetoDelElementoPila(0, Parcela.class);
					parcela.setListaPlanosConstruccion((this.getListaDelCommunication6()));

					info("El Plano Construcción se elimin\363 exitosamente.");
				}
			} catch(Exception ex) {
				log("ModificarParcela" + "_EliminarPlanoConstruccionError:", ex);
				error("Modificar Parcela" + " - Eliminar Plano Construcción: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarVolante_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				this.guardarEstadoObjetosUsados();
				Parcela parcela = this.obtenerObjetoDelElementoPila(0, Parcela.class);
				ArrayList listaVolanteCatastralNuevos = this.obtenerObjetoDelElementoPila(5, ArrayList.class);
				VolanteCatastral nuevoVolante = null;
				this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
				nuevoVolante = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().generarVolanteCatastral(parcela);
				if(nuevoVolante != null) {
					/*
					 * Solucion para el tema de los nros de volantes repetidos antes de persistirse:
					 */

					if(listaVolanteCatastralNuevos != null && listaVolanteCatastralNuevos.size() > 0) {
						Integer num = ((VolanteCatastral) listaVolanteCatastralNuevos.get(0)).getNroVolanteCatastral();
						for(Object obj : listaVolanteCatastralNuevos) {
							VolanteCatastral volanteCatastral = (VolanteCatastral) obj;
							if(volanteCatastral.getNroVolanteCatastral() > num) {
								num = volanteCatastral.getNroVolanteCatastral();
							}
						}
						num++;
						nuevoVolante.setNroVolanteCatastral(num);
					}

					listaVolanteCatastralNuevos.add(nuevoVolante);
					this.getListaDelCommunication3().add(nuevoVolante);
					this.getLdpVolanteCatastralParcela().setList(this.getListaDelCommunication3());
				}
			} catch(Exception ex) {
				log("ModificarParcela" + "_EliminarRegistroMejoraError:", ex);
				error("Modificar Parcela" + " - Eliminar Registro de Mejora: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnQuitarVolante_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		ArrayList listaVolanteCatastralNuevos = this.obtenerObjetoDelElementoPila(5, ArrayList.class);
		List listaVolantesAEliminar = this.obtenerObjetoDelElementoPila(9, ArrayList.class);

		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado3();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getLdpVolanteCatastralParcela().getObjects()[index];
					VolanteCatastral volanteCatastral = (VolanteCatastral) obj;

					if(volanteCatastral.getIdVolanteCatastral() != -1) {
						listaVolantesAEliminar.add(volanteCatastral);
					} else {
						listaVolanteCatastralNuevos.remove(volanteCatastral);
					}
					this.getListaDelCommunication3().remove(volanteCatastral);
					this.getObjectListDataProvider3().setList(this.getListaDelCommunication3());

					this.getElementoPila().getObjetos().set(5, listaVolanteCatastralNuevos);
					this.getElementoPila().getObjetos().set(9, listaVolantesAEliminar);

					info("El Volante Catastral se elimin\363 exitosamente.");
				}
			} catch(Exception ex) {
				log("ModificarParcela" + "_EliminarVolanteCatastralError:", ex);
				error("Modificar Parcela" + " - Eliminar Volante Catastral: " + ex.getMessage());
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public String btnAgregarZona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminZona";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarZona_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		Parcela parcela = this.obtenerObjetoDelElementoPila(0, Parcela.class);

		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());

					Object obj = this.getObjectListDataProvider5().getObjects()[index];

					AsociacionParcela asociacion = (AsociacionParcela) obj;

					if(this.getObjectListDataProvider5().getList().size() > 0) {
						this.getObjectListDataProvider5().commitChanges();
					}

					parcela.removeZona(asociacion);

					this.setListaDelCommunication5(parcela.getListaAsociacionParcela());
					this.getObjectListDataProvider5().setList(this.getListaDelCommunication5());
					this.getElementoPila().getObjetos().set(0, parcela);
				}
			} catch(Exception ex) {
				System.out.println("btnQuitar() entra al catch...");
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		}
		return retorno;
	}

	public String btnAgregarPersonaFisica_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminPersonaFisica";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnAgregarPersonaJuridica_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

			// CAMBIAR: Caso de navegacion de la pagina de administracion
			// correspondiente.
			retorno = "AdminPersonaJuridica";
		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitar_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();
		Parcela parcela = this.obtenerObjetoDelElementoPila(0, Parcela.class);

		TituloPropiedadParcelario tituloPropiedad = parcela.getTituloPropiedad();
		List registros = tituloPropiedad.getListaRegistrosPropietarios();

		if(ultimo) {
			RowKey rk = null;
			try {
				rk = this.getSeleccionado();
				if(rk != null) {
					int index = getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider4().getObjects()[index];
					RegistroPropietario registroPropietario = (RegistroPropietario) obj;

					if(this.getObjectListDataProvider4().getList().size() > 0) {
						this.getObjectListDataProvider4().commitChanges();
					}
					
					registros.remove(registroPropietario);
					
					if(registroPropietario.getEncargadoDeObligaciones() && registros.size() > 0) {
						RegistroPropietario encargadoDesignado = (RegistroPropietario) registros.get(0);
						encargadoDesignado.setEncargadoDeObligaciones(true);
					}
					
					this.setListaDelCommunication4(registros);
					this.getObjectListDataProvider4().setList(this.getListaDelCommunication4());
				}
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	public String btnQuitarTodos_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {

			try {
				this.getListaDelCommunication4().clear();
				this.getObjectListDataProvider4().setList(this.getListaDelCommunication4());
			} catch(Exception ex) {
			}

			this.guardarEstadoObjetosUsados();
			this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());

		} else {
			retorno = this.prepararCaducidad();
		}

		return retorno;
	}

	// public String btnImprimirVolante_action() {
	// String retorno = null;
	// boolean ultimo = this.ultimoElementoPilaDeSubSesion();
	//
	// if (ultimo) {
	// RowKey rk = null;
	//
	// try {
	// rk = this.getSeleccionado3();
	// if (rk != null) {
	// int index = getNroFila(rk.toString());
	// // CAMBIAR: Utilizar el ListDataProvider adecuado.
	// Object obj = this.getLdpVolanteCatastralParcela().getObjects()[index];
	// this.getSessionBean1().setObjetoImpresion(obj);
	// }
	// } catch (Exception ex) {
	// log(CASO_NAVEGACION + "_ImprimirVolanteCatastralError:", ex);
	// error(NOMBRE_PAGINA + " - Imprimir Volante Catastral: " +
	// ex.getMessage());
	// }
	//
	// this.guardarEstadoObjetosUsados();
	// this.getRequestBean1().setIdSubSesion(this.getIdSubSesion());
	//
	// } else {
	// retorno = this.prepararCaducidad();
	// }
	// return retorno;
	// }

	public String btnImprimirReporte_action() {
		String retorno = null;
		boolean ultimo = this.ultimoElementoPilaDeSubSesion();

		if(ultimo) {
			try {
				RowKey rk = null;
				rk = this.getSeleccionado3();
				if(rk != null) {
					int index = this.getNroFila(rk.toString());
					Object obj = this.getObjectListDataProvider3().getObjects()[index];
					VolanteCatastral locVolanteCatastral = (VolanteCatastral) obj;
					// this.getSessionBean1().setObjetoImpresion(obj);
					this.getComunicationCatastroBean().getRemoteSystemReportesCatastro().setLlave(this.getSessionBean1().getLlave());

					JasperPrint jp = this.getComunicationCatastroBean().getRemoteSystemReportesCatastro().getReporteVolanteCatastral(locVolanteCatastral);

					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(ConstantesReportes.FORMATO_REPORTE, ConstantesReportes.PDF);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportName", "Reporte_InformacionVolanteCatastral");
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
				}
			} catch(Exception e) {
				log("ModificarParcela" + "_ReporteDinamicoError: ", e);
				error("Modificar Parcela" + " - ReporteDinamico: " + e.getMessage());
			}
		} else {
			retorno = this.prepararCaducidad();
		}
		return retorno;
	}

	public void valueChangeEvent(ValueChangeEvent event) {
		this.btnImprimirVolante.setDisabled(false);
	}

	@Override
	protected String getCasoNavegacion() {
		return "ABMParcela";
	}

	@Override
	public void destroy() {
		super.destroy();

		try {
			this.guardarEstadoObjetosUsados();
		} catch(Exception e) {
//			e.printStackTrace();
			// no imprimo stacktrace para q no joda (Nico: si lo imprimo porque me jodio mas que no lo imprima ¬¬)
		}
	}

	@Override
	protected void procesarObjetoSeleccion(Object pObject) {
		Parcela parcela = null;
		if(pObject instanceof Persona) {
			parcela = this.obtenerObjetoDelElementoPila(0, Parcela.class);
			Persona nuevaPersona = (Persona) pObject;

			// tituloPropiedad = (TituloPropiedadParcelario) this
			// .obtenerObjetoDelElementoPila(15,
			// TituloPropiedadParcelario.class);

			TituloPropiedadParcelario tituloPropiedad = parcela.getTituloPropiedad();

			List registrosGuardados = this.getListaDelCommunication4();

			RegistroPropietario deLaTabla = null;
			boolean esta = false;
			int i = 0;
			while(i < registrosGuardados.size() && !esta) {
				deLaTabla = (RegistroPropietario) registrosGuardados.get(i);
				esta = (deLaTabla.getPersona().getIdPersona() == nuevaPersona.getIdPersona());
				i++;
			}
			if(!esta) {
				RegistroPropietario nuevoReg = new RegistroPropietario();
				nuevoReg.setPersona(nuevaPersona);
				nuevoReg.setTituloPropiedad(tituloPropiedad);
				if(registrosGuardados.size() == 0) {
					nuevoReg.setPorcentaje(100.00f);
					nuevoReg.setEncargadoDeObligaciones(true);
				} else {
					nuevoReg.setPorcentaje(0.0f);
					nuevoReg.setEncargadoDeObligaciones(false);
				}
				registrosGuardados.add(nuevoReg);
			} else {
				warn("La Persona que intenta agregar ya se encuentra en la lista.");
			}
			tituloPropiedad.setListaRegistrosPropietarios(registrosGuardados);
			// this.getElementoPila().getObjetos().set(16, registros);
			// this.getElementoPila().getObjetos().set(15, tituloPropiedad);
			// parcela.setTituloPropiedad(tituloPropiedad);
			this.getRequestBean1().setObjetoSeleccion(null);
		}

		if(pObject instanceof Zona) {
			parcela = this.obtenerObjetoDelElementoPila(0, Parcela.class);
			Zona nuevaZona = (Zona) pObject;
			AsociacionParcela asociacionExistente = null;
			boolean esta = false;
			int i = 0;
			while(i < parcela.getListaAsociacionParcela().size() && !esta) {
				asociacionExistente = parcela.getListaAsociacionParcela().get(i);
				esta = (asociacionExistente.getZona().equals(nuevaZona));
				i++;
			}
			if(!esta) {
				parcela.addZona(nuevaZona);
			} else {
				warn("La Zona que intenta agregar ya se encuentra en la lista.");
			}
			this.getElementoPila().getObjetos().set(0, parcela);
			this.getRequestBean1().setObjetoSeleccion(null);
		}
		// /Agregado

		if(parcela == null) {
			parcela = new Parcela();
		}

		if(pObject instanceof Manzana) {
			Manzana manzana = (Manzana) pObject;

			parcela.setManzana(manzana);

			List cuadras = null;
			try {
				this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
				cuadras = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getListaCuadrasPorParcela(parcela);
			} catch(Exception ex) {
				error("No se pudieron obtener las Cuadras de la Manzana: " + ex.getMessage());
			}
			this.getElementoPila().getObjetos().set(2, cuadras);
			this.getElementoPila().getObjetos().set(3, manzana);

		} else if(pObject instanceof RegistroMejora) {
			RegistroMejora registroMejora = (RegistroMejora) pObject;
			// Parcela locParcela = (Parcela)
			// this.obtenerObjetoDelElementoPila(0, Parcela.class);
			List listaRegistroMejora = this.obtenerObjetoDelElementoPila(6, List.class);

			if(parcela.getListaRegistrosMejora() == null) {
				parcela.setListaRegistrosMejora(new HashSet<RegistroMejora>());
			}

			parcela.getListaRegistrosMejora().add(registroMejora);
			listaRegistroMejora.add(registroMejora);
			// this.getListaDelCommunication2().add(registroMejora);
			// this.getElementoPila().getObjetos().set(0, parcela);
			this.getElementoPila().getObjetos().set(6, listaRegistroMejora);

		} else if(pObject instanceof VolanteCatastral) {
			// el volante se agrega a la lista en el mismo boton de
			// agregarvolante
			// VolanteCatastral volanteCatastral = (VolanteCatastral)
			// seleccionado;
			// System.out.println("instanceof volanteC - " +
			// volanteCatastral);
			// listaVolanteCatastral = (List)
			// this.obtenerObjetoDelElementoPila(5, List.class);
			// listaVolanteCatastral.add(volanteCatastral);
			// System.out.println("tam lista despues de add volante -- " +
			// listaVolanteCatastral.size());
			// this.getElementoPila().getObjetos().set(5,
			// listaVolanteCatastral);
		}else if(pObject instanceof PlanoConstruccion) {
			PlanoConstruccion planoConstruccion = (PlanoConstruccion) pObject;

			List listaPlanoConstruccion = this.obtenerObjetoDelElementoPila(19, List.class);

			if( parcela.getListaPlanosConstruccion() == null) {
				parcela.setListaPlanosConstruccion(new ArrayList<PlanoConstruccion>());  
			}
			parcela.getListaPlanosConstruccion().add(planoConstruccion);
			listaPlanoConstruccion.add(planoConstruccion);
			this.getElementoPila().getObjetos().set(19, listaPlanoConstruccion);
		}
		
		this.getRequestBean1().setObjetoSeleccion(null);
	}

	@Override
	protected void procesarObjetoABM(Object pObject) {
		// si la parcela no tiene titulo de propiedad no agregar
		// subdivisiones:
		Parcela parcela = (Parcela) pObject;
		if(parcela.getIdParcela() == -1) {
			this.getElementoPila().getObjetos().set(0, parcela);
			this.getElementoPila().getObjetos().set(4, parcela.getNomenclaturaCatastral());
			return;
		}
		Domicilio domicilio = parcela.getDomicilioParcelario();
		Manzana manzana = parcela.getManzana();
		NomenclaturaCatastral nomenclaturaCatastral = parcela.getNomenclaturaCatastral();
		List registros = new ArrayList();
		List listaVolanteCatastral = null;
		List listaZona = new ArrayList();
		List cuadras = null;
		List listaRegistroMejora = new ArrayList();
		List listaObligacionesOSP = null;
		List atributosDinamicos = null;
		List atributosDinamicosTituloPropiedad = null;
		List listaPlanoConstruccion = new ArrayList();
		
		TituloPropiedadParcelario tituloPropiedad = parcela.getTituloPropiedad();

		// tipoTransac = titulo.getTipoTransaccionCatastral();

		for(Object obj : tituloPropiedad.getListaRegistrosPropietarios()) {
			RegistroPropietario locRegistroPropietario = (RegistroPropietario) obj;
			if(locRegistroPropietario.getPersona().getEstado().equals(Persona.Estado.ACTIVO)) {
				registros.add(obj);
			}
		}

		try {
			this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
			cuadras = this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getListaCuadrasPorParcela(parcela);
		} catch(Exception ex) {
			error("No se pudieron obtener las Cuadras de la Manzana: " + ex.getMessage());
		}

		try {
			this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
			this.setListaDelCommunication3((ArrayList) this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().findVolanteCatastral(null, parcela));
			this.getLdpVolanteCatastralParcela().setList(this.getListaDelCommunication3());
		} catch(Exception ex) {
			log("ModificarParcela" + "_findVolanteCatastral: ", ex);
			error("No se pudieron obtener los Volantes Catastrales de la Parcela: " + ex.getMessage());
		}

		try {
			this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
			this.setListaDelCommunicationObligaciones(this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion()
					.findListaObligaciones(null, null, null, parcela, null));
			this.getLdpObligaciones().setList(this.getListaDelCommunicationObligaciones());

		} catch(Exception ex) {
			error("No se pudieron obtener las Obligaciones de la Parcela: " + ex.getMessage());
		}

		try {
			this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
			this.setListaDelCommunication2(new ArrayList<RegistroMejora>(parcela.getListaRegistrosMejora()));
			this.getLdpRegistroMejoraParcela().setList(this.getListaDelCommunication2());
			listaRegistroMejora = this.getLdpRegistroMejoraParcela().getList();
		} catch(Exception ex) {
			log("ModificarParcela" + "_findRegistroMejora: ", ex);
			error("No se pudieron obtener los Registros de Mejora de la Parcela: " + ex.getMessage());
		}
		
		try {
			this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().setLlave(this.getSessionBean1().getLlave());
			this.setListaDelCommunication6(new ArrayList<PlanoConstruccion>(parcela.getListaPlanosConstruccion()));
			this.getLdpPlanoConstruccion().setList(this.getListaDelCommunication6());
			listaPlanoConstruccion = this.getLdpPlanoConstruccion().getList();
		} catch(Exception ex) {
//			log("ModificarParcela" + "_findRegistroMejora: ", ex);
			error("No se pudieron obtener los Planos Construcción de la Parcela: " + ex.getMessage());
		}

		// Agregado
		try {
			this.setListaDelCommunication4(new ArrayList<RegistroPropietario>(tituloPropiedad.getListaRegistrosPropietarios()));
			this.getLdpRegistroPropietario().setList(this.getListaDelCommunication4());
			registros = this.getLdpRegistroPropietario().getList();
		} catch(Exception ex) {
			log("ModificarParcela" + "_findRegistroPropietario: ", ex);
			error("No se pudieron obtener los Registros de Propietario de la Parcela: " + ex.getMessage());
		}

		try {
			this.setListaDelCommunication5(parcela.getListaAsociacionParcela());
			this.getLdpZonas().setList(this.getListaDelCommunication5());
			listaZona = this.getLdpZonas().getList();
		} catch(Exception ex) {
			log("ModificarParcela" + "_findZona: ", ex);
			error("No se pudieron obtener las Zonas de la Parcela: " + ex.getMessage());
		}

		// Comentado
		// try {
		// this.getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().setLlave(this.getSessionBean1().getLlave());
		// zonas = (List)
		// this.getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().getListaZonasFromParcela(parcela);
		//
		// } catch (Exception e) {
		// }

		// this.setListaDelCommunication4(new
		// ArrayList(parcela.getListaSubParcelas()));
		// this.getLdpSubdivisiones().setList(this.getListaDelCommunication4());

		// //obtengo obligaciones OSP
		// try {
		// this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().setLlave(this.getSessionBean1().getLlave());
		// listaObligacionesOSP.addAll((ArrayList)
		// this.getCommunicationHabilitacionesBean().getRemoteSystemObligacion().findListaObligacionesOSP(null,
		// Conversor.getIntegerDeString(parcela.getNroRegistro()), null,
		// null));
		// } catch (Exception ex) {
		// log(CASO_NAVEGACION + "_findObligacionesOSP: ", ex);
		// error("No se pudieron obtener los Registros de Obligaciones OSP: "
		// + ex.getMessage());
		// }

		// Zonas:
		String stringListaZonas = "<b>Otras asociaciones (Por manzana, cuadra, calle):</b><br/>";
		try {
			this.getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().setLlave(this.getSessionBean1().getLlave());
			List<Zona> listaZonas;
			if(parcela != null && parcela.getIdParcela() != -1) {
				listaZonas = this.getComunicationCatastroBean().getRemoteSystemAdministracionZonificacion().getListaZonasFromParcelaSinLimitar(parcela);

				if(listaZonas != null && listaZonas.size() > 0) {

					for(AsociacionParcela cadaAsociacion : parcela.getListaAsociacionParcela()) {
						listaZonas.remove(cadaAsociacion.getZona());
					}

					if(listaZonas.size() == 0) {
						stringListaZonas += "Ning\372na Zona Asociada.";
					} else {
						stringListaZonas += "<ol>";
						for(Zona zona : listaZonas) {
							stringListaZonas += "<li>" + zona.getNombre().toString() + " (" + zona.getZonificacion().getNombre() + ") " + "</li>";
						}
						stringListaZonas += "</ol>";
					}

				} else {
					stringListaZonas += "Ning\372na Zona Asociada.";
				}
			} else {
				stringListaZonas += "Ning\372na Zona Asociada.";
			}

			// this.getStZonas().setText(stringListaZonas);

		} catch(Exception ex) {
			this.getStZonas().setText(stringListaZonas + "Ha ocurrido un error intentando recuperar las zonas." + "<br/><br/>" + ex.getMessage());
		}
		this.getElementoPila().getObjetos().set(15, stringListaZonas);

		if(parcela.getListaAtributosDinamicos() != null) {
			try {
				atributosDinamicos = this.getComunicationBean().getRemoteSystemParametro().getAtributosPorRecurso(Parcela.serialVersionUID, parcela.getListaAtributosDinamicos(), null);
				this.getElementoPila().getObjetos().set(14, atributosDinamicos);
				parcela.setListaAtributosDinamicos(atributosDinamicos);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}

		int ind = 0;
		this.getElementoPila().getObjetos().set(ind++, parcela);
		this.getElementoPila().getObjetos().set(ind++, domicilio);
		this.getElementoPila().getObjetos().set(ind++, cuadras);
		this.getElementoPila().getObjetos().set(ind++, manzana);
		this.getElementoPila().getObjetos().set(ind++, nomenclaturaCatastral);
		this.getElementoPila().getObjetos().set(ind++, listaVolanteCatastral);
		this.getElementoPila().getObjetos().set(ind++, listaRegistroMejora);
		this.getElementoPila().getObjetos().set(11, listaObligacionesOSP);
		this.getElementoPila().getObjetos().set(19, listaPlanoConstruccion);
	}

	@Override
	protected void _prerender() throws Exception {
		super._prerender();

		try {

			String idTab = this.getTabSet1().getSelected();
			Parcela parcela = this.obtenerObjetoDelElementoPila(0, Parcela.class);
			// NomenclaturaCatastral nomenclaturaCatastral = parcela.getNomenclaturaCatastral();
			// nomenclaturaCatastral.setNroParcela(this.getComunicationCatastroBean().getRemoteSystemInformacionParcelaria().getSugerenciaNumeroParcela().toString());
			// this.getTfNroParcela().setText(nomenclaturaCatastral.getNroParcela().toString());

			if(idTab != null) {
				if (idTab.equals("one")) {
					this.getTablaLogs().setRendered(false);
				} else if(idTab.equals("two")) {
					this.getTablaLogs().getLdpLogs().setList(parcela.getListaLogsAuditoria());
				} else if(idTab.equals("three") && parcela.getTituloPropiedad() != null) {
					this.getTablaLogs().getLdpLogs().setList(parcela.getTituloPropiedad().getListaLogsAuditoria());
				} else if(idTab.equals("four") && parcela.getPlanoMensura() != null) {
					this.getTablaLogs().getLdpLogs().setList(parcela.getPlanoMensura().getListaLogsAuditoria());
				} else if(idTab.equals("five") && parcela.getPlanoConstruccion() != null) {
					this.getTablaLogs().setRendered(false);
					this.getTablaLogs().getLdpLogs().getList().clear();
				} else if(idTab.equals("six")) {
					this.getTablaLogs().setRendered(false);
					this.getTablaLogs().getLdpLogs().getList().clear();
				}
				for (RegistroPropietario cadaRegistro : parcela.getTituloPropiedad().getListaRegistrosPropietarios()){
					if (cadaRegistro.getEncargadoDeObligaciones() != false){
						this.seleccionarFilaEncargado(cadaRegistro);
					}
				} 
				this.guardarEstadoObjetosUsados();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public long getSerialVersionUID() {
		return Parcela.serialVersionUID;
	}

	@Override
	public String getNombreBean() {
		return "#{catastro$ABMParcela$ABMParcela}";
	}

	@Override
	public String btnGuardar_action() {
		String retorno = super.btnGuardar_action();
		String tabSelected = this.getTabSet1().getSelected();
		for(UIComponent cadaTab : this.getTabSet1().getChildren()) {
			if(!cadaTab.getId().equals(tabSelected)) {
				for(UIComponent cadaComponente : cadaTab.getChildren()) {
					if(cadaComponente instanceof UIInput && ((UIInput) cadaComponente).isValid() == false) {
						((UIInput) cadaComponente).setValid(true);
					}
				}
			}
		}

		if(!tabSelected.equals("one")) {
			((UIInput) this.getTfManzanaCuadra()).setValid(true);
		}
		if(!tabSelected.equals("two")) {
			this.setearValidAtributosDinamicos(this.getPanelAtributoDinamico());
		}
		if(!tabSelected.equals("three")) {
			this.setearValidAtributosDinamicos(this.getPanelAtributoDinamicoTituloPropiedad());
		}
		if(!tabSelected.equals("four")) {
			this.setearValidAtributosDinamicos(this.getPanelAtributoDinamicoPlanoMensura());
		}
		if(!tabSelected.equals("five")) {
			this.setearValidAtributosDinamicos(this.getPanelAtributoDinamicoTituloPropiedad());
		}

		return retorno;
	}

	private void setearValidAtributosDinamicos(PanelAtributoDinamico pPanel) {
		for(UIComponent cadaComponente : pPanel.getChildren().get(0).getChildren()) {
			if(cadaComponente instanceof UIInput) {
				((UIInput) cadaComponente).setValid(true);
			}
		}
	}
}