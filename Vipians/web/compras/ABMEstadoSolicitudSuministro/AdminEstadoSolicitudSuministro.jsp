<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.page1}" id="page1">
			<ui:html binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.html1}" id="html1">
			<ui:head binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.head1}" id="head1"
				title="Estado Solicitud de Suministro">
				<ui:link binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center" nowrap="nowrap">
											<ui:staticText binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.stFiltrarPor}" escape="false"
												id="stFiltrarPor" styleClass="textoFiltrarPor" text="Filtrar por" />
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<hr />
										</td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td>
															<br />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.lblNombre}" for="tfNombre"
																id="lblNombre" styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.tfNombre}" columns="20"
																disabled="false" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox
																binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.tablaBusquedaLogs}"
																id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.btnBuscar}"
												action="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.btnReiniciar_action}"
												binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.stSeparador1}" escape="false"
												id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.btnCancelar_action}"
												binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.btnCancelar}" id="btnCancelar"
												styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td style="height: 102px" nowrap="nowrap">
										<ui:table binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.paginatedTable}"
											styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.groupPanel1}" id="groupPanel1">
													<ui:button action="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.btnSeleccionar_action}"
														binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.stSeparadorSeleccionar}"
														escape="false" id="stSeparador3" />
													<ui:button action="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.btnAgregar_action}"
														binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.btnAgregar}" id="btnAgregar"
														styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.btnModificar_action}"
														binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.btnModificar}" id="btnModificar"
														styleClass="button" text="Modificar" />
													<ui:staticText binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.stSeparadorAccion}"
														escape="false" id="stSeparador2" />
													<ui:button action="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.btnConsultar_action}"
														binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.btnConsultar}" id="btnConsultar"
														styleClass="button" text="Consultar" />
													<ui:staticText text="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.textoSeparador}" escape="false"
														id="stSeparador5" />
													<ui:button action="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.btnEliminar_action}"
														binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.btnEliminar}" id="btnEliminar"
														styleClass="button" text="Eliminar" />
													<ui:staticText
														binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink
														binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.idSubSesion}" />
					<ui:script binding="#{compras$ABMEstadoSolicitudSuministro$AdminEstadoSolicitudSuministro.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
