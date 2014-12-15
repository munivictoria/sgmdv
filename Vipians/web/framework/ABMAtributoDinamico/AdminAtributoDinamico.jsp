<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.page1}" id="page1">
			<ui:html binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.html1}" id="html1">
			<ui:head binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.head1}" id="head1" title="Administración de Atributos Dinámicos">
				<ui:link binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.form1}" id="form1"
					virtualFormsConfig="vfCancelar | hidIdSubSesion hidIdPagina | btnCancelar">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.head1.title}" />
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
											<ui:panelGroup binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.label1}" for="tfNombre" id="label1"
																styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.tfNombre}" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.label2}" id="label2" styleClass="label"
																text="Recurso" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.tfRecurso}" columns="40" disabled="true"
																id="tfRecurso" styleClass="textField" />
															<ui:button action="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnSeleccionarRecurso_action}"
																binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnSeleccionarRecurso}" escape="false"
																id="btnSeleccionarRecurso" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarRecurso" reRender="form1:tfRecurso" title="Limpiar"
																binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnLimpiarRecurso}"
																action="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnLimpiarRecurso_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnBuscar}"
												action="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnReiniciar_action}"
												binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnCancelar_action}"
												binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.paginatedTable}" id="table1" style="align:center"
											styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnSeleccionar_action}"
														binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnAgregar_action}"
														binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnModificar_action}"
														binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnEliminar_action}"
														binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.stSeparadorAccion}" id="separador_2"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnConsultar_action}"
														binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText text="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.textoSeparador}" id="separador_3"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnExportar_action}"
														binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_4" styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.idSubSesion}" />
					<ui:script binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
					<ui:script binding="#{framework$ABMAtributoDinamico$AdminAtributoDinamico.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
