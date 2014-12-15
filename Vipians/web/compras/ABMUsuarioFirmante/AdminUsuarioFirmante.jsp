<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.page1}" id="page1">
			<ui:html binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.html1}" id="html1">
			<ui:head binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.head1}" id="head1" title="Administración de Usuarios Firmantes">
				<ui:link binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.body1}" focus="form1:btnBuscar" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar(); "
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.head1.title}" />
								</caption>
								<tbody>
									<ui:table>
										<tr>
											<td colspan="4">
												<br />
											</td>
										</tr>
										<tr>
											<td colspan="4">
												<ui:checkbox binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.tablaBusquedaLogs.ckbBuscarPorLogs}" />
											</td>
										</tr>
										<tr>
											<td colspan="4">
												<ui:table binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
											</td>
										</tr>
									</ui:table>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnBuscar}"
												action="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnReiniciar_action}"
												binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnCancelar_action}"
												binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.groupPanel1}" id="groupPanel1">
													<ui:button action="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnSeleccionar_action}"
														binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.stSeparadorSeleccionar}" escape="false"
														id="staticText6" />
													<ui:button action="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnAgregar_action}"
														binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnModificar_action}"
														binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnEliminar_action}"
														binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.stSeparadorAccion}" escape="false" id="staticText8" />
													<ui:button action="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnConsultar_action}"
														binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.stSeparador4}" escape="false" id="staticText9"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnExportar_action}"
														binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnBuscar').focus();
					</script>
					<ui:hiddenField binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.idSubSesion}" />
					<ui:script binding="#{compras$ABMUsuarioFirmante$AdminUsuarioFirmante.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
