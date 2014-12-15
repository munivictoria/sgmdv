<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{inventario$ABMStock$ConsultarStock.page1}" id="page1">
            <ui:html binding="#{inventario$ABMStock$ConsultarStock.html1}" id="html1">
                 <ui:head binding="#{inventario$ABMStock$ConsultarStock.head1}" id="head1" title="Consultar Stock">
                    <ui:link binding="#{inventario$ABMStock$ConsultarStock.link1}" id="link1" url="/resources/stylesheet.css"/>
                </ui:head>
                <ui:body binding="#{inventario$ABMStock$ConsultarStock.body1}" focus="form1:tfNombre" id="body1" onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnVolver')">
                    <ui:form binding="#{inventario$ABMStock$ConsultarStock.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="gris">
                                <caption>
                                    <ui:staticText binding="#{inventario$ABMStock$ConsultarStock.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{inventario$ABMStock$ConsultarStock.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMStock$ConsultarStock.lblBien}" for="tfBien" id="lblBien" styleClass="label" text="Bien"/>
                                        </td>
                                        <td nowrap="nowrap" >
                                            <ui:textField binding="#{inventario$ABMStock$ConsultarStock.tfBien}" columns="50" disabled="true" id="tfBien" styleClass="textField"/>                                            
                                        </td>

                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMStock$ConsultarStock.lblCantidad}" for="tfCantidad" id="lblCantidad" styleClass="label" text="Cantidad"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{inventario$ABMStock$ConsultarStock.tfCantidad}" columns="10" disabled="true" id="tfCantidad" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <br/>
                                            <ui:label binding="#{inventario$ABMStock$ConsultarStock.lblDescripcion}" id="lblDescripcion" styleClass="label" text="Descripción"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <br/>
                                            <ui:textArea binding="#{inventario$ABMStock$ConsultarStock.taDescripcion}" columns="50" id="taDescripcion" disabled="true" rows="5" styleClass="textField" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <br/>
                                            <ui:label binding="#{inventario$ABMStock$ConsultarStock.lblDeposito}" for="tfDeposito" id="lblDeposito" styleClass="label" text="Depósito"/>
                                        </td>
                                        <td nowrap="nowrap" >
                                            <br/>
                                            <ui:textField binding="#{inventario$ABMStock$ConsultarStock.tfDeposito}" columns="50" disabled="true" id="tfDeposito" styleClass="textField"/>                                          
                                        </td>
                                    </tr>
                                    <tr>
                                        <tr>
                                            <td colspan="4">
                                                <br/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right" nowrap="nowrap">
                                                <ui:label binding="#{inventario$ABMStock$ConsultarStock.lblPuntoDeReposicion}" for="tfPuntoDeReposicion" id="lblPuntoDeReposicion" styleClass="label2" text="Punto de Reposición"/>
                                            </td>
                                        </tr>
                                        
                                        <tr>
                                            <td align="right" nowrap="nowrap">
                                                <br/>
                                                <ui:label binding="#{inventario$ABMStock$ConsultarStock.lblCantidadAComprar}" for="tfCantidadAComprar" id="lblCantidadAComprar" styleClass="label" text="Cantidad a comprar"/>
                                            </td>
                                            <td>
                                                <br/>
                                                <ui:textField binding="#{inventario$ABMStock$ConsultarStock.tfCantidadAComprar}" columns="10" id="tfCantidadAComprar" styleClass="textField" disabled="true"/>
                                            </td>
                                        </tr>
                                        <tr>
                                             <td align="right" nowrap="nowrap">
                                                 <br/>
                                                 <ui:label binding="#{inventario$ABMStock$ConsultarStock.lblCantidadLimite}" id="lblCantidadLimite" styleClass="label" text="Cantidad límite"/>
                                             </td>
                                             <td>
                                                <br/>
                                                <ui:textField binding="#{inventario$ABMStock$ConsultarStock.tfCantidadLimite}" id="tfCantidadLimite" styleClass="textField" columns="10" disabled="true"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right" nowrap="nowrap">
                                                <br/>
                                                <ui:label binding="#{inventario$ABMStock$ConsultarStock.lblDescripcionPtoRep}" id="lblDescripcionPtoRep" styleClass="label" text="Descripción"/>
                                            </td>
                                            <td>
                                                <br/>
                                                <ui:textArea binding="#{inventario$ABMStock$ConsultarStock.taDescripcionPtoRep}" columns="40" id="taDescripcionPtoRep" styleClass="textField" rows="5" disabled="true"/>
                                            </td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{inventario$ABMStock$ConsultarStock.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="4" nowrap="nowrap">
                                            <ui:button action="#{inventario$ABMStock$ConsultarStock.btnVolver_action}"
                                                binding="#{inventario$ABMStock$ConsultarStock.btnVolver}" id="btnVolver" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{inventario$ABMStock$ConsultarStock.hidIdPagina}" id="hidIdPagina" text="#{inventario$ABMStock$ConsultarStock.idPagina}"/>
                        <ui:hiddenField binding="#{inventario$ABMStock$ConsultarStock.hidIdSubSesion}" id="hidIdSubSesion" text="#{inventario$ABMStock$ConsultarStock.idSubSesion}"/>
                        <ui:script binding="#{inventario$ABMStock$ConsultarStock.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>