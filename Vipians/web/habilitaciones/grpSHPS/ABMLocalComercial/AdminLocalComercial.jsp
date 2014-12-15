<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.head1}" id="head1"
				title="Administración de Locales Comerciales">
				<ui:link binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfParcelaSeleccionada", "parcela", nombreBean, "setParcelaAutocompletar");
					}

					function focusearTfParcelaSeleccionada() {
						$("#form1\\:tfParcelaSeleccionada").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.body1}" focus="form1:tfNumeroInscripcion" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.label2}" for="tfNumeroInscripcion"
																id="label2" styleClass="label" text="Número de Inscripción" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.tfNumeroInscripcion}"
																id="tfNumeroInscripcion" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.lblParcelaSeleccionada}"
																id="lblParcelaSeleccionada" styleClass="label" text="Parcela" />
														</td>
														<td>
															<!-- <ui:textField binding="#{catastro$ABMManzana$AdminManzana.Zona}" disabled="true" id="tfZona" styleClass="textField"/> -->
															<ui:textField binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.tfParcelaSeleccionada}" columns="40"
																id="tfParcelaSeleccionada"
																styleClass="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.paginatedTable.filtro.parcela != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.paginatedTable.filtro.parcela != null}" />
															<ui:button action="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnSeleccionarParcela_action}"
																binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnSeleccionarParcela}" escape="false"
																id="btnSeleccionarParcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcelaSeleccionada" title="Limpiar"
																binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnLimpiarParcela}"
																action="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnLimpiarParcela_action}"
																styleClass="buttonLimpiarAjax" oncomplete="cargarComportamientoJQuery(); focusearTfParcelaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnBuscar}"
												action="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnReiniciar_action}"
												binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.staticText2}" escape="false"
												id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnCancelar_action}"
												binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.groupPanel1}" id="groupPanel1">
													<ui:button action="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnSeleccionar_action}"
														binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.stSeparadorSeleccionar}" escape="false"
														id="staticText6" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnAgregar_action}"
														binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnModificar_action}"
														binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnEliminar_action}"
														binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.stSeparadorAccion}" escape="false"
														id="staticText8" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnConsultar_action}"
														binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.staticText3}" escape="false"
														id="staticText3" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnImprimirReporte_action}"
														binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnImprimirReporte}" disabled="true"
														id="btnImprimirReporte" styleClass="button" text="Visualizar Reporte" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.staticText9}" escape="false"
														id="staticText9" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnExportar_action}"
														binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNumeroInscripcion').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMLocalComercial$AdminLocalComercial.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
