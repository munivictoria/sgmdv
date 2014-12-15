<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMUnidad$AdminUnidad.page1}" id="page1">
			<ui:html binding="#{compras$ABMUnidad$AdminUnidad.html1}" id="html1">
			<ui:head binding="#{compras$ABMUnidad$AdminUnidad.head1}" id="head1" title="Administración de Unidad de Medidas">
				<ui:link binding="#{compras$ABMUnidad$AdminUnidad.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMUnidad$AdminUnidad.body1}" focus="form1:tfDescripcion" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMUnidad$AdminUnidad.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$ABMUnidad$AdminUnidad.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{compras$ABMUnidad$AdminUnidad.head1.title}" />
								</caption>
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
								<tbody>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{compras$ABMUnidad$AdminUnidad.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMUnidad$AdminUnidad.lblDescripcion}" for="tfDescripcion" id="lblDescripcion"
																styleClass="label" text="Descripción" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMUnidad$AdminUnidad.tfDescripcion}" columns="40" id="tfDescripcion" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{compras$ABMUnidad$AdminUnidad.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{compras$ABMUnidad$AdminUnidad.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{compras$ABMUnidad$AdminUnidad.btnBuscar}"
												action="#{compras$ABMUnidad$AdminUnidad.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{compras$ABMUnidad$AdminUnidad.btnReiniciar_action}"
												binding="#{compras$ABMUnidad$AdminUnidad.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{compras$ABMUnidad$AdminUnidad.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMUnidad$AdminUnidad.btnCancelar_action}" binding="#{compras$ABMUnidad$AdminUnidad.btnCancelar}"
												id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$ABMUnidad$AdminUnidad.messageGroup}" id="messageGroup" showDetail="true" showSummary="false" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{compras$ABMUnidad$AdminUnidad.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMUnidad$AdminUnidad.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{compras$ABMUnidad$AdminUnidad.btnSeleccionar_action}"
														binding="#{compras$ABMUnidad$AdminUnidad.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{compras$ABMUnidad$AdminUnidad.stSeparadorSeleccionar}" escape="false" id="stSeparador2" />
													<ui:button action="#{compras$ABMUnidad$AdminUnidad.btnAgregar_action}" binding="#{compras$ABMUnidad$AdminUnidad.btnAgregar}"
														id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMUnidad$AdminUnidad.btnModificar_action}"
														binding="#{compras$ABMUnidad$AdminUnidad.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{compras$ABMUnidad$AdminUnidad.btnEliminar_action}" binding="#{compras$ABMUnidad$AdminUnidad.btnEliminar}"
														id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{compras$ABMUnidad$AdminUnidad.stSeparadorAccion}" escape="false" id="stSeparador3" />
													<ui:button action="#{compras$ABMUnidad$AdminUnidad.btnConsultar_action}"
														binding="#{compras$ABMUnidad$AdminUnidad.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText binding="#{compras$ABMUnidad$AdminUnidad.stSeparador4}" escape="false" id="stSeparador4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMUnidad$AdminUnidad.btnImprimirReporte_action}"
														binding="#{compras$ABMUnidad$AdminUnidad.btnImprimirReporte}" id="btnImprimirReporte" disabled="true" styleClass="button"
														text="Visualizar Listado" onClick="newWindow = window.open('ImprimirUnidad.jsp', '_parent')" />
													<ui:staticText binding="#{compras$ABMUnidad$AdminUnidad.stSeparador5}" escape="false" id="stSeparador5"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMUnidad$AdminUnidad.btnExportar_action}" binding="#{compras$ABMUnidad$AdminUnidad.btnExportar}"
														id="btnExportar" styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{compras$ABMUnidad$AdminUnidad.paginatedTable.stSeparadorOrdenamiento}" id="separador_1" />
													<ui:imageHyperlink binding="#{compras$ABMUnidad$AdminUnidad.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{compras$ABMUnidad$AdminUnidad.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{compras$ABMUnidad$AdminUnidad.stCantidadRegistros}" id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfDescripcion').focus();
					</script>
					<ui:hiddenField binding="#{compras$ABMUnidad$AdminUnidad.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMUnidad$AdminUnidad.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMUnidad$AdminUnidad.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMUnidad$AdminUnidad.idSubSesion}" />
					<ui:script binding="#{compras$ABMUnidad$AdminUnidad.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
