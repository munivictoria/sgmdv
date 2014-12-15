<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.page1}" id="page1">
            <ui:html binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.html1}" id="html1">
                <ui:head binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.head1}" id="head1" title="Seleccione las líneas de Solicitudes de Suministros">
                    <ui:link binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.link1}" id="link1" url="/resources/stylesheet.css"/>
                    <script type="text/javascript"><![CDATA[
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
                   
                        function prueba(){
                            var lenght = document.getElementById('form1:table1').rows.length - 2;
                            //   var cant = document.getElementById('form1:tfCantidadBien');
                            //  var monto = document.getElementById('form1:tfMontoUnit');
                            // if(monto == null){ valorEstimado = formatNumber(0.0)}
                            var montoTotal = 0;
                            var montoLinea = 0;

                            for (var i=0;i<lenght;i++) {
                                var monto = formatNumber(document.getElementById('form1:table1:tableRowGroup1:'+i+':tableColumn5:tfMontoUnit').value);
                                var cant = formatNumber(document.getElementById('form1:table1:tableRowGroup1:'+i+':tableColumn3:tfCantidadBien').value);
                                document.getElementById('form1:table1:tableRowGroup1:'+i+':tableColumn6:tfMontoTotalLinea').value = formatNumber(parseFloat(monto)*parseFloat(cant));
                                montoLinea = formatNumber(document.getElementById('form1:table1:tableRowGroup1:'+i+':tableColumn6:tfMontoTotalLinea').value);
                                montoTotal += parseFloat(montoLinea);
                            }
                            
                            document.getElementById('form1:stMontoTotal').innerHTML =  formatNumber(parseFloat(montoTotal));
                        
                            //  document.getElementById('form1:tfMontoTotal').value = formatNumber(parseFloat(valorEstimado));

                        }

                    ]]></script>
                    </ui:head>
                    <ui:body binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.body1}" id="body1" onLoad="parent.footer.location.reload();Init();prueba();" style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                        <ui:form binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.form1}" id="form1">
                        <div class="divAdmin">
                            <div class="formularioABM">
                                <table border="0" class="verde">
                                    <caption>
                                        <ui:staticText binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.head1.title}"/>
                                    </caption>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tbody>
                                        <tr>
                                            <td align="right" nowrap="true">
                                                <ui:label binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.lblProveedor}" id="lblProveedor"
                                                          styleClass="label" text="Proveedor"/>
                                            </td>
                                            <td nowrap="nowrap">
                                                <ui:staticText binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.stProveedor}" id="stProveedor"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" style="height: 20px">
                                                <hr/>
                                                <br/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" style="height: 19px">
                                                <ui:label binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.lblTexto1}" id="lblTexto1"
                                                          styleClass="label2" text="Listado de Líneas de Solicitudes de Suministros"/>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4">
                                                <ui:table binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.table1}" id="table1">
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
                                                        <ui:tableRowGroup binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.tableRowGroup1}"
                                                                          emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1"
                                                                          selected="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.currentRowSelected}"
                                                                          sourceData="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.ldpLineasSoSuministro}" sourceVar="currentRow">
                                                            <ui:tableColumn align="center" binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.tableColumn7}"
                                                                            id="tableColumn7" onClick="setTimeout('initAllRows()', 0)" valign="middle" width="10">
                                                                <ui:checkbox binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.checkbox1}" id="checkbox1" name="buttonsGroup"
                                                                             selected="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.selected}" selectedValue="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.selectedValue}"/>
                                                            </ui:tableColumn>
                                                            <ui:tableColumn binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.tcNumeroSolicitud}"
                                                                            headerText="Nº Solicitud" id="tcNumeroSolicitud" sort="numeroSolicitud">
                                                                <ui:staticText binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.stNumeroSolicitud}"
                                                                               id="stNumeroSolicitud" text="#{currentRow.value['numeroSolicitud']}"/>
                                                            </ui:tableColumn>
                                                            <ui:tableColumn binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.tcArea}"
                                                                            headerText="Area" id="tcArea" sort="area">
                                                                <ui:staticText binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.stArea}"
                                                                               id="stArea" text="#{currentRow.value['area']}"/>
                                                            </ui:tableColumn>
                                                            <ui:tableColumn binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.tcBienAsociado}"
                                                                            headerText="Bien" id="tcBienAsociado" sort="bien">
                                                                <ui:staticText binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.stBienAsociado}"
                                                                               id="stBienAsociado" text="#{currentRow.value['bien']}"/>
                                                            </ui:tableColumn>
                                                            <ui:tableColumn binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.tcCantidad}"
                                                                            headerText="Cantidad" id="tcCantidad" sort="cantidad">
                                                               <ui:staticText binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.stCantidad}"
                                                                           id="stCantidad" text="#{currentRow.value['cantidad']}"/>
                                                            <!--<ui:textField binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.tfCantidadBien}"
                                                                          onBlur="prueba()" onKeyPress="return ValidarFloat(event,this)" id="tfCantidadBien" text="#{currentRow.value['cantidad']}"/>-->
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                    </ui:table>
                                    </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <div>
                                                <ui:messageGroup binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.messageGroup}" id="messageGroup"
                                                                 showDetail="true" showSummary="false" styleClass="grupoMsgAdmin"/>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td align="right" colspan="4" nowrap="nowrap">
                                                <ui:button action="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.btnAceptar_action}"
                                                           binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.btnAceptar}" id="btnAceptar" styleClass="button" text="Aceptar Líneas Orden"/>
                                                <ui:staticText binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.stSeparador2}" escape="false"
                                                               id="stSeparador2" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                <ui:button action="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.btnCancelar_action}"
                                                           binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                            </td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                        <ui:hiddenField binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.hidIdPagina}" id="hidIdPagina" text="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.idPagina}"/>
                        <ui:hiddenField binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.hidIdSubSesion}" id="hidIdSubSesion" text="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.idSubSesion}"/>
                        <ui:script binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js"/>
                        <ui:script binding="#{compras$ABMOrdenCompra$AgregarLineaOrdenCompra.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
