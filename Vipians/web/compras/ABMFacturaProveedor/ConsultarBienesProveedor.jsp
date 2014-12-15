<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.page1}" id="page1">
            <ui:html binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.html1}" id="html1">
                <ui:head binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.head1}" id="head1" title="Consulta de Productos Provistos">
                    <ui:link binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.body1}" focus="form1:tfRazonSocial" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.lblProveedor}" for="tfProveedor"
                                                id="label1" styleClass="label" text="Proveedor"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.tfProveedor}" columns="40"
                                                disabled="true" id="tfProveedor" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <hr/>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:staticText binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.staticText3}" id="staticText3"
                                                styleClass="label2" text="Productos Provistos"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.table1}" id="table1" width="479">
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
                                                <ui:tableRowGroup binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.tableRowGroup1}"
                                                    id="tableRowGroup1" 
                                                    sourceData="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.ldpBienes}" sourceVar="currentRow">
                                                    <ui:tableColumn align="center"
                                                        binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.tableColumn1}" id="tableColumn1"
                                                        valign="middle" width="10">
                                                        <ui:radioButton binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.radioButton1}"
                                                            id="radioButton1" label="" name="buttonGroup"
                                                            selected="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.RBSelected}" selectedValue="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.currentRow}"/>
                                                    </ui:tableColumn>
                                                    <!--<ui:tableColumn align="center" binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.tableColumn5}" id="tableColumn5"
                                                                    onClick="setTimeout('initAllRows()', 0)" valign="middle" width="10">
                                                        <ui:checkbox binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.checkbox1}" id="checkbox1"
                                                                     selected="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.selected}"
                                                                     selectedValue="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.selectedValue}"/>
                                                    </ui:tableColumn>-->
                                                    <ui:tableColumn binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.tableColumn2}"
                                                        headerText="Bien" id="tableColumn2" sort="bien">
                                                        <ui:staticText binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.staticText1}"
                                                            id="staticText1" text="#{currentRow.value['nombre']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.tableColumn3}"
                                                        headerText="Descripción" id="tableColumn3" sort="descripcion">
                                                        <ui:staticText binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.staticText4}"
                                                            id="staticText4" text="#{currentRow.value['descripcion']}"/>
                                                    </ui:tableColumn>                            
                                                </ui:tableRowGroup>
                                                <f:facet name="actionsTop">
                                                    <ui:panelGroup binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.groupPanel1}" id="groupPanel1">
                                                        <ui:button action="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.btnSeleccionar_action}"
                                                            binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.btnSeleccionar}" id="btnSeleccionar"
                                                            styleClass="button" text="Seleccionar"/>
                                                    </ui:panelGroup>
                                                </f:facet>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <!--<ui:staticText binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.stSeparador}" escape="false"
                                                id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>-->
                                            <ui:button action="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.btnCancelar_action}"
                                                binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.btnCancelar}" id="btnCancelar"
                                                styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.hidIdPagina}" id="hidIdPagina" text="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.idPagina}"/>
                        <ui:hiddenField binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.hidIdSubSesion}" id="hidIdSubSesion" text="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.idSubSesion}"/>
                        <ui:script binding="#{compras$ABMFacturaProveedor$ConsultarBienesProveedor.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
