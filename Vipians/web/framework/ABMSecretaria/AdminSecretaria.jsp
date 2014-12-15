<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMSecretaria$AdminSecretaria.page1}" id="page1">
			<ui:html binding="#{framework$ABMSecretaria$AdminSecretaria.html1}" id="html1">
			<ui:head binding="#{framework$ABMSecretaria$AdminSecretaria.head1}" id="head1" title="Administración de Secretarías">
				<ui:link binding="#{framework$ABMSecretaria$AdminSecretaria.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMSecretaria$AdminSecretaria.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMSecretaria$AdminSecretaria.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMSecretaria$AdminSecretaria.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{framework$ABMSecretaria$AdminSecretaria.head1.title}" />
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
											<ui:panelGroup binding="#{framework$ABMSecretaria$AdminSecretaria.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMSecretaria$AdminSecretaria.lbNombre}" for="tfNombre" id="lbNombre" styleClass="label"
																text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMSecretaria$AdminSecretaria.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{framework$ABMSecretaria$AdminSecretaria.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{framework$ABMSecretaria$AdminSecretaria.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{framework$ABMSecretaria$AdminSecretaria.btnBuscar}"
												action="#{framework$ABMSecretaria$AdminSecretaria.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMSecretaria$AdminSecretaria.btnReiniciar_action}"
												binding="#{framework$ABMSecretaria$AdminSecretaria.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{framework$ABMSecretaria$AdminSecretaria.staticText1}" escape="false" id="staticText1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMSecretaria$AdminSecretaria.btnCancelar_action}"
												binding="#{framework$ABMSecretaria$AdminSecretaria.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMSecretaria$AdminSecretaria.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{framework$ABMSecretaria$AdminSecretaria.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMSecretaria$AdminSecretaria.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMSecretaria$AdminSecretaria.btnSeleccionar_action}"
														binding="#{framework$ABMSecretaria$AdminSecretaria.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMSecretaria$AdminSecretaria.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMSecretaria$AdminSecretaria.btnAgregar_action}"
														binding="#{framework$ABMSecretaria$AdminSecretaria.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{framework$ABMSecretaria$AdminSecretaria.btnModificar_action}"
														binding="#{framework$ABMSecretaria$AdminSecretaria.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{framework$ABMSecretaria$AdminSecretaria.btnEliminar_action}"
														binding="#{framework$ABMSecretaria$AdminSecretaria.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{framework$ABMSecretaria$AdminSecretaria.stSeparadorAccion}" id="separador_2"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMSecretaria$AdminSecretaria.btnConsultar_action}"
														binding="#{framework$ABMSecretaria$AdminSecretaria.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText binding="#{framework$ABMSecretaria$AdminSecretaria.paginatedTable.stSeparadorOrdenamiento}" id="separador_4"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{framework$ABMSecretaria$AdminSecretaria.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{framework$ABMSecretaria$AdminSecretaria.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{framework$ABMSecretaria$AdminSecretaria.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{framework$ABMSecretaria$AdminSecretaria.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMSecretaria$AdminSecretaria.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMSecretaria$AdminSecretaria.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMSecretaria$AdminSecretaria.idSubSesion}" />
					<ui:script binding="#{framework$ABMSecretaria$AdminSecretaria.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{framework$ABMSecretaria$AdminSecretaria.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
