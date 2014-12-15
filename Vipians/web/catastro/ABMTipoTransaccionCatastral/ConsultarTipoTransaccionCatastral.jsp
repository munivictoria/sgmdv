<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.page1}" id="page1">
            <ui:html binding="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.html1}" id="html1">
                <ui:head binding="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.head1}" id="head1" title="Consultar Tipo de Transacción Catastral">
                    <ui:link binding="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.body1}" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                    <ui:form binding="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.label4}" for="tfNombre"
                                                id="label4" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.tfNombre}"
                                                disabled="true" id="tfNombre" styleClass="textFieldDisabled" columns="40"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.messageGroup1}"
                                            id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="true" style="height: 32px">
                                            <ui:button action="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.btnVolver_action}"
                                                binding="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.btnVolver}" id="btnVolver"
                                                styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.hidIdPagina}" id="hidIdPagina" text="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.idPagina}"/>
                        <ui:hiddenField binding="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.hidIdSubSesion}" id="hidIdSubSesion" text="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.idSubSesion}"/>
                        <ui:hiddenField binding="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.hidIdObjeto}" id="hidIdObjeto"/>
                        <ui:script binding="#{catastro$ABMTipoTransaccionCatastral$ConsultarTipoTransaccionCatastral.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
