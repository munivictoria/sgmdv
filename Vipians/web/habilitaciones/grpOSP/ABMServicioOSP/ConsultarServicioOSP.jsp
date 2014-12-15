<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.head1}" id="head1" title="Consultar Código de Servicio de OySP">
                    <ui:link binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.body1}" focus="form1:tfCodigo" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                    <ui:form binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.label4}" for="tfCodigo" id="label4"
                                                styleClass="label" text="Código"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.tfCodigo}" columns="10"
                                                disabled="true" id="tfCodigo" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.label8}" for="tfCoeficienteCodigo" id="label8"
                                                styleClass="label" text="Coeficiente Código de Servicio"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.tfCoeficienteCodigo}" columns="10"
                                            id="tfCoeficienteCodigo" disabled="true" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.lblCoeficienteValorTerreno}"
                                            for="tfCoeficienteValorTerreno" id="lblCoeficienteValorTerreno" styleClass="label" text="Coeficiente Valor de Terreno"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.tfCoeficienteValorTerreno}" columns="10"
                                            id="tfCoeficienteValorTerreno" styleClass="textFieldDisabled" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.lblCoeficienteValorEdificado}"
                                            for="tfCoeficienteValorEdificado" id="lblCoeficienteValorEdificado" styleClass="label" text="Coeficiente Valor Edificado"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.tfCoeficienteValorEdificado}" columns="10"
                                            id="tfCoeficienteValorEdificado" styleClass="textFieldDisabled" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.label5}" for="taNombre" id="label5"
                                                styleClass="label" text="Descripción"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.taNombre}" columns="40"
                                                disabled="true" id="taNombre" rows="5" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.label2}" for="tfValor" id="label2"
                                                styleClass="label" text="Valor Mínimo"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.tfValor}" columns="10"
                                                disabled="true" id="tfValor" styleClass="textFieldDisabled"/>
                                            <ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.staticText1}" escape="false"
                                                id="staticText1" styleClass="label" text="&amp;nbsp;[$]"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label for="ddPeriodicidad" id="lblPeriodicidad"
                                            styleClass="label" text="Periodicidad"/>
                                        </td>
                                        <td colspan="2">
                                            <ui:dropDown binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.ddPeriodicidad}" id="ddPeriodicidad"
                                            disabled="true" items="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.ddPeriodicidadDefaultOptions.options}" styleClass="TextField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <hr/>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.label3}" for="cbMedido" id="label3"
                                                styleClass="label" text="Medido"/>
                                        </td>
                                        <td>
                                            <ui:checkbox binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.cbMedido}" disabled="true" id="cbMedido"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.label7}" for="ddUnidadMedida"
                                                id="label7" styleClass="label" text="Unidad de Medida"/>
                                        </td>
                                        <td>
                                            <ui:dropDown binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.ddUnidadMedida}" disabled="true"
                                                id="ddUnidadMedida"
                                                items="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.ddUnidadMedidaDefaultOptions.options}" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.label1}" for="tfBaseConsumo"
                                                id="label1" styleClass="label" text="Base de Consumo"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.tfBaseConsumo}" columns="10"
                                                disabled="true" id="tfBaseConsumo" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.label6}" for="tfValorPorExcedente"
                                                id="label6" styleClass="label" text="Valor del Excedente"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.tfValorPorExcedente}"
                                                columns="10" disabled="true" id="tfValorPorExcedente" styleClass="textFieldDisabled"/>
                                            <ui:staticText binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.staticText2}" escape="false"
                                                id="staticText2" styleClass="label" text="&amp;nbsp;[$]"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.lblVolcadoEfluentesIndustriales}" for="cbVolcadoEfluentesIndustriales" id="lblVolcadoEfluentesIndustriales"
                                                styleClass="label" text="Volcado Efluentes Industriales"/>
                                        </td>
                                        <td>
                                            <ui:checkbox binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.cbVolcadoEfluentesIndustriales}" disabled="true"  id="cbVolcadoEfluentesIndustriales" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap">
                                            <ui:button action="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.btnVolver_action}"
                                                binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.btnVolver}" id="btnVolver"
                                                styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.hidIdSubSesion}" id="hidIdSubSesion" text="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpOSP$ABMServicioOSP$ConsultarServicioOSP.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
