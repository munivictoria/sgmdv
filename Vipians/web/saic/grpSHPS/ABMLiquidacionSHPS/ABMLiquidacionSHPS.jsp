<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.page1}" id="page1">
            <ui:html binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.html1}" id="html1">
                <ui:head binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.head1}" id="head1">
                    <ui:link binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.body1}" focus="form1:tfNombre" id="body1"
                    onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 242, 242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.stTitulo}" id="stTitulo"
                                        styleClass="tituloABM" text="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="3">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td nowrap="nowrap">
                                            <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.lblDatosObligacion}" id="lblDatosObligacion"
                                                      styleClass="label3" text="Datos de la Obligación"/>
                                            <hr/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                                <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.lblTituloPeriodo}" id="lblTituloPeriodo"
                                                          styleClass="label2" text="Período"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.tfPeriodo}" columns="25" id="tfPeriodo"
                                                          maxLength="4" styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.lblObligacion}" id="lblObligacion"
                                                      styleClass="label2" text="Obligación"/>                                          
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.taObligacion}" id="taObligacion" columns="70" rows="3"
                                                         styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.lblFormula}" id="lblFormula"
                                                      styleClass="label2" text="Fórmula"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.taFormula}" id="taFormula" columns="70" rows="3"
                                                         styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.lblParametros}" id="lblParametros"
                                                      styleClass="label2" text="Parámetros"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.taParametros}" id="taParametros" columns="70" rows="3"
                                                         styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                     <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.lblParametrosValuadosAlicuota}" id="lblParametrosValuadosAlicuota"
                                                      styleClass="label2" text="Parámetros Valuados Alicuota"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.taParametrosValuadosAlicuota}" id="taParametrosValuadosAlicuota" columns="70" rows="3"
                                                         styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.lblModificadores}" id="lblModificadores"
                                                      styleClass="label2" text="Modificadores"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.taModificadores}" id="taModificadores" columns="70" rows="3"
                                                         styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.lblVencimientos}" id="lblVencimientos" styleClass="label2"
                                                      text="Vencimientos"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.taVencimientos}" id="taVencimientos" styleClass="textField"
                                                         disabled="true" columns="70" rows="3"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.lblExencion}" id="lblExencion" styleClass="label2"
                                                      text="Exención"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.taExencion}" id="taExencion" styleClass="textField"
                                                         disabled="true" columns="70" rows="3"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>                                   
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.lblDatosLiquidacion}" id="lblDatosLiquidacion"
                                                styleClass="label3" text="Datos de la Liquidación"/>
                                            <hr/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.lblCuotaLiquidada}" id="lblCuotaLiquidada" for="tfCuotaLiquidada"
                                                      styleClass="label" text="Cuota Liquidada"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.tfCuotaLiquidada}" disabled="true"
                                                id="tfCuotaLiquidada" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.lblMonto}" id="lblMonto" for="tfMonto" styleClass="label2"
                                                      text="Monto "/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.tfMonto}" id="tfMonto" styleClass="textField"
                                                          disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.lblEstado}" id="lblEstado" styleClass="label2" for="tfEstado"
                                                      text="Estado"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.tfEstado}" id="tfEstado" styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.lblFechaCancelacion}" id="lblFechaCancelacion" for="tfFechaCancelacion"
                                                      styleClass="label" text="Fecha de Cancelación"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.tfFechaCancelacion}" id="tfFechaCancelacion" styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>                                    
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.btnCancelar_action}"
                                                binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.btnCancelar}" id="btnCancelar" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                            <br/>
                            <br/>
                            <br/>
                        </div>
                        <ui:hiddenField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.hidIdPagina}" id="hidIdPagina" text="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.idPagina}"/>
                        <ui:hiddenField binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.hidIdSubSesion}" id="hidIdSubSesion" text="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.idSubSesion}"/>
                        <ui:script binding="#{saic$grpSHPS$ABMLiquidacionSHPS$ABMLiquidacionSHPS.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
