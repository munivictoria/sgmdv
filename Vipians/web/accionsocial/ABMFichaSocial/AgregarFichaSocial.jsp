<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.head1}" id="head1" title="Agregar FichaSocial">
                    <ui:link binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.body1}" focus="form1:tfCodigo" id="body1" onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label4}" for="tfCodigo" id="label4" styleClass="label" text="Número"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tfCodigo}" onKeyPress="return ValidarNum(event,this)" id="tfCodigo" maxLength="9" styleClass="textField"/>
                                        </td> 
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label23}" for="tfFecha" id="label23" styleClass="label" text="Fecha"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tfFecha}" id="tfFecha" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
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
                                            <ui:tabSet binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tabSet1}">
                                                <ui:tab id ="one" text="Beneficiarios">
                                                    <table>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label1}" for="tfTitular" id="label1" styleClass="label" text="Titular"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tfTitular}" columns="50" disabled="true"
                                                                              id="tfTitular" styleClass="textField"/>
                                                                <ui:button action="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnAgregarTitular_action}"                                                                          
                                                                           binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnAgregarTitular}"
                                                                           escape="false" mini="true" text="&amp;nbsp;" styleClass="buttonAgregar"/>
                                                                <ui:button action="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnModificarTitular_action}"                                                                          
                                                                           binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnModificarTitular}"
                                                                           escape="false" mini="true" text="&amp;nbsp;" styleClass="buttonModificar"/>
                                                                <ui:button action="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnEliminarTitular_action}" 
                                                                           binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnEliminarTitular}"
                                                                           escape="false" mini="true" styleClass="buttonRemove" text="&amp;nbsp;"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <br/>
                                                            </td>
                                                        </tr>    
                                                        <tr>
                                                            <td nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label22}" id="label22" styleClass="label" text="Grupo Familiar"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="4">
                                                                <ui:table augmentTitle="false" binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.table1}" id="table1" width="283">
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
                                                                        <ui:tableRowGroup binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableRowGroup1}" id="tableRowGroup1"
                                                                                          sourceData="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.ldpGrupoFamiliar}" sourceVar="currentRow">
                                                                            <ui:tableColumn align="center" binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn1}"
                                                                                            id="tableColumn1" valign="middle" width="10">
                                                                                <ui:radioButton binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.radioButton1}" id="radioButton1"
                                                                                                label="" name="buttonGroup1" selected="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.RBSelected1}" selectedValue="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.currentRow1}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn2}"
                                                                                            headerText="Persona" id="tableColumn2">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText1}" id="staticText1" text="#{currentRow.value['persona']}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn9}"
                                                                                            headerText="Obra Social" id="tableColumn9">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText7}" id="staticText7" text="#{currentRow.value['obraSocial']}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn10}"
                                                                                            headerText="Vinculo" id="tableColumn10">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText8}" id="staticText8" text="#{currentRow.value['vinculo']}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn11}"
                                                                                            headerText="Instrucción" id="tableColumn11">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText9}" id="staticText9" text="#{currentRow.value['instruccion']}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn3}" headerText="Ocupación" id="tableColumn3">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText2}" id="staticText2" text="#{currentRow.value['ocupacion']}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn4}" headerText="Ingresos" id="tableColumn4">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText3}" id="staticText3" text="#{currentRow.value['ingresos']}"/>
                                                                            </ui:tableColumn>                       
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn12}" headerText="Salud" id="tableColumn12">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText10}" id="staticText10" text="#{currentRow.value['salud']}"/>
                                                                            </ui:tableColumn>  
                                                                        </ui:tableRowGroup>
                                                                        <f:facet name="actionsTop">
                                                                            <ui:panelGroup binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.groupPanel1}" id="groupPanel1">
                                                                                <ui:button action="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnAgregarFamiliar_action}"
                                                                                           binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnAgregarFamiliar}" id="btnAgregarBeneficiario"
                                                                                           styleClass="button" text="Agregar"/>
                                                                                <ui:button action="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnModificarFamiliar_action}"
                                                                                           binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnModificarFamiliar}" id="btnModificarBeneficiario"
                                                                                           styleClass="button" text="Modificar"/>
                                                                                <ui:button action="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnEliminarFamiliar_action}"
                                                                                           binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnEliminarFamiliar}" id="btnQuitarBeneficiario"                                                                                           
                                                                                           styleClass="button" text="Eliminar"/>
                                                                            </ui:panelGroup>
                                                                        </f:facet>
                                                                    </ui:table>
                                                            </td>
                                                        </tr>         
                                                    </table>   
                                                </ui:tab>    
                                                <ui:tab id="two" text="Aspecto Habitacional">
                                                    <table>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label2}" for="tfNroPersonas" id="label2" styleClass="label" text="Nro. de Personas"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tfNroPersonas}" onKeyPress="return ValidarNum(event,this)" columns="40" id="tfNroPersonas" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label3}" for="tfVivienda" id="label3" styleClass="label" text="Vivienda"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tfVivienda}" columns="40" id="tfVivienda" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label5}" for="tfTenencia" id="label5" styleClass="label" text="Tenencia"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tfTenencia}" columns="40" id="tfTenencia" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label6}" for="tfNroCamas" id="label6" styleClass="label" text="Nro. de Camas"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tfNroCamas}" onKeyPress="return ValidarNum(event,this)" columns="40" id="tfNroCamas" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label7}" for="tfNroAmbientes" id="label7" styleClass="label" text="Nro. de Ambientes"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tfNroAmbientes}" onKeyPress="return ValidarNum(event,this)" columns="40" id="tfNroAmbientes" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label8}" for="cbBanioCompleto" id="label8" styleClass="label" text="Baño Completo"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.cbBanioCompleto}" id="cbBanioCompleto"/>
                                                            </td>    
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label9}" for="cbBanioInterno" id="label9" styleClass="label" text="Baño Interno"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.cbBanioInterno}" id="cbBanioInterno"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label10}" for="cbAgua" id="label10" styleClass="label" text="Agua"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.cbAgua}" id="cbAgua"/>
                                                            </td>    
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label11}" for="cbLuz" id="label11" styleClass="label" text="Luz"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.cbLuz}" id="cbLuz"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label12}" for="cbCloaca" id="label12" styleClass="label" text="Cloaca"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.cbCloaca}" id="cbCloaca"/>
                                                            </td>    
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label13}" for="cbGasNatural" id="label13" styleClass="label" text="Gas Natural"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.cbGasNatural}" id="cbGasNatural"/>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </ui:tab>
                                                <ui:tab id="three" text="Aspecto Económico">
                                                    <table>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label14}" for="tfNroCasas" id="label14" styleClass="label" text="Nro. de Casas"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tfNroCasas}" onKeyPress="return ValidarNum(event,this)" columns="40" id="tfNroCasas" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label15}" for="tfNroTerrenos" id="label15" styleClass="label" text="Nro. de Terrenos"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tfNroTerrenos}" onKeyPress="return ValidarNum(event,this)" columns="40" id="tfNroTerrenos" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label16}" for="tfNroCampos" id="label16" styleClass="label" text="Nro. de Campos"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tfNroCampos}" onKeyPress="return ValidarNum(event,this)" columns="40" id="tfNroCampos" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label17}" for="tfVehiculo" id="label17" styleClass="label" text="Vehículo"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tfVehiculo}" columns="40" id="tfVehiculo" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label18}" for="tfIndustria" id="label18" styleClass="label" text="Industria"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tfIndustria}" columns="40" id="tfIndustria" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label19}" for="tfActividadLaboral" id="label19" styleClass="label" text="Actividad Laboral"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tfActividadLaboral}" columns="40" id="tfActividadLaboral" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.label20}" for="tfComercio" id="label20" styleClass="label" text="Comercio"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tfComercio}" columns="40" id="tfComercio" styleClass="textField"/>
                                                            </td>
                                                        </tr>                                                        
                                                    </table>
                                                </ui:tab>
                                                <ui:tab id="four" text="Solicitud de Beneficio">
                                                    <table>
                                                        <tr>
                                                            <td colspan="4">
                                                                <ui:table augmentTitle="false" binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.table2}" id="table2" width="283">
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
                                                                        <ui:tableRowGroup binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableRowGroup2}" id="tableRowGroup2" 
                                                                                          sourceData="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.ldpSolicitudesBeneficios}" sourceVar="currentRow">
                                                                            <ui:tableColumn align="center" binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn5}"
                                                                                            id="tableColumn5" valign="middle" width="10">
                                                                                <ui:radioButton binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.radioButton2}" id="radioButton2"
                                                                                                label="" name="buttonGroup2" selected="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.RBSelected2}" selectedValue="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.currentRow2}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn13}"
                                                                                            headerText="Beneficio" id="tableColumn13">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText11}" id="staticText11" text="#{currentRow.value['beneficio']}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn14}"
                                                                                            headerText="Fecha Alta" id="tableColumn14">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText12}" id="staticText12" text="#{currentRow.value['fechaAlta']}" converter="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.dateTimeConverter1}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn8}" headerText="Cantidad" id="tableColumn8">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText6}" id="staticText6" text="#{currentRow.value['cantidad']}"/>
                                                                            </ui:tableColumn> 
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn15}" headerText="Monto" id="tableColumn15">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText13}" id="staticText13" text="#{currentRow.value['monto']}"/>
                                                                            </ui:tableColumn> 
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn17}" headerText="Descripción" id="tableColumn17">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText15}" id="staticText15" text="#{currentRow.value['descripcion']}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn7}" headerText="Fecha Finalización" id="tableColumn7">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText5}" id="staticText5" text="#{currentRow.value['fechaFinalizacion']}" converter="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.dateTimeConverter1}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn16}" headerText="Comentario de Finalización" id="tableColumn16">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText14}" id="staticText14" text="#{currentRow.value['comentarioFinalizacion']}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn6}"
                                                                                            headerText="Estado" id="tableColumn6">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText4}" id="staticText4" text="#{currentRow.value['estado']}"/>
                                                                            </ui:tableColumn>
                                                                        </ui:tableRowGroup>
                                                                        <f:facet name="actionsTop">
                                                                            <ui:panelGroup binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.groupPanel2}" id="groupPanel2">
                                                                                <ui:button action="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnAgregarSolicitud_action}"
                                                                                           binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnAgregarSolicitud}" id="btnAgregarSolicitud"
                                                                                           styleClass="button" text="Agregar"/>
                                                                                <ui:button action="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnModificarSolicitud_action}"
                                                                                           binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnModificarSolicitud}" id="btnModificarSolicitud"
                                                                                           styleClass="button" text="Modificar"/>
                                                                                <ui:button action="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnEliminarSolicitud_action}"
                                                                                           binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnEliminarSolicitud}" id="btnEliminarSolicitud"                                                                                       
                                                                                           styleClass="button" text="Eliminar"/>
                                                                                <ui:button action="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnAceptarSolicitud_action}"
                                                                                           binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnAceptarSolicitud}" id="btnAceptarSolicitud"                                                                                       
                                                                                           styleClass="button" text="Aceptar Entrega"/>
                                                                                <ui:button action="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnRechazarSolicitud_action}"
                                                                                           binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnRechazarSolicitud}" id="btnRechazarSolicitud"                                                                                       
                                                                                           styleClass="button" text="Rechazar Solicitud"/>
                                                                            </ui:panelGroup>
                                                                        </f:facet>
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
                                        <ui:messageGroup binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnGuardar_action}"
                                                       binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnCancelar_action}"
                                                       binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.idSubSesion}"/>
                        <ui:script binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
