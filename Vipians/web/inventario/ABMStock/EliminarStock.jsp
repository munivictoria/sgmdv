<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{inventario$ABMStock$EliminarStock.page1}" id="page1">
            <ui:html binding="#{inventario$ABMStock$EliminarStock.html1}" id="html1">
                <ui:head binding="#{inventario$ABMStock$EliminarStock.head1}" id="head1" title="Eliminar Stock">
                     <ui:link binding="#{inventario$ABMStock$EliminarStock.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{inventario$ABMStock$EliminarStock.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{inventario$ABMStock$EliminarStock.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="rojo">
                                <caption>
                                    <ui:staticText binding="#{inventario$ABMStock$EliminarStock.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{inventario$ABMStock$EliminarStock.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMStock$EliminarStock.lblBien}" for="tfBien" id="lblBien" styleClass="label" text="Bien"/>
                                        </td>
                                        <td nowrap="nowrap" >
                                            <ui:textField binding="#{inventario$ABMStock$EliminarStock.tfBien}" columns="50" disabled="true" id="tfBien" styleClass="textField"/>                                           
                                        </td>

                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMStock$EliminarStock.lblCantidad}" for="tfCantidad" id="lblCantidad" styleClass="label" text="Cantidad"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{inventario$ABMStock$EliminarStock.tfCantidad}" columns="10" disabled="true" id="tfCantidad" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <br/>
                                            <ui:label binding="#{inventario$ABMStock$EliminarStock.lblDescripcion}" id="lblDescripcion" styleClass="label" text="Descripción"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <br/>
                                            <ui:textArea binding="#{inventario$ABMStock$EliminarStock.taDescripcion}" columns="50" id="taDescripcion" disabled="true" rows="5" styleClass="textField" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <br/>
                                            <ui:label binding="#{inventario$ABMStock$EliminarStock.lblDeposito}" for="tfDeposito" id="lblDeposito" styleClass="label" text="Depósito"/>
                                        </td>
                                        <td nowrap="nowrap" >
                                            <br/>
                                            <ui:textField binding="#{inventario$ABMStock$EliminarStock.tfDeposito}" columns="50" disabled="true" id="tfDeposito" styleClass="textField"/>                                 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <br/>
                                            <ui:label binding="#{inventario$ABMStock$EliminarStock.lblPuntoDeReposicion}" for="tfPuntoDeReposicion" id="lblPuntoDeReposicion" styleClass="label" text="Punto de Reposición"/>
                                        </td>
                                        <td nowrap="nowrap" >
                                            <br/>
                                            <ui:textField binding="#{inventario$ABMStock$EliminarStock.tfPuntoDeReposicion}" columns="50" disabled="true" id="tfPuntoDeReposicion" styleClass="textField"/>                                                                                        
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{inventario$ABMStock$EliminarStock.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{inventario$ABMStock$EliminarStock.btnEliminar_action}"
                                                binding="#{inventario$ABMStock$EliminarStock.btnEliminar}" id="btnEliminar" styleClass="button" text="Eliminar"/>
                                            <ui:staticText binding="#{inventario$ABMStock$EliminarStock.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{inventario$ABMStock$EliminarStock.btnCancelar_action}"
                                                binding="#{inventario$ABMStock$EliminarStock.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{inventario$ABMStock$EliminarStock.hidIdPagina}" id="hidIdPagina" text="#{inventario$ABMStock$EliminarStock.idPagina}"/>
                        <ui:hiddenField binding="#{inventario$ABMStock$EliminarStock.hidIdSubSesion}" id="hidIdSubSesion" text="#{inventario$ABMStock$EliminarStock.idSubSesion}"/>
                        <ui:script binding="#{inventario$ABMStock$EliminarStock.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>