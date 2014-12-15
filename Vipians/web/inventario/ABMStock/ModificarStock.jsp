<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{inventario$ABMStock$ModificarStock.page1}" id="page1">
            <ui:html binding="#{inventario$ABMStock$ModificarStock.html1}" id="html1">
                <ui:head binding="#{inventario$ABMStock$ModificarStock.head1}" id="head1" title="Modificar Stock">
                     <ui:link binding="#{inventario$ABMStock$ModificarStock.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{inventario$ABMStock$ModificarStock.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{inventario$ABMStock$ModificarStock.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="amarillo">
                                <caption>
                                    <ui:staticText binding="#{inventario$ABMStock$ModificarStock.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{inventario$ABMStock$ModificarStock.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMStock$ModificarStock.lblBien}" for="tfBien" id="lblBien" styleClass="label" text="Bien"/>
                                        </td>
                                        <td nowrap="nowrap" >
                                            <ui:textField binding="#{inventario$ABMStock$ModificarStock.tfBien}" columns="50" disabled="true" id="tfBien" styleClass="textField"/>
                                            <ui:button action="#{inventario$ABMStock$ModificarStock.btnSeleccionarBien_action}"
                                                binding="#{inventario$ABMStock$ModificarStock.btnSeleccionarBien}" escape="false" id="btnSeleccionarBien"
                                                mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Bien"/>
                                        </td>

                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMStock$ModificarStock.lblCantidad}" for="tfCantidad" id="lblCantidad" styleClass="label" text="Cantidad"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{inventario$ABMStock$ModificarStock.tfCantidad}" onKeyPress="return ValidarFloat(event,this)" columns="10" id="tfCantidad" disabled= "true" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <br/>
                                            <ui:label binding="#{inventario$ABMStock$ModificarStock.lblDescripcion}" id="lblDescripcion" styleClass="label" text="Descripción"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <br/>
                                            <ui:textArea binding="#{inventario$ABMStock$ModificarStock.taDescripcion}" columns="50" id="taDescripcion" rows="5" styleClass="textField" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <br/>
                                            <ui:label binding="#{inventario$ABMStock$ModificarStock.lblDeposito}" for="tfDeposito" id="lblDeposito" styleClass="label" text="Depósito"/>
                                        </td>
                                        <td nowrap="nowrap" >
                                            <br/>
                                            <ui:textField binding="#{inventario$ABMStock$ModificarStock.tfDeposito}" columns="50" disabled="true" id="tfDeposito" styleClass="textField"/>
                                            <ui:button action="#{inventario$ABMStock$ModificarStock.btnSeleccionarDeposito_action}"
                                                binding="#{inventario$ABMStock$ModificarStock.btnSeleccionarDeposito}" escape="false" id="btnSeleccionarDeposito"
                                                mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Depósito"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <br/>
                                            <ui:label binding="#{inventario$ABMStock$ModificarStock.lblPuntoDeReposicion}" for="tfPuntoDeReposicion" id="lblPuntoDeReposicion" styleClass="label" text="Punto de Reposición"/>
                                        </td>
                                        <td nowrap="nowrap" >
                                            <br/>
                                            <ui:textField binding="#{inventario$ABMStock$ModificarStock.tfPuntoDeReposicion}" columns="50" disabled="true" id="tfPuntoDeReposicion" styleClass="textField"/>
                                            <ui:button action="#{inventario$ABMStock$ModificarStock.btnSeleccionarPuntoDeReposicion_action}"
                                                binding="#{inventario$ABMStock$ModificarStock.btnSeleccionarPuntoDeReposicion}" escape="false" id="btnSeleccionarPuntoDeReposicion"
                                                mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Punto de Reposición"/>
                                            <ui:button action="#{inventario$ABMStock$ModificarStock.btnLimpiarPuntoDeReposicion_action}"
                                                binding="#{inventario$ABMStock$ModificarStock.btnLimpiarPuntoDeReposicion}" escape="false" id="btnLimpiarPuntoDeReposicion" mini="true"
                                                styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{inventario$ABMStock$ModificarStock.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{inventario$ABMStock$ModificarStock.btnGuardar_action}"
                                                binding="#{inventario$ABMStock$ModificarStock.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{inventario$ABMStock$ModificarStock.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{inventario$ABMStock$ModificarStock.btnCancelar_action}"
                                                binding="#{inventario$ABMStock$ModificarStock.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{inventario$ABMStock$ModificarStock.hidIdPagina}" id="hidIdPagina" text="#{inventario$ABMStock$ModificarStock.idPagina}"/>
                        <ui:hiddenField binding="#{inventario$ABMStock$ModificarStock.hidIdSubSesion}" id="hidIdSubSesion" text="#{inventario$ABMStock$ModificarStock.idSubSesion}"/>
                        <ui:script binding="#{inventario$ABMStock$ModificarStock.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>