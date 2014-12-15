<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.head1}" id="head1" title="Administración de Modelos">
				<ui:link binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init(); changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.lblNombre}" for="tfNombre" id="lblNombre"
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.tfNombre}" columns="40" id="tfNombre"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.lblMarca}" id="lblMarca" styleClass="label"
																text="Marca" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.ddMarca}" id="ddMarca"
																items="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.ddMarcaDefaultOptions.options}" styleClass="textField" />
															<ui:button action="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnSeleccionarMarca_action}"
																binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnSeleccionarMarca}" escape="false" id="btnSeleccionarMarca"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarMarca" reRender="form1:ddMarca" title="Limpiar"
																binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnLimpiarMarca}"
																action="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnLimpiarMarca_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.lblTipoVehiculo}" id="lblTipoVehiculo"
																styleClass="label" text="Tipo Vehiculo" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.ddTipoVehiculo}" id="ddTipoVehiculo"
																items="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.ddTipoVehiculoDefaultOptions.options}" styleClass="textField" />
															<ui:button action="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnSeleccionarTipoVehiculo_action}"
																binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnSeleccionarTipoVehiculo}" escape="false"
																id="btnSeleccionarTipoVehiculo" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarTipoVehiculo" reRender="form1:ddTipoVehiculo" title="Limpiar"
																binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnLimpiarTipoVehiculo}"
																action="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnLimpiarTipoVehiculo_action}" styleClass="buttonLimpiarAjax" />
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
																		<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.panelAtributoDinamico}"
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
															<ui:checkbox binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnBuscar}"
												action="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnReiniciar_action}"
												binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnCancelar_action}"
												binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnSeleccionar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.stSeparadorSeleccionar}" escape="false"
														id="separador2" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnAgregar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnModificar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnEliminar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.stSeparadorAccion}" escape="false" id="separador3" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnConsultar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.stSeparador4}" escape="false" id="separador4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnExportar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.lblEncontrados}" id="lblEncontrados" styleClass="label"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpAutomotor$ABMModelo$AdminModelo.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
