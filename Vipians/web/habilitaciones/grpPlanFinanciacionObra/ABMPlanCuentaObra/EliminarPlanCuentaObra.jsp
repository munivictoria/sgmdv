<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.head1}" id="head1" title="Eliminar Plan de Cuenta para Obras">
                    <ui:link binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 236, 236); -rave-layout: grid"  onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="rojo">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.stTitulo}"
                                        id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap" style="height: 20px">
                                            <ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.lblNombre}"
                                                for="tfNombre" id="lblNombre" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.tfNombre}"
                                                columns="40" disabled="true" id="tfNombre" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.lblCantidadCuotas}"
                                                for="tfCantidadCuotas" id="lblCantidadCuotas" styleClass="label" text="Cantidad de Cuotas"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.tfCantidadCuotas}"
                                                columns="10" disabled="true" id="tfCantidadCuotas" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.lblTasaAnual}"
                                                for="tfTasaAnual" id="lblTasaAnual" styleClass="label" text="Tasa Anual"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.tfTasaAnual}"
                                                columns="10" disabled="true" id="tfTasaAnual" styleClass="textFieldDisabled"/>
                                            <ui:staticText
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.staticText1}"
                                                escape="false" id="staticText1" styleClass="staticText" text="&amp;nbsp;[%]"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.lblFrecuenciaPago}"
                                                for="ddFrecuenciaPago" id="lblFrecuenciaPago" styleClass="label" text="Frecuencia de Pago"/>
                                        </td>
                                        <td>
                                            <ui:dropDown
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.ddFrecuenciaPago}"
                                                disabled="true" id="ddFrecuenciaPago"
                                                items="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.ddFrecuenciaPagoDefaultOptions.options}" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.lblSistemaCalculoInteres}"
                                                for="ddSistemaCalculoInteres" id="lblSistemaCalculoInteres" styleClass="label" text="Sistema de Cálculo del Interés"/>
                                        </td>
                                        <td>
                                            <ui:dropDown
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.ddSistemaCalculoInteres}"
                                                disabled="true" id="ddSistemaCalculoInteres"
                                                items="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.ddSistemaCalculoInteresDefaultOptions.options}" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap">
                                            <ui:button
                                                action="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.btnEliminar_action}"
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.btnEliminar}"
                                                id="btnEliminar" styleClass="button" text="Eliminar"/>
                                            <ui:staticText
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.stSeparador}"
                                                escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button
                                                action="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.btnCancelar_action}"
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.btnCancelar}"
                                                id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.hidIdPagina}"
                            id="hidIdPagina" text="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.hidIdSubSesion}"
                            id="hidIdSubSesion" text="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$EliminarPlanCuentaObra.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
