<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.page1}" id="page1">
			<ui:html binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.html1}" id="html1">
			<ui:head binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.head1}" id="head1" title="Administración de Declaraciones Juradas de SHPS">
				<ui:link binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfPersona", "persona", nombreBean, "setPersonaAutocompletar");
					}

					function focusearTfPersona() {
						$("#form1\\:tfPersona").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.body1}" focus="form1:btnSeleccionarPersonaFisica" id="body1"
				onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 236, 242); -rave-layout: grid"
				onKeyPress="eventoByEnter(event,'btnBuscar')" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td nowrap="nowrap" align="center">
											<ui:panelGroup id="pgParametros1">
												<table>
													<tr>
														<td colspan="2"></td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.label2}" for="tfPersona" id="label2" styleClass="label"
																text="Persona" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.tfPersona}" columns="40" id="tfPersona"
																styleClass="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnSeleccionarPersonaFisica_action}"
																binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
															<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnSeleccionarPersonaJuridica_action}"
																binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Juridica" />
															<a4j:commandButton action="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnLimpiarPersona_action}"
																binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnLimpiarPersona}" id="btnLimpiarPersona" styleClass="buttonLimpiarAjax"
																reRender="form1:tfPersona" oncomplete="cargarComportamientoJQuery(); focusearTfPersona()" title="Limpiar" />
														</td>
													</tr>
													<tr>
														<td>
															<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.lblNroInscripcion}" for="tfNroInscripcion" id="lblNroInscripcion"
																styleClass="label" text="Número Inscripción" />
														</td>
														<td>
															<ui:textField binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.tfNroInscripcion}" columns="10" id="tfNroInscripcion"
																styleClass="textField" onKeyPress="return ValidarNum(event,this)" />
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
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
															<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.lblPeriodo}" id="lblPeriodo" styleClass="label2" text="Período" />
														</td>
													</tr>
													<tr>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.lblAnio}" for="ddAnios" id="lblAnio" styleClass="label"
																text="Año" />
															<ui:dropDown binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.ddAnios}" id="ddAnios" styleClass="textField"
																items="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.ddAniosOptions.options}"
																valueChangeListener="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.eventoSeleccionAnio(evento)}">
																<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.lblCalendarios}" for="ddCalendarios" id="lblCalendarios"
																styleClass="label" text="Calendario" />
															<ui:dropDown binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.ddCalendarios}" id="ddCalendarios" styleClass="textField"
																items="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.ddCalendariosOptions.options}"
																valueChangeListener="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.eventoSeleccionCalendario(event)}">
																<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.lblPeriodos}" for="ddPeriodos" id="lblPeriodos"
																styleClass="label" text="Periodo" />
															<ui:dropDown binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.ddPeriodos}" id="ddPeriodos" styleClass="textField"
																items="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.ddPeriodosOptions.options}"
																valueChangeListener="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.eventoSeleccionPeriodo(event)}">
																<a4j:support event="onChange" reRender="form1:ddCuotas " />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.lblCuotas}" for="ddCuotas" id="lblCuotas" styleClass="label"
																text="Cuota" />
															<ui:dropDown binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.ddCuotas}" id="ddCuotas" styleClass="textField"
																items="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.ddCuotasOptions.options}" />
														</td>
													</tr>
													<tr>
														<td colspan="2"></td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton action="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnBuscar_action}"
												binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnBuscar}" id="btnBuscar" styleClass="btnAjax" value="Buscar"
												reRender="form1:table1,form1:stCantidadRegistros" />
											<a4j:commandButton action="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnReiniciar_action}"
												binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros1, form1:pgParametros2, form1:table1,form1:stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnCancelar_action}"
												binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<ui:messageGroup binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.messageGroup}" id="messageGroup" showDetail="true"
									showSummary="false" styleClass="grupoMsgAdmin" />
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.paginatedTable}" id="table1" width="622">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.gpBotones}" id="gpBotones">
														<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnSeleccionar_action}"
															binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
														<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.stSeparadorSeleccionar}" escape="false" id="staticText6" />
														<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnAgregar_action}"
															binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
														<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnModificar_action}"
															binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
														<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnEliminar_action}"
															binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
														<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.stSeparadorAccion}" escape="false" id="staticText8" />
														<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnConsultar_action}"
															binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
														<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.staticText11}" escape="false" id="staticText11"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnImprimir_action}"
															binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnImprimir}" id="btnImprimir" styleClass="button" text="Generar Impresión" />
														<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnImprimirReporte_action}"
															binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnImprimirReporte}" id="btnImprimirReporte" styleClass="button"
															text="Visualizar Reporte" onClick="newWindow = window.open('ImprimirDDJJSHPS.jsp', '_parent')" disabled="true" />
														<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.staticText12}" escape="false" id="staticText12"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnExportar_action}"
															binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
															onClick="return exportarReporte()" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarPersonaFisica').focus();
					</script>
					<ui:hiddenField binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.idPagina}" />
					<ui:hiddenField binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.idSubSesion}" />
					<ui:script binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{saic$grpSHPS$ABMDDJJSHPS$AdminDDJJSHPS.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
