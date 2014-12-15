<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.head1}" id="head1" title="Eliminar FichaSocial">
                    <ui:link binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.body1}" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 236, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="rojo">
                                <caption>
                                    <ui:staticText binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label4}" for="tfCodigo" id="label4" styleClass="label" text="Número"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tfCodigo}" id="tfCodigo" styleClass="textFieldDisabled" disabled="true"/>
                                        </td> 
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label23}" for="tfFecha" id="label23" styleClass="label" text="Fecha"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tfFecha}" id="tfFecha" styleClass="textFieldDisabled" disabled="true" columns="10"/>
                                            <!--<ui:label id="lblFecha" styleClass="label" text="[dd/mm/aaaa]"/>-->
                                        </td>  
                                    </tr>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center" colspan="4">
                                            <ui:tabSet binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tabSet1}">
                                                <ui:tab id ="One" text="Beneficiarios">
                                                    <table>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label1}" for="tfTitular" id="label1" styleClass="label" text="Titular"/>
                                                            </td>  
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tfTitular}" columns="50" id="tfTitular" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <br/>
                                                            </td>
                                                        </tr>    
                                                        <tr>
                                                            <td nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label22}" id="label22" styleClass="label" text="Grupo Familiar"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="4">
                                                                <ui:table augmentTitle="false" binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.table1}" id="table1" width="283">
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
                                                                        <ui:tableRowGroup binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tableRowGroup1}" id="tableRowGroup1"
                                                                                          sourceData="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.ldpGrupoFamiliar}" sourceVar="currentRow">

                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tableColumn2}"
                                                                                        headerText="Persona" id="tableColumn2">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.staticText1}" id="staticText1" text="#{currentRow.value['persona']}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tableColumn9}"
                                                                                        headerText="Obra Social" id="tableColumn9">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.staticText7}" id="staticText7" text="#{currentRow.value['obraSocial']}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tableColumn10}"
                                                                                        headerText="Vinculo" id="tableColumn10">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.staticText8}" id="staticText8" text="#{currentRow.value['vinculo']}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tableColumn11}"
                                                                                        headerText="Instrucción" id="tableColumn11">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.staticText9}" id="staticText9" text="#{currentRow.value['instruccion']}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tableColumn3}" headerText="Ocupación" id="tableColumn3">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.staticText2}" id="staticText2" text="#{currentRow.value['ocupacion']}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tableColumn4}" headerText="Ingresos" id="tableColumn4">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.staticText3}" id="staticText3" text="#{currentRow.value['ingresos']}"/>
                                                                        </ui:tableColumn>                       
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tableColumn12}" headerText="Salud" id="tableColumn12">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.staticText10}" id="staticText10" text="#{currentRow.value['salud']}"/>
                                                                        </ui:tableColumn>  
                                                                    </ui:tableRowGroup>                                                                      
                                                                </ui:table>
                                                            </td>
                                                        </tr>         
                                                    </table>   
                                                </ui:tab>    
                                                <ui:tab id="Two" text="Aspecto Habitacional">
                                                    <table>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label2}" for="tfNroPersonas" id="label2" styleClass="label" text="Nro. de Personas"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tfNroPersonas}" columns="40" id="tfNroPersonas" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label3}" for="tfVivienda" id="label3" styleClass="label" text="Vivienda"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tfVivienda}" columns="40" id="tfVivienda" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label5}" for="tfTenencia" id="label5" styleClass="label" text="Tenencia"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tfTenencia}" columns="40" id="tfTenencia" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label6}" for="tfNroCamas" id="label6" styleClass="label" text="Nro. de Camas"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tfNroCamas}" columns="40" id="tfNroCamas" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label7}" for="tfNroAmbientes" id="label7" styleClass="label" text="Nro. de Ambientes"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tfNroAmbientes}" columns="40" id="tfNroAmbientes" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label8}" for="cbBanioCompleto" id="label8" styleClass="label" text="Baño Completo"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.cbBanioCompleto}" id="cbBanioCompleto" disabled="true"/>
                                                            </td>    
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label9}" for="cbBanioInterno" id="label9" styleClass="label" text="Baño Interno"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.cbBanioInterno}" id="cbBanioInterno" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label10}" for="cbAgua" id="label10" styleClass="label" text="Agua"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.cbAgua}" id="cbAgua" disabled="true"/>
                                                            </td>    
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label11}" for="cbLuz" id="label11" styleClass="label" text="Luz"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.cbLuz}" id="cbLuz" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label12}" for="cbCloaca" id="label12" styleClass="label" text="Cloaca"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.cbCloaca}" id="cbCloaca" disabled="true"/>
                                                            </td>    
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label13}" for="cbGasNatural" id="label13" styleClass="label" text="Gas Natural"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.cbGasNatural}" id="cbGasNatural" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </ui:tab>
                                                <ui:tab id="Three" text="Aspecto Económico">
                                                    <table>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label14}" for="tfNroCasas" id="label14" styleClass="label" text="Nro. de Casas"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tfNroCasas}" columns="40" id="tfNroCasas" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label15}" for="tfNroTerrenos" id="label15" styleClass="label" text="Nro. de Terrenos"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tfNroTerrenos}" columns="40" id="tfNroTerrenos" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label16}" for="tfNroCampos" id="label16" styleClass="label" text="Nro. de Campos"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tfNroCampos}" columns="40" id="tfNroCampos" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label17}" for="tfVehiculo" id="label17" styleClass="label" text="Vehículo"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tfVehiculo}" columns="40" id="tfVehiculo" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label18}" for="tfIndustria" id="label18" styleClass="label" text="Industria"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tfIndustria}" columns="40" id="tfIndustria" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label19}" for="tfActividadLaboral" id="label19" styleClass="label" text="Actividad Laboral"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tfActividadLaboral}" columns="40" id="tfActividadLaboral" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.label20}" for="tfComercio" id="label20" styleClass="label" text="Comercio"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tfComercio}" columns="40" id="tfComercio" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>                                                        
                                                    </table>
                                                </ui:tab>
                                                <ui:tab id="four" text="Solicitud de Beneficio">
                                                    <table>
                                                        <tr>
                                                            <td colspan="4">
                                                                <ui:table augmentTitle="false" binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.table2}" id="table2" width="283">
                                                                    <script><![CDATA[
                                                                /* ----- Functions for Table Preferences Panel ----- */
                                                                /*
                                                                 * Toggle the table preferences panel open or closed
                                                                 */
                                                                function togglePreferencesPanel() {
                                                                    var table = document.getElementById("form1:table2");
                                                                    table.toggleTblePreferencesPanel();
                                                                }
                                                                /* ----- Functions for Filter Panel ----- */
                                                                /*
                                                                 * Return true if the filter menu has actually changed,
                                                                 * so the corresponding event should be allowed to continue.
                                                                 */
                                                                function filterMenuChanged() {
                                                                    var table = document.getElementById("form1:table2");
                                                                    return table.filterMenuChanged();
                                                                }
                                                                /*
                                                                 * Toggle the custom filter panel (if any) open or closed.
                                                                 */
                                                                function toggleFilterPanel() {
                                                                    var table = document.getElementById("form1:table2");
                                                                    return table.toggleTableFilterPanel();
                                                                }
                                                                /* ----- Functions for Table Actions ----- */
                                                                /*
                                                                 * Initialize all rows of the table when the state
                                                                 * of selected rows changes.
                                                                 */
                                                                function initAllRows() {
                                                                    var table = document.getElementById("form1:table2");
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
                                                                    var table = document.getElementById("form1:table2");
                                                                    table.selectGroupRows(rowGroupId, selected);
                                                                }
                                                                /*
                                                                 * Disable all table actions if no rows have been selected.
                                                                 */
                                                                function disableActions() {
                                                                    // Determine whether any rows are currently selected
                                                                    var table = document.getElementById("form1:table2");
                                                                    var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
                                                                    // Set disabled state for top actions
                                                                    document.getElementById("form1:table2:tableActionsTop:deleteTop").setDisabled(disabled);
                                                                    // Set disabled state for bottom actions
                                                                    document.getElementById("form1:table2:tableActionsBottom:deleteBottom").setDisabled(disabled);
                                                                }]]></script>
                                                                        <ui:tableRowGroup binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tableRowGroup2}" id="tableRowGroup2"
                                                                                          sourceData="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.ldpSolicitudesBeneficios}" sourceVar="currentRow">

                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tableColumn13}"
                                                                                        headerText="Beneficio" id="tableColumn13">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.staticText11}" id="staticText11" text="#{currentRow.value['beneficio']}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tableColumn14}"
                                                                                        headerText="Fecha Alta" id="tableColumn14">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.staticText12}" id="staticText12" text="#{currentRow.value['fechaAlta']}" converter="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.dateTimeConverter1}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tableColumn8}" headerText="Cantidad" id="tableColumn8">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.staticText6}" id="staticText6" text="#{currentRow.value['cantidad']}"/>
                                                                        </ui:tableColumn> 
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tableColumn15}" headerText="Monto" id="tableColumn15">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.staticText13}" id="staticText13" text="#{currentRow.value['monto']}"/>
                                                                        </ui:tableColumn> 
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn17}" headerText="Descripción" id="tableColumn17">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText15}" id="staticText15" text="#{currentRow.value['descripcion']}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tableColumn7}" headerText="Fecha Finalización" id="tableColumn7">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.staticText5}" id="staticText5" text="#{currentRow.value['fechaFinalizacion']}" converter="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.dateTimeConverter1}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn16}" headerText="Comentario de Finalización" id="tableColumn16">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText14}" id="staticText14" text="#{currentRow.value['comentarioFinalizacion']}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.tableColumn6}"
                                                                                        headerText="Estado" id="tableColumn6">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.staticText4}" id="staticText4" text="#{currentRow.value['estado']}"/>
                                                                        </ui:tableColumn>
                                                                    </ui:tableRowGroup>                                                                     
                                                                </ui:table>
                                                            </td>
                                                        </tr>         
                                                    </table>    
                                                </ui:tab>    
                                            </ui:tabSet>
                                        </td>
                                    </tr>
                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.btnEliminar_action}"
                                                       binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar"/>
                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.btnCancelar_action}"
                                                       binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.idSubSesion}"/>
                        <ui:script binding="#{accionsocial$ABMFichaSocial$EliminarFichaSocial.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
