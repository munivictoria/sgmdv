<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMUsuario$AdminUsuario.page1}" id="page1">
			<ui:html binding="#{framework$ABMUsuario$AdminUsuario.html1}" id="html1">
			<ui:head binding="#{framework$ABMUsuario$AdminUsuario.head1}" id="head1" title="Administración de Usuarios">
				<ui:link binding="#{framework$ABMUsuario$AdminUsuario.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMUsuario$AdminUsuario.body1}" focus="form1:tfUsuario" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMUsuario$AdminUsuario.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMUsuario$AdminUsuario.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{framework$ABMUsuario$AdminUsuario.head1.title}" />
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
											<ui:panelGroup binding="#{framework$ABMUsuario$AdminUsuario.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{framework$ABMUsuario$AdminUsuario.label1}" id="label1" styleClass="label" text="Persona" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMUsuario$AdminUsuario.tfPersona}" columns="40" disabled="true" id="tfPersona"
																styleClass="textField" />
															<ui:button action="#{framework$ABMUsuario$AdminUsuario.btnSeleccionarPersona_action}"
																binding="#{framework$ABMUsuario$AdminUsuario.btnSeleccionarPersona}" escape="false" id="btnSeleccionarPersona" mini="true"
																styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
																binding="#{framework$ABMUsuario$AdminUsuario.btnLimpiarPersona}"
																action="#{framework$ABMUsuario$AdminUsuario.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{framework$ABMUsuario$AdminUsuario.label2}" id="label2" styleClass="label" text="Nombre de Usuario" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMUsuario$AdminUsuario.tfUsuario}" columns="40" id="tfUsuario" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{framework$ABMUsuario$AdminUsuario.lblEstado}" id="lblEstado" styleClass="label" text="Estado" />
														</td>
														<td>
															<ui:dropDown binding="#{framework$ABMUsuario$AdminUsuario.ddEstado}" id="ddEstado"
																items="#{framework$ABMUsuario$AdminUsuario.ddEstadoDefaultOptions.options}" styleClass="textField"
																onChange="deshabilitarBotones(ddId)" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{framework$ABMUsuario$AdminUsuario.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{framework$ABMUsuario$AdminUsuario.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{framework$ABMUsuario$AdminUsuario.btnBuscar}"
												action="#{framework$ABMUsuario$AdminUsuario.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMUsuario$AdminUsuario.btnReiniciar_action}"
												binding="#{framework$ABMUsuario$AdminUsuario.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{framework$ABMUsuario$AdminUsuario.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMUsuario$AdminUsuario.btnCancelar_action}"
												binding="#{framework$ABMUsuario$AdminUsuario.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMUsuario$AdminUsuario.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{framework$ABMUsuario$AdminUsuario.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMUsuario$AdminUsuario.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMUsuario$AdminUsuario.btnSeleccionar_action}"
														binding="#{framework$ABMUsuario$AdminUsuario.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMUsuario$AdminUsuario.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMUsuario$AdminUsuario.btnAgregar_action}"
														binding="#{framework$ABMUsuario$AdminUsuario.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{framework$ABMUsuario$AdminUsuario.btnModificar_action}"
														binding="#{framework$ABMUsuario$AdminUsuario.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{framework$ABMUsuario$AdminUsuario.btnEliminar_action}"
														binding="#{framework$ABMUsuario$AdminUsuario.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{framework$ABMUsuario$AdminUsuario.stSeparadorAccion}" id="separador_2"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMUsuario$AdminUsuario.btnConsultar_action}"
														binding="#{framework$ABMUsuario$AdminUsuario.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{framework$ABMUsuario$AdminUsuario.textoSeparador}" id="separador_3" styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMUsuario$AdminUsuario.btnActivar_action}"
														binding="#{framework$ABMUsuario$AdminUsuario.btnActivar}" id="btnActivar" styleClass="button" text="Recuperar Usuario" />
													<ui:staticText text="#{framework$ABMUsuario$AdminUsuario.textoSeparador}" id="separador_4" styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMUsuario$AdminUsuario.btnExportar_action}"
														binding="#{framework$ABMUsuario$AdminUsuario.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{framework$ABMUsuario$AdminUsuario.paginatedTable.stSeparadorOrdenamiento}" id="separador_5"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{framework$ABMUsuario$AdminUsuario.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{framework$ABMUsuario$AdminUsuario.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{framework$ABMUsuario$AdminUsuario.stCantidadRegistros}" id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarPersona').focus();
					</script>
					<ui:hiddenField binding="#{framework$ABMUsuario$AdminUsuario.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMUsuario$AdminUsuario.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMUsuario$AdminUsuario.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMUsuario$AdminUsuario.idSubSesion}" />
					<ui:script binding="#{framework$ABMUsuario$AdminUsuario.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{framework$ABMUsuario$AdminUsuario.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
