<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.head1}" id="head1"
				title="Administración del Registro de Alicuotas">
				<ui:link binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.body1}" focus="form1:tfCodigo" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.label1}" for="tfCodigo" id="label1"
																style="" styleClass="label" text="Código" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.tfCodigo}" columns="10" id="tfCodigo"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.lblCodigoCiiu}" for="tfCodigoCiiu"
																id="lblCodigoCiiu" styleClass="label" text="Codigo Ciiu" />
														</td>
														<td nowrap="true">
															<ui:textField binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.tfCodigoCiiu}" columns="40"
																disabled="true" id="tfCodigoCiiu" styleClass="textField" />
															<ui:button action="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnSeleccionarCodigoCiiu_action}"
																binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnSeleccionarCodigoCiiu}" escape="false"
																id="btnSeleccionarCodigoCiiu" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarCodigoCiiu" reRender="form1:tfCodigoCiiu" title="Limpiar"
																binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnLimpiarCodigoCiiu}"
																action="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnLimpiarCodigoCiiu_action}"
																styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<table border="0" width="600">
																<tr>
																	<td>
																		<ui:label id="lblVacio" styleClass="label" text="" />
																	</td>
																	<td>
																		<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.panelAtributoDinamico}"
																			id="panelAtributoDinamico">
																			<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
																		</ui:panelGroup>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.tablaBusquedaLogs}"
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
											<a4j:commandButton binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnBuscar}"
												action="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnReiniciar_action}"
												binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.staticText2}" escape="false"
												id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnCancelar_action}"
												binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnSeleccionar_action}"
														binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.stSeparadorSeleccionar}"
														escape="false" id="staticText6" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnAgregar_action}"
														binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnModificar_action}"
														binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnEliminar_action}"
														binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.stSeparadorAccion}" escape="false"
														id="staticText8" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnConsultar_action}"
														binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.staticText10}" escape="false"
														id="staticText10" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnExportar_action}"
														binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText
														binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_5" styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfCodigo').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMRegAlicuotaSHPS$AdminRegAlicuotaSHPS.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
