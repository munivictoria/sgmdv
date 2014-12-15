<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.head1}" id="head1" title="Agregar Inhabilitación Temporaria de Libreta Sanitaria">
                    <ui:link binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.body1}"
                    focus="form1:tfFechaInhabilitacion" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.stTitulo}"
                                        id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.label1}"
                                                for="tfLibretaSanitaria" id="label1" styleClass="label" text="Libreta Sanitaria"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.tfLibretaSanitaria}"
                                                columns="40" disabled="true" id="tfLibretaSanitaria" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap" style="height: 22px">
                                            <ui:label binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.label2}"
                                                for="tfFechaInhabilitacion" id="label2" styleClass="label" text="Fecha Inhabilitación"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.tfFechaInhabilitacion}"
                                                id="tfFechaInhabilitacion" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:staticText
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.staticText2}"
                                                escape="false" id="staticText2" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap" style="height: 18px">
                                            <ui:label binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.label5}"
                                                for="tfDiagnostico" id="label5" styleClass="label" text="Diagnóstico"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.tfDiagnostico}"
                                                columns="40" id="tfDiagnostico" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.label4}"
                                                for="tfPruebaConfirmatoria" id="label4" styleClass="label" text="Prueba Confirmatoria"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.tfPruebaConfirmatoria}"
                                                columns="40" id="tfPruebaConfirmatoria" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.label3}"
                                                for="tfFechaReintegro" id="label3" styleClass="label" text="Fecha Reintegro"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.tfFechaReintegro}"
                                                id="tfFechaReintegro" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:staticText
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.staticText1}"
                                                escape="false" id="staticText1" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap">
                                            <ui:button
                                                action="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.btnGuardar_action}"
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.btnGuardar}"
                                                id="btnGuardar" styleClass="button" text="Aceptar"/>
                                            <ui:staticText
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.stSeparador}"
                                                escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button
                                                action="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.btnCancelar_action}"
                                                binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.btnCancelar}"
                                                id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.hidIdPagina}"
                            id="hidIdPagina" text="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.hidIdSubSesion}"
                            id="hidIdSubSesion" text="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpSHPS$ABMInhabilitacionTemporariaLS$AgregarInhabilitacionTemporariaLS.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
