<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.head1}" id="head1" title="Modificar FichaSocial">
                    <ui:link binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.body1}" focus="form1:tfCodigo" id="body1" onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(242, 242, 236); -rave-layout: grid"  onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="amarillo">
                                <caption>
                                    <ui:staticText binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label4}" for="tfCodigo" id="label4" styleClass="label" text="Número"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tfCodigo}" onKeyPress="return ValidarNum(event,this)" maxLength="9" id="tfCodigo" styleClass="textField"/>
                                        </td> 
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label23}" for="tfFecha" id="label23" styleClass="label" text="Fecha"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tfFecha}" id="tfFecha" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
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
                                            <ui:tabSet binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tabSet1}">
                                                <ui:tab id ="one" text="Beneficiarios">
                                                    <table>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label1}" for="tfTitular" id="label1" styleClass="label" text="Titular"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tfTitular}" columns="50" disabled="true"
                                                                              id="tfTitular" styleClass="textField"/>
                                                                <ui:button action="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnAgregarTitular_action}"                                                                          
                                                                           binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnAgregarTitular}"
                                                                           escape="false" mini="true" text="&amp;nbsp;" styleClass="buttonAgregar"/>
                                                                <ui:button action="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnModificarTitular_action}"                                                                          
                                                                           binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnModificarTitular}"
                                                                           escape="false" mini="true" text="&amp;nbsp;" styleClass="buttonModificar"/>
                                                                <ui:button action="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnEliminarTitular_action}" 
                                                                           binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnEliminarTitular}"
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
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label22}" id="label22" styleClass="label" text="Grupo Familiar"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="4">
                                                                <ui:table augmentTitle="false" binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.table1}" id="table1" width="283">
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
                                                                        <ui:tableRowGroup binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tableRowGroup1}" id="tableRowGroup1"                                                                                          sourceData="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.ldpGrupoFamiliar}" sourceVar="currentRow">
                                                                            <ui:tableColumn align="center" binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tableColumn1}"
                                                                                            id="tableColumn1" valign="middle" width="10">
                                                                                <ui:radioButton binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.radioButton1}" id="radioButton1"
                                                                                                label="" name="buttonGroup1" selected="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.RBSelected1}" selectedValue="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.currentRow1}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tableColumn2}"
                                                                                            headerText="Persona" id="tableColumn2">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.staticText1}" id="staticText1" text="#{currentRow.value['persona']}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tableColumn9}"
                                                                                            headerText="Obra Social" id="tableColumn9">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.staticText7}" id="staticText7" text="#{currentRow.value['obraSocial']}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tableColumn10}"
                                                                                            headerText="Vinculo" id="tableColumn10">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.staticText8}" id="staticText8" text="#{currentRow.value['vinculo']}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tableColumn11}"
                                                                                            headerText="Instrucción" id="tableColumn11">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.staticText9}" id="staticText9" text="#{currentRow.value['instruccion']}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tableColumn3}" headerText="Ocupación" id="tableColumn3">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.staticText2}" id="staticText2" text="#{currentRow.value['ocupacion']}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tableColumn4}" headerText="Ingresos" id="tableColumn4">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.staticText3}" id="staticText3" text="#{currentRow.value['ingresos']}"/>
                                                                            </ui:tableColumn>                       
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tableColumn12}" headerText="Salud" id="tableColumn12">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.staticText10}" id="staticText10" text="#{currentRow.value['salud']}"/>
                                                                            </ui:tableColumn>  
                                                                        </ui:tableRowGroup>
                                                                        <f:facet name="actionsTop">
                                                                            <ui:panelGroup binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.groupPanel1}" id="groupPanel1">
                                                                                <ui:button action="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnAgregarFamiliar_action}"
                                                                                           binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnAgregarFamiliar}" id="btnAgregarBeneficiario"
                                                                                           styleClass="button" text="Agregar"/>
                                                                                <ui:button action="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnModificarFamiliar_action}"
                                                                                           binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnModificarFamiliar}" id="btnModificarBeneficiario"
                                                                                           styleClass="button" text="Modificar"/>
                                                                                <ui:button action="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnEliminarFamiliar_action}"
                                                                                           binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnEliminarFamiliar}" id="btnQuitarBeneficiario"                                                                                           
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
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label2}" for="tfNroPersonas" id="label2" styleClass="label" text="Nro. de Personas"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tfNroPersonas}" onKeyPress="return ValidarNum(event,this)" columns="40" id="tfNroPersonas" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label3}" for="tfVivienda" id="label3" styleClass="label" text="Vivienda"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tfVivienda}" columns="40" id="tfVivienda" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label5}" for="tfTenencia" id="label5" styleClass="label" text="Tenencia"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tfTenencia}" columns="40" id="tfTenencia" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label6}" for="tfNroCamas" id="label6" styleClass="label" text="Nro. de Camas"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tfNroCamas}" onKeyPress="return ValidarNum(event,this)" columns="40" id="tfNroCamas" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label7}" for="tfNroAmbientes" id="label7" styleClass="label" text="Nro. de Ambientes"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tfNroAmbientes}" onKeyPress="return ValidarNum(event,this)" columns="40" id="tfNroAmbientes" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label8}" for="cbBanioCompleto" id="label8" styleClass="label" text="Baño Completo"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.cbBanioCompleto}" id="cbBanioCompleto"/>
                                                            </td>    
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label9}" for="cbBanioInterno" id="label9" styleClass="label" text="Baño Interno"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.cbBanioInterno}" id="cbBanioInterno"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label10}" for="cbAgua" id="label10" styleClass="label" text="Agua"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.cbAgua}" id="cbAgua"/>
                                                            </td>    
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label11}" for="cbLuz" id="label11" styleClass="label" text="Luz"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.cbLuz}" id="cbLuz"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label12}" for="cbCloaca" id="label12" styleClass="label" text="Cloaca"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.cbCloaca}" id="cbCloaca"/>
                                                            </td>    
                                                            <td align="right" nowrap="nowrap">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label13}" for="cbGasNatural" id="label13" styleClass="label" text="Gas Natural"/>
                                                            </td>
                                                            <td>
                                                                <ui:checkbox binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.cbGasNatural}" id="cbGasNatural"/>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </ui:tab>
                                                <ui:tab id="three" text="Aspecto Económico">
                                                    <table>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label14}" for="tfNroCasas" id="label14" styleClass="label" text="Nro. de Casas"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tfNroCasas}" onKeyPress="return ValidarNum(event,this)" columns="40" id="tfNroCasas" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label15}" for="tfNroTerrenos" id="label15" styleClass="label" text="Nro. de Terrenos"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tfNroTerrenos}" onKeyPress="return ValidarNum(event,this)" columns="40" id="tfNroTerrenos" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label16}" for="tfNroCampos" id="label16" styleClass="label" text="Nro. de Campos"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tfNroCampos}" onKeyPress="return ValidarNum(event,this)" columns="40" id="tfNroCampos" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label17}" for="tfVehiculo" id="label17" styleClass="label" text="Vehículo"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tfVehiculo}" columns="40" id="tfVehiculo" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label18}" for="tfIndustria" id="label18" styleClass="label" text="Industria"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tfIndustria}" columns="40" id="tfIndustria" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label19}" for="tfActividadLaboral" id="label19" styleClass="label" text="Actividad Laboral"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tfActividadLaboral}" columns="40" id="tfActividadLaboral" styleClass="textField"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" nowrap="nowrap" style="height: 15px">
                                                                <ui:label binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.label20}" for="tfComercio" id="label20" styleClass="label" text="Comercio"/>
                                                            </td>
                                                            <td colspan="3" nowrap="nowrap">
                                                                <ui:textField binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tfComercio}" columns="40" id="tfComercio" styleClass="textField"/>
                                                            </td>
                                                        </tr>                                                        
                                                    </table>
                                                </ui:tab>
                                                <ui:tab id="four" text="Solicitud de Beneficio">
                                                    <table>
                                                        <tr>
                                                            <td colspan="4">
                                                                <ui:table augmentTitle="false" binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.table2}" id="table2" width="283">
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
                                                                        <ui:tableRowGroup binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tableRowGroup2}" id="tableRowGroup2"
                                                                                          sourceData="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.ldpSolicitudesBeneficios}" sourceVar="currentRow">
                                                                            <ui:tableColumn align="center" binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tableColumn5}"
                                                                                            id="tableColumn5" valign="middle" width="10">
                                                                                <ui:radioButton binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.radioButton2}" id="radioButton2"
                                                                                                label="" name="buttonGroup2" selected="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.RBSelected2}" selectedValue="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.currentRow2}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tableColumn13}"
                                                                                            headerText="Beneficio" id="tableColumn13">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.staticText11}" id="staticText11" text="#{currentRow.value['beneficio']}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tableColumn14}"
                                                                                            headerText="Fecha Alta" id="tableColumn14">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.staticText12}" id="staticText12" text="#{currentRow.value['fechaAlta']}" converter="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.dateTimeConverter1}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tableColumn8}" headerText="Cantidad" id="tableColumn8">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.staticText6}" id="staticText6" text="#{currentRow.value['cantidad']}"/>
                                                                            </ui:tableColumn> 
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tableColumn15}" headerText="Monto" id="tableColumn15">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.staticText13}" id="staticText13" text="#{currentRow.value['monto']}"/>
                                                                            </ui:tableColumn> 
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn17}" headerText="Descripción" id="tableColumn17">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText15}" id="staticText15" text="#{currentRow.value['descripcion']}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tableColumn7}" headerText="Fecha Finalización" id="tableColumn7">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.staticText5}" id="staticText5" text="#{currentRow.value['fechaFinalizacion']}" converter="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.dateTimeConverter1}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.tableColumn16}" headerText="Comentario de Finalización" id="tableColumn16">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$AgregarFichaSocial.staticText14}" id="staticText14" text="#{currentRow.value['comentarioFinalizacion']}"/>
                                                                            </ui:tableColumn>
                                                                            <ui:tableColumn binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.tableColumn6}"
                                                                                            headerText="Estado" id="tableColumn6">
                                                                                <ui:staticText binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.staticText4}" id="staticText4" text="#{currentRow.value['estado']}"/>
                                                                            </ui:tableColumn>
                                                                        </ui:tableRowGroup>
                                                                        <f:facet name="actionsTop">
                                                                            <ui:panelGroup binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.groupPanel2}" id="groupPanel2">
                                                                                <ui:button action="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnAgregarSolicitud_action}"
                                                                                           binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnAgregarSolicitud}" id="btnAgregarSolicitud"
                                                                                           styleClass="button" text="Agregar"/>
                                                                                <ui:button action="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnModificarSolicitud_action}"
                                                                                           binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnModificarSolicitud}" id="btnModificarSolicitud"
                                                                                           styleClass="button" text="Modificar"/>
                                                                                <ui:button action="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnEliminarSolicitud_action}"
                                                                                           binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnEliminarSolicitud}" id="btnEliminarSolicitud"                                                                                       
                                                                                           styleClass="button" text="Eliminar"/>
                                                                                <ui:button action="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnAceptarSolicitud_action}"
                                                                                           binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnAceptarSolicitud}" id="btnAceptarSolicitud"                                                                                       
                                                                                           styleClass="button" text="Aceptar Entrega"/>
                                                                                <ui:button action="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnRechazarSolicitud_action}"
                                                                                           binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnRechazarSolicitud}" id="btnRechazarSolicitud"                                                                                       
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
                                        <ui:messageGroup binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnGuardar_action}"
                                                       binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnCancelar_action}"
                                                       binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.idSubSesion}"/>
                        <ui:script binding="#{accionsocial$ABMFichaSocial$ModificarFichaSocial.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
