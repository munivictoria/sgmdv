<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{framework$ABMTema$ABMTema.page1}" id="page1">
            <ui:html binding="#{framework$ABMTema$ABMTema.html1}" id="html1">
                <ui:head binding="#{framework$ABMTema$ABMTema.head1}" id="head1">
                    <ui:link binding="#{framework$ABMTema$ABMTema.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{framework$ABMTema$ABMTema.body1}" focus="form1:tfTema" id="body1" onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{framework$ABMTema$ABMTema.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="amarillo">
                                <caption>
                                    <ui:staticText binding="#{framework$ABMTema$ABMTema.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{framework$ABMTema$ABMTema.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{framework$ABMTema$ABMTema.label4}" for="tfTema" id="label4" styleClass="label" text="Tema"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{framework$ABMTema$ABMTema.tfTema}" columns="40" id="tfTema" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{framework$ABMTema$ABMTema.label5}" id="label5" styleClass="label" text="Descripción"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{framework$ABMTema$ABMTema.taDescripcion}" columns="40" id="taDescripcion" rows="5" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{framework$ABMTema$ABMTema.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="true">
                                            <ui:button action="#{framework$ABMTema$ABMTema.btnGuardar_action}"
                                                binding="#{framework$ABMTema$ABMTema.btnGuardar}" id="btnGuardar" styleClass="button"/>
                                            <ui:staticText binding="#{framework$ABMTema$ABMTema.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{framework$ABMTema$ABMTema.btnCancelar_action}"
                                                binding="#{framework$ABMTema$ABMTema.btnCancelar}" id="btnCancelar" styleClass="button"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{framework$ABMTema$ABMTema.hidIdPagina}" id="hidIdPagina" text="#{framework$ABMTema$ABMTema.idPagina}"/>
                        <ui:hiddenField binding="#{framework$ABMTema$ABMTema.hidIdSubSesion}" id="hidIdSubSesion" text="#{framework$ABMTema$ABMTema.idSubSesion}"/>
                        <ui:script binding="#{framework$ABMTema$ABMTema.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
