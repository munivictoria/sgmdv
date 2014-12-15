<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.head1}" id="head1" title="Administración de Obras">
				<ui:link binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.body1}" focus="form1:tfNumeroObra" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap" style="height: 19px">
															<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.lblNumeroObra}" for="tfNumeroObra"
																id="lblNumeroObra" styleClass="label" text="Número de Obra" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.tfNumeroObra}" columns="10"
																id="tfNumeroObra" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap" style="height: 67px">
															<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.label2}" for="taDescripcion" id="label2"
																styleClass="label" text="Descripción" />
														</td>
														<td>
															<ui:textArea binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.taDescripcion}" columns="40"
																id="taDescripcion" rows="5" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap" style="height: 19px">
															<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.label1}" for="tfCuadra" id="label1"
																styleClass="label" text="Cuadra Afectada" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.tfCuadra}" columns="40" disabled="true"
																id="tfCuadra" styleClass="textField" />
															<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnSeleccionarCuadra_action}"
																binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnSeleccionarCuadra}" escape="false"
																id="btnSeleccionarCuadra" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarCuadra" reRender="form1:tfCuadra" title="Limpiar"
																binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnLimpiarCuadra}"
																action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnLimpiarCuadra_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap" style="height: 25px">
															<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.label3}" for="ddTipoObra" id="label3"
																styleClass="label" text="Tipo de Obra" />
														</td>
														<td>
															<ui:dropDown binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.ddTipoObra}" id="ddTipoObra"
																items="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.ddTipoObraDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="2"></td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnBuscar}"
												action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnReiniciar_action}"
												binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnCancelar_action}"
												binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.paginatedTable}" id="table1" width="599">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.groupPanel1}" id="groupPanel1">
													<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnSeleccionar_action}"
														binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.stSeparadorSeleccionar}" escape="false"
														id="staticText6" />
													<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnAgregar_action}"
														binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnModificar_action}"
														binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnEliminar_action}"
														binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.stSeparadorAccion}" escape="false"
														id="staticText8" />
													<ui:button binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnImprimirReporte}" disabled="true"
														id="btnImprimirReporte" styleClass="button" text="Visualizar Reporte" />
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.staticText11}" escape="false"
														id="staticText11" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnConsultar_action}"
														binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.staticText12}" escape="false"
														id="staticText12" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnExportar_action}"
														binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNumeroObra').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpPlanFinanciacionObra$ABMObra$AdminObra.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
