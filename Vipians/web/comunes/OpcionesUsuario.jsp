<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{comunes$OpcionesUsuario.page1}" id="page1">
            <ui:html binding="#{comunes$OpcionesUsuario.html1}" id="html1">
                <ui:head binding="#{comunes$OpcionesUsuario.head1}" id="head1" title="Opciones de Usuario">
                    <ui:link binding="#{comunes$OpcionesUsuario.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{comunes$OpcionesUsuario.body1}" focus="form1:pfAnterior" id="body1" onLoad="parent.footer.location.reload();" style="background-color: rgb(236, 236, 242); -rave-layout: grid">
                    <ui:form binding="#{comunes$OpcionesUsuario.form1}" id="form1">
                        <div class="divAdmin">
                            <div class="formularioABM">
                                <table border="0" class="azul">
                                    <caption>
                                        <ui:staticText binding="#{comunes$OpcionesUsuario.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{comunes$OpcionesUsuario.head1.title}"/>
                                    </caption>
                                    <tbody>
                                        <tr>
                                            <td colspan="2" nowrap="nowrap" style="border-bottom:1px solid #999;">
                                                <ui:label binding="#{comunes$OpcionesUsuario.label1}" id="label1" style="font-size: 11px" text="Cambio de Contraseña"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" nowrap="nowrap"/>
                                        </tr>
                                        <tr>
                                            <td align="right" nowrap="nowrap">
                                                <ui:label binding="#{comunes$OpcionesUsuario.label2}" for="pfAnterior" id="label2" styleClass="label" text="Contraseña Anterior"/>
                                            </td>
                                            <td>
                                                <ui:passwordField binding="#{comunes$OpcionesUsuario.pfAnterior}" columns="30" id="pfAnterior"
                                                    onBlur="this.className=&quot;textField2&quot;" onFocus="this.className=&quot;textFieldFocus2&quot;" styleClass="textField2"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right" nowrap="nowrap">
                                                <ui:label binding="#{comunes$OpcionesUsuario.label3}" for="pfNuevo1" id="label3" styleClass="label" text="Nueva Contraseña"/>
                                            </td>
                                            <td>
                                                <ui:passwordField binding="#{comunes$OpcionesUsuario.pfNuevo1}" columns="30" id="pfNuevo1"
                                                    onBlur="this.className=&quot;textField2&quot;" onFocus="this.className=&quot;textFieldFocus2&quot;" styleClass="textField2"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right" nowrap="nowrap">
                                                <ui:label binding="#{comunes$OpcionesUsuario.label4}" for="pfNuevo2" id="label4" styleClass="label" text="Repetir Nueva Contraseña"/>
                                            </td>
                                            <td>
                                                <ui:passwordField binding="#{comunes$OpcionesUsuario.pfNuevo2}" columns="30" id="pfNuevo2"
                                                    onBlur="this.className=&quot;textField2&quot;" onFocus="this.className=&quot;textFieldFocus2&quot;" styleClass="textField2"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <ui:messageGroup binding="#{comunes$OpcionesUsuario.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                            </td>
                                        </tr>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td align="right" colspan="2">
                                                <ui:button action="#{comunes$OpcionesUsuario.btnGuardar_action}" binding="#{comunes$OpcionesUsuario.btnGuardar}"
                                                    id="btnGuardar" styleClass="button" text="Guardar Cambios"/>
                                                <ui:staticText binding="#{comunes$OpcionesUsuario.staticText1}" escape="false" id="staticText1" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                                <ui:button action="#{comunes$OpcionesUsuario.btnCancelar_action}"
                                                    binding="#{comunes$OpcionesUsuario.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                            </td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                            <div></div>
                        </div>
                        <ui:hiddenField binding="#{comunes$OpcionesUsuario.hidIdPagina}" id="hidIdPagina" text="#{comunes$OpcionesUsuario.idPagina}"/>
                        <ui:hiddenField binding="#{comunes$OpcionesUsuario.hidIdSubSesion}" id="hidIdSubSesion" text="#{comunes$OpcionesUsuario.idSubSesion}"/>
                        <ui:script binding="#{comunes$OpcionesUsuario.scriptFinal1}" id="scriptFinal1" url="/resources/javascript/pageGreyXlib.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
