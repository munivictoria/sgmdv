<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.head1}" id="head1" title="Eliminar Inspector">
                    <ui:link binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 236, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="rojo">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.label4}" for="tfNombre" id="label4"
                                                styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.tfNombre}" columns="40"
                                                disabled="true" id="tfNombre" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.label1}" for="tfPersona" id="label1"
                                                styleClass="label" text="Persona"/>
                                        </td>
                                        <td nowrap="true">
                                            <ui:textField binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.tfPersona}" columns="40"
                                                disabled="true" id="tfPersona" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="true">
                                            <ui:button action="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.btnEliminar_action}"
                                                binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.btnEliminar}" id="btnEliminar"
                                                styleClass="button" text="Eliminar"/>
                                            <ui:staticText binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.stSeparador}" escape="false"
                                                id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.btnCancelar_action}"
                                                binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.btnCancelar}" id="btnCancelar"
                                                styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpSHPS$ABMInspector$EliminarInspector.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
