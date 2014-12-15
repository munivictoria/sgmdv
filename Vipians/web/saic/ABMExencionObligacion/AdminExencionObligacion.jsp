<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.page1}" id="page1">
			<ui:html binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.html1}" id="html1">
			<ui:head binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.head1}" id="head1"
				title="Administración de Exenciones de Obligaciones">
				<ui:link binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{saic$ABMExencionObligacion$AdminExencionObligacion.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.lblPersonaSeleccionada}" for="tfPersonaSeleccionada"
																id="lblPersonaSeleccionada" styleClass="label" text="Persona" />
														</td>
														<td>
															<ui:textField binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.tfPersonaSeleccionada}" columns="40"
																disabled="true" id="tfPersonaSeleccionada" styleClass="textField" />
															<ui:button action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionarPersonaFisica_action}"
																binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar" />
															<ui:button action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionarPersonaJuridica_action}"
																binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersonaSeleccionada" title="Limpiar"
																binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnLimpiarPersona}"
																action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.lblObligacion}" for="tfObligacion" id="lblObligacion"
																styleClass="label" text="Obligación" />
														</td>
														<td>
															<ui:textField binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.tfObligacion}" columns="40" disabled="true"
																id="tfObligacion" styleClass="textField" />
															<ui:button action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionarObligacionOSP_action}"
																styleClass="button" text="OSP" binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionarObligacionOSP}"
																id="btnObligacionOSP" />
															<ui:button action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionarObligacionTGI_action}"
																binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionarObligacionTGI}" id="btnObligacionTGI"
																styleClass="button" text="TGI" />
															<ui:button action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionarObligacionPFO_action}"
																binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionarObligacionPFO}" id="btnObligacionPFO"
																styleClass="button" text="PFO" />
															<ui:button action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionarObligacionSHPS_action}"
																binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionarObligacionSHPS}" id="btnObligacionSHPS"
																styleClass="button" text="SHPS" />
															<ui:button action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionarObligacionAutomotor_action}"
																binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionarObligacionAutomotor}"
																id="btnObligacionAutomotor" styleClass="button" text="Automotor" />
															<ui:button action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionarObligacionCementerio_action}"
																binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionarObligacionCementerio}"
																id="btnObligacionCementerio" styleClass="button" text="Cementerio" />
															<a4j:commandButton id="btnLimpiarObligacion" reRender="form1:tfObligacion" title="Limpiar"
																binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnLimpiarObligacion}"
																action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnLimpiarObligacion_action}" styleClass="buttonLimpiarAjax" />
														</td>
													</tr>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.lblAnio}" for="tfAnio" id="lblAnio"
																styleClass="label" text="Año" />
														</td>
														<td>
															<ui:textField binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.tfAnio}" columns="5" maxLength="4" id="tfAnio"
																styleClass="textField" />
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
											<a4j:commandButton binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnBuscar}"
												action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1,form1:stCantidadRegistros" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnReiniciar_action}"
												binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax"
												value="Reiniciar" reRender="form1:pgParametros,form1:table1,form1:stCantidadRegistros" />
											<ui:staticText binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnCancelar_action}"
												binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<!--<h:panelGrid binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.grpCargando}" columns="2" id="grpCargando"
								style="display: none; margin-left: 7px; padding-left: 10px" styleClass="msgLiquidacion">
								<ui:image binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.image1}" id="image1"
									url="/resources/imagenes/abm/wait_medium_1.gif" />
								<ui:staticText binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.staticText1}" escape="false" id="staticText1"
									styleClass="label2" text="     Generando archivos de impresión... Aguarde por favor." />
							</h:panelGrid>
							<script>
								<![CDATA[
                            document.getElementById("form1:grpCargando").style.display = "none";

                            function mostrarProgreso() {
                                tabla = document.getElementById("form1:grpCargando");
                                tabla.style.display = "block";
                            }
                        ]]></script>-->
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.paginatedTable}" styleClass="tablaPaginada" id="table1">
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.gpBotones}" id="gpBotones">
														<ui:button action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionar_action}"
															binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnSeleccionar}" id="btnSeleccionar" styleClass="button"
															text="Seleccionar" />
														<ui:staticText binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.stSeparadorSeleccionar}" escape="false"
															id="staticText6" />
														<ui:button action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnAgregar_action}"
															binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar" />
														<ui:button action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnModificar_action}"
															binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnModificar}" id="btnModificar" styleClass="button"
															text="Modificar" />
														<ui:button action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnTerminar_action}"
															binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnTerminar}" id="btnTerminar" styleClass="button"
															text="Terminar" />
														<ui:staticText binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.stSeparadorAccion}" escape="false"
															id="staticText7" />
														<ui:button action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnConsultar_action}"
															binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnConsultar}" id="btnConsultar" styleClass="button"
															text="Consultar" />
														<ui:staticText binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.staticText10}" escape="false" id="staticText10"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnExportar_action}"
															binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.btnExportar}" id="btnExportar" styleClass="button"
															text="Exportar" onClick="return exportarReporte()" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<ui:label binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.lblEncontrados}" id="lblEncontrados" styleClass="label2"
											text="Registros Encontrados: " />
										<ui:staticText binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.stCantidadRegistros}" id="stCantidadRegistros"
											styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
						document.getElementById('form1:tfNombre').focus();
					</script>
					<ui:hiddenField binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.hidIdPagina}" id="hidIdPagina"
						text="#{saic$ABMExencionObligacion$AdminExencionObligacion.idPagina}" />
					<ui:hiddenField binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ABMExencionObligacion$AdminExencionObligacion.idSubSesion}" />
					<ui:script binding="#{saic$ABMExencionObligacion$AdminExencionObligacion.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
