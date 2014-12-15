<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.page1}" id="page1">
            <ui:html binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.html1}" id="html1">
                <ui:head binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.head1}" id="head1" title="Modificar Oferta Licitación">
                    <ui:link binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.body1}" focus="form1:btnSeleccionarLicitacion" id="body1" onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.label3}" for="tfLicitacion" id="label3" styleClass="label" text="Licitación"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.tfLicitacion}" columns="40" id="tfLicitacion" styleClass="textField" disabled="true"/>
                                            <ui:button action="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnSeleccionarLicitacion_action}"
                                                       binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnSeleccionarLicitacion}" escape="false" id="btnSeleccionarLicitacion"
                                                       mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar"/>
                                            <ui:button action="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnLimpiarLicitacion_action}"
                                                       binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnLimpiarLicitacion}" escape="false" id="btnLimpiarLicitacion" mini="true"
                                                       styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.label2}" for="tfProveedor" id="label2" styleClass="label" text="Proveedor"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.tfProveedor}" columns="40" id="tfProveedor" styleClass="textField" disabled="true"/>
                                            <ui:button action="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnSeleccionarProveedor_action}"
                                                       binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnSeleccionarProveedor}" escape="false" id="btnSeleccionarProveedor"
                                                       mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar"/>
                                            <ui:button action="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnLimpiarProveedor_action}"
                                                       binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnLimpiarProveedor}" escape="false" id="btnLimpiarProveedor" mini="true"
                                                       styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.label4}" for="tfFechaOferta" id="label4" styleClass="label" text="Fecha de Oferta"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.tfFechaOferta}" id="tfFechaOferta" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:label id="lblFormatoFechaOferta" styleClass="label" text=" [dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.label5}" for="tfImporte" id="label5" styleClass="label" text="Importe"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.tfImporte}" onKeyPress="return ValidarFloat(event,this)" columns="30" id="tfImporte" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.label6}" for="tfGarantia" id="label6" styleClass="label" text="Garantía"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.tfGarantia}" columns="30" id="tfGarantia" styleClass="textField"/>
                                        </td>
                                    </tr>                                    
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.label8}" for="tfPlazo" id="label8" styleClass="label" text="Plazo"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.tfPlazo}" columns="30" id="tfPlazo" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">                                            
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.label7}" id="label7" styleClass="label" text="Comentarios"/>
                                        </td>
                                        <td>                                            
                                            <ui:textArea binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.taComentariosOferta}" columns="40" id="taComentariosOferta" rows="5" styleClass="textField" />
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
                                            <ui:staticText binding="#{compras$ABMProveedor$AgregarProveedor.staticText3}" id="staticText3" styleClass="label2" text="Renglones de la Licitación"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.table1}" id="table1">
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
                                                    <ui:tableRowGroup binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.tableRowGroup1}" id="tableRowGroup1"
                                                                      sourceData="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.ldpOfertaRenglonesLicitacion}" sourceVar="currentRow">
                                                        <ui:tableColumn align="center" binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.tableColumn1}"
                                                                        id="tableColumn1" valign="middle" width="10">
                                                            <ui:radioButton binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.radioButton1}" id="radioButton1" label=""
                                                                            name="buttonGroup" selected="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.RBSelected}" selectedValue="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.currentRow}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.tableColumn2}" headerText="Renglón Licitación"
                                                                        id="tableColumn2" sort="nombre" width="40">
                                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.staticText1}" id="staticText1" text="#{currentRow.value['renglonLicitacion']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.tableColumn3}" headerText="Precio Unitario"
                                                                        id="tableColumn3" sort="nombre" width="40">
                                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.staticText2}" id="staticText2" text="#{currentRow.value['precioUnitario']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.tableColumn4}" headerText="Precio Total"
                                                                        id="tableColumn4" sort="nombre" width="40">
                                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.staticText5}" id="staticText5" text="#{currentRow.value['precioTotal']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.tableColumn5}" headerText="Comentarios"
                                                                        id="tableColumn5" sort="nombre" width="40">
                                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.staticText6}" id="staticText6" text="#{currentRow.value['comentarios']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                                    <f:facet name="actionsTop">
                                                        <ui:panelGroup binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.groupPanel1}" id="groupPanel1">
                                                            <ui:button action="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnAgregar_action}"
                                                                       binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnAgregar}" id="btnAgregar" styleClass="button" text="Agregar"/>
                                                            <ui:button action="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnQuitar_action}"
                                                                       binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnQuitar}" id="btnQuitar" styleClass="button" text="Quitar"/>
                                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.staticText4}" escape="false"
                                                                           id="staticText4" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                            <ui:button action="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnQuitarTodos_action}"
                                                                       binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnQuitarTodos}" id="btnQuitarTodos"
                                                                       styleClass="button" text="Quitar todos"/>
                                                        </ui:panelGroup>
                                                    </f:facet>
                                                </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>                            
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnGuardar_action}"
                                                       binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnCancelar_action}"
                                                       binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.hidIdPagina}" id="hidIdPagina" text="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.idPagina}"/>
                        <ui:hiddenField binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.hidIdSubSesion}" id="hidIdSubSesion" text="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.idSubSesion}"/>
                        <ui:script binding="#{compras$ABMOfertaLicitacion$ModificarOfertaLicitacion.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
