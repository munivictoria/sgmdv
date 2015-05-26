<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{comunes$ABMIngresoVario$AdminIngresoVario.page1}" id="page1">
			<ui:html binding="#{comunes$ABMIngresoVario$AdminIngresoVario.html1}" id="html1">
			<ui:head binding="#{comunes$ABMIngresoVario$AdminIngresoVario.head1}" id="head1" title="Administración de Ingresos Varios">
				<ui:link binding="#{comunes$ABMIngresoVario$AdminIngresoVario.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "comunes$ABMIngresoVario$AdminIngresoVario";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfPersonaSeleccionada", "persona", nombreBean, "setPersonaAutocompletar");
						autoCompletarEnTextField("#form1:tfConceptoIngresoVario", "conceptoIngresoVario", nombreBean, "setConceptoAutocompletar");
						calendarioEnTextField("#form1:tfFechaDesde");
						calendarioEnTextField("#form1:tfFechaHasta");
					}

					function focusearTfPersonaSeleccionada() {
						$("#form1\\:tfPersonaSeleccionada").focus();
					}

					function focusearTfConcepto() {
						$("#form1\\:tfConceptoIngresoVario").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{comunes$ABMIngresoVario$AdminIngresoVario.body1}" focus="form1:btnSeleccionarPersonaFisica" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{comunes$ABMIngresoVario$AdminIngresoVario.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{comunes$ABMIngresoVario$AdminIngresoVario.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{comunes$ABMIngresoVario$AdminIngresoVario.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{comunes$ABMIngresoVario$AdminIngresoVario.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td></td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{comunes$ABMIngresoVario$AdminIngresoVario.lblPersonaSeleccionada}" for="tfPersonaSeleccionada"
																id="lblPersonaSeleccionada" styleClass="label" text="Persona" />
														</td>
														<td colspan="3">
															<ui:textField binding="#{comunes$ABMIngresoVario$AdminIngresoVario.tfPersonaSeleccionada}" columns="40"
																id="tfPersonaSeleccionada"
																styleClass="#{comunes$ABMIngresoVario$AdminIngresoVario.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{comunes$ABMIngresoVario$AdminIngresoVario.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{comunes$ABMIngresoVario$AdminIngresoVario.btnSeleccionarPersonaFisica_action}"
																binding="#{comunes$ABMIngresoVario$AdminIngresoVario.btnSeleccionarPersonaFisica}" id="btnSeleccionarPersonaFisica"
																mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Física" />
															<ui:button action="#{comunes$ABMIngresoVario$AdminIngresoVario.btnSeleccionarPersonaJuridica_action}"
																binding="#{comunes$ABMIngresoVario$AdminIngresoVario.btnSeleccionarPersonaJuridica}" id="btnSeleccionarPersonaJuridica"
																mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Jurídica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaSeleccionada" title="Limpiar"
																binding="#{comunes$ABMIngresoVario$AdminIngresoVario.btnLimpiarPersona}"
																action="#{comunes$ABMIngresoVario$AdminIngresoVario.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label for="tfConceptoIngresoVario" id="lblConceptoIngresoVario" styleClass="label" text="Concepto" />
														</td>
														<td>
															<ui:textField binding="#{comunes$ABMIngresoVario$AdminIngresoVario.tfConcepto}" columns="40" id="tfConceptoIngresoVario"
																styleClass="#{comunes$ABMIngresoVario$AdminIngresoVario.paginatedTable.filtro.conceptoIngresoVario != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{comunes$ABMIngresoVario$AdminIngresoVario.paginatedTable.filtro.conceptoIngresoVario != null}" />
															<a4j:commandButton id="btnLimpiarConcepto" reRender="form1:tfConceptoIngresoVario" title="Limpiar"
																binding="#{comunes$ABMIngresoVario$AdminIngresoVario.btnLimpiarConcepto}"
																action="#{comunes$ABMIngresoVario$AdminIngresoVario.btnLimpiarConceptoIngresoVario_action}" 
																styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfConcepto();"/>
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{comunes$ABMIngresoVario$AdminIngresoVario.lblEstado}" for="ddEstado" id="lblEstado" styleClass="label"
																text="Estado" />
														</td>
														<td>
															<ui:dropDown binding="#{comunes$ABMIngresoVario$AdminIngresoVario.ddEstado}" id="ddEstado"
																items="#{comunes$ABMIngresoVario$AdminIngresoVario.ddEstadoDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label for="tfFechaDesde" id="lblFechaDesde" styleClass="label" text="Fecha desde" />
														</td>
														<td>
															<ui:textField id="tfFechaDesde" binding="#{comunes$ABMIngresoVario$AdminIngresoVario.tfFechaDesde}" styleClass="textField"/>
														</td>
														<td align="right" nowrap="true">
															<ui:label for="tfFechaHasta" id="lblFechaHasta" styleClass="label" text="Fecha hasta" />
														</td>
														<td>
															<ui:textField id="tfFechaHasta" binding="#{comunes$ABMIngresoVario$AdminIngresoVario.tfFechaHasta}" styleClass="textField"/>
														</td>
													</tr>
													<tr>
														<td></td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{comunes$ABMIngresoVario$AdminIngresoVario.btnBuscar}"
												action="#{comunes$ABMIngresoVario$AdminIngresoVario.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{comunes$ABMIngresoVario$AdminIngresoVario.btnReiniciar_action}"
												binding="#{comunes$ABMIngresoVario$AdminIngresoVario.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{comunes$ABMIngresoVario$AdminIngresoVario.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{comunes$ABMIngresoVario$AdminIngresoVario.btnCancelar_action}"
												binding="#{comunes$ABMIngresoVario$AdminIngresoVario.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{comunes$ABMIngresoVario$AdminIngresoVario.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{comunes$ABMIngresoVario$AdminIngresoVario.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{comunes$ABMIngresoVario$AdminIngresoVario.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{comunes$ABMIngresoVario$AdminIngresoVario.btnSeleccionar_action}"
														binding="#{comunes$ABMIngresoVario$AdminIngresoVario.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{comunes$ABMIngresoVario$AdminIngresoVario.stSeparadorSeleccionar}" escape="false" id="stSeparador2" />
													<ui:button action="#{comunes$ABMIngresoVario$AdminIngresoVario.btnAgregar_action}"
														binding="#{comunes$ABMIngresoVario$AdminIngresoVario.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{comunes$ABMIngresoVario$AdminIngresoVario.btnModificar_action}"
														binding="#{comunes$ABMIngresoVario$AdminIngresoVario.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{comunes$ABMIngresoVario$AdminIngresoVario.btnEliminar_action}"
														binding="#{comunes$ABMIngresoVario$AdminIngresoVario.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{comunes$ABMIngresoVario$AdminIngresoVario.stSeparadorAccion}" escape="false" id="stSeparador3" />
													<ui:button action="#{comunes$ABMIngresoVario$AdminIngresoVario.btnConsultar_action}"
														binding="#{comunes$ABMIngresoVario$AdminIngresoVario.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{comunes$ABMIngresoVario$AdminIngresoVario.textoSeparador}" escape="false" id="stSeparador4" />
													<ui:button action="#{comunes$ABMIngresoVario$AdminIngresoVario.btnRefinanciar_action}"
															binding="#{comunes$ABMIngresoVario$AdminIngresoVario.btnRefinanciar}"
															id="btnRefinanciar" styleClass="button"
															text="Refinanciar" />
													<ui:button action="#{comunes$ABMIngresoVario$AdminIngresoVario.btnImprimir_action}"
														binding="#{comunes$ABMIngresoVario$AdminIngresoVario.btnImprimir}"
														onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" id="btnImprimir" styleClass="button"
														text="Imprimir" />
													<ui:button action="#{comunes$ABMIngresoVario$AdminIngresoVario.btnExportar_action}"
														binding="#{comunes$ABMIngresoVario$AdminIngresoVario.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{comunes$ABMIngresoVario$AdminIngresoVario.paginatedTable.stSeparadorOrdenamiento}" id="separador_1" />
													<ui:imageHyperlink binding="#{comunes$ABMIngresoVario$AdminIngresoVario.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{comunes$ABMIngresoVario$AdminIngresoVario.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{comunes$ABMIngresoVario$AdminIngresoVario.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarPersonaFisica').focus();
					</script>
					<ui:hiddenField binding="#{comunes$ABMIngresoVario$AdminIngresoVario.hidIdPagina}" id="hidIdPagina"
						text="#{comunes$ABMIngresoVario$AdminIngresoVario.idPagina}" />
					<ui:hiddenField binding="#{comunes$ABMIngresoVario$AdminIngresoVario.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{comunes$ABMIngresoVario$AdminIngresoVario.idSubSesion}" />
					<ui:script binding="#{comunes$ABMIngresoVario$AdminIngresoVario.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{comunes$ABMIngresoVario$AdminIngresoVario.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
