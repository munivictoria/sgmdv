<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.page1}" id="page1">
            <ui:html binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.html1}" id="html1">
                <ui:head binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.head1}" id="head1" title="Consultar Estado de Cuenta">
                    <ui:link binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                    <ui:form binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.label2}" id="label2" styleClass="label" text="Obligación"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.taObligacion}" columns="80" disabled="true"
                                                id="taObligacion" rows="3" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap"></td>
                                        <td align="right" nowrap="nowrap"></td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.lblEstado}" id="lblEstado" styleClass="label" text="Estado"/>
                                        </td>
                                        <td>
                                            <ui:dropDown binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.ddEstado}" id="ddEstado" styleClass="textField"
                                                         items="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.ddEstadoDefaultOptions.options}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <hr/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label id="lblPeriodo" styleClass="label2" text="Período"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.lblCalendarios}" id="lblCalendarios" styleClass="label" text="Calendarios"/>
                                        </td>
                                        <td>
                                            <ui:dropDown binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.ddCalendarios}" id="ddCalendarios" styleClass="textField"
                                                         items="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.ddCalendariosOptions.options}"
                                                         valueChangeListener="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.valueChangeEvent(event)}"
                                                         onChange="this.form.submit()" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.lblPeriodos}" id="lblPeriodos" styleClass="label" text="Periodos"/>
                                        </td>
                                        <td>
                                            <ui:dropDown binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.ddPeriodos}" id="ddPeriodos" styleClass="textField"
                                                         items="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.ddPeriodosOptions.options}"
                                                         valueChangeListener="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.valueChangeEventDdPeriodos(event)}"
                                                         onChange="this.form.submit()"/>
                                        </td>                                                                        
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.lblCuotas}" id="lblCuotas" styleClass="label" text="Cuotas"/>
                                        </td>
                                        <td>
                                            <ui:dropDown binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.ddCuotas}" id="ddCuotas" styleClass="textField"
                                                         items="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.ddCuotasOptions.options}"/>   
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap">
                                            <ui:button binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.btnBuscar}"
                                                       action="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.btnBuscar_action}" id="btnBuscar" text="Buscar" styleClass="button"/>
                                            <ui:staticText binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.staticText16}" escape="false"
                                                        id="staticText11" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.btnImprimir}" id="btnImprimir" styleClass="button"
                                                       action="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.btnImprimir_action}" text="Imprimir"
                                                       onClick="newWindow = window.open('ImprimirEstadoCuenta.jsp', '_parent')"/>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:label binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.label3}" id="label3" styleClass="label2" text="Períodos Liquidados"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:table augmentTitle="false" binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.table2}" id="table2" width="96">
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
                                                <ui:tableRowGroup binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.tableRowGroup2}" id="tableRowGroup2"
                                                    sourceData="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.ldpPeriodosAdeudados}" sourceVar="currentRow">
                                                    <ui:tableColumn binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.tableColumn9}" headerText="Período"
                                                        id="tableColumn9" sort="periodo">
                                                        <ui:staticText binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.staticText7}" id="staticText7" text="#{currentRow.value['stringPeriodoLiquidado']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.tableColumn15}" headerText="Vencimiento"
                                                                    id="tableColumn15" sort="vencimiento">
                                                        <ui:staticText binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.staticText13}" id="staticText13" converter="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.dateTimeConverter1}" text="#{currentRow.value['fechaVencimiento']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.tableColumn11}" headerText="Monto"
                                                        id="tableColumn11" sort="monto">
                                                        <ui:staticText binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.staticText9}" 
                                                                       converter="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.numberConverter1}" id="staticText9" text="#{currentRow.value['monto']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.tableColumn12}" headerText="Intereses"
                                                                    id="tableColumn12" sort="interes">
                                                        <ui:staticText binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.staticText10}"
                                                                       converter="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.numberConverter1}"
                                                                       id="staticText10" text="#{currentRow.value['interes']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.tableColumn13}" headerText="Recargos"
                                                        id="tableColumn13" sort="recargo">
                                                        <ui:staticText binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.staticText11}"
                                                                       converter="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.numberConverter1}"
                                                                       id="staticText11" text="#{currentRow.value['recargo']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.tableColumn14}" headerText="Multas"
                                                        id="tableColumn14" sort="multas">
                                                        <ui:staticText binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.staticText12}" id="staticText12" text="#{currentRow.value['multas']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.tableColumn17}" headerText="Tipo de Deuda"
                                                                    id="tableColumn17" sort="tipoDeuda">
                                                        <ui:staticText binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.staticText15}" id="staticText15" text="#{currentRow.value['tipoDeuda']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.tableColumn16}" headerText="Estado"
                                                                    id="tableColumn16" sort="estado">
                                                        <ui:staticText binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.staticText14}" id="staticText14" text="#{currentRow.value['estado']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.tableColumn18}" id="tableColumn18" headerText="Fecha de Cancelación"
                                                                    sort="fechaCancelación">
                                                        <ui:staticText binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.staticText17}" id="staticText17" text="#{currentRow.value['fechaCancelacion']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                                <f:facet name="actionsTop"/>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap">
                                            <ui:button action="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.btnVolver_action}"
                                                binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.btnVolver}" id="btnVolver" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                            <br/>
                            <br/>
                            <br/>
                        </div>
                        <ui:hiddenField binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.hidIdPagina}" id="hidIdPagina" text="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.idPagina}"/>
                        <ui:hiddenField binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.hidIdSubSesion}" id="hidIdSubSesion" text="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.idSubSesion}"/>
                        <ui:script binding="#{saic$ABMEstadoCuenta$ConsultarEstadoCuenta.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
