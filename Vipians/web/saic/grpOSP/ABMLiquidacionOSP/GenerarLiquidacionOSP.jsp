<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false"
		deferredSyntaxAllowedAsLiteral="false" />
	<f:view>
		<ui:page binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.page1}" id="page1">
			<ui:html binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.html1}" id="html1">
			<ui:head binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.head1}" id="head1" title="Generar Liquidaciones de OSP">
				<ui:link binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[

					document.getElementById("form1:grpCargando").style.display = "none";

					function mostrarProgreso() {
						tabla = document.getElementById("form1:grpCargando");
						tabla.style.display = "block";
					}

					function confirmarLiquidacionSinParametros() {
						var tfPersona = document
								.getElementById('form1:tfPersona');
						var tfParcela = document
								.getElementById('form1:tfParcela');
						var tfServicioOSP = document
								.getElementById('form1:tfServicioOSP');
						var tfCalle = document.getElementById('form1:tfCalle');
						var retorno = tfPersona.value != ''
								|| tfParcela.value != ''
								|| tfServicioOSP.value != ''
								|| tfCalle.value != ''
								|| confirm('No ha seleccionado ning\372n par\341metro.\nSe liquidará la tasa para todos los contribuyentes. ¿Desea continuar?');
						if (retorno) {
							mostrarProgreso();
						}
						return retorno;
					}

					]]>
				</script>
			</ui:head>
			<ui:body binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.body1}" focus="form1:tfAnio" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.lblPersona}" for="tfPersona" id="labelPersona"
											styleClass="label" text="Persona" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.tfPersona}" columns="40" disabled="true"
											id="tfPersona" styleClass="textField" />
										<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnSeleccionarPersonaFisica_action}"
											binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnSeleccionarPersonaFisica}" escape="false" id="btnSeleccionarPF"
											mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
										<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnSeleccionarPersonaJuridica_action}"
											binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnSeleccionarPersonaJuridica}" escape="false"
											id="btnSeleccionarPJ" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Juridica" />
										<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona"
											binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnLimpiarPersona}"
											action="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label for="tfParcela" id="lblParcela" styleClass="label" text="Parcela" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.tfParcela}" columns="40" disabled="true"
											id="tfParcela" styleClass="textField" />
										<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnSeleccionarParcela_action}"
											binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnSeleccionarParcela}" id="btnSeleccionarParcela" escape="false"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcela"
											binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnLimpiarParcela}"
											action="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnLimpiarParcela_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.label2}" for="tfServicioOSP" id="label2"
											styleClass="label" text="Servicio" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.tfServicioOSP}" columns="40" disabled="true"
											id="tfServicioOSP" styleClass="textField" />
										<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnSeleccionarServicioOSP_action}"
											binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnSeleccionarServicioOSP}" escape="false"
											id="btnSeleccionarServicioOSP" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarServicioOSP" reRender="form1:tfServicioOSP"
											binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnLimpiarServicioOSP}"
											action="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnLimpiarServicioOSP_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.label4}" for="tfCalle" id="label4" styleClass="label"
											text="Calle" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.tfCalle}" columns="40" disabled="true" id="tfCalle"
											styleClass="textField" />
										<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnSeleccionarCalle_action}"
											binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnSeleccionarCalle}" escape="false" id="btnSeleccionarCalle"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarCalle" reRender="form1:tfCalle"
											binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnLimpiarCalle}"
											action="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnLimpiarCalle_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<table border="0" width="600">
											<tr>
												<td>
													<ui:label id="lblVacio" styleClass="label" text="" />
												</td>
												<td>
													<ui:panelGroup binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.panelAtributoDinamico}"
														id="panelAtributoDinamico">
														<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
													</ui:panelGroup>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2" style="height: 16px">
										<hr />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.label10}" id="label10" styleClass="label2" text="Período" />
									</td>
								</tr>
								<tr>
									<td nowrap="nowrap" colspan="2">
										<table>
											<tbody>
												<tr>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.lblAnio}" for="ddAnios" id="lblAnio"
															styleClass="label" text="Año" />
														<ui:dropDown binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.ddAnios}" id="ddAnios" styleClass="textField"
															items="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.ddAniosOptions.options}"
															valueChangeListener="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.eventoSeleccionAnio(evento)}">
															<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
														</ui:dropDown>
													</td>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.lblCalendarios}" for="ddCalendarios"
															id="lblCalendarios" styleClass="label" text="Calendario" />
														<ui:dropDown binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.ddCalendarios}" id="ddCalendarios"
															styleClass="textField" items="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.ddCalendariosOptions.options}"
															valueChangeListener="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.eventoSeleccionCalendario(event)}">
															<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
														</ui:dropDown>
													</td>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.lblPeriodos}" for="ddPeriodos" id="lblPeriodos"
															styleClass="label" text="Periodo" />
														<ui:dropDown binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.ddPeriodos}" id="ddPeriodos"
															styleClass="textField" items="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.ddPeriodosOptions.options}"
															valueChangeListener="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.eventoSeleccionPeriodo(event)}">
															<a4j:support event="onChange" reRender="form1:ddCuotas " />
														</ui:dropDown>
													</td>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.lblCuotas}" for="ddCuotas" id="lblCuotas"
															styleClass="label" text="Cuota" />
														<ui:dropDown binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.ddCuotas}" id="ddCuotas" styleClass="textField"
															items="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.ddCuotasOptions.options}" />
													</td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
								<tr><td align="center" nowrap="nowrap">
									<ui:label binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.lblIgnorarPlan}" id="lblIgnorarPlan"
												styleClass="label" text="Ignorar Plan"/>
									</td><td>
									<ui:checkbox binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.cbIgnorarPlan}" id="cbIgnorarPlan"/>
								</td></tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.messageGroup1}" id="messageGroup1"
											showGlobalOnly="true" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap" style="height: 24px">
										<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnGenerarLiquidacionPrueba_action}"
											binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnGenerarLiquidacionPrueba}" id="btnGenerarLiquidacionPrueba"
											styleClass="button" text="Generar Prueba Liquidaciones"
											onClick="newWindow = window.open('/Vipians/faces/saic/grpOSP/ABMLiquidacionOSP/ImprimirLiquidacionOSP.jsp', 'Reporte')" />
										<ui:staticText binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.stSeparador1}" escape="false" id="stSeparador1"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnGenerarLiquidaciones_action}"
											binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnGenerarLiquidaciones}" id="btnGenerarLiquidaciones"
											onClick="return confirmarLiquidacionSinParametros();" styleClass="button" text="Generar Liquidaciones" />
										<ui:staticText binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnCancelar_action}"
											binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.idPagina}" />
					<ui:hiddenField binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.idSubSesion}" />
					<ui:script binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
					<h:panelGrid binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.grpCargando}" columns="2" id="grpCargando"
						style="display:none; padding-left: 10px" styleClass="msgLiquidacion">
						<ui:image binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.image1}" id="image1"
							url="/resources/imagenes/abm/loading.gif" />
						<ui:staticText binding="#{saic$grpOSP$ABMLiquidacionOSP$GenerarLiquidacionOSP.staticText1}" escape="false" id="staticText1"
							styleClass="label2" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;Generando Liquidaciones" />
					</h:panelGrid>
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
