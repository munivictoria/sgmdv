<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.head1}" id="head1" title="Consultar Beneficio">
                    <ui:link binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.body1}" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242,242,242); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                    <ui:form binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMBeneficio$ConsultarBeneficio.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.label2}" for="tfNombre" id="label2" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td colspan="3" nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.tfNombre}" columns="40" disabled="true" id="tfNombre" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.label1}"
                                                      for="ddTipoBeneficio" id="label1" styleClass="label" text="Tipo de Beneficio"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:dropDown binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.ddTipoBeneficio}"
                                                         id="ddTipoBeneficio"  disabled="true"
                                                         items="#{accionsocial$ABMBeneficio$ConsultarBeneficio.ddTipoBeneficioDefaultOptions.options}"  styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.label3}" for="tfMonto" id="label3" styleClass="label" text="Monto"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.tfMonto}" id="tfMonto" styleClass="textFieldDisabled" disabled="true"/>
                                        </td>
                                        <td align="right" nowrap="nowrap"/>
                                        <td/>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.label4}" for="taDescripcion" id="label4" styleClass="label" text="Descripción"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.taDescripcion}" id="taDescripcion" disabled="true" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap"/>
                                        <td/>
                                    </tr>
                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{accionsocial$ABMBeneficio$ConsultarBeneficio.btnVolver_action}"
                                                       binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.btnVolver}" id="btnVolver" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMBeneficio$ConsultarBeneficio.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMBeneficio$ConsultarBeneficio.idSubSesion}"/>
                        <ui:script binding="#{accionsocial$ABMBeneficio$ConsultarBeneficio.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
