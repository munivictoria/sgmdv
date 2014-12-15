<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.page1}" id="page1">
            <ui:html binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.html1}" id="html1">
                <ui:head binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.head1}" id="head1" title="Autorizar Exenciones de Registro de Deuda">
                    <ui:link binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                             <ui:label binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.lblNombre}" for="tfNombre"
                                                      id="lblNombre" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.tfNombre}" columns="50"
                                                          disabled="true" id="tfNombre" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                             <ui:label binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.lblPorcentaje}" for="tfPorcentaje"
                                                      id="lblPorcentaje" styleClass="label" text="Porcentaje"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.tfPorcentaje}" id="tfPorcentaje"
                                            columns="10" disabled="true" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.lblMotivo}" for="taMotivo" id="lblMotivo" styleClass="label" text="Motivo"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.taMotivo}" columns="37" id="taMotivo" disabled="true" rows= "5" styleClass="textField"/>
                                       </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.lblDigestoMunicipal}" id="lblDigestoMunicipal"
                                                styleClass="label" text="Digesto Municipal"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.tfDigestoMunicipal}"
                                                columns="40" disabled="true" id="tfDigestoMunicipal" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <hr/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:label binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.label3}" id="label3" styleClass="label2" text="Período"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.label4}" for="tfAnio" id="label4"
                                                styleClass="label" text="Año"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.tfAnio}" columns="10" disabled="true"  id="tfAnio"
                                                maxLength="4" styleClass="textField"/>
                                            <ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.staticText5}" escape="false"
                                                id="staticText5" styleClass="label" text="&amp;nbsp;[aaaa]"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.label5}" for="ddPeriodicidad" id="label5"
                                                styleClass="label" text="Periodicidad"/>
                                        </td>
                                        <td>
                                            <ui:dropDown binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.ddPeriodicidad}" id="ddPeriodicidad" disabled="true"
                                                items="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.ddPeriodicidadDefaultOptions.options}" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.label6}" for="tfPeriodo" id="label6"
                                                styleClass="label" text="Período Número"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.tfPeriodo}" columns="10"
                                                id="tfPeriodo" styleClass="textField" disabled="true" />
                                        </td>
                                    </tr>

                                    <tr>
                                        <td colspan="2">
                                            <hr/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:label binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.label7}" id="label7" styleClass="label2" text="Periodicidad de la cuota"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.label8}" for="ddPeriodicidadCuota" id="label8"
                                                styleClass="label" text="Periodicidad"/>
                                        </td>
                                        <td>
                                            <ui:dropDown binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.ddPeriodicidadCuota}" id="ddPeriodicidadCuota" disabled="true"
                                                items="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.ddPeriodicidadCuotaDefaultOptions.options}" styleClass="textField"/>
                                        </td>
                                    </tr>


                                <tr>
                                    <td colspan="2">
                                        <ui:messageGroup binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="2">
                                        <ui:table binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.table1}" id="table1">
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
                                            <ui:tableRowGroup binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.tableRowGroup1}"
                                                emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1"
                                                sourceData="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.ldpExencion}" sourceVar="currentRow">
                                                <ui:tableColumn align="center" binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.tableColumn1}"
                                                    id="tableColumn1" valign="middle" width="10">
                                                </ui:tableColumn>
                                                <ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.tcPeriodo}" headerText="Período"
                                                    id="tcPeriodo" sort="stringPeriodoLiquidado">
                                                    <ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.stPeriodo}" id="stPeriodo" text="#{currentRow.value['stringPeriodoLiquidado']}"/>
                                                </ui:tableColumn>
                                                <ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.tcObligacion}" headerText="Obligacion"
                                                                id="tcObligacion" sort="obligacion">
                                                    <ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.stObligacion}" id="stObligacion" text="#{currentRow.value['stringObligacion']}"/>
                                                </ui:tableColumn>
                                                <ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.tcMontoExento}" headerText="Monto Exento"
                                                                id="tcMontoExento" sort="montoExencion">
                                                    <ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.stMontoExento}" id="stMontoExento" text="#{currentRow.value['montoExencion']}" />
                                                </ui:tableColumn>
                                                <ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.tcMonto}" headerText="Monto con Exención"
                                                                id="tcMonto" sort="monto">
                                                    <ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.stMonto}"
                                                                   converter="#{saic$ABMExencionRegistroDeuda$ModificarExencionRegistroDeuda.numberConverter1}" id="stMonto" text="#{currentRow.value['monto']}" />
                                                </ui:tableColumn>
                                        <!--                            NUEVO                               -->
                                                <ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.tcFechaVencimiento}" headerText="Fecha Vencimiento"
                                                                id="tcFechaVencimiento" sort="fechaVencimiento">
                                                    <ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.stFechaVencimiento}" id="stFechaVencimiento"
                                                                   converter="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.dateTimeConverter1}"
                                                                   text="#{currentRow.value['fechaVencimiento']}" />
                                                </ui:tableColumn>
                                      <!--                            Fin nuevo                           -->

                                                <ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.tcReferenciaNotaHCD}" headerText="Referencia Nota HCD" id="tcReferenciaNotaHCD" sort="referenciaNotaHCD">
                                                     <ui:textField binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.tfNotaHCD}" id="tfNotaHCD" columns="15" disabled="true" styleClass="textField" text="#{currentRow.value['referenciaNotaHCD']}"/>
                                                </ui:tableColumn>

                                            </ui:tableRowGroup>
                                           
                                        </ui:table>
                                    </td>
                                </tr>
                                 <tr>
                                    <td colspan="2">
                                        <ui:table binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.table2}" id="table2">
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
                                            <ui:tableRowGroup binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.tableRowGroup2}"
                                                emptyDataMsg="Ningún registro encontrado." id="tableRowGroup2"
                                                sourceData="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.ldpExencionFirma}" sourceVar="currentRow">

                                                <ui:tableColumn align="center" binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.tableColumn2}"
                                                        id="tableColumn2" valign="middle" width="10">
                                                </ui:tableColumn>
                                                <ui:tableColumn binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.tcComentario}" headerText="Firma"
                                                                            id="tcComentario" sort="comentario">
                                                          <ui:staticText binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.stComentario}" id="stComentario"
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
                                         <ui:button action="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.btnAutorizar_action}"
                                            binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.btnAutorizar}" id="btnAutorizar" styleClass="button" text="Autorizar"/>
                                    </td>
                                    <td align="right" colspan="6" nowrap="true">
                                         <ui:button action="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.btnCancelar_action}"
                                            binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                    </td>
                                </tr>
                              </tfoot>
                            </table>
                         </div>
                        <ui:hiddenField binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.hidIdPagina}" id="hidIdPagina" text="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.idPagina}"/>
                        <ui:hiddenField binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.hidIdSubSesion}" id="hidIdSubSesion" text="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.idSubSesion}"/>
                        <ui:script binding="#{saic$ABMExencionRegistroDeuda$AutorizarExencionRegistroDeuda.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>