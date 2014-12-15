<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.page1}" id="page1">
			<ui:html binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.html1}" id="html1">
			<ui:head binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.head1}" id="head1"
				title="Administración de Mediciones de los Medidores">
				<ui:link binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.body1}" focus="form1:tfCodigoMedidor" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.lblCodigoMedidor}" for="lblCodigoMedidor" id="lblCodigor"
																styleClass="label" text="Código Medidor" />
														</td>
														<td>
															<ui:textField binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.tfCodigoMedidor}" id="tfCodigoMedidor"
																styleClass="textField" columns="10" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.label1}" for="tfServicioOSP" id="label1"
																styleClass="label" text="Servicio" />
														</td>
														<td>
															<ui:textField binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.tfServicioOSP}" columns="40" disabled="true"
																id="tfServicioOSP" styleClass="textField" />
															<ui:button action="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnSeleccionarServicioOSP_action}"
																binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnSeleccionarServicioOSP}" escape="false"
																id="btnSeleccionarServicioOSP" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarServicio" reRender="form1:tfServicioOSP" title="Limpiar"
																binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnLimpiarServicio}"
																action="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnLimpiarServicio_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.label2}" for="tfCalle" id="label2" styleClass="label"
																text="Calle" />
														</td>
														<td>
															<ui:textField binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.tfCalle}" columns="40" disabled="true" id="tfCalle"
																styleClass="textField" />
															<ui:button action="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnSeleccionarCalle_action}"
																binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnSeleccionarCalle}" escape="false" id="btnSeleccionarCalle"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarCalle" reRender="form1:tfCalle" title="Limpiar"
																binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnLimpiarCalle}"
																action="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnLimpiarCalle_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
												</table>
												<table>
													<tr>
														<td nowrap="nowrap" align="center">
															<ui:panelGroup id="pgParametros2">
																<table>
																	<tr>
																		<td colspan="4">
																			<hr />
																		</td>
																	</tr>
																	<tr>
																		<td colspan="4">
																			<ui:label binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.lblPeriodo}" id="lblPeriodo" styleClass="label2"
																				text="Período" />
																		</td>
																	</tr>
																	<tr>
																		<td align="center" nowrap="nowrap">
																			<ui:label binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.lblAnio}" for="ddAnios" id="lblAnio"
																				styleClass="label" text="Año" />
																			<ui:dropDown binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.ddAnios}" id="ddAnios" styleClass="textField"
																				items="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.ddAniosOptions.options}"
																				valueChangeListener="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.eventoSeleccionAnio(evento)}">
																				<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
																			</ui:dropDown>
																		</td>
																		<td align="center" nowrap="nowrap">
																			<ui:label binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.lblCalendarios}" for="ddCalendarios"
																				id="lblCalendarios" styleClass="label" text="Calendario" />
																			<ui:dropDown binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.ddCalendarios}" id="ddCalendarios"
																				styleClass="textField" items="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.ddCalendariosOptions.options}"
																				valueChangeListener="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.eventoSeleccionCalendario(event)}">
																				<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
																			</ui:dropDown>
																		</td>
																		<td align="center" nowrap="nowrap">
																			<ui:label binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.lblPeriodos}" for="ddPeriodos" id="lblPeriodos"
																				styleClass="label" text="Periodo" />
																			<ui:dropDown binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.ddPeriodos}" id="ddPeriodos" styleClass="textField"
																				items="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.ddPeriodosOptions.options}"
																				valueChangeListener="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.eventoSeleccionPeriodo(event)}">
																				<a4j:support event="onChange" reRender="form1:ddCuotas " />
																			</ui:dropDown>
																		</td>
																		<td align="center" nowrap="nowrap">
																			<ui:label binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.lblCuotas}" for="ddCuotas" id="lblCuotas"
																				styleClass="label" text="Cuota" />
																			<ui:dropDown binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.ddCuotas}" id="ddCuotas" styleClass="textField"
																				items="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.ddCuotasOptions.options}" />
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2"></td>
																	</tr>
																</table>
															</ui:panelGroup>
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnBuscar}"
												action="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnReiniciar_action}"
												binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnCancelar_action}"
												binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.paginatedTable}" id="table1" width="622">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.gpBotones}" id="gpBotones">
														<ui:button action="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnSeleccionar_action}"
															binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
															text="Seleccionar" />
														<ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.stSeparadorSeleccionar}" escape="false"
															id="staticText6" />
														<ui:button action="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnAgregar_action}"
															binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
														<ui:button action="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnModificar_action}"
															binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnModificar}" id="btnModificar" styleClass="button"
															text="Modificar" />
														<ui:button action="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnEliminar_action}"
															binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnEliminar}" disabled="true" id="btnEliminar" styleClass="button"
															text="Eliminar" />
														<ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.stSeparadorAccion}" escape="false" id="staticText12" />
														<ui:button action="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnConsultar_action}"
															binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnConsultar}" id="btnConsultar" styleClass="button"
															text="Consultar" />
														<ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.staticText14}" escape="false" id="staticText14"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnExportar_action}"
															binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
															onClick="return exportarReporte()" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfCodigoMedidor').focus();
					</script>
					<ui:hiddenField binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.idPagina}" />
					<ui:hiddenField binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.idSubSesion}" />
					<ui:script binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{saic$grpOSP$ABMValorMedidor$AdminValorMedidor.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
