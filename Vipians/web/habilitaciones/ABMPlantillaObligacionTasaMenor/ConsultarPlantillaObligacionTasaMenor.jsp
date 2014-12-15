<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.page1}" id="page1">
            <ui:html binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.html1}" id="html1">
                <ui:head binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.head1}" id="head1" title="Eliminar Plantilla de Obligación Menor">
                    <ui:link binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.body1}" focus="form1:tfNombre" id="body1"
                         onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"  onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.form1}" id="form1">
                        <div class="formularioABM" style="left: -4px; top: 0px; position: absolute">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.stTitulo}" id="stTitulo"
                                                   styleClass="tituloABM" text="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap" width="50">
                                            <ui:label binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.label4}" for="tfNombre" id="label4"
                                                      styleClass="label" text="Nombre de la Plantilla"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.tfNombre}" columns="40"
                                                          disabled="true" id="tfNombre" styleClass="textField"/>
                                        </td>                                        
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:label binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.lblDatosPlantilla}"
                                                      id="lblDatosPlantilla" styleClass="label2" text="Datos de la Plantilla"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.table1}"
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
                                                    <ui:tableRowGroup binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.tableRowGroup1}"
                                                                      id="tableRowGroup1"
                                                                      sourceData="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.ldpDatosPlantilla}"
                                                                      sourceVar="currentRow">
                                                    <!--      <ui:tableColumn
                                                        align="center" binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.tableColumn1}"
                                                        id="tableColumn1" valign="middle" width="10">
                                                        <ui:radioButton binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.radioButton1}"
                                                                        id="radioButton1" label="" name="buttonGroup"
                                                                        selected="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.RBSelected}" selectedValue="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.currentRow}"/>
                                                    </ui:tableColumn> -->
                                                    <ui:tableColumn binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.tableColumn2}"
                                                                    headerText="Nombre del Dato" id="tableColumn2" sort="nombre" width="40">
                                                        <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.staticText1}"
                                                                       id="staticText1" text="#{currentRow.value['nombre']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.tableColumn5}"
                                                                    headerText="Tipo del Dato" id="tableColumn5" sort="tipo" width="40">
                                                        <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.staticText2}"
                                                                       id="staticText2" text="#{currentRow.value['tipo']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                                <!--   <f:facet name="actionsTop">
                                                <ui:panelGroup binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.groupPanel1}" id="groupPanel1">
                                                    <ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.btnQuitar_action}"
                                                               binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.btnQuitar}" id="btnQuitar"
                                                               styleClass="button" text="Quitar"/>
                                                    <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.staticText6}"
                                                                   escape="false" id="staticText6" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                    <ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.btnQuitarTodos_action}"
                                                               binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.btnQuitarTodos}"
                                                               id="btnQuitarTodos" styleClass="button" text="Quitar todos"/>
                                                    <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.staticText8}"
                                                                   escape="false" id="staticText8" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                    <ui:button
                                                        action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.btnModificarLineaSS_action}"
                                                        binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.btnModificarLineaSS}"
                                                        id="btnModificarLineaSS" styleClass="button" text="Modificar Línea S.S."/>
                                                </ui:panelGroup>
                                            </f:facet>  -->
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:label binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.lblDatosPlantillaRegValuado}"
                                                      id="lblDatosPlantillaRegValuado" styleClass="label2" text="Datos de Registro Valuado"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.table2}"
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
                                                    <ui:tableRowGroup binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.tableRowGroup2}"
                                                                      id="tableRowGroup2"
                                                                      sourceData="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.ldpDatosPlantillaRegValuado}"
                                                                      sourceVar="currentRow">
                                                    <!--      <ui:tableColumn
                                                        align="center" binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.tableColumn6}"
                                                        id="tableColumn6" valign="middle" width="10">
                                                        <ui:radioButton binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.radioButton2}"
                                                                        id="radioButton2" label="" name="buttonGroup"
                                                                        selected="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.RBSelected2}" selectedValue="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.currentRow2}"/>
                                                    </ui:tableColumn> -->
                                                    <ui:tableColumn binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.tableColumn7}"
                                                                    headerText="Nombre del Dato" id="tableColumn7" sort="nombre" width="40">
                                                        <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.staticText9}"
                                                                       id="staticText9" text="#{currentRow.value['nombre']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.tableColumn8}"
                                                                    headerText="Tipo del Dato" id="tableColumn8" sort="tipo" width="40">
                                                        <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.staticText10}"
                                                                       id="staticText10" text="#{currentRow.value['tipo']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                                <!--   <f:facet name="actionsTop">
                                                <ui:panelGroup binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.groupPanel1}" id="groupPanel1">
                                                    <ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.btnQuitar_action}"
                                                               binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.btnQuitar}" id="btnQuitar"
                                                               styleClass="button" text="Quitar"/>
                                                    <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.staticText6}"
                                                                   escape="false" id="staticText6" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                    <ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.btnQuitarTodos_action}"
                                                               binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.btnQuitarTodos}"
                                                               id="btnQuitarTodos" styleClass="button" text="Quitar todos"/>
                                                    <ui:staticText binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.staticText8}"
                                                                   escape="false" id="staticText8" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                    <ui:button
                                                        action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.btnModificarLineaSS_action}"
                                                        binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.btnModificarLineaSS}"
                                                        id="btnModificarLineaSS" styleClass="button" text="Modificar Línea S.S."/>
                                                </ui:panelGroup>
                                            </f:facet>  -->
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.messageGroup1}"
                                                             id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.btnVolver_action}"
                                                       binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.btnVolver}" id="btnVolver"
                                                       styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$ABMPlantillaObligacionTasaMenor$ConsultarPlantillaObligacionTasaMenor.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
