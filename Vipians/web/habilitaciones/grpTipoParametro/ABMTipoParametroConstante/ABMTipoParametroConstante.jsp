<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.head1}" id="head1">
                    <ui:link binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.link1}" id="link1" url="/resources/stylesheet.css"/>
                    <script>
                    <![CDATA[
                        /**
                         * Genera el Nombre de Variable
                         */
                        function armarNombreVariable(tfOrigen, idTfDestino) {
                            valor = tfOrigen.value;
                            if (!isNaN(valor.substring(0,1))){ valor = "VAR_" + valor;}
                            separador = "_";
                            txtNomVar = document.getElementById(idTfDestino);
                            valor = valor.replace(/ /g,separador);
                            valor = valor.replace(/á/g,"a");
                            valor = valor.replace(/é/g,"e");
                            valor = valor.replace(/í/g,"i");
                            valor = valor.replace(/ó/g,"o");
                            valor = valor.replace(/ú/g,"u");
                            valor = valor.replace(/\./g,separador);
                            valor = valor.replace(/\\/g,separador);
                            valor = valor.replace(/\+/g,separador);
                            valor = valor.replace(/\-/g,separador);
                            valor = valor.replace(/\*/g,separador);
                            // valor = valor.replace(/\//g,separador);
                           // valor = valor.replace(/\"/g,separador);
                           // valor = valor.replace(/\'/g,separador); 
                            valor = valor.replace(/\#/g,separador);
                            valor = valor.replace(/\(/g,separador);
                            valor = valor.replace(/\)/g,separador);
                            valor = valor.replace(/\=/g,separador);
                            valor = valor.replace(/\&/g,separador);
                            valor = valor.replace(/\?/g,separador);
                            valor = valor.replace(/\¿/g,separador);
                            valor = valor.replace(/\%/g,separador);
                            txtNomVar.value = valor.toUpperCase();
                        }
                        
                        function cargarComportamientoJQuery() {
							var $filas = $("#form1\\:tableListaLineas tr:not(:first-child)");
							if($filas.length > 0) {
								var $idPrimero = $("#form1\\:tableListaLineas tr:gt(2) td:gt(1) :first").attr("id");
								$("#" + $idPrimero.replace(/:/g, "\\:")).val("");
								$("#" + $idPrimero.replace(/:/g, "\\:")).prop("disabled", true);
							}
							for(var i = 0; i < $filas.length; i++) {
								calendarioEnTextField("#" + $filas.eq(i).find("td:gt(1) :first").attr("id"));
							}
						}
						$(document).ready(function() {
							cargarComportamientoJQuery();
						});
                    ]]>
                    </script>
                    </ui:head>
                    <ui:body binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.body1}" focus="form1:tfNombre"
                             id="body1" onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                        <ui:form binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.stTitulo}"
                                                   id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.label4}"
                                                for="tfNombre" id="label4" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.tfNombre}"
                                                columns="40" id="tfNombre" onKeyUp="armarNombreVariable(this, 'form1:tfNombreVariable');" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.label1}"
                                                for="tfNombreVariable" id="label1" styleClass="label" text="Nombre de Variable"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.tfNombreVariable}"
                                                columns="40" id="tfNombreVariable" onBlur="armarNombreVariable(this, this.id);" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.label2}"
                                                for="tfValor" id="label2" styleClass="label" text="Valor"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.tfValor}"
                                                columns="15" id="tfValor" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.label3}"
                                                id="label3" styleClass="label" text="Tipo de Valor"/>
                                        </td>
                                        <td>
                                            <ui:radioButton
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.rbFijo}"
                                                id="rbFijo" name="rbgTipoValor"/>
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.label5}"
                                                for="rbFijo" id="label5" styleClass="label" text="Fijo"/>
                                            <br/>
                                            <ui:radioButton
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.rbPorcentual}"
                                                id="rbPorcentual" name="rbgTipoValor"/>
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.label6}"
                                                for="rbPorcentual" id="label6" styleClass="label" text="Porcentual"/>
                                        </td>
                                    </tr>
                                    
                                    <tr>
		                               	<td nowrap="nowrap">
											<ui:label binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.lblListaLineas}" id="lblListaLineas" styleClass="label2" text="Lineas" />
										</td>
									</tr>
									<tr>
										<td>
											<a4j:commandButton action="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.btnAgregarLinea_action}" 
											binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.btnAgregarVariable}" 
											id="btnAgregarVariable" value="Agregar" styleClass="btnAjax" reRender="tableListaLineas" oncomplete="cargarComportamientoJQuery();"/>
											
											<a4j:commandButton action="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.btnQuitarLinea_action}" 
											binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.btnQuitarVariable}" 
											id="btnQuitarVariable" onmouseup="return (confirm(&quot;¿Está seguro que desea Quitar la Variable?&quot;));" 
											styleClass="btnAjax" reRender="tableListaLineas" value="Quitar" oncomplete="cargarComportamientoJQuery();"/>
										</td>
									</tr>
									<tr>
										<td colspan="4" nowrap="nowrap">
											<div style="overflow: auto; width: 790px;">
												<ui:table augmentTitle="false" binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.tableListaLineas}" id="tableListaLineas">
													<script>
												<![CDATA[
												/* ----- Functions for Table Preferences Panel ----- */
												/*
												 * Toggle the table preferences panel open or closed
												 */
												function togglePreferencesPanel() {
													var table = document
															.getElementById("form1:tableListaLineas");
													table
															.toggleTblePreferencesPanel();
												}
												/* ----- Functions for Filter Panel ----- */
												/*
												 * Return true if the filter menu has actually changed,
												 * so the corresponding event should be allowed to continue.
												 */
												function filterMenuChanged() {
													var table = document
															.getElementById("form1:tableListaLineas");
													return table
															.filterMenuChanged();
												}
												/*
												 * Toggle the custom filter panel (if any) open or closed.
												 */
												function toggleFilterPanel() {
													var table = document
															.getElementById("form1:tableListaLineas");
													return table
															.toggleTableFilterPanel();
												}
												/* ----- Functions for Table Actions ----- */
												/*
												 * Initialize all rows of the table when the state
												 * of selected rows changes.
												 */
												function initAllRows() {
													var table = document
															.getElementById("form1:tableListaLineas");
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
												function selectGroupRows(
														rowGroupId, selected) {
													var table = document
															.getElementById("form1:tableListaLineas");
													table.selectGroupRows(
															rowGroupId,
															selected);
												}
												/*
												 * Disable all table actions if no rows have been selected.
												 */
												function disableActions() {
													// Determine whether any rows are currently selected
													var table = document
															.getElementById("form1:tableListaLineas");
													var disabled = (table
															.getAllSelectedRowsCount() > 0) ? false
															: true;
													// Set disabled state for top actions
													document
															.getElementById(
																	"form1:tableListaLineas:tableActionsTop:deleteTop")
															.setDisabled(
																	disabled);
													// Set disabled state for bottom actions
													document
															.getElementById(
																	"form1:tableListaLineas:tableActionsBottom:deleteBottom")
															.setDisabled(
																	disabled);
												}
												]]>
											</script>
													<ui:tableRowGroup binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.tableRowGroupListaLineas}" id="tableRowGroupListaLineas" sourceData="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.ldpLineas}" sourceVar="var">
														<ui:tableColumn align="center" binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.tableColumn1}" id="tableColumn1" valign="middle" width="10">
															<ui:radioButton binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.radioButton1}" id="radioButton1" label="" name="buttonGroup1" selected="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.seleccion}" selectedValue="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.var}" />
														</ui:tableColumn>
														<ui:tableColumn binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.tableColumn3}" headerText="Valor" id="tableColumn3" sort="valor">
															<ui:textField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.tfValorLinea}" id="tfValorLinea" text="#{var.value['valor']}"  />
														</ui:tableColumn>
														<ui:tableColumn binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.tableColumn2}" headerText="Fecha de baja" id="tableColumn2" sort="fechaBaja">
															<ui:textField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.tfFechaBaja}" id="tfFechaBaja" text="#{var.value['fechaBaja']}" converter="DateConverter"/>
														</ui:tableColumn>
													</ui:tableRowGroup>
												</ui:table>
											</div>
										</td>
									</tr>
                                    
                                    <tr><td colspan="4"><br/></td></tr>
									<tr><td align="right"><ui:label binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria"/></td>
									<td><ui:textArea binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.taComentarioLogAuditoria}" id="taComentarioLogAuditoria"/></td></tr><tr><td><br/></td></tr>
									<tr><td colspan="4"><ui:table binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.tablaLogs}" id="tbLogsAuditoria"/></td></tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap" style="height: 24px">
                                            <ui:button
                                                action="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.btnGuardar_action}"
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.btnGuardar}"
                                                id="btnGuardar" styleClass="button"/>
                                            <ui:staticText
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.stSeparador}"
                                                escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button
                                                action="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.btnCancelar_action}"
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.btnCancelar}"
                                                id="btnCancelar" styleClass="button"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.hidIdPagina}"
                                        id="hidIdPagina" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.hidIdSubSesion}"
                                        id="hidIdSubSesion" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroConstante$ABMTipoParametroConstante.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
