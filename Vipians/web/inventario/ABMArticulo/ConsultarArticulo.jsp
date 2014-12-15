<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{inventario$ABMArticulo$ConsultarArticulo.page1}" id="page1">
            <ui:html binding="#{inventario$ABMArticulo$ConsultarArticulo.html1}" id="html1">
                <ui:head binding="#{inventario$ABMArticulo$ConsultarArticulo.head1}" id="head1" title="Consultar Artículo">
                    <ui:link binding="#{inventario$ABMArticulo$ConsultarArticulo.link1}" id="link1" url="/resources/stylesheet.css"/>

                </ui:head>
                <ui:body binding="#{inventario$ABMArticulo$ConsultarArticulo.body1}" focus="form1:tfCodigoArticulo" id="body1"
                         onLoad="parent.footer.location.reload();Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{inventario$ABMArticulo$ConsultarArticulo.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{inventario$ABMArticulo$ConsultarArticulo.stTitulo}" id="stTitulo" styleClass="tituloABM" text="Consultar Artículo"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ConsultarArticulo.label1}" for="tfCodigoArticulo" id="label1"
                                                      styleClass="label" text="Código"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$ConsultarArticulo.tfCodigoArticulo}" id="tfCodigoArticulo" styleClass="textField" disabled="true"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ConsultarArticulo.label2}" for="tfNombre" id="label2" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{inventario$ABMArticulo$ConsultarArticulo.tfNombre}" columns="40" id="tfNombre" styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ConsultarArticulo.label3}" for="tfFechaCompra" id="label3"
                                                      styleClass="label" text="Fecha Compra"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$ConsultarArticulo.tfFechaCompra}" id="tfFechaCompra" maxLength="10" styleClass="textField" disabled="true"/>
                                            <!--<ui:staticText binding="#{inventario$ABMArticulo$ConsultarArticulo.staticText1}" escape="false" id="staticText1"
                                                           styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ConsultarArticulo.label4}" for="tfFechaEntradaServicio" id="label4" styleClass="label" text="Fecha de Entrada en Servicio"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$ConsultarArticulo.tfFechaEntradaServicio}" id="tfFechaEntradaServicio" maxLength="10" styleClass="textField" disabled="true"/>
                                            <!--<ui:staticText binding="#{inventario$ABMArticulo$ConsultarArticulo.staticText3}" escape="false" id="staticText3"
                                                           styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ConsultarArticulo.label14}" for="tfArea" id="label14"
                                                      styleClass="label" text="Área"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$ConsultarArticulo.tfArea}" id="tfArea" maxLength="10" styleClass="textField" disabled="true"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ConsultarArticulo.label6}" for="tfCosto" id="label6" styleClass="label" text="Costo"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$ConsultarArticulo.tfCosto}" id="tfCosto" maxLength="10" style="text-align:right; padding-right:6px;" styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ConsultarArticulo.label5}" for="taDescripcion" id="label5"
                                                      styleClass="label" text="Descripción"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{inventario$ABMArticulo$ConsultarArticulo.taDescripcion}" columns="40" id="taDescripcion"
                                                         rows="3" styleClass="textField" disabled="true"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ConsultarArticulo.label15}" for="tfEstado" id="label15"
                                                      styleClass="label" text="Estado"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$ConsultarArticulo.tfEstado}" id="tfEstado" styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{inventario$ABMArticulo$ConsultarArticulo.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <table border="0" class="verde" style="border-style:dotted;">
                                                <tr>
                                                    <td align="left" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ConsultarArticulo.label7}" id="label7" styleClass="label2" text="Información Técnica"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ConsultarArticulo.label8}" for="tfMarca" id="label8"
                                                                  styleClass="label" text="Marca"/>
                                                    </td>
                                                    <td>
                                                        <ui:textField binding="#{inventario$ABMArticulo$ConsultarArticulo.tfMarca}" id="tfMarca" styleClass="textField" disabled="true"/>
                                                    </td>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ConsultarArticulo.label9}" for="tfModelo" id="label9" styleClass="label" text="Modelo"/>
                                                    </td>
                                                    <td nowrap="nowrap">
                                                        <ui:textField binding="#{inventario$ABMArticulo$ConsultarArticulo.tfModelo}" columns="40" id="tfModelo" styleClass="textField" disabled="true"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ConsultarArticulo.label10}" for="tfNumeroSerie" id="label10"
                                                                  styleClass="label" text="Numero de Serie"/>
                                                    </td>
                                                    <td>
                                                        <ui:textField binding="#{inventario$ABMArticulo$ConsultarArticulo.tfNumeroSerie}" id="tfNumeroSerie" styleClass="textField" disabled="true"/>
                                                    </td>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ConsultarArticulo.label11}" for="tfMaterial" id="label11" styleClass="label" text="Material"/>
                                                    </td>
                                                    <td nowrap="nowrap">
                                                        <ui:textField binding="#{inventario$ABMArticulo$ConsultarArticulo.tfMaterial}" columns="40" id="tfMaterial" styleClass="textField" disabled="true"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ConsultarArticulo.label12}" for="tfColor" id="label12"
                                                                  styleClass="label" text="Color"/>
                                                    </td>
                                                    <td>
                                                        <ui:textField binding="#{inventario$ABMArticulo$ConsultarArticulo.tfColor}" id="tfColor" styleClass="textField" disabled="true"/>
                                                    </td>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ConsultarArticulo.label13}" id="label13" styleClass="label" text="Estado"/>
                                                    </td>
                                                    <td nowrap="nowrap">
                                                        <ui:dropDown binding="#{inventario$ABMArticulo$ConsultarArticulo.ddEstado}" id="ddEstado"
                                                                     items="#{inventario$ABMArticulo$ConsultarArticulo.ddEstadoDefaultOptions.options}" styleClass="textField" disabled="true"/>
                                                    </td>
                                                </tr>

                                            </table>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{inventario$ABMArticulo$ConsultarArticulo.btnCancelar_action}"
                                                       binding="#{inventario$ABMArticulo$ConsultarArticulo.btnCancelar}" id="btnCancelar" styleClass="button" text="Volver"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{inventario$ABMArticulo$ConsultarArticulo.hidIdPagina}" id="hidIdPagina" text="#{inventario$ABMArticulo$ConsultarArticulo.idPagina}"/>
                        <ui:hiddenField binding="#{inventario$ABMArticulo$ConsultarArticulo.hidIdSubSesion}" id="hidIdSubSesion" text="#{inventario$ABMArticulo$ConsultarArticulo.idSubSesion}"/>
                        <ui:script binding="#{inventario$ABMArticulo$ConsultarArticulo.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>

                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
