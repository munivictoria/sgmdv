<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false"
		deferredSyntaxAllowedAsLiteral="false" />
	<f:view>
		<ui:page binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.page1}" id="page1">
			<ui:html binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.html1}" id="html1">
			<ui:head binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.head1}" id="head1">
				<ui:link binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.link1}" id="link1"
					url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					var nombreBean = "saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor";

					function cargarComportamientoJQuery() {
						autoCompletarEnTextField("#form1:tfPersona", "persona", nombreBean, "setPersonaAutocompletar");
						autoCompletarEnTextField("#form1:tfParcela", "parcela", nombreBean, "setParcelaAutocompletar");
					}

					function focusearTfPersonaSeleccionada() {
						$("#form1\\:tfPersona").focus();
					}

					function focusearTfParcelaSeleccionada() {
						$("#form1\\:tfParcela").focus();
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.body1}" focus="form1:btnSeleccionarPersonaFisica"
				id="body1" onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="4"></td>
									</tr>
									<tr>
										<td align="left" style="padding-left: 40px">
											<ui:panelGroup binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.label2}" for="tfPersona" id="label2"
																styleClass="label" text="Persona" />
														</td>
														<td colspan="4">
															<ui:textField binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.tfPersona}" columns="40"
																id="tfPersona"
																styleClass="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.paginatedTable.filtro.persona != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.paginatedTable.filtro.persona != null}" />
															<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnSeleccionarPersonaFisica_action}"
																binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
															<ui:button
																action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnSeleccionarPersonaJuridica_action}"
																binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnSeleccionarPersonaJuridica}"
																escape="false" id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ"
																toolTip="Seleccionar Persona Juridica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
																binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnLimpiarPersona}"
																action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnLimpiarPersona_action}"
																styleClass="buttonLimpiarAjax" oncomplete="cargarComportamientoJQuery(); focusearTfPersonaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.label1}" for="tfParcela" id="label1"
																styleClass="label" text="Parcela" />
														</td>
														<td align="left" colspan="4" nowrap="nowrap">
															<ui:textField binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.tfParcela}" columns="50"
																id="tfParcela"
																styleClass="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.paginatedTable.filtro.parcela != null ? 'textFieldDisabled' : 'textField'}"
																disabled="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.paginatedTable.filtro.parcela != null}" />
															<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnSeleccionarParcela_action}"
																binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnSeleccionarParcela}" escape="false"
																id="btnSeleccionarParcela" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarParcela" reRender="form1:tfParcela" title="Limpiar"
																binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnLimpiarParcela}"
																action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnLimpiarParcela_action}"
																styleClass="buttonLimpiarAjax" oncomplete="cargarComportamientoJQuery(); focusearTfParcelaSeleccionada();" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.label9}" for="tfTipoObligacionTasa"
																id="label9" styleClass="label" text="Tipo Obligación Tasa Menor" />
														</td>
														<td>
															<ui:dropDown binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.ddTipoObligacionTasa}"
																id="ddTipoObligacionTasa"
																items="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.ddTipoObligacionTasaOptions.options}"
																styleClass="textField"
																valueChangeListener="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.eventoSeleccionTipoObligacion(evento)}">
																<a4j:support event="onChange" reRender="form1:ddAnios, form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
															</ui:dropDown>
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<hr />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.label3}" id="label3"
																styleClass="label2" text="Período" />
														</td>
													</tr>
													<tr>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.lblAnio}" for="ddAnios" id="lblAnio"
																styleClass="label" text="Año" />
															<ui:dropDown binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.ddAnios}" id="ddAnios"
																styleClass="textField" items="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.ddAniosOptions.options}"
																valueChangeListener="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.eventoSeleccionAnio(evento)}">
																<a4j:support event="onChange" reRender="form1:ddCalendarios, form1:ddPeriodos, form1:ddCuotas" />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.lblCalendarios}" for="ddCalendarios"
																id="lblCalendarios" styleClass="label" text="Calendario" />
															<ui:dropDown binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.ddCalendarios}"
																id="ddCalendarios" styleClass="textField"
																items="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.ddCalendariosOptions.options}"
																valueChangeListener="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.eventoSeleccionCalendario(event)}">
																<a4j:support event="onChange" reRender="form1:ddPeriodos, form1:ddCuotas" />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.lblPeriodos}" for="ddPeriodos"
																id="lblPeriodos" styleClass="label" text="Periodo" />
															<ui:dropDown binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.ddPeriodos}" id="ddPeriodos"
																styleClass="textField"
																items="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.ddPeriodosOptions.options}"
																valueChangeListener="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.eventoSeleccionPeriodo(event)}">
																<a4j:support event="onChange" reRender="form1:ddCuotas " />
															</ui:dropDown>
														</td>
														<td align="center" nowrap="nowrap">
															<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.lblCuotas}" for="ddCuotas"
																id="lblCuotas" styleClass="label" text="Cuota" />
															<ui:dropDown binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.ddCuotas}" id="ddCuotas"
																styleClass="textField"
																items="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.ddCuotasOptions.options}" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
															<hr />
															<br />
														</td>
													</tr>
												</table>
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.lblEstado}" id="lblEstado"
																styleClass="label" text="Estado" />
															<ui:dropDown binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.ddEstado}" id="ddEstado"
																styleClass="textField"
																items="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.ddEstadoDefaultOptions.options}" />
														</td>
														<td nowrap="nowrap">
															<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.lblTipo}" id="lblTipo"
																styleClass="label" text="Tipo" />
															<ui:dropDown binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.ddTipo}" id="ddTipo"
																styleClass="textField"
																items="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.ddTipoDefaultOptions.options}" />
														</td>
														<td nowrap="nowrap">
															<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.lblOmitir}" id="lblOmitir"
																styleClass="label" text="Omitir monto en cero" />
															<ui:checkbox binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.chkOmitir}" id="chkOmitir"
																selected="false" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
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
											<a4j:commandButton binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnBuscar}"
												action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnReiniciar_action}"
												binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnReiniciar}" id="btnReiniciar"
												styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros"
												oncomplete="cargarComportamientoJQuery();" />
											<ui:staticText binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.staticText2}" escape="false"
												id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnCancelar_action}"
												binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnCancelar}" id="btnCancelar"
												styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<h:panelGrid binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.grpCargando}" columns="2"
								id="grpCargando" style="display: none; margin-left: 7px; padding-left: 10px" styleClass="msgLiquidacion">
								<ui:image binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.image1}" id="image1"
									url="/resources/imagenes/abm/wait_medium_1.gif" />
								<ui:staticText binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.staticText4}" escape="false"
									id="staticText4" styleClass="label2" text="     Generando archivos de impresión... Aguarde por favor." />
							</h:panelGrid>
							<script>
								<![CDATA[
								document.getElementById("form1:grpCargando").style.display = "none";

								function mostrarProgreso() {
									tabla = document.getElementById("form1:grpCargando");
									tabla.style.display = "block";
								}
								]]>
							</script>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.paginatedTable}" id="table1"
											styleClass="tablaPaginada">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.gpBotones}" id="gpBotones">
														<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnSeleccionar_action}"
															binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnSeleccionar}" id="btnSeleccionar"
															styleClass="button" text="Seleccionar" />
														<ui:staticText binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.stSeparadorSeleccionar}"
															escape="false" id="staticText6" />
														<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnSeleccionarTodas_action}"
															binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnSeleccionarTodas}" id="btnSeleccionarTodas"
															styleClass="button" text="Todas" toolTip="Selecciona todas" />
														<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnDeseleccionarTodas_action}"
															binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnDeseleccionarTodas}"
															id="btnDeseleccionarTodas" styleClass="button" text="Ninguna" toolTip="Deselecciona todas" />
														<ui:staticText escape="false" id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnGenerarLiquidaciones_action}"
															binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnGenerarLiquidaciones}"
															id="btnGenerarLiquidaciones" styleClass="button" text="Generar Liquidaciones" />
														<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnConsultar_action}"
															binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnConsultar}" id="btnConsultar"
															styleClass="button" text="Consultar" />
														<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnImprimir_action}"
															binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnImprimir}" id="btnImprimir"
															onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" styleClass="button" text="Imprimir" />
														<ui:staticText binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.staticText16}" escape="false"
															id="staticText16" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnReliquidar_action}"
															binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnReliquidar}" id="btnReliquidar"
															styleClass="button" text="Reliquidar" />
														<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnActualizarDeuda_action}"
															binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnActualizarDeuda}" id="btnActualizarDeuda"
															styleClass="button" text="Actualizar Deuda" />
														<ui:staticText escape="false" id="stSeparador3" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnExportar_action}"
															binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnExportar}" id="btnExportar"
															styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.lblEncontrados}" id="lblEncontrados"
											styleClass="label2" text="Registros Encontrados: " />
										<ui:staticText binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.stCantidadRegistros}"
											id="stCantidadRegistros" styleClass="staticText" />
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<div class="notifABM clrNota">
											<b>Nota: </b>La columna <i>Monto</i> es el resultado del importe de la tasa más el valor de los modificadores correspondientes.
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<ui:panelGroup id="pgPanelAdminLiquidaciones"
											rendered="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.renderPanelAdminLiquidaciones}">
											<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnMarcarPagada_action}"
												binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnMarcarPagada}" id="btnMarcarPagada"
												styleClass="button" text="Marcar Pagada" />
											<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnMarcarImpaga_action}"
												binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnMarcarImpaga}" id="btnMarcarImpaga"
												styleClass="button" text="Marcar Impaga" />
											<ui:staticText escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnModificarLiquidacion_action}"
												binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnModificarLiquidacion}"
												id="btnModificarLiquidacion" styleClass="button" text="Modificar" />
											<ui:staticText escape="false" id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnEliminarLiquidacion_action}"
												binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnEliminarLiquidacion}"
												id="btnEliminarLiquidacion" styleClass="button" text="Eliminar" />
											<ui:staticText escape="false" id="stSeparadorBotonImprimirServidor" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<a4j:commandButton binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnImprimirEnServidor}"
												action="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.btnImprimirEnServidor_action}"
												id="btnImprimirEnServidor" value="Imprimir" styleClass="btnAjax" oncomplete="changeStyleAlIngresar()" />
										</ui:panelGroup>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:btnSeleccionarPersonaFisica').focus();
					</script>
					<ui:hiddenField binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.hidIdPagina}" id="hidIdPagina"
						text="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.idPagina}" />
					<ui:hiddenField binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.idSubSesion}" />
					<ui:script binding="#{saic$grpTasaMenor$ABMLiquidacionTasaMenor$AdminLiquidacionTasaMenor.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
