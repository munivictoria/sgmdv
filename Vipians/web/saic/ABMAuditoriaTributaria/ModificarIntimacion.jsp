<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.page1}" id="page1">
            <ui:html binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.html1}" id="html1">
                <ui:head binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.head1}" id="head1" title="Modificar Intimación">
                    <ui:link binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.body1}" focus="form1:tfFechaEmision" id="body1" onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.label1}" for="tfFechaEmision" id="label1" styleClass="label" text="Fecha de Emisión"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.tfFechaEmision}" id="tfFechaEmision" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:label id="lblFormatoFechaEmision" styleClass="label" text=" [dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.label2}" for="tfFechaRecepcion" id="label2" styleClass="label" text="Fecha de Recepción"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.tfFechaRecepcion}" id="tfFechaRecepcion" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:label id="lblFormatoFechaRecepcion" styleClass="label" text=" [dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.label3}" for="tfCausa" id="label3" styleClass="label" text="Causa"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.tfCausa}" columns="40" id="tfCausa" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.label4}" for="tfPeriodoValidez" id="label4" styleClass="label" text="Período de Validez"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.tfPeriodoValidez}" columns="40" id="tfPeriodoValidez" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">                                            
                                            <ui:label binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.label5}" id="label5" styleClass="label" text="Observación"/>
                                        </td>
                                        <td>                                            
                                            <ui:textArea binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.taObservacionIntimacion}" columns="40" id="taObservacionIntimacion" rows="5" styleClass="textField" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>                            
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.btnGuardar_action}"
                                                       binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.btnCancelar_action}"
                                                       binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.hidIdPagina}" id="hidIdPagina" text="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.idPagina}"/>
                        <ui:hiddenField binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.hidIdSubSesion}" id="hidIdSubSesion" text="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.idSubSesion}"/>
                        <ui:script binding="#{saic$ABMAuditoriaTributaria$ModificarIntimacion.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
