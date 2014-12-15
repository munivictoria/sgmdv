<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.page1}" id="page1">
            <ui:html binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.html1}" id="html1">
                <ui:head binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.head1}" id="head1" title="Seleccion de Linea Orden de Compra">
                    <ui:link binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.body1}" focus="form1:tfAnio" id="body1"
                    onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.stTitulo}"
                                                   id="stTitulo" styleClass="tituloABM" text="#{compras$ABMFacturaProveedor$GenerarLineasFactura.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="4">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.lblOrdenesCompra}" for="tfOrdenCompra" id="lblOrdenesCompra" styleClass="label" text="Orden Compra"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.tfOrdenCompra}" columns="40"
                                                disabled="true" id="tfOrdenCompra" styleClass="textField"/>
                                            <ui:button action="#{compras$ABMFacturaProveedor$GenerarLineasFactura.btnSeleccionarOrdenesCompra_action}"
                                                            binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.btnSeleccionarOrdenesCompra}" escape="false" id="btnSeleccionarOrdenesCompra"
                                                            mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:label binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.lblLineasOrdenCompra}" id="lblLineasOrdenCompra" styleClass="label2" text="Bienes"/>
                                            <ui:label binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.lblPagos}" id="lblPagos" styleClass="label2" text="Pagos"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.table1}" id="table1" width="239">
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
                                                <ui:tableRowGroup binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.tableRowGroup1}"
                                                    id="tableRowGroup2" sourceData="#{compras$ABMFacturaProveedor$GenerarLineasFactura.ldpLineasOrdenCompra}" sourceVar="currentRow">
                                                    <ui:tableColumn binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.tcBien}"
                                                        headerText="Bien" id="tcBien" sort="bien" width="40">
                                                        <ui:staticText binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.stBien}"
                                                            id="stBien" text="#{currentRow.value['bien']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.tcCantidad}"
                                                        headerText="Cantidad" id="tcCantidad" sort="cantidad" width="40">
                                                        <ui:staticText binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.stCantidad}"
                                                            id="stCantidad" text="#{currentRow.value['cantidad']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.tcMontoUnitario}"
                                                        headerText="Precio Unitario" id="tcMontoUnitario" sort="montoUnitario" width="40">
                                                        <ui:staticText binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.stMontoUnitario}"
                                                            id="stMontoUnitario" text="#{currentRow.value['montoUnitario']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.tcMontoTotal}"
                                                        headerText="Precio Total" id="tcMontoTotal" sort="montoTotal" width="40">
                                                        <ui:staticText binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.stMontoTotal}"
                                                            id="stMontoTotal" text="#{currentRow.value['montoTotal']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                            </ui:table>
                                            <ui:table augmentTitle="false" binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.table2}" id="table2" width="239">
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
                                                <ui:tableRowGroup binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.tableRowGroup2}"
                                                    id="tableRowGroup2" selected="#{compras$ABMFacturaProveedor$GenerarLineasFactura.currentRowSelected}"
                                                    sourceData="#{compras$ABMFacturaProveedor$GenerarLineasFactura.ldpPagos}" sourceVar="currentRow">
                                                    <ui:tableColumn align="center" binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.tableColumn1}"
                                                                            id="tableColumn1" onClick="setTimeout('initAllRows()', 0)" valign="middle" width="10">
                                                        <ui:checkbox binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.checkbox1}" id="checkbox1" name="buttonsGroup"
                                                                             selected="#{compras$ABMFacturaProveedor$GenerarLineasFactura.selected}" selectedValue="#{compras$ABMFacturaProveedor$GenerarLineasFactura.selectedValue}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.tcNombre}"
                                                        headerText="Nombre" id="tcNombre" sort="nombre" width="40">
                                                        <ui:staticText binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.stNombre}"
                                                            id="stUsuario" text="#{currentRow.value['nombre']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.tcMonto}"
                                                        headerText="Monto" id="tcMonto" sort="monto" width="40">
                                                        <ui:staticText binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.stMonto}"
                                                            id="stMonto" text="#{currentRow.value['monto']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.messageGroup}" id="messageGroup" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{compras$ABMFacturaProveedor$GenerarLineasFactura.btnGuardar_action}"
                                                binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.btnGuardar}" id="btnGuardar" styleClass="button"/>
                                            <ui:staticText binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.stSeparador}" escape="false"
                                                id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{compras$ABMFacturaProveedor$GenerarLineasFactura.btnCancelar_action}"
                                                binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.btnCancelar}" id="btnCancelar" styleClass="button"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.hidIdPagina}" id="hidIdPagina" text="#{compras$ABMFacturaProveedor$GenerarLineasFactura.idPagina}"/>
                        <ui:hiddenField binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.hidIdSubSesion}" id="hidIdSubSesion" text="#{compras$ABMFacturaProveedor$GenerarLineasFactura.idSubSesion}"/>
                        <ui:script binding="#{compras$ABMFacturaProveedor$GenerarLineasFactura.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>