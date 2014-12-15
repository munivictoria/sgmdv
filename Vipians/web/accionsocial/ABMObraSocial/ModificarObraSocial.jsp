<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMObraSocial$ModificarObraSocial.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMObraSocial$ModificarObraSocial.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMObraSocial$ModificarObraSocial.head1}" id="head1" title="Modificar ObraSocial">
                    <ui:link binding="#{accionsocial$ABMObraSocial$ModificarObraSocial.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMObraSocial$ModificarObraSocial.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{accionsocial$ABMObraSocial$ModificarObraSocial.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="amarillo">
                                <caption>
                                    <ui:staticText binding="#{accionsocial$ABMObraSocial$ModificarObraSocial.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMObraSocial$ModificarObraSocial.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>

                                    <tr>
                                        <td align="right" nowrap="nowrap" style="height: 15px">
                                            <ui:label binding="#{accionsocial$ABMObraSocial$ModificarObraSocial.label2}" for="tfNombre" id="label2" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td colspan="3" nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMObraSocial$ModificarObraSocial.tfNombre}" columns="40" id="tfNombre" styleClass="textField"/>
                                        </td>
                                    </tr>

                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{accionsocial$ABMObraSocial$ModificarObraSocial.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{accionsocial$ABMObraSocial$ModificarObraSocial.btnGuardar_action}"
                                                       binding="#{accionsocial$ABMObraSocial$ModificarObraSocial.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{accionsocial$ABMObraSocial$ModificarObraSocial.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{accionsocial$ABMObraSocial$ModificarObraSocial.btnCancelar_action}"
                                                       binding="#{accionsocial$ABMObraSocial$ModificarObraSocial.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{accionsocial$ABMObraSocial$ModificarObraSocial.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMObraSocial$ModificarObraSocial.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMObraSocial$ModificarObraSocial.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMObraSocial$ModificarObraSocial.idSubSesion}"/>
                        <ui:script binding="#{accionsocial$ABMObraSocial$ModificarObraSocial.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
