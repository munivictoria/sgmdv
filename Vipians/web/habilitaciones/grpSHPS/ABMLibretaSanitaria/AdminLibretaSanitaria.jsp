<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.head1}" id="head1"
				title="Administración de Libretas Sanitarias">
				<ui:link binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.body1}" focus="form1:tfNumeroLibretaSanitaria"
				id="body1" onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.label2}" for="tfNumeroLibretaSanitaria"
																id="label2" styleClass="label" text="Número" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.tfNumeroLibretaSanitaria}"
																columns="10" id="tfNumeroLibretaSanitaria" styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.label1}" for="tfPersonaFisica"
																id="label1" styleClass="label" text="Persona" />
														</td>
														<td>
															<!-- <ui:textField binding="#{catastro$ABMManzana$AdminManzana.tfZona}" columns="40" disabled="true" id="tfZona" styleClass="textField"/>
                                                <ui:button action="#{catastro$ABMManzana$AdminManzana.btnSeleccionarZona_action}"
                                                           binding="#{catastro$ABMManzana$AdminManzana.btnSeleccionarZona}" escape="false" id="btnSeleccionarZona"
                                                           mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar"/>-->
															<ui:textField binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.tfPersonaFisica}" columns="40"
																disabled="true" id="tfPersonaFisica" styleClass="textField" />
															<ui:button action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnSeleccionarPersonaFisica_action}"
																binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaFisica" title="Limpiar"
																binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnLimpiarPersona}"
																action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnLimpiarPersona_action}"
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
															<ui:checkbox binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.tablaBusquedaLogs}"
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
											<a4j:commandButton binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnBuscar}"
												action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnReiniciar_action}"
												binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.staticText2}" escape="false"
												id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnCancelar_action}"
												binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.groupPanel1}" id="groupPanel1">
													<ui:button action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnSeleccionar_action}"
														binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnSeleccionar}" id="btnSeleccionar"
														styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.stSeparadorSeleccionar}"
														escape="false" id="staticText6" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnAgregar_action}"
														binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnAgregar}" id="btnAgregar" styleClass="button"
														text="Agregar" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnModificar_action}"
														binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnModificar}" id="btnModificar"
														styleClass="button" text="Modificar" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnEliminar_action}"
														binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnEliminar}" id="btnEliminar" styleClass="button"
														text="Eliminar" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.stSeparadorAccion}" escape="false"
														id="staticText8" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnConsultar_action}"
														binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnConsultar}" id="btnConsultar"
														styleClass="button" text="Consultar" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.staticText4}" escape="false"
														id="staticText4" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnImprimirReporte_action}"
														binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnImprimirReporte}" id="btnImprimirReporte"
														styleClass="button" text="Imprimir Reporte" disabled="true"
														onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.staticText9}" escape="false"
														id="staticText9" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
													<ui:button action="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnExportar_action}"
														binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.btnExportar}" id="btnExportar" styleClass="button"
														text="Exportar" onClick="return exportarReporte()" />
													<ui:staticText
														binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.paginatedTable.stSeparadorOrdenamiento}"
														id="separador_1" />
													<ui:imageHyperlink
														binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNumeroLibretaSanitaria').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMLibretaSanitaria$AdminLibretaSanitaria.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
