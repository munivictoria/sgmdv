<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMProvincia$AdminProvincia.page1}" id="page1">
			<ui:html binding="#{framework$ABMProvincia$AdminProvincia.html1}" id="html1">
			<ui:head binding="#{framework$ABMProvincia$AdminProvincia.head1}" id="head1" title="Administración de Provincias">
				<ui:link binding="#{framework$ABMProvincia$AdminProvincia.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMProvincia$AdminProvincia.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMProvincia$AdminProvincia.form1}" id="form1"
					virtualFormsConfig="vfCancelar | hidIdPagina hidIdSubSesion | btnCancelar">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMProvincia$AdminProvincia.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{framework$ABMProvincia$AdminProvincia.head1.title}" />
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
											<ui:panelGroup binding="#{framework$ABMProvincia$AdminProvincia.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{framework$ABMProvincia$AdminProvincia.label1}" id="label1" styleClass="label" text="País" />
														</td>
														<td>
															<ui:dropDown binding="#{framework$ABMProvincia$AdminProvincia.ddPais}" id="ddPais"
																items="#{framework$ABMProvincia$AdminProvincia.ddPaisOptions.options}" styleClass="textField" />
															<ui:button action="#{framework$ABMProvincia$AdminProvincia.btnSeleccionarPais_action}"
																binding="#{framework$ABMProvincia$AdminProvincia.btnSeleccionarPais}" escape="false" id="btnSeleccionarPais" mini="true"
																styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarPais" reRender="form1:ddPais" title="Limpiar"
																binding="#{framework$ABMProvincia$AdminProvincia.btnLimpiarPais}"
																action="#{framework$ABMProvincia$AdminProvincia.btnLimpiarPais_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{framework$ABMProvincia$AdminProvincia.label2}" for="tfNombre" id="label2" styleClass="label"
																text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMProvincia$AdminProvincia.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{framework$ABMProvincia$AdminProvincia.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{framework$ABMProvincia$AdminProvincia.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{framework$ABMProvincia$AdminProvincia.btnBuscar}"
												action="#{framework$ABMProvincia$AdminProvincia.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMProvincia$AdminProvincia.btnReiniciar_action}"
												binding="#{framework$ABMProvincia$AdminProvincia.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{framework$ABMProvincia$AdminProvincia.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMProvincia$AdminProvincia.btnCancelar_action}"
												binding="#{framework$ABMProvincia$AdminProvincia.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMProvincia$AdminProvincia.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{framework$ABMProvincia$AdminProvincia.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMProvincia$AdminProvincia.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMProvincia$AdminProvincia.btnSeleccionar_action}"
														binding="#{framework$ABMProvincia$AdminProvincia.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMProvincia$AdminProvincia.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMProvincia$AdminProvincia.btnAgregar_action}"
														binding="#{framework$ABMProvincia$AdminProvincia.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{framework$ABMProvincia$AdminProvincia.btnModificar_action}"
														binding="#{framework$ABMProvincia$AdminProvincia.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{framework$ABMProvincia$AdminProvincia.btnEliminar_action}"
														binding="#{framework$ABMProvincia$AdminProvincia.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{framework$ABMProvincia$AdminProvincia.stSeparadorAccion}" id="separador_2"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMProvincia$AdminProvincia.btnConsultar_action}"
														binding="#{framework$ABMProvincia$AdminProvincia.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{framework$ABMProvincia$AdminProvincia.textoSeparador}" id="separador_3"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMProvincia$AdminProvincia.btnExportar_action}"
														binding="#{framework$ABMProvincia$AdminProvincia.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{framework$ABMProvincia$AdminProvincia.paginatedTable.stSeparadorOrdenamiento}" id="separador_4"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{framework$ABMProvincia$AdminProvincia.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{framework$ABMProvincia$AdminProvincia.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{framework$ABMProvincia$AdminProvincia.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarPais').focus();
					</script>
					<ui:hiddenField binding="#{framework$ABMProvincia$AdminProvincia.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMProvincia$AdminProvincia.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMProvincia$AdminProvincia.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMProvincia$AdminProvincia.idSubSesion}" />
					<ui:script binding="#{framework$ABMProvincia$AdminProvincia.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{framework$ABMProvincia$AdminProvincia.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
