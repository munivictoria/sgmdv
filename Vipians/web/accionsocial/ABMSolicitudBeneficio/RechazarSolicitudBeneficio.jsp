<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.head1}" id="head1" title="Aceptar Entrega de Solicitud de Beneficio">
                    <ui:link binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.body1}" focus="form1:tfFechaFin" id="body1" onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.label2}" for="tfBeneficio" id="label2" styleClass="label" text="Beneficio"/>
                                        </td>
                                        <td colspan="2" nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.tfBeneficio}" columns="40" id="tfBeneficio" styleClass="textFieldDisabled" disabled="true"/>
                                        </td>                                        
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.label3}" for="tfFechaAlta" id="label3" styleClass="label" text="Fecha Alta"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.tfFechaAlta}" id="tfFechaAlta" styleClass="textFieldDisabled" columns="10" disabled="true"/>
                                            <!--<ui:label id="lblFechaAlta" styleClass="label" text="[dd/mm/aaaa]"/>-->
                                        </td> 
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.label1}"
                                                      for="tfCantidad" id="label1" styleClass="label" text="Cantidad"/>
                                        </td>
                                        <td nowrap="nowrap" colspan ="2">
                                            <ui:textField binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.tfCantidad}"
                                                          id="tfCantidad" styleClass="textFieldDisabled" disabled="true"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.label5}"
                                                      for="tfMonto" id="label5" styleClass="label" text="Monto"/>
                                        </td>
                                        <td nowrap="nowrap" colspan ="2">
                                            <ui:textField binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.tfMonto}"                                                          
                                                          id="tfMonto" styleClass="textFieldDisabled" disabled="true"/>
                                        </td> 

                                    </tr>  
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.label10}"
                                                      for="ddEstado" id="label10" styleClass="label" text="Estado"/>
                                        </td>
                                        <td nowrap="nowrap" colspan="2">
                                            <ui:dropDown binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.ddEstado}"
                                                         items="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.ddEstadoDefaultOptions.options}"
                                                         id="ddEstado" disabled="true"/>
                                        </td> 
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.label4}" for="tfFechaFin" id="label4" styleClass="label" text="Fecha Finalización"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.tfFechaFin}" id="tfFechaFin" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:label id="lblFechaFin" styleClass="label" text="[dd/mm/aaaa]"/>-->
                                        </td> 


                                    </tr> 
                                    <tr>

                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.label9}"
                                                      for="taDescripcion" id="label9" styleClass="label" text="Descripción"/>
                                        </td>
                                        <td nowrap="nowrap" colspan="2">
                                            <ui:textArea binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.taDescripcion}"
                                                         id="taDescripcion" styleClass="textFieldDisabled" disabled="true"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.label8}"
                                                      for="taComentarioFin" id="label8" styleClass="label" text="Comentario de Finalización"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textArea binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.taComentarioFin}"
                                                         id="taComentarioFin" styleClass="textField"/>
                                        </td>
                                    </tr> 

                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.btnRechazar_action}"
                                                       binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.btnRechazar}" id="btnRechazar" styleClass="button" text="Rechazar Solicitud"/>
                                            <ui:staticText binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.btnCancelar_action}"
                                                       binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.idSubSesion}"/>
                        <ui:script binding="#{accionsocial$ABMSolicitudBeneficio$RechazarSolicitudBeneficio.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
