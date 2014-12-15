<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.page1}" id="page1">
            <ui:html binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.html1}" id="html1">
                <ui:head binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.head1}" id="head1" title="Administración de Refinanciaciones">
                    <ui:link binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.body1}" focus="form1:tfNumeroTramite" id="body1"
                         onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();" style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.form1}" id="form1">
                        <div class="divAdmin">
                            <div class="formularioABM">
                                <table border="0" class="azul">
                                    <caption>
                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.stTitulo}" id="stTitulo"
                                                       styleClass="tituloABM" text="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.head1.title}"/>
                                    </caption>
                                    <tbody>
                               
                                        <tr>
                                            <td colspan="2"></td>
                                        </tr>
                                                 <tr><td align="center">
                                                <ui:panelGroup binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.pgParametros}" id="pgParametros">
                                                    <table>
                                        <tr>
                                            <td align="right" nowrap="nowrap">
                                                <ui:label binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.label1}" for="tfNumeroTramite"
                                                          id="label1" styleClass="label" text="Nº de Refinanciación"/>
                                            </td>
                                            <td>
                                                <ui:textField binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.tfNumeroTramite}" onKeyPress="return ValidarNum(event,this)" columns="15"
                                                              id="tfNumeroTramite" styleClass="textField"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right" nowrap="nowrap">
                                                <ui:label binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.label2}" for="tfPersona" id="label2"
                                                          styleClass="label" text="Contribuyente"/>
                                            </td>
                                            <td>
                                                <ui:textField binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.tfPersona}" columns="40"
                                                              disabled="true" id="tfPersona" styleClass="textField"/>
                                                <ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnSeleccionarPersonaFisica_action}"
                                                           binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnSeleccionarPersonaFisica}" escape="false"
                                                           id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Física"/>
                                                <ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnSeleccionarPersonaJuridica_action}"
                                                           binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnSeleccionarPersonaJuridica}" escape="false"
                                                           id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Jurídica"/>
                                                <ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnLimpiarPersona_action}"
                                                           binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnLimpiarPersona}" escape="false"
                                                           id="btnLimpiarPersona" mini="true" styleClass="buttonLimpiar" text="&amp;nbsp" toolTip="Limpiar"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2"></td>
                                        </tr>
                                         </table>
                                        </ui:panelGroup>
                                        </td></tr>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td align="right" colspan="2">
                                                <a4j:commandButton binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnBuscar}" action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnBuscar_action}"
                                                                   id="btnBuscar" value="Buscar" styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()"/>
                                                <a4j:commandButton action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnReiniciar_action}"
                                                           binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1"/>

                                                <ui:staticText binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.staticText2}" escape="false"
                                                               id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                <ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnCancelar_action}"
                                                           binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnCancelar}" id="btnCancelar"
                                                           styleClass="button" text="Cancelar"/>
                                            </td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                            <div>
                                <ui:messageGroup binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.messageGroup}" id="messageGroup" showDetail="true"
                                                 showSummary="false" styleClass="grupoMsgAdmin"/>
                            </div>
                            <table>
                                <tr>
                                    <td>
                                        <ui:table binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.table1}" id="table1">
                                            <script><![CDATA[
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
                                                <ui:tableRowGroup binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.tableRowGroup1}"
                                                                  emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)" onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
                                                                  sourceData="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.ldpRefinanciacion}" sourceVar="currentRow">
                                                    <ui:tableColumn align="center" binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.tableColumn1}"
                                                                    id="tableColumn1" valign="middle" width="10">
                                                        <ui:radioButton binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.radioButton1}"
                                                                        id="radioButton1" label="" name="buttonGroup" onClick="checkUncheck(this)" 
                                                                        selected="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.RBSelected}" selectedValue="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.currentRow}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.tableColumn2}"
                                                                    headerText="Nº de Refinanciación" id="tableColumn2" sort="numeroRefinanciacion">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.staticText1}" id="staticText1" text="#{currentRow.value['numeroRefinanciacion']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.tableColumn5}"
                                                                    headerText="Contribuyente" id="tableColumn5" sort="stringPersona">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.staticText3}" id="staticText3" text="#{currentRow.value['stringPersona']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.tableColumn3}"
                                                                    headerText="Mes/Año de Inicio" id="tableColumn3" sort="stringMesAnioInicio">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.staticText8}" id="staticText8"
                                                                       text="#{currentRow.value['stringMesAnioInicio']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.tableColumn4}" headerText="Total A Pagar"
                                                                    id="tableColumn4" sort="totalAPagar">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.staticText4}" 
                                                                       converter="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.numberConverter1}" id="staticText4" text="#{currentRow.value['totalAPagar']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.tableColumn6}" headerText="Estado"
                                                                    id="tableColumn6" sort="stringEstadoRefinanciacion">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.staticText9}" id="staticText9" text="#{currentRow.value['stringEstadoRefinanciacion']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                    <f:facet name="actionsTop">
                                        <ui:panelGroup binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.groupPanel1}" id="groupPanel1" separator="&lt;br/&gt;">
                                            <ui:panelGroup binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.gpBotones}" id="gpBotones">
                                                <ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnSeleccionar_action}"
                                                           binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnSeleccionar}" id="btnSeleccionar"
                                                           styleClass="button" text="Seleccionar"/>
                                                <ui:staticText binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.staticText5}" escape="false"
                                                               id="staticText5" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                <ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnGenerar_action}"
                                                           binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnGenerar}" id="btnGenerar"
                                                           styleClass="button" text="Generar"/>
                                                <ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnModificar_action}"
                                                           binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnModificar}" id="btnModificar"
                                                           styleClass="button" text="Modificar" disabled="true"/>
                                                <ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnEliminar_action}"
                                                           binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnEliminar}" disabled="true"
                                                           id="btnEliminar" styleClass="button" text="Eliminar"/>
                                                <ui:staticText binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.staticText7}" escape="false"
                                                               id="staticText7" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                <ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnConsultar_action}"
                                                           binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnConsultar}" id="btnConsultar"
                                                           styleClass="button" text="Consultar"/>
                                                <ui:staticText binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.staticText10}" escape="false" id="staticText10" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                <ui:button action="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnExportar_action}" binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.btnExportar}" id="btnExportar" styleClass="button" text="Exportar" onClick="return exportarReporte()"/>
                                            </ui:panelGroup>
                                        </ui:panelGroup>
                                    </f:facet>
                                </ui:table>
                                </td>
                                </tr>
                            </table>
                        </div>
                        <script>
                    document.getElementById('form1:tfNumeroTramite').focus();
                        </script>
                        <ui:hiddenField binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.hidIdPagina}" id="hidIdPagina" text="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.idPagina}"/>
                        <ui:hiddenField binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.hidIdSubSesion}" id="hidIdSubSesion" text="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.idSubSesion}"/>
                        <ui:script binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js"/>
                        <ui:script binding="#{excepciones$ABMRefinanciacion$AdminRefinanciacion.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
