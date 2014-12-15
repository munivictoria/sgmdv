<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.head1}" id="head1" title="Valuación Acara">
				<ui:link binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfModelo", "modeloVehiculo", nombreBean, "setModeloVehiculoAutocompletar");
					}

					function focusearTfModeloVehiculo() {
						$("#form1\\:tfModelo").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.body1}" focus="form1:tfNumero" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.head1.title}" />
								</caption>
								<tbody>
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
											<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td>
															<br />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.lblAnio}" for="tfAnio" id="lblAnio"
																styleClass="label" text="Año" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.tfAnio}" columns="4"
																disabled="false" id="tfAnio" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.lblValor}" for="tfValor" id="lblValor"
																styleClass="label" text="Valor" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.tfValor}" columns="20"
																disabled="false" id="tfValor" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.lblMoneda}" id="lblMoneda"
																styleClass="label" text="Moneda" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.ddMoneda}" id="ddMoneda"
																items="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.ddMonedaDefaultOptions.options}"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.lblModelo}" id="lblModelo"
																styleClass="label" text="Modelo" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.tfModelo}" columns="40"
																id="tfModelo"
																styleClass="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.paginatedTable.filtro.modelo != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.paginatedTable.filtro.modelo != null}" />
															<ui:button action="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnSeleccionarModelo_action}"
																binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnSeleccionarModelo}" escape="false"
																id="btnSeleccionarModelo" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarModelo" reRender="form1:tfModelo" title="Limpiar"
																binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnLimpiarModelo}"
																action="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnLimpiarModelo_action}"
																styleClass="buttonLimpiarAjax" oncomplete="cargarComportamientoJQuery(); focusearTfModeloVehiculo();" />
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
																		<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.panelAtributoDinamico}"
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
															<ui:checkbox
																binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.tablaBusquedaLogs}"
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
											<a4j:commandButton binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnBuscar}"
												action="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnReiniciar_action}"
												binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.stSeparador1}" escape="false"
												id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnCancelar_action}"
												binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td style="height: 102px">
										<ui:table binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.paginatedTable}" styleClass="tablaPaginada"
											id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.groupPanel1}" id="groupPanel1">
													<ui:button action="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnSeleccionar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.stSeparadorSeleccionar}"
														escape="false" id="stSeparador3" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnAgregar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnModificar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnModificar}" id="btnModificar"
														styleClass="button" text="Modificar" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnEliminar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnEliminar}" id="btnEliminar"
														styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.stSeparadorAccion}" escape="false"
														id="stSeparador5" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnConsultar_action}"
														binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnConsultar}" id="btnConsultar"
														styleClass="button" text="Consultar" />
													<ui:staticText text="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.textoSeparador}" escape="false"
														id="stSeparador6" />
													<ui:button action="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnProcesarArchivoAcara_action}"
														binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.btnProcesarArchivoAcara}"
														id="btnProcesarArchivoAcara" styleClass="button" text="Procesar Archivo Acara" />
													<ui:staticText
														binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink
														binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNumero').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpAutomotor$ABMValuacionAcara$AdminValuacionAcara.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
