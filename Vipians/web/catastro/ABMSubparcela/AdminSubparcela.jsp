<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMSubparcela$AdminSubparcela.page1}" id="page1">
			<ui:html binding="#{catastro$ABMSubparcela$AdminSubparcela.html1}" id="html1">
			<ui:head binding="#{catastro$ABMSubparcela$AdminSubparcela.head1}" id="head1" title="Administración de Subparcelas">
				<ui:link binding="#{catastro$ABMSubparcela$AdminSubparcela.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{catastro$ABMSubparcela$AdminSubparcela.body1}" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMSubparcela$AdminSubparcela.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{catastro$ABMSubparcela$AdminSubparcela.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{catastro$ABMSubparcela$AdminSubparcela.head1.title}" />
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
											<ui:panelGroup binding="#{catastro$ABMSubparcela$AdminSubparcela.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMSubparcela$AdminSubparcela.lblParcelaSeleccionada}" for="tfParcelaSeleccionada"
																id="lblParcelaSeleccionada" style="" styleClass="label" text="Parcela" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{catastro$ABMSubparcela$AdminSubparcela.tfParcelaSeleccionada}" columns="45" disabled="true"
																id="tfParcelaSeleccionada" styleClass="textField" />
															<ui:button action="#{catastro$ABMSubparcela$AdminSubparcela.btnSeleccionarParcela_action}"
																binding="#{catastro$ABMSubparcela$AdminSubparcela.btnSeleccionarParcela}" escape="false" id="btnSeleccionarParcela"
																mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcelaSeleccionada" title="Limpiar"
																binding="#{catastro$ABMSubparcela$AdminSubparcela.btnLimpiarParcela}"
																action="#{catastro$ABMSubparcela$AdminSubparcela.btnLimpiarParcela_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{catastro$ABMSubparcela$AdminSubparcela.lblPersonaSeleccionada}" for="tfPersonaSeleccionada"
																id="lblPersonaSeleccionada" styleClass="label" text="Titular" />
														</td>
														<td>
															<ui:textField binding="#{catastro$ABMSubparcela$AdminSubparcela.tfPersonaSeleccionada}" columns="40" disabled="true"
																id="tfPersonaSeleccionada" styleClass="textField" />
															<ui:button action="#{catastro$ABMSubparcela$AdminSubparcela.btnSeleccionarPersonaFisica_action}"
																binding="#{catastro$ABMSubparcela$AdminSubparcela.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Física" />
															<ui:button action="#{catastro$ABMSubparcela$AdminSubparcela.btnSeleccionarPersonaJuridica_action}"
																binding="#{catastro$ABMSubparcela$AdminSubparcela.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Jurídica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaSeleccionada" title="Limpiar"
																binding="#{catastro$ABMSubparcela$AdminSubparcela.btnLimpiarPersona}"
																action="#{catastro$ABMSubparcela$AdminSubparcela.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td colspan="2"></td>
													</tr>
												</table>
											</ui:panelGroup>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="4">
											<a4j:commandButton binding="#{catastro$ABMSubparcela$AdminSubparcela.btnBuscar}"
												action="#{catastro$ABMSubparcela$AdminSubparcela.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1,stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{catastro$ABMSubparcela$AdminSubparcela.btnReiniciar_action}"
												binding="#{catastro$ABMSubparcela$AdminSubparcela.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1,stCantidadRegistros" />
											<ui:staticText binding="#{catastro$ABMSubparcela$AdminSubparcela.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{catastro$ABMSubparcela$AdminSubparcela.btnCancelar_action}"
												binding="#{catastro$ABMSubparcela$AdminSubparcela.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{catastro$ABMSubparcela$AdminSubparcela.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td colspan="4">
										<ui:table binding="#{catastro$ABMSubparcela$AdminSubparcela.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{catastro$ABMSubparcela$AdminSubparcela.groupPanel1}" id="groupPanel1" style="">
													<ui:button action="#{catastro$ABMSubparcela$AdminSubparcela.btnSeleccionar_action}"
														binding="#{catastro$ABMSubparcela$AdminSubparcela.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
													<ui:staticText binding="#{catastro$ABMSubparcela$AdminSubparcela.stSeparadorSeleccionar}" escape="false" id="staticText6"
														styleClass="barraSeparadoraVertical" />
													<!--
                                                <ui:button action="#{catastro$ABMSubparcela$AdminSubparcela.btnConsultar_action}"
                                                           binding="#{catastro$ABMSubparcela$AdminSubparcela.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar"/>
                                                -->
													<!--
                                                <ui:button action="#{catastro$ABMSubparcela$AdminSubparcela.btnImprimirReporte_action}"
                                                           binding="#{catastro$ABMSubparcela$AdminSubparcela.btnImprimirReporte}" id="btnImprimirReporte"
                                                           styleClass="button" text="Visualizar Reporte" onClick="newWindow = window.open('ImprimirParcela.jsp', '_parent')"/>
                                                -->
													<ui:button action="#{catastro$ABMSubparcela$AdminSubparcela.btnExportar_action}"
														binding="#{catastro$ABMSubparcela$AdminSubparcela.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
														onClick="return exportarReporte()" />
													<ui:staticText binding="#{catastro$ABMSubparcela$AdminSubparcela.paginatedTable.stSeparadorOrdenamiento}"
														styleClass="barraSeparadoraVertical" />
													<ui:imageHyperlink binding="#{catastro$ABMSubparcela$AdminSubparcela.paginatedTable.botonOrdenamiento}" />
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{catastro$ABMSubparcela$AdminSubparcela.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{catastro$ABMSubparcela$AdminSubparcela.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarParcela').focus();
					</script>
					<ui:hiddenField binding="#{catastro$ABMSubparcela$AdminSubparcela.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMSubparcela$AdminSubparcela.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMSubparcela$AdminSubparcela.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMSubparcela$AdminSubparcela.idSubSesion}" />
					<ui:script binding="#{catastro$ABMSubparcela$AdminSubparcela.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{catastro$ABMSubparcela$AdminSubparcela.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
