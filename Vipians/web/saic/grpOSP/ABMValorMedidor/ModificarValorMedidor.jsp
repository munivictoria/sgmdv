<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.page1}" id="page1">
            <ui:html binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.html1}" id="html1">
                <ui:head binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.head1}" id="head1" title="Modificar Medición del Medidor">
                    <ui:link binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.link1}" id="link1" url="/resources/stylesheet.css"/>
                    <script><![CDATA[
                        var _decimales = 2;

                        function getById(id) {
                            elem = document.getElementById(id);
                            return elem;
                        }

                        function formatNumber(number) {
                            number = Math.round( number * Math.pow(10,_decimales) ) / Math.pow(10,_decimales);
                            cantDecimales = 0;
                            if (number.toString().indexOf(".") >= 0) cantDecimales = number.toString().substring( number.toString().indexOf(".")+1 ).length;
                            if (number == Math.floor(number)) number = number + ".";
                            for (i = 0; i < (_decimales-cantDecimales); i++) number += "0";
                            return number;
                        }

                        function resta(valor1, valor2){
                            return (valor1 - valor2);
                        }

                        function calcularTotal(){
                            tfLecturaAnterior = getById("form1:tfLecturaAnterior");
                            tfLectura         = getById("form1:tfLectura");
                            tfMontoImponible  = getById("form1:tfMontoImponible");

                            if(tfLectura.value > 0){
                                tfMontoImponible.value = formatNumber(resta(tfLectura.value, tfLecturaAnterior.value));
                            }else tfMontoImponible.value = "";
                        }

                    ]]></script>
                    </ui:head>
                    <ui:body binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.body1}" focus="form1:tfLectura" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                        <ui:form binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="amarillo">
                                <caption>
                                    <ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.label7}" for="tfServicioOSP" id="label7"
                                                      styleClass="label" text="Servicio"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.tfServicioOSP}" columns="40"
                                                          disabled="true" id="tfServicioOSP" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.label3}" for="tfPeriodo" id="label3"
                                                      styleClass="label" text="Período"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.tfPeriodo}" columns="40" disabled="true"
                                                          id="tfPeriodo" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.label1}" for="tfPersona" id="label1"
                                                      styleClass="label" text="Persona"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.tfPersona}" columns="40" disabled="true"
                                                          id="tfPersona" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.label6}" for="tfDireccion" id="label6"
                                                      styleClass="label" text="Dirección"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.tfDireccion}" columns="40"
                                                          disabled="true" id="tfDireccion" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.label5}" for="tfCodigoMedidor" id="label5"
                                                      styleClass="label" text="Código Medidor"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.tfCodigoMedidor}" disabled="true"
                                                          id="tfCodigoMedidor" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.label2}" for="tfFecha" id="label2"
                                                      styleClass="label" text="Fecha"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.tfFecha}" id="tfFecha" maxLength="10" styleClass="textField" disabled="true"/>
                                            <ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.staticText5}" escape="false"
                                                           id="staticText5" styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.lblLecturaAnterior}" id="lblLecturaAnterior"
                                                      for="tfLecturaAnterior" styleClass="label" text="Lectura Anterior"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.tfLecturaAnterior}" id="tfLecturaAnterior" styleClass="textField"
                                                          disabled="true" onBlur="calcularTotal()"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.lblLectura}" id="lblLectura" styleClass="label" text="Lectura Actual"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.tfLectura}" id="tfLectura" styleClass="textField" columns="10" onBlur="calcularTotal()"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.label4}" for="tfMontoImponible" id="label4"
                                                      styleClass="label" text="Consumo"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.tfMontoImponible}" columns="10"
                                                          id="tfMontoImponible" styleClass="textField" onBlur="calcularTotal()"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <ui:messageGroup binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="2" nowrap="nowrap" style="height: 24px">
                                            <ui:button action="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.btnGuardar_action}"
                                                       binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.stSeparador}" escape="false"
                                                           id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.btnCancelar_action}"
                                                       binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.hidIdPagina}" id="hidIdPagina" text="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.idPagina}"/>
                        <ui:hiddenField binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.hidIdSubSesion}" id="hidIdSubSesion" text="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.idSubSesion}"/>
                        <ui:script binding="#{saic$grpOSP$ABMValorMedidor$ModificarValorMedidor.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                        <script><![CDATA[calcularTotal();]]></script>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
