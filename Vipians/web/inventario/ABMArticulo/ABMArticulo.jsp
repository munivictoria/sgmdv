<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{inventario$ABMArticulo$ABMArticulo.page1}" id="page1">
            <ui:html binding="#{inventario$ABMArticulo$ABMArticulo.html1}" id="html1">
                <ui:head binding="#{inventario$ABMArticulo$ABMArticulo.head1}" id="head1">
                    <ui:link binding="#{inventario$ABMArticulo$ABMArticulo.link1}" id="link1" url="/resources/stylesheet.css"/>

                </ui:head>
                <ui:body binding="#{inventario$ABMArticulo$ABMArticulo.body1}" focus="form1:tfCodigoArticulo" id="body1"
                         onLoad="parent.footer.location.reload(); Init();" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{inventario$ABMArticulo$ABMArticulo.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{inventario$ABMArticulo$ABMArticulo.stTitulo}" id="stTitulo" styleClass="tituloABM" text="#{inventario$ABMArticulo$ABMArticulo.head1.title}"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ABMArticulo.label1}" for="tfCodigoArticulo" id="label1"
                                                      styleClass="label" text="Código"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$ABMArticulo.tfCodigoArticulo}" id="tfCodigoArticulo" styleClass="textField"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ABMArticulo.label2}" for="tfNombre" id="label2" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{inventario$ABMArticulo$ABMArticulo.tfNombre}" columns="40" id="tfNombre" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ABMArticulo.label3}" for="tfFechaCompra" id="label3"
                                                      styleClass="label" text="Fecha Compra"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$ABMArticulo.tfFechaCompra}" id="tfFechaCompra" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:staticText binding="#{inventario$ABMArticulo$ABMArticulo.staticText1}" escape="false" id="staticText1"
                                                           styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ABMArticulo.label4}" for="tfFechaEntradaServicio" id="label4" styleClass="label" text="Fecha de Entrada en Servicio"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$ABMArticulo.tfFechaEntradaServicio}" id="tfFechaEntradaServicio" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:staticText binding="#{inventario$ABMArticulo$ABMArticulo.staticText3}" escape="false" id="staticText3"
                                                           styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ABMArticulo.label14}" for="tfArea" id="label14"
                                                      styleClass="label" text="Área"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$ABMArticulo.tfArea}" id="tfArea" maxLength="10" styleClass="textField" disabled="true"/>
                                            <ui:button action="#{inventario$ABMArticulo$ABMArticulo.btnSeleccionarArea_action}"
                                                       binding="#{inventario$ABMArticulo$ABMArticulo.btnSeleccionarArea}" escape="false" id="btnSeleccionarArea"
                                                       mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" visible="true"/>
                                            <ui:button action="#{inventario$ABMArticulo$ABMArticulo.btnLimpiarArea_action}"
                                                       binding="#{inventario$ABMArticulo$ABMArticulo.btnLimpiarArea}" escape="false" id="btnLimpiarArea" mini="true"
                                                       styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar" visible="true"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ABMArticulo.label6}" for="tfCosto" id="label6" styleClass="label" text="Costo"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$ABMArticulo.tfCosto}" id="tfCosto" onKeyPress="return ValidarFloat(event,this)" style="text-align:right; padding-right:6px;" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ABMArticulo.label5}" for="taDescripcion" id="label5"
                                                      styleClass="label" text="Descripción"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{inventario$ABMArticulo$ABMArticulo.taDescripcion}" columns="40" id="taDescripcion"
                                                         rows="3" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{inventario$ABMArticulo$ABMArticulo.messageGroup}" id="messageGroup" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <table border="0" class="verde" style="border-style:dotted;">
                                                <tr>
                                                    <td align="left" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ABMArticulo.label7}" id="label7" styleClass="label2" text="Información Técnica"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ABMArticulo.label8}" for="tfMarca" id="label8"
                                                                  styleClass="label" text="Marca"/>
                                                    </td>
                                                    <td>
                                                        <ui:textField binding="#{inventario$ABMArticulo$ABMArticulo.tfMarca}" id="tfMarca" styleClass="textField"/>
                                                    </td>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ABMArticulo.label9}" for="tfModelo" id="label9" styleClass="label" text="Modelo"/>
                                                    </td>
                                                    <td nowrap="nowrap">
                                                        <ui:textField binding="#{inventario$ABMArticulo$ABMArticulo.tfModelo}" columns="40" id="tfModelo" styleClass="textField"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ABMArticulo.label10}" for="tfNumeroSerie" id="label10"
                                                                  styleClass="label" text="Numero de Serie"/>
                                                    </td>
                                                    <td>
                                                        <ui:textField binding="#{inventario$ABMArticulo$ABMArticulo.tfNumeroSerie}" id="tfNumeroSerie" styleClass="textField"/>
                                                    </td>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ABMArticulo.label11}" for="tfMaterial" id="label11" styleClass="label" text="Material"/>
                                                    </td>
                                                    <td nowrap="nowrap">
                                                        <ui:textField binding="#{inventario$ABMArticulo$ABMArticulo.tfMaterial}" columns="40" id="tfMaterial" styleClass="textField"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ABMArticulo.label12}" for="tfColor" id="label12"
                                                                  styleClass="label" text="Color"/>
                                                    </td>
                                                    <td>
                                                        <ui:textField binding="#{inventario$ABMArticulo$ABMArticulo.tfColor}" id="tfColor" styleClass="textField"/>
                                                    </td>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ABMArticulo.label13}" id="label13" styleClass="label" text="Estado"/>
                                                    </td>
                                                    <td nowrap="nowrap">
                                                        <ui:dropDown binding="#{inventario$ABMArticulo$ABMArticulo.ddEstado}" id="ddEstado"
                                                                     items="#{inventario$ABMArticulo$ABMArticulo.ddEstadoDefaultOptions.options}" styleClass="textField"/>
                                                    </td>
                                                </tr>

                                            </table>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{inventario$ABMArticulo$ABMArticulo.btnGuardar_action}"
                                                       binding="#{inventario$ABMArticulo$ABMArticulo.btnGuardar}" id="btnGuardar" styleClass="button"/>
                                            <ui:staticText binding="#{inventario$ABMArticulo$ABMArticulo.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{inventario$ABMArticulo$ABMArticulo.btnCancelar_action}"
                                                       binding="#{inventario$ABMArticulo$ABMArticulo.btnCancelar}" id="btnCancelar" styleClass="button" />
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{inventario$ABMArticulo$ABMArticulo.hidIdPagina}" id="hidIdPagina" text="#{inventario$ABMArticulo$ABMArticulo.idPagina}"/>
                        <ui:hiddenField binding="#{inventario$ABMArticulo$ABMArticulo.hidIdSubSesion}" id="hidIdSubSesion" text="#{inventario$ABMArticulo$ABMArticulo.idSubSesion}"/>
                        <ui:script binding="#{inventario$ABMArticulo$ABMArticulo.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
