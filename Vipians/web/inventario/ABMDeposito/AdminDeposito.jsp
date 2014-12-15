<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{inventario$ABMDeposito$AdminDeposito.page1}" id="page1">
			<ui:html binding="#{inventario$ABMDeposito$AdminDeposito.html1}" id="html1">
			<ui:head binding="#{inventario$ABMDeposito$AdminDeposito.head1}" id="head1" title="Administración de Depósitos">
				<ui:link binding="#{inventario$ABMDeposito$AdminDeposito.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{inventario$ABMDeposito$AdminDeposito.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init(); changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{inventario$ABMDeposito$AdminDeposito.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{inventario$ABMDeposito$AdminDeposito.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{inventario$ABMDeposito$AdminDeposito.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{inventario$ABMDeposito$AdminDeposito.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td></td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{inventario$ABMDeposito$AdminDeposito.lblNombre}" id="lblNombre" style="" styleClass="label"
																text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{inventario$ABMDeposito$AdminDeposito.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{inventario$ABMDeposito$AdminDeposito.lblArea}" id="lblArea" styleClass="label" text="Área" for="ddArea" />
														</td>
														<td>
															<ui:dropDown binding="#{inventario$ABMDeposito$AdminDeposito.ddArea}" id="ddArea"
																items="#{inventario$ABMDeposito$AdminDeposito.ddAreaOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{inventario$ABMDeposito$AdminDeposito.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{inventario$ABMDeposito$AdminDeposito.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{inventario$ABMDeposito$AdminDeposito.btnBuscar}"
												action="#{inventario$ABMDeposito$AdminDeposito.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{inventario$ABMDeposito$AdminDeposito.btnReiniciar_action}"
												binding="#{inventario$ABMDeposito$AdminDeposito.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{inventario$ABMDeposito$AdminDeposito.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{inventario$ABMDeposito$AdminDeposito.btnCancelar_action}"
												binding="#{inventario$ABMDeposito$AdminDeposito.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<!--</div>
                                -->
							<div>
								<ui:messageGroup binding="#{inventario$ABMDeposito$AdminDeposito.messageGroup}" id="messageGroup" showDetail="true"
									showSummary="false" styleClass="grupoMsgAdmin" />
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{inventario$ABMDeposito$AdminDeposito.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{inventario$ABMDeposito$AdminDeposito.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{inventario$ABMDeposito$AdminDeposito.btnSeleccionar_action}"
														binding="#{inventario$ABMDeposito$AdminDeposito.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{inventario$ABMDeposito$AdminDeposito.stSeparadorSeleccionar}" escape="false" id="staticText6" />
													<ui:button action="#{inventario$ABMDeposito$AdminDeposito.btnAgregar_action}"
														binding="#{inventario$ABMDeposito$AdminDeposito.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{inventario$ABMDeposito$AdminDeposito.btnModificar_action}"
														binding="#{inventario$ABMDeposito$AdminDeposito.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{inventario$ABMDeposito$AdminDeposito.btnEliminar_action}"
														binding="#{inventario$ABMDeposito$AdminDeposito.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{inventario$ABMDeposito$AdminDeposito.stSeparadorAccion}" escape="false" id="staticText8" />
													<ui:button action="#{inventario$ABMDeposito$AdminDeposito.btnConsultar_action}"
														binding="#{inventario$ABMDeposito$AdminDeposito.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText binding="#{inventario$ABMDeposito$AdminDeposito.staticText10}" escape="false" id="staticText10"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<a4j:commandButton action="#{inventario$ABMDeposito$AdminDeposito.btnCheckearStock_action}"
														binding="#{inventario$ABMDeposito$AdminDeposito.btnCheckearStock}" id="btnCheckearStock" styleClass="btnAjax"
														value="Checkear Stock" />
													<ui:staticText binding="#{inventario$ABMDeposito$AdminDeposito.staticText9}" escape="false" id="staticText9"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{inventario$ABMDeposito$AdminDeposito.btnExportar_action}"
														binding="#{inventario$ABMDeposito$AdminDeposito.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{inventario$ABMDeposito$AdminDeposito.paginatedTable.stSeparadorOrdenamiento}" id="separador_1" />
													<ui:imageHyperlink binding="#{inventario$ABMDeposito$AdminDeposito.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{inventario$ABMDeposito$AdminDeposito.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{inventario$ABMDeposito$AdminDeposito.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{inventario$ABMDeposito$AdminDeposito.hidIdPagina}" id="hidIdPagina"
						text="#{inventario$ABMDeposito$AdminDeposito.idPagina}" />
					<ui:hiddenField binding="#{inventario$ABMDeposito$AdminDeposito.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{inventario$ABMDeposito$AdminDeposito.idSubSesion}" />
					<ui:script binding="#{inventario$ABMDeposito$AdminDeposito.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{inventario$ABMDeposito$AdminDeposito.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
