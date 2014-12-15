<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMManzana$AdminManzana.page1}" id="page1">
			<ui:html binding="#{catastro$ABMManzana$AdminManzana.html1}" id="html1">
			<ui:head binding="#{catastro$ABMManzana$AdminManzana.head1}" id="head1" title="Administración de Manzanas">
				<ui:link binding="#{catastro$ABMManzana$AdminManzana.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMManzana$AdminManzana.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init(); changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMManzana$AdminManzana.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{catastro$ABMManzana$AdminManzana.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{catastro$ABMManzana$AdminManzana.head1.title}" />
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
											<ui:panelGroup binding="#{catastro$ABMManzana$AdminManzana.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMManzana$AdminManzana.label2}" for="tfNombre" id="label2" styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMManzana$AdminManzana.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMManzana$AdminManzana.lblNumero}" for="tfNumero" id="lblNumero" styleClass="label"
																text="Número" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMManzana$AdminManzana.tfNumero}" onKeyPress="return ValidarNum(event,this)" columns="10"
																id="tfNumero" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMManzana$AdminManzana.lblCuadra}" for="tfCuadra" id="lblCuadra" styleClass="label"
																text="Cuadra" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMManzana$AdminManzana.tfCuadra}" columns="39" disabled="true" id="tfCuadra"
																styleClass="textField" />
															<ui:button action="#{catastro$ABMManzana$AdminManzana.btnSeleccionarCuadra_action}"
																binding="#{catastro$ABMManzana$AdminManzana.btnSeleccionarCuadra}" escape="false" id="btnSeleccionarCuadra" mini="true"
																styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarCuadra" reRender="form1:tfCuadra" title="Limpiar"
																binding="#{catastro$ABMManzana$AdminManzana.btnLimpiarCuadra}"
																action="#{catastro$ABMManzana$AdminManzana.btnLimpiarCuadra_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMManzana$AdminManzana.label1}" for="ddCalle" id="label1" style="" styleClass="label"
																text="Calle" />
														</td>
														<td>
															<ui:dropDown binding="#{catastro$ABMManzana$AdminManzana.ddCalle}" id="ddCalle"
																items="#{catastro$ABMManzana$AdminManzana.ddCalleOptions.options}" styleClass="textField" />
															<ui:button action="#{catastro$ABMManzana$AdminManzana.btnSeleccionarCalle_action}"
																binding="#{catastro$ABMManzana$AdminManzana.btnSeleccionarCalle}" escape="false" id="btnSeleccionarCalle" mini="true"
																styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarCalle" reRender="form1:ddCalle"
																binding="#{catastro$ABMManzana$AdminManzana.btnLimpiarCalle}"
																action="#{catastro$ABMManzana$AdminManzana.btnLimpiarCalle_action}" styleClass="buttonLimpiarAjax" />
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
																		<ui:panelGroup binding="#{catastro$ABMManzana$AdminManzana.panelAtributoDinamico}" id="panelAtributoDinamico">
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
															<ui:checkbox binding="#{catastro$ABMManzana$AdminManzana.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{catastro$ABMManzana$AdminManzana.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{catastro$ABMManzana$AdminManzana.btnBuscar}"
												action="#{catastro$ABMManzana$AdminManzana.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{catastro$ABMManzana$AdminManzana.btnReiniciar_action}"
												binding="#{catastro$ABMManzana$AdminManzana.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,stCantidadRegistros" />
											<ui:staticText binding="#{catastro$ABMManzana$AdminManzana.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{catastro$ABMManzana$AdminManzana.btnCancelar_action}"
												binding="#{catastro$ABMManzana$AdminManzana.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{catastro$ABMManzana$AdminManzana.messageGroup}" id="messageGroup" showDetail="true" showSummary="false"
										styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{catastro$ABMManzana$AdminManzana.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMManzana$AdminManzana.groupPanel1}" id="groupPanel1">
													<ui:button action="#{catastro$ABMManzana$AdminManzana.btnSeleccionar_action}"
														binding="#{catastro$ABMManzana$AdminManzana.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{catastro$ABMManzana$AdminManzana.stSeparadorSeleccionar}" escape="false" id="staticText6"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMManzana$AdminManzana.btnAgregar_action}"
														binding="#{catastro$ABMManzana$AdminManzana.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{catastro$ABMManzana$AdminManzana.btnModificar_action}"
														binding="#{catastro$ABMManzana$AdminManzana.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{catastro$ABMManzana$AdminManzana.btnEliminar_action}"
														binding="#{catastro$ABMManzana$AdminManzana.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{catastro$ABMManzana$AdminManzana.stSeparadorAccion}" escape="false" id="staticText8"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMManzana$AdminManzana.btnConsultar_action}"
														binding="#{catastro$ABMManzana$AdminManzana.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{catastro$ABMManzana$AdminManzana.textoSeparador}" escape="false" id="staticText9"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMManzana$AdminManzana.btnExportar_action}"
														binding="#{catastro$ABMManzana$AdminManzana.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{catastro$ABMManzana$AdminManzana.paginatedTable.stSeparadorOrdenamiento}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{catastro$ABMManzana$AdminManzana.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{catastro$ABMManzana$AdminManzana.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{catastro$ABMManzana$AdminManzana.stCantidadRegistros}" id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{catastro$ABMManzana$AdminManzana.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMManzana$AdminManzana.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMManzana$AdminManzana.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMManzana$AdminManzana.idSubSesion}" />
					<ui:script binding="#{catastro$ABMManzana$AdminManzana.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{catastro$ABMManzana$AdminManzana.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
