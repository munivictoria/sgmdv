<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.page1}" id="page1">
			<ui:html binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.html1}" id="html1">
			<ui:head binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.head1}" id="head1"
				title="Administración de Coeficientes de Depreciación">
				<ui:link binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.body1}" focus="form1:tfCodigo" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="Administración de Coeficientes de Depreciación" />
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
											<ui:panelGroup binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right">
															<ui:label binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.label2}" for="tfCodigo" id="label2"
																styleClass="label" text="Código de Categoría" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.tfCodigo}" id="tfCodigo"
																onKeyPress="return ValidarNum(event)" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right">
															<ui:label binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.label3}" for="tfNombre" id="label3"
																styleClass="label" text="Nombre de Categoría" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.tfNombre}" columns="40"
																id="tfNombre" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.label4}" for="tfTipoConstruccion"
																id="label4" styleClass="label" text="Tipo de Construcción" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.tfTipoConstruccion}" columns="40"
																disabled="true" id="tfTipoConstruccion" styleClass="textField" />
															<ui:button action="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnSeleccionarTipoConstruccion_action}"
																binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnSeleccionarTipoConstruccion1}" escape="false"
																id="btnSeleccionarTipoConstruccion1" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarTipoConstruccion" reRender="form1:tfTipoConstruccion" title="Limpiar"
																binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnLimpiarTipoConstruccion}"
																action="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnLimpiarTipoConstruccion_action}"
																styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:checkbox binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.tablaBusquedaLogs}"
																id="tablaBusquedaLogs" />
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
											<a4j:commandButton binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnBuscar}"
												action="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnReiniciar_action}"
												binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.staticText2}" escape="false"
												id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnCancelar_action}"
												binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td colspan="2">
										<ui:table binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.paginatedTable}" styleClass="tablaPaginada"
											id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.groupPanel1}" id="groupPanel1">
													<ui:button action="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnSeleccionar_action}"
														binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.stSeparadorSeleccionar}"
														id="separador_1" styleClass="barraSeparadoraVertical" />
													<!--<ui:button action="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnAgregar_action}"
                                                           binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnAgregar}" disabled="true"
                                                           id="btnAgregar" styleClass="button" text="Agregar"/> -->
													<ui:button action="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnModificar_action}"
														binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnModificar}" id="btnModificar"
														styleClass="button" text="Modificar" />
													<!--<ui:button action="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnEliminar_action}"
                                                           binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnEliminar}"
                                                           disabled="true" id="btnEliminar" styleClass="button" text="Eliminar"/> -->
													<ui:staticText binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.stSeparadorAccion}" id="separador_2"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnConsultar_action}"
														binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnConsultar}" id="btnConsultar"
														styleClass="button" text="Consultar" />
													<ui:staticText text="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.textoSeparador}" id="separador_3"
														styleClass="barraSeparadoraVertical" />
													<ui:button action="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnExportar_action}"
														binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText
														binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_4" styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink
														binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left">
										<ui:label binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
									<td align="right">
										<ui:hyperlink binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.hpAgregarPaginaAccesoDirecto}"/>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfCodigo').focus();
					</script>
					<ui:hiddenField binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.idSubSesion}" />
					<ui:script binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{catastro$ABMCoeficienteDepreciacion$AdminCoeficienteDepreciacion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
