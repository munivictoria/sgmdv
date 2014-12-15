<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.page1}" id="page1">
            <ui:html binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.html1}" id="html1">
                <ui:head binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.head1}" id="head1" title="Firmar Solicitud de Suministro">
                    <ui:link binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.body1}" focus="form1:tfArea" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.form1}" id="form1">
                        <div class="formularioABM" style="left: -4px; top: 0px; position: absolute">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.lblNro}" for="tfNro" id="lblNro"
                                                styleClass="label" text="Nº"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tfNro}" columns="15"
                                                disabled="true" id="tfNro" styleClass="textField"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.lblEstado}" id="lblEstado"
                                                      styleClass="label" text="Estado"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tfEstado}" id="tfEstado"
                                                          columns="20" styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.label4}" for="tfArea" id="label4"
                                                styleClass="label" text="Área"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tfArea}" columns="40"
                                                disabled="true" id="tfArea" styleClass="textField"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.label1}" id="label1"
                                                styleClass="label" text="Usuario Creador"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tfUsuario}" disabled="true"
                                                id="tfUsuario" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.label3}" for="tfFechaEmision"
                                                id="label3" styleClass="label" text="Fecha de Emisión"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tfFechaEmision}"
                                                disabled="true" id="tfFechaEmision" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.label5}" id="label5"
                                                styleClass="label" text="Descripción"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.taDescripcion}" columns="40"
                                                disabled="true" id="taDescripcion" rows="5" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:label binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.lblLineaSolSuministro}"
                                                id="lblLineaSolSuministro" styleClass="label2" text="Líneas de la Solicitud de Suministro"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.table1}"
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
                                                <ui:tableRowGroup binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tableRowGroup1}"
                                                    id="tableRowGroup1"
                                                    sourceData="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.ldpLineaSolSuministro}" sourceVar="currentRow">
                                                    <ui:tableColumn align="center"
                                                        binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tableColumn1}" id="tableColumn1"
                                                        valign="middle" width="10">
                                                        <ui:radioButton binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.radioButton1}"
                                                            id="radioButton1" label="" name="buttonGroup"
                                                            selected="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.RBSelected}" selectedValue="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.currentRow}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tableColumn2}"
                                                        headerText="Bien Asociado" id="tableColumn2" sort="bienAsociado" width="40">
                                                        <ui:staticText binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.staticText1}"
                                                            id="staticText1" text="#{currentRow.value['bienAsociado']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tableColumn5}"
                                                                    headerText="Cantidad" id="tableColumn5" width="40" sort="cantidad">
                                                        <ui:staticText binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.stCantidad}"
                                                            id="stCantidad" text="#{currentRow.value['cantidad']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tableColumn3}"
                                                        headerText="Precio Unitario" id="tableColumn3" sort="valorEstimado">
                                                        <ui:textField binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tfValorEst}"
                                                            disabled="true" id="tfValorEst" maxLength="10" styleClass="textFieldDisabled" text="#{currentRow.value['valorEstimado']}"/>
                                                    </ui:tableColumn>
                                                    <!--<ui:tableColumn binding="#{compras$ABMSolicitudSuministro$AgregarSolicitudSuministro.tableColumn4}"
                                                        headerText="Estado" id="tableColumn4" sort="estado">
                                                        <ui:textField binding="#{compras$ABMSolicitudSuministro$AgregarSolicitudSuministro.tfEstado}" id="tfEstado" disabled="true" maxLength="40"
                                                            styleClass="textFieldDisabled" text="#{currentRow.value['estado']}"/>
                                                    </ui:tableColumn>-->
                                                    <ui:tableColumn binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tableColumn11}"
                                                                    headerText="Precio Total" id="tableColumn11" sort="valorTotal">
                                                        <ui:textField binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tfValorTotal}" id="tfValorTotal" disabled="true" maxLength="10"
                                                                      styleClass="textFieldDisabled" text="#{currentRow.value['total']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tableColumn6}"
                                                        headerText="Cuenta Asociada" id="tableColumn6" sort="cuentaRfr">
                                                        <ui:staticText binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.stCuenta}"
                                                            id="stCuenta" text="#{currentRow.value['cuentaRfr']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:label binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.lblListaFirmas}"
                                                id="lblListaFirmas" styleClass="label2" text="Listado de Firmas De La Solicitud"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.table2}"
                                                id="table2" width="200">
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
                                                <ui:tableRowGroup binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tableRowGroup2}"
                                                    id="tableRowGroup2"
                                                    sourceData="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.ldpListaFirmaPermiso}" sourceVar="currentRow">
                                                    <ui:tableColumn align="center"
                                                        binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tableColumn7}" id="tableColumn7"
                                                        valign="middle" width="10">
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tableColumn8}"
                                                        headerText="Fecha - Hora" id="tableColumn8" sort="fechaHora">
                                                        <ui:staticText binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.staticText2}"
                                                            id="staticText2" text="#{currentRow.value['firmaPermiso'].fechaHora}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tableColumn9}"
                                                        headerText="Comentario" id="tableColumn9">
                                                        <ui:staticText binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.staticText3}"
                                                            id="staticText3" text="#{currentRow.value['firmaPermiso'].comentario}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.tableColumn10}"
                                                        headerText="Usuario" id="tableColumn10" sort="usuario">
                                                        <ui:staticText binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.staticText4}"
                                                            id="staticText4" text="#{currentRow.value['firmaPermiso'].usuario}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.btnGuardar_action}"
                                                       binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.btnGuardar}" id="btnGuardar"
                                                styleClass="button" text="Firmar Solicitud"/>
                                            <ui:staticText binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.stSeparador}" escape="false"
                                                id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.btnCancelar_action}"
                                                binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.btnCancelar}" id="btnCancelar"
                                                styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.hidIdPagina}" id="hidIdPagina" text="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.idPagina}"/>
                        <ui:hiddenField binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.hidIdSubSesion}" id="hidIdSubSesion" text="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.idSubSesion}"/>
                        <ui:script binding="#{compras$ABMSolicitudSuministro$FirmarSolicitudSuministro.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
