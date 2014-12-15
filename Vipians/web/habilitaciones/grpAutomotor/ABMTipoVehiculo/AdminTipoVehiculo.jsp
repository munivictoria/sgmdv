<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.head1}" id="head1"
				title="Administración de Tipos de Vehiculos">
				<ui:link binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.lblNombre}" for="tfNombre" id="lblNombre"
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.tfNombre}" columns="40" id="tfNombre"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.lblCodigo}" for="tfCodigo" id="lblCodigo"
																styleClass="label" text="Codigo" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.tfCodigo}" columns="40" id="tfCodigo"
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
																		<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.panelAtributoDinamico}"
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
															<ui:checkbox binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnBuscar}"
												action="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1, form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnReiniciar_action}"
												binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1, form1:stCantidadRegistros" />
											<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.stSeparador1}" escape="false"
												id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnCancelar_action}"
												binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnSeleccionar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.stSeparadorSeleccionar}" escape="false"
														id="separador2" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnAgregar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnModificar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnEliminar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.stSeparadorAccion}" escape="false"
														id="separador3" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnConsultar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.stSeparador4}" escape="false"
														id="separador4" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnExportar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText
														binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpAutomotor$ABMTipoVehiculo$AdminTipoVehiculo.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
