<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.head1}" id="head1" title="Consultar Consumo Básico">
                    <ui:link binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                    <ui:form binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.label1}"
                                                for="tfSuperficieMejorasMinimo" id="label1" styleClass="label" text="Mínimo de Superficie de Mejoras"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.tfSuperficieMejorasMinimo}"
                                                columns="10" disabled="true" id="tfSuperficieMejorasMinimo" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.label2}"
                                                for="tfSuperficieMejorasMaximo" id="label2" styleClass="label" text="Máximo de Superficie de Mejoras"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.tfSuperficieMejorasMaximo}"
                                                columns="10" disabled="true" id="tfSuperficieMejorasMaximo" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <!-- ariel - puse el width para que quede mas a la izquierda -->
                                        <td align="right" nowrap="nowrap" width="300">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.label4}" for="tfConsumoInicial"
                                                id="label4" styleClass="label" text="Consumo Inicial"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.tfConsumoInicial}"
                                                columns="10" disabled="true" id="tfConsumoInicial" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.label5}"
                                                for="tfConsumoPorExcedente" id="label5" styleClass="label" text="Consumo por Excedente"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.tfConsumoPorExcedente}"
                                                columns="10" disabled="true" id="tfConsumoPorExcedente" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.btnVolver_action}"
                                                binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.btnVolver}" id="btnVolver"
                                                styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpOSP$ABMConsumoBasico$ConsultarConsumoBasico.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
