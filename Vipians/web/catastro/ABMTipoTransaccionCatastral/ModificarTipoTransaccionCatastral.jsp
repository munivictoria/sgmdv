<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.page1}" id="page1">
            <ui:html binding="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.html1}" id="html1">
                <ui:head binding="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.head1}" id="head1">
                    <ui:link binding="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="amarillo">
                                <caption>
                                    <ui:staticText binding="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.label4}" for="tfNombre"
                                                id="label4" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.tfNombre}"
                                                id="tfNombre" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup binding="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="true">
                                            <ui:button action="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.btnGuardar_action}"
                                                binding="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.btnGuardar}" id="btnGuardar"
                                                styleClass="button"/>
                                            <ui:staticText binding="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.stSeparador}"
                                                escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.btnCancelar_action}"
                                                binding="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.btnCancelar}" id="btnCancelar"
                                                styleClass="button"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.hidIdPagina}" id="hidIdPagina" text="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.idPagina}"/>
                        <ui:hiddenField binding="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.hidIdSubSesion}" id="hidIdSubSesion" text="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.idSubSesion}"/>
                        <ui:hiddenField binding="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.hidIdObjeto}" id="hidIdObjeto"/>
                        <ui:script binding="#{catastro$ABMTipoTransaccionCatastral$ABMTipoTransaccionCatastral.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
