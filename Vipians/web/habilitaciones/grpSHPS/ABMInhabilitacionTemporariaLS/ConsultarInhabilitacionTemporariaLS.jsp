<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.head1}" id="head1" title="Consultar Inhabilitación Temporaria de Libreta Sanitaria">
                    <ui:link binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.body1}"
                    focus="form1:tfFechaInhabilitacion" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                    <ui:form binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText
                                        binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.stTitulo}"
                                        id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.label1}"
                                                for="tfLibretaSanitaria" id="label1" styleClass="label" text="Libreta Sanitaria"/>
                                        </td>
                                        <td nowrap="true">
                                            <ui:textField
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.tfLibretaSanitaria}"
                                                columns="40" disabled="true" id="tfLibretaSanitaria" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.label2}"
                                                for="tfFechaInhabilitacion" id="label2" styleClass="label" text="Fecha Inhabilitación"/>
                                        </td>
                                        <td nowrap="true">
                                            <ui:textField
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.tfFechaInhabilitacion}"
                                                disabled="true" id="tfFechaInhabilitacion" styleClass="textFieldDisabled"/>
                                            <!--<ui:staticText
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.staticText2}"
                                                escape="false" id="staticText2" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.label5}"
                                                for="tfDiagnostico" id="label5" styleClass="label" text="Diagnóstico"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.tfDiagnostico}"
                                                columns="40" disabled="true" id="tfDiagnostico" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.label4}"
                                                for="tfPruebaConfirmatoria" id="label4" styleClass="label" text="Prueba Confirmatoria"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.tfPruebaConfirmatoria}"
                                                columns="40" disabled="true" id="tfPruebaConfirmatoria" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.label3}"
                                                for="tfFechaReintegro" id="label3" styleClass="label" text="Fecha Reintegro"/>
                                        </td>
                                        <td nowrap="true">
                                            <ui:textField
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.tfFechaReintegro}"
                                                disabled="true" id="tfFechaReintegro" styleClass="textFieldDisabled"/>
                                            <!--<ui:staticText
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.staticText1}"
                                                escape="false" id="staticText1" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="true">
                                            <ui:button
                                                action="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.btnVolver_action}"
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.btnVolver}"
                                                id="btnVolver" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.hidIdPagina}"
                            id="hidIdPagina" text="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.hidIdSubSesion}"
                            id="hidIdSubSesion" text="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$ConsultarInhabilitacionTemporariaLS.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
