<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.head1}" id="head1" title="Consultar Inspección Local Comercial">
                    <ui:link binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.body1}" focus="form1:tfFecha" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                    <ui:form binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.stTitulo}"
                                        id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.label1}"
                                                for="tfLocalComercial" id="label1" styleClass="label" text="Local Comercial"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.tfLocalComercial}"
                                                columns="40" disabled="true" id="tfLocalComercial" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.label4}"
                                                for="tfInspector" id="label4" styleClass="label" text="Inspector"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.tfInspector}"
                                                columns="40" disabled="true" id="tfInspector" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.label2}"
                                                for="tfFecha" id="label2" styleClass="label" text="Fecha"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.tfFecha}"
                                                disabled="true" id="tfFecha" styleClass="textFieldDisabled"/>
                                            <!--<ui:staticText
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.staticText2}"
                                                escape="false" id="staticText2" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.label5}"
                                                for="ddEstado" id="label5" styleClass="label" text="Estado"/>
                                        </td>
                                        <td>
                                            <ui:dropDown
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.ddEstado}"
                                                disabled="true" id="ddEstado"
                                                items="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.ddEstadoDefaultOptions.options}" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.label3}"
                                                for="taDescripcion" id="label3" styleClass="label" text="Descripción"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.taDescripcion}"
                                                columns="40" disabled="true" id="taDescripcion" rows="5" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap">
                                            <ui:button
                                                action="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.btnVolver_action}"
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.btnVolver}"
                                                id="btnVolver" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.hidIdPagina}"
                            id="hidIdPagina" text="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.hidIdSubSesion}"
                            id="hidIdSubSesion" text="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpBromatologia$ABMInspeccionComercial$ConsultarInspeccionComercial.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
