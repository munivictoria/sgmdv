<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.page1}" id="page1">
            <ui:html binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.html1}" id="html1">
                <ui:head binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.head1}" id="head1" title="Modificar Plantilla de Obligación Menor">
                    <ui:link binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.body1}" focus="form1:tfNombre" id="body1"
                         onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.form1}" id="form1">
                        <div class="formularioABM" style="left: -4px; top: 0px; position: absolute">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.stTitulo}" id="stTitulo"
                                                   styleClass="tituloABM" text="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap" width="50">
                                            <ui:label binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.label4}" for="tfNombre" id="label4"
                                                      styleClass="label" text="Nombre de la Plantilla"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.tfNombre}" columns="40"
                                                          id="tfNombre" styleClass="textField"/>
                                        </td>                                        
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:label binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.lblDatosPlantilla}"
                                                      id="lblDatosPlantilla" styleClass="label2" text="Datos de la Plantilla"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">
                                            <ui:label binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.lblNombreDato}" id="lblNombreDato"
                                                      styleClass="label" text="Nombre"/>    
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.tfNombreDato}" columns="40"
                                                          id="tfNombreDato" styleClass="textField"/>
                                        </td>
                                    </tr>                                                                        
                                    <tr>
                                        <td align="right">
                                            <ui:label binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.label1}"
                                                      for="ddTipoDato" id="label1" styleClass="label" text="Tipo de Dato"/>                                        
                                        </td>
                                        <td>
                                            <ui:dropDown binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.ddTipoDato}"
                                                         id="ddTipoDato"
                                                         items="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.ddTipoDatoDefaultOptions.options}" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>    
                                        <td align="right" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.btnAgregarDato_action}"                                                                          
                                                       binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.btnAgregarDato}"
                                                       id="btnAgregarDato" escape="false" mini="true" text="&amp;nbsp;" styleClass="buttonAgregar"/>                                                                                                         
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.table1}"
                                                      id="table1" width="200">
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
                                                    <ui:tableRowGroup binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.tableRowGroup1}"
                                                                      id="tableRowGroup1"
                                                                      sourceData="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.ldpDatosPlantilla}"
                                                                      sourceVar="currentRow">
                                                        <ui:tableColumn
                                                            align="center" binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.tableColumn1}"
                                                            id="tableColumn1" valign="middle" width="10">
                                                            <ui:radioButton binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.radioButton1}"
                                                                            id="radioButton1" label="" name="buttonGroup"
                                                                            selected="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.RBSelected}" selectedValue="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.currentRow}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.tableColumn2}"
                                                                        headerText="Nombre del Dato" id="tableColumn2" sort="nombre" width="40">
                                                            <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.staticText1}"
                                                                           id="staticText1" text="#{currentRow.value['nombre']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.tableColumn5}"
                                                                        headerText="Tipo del Dato" id="tableColumn5" sort="tipo" width="40">
                                                            <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.staticText2}"
                                                                           id="staticText2" text="#{currentRow.value['tipo']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                <!--   <f:facet name="actionsTop">
                                                <ui:panelGroup binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.groupPanel1}" id="groupPanel1">
                                                    <ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.btnQuitar_action}"
                                                               binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.btnQuitar}" id="btnQuitar"
                                                               styleClass="button" text="Quitar"/>
                                                    <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.staticText6}"
                                                                   escape="false" id="staticText6" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                    <ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.btnQuitarTodos_action}"
                                                               binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.btnQuitarTodos}"
                                                               id="btnQuitarTodos" styleClass="button" text="Quitar todos"/>
                                                    <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.staticText8}"
                                                                   escape="false" id="staticText8" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                    <ui:button
                                                        action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.btnModificarLineaSS_action}"
                                                        binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.btnModificarLineaSS}"
                                                        id="btnModificarLineaSS" styleClass="button" text="Modificar Línea S.S."/>
                                                </ui:panelGroup>
                                            </f:facet>  -->
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:label binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.lblDatosPlantillaRegValuado}"
                                                      id="lblDatosPlantillaRegValuado" styleClass="label2" text="Datos de Registro Valuado"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">
                                            <ui:label binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.lblNombreDatoRegValuado}" id="lblNombreDatoRegValuado"
                                                      styleClass="label" text="Nombre"/>    
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.tfNombreDatoRegValuado}" columns="40"
                                                          id="tfNombreDatoRegValuado" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">
                                            <ui:label binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.label5}"
                                                      for="ddTipoDatoRegValuado" id="label5" styleClass="label" text="Tipo de Dato"/>                                        
                                        </td>
                                        <td>
                                            <ui:dropDown binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.ddTipoDatoRegValuado}"
                                                         id="ddTipoDatoRegValuado"
                                                         items="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.ddTipoDatoRegValuadoDefaultOptions.options}" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>    
                                        <td align="right" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.btnAgregarDatoRegValuado_action}"                                                                          
                                                       binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.btnAgregarDatoRegValuado}"
                                                       id="btnAgregarDatoRegValuado" escape="false" mini="true" text="&amp;nbsp;" styleClass="buttonAgregar"/>                                                                                                         
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.table2}"
                                                      id="table2" width="200">
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
                                                    <ui:tableRowGroup binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.tableRowGroup2}"
                                                                      id="tableRowGroup2"
                                                                      sourceData="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.ldpDatosPlantillaRegValuado}"
                                                                      sourceVar="currentRow">
                                                        <ui:tableColumn
                                                            align="center" binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.tableColumn6}"
                                                            id="tableColumn6" valign="middle" width="10">
                                                            <ui:radioButton binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.radioButton2}"
                                                                            id="radioButton2" label="" name="buttonGroup2"
                                                                            selected="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.RBSelected2}" selectedValue="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.currentRow2}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.tableColumn7}"
                                                                        headerText="Nombre del Dato" id="tableColumn7" sort="nombre" width="40">
                                                            <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.staticText9}"
                                                                           id="staticText9" text="#{currentRow.value['nombre']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.tableColumn8}"
                                                                        headerText="Tipo del Dato" id="tableColumn8" sort="tipo" width="40">
                                                            <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.staticText10}"
                                                                           id="staticText10" text="#{currentRow.value['tipo']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.messageGroup1}"
                                                             id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.btnGuardar_action}"
                                                       binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.btnGuardar}" id="btnGuardar"
                                                       styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.stSeparador}" escape="false"
                                                           id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.btnCancelar_action}"
                                                       binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.btnCancelar}" id="btnCancelar"
                                                       styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ModificarPlantillaObligacionTasaMenor.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
