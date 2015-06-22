<?xml version="1.0" encoding="UTF-8"?>
<!-- © Copyright 2015, CoDeSoft Todos los derechos reservados. -->
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui"
	xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.page1}" id="page1">
			<ui:html binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.html1}" id="html1">
			<ui:head binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.head1}" id="head1" title="Armar Plan de Pago de la Refinanciación">
				<ui:script id="jQuery" url="/resources/javascript/jQuery/jQuery.js" />
				<ui:script id="jQuery-UI" url="/resources/javascript/jQuery/jQuery-UI.js" />
				<ui:link id="jQuery-UI-Style" url="/resources/javascript/jQuery/jQuery-UI.css" />
				<ui:script id="maskedInput" url="/resources/javascript/jQuery/maskedInputPlugin.js" />
				<ui:link binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[

					var _decimales = 2;

					function cargarComportamientoJQuery() {
						calendarioEnTextField("#form1:tfFechaVencimiento");
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});

					function getById(id) {
						elem = document.getElementById(id);
						return elem;
					}

					function formatNumber(number) {
						number = Math.round(number * Math.pow(10, _decimales)) / Math.pow(10, _decimales);
						cantDecimales = 0;
						if (number.toString().indexOf(".") >= 0)
							cantDecimales = number.toString().substring(number.toString().indexOf(".") + 1).length;
						if (number == Math.floor(number))
							number = number + ".";
						for (i = 0; i < (_decimales - cantDecimales); i++)
							number += "0";
						return number;
					}

					function getValorDePorcentaje(valor, porcentaje) {
						return (valor * porcentaje / 100);
					}

					function sumarValores() {
						var total = 0.00;
						for (i = 0; i < sumarValores.arguments.length; i++) {
							total += parseFloat(sumarValores.arguments[i], _decimales);
						}
						return total;
					}

					function formatearCamposPorCalculoCuotas() {
						tfInteresesAPagar = getById("form1:tfInteresesAPagar");
						tfTotalAPagar = getById("form1:tfTotalAPagar");

						tfInteresesAPagar.value = formatNumber(tfInteresesAPagar.value);
						tfTotalAPagar.value = formatNumber(tfTotalAPagar.value);
					}

					function calcularCondonacionImporte() {
						tfImporte = getById("form1:tfImporte");
						tfImporteACondonar = getById("form1:tfImporteACondonar");
						tfImporteCondonado = getById("form1:tfImporteCondonado");
						rbPorcentual = getById("form1:rbCondonarImportePorc");

						if (rbPorcentual.checked) {
							tfImporteCondonado.value = formatNumber(getValorDePorcentaje(tfImporte.value, tfImporteACondonar.value));
						} else {
							tfImporteCondonado.value = tfImporteACondonar.value;
						}
					}

					function calcularCondonacionIntereses() {
						tfIntereses = getById("form1:tfIntereses");
						tfInteresesACondonar = getById("form1:tfInteresesACondonar");
						tfInteresesCondonado = getById("form1:tfInteresesCondonado");
						rbPorcentual = getById("form1:rbCondonarInteresesPorc");

						if (rbPorcentual.checked) {
							tfInteresesCondonado.value = formatNumber(getValorDePorcentaje(tfIntereses.value, tfInteresesACondonar.value));
						} else {
							tfInteresesCondonado.value = tfInteresesACondonar.value;
						}
					}

					function calcularCondonacionRecargos() {
						tfRecargos = getById("form1:tfRecargos");
						tfRecargosACondonar = getById("form1:tfRecargosACondonar");
						tfRecargosCondonado = getById("form1:tfRecargosCondonado");
						rbPorcentual = getById("form1:rbCondonarRecargosPorc");

						if (rbPorcentual.checked) {
							tfRecargosCondonado.value = formatNumber(getValorDePorcentaje(tfRecargos.value, tfRecargosACondonar.value));
						} else {
							tfRecargosCondonado.value = tfRecargosACondonar.value;
						}
					}

					function calcularCondonacionMultas() {
						tfMultas = getById("form1:tfMultas");
						tfMultasACondonar = getById("form1:tfMultasACondonar");
						tfMultasCondonado = getById("form1:tfMultasCondonado");
						rbPorcentual = getById("form1:rbCondonarMultasPorc");

						if (rbPorcentual.checked) {
							tfMultasCondonado.value = formatNumber(getValorDePorcentaje(tfMultas.value, tfMultasACondonar.value));
						} else {
							tfMultasCondonado.value = tfMultasACondonar.value;
						}
					}

					function sumarTotalARefinanciar() {
						tfImporte = getById("form1:tfImporte");
						tfIntereses = getById("form1:tfIntereses");
						tfRecargos = getById("form1:tfRecargos");
						tfMultas = getById("form1:tfMultas");
						tfTotalARefinanciar = getById("form1:tfTotalARefinanciar");

						tfSaldoAFavor = getById("form1:tfSaldoAFavor");

						tfTotalARefinanciar.value = sumarValores(tfImporte.value, tfIntereses.value, tfRecargos.value, tfMultas.value) - parseFloat(sumarTotalCondonado(), _decimales)
								- parseFloat(tfSaldoAFavor.value, _decimales);
						tfTotalARefinanciar.value = formatNumber(tfTotalARefinanciar.value);
					}

					function sumarTotalCondonado() {
						tfImporteCondonado = getById("form1:tfImporteCondonado");
						tfInteresesCondonado = getById("form1:tfInteresesCondonado");
						tfRecargosCondonado = getById("form1:tfRecargosCondonado");
						tfMultasCondonado = getById("form1:tfMultasCondonado");
						tfTotalCondonado = getById("form1:tfTotalCondonado");
						tfTotalCondonado.value = sumarValores(tfImporteCondonado.value, tfInteresesCondonado.value, tfRecargosCondonado.value, tfMultasCondonado.value);
						tfTotalCondonado.value = formatNumber(tfTotalCondonado.value);
						return tfTotalCondonado.value;
					}

					function calcularCondonaciones() {
						calcularCondonacionImporte();
						calcularCondonacionIntereses();
						calcularCondonacionRecargos();
						calcularCondonacionMultas();
						sumarTotalCondonado();
						sumarTotalARefinanciar();

						formatearCamposPorCalculoCuotas();
					}

					]]>
				</script>
			</ui:head>
			<ui:body binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.body1}" focus="form1:tfImporteACondonar" id="body1" onLoad="parent.footer.location.reload();Init();"
				style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td>
										<table align="center" width="70%">
											<tr>
												<td colspan="4">
													<br />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label id="lblPlantillaPlanDePago" text="Plantilla" for="ddPlantillaPlanDePago" styleClass="label"
														binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblPlantillaPlanDePago}" />
												</td>
												<td align="left" colspan="3" nowrap="nowrap">
													<ui:dropDown binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.ddPlantillaPlanDePago}" id="ddPlantillaPlanDePago" styleClass="textField"
														items="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.ddPlantillaPlanDePagoOptions.options}">
														<a4j:support event="onChange" oncomplete="calcularCondonaciones(); cargarComportamientoJQuery();"
															reRender="form1:tfImporteACondonar, form1:tfInteresACondonar, form1:rbCondonarImportePorc,
														form1:rbCondonarImporteFijo, form1:rbCondonarInteresesPorc, form1:rbCondonarInteresesFijo
														form1:tfInteresesACondonar, form1:rbCondonarInteresesFijo,
														form1:tfCantidadCuotas, form1:tfTasaNominalAnual,
														form1:tfInteresPunitorio, form1:tfFechaVencimiento, form1:tfCantidadDiasCaida, form1:tfCantidadCuotasCaida, form1"
															actionListener="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.eventoSeleccionPlantilla(evento)}" />
													</ui:dropDown>
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblContribuyente}" for="tfContribuyente" id="lblContribuyente" styleClass="label"
														text="Contribuyente" />
												</td>
												<td align="left" colspan="3" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfContribuyente}" columns="40" disabled="true" id="tfContribuyente"
														styleClass="textFieldDisabled" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblSuscriptor}" for="tfSuscriptor" id="lblSuscriptor" styleClass="label" text="Suscriptor" />
												</td>
												<td align="left" colspan="3" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfSuscriptor}" columns="40" id="tfSuscriptor" disabled="true" styleClass="textFieldDisabled" />
													<ui:button action="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.btnSeleccionarPersonaFisica_action}"
														binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.btnSeleccionarPersona}" escape="false" id="btnSeleccionarPersona" mini="true"
														styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblDigesto}" for="tfDigesto" id="lblDigesto" styleClass="label"
														text="Decreto, Ordenanza, Resolución" />
												</td>
												<td align="left" colspan="3" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfDigesto}" columns="60" disabled="true" id="tfDigesto" styleClass="textField" />
													<ui:button action="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.btnSeleccionarDigesto_action}"
														binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.btnSeleccionarDigesto}" escape="false" id="btnSeleccionarDigesto" mini="true"
														styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
													<ui:button action="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.btnLimpiarDigesto_action}"
														binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.btnLimpiarDigesto}" escape="false" id="btnLimpiarDigesto" mini="true" styleClass="buttonLimpiar"
														text="&amp;nbsp;" toolTip="Limpiar" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<br />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblPeriodosSeleccionados}" for="tfPeriodosSeleccionados" id="lblPeriodosSeleccionados"
														styleClass="label" text="Períodos Seleccionados" />
												</td>
												<td>
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfPeriodosSeleccionados}" columns="4" disabled="true" id="tfPeriodosSeleccionados"
														styleClass="textFieldDisabled" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.label2}" id="label2" styleClass="label2" text="Condonar" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.label3}" id="label3" styleClass="label2" text="Condonado" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblImporte}" for="tfImporte" id="lblImporte" styleClass="label" text="Importe" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfImporte}" columns="10" disabled="true" id="tfImporte"
														style="text-align:right; padding-right:6px;" styleClass="textFieldDisabled" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblImporteACondonar}" for="tfImporteACondonar" id="lblImporteACondonar" styleClass="label"
														text=" " />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfImporteACondonar}" columns="10" id="tfImporteACondonar" onKeyUp="calcularCondonaciones()"
														style="text-align:right; padding-right:6px;" styleClass="textField" />
													<ui:radioButton binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.rbCondonarImportePorc}" id="rbCondonarImportePorc" name="rbgImporte"
														onClick="calcularCondonaciones()" />
													<sup> <ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.label9}" for="rbCondonarImportePorc" id="label9" styleClass="label" text="%" />
													</sup>
													<ui:radioButton binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.rbCondonarImporteFijo}" id="rbCondonarImporteFijo" name="rbgImporte"
														onClick="calcularCondonaciones()" selected="true" />
													<sup> <ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.label10}" for="rbCondonarImporteFijo" id="label10" styleClass="label" text="$" />
													</sup>
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfImporteCondonado}" columns="10" id="tfImporteCondonado" onKeyUp="calcularCondonaciones()"
														style="text-align:right; padding-right:6px;" styleClass="textFieldDisabled" text="0.00" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblIntereses}" for="tfIntereses" id="lblIntereses" styleClass="label" text="Intereses" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfIntereses}" columns="10" disabled="true" id="tfIntereses"
														style="text-align:right; padding-right:6px;" styleClass="textFieldDisabled" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblInteresesACondonar}" for="tfInteresesACondonar" id="lblInteresesACondonar"
														styleClass="label    " text=" " />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfInteresesACondonar}" columns="10" id="tfInteresesACondonar"
														onKeyUp="calcularCondonaciones()" style="text-align:right; padding-right:6px;" styleClass="textField" />
													<ui:radioButton binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.rbCondonarInteresesPorc}" id="rbCondonarInteresesPorc" name="rbgIntereses"
														onClick="calcularCondonaciones()" />
													<sup> <ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.label11}" for="rbCondonarInteresesPorc" id="label11" styleClass="label" text="%" />
													</sup>
													<ui:radioButton binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.rbCondonarInteresesFijo}" id="rbCondonarInteresesFijo" name="rbgIntereses"
														onClick="calcularCondonaciones()" selected="true" />
													<sup> <ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.label12}" for="rbCondonarInteresesFijo" id="label12" styleClass="label" text="$" />
													</sup>
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfInteresesCondonado}" columns="10" id="tfInteresesCondonado"
														onKeyUp="calcularCondonaciones()" style="text-align:right; padding-right:6px;" styleClass="textFieldDisabled" text="0.00" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblRecargos}" for="tfRecargos" id="lblRecargos" styleClass="label" text="Recargos" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfRecargos}" columns="10" disabled="true" id="tfRecargos"
														style="text-align:right; padding-right:6px;" styleClass="textFieldDisabled" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblRecargosACondonar}" for="tfRecargosACondonar" id="lblRecargosACondonar" styleClass="label    "
														text=" " />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfRecargosACondonar}" columns="10" id="tfRecargosACondonar" onKeyUp="calcularCondonaciones()"
														style="text-align:right; padding-right:6px;" styleClass="textField" />
													<ui:radioButton binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.rbCondonarRecargosPorc}" id="rbCondonarRecargosPorc" name="rbgRecargos"
														onClick="calcularCondonaciones()" />
													<sup> <ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.label13}" for="rbCondonarRecargosPorc" id="label13" styleClass="label" text="%" />
													</sup>
													<ui:radioButton binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.rbCondonarRecargosFijo}" id="rbCondonarRecargosFijo" name="rbgRecargos"
														onClick="calcularCondonaciones()" selected="true" />
													<sup> <ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.label14}" for="rbCondonarRecargosFijo" id="label14" styleClass="label" text="$" />
													</sup>
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfRecargosCondonado}" columns="10" id="tfRecargosCondonado" onKeyUp="calcularCondonaciones()"
														style="text-align:right; padding-right:6px;" styleClass="textFieldDisabled" text="0.00" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblMultas}" for="tfMultas" id="lblMultas" styleClass="label" text="Multas" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfMultas}" columns="10" disabled="true" id="tfMultas"
														style="padding-right: 6px; text-align: right" styleClass="textFieldDisabled" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblMultasACondonar}" for="tfMultasACondonar" id="lblMultasACondonar" styleClass="label    "
														text=" " />
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfMultasACondonar}" columns="10" id="tfMultasACondonar" onKeyUp="calcularCondonaciones()"
														style="padding-right: 6px; text-align: right" styleClass="textField" />
													<ui:radioButton binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.rbCondonarMultasPorc}" id="rbCondonarMultasPorc" name="rbgMultas"
														onClick="calcularCondonaciones()" />
													<sup> <ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.label22}" for="rbCondonarMultasPorc" id="label22" styleClass="label" text="%" />
													</sup>
													<ui:radioButton binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.rbCondonarMultasFijo}" id="rbCondonarMultasFijo" name="rbgMultas"
														onClick="calcularCondonaciones()" selected="true" />
													<sup> <ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.label23}" for="rbCondonarMultasFijo" id="label23" styleClass="label" text="$" />
													</sup>
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfMultasCondonado}" columns="10" id="tfMultasCondonado" onKeyUp="calcularCondonaciones()"
														style="padding-right: 6px; text-align: right" styleClass="textFieldDisabled" text="0.00" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblSaldoAFavor}" for="tfSaldoAFavor" id="lblSaldoAFavor" styleClass="label" text="Saldo a Favor" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfSaldoAFavor}" columns="10" id="tfSaldoAFavor" onKeyUp="sumarTotalARefinanciar()"
														style="padding-right: 6px; text-align: right" styleClass="textField" />
												</td>
												<td align="left" nowrap="nowrap"></td>
												<td align="left" nowrap="nowrap"></td>
											</tr>
											<tr>
												<td align="right" colspan="2" nowrap="nowrap">
													<hr />
												</td>
												<td align="right" colspan="2" nowrap="nowrap">
													<hr />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap"></td>
												<td align="left" nowrap="nowrap"></td>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblTotalCondonado}" for="tfTotalCondonado" id="lblTotalCondonado" styleClass="label2"
														text="Total Condonado" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfTotalCondonado}" columns="10" disabled="true" id="tfTotalCondonado"
														style="text-align:right; padding-right:6px;" styleClass="textFieldDisabled" text="0.00" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap"></td>
												<td align="left" nowrap="nowrap"></td>
												<td align="right" nowrap="nowrap"></td>
												<td align="left" nowrap="nowrap"></td>
											</tr>
											<tr>
												<td colspan="4">
													<hr />
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.label1}" id="label1" styleClass="label2" text="Generación de las Cuotas" />
													<br />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblCantidadCuotas}" for="tfCantidadCuotas" id="lblCantidadCuotas" styleClass="label"
														text="Cantidad de Cuotas" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfCantidadCuotas}" columns="6" id="tfCantidadCuotas"
														onKeyPress="return ValidarNum(event,this)" style="&lt;Valores diferentes&gt;" styleClass="textField">
														<a4j:support event="onChange" actionListener="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.cantidadCuotasActionListener}"
															reRender="tfTasaNominalAnual"/>
													</ui:textField>
												</td>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblCapitalARefinanciar}" for="tfTotalARefinanciar" id="lblCapitalARefinanciar" styleClass="label2"
														text="Capital a refinanciar" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfTotalARefinanciar}" columns="10" id="tfTotalARefinanciar" onKeyUp="calcularCondonaciones()"
														style="font-weight: bold; padding-right: 6px; text-align: right" styleClass="textFieldDisabled" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblTasaNominalAnual}" for="tfTasaNominalAnual" id="lblTasaNominalAnual" styleClass="label"
														text="Tasa Nominal Anual" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfTasaNominalAnual}" columns="6" onKeyPress="return ValidarFloat(event,this)"
														id="tfTasaNominalAnual" style="&lt;Valores diferentes&gt;" styleClass="textField" />
													<ui:staticText binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.staticText5}" escape="false" id="staticText5" styleClass="label" text=" [% anual]" />
												</td>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblInteresesAPagar}" for="tfInteresesAPagar" id="lblInteresesAPagar" styleClass="label2"
														text="Intereses" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfInteresesAPagar}" columns="10" disabled="true" id="tfInteresesAPagar"
														style="font-weight: bold; padding-right: 6px; text-align: right" styleClass="textFieldDisabled" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblInteresPunitorio}" for="tfInteresPunitorio" id="lblInteresPunitorio" styleClass="label"
														text="Interés Punitorio" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfInteresPunitorio}" columns="6" id="tfInteresPunitorio" style="&lt;Valores diferentes&gt;"
														styleClass="textField" />
													<ui:staticText binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.staticText6}" escape="false" id="staticText6" styleClass="label" text=" [diario]" />
												</td>
												<td align="right" nowrap="nowrap"></td>
												<td align="left" nowrap="nowrap">
													<hr />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblFechaVencimiento}" for="tfFechaVencimiento" id="lblFechaVencimiento" styleClass="label"
														text="Fecha de Vencimiento" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfFechaVencimiento}" columns="10" id="tfFechaVencimiento" styleClass="textField" />
													<ui:staticText escape="false" id="stFechaVencimiento" styleClass="label" text=" [Apartir de la 2da cuota]" />
												</td>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblTotalAPagar}" for="tfTotalAPagar" id="lblTotalAPagar" styleClass="label2" text="Total a Pagar" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfTotalAPagar}" columns="10" disabled="true" id="tfTotalAPagar"
														style="font-weight: bold; padding-right: 6px; text-align: right" styleClass="textFieldDisabled" />
												</td>
											</tr>
											<tr>
												<td align="right" colspan="4" nowrap="nowrap"></td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:table augmentTitle="false" binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.table1}" id="table1" width="192">
														<script>
															<![CDATA[
															/* ----- Functions for Table Preferences Panel ----- */
															/*
															 * Toggle the table preferences panel open or closed
															 */
															function togglePreferencesPanel() {
																var table = document.getElementById("form1:table1");
																table.toggleTblePreferencesPanel();
															}
															/* ----- Functions for Filter Panel ----- */
															/*
															 * Return true if the filter menu has actually changed,
															 * so the corresponding event should be allowed to continue.
															 */
															function filterMenuChanged() {
																var table = document.getElementById("form1:table1");
																return table.filterMenuChanged();
															}
															/*
															 * Toggle the custom filter panel (if any) open or closed.
															 */
															function toggleFilterPanel() {
																var table = document.getElementById("form1:table1");
																return table.toggleTableFilterPanel();
															}
															/* ----- Functions for Table Actions ----- */
															/*
															 * Initialize all rows of the table when the state
															 * of selected rows changes.
															 */
															function initAllRows() {
																var table = document.getElementById("form1:table1");
																table.initAllRows();
															}
															/*
															 * Set the selected state for the given row groups
															 * displayed in the table.  This functionality requires
															 * the 'selectId' of the tableColumn to be set.
															 *
															 * @param rowGroupId HTML element id of the tableRowGroup component
															 * @param selected Flag indicating whether components should be selected
															 */
															function selectGroupRows(rowGroupId, selected) {
																var table = document.getElementById("form1:table1");
																table.selectGroupRows(rowGroupId, selected);
															}
															/*
															 * Disable all table actions if no rows have been selected.
															 */
															function disableActions() {
																// Determine whether any rows are currently selected
																var table = document.getElementById("form1:table1");
																var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
																// Set disabled state for top actions
																document.getElementById("form1:table1:tableActionsTop:deleteTop").setDisabled(disabled);
																// Set disabled state for bottom actions
																document.getElementById("form1:table1:tableActionsBottom:deleteBottom").setDisabled(disabled);
															}
															]]>
														</script>
														<ui:tableRowGroup binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tableRowGroup1}" id="tableRowGroup1"
															sourceData="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.ldpCuotasRefinanciacion}" sourceVar="currentRow">
															<ui:tableColumn align="center" binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tableColumn1}" id="tableColumn1" rendered="false" valign="middle"
																width="10">
																<ui:radioButton binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.radioButton1}" id="radioButton1" label="" name="buttonGroup"
																	selected="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.RBSelected}" selectedValue="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.currentRow}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tableColumn3}" headerText="Nº" id="tableColumn3" sort="numeroCuota">
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.staticText2}" id="staticText2" text="#{currentRow.value['numeroCuota']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tableColumn2}" headerText="Valor Cuota" id="tableColumn2" noWrap="true" sort="valorCuota"
																width="40">
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.staticText1}"
																	converter="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.numberConverter1}" id="staticText1" text="#{currentRow.value['valorCuota']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tableColumn4}" headerText="Interés" id="tableColumn4" sort="interes">
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.staticText3}"
																	converter="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.numberConverter1}" id="staticText3" text="#{currentRow.value['interes']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tableColumn5}" headerText="Saldo Capital" id="tableColumn5" sort="saldoCapital">
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.staticText4}"
																	converter="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.numberConverter1}" id="staticText4" text="#{currentRow.value['saldoCapital']}" />
															</ui:tableColumn>
															<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tableColumn6}" headerText="Vencimiento" id="tableColumn6" sort="fechaVencimiento">
																<ui:staticText binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.staticText7}"
																	converter="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.dateTimeConverter1}" id="staticText7" text="#{currentRow.value['fechaVencimiento']}" />
															</ui:tableColumn>
														</ui:tableRowGroup>
														<f:facet name="actionsTop">
															<ui:panelGroup binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.groupPanel1}" id="groupPanel1">
																<ui:button action="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.btnGenerarCuotas_action}"
																	binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.btnGenerarCuotas}" id="btnGenerarCuotas" styleClass="button" text="Generar Cuotas" />
															</ui:panelGroup>
														</f:facet>
													</ui:table>
												</td>
											</tr>
											<tr>
												<td colspan="4"></td>
											</tr>
											<tr>
												<td align="left" colspan="4" nowrap="nowrap">
													<hr />
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.label32}" id="label32" styleClass="label2" text="Cese de la Refinanciación" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:staticText binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.staticText9}" id="staticText9" styleClass="label"
														text=" Pasados 'Cantidad de Días' después de la fecha de vencimiento de la última cuota impaga indicada en 'Cantidad de Cuotas', cesa la Refinanciación." />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblCantidadDiasCaida}" for="tfCantidadDiasCaida" id="lblCantidadDiasCaida" styleClass="label"
														text="Cantidad de Días" />
												</td>
												<td align="left" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfCantidadDiasCaida}" columns="4" onKeyPress="return ValidarNum(event,this)"
														id="tfCantidadDiasCaida" styleClass="textField" maxLength="4" />
												</td>
												<td align="right" nowrap="nowrap"></td>
												<td align="left" nowrap="nowrap"></td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.lblCantidadCuotasCaida}" for="tfCantidadCuotasCaida" id="lblCantidadCuotasCaida"
														styleClass="label" text="Cantidad de Cuotas" />
												</td>
												<td align="left" colspan="3" nowrap="nowrap">
													<ui:textField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.tfCantidadCuotasCaida}" columns="6" onKeyPress="return ValidarNum(event,this)"
														id="tfCantidadCuotasCaida" styleClass="textField" />
												</td>
											</tr>
											<tr>
												<td align="right" colspan="4" nowrap="nowrap"></td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:messageGroup binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.btnGuardar_action}" binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.btnGuardar}"
											id="btnGuardar" styleClass="button" text="Guardar" />
										<ui:staticText binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.btnCancelar_action}" binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.btnCancelar}"
											id="btnCancelar" styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.hidIdPagina}" id="hidIdPagina"
						text="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.idPagina}" />
					<ui:hiddenField binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.idSubSesion}" />
					<ui:script binding="#{excepciones$ABMRefinanciacion$AgregarPlanPagoRefinanciacion.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
					<script>
						<![CDATA[
						calcularCondonaciones();
						/*sumarTotalARefinanciar();*/
						]]>
					</script>
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>