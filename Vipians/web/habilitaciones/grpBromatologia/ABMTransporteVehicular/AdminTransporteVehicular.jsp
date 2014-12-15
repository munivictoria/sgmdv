<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.head1}" id="head1"
				title="Administración de Transportes Vehiculares">
				<ui:link binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.link1}" id="link1"
					url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.body1}"
				focus="form1:tfNumeroInscripcion" id="body1" onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.pgParametros}"
												id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.label2}"
																for="tfNumeroInscripcion" id="label2" styleClass="label" text="Número de Inscripción" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.tfNumeroInscripcion}"
																id="tfNumeroInscripcion" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.label1}" for="tfVehiculo"
																id="label1" styleClass="label" text="Vehículo" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.tfVehiculo}"
																columns="40" disabled="true" id="tfVehiculo" styleClass="textField" />
															<ui:button
																action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnSeleccionarVehiculo_action}"
																binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnSeleccionarVehiculo}"
																escape="false" id="btnSeleccionarVehiculo" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;"
																toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarVehiculo" reRender="form1:tfVehiculo" title="Limpiar"
																binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnLimpiarVehiculo}"
																action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnLimpiarVehiculo_action}"
																styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.label3}" for="cbActivo"
																id="label3" styleClass="label" text="Activo" />
														</td>
														<td>
															<ui:checkbox binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.cbActivo}"
																disabled="false" id="cbActivo" selected="true" />
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
																binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.tablaBusquedaLogs}"
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
											<a4j:commandButton binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnBuscar}"
												action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnBuscar_action}" id="btnBuscar"
												value="Buscar" styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnReiniciar_action}"
												binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.staticText2}"
												escape="false" id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnCancelar_action}"
												binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnCancelar}" id="btnCancelar"
												styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.messageGroup}"
										id="messageGroup" showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td style="height: 82px">
										<ui:table binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.groupPanel1}"
													id="groupPanel1">
													<ui:button action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnSeleccionar_action}"
														binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText
														binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.stSeparadorSeleccionar}"
														escape="false" id="staticText6" />
													<ui:button action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnAgregar_action}"
														binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnAgregar}" id="btnAgregar"
														styleClass="button" text="Agregar" />
													<ui:button action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnModificar_action}"
														binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnModificar}" id="btnModificar"
														styleClass="button" text="Modificar" />
													<ui:button action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnEliminar_action}"
														binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnEliminar}" id="btnEliminar"
														styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.stSeparadorAccion}"
														escape="false" id="staticText8" />
													<ui:button action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnConsultar_action}"
														binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnConsultar}" id="btnConsultar"
														styleClass="button" text="Consultar" />
													<ui:staticText binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.staticText9}"
														escape="false" id="staticText9" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnExportar_action}"
														binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.btnExportar}" id="btnExportar"
														styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText
														binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink
														binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.lblEncontrados}"
											id="lblEncontrados" styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNumeroInscripcion').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.hidIdPagina}"
						id="hidIdPagina" text="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.hidIdSubSesion}"
						id="hidIdSubSesion" text="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpBromatologia$ABMTransporteVehicular$AdminTransporteVehicular.scriptValidador}"
						id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
