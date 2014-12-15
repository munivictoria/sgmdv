<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.head1}" id="head1"
				title="Administración de Obligaciones: Automotor">
				<ui:link binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.link1}" id="link1"
					url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfPersona", "persona", nombreBean, "setPersonaAutocompletar");
					}

					function focusearTfPersonaSeleccionada() {
						$("#form1\\:tfPersona").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.body1}" focus="form1:tfPatente" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center" nowrap="nowrap">
											<ui:staticText escape="false" id="stFiltrarPor" styleClass="textoFiltrarPor" text="Filtrar por" />
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<hr />
										</td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.lblNroCuenta}" id="label5"
																text="Número de Cuenta" style="" styleClass="label" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.tfNumeroCuenta}" columns="20"
																id="tfNumeroCuenta" styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.lblPersonaSeleccionada}"
																id="lblPersona" styleClass="label" text="Persona" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.tfPersonaSeleccionada}"
																columns="40" id="tfPersona"
																styleClass="#{framework$ABMContrato$AdminContrato.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{framework$ABMContrato$AdminContrato.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnSeleccionarPersonaFisica_action}"
																binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
															<ui:button
																action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnSeleccionarPersonaJuridica_action}"
																binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnSeleccionarPersonaJuridica}"
																escape="false" id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ"
																toolTip="Seleccionar Persona Juridica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
																binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnLimpiarPersona}"
																action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnLimpiarPersona_action}"
																styleClass="buttonLimpiarAjax" oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.lblPatente}" for="tfPatente"
																id="lblPatente" styleClass="label" text="Patente" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.tfPatente}" columns="20"
																id="tfPatente" styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.lblVehiculo}" id="lblVehiculo"
																styleClass="label" text="Vehiculo" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.tfVehiculo}" columns="40"
																disabled="true" id="tfVehiculo" styleClass="textField" />
															<ui:button action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnSeleccionarVehiculo_action}"
																binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnSeleccionarVehiculo}" escape="false"
																id="btnSeleccionarVehiculo" mini="true" styleClass="button" text="SV" toolTip="Seleccionar Vehiculo" />
															<a4j:commandButton id="btnLimpiarVehiculo" reRender="form1:tfVehiculo" title="Limpiar"
																binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnLimpiarVehiculo}"
																action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnLimpiarVehiculo_action}"
																styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.lblEstado}" id="label4" style=""
																styleClass="label" text="Estado" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.ddEstado}" id="ddEstado"
																items="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.ddEstadoDefaultOptions.options}"
																styleClass="textField" />
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
																		<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.panelAtributoDinamico}"
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
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox
																binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.tablaBusquedaLogs}"
																id="tablaBusquedaLogs" />
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tr>
									<td></td>
								</tr>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnBuscar}"
												action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1, form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnReiniciar_action}"
												binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1, form1:stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.stSeparador1}" escape="false"
												id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnCancelar_action}"
												binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnCancelar}" id="btnCancelar"
												styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.gpSeleccion}" id="gpSeleccion">
											<ui:label binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.label3}" for="ddPlantillaObligacion"
												id="label3" styleClass="label" text="Plantilla de Obligación" />
											<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.staticText5}" escape="false"
												id="staticText5" styleClass="label" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
											<ui:dropDown binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.ddPlantillaObligacion}"
												id="ddPlantillaObligacion"
												items="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.ddPlantillaObligacionOptions.options}"
												styleClass="textField" />
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.groupPanel1}" id="groupPanel1"
													style="">
													<ui:button action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnSeleccionar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.stSeparadorSeleccionar}"
														escape="false" id="separador2" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnAgregar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnAgregar}" id="btnAgregar"
														styleClass="button" text="Agregar" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnModificar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnModificar}" id="btnModificar"
														styleClass="button" text="Modificar" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnEliminar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnEliminar}" id="btnEliminar"
														styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.stSeparadorAccion}"
														escape="false" id="separador3" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnConsultar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnConsultar}" id="btnConsultar"
														styleClass="button" text="Consultar" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.stSeparador4}" escape="false"
														id="separador4" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnExportar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.btnExportar}" id="btnExportar"
														styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText
														binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink
														binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarPersonaFisica').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpAutomotor$ABMDocEspAutomotor$AdminDocEspAutomotor.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
