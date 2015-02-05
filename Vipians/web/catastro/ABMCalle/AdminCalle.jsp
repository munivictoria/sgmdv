<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMCalle$AdminCalle.page1}" id="page1">
			<ui:html binding="#{catastro$ABMCalle$AdminCalle.html1}" id="html1">
			<ui:head binding="#{catastro$ABMCalle$AdminCalle.head1}" id="head1" title="Administración de Calles">
				<ui:link binding="#{catastro$ABMCalle$AdminCalle.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMCalle$AdminCalle.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMCalle$AdminCalle.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{catastro$ABMCalle$AdminCalle.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{catastro$ABMCalle$AdminCalle.head1.title}" />
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
											<ui:panelGroup binding="#{catastro$ABMCalle$AdminCalle.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{catastro$ABMCalle$AdminCalle.label2}" for="tfNombre" id="label2" styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMCalle$AdminCalle.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{catastro$ABMCalle$AdminCalle.label1}" id="label1" style="" styleClass="label" text="Tipo de Calle" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMCalle$AdminCalle.tfTipoCalle}" columns="40" disabled="true" id="tfTipoCalle"
																styleClass="textField" />
															<ui:button action="#{catastro$ABMCalle$AdminCalle.btnSeleccionarTipoCalle_action}"
																binding="#{catastro$ABMCalle$AdminCalle.btnSeleccionarTipoCalle}" escape="false" id="btnSeleccionarTipoCalle" mini="true"
																styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarTipoCalle" reRender="form1:tfTipoCalle" title="Limpiar"
																binding="#{catastro$ABMCalle$AdminCalle.btnLimpiarTipoCalle}"
																action="#{catastro$ABMCalle$AdminCalle.btnLimpiarTipoCalle_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMCalle$AdminCalle.lblCodigo}" for="tfCodigo" id="label4" styleClass="label" text="Código" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMCalle$AdminCalle.tfCodigo}" onKeyPress="return ValidarNum(event)" id="tfCodigo"
																styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap" />
														<td />
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{catastro$ABMCalle$AdminCalle.lblActiva}" id="lblActiva" style="" styleClass="label" text="Activa" />
														</td>
														<td>
															<ui:checkbox binding="#{catastro$ABMCalle$AdminCalle.cbActiva}" disabled="false" id="cbActiva" selected="true" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{catastro$ABMCalle$AdminCalle.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{catastro$ABMCalle$AdminCalle.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{catastro$ABMCalle$AdminCalle.btnBuscar}" action="#{catastro$ABMCalle$AdminCalle.btnBuscar_action}"
												id="btnBuscar" value="Buscar" styleClass="btnAjax" reRender="form1:table1,stCantidadRegistros"
												oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{catastro$ABMCalle$AdminCalle.btnReiniciar_action}"
												binding="#{catastro$ABMCalle$AdminCalle.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,stCantidadRegistros" />
											<ui:staticText binding="#{catastro$ABMCalle$AdminCalle.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{catastro$ABMCalle$AdminCalle.btnCancelar_action}" binding="#{catastro$ABMCalle$AdminCalle.btnCancelar}"
												id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{catastro$ABMCalle$AdminCalle.messageGroup}" id="messageGroup" showDetail="true" showSummary="false"
										styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td colspan="2">
										<ui:table binding="#{catastro$ABMCalle$AdminCalle.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMCalle$AdminCalle.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{catastro$ABMCalle$AdminCalle.btnSeleccionar_action}"
														binding="#{catastro$ABMCalle$AdminCalle.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{catastro$ABMCalle$AdminCalle.stSeparadorSeleccionar}" escape="false" id="staticText6"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMCalle$AdminCalle.btnAgregar_action}" binding="#{catastro$ABMCalle$AdminCalle.btnAgregar}"
														id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{catastro$ABMCalle$AdminCalle.btnModificar_action}" binding="#{catastro$ABMCalle$AdminCalle.btnModificar}"
														id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{catastro$ABMCalle$AdminCalle.btnEliminar_action}" binding="#{catastro$ABMCalle$AdminCalle.btnEliminar}"
														id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{catastro$ABMCalle$AdminCalle.stSeparadorAccion}" escape="false" id="staticText8"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMCalle$AdminCalle.btnConsultar_action}" binding="#{catastro$ABMCalle$AdminCalle.btnConsultar}"
														id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{catastro$ABMCalle$AdminCalle.textoSeparador}" escape="false" id="staticText9"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMCalle$AdminCalle.btnExportar_action}" binding="#{catastro$ABMCalle$AdminCalle.btnExportar}"
														id="btnExportar" styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{catastro$ABMCalle$AdminCalle.paginatedTable.stSeparadorOrdenamiento}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{catastro$ABMCalle$AdminCalle.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left">
										<ui:label binding="#{catastro$ABMCalle$AdminCalle.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{catastro$ABMCalle$AdminCalle.stCantidadRegistros}" id="stCantidadRegistros" styleClass="staticText" />
									</td>
									<td align="right">
										<ui:hyperlink binding="#{catastro$ABMCalle$AdminCalle.hpAgregarPaginaAccesoDirecto}"/>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{catastro$ABMCalle$AdminCalle.hidIdPagina}" id="hidIdPagina" text="#{catastro$ABMCalle$AdminCalle.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMCalle$AdminCalle.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMCalle$AdminCalle.idSubSesion}" />
					<ui:script binding="#{catastro$ABMCalle$AdminCalle.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{catastro$ABMCalle$AdminCalle.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
