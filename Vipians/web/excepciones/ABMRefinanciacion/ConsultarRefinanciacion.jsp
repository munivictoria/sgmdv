<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.page1}" id="page1">
            <ui:html binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.html1}" id="html1">
                <ui:head binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.head1}" id="head1" title="Consultar Refinanciación">
                    <ui:link binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                    <ui:form binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.form1}" id="form1">
                        <div align="left" class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="3" nowrap="nowrap" style="height: 16px">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="left" colspan="3" nowrap="nowrap">
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfNombreRefinanciacion}" columns="60"
                                                disabled="true" id="tfNombreRefinanciacion" styleClass="textField2"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="left" colspan="3" nowrap="nowrap">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" style="height: 16px">
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblNroRefinanciacion}"
                                                for="tfNroRefinanciacion" id="lblNroRefinanciacion" styleClass="label" text="Número de Refinanciación"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfNroRefinanciacion}" columns="10"
                                                disabled="true" id="tfNroRefinanciacion" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" style="height: 16px">
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblFechaRefinanciacion}"
                                                for="tfFechaRefinanciacion" id="lblFechaRefinanciacion" styleClass="label" text="Fecha de Refinanciacion"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfFechaRefinanciacion}" columns="10"
                                                disabled="true" id="tfFechaRefinanciacion" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblEstado}" for="tfEstado" id="lblEstado"
                                                styleClass="label" text="Estado"/>
                                        </td>
                                        <td nowrap="true">
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfEstado}" columns="10"
                                                disabled="true" id="tfEstado" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" style="height: 16px">
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblContribuyente}" for="tfContribuyente"
                                                id="lblContribuyente" styleClass="label" text="Contribuyente"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfContribuyente}" columns="40"
                                                disabled="true" id="tfContribuyente" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" style="height: 33px">
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblDigesto}" for="tfDigesto"
                                                id="lblDigesto" styleClass="label" text="Digesto Municipal"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfDigesto}" columns="40"
                                                disabled="true" id="tfDigesto" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" style="height: 16px">
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblInmuebles}" id="lblInmuebles"
                                                styleClass="label" text="Inmuebles"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.taInmuebles}" columns="40"
                                                disabled="true" id="taInmuebles" rows="5" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" style="height: 16px">
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblComercios}" id="lblComercios"
                                                styleClass="label" text="Comercios"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.taComercios}" columns="40"
                                                disabled="true" id="taComercios" rows="5" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">
                                            <br/>
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblTitle1}" id="lblTitle1"
                                                styleClass="label2" text="Detalle de la Refinanciación"/>
                                            <hr/>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" style="height: 25px">
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblCantCuotas}" for="tfCantCuotas"
                                                id="lblCantCuotas" styleClass="label" text="Cantidad de Cuotas"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfCantCuotas}" columns="10"
                                                disabled="true" id="tfCantCuotas" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap" style="height: 16px">
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblTasaNominal}" for="tfTasaNominal"
                                                id="lblTasaNominal" styleClass="label" text="Tasa Nominal Anual"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfTasaNominal}" columns="10"
                                                disabled="true" id="tfTasaNominal" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" style="height: 21px">
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblInteresDiario}" for="tfInteresDiario"
                                                id="lblInteresDiario" styleClass="label" text="Interés Diario"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfInteresDiario}" columns="10"
                                                disabled="true" id="tfInteresDiario" styleClass="textField"/>
                                        </td>
                                        <td align="right" nowrap="nowrap"></td>
                                        <td align="right" nowrap="nowrap"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">
                                            <br/>
                                            <br/>
                                            <hr/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" colspan="3" nowrap="true">
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblImporteTotal}" for="tfImporteTotal"
                                                id="lblImporteTotal" styleClass="label" text="Importe Total :"/>
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfImporteTotal}" columns="15"
                                                disabled="true" id="tfImporteTotal" styleClass="textField"/>
                                            <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.stSeparador1}" escape="false"
                                                id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblImporteCondonado}"
                                                for="tfImporteCondonado" id="lblImporteCondonado" styleClass="label" text="Importe Condonado:"/>
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfImporteCondonado}" columns="15"
                                                disabled="true" id="tfImporteCondonado" styleClass="textField"/>
                                            <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.stSeparador2}" escape="false"
                                                id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblSubTotal}" for="tfSubTotal"
                                                id="lblSubTotal" style="true" styleClass="label" text="Importe Refinanciado:"/>
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfSubTotal}" columns="15"
                                                disabled="true" id="tfSubTotal" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" colspan="3" nowrap="true">
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblInteresTotal}" for="tfInteresTotal"
                                                id="lblInteresTotal" styleClass="label" text="Interés Total:"/>
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfInteresTotal}" columns="15"
                                                disabled="true" id="tfInteresTotal" styleClass="textField"/>
                                            <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.stSeparador3}" escape="false"
                                                id="stSeparador3" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblInteresCondonado}"
                                                for="tfInteresCondonado" id="lblInteresCondonado" styleClass="label" text="Interés Condonado:"/>
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfInteresCondonado}" columns="15"
                                                disabled="true" id="tfInteresCondonado" styleClass="textField"/>
                                            <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.stSeparador4}" escape="false"
                                                id="stSeparador4" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblInteresRefinanciacion}"
                                                for="tfInteresRefinanciacion" id="lblInteresRefinanciacion" styleClass="label" text="Interés Refinanciado:"/>
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfInteresRefinanciacion}"
                                                columns="15" disabled="true" id="tfInteresRefinanciacion" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" colspan="3" nowrap="true">
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblRecargoTotal}" for="tfRecargoTotal"
                                                id="lblRecargoTotal" styleClass="label" text="Recargo Total:"/>
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfRecargoTotal}" columns="15"
                                                disabled="true" id="tfRecargoTotal" styleClass="textField"/>
                                            <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.stSeparador5}" escape="false"
                                                id="stSeparador5" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblRecargoCondonado}"
                                                for="tfRecargoCondonado" id="lblRecargoCondonado" styleClass="label" text="Recargo Condonado:"/>
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfRecargoCondonado}" columns="15"
                                                disabled="true" id="tfRecargoCondonado" styleClass="textField"/>
                                            <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.stSeparador6}" escape="false"
                                                id="stSeparador6" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblRecargoRefinanciacion}"
                                                for="tfRecargoRefinanciacion" id="lblRecargoRefinanciacion" styleClass="label" text="Recargo Refinanciado:"/>
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfRecargoRefinanciacion}"
                                                columns="15" disabled="true" id="tfRecargoRefinanciacion" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" colspan="3" nowrap="nowrap">
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblMultaTotal}" for="tfMultaTotal"
                                                id="lblMultaTotal" styleClass="label" text="Multa Total"/>
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfMultaTotal}" columns="15"
                                                disabled="true" id="tfMultaTotal" styleClass="textField"/>
                                            <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.stSeparador7}" escape="false"
                                                id="stSeparador7" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblMultaCondonado}"
                                                for="tfMultaCondonado" id="lblMultaCondonado" styleClass="label" text="Multas Condonadas      :"/>
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfMultaCondonado}" columns="15"
                                                disabled="true" id="tfMultaCondonado" styleClass="textField"/>
                                            <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.stSeparador8}" escape="false"
                                                id="stSeparador8" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblMultaRefinanciacion}"
                                                for="tfMultaRefinanciacion" id="lblMultaRefinanciacion" styleClass="label" text="Multas Refinanciadas:"/>
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfMultaRefinanciacion}" columns="15"
                                                disabled="true" id="tfMultaRefinanciacion" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" colspan="3" nowrap="nowrap">
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblMontoTotal}" for="tfMontoTotal"
                                                id="lblMontoTotal" styleClass="label2" text="Monto Total: "/>
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfMontoTotal}" columns="15"
                                                disabled="true" id="tfMontoTotal" styleClass="textField"/>
                                            <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.stSeparador9}" escape="false"
                                                id="stSeparador9" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblTotalCondonado}"
                                                for="tfTotalCondonado" id="lblTotalCondonado" styleClass="label2" text="Total Condonado:"/>
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfTotalCondonado}" columns="15"
                                                disabled="true" id="tfTotalCondonado" styleClass="textField"/>
                                            <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.stSeparador10}" escape="false"
                                                id="stSeparador10" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblCapitalRefinanciado}"
                                                for="tfCapitalRefinanciado" id="lblCapitalRefinanciado" styleClass="label2" text="Capital a Pagar"/>
                                            <ui:textField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tfCapitalRefinanciado}" columns="15"
                                                disabled="true" id="tfCapitalRefinanciado" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <br/>
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblCuotasGeneradas}"
                                                id="lblCuotasGeneradas" styleClass="label2" text="Cuotas Generadas"/>
                                            <hr/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:table augmentTitle="false" binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.table1}" id="table1" width="95">
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
                                                <ui:tableRowGroup binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tableRowGroup1}"
                                                    id="tableRowGroup1"
                                                    sourceData="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.ldpCuotasGeneradas}" sourceVar="currentRow">
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tableColumn2}"
                                                        headerText="Cuota Nº" id="tableColumn2" sort="numeroCuota">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.staticText1}"
                                                            id="staticText1" text="#{currentRow.value['numeroCuota']}"/>
                                                    </ui:tableColumn>
                                                    <!--<ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tableColumn6}"
                                                        headerText="Período" id="tableColumn6" sort="periodo">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.staticText5}"
                                                            id="staticText5" text="#{currentRow.value['periodo']}"/>
                                                    </ui:tableColumn>-->
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tableColumn3}"
                                                        headerText="Monto" id="tableColumn3" sort="monto">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.staticText2}"
                                                            converter="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.numberConverter1}" id="staticText2" text="#{currentRow.value['monto']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tableColumn4}"
                                                        headerText="Interés" id="tableColumn4" sort="interes">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.staticText3}"
                                                            converter="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.numberConverter1}" id="staticText3" text="#{currentRow.value['interes']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tableColumn5}"
                                                        headerText="Recargo" id="tableColumn5" sort="recargo">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.staticText4}"
                                                            converter="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.numberConverter1}" id="staticText4" text="#{currentRow.value['recargo']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tableColumn9}"
                                                        headerText="Multa" id="tableColumn9" sort="multas">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.staticText8}"
                                                            converter="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.numberConverter1}" id="staticText8" text="#{currentRow.value['multas']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tableColumn8}"
                                                        headerText="Estado" id="tableColumn8" sort="estado">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.staticText7}"
                                                            id="staticText7" text="#{currentRow.value['estado']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tableColumn7}"
                                                        headerText="Fecha Vencimiento" id="tableColumn7" sort="fechaVencimiento">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.staticText6}"
                                                            converter="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.dateTimeConverter1}"
                                                            id="staticText6" text="#{currentRow.value['fechaVencimiento']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <br/>
                                            <ui:label binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.lblResumenDeuda}" id="lblResumenDeuda"
                                                styleClass="label2" text="Resumen de Deuda"/>
                                            <hr/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:table augmentTitle="false" binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.table2}" id="table2" width="95">
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
                                                <ui:tableRowGroup binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tableRowGroup2}"
                                                    id="tableRowGroup1" rows="15"
                                                    sourceData="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.ldpPeriodosAdeudados}" sourceVar="currentRow">
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tableColumn10}"
                                                        headerText="Período" id="tableColumn10" sort="stringPeriodoLiquidado">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.staticText9}"
                                                            id="staticText9" text="#{currentRow.value['stringPeriodoLiquidado']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tableColumn11}"
                                                        headerText="Obligación" id="tableColumn11" sort="stringObligacion">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.staticText10}"
                                                            id="staticText10" text="#{currentRow.value['stringObligacion']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tableColumn12}"
                                                        headerText="Monto" id="tableColumn12" sort="monto">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.staticText11}"
                                                            converter="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.numberConverter1}" id="staticText11" text="#{currentRow.value['monto']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tableColumn13}"
                                                        headerText="Interés" id="tableColumn13" sort="interes">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.staticText12}"
                                                            converter="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.numberConverter1}" id="staticText12" text="#{currentRow.value['interes']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tableColumn14}"
                                                        headerText="Recargo" id="tableColumn14" sort="recargo">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.staticText13}"
                                                            converter="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.numberConverter1}" id="staticText13" text="#{currentRow.value['recargo']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tableColumn15}"
                                                        headerText="Tipo de Deuda" id="tableColumn15" sort="tipoDeuda">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.staticText14}"
                                                            id="staticText14" text="#{currentRow.value['tipoDeuda']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.tableColumn16}"
                                                        headerText="Fecha de Vencimiento" id="tableColumn16" sort="fechaVencimiento">
                                                        <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.staticText15}"
                                                            converter="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.dateTimeConverter1}" id="staticText15" text="#{currentRow.value['fechaVencimiento']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap">
                                            <ui:button action="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.btnImprimirR_action}"
                                                binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.btnImprimirReconocimientoDeuda}"
                                                id="btnImprimirR" styleClass="button" text="Imprimir Reconocimiento de Deuda"
                                                onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')"/>
                                            <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.stSeparador12}" escape="false"
                                                id="stSeparador12" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.btnImprimirCuotasGeneradas_action}"
                                                binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.btnImprimirCuotasGeneradas}"
                                                id="btnImprimirCuotasGeneradas" styleClass="button" text="Imprimir Cuotas Refinanciación"
                                                onClick="newWindow = window.open('/Vipians/faces/ImpresionServlet', 'Reporte')"/>
                                            <ui:staticText binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.stSeparador11}" escape="false"
                                                id="stSeparador11" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.btnVolver_action}"
                                                binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.btnVolver}" id="btnVolver" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                            <br/>
                            <br/>
                            <br/>
                        </div>
                        <ui:hiddenField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.hidIdPagina}" id="hidIdPagina" text="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.idPagina}"/>
                        <ui:hiddenField binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.hidIdSubSesion}" id="hidIdSubSesion" text="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.idSubSesion}"/>
                        <ui:script binding="#{excepciones$ABMRefinanciacion$ConsultarRefinanciacion.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
