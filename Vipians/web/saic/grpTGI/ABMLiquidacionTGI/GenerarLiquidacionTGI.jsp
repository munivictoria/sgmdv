<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false"
		deferredSyntaxAllowedAsLiteral="false" />
	<f:view>
		<ui:page binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.page1}" id="page1">
			<ui:html binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.html1}" id="html1">
			<ui:head binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.head1}" id="head1" title="Generar Liquidaciones de TGI">
				<ui:link binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
                    
                        document.getElementById("form1:grpCargando").style.display = "none";
                    
                        function mostrarProgreso() {
                            tabla = document.getElementById("form1:grpCargando");
                            tabla.style.display = "block";
                        }
                        
                        function confirmarLiquidacionSinParametros(){
                        var tfPersona = document.getElementById('form1:tfPersona');
                        var tfParcela = document.getElementById('form1:tfParcela');
                        var retorno = tfPersona.value != '' 
                            || tfParcela.value != ''
                            || confirm('No ha seleccionado ning\372n par\341metro.\nSe liquidará la tasa para todos los contribuyentes. ¿Desea continuar?');
                        if (retorno){
                            mostrarProgreso();
                        }
                        return retorno;
                    }
                    
                    ]]></script>
			</ui:head>
			<ui:body binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.body1}" focus="form1:btnSeleccionarPersonaFisica" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td nowrap="nowrap" colspan="4">
										<table>
											<tbody>
												<tr>
													<td align="right" nowrap="nowrap">
														<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.label2}" for="tfPersonaFisica" id="label2"
															styleClass="label" text="Persona" />
													</td>
													<td nowrap="nowrap" colspan="4">
														<ui:textField binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.tfPersona}" columns="40" disabled="true"
															id="tfPersona" styleClass="textField" />
														<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnSeleccionarPersonaFisica_action}"
															binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnSeleccionarPersonaFisica}" escape="false"
															id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar" />
														<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnSeleccionarPersonaJuridica_action}"
															binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnSeleccionarPersonaJuridica}" escape="false"
															id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar" />
														<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona"
															binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnLimpiarPersona}"
															action="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax" />
													</td>
												</tr>
												<tr>
													<td align="right" nowrap="nowrap">
														<ui:label for="tfParcela" id="lblParcela" styleClass="label" text="Parcela" />
													</td>
													<td nowrap="nowrap" colspan="4">
														<ui:textField binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.tfParcela}" columns="49" disabled="true"
															id="tfParcela" styleClass="textField" />
														<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnSeleccionarParcela_action}"
															binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnSeleccionarParcela}" id="btnSeleccionarParcela"
															escape="false" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
														<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcela"
															binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnLimpiarParcela}"
															action="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnLimpiarParcela_action}" styleClass="buttonLimpiarAjax" />
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
																	<ui:panelGroup binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.panelAtributoDinamico}"
																		id="panelAtributoDinamico">
																		<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
																	</ui:panelGroup>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td colspan="4">
														<hr />
													</td>
												</tr>
												<tr>
													<td colspan="4">
														<ui:label id="label3" styleClass="label2" text="Período" />
													</td>
												</tr>
												<tr>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.lblAnios}" for="ddAnios" id="lblAnios"
															styleClass="label" text="Año" />
														<ui:dropDown binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.ddAnios}" id="ddAnios" styleClass="textField"
															items="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.ddAniosOptions.options}"
															valueChangeListener="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.eventoSeleccionAnio(evento)}">
															<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
														</ui:dropDown>
													</td>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.lblCalendarios}" for="ddCalendarios"
															id="lblCalendarios" styleClass="label" text="Calendario" />
														<ui:dropDown binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.ddCalendarios}" id="ddCalendarios"
															styleClass="textField" items="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.ddCalendariosOptions.options}"
															valueChangeListener="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.eventoSeleccionCalendario(event)}">
															<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
														</ui:dropDown>
													</td>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.lblPeriodos}" for="ddPeriodos" id="lblPeriodos"
															styleClass="label" text="Periodo" />
														<ui:dropDown binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.ddPeriodos}" id="ddPeriodos"
															styleClass="textField" items="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.ddPeriodosOptions.options}"
															valueChangeListener="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.eventoSeleccionPeriodo(event)}">
															<a4j:support event="onChange" reRender="form1:ddCuotas " />
														</ui:dropDown>
													</td>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.lblCuotas}" for="ddCuotas" id="lblCuotas"
															styleClass="label" text="Cuota" />
														<ui:dropDown binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.ddCuotas}" id="ddCuotas" styleClass="textField"
															items="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.ddCuotasOptions.options}" />
													</td>
												</tr>
												<tr><td><br/></td></tr>
												<tr><td align="center" nowrap="nowrap">
												<ui:label binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.lblIgnorarPlan}" for="cbIgnorarPlan" id="lblIgnorarPlan"
															styleClass="label" text="Ignorar Plan"/>
												</td><td>
												<ui:checkbox binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.cbIgnorarPlan}" id="cbIgnorarPlan"/>
												</td></tr>
											</tbody>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.messageGroup1}" id="messageGroup1"
											showGlobalOnly="true" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap" style="height: 33px">
										<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnGenerarLiquidacionPrueba_action}"
											binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnGenerarLiquidacionPrueba}" id="btnGenerarLiquidacionPrueba"
											styleClass="button" text="Generar Prueba Liquidaciones"
											onClick=" mostrarProgreso(); newWindow = window.open('/Vipians/faces/saic/grpTGI/ABMLiquidacionTGI/ImprimirLiquidacionTGI.jsp', 'Reporte')" />
										<ui:staticText binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.stSeparador1}" escape="false" id="stSeparador1"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnGenerarLiquidaciones_action}"
											binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnGenerarLiquidaciones}" id="btnGenerarLiquidaciones"
											onClick="return confirmarLiquidacionSinParametros();" styleClass="button" text="Generar Liquidaciones" />
										<ui:staticText binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnCancelar_action}"
											binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.idPagina}" />
					<ui:hiddenField binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.idSubSesion}" />
					<ui:script binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
					<h:panelGrid bgcolor="#DDD" binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.gdpGenerarLiquidaciones}" cellpadding="2"
						cellspacing="2" columns="2" id="gdpGenerarLiquidaciones" rendered="false" styleClass="msgLiquidacion">
						<ui:image binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.image1}" id="image1"
							url="/resources/imagenes/mensajes/warning_large.gif" />
						<ui:staticText binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.stMensajeGeneracion1}" escape="false"
							id="stMensajeGeneracion1" styleClass="staticText2"
							text="Se generarán XXX Liquidaciones de TGI. Haga clic en &lt;i&gt;Generar Liquidaciones&lt;/i&gt; para comenzar el proceso." />
						<ui:staticText binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.stSpacer1}" escape="false" id="stSpacer1" />
						<ui:button action="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnGenerarLiquidacionesFinal_action}"
							binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.btnGenerarLiquidacionesFinal}" id="btnGenerarLiquidacionesFinal"
							styleClass="button" text="Generar Liquidaciones" />
					</h:panelGrid>
					<h:panelGrid binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.grpCargando}" columns="2" id="grpCargando"
						style="display:none; padding-left:10px;" styleClass="msgLiquidacion">
						<ui:image binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.image2}" id="image2"
							url="/resources/imagenes/abm/loading.gif" />
						<ui:staticText binding="#{saic$grpTGI$ABMLiquidacionTGI$GenerarLiquidacionTGI.staticText1}" escape="false" id="staticText1"
							styleClass="label2" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;Generando Liquidaciones" />
					</h:panelGrid>
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
