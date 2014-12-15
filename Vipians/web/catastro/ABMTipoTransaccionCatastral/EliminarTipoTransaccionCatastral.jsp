<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.page1}" id="page1">
            <ui:html binding="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.html1}" id="html1">
                <ui:head binding="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.head1}" id="head1">
                    <ui:link binding="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 236, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="rojo">
                                <caption>
                                    <ui:staticText binding="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="Eliminar Tipo de Transacción Catastral"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="true">
                                            <ui:label binding="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.label4}" for="tfNombre"
                                                id="label4" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.tfNombre}"
                                                disabled="true" id="tfNombre" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.messageGroup1}"
                                            id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="true">
                                            <ui:button action="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.btnEliminar_action}"
                                                binding="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.btnEliminar}" id="btnEliminar"
                                                styleClass="button" text="Eliminar"/>
                                            <ui:staticText binding="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.stSeparador}"
                                                escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.btnCancelar_action}"
                                                binding="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.btnCancelar}" id="btnCancelar"
                                                styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.hidIdPagina}" id="hidIdPagina" text="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.idPagina}"/>
                        <ui:hiddenField binding="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.hidIdSubSesion}" id="hidIdSubSesion" text="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.idSubSesion}"/>
                        <ui:hiddenField binding="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.hidIdObjeto}" id="hidIdObjeto"/>
                        <ui:script binding="#{catastro$ABMTipoTransaccionCatastral$EliminarTipoTransaccionCatastral.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
