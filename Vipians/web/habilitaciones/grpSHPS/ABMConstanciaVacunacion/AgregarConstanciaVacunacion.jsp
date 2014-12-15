<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.head1}" id="head1" title="Agregar Constancia de Vacunación">
                    <ui:link binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.body1}" focus="form1:tfFecha" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.stTitulo}"
                                        id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.label1}"
                                                for="tfLibretaSanitaria" id="label1" styleClass="label" text="Libreta Sanitaria"/>
                                        </td>
                                        <td nowrap="true">
                                            <ui:textField
                                                binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.tfLibretaSanitaria}"
                                                columns="40" disabled="true" id="tfLibretaSanitaria" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.label2}"
                                                for="tfFecha" id="label2" styleClass="label" text="Fecha Vacunación"/>
                                        </td>
                                        <td nowrap="true">
                                            <ui:textField binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.tfFecha}"
                                                id="tfFecha" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:staticText binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.staticText2}"
                                                escape="false" id="staticText2" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.label5}"
                                                for="tfVacuna" id="label5" styleClass="label" text="Vacuna"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.tfVacuna}"
                                                columns="40" id="tfVacuna" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.label3}"
                                                for="tfFechaValidez" id="label3" styleClass="label" text="Fecha Validez"/>
                                        </td>
                                        <td nowrap="true">
                                            <ui:textField binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.tfFechaValidez}"
                                                id="tfFechaValidez" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:staticText binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.staticText1}"
                                                escape="false" id="staticText1" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup
                                                binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="true">
                                            <ui:button action="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.btnGuardar_action}"
                                                binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.btnGuardar}"
                                                id="btnGuardar" styleClass="button" text="Aceptar"/>
                                            <ui:staticText binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.stSeparador}"
                                                escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.btnCancelar_action}"
                                                binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.btnCancelar}"
                                                id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.hidIdSubSesion}"
                            id="hidIdSubSesion" text="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpSHPS$ABMConstanciaVacunacion$AgregarConstanciaVacunacion.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
