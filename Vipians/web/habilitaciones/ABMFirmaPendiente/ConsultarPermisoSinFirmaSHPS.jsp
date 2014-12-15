<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.page1}" id="page1">
            <ui:html binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.html1}" id="html1">
                <ui:head binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.head1}" id="head1" title="Permisos Sin Firmar: Salud, Higiene, Profilaxis y Seguridad">
                    <ui:link binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.body1}" focus="form1:tfNumeroInscripcion" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="4">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.label15}"
                                                for="tfNumeroInscripcion" id="label15" styleClass="label" text="Número de Inscripción"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tfNumeroInscripcion}"
                                                disabled="true" id="tfNumeroInscripcion" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.label2}" for="tfFechaInicio"
                                                id="label2" styleClass="label" text="Inicio de Actividades"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tfFechaInicio}"
                                                disabled="true" id="tfFechaInicio" maxLength="10" styleClass="textFieldDisabled"/>
                                            <!--<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.staticText11}"
                                                escape="false" id="staticText11" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.label5}" for="tfNombre"
                                                id="label5" styleClass="label" text="Nombre del Documento"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tfNombre}" columns="40"
                                                disabled="true" id="tfNombre" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.label3}" for="tfFechaCese"
                                                id="label3" styleClass="label" text="Cese de Actividades"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tfFechaCese}" disabled="true"
                                                id="tfFechaCese" maxLength="10" styleClass="textFieldDisabled"/>
                                            <!--<ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.staticText12}"
                                                escape="false" id="staticText12" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
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
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.label16}" for="tfPersona"
                                                id="label16" styleClass="label" text="Persona Solicitante"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tfPersona}" columns="40"
                                                disabled="true" id="tfPersona" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.lblLibretaSanitaria}"
                                                for="tfLibretaSanitaria" id="lblLibretaSanitaria" styleClass="label" text="Libreta Sanitaria"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tfLibretaSanitaria}"
                                                columns="40" disabled="true" id="tfLibretaSanitaria" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.label1}" id="label1"
                                                styleClass="label" text="Domicilio Postal"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.stDomicilioPostal}"
                                                escape="false" id="stDomicilioPostal" styleClass="staticText  "/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.label6}"
                                                for="tfDenominacionEntidad" id="label6" styleClass="label" text="Denominación de la Entidad"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tfDenominacionEntidad}"
                                                columns="40" disabled="true" id="tfDenominacionEntidad" styleClass="textFieldDisabled"/>
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
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.label4}" for="tfRubro"
                                                id="label4" styleClass="label" text="Rubro"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tfRubro}" columns="40"
                                                disabled="true" id="tfRubro" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <hr/>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.label7}" id="label7"
                                                styleClass="label2" text="Locales Comerciales"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.table1}"
                                                id="table1" width="359">
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
                                                <ui:tableRowGroup binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tableRowGroup1}"
                                                    id="tableRowGroup1" 
                                                    sourceData="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.ldpLocalComercialSHPS}" sourceVar="currentRow">
                                                    <ui:tableColumn align="center"
                                                        binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tableColumn1}"
                                                        id="tableColumn1" rendered="false" valign="middle" width="10">
                                                        <ui:radioButton binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.radioButton1}"
                                                            id="radioButton1" label="" name="buttonGroup"
                                                            selected="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.RBSelected}" selectedValue="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.currentRow}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tableColumn2}"
                                                        headerText="Nº Inscripción del Local" id="tableColumn2" noWrap="true" sort="numeroInscripcion">
                                                        <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.staticText2}"
                                                            escape="false" id="staticText2" text="#{currentRow.value['numeroInscripcion']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tableColumn3}"
                                                        headerText="Sup. Cubierta" id="tableColumn3" noWrap="true" sort="superficieCubiertaAfectada">
                                                        <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.staticText7}"
                                                            escape="false" id="staticText7" text="#{currentRow.value['superficieCubiertaAfectada']}&amp;nbsp;m&lt;sup&gt;2&lt;/sup&gt;"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tableColumn13}"
                                                        headerText="Sup. Semicubierta" id="tableColumn13" noWrap="true" sort="superficieSemicubiertaAfectada">
                                                        <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.staticText13}"
                                                            escape="false" id="staticText13" text="#{currentRow.value['superficieSemicubiertaAfectada']}&amp;nbsp;m&lt;sup&gt;2&lt;/sup&gt;"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tableColumn14}"
                                                        headerText="Descripción" id="tableColumn14" sort="descripcion">
                                                        <ui:textArea binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.textArea1}"
                                                            disabled="true" id="textArea1" styleClass="textFieldDisabled" text="#{currentRow.value['descripcion']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                                <f:facet name="actionsTop"/>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.label27}" id="label27"
                                                styleClass="label2" text="Transportes Vehiculares"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.table2}"
                                                id="table2" width="479">
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
                                                <ui:tableRowGroup binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tableRowGroup2}"
                                                    id="tableRowGroup2"
                                                    sourceData="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.ldpTransporteVehicularSHPS}" sourceVar="currentRow">
                                                    <ui:tableColumn align="center"
                                                        binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tableColumn11}"
                                                        id="tableColumn11" rendered="false" valign="middle" width="10">
                                                        <ui:radioButton binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.radioButton2}"
                                                            id="radioButton2" label="" name="buttonGroup2"
                                                            selected="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.RBSelected2}" selectedValue="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.currentRow2}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tableColumn4}"
                                                        headerText="Nº Inscripción del Transporte" id="tableColumn4" noWrap="true" sort="numeroInscripcion">
                                                        <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.staticText5}"
                                                            id="staticText5" text="#{currentRow.value['numeroInscripcion']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tableColumn17}"
                                                        headerText="Vehículo" id="tableColumn17" noWrap="true" sort="vehiculo">
                                                        <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.staticText4}"
                                                            id="staticText4" text="#{currentRow.value['vehiculo']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tableColumn5}"
                                                        headerText="Fecha Alta" id="tableColumn5" noWrap="true" sort="fechaAlta">
                                                        <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.staticText15}"
                                                            converter="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.dateTimeConverter1}"
                                                            id="staticText15" text="#{currentRow.value['fechaAlta']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tableColumn6}"
                                                        headerText="Fecha Baja" id="tableColumn6" noWrap="true" sort="fechaBaja">
                                                        <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.staticText16}"
                                                            converter="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.dateTimeConverter1}"
                                                            id="staticText16" text="#{currentRow.value['fechaBaja']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tableColumn15}"
                                                        headerText="Descripción" id="tableColumn15" sort="descripcion">
                                                        <ui:textArea binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.textArea2}"
                                                            columns="40" disabled="true" id="textArea2" styleClass="textFieldDisabled" text="#{currentRow.value['descripcion']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                                <f:facet name="actionsTop"/>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:label binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.label28}" id="label28"
                                                styleClass="label2" text="Permisos Sin Firmar"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.table3}" id="table3">
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
                                                <ui:tableRowGroup binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tableRowGroup3}"
                                                    id="tableRowGroup3" rows="#{ApplicationBean1.cantidadFilasTablasAdmin}"
                                                    selected="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.currentRowSelected}"
                                                    sourceData="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.ldpFirmaPendienteSHPS}" sourceVar="currentRow">
                                                    <ui:tableColumn align="center"
                                                        binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tableColumn7}"
                                                        id="tableColumn7" valign="middle" width="10">
                                                        <ui:checkbox binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.checkbox1}"
                                                            id="checkbox1" selected="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.selected}" selectedValue="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.selectedValue}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tableColumn8}"
                                                        headerText="Nombre" id="tableColumn8" sort="nombre">
                                                        <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.staticText1}"
                                                            id="staticText1" text="#{currentRow.value['nombre']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.tableColumn16}"
                                                        headerText="Estado" id="tableColumn16" sort="estado" width="600">
                                                        <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.staticText3}"
                                                            id="staticText3" text="#{currentRow.value['estado']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                                <f:facet name="actionsTop"/>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.btnFirmar_action}"
                                                binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.btnFirmar}" id="btnFirmar"
                                                styleClass="button" text="Firmar Permisos"/>
                                            <ui:staticText binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.stSeparador}" escape="false"
                                                id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.btnCancelar_action}"
                                                binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.btnCancelar}" id="btnCancelar"
                                                styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                            <br/>
                            <br/>
                            <br/>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$ABMFirmaPendiente$ConsultarPermisoSinFirmaSHPS.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
