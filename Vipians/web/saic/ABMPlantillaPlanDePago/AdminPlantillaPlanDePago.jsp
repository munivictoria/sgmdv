<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.page1}" id="page1">
			<ui:html binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.html1}" id="html1">
			<ui:head binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.head1}" id="head1" title="Administración de Áreas">
				<ui:link binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.head1.title}" />
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
											<ui:panelGroup binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.label2}" for="tfNombre" id="label2" styleClass="label" text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnBuscar}" action="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnBuscar_action}"
												id="btnBuscar" value="Buscar" styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros"
												oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnReiniciar_action}"
												binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnCancelar_action}" binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnCancelar}"
												id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.messageGroup}" id="messageGroup" showDetail="true" showSummary="false"
										styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.paginatedTable}" id="table1" styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.groupPanel1}" id="groupPanel1">
													<ui:button action="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnSeleccionar_action}"
														binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnAgregar_action}" binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnAgregar}"
														id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnModificar_action}" binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnModificar}"
														id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnEliminar_action}" binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnEliminar}"
														id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.stSeparadorAccion}" id="separador_2" styleClass="barraSeparadoraVertical" />
													<ui:button action="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnConsultar_action}" binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnConsultar}"
														id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.textoSeparador}" id="separador_3" styleClass="barraSeparadoraVertical" />
													<ui:button action="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnExportar_action}" binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.btnExportar}"
														id="btnExportar" styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.paginatedTable.stSeparadorOrdenamiento}" id="separador_4"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.stCantidadRegistros}" id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.hidIdPagina}" id="hidIdPagina" text="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.idPagina}" />
					<ui:hiddenField binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.idSubSesion}" />
					<ui:script binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{saic$ABMPlantillaPlanDePago$AdminPlantillaPlanDePago.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
