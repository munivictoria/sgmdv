<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.head1}" id="head1" title="Administración de Vehículos">
				<ui:link binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfModelo", "modeloVehiculo", nombreBean, "setModeloVehiculoAutocompletar");
						autoCompletarEnTextField("#form1:tfPersonaSeleccionada", "persona", nombreBean, "setPersonaAutocompletar");
						calendarioEnTextField("#form1:tfFechaInscripcion");
					}

					function focusearTfModeloVehiculo() {
						$("#form1\\:tfModelo").focus();
					}

					function focusearTfPersona() {
						$("#form1\\:tfPersonaSeleccionada").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.body1}" focus="form1:tfPatente" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar(); "
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td>
															<div class="div" style="width: 410px; height: 15px;">Vehículo</div>
															<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
																<tr>
																	<td align="right" nowrap="nowrap">
																		<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.lblPatente}" for="tfPatente" id="lblPatente"
																			style="" styleClass="label" text="Patente" />
																	</td>
																	<td>
																		<ui:textField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.tfPatente}" id="tfPatente" maxLength="10"
																			styleClass="textField" />
																	</td>
																</tr>
																<tr>
																	<td align="right" nowrap="nowrap">
																		<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.lblModelo}" for="tfModelo" id="lblModelo"
																			styleClass="label" text="Modelo" />
																	</td>
																	<td nowrap="nowrap">
																		<ui:textField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.tfModelo}" columns="40" id="tfModelo"
																			styleClass="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.paginatedTable.filtro.modelo != null ? 'textFieldDisabled' : 'textField'}"
																			disabled="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.paginatedTable.filtro.modelo != null}" />
																		<ui:button action="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnSeleccionarModelo_action}"
																			binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnSeleccionarModelo}" escape="false"
																			id="btnSeleccionarModelo" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
																		<a4j:commandButton id="btnLimpiarModelo" reRender="form1:tfModelo" title="Limpiar"
																			binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnLimpiarModelo}"
																			action="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnLimpiarModelo_action}" styleClass="buttonLimpiarAjax"
																			oncomplete="cargarComportamientoJQuery(); focusearTfModeloVehiculo();" />
																	</td>
																</tr>
																<tr>
																	<td align="right" nowrap="nowrap">
																		<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.lblMotor}" for="tfMotor" id="lblMotor" style=""
																			styleClass="label" text="Motor" />
																	</td>
																	<td>
																		<ui:textField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.tfMotor}" id="tfMotor"
																			styleClass="textField" />
																	</td>
																</tr>
																<tr>
																	<td nowrap="nowrap" colspan="2">
																		<table border="0" width="400">
																			<tr>
																				<td>
																					<ui:label id="lblVacio" styleClass="label" text="" />
																				</td>
																				<td>
																					<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.panelAtributoDinamico}"
																						id="panelAtributoDinamico">
																						<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
																					</ui:panelGroup>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
														</td>
														<td>
															<div class="div" style="width: 470px; height: 15px;">Titulo Propiedad</div>
															<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
																<tr>
																	<td align="right" nowrap="nowrap">
																		<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.lblPersonaSeleccionada}"
																			for="tfPersonaSeleccionada" id="lblPersonaSeleccionada" styleClass="label" text="Propietario" />
																	</td>
																	<td nowrap="nowrap">
																		<ui:textField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.tfPersonaSeleccionada}" columns="40"
																			id="tfPersonaSeleccionada"
																			styleClass="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.paginatedTable.filtro.propietario != null ? 'textFieldDisabled' : 'textField'}"
																			disabled="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.paginatedTable.filtro.propietario != null}" />
																		<ui:button action="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnSeleccionarPersonaFisica_action}"
																			binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnSeleccionarPersonaFisica}" escape="false"
																			id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Física" />
																		<ui:button action="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnSeleccionarPersonaJuridica_action}"
																			binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnSeleccionarPersonaJuridica}" escape="false"
																			id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Jurídica" />
																		<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaSeleccionada" title="Limpiar"
																			binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnLimpiarPersona}"
																			action="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax"
																			oncomplete="cargarComportamientoJQuery(); focusearTfPersona();" />
																	</td>
																</tr>
																<tr>
																	<td align="right" nowrap="nowrap">
																		<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.lblFechaInscripcion}" id="lblFechaInscripcion"
																			styleClass="label" text="Fecha Inscripción" for="tfFechaInscripcion" />
																	</td>
																	<td>
																		<ui:textField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.tfFechaInscripcion}"
																			id="tfFechaInscripcion" styleClass="textField" columns="10" />
																	</td>
																</tr>
																<tr>
																	<td align="right" nowrap="nowrap">
																		<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.lblCodigo}" id="lblCodigo" styleClass="label"
																			text="Código" for="tfCodigo" />
																	</td>
																	<td>
																		<ui:textField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.tfCodigo}" id="tfCodigo"
																			styleClass="textField" />
																	</td>
																</tr>
																<tr>
																	<td nowrap="nowrap" colspan="2">
																		<table border="0" width="400">
																			<tr>
																				<td>
																					<ui:label id="lblVacio3" styleClass="label" text="" />
																				</td>
																				<td>
																					<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.panelAtributoDinamico2}"
																						id="panelAtributoDinamico2">
																						<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
																					</ui:panelGroup>
																				</td>
																			</tr>
																		</table>
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
															<ui:checkbox binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnBuscar}"
												action="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnReiniciar_action}"
												binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnCancelar_action}"
												binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table augmentTitle="false" binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnSeleccionar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.stSeparadorSeleccionar}" escape="false"
														id="stSeparador2" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnAgregar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnModificar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnEliminar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.stSeparadorAccion}" escape="false"
														id="stSeparador3" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnConsultar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.stSeparador4}" escape="false" id="stSeparador4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnExportar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfPatente').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpAutomotor$ABMVehiculo$AdminVehiculo.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
