<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{catastro$ABMParcela$GenerarObligacionOSP.page1}" id="page1">
            <ui:html binding="#{catastro$ABMParcela$GenerarObligacionOSP.html1}" id="html1">
                <ui:head binding="#{catastro$ABMParcela$GenerarObligacionOSP.head1}" id="head1" title="Generar Obligación OSP">
                    <ui:link binding="#{catastro$ABMParcela$GenerarObligacionOSP.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{catastro$ABMParcela$GenerarObligacionOSP.body1}" focus="form1:tfTitularSubdivision" id="body1" onLoad="parent.footer.location.reload();" style="background-color: rgb(236, 242, 236); -rave-layout: grid">
                    <ui:form binding="#{catastro$ABMParcela$GenerarObligacionOSP.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{catastro$ABMParcela$GenerarObligacionOSP.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{catastro$ABMParcela$GenerarObligacionOSP.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                    <td colspan="6">
                                        <br/>
                                    </td>
                                    </tr>
                                    <tr>
                                    <td align="left" nowrap="true">
                                        <ui:label binding="#{catastro$ABMParcela$GenerarObligacionOSP.label2}" id="label2"
                                                  style="" styleClass="label2" text="Parcela: "/>
                                        <ui:label binding="#{catastro$ABMParcela$GenerarObligacionOSP.lblParcela}" styleClass="label2"
                                                  id="lblParcela"/>
                                    </td>
                                    </tr>
                                    <tr>
                                    <td colspan="6">
                                        <br/>
                                    </td>
                                    </tr>   
                                    <tr>
                                    <td colspan="6">
                                        <ui:label binding="#{catastro$ABMParcela$GenerarObligacionOSP.label1}" id="label1" styleClass="label2" text="Seleccione los Servicios OSP que se les generará a las subparcelas"/>
                                    </td>
                                    </tr>
                                    <tr>
                                    <td align="right" nowrap="nowrap">
                                        <ui:dropDown binding="#{catastro$ABMParcela$GenerarObligacionOSP.ddServicioOSP}" id="ddEstado"
                                                     items="#{catastro$ABMParcela$GenerarObligacionOSP.ddServicioOSPDefaultOptions.options}" styleClass="textField" />
                                    </td>
                                    <td nowrap="nowrap">
                                        <ui:button action="#{catastro$ABMParcela$GenerarObligacionOSP.btnAgregarOSP_action}"
                                                   binding="#{catastro$ABMParcela$GenerarObligacionOSP.btnAgregarOSP}" id="btnAgregarOSP" styleClass="button" text="Generar OSP a subparcelas"/>
                                    </td>
                                    </tr> 
                                    <tr>
                                    <td colspan="6" style="padding-left:1px; padding-right:1px;">
                                        <div class="div" style="width: 780px; height: 15px;"> Obligación OSP por Subparcela </div>
                                        <table class="tablaInterna" style="-moz-border-radius: 0px 0px 5px 5px; width: 192px;">
                                            <tbody>
                                                <tr>
                                                <td><b>Obligaciones Creadas:  </b> </td>
                                                </tr> 
                                                <tr>
                                                <td colspan="6">                                                    
                                                    <ui:table augmentTitle="false" binding="#{catastro$ABMParcela$GenerarObligacionOSP.table2}" id="table2" width="108">
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
                                                            <ui:tableRowGroup binding="#{catastro$ABMParcela$GenerarObligacionOSP.tableRowGroup2}" id="tableRowGroup2"
                                                                              emptyDataMsg="Ningún registro encontrado."
                                                                              sourceData="#{catastro$ABMParcela$GenerarObligacionOSP.ldpObligacionesOSPExistentes}" sourceVar="currentRow">                                                          
                                                                <ui:tableColumn binding="#{catastro$ABMParcela$GenerarObligacionOSP.tableColumn7}"
                                                                                headerText="Parcela" id="tableColumn7" sort="parcela">
                                                                    <ui:staticText binding="#{catastro$ABMParcela$GenerarObligacionOSP.staticText1}" id="staticText1" text="#{currentRow.value['parcela']}"/>
                                                                </ui:tableColumn>
                                                                <ui:tableColumn binding="#{catastro$ABMParcela$GenerarObligacionOSP.tableColumn8}" headerText="Número de cuenta"
                                                                                id="tableColumn8">
                                                                    <ui:staticText binding="#{catastro$ABMParcela$GenerarObligacionOSP.stNumeroCuenta}"
                                                                                   id="stNumeroCuenta" text="#{currentRow.value['numeroCuenta']}"/>                                                                
                                                                </ui:tableColumn>
                                                                <ui:tableColumn binding="#{catastro$ABMParcela$GenerarObligacionOSP.tableColumn9}" headerText="Número Subcuenta"
                                                                                id="tableColumn9">
                                                                    <ui:staticText binding="#{catastro$ABMParcela$GenerarObligacionOSP.stNumeroSubcuenta}"
                                                                                   id="stNumeroSubcuenta"  text="#{currentRow.value['numeroSubCuenta']}" />
                                                                </ui:tableColumn>   
                                                                <ui:tableColumn binding="#{catastro$ABMParcela$GenerarObligacionOSP.tableColumn10}" headerText="Código de Medidor"
                                                                                id="tableColumn10">
                                                                    <ui:staticText binding="#{catastro$ABMParcela$GenerarObligacionOSP.stCodigoMedidor}"
                                                                                   id="stcodigoMedidor" text="#{currentRow.value['codigoMedidor']}" />
                                                                </ui:tableColumn>                                                
                                                            </ui:tableRowGroup>                                      
                                                        </ui:table>
                                                </td>
                                                </tr>
                                                <tr>
                                                <td><b>Obligaciones a Generar: </b> </td>
                                                </tr> 
                                                <tr>
                                                <td colspan="6">

                                                    <ui:table augmentTitle="false" binding="#{catastro$ABMParcela$GenerarObligacionOSP.table1}" id="table1" width="108">
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
                                                            <ui:tableRowGroup binding="#{catastro$ABMParcela$GenerarObligacionOSP.tableRowGroup1}" id="tableRowGroup1"
                                                                              emptyDataMsg="Ningún registro encontrado."
                                                                              selected="#{catastro$ABMParcela$GenerarObligacionOSP.currentRowSelected}"
                                                                              sourceData="#{catastro$ABMParcela$GenerarObligacionOSP.ldpObligacionesOSP}" sourceVar="currentRow">

                                                            <ui:tableColumn align="center" binding="#{catastro$ABMParcela$GenerarObligacionOSP.tableColumn11}" id="tableColumn11"
                                                                            valign="middle" width="10">
                                                                <ui:checkbox binding="#{catastro$ABMParcela$GenerarObligacionOSP.checkbox1}" id="checkbox1"
                                                                             onClick="setTimeout('initAllRows()', 0)" selected="#{catastro$ABMParcela$GenerarObligacionOSP.selected}"
                                                                             selectedValue="#{catastro$ABMParcela$GenerarObligacionOSP.selectedValue}"/>
                                                            </ui:tableColumn>
                                                            <ui:tableColumn binding="#{catastro$ABMParcela$GenerarObligacionOSP.tableColumn3}"
                                                                            headerText="Parcela" id="tableColumn3" sort="parcela">
                                                                <ui:staticText binding="#{catastro$ABMParcela$GenerarObligacionOSP.staticText2}" id="staticText2" text="#{currentRow.value['parcela']}"/>
                                                            </ui:tableColumn>
                                                            <ui:tableColumn binding="#{catastro$ABMParcela$GenerarObligacionOSP.tableColumn5}" headerText="Número de cuenta"
                                                                            id="tableColumn5">
                                                                <ui:textField binding="#{catastro$ABMParcela$GenerarObligacionOSP.tfNumeroCuenta}" columns="7"
                                                                              id="tfNumeroCuenta" maxLength="5" styleClass="textField"  text="#{currentRow.value['numeroCuenta']}"/>                                                                
                                                            </ui:tableColumn>
                                                            <ui:tableColumn binding="#{catastro$ABMParcela$GenerarObligacionOSP.tableColumn4}" headerText="Número Subcuenta"
                                                                            id="tableColumn4">
                                                                <ui:textField binding="#{catastro$ABMParcela$GenerarObligacionOSP.tfNumeroSubCuenta}" columns="4"
                                                                              id="tfNumeroSubCuenta" maxLength="3" styleClass="textField" text="#{currentRow.value['numeroSubCuenta']}" />
                                                            </ui:tableColumn>   
                                                            <ui:tableColumn binding="#{catastro$ABMParcela$GenerarObligacionOSP.tableColumn6}" headerText="Código de Medidor"
                                                                            id="tableColumn6">
                                                                <ui:textField binding="#{catastro$ABMParcela$GenerarObligacionOSP.tfCodigoMedidor}" columns="4"
                                                                              id="tfcodigoMedidor" maxLength="3" styleClass="textField" text="#{currentRow.value['codigoMedidor']}" />
                                                            </ui:tableColumn>                                                
                                                        </ui:tableRowGroup>                                      
                                                    </ui:table>
                                                </td>
                                                </tr>   
                                            </tbody>  
                                            <tfoot>
                                                <tr>
                                                <td align="right" colspan="6" nowrap="true">
                                                    <ui:button action="#{catastro$ABMParcela$GenerarObligacionOSP.btnGenerar_action}"
                                                               binding="#{catastro$ABMParcela$GenerarObligacionOSP.btnGenerar}" id="btnGenerar"
                                                               styleClass="button" text="Guardar"/>  
                                                    <!--
                                                    <ui:button action="#{catastro$ABMParcela$GenerarObligacionOSP.btnNoGenerar_action}"
                                                               binding="#{catastro$ABMParcela$GenerarObligacionOSP.btnNoGenerar}" id="btnNoGenerar"  
                                                               onClick="return (confirm(&quot;¿Desea que la Obligación OSP se le genere a la Parcela?&quot;));"
                                                               styleClass="button" text="Cancelar"/>
                                                    -->
                                                </td>
                                                </tr>
                                            </tfoot>
                                        </table> 
                                    </td>                               
                                    </tr>
                                    <tr>                                   
                                    </tr>
                                    <tr>
                                    <td colspan="6" style="height: 22px">
                                        <ui:messageGroup binding="#{catastro$ABMParcela$GenerarObligacionOSP.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                    <td align="right" colspan="6" nowrap="true">
                                        <ui:button action="#{catastro$ABMParcela$GenerarObligacionOSP.btnVolver_action}"
                                                   binding="#{catastro$ABMParcela$GenerarObligacionOSP.btnVolver}" id="btnVolver"                                                              
                                                   styleClass="button" text="Volver"/> 
                                    </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{catastro$ABMParcela$GenerarObligacionOSP.hidIdPagina}" id="hidIdPagina" text="#{catastro$ABMParcela$GenerarObligacionOSP.idPagina}"/>
                        <ui:hiddenField binding="#{catastro$ABMParcela$GenerarObligacionOSP.hidIdSubSesion}" id="hidIdSubSesion" text="#{catastro$ABMParcela$GenerarObligacionOSP.idSubSesion}"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>