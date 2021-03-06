<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.page1}" id="page1">
            <ui:html binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.html1}" id="html1">
                <ui:head binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.head1}" id="head1" title="Eliminar Oferta Licitación">
                    <ui:link binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.label3}" for="tfLicitacion" id="label3" styleClass="label" text="Licitación"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.tfLicitacion}" columns="40" id="tfLicitacion" styleClass="textFieldDisabled"/>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.label2}" for="tfProveedor" id="label2" styleClass="label" text="Proveedor"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.tfProveedor}" columns="40" id="tfProveedor" styleClass="textFieldDisabled"/>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.label4}" for="tfFechaOferta" id="label4" styleClass="label" text="Fecha de Oferta"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.tfFechaOferta}" columns="10" id="tfFechaOferta" styleClass="textFieldDisabled"/>
                                            <!--<ui:label id="lblFormatoFechaOferta" styleClass="label" text=" [dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.label5}" for="tfImporte" id="label5" styleClass="label" text="Importe"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.tfImporte}" columns="30" id="tfImporte" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.label6}" for="tfGarantia" id="label6" styleClass="label" text="Garantía"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.tfGarantia}" columns="30" id="tfGarantia" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>                                    
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.label8}" for="tfPlazo" id="label8" styleClass="label" text="Plazo"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.tfPlazo}" columns="30" id="tfPlazo" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">                                            
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.label7}" id="label7" styleClass="label" text="Comentarios"/>
                                        </td>
                                        <td>                                            
                                            <ui:textArea binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.taComentariosOferta}" columns="40" id="taComentariosOferta" rows="5" styleClass="textFieldDisabled" />
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
                                            <ui:table augmentTitle="false" binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.table1}" id="table1">
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
                                                    <ui:tableRowGroup binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.tableRowGroup1}" id="tableRowGroup1"
                                                                      sourceData="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.ldpOfertaRenglonesLicitacion}" sourceVar="currentRow">
                                                    <!--
                                                    <ui:tableColumn align="center" binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.tableColumn1}"
                                                                    id="tableColumn1" valign="middle" width="10">
                                                        <ui:radioButton binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.radioButton1}" id="radioButton1" label=""
                                                                        name="buttonGroup" selected="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.RBSelected}" selectedValue="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.currentRow}"/>
                                                    </ui:tableColumn>
                                                    -->
                                                    <ui:tableColumn binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.tableColumn2}" headerText="Renglón Licitación"
                                                                    id="tableColumn2" sort="nombre" width="40">
                                                        <ui:staticText binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.staticText1}" id="staticText1" text="#{currentRow.value['renglonLicitacion']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.tableColumn3}" headerText="Precio Unitario"
                                                                    id="tableColumn3" sort="nombre" width="40">
                                                        <ui:staticText binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.staticText2}" id="staticText2" text="#{currentRow.value['precioUnitario']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.tableColumn4}" headerText="Precio Total"
                                                                    id="tableColumn4" sort="nombre" width="40">
                                                        <ui:staticText binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.staticText5}" id="staticText5" text="#{currentRow.value['precioTotal']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.tableColumn5}" headerText="Comentarios"
                                                                    id="tableColumn5" sort="nombre" width="40">
                                                        <ui:staticText binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.staticText6}" id="staticText6" text="#{currentRow.value['comentarios']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>                                                   
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>                            
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.btnGuardar_action}"
                                                       binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.btnGuardar}" id="btnGuardar" styleClass="button" text="Eliminar"/>
                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.btnCancelar_action}"
                                                       binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.hidIdPagina}" id="hidIdPagina" text="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.idPagina}"/>
                        <ui:hiddenField binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.hidIdSubSesion}" id="hidIdSubSesion" text="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.idSubSesion}"/>
                        <ui:script binding="#{compras$ABMOfertaLicitacion$EliminarOfertaLicitacion.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
