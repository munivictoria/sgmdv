<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.head1}" id="head1" title="Consultar FichaSocial">
                    <ui:link binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.body1}" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242,242,242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                    <ui:form binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label4}" for="tfCodigo" id="label4" styleClass="label" text="Número"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tfCodigo}" id="tfCodigo" styleClass="textFieldDisabled" disabled="true"/>
                                        </td> 
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label23}" for="tfFecha" id="label23" styleClass="label" text="Fecha"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tfFecha}" id="tfFecha" styleClass="textFieldDisabled" disabled="true" columns="10"/>
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
                                            <ui:tabSet binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tabSet1}">
                                                <ui:tab id ="One" text="Beneficiarios">
                                                    <table>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label1}" for="tfTitular" id="label1" styleClass="label" text="Titular"/>
                                                            </td>  
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tfTitular}" columns="50" id="tfTitular" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <br/>
                                                            </td>
                                                        </tr>    
                                                        <tr>
                                                            <td nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label22}" id="label22" styleClass="label" text="Grupo Familiar"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="4">
                                                                <ui:table augmentTitle="false" binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.table1}" id="table1" width="283">
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
                                                                        <ui:tableRowGroup binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tableRowGroup1}" id="tableRowGroup1" 
                                                                                          sourceData="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.ldpGrupoFamiliar}" sourceVar="currentRow">

                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tableColumn2}"
                                                                                        headerText="Persona" id="tableColumn2">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.staticText1}" id="staticText1" text="#{currentRow.value['persona']}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tableColumn9}"
                                                                                        headerText="Obra Social" id="tableColumn9">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.staticText7}" id="staticText7" text="#{currentRow.value['obraSocial']}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tableColumn10}"
                                                                                        headerText="Vinculo" id="tableColumn10">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.staticText8}" id="staticText8" text="#{currentRow.value['vinculo']}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tableColumn11}"
                                                                                        headerText="Instrucción" id="tableColumn11">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.staticText9}" id="staticText9" text="#{currentRow.value['instruccion']}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tableColumn3}" headerText="Ocupación" id="tableColumn3">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.staticText2}" id="staticText2" text="#{currentRow.value['ocupacion']}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tableColumn4}" headerText="Ingresos" id="tableColumn4">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.staticText3}" id="staticText3" text="#{currentRow.value['ingresos']}"/>
                                                                        </ui:tableColumn>                       
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tableColumn12}" headerText="Salud" id="tableColumn12">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.staticText10}" id="staticText10" text="#{currentRow.value['salud']}"/>
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
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label2}" for="tfNroPersonas" id="label2" styleClass="label" text="Nro. de Personas"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tfNroPersonas}" columns="40" id="tfNroPersonas" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label3}" for="tfVivienda" id="label3" styleClass="label" text="Vivienda"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tfVivienda}" columns="40" id="tfVivienda" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label5}" for="tfTenencia" id="label5" styleClass="label" text="Tenencia"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tfTenencia}" columns="40" id="tfTenencia" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label6}" for="tfNroCamas" id="label6" styleClass="label" text="Nro. de Camas"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tfNroCamas}" columns="40" id="tfNroCamas" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label7}" for="tfNroAmbientes" id="label7" styleClass="label" text="Nro. de Ambientes"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tfNroAmbientes}" columns="40" id="tfNroAmbientes" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label8}" for="cbBanioCompleto" id="label8" styleClass="label" text="Baño Completo"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.cbBanioCompleto}" id="cbBanioCompleto" disabled="true"/>
                                                            </td>    
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label9}" for="cbBanioInterno" id="label9" styleClass="label" text="Baño Interno"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.cbBanioInterno}" id="cbBanioInterno" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label10}" for="cbAgua" id="label10" styleClass="label" text="Agua"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.cbAgua}" id="cbAgua" disabled="true"/>
                                                            </td>    
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label11}" for="cbLuz" id="label11" styleClass="label" text="Luz"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.cbLuz}" id="cbLuz" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label12}" for="cbCloaca" id="label12" styleClass="label" text="Cloaca"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.cbCloaca}" id="cbCloaca" disabled="true"/>
                                                            </td>    
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label13}" for="cbGasNatural" id="label13" styleClass="label" text="Gas Natural"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.cbGasNatural}" id="cbGasNatural" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </ui:tab>
                                                <ui:tab id="Three" text="Aspecto Económico">
                                                    <table>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label14}" for="tfNroCasas" id="label14" styleClass="label" text="Nro. de Casas"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tfNroCasas}" columns="40" id="tfNroCasas" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label15}" for="tfNroTerrenos" id="label15" styleClass="label" text="Nro. de Terrenos"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tfNroTerrenos}" columns="40" id="tfNroTerrenos" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label16}" for="tfNroCampos" id="label16" styleClass="label" text="Nro. de Campos"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tfNroCampos}" columns="40" id="tfNroCampos" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label17}" for="tfVehiculo" id="label17" styleClass="label" text="Vehículo"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tfVehiculo}" columns="40" id="tfVehiculo" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label18}" for="tfIndustria" id="label18" styleClass="label" text="Industria"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tfIndustria}" columns="40" id="tfIndustria" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label19}" for="tfActividadLaboral" id="label19" styleClass="label" text="Actividad Laboral"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tfActividadLaboral}" columns="40" id="tfActividadLaboral" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.label20}" for="tfComercio" id="label20" styleClass="label" text="Comercio"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tfComercio}" columns="40" id="tfComercio" styleClass="textFieldDisabled" disabled="true"/>
                                                            </td>
                                                        </tr>                                                        
                                                    </table>
                                                </ui:tab>
                                                <ui:tab id="four" text="Solicitud de Beneficio">
                                                    <table>
                                                        <tr>
                                                            <td colspan="4">
                                                                <ui:table augmentTitle="false" binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.table2}" id="table2" width="283">
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
                                                                        <ui:tableRowGroup binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tableRowGroup2}" id="tableRowGroup2"
                                                                                          sourceData="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.ldpSolicitudesBeneficios}" sourceVar="currentRow">

                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tableColumn13}"
                                                                                        headerText="Beneficio" id="tableColumn13">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.staticText11}" id="staticText11" text="#{currentRow.value['beneficio']}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tableColumn14}"
                                                                                        headerText="Fecha Alta" id="tableColumn14">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.staticText12}" id="staticText12" text="#{currentRow.value['fechaAlta']}" converter="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.dateTimeConverter1}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tableColumn8}" headerText="Cantidad" id="tableColumn8">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.staticText6}" id="staticText6" text="#{currentRow.value['cantidad']}"/>
                                                                        </ui:tableColumn> 
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tableColumn15}" headerText="Monto" id="tableColumn15">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.staticText13}" id="staticText13" text="#{currentRow.value['monto']}"/>
                                                                        </ui:tableColumn> 
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn17}" headerText="Descripción" id="tableColumn17">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText15}" id="staticText15" text="#{currentRow.value['descripcion']}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tableColumn7}" headerText="Fecha Finalización" id="tableColumn7">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.staticText5}" id="staticText5" text="#{currentRow.value['fechaFinalizacion']}" converter="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.dateTimeConverter1}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn16}" headerText="Comentario de Finalización" id="tableColumn16">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText14}" id="staticText14" text="#{currentRow.value['comentarioFinalizacion']}"/>
                                                                        </ui:tableColumn>
                                                                        <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.tableColumn6}"
                                                                                        headerText="Estado" id="tableColumn6">
                                                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.staticText4}" id="staticText4" text="#{currentRow.value['estado']}"/>
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
                                        <ui:messageGroup binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.btnVolver_action}"
                                                       binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.btnVolver}" id="btnVolver" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.idSubSesion}"/>
                        <ui:script binding="#{accionsocial$ABMFichaSocial$ConsultarFichaSocial.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
