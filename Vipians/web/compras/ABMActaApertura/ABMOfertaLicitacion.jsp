<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.page1}" id="page1">
            <ui:html binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.html1}" id="html1">
                <ui:head binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.head1}" id="head1">
                    <ui:link binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.link1}" id="link1" url="/resources/stylesheet.css"/>
                    <script><![CDATA[
                        
                        function calcular(input){
                            var locParentNode = input.parentNode;
                            var fila = locParentNode.parentNode;
                            
                            var cantidad = parseFloat(fila.cells[2].childNodes[0].innerHTML);
                            var precioUnitario = parseFloat(input.value);
                            fila.cells[5].childNodes[0].innerHTML = cantidad * precioUnitario;
                        }
                            
                    ]]></script>
                </ui:head>
                <ui:body binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.body1}" id="body1" onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{compras$ABMActaApertura$ABMOfertaLicitacion.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.lblProveedor}" for="tfProveedor" id="lblProveedor" styleClass="label" text="Proveedor"/>
                                        </td>
                                        <td nowrap="true">
                                            <ui:dropDown binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.ddProveedor}" items="#{compras$ABMActaApertura$ABMOfertaLicitacion.ddProveedorDefaultOptions.options}"
                                                         id="ddProveedor" styleClass="textField"/>
                                            <ui:textField binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.tfProveedor}" columns="40" disabled="true"
                                                              id="tfProveedor" styleClass="textField"/>
                                            <ui:button action="#{compras$ABMActaApertura$ABMOfertaLicitacion.btnSeleccionar_action}"
                                                binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.btnSeleccionar}" escape="false" id="btnSeleccionar"
                                                mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar"/>
                                        </td>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.lblPlazo}" for="tfPlazo" id="lblPlazo" styleClass="label" text="Plazo de mantenimiento"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.tfPlazo}" columns="40" id="tfPlazo" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr></tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:staticText binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.stRenglonesLicitacion}" id="stRenglonesLicitacion" styleClass="label2" text="Renglones de Contratación"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.table1}" id="table1" width="479">
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
                                                    <ui:tableRowGroup binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.tableRowGroup1}" id="tableRowGroup1"
                                                                      sourceData="#{compras$ABMActaApertura$ABMOfertaLicitacion.ldpOfertaRenglonesLicitacion}" sourceVar="currentRow">
                                                        <ui:tableColumn binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.tcBien}" headerText="Bien"
                                                                        id="tcBien" sort="bien">
                                                            <ui:staticText binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.stBien}" id="stBien" text="#{currentRow.value['bien']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.tcUnidad}" headerText="Unidad"
                                                                        id="tcUnidad" sort="unidad">
                                                            <ui:staticText binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.stUnidad}" id="stUnidad" text="#{currentRow.value['unidad']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.tcCantidad}" headerText="Cantidad"
                                                                        id="tcCantidad" sort="cantidadRenglon">
                                                            <ui:staticText binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.stCantidad}" id="stCantidad" text="#{currentRow.value['cantidadRenglon']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.tcPrecioUnitario}" headerText="Precio unitario"
                                                                        id="tcPrecioUnitario">
                                                            <ui:textField binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.tfPrecioUnitario}" id="tfPrecioUnitario" text="#{currentRow.value['precioUnitario']}" onBlur="calcular(this)" onKeyPress="return ValidarFloat(event,this)"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.tcComentario}" headerText="Comentario"
                                                                        id="tcComentario">
                                                            <ui:textArea binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.taComentario}" id="taComentario" text="#{currentRow.value['comentarios']}" />
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.tcPrecioTotal}" headerText="Precio total"
                                                                        id="tcPrecioTotal" sort="precioTotal">
                                                            <ui:staticText binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.stPrecioTotal}" id="stPrecioTotal" text="#{currentRow.value['precioTotal']}"/> 
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.messageGroup}" id="messageGroup" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="true">
                                            <ui:button action="#{compras$ABMActaApertura$ABMOfertaLicitacion.btnGuardar_action}"
                                                binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.btnGuardar}" id="btnGuardar" styleClass="button"/>
                                            <ui:staticText binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{compras$ABMActaApertura$ABMOfertaLicitacion.btnCancelar_action}"
                                                binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.btnCancelar}" id="btnCancelar" styleClass="button"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.hidIdPagina}" id="hidIdPagina" text="#{compras$ABMActaApertura$ABMOfertaLicitacion.idPagina}"/>
                        <ui:hiddenField binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.hidIdSubSesion}" id="hidIdSubSesion" text="#{compras$ABMActaApertura$ABMOfertaLicitacion.idSubSesion}"/>
                        <ui:script binding="#{compras$ABMActaApertura$ABMOfertaLicitacion.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>