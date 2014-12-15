<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.page1}" id="page1">
            <ui:html binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.html1}" id="html1">
                <ui:head binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.head1}" id="head1">
                    <ui:link binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.body1}" focus="form1:btnSeleccionarLicitacion" id="body1" onLoad="parent.footer.location.reload(); Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.lblLicitacion}" for="tfLicitacion" id="lblLicitacion" styleClass="label" text="Licitación"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.tfLicitacion}" columns="40" id="tfLicitacion" styleClass="textField" disabled="true"/>
                                            <ui:button action="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnSeleccionarLicitacion_action}"
                                                       binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnSeleccionarLicitacion}" escape="false" id="btnSeleccionarLicitacion"
                                                       mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar"/>
                                            <ui:button action="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnLimpiarLicitacion_action}"
                                                       binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnLimpiarLicitacion}" escape="false" id="btnLimpiarLicitacion" mini="true"
                                                       styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.lblProveedor}" for="tfProveedor" id="lblProveedor" styleClass="label" text="Proveedor"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.tfProveedor}" columns="40" id="tfProveedor" styleClass="textField" disabled="true"/>
                                            <ui:button action="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnSeleccionarProveedor_action}"
                                                       binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnSeleccionarProveedor}" escape="false" id="btnSeleccionarProveedor"
                                                       mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar"/>
                                            <ui:button action="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnLimpiarProveedor_action}"
                                                       binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnLimpiarProveedor}" escape="false" id="btnLimpiarProveedor" mini="true"
                                                       styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.lblFechaOferta}" for="tfFechaOferta" id="lblFechaOferta" styleClass="label" text="Fecha de Oferta"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.tfFechaOferta}" id="tfFechaOferta" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:label id="lblFormatoFechaOferta" styleClass="label" text=" [dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.lblImporte}" for="tfImporte" id="lblImporte" styleClass="label" text="Importe"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.tfImporte}" onKeyPress="return ValidarFloat(event,this)" columns="30" id="tfImporte" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.lblGarantia}" for="tfGarantia" id="lblGarantia" styleClass="label" text="Garantía"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.tfGarantia}" columns="30" id="tfGarantia" styleClass="textField"/>
                                        </td>
                                    </tr>                                    
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.lblPlazo}" for="tfPlazo" id="lblPlazo" styleClass="label" text="Plazo"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.tfPlazo}" columns="30" id="tfPlazo" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">                                            
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.lblComentarios}" id="lblComentarios" styleClass="label" text="Comentarios"/>
                                        </td>
                                        <td>                                            
                                            <ui:textArea binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.taComentariosOferta}" columns="40" id="taComentariosOferta" rows="5" styleClass="textField" />
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
                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.staticText3}" id="staticText3" styleClass="label2" text="Renglones de la Licitación"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.table1}" id="table1">
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
                                                    <ui:tableRowGroup binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.tableRowGroup1}" id="tableRowGroup1"
                                                                      sourceData="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.ldpOfertaRenglonesLicitacion}" sourceVar="currentRow">
                                                        <ui:tableColumn align="center" binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.tableColumn1}"
                                                                        id="tableColumn1" valign="middle" width="10">
                                                            <ui:radioButton binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.radioButton1}" id="radioButton1" label=""
                                                                            name="buttonGroup" selected="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.RBSelected}" selectedValue="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.currentRow}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.tcRenglonLicitacion}" headerText="Renglón Licitación"
                                                                        id="tcRenglonLicitacion" sort="nombre" width="40">
                                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.stRenglonLicitacion}" id="stRenglonLicitacion" text="#{currentRow.value['renglonLicitacion']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.tcPrecioUnitario}" headerText="Precio Unitario"
                                                                        id="tcPrecioUnitario" sort="nombre" width="40">
                                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.stPrecioUnitario}" id="stPrecioUnitario" text="#{currentRow.value['precioUnitario']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.tcPrecioTotal}" headerText="Precio Total"
                                                                        id="tcPrecioTotal" sort="nombre" width="40">
                                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.stPrecioTotal}" id="stPrecioTotal" text="#{currentRow.value['precioTotal']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.tcComentarios}" headerText="Comentarios"
                                                                        id="tcComentarios" sort="nombre" width="40">
                                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.stComentarios}" id="stComentarios" text="#{currentRow.value['comentarios']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                    <f:facet name="actionsTop">
                                                        <ui:panelGroup binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.groupPanel1}" id="groupPanel1">
                                                            <ui:button action="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnAgregar_action}"
                                                                       binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar"/>
                                                            <ui:button action="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnQuitar_action}"
                                                                       binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnQuitar}" id="btnQuitar" styleClass="button" text="Quitar"/>
                                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.stSeparador1}" escape="false"
                                                                           id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                            <ui:button action="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnQuitarTodos_action}"
                                                                       binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnQuitarTodos}" id="btnQuitarTodos"
                                                                       styleClass="button" text="Quitar todos"/>
                                                        </ui:panelGroup>
                                                    </f:facet>
                                                </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.messageGroup}" id="messageGroup" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>                            
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnGuardar_action}"
                                                       binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnGuardar}" id="btnGuardar" styleClass="button"/>
                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnCancelar_action}"
                                                       binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.btnCancelar}" id="btnCancelar" styleClass="button"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.hidIdPagina}" id="hidIdPagina" text="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.idPagina}"/>
                        <ui:hiddenField binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.hidIdSubSesion}" id="hidIdSubSesion" text="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.idSubSesion}"/>
                        <ui:script binding="#{compras$ABMOfertaLicitacion$ABMOfertaLicitacion.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
