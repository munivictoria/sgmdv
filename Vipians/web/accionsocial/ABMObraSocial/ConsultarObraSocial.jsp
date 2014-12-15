<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMObraSocial$ConsultarObraSocial.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMObraSocial$ConsultarObraSocial.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMObraSocial$ConsultarObraSocial.head1}" id="head1" title="Consultar ObraSocial">
                    <ui:link binding="#{accionsocial$ABMObraSocial$ConsultarObraSocial.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMObraSocial$ConsultarObraSocial.body1}" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242,242,242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                    <ui:form binding="#{accionsocial$ABMObraSocial$ConsultarObraSocial.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{accionsocial$ABMObraSocial$ConsultarObraSocial.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMObraSocial$ConsultarObraSocial.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                   
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMObraSocial$ConsultarObraSocial.label2}" for="tfNombre" id="label2" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td colspan="3" nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMObraSocial$ConsultarObraSocial.tfNombre}" columns="40" disabled="true" id="tfNombre" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                   
                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{accionsocial$ABMObraSocial$ConsultarObraSocial.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{accionsocial$ABMObraSocial$ConsultarObraSocial.btnVolver_action}"
                                                binding="#{accionsocial$ABMObraSocial$ConsultarObraSocial.btnVolver}" id="btnVolver" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{accionsocial$ABMObraSocial$ConsultarObraSocial.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMObraSocial$ConsultarObraSocial.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMObraSocial$ConsultarObraSocial.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMObraSocial$ConsultarObraSocial.idSubSesion}"/>
                        <ui:script binding="#{accionsocial$ABMObraSocial$ConsultarObraSocial.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
