<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.page1}" id="page1">
			<ui:html binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.html1}" id="html1">
			<ui:head binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.head1}" id="head1" title="Administración de Días Feriados">
				<ui:link binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{framework$ABMDiaFeriado$AdminDiaFeriado.head1.title}" />
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
											<ui:panelGroup binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.label1}" id="label1" styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.label2}" for="tfAno" id="label2" styleClass="label" text="Año" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.tfAno}" columns="10"
																onKeyPress="return ValidarNum(event,this)" id="tfAno" maxLength="4" styleClass="textField" />
															<ui:staticText binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.staticText4}" escape="false" id="staticText4"
																styleClass="label" text="&amp;nbsp;[aaaa]" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnBuscar}"
												action="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnReiniciar_action}"
												binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnCancelar_action}"
												binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.paginatedTable}" id="table1" width="359" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnSeleccionar_action}"
														binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnAgregar_action}"
														binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnModificar_action}"
														binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnEliminar_action}"
														binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.stSeparadorAccion}" id="separador_2"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnConsultar_action}"
														binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{framework$ABMDiaFeriado$AdminDiaFeriado.textoSeparador}" id="separador_3"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnExportar_action}"
														binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.paginatedTable.stSeparadorOrdenamiento}" id="separador_4"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMDiaFeriado$AdminDiaFeriado.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMDiaFeriado$AdminDiaFeriado.idSubSesion}" />
					<ui:script binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{framework$ABMDiaFeriado$AdminDiaFeriado.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
