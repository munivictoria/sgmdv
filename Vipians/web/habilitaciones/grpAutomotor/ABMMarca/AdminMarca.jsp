<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.head1}" id="head1" title="Administración de Marcas">
				<ui:link binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.lblNombre}" for="tfNombre" id="lblNombre"
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.tfNombre}" columns="40" id="tfNombre"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.lblCodigo}" for="tfCodigo" id="lblCodigo"
																styleClass="label" text="Codigo" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.tfCodigo}" columns="40" id="tfCodigo"
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
																		<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.panelAtributoDinamico}"
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
															<ui:checkbox binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnBuscar}"
												action="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnReiniciar_action}"
												binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnCancelar_action}"
												binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnSeleccionar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.stSeparadorSeleccionar}" escape="false"
														id="separador2" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnAgregar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnModificar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnEliminar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.stSeparadorAccion}" escape="false" id="separador3" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnConsultar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.stSeparador4}" escape="false" id="separador4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnExportar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpAutomotor$ABMMarca$AdminMarca.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
