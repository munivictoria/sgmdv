<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.head1}" id="head1"
				title="Administración de Códigos de Servicio de OySP">
				<ui:link binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.body1}" focus="form1:tfCodigo" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.lblCodigo}" for="tfCodigo" id="lblCodigo"
																styleClass="label" text="Código" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.tfCodigo}" columns="10" id="tfCodigo"
																onKeyPress="return ValidarNum(event,this)" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.lblNombre}" for="tfNombre" id="lblNombre"
																styleClass="label" text="Servicio" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.tfNombre}" columns="40" id="tfNombre"
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
																		<ui:panelGroup binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.panelAtributoDinamico}"
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
															<ui:checkbox binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnBuscar}"
												action="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnReiniciar_action}"
												binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.stSeparador3}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnCancelar_action}"
												binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.groupPanel1}" id="groupPanel1">
													<ui:button action="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnSeleccionar_action}"
														binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.stSeparadorSeleccionar}" escape="false"
														id="staticText6" />
													<ui:button action="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnAgregar_action}"
														binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnModificar_action}"
														binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnEliminar_action}"
														binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.stSeparadorAccion}" escape="false"
														id="staticText8" />
													<ui:button action="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnConsultar_action}"
														binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.stSeparador4}" escape="false" id="staticText12"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnExportar_action}"
														binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
							<td></td>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfCodigo').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpOSP$ABMServicioOSP$AdminServicioOSP.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
