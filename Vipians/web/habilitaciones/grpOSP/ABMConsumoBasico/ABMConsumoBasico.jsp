<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.head1}" id="head1">
                    <ui:link binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.body1}" focus="form1:tfSuperficieMejorasMinimo" id="body1"
                    onLoad="parent.footer.location.reload();Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="amarillo">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.label1}"
                                                for="tfSuperficieMejorasMinimo" id="label1" styleClass="label" text="Mínimo de Superficie de Mejoras"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.tfSuperficieMejorasMinimo}"
                                                columns="10" id="tfSuperficieMejorasMinimo" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.label2}"
                                                for="tfSuperficieMejorasMaximo" id="label2" styleClass="label" text="Máximo de Superficie de Mejoras"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.tfSuperficieMejorasMaximo}"
                                                columns="10" id="tfSuperficieMejorasMaximo" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <!-- ariel - puse el width para que quede mas a la izquierda -->
                                        <td align="right" nowrap="nowrap" width="300">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.label4}" for="tfConsumoInicial"
                                                id="label4" styleClass="label" text="Consumo Inicial"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.tfConsumoInicial}"
                                                columns="10" id="tfConsumoInicial" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.label5}"
                                                for="tfConsumoPorExcedente" id="label5" styleClass="label" text="Consumo por Excedente"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.tfConsumoPorExcedente}"
                                                columns="10" id="tfConsumoPorExcedente" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.btnGuardar_action}"
                                                binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.btnGuardar}" id="btnGuardar"
                                                styleClass="button"/>
                                            <ui:staticText binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.stSeparador}" escape="false"
                                                id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.btnCancelar_action}"
                                                binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.btnCancelar}" id="btnCancelar"
                                                styleClass="button" />
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ABMConsumoBasico.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
