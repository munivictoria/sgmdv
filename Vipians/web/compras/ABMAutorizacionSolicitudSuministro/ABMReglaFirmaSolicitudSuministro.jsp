<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui"
xmlns:a4j="https://ajax4jsf.dev.java.net/ajax">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.page1}" id="page1">
            <ui:html binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.html1}" id="html1">
                <ui:head binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.head1}" id="head1">
                    <ui:link binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.body1}" focus="form1:tfAnio" id="body1"
                    onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.stTitulo}"
                                                   id="stTitulo" styleClass="tituloABM" text="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="4">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.lblOrden}" 
                                                      for="tfOrden" id="lblOrden" styleClass="label" text="Orden de evaluación"/>
                                        </td>
                                        <td nowrap="nowrap">
                                                <ui:textField binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.tfOrden}" id="tfOrden" styleClass="textField" onKeyPress="return ValidarNum(event,this)"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
										<ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.lblUrgente}" for="cbUrgente" id="lblUrgente" styleClass="label"
											text="Urgente" />
										</td>
										<td>
											<ui:checkbox binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.cbUrgente}" id="cbUrgente" />
										</td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.lblUsuarios}" 
                                                      id="lblUsuarios" styleClass="label2" text="Usuarios que firman"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:table augmentTitle="false" binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.table1}" id="table1" width="239">
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
                                                <ui:tableRowGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.tableRowGroup1}"
                                                    id="tableRowGroup1"
                                                    sourceData="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.ldpUsuariosAutorizadores}" sourceVar="currentRow">
                                                    <ui:tableColumn align="center" binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.tableColumn1}"
                                                        id="tableColumn1" valign="middle" width="10">
                                                        <ui:radioButton binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.radioButton1}"
                                                            id="radioButton1" label="" name="buttonGroup"
                                                            selected="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.RBSelected}"
                                                            selectedValue="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.currentRow}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.tcNombre}"
                                                        headerText="Nombre" id="tcNombre" sort="user" width="40">
                                                        <ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.stUsuario}"
                                                            id="stUsuario" text="#{currentRow.value['usuario']}"/>
                                                    </ui:tableColumn>
                                                </ui:tableRowGroup>
                                                <f:facet name="actionsTop">
                                                    <ui:panelGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.groupPanel1}" id="groupPanel1">
                                                        <ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.lblListaUsuarios}" for="ddUsuarios" id="lblListaUsuarios" styleClass="label" text="Usuarios"/>
                                                        <ui:dropDown binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.ddUsuarios}" id="ddUsuarios" styleClass="textField"
                                                         items="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.ddUsuariosDefaultOptions.options}"/>
                                                        <ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.btnAgregar_action}"
                                                            binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.btnAgregar}" id="btnAgregar"
                                                            styleClass="button" text="Agregar"/>
                                                        <a4j:commandButton action="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.btnQuitar_action}"
                                                            binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.btnQuitar}" id="btnQuitar"
                                                            value="Quitar" styleClass="btnAjax" reRender="table1"/>
                                                        <ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.stSeparador1}" escape="false"
                                                            id="stSeparador1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                        <a4j:commandButton action="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.btnQuitarTodos_action}"
                                                            binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.btnQuitarTodos}" id="btnQuitarTodos"
                                                            value="Quitar todos" styleClass="btnAjax" reRender="table1"/>
                                                    </ui:panelGroup>
                                                </f:facet>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.lblEstado}" 
                                                      for="ddEstado" id="lblEstado" styleClass="label" text="Estado que toma la Solicitud"/>
                                        </td>
                                        <td>
                                            <ui:dropDown binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.ddEstado}" id="ddUsuarios" styleClass="textField"
                                                         items="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.ddEstadoDefaultOptions.options}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.messageGroup}" id="messageGroup" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.btnGuardar_action}"
                                                binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.btnGuardar}" id="btnGuardar" styleClass="button"/>
                                            <ui:staticText binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.stSeparador}" escape="false"
                                                id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.btnCancelar_action}"
                                                binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.btnCancelar}" id="btnCancelar" styleClass="button"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.hidIdPagina}" id="hidIdPagina" text="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.idPagina}"/>
                        <ui:hiddenField binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.hidIdSubSesion}" id="hidIdSubSesion" text="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.idSubSesion}"/>
                        <ui:script binding="#{compras$ABMAutorizacionSolicitudSuministro$ABMReglaFirmaSolicitudSuministro.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
