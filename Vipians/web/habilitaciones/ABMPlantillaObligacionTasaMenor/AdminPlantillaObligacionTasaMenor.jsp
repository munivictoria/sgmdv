<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.page1}" id="page1">
			<ui:html binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.html1}" id="html1">
			<ui:head binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.head1}" id="head1"
				title="Administración de Plantillas de Obligaciones Menores">
				<ui:link binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.body1}" focus="form1:tfNumero"
				id="body1" onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.pgParametros}"
												id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.lblNombre}"
																for="tfNombre" id="lblNombre" styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.tfNombre}"
																columns="40" disabled="false" id="tfNombre" styleClass="textField" />
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
																binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.tablaBusquedaLogs}"
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
											<a4j:commandButton binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnBuscar}"
												action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnBuscar_action}" id="btnBuscar"
												value="Buscar" styleClass="btnAjax" reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton
												action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnReiniciar_action}"
												binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,stCantidadRegistros" />
											<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.staticText2}"
												escape="false" id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnCancelar_action}"
												binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnCancelar}" id="btnCancelar"
												styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.messageGroup}"
										id="messageGroup" showDetail="true" showSummary="false" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.groupPanel1}"
													id="groupPanel1">
													<ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnSeleccionar_action}"
														binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnSeleccionar}"
														id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText
														binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.stSeparadorSeleccionar}"
														escape="false" id="staticText6" />
													<ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnAgregar_action}"
														binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnAgregar}" id="btnAgregar"
														styleClass="button" text="Agregar" />
													<ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnModificar_action}"
														binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnModificar}" id="btnModificar"
														styleClass="button" text="Modificar" />
													<ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnEliminar_action}"
														binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnEliminar}" id="btnEliminar"
														styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.stSeparadorAccion}"
														escape="false" />
													<ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnConsultar_action}"
														binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnConsultar}" id="btnConsultar"
														styleClass="button" text="Consultar" />
													<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.staticText7}"
														escape="false" id="staticText7" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnExportar_action}"
														binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.btnExportar}" id="btnExportar"
														styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													<ui:imageHyperlink
														binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.lblEncontrados}"
											id="lblEncontrados" styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.hidIdPagina}"
						id="hidIdPagina" text="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.hidIdSubSesion}"
						id="hidIdSubSesion" text="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.idSubSesion}" />
					<ui:script binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$AdminPlantillaObligacionTasaMenor.scriptValidador}"
						id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
