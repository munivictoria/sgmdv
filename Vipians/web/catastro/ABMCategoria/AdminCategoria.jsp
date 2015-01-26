<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMCategoria$AdminCategoria.page1}" id="page1">
			<ui:html binding="#{catastro$ABMCategoria$AdminCategoria.html1}" id="html1">
			<ui:head binding="#{catastro$ABMCategoria$AdminCategoria.head1}" id="head1" title="Administración de Categorías">
				<ui:link binding="#{catastro$ABMCategoria$AdminCategoria.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMCategoria$AdminCategoria.body1}" focus="form1:tfCodigo" id="body1"
				onLoad="parent.footer.location.reload();Init(); changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMCategoria$AdminCategoria.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{catastro$ABMCategoria$AdminCategoria.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{catastro$ABMCategoria$AdminCategoria.head1.title}" />
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
											<ui:panelGroup binding="#{catastro$ABMCategoria$AdminCategoria.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{catastro$ABMCategoria$AdminCategoria.label3}" id="label3" styleClass="label" text="Código" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMCategoria$AdminCategoria.tfCodigo}" onKeyPress="return ValidarNum(event)" id="tfCodigo"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{catastro$ABMCategoria$AdminCategoria.label2}" for="tfNombre" id="label2" styleClass="label"
																text="Nombre" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMCategoria$AdminCategoria.tfNombre}" columns="40" id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{catastro$ABMCategoria$AdminCategoria.label1}" id="label1" style="" styleClass="label"
																text="Tipo de Construcción" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMCategoria$AdminCategoria.tfTipoConstruccion}" columns="40" disabled="true"
																id="tfTipoConstruccion" styleClass="textField" />
															<ui:button action="#{catastro$ABMCategoria$AdminCategoria.btnSeleccionarTipoConstruccion_action}"
																binding="#{catastro$ABMCategoria$AdminCategoria.btnSeleccionarTipoConstruccion}" escape="false"
																id="btnSeleccionarTipoConstruccion" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarTipoConstruccion" reRender="form1:tfTipoConstruccion" title="Limpiar"
																binding="#{catastro$ABMCategoria$AdminCategoria.btnLimpiarTipoConstruccion}"
																action="#{catastro$ABMCategoria$AdminCategoria.btnLimpiarTipoConstruccion_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{catastro$ABMCategoria$AdminCategoria.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{catastro$ABMCategoria$AdminCategoria.tablaBusquedaLogs}" id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{catastro$ABMCategoria$AdminCategoria.btnBuscar}"
												action="#{catastro$ABMCategoria$AdminCategoria.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{catastro$ABMCategoria$AdminCategoria.btnReiniciar_action}"
												binding="#{catastro$ABMCategoria$AdminCategoria.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{catastro$ABMCategoria$AdminCategoria.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{catastro$ABMCategoria$AdminCategoria.btnCancelar_action}"
												binding="#{catastro$ABMCategoria$AdminCategoria.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{catastro$ABMCategoria$AdminCategoria.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{catastro$ABMCategoria$AdminCategoria.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMCategoria$AdminCategoria.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{catastro$ABMCategoria$AdminCategoria.btnSeleccionar_action}"
														binding="#{catastro$ABMCategoria$AdminCategoria.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{catastro$ABMCategoria$AdminCategoria.stSeparadorSeleccionar}" id="separador_1"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMCategoria$AdminCategoria.btnAgregar_action}"
														binding="#{catastro$ABMCategoria$AdminCategoria.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
													<ui:button action="#{catastro$ABMCategoria$AdminCategoria.btnModificar_action}"
														binding="#{catastro$ABMCategoria$AdminCategoria.btnModificar}" id="btnModificar" styleClass="button" text="Modificar" />
													<ui:button action="#{catastro$ABMCategoria$AdminCategoria.btnEliminar_action}"
														binding="#{catastro$ABMCategoria$AdminCategoria.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar" />
													<ui:staticText binding="#{catastro$ABMCategoria$AdminCategoria.stSeparadorAccion}" id="separador_2"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMCategoria$AdminCategoria.btnConsultar_action}"
														binding="#{catastro$ABMCategoria$AdminCategoria.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
													<ui:staticText text="#{catastro$ABMCategoria$AdminCategoria.textoSeparador}" id="separador_3"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMCategoria$AdminCategoria.btnExportar_action}"
														binding="#{catastro$ABMCategoria$AdminCategoria.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{catastro$ABMCategoria$AdminCategoria.paginatedTable.stSeparadorOrdenamiento}" id="separador_4"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{catastro$ABMCategoria$AdminCategoria.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{catastro$ABMCategoria$AdminCategoria.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{catastro$ABMCategoria$AdminCategoria.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
									<td align="right">
										<ui:hyperlink binding="#{catastro$ABMCategoria$AdminCategoria.hpAgregarPaginaAccesoDirecto}"/>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfCodigo').focus();
					</script>
					<ui:hiddenField binding="#{catastro$ABMCategoria$AdminCategoria.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMCategoria$AdminCategoria.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMCategoria$AdminCategoria.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMCategoria$AdminCategoria.idSubSesion}" />
					<ui:script binding="#{catastro$ABMCategoria$AdminCategoria.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{catastro$ABMCategoria$AdminCategoria.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
