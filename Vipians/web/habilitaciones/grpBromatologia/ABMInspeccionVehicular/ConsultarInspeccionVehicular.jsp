<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.head1}" id="head1" title="Consultar Inspección Vehicular">
                    <ui:link binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.body1}" focus="form1:tfFecha" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                    <ui:form binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.stTitulo}"
                                        id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.label1}"
                                                for="tfTransporteVehicular" id="label1" styleClass="label" text="Transporte Vehicular"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.tfTransporteVehicular}"
                                                columns="40" disabled="true" id="tfTransporteVehicular" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.label4}"
                                                for="tfInspector" id="label4" styleClass="label" text="Inspector"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.tfInspector}"
                                                columns="40" disabled="true" id="tfInspector" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.label2}"
                                                for="tfFecha" id="label2" styleClass="label" text="Fecha"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.tfFecha}"
                                                disabled="true" id="tfFecha" maxLength="10" styleClass="textFieldDisabled"/>
                                            <!--<ui:staticText
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.staticText2}"
                                                escape="false" id="staticText2" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.label5}"
                                                for="ddEstado" id="label5" styleClass="label" text="Estado"/>
                                        </td>
                                        <td>
                                            <ui:dropDown
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.ddEstado}"
                                                disabled="true" id="ddEstado"
                                                items="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.ddEstadoDefaultOptions.options}" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.label3}"
                                                for="taDescripcion" id="label3" styleClass="label" text="Descripción"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.taDescripcion}"
                                                columns="40" disabled="true" id="taDescripcion" rows="5" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap">
                                            <ui:button
                                                action="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.btnVolver_action}"
                                                binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.btnVolver}"
                                                id="btnVolver" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.hidIdPagina}"
                            id="hidIdPagina" text="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.hidIdSubSesion}"
                            id="hidIdSubSesion" text="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpBromatologia$ABMInspeccionVehicular$ConsultarInspeccionVehicular.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
