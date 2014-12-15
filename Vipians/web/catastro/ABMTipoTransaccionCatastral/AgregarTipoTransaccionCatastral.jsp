<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.page1}" id="page1">
            <ui:html binding="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.html1}" id="html1">
                <ui:head binding="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.head1}" id="head1">
                    <ui:link binding="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="Agregar Tipo de Transacción Catastral"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.label4}" for="tfNombre"
                                                id="label4" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.tfNombre}"
                                                id="tfNombre" styleClass="textField"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.messageGroup1}"
                                            id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="true">
                                            <ui:button action="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.btnGuardar_action}"
                                                binding="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.btnGuardar}" id="btnGuardar"
                                                styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.stSeparador}"
                                                escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.btnCancelar_action}"
                                                binding="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.btnCancelar}" id="btnCancelar"
                                                styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.hidIdPagina}" id="hidIdPagina" text="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.idPagina}"/>
                        <ui:hiddenField binding="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.hidIdSubSesion}" id="hidIdSubSesion" text="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.idSubSesion}"/>
                        <ui:script binding="#{catastro$ABMTipoTransaccionCatastral$AgregarTipoTransaccionCatastral.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
