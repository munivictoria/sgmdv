<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false"
		deferredSyntaxAllowedAsLiteral="false" />
	<f:view>
		<ui:page binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.page1}" id="page1">
			<ui:html binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.html1}" id="html1">
			<ui:head binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.head1}" id="head1" title="Generar Liquidaciones de PFO">
				<ui:link binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
                    
                        document.getElementById("form1:grpCargando").style.display = "none";
                    
                        function mostrarProgreso() {
                            tabla = document.getElementById("form1:grpCargando");
                            tabla.style.display = "block";
                        }
                    
                    ]]></script>
			</ui:head>
			<ui:body binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.body1}" focus="form1:tfAnio" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.label6}" for="tfPersona" id="label6" styleClass="label"
											text="Persona" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.tfPersona}" columns="40" disabled="true"
											id="tfPersona" styleClass="textField" />
										<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnSeleccionarPersonaFisica_action}"
											binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnSeleccionarPersonaFisica}" escape="false"
											id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar" />
										<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnSeleccionarPersonaJuridica_action}"
											binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnSeleccionarPersonaJuridica}" escape="false"
											id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarPersonaFisica" reRender="form1:tfPersona"
											binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnLimpiarPersonaFisica}"
											action="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnLimpiarPersonaFisica_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.label7}" for="tfObra" id="label7" styleClass="label"
											text="Obra" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.tfObra}" columns="40" disabled="true" id="tfObra"
											styleClass="textField" />
										<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnSeleccionarObra_action}"
											binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnSeleccionarObra}" escape="false" id="btnSeleccionarObra"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarObra" reRender="form1:tfObra"
											binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnLimpiarObra}"
											action="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnLimpiarObra_action}" styleClass="buttonLimpiarAjax" />
									</td>
								</tr>
								<!--<tr>
                                        <td align="right" nowrap="nowrap">-->
								<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.label2}" for="tfCuadra" id="label2" rendered="false"
									styleClass="label" text="Cuadra" />
								<!--</td>
                                        <td nowrap="nowrap">-->
								<ui:textField binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.tfCuadra}" columns="40" disabled="true" id="tfCuadra"
									rendered="false" styleClass="textField" />
								<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnSeleccionarCuadra_action}"
									binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnSeleccionarCuadra}" escape="false" id="btnSeleccionarCuadra"
									mini="true" rendered="false" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
								<a4j:commandButton id="btnLimpiarCuadra" reRender="form1:tfCuadra"
									binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnLimpiarCuadra}"
									action="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnLimpiarCuadra_action}" styleClass="buttonLimpiarAjax" />
								<!--</td>
                                    </tr>-->
								<tr>
									<td align="right" nowrap="nowrap">
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.label4}" for="tfCalle" id="label4" styleClass="label"
											text="Calle" />
									</td>
									<td nowrap="nowrap">
										<ui:textField binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.tfCalle}" columns="40" disabled="true" id="tfCalle"
											styleClass="textField" />
										<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnSeleccionarCalle_action}"
											binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnSeleccionarCalle}" escape="false" id="btnSeleccionarCalle"
											mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarCalle" reRender="form1:tfCalle"
											binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnLimpiarCalle}"
											action="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnLimpiarCalle_action}" styleClass="buttonLimpiarAjax" />
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
													<ui:panelGroup binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.panelAtributoDinamico}"
														id="panelAtributoDinamico">
														<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
													</ui:panelGroup>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<hr />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.label3}" id="label3" styleClass="label2" text="Período" />
									</td>
								</tr>
								<tr>
									<td nowrap="nowrap" colspan="2">
										<table>
											<tbody>
												<tr>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.lblAnios}" for="ddAnios" id="lblAnios"
															styleClass="label" text="Año" />
														<ui:dropDown binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.ddAnios}" id="ddAnios" styleClass="textField"
															items="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.ddAniosOptions.options}"
															valueChangeListener="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.eventoSeleccionAnio(evento)}">
															<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
														</ui:dropDown>
													</td>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.lblCalendarios}" for="ddCalendarios"
															id="lblCalendarios" styleClass="label" text="Calendario" />
														<ui:dropDown binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.ddCalendarios}" id="ddCalendarios"
															styleClass="textField" items="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.ddCalendariosOptions.options}"
															valueChangeListener="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.eventoSeleccionCalendario(event)}">
															<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
														</ui:dropDown>
													</td>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.lblPeriodos}" for="ddPeriodos" id="lblPeriodos"
															styleClass="label" text="Periodo" />
														<ui:dropDown binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.ddPeriodos}" id="ddPeriodos"
															styleClass="textField" items="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.ddPeriodosOptions.options}"
															valueChangeListener="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.eventoSeleccionPeriodo(event)}">
															<a4j:support event="onChange" reRender="form1:ddCuotas " />
														</ui:dropDown>
													</td>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.lblCuotas}" for="ddCuotas" id="lblCuotas"
															styleClass="label" text="Cuota" />
														<ui:dropDown binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.ddCuotas}" id="ddCuotas" styleClass="textField"
															items="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.ddCuotasOptions.options}" />
													</td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<ui:messageGroup binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.messageGroup1}" id="messageGroup1"
											showGlobalOnly="true" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap" style="height: 24px">
										<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnGenerarLiquidacionPrueba_action}"
											binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnGenerarLiquidacionPrueba}" id="btnGenerarLiquidacionPrueba"
											styleClass="button" text="Generar Prueba Liquidaciones"
											onClick="newWindow = window.open('/Vipians/faces/saic/grpPFO/ABMLiquidacionPFO/ImprimirLiquidacionPFO.jsp', 'Reporte')" />
										<ui:staticText binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.stSeparador1}" escape="false" id="stSeparador1"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnGenerarLiquidaciones_action}"
											binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnGenerarLiquidaciones}" id="btnGenerarLiquidaciones"
											onClick="mostrarProgreso();" styleClass="button" text="Generar Liquidaciones" />
										<ui:staticText binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnCancelar_action}"
											binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
						<h:panelGrid binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.grpCargando}" columns="2" id="grpCargando"
							style="display: none; padding-left: 10px" styleClass="msgLiquidacion">
							<ui:image binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.image1}" id="image1"
								url="/resources/imagenes/abm/loading.gif" />
							<ui:staticText binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.staticText1}" escape="false" id="staticText1"
								styleClass="label2" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;Generando Liquidaciones" />
						</h:panelGrid>
					</div>
					<ui:hiddenField binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.idPagina}" />
					<ui:hiddenField binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.idSubSesion}" />
					<ui:script binding="#{saic$grpPFO$ABMLiquidacionPFO$GenerarLiquidacionPFO.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
