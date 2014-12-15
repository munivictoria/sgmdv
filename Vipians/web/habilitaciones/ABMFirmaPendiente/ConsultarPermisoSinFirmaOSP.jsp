<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.page1}" id="page1">
            <ui:html binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.html1}" id="html1">
                <ui:head binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.head1}" id="head1" title="Permisos Sin Firmar: Obras y Servicios Públicos">
                    <ui:link binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="4">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.label4}" id="label4"
                                                styleClass="label" text="Nombre del Documento"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.tfNombre}" columns="40"
                                                disabled="true" id="tfNombre" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.label2}" for="tfFechaInicio"
                                                id="label2" styleClass="label" text="Inicio de Actividades"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.tfFechaInicio}"
                                                disabled="true" id="tfFechaInicio" maxLength="10" styleClass="textFieldDisabled"/>
                                            <!--<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.staticText11}" escape="false"
                                                id="staticText11" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.label7}" for="tfNumeroCuenta"
                                                id="label7" styleClass="label" text="Cuenta/Subcuenta"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.tfNumeroCuenta}" columns="7"
                                                disabled="true" id="tfNumeroCuenta" maxLength="5" styleClass="textFieldDisabled"/>
                                            <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.staticText3}" escape="false"
                                                id="staticText3" text="&amp;nbsp;/&amp;nbsp;"/>
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.tfNumeroSubCuenta}"
                                                columns="4" disabled="true" id="tfNumeroSubCuenta" maxLength="3" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.label3}" for="tfFechaCese"
                                                id="label3" styleClass="label" text="Cese de Actividades"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.tfFechaCese}" disabled="true"
                                                id="tfFechaCese" maxLength="10" styleClass="textFieldDisabled"/>
                                            <!--<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.staticText12}" escape="false"
                                                id="staticText12" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
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
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.label16}" for="tfPersona"
                                                id="label16" styleClass="label" text="Persona Solicitante"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.tfPersona}" columns="40"
                                                disabled="true" id="tfPersona" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.label6}" for="tfServicioOSP"
                                                id="label6" styleClass="label" text="Servicio"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.tfServicioOSP}" columns="40"
                                                disabled="true" id="tfServicioOSP" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.lblParcela1}" for="tfParcela"
                                                id="lblParcela1" styleClass="label" text="Parcela"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.tfParcela}" columns="40"
                                                disabled="true" id="tfParcela" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.lblCodigoMedidor}"
                                                id="lblCodigoMedidor" styleClass="label" text="Código de Medidor"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.tfCodigoMedidor}"
                                                disabled="true" id="tfCodigoMedidor" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.label1}" id="label1"
                                                styleClass="label" text="Domicilio Postal"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.stDomicilioPostal}"
                                                escape="false" id="stDomicilioPostal" styleClass="staticText   "/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.lblCoeficienteZonal}"
                                                id="lblCoeficienteZonal" styleClass="label" text="Coeficiente Zonal"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.tfCoeficienteZonal}" disabled="true"
                                                id="tfCoeficienteZonal" styleClass="textFieldDisabled"/>
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
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.label5}" id="label5"
                                                styleClass="label2" text="Permisos Sin Firmar"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.table1}" id="table1">
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
                                                <ui:tableRowGroup binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.tableRowGroup1}"
                                                    id="tableRowGroup1" rows="#{ApplicationBean1.cantidadFilasTablasAdmin}"
                                                    selected="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.currentRowSelected}"
                                                    sourceData="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.ldpFirmaPendienteOSP}" sourceVar="currentRow">
                                                    <ui:tableColumn align="center"
                                                        binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.tableColumn1}" id="tableColumn1"
                                                        onClick="setTimeout('initAllRows()', 0)" valign="middle" width="10">
                                                        <ui:checkbox binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.checkbox1}"
                                                            id="checkbox1" selected="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.selected}" selectedValue="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.selectedValue}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.tableColumn3}"
                                                        headerText="Nombre" id="tableColumn3" sort="nombre">
                                                        <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.staticText1}"
                                                            id="staticText1" text="#{currentRow.value['nombre']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.tableColumn2}"
                                                        headerText="Estado" id="tableColumn2" sort="estado" width="600">
                                                        <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.staticText2}"
                                                            id="staticText2" text="#{currentRow.value['estado']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                                <f:facet name="actionsTop"/>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.btnFirmar_action}"
                                                binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.btnFirmar}" id="btnFirmar"
                                                styleClass="button" text="Firmar Permisos"/>
                                            <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.stSeparador}" escape="false"
                                                id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.btnCancelar_action}"
                                                binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.btnCancelar}" id="btnCancelar"
                                                styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                            <br/>
                            <br/>
                            <br/>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaOSP.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
