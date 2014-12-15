<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:ui="http://www.sun.com/web/ui"
	xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1"
		pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{catastro$ABMParcela$ABMPlanoConstruccion.page1}"
			id="page1">
			<ui:html binding="#{catastro$ABMParcela$ABMPlanoConstruccion.html1}"
				id="html1">
			<ui:head binding="#{catastro$ABMParcela$ABMPlanoConstruccion.head1}"
				id="head1">
				<ui:link binding="#{catastro$ABMParcela$ABMPlanoConstruccion.link1}"
					id="link1" url="/resources/stylesheet.css" />
				<script>
					<![CDATA[
					function cargarComportamientoJQuery() {
						calendarioEnTextField("#form1:tfFechaPlanoConstruccion");
						
						var $filas = $("#form1\\:table1 tr");
						for(var i = 2; i < $filas.length; i++) {
							if($filas.eq(i).find("td:gt(2) :first").attr("id")) {
								calendarioEnTextField("#" + $filas.eq(i).find("td:gt(2) :first").attr("id"));
							}
						}
					}

					$(document).ready(function() {
						cargarComportamientoJQuery();
					});
					]]>
				</script>
			</ui:head>
			<ui:body binding="#{catastro$ABMParcela$ABMPlanoConstruccion.body1}"
				focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{catastro$ABMParcela$ABMPlanoConstruccion.form1}"
					id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText
									binding="#{catastro$ABMParcela$ABMPlanoConstruccion.stTitulo}"
									id="stTitulo" styleClass="tituloABM"
									text="#{catastro$ABMParcela$ABMPlanoConstruccion.head1.title}" />
							</caption>
							<tbody>
								<tr>
									<td colspan="2"><ui:label
											binding="#{catastro$ABMParcela$ABMPlanoConstruccion.label74}"
											id="label74" styleClass="label71" text="Expediente" /></td>
								</tr>
								<tr>
									<td>
										<h:panelGrid styleClass="bordesGrisesRedondos" columns="4" style="width: 75%">
											<ui:label
												binding="#{catastro$ABMParcela$ABMPlanoConstruccion.label75}"
												for="tfNroExpedientePlanoConstruccion" id="label75"
												styleClass="label" text="Nº Expediente" />
											<ui:textField
												binding="#{catastro$ABMParcela$ABMPlanoConstruccion.tfNroExpedientePlanoConstruccion}"
												maxLength="10" id="tfNroExpedientePlanoConstruccion"
												styleClass="textField" />
											<ui:label
												binding="#{catastro$ABMParcela$ABMPlanoConstruccion.label69}"
												for="tfFechaPlanoConstruccion" id="label69"
												styleClass="label" text="Fecha" />
											<ui:textField
												binding="#{catastro$ABMParcela$ABMPlanoConstruccion.tfFechaPlanoConstruccion}"
												id="tfFechaPlanoConstruccion" styleClass="textField"
												columns="10" />
										</h:panelGrid>
									</td>
								</tr>
								<tr><td></td></tr>
								<tr>
									<td colspan="4">
										<ui:label
											binding="#{catastro$ABMParcela$ABMPlanoConstruccion.lblSuperficieCubiertaSemicubierta}"
											id="lblSuperficieCubiertaSemicubierta" styleClass="label71" text="Superficie Cubierta y SemiCubierta" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
											<h:panelGrid columns="4" styleClass="bordesGrisesRedondos" style="width: 75%">
											<ui:label
														binding="#{catastro$ABMParcela$ABMPlanoConstruccion.lblSuperficieCubiertaPlantaBaja}"
														for="tfSuperficieCubiertaPlantaBaja" id="lblSuperficieCubiertaPlantaBaja"
														styleClass="label" text="Superficie Cubierta Planta Baja"/>
												<ui:textField
														binding="#{catastro$ABMParcela$ABMPlanoConstruccion.tfSuperficieCubiertaPlantaBaja}"
														 id="tfSuperficieCubiertaPlantaBaja" styleClass="textField" columns="10" onKeyPress="return ValidarNum(event,this)"/>
											<ui:label
												binding="#{catastro$ABMParcela$ABMPlanoConstruccion.lblSuperficieCubiertaPlantaAlta}"
												for="tfSuperficieCubiertaPlantaAlta" id="lblSuperficieCubiertaPlantaAlta"
												styleClass="label" text="Superficie Cubierta Planta Alta"/>
											 <ui:textField
												binding="#{catastro$ABMParcela$ABMPlanoConstruccion.tfSuperficieCubiertaPlantaAlta}"
												id="tfSuperficieCubiertaPlantaAlta" styleClass="textField" columns="10" onKeyPress="return ValidarNum(event,this)"/>
													<ui:label
														binding="#{catastro$ABMParcela$ABMPlanoConstruccion.lblSuperficieSemiCubiertaPlantaBaja}"
														for="tfSuperficieSemiCubiertaPlantaBaja" id="lblSuperficieSemiCubiertaPlantaBaja"
														styleClass="label" text="Superficie Semi Cubierta Planta Baja" />
													<ui:textField
														binding="#{catastro$ABMParcela$ABMPlanoConstruccion.tfSuperficieSemiCubiertaPlantaBaja}"
														id="tfSuperficieSemiCubiertaPlantaBaja" columns="10"	styleClass="textField" onKeyPress="return ValidarNum(event,this)"/>
													<ui:label
														binding="#{catastro$ABMParcela$ABMPlanoConstruccion.lblSuperficieSemiCubiertaPlantaAlta}"
														for="tfSuperficieSemiCubiertaPlantaAlta" id="lblSuperficieSemiCubiertaPlantaAlta"
														styleClass="label" text="Superficie Semi Cubierta Planta Alta"/>
													 <ui:textField
														binding="#{catastro$ABMParcela$ABMPlanoConstruccion.tfSuperficieSemiCubiertaPlantaAlta}"
														id="tfSuperficieSemiCubiertaPlantaAlta" styleClass="textField"	columns="10" onKeyPress="return ValidarNum(event,this)"/>
														<ui:label
															binding="#{catastro$ABMParcela$ABMPlanoConstruccion.lblNroPlantas}"
															for="tfNroPlantas" id="lblNroPlantas"
															styleClass="label" text="Nro Plantas"/>
														 <ui:textField
															binding="#{catastro$ABMParcela$ABMPlanoConstruccion.tfNroPlantas}"
															id="tfNroPlantas" styleClass="textField" columns="10" onKeyPress="return ValidarNum(event,this)"/>
											</h:panelGrid>
									</td>
								</tr>
									<tr><td></td></tr>
								<tr>
									<td colspan="4">
										<ui:label binding="#{catastro$ABMParcela$ABMPlanoConstruccion.label79}"
											  id="label79" styleClass="label71" text="Estadisticas Indec" />
									</td>
								</tr>
								<tr>
									<td>		
										<ui:label binding="#{catastro$ABMParcela$ABMPlanoConstruccion.lblEstadisticasIndec}" id="lblEstadisticasIndec"
											styleClass="label" text="Estadisticas Indec" />
										<ui:textField binding="#{catastro$ABMParcela$ABMPlanoConstruccion.tfEstadisticasIndec}" columns="75" disabled="true"
											id="tfEstadisticasIndec" styleClass="textField" />
										<ui:button action="#{catastro$ABMParcela$ABMPlanoConstruccion.btnSeleccionarEstadisticaIndec_action}"
											binding="#{catastro$ABMParcela$ABMPlanoConstruccion.btnSeleccionarEstadisticaIndec}" escape="false"
											id="btnSeleccionarEstadisticaIndec" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" />
										<a4j:commandButton id="btnLimpiarEstadisticaIndec" reRender="form1:tfEstadisticasIndec"
											binding="#{catastro$ABMParcela$ABMPlanoConstruccion.btnLimpiarEstadisticaIndec}"
											action="#{catastro$ABMParcela$ABMPlanoConstruccion.btnLimpiarEstadisticaIndec_action}" styleClass="buttonLimpiarAjax" />
									</td>	
								</tr> 
								<tr><td></td></tr>
								<tr>
									<td colspan="2"><ui:label
											binding="#{catastro$ABMParcela$ABMPlanoConstruccion.label78}"
											id="label78" styleClass="label71" text="Estado" /></td>
								</tr>
								<tr>
									<td colspan="2">
										<h:panelGrid columns="4" styleClass="bordesGrisesRedondos" style="width: 75%">
													<ui:radioButtonGroup
															styleClass="optgroup"
															binding="#{catastro$ABMParcela$ABMPlanoConstruccion.rbgEstadoPlanoConstruccion}"
															items="#{catastro$ABMParcela$ABMPlanoConstruccion.rbgEstadoPlanoConstruccionOptions.options}" 
															id="rbgEstadoPlanoConstruccion" columns="4"
															labelLevel="2">
														</ui:radioButtonGroup>
										</h:panelGrid>
									</td>
								</tr>
								<tr><td></td></tr>
									<tr>
									<td colspan="4">
										<ui:label binding="#{catastro$ABMParcela$ABMPlanoConstruccion.label1}" id="label1" styleClass="label71" text="Cargos" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<ui:table augmentTitle="false" binding="#{catastro$ABMParcela$ABMPlanoConstruccion.table1}" id="table1" width="479">
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
											<ui:tableRowGroup binding="#{catastro$ABMParcela$ABMPlanoConstruccion.tableRowGroup1}" id="tableRowGroup1"
												sourceData="#{catastro$ABMParcela$ABMPlanoConstruccion.ldpCargos}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{catastro$ABMParcela$ABMPlanoConstruccion.tableColumn1}" id="tableColumn1"
													valign="middle" width="10">
													<ui:radioButton binding="#{catastro$ABMParcela$ABMPlanoConstruccion.radioButton1}" id="radioButton1" label=""
														name="buttonGroup" selected="#{catastro$ABMParcela$ABMPlanoConstruccion.RBSelected}"
														selectedValue="#{catastro$ABMParcela$ABMPlanoConstruccion.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{catastro$ABMParcela$ABMPlanoConstruccion.tableColumn2}" headerText="Persona fisica"
													id="tableColumn2">
													<ui:staticText binding="#{catastro$ABMParcela$ABMPlanoConstruccion.staticText1}" id="staticText1"
														text="#{currentRow.value['persona']}" />
												</ui:tableColumn>
												<ui:tableColumn align="center" binding="#{catastro$ABMParcela$ABMPlanoConstruccion.tableColumn4}" headerText="Cargo"
													id="tableColumn4" valign="middle" width="10">
													<ui:dropDown binding="#{catastro$ABMParcela$ABMPlanoConstruccion.ddCargo}" id="ddEstado"
														items="#{catastro$ABMParcela$ABMPlanoConstruccion.ddCargoSocietarioDefaultOptions.options}" styleClass="textField"
														selected="#{currentRow.value['cargo']}" converter="EnumConverter" immediate="false" /> 
												</ui:tableColumn>
												<ui:tableColumn align="center" binding="#{catastro$ABMParcela$ABMPlanoConstruccion.tableColumn5}" headerText="Fecha Firma"
													id="tableColumn5" valign="middle" width="10">
													<ui:textField	binding="#{catastro$ABMParcela$ABMPlanoConstruccion.tfFechaFirma}"
															id="tfFechaFirma" styleClass="textField" columns="10" text="#{currentRow.value['fechaFirma']}" converter="DateConverter"/>
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop"> 
												<ui:panelGroup binding="#{catastro$ABMParcela$ABMPlanoConstruccion.groupPanel1}" id="groupPanel1">

													<ui:button action="#{catastro$ABMParcela$ABMPlanoConstruccion.btnAgregar_action}"
														binding="#{catastro$ABMParcela$ABMPlanoConstruccion.btnAgregar}" id="btnAgregarPF" styleClass="button"
														text="Agregar PF" /> 
													<ui:button action="#{catastro$ABMParcela$ABMPlanoConstruccion.btnAgregarPJ_action}"
														binding="#{catastro$ABMParcela$ABMPlanoConstruccion.btnAgregarPJ}" id="btnAgregarPJ" styleClass="button"
														text="Agregar PJ" />
													 <a4j:commandButton action="#{catastro$ABMParcela$ABMPlanoConstruccion.btnQuitar_action}"
                                                       binding="#{catastro$ABMParcela$ABMPlanoConstruccion.btnQuitar}" id="btnQuitar"
                                                       value="Quitar" styleClass="btnAjax" reRender="table1" onmousedown="reemplazarClickConConfirmacion(this, '');" />
													<ui:staticText binding="#{catastro$ABMParcela$ABMPlanoConstruccion.staticText4}" escape="false" id="staticText4"
														text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
												       <a4j:commandButton action="#{catastro$ABMParcela$ABMPlanoConstruccion.btnQuitarTodos_action}"
                                                       binding="#{catastro$ABMParcela$ABMPlanoConstruccion.btnQuitarTodos}"
                                                       onmousedown="reemplazarClickConConfirmacion(this, '¿Desea quitar todos los elementos de la lista?');"
                                                       id="btnQuitarTodos" value="Quitar todos" styleClass="btnAjax" reRender="table1" /> 
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="div" style="width: 290px; height: 15px;">Atributos
											Dinámicos</div>
										<table border="0" class="tablaInterna"
											style="-moz-border-radius: 0px 0px 5px 5px; width: 299px;">
											<tr>
												<td colspan="4"><ui:panelGroup
														binding="#{catastro$ABMParcela$ABMPlanoConstruccion.panelAtributoDinamicoPlanoConstruccion}"
														id="panelAtributoDinamicoPlanoConstruccion">
														<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
													</ui:panelGroup></td>
											</tr>
										</table>
									</td>
								</tr>
							</tbody>
							<tr>
								<td colspan="4"><br />
									<hr /></td>
							</tr>
							<tr>
								<td><ui:label
										binding="#{catastro$ABMParcela$ABMPlanoConstruccion.lblComentario2}"
										id="lblComentario2" styleClass="label2" text="Comentario" />
								</td>
							</tr>
							<tr>
								<td colspan="4" align="left"><ui:textArea
										binding="#{catastro$ABMParcela$ABMPlanoConstruccion.taComentario2}"
										id="taComentario2" columns="90">
									</ui:textArea></td>
							</tr>

							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap"><ui:button
											action="#{catastro$ABMParcela$ABMPlanoConstruccion.btnGuardar_action}"
											binding="#{catastro$ABMParcela$ABMPlanoConstruccion.btnGuardar}"
											id="btnGuardar" styleClass="button" /> <ui:staticText
											binding="#{catastro$ABMParcela$ABMPlanoConstruccion.stSeparador}"
											escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" /> <ui:button
											action="#{catastro$ABMParcela$ABMPlanoConstruccion.btnCancelar_action}"
											binding="#{catastro$ABMParcela$ABMPlanoConstruccion.btnCancelar}"
											id="btnCancelar" styleClass="button" /></td>
								</tr>
							</tfoot>
								<tr>
									<td align="center">
										<h:panelGrid columns="4">
											<ui:label binding="#{catastro$ABMParcela$ABMPlanoConstruccion.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria" />
											<ui:textArea binding="#{catastro$ABMParcela$ABMPlanoConstruccion.taComentarioLogAuditoria}" id="taComentarioLogAuditoria" />
										</h:panelGrid>
									</td>
								</tr>
							<tr>
								<td><br /></td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table binding="#{catastro$ABMParcela$ABMPlanoConstruccion.tablaLogs}" id="tbLogsAuditoria" />
								</td>
							</tr>
							<td colspan="4">
								<ui:messageGroup binding="#{catastro$ABMParcela$ABMPlanoConstruccion.messageGroup}"	id="messageGroup" styleClass="grupoMsg" />
							</td>
						</table>
					</div>
					<ui:hiddenField
						binding="#{catastro$ABMParcela$ABMPlanoConstruccion.hidIdPagina}"
						id="hidIdPagina"
						text="#{catastro$ABMParcela$ABMPlanoConstruccion.idPagina}" />
					<ui:hiddenField
						binding="#{catastro$ABMParcela$ABMPlanoConstruccion.hidIdSubSesion}"
						id="hidIdSubSesion"
						text="#{catastro$ABMParcela$ABMPlanoConstruccion.idSubSesion}" />
					<ui:script
						binding="#{catastro$ABMParcela$ABMPlanoConstruccion.scriptValidador}"
						id="scriptValidador" url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>