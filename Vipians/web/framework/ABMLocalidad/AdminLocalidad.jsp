<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMLocalidad$AdminLocalidad.page1}" id="page1">
			<ui:html binding="#{framework$ABMLocalidad$AdminLocalidad.html1}" id="html1">
			<ui:head binding="#{framework$ABMLocalidad$AdminLocalidad.head1}" id="head1" title="Administración de Localidades">
				<ui:link binding="#{framework$ABMLocalidad$AdminLocalidad.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMLocalidad$AdminLocalidad.body1}" focus="form1:btnSeleccionarProvincia" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar(); "
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMLocalidad$AdminLocalidad.form1}" id="form1"
					virtualFormsConfig="vfCancelar | hidIdSubSesion hidIdPagina | btnCancelar">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMLocalidad$AdminLocalidad.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{framework$ABMLocalidad$AdminLocalidad.head1.title}" />
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
											<ui:panelGroup binding="#{framework$ABMLocalidad$AdminLocalidad.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMLocalidad$AdminLocalidad.label3}" id="label3" styleClass="label" text="Provincia" />
														</td>
														<td>
															<ui:dropDown binding="#{framework$ABMLocalidad$AdminLocalidad.ddProvincia}" id="ddProvincia"
																items="#{framework$ABMLocalidad$AdminLocalidad.ddProvinciaOptions.options}" styleClass="textField" />
															<ui:button action="#{framework$ABMLocalidad$AdminLocalidad.btnSeleccionarProvincia_action}"
																binding="#{framework$ABMLocalidad$AdminLocalidad.btnSeleccionarProvincia}" escape="false" id="btnSeleccionarProvincia"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarProvincia" reRender="form1:ddProvincia" title="Limpiar"
																binding="#{framework$ABMLocalidad$AdminLocalidad.btnLimpiarProvincia}"
																action="#{framework$ABMLocalidad$AdminLocalidad.btnLimpiarProvincia_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMLocalidad$AdminLocalidad.label2}" for="tfNombre" id="label2" styleClass="label"
																text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMLocalidad$AdminLocalidad.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{framework$ABMLocalidad$AdminLocalidad.label4}" id="label4" styleClass="label" text="Código Postal" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMLocalidad$AdminLocalidad.tfCodigoPostal}" id="tfCodigoPostal" maxLength="10"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{framework$ABMLocalidad$AdminLocalidad.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{framework$ABMLocalidad$AdminLocalidad.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{framework$ABMLocalidad$AdminLocalidad.btnBuscar}"
												action="#{framework$ABMLocalidad$AdminLocalidad.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMLocalidad$AdminLocalidad.btnReiniciar_action}"
												binding="#{framework$ABMLocalidad$AdminLocalidad.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,stCantidadRegistros" />
											<ui:staticText binding="#{framework$ABMLocalidad$AdminLocalidad.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMLocalidad$AdminLocalidad.btnCancelar_action}"
												binding="#{framework$ABMLocalidad$AdminLocalidad.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMLocalidad$AdminLocalidad.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{framework$ABMLocalidad$AdminLocalidad.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMLocalidad$AdminLocalidad.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMLocalidad$AdminLocalidad.btnSeleccionar_action}"
														binding="#{framework$ABMLocalidad$AdminLocalidad.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMLocalidad$AdminLocalidad.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMLocalidad$AdminLocalidad.btnAgregar_action}"
														binding="#{framework$ABMLocalidad$AdminLocalidad.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{framework$ABMLocalidad$AdminLocalidad.btnModificar_action}"
														binding="#{framework$ABMLocalidad$AdminLocalidad.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{framework$ABMLocalidad$AdminLocalidad.btnEliminar_action}"
														binding="#{framework$ABMLocalidad$AdminLocalidad.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{framework$ABMLocalidad$AdminLocalidad.stSeparadorAccion}" id="separador_2"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMLocalidad$AdminLocalidad.btnConsultar_action}"
														binding="#{framework$ABMLocalidad$AdminLocalidad.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{framework$ABMLocalidad$AdminLocalidad.textoSeparador}" id="separador_3"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMLocalidad$AdminLocalidad.btnExportar_action}"
														binding="#{framework$ABMLocalidad$AdminLocalidad.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{framework$ABMLocalidad$AdminLocalidad.paginatedTable.stSeparadorOrdenamiento}" id="separador_4"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{framework$ABMLocalidad$AdminLocalidad.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{framework$ABMLocalidad$AdminLocalidad.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{framework$ABMLocalidad$AdminLocalidad.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarProvincia').focus();
					</script>
					<ui:hiddenField binding="#{framework$ABMLocalidad$AdminLocalidad.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMLocalidad$AdminLocalidad.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMLocalidad$AdminLocalidad.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMLocalidad$AdminLocalidad.idSubSesion}" />
					<ui:script binding="#{framework$ABMLocalidad$AdminLocalidad.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{framework$ABMLocalidad$AdminLocalidad.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
