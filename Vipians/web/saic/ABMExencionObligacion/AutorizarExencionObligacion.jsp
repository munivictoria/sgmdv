<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.page1}" id="page1">
            <ui:html binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.html1}" id="html1">
                <ui:head binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.head1}" id="head1" title="Autorizar Exenciones de Obligaciones">
                    <ui:link binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                             <ui:label binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.lblNombre}" for="tfNombre"
                                                      id="lblNombre" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.tfNombre}" columns="30"
                                                          disabled="true" id="tfNombre" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                             <ui:label binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.lblPorcentaje}" for="tfPorcentaje"
                                                      id="lblPorcentaje" styleClass="label" text="Porcentaje"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.tfPorcentaje}" id="tfPorcentaje"
                                            columns="10" disabled="true" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.lblMotivo}" for="taMotivo" id="lblMotivo" styleClass="label" text="Motivo"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.taMotivo}" columns="37" id="taMotivo" disabled="true" rows= "5" styleClass="textField"/>
                                       </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.lblDigestoMunicipal}" id="lblDigestoMunicipal"
                                                styleClass="label" text="Digesto Municipal"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.tfDigestoMunicipal}"
                                                columns="40" disabled="true" id="tfDigestoMunicipal" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.label7}" for="ddTipo" id="label7"
                                                styleClass="label" text="Tipo"/>
                                        </td>
                                        <td>
                                            <ui:dropDown binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.ddTipo}" id="ddTipo"
                                                items="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.ddTipoDefaultOptions.options}"
                                                disabled="true" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>  </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:label binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.label3}" id="label3" styleClass="label2" text="Período"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.label4}" for="tfAnio" id="label4"
                                                styleClass="label" text="Año"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.tfAnio}" columns="10" disabled="true"  id="tfAnio"
                                                maxLength="4" styleClass="textField"/>
                                            <ui:staticText binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.staticText5}" escape="false"
                                                id="staticText5" styleClass="label" text="&amp;nbsp;[aaaa]"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.label5}" for="ddPeriodicidad" id="label5"
                                                styleClass="label" text="Periodicidad"/>
                                        </td>
                                        <td>
                                            <ui:dropDown binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.ddPeriodicidad}" id="ddPeriodicidad" disabled="true"
                                                items="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.ddPeriodicidadDefaultOptions.options}" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.label6}" for="tfPeriodo" id="label6"
                                                styleClass="label" text="Período Número"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.tfPeriodo}" columns="10"
                                                id="tfPeriodo" styleClass="textField" disabled="true" />
                                        </td>
                                    </tr>
                                <tr>
                                    <td colspan="2">
                                        <ui:messageGroup binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="2">
                                        <ui:table binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.table1}" id="table1">
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
                                            <ui:tableRowGroup binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.tableRowGroup1}"
                                                emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1"
                                                sourceData="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.ldpExencion}" sourceVar="currentRow">
                                            
                                               <ui:tableColumn align="center" binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.tableColumn1}"
                                                    id="tableColumn1" valign="middle" width="10">
                                               </ui:tableColumn>

                                                <ui:tableColumn binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.tcObligacion}" headerText="Obligacion"
                                                                id="tcObligacion" sort="obligacion">
                                                    <ui:staticText binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.stObligacion}" id="stObligacion"

                                                                   text="#{currentRow.value['obligacion']}" />
                                                </ui:tableColumn>

                                                <ui:tableColumn binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.tcReferenciaNotaHCD}" headerText="Referencia Nota HCD" id="tcReferenciaNotaHCD" sort="referenciaNotaHCD">

                                                    <ui:textField binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.tfNotaHCD}" id="tfNotaHCD" columns="15"  disabled= "true" styleClass="textField" text="#{currentRow.value['referenciaNotaHCD']}"/>
                                                </ui:tableColumn>

                                            </ui:tableRowGroup>
                                           
                                        </ui:table>
                                    </td>
                                </tr>
                                 <tr>
                                    <td colspan="2">
                                        <ui:table binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.table2}" id="table2">
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
                                            <ui:tableRowGroup binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.tableRowGroup2}"
                                                emptyDataMsg="Ningún registro encontrado." id="tableRowGroup2"
                                                sourceData="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.ldpExencionFirma}" sourceVar="currentRow">
                                                <ui:tableColumn align="center" binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.tableColumn2}"
                                                        id="tableColumn2" valign="middle" width="10">
                                                </ui:tableColumn>
                                             
                                                <ui:tableColumn binding="#{saic$ABMExencionObligacion$ConsultarExencionObligacion.tcComentario}" headerText="Firma"
                                                                            id="tcComentario" sort="comentario">
                                                          <ui:staticText binding="#{saic$ABMExencionObligacion$ConsultarExencionObligacion.stComentario}" id="stComentario"
                                                                            text="#{currentRow.value['comentario']}" />
                                                </ui:tableColumn>
                                            </ui:tableRowGroup>
                                        </ui:table>
                                    </td>
                                </tr>
                              </tbody>
                              <tfoot>
                                <tr>
                                     <td align="right" colspan="6" nowrap="true">
                                         <ui:button action="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.btnAutorizar_action}"
                                            binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.btnAutorizar}" id="btnAutorizar" styleClass="button" text="Autorizar"/>
                                    </td>
                                    <td align="right" colspan="6" nowrap="true">
                                         <ui:button action="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.btnCancelar_action}"
                                            binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                    </td>
                                </tr>
                              </tfoot>
                            </table>
                         </div>
                        <ui:hiddenField binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.hidIdPagina}" id="hidIdPagina" text="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.idPagina}"/>
                        <ui:hiddenField binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.hidIdSubSesion}" id="hidIdSubSesion" text="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.idSubSesion}"/>
                        <ui:script binding="#{saic$ABMExencionObligacion$AutorizarExencionObligacion.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>