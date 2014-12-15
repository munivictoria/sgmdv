<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMPais$AdminPais.page1}" id="page1">
			<ui:html binding="#{framework$ABMPais$AdminPais.html1}" id="html1">
			<ui:head binding="#{framework$ABMPais$AdminPais.head1}" id="head1" title="Administración de Paises">
				<ui:link binding="#{framework$ABMPais$AdminPais.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMPais$AdminPais.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMPais$AdminPais.form1}" id="form1"
					virtualFormsConfig="vfCancelar | hidIdPagina hidIdSubSesion | div:table:tfoot:tr:td:btnCancelar">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{framework$ABMPais$AdminPais.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{framework$ABMPais$AdminPais.head1.title}" />
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
											<ui:panelGroup binding="#{framework$ABMPais$AdminPais.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{framework$ABMPais$AdminPais.label1}" id="label1" styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{framework$ABMPais$AdminPais.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{framework$ABMPais$AdminPais.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{framework$ABMPais$AdminPais.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{framework$ABMPais$AdminPais.btnBuscar}" action="#{framework$ABMPais$AdminPais.btnBuscar_action}"
												id="btnBuscar" value="Buscar" styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros"
												oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{framework$ABMPais$AdminPais.btnReiniciar_action}"
												binding="#{framework$ABMPais$AdminPais.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{framework$ABMPais$AdminPais.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{framework$ABMPais$AdminPais.btnCancelar_action}" binding="#{framework$ABMPais$AdminPais.btnCancelar}"
												id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{framework$ABMPais$AdminPais.messageGroup}" id="messageGroup" showDetail="true" showSummary="false"
										styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{framework$ABMPais$AdminPais.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{framework$ABMPais$AdminPais.groupPanel1}" id="groupPanel1">
													<ui:button action="#{framework$ABMPais$AdminPais.btnSeleccionar_action}"
														binding="#{framework$ABMPais$AdminPais.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{framework$ABMPais$AdminPais.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMPais$AdminPais.btnAgregar_action}" binding="#{framework$ABMPais$AdminPais.btnAgregar}"
														id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{framework$ABMPais$AdminPais.btnModificar_action}" binding="#{framework$ABMPais$AdminPais.btnModificar}"
														id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{framework$ABMPais$AdminPais.btnEliminar_action}" binding="#{framework$ABMPais$AdminPais.btnEliminar}"
														id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{framework$ABMPais$AdminPais.stSeparadorAccion}" id="separador_2" styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMPais$AdminPais.btnConsultar_action}" binding="#{framework$ABMPais$AdminPais.btnConsultar}"
														id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{framework$ABMPais$AdminPais.textoSeparador}" id="separador_3" styleClass="barraSeparadoraVertical" />
													<ui:button action="#{framework$ABMPais$AdminPais.btnExportar_action}" binding="#{framework$ABMPais$AdminPais.btnExportar}"
														id="btnExportar" styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{framework$ABMPais$AdminPais.paginatedTable.stSeparadorOrdenamiento}" id="separador_4"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{framework$ABMPais$AdminPais.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{framework$ABMPais$AdminPais.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{framework$ABMPais$AdminPais.stCantidadRegistros}" id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{framework$ABMPais$AdminPais.hidIdPagina}" id="hidIdPagina" text="#{framework$ABMPais$AdminPais.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMPais$AdminPais.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMPais$AdminPais.idSubSesion}" />
					<ui:script binding="#{framework$ABMPais$AdminPais.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{framework$ABMPais$AdminPais.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
