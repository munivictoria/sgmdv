<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.page1}" id="page1">
			<ui:html binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.html1}" id="html1">
			<ui:head binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.head1}" id="head1" title="Seleccionar Recurso">
				<ui:link binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.body1}" focus="form1:tfNombre" id="body1"
				onLoad="parent.footer.location.reload();Init();setStyleTextoEncabezado();"
				style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.form1}" id="form1">
					<div class="formularioABM">
						<table border="0" class="verde">
							<caption>
								<ui:staticText binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.stTitulo}" id="stTitulo" styleClass="tituloABM"
									text="#{framework$ABMAtributoDinamico$SeleccionarRecurso.head1.title}" />
							</caption>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:panelGroup binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.panelBotonesDinamicos}" id="panelBotonesDinamicos">
										<!-- AQUI VA LO QUE SE CREA DINAMICAMENTE -->
										<script>
                                                            function capturarClickBoton(boton){
                                                                textoBoton = boton.value;
                                                                boton.style.border = "none";
                                                                seteaTextoBonton(textoBoton);
                                                            }
                                                            function seteaTextoBonton(newVal){
                                                                var hidenField = document.getElementById("form1:hidIdBotonera");
                                                                hidenField.value = newVal;
                                                            }
                                                        </script>
									</ui:panelGroup>
								</td>
							</tr>
							<tr>
								<td>
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:table augmentTitle="false" binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.table1}" id="table1" width="1200px"
										clearSortButton="false" >
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
										<ui:tableRowGroup binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.tableRowGroup1}" id="tableRowGroup1"
											sourceData="#{framework$ABMAtributoDinamico$SeleccionarRecurso.ldpRecursos}" sourceVar="currentRow">
											<ui:tableColumn align="center" binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.tableColumn1}" id="tableColumn1"
												valign="middle" width="10">
												<ui:radioButton binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.radioButton1}" id="radioButton1" label=""
													name="buttonGroup" selected="#{framework$ABMAtributoDinamico$SeleccionarRecurso.RBSelected}"
													selectedValue="#{framework$ABMAtributoDinamico$SeleccionarRecurso.currentRow}" />
											</ui:tableColumn>
											<ui:tableColumn binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.tableColumn2}" id="tableColumn2" noWrap="true"
												sort="nombre">
												<ui:staticText binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.staticText1}" id="staticText1"
													text="#{currentRow.value['nombre']}" />
											</ui:tableColumn>
										</ui:tableRowGroup>
									</ui:table>
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<ui:messageGroup binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.messageGroup1}" id="messageGroup1"
										styleClass="grupoMsg" />
								</td>
							</tr>
						</table>
						<table class="verde">
							<tfoot>
								<tr>
									<td align="right" colspan="4" nowrap="nowrap" class="formularioABM tfoot td">
										<ui:button action="#{framework$ABMAtributoDinamico$SeleccionarRecurso.btnGuardar_action}"
											binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.btnGuardar}" id="btnGuardar" styleClass="button" text="Seleccionar" />
										<ui:staticText binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.stSeparador}" escape="false" id="stSeparador"
											text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
										<ui:button action="#{framework$ABMAtributoDinamico$SeleccionarRecurso.btnCancelar_action}"
											binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar" />
									</td>
								</tr>
							</tfoot>
						</table>
						<br /> <br /> <br />
					</div>
					<ui:hiddenField binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.hidIdPagina}" id="hidIdPagina"
						text="#{framework$ABMAtributoDinamico$SeleccionarRecurso.idPagina}" />
					<ui:hiddenField binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{framework$ABMAtributoDinamico$SeleccionarRecurso.idSubSesion}" />
					<ui:hiddenField binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.hidIdBotonera}" id="hidIdBotonera" />
					<ui:script binding="#{framework$ABMAtributoDinamico$SeleccionarRecurso.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>
