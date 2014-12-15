<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.page1}" id="page1">
			<ui:html binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.html1}" id="html1">
			<ui:head binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.head1}" id="head1"
				title="Administración de Autorizaciones de Solicitud de Suministro">
				<ui:link binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.body1}" focus="form1:btnBuscar"
				id="body1" onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar(); "
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td align="center" nowrap="nowrap">
											<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.stFiltrarPor}"
												escape="false" id="stFiltrarPor" styleClass="textoFiltrarPor" text="Filtrar por" />
										</td>
									<tr>
										<td colspan="4">
											<br />
										</td>
									</tr>
									<tr>
										<td align="right" nowrap="nowrap">
											<ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.lblArea}" id="lblArea"
												styleClass="label" text="Área" for="ddArea" />
										</td>
										<td>
											<ui:dropDown binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.ddArea}" id="ddArea"
												items="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.ddAreaOptions.options}"
												styleClass="textField" />
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<ui:checkbox
												binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.tablaBusquedaLogs.ckbBuscarPorLogs}"
												id="ckbBuscarPorLogs" />
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<ui:table binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.tablaBusquedaLogs}"
												id="tablaBusquedaLogs" />
										</td>
									</tr>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnBuscar}"
												action="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnBuscar_action}" id="btnBuscar"
												value="Buscar" styleClass="btnAjax" reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton
												action="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnReiniciar_action}"
												binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar"
												reRender="form1:ddArea,form1:table1,tablaBusquedaLogs,ckbBuscarPorLogs,stCantidadRegistros" />
											<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.stSeparador1}"
												escape="false" id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnCancelar_action}"
												binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnCancelar}" id="btnCancelar"
												styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.messageGroup}"
										id="messageGroup" showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.groupPanel1}"
													id="groupPanel1">
													<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnSeleccionar_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnSeleccionar}"
														id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText
														binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.stSeparadorSeleccionar}"
														escape="false" id="staticText6" />
													<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnAgregar_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnAgregar}" id="btnAgregar"
														styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnModificar_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnModificar}" id="btnModificar"
														styleClass="button" text="Modificar" />
													<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnEliminar_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnEliminar}" id="btnEliminar"
														styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.stSeparadorAccion}"
														escape="false" id="staticText8" />
													<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnConsultar_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnConsultar}" id="btnConsultar"
														styleClass="button" text="Consultar" />
													<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.stSeparador4}"
														escape="false" id="staticText9" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnExportar_action}"
														binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.btnExportar}" id="btnExportar"
														styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText
														binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink
														binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.lblEncontrados}"
											id="lblEncontrados" styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnBuscar').focus();
					</script>
					<ui:hiddenField binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.hidIdPagina}"
						id="hidIdPagina" text="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.hidIdSubSesion}"
						id="hidIdSubSesion" text="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.idSubSesion}" />
					<ui:script binding="#{compras$ABMAutorizacionSolicitudSuministro$AdminAutorizacionSolicitudSuministro.scriptValidador}"
						id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
