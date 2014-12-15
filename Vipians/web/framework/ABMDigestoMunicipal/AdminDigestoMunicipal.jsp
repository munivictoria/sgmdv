<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.page1}" id="page1">
			<ui:html binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.html1}" id="html1">
			<ui:head binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.head1}" id="head1"
				title="Administración de Decretos, Ordenanzas y Resoluciones">
				<ui:link binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.body1}" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.head1.title}" />
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
											<ui:panelGroup binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.label1}" id="label1" style="" styleClass="label"
																text="Tipo" />
														</td>
														<td>
															<ui:dropDown binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.ddTipoDigesto}" id="ddTipoDigesto"
																items="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.ddTipoDigestoDefaultOptions.options}" styleClass="textField" />
														</td>
														<td align="right" nowrap="true">
															<ui:label binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.label2}" id="labelET" styleClass="label"
																text="Eje Tematico" />
														</td>
														<td>
															<ui:dropDown binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.ddEjeTematico}" id="ddEjeTematicoDigesto"
																items="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.ddEjeTematicoDigestoDefaultOptions.options}"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.lblNumero}" id="lblNumero" styleClass="label"
																text="Número" for="tfNumero" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.tfNumero}" id="tfNumero" styleClass="textField"
																columns="10" />
														</td>
												
														<td align="right" nowrap="true">
															<ui:label binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.lblEstadoDigesto}" id="lblEstadoDigesto"
																styleClass="label" text="Estado" />
														</td>
														<td>
															<ui:dropDown binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.ddEstadoDigesto}" id="ddEstadoDigesto"
																items="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.ddEstadoDigestoDefaultOptions.options}" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.lblFecha}" id="lblFecha" styleClass="label"
																text="Año" for="tfFecha" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.tfFecha}" id="tfFecha"
																onKeyPress="return ValidarNum(event,this)" maxLength="4" styleClass="textField" columns="10" />
															<ui:staticText binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.staticText5}" escape="false" id="staticText5"
																styleClass="label" text="&amp;nbsp;[aaaa]" />
														</td>
														<td align="right" nowrap="true">
															<ui:label binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.lblDescripcion}" id="lblDescripcion"
																styleClass="label" text="Descripción (Voces o Palabras)" for="tfDescripcion" />
														</td>
														<td>
															<ui:textArea binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.taDescripcion}" id="taDescripcion"
																styleClass="textArea" columns="40" />
														</td>
													</tr>
															<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
														</td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tr>
									<td></td>
								</tr>
								<tfoot>
									<tr>
										<td align="right" colspan="4">
											<a4j:commandButton binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnBuscar}"
												action="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnReiniciar_action}"
												binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnCancelar_action}"
												binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.paginatedTable}" id="table1" width="200"
											styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnSeleccionar_action}"
														binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnAgregar_action}"
														binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnModificar_action}"
														binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnEliminar_action}"
														binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.stSeparadorAccion}" id="separador_2"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnConsultar_action}"
														binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText text="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.textoSeparador}" id="separador_3"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnExportar_action}"
														binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_4" styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
							  <tr>
									<td align="left" colspan="2">
										<ui:label binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr> 
							</table>
						</div>
					</div>
					<script>
                    document.getElementById('form1:tfNumero').focus();
                        </script>
					<ui:hiddenField binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.idSubSesion}" />
					<ui:script binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
					<ui:script binding="#{framework$ABMDigestoMunicipal$AdminDigestoMunicipal.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
