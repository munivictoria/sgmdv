<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.page1}" id="page1">
            <ui:html binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.html1}" id="html1">
                <ui:head binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.head1}" id="head1" title="Agregar Mediciones de Medidores">
                    <ui:link binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.link1}" id="link1" url="/resources/stylesheet.css"/>
                    <script><![CDATA[
                        var _decimales = 2;

                        function getById(id) {
                            elem = document.getElementById(id);
                            return elem;
                        }

                        function formatNumber(number) {
                            number = Math.round( number * Math.pow(10,_decimales) ) / Math.pow(10,_decimales);
                            cantDecimales = 0;
                            if (number.toString().indexOf(".") >= 0) cantDecimales = number.toString().substring( number.toString().indexOf(".")+1 ).length;
                            if (number == Math.floor(number)) number = number + ".";
                            for (i = 0; i < (_decimales-cantDecimales); i++) number += "0";
                            return number;
                        }

                        function calcular(input){
                            var locParentNode = input.parentNode;
                            var fila = locParentNode.parentNode;

                            var lecturaAnterior = fila.cells[3].childNodes[0].value;
                            var lecturaActual = input.value;                            
                            if(lecturaAnterior != ' '){
                                fila.cells[6].childNodes[0].value = lecturaActual - lecturaAnterior;
                            }
                            else{
                                fila.cells[6].childNodes[0].value = 0;
                            }

                            var tabla = fila.parentNode;
                            //calcularTotal(tabla);
                        }

                        function calcularTotal(tabla){
                            var rows = tabla.rows;
                            var total = 0;
                            for (var i = 0 ; i<rows.length;i++){
                                var valor = rows[i].cells[3].childNodes[0].value ;
                                if (!isNaN(valor)){
                                    total = Math.abs(total) - Math.abs(valor);
                                }
                            }
                            tfConsumo = getById("form1:tfConsumo");

                            tfConsumo.value = total;
                        }
]]></script>
                </ui:head>
                <ui:body binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.body1}" focus="form1:tfCalle" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.form1}" id="form1">
                        <div class="formularioABM">

                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.label1}" for="tfServicioOSP" id="label1"
                                                styleClass="label" text="Servicio"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.tfServicioOSP}" columns="40"
                                                disabled="true" id="tfServicioOSP" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.label5}" for="tfCalle" id="label5"
                                                styleClass="label" text="Calle"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.tfCalle}" columns="40" disabled="true"
                                                id="tfCalle" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.label3}" for="tfPeriodo" id="label3"
                                                styleClass="label" text="Período"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.tfPeriodo}" columns="40" disabled="true"
                                                id="tfPeriodo" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <hr/>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:label binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.label6}" id="label6" styleClass="label2" text="Valores de las Mediciones"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:table augmentTitle="false" binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.table1}" id="table1" width="871">
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
}

]]></script>
                                   <!-- PROBANDO             <ui:tableRowGroup binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.tableRowGroup1}"
                                                    id="tableRowGroup1"
                                                    sourceData="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.ldpRegistroValuado}" sourceVar="currentRow">
                                                    <ui:tableColumn align="center" binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.tableColumn1}"
                                                        id="tableColumn1" rendered="false" valign="middle" width="10">
                                                        <ui:radioButton binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.radioButton1}"
                                                            id="radioButton1" label="" name="buttonGroup"
                                                            selected="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.RBSelected}" selectedValue="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.currentRow}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.tableColumn3}"
                                                        headerText="Código Medidor" id="tableColumn3" sort="stringCodigoMedidor" width="20">
                                                        <ui:staticText binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.staticText5}" id="staticText5" text="#{currentRow.value['stringCodigoMedidor']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.tableColumn4}"
                                                        headerText="Persona" id="tableColumn4" sort="stringPersona" width="200">
                                                        <ui:staticText binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.staticText1}" id="staticText1" text="#{currentRow.value['stringPersona']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.tableColumn2}"
                                                        headerText="Dirección" id="tableColumn2" sort="stringDireccion" width="300">
                                                        <ui:staticText binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.staticText4}" id="staticText4" text="#{currentRow.value['stringDireccion']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.tableColumn7}" headerText="Lectura Anterior" id="tableColumn7"
                                                                    sort="stringLecturaAnterior" width="20">
                                                        <ui:textField binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.tfLecturaAnterior}" id="tfLecturaAnterior" disabled="true" 
                                                                      text="#{currentRow.value['stringLecturaAnterior']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.tableColumn5}" headerText="Fecha "
                                                        id="tableColumn5" sort="fecha" width="20">
                                                        <ui:textField binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.textField1}"
                                                            converter="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.dateTimeConverter1}" id="textField1"
                                                            maxLength="10" text="#{currentRow.value['fecha']}"/>
                                                        <ui:staticText binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.staticText2}" escape="false"
                                                            id="staticText2" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>
                                                        <ui:message binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.message1}" for="textField1"
                                                            id="message1" showDetail="false" showSummary="true"/>
                                                    </ui:tableColumn>
                                                   <ui:tableColumn binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.tableColumn6}"
                                                                   headerText="Lectura Actual" id="tableColumn6" sort="lectura" width="20">
                                                       <ui:textField binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.tfLectura}" id="tfLectura" text="#{currentRow.value['lectura']}" onBlur="calcular(this)" />
                                                        <ui:message binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.message2}" for="tfLectura"
                                                        id="message2" showDetail="false" showSummary="true"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.tableColumn8}" headerText="Consumo"
                                                                    id="tableColumn8" sort="consumo" width="20">
                                                        <ui:textField binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.tfConsumo}" id="tfConsumo" text="#{currentRow.value['montoImponible']}" />
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                                <f:facet name="actionsTop">
                                                </f:facet>   -->
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.messageGroup1}" id="messageGroup1"
                                                showGlobalOnly="true" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap" style="height: 24px">
                                            <ui:button action="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.btnGuardar_action}"
                                                binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.stSeparador}" escape="false"
                                                id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.btnCancelar_action}"
                                                binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.hidIdPagina}" id="hidIdPagina" text="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.idPagina}"/>
                        <ui:hiddenField binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.hidIdSubSesion}" id="hidIdSubSesion" text="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.idSubSesion}"/>
                    <ui:script binding="#{saic$grpTasaMenor$ABMRegistroValuadoTasaMenor$AgregarRegistroValuadoTasaMenor.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
