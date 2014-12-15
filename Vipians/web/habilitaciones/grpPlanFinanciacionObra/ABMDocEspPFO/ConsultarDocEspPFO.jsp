<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" errorPage="/faces/comunes/ErrorHandler.jsp" isErrorPage="false" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.head1}" id="head1" title="Consultar Obligación: Plan de Financiación de Obra">
                    <ui:link binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242,242,242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                    <ui:form binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.form1}" id="form1">
                        <div class="formularioABM" style="left: -4px; top: 0px; position: absolute">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="4">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.label4}"
                                                for="tfFechaInicio" id="label4" rendered="false" styleClass="label" text="Inicio de Actividades"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.tfFechaInicio}"
                                                disabled="true" id="tfFechaInicio" maxLength="10" rendered="false" styleClass="textFieldDisabled"/>
                                            <!--<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.staticText1}"
                                                escape="false" id="staticText1" rendered="false" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.label3}" for="tfObra"
                                                id="label3" styleClass="label" text="Obra"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.tfObra}"
                                                columns="40" disabled="true" id="tfObra" readOnly="true" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.label6}"
                                                for="tfFechaCese" id="label6" rendered="false" styleClass="label" text="Cese de Actividades"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.tfFechaCese}"
                                                disabled="true" id="tfFechaCese" maxLength="10" rendered="false" styleClass="textFieldDisabled"/>
                                            <!--<ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.staticText2}"
                                                escape="false" id="staticText2" rendered="false" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
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
                                            <ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.label16}"
                                                for="tfPersona" id="label16" styleClass="label" text="Persona Solicitante"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.tfPersona}"
                                                columns="40" disabled="true" id="tfPersona" readOnly="true" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.label2}"
                                                for="tfPlanCuenta" id="label2" styleClass="label" text="Plan de Cuenta"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.tfPlanCuenta}"
                                                columns="40" disabled="true" id="tfPlanCuenta" readOnly="true" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.lblParcela}"
                                                for="tfParcela" id="lblParcela" styleClass="label" text="Parcela"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.tfParcela}"
                                                columns="40" disabled="true" id="tfParcela" readOnly="true" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap"></td>
                                        <td nowrap="nowrap"></td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.label1}" id="label1"
                                                styleClass="label" text="Domicilio Postal"/>
                                        </td>
                                        <td nowrap="nowrap"></td>
                                        <td align="right" nowrap="nowrap"></td>
                                        <td nowrap="nowrap"></td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap"></td>
                                        <td colspan="3">
                                            <ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.stDomicilioPostal}"
                                                escape="false" id="stDomicilioPostal" styleClass="staticText"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" style="height: 15px">
                                            <hr/>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.label7}" id="label7"
                                                styleClass="label2" text="Cuadras Afectadas"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false"
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.table1}" id="table1">
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
                                                <ui:tableRowGroup
                                                    binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.tableRowGroup1}"
                                                    id="tableRowGroup1" rows="#{ApplicationBean1.cantidadFilasTablasAdmin}"
                                                    sourceData="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.ldpCuadrasAfectadasPFO}" sourceVar="currentRow">
                                                    <ui:tableColumn
                                                        binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.tableColumn1}"
                                                        headerText="Cuadra" id="tableColumn1" sort="cuadra">
                                                        <ui:staticText
                                                            binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.staticText4}"
                                                            id="staticText4" text="#{currentRow.value['cuadra']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn
                                                        binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.tableColumn2}"
                                                        headerText="Metros" id="tableColumn2" sort="metros">
                                                        <ui:staticText
                                                            binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.staticText5}"
                                                            id="staticText5" text="#{currentRow.value['metros']} mts"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" style="height: 18px">
                                            <ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.label28}" id="label28"
                                                rendered="false" styleClass="label2" text="Modificaciones"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false"
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.table3}" id="table3"
                                                rendered="false" width="158">
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
                                                <ui:tableRowGroup
                                                    binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.tableRowGroup3}"
                                                    id="tableRowGroup3"
                                                    sourceData="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.ldpLogModificacionesPFO}" sourceVar="currentRow">
                                                    <ui:tableColumn align="center"
                                                        binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.tableColumn7}"
                                                        id="tableColumn7" rendered="false" valign="middle" width="10">
                                                        <ui:radioButton
                                                            binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.radioButton3}"
                                                            id="radioButton3" label="" name="buttonGroup3"
                                                            selected="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.RBSelected3}" selectedValue="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.currentRow3}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn
                                                        binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.tableColumn8}"
                                                        headerText="Número de Inscripción" id="tableColumn8" sort="numeroInscripcion">
                                                        <ui:staticText
                                                            binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.staticText8}"
                                                            id="staticText8" text="#{currentRow.value['numeroInscripcion']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn
                                                        binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.tableColumn16}"
                                                        headerText="Fecha" id="tableColumn16" sort="accion">
                                                        <ui:staticText
                                                            binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.staticText3}"
                                                            converter="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.dateTimeConverter1}"
                                                            id="staticText3" text="#{currentRow.value['fecha']}"/>
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
                                            <ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.label9}" id="label9"
                                                styleClass="label9" text="Exenciones"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.table5}"
                                                id="table5" width="480">
                                                <script><![CDATA[
/* ----- Functions for Table Preferences Panel ----- */
/*
 * Toggle the table preferences panel open or closed
 */
function togglePreferencesPanel() {
  var table = document.getElementById("form1:table5");
  table.toggleTblePreferencesPanel();
}
/* ----- Functions for Filter Panel ----- */
/*
 * Return true if the filter menu has actually changed,
 * so the corresponding event should be allowed to continue.
 */
function filterMenuChanged() {
  var table = document.getElementById("form1:table5");
  return table.filterMenuChanged();
}
/*
 * Toggle the custom filter panel (if any) open or closed.
 */
function toggleFilterPanel() {
  var table = document.getElementById("form1:table5");
  return table.toggleTableFilterPanel();
}
/* ----- Functions for Table Actions ----- */
/*
 * Initialize all rows of the table when the state
 * of selected rows changes.
 */
function initAllRows() {
  var table = document.getElementById("form1:table5");
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
  var table = document.getElementById("form1:table5");
  table.selectGroupRows(rowGroupId, selected);
}
/*
 * Disable all table actions if no rows have been selected.
 */
function disableActions() {
  // Determine whether any rows are currently selected
  var table = document.getElementById("form1:table5");
  var disabled = (table.getAllSelectedRowsCount() > 0) ? false : true;
  // Set disabled state for top actions
  document.getElementById("form1:table5:tableActionsTop:deleteTop").setDisabled(disabled);
  // Set disabled state for bottom actions
  document.getElementById("form1:table5:tableActionsBottom:deleteBottom").setDisabled(disabled);
}]]></script>
                                                <ui:tableRowGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.tableRowGroup5}"
                                                    id="tableRowGroup5"
                                                    sourceData="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.ldpExenciones}" sourceVar="currentRow">
                                                    <ui:tableColumn align="center"
                                                        binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.tableColumn20}" id="tableColumn20"
                                                        valign="middle" width="10">
                                                        <ui:radioButton binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.radioButton5}"
                                                            id="radioButton5" label="" name="buttonGroup5"
                                                            selected="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.RBSelected5}" selectedValue="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.currentRow5}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.tcExencion}" headerText="Exencion"
                                                                    id="tcExencion">
                                                            <ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.stExencion}" id="stExencion"
                                                                text="#{currentRow.value['stringRegistroExencion']}" />
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
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
                                            <ui:messageGroup binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap" style="height: 30px">
                                            <ui:button action="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.btnVolver_action}"
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.btnVolver}" id="btnVolver"
                                                styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                            <br/>
                            <br/>
                            <br/>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpPlanFinanciacionObra$ABMDocEspPFO$ConsultarDocEspPFO.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
