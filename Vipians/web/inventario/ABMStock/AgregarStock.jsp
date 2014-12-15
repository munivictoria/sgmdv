<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{inventario$ABMStock$AgregarStock.page1}" id="page1">
            <ui:html binding="#{inventario$ABMStock$AgregarStock.html1}" id="html1">
                <ui:head binding="#{inventario$ABMStock$AgregarStock.head1}" id="head1" title="Agregar Stock">
                     <ui:link binding="#{inventario$ABMStock$AgregarStock.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{inventario$ABMStock$AgregarStock.body1}" focus="form1:btnSeleccionarBien" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{inventario$ABMStock$AgregarStock.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{inventario$ABMStock$AgregarStock.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{inventario$ABMStock$AgregarStock.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMStock$AgregarStock.lblBien}" for="tfBien" id="lblBien" styleClass="label" text="Bien"/>
                                        </td>
                                        <td nowrap="nowrap" align="left">
                                            <ui:textField binding="#{inventario$ABMStock$AgregarStock.tfBien}" columns="50" disabled="true" id="tfBien" styleClass="textField"/>
                                            <ui:button action="#{inventario$ABMStock$AgregarStock.btnSeleccionarBien_action}"
                                                binding="#{inventario$ABMStock$AgregarStock.btnSeleccionarBien}" escape="false" id="btnSeleccionarBien"
                                                mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Bien"/>
                                       </td>
                                       <td align="left" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMStock$AgregarStock.lblCantidad}" for="tfCantidad" id="lblCantidad" styleClass="label" text="Cantidad"/>
                                            
                                       </td>
                                       <td align="left" nowrap="nowrap">
                                            <ui:textField binding="#{inventario$ABMStock$AgregarStock.tfCantidad}" onKeyPress="return ValidarFloat(event,this)" columns="10" id="tfCantidad" styleClass="textField"/>
                                       </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <br/>
                                            <ui:label binding="#{inventario$ABMStock$AgregarStock.lblDescripcion}" id="lblDescripcion" styleClass="label" text="Descripción"/>
                                        </td>
                                        <td>
                                            <br/>
                                            <ui:textArea binding="#{inventario$ABMStock$AgregarStock.taDescripcion}" columns="50" id="taDescripcion" rows="5" styleClass="textField" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <br/>
                                            <ui:label binding="#{inventario$ABMStock$AgregarStock.lblDeposito}" for="tfDeposito" id="lblDeposito" styleClass="label" text="Depósito"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <br/>
                                            <ui:textField binding="#{inventario$ABMStock$AgregarStock.tfDeposito}" columns="50" disabled="true" id="tfDeposito" styleClass="textField"/>
                                            <ui:button action="#{inventario$ABMStock$AgregarStock.btnSeleccionarDeposito_action}"
                                                binding="#{inventario$ABMStock$AgregarStock.btnSeleccionarDeposito}" escape="false" id="btnSeleccionarDeposito"
                                                mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Depósito"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <br/>
                                            <ui:label binding="#{inventario$ABMStock$AgregarStock.lblPuntoDeReposicion}" for="tfPuntoDeReposicion" id="lblPuntoDeReposicion" styleClass="label" text="Punto de Reposición"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <br/>
                                            <ui:textField binding="#{inventario$ABMStock$AgregarStock.tfPuntoDeReposicion}" columns="50" disabled="true" id="tfPuntoDeReposicion" styleClass="textField"/>
                                            <ui:button action="#{inventario$ABMStock$AgregarStock.btnSeleccionarPuntoDeReposicion_action}"
                                                binding="#{inventario$ABMStock$AgregarStock.btnSeleccionarPuntoDeReposicion}" escape="false" id="btnSeleccionarPuntoDeReposicion"
                                                mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar Punto de Reposición"/>
                                            <ui:button action="#{inventario$ABMStock$AgregarStock.btnLimpiarPuntoDeReposicion_action}"
                                                binding="#{inventario$ABMStock$AgregarStock.btnLimpiarPuntoDeReposicion}" escape="false" id="btnLimpiarPuntoDeReposicion" mini="true"
                                                styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{inventario$ABMStock$AgregarStock.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{inventario$ABMStock$AgregarStock.btnGuardar_action}"
                                                binding="#{inventario$ABMStock$AgregarStock.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{inventario$ABMStock$AgregarStock.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{inventario$ABMStock$AgregarStock.btnCancelar_action}"
                                                binding="#{inventario$ABMStock$AgregarStock.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{inventario$ABMStock$AgregarStock.hidIdPagina}" id="hidIdPagina" text="#{inventario$ABMStock$AgregarStock.idPagina}"/>
                        <ui:hiddenField binding="#{inventario$ABMStock$AgregarStock.hidIdSubSesion}" id="hidIdSubSesion" text="#{inventario$ABMStock$AgregarStock.idSubSesion}"/>
                        <ui:script binding="#{inventario$ABMStock$AgregarStock.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
