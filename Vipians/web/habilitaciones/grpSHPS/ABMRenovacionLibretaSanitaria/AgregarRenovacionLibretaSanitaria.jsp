<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.head1}" id="head1" title="Agregar Renovación de Libreta Sanitaria">
                    <ui:link binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.body1}" focus="form1:tfFechaDesde"
                    id="body1" onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.stTitulo}"
                                        id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.label1}"
                                                for="tfLibretaSanitaria" id="label1" styleClass="label" text="Libreta Sanitaria"/>
                                        </td>
                                        <td nowrap="true">
                                            <ui:textField
                                                binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.tfLibretaSanitaria}"
                                                columns="40" disabled="true" id="tfLibretaSanitaria" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.label2}"
                                                for="tfFechaDesde" id="label2" styleClass="label" text="Fecha Renovación"/>
                                        </td>
                                        <td nowrap="true">
                                            <ui:textField
                                                binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.tfFechaDesde}"
                                                id="tfFechaDesde" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:staticText
                                                binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.staticText2}"
                                                escape="false" id="staticText2" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.label3}"
                                                for="tfFechaVigencia" id="label3" styleClass="label" text="Fecha Vigencia"/>
                                        </td>
                                        <td nowrap="true">
                                            <ui:textField
                                                binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.tfFechaVigencia}"
                                                id="tfFechaVigencia" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:staticText
                                                binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.staticText1}"
                                                escape="false" id="staticText1" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup
                                                binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="true">
                                            <ui:button
                                                action="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.btnGuardar_action}"
                                                binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.btnGuardar}"
                                                id="btnGuardar" styleClass="button" text="Aceptar"/>
                                            <ui:staticText
                                                binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.stSeparador}"
                                                escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button
                                                action="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.btnCancelar_action}"
                                                binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.btnCancelar}"
                                                id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.hidIdPagina}"
                            id="hidIdPagina" text="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.hidIdSubSesion}"
                            id="hidIdSubSesion" text="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpSHPS$ABMRenovacionLibretaSanitaria$AgregarRenovacionLibretaSanitaria.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
