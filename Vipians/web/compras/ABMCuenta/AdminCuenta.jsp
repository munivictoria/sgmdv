<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMCuenta$AdminCuenta.page1}" id="page1">
			<ui:html binding="#{compras$ABMCuenta$AdminCuenta.html1}" id="html1">
			<ui:head binding="#{compras$ABMCuenta$AdminCuenta.head1}" id="head1" title="Administración de Cuentas">
				<ui:link binding="#{compras$ABMCuenta$AdminCuenta.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{compras$ABMCuenta$AdminCuenta.body1}" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMCuenta$AdminCuenta.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$ABMCuenta$AdminCuenta.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{compras$ABMCuenta$AdminCuenta.head1.title}" />
								</caption>
								<tr>
									<td></td>
								</tr>
								<tbody>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{compras$ABMCuenta$AdminCuenta.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMCuenta$AdminCuenta.lblAbreviatura}" for="tfAbreviatura" id="lblAbreviatura"
																styleClass="label" text="Abreviatura" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMCuenta$AdminCuenta.tfAbreviatura}" columns="20" id="tfAbreviatura" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMCuenta$AdminCuenta.lblCodigoImputacion}" for="tfCodigoImputacion" id="lblCodigoImputacion"
																styleClass="label" text="Código Imputación" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMCuenta$AdminCuenta.tfCodigoImputacion}" columns="20" id="tfCodigoImputacion"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMCuenta$AdminCuenta.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMCuenta$AdminCuenta.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tr>
									<td></td>
								</tr>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{compras$ABMCuenta$AdminCuenta.btnBuscar}"
												action="#{compras$ABMCuenta$AdminCuenta.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{compras$ABMCuenta$AdminCuenta.btnReiniciar_action}"
												binding="#{compras$ABMCuenta$AdminCuenta.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1" />
											<ui:staticText binding="#{compras$ABMCuenta$AdminCuenta.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMCuenta$AdminCuenta.btnCancelar_action}" binding="#{compras$ABMCuenta$AdminCuenta.btnCancelar}"
												id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$ABMCuenta$AdminCuenta.messageGroup}" id="messageGroup" showDetail="true" showSummary="false" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{compras$ABMCuenta$AdminCuenta.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMCuenta$AdminCuenta.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{compras$ABMCuenta$AdminCuenta.btnSeleccionar_action}"
														binding="#{compras$ABMCuenta$AdminCuenta.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{compras$ABMCuenta$AdminCuenta.stSeparadorSeleccionar}" escape="false" id="stSeparador2" />
													<ui:button action="#{compras$ABMCuenta$AdminCuenta.btnAgregar_action}" disabled="true"
														binding="#{compras$ABMCuenta$AdminCuenta.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMCuenta$AdminCuenta.btnModificar_action}" disabled="true"
														binding="#{compras$ABMCuenta$AdminCuenta.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{compras$ABMCuenta$AdminCuenta.btnEliminar_action}" disabled="true"
														binding="#{compras$ABMCuenta$AdminCuenta.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{compras$ABMCuenta$AdminCuenta.stSeparadorAccion}" escape="false" id="stSeparador3" />
													<ui:button action="#{compras$ABMCuenta$AdminCuenta.btnConsultar_action}" disabled="true"
														binding="#{compras$ABMCuenta$AdminCuenta.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText binding="#{compras$ABMCuenta$AdminCuenta.stSeparador4}" escape="false" id="stSeparador4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMCuenta$AdminCuenta.btnExportar_action}" binding="#{compras$ABMCuenta$AdminCuenta.btnExportar}"
														id="btnExportar" styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{compras$ABMCuenta$AdminCuenta.paginatedTable.stSeparadorOrdenamiento}" id="separador_4"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{compras$ABMCuenta$AdminCuenta.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<ui:hiddenField binding="#{compras$ABMCuenta$AdminCuenta.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMCuenta$AdminCuenta.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMCuenta$AdminCuenta.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMCuenta$AdminCuenta.idSubSesion}" />
					<ui:script binding="#{compras$ABMCuenta$AdminCuenta.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
