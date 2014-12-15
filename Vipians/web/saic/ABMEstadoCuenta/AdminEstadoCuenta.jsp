<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" isELIgnored="false"
		deferredSyntaxAllowedAsLiteral="false" />
	<f:view>
		<ui:page binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.page1}" id="page1">
			<ui:html binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.html1}" id="html1">
			<ui:head binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.head1}" id="head1" title="Estado de Cuenta de Contribuyentes">
				<ui:link binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.body1}" focus="form1:btnSeleccionarPersonaFisica" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();"
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.stTitulo}" id="stTitulo" styleClass="tituloABM"
										text="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td align="center">
											<ui:panelGroup binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.pgParametros}" id="pgParametros">
												<table>
													<tr>
														<td align="right" nowrap="nowrap">
															<ui:label binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.label2}" for="tfPersona" id="label2" styleClass="label"
																text="Persona" />
														</td>
														<td>
															<ui:textField binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.tfPersona}" columns="40" disabled="true" id="tfPersona"
																styleClass="textField" />
															<ui:button action="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnSeleccionarPersonaFisica_action}"
																binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnSeleccionarPersonaFisica}" escape="false"
																id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Fisica" />
															<ui:button action="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnSeleccionarPersonaJuridica_action}"
																binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnSeleccionarPersonaJuridica}" escape="false"
																id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Juridica" />
															<a4j:commandButton id="btnLimpiarPersona" reRender="form1:tfPersona" title="Limpiar"
																binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnLimpiarPersona}"
																action="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnLimpiarPersona_action}" styleClass="buttonLimpiarAjax" />
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
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnBuscar}"
												action="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnBuscar_action}" id="btnBuscar" value="Buscar" styleClass="btnAjax"
												reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
											<a4j:commandButton action="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnReiniciar_action}"
												binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar"
												reRender="form1:pgParametros,form1:table1" />
											<ui:staticText binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.staticText2}" escape="false" id="staticText2"
												text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnCancelar_action}"
												binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.messageGroup}" id="messageGroup" showDetail="true"
										showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<h:panelGrid binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.grpCargando}" columns="2" id="grpCargando"
								style="display: none; margin-left: 7px; padding-left: 10px" styleClass="msgLiquidacion">
								<ui:image binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.image1}" id="image1" url="/resources/imagenes/abm/wait_medium_1.gif" />
								<ui:staticText binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.staticText1}" escape="false" id="staticText1" styleClass="label2"
									text="     Generando archivos de impresión... Aguarde por favor." />
							</h:panelGrid>
							<script>
								<![CDATA[
                                    document.getElementById("form1:grpCargando").style.display = "none";

                                    function mostrarProgreso() {
                                        tabla = document.getElementById("form1:grpCargando");
                                        tabla.style.display = "block";
                                    }
                                ]]></script>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.table1}" id="table1" width="128">
											<script>
												<![CDATA[
                                            /* ----- Functions for Table Preferences Panel ----- */
                                            /*
                                             * Toggle the table preferences panel open or closed
                                             */
                                            function togglePreferencesPanel() {
                                                var table = document.getElementById("form1:table1");
                                                table.toggleTblePreferencesPanel();
                                            }
                                            /* ----- Functions for Filter Panel ----- */
                                            /*
                                             * Return true if the filter menu has actually changed,
                                             * so the corresponding event should be allowed to continue.
                                             */
                                            function filterMenuChanged() {
                                                var table = document.getElementById("form1:table1");
                                                return table.filterMenuChanged();
                                            }
                                            /*
                                             * Toggle the custom filter panel (if any) open or closed.
                                             */
                                            function toggleFilterPanel() {
                                                var table = document.getElementById("form1:table1");
                                                return table.toggleTableFilterPanel();
                                            }
                                            /* ----- Functions for Table Actions ----- */
                                            /*
                                             * Initialize all rows of the table when the state
                                             * of selected rows changes.
                                             */
                                            function initAllRows() {
                                                var table = document.getElementById("form1:table1");
                                                table.initAllRows();
                                            }
                                            /*
                                             * Set the selected state for the given row groups
                                             * displayed in the table.  This functionality requires
                                             * the 'selectId' of the tableColumn to be set.
                                             *
                                             * @param rowGroupId HTML element id of the tableRowGroup component
                                             * @param selected Flag indicating whether components should be selected
                                             */
                                            function selectGroupRows(rowGroupId, selected) {
                                                var table = document.getElementById("form1:table1");
                                                table.selectGroupRows(rowGroupId, selected);
                                            }
                                            /*
                                             * Disable all table actions if no rows have been selected.
                                             */
                                            function disableActions() {
                                                // Determine whether any rows are currently selected
                                                var table = document.getElementById("form1:table1");
                                                var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
                                                // Set disabled state for top actions
                                                document.getElementById("form1:table1:tableActionsTop:deleteTop").setDisabled(disabled);
                                                // Set disabled state for bottom actions
                                                document.getElementById("form1:table1:tableActionsBottom:deleteBottom").setDisabled(disabled);
                                            }]]></script>
											<ui:tableRowGroup binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.tableRowGroup1}" emptyDataMsg="Ningún registro encontrado."
												id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)" onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)"
												onDblClick="funcionSeleccionar()" sourceData="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.ldpObligacionEstadoCuenta}"
												sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.radioButton1}" id="radioButton1" label=""
														onClick="checkUncheck(this)" name="buttonGroup" selected="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.RBSelected}"
														selectedValue="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.tableColumn2}" headerText="Nº Trámite" id="tableColumn2"
													sort="numeroTramite">
													<ui:staticText binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.staticText3}" id="staticText3"
														text="#{currentRow.value['numeroTramite']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.tableColumn3}" headerText="Obligación" id="tableColumn3"
													sort="obligacion">
													<ui:staticText binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.staticText4}" id="staticText4"
														text="#{currentRow.value['obligacion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.tableColumn4}" headerText="Estado" id="tableColumn4"
													sort="estado">
													<ui:staticText binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.staticText5}" id="staticText5"
														text="#{currentRow.value['estado']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.tableColumn5}" headerText="Total" id="tableColumn5"
													sort="totalAdeudado">
													<ui:staticText binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.staticText7}"
														converter="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.numberConverter1}" id="staticText7"
														text="#{currentRow.value['totalAdeudado']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.gpBotones}" id="gpBotones">
														<ui:button action="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnSeleccionar_action}"
															binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar" />
														<ui:staticText binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.staticText6}" escape="false" id="staticText6"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnConsultar_action}"
															binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar" />
														<ui:button action="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnImprimir_action}"
															binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnImprimir}" disabled="true" id="btnImprimir"
															onClick="ok = confirm(&quot;Se IMPRIMIRÁN las Liquidaciones listadas.\n\n¿Está seguro?&quot;);&#xa;if (ok) mostrarProgreso();&#xa;return ok;"
															styleClass="button" text="Imprimir" />
														<ui:staticText binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.staticText9}" escape="false" id="staticText9"
															text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnExportar_action}"
															binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.btnExportar}" id="btnExportar" styleClass="button" text="Exportar"
															onClick="return exportarReporte()" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="2">
										<ui:label binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.lblTotalAdeudado}" id="lblTotalAdeudado" styleClass="label"
											text="Total Adeudado:" />
										<ui:staticText binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.stTotalAdeudado}"
											converter="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.numberConverter1}" id="stTotalAdeudado" styleClass="staticText" />
									</td>
								</tr>
							</table>
						</div>
					</div>
					<script>
            document.getElementById('form1:btnSeleccionarPersonaFisica').focus();
                        </script>
					<ui:hiddenField binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.hidIdPagina}" id="hidIdPagina"
						text="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.idPagina}" />
					<ui:hiddenField binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.idSubSesion}" />
					<ui:script binding="#{saic$ABMEstadoCuenta$AdminEstadoCuenta.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
