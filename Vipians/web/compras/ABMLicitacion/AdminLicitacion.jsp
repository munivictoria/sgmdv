<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{compras$ABMLicitacion$AdminLicitacion.page1}" id="page1">
			<ui:html binding="#{compras$ABMLicitacion$AdminLicitacion.html1}" id="html1">
			<ui:head binding="#{compras$ABMLicitacion$AdminLicitacion.head1}" id="head1" title="Administración de Contrataciones">
				<ui:link binding="#{compras$ABMLicitacion$AdminLicitacion.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "compras$ABMLicitacion$AdminLicitacion";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfBien", "bien", nombreBean, "setBienAutocompletar");
						calendarioEnTextField("#form1:tfFechaPublicacion");
					}

					function focusearTfBienSeleccionada() {
						$("#form1\\:tfBien").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{compras$ABMLicitacion$AdminLicitacion.body1}" focus="form1:tfNroLicitacion" id="body1"
				onLoad="parent.footer.location.reload(); Init();changeStyleAlIngresar()"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{compras$ABMLicitacion$AdminLicitacion.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{compras$ABMLicitacion$AdminLicitacion.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{compras$ABMLicitacion$AdminLicitacion.head1.title}" />
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
											<ui:panelGroup binding="#{compras$ABMLicitacion$AdminLicitacion.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$AdminLicitacion.lblNroLicitacion}" for="tfNroLicitacion" id="lblNroLicitacion"
																styleClass="label" text="Número Contratación" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMLicitacion$AdminLicitacion.tfNroLicitacion}" columns="10" id="tfNroLicitacion"
																styleClass="textField" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$AdminLicitacion.lblObjeto}" for="tfObjeto" id="lblObjeto" styleClass="label"
																text="Objeto" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMLicitacion$AdminLicitacion.tfObjeto}" columns="10" id="tfObjeto" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{compras$ABMLicitacion$AdminLicitacion.lblTipoLicitacion}" id="lblTipoLicitacion" styleClass="label"
																text="Tipo Contratación" />
														</td>
														<td>
															<ui:dropDown binding="#{compras$ABMLicitacion$AdminLicitacion.ddTipoLicitacion}" id="ddTipoLicitacion"
																items="#{compras$ABMLicitacion$AdminLicitacion.ddTipoLicitacionDefaultOptions.options}" styleClass="textField" />
														</td>
														<td align="right" nowrap="true">
															<ui:label binding="#{compras$ABMLicitacion$AdminLicitacion.lblEstadoLicitacion}" id="lblEstadoLicitacion" styleClass="label"
																text="Estado Contratación" />
														</td>
														<td>
															<ui:dropDown binding="#{compras$ABMLicitacion$AdminLicitacion.ddEstadoLicitacion}" id="ddEstadoLicitacion"
																items="#{compras$ABMLicitacion$AdminLicitacion.ddEstadoLicitacionDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$AdminLicitacion.lblSecretaria}" id="lblSecretaria" styleClass="label"
																text="Secretaria" for="ddSecretaria" />
														</td>
														<td>
															<ui:dropDown binding="#{compras$ABMLicitacion$AdminLicitacion.ddSecretaria}" id="ddSecretaria"
																items="#{compras$ABMLicitacion$AdminLicitacion.ddSecretariaOptions.options}" styleClass="textField">
																<a4j:support event="onChange" reRender="form1:ddArea"
																	actionListener="#{compras$ABMLicitacion$AdminLicitacion.actionListenerDropSecretaria(event)}" />
															</ui:dropDown>
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$AdminLicitacion.lblArea}" id="lblArea" styleClass="label" text="Área" for="ddArea" />
														</td>
														<td>
															<ui:dropDown binding="#{compras$ABMLicitacion$AdminLicitacion.ddArea}" id="ddArea"
																items="#{compras$ABMLicitacion$AdminLicitacion.ddAreaOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$AdminLicitacion.lblBien}" for="tfBien" id="lblBien" styleClass="label" text="Bien" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMLicitacion$AdminLicitacion.tfBien}" columns="40" id="tfBien"
																styleClass="#{compras$ABMLicitacion$AdminLicitacion.paginatedTable.filtro.bien != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{compras$ABMLicitacion$AdminLicitacion.paginatedTable.filtro.bien != null}" />
															<ui:button action="#{compras$ABMLicitacion$AdminLicitacion.btnSeleccionarBien_action}"
																binding="#{compras$ABMLicitacion$AdminLicitacion.btnSeleccionarBien}" escape="false" id="btnSeleccionarBien" mini="true"
																styleClass="buttonSeleccionar" text="&amp;nbsp;" />
															<a4j:commandButton id="btnLimpiarBien" reRender="form1:tfBien" title="Limpiar"
																binding="#{compras$ABMLicitacion$AdminLicitacion.btnLimpiarBien}"
																action="#{compras$ABMLicitacion$AdminLicitacion.btnLimpiarBien_action}" styleClass="buttonLimpiarAjax"
																oncomplete="cargarComportamientoJQuery(); focusearTfBienSeleccionada();" />
														</td>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{compras$ABMLicitacion$AdminLicitacion.lblFechaPublicacion}" id="lblFechaPublicacion" styleClass="label"
																text="Fecha Publicación" for="tfFechaPublicacion" />
														</td>
														<td>
															<ui:textField binding="#{compras$ABMLicitacion$AdminLicitacion.tfFechaPublicacion}" id="tfFechaPublicacion"
																styleClass="textField" columns="10" />
														</td>
													</tr>
													<!--  <tr>
                                              <td align="right" nowrap="nowrap">
                                        <ui:label binding="#{compras$ABMLicitacion$AdminLicitacion.lblFechaAdjudicacion}" id="lblFechaAdjudicacion" styleClass="label" text="Fecha Adjudicación" for="tfFechaAdjudicacion"/>
                                    </td>
                                    <td>
                                        <ui:textField binding="#{compras$ABMLicitacion$AdminLicitacion.tfFechaAdjudicacion}" id="tfFechaAdjudicacion" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                        <ui:label id="lblFormatoFechaAdjudicacion" styleClass="label" text=" [dd/mm/aaaa]"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" nowrap="nowrap">
                                        <ui:label binding="#{compras$ABMLicitacion$AdminLicitacion.lblFechaOferta}" id="lblFechaOferta" styleClass="label" text="Fecha Oferta" for="tfFechaOferta"/>
                                    </td>
                                    <td>
                                        <ui:textField binding="#{compras$ABMLicitacion$AdminLicitacion.tfFechaOferta}" id="tfFechaOferta" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                        <ui:label id="lblFormatoFechaOferta" styleClass="label" text=" [dd/mm/aaaa]"/>
                                    </td>
                                </tr>
                                        -->
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{compras$ABMLicitacion$AdminLicitacion.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{compras$ABMLicitacion$AdminLicitacion.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{compras$ABMLicitacion$AdminLicitacion.btnBuscar}"
												action="#{compras$ABMLicitacion$AdminLicitacion.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{compras$ABMLicitacion$AdminLicitacion.btnReiniciar_action}"
												binding="#{compras$ABMLicitacion$AdminLicitacion.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery(); focusearTfBienSeleccionada();" />
											<ui:staticText binding="#{compras$ABMLicitacion$AdminLicitacion.stSeparador1}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{compras$ABMLicitacion$AdminLicitacion.btnCancelar_action}"
												binding="#{compras$ABMLicitacion$AdminLicitacion.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{compras$ABMLicitacion$AdminLicitacion.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{compras$ABMLicitacion$AdminLicitacion.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{compras$ABMLicitacion$AdminLicitacion.groupPanel1}" id="groupPanel1">
													<ui:button action="#{compras$ABMLicitacion$AdminLicitacion.btnSeleccionar_action}"
														binding="#{compras$ABMLicitacion$AdminLicitacion.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{compras$ABMLicitacion$AdminLicitacion.stSeparadorSeleccionar}" escape="false" id="stSeparador2" />
													<ui:button action="#{compras$ABMLicitacion$AdminLicitacion.btnAgregar_action}"
														binding="#{compras$ABMLicitacion$AdminLicitacion.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{compras$ABMLicitacion$AdminLicitacion.btnModificar_action}"
														binding="#{compras$ABMLicitacion$AdminLicitacion.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{compras$ABMLicitacion$AdminLicitacion.btnEliminar_action}"
														binding="#{compras$ABMLicitacion$AdminLicitacion.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{compras$ABMLicitacion$AdminLicitacion.stSeparadorAccion}" escape="false" id="stSeparador3" />
													<ui:button action="#{compras$ABMLicitacion$AdminLicitacion.btnConsultar_action}"
														binding="#{compras$ABMLicitacion$AdminLicitacion.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText binding="#{compras$ABMLicitacion$AdminLicitacion.stSeparador4}" escape="false" id="stSeparador4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{compras$ABMLicitacion$AdminLicitacion.btnExportar_action}"
														binding="#{compras$ABMLicitacion$AdminLicitacion.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{compras$ABMLicitacion$AdminLicitacion.paginatedTable.stSeparadorOrdenamiento}" id="separador_1" />
													<ui:imageHyperlink binding="#{compras$ABMLicitacion$AdminLicitacion.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{compras$ABMLicitacion$AdminLicitacion.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{compras$ABMLicitacion$AdminLicitacion.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNroLicitacion').focus();
					</script>
					<ui:hiddenField binding="#{compras$ABMLicitacion$AdminLicitacion.hidIdPagina}" id="hidIdPagina"
						text="#{compras$ABMLicitacion$AdminLicitacion.idPagina}" />
					<ui:hiddenField binding="#{compras$ABMLicitacion$AdminLicitacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{compras$ABMLicitacion$AdminLicitacion.idSubSesion}" />
					<ui:script binding="#{compras$ABMLicitacion$AdminLicitacion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
