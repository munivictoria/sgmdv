<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.head1}" id="head1" title="Consultar Inspector">
                    <ui:link binding="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                    <ui:form binding="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.label4}" for="tfNombre" id="label4"
                                                styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.tfNombre}" columns="40"
                                                disabled="true" id="tfNombre" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.label1}" for="tfPersona" id="label1"
                                                styleClass="label" text="Persona"/>
                                        </td>
                                        <td nowrap="true">
                                            <ui:textField binding="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.tfPersona}" columns="40"
                                                disabled="true" id="tfPersona" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup binding="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="true">
                                            <ui:button action="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.btnVolver_action}"
                                                binding="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.btnVolver}" id="btnVolver" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpSHPS$ABMInspector$ConsultarInspector.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
