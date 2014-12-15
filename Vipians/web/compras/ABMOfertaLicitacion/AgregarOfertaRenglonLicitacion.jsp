<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.page1}" id="page1">
            <ui:html binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.html1}" id="html1">
                <ui:head binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.head1}" id="head1" title="Agregar Renglón de Ofertas de Licitación ">
                    <ui:link binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.body1}" focus="form1:tfFechaDesde"
                         id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.stTitulo}"
                                                   id="stTitulo" styleClass="tituloABM" text="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>                                
                                    <tr>
                                        <td colspan="4">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.label6}" id="label6" styleClass="label2" text="Renglones de la Licitación"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.table1}" id="table1">
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
                                                    <ui:tableRowGroup binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.tableRowGroup1}" id="tableRowGroup1"
                                                                      sourceData="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.ldpRenglonesLicitacion}" sourceVar="currentRow">
                                                        <ui:tableColumn align="center" binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.tableColumn1}"
                                                                        id="tableColumn1" valign="middle" width="10">
                                                            <ui:radioButton binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.radioButton1}" id="radioButton1" label=""
                                                                            name="buttonGroup" selected="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.RBSelected}" selectedValue="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.currentRow}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.tableColumn2}" headerText="Nombre"
                                                                        id="tableColumn2" sort="nombre" width="40">
                                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.staticText1}" id="staticText1" text="#{currentRow.value['nombre']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.tableColumn3}" headerText="Descripción"
                                                                        id="tableColumn3" sort="descripcion" width="40">
                                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.staticText7}" id="staticText7" text="#{currentRow.value['descripcion']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.tableColumn4}" headerText="Cantidad"
                                                                        id="tableColumn4" sort="cantidad" width="40">
                                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.staticText8}" id="staticText8" text="#{currentRow.value['cantidad']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.tableColumn5}" headerText="Unidad de medida"
                                                                        id="tableColumn5" sort="unidad" width="40">
                                                            <ui:staticText binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.staticText9}" id="staticText9" text="#{currentRow.value['unidad']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>                                          
                                                </ui:table>
                                        </td>
                                    </tr>   
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.label1}"
                                                      for="tfPrecioUnitario" id="label1" styleClass="label" text="Precio Unitario"/>
                                        </td>
                                        <td nowrap="true">
                                            <ui:textField
                                                binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.tfPrecioUnitario}"
                                                columns="30" onKeyPress="return ValidarFloat(event,this)" id="tfPrecioUnitario" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <!--
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.label2}"
                                                      for="tfPrecioTotal" id="label2" styleClass="label" text="Precio Total"/>
                                        </td>
                                        <td nowrap="true">
                                            <ui:textField
                                                binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.tfPrecioTotal}"
                                                id="tfPrecioTotal" maxLength="10" styleClass="textField"/>                                        
                                        </td>
                                    </tr>   
                                    -->
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.label3}"
                                                      for="taComentario" id="label3" styleClass="label" text="Comentario"/>
                                        </td>
                                        <td nowrap="true">
                                            <ui:textArea
                                                binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.taComentario}"
                                                columns="40" id="taComentario" rows="5" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup
                                                binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="true">
                                            <ui:button
                                                action="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.btnGuardar_action}"
                                                binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.btnGuardar}"
                                                id="btnGuardar" styleClass="button" text="Aceptar"/>
                                            <ui:staticText
                                                binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.stSeparador}"
                                                escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button
                                                action="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.btnCancelar_action}"
                                                binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.btnCancelar}"
                                                id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.hidIdPagina}" id="hidIdPagina" text="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.idPagina}"/>
                        <ui:hiddenField binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.hidIdSubSesion}" id="hidIdSubSesion" text="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.idSubSesion}"/>
                        <ui:script binding="#{compras$ABMOfertaLicitacion$AgregarOfertaRenglonLicitacion.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
