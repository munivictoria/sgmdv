<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.page1}" id="page1">
			<ui:html binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.html1}" id="html1">
			<ui:head binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.head1}" id="head1"
				title="Administración de Plantillas de Obligación">
				<ui:link binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.label2}" for="tfNombre" id="label2"
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.tfNombre}" columns="40" id="tfNombre"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.label1}" for="ddTipoObligacion"
																id="label1" styleClass="label" text="Tipo de Obligación" />
														</td>
														<td>
															<ui:dropDown binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.ddTipoObligacion}"
																id="ddTipoObligacion"
																items="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.ddTipoObligacionDefaultOptions.options}"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.tablaBusquedaLogs}"
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
											<a4j:commandButton binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnBuscar}"
												action="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnReiniciar_action}"
												binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.staticText2}" escape="false"
												id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnCancelar_action}"
												binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.groupPanel1}" id="groupPanel1">
													<ui:button action="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnSeleccionar_action}"
														binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.stSeparadorSeleccionar}"
														escape="false" id="staticText6" />
													<ui:button action="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnAgregar_action}"
														binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnModificar_action}"
														binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnEliminar_action}"
														binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.stSeparadorAccion}" escape="false"
														id="staticText8" />
													<ui:button action="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnConsultar_action}"
														binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.staticText9}" escape="false"
														id="staticText9" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnExportar_action}"
														binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText
														binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.idSubSesion}" />
					<ui:script binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$ABMPlantillaObligacion$AdminPlantillaObligacion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
