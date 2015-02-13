<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false"
		deferredSyntaxAllowedAsLiteral="false" />
	<f:view>
		<ui:page binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.page1}" id="page1">
			<ui:html binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.html1}" id="html1">
			<ui:head binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.head1}" id="head1" title="Generar Liquidaciones de Arrendamiento">
				<ui:link binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.link1}" id="link1" url="/resources/stylesheet.css" />
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
			<ui:body binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.body1}" focus="form1:btnSeleccionarPersonaFisica" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.head1.title}" />
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
														<ui:label binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.label2}" for="tfPersonaFisica" id="label2"
															styleClass="label" text="Persona" />
													</td>
													<td nowrap="nowrap" colspan="4">
														<ui:textField binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.tfPersona}" columns="40" disabled="true"
															id="tfPersona" styleClass="textField" />
														<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnSeleccionarPersonaFisica_action}"
															binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnSeleccionarPersonaFisica}" escape="false"
															id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar" />
														<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnSeleccionarPersonaJuridica_action}"
															binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnSeleccionarPersonaJuridica}" escape="false"
															id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar" />
														<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona"
															binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnLimpiarPersona}"
															action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax" />
													</td>
												</tr>
												<tr>
													<td align="right" nowrap="nowrap">
														<ui:label for="tfParcela" id="lblParcela" styleClass="label" text="Parcela" />
													</td>
													<td nowrap="nowrap" colspan="4">
														<ui:textField binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.tfParcela}" columns="49" disabled="true"
															id="tfParcela" styleClass="textField" />
														<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnSeleccionarParcela_action}"
															binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnSeleccionarParcela}" id="btnSeleccionarParcela"
															escape="false" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
														<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcela"
															binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnLimpiarParcela}"
															action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnLimpiarParcela_action}" styleClass="buttonLimpiarAjax" />
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
																	<ui:panelGroup binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.panelAtributoDinamico}"
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
														<ui:label binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.lblAnios}" for="ddAnios" id="lblAnios"
															styleClass="label" text="Año" />
														<ui:dropDown binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.ddAnios}" id="ddAnios" styleClass="textField"
															items="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.ddAniosOptions.options}"
															valueChangeListener="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.eventoSeleccionAnio(evento)}">
															<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
														</ui:dropDown>
													</td>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.lblCalendarios}" for="ddCalendarios"
															id="lblCalendarios" styleClass="label" text="Calendario" />
														<ui:dropDown binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.ddCalendarios}" id="ddCalendarios"
															styleClass="textField" items="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.ddCalendariosOptions.options}"
															valueChangeListener="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.eventoSeleccionCalendario(event)}">
															<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
														</ui:dropDown>
													</td>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.lblPeriodos}" for="ddPeriodos" id="lblPeriodos"
															styleClass="label" text="Periodo" />
														<ui:dropDown binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.ddPeriodos}" id="ddPeriodos"
															styleClass="textField" items="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.ddPeriodosOptions.options}"
															valueChangeListener="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.eventoSeleccionPeriodo(event)}">
															<a4j:support event="onChange" reRender="form1:ddCuotas " />
														</ui:dropDown>
													</td>
													<td align="center" nowrap="nowrap">
														<ui:label binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.lblCuotas}" for="ddCuotas" id="lblCuotas"
															styleClass="label" text="Cuota" />
														<ui:dropDown binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.ddCuotas}" id="ddCuotas" styleClass="textField"
															items="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.ddCuotasOptions.options}" />
													</td>
												</tr>
												<tr><td><br/></td></tr>
												<tr><td align="center" nowrap="nowrap">
												<ui:label binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.lblIgnorarPlan}" for="cbIgnorarPlan" id="lblIgnorarPlan"
															styleClass="label" text="Ignorar Plan"/>
												</td><td>
												<ui:checkbox binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.cbIgnorarPlan}" id="cbIgnorarPlan"/>
												</td></tr>
											</tbody>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.messageGroup1}" id="messageGroup1"
											showGlobalOnly="true" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="2" nowrap="nowrap" style="height: 33px">
										<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnGenerarLiquidacionPrueba_action}"
											binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnGenerarLiquidacionPrueba}" id="btnGenerarLiquidacionPrueba"
											styleClass="button" text="Generar Prueba Liquidaciones"
											onClick=" mostrarProgreso(); newWindow = window.open('/Vipians/faces/saic/grpArrendamiento/ABMLiquidacionArrendamiento/ImprimirLiquidacionArrendamiento.jsp', 'Reporte')" />
										<ui:staticText binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.stSeparador1}" escape="false" id="stSeparador1"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnGenerarLiquidaciones_action}"
											binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnGenerarLiquidaciones}" id="btnGenerarLiquidaciones"
											onClick="return confirmarLiquidacionSinParametros();" styleClass="button" text="Generar Liquidaciones" />
										<ui:staticText binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnCancelar_action}"
											binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<ui:hiddenField binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.idPagina}" />
					<ui:hiddenField binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.idSubSesion}" />
					<ui:script binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
					<h:panelGrid bgcolor="#DDD" binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.gdpGenerarLiquidaciones}" cellpadding="2"
						cellspacing="2" columns="2" id="gdpGenerarLiquidaciones" rendered="false" styleClass="msgLiquidacion">
						<ui:image binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.image1}" id="image1"
							url="/resources/imagenes/mensajes/warning_large.gif" />
						<ui:staticText binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.stMensajeGeneracion1}" escape="false"
							id="stMensajeGeneracion1" styleClass="staticText2"
							text="Se generarán XXX Liquidaciones de Arrendamiento. Haga clic en &lt;i&gt;Generar Liquidaciones&lt;/i&gt; para comenzar el proceso." />
						<ui:staticText binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.stSpacer1}" escape="false" id="stSpacer1" />
						<ui:button action="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnGenerarLiquidacionesFinal_action}"
							binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.btnGenerarLiquidacionesFinal}" id="btnGenerarLiquidacionesFinal"
							styleClass="button" text="Generar Liquidaciones" />
					</h:panelGrid>
					<h:panelGrid binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.grpCargando}" columns="2" id="grpCargando"
						style="display:none; padding-left:10px;" styleClass="msgLiquidacion">
						<ui:image binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.image2}" id="image2"
							url="/resources/imagenes/abm/loading.gif" />
						<ui:staticText binding="#{saic$grpArrendamiento$ABMLiquidacionArrendamiento$GenerarLiquidacionArrendamiento.staticText1}" escape="false" id="staticText1"
							styleClass="label2" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;Generando Liquidaciones" />
					</h:panelGrid>
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
