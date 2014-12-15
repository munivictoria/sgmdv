<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.page1}" id="page1">
			<ui:html binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.html1}" id="html1">
			<ui:head binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.head1}" id="head1"
				title="Administración de Configuración de Recursos">
				<ui:link binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar(); "
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td></td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.lblUsuario}" for="tfUsuario" id="lblUsuario"
																styleClass="label" text="Usuario" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.tfUsuario}" columns="30" disabled="true"
																id="tfUsuario" styleClass="textFieldDisabled" />
															<ui:button action="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnBuscarUsuario_action}"
																binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnBuscarUsuario}" escape="false"
																id="btnSeleccionarUsuario" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarUsuario" reRender="form1:tfUsuario" title="Limpiar"
																binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnLimpiarUsuario}"
																action="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnLimpiarUsuario_action}"
																styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.label1}" for="tfRecurso" id="lblRecurso"
																styleClass="label" text="Recurso" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.tfRecurso}" columns="40" disabled="true"
																id="tfRecurso" styleClass="textFieldDisabled" />
															<ui:button action="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnBuscarRecurso_action}"
																binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnBuscarRecurso}" escape="false"
																id="btnSeleccionarRecurso" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarRecurso" reRender="form1:tfRecurso" title="Limpiar"
																binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnLimpiarRecurso}"
																action="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnLimpiarRecurso_action}"
																styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.label2}" for="tfNombreAlias" id="label2"
																styleClass="label" text="Nombre Alias" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.tfNombreAlias}" columns="40"
																id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td></td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnBuscar}"
												action="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1, form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnReiniciar_action}"
												binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros " />
											<ui:staticText binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.staticText2}" escape="false"
												id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnCancelar_action}"
												binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.paginatedTable}" id="table1"
											styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnSeleccionar_action}"
														binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnAgregar_action}"
														binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnModificar_action}"
														binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnEliminar_action}"
														binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.stSeparadorAccion}" id="separador_2"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnConsultar_action}"
														binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText text="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.textoSeparador}" id="separador_3"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnExportar_action}"
														binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_4" styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombreAlias').focus();
					</script>
					<ui:hiddenField binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.idSubSesion}" />
					<ui:script binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{framework$ABMConfiguracionRecurso$AdminConfiguracionRecurso.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
