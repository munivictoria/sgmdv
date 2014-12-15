<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui" xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{inventario$ABMStock$AdminStock.page1}" id="page1">
            <ui:html binding="#{inventario$ABMStock$AdminStock.html1}" id="html1">
                <ui:head binding="#{inventario$ABMStock$AdminStock.head1}" id="head1" title="Administración de Stock">
                    <ui:link binding="#{inventario$ABMStock$AdminStock.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{inventario$ABMStock$AdminStock.body1}" focus="form1:btnSeleccionarDeposito" id="body1" onLoad="parent.footer.location.reload();Init();changeStyleAlIngresar();" style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyPress="return eventoByEnter(event,'btnBuscar')" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{inventario$ABMStock$AdminStock.form1}" id="form1">
                        <div class="divAdmin">
                            <div class="formularioABM">
                                <table border="0" class="azul">
                                    <caption>
                                        <ui:staticText binding="#{inventario$ABMStock$AdminStock.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{inventario$ABMStock$AdminStock.head1.title}"/>
                                    </caption>
                                    <tbody>
                                        <tr><td align="center">
                                                <ui:panelGroup binding="#{inventario$ABMStock$AdminStock.pgParametros}" id="pgParametros">
                                                    <table>
                                        <tr>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td align="right" nowrap="true">
                                                <ui:label binding="#{inventario$ABMStock$AdminStock.lblDeposito}" id="lblDeposito" style="" styleClass="label" text="Depósito"/>
                                            </td>
                                            <td>
                                                <ui:textField binding="#{inventario$ABMStock$AdminStock.tfDeposito}" columns="40" disabled="true" id="tfDeposito" styleClass="textField"/>
                                                <ui:button action="#{inventario$ABMStock$AdminStock.btnSeleccionarDeposito_action}"
                                                           binding="#{inventario$ABMStock$AdminStock.btnSeleccionarDeposito}" escape="false" id="btnSeleccionarDeposito"
                                                           mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Depósito"/>
                                                <ui:button action="#{inventario$ABMStock$AdminStock.btnLimpiarDeposito_action}"
                                                           binding="#{inventario$ABMStock$AdminStock.btnLimpiarDeposito}" escape="false" id="btnLimpiarDeposito" mini="true"
                                                           styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right" nowrap="true">
                                                <ui:label binding="#{inventario$ABMStock$AdminStock.lblBien}" id="lblBien" style="" styleClass="label" text="Bien"/>
                                            </td>
                                            <td nowrap="nowrap">
                                                <ui:textField binding="#{inventario$ABMStock$AdminStock.tfBien}" columns="40" disabled="true" id="tfBien" styleClass="textField"/>
                                                <ui:button action="#{inventario$ABMStock$AdminStock.btnSeleccionarBien_action}"
                                                           binding="#{inventario$ABMStock$AdminStock.btnSeleccionarBien}" escape="false" id="btnSeleccionarBien"
                                                           mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Depósito"/>
                                                <ui:button action="#{inventario$ABMStock$AdminStock.btnLimpiarBien_action}"
                                                           binding="#{inventario$ABMStock$AdminStock.btnLimpiarBien}" escape="false" id="btnLimpiarBien" mini="true"
                                                           styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                        </tr>
                                         </table>
                                        </ui:panelGroup>
                                        </td></tr>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td align="right" colspan="2">
                                                <a4j:commandButton binding="#{inventario$ABMStock$AdminStock.btnBuscar}" action="#{inventario$ABMStock$AdminStock.btnBuscar_action}"
                                                                   id="btnBuscar" value="Buscar" styleClass="btnAjax" reRender="form1:table1" oncomplete="changeStyleAlIngresar()"/>
                                                <a4j:commandButton action="#{inventario$ABMStock$AdminStock.btnReiniciar_action}"
                                                           binding="#{inventario$ABMStock$AdminStock.btnReiniciar}" id="btnReiniciar" styleClass="btnAjax" value="Reiniciar" reRender="form1:pgParametros,form1:table1"/>

                                                <ui:staticText binding="#{inventario$ABMStock$AdminStock.staticText2}" escape="false" id="staticText2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                <ui:button action="#{inventario$ABMStock$AdminStock.btnCancelar_action}"
                                                           binding="#{inventario$ABMStock$AdminStock.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                            </td>
                                        </tr>
                                    </tfoot>
                                </table>
                                <!--</div>
                                -->
                                <div>
                                    <ui:messageGroup binding="#{inventario$ABMStock$AdminStock.messageGroup}" id="messageGroup" showDetail="true" showSummary="false" styleClass="grupoMsgAdmin"/>
                                </div>
                                <table class ="general">
                                    <tr>
                                        <td>
                                            <ui:table binding="#{inventario$ABMStock$AdminStock.table1}" id="table1">
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
                                                    <ui:tableRowGroup binding="#{inventario$ABMStock$AdminStock.tableRowGroup1}"
                                                                      emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1" onMouseOver="jsRowMouseOver(this)" onMouseOut="jsRowMouseOut(this)" onClick="jsRowMouseClick(this)" onDblClick="funcionSeleccionar()"
                                                                      sourceData="#{inventario$ABMStock$AdminStock.ldpStock}" sourceVar="currentRow">
                                                        <ui:tableColumn align="center" binding="#{inventario$ABMStock$AdminStock.tableColumn1}" id="tableColumn1"
                                                                        valign="middle" width="10">
                                                            <ui:radioButton binding="#{inventario$ABMStock$AdminStock.radioButton1}" id="radioButton1" label=""  onClick="checkUncheck(this)"
                                                                            name="buttonGroup" selected="#{inventario$ABMStock$AdminStock.RBSelected}" selectedValue="#{inventario$ABMStock$AdminStock.currentRow}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{inventario$ABMStock$AdminStock.tableColumn2}" headerText="Bien" id="tableColumn2" sort="bien">
                                                            <ui:staticText binding="#{inventario$ABMStock$AdminStock.stColBien}" id="stColBien" text="#{currentRow.value['bien']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{inventario$ABMStock$AdminStock.tableColumn3}" headerText="Cantidad" id="tableColumn3" sort="cantidad">
                                                            <ui:staticText binding="#{inventario$ABMStock$AdminStock.stColCantidad}" id="stColCantidad" text="#{currentRow.value['cantidad']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{inventario$ABMStock$AdminStock.tableColumn4}" headerText="Depósito" id="tableColumn4" sort="deposito">
                                                            <ui:staticText binding="#{inventario$ABMStock$AdminStock.stColDeposito}" id="stColDeposito" text="#{currentRow.value['deposito']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                        <f:facet name="actionsTop">
                                            <ui:panelGroup binding="#{inventario$ABMStock$AdminStock.groupPanel1}" id="groupPanel1" style="">
                                                <ui:button action="#{inventario$ABMStock$AdminStock.btnSeleccionar_action}"
                                                           binding="#{inventario$ABMStock$AdminStock.btnSeleccionar}" id="btnSeleccionar" styleClass="button" text="Seleccionar"/>
                                                <ui:staticText binding="#{inventario$ABMStock$AdminStock.staticText6}" escape="false" id="staticText6" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                <ui:button action="#{inventario$ABMStock$AdminStock.btnAgregar_action}"
                                                           binding="#{inventario$ABMStock$AdminStock.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar"/>
                                                <ui:button action="#{inventario$ABMStock$AdminStock.btnModificar_action}"
                                                           binding="#{inventario$ABMStock$AdminStock.btnModificar}" id="btnModificar" styleClass="button" text="Modificar"/>
                                                <ui:button action="#{inventario$ABMStock$AdminStock.btnEliminar_action}"
                                                           binding="#{inventario$ABMStock$AdminStock.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar"/>
                                                <ui:staticText binding="#{inventario$ABMStock$AdminStock.staticText8}" escape="false" id="staticText8" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                <ui:button action="#{inventario$ABMStock$AdminStock.btnConsultar_action}"
                                                           binding="#{inventario$ABMStock$AdminStock.btnConsultar}" id="btnConsultar" styleClass="button" text="Consultar"/>
                                                <ui:staticText binding="#{inventario$ABMStock$AdminStock.staticText9}" escape="false" id="staticText9" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
<ui:button action="#{inventario$ABMStock$AdminStock.btnExportar_action}" binding="#{inventario$ABMStock$AdminStock.btnExportar}" id="btnExportar" styleClass="button" text="Exportar" onClick="return exportarReporte()"/>
                                            </ui:panelGroup>
                                        </f:facet>
                                    </ui:table>
                                    </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <script>
                    document.getElementById('form1:btnSeleccionarDeposito').focus();
                        </script>
                        <ui:hiddenField binding="#{inventario$ABMStock$AdminStock.hidIdPagina}" id="hidIdPagina" text="#{inventario$ABMStock$AdminStock.idPagina}"/>
                        <ui:hiddenField binding="#{inventario$ABMStock$AdminStock.hidIdSubSesion}" id="hidIdSubSesion" text="#{inventario$ABMStock$AdminStock.idSubSesion}"/>
                        <ui:script binding="#{inventario$ABMStock$AdminStock.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js"/>
                        <ui:script binding="#{inventario$ABMStock$AdminStock.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>                        
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
