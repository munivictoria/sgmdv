<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.head1}" id="head1">
                    <ui:link binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.link1}" id="link1" url="/resources/stylesheet.css"/>
                    <script><![CDATA[
                        /**
                         * Genera el Nombre de Variable
                         */
                        function armarNombreVariable(tfOrigen, idTfDestino) {
                            valor = tfOrigen.value;
                            if (!isNaN(valor.substring(0,1))) valor = "VAR_" + valor;
                            separador = "_";
                            txtNomVar = document.getElementById(idTfDestino);
                            valor = valor.replace(/ /g,separador);
                            valor = valor.replace(/á/g,"a");
                            valor = valor.replace(/é/g,"e");
                            valor = valor.replace(/í/g,"i");
                            valor = valor.replace(/ó/g,"o");
                            valor = valor.replace(/ú/g,"u");
                            valor = valor.replace(/\./g,separador);
                            valor = valor.replace(/\\/g,separador);
                            valor = valor.replace(/\+/g,separador);
                            valor = valor.replace(/\-/g,separador);
                            valor = valor.replace(/\*/g,separador);
                            valor = valor.replace(/\//g,separador);
                            valor = valor.replace(/\"/g,separador);
                            valor = valor.replace(/\'/g,separador);
                            valor = valor.replace(/\#/g,separador);
                            valor = valor.replace(/\(/g,separador);
                            valor = valor.replace(/\)/g,separador);
                            valor = valor.replace(/\=/g,separador);
                            valor = valor.replace(/\&/g,separador);
                            valor = valor.replace(/\?/g,separador);
                            valor = valor.replace(/\¿/g,separador);
                            valor = valor.replace(/\%/g,separador);
                            txtNomVar.value = valor.toUpperCase();
                        }
                    ]]></script>
                    </ui:head>
                    <ui:body binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.body1}" focus="form1:tfNombre"
                             id="body1" onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                        <ui:form binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.stTitulo}"
                                                   id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.label4}"
                                                for="tfNombre" id="label4" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.tfNombre}"
                                                columns="40" id="tfNombre" onKeyUp="armarNombreVariable(this, 'form1:tfNombreVariable');" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.label1}"
                                                for="tfNombreVariable" id="label1" styleClass="label" text="Nombre de Variable"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.tfNombreVariable}"
                                                columns="40" id="tfNombreVariable" onBlur="armarNombreVariable(this, this.id);" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.lblZonificacion}" for="tfZonificacion" id="lblZonificacion" styleClass="label" text="Zonificación"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.tfZonificacion}" columns="40" disabled="true" id="tfZonificacion" styleClass="textField"/>
                                            <ui:button action="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.btnSeleccionarZonificacion_action}"
                                                       binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.btnSeleccionarZonificacion}" escape="false" id="btnSeleccionarZonificacion"
                                                       mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar"/>
                                        </td>
                                        <td colspan="2"></td>
                                    </tr>

                                    <tr>
                                        <td colspan="2">
                                            <hr/>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:label
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.label5}"
                                                id="label5" styleClass="label2" text="Zonas"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:table augmentTitle="false"
                                                      binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.table1}"
                                                      id="table1" width="240">
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
                                                    <ui:tableRowGroup
                                                        binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.tableRowGroup1}"
                                                        id="tableRowGroup1"
                                                        sourceData="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.ldpZonasTipoParamGrupoZona}" sourceVar="currentRow">
                                                        <ui:tableColumn
                                                            binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.tableColumn1}"
                                                            headerText="Zona" id="tableColumn1" sort="zona">
                                                            <ui:staticText
                                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.staticText1}"
                                                                id="staticText1" text="#{currentRow.value['zona']}"/>
                                                        </ui:tableColumn>
                                                        <ui:tableColumn
                                                            binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.tableColumn2}"
                                                            headerText="Valor" id="tableColumn2" sort="valor">
                                                            <ui:textField
                                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.tfValor}"
                                                                id="tfValor" styleClass="textField" text="#{currentRow.value['valor']}"/>
                                                        </ui:tableColumn>
                                                    </ui:tableRowGroup>
                                    <f:facet name="actionsTop"/>
                                </ui:table>
                                </td>
                                </tr>
                                <tr><td colspan="4"><br/></td></tr>
								<tr><td align="right"><ui:label binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.lblComentarioLogAuditoria}" id="lblComentarioLogAuditoria"/></td>
								<td><ui:textArea binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.taComentarioLogAuditoria}" id="taComentarioLogAuditoria"/></td></tr><tr><td><br/></td></tr>
								<tr><td colspan="4"><ui:table binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.tablaLogs}" id="tbLogsAuditoria"/></td></tr>
                                <tr>
                                    <td colspan="2">
                                        <ui:messageGroup
                                            binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.messageGroup1}"
                                            id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap" style="height: 24px">
                                            <ui:button
                                                action="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.btnGuardar_action}"
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.btnGuardar}"
                                                id="btnGuardar" styleClass="button"/>
                                            <ui:staticText
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.stSeparador}"
                                                escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button
                                                action="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.btnCancelar_action}"
                                                binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.btnCancelar}"
                                                id="btnCancelar" styleClass="button"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.hidIdPagina}"
                                        id="hidIdPagina" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.hidIdSubSesion}"
                                        id="hidIdSubSesion" text="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpTipoParametro$ABMTipoParametroGrupoZona$ABMTipoParametroGrupoZona.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
