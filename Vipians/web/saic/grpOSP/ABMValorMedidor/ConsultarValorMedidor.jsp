<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.page1}" id="page1">
            <ui:html binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.html1}" id="html1">
                <ui:head binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.head1}" id="head1" title="Consultar Medición del Medidor">
                    <ui:link binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.body1}" focus="form1:tfFecha" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.lblObligacion}" for="taObligacion"
                                                      id="lblObligacion" styleClass="label" text="Obligación"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.taObligacion}" id="taObligacion"
                                                         styleClass="textField" columns="70" rows="4" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.lblServicio}" id="lblServicio" for="taServicio"
                                                      styleClass="label" text="Servicio"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.taServicio}" rows="3" columns="70" disabled="true"
                                                         id="taServicio" styleClass="textField" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>                                   
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.label1}" for="tfPersona" id="label1"
                                                styleClass="label" text="Persona"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.tfPersona}" columns="60" disabled="true"
                                                id="tfPersona" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.label6}" for="tfDireccion" id="label6"
                                                styleClass="label" text="Dirección"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.tfDireccion}" columns="60"
                                                disabled="true" id="tfDireccion" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.label5}" for="tfCodigoMedidor" id="label5"
                                                styleClass="label" text="Código Medidor"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.tfCodigoMedidor}" columns="15" disabled="true"
                                                id="tfCodigoMedidor" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td/>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:label binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.lblHistoricoMedicion}"
                                                id="lblHistoricoMedicion" styleClass="label2" text="Historial de Mediciones"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:table augmentTitle="false" binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.tblHistoricoMedicion}"
                                                id="tblHistoricoMedicion" width="871">
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
                                                <ui:tableRowGroup binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.tblRGHistoricoMedicion}" id="tblRGHistoricoMedicion"
                                                    sourceData="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.ldpHistoricoMedicion}" sourceVar="currentRow">
                                                    <ui:tableColumn binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.tblClmPeriodo}"
                                                        headerText="Período" id="tblClmPeriodo" sort="periodo" width="20">
                                                        <ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.sttClmPeriodo}"
                                                            id="sttClmPeriodo" text="#{currentRow.value['periodo']}"/>
                                                    </ui:tableColumn>
                                                    <ui:tableColumn binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.tblClmLecturaActual}"
                                                        headerText="Lectura Actual" id="tblClmLecturaActual" sort="lectura" width="20">
                                                        <ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.sttClmLecturaActual}"
                                                            id="sttClmLecturaActual" text="#{currentRow.value['lectura']}"/>
                                                    </ui:tableColumn>                                                    
                                                    <ui:tableColumn binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.tblClmConsumo}"
                                                        headerText="Consumo" id="tblClmConsumo" sort="montoImponible" width="20">
                                                        <ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.sttClmConsumo}"
                                                            id="sttClmConsumo" text="#{currentRow.value['montoImponible']}"/>
                                                    </ui:tableColumn>

                                                </ui:tableRowGroup>
                                            </ui:table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap" style="height: 24px">
                                            <ui:button action="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.btnCancelar_action}"
                                                binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.btnCancelar}" id="btnCancelar" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.hidIdPagina}" id="hidIdPagina" text="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.idPagina}"/>
                        <ui:hiddenField binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.hidIdSubSesion}" id="hidIdSubSesion" text="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.idSubSesion}"/>
                        <ui:script binding="#{saic$grpOSP$ABMValorMedidor$ConsultarValorMedidor.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
