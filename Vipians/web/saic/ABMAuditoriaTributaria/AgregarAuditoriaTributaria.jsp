<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.page1}" id="page1">
            <ui:html binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.html1}" id="html1">
                <ui:head binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.head1}" id="head1" title="Agregar Auditoria Tributaria">
                    <ui:link binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.link1}" id="link1" url="/resources/stylesheet.css"/>
                     <script>
                      <![CDATA[
                        function limpiarPersona(){
                            tfContribuyente = document.getElementById("form1:tfContribuyente");
                            
                            tfContribuyente.value="";                          
                            
                         }
                     ]]></script>
                
                </ui:head>
                <ui:body binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.body1}" focus="form1:tfFecha" id="body1"
                         onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.stTitulo}" id="stTitulo"
                                                   styleClass="tituloABM" text="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.lblFecha}" id="lblFecha" styleClass="label" text="Fecha"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tfFecha}" id="tfFecha" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.staticText18}" escape="false"
                                                           id="staticText18" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.label2}" for="ddTipoObligacion" id="label2"
                                                      styleClass="label" text="Tipo de Obligación"/>
                                        </td>
                                        <td>
                                            <ui:dropDown binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.ddTipoObligacion}" id="ddTipoObligacion" onChange="limpiarPersona()"
                                                         styleClass="textField" items="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.ddTipoObligacionDefaultOptions.options}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.label4}" for="tfContribuyente" id="label4"
                                                      styleClass="label" text="Contribuyente"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tfContribuyente}" columns="80"
                                                          disabled="true" id="tfContribuyente" styleClass="textField"/>
                                            <ui:button action="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnSeleccionarPersonaFisica_action}"
                                                       binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnSeleccionarPersonaFisica}" escape="false"
                                                       id="btnSeleccionarPersonaFisica" mini="true" styleClass="button" text="PF" toolTip="Seleccionar Persona Física"/>
                                            <ui:button action="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnSeleccionarPersonaJuridica_action}"
                                                       binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnSeleccionarPersonaJuridica}" escape="false"
                                                       id="btnSeleccionarPersonaJuridica" mini="true" styleClass="button" text="PJ" toolTip="Seleccionar Persona Jurídica"/>
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
                                            <ui:label binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.label1}" id="label1" styleClass="label2" text="Obligaciones del Contribuyente"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:table augmentTitle="false" binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.table1}" id="table1" width="10">
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
                                                    <ui:tableRowGroup binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableRowGroup1}"
                                                                      id="tableRowGroup1"
                                                                      selected="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.currentRowSelected}"
                                                                      sourceData="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.ldpObligaciones}" sourceVar="currentRow">
                                                        <ui:tableColumn align="center" binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn7}"
                                                                        id="tableColumn7" valign="middle" width="10">
                                                            <ui:checkbox binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.checkbox4}" id="checkbox4"
                                                                         selected="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.selected}" selectedValue="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.selectedValue}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn2}"
                                                                        headerText="Nº Tramite" id="tableColumn2" noWrap="true" sort="numeroTramite">
                                                            <ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.staticText1}"
                                                                           id="staticText1" text="#{currentRow.value['numeroTramite']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn1}"
                                                                        headerText="Documento" id="tableColumn1" sort="documentoEspecializado">
                                                            <ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.staticText2}"
                                                                           id="staticText2" text="#{currentRow.value['documentoEspecializado']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn3}"
                                                                        headerText="Estado" id="tableColumn3" sort="estado">
                                                            <ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.staticText3}"
                                                                           id="staticText3" text="#{currentRow.value['estado']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                    <f:facet name="actionsTop">
                                                        <ui:panelGroup binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.groupPanel1}" id="groupPanel1">
                                                            <ui:button action="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnVerPeriodos_action}"
                                                                       binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnVerPeriodos}" id="btnVerPeriodos"
                                                                       styleClass="button" text="Ver Períodos Adeudados"/>
                                                        </ui:panelGroup>
                                                    </f:facet>
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
                                            <ui:label binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.label3}" id="label3" styleClass="label2" text="Períodos Adeudados"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:table augmentTitle="false" binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.table2}" id="table2" width="72">
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
                                                    <ui:tableRowGroup binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableRowGroup2}"
                                                                      id="tableRowGroup2"
                                                                      selected="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.currentRowSelectedTabla2}"
                                                                      sourceData="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.ldpPeriodosAdeudados}" sourceVar="currentRow">
                                                        <ui:tableColumn align="center"
                                                                        binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn15}" id="tableColumn15"
                                                                        valign="middle" width="10">
                                                            <ui:checkbox binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.checkbox1}" id="checkbox1"
                                                                         selected="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.selectedTabla2}" selectedValue="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.selectedValueTabla2}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn16}"
                                                                        headerText="Período" id="tableColumn16" noWrap="true" sort="nombre">
                                                            <ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.staticText11}"
                                                                           id="staticText11" text="#{currentRow.value['nombre']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn12}"
                                                                        headerText="Monto" id="tableColumn12" sort="monto">
                                                            <ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.staticText10}"
                                                                           converter="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.numberConverter1}"
                                                                           id="staticText10" text="#{currentRow.value['monto']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn9}"
                                                                        headerText="Interés" id="tableColumn9" sort="interes">
                                                            <ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.staticText7}"
                                                                           converter="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.numberConverter1}"
                                                                           id="staticText7" text="#{currentRow.value['interes']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn4}"
                                                                        headerText="Recargo" id="tableColumn4" sort="recargo">
                                                            <ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.staticText4}"
                                                                           converter="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.numberConverter1}"
                                                                           id="staticText4" text="#{currentRow.value['recargo']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn5}"
                                                                        headerText="Multas" id="tableColumn5" sort="multas">
                                                            <ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.staticText5}"
                                                                           converter="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.numberConverter1}"
                                                                           id="staticText5" text="#{currentRow.value['multas']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn11}"
                                                                        headerText="Fecha Vencimiento" id="tableColumn11" sort="fechaVencimiento">
                                                            <ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.staticText9}"
                                                                           converter="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.dateTimeConverter}"
                                                                           id="staticText9" text="#{currentRow.value['fechaVencimiento']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                <!-- <f:facet name="actionsTop">
                                                    <ui:panelGroup binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.groupPanel2}" id="groupPanel2">
                                                        <ui:button action="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnArmarPlanDePago_action}"
                                                                   binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnArmarPlanDePago}"
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
                                            <ui:label binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.label5}" id="label5" styleClass="label2" text="Intimaciones"/>
                                        </td>
                                    </tr>

                                    <tr>

                                        <td colspan="2">

                                            <ui:table augmentTitle="false" binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.table3}" id="table3" width="72">                                                
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

                                                <ui:tableRowGroup binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableRowGroup3}" emptyDataMsg="Ningún registro encontrado." id="tableRowGroup3" sourceData="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.ldpIntimaciones}" sourceVar="currentRow">

                                                    <ui:tableColumn align="center" binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn17}" id="tableColumn17" valign="middle" width="10">
                                                        <ui:radioButton binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.radioButton1}" id="radioButton1" label="" name="buttonGroup" selected="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.RBSelected}" selectedValue="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.currentRow}"/>
                                                    </ui:tableColumn>

                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn18}" headerText="Fecha de Emisión" id="tableColumn18" noWrap="true" sort="fechaEmision">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.staticText12}" 
                                                                       converter="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.dateTimeConverter}"
                                                                       id="staticText12" text="#{currentRow.value['fechaEmision']}"/>
                                                    </ui:tableColumn>

                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn19}" headerText="Fecha Recepción" id="tableColumn19" sort="fechaRecepción">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.staticText13}"
                                                                       converter="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.dateTimeConverter}"
                                                                       id="staticText13" text="#{currentRow.value['fechaRecepcion']}"/>
                                                    </ui:tableColumn>

                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn20}" headerText="Causa" id="tableColumn20" sort="causa">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.staticText14}" id="staticText14" text="#{currentRow.value['causa']}"/>
                                                    </ui:tableColumn>

                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn21}" headerText="Estado" id="tableColumn21" sort="estado">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.staticText15}" id="staticText15" text="#{currentRow.value['estado']}"/>
                                                    </ui:tableColumn>

                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn22}" headerText="Periodo Validez" id="tableColumn22" sort="periodoValidez">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.staticText16}" id="staticText16" text="#{currentRow.value['periodoValidez']}"/>
                                                    </ui:tableColumn>

                                                    <ui:tableColumn binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.tableColumn23}" headerText="Observaciones" id="tableColumn23" sort="observaciones">
                                                        <ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.staticText17}" id="staticText17" text="#{currentRow.value['observaciones']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>

                                                <f:facet name="actionsTop">

                                                    <ui:panelGroup binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.groupPanel3}" id="groupPanel3">
                                                        <ui:button action="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnAgregarIntimacion_action}" binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnAgregarIntimacion}" id="btnAgregarIntimacion" styleClass="button" text="Agregar"/>
                                                        <ui:button action="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnModificarIntimacion_action}" binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnModificarIntimacion}" id="btnModificarIntimacion" styleClass="button" text="Modificar"/>
                                                        <ui:button action="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnQuitar_action}" binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnQuitar}" id="btnQuitar" styleClass="button" text="Quitar"/>
                                                    </ui:panelGroup>
                                                </f:facet>
                                            </ui:table>
                                        </td>
                                    </tr>





                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>

                                        <td align="right" colspan="6" nowrap="true">
                                            <ui:button action="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnGuardar_action}" binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnCancelar_action}" binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                            <br/>
                            <br/>
                            <br/>
                        </div>
                        <ui:hiddenField binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.hidIdPagina}" id="hidIdPagina" text="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.idPagina}"/>
                        <ui:hiddenField binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.hidIdSubSesion}" id="hidIdSubSesion" text="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.idSubSesion}"/>
                        <ui:script binding="#{saic$ABMAuditoriaTributaria$AgregarAuditoriaTributaria.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
