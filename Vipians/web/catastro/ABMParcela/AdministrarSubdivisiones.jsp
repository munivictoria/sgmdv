<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{catastro$ABMParcela$AdministrarSubdivisiones.page1}" id="page1">
            <ui:html binding="#{catastro$ABMParcela$AdministrarSubdivisiones.html1}" id="html1">
                <ui:head binding="#{catastro$ABMParcela$AdministrarSubdivisiones.head1}" id="head1" title="Administrar Subdivisiones">
                    <ui:link binding="#{catastro$ABMParcela$AdministrarSubdivisiones.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{catastro$ABMParcela$AdministrarSubdivisiones.body1}" focus="form1:btnAgregarSubdivision" id="body1"
                         onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(236, 236, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{catastro$ABMParcela$AdministrarSubdivisiones.form1}" id="form1">
                        <div class="divAdmin">
                            <div class="formularioABM">
                                <table border="0" class="azul">
                                    <caption>
                                        <ui:staticText binding="#{catastro$ABMParcela$AdministrarSubdivisiones.stTitulo}" id="stTitulo"
                                                       styleClass="tituloABM" text="#{catastro$ABMParcela$AdministrarSubdivisiones.head1.title}"/>
                                    </caption>
                                    <tr>
                                    <td></td>
                                    </tr>
                                    <tbody>                                 
                                        <tr>
                                        <td align="left" nowrap="true">
                                            <ui:label binding="#{catastro$ABMParcela$AdministrarSubdivisiones.label1}" for="tfParcela" id="label1"
                                                      style="" styleClass="label2" text="Parcela: "/>                                        
                                            <ui:label binding="#{catastro$ABMParcela$AdministrarSubdivisiones.lblParcela}" styleClass="label2"
                                                          id="lblParcela"/>                                           
                                        </td>
                                        </tr>
                                        <tr>
                                        <td>
                                            <br></br>
                                        </td>
                                        </tr>     
                                        <!--
</tfoot>
                                        -->

                                        <!--
                                        </div>
                                        -->
                                        <tr>
                                        <td colspan="6">
                                            <table class="general">
                                                <tr>
                                                <td>
                                                    <ui:table binding="#{catastro$ABMParcela$AdministrarSubdivisiones.table1}" id="table1">
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
                                                            <ui:tableRowGroup binding="#{catastro$ABMParcela$AdministrarSubdivisiones.tableRowGroup1}" id="tableRowGroup1"
                                                                              emptyDataMsg="Ningún registro encontrado."
                                                                              sourceData="#{catastro$ABMParcela$AdministrarSubdivisiones.ldpSubparcelas}" sourceVar="currentRow">
                                                                <ui:tableColumn align="center" binding="#{catastro$ABMParcela$AdministrarSubdivisiones.tableColumn1}"
                                                                                id="tableColumn1" valign="middle" width="10">
                                                                    <ui:radioButton binding="#{catastro$ABMParcela$AdministrarSubdivisiones.radioButton1}" id="radioButton1"
                                                                                    label="" name="buttonGroup" selected="#{catastro$ABMParcela$AdministrarSubdivisiones.RBSelected}" selectedValue="#{catastro$ABMParcela$AdministrarSubdivisiones.currentRow}"/>
                                                                </ui:tableColumn>                                                            
                                                            <ui:tableColumn binding="#{catastro$ABMParcela$AdministrarSubdivisiones.tableColumn3}"
                                                                            headerText="Titular" id="tableColumn3" sort="titular">
                                                                <ui:staticText binding="#{catastro$ABMParcela$AdministrarSubdivisiones.staticText2}" id="staticText2" text="#{currentRow.value['titular']}"/>
                                                            </ui:tableColumn>
                                                            <ui:tableColumn binding="#{catastro$ABMParcela$AdministrarSubdivisiones.tableColumn4}" headerText="Superficie"
                                                                            id="tableColumn4">
                                                                <ui:staticText binding="#{catastro$ABMParcela$AdministrarSubdivisiones.staticText3}"
                                                                               id="staticText3" text="#{currentRow.value['superficie']}"/>
                                                            </ui:tableColumn>                                               
                                                        </ui:tableRowGroup>
                                                    <f:facet name="actionsTop">
                                                        <ui:panelGroup binding="#{catastro$ABMParcela$AdministrarSubdivisiones.groupPanel1}" id="groupPanel1">
                                                            <ui:button action="#{catastro$ABMParcela$AdministrarSubdivisiones.btnAgregarSubdivision_action}"
                                                                       binding="#{catastro$ABMParcela$AdministrarSubdivisiones.btnAgregarSubdivision}" id="btnAgregarSubdivision"                                                              
                                                                       styleClass="button" text="Agregar"/>
                                                            <ui:button action="#{catastro$ABMParcela$AdministrarSubdivisiones.btnQuitarSubdivision_action}"
                                                                       binding="#{catastro$ABMParcela$AdministrarSubdivisiones.btnQuitarSubdivision}" id="btnQuitarSubdivision"                                                                       
                                                                       styleClass="button" text="Quitar"/>  
                                                            <ui:staticText binding="#{catastro$ABMParcela$AdministrarSubdivisiones.staticText20}" escape="false"
                                                                           id="staticText20" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                            <ui:label binding="#{catastro$ABMParcela$AdministrarSubdivisiones.label42}" text="Superficie Dividida:"
                                                                     id="label42"/>
                                                            <ui:label binding="#{catastro$ABMParcela$AdministrarSubdivisiones.lblSuperficieDividida}" 
                                                                         id="lblSuperficieDividida"/>
                                                            <ui:staticText binding="#{catastro$ABMParcela$AdministrarSubdivisiones.staticText21}" escape="false"
                                                                           id="staticText21" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                            <ui:label binding="#{catastro$ABMParcela$AdministrarSubdivisiones.label43}" text="Superficie Total:"
                                                                      for="tfSuperficieDividida" id="label43"/>
                                                            <ui:label binding="#{catastro$ABMParcela$AdministrarSubdivisiones.lblSuperficieTotal}" 
                                                                          id="lblSuperficieTotal"/>
                                                        </ui:panelGroup>
                                                    </f:facet>
                                                </ui:table>
                                        </td>
                                        </tr>                                   
                                </table>
                                </td>                               
                                </tr>
                                <tr>
                                <td>
                                    <br/>
                                </td>
                                </tr>
                                <tr>
                                <td colspan="6" style="height: 22px">
                                    <ui:messageGroup binding="#{catastro$ABMParcela$AdministrarSubdivisiones.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                </td>
                                </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                    <td align="right" colspan="6" nowrap="nowrap">
                                        <ui:button action="#{catastro$ABMParcela$AdministrarSubdivisiones.btnGuardar_action}"
                                                   binding="#{catastro$ABMParcela$AdministrarSubdivisiones.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                        <ui:button action="#{catastro$ABMParcela$AdministrarSubdivisiones.btnGenerarObligacionesOSP_action}"
                                                           binding="#{catastro$ABMParcela$AdministrarSubdivisiones.btnGenerarObligacionesOSP}" disabled="false" id="btnGenerarObligacionesOSP"
                                                           styleClass="button" text="Generar Obligaciones OSP"/>
                                        <ui:staticText binding="#{catastro$ABMParcela$AdministrarSubdivisiones.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                        <ui:button action="#{catastro$ABMParcela$AdministrarSubdivisiones.btnCancelar_action}"
                                                   binding="#{catastro$ABMParcela$AdministrarSubdivisiones.btnCancelar}" id="btnCancelar" styleClass="button" text="Volver"/>
                                    </td>
                                    </tr>
                                </tfoot>
                                </table>
                            </div>
                        </div>
                        <script>
                    document.getElementById('form1:tfCodigo').focus();
                        </script>
                        <ui:hiddenField binding="#{catastro$ABMParcela$AdministrarSubdivisiones.hidIdPagina}" id="hidIdPagina" text="#{catastro$ABMParcela$AdministrarSubdivisiones.idPagina}"/>
                        <ui:hiddenField binding="#{catastro$ABMParcela$AdministrarSubdivisiones.hidIdSubSesion}" id="hidIdSubSesion" text="#{catastro$ABMParcela$AdministrarSubdivisiones.idSubSesion}"/>
                        <ui:script binding="#{catastro$ABMParcela$AdministrarSubdivisiones.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js"/>
                        <ui:script binding="#{catastro$ABMParcela$AdministrarSubdivisiones.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
