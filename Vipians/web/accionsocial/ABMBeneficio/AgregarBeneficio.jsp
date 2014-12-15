<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.head1}" id="head1" title="Agregar Beneficio">
                    <ui:link binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMBeneficio$AgregarBeneficio.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.label2}" for="tfNombre" id="label2" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td colspan="3" nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.tfNombre}" columns="40" id="tfNombre" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.label1}"
                                                      for="ddTipoBeneficio" id="label1" styleClass="label" text="Tipo de Beneficio"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:dropDown binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.ddTipoBeneficio}"
                                                         id="ddTipoBeneficio"
                                                         items="#{accionsocial$ABMBeneficio$AgregarBeneficio.ddTipoBeneficioDefaultOptions.options}" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.label3}" for="tfMonto" id="label3" styleClass="label" text="Monto"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.tfMonto}" id="tfMonto" onKeyPress="return ValidarFloat(event,this)" styleClass="textField"/>
                                        </td>
                                        <td align="right" nowrap="nowrap"/>
                                        <td/>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.label4}" for="tfDescripcion" id="label4" styleClass="label" text="Descripción"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.taDescripcion}" id="taDescripcion" styleClass="textField"/>
                                        </td>
                                        <td align="right" nowrap="nowrap"/>
                                        <td/>
                                    </tr> 
                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{accionsocial$ABMBeneficio$AgregarBeneficio.btnGuardar_action}"
                                                       binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{accionsocial$ABMBeneficio$AgregarBeneficio.btnCancelar_action}"
                                                       binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMBeneficio$AgregarBeneficio.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMBeneficio$AgregarBeneficio.idSubSesion}"/>
                        <ui:script binding="#{accionsocial$ABMBeneficio$AgregarBeneficio.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
