<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMProcesoDB$AdminProcesoDB.page1}" id="page1">
			<ui:html binding="#{framework$ABMProcesoDB$AdminProcesoDB.html1}" id="html1">
			<ui:head binding="#{framework$ABMProcesoDB$AdminProcesoDB.head1}" id="head1" title="Administración de Provincias">
				<ui:link binding="#{framework$ABMProcesoDB$AdminProcesoDB.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMProcesoDB$AdminProcesoDB.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMProcesoDB$AdminProcesoDB.form1}" id="form1"
					virtualFormsConfig="vfCancelar | hidIdPagina hidIdSubSesion | btnCancelar">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMProcesoDB$AdminProcesoDB.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{framework$ABMProcesoDB$AdminProcesoDB.head1.title}" />
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
											<ui:panelGroup binding="#{framework$ABMProcesoDB$AdminProcesoDB.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label for="tfNombre" id="lblNombre" styleClass="label"
																text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMProcesoDB$AdminProcesoDB.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label  id="lblNombreProceso" styleClass="label" text="Nombre proceso" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMProcesoDB$AdminProcesoDB.tfNombreProceso}" columns="40" id="tfNombreProceso" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{framework$ABMProcesoDB$AdminProcesoDB.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{framework$ABMProcesoDB$AdminProcesoDB.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{framework$ABMProcesoDB$AdminProcesoDB.btnBuscar}"
												action="#{framework$ABMProcesoDB$AdminProcesoDB.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMProcesoDB$AdminProcesoDB.btnReiniciar_action}"
												binding="#{framework$ABMProcesoDB$AdminProcesoDB.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMProcesoDB$AdminProcesoDB.btnCancelar_action}"
												binding="#{framework$ABMProcesoDB$AdminProcesoDB.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMProcesoDB$AdminProcesoDB.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{framework$ABMProcesoDB$AdminProcesoDB.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMProcesoDB$AdminProcesoDB.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMProcesoDB$AdminProcesoDB.btnSeleccionar_action}"
														binding="#{framework$ABMProcesoDB$AdminProcesoDB.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMProcesoDB$AdminProcesoDB.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMProcesoDB$AdminProcesoDB.btnAgregar_action}"
														binding="#{framework$ABMProcesoDB$AdminProcesoDB.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{framework$ABMProcesoDB$AdminProcesoDB.btnModificar_action}"
														binding="#{framework$ABMProcesoDB$AdminProcesoDB.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{framework$ABMProcesoDB$AdminProcesoDB.btnEliminar_action}"
														binding="#{framework$ABMProcesoDB$AdminProcesoDB.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{framework$ABMProcesoDB$AdminProcesoDB.stSeparadorAccion}" id="separador_2"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMProcesoDB$AdminProcesoDB.btnConsultar_action}"
														binding="#{framework$ABMProcesoDB$AdminProcesoDB.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
														<ui:button action="#{framework$ABMProcesoDB$AdminProcesoDB.btnEjecutar_action}"
														binding="#{framework$ABMProcesoDB$AdminProcesoDB.btnEjecutar}" id="btnEjecutar" styleClass="button" text="Ejecutar" />
													<ui:staticText text="#{framework$ABMProcesoDB$AdminProcesoDB.textoSeparador}" id="separador_3"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMProcesoDB$AdminProcesoDB.btnExportar_action}"
														binding="#{framework$ABMProcesoDB$AdminProcesoDB.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{framework$ABMProcesoDB$AdminProcesoDB.paginatedTable.stSeparadorOrdenamiento}" id="separador_4"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{framework$ABMProcesoDB$AdminProcesoDB.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{framework$ABMProcesoDB$AdminProcesoDB.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{framework$ABMProcesoDB$AdminProcesoDB.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{framework$ABMProcesoDB$AdminProcesoDB.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMProcesoDB$AdminProcesoDB.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMProcesoDB$AdminProcesoDB.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMProcesoDB$AdminProcesoDB.idSubSesion}" />
					<ui:script binding="#{framework$ABMProcesoDB$AdminProcesoDB.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{framework$ABMProcesoDB$AdminProcesoDB.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
