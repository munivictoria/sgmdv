<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMArea$AdminArea.page1}" id="page1">
			<ui:html binding="#{framework$ABMArea$AdminArea.html1}" id="html1">
			<ui:head binding="#{framework$ABMArea$AdminArea.head1}" id="head1" title="Administración de Áreas">
				<ui:link binding="#{framework$ABMArea$AdminArea.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMArea$AdminArea.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMArea$AdminArea.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMArea$AdminArea.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{framework$ABMArea$AdminArea.head1.title}" />
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
											<ui:panelGroup binding="#{framework$ABMArea$AdminArea.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMArea$AdminArea.label2}" for="tfNombre" id="label2" styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMArea$AdminArea.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{framework$ABMArea$AdminArea.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{framework$ABMArea$AdminArea.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{framework$ABMArea$AdminArea.btnBuscar}" action="#{framework$ABMArea$AdminArea.btnBuscar_action}"
												id="btnBuscar" value="Buscar" styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros"
												oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMArea$AdminArea.btnReiniciar_action}"
												binding="#{framework$ABMArea$AdminArea.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{framework$ABMArea$AdminArea.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMArea$AdminArea.btnCancelar_action}" binding="#{framework$ABMArea$AdminArea.btnCancelar}"
												id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMArea$AdminArea.messageGroup}" id="messageGroup" showDetail="true" showSummary="false"
										styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{framework$ABMArea$AdminArea.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMArea$AdminArea.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMArea$AdminArea.btnSeleccionar_action}"
														binding="#{framework$ABMArea$AdminArea.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMArea$AdminArea.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMArea$AdminArea.btnAgregar_action}" binding="#{framework$ABMArea$AdminArea.btnAgregar}"
														id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{framework$ABMArea$AdminArea.btnModificar_action}" binding="#{framework$ABMArea$AdminArea.btnModificar}"
														id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{framework$ABMArea$AdminArea.btnEliminar_action}" binding="#{framework$ABMArea$AdminArea.btnEliminar}"
														id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{framework$ABMArea$AdminArea.stSeparadorAccion}" id="separador_2" styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMArea$AdminArea.btnConsultar_action}" binding="#{framework$ABMArea$AdminArea.btnConsultar}"
														id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{framework$ABMArea$AdminArea.textoSeparador}" id="separador_3" styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMArea$AdminArea.btnExportar_action}" binding="#{framework$ABMArea$AdminArea.btnExportar}"
														id="btnExportar" styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{framework$ABMArea$AdminArea.paginatedTable.stSeparadorOrdenamiento}" id="separador_4"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{framework$ABMArea$AdminArea.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{framework$ABMArea$AdminArea.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{framework$ABMArea$AdminArea.stCantidadRegistros}" id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{framework$ABMArea$AdminArea.hidIdPagina}" id="hidIdPagina" text="#{framework$ABMArea$AdminArea.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMArea$AdminArea.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMArea$AdminArea.idSubSesion}" />
					<ui:script binding="#{framework$ABMArea$AdminArea.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{framework$ABMArea$AdminArea.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
