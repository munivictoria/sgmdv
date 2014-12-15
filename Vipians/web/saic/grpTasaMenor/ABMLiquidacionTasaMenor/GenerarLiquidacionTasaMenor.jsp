<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false"
		deferredSyntaxAllowedAsLiteral="false" />
	<f:view>
		<ui:page binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.page1}" id="page1">
			<ui:html binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.html1}" id="html1">
			<ui:head binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.head1}" id="head1"
				title="Generar Liquidaciones de Tasas Menores">
				<ui:link binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.link1}" id="link1"
					url="/resources/stylesheet.css" />
				<script>
					<![CDATA[

					document.getElementById("form1:grpCargando").style.display = "none";

					function mostrarProgreso() {
						tabla = document.getElementById("form1:grpCargando");
						tabla.style.display = "block";
					}

					function confirmarLiquidacionSinParametros() {
						var tfPersona = document.getElementById('form1:tfPersona');
						var tfParcela = document.getElementById('form1:tfParcela');
						var retorno = tfPersona.value != ''
								|| tfParcela.value != ''
								|| confirm('No ha seleccionado ning\372n par\341metro.\nSe liquidará la tasa para todos los contribuyentes. ¿Desea continuar?');
						if (retorno) {
							mostrarProgreso();
						}
						return retorno;
					}

					]]>
				</script>
			</ui:head>
			<ui:body binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.body1}" focus="form1:tfAnio" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.stTitulo}" id="stTitulo"
									styleClass="tituloABM" text="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td align="center">
										<table>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.label2}" for="tfPersonaFisica"
														id="label2" styleClass="label" text="Persona" />
												</td>
												<td>
													<ui:textField binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.tfPersona}" columns="40"
														disabled="true" id="tfPersonaFisica" styleClass="textField" />
													<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnSeleccionarPersonaFisica_action}"
														binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnSeleccionarPersonaFisica}" escape="false"
														id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
													<ui:button
														action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnSeleccionarPersonaJuridica_action}"
														binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnSeleccionarPersonaJuridica}"
														escape="false" id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ"
														toolTip="Seleccionar Persona Juridica" />
													<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaFisica"
														binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnLimpiarPersona}"
														action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnLimpiarPersona_action}"
														styleClass="buttonLimpiarAjax" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.label1}" for="tfParcela" id="label1"
														styleClass="label" text="Parcela" />
												</td>
												<td>
													<ui:textField binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.tfParcela}" columns="40"
														disabled="true" id="tfParcela" styleClass="textField" />
													<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnSeleccionarParcela_action}"
														binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnSeleccionarParcela}" escape="false"
														id="btnSeleccionarParcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
													<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcela"
														binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnLimpiarParcela}"
														action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnLimpiarParcela_action}"
														styleClass="buttonLimpiarAjax" />
												</td>
											</tr>
											<tr>
												<td align="right" nowrap="nowrap">
													<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.label9}" for="tfTipoObligacionTasa"
														id="label9" styleClass="label" text="Tipo Obligación Tasa Menor" />
												</td>
												<td>
													<ui:dropDown binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.ddTipoObligacionTasa}"
														id="ddTipoObligacionTasa"
														items="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.ddTipoObligacionTasaOptions.options}"
														styleClass="textField"
														valueChangeListener="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.eventoSeleccionTipoObligacion(evento)}">
														<a4j:support event="onChange" reRender="form1:ddAnios, form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
													</ui:dropDown>
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<hr />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.lblPeriodo}" id="lblPeriodo"
														styleClass="label2" text="Período" />
												</td>
											</tr>
											<tr>
												<td nowrap="nowrap" colspan="2">
													<table>
														<tbody>
															<tr>
																<td align="center" nowrap="nowrap">
																	<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.lblAnio}" for="ddAnios"
																		id="lblAnio" styleClass="label" text="Año" />
																	<ui:dropDown binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.ddAnios}" id="ddAnios"
																		styleClass="textField"
																		items="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.ddAniosOptions.options}"
																		valueChangeListener="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.eventoSeleccionAnio(evento)}">
																		<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
																	</ui:dropDown>
																</td>
																<td align="center" nowrap="nowrap">
																	<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.lblCalendarios}"
																		for="ddCalendarios" id="lblCalendarios" styleClass="label" text="Calendario" />
																	<ui:dropDown binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.ddCalendarios}"
																		id="ddCalendarios" styleClass="textField"
																		items="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.ddCalendariosOptions.options}"
																		valueChangeListener="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.eventoSeleccionCalendario(event)}">
																		<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
																	</ui:dropDown>
																</td>
																<td align="center" nowrap="nowrap">
																	<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.lblPeriodos}" for="ddPeriodos"
																		id="lblPeriodos" styleClass="label" text="Periodo" />
																	<ui:dropDown binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.ddPeriodos}" id="ddPeriodos"
																		styleClass="textField"
																		items="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.ddPeriodosOptions.options}"
																		valueChangeListener="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.eventoSeleccionPeriodo(event)}">
																		<a4j:support event="onChange" reRender="form1:ddCuotas " />
																	</ui:dropDown>
																</td>
																<td align="center" nowrap="nowrap">
																	<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.lblCuotas}" for="ddCuotas"
																		id="lblCuotas" styleClass="label" text="Cuota" />
																	<ui:dropDown binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.ddCuotas}" id="ddCuotas"
																		styleClass="textField"
																		items="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.ddCuotasOptions.options}" />
																</td>
															</tr>
															<tr>
																<td>
																	<br />
																</td>
															</tr>
															<tr>
																<td align="center" nowrap="nowrap">
																	<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.lblIgnorarPlan}"
																		for="cbIgnorarPlan" id="lblIgnorarPlan" styleClass="label" text="Ignorar Plan" />
																</td>
																<td>
																	<ui:checkbox binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.cbIgnorarPlan}"
																		id="cbIgnorarPlan" />
																</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.messageGroup1}"
											id="messageGroup1" showGlobalOnly="true" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap" style="height: 33px">
										<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnGenerarLiquidacionPrueba_action}"
											binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnGenerarLiquidacionPrueba}"
											id="btnGenerarLiquidacionPrueba" styleClass="button" text="Generar Prueba Liquidaciones"
											onClick=" mostrarProgreso(); newWindow = window.open('/Vipians/faces/saic/grpTGI/ABMLiquidacionTGI/ImprimirLiquidacionTGI.jsp', 'Reporte')" />
										<ui:staticText binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.stSeparador1}" escape="false"
											id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnGenerarLiquidaciones_action}"
											binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnGenerarLiquidaciones}"
											id="btnGenerarLiquidaciones" onClick="mostrarProgreso();" styleClass="button" text="Generar Liquidaciones" />
										<ui:staticText binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.stSeparador}" escape="false"
											id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnCancelar_action}"
											binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnCancelar}" id="btnCancelar"
											styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.idPagina}" />
					<ui:hiddenField binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.idSubSesion}" />
					<ui:script binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
					<h:panelGrid bgcolor="#DDD" binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.gdpGenerarLiquidaciones}"
						cellpadding="2" cellspacing="2" columns="2" id="gdpGenerarLiquidaciones" rendered="false" styleClass="msgLiquidacion">
						<ui:image binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.image1}" id="image1"
							url="/resources/imagenes/mensajes/warning_large.gif" />
						<ui:staticText binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.stMensajeGeneracion1}" escape="false"
							id="stMensajeGeneracion1" styleClass="staticText2"
							text="Se generarán XXX Liquidaciones de TGI. Haga clic en &lt;i&gt;Generar Liquidaciones&lt;/i&gt; para comenzar el proceso." />
						<ui:staticText binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.stSpacer1}" escape="false"
							id="stSpacer1" />
						<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnGenerarLiquidacionesFinal_action}"
							binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.btnGenerarLiquidacionesFinal}"
							id="btnGenerarLiquidacionesFinal" styleClass="button" text="Generar Liquidaciones" />
					</h:panelGrid>
					<h:panelGrid binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.grpCargando}" columns="2"
						id="grpCargando" style="display:none; padding-left:10px;" styleClass="msgLiquidacion">
						<ui:image binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.image2}" id="image2"
							url="/resources/imagenes/abm/loading.gif" />
						<ui:staticText binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$GenerarLiquidacionTasaMenor.staticText1}" escape="false"
							id="staticText1" styleClass="label2" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;Generando Liquidaciones" />
					</h:panelGrid>
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
