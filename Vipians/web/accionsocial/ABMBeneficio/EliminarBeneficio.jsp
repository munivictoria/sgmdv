<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.page1}" id="page1">
            <ui:html binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.html1}" id="html1">
                <ui:head binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.head1}" id="head1" title="Eliminar Beneficio">
                    <ui:link binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.body1}" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(242, 236, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="rojo">
                                <caption>
                                    <ui:staticText binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{accionsocial$ABMBeneficio$EliminarBeneficio.head1.title}"/>
                                </caption>
                                <tr>
                                    <td>
                                        <br/>
                                    </td>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.label2}" for="tfNombre" id="label2" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td colspan="3" nowrap="nowrap">
                                            <ui:textField binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.tfNombre}" columns="40" disabled="true" id="tfNombre" styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.label1}"
                                                      for="ddTipoBeneficio" id="label1" styleClass="label" text="Tipo de Beneficio"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:dropDown binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.ddTipoBeneficio}"
                                                         id="ddTipoBeneficio"  disabled="true"
                                                         items="#{accionsocial$ABMBeneficio$EliminarBeneficio.ddTipoBeneficioDefaultOptions.options}"  styleClass="textFieldDisabled"/>
                                        </td>
                                    </tr> 
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.label3}" for="tfMonto" id="label3" styleClass="label" text="Monto"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.tfMonto}" id="tfMonto" disabled="true" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap"/>
                                        <td/>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.label4}" for="taDescripcion" id="label4" styleClass="label" text="Descripción"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.taDescripcion}" id="taDescripcion" disabled="true" styleClass="textFieldDisabled"/>
                                        </td>
                                        <td align="right" nowrap="nowrap"/>
                                        <td/>
                                    </tr>
                                </tbody>
                                <tr>
                                    <td colspan="4">
                                        <ui:messageGroup binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{accionsocial$ABMBeneficio$EliminarBeneficio.btnEliminar_action}"
                                                       binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar"/>
                                            <ui:staticText binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{accionsocial$ABMBeneficio$EliminarBeneficio.btnCancelar_action}"
                                                       binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.hidIdPagina}" id="hidIdPagina" text="#{accionsocial$ABMBeneficio$EliminarBeneficio.idPagina}"/>
                        <ui:hiddenField binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.hidIdSubSesion}" id="hidIdSubSesion" text="#{accionsocial$ABMBeneficio$EliminarBeneficio.idSubSesion}"/>
                        <ui:script binding="#{accionsocial$ABMBeneficio$EliminarBeneficio.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
