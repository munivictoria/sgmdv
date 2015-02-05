<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.page1}" id="page1">
			<ui:html binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.html1}" id="html1">
			<ui:head binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.head1}" id="head1"
				title="Administración de Valores Básicos por Mejoras">
				<ui:link binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.body1}" focus="form1:btnSeleccionarCategoria" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.head1.title}" />
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
											<ui:panelGroup binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.label1}" id="label1" style="" styleClass="label"
																text="Categoría" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.tfCategoria}" columns="40" disabled="true"
																id="tfCategoria" styleClass="textField" />
															<ui:button action="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnSeleccionarCategoria_action}"
																binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnSeleccionarCategoria}" escape="false"
																id="btnSeleccionarCategoria" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarCategoria" reRender="form1:tfCategoria" title="Limpiar"
																binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnLimpiarCategoria}"
																action="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnLimpiarCategoria_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnBuscar}"
												action="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnReiniciar_action}"
												binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnCancelar_action}"
												binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td colspan="2">
										<ui:table binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnSeleccionar_action}"
														binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
														text="Seleccionar" />
													<ui:staticText binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.stSeparadorSeleccionar}" escape="false"
														styleClass="barraSeparadoraVertical" id="staticText6" />
													<ui:button action="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnAgregar_action}"
														binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnModificar_action}"
														binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnModificar}" id="btnModificar" styleClass="button"
														text="Modificar" />
													<ui:button action="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnEliminar_action}"
														binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.stSeparadorAccion}" escape="false"
														styleClass="barraSeparadoraVertical" id="staticText8" />
													<ui:button action="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnConsultar_action}"
														binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnConsultar}" id="btnConsultar" styleClass="button"
														text="Consultar" />
													<ui:staticText text="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.textoSeparador}" escape="false" id="staticText9"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnExportar_action}"
														binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.paginatedTable.stSeparadorOrdenamiento}"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left">
										<ui:label binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
									<td align="right">
										<ui:hyperlink binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.hpAgregarPaginaAccesoDirecto}"/>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarCategoria').focus();
					</script>
					<ui:hiddenField binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.idSubSesion}" />
					<ui:script binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{catastro$ABMValorBasicoMejora$AdminValorBasicoMejora.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
