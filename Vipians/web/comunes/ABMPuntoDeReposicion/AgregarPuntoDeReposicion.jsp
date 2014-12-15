<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.page1}" id="page1">
            <ui:html binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.html1}" id="html1">
                <ui:head binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.head1}" id="head1" title="Agregar Punto de Reposición">
                    <ui:link binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.body1}" focus="form1:tfCantidadAComprar" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td>
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.lblCantidadAComprar}" for="tfCantidadAComprar" id="lblCantidadAComprar" styleClass="label" text="Cantidad a comprar"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.tfCantidadAComprar}" onKeyPress="return ValidarFloat(event,this)" columns="10" id="tfCantidadAComprar" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <br/>
                                            <ui:label binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.lblCantidadLimite}" id="lblCantidadLimite" styleClass="label" text="Cantidad límite"/>
                                        </td>
                                        <td>
                                            <br/>
                                            <ui:textField binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.tfCantidadLimite}" onKeyPress="return ValidarFloat(event,this)" id="tfCantidadLimite" styleClass="textField" columns="10"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <br/>
                                            <ui:label binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.lblDescripcion}" id="lblDescripcion" styleClass="label" text="Descripción"/>
                                        </td>
                                        <td>
                                            <br/>
                                            <ui:textArea binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.taDescripcion}" columns="40" id="taDescripcion" styleClass="textField" rows="5"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                    </tr>


                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.btnGuardar_action}"
                                                binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.btnGuardar}" id="btnGuardar" styleClass="button" text="Aceptar"/>
                                            <ui:staticText binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.btnCancelar_action}"
                                                binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                            <br/>
                            <br/>
                            <br/>
                        </div>
                        <ui:hiddenField binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.hidIdPagina}" id="hidIdPagina" text="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.idPagina}"/>
                        <ui:hiddenField binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.hidIdSubSesion}" id="hidIdSubSesion" text="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.idSubSesion}"/>
                        <ui:script binding="#{comunes$ABMPuntoDeReposicion$AgregarPuntoDeReposicion.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
