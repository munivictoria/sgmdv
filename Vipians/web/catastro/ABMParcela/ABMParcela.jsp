<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMParcela$ABMParcela.page1}" id="page1">
			<ui:html binding="#{catastro$ABMParcela$ABMParcela.html1}" id="html1">
			<ui:head binding="#{catastro$ABMParcela$ABMParcela.head1}" id="head1">
				<ui:link binding="#{catastro$ABMParcela$ABMParcela.link1}" id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
						function cargarComportamientoJQuery() {
							calendarioEnTextField("#form1:tabSet1:one:tfFechaAlta");
							calendarioEnTextField("#form1:tabSet1:three:tfFechaInscripcion");
							calendarioEnTextField("#form1:tabSet1:four:tfFechaPlanoMensura");
							calendarioEnTextField("#form1:tabSet1:five:tfFechaPlanoConstruccion");
						}

						$(document).ready(function() {
							cargarComportamientoJQuery();
						});
					]]>
            	</script>
			</ui:head>
			<ui:body binding="#{catastro$ABMParcela$ABMParcela.body1}" id="body1" onLoad="parent.footer.location.reload(); Init();"
				onKeyUp="eventoByEscape(event,'btnCancelar')" focus="form1:tabSet1:one:tfNumeroParcela">
				<ui:form binding="#{catastro$ABMParcela$ABMParcela.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{catastro$ABMParcela$ABMParcela.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="4">
										<br />
									</td>
								</tr>
								<tr>
									<td align="left" colspan="4">
										<ui:tabSet binding="#{catastro$ABMParcela$ABMParcela.tabSet1}" id="tabSet1" mini="true" lite="true">
											<ui:tab id="one" binding="#{catastro$ABMParcela$ABMParcela.tabOne}" text="Información Básica">
												<table>
													<tr>
														<td colspan="4">
															<br />
															<ui:panelGroup binding="#{catastro$ABMParcela$ABMParcela.groupPanelPropietarios}" block="true"
																style="border: 1px solid #888888; padding: 6px; padding-left: 12px; background-color: #EEEEEE">
																<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.stPropietariosTemp}" escape="false" id="stPropietariosTemp" />
															</ui:panelGroup>
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<table class="verde" style="border-width: 0px; padding: 5px 1px; width: 100%;">
																<tr>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label5}" id="label5" styleClass="label" text="Nº de Parcela"
																			for="tfNumeroParcela" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label32}" id="label32" styleClass="label" text="Nº de Partida"
																			for="tfNroPartida" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label33}" id="label33" styleClass="label" text="Nº de Registro"
																			for="tfNroRegistro" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label34}" id="label34" styleClass="label" text="Nº de Matrícula"
																			for="tfNroMatricula" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label36}" id="label36" styleClass="label" text="Nº de Cuenta"
																			for="tfNroCuenta" />
																	</td>
																</tr>
																<tr>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfNumeroParcela}" maxLength="10" id="tfNumeroParcela"
																			styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfNroPartida}" maxLength="10" id="tfNroPartida"
																			styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfNroRegistro}" maxLength="10" id="tfNroRegistro"
																			styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfNroMatricula}" maxLength="10" id="tfNroMatricula"
																			styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfNroCuenta}" maxLength="10" id="tfNroCuenta"
																			styleClass="textField" />
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td >
														<table>
														<tr>
														<td>
															<ui:label binding="#{catastro$ABMParcela$ABMParcela.lblEdificacion}" id="lblEdificacion"
																for="ddClasificacionSegunPlantaUrbana" styleClass="label2" text="Edificación" />
															<ui:dropDown binding="#{catastro$ABMParcela$ABMParcela.ddClasificacionSegunPlantaUrbana}"
																id="ddClasificacionSegunPlantaUrbana"
																items="#{catastro$ABMParcela$ABMParcela.ddClasificacionSegunPlantaUrbanaDefaultOptions.options}" styleClass="textField" />
															</td><td>
															<ui:label binding="#{catastro$ABMParcela$ABMParcela.label48}" id="label48" styleClass="label2" text="Tipo Parcela" />
															<ui:dropDown binding="#{catastro$ABMParcela$ABMParcela.ddTipoParcela}" disabled="false" id="ddTipoParcela"
																	items="#{catastro$ABMParcela$ABMParcela.ddTipoParcelaDefaultOptions.options}" styleClass="textField" />
																	</td>
																	</tr>
															</table>		
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<table class="verde" style="border-width: 0px; padding: 5px 1px; width: 100%;">
																<tr>
																	<td colspan="1" style="padding-left: 1px; padding-right: 1px;" valign="top">
																		<div class="div" style="width: 225px; height: 15px;">Domicilio</div>
																		<table class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 192px; padding: 10px 5px;">
																			<tbody>
																				<tr>
																					<td nowrap="nowrap">
																						<ui:label binding="#{catastro$ABMParcela$ABMParcela.label13}" id="label13" styleClass="label"
																							text="Domicilio Parcelario" />
																						<ui:button action="#{catastro$ABMParcela$ABMParcela.btnSeleccionarDomicilio_action}"
																							binding="#{catastro$ABMParcela$ABMParcela.btnSeleccionarDomicilio}" escape="false" id="btnSeleccionarDomicilio"
																							mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Agregar/Modificar" />
																					</td>
																				</tr>
																				<tr>
																					<td nowrap="nowrap">
																						<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.stDomicilio}" escape="false" id="stDomicilio"
																							styleClass="staticText " />
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<ui:label binding="#{catastro$ABMParcela$ABMParcela.label3}" for="tfSuperficie" id="label3" styleClass="label"
																							text="Superficie" />
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfSuperficie}" columns="10" id="tfSuperficie"
																							styleClass="textField" />
																						<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticText19}" escape="false" id="staticText19"
																							styleClass="label" text=" [m&lt;sup&gt;2&lt;/sup&gt;]" />
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<ui:label binding="#{catastro$ABMParcela$ABMParcela.label1}" for="tfFechaAlta" id="label1" styleClass="label"
																							text="Fecha Alta" />
																					</td>
																				</tr>
																				<tr>
																					<td nowrap="nowrap">
																						<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfFechaAlta}" id="tfFechaAlta" styleClass="textField"
																							columns="10" />
																					</td>
																				</tr>
																			</tbody>
																		</table>
																	</td>
																	<td colspan="1" style="padding-left: 1px; padding-right: 1px;" valign="top">
																		<div class="div" style="width: 184px; height: 15px;">Avalúos</div>
																		<table class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 192px; padding: 10px 5px;">
																			<tbody>
																				<tr>
																					<td nowrap="nowrap">
																						<ui:label binding="#{catastro$ABMParcela$ABMParcela.label25}" for="tfAvaluoTerreno" id="label25" styleClass="label"
																							text="Avalúo del Terreno" />
																					</td>
																				</tr>
																				<tr>
																					<td nowrap="nowrap">
																						<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfAvaluoTerreno}" columns="15" id="tfAvaluoTerreno"
																							styleClass="textField" />
																					</td>
																				</tr>
																				<tr>
																					<td nowrap="nowrap">
																						<ui:label binding="#{catastro$ABMParcela$ABMParcela.label26}" for="tfAvaluoMejoras" id="label26" styleClass="label"
																							text="Avalúo de Mejoras" />
																					</td>
																				</tr>
																				<tr>
																					<td nowrap="nowrap">
																						<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfAvaluoMejoras}" columns="15" id="tfAvaluoMejoras"
																							styleClass="textField" />
																					</td>
																				</tr>
																				<tr>
																					<td nowrap="nowrap">
																						<ui:label binding="#{catastro$ABMParcela$ABMParcela.lblRegistroMejora}" for="tfRegistroMejora" id="lblRegistroMejora"
																							styleClass="label" text="Metros Mejoras" />
																					</td>
																				</tr>
																				<tr>
																					<td nowrap="nowrap">
																						<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfRegistroMejora}" columns="15" id="tfRegistroMejora"
																							styleClass="textField" />
																					</td>
																				</tr>
																			</tbody>
																		</table>
																	</td>
																	<td colspan="2" style="padding-left: 1px; padding-right: 1px;" valign="top">
																		<div class="div">Metros de Frente por Cuadra</div>
																		<table class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px;">
																			<tbody>
																				<tr>
																					<td>
																						<ui:table augmentTitle="false" binding="#{catastro$ABMParcela$ABMParcela.table1}" id="table1" style="width: 100px;">
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
																							<ui:tableRowGroup binding="#{catastro$ABMParcela$ABMParcela.tableRowGroup1}" id="tableRowGroup1"
																								sourceData="#{catastro$ABMParcela$ABMParcela.ldpParcelaCuadras}" sourceVar="currentRow">
																								<ui:tableColumn align="center" binding="#{catastro$ABMParcela$ABMParcela.tableColumn1}" id="tableColumn1"
																									rendered="false" valign="middle" width="10">
																									<ui:radioButton binding="#{catastro$ABMParcela$ABMParcela.radioButton1}" id="radioButton1" label=""
																										name="buttonGroup" selected="#{catastro$ABMParcela$ABMParcela.RBSelected}"
																										selectedValue="#{catastro$ABMParcela$ABMParcela.currentRow}" />
																								</ui:tableColumn>
																								<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumn2}" headerText="Cuadra" id="tableColumn2"
																									sort="cuadra" width="20">
																									<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticText2}" id="staticText2"
																										text="#{currentRow.value['cuadra']}" />
																								</ui:tableColumn>
																								<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumn3}" headerText="Metros de Frente"
																									id="tableColumn3" sort="metrosPorCuadra" width="10">
																									<ui:textField binding="#{catastro$ABMParcela$ABMParcela.textField1}" id="textField1" styleClass="textField"
																										onKeyPress="return ValidarFloat(event,this)" text="#{currentRow.value['metrosPorCuadra']}" />
																								</ui:tableColumn>
																							</ui:tableRowGroup>
																							<f:facet name="actionsTop">
																								<ui:panelGroup binding="#{catastro$ABMParcela$ABMParcela.groupPanel1}" id="groupPanel1">
																									<ui:label binding="#{catastro$ABMParcela$ABMParcela.label14}" for="tfManzanaCuadra" id="label14" styleClass="label"
																										text="Manzana" />
																									<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticText18}" escape="false" id="staticTex18"
																										text="&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;" />
																									<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfManzanaCuadra}" columns="30" disabled="true"
																										id="tfManzanaCuadra" styleClass="textField" />
																									<ui:button action="#{catastro$ABMParcela$ABMParcela.btnSeleccionarManzana_action}"
																										binding="#{catastro$ABMParcela$ABMParcela.btnSeleccionarManzana}" escape="false" id="btnSeleccionarManzana"
																										mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
																									<a4j:commandButton id="btnLimpiarManzanaCuadra" reRender="table1" title="Limpiar"
																										binding="#{catastro$ABMParcela$ABMParcela.btnLimpiarManzanaCuadra}"
																										action="#{catastro$ABMParcela$ABMParcela.btnLimpiarManzanaCuadra_action}" styleClass="buttonLimpiarAjax" />
																								</ui:panelGroup>
																							</f:facet>
																						</ui:table>
																					</td>
																				</tr>
																			</tbody>
																		</table>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td colspan="2" style="padding-left: 1px; padding-right: 1px;" valign="top">
															<div class="div" style="width: 99%; height: 15px;">Zonas</div>
															<ui:table id="table6" width="100%" style="-moz-border-radius: 0px 0px 5px 5px; padding: 10px 5px;">
																<ui:tableRowGroup id="tableRowGroup6" binding="#{catastro$ABMParcela$ABMParcela.tableRowGroup6}"
																	sourceData="#{catastro$ABMParcela$ABMParcela.ldpZonas}" sourceVar="currentRowZona">
																	<ui:tableColumn align="center" id="tableColumn21" binding="#{catastro$ABMParcela$ABMParcela.tableColumn21}" valign="middle"
																		width="10">
																		<ui:radioButton id="radioButton6" binding="#{catastro$ABMParcela$ABMParcela.radioButton6}" label="" name="buttonGroup"
																			selected="#{catastro$ABMParcela$ABMParcela.RBSelected6}" selectedValue="#{catastro$ABMParcela$ABMParcela.currentRow6}" />
																	</ui:tableColumn>
																	<ui:tableColumn id="tableColumn22" headerText="Nombre" sort="zona"
																		binding="#{catastro$ABMParcela$ABMParcela.tableColumn22}">
																		<ui:staticText id="staticText26" binding="#{catastro$ABMParcela$ABMParcela.staticText26}"
																			text="#{currentRowZona.value['zona']}" />
																	</ui:tableColumn>
																	<ui:tableColumn id="tableColumn23" headerText="Zonificación" sort="#{currentRowZona.value.zona.zonificacion.nombre}"
																		binding="#{catastro$ABMParcela$ABMParcela.tableColumn23}">
																		<ui:staticText id="staticText27" binding="#{catastro$ABMParcela$ABMParcela.staticText27}"
																			text="#{currentRowZona.value.zona.zonificacion.nombre}" />
																	</ui:tableColumn>
																</ui:tableRowGroup>
																<f:facet name="actionsTop">
																	<ui:panelGroup id="groupPanel6" binding="#{catastro$ABMParcela$ABMParcela.groupPanel6}">
																		<ui:button id="btnAgregarZona" styleClass="button" text="Agregar" toolTip="Agregar Zona"
																			binding="#{catastro$ABMParcela$ABMParcela.btnAgregarZona}"
																			action="#{catastro$ABMParcela$ABMParcela.btnAgregarZona_action}" />
																		<a4j:commandButton id="btnQuitarZona" value="Quitar" styleClass="btnAjax" reRender="table6"
																			onmousedown="reemplazarClickConConfirmacion(this, '');" binding="#{catastro$ABMParcela$ABMParcela.btnQuitarZona}"
																			action="#{catastro$ABMParcela$ABMParcela.btnQuitarZona_action}" />
																	</ui:panelGroup>
																</f:facet>
															</ui:table>
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:panelGroup binding="#{catastro$ABMParcela$ABMParcela.groupPanelZonas}" block="true"
																style="border: 1px solid #888888; padding: 6px; padding-left: 12px; background-color: #EEEEEE">
																<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.stZonas}" escape="false" id="stZonas" />
															</ui:panelGroup>
														</td>
													</tr>
													<tr>
														<td colspan="4" style="padding-left: 1px; padding-right: 1px;">
															<ui:panelGroup binding="#{catastro$ABMParcela$ABMParcela.groupPanelObligaciones}" id="groupPanelObligaciones">
																<div class="div" style="width: 780px; height: 15px;">Obligaciones</div>
																<table class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 192px;">
																	<tbody>
																		<tr>
																			<td>
																				<ui:table augmentTitle="false" binding="#{catastro$ABMParcela$ABMParcela.tableObligaciones}" id="tableObligaciones"
																					width="108">
																					<script>
																						<![CDATA[
											                                        /* ----- Functions for Table Preferences Panel ----- */
											                                        /*
											                                         * Toggle the table preferences panel open or closed
											                                         */
											                                        function togglePreferencesPanel() {
											                                            var table = document.getElementById("form1:tableObligaciones");
											                                            table.toggleTblePreferencesPanel();
											                                        }
											                                        /* ----- Functions for Filter Panel ----- */
											                                        /*
											                                         * Return true if the filter menu has actually changed,
											                                         * so the corresponding event should be allowed to continue.
											                                         */
											                                        function filterMenuChanged() {
											                                            var table = document.getElementById("form1:tableObligaciones");
											                                            return table.filterMenuChanged();
											                                        }
											                                        /*
											                                         * Toggle the custom filter panel (if any) open or closed.
											                                         */
											                                        function toggleFilterPanel() {
											                                            var table = document.getElementById("form1:tableObligaciones");
											                                            return table.toggleTableFilterPanel();
											                                        }
											                                        /* ----- Functions for Table Actions ----- */
											                                        /*
											                                         * Initialize all rows of the table when the state
											                                         * of selected rows changes.
											                                         */
											                                        function initAllRows() {
											                                            var table = document.getElementById("form1:tableObligaciones");
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
											                                            var table = document.getElementById("form1:tableObligaciones");
											                                            table.selectGroupRows(rowGroupId, selected);
											                                        }
											                                        /*
											                                         * Disable all table actions if no rows have been selected.
											                                         */
											                                        function disableActions() {
											                                            // Determine whether any rows are currently selected
											                                            var table = document.getElementById("form1:tableObligaciones");
											                                            var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
											                                        	// Set disabled state for top actions
																						document.getElementById("form1:tableObligaciones:tableActionsTop:deleteTop").setDisabled(disabled);
																						// Set disabled state for bottom actions
																						document.getElementById("form1:tableObligaciones:tableActionsBottom:deleteBottom").setDisabled(disabled);
											                                        }]]></script>
																					<ui:tableRowGroup binding="#{catastro$ABMParcela$ABMParcela.tableRowObligacion}" id="tableRowObligacion"
																						emptyDataMsg="Ningún registro encontrado." sourceData="#{catastro$ABMParcela$ABMParcela.ldpObligaciones}"
																						sourceVar="currentRow">
																						<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumnObligacion1}"
																							headerText="Documento Especializado" id="tableColumnObligacion1">
																							<ui:textArea styleClass="textFieldDisabled" readOnly="true"
																								id="taDocumentoObligacion" text="#{currentRow.value['documentoEspecializado']}" >
																								<f:facet name="readOnly">
																									<ui:textArea id="taAuxDocumentoObligacion" disabled="true"></ui:textArea>
																								</f:facet>
																							</ui:textArea>
																						</ui:tableColumn>
																						<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumnObligacion2}" headerText="Persona"
																							id="tableColumnObligacion2">
																							<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticTextObligacion2}" id="staticTextObligacion2"
																								text="#{currentRow.value['persona']}" />
																						</ui:tableColumn>
																						<ui:tableColumn headerText="Servicios" id="tcServicios">
																							<ui:textArea disabled="true" styleClass="textFieldDisabled" id="taServicios" text="#{currentRow.value['servicios']}" />
																						</ui:tableColumn>
																						<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumnObligacion3}" headerText="Estado"
																							id="tableColumnObligacion3">
																							<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticTextObligacion3}" id="staticTextObligacion3"
																								text="#{currentRow.value['estado']}" />
																						</ui:tableColumn>
																					</ui:tableRowGroup>
																				</ui:table>
																			</td>
																		</tr>
																	</tbody>
																</table>
															</ui:panelGroup>
														</td>
													</tr>
												</table>
											</ui:tab>
											<ui:tab id="two" binding="#{catastro$ABMParcela$ABMParcela.tabTwo}" text="Más información">
												<table>
													<tr>
														<td colspan="4">
															<ui:label binding="#{catastro$ABMParcela$ABMParcela.label20bis}" id="label20bis" styleClass="label2"
																text="Nomenclatura Catastral" />
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<table class="verde" style="border-width: 0px; padding: 5px 1px; width: 100%;">
																<tr>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label21}" id="label21" styleClass="label" text="Dpto." for="tfDpto" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label22}" id="label22" styleClass="label" text="Pedania"
																			for="tfPedania" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label23}" id="label23" styleClass="label" text="Circun."
																			for="tfCircunscripcion" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label24}" id="label24" styleClass="label" text="Distrito"
																			for="tfDistrito" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label27}" id="label27" styleClass="label" text="Sub-Dis."
																			for="tfSubDistrito" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label28}" id="label28" styleClass="label" text="Sección/Poligono"
																			for="tfSeccionPoligono" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label30}" id="label30" styleClass="label" text="Quinta" for="tfQuinta" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label31}" id="label31" styleClass="label" text="Chacra o Fracción"
																			for="tfChacraFraccion" />
																	</td>
																	<td>
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label35}" id="label35" styleClass="label" text="Lote" for="tfLote" />
																	</td>
																</tr>
																<tr>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfDpto}" maxLength="6" columns="6" id="tfDpto"
																			styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfPedania}" maxLength="6" columns="6" id="tfPedania"
																			styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfCircunscripcion}" maxLength="6" columns="6"
																			id="tfCircunscripcion" styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfDistrito}" maxLength="6" columns="6" id="tfDistrito"
																			styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfSubDistrito}" maxLength="6" columns="6" id="tfSubDistrito"
																			styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfSeccionPoligono}" maxLength="6" columns="6"
																			id="tfSeccionPoligono" styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfQuinta}" maxLength="6" columns="6" id="tfQuinta"
																			styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfChacraFraccion}" maxLength="6" columns="6" id="tfChacraFraccion"
																			styleClass="textField" />
																	</td>
																	<td>
																		<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfLote}" maxLength="6" columns="6" id="tfLote"
																			styleClass="textField" />
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<table class="verde" style="border-width: 0px; padding: 5px 1px; width: 100%;">
																<tr>
																	<td rowspan="2" colspan="1" style="padding-left: 1px; padding-right: 1px;">
																		<div class="div" style="width: 388px; height: 15px;">Clasificación Catastral según su Planta</div>
																		<table class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 398px;">
																			<tbody>
																				<tr>
																					<td colspan="4"
																						style="padding-left: 1px; padding-right: 1px; border-color: rgb(178, 178, 178); border-style: none none none; border-width: 1px;">
																						<div>
																							<ui:radioButtonGroup styleClass="optgroup" binding="#{catastro$ABMParcela$ABMParcela.rbgClasificacionSegunPlanta}"
																								items="#{catastro$ABMParcela$ABMParcela.rbgClasificacionSegunPlantaDefaultOptions.options}"
																								id="rbgClasificacionSegunPlanta" columns="4" labelLevel="2">
																							</ui:radioButtonGroup>
																						</div>
																					</td>
																				</tr>
																				<tr>
																					<td style="padding: 0px;">
																						<div class="tablaInterna" align="center" style="-moz-border-radius-bottomright: 5px;">
																							<ui:checkbox binding="#{catastro$ABMParcela$ABMParcela.chbClasificacionRegadio}" id="chbClasificacionRegadio"
																								label="Regadio">
																							</ui:checkbox>
																							<ui:checkbox binding="#{catastro$ABMParcela$ABMParcela.chbClasificacionSecano}" id="chbClasificacionSecano"
																								label="Secano">
																							</ui:checkbox>
																						</div>
																					</td>
																				</tr>
																			</tbody>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td colspan="1">
																		<div class="div" style="width: 245px; height: 15px;">Afectaciones y Restricciones</div>
																		<table class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 192px; padding: 10px 5px;">
																			<tbody>
																				<tr>
																					<td>
																						<ui:label binding="#{catastro$ABMParcela$ABMParcela.label38}" id="label38" styleClass="label"
																							text="Afectaciones Explícitas" />
																						<ui:dropDown binding="#{catastro$ABMParcela$ABMParcela.ddAfectacionesExplicitas}" disabled="false"
																							id="ddAfectacionesExplicitas"
																							items="#{catastro$ABMParcela$ABMParcela.ddAfectacionesExplicitasDefaultOptions.options}" styleClass="textField" />
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<ui:label binding="#{catastro$ABMParcela$ABMParcela.label39}" id="label39" styleClass="label"
																							text="Afectaciones No Explícitas" />
																						<ui:dropDown binding="#{catastro$ABMParcela$ABMParcela.ddAfectacionesNoExplicitas}" disabled="false"
																							id="ddAfectacionesNoExplicitas"
																							items="#{catastro$ABMParcela$ABMParcela.ddAfectacionesNoExplicitasDefaultOptions.options}" styleClass="textField" />
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<ui:label binding="#{catastro$ABMParcela$ABMParcela.label40}" id="label40" styleClass="label"
																							text="Restricciones Al Dominio Explícitas" />
																						<ui:dropDown binding="#{catastro$ABMParcela$ABMParcela.ddRestriccionesAlDominioExplicitas}" disabled="false"
																							id="ddRestriccionesAlDominioExplicitas"
																							items="#{catastro$ABMParcela$ABMParcela.ddRestriccionesAlDominioExplicitasDefaultOptions.options}"
																							styleClass="textField" />
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<ui:label binding="#{catastro$ABMParcela$ABMParcela.label41}" id="label41" styleClass="label"
																							text="Restricciones Al Dominio No Explícitas" />
																						<ui:dropDown binding="#{catastro$ABMParcela$ABMParcela.ddRestriccionesAlDominioNoExplicitas}" disabled="false"
																							id="ddRestriccionesAlDominioNoExplicitas"
																							items="#{catastro$ABMParcela$ABMParcela.ddRestriccionesAlDominioNoExplicitasDefaultOptions.options}"
																							styleClass="textField" />
																					</td>
																				</tr>
																			</tbody>
																		</table>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<ui:label binding="#{catastro$ABMParcela$ABMParcela.label37}" id="label37" styleClass="label2"
																text="Clasificación Catastral según su uso" />
														</td>
													</tr>
													<tr>
														<td colspan="4" style="padding-left: 1px; padding-right: 1px;">
															<table align="center" class="verde" style="border-width: 0px; padding: 5px 1px; width: 99%;">
																<tr>
																	<td align="right" nowrap="nowrap">
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label43}" id="label43" styleClass="label" text="Rural" />
																	</td>
																	<td>
																		<ui:dropDown binding="#{catastro$ABMParcela$ABMParcela.ddRural}" disabled="false" id="ddRural"
																			items="#{catastro$ABMParcela$ABMParcela.ddRuralDefaultOptions.options}" styleClass="textField" />
																	</td>
																	<td align="right" nowrap="nowrap">
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label44}" id="label44" styleClass="label" text="Comercial" />
																	</td>
																	<td>
																		<ui:dropDown binding="#{catastro$ABMParcela$ABMParcela.ddComercial}" disabled="false" id="ddComercial"
																			items="#{catastro$ABMParcela$ABMParcela.ddComercialDefaultOptions.options}" styleClass="textField" />
																	</td>
																</tr>
																<tr>
																	<td align="right" nowrap="nowrap">
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label45}" id="label45" styleClass="label" text="Equipamiento" />
																	</td>
																	<td>
																		<ui:dropDown binding="#{catastro$ABMParcela$ABMParcela.ddEquipamiento}" disabled="false" id="ddEquipamiento"
																			items="#{catastro$ABMParcela$ABMParcela.ddEquipamientoDefaultOptions.options}" styleClass="textField" />
																	</td>
																	<td align="right" nowrap="nowrap">
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label46}" id="label46" styleClass="label" text="Residencial" />
																	</td>
																	<td>
																		<ui:dropDown binding="#{catastro$ABMParcela$ABMParcela.ddResidencial}" disabled="false" id="ddResidencial"
																			items="#{catastro$ABMParcela$ABMParcela.ddResidencialDefaultOptions.options}" styleClass="textField" />
																	</td>
																</tr>
																<tr>
																	<td align="right" nowrap="nowrap">
																		<ui:label binding="#{catastro$ABMParcela$ABMParcela.label47}" id="label47" styleClass="label" text="Varios" />
																	</td>
																	<td>
																		<ui:dropDown binding="#{catastro$ABMParcela$ABMParcela.ddVarios}" disabled="false" id="ddVarios"
																			items="#{catastro$ABMParcela$ABMParcela.ddVariosDefaultOptions.options}" styleClass="textField" />
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td colspan="4" style="padding-left: 1px; padding-right: 1px;">
															<ui:panelGroup binding="#{catastro$ABMParcela$ABMParcela.groupPanelSubdivisionParcela}" id="groupPanelSubdivisionParcela">
																<div class="div" style="width: 780px; height: 15px;">Subdivisión de Parcela</div>
																<table class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 192px;">
																	<tbody>
																		<tr>
																			<td>
																				<ui:table augmentTitle="false" binding="#{catastro$ABMParcela$ABMParcela.table4}" id="table4" width="108">
																					<script>
																						<![CDATA[
									                                                /* ----- Functions for Table Preferences Panel ----- */
									                                                /*
									                                                 * Toggle the table preferences panel open or closed
									                                                 */
									                                                function togglePreferencesPanel() {
									                                                    var table = document.getElementById("form1:table4");
									                                                    table.toggleTblePreferencesPanel();
									                                                }
									                                                /* ----- Functions for Filter Panel ----- */
									                                                /*
									                                                 * Return true if the filter menu has actually changed,
									                                                 * so the corresponding event should be allowed to continue.
									                                                 */
									                                                function filterMenuChanged() {
									                                                    var table = document.getElementById("form1:table4");
									                                                    return table.filterMenuChanged();
									                                                }
									                                                /*
									                                                 * Toggle the custom filter panel (if any) open or closed.
									                                                 */
									                                                function toggleFilterPanel() {
									                                                    var table = document.getElementById("form1:table4");
									                                                    return table.toggleTableFilterPanel();
									                                                }
									                                                /* ----- Functions for Table Actions ----- */
									                                                /*
									                                                 * Initialize all rows of the table when the state
									                                                 * of selected rows changes.
									                                                 */
									                                                function initAllRows() {
									                                                    var table = document.getElementById("form1:table4");
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
									                                                    var table = document.getElementById("form1:table4");
									                                                    table.selectGroupRows(rowGroupId, selected);
									                                                }
									                                                /*
									                                                 * Disable all table actions if no rows have been selected.
									                                                 */
									                                                function disableActions() {
									                                                    // Determine whether any rows are currently selected
									                                                    var table = document.getElementById("form1:table4");
									                                                    var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
									                                                    // Set disabled state for top actions
									                                                    document.getElementById("form1:table4:tableActionsTop:deleteTop").setDisabled(disabled);
									                                                    // Set disabled state for bottom actions
									                                                    document.getElementById("form1:table4:tableActionsBottom:deleteBottom").setDisabled(disabled);
									                                                }]]></script>
																					<ui:tableRowGroup binding="#{catastro$ABMParcela$ABMParcela.tableRowGroup4}" id="tableRowGroup4"
																						emptyDataMsg="Ningún registro encontrado." sourceData="#{catastro$ABMParcela$ABMParcela.ldpSubdivisiones}"
																						sourceVar="currentRow">
																						<ui:tableColumn align="center" binding="#{catastro$ABMParcela$ABMParcela.tableColumn13}" id="tableColumn13"
																							valign="middle" width="10">
																							<ui:radioButton binding="#{catastro$ABMParcela$ABMParcela.radioButton4}" id="radioButton4" label=""
																								name="buttonGroup4" selected="#{catastro$ABMParcela$ABMParcela.RBSelected}"
																								selectedValue="#{catastro$ABMParcela$ConsultarParcela.currentRow4}" />
																						</ui:tableColumn>
																						<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumn16}" headerText="Titular" id="tableColumn16"
																							sort="titular">
																							<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticText16}" id="staticText16"
																								text="#{currentRow.value['titular']}" />
																						</ui:tableColumn>
																						<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumn15}" headerText="Superficie" id="tableColumn15">
																							<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticText17}" id="staticText17"
																								text="#{currentRow.value['superficie']}" />
																						</ui:tableColumn>
																					</ui:tableRowGroup>
																					<f:facet name="actionsTop">
																						<ui:panelGroup binding="#{catastro$ABMParcela$ABMParcela.groupPanel4}" id="groupPanel4">
																							<ui:label binding="#{catastro$ABMParcela$ABMParcela.label42}" text="Superficie Dividida:" for="tfSuperficieDividida"
																								id="label42" />
																							<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfSuperficieDividida}" columns="10" id="tfSuperficieDividida"
																								disabled="true" styleClass="textFieldDisabled" />
																						</ui:panelGroup>
																					</f:facet>
																				</ui:table>
																			</td>
																		</tr>
																	</tbody>
																</table>
															</ui:panelGroup>
														</td>
													</tr>
													<tr>
														<td colspan="4">
															<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
															<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
																<tr>
																	<td colspan="4">
																		<ui:panelGroup binding="#{catastro$ABMParcela$ABMParcela.panelAtributoDinamico}" id="panelAtributoDinamico">
																			<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
																		</ui:panelGroup>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<br />
														</td>
													</tr>
													<tr>
														<td colspan="4" style="padding-left: 1px; padding-right: 1px;">
															<ui:panelGroup binding="#{catastro$ABMParcela$ABMParcela.groupPanelVolanteCatastral}" id="groupPanelVolanteCatastral">
																<div class="div" style="width: 780px; height: 15px;">Volantes Catastrales</div>
																<table class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 192px;">
																	<tbody>
																		<tr>
																			<td>
																				<ui:table augmentTitle="false" binding="#{catastro$ABMParcela$ABMParcela.table3}" id="table3" width="108">
																					<script>
																						<![CDATA[
												                                        /* ----- Functions for Table Preferences Panel ----- */
												                                        /*
												                                         * Toggle the table preferences panel open or closed
												                                         */
												                                        function togglePreferencesPanel() {
												                                            var table = document.getElementById("form1:table3");
												                                            table.toggleTblePreferencesPanel();
												                                        }
												                                        /* ----- Functions for Filter Panel ----- */
												                                        /*
												                                         * Return true if the filter menu has actually changed,
												                                         * so the corresponding event should be allowed to continue.
												                                         */
												                                        function filterMenuChanged() {
												                                            var table = document.getElementById("form1:table3");
												                                            return table.filterMenuChanged();
												                                        }
												                                        /*
												                                         * Toggle the custom filter panel (if any) open or closed.
												                                         */
												                                        function toggleFilterPanel() {
												                                            var table = document.getElementById("form1:table3");
												                                            return table.toggleTableFilterPanel();
												                                        }
												                                        /* ----- Functions for Table Actions ----- */
												                                        /*
												                                         * Initialize all rows of the table when the state
												                                         * of selected rows changes.
												                                         */
												                                        function initAllRows() {
												                                            var table = document.getElementById("form1:table3");
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
												                                            var table = document.getElementById("form1:table3");
												                                            table.selectGroupRows(rowGroupId, selected);
												                                        }
												                                        /*
												                                         * Disable all table actions if no rows have been selected.
												                                         */
												                                        function disableActions() {
												                                            // Determine whether any rows are currently selected
												                                            var table = document.getElementById("form1:table3");
												                                            var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
												                                            // Set disabled state for top actions
												                                            document.getElementById("form1:table3:tableActionsTop:deleteTop").setDisabled(disabled);
												                                            // Set disabled state for bottom actions
												                                            document.getElementById("form1:table3:tableActionsBottom:deleteBottom").setDisabled(disabled);
												                                        }]]></script>
																					<ui:tableRowGroup binding="#{catastro$ABMParcela$ABMParcela.tableRowGroup3}" id="tableRowGroup3"
																						emptyDataMsg="Ningún registro encontrado." sourceData="#{catastro$ABMParcela$ABMParcela.ldpVolanteCatastralParcela}"
																						sourceVar="currentRow">
																						<ui:tableColumn align="center" binding="#{catastro$ABMParcela$ABMParcela.tableColumn7}" id="tableColumn7"
																							valign="middle" width="10">
																							<ui:radioButton binding="#{catastro$ABMParcela$ABMParcela.radioButton3}" id="radioButton3"
																								valueChangeListener="#{catastro$ABMParcela$ABMParcela.valueChangeEvent(event)}" onChange="this.form.submit()"
																								label="" name="buttonGroup3" selected="#{catastro$ABMParcela$ABMParcela.RBSelected3}"
																								selectedValue="#{catastro$ABMParcela$ABMParcela.currentRow3}" />
																						</ui:tableColumn>
																						<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumn8}" headerText="Nº Volante Catastral"
																							id="tableColumn8" sort="nroVolanteCatastral">
																							<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticText8}" id="staticText8"
																								text="#{currentRow.value['nroVolanteCatastral']}" />
																						</ui:tableColumn>
																						<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumn9}" headerText="Fecha" id="tableColumn9"
																							sort="fecha">
																							<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticText9}"
																								converter="#{catastro$ABMParcela$ABMParcela.dateTimeConverter1}" id="staticText9"
																								text="#{currentRow.value['fecha']}" />
																						</ui:tableColumn>
																					</ui:tableRowGroup>
																					<f:facet name="actionsTop">
																						<ui:panelGroup binding="#{catastro$ABMParcela$ABMParcela.groupPanel3}" id="groupPanel3">
																							<ui:button action="#{catastro$ABMParcela$ABMParcela.btnQuitarVolante_action}"
																								binding="#{catastro$ABMParcela$ABMParcela.btnQuitarVolante}" id="btnQuitarVolante"
																								onClick="return (confirm(&quot;¿Está seguro que desea eliminar el Volante Catastral?&quot;));" styleClass="button"
																								text="Eliminar" />
																							<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticText10}" escape="false" id="staticText10"
																								text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																							<ui:button action="#{catastro$ABMParcela$ABMParcela.btnImprimirReporte_action}"
																								binding="#{catastro$ABMParcela$ABMParcela.btnImprimirVolante}"
																								onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')" id="btnImprimirVolante"
																								styleClass="button" text="Visualizar Reporte" />
																						</ui:panelGroup>
																					</f:facet>
																				</ui:table>
																			</td>
																		</tr>
																	</tbody>
																</table>
															</ui:panelGroup>
														</td>
													</tr>
													
												</table>
											</ui:tab>
											<ui:tab id="three" binding="#{catastro$ABMParcela$ABMParcela.tabThree}" text="Título de propiedad">
												<table border="0" class="verde">
													<tbody>
														<tr>
															<td colspan="2">
																<ui:label binding="#{catastro$ABMParcela$ABMParcela.label57}" id="label57" styleClass="label57" text="Propietarios" />
															</td>
														</tr>
														<tr>
															<td colspan="2">
																<ui:table augmentTitle="false" binding="#{catastro$ABMParcela$ABMParcela.table5}" id="table5" width="359">
																	<script>
																		<![CDATA[
							                    /* ----- Functions for Table Preferences Panel ----- */
							                    /*
							                     * Toggle the table preferences panel open or closed
							                     */
							                    function togglePreferencesPanel() {
							                        var table = document.getElementById("form1:table5");
							                        table.toggleTblePreferencesPanel();
							                    }
							                    /* ----- Functions for Filter Panel ----- */
							                    /*
							                     * Return true if the filter menu has actually changed,
							                     * so the corresponding event should be allowed to continue.
							                     */
							                    function filterMenuChanged() {
							                        var table = document.getElementById("form1:table5");
							                        return table.filterMenuChanged();
							                    }
							                    /*
							                     * Toggle the custom filter panel (if any) open or closed.
							                     */
							                    function toggleFilterPanel() {
							                        var table = document.getElementById("form1:table5");
							                        return table.toggleTableFilterPanel();
							                    }
							                    /* ----- Functions for Table Actions ----- */
							                    /*
							                     * Initialize all rows of the table when the state
							                     * of selected rows changes.
							                     */
							                    function initAllRows() {
							                        var table = document.getElementById("form1:table5");
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
							                        var table = document.getElementById("form1:table5");
							                        table.selectGroupRows(rowGroupId, selected);
							                    }
							                    /*
							                     * Disable all table actions if no rows have been selected.
							                     */
							                    function disableActions() {
							                        // Determine whether any rows are currently selected
							                        var table = document.getElementById("form1:table5");
							                        var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
							                        // Set disabled state for top actions
							                        document.getElementById("form1:table5:tableActionsTop:deleteTop").setDisabled(disabled);
							                        // Set disabled state for bottom actions
							                        document.getElementById("form1:table5:tableActionsBottom:deleteBottom").setDisabled(disabled);
							                    }]]></script>
																	<ui:tableRowGroup binding="#{catastro$ABMParcela$ABMParcela.tableRowGroup5}" id="tableRowGroup5"
																		sourceData="#{catastro$ABMParcela$ABMParcela.ldpRegistroPropietario}" sourceVar="currentRow">
																		<ui:tableColumn align="center" binding="#{catastro$ABMParcela$ABMParcela.tableColumn18}" id="tableColumn18"
																			valign="middle" width="10">
																			<ui:radioButton binding="#{catastro$ABMParcela$ABMParcela.radioButton5}" id="radioButton5" label="" name="buttonGroup"
																				selected="#{catastro$ABMParcela$ABMParcela.RBSelected5}" selectedValue="#{catastro$ABMParcela$ABMParcela.currentRow5}" />
																		</ui:tableColumn>
																		<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumn19}" headerText="Persona" id="tableColumn19"
																			sort="persona">
																			<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticText23}" id="staticText23"
																				text="#{currentRow.value['persona']}" />
																		</ui:tableColumn>
																		<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumn20}" headerText="Descripción" id="tableColumn20"
																			sort="descripcion">
																			<ui:textArea binding="#{catastro$ABMParcela$ABMParcela.textArea1}" columns="40" id="textArea1" styleClass="textField"
																				text="#{currentRow.value['descripcion']}" />
																		</ui:tableColumn>
																		<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumn24}" headerText="Porcentaje" id="tableColumn24"
																			sort="porcentaje" align="center">
																			<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfPorcentaje}" columns="5" id="tfPorcentaje"
																				styleClass="textField" text="#{currentRow.value['porcentaje']}" />
																		</ui:tableColumn>
																		<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumn25}" headerText="Encargado" id="tableColumn25"
																			align="center">
																			<ui:radioButton binding="#{catastro$ABMParcela$ABMParcela.radioButtonEncargado}" id="radioButton7" label=""
																				name="buttonGroupEncargado" selected="#{catastro$ABMParcela$ABMParcela.RBSelectedEncargado}"
																				selectedValue="#{catastro$ABMParcela$ABMParcela.currentRowEncargado}" />
																		</ui:tableColumn>
																	</ui:tableRowGroup>
																	<f:facet name="actionsTop">
																		<ui:panelGroup binding="#{catastro$ABMParcela$ABMParcela.groupPanel5}" id="groupPanel5">
																			<ui:button action="#{catastro$ABMParcela$ABMParcela.btnAgregarPersonaFisica_action}"
																				binding="#{catastro$ABMParcela$ABMParcela.btnAgregarPersonaFisica}" id="btnAgregarPersonaFisica" styleClass="button"
																				text="Agregar PF" toolTip="Agregar Persona Física" />
																			<ui:button action="#{catastro$ABMParcela$ABMParcela.btnAgregarPersonaJuridica_action}"
																				binding="#{catastro$ABMParcela$ABMParcela.btnAgregarPersonaJuridica}" id="btnAgregarPersonaJuridica" styleClass="button"
																				text="Agregar PJ" toolTip="Agregar Persona Jurídica" />
																			<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticText24}" escape="false" id="staticText24"
																				text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
																			<a4j:commandButton action="#{catastro$ABMParcela$ABMParcela.btnQuitar_action}"
																				binding="#{catastro$ABMParcela$ABMParcela.btnQuitar}" id="btnQuitar" value="Quitar" styleClass="btnAjax"
																				reRender="table5" onmousedown="reemplazarClickConConfirmacion(this, '');" />
																			<a4j:commandButton action="#{catastro$ABMParcela$ABMParcela.btnQuitarTodos_action}"
																				binding="#{catastro$ABMParcela$ABMParcela.btnQuitarTodos}" id="btnQuitarTodos" value="Quitar todos" styleClass="btnAjax"
																				reRender="table5"
																				onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea quitar todos los elementos de esta lista?');" />
																		</ui:panelGroup>
																	</f:facet>
																</ui:table>
															</td>
														</tr>
														<tr>
															<td colspan="2">
																<ui:messageGroup binding="#{catastro$ABMParcela$ABMParcela.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
															</td>
														</tr>
														<tr>
															<td colspan="2">
																<hr />
																<br />
															</td>
														</tr>
														<tr>
															<td align="right" nowrap="nowrap">
																<ui:label binding="#{catastro$ABMParcela$ABMParcela.label49}" for="tfTitulo" id="label49" styleClass="label" text="Título" />
															</td>
															<td>
																<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfTitulo}" columns="40" id="tfTitulo" styleClass="textField" />
															</td>
														</tr>
														<tr>
															<td align="right" nowrap="nowrap">
																<ui:label binding="#{catastro$ABMParcela$ABMParcela.label50}" for="tfFechaInscripcion" id="label50" styleClass="label"
																	text="Fecha de Inscripción" />
															</td>
															<td>
																<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfFechaInscripcion}" id="tfFechaInscripcion" styleClass="textField"
																	columns="10" />
															</td>
														</tr>
														<tr>
															<td align="right" nowrap="nowrap">
																<ui:label binding="#{catastro$ABMParcela$ABMParcela.label51}" for="tfTomo" id="label51" styleClass="label" text="Tomo" />
															</td>
															<td>
																<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfTomo}" maxLength="8" id="tfTomo" styleClass="textField" />
															</td>
														</tr>
														<tr>
															<td align="right" nowrap="nowrap">
																<ui:label binding="#{catastro$ABMParcela$ABMParcela.label52}" for="tfFolio" id="label52" styleClass="label" text="Folio" />
															</td>
															<td>
																<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfFolio}" maxLength="8" id="tfFolio" styleClass="textField" />
															</td>
														</tr>
														<tr>
															<td align="right" nowrap="nowrap">
																<ui:label binding="#{catastro$ABMParcela$ABMParcela.label53}" for="tfAsiento" id="label53" styleClass="label" text="Asiento" />
															</td>
															<td>
																<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfAsiento}" maxLength="8" id="tfAsiento" styleClass="textField" />
															</td>
														</tr>
														<tr>
															<td align="right" nowrap="nowrap">
																<ui:label binding="#{catastro$ABMParcela$ABMParcela.label54}" for="tfArea" id="label54" styleClass="label" text="Área" />
															</td>
															<td>
																<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfArea}" columns="20" id="tfArea" styleClass="textField" />
															</td>
														</tr>
														<tr>
															<td align="right" nowrap="nowrap">
																<ui:label binding="#{catastro$ABMParcela$ABMParcela.label56}" for="tfTipoTransaccion" id="label56" styleClass="label"
																	text="Tipo de Transacción Catastral" />
															</td>
															<td nowrap="nowrap">
																<ui:dropDown styleClass="textField" binding="#{catastro$ABMParcela$ABMParcela.ddTipoTransaccion}"
																	items="#{catastro$ABMParcela$ABMParcela.ddTipoTransaccionOptions.options}" id="ddTipoTransaccion">
																</ui:dropDown>
															</td>
														</tr>
														<tr>
															<td>
																<br />
															</td>
														</tr>
														<tr>
															<td colspan="4">
																<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
																<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
																	<tr>
																		<td colspan="4">
																			<ui:panelGroup binding="#{catastro$ABMParcela$ABMParcela.panelAtributoDinamicoTituloPropiedad}"
																				id="panelAtributoDinamicoTituloPropiedad">
																				<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
																			</ui:panelGroup>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</tbody>
												</table>
											</ui:tab>
											<ui:tab id="four" binding="#{catastro$ABMParcela$ABMParcela.tabFour}" text="Plano de Mensura">
												<table border="0" class="verde">
													<tbody>
														<tr>
															<td colspan="4">
																<br />
															</td>
														</tr>
														<tr>
															<td align="center" nowrap="nowrap" width="10%">
																<ui:label binding="#{catastro$ABMParcela$ABMParcela.label66}" for="tfFechaPlanoMensura" id="label66" styleClass="label"
																	text="Fecha de Inscripción" />
																<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfFechaPlanoMensura}" id="tfFechaPlanoMensura"
																	styleClass="textField" columns="10" />
															</td>
														</tr>
														<tr>
															<td>
																<ui:label binding="#{catastro$ABMParcela$ABMParcela.label58}" id="label58" styleClass="label60" text="Números" />
															</td>
														</tr>
														<tr>
															<td>
																<table class="verde" style="border-width: 0px; padding: 5px 1px; width: 100%;">
																	<tr>
																		<td align="right" nowrap="nowrap">
																			<ui:label binding="#{catastro$ABMParcela$ABMParcela.label60}" for="tfNroPlanoMensura" id="label60" styleClass="label"
																				text="Plano" />
																		</td>
																		<td>
																			<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfNroPlanoMensura}" maxLength="10" id="tfNroPlanoMensura"
																				styleClass="textField" />
																		</td>
																		<td align="right" nowrap="nowrap">
																			<ui:label binding="#{catastro$ABMParcela$ABMParcela.label61}" id="label61" styleClass="label" text="Folio" />
																		</td>
																		<td>
																			<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfFolioPlanoMensura}" maxLength="10" id="tfFolioPlanoMensura"
																				styleClass="textField" />
																		</td>
																		<td align="right" nowrap="nowrap">
																			<ui:label binding="#{catastro$ABMParcela$ABMParcela.label62}" id="label62" styleClass="label" text="Tomo" />
																		</td>
																		<td>
																			<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfTomoPlanoMensura}" maxLength="10" id="tfTomoPlanoMensura"
																				styleClass="textField" />
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr>
															<td>
																<ui:label binding="#{catastro$ABMParcela$ABMParcela.label59}" id="label59" styleClass="label60" text="Plano Aprobado" />
															</td>
														</tr>
														<tr>
															<td>
																<table class="verde" style="border-width: 0px; padding: 5px 1px; width: 100%;">
																	<tr>
																		<td align="right" nowrap="nowrap">
																			<ui:label binding="#{catastro$ABMParcela$ABMParcela.label63}" for="tfNroExpediente" id="label63" styleClass="label"
																				text="Nº Expediente" />
																		</td>
																		<td>
																			<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfNroExpedientePlanoMensura}" maxLength="10"
																				id="tfNroExpedientePlanoMensura" styleClass="textField" />
																		</td>
																		<td align="right" nowrap="nowrap">
																			<ui:label binding="#{catastro$ABMParcela$ABMParcela.label64}" id="label64" styleClass="label" text="Nº Registro" />
																		</td>
																		<td>
																			<ui:textField binding="#{catastro$ABMParcela$ABMParcela.tfNroRegistroPlanoMensura}" maxLength="10"
																				id="tfNroRegistroPlanoMensura" styleClass="textField" />
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr>
															<td colspan="4">
																<br />
															</td>
														</tr>
														<tr>
															<td>
																<ui:label binding="#{catastro$ABMParcela$ABMParcela.label67}" id="label67" styleClass="label60" text="Tipo de Plano" />
															</td>
														</tr>
														<tr>
															<td>
																<table class="verde" style="border-width: 0px; padding: 5px 1px; width: 100%;">
																	<tbody>
																		<tr>
																			<td colspan="4" style="padding-left: 1px; padding-right: 1px;">
																				<ui:radioButtonGroup styleClass="optgroup" binding="#{catastro$ABMParcela$ABMParcela.rbgTipoPlanoMensura}"
																					items="#{catastro$ABMParcela$ABMParcela.rbgTipoPlanoMensuraOptions.options}" id="rbgTipoPlanoMensura" columns="3"
																					labelLevel="2">
																				</ui:radioButtonGroup>
																			</td>
																		</tr>
																	</tbody>
																</table>
															</td>
														</tr>
														<tr>
															<td>
																<ui:label binding="#{catastro$ABMParcela$ABMParcela.label68}" id="label68" styleClass="label60" text="Estado" />
															</td>
														</tr>
														<tr>
															<td>
																<table class="verde" style="border-width: 0px; padding: 5px 1px; width: 100%;">
																	<tbody>
																		<tr>
																			<td colspan="4" style="padding-left: 1px; padding-right: 1px;">
																				<ui:radioButtonGroup styleClass="optgroup" binding="#{catastro$ABMParcela$ABMParcela.rbgEstadoPlanoMensura}"
																					items="#{catastro$ABMParcela$ABMParcela.rbgEstadoPlanoMensuraOptions.options}" id="rbgEstadoPlanoMensura" columns="3"
																					labelLevel="2">
																				</ui:radioButtonGroup>
																			</td>
																		</tr>
																	</tbody>
																</table>
															</td>
														</tr>
														<tr>
															<td>
																<br />
															</td>
														</tr>
														<tr>
															<td colspan="4">
																<div class="div" style="width: 290px; height: 15px;">Atributos Dinámicos</div>
																<table border="0" class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
																	<tr>
																		<td colspan="4">
																			<ui:panelGroup binding="#{catastro$ABMParcela$ABMParcela.panelAtributoDinamicoPlanoMensura}"
																				id="panelAtributoDinamicoPlanoMensura">
																				<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
																			</ui:panelGroup>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</tbody>
													<tr>
														<td colspan="4">
															<br />
															<hr />
														</td>
													</tr>
													<tr>
														<td>
															<ui:label binding="#{catastro$ABMParcela$ABMParcela.lblComentario}" id="lblComentario" styleClass="label2" text="Comentario" />
														</td>
													</tr>
													<tr>
														<td colspan="4" align="left">
															<ui:textArea binding="#{catastro$ABMParcela$ABMParcela.taComentario}" id="taComentario" columns="90">
															</ui:textArea>
														</td>
													</tr>
												</table>
											</ui:tab>
											<ui:tab id="five" binding="#{catastro$ABMParcela$ABMParcela.tabFive}" text="Plano de Construcción">
														<table border="0" class="verde">
													<tr>
														<td colspan="4" style="padding-left: 1px; padding-right: 1px;">
															<div class="div" style="width: 780px; height: 15px;">Plano de Construcción</div>
															<table class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 192px;">
																<tbody>
																	<tr>
																		<td>
																			<ui:table augmentTitle="false" binding="#{catastro$ABMParcela$ABMParcela.tablePlanoConstruccion}" id="tablePlanoConstruccion" width="283">
																				<script>
																					<![CDATA[
								                                                    /* ----- Functions for Table Preferences Panel ----- */
								                                                    /*
								                                                     * Toggle the table preferences panel open or closed
								                                                     */
								                                                    function togglePreferencesPanel() {
								                                                        var table = document.getElementById("form1:tablePlanoConstruccion");
								                                                        table.toggleTblePreferencesPanel();
								                                                    }
								                                                    /* ----- Functions for Filter Panel ----- */
								                                                    /*
								                                                     * Return true if the filter menu has actually changed,
								                                                     * so the corresponding event should be allowed to continue.
								                                                     */
								                                                    function filterMenuChanged() {
								                                                        var table = document.getElementById("form1:tablePlanoConstruccion");
								                                                        return table.filterMenuChanged();
								                                                    }
								                                                    /*
								                                                     * Toggle the custom filter panel (if any) open or closed.
								                                                     */
								                                                    function toggleFilterPanel() {
								                                                        var table = document.getElementById("form1:tablePlanoConstruccion");
								                                                        return table.toggleTableFilterPanel();
								                                                    }
								                                                    /* ----- Functions for Table Actions ----- */
								                                                    /*
								                                                     * Initialize all rows of the table when the state
								                                                     * of selected rows changes.
								                                                     */
								                                                    function initAllRows() {
								                                                        var table = document.getElementById("form1:tablePlanoConstruccion");
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
								                                                        var table = document.getElementById("form1:tablePlanoConstruccion");
								                                                        table.selectGroupRows(rowGroupId, selected);
								                                                    }
								                                                    /*
								                                                     * Disable all table actions if no rows have been selected.
								                                                     */
								                                                    function disableActions() {
								                                                        // Determine whether any rows are currently selected
								                                                        var table = document.getElementById("form1:tablePlanoConstruccion");
								                                                        var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
								                                                        // Set disabled state for top actions
								                                                        document.getElementById("form1:table1:tableActionsTop:deleteTop").setDisabled(disabled);
								                                                        // Set disabled state for bottom actions
								                                                        document.getElementById("form1:table1:tableActionsBottom:deleteBottom").setDisabled(disabled);
								                                                    }]]></script>
																				<ui:tableRowGroup binding="#{catastro$ABMParcela$ABMParcela.tableRowGroupPlanoConstrucion}" id="tableRowGroupPlanoConstrucion"
																					emptyDataMsg="Ningún registro encontrado." sourceData="#{catastro$ABMParcela$ABMParcela.ldpPlanoConstruccion}"
																					sourceVar="currentRow">
																					<ui:tableColumn align="center" binding="#{catastro$ABMParcela$ABMParcela.tableColumnPlano1}" id="tableColumnPlano1"
																						valign="middle" width="10">
																						<ui:radioButton binding="#{catastro$ABMParcela$ABMParcela.radioButtonTablaPlano1}" id="radioButtonTablaPlano1" label=""
																							name="buttonGroup6" selected="#{catastro$ABMParcela$ABMParcela.RBSelected7}"
																							selectedValue="#{catastro$ABMParcela$ABMParcela.currentRowPlanoConstruccion}" />
																					</ui:tableColumn>
																			    	 <ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumnPlano2}" headerText="Nro Expediente"
																						id="tableColumnPlano2">
																						<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticTextTablaPlanoConstruccion1}" id="staticTextTablaPlanoConstruccion1"
																							text="#{currentRow.value['nroExpediente']}" />
																					</ui:tableColumn> 
																					<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumnPlano6}" headerText="Fecha"
																						id="tableColumnPlano6">
																						<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticTextTablaPlanoConstruccion5}" id="staticTextTablaPlanoConstruccion5"
																							text="#{currentRow.value['fechaInscripcion']}" converter="DateConverter"/>
																					</ui:tableColumn> 
																					<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumnPlano3}" headerText="Estado" id="tableColumnPlano3">
																						<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticTextTablaPlanoConstruccion2}" id="staticTextTablaPlanoConstruccion2"
																							text="#{currentRow.value['estado']}" />
																					</ui:tableColumn>
																				 <!--	<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumnPlano4}" headerText="Tipo" id="tableColumnPlano4">
																						<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticTextTablaPlanoConstruccion3}" id="staticTextTablaPlanoConstruccion3"
																							text="#{currentRow.value['tipo']}" />
																					</ui:tableColumn> -->
																				<!--	<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumnPlano5}" headerText="Plano"
																						id="tableColumnPlano5">
																						<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticTextTablaPlanoConstruccion4}" id="staticTextTablaPlanoConstruccion4"
																							text="#{currentRow.value['plano']}" />
																					</ui:tableColumn> -->
																				</ui:tableRowGroup> 
																				<f:facet name="actionsTop">
																					<ui:panelGroup binding="#{catastro$ABMParcela$ABMParcela.groupPanelPlanoConstruccion}" id="groupPanelPlanoConstruccion">
																						<ui:button action="#{catastro$ABMParcela$ABMParcela.btnAgregarPlanoConstrucion_action}"
																							binding="#{catastro$ABMParcela$ABMParcela.btnAgregarPlanoConstruccion}" id="btnAgregarPlanoConstruccion" styleClass="button"
																							text="Agregar" />
																						<ui:button action="#{catastro$ABMParcela$ABMParcela.btnModificarPlanoConstruccion_action}"
																							binding="#{catastro$ABMParcela$ABMParcela.btnModificarPlanoConstruccion}" id="btnModificarPlanoConstruccion" styleClass="button"
																							text="Modificar" />
																						<a4j:commandButton action="#{catastro$ABMParcela$ABMParcela.btnQuitarPlanoConstruccion_action}"
																							binding="#{catastro$ABMParcela$ABMParcela.btnQuitarPlanoConstruccion}" id="btnQuitarPlanoConstruccion"
																							onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea eliminar el Registro de Mejora?');"
																							value="Eliminar" styleClass="btnAjax" reRender="tablePlanoConstruccion" />
																						<ui:button action="#{catastro$ABMParcela$ABMParcela.btnConsultarPlanoConstruccion_action}"
																							binding="#{catastro$ABMParcela$ABMParcela.btnConsultarPlanoConstruccion}" id="btnConsultarPlanoConstruccion" styleClass="button"
																							text="Consultar" />
																					</ui:panelGroup>
																				</f:facet>
																			</ui:table>
																		</td>
																	</tr>
																</tbody>
															</table>
														</td>
													</tr>
												</table>
												
												
												
												
												
												
												
												
												
												
												
												
												
												
												
												
												
											</ui:tab>
											<ui:tab id="six" binding="#{catastro$ABMParcela$ABMParcela.tabSix}" text="Registros de Mejora">
												<table border="0" class="verde">
													<tr>
														<td colspan="4" style="padding-left: 1px; padding-right: 1px;">
															<div class="div" style="width: 780px; height: 15px;">Registros de Mejora</div>
															<table class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 192px;">
																<tbody>
																	<tr>
																		<td>
																			<ui:table augmentTitle="false" binding="#{catastro$ABMParcela$ABMParcela.table2}" id="table2" width="283">
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
																				<ui:tableRowGroup binding="#{catastro$ABMParcela$ABMParcela.tableRowGroup2}" id="tableRowGroup2"
																					emptyDataMsg="Ningún registro encontrado." sourceData="#{catastro$ABMParcela$ABMParcela.ldpRegistroMejoraParcela}"
																					sourceVar="currentRow">
																					<ui:tableColumn align="center" binding="#{catastro$ABMParcela$ABMParcela.tableColumn11}" id="tableColumn11"
																						valign="middle" width="10">
																						<ui:radioButton binding="#{catastro$ABMParcela$ABMParcela.radioButton2}" id="radioButton2" label=""
																							name="buttonGroup2" selected="#{catastro$ABMParcela$ABMParcela.RBSelected2}"
																							selectedValue="#{catastro$ABMParcela$ABMParcela.currentRow2}" />
																					</ui:tableColumn>
																					<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumn4}" headerText="Año de Construcción"
																						id="tableColumn4">
																						<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticText3}" id="staticText3"
																							text="#{currentRow.value['anioConstruccion']}" />
																					</ui:tableColumn>
																					<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumn5}" headerText="Categoría" id="tableColumn5">
																						<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticText4}" id="staticText4"
																							text="#{currentRow.value['categoria']}" />
																					</ui:tableColumn>
																					<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumn6}" headerText="Superficie" id="tableColumn6">
																						<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticText5}" id="staticText5"
																							text="#{currentRow.value['superficie']}" />
																					</ui:tableColumn>
																					<ui:tableColumn binding="#{catastro$ABMParcela$ABMParcela.tableColumn10}" headerText="Estado de la Mejora"
																						id="tableColumn10">
																						<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.staticText11}" id="staticText11"
																							text="#{currentRow.value['estadoMejora']}" />
																					</ui:tableColumn>
																				</ui:tableRowGroup>
																				<f:facet name="actionsTop">
																					<ui:panelGroup binding="#{catastro$ABMParcela$ABMParcela.groupPanel2}" id="groupPanel2">
																						<ui:button action="#{catastro$ABMParcela$ABMParcela.btnAgregarRegMejora_action}"
																							binding="#{catastro$ABMParcela$ABMParcela.btnAgregarRegMejora}" id="btnAgregarRegMejora" styleClass="button"
																							text="Agregar" />
																						<ui:button action="#{catastro$ABMParcela$ABMParcela.btnModificarRegMejora_action}"
																							binding="#{catastro$ABMParcela$ABMParcela.btnModificarRegMejora}" id="btnModificarRegMejora" styleClass="button"
																							text="Modificar" />
																						<a4j:commandButton action="#{catastro$ABMParcela$ABMParcela.btnQuitarRegMejora_action}"
																							binding="#{catastro$ABMParcela$ABMParcela.btnQuitarRegMejora}" id="btnQuitarRegMejora"
																							onmousedown="reemplazarClickConConfirmacion(this, '¿Está seguro que desea eliminar el Registro de Mejora?');"
																							value="Eliminar" styleClass="btnAjax" reRender="table2" />
																						<ui:button action="#{catastro$ABMParcela$ABMParcela.btnConsultarRegMejora_action}"
																							binding="#{catastro$ABMParcela$ABMParcela.btnConsultarRegMejora}" id="btnConsultarRegMejora" styleClass="button"
																							text="Consultar" />
																					</ui:panelGroup>
																				</f:facet>
																			</ui:table>
																		</td>
																	</tr>
																</tbody>
															</table>
														</td>
													</tr>
												</table>
											</ui:tab>
										</ui:tabSet>
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								<tr>
									<td align="right">
										<ui:label binding="#{catastro$ABMParcela$ABMParcela.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
									</td>
									<td>
										<ui:textArea binding="#{catastro$ABMParcela$ABMParcela.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
									</td>
								</tr>
								<tr>
									<td>
										<br />
									</td>
								</tr>
								 <tr>
									<td colspan="4">
										<ui:table binding="#{catastro$ABMParcela$ABMParcela.tablaLogs}" id="tbLogsAuditoria" 
											rendered="#{catastro$ABMParcela$ABMParcela.tabSet1.selected != 'one'
												and catastro$ABMParcela$ABMParcela.tabSet1.selected != 'five'
												and catastro$ABMParcela$ABMParcela.tabSet1.selected != 'six'}"/>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:messageGroup binding="#{catastro$ABMParcela$ABMParcela.messageGroup1}" id="messageGroup1" styleClass="grupoMsg" />
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap">
										<ui:button action="#{catastro$ABMParcela$ABMParcela.btnGuardar_action}" binding="#{catastro$ABMParcela$ABMParcela.btnGuardar}"
											id="btnGuardar" styleClass="button" />
										<ui:staticText binding="#{catastro$ABMParcela$ABMParcela.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{catastro$ABMParcela$ABMParcela.btnCancelar_action}" binding="#{catastro$ABMParcela$ABMParcela.btnCancelar}"
											id="btnCancelar" styleClass="button" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{catastro$ABMParcela$ABMParcela.hidIdPagina}" id="hidIdPagina"
						text="#{catastro$ABMParcela$ABMParcela.idPagina}" />
					<ui:hiddenField binding="#{catastro$ABMParcela$ABMParcela.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{catastro$ABMParcela$ABMParcela.idSubSesion}" />
					<ui:script binding="#{catastro$ABMParcela$ABMParcela.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>