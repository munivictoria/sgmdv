<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMObraSocial$EliminarObraSocial.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMObraSocial$EliminarObraSocial.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMObraSocial$EliminarObraSocial.head1}" id="head1" title="Eliminar ObraSocial">
                    <ui:link binding="#{accionsocial$ABMObraSocial$EliminarObraSocial.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMObraSocial$EliminarObraSocial.body1}" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 236, 236); -rave-layout: grid"  onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{accionsocial$ABMObraSocial$EliminarObraSocial.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="rojo">
                                <caption>
                                    <ui:staticText binding="#{accionsocial$ABMObraSocial$EliminarObraSocial.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMObraSocial$EliminarObraSocial.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>

                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMObraSocial$EliminarObraSocial.label2}" for="tfNombre" id="label2" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td colspan="3" nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMObraSocial$EliminarObraSocial.tfNombre}" columns="40" disabled="true" id="tfNombre" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>

                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{accionsocial$ABMObraSocial$EliminarObraSocial.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{accionsocial$ABMObraSocial$EliminarObraSocial.btnEliminar_action}"
                                                       binding="#{accionsocial$ABMObraSocial$EliminarObraSocial.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar"/>
                                            <ui:staticText binding="#{accionsocial$ABMObraSocial$EliminarObraSocial.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{accionsocial$ABMObraSocial$EliminarObraSocial.btnCancelar_action}"
                                                       binding="#{accionsocial$ABMObraSocial$EliminarObraSocial.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{accionsocial$ABMObraSocial$EliminarObraSocial.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMObraSocial$EliminarObraSocial.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMObraSocial$EliminarObraSocial.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMObraSocial$EliminarObraSocial.idSubSesion}"/>
                        <ui:script binding="#{accionsocial$ABMObraSocial$EliminarObraSocial.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
