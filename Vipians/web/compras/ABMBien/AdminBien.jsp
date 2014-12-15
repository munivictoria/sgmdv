<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMBien$AdminBien.page1}" id="page1">
			<ui:html binding="#{compras$ABMBien$AdminBien.html1}" id="html1">
			<ui:head binding="#{compras$ABMBien$AdminBien.head1}" id="head1" title="Administración de Bienes">
				<ui:link binding="#{compras$ABMBien$AdminBien.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "compras$ABMBien$AdminBien";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfCodigoCiiu", "codigoCiiu", nombreBean, "setCodigoCiiuAutocompletar");
					}

					function focusearTfCodigoCiiuSeleccionado() {
						$("#form1\\:tfCodigoCiiu").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{compras$ABMBien$AdminBien.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMBien$AdminBien.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$ABMBien$AdminBien.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{compras$ABMBien$AdminBien.head1.title}" />
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
											<ui:panelGroup binding="#{compras$ABMBien$AdminBien.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMBien$AdminBien.lblNombre}" for="tfNombre" id="lblNombre" styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMBien$AdminBien.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMBien$AdminBien.lblDescripcion}" for="tfDescripcion" styleClass="label" text="Descripción" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMBien$AdminBien.tfDescripcion}" columns="40" id="tfDescripcion" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMBien$AdminBien.lblCodigoCiiu}" id="lblCodigoCiiu" styleClass="label" text="Código CIIU" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{compras$ABMBien$AdminBien.tfCodigoCiiu}" columns="40" id="tfCodigoCiiu"
																styleClass="#{compras$ABMBien$AdminBien.paginatedTable.filtro.codigoCiiu != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{compras$ABMBien$AdminBien.paginatedTable.filtro.codigoCiiu != null}" />
															<ui:button action="#{compras$ABMBien$AdminBien.btnSeleccionarCodigoCiiu_action}"
																binding="#{compras$ABMBien$AdminBien.btnSeleccionarCodigoCiiu}" escape="false" id="btnSeleccionarCodigoCiiu" mini="true"
																styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarCodigoCiiu" reRender="form1:tfCodigoCiiu" title="Limpiar"
																binding="#{compras$ABMBien$AdminBien.btnLimpiarCodigoCiiu}"
																action="#{compras$ABMBien$AdminBien.btnLimpiarCodigoCiiu_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfCodigoCiiuSeleccionado();" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMBien$AdminBien.lblUnidad}" id="lblUnidad" styleClass="label" text="Unidad" />
														</td>
														<td nowrap="nowrap">
															<ui:dropDown binding="#{compras$ABMBien$AdminBien.ddUnidad}" id="ddUnidad"
																items="#{compras$ABMBien$AdminBien.ddUnidadDefaultOptions.options}" styleClass="textField" />
															<ui:button action="#{compras$ABMBien$AdminBien.btnSeleccionarUnidad_action}"
																binding="#{compras$ABMBien$AdminBien.btnSeleccionarUnidad}" escape="false" id="btnSeleccionarUnidad" mini="true"
																styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarUnidad" reRender="form1:ddUnidad" title="Limpiar"
																binding="#{compras$ABMBien$AdminBien.btnLimpiarUnidad}" action="#{compras$ABMBien$AdminBien.btnLimpiarUnidad_action}"
																styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<!--                                        <tr>
                                            <td align="right" nowrap="nowrap">
                                                <ui:label binding="#{compras$ABMBien$AdminBien.lblGrupoBienes}" for="tfGrupoBienes" id="lblGrupoNombres" styleClass="label" text="Grupo de Bienes"/>
                                            </td>
                                            <td>
                                                <ui:textField binding="#{compras$ABMBien$AdminBien.tfGrupoBienes}" columns="40" disabled="true"
                                                              id="tfGrupoBienes" styleClass="textField"/>
                                                <ui:button action="#{compras$ABMBien$AdminBien.btnSeleccionGrupoBienes_action}"
                                                           binding="#{compras$ABMBien$AdminBien.btnSeleccionGrupoBienes}" escape="false" id="btnSeleccionGrupoBienes"
                                                           mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;"/>
                                                <ui:button action="#{compras$ABMBien$AdminBien.btnLimpiarGrupoBien_action}"
                                                           binding="#{compras$ABMBien$AdminBien.btnLimpiarGrupoBien}" escape="false" id="btnLimpiarGrupoBien" mini="true"
                                                           styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                            </td>
                                        </tr>-->
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{compras$ABMBien$AdminBien.lblEstado}" id="lblEstado" styleClass="label" text="Estado" />
														</td>
														<td>
															<ui:dropDown binding="#{compras$ABMBien$AdminBien.ddEstado}" id="ddEstado"
																items="#{compras$ABMBien$AdminBien.ddEstadoDefaultOptions.options}" styleClass="textField" />
														</td>
														<td align="right" nowrap="true">
															<ui:label binding="#{compras$ABMBien$AdminBien.lblTipo}" id="lblTipo" styleClass="label" text="Tipo" />
														</td>
														<td>
															<ui:dropDown binding="#{compras$ABMBien$AdminBien.ddTipo}" id="ddTipo"
																items="#{compras$ABMBien$AdminBien.ddTipoDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{compras$ABMBien$AdminBien.lblTipoBien}" id="lblTipoBien" styleClass="label" text="Categoría Bien" />
														</td>
														<td>
															<ui:dropDown binding="#{compras$ABMBien$AdminBien.ddTipoBien}" id="ddTipoBien"
																items="#{compras$ABMBien$AdminBien.ddTipoBienDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{compras$ABMBien$AdminBien.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{compras$ABMBien$AdminBien.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{compras$ABMBien$AdminBien.btnBuscar}" action="#{compras$ABMBien$AdminBien.btnBuscar_action}"
												id="btnBuscar" value="Buscar" styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros"
												oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{compras$ABMBien$AdminBien.btnReiniciar_action}" binding="#{compras$ABMBien$AdminBien.btnReiniciar}"
												id="btnReiniciar" styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{compras$ABMBien$AdminBien.stSeparador1}" escape="false" id="stSeparador1"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMBien$AdminBien.btnCancelar_action}" binding="#{compras$ABMBien$AdminBien.btnCancelar}"
												id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$ABMBien$AdminBien.messageGroup}" id="messageGroup" showDetail="true" showSummary="false" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{compras$ABMBien$AdminBien.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMBien$AdminBien.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{compras$ABMBien$AdminBien.btnSeleccionar_action}" binding="#{compras$ABMBien$AdminBien.btnSeleccionar}"
														id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{compras$ABMBien$AdminBien.stSeparadorSeleccionar}" escape="false" id="stSeparador2" />
													<ui:button action="#{compras$ABMBien$AdminBien.btnAgregar_action}" binding="#{compras$ABMBien$AdminBien.btnAgregar}"
														id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMBien$AdminBien.btnModificar_action}" binding="#{compras$ABMBien$AdminBien.btnModificar}"
														id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{compras$ABMBien$AdminBien.btnEliminar_action}" binding="#{compras$ABMBien$AdminBien.btnEliminar}"
														id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{compras$ABMBien$AdminBien.stSeparadorAccion}" escape="false" id="stSeparador3" />
													<ui:button action="#{compras$ABMBien$AdminBien.btnConsultar_action}" binding="#{compras$ABMBien$AdminBien.btnConsultar}"
														id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{compras$ABMBien$AdminBien.textoSeparador}" escape="false" id="stSeparador4" />
													<ui:button action="#{compras$ABMBien$AdminBien.btnActivar_action}" binding="#{compras$ABMBien$AdminBien.btnActivar}"
														id="btnActivar" styleClass="button" text="Recuperar Bien" />
													<!--     <ui:button action="#{compras$ABMBien$AdminBien.btnImprimirReporte_action}"
                                                           binding="#{compras$ABMBien$AdminBien.btnImprimirReporte}" id="btnImprimirReporte"
                                                           disabled="true" styleClass="button" text="Visualizar Listado" onClick="newWindow = window.open('ImprimirBien.jsp', '_parent')"/>  -->
													<ui:staticText text="#{compras$ABMBien$AdminBien.textoSeparador}" escape="false" id="stSeparador5" />
													<ui:button action="#{compras$ABMBien$AdminBien.btnExportar_action}" binding="#{compras$ABMBien$AdminBien.btnExportar}"
														id="btnExportar" styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{compras$ABMBien$AdminBien.paginatedTable.stSeparadorOrdenamiento}" id="separador_1" />
													<ui:imageHyperlink binding="#{compras$ABMBien$AdminBien.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{compras$ABMBien$AdminBien.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{compras$ABMBien$AdminBien.stCantidadRegistros}" id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{compras$ABMBien$AdminBien.hidIdPagina}" id="hidIdPagina" text="#{compras$ABMBien$AdminBien.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMBien$AdminBien.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMBien$AdminBien.idSubSesion}" />
					<ui:script binding="#{compras$ABMBien$AdminBien.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
