<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.head1}" id="head1" title="Agregar Solicitud de Beneficio">
                    <ui:link binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.body1}" focus="form1:btnSeleccionarBeneficio" id="body1" onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.label2}" for="tfBeneficio" id="label2" styleClass="label" text="Beneficio"/>
                                        </td>
                                        <td colspan="2" nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.tfBeneficio}" columns="40" disabled="true" id="tfBeneficio" styleClass="textField"/>
                                            <ui:button action="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.btnSeleccionarBeneficio_action}"
                                                       binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.btnSeleccionarBeneficio}" escape="false"
                                                       id="btnSeleccionarPersonaFisica" mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Beneficio"/>                                            
                                            <ui:button action="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.btnLimpiarBeneficio_action}"
                                                       binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.btnLimpiarBeneficio}" escape="false"
                                                       id="btnLimpiarBeneficio" mini="true" styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                        </td>                                        
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.label3}" for="tfFechaAlta" id="label3" styleClass="label" text="Fecha Alta"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.tfFechaAlta}" id="tfFechaAlta" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:label id="lblFechaAlta" styleClass="label" text="[dd/mm/aaaa]"/>-->
                                        </td> 
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.label1}"
                                                      for="tfCantidad" id="label1" styleClass="label" text="Cantidad"/>
                                        </td>
                                        <td nowrap="nowrap" colspan ="2">
                                            <ui:textField binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.tfCantidad}"
                                                          id="tfCantidad" onKeyPress="return ValidarFloat(event,this)" styleClass="textField"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.label5}"
                                                      for="tfMonto" id="label5" styleClass="label" text="Monto"/>
                                        </td>
                                        <td nowrap="nowrap" colspan ="2">
                                            <ui:textField binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.tfMonto}"                                                          
                                                          id="tfMonto" onKeyPress="return ValidarFloat(event,this)" styleClass="textField"/>
                                        </td> 

                                    </tr>  
                                    <tr>

                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.label9}"
                                                      for="taDescripcion" id="label9" styleClass="label" text="Descripción"/>
                                        </td>
                                        <td nowrap="nowrap" colspan="2">
                                            <ui:textArea binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.taDescripcion}"
                                                         id="taDescripcion" styleClass="textField"/>
                                        </td>                                     
                                    </tr> 

                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.btnGuardar_action}"
                                                       binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.btnCancelar_action}"
                                                       binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.idSubSesion}"/>
                        <ui:script binding="#{accionsocial$ABMSolicitudBeneficio$AgregarSolicitudBeneficio.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
