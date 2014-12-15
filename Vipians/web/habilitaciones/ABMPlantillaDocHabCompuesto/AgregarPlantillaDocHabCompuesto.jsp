<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.page1}" id="page1">
            <ui:html binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.html1}" id="html1">
                <ui:head binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.head1}" id="head1" title="Agregar Documento Compuesto">
                    <ui:link binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.stTitulo}"
                                        id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.label1}"
                                                for="tfPadre" id="label1" styleClass="label" text="Agregar a"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.tfPadre}"
                                                columns="40" disabled="true" id="tfPadre" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.label4}"
                                                for="tfNombre" id="label4" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.tfNombre}"
                                                columns="40" id="tfNombre" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.label5}"
                                                for="taDescripcion" id="label5" styleClass="label" text="Descripción"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.taDescripcion}"
                                                columns="40" id="taDescripcion" rows="5" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup
                                                binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.btnGuardar_action}"
                                                binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.btnGuardar}"
                                                id="btnGuardar" styleClass="button" text="Aceptar"/>
                                            <ui:staticText binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.stSeparador}"
                                                escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.btnCancelar_action}"
                                                binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.btnCancelar}"
                                                id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.hidIdSubSesion}"
                            id="hidIdSubSesion" text="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$ABMPlantillaDocHabCompuesto$AgregarPlantillaDocHabCompuesto.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
