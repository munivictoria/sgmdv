<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.head1}" id="head1" title="Agregar Solicitud de Beneficio">
                    <ui:link binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.body1}" focus="form1:btnSeleccionarBeneficio" id="body1" onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.label2}" for="tfBeneficio" id="label2" styleClass="label" text="Beneficio"/>
                                        </td>
                                        <td colspan="2" nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.tfBeneficio}" columns="40" disabled="true" id="tfBeneficio" styleClass="textField"/>
                                            <ui:button action="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.btnSeleccionarBeneficio_action}"
                                                       binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.btnSeleccionarBeneficio}" escape="false"
                                                       id="btnSeleccionarPersonaFisica" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Beneficio"/>                                            
                                            <ui:button action="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.btnLimpiarBeneficio_action}"
                                                       binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.btnLimpiarBeneficio}" escape="false"
                                                       id="btnLimpiarBeneficio" mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                        </td>                                        
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.label3}" for="tfFechaAlta" id="label3" styleClass="label" text="Fecha Alta"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.tfFechaAlta}" id="tfFechaAlta" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:label id="lblFechaAlta" styleClass="label" text="[dd/mm/aaaa]"/>-->
                                        </td> 
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.label1}"
                                                      for="tfCantidad" id="label1" styleClass="label" text="Cantidad"/>
                                        </td>
                                        <td nowrap="nowrap" colspan ="2">
                                            <ui:textField binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.tfCantidad}"
                                                          id="tfCantidad" onKeyPress="return ValidarFloat(event,this)" styleClass="textField"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.label5}"
                                                      for="tfMonto" id="label5" styleClass="label" text="Monto"/>
                                        </td>
                                        <td nowrap="nowrap" colspan ="2">
                                            <ui:textField binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.tfMonto}"                                                          
                                                          id="tfMonto" onKeyPress="return ValidarFloat(event,this)" styleClass="textField"/>
                                        </td> 

                                    </tr>  
                                 
                                    <tr>

                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.label9}"
                                                      for="taDescripcion" id="label9" styleClass="label" text="Descripción"/>
                                        </td>
                                        <td nowrap="nowrap" colspan="2">
                                            <ui:textArea binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.taDescripcion}"
                                                         id="taDescripcion" styleClass="textField"/>
                                        </td>                                      
                                    </tr> 

                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.btnGuardar_action}"
                                                       binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.btnCancelar_action}"
                                                       binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.idSubSesion}"/>
                        <ui:script binding="#{accionsocial$ABMSolicitudBeneficio$ModificarSolicitudBeneficio.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
