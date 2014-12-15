<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.page1}" id="page1">
            <ui:html binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.html1}" id="html1">
                <ui:head binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.head1}" id="head1" title="Permisos Sin Firmar: Planes de Financiación de Obras">
                    <ui:link binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="4">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.label5}" for="tfNombre"
                                                id="label5" styleClass="label" text="Nombre del Documento"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.tfNombre}" columns="40"
                                                disabled="true" id="tfNombre" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.label4}" for="tfFechaInicio"
                                                id="label4" styleClass="label" text="Inicio de Actividades"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.tfFechaInicio}"
                                                disabled="true" id="tfFechaInicio" maxLength="10" styleClass="textFieldDisabled"/>
                                            <!--<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.staticText1}" escape="false"
                                                id="staticText1" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.label3}" id="label3"
                                                styleClass="label" text="Obra"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.tfObra}" columns="40"
                                                disabled="true" id="tfObra" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.label6}" for="tfFechaCese"
                                                id="label6" styleClass="label" text="Cese de Actividades"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.tfFechaCese}" disabled="true"
                                                id="tfFechaCese" maxLength="10" styleClass="textFieldDisabled"/>
                                            <!--<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.staticText2}" escape="false"
                                                id="staticText2" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <hr/>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.label16}" for="tfPersona"
                                                id="label16" styleClass="label" text="Persona Solicitante"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.tfPersona}" columns="40"
                                                disabled="true" id="tfPersona" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.label2}" for="tfPlanCuenta"
                                                id="label2" styleClass="label" text="Plan de Cuenta"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.tfPlanCuenta}" columns="40"
                                                disabled="true" id="tfPlanCuenta" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.lblParcela}" for="tfParcela"
                                                id="lblParcela" styleClass="label" text="Parcela"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.tfParcela}" columns="40"
                                                disabled="true" id="tfParcela" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap"></td>
                                        <td nowrap="nowrap"></td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.label1}" id="label1"
                                                styleClass="label" text="Domicilio Postal"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.stDomicilioPostal}"
                                                escape="false" id="stDomicilioPostal" styleClass="staticText     "/>
                                        </td>
                                        <td align="right" nowrap="nowrap"></td>
                                        <td nowrap="nowrap"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <hr/>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.label28}" id="label28"
                                                styleClass="label2" text="Permisos Sin Firmar"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.table1}" id="table1">
                                                <script><![CDATA[
/* ----- Functions for Table Preferences Panel ----- */
/*
 * Toggle the table preferences panel open or closed
 */
function togglePreferencesPanel() {
  var table = document.getElementById("form1:table3");
  table.toggleTblePreferencesPanel();
}
/* ----- Functions for Filter Panel ----- */
/*
 * Return true if the filter menu has actually changed,
 * so the corresponding event should be allowed to continue.
 */
function filterMenuChanged() {
  var table = document.getElementById("form1:table3");
  return table.filterMenuChanged();
}
/*
 * Toggle the custom filter panel (if any) open or closed.
 */
function toggleFilterPanel() {
  var table = document.getElementById("form1:table3");
  return table.toggleTableFilterPanel();
}
/* ----- Functions for Table Actions ----- */
/*
 * Initialize all rows of the table when the state
 * of selected rows changes.
 */
function initAllRows() {
  var table = document.getElementById("form1:table3");
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
  var table = document.getElementById("form1:table3");
  table.selectGroupRows(rowGroupId, selected);
}
/*
 * Disable all table actions if no rows have been selected.
 */
function disableActions() {
  // Determine whether any rows are currently selected
  var table = document.getElementById("form1:table3");
  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
  // Set disabled state for top actions
  document.getElementById("form1:table3:tableActionsTop:deleteTop").setDisabled(disabled);
  // Set disabled state for bottom actions
  document.getElementById("form1:table3:tableActionsBottom:deleteBottom").setDisabled(disabled);
}]]></script>
                                                <ui:tableRowGroup binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.tableRowGroup1}"
                                                    id="tableRowGroup1" rows="#{ApplicationBean1.cantidadFilasTablasAdmin}"
                                                    selected="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.currentRowSelected}"
                                                    sourceData="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.ldpFirmaPendientePFO}" sourceVar="currentRow">
                                                    <ui:tableColumn align="center"
                                                        binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.tableColumn7}" id="tableColumn7"
                                                        valign="middle" width="10">
                                                        <ui:checkbox binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.checkbox1}"
                                                            id="checkbox1" selected="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.selected}" selectedValue="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.selectedValue}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.tableColumn1}"
                                                        headerText="Nombre" id="tableColumn1" sort="nombre">
                                                        <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.staticText3}"
                                                            id="staticText3" text="#{currentRow.value['nombre']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.tableColumn8}"
                                                        headerText="Estado" id="tableColumn8" sort="estado">
                                                        <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.staticText8}"
                                                            id="staticText8" text="#{currentRow.value['estado']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                                <f:facet name="actionsTop"/>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.btnFirmar_action}"
                                                binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.btnFirmar}" id="btnFirmar"
                                                styleClass="button" text="Firmar Permisos"/>
                                            <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.stSeparador}" escape="false"
                                                id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.btnCancelar_action}"
                                                binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.btnCancelar}" id="btnCancelar"
                                                styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                            <br/>
                            <br/>
                            <br/>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaPFO.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
