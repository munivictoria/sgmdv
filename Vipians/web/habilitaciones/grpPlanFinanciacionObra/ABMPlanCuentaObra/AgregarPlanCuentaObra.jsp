<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.page1}" id="page1">
            <ui:html binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.html1}" id="html1">
                <ui:head binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.head1}" id="head1" title="Agregar Plan de Cuenta para Obras">
                    <ui:link binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid"  onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.stTitulo}"
                                        id="stTitulo" styleClass="tituloABM" text="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.lblNombre}"
                                                for="tfNombre" id="lblNombre" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.tfNombre}"
                                                columns="40" id="tfNombre" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.lblCantidadCuotas}"
                                                for="tfCantidadCuotas" id="lblCantidadCuotas" styleClass="label" text="Cantidad de Cuotas"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.tfCantidadCuotas}"
                                                columns="10" id="tfCantidadCuotas" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.lblTasaAnual}"
                                                for="tfTasaAnual" id="lblTasaAnual" styleClass="label" text="Tasa Anual"/>
                                        </td>
                                        <td>
                                            <ui:textField
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.tfTasaAnual}"
                                                columns="10" id="tfTasaAnual" styleClass="textField"/>
                                            <ui:staticText
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.staticText1}"
                                                escape="false" id="staticText1" styleClass="staticText" text="&amp;nbsp;[%]"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.lblFrecuenciaPago}"
                                                for="ddFrecuenciaPago" id="lblFrecuenciaPago" styleClass="label" text="Frecuencia de Pago"/>
                                        </td>
                                        <td>
                                            <ui:dropDown
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.ddFrecuenciaPago}"
                                                id="ddFrecuenciaPago"
                                                items="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.ddFrecuenciaPagoDefaultOptions.options}" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.lblSistemaCalculoInteres}"
                                                for="ddSistemaCalculoInteres" id="lblSistemaCalculoInteres" styleClass="label" text="Sistema de Cálculo del Interés"/>
                                        </td>
                                        <td>
                                            <ui:dropDown
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.ddSistemaCalculoInteres}"
                                                id="ddSistemaCalculoInteres"
                                                items="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.ddSistemaCalculoInteresDefaultOptions.options}" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.messageGroup1}"
                                                id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap">
                                            <ui:button
                                                action="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.btnGuardar_action}"
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.btnGuardar}"
                                                id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.stSeparador}"
                                                escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button
                                                action="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.btnCancelar_action}"
                                                binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.btnCancelar}"
                                                id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.hidIdPagina}" id="hidIdPagina" text="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.idPagina}"/>
                        <ui:hiddenField binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.hidIdSubSesion}"
                            id="hidIdSubSesion" text="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.idSubSesion}"/>
                        <ui:script binding="#{habilitaciones$grpPlanFinanciacionObra$ABMPlanCuentaObra$AgregarPlanCuentaObra.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
