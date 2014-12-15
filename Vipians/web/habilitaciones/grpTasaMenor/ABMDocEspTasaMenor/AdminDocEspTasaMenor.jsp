<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.head1}" id="head1"
				title="Administración de Obligaciones: Tasas Menores">
				<ui:link binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.link1}" id="link1"
					url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfPersonaSeleccionada", "parcela", nombreBean, "setPersonaAutocompletar");
						autoCompletarEnTextField("#form1:tfParcelaSeleccionada", "parcela", nombreBean, "setParcelaAutocompletar");
					}

					function focusearTfParcelaSeleccionada() {
						$("#form1\\:tfParcelaSeleccionada").focus();
					}

					function focusearTfPersonaSeleccionada() {
						$("#form1\\:tfPersonaSeleccionada").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.body1}" focus="form1:btnSeleccionarPersonaFisica"
				id="body1" onLoad="parent.footer.location.reload();Init(); changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.head1.title}" />
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
											<ui:panelGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.lblPersonaSeleccionada}"
																for="tfPersonaSeleccionada" id="lblPersonaSeleccionada" styleClass="label" text="Persona" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.tfPersonaSeleccionada}"
																columns="40" id="tfPersonaSeleccionada"
																styleClass="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnSeleccionarPersonaFisica_action}"
																binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
															<ui:button
																action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnSeleccionarPersonaJuridica_action}"
																binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnSeleccionarPersonaJuridica}"
																escape="false" id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ"
																toolTip="Seleccionar Persona Juridica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaSeleccionada" title="Limpiar"
																binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnLimpiarPersona}"
																action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnLimpiarPersona_action}"
																styleClass="buttonLimpiarAjax" oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.lblParcelaSeleccionada}"
																for="tfParcelaSeleccionada" id="lblParcelaSeleccionada" styleClass="label" text="Parcela" />
														</td>
														<td nowrap="nowrap">
															<ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.tfParcelaSeleccionada}"
																columns="40" id="tfParcelaSeleccionada"
																styleClass="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.paginatedTable.filtro.parcela != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.paginatedTable.filtro.parcela != null}" />
															<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnSeleccionarParcela_action}"
																binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnSeleccionarParcela}" escape="false"
																id="btnSeleccionarParcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Parcela" />
															<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcelaSeleccionada" title="Limpiar"
																oncomplete="cargarComportamientoJQuery(); focusearTfParcelaSeleccionada();"
																binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnLimpiarParcela}"
																action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnLimpiarParcela_action}"
																styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="true">
															<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.lblTipoObligacion}"
																for="ddTipoObligacion" id="lblTipoObligacion" styleClass="label" text="Tipo de Obligación" />
														</td>
														<td>
															<ui:dropDown binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.ddTipoObligacion}"
																id="ddTipoObligacion"
																items="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.ddTipoObligacionDefaultOptions.options}"
																styleClass="textField" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap" style="height: 18px">
															<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.lblNroParcela}" for="tfNroParcela"
																id="lblNroParcela" styleClass="label" text="Nº de Parcela" />
														</td>
														<td>
															<ui:textField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.tfNroParcela}" id="tfNroParcela"
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
															<ui:checkbox
																binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.tablaBusquedaLogs.ckbBuscarPorLogs}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:table binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.tablaBusquedaLogs}"
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
											<a4j:commandButton binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnBuscar}"
												action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnReiniciar_action}"
												binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.stSeparador1}" escape="false"
												id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnCancelar_action}"
												binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnCancelar}" id="btnCancelar"
												styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:panelGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.gpSeleccion}" id="gpSeleccion">
											<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.label3}" for="ddPlantillaObligacion"
												id="label3" styleClass="label" text="Plantilla de Obligación" />
											<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.stSeparador2}" escape="false"
												id="staticText5" styleClass="label" text="&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
											<ui:dropDown binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.ddPlantillaObligacion}"
												id="ddPlantillaObligacion"
												items="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.ddPlantillaObligacionOptions.options}"
												styleClass="textField" />
										</ui:panelGroup>
									</td>
								</tr>
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.paginatedTable}" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.gpBotones}" id="gpBotones">
														<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnSeleccionar_action}"
															binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnSeleccionar}" id="btnSeleccionar"
															styleClass="button" text="Seleccionar" />
														<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.stSeparadorSeleccionar}"
															escape="false" id="staticText6" />
														<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnAgregar_action}"
															binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnAgregar}" id="btnAgregar"
															styleClass="button" text="Agregar" />
														<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnModificar_action}"
															binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnModificar}" id="btnModificar"
															styleClass="button" text="Modificar" />
														<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnEliminar_action}"
															binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnEliminar}" id="btnEliminar"
															styleClass="button" text="Eliminar" />
														<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.stSeparadorAccion}"
															escape="false" id="staticText8" />
														<!--<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnDardeAlta_action}"
															binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnDardeAButton}" id="btnDardeAlta" styleClass="button"
															text="Dar de Alta" /> -->
														<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnConsultar_action}"
															binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnConsultar}" id="btnConsultar"
															styleClass="button" text="Consultar" />
														<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.staticText7}" escape="false"
															id="staticText7" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.idImprimirReporte}" disabled="true"
															id="idImprimirReporte" styleClass="button" text="Visualizar Reporte" />
														<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.staticText11}" escape="false"
															id="staticText11" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnExportar_action}"
															binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnExportar}" id="btnExportar"
															styleClass="button" text="Exportar" onClick="return exportarReporte()" />
														<ui:staticText
															binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.paginatedTable.stSeparadorOrdenamiento}"
															id="separador_1" />
														<ui:button action="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnImprimirReporte_action}"
															binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.btnImprimirReporte}"
															onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" id="btnImprimirReporte" styleClass="button"
															text="Visualizar Reporte" />
														<ui:imageHyperlink
															binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.paginatedTable.botonOrdenamiento}" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarPersonaFisica').focus();
					</script>
					<ui:hiddenField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpTasaMenor$ABMDocEspTasaMenor$AdminDocEspTasaMenor.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
