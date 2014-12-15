<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMObraSocial$AgregarObraSocial.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMObraSocial$AgregarObraSocial.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMObraSocial$AgregarObraSocial.head1}" id="head1" title="Agregar ObraSocial">
                    <ui:link binding="#{accionsocial$ABMObraSocial$AgregarObraSocial.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMObraSocial$AgregarObraSocial.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{accionsocial$ABMObraSocial$AgregarObraSocial.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{accionsocial$ABMObraSocial$AgregarObraSocial.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMObraSocial$AgregarObraSocial.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>

                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMObraSocial$AgregarObraSocial.label2}" for="tfNombre" id="label2" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td colspan="3" nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMObraSocial$AgregarObraSocial.tfNombre}" columns="40" id="tfNombre" styleClass="textField"/>
                                        </td>
                                    </tr>

                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{accionsocial$ABMObraSocial$AgregarObraSocial.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{accionsocial$ABMObraSocial$AgregarObraSocial.btnGuardar_action}"
                                                       binding="#{accionsocial$ABMObraSocial$AgregarObraSocial.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{accionsocial$ABMObraSocial$AgregarObraSocial.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{accionsocial$ABMObraSocial$AgregarObraSocial.btnCancelar_action}"
                                                       binding="#{accionsocial$ABMObraSocial$AgregarObraSocial.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{accionsocial$ABMObraSocial$AgregarObraSocial.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMObraSocial$AgregarObraSocial.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMObraSocial$AgregarObraSocial.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMObraSocial$AgregarObraSocial.idSubSesion}"/>
                        <ui:script binding="#{accionsocial$ABMObraSocial$AgregarObraSocial.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
