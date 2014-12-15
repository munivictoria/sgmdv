<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.page1}" id="page1">
            <ui:html binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.html1}" id="html1">
                <ui:head binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.head1}" id="head1" title="Consultar Volante Catastral">
                    <ui:link binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.body1}" focus="form1:tfAvaluoTerreno" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                    <ui:form binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.label2}" for="tfNroVolanteCatastral"
                                                id="label2" styleClass="label" text="Nº de Volante Catastral"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.tfNroVolanteCatastral}" columns="10"
                                                disabled="true" id="tfNroVolanteCatastral" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.label1}" for="tfParcela" id="label1"
                                                styleClass="label" text="Parcela"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.tfParcela}" columns="40"
                                                disabled="true" id="tfParcela" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap" style="height: 17px">
                                            <ui:label binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.label4}" for="tfAvaluoTerreno"
                                                id="label4" styleClass="label" text="Avaluo del Terreno"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.tfAvaluoTerreno}" columns="10"
                                                id="tfAvaluoTerreno" styleClass="textFieldDisabled"/>
                                            <ui:staticText binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.staticText2}" escape="false"
                                                id="staticText2" styleClass="label" text="&amp;nbsp;[$]"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.label5}" for="tfAvaluoMejora"
                                                id="label5" styleClass="label" text="Avaluo de Mejora"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.tfAvaluoMejora}" columns="10"
                                                id="tfAvaluoMejora" styleClass="textFieldDisabled"/>
                                            <ui:staticText binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.staticText3}" escape="false"
                                                id="staticText3" styleClass="label" text="&amp;nbsp;[$]"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.label3}" for="tfFecha" id="label3"
                                                styleClass="label" text="Fecha"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.tfFecha}" id="tfFecha"
                                                maxLength="10" styleClass="textFieldDisabled"/>
                                            <!--<ui:staticText binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.staticText1}" escape="false"
                                                id="staticText1" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.label6}" for="cbxRadio" id="label6"
                                                styleClass="label" text="Radio Céntrico"/>
                                        </td>
                                        <td>
                                            <ui:checkbox binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.cbxRadio}" id="cbxRadio"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" style="height: 22px">
                                        <ui:messageGroup binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="true">
                                            <ui:button action="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.btnVolver_action}"
                                                binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.btnVolver}" id="btnVolver" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.hidIdPagina}" id="hidIdPagina" text="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.idPagina}"/>
                        <ui:hiddenField binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.hidIdSubSesion}" id="hidIdSubSesion" text="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.idSubSesion}"/>
                        <ui:script binding="#{catastro$ABMVolanteCatastral$ConsultarVolanteCatastral.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
