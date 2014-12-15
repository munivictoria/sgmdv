<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
	xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
	<jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8" />
	<f:view>
		<ui:page binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.page1}" id="page1">
			<ui:html binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.html1}" id="html1">
			<ui:head binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.head1}" id="head1"
				title="Administración de Clausuras Pendientes de Firma">
				<ui:link binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.link1}" id="link1" url="/resources/stylesheet.css" />
			</ui:head>
			<ui:body binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.body1}" focus="form1:btnBuscar" id="body1"
				onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar(); "
				style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')"
				onKeyUp="eventoByEscape(event,'btnCancelar')">
				<ui:form binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.form1}" id="form1">
					<div class="divAdmin">
						<div class="formularioABM">
							<table border="0" class="azul">
								<caption>
									<ui:staticText binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.stTitulo}" id="stTitulo"
										styleClass="tituloABM" text="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.head1.title}" />
								</caption>
								<tbody>
									<tr>
										<td colspan="2"></td>
									</tr>
									<tr>
										<td colspan="2">
											<div>
												Haga clic en <b>Listar Firmas Pendientes</b> para ver las Clausuras que requieren su Firma.
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="2"></td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<td align="right" colspan="2">
											<a4j:commandButton binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.btnBuscar}"
												action="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.btnBuscar_action}" id="btnBuscar" value="Buscar"
												styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()" />
											<ui:staticText binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.staticText2}" escape="false"
												id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
											<ui:button action="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.btnCancelar_action}"
												binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.btnCancelar}" id="btnCancelar" styleClass="button"
												text="Cancelar" />
										</td>
									</tr>
								</tfoot>
							</table>
							<div>
								<a4j:outputPanel ajaxRendered="true">
									<ui:messageGroup binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.messageGroup}" id="messageGroup"
										showDetail="true" showSummary="false" styleClass="grupoMsgAdmin" />
								</a4j:outputPanel>
							</div>
							<table class="general">
								<tr>
									<td>
										<ui:table binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.table1}" id="table1">
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
                                                    }
                                                    ]]></script>
											<ui:tableRowGroup binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.tableRowGroup1}"
												emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)"
												onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
												sourceData="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.ldpFirmaPendiente}" sourceVar="currentRow">
												<ui:tableColumn align="center" binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.tableColumn1}"
													id="tableColumn1" valign="middle" width="10">
													<ui:radioButton binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.radioButton1}" id="radioButton1"
														label="" name="buttonGroup" onClick="checkUncheck(this)"
														selected="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.RBSelected}"
														selectedValue="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.currentRow}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.tableColumn3}"
													headerText="Obligación" id="tableColumn3" sort="documentoSHPS">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.staticText3}" id="staticText3"
														text="#{currentRow.value['documentoSHPS']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.tableColumn4}"
													headerText="Fecha Alta" id="tableColumn4" sort="fechaAlta">
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.staticText4}"
														converter="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.dateTimeConverter1}" id="staticText4"
														text="#{currentRow.value['fechaAlta']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.tableColumn2}"
													headerText="Fecha Baja" id="tableColumn2" sort="fechaBaja">
													<ui:textField binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.tfFechaBaja}"
														converter="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.dateTimeConverter1}" id="tfFechaBaja"
														styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" text="#{currentRow.value['fechaBaja']}" />
													<ui:staticText binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.stFormatoFecha}" escape="false"
														id="stFormatoFecha" styleClass="label" text="[dd/mm/aaaa]" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.tableColumn5}"
													headerText="Descripción" id="tableColumn5" sort="descripcion">
													<ui:textArea binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.textArea1}" columns="40"
														disabled="true" id="textArea1" styleClass="textFieldDisabled" text="#{currentRow.value['descripcion']}" />
												</ui:tableColumn>
												<ui:tableColumn binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.tableColumn6}" headerText="Activa"
													id="tableColumn6" sort="clausuraActiva">
													<ui:checkbox binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.checkbox1}" disabled="true"
														id="checkbox1" selected="#{currentRow.value['clausuraActiva']}" />
												</ui:tableColumn>
											</ui:tableRowGroup>
											<f:facet name="actionsTop">
												<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.groupPanel1}" id="groupPanel1"
													separator="&lt;br/&gt;&lt;br/&gt;">
													<ui:panelGroup binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.gpBotones}" id="gpBotones">
														<ui:button action="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.btnAnularClausura_action}"
															binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.btnAnularClausura}" id="btnAnularClausura"
															onClick="return (confirm(&quot;¿Está seguro que desea ANULAR la Clausura?&quot;));" styleClass="button"
															text="Anular Clausura" />
														<ui:staticText binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.staticText8}" escape="false"
															id="staticText8" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;" />
														<ui:button action="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.btnExportar_action}"
															binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.btnExportar}" id="btnExportar"
															styleClass="button" text="Exportar" onClick="return exportarReporte()" />
													</ui:panelGroup>
												</ui:panelGroup>
											</f:facet>
										</ui:table>
									</td>
								</tr>
							</table>
							<br /> <br /> <br />
						</div>
					</div>
					<script>
                    document.getElementById('form1:btnBuscar').focus();
                        </script>
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.hidIdPagina}" id="hidIdPagina"
						text="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.idPagina}" />
					<ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.hidIdSubSesion}" id="hidIdSubSesion"
						text="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.idSubSesion}" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.scriptFinal1}" id="scriptFinal1"
						url="/resources/javascript/pageGreyXlib.js" />
					<ui:script binding="#{habilitaciones$grpSHPS$ABMClausuraSinFirma$AdminClausuraSinFirma.scriptValidador}" id="scriptValidador"
						url="/resources/javascript/validador.js" />
				</ui:form>
			</ui:body>
			</ui:html>
		</ui:page>
	</f:view>
</jsp:root>