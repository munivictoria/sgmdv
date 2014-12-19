<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.page1}" id="page1">
			<ui:html binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.html1}" id="html1">
			<ui:head binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.head1}" id="head1" title="Actividades CIIU">
				<ui:link binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.body1}" focus="form1:tfNombre" id="body1"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar(); " onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.head1.title}" />
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
											<ui:panelGroup binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label for="ddSeccion" id="lblSeccion" styleClass="label" text="Sección" />
														</td>
														<td>
															<ui:dropDown binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.ddSeccion}" id="ddSeccion"
																items="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.ddSeccionDefaultOptions.options}" styleClass="textField"
																valueChangeListener="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.valueChangeEvent(event)}">
																<a4j:support event="onChange" reRender="form1:ddGrupo" />
															</ui:dropDown>
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label for="ddGrupo" id="lblGrupo" styleClass="label" text="Grupo" />
														</td>
														<td>
															<ui:dropDown binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.ddGrupo}" id="ddGrupo"
																items="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.ddGrupoDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
												</table>
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.lblCodigo}" for="tfCodigo" id="lblCodigo" styleClass="label"
																text="Código" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.tfCodigo}" columns="40" id="tfCodigo" styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.lblDescripcion}" for="tfDescripcion" id="lblDescripcion"
																styleClass="label" text="Descripción" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.tfDescripcion}" columns="40" id="tfDescripcion"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td></td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnBuscar}"
												action="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnReiniciar_action}"
												binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnCancelar_action}"
												binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnSeleccionar_action}"
														binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnAgregar_action}" 
														binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnAgregar}"
														id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnModificar_action}" 
														binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnModificar}"
														id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnEliminar_action}" 
														binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnEliminar}"
														id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:button action="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnConsultar_action}"
														binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.textoSeparador}" id="separador_2"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnExportar_action}"
														binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.paginatedTable.stSeparadorOrdenamiento}" id="separador_3"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.idSubSesion}" />
					<ui:script binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{framework$ABMCodigoCiiu$AdminCodigoCiiu.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
