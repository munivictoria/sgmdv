<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="1.2" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:ui="http://www.sun.com/web/ui">
    <jsp:directive.page contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"/>
    <f:view>
        <ui:page binding="#{inventario$ABMArticulo$ModificarArticulo.page1}" id="page1">
            <ui:html binding="#{inventario$ABMArticulo$ModificarArticulo.html1}" id="html1">
                <ui:head binding="#{inventario$ABMArticulo$ModificarArticulo.head1}" id="head1" title="Modificar Artículo">
                    <ui:link binding="#{inventario$ABMArticulo$ModificarArticulo.link1}" id="link1" url="/resources/stylesheet.css"/>

                </ui:head>
                <ui:body binding="#{inventario$ABMArticulo$ModificarArticulo.body1}" focus="form1:tfCodigoArticulo" id="body1"
                         onLoad="parent.footer.location.reload(); Init();" style="background-color: rgb(236, 242, 236); -rave-layout: grid" onKeyUp="eventoByEscape(event,'btnCancelar')">
                    <ui:form binding="#{inventario$ABMArticulo$ModificarArticulo.form1}" id="form1">
                        <div class="formularioABM">
                            <table border="0" class="verde">
                                <caption>
                                    <ui:staticText binding="#{inventario$ABMArticulo$ModificarArticulo.stTitulo}" id="stTitulo" styleClass="tituloABM" text="Modificar Artículo"/>
                                </caption>
                                <tbody>
                                    <tr>
                                        <td colspan="2">
                                            <br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ModificarArticulo.label1}" for="tfCodigoArticulo" id="label1"
                                                      styleClass="label" text="Código"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$ModificarArticulo.tfCodigoArticulo}" id="tfCodigoArticulo" styleClass="textField"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ModificarArticulo.label2}" for="tfNombre" id="label2" styleClass="label" text="Nombre"/>
                                        </td>
                                        <td nowrap="nowrap">
                                            <ui:textField binding="#{inventario$ABMArticulo$ModificarArticulo.tfNombre}" columns="40" id="tfNombre" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ModificarArticulo.label3}" for="tfFechaCompra" id="label3"
                                                      styleClass="label" text="Fecha Compra"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$ModificarArticulo.tfFechaCompra}" id="tfFechaCompra" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"
                                                          />
                                            <!--<ui:staticText binding="#{inventario$ABMArticulo$ModificarArticulo.staticText1}" escape="false" id="staticText1"
                                                           styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ModificarArticulo.label4}" for="tfFechaEntradaServicio" id="label4" styleClass="label" text="Fecha de Entrada en Servicio"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$ModificarArticulo.tfFechaEntradaServicio}" id="tfFechaEntradaServicio" styleClass="textField" onKeyUp="mascara(this,'/',patronFecha,true)" maxLength="10"/>
                                            <!--<ui:staticText binding="#{inventario$ABMArticulo$ModificarArticulo.staticText3}" escape="false" id="staticText3"
                                                           styleClass="label" text="&amp;nbsp;[dd/mm/aaaa]"/>-->
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ModificarArticulo.label14}" for="tfArea" id="label14"
                                                      styleClass="label" text="Área"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$ModificarArticulo.tfArea}" id="tfArea" maxLength="10" styleClass="textField" disabled="true"/>
                                          <!--  <ui:button action="#{inventario$ABMArticulo$ModificarArticulo.btnSeleccionarArea_action}"
                                                       binding="#{inventario$ABMArticulo$ModificarArticulo.btnSeleccionarArea}" escape="false" id="btnSeleccionarArea"
                                                       mini="true" styleClass="buttonSeleccionar" text="&amp;nbsp;" toolTip="Seleccionar" visible="true"/>
                                            <ui:button action="#{inventario$ABMArticulo$ModificarArticulo.btnLimpiarArea_action}"
                                                       binding="#{inventario$ABMArticulo$ModificarArticulo.btnLimpiarArea}" escape="false" id="btnLimpiarArea" mini="true"
                                                       styleClass="buttonLimpiar" text="&amp;nbsp;" toolTip="Limpiar" visible="true"/> -->
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ModificarArticulo.label6}" for="tfCosto" id="label6" styleClass="label" text="Costo"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$ModificarArticulo.tfCosto}" id="tfCosto" onKeyPress="return ValidarFloat(event,this)" style="text-align:right; padding-right:6px;" styleClass="textField"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ModificarArticulo.label5}" for="taDescripcion" id="label5"
                                                      styleClass="label" text="Descripción"/>
                                        </td>
                                        <td>
                                            <ui:textArea binding="#{inventario$ABMArticulo$ModificarArticulo.taDescripcion}" columns="40" id="taDescripcion"
                                                         rows="3" styleClass="textField"/>
                                        </td>
                                        <td align="right" nowrap="nowrap">
                                            <ui:label binding="#{inventario$ABMArticulo$ModificarArticulo.label15}" for="tfEstado" id="label15" styleClass="label" text="Estado"/>
                                        </td>
                                        <td>
                                            <ui:textField binding="#{inventario$ABMArticulo$ModificarArticulo.tfEstado}" id="tfEstado" maxLength="10" style="text-align:right; padding-right:6px;" styleClass="textField" disabled="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <ui:messageGroup binding="#{inventario$ABMArticulo$ModificarArticulo.messageGroup1}" id="messageGroup1" styleClass="grupoMsg"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <table border="0" class="verde" style="border-style:dotted;">
                                                <tr>
                                                    <td align="left" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ModificarArticulo.label7}" id="label7" styleClass="label2" text="Información Técnica"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ModificarArticulo.label8}" for="tfMarca" id="label8"
                                                                  styleClass="label" text="Marca"/>
                                                    </td>
                                                    <td>
                                                        <ui:textField binding="#{inventario$ABMArticulo$ModificarArticulo.tfMarca}" id="tfMarca" styleClass="textField"/>
                                                    </td>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ModificarArticulo.label9}" for="tfModelo" id="label9" styleClass="label" text="Modelo"/>
                                                    </td>
                                                    <td nowrap="nowrap">
                                                        <ui:textField binding="#{inventario$ABMArticulo$ModificarArticulo.tfModelo}" columns="40" id="tfModelo" styleClass="textField"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ModificarArticulo.label10}" for="tfNumeroSerie" id="label10"
                                                                  styleClass="label" text="Numero de Serie"/>
                                                    </td>
                                                    <td>
                                                        <ui:textField binding="#{inventario$ABMArticulo$ModificarArticulo.tfNumeroSerie}" id="tfNumeroSerie" styleClass="textField"/>
                                                    </td>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ModificarArticulo.label11}" for="tfMaterial" id="label11" styleClass="label" text="Material"/>
                                                    </td>
                                                    <td nowrap="nowrap">
                                                        <ui:textField binding="#{inventario$ABMArticulo$ModificarArticulo.tfMaterial}" columns="40" id="tfMaterial" styleClass="textField"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ModificarArticulo.label12}" for="tfColor" id="label12"
                                                                  styleClass="label" text="Color"/>
                                                    </td>
                                                    <td>
                                                        <ui:textField binding="#{inventario$ABMArticulo$ModificarArticulo.tfColor}" id="tfColor" styleClass="textField"/>
                                                    </td>
                                                    <td align="right" nowrap="nowrap">
                                                        <ui:label binding="#{inventario$ABMArticulo$ModificarArticulo.label13}" id="label13" styleClass="label" text="Estado"/>
                                                    </td>
                                                    <td nowrap="nowrap">
                                                        <ui:dropDown binding="#{inventario$ABMArticulo$ModificarArticulo.ddEstado}" id="ddEstado"
                                                                     items="#{inventario$ABMArticulo$ModificarArticulo.ddEstadoDefaultOptions.options}" styleClass="textField"/>
                                                    </td>
                                                </tr>

                                            </table>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td align="right" colspan="6" nowrap="nowrap">
                                            <ui:button action="#{inventario$ABMArticulo$ModificarArticulo.btnGuardar_action}"
                                                       binding="#{inventario$ABMArticulo$ModificarArticulo.btnGuardar}" id="btnGuardar" styleClass="button" text="Guardar"/>
                                            <ui:staticText binding="#{inventario$ABMArticulo$ModificarArticulo.stSeparador}" escape="false" id="stSeparador" text="&amp;nbsp;&amp;nbsp;|&amp;nbsp;&amp;nbsp;"/>
                                            <ui:button action="#{inventario$ABMArticulo$ModificarArticulo.btnCancelar_action}"
                                                       binding="#{inventario$ABMArticulo$ModificarArticulo.btnCancelar}" id="btnCancelar" styleClass="button" text="Cancelar"/>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <ui:hiddenField binding="#{inventario$ABMArticulo$ModificarArticulo.hidIdPagina}" id="hidIdPagina" text="#{inventario$ABMArticulo$ModificarArticulo.idPagina}"/>
                        <ui:hiddenField binding="#{inventario$ABMArticulo$ModificarArticulo.hidIdSubSesion}" id="hidIdSubSesion" text="#{inventario$ABMArticulo$ModificarArticulo.idSubSesion}"/>
                        <ui:script binding="#{inventario$ABMArticulo$ModificarArticulo.scriptValidador}" id="scriptValidador" url="/resources/javascript/validador.js"/>
                    </ui:form>
                </ui:body>
            </ui:html>
        </ui:page>
    </f:view>
</jsp:root>
