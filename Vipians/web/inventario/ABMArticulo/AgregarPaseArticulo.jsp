<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{inventario$ABMArticulo$AgregarPaseArticulo.page1}" id="page1">
            <ui:html binding="#{inventario$ABMArticulo$AgregarPaseArticulo.html1}" id="html1">
                <ui:head binding="#{inventario$ABMArticulo$AgregarPaseArticulo.head1}" id="head1" title="Pases de Area de Artículos">
                    <ui:link binding="#{inventario$ABMArticulo$AgregarPaseArticulo.link1}" id="link1" url="/resources/stylesheet.css"/>

                </ui:head>
                <ui:body binding="#{inventario$ABMArticulo$AgregarPaseArticulo.body1}" focus="form1:tfCodigoArticulo" id="body1"
                         onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{inventario$ABMArticulo$AgregarPaseArticulo.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{inventario$ABMArticulo$AgregarPaseArticulo.stTitulo}" id="stTitulo" styleClass="tituloABM" text="Pasar Artículo"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label1}" for="tfCodigoArticulo" id="label1"
                                                      styleClass="label" text="Código"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tfCodigoArticulo}" id="tfCodigoArticulo" styleClass="textField" disabled="true"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label2}" for="tfNombre" id="label2" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tfNombre}" columns="40" id="tfNombre" styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label3}" for="tfFechaCompra" id="label3"
                                                      styleClass="label" text="Fecha Compra"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tfFechaCompra}" id="tfFechaCompra" styleClass="textField" disabled="true" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:staticText binding="#{inventario$ABMArticulo$AgregarPaseArticulo.staticText1}" escape="false" id="staticText1"
                                                           styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label4}" for="tfFechaEntradaServicio" id="label4" styleClass="label" text="Fecha de Entrada en Servicio"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tfFechaEntradaServicio}" id="tfFechaEntradaServicio" styleClass="textField" disabled="true" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:staticText binding="#{inventario$ABMArticulo$AgregarPaseArticulo.staticText3}" escape="false" id="staticText3"
                                                           styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label14}" for="tfArea" id="label14"
                                                      styleClass="label" text="Área"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tfArea}" id="tfArea" maxLength="10" styleClass="textField" disabled="true"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label6}" for="tfCosto" id="label6" styleClass="label" text="Costo"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tfCosto}" id="tfCosto" onKeyPress="return ValidarFloat(event,this)" style="text-align:right; padding-right:6px;" styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label5}" for="taDescripcion" id="label5"
                                                      styleClass="label" text="Descripción"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{inventario$ABMArticulo$AgregarPaseArticulo.taDescripcion}" columns="40" id="taDescripcion"
                                                         rows="3" styleClass="textField" disabled="true"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label15}" for="tfEstado" id="label15"
                                                      styleClass="label" text="Estado"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tfEstado}" id="tfEstado" styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <table border="0" class="verde" style="border-style:dotted;">
                                                <tr>
                                                    <td align="left" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label7}" id="label7" styleClass="label2" text="Información Técnica"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label8}" for="tfMarca" id="label8"
                                                                  styleClass="label" text="Marca"/>
                                                    </td>
                                                    <td>
                                                        <ui:textField binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tfMarca}" id="tfMarca" styleClass="textField" disabled="true"/>
                                                    </td>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label9}" for="tfModelo" id="label9" styleClass="label" text="Modelo"/>
                                                    </td>
                                                    <td nowrap="nowrap">
                                                        <ui:textField binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tfModelo}" columns="40" id="tfModelo" styleClass="textField" disabled="true"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label10}" for="tfNumeroSerie" id="label10"
                                                                  styleClass="label" text="Numero de Serie"/>
                                                    </td>
                                                    <td>
                                                        <ui:textField binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tfNumeroSerie}" id="tfNumeroSerie" styleClass="textField" disabled="true"/>
                                                    </td>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label11}" for="tfMaterial" id="label11" styleClass="label" text="Material"/>
                                                    </td>
                                                    <td nowrap="nowrap">
                                                        <ui:textField binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tfMaterial}" columns="40" id="tfMaterial" styleClass="textField" disabled="true"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label12}" for="tfColor" id="label12"
                                                                  styleClass="label" text="Color"/>
                                                    </td>
                                                    <td>
                                                        <ui:textField binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tfColor}" id="tfColor" styleClass="textField" disabled="true"/>
                                                    </td>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label13}" id="label13" styleClass="label" text="Estado"/>
                                                    </td>
                                                    <td nowrap="nowrap">
                                                        <ui:dropDown binding="#{inventario$ABMArticulo$AgregarPaseArticulo.ddEstado}" id="ddEstado"
                                                                     items="#{inventario$ABMArticulo$AgregarPaseArticulo.ddEstadoDefaultOptions.options}" styleClass="textField" disabled="true"/>
                                                    </td>
                                                </tr>

                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <hr/>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="left" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label16}" id="label16" styleClass="label2" text="Estado de pases de Area"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{inventario$ABMArticulo$AgregarPaseArticulo.table1}" id="table1" width="239">
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
                                                    <ui:tableRowGroup binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tableRowGroup1}"
                                                                      emptyDataMsg="Ningún registro encontrado." id="tableRowGroup1"
                                                                      sourceData="#{inventario$ABMArticulo$AgregarPaseArticulo.ldpListaPasesArticulo}" sourceVar="currentRow">
                                                        <ui:tableColumn align="center" binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tableColumn4}"
                                                                        id="tableColumn4" width="10">
                                                            <ui:radioButton binding="#{inventario$ABMArticulo$AgregarPaseArticulo.radioButton1}" id="radioButton1"
                                                                            label="" name="buttonGroup" selected="#{inventario$ABMArticulo$AgregarPaseArticulo.RBSelected}" selectedValue="#{inventario$ABMArticulo$AgregarPaseArticulo.currentRow}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tableColumn1}" headerText="Area de Origen"
                                                                        id="tableColumn1" noWrap="true" sort="areaOrigen">

                                                        <ui:staticText binding="#{inventario$ABMArticulo$AgregarPaseArticulo.stOrigen}" id="stOrigen" text="#{currentRow.value['areaOrigen']}"/>

                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tableColumn2}" headerText="Area Destino"
                                                                    id="tableColumn2" sort="areaDestino">

                                                        <ui:staticText binding="#{inventario$ABMArticulo$AgregarPaseArticulo.stDestino}" id="stDestino" text="#{currentRow.value['areaDestino']}"/>

                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tableColumn3}" headerText="Fecha de Pase"
                                                                    id="tableColumn3" sort="fecha">

                                                        <ui:staticText binding="#{inventario$ABMArticulo$AgregarPaseArticulo.stFecha}" id="stFecha" converter="#{inventario$ABMArticulo$AgregarPaseArticulo.dateTimeConverter1}"  text="#{currentRow.value['fecha']}"/>

                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tableColumn5}" headerText="Usuario"
                                                                    id="tableColumn5" sort="usuario">

                                                        <ui:staticText binding="#{inventario$ABMArticulo$AgregarPaseArticulo.stUsuario}" id="stUsuario" text="#{currentRow.value['usuario']}"/>
                                                    </ui:tableColumn>

                                                </ui:tableRowGroup>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <hr/>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="left" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label19}" id="label19" styleClass="label2" text="Siguiente Pase"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label17}" for="tfAreaDestino" id="label17"
                                                      styleClass="label" text="Área Destino"/>
                                        </td>


                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tfAreaDestino}" id="tfAreaDestino" styleClass="textField" disabled="true" columns="33"/>
                                            <ui:button action="#{inventario$ABMArticulo$AgregarPaseArticulo.btnSeleccionarArea_action}"
                                                       binding="#{inventario$ABMArticulo$AgregarPaseArticulo.btnSeleccionarArea}" escape="false" id="btnSeleccionarArea"
                                                       mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" visible="true"/>
                                            <ui:button action="#{inventario$ABMArticulo$AgregarPaseArticulo.btnLimpiarArea_action}"
                                                       binding="#{inventario$ABMArticulo$AgregarPaseArticulo.btnLimpiarArea}" escape="false" id="btnLimpiarArea" mini="true"
                                                       styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar" visible="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label20}" for="tfFechaPase" id="label20"
                                                      styleClass="label" text="Fecha de Pase"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$AgregarPaseArticulo.tfFechaPase}" id="tfFechaPase" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:staticText binding="#{inventario$ABMArticulo$AgregarPaseArticulo.staticText4}" escape="false" id="staticText4"
                                                           styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$AgregarPaseArticulo.label18}" for="taComentarios" id="label18" styleClass="label" text="Comentarios"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{inventario$ABMArticulo$AgregarPaseArticulo.taComentarios}" columns="40" id="taComentarios"
                                                         rows="3" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="left" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{inventario$ABMArticulo$AgregarPaseArticulo.btnEfectuarPase_action}"
                                                       binding="#{inventario$ABMArticulo$AgregarPaseArticulo.btnEfectuarPase}" id="btnEfectuarPase" styleClass="button" text="Efectuar Pase"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{inventario$ABMArticulo$AgregarPaseArticulo.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{inventario$ABMArticulo$AgregarPaseArticulo.btnCancelar_action}"
                                                       binding="#{inventario$ABMArticulo$AgregarPaseArticulo.btnCancelar}" id="btnCancelar" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr> 
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{inventario$ABMArticulo$AgregarPaseArticulo.hidIdPagina}" id="hidIdPagina" text="#{inventario$ABMArticulo$AgregarPaseArticulo.idPagina}"/>
                        <ui:hiddenField binding="#{inventario$ABMArticulo$AgregarPaseArticulo.hidIdSubSesion}" id="hidIdSubSesion" text="#{inventario$ABMArticulo$AgregarPaseArticulo.idSubSesion}"/>
                        <ui:script binding="#{inventario$ABMArticulo$AgregarPaseArticulo.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
