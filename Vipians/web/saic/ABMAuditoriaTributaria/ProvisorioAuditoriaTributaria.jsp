<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.page1}" id="page1">
            <ui:html binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.html1}" id="html1">
                <ui:head binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.head1}" id="head1" title="Auditoría Tributaria en Provisorio">
                    <ui:link binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.body1}" focus="form1:tfFecha" id="body1"
                         onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.stTitulo}" id="stTitulo"
                                                   styleClass="tituloABM" text="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.lblFecha}" id="lblFecha" styleClass="label" text="Fecha Desde"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tfFecha}" id="tfFecha" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:staticText binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.staticText18}" escape="false"
                                                           id="staticText18" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.label2}" for="ddTipoObligacion" id="label2"
                                                      styleClass="label" text="Tipo de Obligación"/>
                                        </td>
                                        <td>
                                            <ui:dropDown binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.ddTipoObligacion}" id="ddTipoObligacion"
                                                         styleClass="textField" disabled="true" items="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.ddTipoObligacionDefaultOptions.options}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.label4}" for="tfContribuyente" id="label4"
                                                      styleClass="label" text="Contribuyente"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tfContribuyente}" columns="80"
                                                          disabled="true" id="tfContribuyente" styleClass="textField"/>

                                        </td>
                                    </tr>                                   
                                    <tr>
                                        <td align="right" nowrap="nowrap"></td>
                                        <td align="right" nowrap="nowrap"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <hr/>
                                            <br/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td colspan="2">
                                            <ui:label binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.label3}" id="label3" styleClass="label2" text="Períodos Adeudados"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:table augmentTitle="false" binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.table2}" id="table2" width="72">
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
                                                    <ui:tableRowGroup binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tableRowGroup2}"
                                                                      id="tableRowGroup2"
                                                                      selected="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.currentRowSelectedTabla2}"
                                                                      sourceData="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.ldpPeriodosAdeudados}" sourceVar="currentRow">
                                                    <!-- <ui:tableColumn align="center"
                                                                    binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tableColumn15}" id="tableColumn15"
                                                                    valign="middle" width="10">
                                                        <ui:checkbox binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.checkbox1}" id="checkbox1"
                                                                     selected="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.selectedTabla2}" selectedValue="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.selectedValueTabla2}"/>
                                                    </ui:tableColumn> -->
                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tableColumn16}"
                                                                    headerText="Período" id="tableColumn16" noWrap="true" sort="nombre">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.staticText11}"
                                                                       id="staticText11" text="#{currentRow.value['nombre']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tableColumn12}"
                                                                    headerText="Monto" id="tableColumn12" sort="monto">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.staticText10}"
                                                                       converter="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.numberConverter1}"
                                                                       id="staticText10" text="#{currentRow.value['monto']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tableColumn9}"
                                                                    headerText="Interés" id="tableColumn9" sort="interes">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.staticText7}"
                                                                       converter="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.numberConverter1}"
                                                                       id="staticText7" text="#{currentRow.value['interes']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tableColumn4}"
                                                                    headerText="Recargo" id="tableColumn4" sort="recargo">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.staticText4}"
                                                                       converter="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.numberConverter1}"
                                                                       id="staticText4" text="#{currentRow.value['recargo']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tableColumn5}"
                                                                    headerText="Multas" id="tableColumn5" sort="multas">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.staticText5}"
                                                                       converter="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.numberConverter1}"
                                                                       id="staticText5" text="#{currentRow.value['multas']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tableColumn11}"
                                                                    headerText="Fecha Vencimiento" id="tableColumn11" sort="fechaVencimiento">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.staticText9}"
                                                                       converter="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.dateTimeConverter}"
                                                                       id="staticText9" text="#{currentRow.value['fechaVencimiento']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                                <!-- <f:facet name="actionsTop">
                                                    <ui:panelGroup binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.groupPanel2}" id="groupPanel2">
                                                        <ui:button action="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.btnArmarPlanDePago_action}"
                                                                   binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.btnArmarPlanDePago}"
                                                                   id="btnArmarPlanDePago" styleClass="button" text="Armar Plan de Pago"/>
                                                    </ui:panelGroup>
                                                </f:facet>
                                                -->
                                            </ui:table>
                                        </td>
                                    </tr>

                                    <tr>

                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>

                                    <tr>

                                        <td colspan="2">
                                            <ui:label binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.label5}" id="label5" styleClass="label2" text="Intimaciones"/>
                                        </td>
                                    </tr>

                                    <tr>

                                        <td colspan="2">

                                            <ui:table augmentTitle="false" binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.table3}" id="table3" width="72">                                                
                                                <script><![CDATA[
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

                                                <ui:tableRowGroup binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tableRowGroup3}" emptyDataMsg="Ningún registro encontrado." id="tableRowGroup3" sourceData="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.ldpIntimaciones}" sourceVar="currentRow">

                                                    <!-- 
                                                    <ui:tableColumn align="center" binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tableColumn17}" id="tableColumn17" valign="middle" width="10">
                                                        <ui:radioButton binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.radioButton1}" id="radioButton1" label="" name="buttonGroup" selected="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.RBSelected}" selectedValue="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.currentRow}"/>
                                                    </ui:tableColumn>
                                                    -->

                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tableColumn18}" headerText="Fecha de Emisión" id="tableColumn18" noWrap="true" sort="fechaEmision">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.staticText12}" 
                                                                       converter="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.dateTimeConverter}"
                                                                       id="staticText12" text="#{currentRow.value['fechaEmision']}"/>
                                                    </ui:tableColumn>

                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tableColumn19}" headerText="Fecha Recepción" id="tableColumn19" sort="fechaRecepción">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.staticText13}"
                                                                       converter="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.dateTimeConverter}"
                                                                       id="staticText13" text="#{currentRow.value['fechaRecepcion']}"/>
                                                    </ui:tableColumn>

                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tableColumn20}" headerText="Causa" id="tableColumn20" sort="causa">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.staticText14}" id="staticText14" text="#{currentRow.value['causa']}"/>
                                                    </ui:tableColumn>

                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tableColumn21}" headerText="Estado" id="tableColumn21" sort="estado">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.staticText15}" id="staticText15" text="#{currentRow.value['estado']}"/>
                                                    </ui:tableColumn>

                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tableColumn22}" headerText="Periodo Validez" id="tableColumn22" sort="periodoValidez">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.staticText16}" id="staticText16" text="#{currentRow.value['periodoValidez']}"/>
                                                    </ui:tableColumn>

                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.tableColumn23}" headerText="Observaciones" id="tableColumn23" sort="observaciones">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.staticText17}" id="staticText17" text="#{currentRow.value['observaciones']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>

                                        <td align="right" colspan="6" nowrap="true">
                                            <ui:button action="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.btnGuardar_action}" binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.btnGuardar}" id="btnGuardar" styleClass="button" text="Provisorio"/>
                                            <ui:staticText binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.btnCancelar_action}" binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                            <br/>
                            <br/>
                            <br/>
                        </div>
                        <ui:hiddenField binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.hidIdPagina}" id="hidIdPagina" text="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.idPagina}"/>
                        <ui:hiddenField binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.hidIdSubSesion}" id="hidIdSubSesion" text="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.idSubSesion}"/>
                        <ui:script binding="#{saic$ABMAuditoriaTributaria$ProvisorioAuditoriaTributaria.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
